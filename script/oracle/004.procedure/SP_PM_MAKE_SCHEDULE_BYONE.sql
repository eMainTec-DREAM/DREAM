CREATE OR REPLACE PROCEDURE DREAM.SP_PM_MAKE_SCHEDULE_BYONE(
     v_comp_no     IN varchar2          
    ,v_pm_id       IN number
    ,v_user_id     in number
    ,v_target_date IN varchar2  
) IS
    v_count                                                       number(4);   
    v_is_manage_pmi_with_order                       varchar2(20);
    
    CURSOR c_pm IS
                SELECT
                     A.pm_id
                    ,a.wo_type
                    ,a.pm_type
                    ,A.schedule_type -- T:Time, U:Usage
                    ,a.lnwrklist_id
                FROM TAPMLST A
                WHERE 1=1
                    AND A.comp_no = v_comp_no
                    AND A.pm_id     = v_pm_id
                    AND A.is_active = 'Y'
            ;
  
BEGIN

   -- 예방점검을 Work Order로 관리할지 여부 파악..
   -- WO_TYPE:PMI 이고 PM_TYPE:  INS, DINS, EINS, RINS
    select count(*)
    into v_count 
    from taconfig
    where comp_no = v_comp_no
        and name = 'IS_MANAGE_PMI_WITH_ORDER'
    ;
    
    if v_count > 0 then
        select nvl(value$,'N')
        into v_is_manage_pmi_with_order 
        from taconfig
        where comp_no = v_comp_no
            and name = 'IS_MANAGE_PMI_WITH_ORDER'
        ;
    else
        v_is_manage_pmi_with_order := 'N';
    end if;
    
    
    IF v_is_manage_pmi_with_order = 'Y' then   -- Y 이면 Work Order로 관리함.
            FOR c1 IN c_pm LOOP
                IF c1.wo_type = 'PMI' and c1.pm_type in ('DINS', 'EINS') then  -- 일상점검, 이벤트 점검일 경우 어떤 오더도 생성하지 않음.
                    null; 
                ELSE  -- 나머지 일때는 전체를 Work Order로 관리함.
                        IF c1.schedule_type = 'T' THEN    -- Period Time Based Scheduling
                                SP_PM_MAKE_SCHEDWO_BYONE_TIME (v_comp_no, c1.pm_id, v_target_date );
                        ELSIF c1.schedule_type = 'U' THEN    -- Run Time Based Scheduling
                                IF C1.LNWRKLIST_ID IS NOT NULL THEN  -- 가동시간이 설정되어 있지 않으면 SKIP
                                    SP_PM_MAKE_SCHEDWO_BYONE_USE(v_comp_no, c1.pm_id, v_target_date );
                                END IF;
                        END IF;
                END IF;
            END LOOP;
    ELSE   -- N 이면 점검을 Work Oder로 관리하지 않고 별도 테이블 및 화면으로 관리
            FOR c1 IN c_pm LOOP
            
                IF c1.wo_type = 'PMI' and c1.pm_type in ('INS', 'RINS') then  -- 점검중에서 점검, 정기점검, Event점검은  
                        IF c1.schedule_type = 'T' THEN    -- Period Time Based Scheduling
                                SP_PM_MAKE_SCHEDPMI_BYONE_TIME (v_comp_no, c1.pm_id, v_target_date );
                        ELSIF c1.schedule_type = 'U' THEN    -- Run Time Based Scheduling
                                IF C1.LNWRKLIST_ID IS NOT NULL THEN  -- 가동시간이 설정되어 있지 않으면 SKIP
                                    SP_PM_MAKE_SCHEDPMI_BYONE_USE(v_comp_no, c1.pm_id, v_target_date );
                                END IF;
                        END IF;
                ELSIF c1.wo_type = 'PMI' and c1.pm_type in ('DINS', 'EINS') then  -- 일상점검, 이벤트 점검일 경우 어떤 오더도 생성하지 않음.
                    null; 
                ELSE  -- 나머지는 모두 Work Order로 관리
                        IF c1.schedule_type = 'T' THEN    -- Period Time Based Scheduling
                                SP_PM_MAKE_SCHEDWO_BYONE_TIME (v_comp_no, c1.pm_id, v_target_date );
                        ELSIF c1.schedule_type = 'U' THEN    -- Run Time Based Scheduling
                                IF C1.LNWRKLIST_ID IS NOT NULL THEN  -- 가동시간이 설정되어 있지 않으면 SKIP
                                    SP_PM_MAKE_SCHEDWO_BYONE_USE(v_comp_no, c1.pm_id, v_target_date );
                                END IF;
                        END IF;
                END IF;
                
            END LOOP;    
    END IF;
    
    
     UPDATE TABATPGM SET 
         exe_by = v_user_id
        ,last_exe_date = TO_CHAR(SYSDATE,'yyyymmdd')
        ,last_exe_time = TO_CHAR(SYSDATE,'yyyymmddhh24miss')
    WHERE comp_no = v_comp_no
        AND batpgm_no = 'TAPMSCHED_ONE'
    ;
    
    
END;
/

CREATE OR REPLACE PROCEDURE DREAM.SP_PM_MAKE_SCHEDWO_BYONE_TIME(
     v_comp_no     IN varchar2          
    ,v_pm_id       IN number
    ,v_target_date IN varchar2   -- 이 일자까지를 목표로 예방작업일정을 생성함.  
) IS
    v_count                   number(4);   
    v_del_start_date      varchar2(8);  -- 기존작업을 삭제할 시작 일자 변
    v_last_sch_date       varchar2(8);  -- 일정이 생성된 마지막 일자를 기준으로 다음일정을 생성할 일자..
    v_next_sch_date      varchar2(8);
    v_to_day                 varchar2(8);  -- 오늘
    
    CURSOR c_pm IS
            SELECT
                 A.pm_id
                ,A.CYCLE
                ,A.period_type 
                ,A.last_sch_date    -- 다음일정 예정일[주말보정전]
                ,A.wrkcallist_id
            FROM TAPMLST A
            WHERE 1=1
                AND A.comp_no = v_comp_no
                AND A.pm_id     = v_pm_id
                AND A.is_active = 'Y'
                AND A.last_sch_date <= v_target_date
        ;
  
BEGIN

    v_to_day := TO_CHAR(SYSDATE,'yyyymmdd');
    -- IS_USE_WORK_CALENDAR 값은 사용하지 않음.
     
    
    FOR c1 IN c_pm LOOP
    
                -- 스케쥴을 시작하기로 한 날짜 이후에 work order가 완료되었는지 확인.
                -- 발행된건이 있으면 그 건의 날짜를 시작날짜로 지정하여 스케쥴을 생성함.
                SELECT COUNT(*)
                INTO v_count
                FROM TAPMSCHED
                WHERE comp_no = v_comp_no
                    AND pm_id = c1.pm_id
                    AND pmsched_status IN ('C') -- work order가 완료되었는지 확인.
                    AND plan_date >= c1.last_sch_date  -- 스케쥴을 시작하기로 한 날짜 이후에 work order가 완료되었는지 확인.
                ;
                
                IF v_count > 0 THEN 
                        -- 오늘부터 스케쥴시작 날짜 이전까지의  완료되지 않는 스케쥴, work order를 삭제해야 함. 
                        SELECT NVL(MAX(plan_date),c1.last_sch_date)
                        INTO v_last_sch_date  -- 이 날짜가 스케쥴을 시작할 날짜임.
                        FROM TAPMSCHED
                        WHERE comp_no = v_comp_no
                            AND pm_id = c1.pm_id
                            AND pmsched_status IN ('C') -- work order가 완료된 상태
                            AND plan_date >= c1.last_sch_date
                        ;
                        
                        -- 스케쥴 시작할 날짜가 오늘보다 큰지 비교해서 기존에 생성된 작업중에 완료안된 작업을 삭제함.
                        -- 미 작업이라고 하더라도 오늘일자 이후에 데이타만 삭제함. 오늘이전 데이타는 삭제하지 않음.
                        -----------------------------------------------------------------------------------------------------------------------------------------------
                        if v_to_day < v_last_sch_date then
                            v_del_start_date := v_to_day;
                        else
                            v_del_start_date := v_last_sch_date;
                        end if;
                        -- 작업이 완료되지 않은 오더를 삭제함. 오더에 상태가 결재중이거나, 취소이거나 하면 어떻게 처리해야 할지 추가로 결정해야 함.
                        DELETE FROM TAWORKORDER
                        WHERE comp_no = v_comp_no
                              AND pmsched_id IN ( SELECT pmsched_id 
                                                             FROM TAPMSCHED 
                                                            WHERE comp_no = v_comp_no
                                                               AND pm_id = c1.pm_id
                                                               AND pmsched_status IN ('P','S') -- 작업완료되지 않은 작업.
                                                               AND plan_date >= v_del_start_date )
                       ;
                        -- 작업이 완료되지 않은 예방작업일정을 삭제함
                        DELETE FROM TAPMSCHED
                        WHERE comp_no = v_comp_no
                           AND pm_id = c1.pm_id
                           AND pmsched_status IN ('P','S')  -- 작업완료되지 않은 작업.
                           AND plan_date >= v_del_start_date
                       ;
                       -----------------------------------------------------------------------------------------------------------------------------------------------
                       
                       -- 예방작업을 완료한 최종일자를 확인했으므로 그 다음 일정을 언제 생성해야 할지를 알아냄.
                       SELECT    
                            CASE 
                                 WHEN c1.period_type = 'D' THEN TO_CHAR(TO_DATE(v_last_sch_date,'yyyymmdd') + (c1.CYCLE * 1 ),'yyyymmdd')
                                 WHEN c1.period_type = 'W' THEN TO_CHAR(TO_DATE(v_last_sch_date,'yyyymmdd') + (7 * c1.CYCLE * 1 ),'yyyymmdd') 
                                 WHEN c1.period_type = 'M' THEN TO_CHAR(ADD_MONTHS(TO_DATE(v_last_sch_date,'yyyymmdd'),c1.CYCLE * 1),'yyyymmdd') 
                                 WHEN c1.period_type = 'Y' THEN TO_CHAR(ADD_MONTHS(TO_DATE(v_last_sch_date,'yyyymmdd'),12 * c1.CYCLE * 1),'yyyymmdd')
                            END AS next_date
                        INTO v_last_sch_date
                        FROM dual
                        ;

                ELSE 
                        -- 사용자가 입력한 날짜를 작업일정의 시작으로 결정함.
                        v_last_sch_date := c1.last_sch_date;
                       
                       -- 미 작업이라고 하더라도 오늘일자 이후에 데이타만 삭제함. 오늘이전 데이타는 삭제하지 않음. 
                        if v_to_day < v_last_sch_date then
                            v_del_start_date := v_to_day;
                        else
                            v_del_start_date := v_last_sch_date;
                        end if;
                        
                        -- 작업이 완료되지 않은 오더를 삭제함. 오더에 상태가 결재중이거나, 취소이거나 하면 어떻게 처리해야 할지 추가로 결정해야 함.
                        DELETE FROM TAWORKORDER
                        WHERE comp_no = v_comp_no
                              AND pmsched_id IN ( SELECT pmsched_id 
                                                             FROM TAPMSCHED 
                                                           WHERE comp_no = v_comp_no
                                                               AND pm_id = c1.pm_id
                                                               AND pmsched_status IN ('P','S') -- 작업완료되지 않은 작업.
                                                               AND plan_date >= v_del_start_date )
                       ;
                        
                        DELETE FROM TAPMSCHED
                        WHERE comp_no = v_comp_no
                           AND pm_id = c1.pm_id
                           AND pmsched_status IN ('P','S') -- 작업완료되지 않은 작업.
                           AND plan_date >= v_del_start_date
                       ;
                       
                END IF;
                -- 여기까지 정리...
                -- 예방작업 주기의 시작일자를 기준으로 기존 데이타를 정리했음.
                -- 예방작업 주기의 시작일자 이후에 완료건이 없으면 그 날짜 이후에 작업이나 작업일정을 삭제함.
                -- 예방작업 주기의 시작일자 이후에 완교건이 있으면 그 완료된 일자를 시점으로 작업이나 작업예정일정을 삭제함.
                
                
                
                -- 여기서 부터는 예방작업 일정을 만드는 작업을 수행함.
                -- 먼저 예방작업을 언제 만들지를 갱신하고 시작업..v_last_sch_date는 이 날짜 부터 일정을 생성해야 한다는 의미임.
                UPDATE TAPMLST SET
                       last_sch_date = v_last_sch_date
                      ,next_sch_date = v_last_sch_date
                WHERE comp_no = v_comp_no
                      AND pm_id = c1.pm_id
                ; 
                
                
                --  v_target_date까지 예방작업일정을 생성함.
                WHILE v_last_sch_date <= v_target_date  LOOP
                        
                        -- v_last_sch_date 는 이 날짜에 일정을 생성해야 하는 일자인데 이 날짜가 근무일이 아니면 근무일을 찾아서 보정해 줘야 함.
                        -- v_last_sch_date 이 작업을 만들어야 하는 일자. cycle,period_type을 체크해서 이 과거 범위한에 최대한 근접한 근무일자에 작업을 만들어 줌.
                            SELECT  nvl(max(cal_date), '19721126')
                            INTO v_next_sch_date
                            FROM TAWRKCALENDAR A
                            WHERE A.comp_no = v_comp_no
                                AND a.wrkcallist_id = c1.wrkcallist_id
                                AND A.cal_date <=v_last_sch_date
                                and a.cal_date >=  (CASE 
                                                                 WHEN c1.period_type = 'D' THEN TO_CHAR(TO_DATE(v_last_sch_date,'yyyymmdd') + (c1.CYCLE *- 1 ),'yyyymmdd')
                                                                 WHEN c1.period_type = 'W' THEN TO_CHAR(TO_DATE(v_last_sch_date,'yyyymmdd') + (7 * c1.CYCLE * -1 ),'yyyymmdd') 
                                                                 WHEN c1.period_type = 'M' THEN TO_CHAR(ADD_MONTHS(TO_DATE(v_last_sch_date,'yyyymmdd'),c1.CYCLE * -1),'yyyymmdd') 
                                                                 WHEN c1.period_type = 'Y' THEN TO_CHAR(ADD_MONTHS(TO_DATE(v_last_sch_date,'yyyymmdd'),c1.CYCLE * -1),'yyyymmdd') -- 연간이더라도 한달범위로 제한함.
                                                            END )
                                AND A.is_work = 'Y'
                            ;
                            
                            -- 19721126일 아니라면 근무일이 있다는 얘기이므로 그 날짜를 기준으로 작업을 생성하면 됨.
                            if v_next_sch_date <> '19721126' then
                                -- 단 그 근무일에 과거이면 생성하지 않고 오늘이후 조건시에만 생성해야 함.
                                IF v_next_sch_date > v_to_day THEN
                                    INSERT INTO TAPMSCHED(comp_no, pmsched_id, pmequip_id, equip_id,  pm_id, plan_init_date, plan_date,sched_date, pmsched_status)
                                    SELECT comp_no, sqapmsched_id.NEXTVAL, a.pmequip_id, a.equip_id, c1.pm_id, v_last_sch_date, v_next_sch_date, v_next_sch_date,'P'
                                    from TAPMEQUIP a
                                    where a.comp_no = v_comp_no
                                        and a.pm_id =  c1.pm_id; 
                                END IF;  
                            else
                                -- 19721126이면 근무일이 없다는 얘기이므로 상황이 복잡하기 때문에 경우에 따라서 다른 논리를 적용함.
                                -- 1. 일상점검이 아닌경우 휴일이라고 하더라도 일정을 생성함.                                
                                -- 2. 일상점검을 일정을 생성하지 않음.(일상점검의 기준: cycle < 7, period_type=D)
                                v_next_sch_date := v_last_sch_date;
                                if c1.period_type <> 'D' and c1.CYCLE > 7 then
                                    IF v_next_sch_date > v_to_day THEN
                                        INSERT INTO TAPMSCHED(comp_no, pmsched_id, pmequip_id, equip_id,  pm_id, plan_init_date, plan_date,sched_date, pmsched_status)
                                        SELECT comp_no, sqapmsched_id.NEXTVAL, a.pmequip_id, a.equip_id, c1.pm_id, v_last_sch_date, v_next_sch_date, v_next_sch_date,'P'
                                        from TAPMEQUIP a
                                        where a.comp_no = v_comp_no
                                            and a.pm_id =  c1.pm_id; 
                                    END IF;  
                                else
                                    null;
                                end if;
                                  
                            end if;
                            
                            UPDATE TAPMLST SET
                                       last_sch_date = v_last_sch_date   -- 휴일 보전전 일정, 이 일정으로 다음 작업을 생성해야 함
                                      ,next_sch_date = v_next_sch_date  -- 휴일을 보정하고 난 실제 작업일정.
                            WHERE comp_no = v_comp_no
                            AND pm_id = c1.pm_id
                            ; 
                            
                            -- 다음에는 언제 일정을 생성해야할지 찾기.
                         SELECT    
                            CASE 
                                 WHEN c1.period_type = 'D' THEN TO_CHAR(TO_DATE(v_last_sch_date,'yyyymmdd') + (c1.CYCLE * 1 ),'yyyymmdd')
                                 WHEN c1.period_type = 'W' THEN TO_CHAR(TO_DATE(v_last_sch_date,'yyyymmdd') + (7 * c1.CYCLE * 1 ),'yyyymmdd') 
                                 WHEN c1.period_type = 'M' THEN TO_CHAR(ADD_MONTHS(TO_DATE(v_last_sch_date,'yyyymmdd'),c1.CYCLE * 1),'yyyymmdd') 
                                 WHEN c1.period_type = 'Y' THEN TO_CHAR(ADD_MONTHS(TO_DATE(v_last_sch_date,'yyyymmdd'),12 * c1.CYCLE * 1),'yyyymmdd')
                            END AS next_date
                        INTO v_last_sch_date
                        FROM dual
                        ;
                        
                END LOOP;
                
        
    END LOOP;
    
    
END;
/

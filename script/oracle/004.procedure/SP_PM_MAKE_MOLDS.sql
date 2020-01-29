CREATE OR REPLACE PROCEDURE SP_PM_MAKE_MOLDS(
     v_comp_no     IN varchar2          
    ,v_user_no     IN varchar2  
) IS
    v_count             number(4);   
    v_user_id             number(18);
    
    -- exec SP_PM_MAKE_MOLDS('100','ADMIN')
    
    --삭제할 wkor_id 목록
    CURSOR c_wkor_id IS
                SELECT wkor_id, comp_no
                FROM TAWOEQUIP x
                WHERE 1=1 
                AND x.wkor_id IN (SELECT wkor_id
                                             FROM TAWORKORDER 
                                             WHERE comp_no  = v_comp_no
                                             AND wo_status != 'C'
                                             AND wo_gen_type='PMPLAN'
                                             AND wkor_date > TO_CHAR(SYSDATE-1,'YYYYMMDD') )
                AND x.equip_id in (SELECT equip_id
                                            FROM taequipment
                                            WHERE comp_no = v_comp_no
                                            AND eqctg_type='MD'
                                            AND eq_status='R')
                AND x.comp_no = v_comp_no
            ;

    --삭제할 pmsched_id 목록 
    CURSOR c_pmsched_id IS
                SELECT pmsched_id, comp_no
                FROM TAPMSCHED
                WHERE comp_no = v_comp_no
                AND pmsched_status !='C'
                AND plan_date > TO_CHAR(SYSDATE-1,'YYYYMMDD')
                AND pm_id IN (SELECT pm_id 
                                        FROM TAPMEQUIP
                                        WHERE comp_no =v_comp_no
                                        AND equip_id IN  (select equip_id
                                                                FROM taequipment
                                                                where comp_no = v_comp_no
                                                                and eqctg_type='MD'
                                                                and eq_status='R')
                                        )
                 ;
                 
    --현재 Line에 올라와 있는 금형 설비의 PM List
    CURSOR c_pmlist IS
                SELECT pm_id, comp_no
                FROM TAPMLST
                WHERE comp_no = v_comp_no
                AND pm_id IN (SELECT pm_id 
                                        FROM TAPMEQUIP
                                        WHERE comp_no = v_comp_no
                                        AND equip_id IN  (SELECT equip_id
                                                                FROM taequipment
                                                                WHERE comp_no = v_comp_no
                                                                AND eqctg_type='MD'
                                                                AND eq_status='R'
                                                                AND eqstrloc_no IN (SELECT cdusrd_no
                                                                                            FROM TACDUSRD
                                                                                            WHERE cdusrm_id = (SELECT cdusrm_id
                                                                                                                            FROM TACDUSRM
                                                                                                                            WHERE 1=1
                                                                                                                            AND comp_no = v_comp_no
                                                                                                                            AND dir_type='EQSTRLOC_NO'
                                                                                                                            )
                                                                                              AND  cdusrd_no not in ('STOCK','IR','ER')
                                                                                              AND IS_USE = 'Y'
                                                                                              )
                                                                   )
                                        )
                 ;
  
BEGIN
    --W/O 삭제 
    FOR c1 IN c_wkor_id LOOP
    
            -- W/O 삭제
            DELETE FROM TAWORKORDER
            WHERE 1=1
            AND wkor_id = c1.wkor_id
            AND comp_no = c1.comp_no
            ;
            -- W/O FAIL 삭제
            DELETE FROM TAWOFAIL
            WHERE 1=1
            AND wkor_id = c1.wkor_id
            AND comp_no = c1.comp_no
            ;
            -- W/O CRAFT 삭제
            DELETE FROM TAWOCRAFT
            WHERE 1=1
            AND wkor_id = c1.wkor_id
            AND comp_no = c1.comp_no
            ;
            -- W/O PARTS 삭제
            DELETE FROM TAWOPARTS
            WHERE 1=1
            AND wkor_id = c1.wkor_id
            AND comp_no = c1.comp_no
            ;
            -- W/O POINT 삭제
            DELETE FROM TAWOPOINT
            WHERE 1=1
            AND wkor_id = c1.wkor_id
            AND comp_no = c1.comp_no
            ;
            -- W/O WOEQUIP 삭제
            DELETE FROM TAWOEQUIP
            WHERE 1=1
            AND wkor_id = c1.wkor_id
            AND comp_no = c1.comp_no
            ;
            -- W/O 긴급출고 상태 변경
            UPDATE TAPTEMGISSLIST SET
                        ptemg_task_status = 'W',
                        wkor_id = ''
            WHERE 1=1
            AND wkor_id = c1.wkor_id
            AND comp_no = c1.comp_no
            ;
            
    END LOOP;
    
    --PM SCHED 삭제 
    FOR c2 IN c_pmsched_id LOOP
    
            DELETE FROM TAPMSCHED
            WHERE 1=1
            AND comp_no = c2.comp_no
            AND pmsched_id = c2.pmsched_id
            ;
    
    END LOOP;
    
    -- 금형설비의 PM 마스터의  is_active값 모두 N , Last sch Date 값 변경
    UPDATE TAPMLST
    SET is_active = 'N'
          ,last_sch_date = TO_CHAR(SYSDATE-1,'YYYYMMDD')
    WHERE comp_no = v_comp_no
    AND pm_id IN (SELECT pm_id 
                            FROM TAPMEQUIP
                            WHERE comp_no = v_comp_no
                            AND equip_id IN  (SELECT equip_id
                                                    FROM taequipment
                                                    WHERE comp_no = v_comp_no
                                                    AND eqctg_type='MD'
                                                    AND eq_status='R')
                            )
    ;
    
    -- 금형설비의 PM 마스터의 next sch Date 값 변경
    UPDATE TAPMLST
    SET next_sch_date =  (CASE 
                                 WHEN period_type = 'D' THEN TO_CHAR(TO_DATE(last_sch_date,'yyyymmdd') + (cycle * 1 ),'yyyymmdd')
                                 WHEN period_type = 'W' THEN TO_CHAR(TO_DATE(last_sch_date,'yyyymmdd') + (7 * cycle * 1 ),'yyyymmdd') 
                                 WHEN period_type = 'M' THEN TO_CHAR(ADD_MONTHS(TO_DATE(last_sch_date,'yyyymmdd'),cycle * 1),'yyyymmdd') 
                                 WHEN period_type = 'Y' THEN TO_CHAR(ADD_MONTHS(TO_DATE(last_sch_date,'yyyymmdd'),12 * cycle * 1),'yyyymmdd')
                            END)
    WHERE comp_no = v_comp_no
    AND pm_id IN (SELECT pm_id 
                            FROM TAPMEQUIP
                            WHERE comp_no = v_comp_no
                            AND equip_id IN  (SELECT equip_id
                                                    FROM taequipment
                                                    WHERE comp_no = v_comp_no
                                                    AND eqctg_type='MD'
                                                    AND eq_status='R')
                            )
    ;
    
    --Line에 올라와있는 금형설비의 PM 마스터의 is_active값 Y로 변경 
    FOR c3 IN c_pmlist LOOP
    
            UPDATE TAPMLST
            SET is_active = 'Y'
            WHERE 1=1
            AND comp_no = c3.comp_no
            AND pm_id = c3.pm_id
            ;
    
    END LOOP;
    
    --SP_PM_MAKE_SCHEDULE_BYALL 프로시저 실행
    SP_PM_MAKE_SCHEDULE_BYALL(v_comp_no,v_user_no);
    --SP_PM_MAKE_WORKORDER 프로시저 실행
    SP_PM_MAKE_WO_BYALL(v_comp_no,v_user_no);
    
END;
/

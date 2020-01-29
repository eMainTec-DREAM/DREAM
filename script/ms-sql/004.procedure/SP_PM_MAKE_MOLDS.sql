CREATE PROCEDURE [dbo].[SP_PM_MAKE_MOLDS] (
       @v_comp_no      varchar(6)
      ,@v_user_no      varchar(40)
) 
as
    SET NOCOUNT ON;
    
    declare @v_count          bigint
           ,@v_user_id        bigint

    --삭제할 wkor_id 목록
    DECLARE c_wkor_id CURSOR FOR
                SELECT wkor_id
                FROM TAWOEQUIP x
                WHERE 1=1 
                AND x.wkor_id IN (SELECT wkor_id
                                             FROM TAWORKORDER 
                                             WHERE comp_no  = @v_comp_no
                                             AND wo_status != 'C'
                                             AND wo_gen_type='PMPLAN'
                                             AND wkor_date > convert(varchar(8), DATEADD(day,-1,GETDATE()), 112) )
                AND x.equip_id in (SELECT equip_id
                                            FROM taequipment
                                            WHERE comp_no = @v_comp_no
                                            AND eqctg_type='MD'
                                            AND eq_status='R')
                AND x.comp_no = @v_comp_no
            ;
            
    DECLARE @c_wkor_id       bigint

    --삭제할 pmsched_id 목록 
    DECLARE c_pmsched_id CURSOR FOR
                SELECT pmsched_id
                FROM TAPMSCHED
                WHERE comp_no = @v_comp_no
                AND pmsched_status !='C'
                AND plan_date > convert(varchar(8), DATEADD(day,-1,GETDATE()), 112)
                AND pm_id IN (SELECT pm_id 
                                        FROM TAPMEQUIP
                                        WHERE comp_no =@v_comp_no
                                        AND equip_id IN  (select equip_id
                                                                FROM taequipment
                                                                where comp_no = @v_comp_no
                                                                and eqctg_type='MD'
                                                                and eq_status='R')
                                        )
                 ;
                 
    DECLARE @c_pmsched_id       bigint
                 
    --현재 Line에 올라와 있는 금형 설비의 PM List
    DECLARE c_pmlist CURSOR FOR
                SELECT pm_id
                FROM TAPMLST
                WHERE comp_no = @v_comp_no
                AND pm_id IN (SELECT pm_id 
                                        FROM TAPMEQUIP
                                        WHERE comp_no = @v_comp_no
                                        AND equip_id IN  (SELECT equip_id
                                                                FROM taequipment
                                                                WHERE comp_no = @v_comp_no
                                                                AND eqctg_type='MD'
                                                                AND eq_status='R'
                                                                AND eqstrloc_no IN (SELECT cdusrd_no
                                                                                            FROM TACDUSRD
                                                                                            WHERE cdusrm_id = (SELECT cdusrm_id
                                                                                                                            FROM TACDUSRM
                                                                                                                            WHERE 1=1
                                                                                                                            AND comp_no = @v_comp_no
                                                                                                                            AND dir_type='EQSTRLOC_NO'
                                                                                                                            )
                                                                                              AND  cdusrd_no not in ('STOCK','IR','ER')
                                                                                              AND IS_USE = 'Y'
                                                                                              )
                                                                   )
                                        )
                 ;
                 
    DECLARE @c_pm_id       bigint
    
    OPEN c_wkor_id
    FETCH NEXT FROM c_wkor_id INTO @c_wkor_id 
    WHILE (@@FETCH_STATUS=0)
        BEGIN
            -- W/O 삭제
            DELETE FROM TAWORKORDER
            WHERE 1=1
            AND wkor_id = @c_wkor_id
            AND comp_no = @v_comp_no
            ;
            -- W/O FAIL 삭제
            DELETE FROM TAWOFAIL
            WHERE 1=1
            AND wkor_id = @c_wkor_id
            AND comp_no = @v_comp_no
            ;
            -- W/O CRAFT 삭제
            DELETE FROM TAWOCRAFT
            WHERE 1=1
            AND wkor_id = @c_wkor_id
            AND comp_no = @v_comp_no
            ;
            -- W/O PARTS 삭제
            DELETE FROM TAWOPARTS
            WHERE 1=1
            AND wkor_id = @c_wkor_id
            AND comp_no = @v_comp_no
            ;
            -- W/O POINT 삭제
            DELETE FROM TAWOPOINT
            WHERE 1=1
            AND wkor_id = @c_wkor_id
            AND comp_no = @v_comp_no
            ;
            -- W/O WOEQUIP 삭제
            DELETE FROM TAWOEQUIP
            WHERE 1=1
            AND wkor_id = @c_wkor_id
            AND comp_no = @v_comp_no
            ;
            -- W/O 긴급출고 상태 변경
            UPDATE TAPTEMGISSLIST SET
                        ptemg_task_status = 'W',
                        wkor_id = null
            WHERE 1=1
            AND wkor_id = @c_wkor_id
            AND comp_no = @v_comp_no
            ;
        
            FETCH NEXT FROM c_wkor_id INTO @c_wkor_id
        END
    CLOSE c_wkor_id
    DEALLOCATE c_wkor_id
    
    
    OPEN c_pmsched_id
    FETCH NEXT FROM c_pmsched_id INTO @c_pmsched_id 
    WHILE (@@FETCH_STATUS=0)
        BEGIN
        
            DELETE FROM TAPMSCHED
            WHERE 1=1
            AND comp_no = @v_comp_no
            AND pmsched_id = @c_pmsched_id
            ;
        
            FETCH NEXT FROM c_pmsched_id INTO @c_pmsched_id
        END
    CLOSE c_pmsched_id
    DEALLOCATE c_pmsched_id
    
    -- 금형설비의 PM 마스터의  is_active값 모두 N , Last sch Date 값 변경
    UPDATE TAPMLST
    SET is_active = 'N'
          ,last_sch_date = convert(varchar(8), DATEADD(day,-1,GETDATE()), 112)
    WHERE comp_no = @v_comp_no
    AND pm_id IN (SELECT pm_id 
                            FROM TAPMEQUIP
                            WHERE comp_no = @v_comp_no
                            AND equip_id IN  (SELECT equip_id
                                                    FROM taequipment
                                                    WHERE comp_no = @v_comp_no
                                                    AND eqctg_type='MD'
                                                    AND eq_status='R')
                            )
    ;
    
    -- 금형설비의 PM 마스터의 next sch Date 값 변경
    UPDATE TAPMLST
    SET next_sch_date =  (case when period_type = 'Y' then convert(varchar(8), DATEADD(MONTH,1*cycle*12,CAST(last_sch_date AS DATETIME)) , 112) 
                               when period_type = 'M' then convert(varchar(8), DATEADD(MONTH,1*cycle,CAST(last_sch_date AS DATETIME)) , 112) 
                               when period_type = 'W' then convert(varchar(8), DATEADD(day,1*cycle*7,CAST(last_sch_date AS DATETIME)) , 112) 
                               when period_type = 'D' then convert(varchar(8), DATEADD(day,1*cycle,CAST(last_sch_date AS DATETIME)) , 112) 
                           end )
    WHERE comp_no = @v_comp_no
    AND pm_id IN (SELECT pm_id 
                            FROM TAPMEQUIP
                            WHERE comp_no = @v_comp_no
                            AND equip_id IN  (SELECT equip_id
                                                    FROM taequipment
                                                    WHERE comp_no = @v_comp_no
                                                    AND eqctg_type='MD'
                                                    AND eq_status='R')
                            )
    ;
    
    
    OPEN c_pmlist
    FETCH NEXT FROM c_pmlist INTO @c_pm_id
    WHILE (@@FETCH_STATUS=0)
        BEGIN
        
            UPDATE TAPMLST
            SET is_active = 'Y'
            WHERE 1=1
            AND comp_no = @v_comp_no
            AND pm_id = @c_pm_id
            ;
        
            FETCH NEXT FROM c_pmlist INTO @c_pm_id
        END
    CLOSE c_pmlist
    DEALLOCATE c_pmlist
    
    
    --SP_PM_MAKE_SCHEDULE_BYALL 프로시저 실행
    exec SP_PM_MAKE_SCHEDULE_BYALL @v_comp_no, @v_user_no

    --SP_PM_MAKE_WORKORDER 프로시저 실행
    exec SP_PM_MAKE_WORKORDER @v_comp_no, @v_user_no
    
    
    

-----------------------------------------------------------------------------------------------------------------------------------------------------                 
    
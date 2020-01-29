CREATE PROCEDURE [dbo].[SP_PM_MAKE_WO_BY_SCHEDID] (
       @v_comp_no      varchar(6)
      ,@v_user_no      varchar(40)
      ,@v_pmsched_id   bigint
) 
as
    SET NOCOUNT ON;
    declare @v_count          as bigint
    DECLARE @v_wo_id          bigint
    
    select @v_wo_id = NEXT VALUE FOR sqawkor_id;

   --W/O CREATE 
     insert into taworkorder(
                 comp_no
                ,wkor_id
                ,wo_no
                ,description
                ,wo_status
                ,wo_type 
                ,pm_type
                ,pm_id
                ,pmsched_id
                ,start_date
                ,end_date
                ,dept_id
                ,emp_id
                ,wo_gen_type
                ,wkor_date
                ,wkctr_id
                ,upd_date
                ,upd_by
            )
            select 
                 a.comp_no
                ,@v_wo_id
                ,@v_wo_id
                ,'['+a.wo_type+']'+a.description
                ,'P'
                ,a.wo_type
                ,a.pm_type
                ,a.pm_id
                ,a.pmsched_id
                ,a.sched_date
                ,a.sched_date
                ,a.dept_id
                ,a.main_mng_id
                ,'PMPLAN'
                ,a.wkor_date
                ,a.wkctr_id
                ,CONVERT(varchar(8), getdate(), 112)
                ,(SELECT top 1 user_id FROM TAUSER where comp_no = a.comp_no AND user_no = @v_user_no)
            FROM ( SELECT x.comp_no comp_no
                                    ,y.description description
                                    ,y.pm_type pm_type
                                    ,y.pm_id pm_id
                                    ,y.wo_type
                                    ,x.pmsched_id pmsched_id
                                    ,y.dept_id dept_id
                                    ,x.sched_date sched_date
                                    ,x.sched_date wkor_date
                                    ,(SELECT MIN(b.main_mng_id)
                                          FROM TAPMEQUIP a, TAEQUIPMENT b
                                          WHERE a.comp_no = b.comp_no
                                          AND a.equip_id = b.equip_id
                                          AND a.pm_id = y.pm_id
                                          GROUP By a.comp_no, a.pm_id    ) AS main_mng_id
                                    ,x.sched_date plan_date
                                    ,y.wkctr_id wkctr_id
                           FROM TAPMSCHED x, TAPMLST y
                           WHERE x.comp_no  = y.comp_no
                           AND x.pm_id    = y.pm_id
                           AND x.wkor_id is null
                           AND y.is_active = 'Y'
                           AND x.pmsched_status = 'P'
                           AND x.comp_no = @v_comp_no
                           AND x.pmsched_id = @v_pmsched_id
             ) a
            ;
            --W/O Equipment CREATE
           INSERT INTO TAWOEQUIP(comp_no,woequip_id,wkor_id,equip_id)
           SELECT a.comp_no,NEXT VALUE FOR SQAWOEQUIP_ID,a.wkor_id,b.equip_id
           FROM TAWORKORDER a, TAPMEQUIP b
           WHERE a.comp_no = b.comp_no
           AND a.pm_id = b.pm_id
           AND a.wo_status = 'P'
           AND a.wo_type IN ('PMI','PMW')
           AND a.comp_no = @v_comp_no
           AND a.pmsched_id IN
                ( SELECT x.pmsched_id
                  FROM TAPMSCHED x, TAPMLST y  
                  WHERE x.comp_no  = y.comp_no
                  AND x.pm_id    = y.pm_id
                  AND x.comp_no = @v_comp_no
                  AND y.is_active = 'Y'
                  AND x.pmsched_id = @v_pmsched_id
                  )
           ;
           --UPDATE PM SCHED STATUS
           UPDATE a SET 
                 a.pmsched_status = 'S'
                ,a.wkor_id = (SELECT aa.wkor_id
                                    FROM TAWORKORDER aa
                                    WHERE a.comp_no = aa.comp_no
                                    AND a.pmsched_id = aa.pmsched_id
                                    AND a.comp_no = @v_comp_no)
           FROM TAPMSCHED a
           WHERE 1=1
               AND a.pmsched_id = @v_pmsched_id
               AND a.comp_no = @v_comp_no
               AND (SELECT is_active
                        FROM TAPMLST aa
                        WHERE a.comp_no = aa.comp_no
                        AND a.pm_id = aa.pm_id
                        AND a.comp_no = @v_comp_no) = 'Y'
              ;
          --W/O  PARTS CREATE
            INSERT INTO TAWOPARTS(
                comp_no
                ,wopart_id
                ,wkor_id
                ,wcode_id
                ,part_id
                ,part_grade
                ,use_qty
                ,unit_price
                ,tot_price
            )
            SELECT x.comp_no as comp_no
                ,NEXT VALUE FOR sqawopart_id as wopart_id
                ,x.wkor_id     as wkor_id
                ,(SELECT wcode_id 
                     FROM TADEPT 
                   WHERE dept_id = (SELECT dept_id FROM TAPMLST WHERE comp_no = x.comp_no AND pm_id = x.pm_id)
                        AND comp_no = x.comp_no) as wcode_id
                ,y.part_id     as part_id
                ,'A'           as part_grade
                ,y.use_qty   as use_qty
                ,z.last_price  as unit_price
                ,y.use_qty * z.last_price as tot_price
            FROM TAPMSCHED x, TAPMPART y, TAPARTS z
            WHERE x.comp_no = y.comp_no
            AND y.comp_no = z.comp_no
            AND x.pm_id = y.pm_id
            AND y.part_id = z.part_id
            AND x.comp_no = @v_comp_no
            AND x.pmsched_id = @v_pmsched_id
            ;
            
            -- W/O  CRAFT CREATE
            INSERT INTO TAWOCRAFT(
                    comp_no,        wocraft_id,        wkor_id,
                    emp_id,            start_date,        end_date
                )
            SELECT x.comp_no,        NEXT VALUE FOR SQAWOCRAFT_ID, x.wkor_id,
                        (SELECT MIN(b.main_mng_id)
                        FROM TAPMEQUIP a, TAEQUIPMENT b
                        WHERE a.comp_no = b.comp_no
                        AND a.equip_id = b.equip_id
                        AND a.pm_id = y.pm_id
                        AND a.comp_no = y.comp_no
                        GROUP By a.comp_no, a.pm_id    ) AS main_mng_id,
                        x.sched_date,x.sched_date
            FROM TAPMSCHED x, TAPMLST y
            WHERE x.comp_no = y.comp_no
            AND x.pm_id = y.pm_id
            AND x.comp_no = @v_comp_no
            AND x.pmsched_id = @v_pmsched_id
            AND   (SELECT MIN(b.main_mng_id)
                      FROM TAPMEQUIP a, TAEQUIPMENT b
                      WHERE a.comp_no = b.comp_no
                      AND a.equip_id = b.equip_id
                      AND a.pm_id = y.pm_id
                      AND a.comp_no = y.comp_no
                      GROUP BY a.comp_no, a.pm_id    ) is not null
            ;
            
            --W/O  POINT CREATE
            INSERT INTO TAWOPOINT(
                    comp_no,        wopoint_id,             wkor_id,
                    pm_point_id,    pm_point_rslt_status,   pm_point_rep_status
                    )
            SELECT x.comp_no,     NEXT VALUE FOR SQAWOPOINT_ID,  x.wkor_id,
                        y.pm_point_id, (select cdsysd_no from (select top 1 * from tacdsysd
                                                                     where list_type='PM_POINT_RSLT_STATUS'
                                                                     order by ord_no) a
                        ),                   'GD'
            FROM TAPMSCHED x, TAPMPOINT y
            WHERE x.comp_no = y.comp_no
            AND x.pm_id = y.pm_id
            AND x.comp_no = @v_comp_no
            AND x.pmsched_id = @v_pmsched_id
            ;
            
                        
                                    
            -- UPDATE LAST SCHED DATE
            UPDATE TAPMLST SET last_sch_date =
                   case when period_type = 'Y' then convert(varchar(8), DATEADD(MONTH,1*cycle*12,CAST(last_sch_date AS DATETIME)) , 112) 
                        when period_type = 'M' then convert(varchar(8), DATEADD(MONTH,1*cycle,CAST(last_sch_date AS DATETIME)) , 112) 
                        when period_type = 'W' then convert(varchar(8), DATEADD(day,1*cycle*7,CAST(last_sch_date AS DATETIME)) , 112) 
                        when period_type = 'D' then convert(varchar(8), DATEADD(day,1*cycle,CAST(last_sch_date AS DATETIME)) , 112) 
                        end 
            WHERE 1=1
            AND comp_no = @v_comp_no
            AND is_active = 'Y'
            AND pm_id IN (SELECT pm_id
                                    FROM TAPMSCHED
                                    WHERE comp_no = @v_comp_no
                                    AND pmsched_id = @v_pmsched_id
                                    )
            ;
            
            
            exec SP_WOMAKE_4WP_BYALL @v_comp_no
            


            

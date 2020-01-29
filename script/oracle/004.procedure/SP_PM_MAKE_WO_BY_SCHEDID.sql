CREATE OR REPLACE PROCEDURE SP_PM_MAKE_WO_BY_SCHEDID(
      v_comp_no     IN varchar2      
     ,v_user_no      IN varchar2
     ,v_pmsched_id IN number
) IS
    v_count                         number(4); 
    v_wkor_id                     number(18);
    
    -- exec SP_PM_MAKE_WO_BY_SCHEDID('120','ADMIN',0000);

     
        
BEGIN

  SELECT  SQAWKOR_ID.NEXTVAL
  INTO v_wkor_id
  FROM dual;

   --W/O CREATE 
     INSERT INTO taworkorder(
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
            SELECT 
                 a.comp_no
                ,v_wkor_id
                ,v_wkor_id
                ,'['||a.wo_type||']'||a.description
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
                ,to_char(sysdate,'yyyymmdd')
                ,(SELECT user_id FROM TAUSER WHERE comp_no = a.comp_no AND user_no = v_user_no AND ROWNUM=1)
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
                                          GROUP BY a.comp_no, a.pm_id    ) AS main_mng_id
                                    ,x.sched_date plan_date
                                    ,y.wkctr_id wkctr_id
                           FROM TAPMSCHED x, TAPMLST y
                           WHERE x.comp_no  = y.comp_no
                           AND x.pm_id    = y.pm_id
                           AND x.wkor_id IS NULL
                           AND y.is_active = 'Y'
                           AND x.pmsched_status = 'P'
                           AND x.comp_no = v_comp_no
                           AND x.pmsched_id = v_pmsched_id
             ) a
            ;
            --W/O Equipment CREATE
           INSERT INTO TAWOEQUIP(comp_no,woequip_id,wkor_id,equip_id)
           SELECT a.comp_no,SQAWOEQUIP_ID.NEXTVAL,a.wkor_id,b.equip_id
           FROM TAWORKORDER a, TAPMEQUIP b
           WHERE a.comp_no = b.comp_no
           AND a.pm_id = b.pm_id
           AND a.wo_status = 'P'
           AND a.wo_type IN ('PMI','PMW')
           AND a.comp_no = v_comp_no
           AND a.pmsched_id IN
                ( SELECT x.pmsched_id
                  FROM TAPMSCHED x, TAPMLST y  
                  WHERE x.comp_no  = y.comp_no
                  AND x.pm_id    = y.pm_id
                  AND x.comp_no = v_comp_no
                  AND y.is_active = 'Y'
                  AND x.pmsched_id = v_pmsched_id
                  )
           ;
           --UPDATE PM SCHED STATUS
           UPDATE TAPMSCHED x SET x.pmsched_status = 'S'
                                                    ,x.wkor_id = (SELECT wkor_id
                                                                        FROM TAWORKORDER
                                                                        WHERE comp_no = x.comp_no
                                                                        AND pmsched_id = x.pmsched_id
                                                                        AND comp_no = v_comp_no)
           WHERE 1=1
           AND x.pmsched_id = v_pmsched_id
           AND x.comp_no = v_comp_no
           AND (SELECT is_active
                    FROM TAPMLST
                    WHERE comp_no = x.comp_no
                    AND pm_id = x.pm_id
                    AND comp_no = v_comp_no) = 'Y'
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
            SELECT x.comp_no AS comp_no
                ,sqawopart_id.NEXTVAL AS wopart_id
                ,x.wkor_id     AS wkor_id
                ,(SELECT wcode_id 
                     FROM TADEPT 
                   WHERE dept_id = 
                                            (SELECT dept_id FROM TAPMLST WHERE comp_no = x.comp_no AND pm_id = x.pm_id)
                        AND comp_no = x.comp_no) AS wcode_id
                ,y.part_id     AS part_id
                ,'A'              AS part_grade
                ,y.use_qty   AS use_qty
                ,z.last_price  AS unit_price
                ,y.use_qty * z.last_price AS tot_price
            FROM TAPMSCHED x, TAPMPART y, TAPARTS z
            WHERE x.comp_no = y.comp_no
            AND y.comp_no = z.comp_no
            AND x.pm_id = y.pm_id
            AND y.part_id = z.part_id
            AND x.comp_no = v_comp_no
            AND x.pmsched_id = v_pmsched_id
            ;
            
            -- W/O  CRAFT CREATE
            INSERT INTO TAWOCRAFT(
                    comp_no,        wocraft_id,        wkor_id,
                    emp_id,            start_date,        end_date
                )
            SELECT x.comp_no,        SQAWOCRAFT_ID.NEXTVAL, x.wkor_id,
                        (SELECT MIN(b.main_mng_id)
                        FROM TAPMEQUIP a, TAEQUIPMENT b
                        WHERE a.comp_no = b.comp_no
                        AND a.equip_id = b.equip_id
                        AND a.pm_id = y.pm_id
                        AND a.comp_no = y.comp_no
                        GROUP BY a.comp_no, a.pm_id    ) AS main_mng_id,
                        x.sched_date,x.sched_date
            FROM TAPMSCHED x, TAPMLST y
            WHERE x.comp_no = y.comp_no
            AND x.pm_id = y.pm_id
            AND x.comp_no = v_comp_no
            AND x.pmsched_id = v_pmsched_id
            AND   (SELECT MIN(b.main_mng_id)
                      FROM TAPMEQUIP a, TAEQUIPMENT b
                      WHERE a.comp_no = b.comp_no
                      AND a.equip_id = b.equip_id
                      AND a.pm_id = y.pm_id
                      AND a.comp_no = y.comp_no
                      GROUP BY a.comp_no, a.pm_id    ) IS NOT NULL
            ;
            
            --W/O  POINT CREATE
            INSERT INTO TAWOPOINT(
                    comp_no,        wopoint_id,             wkor_id,
                    pm_point_id,    pm_point_rslt_status,   pm_point_rep_status
                    )
            SELECT x.comp_no,     SQAWOPOINT_ID.NEXTVAL,  x.wkor_id,
                        y.pm_point_id, (SELECT cdsysd_no FROM 
                        (SELECT * FROM tacdsysd
                        WHERE list_type='PM_POINT_RSLT_STATUS'
                        ORDER BY ord_no)
                        WHERE ROWNUM=1),                   'GD'
            FROM TAPMSCHED x, TAPMPOINT y
            WHERE x.comp_no = y.comp_no
            AND x.pm_id = y.pm_id
            AND x.comp_no = v_comp_no
            AND x.pmsched_id = v_pmsched_id
            ;
            
            -- UPDATE LAST SCHED DATE
            UPDATE TAPMLST a SET a.last_sch_date =
                    DECODE(a.period_type,
                                'Y',to_char(add_months(to_date(a.last_sch_date,'yyyymmdd'), 1*a.cycle*12), 'yyyymmdd'),
                                'M',to_char(add_months(to_date(a.last_sch_date,'yyyymmdd'), a.cycle*1), 'yyyymmdd'),
                                'W',to_char(to_date(a.last_sch_date,'yyyymmdd')+1*a.cycle*7, 'yyyymmdd'),
                                'D',to_char(to_date(a.last_sch_date,'yyyymmdd')+a.cycle*1, 'yyyymmdd'))
            WHERE 1=1
            AND a.comp_no = v_comp_no
            AND a.is_active = 'Y'
            AND a.pm_id IN (SELECT pm_id
                                    FROM TAPMSCHED
                                    WHERE comp_no = a.comp_no
                                    AND pmsched_id = v_pmsched_id
                                    )
            ;
            
            
            SP_WOMAKE_4WP_BYALL(v_comp_no);
            
END;
/

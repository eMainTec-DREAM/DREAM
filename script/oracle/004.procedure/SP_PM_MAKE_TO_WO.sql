CREATE OR REPLACE PROCEDURE DREAM_DEV.SP_PM_MAKE_TO_WO(
      v_comp_no     IN varchar2      
     ,v_pmsched_id IN number
) IS
    v_count                         number(4); 
    v_wo_id                        number(18);
    v_wo_type                    varchar2(20);
    v_wo_status                    varchar2(20);
    v_is_work_plan               varchar2(20);
    
    CURSOR c_pm_sched_id IS
        SELECT 
             b.pm_id
            ,(SELECT aa.wcode_id FROM TADEPT aa WHERE b.comp_no = aa.comp_no AND b.dept_id = aa.dept_id) AS wcode_id
            ,b.wo_type
            ,b.pm_type
            ,A.pmsched_id
            ,A.sched_date
            ,A.pmequip_id
            ,A.equip_id
            ,c.eqasmb_id
        FROM TAPMSCHED A, TAPMLST b, TAPMEQUIP c
        WHERE 1=1
            AND A.comp_no = b.comp_no
            AND A.pm_id = b.pm_id
            AND b.comp_no = c.comp_no
            AND b.pm_id = c.pm_id
            AND c.is_deleted = 'N'
            AND c.is_active = 'Y'
            AND b.is_active = 'Y'
            AND A.pmsched_status = 'P' -- not release work order 
            AND A.comp_no = v_comp_no
            AND A.pmsched_id = v_pmsched_id
        ;
     
        
BEGIN
        
    --    Y인경우 작업계획(TAWOPLAN)으로 발행
    --    N인경우 작업계획(TAWOPLAN), 작업실적(TAWORKORDER) 2가지 모두 발행 
    --    작업계획을 자동 확정되고 사용자는 작업실적을 사용
    SELECT COUNT(*)
        INTO v_count 
        FROM TACONFIG
        WHERE comp_no = v_comp_no
            AND NAME = 'IS_WORK_PLAN'
        ;
        
        IF v_count > 0 THEN
            SELECT NVL(value$,'N')
            INTO v_is_work_plan 
            FROM TACONFIG
            WHERE comp_no = v_comp_no
                AND NAME = 'IS_WORK_PLAN'
            ;
        ELSE
            v_is_work_plan := 'N';
        END IF;

  IF v_is_work_plan = 'N' THEN
        SELECT 'PRW' -- 작업결과작성중
            INTO v_wo_status 
        FROM dual;
    ELSE
        SELECT 'P'      -- 작업대기
            INTO v_wo_status 
        FROM dual;        
    END IF;

-- 작업계획(TAWOPLAN) 발행 
    FOR c1 IN c_pm_sched_id LOOP
            
         SELECT sqawkor_id.NEXTVAL 
         INTO v_wo_id
         FROM dual;
                     
         SELECT a.wo_type
         INTO v_wo_type
         FROM TAPMLST A
        WHERE 1=1
            AND A.comp_no = v_comp_no
            AND A.pm_id = c1.pm_id;
                         
          INSERT INTO TAWOPLAN(
             comp_no
            ,wkor_id
            ,wo_no
            ,description
            ,wo_status 
            ,wo_type
            ,pm_type
            ,wo_gen_type
            ,start_date
            ,end_date
            ,wkor_date
            ,dept_id
            ,emp_id
            ,perform
            ,pm_id
            ,pmsched_id
            ,upd_date
            ,upd_by
            ,wkctr_id
            ,plant
        )
        SELECT 
             v_comp_no  AS comp_no
            ,v_wo_id      AS wkor_id
            ,v_wo_id      AS wo_no
            ,A.description  AS description
            ,v_wo_status   AS wo_status
            ,A.wo_type      AS wo_type  -- preventmaint
            ,A.pm_type      AS pm_type
            ,'PMPLAN'        AS wo_gen_type
            ,c1.sched_date  AS start_date
            ,c1.sched_date  AS end_date
            ,c1.sched_date  AS wkor_date
            ,A.dept_id         AS dept_id
            ,A.emp_id        AS emp_id
            ,A.REMARK         AS perform
            ,c1.pm_id         AS pm_id
            ,c1.pmsched_id  AS pmsched_id
            ,TO_CHAR(SYSDATE,'yyyymmdd') AS upd_date
            ,A.emp_id          AS upd_by
            ,A.wkctr_id
            ,a.plant
        FROM TAPMLST A
        WHERE 1=1
            AND A.comp_no = v_comp_no
            AND A.pm_id = c1.pm_id
        ;
             IF v_wo_type = 'PMC' THEN
            INSERT INTO TAWOCALIB (comp_no , wkor_id , calib_type) VALUES (v_comp_no,v_wo_id,'R');
        END IF;
        -- tawoequip 
        INSERT INTO TAWOEQUIP(comp_no, woequip_id, wkor_id, equip_id, eqctg_id, description)
        SELECT comp_no, sqawoequip_id.NEXTVAL,  v_wo_id,  equip_id, eqctg_id, description 
        FROM TAEQUIPMENT
        WHERE comp_no = v_comp_no
            AND equip_id = c1.equip_id
        ;
                        
        -- parts
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
        SELECT v_comp_no AS comp_no
            ,sqawopart_id.NEXTVAL AS wopart_id
            ,v_wo_id     AS wkor_id
            ,c1.wcode_id AS wcode_id
            ,A.part_id     AS part_id
            ,'A'              AS part_grade
            ,A.use_qty   AS use_qty
            ,b.last_price  AS unit_price
            ,A.use_qty * b.last_price AS tot_price
        FROM TAPMPART A
            ,TAPARTS b
        WHERE A.comp_no = b.comp_no
            AND A.part_id = b.part_id
            AND A.comp_no = v_comp_no
            AND A.pm_id =  c1.pm_id
        ;
                        
        -- inspection point
        INSERT INTO TAWOPOINT(
            comp_no
            ,wopoint_id
            ,wkor_id
            ,pm_point_id
            ,pm_point_rslt_status
            ,pm_point_rep_status
            ,is_saved
        )
        SELECT v_comp_no AS comp_no
            ,sqawopoint_id.NEXTVAL AS wopoint_id
            ,v_wo_id     AS wkor_id
            ,A.pm_point_id AS pm_point_id
            ,(SELECT cdsysd_no FROM 
                    (SELECT * FROM TACDSYSD
                    WHERE list_type='PM_POINT_RSLT_STATUS'
                    ORDER BY ord_no)
                    WHERE ROWNUM=1)               AS pm_point_rslt_status
            ,'GD'               AS pm_point_rep_status
            ,'N'                  AS is_saved
        FROM TAPMPOINT A
        WHERE 1=1
            AND A.comp_no = v_comp_no
            AND A.pm_id =  c1.pm_id
            AND A.is_active = 'Y'
        ;
                        
            /*
         -- stardard point insert 
        INSERT INTO TAWOSTPOINT(
             comp_no
            ,wostpoint_id
            ,wkor_id
            ,stwrk_point_id
            ,st_point_rslt_status
        )
        SELECT v_comp_no AS comp_no
            ,sqawostpoint_id.NEXTVAL AS wostpoint_id
            ,v_wo_id     AS wkor_id
            ,A.stwrk_point_id AS stwrk_point_id
            ,'GD'               AS st_point_rslt_status
        FROM TASTWRKPOINT A, TASTWRKLST b
        WHERE 1=1
            AND A.comp_no = v_comp_no
            AND A.comp_no = b.comp_no
            AND A.stwrk_id = b.stwrk_id
            AND A.is_active = 'Y'
            AND b.pm_type = c1.pm_type
            AND b.is_active = 'Y'
            AND b.is_force ='Y'
        ;
        */
                        
        -- update wo_no in TAPMSCHED
        UPDATE TAPMSCHED SET
             wkor_id = v_wo_id
            ,pmsched_status =  'S' 
        WHERE comp_no = v_comp_no
            AND pmsched_id = c1.pmsched_id
        ;
                
       
     --  N인경우 작업계획(TAWOPLAN), 작업실적(TAWORKORDER) 2가지 모두 발행 
        --  사용자는 작업실적을 사용
        IF v_is_work_plan = 'N' THEN
                  
            SELECT MAX(wkor_id)
            INTO v_wo_id
            FROM TAWOPLAN;
                             
              INSERT INTO TAWORKORDER(
                 comp_no
                ,wkor_id
                ,wo_no
                ,description
                ,wo_status 
                ,wo_type
                ,pm_type
                ,wo_gen_type
                ,start_date
                ,end_date
                ,wkor_date
                ,dept_id
                ,emp_id
                ,perform
                ,pm_id
                ,pmsched_id
                ,upd_date
                ,upd_by
                ,wkctr_id
                ,plant
                ,eqasmb_id
            )
            SELECT 
                 v_comp_no  AS comp_no
                ,v_wo_id      AS wkor_id
                ,v_wo_id      AS wo_no
                ,A.description  AS description
                ,'P'                 AS wo_status
                ,A.wo_type      AS wo_type  -- preventmaint
                ,A.pm_type      AS pm_type
                ,'PMPLAN'        AS wo_gen_type
                ,c1.sched_date  AS start_date
                ,c1.sched_date  AS end_date
                ,c1.sched_date  AS wkor_date
                ,A.dept_id         AS dept_id
                ,A.emp_id        AS emp_id
                ,A.REMARK         AS perform
                ,c1.pm_id         AS pm_id
                ,c1.pmsched_id  AS pmsched_id
                ,TO_CHAR(SYSDATE,'yyyymmdd') AS upd_date
                ,A.emp_id          AS upd_by
                ,A.wkctr_id
                ,a.plant
                ,c1.eqasmb_id
            FROM TAPMLST A
            WHERE 1=1
                AND A.comp_no = v_comp_no
                AND A.pm_id = c1.pm_id
            ;
                             
            -- update wo_no in TAPMSCHED
            UPDATE TAPMSCHED SET
                 wkor_id = v_wo_id
                ,pmsched_status =  'S' 
            WHERE comp_no = v_comp_no
                AND pmsched_id = c1.pmsched_id
            ;
                            
            SP_WOMAKE_4WP_BYONE(v_comp_no, v_wo_id);
                
        END IF;
    
    
    
    
        END LOOP;
     
END;
/

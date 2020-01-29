CREATE OR REPLACE PROCEDURE SP_KPI_MAKE_TAPTMONTHLYSTOCK(
      v_comp_no     IN varchar2      
     ,v_user_no      IN varchar2
) IS
    v_count                                 number(4); 
    
    -- exec SP_KPI_MAKE_TAPTMONTHLYSTOCK('100','ADMIN');
    
    CURSOR c_base IS
           SELECT 
                a.comp_no
                ,a.wcode_id
                ,to_char(sysdate,'yyyymm') AS yyyymm
                ,a.part_id
                ,a.part_grade
                ,a.result_tot AS base_qty
                ,a.result_unit_price AS base_unit_price
                ,a.result_tot_price AS base_tot_price
            FROM TAPTMONTHLYSTOCK a
            WHERE 1=1
                AND a.comp_no = v_comp_no
                AND a.yyyymm = to_char(add_months(sysdate,-1),'yyyymm')  -- 한달전
            ;
            
    CURSOR c_rec_part IS
           SELECT 
                a.comp_no
                ,a.wcode_id
                ,substr(a.rec_date,1,6) AS yyyymm
                ,a.part_id
                ,a.part_grade
                ,sum(CASE WHEN a.ptrec_mode ='C' THEN a.rec_qty 
                        WHEN a.ptrec_mode = 'R' THEN a.rec_qty * -1
                        END) AS rec_qty
                ,sum(CASE WHEN a.ptrec_mode ='C' THEN a.tot_price 
                        WHEN a.ptrec_mode = 'R' THEN a.tot_price * -1
                        END) AS tot_price
            FROM taptrechist a
            WHERE 1=1
                AND a.comp_no = v_comp_no
                AND a.ptrec_type = 'PRREC'
                AND a.rec_date >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymmdd')  -- 한달전 1일 부터 재 계산 처리...
                AND a.rec_date <= to_char(sysdate,'yyyymmdd')
            GROUP BY a.comp_no,a.wcode_id,substr(a.rec_date,1,6),a.part_id,a.part_grade   
            ;
     
    CURSOR c_used_part IS
           SELECT 
                a.comp_no
                ,a.wcode_id
                ,substr(a.iss_date,1,6) AS yyyymm
                ,a.part_id
                ,a.part_grade
                ,sum(CASE WHEN a.ptiss_mode ='C' THEN a.iss_qty 
                        WHEN a.ptiss_mode = 'R' THEN a.iss_qty * -1
                        END) AS iss_qty
                ,sum(CASE WHEN a.ptiss_mode ='C' THEN a.tot_price 
                        WHEN a.ptiss_mode = 'R' THEN a.tot_price * -1
                        END) AS tot_price
            FROM taptisshist a
            WHERE 1=1
                AND a.comp_no = v_comp_no
                AND a.ptiss_type = 'WOISS'
                AND a.iss_date >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymmdd')  -- 한달전 1일 부터 재 계산 처리...
                AND a.iss_date <= to_char(sysdate,'yyyymmdd')
            GROUP BY a.comp_no,a.wcode_id,substr(a.iss_date,1,6),a.part_id,a.part_grade   
            ;
            
       
    
        
BEGIN

   UPDATE taptstock a set 
        a.unit_price = (SELECT last_price FROM taparts
                              WHERE comp_no = a.comp_no
                              AND part_id = a.part_id)
   WHERE a.comp_no = v_comp_no
   ;

   UPDATE taptmonthlystock a set 
       a.rec_qty = 0
       ,a.rec_tot_price = 0
       ,a.issue_qty = 0
       ,a.issue_tot_price = 0
   WHERE 1=1
       AND a.yyyymm >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymm') 
       AND a.yyyymm <= to_char(sysdate,'yyyymm')
   ;
   
   UPDATE taptmonthlystock a set 
       a.base_qty = 0
       ,a.base_unit_price = 0
       ,a.base_tot_price = 0
       ,a.result_tot = 0
       ,a.result_unit_price = 0
       ,a.result_tot_price = 0
   WHERE 1=1
       AND a.yyyymm = to_char(sysdate,'yyyymm')
   ;


    FOR c1 IN c_base LOOP
    
       SELECT count(*)
       INTO v_count
       FROM taptmonthlystock
       WHERE comp_no = c1.comp_no
           AND wcode_id = c1.wcode_id
           AND yyyymm = c1.yyyymm
           AND part_id = c1.part_id
           AND part_grade = c1.part_grade
       ;
       
       IF v_count > 0 THEN
           UPDATE taptmonthlystock  a set
                     a.base_qty = c1.base_qty
                    ,a.base_unit_price = c1.base_unit_price
                    ,a.base_tot_price = c1.base_tot_price
            WHERE a.comp_no = c1.comp_no
               AND a.wcode_id = c1.wcode_id
               AND a.yyyymm = c1.yyyymm
               AND a.part_id = c1.part_id
               AND a.part_grade = c1.part_grade
                ;
       ELSE
           INSERT INTO taptmonthlystock( comp_no, wcode_id, yyyymm, part_id, part_grade, base_qty, base_unit_price, base_tot_price
           ) VALUES (
               c1.comp_no, c1.wcode_id, c1.yyyymm, c1.part_id, c1.part_grade, c1.base_qty,  c1.base_unit_price, c1.base_tot_price
           );
       END IF;
       
   END LOOP;
    
   FOR c1 IN c_rec_part LOOP
    
       SELECT count(*)
       INTO v_count
       FROM taptmonthlystock
       WHERE comp_no = c1.comp_no
           AND wcode_id = c1.wcode_id
           AND yyyymm = c1.yyyymm
           AND part_id = c1.part_id
           AND part_grade = c1.part_grade
       ;
       
       IF v_count > 0 THEN
           UPDATE taptmonthlystock  a set
                     a.rec_qty = c1.rec_qty
                    ,a.rec_tot_price = c1.tot_price
            WHERE a.comp_no = c1.comp_no
               AND a.wcode_id = c1.wcode_id
               AND a.yyyymm = c1.yyyymm
               AND a.part_id = c1.part_id
               AND a.part_grade = c1.part_grade
                ;
       ELSE
           INSERT INTO taptmonthlystock( comp_no, wcode_id, yyyymm, part_id, part_grade, rec_qty, rec_tot_price
           ) VALUES (
               c1.comp_no, c1.wcode_id, c1.yyyymm, c1.part_id, c1.part_grade, c1.rec_qty,  c1.tot_price
           );
       END IF;
       
   END LOOP;
        
   FOR c1 IN c_used_part LOOP
   
       SELECT count(*)
       INTO v_count
       FROM taptmonthlystock
       WHERE comp_no = c1.comp_no
           AND wcode_id = c1.wcode_id
           AND yyyymm = c1.yyyymm
           AND part_id = c1.part_id
           AND part_grade = c1.part_grade
       ;
       
       IF v_count > 0 THEN
           UPDATE taptmonthlystock  a set
                     a.issue_qty = c1.iss_qty
                    ,a.issue_tot_price = c1.tot_price
            WHERE a.comp_no = c1.comp_no
               AND a.wcode_id = c1.wcode_id
               AND a.yyyymm = c1.yyyymm
               AND a.part_id = c1.part_id
               AND a.part_grade = c1.part_grade
                ;
       ELSE
           INSERT INTO taptmonthlystock( comp_no, wcode_id, yyyymm, part_id, part_grade, issue_qty, issue_tot_price
           ) VALUES (
               c1.comp_no, c1.wcode_id, c1.yyyymm, c1.part_id, c1.part_grade, c1.iss_qty,  c1.tot_price
           );
       END IF;
       
   END LOOP;
   
   
   FOR c1 IN (SELECT 
                        a.comp_no
                        ,a.wcode_id
                        ,to_char(sysdate,'yyyymm') AS yyyymm
                        ,a.part_id
                        ,a.part_grade
                        ,a.stock_qty
                        ,nvl(a.unit_price,0) AS unit_price
                        ,a.stock_qty * nvl(a.unit_price,0) AS tot_price
                    FROM taptstock a
                    WHERE 1=1
                        AND a.comp_no = v_comp_no
                        AND a.stock_qty > 0) 
   LOOP
    
       SELECT count(*)
       INTO v_count
       FROM taptmonthlystock
       WHERE comp_no = c1.comp_no
           AND wcode_id = c1.wcode_id
           AND yyyymm = c1.yyyymm
           AND part_id = c1.part_id
           AND part_grade = c1.part_grade
       ;
       
       IF v_count > 0 THEN
           UPDATE taptmonthlystock  a set
                     a.result_tot = c1.stock_qty
                    ,a.result_unit_price = c1.unit_price
                    ,a.result_tot_price = c1.tot_price
            WHERE a.comp_no = c1.comp_no
               AND a.wcode_id = c1.wcode_id
               AND a.yyyymm = c1.yyyymm
               AND a.part_id = c1.part_id
               AND a.part_grade = c1.part_grade
                ;
       ELSE
           INSERT INTO taptmonthlystock( comp_no, wcode_id, yyyymm, part_id, part_grade, result_tot, result_unit_price, result_tot_price
           ) VALUES (
               c1.comp_no, c1.wcode_id, c1.yyyymm, c1.part_id, c1.part_grade, c1.stock_qty,  c1.unit_price, c1.tot_price
           );
       END IF;
       
   END LOOP;
   
   
   
   
   
   
    UPDATE TABATPGM set 
         exe_by = (SELECT user_id 
                        FROM tauser 
                        WHERE comp_no = v_comp_no 
                            AND user_no = v_user_no 
                            AND ROWNUM = 1
                       )
        ,last_exe_date = to_char(sysdate,'yyyymmdd')
        ,last_exe_time = to_char(sysdate,'yyyymmddhh24miss')
    WHERE comp_no = v_comp_no
        AND batpgm_no = 'TAPTMONTHLYSTOCK'
    ;
    
    
    
    
END;
/
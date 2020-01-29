CREATE OR REPLACE PROCEDURE SP_IF_UPD_TAPTSTOCK    (
      v_comp_no     IN varchar2
     ,v_user_no      IN varchar2
) IS

   v_count number(4);
   v_dept_id number(18);
   v_emp_id number(18);
      
    CURSOR c_warehouse IS
     SELECT werks, lgort
       FROM TXERPSTKDAY
       WHERE rec_date = TO_CHAR(SYSDATE,'yyyymmdd')
       GROUP BY werks,lgort
    ;
    
    CURSOR c_stock IS
     SELECT 
                erpstkday_id,
                rec_date, --일자
                bukrs,  --회사코드
                werks,  -- 플랜트
                lgort,  -- 저장위치(창고)
                matnr,  --자재번호
                maktx,  --품명
                groes,   --크기/치수
                first_value(labst) over (partition by trim(matnr) order by erpstkday_id desc) labst, 
            --    labst,  --가용수량
                meins,  --단위
                verpr,  -- 단가(이동평균단가)
                peinh,  --가격단위 
                y.part_id,
                (SELECT a.wcode_id
                 FROM    TAWAREHOUSE a
                 WHERE a.out_plant = x.werks
                     AND a.out_wcode = x.lgort) wcode_id
       FROM TXERPSTKDAY x , TAPARTS y
       WHERE x.rec_date = TO_CHAR(SYSDATE,'yyyymmdd')
           AND TRIM(x.matnr) = TRIM(y.part_no)
       order by TRIM(x.matnr)
    ;
   
                                    
BEGIN

   FOR c0 IN c_warehouse LOOP
   
      UPDATE TAPTSTOCK a SET a.stock_qty = 0
       WHERE a.comp_no = v_comp_no
          AND a.wcode_id = (SELECT aa.wcode_id FROM TAWAREHOUSE aa WHERE aa.out_plant = c0.werks AND aa.out_wcode = c0.lgort)
          AND a.part_grade = 'A'
       ;
       
               
    END LOOP;


   
  
    
    FOR c1 IN c_stock LOOP

      SELECT COUNT(*)
       INTO v_count
       FROM TAPTSTOCK x
       WHERE 1=1
           AND x.comp_no = v_comp_no
           AND x.part_id = c1.part_id
           AND x.wcode_id  = c1.wcode_id
           AND part_grade = 'A'
       ;
       
       IF v_count = 0 THEN
          
          INSERT INTO TAPTSTOCK(
              comp_no
              ,wcode_id
              ,part_id
              ,part_grade
              ,stock_qty
              ,bin_no
              ,unit_price
          )VALUES(
              v_comp_no
              ,c1.wcode_id
              ,c1.part_id --
              ,'A' --  
              ,c1.labst --가용수량
              ,''  -- 저장위치 ?
              ,c1.verpr  -- 단가 
          );

       ELSE
          
             UPDATE  TAPTSTOCK 
                  SET    stock_qty = c1.labst
                            ,unit_price = c1.verpr
            WHERE 1=1
               AND comp_no = v_comp_no
               AND part_id = c1.part_id
               AND wcode_id  = c1.wcode_id
               AND part_grade = 'A'
           ;
           
       END IF;
               
    END LOOP;
    
    UPDATE TABATPGM SET 
         exe_by = (SELECT user_id 
                        FROM TAUSER 
                        WHERE comp_no = v_comp_no 
                            AND user_no = v_user_no 
                            AND ROWNUM = 1
                       )
        ,last_exe_date = TO_CHAR(SYSDATE,'yyyymmdd')
        ,last_exe_time = TO_CHAR(SYSDATE,'yyyymmddhh24miss')
    WHERE comp_no = v_comp_no
        AND batpgm_no = 'TAPTSTOCK'
    ;
    
END;
/

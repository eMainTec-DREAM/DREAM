CREATE OR REPLACE PROCEDURE SP_MUP_TYCATEG_NO (
    v_comp_no varchar2 DEFAULT '100'
) IS

   v_count number(4);
   v_eqctg_id number(18);
   v_p_eqctg_id number(18);
   v_lvl  number(4);
   v_eqctg_no varchar2(20);
   v_full_desc varchar2(512);
   v_asmb_desc varchar2(512);
   v_ord_no number(4);
   
   -- exec SP_MUP_TYCATEG_NO('120')
      
    
     
    CURSOR c_e1_data IS
                SELECT 
                     TRIM(tp_l) AS e1_desc
                FROM TYEQUIPMENT a
                WHERE 1=1
                    AND TRIM(tp_l) IS NOT NULL
                GROUP BY TRIM(tp_l) 
                ORDER BY TRIM(tp_l) 
                ;
                
    CURSOR c_e2_data IS
     SELECT 
                     TRIM(tp_l) AS e1_desc
                     ,TRIM(tp_2) AS e2_desc
                FROM TYEQUIPMENT a
                WHERE 1=1
                    AND TRIM(tp_2) IS NOT NULL
                GROUP BY TRIM(tp_l) ,TRIM(tp_2) 
                ORDER BY TRIM(tp_l)  ,TRIM(tp_2) 
                ;
                
       CURSOR c_asmb_data(p_e4_desc varchar2) IS
            WITH DATA AS ( 
                  SELECT p_e4_desc str FROM dual
               )
               SELECT TRIM(REGEXP_SUBSTR(str, '[^,]+', 1, LEVEL)) str
               FROM DATA
               CONNECT BY INSTR(str, ',', 1, LEVEL - 1) > 0
               ;               
                                
BEGIN

    
    
    FOR c1 IN c_e1_data LOOP

                   SELECT sqaeqctg_id.NEXTVAL
                   INTO v_eqctg_id
                   FROM dual;
                   
                   SELECT TRIM(TO_CHAR(NVL(TO_NUMBER(MAX(eqctg_no)),10000) + 1,'00000'))
                   INTO v_eqctg_no
                   FROM TAEQCTG
                   WHERE TRANSLATE(eqctg_no,'x0123456789','x') IS NULL 
                   ;
                   
                   v_p_eqctg_id := 0;
                    v_lvl := 1;
                    v_full_desc := c1.e1_desc;
                   
                    --
               SELECT COUNT(*)
               INTO v_count
               FROM TAEQCTG
               WHERE comp_no = v_comp_no
                   AND full_desc = v_full_desc
               ;
               
               IF v_count = 0 THEN

                          INSERT INTO TAEQCTG(
                                                 comp_no,eqctg_id, eqctg_no, description
                                                ,full_desc, p_eqctg_id, lvl
                                                ,REMARK, ord_no, is_use
                         )VALUES(
                                               v_comp_no, v_eqctg_id, v_eqctg_no, c1.e1_desc
                                               ,v_full_desc, v_p_eqctg_id, v_lvl
                                               ,'', v_eqctg_no, 'Y'
                        );
                   
                 END IF;
              
                   
    END LOOP;
    
    
    
     FOR c1 IN c_e2_data LOOP

                   SELECT sqaeqctg_id.NEXTVAL
                   INTO v_eqctg_id
                   FROM dual;
                   
                   SELECT TRIM(TO_CHAR(NVL(TO_NUMBER(MAX(eqctg_no)),10000) + 1,'00000'))
                   INTO v_eqctg_no
                   FROM TAEQCTG
                   WHERE TRANSLATE(eqctg_no,'x0123456789','x') IS NULL 
                   ;
                   
                   SELECT eqctg_id
                   INTO v_p_eqctg_id
                   FROM TAEQCTG
                   WHERE comp_no = v_comp_no
                       AND description = c1.e1_desc
                       AND ROWNUM = 1;
                   
                    v_lvl := 2;
                    v_full_desc := c1.e1_desc || ' > ' || c1.e2_desc;
                   
                    SELECT COUNT(*)
               INTO v_count
               FROM TAEQCTG
               WHERE comp_no = v_comp_no
                   AND full_desc = v_full_desc
               ;
               
                 IF v_count = 0 THEN

                    INSERT INTO TAEQCTG(
                                                 comp_no,eqctg_id, eqctg_no, description
                                                ,full_desc, p_eqctg_id, lvl
                                                ,REMARK, ord_no, is_use
                   )VALUES(
                                               v_comp_no, v_eqctg_id, v_eqctg_no, c1.e2_desc
                                               ,v_full_desc, v_p_eqctg_id, v_lvl
                                               ,'', v_eqctg_no, 'Y'
                   );
                 END IF;
                 
                   
                   
                   SELECT TRIM(asmb_no)
                   INTO v_asmb_desc
                   FROM TYEQUIPMENT
                   WHERE TRIM(tp_l) = c1.e1_desc
                       AND TRIM(tp_2) = c1.e2_desc
                       AND ROWNUM = 1;
                       
                   IF v_asmb_desc IS NOT NULL THEN
                       v_ord_no := 0;
                       FOR c2 IN c_asmb_data(v_asmb_desc) LOOP
                           v_ord_no := v_ord_no + 1;
                           
                           INSERT INTO TAEQCTGASMB(comp_no, eq_ctg_asmb_id, eqctg_id, description, ord_no, is_use,REMARK)
                           VALUES(v_comp_no, sqaeq_ctg_asmb_id.NEXTVAL, v_eqctg_id, c2.str, v_ord_no, 'Y', c2.str);

                       END LOOP;
                   
                   END IF;
                   
                   
    END LOOP;
    
    
END;
/

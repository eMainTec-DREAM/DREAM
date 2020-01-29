CREATE OR REPLACE PROCEDURE SP_MUP_TYEQCTG_NO (
    v_comp_no varchar2 DEFAULT '100'
) IS

-- truncate table TAEQASMB
-- exec SP_MUP_TYEQCTG_NO('120')

   v_count number(4);
   v_equip_id number(18);
   
   v_eqctg_id number(18);
   v_p_eqctg_id number(18);
   v_lvl  number(4);
   v_eqctg_no varchar2(20);
   v_full_desc varchar2(512);
   v_asmb_desc varchar2(512);
   v_ord_no number(4);

                
      CURSOR c_e1_data IS
            SELECT 
                     sn
                     ,trim(excel_no) as excel_no 
                     ,trim(loc_line) as loc_line 
                     ,trim(loc_line_group) as loc_line_group 
                     ,trim(item_no) as item_no 
                     ,TRIM(tp_l) AS e1_desc
                     ,TRIM(tp_2) AS e2_desc
                FROM TYEQUIPMENT a
                WHERE 1=1
                ORDER BY sn
                ; 
                
                                
BEGIN


            UPDATE TAEQUIPMENT SET eqctg_id = NULL
            WHERE comp_no = v_comp_no
            ;
    
    
    
    FOR c1 IN c_e1_data LOOP
    
                   SELECT COUNT(*)
                   INTO v_count
                   FROM TAEQCTG
                   WHERE comp_no = v_comp_no
                       AND full_desc = c1.e1_desc || ' > ' || c1.e2_desc
                       AND ROWNUM = 1;
                       
                   IF v_count > 0 THEN
                   
                       SELECT eqctg_id
                       INTO v_eqctg_id
                       FROM TAEQCTG
                       WHERE comp_no = v_comp_no
                           AND full_desc = c1.e1_desc || ' > ' || c1.e2_desc
                           AND ROWNUM = 1; 
                           
                           
                   
                       SELECT COUNT(*)
                       INTO v_count
                       FROM TAEQUIPMENT
                       WHERE comp_no = v_comp_no
                           AND excel_no = c1.excel_no
                           AND ROWNUM = 1;
                           
                       
                       IF v_count > 0 THEN
                       
                               SELECT equip_id
                               INTO v_equip_id
                               FROM TAEQUIPMENT
                               WHERE comp_no = v_comp_no
                                   AND excel_no = c1.excel_no
                                   AND ROWNUM = 1;
                       
                           -- ???? ??
                           UPDATE TAEQUIPMENT SET 
                               eqctg_id = v_eqctg_id
                           WHERE comp_no = v_comp_no
                               AND equip_id = v_equip_id
                            ;
                            
                            
                            -- ??? ???? ??
                           /* 
                            INSERT INTO TAEQASMB(comp_no, eqasmb_id, equip_id, eq_ctg_asmb_id, eqctg_id, description, is_eqtype_asmb, ord_no, is_use)
                            SELECT  a.comp_no, sqaeqasmb_id.NEXTVAL, v_equip_id, b.eq_ctg_asmb_id,b.eqctg_id, b.description, 'Y' , b.ord_no, b.is_use
                            FROM TAEQCTG a
                                 ,TAEQCTGASMB b
                            WHERE a.comp_no = b.comp_no
                                AND a.eqctg_id = b.eqctg_id
                                AND a.eqctg_id = v_eqctg_id
                            ;
                           */
                            
                            
                   END IF;
               END IF;
                            
    END LOOP;
    
    
    
    
    
END;
/

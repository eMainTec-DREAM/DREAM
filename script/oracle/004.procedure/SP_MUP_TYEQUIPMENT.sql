CREATE OR REPLACE PROCEDURE "SP_MUP_TYEQUIPMENT" (
    v_comp_no varchar2 DEFAULT '120'
) IS

   v_count     number(4);
   v_item_no varchar2(20);
   v_eqloc_id number(18);
   v_full_desc varchar2(512);
   v_eqctg_id number(18);
   v_asmb_no varchar2(512);
   v_equip_id number(18);
   v_dept_id number(18);
   v_is_law_eq varchar2(20);
   v_plf_type varchar2(20);
   v_mainmng_id  number(18);
   v_submng_id number(18);
   v_asset_id number(18);
   v_ord_no number(18);

   v_p_eqloc_id number(18);
   v_lvl_type  varchar2(10);
   v_lvl  number(4);
   v_eqloc_no varchar2(20);
   
   v_ctctr_id number(18);
   v_ctctr_no varchar2(20);
   
   v_remark varchar2(512);
   v_dept_no  varchar2(10);

-- select excel_no from TYEQUIPMENT group by excel_no having count(*) > 1   
-- truncate table TYEQUIPMENT   
-- truncate table TAEQUIPMENT
-- truncate table TAEQLOC
-- truncate table TACTCTR
-- truncate table TAEQASSET
-- truncate table TAASSET
-- truncate table TAEQASMB
-- truncate table TAEQCTG



-- exec SP_MUP_TYEQUIPMENT('120')
-- exec SP_MUP_TYCATEG_NO('120')
-- exec SP_MUP_TYEQCTG_NO('120')
-- exec SP_EQ_UPD_EQLOC_ALL('120')
-- exec SP_EQ_UPD_TAEQCTG_ALL('120')


   CURSOR c_cp_data IS 
                 SELECT 
                          cost_cd
                        , TRIM(cost_nm) AS cost_nm   
                FROM TYEQUIPMENT a
                WHERE 1=1
                    AND cost_cd IS NOT NULL
                GROUP BY cost_cd, TRIM(cost_nm)
                ORDER BY cost_cd, TRIM(cost_nm)
                ;

    CURSOR c_l1_data IS
         SELECT 
                  TRIM(loc_line) AS l1_desc    
        FROM TYEQUIPMENT a
        WHERE 1=1
           and  TRIM(loc_line) is not null
        GROUP BY  TRIM(loc_line)
        ;
                
    CURSOR c_l2_data IS
      SELECT 
                  TRIM(loc_line) AS l1_desc
                  ,TRIM(loc_line_group) AS l2_desc
        FROM TYEQUIPMENT a
        WHERE 1=1
        GROUP BY  TRIM(loc_line),TRIM(loc_line_group)
        ;
        
    CURSOR c_excel_data IS 
     SELECT 
                         sn         
                        ,trim(excel_no) as excel_no 
                        ,trim(item_no) as item_no   
                        ,trim(description) as description 
                        ,trim(tp_l) as tp_l 
                        ,trim(tp_2) as tp_2 
                        ,trim(asmb_no) as  asmb_no
                        ,trim(maker) as maker
                        ,trim(model_no) as model_no
                        ,trim(serial_no) as serial_no
                        ,trim(comp_no) as comp_no
                        ,trim(location) as location
                        ,trim(loc_plant) as loc_plant
                        ,trim(loc_line)    as l1_desc
                        ,trim(loc_line_group)  as l2_desc
                        ,trim(isuse) as isuse
                        ,trim(critical) as critical
                        ,trim(cost_cd) as cost_cd
                        ,trim(cost_nm) as cost_nm
                        ,REPLACE(REPLACE(REPLACE(trim(setup_date),'-',''),'.',''),' ','') AS setup_date
                        ,trim(asset_no) as asset_no
                        ,trim(asset_name) as asset_name
                        ,trim(as_vendor_name) as as_vendor_name
                        ,trim(as_tel_no) as as_tel_no
                FROM TYEQUIPMENT a
                WHERE 1=1
                ORDER BY  excel_no, sn
                ;
                
      CURSOR c_asmb_data(p_eqasmb varchar2) IS
            WITH DATA AS ( 
                  SELECT p_eqasmb str FROM dual
               )
               SELECT TRIM(REGEXP_SUBSTR(str, '[^,]+', 1, LEVEL)) str
               FROM DATA
               CONNECT BY INSTR(str, ',', 1, LEVEL - 1) > 0
               ;               
                                
BEGIN

  
  v_ord_no := 0;
  v_dept_no := 'D102'; -- you have to change, 
  
    FOR c1 IN c_cp_data LOOP
  
            SELECT COUNT(*)
            INTO v_count
            FROM TACTCTR
            WHERE comp_no = v_comp_no
                AND ctctr_no = c1.cost_cd
            ;
            
            IF v_count > 0 THEN
                 UPDATE TACTCTR  SET description = c1.cost_nm
                 WHERE comp_no = v_comp_no
                    AND ctctr_no = c1.cost_cd
                 ;
            ELSE
                 SELECT sqactctr_id.NEXTVAL
                 INTO v_ctctr_id
                 FROM dual;
                 
                                 
                 v_ord_no := v_ord_no + 1;
                   
                INSERT INTO TACTCTR(comp_no, ctctr_id, ctctr_no, description, ord_no, is_use)
                VALUES(v_comp_no, v_ctctr_id, c1.cost_cd, c1.cost_nm, v_ord_no, 'Y');
                
            END IF;
  
  END LOOP;


   FOR c1 IN c_l1_data LOOP
    
                   SELECT sqaeqloc_id.NEXTVAL
                   INTO v_eqloc_id
                   FROM dual;
                   
                   SELECT TRIM(TO_CHAR(NVL(TO_NUMBER(MAX(eqloc_no)),10000) + 1,'00000'))
                   INTO v_eqloc_no
                   FROM TAEQLOC
                   WHERE TRANSLATE(eqloc_no,'x0123456789','x') IS NULL 
                   ;
                   
                   v_p_eqloc_id := 0;
                   v_lvl_type  := 'LVL_1';
                    v_lvl := 1;
                    v_full_desc := c1.l1_desc;
                   
                   INSERT INTO TAEQLOC(
                                                 comp_no,eqloc_id, eqloc_no, description
                                                ,full_desc, p_eqloc_id, eqloc_lvl_type, lvl
                                                ,REMARK, ord_no, is_use, plant
                   )VALUES(
                                               v_comp_no, v_eqloc_id, v_eqloc_no, c1.l1_desc
                                               ,v_full_desc, v_p_eqloc_id, v_lvl_type, v_lvl
                                               ,'', v_eqloc_no, 'Y', 'SK'
                   );
                   
                   
    END LOOP;
    
    
    
     FOR c1 IN c_l2_data LOOP
    
                   if c1.l2_desc is not null then
                       
                       SELECT sqaeqloc_id.NEXTVAL
                       INTO v_eqloc_id
                       FROM dual;
                       
                       SELECT TRIM(TO_CHAR(NVL(TO_NUMBER(MAX(eqloc_no)),10000) + 1,'00000'))
                       INTO v_eqloc_no
                       FROM TAEQLOC
                       WHERE TRANSLATE(eqloc_no,'x0123456789','x') IS NULL 
                       ;
                       
                       SELECT eqloc_id
                       INTO v_p_eqloc_id
                       FROM TAEQLOC
                       WHERE comp_no = v_comp_no
                           AND description = c1.l1_desc
                           AND ROWNUM = 1;
                       
                       v_lvl_type  := 'LVL_2';
                        v_lvl := 2;
                        v_full_desc := c1.l1_desc || ' > ' || c1.l2_desc;
                       
                       INSERT INTO TAEQLOC(
                                                     comp_no,eqloc_id, eqloc_no, description
                                                    ,full_desc, p_eqloc_id, eqloc_lvl_type, lvl
                                                    ,REMARK, ord_no, is_use, plant
                       )VALUES(
                                                   v_comp_no, v_eqloc_id, v_eqloc_no, c1.l2_desc
                                                   ,v_full_desc, v_p_eqloc_id, v_lvl_type, v_lvl
                                                   ,'', v_eqloc_no, 'Y', 'SK'
                       );
                   
                   end if;
                   
    END LOOP;
    
    FOR c1 IN c_excel_data LOOP
    
    
            select count(*)
             into v_count
             from tadept
             where comp_no = v_comp_no
                 and dept_no = v_dept_no
             ;
             
             if v_count > 0 then
                 select dept_id
                 into v_dept_id
                 from tadept
                 where comp_no = v_comp_no
                     and dept_no = v_dept_no
                 ;
             else
                v_dept_id := null;
             end if;
             
    
    
    
              IF c1.l1_desc IS NOT NULL AND c1.l2_desc IS NOT NULL THEN
                  v_full_desc := c1.l1_desc || ' > ' ||  c1.l2_desc;
              ELSE
                  v_full_desc := c1.l1_desc ;
              END IF;
              
              SELECT COUNT(*)
              INTO v_count
              FROM TAEQLOC 
              WHERE full_desc = v_full_desc
                  AND ROWNUM = 1;
              
              IF v_count > 0 THEN
                  SELECT eqloc_id
                  INTO v_eqloc_id
                  FROM TAEQLOC 
                  WHERE full_desc = v_full_desc
                      AND ROWNUM = 1;
              else
                 v_eqloc_id := null;
              END IF;
              
              
               SELECT COUNT(*)
               INTO v_count
               FROM TACTCTR
               WHERE comp_no = v_comp_no
                   AND ctctr_no = c1.cost_cd
               ;
               
               IF v_count > 0 THEN
                   SELECT ctctr_id
                   INTO v_ctctr_id
                   FROM TACTCTR
                   WHERE comp_no = v_comp_no
                       AND ctctr_no = c1.cost_cd
                   ;
                   
                   UPDATE TAEQLOC SET ctctr_id = v_ctctr_id
                   WHERE comp_no = v_comp_no
                       AND eqloc_id = v_eqloc_id
                       
                   ;
               END IF;



              SELECT TRIM(TO_CHAR(NVL(TO_NUMBER(MAX(TO_NUMBER(item_no))),10000) + 1,'00000'))
               INTO v_item_no
               FROM TAEQUIPMENT
               WHERE comp_no = v_comp_no
                  AND  TRANSLATE(item_no,'x0123456789','x') IS NULL
               ;
                       
               SELECT COUNT(*)
               INTO v_count
               FROM TAEQUIPMENT
               WHERE comp_no = v_comp_no
                   AND excel_no = c1.excel_no
               ;
               
               IF v_count > 0 THEN
                    SELECT equip_id
                    INTO v_equip_id
                    FROM TAEQUIPMENT
                    WHERE comp_no = v_comp_no
                        AND excel_no = c1.excel_no
                        AND ROWNUM = 1
                    ;
               ELSE 
                   SELECT sqaequip_id.NEXTVAL
                   INTO v_equip_id
                    FROM dual
                    ;
               END IF;
               
               IF v_count > 0 THEN 
                   
               
                   UPDATE TAEQUIPMENT SET
                        description = trim(c1.description)
                       ,eqloc_id = v_eqloc_id
                       ,eqctg_id = v_eqctg_id
                       ,maker = c1.maker
                       ,model_no = c1.model_no
                       ,serial_no = c1.serial_no
                       ,eq_status = decode(c1.isuse, 'Y','R','S')
                       ,eq_grade = c1.critical
                       ,setup_date = c1.setup_date
                       ,as_vendor = c1.as_vendor_name
                       ,as_phone = c1.as_tel_no
                       ,old_eq_no = c1.item_no
                       ,ord_no = c1.sn
                       ,dept_id = v_dept_id
                   WHERE  comp_no = v_comp_no
                   AND excel_no = c1.excel_no
                   ;
                   
               ELSE
                   INSERT INTO TAEQUIPMENT(
                        comp_no, equip_id,excel_no, item_no, description ,eqloc_id
                       ,eqctg_id,maker,model_no,serial_no,eq_status
                       ,eq_grade,setup_date,as_vendor,as_phone
                       ,old_eq_no,ord_no, dept_id
                   ) VALUES(
                       v_comp_no, v_equip_id, c1.excel_no, v_item_no
                       , trim(c1.description),v_eqloc_id
                       ,v_eqctg_id,c1.maker,c1.model_no,c1.serial_no,decode(c1.isuse, 'Y','R','S')
                       ,c1.critical,c1.setup_date,c1.as_vendor_name,c1.as_tel_no
                       ,c1.item_no,c1.sn, v_dept_id
                   );
                   
               END IF;
               
               
               
               
               if c1.asset_no is not null then 
                       SELECT COUNT(*)
                       INTO v_count
                       FROM TAASSET
                       WHERE 1=1
                           AND comp_no = v_comp_no
                           AND asset_no = c1.asset_no
                       ;
                       
                       IF v_count > 0 THEN
                               SELECT asset_id
                               INTO v_asset_id
                               FROM TAASSET
                               WHERE 1=1
                                   AND comp_no = v_comp_no
                                   AND asset_no = c1.asset_no
                               ;
                       else
                       
                           select sqaasset_id.nextval
                           into v_asset_id
                           from dual;
                           
                            insert into TAASSET(comp_no, asset_id, asset_no,description)
                            values(v_comp_no, v_asset_id, c1.asset_no, c1.asset_name)
                            ;
                       
                       end if;
                       
                       SELECT COUNT(*)
                       INTO v_count
                       FROM TAEQASSET
                       WHERE comp_no = v_comp_no
                           AND asset_id = v_asset_id
                           AND equip_id = v_equip_id
                       ;
                               
                       IF v_count = 0 THEN
                           INSERT INTO TAEQASSET(comp_no, eqasset_id, asset_id, equip_id)
                           VALUES(v_comp_no, sqaeqasset_id.NEXTVAL, v_asset_id, v_equip_id);
                       END IF;
               end if;
                       
               v_asmb_no := c1.asmb_no;
               IF v_asmb_no IS NOT NULL THEN
               
                   v_ord_no := 0;
                   FOR c2 IN c_asmb_data(v_asmb_no) LOOP
                        v_ord_no := v_ord_no + 1;
                        
                        INSERT INTO TAEQASMB(comp_no, eqasmb_id, equip_id, description, ord_no,is_use)
                        VALUES(v_comp_no, sqaeqasmb_id.NEXTVAL, v_equip_id, c2.str, v_ord_no, 'Y');
                        
                        
                   END LOOP;
                   
               END IF;
               
               
              
               
    END LOOP;
    
    
    
END;
/

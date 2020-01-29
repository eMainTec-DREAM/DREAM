CREATE OR REPLACE PROCEDURE SP_MUP_TYEQUIPMENT_NO (
    v_comp_no varchar2 DEFAULT '100'
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

-- select excel_no from TYEQUIPMENT group by excel_no having count(*) > 1   
-- truncate table TYEQUIPMENT   
-- truncate table TAEQUIPMENT
-- truncate table TAEQLOC
-- truncate table TACTCTR
-- exec SP_MUP_TYEQUIPMENT
      
    CURSOR c_excel_data IS
                               SELECT 
                         sn         
                        ,excel_no     --엑셀작성번호
                        ,TRIM(loc_plant) AS      loc_plant --공장구분
                        ,TRIM(loc_section) AS loc_gw        --생산과
                        ,TRIM(loc_line) AS loc_line       --라인
                --        ,TRIM(cost_cd) AS cost_cd       --CP코드
                --        ,TRIM(cost_nm) AS cost_nm      --CP명
                        ,item_no      --설비명
                        ,asmb_no      --부위
                        ,tp_l            --설비종류(대)
                        ,tp_2           --설비종류(중)
          --              ,tp_3           --설비종류(소)
                        ,dept_cd        --관리부서
                        ,TRIM(dept_nm) AS     dept_nm   --부서명
          --              ,TRIM(mainmng) AS      mainmng --담당정사번
          --              ,mainmng_name   --당당정성명
          --              ,TRIM(submng) AS submng        --담당부사번
          --              ,submng_name    --담당부성명
                        ,REPLACE(REPLACE(REPLACE(setup_date,'-',''),'.',''),' ','') AS setup_date   --설치일자
          --              ,NVL(buy_amt,0) AS buy_amt      --구입금액
          --              ,NVL(plf_type,'내자') AS plf_type     --내/외자구분
                        ,maker         --제작사
          --              ,model_no       --모델명
                        ,asset_no      --자산번호
          --              ,dep_dur      --감가상각기간
          --             ,capacity       --기계능력
          --              ,util_capa       --사용Util
          --              ,NVL(law_type,'N') AS  law_type     --법정설비여부
                        ,TRIM(as_vendor_name) AS as_vendor_name  -- AS 업체명
                        ,TRIM(as_name) AS as_name  --AS 담당자
          --              ,TRIM(as_email) AS as_email  -- AS 담당자 이메일
          --              ,TRIM(as_tel_no) AS as_tel_no  -- AS 담당자 전화번호
                        ,TRIM(as_mofile_no) AS as_mofile_no  -- AS담당자 핸드폰 번호
                FROM TYEQUIPMENT a
                WHERE 1=1
                ORDER BY dept_cd,  excel_no, sn
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
  
    FOR c1 IN c_excel_data LOOP
    
            -- 위치코드
              IF c1.loc_gw IS NOT NULL AND c1.loc_line IS NOT NULL THEN
                  v_full_desc := c1.loc_plant || ' > ' ||  c1.loc_gw || ' > ' ||  c1.loc_line;
              ELSIF c1.loc_gw IS NOT NULL THEN
                  v_full_desc := c1.loc_plant || ' > ' ||  c1.loc_gw ;
              ELSE 
                  v_full_desc := c1.loc_plant ;
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
              END IF;
           
              -- 부서코드
              SELECT COUNT(*)
               INTO v_count
               FROM TADEPT 
               WHERE dept_no = c1.dept_cd
                   AND ROWNUM = 1;
              
              IF v_count > 0 THEN
                   SELECT dept_id
                   INTO v_dept_id
                   FROM TADEPT 
                   WHERE dept_no = c1.dept_cd
                   AND ROWNUM = 1;
              END IF;
             
              -- 설비번호 
              SELECT TRIM(TO_CHAR(NVL(TO_NUMBER(MAX(TO_NUMBER(item_no))),10000) + 1,'00000'))
               INTO v_item_no
               FROM TAEQUIPMENT
               WHERE comp_no = v_comp_no
                  AND  TRANSLATE(item_no,'x0123456789','x') IS NULL
               ;
                       
              --설비id
               SELECT COUNT(*)
               INTO v_count
               FROM TAEQUIPMENT
               WHERE comp_no = v_comp_no
                   AND excel_no = c1.excel_no
               ;
               
               v_remark := '';
               IF c1.as_vendor_name IS NOT NULL THEN
                 v_remark := v_remark || ' AS 업체:' || c1.as_vendor_name ;
               END IF;
               IF c1.as_name IS  NOT NULL THEN
                 v_remark := v_remark || CHR(13)||CHR(10) || ' AS 담당:' || c1.as_name ;
               END IF;
              
                IF c1.as_mofile_no  IS NOT NULL THEN
                 v_remark := v_remark || CHR(13)||CHR(10) ||' AS 핸드폰:' || c1.as_mofile_no ;
               END IF;
               
                IF c1.asmb_no  IS NOT NULL THEN
                 v_remark := v_remark || CHR(13)||CHR(10) ||' 부위:' || c1.asmb_no ;
               END IF;
               
               
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
                       description = c1.item_no
                       ,dept_id = v_dept_id
                       ,eqloc_id = v_eqloc_id
                       ,eqctg_id = v_eqctg_id
                       ,eq_status = 'R'
                     --  ,main_mng_id = v_mainmng_id
                     --  ,sub_mng_id = v_submng_id
                       ,setup_date = c1.setup_date
                   --    ,buy_amt =  c1.buy_amt
                   --    ,plf_type = v_plf_type
                       ,maker = c1.maker
                   --    ,model_no = c1.model_no
                  --     ,capacity = c1.capacity
                   --    ,util_capa =  c1.util_capa
                  --     ,is_law_eq = v_is_law_eq
                       ,ord_no = c1.sn
                       ,REMARK = v_remark
                   WHERE  comp_no = v_comp_no
                   AND excel_no = c1.excel_no
                   ;
                   
               ELSE
                   INSERT INTO TAEQUIPMENT(
                        comp_no, equip_id, item_no, description, eqloc_id, eqctg_id
                       ,eq_status, dept_id--, main_mng_id, sub_mng_id
                       ,setup_date, maker--, buy_amt, plf_type, maker
                     --  ,model_no, capacity, util_capa, 
                        ,is_law_eq
                       ,ord_no, excel_no, REMARK
                   ) VALUES(
                       v_comp_no, v_equip_id, v_item_no, c1.item_no, v_eqloc_id, v_eqctg_id
                       ,'R', v_dept_id--, v_mainmng_id, v_submng_id
                       ,c1.setup_date, c1.maker--, c1.buy_amt, v_plf_type, c1.maker
                     --  ,c1.model_no, c1.capacity, c1.util_capa, v_is_law_eq
                        ,'Y'
                       ,c1.sn, c1.excel_no ,v_remark
                   );
                   
               END IF;
               
               -- 자산번호 갱신
               -- TAEQASSET
               -- TAASSET
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
                       
                       
                   
               END IF;
               
/*                  
               v_asmb_no := c1.asmb_no;
               if v_asmb_no is not null then
               
                   v_ord_no := 0;
                   for c2 in c_asmb_data(v_asmb_no) loop
                        -- 부위생성
                        v_ord_no := v_ord_no + 1;
                        
                        insert into TAEQASMB(comp_no, eqasmb_id, equip_id, description, ord_no,is_use)
                        values(v_comp_no, sqaeqasmb_id.nextval, v_equip_id, c2.str, v_ord_no, 'Y');
                        
                        
                   end loop;
                   
               end if;
               */
               --SP_MUP_TYEQLOC_NO('100')  --위치 데이터 설정

--SP_MUP_TYCATEG_NO('100') --  기능 데이터 설정

--SP_MUP_TYEQUIPMENT_NO('100')  -- 설비 데이터 
 
--SP_MUP_TYEQCTG_NO('100')  -- 설비에 기능 설정
              
               
    END LOOP;
    
    
    
END;
/

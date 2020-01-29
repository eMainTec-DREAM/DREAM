CREATE OR REPLACE PROCEDURE SP_IF_UPD_ASSET(
      v_comp_no     IN varchar2
     ,v_user_no      IN varchar2
) IS

   v_count number(4);
   v_asset_id number(18);
      
    CURSOR c_data IS
        SELECT 
               asset_number AS asset_no
               ,description AS description
               ,to_char(ori_date,'yyyymmdd') AS acq_date
               ,ori_cost AS buyer_amt
               ,dep_cost AS dep_amt
               ,sal_cost AS res_amt
               ,to_char(last_update_date,'yyyymmddhh24miss') AS last_update_date
        FROM OEMS_asset_books_M_V@OEMS_CRP_DBLINK a
        WHERE 1=1
            AND to_char(last_update_date,'yyyymmdd') >= (SELECT last_exe_date 
                                                                                 FROM TABATPGM 
                                                                                 WHERE comp_no = v_comp_no 
                                                                                           AND batpgm_no = 'TAASSET' 
                                                                                )

        ;
                                
BEGIN

    -- TAASSET : ERP에서 자산번호,자산금액을 가져오는 프로그램
    -- EXEC SP_IF_UPD_ASSET('100','ADMIN')
        
    FOR c1 IN c_data LOOP
    
        
        -- create TAASSET table
        
        SELECT count(*)
        INTO v_count
        FROM TAASSET
        WHERE comp_no = v_comp_no
            AND asset_no = c1.asset_no
        ;
        
        IF v_count > 0 THEN
            SELECT asset_id
            INTO v_asset_id
            FROM TAASSET
            WHERE comp_no = v_comp_no
                AND asset_no = c1.asset_no
            ;
            
            UPDATE TAASSET set
                 description = c1.description
                ,acq_date = c1.acq_date
                ,buyer_amt = c1.buyer_amt
                ,dep_amt = c1.dep_amt
                ,res_amt = c1.res_amt
            WHERE comp_no = v_comp_no
                AND asset_no = c1.asset_no
            ;
            
        ELSE
            SELECT sqaasset_id.NEXTVAL
            INTO v_asset_id
            FROM dual; 
            
            INSERT INTO TAASSET(comp_no,asset_id,asset_no,description,acq_date,buyer_amt,dep_amt,res_amt)
            VALUES(v_comp_no, v_asset_id, c1.asset_no,c1.description,c1.acq_date,c1.buyer_amt,c1.dep_amt,c1.res_amt)
            ;
            
        
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
        AND batpgm_no = 'TAASSET'
    ;
    
    
    
END;
/

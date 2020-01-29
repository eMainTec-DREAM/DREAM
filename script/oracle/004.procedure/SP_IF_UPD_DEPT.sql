CREATE OR REPLACE PROCEDURE SP_IF_UPD_DEPT   (
      v_comp_no     IN varchar2
     ,v_user_no      IN varchar2
) IS

   v_count number(4);
   v_dept_id number(18);
   v_p_dept_id number(18);
      
    CURSOR c_data IS
        SELECT 
              group_id
              ,dept_code AS dept_no
              ,dept_name AS description
              ,to_char(org_order,'0000') AS ord_no
              ,parent_dept_code AS p_dept_no
              ,parent_dept_name
              ,'Y' AS is_use
        FROM OEMS_HR_ORG_M_V@OEMS_CRP_DBLINK a
        WHERE 1=1
        ;
                                
BEGIN

    
    FOR c1 IN c_data LOOP
    
        SELECT count(*)
        INTO v_count
        FROM tadept
        WHERE comp_no = v_comp_no
            AND dept_no = c1.p_dept_no
        ;
        
        IF v_count > 0 THEN
            SELECT dept_id
            INTO v_p_dept_id
            FROM tadept
            WHERE comp_no = v_comp_no
                AND dept_no = c1.p_dept_no
            ;
        ELSE
            v_p_dept_id := 0;
        END IF;
    
        -- create dept table
        SELECT count(*)
        INTO v_count
        FROM tadept
        WHERE comp_no = v_comp_no
            AND dept_no = c1.dept_no
        ;
        
        IF v_count > 0 THEN
            SELECT dept_id
            INTO v_dept_id
            FROM tadept
            WHERE comp_no = v_comp_no
                AND dept_no = c1.dept_no
            ;
        ELSE
            SELECT sqadept_id.NEXTVAL
            INTO v_dept_id
            FROM dual; 

        END IF;
        
                
        IF v_count > 0 THEN
        
            UPDATE tadept set
                 description = c1.description
                ,p_dept_id = v_p_dept_id
                ,is_use = c1.is_use
                ,ord_no = c1.ord_no
            WHERE comp_no = v_comp_no
                AND dept_no = c1.dept_no
            ;
       
        ELSE
        
            INSERT INTO tadept(comp_no, dept_id, dept_no, description, p_dept_id,is_use, ord_no)
            VALUES(v_comp_no, v_dept_id, c1.dept_no, c1.description, v_p_dept_id, c1.is_use, c1.ord_no)
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
        AND batpgm_no = 'TADEPT'
    ;
    
    
    
END;
/

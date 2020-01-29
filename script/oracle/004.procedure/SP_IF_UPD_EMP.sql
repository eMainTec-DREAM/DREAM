CREATE OR REPLACE PROCEDURE SP_IF_UPD_EMP    (
      v_comp_no     IN varchar2
     ,v_user_no      IN varchar2
) IS

   v_count number(4);
   v_dept_id number(18);
   v_emp_id number(18);
      
    CURSOR c_data IS
        SELECT 
              group_id
              ,emp_no
              ,emp_name
              ,email AS e_mail
              ,dept_code AS dept_no
              ,dept_name
              ,'Y' AS is_direct
              ,tel
              ,mobile AS m_phone
              ,to_char(hired_date,'yyyymmdd') AS join_date
              ,grade
              ,to_char(retired_date,'yyyymmdd') AS retired_date
              ,CASE WHEN nvl(to_char(retired_date,'yyyymmdd'),'99991231') >= to_char(sysdate,'yyyymmdd') THEN 'Y' ELSE 'N' END  is_join
              ,to_char(last_update_date,'yyyymmdd') AS last_update_date
        FROM OEMS_HR_EMP_M_V@OEMS_CRP_DBLINK a
        WHERE 1=1
            AND to_char(last_update_date,'yyyymmdd') >= (SELECT last_exe_date 
                                                                                 FROM TABATPGM 
                                                                                 WHERE comp_no = v_comp_no 
                                                                                           AND batpgm_no = 'TAEMP' 
                                                                                 )
        ;
                                
BEGIN

    
    FOR c1 IN c_data LOOP
    
        -- get dept id
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
            v_dept_id := NULL;
        END IF;
        
        
        -- create emp table
        SELECT count(*)
        INTO v_count
        FROM taemp
        WHERE comp_no = v_comp_no
            AND emp_no = c1.emp_no
        ;
        
        IF v_count > 0 THEN
            UPDATE taemp set
                emp_name = c1.emp_name
                ,dept_id = v_dept_id
                ,grade = c1.grade
                ,is_direct = c1.is_direct
                ,m_phone = c1.m_phone
                ,e_mail = c1.e_mail
                ,join_date = c1.join_date
                ,retire_date = c1.retired_date
                ,is_join = c1.is_join
            WHERE comp_no = v_comp_no
                AND emp_no = c1.emp_no
            ;
        
        ELSE
        
            SELECT sqaemp_id.NEXTVAL
            INTO v_emp_id
            FROM dual;
            
            INSERT INTO taemp(comp_no, emp_id, emp_no, emp_name, dept_id, grade, is_direct
                              , m_phone, e_mail, join_date, retire_date, is_join)
            VALUES(v_comp_no, v_emp_id, c1.emp_no, c1.emp_name, v_dept_id, c1.grade, c1.is_direct
                              , c1.m_phone, c1.e_mail, c1.join_date, c1.retired_date, c1.is_join
                              )
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
        AND batpgm_no = 'TAEMP'
    ;
    
    
    
END;
/

CREATE OR REPLACE PROCEDURE DREAM.SP_PM_MAKE_SCHEDULE_BYALL(
     v_comp_no     IN varchar2          
    ,v_user_no     IN varchar2  
) IS
    v_count             number(4);   
    v_user_id             number(18);
    
    -- truncate table TAPMSCHED 
    -- exec SP_PM_MAKE_SCHEDULE_BYALL('100','36','ADMIN','20161231')
    
    CURSOR c_pm IS
                SELECT
                     a.comp_no 
                    ,a.pm_id
                    ,nvl(a.target_sch_date, TO_CHAR(ADD_MONTHS(SYSDATE,12),'yyyymmdd')) as target_sch_date  -- 언제까지 생성할건지 날짜
                FROM TAPMLST a
                WHERE 1=1
                    AND a.comp_no = v_comp_no
                    AND a.dept_id IN (SELECT d.dept_id
                                               FROM   TADEPT d
                                               WHERE comp_no = v_comp_no
                                                   AND d.is_use = 'Y'
                                               START WITH d.p_dept_id = 0 AND d.comp_no = v_comp_no AND d.is_use = 'Y'
                                               CONNECT BY PRIOR d.dept_id = d.p_dept_id AND d.comp_no = v_comp_no)
                    AND a.is_active = 'Y'
                    AND a.last_sch_date <= TO_CHAR(ADD_MONTHS(SYSDATE,12),'yyyymmdd') 
            ;
  
BEGIN

    BEGIN
        SELECT user_id 
        INTO v_user_id
        FROM TAUSER 
        WHERE comp_no = v_comp_no 
            AND user_no = v_user_no 
            AND ROWNUM = 1
        ;
    EXCEPTION
        WHEN others THEN v_user_id := NULL;
    END ;
    
    FOR c1 IN c_pm LOOP
    
      SP_PM_MAKE_SCHEDULE_BYONE(v_comp_no, c1.pm_id, v_user_id,  c1.target_sch_date );
        
    END LOOP;
    
    UPDATE TABATPGM SET 
         exe_by = v_user_id
        ,last_exe_date = TO_CHAR(SYSDATE,'yyyymmdd')
        ,last_exe_time = TO_CHAR(SYSDATE,'yyyymmddhh24miss')
    WHERE comp_no = v_comp_no
        AND batpgm_no = 'TAPMSCHED_ALL'
    ;
    
END;
/

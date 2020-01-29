CREATE OR REPLACE procedure SP_KPI_MAKE_TAKPIMEDU(
      v_comp_no     in varchar2      
     ,v_user_no      in varchar2
) is
    v_count                                 number(4); 

    
    -- exec SP_KPI_MAKE_TAKPIMEDU('100','ADMIN'); 

    cursor c_tr_plan_actual is       
        select  a.comp_no,
            (select plant FROM TAPLANT where comp_no= v_comp_no and rownum=1) plant,
            SUBSTR (a.wkor_date, 1, 6) AS yyyymm,
            f.pm_type AS tr_type,
            COUNT (*) AS pvalue,
            SUM (CASE WHEN a.wo_status = 'C' THEN 1 ELSE 0 END) AS avalue
       FROM taworkorder a,
            tapmsched b,
            tapmlst f
      WHERE     a.comp_no = b.comp_no
            AND a.pmsched_id = b.pmsched_id
            AND a.comp_no = f.comp_no
            AND a.pm_id = f.pm_id
            AND a.wo_status IN ('P', 'C')
            and f.wo_type = 'TR'
            and a.wkor_date >= to_char(trunc(add_months(sysdate,-1),'mm'),'yyyymmdd') 
            and a.wkor_date <= to_char(sysdate,'yyyymmdd')
            AND f.pm_type IS NOT NULL
            AND a.wkor_date IS NOT NULL
   GROUP BY a.comp_no,SUBSTR (a.wkor_date, 1, 6),f.pm_type
    ;

begin

    
        
   for c1 in c_tr_plan_actual loop
   
       select count(*)
       into v_count
       from takpimedu
       where comp_no = c1.comp_no
           and plant = c1.plant
           and yyyymm = c1.yyyymm
           and tr_type = c1.tr_type
       ;
       
       if v_count > 0 then
           update takpimedu set
                     m_plan = c1.pvalue
                     ,m_actual = c1.avalue
            where comp_no = c1.comp_no
                   and plant = c1.plant
                   and yyyymm = c1.yyyymm
                   and tr_type = c1.tr_type
            ;
       else
           insert into takpimedu( comp_no, plant, yyyymm, tr_type, m_plan, m_actual
           ) values (
               c1.comp_no, c1.plant, c1.yyyymm, c1.tr_type, c1.pvalue, c1.avalue
           );
       end if;
       
   end loop;
   
   
   
    update TABATPGM set 
         exe_by = (select user_id 
                        from tauser 
                        where comp_no = v_comp_no 
                            and user_no = v_user_no 
                            and rownum = 1
                       )
        ,last_exe_date = to_char(sysdate,'yyyymmdd')
        ,last_exe_time = to_char(sysdate,'yyyymmddhh24miss')
    where comp_no = v_comp_no
        and batpgm_no = 'TAKPIMEDU'
    ;
    
    
    
    
end;
/

CREATE OR REPLACE PROCEDURE SP_IF_UPD_CTCTR  (
      v_comp_no     in varchar2
     ,v_user_no      in varchar2
) is

   v_count number(4);
   v_ctctr_id number(18);
      
    cursor c_data is
        select 
                cp_code as ctctr_no
               ,cp_name as description
               ,cp_level 
               ,dept_code as dept_no
               ,dept_name
               ,parent_dept_code as p_dept_no
               ,parent_dept_name
               ,cp_code as ord_no
               ,'Y' as is_use
               ,to_char(last_update_date,'yyyymmddhh24miss') as last_update_date
        from OEMS_CP_M_V@OEMS_CRP_DBLINK a
        where 1=1
           and to_char(last_update_date,'yyyymmdd') >= (select last_exe_date 
                                                                                 from TABATPGM 
                                                                                 where comp_no = v_comp_no 
                                                                                           and batpgm_no = 'TACTCTR' 
                                                                                 )
        ;
                                
begin

        
    for c1 in c_data loop
    
        
        -- create TACTCTR table
        
        select count(*)
        into v_count
        from TACTCTR
        where comp_no = v_comp_no
            and ctctr_no = c1.ctctr_no
        ;
        
        if v_count > 0 then
            select ctctr_id
            into v_ctctr_id
            from TACTCTR
            where comp_no = v_comp_no
                and ctctr_no = c1.ctctr_no
            ;
            
                     
            update TACTCTR set
                 description = c1.description
                ,ord_no = c1.ord_no
                ,is_use = c1.is_use
            where comp_no = v_comp_no
                and ctctr_no = c1.ctctr_no
            ;
            
        else
            select sqactctr_id.nextval
            into v_ctctr_id
            from dual; 
            
            insert into TACTCTR(comp_no, ctctr_id, ctctr_no, description, ord_no, is_use)
            values(v_comp_no, v_ctctr_id, c1.ctctr_no,c1.description,c1.ord_no,c1.is_use)
            ;
            
        
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
        and batpgm_no = 'TACTCTR'
    ;
    
    
    
end;
/

CREATE OR REPLACE PROCEDURE SP_IF_UPD_VENDOR  (
      v_comp_no     in varchar2
     ,v_user_no      in varchar2
) is

   v_count number(4);
   v_vendor_id number(18);
      
    cursor c_data is
        select 
               vendor_code as vendor_no
              ,vendor_nane as description
              ,address
              ,rep_name as repname
              ,last_update_date
        from OEMS_VENDOR_M_V@OEMS_CRP_DBLINK a
        where 1=1
            and to_char(last_update_date,'yyyymmddhh24miss')  >= (select last_exe_date 
                                                                                                 from TABATPGM 
                                                                                                 where comp_no = v_comp_no 
                                                                                                           and batpgm_no = 'TAVENDOR' 
                                                                                              )
        ;
                                
begin

        
    for c1 in c_data loop
    
        -- create dept table
        
        select count(*)
        into v_count
        from tavendor
        where comp_no = v_comp_no
            and vendor_no = c1.vendor_no
        ;
        
        if v_count > 0 then
            select vendor_id
            into v_vendor_id
            from tavendor
            where comp_no = v_comp_no
                and vendor_no = c1.vendor_no
            ;
            
            update tavendor set
                 description = c1.description
                ,address = c1.address
                ,repname = c1.repname
            where comp_no = v_comp_no
                and vendor_no = c1.vendor_no
            ;
            
        else
            select sqavendor_id.nextval
            into v_vendor_id
            from dual; 
            
            insert into tavendor(comp_no, vendor_id, vendor_no, description, address, repname,is_use)
            values(v_comp_no, v_vendor_id, c1.vendor_no, c1.description, c1.address, c1.repname,'N')
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
        and batpgm_no = 'TAVENDOR'
    ;
    
    
    
end;
/

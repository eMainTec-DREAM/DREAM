CREATE OR REPLACE PROCEDURE "SP_MUP_TYVENDOR" (
    v_comp_no varchar2 default '100'
) is

   v_count number(4);
      
    cursor c_excel_data is
                select 
                      sn
                     ,vendor_no
                     ,description
                     ,rep_name
                     ,address
                from TYVENDOR a
                where 1=1
                order by sn
                ;
                                
begin

    
    
    for c1 in c_excel_data loop
    
               select count(*)
               into v_count
               from TAVENDOR
               where comp_no = v_comp_no
                   and vendor_no = c1.vendor_no
               ;
               
               if v_count > 0 then 
                   update TAVENDOR set
                       description = c1.description
                       ,repname = c1.rep_name
                       ,address = c1.address
                   where   comp_no = v_comp_no
                   and vendor_no = c1.vendor_no
                   ;
               else
                   
                   insert into TAVENDOR(comp_no, vendor_id, vendor_no, description, repname, address)
                   values(v_comp_no, sqavendor_id.nextval, c1.vendor_no, c1.description,  c1.rep_name,c1.address)
                   ;
                   
                   
               end if;
               
               
                 
                 null;
               
    end loop;
    
    
    
end;
/

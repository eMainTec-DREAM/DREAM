CREATE OR REPLACE PROCEDURE "SP_MUP_TYFAILURE" (
    v_comp_no varchar2 default '100'
) is

   v_count number(4);
      
    cursor c_excel_data is
                select 
                      sn
                     ,failure_no
                     ,description
                     ,fail_type
                from TYFAILURE a
                where 1=1
                order by sn
                ;
                                
begin

    
    
    for c1 in c_excel_data loop
    
               select count(*)
               into v_count
               from TAFAILURE
               where comp_no = v_comp_no
                   and failure_no = c1.failure_no
               ;
               
               if v_count > 0 then 
                   update TAFAILURE set
                       description = c1.description
                       ,fail_type = c1.fail_type
                       ,is_use = 'Y'
                       ,ord_no = c1.sn
                   where  comp_no = v_comp_no
                       and failure_no = c1.failure_no
                   ;
               else
                   
                   insert into TAFAILURE(comp_no, failure_id, failure_no, description, fail_type, is_use, ord_no)
                   values(v_comp_no, sqafailure_id.nextval, c1.failure_no, c1.description,  c1.fail_type,'Y',c1.sn)
                   ;
                   
                   
               end if;
               
               
                 
                 null;
               
    end loop;
    
    
    
end;
/

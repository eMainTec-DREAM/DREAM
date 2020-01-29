CREATE OR REPLACE PROCEDURE "SP_MUP_TYCTCTR" (
    v_comp_no varchar2 default '100'
) is

   v_count number(4);
      
    cursor c_excel_data is
                select 
                      sn
                     ,ctctr_no
                     ,description
                from TYCTCTR a
                where 1=1
                order by sn
                ;
                                
begin

    
    
    for c1 in c_excel_data loop
    
               select count(*)
               into v_count
               from TACTCTR
               where comp_no = v_comp_no
                   and ctctr_no = c1.ctctr_no
               ;
               
               if v_count > 0 then 
                   update TACTCTR set
                       description = c1.description
                       ,is_use = 'Y'
                       ,ord_no = c1.sn
                   where  comp_no = v_comp_no
                       and ctctr_no = c1.ctctr_no
                   ;
               else
                   
                   insert into TACTCTR(comp_no, ctctr_id, ctctr_no, description, is_use, ord_no)
                   values(v_comp_no, sqactctr_id.nextval, c1.ctctr_no, c1.description,  'Y',c1.sn)
                   ;
                   
                   
               end if;
               
               
                 
                 null;
               
    end loop;
    
    
    
end;
/

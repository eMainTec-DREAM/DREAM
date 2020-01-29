CREATE OR REPLACE PROCEDURE "SP_MUP_TYDEPT" (
    v_comp_no varchar2 default '100'
) is

   v_count number(4);
   v_p_dept_id number(18);
      
    cursor c_excel_data is
                select 
                      sn
                     ,dept_no
                     ,dept_name
                     ,p_dept_no
                     ,p_dept_name
                     ,dept_categ
                     ,dept_categ_name
                from TYDEPT a
                where 1=1
                order by sn
                ;
                                
begin

    
    
    for c1 in c_excel_data loop
    
               
               select count(*)
               into v_count
               from tadept
               where comp_no = v_comp_no
                   and dept_no = c1.dept_no
               ;
               
               if v_count > 0 then 
                   update tadept set
                       description = c1.dept_name
                       ,dept_categ = c1.dept_categ
                       ,ord_no = c1.sn
                   where  comp_no = v_comp_no
                       and dept_no = c1.dept_no
                   ;
               else
                   
                   insert into tadept(comp_no, dept_id, dept_no, description, is_use,ord_no, dept_categ)
                   values(v_comp_no, sqadept_id.nextval, c1.dept_no, c1.dept_name,  'Y', c1.sn, c1.dept_categ)
                   ;
               end if;
               
    end loop;
    
    
    for c1 in c_excel_data loop
    
               select count(*)
               into v_count
               from tadept
               where comp_no = v_comp_no
                   and dept_no = c1.p_dept_no
               ;
               
               if v_count > 0 then 
               
                   select dept_id
                   into v_p_dept_id
                   from tadept
                   where comp_no = v_comp_no
                       and dept_no = c1.p_dept_no
                   ;
               
                   update tadept set
                       p_dept_id = v_p_dept_id
                   where  comp_no = v_comp_no
                       and dept_no = c1.dept_no
                   ;
                   
               end if;
               
    end loop;
    
    
end;
/

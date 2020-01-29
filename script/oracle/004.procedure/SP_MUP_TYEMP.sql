CREATE OR REPLACE PROCEDURE "SP_MUP_TYEMP" (
    v_comp_no varchar2 default '100'
) is

   v_count number(4);
   v_dept_id number(18);
   v_wkctr_id number(18);
      
    cursor c_excel_data is
                select 
                      sn
                     ,emp_no
                     ,emp_name
                     ,dept_no
                     ,dept_name
                     ,grade
                     ,m_phome
                     ,e_mail
                     ,join_date
                     ,secure_level
                     ,wkctr_desc
                     ,is_use
                from TYEMP a
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
                    select dept_id
                    into v_dept_id
                    from tadept
                    where comp_no = v_comp_no
                        and dept_no = c1.dept_no
                        and rownum = 1
                    ;
               else
                   v_dept_id := null;
               end if;
               
                select count(*)
               into v_count
               from tawkctr
               where comp_no = v_comp_no
                   and description = c1.wkctr_desc
               ;
               
               if v_count > 0 then
                    select wkctr_id
                    into v_wkctr_id
                    from tawkctr
               where comp_no = v_comp_no
                   and description = c1.wkctr_desc
                        and rownum = 1
                    ;
               else
                   v_wkctr_id := null;
               end if;
            
               
               
               select count(*)
               into v_count
               from taemp
               where comp_no = v_comp_no
                   and emp_no = c1.emp_no
               ;
               
               if v_count > 0 then 
                   update taemp set
                       emp_name = c1.emp_name
                       ,dept_id  = v_dept_id
                       ,grade = c1.grade
                       ,is_direct = 'Y'
                       ,m_phone = c1.m_phome
                       ,e_mail = c1.e_mail
                       ,is_join = case when c1.is_use = '사용' then 'Y' else 'N' end
                       ,join_date = c1.join_date
                       ,wkctr_id = v_wkctr_id
                   where  comp_no = v_comp_no
                       and emp_no = c1.emp_no
                   ;
               else
                   
                   insert into taemp(comp_no, emp_id, emp_no, emp_name, dept_id, grade, is_direct, m_phone,e_mail,is_join,join_date,wkctr_id)
                   values(v_comp_no, sqaemp_id.nextval, c1.emp_no, c1.emp_name, v_dept_id, c1.grade,'Y',c1.m_phome,c1.e_mail,case when c1.is_use = '사용' then 'Y' else 'N' end, c1.join_date,v_wkctr_id)
                   ;
                   
                   
               end if;
               
               
                 
                 null;
               
    end loop;
    
    
    
end;
/

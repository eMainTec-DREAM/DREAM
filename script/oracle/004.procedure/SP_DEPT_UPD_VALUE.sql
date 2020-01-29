CREATE OR REPLACE PROCEDURE SP_DEPT_UPD_VALUE (
      v_comp_no     in varchar2
     ,v_dept_id      in number
) is


        v_count number(4);
   

begin

            update TADEPT a set a.IS_LOWEST_LVL = 'N'                        
            where 1=1                                                         
                and a.comp_no = v_comp_no        
                and a.dept_id in (select b.dept_id                          
                                        from TADEPT b                        
                                        where 1=1                             
                                        start with b.dept_id =v_dept_id
                                        connect by prior b.p_dept_id = b.dept_id                   
                                       )    
           ;
           
           
           select count(a.dept_id)
           into v_count                                          
           from TADEPT a                                                
           where 1=1                                                     
                and a.comp_no = v_comp_no 
           start with a.p_dept_id = v_dept_id
           connect by prior a.dept_id = a.p_dept_id  
           ;
           
           if v_count = 0 then
                 update tadept a set a.IS_LOWEST_LVL = 'Y'             
                 where 1=1                                              
                    and a.comp_no = v_comp_no
                    and a.dept_id = v_dept_id
                 ;
           end if;
                                                                                           



end;
/

CREATE OR REPLACE PROCEDURE SP_DEPT_UPD_VALUE_ALL (
      v_comp_no     in varchar2
) is

   v_count number(4);

    cursor c_data is
        SELECT comp_no, dept_id                         
        FROM TADEPT                                            
        WHERE comp_no=v_comp_no
        order by dept_id            
        ;

begin


    for c1 in c_data loop

       SP_DEPT_UPD_VALUE(c1.comp_no, c1.dept_id);
       
    end loop;


end;
/

CREATE OR REPLACE PROCEDURE SP_EQ_UPD_EQLOC_ALL (
      v_comp_no     in varchar2
) is

   v_count number(4);
   
   -- exec SP_EQ_UPD_EQLOC_ALL('120')

    cursor c_data is
        SELECT comp_no, eqloc_id                         
        FROM TAEQLOC                                            
        WHERE comp_no=v_comp_no
        order by eqloc_id            
        ;

begin


    for c1 in c_data loop

       SP_EQ_UPD_EQLOC(c1.comp_no, c1.eqloc_id);
       
    end loop;


end;
/

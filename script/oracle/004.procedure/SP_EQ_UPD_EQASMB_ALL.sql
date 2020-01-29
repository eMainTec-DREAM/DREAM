CREATE OR REPLACE PROCEDURE SP_EQ_UPD_EQASMB_ALL (
      v_comp_no     in varchar2
) is

   v_count number(4);
   
   -- exec SP_EQ_UPD_EQLOC_ALL('120')

    cursor c_data is
        SELECT comp_no, eqasmb_id                         
        FROM TAEQASMB                                            
        WHERE comp_no=v_comp_no
        order by eqasmb_id            
        ;

begin


    for c1 in c_data loop

       SP_EQ_UPD_EQASMB(c1.comp_no, c1.eqasmb_id);
       
    end loop;


end;
/

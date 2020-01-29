CREATE OR REPLACE PROCEDURE SP_EQ_UPD_TAEQCTG_ALL (
      v_comp_no     in varchar2
) is

   v_count number(4);
   
   -- exec SP_EQ_UPD_TAEQCTG_ALL('120')

    cursor c_data is
        SELECT comp_no, eqctg_id                         
        FROM TAEQCTG                                            
        WHERE comp_no=v_comp_no
        order by eqctg_id            
        ;

begin


    for c1 in c_data loop

       SP_EQ_UPD_TAEQCTG(c1.comp_no, c1.eqctg_id);
       
    end loop;


end;
/

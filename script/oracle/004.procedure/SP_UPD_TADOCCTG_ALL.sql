CREATE OR REPLACE PROCEDURE DREAM_DEV.SP_UPD_TADOCCTG_ALL (
      v_comp_no     IN varchar2
) IS

   v_count number(4);

    CURSOR c_data IS
        SELECT comp_no, docctg_id                         
        FROM TADOCCTG                                            
        WHERE comp_no=v_comp_no
        ORDER BY docctg_id            
        ;

BEGIN


    FOR c1 IN c_data LOOP

       SP_UPD_TADOCCTG(c1.comp_no, c1.docctg_id);
       
    END LOOP;


END;
/

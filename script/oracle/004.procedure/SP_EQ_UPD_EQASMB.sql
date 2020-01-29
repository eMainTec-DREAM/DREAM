CREATE OR REPLACE PROCEDURE SP_EQ_UPD_EQASMB (
      v_comp_no         in varchar2
     ,v_eqasmb_id      in number
) is
       
     v_count number(4);
     
begin

    UPDATE TAEQASMB    SET                                       
                full_desc = (                                                    
                SELECT 
                        min(decode(lev,9,description||'-',''))||
                        min(decode(lev,8,description||'-',''))||
                        min(decode(lev,7,description||'-',''))||
                        min(decode(lev,6,description||'-',''))||
                        min(decode(lev,5,description||'-',''))||
                        min(decode(lev,4,description||'-',''))||                
                        min(decode(lev,3,description||'-',''))||                
                        min(decode(lev,2,description||'-',''))||                
                        min(decode(lev,1,description,'')) a                      
                FROM (                                                        
                        SELECT description,LEVEL lev                             
                        FROM TAEQASMB                                            
                        WHERE comp_no=v_comp_no  
                        START WITH eqasmb_id=v_eqasmb_id    
                        CONNECT BY PRIOR  p_eqasmb_id = eqasmb_id
                    )                                                            
                )                               
                WHERE eqasmb_id = v_eqasmb_id
            ; 
            
end;
/

CREATE OR REPLACE PROCEDURE DREAM_DEV.SP_UPD_TADOCCTG (
      v_comp_no     IN varchar2
     ,v_docctg_id      IN number
) IS


        v_count number(4);
   

BEGIN


    UPDATE TADOCCTG    SET                                       
                full_desc = (                                                    
                SELECT 
                        MIN(DECODE(lev,5,description||'-',''))||
                        MIN(DECODE(lev,4,description||'-',''))||                
                        MIN(DECODE(lev,3,description||'-',''))||                
                        MIN(DECODE(lev,2,description||'-',''))||                
                        MIN(DECODE(lev,1,description,'')) a                      
                FROM (                                                        
                        SELECT description,LEVEL lev                             
                        FROM TADOCCTG                                            
                        WHERE comp_no=v_comp_no  
                        START WITH docctg_id=v_docctg_id    
                        CONNECT BY PRIOR  p_docctg_id = docctg_id                   
                    )                                                            
                )            
                WHERE docctg_id = v_docctg_id      
            ;                 
END;
/

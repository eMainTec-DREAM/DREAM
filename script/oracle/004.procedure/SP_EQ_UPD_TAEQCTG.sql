CREATE OR REPLACE PROCEDURE SP_EQ_UPD_TAEQCTG (
      v_comp_no     in varchar2
     ,v_eqctg_id      in number
) is


        v_count number(4);
   

begin


    UPDATE TAEQCTG    SET                                       
                full_desc = (                                                    
                SELECT min(decode(lev,4,description||' > ',''))||                
                        min(decode(lev,3,description||' > ',''))||                
                        min(decode(lev,2,description||' > ',''))||                
                        min(decode(lev,1,description,'')) a                      
                FROM (                                                        
                        SELECT description,LEVEL lev                             
                        FROM TAEQCTG                                            
                        WHERE comp_no=v_comp_no  
                        START WITH eqctg_id=v_eqctg_id    
                        CONNECT BY PRIOR  p_eqctg_id = eqctg_id                   
                    )                                                            
                ),                                                            
                lvl = (                                                        
                        SELECT MAX(LEVEL)                                         
                        FROM TAEQCTG                                            
                        WHERE comp_no=v_comp_no 
                        START WITH eqctg_id=v_eqctg_id    
                        CONNECT BY PRIOR  p_eqctg_id = eqctg_id                    
                )                                                                
                WHERE eqctg_id = v_eqctg_id      
            ; 
            
            
            update TAEQCTG a set a.IS_LOWEST_LVL = 'N'                        
            where 1=1                                                         
                and a.comp_no = v_comp_no        
                and a.eqctg_id in (select b.eqctg_id                          
                                        from TAEQCTG b                        
                                        where 1=1                             
                                        start with b.eqctg_id =v_eqctg_id
                                        connect by prior b.p_eqctg_id = b.eqctg_id                   
                                       )    
           ;
           
           
           select count(a.eqctg_id)
           into v_count                                          
           from TAEQCTG a                                                
           where 1=1                                                     
                and a.comp_no = v_comp_no 
           start with a.p_eqctg_id = v_eqctg_id
           connect by prior a.eqctg_id = a.p_eqctg_id 
           ;
           
           if v_count = 0 then
                 update TAEQCTG a set a.IS_LOWEST_LVL = 'Y'             
                 where 1=1                                              
                    and a.comp_no = v_comp_no
                    and a.eqctg_id = v_eqctg_id
                 ;
           end if;
                                                                                           



end;
/

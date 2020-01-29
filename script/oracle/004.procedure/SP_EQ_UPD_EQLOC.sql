CREATE OR REPLACE PROCEDURE SP_EQ_UPD_EQLOC (
      v_comp_no     in varchar2
     ,v_eqloc_id      in number
) is


        v_count number(4);
   

begin


    UPDATE TAEQLOC    SET                                       
                full_desc = (                                                    
                SELECT min(decode(lev,4,description||' > ',''))||                
                        min(decode(lev,3,description||' > ',''))||                
                        min(decode(lev,2,description||' > ',''))||                
                        min(decode(lev,1,description,'')) a                      
                FROM (                                                        
                        SELECT description,LEVEL lev                             
                        FROM TAEQLOC                                            
                        WHERE comp_no=v_comp_no  
                        START WITH eqloc_id=v_eqloc_id    
                        CONNECT BY PRIOR  p_eqloc_id = eqloc_id                    
                    )                                                            
                ),                                                            
                lvl = (                                                        
                        SELECT MAX(LEVEL)                                         
                        FROM TAEQLOC                                            
                        WHERE comp_no=v_comp_no 
                        START WITH eqloc_id=v_eqloc_id    
                        CONNECT BY PRIOR  p_eqloc_id = eqloc_id                    
                )                                                                
                WHERE eqloc_id = v_eqloc_id      
            ; 
            
            
            update TAEQLOC a set a.IS_LOWEST_LVL = 'N'                        
            where 1=1                                                         
                and a.comp_no = v_comp_no        
                and a.eqloc_id in (select b.eqloc_id                          
                                        from taeqloc b                        
                                        where 1=1                             
                                        start with b.eqloc_id =v_eqloc_id
                                        connect by prior b.p_eqloc_id = b.eqloc_id                   
                                       )    
           ;
           
           
           select count(a.eqloc_id)
           into v_count                                          
           from TAEQLOC a                                                
           where 1=1                                                     
                and a.comp_no = v_comp_no 
           start with a.p_eqloc_id = v_eqloc_id
           connect by prior a.eqloc_id = a.p_eqloc_id  
           ;
           
           if v_count = 0 then
                 update TAEQLOC a set a.IS_LOWEST_LVL = 'Y'             
                 where 1=1                                              
                    and a.comp_no = v_comp_no
                    and a.eqloc_id = v_eqloc_id
                 ;
           end if;
                                                                                           



end;
/

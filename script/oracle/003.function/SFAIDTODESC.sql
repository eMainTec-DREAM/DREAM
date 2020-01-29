CREATE OR REPLACE FUNCTION SFAIDTODESC(pId VARCHAR2, pCodeType VARCHAR2, pClassType VARCHAR2, pCompNo VARCHAR2)
    RETURN VARCHAR2
IS
      description VARCHAR2(200);
BEGIN

  description := '';

IF(pClassType = 'SYS') THEN   
        SELECT description
           INTO  description
        FROM TACDSYSD
        WHERE cdsysd_id = pId
        AND ROWNUM = 1
        AND cdsysm_id = (SELECT cdsysm_id
                                    FROM TACDSYSM
                                    WHERE list_type=pCodeType
                                        AND ROWNUM=1);
                                                                                    
  ELSIF(pClassType = 'USR') THEN 
       SELECT description
               INTO  description
            FROM TACDUSRD
            WHERE cdusrd_id = pId
            AND ROWNUM = 1
            AND comp_no = pCompNo
            AND cdusrm_id = (SELECT cdusrm_id
                                        FROM TACDUSRM
                                        WHERE dir_type=pCodeType
                                            AND comp_no = pCompNo
                                            AND ROWNUM=1);
                                            
                                        
  ELSIF(pClassType = 'USER') THEN 
       SELECT user_name
       INTO     description
       FROM    TAUSER
       WHERE  user_id = pId
           AND  ROWNUM = 1
           AND  comp_no = pCompNo ;                                                
                                        
  ELSIF(pClassType = 'EMP') THEN 
       SELECT emp_name
       INTO     description
       FROM    TAEMP
       WHERE  emp_id = pId
           AND  ROWNUM = 1
           AND  comp_no = pCompNo  ;     
                                        
  ELSIF(pClassType = 'DEPT') THEN 
       SELECT description
       INTO     description
       FROM    TADEPT
       WHERE  dept_id = pId
           AND  ROWNUM = 1
           AND  comp_no = pCompNo ;               
                                        
  ELSIF(pClassType = 'USRGRP') THEN 
       SELECT group_name
       INTO     description
       FROM    TAUSRGRP
       WHERE  usrgrp_id = pId
           AND  ROWNUM = 1
           AND  comp_no = pCompNo ;                                              
                                       
  ELSIF(pClassType = 'MENU') THEN 
       SELECT description
       INTO     description
       FROM    TAMENU
       WHERE  menu_id = pId
           AND  ROWNUM = 1 ;                                                              
                                       
  ELSIF(pClassType = 'VENDOR') THEN 
       SELECT description
       INTO     description
       FROM    TAVENDOR
       WHERE  vendor_id = pId
           AND  ROWNUM = 1 ;                                              
                                                                               
   END IF;
   RETURN description;
END;
/

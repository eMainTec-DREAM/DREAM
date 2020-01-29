CREATE OR REPLACE FUNCTION SFACODE_TO_DESC(pCode VARCHAR2, pCodeType VARCHAR2, pClassType VARCHAR2, pCompNo VARCHAR2, pUserLang VARCHAR2)
    RETURN VARCHAR2
IS
      description VARCHAR2(200);
      userLang VARCHAR2(10);
BEGIN

    userLang := pUserLang;   

   IF(userLang = '') THEN
    userLang := 'en';
   END IF;
   
   IF(pClassType = 'SYS') THEN 
   
        SELECT 
             nvl((select aa.key_name 
              from talang aa
              where  lang = userLang
                  and x.key_type = aa.key_type
                  and x.key_no = aa.key_no), description) as description
        INTO  description
        FROM TACDSYSD x
        WHERE x.CDSYSD_NO = pCode
             AND x.list_type=pCodeType
        ;                                    
   ELSIF(pClassType = 'USR') THEN
   
        SELECT description
           INTO  description
        FROM TACDUSRD
        WHERE CDUSRD_NO = pCode
        AND ROWNUM = 1
        AND comp_no = pCompNo
        AND cdusrm_id = (SELECT cdusrm_id
                                    FROM TACDUSRM
                                    WHERE dir_type=pCodeType
                                        AND comp_no = pCompNo
                                        AND ROWNUM=1);
                                        
   END IF;

   RETURN description;
END;
/

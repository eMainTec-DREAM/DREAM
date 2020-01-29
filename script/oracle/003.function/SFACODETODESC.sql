CREATE OR REPLACE FUNCTION SFACODETODESC(pCode VARCHAR2, pCodeType VARCHAR2, pClassType VARCHAR2, pCompNo VARCHAR2)
    RETURN VARCHAR2
IS
      description VARCHAR2(200);
BEGIN

   IF(pClassType = 'SYS' AND pCodeType='PM_TYPE') THEN   
    SELECT description
           INTO  description
        FROM TACDSYSD
        WHERE CDSYSD_NO = pCode
        AND ROWNUM = 1
        AND cdsysm_id IN (SELECT cdsysm_id
                                    FROM TACDSYSM
                                    WHERE list_type='PMI_TYPE' OR list_type='PMW_TYPE');
   ELSIF(pClassType = 'SYS'  AND  pCodeType!='PM_TYPE') THEN   
        SELECT description
           INTO  description
        FROM TACDSYSD
        WHERE CDSYSD_NO = pCode
        AND ROWNUM = 1
        AND cdsysm_id = (SELECT cdsysm_id
                                    FROM TACDSYSM
                                    WHERE list_type=pCodeType
                                        AND ROWNUM=1);
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

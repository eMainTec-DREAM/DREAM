CREATE OR REPLACE FUNCTION SFACODETOID(pCode VARCHAR2, pCodeType VARCHAR2, pClassType VARCHAR2, pCompNo VARCHAR2)
    RETURN NUMBER
IS
      return_id NUMBER(18);
BEGIN

   IF(pClassType = 'SYS') THEN   
    SELECT cdsysd_id
           INTO  return_id
        FROM TACDSYSD
        WHERE CDSYSD_NO = pCode
        AND ROWNUM = 1
        AND cdsysm_id = (SELECT cdsysm_id
                                    FROM TACDSYSM
                                    WHERE list_type=pCodeType
                                        AND ROWNUM=1);
   ELSIF(pClassType = 'USR') THEN
        SELECT cdusrd_id
           INTO  return_id
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

   RETURN return_id;
END;
/

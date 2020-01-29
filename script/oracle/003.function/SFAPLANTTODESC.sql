CREATE OR REPLACE FUNCTION SFAPLANTTODESC(pCompNo VARCHAR2, pPlant VARCHAR2)
    RETURN VARCHAR2
IS
      description VARCHAR2(200);
BEGIN
   SELECT description
   INTO  description
   FROM TAPLANT
   WHERE COMP_NO = pCompNo
       AND PLANT = pPlant
   ;
   RETURN description;
END;


/** 2016-10-20 Mseat 이동유형 세팅 */
/

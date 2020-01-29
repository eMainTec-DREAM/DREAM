CREATE OR REPLACE FUNCTION SFAGETDATE(pOffset NUMBER, pIntervalType VARCHAR2, pInterval NUMBER)
    RETURN TIMESTAMP
IS
    v_date TIMESTAMP;
BEGIN

    SELECT sys_extract_utc(systimestamp)+NUMTODSINTERVAL(pOffset,'HOUR')
    INTO v_date
    FROM dual;
    
    IF(pIntervalType = 'YEAR') THEN
        SELECT v_date+NUMTOYMINTERVAL(pInterval,'YEAR')
        INTO v_date
        FROM dual;
    ELSIF(pIntervalType = 'MONTH') THEN
        SELECT v_date+NUMTOYMINTERVAL(pInterval,'MONTH')
        INTO v_date
        FROM dual;
    ELSIF(pIntervalType = 'WEEK') THEN
        SELECT v_date+NUMTODSINTERVAL(pInterval*7,'DAY')
        INTO v_date
        FROM dual;
    ELSIF(pIntervalType = 'DAY') THEN
        SELECT v_date+NUMTODSINTERVAL(pInterval,'DAY')
        INTO v_date
        FROM dual;
    ELSIF(pIntervalType = 'HOUR') THEN
        SELECT v_date+NUMTODSINTERVAL(pInterval,'HOUR')
        INTO v_date
        FROM dual;
    ELSIF(pIntervalType = 'MINUTE') THEN
        SELECT v_date+NUMTODSINTERVAL(pInterval,'MINUTE')
        INTO v_date
        FROM dual;
    ELSIF(pIntervalType = 'SECOND') THEN
        SELECT v_date+NUMTODSINTERVAL(pInterval,'SECOND')
        INTO v_date
        FROM dual;
    ELSE
        SELECT v_date+NUMTODSINTERVAL(pInterval,'DAY')
        INTO v_date
        FROM dual;
    END IF;
    
    RETURN v_date;
END;
/
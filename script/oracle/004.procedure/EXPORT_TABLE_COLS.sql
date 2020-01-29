CREATE OR REPLACE PROCEDURE EXPORT_TABLE_COLS (P_TABLE_NAME IN VARCHAR2)
IS

  -----------------------------------------------------------------------
  -- 1. ????  :  
  -- 2. SQL-ID    :  PROC_EXPORT_TABLE_COLS
  -- 3. ????  :  JAVA VO ?? ????
  -----------------------------------------------------------------------


    v_cnt         INTEGER   := 0 ;
        v_start       INTEGER   := 1 ;
        v_len         INTEGER   := 0 ;
        v_pos         INTEGER   := 0 ;
        v_length      INTEGER   := 0 ;
        v_text        VARCHAR2 (32767) := '' ;
        col           VARCHAR2 (32767) := '';
BEGIN
      
    FOR lec_data IN (
        SELECT 'private ' preFix
             ,CASE  a.data_type 
              WHEN 'NUMBER' THEN 'int'
              WHEN 'DATE'   THEN 'Date'
              ELSE  'String' 
              END objectType
             ,LOWER(b.column_name) column_name
             ,b.comments comments
        FROM all_tab_cols a
            ,all_col_comments b 
       WHERE a.table_name=UPPER(P_TABLE_NAME)
         AND a.table_name = b.table_name
         AND a.column_name = b.column_name
    )LOOP
    
         v_text := TRIM(lec_data.column_name) ;
      col      := NULL;
      v_cnt    := 0 ;
           v_start  := 1 ;
          v_len    := 0 ;
          v_pos    := 0 ;
          v_length := 0 ;
          
      IF (v_text IS NULL) OR (v_text < '') THEN
        DBMS_OUTPUT.put_line('12 '||lec_data.prefix||lec_data.objectType||' '||lec_data.column_name||' //'||lec_data.comments);
          END IF ;
  
          v_len    := LENGTHB('_');
          v_length := LENGTHB(v_text);
  
          /*---------------------------------------------------------------*
         * AA ~t BB ~t CC ~t? ??? ?? => ??? ???? ????.  *
         *---------------------------------------------------------------*/
          IF SUBSTRB(v_text, v_length - v_len +1, v_len ) = '_' THEN
        v_text := SUBSTRB(v_text, 1, v_length - v_len );
           END IF;
  
        v_pos := INSTRB(v_text, '_', v_start);
     
         WHILE v_pos <> 0  LOOP
  
        v_cnt := v_cnt + 1;
        
        IF v_cnt = 1 THEN
           col := col||SUBSTRB(v_text, v_start, v_pos - v_start);
        ELSE
           col := col||UPPER(SUBSTR(SUBSTRB(v_text, v_start,  v_pos - v_start),1,1))||SUBSTRB(v_text, v_start+1,  v_pos - v_start -1);
        END IF;
        v_start      := v_start + (v_pos - v_start + v_len);
        v_pos        := INSTRB(v_text, '_', v_start); 
      END LOOP;
  
        v_cnt := v_cnt + 1;
      
      IF v_cnt = 1 THEN
         col := lec_data.column_name;
      ELSE
         col :=col||UPPER(SUBSTR(SUBSTRB(v_text, v_start, LENGTHB(v_text) + 1 - v_start),1,1))||SUBSTRB(v_text, v_start+1, LENGTHB(v_text) + 1 - v_start);
      END IF;
    
      DBMS_OUTPUT.put_line('/** '||lec_data.comments||' */');   
      DBMS_OUTPUT.put_line(lec_data.prefix||lec_data.objectType||' '||col||' = "";');   
    END LOOP;
END EXPORT_TABLE_COLS;
/

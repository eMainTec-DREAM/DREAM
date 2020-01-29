CREATE OR REPLACE PROCEDURE SP_IF_UPD_TAERPISSHIST    (
      v_comp_no     IN varchar2
     ,v_user_no      IN varchar2
) IS

   v_count number(4);
   v_dept_id number(18);
   v_emp_id number(18);
      
    CURSOR c_stock IS
       SELECT 
                erpissue_id,
                rec_date, -- 수신일자
                bukrs,  --회사코드
                werks,  --플랜트
                bwart,   --이동유형
                mjahr,  --생성년 (key)
                mblnr,  -- ERP문서번호 (key)
                zeile,   -- 품번
                budat,   --전기일
                matnr, --자재번호
                menge,  --수량
                verpr,  --출고단가
                dmbtr,  -- 금액
                meins,  -- 단위
                maktx  -- 품명
       FROM TXERPISSUE
       WHERE rec_date = TO_CHAR(SYSDATE,'yyyymmdd')
    ;
   
                                    
BEGIN

    
    FOR c1 IN c_stock LOOP

      SELECT COUNT(*)
       INTO v_count
       FROM TAPTERPISSHIST 
       WHERE 1=1
           AND erp_yyyy = c1.mjahr --생성년 
           AND erp_iss_no = c1.mblnr  --문서번호 
       ;
       
       IF v_count = 0 THEN
          
          INSERT INTO TAPTERPISSHIST(
              erp_iss_no -- 문서번호
              ,erp_yyyy --생성년
              ,bukrs  --회사코드
              ,werks -- 플랜트
              ,bwart  --이동유형
              ,zeile  --품번
              ,budat  --전기일 
              ,matnr  --자재번호 
              ,menge  --수량
              ,verpr  --출고단가
              ,dmbtr  --금액
              ,meins --단위 
              ,maktx  --품명 
          )VALUES(
                c1.mblnr,  -- ERP문서번호 (key)
                c1.mjahr,  --생성년 (key)
                c1.bukrs,  --회사코드
                c1.werks,  --플랜트
                c1.bwart,   --이동유형
                c1.zeile,   -- 품번
                c1.budat,   --전기일
                c1.matnr, --자재번호
                c1.menge,  --수량
                c1.verpr,  --출고단가
                c1.dmbtr,  -- 금액
                c1.meins,  -- 단위
                c1.maktx  -- 품명
          );
            
       ELSE
          
             UPDATE  TAPTERPISSHIST
                  SET    bukrs = c1.bukrs,  --회사코드
                            werks = c1.werks,  --플랜트
                            bwart= c1.bwart,   --이동유형
                            zeile = c1.zeile,   -- 품번
                            budat = c1.budat,   --전기일
                            matnr = c1.matnr, --자재번호
                            menge = c1.menge,  --수량
                            verpr = c1.verpr,  --출고단가
                            dmbtr = c1.dmbtr,  -- 금액
                            meins = c1.meins,  -- 단위
                            maktx = c1.maktx  -- 품명
            WHERE 1=1
                 AND erp_yyyy = c1.mjahr --생성년 
                 AND erp_iss_no = c1.mblnr  --문서번호 
           ;
            
       END IF;
               
    END LOOP;
    
    UPDATE TABATPGM SET 
         exe_by = (SELECT user_id 
                        FROM TAUSER 
                        WHERE comp_no = v_comp_no 
                            AND user_no = v_user_no 
                            AND ROWNUM = 1
                       )
        ,last_exe_date = TO_CHAR(SYSDATE,'yyyymmdd')
        ,last_exe_time = TO_CHAR(SYSDATE,'yyyymmddhh24miss')
    WHERE comp_no = v_comp_no
        AND batpgm_no = 'TAERPISSHIST'
    ;
    
END;
/

CREATE OR REPLACE PROCEDURE SP_IF_UPD_TAPARTS     (
      v_comp_no     IN varchar2
     ,v_user_no      IN varchar2
) IS
   v_partId number(18);   
   v_count number(4);

    CURSOR c_data IS
     SELECT
            TRIM(bukrs) AS bukrs , --회사코드
            TRIM(werks) AS werks, --플랜트
            TRIM(matnr) AS matnr, -- 품번
            TRIM(maktx) AS maktx, --품명
            TRIM(groes) AS groes, --규격
            TRIM(mtart) AS mtart, --자재유형코드
            TRIM(mtbez) AS mtbez, -- 자재유형명
            TRIM(matkl) AS matkl, -- 자재그룹코드
            TRIM(wgbez) AS wgbez, --자재그룹명
            TRIM(lgfsb) AS lgfsb, --저장위치
            TRIM(meins) AS meins, --단위
            TRIM(bklas) AS bklas, --평가 Calss 코드
            TRIM(erdat) AS erdat, --생성 또는 수정일
            TRIM(lvorm) AS lvorm, --삭제 지시자 
            TRIM(wlvorm) AS wlvorm, --플랜트 레벨 삭제 지시자
            TRIM(ifresult) AS ifresult, --IF전송 FLAG
            TRIM(ifdate) AS ifdate, --IF생성일자
            TO_CHAR(SYSDATE,'yyyy-mm-dd hh24:mi:ss') received_date
        FROM MWARE_EAI.TXERPPARTS
        WHERE 1=1
            --AND ifresult IS NULL
        ;

BEGIN

    FOR c1 IN c_data LOOP

       SELECT COUNT(*)
       INTO v_count
       FROM TAPARTS 
       WHERE 1=1
           AND comp_no = v_comp_no
           AND TRIM(part_no) = c1.matnr
       ;
       
       IF v_count = 0 THEN
          
       SELECT sqapart_id.nextval INTO v_partId
        FROM DUAL;
  
          INSERT INTO TAPARTS(
              comp_no
              ,part_id
              ,part_no
              ,description
              ,is_inpart
              ,part_categ
              ,is_use
              ,part_group
              ,full_desc
              ,vendor_code
             /* ,pt_size
              ,uom
              ,full_desc
              ,MODEL
              ,maker
              ,USAGE
              ,last_price
              ,plf_type
              ,pco_type
              ,seller
              ,lead_time
              ,is_use
              ,REMARK
              ,upd_date
              ,upd_by
              ,mro_type
              ,kind
              ,var_class
              ,vendor_desc*/
          )VALUES(
              v_comp_no
              ,v_partId
              ,c1.matnr --품번
              ,c1.maktx  --품명 
              ,'N'
              ,'SPPT' --자재와 공구 구분 (자재)
              ,'Y'
              ,c1.wgbez
              ,c1.maktx  --품명 
              ,v_partId
          /*    ,c1.groes  --규격 
              ,c1.meins  --단위 
              ,c1.maktx||'|'||c1.groes||'|'||c1.meins
              ,''
              ,''
              ,''
              ,''
              ,''
              ,''
              ,''
              ,''
              ,'Y' --isUse
              ,''
              ,''
              ,''
              ,''
              ,''
              ,''
              ,''
              ,'' */
          );
          
          INSERT INTO TAPTSTOCK                            
        (comp_no , wcode_id, part_id, part_grade,        
        stock_qty, bin_no, unit_price)                
        select x.comp_no,y.wcode_id, x.part_id, z.cdsysd_no,0,'',x.last_price
        from taparts x, tawarehouse y, (select * from tacdsysd where list_type='PART_GRADE') z
        where x.comp_no  = y.comp_no        
        and x.comp_no =    v_comp_no                
        and x.part_id =   v_partId            
        and z.is_use=        'Y'                
        and y.wh_categ=    'PART'            
        and y.is_use=        'Y';  

           UPDATE MWARE_EAI.TXERPPARTS SET
                ifresult = 'Y'
                ,received_date = c1.received_date
            WHERE TRIM(bukrs) = c1.bukrs
                 AND TRIM(werks) = c1.werks
                 AND TRIM(matnr) = c1.matnr
            ;
            
       ELSE
          
             UPDATE  TAPARTS
                  SET    part_no = c1.matnr
                            ,part_group = c1.wgbez
                          /* ,description = c1.maktx
                            ,pt_size = c1.groes
                            ,uom = c1.meins
                            ,full_desc = c1.maktx||'|'||c1.groes||'|'||c1.meins
                            ,is_use = 'Y'
                            ,part_group = c1.matkl */
                            ,is_inpart = 'N'
                            ,part_categ='SPPT'
            WHERE 1=1
                AND comp_no = v_comp_no
                AND TRIM(part_no) = c1.matnr
           ;
           
            UPDATE MWARE_EAI.TXERPPARTS SET
                ifresult = 'Y'
                ,received_date = c1.received_date
            WHERE TRIM(bukrs) = c1.bukrs
                 AND TRIM(werks) = c1.werks
                 AND TRIM(matnr) = c1.matnr
            ;
            
       END IF;
       


    END LOOP;
    
MERGE INTO TXERPPARTS a
    USING( SELECT
            TRIM(bukrs) AS bukrs , --회사코드
            TRIM(werks) AS werks, --플랜트
            TRIM(matnr) AS matnr, -- 품번
            TRIM(maktx) AS maktx, --품명
            TRIM(groes) AS groes, --규격
            TRIM(mtart) AS mtart, --자재유형코드
            TRIM(mtbez) AS mtbez, -- 자재유형명
            TRIM(matkl) AS matkl, -- 자재그룹코드
            TRIM(wgbez) AS wgbez, --자재그룹명
            TRIM(lgfsb) AS lgfsb, --저장위치
            TRIM(meins) AS meins, --단위
            TRIM(bklas) AS bklas, --평가 Calss 코드
            TRIM(erdat) AS erdat, --생성 또는 수정일
            TRIM(lvorm) AS lvorm, --삭제 지시자 
            TRIM(wlvorm) AS wlvorm, --플랜트 레벨 삭제 지시자
            TRIM(ifresult) AS ifresult, --IF전송 FLAG
            TRIM(ifdate) AS ifdate, --IF생성일자
            TO_CHAR(SYSDATE,'yyyy-mm-dd hh24:mi:ss') received_date
        FROM MWARE_EAI.TXERPPARTS
        WHERE 1=1
        ) b
     ON(TRIM(a.bukrs) = b.bukrs
           AND TRIM(a.werks) = b.werks
           AND TRIM(a.matnr) = b.matnr)
     WHEN MATCHED THEN
           UPDATE SET 
                  --  a.bukrs= b.bukrs , --회사코드
                 --   a.werks= b.werks, --플랜트
                 --   a.matnr= b.matnr, -- 품번
                    a.maktx= b.maktx, --품명
                    a.groes= b.groes, --규격
                    a.mtart= b.mtart, --자재유형코드
                    a.mtbez= b.mtbez, -- 자재유형명
                    a.matkl= b.matkl, -- 자재그룹코드
                    a.wgbez= b.wgbez, --자재그룹명
                    a.lgfsb= b.lgfsb, --저장위치
                    a.meins= b.meins, --단위
                    a.bklas= b.bklas, --평가 Calss 코드
                    a.erdat= b.erdat, --생성 또는 수정일
                    a.lvorm= b.lvorm, --삭제 지시자 
                    a.wlvorm= b.wlvorm, --플랜트 레벨 삭제 지시자
                    a.ifresult= b.ifresult, --IF전송 FLAG
                    a.ifdate= b.ifdate --IF생성일자
               --     a.received_date = b.received_date
        WHEN NOT MATCHED THEN                                         
              INSERT ( a.bukrs , --회사코드
                        a.werks, --플랜트
                        a.matnr, -- 품번
                        a.maktx, --품명
                        a.groes, --규격
                        a.mtart, --자재유형코드
                        a.mtbez, -- 자재유형명
                        a.matkl, -- 자재그룹코드
                        a.wgbez, --자재그룹명
                        a.lgfsb, --저장위치
                        a.meins, --단위
                        a.bklas, --평가 Calss 코드
                        a.erdat, --생성 또는 수정일
                        a.lvorm, --삭제 지시자 
                        a.wlvorm, --플랜트 레벨 삭제 지시자
                        a.ifresult, --IF전송 FLAG
                        a.ifdate, --IF생성일자
                        a.received_date
                     )                                                    
              VALUES (b.bukrs , --회사코드
                        b.werks, --플랜트
                        b.matnr, -- 품번
                        b.maktx, --품명
                        b.groes, --규격
                        b.mtart, --자재유형코드
                        b.mtbez, -- 자재유형명
                        b.matkl, -- 자재그룹코드
                        b.wgbez, --자재그룹명
                        b.lgfsb, --저장위치
                        b.meins, --단위
                        b.bklas, --평가 Calss 코드
                        b.erdat, --생성 또는 수정일
                        b.lvorm, --삭제 지시자 
                        b.wlvorm, --플랜트 레벨 삭제 지시자
                        b.ifresult, --IF전송 FLAG
                        b.ifdate, --IF생성일자
                        b.received_date
                     )       
      ;

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
        AND batpgm_no = 'TAPARTS'
    ;

END;
/

select 
'insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES(NEXT value FOR sqabutton_id, '''||button_no||''', '''||description||''', '''||btn_img||''', '''||ord_no||''', '''||is_use||''' ); ' 
from tabutton
;

---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-11   박철완 최초 생성*/ 
---------------------------------------------------------------------------------------------------------------------------------------------------
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(NEXT VALUE FOR sqabutton_id, 'SEARCH','자료검색하기','','100','Y')
go 
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(NEXT VALUE FOR sqabutton_id, 'INTERFACE','I/F 수신','','490','Y')
go
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(NEXT VALUE FOR sqabutton_id, 'CONFIRM','작업완료 확정하기','','500','Y')
go
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(NEXT VALUE FOR sqabutton_id, 'REPORT','레포트파일변환하기(FO)','','510','Y')
go
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(NEXT VALUE FOR sqabutton_id, 'PDF','출력하기','','520','Y')
go 
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(NEXT VALUE FOR sqabutton_id, 'SELECT','데이타선택하기','','530','Y')
go
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(NEXT VALUE FOR sqabutton_id, 'CHPW','비밀번호변경하기','','540','Y')
go
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(NEXT VALUE FOR sqabutton_id, 'HISTORY','변경이력보기','','550','Y')
go
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(NEXT VALUE FOR sqabutton_id, 'PHOTO','사진등록하기','','570','Y')
go 
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(NEXT VALUE FOR sqabutton_id, 'REC_COMPLETE','입고완료 확정하기','','630','Y')
go 
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(NEXT VALUE FOR sqabutton_id, 'REC_CANCEL','입고취소하기','','640','Y')
go 
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(NEXT VALUE FOR sqabutton_id, 'REPAIR_V','외주수리완료처리하기','','650','Y')
go
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(NEXT VALUE FOR sqabutton_id, 'REPAIR_L','사내수리완료처리하기','','660','Y')
go
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(NEXT VALUE FOR sqabutton_id, 'REPAIR_X','수리불가처리하기','','670','Y')
go
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(NEXT VALUE FOR sqabutton_id, 'CANCEL','수리취소하기','','680','Y')
go 
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(NEXT VALUE FOR sqabutton_id, 'FIX','일정확정하기','','800','Y')
go 
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(NEXT VALUE FOR sqabutton_id, 'SAVENEW','저장후신규생성하기','','860','Y')
go
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(NEXT VALUE FOR sqabutton_id, 'EXCEL','Excel다운로드','','910','Y')
go
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(NEXT VALUE FOR sqabutton_id, 'OPEN','세부정보열기','','920','Y')
go 
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(NEXT VALUE FOR sqabutton_id, 'CREATE','신규생성하기','','930','Y')
go
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(NEXT VALUE FOR sqabutton_id, 'DELETE','삭제하기','','940','Y')
go 
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(NEXT VALUE FOR sqabutton_id, 'SAVE','자료저장하기','','945','Y')
go
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(NEXT VALUE FOR sqabutton_id, 'SETTING','Grid사용자설정','','950','Y')
go
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(NEXT VALUE FOR sqabutton_id, 'CLOSE','화면닫기','','990','Y')
go 
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(NEXT VALUE FOR sqabutton_id, 'PLAY','실행하기','','991','Y')
go 
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(NEXT VALUE FOR sqabutton_id, 'WOEX','WO발행','','992','Y')
go 
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-12   김정우 추가작업 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO TABUTTON (button_id, button_no, description, is_use, ord_no) VALUES (NEXT VALUE FOR sqabutton_id, 'BREAK','설비고장검색하기','Y',994)
go
INSERT INTO TABUTTON (button_id, button_no, description, is_use, ord_no) VALUES (NEXT VALUE FOR sqabutton_id, 'CHPART','부품교체검색하기','Y',995)
go
INSERT INTO TABUTTON (button_id, button_no, description, is_use, ord_no) VALUES (NEXT VALUE FOR sqabutton_id, 'REPAIR','예방수리검색하기','Y',996)
go
INSERT INTO TABUTTON (button_id, button_no, description, is_use, ord_no) VALUES (NEXT VALUE FOR sqabutton_id, 'INSPECTION','예방점검검색하기','Y',997)
go
INSERT INTO TABUTTON (button_id, button_no, description, is_use, ord_no) VALUES (NEXT VALUE FOR sqabutton_id, 'STANDARD','예방기준확인하기','Y',998)
go
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-12   김정우 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------

/* 2016-06-22 노정현 결재 승인요청 버튼 추가 */
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'APPROVAL', '승인요청', 'Y', '480')
go
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'APPRLINE', '결재선 선택', 'Y', '485')
go
   /** 2016-06-27 노정현 버튼 추가  */
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'APPRACTION', '결재처리', 'Y', '500')
go
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'APPRHIST', '결재이력', 'Y', '510')
go
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'REJECT', '반려', 'Y', '515')
go
   /** 2016-06-28 김정우 필드가져오기 버튼 추가 */
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'IMP_FIELD', '필드가져오기', 'Y', '520')
go
   /** 2016-07-06 김정우 설비마스터 버튼 추가 */
   
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'EQBM', '설비고장', 'Y', '530')
go
   
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'PMMSTR', '예방작업기준', 'Y', '540')
go
   
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'PMWO', '예방작업', 'Y', '550')
go
   
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'USEPARTS', '사용부품', 'Y', '560')
go


INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'PMSTD', '기준서', 'Y', '530')
go
   /** 2016-07-13 김정우  구매신청 버튼 추가 */
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'BUYREQ', '구매신청', 'Y', '570')
go

/** 2016-07-21 노정현 버튼 추가 */
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'CONFIRMISS', '출고처리', 'Y', '580')
go
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'CANCELISS', '출고취소', 'Y', '590')
go
   ---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-07-25   김정우 자재내역확인 버튼 추가 추가작업  */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'PARTHIST', '자재내역보기', 'Y', '999')
go
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-07-25   김정우 외주수리의뢰 버튼 추가 추가작업  */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'REQREPVEN', '외주수리의뢰', 'Y', '999')
go
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-07-28   김정우 근무지정, 휴무지정 추가작업  */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
    INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'DAYON', '선택항목 근무로 지정', 'Y', '999')
go
   INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'DAYOFF', '선택항목 휴무로 지정', 'Y', '999')
go
   
   ---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-08-2   이규선 출력버튼 추가작업 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
   INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'PRINT', '출력하기', 'Y', '993')
go
   /** 2016-09-01 김정우 버튼 추가 */
   
   INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'EQPDF', '설비이력출력', 'Y', '994')
go
   INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'WOPDF', '작업결과출력', 'Y', '995')
go
   
       INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'CONFIRMRTN', '반납확정', 'Y', '994')
go
   /** 2016-09-05 김정우 버튼 추가 */
   
   INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'PTREQPDF', '구매요청서출력', 'Y', '996')
go
   INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'WODAILYPDF', '작업일지출력', 'Y', '997')
go
   
 INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'RELOAD', '로딩', 'Y', '810')
go
   /** 2016-09-06 이규선 버튼 추가 */
   INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'CONFIRMDIS', '폐기확정', 'Y', '994')
go
   /** 2016-09-07 김정우 접수 버튼추가 */
   
   INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'RECCONFIRM', '접수확인', 'Y', '999')
go
   
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'COLSETTING', '항목설정', 'Y', '999')
go
   
    INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'CHECKDIS', '폐기처리', 'Y', '999')
go
   
       INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'CHECKRETURN', '반납', 'Y', '999')
go
   
    /** 20160926 김정우 슬로박지표 추가 */
       INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'REPORTPRINT', '레포트 출력', 'Y', '999')
go
   
       /** 20161011 이규선 일저재조정 추가 */
          INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'SCHEDRESET', '일정재조정', 'Y', '999')
go
   
             INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'SCHDRSTCON', '일정재조정확정', 'Y', '999')
go
   
   
      /** 2016-10-13 김정우 버튼 추가 */
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'POCOMPLETE', '발주완료', 'Y', '999')
go
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'RECCOMPLETE', '입고완료', 'Y', '999')
go
   /**2016-10-19 김정우 버튼 추가 */
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'TRANSFERBSTOCK', '무상입고완료', 'Y', '999')
go
   
   /** 2016-11-16 김정우 복사 버튼 */
   
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'COPYCREATE', '복사', 'Y', '999')
go
   
   /** 2016-12-16 이규선 출력 버튼 */   
   INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'MTLPRPRINT', '몬테레이PR출력', 'N', '999')
go
      /** 2017-01-11 이규선 작업일정 버튼 */  
   INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'PMSCHED', '작업일정', 'Y', '999')
go
   
-----------------------------------------------------------------------------
--------------------------------유한킴벌리 patch완료 2017.01.13일-------------------
-----------------------------------------------------------------------------
   
    
   /**2017-03-03 김정우 추가 */
   
   INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'PTSTOCKPDF', 'STOCK of PARTS', 'Y', '999')
go
   
     /** 2017-03-03 이규선 출력 버튼 */   
 INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'BZPRPRINT', '브라질PR출력', 'N', '999')
go
   
 -----------------------------------------------------------------------------
--------------------------------유한킴벌리 patch완료 2017.03.15일-------------------
-----------------------------------------------------------------------------   
   /** 2017-03-16 김정우 추가 */
   
 INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'LISTBARCODE', 'LIST BARCODE', 'N', '999')
go
   
   
       /** 2017-03-20 이규선 출력 버튼 */   
 INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (NEXT VALUE FOR sqabutton_id, 'BZSHIFTPRINT', '브라질Daily(Shift)출력', 'N', '999')
go
   
   
 -----------------------------------------------------------------------------
--------------------------------mseat patch완료 2017.04.04일-------------------
----------------------------------------------------------------------------- 
-----------------------------------------------------------------------------
--------------------------------다이모스체코 patch완료 2017.04.04일-------------------
-----------------------------------------------------------------------------
-----------------------------------------------------------------------------
--------------------------------다이모스 슬로박 patch완료 2017.04.04일-------------------
-----------------------------------------------------------------------------   
-----------------------------------------------------------------------------
--------------------------------다이모스 조지아 patch완료 2017.03.24일-------------------
-----------------------------------------------------------------------------
-----------------------------------------------------------------------------
--------------------------------다이모스 몬테레이 patch완료 2017.03.24일-------------------
-----------------------------------------------------------------------------
-----------------------------------------------------------------------------
--------------------------------다이모스 slp patch완료 2017.03.24일-------------------
-----------------------------------------------------------------------------
   -----------------------------------------------------------------------------
--------------------------------다이모스 조지아 patch완료 2017.03.24일-------------------
-----------------------------------------------------------------------------
-----------------------------------------------------------------------------
--------------------------------다이모스 브라질 patch완료 2017.03.24일-------------------
-----------------------------------------------------------------------------

   
   -----------------------------------------------------------------------------
--------------------------------유한킴벌리 patch완료 2017.04.12일-------------------

INSERT INTO TABUTTON
	(BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO) 
VALUES
	(NEXT VALUE FOR sqabutton_id, 'SENDMAIL','메일발송','Y','993')
go	
	 /** 2017-08-08 김정우 추가 */
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (sqabutton_id.NEXTVAL, 'MATRIXVIEW', 'Matrix 보기', 'Y', '999');
   
    /** 2017-08-25 김영주 추가 */
INSERT INTO TABUTTON
    (button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES
    (NEXT VALUE FOR sqabutton_id, 'ASSCOMPLETED','평가 완료','','500','Y');
    
       /** 2017-08-30 차한결 추가  */
INSERT INTO tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqabutton_id, 'ADDFENV','운전환경추가','','900','Y');
INSERT INTO tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqabutton_id, 'ADDEMP','분석자추가','','900','Y'); 
INSERT INTO tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqabutton_id, 'ADDEQ','설비설정추가','','900','Y');
INSERT INTO tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqabutton_id, 'ADDASMB','설비부위추가','','900','Y');

/** 2017-09-20 이근환 추가  */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use)
values(NEXT VALUE FOR sqabutton_id, 'REQUEST','요청','','450','Y'); 
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use)
values(NEXT VALUE FOR sqabutton_id, 'RECEIVE','접수','','450','Y'); 

/** 2017-09-21 김영주 추가  */
INSERT INTO TABUTTON
    (button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES
    (NEXT VALUE FOR sqabutton_id, 'PTRLCOMPLETED','순회완료','','500','Y');


/** 2017-09-21 김정우 */
INSERT INTO tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqabutton_id, 'APPREQUEST','결재요청','','900','Y');

/** 2017-09-21 김정우 */
INSERT INTO tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqabutton_id, 'COPYCALIBSTDVALUE','교정표준값복사','','900','Y');
INSERT INTO tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqabutton_id, 'REGBATCHCALIBVAL','측정값일괄등록','','900','Y');

/** 2017-09-22 차한결 */
INSERT INTO tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqabutton_id, 'EMPPWRESET','전사원비번리셋','','950','Y');

/** 2017-09-28 김영주 추가 */
INSERT INTO TABUTTON
    (button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES
    (NEXT VALUE FOR sqabutton_id, 'COMPLETED','완료','','500','Y');
    
/** 2017-10-11 김정우 추가 */
INSERT INTO TABUTTON (button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES (NEXT VALUE FOR sqabutton_id, 'REVISION','개정','','500','Y');
INSERT INTO TABUTTON (button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES (NEXT VALUE FOR sqabutton_id, 'REVISIONHISTORY','개정이력','','500','Y');

/** 2017-10-17 이근환 추가 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(NEXT VALUE FOR sqabutton_id, 'EQLCC','설비별 보기','','100','Y'); 

/** 2017-10-19 이근환 추가 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(NEXT VALUE FOR sqabutton_id, 'RESETPW','비밀번호리셋','','500','Y'); 

/** 2017-10-20 차한결 추가 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(NEXT VALUE FOR sqabutton_id, 'REVCOMPLETED','제개정완료','','950','Y'); 
/** 2017-11-27 김정우 */
INSERT INTO tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES(NEXT value FOR sqabutton_id, 'INSLIST','예방점검','','999','Y'); 

/** 2017-12-07 김영주 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES( NEXT VALUE FOR sqabutton_id, 'EXEINS','점검일정발행','','993','Y');

 /** 2017-12-07 이근환 */
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(NEXT VALUE FOR sqabutton_id, 'RESULT','점검결과열기','','500','Y'); 

/** 2017-12-11 김영주 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqabutton_id, 'refEqList','관련설비조회','','500','Y');

/** 2017-12-15 이근환 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(NEXT VALUE FOR sqabutton_id, 'RPLLIST','정기교체','','500','Y'); 

/** 2017-12-19 김영주 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqabutton_id, 'ACWO','조치W/O 열기','','510','Y');

/** 2017-12-20 이근환 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(NEXT VALUE FOR sqabutton_id, 'SETSYSY','시스템숨김(Y)','','200','Y'); 
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(NEXT VALUE FOR sqabutton_id, 'SETSYSN','시스템숨김(N)','','210','Y'); 

/** 2017-12-22 김영주 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES( NEXT VALUE FOR sqabutton_id, 'CONFIRMWOPLAN','작업계획확정','','500','Y');


/*2017-12-24 박철완**/
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(NEXT VALUE FOR sqabutton_id, 'REFERENCE','참조확인','','210','Y'); 

/** 2017-12-28 김정우 */
INSERT INTO tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES(NEXT VALUE FOR sqabutton_id, 'INSSCHED','점검일정','','999','Y'); 

/** 2017-12-28 김영주 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES( NEXT VALUE FOR sqabutton_id, 'WOPLAN','작업계획보기','','500','Y');

/** 2017-12-28 이근환 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(NEXT VALUE FOR sqabutton_id, 'HELP','도움말','','990','Y'); 

/** 2018-01-04 김영주 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES( NEXT VALUE FOR sqabutton_id, 'ROLLBACK','테이블생성취소','','500','Y');

/** 2018-01-04 이근환 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(NEXT VALUE FOR sqabutton_id, 'TRACELOG','TraceLog','','970','Y'); 

/** 2018-01-04 김정우 추가 */
INSERT INTO tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES(NEXT value FOR sqabutton_id, 'CANTWORK','작업불가','','990','Y');    

/** 2018-01-05 김영주 추가 */
UPDATE TABUTTON SET button_no = 'DROP' WHERE description = '테이블생성취소';

/** 2018-01-05 이근환 추가 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(NEXT value FOR sqabutton_id, 'SELECTFILE','파일선택','','500','Y'); 

/** 2018-01-12 이근환 추가 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(NEXT value FOR sqabutton_id, 'UPLOAD','업로드','','500','Y'); 
/**2018-01-13 김정우 추가 */
INSERT INTO tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES(NEXT value FOR sqabutton_id, 'PMCHECKLIST','예방점검표','','999','Y'); 

/** 2018-01-25 김정우 추가 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES(NEXT value FOR sqabutton_id, 'EQALLLISTPRINT','전체리스트출력','','999','Y');

INSERT INTO tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES(NEXT value FOR sqabutton_id, 'EQINFOPDF','설비등록카드','','999','Y'); 

/** 2018-01-31 이근환 추가 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(NEXT value FOR sqabutton_id, 'TEMPLATE','템플릿 가져오기','','450','Y'); 

/** 2018-02-05 김영주 추가 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(NEXT VALUE FOR sqabutton_id, 'INVTPHASE','투자진행하기','','500','Y'); 

/** 2018-02-05 이근환 추가 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(NEXT value FOR sqabutton_id, 'CART','카트담기','','500','Y'); 

/** 2018-02-06 김영주 추가 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES( NEXT VALUE FOR sqabutton_id, 'REQDETAIL','요청서보기','','500','Y'); 

/** 2018-02-12 김정우 추가 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES( NEXT VALUE FOR sqabutton_id, 'GRPRINT','출고요청서','','500','Y');

/** 2018-02-12 이근환 추가 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(NEXT value FOR sqabutton_id, 'CREWOPLAN','작업계획발행','','500','Y'); 

/**  2018-03-07 매일유업 반영 */

/** 2018-03-09 김영주 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES( NEXT VALUE FOR sqabutton_id, 'NOTICE','공지','','500','Y'); 

/**  2018-03-13 매일유업 반영 */

/** 2018-03-14 김정우 */


insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(next value for sqabutton_id, 'WORKRESLIST','요청접수리스트','','500','Y'); 

/** 2018-03-14 이근환 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(next value for sqabutton_id, 'REGBATCH','일괄등록','','500','Y'); 

/**  2018-03-14 매일유업 반영 */

/** 2018-03-15 김정우 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(next value for sqabutton_id, 'WORKLISTPRINT','작업목록출력',NULL,'500','Y'); 

/**  2018-03-20 매일유업 반영 */
/**  2018-04-18 매일유업 반영 */

/** 2018-04-20 김영주 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(NEXT VALUE FOR sqabutton_id, 'SELECTEDPRINT','선택목록출력','','500','Y'); 

/**  2018-04-25 매일유업 반영 */
/**  2018-05-02 매일유업 반영 */

/** 2018-05-03 김영주 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(NEXT VALUE FOR sqabutton_id, 'WORKREQ','작업요청','','500','Y'); 
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(NEXT VALUE FOR sqabutton_id, 'WORKPLAN','작업계획','','500','Y'); 

/**  2018-05-09 매일유업 반영 */
/**  2018-05-25 매일유업 반영 */

/** 2018-05-29 이근환 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(next value for sqabutton_id, 'REVERSE','작업완료 취소하기','','500','Y'); 

/**  2018-06-01 매일유업 반영 */

/** 2018-06-07 김영주 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(NEXT VALUE FOR sqabutton_id, 'ADD','추가','','520','Y'); 
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(NEXT VALUE FOR sqabutton_id, 'EDIT','Edit Grid','','925','Y'); 
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(NEXT VALUE FOR sqabutton_id, 'DEL','삭제','','530','Y'); 
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(NEXT VALUE FOR sqabutton_id, 'EDITCNCL','수정취소','','540','Y'); 

/**  2018-06-19 매일유업 반영 */
/**  2018-06-20 매일유업 반영 */
/**  2018-06-22 매일유업 반영 */
/**  2018-06-27 매일유업 반영 */

/** 2018-06-27 김정우 */

INSERT INTO TABUTTON(button_id, button_no, description, ord_no, is_use) VALUES(next value for sqabutton_id, 'BEFOREPHOTO','전사진','999','Y');
INSERT INTO TABUTTON(button_id, button_no, description, ord_no, is_use) VALUES(next value for sqabutton_id, 'AFTERPHOTO','전사진','999','Y');

/** 2018-06-28 이지수 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(NEXT VALUE FOR sqabutton_id, 'INVTCREATE','투자목록생성','','500','Y'); 
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(NEXT VALUE FOR sqabutton_id, 'INVTSELECT','기존투자연결','','500','Y'); 

/**  2018-07-02 매일유업 반영 */

/** 2018-07-03 김영주 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES( NEXT VALUE FOR sqabutton_id, 'ADDPHASE','절차추가하기','','500','Y'); 
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES( NEXT VALUE FOR sqabutton_id, 'ADDNEWEQUIP','신규설비생성','','500','Y'); 
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES( NEXT VALUE FOR sqabutton_id, 'ADDEQUIP','기존설비','','510','Y'); 

/**  2018-07-03 매일유업 반영 */

/** 2018-07-05 김영주 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES( NEXT VALUE FOR sqabutton_id, 'STDPT','표준부품선택','','500','Y'); 
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES( NEXT VALUE FOR sqabutton_id, 'STDSP','표준제원선택','','500','Y'); 

/**  2018-07-12 매일유업 반영 */

/**  2018-07-13 DREAM 반영 */

/** 2018-07-15 양소영 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(NEXT VALUE FOR sqabutton_id, 'STDASMB','표준부위선택','','500','Y'); 

/**  2018-07-19 매일유업 반영 */
/**  2018-07-19 국도 반영 */

/** 2018-07-20 김영주 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES( NEXT VALUE FOR sqabutton_id, 'WOCREATE','신규작업생성','','500','Y'); 
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES( NEXT VALUE FOR sqabutton_id, 'WOSELECT','기존작업선택','','510','Y');
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES( NEXT VALUE FOR sqabutton_id, 'WOPLANCREATE','신규계획생성','','500','Y'); 
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES( NEXT VALUE FOR sqabutton_id, 'WOPLANSELECT','기존계획선택','','510','Y');

/** 2018-07-24 국도 반영 */
/** 2018-07-24 DREAM 반영 */
/** 2018-07-26 국도 반영 */
/** 2018-07-27 매일유업 반영 */

/** 2018-07-31 김정우 추가 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(NEXT VALUE FOR sqabutton_id, 'DATAUPLOAD','데이터 반영','','','Y'); 

/** 2018-08-02 김영주 추가 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(NEXT VALUE FOR sqabutton_id, 'CREPLANINVT','계획투자생성','','500','Y'); 

/** 2018-08-06 이지수 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES( NEXT VALUE FOR sqabutton_id, 'INTFEXE','즉시수신','','150','Y');

/** 2018-08-07 국도 반영 */
/** 2018-08-08 국도 반영 */

/**  2018-08-08 이근환 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(next value for sqabutton_id, 'PTSTOCK','재고조회','','490','Y'); 

/** 2018-08-09 국도 반영 */
/** 2018-08-10 국도 반영 */

/** 2018-08-13 김남현 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(NEXT VALUE FOR sqabutton_id, 'CONTENTSSELECT','CONTENTS 선택','','500','Y');

/** 2018-08-17 국도 반영 */

/** 2018-08-20 이지수 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES( NEXT VALUE FOR sqabutton_id, 'CANCELINVT','투자완료 취소하기','','300','Y');

/** 2018-08-21 DREAM 반영 */

/** 2018-08-21 이근환 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(next value for sqabutton_id, 'CONFIRMPTSTKMOVE','재고이동 확정하기','','500','Y'); 
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(next value for sqabutton_id, 'CANCELPTSTKMOVE','재고이동 취소하기','','500','Y'); 

/** 2018-08-22 매일유업 반영 */
/** 2018-08-28 국도 반영 */

/** 2018-09-04 이지수 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES( NEXT VALUE FOR sqabutton_id, 'SCORECOPY','평가결과복사','','100','Y');
 
/** 2018-09-04 국도 반영 */
/** 2018-09-06 매일유업 반영 */

/** 2018-09-07 이근환 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(next value for sqabutton_id, 'SENDUSERINFOMAIL','사용자 정보 메일 발송','','500','Y'); 

/** 2018-09-10 국도 반영 */

/** 2018-09-07 양소영 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(NEXT VALUE FOR sqabutton_id, 'WOREQCREATE','신규요청생성','','500','Y'); 
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(NEXT VALUE FOR sqabutton_id, 'WOREQSELECT','기존요청선택','','510','Y'); 

/** 2018-09-20 국도 반영 */
/** 2018-09-20 매일유업 반영 */
/** 2018-10-12 국도화학 반영 */

/** 2018-10-12 김은아 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(NEXT VALUE FOR sqabutton_id, 'WOPRWEXECUTE','작업대기','','500','Y'); 
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(NEXT VALUE FOR sqabutton_id, 'WOPRPEXECUTE','작업중','','500','Y'); 

/** 2018-10-18 국도화학 반영 */

/** 2018-10-19 이지수 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES( NEXT VALUE FOR sqabutton_id, 'PNSELECT','현장신청부품','','200','Y');

/** 2018-10-24 김남현 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(NEXT VALUE FOR sqabutton_id, 'CALIBREPORTPRINT','레포트출력(교정)','','500','Y');

/** 2018-10-25 국도화학 반영 */
/** 2018-10-26 국도화학 반영 */

/** 2018-10-30 이근환 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(next value for sqabutton_id, 'LINKWOPART','작업사용부품연결','','500','Y'); 
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(next value for sqabutton_id, 'ADDWOPART','작업사용부품추가','','510','Y'); 
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(next value for sqabutton_id, 'ISSSELECT','출고자재선택','','400','Y'); 

/** 2018-10-30 매일유업 반영 */
/** 2018-11-01 국도화학 반영 */
/** 2018-11-02 매일유업 반영 */
/** 2018-11-07 본사 DREAM 반영 */
/** 2018-11-08 국도화학 반영 */
/** 2018-11-08 매일유업 반영 */
/** 2018-11-14 김정우 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(next value for sqabutton_id, 'WOMONTHSCHED','월간작업일정','','999','Y'); 

/** 2018-11-15 국도화학 반영 */
/** 2018-11-16 국도화학 반영 */

/** 2018-11-19 김남현 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(NEXT VALUE FOR sqabutton_id, 'SELECTPO','발주선택','','500','Y');

/** 2018-11-19 본사 국도화학 반영 */
/** 2018-11-19 국도화학 반영 */ 
/** 2018-11-20 국도화학 반영 */
/** 2018-11-21 국도화학 반영 */
/** 2018-11-23 김정우 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(next value for sqabutton_id, 'WOCONFIRMPRINT','확인절차서출력','','500','Y'); 

/** 2018-11-28 양소영 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(NEXT VALUE FOR sqabutton_id, 'SENDMAILCHECK','메일발송체크','','500','Y'); 

/** 2018-11-28 국도화학 반영 */
/** 2018-11-29 국도화학 반영 */
/** 2018-12-03 국도화학 반영 */
/** 2018-12-05 본사 DREAM 반영 */
/** 2018-12-06 매일유업 반영 */
/** 2018-12-14 국도화학 반영 */
/** 2018-12-17 10:08 국도화학 반영 */
/** 2018-12-17 12:20 국도화학 반영 */
/** 2018-12-19 11:00 국도화학 반영 */
/** 2018-12-19 17:25 국도화학 반영 */
/** 2018-12-20 17:10 국도화학 반영 */
/** 2018-12-21 17:50 국도화학 반영 */
/** 2018-12-28 14:20 국도화학 반영 */
/** 2019-01-03 고려용접봉(본사) 반영 */
/** 2019-01-07 국도화학(본사) 반영 */
/** 2019-01-11 국도화학 반영 */
/** 2019-01-17 국도화학 반영 */
/** 2019-01-22 고려용접봉(본사) 반영 */
/** 2019-01-22 국도화학 반영 */
/** 2019-01-25 국도화학 반영 */
/** 2019-01-30 국도화학 반영 */
/** 2019-01-31 국도화학 반영 */
/** 2019-02-14 국도화학 반영 */
/** 2019-02-19 매일유업 반영 */
/** 2019-02-20 국도화학 반영 */
/** 2019-02-26 국도화학 반영 */
/** 2019-03-05 매일유업 반영 */
/** 2019-03-13 국도화학 반영 */
/** 2019-03-13 고려용접봉 반영 */
/** 2019-03-19 매일유업 반영 */


/** 2019-03-26 김정우 */
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'woreq_list_create_ib','CREATE','Y','900','The CREATE button on W/O request list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'woreq_detail_save_btn','SAVE','Y','900','The SAVE button on W/O request detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'woreq_detail_req_btn','REQUEST','Y','900','The REQUEST button on W/O request detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'woreq_detail_approval_btn','APPROVE','Y','900','The APPROVE button on W/O request detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'photo_list_create_ib','CREATE','Y','900','The CREATE button on photo list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'photo_detail_gal_ib','GALLERY','Y','900','The GALLERY button on photo detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'photo_detail_cam_ib','CAMERA','Y','900','The CAMERA button on photo detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'photo_detail_save_btn','SAVE','Y','900','The SAVE button on photo detail page (Android)');

INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'document_list_create_ib','CREATE','Y','900','The CREATE button on document list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'document_detail_viewfile_btn','DOWNLOADFILE','Y','900','The DOWNLOADFILE button on document detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'calibration_dialog_exit_ib','CLOSE','Y','900','The CLOSE button on calibration popup page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'dept_dialog_exit_ib','CLOSE','Y','900','The CLOSE button on department popup page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'emp_dialog_exit_ib','CLOSE','Y','900','The CLOSE button on employee popup page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'equip_dialog_exit_ib','CLOSE','Y','900','The CLOSE button on equipment popup page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'eqasmb_dialog_exit_ib','CLOSE','Y','900','The CLOSE button on assembly popup page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'eqloc_dialog_exit_ib','CLOSE','Y','900','The CLOSE button on location popup page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'failure_dialog_exit_ib','CLOSE','Y','900','The CLOSE button on failureDesc popup page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'parts_dialog_exit_ib','CLOSE','Y','900','The CLOSE button on parts popup page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'plant_dialog_exit_ib','CLOSE','Y','900','The CLOSE button on plant popup page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'syscode_dialog_exit_ib','CLOSE','Y','900','The CLOSE button on syscode popup page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'usrcode_dialog_exit_ib','CLOSE','Y','900','The CLOSE button on usrcode popup page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'wh_dialog_exit_ib','CLOSE','Y','900','The CLOSE button on warehouse popup page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'wkctr_dialog_exit_ib','CLOSE','Y','900','The CLOSE button on work popup page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'iss_list_create_ib','CREATE ','Y','900','The CREATE button on partRelease list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'iss_detail_save_btn','SAVE','Y','900','The SAVE button on partRelease detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'login_login_btn','LOGIN','Y','900','The LOGIN button on login list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'plandown_list_down_btn','PLANDOWNLOAD','Y','900','The PLANDOWNLOAD button on plandown list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'plandown_option_save_btn','SAVE','Y','900','The SAVE button on plandownOption list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'pmi_day_detail_save_btn','SAVE','Y','900','The SAVE button on inspection detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'pmi_day_detail_confirm_btn','CONFIRM','Y','900','The CONFIRM button on inspection detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'pmi_day_point_detail_save_btn','SAVE','Y','900','The SAVE button on inspection point list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'pmi_part_detail_save_btn','SAVE','Y','900','The SAVE button on inspection partChange detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'pmi_part_detail_confirm_btn','CONFIRM','Y','900','The CONFIRM button on inspection partChange detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'pmi_part_point_detail_save_btn','SAVE','Y','900','The SAVE button on inspection partChange point list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'pmi_patrol_detail_save_btn','SAVE','Y','900','The SAVE button on inspection patrol detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'pmi_patrol_detail_confirm_btn','CONFIRM','Y','900','The CONFIRM button on inspection patrol detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'pmi_patrol_point_detail_save_btn','SAVE','Y','900','The SAVE button on inspection patrol point list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'pmi_routine_detail_save_btn','SAVE','Y','900','The SAVE button on inspection routine detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'pmi_routine_detail_confirm_btn','CONFIRM','Y','900','The CONFIRM button on inspection routine detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'pmi_routine_point_detail_save_btn','SAVE','Y','900','The SAVE button on inspection routine point list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'wores_detail_save_btn','SAVE','Y','900','The SAVE button on request recive detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'wores_work_list_create_ib','CREATE','Y','900','The CREATE button on request recive processing list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'search_option_save_btn','SAVE','Y','900','The SAVE button on search option list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'initial_down_down_btn','DOWNLOAD','Y','900','The DOWNLOAD button on beginning option list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'stktake_list_create_ib','CREATE','Y','900','The CREATE button on partDueDiligence part list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'stktake_detail_save_btn','SAVE','Y','900','The SAVE button on partDueDiligence part detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'stock_detail_save_btn','SAVE','Y','900','The SAVE button on stock detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'upload_upload_btn','UPLOAD','Y','900','The UPLOAD button on upload list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'cal_list_create_ib','CREATE','Y','900','The CREATE button on calibration list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'cal_detail_save_btn','SAVE','Y','900','The SAVE button on calibration detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'base_eq_list_create_ib','CREATE','Y','900','The CREATE button on calibration baseEquip list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'base_eq_detail_save_btn','SAVE','Y','900','The SAVE button on calibration baseEquip detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'wocalib_gnl_value_list_create_ib','CREATE','Y','900','The CREATE button on calibration measure list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'wocalib_gnl_value_detail_save_btn','SAVE','Y','900','The SAVE button on calibration measure detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'wocalib_gnl_value_detail_create_ib','CREATE','Y','900','The CREATE button on calibration measure detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'wocalib_prs_value_list_create_ib','CREATE','Y','900','The CREATE button on calibration measure list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'wocalib_prs_value_detail_save_btn','SAVE','Y','900','The SAVE button on calibration measure detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'wocalib_prs_value_detail_create_ib','CREATE','Y','900','The CREATE button on calibration measure detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'wocalib_scl_value_save_bnt','SAVE','Y','900','The SAVE button on calibration measure list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'wocalibvalue_list_create_ib','CREATE','Y','900','The CREATE button on calibration measure list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'wocalibvalue_detail_save_btn','SAVE','Y','900','The SAVE button on calibration measure detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'wocraft_list_create_ib','CREATE','Y','900','The CREATE button on calibration worker list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'wocraft_detail_save_btn','SAVE','Y','900','The SAVE button on calibration worker detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'inspection_detail_save_btn','SAVE','Y','900','The SAVE button on inspection detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'wopoint_detail_save_btn','SAVE','Y','900','The SAVE button on inspection point detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'pmwork_detail_save_btn','SAVE','Y','900','The SAVE button on W.O plan detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'woparts_list_create_ib','CREATE','Y','900','The CREATE button on W.O plan part list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'woparts_detail_save_btn','SAVE','Y','900','The SAVE button on W.O plan part detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'unplan_list_create_ib','CREATE','Y','900','The CREATE button on W.O unplan list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'unplan_detail_save_btn','SAVE','Y','900','The SAVE button on W.O unplan detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'unplan_detail_confirm_btn','CONFIRM','Y','900','The CONFIRM button on W.O unplan detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'wofail_detail_save_btn','SAVE','Y','900','The SAVE button on W.O unplan fail detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'pmwork_detail_confirm_btn','CONFIRM','Y','900','The CONFIRM button on PM work detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (NEXT VALUE FOR SQABUTTON_ID, 'inspection_detail_confirm_btn','CONFIRM','Y','900','The CONFIRM button on inspection detail page (Android)');

/** 2018-03-14 양소영 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(NEXT VALUE FOR sqabutton_id, 'COVERPRINT','표지출력','','500','Y'); 

/** 2019-04-04 매일유업 반영 */
/** 2019-04-15 고려용접봉 반영 */
/** 2019-04-17 국도화학 반영 */

/** 2019-04-17 김은아 */
update tabutton set CRE_DATE = '20190327132010';
update tabutton set UPD_DATE = '20190327132010';

/** 2019-04-18 매일유업 반영 */
/** 2019-04-26 매일유업 반영 */
/** 2019-05-08 매일유업 반영 */
/** 2019-05-10 국도화학 반영 */


/** 2019-04-30 이근환 */
INSERT INTO tabutton(button_id, button_no, description, is_use, ord_no,service_type, site_type, version_info, cre_date, upd_date) 
VALUES(NEXT VALUE FOR sqabutton_id,'INPUTAUTH','권한부여','Y','110','WEB','DREAM','2.01',CONVERT(nvarchar,GETDATE(),112)+REPLACE(CONVERT(nvarchar,GETDATE(),108),':',''),CONVERT(nvarchar,GETDATE(),112)+REPLACE(CONVERT(nvarchar,GETDATE(),108),':','')); 
INSERT INTO tabutton(button_id, button_no, description, is_use, ord_no,service_type, site_type, version_info, cre_date, upd_date) 
VALUES(NEXT VALUE FOR sqabutton_id,'DELETEAUTH','권한제거','Y','120','WEB','DREAM','2.01',CONVERT(nvarchar,GETDATE(),112)+REPLACE(CONVERT(nvarchar,GETDATE(),108),':',''),CONVERT(nvarchar,GETDATE(),112)+REPLACE(CONVERT(nvarchar,GETDATE(),108),':','')); 


/** 2019-05-14 노정현 */
 insert into tabutton(button_id, button_no, description, is_use, ord_no,service_type, site_type, version_info, cre_date, upd_date) 
 values(next value for sqabutton_id, 'PAGEAUTH','페이지권한','Y','110','WEB','DREAM','2.01',convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121));
  insert into tabutton(button_id, button_no, description, is_use, ord_no,service_type, site_type, version_info, cre_date, upd_date) 
  values(next value for sqabutton_id, 'BTNAUTH','버튼권한','Y','110','WEB','DREAM','2.01',convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121)); 

/** 2019-05-20 김남현 */
insert into tabutton(button_id, button_no, description, is_use, ord_no,service_type, site_type, version_info, cre_date, upd_date) values(next value for sqabutton_id, 'CANTWORKCANCEL','작업불가취소','Y','520','WEB','DREAM','2.00',convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121));

/** 2019-05-24 국도화학 반영 */
/** 2019-05-24 고려용접봉 반영 */
/** 2019-05-29 고려용접봉 반영 */
/** 2019-05-30 매일유업 반영 */
/** 2019-06-04 매일유업 반영 */
/** 2019-06-17 경보제약 반영 */
/** 2019-06-17 고려용접봉 반영 */
/** 2019-06-26 고려용접봉 반영 */
/** 2019-06-26 국도화학 반영 */
/** 2019-06-26 매일유업 반영 */
/** 2019-06-28 매일유업 반영 */
/** 2019-07-10 경보제약 반영 */
/** 2019-07-18 경보제약 반영 */
/** 2019-07-25 경보제약 반영 */
/** 2019-07-30 매일유업 반영 */
/** 2019-08-07 경보제약 반영 */ 
/** 2019-08-09 경보제약 반영 */
/** 2019-08-12 경보제약 반영 */
/** 2019-08-13 경보제약 반영 */
/** 2019-08-19 경보제약 반영 */
/** 2019-08-22 경보제약 반영 */
/** 2019-08-23 국도화학 반영 */
/** 2019-08-26 본사 경보제약 반영 */
/** 2019-08-27 경보제약 반영 */
/** 2019-08-29 매일유업 반영 */
/** 2019-09-05 경보제약 반영 */

/** 328 + 은아 */
insert into tabutton(button_id, button_no, description, is_use, ord_no,service_type, site_type, version_info, cre_date, upd_date) values(next value for sqabutton_id,'REQCANCEL','요청취소','N','115','WEB','DREAM','2.00',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 

/** 2019-09-11 경보제약 반영 */
/** 2019-09-18 본사 국도화학 반영 */
/** 2019-10-01 매일유업 반영 */
/** 2019-10-02 경보제약 반영 */


/** 283 + euna0207 */
insert into tabutton(button_id, button_no, description, is_use, ord_no,service_type, site_type, version_info, cre_date, upd_date) values(next value for sqabutton_id,'CONSAVE','조건저장','Y','145','WEB','DREAM','2.00',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tabutton(button_id, button_no, description, is_use, ord_no,service_type, site_type, version_info, cre_date, upd_date) values(next value for sqabutton_id,'CONOPEN','조건열기','Y','148','WEB','DREAM','2.00',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 

/** 2019-10-14 매일유업 반영 */
/** 2019-10-17 매일유업 반영 */
/** 2019-10-22 고려용접봉 반영 */
/** 2019-10-29 경보제약 반영 */

/** 658 이근환 */
insert into tabutton(button_id, button_no, description, is_use, ord_no,service_type, site_type, version_info, cre_date, upd_date) 
values(next value for sqabutton_id,'GETIMAGE','이미지수신','N','110','WEB','DREAM','2.01',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 

/** 2019-11-05 본사 국도화학 반영 */
/** 2019-11-05 고려용접봉 반영 */
/** 2019-11-06 국도화학 반영 */
/** 2019-11-14 고려용접봉 반영 */
/** 2019-11-20 매일유업 반영 */
/** 2019-12-02 매일유업 반영 */

/** 774 + 김은아 */
insert into tabutton(button_id, button_no, description, is_use, ord_no,service_type, site_type, version_info, cre_date, upd_date) values(next value for sqabutton_id,'NOVIEW','조회불가','N','110','WEB','DREAM','2.01',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tabutton(button_id, button_no, description, is_use, ord_no,service_type, site_type, version_info, cre_date, upd_date) values(next value for sqabutton_id,'NOEDIT','수정불가','N','120','WEB','DREAM','2.01',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tabutton(button_id, button_no, description, is_use, ord_no,service_type, site_type, version_info, cre_date, upd_date) values(next value for sqabutton_id,'NOLIMIT','제한없음','N','130','WEB','DREAM','2.01',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 

/** 2019-12-13 한국내화 반영 */
/** 2019-12-20 고려용접봉 반영 */


/** 2020-01-03 azdream - AZURE CLOUD DREAM2 반영 */
/** 2020-01-09 고려용접봉 반영*/
/** 2020-01-09 매일유업 반영 */
/** 2020-01-14 한국내화 kref 반영 */
/** 2020-01-17 매일유업 반영 */
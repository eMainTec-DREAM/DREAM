---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-11   박철완 최초 생성*/ 
---------------------------------------------------------------------------------------------------------------------------------------------------
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(sqabutton_id.nextval, 'SEARCH','자료검색하기','','100','Y'); 
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(sqabutton_id.nextval, 'INTERFACE','I/F 수신','','490','Y');
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(sqabutton_id.nextval, 'CONFIRM','작업완료 확정하기','','500','Y');
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(sqabutton_id.nextval, 'REPORT','레포트파일변환하기(FO)','','510','Y');
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(sqabutton_id.nextval, 'PDF','출력하기','','520','Y'); 
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(sqabutton_id.nextval, 'SELECT','데이타선택하기','','530','Y');
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(sqabutton_id.nextval, 'CHPW','비밀번호변경하기','','540','Y');
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(sqabutton_id.nextval, 'HISTORY','변경이력보기','','550','Y');
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(sqabutton_id.nextval, 'PHOTO','사진등록하기','','570','Y'); 
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(sqabutton_id.nextval, 'REC_COMPLETE','입고완료 확정하기','','630','Y'); 
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(sqabutton_id.nextval, 'REC_CANCEL','입고취소하기','','640','Y'); 
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(sqabutton_id.nextval, 'REPAIR_V','외주수리완료처리하기','','650','Y');
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(sqabutton_id.nextval, 'REPAIR_L','사내수리완료처리하기','','660','Y');
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(sqabutton_id.nextval, 'REPAIR_X','수리불가처리하기','','670','Y');
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(sqabutton_id.nextval, 'CANCEL','수리취소하기','','680','Y'); 
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(sqabutton_id.nextval, 'FIX','일정확정하기','','800','Y'); 
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(sqabutton_id.nextval, 'SAVENEW','저장후신규생성하기','','860','Y');
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(sqabutton_id.nextval, 'EXCEL','Excel다운로드','','910','Y');
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(sqabutton_id.nextval, 'OPEN','세부정보열기','','920','Y'); 
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(sqabutton_id.nextval, 'CREATE','신규생성하기','','930','Y');
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(sqabutton_id.nextval, 'DELETE','삭제하기','','940','Y'); 
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(sqabutton_id.nextval, 'SAVE','자료저장하기','','945','Y');
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(sqabutton_id.nextval, 'SETTING','Grid사용자설정','','950','Y');
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(sqabutton_id.nextval, 'CLOSE','화면닫기','','990','Y'); 
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(sqabutton_id.nextval, 'PLAY','실행하기','','991','Y'); 
 
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
 values(sqabutton_id.nextval, 'WOEX','WO발행','','992','Y'); 
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-12   김정우 추가작업 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO TABUTTON (button_id, button_no, description, is_use, ord_no) VALUES (SQABUTTON_ID.nextval, 'BREAK','설비고장검색하기','Y',994);
INSERT INTO TABUTTON (button_id, button_no, description, is_use, ord_no) VALUES (SQABUTTON_ID.nextval, 'CHPART','부품교체검색하기','Y',995);
INSERT INTO TABUTTON (button_id, button_no, description, is_use, ord_no) VALUES (SQABUTTON_ID.nextval, 'REPAIR','예방수리검색하기','Y',996);
INSERT INTO TABUTTON (button_id, button_no, description, is_use, ord_no) VALUES (SQABUTTON_ID.nextval, 'INSPECTION','예방점검검색하기','Y',997);
INSERT INTO TABUTTON (button_id, button_no, description, is_use, ord_no) VALUES (SQABUTTON_ID.nextval, 'STANDARD','예방기준확인하기','Y',998);
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-12   김정우 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------

/* 2016-06-22 노정현 결재 승인요청 버튼 추가 */
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (SQABUTTON_ID.NEXTVAL, 'APPROVAL', '승인요청', 'Y', '480');
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (SQABUTTON_ID.NEXTVAL, 'APPRLINE', '결재선 선택', 'Y', '485');
   /** 2016-06-27 노정현 버튼 추가  */
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (SQABUTTON_ID.NEXTVAL, 'APPRACTION', '결재처리', 'Y', '500');
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (SQABUTTON_ID.NEXTVAL, 'APPRHIST', '결재이력', 'Y', '510');
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (SQABUTTON_ID.NEXTVAL, 'REJECT', '반려', 'Y', '515');
   /** 2016-06-28 김정우 필드가져오기 버튼 추가 */
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (SQABUTTON_ID.NEXTVAL, 'IMP_FIELD', '필드가져오기', 'Y', '520');
   /** 2016-07-06 김정우 설비마스터 버튼 추가 */
   
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (SQABUTTON_ID.NEXTVAL, 'EQBM', '설비고장', 'Y', '530');
   
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (SQABUTTON_ID.NEXTVAL, 'PMMSTR', '예방작업기준', 'Y', '540');
   
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (SQABUTTON_ID.NEXTVAL, 'PMWO', '예방작업', 'Y', '550');
   
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (SQABUTTON_ID.NEXTVAL, 'USEPARTS', '사용부품', 'Y', '560');


INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (SQABUTTON_ID.NEXTVAL, 'PMSTD', '기준서', 'Y', '530');
   /** 2016-07-13 김정우  구매신청 버튼 추가 */
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (SQABUTTON_ID.NEXTVAL, 'BUYREQ', '구매신청', 'Y', '570');

/** 2016-07-21 노정현 버튼 추가 */
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (SQABUTTON_ID.NEXTVAL, 'CONFIRMISS', '출고처리', 'Y', '580');
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (SQABUTTON_ID.NEXTVAL, 'CANCELISS', '출고취소', 'Y', '590');
   ---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-07-25   김정우 자재내역확인 버튼 추가 추가작업  */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (sqabutton_id.nextval, 'PARTHIST', '자재내역보기', 'Y', '999');
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-07-25   김정우 외주수리의뢰 버튼 추가 추가작업  */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (sqabutton_id.nextval, 'REQREPVEN', '외주수리의뢰', 'Y', '999');
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-07-28   김정우 근무지정, 휴무지정 추가작업  */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
    INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (sqabutton_id.nextval, 'DAYON', '선택항목 근무로 지정', 'Y', '999');
   INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (sqabutton_id.nextval, 'DAYOFF', '선택항목 휴무로 지정', 'Y', '999');
   
   ---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-08-2   이규선 출력버튼 추가작업 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
   INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (sqabutton_id.nextval, 'PRINT', '출력하기', 'Y', '993');
   /** 2016-09-01 김정우 버튼 추가 */
   
   INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (sqabutton_id.nextval, 'EQPDF', '설비이력출력', 'Y', '994');
   INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (sqabutton_id.nextval, 'WOPDF', '작업결과출력', 'Y', '995');
   
       INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (sqabutton_id.nextval, 'CONFIRMRTN', '반납확정', 'Y', '994');
   /** 2016-09-05 김정우 버튼 추가 */
   
   INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (sqabutton_id.NEXTVAL, 'PTREQPDF', '구매요청서출력', 'Y', '996');
   INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (sqabutton_id.NEXTVAL, 'WODAILYPDF', '작업일지출력', 'Y', '997');
   
 INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (sqabutton_id.nextval, 'RELOAD', '로딩', 'Y', '810');
   /** 2016-09-06 이규선 버튼 추가 */
   INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (sqabutton_id.nextval, 'CONFIRMDIS', '폐기확정', 'Y', '994');
   /** 2016-09-07 김정우 접수 버튼추가 */
   
   INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (sqabutton_id.nextval, 'RECCONFIRM', '접수확인', 'Y', '999');
   
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (sqabutton_id.nextval, 'COLSETTING', '항목설정', 'Y', '999');
   
    INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (sqabutton_id.nextval, 'CHECKDIS', '폐기처리', 'Y', '999');
   
       INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (sqabutton_id.nextval, 'CHECKRETURN', '반납', 'Y', '999');
   
    /** 20160926 김정우 슬로박지표 추가 */
       INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (sqabutton_id.nextval, 'REPORTPRINT', '레포트 출력', 'Y', '999');
   
       /** 20161011 이규선 일저재조정 추가 */
          INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (sqabutton_id.nextval, 'SCHEDRESET', '일정재조정', 'Y', '999');
   
             INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (sqabutton_id.nextval, 'SCHDRSTCON', '일정재조정확정', 'Y', '999');
   
   
      /** 2016-10-13 김정우 버튼 추가 */
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (sqabutton_id.nextval, 'POCOMPLETE', '발주완료', 'Y', '999');
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (sqabutton_id.nextval, 'RECCOMPLETE', '입고완료', 'Y', '999');
   /**2016-10-19 김정우 버튼 추가 */
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (sqabutton_id.nextval, 'TRANSFERBSTOCK', '무상입고완료', 'Y', '999');
   
   /** 2016-11-16 김정우 복사 버튼 */
   
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (sqabutton_id.nextval, 'COPYCREATE', '복사', 'Y', '999');
   
   /** 2016-12-16 이규선 출력 버튼 */   
   INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (sqabutton_id.nextval, 'MTLPRPRINT', '몬테레이PR출력', 'N', '999');
      /** 2017-01-11 이규선 작업일정 버튼 */  
   INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (sqabutton_id.nextval, 'PMSCHED', '작업일정', 'Y', '999');
   
-----------------------------------------------------------------------------
--------------------------------유한킴벌리 patch완료 2017.01.13일-------------------
-----------------------------------------------------------------------------
   
    
   /**2017-03-03 김정우 추가 */
   
   INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (sqabutton_id.nextval, 'PTSTOCKPDF', 'STOCK of PARTS', 'Y', '999');
   
     /** 2017-03-03 이규선 출력 버튼 */   
 INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (sqabutton_id.nextval, 'BZPRPRINT', '브라질PR출력', 'N', '999');
   
 -----------------------------------------------------------------------------
--------------------------------유한킴벌리 patch완료 2017.03.15일-------------------
-----------------------------------------------------------------------------   
   /** 2017-03-16 김정우 추가 */
   
 INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (sqabutton_id.nextval, 'LISTBARCODE', 'LIST BARCODE', 'N', '999');
   
   
       /** 2017-03-20 이규선 출력 버튼 */   
 INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (sqabutton_id.nextval, 'BZSHIFTPRINT', '브라질Daily(Shift)출력', 'N', '999');
   
   
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
----------------------------------------------------------------------------- 
/** 2017-04-18 차한결  메일수신자설정 메일발송 버튼추가  */  
INSERT INTO TABUTTON
	(BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO) 
VALUES
	(sqabutton_id.NEXTVAL, 'SENDMAIL','메일발송','Y','993'); 
	
	
	 /** 2017-07-05 이규선  실사확정 버튼추가  */  
     INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (sqabutton_id.NEXTVAL, 'CONFIRMTAKE', '실사확정', 'Y', '994');
   
   /** 2017-08-08 김정우 추가 */
INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (sqabutton_id.NEXTVAL, 'MATRIXVIEW', 'Matrix 보기', 'Y', '999');
   
      /** 2017-08-17 이규선 추가 */
   INSERT INTO TABUTTON
   (BUTTON_ID, BUTTON_NO, DESCRIPTION, IS_USE, ORD_NO)
 VALUES
   (sqabutton_id.NEXTVAL, 'CTGSELECT', '설비(부위) 선택', 'Y', '999');
   
   /** 2017-08-23 김영주 추가 */
INSERT INTO TABUTTON
    (button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES
    (sqabutton_id.NEXTVAL, 'ASSCOMPLETED','평가 완료','','500','Y');
  
   /** 2017-08-30 차한결 추가  */
INSERT INTO tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES(sqabutton_id.NEXTVAL, 'ADDFENV','운전환경추가','','900','Y');
INSERT INTO tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES(sqabutton_id.NEXTVAL, 'ADDEMP','분석자추가','','900','Y'); 
INSERT INTO tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES(sqabutton_id.NEXTVAL, 'ADDEQ','설비설정추가','','900','Y');
INSERT INTO tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES(sqabutton_id.NEXTVAL, 'ADDASMB','설비부위추가','','900','Y');

/** 2017-09-20 이근환 추가  */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use)
values(sqabutton_id.nextval, 'REQUEST','요청','','450','Y'); 
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use)
values(sqabutton_id.nextval, 'RECEIVE','접수','','450','Y'); 

/** 2017-09-21 김영주 추가  */
INSERT INTO TABUTTON
    (button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES
    (sqabutton_id.NEXTVAL, 'PTRLCOMPLETED','순회완료','','500','Y');

    
/** 2017-09-21 김정우 */
INSERT INTO tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES(sqabutton_id.NEXTVAL, 'APPREQUEST','결재요청','','900','Y');
/** 2017-09-21 김정우 */
INSERT INTO tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES(sqabutton_id.NEXTVAL, 'COPYCALIBSTDVALUE','교정표준값복사','','900','Y');
INSERT INTO tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES(sqabutton_id.NEXTVAL, 'REGBATCHCALIBVAL','측정값일괄등록','','900','Y');

/** 2017-09-22 차한결 */
INSERT INTO tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES(sqabutton_id.NEXTVAL, 'EMPPWRESET','전사원비번리셋','','950','Y');

/** 2017-09-28 김영주 추가 */
INSERT INTO TABUTTON
    (button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES
    (sqabutton_id.NEXTVAL, 'COMPLETED','완료','','500','Y');
    
/** 2017-10-11 김정우 추가 */
INSERT INTO TABUTTON (button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES (sqabutton_id.NEXTVAL, 'REVISION','개정','','500','Y');
INSERT INTO TABUTTON (button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES (sqabutton_id.NEXTVAL, 'REVISIONHISTORY','개정이력','','500','Y');

/** 2017-10-17 이근환 추가 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(sqabutton_id.nextval, 'EQLCC','설비별 보기','','100','Y'); 

/** 2017-10-19 이근환 추가 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(sqabutton_id.nextval, 'RESETPW','비밀번호리셋','','500','Y'); 

/** 2017-10-20 차한결 추가 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(sqabutton_id.nextval, 'REVCOMPLETED','제개정완료','','950','Y'); 


/*2017-11-23일 동국제약 반영*/

/** 2017-11-27 김정우 */
INSERT INTO tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES(sqabutton_id.NEXTVAL, 'INSLIST','예방점검','','999','Y'); 

/*2017-11-28일 동국제약 반영*/

/*2017-12-06일 동국제약 반영*/

/** 2017-12-07 김영주 */
 INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) 
 VALUES(sqabutton_id.NEXTVAL, 'EXEINS','점검일정발행','','993','Y');
 
 /** 2017-12-07 이근환 */
 insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(sqabutton_id.nextval, 'RESULT','점검결과열기','','500','Y'); 
 
/** 2017-12-11 김영주 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES(sqabutton_id.NEXTVAL, 'refEqList','관련설비조회','','500','Y');

/** 2017-12-14 동국제약 반영 */

/** 2017-12-15 이근환 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(sqabutton_id.nextval, 'RPLLIST','정기교체','','500','Y'); 

/** 2017-12-15 동국제약 반영 */

/** 2017-12-19 김영주 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES(sqabutton_id.NEXTVAL, 'ACWO','조치W/O 열기','','510','Y');

/** 2017-12-20 이근환 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(sqabutton_id.nextval, 'SETSYSY','시스템숨김(Y)','','200','Y'); 
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(sqabutton_id.nextval, 'SETSYSN','시스템숨김(N)','','210','Y'); 

/** 2017-12-22 김영주 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES(sqabutton_id.NEXTVAL, 'CONFIRMWOPLAN','작업계획확정','','500','Y');


/*2017-12-24 박철완**/
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(sqabutton_id.nextval, 'REFERENCE','참조확인','','210','Y'); 

/** 2017-12-26 동국제약 반영 */
/** 2017-12-27 동국제약 반영 */
/** 2017-12-28 김정우 */
INSERT INTO tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES(sqabutton_id.NEXTVAL, 'INSSCHED','점검일정','','999','Y'); 

/** 2017-12-28 김영주 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.NEXTVAL, 'WOPLAN','작업계획보기','','500','Y');

/** 2017-12-28 이근환 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(sqabutton_id.nextval, 'HELP','도움말','','990','Y'); 

/** 2017-12-28 동국제약 반영 */

/** 2018-01-03 동국제약 반영 */

/** 2018-01-04 김영주 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.NEXTVAL, 'ROLLBACK','테이블생성취소','','500','Y');

/** 2018-01-04 이근환 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(sqabutton_id.nextval, 'TRACELOG','TraceLog','','970','Y'); 

/** 2018-01-04 김정우 추가 */
INSERT INTO tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES(sqabutton_id.NEXTVAL, 'CANTWORK','작업불가','','990','Y');    

/** 2018-01-05 김영주 추가 */
UPDATE TABUTTON SET button_no = 'DROP' WHERE description = '테이블생성취소';

/** 2018-01-05 이근환 추가 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(sqabutton_id.nextval, 'SELECTFILE','파일선택','','500','Y'); 

/** 2018-01-09 동국제약 반영 */

/** 2018-01-12 이근환 추가 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(sqabutton_id.nextval, 'UPLOAD','업로드','','500','Y'); 

/** 2018-01-12 동국제약 반영 */

/**2018-01-13 김정우 추가 */

INSERT INTO tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES(sqabutton_id.NEXTVAL, 'PMCHECKLIST','예방점검표','','999','Y'); 

/** 2018-01-16 동국제약 반영 */
/** 2018-01-18 동국제약 반영 */
/** 2018-01-23 동국제약 반영 */


/** 2018-01-25 김정우 추가 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES(sqabutton_id.NEXTVAL, 'EQALLLISTPRINT','전체리스트출력','','999','Y'); 

INSERT INTO tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
VALUES(sqabutton_id.NEXTVAL, 'EQINFOPDF','설비등록카드','','999','Y'); 

/** 2018-01-26 동국제약 반영 */
/** 2018-01-30 동국제약 반영 */

/** 2018-01-31 이근환 추가 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(sqabutton_id.nextval, 'TEMPLATE','템플릿 가져오기','','450','Y'); 

/** 2018-02-01 동국제약 반영 */

/** 2018-02-05 김영주 추가 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.NEXTVAL, 'INVTPHASE','투자진행하기','','500','Y'); 

/** 2018-02-05 이근환 추가 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(sqabutton_id.nextval, 'CART','카트담기','','500','Y'); 

/** 2018-02-06 김영주 추가 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.NEXTVAL, 'REQDETAIL','요청서보기','','500','Y'); 

/** 2018-02-12 김정우 추가 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES( sqabutton_id.nextval, 'GRPRINT','출고요청서','','500','Y');

/** 2018-02-12 이근환 추가 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(sqabutton_id.nextval, 'CREWOPLAN','작업계획발행','','500','Y'); 

/** 2018-03-08 동국제약 반영 */

/** 2018-03-09 김영주 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES( sqabutton_id.NEXTVAL, 'NOTICE','공지','','500','Y'); 

/** 2018-03-14 김정우 */

insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(sqabutton_id.nextval, 'WORKRESLIST','요청접수리스트','','500','Y'); 

/** 2018-03-14 이근환 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(sqabutton_id.nextval, 'REGBATCH','일괄등록','','500','Y'); 


 /**  2018-03-15 대웅제약 반영 */

/** 2018-03-15 김정우 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(sqabutton_id.nextval, 'WORKLISTPRINT','작업목록출력',NULL,'500','Y'); 

/** 2018-03-19 오뚜기라면 반영 */

/**  2018-03-22 동국제약 반영 */
/**  2018-03-28 동국제약 반영 */
/** 2018-03-28 오뚜기라면 반영 */
/** 2018-04-02 동국제약 반영 */ 
/** 2018-04-03 오뚜기라면 반영 */
/** 2018-04-05 오뚜기라면 반영 */
/** 2018-04-09 오뚜기라면 반영 */
/** 2018-04-10 동국제약 반영 */
/** 2018-04-12 오뚜기라면 반영 */
/** 2018-04-16 연우 반영 */
/** 2018-04-17 오뚜기계열사 반영 */
/** 2018-04-18 오뚜기계열사 반영 */

/** 2018-04-20 김영주 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.NEXTVAL, 'SELECTEDPRINT','선택목록출력','','500','Y'); 

/** 2018-04-24 오뚜기계열사 반영 */
/** 2018-05-02 오뚜기계열사 반영 */

/** 2018-05-03 김영주 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.NEXTVAL, 'WORKREQ','작업요청','','500','Y'); 
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.NEXTVAL, 'WORKPLAN','작업계획','','500','Y'); 

/** 2018-05-03 동국제약 반영 */
/** 2018-05-08 오뚜기계열사 반영 */
/** 2018-05-10 연우 반영 */
/** 2018-05-15 오뚜기계열사 반영 */
/** 2018-05-15 연우 반영 */
/** 2018-05-15 오뚜기계열사 반영 */
/** 2018-05-16 연우 반영 */
/** 2018-05-18 오뚜기계열사 반영 */
/** 2018-05-25 오뚜기계열사 반영 */

/** 2018-05-29 이근환 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(sqabutton_id.nextval, 'REVERSE','작업완료 취소하기','','500','Y'); 

/** 2018-05-30 동국제약 반영 */
/** 2018-06-01 오뚜기계열사 반영 */
/** 2018-06-01 대웅제약 반영 */
/** 2018-06-07 연우 반영 */

/** 2018-06-07 김영주 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.NEXTVAL, 'ADD','추가','','520','Y'); 
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.NEXTVAL, 'EDIT','Edit Grid','','925','Y'); 
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.NEXTVAL, 'DEL','삭제','','530','Y'); 
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.NEXTVAL, 'EDITCNCL','수정취소','','540','Y'); 

/** 2018-06-08 오뚜기계열사 반영 */
/** 2018-06-14 연우 반영 */
/** 2018-06-15 연우 반영 */
/** 2018-06-18 연우 반영 */
/** 2018-06-20 연우 반영 */
/** 2018-06-22 11:55 오뚜기 협력사 적용 */
/** 2018-06-25 대웅제약 반영 */

/** 2018-06-27 김정우 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.NEXTVAL, 'BEFOREPHOTO','전사진','','999','Y');
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.NEXTVAL, 'AFTERPHOTO','전사진','','999','Y');

/** 2018-06-28 12:55 오뚜기 협력사 적용 */

/** 2018-06-28 이지수 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.NEXTVAL, 'INVTCREATE','투자목록생성','','500','Y'); 
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.NEXTVAL, 'INVTSELECT','기존투자연결','','500','Y'); 

/** 2018-06-29 연우 반영 */

/** 2018-07-03 김영주 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.NEXTVAL, 'ADDPHASE','절차추가하기','','500','Y'); 
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.NEXTVAL, 'ADDNEWEQUIP','신규설비생성','','500','Y'); 
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.NEXTVAL, 'ADDEQUIP','기존설비','','510','Y'); 

/** 2018-07-03 대웅제약 반영 */
/** 2018-07-04 본사Dream 반영 */
/** 2018-07-04 연우 반영 */

/** 2018-07-05 김영주 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.NEXTVAL, 'STDPT','표준부품선택','','500','Y'); 
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.NEXTVAL, 'STDSP','표준제원선택','','500','Y'); 

/** 2018-07-06 본사Dream 반영 */
/** 2018-07-09 동국제약 반영 */
/** 2018-07-10 오뚜기 협력사 적용 */
/** 2018-07-11 본사Dream 반영 */
/** 2018-07-11 동국제약 반영 */
/** 2018-07-12 동국제약 반영 */
/** 2018-07-12 본사Dream 반영 */
/** 2018-07-13 본사Dream 반영 */

/** 2018-07-15 양소영 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.nextval, 'STDASMB','표준부위선택','','500','Y'); 

/** 2018-07-17 본사Dream 반영 */
/** 2018-07-18 연우 반영 */
/** 2018-07-18 대웅제약 반영 */

/** 2018-07-20 김영주 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.NEXTVAL, 'WOCREATE','신규작업생성','','500','Y'); 
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.NEXTVAL, 'WOSELECT','기존작업선택','','510','Y');
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.NEXTVAL, 'WOPLANCREATE','신규계획생성','','500','Y'); 
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.NEXTVAL, 'WOPLANSELECT','기존계획선택','','510','Y');

/** 2018-07-20 본사Dream 반영 */
/** 2018-07-24 11:10 오뚜기협력사 반영 */
/** 2018-07-25 대웅제약 반영 */

/** 2018-07-31 김정우 추가 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.nextval, 'DATAUPLOAD','데이터 반영','','','Y'); 

/** 2018-08-02 김영주 추가 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.NEXTVAL, 'CREPLANINVT','계획투자생성','','500','Y'); 

/** 2018-08-06 대웅제약 반영 */

/** 2018-08-06 이지수 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES( sqabutton_id.nextval, 'INTFEXE','즉시수신','','150','Y');

/**  2018-08-08 이근환 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(sqabutton_id.nextval, 'PTSTOCK','재고조회','','490','Y'); 

/** 2018-08-10 10:00 로얄캐닌 적용 */
/** 2018-08-11 대웅제약 적용 */
/** 2018-08-13 김남현 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.nextval, 'CONTENTSSELECT','CONTENTS 선택','','500','Y');

/** 2018-08-14  9:40 로얄캐닌 적용 */
/** 2018-08-14  9:40 로얄캐닌 적용 */
/** 2018-08-17 평화오일씰 반영 */
/** 2018-08-17 안국약품 반영 */
/** 2018-08-17 본사Dream 반영 */

/** 2018-08-20 대웅제약 적용 */

/** 2018-08-20 이지수 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.nextval, 'CANCELINVT','투자완료 취소하기','','300','Y');

/** 2018-08-21 09:10 오뚜기협력사 반영 */
/** 2018-08-21 16:00 오뚜기협력사 반영 */

/** 2018-08-21 이근환 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(sqabutton_id.nextval, 'CONFIRMPTSTKMOVE','재고이동 확정하기','','500','Y'); 
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(sqabutton_id.nextval, 'CANCELPTSTKMOVE','재고이동 취소하기','','500','Y'); 

/** 2018-08-22 평화오일씰 반영 */
/** 2018-08-23 11:00 동국제약 반영 */

/** 2018-08-29 안국약품 반영 */
/** 2018-08-29 본사Dream 반영 */
/** 2018-08-29 대웅제약 반영 */
/** 2018-08-30 평화오일씰 반영 */
/** 2018-09-04 10:20 오뚜기협력사 반영 */

/** 2018-09-04 이지수 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES( sqabutton_id.nextval, 'SCORECOPY','평가결과복사','','100','Y');

/** 2018-09-05 평화오일씰 반영 */

/** 2018-09-07 이근환 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(sqabutton_id.nextval, 'SENDUSERINFOMAIL','사용자 정보 메일 발송','','500','Y'); 

/** 2018-09-10 대웅제약 반영 */
/** 2018-09-10 19:00 동국제약 반영 */

/** 2018-09-14 양소영 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.nextval, 'WOREQCREATE','신규요청생성','','500','Y'); 
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.nextval, 'WOREQSELECT','기존요청선택','','510','Y'); 

/** 2018-09-14 대웅제약 반영 */
/** 2018-09-18 평화오일씰 반영 */
/** 2018-09-18 안국약품반영 반영 */
/** 2018-09-21 08:30 오뚜기협력사 반영 */
/** 2018-09-28 평화오일씰 반영 */
/** 2018-10-02 안국약품반영 반영 */
/** 2018-10-02 평화오일씰 반영 */
/** 2018-10-05 평화오일씰 반영 */
/** 2018-10-10 안국약품반영 반영 */
/** 2018-10-10 평화오일씰 반영 */
/** 2018-10-10 18:00 동국제약 반영 */
/** 2018-10-11 평화오일씰 반영 */

/** 2018-10-12 김은아 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.NEXTVAL, 'WOPRWEXECUTE','작업대기','','500','Y'); 
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.NEXTVAL, 'WOPRPEXECUTE','작업중','','500','Y'); 

/** 2018-10-14 대웅제약 반영 */
/** 2018-10-16 09:30 동국제약 반영 */

 /** 2018-10-16 12:00 오뚜기 협력사 반영 */
/** 2018-10-18 평화오일씰 반영 */

/** 2018-10-19 이지수 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES( sqabutton_id.nextval, 'PNSELECT','현장신청부품','','200','Y');

/** 2018-10-19 10:00 동국제약 반영 */
/** 2018-10-21 대웅제약 반영 */
/** 2018-10-23 안국약품 반영 */
/** 2018-10-23 평화오일씰 반영 */
/** 2018-10-24 평화오일씰 반영 */
/** 2018-10-23 오뚜기 협력사 반영 */

/** 2018-10-24 김남현 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.nextval, 'CALIBREPORTPRINT','레포트출력(교정)','','500','Y');

/** 2018-10-25 대웅제약 반영 */
/** 2018-10-25 11:20 동국제약 반영 */
/** 2018-10-26 평화오일씰 반영 */
/** 2018-10-27 평화오일씰 반영 */
/** 2018-10-29 본사Dream 반영 */

/** 2018-10-30 이근환 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(sqabutton_id.nextval, 'LINKWOPART','작업사용부품연결','','500','Y'); 
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(sqabutton_id.nextval, 'ADDWOPART','작업사용부품추가','','510','Y'); 
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(sqabutton_id.nextval, 'ISSSELECT','출고자재선택','','400','Y'); 

/** 2018-10-30 평화오일씰 반영 */
/** 2018-10-30 대웅제약 반영 */

/** 2018-10-30 안국약품 반영 */
/** 2018-10-30 15:10 동국제약 반영 */
/** 2018-10-31 평화오일씰 반영 */
/** 2018-11-01 10:40 동국제약 반영 */
/** 2018-11-01 평화오일씰 반영 */
/** 2018-11-02 평화오일씰 반영 */
/** 2018-11-02 오뚜기 협력사 반영 */
/** 2018-11-06 1030 오뚜기협력사 반영 */
/** 2018-11-06 평화오일씰 반영 */
/** 2018-11-07 평화오일씰 반영 */
/** 2018-11-07 16:30 동국제약 반영 */
/** 2018-11-09 평화오일씰 반영 */
/** 2018-11-13 평화오일씰 반영 */
/** 2018-11-13 안국약품 반영 */

/** 2018-11-14 김정우 */
insert into tabutton(button_id, button_no, description, btn_img, ord_no, is_use) 
values(sqabutton_id.nextval, 'WOMONTHSCHED','월간작업일정','','999','Y'); 
/** 2018-11-14 1015 오뚜기협력사 반영 */
/** 2018-11-14 평화오일씰 반영 */
/** 2018-11-15 08:15 동국제약 반영 */
/** 2018-11-15 안국약품 반영 */
/** 2018-11-15 평화오일씰 반영 */
/** 2018-11-16 평화오일씰 반영 */

/** 2018-11-19 김남현 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.nextval, 'SELECTPO','발주선택','','500','Y'); 

/** 2018-11-19 안국약품 반영 */
/** 2018-11-20 평화오일씰 반영 */
/** 2018-11-21 평화오일씰 반영 */
/** 2018-11-22 평화오일씰 반영 */
/** 2018-11-23 평화오일씰 반영 */

/** 2018-11-23 김정우 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.nextval, 'WOCONFIRMPRINT','확인절차서출력','','500','Y'); 

/** 2018-11-26 평화오일씰 반영 */
/** 2018-11-27 안국약품 반영 */
/** 2018-11-27 평화오일씰 반영 */
/** 2018-11-27 오뚜기협력사 반영 */
/** 2018-11-27 05:30 동국제약 반영 */
/** 2018-11-28 평화오일씰 반영 */

/** 2018-11-28 양소영 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.nextval, 'SENDMAILCHECK','메일발송체크','','500','Y'); 

/** 2018-11-28 안국약품 반영 */
/** 2018-11-28 본사Dream 반영 */
/** 2018-11-28 15:45 동국제약 반영 */
/** 2018-11-29 평화오일씰 반영 */
/** 2018-12-04 대웅제약 반영  */
/** 2018-12-04 평화오일씰 반영 */
/** 2018-12-05 09:30 동국제약 반영 */
/** 2018-12-05 평화오일씰 반영 */
/** 2018-12-06 평화오일씰 반영 */
/** 2018-12-07 09:10 동국제약 반영 */
/** 2018-12-10 평화오일씰 반영 */
/** 2018-12-11 안국약품 반영 */
/** 2018-12-12 평화오일씰 반영 */
/** 2018-12-13 평화오일씰 반영 */
/** 2018-12-14 평화오일씰 반영 */
/** 2018-12-14 동국제약 반영 */
/** 2018-12-17 평화오일씰 반영 */
/** 2018-12-18 평화오일씰 반영 */
/** 2018-12-18 안국약품 반영 */
/** 2018-12-19 평화오일씰 반영 */
/** 2018-12-20 평화오일씰 반영 */
/** 2018-12-20 동국제약 반영 */
/** 2018-12-21 평화오일씰 반영 */
/**2018-12-24 13:38 오뚜기 협력사 반영 */
/** 2018-12-26 평화오일씰 반영 */
/** 2018-12-27 안국약품 반영 */
/** 2018-12-27 평화오일씰 반영 */
/** 2019-01-02 본사Dream 반영 */
/** 2019-01-04 평화오일씰 반영 */
/** 2019-01-08 안국약품 반영 */
/** 2019-01-16 동국제약 반영 */
/** 2019-01-31 평화오일씰 반영 */
/** 2019-02-11 본사Dream 반영 */
/** 2019-02-12 안국약품 반영 */
/** 2019-03-04 안국약품 반영 */
/** 2019-03-07 동국제약 반영 */
/** 2019-03-13 현대일렉트릭 반영 */
/** 2019-03-26 오뚜기 반영 */


/**2019-03-26 김정우 */
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'woreq_list_create_ib','CREATE','Y','900','The CREATE button on W/O request list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'woreq_detail_save_btn','SAVE','Y','900','The SAVE button on W/O request detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'woreq_detail_req_btn','REQUEST','Y','900','The REQUEST button on W/O request detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'woreq_detail_approval_btn','APPROVE','Y','900','The APPROVE button on W/O request detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'photo_list_create_ib','CREATE','Y','900','The CREATE button on photo list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'photo_detail_gal_ib','GALLERY','Y','900','The GALLERY button on photo detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'photo_detail_cam_ib','CAMERA','Y','900','The CAMERA button on photo detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'photo_detail_save_btn','SAVE','Y','900','The SAVE button on photo detail page (Android)');

INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'document_list_create_ib','CREATE','Y','900','The CREATE button on document list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'document_detail_viewfile_btn','DOWNLOADFILE','Y','900','The DOWNLOADFILE button on document detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'calibration_dialog_exit_ib','CLOSE','Y','900','The CLOSE button on calibration popup page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'dept_dialog_exit_ib','CLOSE','Y','900','The CLOSE button on department popup page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'emp_dialog_exit_ib','CLOSE','Y','900','The CLOSE button on employee popup page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'equip_dialog_exit_ib','CLOSE','Y','900','The CLOSE button on equipment popup page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'eqasmb_dialog_exit_ib','CLOSE','Y','900','The CLOSE button on assembly popup page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'eqloc_dialog_exit_ib','CLOSE','Y','900','The CLOSE button on location popup page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'failure_dialog_exit_ib','CLOSE','Y','900','The CLOSE button on failureDesc popup page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'parts_dialog_exit_ib','CLOSE','Y','900','The CLOSE button on parts popup page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'plant_dialog_exit_ib','CLOSE','Y','900','The CLOSE button on plant popup page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'syscode_dialog_exit_ib','CLOSE','Y','900','The CLOSE button on syscode popup page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'usrcode_dialog_exit_ib','CLOSE','Y','900','The CLOSE button on usrcode popup page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'wh_dialog_exit_ib','CLOSE','Y','900','The CLOSE button on warehouse popup page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'wkctr_dialog_exit_ib','CLOSE','Y','900','The CLOSE button on work popup page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'iss_list_create_ib','CREATE ','Y','900','The CREATE button on partRelease list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'iss_detail_save_btn','SAVE','Y','900','The SAVE button on partRelease detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'login_login_btn','LOGIN','Y','900','The LOGIN button on login list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'plandown_list_down_btn','PLANDOWNLOAD','Y','900','The PLANDOWNLOAD button on plandown list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'plandown_option_save_btn','SAVE','Y','900','The SAVE button on plandownOption list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'pmi_day_detail_save_btn','SAVE','Y','900','The SAVE button on inspection detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'pmi_day_detail_confirm_btn','CONFIRM','Y','900','The CONFIRM button on inspection detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'pmi_day_point_detail_save_btn','SAVE','Y','900','The SAVE button on inspection point list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'pmi_part_detail_save_btn','SAVE','Y','900','The SAVE button on inspection partChange detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'pmi_part_detail_confirm_btn','CONFIRM','Y','900','The CONFIRM button on inspection partChange detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'pmi_part_point_detail_save_btn','SAVE','Y','900','The SAVE button on inspection partChange point list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'pmi_patrol_detail_save_btn','SAVE','Y','900','The SAVE button on inspection patrol detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'pmi_patrol_detail_confirm_btn','CONFIRM','Y','900','The CONFIRM button on inspection patrol detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'pmi_patrol_point_detail_save_btn','SAVE','Y','900','The SAVE button on inspection patrol point list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'pmi_routine_detail_save_btn','SAVE','Y','900','The SAVE button on inspection routine detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'pmi_routine_detail_confirm_btn','CONFIRM','Y','900','The CONFIRM button on inspection routine detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'pmi_routine_point_detail_save_btn','SAVE','Y','900','The SAVE button on inspection routine point list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'wores_detail_save_btn','SAVE','Y','900','The SAVE button on request recive detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'wores_work_list_create_ib','CREATE','Y','900','The CREATE button on request recive processing list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'search_option_save_btn','SAVE','Y','900','The SAVE button on search option list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'initial_down_down_btn','DOWNLOAD','Y','900','The DOWNLOAD button on beginning option list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'stktake_list_create_ib','CREATE','Y','900','The CREATE button on partDueDiligence part list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'stktake_detail_save_btn','SAVE','Y','900','The SAVE button on partDueDiligence part detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'stock_detail_save_btn','SAVE','Y','900','The SAVE button on stock detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'upload_upload_btn','UPLOAD','Y','900','The UPLOAD button on upload list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'cal_list_create_ib','CREATE','Y','900','The CREATE button on calibration list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'cal_detail_save_btn','SAVE','Y','900','The SAVE button on calibration detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'base_eq_list_create_ib','CREATE','Y','900','The CREATE button on calibration baseEquip list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'base_eq_detail_save_btn','SAVE','Y','900','The SAVE button on calibration baseEquip detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'wocalib_gnl_value_list_create_ib','CREATE','Y','900','The CREATE button on calibration measure list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'wocalib_gnl_value_detail_save_btn','SAVE','Y','900','The SAVE button on calibration measure detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'wocalib_gnl_value_detail_create_ib','CREATE','Y','900','The CREATE button on calibration measure detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'wocalib_prs_value_list_create_ib','CREATE','Y','900','The CREATE button on calibration measure list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'wocalib_prs_value_detail_save_btn','SAVE','Y','900','The SAVE button on calibration measure detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'wocalib_prs_value_detail_create_ib','CREATE','Y','900','The CREATE button on calibration measure detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'wocalib_scl_value_save_bnt','SAVE','Y','900','The SAVE button on calibration measure list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'wocalibvalue_list_create_ib','CREATE','Y','900','The CREATE button on calibration measure list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'wocalibvalue_detail_save_btn','SAVE','Y','900','The SAVE button on calibration measure detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'wocraft_list_create_ib','CREATE','Y','900','The CREATE button on calibration worker list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'wocraft_detail_save_btn','SAVE','Y','900','The SAVE button on calibration worker detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'inspection_detail_save_btn','SAVE','Y','900','The SAVE button on inspection detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'wopoint_detail_save_btn','SAVE','Y','900','The SAVE button on inspection point detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'pmwork_detail_save_btn','SAVE','Y','900','The SAVE button on W.O plan detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'woparts_list_create_ib','CREATE','Y','900','The CREATE button on W.O plan part list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'woparts_detail_save_btn','SAVE','Y','900','The SAVE button on W.O plan part detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'unplan_list_create_ib','CREATE','Y','900','The CREATE button on W.O unplan list page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'unplan_detail_save_btn','SAVE','Y','900','The SAVE button on W.O unplan detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'unplan_detail_confirm_btn','CONFIRM','Y','900','The CONFIRM button on W.O unplan detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'wofail_detail_save_btn','SAVE','Y','900','The SAVE button on W.O unplan fail detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'pmwork_detail_confirm_btn','CONFIRM','Y','900','The CONFIRM button on PM work detail page (Android)');
INSERT INTO TABUTTON (button_id, button_no,description,is_use,ord_no,remark) VALUES (SQABUTTON_ID.NEXTVAL, 'inspection_detail_confirm_btn','CONFIRM','Y','900','The CONFIRM button on inspection detail page (Android)');


/** 2019-03-26 14:30 오뚜기 반영 */

/** 2018-03-14 양소영 */
INSERT INTO TABUTTON(button_id, button_no, description, btn_img, ord_no, is_use) VALUES(sqabutton_id.nextval, 'COVERPRINT','표지출력','','500','Y'); 

/** 2019-03-26 안국약품 반영 */
/** 2019-03-27 로얄캐닌 반영 */
/** 2019-03-28 평화오일씰 반영 */
/** 2019-04-08 평화오일씰 반영 */
/** 2019-04-16 오뚜기 반영 */

/** 2019-04-17 김은아 */
update tabutton set CRE_DATE = '20190327132010';
update tabutton set UPD_DATE = '20190327132010';

/** 2019-04-18 현대일렉트릭 반영 */
/** 2019-04-26 현대일렉트릭 반영 */
/** 2019-05-02 현대일렉트릭 반영 */
/** 2019-05-12 백광산업 반영 */
/** 2019-05-13 평화오일씰 반영 */

/** 2019-04-30 이근환 */
INSERT INTO tabutton(button_id, button_no, description, is_use, ord_no,service_type, site_type, version_info, cre_date, upd_date) 
VALUES(sqabutton_id.NEXTVAL,'INPUTAUTH','권한부여','Y','110','WEB','DREAM','2.01',TO_CHAR(SYSDATE,'YYYYMMDDHH24MISS'),TO_CHAR(SYSDATE,'YYYYMMDDHH24MISS')); 
INSERT INTO tabutton(button_id, button_no, description, is_use, ord_no,service_type, site_type, version_info, cre_date, upd_date) 
VALUES(sqabutton_id.NEXTVAL,'DELETEAUTH','권한제거','Y','120','WEB','DREAM','2.01',TO_CHAR(SYSDATE,'YYYYMMDDHH24MISS'),TO_CHAR(SYSDATE,'YYYYMMDDHH24MISS')); 

/** 2019-05-16 현대일렉트릭 반영 */


/** 2019-05-14 노정현 */
INSERT INTO tabutton(button_id, button_no, description, is_use, ord_no,service_type, site_type, version_info, cre_date, upd_date) 
VALUES(sqabutton_id.NEXTVAL,'BTNAUTH','버튼권한','Y','110','WEB','DREAM','2.01',TO_CHAR(SYSDATE,'YYYYMMDDHH24MISS'),TO_CHAR(SYSDATE,'YYYYMMDDHH24MISS')); 
INSERT INTO tabutton(button_id, button_no, description, is_use, ord_no,service_type, site_type, version_info, cre_date, upd_date) 
VALUES(sqabutton_id.NEXTVAL,'PAGEAUTH','페이지권한','Y','120','WEB','DREAM','2.01',TO_CHAR(SYSDATE,'YYYYMMDDHH24MISS'),TO_CHAR(SYSDATE,'YYYYMMDDHH24MISS')); 

/** 2019-05-17 현대일렉트릭 반영 */
/** 2019-05-21 안국약품 반영 */


/** 2019-05-20 김남현 */
insert into tabutton(button_id, button_no, description, is_use, ord_no,service_type, site_type, version_info, cre_date, upd_date) values(sqabutton_id.nextval,'CANTWORKCANCEL','작업불가취소','Y','520','WEB','DREAM','2.00','20190331131020','20190331131020'); 


/** 2019-05-22 오뚜기 본사 반영 */

/** 2019-05-22 현대일렉트릭 반영 */
/** 2019-05-23 평화오일씰 반영 */
/** 2019-05-23 현대일렉트릭 반영 */
/** 2019-05-29 현대일렉트릭 반영 */
/** 2019-05-30 현대일렉트릭 반영 */
/** 2019-06-03 현대일렉트릭 반영 */
/** 2019-06-05 현대일렉트릭 반영 */
/** 2019-06-07 현대일렉트릭 반영 */
/** 2019-06-10 오뚜기 본사 반영 */
/** 2019-06-11 현대일렉트릭 반영 */
/** 2019-06-12 평화오일씰 반영 */
/** 2019-06-19 현대일렉트릭 반영 */
/** 2019-06-19 오뚜기 본사 반영 */
/** 2019-06-25 현대일렉트릭 반영 */
/** 2019-07-02 오뚜기 본사 반영 */
/** 2019-07-03 현대일렉트릭 반영 */
/** 2019-07-11 평화오일씰 반영 */
/** 2019-07-12 백광 반영 */
/** 2019-07-24 로얄캐닌 반영 */
/** 2019-08-12 오뚜기(OEMS) 반영 */
/** 2019-08-21 연우 반영 */
/** 2019-08-26 동국제약 반영 */
/** 2019-08-26 연우 반영 */

/** 328 + euna0207 */
insert into tabutton(button_id, button_no, description, is_use, ord_no,service_type, site_type, version_info, cre_date, upd_date) values(sqabutton_id.nextval,'REQCANCEL','요청취소','N','115','WEB','DREAM','2.00',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 

/** 2019-09-09 평화오일씰 반영 */
/** 2019-10-01 안국약품 반영 */

/** 283 + euna0207 */
insert into tabutton(button_id, button_no, description, is_use, ord_no,service_type, site_type, version_info, cre_date, upd_date) values(sqabutton_id.nextval,'CONSAVE','조건저장','Y','145','WEB','DREAM','2.00',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 
insert into tabutton(button_id, button_no, description, is_use, ord_no,service_type, site_type, version_info, cre_date, upd_date) values(sqabutton_id.nextval,'CONOPEN','조건열기','Y','148','WEB','DREAM','2.00',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 

/** 2019-10-16 현대일렉트릭 반영 */

/** 658 이근환 */
insert into tabutton(button_id, button_no, description, is_use, ord_no,service_type, site_type, version_info, cre_date, upd_date) 
values(sqabutton_id.nextval,'GETIMAGE','이미지수신','N','110','WEB','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 

/** 2019-10-30 평화오일씰 반영 */
/** 2019-11-05 안국약품 반영 */
/** 2019-11-22 동국제약 반영 */
/** 2019-11-25 연우 반영 * /
/** 2019-11-26 평화오일씰 반영 */
/** 2019-11-27 연우 반영 */
/** 2019-12-04 안국약품 반영 */

/** 774 + 김은아 
 * 
 */*/
insert into tabutton(button_id, button_no, description, is_use, ord_no,service_type, site_type, version_info, cre_date, upd_date) values(sqabutton_id.nextval,'NOVIEW','조회불가','N','110','WEB','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 
insert into tabutton(button_id, button_no, description, is_use, ord_no,service_type, site_type, version_info, cre_date, upd_date) values(sqabutton_id.nextval,'NOEDIT','수정불가','N','120','WEB','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 
insert into tabutton(button_id, button_no, description, is_use, ord_no,service_type, site_type, version_info, cre_date, upd_date) values(sqabutton_id.nextval,'NOLIMIT','제한없음','N','130','WEB','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 

/** 2020-01-07 연우 반영 */
/** 2020-01-09 평화오일씰 phos 반영 */
/** 2020-01-13 현대일렉트릭 hdele 반영 */
/** 2020-01-13 평화오일씰 phos 반영 */
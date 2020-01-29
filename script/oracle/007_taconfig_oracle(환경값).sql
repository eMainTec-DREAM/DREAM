---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-11   박철완 최초 생성*/ 
---------------------------------------------------------------------------------------------------------------------------------------------------

 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES('100',SQACONFIG_ID.NEXTVAL,'MAIL_SENDER_ID', 'oems@ottogi.co.kr', '메일을 보내는 계정', 'N'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES('100',SQACONFIG_ID.NEXTVAL,'SYSTEM_ADMIN_GROUP', 'SYSTEM', '홈에 어드민 버튼이 보이는 권한그룹', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES('100',SQACONFIG_ID.NEXTVAL,'FILE_DIR', 'D:\mware_data\file\', '첨부파일 저장 경로', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES('100',SQACONFIG_ID.NEXTVAL,'BATCH_EXEC_USER_ID', 'ADMIN', '배치프로그램 실행시 사용할 User', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES('100',SQACONFIG_ID.NEXTVAL,'LOGINKEYVALUE', 'MWARE', 'SSO로그인시 키값', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES('100',SQACONFIG_ID.NEXTVAL,'CLOSING_DAY', '10', '매월 5일이 지나면 전월 데이터 마감', 'N'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM)
 VALUES('100',SQACONFIG_ID.NEXTVAL,'LAST_CLOSED_DATE', '2016-04-30', '최종마감 일자[현재~이값의 월데이타 재집계]', 'N'); 

---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-17  노정현 추가 작업*/ 
---------------------------------------------------------------------------------------------------------------------------------------------------

INSERT INTO TACONFIG
   (COMP_NO, CONFIG_ID, NAME, VALUE$, DESCRIPTION, IS_SYSTEM)
 VALUES
   ('100', SQACONFIG_ID.NEXTVAL, 'IMG_FILE_TYPE', 'jpg,bmp,jpeg,png,gif,raw,tiff', '업로드 허용파일', 
    'Y');
INSERT INTO TACONFIG
   (COMP_NO, CONFIG_ID, NAME, VALUE$, DESCRIPTION, IS_SYSTEM)
 VALUES
   ('100', SQACONFIG_ID.NEXTVAL, 'MAX_FILE_SIZE', '200', '업로드 파일 허용 크기 (MB)', 
    'Y');
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-17  노정현 추가 작업 끝*/ 
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-19  박철완 - 작업마감일 설정값.*/ 
---------------------------------------------------------------------------------------------------------------------------------------------------
  INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
    VALUES('100',SQACONFIG_ID.NEXTVAL,'WORK_CLOSE_TERM_DAY', '30', '작업마감완료일간격(며칠전 작업마감완료)', 'N'); 

    ---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-06-01  박철완 - 암호화 ㄴSEED.*/ 
---------------------------------------------------------------------------------------------------------------------------------------------------
 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM)
 VALUES('100',SQACONFIG_ID.NEXTVAL,'IFAUTHSEED', '123456789', '인터페이스 암호화 Seed', 'Y'); 
 
 
 /*2016-07-07  박철완 - 가동시간 DEFAULT 값 셋팅...*/  
  
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES('100',SQACONFIG_ID.NEXTVAL,'DAY_WORKING_TIME', '480', '낮 가동시간', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES('100',SQACONFIG_ID.NEXTVAL,'NIGHT_WORKING_TIME', '480', '밤 가동시간', 'Y'); 
  INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES('100',SQACONFIG_ID.NEXTVAL,'BD_MONTHLY_TVALUE', '0.3', 'B/D(%)목표 월별 default 값', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES('100',SQACONFIG_ID.NEXTVAL,'MTTR_MONTHLY_TVALUE', '20', 'MTTR(분)  월별 default값', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES('100',SQACONFIG_ID.NEXTVAL,'MTBF_MONTHLY_TVALUE', '20000', 'MTBF(분)  월별 default값', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES('100',SQACONFIG_ID.NEXTVAL,'STOCK_MONTHLY_TVALUE', '6000000', 'STOCK  월별 default 값', 'Y'); 

 /** 2016-07-18 노정현 ERP Interface Info */
  INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM)
 VALUES('100',SQACONFIG_ID.NEXTVAL,'IF_ERP_URL', '10.214.100.222:5510', 'ERP SAP URL', 'Y'); 
 
  INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM)
 VALUES('100',SQACONFIG_ID.NEXTVAL,'IF_ERP_USER_NAME', 'if_dymos', 'ERP SAP User Name', 'Y'); 
 
  INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM)
 VALUES('100',SQACONFIG_ID.NEXTVAL,'IF_ERP_PASSWORD', 'if_dymos', 'ERP SAP Password', 'Y');  
 
  /*2016-11-18  이규선 - 모니터링페이지 넘김 조건 시간 ...*/  
  INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES('100',SQACONFIG_ID.NEXTVAL,'POPUP_NEXT_TIME', '60', '모니터링페이지 팝업 넘기시간 조건(초단위)', 'N');
  
/** 2016-11-21 노정현 Global Monitering Parameter for DB Connection 
 */  
  INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
VALUES((SELECT comp_no FROM TACOMP WHERE ROWNUM= 1),SQACONFIG_ID.NEXTVAL,'GLOBAL_URL', 'jdbc:oracle:thin:@10.214.100.156:1521:GMMS', 'GLOBAL KPI URL', 'Y');  --158 (dev)

INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
VALUES((SELECT comp_no FROM TACOMP WHERE ROWNUM= 1),SQACONFIG_ID.NEXTVAL,'GLOBAL_USER_NAME', 'gmmsadmin', 'GLOBAL KPI USER NAME', 'Y'); 

INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
VALUES((SELECT comp_no FROM TACOMP WHERE ROWNUM= 1),SQACONFIG_ID.NEXTVAL,'GLOBAL_PASSWORD', 'gmmsadmin1#', 'GLOBAL KPI PASSWORD', 'Y'); 

INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
VALUES((SELECT comp_no FROM TACOMP WHERE ROWNUM= 1),SQACONFIG_ID.NEXTVAL,'GLOBAL_DRIVE_NAME', 'oracle.jdbc.driver.OracleDriver', 'GLOBAL KPI DB Driver', 'Y');

/** 2017-01-10 SLP 설비고장+라인고장 목표값 수치 */

  INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((SELECT comp_no FROM TACOMP WHERE ROWNUM= 1),SQACONFIG_ID.NEXTVAL,'BD_MONTHLY_TVALUE_EQ', '2.0', 'B/D(%)목표(설비고장+라인고장) 월별 default 값', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((SELECT comp_no FROM TACOMP WHERE ROWNUM= 1),SQACONFIG_ID.NEXTVAL,'MTTR_MONTHLY_TVALUE_EQ', '30', 'MTTR(분)(설비고장+라인고장)  월별 default값', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((SELECT comp_no FROM TACOMP WHERE ROWNUM= 1),SQACONFIG_ID.NEXTVAL,'MTBF_MONTHLY_TVALUE_EQ', '570', 'MTBF(분)(설비고장+라인고장)  월별 default값', 'Y'); 
 
 
 -----------------------------------------------------------------------------
--------------------------------유한킴벌리 patch완료 2017.01.13일-------------------
-----------------------------------------------------------------------------
 
 
 /** 2017-01-18  노정현 추가  */
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((SELECT comp_no FROM TACOMP WHERE ROWNUM= 1),SQACONFIG_ID.NEXTVAL,'AC_LENGTH', '10', 'Length of AutoComplete Result', 'Y'); 
 
 
 /** 2017-01-25 김정우 추가 */
  INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((SELECT comp_no FROM TACOMP WHERE ROWNUM= 1),SQACONFIG_ID.NEXTVAL,'BDN_MONTHLY_TVALUE_EQ', '20', 'B/D(건수)목표(설비고장+라인고장) 월별 default 값', 'Y'); 
  INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((SELECT comp_no FROM TACOMP WHERE ROWNUM= 1),SQACONFIG_ID.NEXTVAL,'BDN_MONTHLY_TVALUE', '2.0', 'B/D(건수)목표(라인고장) 월별 default 값', 'Y'); 
  
  /** 2017-02-20 김정우 추가 ※공장별로 값 변경 */
  INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((SELECT comp_no FROM TACOMP WHERE ROWNUM= 1),SQACONFIG_ID.NEXTVAL,'PART_GRADE', 'B', 'PART_GRADE 기본 값', 'Y'); 
  
  /** 2017-03-17 고장시간 목표 추가 */
    INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((SELECT comp_no FROM TACOMP WHERE ROWNUM= 1),SQACONFIG_ID.NEXTVAL,'BDH_MONTHLY_TVALUE', '1.0', 'B/D(시간)목표(라인고장) 월별 default 값', 'Y'); 
  INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((SELECT comp_no FROM TACOMP WHERE ROWNUM= 1),SQACONFIG_ID.NEXTVAL,'BDH_MONTHLY_TVALUE_EQ', '2.0', 'B/D(시간)목표(설비고장+라인고장) 월별 default 값', 'Y'); 



/** 2017-04-3 메일전송자 추가 */  
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((SELECT comp_no FROM TACOMP WHERE ROWNUM= 1),SQACONFIG_ID.NEXTVAL,'MAIL_SENDER_PWD', 'emaintec4231', '메일보내는 패스워드', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((SELECT comp_no FROM TACOMP WHERE ROWNUM= 1),SQACONFIG_ID.NEXTVAL,'MAIL_SENDER_SERVER', 'mail.emaintec.com', '메일보는서버주소', 'Y'); 
-----------------------------------------------------------------------------
--------------------------------다이모스 체코 patch완료 2017.04.05일-------------------
----------------------------------------------------------------------------- 
 -----------------------------------------------------------------------------
--------------------------------다이모스 슬로박 patch완료 2017.04.05일-------------------
-----------------------------------------------------------------------------
 -----------------------------------------------------------------------------
--------------------------------다이모스 몬테레이 patch완료 2017.04.05일-------------------
-----------------------------------------------------------------------------
 -----------------------------------------------------------------------------
--------------------------------다이모스 slp patch완료 2017.04.05일-------------------
-----------------------------------------------------------------------------
 -----------------------------------------------------------------------------
--------------------------------브라질 patch완료 2017.03.24일-------------------
-----------------------------------------------------------------------------  
-----------------------------------------------------------------------------
--------------------------------mseat patch완료 2017.04.04일-------------------
-----------------------------------------------------------------------------
-----------------------------------------------------------------------------
--------------------------------다이모스 조지아 patch완료 2017.03.24일-------------------
-----------------------------------------------------------------------------
 
 
-----------------------------------------------------------------------------
--------------------------------유한킴벌리 patch완료 2017.04.12일-------------------
-----------------------------------------------------------------------------

 
/** 2017-06-26 박철완, 주기적인 패스워드 변경 설정값 추가 */ 
INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
VALUES('100',SQACONFIG_ID.NEXTVAL,'IS_CHANGE_PW_NEEDED', 'Y', '주기적으로 로그인 패스워드를 변경해야 하는지 여부 Y:주기적으로 변경해야 함, N:하지 않아도 됨', 'Y');

INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
VALUES('100',SQACONFIG_ID.NEXTVAL,'PW_CHANGE_CYCLE_DAY', '60', '주기적으로 로그인 패스워드를 변경해야 할때 몇일마다 변경해야 하는지 설정값, 30이면 30일마다 변경', 'Y'); 

INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
VALUES('100',SQACONFIG_ID.NEXTVAL,'IS_PW_SAFETY_LENGTH', 'Y', '패스워드 변경할때 패스워드 복잡도(특수문자, 숫자,대문자,소문자, 8자이상)를 적용할지 여부', 'Y');



/*2017.07.23 박철완*/
INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
VALUES('100',SQACONFIG_ID.NEXTVAL,'IS_MANAGE_PMI_WITH_ORDER', 'Y', '예방점검 데이터를 Work Order로 관리할지 여부
Y:이면 일정생성후 작업오더로 데이터 관리
N:이면 일정생성후 점검결과로 데이타 관리', 'Y'); 




/** 2017-09-19 노정현 추가  */

INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
VALUES('100',SQACONFIG_ID.NEXTVAL,'FILE_TYPE', 'asp,aspx,jsp,php,cer,cdx,asa,php3,war', '파일 업로드 금지 확장자', 'Y');

/*2017-11-23일 동국제약 반영*/

/*2017-11-28일 동국제약 반영*/


/*2017.12.03 박철완***/
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES('100',SQACONFIG_ID.NEXTVAL,'DREAM_URL', 'http://localhost:8080/dream/index.do', '메일이나 기타 주소에서 Dream바로가기 연결주소', 'Y');
 
INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES('100',SQACONFIG_ID.NEXTVAL,'IS_USE_MAIL_SERVICE', 'N', '메일링 서비스를 사용할지 여부 
Y:메일링 서비스를 사용함. 메일계정등이 설정되어 있어야 함
N:메일링 서비스를 사용하지 않음', 'Y');


/*2017-12-06일 동국제약 반영*/


INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
VALUES('100',SQACONFIG_ID.NEXTVAL,'IS_USE_PART_GRADE', 'N', '부품을 재고등급으로 나누어 사용하는지 여부 Y: A,B등급으로 사용하는 곳도 있고 N: A나 B 한등급으로 사용하는게 있음', 'Y');


 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES('100',SQACONFIG_ID.NEXTVAL,'IS_ORDER_CONFIRM_TO_ISSUE', 'N', '작업오더 확정시 자채출고를 자동으로 처리할지 여부
 Y : 오더확정시 출고처리 완료
 N: 오더확정시 출고하지고 않고 출고화면에서 수동처리', 'Y'); 
 
 
 /** 2017-12-14 동국제약 반영 */
 
 /** 2017-12-21 김정우 추가 */
 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES('100',SQACONFIG_ID.NEXTVAL,'MAIL_SENDER_IS_SSL', 'N', '메일서버 SSL 연결 여부', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES('100',SQACONFIG_ID.NEXTVAL,'MAIL_SENDER_PORT', '465', '메일서버 PORT', 'Y'); 
 
  /** 2017-12-26 동국제약 반영 */
 /** 2017-12-27 동국제약 반영 */
 /** 2017-12-28 동국제약 반영 */
 
 
 
 
 /*2017-12-30 박철완.*/
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'IS_USE_ASSET_REVISION', 'Y', '설비생성시에 제개정을 사용할지 여부 default N', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'IS_USE_PM_REVISION', 'Y', '예방점검생성시에 제개정을 사용할지 여부 default N', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'IS_USE_SCRNTRACE', 'Y', '스크린 Trace를 사용할지 여부 default N', 'Y'); 

 
/** 2018-01-03 동국제약 반영 */
/** 2018-01-09 동국제약 반영 */
/** 2018-01-12 동국제약 반영 */
/** 2018-01-16 동국제약 반영 */
/** 2018-01-18 동국제약 반영 */
/** 2018-01-23 동국제약 반영 */
/** 2018-01-26 동국제약 반영 */
/** 2018-01-30 동국제약 반영 */ 
 /** 2018-01-31 김정우 추가 */
  INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((SELECT comp_no FROM tacomp WHERE is_use = 'Y' AND init_ct_path_yn = 'Y' AND ROWNUM = 1),sqaCONFIG_ID.nextval,'SAP_ERP_ID', '', 'SAP 접속ID', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((SELECT comp_no FROM tacomp WHERE is_use = 'Y' AND init_ct_path_yn = 'Y' AND ROWNUM = 1),sqaCONFIG_ID.nextval,'SAP_ERP_PASSWORD', '', 'SAP 접속 PASSWORD', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((SELECT comp_no FROM tacomp WHERE is_use = 'Y' AND init_ct_path_yn = 'Y' AND ROWNUM = 1),sqaCONFIG_ID.nextval,'SAP_ERP_CLIENT', '', 'SAP 접속 Client', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((SELECT comp_no FROM tacomp WHERE is_use = 'Y' AND init_ct_path_yn = 'Y' AND ROWNUM = 1),sqaCONFIG_ID.nextval,'SAP_ERP_LANGUAGE', '', 'SAP 언어', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((SELECT comp_no FROM tacomp WHERE is_use = 'Y' AND init_ct_path_yn = 'Y' AND ROWNUM = 1),sqaCONFIG_ID.nextval,'SAP_ERP_SERVER_IP', '', 'SAP SERVER IP', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((SELECT comp_no FROM tacomp WHERE is_use = 'Y' AND init_ct_path_yn = 'Y' AND ROWNUM = 1),sqaCONFIG_ID.nextval,'SAP_ERP_SYSTEM_NUMBER', '', 'SAP SYSTEM NO', 'Y'); 

/** 2018-02-01 동국제약 반영 */
 
 
  /*2018-2-15 박철완.*/
 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),sqaCONFIG_ID.nextval,'USER_LICENSE_TYPE', 'CONCURRENT', '라이센스 계약 방식[NAMED, CONCURRENT]', 'Y'); 

 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y'  and rownum = 1),sqaCONFIG_ID.nextval,'USER_LICENSE_CNT', '20', '라이센스 계약 갯수', 'Y'); 

/*2018-03-03 박철완*/ 
INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES('100',SQACONFIG_ID.nextval,'WORK_START_BASE_TIME', '0600', '작업시작 기준시간, 하루는 이 시간부터 산정', 'Y'); 

/** 2018-03-05 김정우 */

INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y'  and rownum = 1),sqaCONFIG_ID.nextval,'ANDROID_VERSION_CODE', '', '안드로이드 버전', 'Y'); 

INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y'  and rownum = 1),sqaCONFIG_ID.nextval,'ANDROID_APK_URL', '', '안드로이드 apk 다운로드 URL', 'Y'); 
 
/** 2018-03-08 동국제약 반영 */

/**  2018-03-15 대웅제약 반영 */
 
/** 2018-03-19 오뚜기라면 반영 */
 
/**  2018-03-22 동국제약 반영 */
/**  2018-03-28 동국제약 반영 */
/** 2018-03-28 오뚜기라면 반영 */
 
/*2018-03-03 박철완*/
INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUEs$,DESCRIPTION,IS_SYSTEM) 
VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'IS_WORK_PLAN', 'N', '작업계획에서 확정하면 작업결과가 만들지 여부, 예방작업에서 이 값을 이용하여 계획이나 결과로 데이타를 만듬', 'Y'); 
 
/** 2018-04-02 동국제약 반영 */ 
/** 2018-04-03 오뚜기라면 반영 */
/** 2018-04-05 오뚜기라면 반영 */
/** 2018-04-09 오뚜기라면 반영 */
/** 2018-04-10 동국제약 반영 */
/** 2018-04-12 오뚜기라면 반영 */
/** 2018-04-16 연우 반영 */
/** 2018-04-17 오뚜기계열사 반영 */
/** 2018-04-18 오뚜기계열사 반영 */
/** 2018-04-24 오뚜기계열사 반영 */
/** 2018-05-02 오뚜기계열사 반영 */
/** 2018-05-03 동국제약 반영 */
/** 2018-05-08 오뚜기계열사 반영 */
/** 2018-05-10 연우 반영 */
/** 2018-05-15 오뚜기계열사 반영 */
/** 2018-05-15 연우 반영 */
/** 2018-05-15 오뚜기계열사 반영 */
/** 2018-05-16 연우 반영 */
/** 2018-05-18 오뚜기계열사 반영 */
/** 2018-05-25 오뚜기계열사 반영 */
/** 2018-05-30 동국제약 반영 */
/** 2018-06-01 오뚜기계열사 반영 */
 
/** 2018-06-01 대웅제약 반영 */
/** 2018-06-07 연우 반영 */
/** 2018-06-14 연우 반영 */
/** 2018-06-15 연우 반영 */
/** 2018-06-18 연우 반영 */
/** 2018-06-20 연우 반영 */

/** 2018-06-22 11:55 오뚜기 협력사 적용 */
/** 2018-06-28 12:55 오뚜기 협력사 적용 */

/** 2018-06-29 연우 반영 */
/** 2018-07-04 본사Dream 반영 */
/** 2018-07-04 연우 반영 */
/** 2018-07-06 본사Dream 반영 */
/** 2018-07-09 동국제약 반영 */
/** 2018-07-10 오뚜기 협력사 적용 */
/** 2018-07-11 본사Dream 반영 */
/** 2018-07-11 동국제약 반영 */
/** 2018-07-12 동국제약 반영 */
/** 2018-07-12 본사Dream 반영 */
/** 2018-07-13 본사Dream 반영 */

/*2018-07-14 박철완*/
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'IS_USE_PAGE_ACCESS_LOGGING', 'N', '사용자가 접속한 페이지가 어떤 페이지인지 로그을 남길지 여부. 화면을 접근할때마다 TAACCESSCCLOG 에 기록함. 속도가 느렬질수 있고 데이타를 주기적으로 지워야 함', 'Y'); 

/** 2018-07-17 본사Dream 반영 */
/** 2018-07-18 연우 반영 */
/** 2018-07-18 대웅제약 반영 */ 
/** 2018-07-20 본사Dream 반영 */
 
 /** 2018-07-23 김정우 */
INSERT into TACONFIG(comp_no,config_id,name,value$,description,is_system)
values ((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),sqaconfig_id.nextval,'IS_USE_SMS_SERVICE','N','SMS 발송서비스 사용여부','Y');
INSERT into TACONFIG(comp_no,config_id,name,value$,description,is_system)
values ((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),sqaconfig_id.nextval,'SMS_API_KEY','','COOL SMS API KEY','Y');
INSERT  into  TACONFIG(comp_no,config_id,name,value$,description,is_system)
values ((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),sqaconfig_id.nextval,'SMS_API_SECRET','','COOL SMS API SECRET','Y');
INSERT  into  TACONFIG(comp_no,config_id,name,value$,description,is_system)
values ((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),sqaconfig_id.nextval,'SMS_SEND_NUMBER','','COOL SMS SEND NUMBER','Y');


/** 2018-07-24 11:10 오뚜기협력사 반영 */

/*2018-07-24 박철완*/
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'SMS_MONTH_CNT', '200', 'SMS 월 사용가능갯수', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'SMS_TOT_CNT', '20000', 'SMS 총 사용가능갯수[사용종료일 기준]', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'SMS_EXPIRE_DATE', '9999-12-31', 'SMS사용종료일', 'Y'); 

 /** 2018-07-25 대웅제약 반영 */
 
 /** 2018-08-07 노정현 추가 */
 
  INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((SELECT comp_no FROM TACOMP WHERE is_use = 'Y' AND init_ct_path_yn = 'Y' AND ROWNUM = 1),SQACONFIG_ID.NEXTVAL,'IS_USE_AUDIT_TRAIL', 'N', 'Audit Trail 사용여부', 'Y'); 

 
/** 2018-08-10 10:00 로얄캐닌 적용 */
/** 2018-08-14  9:40 로얄캐닌 적용 */
 
  /** 2018-08-07 노정현 추가 */
 
  INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((SELECT comp_no FROM TACOMP WHERE is_use = 'Y' AND init_ct_path_yn = 'Y' AND ROWNUM = 1),SQACONFIG_ID.NEXTVAL,'IS_USE_AUDIT_TRAIL_READ', 'N', 'Audit Trail READ 사용여부', 'Y'); 

/** 2018-08-17 평화오일씰 반영 */
/** 2018-08-17 안국약품 반영 */
/** 2018-08-17 본사Dream 반영 */
/** 2018-08-21 09:10 오뚜기협력사 반영 */
/** 2018-08-21 16:00 오뚜기협력사 반영 */
/** 2018-08-22 평화오일씰 반영 */
/** 2018-08-23 11:00 동국제약 반영 */
/** 2018-08-29 안국약품 반영 */
/** 2018-08-29 본사Dream 반영 */
/** 2018-08-30 평화오일씰 반영 */

 
/** 2018-08-31 박철완 추가 */
INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM)  VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'IS_USE_OTP_LOGIN', 'N', '사용자가 로그인할때 OTP로그인을 사용할지 여부', 'Y'); 

/** 2018-09-02 대웅제약 반영 */


/** 2018-09-04 박철완 추가 */  
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'IS_PR_CONFIRM_TO_RECEIPT', 'N', '구매청구 확정시 구매입고로 데이터 생성여부
 Y : 구매입고에 작성중으로 데이터 생성함
 N: 구매입고에 작성중으로 데이터 생성하지 않음.', 'Y'); 

/** 2018-09-04 10:20 오뚜기협력사 반영 */
/** 2018-09-05 평화오일씰 반영 */
 
 /** 2018-09-07 박철완 추가 */  
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum=1),SQACONFIG_ID.NEXTVAL,'MANAGER_MAIL_ID', 'reply@emaintec.com', '시스템운영담당자 메일주소', 'Y'); 

 /** 2018-09-10 대웅제약 반영 */
 /** 2018-09-10 19:00 동국제약 반영 */
/** 2018-09-18 평화오일씰 반영 */
/** 2018-09-18 안국약품 반영 */
 
 /** 2018-09-19 박철완 추가 */
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'IS_COMP_PLANWRK_MOLOAD', 'N', '계획작업 업로드시 계획작업 상태를 완료로 변경할지 여부', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'IS_COMP_INSWRK_MOLOAD', 'N', '점검작업 업로드시 점검작업 상태로 완료로 변경할지 여부', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'IS_COMP_BMWRK_MOLOAD', 'N', '고장작업 업로드시 고장작업 상태를 완료로 변경할지 여부', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'IS_COMP_CALWRK_MOLOAD', 'N', '교정작업 업로드시 교정작업 상태를 완료로 변경할지 여부', 'Y'); 

 
/** 2018-09-21 08:30 오뚜기협력사 반영 */
/** 2018-09-28 평화오일씰 반영 */
/** 2018-09-30 대웅제약 반영 */
/** 2018-10-02 안국약품반영  */
/** 2018-10-02 평화오일씰 반영 */
/** 2018-10-05 평화오일씰 반영 */
/** 2018-10-10 안국약품반영 반영 */
/** 2018-10-10 평화오일씰 반영 */
/** 2018-10-10 18:00 동국제약 반영 */
/** 2018-10-11 평화오일씰 반영 */
 
 
/** 2018-10-11 김정우 추가 */
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'EMPTY_FIELD_VALUE', 'N/A', '빈 필드 기본값', 'Y'); 
 
 /** 2018-10-14 대웅제약 반영 */
 
 /** 2018-10-15 김정우 추가 */
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'BARCODE_DIVIDER', '', '바코드 구분자', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'BARCODE_SEQ', '', '바코드를 읽었을 때 사용할 영역의 순서', 'Y'); 
 
/** 2018-10-16 09:30 동국제약 반영 */
 /** 2018-10-16 12:00 오뚜기 협력사 반영 */
 /** 2018-10-16 박철완 추가 */
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'IS_LOCK_EXCEL_DOWN_FILE', 'N', '엑셀다운로드시 암호화여부', 'Y');
 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'IS_LOCK_EXCEL_PASSWORD', '1234', '엑셀다운로된 파일의 암호값', 'Y'); 

/** 2018-10-18 평화오일씰 반영 */
/** 2018-10-19 10:00 동국제약 반영 */
 /** 2018-10-21 대웅제약 반영 */
 /** 2018-10-23 안국약품 반영 */
/** 2018-10-23 평화오일씰 반영 */
/** 2018-10-24 평화오일씰 반영 */
/** 2018-10-23 오뚜기 협력사 반영 */
/** 2018-10-25 11:20 동국제약 반영 */
/** 2018-10-26 평화오일씰 반영 */
/** 2018-10-27 평화오일씰 반영 */
 /** 2018-10-29 본사Dream 반영 */
/** 2018-10-30 평화오일씰 반영 */
/** 2018-10-30 안국약품 반영 */
/** 2018-10-30 15:10 동국제약 반영 */

/** 2018-10-31 박철완 */
INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'PW_FAIL_LIMIT_CNT', '0', '로그인시 비밀번호를 이 값 이상으로 실패하면 해당 계정은 관리자가 사용자에 대한 계정을 Reset해야만 로그인 가능 설정값이 0이면 무제한으로 로그인 시도 가능', 'Y'); 
 
INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'PW_CHANGE_HIST_CNT', '0', '최근 사용한 비밀번호는 사용할 수 없을때 최근 몇번까지 사용할 수 없는지 설정값. 3이면 최근 3번까지의 사용한 비밀번호는 사용할 수 없음. 0이면 변경이력을 체크하지 않음. IS_CHANGE_PW_NEEDED가 Y일때 사용.', 'Y'); 

/** 2018-10-31 평화오일씰 반영 */
/** 2018-11-01 10:40 동국제약 반영 */
/** 2018-11-01 평화오일씰 반영 */
/** 2018-11-02 평화오일씰 반영 */
/** 2018-11-02 오뚜기 협력사 반영 */
/** 2018-11-05 대웅제약 반영 */
/** 2018-11-06 1030 오뚜기협력사 반영 */
/** 2018-11-06 평화오일씰 반영 */
/** 2018-11-07 평화오일씰 반영 */
/** 2018-11-07 16:30 동국제약 반영 */
/** 2018-11-09 평화오일씰 반영 */

/** 2018-11-13 박철완 */
INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'IS_USE_SSO_LOGIN', 'N', '로그인시 SSO을 사용하는지 여부
SSO를 사용하지 않으면 logout시 index.do로 실행되고
SSO를 사용할 경우 logout는 ssoindex.do로 실행함', 'Y'); 

/** 2018-11-13 평화오일씰 반영 */
/** 2018-11-13 안국약품 반영 */
/** 2018-11-14 1015 오뚜기협력사 반영 */
/** 2018-11-14 평화오일씰 반영 */
/** 2018-11-15 08:15 동국제약 반영 */
/** 2018-11-15 안국약품 반영 */
/** 2018-11-15 평화오일씰 반영 */
/** 2018-11-16 평화오일씰 반영 */
/** 2018-11-19 안국약품 반영 */
/** 2018-11-20 평화오일씰 반영 */
/** 2018-11-21 평화오일씰 반영 */
/** 2018-11-22 평화오일씰 반영 */
/** 2018-11-23 평화오일씰 반영 */
/** 2018-11-26 평화오일씰 반영 */

/** 2018-11-27 박철완 */
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'MAIL_SMTP_AUTH', 'true', 'SMTP 인증 여부(true/false), default false', 'Y'); 

 
/** 2018-11-27 안국약품 반영 */
/** 2018-11-27 평화오일씰 반영 */

/** 2018-11-27 오뚜기협력사 반영 */
/** 2018-11-27 05:30 동국제약 반영 */
/** 2018-11-28 평화오일씰 반영 */
/** 2018-11-28 안국약품 반영 */
/** 2018-11-28 본사Dream 반영 */
/** 2018-11-28 15:45 동국제약 반영 */
/** 2018-11-29 평화오일씰 반영 */
 /** 2018-12-04 대웅제약 반영 */
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
 
/** 2019-01-15 박철완 */ 
  INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
  VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'SSO_AES_KEY', 'DreamSolutionForMaintenance#1234', '외부시스템에 제공한 SSO 암호키 값.  이값을 이용하여 복호화해서 인증여부를 체크함.', 'Y'); 

/** 2019-01-16 동국제약 반영 */
/** 2019-01-31 평화오일씰 반영 */
/** 2019-02-11 본사Dream 반영 */
/** 2019-02-12 안국약품 반영 */
/** 2019-03-04 안국약품 반영 */
/** 2019-03-07 동국제약 반영 */
/** 2019-03-13 현대일렉트릭 반영 */
/** 2019-03-26 오뚜기 반영 */
/** 2019-03-26 14:30 오뚜기 반영 */
/** 2019-03-26 안국약품 반영 */
/** 2019-03-27 로얄캐닌 반영 */
/** 2019-03-28 평화오일씰 반영 */
/** 2019-04-08 평화오일씰 반영 */
/** 2019-04-16 오뚜기 반영 */
/** 2019-04-18 현대일렉트릭 반영 */
/** 2019-04-26 현대일렉트릭 반영 */
/** 2019-05-02 현대일렉트릭 반영 */
/** 2019-05-12 백광산업 반영 */
/** 2019-05-13 평화오일씰 반영 */
/** 2019-05-16 현대일렉트릭 반영 */
/** 2019-05-17 현대일렉트릭 반영 */


/** 2019-05-10 양소영 */
INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
VALUES((SELECT comp_no FROM tacomp WHERE is_use = 'Y' AND init_ct_path_yn = 'Y' AND ROWNUM = 1),SQACONFIG_ID.NEXTVAL,'IS_WOPMWORK_RESCHED', 'N', '1. 예방정비작업이 완료되면 완료시점에 다음 작업예정 스케쥴을 자동으로 변경할지 여부 지정
   WO_TYPE = PMW인 작업 대상
2. Y이면 예방작업완료시점으로 차기 예방작업일자 스케쥴을 재 조정함.
    N이면 스케쥴을 재 조정하지 않음.
3. Ex: Y
4. 이 파라미터 값은 향후 WO_TYPE중에서 PM작업의 항목을 검토하여 개선이 필요함.
java, jsp, javascript에서는 확인이 안됨.', 'Y');

/** 2019-05-21 김정우 */
  INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
  VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'ANT_APK_URL', '', 'Ant apk 파일 다운로드 url', 'Y'); 
    INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
  VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'BEE_APK_URL', '', 'Bee apk 파일 다운로드 url', 'Y'); 
    INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
  VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'CRICKET_APK_URL', '', 'Cricket apk 파일 다운로드 url', 'Y'); 
    INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
  VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'ANT_VERSION_CODE', '', 'Ant Version', 'Y'); 
    INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
  VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'BEE_VERSION_CODE', '', 'Bee Version', 'Y'); 
    INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
  VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'CRIKET_VERSION_CODE', '', 'Cricket Version', 'Y'); 
 
   
/** 2019-05-21 안국약품 반영 */
/** 2019-05-22 오뚜기 본사 반영 */

/** 2019-05-22 현대일렉트릭 반영 */
/** 2019-05-23 평화오일씰 반영 */
/** 2019-05-23 현대일렉트릭 반영 */
  
   /** 2019-05-24 김정우 */
  
UPDATE TACONFIG SET name='CRICKET_VERSION_CODE' WHERE name='CRIKET_VERSION_CODE';

/** 2019-05-24 오뚜기 본사 반영 */
/** 2019-05-29 현대일렉트릭 반영 */
/** 2019-05-30 현대일렉트릭 반영 */
/** 2019-06-03 현대일렉트릭 반영 */
/** 2019-06-05 현대일렉트릭 반영 */
/** 2019-06-07 현대일렉트릭 반영 */
/** 2019-06-10 오뚜기 본사 반영 */
/** 2019-06-11 현대일렉트릭 반영 */
/** 2019-06-12 평화오일씰 반영 */
/** 2019-06-19 현대일렉트릭 반영 */

/** 2019-06-19 김정우 */
INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
  VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'VIDEO_FILE_TYPE', 'mts,mov,m4v,mkv,wmv,avi,mp4', '업로드 허용된 동영상 파일 확장자', 'Y'); 
  
/** 2019-06-19 오뚜기 본사 반영 */
  
/** 2019-06-25 현대일렉트릭 반영 */
/** 2019-07-02 오뚜기 본사 반영 */
/** 2019-07-03 현대일렉트릭 반영 */
/** 2019-07-11 평화오일씰 반영 */
/** 2019-07-12 백광 반영 */
  
  /** 2019-07-23 김정우 */
update taconfig set value$='2.103' where name ='ANT_VERSION_CODE';
update taconfig set value$='2.104' where name ='BEE_VERSION_CODE';
update taconfig set value$='2.101' where name ='CRICKET_VERSION_CODE';
/** 2019-07-24 로얄캐닌 반영 */

/** 2019-08-12 오뚜기(OEMS) 반영 */

  /** 2019-08-16 김정우 */
update taconfig set value$='2.104' where name ='ANT_VERSION_CODE';
update taconfig set value$='2.105' where name ='BEE_VERSION_CODE';
update taconfig set value$='2.104' where name ='ANDROID_VERSION_CODE';

/** 2019-08-21 연우 반영 */

/** 2019-08-26 동국제약 반영 */
/** 2019-08-26 연우 반영 */

/** 2019-09-04 김정우 */
update taconfig set value$='2.105' where name ='ANT_VERSION_CODE';
update taconfig set value$='2.106' where name ='BEE_VERSION_CODE';

/** 2019-09-09 평화오일씰 반영 */

/** 2019-09-10 김정우 */
update taconfig set value$='2.106' where name ='ANT_VERSION_CODE';
update taconfig set value$='2.107' where name ='BEE_VERSION_CODE';
/** 2019-09-18 김정우 */
update taconfig set value$='2.107' where name ='ANT_VERSION_CODE';
/** 2019-10-01 안국약품 반영 */

/** 2019-10-07 김정우 */
update taconfig set value$='2.108' where name ='BEE_VERSION_CODE';
/** 2019-10-08 김정우 */
update taconfig set value$='2.109' where name ='BEE_VERSION_CODE';
/** 2019-10-10 김정우 */
update taconfig set value$='2.108' where name ='ANT_VERSION_CODE';
update taconfig set value$='2.110' where name ='BEE_VERSION_CODE';

/** 2019-10-14 김정우 */
update taconfig set value$='2.111' where name ='BEE_VERSION_CODE';

/** 2019-10-16 현대일렉트릭 반영 */
/** 2019-10-30 평화오일씰 반영 */
/** 2019-11-05 안국약품 반영 */


 /** 2019-11-12 김정우 */
update taconfig set value$='2.110' where name ='ANT_VERSION_CODE';
update taconfig set value$='2.112' where name ='BEE_VERSION_CODE';

 /** 2019-11-15 김정우 */
update taconfig set value$='2.111' where name ='ANT_VERSION_CODE';
update taconfig set value$='2.113' where name ='BEE_VERSION_CODE';

/** 2019-11-22 동국제약 반영 */
/** 2019-11-25 연우 반영 * /
/** 2019-11-26 평화오일씰 반영 */
/** 2019-11-27 연우 반영 */
/** 2019-12-04 안국약품 반영 */
/** 2020-01-07 연우 반영 */
/** 2020-01-09 평화오일씰 phos 반영 */
/** 2020-01-13 현대일렉트릭 hdele 반영 */
/** 2020-01-13 평화오일씰 phos 반영 */

/** 2020-01-17 김정우 추가  */ */
 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'MESSAGE_SERVICE_URL', '', '메시지 전송 요청 URL', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'MESSAGE_KEY_VALUE', '', '메시지 전송 인증 키', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'IS_USE_KAKAO_ALARM_SERVICE', '', '카카오 알림톡 사용여부', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'KAKAO_ALARM_ID', '', '카카오 알림톡 API에 사용할 인증 키', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'KAKAO_ALARM_API_URL', '', '카카오 알림톡 API URL', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'KAKAO_ALARM_PW', '', '카카오 알림톡 API에 인증 PW', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((select comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' and rownum = 1),SQACONFIG_ID.NEXTVAL,'KAKAO_ALARM_SENDER_KEY', '', '카카오 알림톡 API에 인증 KEY', 'Y'); 



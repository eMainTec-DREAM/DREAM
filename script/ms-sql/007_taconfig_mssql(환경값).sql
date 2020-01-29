select 
' INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM)
 VALUES((select top 1 comp_no from tacomp where is_use = ''Y'' and init_ct_path_yn = ''Y''),NEXT VALUE FOR SQACONFIG_ID, '''||NAME||''', '''||VALUE$||''', '''||DESCRIPTION||''', '''||IS_SYSTEM||''' ); ' 
from TACONFIG



---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-11   박철완 최초 생성*/ 
---------------------------------------------------------------------------------------------------------------------------------------------------

 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'MAIL_SENDER_ID', 'oems@ottogi.co.kr', '메일을 보내는 계정', 'N')
go 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'SYSTEM_ADMIN_GROUP', 'SYSTEM', '홈에 어드민 버튼이 보이는 권한그룹', 'Y')
go 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'FILE_DIR', 'D:\mware_data\file\', '첨부파일 저장 경로', 'Y')
go 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'BATCH_EXEC_USER_ID', 'ADMIN', '배치프로그램 실행시 사용할 User', 'Y')
go 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'LOGINKEYVALUE', 'MWARE', 'SSO로그인시 키값', 'Y')
go 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'CLOSING_DAY', '10', '매월 5일이 지나면 전월 데이터 마감', 'N')
go 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM)
 VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'LAST_CLOSED_DATE', '2016-04-30', '최종마감 일자[현재~이값의 월데이타 재집계]', 'N')
go 

---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-17  노정현 추가 작업*/ 
---------------------------------------------------------------------------------------------------------------------------------------------------

INSERT INTO TACONFIG
   (COMP_NO, CONFIG_ID, NAME, VALUE$, DESCRIPTION, IS_SYSTEM)
 VALUES
   ('100', NEXT VALUE FOR SQACONFIG_ID, 'IMG_FILE_TYPE', 'jpg,bmp,jpeg,png,gif,raw,tiff', '업로드 허용파일', 
    'Y')
go
INSERT INTO TACONFIG
   (COMP_NO, CONFIG_ID, NAME, VALUE$, DESCRIPTION, IS_SYSTEM)
 VALUES
   ('100', NEXT VALUE FOR SQACONFIG_ID, 'MAX_FILE_SIZE', '200', '업로드 파일 허용 크기 (MB)', 
    'Y')
go
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-17  노정현 추가 작업 끝*/ 
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-19  박철완 - 작업마감일 설정값.*/ 
---------------------------------------------------------------------------------------------------------------------------------------------------
  INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
    VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'WORK_CLOSE_TERM_DAY', '30', '작업마감완료일간격(며칠전 작업마감완료)', 'N')
go 

    ---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-06-01  박철완 - 암호화 ㄴSEED.*/ 
---------------------------------------------------------------------------------------------------------------------------------------------------
 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM)
 VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'IFAUTHSEED', '123456789', '인터페이스 암호화 Seed', 'Y')
go 
 
 
 /*2016-07-07  박철완 - 가동시간 DEFAULT 값 셋팅...*/  
  
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'DAY_WORKING_TIME', '480', '낮 가동시간', 'Y')
go 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'NIGHT_WORKING_TIME', '480', '밤 가동시간', 'Y')
go 
  INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'BD_MONTHLY_TVALUE', '0.3', 'B/D(%)목표 월별 default 값', 'Y')
go 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'MTTR_MONTHLY_TVALUE', '20', 'MTTR(분)  월별 default값', 'Y')
go 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'MTBF_MONTHLY_TVALUE', '20000', 'MTBF(분)  월별 default값', 'Y')
go 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'STOCK_MONTHLY_TVALUE', '6000000', 'STOCK  월별 default 값', 'Y')
go 

 /** 2016-07-18 노정현 ERP Interface Info */
  INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM)
 VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'IF_ERP_URL', '10.214.100.222:5510', 'ERP SAP URL', 'Y')
go 
 
  INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM)
 VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'IF_ERP_USER_NAME', 'if_dymos', 'ERP SAP User Name', 'Y')
go 
 
  INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM)
 VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'IF_ERP_PASSWORD', 'if_dymos', 'ERP SAP Password', 'Y')
go  
 
  /*2016-11-18  이규선 - 모니터링페이지 넘김 조건 시간 ...*/  
  INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'POPUP_NEXT_TIME', '60', '모니터링페이지 팝업 넘기시간 조건(초단위)', 'N')
go
  
/** 2016-11-21 노정현 Global Monitering Parameter for DB Connection 
 */  
  INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'GLOBAL_URL', 'jdbc:oracle:thin:@10.214.100.156:1521:GMMS', 'GLOBAL KPI URL', 'Y')
go  --158 (dev)

INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'GLOBAL_USER_NAME', 'gmmsadmin', 'GLOBAL KPI USER NAME', 'Y')
go 

INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'GLOBAL_PASSWORD', 'gmmsadmin1#', 'GLOBAL KPI PASSWORD', 'Y')
go 

INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'GLOBAL_DRIVE_NAME', 'oracle.jdbc.driver.OracleDriver', 'GLOBAL KPI DB Driver', 'Y')
go

/** 2017-01-10 SLP 설비고장+라인고장 목표값 수치 */

  INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'BD_MONTHLY_TVALUE_EQ', '2.0', 'B/D(%)목표(설비고장+라인고장) 월별 default 값', 'Y')
go 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'MTTR_MONTHLY_TVALUE_EQ', '30', 'MTTR(분)(설비고장+라인고장)  월별 default값', 'Y')
go 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'MTBF_MONTHLY_TVALUE_EQ', '570', 'MTBF(분)(설비고장+라인고장)  월별 default값', 'Y')
go 
 
 
 -----------------------------------------------------------------------------
--------------------------------유한킴벌리 patch완료 2017.01.13일-------------------
-----------------------------------------------------------------------------
 
 
 /** 2017-01-18  노정현 추가  */
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'AC_LENGTH', '10', 'Length of AutoComplete Result', 'Y')
go 
 
 
 /** 2017-01-25 김정우 추가 */
  INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'BDN_MONTHLY_TVALUE_EQ', '20', 'B/D(건수)목표(설비고장+라인고장) 월별 default 값', 'Y')
go 
  INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'BDN_MONTHLY_TVALUE', '2.0', 'B/D(건수)목표(라인고장) 월별 default 값', 'Y')
go 
  
  /** 2017-02-20 김정우 추가 ※공장별로 값 변경 */
  INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'PART_GRADE', 'B', 'PART_GRADE 기본 값', 'Y')
go 
  
  /** 2017-03-17 고장시간 목표 추가 */
    INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'BDH_MONTHLY_TVALUE', '1.0', 'B/D(시간)목표(라인고장) 월별 default 값', 'Y')
go 
  INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'BDH_MONTHLY_TVALUE_EQ', '2.0', 'B/D(시간)목표(설비고장+라인고장) 월별 default 값', 'Y')
go 



/** 2017-04-3 메일전송자 추가 */  
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'MAIL_SENDER_PWD', 'emaintec4231', '메일보내는 패스워드', 'Y')
go 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'MAIL_SENDER_SERVER', 'mail.emaintec.com', '메일보는서버주소', 'Y')
go 
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
VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'IS_CHANGE_PW_NEEDED', 'Y', '주기적으로 로그인 패스워드를 변경해야 하는지 여부 Y:주기적으로 변경해야 함, N:하지 않아도 됨', 'Y');
go
INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'PW_CHANGE_CYCLE_DAY', '60', '주기적으로 로그인 패스워드를 변경해야 할때 몇일마다 변경해야 하는지 설정값, 30이면 30일마다 변경', 'Y'); 
go
INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'IS_PW_SAFETY_LENGTH', 'Y', '패스워드 변경할때 패스워드 복잡도(특수문자, 숫자,대문자,소문자, 8자이상)를 적용할지 여부', 'Y');
go

/*2017.05.23 박철완**/


/** 2017-09-19 노정현 추가  */

INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'FILE_TYPE', 'asp,aspx,jsp,php,cer,cdx,asa,php3,war', '파일 업로드 금지 확장자', 'Y');



/*2017.12.03 박철완***/
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'DREAM_URL', 'http://localhost:8080/dream/index.do', '메일이나 기타 주소에서 Dream바로가기 연결주소', 'Y');
 
INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'IS_USE_MAIL_SERVICE', 'N', '메일링 서비스를 사용할지 여부 
Y:메일링 서비스를 사용함. 메일계정등이 설정되어 있어야 함
N:메일링 서비스를 사용하지 않음', 'Y');

INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'IS_USE_PART_GRADE', 'N', '부품을 재고등급으로 나누어 사용하는지 여부 Y: A,B등급으로 사용하는 곳도 있고 N: A나 B 한등급으로 사용하는게 있음', 'Y');


 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'IS_ORDER_CONFIRM_TO_ISSUE', 'N', '작업오더 확정시 자채출고를 자동으로 처리할지 여부
 Y : 오더확정시 출고처리 완료
 N: 오더확정시 출고하지고 않고 출고화면에서 수동처리', 'Y');
 
 
 /** 2017-12-21 김정우 추가 */
 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'MAIL_SENDER_IS_SSL', 'N', '메일서버 SSL 연결 여부', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES('100',NEXT VALUE FOR SQACONFIG_ID,'MAIL_SENDER_PORT', '465', '메일서버 PORT', 'Y');
 
 
 
 
 /*2017-12-30 박철완.*/
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y'),NEXT VALUE FOR SQACONFIG_ID,'IS_USE_ASSET_REVISION', 'Y', '설비생성시에 제개정을 사용할지 여부 default N', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y'),NEXT VALUE FOR SQACONFIG_ID,'IS_USE_PM_REVISION', 'Y', '예방점검생성시에 제개정을 사용할지 여부 default N', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y'),NEXT VALUE FOR SQACONFIG_ID,'IS_USE_SCRNTRACE', 'Y', '스크린 Trace를 사용할지 여부 default N', 'Y'); 

 /** 2018-01-31 김정우 추가 */
  INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((SELECT top 1 comp_no FROM tacomp WHERE is_use = 'Y' AND init_ct_path_yn = 'Y'),next value for sqaCONFIG_ID,'SAP_ERP_ID', '', 'SAP 접속ID', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((SELECT top 1 comp_no FROM tacomp WHERE is_use = 'Y' AND init_ct_path_yn = 'Y'),next value for sqaCONFIG_ID,'SAP_ERP_PASSWORD', '', 'SAP 접속 PASSWORD', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((SELECT top 1 comp_no FROM tacomp WHERE is_use = 'Y' AND init_ct_path_yn = 'Y' ),next value for sqaCONFIG_ID,'SAP_ERP_CLIENT', '', 'SAP 접속 Client', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((SELECT top 1 comp_no FROM tacomp WHERE is_use = 'Y' AND init_ct_path_yn = 'Y' ),next value for sqaCONFIG_ID,'SAP_ERP_LANGUAGE', '', 'SAP 언어', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((SELECT top 1 comp_no FROM tacomp WHERE is_use = 'Y' AND init_ct_path_yn = 'Y' ),next value for sqaCONFIG_ID,'SAP_ERP_SERVER_IP', '', 'SAP SERVER IP', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((SELECT top 1 comp_no FROM tacomp WHERE is_use = 'Y' AND init_ct_path_yn = 'Y' ),next value for sqaCONFIG_ID,'SAP_ERP_SYSTEM_NUMBER', '', 'SAP SYSTEM NO', 'Y'); 

 /*2018-2-11 박철완.*/
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' ),NEXT VALUE FOR SQACONFIG_ID,'SERVER_DATABASE', 'Mssql', '서버 데이타 베이스 종류 - 레포트 사용할때 sql', 'Y'); 
 
 
 /*2018-2-15 박철완.*/
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' ),NEXT VALUE FOR SQACONFIG_ID,'USER_LICENSE_TYPE', 'CONCURRENT', '라이센스 계약 방식[NAMED, CONCURRENT]', 'Y'); 

 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' ),NEXT VALUE FOR SQACONFIG_ID,'USER_LICENSE_CNT', '20', '라이센스 계약 갯수', 'Y'); 

 
 /*2018-03-03 박철완*/ 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' ),NEXT VALUE FOR SQACONFIG_ID,'WORK_START_BASE_TIME', '0600', '작업시작 기준시간, 하루는 이 시간부터 산정', 'Y'); 

 /**2018-03-05 김정우 */
 
 INSERT INTO 
 TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' )
 ,NEXT VALUE FOR SQACONFIG_ID,'ANDROID_VERSION_CODE', '', '안드로이드 버전', 'Y'); 
 
 INSERT INTO 
 TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' )
 ,NEXT VALUE FOR SQACONFIG_ID,'ANDROID_APK_URL', '', '안드로이드 apk 다운로드 URL', 'Y'); 

 /*2018-03-03 박철완*/
  INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' ),NEXT VALUE FOR SQACONFIG_ID,'IS_WORK_PLAN', 'Y', '작업계획에서 확정하면 작업결과가 만들지 여부, 예방작업에서 이 값을 이용하여 계획이나 결과로 데이타를 만듬', 'Y'); 

/**  2018-03-30 매일유업 반영 */
/**  2018-04-18 매일유업 반영 */
/**  2018-04-25 매일유업 반영 */
/**  2018-05-02 매일유업 반영 */
/**  2018-05-09 매일유업 반영 */
/**  2018-05-25 매일유업 반영 */
/**  2018-06-01 매일유업 반영 */
/**  2018-06-19 매일유업 반영 */
/**  2018-06-20 매일유업 반영 */
/**  2018-06-22 매일유업 반영 */
/**  2018-06-27 매일유업 반영 */
/**  2018-07-02 매일유업 반영 */
/**  2018-07-03 매일유업 반영 */
/**  2018-07-12 매일유업 반영 */
 
 
/**  2018-07-13 DREAM 반영 */
 
 
/*2018-07-14 박철완*/
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y'),NEXT VALUE FOR SQACONFIG_ID,'IS_USE_PAGE_ACCESS_LOGGING', 'N', '사용자가 접속한 페이지가 어떤 페이지인지 로그을 남길지 여부. 화면을 접근할때마다 TAACCESSCCLOG 에 기록함. 속도가 느렬질수 있고 데이타를 주기적으로 지워야 함', 'Y'); 

/**  2018-07-19 매일유업 반영 */
/**  2018-07-19 국도 반영 */
 
 /** 2018-07-23 김정우 */
INSERT into TACONFIG(comp_no,config_id,name,value$,description,is_system)
values ((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y'),next value for sqaconfig_id,'IS_USE_SMS_SERVICE','N','SMS 발송서비스 사용여부','Y');
INSERT into TACONFIG(comp_no,config_id,name,value$,description,is_system)
values ((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y'),next value for sqaconfig_id,'SMS_API_KEY','','COOL SMS API KEY','Y');
INSERT  into  TACONFIG(comp_no,config_id,name,value$,description,is_system)
values ((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y'),next value for sqaconfig_id,'SMS_API_SECRET','','COOL SMS API SECRET','Y');
INSERT  into  TACONFIG(comp_no,config_id,name,value$,description,is_system)
values ((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y'),next value for sqaconfig_id,'SMS_SEND_NUMBER','','COOL SMS SEND NUMBER','Y');




/*2018-07-24 박철완*/
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y'),next value for sqaconfig_id,'SMS_MONTH_CNT', '200', 'SMS 월 사용가능갯수', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y'),next value for sqaconfig_id,'SMS_TOT_CNT', '20000', 'SMS 총 사용가능갯수[사용종료일 기준]', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y'),next value for sqaconfig_id,'SMS_EXPIRE_DATE', '9999-12-31', 'SMS사용종료일', 'Y'); 
 
/** 2018-07-24 국도 반영 */
/** 2018-07-24 DREAM 반영 */
/** 2018-07-26 국도 반영 */
/** 2018-07-27 매일유업 반영 */
/** 2018-08-07 국도 반영 */
/** 2018-08-08 국도 반영 */
/** 2018-08-09 국도 반영 */
/** 2018-08-10 국도 반영 */
 
 
  /** 2018-08-07 노정현 추가 */
 
  INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y'),next value for sqaconfig_id,'IS_USE_AUDIT_TRAIL', 'N', 'Audit Trail 사용여부', 'Y'); 

 
   INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y'),next value for sqaconfig_id,'IS_USE_AUDIT_TRAIL_READ', 'N', 'Audit Trail READ 사용여부', 'Y'); 

/** 2018-08-17 국도 반영 */
/** 2018-08-21 DREAM 반영 */
/** 2018-08-22 매일유업 반영 */
/** 2018-08-28 국도 반영 */
 
 
/** 2018-08-31 박철완 추가 */
INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM)  
VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y'),next value for SQACONFIG_ID,'IS_USE_OTP_LOGIN', 'N', '사용자가 로그인할때 OTP로그인을 사용할지 여부', 'Y'); 

/** 2018-09-04 박철완 추가 */  
INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' ),NEXT VALUE FOR SQACONFIG_ID,'IS_PR_CONFIRM_TO_RECEIPT', 'N', '구매청구 확정시 구매입고로 데이터 생성여부
 Y : 구매입고에 작성중으로 데이터 생성함
 N: 구매입고에 작성중으로 데이터 생성하지 않음.', 'Y'); 
 
/** 2018-09-04 국도 반영 */
/** 2018-09-06 매일유업 반영 */
 
 
/** 2018-09-07 박철완 추가 */  

INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y'),NEXT VALUE FOR SQACONFIG_ID,'MANAGER_MAIL_ID', 'reply@emaintec.com', '시스템운영담당자 메일주소', 'Y'); 

/** 2018-09-10 국도 반영 */ 
 
 
/** 2018-09-19 박철완 추가 */
INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' ),NEXT VALUE FOR SQACONFIG_ID,'IS_COMP_PLANWRK_MOLOAD', 'N', '계획작업 업로드시 계획작업 상태를 완료로 변경할지 여부', 'Y'); 
INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' ),NEXT VALUE FOR SQACONFIG_ID,'IS_COMP_INSWRK_MOLOAD', 'N', '점검작업 업로드시 점검작업 상태로 완료로 변경할지 여부', 'Y'); 
INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' ),NEXT VALUE FOR SQACONFIG_ID,'IS_COMP_BMWRK_MOLOAD', 'N', '고장작업 업로드시 고장작업 상태를 완료로 변경할지 여부', 'Y'); 
INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' ),NEXT VALUE FOR SQACONFIG_ID,'IS_COMP_CALWRK_MOLOAD', 'N', '교정작업 업로드시 교정작업 상태를 완료로 변경할지 여부', 'Y'); 

/** 2018-09-20 국도 반영 */
/** 2018-09-20 매일유업 반영 */

/** 2018-10-11 김정우 추가 */
INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' ),NEXT VALUE FOR SQACONFIG_ID,'EMPTY_FIELD_VALUE', 'N/A', '빈 필드 기본값', 'Y');

/** 2018-10-12 국도화학 반영 */

/** 2018-10-15 김정우 추가 */
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' ),NEXT VALUE FOR SQACONFIG_ID,'BARCODE_DIVIDER', '', '바코드 구분자', 'Y');
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' ),NEXT VALUE FOR SQACONFIG_ID,'BARCODE_SEQ', '', '바코드를 읽었을 때 사용할 영역의 순서', 'Y');



 /** 2018-10-16 박철완 추가 */
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' ),next value for SQACONFIG_ID,'IS_LOCK_EXCEL_DOWN_FILE', 'N', '엑셀다운로드시 암호화여부', 'Y');
 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((select top 1  comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' ),next value for SQACONFIG_ID,'IS_LOCK_EXCEL_PASSWORD', '1234', '엑셀다운로된 파일의 암호값', 'Y'); 

/** 2018-10-18 국도화학 반영 */
/** 2018-10-25 국도화학 반영 */
/** 2018-10-26 국도화학 반영 */
/** 2018-10-30 매일유업 반영 */
 
 /** 2018-10-31 박철완 */
INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y'),next value for SQACONFIG_ID,'PW_FAIL_LIMIT_CNT', '0', '로그인시 비밀번호를 이 값 이상으로 실패하면 해당 계정은 관리자가 사용자에 대한 계정을 Reset해야만 로그인 가능 설정값이 0이면 무제한으로 로그인 시도 가능', 'Y'); 
 
INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y'),next value for SQACONFIG_ID,'PW_CHANGE_HIST_CNT', '0', '최근 사용한 비밀번호는 사용할 수 없을때 최근 몇번까지 사용할 수 없는지 설정값. 3이면 최근 3번까지의 사용한 비밀번호는 사용할 수 없음. 0이면 변경이력을 체크하지 않음. IS_CHANGE_PW_NEEDED가 Y일때 사용.', 'Y'); 

/** 2018-11-01 국도화학 반영 */
/** 2018-11-02 매일유업 반영 */
/** 2018-11-07 본사 DREAM 반영 */
/** 2018-11-08 국도화학 반영 */
/** 2018-11-08 매일유업 반영 */

/** 2018-11-13 박철완 */
INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y'),next value for SQACONFIG_ID,'IS_USE_SSO_LOGIN', 'N', '로그인시 SSO을 사용하는지 여부
SSO를 사용하지 않으면 logout시 index.do로 실행되고
SSO를 사용할 경우 logout는 ssoindex.do로 실행함', 'Y'); 

/** 2018-11-15 국도화학 반영 */
/** 2018-11-16 국도화학 반영 */
/** 2018-11-19 본사 국도화학 반영 */
/** 2018-11-19 국도화학 반영 */ 
/** 2018-11-20 국도화학 반영 */
/** 2018-11-21 국도화학 반영 */


/** 2018-11-27 박철완 */
  INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y'),next value for SQACONFIG_ID,'MAIL_SMTP_AUTH', 'true', 'SMTP 인증 여부(true/false), default false', 'Y'); 
 
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
 
 
/** 2019-01-15 박철완 */ 
  INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
 VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' ),next value for SQACONFIG_ID,'SSO_AES_KEY', 'DreamSolutionForMaintenance#1234', '외부시스템에 제공한 SSO 암호키 값.  이값을 이용하여 복호화해서 인증여부를 체크함.', 'Y'); 

  
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
/** 2019-04-04 매일유업 반영 */
/** 2019-04-15 고려용접봉 반영 */
/** 2019-04-17 국도화학 반영 */
/** 2019-04-18 매일유업 반영 */
/** 2019-04-26 매일유업 반영 */
/** 2019-05-08 매일유업 반영 */
/** 2019-05-10 국도화학 반영 */


/** 2019-05-10 양소영 */
INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
VALUES((SELECT TOP 1 comp_no FROM tacomp WHERE is_use = 'Y' AND init_ct_path_yn = 'Y'),NEXT VALUE FOR SQACONFIG_ID,'IS_WOPMWORK_RESCHED', 'N', '1. 예방정비작업이 완료되면 완료시점에 다음 작업예정 스케쥴을 자동으로 변경할지 여부 지정
   WO_TYPE = PMW인 작업 대상
2. Y이면 예방작업완료시점으로 차기 예방작업일자 스케쥴을 재 조정함.
    N이면 스케쥴을 재 조정하지 않음.
3. Ex: Y
4. 이 파라미터 값은 향후 WO_TYPE중에서 PM작업의 항목을 검토하여 개선이 필요함.
java, jsp, javascript에서는 확인이 안됨.', 'Y');

/** 2019-05-21 김정우 */
  INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
  VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' ),next value for SQACONFIG_ID,'ANT_APK_URL', '', 'Ant apk 파일 다운로드 url', 'Y'); 
    INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
  VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' ),next value for SQACONFIG_ID,'BEE_APK_URL', '', 'Bee apk 파일 다운로드 url', 'Y'); 
    INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
  VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' ),next value for SQACONFIG_ID,'CRICKET_APK_URL', '', 'Cricket apk 파일 다운로드 url', 'Y'); 
    INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
  VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' ),next value for SQACONFIG_ID,'ANT_VERSION_CODE', '', 'Ant Version', 'Y'); 
    INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
  VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' ),next value for SQACONFIG_ID,'BEE_VERSION_CODE', '', 'Bee Version', 'Y'); 
    INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
  VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' ),next value for SQACONFIG_ID,'CRIKET_VERSION_CODE', '', 'Cricket Version', 'Y'); 


/** 2019-05-24 국도화학 반영 */
  
   /** 2019-05-24 김정우 */
  
UPDATE TACONFIG SET name='CRICKET_VERSION_CODE' WHERE name='CRIKET_VERSION_CODE';

/** 2019-05-24 고려용접봉 반영 */
/** 2019-05-29 고려용접봉 반영 */
/** 2019-05-30 매일유업 반영 */
/** 2019-06-04 매일유업 반영 */
/** 2019-06-17 경보제약 반영 */
/** 2019-06-17 고려용접봉 반영 */
   /** 2019-06-19 김정우 */

     INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) 
  VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' ),next value for SQACONFIG_ID,'VIDEO_FILE_TYPE', 'mts,mov,m4v,mkv,wmv,avi,mp4', '업로드 허용된 동영상 파일 확장자', 'Y'); 

/** 2019-06-26 고려용접봉 반영 */
/** 2019-06-26 국도화학 반영 */
/** 2019-06-26 매일유업 반영 */
/** 2019-06-28 매일유업 반영 */
/** 2019-07-10 경보제약 반영 */
/** 2019-07-18 경보제약 반영 */
  /** 2019-07-23 김정우 */
update taconfig set value$='2.103' where name ='ANT_VERSION_CODE';
update taconfig set value$='2.104' where name ='BEE_VERSION_CODE';
update taconfig set value$='2.101' where name ='CRICKET_VERSION_CODE';
  

/** 2019-07-25 경보제약 반영 */
/** 2019-07-30 매일유업 반영 */
/** 2019-08-07 경보제약 반영 */ 
/** 2019-08-09 경보제약 반영 */
/** 2019-08-12 경보제약 반영 */
/** 2019-08-13 경보제약 반영 */
  /** 2019-08-16 김정우 */
update taconfig set value$='2.104' where name ='ANT_VERSION_CODE';
update taconfig set value$='2.105' where name ='BEE_VERSION_CODE';
update taconfig set value$='2.104' where name ='ANDROID_VERSION_CODE';

/** 2019-08-19 경보제약 반영 */
/** 2019-08-22 경보제약 반영 */
/** 2019-08-23 국도화학 반영 */
/** 2019-08-26 본사 경보제약 반영 */
/** 2019-08-27 경보제약 반영 */
/** 2019-08-29 매일유업 반영 */

/** 2019-09-04 김정우 */
update taconfig set value$='2.105' where name ='ANT_VERSION_CODE';
update taconfig set value$='2.106' where name ='BEE_VERSION_CODE';

/** 2019-09-05 경보제약 반영 */
/** 2019-09-10 김정우 */
update taconfig set value$='2.106' where name ='ANT_VERSION_CODE';
update taconfig set value$='2.107' where name ='BEE_VERSION_CODE';

/** 2019-09-11 경보제약 반영 */
/** 2019-09-18 본사 국도화학 반영 */
/** 2019-09-18 김정우 */
update taconfig set value$='2.107' where name ='ANT_VERSION_CODE';
/** 2019-10-01 매일유업 반영 */
/** 2019-10-02 경보제약 반영 */


/** 2019-10-07 김정우 */
update taconfig set value$='2.108' where name ='BEE_VERSION_CODE';
/** 2019-10-08 김정우 */
update taconfig set value$='2.109' where name ='BEE_VERSION_CODE';
/** 2019-10-10 김정우 */
update taconfig set value$='2.108' where name ='ANT_VERSION_CODE';
update taconfig set value$='2.110' where name ='BEE_VERSION_CODE';
/** 2019-10-14 김정우 */
update taconfig set value$='2.111' where name ='BEE_VERSION_CODE';
/** 2019-10-14 매일유업 반영 */
/** 2019-10-17 매일유업 반영 */
/** 2019-10-22 고려용접봉 반영 */
/** 2019-10-29 경보제약 반영 */
/** 2019-11-05 본사 국도화학 반영 */
/** 2019-11-05 고려용접봉 반영 */
/** 2019-11-06 국도화학 반영 */

 /** 2019-11-12 김정우 */
update taconfig set value$='2.110' where name ='ANT_VERSION_CODE';
update taconfig set value$='2.112' where name ='BEE_VERSION_CODE';

/** 2019-11-14 고려용접봉 반영 */

 /** 2019-11-15 김정우 */
update taconfig set value$='2.111' where name ='ANT_VERSION_CODE';
update taconfig set value$='2.113' where name ='BEE_VERSION_CODE';
/** 2019-11-20 매일유업 반영 */
/** 2019-12-02 매일유업 반영 */
/** 2019-12-13 한국내화 반영 */
/** 2019-12-20 고려용접봉 반영 */

/** 2020-01-03 azdream - AZURE CLOUD DREAM2 반영 */
/** 2020-01-09 고려용접봉 반영*/
/** 2020-01-09 매일유업 반영 */
/** 2020-01-14 한국내화 kref 반영 */

/** 2020-01-17 김정우 */
  INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' ),NEXT VALUE FOR SQACONFIG_ID,'MESSAGE_SERVICE_URL', '', '메시지 전송 요청 URL', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' ),NEXT VALUE FOR SQACONFIG_ID,'MESSAGE_KEY_VALUE', '', '메시지 전송 인증 키', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' ),NEXT VALUE FOR SQACONFIG_ID,'IS_USE_KAKAO_ALARM_SERVICE', 'N', '카카오 알림톡 사용여부', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' ),NEXT VALUE FOR SQACONFIG_ID,'KAKAO_ALARM_ID', '', '카카오 알림톡 API에 사용할 인증 키', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' ),NEXT VALUE FOR SQACONFIG_ID,'KAKAO_ALARM_API_URL', '', '카카오 알림톡 API URL', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' ),NEXT VALUE FOR SQACONFIG_ID,'KAKAO_ALARM_PW', '', '카카오 알림톡 API에 인증 PW', 'Y'); 
 INSERT INTO TACONFIG(COMP_NO,CONFIG_ID,NAME,VALUE$,DESCRIPTION,IS_SYSTEM) VALUES((select top 1 comp_no from tacomp where is_use = 'Y' and init_ct_path_yn = 'Y' ),NEXT VALUE FOR SQACONFIG_ID,'KAKAO_ALARM_SENDER_KEY', '', '카카오 알림톡 API에 인증 KEY', 'Y'); 

/** 2020-01-17 azdream - AZURE CLOUD DREAM2 반영 */
/** 2020-01-17 매일유업 반영 */

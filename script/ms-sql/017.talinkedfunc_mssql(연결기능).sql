

/** 2018-05-15 양소영 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use, remark) VALUES( NEXT VALUE FOR sqaLINKEDFUNC_ID, 'ASMB', 'asmb', '부위보기', 'Y', '');

/** 2018-05-15 김영주 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES(NEXT VALUE FOR sqalinkedfunc_id, 'EQMACH' , 'machEquipment', '설비보기' ,'Y');
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES(NEXT VALUE FOR sqalinkedfunc_id, 'EQTOOL' , 'toolsequipment', '계측기보기' ,'Y');

/** 2018-05-15 김영주 */
UPDATE TALINKEDFUNC SET linkedfunc_method = LOWER(linkedfunc_method) WHERE linkedfunc_no = 'EQMACH';

/** 2018-05-17 김영주 */
UPDATE TAPGLINKEDFUNC SET key_no = 'equipment' WHERE key_no = 'eqMach';

/**  2018-05-25 매일유업 반영 */
/**  2018-06-01 매일유업 반영 */

/** 2018-06-12 김영주 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES( NEXT VALUE FOR sqalinkedfunc_id, 'EQBM' , 'eqbm', '고장이력' ,'Y');

/** 2018-06-18 김영주 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES( NEXT VALUE FOR sqalinkedfunc_id, 'PTINFO' , 'ptinfo', '부품정보' ,'Y');
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES( NEXT VALUE FOR sqalinkedfunc_id, 'PTRECHIST' , 'ptrechist', '입고이력' ,'Y');
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES( NEXT VALUE FOR sqalinkedfunc_id, 'PTISSHIST' , 'ptisshist', '출고이력' ,'Y');
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES( NEXT VALUE FOR sqalinkedfunc_id, 'PTUSEHIST' , 'ptusehist', '사용이력' ,'Y');
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES( NEXT VALUE FOR sqalinkedfunc_id, 'PTCURRSTOCK' , 'ptcurrstock', '현재고' ,'Y');

/**  2018-06-19 매일유업 반영 */

/** 2018-06-20 양소영 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use) VALUES( NEXT VALUE FOR sqalinkedfunc_id, 'REVISIONHISTORY', 'revisionhistory', '개정이력','Y');
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use) VALUES( NEXT VALUE FOR sqalinkedfunc_id, 'EQPDF', 'eqpdf', '설비이력출력','Y');
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use) VALUES( NEXT VALUE FOR sqalinkedfunc_id, 'HISTORY', 'history', '변경이력보기','Y');
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use) VALUES( NEXT VALUE FOR sqalinkedfunc_id, 'EQINFOPDF' , 'eqinfopdf', '설비등록카드','Y');
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use) VALUES( NEXT VALUE FOR sqalinkedfunc_id, 'PMWO', 'pmwo', '예방작업이력','Y');
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use) VALUES( NEXT VALUE FOR sqalinkedfunc_id, 'PMMSTR', 'pmmstr', '예방작업기준서','Y');
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use) VALUES( NEXT VALUE FOR sqalinkedfunc_id, 'USEPARTS', 'useparts', '사용부품이력','Y');

/**  2018-06-20 매일유업 반영 */
/**  2018-06-22 매일유업 반영 */
/**  2018-06-27 매일유업 반영 */

/** 2018-06-28 김영주 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES( NEXT VALUE FOR sqalinkedfunc_id, 'EXLUPLOAD' , 'exlupload', '엑셀데이터업로드 목록보기' ,'Y');

/**  2018-07-02 매일유업 반영 */
/**  2018-07-03 매일유업 반영 */

/** 2018-07-05 이지수 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use) VALUES(NEXT VALUE FOR sqalinkedfunc_id, 'WOREQ', 'woreq', '요청접수','Y');

/**  2018-07-12 매일유업 반영 */

/** 2018-07-12 이지수 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use) VALUES(NEXT VALUE FOR sqalinkedfunc_id, 'REPAIRXPDF', 'repairxpdf', '수리불가목록출력','Y');


/**  2018-07-13 DREAM 반영 */
/**  2018-07-19 매일유업 반영 */
/**  2018-07-19 국도 반영 */
/** 2018-07-24 국도 반영 */
/** 2018-07-24 DREAM 반영 */
/** 2018-07-26 국도 반영 */
/** 2018-07-27 매일유업 반영 */

/** 2018-08-06 김영주 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES( NEXT VALUE FOR sqalinkedfunc_id, 'AUDTRAIL' , 'audtrail', 'Audit Trail' ,'Y');

/** 2018-08-07 국도 반영 */
/** 2018-08-08 국도 반영 */
/** 2018-08-09 국도 반영 */
/** 2018-08-10 국도 반영 */
/** 2018-08-17 국도 반영 */
/** 2018-08-21 DREAM 반영 */
/** 2018-08-22 매일유업 반영 */
/** 2018-08-28 국도 반영 */

/** 2018-08-31 김영주 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES(NEXT VALUE FOR sqalinkedfunc_id, 'PMCPDF' , 'pmcpdf', '교정성적서 출력' ,'Y');

/** 2018-09-03 양소영 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES(NEXT VALUE FOR sqalinkedfunc_id, 'WOPMC' , 'wopmc', '교정이력' ,'Y');
 
/** 2018-09-04 국도 반영 */
/** 2018-09-06 매일유업 반영 */
/** 2018-09-10 국도 반영 */

/** 2018-09-17 양소영 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES(NEXT VALUE FOR sqalinkedfunc_id, 'VENDOR' , 'vendor', '거래처' ,'Y');

/* 2018-09-20 김영주 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES(NEXT VALUE FOR sqalinkedfunc_id, 'FMEA' , 'fmea', '고장영향성평가' ,'Y');

/** 2018-09-20 국도 반영 */
/** 2018-09-20 매일유업 반영 */

/** 2018-09-28 양소영 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES( NEXT VALUE FOR sqalinkedfunc_id, 'PMSTD' , 'pmstd', '기준서', 'Y' ); 

/** 2018-10-05 김영주 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES(NEXT VALUE FOR sqalinkedfunc_id, 'INSSCHED' , 'inssched', '연간 점검일정 출력' ,'N');

/** 2018-10-12 국도화학 반영 */

/** 2018-10-12 김은아 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES(NEXT VALUE FOR sqalinkedfunc_id, 'WOPLAN' , 'woplan', '작업계획보기' ,'N');

/** 2018-10-16 이근환 */
INSERT INTO TALINKEDFUNC(linkedfunc_id,linkedfunc_no,linkedfunc_method,description,is_use)
VALUES(NEXT VALUE FOR sqalinkedfunc_id,'WOHIST','wohist','작업목록보기','N');

/** 2018-10-18 국도화학 반영 */
/** 2018-10-25 국도화학 반영 */

/** 2018-10-25 김영주 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use) VALUES( NEXT VALUE FOR sqalinkedfunc_id, 'RAPRINT', 'raprint', '위험분석출력','N');

/** 2018-10-26 국도화학 반영 */

/** 2018-10-29 김영주 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use) VALUES( NEXT VALUE FOR sqalinkedfunc_id, 'REQPDF', 'reqpdf', '의뢰서출력','N');

/** 2018-10-30 매일유업 반영 */

/** 2018-10-31 김영주 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES(NEXT VALUE FOR sqalinkedfunc_id, 'WOPDF' , 'wopdf', '작업결과출력' ,'Y');

/** 2018-11-01 국도화학 반영 */
/** 2018-11-02 매일유업 반영 */
/** 2018-11-07 본사 DREAM 반영 */
/** 2018-11-08 국도화학 반영 */
/** 2018-11-08 매일유업 반영 */
/** 2018-11-15 국도화학 반영 */
/** 2018-11-16 국도화학 반영 */

/** 2018-11-19 김영주 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES(NEXT VALUE FOR sqalinkedfunc_id, 'COPY' , 'copy', '복사' ,'N');

/** 2018-11-19 본사 국도화학 반영 */
/** 2018-11-19 국도화학 반영 */ 
/** 2018-11-20 국도화학 반영 */
/** 2018-11-21 국도화학 반영 */

/** 2018-11-23 김정우 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES(next value for sqalinkedfunc_id, 'WOCONFIRMPRINT' , 'woconfirmprint', '확인절차서출력' ,'Y');

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


/** 2019-02-19 김은아 */
INSERT INTO TALINKEDFUNC (LINKEDFUNC_ID, LINKEDFUNC_NO, LINKEDFUNC_METHOD, DESCRIPTION, IS_USE) VALUES (next value for SQALINKEDFUNC_ID, 'INSPDF', 'inspdf', '점검결과출력', 'Y');
 
/** 2019-02-20 국도화학 반영 */
/** 2019-02-26 국도화학 반영 */
/** 2019-03-05 매일유업 반영 */


/** 2019-02-12 양소영 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES( NEXT VALUE FOR sqalinkedfunc_id, 'PRINT' , 'print', '출력', 'Y' ); 

/** 2019-03-11 이근환 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use, REMARK)
VALUES(NEXT VALUE FOR sqalinkedfunc_id, UPPER('WO') , 'wo', '작업서' ,'Y','선택한 작업의 상세화면을 조회');


/** 2019-03-12 김영주 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use, REMARK)VALUES(NEXT VALUE FOR sqalinkedfunc_id, UPPER('WOREQDETAIL') , 'woreqdetail', '요청서' ,'Y','선택한 요청접수의 상세화면을 조회');


/** 2019-03-13 국도화학 반영 */
/** 2019-03-13 고려용접봉 반영 */


/** 2019-03-13 양소영 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use, REMARK) VALUES(NEXT VALUE FOR sqalinkedfunc_id, 'WOPMI' , 'wopmi', '예방점검 작업서' ,'Y','선택한 점검 작업의 상세화면을 조회');

/** 2019-03-19 매일유업 반영 */



/** 2019-03-26 김정우 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use, REMARK) VALUES(NEXT VALUE FOR sqalinkedfunc_id, 'VIEWDETAIL' , 'viewDetail', '상세보기' ,'Y','상세보기');
 
/** 2019-03-14 양소영 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use) VALUES(NEXT VALUE FOR sqalinkedfunc_id, 'COVERPRINT' , 'coverprint', '표지출력', 'Y'); 

/** 2019-04-04 매일유업 반영 */
/** 2019-04-15 고려용접봉 반영 */
/** 2019-04-17 국도화학 반영 */
/** 2019-04-18 매일유업 반영 */
/** 2019-04-26 매일유업 반영 */
/** 2019-05-08 매일유업 반영 */
/** 2019-05-10 국도화학 반영 */


/** 2019-04-30 이근환 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)
VALUES(NEXT VALUE FOR sqalinkedfunc_id, 'PAGEAUTH' , 'pageauth', '화면권한설정', 'Y' ); 


/** 2019-05-20 양소영 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES( NEXT VALUE FOR sqalinkedfunc_id, 'WORKLISTPRINT' , 'worklistprint', '작업목록출력', 'Y'); 

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

/** 297 영주 */
insert into talinkedfunc(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use) values(next value for sqalinkedfunc_id, 'BARCODEPRINT', 'printbarcode', '바코드출력', 'N'); 


/** 2019-08-23 국도화학 반영 */
/** 2019-08-26 본사 경보제약 반영 */
/** 2019-08-27 경보제약 반영 */
/** 2019-08-29 매일유업 반영 */
/** 2019-09-05 경보제약 반영 */
/** 2019-09-11 경보제약 반영 */
/** 2019-09-18 본사 국도화학 반영 */
/** 2019-10-01 매일유업 반영 */
/** 2019-10-02 경보제약 반영 */
/** 2019-10-14 매일유업 반영 */
/** 2019-10-17 매일유업 반영 */
/** 2019-10-22 고려용접봉 반영 */
 
/*532 김남현*/
insert into talinkedfunc(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use) values(next value for sqalinkedfunc_id, 'PDF', 'pdf', '출력', 'Y'); 
/** 2019-10-29 경보제약 반영 */
/** 2019-11-05 본사 국도화학 반영 */
/** 2019-11-05 고려용접봉 반영 */
/** 2019-11-06 국도화학 반영 */
/** 2019-11-14 고려용접봉 반영 */
/** 2019-11-20 매일유업 반영 */ 
/** 2019-12-02 매일유업 반영 */ 

/** 758 이근환 */
insert into talinkedfunc(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use) 
values(next value for sqalinkedfunc_id, 'RPTEQINFOPDF', 'rpteqinfopdf', '설비등록카드', 'N');

/** 2019-12-13 한국내화 반영 */
/** 2019-12-20 고려용접봉 반영 */
/** 2020-01-03 azdream - AZURE CLOUD DREAM2 반영 */
/** 2020-01-09 고려용접봉 반영*/
/** 2020-01-09 매일유업 반영 */
/** 2020-01-14 한국내화 kref 반영 */
/** 2020-01-17 매일유업 반영 */

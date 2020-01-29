

/** 2018-05-15 양소영 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use, remark) VALUES(sqaLINKEDFUNC_ID.NEXTVAL, 'ASMB', 'asmb', '부위보기', 'Y', '부위보기');

/** 2018-05-15 김영주 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES(sqalinkedfunc_id.NEXTVAL, 'EQMACH' , 'machEquipment', '설비보기' ,'Y');
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES(sqalinkedfunc_id.NEXTVAL, 'EQTOOL' , 'toolsequipment', '계측기보기' ,'Y');

/** 2018-05-15 오뚜기계열사 반영 */
/** 2018-05-15 연우 반영 */

/** 2018-05-15 김영주 */
UPDATE TALINKEDFUNC SET linkedfunc_method = LOWER(linkedfunc_method) WHERE linkedfunc_no = 'EQMACH';

/** 2018-05-15 오뚜기계열사 반영 */
/** 2018-05-16 연우 반영 */

/** 2018-05-17 김영주 */
UPDATE TAPGLINKEDFUNC SET key_no = 'equipment' WHERE key_no = 'eqMach';

/** 2018-05-18 오뚜기계열사 반영 */
/** 2018-05-25 오뚜기계열사 반영 */
/** 2018-05-30 동국제약 반영 */
/** 2018-06-01 오뚜기계열사 반영 */
/** 2018-06-01 대웅제약 반영 */
/** 2018-06-07 연우 반영 */

/** 2018-06-12 김영주 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES(sqalinkedfunc_id.NEXTVAL, 'EQBM' , 'eqbm', '고장이력' ,'Y');

/** 2018-06-14 연우 반영 */
/** 2018-06-15 연우 반영 */

/** 2018-06-18 김영주 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES(sqalinkedfunc_id.NEXTVAL, 'PTINFO' , 'ptinfo', '부품정보' ,'Y');
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES(sqalinkedfunc_id.NEXTVAL, 'PTRECHIST' , 'ptrechist', '입고이력' ,'Y');
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES(sqalinkedfunc_id.NEXTVAL, 'PTISSHIST' , 'ptisshist', '출고이력' ,'Y');
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES(sqalinkedfunc_id.NEXTVAL, 'PTUSEHIST' , 'ptusehist', '사용이력' ,'Y');
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES(sqalinkedfunc_id.NEXTVAL, 'PTCURRSTOCK' , 'ptcurrstock', '현재고' ,'Y');

/** 2018-06-18 연우 반영 */

/** 2018-06-20 양소영 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use) VALUES(sqalinkedfunc_id.NEXTVAL, 'REVISIONHISTORY', 'revisionhistory', '개정이력','Y');
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use) VALUES(sqalinkedfunc_id.NEXTVAL, 'EQPDF', 'eqpdf', '설비이력출력','Y');
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use) VALUES(sqalinkedfunc_id.NEXTVAL, 'HISTORY', 'history', '변경이력보기','Y');
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use) VALUES(sqalinkedfunc_id.NEXTVAL, 'EQINFOPDF', 'eqinfopdf', '설비등록카드','Y');
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use) VALUES(sqalinkedfunc_id.NEXTVAL, 'PMWO', 'pmwo', '예방작업이력','Y');
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use) VALUES(sqalinkedfunc_id.NEXTVAL, 'PMMSTR', 'pmmstr', '예방작업기준서','Y');
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use) VALUES(sqalinkedfunc_id.NEXTVAL, 'USEPARTS', 'useparts', '사용부품이력','Y');

/** 2018-06-20 연우 반영 */

/** 2018-06-22 11:55 오뚜기 협력사 적용 */
/** 2018-06-25 대웅제약 반영 */
/** 2018-06-28 12:55 오뚜기 협력사 적용 */

/** 2018-06-28 김영주 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES(sqalinkedfunc_id.NEXTVAL, 'EXLUPLOAD' , 'exlupload', '엑셀데이터업로드 목록보기' ,'Y');

/** 2018-06-29 연우 반영 */
/** 2018-07-02 대웅제약 반영 */
/** 2018-07-04 본사Dream 반영 */
/** 2018-07-04 연우 반영 */

/** 2018-07-05 이지수 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use) VALUES(sqalinkedfunc_id.NEXTVAL, 'WOREQ', 'woreq', '요청접수','Y');

/** 2018-07-06 본사Dream 반영 */
/** 2018-07-09 동국제약 반영 */
/** 2018-07-10 오뚜기 협력사 적용 */
/** 2018-07-11 본사Dream 반영 */
/** 2018-07-11 동국제약 반영 */
/** 2018-07-12 동국제약 반영 */
/** 2018-07-12 본사Dream 반영 */

/** 2018-07-12 이지수 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use) VALUES(sqalinkedfunc_id.NEXTVAL, 'REPAIRXPDF', 'repairxpdf', '수리불가목록출력','Y');

/** 2018-07-13 본사Dream 반영 */
/** 2018-07-17 본사Dream 반영 */
/** 2018-07-18 연우 반영 */
/** 2018-07-18 대웅제약 반영 */
/** 2018-07-20 본사Dream 반영 */


/** 2018-07-24 11:10 오뚜기협력사 반영 */

/** 2018-08-06 김영주 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES(sqalinkedfunc_id.NEXTVAL, 'AUDTRAIL' , 'audtrail', 'Audit Trail' ,'Y');

/** 2018-08-06 대웅제약 반영 */

/** 2018-08-10 10:00 로얄캐닌 적용 */
/** 2018-08-14  9:40 로얄캐닌 적용 */
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

/** 2018-08-31 김영주 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES(sqalinkedfunc_id.NEXTVAL, 'PMCPDF' , 'pmcpdf', '교정성적서 출력' ,'Y');

/** 2018-09-02 대웅제약 반영 */

/** 2018-09-03 양소영 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES(sqalinkedfunc_id.NEXTVAL, 'WOPMC' , 'wopmc', '교정이력' ,'Y');

/** 2018-09-04 10:20 오뚜기협력사 반영 */
/** 2018-09-05 평화오일씰 반영 */
/** 2018-09-10 대웅제약 반영 */
/** 2018-09-10 19:00 동국제약 반영 */

/** 2018-09-17 양소영 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES(sqalinkedfunc_id.NEXTVAL, 'VENDOR' , 'vendor', '거래처' ,'Y');

/** 2018-09-18 평화오일씰 반영 */
/** 2018-09-18 안국약품 반영 */

/* 2018-09-20 김영주 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES(sqalinkedfunc_id.NEXTVAL, 'FMEA' , 'fmea', '고장영향성평가' ,'N');

/** 2018-09-21 08:30 오뚜기협력사 반영 */
/** 2018-09-21 대웅제약 반영 */
/** 2018-09-28 평화오일씰 반영 */

/* 2018-09-28 양소영 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES(sqalinkedfunc_id.NEXTVAL, 'PMSTD' , 'pmstd', '기준서', 'Y' ); 

/** 2018-09-30 대웅제약 반영 */
/** 2018-10-02 안국약품 반영 */
/** 2018-10-02 평화오일씰 반영 */
/** 2018-10-05 평화오일씰 반영 */

/* 2018-10-05 김영주 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES(sqalinkedfunc_id.NEXTVAL, 'INSSCHED' , 'inssched', '연간 점검일정 출력' ,'N');

/** 2018-10-08 대웅제약 반영 */
/** 2018-10-10 안국약품반영 반영 */
/** 2018-10-10 평화오일씰 반영 */
/** 2018-10-10 18:00 동국제약 반영 */
/** 2018-10-11 평화오일씰 반영 */

/* 2018-10-12 김은아 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES(sqalinkedfunc_id.NEXTVAL, 'WOPLAN' , 'woplan', '작업계획보기' ,'N');

/** 2018-10-14 대웅제약 반영 */
/** 2018-10-16 09:30 동국제약 반영 */

 /** 2018-10-16 12:00 오뚜기 협력사 반영 */
/** 2018-10-16 이근환 */
INSERT INTO TALINKEDFUNC(linkedfunc_id,linkedfunc_no,linkedfunc_method,description,is_use)
VALUES(sqalinkedfunc_id.NEXTVAL,'WOHIST','wohist','작업목록보기','N');

/** 2018-10-18 평화오일씰 반영 */
/** 2018-10-19 10:00 동국제약 반영 */
/** 2018-10-21 대웅제약 반영 */
/** 2018-10-23 안국약품 반영 */
/** 2018-10-23 평화오일씰 반영 */
/** 2018-10-24 평화오일씰 반영 */
/** 2018-10-23 오뚜기 협력사 반영 */
/** 2018-10-25 11:20 동국제약 반영 */

/** 2018-10-25 김영주 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use) VALUES(sqalinkedfunc_id.NEXTVAL, 'RAPRINT', 'raprint', '위험분석출력','N');

/** 2018-10-26 평화오일씰 반영 */
/** 2018-10-26 대웅제약 반영 */
/** 2018-10-27 평화오일씰 반영 */
/** 2018-10-29 본사Dream 반영 */

/** 2018-10-29 김영주 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use) VALUES(sqalinkedfunc_id.NEXTVAL, 'REQPDF', 'reqpdf', '의뢰서출력','N');

/** 2018-10-30 평화오일씰 반영 */
/** 2018-10-30 대웅제약 반영 */
/** 2018-10-30 안국약품 반영 */
/** 2018-10-30 15:10 동국제약 반영 */

/** 2018-10-31 김영주 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES(sqalinkedfunc_id.NEXTVAL, 'WOPDF' , 'wopdf', '작업결과출력' ,'Y');

/** 2018-10-31 평화오일씰 반영 */
/** 2018-11-01 10:40 동국제약 반영 */
/** 2018-11-01 평화오일씰 반영 */
/** 2018-11-02 평화오일씰 반영 */
/** 2018-11-02 오뚜기 협력사 반영 */
/** 2018-11-06 1030 오뚜기협력사 반영 */
/** 2018-11-06 평화오일씰 반영 */
/** 2018-11-07 16:30 동국제약 반영 */
/** 2018-11-07 대웅제약 반영 */
/** 2018-11-09 평화오일씰 반영 */
/** 2018-11-13 평화오일씰 반영 */
/** 2018-11-13 안국약품 반영 */
/** 2018-11-14 1015 오뚜기협력사 반영 */
/** 2018-11-14 평화오일씰 반영 */
/** 2018-11-15 08:15 동국제약 반영 */
/** 2018-11-15 안국약품 반영 */
/** 2018-11-15 평화오일씰 반영 */
/** 2018-11-16 평화오일씰 반영 */

/** 2018-11-19 김영주 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES(sqalinkedfunc_id.NEXTVAL, 'COPY' , 'copy', '복사' ,'N');

/** 2018-11-19 안국약품 반영 */
/** 2018-11-20 평화오일씰 반영 */
/** 2018-11-21 평화오일씰 반영 */
/** 2018-11-22 대웅제약 반영 */
/** 2018-11-22 평화오일씰 반영 */
/** 2018-11-23 평화오일씰 반영 */

/** 2018-11-23 김정우 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES(sqalinkedfunc_id.NEXTVAL, 'WOCONFIRMPRINT' , 'woconfirmprint', '확인절차서출력' ,'Y');

/** 2018-11-26 평화오일씰 반영 */
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
/** 2019-01-16 동국제약 반영 */
/** 2019-01-31 평화오일씰 반영 */
/** 2019-02-11 본사Draem 반영 */
/** 2019-02-12 안국약품 반영 */

/** 2019-02-19 김은아 */
INSERT INTO TALINKEDFUNC (LINKEDFUNC_ID, LINKEDFUNC_NO, LINKEDFUNC_METHOD, DESCRIPTION, IS_USE) VALUES (SQALINKEDFUNC_ID.NEXTVAL, 'INSPDF', 'inspdf', '점검결과출력', 'Y'); 

/** 2019-03-04 안국약품 반영 */


/** 2019-02-12 양소영 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES(sqalinkedfunc_id.NEXTVAL, 'PRINT' , 'print', '출력', 'Y' ); 

/** 2019-03-07 동국제약 반영 */

/** 2019-03-11 이근환 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use, REMARK)
VALUES(sqalinkedfunc_id.NEXTVAL, UPPER('WO') , 'wo', '작업서' ,'Y','선택한 작업의 상세화면을 조회');


/** 2019-03-12 김영주 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use, REMARK)
VALUES(sqalinkedfunc_id.NEXTVAL, UPPER('WOREQDETAIL') , 'woreqdetail', '요청서' ,'Y','선택한 요청접수의 상세화면을 조회');

/** 2019-03-13 현대일렉트릭 반영 */


/** 2019-03-13 양소영 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use, REMARK) VALUES(sqalinkedfunc_id.NEXTVAL, 'WOPMI' , 'wopmi', '예방점검 작업서' ,'Y','선택한 점검 작업의 상세화면을 조회');

/** 2019-03-26 오뚜기 반영 */

/** 2019-03-26 김정우 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use, REMARK) VALUES(sqalinkedfunc_id.NEXTVAL, 'VIEWDETAIL' , 'viewDetail', '상세보기' ,'Y','상세보기');

/** 2019-03-26 14:30 오뚜기 반영 */

/** 2019-03-14 양소영 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)VALUES(sqalinkedfunc_id.NEXTVAL, 'COVERPRINT' , 'coverprint', '표지출력', 'Y' ); 

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

/** 2019-04-30 이근환 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use)
VALUES(sqalinkedfunc_id.NEXTVAL, 'PAGEAUTH' , 'pageauth', '화면권한설정', 'Y' ); 

/** 2019-05-16 현대일렉트릭 반영 */
/** 2019-05-17 현대일렉트릭 반영 */
/** 2019-05-21 안국약품 반영 */

/** 2019-05-22 오뚜기 본사 반영 */

/** 2019-05-22 현대일렉트릭 반영 */


/** 2019-05-20 양소영 */
INSERT INTO TALINKEDFUNC(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use) VALUES(sqalinkedfunc_id.NEXTVAL, 'WORKLISTPRINT' , 'worklistprint', '작업목록출력', 'Y'); 

/** 2019-05-23 평화오일씰 반영 */
/** 2019-05-23 현대일렉트릭 반영 */
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
/** 2019-06-19 오뚜기 본사 반영 */
/** 2019-06-25 현대일렉트릭 반영 */
/** 2019-07-02 오뚜기 본사 반영 */
/** 2019-07-03 현대일렉트릭 반영 */
/** 2019-07-11 평화오일씰 반영 */
/** 2019-07-12 백광 반영 */
/** 2019-07-24 로얄캐닌 반영 */
/** 2019-08-12 오뚜기(OEMS) 반영 */
/** 2019-08-21 연우 반영 */
/** 297 영주 */
insert into talinkedfunc(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use) values(sqalinkedfunc_id.nextval, 'BARCODEPRINT', 'printbarcode', '바코드출력', 'N'); 

 
/** 2019-08-26 동국제약 반영 */
/** 2019-08-26 연우 반영 */
/** 2019-09-09 평화오일씰 반영 */
/** 2019-10-01 안국약품 반영 */
/** 2019-10-16 현대일렉트릭 반영 */
/*532 김남현*/
insert into talinkedfunc(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use) values(sqalinkedfunc_id.nextval, 'PDF', 'pdf', '출력', 'N'); 

/** 2019-10-30 평화오일씰 반영 */
/** 2019-11-05 안국약품 반영 */
/** 2019-11-22 동국제약 반영 */
/** 2019-11-25 연우 반영 */
/** 2019-11-26 평화오일씰 반영 */
/** 2019-11-27 연우 반영 */
/** 2019-12-04 안국약품 반영 */

/** 758 이근환 */
insert into talinkedfunc(linkedfunc_id, linkedfunc_no, linkedfunc_method, description, is_use) 
values(sqalinkedfunc_id.nextval, 'RPTEQINFOPDF', 'rpteqinfopdf', '설비등록카드', 'N');

/** 2020-01-07 연우 반영 */
/** 2020-01-09 평화오일씰 phos 반영 */
/** 2020-01-13 현대일렉트릭 hdele 반영 */
/** 2020-01-13 평화오일씰 phos 반영 */
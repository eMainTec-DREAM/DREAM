---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-11   박철완 최초 생성*/ 
---------------------------------------------------------------------------------------------------------------------------------------------------
 
 INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) 
 VALUES('100',SQABATPGM_ID.NEXTVAL,'TAASSET','ERP에서 자산번호,자산금액을 가져오는 프로그램','SP_IF_UPD_ASSET','Y','19900101','매일 새벽 2시 50분에 1회 실행'); 
 
 INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) 
 VALUES('100',SQABATPGM_ID.NEXTVAL,'TXBGTAMT','ERP에서 수선비 실적(GL금액)을 가져오는 프로그램','SP_IF_UPD_BGTACT','Y','19900101','매일 새벽 3시에 1회 실행'); 
 
 INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) 
 VALUES('100',SQABATPGM_ID.NEXTVAL,'BGTDEPTACT','노츠에서 수선비 품의서 결재완료된 금액을 가져오는 프로그램.','SP_IF_UPD_BGTDEPTACT','Y','19900101','매시 45분에 1회 실행'); 
 
 INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) 
 VALUES('100',SQABATPGM_ID.NEXTVAL,'TXBGTPLAN','수선비 예산계획을 노츠로 주는 프로그램','SP_IF_UPD_BGTPLAN','Y','19900101','매일 12:00, 24:00에 실행'); 
 
 INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) 
 VALUES('100',SQABATPGM_ID.NEXTVAL,'TACTCTR','ERP에서 CP코드를 가져오는 프로그램','SP_IF_UPD_CTCTR','Y','19900101','매일 새벽 2시 40분에 1회 실행'); 
 
 INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) 
 VALUES('100',SQABATPGM_ID.NEXTVAL,'TADEPT','ERP에서 부서정보를 가져오는 프로그램','SP_IF_UPD_DEPT','Y','19900101','매일 새벽 2시 20분에 1회 실행');
 
 INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) 
 VALUES('100',SQABATPGM_ID.NEXTVAL,'TAEMP','ERP에서 사원정보를 가져오는 프로그램','SP_IF_UPD_EMP','Y','19900101','매일 새벽 2시 30분에 1회 실행');
 
 INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) 
 VALUES('100',SQABATPGM_ID.NEXTVAL,'EQAPPLIST','노츠에서 설비기안내용을 가져오는 프로그램','SP_IF_UPD_EQAPPLIST','Y','19900101','매시 05분에 1회 실행');
 
 INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) 
 VALUES('100',SQABATPGM_ID.NEXTVAL,'TAEQRUNLST','ERP에서 라인가동시간을 가져오는 프로그램','SP_IF_UPD_EQRUNLST','Y','19900101','매일 새벽 2시 10분에 1회 실행');
 
 INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) 
 VALUES('100',SQABATPGM_ID.NEXTVAL,'POPLNRUNLIST','가동모니터링(POP)에서 일별 가동실적을 가져오는 프로그램','SP_IF_UPD_POPLNRUNLIST','Y','19900101','30분 단위로 실행');
 
 INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) 
 VALUES('100',SQABATPGM_ID.NEXTVAL,'PTAPPLIST','노츠에서 부품수리 품의내용을 가져오는 프로그램','SP_IF_UPD_PTAPPLIST','Y','19900101','매시 15분에 1회 실행');
 
 INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) 
 VALUES('100',SQABATPGM_ID.NEXTVAL,'TXEQUIPMENT','노츠에 설비마스터 정보를 보내주는 프로그램','SP_IF_UPD_TXEQUIPMENT','Y','19900101','매시 25분에 1회 실행');
 
 INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) 
 VALUES('100',SQABATPGM_ID.NEXTVAL,'TXPARTS','노츠에 자재마스터 정보를 보내주는 프로그램','SP_IF_UPD_TXPARTS','Y','19900101','매시 35분에 1회 실행');
 
 INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) 
 VALUES('100',SQABATPGM_ID.NEXTVAL,'TAVENDOR','ERP에서 거래처 정보를 가져오는 프로그램','SP_IF_UPD_VENDOR','Y','19900101','매일 새벽 2시에 1회 실행');
 
 INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) 
 VALUES('100',SQABATPGM_ID.NEXTVAL,'TAPMSCHED_ALL','예방작업 전체일정을 생성하는 프로그램','SP_PM_MAKE_SCHEDULE_BYALL','Y','19900101','매일 새벽 1시에 1회 실행');
 
 INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) 
 VALUES('100',SQABATPGM_ID.NEXTVAL,'TAPMSCHED_ONE','예방작업 건별일정을 생성하는 프로그램','SP_PM_MAKE_SCHEDULE_BYONE','N','19900101','화면에서 작업시 호출');
 
 INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) 
 VALUES('100',SQABATPGM_ID.NEXTVAL,'TAPMWORK','예방작업 일정에서 작업서(WO)를 자동으로 발행해 주는 프로그램','SP_PM_MAKE_WORKORDER','Y','19900101','매일 새벽 1시 30분에 1회 실행');
 
 INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) 
 VALUES('100',SQABATPGM_ID.NEXTVAL,'TAPTINSTOCK','현재 장부재고에서 재고를 더해주고 입고이력을 기록해 주는 프로그램','SP_PT_INSTOCK','N','19900101','화면에서 작업시 호출');
 
 INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) 
 VALUES('100',SQABATPGM_ID.NEXTVAL,'TAPTOUSTOCK','현재 장부재고에서 재고를 빼주고 출고이력을 기록해 주는 프로그램','SP_PT_OUTSTOCK','N','19900101','화면에서 작업시 호출');
 
 INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) 
 VALUES('100',SQABATPGM_ID.NEXTVAL,'PTREPAIRLIST','노츠에서 부품/수리 품의서를 가져와서 입고를 기록해 주는 프로그램 ','SP_IF_UPD_PTREPAIRLIST','Y','19900101','매시 30분에 1회 실행'); 
 
 INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) 
 VALUES('100',SQABATPGM_ID.NEXTVAL,'TAUSRPTDAY','일별로 사용현황모니터링 데이터를 집계해 주는 프로그램','SP_SYS_UPD_USRPTDAY','Y','19900101','매일새벽 4시에 전일거 실행'); 
 
 
 
 /*2016.07.07일 추가..*/
 
 
  INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) VALUES('100',SQABATPGM_ID.NEXTVAL,'TALNWRKTIME','한달치에 가동시간을 default로 생성해 주는 프로그램','SP_PM_MAKE_TALNWRKTIME','Y','19900101','매일새벽 3시 10분에 실행'); 
 INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) VALUES('100',SQABATPGM_ID.NEXTVAL,'TAINVESTAMT','라인별 투자목표금액을 default로 생성해 주는 프로그램','SP_PM_MAKE_TAINVESTAMT','Y','19900101','매일새벽 3시 20분에 실행'); 
 INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) VALUES('100',SQABATPGM_ID.NEXTVAL,'TAMTPOINT','월별보전목표 default값을 생성해 주는 프로그램','SP_PM_MAKE_TAMTPOINT','Y','19900101','매일새벽 3시 30분에 실행'); 
/** 2016-07-26 김정우 배치프로그램 프로시저 추가 */
 
INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) 
VALUES('100',SQABATPGM_ID.NEXTVAL,'TAWRKCALENDAR','공장 가동카렌다를 default로 만들어주는 프로그램','SP_SET_WORKCALENDAR','Y','19900101','매일새벽 3시 40분에 실행'); 
INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) 
VALUES('100',SQABATPGM_ID.NEXTVAL,'TAKPIDLOCDN','일별고장율 데이터를 집계하는 프로그램[전월 1일부터 오늘까지 재생성]','SP_KPI_MAKE_TAKPIDLOCDN','Y','19900101','매일새벽 3시 50분에 실행'); 
INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) 
VALUES('100',SQABATPGM_ID.NEXTVAL,'TAKPIMLOCDN','월별고장율 데이터를 집계하는 프로그램[전월,당월 재생성]','SP_KPI_MAKE_TAKPIMLOCDN','Y','19900101','매일새벽 4시에 실행'); 
INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) 
VALUES('100',SQABATPGM_ID.NEXTVAL,'TAKPIDLOCCTGDN','설비종류별고장율 데이터를 집계하는 프로그램[전월 1일부터 오늘까지 재생성]','SP_KPI_MAKE_TAKPIDLOCCTGDN','Y','19900101','매일새벽 4시 10분에 실행'); 
INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) 
VALUES('100',SQABATPGM_ID.NEXTVAL,'TAKPIMLOCCTGDN','설비종류별 고장율 데이터를 월별로 집계','SP_KPI_MAKE_TAKPIMLOCCTGDN','Y','19900101','매일새벽 4시 20분에 실행'); 
INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) 
VALUES('100',SQABATPGM_ID.NEXTVAL,'TAKPIMMPOINT','월간보전항목 지표 데이터를 생성하는 프로그램','SP_KPI_MAKE_TAKPIMMPOINT','Y','19900101','매일새벽 4시 30분에 실행'); 
INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) 
VALUES('100',SQABATPGM_ID.NEXTVAL,'TAKPIWMPOINT','주간단위 고장지표 데이터를 생성하는 프로그램','SP_KPI_MAKE_TAKPIWMPOINT','Y','19900101','매일새벽 4시 40분에 실행'); 
INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) 
VALUES('100',SQABATPGM_ID.NEXTVAL,'TAKPIMEDU','월간교육계획 지표 데이터를 생성하는 프로그램','SP_KPI_MAKE_TAKPIMEDU','Y','19900101','매일새벽 4시 50분에 실행'); 
INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) 
VALUES('100',SQABATPGM_ID.NEXTVAL,'TAPTMONTHLYSTOCK','월간 수불부를 생성하는 프로그램','SP_KPI_MAKE_TAPTMONTHLYSTOCK','Y','19900101','매일새벽 5시 00분에 실행'); 



/*2016.08.11 자재마스터 베치 프그램..*/

INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) VALUES('100',SQABATPGM_ID.NEXTVAL,'TAPARTS','자재품번을 생성하는 프로그랩','SP_IF_UPD_TAPARTS','Y','19900101','매일1,5,9,13,17,21 이후 10분후실행'); 

INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) VALUES('100',SQABATPGM_ID.NEXTVAL,'TAPTSTOCK','ERP재고를 가져오는 프로그램','SP_IF_UPD_TAPTSTOCK','Y','19900101','새벽 5시에 10분실행'); 
  
INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) VALUES('100',SQABATPGM_ID.NEXTVAL,'TAERPISSHIST','ERP출고 이력 가져오는 프로그램','SP_IF_UPD_TAERPISSHIST','Y','19900101','새벽 5시30분 10분후에 실행'); 

INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) VALUES('100',SQABATPGM_ID.NEXTVAL,'TXERPPRPOLIST','ERP 구매청구 진행현황을 수신 프로그램','SP_IF_UPD_TXERPPRPOLIST','Y','19900101','매일 새벽 4시 40분에 실행'); 
 
/** 2016-09-29 MES */
INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) 
VALUES('100',SQABATPGM_ID.NEXTVAL,'TXLNWRKTIME','라인가동시간 수신프로그램','SP_IF_UPD_TXLNWRKTIME','Y','19900101','매일 새벽 6시 00분에 실행'); 
 
INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) 
VALUES('100',SQABATPGM_ID.NEXTVAL,'TXLNNTWRKTIME','라인비가동시간 수신프로그램','SP_IF_UPD_TXLNNTWRKTIME','Y','19900101','매일 새벽 6시 10분에 실행'); 
 

/** 2016-11-09 부품입고이력 NOT MSEAT*/
INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) 
VALUES('110',SQABATPGM_ID.NEXTVAL,'TXPTERPRECLIST','부품입고이력 수신프로그램','SP_IF_UPD_TXPTERPRECLIST','Y','19900101','매일 새벽 6시 20분에 실행'); 
 

INSERT INTO TABATPGM(COMP_NO,BATPGM_ID,BATPGM_NO,DESCRIPTION,BATCH_PGM,IS_EXEC,LAST_EXE_DATE,REMARK) 
VALUES('110',SQABATPGM_ID.NEXTVAL,'GLOBALKPI','Global KPI','GLOBAL_KPI','Y','19900101','매일 새벽 1시 20분에 실행'); 
 
 /**  2018-03-15 대웅제약 반영 */ 
/** 2018-04-09 오뚜기라면 반영 */
/** 2018-04-10 동국제약 반영 */
/** 2018-04-12 오뚜기라면 반영 */
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
/** 2018-07-17 본사Dream 반영 */
/** 2018-07-18 연우 반영 */
/** 2018-07-20 본사Dream 반영 */


/** 2018-07-24 11:10 오뚜기협력사 반영 */

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
/** 2018-09-04 10:20 오뚜기협력사 반영 */
/** 2018-09-05 평화오일씰 반영 */
/** 2018-09-10 19:00 동국제약 반영 */
/** 2018-09-18 평화오일씰 반영 */
/** 2018-09-18 안국약품 반영 */
/** 2018-09-21 08:30 오뚜기협력사 반영 */
/** 2018-09-28 평화오일씰 반영 */
/** 2018-10-02 안국약품 반영 */
/** 2018-10-02 평화오일씰 반영 */
/** 2018-10-05 평화오일씰 반영 */
/** 2018-10-10 안국약품반영 반영 */
/** 2018-10-10 평화오일씰 반영 */
/** 2018-10-10 18:00 동국제약 반영 */
/** 2018-10-11 평화오일씰 반영 */
/** 2018-10-16 09:30 동국제약 반영 */
/** 2018-10-18 평화오일씰 반영 */

 /** 2018-10-16 12:00 오뚜기 협력사 반영 */
/** 2018-10-19 10:00 동국제약 반영 */
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
/** 2018-10-31 평화오일씰 반영 */
/** 2018-11-01 10:40 동국제약 반영 */
/** 2018-11-01 평화오일씰 반영 */
/** 2018-11-02 평화오일씰 반영 */
/** 2018-11-02 오뚜기 협력사 반영 */
/** 2018-11-06 1030 오뚜기협력사 반영 */
/** 2018-11-06 평화오일씰 반영 */
/** 2018-11-07 16:30 동국제약 반영 */
/** 2018-11-07 평화오일씰 반영 */
/** 2018-11-09 평화오일씰 반영 */
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
/** 2018-11-27 안국약품 반영 */
/** 2018-11-27 평화오일씰 반영 */
/** 2018-11-27 오뚜기협력사 반영 */
/** 2018-11-27 05:30 동국제약 반영 */
/** 2018-11-28 평화오일씰 반영 */
/** 2018-11-28 안국약품 반영 */
/** 2018-11-28 본사Dream 반영 */
/** 2018-11-28 15:45 동국제약 반영 */
/** 2018-11-29 평화오일씰 반영 */
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
/** 2019-05-21 안국약품 반영 */
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
/** 2019-09-09 평화오일씰 반영 */
/** 2019-10-01 안국약품 반영 */
/** 2019-10-16 현대일렉트릭 반영 */
/** 2019-10-30 평화오일씰 반영 */
/** 2019-11-05 안국약품 반영 */
/** 2019-11-22 동국제약 반영 */
/** 2019-11-25 연우 반영 * /
/** 2019-11-26 평화오일씰 반영 */
/** 2019-11-27 연우 반영 * /
/** 2019-12-04 안국약품 반영 */
/** 2020-01-07 연우 반영 */
/** 2020-01-09 평화오일씰 phos 반영 */
/** 2020-01-13 현대일렉트릭 hdele 반영 */
/** 2020-01-13 평화오일씰 phos 반영 */
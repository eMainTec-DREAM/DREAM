select 
' INSERT INTO TAMENU(menu_id,description,page_id,ord_no,param,is_system,key_no,key_type,is_use,service_type,menu_no, p_menu_no, file_name, remark)
VALUES(NEXT VALUE FOR sqamenu_id , '''||description||''', (SELECT page_id FROM TAPAGE WHERE file_name='''||page_id||''') , '''||ord_no||''', '''||param||''', '''||is_system||''' , '''||key_no||''', '''||key_type||''', '''||is_use||''', '''||service_type||''', '''||menu_no||''', '''||p_menu_no||''', '''||file_name||''', '''||remark||'''); '
 from TAMENU;
 
update TAMENU set p_menu_id = aa.menu_id
from TAMENU a inner join TAMENU aa on aa.menu_id = a.p_menu_id
where 1=1
;
 

---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-11   박철완 최초 생성*/ 
---------------------------------------------------------------------------------------------------------------------------------------------------

 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM) 
 VALUES(NEXT VALUE FOR SQAMENU_ID,'설비정보',0,NULL,'010','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'위치분류',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='설비정보'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maEqLocList'),'10','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'설비마스터',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='설비정보'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maEqMstrList'),'30','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'설비종류',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='설비정보'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maEqCatalogList'),'20','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM) 
 VALUES(NEXT VALUE FOR SQAMENU_ID,'고장분류',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='설비정보'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maFailureList'),'60','')
go 
 
 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'예방작업',0,NULL,'020','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'예방작업일정(연간)',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='예방작업'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maWoYearSchedList'),'30','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'연간부품사용일정',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='예방작업'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maPmYearPtSchedList'),'60','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'예방작업기준',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='예방작업'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maPmMstrList'),'10','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM) 
 VALUES(NEXT VALUE FOR SQAMENU_ID,'예방작업일정(월간)',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='예방작업'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maWoMonthSchedList'),'40','')
go 
 
 
 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'작업관리',0,NULL,'030','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'이상점검조치',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='작업관리'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maBdPointList'),'60','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'월간작업일정',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='작업관리'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maWoMonthWoList'),'50','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'작업결과',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='작업관리'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maWoResultMstrList'),'20','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'라인가동내역',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='작업관리'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maWoLinepfList'),'70','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'부품사용이력',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='작업관리'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maWoPtHistList'),'40','')
go 
 
 
 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'부품관리',0,NULL,'040','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM) 
 VALUES(NEXT VALUE FOR SQAMENU_ID,'예산계획',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='부품관리'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maPtBudgetList'),'80','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM) 
 VALUES(NEXT VALUE FOR SQAMENU_ID,'부품수리',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='부품관리'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maPtRepList'),'20','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'구매입고',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='부품관리'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maPtRecList'),'10','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM) 
 VALUES(NEXT VALUE FOR SQAMENU_ID,'부품마스터',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='부품관리'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maPtMstrList'),'70','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'부품창고',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='부품관리'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maPtWhList'),'900','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM) 
 VALUES(NEXT VALUE FOR SQAMENU_ID,'부품재고',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='부품관리'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maPtStckList'),'30','')
go 
 
 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'분석지표',0,NULL,'050','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'설비작업현황',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='분석지표'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maEqWoChart'),'10','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM) 
 VALUES(NEXT VALUE FOR SQAMENU_ID,'설비종류별고장분석',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='분석지표'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maBmCtgChart'),'70','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM) 
 VALUES(NEXT VALUE FOR SQAMENU_ID,'부품비용분석',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='분석지표'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maPtCostChart'),'20','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM) 
 VALUES(NEXT VALUE FOR SQAMENU_ID,'설비별고장분석',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='분석지표'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maBmEqList'),'80','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'부품수리내역',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='분석지표'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maPtRepStatList'),'40','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM) 
 VALUES(NEXT VALUE FOR SQAMENU_ID,'일별작업실행율',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='분석지표'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maWoDailyChart'),'100','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'라인고장분석',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='분석지표'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maBmLnChart'),'60','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'과별고장분석',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='분석지표'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maBmGwChart'),'50','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM) 
 VALUES(NEXT VALUE FOR SQAMENU_ID,'예산사용현황',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='분석지표'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maPtBgList'),'110','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM) 
 VALUES(NEXT VALUE FOR SQAMENU_ID,'고장조치작업',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='분석지표'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maBmActList'),'90','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'부품입고내역',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='분석지표'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maPtRecStatList'),'30','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM) 
 VALUES(NEXT VALUE FOR SQAMENU_ID,'예방점검경향분석',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='분석지표'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maPmTrendChart'),'120','')
go 
 
 
 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM) 
 VALUES(NEXT VALUE FOR SQAMENU_ID,'일반관리',0,NULL,'060','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM) 
 VALUES(NEXT VALUE FOR SQAMENU_ID,'보전사원',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='일반관리'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maEmpList'),'210','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM) 
 VALUES(NEXT VALUE FOR SQAMENU_ID,'작업부서',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='일반관리'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maDeptList'),'200','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'사진첨부',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='일반관리'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maDocImgList'),'60','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'일반자료실',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='일반관리'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maDocCntrCdList'),'70','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'첨부문서',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='일반관리'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maDocLibList'),'50','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'업체정보',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='일반관리'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maVendorList'),'30','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'동종기계정보',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='일반관리'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maDocCntrEcList'),'80','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'개인설정',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='일반관리'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maMyInfo'),'250','')
go 
 
 
 
 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'시스템관리',0,NULL,'070','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM) 
 VALUES(NEXT VALUE FOR SQAMENU_ID,'설비담당자변경',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='시스템관리'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maEqMngList'),'50','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'배치작업내역',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='시스템관리'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maBatchMngList'),'260','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'실시간 접속자',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='시스템관리'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maSesMngList'),'240','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'사용자',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='시스템관리'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maUserList'),'230','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'권한명',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='시스템관리'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maUsrGrpList'),'250','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM) 
 VALUES(NEXT VALUE FOR SQAMENU_ID,'사용자코드',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='시스템관리'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maCdUsrList'),'160','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM) 
 VALUES(NEXT VALUE FOR SQAMENU_ID,'메일수신자설정',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='시스템관리'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maMailList'),'270','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM) 
 VALUES(NEXT VALUE FOR SQAMENU_ID,'시스템 접속현황',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='시스템관리'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maConnChart'),'270','')
go 
  INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM) 
 VALUES(NEXT VALUE FOR SQAMENU_ID,'시스템 사용현황',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='시스템관리'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maUseChart'),'280','')
go 
 
 
 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'MWARE설정',0,NULL,'090','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM) 
 VALUES(NEXT VALUE FOR SQAMENU_ID,'다국어',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='MWARE설정'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maLangMngList'),'180','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM) 
 VALUES(NEXT VALUE FOR SQAMENU_ID,'레포트',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='MWARE설정'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maReport'),'210','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM) 
 VALUES(NEXT VALUE FOR SQAMENU_ID,'시스템코드',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='MWARE설정'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maCdSysList'),'50','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM) 
 VALUES(NEXT VALUE FOR SQAMENU_ID,'버튼',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='MWARE설정'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maBtnMngList'),'130','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM) 
 VALUES(NEXT VALUE FOR SQAMENU_ID,'메뉴',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='MWARE설정'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maMenuMngList'),'150','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'화면',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='MWARE설정'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maPgMngList'),'140','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'시스템 환경변수',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='MWARE설정'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maConfigList'),'190','')
go 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'화면컬럼정의',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='MWARE설정'),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maGrdMngList'),'200','')
go 
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-12   김정우 추가작업 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system)
values (NEXT VALUE FOR SQAMENU_ID,'설비고장내역',(select menu_id FROM TAMENU where description = '분석지표'),(select page_id FROM TAPAGE WHERE file_name='maEqBmList'),'130','N')
go
INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system)
values (NEXT VALUE FOR SQAMENU_ID,'예방수리내역',(select menu_id FROM TAMENU where description = '분석지표'),(select page_id FROM TAPAGE WHERE file_name='maPmRepList'),'140','N')
go
INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system)
values (NEXT VALUE FOR SQAMENU_ID,'예방점검내역',(select menu_id FROM TAMENU where description = '분석지표'),(select page_id FROM TAPAGE WHERE file_name='maPmPointList'),'150','N')
go
INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system)
values (NEXT VALUE FOR SQAMENU_ID,'부서별작업분석',(select menu_id FROM TAMENU where description = '분석지표'),(select page_id FROM TAPAGE WHERE file_name='maDeptWoList'),'160','N')
go
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-12   김정우 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-12   노정현 추가작업 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO TAMENU (menu_id,description,p_menu_id,page_id,ord_no,is_system)
VALUES (NEXT VALUE FOR SQAMENU_ID, '무정지대표라인', (SELECT menu_id FROM TAMENU WHERE description ='일반관리') , (SELECT page_id FROM TAPAGE WHERE file_name='maNstGrpList'), '260', 'N')
go

--일반자료실 메뉴 파라미터 세팅
UPDATE TAMENU SET        
    param        = 'maDocCntrCdCommonDTO.docCntrType=CD'       
WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE x.description = '일반자료실')  
go

--일반자료실 자료 (자료타입 세팅)
UPDATE TADOCCNTR SET doccntr_type = 'CD' WHERE description LIKE '%교육자료%'
go
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-12   노정현 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-16   김정우 추가작업 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------

delete FROM TAMENU where page_id = (select page_id from tapage where file_name='maStdPointList')
go
INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system)
values (NEXT VALUE FOR SQAMENU_ID,'예방작업 표준항목',(select menu_id FROM TAMENU where description = '예방작업'),(select page_id FROM TAPAGE WHERE file_name='maStdPointHdrList'),'70','N')
go
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-16   김정우 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-26   김정우 추가작업 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system)
values (NEXT VALUE FOR SQAMENU_ID,'질의응답',(select menu_id FROM TAMENU where description = '일반관리'),(select page_id FROM TAPAGE WHERE file_name='maQnaList'),'260','N')
go
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-26   김정우 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------

 ---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-06-01   김정우 구매신청 메뉴 추가작업 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system)
values (NEXT VALUE FOR SQAMENU_ID,'구매신청',(select menu_id FROM TAMENU where description = '부품관리'),(select page_id FROM TAPAGE WHERE file_name='maPtBuyReqHdrList'),'80','N')
go
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-06-01  김정우 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------

 
 /*
  * 
  * 
  * 설비분류	위치분류	설비가 어디에 위치하는지에 대한 위치분류 작성하는 기능
	종류분류	설비가 어떤종류를 가지고 있는지에 대한 종류분류를 작성하는 기능
	설비분류	보유설비에 대한 목록를 확인하고 작성하는 기능
	고장분류	고장현상,원인,조치에 대한 분류코들 확인하고 작성하는 기능
		
예방작업	작업기준	일상점검,정기점검,계획보전 작업 기준을 확인하고 작성하는 기능
	작업계획	일상점검,정기점검,계획보전의 년간,월간작업계획을 확인하는 기능
	부품계획	계획보전의 투입되는 부품의 년간,월간사용계획을 확인하는 기능
		
보전작업	작업일정	보전작업자의 작업일정을 확인하고 작업내용을 등록하는 기능
	이상점검	일상점검,정기점검중에서 이상시 즉시조치를 할 수 없는 작업을 확인하는 기능
		
부품관리	부품분류	부품에 대한 마스터 정보를 관리하는 기능, ERP부품을 수신하고 자재품번을 채번하는 기능
	부품재고	창고별로 부품에 대한 장부재고를 확인하는 기능
	부품신청(현장)	현장에서 부품의 구매가 필요한 경우 작성하는 기능
	부품입고(무상)	현장에서 무상입고품에 대한 내용을 등록하는 기능
	부품수리	부품에 대한 수리내역을 등록하고 수리입고를 처리하는 기능
	부품출고(확정)	사용한 부품에 대한 내용을 확인하고 저장품,비저장품의 출고확정하는 기능
	부품창고	부품창고를 확인하고 등록하는 기능
		
사원관리	조직	보전조직을 확인하고 등록하는 기능
	사원	보전사원을 확인하고 등록하는 기능
	개인설정	접속자의 개인설정 정보를 확인하고 조정하는 기능
		
MES연계	라인정지이력	MES에 라인정지 내역을 조회하는 기능
	라인가동시간	MES에 라이별 가동시간을 조회하는 기능
		
자료실	일반자료실	일반자료를 조회하고 등록하는 기능
	매뉴얼자료실	시스템사용자의 매뉴얼 조회하는 기능
	첨부자료실	시스템에 첨부된 파일을 검색하고 조회하는 기능
		
시스템관리	사용자	시스템 접근 사용자를 확인하고 등록하는 기능
	접근권한	시스템 접근권한을 설정하는 기능
	시스템코드	시스템 사용분류코드를 확인하고 등록하는 기능
	현재접속자	현재 시스템에 접속하고 있는 실시간 접속자를 확인하는 기능
	접속이력	시스템 접속이력을 조회하는 기능
		
보전작업분석	설비작업이력	설비별 작업이력을 조회하는 기능
	작업진행현황(부서별)	부서별 작업진행현황을 조회하는 기능
	작업진행현황(전체)	전체부서의 일별로 작업 진행현황을 조회하는 기능
	작업종류분석	부서별 작업종류별 작업건수 및 시간을 조회하는 기능
		
예방작업분석	예방작업이행율(점검)	예방작업중에서 예방점검에 대한 이행율을 조회하는 기능
	예방작업이력(점검)	예방작업중에서 예방점검이력을 조회하는 기능
	예방작업이행율(계획보전)	예방작업중에서 계획보전에 대한 이행율을 조회하는 기능
	예방작업이력(계획보전)	예방작업중에서 계획보전이력을 조회하는 기능
		
설비고장분석	설비고장이력	설비고장이력을 조회하는 기능
	고장분석(부서별)	부서별 고장분석을 조회하는 기능
	고장분석(위치별)	위치별 고장분석을 조회하는 기능
	고장분석(설비종류별)	설비종류별 고장분석을 조회하는 기능
		
부품사용분석	부품사용이력	부품의 작업에 대한 사용이력을 조회하는 기능
	부품사용분석(월별)	월별로 부품의 사용수량, 사용금액을 조회하는 기능
	ERP구매청구현황	ERP에 구매청구 현황을 조회하는 기능
		
예산관리	예산계획	월별 부서별 예산계획을 조회하고 등록하는 기능
	예산사용현황	월별 부서별 예산의 사용현황을 조회하는 기능
  */
 
 
 ---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-31   이규선 추가작업 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------

 INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system)
values (NEXT VALUE FOR SQAMENU_ID,'현장구매신청',(select menu_id FROM TAMENU where description = '부품관리'),(select page_id FROM TAPAGE WHERE file_name='maPtPurReqList'),'80','N')
go
 
  ---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-31   이규선 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-06-08   김정우 메뉴 다국어 라벨  추가작업 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
update TAMENU set key_no='CDSYS' WHERE description='시스템코드'
go
update TAMENU set key_no='EQCTG' WHERE description='설비종류'
go
update TAMENU set key_no='PTSTCK' WHERE description='부품재고'
go
update TAMENU set key_no='WORESULT' WHERE description='작업결과'
go
update TAMENU set key_no='PTREC' WHERE description='구매입고'
go
update TAMENU set key_no='PTREP' WHERE description='부품수리'
go
update TAMENU set key_no='YEARPMSCHED' WHERE description='예방작업일정(연간)'
go
update TAMENU set key_no='PAGECOL' WHERE description='화면컬럼정의'
go
update TAMENU set key_no='DOCCNTR_EC' WHERE description='동종기계정보'
go
update TAMENU set key_no='BMEQLIST' WHERE description='설비별고장분석'
go
update TAMENU set key_no='MAILSET' WHERE description='메일수신자설정'
go
update TAMENU set key_no='EQBMLIST' WHERE description='설비고장내역'
go
update TAMENU set key_no='BUDGETMNG' WHERE description='예산관리'
go
update TAMENU set key_no='PMSTDPOINT' WHERE description='예방작업 표준항목'
go
update TAMENU set key_no='PTRECSTAT' WHERE description='부품입고내역'
go
update TAMENU set key_no='BMLNCHART' WHERE description='라인고장분석'
go
update TAMENU set key_no='PTREPSTAT' WHERE description='부품수리내역'
go
update TAMENU set key_no='BMCTGCHART' WHERE description='설비종류별고장분석'
go
update TAMENU set key_no='EQACTION' WHERE description='설비조치작업'
go
update TAMENU set key_no='PMYEARPTSCHED' WHERE description='연간부품사용일정'
go
update TAMENU set key_no='MONTHWOSCHED' WHERE description='월간작업일정'
go
update TAMENU set key_no='BDPOINT' WHERE description='이상점검조치'
go
update TAMENU set key_no='PTSTOCK' WHERE description='부품창고'
go
update TAMENU set key_no='SYSUSELIST' WHERE description='시스템 사용현황'
go
update TAMENU set key_no='EQMSTR' WHERE description='설비마스터'
go
update TAMENU set key_no='VENDOR' WHERE description='업체정보'
go
update TAMENU set key_no='NORMALMNG' WHERE description='일반관리'
go
update TAMENU set key_no='DOCLIB' WHERE description='첨부문서'
go
update TAMENU set key_no='PTMSTR' WHERE description='부품마스터'
go
update TAMENU set key_no='PMMSTR' WHERE description='예방작업기준'
go
update TAMENU set key_no='DOCCNTR_CD' WHERE description='일반자료실'
go
update TAMENU set key_no='PTCOSTCHART' WHERE description='부품비용분석'
go
update TAMENU set key_no='BMGWCHART' WHERE description='과별고장분석'
go
update TAMENU set key_no='PMTREND' WHERE description='예방점검경향분석'
go
update TAMENU set key_no='COMPSET' WHERE description='회사설정'
go
update TAMENU set key_no='EQLOC' WHERE description='위치분류'
go
update TAMENU set key_no='EQINFO' WHERE description='설비정보'
go
update TAMENU set key_no='PTMNG' WHERE description='부품관리'
go
update TAMENU set key_no='BMCATEG' WHERE description='고장분류'
go
update TAMENU set key_no='DOCIMG' WHERE description='사진첨부'
go
update TAMENU set key_no='MONTHPMSCHED' WHERE description='예방작업일정(월간)'
go
update TAMENU set key_no='EQWOCHART' WHERE description='설비작업현황'
go
update TAMENU set key_no='REPORTMNG' WHERE description='레포트'
go
update TAMENU set key_no='PM' WHERE description='예방작업'
go
update TAMENU set key_no='maEqMngList' WHERE description='설비담당자변경'
go
update TAMENU set key_no='BUDGETPLAN' WHERE description='예산계획'
go
update TAMENU set key_no='SYSCONNLIST' WHERE description='시스템 접속현황'
go
update TAMENU set key_no='USERMANUAL' WHERE description='사용자매뉴얼'
go
update TAMENU set key_no='PMPOINTLIST' WHERE description='예방점검내역'
go
update TAMENU set key_no='MWARESET' WHERE description='MWARE설정'
go
update TAMENU set key_no='PAGE' WHERE description='화면'
go
update TAMENU set key_no='MENU' WHERE description='메뉴'
go
update TAMENU set key_no='BUTTON' WHERE description='버튼'
go
update TAMENU set key_no='CDUSR' WHERE description='사용자코드'
go
update TAMENU set key_no='DEPT' WHERE description='작업부서'
go
update TAMENU set key_no='EMP' WHERE description='보전사원'
go
update TAMENU set key_no='USRGRP' WHERE description='권한명'
go
update TAMENU set key_no='USER' WHERE description='사용자'
go
update TAMENU set key_no='MYINFO' WHERE description='개인설정'
go
update TAMENU set key_no='LANG' WHERE description='다국어'
go
update TAMENU set key_no='CONFIG' WHERE description='시스템 환경변수'
go
update TAMENU set key_no='SESSION' WHERE description='실시간 접속자'
go
update TAMENU set key_no='SYSMNG' WHERE description='시스템관리'
go
update TAMENU set key_no='WOMNG' WHERE description='작업관리'
go
update TAMENU set key_no='KPI' WHERE description='분석지표'
go
update TAMENU set key_no='PTUSELIST' WHERE description='부품사용내역'
go
update TAMENU set key_no='BATCHMNG' WHERE description='배치작업내역'
go
update TAMENU set key_no='WODAILYRATE' WHERE description='일별작업실행율'
go
update TAMENU set key_no='WOLINEPF' WHERE description='라인가동내역'
go
update TAMENU set key_no='BUDGETUSELIST' WHERE description='예산사용현황'
go
update TAMENU set key_no='NONSTOPLIST' WHERE description='무정지현황'
go
update TAMENU set key_no='NONSTOPGOAL' WHERE description='무정지목표율'
go
update TAMENU set key_no='PMREPLIST' WHERE description='예방수리내역'
go
update TAMENU set key_no='DEPTWO' WHERE description='부서별작업분석'
go
update TAMENU set key_no='PURREQ' WHERE description='현장구매신청'
go
update TAMENU set key_no='QNA' WHERE description='질의응답'
go
update TAMENU set key_no='PTBUY' WHERE description='구매신청'
go
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-06-08   김정우 메뉴 다국어 라벨  추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------

/** 2016-06-14 노정현 일일작업일지확인 메뉴 추가 */
INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '작업관리'), (SELECT page_id FROM TAPAGE WHERE file_name ='maWoDailyList'), '90', 
    'N', 'WODAILY')
go

INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '일반관리'), (SELECT page_id FROM TAPAGE WHERE file_name ='maAppLineList'), '230', 
    'N', 'APPLINE')
go
     
INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '일반관리'), (SELECT page_id FROM TAPAGE WHERE file_name ='appReadyList'), '235', 
    'N', 'APPREADY')
go
    
/** 2016-06-28 김정우 무상입고 메뉴추가 */
     INSERT INTO TAMENU
   (MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, '무상입고',(SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '부품관리'), (SELECT page_id FROM TAPAGE WHERE file_name ='maPtFcRecList'), '90', 
    'N', 'PTFCREC')
go
    
/** 2016-06-29 노정현 월별보전목표  */
INSERT INTO TAMENU
   (MENU_ID, DESCRIPTION,P_MENU_ID, ORD_NO, IS_SYSTEM, KEY_NO)
 VALUES
       (NEXT VALUE FOR SQAMENU_ID,'보전목표', 0, '100', 'N', 'MAINTGOAL')
go
 INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '보전목표'), (SELECT page_id FROM TAPAGE WHERE file_name ='maMtGoalList'), '900', 
    'N', 'MONGOAL')
go 
    
-------------------------------------
/** 2016-06-30 김정우 - 라인가동계획 메뉴 추가 */
-------------------------------------
INSERT INTO TAMENU
   (MENU_ID, DESCRIPTION,P_MENU_ID, ORD_NO, IS_SYSTEM, KEY_NO)
 VALUES
       (NEXT VALUE FOR SQAMENU_ID,'라인가동', 0, '45', 'N', 'LNRUN')
go
  INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '보전목표'), (SELECT page_id FROM TAPAGE WHERE file_name ='maLnGoalList'), '910', 
    'N', 'LNGOAL')
go
     INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '라인가동'), (SELECT page_id FROM TAPAGE WHERE file_name ='maLineRunPlanList'), '900', 
    'N', 'LNRUNPLAN')
go
    
    
INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '부품관리'), (SELECT page_id FROM TAPAGE WHERE file_name ='maPtIssList'), '95', 
    'N', 'PTISS','MENU', 'Y')
go
    /** 2016-07-27 김정우 월별재고목표 메뉴추가 */
INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '부품관리'), (SELECT page_id FROM TAPAGE WHERE file_name ='maMonthStockGoalList'), '96', 
    'N', 'MONTHSTOCKGOAL','MENU', 'Y')
go
/** 2016-07-28 Working Calendar 메뉴추가 */
INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '라인가동'), (SELECT page_id FROM TAPAGE WHERE file_name ='maWoCalList'), '910', 
    'N', 'WORKCAL','MENU', 'Y')
go
/** 2016-08-01 김정우 일일보전지표 */
INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '분석지표'), (SELECT page_id FROM TAPAGE WHERE file_name ='maDayLocDnChart'), '170', 
    'N', 'DAYLOCDN','MENU', 'Y')
go
/** 2016-08-03 김정우 월간보전지표 */
INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '분석지표'), (SELECT page_id FROM TAPAGE WHERE file_name ='maMonLocDnChart'), '180', 
    'N', 'MONLOCDN','MENU', 'Y')
go
/** 2016-08-04 김정우 월간Mttr,Mtbf지표 */
INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '분석지표'), (SELECT page_id FROM TAPAGE WHERE file_name ='maMonMttrChart'), '190', 
    'N', 'MONMTTRMTBF','MENU', 'Y')
go
INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '분석지표'), (SELECT page_id FROM TAPAGE WHERE file_name ='maYearMttrChart'), '210', 
    'N', 'YEARMTTRMTBF','MENU', 'Y')
go
 /** 2016-08-03 이규선 연간BD지표 */   
    INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '분석지표'), (SELECT page_id FROM TAPAGE WHERE file_name ='maBmYearChart'), '190', 
    'N', 'BMYEAR','MENU', 'Y')
go
 /** 2016-08-08 작업유형별현황 */   
    INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '분석지표'), (SELECT page_id FROM TAPAGE WHERE file_name ='maWoTypeChart'), '220', 
    'N', 'WOTYPE','MENU', 'Y')
go
 /** 2016-08-03 이규선 월간BD지표 */   
        INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '분석지표'), (SELECT page_id FROM TAPAGE WHERE file_name ='maBmMonthChart'), '2000', 
    'N', 'BMMONTH','MENU', 'Y')
go
 /** 2016-08-16 김정우  교육지표 */   
        INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '분석지표'), (SELECT page_id FROM TAPAGE WHERE file_name ='maTrChart'), '230', 
    'N', 'TRTYPEKPI','MENU', 'Y')
go
     /** 2016-08-18 김정우  투자지표 */   
        INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '분석지표'), (SELECT page_id FROM TAPAGE WHERE file_name ='maIvChart'), '240', 
    'N', 'IVTYPEKPI','MENU', 'Y')
go
    
         /** 2016-08-24 김정우  재고지표  */   
        INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '분석지표'), (SELECT page_id FROM TAPAGE WHERE file_name ='maStckChart'), '250', 
    'N', 'STCKKPI','MENU', 'Y')
go
    
        /** 2016-08-24 이규선  공기구관리 */   
INSERT into TAMENU
   (MENU_ID, DESCRIPTION, P_MENU_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 Values
   (NEXT VALUE FOR SQAMENU_ID, '공기구관리', 0, '920', 
    'N', 'PTTMNG', 'MENU', 'Y')
go
    
    INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '공기구관리'), (SELECT page_id FROM TAPAGE WHERE file_name ='maPttMstrList'), '100', 
    'N', 'PTTMSTR','MENU', 'Y')
go
    
     INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '공기구관리'), (SELECT page_id FROM TAPAGE WHERE file_name ='maPttStckList'), '110', 
    'N', 'PTTSTCK','MENU', 'Y')
go
    
    
     INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '공기구관리'), (SELECT page_id FROM TAPAGE WHERE file_name ='maPttRecList'), '120', 
    'N', 'PTTREC','MENU', 'Y')
go
    
     
 
      INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '공기구관리'), (SELECT page_id FROM TAPAGE WHERE file_name ='maPttIssList'), '130', 
    'N', 'PTTISS','MENU', 'Y')
go
 
    INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '공기구관리'), (SELECT page_id FROM TAPAGE WHERE file_name ='maPttRtnList'), '140', 
    'N', 'PTTRTN','MENU', 'Y')
go
    
        INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '공기구관리'), (SELECT page_id FROM TAPAGE WHERE file_name ='maPttRentList'), '160', 
    'N', 'PTTRENT','MENU', 'Y')
go
 
     INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '공기구관리'), (SELECT page_id FROM TAPAGE WHERE file_name ='maPttDisList'), '150', 
    'N', 'PTTDIS','MENU', 'Y')
go
    
    
   /** 2016-09-06 박철완 추가 */     
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE) VALUES(NEXT VALUE FOR SQAMENU_ID,'공장(Plant)',0,NULL,'220','','N','PLANTMNG','MENU','Y')
go 
    
 
 
 INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, '0' , null, '960', 'N', 'INTERFACE','MENU', 'Y')
go
 INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = 'Interface'), (SELECT page_id FROM TAPAGE WHERE file_name ='maIfPtIssList'), '900', 
    'N', 'PTISSINTFHIST','MENU', 'Y')
go
    /** 2016-09-07 김정우 부품마스터 Interface */
     INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = 'Interface'), (SELECT page_id FROM TAPAGE WHERE file_name ='maIfPtMstrList'), '100', 
    'N', 'PTMSTRINTFHIST','MENU', 'Y')
go
     /** 2016-09-07 김정우 구매청구 Interface */
    
  INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = 'Interface'), (SELECT page_id FROM TAPAGE WHERE file_name ='maIfPtPoList'), '200', 
    'N', 'PTPOINTFHIST','MENU', 'Y')
go
    
    /** 2016-09-07 김정우 부품출고처리내역 Interface */
  INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = 'Interface'), (SELECT page_id FROM TAPAGE WHERE file_name ='maIfPtIssErpList'), '300', 
    'N', 'PTISSERPINTFHIST','MENU', 'Y')
go
    /** 2016-09-07 김정우 재고 Interface */
  INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = 'Interface'), (SELECT page_id FROM TAPAGE WHERE file_name ='maIfPtStockList'), '910', 
    'N', 'PTSTOCKINTFHIST','MENU', 'Y')
go
    /** 2016-09-08 이규선 예방작업-예방작업일정(기간)*/
    INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
       (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '예방작업'), (SELECT page_id FROM TAPAGE WHERE file_name ='maWoSchedList'), '50', 
    'N', 'WOSCHED','MENU', 'Y')
go

    /** 2016-09-07 김정우 MES 가동시간 Interface */
  INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = 'Interface'), (SELECT page_id FROM TAPAGE WHERE file_name ='maIfMesRunList'), '500', 
    'N', 'PTMESRUNINTFHIST','MENU', 'Y')
go
    
    /** 2016-09-07 김정우 MES 정지시간 Interface */
  INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = 'Interface'), (SELECT page_id FROM TAPAGE WHERE file_name ='maIfMesStopList'), '600', 
    'N', 'PTMESSTOPINTFHIST','MENU', 'Y')
go
/** 2016-09-12 김정우 주간작업일정 추가 */
    
  INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '작업관리'), (SELECT page_id FROM TAPAGE WHERE file_name ='maWoWeekWoList'), '40', 
    'N', 'WEEKWOSCHED','MENU', 'Y')
go
    
 INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '분석지표'), 
    (SELECT page_id FROM TAPAGE WHERE file_name ='maEqBdChart'), '260', 'N', 'MAEQBDCHART','MENU', 'Y')
go
/** 2016-09-20 김정우 일별 BD 지표(라인별) 추가 */
 INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '분석지표'), 
    (SELECT page_id FROM TAPAGE WHERE file_name ='maBmDailyLineChart'), '270', 'N', 'BMDAILYLINE','MENU', 'Y')
go
 INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '분석지표'), 
    (SELECT page_id FROM TAPAGE WHERE file_name ='maWkBdChart'), '280', 'N', 'MAWKBDCHART','MENU', 'Y')
go
    
     /** 2016-09-21 이규선 월간 BD 지표(라인별) 추가 */
 INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '분석지표'), 
    (SELECT page_id FROM TAPAGE WHERE file_name ='maBmMonthLineChart'), '265', 'N', 'BMMONTHLINE','MENU', 'Y')
go
/** 2016-09-21 김정우 일별 BD 지표 추가 */
 INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '분석지표'), 
    (SELECT page_id FROM TAPAGE WHERE file_name ='maBmDailyChart'), '290', 'N', 'BMDAILY','MENU', 'Y')
go
    
    
/** 20160926 김정우 슬로박지표 추가 */
 INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, 0, 
    null, '610', 'N', 'PLANTREPORT','MENU', 'Y')
go
    
 INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '공장별지표'), 
    (SELECT page_id FROM TAPAGE WHERE file_name ='maSlovakReport'), '900', 'N', 'SLOVAKREPORT','MENU', 'Y')
go
    
    
     INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '공장별지표'), 
    (SELECT page_id FROM TAPAGE WHERE file_name ='maCzechReport'), '910', 'N', 'CZECHREPORT','MENU', 'Y')
go
    
    
  /** 2016-10-13 김정우 메뉴 추가 */
 INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '부품관리'), 
    (SELECT page_id FROM TAPAGE WHERE file_name ='maPtPoList'), '410', 'N', 'PTPO','MENU', 'Y')
go

  /** 2016-10-17 김정우 메뉴 추가 */
 INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '일반관리'), 
    (SELECT page_id FROM TAPAGE WHERE file_name ='maWkCtrList'), '110', 'N', 'WKCTR','MENU', 'Y')
go
    
      /** 2016-10-19 이규선 메뉴 추가 */
 INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '분석지표'), 
    (SELECT page_id FROM TAPAGE WHERE file_name ='maMonitoring'), '280', 'N', 'CZMONITORING','MENU', 'Y')
go
    
/** 2016-10-24 노정현 메뉴 추가 */    
 INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '부품관리'), 
    (SELECT page_id FROM TAPAGE WHERE file_name ='maPtIssEmgList'), '650', 'N', 'PTEMG','MENU', 'Y')
go
    
/** 2016-10-24 김정우 추가 */
     INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '시스템관리'), 
    (SELECT page_id FROM TAPAGE WHERE file_name ='maResList'), '910', 'N', 'RES','MENU', 'Y')
go
    /** 2016- 10-27 금형 관리  슬로바키아만 적용 */
     INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '분석지표'), 
    (SELECT page_id FROM TAPAGE WHERE file_name ='maMoldLayout'), '280', 'N', 'MOLDLAYOUT','MENU', 'Y')
go
    
    
/** 2016-10-27 노정현 추가 */
INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = 'Interface'), 
    (SELECT page_id FROM TAPAGE WHERE file_name ='maIfPtRecList'), '920', 'N', 'ERPREC','MENU', 'Y')
go 
    
/** 2016-10-28 김정우 금형관리 추가 */
     INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '작업관리'), 
    (SELECT page_id FROM TAPAGE WHERE file_name ='maMoldsList'), '91', 'N', 'MOLDSMNG','MENU', 'Y')
go
   
/** 2016-11-03 노정현 Help Desk 추가 */
INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '일반관리'), 
    (SELECT page_id FROM TAPAGE WHERE file_name ='maHelpList'), '91', 'N', 'HELPDESK','MENU', 'Y')
go

/** 2016-11-16 노정현 개선진행현황 추가 */
INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = 'MWare Config'), 
    (SELECT page_id FROM TAPAGE WHERE file_name ='maWrkImpList'), '230', 'Y', 'WRKIMP','MENU', 'Y')
go
    
/** 2016- 11- 21 김정우 추가 */
INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, 0, null, '620', 'N', 'GKPI','MENU', 'Y')
go
   
INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '글로벌지표'), 
    (SELECT page_id FROM TAPAGE WHERE file_name ='maGlMonthlyMtChart'), '100', 'N', 'GLMONMTCHART','MENU', 'Y')
go
   
    /** 2016- 11- 22 김정우 추가 */
INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '글로벌지표'), 
    (SELECT page_id FROM TAPAGE WHERE file_name ='maGlStockList'), '110', 'N', 'GLSTOCK','MENU', 'Y')
go
    
    
        /** 2016- 12-14 금형 관리  슬로바키아만 적용 */
     INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '분석지표'), 
    (SELECT page_id FROM TAPAGE WHERE file_name ='maMtlMoldLayout'), '290', 'N', 'MTLMOLDLAYOUT','MENU', 'N')
go
    
        /** 2017-01-05 몬테레이 일일레포트 */
   INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '분석지표'), 
    (SELECT page_id FROM TAPAGE WHERE file_name ='maMtlDailyReport'), '300', 'N', 'MTLDAILYREPORT','MENU', 'N')
go
   
    /** 2017-01-05 김정우 작업시간 설정 */
    
     INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '라인가동'), 
    (SELECT page_id FROM TAPAGE WHERE file_name ='maLineTimeList'), '300', 'N', 'RUNTIMESET','MENU', 'N')
go
    
    -----------------------------------------------------------------------------
--------------------------------유한킴벌리 patch완료 2017.01.13일-------------------
-----------------------------------------------------------------------------

    /** 2017- 01- 12 이규선 추가 */  
         INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '공장별지표'), 
    (SELECT page_id FROM TAPAGE WHERE file_name ='maMtlReport'), '910', 'N', 'MTLREPORT','MENU', 'N')
go
        
/**2017-01-18 김정우 작업요청 추가 */
         INSERT INTO TAMENU(MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES(NEXT VALUE FOR SQAMENU_ID, 0,null, '210', 'N', 'WOREQ','MENU', 'N')
go
   
         INSERT INTO TAMENU(MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND 
a.key_type='MENU' AND a.lang='ko') = '작업요청'), null, '100', 'N', 'WOREQDOC','MENU', 'N')
go
    
         INSERT INTO TAMENU(MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND 
a.key_type='MENU' AND a.lang='ko') = '작업요청서'), 
    (SELECT page_id FROM TAPAGE WHERE file_name ='maWoReqList'), '100', 'N', 'WOREQLIST','MENU', 'N')
go

    /** 2017-01-25 김정우 추가 */
        
INSERT INTO TAMENU(MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND 
a.key_type='MENU' AND a.lang='ko') = '작업관리'), 
  (SELECT page_id FROM TAPAGE WHERE file_name ='maWoDayRptList'), '92', 'N', 'WRKDAYRPT','MENU', 'N')
go
  
  /** 2017-03-02 김정우 추가 */
  
INSERT INTO TAMENU(MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND 
a.key_type='MENU' AND a.lang='ko') = '분석지표'), 
  (SELECT page_id FROM TAPAGE WHERE file_name ='maWoTopFiveList'), '310', 'N', 'WOTOP5','MENU', 'N')
go
  
/** 2017-03-08 노정현 추가  */
 INSERT INTO TSDEVMENU (sdevmenu_id, sdevmenu_no, description, p_sdevmenu_id, p_sdevmenu_no, sfile_id, sfile_name, ord_no, isuse, key_no, key_type)
 VALUES(sqasdevmenu_id.NEXTVAL, sqasdevmenu_id.CURRVAL, '테스트메뉴', '0', '0',  (SELECT sfile_id FROM TSFILE WHERE sfile_name ='gaPgMngList'), 'gaPgMngList', '500', 'Y', 'gaPgMngList','MENU' )
go

 /** 2017-03-13 이규선 추가  */
   INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '분석지표'), 
    (SELECT page_id FROM TAPAGE WHERE file_name ='maBzDailyReport'), '300', 'N', 'BZDAILYREPORT','MENU', 'N')
go
    
 -----------------------------------------------------------------------------
--------------------------------유한킴벌리 patch완료 2017.03.15일-------------------
-----------------------------------------------------------------------------    
     /** 2017-03-16 이규선 추가  */
           INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '공장별지표'), 
    (SELECT page_id FROM TAPAGE WHERE file_name ='maBzReport'), '910', 'N', 'BZREPORT','MENU', 'N')
go
    
    
/** 2017-03-21 노정현 추가  */
    
 INSERT INTO TAEHMENU (ehmenu_id, ehmenu_no, description, p_ehmenu_id, p_ehmenu_no, page_id, file_name, ord_no, is_use, key_no, key_type, param)
 VALUES(sqaehmenu_id.NEXTVAL, sqaehmenu_id.CURRVAL, '메뉴사용자', '0', '0',  (SELECT page_id FROM TAPAGE WHERE file_name ='mcMenuList'), 'mcMenuList', '120', 'Y', 'mcMenuList','MENU', '' )
go
 
 
 INSERT INTO TAEHMENU (ehmenu_id, ehmenu_no, description, p_ehmenu_id, p_ehmenu_no, page_id, file_name, ord_no, is_use, key_no, key_type, param)
 VALUES(sqaehmenu_id.NEXTVAL, sqaehmenu_id.CURRVAL, '셋팅사용자', '0', '0',  (SELECT page_id FROM TAPAGE WHERE file_name ='mcUserList'), 'mcUserList', '125', 'Y', 'mcUserList','MENU', '' )
go
 /** 2017-03-23 김정우 추가 */

  INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '분석지표'), 
    (SELECT page_id FROM TAPAGE WHERE file_name ='maGaDailyReport'), '900', 'N', 'GADAILYREPORT','MENU', 'N')
go
    



     /** 2017-04-04 이규선 추가 */
     INSERT INTO TAEHMENU (ehmenu_id, ehmenu_no, description, p_ehmenu_id, p_ehmenu_no, page_id, file_name, ord_no, is_use, key_no, key_type, param)
 VALUES(sqaehmenu_id.NEXTVAL, sqaehmenu_id.CURRVAL, '데이터 테이블', '1023', '0',  (SELECT page_id FROM TAPAGE WHERE file_name ='maTableList'), 'maTableList', '99950', 'N', 'maTableList','MENU', '' )
go
 
 
-----------------------------------------------------------------------------
--------------------------------mseat patch완료 2017.04.04일-------------------
-----------------------------------------------------------------------------   
-----------------------------------------------------------------------------
--------------------------------다이모스 체코 patch완료 2017.04.04일-------------------
-----------------------------------------------------------------------------
 -----------------------------------------------------------------------------
--------------------------------다이모스 슬로박 patch완료 2017.04.04일-------------------
-----------------------------------------------------------------------------
 -----------------------------------------------------------------------------
--------------------------------다이모스 몬테레이 patch완료 2017.04.04일-------------------
-----------------------------------------------------------------------------
 -----------------------------------------------------------------------------
--------------------------------다이모스 slp patch완료 2017.04.04일-------------------
-----------------------------------------------------------------------------
 -----------------------------------------------------------------------------
--------------------------------브라질 patch완료 2017.03.24일-------------------
-----------------------------------------------------------------------------  

-----------------------------------------------------------------------------
--------------------------------다이모스 조지아 patch완료 2017.03.24일-------------------
----------------------------------------------------------------------------
 
 
/** 2017-04-07 노정현 사용자데이터 조회 추가  */
   INSERT INTO TAEHMENU (ehmenu_id, ehmenu_no, description, p_ehmenu_id, p_ehmenu_no, page_id, file_name, ord_no, is_use, key_no, key_type, param)
 VALUES(sqaehmenu_id.NEXTVAL, sqaehmenu_id.CURRVAL, '사용자데이터조회', '0', '0',  (SELECT page_id FROM TAPAGE WHERE file_name ='mcDataSelectList'), 'mcDataSelectList', '130', 'Y', 'mcDataSelectList','MENU', '' )
go
 
 
 
-----------------------------------------------------------------------------
--------------------------------유한킴벌리 patch완료 2017.04.12일-------------------
-----------------------------------------------------------------------------
 
 /** 2017-04-13 노정현 메뉴 이동 */
 
 INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '시스템관리'), 
    (SELECT page_id FROM TAPAGE WHERE file_name ='mcDataSelectList'), '900', 'N', 'mcDataSelectList','MENU', 'N')
go
  
delete  taehmenu where page_id in (SELECT page_id FROM TAPAGE WHERE file_name ='mcDataSelectList')
go

UPDATE TAMENU set param = 'mcDataSelectCommonDTO.usrrptType=SEL' WHERE page_id = (
select page_id from tapage where  file_name ='mcDataSelectList'
)
go


 /** 2017-05-17 노정현 사용자레포트  */
 
  INSERT INTO TAMENU
   (MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES
   (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '시스템관리'), 
    (SELECT page_id FROM TAPAGE WHERE file_name ='maUserRptList'), '890', 'N', 'maUserRptList','MENU', 'N')
go




    
 /** 2017-07-04 박철완  */
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'금형목록'
             ,(select menu_id from tamenu where description = '설비자산')
             ,(select page_id from tapage where file_name = 'maEqMstrMoldList')
             ,'35','','N','EQMOLDMSTR','MENU','Y');
    
/** 2017-07-11 김정우 추가 */
 INSERT INTO TAEHMENU (ehmenu_id, ehmenu_no, description, p_ehmenu_id, p_ehmenu_no, page_id, file_name, ord_no, is_use,is_basic, key_no, key_type, param)
 VALUES(NEXT VALUE FOR SQAEHMENU_ID, CONVERT(NVARCHAR,(SELECT CURRENT_VALUE FROM SYS.SEQUENCES WHERE name='SQAEHMENU_ID')), '접근터미널',(SELECT ehmenu_id FROM TAEHMENU WHERE ehmenu_no = 'SITE'),'SITE',  (SELECT page_id FROM TAPAGE WHERE file_name ='consultCompTerminalList'), 'consultCompTerminalList', '99940', 'Y', 'Y', 'TERMINAL','MENU', '' );
 
/** 2017-07-11 노정현 추가 */
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, SERVICE_TYPE)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'고장Library',
             (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '고장코드관리'), 
             (select page_id from tapage where file_name = 'failLibraryList')
             ,'310','','N','FAILCODELIB','MENU','Y', 'WEB')
             go

/** 2017-07-18 노정현 추가 */
             
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, SERVICE_TYPE)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'문서분류체계',
             (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '공유자료'), 
             (select page_id from tapage where file_name = 'docCtgList')
             ,'210','','N','DOCCATEG','MENU','Y', 'WEB')
        go
        
   /** 2017-07-21 차한결 추가  */
INSERT INTO TAEHMENU (ehmenu_id, ehmenu_no, description, p_ehmenu_id, p_ehmenu_no, page_id, file_name, ord_no, is_use,is_basic, key_no, key_type, param)
VALUES(NEXT VALUE FOR SQAEHMENU_ID, CONVERT(NVARCHAR,(SELECT CURRENT_VALUE FROM SYS.SEQUENCES WHERE name='SQAEHMENU_ID')), '근무일달력'
 		,(SELECT ehmenu_id FROM TAEHMENU WHERE ehmenu_no = 'SITE')
 		,'SITE'
 		,(SELECT page_id FROM TAPAGE WHERE file_name ='consultCompWrkcalList')
 		,'consultCompWrkcalList', '99950', 'Y', 'Y', 'WRKCALLIST','MENU', '' )
        go

        

/** 2017-07-30 박철완 추가 */             
             
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, SERVICE_TYPE)
 VALUES(NEXT VALUE FOR SQAEHMENU_ID,'예방점검일정(연간)',
             (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '작업일정'),
             (select page_id from tapage where file_name = 'workCalPmRInsYearList')
             ,'600','','N','WORKCALPMRINSYEAR','MENU','Y', 'WEB');

INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, SERVICE_TYPE)
 VALUES(NEXT VALUE FOR SQAEHMENU_ID,'예방점검일정(월간)',
             (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '작업일정'),
             (select page_id from tapage where file_name = 'workCalPmRInsMonthList')
             ,'700','','N','WORKCALPMRINSMONTH','MENU','Y', 'WEB');

INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, SERVICE_TYPE)
 VALUES(NEXT VALUE FOR SQAEHMENU_ID,'예방점검일정(기간)',
             (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '작업일정'),
             (select page_id from tapage where file_name = 'workCalPmRInsPeriodList')
             ,'800','','N','WORKCALPMRINSPERIOD','MENU','Y', 'WEB');

INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, SERVICE_TYPE)
 VALUES(NEXT VALUE FOR SQAEHMENU_ID,'일상점검일정',
             (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '작업일정'),
             (select page_id from tapage where file_name = 'workCalPmDInsMonthList')
             ,'900','','N','WORKCALPMDINSMONTH','MENU','Y', 'WEB');

 /** 2017-08-08 김정우 추가 */
  INSERT INTO TAEHMENU (ehmenu_id, ehmenu_no, description, p_ehmenu_id, p_ehmenu_no, page_id, file_name, ord_no, is_use,is_basic, key_no, key_type, param)
 VALUES(NEXT VALUE FOR SQAEHMENU_ID, CONVERT(NVARCHAR,(SELECT CURRENT_VALUE FROM SYS.SEQUENCES WHERE NAME='SQAEHMENU_ID')), '프로그램 가이드',(SELECT ehmenu_id FROM TAEHMENU WHERE ehmenu_no = 'SITE'),'SITE',  (SELECT page_id FROM TAPAGE WHERE file_name ='consultPgmGuideList'), 'consultPgmGuideList', '1500', 'Y', 'Y', 'PGMGUIDE','MENU', '' );
 
   /** 2017-08-08 차한결 추가  */
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, SERVICE_TYPE)
VALUES(NEXT VALUE FOR SQAMENU_ID, 'RCM', 0, '1130', 'N', 'RCM', 'MENU', 'Y', 'WEB');
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE,SERVICE_TYPE)
VALUES(NEXT VALUE FOR SQAMENU_ID,'System분석'
             ,(SELECT menu_id FROM tamenu WHERE description = 'RCM')
             ,(SELECT page_id FROM tapage WHERE file_name = 'rcmSysList')
             ,'10','','N','RCMSYSTEM','MENU','Y','WEB');
             
  /** 2017-08-08 김정우 추가 */
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE,SERVICE_TYPE)
VALUES(NEXT VALUE FOR SQAMENU_ID,'Criticality Matrix'
             ,(SELECT menu_id FROM tamenu WHERE description = 'RCM')
             ,(SELECT page_id FROM tapage WHERE file_name = 'rcmCrityList')
             ,'180','','N','CRITICALMATRIX','MENU','Y','WEB');
             
             
/** 2017-08-21 김영주 추가 */
INSERT INTO TAEHMENU (ehmenu_id, ehmenu_no, description, p_ehmenu_id, p_ehmenu_no, page_id, file_name, ord_no, is_basic, is_use, key_type, key_no)
VALUES(NEXT VALUE FOR SQAMENU_ID, CONVERT(NVARCHAR,(SELECT CURRENT_VALUE FROM SYS.SEQUENCES WHERE NAME='SQAEHMENU_ID')), '사용자', (SELECT ehmenu_id FROM TAEHMENU WHERE ehmenu_no = 'SITE')
                , 'SITE', (SELECT page_id FROM TAPAGE WHERE file_name='consultCompUserList'), 'consultCompUserList', '1130', 'Y', 'Y', 'MENU', 'USER' )

/** 2017-08-23 김정우 추가 */
    INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE,SERVICE_TYPE)
VALUES(NEXT VALUE FOR SQAMENU_ID,'설비등급 평가기준'
             ,(SELECT menu_id FROM tamenu WHERE description = '시스템관리')
             ,(SELECT page_id FROM tapage WHERE file_name = 'assBaseList')
             ,'991','','N','ASSBASE','MENU','Y','WEB');
             
             
/** 2017-08-24 박철완 추가 */             
update tamenu set key_no= 'WOREQRECLIST'  ,ord_no = '200' where  key_no = 'WOREQLIST';


INSERT INTO TAMENU(MENU_ID, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, SERVICE_TYPE)
 VALUES (NEXT VALUE FOR SQAMENU_ID, (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '요청관리')
         ,   (SELECT page_id FROM TAPAGE WHERE file_name ='reqWorkList'), '100', 'N', 'WOREQLIST','MENU', 'N','WEB');
              
              
/** 2017-08-25 김영주 추가 */
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, SERVICE_TYPE)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'설비등급 평가',
             (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '시스템관리'),
             (SELECT page_id FROM TAPAGE WHERE file_name = 'assAssetList'),'992','','N','ASSASSET','MENU','Y', 'WEB');

             
/** 2017-08-30 장효성 추가 */          
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, SERVICE_TYPE)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'구매절차',
             (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '시스템관리'), 
             (SELECT page_id FROM tapage WHERE file_name = 'invtPrcTpList')
             ,'150','','N','INVTPRC','MENU','Y', 'WEB');
             
             
                
/** 2017-08-30 이근환 추가  */
             
 INSERT INTO TAEHMENU (ehmenu_id, ehmenu_no, description, p_ehmenu_id, p_ehmenu_no, page_id, file_name, ord_no, is_use,is_basic, key_no, key_type, param)
 VALUES(NEXT VALUE FOR sqaehmenu_id, CONVERT(NVARCHAR,(SELECT CURRENT_VALUE FROM SYS.SEQUENCES WHERE NAME='sqaehmenu_id')), '컨설팅 사원','1220','0',  (SELECT page_id FROM TAPAGE WHERE file_name ='consultCompEmpList'), 'consultCompEmpList', '1110', 'Y', 'Y', 'EMP','MENU', '' );
 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, SERVICE_TYPE) 
VALUES(NEXT VALUE FOR SQAMENU_ID,'승인Flow',
			(SELECT menu_id FROM TAMENU WHERE key_no='SYSMNG'),
			(SELECT page_id FROM TAPAGE WHERE file_name='mgrWorkflowList'),'170','','N','WORKFLOW','MENU','Y','WEB');

/** 2017-09-06 노정현 추가  */
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, SERVICE_TYPE)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'FMEA',
             (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = 'RCM'), 
             (SELECT page_id FROM tapage WHERE file_name = 'rcmFmeaList')
             ,'140','','N','RCMFMEA','MENU','Y', 'WEB');

INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, SERVICE_TYPE)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'PMTASK',
             (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = 'RCM'), 
             (SELECT page_id FROM tapage WHERE file_name = 'rcmPmtaskList')
             ,'170','','N','RCMPMTASK','MENU','Y', 'WEB');

INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, SERVICE_TYPE)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'Equipment Investment',
             (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '시스템관리'),
             (SELECT page_id FROM tapage WHERE file_name = 'invtList'),'160','','N','EQUIPINVT','MENU','Y', 'WEB');

INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, SERVICE_TYPE)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'Equipment Investment',
             (SELECT x.menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '시스템관리'),
             (SELECT page_id FROM tapage WHERE file_name = 'invtPrcList'),'170','','N','EQUIPINVTPRC','MENU','Y', 'WEB');


  /** 2017-09-19 김정우 추가 */
    INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE,SERVICE_TYPE)
VALUES(NEXT VALUE FOR SQAMENU_ID,'교정표준값'
             ,(SELECT menu_id FROM tamenu WHERE description = '시스템관리')
             ,(SELECT page_id FROM tapage WHERE file_name = 'workPmStdCalibList')
             ,'994','','N','STDCALIB','MENU','Y','WEB');

  /** 2017-09-20 차한결 추가 */
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, SERVICE_TYPE)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'PM Work',
             (SELECT x.menu_id FROM TAMENU x WHERE (SELECT A.key_name FROM TALANG A WHERE A.key_no = x.key_no AND A.key_type='MENU' AND A.lang='ko') = '예방작업' AND page_id IS NULL),
             (SELECT page_id FROM tapage WHERE file_name = 'workPmListWorkList'),'20','','N','PMWMSTR','MENU','Y', 'WEB');           
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, SERVICE_TYPE)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'PM Inspection',
             (SELECT x.menu_id FROM TAMENU x WHERE (SELECT A.key_name FROM TALANG A WHERE A.key_no = x.key_no AND A.key_type='MENU' AND A.lang='ko') = '예방작업' AND page_id IS NULL),
             (SELECT page_id FROM tapage WHERE file_name = 'workPmListInsList'),'30','','N','PMIMSTR','MENU','Y', 'WEB');
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, SERVICE_TYPE)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'PM Calibration',
             (SELECT x.menu_id FROM TAMENU x WHERE (SELECT A.key_name FROM TALANG A WHERE A.key_no = x.key_no AND A.key_type='MENU' AND A.lang='ko') = '예방작업' AND page_id IS NULL),
             (SELECT page_id FROM tapage WHERE file_name = 'workPmListCalList'),'40','','N','PMCMSTR','MENU','Y', 'WEB'); 
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, SERVICE_TYPE)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'PM Training',
             (SELECT x.menu_id FROM TAMENU x WHERE (SELECT A.key_name FROM TALANG A WHERE A.key_no = x.key_no AND A.key_type='MENU' AND A.lang='ko') = '예방작업' AND page_id IS NULL),
             (SELECT page_id FROM tapage WHERE file_name = 'workPmListTrList'),'40','','N','PMTRMSTR','MENU','Y', 'WEB');  
             
 /** 2017-09-21 김영주 추가 */
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, SERVICE_TYPE)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'순회일정',
             (SELECT menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '작업일정'),
             (SELECT page_id FROM TAPAGE WHERE file_name = 'workListPtrlResultList'),'990','','N','PTRLCAL','MENU','Y', 'WEB');
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, SERVICE_TYPE)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'순회일정(월간)',
             (SELECT menu_id FROM TAMENU x WHERE (SELECT a.key_name FROM TALANG a WHERE a.key_no = x.key_no AND a.key_type='MENU' AND a.lang='ko') = '작업일정'),
             (SELECT page_id FROM TAPAGE WHERE file_name = 'workCalPmPtrlMonthList'),'991','','N','MONPTRLCAL','MENU','Y', 'WEB');
 
/** 2017-09-21 김정우 */
    INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE,SERVICE_TYPE)
VALUES(NEXT VALUE FOR SQAMENU_ID,'고장영향성 평가'
             ,(SELECT menu_id FROM tamenu WHERE description = '작업관리')
             ,(SELECT page_id FROM tapage WHERE file_name = 'workFmeaList')
             ,'310','','N','WORKFMEA','MENU','Y','WEB');
             
/** 2017-09-22 차한결 추가 */
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, SERVICE_TYPE)
 VALUES(NEXT VALUE FOR SQAMENU_ID,'예방점검작업목록',
             (SELECT x.menu_id FROM TAMENU x WHERE (SELECT A.key_name FROM TALANG A WHERE A.key_no = x.key_no AND A.key_type='MENU' AND A.lang='ko') = '작업관리' AND page_id IS NULL),
             (SELECT page_id FROM tapage WHERE file_name = 'workListPmiList'),'20','','N','WOPMIRESULT','MENU','Y', 'WEB');             
             
/** 2017-09-22 장효성 추가*/
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, SERVICE_TYPE) 
VALUES(NEXT VALUE FOR SQALANG_ID,'생산설비목록',
            (SELECT menu_id FROM TAMENU WHERE key_no='EQINFO'),
            (SELECT page_id FROM TAPAGE WHERE file_name='maEqMachMstrList'),'140','','N','EQMACHMSTR','MENU','Y','WEB');

INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, SERVICE_TYPE) 
VALUES(NEXT VALUE FOR SQALANG_ID,'계측기목록',
            (SELECT menu_id FROM TAMENU WHERE key_no='EQINFO'),
             (SELECT page_id FROM TAPAGE WHERE file_name='maEqToolMstrList'),'160','','N','EQTOOLSMSTR','MENU','Y','WEB');

INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, SERVICE_TYPE) 
VALUES(NEXT VALUE FOR SQALANG_ID,'토지,건물목록',
            (SELECT menu_id FROM TAMENU WHERE key_no='EQINFO'),
            (SELECT page_id FROM TAPAGE WHERE file_name='maEqBuildingMstrList'),'170','','N','EQBUILDMSTR','MENU','Y','WEB');
  
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, SERVICE_TYPE) 
VALUES(NEXT VALUE FOR SQALANG_ID,'Utility목록',
            (SELECT menu_id FROM TAMENU WHERE key_no='EQINFO'),
            (SELECT page_id FROM TAPAGE WHERE file_name='maEqUtilityMstrList'),'180','','N','EQUTILMSTR','MENU','Y','WEB');
           
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, SERVICE_TYPE) 
VALUES(NEXT VALUE FOR SQALANG_ID,'교육목록',
            (SELECT menu_id FROM TAMENU WHERE key_no='WOMNG'),
            (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultTrMstrList'),'140','','N','WOTRAINRESULT','MENU','Y','WEB');
            
/** 2017-09-26 노정현 추가 */
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, SERVICE_TYPE) 
VALUES(NEXT VALUE FOR SQALANG_ID,'자산부품목록',
            (SELECT menu_id FROM TAMENU WHERE key_no='EQINFO'),
            (SELECT page_id FROM TAPAGE WHERE file_name='maEqPartMstrList'),'190','','N','EQDEVICEMSTR','MENU','Y','WEB');
            

/** 2017-09-28 김영주 추가 */
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE,SERVICE_TYPE)
VALUES(NEXT VALUE FOR SQALANG_ID,'고장영향성 평가요청'
             ,(SELECT menu_id FROM TAMENU WHERE description = '작업관리')
             ,(SELECT page_id FROM TAPAGE WHERE file_name = 'workFmeaReqList')
             ,'311','','N','WORKFMEAREQ','MENU','Y','WEB');
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE,SERVICE_TYPE)
VALUES(NEXT VALUE FOR SQALANG_ID,'고장영향성 평가접수'
             ,(SELECT menu_id FROM TAMENU WHERE description = '작업관리')
             ,(SELECT page_id FROM TAPAGE WHERE file_name = 'workFmeaResList')
             ,'312','','N','WORKFMEARES','MENU','Y','WEB');
             
/** 2017-10-11 이근환 추가 */
INSERT INTO TAMENU(menu_id,description,p_menu_id,ord_no,is_system,key_no,key_type,is_use,service_type) 
VALUES(NEXT VALUE FOR SQAMENU_ID,'설비종합효율',(SELECT menu_id FROM TAMENU WHERE key_no=(SELECT key_no FROM TALANG WHERE key_name='외부Interface')),'950','N','OEE','MENU','Y','WEB');
INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,service_type) 
VALUES(NEXT VALUE FOR SQAMENU_ID,'설비효율(일별)',(SELECT menu_id FROM TAMENU WHERE key_no=(SELECT key_no FROM TALANG WHERE key_name='설비종합효율')),(SELECT page_id FROM TAPAGE WHERE file_name='daewoongKpiOeeDailyList'),'100','N','dailyOEE','MENU','Y','WEB');
INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,service_type) 
VALUES(NEXT VALUE FOR SQAMENU_ID,'설비효율(월별)',(SELECT menu_id FROM TAMENU WHERE key_no=(SELECT key_no FROM TALANG WHERE key_name='설비종합효율')),(SELECT page_id FROM TAPAGE WHERE file_name='daewoongKpiOeeMonthlyList'),'200','N','monthlyOEE','MENU','Y','WEB');

/**  2017-10-17 이근환 추가*/
INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,service_type)
VALUES(NEXT VALUE FOR SQAMENU_ID,'Report',(SELECT menu_id FROM TAMENU WHERE key_no='EQINFO'),'',400,'N','REPORT','MENU','Y','WEB');

INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,service_type)
VALUES(NEXT VALUE FOR SQAMENU_ID,'설비LCC분석 - 고장TOP(설비)',(SELECT menu_id FROM TAMENU WHERE p_menu_id=(SELECT menu_id FROM TAMENU WHERE key_no='EQINFO') AND key_no='REPORT'),(SELECT page_id FROM TAPAGE WHERE file_name='assetReportLccEquipList'),'0010','N','LCCByEqp','MENU','Y','WEB');

INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,service_type)
VALUES(NEXT VALUE FOR SQAMENU_ID,'설비LCC분석 - 고장TOP(위치)',(SELECT menu_id FROM TAMENU WHERE p_menu_id=(SELECT menu_id FROM TAMENU WHERE key_no='EQINFO') AND key_no='REPORT'),(SELECT page_id FROM TAPAGE WHERE file_name='assetReportLccLocList'),'0020','N','LCCByLoc','MENU','Y','WEB');

/** 2017-10-24 김영주 추가 */
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE,SERVICE_TYPE)
VALUES(NEXT VALUE FOR SQAMENU_ID,'일상점검목록'
             ,(SELECT menu_id FROM TAMENU WHERE description = '예방점검작업목록')
             ,(SELECT page_id FROM TAPAGE WHERE file_name = 'workPmiDInsList')
             ,'300','','N','TINSPECTION','MENU','Y','WEB');


/** 2017-10-26 김영주 추가 */
INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,service_type)
VALUES(NEXT VALUE FOR SQAMENU_ID,'Report',(SELECT menu_id FROM TAMENU WHERE key_no='EQUIPINVT'),'',300,'N','REPORT','MENU','Y','WEB');
INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,service_type)
VALUES(NEXT VALUE FOR SQAMENU_ID,'설비투자 - Report - 투자현황',(SELECT menu_id FROM TAMENU WHERE p_menu_id=(SELECT menu_id FROM TAMENU WHERE key_no='EQUIPINVT') AND key_no='REPORT'),(SELECT page_id FROM TAPAGE WHERE file_name='invtRptInvtPreConList'),'0010','N','InvtPreCon','MENU','Y','WEB');

/** 2017-10-30 김영주 추가 */
INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,service_type)
VALUES(NEXT VALUE FOR SQAMENU_ID,'부품관리 - Report - 부품초과보유현황',(SELECT menu_id FROM TAMENU WHERE p_menu_id=(SELECT menu_id FROM TAMENU WHERE key_no='PTMNG') AND key_no='REPORT'),(SELECT page_id FROM TAPAGE WHERE file_name='overOwnPartPreConList'),'900','N','OverOwnPartPreCon','MENU','Y','WEB');

/** 2017-10-31 이근환 추가 */
INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,service_type) 
VALUES(NEXT VALUE FOR SQAMENU_ID,'예방점검이행율(담당자)',(SELECT menu_id FROM TAMENU WHERE p_menu_id=(SELECT menu_id FROM TAMENU WHERE key_no='WOMNG') AND key_no='REPORT'),(SELECT page_id FROM TAPAGE WHERE file_name='workRptPminsAchList'),'850','N','PMINSACH','MENU','Y','WEB');

INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,service_type) 
VALUES(NEXT VALUE FOR SQAMENU_ID,'계획보전율(위치)',(SELECT menu_id FROM TAMENU WHERE p_menu_id=(SELECT menu_id FROM TAMENU WHERE key_no='WOMNG') AND key_no='REPORT'),(SELECT page_id FROM TAPAGE WHERE file_name='workRptPmRatioList'),'870','N','PMRATIO','MENU','Y','WEB');

/** 2017-11-01 김영주 추가 */
INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,service_type)
VALUES( NEXT VALUE FOR SQAMENU_ID,'부품관리 - Report - 재고지표',(SELECT menu_id FROM TAMENU WHERE p_menu_id=(SELECT menu_id FROM TAMENU WHERE key_no='PTMNG') AND key_no='REPORT'),(SELECT page_id FROM TAPAGE WHERE file_name='stockKpiList'),'910','N','STCKKPI','MENU','Y','WEB');

/** 2017-11-02 김영주 추가 */
INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,service_type)
VALUES(NEXT VALUE FOR SQAMENU_ID,'작업관리 - Report - 에너지사용현황',(SELECT menu_id FROM TAMENU WHERE p_menu_id=(SELECT menu_id FROM TAMENU WHERE key_no='WOMNG') AND key_no='REPORT'),(SELECT page_id FROM TAPAGE WHERE file_name='energyUsePreConList'),'900','N','energyUsePreCon','MENU','Y','WEB');

/** 2017-11-02 이근환 추가  */
INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,service_type)
VALUES(NEXT VALUE FOR SQAMENU_ID,'MTBF,MTTR(설비)',(SELECT menu_id FROM TAMENU WHERE p_menu_id=(SELECT menu_id FROM TAMENU WHERE key_no='EQINFO') AND key_no='REPORT'),(SELECT page_id FROM TAPAGE WHERE file_name='assetRptMtbfmttrEquipList'),'0030','N','EQMTBFMTTR','MENU','Y','WEB');

INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,service_type)
VALUES(NEXT VALUE FOR SQAMENU_ID,'MTBF,MTTR(위치)',(SELECT menu_id FROM TAMENU WHERE p_menu_id=(SELECT menu_id FROM TAMENU WHERE key_no='EQINFO') AND key_no='REPORT'),(SELECT page_id FROM TAPAGE WHERE file_name='assetRptMtbfmttrLocList'),'0040','N','LOCMTBFMTTR','MENU','Y','WEB');

INSERT INTO TAMENU(menu_id,description,p_menu_id,ord_no,is_system,key_no,key_type,is_use,service_type)
VALUES(NEXT VALUE FOR SQAMENU_ID,'교정작업일정',(SELECT menu_id FROM TAMENU WHERE key_no='WOMNG'),'550','N','CALIBCALENDAR','MENU','Y','WEB');

INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,service_type)
VALUES(NEXT VALUE FOR SQAMENU_ID,'교정작업일정(연간)',(SELECT menu_id FROM TAMENU WHERE p_menu_id=(SELECT menu_id FROM TAMENU WHERE key_no='WOMNG') AND key_no='CALIBCALENDAR'),(SELECT page_id FROM TAPAGE WHERE file_name='workCalPmcYearList'),'0100','N','YEARCALIBSCHED','MENU','Y','WEB');

INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,service_type)
VALUES(NEXT VALUE FOR SQAMENU_ID,'교정작업일정(월간)',(SELECT menu_id FROM TAMENU WHERE p_menu_id=(SELECT menu_id FROM TAMENU WHERE key_no='WOMNG') AND key_no='CALIBCALENDAR'),(SELECT page_id FROM TAPAGE WHERE file_name='workCalPmcMonthList'),'0200','N','MONTHCALIBSCHED','MENU','Y','WEB');

INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,service_type)
VALUES(NEXT VALUE FOR SQAMENU_ID,'교정작업일정(기간)',(SELECT menu_id FROM TAMENU WHERE p_menu_id=(SELECT menu_id FROM TAMENU WHERE key_no='WOMNG') AND key_no='CALIBCALENDAR'),(SELECT page_id FROM TAPAGE WHERE file_name='workCalPmcPeriodList'),'0300','N','CALIBSCHED','MENU','Y','WEB');

/** 2017-11-02 김영주 추가 */
INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,service_type)
VALUES(NEXT VALUE FOR SQAMENU_ID,'Report',(SELECT menu_id FROM TAMENU WHERE key_no='NORMALMNG'),'',900,'N','REPORT','MENU','Y','WEB');
INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,service_type)
VALUES(NEXT VALUE FOR SQAMENU_ID,'조직관리 - Report - MTTR(담당자)',(SELECT menu_id FROM TAMENU WHERE p_menu_id=(SELECT menu_id FROM TAMENU WHERE key_no='NORMALMNG') AND key_no='REPORT'),(SELECT page_id FROM TAPAGE WHERE file_name='orgRptEmpMttrList'),'0010','N','EmpMTTR','MENU','Y','WEB');

/** 2017-11-03 김영주 추가 */
INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,service_type)
VALUES( NEXT VALUE FOR SQAMENU_ID,'작업관리 - Report - 원단위분석',(SELECT menu_id FROM TAMENU WHERE p_menu_id=(SELECT menu_id FROM TAMENU WHERE key_no='WOMNG') AND key_no='REPORT'),(SELECT page_id FROM TAPAGE WHERE file_name='basicUnitAnalysisList'),'910','N','basicUnitAnalysis','MENU','Y','WEB');


 /** 2017-11-06 김정우 */
 INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, SERVICE_TYPE)
 VALUES(NEXT value FOR sqaMENU_ID,'설치/테스트 작업목록',
             (SELECT x.menu_id FROM TAMENU x WHERE (SELECT A.key_name FROM TALANG A WHERE A.key_no = x.key_no AND A.key_type='MENU' AND A.lang='ko') = '작업관리' AND page_id IS NULL AND service_type='WEB'),
             (SELECT page_id FROM tapage WHERE file_name = 'workListTiMstrList'),'145','','N','WOTIRESULT','MENU','Y', 'WEB');  

/**2017-11-27 김정우 */
             
INSERT INTO tamenu (MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID, ORD_NO, PARAM, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, MENU_NO, P_MENU_NO, SERVICE_TYPE, REMARK)
VALUES(NEXT value FOR sqamenu_id,'다운로드',0,NULL,'010','','N','DOWNLOADMNG','MENU','Y','DOWNLOADMNG','','ANDROID','');
INSERT INTO tamenu (MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID, ORD_NO, PARAM, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, MENU_NO, P_MENU_NO, SERVICE_TYPE, REMARK)
VALUES(NEXT value FOR sqamenu_id,'요청관리',0,NULL,'020','','N','WOREQMNG','MENU','Y','WOREQMNG','','ANDROID','');
INSERT INTO tamenu (MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID, ORD_NO, PARAM, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, MENU_NO, P_MENU_NO, SERVICE_TYPE, REMARK)
VALUES(NEXT value FOR sqamenu_id,'작업관리',0,NULL,'030','','N','WOMNG','MENU','Y','WOMNG','','ANDROID','');
INSERT INTO tamenu (MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID, ORD_NO, PARAM, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, MENU_NO, P_MENU_NO, SERVICE_TYPE, REMARK)
VALUES(NEXT value FOR sqamenu_id,'부품관리',0,NULL,'040','','N','PTMNG','MENU','Y','PTMNG','','ANDROID','');
INSERT INTO tamenu (MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID, ORD_NO, PARAM, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, MENU_NO, P_MENU_NO, SERVICE_TYPE, REMARK)
VALUES(NEXT value FOR sqamenu_id,'자산관리',0,NULL,'050','','N','ASSETMNG','MENU','Y','ASSETMNG','','ANDROID','');
INSERT INTO tamenu (MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID, ORD_NO, PARAM, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, MENU_NO, P_MENU_NO, SERVICE_TYPE, REMARK)
VALUES(NEXT value FOR sqamenu_id,'업로드',0,NULL,'060','','N','UPLOADMNG','MENU','Y','UPLOADMNG','','ANDROID','');


INSERT INTO tamenu (MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID, ORD_NO, PARAM, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, MENU_NO, P_MENU_NO, SERVICE_TYPE, REMARK)
VALUES(NEXT value FOR sqamenu_id,'계획 다운로드',(SELECT menu_id FROM tamenu WHERE service_type='ANDROID' AND menu_no='DOWNLOADMNG'),NULL,'010','','N','PLANDOWNLOAD','MENU','Y','PLANDOWNLOAD'
                ,(SELECT menu_no FROM tamenu WHERE service_type='ANDROID' AND menu_no='DOWNLOADMNG'),'ANDROID','');
INSERT INTO tamenu (MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID, ORD_NO, PARAM, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, MENU_NO, P_MENU_NO, SERVICE_TYPE, REMARK)
VALUES(NEXT value FOR sqamenu_id,'조회조건 설정',(SELECT menu_id FROM tamenu WHERE service_type='ANDROID' AND menu_no='DOWNLOADMNG'),NULL,'020','','N','SEARCHOPTION','MENU','Y','SEARCHOPTION'
                ,(SELECT menu_no FROM tamenu WHERE service_type='ANDROID' AND menu_no='DOWNLOADMNG'),'ANDROID','');
                
INSERT INTO tamenu (MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID, ORD_NO, PARAM, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, MENU_NO, P_MENU_NO, SERVICE_TYPE, REMARK)
VALUES(NEXT value FOR sqamenu_id,'작업요청',(SELECT menu_id FROM tamenu WHERE service_type='ANDROID' AND menu_no='WOREQMNG'),NULL,'010','','N','WOREQLIST','MENU','Y','WOREQLIST'
                ,(SELECT menu_no FROM tamenu WHERE service_type='ANDROID' AND menu_no='WOREQMNG'),'ANDROID','');
INSERT INTO tamenu (MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID, ORD_NO, PARAM, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, MENU_NO, P_MENU_NO, SERVICE_TYPE, REMARK)
VALUES(NEXT value FOR sqamenu_id,'요청접수',(SELECT menu_id FROM tamenu WHERE service_type='ANDROID' AND menu_no='WOREQMNG'),NULL,'020','','N','WOREQRECLIST','MENU','Y','WOREQRECLIST'
                ,(SELECT menu_no FROM tamenu WHERE service_type='ANDROID' AND menu_no='WOREQMNG'),'ANDROID','');
                
INSERT INTO tamenu (MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID, ORD_NO, PARAM, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, MENU_NO, P_MENU_NO, SERVICE_TYPE, REMARK)
VALUES(NEXT value FOR sqamenu_id,'분해점검',(SELECT menu_id FROM tamenu WHERE service_type='ANDROID' AND menu_no='WOMNG'),NULL,'010','','N','INSPECTION','MENU','Y','INSPECTION'
                ,(SELECT menu_no FROM tamenu WHERE service_type='ANDROID' AND menu_no='WOMNG'),'ANDROID','');
INSERT INTO tamenu (MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID, ORD_NO, PARAM, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, MENU_NO, P_MENU_NO, SERVICE_TYPE, REMARK)
VALUES(NEXT value FOR sqamenu_id,'정기점검',(SELECT menu_id FROM tamenu WHERE service_type='ANDROID' AND menu_no='WOMNG'),NULL,'020','','N','PMIROUTINE','MENU','Y','PMIROUTINE'
                ,(SELECT menu_no FROM tamenu WHERE service_type='ANDROID' AND menu_no='WOMNG'),'ANDROID','');
INSERT INTO tamenu (MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID, ORD_NO, PARAM, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, MENU_NO, P_MENU_NO, SERVICE_TYPE, REMARK)
VALUES(NEXT value FOR sqamenu_id,'일상점검',(SELECT menu_id FROM tamenu WHERE service_type='ANDROID' AND menu_no='WOMNG'),NULL,'030','','N','PMIDAY','MENU','Y','PMIDAY'
                ,(SELECT menu_no FROM tamenu WHERE service_type='ANDROID' AND menu_no='WOMNG'),'ANDROID','');
INSERT INTO tamenu (MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID, ORD_NO, PARAM, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, MENU_NO, P_MENU_NO, SERVICE_TYPE, REMARK)
VALUES(NEXT value FOR sqamenu_id,'순회점검',(SELECT menu_id FROM tamenu WHERE service_type='ANDROID' AND menu_no='WOMNG'),NULL,'040','','N','PMIPATROL','MENU','Y','PMIPATROL'
                ,(SELECT menu_no FROM tamenu WHERE service_type='ANDROID' AND menu_no='WOMNG'),'ANDROID','');
INSERT INTO tamenu (MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID, ORD_NO, PARAM, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, MENU_NO, P_MENU_NO, SERVICE_TYPE, REMARK)
VALUES(NEXT value FOR sqamenu_id,'계획작업',(SELECT menu_id FROM tamenu WHERE service_type='ANDROID' AND menu_no='WOMNG'),NULL,'050','','N','PMWORK','MENU','Y','PMWORK'
                ,(SELECT menu_no FROM tamenu WHERE service_type='ANDROID' AND menu_no='WOMNG'),'ANDROID','');
INSERT INTO tamenu (MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID, ORD_NO, PARAM, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, MENU_NO, P_MENU_NO, SERVICE_TYPE, REMARK)
VALUES(NEXT value FOR sqamenu_id,'교정',(SELECT menu_id FROM tamenu WHERE service_type='ANDROID' AND menu_no='WOMNG'),NULL,'060','','N','CALIBRATION','MENU','Y','CALIBRATION'
                ,(SELECT menu_no FROM tamenu WHERE service_type='ANDROID' AND menu_no='WOMNG'),'ANDROID','');
INSERT INTO tamenu (MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID, ORD_NO, PARAM, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, MENU_NO, P_MENU_NO, SERVICE_TYPE, REMARK)
VALUES(NEXT value FOR sqamenu_id,'돌발작업',(SELECT menu_id FROM tamenu WHERE service_type='ANDROID' AND menu_no='WOMNG'),NULL,'070','','N','UNPLANWORK','MENU','Y','UNPLANWORK'
                ,(SELECT menu_no FROM tamenu WHERE service_type='ANDROID' AND menu_no='WOMNG'),'ANDROID','');
                

INSERT INTO tamenu (MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID, ORD_NO, PARAM, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, MENU_NO, P_MENU_NO, SERVICE_TYPE, REMARK)
VALUES(NEXT value FOR sqamenu_id,'부품출고',(SELECT menu_id FROM tamenu WHERE service_type='ANDROID' AND menu_no='PTMNG'),NULL,'010','','N','PTISS','MENU','Y','PTISS'
                ,(SELECT menu_no FROM tamenu WHERE service_type='ANDROID' AND menu_no='PTMNG'),'ANDROID','');
INSERT INTO tamenu (MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID, ORD_NO, PARAM, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, MENU_NO, P_MENU_NO, SERVICE_TYPE, REMARK)
VALUES(NEXT value FOR sqamenu_id,'현재고',(SELECT menu_id FROM tamenu WHERE service_type='ANDROID' AND menu_no='PTMNG'),NULL,'020','','N','CURRSTOCK','MENU','Y','CURRSTOCK'
                ,(SELECT menu_no FROM tamenu WHERE service_type='ANDROID' AND menu_no='PTMNG'),'ANDROID','');
INSERT INTO tamenu (MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID, ORD_NO, PARAM, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, MENU_NO, P_MENU_NO, SERVICE_TYPE, REMARK)
VALUES(NEXT value FOR sqamenu_id,'부품실사',(SELECT menu_id FROM tamenu WHERE service_type='ANDROID' AND menu_no='PTMNG'),NULL,'030','','N','PTADJ','MENU','Y','PTADJ'
                ,(SELECT menu_no FROM tamenu WHERE service_type='ANDROID' AND menu_no='PTMNG'),'ANDROID','');
                
INSERT INTO tamenu (MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID, ORD_NO, PARAM, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, MENU_NO, P_MENU_NO, SERVICE_TYPE, REMARK)
VALUES(NEXT value FOR sqamenu_id,'설비자산',(SELECT menu_id FROM tamenu WHERE service_type='ANDROID' AND menu_no='ASSETMNG'),NULL,'010','','N','ASSETS','MENU','Y','ASSETS'
                ,(SELECT menu_no FROM tamenu WHERE service_type='ANDROID' AND menu_no='ASSETMNG'),'ANDROID','');
                
INSERT INTO tamenu (MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID, ORD_NO, PARAM, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, MENU_NO, P_MENU_NO, SERVICE_TYPE, REMARK)
VALUES(NEXT value FOR sqamenu_id,'업로드',(SELECT menu_id FROM tamenu WHERE service_type='ANDROID' AND menu_no='UPLOADMNG'),NULL,'010','','N','UPLOAD','MENU','Y','UPLOAD'
                ,(SELECT menu_no FROM tamenu WHERE service_type='ANDROID' AND menu_no='UPLOADMNG'),'ANDROID','');
                
/** 2017-12-03 이근환 추가 */
INSERT INTO TAMENU(menu_id,description,p_menu_id,ord_no,is_system,key_no,key_type,is_use,menu_no,service_type) 
SELECT NEXT value FOR sqamenu_id,'설비자산설정',(SELECT menu_id FROM TAMENU WHERE key_no='SYSMNG'),'400','N','ASSETSETTING','MENU','Y','111','WEB'
WHERE (SELECT COUNT(1) FROM TAMENU WHERE key_no='ASSETSETTING') = 0;
INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,service_type)
VALUES(NEXT value FOR sqamenu_id,'생산품목',(SELECT menu_id FROM TAMENU WHERE key_no='ASSETSETTING' AND key_type='MENU'),(SELECT page_id FROM TAPAGE WHERE file_name='assetStdProductList'),'400','N','PRODUCT','MENU','Y','WEB');
INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,service_type)
VALUES(NEXT value FOR sqamenu_id,'Cost Center',(SELECT menu_id FROM TAMENU WHERE key_no='ASSETSETTING' AND key_type='MENU'),(SELECT page_id FROM TAPAGE WHERE file_name='assetStdCtctrList'),'500','N','CTCTR','MENU','Y','WEB');
INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,service_type)
VALUES(NEXT value FOR sqamenu_id,'회계자산',(SELECT menu_id FROM TAMENU WHERE key_no='ASSETSETTING' AND key_type='MENU'),(SELECT page_id FROM TAPAGE WHERE file_name='assetStdAssetList'),'600','N','ACCASSET','MENU','Y','WEB');

/** 2017-12-03 김영주 추가 */
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, SERVICE_TYPE)
 VALUES( NEXT VALUE FOR SQAMENU_ID,'파트체인지점검일정(월간)',
             (SELECT x.menu_id FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE key_no = 'WORKPMISCHED')),
             (SELECT page_id FROM TAPAGE WHERE file_name = 'workCalPmCInsMonthList')
             ,'600','','N','WORKCALPMCINSMONTH','MENU','Y', 'WEB');
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE,SERVICE_TYPE)
VALUES( NEXT VALUE FOR SQAMENU_ID,'파트체인지점검계획'
             ,(SELECT menu_id FROM TAMENU WHERE description = '예방점검작업목록')
             ,(SELECT page_id FROM TAPAGE WHERE file_name = 'workListCinsPlanMstrList')
             ,'500','','N','partChangeInsPlanList','MENU','Y','WEB');

/** 2017-12-06 이근환 추가 */
INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,service_type) 
VALUES(NEXT value FOR sqamenu_id,'파트체인지점검목록',(SELECT menu_id FROM TAMENU WHERE key_no='INSPECTION' AND key_type='MENU' AND service_type='WEB'),(SELECT page_id FROM TAPAGE WHERE file_name='workPmiCInsList'),'600','N','CINSPECTION','MENU','Y','WEB');

/** 2017-12-11 김영주 추가 */
INSERT INTO TAEHMENU (ehmenu_id, ehmenu_no, description, p_ehmenu_id, p_ehmenu_no, page_id, file_name, ord_no, is_use,is_basic, key_no, key_type, param)
VALUES( NEXT VALUE FOR sqaehmenu_id, CONVERT(NVARCHAR,(SELECT CURRENT_VALUE FROM SYS.SEQUENCES WHERE name='SQAEHMENU_ID')), '작업요청 접근터미널',(SELECT ehmenu_id FROM TAEHMENU WHERE ehmenu_no = 'SITE'),'SITE',  (SELECT page_id FROM TAPAGE WHERE file_name ='consultCompTerminalExList'), 'consultCompTerminalExList', '1450', 'Y', 'Y', 'TERMINALEX','MENU', '' );

/** 2017-12-11 이근환 추가 */
DELETE FROM TAMENU WHERE key_no=(SELECT key_no FROM TALANG WHERE key_name='요일별가동 기본시간설정');

INSERT INTO TAEHMENU(ehmenu_id,ehmenu_no,description,p_ehmenu_id,p_ehmenu_no,page_id,file_name,ord_no,is_basic,is_use,key_type,key_no)
VALUES(NEXT VALUE FOR sqaehmenu_id,NEXT VALUE FOR sqaehmenu_id,'가동달력',(SELECT ehmenu_id FROM TAEHMENU WHERE key_no=(SELECT key_no FROM TALANG WHERE key_name='회사설정') AND p_ehmenu_id=0),(SELECT ehmenu_no FROM TAEHMENU WHERE key_no=(SELECT key_no FROM TALANG WHERE key_name='회사설정') AND p_ehmenu_id=0),(SELECT page_id FROM TAPAGE WHERE file_name='maLineTimeList'),'maLineTimeList','1350','Y','Y','MENU','LNWRKLIST');

/** 2017-12-12 이근환 추가 */
UPDATE TAMENU SET key_no='ENERGEUSAGEMON'
WHERE page_id=(SELECT page_id FROM TAPAGE WHERE file_name='energyUsePreConMonthList');

/** 2017-12-13 이근환 추가 */
INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,service_type)
VALUES(NEXT VALUE FOR sqamenu_id,'작업관리 - Report - 에너지사용현황(년)',(SELECT menu_id FROM TAMENU WHERE p_menu_id=(SELECT menu_id FROM TAMENU WHERE p_menu_id=0 AND key_no='WOMNG' AND service_type='WEB') AND key_no='REPORT'),(SELECT page_id FROM TAPAGE WHERE file_name='energyUsePreConYearList'),'901','N','ENERGEUSAGEYEAR','MENU','Y','WEB');

/** 2017-12-18 김정우 */
INSERT INTO tamenu (MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID, ORD_NO, PARAM, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, MENU_NO, P_MENU_NO, SERVICE_TYPE, REMARK)
VALUES(next value for sqamenu_id,'Part Change 점검',(SELECT menu_id FROM tamenu WHERE service_type='ANDROID' AND menu_no='WOMNG'),NULL,'050','','N','PMIPART','MENU','Y','PMIPART'
                ,(SELECT menu_no FROM tamenu WHERE service_type='ANDROID' AND menu_no='WOMNG'),'ANDROID','');
                
/** 2017-12-20 김영주 추가 */
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,key_no,key_type,is_use,is_system,service_type) 
 VALUES( NEXT VALUE FOR SQAMENU_ID,'작업관리 - Report - 예방점검수치추이',(SELECT AA.MENU_ID FROM TAMENU AA WHERE AA.DESCRIPTION='Report' AND aa.p_menu_id = (SELECT b.menU_id FROM TAMENU b WHERE b.description = '작업관리' AND b.service_type='WEB')),(SELECT AA.PAGE_ID FROM TAPAGE AA WHERE AA.FILE_NAME='maPmTrendList'),'650','','PMTREND','MENU','Y','N','WEB'); 
    
/** 2017-12-20 이근환 추가 */
INSERT INTO TAEHMENU(ehmenu_id,ehmenu_no,description,p_ehmenu_id,p_ehmenu_no,page_id,file_name,ord_no,is_basic,is_use,key_type,key_no)
VALUES(NEXT VALUE FOR sqaehmenu_id,NEXT VALUE FOR sqaehmenu_id,'화면GridCol',(SELECT ehmenu_id FROM TAEHMENU WHERE p_ehmenu_id=0 AND key_type='MENU' AND key_no='SYSTEM_SETTING'),(SELECT ehmenu_no FROM TAEHMENU WHERE p_ehmenu_id=0 AND key_type='MENU' AND key_no='SYSTEM_SETTING'),(SELECT page_id FROM TAPAGE WHERE file_name='maPgGrdColMngList'),'maPgGrdColMngList','99550','Y','Y','MENU','PGGRIDCOL');


/** 2017-12-22 김영주 추가*/
INSERT INTO TAEHMENU(ehmenu_id,ehmenu_no,description,p_ehmenu_id,p_ehmenu_no,page_id,file_name,ord_no,is_basic,is_use,key_type,key_no)
VALUES( NEXT VALUE FOR sqaehmenu_id,NEXT VALUE FOR sqaehmenu_id,'화면입력항목',(SELECT ehmenu_id FROM TAEHMENU WHERE p_ehmenu_id=0 AND key_type='MENU' AND key_no='SYSTEM_SETTING'),(SELECT ehmenu_no FROM TAEHMENU WHERE p_ehmenu_id=0 AND key_type='MENU' AND key_no='SYSTEM_SETTING'),(SELECT page_id FROM TAPAGE WHERE file_name='maPgFieldMngList'),'maPgFieldMngList','99560','Y','Y','MENU','PGFIELD');

INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE,SERVICE_TYPE) 
VALUES( NEXT VALUE FOR SQAMENU_ID,'작업계획목록(목록)',(SELECT x.menu_id FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE x.key_no = 'WOMNG' AND x.service_type='WEB' )),(SELECT page_id FROM TAPAGE WHERE file_name = 'woPlanList'),'156','','N','WOPLAN','MENU','Y','WEB'); 


/** 2017-12-27 김정우 */
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, SERVICE_TYPE,MENU_NO)
 VALUES(NEXT value FOR sqaMENU_ID,'예방점검계획승인',
             (SELECT x.menu_id FROM TAMENU x WHERE description = '작업일정'),
             (SELECT page_id FROM TAPAGE WHERE file_name = 'workCalPmInsApprList')
             ,'992','','N','INSAPPR','MENU','Y', 'WEB','INSAPPR');
             
/** 2017-12-27 이근환 */
INSERT INTO TAEHMENU(ehmenu_id,ehmenu_no,description,p_ehmenu_id,p_ehmenu_no,page_id,file_name,ord_no,is_basic,is_use,key_type,key_no)
VALUES (NEXT VALUE FOR sqaehmenu_id,NEXT VALUE FOR sqaehmenu_id,'도움말',(SELECT ehmenu_id FROM TAEHMENU WHERE p_ehmenu_id=0 AND key_no='SYSTEM_SETTING'),(SELECT ehmenu_no FROM TAEHMENU WHERE p_ehmenu_id=0 AND key_no='SYSTEM_SETTING'),(SELECT page_id FROM TAPAGE WHERE file_name='consultProgramOnlinehelpList'),'consultProgramOnlinehelpList','99650','Y','Y','MENU','ONLINEHELP');

/** 2018-01-04 이근환 */
INSERT INTO TAEHMENU(ehmenu_id,ehmenu_no,description,p_ehmenu_id,p_ehmenu_no,page_id,file_name,ord_no,is_basic,is_use,key_type,key_no)
VALUES(NEXT VALUE FOR sqaehmenu_id,NEXT VALUE FOR sqaehmenu_id,'Screen Trace',(SELECT ehmenu_id FROM TAEHMENU WHERE p_ehmenu_id=0 AND key_no='COMPSET'),(SELECT ehmenu_no FROM TAEHMENU WHERE p_ehmenu_id=0 AND key_no='COMPSET'),(select page_id from tapage where file_name='consultCompTracelogList'),'consultCompTracelogList','1470','Y','Y','MENU','SCREENTRACE');

/** 2018-01-04 김영주 */
INSERT INTO TAEHMENU(ehMENU_ID, ehmenu_no, DESCRIPTION, P_ehMENU_ID, PAGE_ID,ORD_NO,PARAM, KEY_NO, KEY_TYPE, IS_USE, is_basic,file_name,p_ehmenu_no) 
VALUES( NEXT VALUE FOR SQAEHMENU_ID,NEXT VALUE FOR SQAEHMENU_ID,'업로드데이타설정(목록)',(SELECT x.ehmenu_id FROM TAEHMENU x WHERE ehmenu_id = (SELECT x.ehmenu_id FROM TAEHMENU x WHERE ehmenu_no = 'CONSULT_SETTING')),(SELECT page_id FROM TAPAGE WHERE file_name = 'consultProgramUploadDataList'),'99960','','UPLOADDATA','MENU','Y','Y','consultProgramUploadDataList','0'); 

/** 2018-01-05 이근환 */
INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,service_type)
VALUES (NEXT VALUE FOR sqamenu_id,'Excel 데이타 업로드',(SELECT menu_id FROM TAMENU WHERE p_menu_id=(SELECT menu_id FROM TAMENU WHERE p_menu_id=0 AND service_type='WEB' AND key_no='SYSMNG') AND key_no='SYSCONFIGSETTING'),(SELECT page_id FROM TAPAGE WHERE file_name='mgrExldataList'),'200','N','EXCELUPLOAD','MENU','Y','WEB');

/** 2018-01-10 김영주 */
UPDATE TAMENU SET key_no = UPPER(key_no) WHERE UPPER(key_no) = 'INVTPRECON';

/** 2018-01-29 이근환 */
INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,menu_no,p_menu_no,service_type,file_name)
VALUES(next value for sqamenu_id,'출고요청',(SELECT menu_id FROM TAMENU WHERE service_type='WEB' AND p_menu_id=0 AND key_no='PTMNG'),(SELECT page_id FROM TAPAGE WHERE file_name='partIssEmgReqList'),'4000','N','PTISSREQ','MENU','Y','PTISSREQ',(SELECT menu_no FROM TAMENU WHERE service_type='WEB' AND p_menu_id=0 AND key_no='PTMNG'),'WEB','partIssEmgReqList');

/** 2018-01-31 김영주 추가 */
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE,menu_no,p_menu_no,SERVICE_TYPE, file_name) 
VALUES( NEXT VALUE FOR SQAMENU_ID,'정비지표 (목록)',(SELECT x.menu_id FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE x.key_no = 'REPORT')),(SELECT page_id FROM TAPAGE WHERE file_name = 'workRptMaintRateList'),'910','N','MAINTRATE','MENU','Y','MAINTRATE','WORKREPORT','WEB','workRptMaintRateList'); 

/** 2018-02-02 김영주 추가 */
INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,service_type,menu_no)
VALUES( NEXT VALUE FOR SQAMENU_ID,'Report',(SELECT menu_id FROM TAMENU WHERE key_no='WOREQ'),'',990,'N','REPORT','MENU','Y','WEB','WOREQRPT');
INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,service_type, menu_no)
VALUES( NEXT VALUE FOR SQAMENU_ID,'요청관리 - Report - 요청접수율(처리자)',(SELECT menu_id FROM TAMENU WHERE p_menu_id=(SELECT menu_id FROM TAMENU WHERE key_no='WOREQ') AND menu_no='WOREQRPT'),(SELECT page_id FROM TAPAGE WHERE file_name='reqRptWoReqRateList'),'0010','N','WOREQRATE','MENU','Y','WEB','WOREQRATE');

/** 2018-02-20 양소영 추가 */
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID, ORD_NO, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, MENU_NO, P_MENU_NO, SERVICE_TYPE,  FILE_NAME)
VALUES(NEXT value for SQAMENU_ID, '결재이력', (SELECT MENU_ID FROM tamenu WHERE key_no='PERSONAL'), (SELECT PAGE_ID FROM tapage where file_name='persApprHistList'), '750', 'N', 'APPRHIST', 'MENU', 'Y', 'APPRHIST',  (SELECT MENU_NO FROM tamenu WHERE key_no='PERSONAL'), 'WEB', 'persApprHistList' );

/** 2018-02-21 김영주 추가 */
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, SERVICE_TYPE,MENU_NO)
 VALUES(NEXT value FOR sqaMENU_ID,'설비부품현황',
             (SELECT x.menu_id FROM TAMENU x WHERE x.menu_no = 'ASSETREPORT'),
             (SELECT page_id FROM TAPAGE WHERE file_name = 'assetRptEqPartPreConList')
             ,'0050','','N','EQPARTPRECON','MENU','Y', 'WEB','EQPARTPRECON');

/** 2018-02-27 김영주 추가 */
INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,menu_no,p_menu_no,service_type,file_name)
VALUES( NEXT VALUE FOR sqamenu_id,'IT장비목록',(SELECT menu_id FROM TAMENU WHERE p_menu_id=0 AND service_type='WEB' AND key_no='ASSETS'),(SELECT page_id FROM TAPAGE WHERE file_name='assetListITList'),'193','N','EQIT','MENU','Y','EQIT',(SELECT menu_no FROM TAMENU WHERE p_menu_id=0 AND service_type='WEB' AND key_no='ASSETS'),'WEB','assetListITList');

/**  2018-03-07 매일유업 반영 */

/** 2018-03-09 김영주 */                                       
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, SERVICE_TYPE,MENU_NO)
 VALUES( NEXT VALUE FOR sqaMENU_ID,'공지사항',
             (SELECT x.menu_id FROM TAMENU x WHERE x.menu_no = 'RESOURCES'),
             (SELECT page_id FROM TAPAGE WHERE file_name = 'docNoticeList')
             ,'0300','','N','NOTICE','MENU','Y', 'WEB','NOTICE');
             
/**  2018-03-13 매일유업 반영 */
             
/** 2018-03-15 김영주 */         
UPDATE TAMENU SET 
    description = '공지등록'
  , key_no = 'ADDNOTICE'
  , menu_no = 'ADDNOTICE' 
WHERE page_id = (SELECT page_id FROM TAPAGE WHERE file_name = 'docNoticeList');

INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, SERVICE_TYPE,MENU_NO)
 VALUES( NEXT VALUE FOR sqaMENU_ID,'공지사항',
             (SELECT x.menu_id FROM TAMENU x WHERE x.menu_no = 'RESOURCES'),
             (SELECT page_id FROM TAPAGE WHERE file_name = 'docNoticeCheckList')
             ,'0310','','N','NOTICE','MENU','Y', 'WEB','NOTICE');

/** 2018-03-19 양소영 */
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, MENU_NO, P_MENU_NO, SERVICE_TYPE,  FILE_NAME) VALUES( NEXT VALUE FOR SQAMENU_ID,'부품창고(목록)',(SELECT x.MENU_ID FROM TAMENU x WHERE MENU_ID = (SELECT x.menu_id FROM TAMENU x WHERE key_no = 'SYSMNG')),(SELECT page_id FROM TAPAGE WHERE file_name = 'mgrPtWhList'),'560','','N','PTWAREHOUSE','MENU','Y', 'PTWAREHOUSE', (SELECT x.menu_no FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE key_no = 'SYSMNG')), 'WEB', 'mgrPtWhList'); 

/** 2018-03-20 김정우 */
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, SERVICE_TYPE,MENU_NO)
 VALUES( next value for sqaMENU_ID,'대시보드',
             (SELECT x.menu_id FROM TAMENU x WHERE x.menu_no = 'SYSMNG'),
             (SELECT page_id FROM TAPAGE WHERE file_name = 'dashboardParent')
             ,'910','','N','DASHBOARD','MENU','Y', 'WEB','DASHBOARD');
             
/**  2018-03-20 매일유업 반영 */
             
/** 2018-03-21 김영주 */
INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,service_type,menu_no) 
VALUES( NEXT VALUE FOR SQAMENU_ID,'예방점검이행율(부서)'
,(SELECT x.menu_id FROM TAMENU x WHERE x.menu_no = 'WORKREPORT')
,(SELECT page_id FROM TAPAGE WHERE file_name='workRptPminsDeptAchList')
,'855','N','PMINSDEPTACH','MENU','Y','WEB','PMINSDEPTACH');

/** 2018-03-23 이근환 */
UPDATE tamenu set description='기타자료실', key_no='ETCDOC', menu_no='ETCDOC'
WHERE key_no='USERMANUAL' AND service_type='WEB';

INSERT INTO tamenu(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,menu_no,p_menu_no,service_type,file_name)
VALUES(NEXT VALUE FOR sqamenu_id,'사용자매뉴얼',(SELECT menu_id FROM tamenu WHERE key_no='RESOURCES' AND service_type='WEB'),(SELECT page_id FROM tapage WHERE file_name='docManualList'),'210','N','USERMANUAL','MENU','Y','USERMANUAL',(SELECT menu_no FROM tamenu WHERE key_no='RESOURCES' AND service_type='WEB'),'WEB','docManualList');

/**  2018-03-28 매일유업 반영 */

/** 2018-03-30 김영주 */
INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,service_type,menu_no) 
VALUES( NEXT VALUE FOR  SQAMENU_ID ,'조직별사용분석'
,(SELECT x.menu_id FROM TAMENU x WHERE x.menu_no = 'PARTREPORT')
,(SELECT page_id FROM TAPAGE WHERE file_name='partRptOrgPtUseHistList')
,'920','N','ORGPTUSEHIST','MENU','Y','WEB','ORGPTUSEHIST');

/** 2018-04-03 이지수 */
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, menu_no, p_menu_no, service_type, file_name)
VALUES(NEXT VALUE FOR SQAMENU_ID,'설비이력(과거)', (SELECT menu_id FROM tamenu WHERE key_no = 'ASSETREPORT' and service_type ='WEB'),(SELECT page_id FROM tapage WHERE file_name = 'assetRptWorkHistList'),'0045','N','EQHIST','MENU','Y','EQHIST', (SELECT menu_no FROM tamenu WHERE key_no = 'ASSETREPORT' and service_type ='WEB'), 'WEB', 'assetRptWorkHistList'); 
 
/**  2018-04-04 매일유업 반영 */

/** 2018-04-12 양소영 */
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, MENU_NO, P_MENU_NO, SERVICE_TYPE,  FILE_NAME) VALUES( NEXT VALUE FOR SQAMENU_ID,'예방점검이행율(조직)',(SELECT x.MENU_ID FROM TAMENU x WHERE MENU_ID = (SELECT x.menu_id FROM TAMENU x WHERE x.menu_no = 'WORKREPORT')),(SELECT page_id FROM TAPAGE WHERE file_name = 'workRptPminsOrgAchList'),'856','','N','PMINSORGACH','MENU','Y', 'PMINSORGACH', (SELECT x.menu_no FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE x.menu_no = 'WORKREPORT')), 'WEB', 'workRptPminsOrgAchList'); 

/** 2018-04-12 김영주 */
INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,service_type,menu_no) 
VALUES( NEXT VALUE FOR  SQAMENU_ID ,'조직별고장분석'
,(SELECT x.menu_id FROM TAMENU x WHERE x.menu_no = 'WORKREPORT')
,(SELECT page_id FROM TAPAGE WHERE file_name='workRptOrgBdList')
,'920','N','ORGBDLIST','MENU','Y','WEB','ORGBDLIST');

/** 2018-04-16 김영주 */
INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,service_type,menu_no) 
VALUES( NEXT VALUE FOR  SQAMENU_ID ,'조직별MTTR,MTBF'
,(SELECT x.menu_id FROM TAMENU x WHERE x.menu_no = 'WORKREPORT')
,(SELECT page_id FROM TAPAGE WHERE file_name='workRptOrgMtbfmttrList')
,'930','N','ORGMTBFMTTR','MENU','Y','WEB','ORGMTBFMTTR');

/** 2018-04-17 양소영 */
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, MENU_NO, P_MENU_NO, SERVICE_TYPE,  FILE_NAME) VALUES( NEXT VALUE FOR SQAMENU_ID,'부품사용분석',(SELECT x.MENU_ID FROM TAMENU x WHERE MENU_ID = (SELECT x.menu_id FROM TAMENU x WHERE x.menu_no = 'PARTREPORT')),(SELECT page_id FROM TAPAGE WHERE file_name = 'partRptPtUseHistList'),'915','','N','PTUSEHIST','MENU','Y', 'PTUSEHIST', (SELECT x.menu_no FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE x.menu_no = 'PARTREPORT')), 'WEB', 'partRptPtUseHistList');

/**  2018-04-18 매일유업 반영 */
/**  2018-04-25 매일유업 반영 */
/**  2018-05-02 매일유업 반영 */
/**  2018-05-09 매일유업 반영 */

/** 2018-05-24 김영주 */
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, p_menu_no, menu_no,PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE,SERVICE_TYPE) 
VALUES( NEXT VALUE FOR SQAMENU_ID,'작업계획승인 (목록)'
,(SELECT x.menu_id FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE key_no = 'WOMNG' AND x.service_type = 'WEB'))
,(SELECT menu_no FROM TAMENU WHERE key_no = 'WOMNG' AND service_type = 'WEB')
,'WOPLANAPPR'
,(SELECT page_id FROM TAPAGE WHERE file_name = 'workPlanApprList'),'157','','N','WOPLANAPPR','MENU','Y','WEB'); 

/**  2018-05-25 매일유업 반영 */

/**  2018-05-29 양소영 */
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, MENU_NO, P_MENU_NO, SERVICE_TYPE,  FILE_NAME) VALUES( NEXT VALUE FOR SQAMENU_ID,'월별작업현황',(SELECT x.MENU_ID FROM TAMENU x WHERE MENU_ID = (SELECT x.menu_id FROM TAMENU x WHERE x.menu_no = 'SYSTEMREPORT')),(SELECT page_id FROM TAPAGE WHERE file_name = 'connRptMonthLogList'),'300','','N','CONNMONTHLIST','MENU','Y', 'CONNMONTHLIST', (SELECT x.menu_no FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE x.menu_no = 'SYSTEMREPORT')), 'WEB', 'connRptMonthLogList'); 

/** 2018-05-29 김영주 */
INSERT INTO TAMENU(MENU_ID, MENU_NO, DESCRIPTION, P_MENU_ID, P_MENU_NO, PAGE_ID, ORD_NO, PARAM, IS_SYSTEM, KEY_TYPE, KEY_NO, IS_USE, SERVICE_TYPE, FILE_NAME) 
VALUES (NEXT VALUE FOR SQAMENU_ID, 'NEWEQUIP', '신규설비등록현황'
, CASE WHEN (SELECT MENU_ID FROM TAMENU WHERE MENU_NO = 'ASSETREPORT') IS NULL THEN (SELECT MENU_ID FROM TAMENU WHERE MENU_NO = 'ALLREPORT') ELSE (SELECT MENU_ID FROM TAMENU WHERE MENU_NO = 'ASSETREPORT') END
, CASE WHEN (SELECT MENU_ID FROM TAMENU WHERE MENU_NO = 'ASSETREPORT') IS NULL THEN 'ALLREPORT' ELSE 'ASSETREPORT' END
, (SELECT PAGE_ID FROM TAPAGE WHERE FILE_NAME = 'assetRptMonthNewList')
, '0150', '', 'N', 'MENU', 'NEWEQUIP', 'Y', 'WEB', 'assetRptMonthNewList');
INSERT INTO TAMENU(MENU_ID, MENU_NO, DESCRIPTION, P_MENU_ID, P_MENU_NO, PAGE_ID, ORD_NO, PARAM, IS_SYSTEM, KEY_TYPE, KEY_NO, IS_USE, SERVICE_TYPE, FILE_NAME) 
VALUES (NEXT VALUE FOR SQAMENU_ID, 'NEWPMI', '신규점검등록현황'
, CASE WHEN (SELECT MENU_ID FROM TAMENU WHERE MENU_NO = 'WORKREPORT') IS NULL THEN (SELECT MENU_ID FROM TAMENU WHERE MENU_NO = 'ALLREPORT') ELSE (SELECT MENU_ID FROM TAMENU WHERE MENU_NO = 'WORKREPORT') END
, CASE WHEN (SELECT MENU_ID FROM TAMENU WHERE MENU_NO = 'WORKREPORT') IS NULL THEN 'ALLREPORT' ELSE 'WORKREPORT' END
, (SELECT PAGE_ID FROM TAPAGE WHERE FILE_NAME = 'workRptPmMonthNewList')
, '920', '', 'N', 'MENU', 'NEWPMI', 'Y', 'WEB', 'workRptPmMonthNewList');

/** 2018-05-30 양소영 */
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, MENU_NO, P_MENU_NO, SERVICE_TYPE,  FILE_NAME) VALUES( NEXT VALUE FOR SQAMENU_ID,'월별점검실행율',(SELECT x.MENU_ID FROM TAMENU x WHERE MENU_ID = (SELECT x.menu_id FROM TAMENU x WHERE x.menu_no = 'WORKREPORT')),(SELECT page_id FROM TAPAGE WHERE file_name = 'workRptPmMonthRateList'),'810','','N','PMMONTHRATE','MENU','Y', 'PMMONTHRATE', (SELECT x.menu_no FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE x.menu_no = 'WORKREPORT')), 'WEB', 'workRptPmMonthRateList'); 

/**  2018-06-01 매일유업 반영 */

/** 2018-06-07 김영주 */
INSERT INTO TAEHMENU(ehMENU_ID, ehmenu_no, DESCRIPTION, P_ehMENU_ID, P_ehMENU_NO, PAGE_ID, ORD_NO, is_basic, IS_USE, KEY_TYPE, KEY_NO, PARAM, REMARK) 
VALUES (NEXT VALUE FOR SQAEHMENU_ID, 'consultPgmErrorList' , 'CONSULT_SETTING'
, (SELECT x.ehmenu_id FROM TAEHMENU x WHERE ehmenu_id = (SELECT x.ehmenu_id FROM TAEHMENU x WHERE ehmenu_no = 'CONSULT_SETTING')), 'ERRORLIST'
, (SELECT page_id FROM TAPAGE WHERE file_name = 'consultPgmErrorList'), '99970', 'Y', 'Y', 'MENU', 'ERRORLIST', '', ''); 

/** 2018-06-08 이지수 */
INSERT INTO tamenu(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,menu_no,p_menu_no,service_type,file_name)
VALUES(NEXT VALUE FOR SQAEHMENU_ID,'일반자산목록',(SELECT menu_id FROM tamenu WHERE key_no='EQINFO' AND service_type='WEB'),(SELECT page_id FROM tapage WHERE file_name='assetListGnMstrList'),'194','N','GNMSTR','MENU','Y','GNMSTR',(SELECT menu_no FROM tamenu WHERE key_no='EQINFO' AND service_type='WEB'),'WEB','assetListGnMstrList');

/** 2018-06-08 양소영 */
UPDATE taehmenu 
SET page_id =(SELECT page_id FROM tapage WHERE file_name ='consultCompConfigList'), file_name= 'consultCompConfigList' 
WHERE key_no = 'CONFIG' AND page_id = (SELECT page_id FROM tapage WHERE file_name ='maConfigList');

INSERT INTO TAEHMENU(ehMENU_ID, ehmenu_no, DESCRIPTION, P_ehMENU_ID, P_ehMENU_NO, PAGE_ID, FILE_NAME, ORD_NO, is_basic, IS_USE, KEY_TYPE, KEY_NO, PARAM, REMARK) 
VALUES ( NEXT VALUE FOR SQAEHMENU_ID, 'PGMCONFIG' , '프로그램 환경변수'
, (SELECT x.ehmenu_id FROM TAEHMENU x WHERE ehmenu_id = (SELECT x.ehmenu_id FROM TAEHMENU x WHERE ehmenu_no = 'CONSULT_SETTING')), 'CONSULT_SETTING'
, (SELECT page_id FROM TAPAGE WHERE file_name = 'maConfigList'), 'maConfigList', '99980', 'Y', 'Y', 'MENU', 'PGMCONFIG', '', '');

/**  2018-06-19 매일유업 반영 */
/**  2018-06-20 매일유업 반영 */

/** 2018-06-22 김정우 */
INSERT INTO TAEHMENU(ehMENU_ID, ehmenu_no, DESCRIPTION, P_ehMENU_ID, P_ehMENU_NO, PAGE_ID, FILE_NAME, ORD_NO, is_basic, IS_USE, KEY_TYPE, KEY_NO, PARAM, REMARK) 
VALUES (NEXT VALUE FOR sqaEHMENU_ID, 'DBCONTENTS' , '대시보드 컨텐츠'
, (SELECT x.ehmenu_id FROM TAEHMENU x WHERE ehmenu_id = (SELECT x.ehmenu_id FROM TAEHMENU x WHERE ehmenu_no = 'SITE')), 'SITE'
, (SELECT page_id FROM TAPAGE WHERE file_name = 'consultPgmDashboardList'), 'consultPgmDashboardList', '4010', 'Y', 'Y', 'MENU', 'DASHBOARDCONTENTS', '', '');

/**  2018-06-22 매일유업 반영 */
/**  2018-06-27 매일유업 반영 */

/** 2018-06-28 이근환 */
INSERT INTO TAMENU(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,menu_no,p_menu_no,service_type,file_name,is_external_link)
VALUES(NEXT VALUE FOR sqamenu_id,'인터페이스 Log',(SELECT menu_id FROM TAMENU WHERE menu_no='SYSMNG' AND service_type='WEB'),(SELECT page_id FROM TAPAGE WHERE file_name='mgrIntfList'),'650','N','INTFLOG','MENU','N','INTFLOG',(SELECT menu_no FROM TAMENU WHERE menu_no='SYSMNG' AND service_type='WEB'),'WEB','mgrIntfList','N');

/**  2018-07-02 매일유업 반영 */
/**  2018-07-03 매일유업 반영 */

/** 2018-07-05 이지수 */
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE,menu_no,p_menu_no,SERVICE_TYPE, file_name) 
VALUES(NEXT VALUE FOR sqamenu_id,'사내,외주작업현황 Report (목록)',(SELECT x.menu_id FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE x.key_no = 'WORKREPORT' AND x.menu_no = 'WORKREPORT')),(SELECT page_id FROM TAPAGE WHERE file_name = 'workRptSelfVendorList'),'940','N','SELFVENDOR','MENU','Y','SELFVENDOR','WORKREPORT','WEB','workRptSelfVendorList'); 

/** 2018-07-09 김영주 */
INSERT INTO TAEHMENU(ehMENU_ID, ehmenu_no, DESCRIPTION, P_ehMENU_ID, P_ehMENU_NO, PAGE_ID, ORD_NO, is_basic, IS_USE, KEY_TYPE, KEY_NO, PARAM, REMARK) VALUES (NEXT VALUE FOR SQAEHMENU_ID, 'LINKEDFUNC' , '연결기능 (Linked Function)', (SELECT x.ehmenu_id FROM TAEHMENU x WHERE ehmenu_id = (SELECT x.ehmenu_id FROM TAEHMENU x WHERE ehmenu_no = 'CONSULT_SETTING')), 'LINKEDFUNC', (SELECT page_id FROM TAPAGE WHERE file_name = 'consultPgmLinkedFuncList'), '99990', 'Y', 'Y', 'MENU', 'LINKEDFUNC', '', '');

/**  2018-07-12 매일유업 반영 */

/**  2018-07-13 DREAM 반영 */

/** 2018-07-13 김영주 */
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE,menu_no,p_menu_no,SERVICE_TYPE, file_name) 
VALUES( NEXT VALUE FOR SQAMENU_ID,'TO-BE 프로세스',(SELECT x.menu_id FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE x.key_no = 'TEMP' AND x.menu_no = 'TEMP')),(SELECT page_id FROM TAPAGE WHERE file_name = 'mgrToBeProcessList'),'920','N','TOBEPROC','MENU','Y','TOBEPROC','TEMP','WEB','mgrToBeProcessList'); 

/** 2018-07-18 이지수 */
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE,menu_no,p_menu_no,SERVICE_TYPE, file_name) 
VALUES(NEXT VALUE FOR SQAMENU_ID,'작업의뢰 사전시스템 요청율',(SELECT x.menu_id FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE x.key_no = 'REPORT' AND x.menu_no = 'WOREQRPT')),(SELECT page_id FROM TAPAGE WHERE file_name = 'reqRptPreWoreqRateList'),'400','N','PREWOREQRATE','MENU','Y','PREWOREQRATE','WOREQRPT','WEB','reqRptPreWoreqRateList'); 

/**  2018-07-19 매일유업 반영 */
/**  2018-07-19 국도 반영 */

/** 2018-07-20 이지수 */
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE,menu_no,p_menu_no,SERVICE_TYPE, file_name) 
VALUES(NEXT VALUE FOR SQAMENU_ID,'Overhaul 작업 목록',(SELECT x.menu_id FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE x.key_no = 'WOMNG' AND x.menu_no = 'WOMNG_WEB')),(SELECT page_id FROM TAPAGE WHERE file_name = 'maWoPmwOvhResultMstrList'),'135','N','WOPMWOVHRESULT','MENU','Y','WOPMWOVHRESULT','WOMNG_WEB','WEB','maWoPmwOvhResultMstrList'); 

INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE,menu_no,p_menu_no,SERVICE_TYPE, file_name) 
VALUES(NEXT VALUE FOR SQAMENU_ID,'작업의뢰 계획대비 실행 비율',(SELECT x.menu_id FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE x.key_no = 'REPORT' AND x.menu_no = 'WOREQRPT')),(SELECT page_id FROM TAPAGE WHERE file_name = 'reqRptWoPlanCmptRateList'),'500','N','WOPLANCMPTRATE','MENU','Y','WOPLANCMPTRATE','WOREQRPT','WEB','reqRptWoPlanCmptRateList'); 
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE,menu_no,p_menu_no,SERVICE_TYPE, file_name) 
VALUES(NEXT VALUE FOR SQAMENU_ID,'사후 작업오더 발생률',(SELECT x.menu_id FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE x.key_no = 'REPORT' AND x.menu_no = 'WOREQRPT')),(SELECT page_id FROM TAPAGE WHERE file_name = 'reqRptEmWoGenRateList'),'600','N','EMWOGENRATE','MENU','Y','EMWOGENRATE','WOREQRPT','WEB','reqRptEmWoGenRateList'); 

/** 2018-07-24 국도 반영 */
/** 2018-07-24 DREAM 반영 */
/** 2018-07-26 국도 반영 */
/** 2018-07-27 매일유업 반영 */

/** 2018-07-27 이지수 */
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE,menu_no,p_menu_no,SERVICE_TYPE, file_name) 
VALUES(NEXT VALUE FOR SQAMENU_ID,'작업오더 사전 계획 수립률',(SELECT x.menu_id FROM TAMENU x WHERE x.menu_no = 'WOREQRPT'),(SELECT page_id FROM TAPAGE WHERE file_name = 'reqRptPreWoPlanRateList'),'500','N','PREWOPLANRATE','MENU','Y','PREWOPLANRATE','WOREQRPT','WEB','reqRptPreWoPlanRateList'); 

/** 2018-07-30 이지수 */
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE,menu_no,p_menu_no,SERVICE_TYPE, file_name) 
VALUES(NEXT VALUE FOR SQAMENU_ID,'작업오더 일마감 처리률',(SELECT x.menu_id FROM TAMENU x WHERE x.menu_no = 'WORKREPORT'),(SELECT page_id FROM TAPAGE WHERE file_name = 'workRptWoEndRateList'),'950','N','WOENDRATE','MENU','Y','WOENDRATE','WORKREPORT','WEB','workRptWoEndRateList'); 
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE,menu_no,p_menu_no,SERVICE_TYPE, file_name) 
VALUES(NEXT VALUE FOR SQAMENU_ID,'예방정비 계획대비 실행 비율',(SELECT x.menu_id FROM TAMENU x WHERE x.menu_no = 'WORKREPORT'),(SELECT page_id FROM TAPAGE WHERE file_name = 'workRptPmwCmptRateList'),'960','N','PMWCMPTRATE','MENU','Y','PMWCMPTRATE','WORKREPORT','WEB','workRptPmwCmptRateList'); 

/** 2018-07-30 김영주 */
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, p_menu_no, menu_no,PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE,SERVICE_TYPE) 
VALUES( NEXT VALUE FOR SQAMENU_ID,'월간작업일정(통합)'
,(SELECT x.menu_id FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE key_no = 'WOMNG' AND x.service_type = 'WEB'))
,(SELECT menu_no FROM TAMENU WHERE key_no = 'WOMNG' AND service_type = 'WEB')
,'UNITEDMON'
,(SELECT page_id FROM TAPAGE WHERE file_name = 'workCalUnitedMonthList'),'159','','N','UNITEDMON','MENU','Y','WEB'); 

/** 2018-07-30 이지수 */
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE,menu_no,p_menu_no,SERVICE_TYPE, file_name) 
VALUES(NEXT VALUE FOR SQAMENU_ID,'예방정비 실행 비율',(SELECT x.menu_id FROM TAMENU x WHERE x.menu_no = 'WORKREPORT'),(SELECT page_id FROM TAPAGE WHERE file_name = 'workRptPmwRateList'),'965','N','PMWRATE','MENU','Y','PMWRATE','WORKREPORT','WEB','workRptPmwRateList'); 
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE,menu_no,p_menu_no,SERVICE_TYPE, file_name) 
VALUES(NEXT VALUE FOR SQAMENU_ID,'예방점검설비 실행 비율',(SELECT x.menu_id FROM TAMENU x WHERE x.menu_no = 'WORKREPORT'),(SELECT page_id FROM TAPAGE WHERE file_name = 'workRptPmiEquipCmptRateList'),'970','N','PMIEQUIPCMPTRATE','MENU','Y','PMIEQUIPCMPTRATE','WORKREPORT','WEB','workRptPmiEquipCmptRateList'); 
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE,menu_no,p_menu_no,SERVICE_TYPE, file_name) 
VALUES(NEXT VALUE FOR SQAMENU_ID,'예방점검항목 실행 비율',(SELECT x.menu_id FROM TAMENU x WHERE x.menu_no = 'WORKREPORT'),(SELECT page_id FROM TAPAGE WHERE file_name = 'workRptPmiCmptRateList'),'975','N','PMICMPTRATE','MENU','Y','PMICMPTRATE','WORKREPORT','WEB','workRptPmiCmptRateList'); 

/** 2018-08-02 양소영 */
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, MENU_NO, P_MENU_NO, SERVICE_TYPE,  FILE_NAME) VALUES( NEXT VALUE FOR SQAMENU_ID,'Message전송현황(목록)',(SELECT x.MENU_ID FROM TAMENU x WHERE MENU_ID = (SELECT x.menu_id FROM TAMENU x WHERE key_no = 'SYSMNG')),(SELECT page_id FROM TAPAGE WHERE file_name = 'mgrMessageTransList'),'660','','N','MESSAGETRANS','MENU','Y', 'MESSAGETRANS', (SELECT x.menu_no FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE key_no = 'SYSMNG')), 'WEB', 'mgrMessageTransList'); 

/** 2018-08-03 김영주 */
INSERT INTO TAMENU(MENU_ID, P_MENU_NO, P_MENU_ID, DESCRIPTION, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_TYPE, KEY_NO, IS_USE,SERVICE_TYPE,MENU_NO, FILE_NAME) 
VALUES( NEXT VALUE FOR SQAMENU_ID,'EQINFO',(SELECT x.menu_id FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE menu_no = 'EQINFO')),'계측기위험도평가',(SELECT page_id FROM TAPAGE WHERE file_name = 'assAssetTlList'),'196','','N','MENU','ASSASSETTL','Y','WEB','ASSASSETTL','assAssetTlList'); 

/** 2018-08-03 최지상  */
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE,menu_no,p_menu_no,SERVICE_TYPE, file_name) 
VALUES(NEXT VALUE FOR SQAMENU_ID,'예방점검 설비 계획대비 실행 비율',(SELECT x.menu_id FROM TAMENU x WHERE x.menu_no = 'WORKREPORT'),(SELECT page_id FROM TAPAGE WHERE file_name = 'workRptPmiEquipPlanRateList'),'980','N','PMIPLANRATE','MENU','Y','PMIPLANRATE','WORKREPORT','WEB','workRptPmiEquipPlanRateList'); 
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE,menu_no,p_menu_no,SERVICE_TYPE, file_name) 
VALUES(NEXT VALUE FOR SQAMENU_ID,'투자비 진행현황',(SELECT x.menu_id FROM TAMENU x WHERE x.menu_no = 'INVREPORT'),(SELECT page_id FROM TAPAGE WHERE file_name = 'invtRptPriceExeRateList'),'990','N','PRICEEXERATE','MENU','Y','PRICEEXERATE','INVREPORT','WEB','invtRptPriceExeRateList'); 

/** 2018-08-06 이근환 */
INSERT INTO tamenu(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,menu_no,p_menu_no,service_type,file_name,is_external_link)
VALUES (NEXT VALUE FOR sqamenu_id,'단가계약요청',(SELECT menu_id FROM tamenu WHERE menu_no='TEMP' AND service_type='WEB'),(SELECT page_id FROM tapage WHERE file_name='daewoongPartPurReqList'),'970','N','PARTPURREQ','MENU','Y','PARTPURREQ',(SELECT menu_no FROM tamenu WHERE menu_no='TEMP' AND service_type='WEB'),'WEB','daewoongPartPurReqList','N');

/** 2018-08-07 국도 반영 */

/** 2018-08-07 김영주 */
INSERT INTO TAMENU(MENU_ID, P_MENU_NO, P_MENU_ID, DESCRIPTION, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_TYPE, KEY_NO, IS_USE,SERVICE_TYPE,MENU_NO, FILE_NAME) VALUES(NEXT VALUE FOR sqamenu_id,'ASSETREPORT',(SELECT x.menu_id FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE menu_no = 'ASSETREPORT')),'수선유지비 집행현황',(SELECT page_id FROM TAPAGE WHERE file_name = 'assetRptMaintCostList'),'160','','N','MENU','MAINTCOST','Y','WEB','MAINTCOST','assetRptMaintCostList'); 
INSERT INTO TAMENU(MENU_ID, P_MENU_NO, P_MENU_ID, DESCRIPTION, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_TYPE, KEY_NO, IS_USE,SERVICE_TYPE,MENU_NO, FILE_NAME) VALUES( NEXT VALUE FOR SQAMENU_ID,'ASSETREPORT',(SELECT x.menu_id FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE menu_no = 'ASSETREPORT')),'설비별 고장강도율',(SELECT page_id FROM TAPAGE WHERE file_name = 'assetRptBdIntensityList'),'170','','N','MENU','BDINTENSITY','Y','WEB','BDINTENSITY','assetRptBdIntensityList'); 

/** 2018-08-07 이지수 */
INSERT INTO TAMENU(MENU_ID, P_MENU_NO, P_MENU_ID, DESCRIPTION, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_TYPE, KEY_NO, IS_USE,SERVICE_TYPE,MENU_NO, FILE_NAME) VALUES( NEXT VALUE FOR SQAMENU_ID,'ASSETREPORT',(SELECT x.menu_id FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE menu_no = 'ASSETREPORT')),'설비별 고장도수율',(SELECT page_id FROM TAPAGE WHERE file_name = 'assetRptBdFrequencyList'),'180','','N','MENU','BDFREQUENCY','Y','WEB','BDFREQUENCY','assetRptBdFrequencyList'); 

/** 2018-08-08 김은아 */
INSERT INTO TAMENU(menu_id, description, p_menu_id, page_id, ord_no,  is_system, key_no, key_type, is_use, menu_no, p_menu_no, service_type, file_name, is_external_link)
VALUES(NEXT VALUE FOR sqamenu_id, '안전작업유형',  (SELECT menu_id FROM TAMENU WHERE menu_no = 'WORKSETTING' AND service_type = 'WEB') 
, (SELECT page_id FROM TAPAGE WHERE file_name = 'workLetCategList'), '900', 'N', 'woLetCtg', 'MENU', 'Y', 'woLetCtg', (SELECT menu_no FROM TAMENU WHERE menu_no =  'WORKSETTING' AND service_type = 'WEB') 
, 'WEB', 'workLetCategList', 'N');


/** 2018-08-08 국도 반영 */
/** 2018-08-09 국도 반영 */
/** 2018-08-10 국도 반영 */

/** 2018-08-13 김남현 */
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE,menu_no,p_menu_no,SERVICE_TYPE, file_name) 
VALUES(NEXT VALUE FOR SQAMENU_ID,'대시보드',(SELECT menu_id FROM tamenu WHERE key_no='PERSONAL' AND menu_no='PERSONAL' AND service_type='WEB'),(SELECT page_id FROM TAPAGE WHERE file_name = 'persPrivDbSetList'),'900','N','DASHBOARD','MENU','Y','DASHBOARD','PERSONAL','WEB','persPrivDbSetList');

UPDATE TAMENU SET key_no = 'WORKSTATUS', menu_no = 'WORKSTATUS' where key_no = 'DASHBOARD';


/** 2018-08-14 최지상*/
INSERT INTO tamenu(menu_id, description, p_menu_id,page_id, ord_no,  is_system, key_no, key_type, is_use, menu_no, p_menu_no, service_type, file_name, is_external_link)
VALUES(NEXT VALUE FOR sqamenu_id,'서비스마스터',(SELECT menu_id FROM tamenu WHERE menu_no='WORKSETTING' AND service_type='WEB'),(SELECT page_id FROM tapage WHERE file_name='workServiceList'),'900','N','WORKSERVICE','MENU','Y','WORKSERVICE', (SELECT menu_no FROM tamenu WHERE menu_no='WORKSETTING' AND service_type='WEB'), 'WEB', 'workServiceList','N');

/** 2018-08-14 김영주 */
INSERT INTO TAMENU(MENU_ID, P_MENU_NO, P_MENU_ID, DESCRIPTION, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_TYPE, KEY_NO, IS_USE,SERVICE_TYPE,MENU_NO, FILE_NAME) VALUES( NEXT VALUE FOR SQAMENU_ID,'SYSMNG',(SELECT x.menu_id FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE menu_no = 'SYSMNG')),'Audit Trail',(SELECT page_id FROM TAPAGE WHERE file_name = 'mgrAtList'),'920','','N','MENU','AUDTRAIL','Y','WEB','AUDTRAIL','mgrAtList'); 

/** 2018-08-16 김영주 */
INSERT INTO TAMENU(MENU_ID, P_MENU_NO, P_MENU_ID, DESCRIPTION, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_TYPE, KEY_NO, IS_USE,SERVICE_TYPE,MENU_NO, FILE_NAME) VALUES( NEXT VALUE FOR SQAMENU_ID,'ASSETREPORT',(SELECT x.menu_id FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE menu_no = 'ASSETREPORT')),'고장TOP(사용부서)',(SELECT page_id FROM TAPAGE WHERE file_name = 'assetRptLccUsDeptList'),'0025','','N','MENU','LCCUSAGEDEPT','Y','WEB','LCCUSAGEDEPT','assetRptLccUsDeptList'); 
INSERT INTO TAMENU(MENU_ID, P_MENU_NO, P_MENU_ID, DESCRIPTION, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_TYPE, KEY_NO, IS_USE,SERVICE_TYPE,MENU_NO, FILE_NAME) VALUES( NEXT VALUE FOR SQAMENU_ID,'ASSETREPORT',(SELECT x.menu_id FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE menu_no = 'ASSETREPORT')),'MTBF,MTTR(사용부서)',(SELECT page_id FROM TAPAGE WHERE file_name = 'assetRptMtbfmttrUsDeptList'),'0045','','N','MENU','MTBFMTTRUSAGEDEPT','Y','WEB','MTBFMTTRUSAGEDEPT','assetRptMtbfmttrUsDeptList'); 

/** 2018-08-17 국도 반영 */

/** 2018-08-20 김영주 추가 */
INSERT INTO TAMENU(MENU_ID, P_MENU_NO, P_MENU_ID, DESCRIPTION, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_TYPE, KEY_NO, IS_USE,SERVICE_TYPE,MENU_NO, FILE_NAME) VALUES( NEXT VALUE FOR SQAMENU_ID,'WORKREPORT',(SELECT x.menu_id FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE menu_no = 'WORKREPORT')),'부서별 작업진행현황',(SELECT page_id FROM TAPAGE WHERE file_name = 'workRptDeptWorkPreconList'),'985','','N','MENU','DEPTWORKPRECON','Y','WEB','DEPTWORKPRECON','workRptDeptWorkPreconList'); 

/** 2018-08-21 DREAM 반영 */

/** 2018-08-21 이근환 */
INSERT INTO tamenu(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,menu_no,p_menu_no,service_type,file_name,is_external_link)
VALUES(NEXT VALUE FOR sqamenu_id,'재고이동',(SELECT menu_id FROM tamenu WHERE menu_no='PTADJUST' AND service_type='WEB'),(SELECT page_id FROM tapage WHERE file_name='partAdjStkMoveList'),'700','N','PTSTKMOVE','MENU','N','PTSTKMOVE','PTADJUST','WEB','partAdjStkMoveList','N');

/** 2018-08-22 매일유업 반영 */

/** 2018-08-27 김남현 */
INSERT INTO TAMENU(MENU_ID, P_MENU_NO, P_MENU_ID, DESCRIPTION, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_TYPE, KEY_NO, IS_USE,SERVICE_TYPE,MENU_NO, FILE_NAME) VALUES(NEXT VALUE FOR SQAMENU_ID,'ASSETREPORT',(SELECT x.menu_id FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE menu_no = 'ASSETREPORT')),'설비등급평가 항목별 점수',(SELECT page_id FROM TAPAGE WHERE file_name = 'assetRptAssAssetScoreList'),'190','','N','MENU','ASSASSETCATEGORYSCORE','Y','WEB','ASSASSETCATEGORYSCORE','assetRptAssAssetScoreList');

/** 2018-08-27 김은아 */
INSERT INTO TAMENU(menu_id, description, p_menu_id, page_id, ord_no,  is_system, key_no, key_type, is_use, menu_no, p_menu_no, service_type, file_name, is_external_link)
VALUES(next value for sqamenu_id,'화면접속로그' ,(SELECT menu_id FROM TAMENU WHERE menu_no='SYSTEMREPORT' AND service_type='WEB') ,(SELECT page_id FROM TAPAGE WHERE file_name='mgrRptScrnLogList'),'900','N','AccessLog' ,'MENU','Y' ,'AccessLog', (SELECT menu_no FROM TAMENU WHERE menu_no='SYSTEMREPORT' AND service_type='WEB'), 'WEB', 'mgrRptScrnLogList' ,'N');
INSERT INTO TAMENU(menu_id, description, p_menu_id, page_id, ord_no,  is_system, key_no, key_type, is_use, menu_no, p_menu_no, service_type, file_name, is_external_link)
VALUES(next value for sqamenu_id,'로그인로그' ,(SELECT menu_id FROM TAMENU WHERE menu_no='SYSTEMREPORT' AND service_type='WEB') ,(SELECT page_id FROM TAPAGE WHERE file_name='mgrRptLoginLogList'),'900','N','LoginLog' ,'MENU','Y' ,'LoginLog' , (SELECT menu_no FROM TAMENU WHERE menu_no='SYSTEMREPORT' AND service_type='WEB'), 'WEB', 'mgrRptLoginLogList' ,'N'); 

/** 2018-08-28 국도 반영 */

/** 2018-08-29 김은아 */
UPDATE tamenu SET p_menu_id = (SELECT menu_id FROM TAMENU WHERE menu_no='SYSTEMREPORT' AND service_type='WEB') WHERE description = '데이타 Log'; 

/** 2018-09-04 국도 반영 */

/** 2018-09-05 김남현 */
UPDATE tamenu set menu_no = 'SCPURCLAIM', key_no = 'SCPURCLAIM' WHERE page_id = (SELECT page_id FROM tapage WHERE file_name = 'maPtPurReqList');

/** 2018-09-06 김영주 */
UPDATE TAMENU SET ORD_NO = '0'+ ORD_NO WHERE P_MENU_NO = 'ASSETREPORT' AND LEN(ORD_NO) = 3; 
INSERT INTO TAMENU(MENU_ID, P_MENU_NO, P_MENU_ID, DESCRIPTION, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_TYPE, KEY_NO, IS_USE,SERVICE_TYPE,MENU_NO, FILE_NAME) VALUES( NEXT VALUE FOR SQAMENU_ID,'WORKREPORT',(SELECT x.menu_id FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE menu_no = 'WORKREPORT')),'에너지 사용량(일별)',(SELECT page_id FROM TAPAGE WHERE file_name = 'workRptDailyEngList'),'990','','N','MENU','DAILYENG','Y','WEB','DAILYENG','workRptDailyEngList');

/** 2018-09-06 매일유업 반영 */
/** 2018-09-10 국도 반영 */

/** 2018-09-10 김영주 */
INSERT INTO TAMENU(MENU_ID, P_MENU_NO, P_MENU_ID, DESCRIPTION, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_TYPE, KEY_NO, IS_USE,SERVICE_TYPE,MENU_NO, FILE_NAME) VALUES( NEXT VALUE FOR SQAMENU_ID,'WORKREPORT',(SELECT x.menu_id FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE menu_no = 'WORKREPORT')),'에너지 사용량(설비별)',(SELECT page_id FROM TAPAGE WHERE file_name = 'workRptEqEngList'),'995','','N','MENU','EQENG','Y','WEB','EQENG','workRptEqEngList');

/**  2018-09-12 최지상 */
INSERT INTO tamenu(menu_id, description, p_menu_id,page_id, ord_no,  is_system, key_no, key_type, is_use, menu_no, p_menu_no, service_type, file_name, is_external_link)
VALUES(NEXT VALUE FOR sqamenu_id,'월별투자집행금액',(SELECT menu_id FROM tamenu WHERE key_no='INVREPORT'),(SELECT page_id FROM tapage WHERE file_name='invtRptMonInvtAmtList'),'990','N','MONINVTAMT','MENU','Y','MONINVTAMT', (SELECT menu_no FROM tamenu WHERE menu_no='INVREPORT' AND service_type='WEB'), 'WEB', 'invtRptMonInvtAmtList','N');

/**  2018-09-12 김영주 */
INSERT INTO TAMENU(MENU_ID, P_MENU_NO, P_MENU_ID, DESCRIPTION, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_TYPE, KEY_NO, IS_USE,SERVICE_TYPE,MENU_NO, FILE_NAME) VALUES( NEXT VALUE FOR SQAMENU_ID,'WORKREPORT',(SELECT x.menu_id FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE menu_no = 'WORKREPORT')),'에너지 사용량(집계)',(SELECT page_id FROM TAPAGE WHERE file_name = 'workRptTotEngList'),'1000','','N','MENU','TOTENG','Y','WEB','TOTENG','workRptTotEngList'); 

/**  2018-09-14 김영주 */
UPDATE TAMENU SET ORD_NO = '0020' WHERE PAGE_ID = (SELECT PAGE_ID FROM TAPAGE WHERE FILE_NAME = 'invtRptPriceExeRateList');
UPDATE TAMENU SET ORD_NO = '0030' WHERE PAGE_ID = (SELECT PAGE_ID FROM TAPAGE WHERE FILE_NAME = 'invtRptMonInvtAmtList');
UPDATE TAMENU SET ORD_NO = '0040' WHERE PAGE_ID = (SELECT PAGE_ID FROM TAPAGE WHERE FILE_NAME = 'invtRptLeadTimeList');
INSERT INTO TAMENU(MENU_ID, P_MENU_NO, P_MENU_ID, DESCRIPTION, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_TYPE, KEY_NO, IS_USE,SERVICE_TYPE,MENU_NO, FILE_NAME) VALUES( NEXT VALUE FOR SQAMENU_ID,'INVREPORT',(SELECT x.menu_id FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE menu_no = 'INVREPORT')),'투자구분분석',(SELECT page_id FROM TAPAGE WHERE file_name = 'invtRptInvtCategList'),'0050','','N','MENU','INVTCATEG','Y','WEB','INVTCATEG','invtRptInvtCategList'); 


/** 2018-09-17 최지상 */
INSERT INTO tamenu(menu_id, description, p_menu_id,page_id, ord_no,  is_system, key_no, key_type, is_use, menu_no, p_menu_no, service_type, file_name, is_external_link)
VALUES(NEXT VALUE FOR sqamenu_id,'투자진행 LeadTime 분석',(SELECT menu_id FROM tamenu WHERE key_no='INVREPORT'),(SELECT page_id FROM tapage WHERE file_name='invtRptLeadTimeList'),'990','N','INVTLEADTIME','MENU','Y','INVTLEADTIME', (SELECT menu_no FROM tamenu WHERE menu_no='INVREPORT' AND service_type='WEB'), 'WEB', 'invtRptLeadTimeList','N');

/** 2018-09-20 국도 반영 */
/** 2018-09-20 매일유업 반영 */
/** 2018-10-12 국도화학 반영 */


/** 2018-10-18 노정현 */
UPDATE TAMENU 
SET param = 'workCalPmInsApprCommonDTO.pminsschedapprType=PLN'
WHERE page_id=  (SELECT A.page_id FROM TAPAGE A WHERE A.file_name ='workCalPmInsApprList');

UPDATE TAMENU 
SET param = 'workPlanApprCommonDTO.woplanapprType=PLN&workPlanApprCommonDTO.woType=PMC'
WHERE page_id=  (SELECT A.page_id FROM TAPAGE A WHERE A.file_name ='workPlanApprList');

/** 2018-10-24 김은아 */
INSERT INTO TAMENU(menu_id, description, p_menu_id, page_id, ord_no,  is_system, key_no, key_type, is_use, menu_no, p_menu_no, service_type, file_name, is_external_link)
VALUES(NEXT VALUE FOR sqamenu_id,'부품수불부', ( SELECT menu_id FROM TAMENU WHERE menu_no = 'PTMNG_WEB' AND service_type = 'WEB'),(SELECT page_id FROM TAPAGE WHERE file_name='partRptMonthlyStockList'),'4500','N','materialsLedger','MENU','Y','MATERIALSLEDGER', 'PTMNG_WEB', 'WEB', 'partRptMonthlyStockList','N');     

/** 2018-10-25 국도화학 반영 */    
/** 2018-10-26 국도화학 반영 */
/** 2018-10-30 매일유업 반영 */
/** 2018-11-01 국도화학 반영 */
/** 2018-11-02 매일유업 반영 */
/** 2018-11-07 본사 DREAM 반영 */

/** 2018-11-07 김은아 */
INSERT INTO TAMENU(menu_id, description, p_menu_id,page_id, ord_no,  is_system, key_no, key_type, is_use, menu_no, p_menu_no, service_type, file_name, is_external_link)
VALUES(NEXT VALUE FOR sqamenu_id,'가동달력설정',(SELECT menu_id FROM TAMENU WHERE key_no='WORKSETTING'),(SELECT page_id FROM TAPAGE WHERE file_name='mgrCalLineTimeSetList'),'920','N','LNWRKLISTSET','MENU','Y','LNWRKLISTSET', 'WORKSETTING', 'WEB', 'mgrCalLineTimeSetList','N');      

/** 2018-11-08 국도화학 반영 */
/** 2018-11-08 매일유업 반영 */

/** 2018-11-09 김은아 */
INSERT INTO TAMENU(menu_id, description, p_menu_id,page_id, ord_no,  is_system, key_no, key_type, is_use, menu_no, p_menu_no, service_type, file_name, is_external_link)
VALUES(NEXT VALUE FOR sqamenu_id,'근무일달력설정',(SELECT menu_id FROM TAMENU WHERE key_no='WORKSETTING'),(SELECT page_id FROM TAPAGE WHERE file_name='mgrCalCompWkrcalList'),'920','N','WORKDAYCALSET','MENU','Y','WORKDAYCALSET', 'WORKSETTING', 'WEB', 'mgrCalCompWkrcalList','N');                    
  
/** 2018-11-09 김영주 */
INSERT INTO TAEHMENU(ehMENU_ID, ehmenu_no, DESCRIPTION, P_ehMENU_ID, P_ehMENU_NO, PAGE_ID, ORD_NO, is_basic, IS_USE, KEY_TYPE, KEY_NO, PARAM, REMARK) VALUES (NEXT VALUE FOR sqamenu_id, 'SETINF' , '인터페이스 설정', (SELECT x.ehmenu_id FROM TAEHMENU x WHERE ehmenu_id = (SELECT x.ehmenu_id FROM TAEHMENU x WHERE ehmenu_no = 'SITE')), 'SETINF', (SELECT page_id FROM TAPAGE WHERE file_name = 'consultCompIntfList'), '1700', 'Y', 'Y', 'MENU', 'SETINF', '', '');

/** 2018-11-15 국도화학 반영 */

/** 2018-11-15 이근환 */
UPDATE tamenu set menu_no='BUDACCOUNT'
WHERE menu_no='BUDACCNT';
INSERT INTO tamenu(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,menu_no,p_menu_no,service_type,is_external_link)
SELECT NEXT VALUE FOR sqamenu_id, description, p_menu_id, (SELECT page_id FROM tapage WHERE file_name='budgetAccountList'), ord_no, is_system, key_no, key_type, is_use, 'BUDACCNT', p_menu_no, 'WEB', is_external_link
FROM tamenu
WHERE menu_no='BUDACCOUNT';

/** 2018-11-16 국도화학 반영 */
/** 2018-11-19 본사 국도화학 반영 */
/** 2018-11-19 국도화학 반영 */ 
/** 2018-11-20 국도화학 반영 */
/** 2018-11-21 국도화학 반영 */

/** 2018-11-23 김영주 */
INSERT INTO TAMENU(MENU_ID, P_MENU_NO, P_MENU_ID, DESCRIPTION, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_TYPE, KEY_NO, IS_USE,SERVICE_TYPE,MENU_NO, FILE_NAME) VALUES( NEXT VALUE FOR SQAMENU_ID,'SYSTEMREPORT',(SELECT x.menu_id FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE menu_no = 'SYSTEMREPORT')),'로그인 Try Log',(SELECT page_id FROM TAPAGE WHERE file_name = 'mgrRptLoginTryLogList'),'910','','N','MENU','LOGINTRYLOG','Y','WEB','LOGINTRYLOG','mgrRptLoginTryLogList'); 

/** 2018-11-28 국도화학 반영 */
/** 2018-11-29 국도화학 반영 */
/** 2018-12-03 국도화학 반영 */
/** 2018-12-05 본사 DREAM 반영 */
/** 2018-12-06 매일유업 반영 */
/** 2018-12-14 국도화학 반영 */

/** 2018-12-14 김영주 */
INSERT INTO TAMENU(MENU_ID, P_MENU_NO, P_MENU_ID, DESCRIPTION, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_TYPE, KEY_NO, IS_USE,SERVICE_TYPE,MENU_NO, FILE_NAME) VALUES( NEXT VALUE FOR SQAMENU_ID,'SYSMNG',(SELECT x.menu_id FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE menu_no = 'SYSMNG')),'메시지 수신설정',(SELECT page_id FROM TAPAGE WHERE file_name = 'mgrMsgRecList'),'670','','N','MENU','MSGREC','N','WEB','MSGREC','mgrMsgRecList'); 

/** 2018-12-17 10:08 국도화학 반영 */

/** 2018-12-17 김영주 */
INSERT INTO TAEHMENU(EHMENU_ID, P_EHMENU_NO, P_EHMENU_ID, DESCRIPTION, PAGE_ID,ORD_NO,PARAM, KEY_TYPE, KEY_NO, IS_USE,EHMENU_NO, FILE_NAME, IS_BASIC) VALUES( NEXT VALUE FOR SQAEHMENU_ID,'SITE',(SELECT x.ehmenu_id FROM TAEHMENU x WHERE ehmenu_id = (SELECT x.ehmenu_id FROM TAEHMENU x WHERE ehmenu_no = 'SITE')),'메시지 설정(메일, SMS)',(SELECT page_id FROM TAPAGE WHERE file_name = 'consultCompMsgList'),'4200','','MENU','COMPMSG','Y','COMPMSG','consultCompMsgList','Y'); 

/** 2018-12-17 12:20 국도화학 반영 */
/** 2018-12-19 11:00 국도화학 반영 */
/** 2018-12-19 17:25 국도화학 반영 */
/** 2018-12-20 17:10 국도화학 반영 */
/** 2018-12-21 17:50 국도화학 반영 */

/** 2018-12-26 양소영 */
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, MENU_NO, P_MENU_NO, SERVICE_TYPE,  FILE_NAME) VALUES( NEXT VALUE FOR SQAMENU_ID,'안전작업(목록)',(SELECT x.MENU_ID FROM TAMENU x WHERE MENU_ID = (SELECT x.menu_id FROM TAMENU x WHERE x.menu_no = 'WOMNG_WEB')),(SELECT page_id FROM TAPAGE WHERE file_name = 'workLetList'),'158','','N','WOLETLIST','MENU','Y', 'WOLETLIST', (SELECT x.menu_no FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE x.menu_no = 'WOMNG_WEB')), 'WEB', 'workLetList'); 

/** 2018-12-28 14:20 국도화학 반영 */

/** 2019-01-03 김영주 */
DELETE FROM TAEHMENU WHERE file_name = 'consultCompMsgList';
INSERT INTO TAEHMENU(EHMENU_ID, P_EHMENU_NO, P_EHMENU_ID, DESCRIPTION, PAGE_ID,ORD_NO,PARAM, KEY_TYPE, KEY_NO, IS_USE,EHMENU_NO, FILE_NAME, IS_BASIC) 
VALUES( NEXT VALUE FOR SQAEHMENU_ID,'SITE',(SELECT x.ehmenu_id FROM TAEHMENU x WHERE ehmenu_id = (SELECT x.ehmenu_id FROM TAEHMENU x WHERE ehmenu_no = 'CONSULT_SETTING')),'메시지 설정(메일, SMS)',(SELECT page_id FROM TAPAGE WHERE file_name = 'consultPgmMsgList'),'99995','','MENU','PGMMSG','Y','PGMMSG','consultMsgMsgList','Y');

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


/** 2019-04-12 이지수 */
INSERT INTO TAMENU(MENU_ID, P_MENU_NO, P_MENU_ID, DESCRIPTION, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_TYPE, KEY_NO, IS_USE,SERVICE_TYPE,MENU_NO, FILE_NAME) 
VALUES( next value for SQAMENU_ID, null,'0','에너지관리',null,'500','','N','MENU','AE000','N','WEB','AE000',''); 
INSERT INTO TAMENU(MENU_ID, P_MENU_NO, P_MENU_ID, DESCRIPTION, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_TYPE, KEY_NO, IS_USE,SERVICE_TYPE,MENU_NO, FILE_NAME) 
VALUES( next value for SQAMENU_ID,'AE000',(SELECT x.menu_id FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE menu_no = 'AE000')),'에너지 측정주기 설정',(SELECT page_id FROM TAPAGE WHERE file_name = 'workPmListUInsList'),'120','','N','MENU','AE120','N','WEB','AE120','workPmListUInsList'); 





/** 2019-02-15 양소영 */
UPDATE TAMENU SET param = 'workCalPmInsApprCommonDTO.pminsschedapprType=PLN' WHERE page_id=(SELECT page_id FROM tapage WHERE file_name='workCalPmInsApprList');

/** 2019-02-18 양소영 */
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, MENU_NO, P_MENU_NO, SERVICE_TYPE,  FILE_NAME) VALUES( NEXT VALUE FOR SQAMENU_ID,'예방점검완료승인(목록)',(SELECT x.MENU_ID FROM TAMENU x WHERE MENU_ID = (SELECT x.menu_id FROM TAMENU x WHERE x.menu_no = 'AD200')),(SELECT page_id FROM TAPAGE WHERE file_name = 'workCalPmInsApprCompList'),'150','workCalPmInsApprCommonDTO.pminsschedapprType=ACT','N','D260','MENU','Y', 'D260', (SELECT x.menu_no FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE x.menu_no = 'AD200')), 'WEB', 'workCalPmInsApprCompList'); 
INSERT INTO TAMENU(MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, MENU_NO, P_MENU_NO, SERVICE_TYPE,  FILE_NAME) VALUES( NEXT VALUE FOR SQAMENU_ID,'미점검승인(목록)',(SELECT x.MENU_ID FROM TAMENU x WHERE MENU_ID = (SELECT x.menu_id FROM TAMENU x WHERE x.menu_no = 'AD200')),(SELECT page_id FROM TAPAGE WHERE file_name = 'workCalPmInsApprNotList'),'160','workCalPmInsApprCommonDTO.pminsschedapprType=NOT','N','D270','MENU','Y', 'D270', (SELECT x.menu_no FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE x.menu_no = 'AD200')), 'WEB', 'workCalPmInsApprNotList'); 

/** 2019-03-12 이근환 */
INSERT INTO tamenu(menu_id,description,p_menu_id,page_id,ord_no,is_system,key_no,key_type,is_use,menu_no,p_menu_no,service_type,file_name,is_external_link)
VALUES(NEXT VALUE FOR sqamenu_id,'예방작업계획실행율',(SELECT menu_id FROM tamenu WHERE menu_no='AP400'),(SELECT page_id FROM tapage WHERE file_name='workRptWoPmwCmptRateList'),'310','N','P4Q0','MENU','N','AP4Q0','AP400','WEB','workRptWoPmwCmptRateList','N');


/** 2019-03-11 김영주 */
UPDATE TAMENU SET description = '설비고장강도,도수율', key_no = 'BDDURFREQRATE' WHERE page_id = (SELECT page_id FROM TAPAGE WHERE file_name = 'assetRptBdIntensityList');


/** 2019-03-13 국도화학 반영 */
/** 2019-03-13 고려용접봉 반영 */
/** 2019-03-19 매일유업 반영 */


/** 2019-03-26 김정우 */

UPDATE TAMENU SET page_id = ( select page_id from tapage where file_name='PlandownListFragment')
                            , file_name='PlandownListFragment'
WHERE menu_no='PLANDOWNLOAD'
AND service_type='ANDROID';

UPDATE TAMENU SET page_id = ( select page_id from tapage where file_name='SearchOptionFragment')
                            , file_name='SearchOptionFragment'
WHERE menu_no='SEARCHOPTION'
AND service_type='ANDROID';

UPDATE TAMENU SET page_id = ( select page_id from tapage where file_name='WoReqListFragment')
                            , file_name='WoReqListFragment'
WHERE menu_no='WOREQLIST'
AND service_type='ANDROID';

UPDATE TAMENU SET page_id = ( select page_id from tapage where file_name='WoResListFragment')
                            , file_name='WoResListFragment'
WHERE menu_no='WOREQRECLIST'
AND service_type='ANDROID';

UPDATE TAMENU SET page_id = ( select page_id from tapage where file_name='InspectionListFragment')
                            , file_name='InspectionListFragment'
WHERE menu_no='INSPECTION'
AND service_type='ANDROID';

UPDATE TAMENU SET page_id = ( select page_id from tapage where file_name='PmiRoutineListFragment')
                            , file_name='PmiRoutineListFragment'
WHERE menu_no='PMIROUTINE'
AND service_type='ANDROID';

UPDATE TAMENU SET page_id = ( select page_id from tapage where file_name='PmiDayListFragment')
                            , file_name='PmiDayListFragment'
WHERE menu_no='PMIDAY'
AND service_type='ANDROID';

UPDATE TAMENU SET page_id = ( select page_id from tapage where file_name='PmiPatrolListFragment')
                            , file_name='PmiPatrolListFragment'
WHERE menu_no='PMIPATROL'
AND service_type='ANDROID';

UPDATE TAMENU SET page_id = ( select page_id from tapage where file_name='PmworkListFragment')
                            , file_name='PmworkListFragment'
WHERE menu_no='PMWORK'
AND service_type='ANDROID';

UPDATE TAMENU SET page_id = ( select page_id from tapage where file_name='CalListFragment')
                            , file_name='CalListFragment'
WHERE menu_no='CALIBRATION'
AND service_type='ANDROID';

UPDATE TAMENU SET page_id = ( select page_id from tapage where file_name='UnplanListFragment')
                            , file_name='UnplanListFragment'
WHERE menu_no='UNPLANWORK'
AND service_type='ANDROID';

UPDATE TAMENU SET page_id = ( select page_id from tapage where file_name='IssListFragment')
                            , file_name='IssListFragment'
WHERE menu_no='PTISS'
AND service_type='ANDROID';

UPDATE TAMENU SET page_id = ( select page_id from tapage where file_name='StockListFragment')
                            , file_name='StockListFragment'
WHERE menu_no='CURRSTOCK'
AND service_type='ANDROID';

UPDATE TAMENU SET page_id = ( select page_id from tapage where file_name='StktakeHdrListFragment')
                            , file_name='StktakeHdrListFragment'
WHERE menu_no='PTADJ'
AND service_type='ANDROID';

UPDATE TAMENU SET page_id = ( select page_id from tapage where file_name='AssetListFragment')
                            , file_name='AssetListFragment'
WHERE menu_no='ASSETS'
AND service_type='ANDROID';

UPDATE TAMENU SET page_id = ( select page_id from tapage where file_name='UploadFragment')
                            , file_name='UploadFragment'
WHERE menu_no='UPLOAD'
AND service_type='ANDROID';

UPDATE TAMENU SET page_id = ( select page_id from tapage where file_name='PmiPartListFragment')
                            , file_name='PmiPartListFragment'
WHERE menu_no='PMIPART'
AND service_type='ANDROID';


INSERT INTO TAMENU (MENU_ID, DESCRIPTION, P_MENU_ID, PAGE_ID, ORD_NO, PARAM, IS_SYSTEM, KEY_NO, KEY_TYPE, IS_USE, MENU_NO, P_MENU_NO, SERVICE_TYPE, REMARK, FILE_NAME, IS_EXTERNAL_LINK, EXTERNAL_LINK, IS_GET_LINK)
VALUES (NEXT VALUE FOR SQAMENU_ID,'Mobile Dreamo',0,null,'001','','N','DREAMO','MENU','Y','DREAMO',null,'ANDROID','','','','','');

UPDATE TAMENU SET P_MENU_ID = (select menu_id from tamenu where service_type='ANDROID' and menu_no='DREAMO') WHERE service_type='ANDROID' AND menu_no IN ('DOWNLOADMNG','WOREQMNG','WOMNG','PTMNG','ASSETMNG','UPLOADMNG');

/** 2019-04-04 매일유업 반영 */
/** 2019-04-15 고려용접봉 반영 */
/** 2019-04-17 국도화학 반영 */

/** 2019-04-17 김은아 */
update TAMENU set CRE_DATE = '20190327132010';
update TAMENU set UPD_DATE = '20190327132010';

/** 2019-04-18 매일유업 반영 */
/** 2019-04-26 매일유업 반영 */


/** 2019-04-26 이지수 */
INSERT INTO TAMENU(MENU_ID, P_MENU_NO, P_MENU_ID, DESCRIPTION, PAGE_ID,ORD_NO,PARAM,IS_SYSTEM, KEY_TYPE, KEY_NO, IS_USE,SERVICE_TYPE,MENU_NO, FILE_NAME) VALUES( next value for SQAMENU_ID,'AP400',(SELECT x.menu_id FROM TAMENU x WHERE menu_id = (SELECT x.menu_id FROM TAMENU x WHERE menu_no = 'AP400')),'부서별작업스케쥴',(SELECT page_id FROM TAPAGE WHERE file_name = 'workListDeptSchedList'),'420','','N','MENU','AP421','N','WEB','AP421','workListDeptSchedList'); 


/** 2019-04-30 이근환 */
INSERT INTO tamenu(menu_id, menu_no,description, menu_lvl, p_menu_no, p_menu_id, page_id, file_name, ord_no, param, is_system, key_type, key_no, basic_key_type, basic_key_no,is_use, service_type, site_type, version_info, remark)  
VALUES(NEXT VALUE FOR sqamenu_id, 'AQ125','화면권한설정','2','AQ000',isnull((SELECT menu_id FROM tamenu WHERE menu_no='AQ000'),0),isnull((SELECT page_id FROM tapage WHERE file_name='mgrUsrGrpPageAuthList'),0),'mgrUsrGrpPageAuthList','125','','N','MENU','AQ125','MENU','AQ125','N','WEB','DREAM','2.01',''); 



/** 2019-04-15 이근환 */
INSERT INTO tamenu(menu_id, menu_no,description, p_menu_no, page_id, file_name, ord_no, param, is_system, key_type, key_no,is_use, service_type, remark)  
VALUES(NEXT VALUE FOR sqamenu_id, 'AP422','작업실행일정(주간)','AP400',isnull((SELECT page_id FROM tapage WHERE file_name='workListWeekWoList'),0),'workListWeekWoList','410','','N','MENU','AP422','N','WEB','');      

/** 2019-05-08 매일유업 반영 */
/** 2019-05-10 국도화학 반영 */


/** 2019-04-29 양소영 */
INSERT INTO TAMENU(MENU_ID, MENU_NO,DESCRIPTION, MENU_LVL, P_MENU_NO, PAGE_ID, FILE_NAME, ORD_NO, PARAM, IS_SYSTEM, KEY_TYPE, KEY_NO, BASIC_KEY_TYPE, BASIC_KEY_NO,IS_USE, SERVICE_TYPE, SITE_TYPE, VERSION_INFO, REMARK) VALUES( NEXT VALUE FOR SQAMENU_ID, 'AE130','에너지값 등록','2','AE000',isnull((SELECT page_id FROM TAPAGE WHERE file_name='workListEnergyMstrList'),0),'workListEnergyMstrList','130','','N','MENU','AE130','MENU','AE130','N','WEB','','','일별 또는 주,월별로 측정항목별로 사용량을 측정하고 등록'); 


/** 2019-05-14 노정현 */
insert into taehmenu(ehmenu_id, ehmenu_no, description, p_ehmenu_no, page_id, file_name, ord_no, param, is_basic, key_type, key_no, is_use, remark)  
values(NEXT VALUE FOR SQAMENU_ID, 'EA135','권한설정','EA000',ISNULL((select page_id from tapage where file_name='consultCompUsrGrpList'),0),'consultCompUsrGrpList','135','','N','MENU','EA135','Y',''); 

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


/** 2019-07-16 김남현 */
INSERT INTO tamenu(menu_id, menu_no,description, menu_lvl, p_menu_id, p_menu_no, page_id, file_name, ord_no, param, is_system, key_type, key_no, basic_key_type, basic_key_no,is_use, service_type, site_type, version_info, remark)  VALUES(next value for sqamenu_id, 'AP592','정기점검항목결과','3',(SELECT menu_id FROM tamenu WHERE menu_no = 'AP500'), 'AP500',isnull((SELECT page_id FROM tapage WHERE file_name='workRptPmiPointValueList'),0),'workRptPmiPointValueList','200','','N','MENU','AP592','MENU','AP592','N','WEB','','','');

/** 2019-07-25 경보제약 반영 */
/** 2019-07-30 매일유업 반영 */
/** 2019-08-07 경보제약 반영 */


/** 2019-08-07 김남현 */
insert into tamenu(menu_id, menu_no,description, menu_lvl, p_menu_id, p_menu_no, page_id, file_name, ord_no, param, is_system, key_type, key_no, basic_key_type, basic_key_no,is_use, service_type, site_type, version_info, remark)  values(next value for sqamenu_id, 'AD800','Alarm','2',(SELECT menu_id FROM tamenu WHERE menu_no = 'AD000'), 'AD000',isnull((select page_id from tapage where file_name=''),0),'','170','','N','MENU','AD800','MENU','AD800','N','WEB','','',''); 
insert into tamenu(menu_id, menu_no,description, menu_lvl, p_menu_id, p_menu_no, page_id, file_name, ord_no, param, is_system, key_type, key_no, basic_key_type, basic_key_no,is_use, service_type, site_type, version_info, remark)  values(next value for sqamenu_id, 'AD810','설비Alarm','3',(SELECT menu_id FROM tamenu WHERE menu_no = 'AD800'), 'AD800',isnull((select page_id from tapage where file_name='workAlarmList'),0),'workAlarmList','110','','N','MENU','AD810','MENU','AD810','N','WEB','','',''); 


/** 2019-08-09 경보제약 반영 */
/** 2019-08-12 경보제약 반영 */


/** 2019-07-11 이근환 */
INSERT INTO taehmenu(ehmenu_id,ehmenu_no,description,p_ehmenu_id,p_ehmenu_no,page_id,file_name,ord_no,is_basic,is_use,key_type,key_no)
VALUES(next value for sqaehmenu_id,'EB310','프로그램Setting값업로드',(SELECT ehmenu_id FROM taehmenu WHERE ehmenu_no IN('CONSULT_SETTING','EB000')),(SELECT ehmenu_no FROM taehmenu WHERE ehmenu_no IN('CONSULT_SETTING','EB000')),(SELECT page_id FROM tapage WHERE file_name='consultPgmSettingUpdList'),'consultPgmSettingUpdList','99996','Y','Y','MENU','EB310');

/** 2019-08-13 경보제약 반영 */
/** 2019-08-19 경보제약 반영 */
/** 2019-08-22 경보제약 반영 */
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
/** 2019-10-29 경보제약 반영 */
/** 2019-11-05 본사 국도화학 반영 */
/** 2019-11-05 고려용접봉 반영 */
/** 2019-11-06 국도화학 반영 */

/** 2019-11-12 김정우 */
INSERT INTO tamenu(menu_id, menu_no,description, menu_lvl, p_menu_no, page_id, file_name, ord_no, param, is_system, key_type, key_no, basic_key_type, basic_key_no,is_use, service_type, site_type, version_info, remark)  VALUES(NEXT value FOR sqamenu_id, 'CA900','설정',1,'CA000',isnull((SELECT page_id FROM tapage WHERE file_name=''),0),'','300','','N','MENU','CA900','MENU','CA900','Y','ANT','DREAM','2.00',''); 
INSERT INTO tamenu(menu_id, menu_no,description, menu_lvl, p_menu_no, page_id, file_name, ord_no, param, is_system, key_type, key_no, basic_key_type, basic_key_no,is_use, service_type, site_type, version_info, remark)  VALUES(NEXT value FOR sqamenu_id, 'CB900','설정',1,'CB000',isnull((SELECT page_id FROM tapage WHERE file_name=''),0),'','110','','N','MENU','CB900','MENU','CB900','Y','BEE','DREAM','2.00',''); 
UPDATE tamenu set key_no = 'CA910' , menu_no = 'CA910' , basic_key_type='MENU', basic_key_no = 'CA910' , p_menu_no = 'CA900'  WHERE service_type ='ANT' AND file_name ='AntInitialDownFragment' AND key_no ='CA130';
UPDATE tamenu set key_no = 'CB910' , menu_no = 'CB910' , basic_key_type='MENU', basic_key_no = 'CB910' , p_menu_no = 'CB900'  WHERE service_type ='BEE' AND file_name ='BeeInitialDownFragment' AND key_no ='CB110';
UPDATE tamenu set key_no = 'CB110' , menu_no = 'CB110' , basic_key_type='MENU', basic_key_no = 'CB110' WHERE service_type ='BEE' AND file_name ='BeeSearchOptionFragment' AND key_no ='CB120';

/** 2019-11-14 고려용접봉 반영 */
/** 2019-11-20 매일유업 반영 */
/** 2019-12-02 매일유업 반영 */
/** 2019-12-13 한국내화 반영 */
/** 2019-12-20 고려용접봉 반영 */
/** 2020-01-03 azdream - AZURE CLOUD DREAM2 반영 */
/** 2020-01-09 고려용접봉 반영*/
/** 2020-01-09 매일유업 반영 */
/** 2020-01-14 한국내화 kref 반영 */
/** 2020-01-17 매일유업 반영 */

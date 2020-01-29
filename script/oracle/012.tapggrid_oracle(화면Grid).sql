---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-11   박철완 최초 생성*/ 
---------------------------------------------------------------------------------------------------------------------------------------------------

  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovAssetPopup') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovButtonPopup') ,'gridbox','버튼 리스트','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovCtCtrPopup') ,'gridbox','CP 리스트','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovDeptPopup') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovEmpPopup') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovEqAppPopup') ,'gridbox','설비기안 품의서 목록 팝업','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovEqAsmbPopup') ,'gridbox','설비부위 팝업 리스트','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovEqCtgAsmbPopup') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovEqCtgPopup') ,'gridbox','설비종류 리스트','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovEqLocPopup') ,'gridbox','위치분류 리스트','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovEquipPopup') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovLangPopup') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovMenuPopup') ,'gridbox','메뉴 리스트 팝업','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovPagePopup') ,'gridbox','페이지 리스트','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovPartsPopup') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovPtAppPopup') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovSysDirPopup') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovTablePopup') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovUserPopup') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovUsrDirPopup') ,'gridbox','사용자 코드 팝업','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovVendorPopup') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovWhPopup') ,'gridbox','사용창고 리스트','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maBatchMngList') ,'gridbox','배치작업 프로시져 목록','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maBdPointList') ,'gridbox','이상점검조치 목록 ','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maBmActList') ,'gridbox','고장조치작업 리스트','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maBmEqList') ,'gridbox','설비별고장분석 리스트','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maBtnMngList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maCdSysCodeList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maCdSysList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maCdUsrCdList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maCdUsrList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maConfigList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maDeptList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maDocCntrCdList') ,'gridbox','일반자료실 목록','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maDocCntrEcList') ,'gridbox','동종기계정보 목록','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maDocImgList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maDocLibList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maEmpList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maEqCatalogList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maEqCtgAsmbList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maEqCtgPartList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maEqLocList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maEqMngList') ,'gridbox','설비담당자별 설비 조회','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maEqMstrAsmbList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maEqMstrAssetList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maEqMstrHistList') ,'gridbox','설비변경이력 목록','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maEqMstrList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maEqMstrPartList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maEqMstrSpecList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maEqMstrVendorList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maEqNotesAppList') ,'gridbox','설비기안 리스트 ','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maFailureList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maGrdMngColList') ,'gridbox','설명 ','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maGrdMngList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maGrdUsrColList') ,'gridbox','사용자 화면 컬럼 정의 페이지','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maLangMngList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maLinkMenuList') ,'gridbox','유저 연결메뉴 목록','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maMailList') ,'gridbox','메일수신자 설정 리스트','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maMailUsrList') ,'gridbox','메일 수신자 리스트','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maMenuMngList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maMyMenuList') ,'gridbox','사용자메뉴','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPgMngBtnList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPgMngList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPgMngPageList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPmMstrList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPmMstrPartList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPmMstrPointList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPmYearPtSchedList') ,'dategridbox','연간부품사용일정 일자별 목록','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPmYearPtSchedList') ,'gridbox','연간부품사용일정','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPmYearPtSchedList') ,'partsgridbox','연간부품사용일정 부품별','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPtBudgetList') ,'gridbox','예산계획','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPtDeptBgList') ,'gridbox','부서별 예산금액 목록','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPtMstrEqPartList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPtMstrList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPtMstrUsedDeptList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPtMstrVendorList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPtRecList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPtRecStatList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPtRepAppList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPtRepList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPtRepStatList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPtStckList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPtWhList') ,'gridbox','부품창고 목록','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maSesMngList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maUserList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maUsrGrpAuthList') ,'gridbox','권한 설정 ','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maUsrGrpList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maVendorList') ,'gridbox','관련업체를 확인하는 리스트 box','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoLinepfDayList') ,'gridbox','일별 라인가동 내역 목록','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoLinepfList') ,'gridbox','라인가동내역 목록','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoMonthSchedList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoMonthWoList') ,'gridbox','작업결과 리스트','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoPtHistList') ,'gridbox','자재사용이력 목록','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultCraftList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultMstrList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPartList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPointList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoYearSchedList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maStdPointList') ,'gridbox','','10'); 
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-12   김정우 추가작업 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO TAPGGRID(pggrid_id,page_id,grid_obj_id,description)
values(SQAPGGRID_ID.nextval,(select page_id FROM TAPAGE WHERE file_name='maEqBmList'),'gridbox','설비고장내역 리스트');
INSERT INTO TAPGGRID(pggrid_id,page_id,grid_obj_id,description)
values(SQAPGGRID_ID.nextval,(select page_id FROM TAPAGE WHERE file_name='maPmRepList'),'gridbox','예방수리내역 리스트');
INSERT INTO TAPGGRID(pggrid_id,page_id,grid_obj_id,description)
values(SQAPGGRID_ID.nextval,(select page_id FROM TAPAGE WHERE file_name='maPmPointList'),'gridbox','예방점검내역 리스트');
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-12   김정우 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-12   노정현 추가작업 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO TAPGGRID(PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION) 
VALUES(SQAPGGRID_ID.NEXTVAL,  (SELECT page_id FROM TAPAGE WHERE file_name='maNstGrpList'), 'gridbox', '무정지대표라인');
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-12   노정현 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-16   김정우 추가작업 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO TAPGGRID(pggrid_id,page_id,grid_obj_id,description)
values(SQAPGGRID_ID.nextval,(select page_id FROM TAPAGE WHERE file_name='maStdPointHdrList'),'gridbox','예방작업표준항목 리스트');
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-16   김정우 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-17   노정현 추가작업 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO TAPGGRID
   (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, HEIGHT)
 VALUES
   (SQAPGGRID_ID.NEXTVAL, (SELECT page_id  FROM TAPAGE WHERE file_name ='maEqDocLibList'), 'gridbox', '10');
INSERT INTO TAPGGRID
   (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, HEIGHT)
 VALUES
   (SQAPGGRID_ID.NEXTVAL, (SELECT page_id  FROM TAPAGE WHERE file_name ='maWoDocLibList'), 'gridbox', '10');
INSERT INTO TAPGGRID
   (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, HEIGHT)
 VALUES
   (SQAPGGRID_ID.NEXTVAL, (SELECT page_id  FROM TAPAGE WHERE file_name ='maPtDocLibList'), 'gridbox', '10');
INSERT INTO TAPGGRID
   (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, HEIGHT)
 VALUES
   (SQAPGGRID_ID.NEXTVAL, (SELECT page_id  FROM TAPAGE WHERE file_name ='maDocImgPopList'), 'gridbox', '10');
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-17   노정현 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-19   김정우 추가작업 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------

 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovUsrGrpPopup') ,'gridbox','권한 리스트 팝업','10'); 
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-19   김정우 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-24   김정우 추가작업 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultStPointList') ,'gridbox','','10'); 
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-24   김정우 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-26   김정우 추가작업 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maQnaList') ,'gridbox','','10'); 

 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maQnaAnsList') ,'gridbox','','10'); 
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-26   김정우 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-30   김정우 추가작업 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPtBuyReqHdrList') ,'gridbox','','10'); 
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-30   김정우 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-31   이규선 추가작업 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------
  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPtPurReqList') ,'gridbox','현장구매신청 리스트',''); 

---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-31   이규선 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------=======
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-06-01   김정우 구매요청 item 페이지 그리드 추가작업 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------
  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPtBuyReqList') ,'gridbox','구매신청 item 리스트',''); 

---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-06-01   김정우 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------=======
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-06-02   김정우 자재검색팝업2 추가작업시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------=======
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovReqPartsPopup') ,'gridbox','','10'); 
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-06-02   김정우 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------=======
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-06-09   김정우 월간작업일정 작업결과 상세 탭페이지 그리드 추가작업 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------=======
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultMonthCraftList') ,'gridbox','','10'); 

 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultMonthPartList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultMonthPointList') ,'gridbox','','10');
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultMonthStPointList') ,'gridbox','','10'); 
INSERT INTO TAPGGRID
   (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, HEIGHT)
 VALUES
   (SQAPGGRID_ID.NEXTVAL, (SELECT page_id  FROM TAPAGE WHERE file_name ='maWoMonthDocLibList'), 'gridbox', '10');
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-06-09   김정우 월간작업일정 작업결과 상세 탭페이지 그리드 추가작업 끝 */
---------------------------------------------------------------------------------------------------------------------------------------------------=======
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-06-13   김정우 예방작업 관련 추가작업 시작 */ 
-------------------------------------------------------------------------------------------------------------------------------------------
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPmEqClnList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPmEqOilList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPmOilPartList') ,'gridbox','','10'); 
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-06-13   김정우 예방작업 관련 추가작업 끝 */ 
-------------------------------------------------------------------------------------------------------------------------------------------

/** 2016-06-14 노정현 일일작업일지확인 그리드 추가 */  
INSERT INTO TAPGGRID
   (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
 VALUES
   (SQAPGGRID_ID.NEXTVAL, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoDailyList'), 'gridbox', '일일작업일지확인 목록', '10');
 
/** 2016-06-15 김정우 작업결과 작업자,점검항목,사용부품 그리드 추가 */  
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmInsCraftList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmInsPointList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmRprCraftList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmRplCraftList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmRplPartList') ,'gridbox','','10'); 
/** 2016-06-15 김정우 작업결과 작업자,점검항목,사용부품 그리드 추가 끝*/  
/** 2016-06-16 김정우 작업결과 작업자,점검항목,사용부품 그리드 추가 시작*/  
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultClnList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmClnCraftList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultOilList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmOilCraftList') ,'gridbox','','10'); 
/** 2016-06-16 김정우 작업결과 작업자,점검항목,사용부품 그리드 추가 끝*/  
/** 2016-06-20 김정우 작업결과 작업자,점검항목,사용부품 그리드 추가 시작*/  
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmRprCraftList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmRplCraftList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmRplPartList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmOilCraftList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmOilPartList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultCmRprCraftList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultCmRplCraftList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultCmRplPartList') ,'gridbox','','10'); 
/** 2016-06-20 김정우 작업결과 작업자,점검항목,사용부품 그리드 추가 끝*/  

/** 2016-06-22 노정현 결재페이지 관련 추가  */

INSERT INTO TAPGGRID
   (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
 VALUES
   (SQAPGGRID_ID.NEXTVAL, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appPrcList') , 'gridbox', '결재자 목록', '10');
INSERT INTO TAPGGRID
   (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
 VALUES
   (SQAPGGRID_ID.NEXTVAL,  (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList'), 'gridbox', '결재자 이력 목록', '5');
INSERT INTO TAPGGRID
   (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
 VALUES
   (SQAPGGRID_ID.NEXTVAL,  (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppLineList'), 'gridbox', '결재선 목록', '10');
INSERT INTO TAPGGRID
   (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
 VALUES
   (SQAPGGRID_ID.NEXTVAL, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppLineUsrList'), 'gridbox', '결재선 유저 목록', '5');
INSERT INTO TAPGGRID
   (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
 VALUES
   (SQAPGGRID_ID.NEXTVAL,  (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppLinePopupList'), 'gridbox', '결재선 목록 팝업', '10');
INSERT INTO TAPGGRID
   (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
 VALUES
   (SQAPGGRID_ID.NEXTVAL, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppLineUsrPopupList'), 'gridbox', '결재선 유저 목록 팝업', '5');
   
   INSERT INTO TAPGGRID
   (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
 VALUES
   (SQAPGGRID_ID.NEXTVAL, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReadyList'), 'gridbox', '결재대기 목록', '10');
   
   /** 2016-06-28 김정우 무상입고 그리드 추가 */
   
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPtFcRecList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPgMngFieldList') ,'gridbox','','10');



/** 2016-06-29 노정현 월별보전목표 그리드 추가 */
INSERT INTO TAPGGRID
   (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
 VALUES
   (SQAPGGRID_ID.NEXTVAL, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maMtGoalList'), 'gridbox', '월별보전목표 목록', '10');
/** 2016-06-30 김정우 라인가동계획 그리드 추가 */
INSERT INTO TAPGGRID
   (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
 VALUES
   (SQAPGGRID_ID.NEXTVAL, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maLineRunPlanList'), 'gridbox', '라인가동계획 목록', '10');

INSERT INTO TAPGGRID
   (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
 VALUES
   (SQAPGGRID_ID.NEXTVAL, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maLnGoalList'), 'gridbox', '라인별투자목표금액 목록', '10');

/** 2016-07-06 김정우 작업결과 교육자 그리드 추가 */
   
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultTrEleCraftList') ,'gridbox','','10'); 
/** 2016-07-07 김정우 설비구성품 그리드 추가 */

 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maEqMstrItemsList') ,'gridbox','','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovMesLinePopup') ,'gridbox','','10');


/** 2016-07-18 부품출고 확정  */
INSERT INTO TAPGGRID
   (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
 VALUES
   (SQAPGGRID_ID.NEXTVAL, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPtIssList'), 'gridbox', '부품출고확정 목록', '10');

INSERT INTO TAPGGRID
   (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
 VALUES
   (SQAPGGRID_ID.NEXTVAL, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'lovWoPopup'), 'gridbox', '작업검색 팝업', '10');
/** 2016-07-27 김정우 월별재고목표  추가 */
INSERT INTO TAPGGRID
   (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
 VALUES
   (SQAPGGRID_ID.NEXTVAL, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maMonthStockGoalList'), 'gridbox', '월별재고목표 그리드', '10');/** 2016-07-27 김정우 월별재고목표 버튼 추가 */
/** 2016-07-28 김정우 Working Calendar  추가 */
   INSERT INTO TAPGGRID
   (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
 VALUES
   (SQAPGGRID_ID.NEXTVAL, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoCalList'), 'gridbox', 'Working Calendar 그리드', '10');
   /** 2016-08-04 이규선  연간BD지표  추가 */
      INSERT INTO TAPGGRID
   (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
 VALUES
   (SQAPGGRID_ID.NEXTVAL, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBmYearChart'), 'gridbox', 'Line time 그리드', '10');
   
         INSERT INTO TAPGGRID
   (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
 VALUES
   (SQAPGGRID_ID.NEXTVAL, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBmYearChart'), 'rategridbox', 'Line B/D Rate 그리드', '20');
   /** 2016-08-12 김정우 일일작업일지확인 - 작업목록  추가 */
      INSERT INTO TAPGGRID
   (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
 VALUES
   (SQAPGGRID_ID.NEXTVAL, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoDailyWoList'), 'gridbox', '일일작업확인 작업목록 그리드', '10');
      /** 2016-08-17 이규선 사용자메뉴 팝업   추가 */
    INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maMyMenu') ,'gridbox','권한 설정 ','10');
/** 2016-08-23 이규선 사용자메뉴 팝업   추가 */
    INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maMyLink') ,'gridbox','권한 설정 ','10'); 
/** 2016-08-23 이규선 공기구마스터   추가 */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPttMstrList') ,'gridbox','','10'); 

 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPttStckList') ,'gridbox','공기구재고 그리드','10'); 

 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovWhToolPopup') ,'gridbox','공기구창고 리스트','10'); 

 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovToolsPopup') ,'gridbox','','10');

 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPttRecList') ,'gridbox','공기구입고 그리드','10'); 

  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPttIssList') ,'gridbox','공기구출고 그리드','10');

  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPttRtnList') ,'gridbox','공기구반납 그리드','10');

  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPttRentList') ,'gridbox','공기구대여그리드','10');

  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPttDisList') ,'gridbox','공기구폐기 그리드','10');

  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPttDisPartsList') ,'gridbox','공기구폐기자재 그리드','10');
/** 2016-09-06 박철완 추가 */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPlantMngList') ,'gridbox','','10'); 

 
  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
  VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maIfPtIssList') ,'gridbox','부품출고확정 Interface','10'); 
  /** 2016-09-07 김정우 부품마스터 Interface*/
  
     INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
  VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maIfPtMstrList') ,'gridbox','부품마스터 Interface','10'); 
  /** 2016-09-07 김정우 구매청구 Interface */
    INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
  VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maIfPtPoList') ,'gridbox','구매청구 Interface','10'); 
  /**2016-09-07 김정우 부품출고처리내역 Interface */
     INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
  VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maIfPtIssErpList') ,'gridbox','부품출고처리내역 Interface','10'); 
  /**2016-09-07 김정우 재고 Interface */
    INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
  VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maIfPtStockList') ,'gridbox','재고 Interface','10'); 
    /**2016-09-08 이규선 예방작업일정 */
      INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
  VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoSchedList') ,'gridbox','예방작업일정 그리드','10'); 
   /**2016-09-07 김정우 MES 가동시간 Interface */
    INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
  VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maIfMesRunList') ,'gridbox','MES가동시간 Interface','10'); 
  
     /**2016-09-07 김정우 MES 정지시간 Interface */
    INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
  VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maIfMesStopList') ,'gridbox','MES정지시간 Interface','10'); 
  
   INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPgUsrFieldList') ,'gridbox','유저화면설정','10'); 

 /** 2016-09-12 김정우 주간작업일정 추가 */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoWeekWoList') ,'gridbox','주간작업일정 리스트','10'); 

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqBdChart') ,'gridbox','설비별 BD 현황','10');
 
   /** 2016-09-21 이규선 월간BD라인 추가 */
  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBmMonthLineChart') ,'gridbox','월간 BD 현황','10');
   INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBmMonthLineChart') ,'detailgridbox','월간 BD 현황 Detail','10');
    INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBmMonthLineChart') ,'dailygridbox','월간 BD 현황 Daily','10')
 
   INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maDbList') ,'gridbox','Dashboard List','10'); 
  /** 2016-10-10 김정우 작업그룹 LOV POPUP 추가 */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovWkCtrPopup') ,'gridbox','작업그룹  리스트','10'); 

    /** 2016-10-13 김정우 그리드 추가 */
  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPtPoList') ,'gridbox','발주이력  리스트','10'); 

     /** 2016-10-17 김정우 그리드 추가 */
  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWkCtrList') ,'gridbox','작업그룹  리스트','10'); 

     /** 2016-10-21 노정현 그리드 추가 */
  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovPlantPopup') ,'gridbox','플랜트(Plant)','10'); 

/** 2016-10-24 노정현 그리드 추가 */
  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPtIssEmgList') ,'gridbox','긴급출고','10'); 
/** 2016-10-24 김정우  추가 */
  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maResList') ,'gridbox','언어 리스트','10'); 
  
/** 2016-10-26 노정현  추가 */
  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovEmgPartPopup') ,'gridbox','긴급출고 Popup','5'); 
    
  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maIfPtRecList') ,'gridbox','ERP S/Parts Rec','10'); 

/** 2016-10-28 김정우 금형관리 추가 */
  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maMoldsList') ,'gridbox','금형관리 리스트','5'); 

/** 2016-11-01 이규선 라인고장율  추가 */
  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maMonitorPopup') ,'detailgridbox','라인별 고장율 팝업','5'); 

/** 2016-11-03 노정현 HelpDesk 추가 */
  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maHelpList') ,'gridbox','HelpDesk','10'); 


/** 2016-11-16 노정현 개선진행현황 추가 */
  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWrkImpList') ,'gridbox','개선진행현황[Imp.Progress Status]','10'); 
/** 2016-11-22 김정우  추가 */
   INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maGlStockList') ,'gridbox','공장별 현재고','10'); 

/** 2017-01-05 김정우 작업시간 설정 */

    INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maLineTimeList') ,'gridbox','라인가동설정 라인리스트','10'); 
    INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maLineTimeDowList') ,'gridbox','라인가동설정 요일별 리스트','10'); 

/**2017-01-10 김정우 추가 */

    INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoDailyActList') ,'gridbox','일일작업-Main Activities','10'); 

-----------------------------------------------------------------------------
--------------------------------유한킴벌리 patch완료 2017.01.13일-------------------
-----------------------------------------------------------------------------

/** 2017-01-18 김정우 작업요청 추가 */
        INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoReqList') ,'gridbox','작업요청 리스트 ','10'); 
        INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoReqResList') ,'gridbox','처리사항 리스트 ','10'); 

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, HEIGHT)
 VALUES (SQAPGGRID_ID.NEXTVAL, (SELECT page_id  FROM TAPAGE WHERE file_name ='maWoReqDocLibList'), 'gridbox', '10');
   
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, HEIGHT)
 VALUES (SQAPGGRID_ID.NEXTVAL, (SELECT page_id  FROM TAPAGE WHERE file_name ='maWoReqResDocLibList'), 'gridbox', '10');
   
/** 2017-01-25 김정우 추가 */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, HEIGHT)
 VALUES (SQAPGGRID_ID.NEXTVAL, (SELECT page_id  FROM TAPAGE WHERE file_name ='maWoDayRptList'), 
'gridbox', '10');
 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, HEIGHT)
 VALUES (SQAPGGRID_ID.NEXTVAL, (SELECT page_id  FROM TAPAGE WHERE file_name ='maWoDayRptWoList'), 
'gridbox', '10');
 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, HEIGHT)
 VALUES (SQAPGGRID_ID.NEXTVAL, (SELECT page_id  FROM TAPAGE WHERE file_name 
='maWoDayRptDocLibList'), 'gridbox', '10');
/** 2017-03-02 김정우 추가 */

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, HEIGHT)
 VALUES (SQAPGGRID_ID.NEXTVAL, (SELECT page_id  FROM TAPAGE WHERE file_name 
='maWoTopFiveList'), 'gridbox', '10');


/*** 2017-03-09  노정현 추가 */

              
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, HEIGHT)
 VALUES (SQAPGGRID_ID.NEXTVAL, (SELECT page_id  FROM TAPAGE WHERE file_name ='maWrkImpCnDocList'), 'gridbox', '10');
 
  -----------------------------------------------------------------------------
--------------------------------유한킴벌리 patch완료 2017.03.15일-------------------
-----------------------------------------------------------------------------
 
 
 /** 2017-03-21 노정현 추가  */
 
  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mcMenuList') ,'gridbox','','10'); 


 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mcUserList') ,'gridbox','','10'); 

   

/** 2017-03-24 김정우 추가 */

 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPgMngGrdList') ,'gridbox',' ','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPgMngGrdColList') ,'gridbox','','10'); 


/** 2017-04-04 이규선 추가 */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maTableList') ,'gridbox',' ','10');


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
-----------------------------------------------------------------------------

/** 2017-04-07 이규선 추가 */
   INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maTableColList') ,'gridbox',' ','10');
-----------------------------------------------------------------------------


/** 2017-04-07 노정현 추가 */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'mcDataSelectList') ,'gridbox','사용자데이터 조회','10');

/** 2017-04-10 이규선 추가 */
    INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovRefColumnPopup') ,'gridbox','데이터 테이블','8'); 

    INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovRefColumnPopup') ,'columngridbox','데이터 컬럼 테이블','8');


-----------------------------------------------------------------------------
--------------------------------유한킴벌리 patch완료 2017.04.12일-------------------
-----------------------------------------------------------------------------

/** 2017-05-17 노정현 추가 */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maUserRptList') ,'gridbox','사용자레포트 조회','10');

 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maUserRptTableList') ,'gridbox','사용자레포트 테이블','3');

 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maUserRptJoinList') ,'gridbox','사용자레포트 조인','3');

 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maUserRptColList') ,'gridbox','사용자레포트 컬럼','5');

 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maUserRptParamList') ,'gridbox','사용자레포트 조건','5');

 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maUserRptOrdList') ,'gridbox','사용자레포트 정렬','3');


/** 2017-07-04 이규선  */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'partAdjStkTakeList') ,'gridbox','부품실사 목록','10');


/** 2017-07-04 박철완  */
insert into tapggrid(pggrid_id, page_id, grid_obj_id,description, height)
values(sqapggrid_id.nextval, (select page_id from tapage where file_name = 'maEqMstrMoldList'), 'gridbox','금형목록 리스트','10')
;

/*금형생산 품목 리스트 페이지에 그리드 추가.*/
insert into tapggrid(pggrid_id, page_id, grid_obj_id,description, height)
values(sqapggrid_id.nextval, (select page_id from tapage where file_name = 'maEqMstrMoldProdList'), 'gridbox','금형생산품목 리스트','10')
;

/*금형갱신이력 리스트 페이지에 그리드 추가.*/
insert into tapggrid(pggrid_id, page_id, grid_obj_id,description, height)
values(sqapggrid_id.nextval, (select page_id from tapage where file_name = 'maEqMstrMoldModHistList'), 'gridbox','금형갱신이력 리스트','10')
;



/** 2017-07-05 차한결 추가 */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'assetListTcycleList') ,'gridbox','교정주기 List','');

/** 2017-07-06 이규선 추가 */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'partAdjStkTakeItemList') ,'gridbox','부품 List','');


/** 2017-07-07 차한결 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workListCavalList') ,'gridbox','측정값List','10'); 

/** 2017-07-11 김정우 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'consultCompTerminalList') ,'gridbox','Access Terminal List Grid','10'); 

/** 2017-07-11 노정현 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'failLibraryList') ,'gridbox','고장Library','10'); 


/** 2017-07-11 차한결 추가 */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'workPmListEquipList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workPmListSchdList') ,'gridbox','','10');
 
/** 2017-07-12 이규선 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'orgEmpCertList') ,'gridbox','자격증 Grid','10'); 

  /** 2017-07-13 이규선 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'orgEmpTrainList') ,'gridbox','교육 Grid','10'); 

  /** 2017-07-17 이규선 추가 */
     INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'lovEduPopup') ,'gridbox','교육 리스트','8'); 

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'lovCertPopup') ,'gridbox','자격증 리스트','8'); 


INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'certList') ,'gridbox','자격증 리스트','10'); 
 


/** 2017-07-18 노정현 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'docCtgList') ,'gridbox','문서분류체계 Grid','10');

/** 2017-07-19 이규선 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'eduList') ,'gridbox','교육과정 리스트','10');

/** 2017-07-19 노정현 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'docCtgLov') ,'gridbox','문서분류체계 LOV Grid','10');

/** 2017-07-20 노정현 추가  */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maEqPartDocLibList') ,'gridbox','설비Part 첨부문서 Grid','10');

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maEqUtilityDocLibList') ,'gridbox','설비Util 첨부문서 Grid','10');

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maEqVehicleDocLibList') ,'gridbox','설비Vehicle 첨부문서 Grid','10');

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maEqBuildingDocLibList') ,'gridbox','설비Building 첨부문서 Grid','10');

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maEqToolDocLibList') ,'gridbox','설비Tool 첨부문서 Grid','10');

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maEqMoldDocLibList') ,'gridbox','설비Mold 첨부문서 Grid','10');

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maPtDocLibList') ,'gridbox','자재마스터 첨부문서 Grid','10');

  /** 2017-07-21 차한결 추가  */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'consultCompWrkcalList') ,'gridbox','근무일달력 목록','10');
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'consultCompListLov') ,'gridbox','회사 팝업','10');
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'consultCompWrkcalDowsetList') ,'gridbox','휴무요일 목록','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'consultCompWrkcalDaysetList') ,'gridbox','휴무일 목록','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'consultCompWrkcalLov') ,'gridbox','근무달력 팝업','10');
 
 /** 2017-07-21 노정현 추가  */
 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoDocLibList') ,'gridbox','Work 첨부문서 Grid','10');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultPmInsDocLibList') ,'gridbox','Work 첨부문서 Grid','10');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultPmRprDocLibList') ,'gridbox','Work PMRPR 첨부문서 Grid','10');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultPmRplDocLibList') ,'gridbox','Work PMRPL 첨부문서 Grid','10');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultPmClnDocLibList') ,'gridbox','Work PMCLN 첨부문서 Grid','10');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultPmOilDocLibList') ,'gridbox','Work PMOIL 첨부문서 Grid','10');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultBmRprDocLibList') ,'gridbox','Work BMRPR 첨부문서 Grid','10');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultBmRplDocLibList') ,'gridbox','Work PMRPL 첨부문서 Grid','10');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultBmOilDocLibList') ,'gridbox','Work BMOIL 첨부문서 Grid','10');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultCmRprDocLibList') ,'gridbox','Work CMRPR 첨부문서 Grid','10');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultCmRplDocLibList') ,'gridbox','Work CMRPL 첨부문서 Grid','10');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultTrEleDocLibList') ,'gridbox','Work TRELE 첨부문서 Grid','10');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultPmGmDocLibList') ,'gridbox','Work PMGM 첨부문서 Grid','10');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultPmCalDocLibList') ,'gridbox','Work PMCAL 첨부문서 Grid','10');

/** 2017-07-25 이규선 추가  */
  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmFfailList') ,'gridbox','기능고장 목록','10');
 
     INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmFfailItemList') ,'gridbox','기능고장 그리드','10');
 
 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmFfailDocLibList') ,'gridbox','RCM 기능고장 첨부문서 Grid','10');


 
   INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmFuncEqList') ,'gridbox','고장부위 목록','10');
 
     INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmFuncEqItemList') ,'gridbox','고장부위 그리드','10');
 
 
  /** 2017-07-29 박철완 추가 */
 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'certEmpList') ,'gridbox','자격증 보유사원','10');


INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'eduEmpList') ,'gridbox','교육이수 사원','10');


/** 2017-07-31 박철완 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workCalPmRInsYearList') ,'gridbox','예방점검리스트','10');

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workCalPmRInsMonthList') ,'gridbox','예방점검리스트','10');

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workCalPmRInsPeriodList') ,'gridbox','예방점검리스트','10');

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workCalPmDInsMonthList') ,'gridbox','예방점검리스트','10');

 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'certRptEmpList') ,'gridbox','자격증 보유현황','10');

 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'eduRptEmpList') ,'gridbox','교육이수이력','10');


/** 2017-07-31 이규선 추가 */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'lovRcmListPopup') ,'gridbox','System 분석 리스트','8'); 

/** 2017-08-01 노정현 추가 */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmFmeaList') ,'gridbox','FMEA','8'); 


/** 2017-08-02 노정현 추가  */

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmFmeaCrityList') ,'gridbox','FMEA Crity','8'); 

 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmFmeaDocLibList') ,'gridbox','RCM FMEA첨부문서 Grid','10');


  /** 2017-08-02 차한결 추가 */
INSERT INTO tapggrid 
SELECT 
SQAPGGRID_ID.NEXTVAL,
(SELECT page_id FROM tapage WHERE file_name = 'workPmListRprEquipList'),
x.grid_obj_id,x.description,x.height
 FROM tapggrid x, tapage z
WHERE z.page_id = x.page_id
AND z.file_name = 'workPmListEquipList';

INSERT INTO tapggrid 
SELECT 
SQAPGGRID_ID.NEXTVAL,
(SELECT page_id FROM tapage WHERE file_name = 'workPmListRplEquipList'),
x.grid_obj_id,x.description,x.height
 FROM tapggrid x, tapage z
WHERE z.page_id = x.page_id
AND z.file_name = 'workPmListEquipList';

INSERT INTO tapggrid 
SELECT 
SQAPGGRID_ID.NEXTVAL,
(SELECT page_id FROM tapage WHERE file_name = 'workPmListGmEquipList'),
x.grid_obj_id,x.description,x.height
 FROM tapggrid x, tapage z
WHERE z.page_id = x.page_id
AND z.file_name = 'workPmListEquipList';

INSERT INTO tapggrid 
SELECT 
SQAPGGRID_ID.NEXTVAL,
(SELECT page_id FROM tapage WHERE file_name = 'workPmListRInsEquipList'),
x.grid_obj_id,x.description,x.height
 FROM tapggrid x, tapage z
WHERE z.page_id = x.page_id
AND z.file_name = 'workPmListEquipList';

INSERT INTO tapggrid 
SELECT 
SQAPGGRID_ID.NEXTVAL,
(SELECT page_id FROM tapage WHERE file_name = 'workPmListDInsEquipList'),
x.grid_obj_id,x.description,x.height
 FROM tapggrid x, tapage z
WHERE z.page_id = x.page_id
AND z.file_name = 'workPmListEquipList';

INSERT INTO tapggrid 
SELECT 
SQAPGGRID_ID.NEXTVAL,
(SELECT page_id FROM tapage WHERE file_name = 'workPmListEInsEquipList'),
x.grid_obj_id,x.description,x.height
 FROM tapggrid x, tapage z
WHERE z.page_id = x.page_id
AND z.file_name = 'workPmListEquipList';

INSERT INTO tapggrid 
SELECT 
SQAPGGRID_ID.NEXTVAL,
(SELECT page_id FROM tapage WHERE file_name = 'workPmListInsEquipList'),
x.grid_obj_id,x.description,x.height
 FROM tapggrid x, tapage z
WHERE z.page_id = x.page_id
AND z.file_name = 'workPmListEquipList';

INSERT INTO tapggrid 
SELECT 
SQAPGGRID_ID.NEXTVAL,
(SELECT page_id FROM tapage WHERE file_name = 'workPmListCalEquipList'),
x.grid_obj_id,x.description,x.height
 FROM tapggrid x, tapage z
WHERE z.page_id = x.page_id
AND z.file_name = 'workPmListEquipList';


/** 2017-08-02 이규선 추가 */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmTaskMapList') ,'gridbox','Task Selection Map','10');

 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmTaskMapItemList') ,'gridbox','Task Selection Item Map','10');

/** 2017-08-03 노정현 추가  */


INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmPmtaskList') ,'gridbox','PMTASK','8'); 

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmPmtaskMapList') ,'gridbox','PMTASK MAP','8'); 

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmPmtaskDocLibList') ,'gridbox','PMTASK DOC','8');


/**  2017-08-04 노정현 추가 */

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmPmtaskCndtList') ,'gridbox','PMTASK_CNDT','8'); 

/**  2017-08-08 이규선 추가 */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'lovTaskMapPopup') ,'gridbox','질의번호 Popup','8'); 

 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'lovRcmEqCtgAsmbPopup') ,'gridbox','RCM 부위 Popup','8'); 

 /** 2017-08-08 차한결 추가  */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmSysList') ,'gridbox','System분석 목록','10');
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmSysList') ,'gridbox','System분석 목록','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmSysFDefList') ,'gridbox','기능정의 목록','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmSysFEnvList') ,'gridbox','운전환경 목록','10'); 
  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmSysEqList') ,'gridbox','설비설정 목록','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmSysEqAsmbList') ,'gridbox','설비부위 목록','10');
  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmSysEmpList') ,'gridbox','분석자 목록','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmSysDocLibList') ,'gridbox','SYSTEM 첨부문서','8');




/** 2017-08-08 김정우 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'consultPgmGuideList') ,'gridbox','Program Guide List Grid','8'); 
/** 2017-08-08 김정우 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmCrityList') ,'gridbox','Criticality Matrix List Grid','8');

/** 2017-08-09 노정현 추가  */

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmCrityValLov') ,'gridbox','RCM Critical LOV Grid','8'); 

/** 2017-08-09 장효성 추가  */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultCompDeptList') ,'gridbox','Consult Dept List Grid','8');


/** 2017-08-09 노정현 추가  */


INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmPmtaskCrityList') ,'gridbox','PMTASK_Crity','8'); 


/** 2017-08-10 김정우 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmCrityColList') ,'gridbox','Criticality Matrix Col List Grid','8'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmCrityRowList') ,'gridbox','Criticality Matrix Row List Grid','8'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmCrityValList') ,'gridbox','Criticality Matrix Value List Grid','8'); 

/** 2017-08-10 노정현 추가 */



INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'mgrUsrgrpPageList') ,'gridbox','AUTH PAGE','8'); 

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'mgrUsrgrpBtnList') ,'gridbox','AUTH PAGE','8'); 

 /**  2017-08-17 이규선 추가 */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'lovEquipCtgPopup') ,'gridbox','설비부위 Popup','8'); 

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'invtPrcTpList') ,'gridbox','구매절차 Grid','8'); 


/**  2017-08-21 김영주 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultCompUserList') ,'gridbox','Company User List Grid','8'); 


/** 2017-08-22 이근환 추가  */

 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'consultCompEmpList') ,'gridbox','','10'); 
 
 /** 2017-08-22 장효성 추가  */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtPrcTpItemList') ,'gridbox','구매절차 Grid','8'); 

/** 2017-08-22 노정현 추가 */

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtList') ,'gridbox','Equip. Invt.','8'); 

/**  2017-08-23 김영주 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assAssetList') ,'gridbox','설비등급 평가 List Grid','8'); 

  /** 2017-08-23 김정우 추가 */
  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'assBaseList') ,'gridbox','설비등급 평가기준 List Grid','8');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'assBaseGradeList') ,'gridbox','등급기준 List Grid','8');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'assBasePointList') ,'gridbox','평가항목 List Grid','8');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'assBasePointValList') ,'gridbox','평가기준 List Grid','8');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'assBaseDocLibList') ,'gridbox','설비등급 평가기준 첨부문서 Grid','8');



/** 2017-08-24 박철완 작업요청 추가 */
        INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'reqWorkList') ,'gridbox','작업요청 리스트 ','10'); 
        INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'reqWorkResList') ,'gridbox','처리사항 리스트 ','10'); 

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, HEIGHT)
 VALUES (SQAPGGRID_ID.NEXTVAL, (SELECT page_id  FROM TAPAGE WHERE file_name ='reqWorkDocLibList'), 'gridbox', '10');
   
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, HEIGHT)
 VALUES (SQAPGGRID_ID.NEXTVAL, (SELECT page_id  FROM TAPAGE WHERE file_name ='reqWorkResDocLibList'), 'gridbox', '10');
 
 
  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'assetListMstrToolsList') ,'gridbox','','10'); 


/** 2017-08-24 김정우 추가 */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'eqLocValLov') ,'gridbox','위치분류 AC 리스트','10'); 
 /** 2017-08-24 노정현 추가  */
 
 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtPhaseList') ,'gridbox','Equip. Invt.','8'); 

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'invtDocLibList') ,'gridbox','Eq. Doc. List','8');

/** 2017-08-24 장효성 추가  */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtPrcTpItemDocLibList') ,'gridbox','구매절차 첨부문서 List Grid','8');

/** 2017-08-24 노정현 추가 */


INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtPrcList') ,'gridbox','Investment Progress','8'); 


 /** 2017-08-24 김정우 추가 */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'plantValLov') ,'gridbox','공장 AC 리스트','10'); 

 /** 2017-08-25 이근환 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'mgrWorkflowList') ,'gridbox','시스템관리 - 승인Flow 목록','10'); 

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'mgrWorkflowPhaseList') ,'gridbox','승인Flow 절차 목록','10'); 


 /** 2017-08-28 김정우 추가 */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'assBaseValLov') ,'gridbox','설비등급 평가기준 AC 리스트','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'assBasePointValValLov') ,'gridbox','평가항목 평가기준 AC 리스트','10'); 
 
 /** 2017-08-29 노정현 추가  */

 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'invtPrcDocLibList') ,'gridbox','Investment Prc Doc List','8');


/** 2017-08-30 김영주 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assAssetScoreList') ,'gridbox','평가점수 List Grid','8');

/** 2017-08-30 노정현 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListGnlCaEqList') ,'gridbox','Calib Eq.','8'); 


 /** 2017-08-31 김정우 추가 */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'tableValLov') ,'gridbox','테이블 AC 리스트','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'tableColValLov') ,'gridbox','테이블 컬럼 AC 리스트','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'partsValLov') ,'gridbox','부품 컬럼 AC 리스트','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'ehMenuValLov') ,'gridbox','컨설트 메뉴 AC 리스트','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'langValLov') ,'gridbox','다국어 AC 리스트','10');
 
 /** 2017-08-31 노정현  */
 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'listOfSysVal') ,'gridbox','Systom Code LOV for AC','8'); 


INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'lovUserAcList') ,'gridbox','USER LOV for AC','8'); 


INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'lovEmpAcList') ,'gridbox','EMP LOV for AC','8'); 


INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'lovEquipAcList') ,'gridbox','Equip LOV for AC','8'); 

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'lovEqCtgAcList') ,'gridbox','Equip CTG LOV for AC','8'); 
 


 /** 2017-09-01 장효성 추가  */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'compValLov') ,'gridbox','회사 팝업','10');
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'eqAsmbValLov') ,'gridbox','설비부위 LOV','10');
 

   /** 2017-09-01 이근환 추가  */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'rcmTaskMapListLov') ,'gridbox','Task Map List AC Lov','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'invtPrcTpLov') ,'gridbox','구매절차 AC Lov','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'invtLov') ,'gridbox','설비투자 AC Lov','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'invtPrcPhLov') ,'gridbox','구매절차 소분류 AC Lov','10'); 

 
  /** 2017-09-01 장효성 추가*/
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'lovTaskMapList') ,'gridbox','질의번호 Popup','8');


/** 2017-09-01 김영주 추가  */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'wrkCalListLov') ,'gridbox','근무달력 팝업','10');
 
 /** 2017-09-04 장효성 추가*/
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'crityValLov') ,'gridbox','Critycality Popup','8');

 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'lovEduList') ,'gridbox','교육과정 LOV','8');

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'lovCertList') ,'gridbox','자격증 LOV','8');

   /** 2017-09-04 이근환 추가  */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'pageValLov') ,'gridbox','Page AC Lov','10'); 

/** 2017-09-04 노정현 추가  */

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'lovVendorAcList') ,'gridbox','Vendor Popup','8');


INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultGnlCaCraftList') ,'gridbox','','10'); 


INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
values( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultGnlCaDocLibList') ,'gridbox','','10'); 
 

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'lovWoAcList') ,'gridbox','Work Order Popup','8');
 /** 2017-09-05 김정우 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'wkCtrValLov') ,'gridbox','작업그룹 AC LOV Grid','8');

   /** 2017-09-05 이근환 추가  */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'menuValLov') ,'gridbox','Menu AC Lov','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'consultCompWarehouseLov') ,'gridbox','Warehouse AC Lov','10'); 

 /** 2017-09-05 김정우 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'pmValLov') ,'gridbox','작업그룹 AC LOV Grid','8');

/** 2017-09-06 김영주 추가  */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'wrkCalListLov') ,'gridbox','근무달력 팝업','10');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'lovCdUsrdAcList') ,'gridbox','상위코드 팝업','10');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'rcmPmtaskAcList') ,'gridbox','System 분석 팝업','10');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'lovEqToolAcList') ,'gridbox','기준측정기 팝업','10');

/** 2017-09-06 노정현 추가 */

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'deptValLov') ,'gridbox','Dept AC LOV Grid','8');

 /** 2017-09-07 이근환 추가  */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovEqCtgAsmbList') ,'gridbox','설비자산 - 설비종류 - 설비부위(Lov)','10'); 

/** 2017-09-06 노정현 추가  */

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maPtBuyReqHdrDocLibList') ,'gridbox','Pt Pr Doc List','8');

/** 2017-09-08 차한결 */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workPmiList') ,'gridbox','점검작업 목록','10');
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'workPmiDocLibList') ,'gridbox','첨부문서 목록','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workPmiPointList') ,'gridbox','점검 목록','10');
 
   /** 2017-09-11 이근환 추가  */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'lovCtCtrList') ,'gridbox','Cost Center Lov','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovMesLineList') ,'gridbox','Mes List Lov','10'); 

 
/** 2017-09-14 김영주 추가  */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'lovUsrRptTabAcList') ,'gridbox','테이블명 검색 팝업','8');

  /** 2017-09-14 이근환 */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'workPmListPtrlRtList') ,'gridbox','예방작업[순회](상세) - 순회코스(목록)','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'workPmListPtrlRtPointList') ,'gridbox','예방작업[순회](상세) - 순회코스(상세) - 점검내용(목록)','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'workPmListPtrlShiftList') ,'gridbox','예방작업[순회](상세) - 교대조(목록)','10'); 

   /** 2017-09-15 이규선 */
  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqUtilityMstrList') ,'gridbox','설비 검색 팝업','8');

 
  /**2017-09-15 장효성 추가 */
  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from TAPAGE aa where aa.file_name = 'maEqMachMstrList') ,'gridbox','생산설비 목록','10');
  
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqToolMstrList') ,'gridbox','설비 계측기','8');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqBuildingMstrList') ,'gridbox','설비 건물,토지','8');
 
   /**2017-09-19 이규선 추가 */
   INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultBmMstrList') ,'gridbox','고장작업 목록','10');
 
 
 /** 2017-09-19 김정우 추가 */
  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workPmStdCalibList') ,'gridbox','교정표준값 타입  List Grid','8');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workPmStdCalibValList') ,'gridbox','표준값 List Grid','8');

 /** 2017-09-20 차한결 추가 */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workPmListWorkList') ,'gridbox','예방수리 목록','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workPmListCalList') ,'gridbox','예방교정 목록','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workPmListTrList') ,'gridbox','예방교육 목록','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workPmListInsList') ,'gridbox','예방점검 목록','10');
 
  /** 2017-09-20 이규선 추가 */
     INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoCmResultMstrList') ,'gridbox','개선작업 목록','10');
 
 
 
 /**2017-09-21 김영주 추가*/
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListPointList') ,'gridbox','점검내용 목록','8');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListDocLibList') ,'gridbox','첨부문서 목록','8');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListPtrlResultList') ,'gridbox','순회일정 목록','8');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmPtrlMonthList') ,'gridbox','순회일정(월간) 목록','8');

/** 2017-09-21 김정우 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workFmeaList') ,'gridbox','고장영향성 평가 List Grid','8');

/** 2017-09-22 차한결 추가 */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workListPmiList') ,'gridbox','예방점검작업목록','10');
 
 
 /**2017-09-22 장효성 추가*/
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultTrMstrList') ,'gridbox','교육목록','10');
 
 /**2017-09-25 이규선 추가*/
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoPmwResultMstrList') ,'gridbox','예방수리작업목록','10');
  
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoPmpResultMstrList') ,'gridbox','교정작업목록','10');
 
/** 2017-09-26 노정현 추가 */ 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maEqPartMstrList') ,'gridbox','자산부품 목록','10');

 
/** 2017-09-26 김정우 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListSclCaEqList') ,'gridbox','Calib Eq.','8'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultSclCaCraftList') ,'gridbox','','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultSclCaDocLibList') ,'gridbox','','10'); 

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListPrsCaEqList') ,'gridbox','Calib Eq.','8'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultPrsCaCraftList') ,'gridbox','','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultPrsCaDocLibList') ,'gridbox','','10'); 

/** 2017-09-26 김영주 추가  */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'lovUsrGrpAcList') ,'gridbox','권한명 검색 팝업','8');
 
/** 2017-09-28 김영주 추가  */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workFmeaReqList') ,'gridbox','고장영향성 평가요청 List Grid','8');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workFmeaResList') ,'gridbox','고장영향성 평가접수 List Grid','8');

/** 2017-10-11 이규선 추가  */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPtRecSerialList') ,'gridbox','시리얼 List Grid','8'); 

/** 2017-10-11 이근환 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'daewoongKpiOeeDailyList') ,'gridbox','외부Interface - 설비종합효율 - 설비효율(일별)','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'daewoongKpiOeeDailyDetailList') ,'gridbox','외부Interface - 설비종합효율 - 설비효율(일별) - 상세 리스트','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'daewoongKpiOeeMonthlyList') ,'gridbox','외부Interface - 설비종합효율 - 설비효율(월별)','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'daewoongKpiOeeMonthlyDetailList') ,'gridbox','외부Interface - 설비종합효율 - 설비효율(월별) - 상세 리스트','10'); 

/**2017-10-13 김정우 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'eduEmpDocLibList') ,'gridbox','교육이수 DOC','8');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'certEmpDocLibList') ,'gridbox','자격증 DOC','8');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'orgEmpTrainDocLibList') ,'gridbox','교육이수 DOC','8');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'orgEmpCertDocLibList') ,'gridbox','자격증 DOC','8');

/** 2017-10-16 김영주 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmCheckList') ,'gridbox','표준점검항목 List Grid','8'); 

/**  2017-10-16 장효성 추가*/
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'lovSerialList') ,'gridbox','순환자재 LOV','8');
 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partStkSerialList') ,'gridbox','부품-상세-시리얼 (목록)','8');

/**  2017-10-17 이근환 추가*/
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'assetReportLccEquipList') ,'gridbox','설비자산 - report - 설비LCC분석 - 고장TOP(설비)','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'assetReportLccEquipDetailList') ,'gridbox','설비자산 - report - 설비LCC분석 - 고장TOP(설비) - 상세 리스트','10'); 

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'assetReportLccLocList') ,'gridbox','설비자산 - report - 설비LCC분석 - 고장TOP(위치)','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'assetReportLccLocDetailList') ,'gridbox','설비자산 - report - 설비LCC분석 - 고장TOP(위치) - 상세 리스트','10'); 


/** 2017-10-18 차한결 추가 */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'commRevHistList') ,'gridbox','개정 변경이력 목록','4');
 
  /** 2017-10-18 노정현 추가  */
  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListPmRplPartSerialList') ,'gridbox','Work Order Part Serial List','5');
 
  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListCmRplPartSerialList') ,'gridbox','Work Order Part Serial List','5');
 
  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListBmRplPartSerialList') ,'gridbox','Work Order Part Serial List','5');
 
 /** 2017-10-19 장효성 추가  */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partIssWoItemList') ,'gridbox','부품- 부품출고 상세-순환품 관리','8');
 
 /** 2017-10-20 김영주 추가  */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'lovStdCheckPointList') ,'gridbox','표준점검항목 팝업','8');

 /** 2017-10-24 김영주 추가  */
  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListDInsPointList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiDInsList') ,'gridbox','일상점검목록 List Grid','8'); 

 /**2017-10-24 김정우 */
  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT)
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workPmStdCalibValLov') ,'gridbox','교정표준값 AC 리스트','10'); 
 
 /**2017-10-24 장효성 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'kacBdList') ,'gridbox','장애보고 List','8');


INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'kacBdDocList') ,'gridbox','장애보고 List-첨부파일','8'); 
 
  /** 2017-10-25 김영주 추가  */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiDInsPointList') ,'gridbox','','10'); 

  /** 2017-10-26 김영주 추가  */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtRptInvtPreConList') ,'gridbox','설비투자 - Report - 투자현황(목록)','8'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtItemList') ,'gridbox','투자항목(목록)','8'); 

  /** 2017-10-26 이규선 추가  */
  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maStdWorkList') ,'gridbox','표준작업-작업','10');
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maStdPartList') ,'gridbox','표준작업-부품','10'); 

 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maStdWoTypeList') ,'gridbox','표준작업-작업종류','10'); 



 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'lovStdWrkWorkList') ,'gridbox','표준작업종류','10'); 


  /** 2017-10-30 김영주 추가  */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'overOwnPartPreConList') ,'gridbox','부품관리 - Report - 부품초과보유현황','8'); 

/** 2017-10-31 이근환 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'workRptPminsAchList') ,'gridbox','작업관리 - report - 예방점검이행율(담당자)','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'workRptPminsAchDetailList') ,'gridbox','작업관리 - report - 예방점검이행율(담당자) - 상세리스트','10'); 

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'workRptPmRatioList') ,'gridbox','작업관리 - report - 계획보전율(위치)','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'workRptPmRatioDetailList') ,'gridbox','작업관리 - report - 계획보전율(위치) - 상세리스트','10'); 

/** 2017-11-01 김영주 추가  */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'stockKpiList') ,'gridbox','부품관리 - Report - 재고지표','8'); 

/** 2017-11-02 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'energyUsePreConList') ,'gridbox','작업관리 - Report - 에너지사용현황','8'); 

/** 2017-11-02 이근환 추가  */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'assetRptMtbfmttrEquipList') ,'gridbox','설비자산 - report - MTBF,MTTR(설비)','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'assetRptMtbfmttrEquipDetailList') ,'gridbox','설비자산 - report - MTBF,MTTR(설비) - 상세리스트','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'assetRptMtbfmttrLocList') ,'gridbox','설비자산 - report - MTBF,MTTR(위치)','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'assetRptMtbfmttrLocDetailList') ,'gridbox','설비자산 - report - MTBF,MTTR(위치) - 상세리스트','10'); 

/** 2017-11-02 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'orgRptEmpMttrList') ,'gridbox','조직관리 - Report - MTTR(담당자)(목록)','8'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'orgRptEmpMttrDetailList') ,'gridbox','조직관리 - Report - MTTR(담당자) - 월별(목록)','8'); 

/** 2017-11-03 김영주 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'basicUnitAnalysisList') ,'gridbox','작업관리 - Report - 원단위분석(목록)','8'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'basicUnitAnalysisDetailList') ,'gridbox','작업관리 - Report - 원단위분석(상세목록)','8'); 

/** 2017-11-03 이근환 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'workCalPmcYearList') ,'gridbox','','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'workCalPmcMonthList') ,'gridbox','','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'workCalPmcPeriodList') ,'gridbox','','10'); 

/** 2017-11-03 김영주 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmCheckMonthlyUnitPriceList') ,'gridbox','월별단가 List Grid','8'); 

/**2017-11-03 김정우 */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultCmLocBaseCraftList') ,'gridbox','','10'); 
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultCmLocBaseDocLibList') ,'gridbox','','10'); 

/** 2017-11-06 김정우 */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListTiMstrList') ,'gridbox','설치/테스트 목록','10');
 
/*2017-11-23일 동국제약 반영*/

/** 2017-11-27 김정우 */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maEqMstrInsList') ,'gridbox','설비 별  점검 탭 리스트','10'); 


/** 2017-11-27 김정우 추가 */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maUsrGrpAndroidAuthList') ,'gridbox','Android 권한 설정 ','10'); 

  /*2017-11-28일 동국제약 반영*/

/** 2017-12-01 김영주 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'lovProductAcList') ,'gridbox','생산제품 검색 팝업','8');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmCInsMonthList') ,'gridbox','파트체인지점검리스트','8');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListCinsPlanMstrList') ,'gridbox','파트체인지 점검계획 List Grid','8'); 

/** 2017-12-03 이근환 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'assetStdProductList') ,'gridbox','생산품목 리스트','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'assetStdCtctrList') ,'gridbox','Cost Center 리스트','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'assetStdAssetList') ,'gridbox','회계자산 리스트','10'); 


/*2017-12-06일 동국제약 반영*/

/** 2017-12-06 이근환 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'workPmiCInsList') ,'gridbox','파트체인지점검목록','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'workPmiCInsPointList') ,'gridbox','','10'); 

/** 2017-12-07 김영주 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'lovPmEquipAcList') ,'gridbox','생산제품(TAPMEQUIP) 검색 팝업','8');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultCompTerminalExList') ,'gridbox','Access Terminal Ex List Grid','10'); 

/** 2017-12-12 이근환 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'energyUsePreConMonthDetailList') ,'gridbox','작업관리 - Report - 에너지사용현황(상세) - 리스트','10'); 

/** 2017-12-13 이근환 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'energyUsePreConYearList') ,'gridbox','작업관리 - Report - 에너지사용현황(년)(목록)','10'); 

/** 2017-12-14 김영주 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'lovLnWrkAcList') ,'gridbox','가동달력 팝업','8'); 

/** 2017-12-14 이근환 추가 */
INSERT INTO TAPGGRID
SELECT sqapggrid_id.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='eqAsmb2ValLov'),grid_obj_id,description,height 
FROM TAPGGRID WHERE page_id=(SELECT page_id FROM TAPAGE WHERE file_name='eqAsmbValLov');

/** 2017-12-14 동국제약 반영 */

/** 2017-12-15 이근환 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maEqMstrPmwRplList') ,'gridbox','설비 정기교체','10'); 

/** 2017-12-15 동국제약 반영 */

/** 2017-12-20 김영주 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPmTrendList') ,'gridbox','작업관리 - Report - 예방점검수치추이(목록)','8'); 

/** 2017-12-20 이근환 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPgGrdColMngList') ,'gridbox','컨설팅 - 프로그램설정 - 화면GridCol설정(목록)','10'); 

/** 2017-12-21 김영주 추가 */
INSERT INTO TAPGGRID
SELECT sqapggrid_id.NEXTVAL, (SELECT page_id FROM TAPAGE WHERE file_name = 'lovConsultMenuAcList')
      , grid_obj_id, 'Consult '||description, height
FROM TAPGGRID WHERE page_id = (SELECT page_id FROM TAPAGE WHERE file_name = 'menuValLov');

/** 2017-12-22 김영주 추가*/
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPgFieldMngList') ,'gridbox','컨설팅 - 프로그램설정 - 화면입력항목(목록)','8'); 

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'woPlanList') ,'gridbox','작업관리 - 작업계획목록','8'); 

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'woPlanCraftList') ,'gridbox','작업계획목록 작업자 Grid','8');

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'woPlanPartList') ,'gridbox','작업계획목록 투입부품 Grid','8'); 

  /** 2017-12-26 동국제약 반영 */

/** 2017-12-27 김정우 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmInsApprList') ,'gridbox','예방점검계획승인 Grid','8');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmInsApprWorkList') ,'gridbox','예방점검계획승인 - 점검작업 Grid','8');

/** 2017-12-27 이근환 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'consultProgramOnlinehelpList') ,'gridbox','컨설팅 - 프로그램설정 - 도움말(목록)','10'); 

/** 2017-12-27 동국제약 반영 */

/** 2017-12-28 이근환 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'consultProgramOnlinehelpDocLibList') ,'gridbox','도움말 첨부문서 Grid','10'); 

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appOnlinehelpDocLibList') ,'gridbox','도움말 popup 첨부문서 Grid','10'); 

/** 2017-12-28 동국제약 반영 */

/** 2018-01-03 동국제약 반영 */

/** 2018-01-04 이근환 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'consultCompTracelogList') ,'gridbox','컨설팅 - 회사설정 - Screen Trace(목록)','10'); 

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'appTracelogList') ,'gridbox','TraceLog Popup(목록)','10'); 

/** 2018-01-04 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultProgramUploadDataList') ,'gridbox','컨설팅 - 프로그램설정 - 업로드데이타설정(목록)','8'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultProgramUploadDataColList') ,'gridbox','컨설팅 - 프로그램설정 - 업로드데이타설정 - 컬럼(목록)','8'); 

/** 2018-01-05 이근환 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'mgrExldataList') ,'gridbox','Excel 데이터 업로드(목록)','10'); 

/** 2018-01-08 이근환 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovExcelTabAcList') ,'gridbox','Excel Tab Popup','10'); 

/** 2018-01-09 동국제약 반영 */
/** 2018-01-12 동국제약 반영 */
/** 2018-01-16 동국제약 반영 */
/** 2018-01-18 동국제약 반영 */
/** 2018-01-23 동국제약 반영 */
/** 2018-01-26 동국제약 반영 */

/** 2018-01-29 이근환 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'partIssEmgReqList') ,'gridbox','부품출고요청(목록)','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'partIssEmgReqPartsList') ,'gridbox','부품출고요청(상세) - 부품(목록)','10'); 

/** 2018-01-30 동국제약 반영 */

/** 2018-01-31 김영주 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptMaintRateList') ,'gridbox','작업관리 - Report - 정비지표 (목록)','5'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptMaintRateDetailByPartList') ,'gridbox','작업관리 - Report - 정비지표 (목록) - 파트별 정비율 (상세목록)','5'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptMaintRateDetailByTypeList') ,'gridbox','작업관리 - Report - 정비지표 (목록) - 유형별 정비율 (상세목록)','5'); 

/** 2018-02-01 동국제약 반영 */

/** 2018-02-02 김영주 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqRptWoReqRateList') ,'gridbox','요청관리 - Report - 요청접수율(처리자) (목록)','5'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqRptWoReqRateDetailList') ,'gridbox','요청관리 - Report - 요청접수율(처리자) (상세목록)','5'); 

/** 2018-02-05 김영주 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiPointDocLibList') ,'gridbox','점검항목 첨부문서','5');

/** 2018-02-07 김영주 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiCInsPointDocLibList') ,'gridbox','파트점검항목 첨부문서','5');

/** 2018-02-09 김영주 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiPInsPointDocLibList') ,'gridbox','순회점검항목 첨부문서','5');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiDInsPointDocLibList') ,'gridbox','일상점검항목 첨부문서','5');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiRInsPointDocLibList') ,'gridbox','정기점검항목 첨부문서','5');
UPDATE TAPGGRID SET description = (CASE SUBSTR(description,0,2) WHEN '예방' THEN description ELSE  '예방'||description END) 
WHERE page_id = (SELECT page_id FROM TAPAGE WHERE file_name = 'workPmiPointDocLibList');

/** 2018-02-20 양소영 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'persApprHistList') ,'gridbox','개인설정 - 결재이력','10');

/** 2018-02-21 김영주 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'assetRptEqPartPreConList') ,'gridbox','지표관리 - 설비지표 - 설비부품현황 (목록)','5'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'assetRptEqPartPreConDetailPartList') ,'gridbox','지표관리 - 설비지표 - 설비부품현황 (상세목록)','5'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'assetRptEqPartPreConDetailStockList') ,'gridbox','지표관리 - 설비지표 - 설비부품현황 (상세상세목록)','5'); 

/** 2018-02-27 김영주 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetListITList') ,'gridbox','설비자산 - IT장비목록','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetListITSWList') ,'gridbox','설비자산 - IT장비 - 설치SW 목록','5'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetListItDocLibList') ,'gridbox','IT장비목록 첨부파일','5');


/** 2018-03-08 동국제약 반영 */

/** 2018-03-09 김영주 */                                       
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES(  sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'docNoticeList') ,'gridbox','공유자료 - 공지사항(목록)','5'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES(  sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'docNoticeDeptList') ,'gridbox','공유자료 - 공지사항 - 공지부서(목록)','5'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES(  sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'docNoticeTargetList') ,'gridbox','공유자료 - 공지사항 - 공지대상(목록)','5'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES(  sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'docNoticeDocLibList') ,'gridbox','공유자료 - 공지사항 - 첨부문서(목록)','5'); 

 /**  2018-03-15 대웅제약 반영 */

/** 2018-03-15 김영주 */            
UPDATE TAPGGRID SET description = REPLACE(description,'사항','등록') WHERE description LIKE '%공지%';
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'docNoticeCheckList') ,'gridbox','공유자료 - 공지사항(목록)','5'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES(  sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'docNoticeCheckDocLibList') ,'gridbox','공유자료 - 공지사항 - 첨부문서(목록)','5'); 

/** 2018-03-15 이근환 */
update tapggrid set height=10 where page_id=(select page_id from tapage where file_name='lovEmgPartPopup');

/** 2018-03-19 양소영 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (select aa.page_id from tapage aa where aa.file_name = 'mgrPtWhList') ,'gridbox','시스템관리 - 부품창고(목록)','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (select aa.page_id from tapage aa where aa.file_name = 'mgrPtWhEmpList') ,'gridbox','시스템관리 - 부품창고(상세) - 담당자(목록)','5'); 


/** 2018-03-19 오뚜기라면 반영 */

/** 2018-03-20 이근환 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoDailyPmiList') ,'gridbox','일일작업확인(상세) - 예방점검 리스트','10'); 


/** 2018-03-21 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptPminsDeptAchList') ,'gridbox','작업관리 - report - 예방점검이행율(부서)','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptPminsDeptAchDetailList') ,'gridbox','작업관리 - report - 예방점검이행율(부서) - 상세리스트','8'); 

/**  2018-03-22 동국제약 반영 */

/** 2018-03-23 이근환 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'docManualList') ,'gridbox','사용자매뉴얼','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'docManualDocLibList') ,'gridbox','사용자매뉴얼(상세) - 첨부문서','10'); 

/**  2018-03-28 동국제약 반영 */
/** 2018-03-28 오뚜기라면 반영 */

/** 2018-03-30 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partRptOrgPtUseHistList') ,'gridbox','지표관리 - 부품지표 - 조직별사용분석(목록)','5'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partRptOrgPtUseHistDetailList') ,'gridbox','지표관리 - 부품지표 - 조직별사용분석(상세목록)','5'); 

/** 2018-04-02 동국제약 반영 */ 
/** 2018-04-03 오뚜기라면 반영 */

/** 2018-04-03 이지수 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'assetRptWorkHistList') ,'gridbox','설비자산 - Report - 설비이력(과거)','10');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'assetRptWorkHistLibList') ,'gridbox','설비자산 - Report - 설비이력(과거)','10'); 

/** 2018-04-05 오뚜기라면 반영 */
/** 2018-04-09 오뚜기라면 반영 */
/** 2018-04-10 동국제약 반영 */

/** 2018-04-10 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMstrPmiList') ,'gridbox','설비 별 분해점검 탭 리스트','8'); 

/** 2018-04-11 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partRptOrgPtUseHistList') ,'gridbox','지표관리 - 부품지표 - 조직별사용분석(목록)','5'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partRptOrgPtUseHistDetailList') ,'gridbox','지표관리 - 부품지표 - 조직별사용분석(상세목록)','5'); 

/** 2018-04-12 오뚜기라면 반영 */

/** 2018-04-12 양소영 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'workRptPminsOrgAchList') ,'gridbox','작업관리 - report - 예방점검이행율(부서)','10','workRptPminsOrgAchList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'workRptPminsOrgAchDetailList') ,'gridbox','작업관리 - report - 예방점검이행율(부서) - 상세리스트','8','workRptPminsOrgAchDetailList'); 

/** 2018-04-12 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptOrgBdList') ,'gridbox','지표관리 - 작업지표 - 조직별고장분석(목록)','5','workRptOrgBdList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptOrgBdDetailList') ,'gridbox','지표관리 - 작업지표 - 조직별고장분석(상세목록)','5','workRptOrgBdDetailList'); 

/** 2018-04-16 연우 반영 */

/** 2018-04-16 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptOrgMtbfmttrList') ,'gridbox','지표관리 - 작업지표 - 조직별MTTR,MTBF(목록)','5','workRptOrgMtbfmttrList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptOrgMtbfmttrDetailList') ,'gridbox','지표관리 - 작업지표 - 조직별MTTR,MTBF(상세목록)','5','workRptOrgMtbfmttrDetailList'); 

/** 2018-04-17 오뚜기계열사 반영 */

/** 2018-04-17 양소영 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'partRptPtUseHistList') ,'gridbox','지표관리 - 부품지표 - 부품사용분석(목록)','10','partRptPtUseHistList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'partRptPtUseHistDetailList') ,'gridbox','지표관리 - 부품지표 - 부품사용분석(목록) - 부품사용분석(상세목록)','8','partRptPtUseHistDetailList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'partRptPtMstrEqPartList') ,'gridbox','지표관리 - 부품지표 - 부품사용분석(목록) - 사용설비(상세목록)','8','partRptPtMstrEqPartList'); 

/** 2018-04-18 오뚜기계열사 반영 */

/** 2018-04-19 김영주 */
DELETE FROM TAPGGRIDCOL WHERE pggrid_id = (SELECT pggrid_id FROM TAPGGRID WHERE page_id = (SELECT page_id FROM TAPAGE WHERE file_name = 'maBmEqList'));
DELETE FROM TAPGGRID WHERE page_id = (SELECT page_id FROM TAPAGE WHERE file_name = 'maBmEqList');

/** 2018-04-24 오뚜기계열사 반영 */

/** 2018-04-30 이근환 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maHelpDocLibList') ,'gridbox','Helpdesk(상세) - 첨부문서','10'); 

/** 2018-05-02 오뚜기계열사 반영 */
/** 2018-05-03 동국제약 반영 */
/** 2018-05-08 오뚜기계열사 반영 */

/** 2018-05-09 이근환 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'assetListAssList') ,'gridbox','설비자산 - 설비 - 등급평가(목록)','10'); 

/** 2018-05-10 연우 반영 */
/** 2018-05-15 오뚜기계열사 반영 */
/** 2018-05-15 연우 반영 */
/** 2018-05-15 오뚜기계열사 반영 */
/** 2018-05-16 연우 반영 */
/** 2018-05-18 오뚜기계열사 반영 */

/** 2018-05-24 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPlanApprList') ,'gridbox','작업계획승인 (목록)','5','workPlanApprList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPlanApprWorkList') ,'gridbox','작업계획승인 - 계획작업(목록)','5','workPlanApprWorkList'); 

/** 2018-05-25 오뚜기계열사 반영 */
/** 2018-05-30 동국제약 반영 */
/** 2018-06-01 오뚜기계열사 반영 */
/** 2018-06-01 대웅제약 반영 */
/** 2018-06-07 연우 반영 */

/** 2018-06-07 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultPgmErrorList') ,'gridbox','Error List','5','consultPgmErrorList'); 

/** 2018-06-08 이지수 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'assetListGnMstrList') ,'gridbox','일반자산(목록)','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'assetListGnDocLibList') ,'gridbox','일반자산(상세) - 첨부문서','10'); 

/** 2018-06-08 김정우 */

INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES
( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maEqMstrPmInsList') ,'gridbox','설비 마스터-정기 점검(목록)','8','maEqMstrPmInsList');

INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES
( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maEqMstrPmWorkList') ,'gridbox','설비 마스터-정기 작업(목록)','8','maEqMstrPmWorkList');

INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES
( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maEqMstrPmInsPointList') ,'gridbox','설비 마스터-정기 점검-점검항목(목록)','8','maEqMstrPmInsPointList');

INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES
( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maEqMstrPmWorkPartList') ,'gridbox','설비 마스터-정기 작업-부품(목록)','8','maEqMstrPmWorkPartList');


/** 2018-06-08 오뚜기 계열사 반영 */

/** 2018-06-08 양소영 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'consultCompConfigList') ,'gridbox','컨설팅 - 프로그램설정 - 환경변수(목록)','10','consultCompConfigList'); 

/** 2018-06-14 연우 반영 */
/** 2018-06-15 연우 반영 */
/** 2018-06-18 연우 반영 */
/** 2018-06-20 연우 반영 */
/** 2018-06-22 김정우 */
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES
( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'consultPgmDashboardList') ,'gridbox','대시보드 컨텐츠 GRID','8','consultPgmDashboardList');

/** 2018-06-22 11:55 오뚜기 협력사 적용 */

/** 2018-06-22 양소영 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maEqCtgSpecList') ,'gridbox','설비자산 - 설비종류 - 제원(목록)','5','maEqCtgSpecList'); 

/** 2018-06-25 대웅제약 적용 */

/** 2018-06-28 12:55 오뚜기 협력사 적용 */

/** 2018-06-28 이지수 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'reqInvRecWorkDocLibList') ,'gridbox','투자요청접수(상세) - 첨부문서','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'reqInvRecWorkResList') ,'gridbox','투자요청접수(상세) - 처리사항 ','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'reqInvWorkDocLibList') ,'gridbox','투자요청(상세) - 첨부문서','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'lovInvtPopup') ,'gridbox','투자목록 검색 팝업','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'reqInvWorkResList') ,'gridbox','투자요청(상세) - 처리사항','10'); 

/** 2018-06-28 이근환 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'mgrIntfList') ,'gridbox','시스템관리 - 인터페이스 Log','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'mgrIntfLogList') ,'gridbox','시스템관리 - 인터페이스 Log(상세) - Log','10'); 

/** 2018-06-29 연우 반영 */

/** 2018-06-29 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmInsApprRsltList') ,'gridbox','예방점검계획승인 - 점검결과 Grid','8');

/** 2018-07-03 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtEquipList') ,'gridbox','투자목록 - 설비 (목록)','5','invtEquipList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtItemsList') ,'gridbox','투자목록 - 구매항목 (목록)','5','invtItemsList'); 


/** 2018-07-03 대웅제약 반영 */
/** 2018-07-04 본사Dream 반영 */
/** 2018-07-04 연우 반영 */

/** 2018-07-05 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'lovEqCtgPartAcList') ,'gridbox','설비종류별부품 Popup','8');

/** 2018-07-05 이지수 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptSelfVendorList') ,'gridbox','사내,외주작업현황 (목록)','20');

/** 2018-07-05 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'lovEqCtgSpecAcList') ,'gridbox','설비종류별제원 Popup','8');

/** 2018-07-06 본사Dream 반영 */

/** 2018-07-09 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultPgmLinkedFuncList') ,'gridbox','연결기능 (Linked Function) (목록)','5','consultPgmLinkedFuncList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultPgmPgLinkedFuncList') ,'gridbox','화면별 연결기능 (목록)','5','consultPgmPgLinkedFuncList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'lovLinkedFuncAcList') ,'gridbox','Linked Function Popup','8');

/** 2018-07-09 동국제약 반영 */

/** 2018-07-10 오뚜기 협력사 적용 */

 /** 2018-07-10 김정우 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'cdSysCodeParentLov') ,'gridbox','시스템 코드 부모 AC LOV Grid','8'); 

/** 2018-07-11 본사Dream 반영 */
/** 2018-07-11 동국제약 반영 */


/** 2018-07-11 김정우 */
 INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'cdUsrCodeParentLov') ,'gridbox','사용자 코드 부모 AC LOV Grid','8'); 

/** 2018-07-12 동국제약 반영 */
/** 2018-07-12 본사Dream 반영 */


/** 2018-07-13 김정우 */
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES
( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPgMngFieldValueList') ,'gridbox','화면별 필드 기본값 목록 그리드','8','maPgMngFieldValueList');

/** 2018-07-13 본사Dream 반영 */

/** 2018-07-13 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrToBeProcessList') ,'gridbox','TO-BE 프로세스 (목록)','5','mgrToBeProcessList'); 

/** 2018-07-15 이지수 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListMsTimeList') ,'gridbox','예방점검 - 예방점검설정 - 작업시간 Grid','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workPmListMsTimeLov') ,'gridbox','작업시간 목록 팝업','10'); 

/** 2018-07-17 본사Dream 반영 */
/** 2018-07-18 연우 반영 */

/** 2018-07-18 이지수 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqRptPreWoreqRateList') ,'gridbox','지표관리 - 요청지표 - 작업의뢰 사전 시스템 요청비율 Grid','15'); 

/** 2018-07-18 대웅제약 반영 */

/** 2018-07-20 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqWoInvtRsltList') ,'gridbox','투자결과 (목록)','5','reqInvtRsltList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqWoRsltList') ,'gridbox','작업결과 (목록)','5','reqWoRsltList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqWoPlanRsltList') ,'gridbox','작업계획 (목록)','5','reqWoPlanRsltList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'lovWoPlanAcList') ,'gridbox','Work Plan Popup','8');

/** 2018-07-20 본사Dream 반영 */

/** 2018-07-20 이지수 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoPmwOvhResultMstrList') ,'gridbox','작업관리 - Overhaul 작업목록  Grid','15'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqRptWoPlanCmptRateList') ,'gridbox','지표관리 - 요청지표 - 작업의뢰 계획대비 실행 비율 Grid','15'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqRptEmWoGenRateList') ,'gridbox','지표관리 - 요청지표 - 사후 작업오더 발생률 Grid','15'); 


/** 2018-07-24 11:10 오뚜기협력사 반영 */
/** 2018-07-25 대웅제약 반영 */

/** 2018-07-27 이지수 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqRptPreWoPlanRateList') ,'gridbox','지표관리 - 요청지표 - 작업오더 사전 계획 수립률 Grid','15'); 

/** 2018-07-30 이지수 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptWoEndRateList') ,'gridbox','지표관리 - 작업지표 - 작업오더 일마감 처리률 Grid','15'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptPmwCmptRateList') ,'gridbox','지표관리 - 작업지표 - 예방정비 계획대비 실행 비율 Grid','15'); 

/** 2018-07-30 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalUnitedMonthList') ,'work_gridbox','월간작업일정(통합) Grid - 작업','5');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalUnitedMonthList') ,'pmi_gridbox','월간작업일정(통합) Grid - 점검','5');

/** 2018-07-30 이지수 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptPmwRateList') ,'gridbox','지표관리 - 작업지표 - 예방정비 실행 비율 Grid','15'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES(sqaPGGRID_ID.NEXTVAL, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptPmiEquipCmptRateList') ,'gridbox','지표관리 - 작업지표 - 예방점검설비 실행 비율 Grid','15'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptPmiCmptRateList') ,'gridbox','지표관리 - 작업지표 - 예방점검항목 실행 비율 Grid','15'); 

/** 2018-07-31 대웅제약 반영 */

/** 2018-07-31 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetStdProductEquipList') ,'gridbox','생산설비 (목록)','5','assetStdProductEquipList'); 

/** 2018-08-02 양소영 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'mgrMessageTransList') ,'gridbox','시스템관리 - Message전송현황(목록)','10','mgrMessageTransList'); 

/** 2018-08-03 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assAssetTlList') ,'gridbox','계측기위험도평가(목록)','5','assAssetTlList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assAssetTlScoreList') ,'gridbox','계측기위험도평가점수(목록)','5','assAssetTlScoreList'); 

/** 2018-08-03 최지상  */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES(sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptPmiEquipPlanRateList') ,'gridbox','지표관리 - 작업지표 - 예방점검 설비 계획대비 실행 비율 Grid','15'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES(sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtRptPriceExeRateList') ,'gridbox','지표관리 - 투자지표 - 투자비 진행현황 Grid','15'); 

/** 2018-08-06 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrAudTrailList') ,'gridbox','Audit Trail (목록)','5','mgrAudTrailList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrAudTrailDtlList') ,'gridbox','변경사항 (목록)','5','mgrAudTrailDtlList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetListAssTlList') ,'gridbox','계측기마스터 - 계측기위험도평가(목록)','5'); 

/** 2018-08-06 이근환 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'daewoongPartPurReqList') ,'gridbox','대웅제약 - 단가계약요청','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'daewoongPartPurReqPartsList') ,'gridbox','대웅제약 - 단가계약요청(상세) - 부품','10'); 

/** 2018-08-06 대웅제약 반영 */

/** 2018-08-07 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptMaintCostList') ,'gridbox','수선유지비 집행현황 (목록)','5','assetRptMaintCostList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptBdIntensityList') ,'gridbox','설비별 고장강도율 (목록)','5','assetRptBdIntensityList'); 


/** 2018-08-07 이지수 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptBdFrequencyList') ,'gridbox','설비별 고장도수율 (목록)','15','assetRptBdFrequencyList'); 

/** 2018-08-08 김은아*/
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'workLetCategList') ,'gridbox','시스템관리 - 작업설정 - 안전작업유형설정','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'workLetCategPointList') ,'gridbox','시스템관리 - 작업설정 - 안전작업유형설정(상세) - 점검항목','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'workLetCategEtcList') ,'gridbox','시스템관리 - 작업설정 - 안전작업유형설정(상세) - 보완사항','10'); 

/** 2018-08-10 10:00 로얄캐닌 적용 */
/** 2018-08-11 대웅제약 반영 */

/** 2018-08-13 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'persPrivDbSetContList') ,'gridbox','대시보드 - 컨텐츠(목록)','5','persPrivDbSetContList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'persPrivDbSetList') ,'gridbox','개인설정 - 대시보드(목록)','5','persPrivDbSetList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovDbConAcList') ,'gridbox','컨텐츠 검색 팝업','5','lovDbConAcList'); 

/** 2018-08-14  9:40 로얄캐닌 적용 */

/** 2018-08-14 최지상*/
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'workServiceList') ,'gridbox','시스템관리 - 작업설정 - 서비스마스터','10'); 

/** 2018-08-14 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrAtList') ,'gridbox','Audit Trail (목록)','5','mgrAtList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrAtHistList') ,'gridbox','Audit Trail History (목록)','5','mgrAtHistList'); 

/** 2018-08-16 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptLccUsDeptList') ,'gridbox','고장TOP(사용부서) (목록)','5','assetRptLccUsDeptList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptLccUsDeptDetailList') ,'gridbox','고장TOP(사용부서) (상세목록)','5','assetRptLccUsDeptDetailList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptMtbfmttrUsDeptList') ,'gridbox','MTBF,MTTR(사용부서) (목록)','5','assetRptMtbfmttrUsDeptList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptMtbfmttrUsDeptDetailList') ,'gridbox','MTBF,MTTR(사용부서) (상세목록)','5','assetRptMtbfmttrUsDeptDetailList'); 

/** 2018-08-16 이근환 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPtMstrStockList') ,'gridbox','부품마스터(상세) - 현재고','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPtMstrRecStatList') ,'gridbox','부품마스터(상세) - 입고이력','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPtMstrIssStatList') ,'gridbox','부품마스터(상세) - 출고이력','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maPtMstrWoPtHistList') ,'gridbox','부품마스터(상세) - 설비사용이력','10'); 

/** 2018-08-17 평화오일씰 반영 */
/** 2018-08-17 안국약품 반영 */
/** 2018-08-17 본사Dream 반영 */
/** 2018-08-20 대웅제약 반영 */
/** 2018-08-21 09:10 오뚜기협력사 반영 */
/** 2018-08-21 16:00 오뚜기협력사 반영 */

/** 2018-08-21 이근환 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'partAdjStkMoveList') ,'gridbox','재고이동','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovPtStckAcList') ,'gridbox','재고선택 Ac Lov','10'); 

/** 2018-08-22 평화오일씰 반영 */
/** 2018-08-23 11:00 동국제약 반영 */

/** 2018-08-27 김남현 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'assetRptAssAssetScoreList') ,'gridbox','설비자산 - Report - 설비등급평가 항목별 점수(목록)','5','assetRptAssAssetScoreList'); 

/** 2018-08-27 김은아 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrRptScrnLogList') ,'gridbox','시스템관리 - Report - 화면접속로그 (목록)','5','mgrRptScrnLogList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrRptLoginLogList') ,'gridbox','시스템관리 - Report - 로그인로그 (목록)','5','mgrRptLoginLogList'); 

/** 2018-08-29 안국약품 반영 */
/** 2018-08-29 본사Dream 반영 */
/** 2018-08-29 대웅제약 반영 */
/** 2018-08-30 평화오일씰 반영 */

/** 2018-08-30 김영주 */
UPDATE TAPGGRIDCOL SET ord_no = ord_no||'0' WHERE pggrid_id =  (SELECT A.pggrid_id FROM TAPGGRID A, TAPAGE b WHERE A.page_id=b.page_id AND b.file_name='maWoPmcResultMstrList' AND A.grid_obj_id = 'gridbox') AND column_id NOT IN ('ISDELCHECK','SEQNO');
UPDATE TAPGGRIDCOL SET ord_no = '0'||ord_no WHERE pggrid_id =  (SELECT A.pggrid_id FROM TAPGGRID A, TAPAGE b WHERE A.page_id=b.page_id AND b.file_name='maWoPmcResultMstrList' AND A.grid_obj_id = 'gridbox') AND column_id NOT IN ('ISDELCHECK','SEQNO') AND LENGTH(ord_no) = 2;
UPDATE TAPGGRIDCOL SET ord_no = '0'||ord_no WHERE pggrid_id =  (SELECT A.pggrid_id FROM TAPGGRID A, TAPAGE b WHERE A.page_id=b.page_id AND b.file_name='maWoPmcResultMstrList' AND A.grid_obj_id = 'gridbox') AND column_id NOT IN ('ISDELCHECK','SEQNO') ;
UPDATE TAPGGRIDCOL SET key_no = 'toolNo' , ord_no = '0063' WHERE pggrid_id = (SELECT A.pggrid_id FROM TAPGGRID A, TAPAGE b WHERE A.page_id=b.page_id AND b.file_name='maWoPmcResultMstrList' AND A.grid_obj_id = 'gridbox') AND column_id = 'EQUIPNO';
UPDATE TAPGGRIDCOL SET key_no = 'toolName', ord_no = '0064'  WHERE pggrid_id = (SELECT A.pggrid_id FROM TAPGGRID A, TAPAGE b WHERE A.page_id=b.page_id AND b.file_name='maWoPmcResultMstrList' AND A.grid_obj_id = 'gridbox') AND column_id = 'EQUIPDESC';
INSERT INTO TAPGGRIDCOL (PGGRIDCOL_ID,pggrid_id,column_id,ord_no,width,TYPE,align,SORT,hidden,key_type, key_no, system_col) VALUES( sqaPGGRIDCOL_ID.NEXTVAL,(SELECT A.pggrid_id FROM TAPGGRID A, TAPAGE b WHERE A.page_id=b.page_id AND b.file_name='maWoPmcResultMstrList' AND A.grid_obj_id = 'gridbox') ,'usageDeptDesc','0065','200','ro','left','str','FALSE','LABEL','usedDept','N'); 
INSERT INTO TAPGGRIDCOL (PGGRIDCOL_ID,pggrid_id,column_id,ord_no,width,TYPE,align,SORT,hidden,key_type, key_no, system_col) VALUES( sqaPGGRIDCOL_ID.NEXTVAL,(SELECT A.pggrid_id FROM TAPGGRID A, TAPAGE b WHERE A.page_id=b.page_id AND b.file_name='maWoPmcResultMstrList' AND A.grid_obj_id = 'gridbox') ,'pequipNo','0066','120','ro','center','str','FALSE','LABEL','equipNo','N'); 
INSERT INTO TAPGGRIDCOL (PGGRIDCOL_ID,pggrid_id,column_id,ord_no,width,TYPE,align,SORT,hidden,key_type, key_no, system_col) VALUES( sqaPGGRIDCOL_ID.NEXTVAL,(SELECT A.pggrid_id FROM TAPGGRID A, TAPAGE b WHERE A.page_id=b.page_id AND b.file_name='maWoPmcResultMstrList' AND A.grid_obj_id = 'gridbox') ,'pequipDesc','0067','150','ro','left','str','FALSE','LABEL','parEquipDesc','N'); 
INSERT INTO TAPGGRIDCOL (PGGRIDCOL_ID,pggrid_id,column_id,ord_no,width,TYPE,align,SORT,hidden,key_type, key_no, system_col) VALUES( sqaPGGRIDCOL_ID.NEXTVAL,(SELECT A.pggrid_id FROM TAPGGRID A, TAPAGE b WHERE A.page_id=b.page_id AND b.file_name='maWoPmcResultMstrList' AND A.grid_obj_id = 'gridbox') ,'pequipUsaDeptDesc','0068','200','ro','left','str','FALSE','LABEL','pUsageDept','N'); 

/** 2018-09-02 대웅제약 반영 */

/** 2018-09-04 최지상*/
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (select aa.page_id from tapage aa where aa.file_name = 'mgrPtWhBinList') ,'gridbox','시스템관리 - 부품창고(상세) - 보관위치(목록)','10'); 

/** 2018-09-04 10:20 오뚜기협력사 반영 */

/** 2018-09-04 이지수 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRIDCOL_ID.NEXTVAL , (select aa.page_id from TAPAGE aa where aa.file_name = 'assAssetScoreCopyLov') ,'main_gridbox','평가결과복사 Lov 메인리스트','5'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRIDCOL_ID.NEXTVAL , (select aa.page_id from TAPAGE aa where aa.file_name = 'assAssetScoreCopyLov') ,'sub_gridbox','평가결과복사 Lov 서브리스트','5'); 

/** 2018-09-05 평화오일씰 반영 */

/** 2018-09-06 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptDailyEngList') ,'gridbox','에너지 사용량(일별) (목록)','5','workRptDailyEngList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptDailyEngDetailList') ,'gridbox','부서별 작업진행현황(목록)','5','workRptDailyEngDetailList'); 

/** 2018-09-10 대웅제약 반영 */
/** 2018-09-10 19:00 동국제약 반영 */

/**  2018-09-12 최지상 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (select aa.page_id from tapage aa where aa.file_name = 'invtRptMonInvtAmtList') ,'gridbox','지표관리 - 투자지표 - 월별투자집행금액(목록)','10'); 

/** 2018-09-12 대웅제약 반영 */


/**  2018-09-12 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptTotEngList') ,'gridbox','에너지사용량 (집계) (목록)','5','workRptTotEngList');  
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptTotEngLocDetailList') ,'gridbox','위치별 에너지사용량 (집계) (상세목록차트)','5','workRptTotEngLocDetailList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptTotEngUsageDeptDetailList') ,'gridbox','사용부서별 에너지사용량 (집계) (상세목록차트)','5','workRptTotEngUsageDeptDetailList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptTotEngCtgDetailList') ,'gridbox','종류별 에너지사용량 (집계) (상세목록차트)','5','workRptTotEngCtgDetailList'); 

/** 2018-09-13 김은아 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'lovWorkPmDInsList') ,'gridbox','작업관리 - 예방작업설정 - 예방점검설정 - 점검항목 (Lov)','5','lovWorkPmDInsList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqCatalogPointList') ,'gridbox','설비자산 - 설비종류 - 점검항목 (목록)','5','maEqCatalogPointList'); 

/**  2018-09-14 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtRptInvtCategList') ,'gridbox','투자구분분석 (목록)','5','invtRptInvtCategList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtRptInvtCategDetailList') ,'gridbox','투자구분분석 (상세목록)','5','invtRptInvtCategDetailList');

/** 2018-09-14 양소영 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maBdPointWoReqList') ,'gridbox','작업관리 - Report - 이상점검이력 - 작업요청(목록)','5','maBdPointWoReqList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maBdPointWoPlanList') ,'gridbox','작업관리 - Report - 이상점검이력 - 작업계획(목록)','5','maBdPointWoPlanList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maBdPointWoRsltList') ,'gridbox','작업관리 - Report - 이상점검이력 - 작업계획(목록)','5','maBdPointWoRsltList'); 

/** 2018-09-14 양소영 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovWoReqAcList') ,'gridbox','Work Req Popup','8','lovWoReqAcList'); 

/** 2018-09-14 대웅제약 반영 */

/** 2018-09-17 최지상 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (select aa.page_id from tapage aa where aa.file_name = 'invtRptLeadTimeList') ,'gridbox','지표관리 - 투자지표 - 투자진행 LeadTime 분석(목록)','10'); 

/** 2018-09-18 평화오일씰 반영 */
/** 2018-09-18 안국약품 반영 */
/** 2018-09-21 08:30 오뚜기협력사 반영 */
/** 2018-09-21 대웅제약 반영 */
/** 2018-09-28 평화오일씰 반영 */

/** 2018-10-01 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmInsApprSchedList') ,'gridbox','예방점검계획승인 - 점검작업 Grid','8');

/** 2018-10-01 대웅제약 반영 */
/** 2018-10-02 안국약품 반영 */
/** 2018-10-02 평화오일씰 반영 */
/** 2018-10-05 평화오일씰 반영 */

/**  2018-10-05 노정현*/

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'persReqHistList') ,'gridbox','결재요청이력 Grid','8');


INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'persApprovedHistList') ,'gridbox','결재이력  Grid','8');

/** 2018-10-08 대웅제약 반영 */

/** 2018-10-09 노정현 */

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrUserPlantauthList') ,'gridbox','사용자 공장권한 Grid','8');


/** 2018-10-10 평화오일씰 반영 */

/** 2018-10-10 박철완 */
INSERT INTO TAPGGRIDCOL (PGGRIDCOL_ID,pggrid_id,column_id,ord_no,width,type,align,sort,hidden,key_type, key_no, system_col, file_name,grid_obj_id) 
values( sqaPGGRIDCOL_ID.nextval,(select a.pggrid_id from TAPGGRID a, tapage b where a.page_id=b.page_id and b.file_name='maCdUsrCdList' and a.grid_obj_id = 'gridbox') ,'MNGCD','167','50','ro','left','str','true','LABEL','mngCd','Y','maCdUsrCdList','gridbox'); 

/** 2018-10-10 안국약품반영 반영 */
/** 2018-10-10 18:00 동국제약 반영 */
/** 2018-10-11 평화오일씰 반영 */
/** 2018-10-14 대웅제약 반영 */
/** 2018-10-16 09:30 동국제약 반영 */


 /** 2018-10-16 12:00 오뚜기 협력사 반영 */
/** 2018-10-18 평화오일씰 반영 */
/** 2018-10-19 10:00 동국제약 반영 */

/** 2018-10-19 이지수 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES(sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'lovPartPurBuyPnList') ,'gridbox','현장신청부품 선택 LOV (목록)','10','lovPartPurBuyPnList'); 

/** 2018-10-21 대웅제약 반영 */
/** 2018-10-23 안국약품 반영 */

/** 2018-10-23 최지상 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'partListBinList') ,'gridbox','부품목록 - 보관위치(목록)','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'lovPartListBinList') ,'gridbox','부품목록 - 보관위치(LOV)','10'); 

/** 2018-10-23 이근환 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovAccountList') ,'gridbox','Account Popup','10'); 

/** 2018-10-23 평화오일씰 반영 */
/** 2018-10-23 오뚜기 협력사 반영 */

/** 2018-10-24 최지상 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'partListSafQtyList') ,'gridbox','부품목록 - 안전재(목록)','10'); 

/** 2018-10-24 김은아 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partRptMonthlyStockList') ,'gridbox','부품관리 - REPORT - 부품수불부(목록)','5','partRptMonthlyStockList'); 

/** 2018-10-24 평화오일씰 반영 */
/** 2018-10-25 대웅제약 반영 */
/** 2018-10-25 11:20 동국제약 반영 */
/** 2018-10-26 평화오일씰 반영 */

/** 2018-10-26 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPlanApprEquipList') ,'gridbox','교정작업계획승인 - 계측기 (목록)','5','workPlanApprEquipList'); 

/** 2018-10-26 대웅제약 반영 */
/** 2018-10-27 평화오일씰 반영 */
/** 2018-10-29 본사Dream 반영 */

/** 2018-10-30 이근환 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'partIssWoPartList') ,'gridbox','부품관리 - 출고 - 부품출고(상세) - 작업','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovWoPartsAcList') ,'gridbox','Wo Part Popup','10'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovPtIssAcList') ,'gridbox','Part Iss. Popup','10'); 

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

/** 2018-11-07 김은아 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maLineTimeSetList') ,'gridbox','가동달력설정 (목록)','5','maLineTimeSetList'); 

/** 2018-11-07 대웅제약 반영 */
/** 2018-11-07 평화오일씰 반영 */
/** 2018-11-09 평화오일씰 반영 */

/** 2018-11-09 김은아 */
DELETE TAPGGRID WHERE page_id = (SELECT page_id FROM TAPAGE WHERE file_name = 'maLineTimeSetList');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES(sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrCalLineTimeSetList') ,'gridbox','가동달력설정 (목록)','5','mgrCalLineTimeSetList'); 

/** 2018-11-09 김은아 추가 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES(sqaPGGRID_ID.NEXTVAL, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrCalCompWkrcalList') ,'gridbox','근무일달력설정(목록)','5','mgrCalCompWkrcalList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrCalCompWkrcalDowsetList') ,'gridbox','시스템관리 - 작업설정 - 근무일달력설정 - 휴무요일설정(목록)','5','mgrCalCompWkrcalDowsetList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrCalCompWkrcalDaysetList') ,'gridbox','시스템관리 - 작업설정 - 근무일달력설정 - 휴무일설정(목록)','5','mgrCalCompWkrcalDaysetList'); 

/** 2018-11-09 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultCompIntfList') ,'gridbox','컨설트 - 회사설정 - 인터페이스설정(목록)','5','consultCompIntfList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultCompIntfMapList') ,'gridbox','인터페이스맵핑(목록)','5','consultCompIntfMapList'); 

/** 2018-11-12 대웅제약 반영 */


/** 2018-11-12 김은아 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrCalLineTimeDowSetList') ,'gridbox','시스템관리 - 작업설정 - 가동달력설정 - 요일별설정(목록)','5','mgrCalLineTimeDowSetList'); 

/** 2018-11-13 평화오일씰 반영 */
/** 2018-11-13 안국약품 반영 */
/** 2018-11-14 1015 오뚜기협력사 반영 */
/** 2018-11-14 평화오일씰 반영 */
/** 2018-11-15 08:15 동국제약 반영 */
/** 2018-11-15 안국약품 반영 */
/** 2018-11-15 평화오일씰 반영 */

/** 2018-11-15 이근환 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'budgetAccountList') ,'gridbox','예산계정','10'); 

/** 2018-11-16 평화오일씰 반영 */

/** 2018-11-19 김남현 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovPoItemAcList') ,'gridbox','부품관리 - 입고 - 구매입고 - 발주선택(Lov)','5','lovPoItemAcList'); 

/** 2018-11-19 안국약품 반영 */
/** 2018-11-20 평화오일씰 반영 */

/**2018-11-20 김정우 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovToolPartsAcList') ,'gridbox','공기구LOV 리스트','10','lovToolPartsAcList'); 

/** 2018-11-21 평화오일씰 반영 */

/** 2018-11-21 김정우 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultToolList') ,'gridbox','투입공기구 리스트','5','maWoResultToolList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmRplToolList') ,'gridbox','투입공기구 리스트','5','maWoResultBmRplToolList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) 
VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmOilToolList') ,'gridbox','투입공기구 리스트','5','maWoResultBmOilToolList'); 

/** 2018-11-22 평화오일씰 반영 */
/** 2018-11-23 평화오일씰 반영 */

/** 2018-11-23 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrRptLoginTryLogList') ,'gridbox','로그인 Try Log(목록)','5','mgrRptLoginTryLogList'); 

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

/** 2018-12-07 이지수 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) VALUES( sqaPGGRID_ID.nextval , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBdPointDocLibList') ,'gridbox','예방점검 - 이상점검결과등록 - 첨부문서(목록)','10'); 

/** 2018-12-10 양소영 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'woPlanWoLetList') ,'gridbox','안전작업(목록)','8','woPlanWoLetList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovWorkLetCategList') ,'gridbox','안전작업유형 LOV','8','lovWorkLetCategList'); 

/** 2018-12-10 평화오일씰 반영 */
/** 2018-12-11 안국약품 반영 */
/** 2018-12-12 평화오일씰 반영 */
/** 2018-12-13 평화오일씰 반영 */
/** 2018-12-14 평화오일씰 반영 */

/** 2018-12-14 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrMsgRecList') ,'gridbox','메시지 수신설정(목록)','5','mgrMsgRecList');

/** 2018-12-14 동국제약 반영 */

/** 2018-12-17 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultCompMsgList') ,'gridbox','메시지 설정(메일, SMS)(목록)','5','consultCompMsgList'); 

/** 2018-12-17 평화오일씰 반영 */
/** 2018-12-18 평화오일씰 반영 */
/** 2018-12-18 안국약품 반영 */
/** 2018-12-19 평화오일씰 반영 */
/** 2018-12-20 평화오일씰 반영 */
/** 2018-12-20 동국제약 반영 */
/** 2018-12-21 평화오일씰 반영 */
/**2018-12-24 13:38 오뚜기 협력사 반영 */
/** 2018-12-26 평화오일씰 반영 */

/** 2018-12-26 양소영 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'workLetList') ,'gridbox','작업관리 - 안전작업(목록)','10','workLetList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'workLetPermitList') ,'gridbox','작업관리 - 안전작업 - 안전작업허가서(목록)','5','workLetPermitList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'workLetPermitPointList') ,'gridbox','안전작업 - 안전작업허가서 - 점검항목(목록)','5','workLetPermitPointList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'workLetPermitCraftList') ,'gridbox','안전작업 - 안전작업허가서 - 작업자(목록)','5','workLetPermitCraftList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'workLetPermitDocLibList') ,'gridbox','안전작업 - 안전작업허가서 - 첨부문서(목록)','5','workLetPermitDocLibList'); 

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovWorkLetCategPointList') ,'gridbox','안전작업허가서 표준점검항목 LOV','8','lovWorkLetCategPointList'); 

/** 2018-12-27 안국약품 반영 */
/** 2018-12-27 평화오일씰 반영 */
/** 2019-01-02 본사Dream 반영 */

/** 2019-01-03 김영주 */
UPDATE TAPGGRID SET file_name = 'consultPgmMsgList' WHERE file_name = 'consultCompMsgList';
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'lovMsgCategList') ,'gridbox','메시지타입 LOV','5','lovMsgCategList');

/** 2019-01-04 평화오일씰 반영 */

/** 2019-01-04 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'persPrivInfoMsgEmpList') ,'gridbox','메시지 수신설정(목록)','5','persPrivInfoMsgEmpList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'lovMsgCompList') ,'gridbox','메시지 전송유형 LOV','5','lovMsgCompList'); 

/** 2019-01-08 안국약품 반영 */

/** 2019-01-11 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'lovUsrPlantAuthList') ,'gridbox','공장권한 LOV','5','lovUsrPlantAuthList');

/** 2019-01-16 동국제약 반영 */

/** 2019-01-30 이근환 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'mgrIntfParams') ,'gridbox','인터페이스 파라미터 popup','10'); 

/** 2019-01-31 평화오일씰 반영 */
/** 2019-02-11 본사Dream 반영 */
/** 2019-02-12 안국약품 반영 */
/** 2019-03-04 안국약품 반영 */



/** 2019-02-18 양소영 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'workCalPmInsApprCompList') ,'gridbox','미점검승인(목록) Grid','8','workCalPmInsApprCompList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'workCalPmInsApprNotList') ,'gridbox','미점검승인(목록) Grid','8','workCalPmInsApprNotList'); 

/** 2019-03-07 동국제약 반영 */

/** 2019-03-11 이근환 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workRptPmwCmptDetailList') ,'gridbox','','10','workRptPmwCmptDetailList'); 


/** 2019-03-11 이근환 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workRptPmwDetailList') ,'gridbox','','10','workRptPmwDetailList'); 


/** 2019-03-12 이근환 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workRptWoPmwCmptRateList') ,'gridbox','','10','workRptWoPmwCmptRateList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workRptWoPmwCmptDetailList') ,'gridbox','','10','workRptWoPmwCmptDetailList'); 


/** 2019-03-12 김영주 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL, (select aa.page_id from tapage aa where aa.file_name = 'reqRptEmWoGenDetailList') ,'gridbox','','10','reqRptEmWoGenDetailList'); 


/** 2019-03-11 양소영 */
UPDATE TAPGGRID SET height='10', file_name='workRptPmiEquipCmptRateList' WHERE PAGE_ID=(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name='workRptPmiEquipCmptRateList'); 

/** 2019-03-12 양소영 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES(sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'workRptPmiEquipCmptDetailList') ,'gridbox','예방점검설비 실행 비율 - 상세리스트','10','workRptPmiEquipCmptDetailList'); 


/** 2019-03-12 양소영 */
UPDATE TAPGGRID SET height='10',file_name='workRptPmiCmptRateList' WHERE PAGE_ID=(SELECT aa.page_id FROM TAPAGE AA WHERE aa.file_name = 'workRptPmiCmptRateList');

/** 2019-03-12 이근환 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workRptPmiCmptDetailList') ,'gridbox','','10','workRptPmiCmptDetailList'); 


/** 2019-03-12 양소영 */
UPDATE TAPGGRID SET height='10',file_name='workRptPmiEquipPlanRateList' WHERE PAGE_ID=(SELECT aa.page_id FROM TAPAGE AA WHERE aa.file_name = 'workRptPmiEquipPlanRateList');

/** 2019-03-12 이근환 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) 
VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workRptPmiEquipPlanDetailList') ,'gridbox','','10','workRptPmiEquipPlanDetailList'); 


/** 2019-03-12 김은아 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptWoEndDetailList') ,'gridbox','','10','workRptWoEndDetailList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES(sqaPGGRID_ID.NEXTVAL, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqRptPreWoPlanDetailList') ,'gridbox','','10','reqRptPreWoPlanDetailList'); 

/** 2019-03-12 김남현 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'reqRptPreWoreqDetailList') ,'gridbox','','10','reqRptPreWoreqDetailList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.NEXTVAL , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'reqRptWoPlanCmptDetailList') ,'gridbox','','10','reqRptWoPlanCmptDetailList'); 

/** 2019-03-13 현대일렉트릭 반영 */
/** 2019-03-26 오뚜기 반영 */


/** 2019-03-26 김정우 */
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name)
VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='WoReqListFragment'),'woreq_list_rv','작업요청목록','5','WoReqListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name)
VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='WoReqPhotoListFragment'),'photo_list_rv','작업요청 사진목록','5','WoReqPhotoListFragment');

INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='AssetListFragment') ,'asset_list_rv','설비자산목록','5','AssetListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='AssetDocumentListFragment') ,'document_list_rv','첨부문서목록','5','AssetDocumentListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='AssetToolListFragment') ,'eqtool_list_rv','계측기목록','5','AssetToolListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='CalibrationDialog') ,'calibration_dialog_rv','설비목록','5','CalibrationDialog');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='DeptDialog') ,'dept_dialog_rv','부서목록','5','DeptDialog');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='EmpDialog') ,'emp_dialog_rv','사원목록','5','EmpDialog');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='EquipDialog') ,'equip_dialog_rv','설비목록','5','EquipDialog');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='EqAsmbDialog') ,'eqasmb_dialog_rv','부위목록','5','EqAsmbDialog');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='EqlocDialog') ,'eqloc_dialog_rv','위치목록','5','EqlocDialog');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='FailureDialog') ,'failure_dialog_rv','분류목록','5','FailureDialog');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='PartsDialog') ,'parts_dialog_rv','부품목록','5','PartsDialog');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='PlantDialog') ,'plant_dialog_rv','공장목록','5','PlantDialog');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='SyscodeDialog') ,'syscode_dialog_rv','코드목록','5','SyscodeDialog');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='UsrcodeDialog') ,'usrcode_dialog_rv','코드목록','5','UsrcodeDialog');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='WhDialog') ,'wh_dialog_rv','창고목록','5','WhDialog');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='WkctrDialog') ,'wkctr_dialog_rv','작업목록','5','WkctrDialog');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='IssListFragment') ,'iss_list_rv','부품출고목록','5','IssListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='PmiDayListFragment') ,'pmi_day_list_rv','일상점검목록','5','PmiDayListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='PmiDayDetailFragment') ,'pmi_day_detail_rv','점검항목목록','5','PmiDayDetailFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='PmiDayPhotoListFragment') ,'photo_list_rv','사진첨부목록','5','PmiDayPhotoListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='PmiDayHistListFragment') ,'pmi_day_hist_rv','이력보기목록','5','PmiDayHistListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='PmiPartListFragment') ,'pmi_part_list_rv','Part Change목록','5','PmiPartListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='PmiPartDetailFragment') ,'pmi_part_detail_rv','점검항목목록','5','PmiPartDetailFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='PmiPartPhotoListFragment') ,'photo_list_rv','사진첨부목록','5','PmiPartPhotoListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='PmiPartHistListFragment') ,'pmi_part_hist_rv','이력보기목록','5','PmiPartHistListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='PmiPatrolListFragment') ,'pmi_patrol_list_rv','순회점검목록','5','PmiPatrolListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='PmiPatrolDetailFragment') ,'pmi_patrol_detail_rv','점검항목목록','5','PmiPatrolDetailFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='PmiPatrolPhotoListFragment') ,'photo_list_rv','사진첨부목록','5','PmiPatrolPhotoListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='PmiPatrolHistListFragment') ,'pmi_patrol_hist_rv','이력보기목록','5','PmiPatrolHistListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='PmiRoutineListFragment') ,'pmi_routine_list_rv','정기점검목록','5','PmiRoutineListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='PmiRoutineDetailFragment') ,'pmi_routine_detail_rv','점검항목목록','5','PmiRoutineDetailFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='PmiRoutinePhotoListFragment') ,'photo_list_rv','사진첨부목록','5','PmiRoutinePhotoListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='PmiRoutineHistListFragment') ,'pmi_routine_hist_rv','이력보기목록','5','PmiRoutineHistListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='WoResListFragment') ,'wores_list_rv','요청접수목록','5','WoResListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='WoResPhotoListFragment') ,'photo_list_rv','사진목록','5','WoResPhotoListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='WoResWorkListFragment') ,'wores_work_list_rv','처리사항목록','5','WoResWorkListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='StktakeHdrListFragment') ,'stktakehdr_list_rv','부품실사목록','5','StktakeHdrListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='StktakeListFragment') ,'stktake_list_rv','부품목록','5','StktakeListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='StockListFragment') ,'stock_list_rv','현재고목록','5','StockListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='CalListFragment') ,'cal_list_rv','검교정목록','5','CalListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='CalBaseEqListFragment') ,'base_eq_list_rv','표준기목록','5','CalBaseEqListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='CalCalibGnlValueListFragment') ,'wocalib_gnl_value_list_rv','측정값목록(일반)','5','CalCalibGnlValueListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='CalCalibGnlValueDetailFragment') ,'wocalib_gnl_value_detail_list_rv','측정값상세목록(일반)','5','CalCalibGnlValueDetailFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='CalCalibPrsValueListFragment') ,'wocalib_prs_value_list_rv','측정값목록(압력)','5','CalCalibPrsValueListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='CalCalibPrsValueDetailFragment') ,'wocalib_prs_value_detail_list_rv','측정값상세목록(압력)','5','CalCalibPrsValueDetailFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='CalCalibValueListFragment') ,'wocalib_prs_value_list_rv','측정값목록','5','CalCalibValueListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='CalCraftListFragment') ,'wocraft_list_rv','작업자목록','5','CalCraftListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='CalPhotoListFragment') ,'photo_list_rv','사진첨부목록','5','CalPhotoListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='WoHistListFragment') ,'wo_hist_rv','작업이력목록','5','WoHistListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='InspectionListFragment') ,'inspection_list_rv','예방점검목록','5','InspectionListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='InspectionDetailFragment') ,'inspection_detail_rv','예방점검항목 목록','5','InspectionDetailFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='InspectionPhotoListFragment') ,'photo_list_rv','사진첨부목록','5','InspectionPhotoListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='InspectionHistListFragment') ,'inspection_hist_rv','이력보기목록','5','InspectionHistListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='PmworkListFragment') ,'pmwork_list_rv','계획작업목록','5','PmworkListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='PmworkCraftListFragment') ,'wocraft_list_rv','작업자목록','5','PmworkCraftListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='PmworkPartsListFragment') ,'woparts_list_rv','투입부품목록','5','PmworkPartsListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='PmworkPhotoListFragment') ,'photo_list_rv','사진첨부목록','5','PmworkPhotoListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='UnplanListFragment') ,'unplan_list_rv','돌발작업목록','5','UnplanListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='UnplanCraftListFragment') ,'wocraft_list_rv','작업자목록','5','UnplanCraftListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='UnplanPartsListFragment') ,'woparts_list_rv','투입부품목록','5','UnplanPartsListFragment');
INSERT INTO TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name) VALUES (SQAPGGRID_ID.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='UnplanPhotoListFragment') ,'photo_list_rv','사진첨부목록','5','UnplanPhotoListFragment');
UPDATE TAPGGRID set grid_obj_id = 'wocalibvalue_list_rv' WHERE file_name='CalCalibValueListFragment' and grid_obj_id='wocalib_prs_value_list_rv';


/** 2019-03-26 14:30 오뚜기 반영 */
/** 2019-03-26 안국약품 반영 */
/** 2019-03-27 로얄캐닌 반영 */
/** 2019-03-28 평화오일씰 반영 */
/** 2019-04-08 평화오일씰 반영 */
/** 2019-04-16 오뚜기 반영 */

/** 2019-04-17 김은아 */
update TAPGGRID set CRE_DATE = '20190327132010';
update TAPGGRID set UPD_DATE = '20190327132010';

/** 2019-04-18 현대일렉트릭 반영 */
/** 2019-04-26 현대일렉트릭 반영 */


/** 2019-04-26 이지수 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.nextval , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListDeptSchedList') ,'gridbox','업체별 작업스케쥴','10','workListDeptSchedList');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.nextval , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListDeptSchedListDeptList') ,'gridbox','업체별 작업스케쥴탭부서별 작업','10','workListDeptSchedListDeptList');

/** 2019-05-02 현대일렉트릭 반영 */
/** 2019-05-12 백광산업 반영 */
/** 2019-05-13 평화오일씰 반영 */


/** 2019-04-12 이지수 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES(sqaPGGRID_ID.nextval , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListUInsList') ,'gridbox','에너지측정주기설정','10','workPmListUInsList');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES(sqaPGGRID_ID.nextval , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmPointUInsList') ,'gridbox','사용량 항목','8','workPmPointUInsList');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES(sqaPGGRID_ID.nextval , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmMsTimeUInsList') ,'gridbox','측정시간','8','workPmMsTimeUInsList');


/** 2019-04-29 양소영 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'workListEnergyMstrList') ,'gridbox','','10','workListEnergyMstrList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'workListEnergyPointList') ,'gridbox','','5','workListEnergyPointList'); 
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'workListEnergyPointDocLibList') ,'gridbox','','3','workListEnergyPointDocLibList'); 

/** 2019-04-29 양소영 */
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT, FILE_NAME) VALUES( sqaPGGRID_ID.nextval , (select aa.page_id from tapage aa where aa.file_name = 'lovWorkPmListUInsList') ,'gridbox','','8','lovWorkPmListUInsList'); 


/** 2019-04-30 이근환 */
INSERT INTO tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) 
VALUES( sqapggrid_id.NEXTVAL,(SELECT page_id FROM tapage WHERE file_name='mgrUsrGrpPageAuthList'),'gridbox', (SELECT description FROM tapage WHERE file_name = 'mgrUsrGrpPageAuthList') ,'10','mgrUsrGrpPageAuthList','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 
INSERT INTO tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) 
VALUES( sqapggrid_id.NEXTVAL,(SELECT page_id FROM tapage WHERE file_name='mgrUsrGrpPageAuthBtnList'),'gridbox', (SELECT description FROM tapage WHERE file_name = 'mgrUsrGrpPageAuthBtnList') ,'10','mgrUsrGrpPageAuthBtnList','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 
INSERT INTO tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) 
VALUES( sqapggrid_id.NEXTVAL,(SELECT page_id FROM tapage WHERE file_name='mgrUsrGrpPageAuthTabList'),'gridbox', (SELECT description FROM tapage WHERE file_name = 'mgrUsrGrpPageAuthTabList') ,'10','mgrUsrGrpPageAuthTabList','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 

/** 2019-05-16 현대일렉트릭 반영 */


/** 2019-05-14 노정현 */
 insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval,(select page_id from tapage where file_name='consultCompUsrGrpList'),'gridbox', (select description from tapage where file_name = 'consultCompUsrGrpList') ,'15','consultCompUsrGrpList','DREAM','2.01','20190331132020',to_char(sysdate,'yyyymmddhh24miss'));
 insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval,(select page_id from tapage where file_name='consultCompUsrGrpWebAuthList'),'gridbox', (select description from tapage where file_name = 'consultCompUsrGrpWebAuthList') ,'8','consultCompUsrGrpWebAuthList','DREAM','2.01','20190331132020',to_char(sysdate,'yyyymmddhh24miss')); 
 insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval,(select page_id from tapage where file_name='consultCompUsrGrpBeeAuthList'),'gridbox', (select description from tapage where file_name = 'consultCompUsrGrpBeeAuthList') ,'8','consultCompUsrGrpBeeAuthList','DREAM','2.01','20190331132020',to_char(sysdate,'yyyymmddhh24miss')); 
 insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval,(select page_id from tapage where file_name='consultCompUsrGrpPageAuthList'),'gridbox', (select description from tapage where file_name = 'consultCompUsrGrpPageAuthList') ,'8','consultCompUsrGrpPageAuthList','DREAM','2.01','20190331132020',to_char(sysdate,'yyyymmddhh24miss')); 
 insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval,(select page_id from tapage where file_name='consultCompUsrGrpBtnAuthList'),'gridbox', (select description from tapage where file_name = 'consultCompUsrGrpBtnAuthList') ,'8','consultCompUsrGrpBtnAuthList','DREAM','2.01','20190331132020',to_char(sysdate,'yyyymmddhh24miss')); 

/** 2019-05-17 현대일렉트릭 반영 */
/** 2019-05-21 안국약품 반영 */

/** 2019-05-22 오뚜기 본사 반영 */
 
/** 2019-05-22 현대일렉트릭 반영 */


/** 2019-05-21 이지수 */
insert into TAPGGRID (pggrid_id, page_id, grid_obj_id, description, height, file_name)
values (sqapggrid_id.nextval, (select page_id from TAPAGE where file_name = 'workPmiRInsPointValueDocLibList'), 'gridbox', '정기점검수치항목 첨부문서', '10','workPmiRInsPointValueDocLibList');

/** 2019-05-23 평화오일씰 반영 */
/** 2019-05-23 현대일렉트릭 반영 */
/** 2019-05-24 오뚜기 본사 반영 */
/** 2019-05-29 현대일렉트릭 반영 */
/** 2019-05-30 현대일렉트릭 반영 */
/** 2019-06-03 현대일렉트릭 반영 */
/** 2019-06-05 현대일렉트릭 반영 */


/** 2019-05-22 김영주 */
INSERT INTO TAPGGRID(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) VALUES( sqapggrid_id.NEXTVAL,(SELECT page_id FROM TAPAGE WHERE file_name='maWoDailyLibList'),'gridbox', (SELECT description FROM TAPAGE WHERE file_name = 'maWoDailyLibList') ,'3','maWoDailyLibList','DREAM','2.00','20190331132020',TO_CHAR(SYSDATE,'yyyymmddhh24miss')); 

/** 2019-06-07 현대일렉트릭 반영 */
/** 2019-06-10 오뚜기 본사 반영 */
/** 2019-06-11 현대일렉트릭 반영 */
/** 2019-06-12 평화오일씰 반영 */
/** 2019-06-12 표준 Dream 반영 */
/** 2019-06-14 표준 Dream 반영 */


/** 2019-06-14 김남현 */
insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval,(select page_id from tapage where file_name='persPrivNoticeList'),'gridbox', (select description from tapage where file_name = 'persPrivNoticeList') ,'10','persPrivNoticeList','','','20190331132020',to_char(sysdate,'yyyymmddhh24miss')); 

/** 2019-06-19 현대일렉트릭 반영 */
/** 2019-06-19 오뚜기 본사 반영 */
/** 2019-06-24 표준 Dream 반영 */
/** 2019-06-25 현대일렉트릭 반영 */

/** 2019-07-02 오뚜기 본사 반영 */

/** 2019-07-03 현대일렉트릭 반영 */
/** 2019-07-11 평화오일씰 반영 */
/** 2019-07-12 백광 반영 */


/** 2019-07-16 김남현 */
insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from tapage where file_name='workRptPmiPointValueList') ,'gridbox', (select description from tapage where file_name = 'workRptPmiPointValueList') ,'10','workRptPmiPointValueList','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 

/** 2019-07-23 표준 Dream 반영 */
/** 2019-07-24 로얄캐닌 반영 */
/** 2019-07-24 Dream3 반영 */


/** 2019-08-07 김남현 */
insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from tapage where file_name='workAlarmList') ,'gridbox', (select description from tapage where file_name = 'workAlarmList') ,'10','workAlarmList','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 
insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from tapage where file_name='workAlarmReqList') ,'gridbox', (select description from tapage where file_name = 'workAlarmReqList') ,'5','workAlarmReqList','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss'));

/** 291 이지수 */
 insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from tapage where file_name='workRptWorkTypeRptByEmpList') ,'gridbox', (select description from tapage where file_name = 'workRptWorkTypeRptByEmpList') ,'10','workRptWorkTypeRptByEmpList','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 
 insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from tapage where file_name='workRptWorkTypeRptByEmpMonth') ,'gridbox', (select description from tapage where file_name = 'workRptWorkTypeRptByEmpMonth') ,'10','workRptWorkTypeRptByEmpMonth','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 
 insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from tapage where file_name='workRptWorkTypeRptByEmpWoType') ,'gridbox', (select description from tapage where file_name = 'workRptWorkTypeRptByEmpWoType') ,'10','workRptWorkTypeRptByEmpWoType','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 

/** 2019-08-12 오뚜기(OEMS) 반영 */


/** 2019-07-11 이근환 */
insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) 
values( sqapggrid_id.nextval, (select page_id from tapage where file_name='consultPgmSettingUpdList') ,'gridbox', (select description from tapage where file_name = 'consultPgmSettingUpdList') ,'10','consultPgmSettingUpdList','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 
insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) 
values( sqapggrid_id.nextval, (select page_id from tapage where file_name='consultProgramUploadDataScriptList') ,'gridbox', (select description from tapage where file_name = 'consultProgramUploadDataScriptList') ,'8','consultProgramUploadDataScriptList','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 


/** 2019-08-21 연우 반영 */

/** 2019-08-26 동국제약 반영 */
/** 2019-08-26 연우 반영 */

/** youngjoo38_306 김영주 */
INSERT INTO TAPGGRID(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) VALUES( sqapggrid_id.NEXTVAL, (SELECT page_id FROM TAPAGE WHERE file_name='consultCdSysFieldList') ,'gridbox', (SELECT description FROM TAPAGE WHERE file_name = 'consultCdSysFieldList') ,'5','consultCdSysFieldList','DREAM','2.00',TO_CHAR(SYSDATE,'yyyymmddhh24miss'),TO_CHAR(SYSDATE,'yyyymmddhh24miss'));

INSERT INTO TAPGGRID(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) VALUES( sqapggrid_id.NEXTVAL, (SELECT page_id FROM TAPAGE WHERE file_name='mgrCdSysList') ,'gridbox', (SELECT description FROM TAPAGE WHERE file_name = 'mgrCdSysList') ,'5','mgrCdSysList','DREAM','2.01',TO_CHAR(SYSDATE,'yyyymmddhh24miss'),TO_CHAR(SYSDATE,'yyyymmddhh24miss')); 
INSERT INTO TAPGGRID(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) VALUES( sqapggrid_id.NEXTVAL, (SELECT page_id FROM TAPAGE WHERE file_name='mgrCdSysCodeList') ,'gridbox', (SELECT description FROM TAPAGE WHERE file_name = 'mgrCdSysCodeList') ,'5','mgrCdSysCodeList','DREAM','2.00',TO_CHAR(SYSDATE,'yyyymmddhh24miss'),TO_CHAR(SYSDATE,'yyyymmddhh24miss')); 
INSERT INTO TAPGGRID(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) VALUES( sqapggrid_id.NEXTVAL, (SELECT page_id FROM TAPAGE WHERE file_name='mgrCdSysFieldList') ,'gridbox', (SELECT description FROM TAPAGE WHERE file_name = 'mgrCdSysFieldList') ,'5','mgrCdSysFieldList','DREAM','2.00',TO_CHAR(SYSDATE,'yyyymmddhh24miss'),TO_CHAR(SYSDATE,'yyyymmddhh24miss')); 

/** 2019-09-02 표준 Dream 반영 */

/** 308 + 은아 */
insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from tapage where file_name='mgrPlantMngList') ,'gridbox', (select description from tapage where file_name = 'mgrPlantMngList') ,'4','mgrPlantMngList','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 

/** 2019-09-09 평화오일씰 반영 */

/** 2019-09-10 표준 Dream 반영 */
/* 277 영주  */
INSERT INTO TAPGGRID(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) VALUES( sqapggrid_id.NEXTVAL, (SELECT page_id FROM TAPAGE WHERE file_name='mgrWorkCloseCheckList') ,'gridbox', (SELECT description FROM TAPAGE WHERE file_name = 'mgrWorkCloseCheckList') ,'10','mgrWorkCloseCheckList','DREAM','2.01',TO_CHAR(SYSDATE,'yyyymmddhh24miss'),TO_CHAR(SYSDATE,'yyyymmddhh24miss'));
INSERT INTO TAPGGRID(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) VALUES( sqapggrid_id.NEXTVAL, (SELECT page_id FROM TAPAGE WHERE file_name='mgrWorkCloseCheckPointList') ,'gridbox', (SELECT description FROM TAPAGE WHERE file_name = 'mgrWorkCloseCheckPointList') ,'5','mgrWorkCloseCheckPointList','DREAM','2.01',TO_CHAR(SYSDATE,'yyyymmddhh24miss'),TO_CHAR(SYSDATE,'yyyymmddhh24miss'));
INSERT INTO TAPGGRID(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) VALUES( sqapggrid_id.NEXTVAL, (SELECT page_id FROM TAPAGE WHERE file_name='mgrWorkCloseCheckDocLibList') ,'gridbox', (SELECT description FROM TAPAGE WHERE file_name = 'mgrWorkCloseCheckDocLibList') ,'5','mgrWorkCloseCheckDocLibList','DREAM','2.01',TO_CHAR(SYSDATE,'yyyymmddhh24miss'),TO_CHAR(SYSDATE,'yyyymmddhh24miss'));

/** 2019-09-24 표준 Dream 반영 */
/** 2019-10-01 안국약품 반영 */

/** 359 이근환 */
insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) 
values( sqapggrid_id.nextval, (select page_id from tapage where file_name='invtWoRsltList') ,'gridbox', (select description from tapage where file_name = 'invtWoRsltList') ,'5','invtWoRsltList','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 

/** 2019-10-08 표준 Dream 반영 */

/** 283 + euna0207 */
insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from tapage where file_name='persPrivFilterValueList') ,'gridbox', (select description from tapage where file_name = 'persPrivFilterValueList') ,'10','persPrivFilterValueList','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 

/** 2019-10-16 현대일렉트릭 반영 */

/** 445 + euna0207 */
insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from tapage where file_name='assetRptEqUnits') ,'gridbox', (select description from tapage where file_name = 'assetRptEqUnits') ,'5','assetRptEqUnits','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 

/** youngjoo38_360 + 김영주 */
insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from tapage where file_name='mgrUsageCalSetList') ,'gridbox', (select description from tapage where file_name = 'mgrUsageCalSetList') ,'5','mgrUsageCalSetList','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 
insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from tapage where file_name='mgrUsageCalSetDowSetList') ,'gridbox', (select description from tapage where file_name = 'mgrUsageCalSetDowSetList') ,'5','mgrUsageCalSetDowSetList','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 
insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from tapage where file_name='mgrUsageCalSetDayList') ,'gridbox', (select description from tapage where file_name = 'mgrUsageCalSetDayList') ,'10','mgrUsageCalSetDayList','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 

INSERT INTO TAPGGRID(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) VALUES( sqapggrid_id.NEXTVAL, (SELECT page_id FROM TAPAGE WHERE file_name='lovUsageWrkAcList') ,'gridbox', (SELECT description FROM TAPAGE WHERE file_name = 'lovUsageWrkAcList') ,'5','lovUsageWrkAcList','DREAM','2.01',TO_CHAR(SYSDATE,'yyyymmddhh24miss'),TO_CHAR(SYSDATE,'yyyymmddhh24miss'));

/** youngjoo38_333 + 김영주 */
 insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from tapage where file_name='consultPgmRptList') ,'gridbox', (select description from tapage where file_name = 'consultPgmRptList') ,'5','consultPgmRptList','DREAM','2.00',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 
 insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from tapage where file_name='consultPgmRptFileList') ,'gridbox', (select description from tapage where file_name = 'consultPgmRptFileList') ,'5','consultPgmRptFileList','DREAM','2.00',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 

/** 658 이근환 */
insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) 
values( sqapggrid_id.nextval, (select page_id from tapage where file_name='partListImgLinkUrlList') ,'gridbox', (select description from tapage where file_name = 'partListImgLinkUrlList') ,'10','partListImgLinkUrlList','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 

/** 2019-10-30 평화오일씰 반영 */
/** 2019-11-04 표준 Dream 반영 */
/** 2019-11-05 안국약품 반영 */

/**614 + 은아 */

/** *** 추가 *** **/
 insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from tapage where file_name='mgrLangMenuList') ,'gridbox', (select description from tapage where file_name = 'mgrLangMenuList') ,'10','mgrLangMenuList','DREAM','2.00',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 
 insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from tapage where file_name='mgrLangPageList') ,'gridbox', (select description from tapage where file_name = 'mgrLangPageList') ,'10','mgrLangPageList','DREAM','2.00',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 
 insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from tapage where file_name='mgrLangPgpageList') ,'gridbox', (select description from tapage where file_name = 'mgrLangPgpageList') ,'10','mgrLangPgpageList','DREAM','2.00',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 
 insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from tapage where file_name='mgrLangPgBtnList') ,'gridbox', (select description from tapage where file_name = 'mgrLangPgBtnList') ,'10','mgrLangPgBtnList','DREAM','2.00',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 
 insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from tapage where file_name='mgrLangPgFieldList') ,'gridbox', (select description from tapage where file_name = 'mgrLangPgFieldList') ,'10','mgrLangPgFieldList','DREAM','2.00',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 
 insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from tapage where file_name='mgrLangPgGridColList') ,'gridbox', (select description from tapage where file_name = 'mgrLangPgGridColList') ,'10','mgrLangPgGridColList','DREAM','2.00',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 
 insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from tapage where file_name='mgrLangPgLinkedList') ,'gridbox', (select description from tapage where file_name = 'mgrLangPgLinkedList') ,'10','mgrLangPgLinkedList','DREAM','2.00',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 
 insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from tapage where file_name='mgrLangSyscodeList') ,'gridbox', (select description from tapage where file_name = 'mgrLangSyscodeList') ,'10','mgrLangSyscodeList','DREAM','2.00',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 
 insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from tapage where file_name='mgrLangFailcodeList') ,'gridbox', (select description from tapage where file_name = 'mgrLangFailcodeList') ,'10','mgrLangFailcodeList','DREAM','2.00',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 

 
/** 2019-11-12 표준 Dream 반영 */
 /**692 + 김은아 */
 insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from tapage where file_name='partStkCurrentList') ,'gridbox', (select description from tapage where file_name = 'partStkCurrentList') ,'10','partStkCurrentList','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 

 /** 766 이근환 */
insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) 
values( sqapggrid_id.nextval, (select page_id from tapage where file_name='assetListPartOpQtyList') ,'gridbox', (select description from tapage where file_name = 'assetListPartOpQtyList') ,'5','assetListPartOpQtyList','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 

/** 792 이근환 */
insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) 
values( sqapggrid_id.nextval, (select page_id from tapage where file_name='assetRptEqParts') ,'gridbox', (select description from tapage where file_name = 'assetRptEqParts') ,'5','assetRptEqParts','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 

/** youngjoo38_794 + 김영주 */
insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from tapage where file_name='assetRptNYearPOList') ,'gridbox', (select description from tapage where file_name = 'assetRptNYearPOList') ,'5','assetRptNYearPOList','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 
insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from tapage where file_name='assetRptNYearPartsList') ,'gridbox', (select description from tapage where file_name = 'assetRptNYearPartsList') ,'5','assetRptNYearPartsList','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 

/** 2019-11-22 동국제약 반영 */
/** 2019-11-25 연우 반영 */
/** 2019-11-26 평화오일씰 반영 */
/** 2019-11-27 연우 반영 */
/** 2019-12-04 안국약품 반영 */

/** 774 + 김은아 */
 insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from tapage where file_name='mgrUsrGrpPageAuthFieldList') ,'gridbox', (select description from tapage where file_name = 'mgrUsrGrpPageAuthFieldList') ,'10','mgrUsrGrpPageAuthFieldList','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 
 insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from tapage where file_name='mgrUsrGrpPageAuthGridColList') ,'gridbox', (select description from tapage where file_name = 'mgrUsrGrpPageAuthGridColList') ,'10','mgrUsrGrpPageAuthGridColList','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 

 /** youngjoo38_698 + 김영주 */

 insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from tapage where file_name='mgrContractList') ,'gridbox', (select description from tapage where file_name = 'mgrContractList') ,'10','mgrContractList','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 
 insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from tapage where file_name='mgrContractItemList') ,'gridbox', (select description from tapage where file_name = 'mgrContractItemList') ,'10','mgrContractItemList','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 
 insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from tapage where file_name='lovMgrContractList') ,'gridbox', (select description from tapage where file_name = 'lovMgrContractList') ,'10','lovMgrContractList','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 

 insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from tapage where file_name='workServicePriceList') ,'gridbox', (select description from tapage where file_name = 'workServicePriceList') ,'10','workServicePriceList','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 
 insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from tapage where file_name='lovWorkServiceList') ,'gridbox', (select description from tapage where file_name = 'lovWorkServiceList') ,'10','lovWorkServiceList','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 

 /** 758 이근환 */
insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) 
values( sqapggrid_id.nextval, (select page_id from tapage where file_name='lovMgrRptCpList') ,'gridbox', (select description from tapage where file_name = 'lovMgrRptCpList') ,'10','lovMgrRptCpList','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 


/** 2019-12-16 표준Dream 반영 */

/** 447 + 이지수 */
 insert into TAPGGRID(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from TAPAGE where file_name='orgRptCraftWorkTimeTop') ,'gridbox', (select description from TAPAGE where file_name = 'orgRptCraftWorkTimeTop') ,'8','orgRptCraftWorkTimeTop','DREAM','2.01',TO_CHAR(SYSDATE,'yyyymmddhh24miss'),TO_CHAR(SYSDATE,'yyyymmddhh24miss')); 

 insert into TAPGGRID(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from TAPAGE where file_name='orgRptCraftWorkTimeMonthly') ,'gridbox', (select description from TAPAGE where file_name = 'orgRptCraftWorkTimeMonthly') ,'8','orgRptCraftWorkTimeMonthly','DREAM','2.01',TO_CHAR(SYSDATE,'yyyymmddhh24miss'),TO_CHAR(SYSDATE,'yyyymmddhh24miss')); 

 insert into TAPGGRID(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from TAPAGE where file_name='orgRptCraftWorkTimeDaily') ,'gridbox', (select description from TAPAGE where file_name = 'orgRptCraftWorkTimeDaily') ,'8','orgRptCraftWorkTimeDaily','DREAM','2.01',TO_CHAR(SYSDATE,'yyyymmddhh24miss'),TO_CHAR(SYSDATE,'yyyymmddhh24miss')); 

/** 2019-12-31 표준Dream 반영 */
/** 2020-01-07 연우 반영 */
/** 2020-01-09 평화오일씰 phos 반영 */
/** 2020-01-13 현대일렉트릭 hdele 반영 */
/** 2020-01-13 평화오일씰 phos 반영 */
 
 /*796 김남현*/
insert into tapggrid(pggrid_id,page_id,grid_obj_id,description,height,file_name,site_type,version_info,cre_date,upd_date) values( sqapggrid_id.nextval, (select page_id from tapage where file_name='woPlanServiceList') ,'gridbox', (select description from tapage where file_name = 'woPlanServiceList') ,'8','woPlanServiceList','DREAM','2.01',to_char(sysdate,'yyyymmddhh24miss'),to_char(sysdate,'yyyymmddhh24miss')); 

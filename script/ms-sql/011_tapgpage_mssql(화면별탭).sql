update TAPGPAGE a set a.file_name = (select aa.file_name from tapage aa where aa.page_id = a.page_id);
update TAPGPAGE a set a.c_file_name = (select aa.file_name from tapage aa where aa.page_id = a.c_page_id);

select 
' INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO,file_name, c_file_name) 
VALUES(NEXT VALUE FOR SQAPGPAGE_ID, (select aa.page_id from tapage aa where aa.file_name = '''||file_name||''') , (select aa.page_id from tapage aa where aa.file_name = '''||c_file_name||''') , '''||ORD_NO||''', '''||IS_USE||''', '''||KEY_TYPE||''', '''||KEY_NO||''', '''||file_name||''', '''||c_file_name||''' ); ' 
from TAPGPAGE
;


select 
'insert into TAPGFIELD(pgfield_id, page_id, field_id, description, hidden_yn, ord_no, display_yn, key_type, key_no, check_yn, field_option, readonly_yn, file_name) 
VALUES(NEXT value FOR sqapgfield_id, (select aa.page_id from tapage aa where aa.file_name = '''||file_name||'''), '''||field_id||''', '''||description||''', '''||hidden_yn||''', '''||ord_no||''', '''||display_yn||''', '''||key_type||''', '''||key_no||''', '''||check_yn||''', '''||field_option||''' , '''||readonly_yn||''', '''||file_name||'''  ); ' 
from TAPGFIELD
;




---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-11   박철완 최초 생성*/ 
---------------------------------------------------------------------------------------------------------------------------------------------------

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maBatchMngList') ,(select aa.page_id from tapage aa where aa.file_name = 'maBatchMngDetail') ,'10','Y','LABEL','programName')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maBdPointList') ,(select aa.page_id from tapage aa where aa.file_name = 'maBdPointDetail') ,'10','Y','LABEL','woNo')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maBtnMngList') ,(select aa.page_id from tapage aa where aa.file_name = 'maBtnMngDetail') ,'10','Y','TAB','maBtnMngDetail')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maCdSysCodeList') ,(select aa.page_id from tapage aa where aa.file_name = 'maCdSysCodeDetail') ,'10','Y','TAB','maCdSysCodeDetail')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maCdSysDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maCdSysCodeList') ,'10','Y','TAB','maCdSysCodeList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maCdSysList') ,(select aa.page_id from tapage aa where aa.file_name = 'maCdSysDetail') ,'10','Y','LABEL','cdType')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maCdUsrCdList') ,(select aa.page_id from tapage aa where aa.file_name = 'maCdUsrCdDetail') ,'10','Y','LABEL','cdUsrdCode')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maCdUsrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maCdUsrCdList') ,'10','Y','TAB','maCdUsrCdList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maCdUsrList') ,(select aa.page_id from tapage aa where aa.file_name = 'maCdUsrDetail') ,'10','Y','LABEL','dirType')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maConfigList') ,(select aa.page_id from tapage aa where aa.file_name = 'maConfigDetail') ,'10','Y','TAB','maConfigDetail')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maDeptList') ,(select aa.page_id from tapage aa where aa.file_name = 'maDeptDetail') ,'10','Y','LABEL','deptNo')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maDocCntrCdDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maDocLibList') ,'10','Y','TAB','maDocLibList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maDocCntrCdList') ,(select aa.page_id from tapage aa where aa.file_name = 'maDocCntrCdDetail') ,'10','Y','LABEL','docCntrNo')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maDocCntrEcDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maDocLibList') ,'20','Y','TAB','maDocLibList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maDocCntrEcList') ,(select aa.page_id from tapage aa where aa.file_name = 'maDocCntrEcDetail') ,'10','Y','LABEL','docCntrNo')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maDocImgList') ,(select aa.page_id from tapage aa where aa.file_name = 'maDocImgDetail') ,'10','Y','TAB','maDocImgDetail')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maDocLibList') ,(select aa.page_id from tapage aa where aa.file_name = 'maDocLibDetail') ,'10','Y','TAB','maDocLibDetail')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maEmpList') ,(select aa.page_id from tapage aa where aa.file_name = 'maEmpDetail') ,'10','Y','LABEL','empNo')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maEqCatalogDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maEqCtgAsmbList') ,'10','Y','TAB','maEqCtgAsmbList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maEqCatalogDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maEqCtgPartList') ,'20','Y','TAB','maEqCtgPartList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maEqCatalogList') ,(select aa.page_id from tapage aa where aa.file_name = 'maEqCatalogDetail') ,'10','Y','LABEL','ctgCode')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maEqCtgAsmbList') ,(select aa.page_id from tapage aa where aa.file_name = 'maEqCtgAsmbDetail') ,'10','Y','LABEL','ctgAsmbName')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maEqCtgPartList') ,(select aa.page_id from tapage aa where aa.file_name = 'maEqCtgPartDetail') ,'10','Y','LABEL','partCode')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maEqLocList') ,(select aa.page_id from tapage aa where aa.file_name = 'maEqLocDetail') ,'10','Y','LABEL','eqLocNo')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maEqMngList') ,(select aa.page_id from tapage aa where aa.file_name = 'maEqMngDetail') ,'10','Y','MENU','maEqMngList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maEqMstrAsmbList') ,(select aa.page_id from tapage aa where aa.file_name = 'maEqMstrAsmbDetail') ,'10','Y','TAB','maEqMstrAsmbList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maEqMstrAssetList') ,(select aa.page_id from tapage aa where aa.file_name = 'maEqMstrAssetDetail') ,'10','Y','LABEL','assetNo')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maEqMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maDocLibList') ,'70','Y','TAB','maDocLibList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maEqMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maEqMstrAsmbList') ,'30','Y','TAB','maEqMstrAsmbList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maEqMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maEqMstrAssetList') ,'40','Y','TAB','maEqMstrAssetList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maEqMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maEqMstrPartList') ,'20','Y','TAB','maEqMstrPartList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maEqMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maEqMstrSpecList') ,'10','Y','TAB','maEqMstrSpecList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maEqMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maEqMstrVendorList') ,'50','Y','TAB','maEqMstrVendorList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maEqMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maEqNotesAppList') ,'60','Y','TAB','maEqNotesAppList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maEqMstrHistList') ,(select aa.page_id from tapage aa where aa.file_name = 'maEqMstrHistDetail') ,'10','Y','LABEL','equipment')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maEqMstrList') ,(select aa.page_id from tapage aa where aa.file_name = 'maEqMstrDetail') ,'10','Y','LABEL','equipment')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maEqMstrPartList') ,(select aa.page_id from tapage aa where aa.file_name = 'maEqMstrPartDetail') ,'10','Y','TAB','maEqMstrPartList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maEqMstrSpecList') ,(select aa.page_id from tapage aa where aa.file_name = 'maEqMstrSpecDetail') ,'10','Y','TAB','maEqMstrSpecList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maEqMstrVendorList') ,(select aa.page_id from tapage aa where aa.file_name = 'maEqMstrVendorDetail') ,'10','Y','TAB','maEqMstrVendorList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maEqNotesAppList') ,(select aa.page_id from tapage aa where aa.file_name = 'maEqNotesAppDetail') ,'10','Y','LABEL','eqAppId')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maFailureList') ,(select aa.page_id from tapage aa where aa.file_name = 'maFailureDetail') ,'10','Y','LABEL','failureNo')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maGrdMngColList') ,(select aa.page_id from tapage aa where aa.file_name = 'maGrdMngColDetail') ,'10','Y','LABEL','columnId')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maGrdMngDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maGrdMngColList') ,'10','Y','TAB','maGrdMngColList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maGrdMngList') ,(select aa.page_id from tapage aa where aa.file_name = 'maGrdMngDetail') ,'10','Y','TAB','maGrdMngDetail')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maGrdUsrColList') ,(select aa.page_id from tapage aa where aa.file_name = 'maGrdUsrColDetail') ,'10','Y','LABEL','maGrdMngColList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maGrdUsrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maGrdUsrColList') ,'10','Y','LABEL','maGrdUsrColList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maLangMngList') ,(select aa.page_id from tapage aa where aa.file_name = 'maLangMngDetail') ,'10','Y','TAB','maLangMngDetail')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maLinkMenuList') ,(select aa.page_id from tapage aa where aa.file_name = 'maLinkMenuDetail') ,'10','Y','LABEL','linkedMenuDesc')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maMailDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maMailUsrList') ,'10','Y','LABEL','mailManager')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maMailList') ,(select aa.page_id from tapage aa where aa.file_name = 'maMailDetail') ,'10','Y','LABEL','title')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maMailUsrList') ,(select aa.page_id from tapage aa where aa.file_name = 'maMailUsrDetail') ,'10','Y','LABEL','empNo')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maMenuMngList') ,(select aa.page_id from tapage aa where aa.file_name = 'maMenuMngDetail') ,'10','Y','TAB','maMenuMngDetail')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maMyInfo') ,(select aa.page_id from tapage aa where aa.file_name = 'maLinkMenuList') ,'20','Y','LABEL','linkedMenuDesc')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maMyInfo') ,(select aa.page_id from tapage aa where aa.file_name = 'maMyMenuList') ,'10','Y','LABEL','maMyUsrList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maMyMenuList') ,(select aa.page_id from tapage aa where aa.file_name = 'maMyMenuDetail') ,'10','Y','LABEL','maMyUsrList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPgMngBtnList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPgMngBtnDetail') ,'10','Y','TAB','maPgMngBtnDetail')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPgMngDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maPgMngBtnList') ,'10','Y','TAB','maPgMngBtnList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPgMngDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maPgMngPageList') ,'20','Y','TAB','maPgMngPageList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPgMngList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPgMngDetail') ,'10','Y','TAB','maPgMngDetail')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPgMngPageList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPgMngPageDetail') ,'10','Y','LABEL','page')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPmMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maPmMstrPartList') ,'10','Y','TAB','maPmMstrPartList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPmMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maPmMstrPointList') ,'20','Y','TAB','maPmMstrPointList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPmMstrList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPmMstrDetail') ,'10','Y','LABEL','pmNo')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPmMstrPartList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPmMstrPartDetail') ,'10','Y','LABEL','parts')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPmMstrPointList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPmMstrPointDetail') ,'10','Y','LABEL','maPmMstrPointList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPtBudgetDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maPtDeptBgList') ,'10','Y','TAB','maPtDeptBdList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPtBudgetList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPtBudgetDetail') ,'10','Y','LABEL','accntNo')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPtDeptBgList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPtDeptBgDetail') ,'10','Y','LABEL','dept')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPtMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maDocLibList') ,'40','Y','TAB','maDocLibList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPtMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maPtMstrEqPartList') ,'20','Y','TAB','maPtMstrEqPartList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPtMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maPtMstrUsedDeptList') ,'30','Y','TAB','maPtMstrUsedDept')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPtMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maPtMstrVendorList') ,'10','Y','TAB','maPtMstrVendorList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPtMstrEqPartList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPtMstrEqPartDetail') ,'10','Y','TAB','maPtMstrEqPartList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPtMstrList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPtMstrDetail') ,'10','Y','LABEL','partNo')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPtMstrUsedDeptList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPtMstrUsedDeptDetail') ,'10','Y','TAB','maPtMstrUsedDept')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPtMstrVendorList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPtMstrVendorDetail') ,'10','Y','LABEL','vendorNo')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPtRecList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPtRecDetail') ,'10','Y','LABEL','ptNo')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPtRepAppList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPtRepAppDetail') ,'10','Y','TAB','maPtRepAppList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPtRepDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maDocLibList') ,'20','Y','TAB','maDocLibList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPtRepDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maPtRepAppList') ,'10','Y','TAB','maPtRepAppList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPtRepList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPtRepDetail') ,'10','Y','LABEL','ptNo')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPtStckList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPtStckDetail') ,'10','Y','LABEL','partNo')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPtWhList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPtWhDetail') ,'10','Y','LABEL','wcode')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maSesMngList') ,(select aa.page_id from tapage aa where aa.file_name = 'maSesMngDetail') ,'10','Y','TAB','maSesMngDetail')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maUserList') ,(select aa.page_id from tapage aa where aa.file_name = 'maUserDetail') ,'10','Y','LABEL','userId')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maUsrGrpDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maUsrGrpAuthList') ,'10','Y','LABEL','auth')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maUsrGrpList') ,(select aa.page_id from tapage aa where aa.file_name = 'maUsrGrpDetail') ,'10','Y','LABEL','usrGrpNo')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maVendorList') ,(select aa.page_id from tapage aa where aa.file_name = 'maVendorDetail') ,'10','Y','LABEL','vendorNo')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoLinepfDayList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoLinepfDayDetail') ,'','Y','LABEL','bmStime')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoLinepfDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoLinepfDayList') ,'10','Y','LABEL','dailyLinePf')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoLinepfList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoLinepfDetail') ,'10','Y','LABEL','dailyLinePf')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoMonthSchedList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoMonthSchedDetail') ,'10','Y','LABEL','pmNo')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoMonthWoList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultMstrDetail') ,'10','Y','LABEL','woNo')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultCraftList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultCraftDetail') ,'10','Y','LABEL','woCraft')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maDocLibList') ,'40','N','TAB','maDocLibList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultCraftList') ,'20','N','TAB','maWoResultCraftList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultFailDetail') ,'10','N','TAB','maWoResultFailDetail')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPartList') ,'30','N','TAB','maWoResultPartList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPointList') ,'40','N','TAB','maWoResultPointList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultMstrList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultMstrDetail') ,'10','Y','LABEL','woNo')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPartList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPartDetail') ,'10','Y','LABEL','parts')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPointList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPointDetail') ,'10','Y','LABEL','checkPoint')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoYearSchedList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoYearSchedDetail') ,'10','Y','LABEL','pmNo')
go 
INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES
   (NEXT VALUE FOR sqapgpage_id,  (SELECT page_id FROM TAPAGE WHERE file_name='maStdPointList'),  (SELECT page_id FROM TAPAGE WHERE file_name='maStdPointDetail'), '10', 'Y', 'LABEL', 'stdPoint2')
go
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-12   노정현 추가작업 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES
   (NEXT VALUE FOR sqapgpage_id,  (SELECT page_id FROM TAPAGE WHERE file_name='maNstGrpList'),  (SELECT page_id FROM TAPAGE WHERE file_name='maNstGrpDetail'), '10', 'Y', 'LABEL', 'lnDown')
go
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-12   노정현 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-16   김정우 추가작업 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES
   (NEXT VALUE FOR sqapgpage_id,  (SELECT page_id FROM TAPAGE WHERE file_name='maStdPointHdrList'),  (SELECT page_id FROM TAPAGE WHERE file_name='maStdPointHdrDetail'), '10', 'Y', 'LABEL', 'stWrkNo')
go
INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES
   (NEXT VALUE FOR sqapgpage_id,  (SELECT page_id FROM TAPAGE WHERE file_name='maStdPointHdrDetail'),  (SELECT page_id FROM TAPAGE WHERE file_name='maStdPointList'), '10', 'Y', 'LABEL', 'stdPoint2')
go
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-16   김정우 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-17   노정현 추가작업 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
   INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES
  (NEXT VALUE FOR sqapgpage_id, (SELECT page_id  FROM TAPAGE WHERE file_name ='maEqDocLibList'), (SELECT page_id  FROM TAPAGE WHERE file_name ='maEqDocLibDetail'), '10', 'Y', 
    'LABEL', 'docNo')
go
UPDATE TAPGPAGE SET    
    c_page_id     =  (SELECT page_id  FROM TAPAGE WHERE file_name ='maEqDocLibList')
WHERE page_id  = (SELECT page_id  FROM TAPAGE WHERE file_name ='maEqMstrDetail')    
AND c_page_id =  (SELECT page_id  FROM TAPAGE WHERE file_name ='maDocLibList')
go
INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES
  (NEXT VALUE FOR sqapgpage_id, (SELECT page_id  FROM TAPAGE WHERE file_name ='maWoDocLibList'), (SELECT page_id  FROM TAPAGE WHERE file_name ='maWoDocLibDetail'), '10', 'Y', 
    'LABEL', 'docNo')
go
UPDATE TAPGPAGE SET    
    c_page_id     =  (SELECT page_id  FROM TAPAGE WHERE file_name ='maWoDocLibList')
WHERE page_id  = (SELECT page_id  FROM TAPAGE WHERE file_name ='maWoMstrDetail')    
AND c_page_id =  (SELECT page_id  FROM TAPAGE WHERE file_name ='maDocLibList')
go
INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES
  (NEXT VALUE FOR sqapgpage_id, (SELECT page_id  FROM TAPAGE WHERE file_name ='maPtDocLibList'), (SELECT page_id  FROM TAPAGE WHERE file_name ='maPtDocLibDetail'), '10', 'Y', 
    'LABEL', 'docNo')
go
UPDATE TAPGPAGE SET    
    c_page_id     =  (SELECT page_id  FROM TAPAGE WHERE file_name ='maPtDocLibList')
WHERE page_id  = (SELECT page_id  FROM TAPAGE WHERE file_name ='maPtMstrDetail')    
AND c_page_id =  (SELECT page_id  FROM TAPAGE WHERE file_name ='maDocLibList')
go
INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES
  (NEXT VALUE FOR sqapgpage_id, (SELECT page_id  FROM TAPAGE WHERE file_name ='maDocImgPopList'), (SELECT page_id  FROM TAPAGE WHERE file_name ='maDocImgPopDetail'), '10', 'Y', 
    'LABEL', 'docNo')
go
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-17   노정현 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-24   김정우 추가작업 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES
   (NEXT VALUE FOR sqapgpage_id,  (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultStPointList'),  (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultStPointDetail'), '10', 'Y', 'LABEL', 'checkPoint')
go
INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES
   (NEXT VALUE FOR sqapgpage_id,  (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultMstrDetail'),  (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultStPointList'), '35', 'Y', 'TAB', 'maWoResultStPointList')
go

update TAPGPAGE set ord_no = 50 where page_id = (SELECT page_id FROM TAPAGE where file_name='maWoResultMstrDetail')
AND c_page_id = (select page_id from TAPAGE where file_name='maDocLibList')
go
update TAPGPAGE set ord_no = 45 where page_id = (SELECT page_id FROM TAPAGE where file_name='maWoResultMstrDetail')
AND c_page_id = (select page_id from TAPAGE where file_name='maWoResultStPointList')
go
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-24   김정우 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-26   김정우 추가작업 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES
   (NEXT VALUE FOR sqapgpage_id,  (SELECT page_id FROM TAPAGE WHERE file_name='maQnaList'),  (SELECT page_id FROM TAPAGE WHERE file_name='maQnaDetail'), '10', 'Y', 'LABEL', 'questionPoint')
go
INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES
   (NEXT VALUE FOR sqapgpage_id,  (SELECT page_id FROM TAPAGE WHERE file_name='maQnaAnsList'),  (SELECT page_id FROM TAPAGE WHERE file_name='maQnaAnsDetail'), '10', 'Y', 'LABEL', 'answerPoint')
go
 INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES
   (NEXT VALUE FOR sqapgpage_id,  (SELECT page_id FROM TAPAGE WHERE file_name='maQnaDetail'),  (SELECT page_id FROM TAPAGE WHERE file_name='maQnaAnsList'), '20', 'Y', 'LABEL', 'answerPoint')
go
 INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES
   (NEXT VALUE FOR sqapgpage_id,  (SELECT page_id FROM TAPAGE WHERE file_name='maQnaDetail'),  (SELECT page_id FROM TAPAGE WHERE file_name='maDocLibList'), '10', 'Y', 'TAB', 'maDocLibList')
go
 INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES
   (NEXT VALUE FOR sqapgpage_id,  (SELECT page_id FROM TAPAGE WHERE file_name='maQnaAnsDetail'),  (SELECT page_id FROM TAPAGE WHERE file_name='maDocLibList'), '10', 'Y', 'TAB', 'maDocLibList')
go

   ---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-26   김정우 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-30   김정우 추가작업 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES
   (NEXT VALUE FOR sqapgpage_id,  (SELECT page_id FROM TAPAGE WHERE file_name='maPtBuyReqHdrList'),  (SELECT page_id FROM TAPAGE WHERE file_name='maPtBuyReqHdrDetail'), '10', 'Y', 'LABEL', 'reqNo')
go
   ---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-30   김정우 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-31   이규선 추가작업 시작 */ 
--------------------------------------------------------------------------------------------------------------------------------------------
 INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES
   (NEXT VALUE FOR sqapgpage_id,  (SELECT page_id FROM TAPAGE WHERE file_name='maPtPurReqList'),  (SELECT page_id FROM TAPAGE WHERE file_name='maPtPurReqDetail'), '10', 'Y', 'LABEL', 'reqNo')
go
   
   ---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-31   이규선 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------=======
   ---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-06-01  구매요청 item 탭 페이지 추가 김정우 추가작업 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES
   (NEXT VALUE FOR sqapgpage_id,  (SELECT page_id FROM TAPAGE WHERE file_name='maPtBuyReqHdrDetail'),  (SELECT page_id FROM TAPAGE WHERE file_name='maPtBuyReqList'), '10', 'Y', 'LABEL', 'ptItem')
go
INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)maPtBuyReqList
 VALUES
   (NEXT VALUE FOR sqapgpage_id,  (SELECT page_id FROM TAPAGE WHERE file_name='maPtBuyReqList'),  (SELECT page_id FROM TAPAGE WHERE file_name='maPtBuyReqDetail'), '10', 'Y', 'LABEL', 'ptItem')
go

   ---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-06-01   김정우 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-06-07  김정우 월간작업일정 작업결과 탭페이지  수정 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
   UPDATE  TAPGPAGE SET is_use='Y' where is_use='N' AND page_id = (select page_id FROM TAPAGE where file_name='maWoResultMstrDetail')
go
   UPDATE TAPGPAGE SET c_page_id=(select page_id FROM TAPAGE where file_name='maWoResultMonthMstrDetail') where page_id =(select page_id FROM TAPAGE where file_name='maWoMonthWoList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultMonthCraftList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultMonthCraftDetail') ,'10','Y','LABEL','woCraft')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultMonthPartList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultMonthPartDetail') ,'10','Y','LABEL','parts')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultMonthPointList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultMonthPointDetail') ,'10','Y','LABEL','checkPoint')
go  
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES (NEXT VALUE FOR sqapgpage_id,  (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultMonthStPointList'),  (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultMonthStPointDetail'), '10', 'Y', 'LABEL', 'checkPoint')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES (NEXT VALUE FOR sqapgpage_id, (SELECT page_id  FROM TAPAGE WHERE file_name ='maWoMonthDocLibList'), (SELECT page_id  FROM TAPAGE WHERE file_name ='maWoMonthDocLibDetail'), '10', 'Y', 'LABEL', 'docNo')
go
   
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultMonthMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoMonthDocLibList') ,'60','Y','TAB','maDocLibList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultMonthMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultMonthCraftList') ,'20','Y','TAB','maWoResultCraftList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultMonthMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultMonthFailDetail') ,'10','Y','TAB','maWoResultFailDetail')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultMonthMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultMonthPartList') ,'30','Y','TAB','maWoResultPartList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultMonthMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultMonthPointList') ,'40','Y','TAB','maWoResultPointList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES (NEXT VALUE FOR sqapgpage_id,  (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultMonthMstrDetail'),  (SELECT page_id FROM TAPAGE WHERE file_name='maWoResultMonthStPointList'), '50', 'Y', 'TAB', 'maWoResultStPointList')
go
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-06-07  김정우 월간작업일정 작업결과  탭페이지  수정 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-06-13  김정우 예방작업기준서 상세(점검) 점검페이지 탭페이지 추가  수정 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPmInsMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maPmMstrPointList') ,'10','Y','TAB','maPmMstrPointList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPmMstrList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPmInsMstrDetail') ,'20','Y','LABEL','pmNo')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPmMstrList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPmRprMstrDetail') ,'30','Y','LABEL','pmNo')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPmMstrList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPmRplMstrDetail') ,'40','Y','LABEL','pmNo')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPmRplMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maPmMstrPartList') ,'10','Y','TAB','maPmMstrPartList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPmMstrList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPmClnMstrDetail') ,'50','Y','LABEL','pmNo')
go  
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPmClnMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maPmEqClnList') ,'10','Y','TAB','maPmEqClnList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPmEqClnList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPmEqClnDetail') ,'10','Y','TAB','maPmEqClnList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPmMstrList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPmOilMstrDetail') ,'60','Y','LABEL','pmNo')
go  
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPmOilMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maPmEqOilList') ,'20','Y','TAB','maPmEqOilList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPmOilMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maPmOilPartList') ,'10','Y','TAB','maPmMstrPartList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPmEqOilList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPmEqOilDetail') ,'10','Y','TAB','maPmEqOilList')
go 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPmOilPartList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPmOilPartDetail') ,'10','Y','TAB','maPmMstrPartList')
go 
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-06-13   김정우 예방작업 관련 추가작업 끝 */ 
-------------------------------------------------------------------------------------------------------------------------------------------

/** 2016_06-14 노정현 일일작업일지확인 추가 */
 INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES
   (NEXT VALUE FOR sqapgpage_id, (SELECT page_id FROM TAPAGE WHERE file_name='maWoDailyList'), (SELECT page_id FROM TAPAGE WHERE file_name='maWoDailyDetail'), '10', 'Y', 
    'LABEL', 'date')
go
/** 2016-06-14 김정우 작업결과 상세페이지, 작업자, 점검항목 탭추가,작업설비 시작 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultMstrList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmInsMstrDetail') ,'10','Y','LABEL','woNo')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmInsMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmInsCraftList') ,'10','Y','TAB','maWoResultCraftList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmInsCraftList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmInsCraftDetail') ,'10','Y','LABEL','woCraft')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmInsMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmInsPointList') ,'20','Y','TAB','maPmMstrPointList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmInsPointList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmInsPointDetail') ,'10','Y','LABEL','maPmMstrPointList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultMstrList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmRprMstrDetail') ,'30','Y','LABEL','woNo')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmRprMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmRprCraftList') ,'10','Y','TAB','maWoResultCraftList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmRprCraftList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmRprCraftDetail') ,'10','Y','LABEL','woCraft')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultMstrList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmRplMstrDetail') ,'40','Y','LABEL','woNo')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmRplMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmRplCraftList') ,'10','Y','TAB','maWoResultCraftList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmRplCraftList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmRplCraftDetail') ,'10','Y','LABEL','woCraft')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmRplMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmRplPartList') ,'20','Y','TAB','maWoResultPartList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmRplPartList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmRplPartDetail') ,'10','Y','LABEL','parts')
go  
/** 2016-06-14 김정우 작업결과 상세페이지, 작업자, 점검항목 탭추가 끝 */
/** 2016-06-16 김정우 작업결과 상세페이지, 작업자, 점검항목 탭추가 끝 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultMstrList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmClnMstrDetail') ,'50','Y','LABEL','woNo')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmClnMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmClnCraftList') ,'10','Y','TAB','maWoResultCraftList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmClnCraftList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmClnCraftDetail') ,'10','Y','LABEL','woCraft')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmClnMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultClnList') ,'20','Y','TAB','maWoResultClnList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultClnList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultClnDetail') ,'10','Y','TAB','maWoResultClnList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultMstrList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmOilMstrDetail') ,'60','Y','LABEL','woNo')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmOilMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmOilCraftList') ,'10','Y','TAB','maWoResultCraftList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmOilCraftList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmOilCraftDetail') ,'10','Y','LABEL','woCraft')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmOilMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultOilList') ,'20','Y','TAB','maWoResultClnList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultOilList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultOilDetail') ,'10','Y','TAB','maWoResultOilList')
go
/** 2016-06-16 김정우 작업결과 상세페이지, 작업자, 점검항목 탭추가 끝 */
/** 2016-06-20 김정우 작업결과 상세페이지, 작업자, 점검항목 탭추가 시작*/
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultMstrList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmRprMstrDetail') ,'70','Y','LABEL','woNo')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmRprMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmRprCraftList') ,'10','Y','TAB','maWoResultCraftList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmRprCraftList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmRprCraftDetail') ,'10','Y','LABEL','woCraft')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultMstrList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmRplMstrDetail') ,'80','Y','LABEL','woNo')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmRplMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmRplCraftList') ,'10','Y','TAB','maWoResultCraftList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmRplCraftList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmRplCraftDetail') ,'10','Y','LABEL','woCraft')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmRplMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmRplPartList') ,'20','Y','TAB','maWoResultPartList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmRplPartList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmRplPartDetail') ,'10','Y','LABEL','parts')
go  
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultMstrList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmOilMstrDetail') ,'90','Y','LABEL','woNo')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmOilMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmOilCraftList') ,'10','Y','TAB','maWoResultCraftList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmOilCraftList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmOilCraftDetail') ,'10','Y','LABEL','woCraft')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmOilMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmOilPartList') ,'20','Y','TAB','maWoResultPartList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmOilPartList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmOilPartDetail') ,'10','Y','LABEL','parts')
go  
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultMstrList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultCmRprMstrDetail') ,'100','Y','LABEL','woNo')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultCmRprMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultCmRprCraftList') ,'10','Y','TAB','maWoResultCraftList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultCmRprCraftList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultCmRprCraftDetail') ,'10','Y','LABEL','woCraft')
go

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultMstrList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultCmRplMstrDetail') ,'110','Y','LABEL','woNo')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultCmRplMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultCmRplCraftList') ,'10','Y','TAB','maWoResultCraftList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultCmRplCraftList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultCmRplCraftDetail') ,'10','Y','LABEL','woCraft')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultCmRplMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultCmRplPartList') ,'20','Y','TAB','maWoResultPartList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultCmRplPartList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultCmRplPartDetail') ,'10','Y','LABEL','parts')
go  
/** 2016-06-20 김정우 작업결과 상세페이지, 작업자, 점검항목 탭추가 끝 */

/** 2016-06-21 김정우 월간작업일정 상세페이지(상세페이지 내 권한 설정을 현재 작업결과와 같음) 시작 */
delete FROM TAPGPAGE where page_id= (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoMonthWoList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoMonthWoList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmInsMstrDetail') ,'10','Y','LABEL','woNo')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoMonthWoList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmRprMstrDetail') ,'20','Y','LABEL','woNo')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoMonthWoList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmRplMstrDetail') ,'30','Y','LABEL','woNo')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoMonthWoList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmClnMstrDetail') ,'40','Y','LABEL','woNo')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoMonthWoList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmOilMstrDetail') ,'50','Y','LABEL','woNo')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoMonthWoList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmRprMstrDetail') ,'60','Y','LABEL','woNo')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoMonthWoList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmRplMstrDetail') ,'70','Y','LABEL','woNo')
go
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoMonthWoList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmOilMstrDetail') ,'80','Y','LABEL','woNo')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoMonthWoList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultCmRprMstrDetail') ,'90','Y','LABEL','woNo')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoMonthWoList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultCmRplMstrDetail') ,'100','Y','LABEL','woNo')
go
 /** 2016-06-21 김정우 월간작업일정 상세페이지(상세페이지 내 권한 설정을 현재 작업결과와 같음) 끝 */

/** 2016-06-22 노정현 결재 페이지 추가*/

INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES
   (NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appPrcList'),  (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appPrcDetail'), '10', 'Y', 
    'LABEL', 'name')
go
INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES
   (NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReqDetail'),  (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appPrcList'), '10', 'Y', 
    'LABEL', 'apprByName')
go
INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES
   (NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppLineDetail'),  (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppLineUsrList'), '10', 'Y', 
    'LABEL', '')
go
INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES
   (NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppLineList'),  (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppLineDetail'), '10', 'Y', 
    'LABEL', 'apprLineTitle')
go
INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES
   (NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppLineUsrList'),  (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppLineUsrDetail'), '10', 'Y', 
    'LABEL', 'name')
go
INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES
   (NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList'),  (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcDetail'), '10', 'Y', 
    'LABEL', 'appByHist')
go
    
INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES
   (NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppLinePopupList'),  (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppLinePopupDetail'), '10', 'Y', 
    'LABEL', 'apprLineTitle')
go
INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES
   (NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppLineUsrPopupList'),  (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppLineUsrPopupDetail'), '10', 'Y', 
    'LABEL', 'name')
go
INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES
   (NEXT VALUE FOR sqapgpage_id , (SELECT page_id FROM TAPAGE WHERE file_name='maWoDailyDetail'),(SELECT page_id FROM TAPAGE WHERE file_name='maAppPrcList') , '10', 'Y', 
    'LABEL', 'appByHist')
go
  INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES
   (NEXT VALUE FOR sqapgpage_id , (SELECT page_id FROM TAPAGE WHERE file_name='appReadyList'),(SELECT page_id FROM TAPAGE WHERE file_name='maWoDailyDetail') , '10', 'Y', 
    'LABEL', 'date')
go
/** 2016-06-28 김정우 무상입고 탭페이지 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPtFcRecList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPtFcRecDetail') ,'10','Y','LABEL','ptNo')
go 

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPgMngDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maPgMngFieldList') ,'30','Y','TAB','maPgMngFieldList')
go 

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPgMngFieldList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPgMngFieldDetail') ,'10','Y','TAB','maPgMngFieldDetail')
go 


/** 2016-06-29 노정현 월별보전목표 */
INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES
   (NEXT VALUE FOR sqapgpage_id , (SELECT page_id FROM TAPAGE WHERE file_name='maMtGoalList'),(SELECT page_id FROM TAPAGE WHERE file_name='maMtGoalDetail') , '10', 'Y', 
    'LABEL', 'date')
go
/** 2016-06-30 김정우 라인가동계획  */
    INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES
   (NEXT VALUE FOR sqapgpage_id , (SELECT page_id FROM TAPAGE WHERE file_name='maLineRunPlanList'),(SELECT page_id FROM TAPAGE WHERE file_name='maLineRunPlanDetail') , '10', 'Y', 
    'LABEL', 'workDate')
go

INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES
   (NEXT VALUE FOR sqapgpage_id , (SELECT page_id FROM TAPAGE WHERE file_name='maLnGoalList'),(SELECT page_id FROM TAPAGE WHERE file_name='maLnGoalDetail') , '10', 'Y', 
    'LABEL', 'date')
go
    
INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES
   (NEXT VALUE FOR sqapgpage_id , (SELECT page_id FROM TAPAGE WHERE file_name='maPmMstrList'),(SELECT page_id FROM TAPAGE WHERE file_name='maPmTrMstrDetail') , '80', 'Y', 
    'LABEL', 'pmNo')
go

/** 2016-07-06 김정우 작업결과 교육, 교육자 탭 추가 */
    
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultMstrList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultTrEleMstrDetail') ,'120','Y','LABEL','woNo')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultTrEleMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultTrEleCraftList') ,'10','Y','TAB','maWoResultTrEleCraftList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultTrEleCraftList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultTrEleCraftDetail') ,'10','Y','LABEL','trainUser')
go
/** 2016-07-07 김정우 설비구성품 탭 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maEqMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maEqMstrItemsList') ,'15','Y','TAB','maEqMstrItemsList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maEqMstrItemsList') ,(select aa.page_id from tapage aa where aa.file_name = 'maEqMstrItemsDetail') ,'10','Y','LABEL','accessoryName')
go

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoMonthWoList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultTrEleMstrDetail') ,'110','Y','LABEL','woNo')
go


INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPtIssList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPtIssDetail') ,'110','Y','LABEL','partNo')
go
/** 2016-07-28 김정우 작업결과에 첨부문서 붙히기 */

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmInsMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maDocLibList') ,'30','Y','TAB','maDocLibList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmRprMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maDocLibList') ,'20','Y','TAB','maDocLibList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmRplMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maDocLibList') ,'30','Y','TAB','maDocLibList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmClnMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maDocLibList') ,'30','Y','TAB','maDocLibList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmOilMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maDocLibList') ,'30','Y','TAB','maDocLibList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmRprMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maDocLibList') ,'20','Y','TAB','maDocLibList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmRplMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maDocLibList') ,'30','Y','TAB','maDocLibList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmOilMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maDocLibList') ,'30','Y','TAB','maDocLibList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultCmRprMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maDocLibList') ,'20','Y','TAB','maDocLibList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultCmRplMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maDocLibList') ,'30','Y','TAB','maDocLibList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultTrEleMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maDocLibList') ,'20','Y','TAB','maDocLibList')
go

/** 2016-07-27 김정우 월별재고목표 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maMonthStockGoalList') ,(select aa.page_id from tapage aa where aa.file_name = 'maMonthStockGoalDetail') ,'10','Y','LABEL','yyyymm')
go
/** 2016-08-03 김정우 월간보전지표 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maMonLocDnChart') ,(select aa.page_id from tapage aa where aa.file_name = 'maMonLocDnDetail') ,'10','Y','LABEL','yyyymm')
go
/** 2016-08-12 김정우 일일작업확인-작업목록  */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoDailyDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoDailyWoList') ,'20','Y','TAB','maWoDailyWoList')
go



INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPttMstrList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPttMstrDetail') ,'10','Y','LABEL','partNo')
go

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPttMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maDocLibList') ,'30','Y','TAB','maDocLibList')
go

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPttStckList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPttStckDetail') ,'10','Y','LABEL','partNo')
go

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPttRecList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPttRecDetail') ,'10','Y','LABEL','partNo')
go

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPttIssList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPttIssDetail') ,'10','Y','LABEL','ptIssListId')
go


/** 2016-09-06 박철완  */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPlantMngList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPlantMngDetail') ,'10','Y','LABEL','plant')
go

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPttDisList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPttDisDetail') ,'10','Y','LABEL','ptDisuseListId')
go

INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES
   (NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPttDisDetail'),  (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPttDisPartsList'), '10', 'Y', 
    'LABEL', 'ptItem')
go
    
    
    INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES
   (NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPttDisPartsList'),  (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPttDisPartsDetail'), '10', 'Y', 
    'LABEL', 'ptNo')
go
    
    INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoSchedList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoSchedDetail') ,'10','Y','LABEL','pmNo')
go


/** 2016.09.09 노정현*/
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
 VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPgUsrFieldList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPgUsrFieldDetail') ,'10','Y','LABEL','maPgUsrFieldDetail')
go
/** 2016-09-12 김정우 주간작업일정 추가 */
 
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoWeekWoList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmInsMstrDetail') ,'10','Y','LABEL','woNo')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoWeekWoList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmRprMstrDetail') ,'20','Y','LABEL','woNo')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoWeekWoList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmRplMstrDetail') ,'30','Y','LABEL','woNo')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoWeekWoList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmClnMstrDetail') ,'40','Y','LABEL','woNo')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoWeekWoList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmOilMstrDetail') ,'50','Y','LABEL','woNo')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoWeekWoList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmRprMstrDetail') ,'60','Y','LABEL','woNo')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoWeekWoList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmRplMstrDetail') ,'70','Y','LABEL','woNo')
go
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoWeekWoList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmOilMstrDetail') ,'80','Y','LABEL','woNo')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoWeekWoList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultCmRprMstrDetail') ,'90','Y','LABEL','woNo')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoWeekWoList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultCmRplMstrDetail') ,'100','Y','LABEL','woNo')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoWeekWoList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultTrEleMstrDetail') ,'110','Y','LABEL','woNo')
go

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPtPurReqDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maDocLibList') ,'40','Y','TAB','maDocLibList')
go 
 /** 2016-10-13 김정우 탭페이지    추가 */
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPtPoList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPtPoDetail') ,'10','Y','LABEL','poNo')
go 

/** 2016-10-17 김정우 탭페이지    추가 */
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWkCtrList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWkCtrDetail') ,'10','Y','LABEL','wkCtrNo')
go 

/** 2016-10-24 노정현 탭페이지    추가 */
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPtIssEmgList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPtIssEmgDetail') ,'10','Y','LABEL','ptIssListId')
go 

/** 2016-10-24 김정우 탭페이지 추가 */
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maResList') ,(select aa.page_id from tapage aa where aa.file_name = 'maResDetail') ,'10','Y','LABEL','keyName')
go 


/** 2016-10-28 김정우 금형관리 추가 */
  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maMoldsList') ,(select aa.page_id from tapage aa where aa.file_name = 'maMoldsDetail') ,'10','Y','LABEL','moldsNo')
go 

/** 2016-11-03 노정현 Help Desk 추가 */
  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maHelpList') ,(select aa.page_id from tapage aa where aa.file_name = 'maHelpDetail') ,'10','Y','LABEL','reqNo')
go 

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPtBuyReqHdrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maDocLibList') ,'20','Y','TAB','maDocLibList')
go 


/*2016.11.08일 박철완..설비상세유형 세부적으로 분리..**/
insert into TAPGPAGE(pgpage_id,page_id,c_page_id,ord_no,is_use,key_type,key_no)
select NEXT VALUE FOR sqapgpage_id,page_id
        ,(select page_id from tapage where file_name = 'maEqMachineMstrDetail')
        ,ord_no,is_use,key_type,key_no
from TAPGPAGE
where page_id  = (select page_id from tapage where file_name = 'maEqMstrList')
         and c_page_id =  (select page_id from tapage where file_name = 'maEqMstrDetail') 
go

insert into TAPGPAGE(pgpage_id,page_id,c_page_id,ord_no,is_use,key_type,key_no)
select NEXT VALUE FOR sqapgpage_id,page_id
        ,(select page_id from tapage where file_name = 'maEqMoldMstrDetail')
        ,ord_no,is_use,key_type,key_no
from TAPGPAGE
where page_id  = (select page_id from tapage where file_name = 'maEqMstrList')
         and c_page_id =  (select page_id from tapage where file_name = 'maEqMstrDetail') 
go

insert into TAPGPAGE(pgpage_id,page_id,c_page_id,ord_no,is_use,key_type,key_no)
select NEXT VALUE FOR sqapgpage_id,page_id
        ,(select page_id from tapage where file_name = 'maEqToolMstrDetail')
        ,ord_no,is_use,key_type,key_no
from TAPGPAGE
where page_id  = (select page_id from tapage where file_name = 'maEqMstrList')
         and c_page_id =  (select page_id from tapage where file_name = 'maEqMstrDetail') 
go

insert into TAPGPAGE(pgpage_id,page_id,c_page_id,ord_no,is_use,key_type,key_no)
select NEXT VALUE FOR sqapgpage_id,page_id
        ,(select page_id from tapage where file_name = 'maEqBuildingMstrDetail')
        ,ord_no,is_use,key_type,key_no
from TAPGPAGE
where page_id  = (select page_id from tapage where file_name = 'maEqMstrList')
         and c_page_id =  (select page_id from tapage where file_name = 'maEqMstrDetail') 
go

insert into TAPGPAGE(pgpage_id,page_id,c_page_id,ord_no,is_use,key_type,key_no)
select NEXT VALUE FOR sqapgpage_id,page_id
        ,(select page_id from tapage where file_name = 'maEqVehicleMstrDetail')
        ,ord_no,is_use,key_type,key_no
from TAPGPAGE
where page_id  = (select page_id from tapage where file_name = 'maEqMstrList')
         and c_page_id =  (select page_id from tapage where file_name = 'maEqMstrDetail') 
go

insert into TAPGPAGE(pgpage_id,page_id,c_page_id,ord_no,is_use,key_type,key_no)
select NEXT VALUE FOR sqapgpage_id,page_id
        ,(select page_id from tapage where file_name = 'maEqUtilityMstrDetail')
        ,ord_no,is_use,key_type,key_no
from TAPGPAGE
where page_id  = (select page_id from tapage where file_name = 'maEqMstrList')
         and c_page_id =  (select page_id from tapage where file_name = 'maEqMstrDetail') 
go

insert into TAPGPAGE(pgpage_id,page_id,c_page_id,ord_no,is_use,key_type,key_no)
select NEXT VALUE FOR sqapgpage_id,page_id
        ,(select page_id from tapage where file_name = 'maEqPartMstrDetail')
        ,ord_no,is_use,key_type,key_no
from TAPGPAGE
where page_id  = (select page_id from tapage where file_name = 'maEqMstrList')
         and c_page_id =  (select page_id from tapage where file_name = 'maEqMstrDetail') 
go


         insert into TAPGPAGE(pgpage_id,page_id,c_page_id,ord_no,is_use,key_type,key_no)
select NEXT VALUE FOR sqapgpage_id
        ,(select page_id from tapage where file_name = 'maEqMachineMstrDetail') as page_id
        ,c_page_id
        ,ord_no,is_use,key_type,key_no
from TAPGPAGE
where page_id  = (select page_id from tapage where file_name = 'maEqMstrDetail')
go

insert into TAPGPAGE(pgpage_id,page_id,c_page_id,ord_no,is_use,key_type,key_no)
select NEXT VALUE FOR sqapgpage_id
        ,(select page_id from tapage where file_name = 'maEqMoldMstrDetail') as page_id
        ,c_page_id
        ,ord_no,is_use,key_type,key_no
from TAPGPAGE
where page_id  = (select page_id from tapage where file_name = 'maEqMstrDetail') 
go

insert into TAPGPAGE(pgpage_id,page_id,c_page_id,ord_no,is_use,key_type,key_no)
select NEXT VALUE FOR sqapgpage_id
        ,(select page_id from tapage where file_name = 'maEqToolMstrDetail') as page_id
        ,c_page_id
        ,ord_no,is_use,key_type,key_no
from TAPGPAGE
where page_id  = (select page_id from tapage where file_name = 'maEqMstrDetail') 
go

insert into TAPGPAGE(pgpage_id,page_id,c_page_id,ord_no,is_use,key_type,key_no)
select NEXT VALUE FOR sqapgpage_id
        ,(select page_id from tapage where file_name = 'maEqBuildingMstrDetail') as page_id
        ,c_page_id
        ,ord_no,is_use,key_type,key_no
from TAPGPAGE
where page_id  = (select page_id from tapage where file_name = 'maEqMstrDetail') 
go

insert into TAPGPAGE(pgpage_id,page_id,c_page_id,ord_no,is_use,key_type,key_no)
select NEXT VALUE FOR sqapgpage_id
        ,(select page_id from tapage where file_name = 'maEqVehicleMstrDetail') as page_id
        ,c_page_id
        ,ord_no,is_use,key_type,key_no
from TAPGPAGE
where page_id  = (select page_id from tapage where file_name = 'maEqMstrDetail') 
go

insert into TAPGPAGE(pgpage_id,page_id,c_page_id,ord_no,is_use,key_type,key_no)
select NEXT VALUE FOR sqapgpage_id
        ,(select page_id from tapage where file_name = 'maEqUtilityMstrDetail') as page_id
        ,c_page_id
        ,ord_no,is_use,key_type,key_no
from TAPGPAGE
where page_id  = (select page_id from tapage where file_name = 'maEqMstrDetail') 
go

insert into TAPGPAGE(pgpage_id,page_id,c_page_id,ord_no,is_use,key_type,key_no)
select NEXT VALUE FOR sqapgpage_id
        ,(select page_id from tapage where file_name = 'maEqPartMstrDetail') as page_id
        ,c_page_id
        ,ord_no,is_use,key_type,key_no
from TAPGPAGE
where page_id  = (select page_id from tapage where file_name = 'maEqMstrDetail') 
go



/*2016.11.08 설비마스터 상세화면들에 권한주기..**/
/*         
insert into TAUGPGPGAU(ugpgpgau_id, usrgrp_id, pgpage_id)
select 
   NEXT VALUE FOR sqaugpgpgau_id
  ,a.usrgrp_id               as usrgrp_id
  ,b.page_id                 as pgpage_id
from TAUSRGRP a
    , (select page_id from tapage where file_name in ('maEqMachineMstrDetail','maEqMoldMstrDetail','maEqToolMstrDetail','maEqBuildingMstrDetail','maEqVehicleMstrDetail','maEqUtilityMstrDetail','maEqPartMstrDetail')
     ) b
where a.usrgrp_no in ('ADMIN','MANAGER','SYSTEM','STAFF')
*/
         


/** 2016-11-16 노정현 개선진행현황 상세 텝 추가 */
  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWrkImpList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWrkImpDetail') ,'10','Y','LABEL','goWrkImpNo')
go 

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWrkImpDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maDocLibList') ,'20','Y','TAB','maDocLibList')
go 

/** 2016-12-20 김정우 추가 */

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultMstrList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmGmMstrDetail') ,'130','Y','LABEL','woNo')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoWeekWoList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmGmMstrDetail') ,'120','Y','LABEL','woNo')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoMonthWoList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmGmMstrDetail') ,'120','Y','LABEL','woNo')
go

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmGmMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultCraftList') ,'10','Y','TAB','maWoResultCraftList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmGmMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maDocLibList') ,'20','Y','TAB','maDocLibList')
go

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPmMstrList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPmGmMstrDetail') ,'90','Y','LABEL','pmNo')
go  

/** 2017-01-05 김정우 작업시간 설정 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maLineTimeList') ,(select aa.page_id from tapage aa where aa.file_name = 'maLineTimeDetail') ,'10','Y','LABEL','eqLocNo')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maLineTimeDowList') ,(select aa.page_id from tapage aa where aa.file_name = 'maLineTimeDowDetail') ,'10','Y','LABEL','day2')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maLineTimeDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maLineTimeDowList') ,'10','Y','TAB','maLineTimeDowList')
go

/** 2017-01-10 김정우 추가 */

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoDailyDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoDailyActList') ,'30','Y','LABEL','mainActivities')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoDailyActList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoDailyActDetail') ,'10','Y','LABEL','equipName')
go

-----------------------------------------------------------------------------
--------------------------------유한킴벌리 patch완료 2017.01.13일-------------------
-----------------------------------------------------------------------------

/** 2017-01-18 김정우 작업요청 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoReqList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maWoReqDetail') ,'10','Y','LABEL','woReqNo')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoReqDetail') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maWoReqResList') ,'10','Y','TAB','maWoReqResList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoReqResList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maWoReqResDetail') ,'10','Y','LABEL','date')
go

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoReqDetail') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maWoReqDocLibList') ,'20','Y','TAB','maDocLibList')
go

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoReqDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maWoReqDocLibDetail') ,'10','Y','TAB','maDocLibDetail')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoReqResDetail') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maWoReqResDocLibList') ,'10','Y','TAB','maDocLibList')
go

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoReqResDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maWoReqResDocLibDetail') ,'10','Y','TAB','maDocLibDetail')
go

/**2017-01-25 김정우 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 
'maWoDayRptList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoDayRptDetail') 
,'10','Y','LABEL','date')
go

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 
'maWoDayRptDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoDayRptWoList') 
,'10','Y','TAB','maWoDayRptWoList')
go

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 
'maWoDayRptDetail') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maWoDayRptDocLibList') ,'20','Y','TAB','maDocLibList')
go

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 
'maWoDayRptDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maWoDayRptDocLibDetail') ,'10','Y','TAB','maDocLibDetail')
go


/**2017-02-11 박철완 추가 */

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPmMstrList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPmCalMstrDetail') ,'90','Y','LABEL','pmNo')
go

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultMstrList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmCalMstrDetail') ,'130','Y','LABEL','woNo')
go

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmCalMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultCraftList') ,'10','Y','TAB','maWoResultCraftList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmCalMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maDocLibList') ,'20','Y','TAB','maDocLibList')
go


/** 2017-02-21 김정우 추가 */

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultMstrList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmBaseMstrDetail') ,'140','Y','LABEL','woNo')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoWeekWoList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmBaseMstrDetail') ,'140','Y','LABEL','woNo')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoMonthWoList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmBaseMstrDetail') ,'140','Y','LABEL','woNo')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmBaseMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmRplCraftList') ,'10','Y','TAB','maWoResultCraftList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmBaseMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmRplPartList') ,'20','Y','TAB','maWoResultPartList')
go
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmBaseMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maDocLibList') ,'30','Y','TAB','maDocLibList')
go


/** 2017-03-09 노정현 추가  */


UPDATE  TAPGPAGE x SET  c_page_id = (SELECT page_id FROM TAPAGE WHERE file_name ='maWrkImpCnDocList')                                                 
WHERE  x.page_id  = (SELECT page_id FROM TAPAGE WHERE file_name ='maWrkImpDetail')
go

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWrkImpCnDocList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWrkImpCnDocDetail') ,'30','Y','TAB','maDocLibList')
go
           
 -----------------------------------------------------------------------------
--------------------------------유한킴벌리 patch완료 2017.03.15일-------------------
-----------------------------------------------------------------------------

/** 2017-03-21 노정현 추가  */

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mcMenuList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mcMenuDetail') ,'10','Y','TAB','mcMenuDetail')
go 

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mcUserList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mcUserDetail') ,'10','Y','TAB','mcUserDetail')
go 


   

/**2017-03-24 김정우 추가 */

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPgMngDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPgMngGrdList') ,'40','Y','TAB','maPgMngGrdList')
go 

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPgMngGrdList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPgMngGrdDetail') ,'10','Y','TAB','maGrdMngDetail')
go 

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPgMngGrdDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPgMngGrdColList') ,'10','Y','TAB','maPgMngGrdList')
go 

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPgMngGrdColList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPgMngGrdColDetail') ,'10','Y','LABEL','columnId')
go 


/**2017-04-04 이규선 추가 */
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maTableList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maTableDetail') ,'10','Y','TAB','maTableDetail')
go


INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maTableDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'maTableColList') ,'10','Y','TAB','maTableColList')
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
-----------------------------------------------------------------------------

/**2017-04-07 이규선 추가 */
update TAPGPAGE
set key_type='LABEL',
key_no='table'
where page_id=(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maTableList') 
go
 

update TAPGPAGE
set key_type='LABEL',
key_no='column'
where page_id=(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maTableDetail') 
go


 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maTableColList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maTableColDetail') ,'10','Y','LABEL','column')
go

go


/** 2017-04-07 노정현 사용자데이터 조회 추가  */

  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mcDataSelectList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mcDataSelectDetail') ,'10','Y','TAB','mcDataSelectDetail')
go 

  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mcDataSelectDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mcDataSelectScript') ,'10','Y','TAB','mcDataSelectScript')
go 

go


/** 2017-04-07 노정현 사용자데이터 조회 추가  */

  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mcDataSelectList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mcDataSelectDetail') ,'10','Y','TAB','mcDataSelectDetail')
go 

  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mcDataSelectDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mcDataSelectScript') ,'10','Y','TAB','mcDataSelectScript')
go 



-----------------------------------------------------------------------------
--------------------------------유한킴벌리 patch완료 2017.04.12일-------------------
-----------------------------------------------------------------------------

/** 2017-04-18 차한결  메일수신자설정 메일발송 버튼추가  */  
INSERT INTO TAPGBTN(pgbtn_id, page_id, button_id, button_loc, remark, ord_no, is_use, is_chkauth, is_basic, is_set_group, key_no, key_type) 
VALUES(NEXT VALUE FOR sqapgbtn_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maMailDetail') ,(SELECT aa.button_id FROM tabutton aa WHERE aa.button_no = 'SENDMAIL' ),'DETAIL','메일발송','5','Y','N','Y','N','SENDMAIL','BUTTON')
go
  

/** 2017-05-17 노정현 사용자데이터 조회 추가  */

  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgbtn_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maUserRptList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maUserRptDetail') ,'10','Y','TAB','maUserRptDetail')
go

  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgbtn_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maUserRptDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maUserRptDesign') ,'10','Y','TAB','maUserRptDesign')
go

  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgbtn_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maUserRptPopup') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maUserRptTableList') ,'10','Y','TAB','maUserRptTableList')
go

  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgbtn_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maUserRptTableList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maUserRptTableDetail') ,'10','Y','TAB','maUserRptTableDetail')
go

  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgbtn_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maUserRptTableDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maUserRptJoinList') ,'10','Y','TAB','maUserRptJoinList')
go

  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgbtn_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maUserRptJoinList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maUserRptJoinDetail') ,'10','Y','TAB','maUserRptJoinDetail')
go

  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgbtn_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maUserRptPopup') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maUserRptColList') ,'30','Y','TAB','maUserRptColList')
go

  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgbtn_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maUserRptColList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maUserRptColDetail') ,'10','Y','TAB','maUserRptColDetail')
go

  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgbtn_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maUserRptPopup') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maUserRptParamList') ,'40','Y','TAB','maUserRptParamList')
go

  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgbtn_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maUserRptParamList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maUserRptParamDetail') ,'10','Y','TAB','maUserRptParamDetail')
go

  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgbtn_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maUserRptPopup') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maUserRptOrdList') ,'50','Y','TAB','maUserRptOrdList')
go

  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgbtn_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maUserRptOrdList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maUserRptOrdDetail') ,'10','Y','TAB','maUserRptOrdDetail')
go



/** 2017-07-06  */
insert into tapgpage(pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no)
values(
    NEXT VALUE FOR sqapgbtn_id,  (select page_id from tapage where file_name = 'maEqMstrMoldList')
    , (select page_id from tapage where file_name = 'maEqMstrMoldDetail')
    ,'10','Y','LABEL','equipDesc'
);


insert into tapgpage( pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no )
values( NEXT VALUE FOR sqapgbtn_id
   ,  (select page_id from tapage where file_name = 'maEqMstrMoldProdList')
   ,  (select page_id from tapage where file_name = 'maEqMstrMoldProdDetail')
   , '10', 'Y', 'TAB', 'maEqMstrMoldProdList');

   
/*금형변경이력 목록페이지에 상세페이지 탭 연결*/
insert into tapgpage( pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no )
values( NEXT VALUE FOR sqapgbtn_id
   ,  (select page_id from tapage where file_name = 'maEqMstrMoldModHistList')
   ,  (select page_id from tapage where file_name = 'maEqMstrMoldModHistDetail')
   , '10', 'Y', 'TAB', 'maEqMstrMoldModHistList');

   
   /*금형상세에 생산품목,변경이력,첨부 탭 연결*/
insert into tapgpage( pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no )
values( NEXT VALUE FOR sqapgbtn_id
   ,  (select page_id from tapage where file_name = 'maEqMstrMoldDetail')
   ,  (select page_id from tapage where file_name = 'maEqMstrMoldProdList')
   , '10', 'Y', 'TAB', 'maEqMstrMoldProdList');

insert into tapgpage( pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no )
values( NEXT VALUE FOR sqapgbtn_id
   ,  (select page_id from tapage where file_name = 'maEqMstrMoldDetail')
   ,  (select page_id from tapage where file_name = 'maEqMstrMoldModHistList')
   , '20', 'Y', 'TAB', 'maEqMstrMoldModHistList');


insert into tapgpage( pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no )
values( NEXT VALUE FOR sqapgbtn_id
   ,  (select page_id from tapage where file_name = 'maEqMstrMoldDetail')
   ,  (select page_id from tapage where file_name = 'maDocLibList')
   , '30', 'Y', 'TAB', 'maDocLibList');


   
 /** 2017-07-04 차한결 추가 */
 UPDATE tapgpage set is_use = 'N'
  WHERE page_id = (SELECT page_id FROM tapage WHERE file_name = 'maEqBuildingMstrDetail')
  AND c_page_id <> (SELECT page_id FROM tapage WHERE file_name = 'maDocLibList')
  ;


/** 2017-07-05 차한결 추가 */   
INSERT INTO tapgpage VALUES (NEXT VALUE FOR SQAPGPAGE_ID, (SELECT page_id FROM tapage WHERE file_name = 'maEqToolMstrDetail'), (SELECT page_id FROM tapage WHERE file_name = 'assetListTcycleList'), '65','Y','TAB','assetListTcycleList');
  
/** 2017-07-07 차한결 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmCalMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListCavalList') ,'5','Y','TAB','workListCavalList');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListCavalList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListCavalDetail') ,'10','Y','TAB','workListCavalList'); 
  /** 2017-07-11 김정우 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultCompTerminalList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultCompTerminalDetail') ,'10','Y','LABEL','terminalNo');

/** 2017-07-11 노정현 추가 */
  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
  VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'failLibraryList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'failLibraryDetail') ,'10','Y','TAB','failLibraryDetail')
  go
     
  /** 2017-07-11 차한결 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPmMstrList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListRInsDetail') ,'10','Y','LABEL','pmNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPmMstrList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListEInsDetail') ,'30','Y','LABEL','pmNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPmMstrList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListDInsDetail') ,'20','Y','LABEL','pmNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListRInsDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListEquipList') ,'10','Y','LABEL','equipment'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListRInsDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPmMstrPointList') ,'20','Y','LABEL','maPmMstrPointList');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListDInsDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListEquipList') ,'10','Y','LABEL','equipment'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListDInsDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPmMstrPointList') ,'20','Y','LABEL','maPmMstrPointList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListEInsDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListEquipList') ,'10','Y','LABEL','equipment'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListEInsDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPmMstrPointList') ,'20','Y','LABEL','maPmMstrPointList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListEInsDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListSchdList') ,'30','Y','LABEL','date'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListSchdList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListSchdDetail') ,'10','Y','LABEL','date'); 
 
  /** 2017-07-12 노정현 추가 */

 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR SQAPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'maWoReqResList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'reqWorkReswoDetail') ,'20','Y','LABEL','woNo')
go

update tapgpage set key_type ='TAB', key_no = 'simpleAction' where c_page_id = (select aa.page_id from tapage aa where aa.file_name = 'maWoReqResDetail')
go

update tapgpage set key_type ='TAB', key_no = 'issueWoAction' where c_page_id = (select aa.page_id from tapage aa where aa.file_name = 'reqWorkReswoDetail')
go

/** 2017-07-18 노정현 추가 */
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR SQAPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'docCtgList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'docCtgDetail') ,'10','Y','TAB','categCode')
go

 /** 2017-07-19 노정현 추가 */
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPGPAGE_ID, 'docCtgLov','문서분류체계 Popup','N','문서분류체계 Popup','Y')
 go
 
 /** 2017-07-20 노정현 추가 */

 UPDATE tapgpage SET c_page_id = (select a.page_id from TAPAGE a where a.file_name ='maEqDocLibList') 
where page_id =  (select a.page_id from TAPAGE a where a.file_name ='maEqMstrDetail')  and c_page_id =  (select a.page_id from TAPAGE a where a.file_name ='maDocLibList')go

  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR SQAPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'maPtDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maPtDocLibDetail') ,'10','Y','TAB','maDocLibDetail')go
 
 UPDATE tapgpage SET c_page_id = (select a.page_id from TAPAGE a where a.file_name ='maPtDocLibList') 
where page_id =  (select a.page_id from TAPAGE a where a.file_name ='maPtMstrDetail')  and c_page_id =  (select a.page_id from TAPAGE a where a.file_name ='maDocLibList')go

  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR SQAPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'maEqMoldDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maEqMoldDocLibDetail') ,'10','Y','TAB','maDocLibDetail')go
 
 UPDATE tapgpage SET c_page_id = (select a.page_id from TAPAGE a where a.file_name ='maEqMoldDocLibList') 
where page_id =  (select a.page_id from TAPAGE a where a.file_name ='maEqMoldMstrDetail')  and c_page_id =  (select a.page_id from TAPAGE a where a.file_name ='maDocLibList')go

  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR SQAPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'maEqToolDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maEqToolDocLibDetail') ,'10','Y','TAB','maDocLibDetail')go
 
 UPDATE tapgpage SET c_page_id = (select a.page_id from TAPAGE a where a.file_name ='maEqToolDocLibList') 
where page_id =  (select a.page_id from TAPAGE a where a.file_name ='maEqToolMstrDetail')  and c_page_id =  (select a.page_id from TAPAGE a where a.file_name ='maDocLibList')go

  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR SQAPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'maEqBuildingDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maEqBuildingDocLibDetail') ,'10','Y','TAB','maDocLibDetail')go
 
 UPDATE tapgpage SET c_page_id = (select a.page_id from TAPAGE a where a.file_name ='maEqBuildingDocLibList') 
where page_id =  (select a.page_id from TAPAGE a where a.file_name ='maEqBuildingMstrDetail')  and c_page_id =  (select a.page_id from TAPAGE a where a.file_name ='maDocLibList')go

  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR SQAPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'maEqVehicleDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maEqVehicleDocLibDetail') ,'10','Y','TAB','maDocLibDetail')go
 
 UPDATE tapgpage SET c_page_id = (select a.page_id from TAPAGE a where a.file_name ='maEqVehicleDocLibList') 
where page_id =  (select a.page_id from TAPAGE a where a.file_name ='maEqVehicleMstrDetail')  and c_page_id =  (select a.page_id from TAPAGE a where a.file_name ='maDocLibList')go

  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR SQAPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'maEqUtilityDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maEqUtilityDocLibDetail') ,'10','Y','TAB','maDocLibDetail')go
 
 UPDATE tapgpage SET c_page_id = (select a.page_id from TAPAGE a where a.file_name ='maEqUtilityDocLibList') 
where page_id =  (select a.page_id from TAPAGE a where a.file_name ='maEqUtilityMstrDetail')  and c_page_id =  (select a.page_id from TAPAGE a where a.file_name ='maDocLibList')go

  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR SQAPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'maEqPartDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maEqPartDocLibDetail') ,'10','Y','TAB','maDocLibDetail')go
 
 UPDATE tapgpage SET c_page_id = (select a.page_id from TAPAGE a where a.file_name ='maEqPartDocLibList') 
where page_id =  (select a.page_id from TAPAGE a where a.file_name ='maEqPartMstrDetail')  and c_page_id =  (select a.page_id from TAPAGE a where a.file_name ='maDocLibList')go

  /** 2017-07-21 차한결 추가  */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultCompWrkcalList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name ='consultCompWrkcalDetail') ,'10','Y','LABEL','workCal');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultCompWrkcalDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name ='consultCompWrkcalDowsetList') ,'10','Y','LABEL','wrkcalDowSet'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultCompWrkcalDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name ='consultCompWrkcalDaysetList') ,'20','Y','LABEL','wrkcalDaySet');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultCompWrkcalDowsetList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name ='consultCompWrkcalDowsetDetail') ,'10','Y','LABEL','wrkcalDowSet'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultCompWrkcalDaysetList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name ='consultCompWrkcalDaysetDetail') ,'10','Y','LABEL','wrkcalDaySet'); 

/** 2017-07-29 박철완 추가 */

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'certDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name ='certEmpList') ,'10','Y','LABEL','certEmp');

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'certEmpList') ,(SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'certEmpDetail') ,'10','Y','LABEL','certEmp');


INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'eduDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name ='eduEmpList') ,'10','Y','LABEL','eduEmp');

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'eduEmpList') ,(SELECT aa.page_id FROM tapage aa
WHERE aa.file_name = 'eduEmpDetail') ,'10','Y','LABEL','eduEmp');


 /** 2017-08-02 차한결 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPmRprMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListRprEquipList') ,'10','Y','LABEL','equipment'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListRprEquipList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListRprEquipDetail') ,'10','Y','LABEL','equipment');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPmRplMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListRplEquipList') ,'10','Y','LABEL','equipment'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListRplEquipList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListRplEquipDetail') ,'10','Y','LABEL','equipment');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPmGmMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListGmEquipList') ,'10','Y','LABEL','equipment'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListGmEquipList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListGmEquipDetail') ,'10','Y','LABEL','equipment');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListRInsEquipList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListRInsEquipDetail') ,'10','Y','LABEL','equipment');
UPDATE tapgpage set c_page_id = (SELECT page_id FROM tapage WHERE file_name = 'workPmListRInsEquipList')
  WHERE page_id = ( SELECT page_id FROM tapage WHERE file_name = 'workPmListRInsDetail')
  AND c_page_id = ( SELECT page_id FROM tapage WHERE file_name = 'workPmListEquipList');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListDInsEquipList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListDInsEquipDetail') ,'10','Y','LABEL','equipment');
UPDATE tapgpage set c_page_id = (SELECT page_id FROM tapage WHERE file_name = 'workPmListDInsEquipList')
 WHERE page_id = ( SELECT page_id FROM tapage WHERE file_name = 'workPmListDInsDetail')
  AND c_page_id = ( SELECT page_id FROM tapage WHERE file_name = 'workPmListEquipList');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListEInsEquipList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListEInsEquipDetail') ,'10','Y','LABEL','equipment');  
UPDATE tapgpage set c_page_id = (SELECT page_id FROM tapage WHERE file_name = 'workPmListEInsEquipList')
 WHERE page_id = ( SELECT page_id FROM tapage WHERE file_name = 'workPmListEInsDetail')
  AND c_page_id = ( SELECT page_id FROM tapage WHERE file_name = 'workPmListEquipList');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPmInsMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListInsEquipList') ,'10','Y','LABEL','equipment'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListInsEquipList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListInsEquipDetail') ,'10','Y','LABEL','equipment');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPmCalMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListCalEquipList') ,'10','Y','LABEL','equipment'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListCalEquipList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListCalEquipDetail') ,'10','Y','LABEL','equipment'); 

/** 2017-08-08 김정우 추가 */
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'consultPgmGuideList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'consultPgmGuideDetail') ,'10','Y','TAB','consultPgmGuideDetail');

 /** 2017-08-08 차한결 추가  */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'rcmSysList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'rcmSysDetail') ,'10','Y','MENU','rcmSystem');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'rcmSysDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'rcmSysFDefList') ,'10','Y','LABEL','funcDef'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'rcmSysFDefList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'rcmSysFDefDetail') ,'10','Y','LABEL','funcNo'); 
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
 VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'rcmSysFDefDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'rcmSysFEnvList') ,'10','Y','LABEL','funcEnv'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'rcmSysFEnvList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'rcmSysFEnvDetail') ,'10','Y','LABEL','prompt');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'rcmSysDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'rcmSysEqList') ,'20','Y','LABEL','equipSet'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'rcmSysEqList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'rcmSysEqDetail') ,'10','Y','LABEL','equipment'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'rcmSysEqDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'rcmSysEqAsmbList') ,'10','Y','LABEL','eqAsmb'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'rcmSysEqAsmbList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'rcmSysEqAsmbDetail') ,'10','Y','LABEL','asmb');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'rcmSysDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'rcmSysEmpList') ,'30','Y','LABEL','analyst'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'rcmSysEmpList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'rcmSysEmpDetail') ,'10','Y','LABEL','emp'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'rcmSysDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'rcmSysDocLibList') ,'40','Y','TAB','maDocLibList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'rcmSysDocLibList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'rcmSysDocLibDetail') ,'10','Y','LABEL','docNo'); 


/** 2017-08-08 김정우 추가 */
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmCrityList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'rcmCrityDetail') ,'10','Y','TAB','rcmCrityDetail');

/** 2017-08-10 김정우 추가 */
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmCrityDetail') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'rcmCrityColList') ,'10','Y','TAB','rcmCrityColList');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmCrityColList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'rcmCrityColDetail') ,'10','Y','LABEL','prompt');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmCrityDetail') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'rcmCrityRowList') ,'20','Y','TAB','rcmCrityRowList');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmCrityRowList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'rcmCrityRowDetail') ,'10','Y','LABEL','prompt');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmCrityDetail') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'rcmCrityValList') ,'30','Y','TAB','rcmCrityValList');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmCrityValList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'rcmCrityValDetail') ,'10','Y','LABEL','prompt');


/** 2017-08-21 김영주 추가*/
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES(  NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultCompUserList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultCompUserDetail') ,'10','Y','LABEL','userName1'); 

 /** 2017-08-23 김정우 추가 */
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'assBaseList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'assBaseDetail') ,'10','Y','LABEL','number');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'assBaseDetail') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'assBaseGradeList') ,'10','Y','TAB','assBaseGradeList');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'assBaseGradeList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'assBaseGradeDetail') ,'10','Y','LABEL','grade0');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'assBaseDetail') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'assBasePointList') ,'20','Y','TAB','assBasePointList');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'assBasePointList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'assBasePointDetail') ,'10','Y','LABEL','prompt');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'assBasePointDetail') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'assBasePointValList') ,'10','Y','TAB','assBasePointValList');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'assBasePointValList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'assBasePointValDetail') ,'10','Y','LABEL','assBase');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'assBaseDetail') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'assBaseDocLibList') ,'30','Y','TAB','maDocLibDetail');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'assBaseDocLibList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'assBaseDocLibDetail') ,'10','Y','TAB','maDocLibDetail');



/** 2017-08-24 박철완 작업요청 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'reqWorkList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'reqWorkDetail') ,'10','Y','LABEL','woReqNo');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'reqWorkDetail') ,(select aa.page_id from tapage aa 
where aa.file_name = 'reqWorkResList') ,'10','Y','TAB','reqWorkResList');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'reqWorkResList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'reqWorkResDetail') ,'10','Y','LABEL','date');

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'reqWorkDetail') ,(select aa.page_id from tapage aa 
where aa.file_name = 'reqWorkDocLibList') ,'20','Y','TAB','maDocLibList');

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'reqWorkDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'reqWorkDocLibDetail') ,'10','Y','TAB','maDocLibDetail');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'reqWorkResDetail') ,(select aa.page_id from tapage aa 
where aa.file_name = 'reqWorkResDocLibList') ,'10','Y','TAB','maDocLibList');

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'reqWorkResDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'reqWorkResDocLibDetail') ,'10','Y','TAB','maDocLibDetail');


INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maEqMstrDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'assetListMstrToolsList') ,'50','Y','TAB','assetListMstrToolsList');


/** 2017-08-25 김영주 추가*/
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assAssetList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assAssetDetail') ,'10','Y','LABEL','docCountrNo'); 

/** 2017-08-30 김영주 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assAssetDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assAssetScoreList') ,'10','Y','TAB','assAssetScoreList');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assAssetScoreList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assAssetScoreDetail') ,'10','Y','LABEL','prompt');


/** 2017-08-30 장효성 추가*/
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultCompDeptList') ,(SELECT aa.page_id FROM TAPAGE aa 
WHERE aa.file_name = 'consultCompDeptDetail') ,'10','Y','LABEL','dept');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtPrcTpDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtPrcTpItemDocLibList') ,'40','Y','TAB','maDocLibList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtPrcTpItemDocLibList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtPrcTpItemDocLibDetail') ,'10','Y','TAB','maDocLibDetail');

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'invtPrcTpList') ,(SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'invtPrcTpDetail') ,'10','Y','LABEL','invtNo');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtPrcTpDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name ='invtPrcTpItemList') ,'10','Y','LABEL','proc');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtPrcTpItemList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name ='invtPrcTpItemDetail') ,'10','Y','LABEL','lType');


/** 2017-08-30 이근환 추가  */

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'consultCompEmpList') ,(select aa.page_id from tapage aa where aa.file_name = 'consultCompEmpDetail') ,'10','Y','LABEL','empNo'); 

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrWorkflowList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrWorkflowDetail') ,'10','Y','LABEL','docCountrNo'); 

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrWorkflowDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrWorkflowPhaseList') ,'10','Y','TAB','mgrWorkflowPhaseList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrWorkflowPhaseList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrWorkflowPhaseDetail') ,'10','Y','TAB','mgrWorkflowPhaseList'); 

/** 2017-09-06 노정현 추가 */


  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR SQAPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmCalDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maWoResultPmCalDocLibDetail') ,'10','Y','TAB','maDocLibDetail');
  UPDATE tapgpage SET c_page_id = (select a.page_id from TAPAGE a where a.file_name ='maWoResultPmCalDocLibList') 
where page_id =  (select a.page_id from TAPAGE a where a.file_name ='maWoResultPmCalMstrDetail')  and c_page_id =  (select a.page_id from TAPAGE a where a.file_name ='maDocLibList');
  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR SQAPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmGmDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maWoResultPmGmDocLibDetail') ,'10','Y','TAB','maDocLibDetail');
 
 UPDATE tapgpage SET c_page_id = (select a.page_id from TAPAGE a where a.file_name ='maWoResultPmGmDocLibList') 
where page_id =  (select a.page_id from TAPAGE a where a.file_name ='maWoResultPmGmMstrDetail')  and c_page_id =  (select a.page_id from TAPAGE a where a.file_name ='maDocLibList');

  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR SQAPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultTrEleDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maWoResultTrEleDocLibDetail') ,'10','Y','TAB','maDocLibDetail');
 
 UPDATE tapgpage SET c_page_id = (select a.page_id from TAPAGE a where a.file_name ='maWoResultTrEleDocLibList') 
where page_id =  (select a.page_id from TAPAGE a where a.file_name ='maWoResultTrEleMstrDetail')  and c_page_id =  (select a.page_id from TAPAGE a where a.file_name ='maDocLibList');
  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR SQAPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultCmRplDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maWoResultCmRplDocLibDetail') ,'10','Y','TAB','maDocLibDetail');
 
 UPDATE tapgpage SET c_page_id = (select a.page_id from TAPAGE a where a.file_name ='maWoResultCmRplDocLibList') 
where page_id =  (select a.page_id from TAPAGE a where a.file_name ='maWoResultCmRplMstrDetail')  and c_page_id =  (select a.page_id from TAPAGE a where a.file_name ='maDocLibList');
  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR SQAPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultCmRprDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maWoResultCmRprDocLibDetail') ,'10','Y','TAB','maDocLibDetail');
 
 UPDATE tapgpage SET c_page_id = (select a.page_id from TAPAGE a where a.file_name ='maWoResultCmRprDocLibList') 
where page_id =  (select a.page_id from TAPAGE a where a.file_name ='maWoResultCmRprMstrDetail')  and c_page_id =  (select a.page_id from TAPAGE a where a.file_name ='maDocLibList');
  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR SQAPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmOilDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maWoResultBmOilDocLibDetail') ,'10','Y','TAB','maDocLibDetail');
 
 UPDATE tapgpage SET c_page_id = (select a.page_id from TAPAGE a where a.file_name ='maWoResultBmOilDocLibList') 
where page_id =  (select a.page_id from TAPAGE a where a.file_name ='maWoResultBmOilMstrDetail')  and c_page_id =  (select a.page_id from TAPAGE a where a.file_name ='maDocLibList');
  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR SQAPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmRplDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maWoResultBmRplDocLibDetail') ,'10','Y','TAB','maDocLibDetail');
 
 UPDATE tapgpage SET c_page_id = (select a.page_id from TAPAGE a where a.file_name ='maWoResultBmRplDocLibList') 
where page_id =  (select a.page_id from TAPAGE a where a.file_name ='maWoResultBmRplMstrDetail')  and c_page_id =  (select a.page_id from TAPAGE a where a.file_name ='maDocLibList');
  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR SQAPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmRprDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maWoResultBmRprDocLibDetail') ,'10','Y','TAB','maDocLibDetail');
 
 UPDATE tapgpage SET c_page_id = (select a.page_id from TAPAGE a where a.file_name ='maWoResultBmRprDocLibList') 
where page_id =  (select a.page_id from TAPAGE a where a.file_name ='maWoResultBmRprMstrDetail')  and c_page_id =  (select a.page_id from TAPAGE a where a.file_name ='maDocLibList');
  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR SQAPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmOilDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maWoResultPmOilDocLibDetail') ,'10','Y','TAB','maDocLibDetail');
 
 UPDATE tapgpage SET c_page_id = (select a.page_id from TAPAGE a where a.file_name ='maWoResultPmOilDocLibList') 
where page_id =  (select a.page_id from TAPAGE a where a.file_name ='maWoResultPmOilMstrDetail')  and c_page_id =  (select a.page_id from TAPAGE a where a.file_name ='maDocLibList');
  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR SQAPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmClnDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maWoResultPmClnDocLibDetail') ,'10','Y','TAB','maDocLibDetail');
 
 UPDATE tapgpage SET c_page_id = (select a.page_id from TAPAGE a where a.file_name ='maWoResultPmClnDocLibList') 
where page_id =  (select a.page_id from TAPAGE a where a.file_name ='maWoResultPmClnMstrDetail')  and c_page_id =  (select a.page_id from TAPAGE a where a.file_name ='maDocLibList');
  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR SQAPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmRplDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maWoResultPmRplDocLibDetail') ,'10','Y','TAB','maDocLibDetail');
 
 UPDATE tapgpage SET c_page_id = (select a.page_id from TAPAGE a where a.file_name ='maWoResultPmRplDocLibList') 
where page_id =  (select a.page_id from TAPAGE a where a.file_name ='maWoResultPmRplMstrDetail')  and c_page_id =  (select a.page_id from TAPAGE a where a.file_name ='maDocLibList');
  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR SQAPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmRprDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maWoResultPmRprDocLibDetail') ,'10','Y','TAB','maDocLibDetail');
 
 UPDATE tapgpage SET c_page_id = (select a.page_id from TAPAGE a where a.file_name ='maWoResultPmRprDocLibList') 
where page_id =  (select a.page_id from TAPAGE a where a.file_name ='maWoResultPmRprMstrDetail')  and c_page_id =  (select a.page_id from TAPAGE a where a.file_name ='maDocLibList');
  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR SQAPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultPmInsDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maWoResultPmInsDocLibDetail') ,'10','Y','TAB','maDocLibDetail');
 
 UPDATE tapgpage SET c_page_id = (select a.page_id from TAPAGE a where a.file_name ='maWoResultPmInsDocLibList') 
where page_id =  (select a.page_id from TAPAGE a where a.file_name ='maWoResultPmInsMstrDetail')  and c_page_id =  (select a.page_id from TAPAGE a where a.file_name ='maDocLibList');
  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR SQAPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'maWoDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maWoDocLibDetail') ,'10','Y','TAB','maDocLibDetail');
 
 UPDATE tapgpage SET c_page_id = (select a.page_id from TAPAGE a where a.file_name ='maWoDocLibList') 
where page_id =  (select a.page_id from TAPAGE a where a.file_name ='maWoResultMstrDetail')  and c_page_id =  (select a.page_id from TAPAGE a where a.file_name ='maDocLibList');

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmFfailList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'rcmFfailDetail') ,'10','Y','LABEL','funcNo');


INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'rcmFfailDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name ='rcmFfailItemList') ,'10','Y','MENU','fnFail');

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'rcmFfailItemList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name ='rcmFfailItemDetail') ,'10','Y','LABEL','rcmFfailNo');


  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR SQAPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'partAdjStkTakeDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'partAdjStkTakeDocLibDetail') ,'10','Y','TAB','maDocLibDetail');
 
 UPDATE tapgpage SET c_page_id = (select a.page_id from TAPAGE a where a.file_name ='partAdjStkTakeDocLibList') 
where page_id =  (select a.page_id from TAPAGE a where a.file_name ='partAdjStkTakeDetail')  and c_page_id =  (select a.page_id from TAPAGE a where a.file_name ='maDocLibList');

  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR SQAPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'maWrkImpDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maWrkImpDocLibDetail') ,'10','Y','TAB','maDocLibDetail');

 UPDATE tapgpage SET c_page_id = (select a.page_id from TAPAGE a where a.file_name ='maWrkImpDocLibList') 
where page_id =  (select a.page_id from TAPAGE a where a.file_name ='maWrkImpDetail')  and c_page_id =  (select a.page_id from TAPAGE a where a.file_name ='maDocLibList');

  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR SQAPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'maPttMstrDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maPttMstrDocLibDetail') ,'10','Y','TAB','maDocLibDetail');
 
 UPDATE tapgpage SET c_page_id = (select a.page_id from TAPAGE a where a.file_name ='maPttMstrDocLibList') 
where page_id =  (select a.page_id from TAPAGE a where a.file_name ='maPttMstrDetail')  and c_page_id =  (select a.page_id from TAPAGE a where a.file_name ='maDocLibList');

  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR SQAPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'maPtPurReqDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maPtPurReqDocLibDetail') ,'10','Y','TAB','maDocLibDetail');
 
 UPDATE tapgpage SET c_page_id = (select a.page_id from TAPAGE a where a.file_name ='maPtPurReqDocLibList') 
where page_id =  (select a.page_id from TAPAGE a where a.file_name ='maPtPurReqDetail')  and c_page_id =  (select a.page_id from TAPAGE a where a.file_name ='maDocLibList');

  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR SQAPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'maPtBuyReqHdrDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maPtBuyReqHdrDocLibDetail') ,'10','Y','TAB','maDocLibDetail');
 
 UPDATE tapgpage SET c_page_id = (select a.page_id from TAPAGE a where a.file_name ='maPtBuyReqHdrDocLibList') 
where page_id =  (select a.page_id from TAPAGE a where a.file_name ='maPtBuyReqHdrDetail')  and c_page_id =  (select a.page_id from TAPAGE a where a.file_name ='maDocLibList');

  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR SQAPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'maQnaAnsDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maQnaAnsDocLibDetail') ,'10','Y','TAB','maDocLibDetail');
 
 UPDATE tapgpage SET c_page_id = (select a.page_id from TAPAGE a where a.file_name ='maQnaAnsDocLibList') 
where page_id =  (select a.page_id from TAPAGE a where a.file_name ='maQnaAnsDetail')  and c_page_id =  (select a.page_id from TAPAGE a where a.file_name ='maDocLibList');

  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR SQAPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'maQnaDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maQnaDocLibDetail') ,'10','Y','TAB','maDocLibDetail');
 
 UPDATE tapgpage SET c_page_id = (select a.page_id from TAPAGE a where a.file_name ='maQnaDocLibList') 
where page_id =  (select a.page_id from TAPAGE a where a.file_name ='maQnaDetail')  and c_page_id =  (select a.page_id from TAPAGE a where a.file_name ='maDocLibList');

  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR SQAPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'maDocCntrCdDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maDocCntrCdDocLibDetail') ,'10','Y','TAB','maDocLibDetail');
 
 UPDATE tapgpage SET c_page_id = (select a.page_id from TAPAGE a where a.file_name ='maDocCntrCdDocLibList') 
where page_id =  (select a.page_id from TAPAGE a where a.file_name ='maDocCntrCdDetail')  and c_page_id =  (select a.page_id from TAPAGE a where a.file_name ='maDocLibList');

  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR SQAPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'maPtRepDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'maPtRepDocLibDetail') ,'10','Y','TAB','maDocLibDetail');
 
 UPDATE tapgpage SET c_page_id = (select a.page_id from TAPAGE a where a.file_name ='maPtRepDocLibList') 
where page_id =  (select a.page_id from TAPAGE a where a.file_name ='maPtRepDetail')  and c_page_id =  (select a.page_id from TAPAGE a where a.file_name ='maDocLibList');

  INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
 VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'docFileLov') ,'gridbox','첨부파일 팝업','10');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maPtRepDocLibList') ,'gridbox','부품수리 첨부문서 Grid','10');

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maDocCntrCdDocLibList') ,'gridbox','일반자료실 첨부문서 Grid','10');

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maQnaDocLibList') ,'gridbox','질의 첨부문서 Grid','10');

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maQnaAnsDocLibList') ,'gridbox','질의변답 첨부문서 Grid','10');

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maPtBuyReqHdrDocLibList') ,'gridbox','구매신청 첨부문서 Grid','10');
INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maPtPurReqDocLibList') ,'gridbox','부품수리 첨부문서 Grid','10');

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maPttMstrDocLibList') ,'gridbox','공구 첨부문서 Grid','10');

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWrkImpDocLibList') ,'gridbox','개선 첨부문서 Grid','10');

INSERT INTO TAPGGRID (PGGRID_ID, PAGE_ID, GRID_OBJ_ID, DESCRIPTION, HEIGHT) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'partAdjStkTakeDocLibList') ,'gridbox','부품실사 첨부문서 Grid','10');


   INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmFfailDetail') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'rcmFfailDocLibList') ,'10','Y','TAB','maDocLibList');


   INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmFfailDocLibList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'rcmFfailDocLibDetail') ,'10','Y','TAB','maDocLibDetail');


 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmFmeaList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'rcmFmeaDetail') ,'10','Y','LABEL','failName');



 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmFmeaCrityList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'rcmFmeaCrityDetail') ,'10','Y','LABEL','prompt');

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmFmeaDetail') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'rcmFmeaCrityList') ,'10','Y','LABEL','critical');

  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR SQAPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'rcmFmeaDetail') ,(select aa.page_id from tapage aa 
where aa.file_name = 'rcmFmeaDocLibList') ,'20','Y','TAB','maDocLibList');

  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR SQAPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'rcmFmeaDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'rcmFmeaDocLibDetail') ,'10','Y','TAB','maDocLibDetail');


 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmPmtaskList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'rcmPmtaskDetail') ,'10','Y','LABEL','failName');

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmPmtaskMapList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'rcmPmtaskMapDetail') ,'10','Y','LABEL','prompt');

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmPmtaskDetail') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'rcmPmtaskMapList') ,'10','Y','LABEL','pmtaskmap');

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmPmtaskDetail') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'rcmPmtaskDocLibList') ,'20','Y','TAB','maDocLibList');

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR SQAPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'rcmPmtaskDocLibList') ,(select aa.page_id from tapage aa 
where aa.file_name = 'rcmPmtaskDocLibDetail') ,'10','Y','TAB','maDocLibDetail');

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmPmtaskCndtList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'rcmPmtaskCndtDetail') ,'10','Y','LABEL','altTask');

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmPmtaskDetail') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'rcmPmtaskCndtList') ,'10','Y','TAB','pmtaskCndt');


 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmPmtaskCrityList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'rcmPmtaskCrityDetail') ,'10','Y','LABEL','prompt');

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'rcmPmtaskDetail') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'rcmPmtaskCrityList') ,'5','Y','LABEL','critical');


 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoWeekWoList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'maWoResultPmCalMstrDetail') ,'95','Y','LABEL','wo');


INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'invtList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'invtDetail') ,'10','Y','TAB','invtlistNo');


 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'invtPhaseList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'invtPhaseDetail') ,'10','Y','TAB','invtlistNo');

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'invtDetail') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'invtPhaseList') ,'10','Y','TAB','maCdSysCodeDetail');


 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'invtDetail') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'invtDocLibList') ,'20','Y','TAB','maDocLibList');


 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'invtDocLibList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'invtDocLibDetail') ,'20','Y','TAB','maDocLibDetail');


 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'invtPrcList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'invtPrcDetail') ,'10','Y','TAB','invtPrcDetail');


 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'invtPrcDetail') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'invtPrcDocLibList') ,'20','Y','TAB','maDocLibList');


 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'invtPrcDocLibList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'invtPrcDocLibDetail') ,'20','Y','TAB','maDocLibDetail');


 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultMstrList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'maWoResultPmGnlMstrDetail') ,'140','Y','LABEL','woNo');


 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workListGnlCaEqList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'workListGnlCaEqDetail') ,'10','Y','LABEL','calibEq');

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultPmGnlMstrDetail') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'workListGnlCaEqList') ,'10','Y','LABEL','calibEq');

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workListGnlCavalList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'workListGnlCavalDetail') ,'10','Y','LABEL','calibPointType');

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultPmGnlMstrDetail') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'workListGnlCavalList') ,'20','Y','TAB','workListCavalList');


 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultGnlCaCraftList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'maWoResultGnlCaCraftDetail') ,'10','Y','LABEL','woCraft');

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultPmGnlMstrDetail') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'maWoResultGnlCaCraftList') ,'30','Y','LABEL','woCraft');


 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultGnlCaDocLibList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'maWoResultGnlCaDocLibDetail') ,'10','Y','TAB','maDocLibDetail');

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultPmGnlMstrDetail') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'maWoResultGnlCaDocLibList') ,'40','Y','TAB','maDocLibList');

/** 2017-09-08 차한결 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiDetail') ,'10','Y','MENU','maPmInsList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiDocLibList') ,'20','Y','TAB','maDocLibList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiDocLibList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiDocLibDetail') ,'10','Y','LABEL',''); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiPointList') ,'10','Y','TAB','maPmMstrPointList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiPointList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiPointDetail') ,'10','Y','LABEL','checkPoint');

 /** 2017-09-14 이근환 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPmMstrList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListPtrlDetail') ,'10','Y','LABEL','ptrlNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListPtrlDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListPtrlRtList') ,'10','Y','TAB','ptrlRt'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListPtrlRtList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListPtrlRtDetail') ,'10','Y','LABEL','ptrlOrd'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListPtrlRtDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListPtrlRtPointList') ,'10','Y','TAB','maPmMstrPointList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListPtrlRtPointList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListPtrlRtPointDetail') ,'10','Y','LABEL','pmStepNum'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListPtrlDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListPtrlShiftList') ,'20','Y','LABEL','shiftType'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListPtrlShiftList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListPtrlShiftDetail') ,'10','Y','LABEL','shiftType'); 

/** 2017-09-19 이근환 추가*/
UPDATE TAPGPAGE SET key_no='maEqToolMstrDetail.maEqMstrSpecList'
WHERE c_page_id=(SELECT page_id FROM TAPAGE WHERE file_name='maEqMstrSpecList')
AND page_id=(SELECT page_id FROM TAPAGE WHERE file_name='maEqToolMstrDetail')

 /** 2017-09-19 김정우 추가 */
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workPmStdCalibList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'workPmStdCalibDetail') ,'10','Y','LABEL','calibType');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workPmStdCalibDetail') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'workPmStdCalibValList') ,'10','Y','TAB','workPmStdCalibValList');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workPmStdCalibValList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'workPmStdCalibValDetail') ,'10','Y','LABEL','calibPointType');

  /** 2017-09-20 차한결 추가 */
INSERT INTO tapgpage 
SELECT 
NEXT VALUE FOR sqapgpage_id,
(SELECT page_id FROM tapage WHERE file_name = 'workPmListWorkList'),
x.c_page_id,
ROW_NUMBER() over(order by x.pgpage_id)*10,
'Y',
x.key_type,
x.key_no,
remark
 FROM tapgpage x  
WHERE page_id = (SELECT page_id FROM tapage y WHERE y.page_id = x.page_id AND y.file_name = 'maPmMstrList')  
AND c_page_id IN (SELECT page_id FROM tapage y WHERE file_name IN ('maPmRprMstrDetail','maPmRplMstrDetail','maPmOilMstrDetail','maPmClnMstrDetail','maPmGmMstrDetail'))
;
INSERT INTO tapgpage 
SELECT 
NEXT VALUE FOR sqapgpage_id,
(SELECT page_id FROM tapage WHERE file_name = 'workPmListInsList'),
x.c_page_id,
ROW_NUMBER() over(order by x.pgpage_id)*10,
'Y',
x.key_type,
x.key_no,
remark
 FROM tapgpage x  
WHERE page_id = (SELECT page_id FROM tapage y WHERE y.page_id = x.page_id AND y.file_name = 'maPmMstrList')  
AND c_page_id IN (SELECT page_id FROM tapage y WHERE file_name IN ('maPmInsMstrDetail','workPmListRInsDetail','workPmListDInsDetail','workPmListEInsDetail'))
;
INSERT INTO tapgpage 
SELECT 
NEXT VALUE FOR sqapgpage_id,
(SELECT page_id FROM tapage WHERE file_name = 'workPmListCalList'),
x.c_page_id,
ROW_NUMBER() over(order by x.pgpage_id)*10,
'Y',
x.key_type,
x.key_no,
remark
 FROM tapgpage x  
WHERE page_id = (SELECT page_id FROM tapage y WHERE y.page_id = x.page_id AND y.file_name = 'maPmMstrList')
AND c_page_id IN (SELECT page_id FROM tapage y WHERE file_name IN ('maPmCalMstrDetail','maPmCalEtcMstrDetail'))
;
INSERT INTO tapgpage 
SELECT 
NEXT VALUE FOR sqapgpage_id,
(SELECT page_id FROM tapage WHERE file_name = 'workPmListTrList'),
x.c_page_id,
ROW_NUMBER() over(order by x.pgpage_id)*10,
'Y',
x.key_type,
x.key_no,
remark
 FROM tapgpage x  
WHERE page_id = (SELECT page_id FROM tapage y WHERE y.page_id = x.page_id AND y.file_name = 'maPmMstrList')
AND c_page_id IN (SELECT page_id FROM tapage y WHERE file_name IN ('maPmTrMstrDetail'))
;


/** 2017-09-21 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListPtrlResultMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListPointList') ,'10','Y','LABEL','pmContents');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListPointList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListPointDetail') ,'10','Y','LABEL','pmContents');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListPtrlResultMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListDocLibList') ,'20','Y','TAB','maDocLibList');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListDocLibList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListDocLibDetail') ,'20','Y','TAB','maDocLibDetail');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListPtrlResultList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListPtrlResultMstrDetail') ,'10','Y','LABEL','date'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmPtrlMonthList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListPtrlResultMstrDetail') ,'10','Y','LABEL','date'); 


/** 2017-09-21 김정우 */
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workFmeaList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'workFmeaDetail') ,'10','Y','LABEL','number');

/*2017.09.21 이근환*/
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReadyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtPrcDetail') ,'20','Y','TAB','invtPrcDetail'); 

  /** 2017-09-22 차한결 추가 */
INSERT INTO tapgpage 
SELECT 
NEXT VALUE FOR sqapgpage_id,
(SELECT page_id FROM tapage WHERE file_name = 'workListPmiList'),
x.c_page_id,
ROW_NUMBER() over(order by x.pgpage_id)*10,
'Y',
x.key_type,
x.key_no,
remark
 FROM tapgpage x  
WHERE page_id = (SELECT page_id FROM tapage y WHERE y.page_id = x.page_id AND y.file_name = 'maWoResultMstrList')  
AND c_page_id IN (SELECT page_id FROM tapage y WHERE file_name IN ('maWoResultPmInsMstrDetail','workListRInsDetail','workListDInsDetail','workListEInsDetail'));

/**2017-09-22 이근환 추가*/ 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtPrcDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'10','Y','LABEL','appByHist');

/** 2017-09-22 장효성 추가*/

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from TAPAGE aa where aa.file_name = 'maEqMachMstrList') ,(select aa.page_id from TAPAGE aa where aa.file_name = 'maEqMstrDetail') ,'10','Y','LABEL','equipment');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from TAPAGE aa where aa.file_name = 'maEqUtilityMstrList') ,(select aa.page_id from TAPAGE aa where aa.file_name = 'maEqUtilityMstrDetail') ,'10','Y','LABEL','equipment');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from TAPAGE aa where aa.file_name = 'maEqToolMstrList') ,(select aa.page_id from TAPAGE aa where aa.file_name = 'maEqToolMstrDetail') ,'10','Y','LABEL','equipment');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from TAPAGE aa where aa.file_name = 'maEqBuildingMstrList') ,(select aa.page_id from TAPAGE aa where aa.file_name = 'maEqBuildingMstrDetail') ,'10','Y','LABEL','equipment');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultTrMstrList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultTrEleMstrDetail') ,'10','Y','LABEL','woNo');


/** 2017-09-26 노정현 추가 */

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maEqPartMstrList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'maEqPartMstrDetail') ,'10','Y','LABEL','equipDesc');

/** 2017-09-26 이규선 추가 */
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoCmResultMstrList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultCmRprMstrDetail') ,'10','Y','LABEL','woNo');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoPmwResultMstrList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmRprMstrDetail') ,'10','Y','LABEL','woNo');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoPmwResultMstrList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmRplMstrDetail') ,'20','Y','LABEL','woNo');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoPmwResultMstrList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmOilMstrDetail') ,'20','Y','LABEL','woNo');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoPmwResultMstrList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmClnMstrDetail') ,'20','Y','LABEL','woNo');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoPmwResultMstrList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmGmMstrDetail') ,'20','Y','LABEL','woNo');


 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultBmMstrList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultBmRprMstrDetail') ,'20','Y','LABEL','woNo');


 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultBmMstrList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultBmRplMstrDetail') ,'20','Y','LABEL','woNo');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultBmMstrList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultBmOilMstrDetail') ,'20','Y','LABEL','woNo');


/** 2017-09-26 김정우 추가 */
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultMstrList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'maWoResultPmSclMstrDetail') ,'140','Y','LABEL','woNo');

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workListSclCaEqList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'workListSclCaEqDetail') ,'10','Y','LABEL','calibEq');

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultPmSclMstrDetail') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'workListSclCaEqList') ,'10','Y','LABEL','calibEq');

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultPmSclMstrDetail') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'workListSclCavalList') ,'20','Y','TAB','workListCavalList');

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultSclCaCraftList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'maWoResultSclCaCraftDetail') ,'10','Y','LABEL','woCraft');

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultPmSclMstrDetail') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'maWoResultSclCaCraftList') ,'30','Y','LABEL','woCraft');

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultSclCaDocLibList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'maWoResultSclCaDocLibDetail') ,'10','Y','TAB','maDocLibDetail');

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultPmSclMstrDetail') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'maWoResultSclCaDocLibList') ,'40','Y','TAB','maDocLibList');

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultMstrList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'maWoResultPmPrsMstrDetail') ,'140','Y','LABEL','woNo');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workListPrsCaEqList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'workListPrsCaEqDetail') ,'10','Y','LABEL','calibEq');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultPmPrsMstrDetail') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'workListPrsCaEqList') ,'10','Y','LABEL','calibEq');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workListPrsCavalList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'workListPrsCavalDetail') ,'10','Y','LABEL','calibPointType');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultPmPrsMstrDetail') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'workListPrsCavalList') ,'20','Y','TAB','workListCavalList');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultPrsCaCraftList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'maWoResultPrsCaCraftDetail') ,'10','Y','LABEL','woCraft');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultPmPrsMstrDetail') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'maWoResultPrsCaCraftList') ,'30','Y','LABEL','woCraft');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultPrsCaDocLibList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'maWoResultPrsCaDocLibDetail') ,'10','Y','TAB','maDocLibDetail');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultPmPrsMstrDetail') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'maWoResultPrsCaDocLibList') ,'40','Y','TAB','maDocLibList');

/** 2017-09-27 이규선 추가 */
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoPmcResultMstrList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmGnlMstrDetail') ,'20','Y','LABEL','woNo');

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoPmcResultMstrList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmSclMstrDetail') ,'20','Y','LABEL','woNo');

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoPmcResultMstrList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmPrsMstrDetail') ,'20','Y','LABEL','woNo');


 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoPmcResultMstrList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmCalEtcMstrDetail') ,'20','Y','LABEL','woNo');


/** 2017-09-28 김영주 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workFmeaReqList') ,(SELECT aa.page_id FROM TAPAGE aa 
WHERE aa.file_name = 'workFmeaReqDetail') ,'10','Y','LABEL','number');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workFmeaResList') ,(SELECT aa.page_id FROM TAPAGE aa 
WHERE aa.file_name = 'workFmeaResDetail') ,'10','Y','LABEL','number');

/** 2017-10-11 이근환 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'daewoongKpiOeeDailyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'daewoongKpiOeeDailyDetailList') ,'10','Y','',''); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'daewoongKpiOeeDailyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'daewoongKpiOeeDailyChart') ,'20','Y','',''); 

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'daewoongKpiOeeMonthlyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'daewoongKpiOeeMonthlyDetailList') ,'10','Y','',''); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'daewoongKpiOeeMonthlyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'daewoongKpiOeeMonthlyChart') ,'20','Y','',''); 


/** 2017-10-13 김정우 추가 */
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'eduEmpDetail') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'eduEmpDocLibList') ,'10','Y','TAB','maDocLibList');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'eduEmpDocLibList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'eduEmpDocLibDetail') ,'10','Y','TAB','maDocLibDetail');

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'certEmpDetail') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'certEmpDocLibList') ,'10','Y','TAB','maDocLibList');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'certEmpDocLibList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'certEmpDocLibDetail') ,'10','Y','TAB','maDocLibDetail');

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'orgEmpTrainDetail') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'orgEmpTrainDocLibList') ,'10','Y','TAB','maDocLibList');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'orgEmpTrainDocLibList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'orgEmpTrainDocLibDetail') ,'10','Y','TAB','maDocLibDetail');

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'orgEmpCertDetail') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'orgEmpCertDocLibList') ,'10','Y','TAB','maDocLibList');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'orgEmpCertDocLibList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'orgEmpCertDocLibDetail') ,'10','Y','TAB','maDocLibDetail');


/** 2017-10-16 김영주 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmCheckList') ,(SELECT aa.page_id FROM TAPAGE aa 
WHERE aa.file_name = 'workPmCheckDetail') ,'10','Y','LABEL','process'); 

/**  2017-10-16 이근환 추가*/
UPDATE TAPGPAGE SET key_type='LABEL', key_no='daily' WHERE page_id=(SELECT page_id FROM TAPAGE WHERE file_name='daewoongKpiOeeDailyList');
UPDATE TAPGPAGE SET key_type='LABEL', key_no='monthly' WHERE page_id=(SELECT page_id FROM TAPAGE WHERE file_name='daewoongKpiOeeMonthlyList');

/**  2017-10-17 이근환 추가*/
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetReportLccEquipList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetReportLccEquipDetailList') ,'10','Y','LABEL','monthly'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetReportLccEquipList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetReportLccEquipChart') ,'20','Y','LABEL','monthly'); 

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetReportLccLocList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetReportLccLocDetailList') ,'10','Y','LABEL','monthly'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetReportLccLocList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetReportLccLocChart') ,'20','Y','LABEL','monthly');


/** 2017-10-18 이규선 추가 */  
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPtRecSerialList') ,(SELECT aa.page_id FROM TAPAGE aa 
WHERE aa.file_name = 'maPtRecSerialDetail') ,'10','Y','LABEL','Serial');


/** 2017-10-18 차한결 추가 */
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
 VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'commRevHistList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'commRevHistDetail') ,'10','Y','TAB','REVISIONHISTORY');
 
/** 2017-10-18 노정현 추가  */
 
  INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultPmRplPartDetail') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'workListPmRplPartSerialList') ,'10','Y','TAB','SerialNo');


 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workListPmRplPartSerialList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'workListPmRplPartSerialDetail') ,'10','Y','TAB','inSerialNo');

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultCmRplPartDetail') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'workListCmRplPartSerialList') ,'10','Y','TAB','SerialNo');


 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workListCmRplPartSerialList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'workListCmRplPartSerialDetail') ,'10','Y','TAB','inSerialNo');

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultBmRplPartDetail') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'workListBmRplPartSerialList') ,'10','Y','TAB','SerialNo');


 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workListBmRplPartSerialList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'workListBmRplPartSerialDetail') ,'10','Y','TAB','inSerialNo');


/** 2017-10-19 장효성 추가  */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPtIssDetail') ,(SELECT aa.page_id FROM TAPAGE aa 
WHERE aa.file_name = 'partIssWoItemList') ,'120','Y','LABEL','serialNo');

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partIssWoItemList') ,(SELECT aa.page_id FROM TAPAGE aa 
WHERE aa.file_name = 'partIssWoItemDetail') ,'130','Y','LABEL','partNo');


/** 2017-10-19 김정우 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workCalPmRInsPeriodList') ,(SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'workCalPmRInsPeriodDetail') ,'10','Y','LABEL','pmNo');

/** 2017-10-24 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListDInsDetail') ,(SELECT aa.page_id FROM TAPAGE aa 
WHERE aa.file_name = 'workPmListDInsPointList') ,'20','Y','LABEL','pm');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListDInsPointList') ,(SELECT aa.page_id FROM TAPAGE aa 
WHERE aa.file_name = 'workPmListDInsPointDetail') ,'10','Y','LABEL','pm');

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiDInsList') ,(SELECT aa.page_id FROM TAPAGE aa 
WHERE aa.file_name = 'workPmiDInsDetail') ,'10','Y','LABEL','woNo'); 

/** 2017-10-24 김정우 */
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoPmcResultMstrList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'maWoResultPmSclMstrDetail') ,'140','Y','LABEL','woNo');

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoPmcResultMstrList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'maWoResultPmPrsMstrDetail') ,'140','Y','LABEL','woNo');

/** 2017-10-25 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiDInsDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiDInsPointList') ,'20','Y','LABEL','pm');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiDInsPointList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiDInsPointDetail') ,'10','Y','LABEL','pm');

/** 2017-10-25 김정우 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( next value for sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListInsList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListPtrlDetail') ,'60','Y','LABEL','pmNo'); 

/** 2017-10-26 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtRptInvtPreConList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtRptInvtPreConDetail') ,'10','Y','LABEL','year'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtRptInvtPreConDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtItemList') ,'10','Y','LABEL','invtItem'); 

/** 2017-10-31 이근환 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptPminsAchList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptPminsAchDetailList') ,'10','Y','LABEL','pmInsAchMon'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptPminsAchList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptPminsAchDetailChart') ,'20','Y','LABEL','pmInsAchMon'); 

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptPmRatioList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptPmRatioDetailList') ,'10','Y','TAB','pmRatioMon'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptPmRatioList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptPmRatioDetailChart') ,'20','Y','TAB','pmRatio'); 

/** 2017-10-31 김정우 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'reqWorkResList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'reqWorkReswoDetail') ,'20','Y','LABEL','woNo');

/** 2017-11-02 김정우 */
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( next  value for sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoMonthWoList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'maWoResultPmSclMstrDetail') ,'140','Y','LABEL','woNo');

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( next  value for sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoWeekWoList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'maWoResultPmSclMstrDetail') ,'140','Y','LABEL','woNo');

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( next value for sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoMonthWoList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'maWoResultPmPrsMstrDetail') ,'150','Y','LABEL','woNo');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( next value for sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoWeekWoList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'maWoResultPmPrsMstrDetail') ,'150','Y','LABEL','woNo');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( next value for sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoMonthWoList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'maWoResultPmGnlMstrDetail') ,'160','Y','LABEL','woNo');
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( next value for sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoWeekWoList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'maWoResultPmGnlMstrDetail') ,'160','Y','LABEL','woNo');

/** 2017-11-02 이근환 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptMtbfmttrEquipList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptMtbfmttrEquipDetailList') ,'10','Y','TAB','monthlyMtbfMttr'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptMtbfmttrEquipList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptMtbfmttrEquipDetailChart') ,'20','Y','TAB','monthlyMtbfMttr'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptMtbfmttrLocList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptMtbfmttrLocDetailList') ,'10','Y','TAB','monthlyMtbfMttr'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptMtbfmttrLocList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptMtbfmttrLocDetailChart') ,'20','Y','TAB','monthlyMtbfMttr'); 

/** 2017-11-02 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES(NEXT VALUE FOR  SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'orgRptEmpMttrList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'orgRptEmpMttrDetailList') ,'10','Y','MENU','EmpMTTR'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES(NEXT VALUE FOR  SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'orgRptEmpMttrList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'orgRptEmpMttrDetailChart') ,'20','Y','MENU','EmpMTTR'); 

/** 2017-11-03 김영주 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'basicUnitAnalysisList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'basicUnitAnalysisDetailList') ,'10','Y','MENU','basicUnitAnalysis'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'basicUnitAnalysisList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'basicUnitAnalysisDetailChart') ,'20','Y','MENU','basicUnitAnalysis'); 

/** 2017-11-03 이근환 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmcPeriodList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmcPeriodDetail') ,'10','Y','LABEL','pmNo'); 

/** 2017-11-03 김영주 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmCheckDetail') ,(SELECT aa.page_id FROM TAPAGE aa 
WHERE aa.file_name = 'workPmCheckMonthlyUnitPriceList') ,'10','Y','LABEL','monthlyUnitPrice');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmCheckMonthlyUnitPriceList') ,(SELECT aa.page_id FROM TAPAGE aa 
WHERE aa.file_name = 'workPmCheckMonthlyUnitPriceDetail') ,'10','Y','LABEL','yyyymm');

 /**2017-11-03 김정우 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultMstrList') ,(SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultCmLocBaseMstrDetail') ,'150','Y','LABEL','woNo');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoCmResultMstrList') ,(SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultCmLocBaseMstrDetail') ,'20','Y','LABEL','woNo');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoMonthWoList') ,(SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultCmLocBaseMstrDetail') ,'170','Y','LABEL','woNo');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoWeekWoList') ,(SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultCmLocBaseMstrDetail') ,'170','Y','LABEL','woNo');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultCmLocBaseMstrDetail') ,(SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultCmLocBaseCraftList') ,'010','Y','TAB','maWoResultCraftList');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultCmLocBaseMstrDetail') ,(SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultCmLocBaseDocLibList') ,'020','Y','TAB','maDocLibList');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultCmLocBaseCraftList') ,(SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultCmLocBaseCraftDetail') ,'10','Y','LABEL','woCraft');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maWoResultCmLocBaseDocLibList') ,(SELECT aa.page_id FROM tapage aa 
WHERE aa.file_name = 'maWoResultCmLocBaseDocLibDetail') ,'10','Y','TAB','maDocLibDetail');

 /** 2017-11-06 김정우 */
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListTiMstrList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultCmRprMstrDetail') ,'10','Y','LABEL','woNo');


/** 2017-11-06 김영주 추가 */
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES(NEXT value FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assBasePointValDetail') ,(SELECT aa.page_id FROM TAPAGE aa 
WHERE aa.file_name = 'assBasePointValScript') ,'10','Y','LABEL','script');

/** 2017-11-07 이근환 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReadyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtDetail') ,'10','Y','TAB','invtlistNo');

/** 2017-11-08 이근환 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReadyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqWorkDetail') ,'10','Y','LABEL','woReqNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReadyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPtBuyReqHdrDetail') ,'10','Y','LABEL','reqNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReadyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partAdjStkTakeDetail') ,'10','Y','LABEL','takeNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReadyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultBmRplMstrDetail') ,'10','Y','LABEL','woNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReadyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultBmRprMstrDetail') ,'10','Y','LABEL','woNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReadyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultBmOilMstrDetail') ,'10','Y','LABEL','woNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReadyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultMstrDetail') ,'10','Y','LABEL','woNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReadyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultMonthMstrDetail') ,'10','Y','LABEL','woNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReadyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmInsMstrDetail') ,'10','Y','LABEL','woNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReadyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmRprMstrDetail') ,'10','Y','LABEL','woNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReadyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmRplMstrDetail') ,'10','Y','LABEL','woNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReadyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmCalEtcMstrDetail') ,'10','Y','LABEL','woNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReadyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmClnMstrDetail') ,'10','Y','LABEL','woNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReadyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmOilMstrDetail') ,'10','Y','LABEL','woNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReadyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultCmRprMstrDetail') ,'10','Y','LABEL','woNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReadyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultCmRplMstrDetail') ,'10','Y','LABEL','woNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReadyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultTrEleMstrDetail') ,'10','Y','LABEL','woNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReadyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmGmMstrDetail') ,'10','Y','LABEL','woNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReadyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultBmBaseMstrDetail') ,'10','Y','LABEL','woNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReadyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmGnlMstrDetail') ,'10','Y','LABEL','woNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReadyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmSclMstrDetail') ,'10','Y','LABEL','woNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReadyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmPrsMstrDetail') ,'10','Y','LABEL','woNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReadyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultCmLocBaseMstrDetail') ,'10','Y','LABEL','woNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReadyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workFmeaDetail') ,'10','Y','LABEL','number'); 

/** 2017-11-14 이근환 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'5','Y','LABEL','appByHist'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultBmBaseMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'5','Y','LABEL','appByHist'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partAdjStkTakeDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'5','Y','LABEL','appByHist'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPtBuyReqHdrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'5','Y','LABEL','appByHist'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqWorkDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'5','Y','LABEL','appByHist'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workFmeaDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'5','Y','LABEL','appByHist'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultBmOilMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'5','Y','LABEL','appByHist'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultBmRplMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'5','Y','LABEL','appByHist'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultBmRprMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'5','Y','LABEL','appByHist'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultCmLocBaseMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'5','Y','LABEL','appByHist'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultCmRplMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'5','Y','LABEL','appByHist'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultCmRprMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'5','Y','LABEL','appByHist'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultMonthMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'5','Y','LABEL','appByHist'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmCalEtcMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'5','Y','LABEL','appByHist'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmGnlMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'5','Y','LABEL','appByHist'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmPrsMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'5','Y','LABEL','appByHist'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmSclMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'5','Y','LABEL','appByHist'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmInsMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'5','Y','LABEL','appByHist'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmClnMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'5','Y','LABEL','appByHist'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmGmMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'5','Y','LABEL','appByHist'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmOilMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'5','Y','LABEL','appByHist'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmRplMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'5','Y','LABEL','appByHist'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmRprMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'5','Y','LABEL','appByHist'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'5','Y','LABEL','appByHist'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultTrEleMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'5','Y','LABEL','appByHist'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoReqDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'15','Y','LABEL','appByHist'); 

/** 2017-11-17 이근환 추가 */
INSERT INTO TAPGPAGE
   (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO)
 VALUES
   (NEXT value FOR SQAPGPAGE_ID,  (SELECT page_id FROM TAPAGE WHERE file_name='partAdjStkTakeList'),  (SELECT page_id FROM TAPAGE WHERE file_name='partAdjStkTakeDetail'), '10', 'Y', 'LABEL', 'reqNo');
   
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partAdjStkTakeItemList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partAdjStkTakeItemDetail') ,'10','Y','LABEL','ptNo'); 


INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
  VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'orgEmpTrainList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'orgEmpTrainDetail') ,'10','Y','LABEL','trainDesc');

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
  VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'orgEmpCertList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'orgEmpCertDetail') ,'10','Y','TAB','orgEmpCertDetail');
  
 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
  VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEmpDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'orgEmpTrainList') ,'100','Y','TAB','orgEmpTrainList');
  
  
 /** 2017-11-27 김정우 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR sqaPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMstrInsList') ,'35','Y','TAB','maEqMstrInsList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR sqaPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMachineMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMstrInsList') ,'35','Y','TAB','maEqMstrInsList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR sqaPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMoldMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMstrInsList') ,'35','Y','TAB','maEqMstrInsList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR sqaPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqToolMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMstrInsList') ,'35','N','TAB','maEqMstrInsList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR sqaPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqBuildingMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMstrInsList') ,'35','Y','TAB','maEqMstrInsList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR sqaPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqVehicleMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMstrInsList') ,'35','Y','TAB','maEqMstrInsList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR sqaPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqUtilityMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMstrInsList') ,'35','Y','TAB','maEqMstrInsList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR sqaPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqPartMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMstrInsList') ,'35','Y','TAB','maEqMstrInsList'); 

 /** 2017-11-27 김정우 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maUsrGrpDetail') ,(SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'maUsrGrpAndroidAuthList') ,'20','Y','TAB','maUsrGrpAndroidAuthList'); 

/** 2017-12-03 이근환 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetStdProductList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetStdProductDetail') ,'10','Y','LABEL','docCountrNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetStdCtctrList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetStdCtctrDetail') ,'10','Y','LABEL','docCountrNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetStdAssetList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetStdAssetDetail') ,'10','Y','LABEL','docCountrNo'); 

/** 2017-12-03 김영주 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListCinsResultMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListEquipList') ,'10','Y','LABEL','equipment'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListCinsResultMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPmMstrPointList') ,'20','Y','LABEL','maPmMstrPointList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPmMstrList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListCinsResultMstrDetail') ,'10','Y','LABEL','pmNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListCinsPlanMstrList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListCinsPlanMstrDetail') ,'10','Y','LABEL','date');


/** 2017-12-04 윤용철 추가 */    
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPtRecDetail') ,(SELECT aa.page_id FROM TAPAGE aa 
WHERE aa.file_name = 'maPtRecSerialList') ,'10','Y','LABEL','Serial');

/** 2017-12-06 이근환 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiCInsList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiCInsDetail') ,'10','Y','MENU','CINSPECTION'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiCInsDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiCInsPointList') ,'20','Y','LABEL','pm'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiCInsPointList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiCInsPointDetail') ,'10','Y','LABEL','pm'); 

/** 2017-12-08 이근환 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBmLnChart') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBmLnChartDetail') ,'10','Y','LABEL','location'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBmCtgChart') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBmCtgChartDetail') ,'10','Y','',''); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBmGwChart') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBmGwChartDetail') ,'10','Y','',''); 

/** 2017-12-11 김영주 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultCompTerminalExList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultCompTerminalExDetail') ,'10','Y','LABEL','terminalNo');

/** 2017-12-11 이근환 추가 */
UPDATE TAPGPAGE SET key_no='lnWrkListNo' 
WHERE page_id=(SELECT page_id FROM TAPAGE WHERE file_name='maLineTimeList');

/** 2017-12-12 이근환 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'energyUsePreConMonthList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'energyUsePreConMonthDetailList') ,'10','Y','LABEL','monthly'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'energyUsePreConMonthList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'energyUsePreConMonthDetailChart') ,'20','Y','LABEL','monthly'); 

/** 2017-12-15 이근환 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMstrPmwRplList') ,'37','Y','TAB','maEqMstrPmwRplList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMachineMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMstrPmwRplList') ,'37','Y','TAB','maEqMstrPmwRplList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMoldMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMstrPmwRplList') ,'37','Y','TAB','maEqMstrPmwRplList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqToolMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMstrPmwRplList') ,'37','Y','TAB','maEqMstrPmwRplList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqBuildingMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMstrPmwRplList') ,'37','Y','TAB','maEqMstrPmwRplList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqVehicleMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMstrPmwRplList') ,'37','Y','TAB','maEqMstrPmwRplList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqUtilityMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMstrPmwRplList') ,'37','Y','TAB','maEqMstrPmwRplList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqPartMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMstrPmwRplList') ,'37','Y','TAB','maEqMstrPmwRplList'); 

UPDATE TAPGPAGE SET is_use='Y' WHERE page_id=(SELECT page_id FROM TAPAGE WHERE file_name='maLineTimeDetail');

/** 2017-12-20 김영주 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPmTrendList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPmTrendDetailChart') ,'10','Y','LABEL','checkPoint'); 

/** 2017-12-20 이근환 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPgGrdColMngList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPgGrdColMngDetail') ,'10','Y','TAB','pgGridCol'); 

/** 2017-12-22 김영주 추가*/
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPgFieldMngList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPgFieldMngDetail') ,'10','Y','TAB','maPgMngDetail'); 

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'woPlanList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'woPlanDetail') ,'10','Y','LABEL','woNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'woPlanDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'10','Y','LABEL','appByHist'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'woPlanCraftList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'woPlanCraftDetail') ,'10','Y','LABEL','woCraft');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'woPlanDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'woPlanCraftList') ,'20','Y','TAB','maWoResultCraftList');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'woPlanPartList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'woPlanPartDetail') ,'10','Y','LABEL','parts'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'woPlanDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'woPlanPartList') ,'30','Y','TAB','maWoResultPartList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'woPlanDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maDocLibList') ,'40','Y','TAB','maDocLibList'); 


/** 2017-12-27 김정우 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmInsApprList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmInsApprDetail') ,'10','Y','LABEL','title'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR sqaPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmInsApprDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmInsApprWorkList') ,'10','Y','TAB','workCalPmInsApprWorkList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR sqaPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmInsApprDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'10','Y','LABEL','appByHist'); 

/** 2017-12-27 이근환 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultProgramOnlinehelpList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultProgramOnlinehelpDetail') ,'10','Y','LABEL','page'); 

/** 2017-12-28 이근환 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultProgramOnlinehelpDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultProgramOnlinehelpDocLibList') ,'10','Y','TAB','maDocLibList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultProgramOnlinehelpDocLibList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultProgramOnlinehelpDocLibDetail') ,'10','Y','TAB','maDocLibDetail'); 

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appOnlinehelpDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appOnlinehelpDocLibList') ,'10','Y','TAB','maDocLibList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appOnlinehelpDocLibList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appOnlinehelpDocLibDetail') ,'10','Y','TAB','maDocLibDetail'); 

/** 2018-01-04 이근환 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultCompTracelogList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultCompTracelogDetail') ,'10','Y','LABEL','docCountrNo'); 

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appTracelogList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appTracelogDetail') ,'10','Y','LABEL','docCountrNo'); 

/** 2018-01-04 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultProgramUploadDataList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultProgramUploadDataDetail') ,'10','Y','LABEL','excelTypeNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultProgramUploadDataDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultProgramUploadDataColList') ,'10','Y','LABEL','column'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultProgramUploadDataColList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultProgramUploadDataColDetail') ,'10','Y','LABEL','column'); 

/** 2018-01-05 이근환 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrExldataList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrExldataDetail') ,'10','Y','LABEL','docCountrNo'); 

/** 2018-01-29 이근환 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partIssEmgReqList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partIssEmgReqDetail') ,'10','Y','LABEL','issNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partIssEmgReqDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partIssEmgReqPartsList') ,'10','Y','LABEL','parts'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT value FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partIssEmgReqPartsList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partIssEmgReqPartsDetail') ,'10','Y','LABEL','partsNo'); 

/** 2018-01-31 김영주 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptMaintRateList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptMaintRateDetailByPartList') ,'10','Y','LABEL','maintRateByPart'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptMaintRateList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptMaintRateDetailByTypeList') ,'20','Y','LABEL','maintRateByType'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptMaintRateList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptMaintRateDetailDayChart') ,'30','Y','LABEL','dailyMaintRate'); 

/** 2018-02-02 김영주 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqRptWoReqRateList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqRptWoReqRateDetailList') ,'20','Y','LABEL','comWoReqCnt'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqRptWoReqRateList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqRptWoReqRateDetailChart') ,'10','Y','LABEL','comWoReqCnt'); 

/** 2018-02-05 김영주 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiPointDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiPointDocLibList') ,'10','Y','TAB','maDocLibList');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiPointDocLibList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiPointDocLibDetail') ,'10','Y','TAB','maDocLibDetail');

/** 2018-02-07 김영주 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES(NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiCInsPointDetail') ,(SELECT aa.page_id FROM TAPAGE aa 
WHERE aa.file_name = 'workPmiPointDocLibList') ,'10','Y','TAB','maDocLibList');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES(NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiCInsPointDocLibList') ,(SELECT aa.page_id FROM TAPAGE aa 
WHERE aa.file_name = 'workPmiCInsPointDocLibDetail') ,'10','Y','TAB','maDocLibDetail');
UPDATE TAPGPAGE SET c_page_id = (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiCInsPointDocLibList') WHERE page_id = (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiCInsPointDetail');

/** 2018-02-09 김영주 추가 */ 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListPointDetail') ,(SELECT aa.page_id FROM TAPAGE aa 
WHERE aa.file_name = 'workPmiPInsPointDocLibList') ,'10','Y','TAB','maDocLibList');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiPInsPointDocLibList') ,(SELECT aa.page_id FROM TAPAGE aa 
WHERE aa.file_name = 'workPmiPInsPointDocLibDetail') ,'10','Y','TAB','maDocLibDetail');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiDInsPointDetail') ,(SELECT aa.page_id FROM TAPAGE aa 
WHERE aa.file_name = 'workPmiDInsPointDocLibList') ,'10','Y','TAB','maDocLibList');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiDInsPointDocLibList') ,(SELECT aa.page_id FROM TAPAGE aa 
WHERE aa.file_name = 'workPmiDInsPointDocLibDetail') ,'10','Y','TAB','maDocLibDetail');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiPointDetail') ,(SELECT aa.page_id FROM TAPAGE aa 
WHERE aa.file_name = 'workPmiRInsPointDocLibList') ,'10','Y','TAB','maDocLibList');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiRInsPointDocLibList') ,(SELECT aa.page_id FROM TAPAGE aa 
WHERE aa.file_name = 'workPmiRInsPointDocLibDetail') ,'10','Y','TAB','maDocLibDetail');
UPDATE TAPGPAGE SET page_id = (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultPmInsPointDetail') 
WHERE page_id = (SELECT page_id FROM TAPAGE WHERE file_name = 'workPmiPointDetail')
  AND c_page_id = (SELECT page_id FROM TAPAGE WHERE file_name = 'workPmiPointDocLibList');

/** 2018-02-14 이근환 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( next value for SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReadyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partIssEmgReqDetail') ,'10','Y','LABEL','issNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( next value for SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partIssEmgReqDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'20','Y','LABEL','appByHist'); 

/** 2018-02-18 이근환 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( next value for SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReadyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'woPlanDetail') ,'10','Y','LABEL','woNo'); 

/** 2018-02-20 양소영 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'persApprHistList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqWorkDetail') ,'10','Y','LABEL','woReqNo');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'persApprHistList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoDailyDetail') ,'10','Y','LABEL','date'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'persApprHistList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtPrcDetail') ,'10','Y','TAB','invtPrcDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'persApprHistList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtDetail') ,'10','Y','TAB','invtlistNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'persApprHistList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPtBuyReqHdrDetail') ,'10','Y','LABEL','reqNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'persApprHistList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partAdjStkTakeDetail') ,'10','Y','LABEL','takeNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'persApprHistList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workFmeaDetail') ,'10','Y','LABEL','number'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'persApprHistList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partIssEmgReqDetail') ,'10','Y','LABEL','issNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'persApprHistList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'woPlanDetail') ,'10','Y','LABEL','woNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'persApprHistList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultMstrDetail') ,'10','Y','LABEL','woNo'); 

/** 2018-02-21 김영주 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) values(NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'assetRptEqPartPreConList') ,(select aa.page_id from tapage aa where aa.file_name = 'assetRptEqPartPreConDetailPartList') ,'10','Y','LABEL','eqAsmbPart'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) values(NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'assetRptEqPartPreConDetailPartList') ,(select aa.page_id from tapage aa where aa.file_name = 'assetRptEqPartPreConDetailStockList') ,'20','Y','LABEL','stockPreCon');

/** 2018-02-21 양소영 추가 */
INSERT INTO TAPGPAGE 
SELECT NEXT VALUE FOR sqapgpage_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'persApprHistList'), c_page_id, ord_no, is_use, key_type, key_no, remark, file_name, c_file_name
FROM TAPGPAGE WHERE page_id =  (SELECT page_id FROM TAPAGE WHERE file_name = 'appReadyList')
AND c_page_id NOT IN (SELECT c_page_id FROM TAPGPAGE WHERE page_id = (SELECT page_id FROM TAPAGE WHERE file_name = 'persApprHistList'))


/*2018-02-27 김영주*/
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetListITList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetListITDetail') ,'10','Y','LABEL','ITEqNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetListITDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetListITSWList') ,'10','Y','LABEL','sw'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetListITSWList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetListITSWDetail') ,'10','Y','LABEL','sw'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetListITDetail') ,(SELECT aa.page_id FROM TAPAGE aa 
WHERE aa.file_name = 'assetListItDocLibList') ,'20','Y','TAB','maDocLibList');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetListItDocLibList') ,(SELECT aa.page_id FROM TAPAGE aa 
WHERE aa.file_name = 'assetListItDocLibDetail') ,'10','Y','TAB','maDocLibDetail');

/*2018-03-02 김영주*/
DELETE FROM TAPGPAGE WHERE page_id = (SELECT page_id FROM TAPAGE WHERE file_name = 'assetRptEqPartPreConDetailPartList');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) values(NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'assetRptEqPartPreConList') ,(select aa.page_id from tapage aa where aa.file_name = 'assetRptEqPartPreConDetailStockList') ,'20','Y','LABEL','stockPreCon');

/**  2018-03-07 매일유업 반영 */

/** 2018-03-09 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'docNoticeList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'docNoticeDetail') ,'10','Y','LABEL','number'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'docNoticeDeptList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'docNoticeDeptDetail') ,'10','Y','LABEL','dept'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'docNoticeTargetList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'docNoticeTargetDetail') ,'10','Y','LABEL','emp'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'docNoticeDocLibList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'docNoticeDocLibDetail') ,'10','Y','LABEL','docNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'docNoticeDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'docNoticeDeptList') ,'10','Y','TAB','NOTICEDEPT'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'docNoticeDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'docNoticeTargetList') ,'20','Y','TAB','NOTICETARGET'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'docNoticeDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'docNoticeDocLibList') ,'30','Y','TAB','maDocLibList'); 

/**  2018-03-13 매일유업 반영 */

/** 2018-03-15 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'docNoticeCheckList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'docNoticeCheckDetail') ,'10','Y','LABEL','number'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'docNoticeCheckDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'docNoticeCheckDocLibList') ,'30','Y','TAB','maDocLibList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'docNoticeCheckDocLibList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'docNoticeCheckDocLibDetail') ,'10','Y','LABEL','docNo'); 

/** 2018-03-19 양소영 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrPtWhList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrPtWhDetail') ,'10','Y','LABEL','docCountrNo');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrPtWhDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrPtWhEmpList') ,'20','Y','LABEL','manager');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrPtWhEmpList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrPtWhEmpDetail') ,'10','Y','LABEL','manager');

/** 2018-03-20 이근환 */
UPDATE tapgpage set ord_no='30' 
WHERE page_id=(SELECT page_id FROM tapage WHERE file_name='maWoDailyDetail') AND c_page_id=(SELECT page_id FROM tapage WHERE file_name='maAppPrcList');
UPDATE tapgpage set ord_no='10' 
WHERE page_id=(SELECT page_id FROM tapage WHERE file_name='maWoDailyDetail') AND c_page_id=(SELECT page_id FROM tapage WHERE file_name='maWoDailyWoList');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoDailyDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoDailyPmiList') ,'20','Y','TAB','maWoDailyPmiList'); 

/** 2018-03-21 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptPminsDeptAchList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptPminsDeptAchDetailList') ,'10','Y','LABEL','pmInsDeptAchMon'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptPminsDeptAchList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptPminsDeptAchDetailChart') ,'20','Y','LABEL','pmInsDeptAchMon'); 

/** 2018-03-23 이근환 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'docManualList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'docManualDetail') ,'10','Y','LABEL','docCountrNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'docManualDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'docManualDocLibList') ,'10','Y','TAB','maDocLibList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'docManualDocLibList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'docManualDocLibDetail') ,'10','Y','TAB','maDocLibDetail'); 

/**  2018-03-28 매일유업 반영 */

/** 2018-03-30 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) 
VALUES(NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptLccEquipList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptLccEquipWorkTimeDetailChart') ,'30','Y','LABEL','monthly','assetRptLccEquipList','assetRptLccEquipWorkTimeDetailChart'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partRptOrgPtUseHistList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partRptOrgPtUseHistDetailList') ,'10','Y','LABEL','monthly','partRptOrgPtUseHistList','partRptOrgPtUseHistDetailList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partRptOrgPtUseHistList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partRptOrgPtUseHistDetailChart') ,'20','Y','LABEL','monthly','partRptOrgPtUseHistList','partRptOrgPtUseHistDetailChart'); 

/** 2018-04-03 이지수 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES(NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptWorkHistList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptWorkHistDetail') ,'10','Y','LABEL','equipDesc'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES(NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptWorkHistDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptWorkHistLibList') ,'10','Y','TAB','maDocLibList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES(NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptWorkHistLibList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptWorkHistLibDetail') ,'10','Y','TAB','maDocLibDetail'); 

/**  2018-04-04 매일유업 반영 */

/** 2018-04-10 양소영 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptLccLocList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptLccLocWorkTimeDetailChart') ,'30','Y','LABEL','monthly', 'assetRptLccLocList', 'assetRptLccLocWorkTimeDetailChart'); 

/** 2018-04-10 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMstrPmiList') ,'35','Y','TAB','maEqMstrPmiList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMachineMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMstrPmiList') ,'35','Y','TAB','maEqMstrPmiList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMoldMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMstrPmiList') ,'35','Y','TAB','maEqMstrPmiList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqToolMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMstrPmiList') ,'35','N','TAB','maEqMstrPmiList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqBuildingMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMstrPmiList') ,'35','N','TAB','maEqMstrPmiList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqVehicleMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMstrPmiList') ,'35','Y','TAB','maEqMstrPmiList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqUtilityMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMstrPmiList') ,'35','Y','TAB','maEqMstrPmiList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqPartMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMstrPmiList') ,'35','Y','TAB','maEqMstrPmiList'); 

/**  2018-04-11 매일유업 반영 */

/** 2018-04-11 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partRptOrgPtUseHistList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partRptOrgPtUseHistDetailList') ,'10','Y','LABEL','monthly','partRptOrgPtUseHistList','partRptOrgPtUseHistDetailList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partRptOrgPtUseHistList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partRptOrgPtUseHistDetailChart') ,'20','Y','LABEL','monthly','partRptOrgPtUseHistList','partRptOrgPtUseHistDetailChart'); 

/** 2018-04-12 양소영 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptPminsOrgAchList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptPminsOrgAchDetailList') ,'10','Y','LABEL','pmInsDeptAchMon', 'workRptPminsOrgAchList', 'workRptPminsOrgAchDetailList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptPminsOrgAchList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptPminsOrgAchDetailChart') ,'20','Y','LABEL','pmInsDeptAchMon', 'workRptPminsOrgAchList', 'workRptPminsOrgAchDetailChart'); 

/** 2018-04-12 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptOrgBdList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptOrgBdDetailList') ,'10','Y','LABEL','monthly','workRptOrgBdList','workRptOrgBdDetailList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptOrgBdList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptOrgBdDetailChart') ,'20','Y','LABEL','monthly','workRptOrgBdList','workRptOrgBdDetailChart'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptOrgBdList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptOrgBdWorkTimeDetailChart') ,'30','Y','LABEL','monthly','workRptOrgBdList','workRptOrgBdWorkTimeDetailChart'); 

/** 2018-04-16 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptOrgMtbfmttrList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptOrgMtbfmttrDetailList') ,'10','Y','TAB','monthlyMtbfMttr','workRptOrgMtbfmttrList','workRptOrgMtbfmttrDetailList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptOrgMtbfmttrList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptOrgMtbfmttrDetailChart') ,'20','Y','TAB','monthlyMtbfMttr','workRptOrgMtbfmttrList','workRptOrgMtbfmttrDetailChart'); 

/** 2018-04-17 양소영 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partRptPtUseHistList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partRptPtUseHistDetailList') ,'10','Y','LABEL','parts', 'partRptPtUseHistList', 'partRptPtUseHistDetailList');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partRptPtUseHistList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partRptPtMstrEqPartList') ,'20','Y','TAB','maPtMstrEqPartList', 'partRptPtUseHistList', 'partRptPtMstrEqPartList');

/**  2018-04-18 매일유업 반영 */
/**  2018-04-25 매일유업 반영 */

/** 2018-04-30 이근환 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maHelpDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maHelpDocLibList') ,'10','Y','TAB','maDocLibList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maHelpDocLibList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maHelpDocLibDetail') ,'10','Y','LABEL','docNo'); 

/** 2018-05-02 이근환 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtPhaseDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtPrcDocLibList') ,'10','Y','TAB','maDocLibList'); 

/**  2018-05-02 매일유업 반영 */
/**  2018-05-09 매일유업 반영 */

/** 2018-05-09 이근환 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetListAssList') ,'8000','N','TAB','assetListAssList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetListAssList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assAssetDetail') ,'10','N','LABEL','docCountrNo'); 

/** 2018-05-14 이근환 */
UPDATE tapgpage set c_page_id=(SELECT page_id FROM tapage WHERE file_name='assetListAssDetail')
WHERE page_id=(SELECT page_id FROM tapage WHERE file_name='assetListAssList');

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetListAssDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assAssetScoreList') ,'10','Y','TAB','assAssetScoreList'); 

/** 2018-05-24 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPlanApprList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPlanApprDetail') ,'10','Y','LABEL','title','workPlanApprList','workPlanApprDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPlanApprDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'10','Y','LABEL','appByHist','workPlanApprDetail','maAppPrcList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPlanApprDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPlanApprWorkList') ,'20','Y','LABEL','pmwork','workPlanApprDetail','workPlanApprWorkList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReadyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPlanApprDetail') ,'10','Y','LABEL','title'); INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReadyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmInsApprDetail') ,'10','Y','LABEL','title'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'persApprHistList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPlanApprDetail') ,'10','Y','LABEL','title'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'persApprHistList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmInsApprDetail') ,'10','Y','LABEL','title'); 

/**  2018-05-25 매일유업 반영 */

/** 2018-05-25 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) 
VALUES( NEXT VALUE FOR sqapgpage_id
, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPlanApprWorkList') 
, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'woPlanDetail') 
,'10','Y','LABEL','woNo','workPlanApprWorkList','woPlanDetail');

/** 2018-05-29 김정우 */

 INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values(next value for  sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maWoResultMstrList') ,(select aa.page_id from tapage aa where aa.file_name = 'maWoResultBmBaseMstrDetail') ,'140','Y','LABEL','woNo');

/**  2018-06-01 매일유업 반영 */

/** 2018-06-07 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultPgmErrorList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultPgmErrorDetail') ,'10','Y','LABEL','number','consultPgmErrorList','consultPgmErrorDetail'); 

/** 2018-06-08 이지수 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetListGnMstrList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetListGnMstrDetail') ,'10','Y','LABEL','equipDesc'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetListGnMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMstrAssetList') ,'10','Y','TAB','maEqMstrAssetList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetListGnMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetListGnDocLibList') ,'20','Y','TAB','maDocLibList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetListGnDocLibList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetListGnDocLibDetail') ,'10','Y','LABEL','docNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMstrList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetListGnMstrDetail') ,'10','Y','LABEL','equipDesc'); 

/** 2018-06-08 양소영 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultCompConfigList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultCompConfigDetail') ,'10','Y','TAB','maConfigDetail', 'consultCompConfigList', 'consultCompConfigDetail'); 

/** 2018-06-14 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetListMstrToolsList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqToolMstrDetail') ,'10','Y','LABEL','eqTool','assetListMstrToolsList','maEqToolMstrDetail'); 

/**  2018-06-19 매일유업 반영 */
/**  2018-06-20 매일유업 반영 */
/** 2018-06-22 김정우 */
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, remark, file_name, c_file_name) VALUES
( NEXT VALUE FOR sqaPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultPgmDashboardList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultPgmDashboardDetail') ,'10','Y','LABEL','number', '', 'consultPgmDashboardList', 'consultPgmDashboardDetail'); 

/**  2018-06-22 매일유업 반영 */

/**  2018-06-22 양소영 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqCatalogDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqCtgSpecList') ,'30','Y','TAB','maEqCtgSpecList', 'maEqCatalogDetail', 'maEqCtgSpecList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqCtgSpecList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqCtgSpecDetail') ,'10','Y','TAB','maEqMstrSpecList', 'maEqCtgSpecList', 'maEqCtgSpecDetail'); 

/**  2018-06-27 매일유업 반영 */

/** 2018-06-27 김정우 */
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, remark, file_name, c_file_name) VALUES
(next value for SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultBmRprMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultWoImageList') ,'30','Y','TAB','maWoResultWoImageList', '', 'maWoResultBmRprMstrDetail', 'maWoResultWoImageList'); 

/** 2018-06-28 이지수 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqWorkList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqInvWorkDetail') ,'10','Y','LABEL','woReqNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoReqList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqInvRecWorkDetail') ,'10','Y','LABEL','woReqNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqInvRecWorkDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqInvRecWorkResList') ,'10','Y','TAB','maWoReqResList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqInvRecWorkResList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqInvRecWorkResDetail') ,'10','Y','TAB','maWoReqResList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqInvRecWorkDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'20','Y','LABEL','appByHist'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqInvRecWorkDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqInvRecWorkDocLibList') ,'30','Y','TAB','maDocLibList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqInvRecWorkDocLibList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqInvRecWorkDocLibDetail') ,'10','Y','TAB','maDocLibDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqInvWorkDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'10','Y','LABEL','appByHist'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqInvWorkDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqInvWorkResList') ,'20','Y','TAB','reqWorkResList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqInvWorkResList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqInvWorkResDetail') ,'10','Y','LABEL','invtlistNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqInvWorkDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqInvWorkDocLibList') ,'30','Y','TAB','maDocLibList');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqInvWorkDocLibList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqInvWorkDocLibDetail') ,'10','Y','TAB','maDocLibDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqInvRecWorkResList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtDetail') ,'10','Y','TAB','invtlistNo'); 

/** 2018-06-28 이근환 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrIntfList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrIntfDetail') ,'10','Y','LABEL','docCountrNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrIntfDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrIntfLogList') ,'10','Y','TAB','mgrIntfLogList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrIntfLogList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrIntfLogDetail') ,'10','Y','LABEL','exeTime'); 

/** 2018-06-29 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmInsApprDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmInsApprRsltList') ,'20','Y','TAB','workCalPmInsApprRsltList');

/**  2018-07-02 매일유업 반영 */

/** 2018-07-03 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtEquipList') ,'30','Y','TAB','INVTEQUIP','invtDetail','invtEquipList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtItemsList') ,'40','Y','TAB','INVTITEMS','invtDetail','invtItemsList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtItemsList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtItemsDetail') ,'10','Y','TAB','INVTITEMS','invtItemsList','invtItemsDetail'); 
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, REMARK, file_name, c_file_name)
SELECT NEXT VALUE FOR sqapgpage_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'invtEquipList'), c_page_id, ord_no, is_use, key_type, key_no, REMARK, 'invtEquipList', c_file_name 
FROM TAPGPAGE WHERE page_id = (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqMstrList');
UPDATE TAPGPAGE SET key_type = 'TAB' , key_no = 'INVTITEMS' WHERE file_name = 'invtItemsList';

/**  2018-07-03 매일유업 반영 */

/** 2018-7-03 16:27 */

INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, remark, file_name, c_file_name) VALUES
( next value for SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultMstrDetail') ,
(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultWoImageList') ,'60','Y','TAB','maWoResultWoImageList', '', 'maWoResultMstrDetail', 'maWoResultWoImageList'); 

INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, remark, file_name, c_file_name) VALUES
( next value for SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultMonthMstrDetail') ,
(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultWoImageList') ,'70','Y','TAB','maWoResultWoImageList', '', 'maWoResultMonthMstrDetail', 'maWoResultWoImageList'); 

INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, remark, file_name, c_file_name) VALUES
( next value for SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmInsMstrDetail') ,
(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultWoImageList') ,'60','Y','TAB','maWoResultWoImageList', '', 'maWoResultPmInsMstrDetail', 'maWoResultWoImageList'); 

INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, remark, file_name, c_file_name) VALUES
( next value for SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmRprMstrDetail') ,
(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultWoImageList') ,'60','Y','TAB','maWoResultWoImageList', '', 'maWoResultPmRprMstrDetail', 'maWoResultWoImageList'); 

INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, remark, file_name, c_file_name) VALUES
( next value for SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmRplMstrDetail') ,
(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultWoImageList') ,'60','Y','TAB','maWoResultWoImageList', '', 'maWoResultPmRplMstrDetail', 'maWoResultWoImageList'); 

INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, remark, file_name, c_file_name) VALUES
( next value for SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmCalEtcMstrDetail') ,
(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultWoImageList') ,'60','Y','TAB','maWoResultWoImageList', '', 'maWoResultPmCalEtcMstrDetail', 'maWoResultWoImageList'); 

INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, remark, file_name, c_file_name) VALUES
( next value for SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmClnMstrDetail') ,
(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultWoImageList') ,'60','Y','TAB','maWoResultWoImageList', '', 'maWoResultPmClnMstrDetail', 'maWoResultWoImageList'); 

INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, remark, file_name, c_file_name) VALUES
( next value for SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmOilMstrDetail') ,
(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultWoImageList') ,'60','Y','TAB','maWoResultWoImageList', '', 'maWoResultPmOilMstrDetail', 'maWoResultWoImageList'); 

INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, remark, file_name, c_file_name) VALUES
( next value for SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultBmRplMstrDetail') ,
(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultWoImageList') ,'60','Y','TAB','maWoResultWoImageList', '', 'maWoResultBmRplMstrDetail', 'maWoResultWoImageList'); 

INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, remark, file_name, c_file_name) VALUES
( next value for SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultBmOilMstrDetail') ,
(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultWoImageList') ,'60','Y','TAB','maWoResultWoImageList', '', 'maWoResultBmOilMstrDetail', 'maWoResultWoImageList'); 

INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, remark, file_name, c_file_name) VALUES
( next value for SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultCmRprMstrDetail') ,
(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultWoImageList') ,'60','Y','TAB','maWoResultWoImageList', '', 'maWoResultCmRprMstrDetail', 'maWoResultWoImageList'); 

INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, remark, file_name, c_file_name) VALUES
( next value for SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultCmRplMstrDetail') ,
(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultWoImageList') ,'60','Y','TAB','maWoResultWoImageList', '', 'maWoResultCmRplMstrDetail', 'maWoResultWoImageList'); 

INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, remark, file_name, c_file_name) VALUES
( next value for SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultTrEleMstrDetail') ,
(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultWoImageList') ,'60','Y','TAB','maWoResultWoImageList', '', 'maWoResultTrEleMstrDetail', 'maWoResultWoImageList'); 

INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, remark, file_name, c_file_name) VALUES
( next value for SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmGmMstrDetail') ,
(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultWoImageList') ,'60','Y','TAB','maWoResultWoImageList', '', 'maWoResultPmGmMstrDetail', 'maWoResultWoImageList'); 


INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, remark, file_name, c_file_name) VALUES
( next value for SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultBmBaseMstrDetail') ,
(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultWoImageList') ,'10','Y','TAB','maWoResultWoImageList', '', 'maWoResultBmBaseMstrDetail', 'maWoResultWoImageList'); 

INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, remark, file_name, c_file_name) VALUES
( next value for SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmGnlMstrDetail') ,
(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultWoImageList') ,'60','Y','TAB','maWoResultWoImageList', '', 'maWoResultPmGnlMstrDetail', 'maWoResultWoImageList'); 

INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, remark, file_name, c_file_name) VALUES
( next value for SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmSclMstrDetail') ,
(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultWoImageList') ,'60','Y','TAB','maWoResultWoImageList', '', 'maWoResultPmSclMstrDetail', 'maWoResultWoImageList'); 

INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, remark, file_name, c_file_name) VALUES
( next value for SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmPrsMstrDetail') ,
(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultWoImageList') ,'60','Y','TAB','maWoResultWoImageList', '', 'maWoResultPmPrsMstrDetail', 'maWoResultWoImageList'); 

INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, remark, file_name, c_file_name) VALUES
( next value for SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultCmLocBaseMstrDetail') ,
(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultWoImageList') ,'60','Y','TAB','maWoResultWoImageList', '', 'maWoResultCmLocBaseMstrDetail', 'maWoResultWoImageList'); 

/** 2018-07-06 양소영 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMstrList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetListITDetail') ,'10','Y','LABEL','equipDesc', 'maEqMstrList', 'assetListITDetail'); 

/** 2018-07-09 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultPgmLinkedFuncList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultPgmLinkedFuncDetail') ,'10','Y','LABEL','docCountrNo','consultPgmLinkedFuncList','consultPgmLinkedFuncDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPgMngDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultPgmPgLinkedFuncList') ,'50','Y','LABEL','linkedFuncDesc','consultPgmPgLinkedFuncList','consultPgmPgLinkedFuncDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultPgmPgLinkedFuncList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultPgmPgLinkedFuncDetail') ,'10','Y','LABEL','linkedFuncDesc','consultPgmPgLinkedFuncList','consultPgmPgLinkedFuncDetail'); 

/**  2018-07-12 매일유업 반영 */


/** 2018-07-13 김정우 */
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, remark, file_name, c_file_name) VALUES
( next value for sqaPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPgMngFieldDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPgMngFieldValueList') ,'66','Y','TAB','maPgMngFieldValueList', '', 'maPgMngFieldDetail', 'maPgMngFieldValueList'); 
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, remark, file_name, c_file_name) VALUES
( next value for sqaPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPgMngFieldValueList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPgMngFieldValueDetail') ,'10','Y','TAB','maPgMngFieldValueDetail', '', 'maPgMngFieldValueList', 'maPgMngFieldValueDetail'); 


/**  2018-07-13 DREAM 반영 */

/** 2018-07-15 이지수 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) 
VALUES( next value for sqaPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListRInsDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListMsTimeList') ,'30','Y','TAB','measureTime','workPmListRInsDetail','workPmListMsTimeList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) 
VALUES( next value for sqaPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListMsTimeList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListMsTimeDetail') ,'10','Y','LABEL','workTime','workPmListMsTimeList','workPmListMsTimeDetail'); 

/**  2018-07-19 매일유업 반영 */
/**  2018-07-19 국도 반영 */

/** 2018-07-20 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoReqDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqWoInvtRsltList') ,'10','Y','TAB','REQINVT','maWoReqDetail','reqInvtRsltList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoReqDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqWoRsltList') ,'20','Y','TAB','REQWO','maWoReqDetail','reqWoRsltList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoReqDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqWoPlanRsltList') ,'30','Y','TAB','REQWOPLAN','maWoReqDetail','reqWoPlanRsltList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqInvRecWorkDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqWoInvtRsltList') ,'10','Y','TAB','REQINVT','reqInvRecWorkDetail','reqInvtRsltList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqInvRecWorkDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqWoRsltList') ,'20','Y','TAB','REQWO','reqInvRecWorkDetail','reqWoRsltList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqInvRecWorkDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqWoPlanRsltList') ,'30','Y','TAB','REQWOPLAN','reqInvRecWorkDetail','reqWoPlanRsltList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqWoInvtRsltList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtDetail') ,'10','Y','TAB','invtlistNo','reqWoInvtRsltList','invtDetail'); 
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, REMARK, file_name, c_file_name)
SELECT NEXT VALUE FOR sqapgpage_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'reqWoRsltList'), c_page_id, ord_no, is_use, key_type, key_no, REMARK, 'reqWoRsltList', c_file_name 
FROM TAPGPAGE WHERE page_id = (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoResultMstrList');

/** 2018-07-20 이지수 */
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, REMARK, file_name, c_file_name)
SELECT NEXT VALUE FOR sqapgpage_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoPmwOvhResultMstrList'), c_page_id, ord_no, is_use, key_type, key_no, REMARK, 'maWoPmwOvhResultMstrList', c_file_name 
FROM TAPGPAGE WHERE page_id = (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoPmwResultMstrList');

/** 2018-07-24 국도 반영 */
/** 2018-07-24 DREAM 반영 */
/** 2018-07-26 국도 반영 */
/** 2018-07-27 매일유업 반영 */

/** 2018-07-30 김영주 */
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, REMARK, file_name, c_file_name)
SELECT NEXT VALUE FOR sqapgpage_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workCalUnitedMonthList'), c_page_id, ord_no, is_use, key_type, key_no, REMARK, 'workCalUnitedMonthList', c_file_name 
FROM TAPGPAGE WHERE page_id = (SELECT page_id FROM TAPAGE WHERE file_name = 'maWoMonthWoList');
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, REMARK, file_name, c_file_name)
SELECT NEXT VALUE FOR sqapgpage_id, (SELECT page_id FROM TAPAGE WHERE file_name = 'workCalUnitedMonthList'), c_page_id, ord_no, is_use, key_type, key_no, REMARK, 'workCalUnitedMonthList', c_file_name 
FROM TAPGPAGE WHERE page_id = (SELECT page_id FROM TAPAGE WHERE file_name = 'workPmiList');

/** 2018-07-30 양소영 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReadyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMstrDetail') ,'10','Y','LABEL','equipment', 'appReadyList', 'maEqMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'persApprHistList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqMstrDetail') ,'10','Y','LABEL','equipment', 'persApprHistList', 'maEqMstrDetail'); 

/** 2018-07-31 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetStdProductDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetStdProductEquipList') ,'10','Y','TAB','PRDEQUIP','assetStdProductDetail','assetStdProductEquipList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetStdProductEquipList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetStdProductEquipDetail') ,'10','Y','TAB','INVTEQUIP','assetStdProductEquipList','assetStdProductEquipDetail'); 


/** 2018-08-01 김정우 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( next value for sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maPmMstrPointList') ,(select aa.page_id from tapage aa where aa.file_name = 'maPmMstrPointValueDetail') ,'20','Y','LABEL','maPmMstrPointList'); 


/** 2018-08-02 양소영 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrMessageTransList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrMessageTransDetail') ,'10','Y','LABEL','docCountrNo', 'mgrMessageTransList', 'mgrMessageTransDetail'); 

/** 2018-08-03 김영주 추가  */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assAssetTlList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assAssetTlDetail') ,'10','Y','LABEL','docCountrNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assAssetTlDetail') ,(SELECT aa.page_id FROM TAPAGE aa 
WHERE aa.file_name = 'assAssetTlScoreList') ,'10','Y','TAB','assAssetScoreList');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assAssetTlScoreList') ,(SELECT aa.page_id FROM TAPAGE aa 
WHERE aa.file_name = 'assAssetTlScoreDetail') ,'10','Y','LABEL','prompt');

/** 2018-08-06 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrAudTrailList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrAudTrailDetail') ,'10','Y','LABEL','docCountrNo','mgrAudTrailList','mgrAudTrailDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrAudTrailDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrAudTrailDtlList') ,'10','Y','LABEL','changedValue','mgrAudTrailDetail','mgrAudTrailDtlList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrAudTrailDtlList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrAudTrailDtlDetail') ,'10','Y','LABEL','prompt','mgrAudTrailDtlList','mgrAudTrailDtlDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqToolMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetListAssTlList') ,'80','N','MENU','ASSASSETTL'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetListAssTlList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetListAssTlDetail') ,'10','N','LABEL','docCountrNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR  SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetListAssTlDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assAssetScoreList') ,'10','Y','TAB','assAssetScoreList'); 

/** 2018-08-06 이근환 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'daewoongPartPurReqList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'daewoongPartPurReqDetail') ,'10','Y','LABEL','docCountrNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'daewoongPartPurReqDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'daewoongPartPurReqPartsList') ,'10','Y','LABEL','parts'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'daewoongPartPurReqPartsList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'daewoongPartPurReqPartsDetail') ,'10','Y','LABEL','ptNo'); 

/** 2018-08-07 국도 반영 */

/** 2018-08-08 김은아 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( next value for SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workLetCategList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workLetCategDetail') ,'10','Y','LABEL','woLetCtgType'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( next value for SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workLetCategDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workLetCategPointList') ,'10','Y','TAB','maEqMstrPmInsPointList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( next value for SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workLetCategPointList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workLetCategPointDetail') ,'10','Y','TAB','maEqMstrPmInsPointDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( next value for SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workLetCategDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workLetCategEtcList') ,'20','Y','TAB','woLetSup'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( next value for SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workLetCategEtcList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workLetCategEtcDetail') ,'10','Y','TAB','woLetSupDetail'); 

/** 2018-08-08 국도 반영 */

/**  2018-08-08 김정우 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
values( next value for sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'maMailList') ,(select aa.page_id from tapage aa where aa.file_name = 'maMailGroupDetail') ,'20','Y','LABEL','title'); 

/** 2018-08-09 국도 반영 */
/** 2018-08-10 국도 반영 */

/** 2018-08-13 김남현 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) values( NEXT VALUE FOR sqaPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'persPrivDbSetList') ,(select aa.page_id from tapage aa where aa.file_name = 'persPrivDbSetDetail') ,'10','Y','LABEL','docCountrNo','persPrivDbSetList','persPrivDbSetDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) values( NEXT VALUE FOR sqaPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'persPrivDbSetDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'persPrivDbSetContList') ,'10','Y','LABEL','content','persPrivDbSetDetail','persPrivDbSetContList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) values( NEXT VALUE FOR sqaPGPAGE_ID , (select aa.page_id from tapage aa where aa.file_name = 'persPrivDbSetContList') ,(select aa.page_id from tapage aa where aa.file_name = 'persPrivDbSetContDetail') ,'10','Y','MENU','MENU','persPrivDbSetContList','persPrivDbSetContDetail');

/** 2018-08-14 최지상*/
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workServiceList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workServiceDetail') ,'10','Y','LABEL','docCountrNo'); 

/** 2018-08-14 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrAtList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrAtDetail') ,'10','Y','LABEL','dataCudType','mgrAtList','mgrAtDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrAtDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrAtHistList') ,'10','Y','LABEL','atHist','mgrAtDetail','mgrAtHistList'); 

/** 2018-08-16 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptLccUsDeptList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptLccUsDeptDetailList') ,'10','Y','LABEL','monthly','assetRptLccUsDeptList','assetRptLccUsDeptDetailList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptLccUsDeptList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptLccUsDeptDetailChart') ,'20','Y','LABEL','monthly','assetRptLccUsDeptList','assetRptLccUsDeptDetailChart'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptLccUsDeptList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptLccUsDeptWorkTimeDetailChart') ,'30','Y','LABEL','monthly','assetRptLccUsDeptList','assetRptLccUsDeptWorkTimeDetailChart'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptMtbfmttrUsDeptList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptMtbfmttrUsDeptDetailList') ,'10','Y','LABEL','monthly','assetRptMtbfmttrUsDeptList','assetRptMtbfmttrUsDeptDetailList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptMtbfmttrUsDeptList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptMtbfmttrUsDeptDetailChart') ,'20','Y','LABEL','monthly','assetRptMtbfmttrUsDeptList','assetRptMtbfmttrUsDeptDetailChart'); 

/** 2018-08-16 이근환 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( next value for sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPtMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPtMstrStockList') ,'32','N','TAB','maPtMstrStockList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( next value for sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPtMstrStockList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPtStckDetail') ,'10','Y','LABEL','partNo'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( next value for sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPtMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPtMstrRecStatList') ,'34','N','TAB','maPtMstrRecStatList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( next value for sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPtMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPtMstrIssStatList') ,'36','N','TAB','maPtMstrIssStatList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( next value for sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPtMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPtMstrWoPtHistList') ,'38','N','TAB','maPtMstrWoPtHistList'); 

/** 2018-08-17 국도 반영 */
/** 2018-08-21 DREAM 반영 */

/** 2018-08-21 이근환 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( next value for SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partAdjStkMoveList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partAdjStkMoveDetail') ,'10','Y','LABEL','docCountrNo'); 

/** 2018-08-22 매일유업 반영 */
/** 2018-08-28 국도 반영 */

/** 2018-08-30 김영주 */
UPDATE TAPGPAGE SET key_type = 'TAB' , key_no = 'eqTool' WHERE c_page_id = (SELECT page_id FROM TAPAGE WHERE file_name = 'workPmListCalEquipList');


/** 2018-09-04 최지상*/
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrPtWhDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrPtWhBinList') ,'30','Y','LABEL','binNo');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrPtWhBinList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrPtWhBinDetail') ,'10','Y','LABEL','binNo');
 
/** 2018-09-04 국도 반영 */

/** 2018-09-06 김은아 */
UPDATE TAPGPAGE SET key_no = 'eqTool' WHERE FILE_NAME = 'maEqToolMstrList'AND pgpage_id = (SELECT pgpage_id FROM TAPAGE WHERE file_name = 'maEqToolMstrList');

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptDailyEngList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptDailyEngDetailList') ,'10','Y','LABEL','daily','workRptDailyEngList','workRptDailyEngDetailList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptDailyEngList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptDailyEngDetailChart') ,'20','Y','LABEL','daily','workRptDailyEngList','workRptDailyEngDetailChart'); 

/** 2018-09-06 매일유업 반영 */

/** 2018-09-07 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReadyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqInvWorkDetail') ,'10','Y','LABEL','woReqNo');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'persApprHistList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqInvWorkDetail') ,'10','Y','LABEL','woReqNo');

/** 2018-09-10 국도 반영 */

/** 2018-09-10 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptEqEngList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptEqEngDetailChart') ,'10','Y','LABEL','equipDesc','workRptEqEngList','workRptEqEngDetailChart'); 

/**  2018-09-12 최지상 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES(NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtRptMonInvtAmtList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtRptMonInvtAmtDetailChart') ,'10','Y','LABEL','chart'); 

/**  2018-09-12 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptTotEngList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptTotEngLocDetailList') ,'10','Y','LABEL','usageAmt','workRptTotEngList','workRptTotEngLocList');  
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptTotEngLocDetailList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptTotEngUsageDeptDetailList') ,'10','Y','LABEL','usageAmt','workRptTotEngLocDetailList','workRptTotEngUsageDeptDetailList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptTotEngUsageDeptDetailList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptTotEngCtgDetailList') ,'10','Y','LABEL','usageAmt','workRptTotEngUsageDeptDetailList','workRptTotEngCtgDetailList'); 

/** 2018-09-13 김은아 */
UPDATE TAPGPAGE SET key_no = 'eqTool' WHERE FILE_NAME = 'maEqToolMstrList'
AND pgpage_id = (SELECT pgpage_id FROM TAPAGE WHERE file_name = 'maEqToolMstrList');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqCatalogDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqCatalogPointList') ,'10','Y','TAB','maEqMstrPmInsPointList','maEqCatalogDetail','maEqCatalogPointList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqCatalogPointList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqCatalogPointDetail') ,'10','Y','TAB','maEqMstrPmInsPointList','maEqCatalogPointList','maEqCatalogPointDetail'); 

/**  2018-09-14 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtRptInvtCategList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'invtRptInvtCategDetailList') ,'10','Y','LABEL','invtCategDesc','invtRptInvtCategList','invtRptInvtCategDetailList'); 

/** 2018-09-14 양소영 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBdPointDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBdPointWoReqList') ,'10','Y','LABEL','woReq', 'maBdPointDetail', 'maBdPointWoReqList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBdPointDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBdPointWoPlanList') ,'20','Y','TAB','REQWOPLAN', 'maBdPointDetail', 'maBdPointWoPlanList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBdPointDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBdPointWoRsltList') ,'30','Y','TAB','REQWO', 'maBdPointDetail', 'maBdPointWoRsltList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBdPointWoReqList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqWorkDetail') ,'10','Y','LABEL','woReqNo', 'maBdPointWoReqList', 'reqWorkDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBdPointWoReqList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqInvWorkDetail') ,'10','Y','LABEL','woReqNo', 'maBdPointWoReqList', 'reqInvWorkDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBdPointWoPlanList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'woPlanDetail') ,'10','Y','LABEL','woNo', 'maBdPointWoPlanList', 'woPlanDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBdPointWoRsltList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultMstrDetail') ,'10','Y','LABEL','woNo', 'maBdPointWoRsltList', 'maWoResultMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBdPointWoRsltList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmInsMstrDetail') ,'10','Y','LABEL','woNo', 'maBdPointWoRsltList', 'maWoResultPmInsMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBdPointWoRsltList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultCmRprMstrDetail') ,'100','Y','LABEL','woNo', 'maBdPointWoRsltList', 'maWoResultCmRprMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBdPointWoRsltList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultCmRplMstrDetail') ,'110','Y','LABEL','woNo', 'maBdPointWoRsltList', 'maWoResultCmRplMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBdPointWoRsltList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultTrEleMstrDetail') ,'120','Y','LABEL','woNo', 'maBdPointWoRsltList', 'maWoResultTrEleMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBdPointWoRsltList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmCalEtcMstrDetail') ,'130','Y','LABEL','woNo', 'maBdPointWoRsltList', 'maWoResultPmCalEtcMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBdPointWoRsltList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmGmMstrDetail') ,'130','Y','LABEL','woNo', 'maBdPointWoRsltList', 'maWoResultPmGmMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBdPointWoRsltList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmPrsMstrDetail') ,'140','Y','LABEL','woNo', 'maBdPointWoRsltList', 'maWoResultPmPrsMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBdPointWoRsltList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmSclMstrDetail') ,'140','Y','LABEL','woNo', 'maBdPointWoRsltList', 'maWoResultPmSclMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBdPointWoRsltList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmGnlMstrDetail') ,'140','Y','LABEL','woNo', 'maBdPointWoRsltList', 'maWoResultPmGnlMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBdPointWoRsltList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultBmBaseMstrDetail') ,'140','Y','LABEL','woNo', 'maBdPointWoRsltList', 'maWoResultBmBaseMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBdPointWoRsltList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultCmLocBaseMstrDetail') ,'150','Y','LABEL','woNo', 'maBdPointWoRsltList', 'maWoResultCmLocBaseMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBdPointWoRsltList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmRprMstrDetail') ,'30','Y','LABEL','woNo', 'maBdPointWoRsltList', 'maWoResultPmRprMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBdPointWoRsltList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmRplMstrDetail') ,'40','Y','LABEL','woNo', 'maBdPointWoRsltList', 'maWoResultPmRplMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBdPointWoRsltList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmClnMstrDetail') ,'50','Y','LABEL','woNo', 'maBdPointWoRsltList', 'maWoResultPmClnMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBdPointWoRsltList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmOilMstrDetail') ,'60','Y','LABEL','woNo', 'maBdPointWoRsltList', 'maWoResultPmOilMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBdPointWoRsltList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultBmRprMstrDetail') ,'70','Y','LABEL','woNo', 'maBdPointWoRsltList', 'maWoResultBmRprMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBdPointWoRsltList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultBmRplMstrDetail') ,'80','Y','LABEL','woNo', 'maBdPointWoRsltList', 'maWoResultBmRplMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBdPointWoRsltList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultBmOilMstrDetail') ,'90','Y','LABEL','woNo', 'maBdPointWoRsltList', 'maWoResultBmOilMstrDetail'); 

/** 2018-09-20 국도 반영 */
/** 2018-09-20 매일유업 반영 */

/** 2018-09-28 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptBdIntensityList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptBdIntensityFreqRateDetailChart') ,'10','Y','LABEL','monthly','assetRptBdIntensityList','assetRptBdIntensityFreqRateDetailChart'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptBdIntensityList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptBdIntensityDuraRateDetailChart') ,'20','Y','LABEL','monthly','assetRptBdIntensityList','assetRptBdIntensityDuraRateDetailChart'); 

/** 2018-09-28 양소영 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoYearSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmRprMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoYearSchedList', 'maWoResultPmRprMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoYearSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmClnMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoYearSchedList', 'maWoResultPmClnMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoYearSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmGmMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoYearSchedList', 'maWoResultPmGmMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoYearSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmRplMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoYearSchedList', 'maWoResultPmRplMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoYearSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmOilMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoYearSchedList', 'maWoResultPmOilMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoYearSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmInsMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoYearSchedList', 'maWoResultPmInsMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoYearSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmGnlMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoYearSchedList', 'maWoResultPmGnlMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoYearSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmSclMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoYearSchedList', 'maWoResultPmSclMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoYearSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmPrsMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoYearSchedList', 'maWoResultPmPrsMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoYearSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmCalEtcMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoYearSchedList', 'maWoResultPmCalEtcMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoYearSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultTrEleMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoYearSchedList', 'maWoResultTrEleMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoYearSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListCinsResultMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoYearSchedList', 'workListCinsResultMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoYearSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListPtrlResultMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoYearSchedList', 'workListPtrlResultMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoMonthSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmRprMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoMonthSchedList', 'maWoResultPmRprMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoMonthSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmClnMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoMonthSchedList', 'maWoResultPmClnMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoMonthSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmGmMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoMonthSchedList', 'maWoResultPmGmMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoMonthSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmRplMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoMonthSchedList', 'maWoResultPmRplMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoMonthSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmOilMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoMonthSchedList', 'maWoResultPmOilMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoMonthSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmInsMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoMonthSchedList', 'maWoResultPmInsMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoMonthSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmGnlMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoMonthSchedList', 'maWoResultPmGnlMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoMonthSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmSclMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoMonthSchedList', 'maWoResultPmSclMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoMonthSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmPrsMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoMonthSchedList', 'maWoResultPmPrsMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoMonthSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmCalEtcMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoMonthSchedList', 'maWoResultPmCalEtcMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoMonthSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultTrEleMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoMonthSchedList', 'maWoResultTrEleMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoMonthSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListCinsResultMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoMonthSchedList', 'workListCinsResultMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoMonthSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListPtrlResultMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoMonthSchedList', 'workListPtrlResultMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmRprMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoSchedList', 'maWoResultPmRprMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmClnMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoSchedList', 'maWoResultPmClnMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmGmMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoSchedList', 'maWoResultPmGmMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmRplMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoSchedList', 'maWoResultPmRplMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmOilMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoSchedList', 'maWoResultPmOilMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmInsMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoSchedList', 'maWoResultPmInsMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmGnlMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoSchedList', 'maWoResultPmGnlMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmSclMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoSchedList', 'maWoResultPmSclMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmPrsMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoSchedList', 'maWoResultPmPrsMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmCalEtcMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoSchedList', 'maWoResultPmCalEtcMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultTrEleMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoSchedList', 'maWoResultTrEleMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListCinsResultMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoSchedList', 'workListCinsResultMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListPtrlResultMstrDetail') ,'10','Y','LABEL','pmNo', 'maWoSchedList', 'workListPtrlResultMstrDetail'); 

/** 2018-10-01 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmInsApprDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmInsApprSchedList') ,'30','N','TAB','workCalPmInsApprSchedList');

/**  2018-10-05 김영주 */  
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'5','Y','LABEL','appByHist','workPmiDetail','maAppPrcList');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReadyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiDetail') ,'10','Y','MENU','maPmInsList');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'persApprHistList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiDetail') ,'10','Y','MENU','maPmInsList');

/** 2018-10-09 노정현 */

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maUserDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrUserPlantauthList') ,'10','Y','TAB','UserPlanauth');

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrUserPlantauthList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrUserPlantauthDetail') ,'10','Y','TAB','UserPlanauth');

/** 2018-10-11 양소영 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmRInsYearList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmRInsPeriodDetail') ,'10','Y','LABEL','pmNo', 'workCalPmRInsYearList', 'workCalPmRInsPeriodDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmRInsYearList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiDetail') ,'10','Y','LABEL','pmNo', 'workCalPmRInsYearList', 'workPmiDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmRInsMonthList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmRInsPeriodDetail') ,'10','Y','LABEL','pmNo', 'workCalPmRInsMonthList', 'workCalPmRInsPeriodDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmRInsMonthList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiDetail') ,'10','Y','LABEL','pmNo', 'workCalPmRInsMonthList', 'workPmiDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmRInsPeriodList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiDetail') ,'10','Y','LABEL','pmNo', 'workCalPmRInsPeriodList', 'workPmiDetail'); 
/** 2018-10-11 양소영 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmcYearList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoYearSchedDetail') ,'10','Y','LABEL','pmNo', 'workCalPmcYearList', 'maWoYearSchedDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmcYearList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmCalEtcMstrDetail') ,'10','Y','LABEL','pmNo', 'workCalPmcYearList', 'maWoResultPmCalEtcMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmcYearList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmGnlMstrDetail') ,'10','Y','LABEL','pmNo', 'workCalPmcYearList', 'maWoResultPmGnlMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmcYearList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmPrsMstrDetail') ,'10','Y','LABEL','pmNo', 'workCalPmcYearList', 'maWoResultPmPrsMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmcYearList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmSclMstrDetail') ,'10','Y','LABEL','pmNo', 'workCalPmcYearList', 'maWoResultPmSclMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmcMonthList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoMonthSchedDetail') ,'10','Y','LABEL','pmNo', 'workCalPmcMonthList', 'maWoMonthSchedDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmcMonthList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmCalEtcMstrDetail') ,'10','Y','LABEL','pmNo', 'workCalPmcMonthList', 'maWoResultPmCalEtcMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmcMonthList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmGnlMstrDetail') ,'10','Y','LABEL','pmNo', 'workCalPmcMonthList', 'maWoResultPmGnlMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmcMonthList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmPrsMstrDetail') ,'10','Y','LABEL','pmNo', 'workCalPmcMonthList', 'maWoResultPmPrsMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmcMonthList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmSclMstrDetail') ,'10','Y','LABEL','pmNo', 'workCalPmcMonthList', 'maWoResultPmSclMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmcPeriodList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmCalEtcMstrDetail') ,'10','Y','LABEL','pmNo', 'workCalPmcPeriodList', 'maWoResultPmCalEtcMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmcPeriodList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmGnlMstrDetail') ,'10','Y','LABEL','pmNo', 'workCalPmcPeriodList', 'maWoResultPmGnlMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmcPeriodList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmPrsMstrDetail') ,'10','Y','LABEL','pmNo', 'workCalPmcPeriodList', 'maWoResultPmPrsMstrDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmcPeriodList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmSclMstrDetail') ,'10','Y','LABEL','pmNo', 'workCalPmcPeriodList', 'maWoResultPmSclMstrDetail'); 

/** 2018-10-11 김영주 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) 
VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListRInsDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'5','N','LABEL','appByHist','workPmListRInsDetail','maAppPrcList');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReadyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListRInsDetail') ,'10','Y','LABEL','pmNo');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'persApprHistList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListRInsDetail') ,'10','Y','LABEL','pmNo');

/** 2018-10-12 국도화학 반영 */

/** 2018-10-12 김영주 추가 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListInsList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListCinsResultMstrDetail') ,'10','N','LABEL','pmNo');

/** 2018-10-18 국도화학 반영 */

/** 2018-10-22 김영주 추가 */
UPDATE TAPGPAGE SET is_use = 'N' WHERE page_id = (SELECT page_id FROM TAPAGE WHERE file_name = 'workPmListDInsDetail') AND c_page_id = (SELECT page_id FROM TAPAGE WHERE file_name ='workPmListDInsPointList');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListDInsDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPmMstrPointList') ,'20','Y','LABEL','maPmMstrPointList');

/** 2018-10-23 최지상 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPtMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partListBinList') ,'5','Y','TAB','binNo');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partListBinList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partListBinDetail') ,'10','Y','LABEL','binNo');

/** 2018-10-23 양소영 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListInsList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListCinsResultMstrDetail') ,'70','N','LABEL','pmNo', 'workPmListInsList', 'workListCinsResultMstrDetail'); 

/** 2018-10-24 최지상 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPtMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partListSafQtyList') ,'8','Y','TAB','saftyQty');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partListSafQtyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partListSafQtyDetail') ,'10','Y','LABEL','saftyQty');

/** 2018-10-24 양소영 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmDInsMonthList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiDInsDetail') ,'10','Y','LABEL','pmNo', 'workCalPmDInsMonthList', 'workPmiDInsDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmDInsMonthList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmDInsMonthDetail') ,'10','Y','LABEL','pmNo', 'workCalPmDInsMonthList', 'workCalPmDInsMonthDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmCInsMonthList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmiCInsDetail') ,'10','Y','LABEL','pmInsNo', 'workCalPmCInsMonthList', 'workPmiCInsDetail'); 

/** 2018-10-25 국도화학 반영 */

/** 2018-10-25 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'appReadyList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqToolMstrDetail') ,'10','Y','LABEL','equipment');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'persApprHistList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqToolMstrDetail') ,'10','Y','LABEL','equipment');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'persApprovedHistList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqToolMstrDetail') ,'10','Y','LABEL','equipment');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR  SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'persReqHistList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maEqToolMstrDetail') ,'10','Y','LABEL','equipment');

/** 2018-10-26 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPlanApprDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPlanApprEquipList') ,'10','Y','TAB','eqTool','workPlanApprDetail','workPlanApprEquipList'); 
INSERT INTO TAPGPAGE(PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) 
SELECT NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPlanApprEquipList'), c_page_id, ord_no, 'Y', key_type, key_no, 'workPlanApprEquipList', c_file_name
FROM TAPGPAGE WHERE page_id = (SELECT page_id FROM TAPAGE WHERE file_name = 'maEqToolMstrList');

/** 2018-10-26 국도화학 반영 */

/** 2018-10-30 이근환 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maPtIssDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'partIssWoPartList') ,'10','Y','LABEL','work'); 

/** 2018-10-30 매일유업 반영 */
/** 2018-11-01 국도화학 반영 */
/** 2018-11-02 매일유업 반영 */
/** 2018-11-07 본사 DREAM 반영 */

/** 2018-11-07 김은아 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'mgrCalLineTimeSetList') ,(SELECT aa.page_id FROM tapage aa WHERE aa.file_name = 'mgrCalLineTimeSetDetail') ,'10','Y','LABEL','lnWrkListSetL','mgrCalLineTimeSetList','mgrCalLineTimeSetDetail'); 

/** 2018-11-08 국도화학 반영 */
/** 2018-11-08 매일유업 반영 */

/** 2018-11-09 김은아 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrCalCompWkrcalList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrCalCompWkrcalDetail') ,'10','Y','LABEL','workDayCalSetL','mgrCalCompWkrcalList','mgrCalCompWkrcalDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'mgrCalCompWkrcalDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'mgrCalCompWkrcalDowsetList') ,'10','Y','LABEL','wrkcalDowSet','mgrCalCompWkrcalDetail','mgrCalCompWkrcalDowsetList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) values(  NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'mgrCalCompWkrcalDowsetList') ,(select aa.page_id from tapage aa where aa.file_name = 'mgrCalCompWkrcalDowsetDetail') ,'10','Y','LABEL','wrkcalDowSet','mgrCalCompWkrcalDowsetList','mgrCalCompWkrcalDowsetDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'mgrCalCompWkrcalDetail') ,(select aa.page_id from tapage aa where aa.file_name = 'mgrCalCompWkrcalDaysetList') ,'10','Y','LABEL','wrkcalDaySet','mgrCalCompWkrcalDetail','mgrCalCompWkrcalDaysetList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) values( NEXT VALUE FOR sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'mgrCalCompWkrcalDaysetList') ,(select aa.page_id from tapage aa where aa.file_name = 'mgrCalCompWkrcalDaysetDetail') ,'10','Y','LABEL','wrkcalDaySet','mgrCalCompWkrcalDaysetList','mgrCalCompWkrcalDaysetDetail'); 

/** 2018-11-09 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultCompIntfList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultCompIntfDetail') ,'10','Y','LABEL','docCountrNo','consultCompIntfList','consultCompIntfDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultCompIntfDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultCompIntfMapList') ,'10','Y','TAB','intfMap','consultCompIntfDetail','consultCompIntfMapList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultCompIntfMapList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultCompIntfMapDetail') ,'10','Y','LABEL','order','consultCompIntfMapList','consultCompIntfMapDetail'); 

/** 2018-11-12 김은아 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES(NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrCalLineTimeSetDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrCalLineTimeDowSetList') ,'10','Y','TAB','maLineTimeDowList','mgrCalLineTimeSetDetail','mgrCalLineTimeDowSetList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES(NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrCalLineTimeDowSetList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrCalLineTimeDowSetDetail') ,'10','Y','TAB','maLineTimeDowList','mgrCalLineTimeDowSetList','mgrCalLineTimeDowSetDetail'); 

/** 2018-11-15 국도화학 반영 */

/** 2018-11-15 이근환 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'budgetAccountList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'budgetAccountDetail') ,'10','Y','LABEL','accntNo'); 

/** 2018-11-16 국도화학 반영 */
/** 2018-11-19 본사 국도화학 반영 */
/** 2018-11-19 국도화학 반영 */ 
/** 2018-11-20 국도화학 반영 */
/** 2018-11-21 국도화학 반영 */

/** 2018-11-21 김정우 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( next value for sqaPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultToolList') ,'35','Y','TAB','maWoResultToolList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( next value for sqaPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultToolList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultToolDetail') ,'10','Y','TAB','maWoResultToolDetail'); 

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( next value for sqaPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultBmRplMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultBmRplToolList') ,'35','Y','TAB','maWoResultBmRplToolList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( next value for sqaPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultBmRplToolList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultBmRplToolDetail') ,'10','Y','TAB','maWoResultBmRplToolDetail'); 

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( next value for sqaPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultBmOilMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultBmOilToolList') ,'35','Y','TAB','maWoResultBmOilToolList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( next value for sqaPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultBmOilToolList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultBmOilToolDetail') ,'10','Y','TAB','maWoResultBmOilToolDetail'); 

/** 2018-11-28 국도화학 반영 */
/** 2018-11-29 국도화학 반영 */
/** 2018-12-03 국도화학 반영 */
/** 2018-12-05 본사 DREAM 반영 */
/** 2018-12-06 매일유업 반영 */

/** 2018-12-07 이지수 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBdPointDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBdPointDocLibList') ,'40','Y','TAB','maDocLibList');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO) 
VALUES( NEXT VALUE FOR SQAPGPAGE_ID , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBdPointDocLibList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maBdPointDocLibDetail') ,'10','Y','TAB','maDocLibDetail'); 

/** 2018-12-07 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptLccLocList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptLccLocMaintAmtDetailChart') ,'40','Y','LABEL','monthly', 'assetRptLccLocList', 'assetRptLccLocMaintAmtDetailChart');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptLccEquipList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'assetRptLccEquipMaintAmtDetailChart') ,'40','Y','LABEL','monthly', 'assetRptLccEquipList', 'assetRptLccEquipMaintAmtDetailChart');

/** 2018-12-10 양소영 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'woPlanDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'woPlanWoLetList') ,'40','N','TAB','woPlanWoLetList', 'woPlanDetail', 'woPlanWoLetList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'woPlanWoLetList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'woPlanWoLetDetail') ,'10','Y','LABEL','woLetCtgType', 'woPlanWoLetList', 'woPlanWoLetDetail'); 
UPDATE TAPGPAGE SET ord_no='90' WHERE page_id=(SELECT page_id FROM TAPAGE WHERE file_name='woPlanDetail') AND c_page_id = (SELECT page_id FROM TAPAGE WHERE file_name='maDocLibList');

/** 2018-12-14 국도화학 반영 */

/** 2018-12-14 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrMsgRecList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'mgrMsgRecDetail') ,'10','Y','LABEL','docCountrNo','mgrMsgRecList','mgrMsgRecDetail'); 

/** 2018-12-17 10:08 국도화학 반영 */

/** 2018-12-17 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultCompMsgList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'consultCompMsgDetail') ,'10','Y','LABEL','docCountrNo','consultCompMsgList','consultCompMsgDetail'); 

/** 2018-12-17 12:20 국도화학 반영 */
/** 2018-12-19 11:00 국도화학 반영 */
/** 2018-12-19 17:25 국도화학 반영 */
/** 2018-12-20 17:10 국도화학 반영 */
/** 2018-12-21 17:50 국도화학 반영 */

/** 2018-12-26 양소영 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workLetList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workLetDetail') ,'10','Y','LABEL','woNo', 'workLetList', 'workLetDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workLetDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workLetPermitList') ,'10','Y','LABEL','woType2', 'workLetDetail', 'workLetPermitList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workLetPermitList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workLetPermitDetail') ,'10','Y','LABEL','woNo', 'workLetPermitList', 'workLetPermitDetail'); 

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workLetPermitDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workLetPermitPointList') ,'010','Y','TAB','maEqMstrPmInsPointList', 'workLetPermitDetail', 'workLetPermitPointList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workLetPermitDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workLetPermitCraftList') ,'020','Y','TAB','maWoResultCraftList', 'workLetPermitDetail', 'workLetPermitCraftList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workLetPermitDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workLetPermitDocLibList') ,'050','Y','TAB','maDocLibList', 'workLetPermitDetail', 'workLetPermitDocLibList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workLetPermitPointList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workLetPermitPointDetail') ,'10','Y','LABEL','order', 'workLetPermitPointList', 'workLetPermitPointDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workLetPermitCraftList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workLetPermitCraftDetail') ,'10','Y','LABEL','order', 'workLetPermitCraftList', 'workLetPermitCraftDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workLetPermitDocLibList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workLetPermitDocLibDetail') ,'10','Y','TAB','maDocLibDetail', 'workLetPermitDocLibList', 'workLetPermitDocLibDetail'); 

/** 2018-12-28 14:20 국도화학 반영 */

/** 2019-01-03 김영주 */
UPDATE TAPGPAGE SET file_name = 'consultPgmMsgList', c_file_name = 'consultPgmMsgDetail' WHERE file_name = 'consultCompMsgList';

/** 2019-01-03 고려용접봉(본사) 반영 */

/** 2019-01-04 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maMyInfo') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'persPrivInfoMsgEmpList') ,'30','Y','TAB','msgEmp','maMyInfo','persPrivInfoMsgEmpList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'persPrivInfoMsgEmpList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'persPrivInfoMsgEmpDetail') ,'10','Y','LABEL','docCountrNo','persPrivInfoMsgEmpList','persPrivInfoMsgEmpDetail'); 

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


/** 2019-02-11 양소영 */
UPDATE TAPGPAGE SET key_no='maEqMstrPmInsPointList' WHERE page_id = (SELECT page_id FROM tapage WHERE file_name = 'workCalPmInsApprDetail' ) AND c_pagE_id = (SELECT pagE_id FROM tapage WHERE file_name = 'workCalPmInsApprRsltList');

/** 2019-02-18 양소영 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmInsApprCompList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmInsApprCompDetail') ,'10','Y','LABEL','title', 'workCalPmInsApprCompList', 'workCalPmInsApprCompDetail');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmInsApprCompDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmInsApprWorkList') ,'10','Y','TAB','workCalPmInsApprWorkList', 'workCalPmInsApprCompDetail', 'workCalPmInsApprWorkList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmInsApprCompDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmInsApprRsltList') ,'20','Y','TAB','maEqMstrPmInsPointList', 'workCalPmInsApprCompDetail', 'workCalPmInsApprRsltList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmInsApprCompDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmInsApprSchedList') ,'30','Y','TAB','workCalPmInsApprSchedList', 'workCalPmInsApprCompDetail', 'workCalPmInsApprSchedList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmInsApprCompDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'35','Y','LABEL','appByHist', 'workCalPmInsApprCompDetail', 'maAppPrcList'); 

INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmInsApprNotList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmInsApprNOTDetail') ,'10','Y','LABEL','title', 'workCalPmInsApprNotList', 'workCalPmInsApprNOTDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmInsApprNOTDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmInsApprWorkList') ,'10','Y','TAB','workCalPmInsApprWorkList', 'workCalPmInsApprNOTDetail', 'workCalPmInsApprWorkList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmInsApprNOTDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmInsApprRsltList') ,'20','Y','TAB','maEqMstrPmInsPointList', 'workCalPmInsApprNOTDetail', 'workCalPmInsApprRsltList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmInsApprNOTDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmInsApprSchedList') ,'30','Y','TAB','workCalPmInsApprSchedList', 'workCalPmInsApprNOTDetail', 'workCalPmInsApprSchedList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR SQAPGPAGE_ID, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workCalPmInsApprNOTDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'35','Y','LABEL','appByHist', 'workCalPmInsApprNOTDetail', 'maAppPrcList'); 

/** 2019-03-11 이근환 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME, REMARK) 
VALUES( NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptPmwCmptRateList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptPmwCmptDetailList') ,'10','Y',UPPER(''),UPPER(''),'workRptPmwCmptRateList','workRptPmwCmptDetailList','선택한 율에 세부 데이타를 확인');

/** 2019-03-11 이근환 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME, REMARK) 
VALUES( NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptPmwRateList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptPmwDetailList') ,'10','Y',UPPER(''),UPPER(''),'workRptPmwRateList','workRptPmwDetailList','선택한 율에 세부 데이타를 확인');



/** 2019-03-12 이근환 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME, REMARK) 
VALUES( NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptWoPmwCmptRateList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptWoPmwCmptDetailList') ,'10','Y',UPPER(''),UPPER(''),'workRptWoPmwCmptRateList','workRptWoPmwCmptDetailList','선택한 율에 세부 데이타를 확인');



/** 2019-03-12 김영주 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME, REMARK) VALUES( NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqRptEmWoGenRateList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqRptEmWoGenDetailList') ,'10','Y',UPPER('LABEL'),UPPER('monthly'),'reqRptEmWoGenRateList','reqRptEmWoGenDetailList','선택한 율에 세부 데이타를 확인');


/** 2019-03-12 양소영 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME, REMARK) VALUES(NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptPmiEquipCmptRateList'),(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptPmiEquipCmptDetailList') ,'10','Y',UPPER('LABEL'), 'monthly','workRptPmiEquipCmptRateList','workRptPmiEquipCmptDetailList','선택한 율에 세부 데이타를 확인');


/** 2019-03-12 이근환 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME, REMARK) 
VALUES( NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptPmiCmptRateList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptPmiCmptDetailList') ,'10','Y',UPPER(''),UPPER(''),'workRptPmiCmptRateList','workRptPmiCmptDetailList','선택한 율에 세부 데이타를 확인');


/** 2019-03-26 김정우 */
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name)
VALUES (NEXT VALUE FOR SQApgpage_id, (select page_id from tapage where file_name='WoReqListFragment'), (select page_id from tapage where file_name='WoReqDetailFragment'),'010','Y','PAGE','WoReqDetailFragment','작업요청 상세','WoReqListFragment','WoReqDetailFragment','WoReqDetailFragment' );
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name)
VALUES (NEXT VALUE FOR SQApgpage_id, (select page_id from tapage where file_name='WoReqDetailFragment'), (select page_id from tapage where file_name='WoReqPhotoListFragment'),'010','Y','LABEL','photoVideo','사진 비디오목록','WoReqDetailFragment','WoReqPhotoListFragment','woreq_detail_photo_rl' );
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name)
VALUES (NEXT VALUE FOR SQApgpage_id, (select page_id from tapage where file_name='WoReqPhotoListFragment'), (select page_id from tapage where file_name='WoReqPhotoDetailFragment'),'010','Y','PAGE','WoReqPhotoDetailFragment','사진비디오 상세','WoReqPhotoDetailFragment','WoReqPhotoDetailFragment','WoReqPhotoDetailFragment' );

INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='AssetListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'AssetDetailFragment') ,'10','Y','PAGE','AssetDetailFragment','설비자산상세','AssetListFragment','AssetDetailFragment','AssetDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='AssetDetailFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'AssetDocumentListFragment') ,'10','Y','LABEL','file','첨부문서목록','AssetDetailFragment','AssetDocumentListFragment','asset_detail_document_rl');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='AssetDetailFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'AssetToolListFragment') ,'10','Y','TAB','eqTool','계측기목록','AssetDetailFragment','AssetToolListFragment','asset_detail_calib_rl');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='AssetDetailFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'WoHistListFragment') ,'10','Y','TAB','woHist','작업이력목록','AssetDetailFragment','WoHistListFragment','');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='AssetDocumentListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'AssetDocumentDetailFragment') ,'10','Y','PAGE','AssetDocumentDetailFragment','첨부문서상세','AssetDocumentListFragment','AssetDocumentDetailFragment','AssetDocumentDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='AssetToolListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'AssetToolDetailFragment') ,'10','Y','PAGE','AssetToolDetailFragment','계측기상세','AssetToolListFragment','AssetToolDetailFragment','AssetToolDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='IssListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'IssDetailFragment') ,'10','Y','PAGE','IssDetailFragment','부품출고상세','IssListFragment','IssDetailFragment','IssDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='PmiDayListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'PmiDayDetailFragment') ,'10','Y','PAGE','PmiDayDetailFragment','일상점검상세','PmiDayListFragment','PmiDayDetailFragment','PmiDayDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='PmiDayDetailFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'PmiDayPointFragment') ,'10','Y','PAGE','PmiDayPointFragment','일상점검항목','PmiDayDetailFragment','PmiDayPointFragment','PmiDayPointFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='PmiDayDetailFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'PmiDayHistListFragment') ,'10','Y','PAGE','PmiDayHistListFragment','이력보기목록','PmiDayDetailFragment','PmiDayHistListFragment','PmiDayHistListFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='PmiDayPointFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'PmiDayPhotoListFragment') ,'10','Y','LABEL','photo','사진목록','PmiDayPointFragment','PmiDayPhotoListFragment','pmi_day_point_detail_photo_rl');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='PmiDayPhotoListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'PmiDayPhotoDetailFragment') ,'10','Y','PAGE','PmiDayPhotoDetailFragment','사진첨부상세','PmiDayPhotoListFragment','PmiDayPhotoDetailFragment','PmiDayPhotoDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='PmiDayHistListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'PmiDayHistDetailFragment') ,'10','Y','PAGE','PmiDayHistDetailFragment','이력보기상세','PmiDayHistListFragment','PmiDayHistDetailFragment','PmiDayHistDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='PmiPartListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'PmiPartDetailFragment') ,'10','Y','PAGE','PmiPartDetailFragment','Part Change상세','PmiPartListFragment','PmiPartDetailFragment','PmiPartDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='PmiPartDetailFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'PmiPartPointFragment') ,'10','Y','PAGE','PmiPartPointFragment','Part Change항목','PmiPartDetailFragment','PmiPartPointFragment','PmiPartPointFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='PmiPartDetailFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'PmiPartHistListFragment') ,'10','Y','PAGE','PmiPartHistListFragment','이력보기목록','PmiPartDetailFragment','PmiPartHistListFragment','PmiPartHistListFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='PmiPartPointFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'PmiPartPhotoListFragment') ,'10','Y','LABEL','photo','사진목록','PmiPartPointFragment','PmiPartPhotoListFragment','pmi_part_point_detail_photo_rl');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='PmiPartPhotoListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'PmiPartPhotoDetailFragment') ,'10','Y','PAGE','PmiPartPhotoDetailFragment','사진첨부상세','PmiPartPhotoListFragment','PmiPartPhotoDetailFragment','PmiPartPhotoDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='PmiPartHistListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'PmiPartHistDetailFragment') ,'10','Y','PAGE','PmiPartHistDetailFragment','이력보기상세','PmiPartHistListFragment','PmiPartHistDetailFragment','PmiPartHistDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='PmiPatrolListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'PmiPatrolDetailFragment') ,'10','Y','PAGE','PmiPatrolDetailFragment','순회점검상세','PmiPatrolListFragment','PmiPatrolDetailFragment','PmiPatrolDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='PmiPatrolDetailFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'PmiPatrolPointFragment') ,'10','Y','PAGE','PmiPatrolPointFragment','순회점검항목','PmiPatrolDetailFragment','PmiPatrolPointFragment','PmiPatrolPointFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='PmiPatrolDetailFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'PmiPatrolHistListFragment') ,'10','Y','PAGE','PmiPatrolHistListFragment','이력보기목록','PmiPatrolDetailFragment','PmiPatrolHistListFragment','PmiPatrolHistListFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='PmiPatrolPointFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'PmiPatrolPhotoListFragment') ,'10','Y','LABEL','photo','사진목록','PmiPatrolPointFragment','PmiPatrolPhotoListFragment','pmi_patrol_point_detail_photo_rl');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='PmiPatrolPhotoListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'PmiPatrolPhotoDetailFragment') ,'10','Y','PAGE','PmiPatrolPhotoDetailFragment','사진첨부상세','PmiPatrolPhotoListFragment','PmiPatrolPhotoDetailFragment','PmiPatrolPhotoDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='PmiPatrolHistListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'PmiPatrolHistDetailFragment') ,'10','Y','PAGE','PmiPatrolHistDetailFragment','이력보기상세','PmiPatrolHistListFragment','PmiPatrolHistDetailFragment','PmiPatrolHistDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='PmiRoutineListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'PmiRoutineDetailFragment') ,'10','Y','PAGE','PmiRoutineDetailFragment','정기점검상세','PmiRoutineListFragment','PmiRoutineDetailFragment','PmiRoutineDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='PmiRoutineDetailFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'PmiRoutinePointFragment') ,'10','Y','PAGE','PmiRoutinePointFragment','정기점검항목','PmiRoutineDetailFragment','PmiRoutinePointFragment','PmiRoutinePointFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='PmiRoutineDetailFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'PmiRoutineHistListFragment') ,'10','Y','PAGE','PmiRoutineHistListFragment','이력보기목록','PmiRoutineDetailFragment','PmiRoutineHistListFragment','PmiRoutineHistListFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='PmiRoutinePointFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'PmiRoutinePhotoListFragment') ,'10','Y','LABEL','photo','사진목록','PmiRoutinePointFragment','PmiRoutinePhotoListFragment','pmi_routine_point_detail_photo_rl');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='PmiRoutinePhotoListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'PmiRoutinePhotoDetailFragment') ,'10','Y','PAGE','PmiRoutinePhotoDetailFragment','사진첨부상세','PmiRoutinePhotoListFragment','PmiRoutinePhotoDetailFragment','PmiRoutinePhotoDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='PmiRoutineHistListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'PmiRoutineHistDetailFragment') ,'10','Y','PAGE','PmiRoutineHistDetailFragment','이력보기상세','PmiRoutineHistListFragment','PmiRoutineHistDetailFragment','PmiRoutineHistDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='WoResListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'WoResDetailFragment') ,'10','Y','PAGE','WoResDetailFragment','요청접수상세','WoResListFragment','WoResDetailFragment','WoResDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='WoResDetailFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'WoResPhotoListFragment') ,'10','Y','LABEL','reqPhoto','요청사진목록','WoResDetailFragment','WoResPhotoListFragment','wores_detail_photo_rl');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='WoResDetailFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'WoResWorkListFragment') ,'10','Y','TAB','reqWorkResList','처리사항목록','WoResDetailFragment','WoResWorkListFragment','wores_detail_work_rl');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='WoResPhotoListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'WoResPhotoDetailFragment') ,'10','Y','PAGE','WoResPhotoDetailFragment','요청사진상세','WoResPhotoListFragment','WoResPhotoDetailFragment','WoResPhotoDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='WoResWorkListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'UnplanDetailFragment') ,'10','Y','PAGE','UnplanDetailFragment','돌발작업상세','WoResWorkListFragment','UnplanDetailFragment','UnplanDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='StktakeHdrListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'StktakeListFragment') ,'10','Y','PAGE','StktakeListFragment','부품목록','StktakeHdrListFragment','StktakeListFragment','StktakeListFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='StktakeListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'StktakeDetailFragment') ,'10','Y','PAGE','StktakeDetailFragment','부품상세','StktakeListFragment','StktakeDetailFragment','StktakeDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='StockListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'StockDetailFragment') ,'10','Y','PAGE','StockDetailFragment','현재고상세','StockListFragment','StockDetailFragment','StockDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='CalListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'CalDetailFragment') ,'10','Y','PAGE','CalDetailFragment','검교정상세','CalListFragment','CalDetailFragment','CalDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='CalDetailFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'CalBaseEqListFragment') ,'10','Y','LABEL','calibEq','표준기목록','CalDetailFragment','CalBaseEqListFragment','cal_detail_base_eq_rl');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='CalDetailFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'CalCalibValueListFragment') ,'10','Y','LABEL','measure','측정값목록','CalDetailFragment','CalCalibValueListFragment','cal_detail_measure_rl');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='CalDetailFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'CalCraftListFragment') ,'10','Y','LABEL','woCraft','작업자목록','CalDetailFragment','CalCraftListFragment','cal_detail_worker_rl');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='CalDetailFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'CalPhotoListFragment') ,'10','Y','LABEL','photo','사진목록','CalDetailFragment','CalPhotoListFragment','cal_detail_photo_rl');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='CalBaseEqListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'CalBaseEqDetailFragment') ,'10','Y','PAGE','CalBaseEqDetailFragment','표준기상세','CalBaseEqListFragment','CalBaseEqDetailFragment','CalBaseEqDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='CalCalibGnlValueListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'CalCalibGnlValueDetailFragment') ,'10','Y','PAGE','CalCalibGnlValueDetailFragment','측정값상세(일반)','CalCalibGnlValueListFragment','CalCalibGnlValueDetailFragment','CalCalibGnlValueDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='CalCalibPrsValueListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'CalCalibPrsValueDetailFragment') ,'10','Y','PAGE','CalCalibPrsValueListFragment','측정값상세(압력)','CalCalibPrsValueListFragment','CalCalibPrsValueListFragment','CalCalibPrsValueListFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='CalCalibValueListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'CalCalibValueDetailFragment') ,'10','Y','PAGE','CalCalibValueDetailFragment','측정값상세','CalCalibValueListFragment','CalCalibValueDetailFragment','CalCalibValueDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='CalCraftListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'CalCraftDetailFragment') ,'10','Y','PAGE','CalCraftDetailFragment','작업자상세','CalCraftListFragment','CalCraftDetailFragment','CalCraftDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='CalPhotoListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'CalPhotoDetailFragment') ,'10','Y','PAGE','CalPhotoDetailFragment','사진첨부상세','CalPhotoListFragment','CalPhotoDetailFragment','CalPhotoDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='WoHistListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'WoHistDetailFragment') ,'10','Y','PAGE','WoHistDetailFragment','작업이력상세','WoHistListFragment','WoHistDetailFragment','WoHistDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='InspectionListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'InspectionDetailFragment') ,'10','Y','PAGE','InspectionDetailFragment','예방점검 항목','InspectionListFragment','InspectionDetailFragment','InspectionDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='InspectionDetailFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'InspectionHistListFragment') ,'10','Y','PAGE','InspectionHistListFragment','이력보기목록','InspectionDetailFragment','InspectionHistListFragment','InspectionHistListFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='InspectionDetailFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'WopointDetailFragment') ,'10','Y','PAGE','WopointDetailFragment','예방점검항목 상세','InspectionDetailFragment','WopointDetailFragment','WopointDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='WopointDetailFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'InspectionPhotoListFragment') ,'10','Y','PAGE','InspectionPhotoListFragment','사진첨부목록','WopointDetailFragment','InspectionPhotoListFragment','wopoint_detail_photo_rl');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='InspectionPhotoListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'InspectionPhotoDetailFragment') ,'10','Y','PAGE','InspectionPhotoDetailFragment','사진첨부상세','InspectionPhotoListFragment','InspectionPhotoDetailFragment','InspectionPhotoDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='InspectionHistListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'InspectionHistDetailFragment') ,'10','Y','PAGE','InspectionHistDetailFragment','이력보기상세','InspectionHistListFragment','InspectionHistDetailFragment','InspectionHistDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='PmworkListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'PmworkDetailFragment') ,'10','Y','PAGE','PmworkDetailFragment','계획작업상세','PmworkListFragment','PmworkDetailFragment','PmworkDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='PmworkDetailFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'PmworkCraftListFragment') ,'10','Y','LABEL','woCraft','작업자목록','PmworkDetailFragment','PmworkCraftListFragment','pmwork_detail_worker_rl');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='PmworkDetailFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'PmworkPartsListFragment') ,'10','Y','LABEL','insertPart','투입부품목록','PmworkDetailFragment','PmworkPartsListFragment','pmwork_detail_parts_rl');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='PmworkDetailFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'PmworkPhotoListFragment') ,'10','Y','LABEL','photo','사직첨부목록','PmworkDetailFragment','PmworkPhotoListFragment','pmwork_detail_photo_rl');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='PmworkCraftListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'PmworkCraftDetailFragment') ,'10','Y','PAGE','PmworkCraftDetailFragment','작업자상세','PmworkCraftListFragment','PmworkCraftDetailFragment','PmworkCraftDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='PmworkPartsListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'PmworkPartsDetailFragment') ,'10','Y','PAGE','PmworkPartsDetailFragment','투입부품상세','PmworkPartsListFragment','PmworkPartsDetailFragment','PmworkPartsDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='PmworkPhotoListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'PmworkPhotoDetailFragment') ,'10','Y','PAGE','PmworkPhotoDetailFragment','사진첨부상세','PmworkPhotoListFragment','PmworkPhotoListFragment','PmworkPhotoListFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='UnplanListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'UnplanDetailFragment') ,'10','Y','PAGE','UnplanDetailFragment','계획작업상세','UnplanListFragment','UnplanDetailFragment','UnplanDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='UnplanDetailFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'UnplanFailDetailFragment') ,'10','Y','LABEL','break','고장목록','UnplanDetailFragment','UnplanFailDetailFragment','unplan_detail_fail_rl');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='UnplanDetailFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'UnplanCraftListFragment') ,'10','Y','LABEL','woCraft','작업자목록','UnplanDetailFragment','UnplanCraftListFragment','unplan_detail_worker_rl');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='UnplanDetailFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'UnplanPartsListFragment') ,'10','Y','LABEL','insertPart','투입부품목록','UnplanDetailFragment','UnplanPartsListFragment','unplan_detail_parts_rl');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='UnplanDetailFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'UnplanPhotoListFragment') ,'10','Y','LABEL','photo','사직첨부목록','UnplanDetailFragment','UnplanPhotoListFragment','unplan_detail_photo_rl');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='UnplanCraftListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'UnplanCraftDetailFragment') ,'10','Y','PAGE','UnplanCraftDetailFragment','작업자상세','UnplanCraftListFragment','UnplanCraftDetailFragment','UnplanCraftDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='UnplanPartsListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'UnplanPartsDetailFragment') ,'10','Y','PAGE','UnplanPartsDetailFragment','투입부품상세','UnplanPartsListFragment','UnplanPartsDetailFragment','UnplanPartsDetailFragment');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name='UnplanPhotoListFragment') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'UnplanPhotoDetailFragment') ,'10','Y','PAGE','UnplanPhotoDetailFragment','사진첨부상세','UnplanPhotoListFragment','UnplanPhotoDetailFragment','UnplanPhotoDetailFragment');

INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(
next value for sqapgpage_id, (select page_id from tapage where file_name='PlandownListFragment') ,
(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'PlandownOptionFragment') ,'10','Y','PAGE','PlandownOptionFragment','계획다운로드옵션','PlandownListFragment','PlandownOptionFragment','plandown_list_option_fl');
INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(
next value for sqapgpage_id, (select page_id from tapage where file_name='CalDetailFragment') ,
(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'CalCalibGnlValueListFragment') ,'20','Y','PAGE','CalCalibGnlValueListFragment','검교정 측정값목록(일반)','CalDetailFragment','CalCalibGnlValueListFragment','');

INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(
next value for sqapgpage_id, (select page_id from tapage where file_name='CalDetailFragment') ,
(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'CalCalibPrsValueListFragment') ,'30','Y','PAGE','CalCalibPrsValueListFragment','검교정 측정값목록(압력)','CalDetailFragment','CalCalibPrsValueListFragment','');

INSERT INTO TAPGPAGE(pgpage_id, page_id, c_page_id,ord_no,is_use,key_type, key_no, remark, file_name, c_file_name, pgpage_name) VALUES(
next value for sqapgpage_id, (select page_id from tapage where file_name='CalDetailFragment') ,
(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'CalCalibSclValueFragment') ,'40','Y','PAGE','CalCalibSclValueFragment','검교정 측정값목록(저울)','CalDetailFragment','CalCalibSclValueFragment','');


/** 2019-03-12 이근환 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME, REMARK) 
VALUES( NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptPmiEquipPlanRateList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workRptPmiEquipPlanDetailList') ,'10','Y',UPPER(''),UPPER(''),'workRptPmiEquipPlanRateList','workRptPmiEquipPlanDetailList','선택한 율에 세부 데이타를 확인');



/** 2019-03-12 김은아 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) values(next value for sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'reqRptPreWoPlanRateList'),(select aa.page_id from tapage aa where aa.file_name = 'reqRptPreWoPlanDetailList') ,'10','Y','LABEL','woNo','reqRptPreWoPlanRateList','reqRptPreWoPlanDetailList'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) values( next value for sqapgpage_id , (select aa.page_id from tapage aa where aa.file_name = 'workRptWoEndRateList') ,(select aa.page_id from tapage aa where aa.file_name = 'workRptWoEndDetailList') ,'10','Y','TAB','','workRptWoEndRateList','workRptWoEndDetailList'); 

/** 2019-03-12 김남현 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME, REMARK) VALUES( NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqRptPreWoreqRateList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqRptPreWoreqDetailList') ,'10','Y','LABEL','plant','reqRptPreWoreqRateList','reqRptPreWoreqDetailList','선택한 율에 세부 데이타를 확인');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME, REMARK) VALUES( NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqRptWoPlanCmptRateList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'reqRptWoPlanCmptDetailList') ,'10','Y','LABEL','plant','reqRptWoPlanCmptRateList','reqRptWoPlanCmptDetailList','선택한 율에 세부 데이타를 확인');

/** 2019-03-12 김은아 */
UPDATE tapgpage SET key_no = 'WoNo' WHERE page_id = (Select page_id FROM tapage WHERE file_name = 'workRptWoEndRateList');

/** 2019-03-13 국도화학 반영 */
/** 2019-03-13 고려용접봉 반영 */


/** 2019-03-13 양소영 */
UPDATE TAPGPAGE SET key_no='monthly', key_type='LABEL' WHERE page_id=(SELECT page_id FROM TAPAGE WHERE file_name='workRptPmiCmptRateList') AND c_page_id=(SELECT page_id FROM TAPAGE WHERE file_name='workRptPmiCmptDetailList');



/** 2019-03-13 양소영 */
UPDATE TAPGPAGE SET key_no='monthly', key_type='LABEL' WHERE page_id=(SELECT page_id FROM TAPAGE WHERE file_name='workRptPmiEquipPlanRateList') AND c_page_id=(SELECT page_id FROM TAPAGE WHERE file_name='workRptPmiEquipPlanDetailList');

/** 2019-03-19 매일유업 반영 */
/** 2019-04-04 매일유업 반영 */
/** 2019-04-15 고려용접봉 반영 */
/** 2019-04-17 국도화학 반영 */
/** 2019-04-17 김은아 */
update TAPGPAGE set CRE_DATE = '20190327132010';
update TAPGPAGE set UPD_DATE = '20190327132010';

/** 2019-04-18 매일유업 반영 */
/** 2019-04-26 매일유업 반영 */


/** 2019-04-26 이지수 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListDeptSchedList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListDeptSchedListDeptList') ,'110','Y','TAB','workListDeptSchedListDeptList','workListDeptSchedList','workListDeptSchedListDeptList'); 


/** 2019-04-15 이근환 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME, REMARK) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListWeekWoList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmInsMstrDetail') ,'10','N','LABEL','woNo','workListWeekWoList','maWoResultPmInsMstrDetail','');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME, REMARK) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListWeekWoList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultCmRplMstrDetail') ,'100','N','LABEL','woNo','workListWeekWoList','maWoResultCmRplMstrDetail','');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME, REMARK) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListWeekWoList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultTrEleMstrDetail') ,'110','N','LABEL','woNo','workListWeekWoList','maWoResultTrEleMstrDetail','');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME, REMARK) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListWeekWoList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmGmMstrDetail') ,'120','N','LABEL','woNo','workListWeekWoList','maWoResultPmGmMstrDetail','');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME, REMARK) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListWeekWoList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmSclMstrDetail') ,'140','N','LABEL','woNo','workListWeekWoList','maWoResultPmSclMstrDetail','');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME, REMARK) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListWeekWoList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmPrsMstrDetail') ,'150','N','LABEL','woNo','workListWeekWoList','maWoResultPmPrsMstrDetail','');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME, REMARK) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListWeekWoList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmGnlMstrDetail') ,'160','N','LABEL','woNo','workListWeekWoList','maWoResultPmGnlMstrDetail','');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME, REMARK) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListWeekWoList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultCmLocBaseMstrDetail') ,'170','N','LABEL','woNo','workListWeekWoList','maWoResultCmLocBaseMstrDetail','');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME, REMARK) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListWeekWoList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmRprMstrDetail') ,'20','N','LABEL','woNo','workListWeekWoList','maWoResultPmRprMstrDetail','');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME, REMARK) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListWeekWoList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmRplMstrDetail') ,'30','N','LABEL','woNo','workListWeekWoList','maWoResultPmRplMstrDetail','');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME, REMARK) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListWeekWoList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmClnMstrDetail') ,'40','N','LABEL','woNo','workListWeekWoList','maWoResultPmClnMstrDetail','');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME, REMARK) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListWeekWoList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmOilMstrDetail') ,'50','N','LABEL','woNo','workListWeekWoList','maWoResultPmOilMstrDetail','');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME, REMARK) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListWeekWoList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultBmRprMstrDetail') ,'60','N','LABEL','woNo','workListWeekWoList','maWoResultBmRprMstrDetail','');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME, REMARK) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListWeekWoList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultBmRplMstrDetail') ,'70','N','LABEL','woNo','workListWeekWoList','maWoResultBmRplMstrDetail','');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME, REMARK) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListWeekWoList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultBmOilMstrDetail') ,'80','N','LABEL','woNo','workListWeekWoList','maWoResultBmOilMstrDetail','');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME, REMARK) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListWeekWoList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultCmRprMstrDetail') ,'90','N','LABEL','woNo','workListWeekWoList','maWoResultCmRprMstrDetail','');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME, REMARK) 
VALUES( NEXT VALUE FOR sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListWeekWoList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maWoResultPmCalEtcMstrDetail') ,'95','N','LABEL','woNo','workListWeekWoList','maWoResultPmCalEtcMstrDetail','');

/** 2019-05-08 매일유업 반영 */
/** 2019-05-10 국도화학 반영 */


/** 2019-04-12 이지수 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME) 
VALUES( NEXT VALUE FOR  sqapgpage_id , (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListUInsList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListUInsDetail') ,'10','N','TAB','workPmListUInsDetail','workPmListUInsList','workPmListUInsDetail'); 
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME, REMARK) 
VALUES( NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListUInsDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmPointUInsList') ,'10','N','TAB','workPmPointUInsList','workPmListUInsDetail','workPmPointUInsList','측정기준의 실측항목을 관리');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME, REMARK) 
VALUES( NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmPointUInsList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmPointUInsDetail') ,'10','N','TAB','workPmPointUInsDetail','workPmPointUInsList','workPmPointUInsDetail','측정기준의 실측항목 리스트이 상세화면');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME, REMARK) 
VALUES( NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListUInsDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmMsTimeUInsList') ,'20','N','TAB','workPmMsTimeUInsList','workPmListUInsDetail','workPmMsTimeUInsList','측정이 일일 1회 이상이면 실행할 시간을 관리');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME, REMARK) 
VALUES( NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmMsTimeUInsList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmMsTimeUInsDetail') ,'10','N','TAB','workPmMsTimeUInsDetail','workPmMsTimeUInsList','workPmMsTimeUInsDetail','측정기준을 결재를 통해서 완료할 경우 결재이력');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME, REMARK) 
VALUES( NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workPmListUInsDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'maAppPrcList') ,'30','N','LABEL','appByHist','workPmListUInsDetail','maAppPrcList','결재이력을 조회');
update tapgpage set key_type = 'TAB', key_no = 'workPmCheckDetail' where page_id = (select page_id from tapage where file_name = 'workPmCheckList') and key_no = 'process';



/** 2019-04-29 양소영 */
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME, REMARK) VALUES( NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListEnergyMstrList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListEnergyMstrDetail') ,'10','Y',UPPER('TAB'),UPPER('workListEnergyMstrDetail'),'workListEnergyMstrList','workListEnergyMstrDetail','결과값');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME, REMARK) VALUES( NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListEnergyMstrDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListEnergyPointList') ,'10','Y',UPPER('TAB'),UPPER('workListEnergyPointList'),'workListEnergyMstrDetail','workListEnergyPointList','측정항목');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME, REMARK) VALUES( NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListEnergyPointList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListEnergyPointDetail') ,'10','Y',UPPER('TAB'),UPPER('workListEnergyPointDetail'),'workListEnergyPointList','workListEnergyPointDetail','측정항목');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME, REMARK) VALUES( NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListEnergyPointDetail') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListEnergyPointDocLibList') ,'10','Y',UPPER('TAB'),UPPER('workListEnergyPointDocLibList'),'workListEnergyPointDetail','workListEnergyPointDocLibList','첨부문서');
INSERT INTO TAPGPAGE (PGPAGE_ID, PAGE_ID, C_PAGE_ID, ORD_NO, IS_USE, KEY_TYPE, KEY_NO, FILE_NAME, C_FILE_NAME, REMARK) VALUES( NEXT VALUE FOR sqapgpage_id, (SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListEnergyPointDocLibList') ,(SELECT aa.page_id FROM TAPAGE aa WHERE aa.file_name = 'workListEnergyPointDocLibDetail') ,'10','Y',UPPER('TAB'),UPPER('workListEnergyPointDocLibDetail'),'workListEnergyPointDocLibList','workListEnergyPointDocLibDetail','첨부문서');


/** 2019-04-30 이근환 */
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)
VALUES (NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name = 'mgrUsrGrpPageAuthList'), (select page_id from TAPAGE where file_name =  'mgrUsrGrpPageAuthDetail'),  '110',  'N',  'TAB',  'mgrUsrGrpPageAuthDetail',  'mgrUsrGrpPageAuthList',  'mgrUsrGrpPageAuthDetail',  'DREAM',  '2.01',  'TAB',  'mgrUsrGrpPageAuthDetail', CONVERT(nvarchar,GETDATE(),112)+REPLACE(CONVERT(nvarchar,GETDATE(),108),':',''), CONVERT(nvarchar,GETDATE(),112)+REPLACE(CONVERT(nvarchar,GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)
VALUES (NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name = 'mgrUsrGrpPageAuthDetail'), (select page_id from TAPAGE where file_name =  'mgrUsrGrpPageAuthBtnList'),  '110',  'N',  'TAB',  'mgrUsrGrpPageAuthBtnList',  'mgrUsrGrpPageAuthDetail',  'mgrUsrGrpPageAuthBtnList',  'DREAM',  '2.01',  'TAB',  'mgrUsrGrpPageAuthBtnList', CONVERT(nvarchar,GETDATE(),112)+REPLACE(CONVERT(nvarchar,GETDATE(),108),':',''), CONVERT(nvarchar,GETDATE(),112)+REPLACE(CONVERT(nvarchar,GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)
VALUES (NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name = 'mgrUsrGrpPageAuthDetail'), (select page_id from TAPAGE where file_name =  'mgrUsrGrpPageAuthTabList'),  '120',  'N',  'TAB',  'mgrUsrGrpPageAuthTabList',  'mgrUsrGrpPageAuthDetail',  'mgrUsrGrpPageAuthTabList',  'DREAM',  '2.01',  'TAB',  'mgrUsrGrpPageAuthTabList', CONVERT(nvarchar,GETDATE(),112)+REPLACE(CONVERT(nvarchar,GETDATE(),108),':',''), CONVERT(nvarchar,GETDATE(),112)+REPLACE(CONVERT(nvarchar,GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)
VALUES (NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name = 'mgrUsrGrpPageAuthBtnList'), (select page_id from TAPAGE where file_name =  'mgrUsrGrpPageAuthBtnDetail'),  '110',  'N',  'TAB',  'mgrUsrGrpPageAuthBtnDetail',  'mgrUsrGrpPageAuthBtnList',  'mgrUsrGrpPageAuthBtnDetail',  'DREAM',  '2.01',  'TAB',  'mgrUsrGrpPageAuthBtnDetail', CONVERT(nvarchar,GETDATE(),112)+REPLACE(CONVERT(nvarchar,GETDATE(),108),':',''), CONVERT(nvarchar,GETDATE(),112)+REPLACE(CONVERT(nvarchar,GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)
VALUES (NEXT VALUE FOR sqapgpage_id, (select page_id from tapage where file_name = 'mgrUsrGrpPageAuthTabList'), (select page_id from TAPAGE where file_name =  'mgrUsrGrpPageAuthTabDetail'),  '110',  'N',  'TAB',  'mgrUsrGrpPageAuthTabDetail',  'mgrUsrGrpPageAuthTabList',  'mgrUsrGrpPageAuthTabDetail',  'DREAM',  '2.01',  'TAB',  'mgrUsrGrpPageAuthTabDetail', CONVERT(nvarchar,GETDATE(),112)+REPLACE(CONVERT(nvarchar,GETDATE(),108),':',''), CONVERT(nvarchar,GETDATE(),112)+REPLACE(CONVERT(nvarchar,GETDATE(),108),':',''));


/** 2019-05-14 노정현  */
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)
VALUES (next value for SQAPGPAGE_ID, (select page_id from tapage where file_name = 'consultCompUsrGrpList'), (select page_id from TAPAGE where file_name =  replace('consultCompUsrGrpDetail', ' ', '')),  replace('110', ' ',''),  replace('N',' ',''),  replace('TAB',' ',''),  replace('consultCompUsrGrpDetail',' ',''),  replace('consultCompUsrGrpList',' ',''),  replace('consultCompUsrGrpDetail',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('consultCompUsrGrpDetail', ' ',''),convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)
VALUES (next value for SQAPGPAGE_ID, (select page_id from tapage where file_name = 'consultCompUsrGrpDetail'), (select page_id from TAPAGE where file_name =  replace('consultCompUsrGrpWebAuthList', ' ', '')),  replace('110', ' ',''),  replace('N',' ',''),  replace('TAB',' ',''),  replace('consultCompUsrGrpWebAuthList',' ',''),  replace('consultCompUsrGrpDetail',' ',''),  replace('consultCompUsrGrpWebAuthList',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('consultCompUsrGrpWebAuthList', ' ',''),convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)
VALUES (next value for SQAPGPAGE_ID, (select page_id from tapage where file_name = 'consultCompUsrGrpDetail'), (select page_id from TAPAGE where file_name =  replace('consultCompUsrGrpBeeAuthList', ' ', '')),  replace('120', ' ',''),  replace('N',' ',''),  replace('TAB',' ',''),  replace('consultCompUsrGrpBeeAuthList',' ',''),  replace('consultCompUsrGrpDetail',' ',''),  replace('consultCompUsrGrpBeeAuthList',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('consultCompUsrGrpBeeAuthList', ' ',''),convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121));

/** 2019-05-21 이지수 */
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)
VALUES (next value for SQAPGPAGE_ID, (select page_id from TAPAGE where file_name = 'workPmiPointValueDetail'), (select page_id from TAPAGE where file_name =  'workPmiRInsPointValueDocLibList'),  '920',  'Y',  'TAB',  'maDocLibList',  'workPmiPointValueDetail',  'workPmiRInsPointValueDocLibList',  'DREAM',  '2.01',  'TAB',  'maDocLibList', CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''), CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)
VALUES (next value for SQAPGPAGE_ID, (select page_id from TAPAGE where file_name = 'workPmiPointList'), (select page_id from TAPAGE where file_name =  'workPmiPointValueDetail'),  '120',  'Y',  'LABEL',  'checkPoint',  'workPmiPointList',  'workPmiPointValueDetail',  'DREAM',  '2.01',  'TAB',  'checkPoint', CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''), CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)
VALUES (next value for SQAPGPAGE_ID, (select page_id from TAPAGE where file_name = 'workPmiRInsPointValueDocLibList'), (select page_id from TAPAGE where file_name =  'workPmiRInsPointValueDocLibDetail'),  '110',  'Y',  'TAB',  'maDocLibDetail',  'workPmiRInsPointValueDocLibList',  'workPmiRInsPointValueDocLibDetail',  'DREAM',  '2.01',  'TAB',  'maDocLibDetail', CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''), CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));

/** 2019-05-24 국도화학 반영 */
/** 2019-05-24 고려용접봉 반영 */



/** 2019-05-24 노정현  */

DELETE  FROM  TAPGPAGE WHERE c_page_id  IN (SELECT page_id FROM TAPAGE WHERE file_name IN ('consultCompUsrGrpDetail','consultCompUsrGrpWebAuthList','consultCompUsrGrpBeeAuthList'));

INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)
VALUES (next value for SQAPGPAGE_ID, (select page_id from tapage where file_name = 'consultCompUsrGrpList'), (select page_id from TAPAGE where file_name =  replace('consultCompUsrGrpDetail', ' ', '')),  replace('110', ' ',''),  replace('N',' ',''),  replace('TAB',' ',''),  replace('consultCompUsrGrpDetail',' ',''),  replace('consultCompUsrGrpList',' ',''),  replace('consultCompUsrGrpDetail',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('consultCompUsrGrpDetail', ' ',''),convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)
VALUES (next value for SQAPGPAGE_ID, (select page_id from tapage where file_name = 'consultCompUsrGrpDetail'), (select page_id from TAPAGE where file_name =  replace('consultCompUsrGrpWebAuthList', ' ', '')),  replace('110', ' ',''),  replace('N',' ',''),  replace('TAB',' ',''),  replace('consultCompUsrGrpWebAuthList',' ',''),  replace('consultCompUsrGrpDetail',' ',''),  replace('consultCompUsrGrpWebAuthList',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('consultCompUsrGrpWebAuthList', ' ',''),convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)
VALUES (next value for SQAPGPAGE_ID, (select page_id from tapage where file_name = 'consultCompUsrGrpDetail'), (select page_id from TAPAGE where file_name =  replace('consultCompUsrGrpBeeAuthList', ' ', '')),  replace('120', ' ',''),  replace('N',' ',''),  replace('TAB',' ',''),  replace('consultCompUsrGrpBeeAuthList',' ',''),  replace('consultCompUsrGrpDetail',' ',''),  replace('consultCompUsrGrpBeeAuthList',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('consultCompUsrGrpBeeAuthList', ' ',''),convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121));

/** 2019-05-29 고려용접봉 반영 */
/** 2019-05-30 매일유업 반영 */
/** 2019-06-04 매일유업 반영 */


/** 2019-05-22 김영주 */
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)VALUES (next value for SQAPGPAGE_ID, (select page_id from tapage where file_name = 'maWoDailyDetail'), (select page_id from TAPAGE where file_name =  replace('maWoDailyImageList', ' ', '')),  replace('140', ' ',''),  replace('Y',' ',''),  replace('TAB',' ',''),  replace('maWoDailyImageList',' ',''),  replace('maWoDailyDetail',' ',''),  replace('maWoDailyImageList',' ',''),  replace('',' ',''),  replace('',' ',''),  replace('TAB',' ',''),  replace('maWoDailyImageList', ' ',''),convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)VALUES (next value for SQAPGPAGE_ID, (select page_id from tapage where file_name = 'maWoDailyDetail'), (select page_id from TAPAGE where file_name =  replace('maWoDailyLibList', ' ', '')),  replace('150', ' ',''),  replace('Y',' ',''),  replace('TAB',' ',''),  replace('maDocLibList',' ',''),  replace('maWoDailyDetail',' ',''),  replace('maWoDailyLibList',' ',''),  replace('',' ',''),  replace('',' ',''),  replace('TAB',' ',''),  replace('maDocLibList', ' ',''),convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)VALUES (next value for SQAPGPAGE_ID, (select page_id from tapage where file_name = 'maWoDailyWoList'), (select page_id from TAPAGE where file_name =  replace('maWoResultPmInsMstrDetail', ' ', '')),  replace('10', ' ',''),  replace('Y',' ',''),  replace('LABEL',' ',''),  replace('pmNo',' ',''),  replace('maWoDailyWoList',' ',''),  replace('maWoResultPmInsMstrDetail',' ',''),  replace('',' ',''),  replace('',' ',''),  replace('LABEL',' ',''),  replace('pmNo', ' ',''),convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)VALUES (next value for SQAPGPAGE_ID, (select page_id from tapage where file_name = 'maWoDailyWoList'), (select page_id from TAPAGE where file_name =  replace('maWoResultPmRprMstrDetail', ' ', '')),  replace('20', ' ',''),  replace('Y',' ',''),  replace('LABEL',' ',''),  replace('pmNo',' ',''),  replace('maWoDailyWoList',' ',''),  replace('maWoResultPmRprMstrDetail',' ',''),  replace('',' ',''),  replace('',' ',''),  replace('LABEL',' ',''),  replace('pmNo', ' ',''),convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)VALUES (next value for SQAPGPAGE_ID, (select page_id from tapage where file_name = 'maWoDailyWoList'), (select page_id from TAPAGE where file_name =  replace('maWoResultPmRplMstrDetail', ' ', '')),  replace('30', ' ',''),  replace('Y',' ',''),  replace('LABEL',' ',''),  replace('pmNo',' ',''),  replace('maWoDailyWoList',' ',''),  replace('maWoResultPmRplMstrDetail',' ',''),  replace('',' ',''),  replace('',' ',''),  replace('LABEL',' ',''),  replace('pmNo', ' ',''),convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)VALUES (next value for SQAPGPAGE_ID, (select page_id from tapage where file_name = 'maWoDailyWoList'), (select page_id from TAPAGE where file_name =  replace('maWoResultPmClnMstrDetail', ' ', '')),  replace('40', ' ',''),  replace('Y',' ',''),  replace('LABEL',' ',''),  replace('pmNo',' ',''),  replace('maWoDailyWoList',' ',''),  replace('maWoResultPmClnMstrDetail',' ',''),  replace('',' ',''),  replace('',' ',''),  replace('LABEL',' ',''),  replace('pmNo', ' ',''),convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)VALUES (next value for SQAPGPAGE_ID, (select page_id from tapage where file_name = 'maWoDailyWoList'), (select page_id from TAPAGE where file_name =  replace('maWoResultPmOilMstrDetail', ' ', '')),  replace('50', ' ',''),  replace('Y',' ',''),  replace('LABEL',' ',''),  replace('pmNo',' ',''),  replace('maWoDailyWoList',' ',''),  replace('maWoResultPmOilMstrDetail',' ',''),  replace('',' ',''),  replace('',' ',''),  replace('LABEL',' ',''),  replace('pmNo', ' ',''),convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)VALUES (next value for SQAPGPAGE_ID, (select page_id from tapage where file_name = 'maWoDailyWoList'), (select page_id from TAPAGE where file_name =  replace('maWoResultBmRprMstrDetail', ' ', '')),  replace('60', ' ',''),  replace('Y',' ',''),  replace('LABEL',' ',''),  replace('pmNo',' ',''),  replace('maWoDailyWoList',' ',''),  replace('maWoResultBmRprMstrDetail',' ',''),  replace('',' ',''),  replace('',' ',''),  replace('LABEL',' ',''),  replace('pmNo', ' ',''),convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)VALUES (next value for SQAPGPAGE_ID, (select page_id from tapage where file_name = 'maWoDailyWoList'), (select page_id from TAPAGE where file_name =  replace('maWoResultBmRplMstrDetail', ' ', '')),  replace('70', ' ',''),  replace('Y',' ',''),  replace('LABEL',' ',''),  replace('pmNo',' ',''),  replace('maWoDailyWoList',' ',''),  replace('maWoResultBmRplMstrDetail',' ',''),  replace('',' ',''),  replace('',' ',''),  replace('LABEL',' ',''),  replace('pmNo', ' ',''),convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)VALUES (next value for SQAPGPAGE_ID, (select page_id from tapage where file_name = 'maWoDailyWoList'), (select page_id from TAPAGE where file_name =  replace('maWoResultBmOilMstrDetail', ' ', '')),  replace('80', ' ',''),  replace('Y',' ',''),  replace('LABEL',' ',''),  replace('pmNo',' ',''),  replace('maWoDailyWoList',' ',''),  replace('maWoResultBmOilMstrDetail',' ',''),  replace('',' ',''),  replace('',' ',''),  replace('LABEL',' ',''),  replace('pmNo', ' ',''),convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)VALUES (next value for SQAPGPAGE_ID, (select page_id from tapage where file_name = 'maWoDailyWoList'), (select page_id from TAPAGE where file_name =  replace('maWoResultCmRprMstrDetail', ' ', '')),  replace('90', ' ',''),  replace('Y',' ',''),  replace('LABEL',' ',''),  replace('pmNo',' ',''),  replace('maWoDailyWoList',' ',''),  replace('maWoResultCmRprMstrDetail',' ',''),  replace('',' ',''),  replace('',' ',''),  replace('LABEL',' ',''),  replace('pmNo', ' ',''),convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)VALUES (next value for SQAPGPAGE_ID, (select page_id from tapage where file_name = 'maWoDailyWoList'), (select page_id from TAPAGE where file_name =  replace('maWoResultPmCalEtcMstrDetail', ' ', '')),  replace('95', ' ',''),  replace('Y',' ',''),  replace('LABEL',' ',''),  replace('pmNo',' ',''),  replace('maWoDailyWoList',' ',''),  replace('maWoResultPmCalEtcMstrDetail',' ',''),  replace('',' ',''),  replace('',' ',''),  replace('LABEL',' ',''),  replace('pmNo', ' ',''),convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)VALUES (next value for SQAPGPAGE_ID, (select page_id from tapage where file_name = 'maWoDailyWoList'), (select page_id from TAPAGE where file_name =  replace('maWoResultCmRplMstrDetail', ' ', '')),  replace('100', ' ',''),  replace('Y',' ',''),  replace('LABEL',' ',''),  replace('pmNo',' ',''),  replace('maWoDailyWoList',' ',''),  replace('maWoResultCmRplMstrDetail',' ',''),  replace('',' ',''),  replace('',' ',''),  replace('LABEL',' ',''),  replace('pmNo', ' ',''),convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)VALUES (next value for SQAPGPAGE_ID, (select page_id from tapage where file_name = 'maWoDailyWoList'), (select page_id from TAPAGE where file_name =  replace('maWoResultTrEleMstrDetail', ' ', '')),  replace('110', ' ',''),  replace('Y',' ',''),  replace('LABEL',' ',''),  replace('pmNo',' ',''),  replace('maWoDailyWoList',' ',''),  replace('maWoResultTrEleMstrDetail',' ',''),  replace('',' ',''),  replace('',' ',''),  replace('LABEL',' ',''),  replace('pmNo', ' ',''),convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)VALUES (next value for SQAPGPAGE_ID, (select page_id from tapage where file_name = 'maWoDailyWoList'), (select page_id from TAPAGE where file_name =  replace('maWoResultPmGmMstrDetail', ' ', '')),  replace('120', ' ',''),  replace('Y',' ',''),  replace('LABEL',' ',''),  replace('pmNo',' ',''),  replace('maWoDailyWoList',' ',''),  replace('maWoResultPmGmMstrDetail',' ',''),  replace('',' ',''),  replace('',' ',''),  replace('LABEL',' ',''),  replace('pmNo', ' ',''),convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)VALUES (next value for SQAPGPAGE_ID, (select page_id from tapage where file_name = 'maWoDailyWoList'), (select page_id from TAPAGE where file_name =  replace('maWoResultPmSclMstrDetail', ' ', '')),  replace('140', ' ',''),  replace('Y',' ',''),  replace('LABEL',' ',''),  replace('pmNo',' ',''),  replace('maWoDailyWoList',' ',''),  replace('maWoResultPmSclMstrDetail',' ',''),  replace('',' ',''),  replace('',' ',''),  replace('LABEL',' ',''),  replace('pmNo', ' ',''),convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)VALUES (next value for SQAPGPAGE_ID, (select page_id from tapage where file_name = 'maWoDailyWoList'), (select page_id from TAPAGE where file_name =  replace('maWoResultPmPrsMstrDetail', ' ', '')),  replace('150', ' ',''),  replace('Y',' ',''),  replace('LABEL',' ',''),  replace('pmNo',' ',''),  replace('maWoDailyWoList',' ',''),  replace('maWoResultPmPrsMstrDetail',' ',''),  replace('',' ',''),  replace('',' ',''),  replace('LABEL',' ',''),  replace('pmNo', ' ',''),convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)VALUES (next value for SQAPGPAGE_ID, (select page_id from tapage where file_name = 'maWoDailyWoList'), (select page_id from TAPAGE where file_name =  replace('maWoResultPmGnlMstrDetail', ' ', '')),  replace('160', ' ',''),  replace('Y',' ',''),  replace('LABEL',' ',''),  replace('pmNo',' ',''),  replace('maWoDailyWoList',' ',''),  replace('maWoResultPmGnlMstrDetail',' ',''),  replace('',' ',''),  replace('',' ',''),  replace('LABEL',' ',''),  replace('pmNo', ' ',''),convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)VALUES (next value for SQAPGPAGE_ID, (select page_id from tapage where file_name = 'maWoDailyWoList'), (select page_id from TAPAGE where file_name =  replace('maWoResultCmLocBaseMstrDetail', ' ', '')),  replace('170', ' ',''),  replace('Y',' ',''),  replace('LABEL',' ',''),  replace('pmNo',' ',''),  replace('maWoDailyWoList',' ',''),  replace('maWoResultCmLocBaseMstrDetail',' ',''),  replace('',' ',''),  replace('',' ',''),  replace('LABEL',' ',''),  replace('pmNo', ' ',''),convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)VALUES (next value for SQAPGPAGE_ID, (select page_id from tapage where file_name = 'maWoDailyPmiList'), (select page_id from TAPAGE where file_name =  replace('workPmiDetail', ' ', '')),  replace('110', ' ',''),  replace('Y',' ',''),  replace('LABEL',' ',''),  replace('pmNo',' ',''),  replace('maWoDailyPmiList',' ',''),  replace('workPmiDetail',' ',''),  replace('',' ',''),  replace('',' ',''),  replace('LABEL',' ',''),  replace('pmNo', ' ',''),convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)VALUES (next value for SQAPGPAGE_ID, (select page_id from tapage where file_name = 'maWoDailyPmiList'), (select page_id from TAPAGE where file_name =  replace('workCalPmRInsPeriodDetail', ' ', '')),  replace('120', ' ',''),  replace('Y',' ',''),  replace('LABEL',' ',''),  replace('pmNo',' ',''),  replace('maWoDailyPmiList',' ',''),  replace('workCalPmRInsPeriodDetail',' ',''),  replace('',' ',''),  replace('',' ',''),  replace('LABEL',' ',''),  replace('pmNo', ' ',''),convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)VALUES (next value for SQAPGPAGE_ID, (select page_id from tapage where file_name = 'maWoDailyLibList'), (select page_id from TAPAGE where file_name =  replace('maWoDailyLibDetail', ' ', '')),  replace('110', ' ',''),  replace('Y',' ',''),  replace('TAB',' ',''),  replace('maDocLibDetail',' ',''),  replace('maWoDailyLibList',' ',''),  replace('maWoDailyLibDetail',' ',''),  replace('',' ',''),  replace('',' ',''),  replace('TAB',' ',''),  replace('maDocLibDetail', ' ',''),convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121));

/** 2019-06-14 김남현  */
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID, (select page_id from tapage where file_name = 'maMyInfo'), (select page_id from TAPAGE where file_name =  replace('persPrivNoticeList', ' ', '')),  replace('140', ' ',''),  replace('N',' ',''),  replace('TAB',' ',''),  replace('persPrivNoticeList',' ',''),  replace('maMyInfo',' ',''),  replace('persPrivNoticeList',' ',''),  replace('',' ',''),  replace('',' ',''),  replace('TAB',' ',''),  replace('persPrivNoticeList', ' ',''),convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPgPAGE_ID, (select page_id from tapage where file_name = 'persPrivNoticeList'), (select page_id from TAPAGE where file_name =  replace('persPrivNoticeDetail', ' ', '')),  replace('110', ' ',''),  replace('N',' ',''),  replace('TAB',' ',''),  replace('persPrivNoticeDetail',' ',''),  replace('persPrivNoticeList',' ',''),  replace('persPrivNoticeDetail',' ',''),  replace('',' ',''),  replace('',' ',''),  replace('TAB',' ',''),  replace('persPrivNoticeDetail', ' ',''),convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121));

/** 2019-06-17 경보제약 반영 */
/** 2019-06-17 고려용접봉 반영 */
/** 2019-06-26 고려용접봉 반영 */
/** 2019-06-26 국도화학 반영 */
/** 2019-06-26 매일유업 반영 */


/** 2019-06-04 양소영 */
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date)
VALUES (NEXT VALUE FOR SQAPGPAGE_ID, (SELECT page_id FROM TAPAGE WHERE file_name = 'assetRptLccUsDeptList'), (SELECT page_id FROM TAPAGE WHERE file_name = 'assetRptLccUsDeptMaintAmtDetailChart'),  '40',  'N',  'TAB',  'assetRptLccUsDeptMaintAmtDetailChart',  'assetRptLccUsDeptList',  'assetRptLccUsDeptMaintAmtDetailChart',  '',  '',  'TAB',  'assetRptLccUsDeptMaintAmtDetailChart', CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''), CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));

/** 2019-06-28 매일유업 반영 */
/** 2019-07-10 경보제약 반영 */
/** 2019-07-18 경보제약 반영 */
/** 2019-07-25 경보제약 반영 */
/** 2019-07-30 매일유업 반영 */
/** 2019-08-07 경보제약 반영 */


/** 2019-08-07 김남현 */
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='workAlarmList') ,  (select page_id from tapage where file_name='workAlarmDetail') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('workAlarmDetail',' ',''),  replace('workAlarmList',' ',''),  replace('workAlarmDetail',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('workAlarmDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='workAlarmDetail') ,  (select page_id from tapage where file_name='workAlarmReqList') ,  replace('710', ' ',''),  'N',  replace('TAB',' ',''),  replace('workAlarmReqList',' ',''),  replace('workAlarmDetail',' ',''),  replace('workAlarmReqList',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('workAlarmReqList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='workAlarmReqList') ,  (select page_id from tapage where file_name='maWoReqDetail') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('maWoReqDetail',' ',''),  replace('workAlarmReqList',' ',''),  replace('maWoReqDetail',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('maWoReqDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));


/** 291 이지수 */
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='workRptWorkTypeRptByEmpList') ,  (select page_id from tapage where file_name='workRptWorkTypeRptByEmpMonth') ,  replace('110', ' ',''),  'Y',  replace('TAB',' ',''),  replace('workRptWorkTypeRptByEmpMonth',' ',''),  replace('workRptWorkTypeRptByEmpList',' ',''),  replace('workRptWorkTypeRptByEmpMonth',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('workRptWorkTypeRptByEmpMonth', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='workRptWorkTypeRptByEmpList') ,  (select page_id from tapage where file_name='workRptWorkTypeRptByEmpWoType') ,  replace('120', ' ',''),  'Y',  replace('TAB',' ',''),  replace('workRptWorkTypeRptByEmpWoType',' ',''),  replace('workRptWorkTypeRptByEmpList',' ',''),  replace('workRptWorkTypeRptByEmpWoType',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('workRptWorkTypeRptByEmpWoType', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));


/** 2019-08-09 경보제약 반영 */
/** 2019-08-12 경보제약 반영 */


/** 2019-07-11 이근환 */
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='consultPgmSettingUpdList') ,  (select page_id from tapage where file_name='consultPgmSettingUpdDetail') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('consultPgmSettingUpdDetail',' ',''),  replace('consultPgmSettingUpdList',' ',''),  replace('consultPgmSettingUpdDetail',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('consultPgmSettingUpdDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='consultProgramUploadDataDetail') ,  (select page_id from tapage where file_name='consultProgramUploadDataScriptList') ,  replace('120', ' ',''),  'N',  replace('TAB',' ',''),  replace('consultProgramUploadDataScriptList',' ',''),  replace('consultProgramUploadDataDetail',' ',''),  replace('consultProgramUploadDataScriptList',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('consultProgramUploadDataScriptList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='consultProgramUploadDataScriptList') ,  (select page_id from tapage where file_name='consultProgramUploadDataScriptDetail') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('consultProgramUploadDataScriptDetail',' ',''),  replace('consultProgramUploadDataScriptList',' ',''),  replace('consultProgramUploadDataScriptDetail',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('consultProgramUploadDataScriptDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));

/** 2019-08-13 경보제약 반영 */
/** 2019-08-19 경보제약 반영 */
/** 2019-08-22 경보제약 반영 */
/** 297 영주 */
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maEqMstrPmInsPointList') ,  (select page_id from tapage where file_name='maPmMstrPointDetail') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('maPmMstrPointDetail',' ',''),  replace('maEqMstrPmInsPointList',' ',''),  replace('maPmMstrPointDetail',' ',''),  replace('DREAM',' ',''),  replace('2.50',' ',''),  replace('TAB',' ',''),  replace('maPmMstrPointDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maEqMstrPmInsPointList') ,  (select page_id from tapage where file_name='maPmMstrPointValueDetail') ,  replace('120', ' ',''),  'N',  replace('TAB',' ',''),  replace('maPmMstrPointValueDetail',' ',''),  replace('maEqMstrPmInsPointList',' ',''),  replace('maPmMstrPointValueDetail',' ',''),  replace('DREAM',' ',''),  replace('2.50',' ',''),  replace('TAB',' ',''),  replace('maPmMstrPointValueDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));



/** 2019-08-23 국도화학 반영 */
/** 2019-08-26 본사 경보제약 반영 */
/** 2019-08-27 경보제약 반영 */

/** youngjoo38_306 김영주 */
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (NEXT VALUE FOR SQAPGPAGE_ID,  (SELECT page_id FROM TAPAGE WHERE file_name='maCdSysDetail') ,  (SELECT page_id FROM TAPAGE WHERE file_name='consultCdSysFieldList') ,  REPLACE('120', ' ',''),  REPLACE('Y',' ',''),  REPLACE('TAB',' ',''),  REPLACE('consultCdSysFieldList',' ',''),  REPLACE('maCdSysDetail',' ',''),  REPLACE('consultCdSysFieldList',' ',''),  REPLACE('DREAM',' ',''),  REPLACE('2.01',' ',''),  REPLACE('TAB',' ',''),  REPLACE('consultCdSysFieldList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));

INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='mgrCdSysList') ,  (select page_id from tapage where file_name='mgrCdSysDetail') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('mgrCdSysDetail',' ',''),  replace('mgrCdSysList',' ',''),  replace('mgrCdSysDetail',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('mgrCdSysDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='mgrCdSysDetail') ,  (select page_id from tapage where file_name='mgrCdSysCodeList') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('mgrCdSysCodeList',' ',''),  replace('mgrCdSysDetail',' ',''),  replace('mgrCdSysCodeList',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('mgrCdSysCodeList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='mgrCdSysDetail') ,  (select page_id from tapage where file_name='mgrCdSysFieldList') ,  replace('120', ' ',''),  'N',  replace('TAB',' ',''),  replace('mgrCdSysFieldList',' ',''),  replace('mgrCdSysDetail',' ',''),  replace('mgrCdSysFieldList',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('mgrCdSysFieldList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='mgrCdSysCodeList') ,  (select page_id from tapage where file_name='mgrCdSysCodeDetail') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('mgrCdSysCodeDetail',' ',''),  replace('mgrCdSysCodeList',' ',''),  replace('mgrCdSysCodeDetail',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('mgrCdSysCodeDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));

/** 2019-08-29 매일유업 반영 */
/** 2019-09-05 경보제약 반영 */
/** 2019-09-11 경보제약 반영 */

/* 277 영주 */
update tapgpage set is_use = 'Y', ord_no = '110', key_type = 'TAB', key_no= 'maStdWorkDetail' ,basic_key_type='TAB',basic_key_no='maStdWorkDetail',version_info='',site_type='' where page_id= (select page_id from tapage where file_name='maStdWorkList')  and c_page_id =  (select page_id from tapage where file_name='maStdWorkDetail')  ;
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='mgrWorkCloseCheckList') ,  (select page_id from tapage where file_name='mgrWorkCloseCheckDetail') ,  replace('110', ' ',''),  replace('Y',' ',''),  replace('TAB',' ',''),  replace('mgrWorkCloseCheckDetail',' ',''),  replace('mgrWorkCloseCheckList',' ',''),  replace('mgrWorkCloseCheckDetail',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('mgrWorkCloseCheckDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='mgrWorkCloseCheckDetail') ,  (select page_id from tapage where file_name='mgrWorkCloseCheckPointList') ,  replace('110', ' ',''),  replace('Y',' ',''),  replace('TAB',' ',''),  replace('mgrWorkCloseCheckPointList',' ',''),  replace('mgrWorkCloseCheckDetail',' ',''),  replace('mgrWorkCloseCheckPointList',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('mgrWorkCloseCheckPointList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='mgrWorkCloseCheckDetail') ,  (select page_id from tapage where file_name='mgrWorkCloseCheckDocLibList') ,  replace('120', ' ',''),  replace('Y',' ',''),  replace('TAB',' ',''),  replace('mgrWorkCloseCheckDocLibList',' ',''),  replace('mgrWorkCloseCheckDetail',' ',''),  replace('mgrWorkCloseCheckDocLibList',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('mgrWorkCloseCheckDocLibList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='mgrWorkCloseCheckPointList') ,  (select page_id from tapage where file_name='mgrWorkCloseCheckPointDetail') ,  replace('110', ' ',''),  replace('Y',' ',''),  replace('TAB',' ',''),  replace('mgrWorkCloseCheckPointDetail',' ',''),  replace('mgrWorkCloseCheckPointList',' ',''),  replace('mgrWorkCloseCheckPointDetail',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('mgrWorkCloseCheckPointDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='mgrWorkCloseCheckDocLibList') ,  (select page_id from tapage where file_name='mgrWorkCloseCheckDocLibDetail') ,  replace('110', ' ',''),  replace('Y',' ',''),  replace('TAB',' ',''),  replace('mgrWorkCloseCheckDocLibDetail',' ',''),  replace('mgrWorkCloseCheckDocLibList',' ',''),  replace('mgrWorkCloseCheckDocLibDetail',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('mgrWorkCloseCheckDocLibDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));

/** 2019-09-18 본사 국도화학 반영 */
/** 2019-10-01 매일유업 반영 */
/** 2019-10-02 경보제약 반영 */

/** 359 이근환 */
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='invtDetail') ,  (select page_id from tapage where file_name='invtWoRsltList') ,  replace('130', ' ',''),  'N',  replace('TAB',' ',''),  replace('invtWoRsltList',' ',''),  replace('invtDetail',' ',''),  replace('invtWoRsltList',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('invtWoRsltList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='invtWoRsltList') ,  (select page_id from tapage where file_name='maWoResultBmRplMstrDetail') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('maWoResultBmRplMstrDetail',' ',''),  replace('invtWoRsltList',' ',''),  replace('maWoResultBmRplMstrDetail',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('maWoResultBmRplMstrDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='invtWoRsltList') ,  (select page_id from tapage where file_name='maWoResultCmRplMstrDetail') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('maWoResultCmRplMstrDetail',' ',''),  replace('invtWoRsltList',' ',''),  replace('maWoResultCmRplMstrDetail',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('maWoResultCmRplMstrDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='invtWoRsltList') ,  (select page_id from tapage where file_name='maWoResultPmRplMstrDetail') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('maWoResultPmRplMstrDetail',' ',''),  replace('invtWoRsltList',' ',''),  replace('maWoResultPmRplMstrDetail',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('maWoResultPmRplMstrDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='invtWoRsltList') ,  (select page_id from tapage where file_name='maWoResultPmGnlMstrDetail') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('maWoResultPmGnlMstrDetail',' ',''),  replace('invtWoRsltList',' ',''),  replace('maWoResultPmGnlMstrDetail',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('maWoResultPmGnlMstrDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));

/** 531 이근환 */
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='assetRptLccEquipList') ,  (select page_id from tapage where file_name='assetRptLccEquipFailCodeDetailChart') ,  replace('150', ' ',''),  'N',  replace('TAB',' ',''),  replace('assetRptLccEquipFailCodeDetailChart',' ',''),  replace('assetRptLccEquipList',' ',''),  replace('assetRptLccEquipFailCodeDetailChart',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('assetRptLccEquipFailCodeDetailChart', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='assetRptLccLocList') ,  (select page_id from tapage where file_name='assetRptLccLocFailCodeDetailChart') ,  replace('150', ' ',''),  'N',  replace('TAB',' ',''),  replace('assetRptLccLocFailCodeDetailChart',' ',''),  replace('assetRptLccLocList',' ',''),  replace('assetRptLccLocFailCodeDetailChart',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('assetRptLccLocFailCodeDetailChart', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));

/** 2019-10-14 매일유업 반영 */
/** 2019-10-17 매일유업 반영 */

/** youngjoo38_360 + 김영주 */
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='mgrUsageCalSetList') ,  (select page_id from tapage where file_name='mgrUsageCalSetDetail') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('mgrUsageCalSetDetail',' ',''),  replace('mgrUsageCalSetList',' ',''),  replace('mgrUsageCalSetDetail',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('mgrUsageCalSetDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='mgrUsageCalSetDetail') ,  (select page_id from tapage where file_name='mgrUsageCalSetDowSetList') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('mgrUsageCalSetDowSetList',' ',''),  replace('mgrUsageCalSetDetail',' ',''),  replace('mgrUsageCalSetDowSetList',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('mgrUsageCalSetDowSetList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='mgrUsageCalSetDowSetList') ,  (select page_id from tapage where file_name='mgrUsageCalSetDowSetDetail') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('mgrUsageCalSetDowSetDetail',' ',''),  replace('mgrUsageCalSetDowSetList',' ',''),  replace('mgrUsageCalSetDowSetDetail',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('mgrUsageCalSetDowSetDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));

INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='mgrUsageCalSetDayList') ,  (select page_id from tapage where file_name='mgrUsageCalSetDayDetail') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('mgrUsageCalSetDayDetail',' ',''),  replace('mgrUsageCalSetDayList',' ',''),  replace('mgrUsageCalSetDayDetail',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('mgrUsageCalSetDayDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));

/** youngjoo38_333 + 김영주 */
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='consultPgmRptList') ,  (select page_id from tapage where file_name='consultPgmRptDetail') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('consultPgmRptDetail',' ',''),  replace('consultPgmRptList',' ',''),  replace('consultPgmRptDetail',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('consultPgmRptDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='consultPgmRptDetail') ,  (select page_id from tapage where file_name='consultPgmRptFileList') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('consultPgmRptFileList',' ',''),  replace('consultPgmRptDetail',' ',''),  replace('consultPgmRptFileList',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('consultPgmRptFileList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='consultPgmRptFileList') ,  (select page_id from tapage where file_name='consultPgmRptFileDetail') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('consultPgmRptFileDetail',' ',''),  replace('consultPgmRptFileList',' ',''),  replace('consultPgmRptFileDetail',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('consultPgmRptFileDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));

/** 2019-10-22 고려용접봉 반영 */
/** 2019-10-29 경보제약 반영 */

/** 658 이근환 */
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maPtMstrDetail') ,  (select page_id from tapage where file_name='partListImgLinkUrlList') ,  replace('175', ' ',''),  'N',  replace('TAB',' ',''),  replace('partListImgLinkUrlList',' ',''),  replace('maPtMstrDetail',' ',''),  replace('partListImgLinkUrlList',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('partListImgLinkUrlList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='partListImgLinkUrlList') ,  (select page_id from tapage where file_name='partListImgLinkUrlDetail') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('partListImgLinkUrlDetail',' ',''),  replace('partListImgLinkUrlList',' ',''),  replace('partListImgLinkUrlDetail',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('partListImgLinkUrlDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));


/** 2019-11-05 본사 국도화학 반영 */
/** 2019-11-05 고려용접봉 반영 */
/** 2019-11-06 국도화학 반영 */

/**614 + 은아 */

/** *** 추가 *** **/
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maResDetail') ,  (select page_id from tapage where file_name='mgrLangMenuList') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('mgrLangMenuList',' ',''),  replace('maResDetail',' ',''),  replace('mgrLangMenuList',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('mgrLangMenuList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maResDetail') ,  (select page_id from tapage where file_name='mgrLangPageList') ,  replace('120', ' ',''),  'N',  replace('TAB',' ',''),  replace('mgrLangPageList',' ',''),  replace('maResDetail',' ',''),  replace('mgrLangPageList',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('mgrLangPageList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maResDetail') ,  (select page_id from tapage where file_name='mgrLangPgpageList') ,  replace('130', ' ',''),  'N',  replace('TAB',' ',''),  replace('mgrLangPgpageList',' ',''),  replace('maResDetail',' ',''),  replace('mgrLangPgpageList',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('mgrLangPgpageList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maResDetail') ,  (select page_id from tapage where file_name='mgrLangPgBtnList') ,  replace('140', ' ',''),  'N',  replace('TAB',' ',''),  replace('mgrLangPgBtnList',' ',''),  replace('maResDetail',' ',''),  replace('mgrLangPgBtnList',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('mgrLangPgBtnList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maResDetail') ,  (select page_id from tapage where file_name='mgrLangPgFieldList') ,  replace('150', ' ',''),  'N',  replace('TAB',' ',''),  replace('mgrLangPgFieldList',' ',''),  replace('maResDetail',' ',''),  replace('mgrLangPgFieldList',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('mgrLangPgFieldList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maResDetail') ,  (select page_id from tapage where file_name='mgrLangPgGridColList') ,  replace('160', ' ',''),  'N',  replace('TAB',' ',''),  replace('mgrLangPgGridColList',' ',''),  replace('maResDetail',' ',''),  replace('mgrLangPgGridColList',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('mgrLangPgGridColList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maResDetail') ,  (select page_id from tapage where file_name='mgrLangPgLinkedList') ,  replace('170', ' ',''),  'N',  replace('TAB',' ',''),  replace('mgrLangPgLinkedList',' ',''),  replace('maResDetail',' ',''),  replace('mgrLangPgLinkedList',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('mgrLangPgLinkedList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maResDetail') ,  (select page_id from tapage where file_name='mgrLangSyscodeList') ,  replace('180', ' ',''),  'N',  replace('TAB',' ',''),  replace('mgrLangSyscodeList',' ',''),  replace('maResDetail',' ',''),  replace('mgrLangSyscodeList',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('mgrLangSyscodeList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maResDetail') ,  (select page_id from tapage where file_name='mgrLangFailcodeList') ,  replace('190', ' ',''),  'N',  replace('TAB',' ',''),  replace('mgrLangFailcodeList',' ',''),  replace('maResDetail',' ',''),  replace('mgrLangFailcodeList',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('mgrLangFailcodeList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));

/**692 + 김은아 */
 INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='partStkCurrentList') ,  (select page_id from tapage where file_name='partStkCurrentDetail') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('partStkCurrentDetail',' ',''),  replace('partStkCurrentList',' ',''),  replace('partStkCurrentDetail',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('partStkCurrentDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));

 /** 2019-11-14 고려용접봉 반영 */
 
 /** 766 이근환 */
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maEqMstrPartDetail') ,  (select page_id from tapage where file_name='assetListPartOpQtyList') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('assetListPartOpQtyList',' ',''),  replace('maEqMstrPartDetail',' ',''),  replace('assetListPartOpQtyList',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('assetListPartOpQtyList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='assetListPartOpQtyList') ,  (select page_id from tapage where file_name='assetListPartOpQtyDetail') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('assetListPartOpQtyDetail',' ',''),  replace('assetListPartOpQtyList',' ',''),  replace('assetListPartOpQtyDetail',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('assetListPartOpQtyDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));

/** 2019-11-20 매일유업 반영 */
/** youngjoo38_746 + 김영주 */
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='assetRptNYearPOList') ,  (select page_id from tapage where file_name='assetRptNYearPartsList') ,  replace('10', ' ',''),  'N',  replace('TAB',' ',''),  replace('assetRptNYearPartsList',' ',''),  replace('assetRptNYearPOList',' ',''),  replace('assetRptNYearPartsList',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('assetRptNYearPartsList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));


/** 2019-12-02 매일유업 반영 */

/** 774 + 김은아 */
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='mgrUsrGrpPageAuthDetail') ,  (select page_id from tapage where file_name='mgrUsrGrpPageAuthFieldList') ,  replace('130', ' ',''),  'N',  replace('TAB',' ',''),  replace('mgrUsrGrpPageAuthFieldList',' ',''),  replace('mgrUsrGrpPageAuthDetail',' ',''),  replace('mgrUsrGrpPageAuthFieldList',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('mgrUsrGrpPageAuthFieldList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='mgrUsrGrpPageAuthDetail') ,  (select page_id from tapage where file_name='mgrUsrGrpPageAuthGridColList') ,  replace('140', ' ',''),  'N',  replace('TAB',' ',''),  replace('mgrUsrGrpPageAuthGridColList',' ',''),  replace('mgrUsrGrpPageAuthDetail',' ',''),  replace('mgrUsrGrpPageAuthGridColList',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('mgrUsrGrpPageAuthGridColList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='mgrUsrGrpPageAuthFieldList') ,  (select page_id from tapage where file_name='mgrUsrGrpPageAuthFieldDetail') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('mgrUsrGrpPageAuthFieldDetail',' ',''),  replace('mgrUsrGrpPageAuthFieldList',' ',''),  replace('mgrUsrGrpPageAuthFieldDetail',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('mgrUsrGrpPageAuthFieldDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='mgrUsrGrpPageAuthGridColList') ,  (select page_id from tapage where file_name='mgrUsrGrpPageAuthGridColDetail') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('mgrUsrGrpPageAuthGridColDetail',' ',''),  replace('mgrUsrGrpPageAuthGridColList',' ',''),  replace('mgrUsrGrpPageAuthGridColDetail',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('mgrUsrGrpPageAuthGridColDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));

/** youngjoo38_698 + 김영주 */

INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='mgrContractList') ,  (select page_id from tapage where file_name='mgrContractDetail') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('mgrContractDetail',' ',''),  replace('mgrContractList',' ',''),  replace('mgrContractDetail',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('mgrContractDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='mgrContractDetail') ,  (select page_id from tapage where file_name='mgrContractItemList') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('mgrContractItemList',' ',''),  replace('mgrContractDetail',' ',''),  replace('mgrContractItemList',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('mgrContractItemList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='mgrContractDetail') ,  (select page_id from tapage where file_name='maPtDocLibList') ,  replace('120', ' ',''),  'N',  replace('TAB',' ',''),  replace('maPtDocLibList',' ',''),  replace('mgrContractDetail',' ',''),  replace('maPtDocLibList',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('maPtDocLibList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='mgrContractItemList') ,  (select page_id from tapage where file_name='mgrContractItemDetail') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('mgrContractItemDetail',' ',''),  replace('mgrContractItemList',' ',''),  replace('mgrContractItemDetail',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('mgrContractItemDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));

INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='workServiceDetail') ,  (select page_id from tapage where file_name='workServicePriceList') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('workServicePriceList',' ',''),  replace('workServiceDetail',' ',''),  replace('workServicePriceList',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('workServicePriceList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='workServiceDetail') ,  (select page_id from tapage where file_name='maPtDocLibList') ,  replace('120', ' ',''),  'N',  replace('TAB',' ',''),  replace('maPtDocLibList',' ',''),  replace('workServiceDetail',' ',''),  replace('maPtDocLibList',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('maPtDocLibList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='workServicePriceList') ,  (select page_id from tapage where file_name='workServicePriceDetail') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('workServicePriceDetail',' ',''),  replace('workServicePriceList',' ',''),  replace('workServicePriceDetail',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('workServicePriceDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));

/** 2019-12-13 한국내화 반영 */
/** 2019-12-20 고려용접봉 반영 */

/** 447 + 이지수 */
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='orgRptCraftWorkTimeTop') ,  (select page_id from tapage where file_name='orgRptCraftWorkTimeMonthly') ,  replace('110', ' ',''),  replace('Y',' ',''),  replace('TAB',' ',''),  replace('orgRptCraftWorkTimeMonthly',' ',''),  replace('orgRptCraftWorkTimeTop',' ',''),  replace('orgRptCraftWorkTimeMonthly',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('orgRptCraftWorkTimeMonthly', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='orgRptCraftWorkTimeTop') ,  (select page_id from tapage where file_name='orgRptCraftWorkTimeDaily') ,  replace('120', ' ',''),  replace('Y',' ',''),  replace('TAB',' ',''),  replace('orgRptCraftWorkTimeDaily',' ',''),  replace('orgRptCraftWorkTimeTop',' ',''),  replace('orgRptCraftWorkTimeDaily',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('orgRptCraftWorkTimeDaily', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));

/*724 김남현*/
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoMonthWoList') ,  (select page_id from tapage where file_name='workListEqChangeMstrDetail') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('workListEqChangeMstrDetail',' ',''),  replace('maWoMonthWoList',' ',''),  replace('workListEqChangeMstrDetail',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('workListEqChangeMstrDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));

INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='workListEqChangeMstrDetail') ,  (select page_id from tapage where file_name='maWoResultCraftList') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('maWoResultCraftList',' ',''),  replace('workListEqChangeMstrDetail',' ',''),  replace('maWoResultCraftList',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('maWoResultCraftList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='workListEqChangeMstrDetail') ,  (select page_id from tapage where file_name='maWoResultPartList') ,  replace('120', ' ',''),  'N',  replace('TAB',' ',''),  replace('maWoResultPartList',' ',''),  replace('workListEqChangeMstrDetail',' ',''),  replace('maWoResultPartList',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('maWoResultPartList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='workListEqChangeMstrDetail') ,  (select page_id from tapage where file_name='maWoResultWoImageList') ,  replace('120', ' ',''),  'N',  replace('TAB',' ',''),  replace('maWoResultWoImageList',' ',''),  replace('workListEqChangeMstrDetail',' ',''),  replace('maWoResultWoImageList',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('maWoResultWoImageList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='workListEqChangeMstrDetail') ,  (select page_id from tapage where file_name='maAppPrcList') ,  replace('130', ' ',''),  'N',  replace('TAB',' ',''),  replace('maAppPrcList',' ',''),  replace('workListEqChangeMstrDetail',' ',''),  replace('maAppPrcList',' ',''),  replace('TBD',' ',''),  replace('1.00',' ',''),  replace('TAB',' ',''),  replace('maAppPrcList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='workListEqChangeMstrDetail') ,  (select page_id from tapage where file_name='maPtDocLibList') ,  replace('140', ' ',''),  'N',  replace('TAB',' ',''),  replace('maPtDocLibList',' ',''),  replace('workListEqChangeMstrDetail',' ',''),  replace('maPtDocLibList',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('maPtDocLibList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));

INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultCraftList') ,  (select page_id from tapage where file_name='maWoResultCraftDetail') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('maWoResultCraftDetail',' ',''),  replace('maWoResultCraftList',' ',''),  replace('maWoResultCraftDetail',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultCraftDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultPartList') ,  (select page_id from tapage where file_name='maWoResultPartDetail') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('maWoResultPartDetail',' ',''),  replace('maWoResultPartList',' ',''),  replace('maWoResultPartDetail',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultPartDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maAppPrcList') ,  (select page_id from tapage where file_name='maAppPrcDetail') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('maAppPrcDetail',' ',''),  replace('maAppPrcList',' ',''),  replace('maAppPrcDetail',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maAppPrcDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maPtDocLibList') ,  (select page_id from tapage where file_name='maPtDocLibDetail') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('maPtDocLibDetail',' ',''),  replace('maPtDocLibList',' ',''),  replace('maPtDocLibDetail',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maPtDocLibDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));


/** 2020-01-03 azdream - AZURE CLOUD DREAM2 반영 */
/** 2020-01-09 고려용접봉 반영*/
/** 2020-01-09 매일유업 반영 */

/*985 김남현*/
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoMonthWoList') ,  (select page_id from tapage where file_name='maWoResultBmRplMstrDetail') ,  replace('110', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultBmRplMstrDetail',' ',''),  replace('maWoMonthWoList',' ',''),  replace('maWoResultBmRplMstrDetail',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultBmRplMstrDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoMonthWoList') ,  (select page_id from tapage where file_name='maWoResultCmRplMstrDetail') ,  replace('110', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultCmRplMstrDetail',' ',''),  replace('maWoMonthWoList',' ',''),  replace('maWoResultCmRplMstrDetail',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultCmRplMstrDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoMonthWoList') ,  (select page_id from tapage where file_name='maWoResultPmRplMstrDetail') ,  replace('110', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultPmRplMstrDetail',' ',''),  replace('maWoMonthWoList',' ',''),  replace('maWoResultPmRplMstrDetail',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultPmRplMstrDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoMonthWoList') ,  (select page_id from tapage where file_name='maWoResultPmGnlMstrDetail') ,  replace('110', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultPmGnlMstrDetail',' ',''),  replace('maWoMonthWoList',' ',''),  replace('maWoResultPmGnlMstrDetail',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultPmGnlMstrDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));

INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultBmRplMstrDetail') ,  (select page_id from tapage where file_name='maWoResultCraftList') ,  replace('110', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultCraftList',' ',''),  replace('maWoResultBmRplMstrDetail',' ',''),  replace('maWoResultCraftList',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultCraftList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultBmRplMstrDetail') ,  (select page_id from tapage where file_name='maWoResultPartList') ,  replace('120', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultPartList',' ',''),  replace('maWoResultBmRplMstrDetail',' ',''),  replace('maWoResultPartList',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultPartList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultBmRplMstrDetail') ,  (select page_id from tapage where file_name='maWoResultToolList') ,  replace('130', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultToolList',' ',''),  replace('maWoResultBmRplMstrDetail',' ',''),  replace('maWoResultToolList',' ',''),  replace('TBD',' ',''),  replace('1.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultToolList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultBmRplMstrDetail') ,  (select page_id from tapage where file_name='maWoResultWoImageList') ,  replace('140', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultWoImageList',' ',''),  replace('maWoResultBmRplMstrDetail',' ',''),  replace('maWoResultWoImageList',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultWoImageList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultBmRplMstrDetail') ,  (select page_id from tapage where file_name='maAppPrcList') ,  replace('150', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maAppPrcList',' ',''),  replace('maWoResultBmRplMstrDetail',' ',''),  replace('maAppPrcList',' ',''),  replace('TBD',' ',''),  replace('1.00',' ',''),  replace('TAB',' ',''),  replace('maAppPrcList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultBmRplMstrDetail') ,  (select page_id from tapage where file_name='maPtDocLibList') ,  replace('160', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maPtDocLibList',' ',''),  replace('maWoResultBmRplMstrDetail',' ',''),  replace('maPtDocLibList',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maPtDocLibList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));

INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultCmRplMstrDetail') ,  (select page_id from tapage where file_name='maWoResultCraftList') ,  replace('110', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultCraftList',' ',''),  replace('maWoResultCmRplMstrDetail',' ',''),  replace('maWoResultCraftList',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultCraftList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultCmRplMstrDetail') ,  (select page_id from tapage where file_name='maWoResultPartList') ,  replace('120', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultPartList',' ',''),  replace('maWoResultCmRplMstrDetail',' ',''),  replace('maWoResultPartList',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultPartList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultCmRplMstrDetail') ,  (select page_id from tapage where file_name='maWoResultWoImageList') ,  replace('130', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultWoImageList',' ',''),  replace('maWoResultCmRplMstrDetail',' ',''),  replace('maWoResultWoImageList',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultWoImageList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultCmRplMstrDetail') ,  (select page_id from tapage where file_name='maAppPrcList') ,  replace('140', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maAppPrcList',' ',''),  replace('maWoResultCmRplMstrDetail',' ',''),  replace('maAppPrcList',' ',''),  replace('TBD',' ',''),  replace('1.00',' ',''),  replace('TAB',' ',''),  replace('maAppPrcList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultCmRplMstrDetail') ,  (select page_id from tapage where file_name='maPtDocLibList') ,  replace('150', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maPtDocLibList',' ',''),  replace('maWoResultCmRplMstrDetail',' ',''),  replace('maPtDocLibList',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maPtDocLibList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));

INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultPmRplMstrDetail') ,  (select page_id from tapage where file_name='maWoResultCraftList') ,  replace('110', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultCraftList',' ',''),  replace('maWoResultPmRplMstrDetail',' ',''),  replace('maWoResultCraftList',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultCraftList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultPmRplMstrDetail') ,  (select page_id from tapage where file_name='maWoResultPartList') ,  replace('120', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultPartList',' ',''),  replace('maWoResultPmRplMstrDetail',' ',''),  replace('maWoResultPartList',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultPartList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultPmRplMstrDetail') ,  (select page_id from tapage where file_name='maWoResultWoImageList') ,  replace('130', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultWoImageList',' ',''),  replace('maWoResultPmRplMstrDetail',' ',''),  replace('maWoResultWoImageList',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultWoImageList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultPmRplMstrDetail') ,  (select page_id from tapage where file_name='maAppPrcList') ,  replace('140', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maAppPrcList',' ',''),  replace('maWoResultPmRplMstrDetail',' ',''),  replace('maAppPrcList',' ',''),  replace('TBD',' ',''),  replace('1.00',' ',''),  replace('TAB',' ',''),  replace('maAppPrcList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultPmRplMstrDetail') ,  (select page_id from tapage where file_name='maPtDocLibList') ,  replace('150', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maPtDocLibList',' ',''),  replace('maWoResultPmRplMstrDetail',' ',''),  replace('maPtDocLibList',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maPtDocLibList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));

INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultPmGnlMstrDetail') ,  (select page_id from tapage where file_name='maWoResultCraftList') ,  replace('110', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultCraftList',' ',''),  replace('maWoResultPmGnlMstrDetail',' ',''),  replace('maWoResultCraftList',' ',''),  replace('TBD',' ',''),  replace('1.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultCraftList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultPmGnlMstrDetail') ,  (select page_id from tapage where file_name='workListGnlCavalList') ,  replace('120', ' ',''),  'Y',  replace('TAB',' ',''),  replace('workListGnlCavalList',' ',''),  replace('maWoResultPmGnlMstrDetail',' ',''),  replace('workListGnlCavalList',' ',''),  replace('TBD',' ',''),  replace('1.00',' ',''),  replace('TAB',' ',''),  replace('workListGnlCavalList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultPmGnlMstrDetail') ,  (select page_id from tapage where file_name='maWoResultCraftList') ,  replace('130', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultCraftList',' ',''),  replace('maWoResultPmGnlMstrDetail',' ',''),  replace('maWoResultCraftList',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultCraftList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultPmGnlMstrDetail') ,  (select page_id from tapage where file_name='maPtDocLibList') ,  replace('140', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maDocLibList',' ',''),  replace('maWoResultPmGnlMstrDetail',' ',''),  replace('maPtDocLibList',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maDocLibList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultPmGnlMstrDetail') ,  (select page_id from tapage where file_name='maAppPrcList') ,  replace('150', ' ',''),  'Y',  replace('TAB',' ',''),  replace('appByHist',' ',''),  replace('maWoResultPmGnlMstrDetail',' ',''),  replace('maAppPrcList',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('appByHist', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultPmGnlMstrDetail') ,  (select page_id from tapage where file_name='maWoResultWoImageList') ,  replace('160', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultWoImageList',' ',''),  replace('maWoResultPmGnlMstrDetail',' ',''),  replace('maWoResultWoImageList',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultWoImageList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));

INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultCraftList') ,  (select page_id from tapage where file_name='maWoResultCraftDetail') ,  replace('110', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultCraftDetail',' ',''),  replace('maWoResultCraftList',' ',''),  replace('maWoResultCraftDetail',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultCraftDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultPartList') ,  (select page_id from tapage where file_name='maWoResultPartDetail') ,  replace('110', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultPartDetail',' ',''),  replace('maWoResultPartList',' ',''),  replace('maWoResultPartDetail',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultPartDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maAppPrcList') ,  (select page_id from tapage where file_name='maAppPrcDetail') ,  replace('110', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maAppPrcDetail',' ',''),  replace('maAppPrcList',' ',''),  replace('maAppPrcDetail',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maAppPrcDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maPtDocLibList') ,  (select page_id from tapage where file_name='maPtDocLibDetail') ,  replace('110', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maPtDocLibDetail',' ',''),  replace('maPtDocLibList',' ',''),  replace('maPtDocLibDetail',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maPtDocLibDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultToolList') ,  (select page_id from tapage where file_name='maWoResultToolDetail') ,  replace('110', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultToolDetail',' ',''),  replace('maWoResultToolList',' ',''),  replace('maWoResultToolDetail',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultToolDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));

update tapgpage set c_page_id = (select page_id from tapage where file_name = 'maWoResultToolList'), c_file_name = 'maWoResultToolList', key_no = 'maWoResultToolList' where c_page_id in (select page_id from tapage where file_name in ('maWoResultBmOilToolList', 'maWoResultBmRplToolList'));
update tapgpage set c_page_id = (select page_id from tapage where file_name = 'maWoResultToolDetail'), c_file_name = 'maWoResultToolDetail', key_no = 'maWoResultToolDetail' where c_page_id in (select page_id from tapage where file_name in ('maWoResultBmOilToolDetail', 'maWoResultBmRplToolDetail'));
update tapgpage set c_page_id = (select page_id from tapage where file_name = 'maWoResultPartList'), c_file_name = 'maWoResultPartList' key_no = 'maWoResultToolDetail' where c_page_id in (select page_id from tapage where file_name in ('maWoResultBmOilPartList', 'maWoResultBmRplPartList', 'maWoResultCmRplPartList', 'maWoResultMonthPartList', 'maWoResultPmRplPartList'));
update tapgpage set c_page_id = (select page_id from tapage where file_name = 'maWoResultPartDetail'), c_file_name = 'maWoResultPartDetail', key_no = 'maWoResultPartDetail' where c_page_id in (select page_id from tapage where file_name in ('maWoResultBmOilPartDetail', 'maWoResultBmRplPartDetail', 'maWoResultCmRplPartDetail', 'maWoResultMonthPartDetail', 'maWoResultPmRplPartDetail'));
update tapgpage set c_page_id = (select page_id from tapage where file_name = 'maWoResultCraftList'), c_file_name = 'maWoResultCraftList',key_no = 'maWoResultCraftList' where c_page_id in (select page_id from tapage where c_file_name in ('maWoResultBmOilCraftList', 'maWoResultBmRplCraftList', 'maWoResultBmRprCraftList', 'maWoResultCmLocBaseCraftList', 'maWoResultCmRplCraftList', 'maWoResultCmRprCraftList', 'maWoResultGnlCaCraftList', 'maWoResultMonthCraftList', 'maWoResultPmClnCraftList' ,'maWoResultPmInsCraftList', 'maWoResultPmOilCraftList', 'maWoResultPmRplCraftList', 'maWoResultPmRprCraftList', 'maWoResultPrsCaCraftList', 'maWoResultSclCaCraftList', 'maWoResultTrEleCraftList'));
update tapgpage set c_page_id = (select page_id from tapage where file_name = 'maWoResultCraftDetail'), c_file_name = 'maWoResultCraftDetail', key_no ='maWoResultCraftDetail' where c_page_id in (select page_id from tapage where c_file_name in ('maWoResultBmOilCraftDetail', 'maWoResultBmRplCraftDetail', 'maWoResultBmRprCraftDetail', 'maWoResultCmLocBaseCraftDetail', 'maWoResultCmRplCraftDetail', 'maWoResultCmRprCraftDetail', 'maWoResultGnlCaCraftDetail', 'maWoResultMonthCraftDetail', 'maWoResultPmClnCraftDetail', 'maWoResultPmInsCraftDetail', 'maWoResultPmOilCraftDetail', 'maWoResultPmRplCraftDetail', 'maWoResultPmRprCraftDetail', 'maWoResultPrsCaCraftDetail', 'maWoResultSclCaCraftDetail', 'maWoResultTrEleCraftDetail'));
update tapgpage set c_page_id = (select page_id from tapage where file_name = 'maPtDocLibList'), c_file_name = 'maPtDocLibList', key_no = 'maPtDocLibList' where page_id = (select page_id from tapage where file_name = 'maWoResultBmRplMstrDetail') and c_page_id = (select page_id from tapage where file_name = 'maWoResultBmRplDocLibList');
update tapgpage set c_page_id = (select page_id from tapage where file_name = 'maPtDocLibList'), c_file_name = 'maPtDocLibList', key_no = 'maPtDocLibList' where page_id = (select page_id from tapage where file_name = 'maWoResultPmOilMstrDetail') and c_page_id = (select page_id from tapage where file_name = 'maWoResultPmOilDocLibList');
update tapgpage set c_page_id = (select page_id from tapage where file_name = 'maPtDocLibList'), c_file_name = 'maPtDocLibList', key_no = 'maPtDocLibList' where page_id = (select page_id from tapage where file_name = 'maWoResultCmRplMstrDetail') and c_page_id = (select page_id from tapage where file_name = 'maWoResultCmRplDocLibList');
update tapgpage set c_page_id = (select page_id from tapage where file_name = 'maPtDocLibList'), c_file_name = 'maPtDocLibList', key_no = 'maPtDocLibList' where page_id = (select page_id from tapage where file_name = 'maWoResultPmRplMstrDetail') and c_page_id = (select page_id from tapage where file_name = 'maWoResultPmRplDocLibList');
update tapgpage set c_page_id = (select page_id from tapage where file_name = 'maPtDocLibList'), c_file_name = 'maPtDocLibList', key_no = 'maPtDocLibList' where page_id = (select page_id from tapage where file_name = 'maWoResultTrEleMstrDetail') and c_page_id = (select page_id from tapage where file_name = 'maWoResultTrEleDocLibList');
update tapgpage set c_page_id = (select page_id from tapage where file_name = 'maPtDocLibList'), c_file_name = 'maPtDocLibList', key_no = 'maPtDocLibList' where page_id = (select page_id from tapage where file_name = 'workListPtrlResultMstrDetail') and c_page_id = (select page_id from tapage where file_name = 'workListDocLibList');

/*985 김남현*/
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoMonthWoList') ,  (select page_id from tapage where file_name='maWoResultBmRplMstrDetail') ,  replace('110', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultBmRplMstrDetail',' ',''),  replace('maWoMonthWoList',' ',''),  replace('maWoResultBmRplMstrDetail',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultBmRplMstrDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoMonthWoList') ,  (select page_id from tapage where file_name='maWoResultCmRplMstrDetail') ,  replace('110', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultCmRplMstrDetail',' ',''),  replace('maWoMonthWoList',' ',''),  replace('maWoResultCmRplMstrDetail',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultCmRplMstrDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoMonthWoList') ,  (select page_id from tapage where file_name='maWoResultPmRplMstrDetail') ,  replace('110', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultPmRplMstrDetail',' ',''),  replace('maWoMonthWoList',' ',''),  replace('maWoResultPmRplMstrDetail',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultPmRplMstrDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoMonthWoList') ,  (select page_id from tapage where file_name='maWoResultPmGnlMstrDetail') ,  replace('110', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultPmGnlMstrDetail',' ',''),  replace('maWoMonthWoList',' ',''),  replace('maWoResultPmGnlMstrDetail',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultPmGnlMstrDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));

INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultBmRplMstrDetail') ,  (select page_id from tapage where file_name='maWoResultCraftList') ,  replace('110', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultCraftList',' ',''),  replace('maWoResultBmRplMstrDetail',' ',''),  replace('maWoResultCraftList',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultCraftList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultBmRplMstrDetail') ,  (select page_id from tapage where file_name='maWoResultPartList') ,  replace('120', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultPartList',' ',''),  replace('maWoResultBmRplMstrDetail',' ',''),  replace('maWoResultPartList',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultPartList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultBmRplMstrDetail') ,  (select page_id from tapage where file_name='maWoResultToolList') ,  replace('130', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultToolList',' ',''),  replace('maWoResultBmRplMstrDetail',' ',''),  replace('maWoResultToolList',' ',''),  replace('TBD',' ',''),  replace('1.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultToolList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultBmRplMstrDetail') ,  (select page_id from tapage where file_name='maWoResultWoImageList') ,  replace('140', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultWoImageList',' ',''),  replace('maWoResultBmRplMstrDetail',' ',''),  replace('maWoResultWoImageList',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultWoImageList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultBmRplMstrDetail') ,  (select page_id from tapage where file_name='maAppPrcList') ,  replace('150', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maAppPrcList',' ',''),  replace('maWoResultBmRplMstrDetail',' ',''),  replace('maAppPrcList',' ',''),  replace('TBD',' ',''),  replace('1.00',' ',''),  replace('TAB',' ',''),  replace('maAppPrcList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultBmRplMstrDetail') ,  (select page_id from tapage where file_name='maPtDocLibList') ,  replace('160', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maPtDocLibList',' ',''),  replace('maWoResultBmRplMstrDetail',' ',''),  replace('maPtDocLibList',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maPtDocLibList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));

INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultCmRplMstrDetail') ,  (select page_id from tapage where file_name='maWoResultCraftList') ,  replace('110', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultCraftList',' ',''),  replace('maWoResultCmRplMstrDetail',' ',''),  replace('maWoResultCraftList',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultCraftList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultCmRplMstrDetail') ,  (select page_id from tapage where file_name='maWoResultPartList') ,  replace('120', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultPartList',' ',''),  replace('maWoResultCmRplMstrDetail',' ',''),  replace('maWoResultPartList',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultPartList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultCmRplMstrDetail') ,  (select page_id from tapage where file_name='maWoResultWoImageList') ,  replace('130', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultWoImageList',' ',''),  replace('maWoResultCmRplMstrDetail',' ',''),  replace('maWoResultWoImageList',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultWoImageList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultCmRplMstrDetail') ,  (select page_id from tapage where file_name='maAppPrcList') ,  replace('140', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maAppPrcList',' ',''),  replace('maWoResultCmRplMstrDetail',' ',''),  replace('maAppPrcList',' ',''),  replace('TBD',' ',''),  replace('1.00',' ',''),  replace('TAB',' ',''),  replace('maAppPrcList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultCmRplMstrDetail') ,  (select page_id from tapage where file_name='maPtDocLibList') ,  replace('150', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maPtDocLibList',' ',''),  replace('maWoResultCmRplMstrDetail',' ',''),  replace('maPtDocLibList',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maPtDocLibList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));

INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultPmRplMstrDetail') ,  (select page_id from tapage where file_name='maWoResultCraftList') ,  replace('110', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultCraftList',' ',''),  replace('maWoResultPmRplMstrDetail',' ',''),  replace('maWoResultCraftList',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultCraftList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultPmRplMstrDetail') ,  (select page_id from tapage where file_name='maWoResultPartList') ,  replace('120', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultPartList',' ',''),  replace('maWoResultPmRplMstrDetail',' ',''),  replace('maWoResultPartList',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultPartList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultPmRplMstrDetail') ,  (select page_id from tapage where file_name='maWoResultWoImageList') ,  replace('130', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultWoImageList',' ',''),  replace('maWoResultPmRplMstrDetail',' ',''),  replace('maWoResultWoImageList',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultWoImageList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultPmRplMstrDetail') ,  (select page_id from tapage where file_name='maAppPrcList') ,  replace('140', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maAppPrcList',' ',''),  replace('maWoResultPmRplMstrDetail',' ',''),  replace('maAppPrcList',' ',''),  replace('TBD',' ',''),  replace('1.00',' ',''),  replace('TAB',' ',''),  replace('maAppPrcList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultPmRplMstrDetail') ,  (select page_id from tapage where file_name='maPtDocLibList') ,  replace('150', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maPtDocLibList',' ',''),  replace('maWoResultPmRplMstrDetail',' ',''),  replace('maPtDocLibList',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maPtDocLibList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));

INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultPmGnlMstrDetail') ,  (select page_id from tapage where file_name='maWoResultCraftList') ,  replace('110', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultCraftList',' ',''),  replace('maWoResultPmGnlMstrDetail',' ',''),  replace('maWoResultCraftList',' ',''),  replace('TBD',' ',''),  replace('1.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultCraftList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultPmGnlMstrDetail') ,  (select page_id from tapage where file_name='workListGnlCavalList') ,  replace('120', ' ',''),  'Y',  replace('TAB',' ',''),  replace('workListGnlCavalList',' ',''),  replace('maWoResultPmGnlMstrDetail',' ',''),  replace('workListGnlCavalList',' ',''),  replace('TBD',' ',''),  replace('1.00',' ',''),  replace('TAB',' ',''),  replace('workListGnlCavalList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultPmGnlMstrDetail') ,  (select page_id from tapage where file_name='maWoResultCraftList') ,  replace('130', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultCraftList',' ',''),  replace('maWoResultPmGnlMstrDetail',' ',''),  replace('maWoResultCraftList',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultCraftList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultPmGnlMstrDetail') ,  (select page_id from tapage where file_name='maPtDocLibList') ,  replace('140', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maDocLibList',' ',''),  replace('maWoResultPmGnlMstrDetail',' ',''),  replace('maPtDocLibList',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maDocLibList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultPmGnlMstrDetail') ,  (select page_id from tapage where file_name='maAppPrcList') ,  replace('150', ' ',''),  'Y',  replace('TAB',' ',''),  replace('appByHist',' ',''),  replace('maWoResultPmGnlMstrDetail',' ',''),  replace('maAppPrcList',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('appByHist', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultPmGnlMstrDetail') ,  (select page_id from tapage where file_name='maWoResultWoImageList') ,  replace('160', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultWoImageList',' ',''),  replace('maWoResultPmGnlMstrDetail',' ',''),  replace('maWoResultWoImageList',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultWoImageList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));

INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultCraftList') ,  (select page_id from tapage where file_name='maWoResultCraftDetail') ,  replace('110', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultCraftDetail',' ',''),  replace('maWoResultCraftList',' ',''),  replace('maWoResultCraftDetail',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultCraftDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultPartList') ,  (select page_id from tapage where file_name='maWoResultPartDetail') ,  replace('110', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultPartDetail',' ',''),  replace('maWoResultPartList',' ',''),  replace('maWoResultPartDetail',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultPartDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maAppPrcList') ,  (select page_id from tapage where file_name='maAppPrcDetail') ,  replace('110', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maAppPrcDetail',' ',''),  replace('maAppPrcList',' ',''),  replace('maAppPrcDetail',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maAppPrcDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maPtDocLibList') ,  (select page_id from tapage where file_name='maPtDocLibDetail') ,  replace('110', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maPtDocLibDetail',' ',''),  replace('maPtDocLibList',' ',''),  replace('maPtDocLibDetail',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maPtDocLibDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='maWoResultToolList') ,  (select page_id from tapage where file_name='maWoResultToolDetail') ,  replace('110', ' ',''),  'Y',  replace('TAB',' ',''),  replace('maWoResultToolDetail',' ',''),  replace('maWoResultToolList',' ',''),  replace('maWoResultToolDetail',' ',''),  replace('DREAM',' ',''),  replace('2.00',' ',''),  replace('TAB',' ',''),  replace('maWoResultToolDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));

update tapgpage set c_page_id = (select page_id from tapage where file_name = 'maWoResultToolList'), c_file_name = 'maWoResultToolList', key_no = 'maWoResultToolList' where c_page_id in (select page_id from tapage where file_name in ('maWoResultBmOilToolList', 'maWoResultBmRplToolList'));
update tapgpage set c_page_id = (select page_id from tapage where file_name = 'maWoResultToolDetail'), c_file_name = 'maWoResultToolDetail', key_no = 'maWoResultToolDetail' where c_page_id in (select page_id from tapage where file_name in ('maWoResultBmOilToolDetail', 'maWoResultBmRplToolDetail'));
update tapgpage set c_page_id = (select page_id from tapage where file_name = 'maWoResultPartList'), c_file_name = 'maWoResultPartList' key_no = 'maWoResultToolDetail' where c_page_id in (select page_id from tapage where file_name in ('maWoResultBmOilPartList', 'maWoResultBmRplPartList', 'maWoResultCmRplPartList', 'maWoResultMonthPartList', 'maWoResultPmRplPartList'));
update tapgpage set c_page_id = (select page_id from tapage where file_name = 'maWoResultPartDetail'), c_file_name = 'maWoResultPartDetail', key_no = 'maWoResultPartDetail' where c_page_id in (select page_id from tapage where file_name in ('maWoResultBmOilPartDetail', 'maWoResultBmRplPartDetail', 'maWoResultCmRplPartDetail', 'maWoResultMonthPartDetail', 'maWoResultPmRplPartDetail'));
update tapgpage set c_page_id = (select page_id from tapage where file_name = 'maWoResultCraftList'), c_file_name = 'maWoResultCraftList',key_no = 'maWoResultCraftList' where c_page_id in (select page_id from tapage where c_file_name in ('maWoResultBmOilCraftList', 'maWoResultBmRplCraftList', 'maWoResultBmRprCraftList', 'maWoResultCmLocBaseCraftList', 'maWoResultCmRplCraftList', 'maWoResultCmRprCraftList', 'maWoResultGnlCaCraftList', 'maWoResultMonthCraftList', 'maWoResultPmClnCraftList' ,'maWoResultPmInsCraftList', 'maWoResultPmOilCraftList', 'maWoResultPmRplCraftList', 'maWoResultPmRprCraftList', 'maWoResultPrsCaCraftList', 'maWoResultSclCaCraftList', 'maWoResultTrEleCraftList'));
update tapgpage set c_page_id = (select page_id from tapage where file_name = 'maWoResultCraftDetail'), c_file_name = 'maWoResultCraftDetail', key_no ='maWoResultCraftDetail' where c_page_id in (select page_id from tapage where c_file_name in ('maWoResultBmOilCraftDetail', 'maWoResultBmRplCraftDetail', 'maWoResultBmRprCraftDetail', 'maWoResultCmLocBaseCraftDetail', 'maWoResultCmRplCraftDetail', 'maWoResultCmRprCraftDetail', 'maWoResultGnlCaCraftDetail', 'maWoResultMonthCraftDetail', 'maWoResultPmClnCraftDetail', 'maWoResultPmInsCraftDetail', 'maWoResultPmOilCraftDetail', 'maWoResultPmRplCraftDetail', 'maWoResultPmRprCraftDetail', 'maWoResultPrsCaCraftDetail', 'maWoResultSclCaCraftDetail', 'maWoResultTrEleCraftDetail'));
update tapgpage set c_page_id = (select page_id from tapage where file_name = 'maPtDocLibList'), c_file_name = 'maPtDocLibList', key_no = 'maPtDocLibList' where page_id = (select page_id from tapage where file_name = 'maWoResultBmRplMstrDetail') and c_page_id = (select page_id from tapage where file_name = 'maWoResultBmRplDocLibList');
update tapgpage set c_page_id = (select page_id from tapage where file_name = 'maPtDocLibList'), c_file_name = 'maPtDocLibList', key_no = 'maPtDocLibList' where page_id = (select page_id from tapage where file_name = 'maWoResultPmOilMstrDetail') and c_page_id = (select page_id from tapage where file_name = 'maWoResultPmOilDocLibList');
update tapgpage set c_page_id = (select page_id from tapage where file_name = 'maPtDocLibList'), c_file_name = 'maPtDocLibList', key_no = 'maPtDocLibList' where page_id = (select page_id from tapage where file_name = 'maWoResultCmRplMstrDetail') and c_page_id = (select page_id from tapage where file_name = 'maWoResultCmRplDocLibList');
update tapgpage set c_page_id = (select page_id from tapage where file_name = 'maPtDocLibList'), c_file_name = 'maPtDocLibList', key_no = 'maPtDocLibList' where page_id = (select page_id from tapage where file_name = 'maWoResultPmRplMstrDetail') and c_page_id = (select page_id from tapage where file_name = 'maWoResultPmRplDocLibList');
update tapgpage set c_page_id = (select page_id from tapage where file_name = 'maPtDocLibList'), c_file_name = 'maPtDocLibList', key_no = 'maPtDocLibList' where page_id = (select page_id from tapage where file_name = 'maWoResultTrEleMstrDetail') and c_page_id = (select page_id from tapage where file_name = 'maWoResultTrEleDocLibList');
update tapgpage set c_page_id = (select page_id from tapage where file_name = 'maPtDocLibList'), c_file_name = 'maPtDocLibList', key_no = 'maPtDocLibList' where page_id = (select page_id from tapage where file_name = 'workListPtrlResultMstrDetail') and c_page_id = (select page_id from tapage where file_name = 'workListDocLibList');

/** 2020-01-14 한국내화 kref 반영 */

/*796 김남현*/
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='woPlanDetail') ,  (select page_id from tapage where file_name='woPlanServiceList') ,  replace('140', ' ',''),  'N',  replace('TAB',' ',''),  replace('woPlanServiceList',' ',''),  replace('woPlanDetail',' ',''),  replace('woPlanServiceList',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('woPlanServiceList', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));
INSERT INTO TAPGPAGE (pgpage_id, page_id, c_page_id, ord_no, is_use, key_type, key_no, file_name, c_file_name, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES (next value for SQAPGPAGE_ID,  (select page_id from tapage where file_name='woPlanServiceList') ,  (select page_id from tapage where file_name='woPlanServiceDetail') ,  replace('110', ' ',''),  'N',  replace('TAB',' ',''),  replace('woPlanServiceDetail',' ',''),  replace('woPlanServiceList',' ',''),  replace('woPlanServiceDetail',' ',''),  replace('DREAM',' ',''),  replace('2.01',' ',''),  replace('TAB',' ',''),  replace('woPlanServiceDetail', ' ',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));

/** 2020-01-17 매일유업 반영 */

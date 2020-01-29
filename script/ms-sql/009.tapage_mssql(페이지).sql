select 
'INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT value FOR SQAPAGE_ID, '''||file_name||''', '''||description||''', '''||is_chkauth||''', '''||remark||''', '''||is_use||''', '''||page_type||''' ); ' 
from tapage

---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-11   박철완 최초 생성*/ 
---------------------------------------------------------------------------------------------------------------------------------------------------
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'lovAssetPopup','자산검색 팝업','N','자산검색 팝업','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'lovButtonPopup','버튼 팝업','N','버튼리스트 팝업','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'lovCtCtrPopup','CP 팝업','N','CP 팝업','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'lovDeptPopup','부서검색 팝업','N','부서검색 팝업','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'lovEmpPopup','사원 선택 팝업','N','사원 선택 팝업','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'lovEqAppPopup','설비기안 품의서 팝업','N','설비기안 품의서 팝업','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'lovEqAsmbPopup','설비부위 팝업','N','설비부위 리스트','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'lovEqCtgAsmbPopup','설비종류 작업부위 팝업','N','설비종류 작업부위 팝업','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'lovEqCtgPopup','설비종류 검색팝업','N','설비종류 검색 팝업','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'lovEqLocPopup','위치분류 검색 팝업','N','위치분류 검색 팝업','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'lovEquipPopup','설비팝업','N','설비팝업 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'lovLangPopup','다국어 팝업','N','다국어 검색 팝업 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'lovMenuPopup','메뉴 팝업','N','메뉴 검색 팝업','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'lovPagePopup','페이지 팝업','N','페이지 리스트 팝업','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'lovPartsPopup','부품 검색 팝업','N','자재검색 팝업 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'lovPtAppPopup','수리기안 품의서 팝업','N','수리기안 품의서 팝업','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'lovSysDirPopup','LovSys팝업','N','ListOfVal 공통 시스템코드 선택 팝업 ','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'lovTablePopup','lovTable팝업','N','ListOfVal 공통 테이블 선택 팝업','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'lovUserPopup','사용자 선택 팝업','N','','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'lovUsrDirPopup','LovUsr팝업','N','ListOfVal 공통 사용자코드 선택 팝업 ','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'lovVendorPopup','거래처 팝업','N','거래처 선택 팝업','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'lovWhPopup','사용창고 검색 팝업','N','사용창고 리스트 ','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maBatchMngDetail','배치작업내역 상세','Y','배치작업내역 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maBatchMngList','배치작업내역 목록','Y','배치작업내역 목록','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maBdPointDetail','이상점검조치 상세','Y','이상점검조치 상세 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maBdPointList','이상점검조치 목록','Y','이상점검 리스트 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maBmActList','고장조치작업','Y','특정부서의 담당자가 조치한 고장작업 리스트를 일자별로 볼 수 있는 화면','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maBmCtgChart','설비종류별고장분석','Y','설비종류별 고장 건수와 시간을 표와 그래프로 볼 수 있는 화면','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maBmEqList','설비별고장분석','Y','설비별로 고장 건수, 시간, 고장원인, 조치를 리스트 형식으로 확인할 수 있는 화면','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maBmGwChart','과별고장분석','Y','고장관련 작업 수치를 년 기준으로 볼 수 있는 화면','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maBmLnChart','라인고장분석','Y','라인별 고장 분석화면','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maBtnMngDetail','버튼 상세','Y','','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maBtnMngList','버튼 목록','Y','','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maCdSysCodeDetail','시스템 세부코드 상세','Y','','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maCdSysCodeList','시스템 세부코드 목록','Y','','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maCdSysDetail','시스템 코드 상세','Y','시스템 코드 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maCdSysList','시스템코드 목록','Y','','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maCdUsrCdDetail','시스템 코드 세부내용','Y','시스템코드 설정하는 세부화면','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maCdUsrCdList','사용자 세부코드 목록','Y','사용자 세부코드 목록','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maCdUsrDetail','사용자코드 세부내용','Y','사용자코드 설정하는 세부화면','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maCdUsrList','사용자코드 목록','Y','사용자 코드 리스트','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maChangePw','비밀번호 변경','N','비밀번호 변경','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maConfigDetail','환경변수 상세','Y','','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maConfigList','환경변수 목록','Y','MWARE환경변수를 설정','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maDeptDetail','작업부서 상세','Y','','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maDeptList','작업부서 목록','Y','','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maDocCntrCdDetail','일반자료실 상세','Y','일반자료실 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maDocCntrCdList','일반자료실 목록','Y','일반자료실 목록','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maDocCntrEcDetail','동종기계정보 상세','Y','동종기계정보 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maDocCntrEcList','동종기계정보 목록','Y','동종기계정보 목록','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maDocImgDetail','사진첨부 상세','Y','사진첨부 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maDocImgList','사진첨부','Y','화면 Slide Image 관리','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maDocLibDetail','첨부문서 상세','Y','첨부문서 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maDocLibList','첨부문서','Y','첨부문서 목록','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maEmpDetail','보전사원 상세','Y','','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maEmpList','보전사원 목록','Y','','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maEqCatalogDetail','설비종류 상세','Y','설비종류 상세 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maEqCatalogList','설비종류 목록','Y','설비종류 목록 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maEqCtgAsmbDetail','설비종류별 작업부위 상세 ','Y','설비종류별 작업부위 상세페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maEqCtgAsmbList','설비종류별 작업부위 목록','Y','설비종류별 작업부위 목록 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maEqCtgPartDetail','설비종류별 부품 상세','Y','설비종류별 부품 상세 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maEqCtgPartList','설비종류별 부품 목록','Y','설비종류별 부품 목록 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maEqLocDetail','위치분류 상세','Y','위치분류 상세 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maEqLocList','위치분류 목록','Y','위치분류목록 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maEqMngDetail','설비담당자 변경 상세','Y','설비담당자 일괄 변경 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maEqMngList','설비담당자변경','Y','설비담당자 일괄 변경','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maEqMstrAsmbDetail','설비부위 상세','Y','설비부위 상세 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maEqMstrAsmbList','설비부위 목록','Y','설비부위 목록 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maEqMstrAssetDetail','설비관련 자산 상세','Y','설비관련 자산 상세 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maEqMstrAssetList','설비관련 자산 목록','Y','설비관련 자산 목록 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maEqMstrDetail','설비마스터 상세','Y','설비마스터 상세 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maEqMstrHistDetail','설비변경이력 상세','Y','설비변경이력 상세 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maEqMstrHistList','설비변경이력 목록','N','설비변경이력 목록 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maEqMstrList','설비마스터 목록','Y','설비마스터 목록 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maEqMstrPartDetail','설비 구성부품 상세','Y','설비 구성자재 상세 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maEqMstrPartList','설비 구성부품 목록','Y','설비 구성자재 목록 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maEqMstrSpecDetail','설비제원 상세','Y','설비제원(스펙) 상세 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maEqMstrSpecList','설비제원 목록','Y','설비제원(스펙) 목록','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maEqMstrVendorDetail','설비거래처 상세','Y','설비거래처 상세 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maEqMstrVendorList','설비거래처 목록','Y','설비거래처 목록 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maEqNotesAppDetail','설비기안 상세','Y','설비기안 상세 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maEqNotesAppList','설비기안 목록','N','설비기안 목록 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maEqWoChart','설비작업현황','Y','설비작업현황 ','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maFailureDetail','고장코드 상세','Y','고장코드 상세 내용','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maFailureList','고장코드 목록','Y','고장코드 목록','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maGrdMngColDetail','화면컬럼 상세','Y','화면컬럼 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maGrdMngColList','화면컬럼 목록','N','화면 컬럼 목록','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maGrdMngDetail','화면컬럼정의 상세','Y','화면컬럼정의 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maGrdMngList','화면컬럼정의','Y','화면컬럼정의','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maGrdUsrColDetail','화면 사용자정의 컬럼 상세','Y','화면 사용자정의 컬럼 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maGrdUsrColList','화면 사용자정의 컬럼','Y','화면 사용자정의 컬럼','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maGrdUsrDetail','화면 사용자 정의 상세','N','화면 사용자 정의 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maLangMngDetail','다국어 상세','Y','','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maLangMngList','다국어 목록','Y','','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maLinkMenuDetail','연결메뉴 상세','N','연결메뉴 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maLinkMenuList','연결메뉴 목록','N','연결메뉴 목록','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maMailDetail','메일수신자설정 상세','Y','메일수신자설정 상세페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maMailList','메일수신자설정 목록','Y','메일수신자설정 목록 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maMailUsrDetail','메일수신자설정 수신자 상세','Y','메일수신자설정 수신자 상세 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maMailUsrList','메일수신자설정 수신자 목록','Y','메일수신자설정 수신자 목록','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maMenuMngDetail','메뉴 상세','Y','','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maMenuMngList','메뉴 목록','Y','','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maMyInfo','개인설정','Y','','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maMyMenuDetail','사용자메뉴 상세','Y','사용자메뉴 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maMyMenuList','사용자메뉴','N','사용자메뉴 [User Menu]','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPgMngBtnDetail','화면별 버튼 상세','Y','','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPgMngBtnList','화면별버튼 목록','Y','','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPgMngDetail','화면 상세','Y','','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPgMngList','화면 목록','Y','','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPgMngPageDetail','화면 탭페이지 상세','Y','화면 탭페이지 상세 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPgMngPageList','화면 탭페이지 목록','Y','화면 탭페이지 목록 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPmMstrDetail','예방작업기준 상세','Y','예방작업기준 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPmMstrList','예방작업기준','Y','예방작업','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPmMstrPartDetail','예방작업기준 사용부품 상세','Y','사용자재 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPmMstrPartList','예방작업 사용부품','Y','사용자재','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPmMstrPointDetail','예방작업기준 점검항목 상세','Y','','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPmMstrPointList','예방작업기준 점검항목 목록','Y','','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPmYearPtSchedList','연간부품사용일정','N','연간부품사용일정 목록','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPtBgList','예산사용현황 목록','Y','예산사용현황 목록','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPtBudgetDetail','예산계획 상세','Y','예산계획 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPtBudgetList','예산계획 목록','Y','예산계획설계 목록','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPtCostChart','부품비용분석','Y','자재비용분석 목록','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPtDeptBgDetail','부서별 예산금액 상세','Y','부서별 예산금액 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPtDeptBgList','부서별 예산금액 목록','Y','부서별 예산금액 목록','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPtMstrDetail','보전부품분류 상세','Y','보전자재분류','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPtMstrEqPartDetail','부품 사용설비 상세','Y','부품 사용설비 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPtMstrEqPartList','부품 사용설비 목록','Y','자재 사용설비 목록','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPtMstrList','보전부품분류 목록','Y','보전자재분류','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPtMstrUsedDeptDetail','부품 사용부서 상세','Y','부품 사용부서 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPtMstrUsedDeptList','부품 사용부서 목록','Y','부품 사용부서 목록','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPtMstrVendorDetail','보전부품분류 부품거래처 상세','Y','','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPtMstrVendorList','보전부품분류 부품거래처 목록','Y','','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPtRecDetail','구매입고 상세','Y','구매입고 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPtRecList','구매입고 목록','Y','','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPtRecStatList','부품입고내역 목록 ','Y','자재입고내역 목록 ','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPtRepAppDetail','수리기안 상세','N','수리기안 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPtRepAppList','수리기안 목록','Y','수리기안 목록','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPtRepDetail','부품수리 상세','Y','부품수리 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPtRepList','부품수리 목록','Y','부품수리 목록','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPtRepStatList','부품수리내역','Y','자재수리내역 목록','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPtStckDetail','부품재고 상세','Y','자재재고 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPtStckList','부품재고 목록','Y','자재재고 목록','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPtWhDetail','부풍창고 상세','Y','부풍창고 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPtWhList','부품창고 목록','Y','부풍창고 목록','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maReport','레포트등록','Y','레포트틀(엑셀)을 등록하고 레포트 파일(fo)로 변환하는 페이지  ','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maSesMngDetail','실시간 접속자 상세','Y','','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maSesMngList','실시간 접속자 목록','Y','','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maUserDetail','사용자 상세','Y','','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maUserList','사용자','Y','','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maUserPw','비밀번호 변경','N','사용자 비밀번호 변경','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maUsrGrpAuthList','권한명 상세 권한','Y','권한명 상세 권한','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maUsrGrpDetail','권한명 상세','N','어떤권한에 대한 세부 화면권한 정의','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maUsrGrpList','권한명 목록','Y','어떤권한을 만들지에 대한 권한리스트','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maVendorDetail','관련업체 상세','Y','관련업체 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maVendorList','관련업체 목록','Y','관련업체 목록','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoDailyChart','일별작업실행율','Y','모든 부서의 일별 작업 실행율을 확인','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoLinepfDayDetail','일별 라인가동내역 상세','Y','일별 라인가동내역 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoLinepfDayList','일별 라인가동내역 목록','Y','일별 라인가동내역 목록','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoLinepfDetail','라인가동내역 상세','Y','라인가동내역 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoLinepfList','라인가동내역 목록','Y','라인가동내역 목록','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoMonthSchedDetail','예방작업일정(월간) 상세','Y','예방작업일정(월간) 상세페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoMonthSchedList','예방작업일정(월간) 목록','Y','예방작업일정(월간) 목록페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoMonthWoList','월간작업일정','Y','월별 작업 일정을 달력형태로 확인하고 수정할 수 있는 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoPtHistList','부품사용이력','Y','부품사용이력 목록','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultCraftDetail','작업결과 작업자 상세','Y','작업결과 작업자 상세페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultCraftList','작업결과 작업자 목록','Y','작업결과 작업자 목록페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultFailDetail','작업결과 고장내역','Y','작업결과 고장내역 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultMstrDetail','작업결과 상세','Y','작업결과 상세 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultMstrList','작업결과 목록','Y','작업결과 목록 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultPartDetail','작업결과 투입부품 상세','Y','작업결과 투입자재 상세 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultPartList','작업결과 투입부품 목록','Y','작업결과 투입자재 목록 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultPointDetail','작업결과 검사항목 상세','Y','작업결과 검사항목 상세 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultPointList','작업결과 검사항목 목록','Y','작업결과 검사항목 목록 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoYearSchedDetail','예방작업일정(연간) 상세','Y','예방작업일정(연간) 상세페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoYearSchedList','예방작업일정(연간) 목록','Y','예방작업일정(연간) 목록 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maConnChart','시스템 접속현황','Y','부서별 사원의 접속현황을 볼 수 있는 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPmTrendChart','예방점검경향분석','Y','예방점검 경향분석 화면','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maUseChart','시스템 사용현황','Y','시스템 사용현황','Y')
go 
INSERT INTO TAPAGE(page_id, file_name, description, remark, is_use, is_chkauth) VALUES (NEXT VALUE FOR sqapage_id, 'maStdPointList','표준항목','표준항목 리스트','Y','Y')
go
INSERT INTO TAPAGE(page_id, file_name, description, remark, is_use, is_chkauth) VALUES (NEXT VALUE FOR sqapage_id, 'maStdPointDetail','표준항목','표준항목 상세','Y','Y')
go
 
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-12   김정우 추가작업 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO TAPAGE(page_id, file_name, description, remark, is_use, is_chkauth) VALUES (NEXT VALUE FOR sqapage_id, 'maEqBmList','설비고장내역','설비고장내역 리스트','Y','Y')
go
INSERT INTO TAPAGE(page_id, file_name, description, remark, is_use, is_chkauth) VALUES (NEXT VALUE FOR sqapage_id, 'maPmRepList','예방수리내역','예방수리내역 리스트','Y','Y')
go
INSERT INTO TAPAGE(page_id, file_name, description, remark, is_use, is_chkauth) VALUES (NEXT VALUE FOR sqapage_id, 'maPmPointList','예방점검내역','예방점검내역 리스트','Y','Y')
go
INSERT INTO TAPAGE(page_id, file_name, description, remark, is_use, is_chkauth) VALUES (NEXT VALUE FOR sqapage_id, 'maDeptWoList','부서별작업분석','부서별작업분석 리스트','Y','Y')
go
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-12   김정우 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-12   노정현 추가작업 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO TAPAGE(PAGE_ID, FILE_NAME, DESCRIPTION, IS_CHKAUTH, REMARK, IS_USE)VALUES(NEXT VALUE FOR sqapage_id, 'maNstGrpList', '무정지대표라인', 'Y', '무정지대표라인', 'Y')
go
INSERT INTO TAPAGE(PAGE_ID, FILE_NAME, DESCRIPTION, IS_CHKAUTH, REMARK, IS_USE)VALUES(NEXT VALUE FOR sqapage_id, 'maNstGrpDetail', '무정지대표라인 상세', 'Y', '무정지대표라인 상세', 'Y')
go
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-12   노정현 추가작업 끝 오뚜기 LIVE 적용 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-17   노정현 추가작업 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO TAPAGE
   (PAGE_ID, FILE_NAME, DESCRIPTION, IS_CHKAUTH, REMARK, IS_USE)
 VALUES
   (NEXT VALUE FOR sqapage_id, 'maEqDocLibDetail', '설비첨부문서 상세', 'Y', '설비첨부문서 상세', 
    'Y')
go
INSERT INTO TAPAGE
   (PAGE_ID, FILE_NAME, DESCRIPTION, IS_CHKAUTH, REMARK, IS_USE)
 VALUES
   (NEXT VALUE FOR sqapage_id, 'maEqDocLibList', '설비첨부문서 목록', 'Y', '설비첨부문서 목록', 
    'Y')
go
INSERT INTO TAPAGE
   (PAGE_ID, FILE_NAME, DESCRIPTION, IS_CHKAUTH, REMARK, IS_USE)
 VALUES
   (NEXT VALUE FOR sqapage_id, 'maWoDocLibDetail', '작업첨부문서 상세', 'Y', '작업첨부문서 상세', 
    'Y')
go
INSERT INTO TAPAGE
   (PAGE_ID, FILE_NAME, DESCRIPTION, IS_CHKAUTH, REMARK, IS_USE)
 VALUES
   (NEXT VALUE FOR sqapage_id, 'maWoDocLibList', '작업첨부문서 목록', 'Y', '작업첨부문서 목록', 
    'Y')
go
INSERT INTO TAPAGE
   (PAGE_ID, FILE_NAME, DESCRIPTION, IS_CHKAUTH, REMARK, IS_USE)
 VALUES
   (NEXT VALUE FOR sqapage_id, 'maPtDocLibDetail', '부품첨부문서 상세', 'Y', '부품첨부문서 상세', 
    'Y')
go
INSERT INTO TAPAGE
   (PAGE_ID, FILE_NAME, DESCRIPTION, IS_CHKAUTH, REMARK, IS_USE)
 VALUES
   (NEXT VALUE FOR sqapage_id, 'maPtDocLibList', '부품첨부문서 목록', 'Y', '부품첨부문서 목록', 
    'Y')
go
INSERT INTO TAPAGE
   (PAGE_ID, FILE_NAME, DESCRIPTION, IS_CHKAUTH, REMARK, IS_USE)
 VALUES
   (NEXT VALUE FOR sqapage_id, 'maDocImgPopDetail', '사진첨부팝업 상세', 'Y', '사진첨부팝업 상세', 
    'Y')
go
INSERT INTO TAPAGE
   (PAGE_ID, FILE_NAME, DESCRIPTION, IS_CHKAUTH, REMARK, IS_USE)
 VALUES
   (NEXT VALUE FOR sqapage_id, 'maDocImgPopList', '사진첨부팜업 목록', 'Y', '사진첨부팜업 목록', 
    'Y')
go
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-17   노정현 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-16   김정우 추가작업 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO TAPAGE(page_id, file_name, description, remark, is_use, is_chkauth) VALUES (NEXT VALUE FOR sqapage_id, 'maStdPointHdrList','예방작업표준항목','예방작업표준항목 리스트','Y','Y')
go
INSERT INTO TAPAGE(page_id, file_name, description, remark, is_use, is_chkauth) VALUES (NEXT VALUE FOR sqapage_id, 'maStdPointHdrDetail','예방작업표준항목','예방작업표준항목 상세','Y','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'lovUsrGrpPopup','권한 팝업','N','권한 검색 팝업','Y')
go
  insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultSelect','작업종류& 작업형태','N','작업종류& 작업형태 팝업','Y')
go 
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-16   김정우 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-24   김정우 추가작업 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO TAPAGE(page_id, file_name, description, remark, is_use, is_chkauth) VALUES (NEXT VALUE FOR sqapage_id, 'maWoResultStPointList','작업필수 점검항목','작업필수 점검항목 리스트','Y','Y')
go
INSERT INTO TAPAGE(page_id, file_name, description, remark, is_use, is_chkauth) VALUES (NEXT VALUE FOR sqapage_id, 'maWoResultStPointDetail','작업필수 점검항목','작업필수 점검항목 상세','Y','Y')
go
 ---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-24   김정우 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-26   김정우 추가작업 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO TAPAGE(page_id, file_name, description, remark, is_use, is_chkauth) VALUES (NEXT VALUE FOR sqapage_id, 'maQnaList','질의응답 목록' ,'질의응답 리스트','Y','Y')
go
INSERT INTO TAPAGE(page_id, file_name, description, remark, is_use, is_chkauth) VALUES (NEXT VALUE FOR sqapage_id, 'maQnaDetail','질의응답 상세','질의응답 상세','Y','Y')
go
INSERT INTO TAPAGE(page_id, file_name, description, remark, is_use, is_chkauth) VALUES (NEXT VALUE FOR sqapage_id, 'maQnaAnsList','답변사항 목록' ,'답변사항 리스트','Y','Y')
go
INSERT INTO TAPAGE(page_id, file_name, description, remark, is_use, is_chkauth) VALUES (NEXT VALUE FOR sqapage_id, 'maQnaAnsDetail','답변사항 상세','답변사항 상세','Y','Y')
go

---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-26   김정우 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-30   김정우 추가작업 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO TAPAGE(page_id, file_name, description, remark, is_use, is_chkauth) VALUES (NEXT VALUE FOR sqapage_id, 'maPtBuyReqHdrList','구매신청 목록' ,'구매신청 리스트','Y','Y')
go
INSERT INTO TAPAGE(page_id, file_name, description, remark, is_use, is_chkauth) VALUES (NEXT VALUE FOR sqapage_id, 'maPtBuyReqHdrDetail','구매신청 상세','구매신청 상세','Y','Y')
go
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-30   김정우 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------



 ---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-31   이규선 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
  insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPtPurReqList','현장구매신청','N','','Y')
go
  insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPtPurReqDetail','현장구매신청 상세','N','현장구매신청','Y')
go 
 
 ---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-05-31   이규선 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
 ---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-06-01   김정우 구매신청 item 페이지 추가 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO TAPAGE(page_id, file_name, description, remark, is_use, is_chkauth) VALUES (NEXT VALUE FOR sqapage_id, 'maPtBuyReqList','구매신청 Item 목록' ,'구매신청 Item 리스트','Y','Y')
go
INSERT INTO TAPAGE(page_id, file_name, description, remark, is_use, is_chkauth) VALUES (NEXT VALUE FOR sqapage_id, 'maPtBuyReqDetail','구매신청 Item 상세','구매신청 Item 상세','Y','Y')
go
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-06-01   김정우 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-06-02   김정우 자재검색 팝업2 페이지 추가 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'lovReqPartsPopup','부품 검색 팝업','N','자재검색 팝업 페이지','Y')
go 
 ---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-06-02   김정우 추가작업 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-06-07   김정우 월간작업일정 작업결과 페이지 추가 시작*/ 
---------------------------------------------------------------------------------------------------------------------------------------------------

 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultMonthMstrDetail','월간작업일정 상세','Y','월별 작업 일정을 달력형태로 확인하고 수정할 수 있는 페이지','Y')
go
 
 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultMonthCraftDetail','작업결과(월간) 작업자 상세','Y','작업결과 작업자 상세페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultMonthCraftList','작업결과(월간) 작업자 목록','Y','작업결과 작업자 목록페이지','Y')
go 

 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultMonthFailDetail','작업결과(월간) 고장내역','Y','작업결과 고장내역 페이지','Y')
go 

 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultMonthPartDetail','작업결과(월간) 투입부품 상세','Y','작업결과 투입자재 상세 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultMonthPartList','작업결과(월간) 투입부품 목록','Y','작업결과 투입자재 목록 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultMonthPointDetail','작업결과(월간) 검사항목 상세','Y','작업결과 검사항목 상세 페이지','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultMonthPointList','작업결과(월간) 검사항목 목록','Y','작업결과 검사항목 목록 페이지','Y')
go 

INSERT INTO TAPAGE(page_id, file_name, description, remark, is_use, is_chkauth) VALUES (NEXT VALUE FOR sqapage_id, 'maWoResultMonthStPointList','(월간)작업필수 점검항목','작업필수 점검항목 리스트','Y','Y')
go
INSERT INTO TAPAGE(page_id, file_name, description, remark, is_use, is_chkauth) VALUES (NEXT VALUE FOR sqapage_id, 'maWoResultMonthStPointDetail','(월간)작업필수 점검항목','작업필수 점검항목 상세','Y','Y')
go


INSERT INTO TAPAGE
   (PAGE_ID, FILE_NAME, DESCRIPTION, IS_CHKAUTH, REMARK, IS_USE)
 VALUES
   (NEXT VALUE FOR sqapage_id, 'maWoMonthDocLibDetail', '작업(월간)첨부문서 상세', 'Y', '작업첨부문서 상세', 
    'Y')
go
INSERT INTO TAPAGE
   (PAGE_ID, FILE_NAME, DESCRIPTION, IS_CHKAUTH, REMARK, IS_USE)
 VALUES
   (NEXT VALUE FOR sqapage_id, 'maWoMonthDocLibList', '작업(월간)첨부문서 목록', 'Y', '작업첨부문서 목록', 
    'Y')
go
 ---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-06-07   김정우 월간작업일정 작업결과 페이지 추가 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
 ---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-06-10   김정우 예방작업기준서 작업형태 팝업 페이지,점검상세페이지 추가 시작 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPmMstrSelect','작업형태','N','작업형태 팝업','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPmInsMstrDetail','예방작업기준(점검) 상세','Y','예방작업기준(점검) 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPmRprMstrDetail','예방작업기준(수리) 상세','Y','예방작업기준(수리) 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPmRplMstrDetail','예방작업기준(교체) 상세','Y','예방작업기준(교체) 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPmClnMstrDetail','예방작업기준(청소) 상세','Y','예방작업기준(청소) 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPmEqClnList','청소예방설비 목록','Y','청소예방설비 목록','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPmEqClnDetail','청소예방설비 상세','Y','청소예방설비 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPmOilMstrDetail','예방작업기준(윤활) 상세','Y','예방작업기준(윤활) 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPmEqOilList','윤활예방설비 목록','Y','윤활예방설비 목록','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPmEqOilDetail','윤활예방설비 상세','Y','윤활예방설비 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPmOilPartList','윤활설비 자재 목록','Y','윤활설비 자재 목록','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPmOilPartDetail','윤활설비 자재 상세','Y','윤활설비 자재 상세','Y')
go 
 ---------------------------------------------------------------------------------------------------------------------------------------------------
/*2016-06-10   김정우 예방작업기준서 작업형태 팝업 페이지 추가 끝 */ 
---------------------------------------------------------------------------------------------------------------------------------------------------
 
 
/** 2016-06-14 노정현 일일작업일지확인 추가 */
INSERT INTO TAPAGE
   (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH)
 VALUES
   (NEXT VALUE FOR sqapage_id , 'maWoDailyList', '일일작업일지확인 목록', '일일작업일지확인 목록', 'Y', 
    'Y')
go
INSERT INTO TAPAGE
   (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH)
 VALUES
   (NEXT VALUE FOR sqapage_id, 'maWoDailyDetail', '일일작업일지확인 상세', '일일작업일지확인 상세', 'Y', 
    'Y')
go
/** 2016-06-15 김정우 작업결과  상세페이지,작업자,점검항목, 자재 추가 시작 */
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultPmInsMstrDetail','작업결과(예방작업-점검) 상세','Y','작업결과(예방작업-점검) 상세','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultPmInsCraftList','작업결과(예방작업-점검) 작업자 목록','Y','작업결과(예방작업-점검) 작업자 목록','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultPmInsCraftDetail','작업결과(예방작업-점검) 작업자 상세','Y','작업결과(예방작업-점검) 작업자 상세','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultPmInsPointList','작업결과(예방작업-점검) 점검항목 목록','Y','작업결과(예방작업-점검) 점검항목 목록','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultPmInsPointDetail','작업결과(예방작업-점검) 점검항목 상세','Y','작업결과(예방작업-점검) 점검항목 상세','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultPmRprMstrDetail','작업결과(예방작업-수리) 상세','Y','작업결과(예방작업-수리) 상세','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultPmRprCraftList','작업결과(예방작업-수리) 작업자 목록','Y','작업결과(예방작업-수리) 작업자 목록','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultPmRprCraftDetail','작업결과(예방작업-수리) 작업자 상세','Y','작업결과(예방작업-수리) 작업자 상세','Y')
go
 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultPmRplMstrDetail','작업결과(예방작업-교체) 상세','Y','작업결과(예방작업-교체) 상세','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultPmRplCraftList','작업결과(예방작업-교체) 작업자 목록','Y','작업결과(예방작업-교체) 작업자 목록','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultPmRplCraftDetail','작업결과(예방작업-교체) 작업자 상세','Y','작업결과(예방작업-교체) 작업자 상세','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultPmRplPartList','작업결과(예방작업-교체) 사용부품 목록','Y','작업결과(예방작업-교체) 사용부품 목록','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultPmRplPartDetail','작업결과(예방작업-교체) 사용부품 상세','Y','작업결과(예방작업-교체) 사용부품 상세','Y')
go
/** 2016-06-15 김정우 작업결과  상세페이지,작업자,점검항목, 자재 추가 끝 */
/** 2016-06-16 김정우 작업결과 상세페이지, 작업자 페이지 추가작업 시작 */
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultClnList','작업결과 청소설비 목록','Y','작업결과 청소설비 목록','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultClnDetail','작업결과 청소설비 상세','Y','작업결과 청소설비 상세','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultPmClnMstrDetail','작업결과(예방작업-청소) 상세','Y','작업결과(예방작업-청소) 상세','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultPmClnCraftList','작업결과(예방작업-청소) 작업자 목록','Y','작업결과(예방작업-청소) 작업자 목록','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultPmClnCraftDetail','작업결과(예방작업-청소) 작업자 상세','Y','작업결과(예방작업-청소) 작업자 상세','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultOilList','작업결과 윤활설비 목록','Y','작업결과 윤활설비 목록','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultOilDetail','작업결과 윤활설비 상세','Y','작업결과 윤활설비 상세','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultPmOilMstrDetail','작업결과(예방작업-윤활) 상세','Y','작업결과(예방작업-윤활) 상세','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultPmOilCraftList','작업결과(예방작업-윤활) 작업자 목록','Y','작업결과(예방작업-윤활) 작업자 목록','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultPmOilCraftDetail','작업결과(예방작업-윤활) 작업자 상세','Y','작업결과(예방작업-윤활) 작업자 상세','Y')
go
/** 2016-06-16 김정우 작업결과 상세페이지, 작업자 페이지 추가작업 끝 */
/** 2016-06-20 김정우 작업결과 상세페이지, 작업자 페이지 추가작업 시작 */
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultBmRprMstrDetail','작업결과(고장작업-수리) 상세','Y','작업결과(고장작업-수리) 상세','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultBmRprCraftList','작업결과(고장작업-수리) 작업자 목록','Y','작업결과(고장작업-수리) 작업자 목록','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultBmRprCraftDetail','작업결과(고장작업-수리) 작업자 상세','Y','작업결과(고장작업-수리) 작업자 상세','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultBmRplMstrDetail','작업결과(고장작업-교체) 상세','Y','작업결과(고장작업-교체) 상세','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultBmRplCraftList','작업결과(고장작업-교체) 작업자 목록','Y','작업결과(고장작업-교체) 작업자 목록','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultBmRplCraftDetail','작업결과(고장작업-교체) 작업자 상세','Y','작업결과(고장작업-교체) 작업자 상세','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultBmRplPartList','작업결과(고장작업-교체) 사용부품 목록','Y','작업결과(고장작업-교체) 사용부품 목록','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultBmRplPartDetail','작업결과(고장작업-교체) 사용부품 상세','Y','작업결과(고장작업-교체) 사용부품 상세','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultBmOilMstrDetail','작업결과(고장작업-윤활) 상세','Y','작업결과(고장작업-윤활) 상세','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultBmOilCraftList','작업결과(고장작업-윤활) 작업자 목록','Y','작업결과(고장작업-윤활) 작업자 목록','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultBmOilCraftDetail','작업결과(고장작업-윤활) 작업자 상세','Y','작업결과(고장작업-윤활) 작업자 상세','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultBmOilPartList','작업결과(고장작업-윤활) 사용부품 목록','Y','작업결과(고장작업-윤활) 사용부품 목록','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultBmOilPartDetail','작업결과(고장작업-윤활) 사용부품 상세','Y','작업결과(고장작업-윤활) 사용부품 상세','Y')
go
  insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultCmRprMstrDetail','작업결과(개선작업-수리) 상세','Y','작업결과(개선작업-수리) 상세','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultCmRprCraftList','작업결과(개선작업-수리) 작업자 목록','Y','작업결과(개선작업-수리) 작업자 목록','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultCmRprCraftDetail','작업결과(개선작업-수리) 작업자 상세','Y','작업결과(개선작업-수리) 작업자 상세','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultCmRplMstrDetail','작업결과(개선작업-교체) 상세','Y','작업결과(개선작업-교체) 상세','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultCmRplCraftList','작업결과(개선작업-교체) 작업자 목록','Y','작업결과(개선작업-교체) 작업자 목록','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultCmRplCraftDetail','작업결과(개선작업-교체) 작업자 상세','Y','작업결과(개선작업-교체) 작업자 상세','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultCmRplPartList','작업결과(개선작업-교체) 사용부품 목록','Y','작업결과(개선작업-교체) 사용부품 목록','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultCmRplPartDetail','작업결과(개선작업-교체) 사용부품 상세','Y','작업결과(개선작업-교체) 사용부품 상세','Y')
go
 
/** 2016-06-20 김정우 작업결과 상세페이지, 작업자 페이지 추가작업 끝 */
 
 
 /** 2016-06-22 노정현 일일작업일지 확인 생성 팝업 추가  */
 INSERT INTO TAPAGE
   (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH)
 VALUES
   (NEXT VALUE FOR sqapage_id, 'maWoDailySelect', '일일작업일지확인 생성 팝업', '일일작업일지확인 생성 팝업', 'Y', 
    'N')
go
INSERT INTO TAPAGE
   (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH)
 VALUES
   (NEXT VALUE FOR sqapage_id, 'appReqDetail', '결재요청팝업', '결재요청팝업', 'Y', 
    'Y')
go
INSERT INTO TAPAGE
   (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH)
 VALUES
   (NEXT VALUE FOR sqapage_id, 'appPrcList', '결재자 목록', '결재자 목록', 'Y', 
    'Y')
go
INSERT INTO TAPAGE
   (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH)
 VALUES
   (NEXT VALUE FOR sqapage_id, 'appPrcDetail', '결재자 상세', '결재자 상세', 'Y', 
    'Y')
go
INSERT INTO TAPAGE
   (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH)
 VALUES
   (NEXT VALUE FOR sqapage_id, 'maAppPrcList', '결재자 이력 목록', '결재자 이력 목록', 'Y', 
    'Y')
go
INSERT INTO TAPAGE
   (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH)
 VALUES
   (NEXT VALUE FOR sqapage_id, 'maAppPrcDetail', '결재자 이력 상세', '결재자 이력 상세', 'Y', 
    'Y')
go
INSERT INTO TAPAGE
   (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH)
 VALUES
   (NEXT VALUE FOR sqapage_id, 'maAppLineList', '결재선 목록', '결재선 목록', 'Y', 
    'Y')
go
INSERT INTO TAPAGE
   (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH)
 VALUES
   (NEXT VALUE FOR sqapage_id, 'maAppLineDetail', '결재선 상세', '결재선 상세', 'Y', 
    'Y')
go
INSERT INTO TAPAGE
   (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH)
 VALUES
   (NEXT VALUE FOR sqapage_id, 'maAppLineUsrList', '결재선 유저 목록', '결재선 유저 목록', 'Y', 
    'Y')
go
INSERT INTO TAPAGE
   (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH)
 VALUES
   (NEXT VALUE FOR sqapage_id, 'maAppLineUsrDetail', '결재선 유저 상세', '결재선 유저 상세', 'Y', 
    'Y')
go
INSERT INTO TAPAGE
   (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH)
 VALUES
   (NEXT VALUE FOR sqapage_id, 'maAppLinePopupList', '결재선 목록 팝업', '결재선 목록 팝업', 'Y', 
    'Y')
go
INSERT INTO TAPAGE
   (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH)
 VALUES
   (NEXT VALUE FOR sqapage_id, 'maAppLinePopupDetail', '결재선 상세 팝업', '결재선 상세 팝업', 'Y', 
    'Y')
go
INSERT INTO TAPAGE
   (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH)
 VALUES
   (NEXT VALUE FOR sqapage_id, 'maAppLineUsrPopupList', '결재선 유저 목록 팝업', '결재선 유저 목록 팝업', 'Y', 
    'Y')
go
INSERT INTO TAPAGE
   (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH)
 VALUES
   (NEXT VALUE FOR sqapage_id, 'maAppLineUsrPopupDetail', '결재선 유저 상세 팝업', '결재선 유저 상세 팝업', 'Y', 
    'Y')
go

INSERT INTO TAPAGE
   (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH)
 VALUES
   (NEXT VALUE FOR sqapage_id, 'appReadyList', '결재대기 목록', '결재대기 목록', 'Y', 
    'Y')
go
INSERT INTO TAPAGE
   (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH)
 VALUES
   (NEXT VALUE FOR sqapage_id, 'appReadyPopup', '결재처리', '결재처리', 'Y', 
    'N')
go

/** -> 2016-06-28 김정우 - 무상입고 추가  */

INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH)
 VALUES(NEXT VALUE FOR sqapage_id, 'maPtFcRecList', '무상입고 목록', '무상입고 목록', 'Y', 'Y')
go
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH)
 VALUES(NEXT VALUE FOR sqapage_id, 'maPtFcRecDetail', '무상입고 상세', '무상입고 상세', 'Y', 'Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPgMngFieldDetail','화면별 필드 상세','Y','화면별 필드 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPgMngFieldList','화면별 필드 목록','Y','화면별 필드 목록','Y')
go 

 /** 2016-06-29 노정현 월별보전목표 */
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH)
 VALUES(NEXT VALUE FOR sqapage_id, 'maMtGoalList', '월별보전목표 목록', '월별보전목표 목록', 'Y', 'Y')
go
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH)
 VALUES(NEXT VALUE FOR sqapage_id, 'maMtGoalDetail', '월별보전목표 상세', '월별보전목표 목록', 'Y', 'Y')
go
  /** 2016-06-29 김정우 라인가동계획 */
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maLineRunPlanDetail','라인가동계획 상세','Y','라인가동계획 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maLineRunPlanList','라인가동계획 목록','Y','라인가동계획 목록','Y')
go 
 
/** 2016-06-30 노정현 라인별 투자목표금액 */
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH)
 VALUES(NEXT VALUE FOR sqapage_id, 'maLnGoalList', '라인별투자목표금액 목록', '라인별투자목표금액 목록', 'Y', 'Y')
go
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH)
 VALUES(NEXT VALUE FOR sqapage_id, 'maLnGoalDetail', '라인별투자목표금액 상세', '라인별투자목표금액 목록', 'Y', 'Y')
go
 
 INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH)
 VALUES(NEXT VALUE FOR sqapage_id, 'maPmTrMstrDetail', '예방작업기준 교육 상세', '예방작업기준 교육 상세', 'Y', 'Y')
go
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH)
 VALUES(NEXT VALUE FOR sqapage_id, 'maPmTrMstrDetail', '예방작업기준 교육 상세', '예방작업기준 교육 상세', 'Y', 'Y')
go
/** 2016-07-06 김정우 작업결과 교육 상세, 교육자 페이지 추가 */
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultTrEleMstrDetail','작업결과(교육) 상세','Y','작업결과(교육) 상세','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultTrEleCraftList','작업결과(교육) 교육자 목록','Y','작업결과(교육) 교육자 목록','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultTrEleCraftDetail','작업결과(교육) 교육자 상세','Y','작업결과(교육) 교육자 상세','Y')
go
 /** 2016-07-07 김정우 설비구성품 화면 추가 */
 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maEqMstrItemsList','설비마스터 - 구성품 목록','Y','설비마스터 - 구성품 목록','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maEqMstrItemsDetail','설비마스터 - 구성품 상세','Y','설비마스터 - 구성품 상세','Y')
go
  insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'lovMesLinePopup','MES LINE 팝업','N','MES LINE 팝업','Y')
go 
 
/** 2016-07-18 노정현 자재 출고 확정  */
insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPtIssList','부품출고확정 목록','Y','부품출고확정 목록','Y')
go
insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPtIssDetail','부품출고확정 상세','Y','부품출고확정 상세','Y')
go 
 
insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'lovWoPopup','작업검색 팝업','N','작업검색 팝업','Y')
go 
 
/** 2016-07-27 김정우 월별재고목표 */
insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maMonthStockGoalList','월별재고목표 목록','Y','월별재고목표 목록','Y')
go
insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maMonthStockGoalDetail','월별재고목표 상세','Y','월별재고목표 상세','Y')
go 
/** 2016-07-28 김정우 Working Calendar */
insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoCalList','Working Calendar 목록','Y','Working Calendar 목록','Y')
go
/** 2016-08-01 김정우 일일보전지표 */
INSERT INTO tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maDayLocDnChart','일일보전지표 목록','Y','일일보전지표 목록','Y')
go
/** 2016-08-03 김정우 월간보전지표 */
INSERT INTO tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maMonLocDnChart','월간보전지표 목록','Y','월간보전지표 목록','Y')
go
INSERT INTO tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maMonLocDnDetail','월간보전지표 상세','Y','월간보전지표 상세','Y')
go
/** 2016-08-04 김정우 월간 MTTR,MTBF지표 */
INSERT INTO tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maMonMttrChart','월간 MTTR,MTBF지표 목록','Y','월간 MTTR,MTBF지표 목록','Y')
go
INSERT INTO tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maYearMttrChart','연간 MTTR,MTBF지표 목록','Y','연간 MTTR,MTBF지표 목록','Y')
go
 /** 2016-08-03 이규선 연간BD지표 */
 INSERT INTO tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maBmYearChart','연간BD지표 목록','Y','연간BD지표 목록','Y')
go
 /** 2016-08-08 김정우 작업유형별현황  */
 INSERT INTO tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maWoTypeChart','작업유형별현황 목록','Y','작업유형별현황 목록','Y')
go
  /** 2016-08-08 이규선 월간BD지표  */
  INSERT INTO tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maBmMonthChart','월간BD지표 목록','Y','월간BD지표 목록','Y')
go
  /** 2016-08-12 김정우 일일작업확인-작업목록   */
  INSERT INTO tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maWoDailyWoList','일일작업확인-작업 목록','Y','일일작업확인-작업 목록','Y')
go
  /** 2016-08-16 김정우 교육지표   */
  INSERT INTO tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maTrChart','교육지표','Y','교육지표','Y')
go
  /** 2016-08-17 이규선 사용자메뉴 팝업   */
  insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maMyMenu','사용자 메뉴','N','사용자 메뉴','Y')
go 
  /** 2016-08-18 김정우 투자지표   */
  INSERT INTO tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maIvChart','투자지표','Y','투자지표','Y')
go
   /** 2016-08-23 이규선 연결메뉴 팝업   */
   insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maMyLink','연결 메뉴','N','연결 메뉴','Y')
go 
  /** 2016-08-24 김정우 재고지표   */
  INSERT INTO tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maStckChart','재고지표','Y','재고지표','Y')
go
 
  /** 2016-08-26 이규선 공기구    */
insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPttMstrList','공기구마스터 목록','Y','공기구마스터','Y')
go 
 
insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPttMstrDetail','공기구마스터 상세','Y','공기구마스터','Y')
go 
 
insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPttStckList','공기구재고 목록','Y','공기구재고 목록','Y')
go 
 
insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPttStckDetail','공기구재고 상세','Y','공기구재고 상세','Y')
go 
 
  insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'lovWhToolPopup','공기구창고 검색 팝업','N','공기구창고 리스트 ','Y')
go 
 
 
   insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'lovToolsPopup','공기구번호 검색 팝업','N','공기구번호 리스트 ','Y')
go 
 
  insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPttRecList','공기구입고 목록','Y','공기구입고 목록','Y')
go 
 
    insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPttRecDetail','공기구입고 상세','Y','공기구입고 상세','Y')
go 
 
   insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPttIssList','공기구출고 목록','Y','공기구출고 목록','Y')
go 
 
    insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPttIssDetail','공기구출고 상세','Y','공기구출고 상세','Y')
go 
 
    insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPttRtnList','공기구반납 목록','Y','공기구반납 목록','Y')
go 
 
    insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPttRtnDetail','공기구반납 상세','Y','공기구반납 상세','Y')
go
 
     insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPttRentList','공기구대여현황 목록','Y','공기구대여현황 목록','Y')
go 
 
    insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPttDisList','공기구폐기 목록','Y','공기구폐기 목록','Y')
go 
 
    insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPttDisDetail','공기구폐기 상세','Y','공기구폐기 상세','Y')
go
 
      insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPttDisPartsList','공기구폐기자재 목록','Y','공기구폐기자재 목록','Y')
go 
 
    insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPttDisPartsDetail','공기구폐기자재 상세','Y','공기구폐기자재 상세','Y')
go
 
 
 /** 2016-09-06 박철완 추가    */
  insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use, page_type) values(NEXT VALUE FOR sqapage_id, 'maPlantMngList','공장코드목록','Y','공장코드목록','Y','MAIN')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use, page_type) values(NEXT VALUE FOR sqapage_id, 'maPlantMngDetail','공장코드상세','Y','공장코드상세','Y','SUB')
go 

 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maIfPtIssList','부품출고확정 Interface','Y','부품출고확정 Interface','Y')
go 
 /** 2016-09-07 김정우 부품마스터 Interface */
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maIfPtMstrList','부품마스터 Interface','Y','부품마스터 Interface','Y')
go 
 /** 2016-09-07 김정우 구매청구 Interface */
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maIfPtPoList','구매청구 Interface','Y','구매청구 Interface','Y')
go 
 /**2016-09-07 김정우 부품출고처리내역 */
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maIfPtIssErpList','부품출고처리내역 Interface','Y','부품출고처리내역 Interface','Y')
go 
 /**2016-09-07 김정우 재고 Interface */
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maIfPtStockList','재고 Interface','Y','재고 Interface','Y')
go 
  /**2016-09-08 이규선 예방작업일정(기간) */
     insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoSchedList','예방작업일정(기간) 목록','Y','예방작업일정(기간) 목록','Y')
go 
    insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoSchedDetail','예방작업일정(기간) 상세','Y','예방작업일정(기간) 상세','Y')
go
 values(NEXT VALUE FOR sqapage_id, 'maIfPtStockList','재고 Interface','Y','재고 Interface','Y')
go 
 
 /**2016-09-07 김정우 MES 가동시간 Interface */
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maIfMesRunList','MES가동시간 Interface','Y','MES가동시간 Interface','Y')
go 
 
 /**2016-09-07 김정우 MES 정지시간 Interface */
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maIfMesStopList','MES정지시간 Interface','Y','MES정지시간 Interface','Y')
go 
 
 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maPgUsrFieldList','유저화면설정','N','유저화면설정','Y')
go 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maPgUsrFieldDetail','유저화면설정 상세','N','유저화면설정상세','Y')
go 
 
    insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'lovPmNoPopup','예방작업번호 검색 팝업','N','예방작업번호 리스트 ','Y')
go 
 
 /** 2016-09-12 김정우 주간작업일정 추가 */
 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoWeekWoList','주간작업일정','Y','주간작업일정 목록','Y')
go 
 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maEqBdChart','설비별 BD 현황','Y','설비별 BD 현황','Y')
go 
 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maWkBdChart','주간 BD 현황','Y','주간 BD','Y')
go 
  /** 2016-09-20 김정우 일별 BD 지표(라인별) 추가 */
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maBmDailyLineChart','일별 BD 지표(라인별) ','Y','일별 BD 지표(라인별)','Y')
go 
 
   /** 2016-09-21 이규선 월간 BD 지표(라인별) 추가 */
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maBmMonthLineChart','월간 BD 지표(라인별) ','Y','월간 BD 지표(라인별)','Y')
go 
  /** 2016-09-21 김정우 일별 BD 지표 추가 */
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maBmDailyChart','일별 BD 지표','Y','일별 BD 지표','Y')
go 
 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maDbList','Dashboard List','N','Dashboard List','Y')
go 
 
/** 20160926 김정우 슬로박지표 추가 */
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maSlovakReport','슬로박 지표','Y','슬로박 지표','Y')
go 
 
 /** 20160928 이규선 체코지표 추가 */
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maCzechReport','체코 지표','Y','체코 지표','Y')
go 
 
 /** 2016-10-10김정우 작업그룹 LOV POPUP 추가 */
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'lovWkCtrPopup','작업그룹  팝업','N','작업그룹  팝업','Y')
go 
 
  insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoCalPopup','일정재조정팝업','N','일정재조정팝업','Y')
go 
 
 
 /** 2016-10-13 김정우 페이지 추가 */
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maPtPoList','발주이력 목록','Y','발주이력 목록','Y')
go 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maPtPoDetail','발주이력 상세 ','Y','발주이력 상세','Y')
go 
 
 /** 2016-10-17 김정우 페이지 추가 */
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maWkCtrList','작업그룹 목록','Y','작업그룹 목록','Y')
go 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maWkCtrDetail','작업그룹 상세 ','Y','작업그룹 상세','Y')
go 
 
  /** 2016-10-19 이규선 페이지 추가 */
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maMonitoring','상황실 모니터링','Y','상황실 모니터링','Y')
go 
 
   /** 2016-10-21 노정현 페이지 추가 */
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'lovPlantPopup','공장 LOV','N','공장 LOV','Y')
go 
 
/** 2016-10-24 노정현 페이지 추가 */
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maPtIssEmgList','긴급출고 [S/Part Emg. Issue]','Y','긴급출고 [S/Part Emg. Issue]','Y')
go 
 
   INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maPtIssEmgDetail','긴급출고 상세 [S/Part Emg. Issue Detail]','Y','긴급출고 상세 [S/Part Emg. Issue Detail]','Y')
go 

 /** 2016-10-24 김정우 페이지 추가 */
 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maResList','언어 목록','Y','언어 목록','Y')
go 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maResDetail','언어 상세 ','Y','언어 상세','Y')
go 
 
/** 2016-10-26 노정현 추가  */
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'lovEmgPartPopup','긴급출고선택 Popup','N','긴급출고선택 Popup','Y')
go 
 
/** 2016- 10-27 금형 관리  슬로바키아만 적용 */
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maMoldLayout','금형Layout 목록','Y','금형Layout 목록','Y')
go 
 
 
 /** 2016-10-27 ERP S/Parts Rec  */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
VALUES(NEXT VALUE FOR sqapage_id, 'maIfPtRecList','ERP S/Parts Rec ','Y','ERP S/Parts Rec','Y')
go

/** 2016-10-28 김정우 금형관리 추가 */

  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maMoldsList','금형관리 목록','Y','금형관리 목록','Y')
go 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maMoldsDetail','금형관리 상세 ','Y','금형관리 상세','Y')
go 
 
    /** 2016-10-31 이규선 페이지 추가 */
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maMonitorPopup',' 모니터링 팝업','N','모니터링 팝업','Y')
go 
   INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maMonitorPopup2',' 모니터링 팝업2','N','모니터링 팝업2','Y')
go 
   INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maMonitorPopup3',' 모니터링 팝업3','N','모니터링 팝업3','Y')
go 
   INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maMonitorPopup4',' 모니터링 팝업4','N','모니터링 팝업4','Y')
go 
 
/** 2016-11-03 노정현 페이지 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
VALUES(NEXT VALUE FOR sqapage_id, 'maHelpList','HelpDesk','Y','HelpDesk','Y')
go 
/** 2016-11-04 김정우 페이지 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
VALUES(NEXT VALUE FOR sqapage_id, 'maEqCtgTypeSelect','설비유형','N','설비유형 팝업','Y')
go 



/*2016.11.08일 설비상세를 세부유형으로 분리.*/
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use, page_type) values(NEXT VALUE FOR sqapage_id, 'maEqMachineMstrDetail','Machinery(기계장치) 상세','Y','Machinery(기계장치) 상세','Y','SUB')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use, page_type) values(NEXT VALUE FOR sqapage_id, 'maEqMoldMstrDetail','Molds(금형) 상세','Y','Molds(금형) 상세','Y','SUB')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use, page_type) values(NEXT VALUE FOR sqapage_id, 'maEqToolMstrDetail','Tools(공기구) 상세','Y','Tools(공기구) 상세','Y','SUB')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use, page_type) values(NEXT VALUE FOR sqapage_id, 'maEqBuildingMstrDetail','Buildings(건물,구축물) 상세','Y','Buildings(건물,구축물) 상세','Y','SUB')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use, page_type) values(NEXT VALUE FOR sqapage_id, 'maEqVehicleMstrDetail','Vehicles(차량운반구) 상세','Y','Vehicles(차량운반구) 상세','Y','SUB')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use, page_type) values(NEXT VALUE FOR sqapage_id, 'maEqUtilityMstrDetail','Utility(시설설비) 상세','Y','Utility(시설설비) 상세','Y','SUB')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use, page_type) values(NEXT VALUE FOR sqapage_id, 'maEqPartMstrDetail','Parts(부품) 상세','Y','Parts(부품) 상세','Y','SUB')
go 

/** 2016-11-16 노정현 페이지 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
VALUES(NEXT VALUE FOR sqapage_id, 'maWrkImpList','개선진행현황[Imp.Progress Status]','Y','개선진행현황[Imp.Progress Status]','Y')
go

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
VALUES(NEXT VALUE FOR sqapage_id, 'maWrkImpDetail','개선진행현황상세[Imp.Progress Status Detail]','Y','개선진행현황[Imp.Progress Status]','Y')
go 

/** 2016-11-21 김정우 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
VALUES(NEXT VALUE FOR sqapage_id, 'maGlMonthlyMtChart','글로벌 월간보전지표','Y','글로벌 월간보전지표','Y')
go 
/** 2016-11-22 김정우 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
VALUES(NEXT VALUE FOR sqapage_id, 'maGlStockList','공장별 현재고','Y','공장별 현재고','Y')
go 


/** 2016- 11-27 금형 관리  몬테레이만  적용 */
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maMtlMoldLayout','금형Layout 목록','Y','금형Layout 목록','Y')
go 

 /** 2016-12-20 페이지 추가 김정우 */
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultPmGmMstrDetail','작업결과(예방작업-일반) 상세','Y','작업결과(예방작업-일반) 상세','Y')
go
 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPmGmMstrDetail','예방작업기준(일반) 상세','Y','예방작업기준(일반) 상세','Y')
go 
 
 
 /** 2017-01-05 몬테레이 일일레포트 이규선 */
     insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maMtlDailyReport','Daily Report','Y','Daily Report','N')
go 
 /** 2017-01-05 김정우 작업시간 설정 */
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maLineTimeList','작업시간설정 목록','Y','작업시간설정 목록','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maLineTimeDetail','작업시간설정 상세','Y','작업시간설정 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maLineTimeDowList','작업시간설정 - 요일별 설정 목록','Y','작업시간설정 - 요일별 설정 목록','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maLineTimeDowDetail','작업시간설정 - 요일별 설정 상세','Y','작업시간설정 - 요일별 설정 상세','Y')
go 
 /** 2017-01-10 김정우 일별활동 탭페이지 추가 */
 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoDailyActList','일일작업 - Main Activities 목록','N','일일작업 - Main Activities 목록','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoDailyActDetail','일일작업 - Main Activities 상세','N','일일작업 - Main Activities 상세','Y')
go
 
 -----------------------------------------------------------------------------
--------------------------------유한킴벌리 patch완료 2017.01.13일-------------------
-----------------------------------------------------------------------------


  /** 2017-01-12 몬테레이 레포트 이규선 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maMtlReport','몬테레이 지표','Y','몬테레이 지표','N')
go 
 
  /** 2017-01-18 김정우 작업요청 추가 */
 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoReqList','요청리스트 목록','Y','요청리스트 목록','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoReqDetail','요청리스트 상세','Y','요청리스트 상세','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoReqResList','요청리스트-처리사항 목록','Y','요청리스트-처리사항 목록','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoReqResDetail','요청리스트-처리사항 상세','Y','요청리스트-처리사항 상세','Y')
go
 
 INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, IS_CHKAUTH, REMARK, IS_USE)
 VALUES  (NEXT VALUE FOR sqapage_id, 'maWoReqDocLibList', '작업요청서 첨부문서 목록', 'Y', '작업요청서 첨부문서 목록', 
    'Y')
go
    
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, IS_CHKAUTH, REMARK, IS_USE)
 VALUES (NEXT VALUE FOR sqapage_id, 'maWoReqDocLibDetail', '작업요청서 첨부문서 상세', 'Y', '작업요청서 첨부문서 상세', 
    'Y')
go
    INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, IS_CHKAUTH, REMARK, IS_USE)
 VALUES  (NEXT VALUE FOR sqapage_id, 'maWoReqResDocLibList', ' 작업요청서 처리사항 첨부문서 목록', 'Y', '작업요청서 처리사항 첨부문서 목록', 
    'Y')
go
    
INSERT INTO TAPAGE
   (PAGE_ID, FILE_NAME, DESCRIPTION, IS_CHKAUTH, REMARK, IS_USE)
 VALUES
   (NEXT VALUE FOR sqapage_id, 'maWoReqResDocLibDetail', '작업요청서 처리사항 첨부문서 상세', 'Y', '작업요청서 처리사항 첨부문서 상세', 
    'Y')
go
    /**2017-01-25 김정우 추가 */
    
insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoDayRptList','업무일지 목록','Y','업무일지 목록','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoDayRptDetail','업무일지 상세','Y','업무일지 상세','Y')
go
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoDayRptWoList','업무일지-wolist 목록','Y','업무일지-wolist 목록','Y')
go
 
 INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, IS_CHKAUTH, REMARK, IS_USE)
 VALUES  (NEXT VALUE FOR sqapage_id, 'maWoDayRptDocLibList', '업무일지 첨부문서 목록', 'Y', '업무일지 첨부문서 목록', 'Y')
go
    
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, IS_CHKAUTH, REMARK, IS_USE)
 VALUES (NEXT VALUE FOR sqapage_id, 'maWoDayRptDocLibDetail', '업무일지 첨부문서 상세', 'Y', '업무일지 첨부문서 상세', 'Y')
go
 
 
 /**2017-02-11 박철완 추가 */
  insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use, page_type)
values(NEXT VALUE FOR sqapage_id, 'maPmCalMstrDetail','예방보전 검교정','Y','예방보전 검교정','Y','SUB')
go


insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use, page_type)
values(NEXT VALUE FOR sqapage_id, 'maWoResultPmCalMstrDetail','예방보전 검교정 작업결과','Y','예방보전 검교정 작업결과','Y','SUB')
go

/**2017-02-21 김정우 추가 */
insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoResultBmBaseMstrDetail','작업결과(기본) 상세','Y','작업결과(기본) 상세','Y')
go

 /** 2017-03-02 김정우 추가 */
insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maWoTopFiveList','WorkTime Top5','Y','WorkTime Top5','Y')
go
 
 
 /** 2017-03-08 노정현 추가 */
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'lovAcDirPopup','AC LOV POPUP','N','COMMON LOV OF AC','Y')
go
 
  INSERT INTO TSFILE(sfile_id, sfile_no, sfile_name, description, credate, creby, issystem, remark, isuse) 
 VALUES(sqasfile_id.NEXTVAL, sqasfile_id.CURRVAL , 'gaPgMngList','테스트 페이지', TO_CHAR(SYSDATE,'yyyymmdd'), 'ADMIN',  'N',' 테스트 페이지','Y')
go
 
  INSERT INTO TSFILE(sfile_id, sfile_no, sfile_name, description, credate, creby, issystem, remark, isuse) 
 VALUES(sqasfile_id.NEXTVAL, sqasfile_id.CURRVAL , 'gaPgMngDetail','테스트 페이지 상세', TO_CHAR(SYSDATE,'yyyymmdd'), 'ADMIN',  'N',' 테스트 페이지 상세','Y')
go
 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maWrkImpCnDocList','개선진행형황 첨부파일 목록','N','개선진행현황 첨부파일 목록','Y')
go
 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maWrkImpCnDocDetail','개선진행형황 첨부파일 상세','N','개선진행현황 첨부파일 상세','Y')
go
 
  /** 2017-03-13 브라질  일일레포트 이규선 */
     insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maBzDailyReport','Daily Report','Y','Daily Report','N')
go 
 
  -----------------------------------------------------------------------------
--------------------------------유한킴벌리 patch완료 2017.03.15일-------------------
-----------------------------------------------------------------------------

  /** 2017-03-16 브라질  월간레포트 이규선 */
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maBzReport','브라질 지표','Y','브라질 지표','N')
go 
 
 
 /*2017-03-21 노정현 추가 */
 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'mcMenuList','메뉴사용자 목록','N','메뉴사용자 목록','Y')
go
 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'mcMenuDetail','메뉴사용자 상세','N','메뉴사용자 상세','Y')
go

 /** 2017-03-22 노정현 추가  */
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'mcUserList','메뉴사용자 목록','N','메뉴사용자 목록','Y')
go
 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'mcUserDetail','메뉴사용자 상세','N','메뉴사용자 상세','Y')
go
 
   INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'mcUserPw','메뉴사용자 비밀번호','N','메뉴사용자 비밀번호','Y')
go
 /** 2017-03-23 김정우 추가 */
 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maGaDailyReport','GA Daily Report','Y','GA Daily Report','N')
go 
 
 
 /** 2017-03-23 노정현 추가  */
 
 update TAPAGE set is_chkauth = 'N' WHERE file_name IN ('maGrdUsrColList','maGrdUsrColDetail')
go
 
   

 /**2017-03-24 김정우 추가 */
 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPgMngGrdColDetail','화면-컬럼 상세','N','화면-컬럼 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPgMngGrdColList','화면-컬럼 목록','N','화면-컬럼 목록','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPgMngGrdDetail','화면-컬럼정의 상세','N','화면-컬럼정의 상세','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maPgMngGrdList','화면-컬럼정의','N','화면-컬럼정의','Y')
go 
 

  /**2017-04-04 이규선 추가 */
  insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maTableColDetail','데이터 테이블 컬럼 상세','N','','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maTableColList','데이터 테이블 컬럼 목록','N','','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maTableDetail','데이터 테이블 상세','N','','Y')
go 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'maTableList','데이터 테이블 목록','N','','Y')
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
 
/** 2017-04-07 노정현 사용자데이터 조회 추가 */
 
   INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'mcDataSelectList','사용자데이터조회 목록','N','사용자데이터조회 목록','Y')
go
 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'mcDataSelectDetail','사용자데이터조회 상세','N','사용자데이터조회 상세','Y')
go
 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'mcDataSelectScript','사용자데이터조회 Script','N','사용자데이터조회 Script','Y')
go

  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'mcDataSelectPopup','사용자데이터조회 결과팝업','N','사용자데이터조회 결과팝업','Y')
go
 
 
  /** 2017-04-10 이규선 추가 */
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'lovRefColumnPopup','REF Column POPUP','N','Ref Column Popup','Y')
go
 
 
-----------------------------------------------------------------------------
--------------------------------유한킴벌리 patch완료 2017.04.12일-------------------
-----------------------------------------------------------------------------


/** 2017-05-17 노정현 사용자레포트  추가 */
 
   INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maUserRptList','사용자레포트 목록','N','사용자레포트 목록','Y')
 go
 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maUserRptDetail','사용자레포트 상세','N','사용자레포트 상세','Y')
 go
 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maUserRptDesign','사용자레포트 Design','N','사용자레포트 Design','Y')
go
 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maUserRptPopup','사용자레포트 팝업','N','사용자레포트 팝업','Y')
 go
 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maUserRptTableList','사용자레포트 테이블 목록','N','사용자레포트 테이블 목록','Y')
go
  
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maUserRptTableDetail','사용자레포트 테이블 상세','N','사용자레포트 테이블 상세','Y')
go
  
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maUserRptColList','사용자레포트 항목 목록','N','사용자레포트 항목 목록','Y')
 go
 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maUserRptColDetail','사용자레포트 항목 상세','N','사용자레포트 항목 상세','Y')
 go
 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maUserRptParamList','사용자레포트 조건 목록','N','사용자레포트 조건 목록','Y')
 go
 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maUserRptParamDetail','사용자레포트 조건 상세','N','사용자레포트 조건 상세','Y')
 go
 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maUserRptOrdList','사용자레포트 정렬 목록','N','사용자레포트 정렬 목록','Y')
 go
 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maUserRptOrdDetail','사용자레포트 정렬 상세','N','사용자레포트 정렬 상세','Y')
 go
 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maUserRptJoinList','사용자레포트 조인 목록','N','사용자레포트 조인 목록','Y')
 go
 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'maUserRptJoinDetail','사용자레포트 조인 상세','N','사용자레포트 조인 상세','Y')
 go

 
 
 
 /** 2017-07-04 박철완 */
 /*금형목록 페이지 추가.*/
insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) values(NEXT VALUE FOR sqapage_id, 'maEqMstrMoldList','금형리스트','Y','금형리스트','Y');
insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) values(NEXT VALUE FOR sqapage_id, 'maEqMstrMoldDetail','금형상세','Y','금형상세화면','Y');
/*금형생산 품목 리스트페이지 추가*/
insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) values(NEXT VALUE FOR sqapage_id, 'maEqMstrMoldProdList','금형생산품목리스트','Y','금형생산품목리스트','Y');
/*금형생산 품목 상세페이지 추가*/
insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) values(NEXT VALUE FOR sqapage_id, 'maEqMstrMoldProdDetail','금형생산품목상세','Y','금형생산품목상세','Y');

/*금형갱신이력  리스트페이지 추가*/
insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) values(NEXT VALUE FOR sqapage_id, 'maEqMstrMoldModHistList','금형갱신이력리스트','Y','금형갱신이력리스트','Y');
/*금형갱신이력 상세페이지 추가*/
insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) values(NEXT VALUE FOR sqapage_id, 'maEqMstrMoldModHistDetail','금형갱신이력상세','Y','금형갱신이력상세','Y');



/** 2017-07-05 차한결 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type, page_categ) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'assetListTcycleList','설비자산 - 설비 - 교정주기(목록)','N','설비자산 - 설비 - 교정주기(목록)','Y', 'SUB','ASSET_LIST');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type, page_categ) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'assetListTcycleDetail','설비자산 - 설비 - 교정주기(상세)','N','설비자산 - 설비 - 교정주기(상세)','Y', 'SUB','ASSET_LIST');

/** 2017-07-07 차한결 추가 */
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workListCavalList','검교정(상세)-측정값 목록','Y','검교정(상세)-측정값 목록','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workListCavalDetail','검교정(상세)-측정값 상세','Y','검교정(상세)-측정값 상세','Y','SUB');
 /** 2017-07-11 김정우 추가 */
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR SQAPAGE_ID, 'consultCompTerminalList','접근터미널 목록','Y','컨설트 - 접근터미널 목록','Y','MAIN'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR SQAPAGE_ID, 'consultCompTerminalDetail','접근터미널 상세','Y','컨설트 - 접근터미널 목록 - 접근터미널 목록','Y','SUB');
 
/** 2017-07-11 노정현 추가 */
 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type)
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'failLibraryList','고장Library(목록)','N','고장Library(목록)','Y','MAIN')
 go
 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type)
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'failLibraryDetail','고장Library(상세)','N','고장Library(상세)','Y','SUB')
 go
 
 /** 2017-07-11 차한결 추가 */
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListRInsDetail','작업관리 - 예방작업 - 예방작업[점검](상세 정기점검)','Y','작업관리 - 예방작업 - 예방작업[점검](상세 정기점검)','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListEInsDetail','작업관리 - 예방작업 - 예방작업[점검](상세 특별점검)','Y','작업관리 - 예방작업 - 예방작업[점검](상세 특별점검)','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListDInsDetail','작업관리 - 예방작업 - 예방작업[점검](상세 일상점검)','Y','작업관리 - 예방작업 - 예방작업[점검](상세 일상점검)','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListEquipList','작업관리 - 예방작업 - 예방작업[점검] - 설비(목록)','Y','작업관리 - 예방작업 - 예방작업[점검] - 설비(목록)','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListEquipDetail','작업관리 - 예방작업 - 예방작업[점검] - 설비(상세)','Y','작업관리 - 예방작업 - 예방작업[점검] - 설비(상세)','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListSchdList','작업관리 - 예방작업 - 예방작업[점검] - 일자(목록)','Y','작업관리 - 예방작업 - 예방작업[점검] - 일자(목록)','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListSchdDetail','작업관리 - 예방작업 - 예방작업[점검] - 일자(상세)','Y','작업관리 - 예방작업 - 예방작업[점검] - 일자(상세)','Y','SUB'); 
 

/** 2017-07-12 노정현 추가 */

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type)
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'reqWorkReswoDetail','작업요청 처리사항(상세)','N','작업요청 처리사항(상세)','Y','SUB')
 go

 
  /**2017-07-18 노정현 추가   */
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type)
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'docCtgList','문서분류체계  (목록)','N','문서부류체계 (목록)','Y','MAIN')
 go
 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type)
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'docCtgDetail','문서부류체계 (상세)','N','문서부류체계 (상세)','Y','SUB')
 go
 
  /** 2017-07-20 노정현 추가  */
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maEqPartDocLibList','설비Part 첨부문서','N','설비Mold 첨부문서','Y')go
 
   INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maEqPartDocLibDetail','설비Part 첨부문서(상세)','N','설비Mold 첨부문서(상세)','Y')go
 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maEqUtilityDocLibList','설비Util 첨부문서','N','설비Mold 첨부문서','Y')go
 
   INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maEqUtilityDocLibDetail','설비Util 첨부문서(상세)','N','설비Mold 첨부문서(상세)','Y')go
 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maEqVehicleDocLibList','설비Vehicle 첨부문서','N','설비Mold 첨부문서','Y')go
 
   INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maEqVehicleDocLibDetail','설비Vehicle 첨부문서(상세)','N','설비Mold 첨부문서(상세)','Y')go
 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maEqBuildingDocLibList','설비Building 첨부문서','N','설비Mold 첨부문서','Y')go
 
   INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maEqBuildingDocLibDetail','설비Building 첨부문서(상세)','N','설비Mold 첨부문서(상세)','Y')go
 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maEqToolDocLibList','설비Tool 첨부문서','N','설비Mold 첨부문서','Y')go
 
   INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maEqToolDocLibDetail','설비Tool 첨부문서(상세)','N','설비Mold 첨부문서(상세)','Y')go
 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maEqMoldDocLibList','설비Mold 첨부문서','N','설비Mold 첨부문서','Y')go
 
   INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maEqMoldDocLibDetail','설비Mold 첨부문서(상세)','N','설비Mold 첨부문서(상세)','Y')go
 
   INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maPtDocLibList','자재마스터 첨부문서','N','자재마스터 첨부문서','Y')go
 
   INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maPtDocLibDetail','자재마스터 첨부문서(상세)','N','자재마스터 첨부문서(상세)','Y')go
 

  /** 2017-07-21 차한결 추가  */ 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'consultCompWrkcalList','컨설팅 - 회사설정 - 근무일달력(목록)','Y','컨설팅 - 회사설정 - 근무일달력(목록)','Y','MAIN'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'consultCompWrkcalDetail','컨설팅 - 회사설정 - 근무일달력(상세)','Y','컨설팅 - 회사설정 - 근무일달력(상세)','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'consultCompListLov','회사 팝업','N','회사 팝업','Y','LOV');
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'consultCompWrkcalDowsetList','컨설팅 - 회사설정 - 근무일달력(상세) - 휴무요일(목록)','Y','컨설팅 - 회사설정 - 근무일달력(상세) - 휴무요일(목록)','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'consultCompWrkcalDowsetDetail','컨설팅 - 회사설정 - 근무일달력(상세) - 휴무요일(상세)','Y','컨설팅 - 회사설정 - 근무일달력(상세) - 휴무요일(상세)','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'consultCompWrkcalDaysetList','컨설팅 - 회사설정 - 근무일달력(상세) - 휴무일(목록)','Y','컨설팅 - 회사설정 - 근무일달력(상세) - 휴무일(목록)','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'consultCompWrkcalDaysetDetail','컨설팅 - 회사설정 - 근무일달력(상세) - 휴무일(상세)','Y','컨설팅 - 회사설정 - 근무일달력(상세) - 휴무일(상세)','Y','SUB');
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'consultCompWrkcalLov','근무달력 팝업','N','근무달력 팝업','Y','LOV');
 
 
 
 /** 2017-07-29 박철완 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'certEmpList','자격증 보유사원 목록','Y','자격증 보유사원 목록','Y');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'certEmpDetail','자격증 보유사원 상세','Y','자격증 보유사원 상세','Y');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'eduEmpList','교육이수 사원목록','Y','교육이수 사원목록','Y');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'eduEmpDetail','교육이수 사원상세','Y','교육이수 사원상세','Y');


/** 2017-07-30 박철완 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workCalPmRInsYearList','예방점검일정(연간)','Y','예방점검일정(연간)','Y', 'MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workCalPmRInsMonthList','예방점검일정(월간)','Y','예방점검일정(월간)','Y','MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workCalPmRInsPeriodList','예방점검일정(기간)','Y','예방점검일정(기간)','Y','MAIN');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workCalPmDInsMonthList','일상점검일정','Y','일상점검일정','Y','MAIN');

 /** 2017-08-02 차한결 추가 */
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListRprEquipList','예방작업[수리](상세 부품없음) - 설비(목록)','Y','예방작업[수리](상세 부품없음) - 설비(목록)','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListRprEquipDetail','예방작업[수리](상세 부품없음) - 설비(상세)','Y','예방작업[수리](상세 부품없음) - 설비(상세)','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListRplEquipList','예방작업[수리](상세 부품있음) - 설비(목록)','Y','예방작업[수리](상세 부품있음) - 설비(목록)','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListRplEquipDetail','예방작업[수리](상세 부품있음) - 설비(상세)','Y','예방작업[수리](상세 부품있음) - 설비(상세)','Y','SUB');
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListGmEquipList','예방작업[일반](상세) - 설비(목록)','Y','예방작업[일반](상세) - 설비(목록)','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListGmEquipDetail','예방작업[일반](상세) - 설비(상세)','Y','예방작업[일반](상세) - 설비(상세)','Y','SUB');
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListRInsEquipList','예방작업[점검](상세 정기점검) - 설비(목록)','Y','예방작업[점검](상세 정기점검) - 설비(목록)','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListRInsEquipDetail','예방작업[점검](상세 정기점검) - 설비(상세)','Y','예방작업[점검](상세 정기점검) - 설비(상세)','Y','SUB');
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListDInsEquipList','예방작업[점검](상세 일상점검) - 설비(목록)','Y','예방작업[점검](상세 일상점검) - 설비(목록)','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListDInsEquipDetail','예방작업[점검](상세 일상점검) - 설비(상세)','Y','예방작업[점검](상세 일상점검) - 설비(상세)','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListEInsEquipList','예방작업[점검](상세 특별점검) - 설비(목록)','Y','예방작업[점검](상세 특별점검) - 설비(목록)','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListEInsEquipDetail','예방작업[점검](상세 특별점검) - 설비(상세)','Y','예방작업[점검](상세 특별점검) - 설비(상세)','Y','SUB');
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
  VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListInsEquipList','예방작업[점검](상세) - 설비(목록)','Y','예방작업[점검](상세) - 설비(목록)','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListInsEquipDetail','예방작업[점검](상세) - 설비(상세)','Y','예방작업[점검](상세) - 설비(상세)','Y','SUB'); 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
  VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListCalEquipList','예방작업[검교정](상세) - 설비(목록)','Y','예방작업[검교정](상세) - 설비(목록)','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListCalEquipDetail','예방작업[검교정](상세) - 설비(상세)','Y','예방작업[검교정](상세) - 설비(상세)','Y','SUB'); 

 
 
/** 2017-08-04 박철완 추가  */
update tapage set file_name = 'persPrivUsrFieldDetail' where file_name = 'maPgUsrFieldDetail';
update tapage set file_name = 'persPrivUsrFieldList' where file_name = 'maPgUsrFieldList';
update TAEHMENU set file_name = 'persPrivUsrFieldDetail' where file_name = 'maPgUsrFieldDetail';
update TAEHMENU set file_name = 'persPrivUsrFieldList' where file_name = 'maPgUsrFieldList';

/** 2017-08-08 김정우 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'consultPgmGuideList','프로그램 가이드 목록','Y','프로그램 가이드 목록','Y', 'MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'consultPgmGuideDetail','프로그램 가이드 상세','Y','프로그램 가이드 상세','Y', 'MAIN');

 /** 2017-08-08 차한결 추가  */
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'rcmSysList','RCM - System분석 목록','Y','RCM - System분석 목록','Y','MAIN'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'rcmSysDetail','RCM - System분석 상세','Y','RCM - System분석 상세','Y','SUB');
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'rcmSysFDefList','RCM - System분석 상세 - 기능정의 목록','Y','RCM - System분석 상세 - 기능정의 목록','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'rcmSysFDefDetail','RCM - System분석 상세 - 기능정의 상세','Y','RCM - System분석 상세 - 기능정의 상세','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
  VALUES(NEXT VALUE FOR SQAPAGE_ID, 'rcmSysFEnvList','RCM - System분석 상세 - 기능정의 상세 - 운전환경 목록','Y','RCM - System분석 상세 - 기능정의 상세 - 운전환경 목록','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'rcmSysFEnvDetail','RCM - System분석 상세 - 기능정의 상세 - 운전환경 상세','Y','RCM - System분석 상세 - 기능정의 상세 - 운전환경 상세','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'rcmSysEqList','RCM - System분석 상세 - 설비설정 목록','Y','RCM - System분석 상세 - 설비설정 목록','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'rcmSysEqDetail','RCM - System분석 상세 - 설비설정 상세','Y','RCM - System분석 상세 - 설비설정 상세','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'rcmSysEqAsmbList','RCM - System분석 상세 - 설비설정 상세 - 설비부위 목록','Y','RCM - System분석 상세 - 설비설정 상세 - 설비부위 목록','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'rcmSysEqAsmbDetail','RCM - System분석 상세 - 설비설정 상세 - 설비부위 상세','Y','RCM - System분석 상세 - 설비설정 상세 - 설비부위 상세','Y','SUB'); 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'rcmSysEmpList','RCM - System분석 상세 - 분석자 목록','Y','RCM - System분석 상세 - 분석자 목록','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'rcmSysEmpDetail','RCM - System분석 상세 - 분석자 상세','Y','RCM - System분석 상세 - 분석자 상세','Y','SUB');
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'rcmSysDocLibList','RCM - System분석 상세 - 첨부파일 목록','Y','RCM - System분석 상세 - 첨부파일 목록','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'rcmSysDocLibDetail','RCM - System분석 상세 - 첨부파일 상세','Y','RCM - System분석 상세 - 첨부파일 상세','Y','SUB');

 
 /** 2017-08-08 김정우 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'rcmCrityList','Criticality Matrix 목록','Y','Criticality Matrix 목록','Y', 'MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'rcmCrityDetail','Criticality Matrix 상세','Y','Criticality Matrix 상세','Y', 'MAIN');

/** 2017-08-10 김정우 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'rcmCrityColList','Criticality Matrix Col 목록','Y','Criticality Matrix Col 목록','Y', 'SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'rcmCrityColDetail','Criticality Matrix Col 상세','Y','Criticality Matrix Col 상세','Y', 'SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'rcmCrityRowList','Criticality Matrix Row 목록','Y','Criticality Matrix Row 목록','Y', 'SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'rcmCrityRowDetail','Criticality Matrix Row 상세','Y','Criticality Matrix Row 상세','Y', 'SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'rcmCrityValList','Criticality Matrix Val 목록','Y','Criticality Matrix Val 목록','Y', 'SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'rcmCrityValDetail','Criticality Matrix Val 상세','Y','Criticality Matrix Val 상세','Y', 'SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'rcmCrityMatrixList','Criticality Matrix','N','Criticality Matrix','Y', 'LOV');


 /** 2017-08-19 박철완 추가  */
update tapage set file_name = 'maWoResultPmCalEtcMstrDetail' where file_name = 'maWoResultPmCalMstrDetail';
update tapage set file_name = 'maPmCalEtcMstrDetail' where file_name = 'maPmCalMstrDetail';
update TACDSYSD  set param1 ='maWoResultPmCalEtcMstrDetail'    , param2 = 'maPmCalEtcMstrDetail'   WHERE list_type='PMC_TYPE' and cdsysd_no = 'ETC'


/** 2017-08-21 김영주 추가 */
INSERT INTO TAPAGE (page_id, file_name, description, REMARK, is_use, is_chkauth, page_type,page_categ) 
VALUES(NEXT VALUE FOR sqapage_id, 'consultCompUserList','컨설트 사용자(목록)','컨설트 사용자(목록)', 'Y','Y','MAIN','');
INSERT INTO TAPAGE (page_id, file_name, description, REMARK, is_use, is_chkauth, page_type,page_categ)
VALUES(NEXT VALUE FOR sqapage_id, 'consultCompUserDetail','컨설트 사용자(상세)','컨설트 사용자(상세)', 'Y','Y','MAIN','');

/** 2017-08-23 김정우 추가 */

  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'assBaseList','설비등급 평가기준(목록)','Y','설비등급 평가기준(목록)','Y', 'MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'assBaseDetail','설비등급 평가기준(상세)','Y','설비등급 평가기준(상세)','Y', 'MAIN');
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'assBaseGradeList','등급기준(목록)','Y','등급기준(목록)','Y', 'SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'assBaseGradeDetail','등급기준(상세)','Y','등급기준(상세)','Y', 'SUB');
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'assBasePointList','평가항목(목록)','Y','평가항목(목록)','Y', 'SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'assBasePointDetail','평가항목(상세)','Y','평가항목(상세)','Y', 'SUB');
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'assBasePointValList','평가기준(목록)','Y','평가기준(목록)','Y', 'SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'assBasePointValDetail','평가기준(상세)','Y','평가기준(상세)','Y', 'SUB');
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'assBaseDocLibList','설비등급 평가기준 첨부문서','N','설비등급 평가기준 첨부문서','Y');
   INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'assBaseDocLibDetail','설비등급 평가기준 첨부문서(상세)','N','설비등급 평가기준 첨부문서(상세)','Y');
 
 
 
 /** 2017-08-24 김정우 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'eqLocValLov','EQ LOCATION AC LOV','N','EQ LOCATION AC LOV','Y', 'LOV');
 
/** 2017-08-24 박철완 추가 */ 
insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'reqWorkList','요청리스트 목록','Y','요청리스트 목록','Y');
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'reqWorkDetail','요청리스트 상세','Y','요청리스트 상세','Y');
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'reqWorkResList','요청리스트-처리사항 목록','Y','요청리스트-처리사항 목록','Y');
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'reqWorkResDetail','요청리스트-처리사항 상세','Y','요청리스트-처리사항 상세','Y');
  
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, IS_CHKAUTH, REMARK, IS_USE)
VALUES  (NEXT VALUE FOR sqapage_id, 'reqWorkDocLibList', '작업요청서 첨부문서 목록', 'Y', '작업요청서 첨부문서 목록', 'Y');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, IS_CHKAUTH, REMARK, IS_USE)
VALUES (NEXT VALUE FOR sqapage_id, 'reqWorkDocLibDetail', '작업요청서 첨부문서 상세', 'Y', '작업요청서 첨부문서 상세', 'Y');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, IS_CHKAUTH, REMARK, IS_USE)
VALUES  (NEXT VALUE FOR sqapage_id, 'reqWorkResDocLibList', ' 작업요청서 처리사항 첨부문서 목록', 'Y', '작업요청서 처리사항 첨부문서 목록','Y');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, IS_CHKAUTH, REMARK, IS_USE)
VALUES(NEXT VALUE FOR sqapage_id, 'reqWorkResDocLibDetail', '작업요청서 처리사항 첨부문서 상세', 'Y', '작업요청서 처리사항 첨부문서 상세','Y');


insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(NEXT VALUE FOR sqapage_id, 'assetListMstrToolsList','설비검교정리스트 목록','Y','설비검교정리스트 목록','Y');
 
/** 2017-08-24 김정우 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'plantValLov','PLANT AC LOV','N','PLANT AC LOV','Y', 'LOV');


 /** 2017-08-25 김영주 추가 */
INSERT INTO TAPAGE (page_id, file_name, description, is_use, is_chkauth, page_type,page_categ) 
VALUES(NEXT VALUE FOR sqapage_id, 'assAssetList','설비등급 평가(목록)', 'Y','Y','MAIN','');
INSERT INTO TAPAGE (page_id, file_name, description, is_use, is_chkauth, page_type,page_categ)
VALUES(NEXT VALUE FOR sqapage_id, 'assAssetDetail','설비등급 평가(상세)', 'Y','Y','MAIN','');

/** 2017-08-28 김정우 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'assBaseValLov','ASS BASE AC LOV','N','설비등급 평가기준 AC LOV','Y', 'LOV');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'assBasePointValValLov','ASS BASE POINT VALUE AC LOV','N','평가항목 평가기준 AC LOV','Y', 'LOV');

/** 2017-08-30 김영주 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'assAssetScoreList','평가점수 (목록)','Y','Ass Asset Score 목록','Y', 'SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'assAssetScoreDetail','평가점수 (상세)','Y','Ass Asset Score 상세','Y', 'SUB');

/**2017-08-30 장효성 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) 
 VALUES(NEXT VALUE FOR sqapage_id, 'invtPrcTpItemDocLibList','구매절차 첨부문서 목록','Y','구매절차 첨부문서 목록','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) 
 VALUES(NEXT VALUE FOR sqapage_id, 'invtPrcTpItemDocLibDetail','구매절차 첨부문서 상세','Y','구매절차 첨부문서 상세','Y','SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'consultCompDeptList','컨설트 부서(목록)','Y','컨설트 부서(목록)','Y', 'MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'consultCompDeptDetail','컨설트 부서(상세)','Y','컨설트 부서(상세)','Y', 'MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'invtPrcTpList','구매절차 목록','Y','구매절차 목록','Y');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'invtPrcTpDetail','구매절차 상세','Y','구매절차 상세','Y');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'invtPrcTpItemList','구매절차 Item 목록','Y','구매절차 Item 목록','Y');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'invtPrcTpItemDetail','구매절차 Item 상세','Y','구매절차 Item 상세','Y');

 
 /** 2017-08-30 이근환 추가 */
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 values(NEXT VALUE FOR sqapage_id, 'consultCompEmpList','사원 목록','N','사원 목록','Y','MAIN'); 
 insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 values(NEXT VALUE FOR sqapage_id, 'consultCompEmpDetail','사원 상세','N','사원 상세','Y','SUB'); 
 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'mgrWorkflowList','시스템관리 - 승인Flow 목록','Y','시스템관리 - 승인Flow 목록','Y','MAIN'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'mgrWorkflowDetail','시스템관리 - 승인Flow 상세','Y','시스템관리 - 승인Flow 상세','Y','SUB'); 

 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'mgrWorkflowPhaseList','시스템관리 - 승인Flow 상세 - 절차 목록','Y','시스템관리 - 승인Flow 상세 - 절차 목록','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'mgrWorkflowPhaseDetail','시스템관리 - 승인Flow 상세 - 절차 상세','Y','시스템관리 - 승인Flow 상세 - 절차 상세','Y','SUB'); 

 
 /** 2017-08-31 김정우 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'tableValLov','테이블 AC LOV','N','테이블 AC LOV','Y', 'LOV');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'tableColValLov','테이블 컬럼  AC LOV','N','테이블 컬럼 AC LOV','Y', 'LOV');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'partsValLov','부품 컬럼  AC LOV','N','부품 컬럼 AC LOV','Y', 'LOV');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'ehMenuValLov','컨설트 메뉴 AC LOV','N','컨설트 메뉴 AC LOV','Y', 'LOV');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'langValLov','다국어 AC LOV','N','다국어 AC LOV','Y', 'LOV');

 /** 2017-09-01 이근환 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'rcmTaskMapListLov','Task Map List Ac Lov','N','Task Map List Ac Lov','Y','LOV'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'invtPrcTpLov','구매절차 Ac Lov','N','구매절차 Ac Lov','Y','LOV'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'invtLov','설비투자 Ac Lov','N','설비투자 Ac Lov','Y','LOV'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'invtPrcPhLov','구매절차 소분류 Ac Lov','N','구매절차 소분류 Ac Lov','Y','LOV'); 


/** 2017-09-04 장효성 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'compValLov','회사 팝업','N','회사 팝업','Y','LOV');
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'eqAsmbValLov','설비부위  LOV','N','설비부위 LOV','Y','LOV');
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'lovTaskMapList','TaskMap  LOV','N','TaskMap LOV','Y','LOV');
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'crityValLov','Critycality  LOV','N','Critycality LOV','Y','LOV');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'lovEduList','교육과정  LOV','N','교육과정  LOV','Y','LOV');
 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'lovCertList','자격증  LOV','N','자격증  LOV','Y','LOV');
 
   /** 2017-09-04 이근환 추가  */
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'pageValLov','Page Ac Lov','N','Page Ac Lov','Y','LOV'); 
 
 /** 2017-09-05 김정우 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'wkCtrValLov','작업그룹 AC LOV','N','작업그룹 AC LOV','Y', 'LOV');

   /** 2017-09-05 이근환 추가  */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'menuValLov','Menu Ac Lov','N','Menu Ac Lov','Y','LOV'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'consultCompWarehouseLov','Warehouse Ac Lov','N','Warehouse Ac Lov','Y','LOV'); 
/** 2017-09-05 김정우 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'pmValLov','점검 AC LOV','N','점검 AC LOV','Y', 'LOV');


 /** 2017-09-06 노정현 추가  */
 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoResultPmCalDocLibList','Work PMCAL 첨부문서','N','Work PMCAL 첨부문서','Y');
 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoResultPmGmDocLibList','Work PMGM 첨부문서','N','Work PMGM 첨부문서','Y');
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoResultTrEleDocLibList','Work TRELE 첨부문서','N','Work TRELE 첨부문서','Y');
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoResultCmRplDocLibList','Work CMRPL 첨부문서','N','Work CMRPL 첨부문서','Y');
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoResultCmRprDocLibList','Work CMRPR 첨부문서','N','Work CMRPR 첨부문서','Y');
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoResultBmOilDocLibList','Work BMOIL 첨부문서','N','Work BMOIL 첨부문서','Y');
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoResultBmRplDocLibList','Work PMRPL 첨부문서','N','Work PMRPL 첨부문서','Y');
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoResultBmRprDocLibList','Work BMRPR 첨부문서','N','Work BMRPR 첨부문서','Y');
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoResultPmOilDocLibList','Work PMOIL 첨부문서','N','Work PMOIL 첨부문서','Y');
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoResultPmClnDocLibList','Work PMRPR 첨부문서','N','Work PMCLN 첨부문서','Y');
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoResultPmRplDocLibList','Work PMRPR 첨부문서','N','Work PMRPL 첨부문서','Y');
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoResultPmRprDocLibList','Work PMRPR 첨부문서','N','Work PMRPR 첨부문서','Y');
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoResultPmInsDocLibList','Work 첨부문서','N','Work 첨부문서','Y');
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoDocLibList','Work 첨부문서','N','Work 첨부문서','Y');
    INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoDocLibDetail','Work 첨부문서(상세)','N','Work 첨부문서(상세)','Y');
    INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoResultPmInsDocLibDetail','Work 첨부문서(상세)','N','Work 첨부문서(상세)','Y');
    INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoResultPmRprDocLibDetail','Work PMRPR 첨부문서(상세)','N','Work PMRPR 첨부문서(상세)','Y');
    INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoResultPmRplDocLibDetail','Work PMRPR 첨부문서(상세)','N','Work PMRPL 첨부문서(상세)','Y');
    INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoResultPmClnDocLibDetail','Work PMRPR 첨부문서(상세)','N','Work PMCLN 첨부문서(상세)','Y');
    INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoResultPmOilDocLibDetail','Work PMOIL 첨부문서(상세)','N','Work PMOIL 첨부문서(상세)','Y');
    INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoResultBmRprDocLibDetail','Work BMRPR 첨부문서(상세)','N','Work BMRPR 첨부문서(상세)','Y');
    INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoResultBmRplDocLibDetail','Work PMRPL 첨부문서(상세)','N','Work PMRPL 첨부문서(상세)','Y');
    INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoResultBmOilDocLibDetail','Work BMOIL 첨부문서(상세)','N','Work BMOIL 첨부문서(상세)','Y');
    INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoResultCmRprDocLibDetail','Work CMRPR 첨부문서(상세)','N','Work CMRPR 첨부문서(상세)','Y');
    INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoResultCmRplDocLibDetail','Work CMRPL 첨부문서(상세)','N','Work CMRPL 첨부문서(상세)','Y');
    INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoResultTrEleDocLibDetail','Work TRELE 첨부문서(상세)','N','Work TRELE 첨부문서(상세)','Y');
    INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoResultPmGmDocLibDetail','Work PMGM 첨부문서(상세)','N','Work PMGM 첨부문서(상세)','Y');
    INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoResultPmCalDocLibDetail','Work PMCAL 첨부문서(상세)','N','Work PMCAL 첨부문서(상세)','Y');
 
 
 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'partAdjStkTakeDocLibList','부품실사 첨부문서','N','부품실사 첨부문서','Y');
 
   INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'partAdjStkTakeDocLibDetail','부품실사 첨부문서(상세)','N','부품실사 첨부문서(상세)','Y');
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWrkImpDocLibList','개선 첨부문서','N','개선 첨부문서','Y');
 
   INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWrkImpDocLibDetail','개선 첨부문서(상세)','N','개선 첨부문서(상세)','Y');
 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maPttMstrDocLibList','공구 첨부문서','N','공구 첨부문서','Y');
 
   INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maPttMstrDocLibDetail','공구 첨부문서(상세)','N','공구 첨부문서(상세)','Y');
 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maPtPurReqDocLibList','부품수리 첨부문서','N','부품수리 첨부문서','Y');
 
   INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maPtPurReqDocLibDetail','부품수리 첨부문서(상세)','N','부품수리 첨부문서(상세)','Y');
 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maPtBuyReqHdrDocLibList','구매신청 첨부문서','N','구매신청 첨부문서','Y');
 
   INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maPtBuyReqHdrDocLibDetail','구매신청 첨부문서(상세)','N','구매신청 첨부문서(상세)','Y');
 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maQnaAnsDocLibList','질의변답 첨부문서','N','질의변답 첨부문서','Y');
 
   INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maQnaAnsDocLibDetail','질의변답 첨부문서(상세)','N','질의변답 첨부문서(상세)','Y');
 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maQnaDocLibList','질의 첨부문서','N','질의 첨부문서','Y');
 
   INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maQnaDocLibDetail','질의 첨부문서(상세)','N','질의 첨부문서(상세)','Y');
 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maDocCntrCdDocLibList','일반자료실 첨부문서','N','부품수리 첨부문서','Y');
 
   INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maDocCntrCdDocLibDetail','일반자료실 첨부문서(상세)','N','부품수리 첨부문서(상세)','Y');
 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maPtRepDocLibList','부품수리 첨부문서','N','부품수리 첨부문서','Y');
 
   INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maPtRepDocLibDetail','부품수리 첨부문서(상세)','N','부품수리 첨부문서(상세)','Y');
 
    INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'docFileLov','첨부파일 팝업','N','첨부파일 팝업','Y'); 
 
 
   INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'rcmFfailDocLibList','RCM 기능고장 첨부문서','N','RCM 기능고장 첨부문서','Y');
 
    INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'rcmFfailDocLibDetail','RCM 기능고장(상세)','N','RCM 기능고장 첨부문서(상세)','Y');
 
 
    INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'failCodeLov','FAILURE 팝업','N','FAILURE 팝업','Y');
 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'rcmFmeaList','FMEA(목록)','Y','FMEA(목록)','Y', 'MAIN');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'rcmFmeaDetail','FMEA(상세)','Y','FMEA(상세)','Y', 'MAIN');
 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'rcmFmeaCrityList','FMEA_Crity(목록)','Y','FMEA_Crity(목록)','Y', 'MAIN');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'rcmFmeaCrityDetail','FMEA_Crity(상세)','Y','FMEA_Crity(상세)','Y', 'MAIN');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'rcmFmeaDocLibList','FMEA DOC(목록)','Y','FFMEA DOC(목록)','Y', 'MAIN');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'rcmFmeaDocLibDetail','FMEA DOC(상세)','Y','FMEA DOC(상세)','Y', 'MAIN');

  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'rcmPmtaskList','PMTASK(목록)','Y','PMTASK(목록)','Y', 'MAIN');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'rcmPmtaskDetail','PMTASK(상세)','Y','PMTASK(상세)','Y', 'MAIN');

 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'rcmPmtaskMapList','PMTASK_MAP(목록)','Y','PMTASK_MAP(목록)','Y', 'MAIN');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'rcmPmtaskMapDetail','PMTASK_MAP(상세)','Y','PMTASK_MAP(상세)','Y', 'MAIN');

 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'rcmPmtaskDocLibList','PMTASK_DOC(목록)','Y','PMTASK_DOC(목록)','Y', 'MAIN');

 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'rcmPmtaskDocLibDetail','PMTASK_DOC(목록)','Y','PMTASK_DOC(목록)','Y', 'MAIN');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'rcmPmtaskCndtList','PMTASK_CNDT(목록)','Y','PMTASK_CNDT(목록)','Y', 'MAIN');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'rcmPmtaskCndtDetail','PMTASK_CNDT(상세)','Y','PMTASK_CNDT(상세)','Y', 'MAIN');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'rcmCrityValLov','RCM Critical LOV','N','RCM Critical LOV','Y');
 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'rcmPmtaskCrityList','PMTASK_Crity(목록)','Y','PMTASK_Crity(목록)','Y', 'MAIN');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'rcmPmtaskCrityDetail','PMTASK_Crity(상세)','Y','PMTASK_Crity(상세)','Y', 'MAIN');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'mgrUsrgrpPageList','Authority of Page','N','Authority of Page','Y', 'MAIN');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'mgrUsrgrpBtnList','Authority of Button','N','Authority of BUtton','Y', 'MAIN');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'invtList','Equipment Investment','N','Equipment Investment','Y', 'MAIN');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'invtDetail','Equipment Investment Detail','N','Equipment Investment Detail','Y', 'MAIN');


INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'invtPhaseList','Equipment Investment Phase','Y','Equipment Investment Phase','Y', 'MAIN');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'invtPhaseDetail','Equipment Investment Phase Detail','Y','Equipment Investment Phase Detail','Y', 'MAIN');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'invtDocLibDetail','Equipment Investment Doc Detail','Y','Equipment Investment Doc Detail','Y', 'MAIN');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'invtDocLibList','Equipment Investment Doc','Y','Equipment Investment Doc','Y', 'MAIN');


 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'invtPrcList','Equipment Investment Process','Y','Equipment Investment Process','Y', 'MAIN');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'invtPrcDetail','Equipment Investment Process Detail','Y','Equipment Investment Process Detail','Y', 'MAIN');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'invtPrcDocLibDetail','Investment Prc Doc Detail','Y','Investment Prc Doc Detail','Y', 'MAIN');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'invtPrcDocLibList','Investment Prc Doc','Y','Investment Prc Doc Detail','Y', 'MAIN');


INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoResultPmGnlMstrDetail','검교정_일반(상세)','Y','검교정_일반(상세)','Y', 'MAIN');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workListGnlCaEqList','검교정_일반_표준기(목록)','Y','검교정_일반_표준기(목록)','Y', 'MAIN');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workListGnlCaEqDetail','검교정_일반_표준기(상세)','Y','검교정_일반_표준기(상세)','Y', 'MAIN');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workListGnlCavalList','검교정_일반_측정값(목록)','Y','검교정_일반_측정값(목록)','Y', 'MAIN');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workListGnlCavalDetail','검교정_일반_측정값(상세)','Y','검교정_일반_측정값(상세)','Y', 'MAIN');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoResultGnlCaCraftList','검교정_일반_작업자(목록)','Y','검교정_일반_작업자(목록)','Y', 'MAIN');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoResultGnlCaCraftDetail','검교정_일반_작업자(상세)','Y','검교정_일반_작업자(상세)','Y', 'MAIN');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoResultGnlCaDocLibList','검교정_일반_문서(목록)','Y','검교정_일반_문서(목록)','Y', 'MAIN');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoResultGnlCaDocLibDetail','검교정_일반_문서(상세)','Y','검교정_일반_문서(상세)','Y', 'MAIN');


INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'listOfSysVal','Systom Code LOV for AC','N','Systom Code LOV for AC','Y', 'MAIN');


INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'lovUserAcList','USER LOV for AC','N','USER LOV for AC','Y', 'MAIN');



INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'lovEmpAcList','EMP LOV for AC','N','EMP LOV for AC','Y', 'MAIN');



INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'lovEquipAcList','Equip LOV for AC','N','Equip LOV for AC','Y', 'MAIN');



INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'lovEqCtgAcList','Equip CTG LOV for AC','N','Equip CTG LOV for AC','Y', 'MAIN');

  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'lovVendorAcList','Vendor 팝업','N','Vendor 팝업','Y','LOV');
 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'lovWoAcList','Work Order 팝업','N','Work Order 팝업','Y','LOV');
 
 
/** 2017-09-06 김영주 추가  */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) 
VALUES(NEXT VALUE FOR sqapage_id, 'wrkCalListLov','근무달력 팝업','N','근무달력 팝업','Y','LOV');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) 
VALUES(NEXT VALUE FOR sqapage_id, 'lovCdUsrdAcList','상위코드 팝업','N','상위코드 팝업','Y','LOV');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) 
VALUES(NEXT VALUE FOR sqapage_id, 'rcmPmtaskAcList','System 분석 팝업','N','System 분석 팝업','Y','LOV');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) 
VALUES(NEXT VALUE FOR sqapage_id, 'lovEqToolAcList','기준측정기 팝업','N','기준측정기 팝업','Y','LOV');

 
/** 2017-09-06 노정현 추가  */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'deptValLov','Dept AC LOV','N','Dept AC LOV','Y', 'LOV');

/** 2017-09-07 이근환 추가  */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'lovEqCtgAsmbList','설비자산 - 설비종류 - 설비부위(Lov)','N','설비자산 - 설비종류 - 설비부위(Lov)','Y','LOV'); 

/** 2017-09-08 차한결 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmiList','점검작업 - 점검작업 목록','Y','점검작업 - 점검작업 목록','Y','MAIN'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmiDetail','점검작업 - 점검작업 상세','Y','점검작업 - 점검작업 상세','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmiDocLibList','점검작업 - 점검작업 상세 - 첨부문서 목록','Y','점검작업 - 점검작업 상세 - 첨부문서 목록','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmiDocLibDetail','점검작업 - 점검작업 상세 - 첨부문서 상세','Y','점검작업 - 점검작업 상세 - 첨부문서 상세','Y','SUB');  
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmiPointList','점검작업 - 점검작업 상세 - 점검 목록','Y','점검작업 - 점검작업 상세 - 점검 목록','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmiPointDetail','점검작업 - 점검작업 상세 - 점검 상세','Y','점검작업 - 점검작업 상세 - 점검 상세','Y','SUB');

 /** 2017-09-11 이근환 추가  */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'lovCtCtrList','Cost Center Lov','N','Cost Center Lov','Y','LOV'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'lovMesLineList','Mes Line Lov','N','Mes Line Lov','Y','LOV'); 

/** 2017-09-14 김영주 추가  */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) 
VALUES(NEXT VALUE FOR sqapage_id, 'lovUsrRptTabAcList','테이블명 검색 팝업','N','테이블명 검색 팝업','Y','LOV');
 
/** 2017-09-14 이근환 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListPtrlDetail','예방작업[순회](상세)','Y','예방작업[순회](상세)','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListPtrlRtList','예방작업[순회](상세) - 순회코스(목록)','Y','예방작업[순회](상세) - 순회코스(목록)','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListPtrlRtDetail','예방작업[순회](상세) - 순회코스(상세)','Y','예방작업[순회](상세) - 순회코스(상세)','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListPtrlRtPointList','예방작업[순회](상세) - 순회코스(상세) - 점검내용(목록)','Y','예방작업[순회](상세) - 순회코스(상세) - 점검내용(목록)','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListPtrlRtPointDetail','예방작업[순회](상세) - 순회코스(상세) - 점검내용(상세)','Y','예방작업[순회](상세) - 순회코스(상세) - 점검내용(상세)','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListPtrlShiftList','예방작업[순회](상세) - 교대조(목록)','Y','예방작업[순회](상세) - 교대조(목록)','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListPtrlShiftDetail','예방작업[순회](상세) - 교대조(상세)','Y','예방작업[순회](상세) - 교대조(상세)','Y','SUB'); 

 /** 2017-09-19 김정우 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'workPmStdCalibList','교정표준값(목록)','Y','교정표준값(목록)','Y', 'MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'workPmStdCalibDetail','교정표준값(상세)','Y','교정표준값(상세)','Y', 'MAIN');
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'workPmStdCalibValList','표준값(목록)','Y','표준값(목록)','Y', 'SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'workPmStdCalibValDetail','표준값(상세)','Y','표준값(상세)','Y', 'SUB');

  /** 2017-09-20 차한결 추가 */
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListWorkList','작업관리 - 예방작업 - 예방수리 목록','Y','작업관리 - 예방작업 - 예방수리 목록','Y','MAIN'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListInsList','작업관리 - 예방작업 - 예방점검 목록','Y','작업관리 - 예방작업 - 예방점검 목록','Y','MAIN');
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListCalList','작업관리 - 예방작업 - 예방교정 목록','Y','작업관리 - 예방작업 - 예방교정 목록','Y','MAIN');
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListTrList','작업관리 - 예방작업 - 예방교육 목록','Y','작업관리 - 예방작업 - 예방교육 목록','Y','MAIN');  

 /** 2017-09-21 김영주 추가*/
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workListPointList','점검내용 (목록)','Y','Work List Point 목록','Y', 'SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workListPointDetail','점검내용 (상세)','Y','Work List Point 상세','Y', 'SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workListDocLibList','순회일정 첨부문서 (목록)','Y','순회일정 첨부문서 목록','Y', 'SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workListDocLibDetail','순회일정 첨부문서 (상세)','Y','순회일정 첨부문서 상세','Y', 'SUB');
INSERT INTO TAPAGE (page_id, file_name, description, is_use, is_chkauth, page_type,page_categ)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workListPtrlResultList','순회일정 목록', 'Y','Y','MAIN','');
INSERT INTO TAPAGE (page_id, file_name, description, is_use, is_chkauth, page_type,page_categ)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workListPtrlResultMstrDetail','순회일정 상세', 'Y','Y','MAIN','';
INSERT INTO TAPAGE (page_id, file_name, description, is_use, is_chkauth, page_type,page_categ) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workCalPmPtrlMonthList','순회일정(월간) 목록', 'Y','Y','MAIN','');

/** 2017-09-21 김정우 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workFmeaList','고장영향성 평가(목록)','Y','고장영향성 평가(목록)','Y', 'MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workFmeaDetail','고장영향성 평가(상세)','Y','고장영향성 평가(상세)','Y', 'MAIN');

  /** 2017-09-22 차한결 추가 */
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workListPmiList','작업관리 - 예방점검작업 목록','Y','작업관리 - 예방점검작업 목록','Y','MAIN');
 
 /** 2017-09-22 장효성 추가 */
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maEqMachMstrList','생산설비목록','Y','생산설비목록','Y','MAIN');
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maEqToolMstrList','계측기목록','Y','계측기목록','Y','MAIN');
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maEqBuildingMstrList','토지,건물목록','Y','토지,건물목록','Y','MAIN');
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maEqUtilityMstrList','Utility목록','Y','Utility목록','Y','MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoResultTrMstrList','작업관리-교육목록','Y','작업관리-교육목록','Y', 'MAIN');
 /** 2017-09-25 김정우 추가 */
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workListGnlCavalPopup','교정 측정값 일괄등록 팝업','N','교정 측정값 일괄등록 팝업','Y','MAIN');
 
 /**2017-09-26 노정현 추가*/
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maEqPartMstrList','자산부품 (목록)','Y','자산부품 (목록)','Y', 'MAIN');
 
  /** 2017-09-26 이규선 추가 */
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type,page_categ) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoResultTypeSelect','고장작업목록','N','고장작업목록','Y','LOV','WORK_LIST');

   INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoResultBmMstrList','고장작업목록','Y','고장작업목록','Y','MAIN');
 
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoCmResultMstrList','개선작업목록','Y','개선작업목록','Y','MAIN');

  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoPmwResultMstrList','예방수리작업목록','Y','예방수리작업목록','Y','MAIN');

   INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maWoPmcResultMstrList','교정작업목록','Y','교정작업목록','Y','MAIN');
 
 
/** 2017-09-26 김정우 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'maWoResultPmSclMstrDetail','검교정_저울(상세)','Y','검교정_저울(상세)','Y', 'MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'workListSclCaEqList','검교정_저울_표준기(목록)','Y','검교정_저울_표준기(목록)','Y', 'MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'workListSclCaEqDetail','검교정_저울_표준기(상세)','Y','검교정_저울_표준기(상세)','Y', 'MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'maWoResultSclCaCraftList','검교정_저울_작업자(목록)','Y','검교정_저울_작업자(목록)','Y', 'MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'workListSclCavalList','검교정_저울_측정값','Y','검교정_저울_측정값','Y', 'MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'maWoResultSclCaCraftDetail','검교정_저울_작업자(상세)','Y','검교정_저울_작업자(상세)','Y', 'MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'maWoResultSclCaDocLibList','검교정_저울_문서(목록)','Y','검교정_저울_문서(목록)','Y', 'MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'maWoResultSclCaDocLibDetail','검교정_저울_문서(상세)','Y','검교정_저울_문서(상세)','Y', 'MAIN');


INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'maWoResultPmPrsMstrDetail','검교정_압력(상세)','Y','검교정_압력(상세)','Y', 'MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'workListPrsCaEqList','검교정_압력_표준기(목록)','Y','검교정_압력_표준기(목록)','Y', 'MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'workListPrsCaEqDetail','검교정_압력_표준기(상세)','Y','검교정_압력_표준기(상세)','Y', 'MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'workListPrsCavalList','검교정_압력_측정값(목록)','Y','검교정_압력_측정값(목록)','Y', 'MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'workListPrsCavalDetail','검교정_압력_측정값(상세)','Y','검교정_압력_측정값(상세)','Y', 'MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'maWoResultPrsCaCraftList','검교정_압력_작업자(목록)','Y','검교정_압력_작업자(목록)','Y', 'MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'maWoResultPrsCaCraftDetail','검교정_압력_작업자(상세)','Y','검교정_압력_작업자(상세)','Y', 'MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'maWoResultPrsCaDocLibList','검교정_압력_문서(목록)','Y','검교정_압력_문서(목록)','Y', 'MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'maWoResultPrsCaDocLibDetail','검교정_압력_문서(상세)','Y','검교정_압력_문서(상세)','Y', 'MAIN');

/** 2017-09-27 김영주 추가  */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) 
VALUES(NEXT VALUE FOR sqapage_id, 'lovUsrGrpAcList','권한명 검색 팝업','N','권한명 검색 팝업','Y','LOV');

/** 2017-09-28 김영주 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'workFmeaReqList','고장영향성 평가요청(목록)','Y','고장영향성 평가요청(목록)','Y', 'MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'workFmeaReqDetail','고장영향성 평가요청(상세)','Y','고장영향성 평가요청(상세)','Y', 'MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'workFmeaResList','고장영향성 평가접수(목록)','Y','고장영향성 평가접수(목록)','Y', 'MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'workFmeaResDetail','고장영향성 평가접수(상세)','Y','고장영향성 평가접수(상세)','Y', 'MAIN');


/** 2017-10-10 김정우 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'assetListRegislate','설비 제정','N','설비 제정','Y', 'LOV');

/** 2017-10-10 김정우 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'assetListRevision','설비 개정','N','설비 개정','Y', 'LOV');

/** 2017-10-11 이근환 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'daewoongKpiOeeDailyList','외부Interface - 설비종합효율 - 설비효율(일별)','Y','외부Interface - 설비종합효율 - 설비효율(일별)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'daewoongKpiOeeDailyDetailList','외부Interface - 설비종합효율 - 설비효율(일별) - 상세 리스트','Y','외부Interface - 설비종합효율 - 설비효율(일별) - 상세 리스트','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'daewoongKpiOeeDailyChart','외부Interface - 설비종합효율 - 설비효율(일별) - 차트','Y','외부Interface - 설비종합효율 - 설비효율(일별) - 차트','Y','SUB'); 

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'daewoongKpiOeeMonthlyList','외부Interface - 설비종합효율 - 설비효율(월별)','Y','외부Interface - 설비종합효율 - 설비효율(월별)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'daewoongKpiOeeMonthlyDetailList','외부Interface - 설비종합효율 - 설비효율(월별) - 상세 리스트','Y','외부Interface - 설비종합효율 - 설비효율(월별) - 상세 리스트','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'daewoongKpiOeeMonthlyChart','외부Interface - 설비종합효율 - 설비효율(월별) - 차트','Y','외부Interface - 설비종합효율 - 설비효율(월별) - 차트','Y','SUB'); 


/** 2017-10-13 김정우 추가 */
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'eduEmpDocLibList','이수 교육 첨부파일 (목록)','Y','이수 교육 첨부파일 (목록)','Y', 'MAIN');
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'eduEmpDocLibDetail','이수 교육 첨부파일 (상세)','Y','이수 교육 첨부파일 (상세)','Y', 'SUB');
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'certEmpDocLibList','자격증 첨부파일 (목록)','Y','자격증 첨부파일 (목록)','Y', 'MAIN');
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'certEmpDocLibDetail','자격증 첨부파일 (상세)','Y','자격증 첨부파일 (상세)','Y', 'SUB');
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'orgEmpTrainDocLibList','이수 교육 첨부파일 (목록)','N','이수 교육 첨부파일 (목록)','Y', 'MAIN');
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'orgEmpTrainDocLibDetail','이수 교육 첨부파일 (상세)','N','이수 교육 첨부파일 (상세)','Y', 'SUB');
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'orgEmpCertDocLibList','자격증 첨부파일 (목록)','N','자격증 첨부파일 (목록)','Y', 'MAIN');
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'orgEmpCertDocLibDetail','자격증 첨부파일 (상세)','N','자격증 첨부파일 (상세)','Y', 'SUB');


/** 2017-10-16 김영주 추가 */
INSERT INTO TAPAGE (page_id, file_name, description, is_use, is_chkauth, page_type,page_categ) 
VALUES(NEXT VALUE FOR sqapage_id, 'workPmCheckList','표준점검항목(목록)', 'Y','Y','MAIN','');
INSERT INTO TAPAGE (page_id, file_name, description, is_use, is_chkauth, page_type,page_categ)
VALUES(NEXT VALUE FOR sqapage_id, 'workPmCheckDetail','표준점검항목(상세)', 'Y','Y','MAIN','');

/** 2017-10-17 이근환 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'assetReportLccEquipList','설비자산 - report - 설비LCC분석 - 고장TOP(설비)','Y','설비자산 - report - 설비LCC분석 - 고장TOP(설비)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'assetReportLccEquipDetailList','설비자산 - report - 설비LCC분석 - 고장TOP(설비) - 상세 리스트','Y','설비자산 - report - 설비LCC분석 - 고장TOP(설비) - 상세 리스트','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'assetReportLccEquipChart','설비자산 - report - 설비LCC분석 - 고장TOP(설비) - 차트','Y','설비자산 - report - 설비LCC분석 - 고장TOP(설비) - 차트','Y','SUB'); 

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'assetReportLccLocList','설비자산 - report - 설비LCC분석 - 고장TOP(위치)','Y','설비자산 - report - 설비LCC분석 - 고장TOP(위치)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'assetReportLccLocDetailList','설비자산 - report - 설비LCC분석 - 고장TOP(위치) - 상세 리스트','Y','설비자산 - report - 설비LCC분석 - 고장TOP(위치) - 상세 리스트','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'assetReportLccLocChart','설비자산 - report - 설비LCC분석 - 고장TOP(위치) - 차트','Y','설비자산 - report - 설비LCC분석 - 고장TOP(위치) - 차트','Y','SUB'); 



/** 2017-10-18 이규선 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'maPtRecSerialDetail','구매입고(상세)-시리얼','Y','구매입고(상세)-시리얼','Y', 'MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'maPtRecSerialList','구매입고(상세)-시리얼','Y','구매입고(상세)-시리얼','Y', 'MAIN');

/** 2017-10-18 차한결 추가 */
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListRevCreate','작업관리 - 예방작업 - 예방작업 - Revision 제정','N','작업관리 - 예방작업 - 예방작업 - Revision 제정','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListRevUpdate','작업관리 - 예방작업 - 예방작업 - Revision 개정','N','작업관리 - 예방작업 - 예방작업 - Revision 개정','Y','SUB');
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'commRevHistList','개정 변경이력 목록','N','개정 변경이력 목록','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'commRevHistDetail','개정 변경이력 상세','N','개정 변경이력 상세','Y','SUB');
 
 
 /** 2017-10-18 노정현 추가  */
 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workListBmRplPartSerialList','작업부품Serial (목록)','Y','작업부품Serial (목록)','Y', 'MAIN');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workListBmRplPartSerialDetail','작업부품Serial (상세)','Y','작업부품Serial (상세)','Y', 'MAIN');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workListCmRplPartSerialList','작업부품Serial (목록)','Y','작업부품Serial (목록)','Y', 'MAIN');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workListCmRplPartSerialDetail','작업부품Serial (상세)','Y','작업부품Serial (상세)','Y', 'MAIN');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workListPmRplPartSerialList','작업부품Serial (목록)','Y','작업부품Serial (목록)','Y', 'MAIN');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workListPmRplPartSerialDetail','작업부품Serial (상세)','Y','작업부품Serial (상세)','Y', 'MAIN');


/** 2017-10-19 장효성 추가  */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type,page_categ) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'partIssWoItemList','부품- 부품출고 상세-순환품 관리','Y','부품- 부품출고 상세-순환품 관리','Y','SUB','PART_ISSUE');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type,page_categ) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'partIssWoItemDetail','부품- 부품출고 상세-순환품 관리상세','Y','부품- 부품출고 상세-순환품 관리상세','Y','SUB','PART_ISSUE');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type,page_categ) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'lovSerialList','순환자재 LOV','N','순환자재 LOV','Y','LOV','');

/** 2017-10-19 김정우 */
    INSERT INTO tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT VALUE FOR sqapage_id, 'workCalPmRInsPeriodDetail','예방점검일정(기간) 상세','Y','예방점검일정(기간) 상세','Y');
 
 /** 2017-10-20 김영주 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'lovStdCheckPointList','표준점검항목 팝업','N','표준점검항목 팝업','Y','LOV');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH)
VALUES (NEXT VALUE FOR SQAPAGE_ID, 'workPmHInsSelect', '시간점검결과 생성 팝업', '시간점검결과 생성 팝업', 'Y', 'N');

/** 2017-10-24 김영주 추가 */
INSERT INTO TAPAGE (page_id, file_name, description, is_use, is_chkauth, page_type,page_categ) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListDInsPointList','점검(목록)', 'Y','Y','SUB','');
INSERT INTO TAPAGE (page_id, file_name, description, is_use, is_chkauth, page_type,page_categ)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmListDInsPointDetail','점검(상세)', 'Y','Y','SUB','');
INSERT INTO TAPAGE (page_id, file_name, description, is_use, is_chkauth, page_type,page_categ) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmiDInsList','일상점검목록(목록)', 'Y','Y','MENU','');
INSERT INTO TAPAGE (page_id, file_name, description, is_use, is_chkauth, page_type,page_categ)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmiDInsDetail','일상점검목록(상세)', 'Y','Y','MENU','');
/**2017-10-24 김정우 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmStdCalibValLov','교정표준값 AC LOV','N','교정표준값 AC LOV','Y', 'LOV');

/** 2017-10-25 김영주 추가 */
INSERT INTO TAPAGE (page_id, file_name, description, is_use, is_chkauth, page_type,page_categ) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmiDInsPointList','일상점검목록 - 점검(목록)', 'Y','Y','SUB','');
INSERT INTO TAPAGE (page_id, file_name, description, is_use, is_chkauth, page_type,page_categ)
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workPmiDInsPointDetail','일상점검목록 - 점검(상세)', 'Y','Y','SUB','');

/** 2017-10-26 김영주 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'invtRptInvtPreConList','설비투자 - Report - 투자현황(목록)','Y','설비투자 - Report - 투자현황(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'invtRptInvtPreConDetail','설비투자 - Report - 투자현황(상세)','Y','설비투자 - Report - 투자현황(상세)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'invtItemList','투자항목(목록)','Y','투자항목(목록)','Y','SUB');

/** 2017-10-27 이근환 추가 */
UPDATE TAPAGE SET file_name='assetRptLccEquipList' WHERE file_name='assetReportLccEquipList';
UPDATE TAPAGE SET file_name='assetRptLccEquipDetailList' WHERE file_name='assetReportLccEquipDetailList';
UPDATE TAPAGE SET file_name='assetRptLccEquipChart' WHERE file_name='assetReportLccEquipChart';
UPDATE TAPAGE SET file_name='assetRptLccLocList' WHERE file_name='assetReportLccLocList';
UPDATE TAPAGE SET file_name='assetRptLccLocDetailList' WHERE file_name='assetReportLccLocDetailList';
UPDATE TAPAGE SET file_name='assetRptLccLocChart' WHERE file_name='assetReportLccLocChart';


/** 2017-10-30 이규선 추가 */
 INSERT INTO TAPAGE (page_id, file_name, description, is_use, is_chkauth, page_type,page_categ) 
VALUES(NEXT VALUE FOR sqapage_id, 'maStdWorkList','표준작업 - 작업(목록)', 'Y','Y','SUB','');
INSERT INTO TAPAGE (page_id, file_name, description, is_use, is_chkauth, page_type,page_categ)
VALUES(NEXT VALUE FOR sqapage_id, 'maStdWorkDetail','표준작업 - 작업(상세)', 'Y','Y','SUB','');

  INSERT INTO TAPAGE (page_id, file_name, description, is_use, is_chkauth, page_type,page_categ) 
VALUES(NEXT VALUE FOR sqapage_id, 'maStdPartList','표준작업 - 부품(목록)', 'Y','Y','SUB','');
INSERT INTO TAPAGE (page_id, file_name, description, is_use, is_chkauth, page_type,page_categ)
VALUES(NEXT VALUE FOR sqapage_id, 'maStdPartDetail','표준작업 - 부품(상세)', 'Y','Y','SUB','');

  INSERT INTO TAPAGE (page_id, file_name, description, is_use, is_chkauth, page_type,page_categ) 
VALUES(NEXT VALUE FOR sqapage_id, 'maStdWoTypeList','표준작업 - 작업종류(목록)', 'Y','Y','SUB','');
INSERT INTO TAPAGE (page_id, file_name, description, is_use, is_chkauth, page_type,page_categ)
VALUES(NEXT VALUE FOR sqapage_id, 'maStdWoTypeDetail','표준작업 - 작업종류(상세)', 'Y','Y','SUB','');


INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) 
VALUES(NEXT VALUE FOR sqapage_id, 'lovStdWrkWorkList','표준작업종류 팝업','N','표준작업종류 팝업','Y','LOV');

/** 2017-10-30 김영주 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'overOwnPartPreConList','부품관리 - Report - 부품초과보유현황(목록)','Y','부품관리 - Report - 부품초과보유현황(목록)','Y','MAIN'); 

/** 2017-10-31 이근환 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workRptPminsAchList','작업관리 - report - 예방점검이행율(담당자)','Y','작업관리 - report - 예방점검이행율(담당자)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workRptPminsAchDetailList','작업관리 - report - 예방점검이행율(담당자) - 상세리스트','Y','작업관리 - report - 예방점검이행율(담당자) - 상세리스트','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workRptPminsAchDetailChart','작업관리 - report - 예방점검이행율(담당자) - 상세차트','Y','작업관리 - report - 예방점검이행율(담당자) - 상세차트','Y','SUB'); 

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workRptPmRatioList','작업관리 - report - 계획보전율(위치)','Y','작업관리 - report - 계획보전율(위치)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workRptPmRatioDetailList','작업관리 - report - 계획보전율(위치) - 상세리스트','Y','작업관리 - report - 계획보전율(위치) - 상세리스트','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workRptPmRatioDetailChart','작업관리 - report - 계획보전율(위치) - 상세차트','Y','작업관리 - report - 계획보전율(위치) - 상세차트','Y','SUB'); 

/** 2017-11-01 김영주 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) 
VALUES( NEXT VALUE FOR SQAPAGE_ID, 'stockKpiList','부품관리 - Report - 재고지표(목록)','Y','부품관리 - Report - 재고지표(목록)','Y','MAIN'); 

/** 2017-11-01 이근환 추가 */
UPDATE TAPAGE SET file_name='assetRptLccEquipDetailChart' WHERE file_name='assetRptLccEquipChart';
UPDATE TAPAGE SET file_name='assetRptLccLocDetailChart' WHERE file_name='assetRptLccLocChart';

/** 2017-11-02 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'energyUsePreConList','작업관리 - Report - 에너지사용현황(목록)','Y','작업관리 - Report - 에너지사용현황(목록)','Y','MAIN'); 

/** 2017-11-02 이근환 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'assetRptMtbfmttrEquipList','설비자산 - report - MTBF,MTTR(설비)','Y','설비자산 - report - MTBF,MTTR(설비)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'assetRptMtbfmttrEquipDetailList','설비자산 - report - MTBF,MTTR(설비) - 상세리스트','Y','설비자산 - report - MTBF,MTTR(설비) - 상세리스트','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'assetRptMtbfmttrEquipDetailChart','설비자산 - report - MTBF,MTTR(설비) - 상세차트','Y','설비자산 - report - MTBF,MTTR(설비) - 상세차트','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'assetRptMtbfmttrLocList','설비자산 - report - MTBF,MTTR(위치)','Y','설비자산 - report - MTBF,MTTR(위치)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'assetRptMtbfmttrLocDetailList','설비자산 - report - MTBF,MTTR(위치) - 상세리스트','Y','설비자산 - report - MTBF,MTTR(위치) - 상세리스트','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'assetRptMtbfmttrLocDetailChart','설비자산 - report - MTBF,MTTR(위치) - 상세차트','Y','설비자산 - report - MTBF,MTTR(위치) - 상세차트','Y','SUB'); 

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workCalPmcYearList','작업관리 - 교정작업일정 - 교정작업일정(연간)','Y','작업관리 - 교정작업일정 - 교정작업일정(연간)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workCalPmcYearDetail','작업관리 - 교정작업일정 - 교정작업일정(연간) - 상세','Y','작업관리 - 교정작업일정 - 교정작업일정(연간) - 상세','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workCalPmcMonthList','작업관리 - 교정작업일정 - 교정작업일정(월간)','Y','작업관리 - 교정작업일정 - 교정작업일정(월간)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workCalPmcMonthDetail','작업관리 - 교정작업일정 - 교정작업일정(월간) - 상세','Y','작업관리 - 교정작업일정 - 교정작업일정(월간) - 상세','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workCalPmcPeriodList','작업관리 - 교정작업일정 - 교정작업일정(기간)','Y','작업관리 - 교정작업일정 - 교정작업일정(기간)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workCalPmcPeriodDetail','작업관리 - 교정작업일정 - 교정작업일정(기간) - 상세','Y','작업관리 - 교정작업일정 - 교정작업일정(기간) - 상세','Y','SUB'); 

/** 2017-11-02 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'orgRptEmpMttrList','조직관리 - Report - MTTR(담당자)(목록)','Y','조직관리 - Report - MTTR(담당자)(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'orgRptEmpMttrDetailList','조직관리 - Report - MTTR(담당자)(상세목록)','Y','조직관리 - Report - MTTR(담당자)(상세목록)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'orgRptEmpMttrDetailChart','조직관리 - Report - MTTR(담당자)(상세차트)','Y','조직관리 - Report - MTTR(담당자)(상세차트)','Y','SUB'); 

/** 2017-11-03 김영주 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) 
VALUES( NEXT VALUE FOR SQAPAGE_ID, 'basicUnitAnalysisList','작업관리 - Report - 원단위분석(목록)','Y','작업관리 - Report - 원단위분석(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) 
VALUES( NEXT VALUE FOR SQAPAGE_ID, 'basicUnitAnalysisDetailList','작업관리 - Report - 원단위분석(상세목록)','Y','작업관리 - Report - 원단위분석(상세목록)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) 
VALUES( NEXT VALUE FOR SQAPAGE_ID, 'basicUnitAnalysisDetailChart','작업관리 - Report - 원단위분석(상세차트)','Y','작업관리 - Report - 원단위분석(상세차트)','Y','SUB'); 
INSERT INTO TAPAGE (page_id, file_name, description, is_use, is_chkauth, page_type,page_categ) 
VALUES( NEXT VALUE FOR sqapage_id, 'workPmCheckMonthlyUnitPriceList','월별단가(목록)', 'Y','Y','MAIN','');
INSERT INTO TAPAGE (page_id, file_name, description, is_use, is_chkauth, page_type,page_categ)
VALUES( NEXT VALUE FOR sqapage_id, 'workPmCheckMonthlyUnitPriceDetail','월별단가(상세)', 'Y','Y','MAIN','');
/**2017-11-03 김정우 */
 INSERT INTO tapage(page_id, file_name, description, is_chkauth, remark, is_use,page_type) 
 VALUES(NEXT value FOR sqapage_id, 'maWoResultCmLocBaseMstrDetail','작업결과(개선작업-일반(위치기준)) 상세','Y','작업결과(개선작업-일반(위치기준)) 상세','Y','SUB');
 INSERT INTO tapage(page_id, file_name, description, is_chkauth, remark, is_use,page_type) 
 VALUES(NEXT value FOR sqapage_id, 'maWoResultCmLocBaseCraftList','작업결과(개선작업-일반) 작업자 목록','Y','작업결과(개선작업-일반) 작업자 목록','Y','MAIN');
 INSERT INTO tapage(page_id, file_name, description, is_chkauth, remark, is_use,page_type) 
 VALUES(NEXT value FOR sqapage_id, 'maWoResultCmLocBaseCraftDetail','작업결과(개선작업-일반) 작업자 상세','Y','작업결과(개선작업-일반) 작업자 상세','Y','SUB');
 INSERT INTO tapage(page_id, file_name, description, is_chkauth, remark, is_use,page_type) 
 VALUES(NEXT value FOR sqapage_id, 'maWoResultCmLocBaseDocLibList','작업결과(개선작업-일반) 첨부파일 목록','Y','작업결과(개선작업-일반) 첨부파일 목록','Y','MAIN');
 INSERT INTO tapage(page_id, file_name, description, is_chkauth, remark, is_use,page_type) 
 VALUES(NEXT value FOR sqapage_id, 'maWoResultCmLocBaseDocLibDetail','작업결과(개선작업-일반) 첨부파일 상세','Y','작업결과(개선작업-일반) 첨부파일 상세','Y','SUB');
 
/** 2017-11-06 김정우 */
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT value FOR sqaPAGE_ID, 'workListTiMstrList','설치/테스트 작업목록','Y','설치/테스트 작업목록','Y','MAIN');

 /** 2017-11-06 김영주 추가 */
INSERT INTO TAPAGE (page_id, file_name, description, is_use, is_chkauth, page_type,page_categ) 
VALUES(NEXT value FOR sqapage_id, 'assBasePointValScript','설비등급기준설정 - 평가항목 - 평가기준 - Script', 'Y','Y','LABEL','script');

/** 2017-11-06 차한결 추가 */
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'commRevRegislate','Revision 제정','N','Revision 제정','Y','SUB'); 
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'commRevRevision','Revision 개정','N','Revision 개정','Y','SUB'); 
 
 /** 2017-11-17 이근환 추가 */
  INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH)
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'partAdjStkTakeList', '부품실사 목록', '부품실사 목록', 'Y', 'Y');
 
  INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH)
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'partAdjStkTakeDetail', '부품실사 상세', '부품실사 상세', 'Y', 'Y');
 
  INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH)
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'partAdjStkTakeItemList', '부품실사자재 목록', '부품실사자재 목록', 'Y', 'Y');
 
  INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH)
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'partAdjStkTakeItemDetail', '부품실사자재 상세', '부품실사자재 상세', 'Y', 'Y');

 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type, page_categ) 
VALUES(NEXT VALUE FOR sqapage_id, 'orgEmpTrainList','조직관리 - 사원 - 이수교육(목록)','N','조직관리 - 사원 - 이수교육(목록)','Y', 'SUB','ORGAN_EMP');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type, page_categ) 
VALUES(NEXT VALUE FOR sqapage_id, 'orgEmpTrainDetail','조직관리 - 사원 - 이수교육(상세)','N','조직관리 - 사원 - 이수교육(상세)','Y', 'SUB','ORGAN_EMP');
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type, page_categ) 
VALUES(NEXT VALUE FOR sqapage_id, 'orgEmpCertList','조직관리 - 사원 - 보유자격증(목록)','N','조직관리 - 사원 - 보유자격증(목록)','Y', 'SUB','ORGAN_EMP');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type, page_categ) 
VALUES(NEXT VALUE FOR sqapage_id, 'orgEmpCertDetail','조직관리 - 사원 - 보유자격증(상세)','N','조직관리 - 사원 - 보유자격증(상세)','Y', 'SUB','ORGAN_EMP');
 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type)
 VALUES(NEXT VALUE FOR SQAPAGE_ID, 'reqWorkReswoDetail','작업요청 처리사항(상세)','N','작업요청 처리사항(상세)','Y','SUB');


/** 2017-11-27 김정우 */
  INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT value FOR sqaPAGE_ID, 'maEqMstrInsList','설비 점검목록','Y','설비 점검목록','Y','MAIN');
 
/** 2017-11-27 김정우 추가 */
 INSERT INTO tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 VALUES(NEXT value FOR sqapage_id, 'maUsrGrpAndroidAuthList','권한명 Android 상세 권한','N','권한명 Android 상세 권한','Y'); 
 
/** 2017-11-29 김정우 추가 */
 INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
 VALUES(NEXT value FOR sqaPAGE_ID, 'commEqToolRevRegislate','계측기 Revision 제정','N','계측기 Revision 제정','Y','LOV'); 
 
/** 2017-12-03 이근환 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT value FOR sqaPAGE_ID, 'assetStdProductList','생산품목 리스트','Y','생산품목 리스트','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT value FOR sqaPAGE_ID, 'assetStdProductDetail','생산품목 상세','Y','생산품목 상세','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT value FOR sqaPAGE_ID, 'assetStdCtctrList','Cost Center 리스트','Y','Cost Center 리스트','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT value FOR sqaPAGE_ID, 'assetStdCtctrDetail','Cost Center 상세','Y','Cost Center 상세','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT value FOR sqaPAGE_ID, 'assetStdAssetList','회계자산 리스트','Y','회계자산 리스트','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT value FOR sqaPAGE_ID, 'assetStdAssetDetail','회계자산 상세','Y','회계자산 상세','Y','SUB'); 

/** 2017-12-03 김영주 추가 */
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH)
VALUES (NEXT VALUE FOR SQAPAGE_ID, 'workListCinsSelect', '파트체인지 점검계획 생성 팝업', '파트체인지 점검계획 생성 팝업', 'Y', 'N');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR SQAPAGE_ID, 'workListCinsPlanMstrList','파트체인지점검계획(목록)','Y','파트체인지점검계획(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR SQAPAGE_ID, 'workListCinsResultMstrDetail','예방작업[PartChange](상세)','Y','예방작업[PartChange](상세)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workCalPmCInsMonthList','파트체인지점검일정(월간)','Y','파트체인지점검일정(월간)','Y','MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR SQAPAGE_ID, 'workListCinsPlanMstrDetail','파트체인지점검계획(상세)','Y','파트체인지점검계획(상세)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR SQAPAGE_ID, 'lovProductAcList','생산제품 검색 팝업','N','생산제품 검색 팝업','Y','LOV');

/** 2017-12-06 이근환 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT value FOR SQAPAGE_ID, 'workPmiCInsList','파트체인지점검목록','Y','파트체인지점검목록','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT value FOR SQAPAGE_ID, 'workPmiCInsDetail','파트체인지점검상세','Y','파트체인지점검상세','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT value FOR SQAPAGE_ID, 'workPmiCInsPointList','파트체인지점검목록 - 점검(목록)','Y','파트체인지점검목록 - 점검(목록)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT value FOR SQAPAGE_ID, 'workPmiCInsPointDetail','파트체인지점검목록 - 점검(상세)','Y','파트체인지점검목록 - 점검(상세)','Y','SUB'); 

/** 2017-12-07 김영주 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR SQAPAGE_ID, 'lovPmEquipAcList','생산제품 검색 팝업','N','생산제품 검색 팝업(TAPMEQUIP)','Y','LOV');

/** 2017-12-08 이근환 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT value FOR SQAPAGE_ID, 'maBmLnChartDetail','위치고장분석 상세','Y','위치고장분석 상세','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT value FOR SQAPAGE_ID, 'maBmCtgChartDetail','작업관리 - Report - 종류별고장분석 - 차트','Y','작업관리 - Report - 종류별고장분석 - 차트','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT value FOR SQAPAGE_ID, 'maBmGwChartDetail','작업관리 - Report - 부서별고장분석 - 차트','Y','작업관리 - Report - 부서별고장분석 - 차트','Y','SUB'); 

/** 2017-12-11 김영주 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR SQAPAGE_ID, 'consultCompTerminalExList','작업요청 접근터미널 목록','Y','컨설트 - 작업요청 접근터미널 목록','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR SQAPAGE_ID, 'consultCompTerminalExDetail','작업요청 접근터미널 상세','Y','컨설트 - 작업요청 접근터미널 목록 - 접근터미널 목록','Y','SUB');

/** 2017-12-12 이근환 추가 */
UPDATE TAPAGE SET file_name='energyUsePreConMonthList'
WHERE file_name='energyUsePreConList';

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT value FOR SQAPAGE_ID, 'energyUsePreConMonthDetailList','작업관리 - Report - 에너지사용현황(상세) - 리스트','Y','작업관리 - Report - 에너지사용현황(상세) - 리스트','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT value FOR SQAPAGE_ID, 'energyUsePreConMonthDetailChart','작업관리 - Report - 에너지사용현황(상세) - 차트','Y','작업관리 - Report - 에너지사용현황(상세) - 차트','Y','SUB'); 

/** 2017-12-13 이근환 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT value FOR SQAPAGE_ID, 'energyUsePreConYearList','작업관리 - Report - 에너지사용현황(년)(목록)','Y','작업관리 - Report - 에너지사용현황(년)(목록)','Y','MAIN'); 

/** 2017-12-14 김영주 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) 
VALUES( NEXT VALUE FOR sqapage_id, 'lovLnWrkAcList','가동달력 팝업','N','가동달력 팝업','Y','LOV'); 

/** 2017-12-14 이근환 추가 */
INSERT INTO TAPAGE
SELECT NEXT VALUE FOR sqapage_id,'eqAsmb2ValLov',description,REMARK,is_use,is_chkauth,page_type,page_categ 
FROM TAPAGE WHERE file_name='eqAsmbValLov';

/** 2017-12-15 이근환 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maEqMstrPmwRplList','설비 정기교체','Y','설비 정기교체','Y','MAIN'); 

update tapage set file_name='eqAsmbByPmValLov' where file_name='eqAsmb2ValLov';

UPDATE TAPAGE SET is_use='Y', is_chkauth='N' WHERE page_id=(SELECT page_id FROM TAPAGE WHERE file_name='maLineTimeDowList');

/** 2017-12-20 김영주 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use,page_type) 
VALUES( NEXT VALUE FOR sqapage_id, 'maPmTrendList','예방점검수치추이(목록)','Y','예방점검 수치추이 목록','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use,page_type) 
VALUES( NEXT VALUE FOR sqapage_id, 'maPmTrendDetailChart','예방점검수치추이(상세차트)','Y','예방점검 수치추이 상세차트','Y','SUB'); 

/** 2017-12-20 이근환 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maPgGrdColMngList','컨설팅 - 프로그램설정 - 화면GridCol설정(목록)','Y','컨설팅 - 프로그램설정 - 화면GridCol설정(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maPgGrdColMngDetail','컨설팅 - 프로그램설정 - 화면GridCol설정(상세)','Y','컨설팅 - 프로그램설정 - 화면GridCol설정(상세)','Y','SUB'); 

/** 2017-12-21 김영주 추가 */
INSERT INTO TAPAGE 
SELECT NEXT VALUE FOR sqapage_id, 'lovConsultMenuAcList', description, REMARK, is_use, is_chkauth, page_type,page_categ
FROM TAPAGE WHERE file_name = 'menuValLov';

/** 2017-12-22 김영주 추가*/
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) 
VALUES( NEXT VALUE FOR SQAPAGE_ID, 'maPgFieldMngList','컨설팅 - 프로그램설정 - 화면입력항목(목록)','Y','컨설팅 - 프로그램설정 - 화면입력항목(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) 
VALUES( NEXT VALUE FOR SQAPAGE_ID, 'maPgFieldMngDetail','컨설팅 - 프로그램설정 - 화면입력항목(상세)','Y','컨설팅 - 프로그램설정 - 화면입력항목(상세)','Y','SUB'); 
 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type)
VALUES( NEXT VALUE FOR sqapage_id, 'woPlanList','작업계획목록(목록)','Y','작업관리 - 작업계획목록(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) 
VALUES( NEXT VALUE FOR sqapage_id, 'woPlanDetail','작업계획목록(상세)','Y','작업관리 - 작업계획목록(상세)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
VALUES( NEXT VALUE FOR sqapage_id, 'woPlanCraftDetail','작업계획목록 작업자(상세)','Y','작업계획목록 작업자(상세)','Y'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
VALUES( NEXT VALUE FOR sqapage_id, 'woPlanCraftList','작업계획목록 작업자(목록)','Y','작업계획목록 작업자(목록)','Y'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
VALUES( NEXT VALUE FOR sqapage_id, 'woPlanPartList','작업계획목록 투입부품(목록)','Y','작업계획목록 투입부품(목록)','Y'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
VALUES( NEXT VALUE FOR sqapage_id, 'woPlanPartDetail','작업계획목록 투입부품(상세)','Y','작업계획목록 투입부품(상세)','Y'); 


/** 2017-12-27 김정우 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type)
VALUES(NEXT value FOR sqapage_id, 'workCalPmInsApprList','예방점검계획승인 (목록)','Y','예방점검계획승인 (목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) 
VALUES(NEXT value FOR sqapage_id, 'workCalPmInsApprDetail','예방점검계획승인 (상세)','Y','예방점검계획승인 (상세)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type)
VALUES(NEXT value FOR sqapage_id, 'workCalPmInsApprWorkList','예방점검계획승인 - 점검작업(목록)','Y','예방점검계획승인 - 점검작업 (목록)','Y','MAIN'); 

/** 2017-12-27 이근환 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT value FOR SQAPAGE_ID, 'consultProgramOnlinehelpList','컨설팅 - 프로그램설정 - 도움말(목록)','Y','컨설팅 - 프로그램설정 - 도움말(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT value FOR SQAPAGE_ID, 'consultProgramOnlinehelpDetail','컨설팅 - 프로그램설정 - 도움말(상세)','Y','컨설팅 - 프로그램설정 - 도움말(상세)','Y','SUB'); 

/** 2017-12-28 이근환 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
VALUES(NEXT value FOR SQAPAGE_ID, 'consultProgramOnlinehelpDocLibList','도움말 첨부문서','N','도움말 첨부문서','Y'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) 
VALUES(NEXT value FOR SQAPAGE_ID, 'consultProgramOnlinehelpDocLibDetail','도움말 첨부문서(상세)','N','도움말 첨부문서(상세)','Y'); 

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT value FOR SQAPAGE_ID, 'appOnlinehelpDetail','도움말 popup','N','도움말 popup','Y','SUB'); 

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
VALUES(NEXT value FOR SQAPAGE_ID, 'appOnlinehelpDocLibList','도움말 popup 첨부문서','N','도움말 popup 첨부문서','Y'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use) 
VALUES(NEXT value FOR SQAPAGE_ID, 'appOnlinehelpDocLibDetail','도움말 popup 첨부문서(상세)','N','도움말 popup 첨부문서(상세)','Y'); 

/** 2018-01-04 이근환 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT value FOR SQAPAGE_ID, 'consultCompTracelogList','컨설팅 - 회사설정 - Screen Trace(목록)','N','컨설팅 - 회사설정 - Screen Trace(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT value FOR SQAPAGE_ID, 'consultCompTracelogDetail','컨설팅 - 회사설정 - Screen Trace(상세)','N','컨설팅 - 회사설정 - Screen Trace(상세)','Y','SUB'); 

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT value FOR SQAPAGE_ID, 'appTracelogList','TraceLog Popup(목록)','N','TraceLog Popup(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT value FOR SQAPAGE_ID, 'appTracelogDetail','TraceLog Popup(상세)','N','TraceLog Popup(상세)','Y','SUB'); 

/** 2018-01-04 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'consultProgramUploadDataList','컨설팅 - 프로그램설정 - 업로드데이타설정(목록)','Y','컨설팅 - 프로그램설정 - 업로드데이타설정(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'consultProgramUploadDataDetail','컨설팅 - 프로그램설정 - 업로드데이타설정(상세)','Y','컨설팅 - 프로그램설정 - 업로드데이타설정(상세)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'consultProgramUploadDataColList','컬럼(목록)','Y','컨설팅 - 프로그램설정 - 업로드데이타설정 - 컬럼(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'consultProgramUploadDataColDetail','컬럼(상세)','Y','컨설팅 - 프로그램설정 - 업로드데이타설정 - 컬럼(상세)','Y','SUB'); 

/** 2018-01-05 이근환 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT value FOR SQAPAGE_ID, 'mgrExldataList','Excel 데이터 업로드(목록)','N','Excel 데이터 업로드(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT value FOR SQAPAGE_ID, 'mgrExldataDetail','Excel 데이터 업로드(상세)','N','Excel 데이터 업로드(상세)','Y','SUB'); 

/** 2018-01-08 이근환 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT value FOR SQAPAGE_ID, 'lovExcelTabAcList','Excel Tab Popup','N','Excel Tab Popup','Y','LOV'); 

/** 2018-01-10 김영주 */
INSERT INTO TAPAGE (page_id,file_name,description,REMARK,is_use,is_chkauth)
VALUES (NEXT VALUE FOR sqapage_id, 'workPmDInsSelect', '일상점검생성팝업','일상점검생성팝업','Y','N');

/** 2018-01-12 이근환 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'mgrExldataUploadDetail','Excel 데이터 업로드 Popup','N','Excel 데이터 업로드 Popup','Y','SUB'); 

/** 2018-01-29 이근환 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'partIssEmgReqList','부품출고요청(목록)','Y','부품출고요청(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'partIssEmgReqDetail','부품출고요청(상세)','Y','부품출고요청(상세)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'partIssEmgReqPartsList','부품출고요청(상세) - 부품(목록)','Y','부품출고요청(상세) - 부품(목록)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'partIssEmgReqPartsDetail','부품출고요청(상세) - 부품(상세)','Y','부품출고요청(상세) - 부품(상세)','Y','SUB'); 

/** 2018-01-31 김영주 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workRptMaintRateList','정비지표 (목록)','Y','작업관리 - Report - 정비지표 (목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workRptMaintRateDetailByPartList','파트별 정비율 (상세목록)','Y','작업관리 - Report - 파트별 정비율 (상세목록)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workRptMaintRateDetailByTypeList','유형별 정비율 (상세목록)','Y','작업관리 - Report - 유형별 정비율 (상세목록)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workRptMaintRateDetailDayChart','일별정비그래프 (상세차트)','Y','작업관리 - Report - 일별정비그래프 (상세차트)','Y','SUB'); 

/** 2018-02-01 이근환 */
UPDATE TAPAGE SET is_chkauth='Y' WHERE file_name = 'mgrExldataList';
UPDATE TAPAGE SET is_chkauth='Y' WHERE file_name = 'mgrExldataDetail';

/** 2018-02-02 김영주 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'reqRptWoReqRateList','요청접수율(처리자) (목록)','Y','요청관리 - Report - 요청접수율(처리자) (목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'reqRptWoReqRateDetailList','요청접수율(처리자) (상세)','Y','요청관리 - Report - 요청접수율(처리자) (상세)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'reqRptWoReqRateDetailChart','요청접수율(처리자) (상세차트)','Y','요청관리 - Report - 요청접수율(처리자) (상세차트)','Y','SUB'); 

/** 2018-02-05 김영주 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use,  page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workPmiPointDocLibList','점검항목 첨부파일 (목록)','N','점검항목 첨부파일 (목록)','Y', 'MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use,  page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workPmiPointDocLibDetail','점검항목 첨부파일 (상세)','N','점검항목 첨부파일 (상세)','Y', 'SUB');

/* 2018-02-07 김영주 추가 */ 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'workPmiCInsPointDocLibList','파트점검항목 첨부파일 (목록)','N','파트점검항목 첨부파일 (목록)','Y', 'MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use,  page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'workPmiCInsPointDocLibDetail','파트점검항목 첨부파일 (상세)','N','파트점검항목 첨부파일 (상세)','Y', 'SUB');

/** 2018-02-09 김영주 추가 */ 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use,  page_type)
VALUES( NEXT VALUE FOR sqapage_id, 'workPmiPInsPointDocLibList','순회점검항목 첨부파일 (목록)','N','순회점검항목 첨부파일 (목록)','Y', 'MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use,  page_type)
VALUES( NEXT VALUE FOR sqapage_id, 'workPmiPInsPointDocLibDetail','순회점검항목 첨부파일 (상세)','N','순회점검항목 첨부파일 (상세)','Y', 'SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use,  page_type)
VALUES( NEXT VALUE FOR sqapage_id, 'workPmiDInsPointDocLibList','일상점검항목 첨부파일 (목록)','N','일상점검항목 첨부파일 (목록)','Y', 'MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use,  page_type)
VALUES( NEXT VALUE FOR sqapage_id, 'workPmiDInsPointDocLibDetail','일상점검항목 첨부파일 (상세)','N','일상점검항목 첨부파일 (상세)','Y', 'SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use,  page_type)
VALUES( NEXT VALUE FOR sqapage_id, 'workPmiRInsPointDocLibList','정기점검항목 첨부파일 (목록)','N','정기점검항목 첨부파일 (목록)','Y', 'MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use,  page_type)
VALUES( NEXT VALUE FOR sqapage_id, 'workPmiRInsPointDocLibDetail','정기점검항목 첨부파일 (상세)','N','정기점검항목 첨부파일 (상세)','Y', 'SUB');

/* 2018-02-18 김영주 추가 */ 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use) VALUES(NEXT VALUE FOR sqapage_id, 'workPmListSelectPm','예방작업형태','N','예방작업형태 팝업','Y');

/* 2018-02-20 양소영 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT value for SQAPAGE_ID, 'persApprHistList','개인설정-결재이력','Y','개인설정-결재이력(목록)','Y','MAIN'); 

/* 2018-02-21 김영주 추가 */ 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'assetRptEqPartPreConList','설비부품현황 (목록)','Y','지표관리 - 설비지표 - 설비부품현황 (목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'assetRptEqPartPreConDetailPartList','설비부품현황 (상세목록)','Y','지표관리 - 설비지표 - 설비부품현황 (상세목록)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'assetRptEqPartPreConDetailStockList','설비부품현황 (상세상세목록)','Y','지표관리 - 설비지표 - 설비부품현황 (상세상세목록)','Y','SUB'); 

/* 2018-02-26 이근환 추가 */ 
update tapage set is_chkauth='Y' where file_name like 'maPtDocLibList';
update tapage set is_chkauth='Y' where file_name like 'maPtDocLibDetail';

/*2018-02-27 김영주*/
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES( NEXT VALUE FOR SQAPAGE_ID, 'assetListITList','설비자산 - IT장비목록','Y','설비자산 - IT장비목록','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES( NEXT VALUE FOR SQAPAGE_ID, 'assetListITDetail','설비자산 - IT장비목록(상세)','Y','설비자산 - IT장비목록(상세)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES( NEXT VALUE FOR SQAPAGE_ID, 'assetListITSWList','설비자산 - IT장비 - 설치 SW','Y','설비자산 - IT장비 - 설치 SW','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES( NEXT VALUE FOR SQAPAGE_ID, 'assetListITSWDetail','설비자산 - IT장비 - 설치 SW(상세)','Y','설비자산 - IT장비 - 설치 SW(상세)','Y','SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use,  page_type)
VALUES( NEXT VALUE FOR sqapage_id, 'assetListItDocLibList','IT장비목록 첨부파일 (목록)','N','IT장비목록 첨부파일 (목록)','Y', 'MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use,  page_type)
VALUES( NEXT VALUE FOR sqapage_id, 'assetListItDocLibDetail','IT장비목록 첨부파일 (상세)','N','IT장비목록 첨부파일 (상세)','Y', 'SUB');

/**  2018-03-07 매일유업 반영 */

/** 2018-03-09 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'docNoticeList','공지사항(목록)','Y','공유자료 - 공지사항(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'docNoticeDetail','공지사항(상세)','Y','공유자료 - 공지사항(상세)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'docNoticeDeptList','공지부서(목록)','Y','공유자료 - 공지사항 - 공지부서(목록)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'docNoticeDeptDetail','공지부서(상세)','Y','공유자료 - 공지사항 - 공지부서(상세)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'docNoticeTargetList','공지대상(목록)','Y','공유자료 - 공지사항 - 공지대상(목록)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'docNoticeTargetDetail','공지대상(상세)','Y','공유자료 - 공지사항 - 공지대상(상세)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'docNoticeDocLibList','공지사항 첨부문서(목록)','Y','공유자료 - 공지사항 - 첨부문서(목록)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'docNoticeDocLibDetail','공지사항 첨부문서(상세)','Y','공유자료 - 공지사항 - 첨부문서(상세)','Y','SUB'); 

/**  2018-03-13 매일유업 반영 */

/** 2018-03-15 김영주 */
UPDATE TAPAGE SET 
     description = REPLACE(description,'사항','등록') 
   , REMARK = REPLACE(REMARK, '사항','등록')
WHERE description LIKE '공지%';
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'docNoticeCheckList','공지사항(목록)','Y','공유자료 - 공지사항(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'docNoticeCheckDetail','공지사항(상세)','Y','공유자료 - 공지사항(상세)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'docNoticeCheckDocLibList','공지사항 첨부문서(목록)','Y','공유자료 - 공지사항 - 첨부문서(목록)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'docNoticeCheckDocLibDetail','공지사항 첨부문서(상세)','Y','공유자료 - 공지사항 - 첨부문서(상세)','Y','SUB');

/** 2018-03-19 양소영 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'mgrPtWhList','시스템관리 - 부품창고(목록)','Y','시스템관리 - 부품창고(목록)','Y','MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'mgrPtWhDetail','시스템관리 - 부품창고(상세)','Y','시스템관리 - 부품창고(상세)','Y','SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'mgrPtWhEmpList','담당자(목록)','Y','시스템관리 - 부품창고 - 담당자(목록)','Y','SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'mgrPtWhEmpDetail','담당자(상세)','Y','시스템관리 - 부품창고 - 담당자(상세)','Y','SUB');


/** 2018-03-20 김정우 */
INSERT INTO TAPAGE(page_id,file_name,description,remark,is_use,is_chkauth,page_type,page_categ) 
VALUES( next value for sqapage_id, 'dashboardParent','대시보드 부모창','','Y','Y','MAIN','DASHBOARD');
INSERT INTO TAPAGE(page_id,file_name,description,remark,is_use,is_chkauth,page_type,page_categ) 
VALUES( next value for sqapage_id, 'dashboardChildWoPie','대시보드 작업현황(pie)','','Y','N','MAIN','DASHBOARD');
INSERT INTO TAPAGE(page_id,file_name,description,remark,is_use,is_chkauth,page_type,page_categ) 
VALUES( next value for sqapage_id, 'dashboardChildWoBar','대시보드 작업현황(bar)','','Y','N','MAIN','DASHBOARD');
INSERT INTO TAPAGE(page_id,file_name,description,remark,is_use,is_chkauth,page_type,page_categ) 
VALUES( next value for sqapage_id, 'dashboardChildWoPlant','대시보드 작업현황(plant)','','Y','N','MAIN','DASHBOARD');
INSERT INTO TAPAGE(page_id,file_name,description,remark,is_use,is_chkauth,page_type,page_categ) 
VALUES( next value for sqapage_id, 'dashboardChildStockBar','대시보드 재고현황(plant)','','Y','N','MAIN','DASHBOARD');

/** 2018-03-20 이근환 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(next value for SQAPAGE_ID, 'maWoDailyPmiList','일일작업확인(상세) - 예방점검 리스트','Y','일일작업확인(상세) - 예방점검 리스트','Y','MAIN'); 

/**  2018-03-20 매일유업 반영 */

/** 2018-03-21 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) 
VALUES( NEXT VALUE FOR SQAPAGE_ID, 'workRptPminsDeptAchList','작업관리 - report - 예방점검이행율(부서)','Y','작업관리 - report - 예방점검이행율(부서)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) 
VALUES( NEXT VALUE FOR SQAPAGE_ID, 'workRptPminsDeptAchDetailList','작업관리 - report - 예방점검이행율(부서) - 상세리스트','Y','작업관리 - report - 예방점검이행율(부서) - 상세리스트','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) 
VALUES( NEXT VALUE FOR SQAPAGE_ID, 'workRptPminsDeptAchDetailChart','작업관리 - report - 예방점검이행율(부서) - 상세차트','Y','작업관리 - report - 예방점검이행율(부서) - 상세차트','Y','SUB');

/** 2018-03-23 이근환 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'docManualList','사용자매뉴얼','Y','사용자매뉴얼','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'docManualDetail','사용자매뉴얼(상세)','Y','사용자매뉴얼(상세)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'docManualDocLibList','사용자매뉴얼(상세) - 첨부문서','Y','사용자매뉴얼(상세) - 첨부문서','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'docManualDocLibDetail','사용자매뉴얼(상세) - 첨부문서(상세)','Y','사용자매뉴얼(상세) - 첨부문서(상세)','Y','SUB'); 

/**  2018-03-28 매일유업 반영 */

/** 2018-03-30 양소영 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type, page_categ)
VALUES( NEXT VALUE FOR sqapage_id, 'lovCompPopup','Web - 회사선택(Lov)','N','Web - 회사선택(Lov)','Y','LOV', 'WEBLOGIN');

/** 2018-03-30 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) 
VALUES(NEXT VALUE FOR sqapage_id, 'assetRptLccEquipWorkTimeDetailChart','설비자산 - report - 설비LCC분석 - 고장TOP(설비) - 작업시간차트','N','설비자산 - report - 설비LCC분석 - 고장TOP(설비) - 작업시간차트','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'partRptOrgPtUseHistList','조직별사용분석(목록)','Y','지표관리 - 부품지표 - 조직별사용분석(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'partRptOrgPtUseHistDetailList','조직별사용분석(상세목록)','Y','지표관리 - 부품지표 - 조직별사용분석(상세목록)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'partRptOrgPtUseHistDetailChart','조직별사용분석(상세차트)','Y','지표관리 - 부품지표 - 조직별사용분석(상세차트)','Y','SUB'); 

/** 2018-04-03 이지수 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR SQAPAGE_ID, 'assetRptWorkHistList','설비자산 - Report - 설비이력(과거)(목록)','Y','설비자산 - Report - 설비이력(과거)(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR SQAPAGE_ID, 'assetRptWorkHistDetail','설비자산 - Report - 설비이력(과거)(상세)','Y','설비자산 - Report - 설비이력(과거)(상세)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR SQAPAGE_ID, 'assetRptWorkHistLibList','설비자산 - Report - 설비이력(과거)(상세) - 첨부파일 목록','N','설비자산 - Report - 설비이력(과거)(상세) - 첨부파일 목록','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR SQAPAGE_ID, 'assetRptWorkHistLibDetail','설비자산 - Report - 설비이력(과거)(상세) - 첨부파일 상세','N','설비자산 - Report - 설비이력(과거)(상세) - 첨부파일 상세','Y','SUB'); 

/**  2018-04-04 매일유업 반영 */

/** 2018-04-10 양소영 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'assetRptLccLocWorkTimeDetailChart','설비자산 - report - 설비LCC분석 - 고장TOP(위치) - 작업시간차트','Y','설비자산 - report - 설비LCC분석 - 고장TOP(위치) - 작업시간차트','Y','SUB'); 

/** 2018-04-10 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR SQAPAGE_ID, 'maEqMstrPmiList','설비 점검목록 (INS)','Y','설비 점검목록 (INS)','Y','MAIN');

/**  2018-04-11 매일유업 반영 */

/** 2018-04-11 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'partRptOrgPtUseHistList','조직별사용분석(목록)','Y','지표관리 - 부품지표 - 조직별사용분석(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'partRptOrgPtUseHistDetailList','조직별사용분석(상세목록)','Y','지표관리 - 부품지표 - 조직별사용분석(상세목록)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'partRptOrgPtUseHistDetailChart','조직별사용분석(상세차트)','Y','지표관리 - 부품지표 - 조직별사용분석(상세차트)','Y','SUB'); 

/** 2018-04-12 김영주 */
update tapage set is_chkauth = 'N' where file_name in ('assetRptLccLocWorkTimeDetailChart','assetRptLccEquipWorkTimeDetailChart');

/** 2018-04-12 양소영 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workRptPminsOrgAchList','작업관리 - report - 예방점검이행율(조직)','Y','작업관리 - report - 예방점검이행율(조직)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workRptPminsOrgAchDetailList','작업관리 - report - 예방점검이행율(조직) - 상세리스트','Y','작업관리 - report - 예방점검이행율(조직) - 상세리스트','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workRptPminsOrgAchDetailChart','작업관리 - report - 예방점검이행율(조직) - 상세차트','Y','작업관리 - report - 예방점검이행율(조직) - 상세차트','Y','SUB'); 

/** 2018-04-12 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workRptOrgBdList','조직별고장분석(목록)','Y','지표관리 - 작업지표 - 조직별고장분석(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workRptOrgBdDetailList','조직별고장분석(상세목록)','N','지표관리 - 작업지표 - 조직별고장분석(상세목록)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workRptOrgBdDetailChart','조직별고장분석(상세차트)','N','지표관리 - 작업지표 - 조직별고장분석(상세차트)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workRptOrgBdWorkTimeDetailChart','조직별고장분석(작업시간차트)','N','지표관리 - 작업지표 - 조직별고장분석(작업시간차트)','Y','SUB'); 

/** 2018-04-16 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workRptOrgMtbfmttrList','조직별MTTR,MTBF(목록)','Y','지표관리 - 작업지표 - 조직별MTTR,MTBF(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workRptOrgMtbfmttrDetailList','조직별MTTR,MTBF(상세목록)','N','지표관리 - 작업지표 - 조직별MTTR,MTBF(상세목록)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workRptOrgMtbfmttrDetailChart','조직별MTTR,MTBF(상세차트)','N','지표관리 - 작업지표 - 조직별MTTR,MTBF(상세차트)','Y','SUB'); 

/** 2018-04-17 양소영 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'partRptPtUseHistList','지표관리 - 부품지표 - 부품사용분석(목록)','Y','지표관리 - 부품지표 - 부품사용분석(목록)','Y','MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'partRptPtUseHistDetailList','지표관리 - 부품지표 - 부품사용분석(목록) - 부품사용분석(상세목록)','Y','지표관리 - 부품지표 - 부품사용분석(목록) - 부품사용분석(상세목록)','Y','SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'partRptPtMstrEqPartList','지표관리 - 부품지표 - 부품사용분석(목록) - 사용설비(상세목록)','Y','지표관리 - 부품지표 - 부품사용분석(목록) - 사용설비(상세목록)','Y','SUB');

/**  2018-04-18 매일유업 반영 */
/**  2018-04-25 매일유업 반영 */

/** 2018-04-30 이근환 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maHelpDocLibList','Helpdesk(상세) - 첨부문서','Y','Helpdesk(상세) - 첨부문서','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'maHelpDocLibDetail','Helpdesk(상세) - 첨부문서(상세)','Y','Helpdesk(상세) - 첨부문서(상세)','Y','SUB'); 

/**  2018-05-02 매일유업 반영 */
/**  2018-05-09 매일유업 반영 */

/** 2018-05-09 이근환 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'assetListAssList','설비자산 - 설비 - 등급평가(목록)','Y','설비자산 - 설비 - 등급평가(목록)','Y','SUB'); 

/** 2018-05-14 이근환 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'assetListAssDetail','설비자산 - 설비 - 등급평가(상세)','Y','설비자산 - 설비 - 등급평가(상세)','Y','SUB'); 

/** 2018-05-24 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workPlanApprList','작업계획승인 (목록)','Y','작업계획승인 (목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workPlanApprDetail','작업계획승인 (상세)','Y','작업계획승인 (상세)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workPlanApprWorkList','작업계획승인 - 계획작업(목록)','Y','작업계획승인 - 계획작업(목록)','Y','MAIN'); 

/**  2018-05-25 매일유업 반영 */

/** 2018-05-29 김정우 */
insert into tapage(page_id, file_name, description, is_chkauth, remark, is_use) 
 values(next value for sqapage_id, 'maWoResultBmBaseMstrDetail','작업결과(기본) 상세','Y','작업결과(기본) 상세','Y');
 
 /** 2018-05-29 양소영 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'connRptMonthLogList','시스템관리 - Report - 월별접속현황','Y','시스템관리 - Report - 월별접속현황','Y','MAIN'); 

/** 2018-05-29 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'assetRptMonthNewList','신규설비등록현황 (목록)','Y','신규설비등록현황 (목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workRptPmMonthNewList','신규점검등록현황 (목록)','Y','신규점검등록현황 (목록)','Y','MAIN'); 

/** 2018-05-30 양소영 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workRptPmMonthRateList','작업관리 - Report - 월별점검실행율','Y','작업관리 - Report - 월별점검실행율','Y','MAIN');

/**  2018-06-01 매일유업 반영 */

/** 2018-06-07 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'consultPgmErrorList','Error List','Y','Consult – 프로그램설정 – Error List','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'consultPgmErrorDetail','Error List (상세)','Y','Consult – 프로그램설정 – Error List (상세)','Y','SUB'); 

/** 2018-06-08 이지수 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'assetListGnMstrList','일반자산(목록)','Y','일반자산(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'assetListGnMstrDetail','일반자산(상세)','Y','일반자산(상세)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'assetListGnDocLibList','일반자산(상세) - 첨부문서','Y','일반자산(상세) - 첨부문서','Y','SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'assetListGnDocLibDetail','일반자산(상세) - 첨부문서(상세)','Y','일반자산(상세) - 첨부문서(상세)','Y','SUB');

/** 2018-06-08 양소영 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type, page_categ) VALUES( NEXT VALUE FOR sqapage_id, 'consultCompConfigList','컨설팅 - 프로그램설정 - 환경변수(목록)','Y','컨설팅 - 프로그램설정 - 환경변수(목록)','Y','MAIN','CONSULT_PROGRAM'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type, page_categ) VALUES( NEXT VALUE FOR sqapage_id, 'consultCompConfigDetail','컨설팅 - 프로그램설정 - 환경변수(상세)','Y','컨설팅 - 프로그램설정 - 환경변수(상세)','Y','SUB','CONSULT_PROGRAM'); 

/**  2018-06-19 매일유업 반영 */
/**  2018-06-20 매일유업 반영 */
/** 2018-06-22 김정우 */
INSERT INTO TAPAGE(page_id, file_name, description, remark, is_use, is_chkauth, page_type,page_categ) VALUES 
(NEXT VALUE FOR sqapage_id, 'consultPgmDashboardList','대시보드 컨텐츠 목록','대시보드 컨텐츠 목록','Y','Y','MAIN','DBCONTENTS'); 
INSERT INTO TAPAGE(page_id, file_name, description, remark, is_use, is_chkauth, page_type,page_categ) VALUES 
(NEXT VALUE FOR sqapage_id, 'consultPgmDashboardDetail','대시보드 컨텐츠 상세','대시보드 컨텐츠 상세','Y','Y','SUB','DBCONTENTS'); 

/**  2018-06-22 매일유업 반영 */

/**  2018-06-22 양소영 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'maEqCtgSpecList','설비자산 - 설비종류 - 제원(목록)','Y','설비자산 - 설비종류 - 제원(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'maEqCtgSpecDetail','설비자산 - 설비종류 - 제원(상세)','Y','설비자산 - 설비종류 - 제원(상세)','Y','SUB'); 

/**  2018-06-27 매일유업 반영 */

/** 2018-06-27 김정우 */
INSERT INTO TAPAGE(page_id, file_name, description, remark, is_use, is_chkauth, page_type,page_categ) VALUES 
(next value for sqapage_id, 'maWoResultWoImageList','작업-사진','작업-사진','Y','Y','SUB','WORESULT_WOIMAGE'); 

/** 2018-06-28 이지수 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'maWoReqTypeSelect','작업요청유형','N','작업요청유형','Y','LOV');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'reqInvWorkDetail','투자요청(상세)','Y','투자요청(상세)','Y','SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'reqInvRecWorkDetail','투자요청접수(상세)','Y','투자요청접수(상세)','Y','SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'reqInvRecWorkResList','요청관리 - 요청접수(투자) - 처리사항(목록)','Y','요청관리 - 요청접수(투자) - 처리사항(목록)','Y','SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'reqInvRecWorkResDetail','요청관리 - 요청접수(투자) - 처리사항(상세)','Y','요청관리 - 요청접수(투자) - 처리사항(상세)','Y','SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'reqInvRecWorkDocLibList','요청관리 - 요청접수(투자) - 첨부문서(목록)','Y','요청관리 - 요청접수(투자) - 첨부문서(목록)','Y','SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'reqInvRecWorkDocLibDetail','요청관리 - 요청접수(투자) - 첨부문서(상세)','Y','요청관리 - 요청접수(투자) - 첨부문서(상세)','Y','SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'reqInvWorkDocLibList','요청관리 - 작업요청(투자) - 첨부문서(목록)','Y','요청관리 - 작업요청(투자) - 첨부문서(목록)','Y','SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'reqInvWorkDocLibDetail','요청관리 - 작업요청(투자) - 첨부문서(상세)','Y','요청관리 - 작업요청(투자) - 첨부문서(상세)','Y','SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'reqInvWorkResList','요청관리 - 작업요청(투자) - 처리사항(목록)','Y','요청관리 - 작업요청(투자) - 처리사항(목록)','Y','SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'reqInvWorkResDetail','요청관리 - 작업요청(투자) - 처리사항(상세)','Y','요청관리 - 작업요청(투자) - 처리사항(상세)','Y','SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'lovInvtPopup','투자 목록 팝업','N','투자 목록 팝업','Y','LOV');

/** 2018-06-28 이근환 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'mgrIntfList','시스템관리 - 인터페이스 Log','Y','시스템관리 - 인터페이스 Log','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'mgrIntfDetail','시스템관리 - 인터페이스 Log(상세)','Y','시스템관리 - 인터페이스 Log(상세)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'mgrIntfLogList','시스템관리 - 인터페이스 Log(상세) - Log','Y','시스템관리 - 인터페이스 Log(상세) - Log','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'mgrIntfLogDetail','시스템관리 - 인터페이스 Log(상세) - Log(상세)','Y','시스템관리 - 인터페이스 Log(상세) - Log(상세)','Y','SUB'); 

/** 2018-06-29 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type)
VALUES( NEXT VALUE FOR sqapage_id, 'workCalPmInsApprRsltList','예방점검계획승인 - 점검결과(목록)','Y','예방점검계획승인 - 점검결과 (목록)','Y','MAIN');

/**  2018-07-02 매일유업 반영 */

/** 2018-07-03 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'invtEquipList','설비 (목록)','Y','투자목록 - 설비 (목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'invtItemsList','구매항목 (목록)','Y','투자목록 - 구매항목 (목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'invtItemsDetail','구매항목 (상세)','Y','투자목록 - 구매항목 (상세)','Y','SUB'); 

/**  2018-07-03 매일유업 반영 */

/** 2018-07-05 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR SQAPAGE_ID, 'lovEqCtgPartAcList','설비종류별부품 팝업','N','설비종류별부품 팝업','Y','LOV');

/** 2018-07-05 이지수 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workRptSelfVendorList','사내,외주작업현황 (목록)','Y','작업관리 - Report - 사내,외주작업현황 (목록)','Y','MAIN'); 

/** 2018-07-05 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR SQAPAGE_ID, 'lovEqCtgSpecAcList','설비종류별제원 팝업','N','설비종류별제원 팝업','Y','LOV');

/** 2018-07-09 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'consultPgmLinkedFuncList','연결기능 (Linked Function) (목록)','Y','컨설트 - 프로그램설정 - 연결기능 (Linked Function) (목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'consultPgmLinkedFuncDetail','연결기능 (Linked Function) (상세)','Y','컨설트 - 프로그램설정 - 연결기능 (Linked Function) (상세)','N','SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'consultPgmPgLinkedFuncList','화면별 연결기능 (목록)','Y','컨설트 - 프로그램설정 - 화면 - 연결기능 (목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'consultPgmPgLinkedFuncDetail','화면별 연결기능 (상세)','N','컨설트 - 프로그램설정 - 화면 - 연결기능 (상세)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR SQAPAGE_ID, 'lovLinkedFuncAcList','Linked Function 팝업','N','Linked Function 팝업','Y','LOV');
/** 2018-07-10 김정우 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(next value for sqapage_id, 'cdSysCodeParentLov','시스템코드 부모 AC LOV','N','시스템 코드 부모 AC LOV','Y', 'LOV');

/** 2018-07-11 김정우 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use,  page_type)
VALUES(next value for sqapage_id, 'cdUsrCodeParentLov','사용자 부모 AC LOV','N','사용자 코드 부모 AC LOV','Y', 'LOV');

/**  2018-07-12 매일유업 반영 */


/** 2018-07-13 김정우 */
INSERT INTO TAPAGE(page_id, file_name, description, remark, is_use, is_chkauth, page_type,page_categ) VALUES 
(next value for sqapage_id, 'maPgMngFieldValueList','화면별 필드 기본값 목록','','Y','Y','MAIN','PG_MNG_FIELD'); 
INSERT INTO TAPAGE(page_id, file_name, description, remark, is_use, is_chkauth, page_type,page_categ) VALUES 
(next value for sqapage_id, 'maPgMngFieldValueDetail','화면별 필드 기본값 상세','','Y','Y','SUB','PG_MNG_FIELD'); 


/**  2018-07-13 DREAM 반영 */

/** 2018-07-13 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'mgrToBeProcessList','TO-BE 프로세스','Y','Site매뉴 - TO-BE 프로세스','Y','MAIN'); 

/** 2018-07-15 이지수 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'workPmListMsTimeList','예방점검 - 예방점검설정 - 작업시간(목록)','Y','예방점검 - 예방점검설정 - 작업시간(목록)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'workPmListMsTimeDetail','예방점검 - 예방점검설정 - 작업시간(상세)','Y','예방점검 - 예방점검설정 - 작업시간(상세)','Y','SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'workPmListMsTimeLov','작업시간 팝업','N','작업시간 팝업','Y','LOV');

/** 2018-07-18 이지수 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'reqRptPreWoreqRateList','지표관리 - 요청지표 - 작업의뢰 사전 시스템 요청비율(목록)','Y','지표관리 - 요청지표 - 작업의뢰 사전 시스템 요청비율(목록)','Y','MAIN'); 

/**  2018-07-19 매일유업 반영 */
/**  2018-07-19 국도 반영 */

/** 2018-07-20 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'reqWoInvtRsltList','투자결과 (목록)','Y','요청접수 - 투자결과 (목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'reqWoRsltList','작업결과 (목록)','Y','요청접수 - 작업결과 (목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'reqWoPlanRsltList','작업계획 (목록)','Y','요청접수 - 작업계획 (목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR SQAPAGE_ID, 'lovWoPlanAcList','Work Plan 팝업','N','Work Plan 팝업','Y','LOV');

/** 2018-07-20 이지수 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'maWoPmwOvhResultMstrList','작업관리 - Overhaul 작업목록 (목록)','Y','작업관리 - Overhaul 작업목록','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'reqRptWoPlanCmptRateList','지표관리 - 요청지표 - 작업의뢰 계획대비 실행 비율(목록)','Y','지표관리 - 요청지표 - 작업의뢰 계획대비 실행 비율(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'reqRptEmWoGenRateList','지표관리 - 요청지표 - 사후 작업오더 발생률(목록)','Y','지표관리 - 요청지표 - 사후 작업오더 발생률(목록)','Y','MAIN'); 

/** 2018-07-24 국도 반영 */
/** 2018-07-24 DREAM 반영 */
/** 2018-07-26 국도 반영 */
/** 2018-07-27 매일유업 반영 */

/** 2018-07-27 이지수 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'reqRptPreWoPlanRateList','지표관리 - 요청지표 - 작업오더 사전 계획 수립률(목록)','Y','지표관리 - 요청지표 - 작업오더 사전 계획 수립률(목록)','Y','MAIN'); 

/** 2018-07-30 이지수 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'workRptWoEndRateList','지표관리 - 작업지표 - 작업오더 일마감 처리율(목록)','Y','지표관리 - 작업지표 - 작업오더 일마감 처리률(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'workRptPmwCmptRateList','지표관리 - 작업지표 - 예방정비 계획대비 실행 비율(목록)','Y','지표관리 - 작업지표 - 예방정비 계획대비 실행 비율(목록)','Y','MAIN'); 

/** 2018-07-30 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workCalUnitedMonthList','월간작업일정 (통합)','Y','작업관리 - 월간작업일정 (통합)','Y','MAIN'); 

/** 2018-07-30 이지수 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'workRptPmwRateList','지표관리 - 작업지표 - 예방정비 실행 비율(목록)','Y','지표관리 - 작업지표 - 예방정비 실행 비율(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'workRptPmiEquipCmptRateList','지표관리 - 작업지표 - 예방점검설비 실행 비율(목록)','Y','지표관리 - 작업지표 - 예방점검설비 실행 비율(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'workRptPmiCmptRateList','지표관리 - 작업지표 - 예방점검항목 실행 비율(목록)','Y','지표관리 - 작업지표 - 예방점검항목 실행 비율(목록)','Y','MAIN'); 

/** 2018-07-31 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'assetStdProductEquipList','생산설비 (목록)','Y','생산품목 - 생산설비 (목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'assetStdProductEquipDetail','생산설비 (상세)','N','생산품목 - 생산설비 (상세)','Y','SUB'); 

/** 2018-08-01 김정우 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(next value for sqapage_id, 'maPmMstrPointValueDetail','점검항목(수치)','Y','예방점검 마스터 - 점검항목(수치)','Y','MAIN'); 

/** 2018-08-02 양소영 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'mgrMessageTransList','시스템관리 - Message전송현황(목록)','Y','시스템관리 - Message전송현황(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'mgrMessageTransDetail','시스템관리 - Message전송현황(상세)','Y','시스템관리 - Message전송현황(상세)','Y','SUB'); 

/** 2018-08-03 김영주 추가  */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'assAssetTlList','계측기위험도평가(목록)','Y','계측기위험도평가(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'assAssetTlDetail','계측기위험도평가(상세)','Y','계측기위험도평가(상세)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'assAssetTlScoreList','계측기위험도평가점수(목록)','Y','계측기위험도평가점수(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'assAssetTlScoreDetail','계측기위험도평가점수(상세)','Y','계측기위험도평가점수(상세)','Y','SUB'); 

/** 2018-08-03 최지상  */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'workRptPmiEquipPlanRateList','지표관리 - 작업지표 - 예방점검 설비 계획대비 실행 비율','Y','지표관리 - 작업지표 - 예방점검 설비 계획대비 실행 비율','Y','MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'invtRptPriceExeRateList','지표관리 - 투자지표 - 투자비 진행현황','Y','지표관리 - 투자지표 - 투자비 진행현황','Y','MAIN');

/** 2018-08-06 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'mgrAudTrailList','Audit Trail (목록)','Y','Audit Trail (목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'mgrAudTrailDetail','Audit Trail (상세)','N','Audit Trail (상세)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'mgrAudTrailDtlList','변경사항 (목록)','Y','Audit Trail - 변경사항 (목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'mgrAudTrailDtlDetail','변경사항 (상세)','N','Audit Trail - 변경사항 (상세)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR SQAPAGE_ID, 'assetListAssTlList','계측기마스터 - 계측기위험도평가(목록)','Y','계측기마스터 - 계측기위험도평가(목록)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR SQAPAGE_ID, 'assetListAssTlDetail','계측기마스터 - 계측기위험도평가(상세)','Y','계측기마스터 - 계측기위험도평가(상세)','Y','SUB'); 

/** 2018-08-06 이근환 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(next value for SQAPAGE_ID, 'daewoongPartPurReqList','대웅제약 - 단가계약요청','Y','대웅제약 - 단가계약요청','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(next value for SQAPAGE_ID, 'daewoongPartPurReqDetail','대웅제약 - 단가계약요청(상세)','Y','대웅제약 - 단가계약요청(상세)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(next value for SQAPAGE_ID, 'daewoongPartPurReqPartsList','대웅제약 - 단가계약요청(상세) - 부품','Y','대웅제약 - 단가계약요청(상세) - 부품','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(next value for SQAPAGE_ID, 'daewoongPartPurReqPartsDetail','대웅제약 - 단가계약요청(상세) - 부품(상세)','Y','대웅제약 - 단가계약요청(상세) - 부품(상세)','Y','SUB'); 

/** 2018-08-06 김영주 */
DELETE FROM TAPGBTN WHERE page_id = (SELECT page_id FROM TAPAGE WHERE file_name = 'mgrAudTrailDtlDetail');
DELETE FROM TAPGPAGE WHERE c_page_id = (SELECT page_id FROM TAPAGE WHERE file_name = 'mgrAudTrailDtlDetail');
DELETE FROM TAPAGE WHERE file_name = 'mgrAudTrailDtlDetail';

/** 2018-08-07 국도 반영 */

/** 2018-08-07 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR SQAPAGE_ID, 'assetRptMaintCostList','수선유지비 집행현황 (목록)','Y','설비지표 - 수선유지비 집행현황 (목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'assetRptBdIntensityList','설비별 고장강도율 (목록)','Y','설비지표 - 설비별 고장강도율 (목록)','Y','MAIN'); 


/** 2018-08-07 이지수 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'assetRptBdFrequencyList','설비별 고장도수율 (목록)','Y','설비지표 - 설비별 고장도수율 (목록)','Y','MAIN'); 

/** 2018-08-08 김은아 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workLetCategList','시스템관리 - 작업설정 - 안전작업유형','Y','시스템관리 - 작업설정 - 안전작업유형설정','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workLetCategDetail','시스템관리 - 작업설정 - 안전작업유형(상세)','Y','시스템관리 - 작업설정 - 안전작업유형설정(상세)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workLetCategPointList','시스템관리 - 작업설정 - 안전작업유형(상세) - 점검항목','Y','시스템관리 - 작업설정 - 안전작업유형설정(상세) - 점검항목','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workLetCategPointDetail','시스템관리 - 작업설정 - 안전작업유형(상세) - 점검항목(상세)','Y','시스템관리 - 작업설정 - 안전작업유형설정(상세) - 점검항목(상세)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workLetCategEtcList','시스템관리 - 작업설정 - 안전작업유형(상세) - 보완사항','Y','시스템관리 - 작업설정 - 안전작업유형설정(상세) - 보완사항','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workLetCategEtcDetail','시스템관리 - 작업설정 - 안전작업유형(상세) - 보완사항(상세)','Y','시스템관리 - 작업설정 - 안전작업유형설정(상세) - 보완사항(상세)','Y','SUB'); 

/** 2018-08-08 국도 반영 */

/**  2018-08-08 김정우 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(next value for sqapage_id, 'maMailGroupDetail','메일링 상세 (그룹)','Y','메일링 상세 (그룹)','Y','MAIN'); 

/** 2018-08-09 국도 반영 */
/** 2018-08-10 국도 반영 */

/** 2018-08-13 김남현 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'lovDbConAcList','컨텐츠 검색 팝업','N','컨텐츠 검색 팝업','Y','LOV'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'persPrivDbSetContList','컨텐츠(목록)','Y','개인설정 - 대시보드 - 컨텐츠(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'persPrivDbSetContDetail','컨텐츠(상세)','N','개인설정 - 대시보드 - 컨텐츠(상세)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'persPrivDbSetList','대시보드(목록)','Y','개인설정 - 대시보드(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'persPrivDbSetDetail','대시보드(상세)','N','개인설정 - 대시보드(상세)','Y','SUB');

/** 2018-08-14 최지상*/
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workServiceList','시스템관리 - 작업설정 - 서비스마스터','Y','시스템관리 - 작업설정 - 서비스마스터','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR SQAPAGE_ID, 'workServiceDetail','시스템관리 - 작업설정 - 서비스마스터(상세)','Y','시스템관리 - 작업설정 - 서비스마스터(상세)','Y','SUB'); 

/** 2018-08-14 김영주 */
update tapage set is_chkauth ='N' where file_name = 'mgrAudTrailDtlList';
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'mgrAtList','Audit Trail (목록)','Y','시스템관리 - Audit Trail (목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'mgrAtDetail','Audit Trail (상세)','Y','시스템관리 - Audit Trail (상세)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'mgrAtHistList','Audit Trail History (목록)','Y','시스템관리 - Audit Trail History (목록)','Y','SUB'); 

/** 2018-08-16 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'assetRptLccUsDeptList','고장TOP(사용부서) (목록)','Y','설비자산 - Report - 고장TOP(사용부서) (목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'assetRptLccUsDeptDetailList','고장TOP(사용부서) (상세목록)','Y','설비자산 - Report - 고장TOP(사용부서) (상세목록)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'assetRptLccUsDeptDetailChart','고장TOP(사용부서) (상세차트)','Y','설비자산 - Report - 고장TOP(사용부서) (상세차트)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'assetRptLccUsDeptWorkTimeDetailChart','고장TOP(사용부서) (작업시간차트)','Y','설비자산 - Report - 고장TOP(사용부서) (작업시간차트)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'assetRptMtbfmttrUsDeptList','MTBF,MTTR(사용부서) (목록)','Y','설비자산 - Report - MTBF,MTTR(사용부서) (목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'assetRptMtbfmttrUsDeptDetailList','MTBF,MTTR(사용부서) (상세목록)','Y','설비자산 - Report - MTBF,MTTR(사용부서) (상세목록)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'assetRptMtbfmttrUsDeptDetailChart','MTBF,MTTR(사용부서) (상세차트)','Y','설비자산 - Report - MTBF,MTTR(사용부서) (상세차트)','Y','SUB'); 

/** 2018-08-16 이근환 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(next value for SQAPAGE_ID, 'maPtMstrStockList','부품마스터(상세) - 현재고','Y','부품마스터(상세) - 현재고','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(next value for SQAPAGE_ID, 'maPtMstrRecStatList','부품마스터(상세) - 입고이력','Y','부품마스터(상세) - 입고이력','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(next value for SQAPAGE_ID, 'maPtMstrIssStatList','부품마스터(상세) - 출고이력','Y','부품마스터(상세) - 출고이력','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(next value for SQAPAGE_ID, 'maPtMstrWoPtHistList','부품마스터(상세) - 설비사용이력','Y','부품마스터(상세) - 설비사용이력','Y','SUB'); 

/** 2018-08-16 김정우 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES
(next value for sqapage_id, 'noAuthPageList','noAuthPageList','N','권한없음','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES
(next value for sqapage_id, 'noAuthPageDetail','noAuthPageDetail','N','권한없음','Y','SUB'); 

/** 2018-08-17 국도 반영 */

/** 2018-08-20 김영주 추가 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workRptDeptWorkPreconList','부서별 작업진행현황(목록)','Y','작업관리 - Report - 부서별 작업진행현황(목록)','Y','MAIN'); 

/** 2018-08-21 DREAM 반영 */

/** 2018-08-21 최지상 추가 */
DELETE FROM TAPGBTN WHERE page_id=(SELECT page_id FROM TAPAGE WHERE file_name='assBasePointValScript');
DELETE FROM TAPGPAGE WHERE c_page_id = (SELECT page_id FROM TAPAGE WHERE file_name = 'assBasePointValScript');
DELETE FROM TAPAGE WHERE file_name='assBasePointValScript';

/** 2018-08-21 이근환 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(next value for SQAPAGE_ID, 'partAdjStkMoveList','재고이동','Y','재고이동','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(next value for SQAPAGE_ID, 'partAdjStkMoveDetail','재고이동(상세)','Y','재고이동(상세)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(next value for SQAPAGE_ID, 'lovPtStckAcList','재고선택 Ac Lov','N','재고선택 Ac Lov','Y','LOV'); 

/** 2018-08-22 매일유업 반영 */

/** 2018-08-27 김남현 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'assetRptAssAssetScoreList','설비등급평가 항목별 점수','Y','설비자산 - Report - 설비등급평가 항목별 점수(목록)','Y','MAIN');

/** 2018-08-27 김은아 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'mgrRptScrnLogList','화면접속로그 (목록)','Y','시스템관리 - Report - 화면접속로그 (목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'mgrRptLoginLogList','로그인로그 (목록)','Y','시스템관리 - Report - 로그인로그 (목록)','Y','MAIN'); 

/** 2018-08-28 국도 반영 */

/** 2018-09-04 최지상*/
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'mgrPtWhBinList','보관위치(목록)','Y','시스템관리 - 부품창고 - 보관위치(목록)','N','SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'mgrPtWhBinDetail','보관위치(상세)','Y','시스템관리 - 부품창고 - 보관위치(상세)','N','SUB');

/** 2018-09-04 이지수 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(next value for SQAPAGE_ID, 'assAssetScoreCopyLov','평가결과복사 Ac Lov','N','평가결과복사 Ac Lov','Y','LOV'); 
 
/** 2018-09-04 국도 반영 */

/** 2018-09-06 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workRptDailyEngList','에너지 사용량(일별) (목록)','Y','설비자산 - Report - 에너지 사용량(일별) (목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workRptDailyEngDetailList','에너지 사용량(일별) (상세목록)','Y','설비자산 - Report - 에너지 사용량(일별) (상세목록)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workRptDailyEngDetailChart','에너지 사용량(일별) (상세차트)','Y','설비자산 - Report - 에너지 사용량(일별) (상세차트)','Y','SUB'); 

/** 2018-09-06 매일유업 반영 */

/** 2018-09-06 김남현 */
UPDATE tapage set description = '부품관리 - 구매 - 현장구매청구(목록)', remark = '부품관리 - 구매 - 현장구매청구(목록)' WHERE page_id = (SELECT page_id FROM tapage WHERE file_name = 'maPtPurReqList');

/** 2018-09-10 국도 반영 */

/** 2018-09-10 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workRptEqEngList','에너지 사용량(설비별) (목록)','Y','설비자산 - Report - 에너지 사용량(설비별) (목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workRptEqEngDetailChart','에너지 사용량(설비별) (상세차트)','Y','설비자산 - Report - 에너지 사용량(설비별) (상세차트)','Y','SUB'); 

/**  2018-09-12 최지상 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'invtRptMonInvtAmtList','월별투자집행금액(목록)','Y','지표관리 - 투자지표 - 월별투자집행금액(목록)','N','MAIN');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'invtRptMonInvtAmtDetailChart','월별투자집행금액(상세)','Y','지표관리 - 투자지표 - 월별투자집행금액(상세)','N','SUB');

/**  2018-09-12 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workRptTotEngList','에너지사용량 (집계) (목록)','Y','설비자산 - Report - 에너지사용량 (집계) (목록)','Y','MAIN');  
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workRptTotEngLocDetailList','위치별 에너지사용량 (집계) (상세목록차트)','Y','설비자산 - Report - 위치별 에너지사용량 (집계) (상세목록차트)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workRptTotEngUsageDeptDetailList','사용부서별 에너지사용량 (집계) (상세목록차트)','Y','설비자산 - Report - 사용부서별 에너지사용량 (집계) (상세목록차트)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workRptTotEngCtgDetailList','종류별 에너지사용량 (집계) (상세목록차트)','Y','설비자산 - Report - 종류별 에너지사용량 (집계) (상세목록차트)','Y','SUB'); 

/** 2018-09-13 김은아 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'lovWorkPmDInsList','점검항목 (Lov)','Y','작업관리 - 예방작업설정 - 예방점검설정 - 점검항목 (Lov)','Y','LOV'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'maEqCatalogPointList','점검항목 (목록)','Y','설비자산 - 설비종류 - 점검항목 (목록)','N','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'maEqCatalogPointDetail','점검항목 (상세)','Y','설비자산 - 설비종류 - 점검항목 (상세)','N','SUB'); 

/**  2018-09-14 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'invtRptInvtCategList','투자구분분석 (목록)','Y','설비투자 - Report - 투자구분분석 (목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'invtRptInvtCategDetailList','투자구분분석 (상세목록)','Y','설비투자 - Report - 투자구분분석 (상세목록)','Y','SUB'); 

/** 2018-09-14 양소영 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'maBdPointWoReqList','작업관리 - Report - 이상점검이력 - 작업요청(목록)','Y','작업관리 - Report - 이상점검이력 - 작업요청(목록)','N','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'maBdPointWoPlanList','작업관리 - Report - 이상점검이력 - 작업계획(목록)','Y','작업관리 - Report - 이상점검이력 - 작업계획(목록)','N','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'maBdPointWoRsltList','작업관리 - Report - 이상점검이력 - 작업결과(목록)','Y','작업관리 - Report - 이상점검이력 - 작업결과(목록)','N','SUB'); 

/** 2018-09-14 양소영 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'lovWoReqAcList','Work Req 팝업','N','Work Req 팝업','Y','LOV'); 


/** 2018-09-17 최지상 */

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'invtRptLeadTimeList','투자진행LeadTime분석(목록)','Y','지표관리 - 투자지표 - 투자진행 LeadTime 분석(목록)','N','MAIN');

/** 2018-09-20 국도 반영 */
/** 2018-09-20 매일유업 반영 */

/** 2018-09-28 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'assetRptBdIntensityFreqRateDetailChart','고장도수율(월별)','Y','설비투자 - Report - 고장강도율 - 고장도수율(월별)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'assetRptBdIntensityDuraRateDetailChart','고장강도율(월별)','Y','설비투자 - Report - 고장강도율 - 고장강도율(월별)','Y','SUB'); 

/** 2018-10-01 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type)
VALUES( NEXT VALUE FOR sqapage_id, 'workCalPmInsApprSchedList','예방점검계획승인 - 점검작업(목록)','Y','예방점검계획승인 - 점검작업 (목록)','Y','MAIN');


/** 2018-10-09 노정현 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'mgrUserPlantauthList','사용자 공장권한(목록)','Y','사용자 공장권한(목록)','Y','MAIN');

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type)
VALUES(NEXT VALUE FOR sqapage_id, 'mgrUserPlantauthDetail','사용자 공장권한(상세)','Y','사용자 공장권한(상세)','Y','MAIN');

/** 2018-10-12 국도화학 반영 */

/** 2018-10-17 이지수 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(next value for SQAPAGE_ID, 'lovPartPurBuyPnList','현장신청부품 선택 Lov','N','현장신청부품 선택 Ac Lov','Y','LOV'); 

/** 2018-10-18 국도화학 반영 */

/** 2018-10-23 최지상 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'partListBinList','보관위치(목록)','Y','부품목록 - 보관위치(목록)','Y','SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'partListBinDetail','보관위치(상세)','Y','부품목록 - 보관위치(상세)','Y','SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'lovPartListBinList','보관위치(LOV)','Y','부품목록 - 보관위치(LOV)','Y','LOV');

/** 2018-10-23 이근환 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'lovAccountList','Account Popup','N','Account Popup','Y','LOV'); 

/** 2018-10-24 최지상 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'partListSafQtyList','안전재고(목록)','Y','부품목록 - 안전재고(목록)','Y','SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'partListSafQtyDetail','안전재고(상세)','Y','부품목록 - 안전재고(상세)','Y','SUB');

/** 2018-10-24 김은아 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'partRptMonthlyStockList','부품수불부 요약(목록)','Y','부품관리 - 부품수불부 (목록)','Y','MAIN'); 

/** 2018-10-24 양소영 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workCalPmDInsMonthDetail','일상점검일정(상세)','Y','일상점검일정(상세)','Y','SUB'); 

/** 2018-10-25 국도화학 반영 */

/** 2018-10-26 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workPlanApprEquipList','계측기(목록)','Y','교정작업계획승인 - 계측기(목록)','Y','SUB'); 

/** 2018-10-26 국도화학 반영 */

/** 2018-10-30 이근환 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'partIssWoPartList','부품관리 - 출고 - 부품출고(상세) - 작업','Y','부품관리 - 출고 - 부품출고(상세) - 작업','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'lovWoPartsAcList','Wo Part Popup','N','Wo Part Popup','Y','LOV'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'lovPtIssAcList','Part Iss. Popup','N','Part Iss. Popup','Y','LOV'); 

/** 2018-10-30 매일유업 반영 */
/** 2018-11-01 국도화학 반영 */
/** 2018-11-02 매일유업 반영 */
/** 2018-11-07 본사 DREAM 반영 */

/** 2018-11-07 김은아 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'mgrCalLineTimeSetList','가동달력설정 (목록)','Y','시스템관리 - 작업설정 - 가동달력설정(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'mgrCalLineTimeSetDetail','가동달력설정(상세)','Y','시스템관리 - 작업설정 - 가동달력설정(상세)','Y','SUB'); 

/** 2018-11-08 국도화학 반영 */
/** 2018-11-08 매일유업 반영 */

/** 2018-11-09 김은아 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'mgrCalCompWkrcalList','근무일달력설정 (목록)','Y','시스템관리 - 작업설정 - 근무일달력설정(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'mgrCalCompWkrcalDetail','근무일달력설정 (상세)','Y','시스템관리 - 작업설정 - 근무일달력설정(상세)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'mgrCalCompWkrcalDowsetList','휴무요일 설정(목록)','Y','시스템관리 - 작업설정 - 근무일달력설정 - 휴무요일 설정(목록)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'mgrCalCompWkrcalDowsetDetail','휴무요일 설정(상세)','Y','시스템관리 - 작업설정 - 근무일달력설정 - 휴무요일 설정(상세)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'mgrCalCompWkrcalDaysetList','휴무일 설정 (목록)','Y','시스템관리 - 작업설정 - 근무일달력설정 - 휴무일 설정(목록)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'mgrCalCompWkrcalDaysetDetail','휴무일 설정 (상세)','Y','시스템관리 - 작업설정 - 근무일달력설정 - 휴무일 설정(목록)','Y','SUB'); 

/** 2018-11-09 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'consultCompIntfList','인터페이스설정(목록)','Y','컨설트 - 회사설정 - 인터페이스설정(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'consultCompIntfDetail','인터페이스설정(상세)','Y','컨설트 - 회사설정 - 인터페이스설정(상세)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'consultCompIntfMapList','인터페이스맵핑(목록)','Y','컨설트 - 회사설정 - 인터페이스설정 - 인터페이스맵핑(목록)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'consultCompIntfMapDetail','인터페이스맵핑(상세)','Y','컨설트 - 회사설정 - 인터페이스설정 - 인터페이스맵핑(상세)','Y','SUB'); 

/** 2018-11-12 김은아 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'mgrCalLineTimeDowSetList','요일별 설정 (목록)','Y','시스템관리 - 작업설정 - 가동달력설정 - 요일별 설정(목록)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'mgrCalLineTimeDowSetDetail','요일별 설정 (상세)','Y','시스템관리 - 작업설정 - 가동달력설정(상세)','Y','SUB'); 

/** 2018-11-15 국도화학 반영 */

/** 2018-11-15 이근환 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'budgetAccountList','예산계정','Y','예산계정','N','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'budgetAccountDetail','예산계정(상세)','Y','예산계정(상세)','N','SUB'); 

/** 2018-11-16 국도화학 반영 */

/** 2018-11-19 김남현 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'lovPoItemAcList','부품관리 - 입고 - 구매입고 - 발주선택(Lov)','N','부품관리 - 입고 - 구매입고 - 발주선택(Lov)','Y','LOV'); 

/** 2018-11-19 본사 국도화학 반영 */
/** 2018-11-19 국도화학 반영 */

/**2018-11-20 김정우 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type)
 VALUES(next value for sqapage_id, 'lovToolPartsAcList','공기구 LOV','N','공기구LOV','Y','LOV'); 
  
/** 2018-11-20 국도화학 반영 */
/** 2018-11-21 국도화학 반영 */
 
/** 2018-11-21 김정우 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(next value for sqapage_id, 'maWoResultToolList','투입공기구 목록','Y','작업상세-투입공기구','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(next value for sqapage_id, 'maWoResultToolDetail','투입공기구 상세','Y','작업상세-투입공기구','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(next value for sqapage_id, 'maWoResultBmRplToolList','투입공기구 목록','Y','작업상세-투입공기구','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(next value for sqapage_id, 'maWoResultBmRplToolDetail','투입공기구 상세','Y','작업상세-투입공기구','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(next value for sqapage_id, 'maWoResultBmOilToolList','투입공기구 목록','Y','작업상세-투입공기구','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(next value for sqapage_id, 'maWoResultBmOilToolDetail','투입공기구 상세','Y','작업상세-투입공기구','Y','SUB'); 

/** 2018-11-23 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'mgrRptLoginTryLogList','로그인 Try Log(목록)','Y','지표관리 - 시스템지표 - 로그인 Try Log(목록)','Y','MAIN'); 

/** 2018-11-28 국도화학 반영 */
/** 2018-11-29 국도화학 반영 */
/** 2018-12-03 국도화학 반영 */
/** 2018-12-05 본사 DREAM 반영 */
/** 2018-12-06 매일유업 반영 */

/** 2018-12-07 이지수 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'maBdPointDocLibList','예방점검 - 이상점검결과등록 - 첨부문서(목록)','Y','예방점검 - 이상점검결과등록 - 첨부문서(목록)','Y','SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'maBdPointDocLibDetail','예방점검 - 이상점검결과등록 - 첨부문서(상세)','Y','예방점검 - 이상점검결과등록 - 첨부문서(상세)','Y','SUB');

/** 2018-12-07 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'assetRptLccLocMaintAmtDetailChart','설비지표 - 고장TOP(위치) - 보전비용차트', 'N', '설비지표 - 고장TOP(위치) - 보전비용차트','Y','SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES(NEXT VALUE FOR sqapage_id, 'assetRptLccEquipMaintAmtDetailChart','설비지표 - 고장TOP(설비) - 보전비용차트', 'N', '설비지표 - 고장TOP(설비) - 보전비용차트','Y','SUB');

/** 2018-12-10 양소영 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'woPlanWoLetList','안전작업(목록)','Y','작업관리 - 작업계획목록 - 안전작업(목록)','N','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'woPlanWoLetDetail','안전작업(상세)','Y','작업관리 - 작업계획목록 - 안전작업(상세)','N','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'lovWorkLetCategList','안전작업유형 LOV','N','안전작업유형 LOV','Y','LOV'); 

/** 2018-12-14 국도화학 반영 */

/** 2018-12-14 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'mgrMsgRecList','메시지 수신설정(목록)','Y','시스템관리 - 메시지 수신설정(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'mgrMsgRecDetail','메시지 수신설정(상세)','Y','시스템관리 - 메시지 수신설정(상세)','Y','SUB'); 

/** 2018-12-17 10:08 국도화학 반영 */

/** 2018-12-17 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'consultCompMsgList','메시지 설정(메일, SMS)(목록)','Y','컨설트 - 회사 - 메시지 설정(메일, SMS)(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'consultCompMsgDetail','메시지 설정(메일, SMS)(상세)','N','컨설트 - 회사 - 메시지 설정(메일, SMS)(상세)','Y','SUB'); 

/** 2018-12-17 12:20 국도화학 반영 */
/** 2018-12-19 11:00 국도화학 반영 */
/** 2018-12-19 17:25 국도화학 반영 */
/** 2018-12-20 17:10 국도화학 반영 */
/** 2018-12-21 17:50 국도화학 반영 */

/** 2018-12-26 양소영 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workLetList','작업관리 - 안전작업(목록)','Y','작업관리 - 안전작업(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workLetDetail','작업관리 - 안전작업(상세)','Y','작업관리 - 안전작업(상세)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workLetPermitList','작업관리 - 안전작업 - 안전작업허가서(목록)','Y','작업관리 - 안전작업 - 안전작업허가서(목록)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workLetPermitDetail','작업관리 - 안전작업 - 안전작업허가서(상세)','Y','작업관리 - 안전작업 - 안전작업허가서(상세)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workLetPermitPointList','안전작업 - 안전작업허가서 - 점검항목(목록)','Y','안전작업 - 안전작업허가서 - 점검항목(목록)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workLetPermitPointDetail','안전작업 - 안전작업허가서 - 점검항목(상세)','Y','안전작업 - 안전작업허가서 - 점검항목(상세)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workLetPermitCraftList','안전작업 - 안전작업허가서 - 작업자(목록)','Y','안전작업 - 안전작업허가서 - 작업자(목록)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workLetPermitCraftDetail','안전작업 - 안전작업허가서 - 작업자(상세)','Y','안전작업 - 안전작업허가서 - 작업자(상세)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workLetPermitDocLibList','안전작업 - 안전작업허가서 - 첨부문서(목록)','Y','안전작업 - 안전작업허가서 - 첨부문서(목록)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workLetPermitDocLibDetail','안전작업 - 안전작업허가서 - 첨부문서(상세)','Y','안전작업 - 안전작업허가서 - 첨부문서(상세)','Y','SUB'); 

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'lovWorkLetCategPointList','안전작업허가서 표준점검항목 LOV','N','안전작업허가서 표준점검항목 LOV','Y','LOV'); 

/** 2018-12-28 14:20 국도화학 반영 */

/** 2019-01-03 김영주 */
UPDATE TAPAGE SET file_name = 'consultPgmMsgList' , REMARK = '컨설트 - 프로그램설정 - 메시지 설정(메일, SMS)(목록)' WHERE file_name = 'consultCompMsgList';
UPDATE TAPAGE SET file_name = 'consultPgmMsgDetail' , REMARK = '컨설트 - 프로그램설정 - 메시지 설정(메일, SMS)(상세)' WHERE file_name = 'consultCompMsgDetail';
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type, service_type) VALUES( NEXT VALUE FOR sqapage_id, 'lovMsgCategList','메시지 타입 팝업','N','메시지 타입 팝업','Y','LOV','WEB');

/** 2019-01-03 고려용접봉(본사) 반영 */

/** 2019-01-04 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'persPrivInfoMsgEmpList','메시지 수신설정(목록)','Y','개인설정 - 개인정보 - 메시지수신설정(목록)','Y','SUB'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'persPrivInfoMsgEmpDetail','메시지 수신설정(상세)','Y','개인설정 - 개인정보 - 메시지수신설정(상세)','Y','SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'lovMsgCompList','메시지 전송유형 팝업','N','메시지 전송유형 팝업','Y','LOV');

/** 2019-01-07 국도화학(본사) 반영 */

/** 2019-01-11 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'lovUsrPlantAuthList','공장권한 팝업','N','공장권한 팝업','Y','LOV');

/** 2019-01-11 국도화학 반영 */
/** 2019-01-17 국도화학 반영 */
/** 2019-01-22 고려용접봉(본사) 반영 */
/** 2019-01-22 국도화학 반영 */
/** 2019-01-25 국도화학 반영 */
/** 2019-01-30 국도화학 반영 */



/** 2019-01-30 이근환 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'mgrIntfParams','인터페이스 파라미터 popup','N','인터페이스 파라미터 popup','Y','LOV'); 

/** 2019-01-31 국도화학 반영 */
/** 2019-02-14 국도화학 반영 */
/** 2019-02-19 매일유업 반영 */
/** 2019-02-20 국도화학 반영 */
/** 2019-02-26 국도화학 반영 */
/** 2019-03-05 매일유업 반영 */


/** 2019-02-18 양소영 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workCalPmInsApprCompList','예방점검완료승인(목록)','Y','예방점검완료승인(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workCalPmInsApprCompDetail','예방점검완료승인(상세)','Y','예방점검완료승인(상세)','Y','SUB'); 

INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workCalPmInsApprNotList','미점검승인(목록)','Y','미점검승인(목록)','Y','MAIN'); 
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES( NEXT VALUE FOR sqapage_id, 'workCalPmInsApprNOTDetail','미점검승인(상세)','Y','미점검승인(상세)','Y','SUB'); 

/** 2019-03-11 이근환 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, service_type, key_type, key_no) 
VALUES(NEXT VALUE FOR sqapage_id, 'workRptPmwCmptDetailList','예방작업 실행 상세 데이타','N','예방작업 실행 상세 데이타','Y','WEB',UPPER(''),UPPER(''));	


/** 2019-03-11 이근환 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, service_type, key_type, key_no) 
VALUES(NEXT VALUE FOR sqapage_id, 'workRptPmwDetailList','주기정비 실행 상세 데이타','N','주기정비 실행 상세 데이타','Y','WEB',UPPER(''),UPPER(''));	

UPDATE tapage set description='주기정비 계획실행 상세 데이타',remark='주기정비 계획실행 상세 데이타' 
WHERE file_name='workRptPmwCmptDetailList';


/** 2019-03-12 이근환 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, service_type, key_type, key_no) 
VALUES(NEXT VALUE FOR sqapage_id, 'workRptWoPmwCmptRateList','예방작업실행율','N','예방작업실행율','Y','WEB',UPPER(''),UPPER(''));
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, service_type, key_type, key_no) 
VALUES(NEXT VALUE FOR sqapage_id, 'workRptWoPmwCmptDetailList','예방작업 실행 상세 데이타','N','예방작업 실행 상세 데이타','Y','WEB',UPPER(''),UPPER(''));



/** 2019-03-11 김영주 */
DELETE FROM TAMENU WHERE page_id =(SELECT page_id FROM TAPAGE WHERE file_name = 'assetRptBdFrequencyList');
DELETE FROM TAPGFIELD WHERE PAGE_ID = (SELECT PAGE_ID FROM TAPAGE WHERE FILE_NAME = 'assetRptBdFrequencyList');
DELETE FROM TAPGGRIDCOL WHERE PGGRID_ID = (SELECT PGGRID_ID FROM TAPGGRID WHERE PAGE_ID = (SELECT PAGE_ID FROM TAPAGE WHERE FILE_NAME = 'assetRptBdFrequencyList'));
DELETE FROM TAPGGRID WHERE PAGE_ID = (SELECT PAGE_ID FROM TAPAGE WHERE FILE_NAME = 'assetRptBdFrequencyList');
DELETE FROM TAPGBTN WHERE PAGE_ID = (SELECT PAGE_ID FROM TAPAGE WHERE FILE_NAME = 'assetRptBdFrequencyList');
DELETE FROM TAPAGE WHERE FILE_NAME = 'assetRptBdFrequencyList';
UPDATE TAPAGE SET description = '설비별 고장강도,도수율 (목록)', REMARK = '설비지표 - 설비별 고장강도,도수율 (목록)' WHERE file_name = 'assetRptBdIntensityList';

/** 2019-03-12 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, service_type, key_type, key_no) VALUES(NEXT VALUE FOR sqapage_id, 'reqRptEmWoGenDetailList','작업의뢰 작업발행 목록','N','작업의뢰 작업발행 목록','Y','WEB',UPPER('LABEL'),UPPER('monthly'));	


/** 2019-03-12 양소영 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, service_type, key_type, key_no) VALUES(NEXT VALUE FOR sqapage_id, 'workRptPmiEquipCmptDetailList','예방점검 실행 상세 데이타','N','예방점검 실행 상세 데이타','Y','WEB',UPPER('PAGE'),UPPER('workRptPmiEquipCmptDetailList'));


/** 2019-03-12 양소영 */
UPDATE TAPAGE SET key_type=UPPER('PAGE'), key_no=UPPER('workRptPmiCmptRateList') WHERE file_name='workRptPmiCmptRateList';

/** 2019-03-12 이근환 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, service_type, key_type, key_no) 
VALUES(NEXT VALUE FOR sqapage_id, 'workRptPmiCmptDetailList','예방점검 항목실행 상세 데이타','N','예방점검 항목실행 상세 데이타','Y','WEB',UPPER(''),UPPER(''));	


/** 2019-03-12 양소영 */
UPDATE TAPAGE SET key_type='PAGE', key_no=UPPER('workRptPmiEquipPlanRateList') WHERE file_name='workRptPmiEquipPlanRateList';

/** 2019-03-12 이근환 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, service_type, key_type, key_no) 
VALUES(NEXT VALUE FOR sqapage_id, 'workRptPmiEquipPlanDetailList','예방점검  계획실행 상세 데이타','N','예방점검  계획실행 상세 데이타','Y','WEB',UPPER(''),UPPER(''));	


/** 2019-03-12 김은아 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(next value for sqapage_id, 'reqRptPreWoPlanDetailList','작업 사전계획 수립율','Y','작업 사전계획 수립율','Y','SUB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, remark, is_use, page_type) VALUES(next value for sqapage_id, 'workRptWoEndDetailList','작업 일마감 처리 상세 데이타','Y','작업 일마감 처리 상세 데이타','Y','SUB'); 

/** 2019-03-12 김남현 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, service_type, key_type, key_no) VALUES(NEXT VALUE FOR sqapage_id, 'reqRptPreWoreqDetailList','작업의뢰 시스템 요청 목록','Y','작업의뢰 시스템 요청 목록','Y','WEB','LABEL','plant');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, service_type, key_type, key_no) VALUES(NEXT VALUE FOR sqapage_id, 'reqRptWoPlanCmptDetailList','작업의뢰 초기계획 요청 목록','Y','작업의뢰 초기계획 요청 목록','Y','WEB','LABEL','plant');

UPDATE TAPAGE SET is_chkauth = 'N' WHERE file_name = 'reqRptWoPlanCmptDetailList';
UPDATE TAPAGE SET is_chkauth = 'N' WHERE file_name = 'reqRptPreWoreqDetailList';

/** 2019-03-13 국도화학 반영 */
/** 2019-03-13 고려용접봉 반영 */
/** 2019-03-19 매일유업 반영 */



/** 2019-03-18 김은아 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, service_type, key_type, key_no) VALUES(NEXT VALUE FOR sqapage_id, 'consultPgmUnderWork','프로그램 개발중','N','프로그램 개발중','Y','WEB',UPPER(''),UPPER(''));


/** 2019-03-26 김정우 */
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO)
VALUES (NEXT VALUE FOR SQAPAGE_ID, 'WoReqListFragment','작업요청목록','작업요청목록','Y','Y','MAIN','','ANDROID','PAGE','WoReqListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO)
VALUES (NEXT VALUE FOR SQAPAGE_ID, 'WoReqDetailFragment','작업요청상세','작업요청상세','Y','Y','SUB','','ANDROID','PAGE','WoReqDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO)
VALUES (NEXT VALUE FOR SQAPAGE_ID, 'WoReqPhotoListFragment','작업요청 사진목록','작업요청 사진목록','Y','Y','MAIN','','ANDROID','PAGE','WoReqPhotoListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO)
VALUES (NEXT VALUE FOR SQAPAGE_ID, 'WoReqPhotoDetailFragment','작업요청 사진상세','작업요청 사진상세','Y','Y','SUB','','ANDROID','PAGE','WoReqPhotoDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'AssetListFragment','설비자산목록','설비자산목록','Y','Y','MAIN','','ANDROID','PAGE','AssetListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'AssetDetailFragment','설비자산상세','설비자산상세','Y','Y','SUB','','ANDROID','PAGE','AssetDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'AssetDocumentListFragment','설비자산 문서목록','설비자산 문서목록','Y','Y','MAIN','','ANDROID','PAGE','AssetDocumentListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'AssetDocumentDetailFragment','설비자산 문서상세','설비자산 문서상세','Y','Y','SUB','','ANDROID','PAGE','AssetDocumentDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'AssetToolListFragment','설비자산 계측기목록','설비자산 계측기목록','Y','Y','MAIN','','ANDROID','PAGE','AssetToolListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'AssetToolDetailFragment','설비자산 계측기상세','설비자산 계측기상세','Y','Y','SUB','','ANDROID','PAGE','AssetToolDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'CalibrationDialog','교정','교정','Y','Y','LOV','','ANDROID','PAGE','CalibrationDialog');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'DeptDialog','부서','부서','Y','Y','LOV','','ANDROID','PAGE','DeptDialog');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'EmpDialog','사원','사원','Y','Y','LOV','','ANDROID','PAGE','EmpDialog');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'EquipDialog','설비','설비','Y','Y','LOV','','ANDROID','PAGE','EquipDialog');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'EqAsmbDialog','부위','부위','Y','Y','LOV','','ANDROID','PAGE','EqAsmbDialog');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'EqlocDialog','위치','위치','Y','Y','LOV','','ANDROID','PAGE','EqlocDialog');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'FailureDialog','분류','분류','Y','Y','LOV','','ANDROID','PAGE','FailureDialog');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PartsDialog','부품','부품','Y','Y','LOV','','ANDROID','PAGE','PartsDialog');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PlantDialog','공장','공장','Y','Y','LOV','','ANDROID','PAGE','PlantDialog');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'SyscodeDialog','sys코드','sys코드','Y','Y','LOV','','ANDROID','PAGE','SyscodeDialog');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'UsrcodeDialog','usr코드','usr코드','Y','Y','LOV','','ANDROID','PAGE','UsrcodeDialog');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'WhDialog','창고','창고','Y','Y','LOV','','ANDROID','PAGE','WhDialog');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'WkctrDialog','작업','작업','Y','Y','LOV','','ANDROID','PAGE','WkctrDialog');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'IssListFragment','부품출고목록','부품출고목록','Y','Y','MAIN','','ANDROID','PAGE','IssListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'IssDetailFragment','부품출고상세','부품출고상세','Y','Y','SUB','','ANDROID','PAGE','IssDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'LoginFragment','로그인','로그인','Y','Y','MAIN','','ANDROID','PAGE','LoginFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'MainActivity','메인','메인','Y','Y','MAIN','','ANDROID','PAGE','MainActivity');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PlandownListFragment','계획다운로드목록','계획다운로드목록','Y','Y','MAIN','','ANDROID','PAGE','PlandownListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PlandownOptionFragment','계획다운로드옵션','계획다운로드옵션','Y','Y','SUB','','ANDROID','PAGE','PlandownOptionFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PmiDayListFragment','일상점검목록','일상점검목록','Y','Y','MAIN','','ANDROID','PAGE','PmiDayListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PmiDayDetailFragment','일상점검상세','일상점검상세','Y','Y','SUB','','ANDROID','PAGE','PmiDayDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PmiDayPointFragment','일상점검항목','일상점검항목','Y','Y','SUB','','ANDROID','PAGE','PmiDayPointFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PmiDayPhotoListFragment','일상점검 사진목록','일상점검 사진목록','Y','Y','MAIN','','ANDROID','PAGE','PmiDayPhotoListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PmiDayPhotoDetailFragment','일상점검 사진상세','일상점검 사진상세','Y','Y','SUB','','ANDROID','PAGE','PmiDayPhotoDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PmiDayHistListFragment','일상점검 이력목록','일상점검 이력목록','Y','Y','MAIN','','ANDROID','PAGE','PmiDayHistListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PmiDayHistDetailFragment','일상점검 이력상세','일상점검 이력상세','Y','Y','SUB','','ANDROID','PAGE','PmiDayHistDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PmiPartListFragment','Part Change점검목록','Part Change점검목록','Y','Y','MAIN','','ANDROID','PAGE','PmiPartListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PmiPartDetailFragment','Part Change점검상세','Part Change점검상세','Y','Y','SUB','','ANDROID','PAGE','PmiPartDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PmiPartPointFragment','Part Change점검항목','Part Change점검항목','Y','Y','SUB','','ANDROID','PAGE','PmiPartPointFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PmiPartPhotoListFragment','Part Change점검 사진목록','Part Change점검 사진목록','Y','Y','MAIN','','ANDROID','PAGE','PmiPartPhotoListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PmiPartPhotoDetailFragment','Part Change점검 사진상세','Part Change점검 사진상세','Y','Y','SUB','','ANDROID','PAGE','PmiPartPhotoDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PmiPartHistListFragment','Part Change점검 이력목록','Part Change점검 이력목록','Y','Y','MAIN','','ANDROID','PAGE','PmiPartHistListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PmiPartHistDetailFragment','Part Change점검 이력상세','Part Change점검 이력상세','Y','Y','SUB','','ANDROID','PAGE','PmiPartHistDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PmiPatrolListFragment','순회점검목록','순회점검목록','Y','Y','MAIN','','ANDROID','PAGE','PmiPatrolListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PmiPatrolDetailFragment','순회점검상세','순회점검상세','Y','Y','SUB','','ANDROID','PAGE','PmiPatrolDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PmiPatrolPointFragment','순회점검항목','순회점검항목','Y','Y','SUB','','ANDROID','PAGE','PmiPatrolPointFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PmiPatrolPhotoListFragment','순회점검 사진목록','순회점검 사진목록','Y','Y','MAIN','','ANDROID','PAGE','PmiPatrolPhotoListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PmiPatrolPhotoDetailFragment','순회점검 사진상세','순회점검 사진상세','Y','Y','SUB','','ANDROID','PAGE','PmiPatrolPhotoDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PmiPatrolHistListFragment','순회점검 이력목록','순회점검 이력목록','Y','Y','MAIN','','ANDROID','PAGE','PmiPatrolHistListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PmiPatrolHistDetailFragment','순회점검 이력상세','순회점검 이력상세','Y','Y','SUB','','ANDROID','PAGE','PmiPatrolHistDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PmiRoutineListFragment','정기점검목록','정기점검목록','Y','Y','MAIN','','ANDROID','PAGE','PmiRoutineListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PmiRoutineDetailFragment','정기점검상세','정기점검상세','Y','Y','SUB','','ANDROID','PAGE','PmiRoutineDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PmiRoutinePointFragment','정기점검항목','정기점검항목','Y','Y','SUB','','ANDROID','PAGE','PmiRoutinePointFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PmiRoutinePhotoListFragment','정기점검 사진목록','정기점검 사진목록','Y','Y','MAIN','','ANDROID','PAGE','PmiRoutinePhotoListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PmiRoutinePhotoDetailFragment','정기점검 사진상세','정기점검 사진상세','Y','Y','SUB','','ANDROID','PAGE','PmiRoutinePhotoDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PmiRoutineHistListFragment','정기점검 이력목록','정기점검 이력목록','Y','Y','MAIN','','ANDROID','PAGE','PmiRoutineHistListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PmiRoutineHistDetailFragment','정기점검 이력상세','정기점검 이력상세','Y','Y','SUB','','ANDROID','PAGE','PmiRoutineHistDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'WoResListFragment','요청접수목록','요청접수목록','Y','Y','MAIN','','ANDROID','PAGE','WoResListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'WoResDetailFragment','요청접수상세','요청접수상세','Y','Y','SUB','','ANDROID','PAGE','WoResDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'WoResPhotoListFragment','요청접수 사진목록','요청접수 사진목록','Y','Y','MAIN','','ANDROID','PAGE','WoResPhotoListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'WoResPhotoDetailFragment','요청접수 사진상세','요청접수 사진상세','Y','Y','SUB','','ANDROID','PAGE','WoResPhotoDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'WoResWorkListFragment','요청접수 처리사항목록','요청접수 처리사항목록','Y','Y','MAIN','','ANDROID','PAGE','WoResWorkListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'SearchOptionFragment','조건조회설정목록','조건조회설정목록','Y','Y','MAIN','','ANDROID','PAGE','SearchOptionFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'InitialDownFragment','초기설정목록','초기설정목록','Y','Y','MAIN','','ANDROID','PAGE','InitialDownFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'OpenLicenseActivity','오픈라이선스목록','오픈라이선스목록','Y','Y','MAIN','','ANDROID','PAGE','OpenLicenseActivity');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PolicyActivity','개인정보처리방침','개인정보처리방침','Y','Y','MAIN','','ANDROID','PAGE','PolicyActivity');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'StktakeHdrListFragment','부품실사목록','부품실사목록','Y','Y','MAIN','','ANDROID','PAGE','StktakeHdrListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'StktakeListFragment','부품실사 부품목록','부품실사 부품목록','Y','Y','MAIN','','ANDROID','PAGE','StktakeListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'StktakeDetailFragment','부품실사 부품상세','부품실사 부품상세','Y','Y','SUB','','ANDROID','PAGE','StktakeDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'StockListFragment','현재고목록','현재고목록','Y','Y','MAIN','','ANDROID','PAGE','StockListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'StockDetailFragment','현재고상세','현재고상세','Y','Y','SUB','','ANDROID','PAGE','StockDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'UploadFragment','업로드','업로드','Y','Y','MAIN','','ANDROID','PAGE','UploadFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'CalListFragment','검교정목록','검교정목록','Y','Y','MAIN','','ANDROID','PAGE','CalListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'CalDetailFragment','검교정상세','검교정상세','Y','Y','SUB','','ANDROID','PAGE','CalDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'CalBaseEqListFragment','검교정 표준기목록','검교정 표준기목록','Y','Y','MAIN','','ANDROID','PAGE','CalBaseEqListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'CalBaseEqDetailFragment','검교정 표준기상세','검교정 표준기상세','Y','Y','SUB','','ANDROID','PAGE','CalBaseEqDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'CalCalibGnlValueListFragment','검교정 측정값목록(일반)','검교정 측정값목록(일반)','Y','Y','MAIN','','ANDROID','PAGE','CalCalibGnlValueListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'CalCalibGnlValueDetailFragment','검교정 측정값상세(일반)','검교정 측정값상세(일반)','Y','Y','SUB','','ANDROID','PAGE','CalCalibGnlValueDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'CalCalibPrsValueListFragment','검교정 측정값목록(압력)','검교정 측정값목록(압력)','Y','Y','MAIN','','ANDROID','PAGE','CalCalibPrsValueListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'CalCalibPrsValueDetailFragment','검교정 측정값상세(압력)','검교정 측정값상세(압력)','Y','Y','SUB','','ANDROID','PAGE','CalCalibPrsValueDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'CalCalibSclValueFragment','검교정 측정값상세(저울)','검교정 측정값상세(저울)','Y','Y','MAIN','','ANDROID','PAGE','CalCalibSclValueFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'CalCalibValueListFragment','검교정 측정값목록','검교정 측정값목록','Y','Y','MAIN','','ANDROID','PAGE','CalCalibValueListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'CalCalibValueDetailFragment','검교정 측정값상세','검교정 측정값상세','Y','Y','SUB','','ANDROID','PAGE','CalCalibValueDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'CalCraftListFragment','검교정 작업자목록','검교정 작업자목록','Y','Y','MAIN','','ANDROID','PAGE','CalCraftListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'CalCraftDetailFragment','검교정 작업자상세','검교정 작업자상세','Y','Y','SUB','','ANDROID','PAGE','CalCraftDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'CalPhotoListFragment','검교정 사진목록','검교정 사진목록','Y','Y','MAIN','','ANDROID','PAGE','CalPhotoListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'CalPhotoDetailFragment','검교정 사진상세','검교정 사진상세','Y','Y','SUB','','ANDROID','PAGE','CalPhotoDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'WoHistListFragment','작업이력목록','작업이력목록','Y','Y','MAIN','','ANDROID','PAGE','WoHistListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'WoHistDetailFragment','작업이력상세','작업이력상세','Y','Y','SUB','','ANDROID','PAGE','WoHistDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'InspectionListFragment','예방점검목록','예방점검목록','Y','Y','MAIN','','ANDROID','PAGE','InspectionListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'InspectionDetailFragment','예방점검항목','예방점검항목','Y','Y','SUB','','ANDROID','PAGE','InspectionDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'WopointDetailFragment','예방점검항목 상세','예방점검항목 상세','Y','Y','SUB','','ANDROID','PAGE','WopointDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'InspectionPhotoListFragment','예방점검 사진목록','예방점검 사진목록','Y','Y','MAIN','','ANDROID','PAGE','InspectionPhotoListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'InspectionPhotoDetailFragment','예방점검 사진상세','예방점검 사진상세','Y','Y','SUB','','ANDROID','PAGE','InspectionPhotoDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'InspectionHistListFragment','예방점검 이력목록','예방점검 이력목록','Y','Y','MAIN','','ANDROID','PAGE','InspectionHistListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'InspectionHistDetailFragment','예방점검 이력상세','예방점검 이력상세','Y','Y','SUB','','ANDROID','PAGE','InspectionHistDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PmworkListFragment','계획작업목록','계획작업목록','Y','Y','MAIN','','ANDROID','PAGE','PmworkListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PmworkDetailFragment','계획작업상세','계획작업상세','Y','Y','SUB','','ANDROID','PAGE','PmworkDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PmworkCraftListFragment','계획작업 작업자목록','계획작업 작업자목록','Y','Y','MAIN','','ANDROID','PAGE','PmworkCraftListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PmworkCraftDetailFragment','계획작업 작업자상세','계획작업 작업자상세','Y','Y','SUB','','ANDROID','PAGE','PmworkCraftDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PmworkPartsListFragment','계획작업 부품목록','계획작업 부품목록','Y','Y','MAIN','','ANDROID','PAGE','PmworkPartsListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PmworkPartsDetailFragment','계획작업 부품상세','계획작업 부품상세','Y','Y','SUB','','ANDROID','PAGE','PmworkPartsDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PmworkPhotoListFragment','계획작업 사진목록','계획작업 사진목록','Y','Y','MAIN','','ANDROID','PAGE','PmworkPhotoListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'PmworkPhotoDetailFragment','계획작업 사진상세','계획작업 사진상세','Y','Y','SUB','','ANDROID','PAGE','PmworkPhotoDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'UnplanListFragment','돌발작업목록','돌발작업목록','Y','Y','MAIN','','ANDROID','PAGE','UnplanListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'UnplanDetailFragment','돌발작업상세','돌발작업상세','Y','Y','SUB','','ANDROID','PAGE','UnplanDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'UnplanFailDetailFragment','돌발작업 고장목록','돌발작업 고장목록','Y','Y','SUB','','ANDROID','PAGE','UnplanFailDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'UnplanCraftListFragment','돌발작업 작업자목록','돌발작업 작업자목록','Y','Y','MAIN','','ANDROID','PAGE','UnplanCraftListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'UnplanCraftDetailFragment','돌발작업 작업자상세','돌발작업 작업자상세','Y','Y','SUB','','ANDROID','PAGE','UnplanCraftDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'UnplanPartsListFragment','돌발작업 부품목록','돌발작업 부품목록','Y','Y','MAIN','','ANDROID','PAGE','UnplanPartsListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'UnplanPartsDetailFragment','돌발작업 부품상세','돌발작업 부품상세','Y','Y','SUB','','ANDROID','PAGE','UnplanPartsDetailFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'UnplanPhotoListFragment','돌발작업 사진목록','돌발작업 사진목록','Y','Y','MAIN','','ANDROID','PAGE','UnplanPhotoListFragment');
INSERT INTO TAPAGE (PAGE_ID, FILE_NAME, DESCRIPTION, REMARK, IS_USE, IS_CHKAUTH, PAGE_TYPE, PAGE_CATEG, SERVICE_TYPE, KEY_TYPE, KEY_NO) VALUES (NEXT VALUE FOR SQAPAGE_ID, 'UnplanPhotoDetailFragment','돌발작업 사진상세','돌발작업 사진상세','Y','Y','SUB','','ANDROID','PAGE','UnplanPhotoDetailFragment');

/** 2019-04-04 매일유업 반영 */
/** 2019-04-15 고려용접봉 반영 */
/** 2019-04-17 국도화학 반영 */

/** 2019-04-17 김은아 */
update tapage set CRE_DATE = '20190327132010';
update tapage set UPD_DATE = '20190327132010';

/** 2019-04-18 매일유업 반영 */
/** 2019-04-26 매일유업 반영 */


/** 2019-04-26 이지수 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type, service_type, KEY_TYPE, KEY_NO) VALUES(next value for sqapage_id, 'workListDeptSchedList','업체별 작업스케쥴','Y','','N','ML','WEB', 'PAGE', 'workListDeptSchedList');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type, service_type, KEY_TYPE, KEY_NO) VALUES(next value for sqapage_id, 'workListDeptSchedListDeptList','업체별 작업스케쥴탭부서별 작업','Y','','N','SL','WEB', 'PAGE', 'workListDeptSchedListDeptList');


/** 2019-04-24 김은아 */
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'excelExport','엑셀Lov','엑셀Lov','Y','N','LV','WEB','PAGE','excelExport','','DREAM','2.00','PAGE','excelExport','20190331131020','20190331131020'); 	 


/** 2019-04-15 이근환 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, service_type, key_type, key_no) 
VALUES(NEXT VALUE FOR sqapage_id, 'workListWeekWoList','작업실행일정(주간)','Y','작업실행일정(주간)','N','WEB','','');        

/** 2019-05-08 매일유업 반영 */
/** 2019-05-10 국도화학 반영 */


/** 2019-04-12 이지수 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type, service_type) 
VALUES(next value for sqapage_id, 'workPmListUInsList','사용량측정주기 검색,목록','Y','등록된 사용량측정주기데이타의 목록을 조회','N','MAIN','WEB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type, service_type) 
VALUES(next value for sqapage_id, 'workPmListUInsDetail','사용량측정주기상세','Y','작업제목,주기,근무달력등 사용량 점검 기준 데이타 관리','N','SUB','WEB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, service_type) 
VALUES(NEXT VALUE FOR sqapage_id, 'workPmPointUInsList','사용량 항목','Y','측정기준의 실측항목을 관리','N','WEB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, service_type) 
VALUES(NEXT VALUE FOR sqapage_id, 'workPmPointUInsDetail','사용량 항목(상세)','Y','측정기준의 실측항목을 관리, 상세등록, 수정화면','N','WEB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type, service_type) 
VALUES(NEXT VALUE FOR sqapage_id, 'workPmMsTimeUInsList','사용량 점검시간','Y','측정이 일일 1회 이상이면 실행할 시간을 관리','N','SUB','WEB');
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, page_type, service_type) 
VALUES(NEXT VALUE FOR sqapage_id, 'workPmMsTimeUInsDetail','사용량 점검시간(상세)','Y','측정이 일일 1회 이상이면 실행할 시간의 상세 등록,수정화면','N','SUB','WEB');



/** 2019-04-29 양소영 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, service_type, key_type, key_no) VALUES(NEXT VALUE FOR sqapage_id, 'workListEnergyMstrList','에너지값 등록 목록','Y','에너지 결과값을 등록하는 목록화면','N','WEB',UPPER('PAGE'),UPPER('workListEnergyMstrList'));
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, service_type, key_type, key_no) VALUES(NEXT VALUE FOR sqapage_id, 'workListEnergyMstrDetail','에너지값 등록 상세','Y','에너지 결과값을 등록하는 상세화면','N','WEB',UPPER('PAGE'),UPPER('workListEnergyMstrDetail'));
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, service_type, key_type, key_no) VALUES(NEXT VALUE FOR sqapage_id, 'workListEnergyPointList','에너지 값 측정항목 목록','Y','에너지 결과값의 측정항목별 결과값 목록','N','WEB',UPPER('PAGE'),UPPER('workListEnergyPointList'));
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, service_type, key_type, key_no) VALUES(NEXT VALUE FOR sqapage_id, 'workListEnergyPointDetail','에너지 값 측정항목 상세','Y','에너지 결과값의 측정항목별 결과값 상세','N','WEB',UPPER('PAGE'),UPPER('workListEnergyPointDetail'));
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, service_type, key_type, key_no) VALUES(NEXT VALUE FOR sqapage_id, 'workListEnergyPointDocLibList','에너지 값 측정항목 첨부','Y','에너지 결과값의 측정항목별 결과값 첨부목록','N','WEB',UPPER('PAGE'),UPPER('workListEnergyPointDocLibList'));
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, service_type, key_type, key_no) VALUES(NEXT VALUE FOR sqapage_id, 'workListEnergyPointDocLibDetail','에너지 값 측정항목 첨부 상세','Y','에너지 결과값의 측정항목별 결과값 첨부상세','N','WEB',UPPER('PAGE'),UPPER('workListEnergyPointDocLibDetail'));

/** 2019-04-29 양소영 */
INSERT INTO TAPAGE(page_id, file_name, description, is_chkauth, REMARK, is_use, service_type, key_type, key_no) VALUES(NEXT VALUE FOR sqapage_id, 'lovWorkPmListUInsList','에너지 측정기준주기 Lov','N','에너지 결과값 신규등록시 측정기준주기 선택 Lov','N','WEB',UPPER('PAGE'),UPPER('lovWorkPmListUInsList'));


/** 2019-04-30 이근환 */
INSERT INTO tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'mgrUsrGrpPageAuthList','화면권한설정목록','1) 권한명이 화면에 대한 권한이 있는지 확인'+char(13)+char(10)+'2) 화면을 선택하여 권한을 부여하거나 제거할 수 있음'+char(13)+char(10)+'3) 권한그룹과 화면을 이용하여 데이타를 조회하고'+char(13)+char(10)+'   권한그룹의 권한여부와 상관없이 모든 화면이 보여야 함.'+char(13)+char(10)+'   (단 사용중이며 권한체크할 화면만 보임)', 'N' ,'Y','ML','WEB','PAGE','mgrUsrGrpPageAuthList','AQ125','DREAM','2.01','PAGE','mgrUsrGrpPageAuthList',CONVERT(nvarchar,GETDATE(),112)+REPLACE(CONVERT(nvarchar,GETDATE(),108),':',''),CONVERT(nvarchar,GETDATE(),112)+REPLACE(CONVERT(nvarchar,GETDATE(),108),':','')); 
INSERT INTO tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'mgrUsrGrpPageAuthDetail','화면권한설정상세','1) 선택한 한개의 화면에 대한 권한여부를 확인'+char(13)+char(10)+'2) 한개의 화면에 대해서 권한을 부여하거나 제외할 수 있음.', 'N' ,'Y','SD','WEB','PAGE','mgrUsrGrpPageAuthDetail','AQ125','DREAM','2.01','PAGE','mgrUsrGrpPageAuthDetail',CONVERT(nvarchar,GETDATE(),112)+REPLACE(CONVERT(nvarchar,GETDATE(),108),':',''),CONVERT(nvarchar,GETDATE(),112)+REPLACE(CONVERT(nvarchar,GETDATE(),108),':','')); 
INSERT INTO tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'mgrUsrGrpPageAuthBtnList','화면권한설정상세탭버튼권한목록','1) 선택한 한개의 화면의 버튼 권한이 있는지 확인'+char(13)+char(10)+'2) 버튼을 선택하여 권한을 부여하거나 제거할 수 있음.'+char(13)+char(10)+'3) 권한그룹과 화면,버튼을 이용하여 데이타를 조회하고'+char(13)+char(10)+'   권한그룹의 권한여부와 상관없이 모든 버튼이 보여야 함.'+char(13)+char(10)+'   (단 사용중이며 권한체크할 화면만 보임)', 'N' ,'Y','SL','WEB','PAGE','mgrUsrGrpPageAuthBtnList','AQ125','DREAM','2.01','PAGE','mgrUsrGrpPageAuthBtnList',CONVERT(nvarchar,GETDATE(),112)+REPLACE(CONVERT(nvarchar,GETDATE(),108),':',''),CONVERT(nvarchar,GETDATE(),112)+REPLACE(CONVERT(nvarchar,GETDATE(),108),':','')); 
INSERT INTO tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'mgrUsrGrpPageAuthBtnDetail','화면권한설정상세탭버튼권한상세','1) 선택한 한개의 버튼에 대한 권한여부를 확인'+char(13)+char(10)+'2) 한개의 버튼에 대해서 권한을 부여하거나 제외할 수 있음.', 'N' ,'Y','SD','WEB','PAGE','mgrUsrGrpPageAuthBtnDetail','AQ125','DREAM','2.01','PAGE','mgrUsrGrpPageAuthBtnDetail',CONVERT(nvarchar,GETDATE(),112)+REPLACE(CONVERT(nvarchar,GETDATE(),108),':',''),CONVERT(nvarchar,GETDATE(),112)+REPLACE(CONVERT(nvarchar,GETDATE(),108),':','')); 
INSERT INTO tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'mgrUsrGrpPageAuthTabList','화면권한설정상세탭탭권한목록','1) 선택한 한개의 화면의 탭화면 권한이 있는지 확인'+char(13)+char(10)+'2) 탭화면을 선택하여 권한을 부여하거나 제거할 수 있음.'+char(13)+char(10)+'3) 권한그룹과 화면,탭화면을 이용하여 데이타를 조회하고'+char(13)+char(10)+'   권한그룹의 권한여부와 상관없이 모든 탭화면이 보여야 함'+char(13)+char(10)+'   (단 사용중이며 권한체크할 화면만 보임)', 'N' ,'Y','SL','WEB','PAGE','mgrUsrGrpPageAuthTabList','AQ125','DREAM','2.01','PAGE','mgrUsrGrpPageAuthTabList',CONVERT(nvarchar,GETDATE(),112)+REPLACE(CONVERT(nvarchar,GETDATE(),108),':',''),CONVERT(nvarchar,GETDATE(),112)+REPLACE(CONVERT(nvarchar,GETDATE(),108),':','')); 
INSERT INTO tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'mgrUsrGrpPageAuthTabDetail','화면권한설정상세탭탭권한상세','1) 선택한 한개의 탭화면에 대한 권한여부를 확인'+char(13)+char(10)+'2) 한개의 탭화면에 대해서 권한을 부여하거나 제외할 수 있음.', 'N' ,'Y','SD','WEB','PAGE','mgrUsrGrpPageAuthTabDetail','AQ125','DREAM','2.01','PAGE','mgrUsrGrpPageAuthTabDetail',CONVERT(nvarchar,GETDATE(),112)+REPLACE(CONVERT(nvarchar,GETDATE(),108),':',''),CONVERT(nvarchar,GETDATE(),112)+REPLACE(CONVERT(nvarchar,GETDATE(),108),':','')); 


 /** 2019-05-14 노정현 */

 insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
 values(next value for sqapage_id, replace('consultCompUsrGrpList',' ',''), replace('Site별권한그룹설정목록',' ',''), replace('Site별권한그룹설정목록',' ',''), replace('N',' ',''), replace('Y',' ',''), replace('ML',' ',''), replace('WEB',' ',''), replace('PAGE',' ',''), replace('consultCompUsrGrpList',' ',''), replace('EA135',' ',''), replace('DREAM',' ',''), replace('2.01',' ',''), replace('PAGE',' ',''), replace('consultCompUsrGrpList',' ',''), convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121)); 
 insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
 values(next value for sqapage_id, replace('consultCompUsrGrpDetail',' ',''), replace('Site별권한그룹설정상세',' ',''), replace('Site별권한그룹설정상세',' ',''), replace('N',' ',''), replace('Y',' ',''), replace('SD',' ',''), replace('WEB',' ',''), replace('PAGE',' ',''), replace('consultCompUsrGrpDetail',' ',''), replace('EA135',' ',''), replace('DREAM',' ',''), replace('2.01',' ',''), replace('PAGE',' ',''), replace('consultCompUsrGrpDetail',' ',''), convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121)); 
 insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
 values(next value for sqapage_id, replace('consultCompUsrGrpWebAuthList',' ',''), replace('Site별권한그룹설정상세탭Web권한',' ',''), replace('Site별권한그룹설정상세탭Web권한',' ',''), replace('N',' ',''), replace('Y',' ',''), replace('SL',' ',''), replace('WEB',' ',''), replace('PAGE',' ',''), replace('consultCompUsrGrpWebAuthList',' ',''), replace('EA135',' ',''), replace('DREAM',' ',''), replace('2.01',' ',''), replace('PAGE',' ',''), replace('consultCompUsrGrpWebAuthList',' ',''), convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121)); 
 insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
 values(next value for sqapage_id, replace('consultCompUsrGrpBeeAuthList',' ',''), replace('Site별권한그룹설정상세탭Bee권한',' ',''), replace('Site별권한그룹설정상세탭Bee권한',' ',''), replace('N',' ',''), replace('Y',' ',''), replace('SD',' ',''), replace('WEB',' ',''), replace('PAGE',' ',''), replace('consultCompUsrGrpBeeAuthList',' ',''), replace('EA135',' ',''), replace('DREAM',' ',''), replace('2.01',' ',''), replace('PAGE',' ',''), replace('consultCompUsrGrpBeeAuthList',' ',''), convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121)); 
 insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
 values(next value for sqapage_id, replace('consultCompUsrGrpPageAuthList',' ',''), replace('Site별권한그룹설정상세탭페이지권한',' ',''), replace('Site별권한그룹설정상세탭페이지권한',' ',''), replace('N',' ',''), replace('Y',' ',''), replace('SL',' ',''), replace('WEB',' ',''), replace('PAGE',' ',''), replace('consultCompUsrGrpPageAuthList',' ',''), replace('EA135',' ',''), replace('DREAM',' ',''), replace('2.01',' ',''), replace('PAGE',' ',''), replace('consultCompUsrGrpPageAuthList',' ',''), convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121)); 
 insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
 values(next value for sqapage_id, replace('consultCompUsrGrpBtnAuthList',' ',''), replace('Site별권한그룹설정상세탭버튼권한',' ',''), replace('Site별권한그룹설정상세탭버튼권한',' ',''), replace('N',' ',''), replace('Y',' ',''), replace('SD',' ',''), replace('WEB',' ',''), replace('PAGE',' ',''), replace('consultCompUsrGrpBtnAuthList',' ',''), replace('EA135',' ',''), replace('DREAM',' ',''), replace('2.01',' ',''), replace('PAGE',' ',''), replace('consultCompUsrGrpBtnAuthList',' ',''), convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121)); 

/** 2019-05-21 이지수 */
insert into TAPAGE(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
values (next value for sqapage_id, 'workPmiPointValueDetail','정기점검결과등록탭점검수치항목상세','정기점검결과등록탭점검수치항목상세','Y','Y','','WEB','PAGE','workPmiPointValueDetail','','DREAM','2.01','PAGE','workPmiPointValueDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into TAPAGE(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
values(next value for sqapage_id, 'workPmiRInsPointValueDocLibList','정기점검결과등록탭점검수치항목탭첨부문서목록','정기점검결과등록탭점검수치항목탭첨부문서목록','Y','Y','SL','WEB','PAGE','workPmiRInsPointValueDocLibList','AD210','DREAM','2.01','PAGE','workPmiRInsPointValueDocLibList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into TAPAGE(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
values(next value for sqapage_id, 'workPmiRInsPointValueDocLibDetail','정기점검결과등록탭점검수치항목탭첨부문서상세','정기점검결과등록탭점검수치항목탭첨부문서상세','Y','Y','SD','WEB','PAGE','workPmiRInsPointValueDocLibDetail','AD210','DREAM','2.01','PAGE','workPmiRInsPointValueDocLibDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 

/** 2019-05-24 국도화학 반영 */
/** 2019-05-24 고려용접봉 반영 */
/** 2019-05-29 고려용접봉 반영 */
/** 2019-05-30 매일유업 반영 */
/** 2019-06-04 매일유업 반영 */


/** 2019-05-22 김영주 */
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, replace('maWoDailyImageList',' ',''), replace('일일작업승인탭고객Sign목록',' ',''), replace('일일작업승인탭고객Sign목록',' ',''), replace('Y',' ',''), replace('Y',' ',''), replace('SL',' ',''), replace('WEB',' ',''), replace('PAGE',' ',''), replace('maWoDailyImageList',' ',''), replace('AJ110',' ',''), replace('',' ',''), replace('',' ',''), replace('PAGE',' ',''), replace('maWoDailyImageList',' ',''), convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121));
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, replace('maWoDailyLibList',' ',''), replace('일일작업승인탭첨부목록',' ',''), replace('일일작업승인탭첨부목록',' ',''), replace('Y',' ',''), replace('Y',' ',''), replace('SL',' ',''), replace('WEB',' ',''), replace('PAGE',' ',''), replace('maWoDailyLibList',' ',''), replace('AJ110',' ',''), replace('',' ',''), replace('',' ',''), replace('PAGE',' ',''), replace('maWoDailyLibList',' ',''), convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121)); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, replace('maWoDailyLibDetail',' ',''), replace('일일작업승인탭첨부상세',' ',''), replace('일일작업승인탭첨부상세',' ',''), replace('Y',' ',''), replace('Y',' ',''), replace('SD',' ',''), replace('WEB',' ',''), replace('PAGE',' ',''), replace('maWoDailyLibDetail',' ',''), replace('AJ110',' ',''), replace('',' ',''), replace('',' ',''), replace('PAGE',' ',''), replace('maWoDailyLibDetail',' ',''), convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121)); 

/** 2019-06-14 김남현 */
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'persPrivNoticeList','개인 사용자정의 화면 Notice 설정 목록','개인 사용자정의 화면 Notice 설정 목록', 'N' ,'N','SL','WEB','PAGE','persPrivNoticeList','AN140','','','PAGE','persPrivNoticeList',convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121)); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'persPrivNoticeDetail','개인 사용자정의 화면 Notice 설정 상세','개인 사용자정의 화면 Notice 설정 상세', 'N' ,'N','SD','WEB','PAGE','persPrivNoticeDetail','AN140','','','PAGE','persPrivNoticeDetail',convert(nvarchar(10),getdate(),121),convert(nvarchar(10),getdate(),121)); 

/** 2019-06-17 경보제약 반영 */
/** 2019-06-17 고려용접봉 반영 */
/** 2019-06-26 고려용접봉 반영 */
/** 2019-06-26 국도화학 반영 */
/** 2019-06-26 매일유업 반영 */


/** 2019-06-04 양소영 */
INSERT INTO TAPAGE(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
VALUES(NEXT VALUE FOR SQAPAGE_ID, 'assetRptLccUsDeptMaintAmtDetailChart','고장TOP(사용부서) (보전비용차트)','설비자산 - Report - 고장TOP(사용부서) (보전비용차트)', 'N' ,'N','SL','WEB','PAGE','assetRptLccUsDeptMaintAmtDetailChart','AP230','','','PAGE','assetRptLccUsDeptMaintAmtDetailChart',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 

/** 2019-06-28 매일유업 반영 */
/** 2019-07-10 경보제약 반영 */
/** 2019-07-18 경보제약 반영 */


/** 2019-07-16 김남현 */
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'workRptPmiPointValueList','정기점검항목결과','정기점검항목결과', 'N' ,'Y','ML','WEB','PAGE','workRptPmiPointValueList','AP592','DREAM','2.01','PAGE','workRptPmiPointValueList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));


/** 2019-07-25 경보제약 반영 */
/** 2019-07-30 매일유업 반영 */
/** 2019-08-07 경보제약 반영 */

/** 2019-08-07 김남현 */
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'workAlarmList','Alarm List','Alarm List 목록', 'N' ,'Y','ML','WEB','PAGE','workAlarmList','AD810','DREAM','2.01','PAGE','workAlarmList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'workAlarmDetail','Alarm List','Alarm List 상세', 'N' ,'Y','SD','WEB','PAGE','workAlarmDetail','AD810','DREAM','2.01','PAGE','workAlarmDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'workAlarmReqList','수리요청접수','Alarm에 대한 수리요청', 'N' ,'Y','SL','WEB','PAGE','workAlarmReqList','AD810','DREAM','2.01','PAGE','workAlarmReqList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 

/** 291 이지수 */
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'workRptWorkTypeRptByEmpList','담당자별 작업현황','담당자별 작업현황', 'Y' ,'Y','ML','WEB','PAGE','workRptWorkTypeRptByEmpList','AP424','DREAM','2.01','PAGE','workRptWorkTypeRptByEmpList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'workRptWorkTypeRptByEmpMonth','담당자 월별작업현황','담당자 월별작업현황', 'Y' ,'Y','SD','WEB','PAGE','workRptWorkTypeRptByEmpMonth','AP424','DREAM','2.01','PAGE','workRptWorkTypeRptByEmpMonth',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'workRptWorkTypeRptByEmpWoType','담당자 작업종류별현황','담당자 작업종류별현황', 'Y' ,'Y','SD','WEB','PAGE','workRptWorkTypeRptByEmpWoType','AP424','DREAM','2.01','PAGE','workRptWorkTypeRptByEmpWoType',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 


/** 2019-08-09 경보제약 반영 */
/** 2019-08-12 경보제약 반영 */


/** 2019-07-11 이근환 */
INSERT INTO tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
VALUES(NEXT value FOR sqapage_id, 'consultPgmSettingUpdList','프로그램Setting값업로드','프로그램Setting값업로드', 'N' ,'Y','ML','WEB','PAGE','consultPgmSettingUpdList','EB310','DREAM','2.01','PAGE','consultPgmSettingUpdList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
INSERT INTO tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
VALUES(NEXT value FOR sqapage_id, 'consultPgmSettingUpdDetail','프로그램Setting값업로드','프로그램Setting값업로드', 'N' ,'Y','SD','WEB','PAGE','consultPgmSettingUpdDetail','EB310','DREAM','2.01','PAGE','consultPgmSettingUpdDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
INSERT INTO tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
VALUES(NEXT value FOR sqapage_id, 'consultPgmSettingUpdFileDetail','프로그램Setting값업로드파일','프로그램Setting값업로드파일', 'N' ,'Y','SD','WEB','PAGE','consultPgmSettingUpdFileDetail','EB310','DREAM','2.01','PAGE','consultPgmSettingUpdFileDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
values(next value for sqapage_id, 'consultProgramUploadDataScriptList','업로드데이타설정Script','업로드데이타설정상세탭컬럼목록', 'N' ,'Y','SL','WEB','PAGE','consultProgramUploadDataScriptList','EB180','DREAM','2.01','PAGE','consultProgramUploadDataScriptList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
values(next value for sqapage_id, 'consultProgramUploadDataScriptDetail','업로드데이타설정Script','업로드데이타설정상세탭컬럼상세', 'N' ,'Y','SD','WEB','PAGE','consultProgramUploadDataScriptDetail','EB180','DREAM','2.01','PAGE','consultProgramUploadDataScriptDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 

/** 2019-08-13 경보제약 반영 */
/** 2019-08-19 경보제약 반영 */
/** 2019-08-22 경보제약 반영 */
/** 2019-08-23 국도화학 반영 */
/** 2019-08-26 본사 경보제약 반영 */
/** 2019-08-27 경보제약 반영 */

/** youngjoo38_306 김영주 */
INSERT INTO TAPAGE(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES(NEXT VALUE FOR sqapage_id, REPLACE('consultCdSysFieldList',' ',''), REPLACE('시스템코드관련Field',' ',''), REPLACE('시스템코드관련Field[TAPGFIELD.CODE_LIST_TYPE]',' ',''), REPLACE('Y',' ',''), REPLACE('Y',' ',''), REPLACE('SL',' ',''), REPLACE('WEB',' ',''), REPLACE('PAGE',' ',''), REPLACE('consultCdSysFieldList',' ',''), REPLACE('EB120',' ',''), REPLACE('DREAM',' ',''), REPLACE('2.01',' ',''), REPLACE('PAGE',' ',''), REPLACE('consultCdSysFieldList',' ',''), CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 

INSERT INTO TAPAGE(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES(NEXT VALUE FOR sqapage_id, 'mgrCdSysList','시스템코드설정','시스템코드설정', 'N' ,'Y','ML','WEB','PAGE','mgrCdSysList','AR115','DREAM','2.01','PAGE','mgrCdSysList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
INSERT INTO TAPAGE(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES(NEXT VALUE FOR sqapage_id, 'mgrCdSysDetail','시스템코드설정','시스템코드설정', 'N' ,'Y','SD','WEB','PAGE','mgrCdSysDetail','AR115','DREAM','2.01','PAGE','mgrCdSysDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
INSERT INTO TAPAGE(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES(NEXT VALUE FOR sqapage_id, 'mgrCdSysCodeList','시스템코드세부유형코드','시스템코드세부유형코드', 'N' ,'Y','SL','WEB','PAGE','mgrCdSysCodeList','AR115','DREAM','2.01','PAGE','mgrCdSysCodeList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
INSERT INTO TAPAGE(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES(NEXT VALUE FOR sqapage_id, 'mgrCdSysCodeDetail','시스템코드세부유형코드','시스템코드세부유형코드', 'N' ,'Y','SD','WEB','PAGE','mgrCdSysCodeDetail','AR115','DREAM','2.01','PAGE','mgrCdSysCodeDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
INSERT INTO TAPAGE(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES(NEXT VALUE FOR sqapage_id, 'mgrCdSysFieldList','시스템코드관련Field','시스템코드관련Field', 'N' ,'Y','SL','WEB','PAGE','mgrCdSysFieldList','AR115','DREAM','2.01','PAGE','mgrCdSysFieldList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 

/** 2019-08-29 매일유업 반영 */
/** 2019-09-05 경보제약 반영 */

/** 308 + 은아 */
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'mgrPlantMngList','공장(Plant)','공장(Plant)', 'N' ,'Y','ML','WEB','PAGE','mgrPlantMngList','AR116','DREAM','2.01','PAGE','mgrPlantMngList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'mgrPlantMngDetail','공장(Plant)','공장(Plant)', 'N' ,'Y','SD','WEB','PAGE','mgrPlantMngDetail','AR116','DREAM','2.01','PAGE','mgrPlantMngDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 


/** 2019-09-11 경보제약 반영 */

/* 277 영주 */
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'mgrWorkCloseCheckList','작업완료필수점검설정','작업완료필수점검설정목록', 'N' ,'Y','ML','WEB','PAGE','mgrWorkCloseCheckList','AR121','DREAM','2.01','PAGE','mgrWorkCloseCheckList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'mgrWorkCloseCheckDetail','작업완료필수점검설정','작업완료필수점검설정상세', 'N' ,'Y','MD','WEB','PAGE','mgrWorkCloseCheckDetail','AR121','DREAM','2.01','PAGE','mgrWorkCloseCheckDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'mgrWorkCloseCheckPointList','작업완료필수점검항목설정','작업완료필수점검항목설정목록', 'N' ,'Y','SL','WEB','PAGE','mgrWorkCloseCheckPointList','AR121','DREAM','2.01','PAGE','mgrWorkCloseCheckPointList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'mgrWorkCloseCheckPointDetail','작업완료필수점검항목설정','작업완료필수점검항목설정상세', 'N' ,'Y','SD','WEB','PAGE','mgrWorkCloseCheckPointDetail','AR121','DREAM','2.01','PAGE','mgrWorkCloseCheckPointDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'mgrWorkCloseCheckDocLibList','첨부문서','작업완료필수점검첨부문서목록', 'N' ,'Y','SL','WEB','PAGE','mgrWorkCloseCheckDocLibList','AR121','DREAM','2.01','PAGE','mgrWorkCloseCheckDocLibList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'mgrWorkCloseCheckDocLibDetail','첨부문서','작업완료필수점검첨부문서상세', 'N' ,'Y','SD','WEB','PAGE','mgrWorkCloseCheckDocLibDetail','AR121','DREAM','2.01','PAGE','mgrWorkCloseCheckDocLibDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 


/** 2019-09-18 본사 국도화학 반영 */
/** 2019-10-01 매일유업 반영 */
/** 2019-10-02 경보제약 반영 */

/** 359 이근환 */
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
values(next value for sqapage_id, 'invtWoRsltList','설비투자작업','설비도입탭투자작업목록', 'N' ,'Y','SL','WEB','PAGE','invtWoRsltList','AA110','DREAM','2.01','PAGE','invtWoRsltList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 

/** 531 이근환 */
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
values(next value for sqapage_id, 'assetRptLccEquipFailCodeDetailChart','설비고장TOP고장분석그래프','설비고장TOP건수를 현상,원인,조치 파이그래프로 조회', 'N' ,'Y','SD','WEB','PAGE','assetRptLccEquipFailCodeDetailChart','AP210','DREAM','2.01','PAGE','assetRptLccEquipFailCodeDetailChart',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
values(next value for sqapage_id, 'assetRptLccLocFailCodeDetailChart','위치고장TOP고장분석그래프','위치고장TOP건수를 현상,원인,조치 파이그래프로 조회', 'N' ,'Y','SD','WEB','PAGE','assetRptLccLocFailCodeDetailChart','AP220','DREAM','2.01','PAGE','assetRptLccLocFailCodeDetailChart',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 

/** 283 + euna0207 */
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'persPrivFilterValueDetail','검색조건저장','화면별검색조건을 저장', 'N' ,'N','LV','WEB','PAGE','persPrivFilterValueDetail','AN140','DREAM','2.01','PAGE','persPrivFilterValueDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'persPrivFilterValueList','검색조건열기','저장된 검색조건', 'N' ,'N','LV','WEB','PAGE','persPrivFilterValueList','AN140','DREAM','2.01','PAGE','persPrivFilterValueList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 

/** 2019-10-14 매일유업 반영 */

/** 445 + euna0207 */
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'assetRptEqUnits','설비별부위(Unit)','설비별 부위데이타를 검색', 'N' ,'Y','ML','WEB','PAGE','assetRptEqUnits','AP291','DREAM','2.01','PAGE','assetRptEqUnits',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 

/** 2019-10-17 매일유업 반영 */

/** youngjoo38_360 + 김영주 */
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'mgrUsageCalSetList','사용달력설정','사용달력설정목록', 'N' ,'Y','ML','WEB','PAGE','mgrUsageCalSetList','AR191','DREAM','2.01','PAGE','mgrUsageCalSetList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'mgrUsageCalSetDetail','사용달력설정','사용달력설정상세', 'N' ,'Y','SD','WEB','PAGE','mgrUsageCalSetDetail','AR191','DREAM','2.01','PAGE','mgrUsageCalSetDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'mgrUsageCalSetDowSetList','요일별기본값설정','사용달력설정탭요일별시간설정목록', 'N' ,'Y','SL','WEB','PAGE','mgrUsageCalSetDowSetList','AR191','DREAM','2.01','PAGE','mgrUsageCalSetDowSetList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'mgrUsageCalSetDowSetDetail','요일별기본값설정','사용달력설정탭요일별시간설정목록', 'N' ,'Y','SD','WEB','PAGE','mgrUsageCalSetDowSetDetail','AR191','DREAM','2.01','PAGE','mgrUsageCalSetDowSetDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 

insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'mgrUsageCalSetDayList','일별사용횟수설정','사용달력일별사용횟수설정목록', 'N' ,'Y','ML','WEB','PAGE','mgrUsageCalSetDayList','AR192','DREAM','2.01','PAGE','mgrUsageCalSetDayList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'mgrUsageCalSetDayDetail','일별사용횟수설정','사용달력일별사용횟수설정상세', 'N' ,'Y','SD','WEB','PAGE','mgrUsageCalSetDayDetail','AR192','DREAM','2.01','PAGE','mgrUsageCalSetDayDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 

INSERT INTO TAPAGE(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) VALUES(NEXT VALUE FOR sqapage_id, 'lovUsageWrkAcList','사용달력','사용달력LOV', 'N' ,'N','LV','WEB','PAGE','lovUsageWrkAcList','AR192','DREAM','2.01','PAGE','lovUsageWrkAcList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''));

/** youngjoo38_333 + 김영주 */
 insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'consultPgmRptList','출력물설정','출력물설정', 'N' ,'Y','ML','WEB','PAGE','consultPgmRptList','EB300','DREAM','2.00','PAGE','consultPgmRptList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
 insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'consultPgmRptDetail','출력물설정','출력물설정', 'N' ,'N','MD','WEB','PAGE','consultPgmRptDetail','EB300','DREAM','2.00','PAGE','consultPgmRptDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
 insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'consultPgmRptFileList','출력물파일명','출력물파일명', 'N' ,'Y','SL','WEB','PAGE','consultPgmRptFileList','EB300','DREAM','2.00','PAGE','consultPgmRptFileList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
 insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'consultPgmRptFileDetail','출력물파일명','출력물파일명', 'N' ,'N','SD','WEB','PAGE','consultPgmRptFileDetail','EB300','DREAM','2.00','PAGE','consultPgmRptFileDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 

/** 2019-10-22 고려용접봉 반영 */
/** 2019-10-29 경보제약 반영 */

 /** 658 이근환 */
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
values(next value for sqapage_id, 'partListImgLinkUrlList','부품Image Link','부품의 이미지 정보 Link URL', 'N' ,'Y','SL','WEB','PAGE','partListImgLinkUrlList','AF110','DREAM','2.01','PAGE','partListImgLinkUrlList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
values(next value for sqapage_id, 'partListImgLinkUrlDetail','부품Image Link','부품의 이미지 정보 Link URL', 'N' ,'Y','SD','WEB','PAGE','partListImgLinkUrlDetail','AF110','DREAM','2.01','PAGE','partListImgLinkUrlDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 


/** 2019-11-05 본사 국도화학 반영 */
/** 2019-11-05 고려용접봉 반영 */
/** 2019-11-06 국도화학 반영 */

/**614 + 은아 */

 /** *** 추가 *** **/
 insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'mgrLangMenuList','다국어사용된사용된메뉴','다국어사용된사용된메뉴', 'N' ,'Y','SL','WEB','PAGE','mgrLangMenuList','AR230','DREAM','2.01','PAGE','mgrLangMenuList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
 insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'mgrLangPageList','다국어사용된사용된화면','다국어사용된사용된화면', 'N' ,'Y','SL','WEB','PAGE','mgrLangPageList','AR230','DREAM','2.01','PAGE','mgrLangPageList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
 insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'mgrLangPgpageList','다국어사용된사용된탭','다국어사용된사용된탭', 'N' ,'Y','SL','WEB','PAGE','mgrLangPgpageList','AR230','DREAM','2.01','PAGE','mgrLangPgpageList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
 insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'mgrLangPgBtnList','다국어사용된버튼','다국어사용된버튼', 'N' ,'Y','SL','WEB','PAGE','mgrLangPgBtnList','AR230','DREAM','2.01','PAGE','mgrLangPgBtnList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
 insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'mgrLangPgFieldList','다국어사용된필드','다국어사용된필드', 'N' ,'Y','SL','WEB','PAGE','mgrLangPgFieldList','AR230','DREAM','2.01','PAGE','mgrLangPgFieldList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
 insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'mgrLangPgGridColList','다국어사용된그리드','다국어사용된그리드', 'N' ,'Y','SL','WEB','PAGE','mgrLangPgGridColList','AR230','DREAM','2.01','PAGE','mgrLangPgGridColList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
 insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'mgrLangPgLinkedList','다국어사용된Linked','다국어사용된Linked', 'N' ,'Y','SL','WEB','PAGE','mgrLangPgLinkedList','AR230','DREAM','2.01','PAGE','mgrLangPgLinkedList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
 insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'mgrLangSyscodeList','다국어사용된시스템코드','다국어사용된시스템코드', 'N' ,'Y','SL','WEB','PAGE','mgrLangSyscodeList','AR230','DREAM','2.01','PAGE','mgrLangSyscodeList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
 insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'mgrLangFailcodeList','다국어사용된고장코드','다국어사용된고장코드', 'N' ,'Y','SL','WEB','PAGE','mgrLangFailcodeList','AR230','DREAM','2.01','PAGE','mgrLangFailcodeList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 

 /**692 + 김은아 */
 insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'partStkCurrentList','부품현재고','부품현재고목록', 'N' ,'N','ML','WEB','PAGE','partStkCurrentList','APC25','DREAM','2.01','PAGE','partStkCurrentList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
 insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'partStkCurrentDetail','부품현재고','부품현재고상세', 'N' ,'N','MD','WEB','PAGE','partStkCurrentDetail','APC25','DREAM','2.01','PAGE','partStkCurrentDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 

 /** 2019-11-14 고려용접봉 반영 */
 
 /** 766 이근환 */
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
values(next value for sqapage_id, 'assetListPartOpQtyList','사용예상수량','설비운용기간사용예상수량', 'N' ,'Y','SL','WEB','PAGE','assetListPartOpQtyList','AB110','DREAM','2.01','PAGE','assetListPartOpQtyList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
values(next value for sqapage_id, 'assetListPartOpQtyDetail','사용예상수량','설비운용기간사용예상수량', 'N' ,'Y','SD','WEB','PAGE','assetListPartOpQtyDetail','AB110','DREAM','2.01','PAGE','assetListPartOpQtyDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 

/** 2019-11-20 매일유업 반영 */
/** 792 이근환 */
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
values(next value for sqapage_id, 'assetRptEqParts','설비구성부품','설비별 구성부품데이타를 검색', 'N' ,'Y','ML','WEB','PAGE','assetRptEqParts','AP292','DREAM','2.01','PAGE','assetRptEqParts',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 


/** youngjoo38_746 + 김영주 */
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'assetRptNYearPOList','N Year Spare Part List','설비별구성부품,운영추천보유수량', 'N' ,'Y','ML','WEB','PAGE','assetRptNYearPOList','AP293','DREAM','2.01','PAGE','assetRptNYearPOList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'assetRptNYearPartsList','N Year Spare Part List','설비별구성부품,운영추천보유수량', 'N' ,'Y','SL','WEB','PAGE','assetRptNYearPartsList','AP293','DREAM','2.01','PAGE','assetRptNYearPartsList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 


/** 2019-12-02 매일유업 반영 */

/** 774 + 김은아 */
 insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'mgrUsrGrpPageAuthFieldList','화면필드권한설정','1) 화면 필드에 대해서 조회제한, 수정제한 설정 목록화면', 'N' ,'Y','SL','WEB','PAGE','mgrUsrGrpPageAuthFieldList','AQ125','DREAM','2.01','PAGE','mgrUsrGrpPageAuthFieldList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
 insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'mgrUsrGrpPageAuthFieldDetail','화면필드권한설정','1) 화면 필드에 대해서 조회제한, 수정제한 설정 상세화면', 'N' ,'Y','SD','WEB','PAGE','mgrUsrGrpPageAuthFieldDetail','AQ125','DREAM','2.01','PAGE','mgrUsrGrpPageAuthFieldDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
 insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'mgrUsrGrpPageAuthGridColList','화면그리드컬럼권한설정','1) 화면 그리드 컬럼에 대해서 조회제한, 수정제한 설정 목록화면', 'N' ,'Y','SL','WEB','PAGE','mgrUsrGrpPageAuthGridColList','AQ125','DREAM','2.01','PAGE','mgrUsrGrpPageAuthGridColList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
 insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'mgrUsrGrpPageAuthGridColDetail','화면그리드컬럼권한설정','1) 화면 그리드 컬럼에 대해서 조회제한, 수정제한 설정 상세화면', 'N' ,'Y','SD','WEB','PAGE','mgrUsrGrpPageAuthGridColDetail','AQ125','DREAM','2.01','PAGE','mgrUsrGrpPageAuthGridColDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 

 /** youngjoo38_698 + 김영주 */

 insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'mgrContractList','단가계약설정','단가계약설정목록', 'N' ,'Y','ML','WEB','PAGE','mgrContractList','AR122','DREAM','2.01','PAGE','mgrContractList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
 insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'mgrContractDetail','단가계약설정','단가계약설정상세', 'N' ,'Y','MD','WEB','PAGE','mgrContractDetail','AR122','DREAM','2.01','PAGE','mgrContractDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
 insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'mgrContractItemList','계약Item','단가계약Item목록', 'N' ,'Y','ML','WEB','PAGE','mgrContractItemList','AR122','DREAM','2.01','PAGE','mgrContractItemList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
 insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'mgrContractItemDetail','계약Item','단가계약Item상세', 'N' ,'Y','MD','WEB','PAGE','mgrContractItemDetail','AR122','DREAM','2.01','PAGE','mgrContractItemDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
 insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'lovMgrContractList','단가계약설정','단가계약설정선택', 'N' ,'N','LV','WEB','PAGE','lovMgrContractList','AR122','DREAM','2.01','PAGE','lovMgrContractList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 

 insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'workServicePriceList','서비스단가','서비스단가', 'N' ,'Y','ML','WEB','PAGE','workServicePriceList','AR111','DREAM','2.01','PAGE','workServicePriceList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
 insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'workServicePriceDetail','서비스단가','서비스단가', 'N' ,'Y','MD','WEB','PAGE','workServicePriceDetail','AR111','DREAM','2.01','PAGE','workServicePriceDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
 insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'lovWorkServiceList','서비스마스터','서비스마스터선택', 'N' ,'N','LV','WEB','PAGE','lovWorkServiceList','AR111','DREAM','2.01','PAGE','lovWorkServiceList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 

 /** 758 이근환 */
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
values(next value for sqapage_id, 'lovMgrRptCpList','출력물 선택','출력물 선택', 'N' ,'N','LV','WEB','PAGE','lovMgrRptCpList','AR118','DREAM','2.01','PAGE','lovMgrRptCpList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 

/** 2019-12-13 한국내화 반영 */
/** 2019-12-20 고려용접봉 반영 */

/** 447 + 이지수 */
 insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) 
 values(next value for sqapage_id, 'orgRptCraftWorkTimeTop','작업자 작업시간 Top','작업자 작업시간 Top', 'N' ,'Y','ML','WEB','PAGE','orgRptCraftWorkTimeTop','APH14','DREAM','2.01','PAGE','orgRptCraftWorkTimeTop',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 

 insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'orgRptCraftWorkTimeMonthly','작업자 월별 작업시간','작업자 월별 작업시간', 'Y' ,'Y','SL','WEB','PAGE','orgRptCraftWorkTimeMonthly','APH14','DREAM','2.01','PAGE','orgRptCraftWorkTimeMonthly',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
 insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'orgRptCraftWorkTimeDaily','작업자 일별 작업시간','작업자 일별 작업시간', 'Y' ,'Y','SL','WEB','PAGE','orgRptCraftWorkTimeDaily','APH14','DREAM','2.01','PAGE','orgRptCraftWorkTimeDaily',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 

 /*724 김남현*/
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'workListEqChangeMstrDetail','설비변경(상태,이동)작업','설비변경(상태,이동)작업', 'N' ,'Y','SD','WEB','PAGE','workListEqChangeMstrDetail','AD130','DREAM','2.01','PAGE','workListEqChangeMstrDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 

insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'maWoResultCraftList','수리작업실행작업자','수리작업실행작업자', 'N' ,'Y','SL','WEB','PAGE','maWoResultCraftList','AD130','DREAM','2.00','PAGE','maWoResultCraftList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'maWoResultCraftDetail','수리작업실행작업자','수리작업실행작업자', 'N' ,'Y','SD','WEB','PAGE','maWoResultCraftDetail','AD130','DREAM','2.00','PAGE','maWoResultCraftDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'maWoResultPartList','수리작업실행투입부품','수리작업실행투입부품', 'N' ,'Y','SL','WEB','PAGE','maWoResultPartList','AD130','DREAM','2.00','PAGE','maWoResultPartList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'maWoResultPartDetail','수리작업실행투입부품','수리작업실행투입부품', 'N' ,'Y','SD','WEB','PAGE','maWoResultPartDetail','AD130','DREAM','2.00','PAGE','maWoResultPartDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'maWoResultToolList','수리작업실행투입공기구','수리작업실행투입공기구', 'N' ,'Y','SL','WEB','PAGE','maWoResultToolList','AD130','DREAM','2.00','PAGE','maWoResultToolList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'maWoResultToolDetail','수리작업실행투입공기구','수리작업실행투입공기구', 'N' ,'Y','SD','WEB','PAGE','maWoResultToolDetail','AD130','DREAM','2.00','PAGE','maWoResultToolDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'maWoResultWoImageList','수리작업실행작업사진','수리작업실행작업사진', 'N' ,'Y','SL','WEB','PAGE','maWoResultWoImageList','AD130','DREAM','2.00','PAGE','maWoResultWoImageList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'maWoResultPointList','수리작업실행점검항목','수리작업실행점검항목목록', 'N' ,'Y','SL','WEB','PAGE','maWoResultPointList','AD130','DREAM','2.00','PAGE','maWoResultPointList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'maWoResultPointDetail','수리작업실행점검항목','수리작업실행점검항목상세', 'N' ,'Y','SD','WEB','PAGE','maWoResultPointDetail','AD130','DREAM','2.00','PAGE','maWoResultPointDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'maWoResultStPointList','수리작업실행완료확인항목','수리작업실행완료확인항목목록', 'N' ,'Y','SL','WEB','PAGE','maWoResultStPointList','AD130','DREAM','2.00','PAGE','maWoResultStPointList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'maWoResultStPointDetail','수리작업실행완료확인항목','수리작업실행완료확인항목상세', 'N' ,'Y','SD','WEB','PAGE','maWoResultStPointDetail','AD130','DREAM','2.00','PAGE','maWoResultStPointDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 


/** 2020-01-03 azdream - AZURE CLOUD DREAM2 반영 */
/** 2020-01-09 고려용접봉 반영*/
/** 2020-01-09 매일유업 반영 */

/*985 김남현*/
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'maWoResultBmRplMstrDetail','수리작업실행(고장)','수리작업실행(고장)상세', 'Y' ,'Y','SD','WEB','PAGE','maWoResultBmRplMstrDetail','AD130','DREAM','2.00','PAGE','maWoResultBmRplMstrDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'maWoResultCmRplMstrDetail','수리작업실행(개선)','수리작업실행(개선)상세', 'Y' ,'Y','SD','WEB','PAGE','maWoResultCmRplMstrDetail','AD130','DREAM','2.00','PAGE','maWoResultCmRplMstrDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'maWoResultPmRplMstrDetail','수리작업실행(예방)','수리작업실행(예방)상세', 'Y' ,'Y','SD','WEB','PAGE','maWoResultPmRplMstrDetail','AD130','DREAM','2.00','PAGE','maWoResultPmRplMstrDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'maWoResultPmOilMstrDetail','수리작업실행(윤활,청소)','수리작업실행(윤활,청소)상세', 'Y' ,'Y','SD','WEB','PAGE','maWoResultPmOilMstrDetail','AD130','DEPRECATED','1.00','PAGE','maWoResultPmOilMstrDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'workListEqChangeMstrDetail','설비변경(상태,이동)작업','설비변경(상태,이동)작업', 'Y' ,'Y','SD','WEB','PAGE','workListEqChangeMstrDetail','AD130','DREAM','2.01','PAGE','workListEqChangeMstrDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'maWoResultTrEleMstrDetail','수리작업실행(교육)','수리작업실행(교육)', 'Y' ,'Y','SD','WEB','PAGE','maWoResultTrEleMstrDetail','AD130','DEPRECATED','1.00','PAGE','maWoResultTrEleMstrDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'maWoResultCraftList','수리작업실행작업자','수리작업실행작업자', 'Y' ,'Y','SL','WEB','PAGE','maWoResultCraftList','AD130','DREAM','2.00','PAGE','maWoResultCraftList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'maWoResultCraftDetail','수리작업실행작업자','수리작업실행작업자', 'Y' ,'Y','SD','WEB','PAGE','maWoResultCraftDetail','AD130','DREAM','2.00','PAGE','maWoResultCraftDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'maWoResultPartList','수리작업실행투입부품','수리작업실행투입부품', 'Y' ,'Y','SL','WEB','PAGE','maWoResultPartList','AD130','DREAM','2.00','PAGE','maWoResultPartList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'maWoResultPartDetail','수리작업실행투입부품','수리작업실행투입부품', 'Y' ,'Y','SD','WEB','PAGE','maWoResultPartDetail','AD130','DREAM','2.00','PAGE','maWoResultPartDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'maWoResultToolList','수리작업실행투입공기구','수리작업실행투입공기구', 'Y' ,'Y','SL','WEB','PAGE','maWoResultToolList','AD130','DREAM','2.00','PAGE','maWoResultToolList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'maWoResultToolDetail','수리작업실행투입공기구','수리작업실행투입공기구', 'Y' ,'Y','SD','WEB','PAGE','maWoResultToolDetail','AD130','DREAM','2.00','PAGE','maWoResultToolDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'maWoResultWoImageList','수리작업실행작업사진','수리작업실행작업사진', 'Y' ,'Y','SL','WEB','PAGE','maWoResultWoImageList','AD130','DREAM','2.00','PAGE','maWoResultWoImageList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'maWoResultPointList','수리작업실행점검항목','수리작업실행점검항목목록', 'Y' ,'Y','SL','WEB','PAGE','maWoResultPointList','AD130','DREAM','2.00','PAGE','maWoResultPointList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'maWoResultPointDetail','수리작업실행점검항목','수리작업실행점검항목상세', 'Y' ,'Y','SD','WEB','PAGE','maWoResultPointDetail','AD130','DREAM','2.00','PAGE','maWoResultPointDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 

update tapage set description='수리작업실행(예방)',remark='수리작업실행(예방)상세',is_use='Y',is_chkauth='Y',page_type='SD',service_type='WEB',key_type='PAGE',key_no='maWoResultPmRplMstrDetail',menu_no='AD130',site_type='DREAM',version_info='2.00',basic_key_type='PAGE',basic_key_no='maWoResultPmRplMstrDetail',upd_date=CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','') where file_name =  'maWoResultPmRplMstrDetail' ; 

/** 2020-01-14 한국내화 kref 반영 */

/*796 김남현*/
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'woPlanServiceList','서비스작업','서비스작업', 'N' ,'Y','SL','WEB','PAGE','woPlanServiceList','AD120','DREAM','2.01','PAGE','woPlanServiceList',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 
insert into tapage(page_id, file_name, description, remark, is_use, is_chkauth, page_type, service_type, key_type, key_no, menu_no, site_type, version_info, basic_key_type, basic_key_no, cre_date, upd_date) values(next value for sqapage_id, 'woPlanServiceDetail','서비스작업','서비스작업', 'N' ,'Y','SD','WEB','PAGE','woPlanServiceDetail','AD120','DREAM','2.01','PAGE','woPlanServiceDetail',CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':',''),CONVERT(CHAR(8),GETDATE(),112)+REPLACE(CONVERT(CHAR(8),GETDATE(),108),':','')); 

/** 2020-01-17 매일유업 반영 */

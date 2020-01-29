## Dream Bee  주요 변경사항
---   
<br>
   

### VersionName: 2.104 / VersionCode: 29
---   
- **PUBLIC 적용일자**   
	- 2019년 07월 18일
- **주요 변경사항**   
	- 모든 사진 탭에 동영상 촬영 가능하도록 버튼 추가   
		- TACONFIG 의 제한용량 적용   
		- 낮은 화질로 변경 후 촬영 시작하도록 적용   
	- 연결된 API 주소 변경 ( 기존 anOn으로 시작하는 API 명을 bee로 시작하는 API로 변경 )   
		- 파일 경로 : JavaSource\intf\dream\bee   
- **특이사항**    
	- bee의 표준 자바 파일이 변경되었으므로  enhance로 뺀 것이 있다면 수정해줘야 한다.   
		- 예시)   
			1. HdeleAnOnAssetListDAOOraImpl.java 의 경우 변경된 표준파일인 BeeAssetListDAOOraImpl 에 맞게 enhance적용   
			2. TACOMPFUNC 데이터 변경
   

### VersionName: 2.105 / VersionCode: 30
---   
- **PUBLIC 적용일자**   
	- 2019년 8월 16일
- **주요 변경사항**   
	- 설비목록에 페이징 추가   
	- 검색 Dialog 검색어 입력칸 아래 구분선 추가    
	- 리스트의 구분선 통일   
	- 비고 스타일 입력창의 내용이 길어지면 높이 자동조정   
- **특이사항**    
	- 없음.   
   

### VersionName: 2.106 / VersionCode: 31
---   
- **PUBLIC 적용일자**   
	- 2019년 09월 03일
- **주요 변경사항**   
	- 수리요청 등록시 CHANNEL값 적용    
	- 팝업 데이터 로딩 중 검색할 수 없도록 변경   
	- 점검- 작업이력 쿼리 에러 수정 ( 조건 : equip_id -> item_no )   
	- 메인화면 count 쿼리 수정 -> 각 화면의 리스트에서 count 함.   
- **특이사항**    
	- 없음.   
   

### VersionName: 2.107 / VersionCode: 32
---   
- **PUBLIC 적용일자**   
	- 2019년 09월 10일
- **주요 변경사항**   
	- 점검, 설비 -> 작업이력 클릭 시 에러 수정   
	- 요청 접수 처리담당자 없을 경우 저장 할 때 로그인 담당자와 로그인담당자의 부서를 입력   
	- MSSQL 사진 부분 에러 나는 곳 수정
- **특이사항**    
	- 없음.   

### VersionName: 2.108 / VersionCode: 33
---   
- **PUBLIC 적용일자**   
	- 2019년 09월 30일
- **주요 변경사항**   
	- 코스트센터 다이얼로그 추가   
	- 매일유업 부품 바코드 출고 추가   
- **특이사항**    
	- 없음.   

### VersionName: 2.109 / VersionCode: 34
---   
- **PUBLIC 적용일자**   
	- 2019년 10월 08일
- **주요 변경사항**   
	- 매일유업 부품 바코드 출고  - 부외자재 재고이동 필드 추가   
- **특이사항**    
	- 없음.   

### VersionName: 2.110 / VersionCode: 35
---   
- **PUBLIC 적용일자**   
	- 2019년 10월 10일
- **주요 변경사항**   
	- LINKEDFUNC 아이디 표준화   
- **특이사항**    
	- 없음.   

### VersionName: 2.111 / VersionCode: 36
---   
- **PUBLIC 적용일자**   
	- 2019년 10월 14일
- **주요 변경사항**   
	- 창고 Dialog 에 WH_TYPE 파라미터 추가   
	- 매일유업 관련사항 수정 ( 출고창고, 부외자재 출고창고, 보관창고 기본값 세팅 변경 )   
- **특이사항**    
	- 없음.   

### VersionName: 2.112 / VersionCode: 37
---   
- **PUBLIC 적용일자**   
	- 2019년 11월 12일
- **주요 변경사항**   
	- Login시 유저정보 가져오는 Model 1개로 통일   
	- Login 비밀번호 암호화하여 전송    
	- Audit Trail 테스트 코드 넣음 ( 설비 상세 )
	- setLabel 필요 없는 부분 삭제
	- Field 값 지우기 버튼 추가
	
- **특이사항**    
	- 서버업데이트되면 apk무조건 변경되어야함.   

### VersionName: 2.113 / VersionCode: 38
---   
- **PUBLIC 적용일자**   
	- 2019년 11월 15일
- **주요 변경사항**   
	- 28버전( Pie ) 부터 https 통신만 허용함 , http 통신도 허용하는 코드 추가 
	
- **특이사항**    
   
   
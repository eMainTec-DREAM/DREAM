## Dream Ant  주요 변경사항
---   
<br>
   

### VersionName: 2.103 / VersionCode: 28
---   
- **PUBLIC 적용일자**   
	- 2019년 07월 18일
- **주요 변경사항**   
	- 연결된 API 주소 변경 ( 기존 anIf으로 시작하는 API 명을 ant로 시작하는 API로 변경 )   
		- 파일 경로 : JavaSource\intf\dream\ant   
- **특이사항**    
	- ant의 표준 자바 파일이 변경되었으므로  enhance로 뺀 것이 있다면 수정해줘야 한다.   
		- 예시)   
			1. HdeleAnIfAssetListDAOOraImpl.java 의 경우 변경된 표준파일인 AntAssetListDAOOraImpl 에 맞게 enhance적용   
			2. TACOMPFUNC 데이터 변경
***

### VersionName: 2.104 / VersionCode: 29
---   
- **PUBLIC 적용일자**   
	- 2019년 8월 16일
- **주요 변경사항**   
	- 검색 Dialog 검색어 입력칸 아래 구분선 추가    
	- 리스트의 구분선 통일   
	- 비고 스타일 입력창의 내용이 길어지면 높이 자동조정   
- **특이사항**    
	- 없음   
   

### VersionName: 2.105 / VersionCode: 30
---   
- **PUBLIC 적용일자**   
	- 2019년 09월 03일
- **주요 변경사항**   
	- 잡업요청 등록 시 CHANNEL 값 적용    
	- 팝업 데이터 로딩 중 검색할 수 없도록 변경   
	- 계획작업 리스트, 상세에 위치 추가   
	- 메인화면 count 쿼리 수정 -> 각 화면의 리스트에서 count 함.   
- **특이사항**    
	- 없음   
   

### VersionName: 2.106 / VersionCode: 31
---   
- **PUBLIC 적용일자**   
	- 2019년 09월 10일
- **주요 변경사항**   
	- 요청 접수 처리담당자 없을 경우 저장 할 때 로그인 담당자와 로그인담당자의 부서를 입력   
	- 요청접수 설비 선택시 계측기만 나오는 에러 수정    
- **특이사항**    
	- 없음.   
   

### VersionName: 2.107 / VersionCode: 32
---   
- **PUBLIC 적용일자**   
	- 2019년 09월 18일
- **주요 변경사항**   
	- App 업데이트 시 apk 파일 다운로드 완료 후 설치페이지가 자동으로 열리지 않는 문제 해결   
- **특이사항**    
	- 기존 버전에서는 1번은 다운로드된 apk를 눌러서 설치해야함.   
   

### VersionName: 2.108 / VersionCode: 33
---   
- **PUBLIC 적용일자**   
	- 2019년 10월 10일
- **주요 변경사항**   
	- LINKEDFUNC 아이디 표준화   
- **특이사항**    
	-    

### VersionName: 2.110 / VersionCode: 35
---   
- **PUBLIC 적용일자**   
	- 2019년 11월 11일
- **주요 변경사항**   
	- Field 값 지우기 버튼 추가
	- 기타 버그 수정
- **특이사항**    
	-    

### VersionName: 2.111 / VersionCode: 36
---   
- **PUBLIC 적용일자**   
	- 2019년 11월 15일
- **주요 변경사항**   
	- 28버전( Pie ) 부터 https 통신만 허용함 , http 통신도 허용하는 코드 추가 
	
- **특이사항**    
   
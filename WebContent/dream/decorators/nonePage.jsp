<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html>
<head>
<title><decorator:title/></title>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link rel="stylesheet" href="<c:url value="/common/css/bg_style.css" />" type="text/css" />
<c:import charEncoding="UTF-8" url="/common/jsp/commonInclude.jsp"></c:import>
<decorator:head />
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<decorator:body />
<!-- page 하단 공통 페이지 -->
<c:import charEncoding="UTF-8"  url="/common/jsp/bottomInclude.jsp"></c:import>
<script>
// body 의 onload 에 넣었다가 IE 8 에서 body 가 모두 로딩되기 전에 호출이 되어서 제일 뒤에 loadpage를 호출하게 수정하였음
loadPage();
</script>
</body>
</html>

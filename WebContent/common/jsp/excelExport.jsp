<%--===========================================================================
author  노정현
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>

<html>
<HEAD>
<TITLE>엑셀 다운로드</TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<style type="text/css">
.loader {
  margin-left:25%;
  border: 16px solid #f3f3f3;
  border-radius: 50%;
  border-top: 16px solid #3498db;
  width: 120px;
  height: 120px;
  -webkit-animation: spin 2s linear infinite; /* Safari */
  animation: spin 2s linear infinite;
}

/* Safari */
@-webkit-keyframes spin {
  0% { -webkit-transform: rotate(0deg); }
  100% { -webkit-transform: rotate(360deg); }
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}
</style>
<script type="text/javascript">

function loadPage()
{
	var FILEDOWNLOAD_INTERVAL = setInterval(function() {
		var cookies = document.cookie;
	  	var cookieArr = cookies.split(";");
	 // 	console.log(cookies);
	  	for(var key in cookieArr)
	  	{
	  		var cArr = cookieArr[key].split("=");
	  		if(cArr[0].trim() == "fileDownloadToken_"+opener.currentPageId && cArr[1] == "TRUE")
	  		{
	  			var _loader = $('.loader');
	  			_loader.removeClass('loader');
	  			_loader.text("파일생성이 완료되었습니다.");
	  			clearInterval(FILEDOWNLOAD_INTERVAL);
	  		}
	  	}
		
	  }, 500);
}

$( document ).ready(function() {
	opener.excelServerExe();
});

</script>
</HEAD>
<BODY>
	<div class="loader"></div>
</BODY>
</html>
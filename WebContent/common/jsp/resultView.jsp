<%--===========================================================================
단순 메세지 보여 줄때 호출된다.
서비스 호출후 결과 만 보여주고 닫힐때 사용
author  javaworker
version $Id: resultView.jsp,v 1.1 2013/08/30 09:09:09 javaworker Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%
	// 화면에 보여줄 메세지 처리한다.
	String message = (String)request.getAttribute("RESULT_MSG");
%>
<HTML>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title><bean:message key="resultView.title"/></title>
<script language='javascript'>

var popWidth = 500;
var popHeight = 400;

// pop up이 중앙에 위치하게 한다.
var TopPosition  = (screen.height/2 - popHeight/2);
var LeftPosition = (screen.width/2 - popWidth/2);

// 중앙에 위치하게 이동
self.moveTo(LeftPosition, TopPosition);

// 사이즈를 맞게 조종한다. width, height
self.resizeTo(popWidth, popHeight);

	
/**
 * 확인 버튼을 누른경우
 */	
function goConfirm()
{
	//alert('<%=message%>');

	// 메세지를 화면에 보여주고 현재 페이지를 종료한다.
	// 종료시 해당 페이지는 메인 페이지 이므로 윈도우 클로우즈시 경고 창이 뜨므로 처리하지 않는다.
	//self.close();
}
</script>
</head>
<body>
<table border="0" cellspacing="5" cellpadding="0">
	<tr><td>
		<FONT color="black" size="5"><%=message%></FONT>
	</td></tr>
</table>
</body>
</html>
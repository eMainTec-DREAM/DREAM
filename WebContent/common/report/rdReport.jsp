<%--===========================================================================
report designer common
author  javaworker
version $Id: report.jsp,v 1.1 2014/03/08 14:23:34 hankyul Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ page import="common.bean.MwareConfig"%>
<%
	String commonContextPath = request.getContextPath();
    String rdExtPath = MwareConfig.getServerDatabase();
%>
<html>
<head>
<title>Report Designer</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 공통 페이지 -->
<c:import charEncoding="UTF-8" url="/common/jsp/commonInclude.jsp"></c:import>
</head>
<body onload="loadPage();">
<script>
var contextPath = "<%=commonContextPath%>";
var rdExtPath = "<%=rdExtPath%>";

/**
 * 페이지를 로딩한다.
 */
function loadPage()
{
	if(rdExtPath !="") rdExtPath = "/"+rdExtPath; 
	var serverUrl = "http://" + location.hostname + ":" +  location.port + contextPath;

	var resultFileName = "/rf [" + serverUrl + "/dream/print/rd/temp/" + reportForm.resultFileName.value + "]";
	var openFileName   = serverUrl+reportForm.reportName.value;

	var param = " /rp";

	
	//Rdviewer.HideToolBar();   // ToolBar 숨김
	//Rdviewer.HidePopupMenu(4);  //저장 메뉴 숨김
	//Rdviewer.HidePopupMenu(9);   // 일부 저장메뉴 숨김 

	Rdviewer.FileOpen(openFileName, resultFileName + param + '/rcodepage [utf-8]'); 
	
}

</script>
<html:form action="report">
	<html:hidden property="reportName"/>
	<html:hidden property="reportPath"/>
	<html:hidden property="resultFileName"/>
	
</html:form>
<script>loadReportActiveX();</script>
<!--object
   id=TChart
   name=TChart style="display:none"
   classid="CLSID:FAB9B41C-87D6-474D-AB7E-F07D78F2422E"
   codebase="<%=request.getContextPath()%>/common/report/teechart7.cab#version=7,0,1,4">
</object-->
</body>
</html>
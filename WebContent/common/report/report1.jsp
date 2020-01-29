<%--===========================================================================
report designer common
author  javaworker
version $Id: report1.jsp,v 1.2 2014/05/12 05:37:29 cjk Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%
	String commonContextPath = request.getContextPath();
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

/**
 * 페이지를 로딩한다.
 */
function loadPage()
{
	var serverUrl = "http://" + location.hostname + ":" +  location.port + contextPath;

	var resultFileName = "/rf [" + serverUrl + "/rd/temp/" + reportForm.resultFileName.value + "]";
	var openFileName   =  serverUrl + "/rd/" + reportForm.reportName.value + ".mrd";
	var param = " /rp" + " []";
	//Rdviewer.FileOpen(openFileName, resultFileName + param);
	Rdviewer.FileOpen(openFileName, resultFileName+param + '/rcodepage [utf-8]');
}

</script>
<html:form action="report">
<input type="hidden" id="reportName" name="reportName" value="<%=request.getAttribute("_reportName")%>"/>
<input type="hidden" id="resultFileName" name="resultFileName" value="<%=request.getAttribute("_resultFileName")%>"/>
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
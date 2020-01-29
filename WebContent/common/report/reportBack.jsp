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
	alert(serverUrl);
	alert(resultFileName);
	alert(openFileName);
	var param = " /rp" + " [" + reportForm.param1.value + "]" +
	                   + " [" + reportForm.param2.value + "]" +
	                   + " [" + reportForm.param3.value + "]" +
	                   + " [" + reportForm.param4.value + "]" +
	                   + " [" + reportForm.param5.value + "]" +
	                   + " [" + reportForm.param6.value + "]" +
	                   + " [" + reportForm.param7.value + "]" +
	                   + " [" + reportForm.param8.value + "]" +
	                   + " [" + reportForm.param9.value + "]" +
	                   + " [" + reportForm.paramA.value + "]" +
	                   + " [" + reportForm.paramB.value + "]" +
	                   + " [" + reportForm.paramC.value + "]" +
	                   + " [" + reportForm.paramD.value + "]" +
	                   + " [" + reportForm.paramE.value + "]" +
	                   + " [" + reportForm.paramF.value + "]" +
	                   + " [" + reportForm.paramG.value + "]";
	
	//Rdviewer.FileOpen(openFileName, resultFileName + param);
	Rdviewer.FileOpen(openFileName, resultFileName + param + '/rcodepage [utf-8]');
	
}

</script>
<html:form action="report">
	<html:hidden property="reportName"/>
	<html:hidden property="resultFileName"/>
	<html:hidden property="param1"/>
	<html:hidden property="param2"/>
	<html:hidden property="param3"/>
	<html:hidden property="param4"/>
	<html:hidden property="param5"/>
	<html:hidden property="param6"/>
	<html:hidden property="param7"/>
	<html:hidden property="param8"/>
	<html:hidden property="param9"/>
	<html:hidden property="paramA"/>
	<html:hidden property="paramB"/>
	<html:hidden property="paramC"/>
	<html:hidden property="paramD"/>
	<html:hidden property="paramE"/>
	<html:hidden property="paramF"/>
	<html:hidden property="paramG"/>
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
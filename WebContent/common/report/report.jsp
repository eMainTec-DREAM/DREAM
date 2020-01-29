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

	var resultFileName = "/rf [" + serverUrl + "/dream/rd"+ rdExtPath + "/temp/" + reportForm.resultFileName.value + "]";
	var openFileName   = serverUrl+"/"+reportForm.reportPath.value + "/" + reportForm.reportName.value + ".mrd";

	var param = " /rp" + " [" + reportForm.param1.value + "]" +
	                   + " [" + reportForm.param2.value + "]" +
	                   + " [" + reportForm.param3.value + "]" +
	                   + " [" + reportForm.param4.value + "]" +
	                   + " [" + reportForm.param5.value + "]" +
	                   + " [" + reportForm.param6.value + "]" +
	                   + " [" + reportForm.param7.value + "]" +
	                   + " [" + reportForm.param8.value + "]" +
	                   + " [" + reportForm.param9.value + "]" +
	                   + " [" + reportForm.param10.value + "]" +
	                   + " [" + reportForm.param11.value + "]" +
	                   + " [" + reportForm.param12.value + "]" +
	                   + " [" + reportForm.param13.value + "]" +
	                   + " [" + reportForm.param14.value + "]" +
	                   + " [" + reportForm.param15.value + "]";

	
	//Rdviewer.HideToolBar();   // ToolBar 숨김
	//Rdviewer.HidePopupMenu(4);  //저장 메뉴 숨김
	//Rdviewer.HidePopupMenu(9);   // 일부 저장메뉴 숨김 

	//Rdviewer.FileOpen(openFileName, resultFileName + param);
	Rdviewer.FileOpen(openFileName, resultFileName + param + '/rcodepage [utf-8]'); 
	
}

</script>
<html:form action="report">
	<html:hidden property="reportName"/>
	<html:hidden property="reportPath"/>
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
	<html:hidden property="param10"/>
	<html:hidden property="param11"/>
	<html:hidden property="param12"/>
	<html:hidden property="param13"/>
	<html:hidden property="param14"/>
	<html:hidden property="param15"/>
	
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
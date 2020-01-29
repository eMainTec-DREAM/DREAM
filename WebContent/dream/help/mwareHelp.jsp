<%--===========================================================================
author  javaworker
version $Id: mwareHelp.jsp,v 1.4 2014/04/18 06:45:12 jung7126 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%
	String commonContextPath = request.getContextPath();
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=7" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<meta name="author" content="" />
	<title>Mware Help Doc</TITLE>
	<link rel="stylesheet" type="text/css" href="common/css/layout.css" />
<script language="javascript">
/**
 *
 **/
var helpPmenuId		=  '<%=(String)request.getParameter("helpPmenuId")%>';
var functionPageId	=  '<%=(String)request.getParameter("functionPageId")%>';
 
</script>
</head>

<frameset rows="7%,*" frameborder="1" border="1" framespacing="1">  
    <frame name="top_frame" src="<%=commonContextPath%>/help/help_top.jsp" noresize frameborder="1" scrolling="NO" marginwidth="0" marginheight="0">
	<frameset cols="20%,80%" frameborder="1" border="1" framespacing="1"> 
		<frame name="menu" src="<%=commonContextPath%>/help/helpMenu.jsp" scrolling="AUTO" noresize  frameborder="0" marginwidth="0" marginheight="0">
		<frame name="helpDoc" src=""  scrolling="AUTO" frameborder="0" noresize marginwidth="49" marginheight="0" >
	</frameset>
</frameset>
</html>
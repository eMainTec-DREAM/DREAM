<%--===========================================================================
author  유영근
version $Id: error404.jsp,v 1.1 2013/08/30 09:09:08 javaworker Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<html>
<HEAD>
<TITLE></TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<style type="text/css">

body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	width: 100%;
	height: 100%;
}
img { 
	border:none; 
}



#container { 
	width: 500px; 
	height: 250px; 
	position: absolute; 
	left: 50%; 
	top: 45%; 
	margin-left: -250px; 
	margin-top: -150px; 
}

</style>
</HEAD>
<BODY>
<div id="container">
<img src="<c:url value="/common/images/main/error.jpg"/>"/>
</BODY>
</html>
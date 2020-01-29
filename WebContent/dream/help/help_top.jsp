<%--===========================================================================
author  javaworker
version $Id: help_top.jsp,v 1.4 2014/04/22 07:34:01 jung7126 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=7" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<meta name="author" content="" />
	<link rel="stylesheet" href="<c:url value="/common/css/help.css" />" type="text/css">
</head>
<body class="bg">
<div id="popup_wrap">

	<!-- HEADER -->
	<div id="popup_header">
		<div class="popup_gnb">
			<h1><img src="<c:url value="/common/images/help/logo_help.gif"/>" alt="HELP" /></h1>
			<h1 style="float: right; position: static; padding-bottom: 20px; padding-right: 20px;">
				<img src="<c:url value="/common/images/help/logo.png"/>" alt="eMainTec" />
		    </h1>
		</div>
	</div>
	<div class="popup_bar"></div>
	<!-- //HEADER -->
</div>
</body>
</html>
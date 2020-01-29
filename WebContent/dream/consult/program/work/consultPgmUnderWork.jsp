<%--===========================================================================
프로그램 개발중 
author  euna0207
version $Id: consultPgmUnderWork.jsp,v 1.5 2014/07/02 04:13:54 euna0207 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.consult.program.work.action.ConsultPgmUnderWorkAction" %>
<html:html>
<head>
<title></title>
<style>
	img {display: block; margin: 0 auto;}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/consultPgmUnderWork" >

    <!-- searchbox 박스 Line -->
    <div class="section_wrap">
    	<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
			</div>
			<div class="function_box filter">
				<div class="fb_group3">
					</div>
				</div>	
			</div>
		</div><!--sheader_box end-->
		
	<img src="common\\images\\underWork.png">
	
</html:form>
</body>
</html:html>
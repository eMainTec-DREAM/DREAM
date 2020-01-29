<%--===========================================================================
author  pochul2423
version $Id: topInclude.jsp,v 1.5 2014/07/03 06:01:10 pochul2423 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@page import="common.bean.User"%>
<%@page import="common.bean.MwareConfig"%>
<%@page import="common.util.DateUtil"%>
<%
    User menuLoginUser = (User)request.getSession().getAttribute(request.getSession().getId()); 
    String userName = "";
    if(menuLoginUser != null)
     userName = menuLoginUser.getUserName();
    
    String deptDesc = "";
    if(menuLoginUser != null)
    	deptDesc = menuLoginUser.getDeptDesc();

    // 현재 페이지의 Menu 경로를 표시한다.
    String [] menuPath = (String [])request.getAttribute("menuPath");
    String yyyymmdd = DateUtil.getDateTime("yyyy-MM-dd");
    String day = DateUtil.getDayOfWeekLangStr(yyyymmdd.replaceAll("-",""),menuLoginUser.getLangId());
    String tDate = yyyymmdd+"["+day+"]";
	
%>
<div class="logo_box"><a href="javascript:goMenuPage('main');"><h1><p><bean:message key='LABEL.cmms'/></p></h1></a></div>
<div class="tmenu_box">
	<!-- <span class="t_date"><%=tDate%></span>  -->
	<b class="t_name"><%=userName%></b> [<%=deptDesc%>] <bean:message key='LABEL.topMenuMsg01'/>
	<span>ㅣ</span><a href="javascript:goLogOut('<%=dream.login.login.action.LoginAction.LOGOUT%>');"><b><bean:message key='LABEL.logout'/></b></a>
	<span>ㅣ</span><a href="javascript:goDashboard();"><bean:message key='LABEL.notice'/></a>
</div>

<script>

	//Main Log Image가 등록되어 있으면 사용 없으면 기본 Main Log 사용 
	$("<img>").prop("src",'common/images/client/main_logo/main_logo.png').load(function(){
		$('.logo_box').find("h1").css("background-image",'url(common/images/client/main_logo/main_logo.png)');
	});

</script>
<%--===========================================================================
author  pochul2423
version $Id: topInclude.jsp,v 1.5 2014/07/03 06:01:10 pochul2423 Exp $
since   1.0
===========================================================================--%>
<%@page import="gaia.gaia.action.GaiaAction"%>
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
<!-- 기계이력관리시스템 -->
<div class="logo_box"><a href="javascript:goMenuPage('gaia');"><h1><p><bean:message key='LABEL.cmms' /></p></h1></a></div>
<div class="tmenu_box">
	<!-- <span class="t_date"><%=tDate%></span>  -->
	<b class="t_name"><%=userName%></b> [<%=deptDesc%>] <bean:message key='LABEL.topMenuMsg01'/>
	<span>ㅣ</span><a href="javascript:goGaiaLogOut('<%=GaiaAction.LOGOUT%>');"><b><bean:message key='LABEL.logout'/></b></a>
</div>
<%--===========================================================================
author  ssong
version $Id: adminTopInclude.jsp,v 1.1 2013/08/30 09:09:08 javaworker Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@page import="common.bean.User"%>
<%@page import="common.bean.MwareConfig"%>
<%
    User menuLoginUser = (User)request.getSession().getAttribute(request.getSession().getId()); 
    String menuLoginUserName = menuLoginUser.getUserName();
    
    // 현재 페이지의 Menu 경로를 표시한다.
    String [] menuPath = (String [])request.getAttribute("menuPath");
	
%>
<div class="logo_box"><a href="javascript:openQuickPage('isDecoratorName=defaultPage', 'main');"><h1><p>MWare</p></h1></a></div>
<div class="tmenu_box"><a href="javascript:openQuickPage('isDecoratorName=defaultPage', 'main');">Main(Popup)</a>
	<span>ㅣ</span><a href="javascript:goLogOut('<%=dream.login.login.action.LoginAction.LOGOUT%>');"><b><bean:message key='LABEL.logout'/></b></a>
	<span>ㅣ</span><a href="javascript:goMenuPage('main');">Home</a>
</div>
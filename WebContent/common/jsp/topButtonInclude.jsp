<%--===========================================================================
하단 버튼
author  javaworker
version $Id: topButtonInclude.jsp,v 1.1 2013/08/30 09:09:09 javaworker Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ page import="common.util.CommonUtil"%>
<%@page import="java.util.Hashtable"%>
<%@page import="common.bean.MwareConfig"%>
<%
    String currentPageId = (String)request.getAttribute("currentPageId");
	Hashtable buttonHash = MwareConfig.getButtonTable();
	String [] pageButtons = (String [])buttonHash.get(currentPageId+"_TOP");
    if (pageButtons == null || pageButtons.length == 0)
    {
        out.println("<div class='top_button_div'></div>");
    }
    else
    {
	    //out.println("<table width=\"100%\" border=\"0\"><tr><td align=\"right\">");
	    out.println("<div class='top_button_div'>");
		for (int i=0; i<pageButtons.length; i++)
		{
		    out.println(CommonUtil.getButtonTag(request, pageButtons[i], "top"));
		}
		out.println("</div>");
    }
%>

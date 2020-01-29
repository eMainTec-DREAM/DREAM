<%--===========================================================================
author  pochul2423
version $Id: tabPageInclude.jsp,v 1.3 2014/02/25 06:34:35 javaworker Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/c-rt.tld" prefix="c-rt"%>
<%@page import="java.util.Hashtable"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="common.bean.MwareConfig"%>
<%
ArrayList tabPageList = (ArrayList)request.getAttribute("tabPage");
//Object tabTitle = request.getAttribute("tabTitle");

String currentPageId = (String)request.getAttribute("currentPageId");
Object decoName = request.getAttribute(com.opensymphony.module.sitemesh.RequestConstants.DECORATOR);

if(tabPageList != null)

	if(decoName.equals("tabPage"))
	{

		for(Object tabs : tabPageList)
		{
		    Map tab = (Map)tabs;
	
		    String tabPageName 	= String.valueOf(tab.get("TABPAGENAME"));
		    String langKey 	    = String.valueOf(tab.get("LANGKEY"));
		    String tabDivName   = "tabDiv"+tabPageName;
		    String tabIfmName   = "tabFrame"+tabPageName;
    %>
    	<div class="hr"> </div>
		<div id="<%=tabDivName%>"  style="display: none;"> 
			<div id="<%=tabPageName%>_title" style="display: none;"><bean:message key="<%=langKey%>"/></div>
			<iframe name="<%=tabIfmName%>" id="<%=tabIfmName%>" src="" style="margin: 0px; padding: 0px; margin-left: 0px;" frameborder="0" height="100%" width="100%" scrolling="no" ></iframe> 
		</div>
    <%
		}
	}
	else 
	{
	    for(Object tabs : tabPageList)
		{
		    Map tab = (Map)tabs;
	
		    String tabPageName 	= String.valueOf(tab.get("TABPAGENAME"));
		    String langKey 	    = String.valueOf(tab.get("LANGKEY"));
		    String tabDivName   = "tabDiv"+tabPageName;
		    String tabIfmName   = "tabFrame"+tabPageName;
	%>
    	<div id="<%=tabDivName%>"  style="display: none;"> 
    		<div id="<%=tabPageName%>_title" style="display: none;"><bean:message key="<%=langKey%>"/></div>
			<iframe name="<%=tabIfmName%>" id="<%=tabIfmName%>" src="" style="margin: 0px; padding: 0px; margin-left: 0px;" frameborder="0" height="100%" width="100%" scrolling="no" ></iframe> 
		</div>
	<%   
		    
		}
	    %>
    	<div id="tabDivNoAuth"  style="display: none;"> 
			<iframe name="tabFrameNoAuth_<%=currentPageId%>" id="tabFrameNoAuth_<%=currentPageId%>" src="" style="margin: 0px; padding: 0px; margin-left: 0px;" frameborder="0" height="280px" width="100%" scrolling="no" ></iframe> 
		</div>
		<%
	}
%>
<input type='hidden' id="currentTabNum" value="<c:out value="${currentTabNum}" />" />

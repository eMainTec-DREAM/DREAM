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
<%@ page import="common.util.CommonUtil"%>
<%@page import="java.util.Hashtable"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="common.bean.MwareConfig"%>
<%
ArrayList tabPageList = (ArrayList)request.getAttribute("tabPage");
//Object tabTitle = request.getAttribute("tabTitle");

String currentPageId = (String)request.getAttribute("currentPageId");
Object decoName = request.getAttribute(com.opensymphony.module.sitemesh.RequestConstants.DECORATOR);
int cnt = 0;
if(tabPageList != null)
	if(decoName.equals("mobileTabPage"))
	{
	%>
	    <div class="list_wrap" >
    		<ul>
	<%
		for(Object tabs : tabPageList)
		{
		    Map tab = (Map)tabs;
		    String tabPageName 	= String.valueOf(tab.get("TABPAGENAME"));
		    String langKey 	    = String.valueOf(tab.get("LANGKEY"));
		    String isUse 	    = String.valueOf(tab.get("ISUSE"));
		    String tabDivName   = "tabDiv"+tabPageName;
		    String tabIfmName   = "tabFrame"+tabPageName;
			String tabPageId	= tabPageName.replaceAll("TAB.", "");
			String display 	    = "block";
			if(!"Y".equals(isUse)) display = "none";
    %>
    	<li class="go_detail" id="<%=tabPageId+"_tabList"%>" style="display:<%=display%>">
    		<a><bean:message key="<%=langKey%>"/> (<span></span>)</a>
		</li>
    <%
		}
	%>
	    	</ul>
   		</div>
	<%
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
    	<div id="<%=tabDivName%>"  style="display: none; top: 0px; position: absolute; padding: 0 15 15 15; left: 0px; right: 0px;"> 
    		
		</div>
	<%   
		    
		}

	}
%>
<input type='hidden' id="currentTabNum" value="<c:out value="${currentTabNum}" />" />
<script language="javascript">
if(!ckCreate(currentPageId)){
<%
if(tabPageList != null)
	if(decoName.equals("mobileTabPage"))
	{
		for(Object tabs : tabPageList)
		{
		    Map tab = (Map)tabs;
		    String tabPageName 	= String.valueOf(tab.get("TABPAGENAME"));
		    String tabIfmName   = "tabFrame"+tabPageName;
			String tabPageId	= tabPageName.replaceAll("TAB.", "");
			
			%>
			getMobileListCount('<%=tabPageId%>');
			<%
		}
	}
%>
//setTimeout("closeModal()", 100);
}

/**
 * Get List Count 
 */
function getMobileListCount(_pageId)
{
	var _url 			= contextPath + "/"+_pageId+".do";
	var _isLoadMaxCount = "N";

	var formObj = $('form').get(0);
	var oldStAct = formObj.strutsAction;
	
	formObj.strutsAction = BASE_QUICK_LIST;

	var paramFirst = FormQueryString(formObj) + "&firstRow="+"0"
		+ "&isLoadMaxCount="+ _isLoadMaxCount
		+ "&isGridSearch=true";
	
	$.post(_url,paramFirst, function(_data){
		//if(checkSession(_data))_list.parse(_data,"json");
		if(_data.indexOf("id") > 0)_data = _data.replace(/id:/g,'"id":'); 

		var jsonObj = JSON.parse(_data);  
		var modRow = jsonObj.data;
		var rowNum = modRow.length;
		
		$("#"+_pageId+"_tabList").find('span').text(rowNum);
		$("#"+_pageId+"_tabList").find('a').on("click",function(e){
			goCommonMobilePage(formObj, '', _pageId);
		}).css("cursor","pointer");

	});
	
	formObj.strutsAction = oldStAct;
}
</script>
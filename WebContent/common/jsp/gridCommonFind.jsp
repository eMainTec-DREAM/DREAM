<%--===========================================================================
Grid 조회 공통
author  javaworker
version $Id: gridCommonFind.jsp,v 1.1 2013/08/30 09:09:09 javaworker Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Map"%>
<%@page import="common.util.CommonUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="common.struts.BaseAction"%>
<%@page import="common.bean.MwareConfig"%>
<%
Object exceptionObj = request.getAttribute("gridException");
String gridException = "";
if (exceptionObj != null) gridException = exceptionObj.toString();

List resultList = (List)request.getAttribute(BaseAction.FIND_XML_ATTRIBUTE);
Object totalCountObj = request.getAttribute(BaseAction.TOTAL_COUNT_ATTRIBUTE);

int totalCount = 0;
int totalDataCount = 0;
if (totalCountObj == null)
{
    if (resultList != null)
    {
    	totalCount = resultList.size();
    }
    else
    {
        resultList = new ArrayList();
    }
}
else
{
    totalCount = Integer.parseInt(totalCountObj.toString());
    
    if (totalCount<resultList.size()) totalCount = resultList.size();
    totalDataCount = totalCount;
}
//=================================================================
if (totalCount>resultList.size()) totalCount = resultList.size();
//=================================================================
%>
{
data : [
<%
Iterator rowIter = resultList.iterator();
Iterator colIter = null;
Collection rowColl = null;
if (totalCount == 0) %>'NO_DATA'<%
for (int i = 1; i<=totalCount; i++)
{
	%>
    [
	<%
    rowColl = ((Map) rowIter.next()).values(); // 해당 row의 컬럼 Map을
	// 추출한다.
	colIter = rowColl.iterator(); // key 값 에 상관없이 순서대로 셋팅을 위해서
	for (int j = 1; colIter.hasNext(); j++)
	{
		Object tempObj = colIter.next();
		String value = tempObj==null?"":tempObj.toString();
		if (j==1){%>"true","<%=CommonUtil.replaceGridChar(value)%>"<%}
		else{%>"<%=CommonUtil.replaceGridChar(value)%>"<%}
		if (j != rowColl.size()){%>,<%}
	}
	%>
	]
	<%if (i != totalCount){%>,<%}
}
%>
    ],
    pageInfo:{totalRowNum:<%=totalCount%>, totalDataCount:<%=totalDataCount%>},
    exception:'<%=gridException%>'
}
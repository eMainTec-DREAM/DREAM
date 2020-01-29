<%--===========================================================================
filter Search Button tag
author  javaworker
version $Id: search.tag,v 1.1 2013/08/30 09:10:49 javaworker Exp $
since   1.0
===========================================================================--%>
<%@ tag pageEncoding="EUC-KR" %>
<%
out.println(common.util.CommonUtil.getButtonTag(request, "SEARCH", "search"));
%>
<%--===========================================================================
button tag
buttonId : button id(沏记积己, 力绢)
buttonType : button type
author  javaworker
version $Id: button.tag,v 1.1 2013/08/30 09:10:49 javaworker Exp $
since   1.0
===========================================================================--%>
<%@ tag pageEncoding="EUC-KR" %>
<%@ attribute name="buttonId" required="true" rtexprvalue="true"%>
<%@ attribute name="buttonType" required="false" rtexprvalue="true"%>
<%@ attribute name="buttonName" required="false" rtexprvalue="false"%>
<%
String buttonId = (String)getJspContext().getAttribute("buttonId");
String buttonType = (String)getJspContext().getAttribute("buttonType");
String buttonName = (String)getJspContext().getAttribute("buttonName");
if (buttonType == null || "".equals(buttonType)) buttonType = "midA";
if (buttonName == null)
{
	out.println(common.util.CommonUtil.getButtonTag(request, buttonId, buttonType));
}
else
{
    out.println(common.util.CommonUtil.getButtonTag(buttonId, buttonName, buttonType));
}
%>

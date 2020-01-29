<%--===========================================================================
button tag2
실제 보여지는 button Name과 button id가 다른경우
buttonId : button id(펑션생성, 제어)
nameKeyId : T4RESOURCE.key_id
buttonType : button type
author  javaworker
version $Id: button2.tag,v 1.1 2013/08/30 09:10:49 javaworker Exp $
since   1.0
===========================================================================--%>
<%@ tag pageEncoding="EUC-KR" %>
<%@ attribute name="buttonId" required="true" rtexprvalue="true"%>
<%@ attribute name="nameKeyId" required="true" rtexprvalue="true"%>
<%@ attribute name="buttonType" required="false" rtexprvalue="true"%>
<%
String buttonId = (String)getJspContext().getAttribute("buttonId");
String nameKeyId = (String)getJspContext().getAttribute("nameKeyId");
common.message.DataBaseMessageResources dataBaseMessageResources =
        ( common.message.DataBaseMessageResources)request.getAttribute(org.apache.struts.Globals.MESSAGES_KEY);
// Login User Instance
common.bean.User user = (common.bean.User)request.getSession().getAttribute(request.getSession().getId());
// message key : lang.pageId.keyId   ex) ko.BUTTON.CANCEL 
String buttonName = dataBaseMessageResources.getMessage(user.getLocale(), "BUTTON." + nameKeyId);
String buttonType = (String)getJspContext().getAttribute("buttonType");
if (buttonType == null || "".equals(buttonType)) buttonType = "midA";
out.println(common.util.CommonUtil.getButtonTag(buttonId, buttonName, buttonType));
%>

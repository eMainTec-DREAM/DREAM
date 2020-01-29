 <%--===========================================================================
Message Transfer Detail
author  syyang
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.message.action.MgrMessageTransDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- message전송현황 -->
<title><bean:message key='MENU.MESSAGETRANS' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!--//

function loadPage() 
{
    setTitle("mgrMessageTransDetailDTO.methodTypeDesc", "mgrMessageTransDetailDTO.sendTime");
}

function goTabPage(pageId) 
{
	goCommonTabPage(mgrIntfDetailForm, '' , pageId);
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/mgrMessageTransDetail">
		<html:hidden property="strutsAction" />
		<html:hidden property="mgrMessageTransCommonDTO.messageId" />	<!-- Key -->
		<html:hidden property="mgrMessageTransDetailDTO.messageId" />	<!-- Key -->
		<div class="article_box">
			<div class="form_box">
				<!-- 메일 ID -->
				<div class="field">
					<label><bean:message key="LABEL.id" /></label>
					<div class="input_read">
						<html:text property="mgrMessageTransDetailDTO.messageId" tabindex="10" readonly="true"/>
					</div>
				</div>
				<!-- 제목 -->
				<div class="field_long">
					<label><bean:message key="LABEL.title" /></label>
					<div class="input_read">
						<html:text property="mgrMessageTransDetailDTO.description" tabindex="20" readonly="true"/>
					</div>
				</div>
				<!-- 수신자 -->
				<div class="field">
					<label><bean:message key="LABEL.Recipient" /></label>
					<div class="input_read">
						<html:text property="mgrMessageTransDetailDTO.receiver" tabindex="30" readonly="true"/>
					</div>
				</div>
				<!-- 전송방법 -->
				<div class="field">
					<label><bean:message key="LABEL.methodType" /></label>
					<div class="input_read">
						<html:text property="mgrMessageTransDetailDTO.methodTypeDesc" tabindex="40" readonly="true"/>
					</div>
				</div>
				<!-- 전송시도횟수 -->
				<div class="field">
					<label><bean:message key="LABEL.transTryCnt" /></label>
					<div class="input_read">
						<html:text property="mgrMessageTransDetailDTO.retryCnt" tabindex="50" readonly="true"/>
					</div>
				</div>
				<!-- 메세지 전송상태 -->
				<div class="field">
					<label><bean:message key="LABEL.msgTransStatus" /></label>
					<div class="input_read">
						<html:text property="mgrMessageTransDetailDTO.msgStatusDesc" tabindex="60" readonly="true"/>
					</div>
				</div>
				<!-- 생성시간 -->
				<div class="field">
					<label><bean:message key="LABEL.createDate" /></label>
					<div class="input_read">
						<html:text property="mgrMessageTransDetailDTO.creTime" tabindex="70" readonly="true"/>
					</div>
				</div>
				<!-- 전송시간 -->
				<div class="field">
					<label><bean:message key="LABEL.transTime" /></label>
					<div class="input_read">
						<html:text property="mgrMessageTransDetailDTO.sendTime" tabindex="80" readonly="true"/>
					</div>
				</div>
				<!-- 전송내용 -->
				<div class="field_long">
					<label><bean:message key="LABEL.transContents" /></label>
					<div class="input_read">
						<html:textarea styleClass="ta50" property="mgrMessageTransDetailDTO.contents" tabindex="90" readonly="true"/>
					</div>
				</div>
				<!-- 에러내용(Error발생시) -->
				<div class="field_long">
					<label><bean:message key="LABEL.errorContents" /></label>
					<div class="input_read">
						<html:textarea styleClass="ta50" property="mgrMessageTransDetailDTO.failMsg" tabindex="100" readonly="true"/>
					</div>
				</div>
			</div>
			<!-- End of Form_box -->
		</div>
		<!-- End of Article_box -->
	</html:form>
</body>
</html:html>

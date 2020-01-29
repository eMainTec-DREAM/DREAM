<%--===========================================================================
Audit Trail
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.mgr.at.action.MgrAtDetailAction"%>
<%@ page import="common.bean.User"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html>
<head>
<!-- Audit Trail -->
<title><bean:message key='MENU.AUDTRAIL' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">

function loadPage() 
{
	setTitle("mgrAtDetailDTO.updTime","mgrAtDetailDTO.dataCudTypeDesc");
	
	setForUpdate();
}

function goTabPage(pageId) 
{
    var form = document.mgrAtDetailForm;
    
    form.elements['mgrAtHistListDTO.fileName'].value = form.elements['mgrAtDetailDTO.fileName'].value; 
    form.elements['mgrAtHistListDTO.fieldId'].value = form.elements['mgrAtDetailDTO.fieldId'].value; 
    form.elements['mgrAtHistListDTO.objectId'].value = form.elements['mgrAtDetailDTO.objectId'].value; 
    form.elements['mgrAtHistListDTO.tracelogId'].value = form.elements['mgrAtDetailDTO.tracelogId'].value; 
    
    goCommonTabPage(form, '' , pageId);
       
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/mgrAtDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="mgrAtCommonDTO.tracelogId" />
	<html:hidden property="mgrAtCommonDTO.tracelogDtlId" />
	<html:hidden property="mgrAtCommonDTO.fileName" />
	<html:hidden property="mgrAtDetailDTO.tracelogId" />
	<html:hidden property="mgrAtDetailDTO.tracelogDtlId" />
	<html:hidden property="mgrAtDetailDTO.fieldId" />
	<html:hidden property="mgrAtDetailDTO.fileName" />
	<html:hidden property="mgrAtHistListDTO.tracelogDtlId" />
	<html:hidden property="mgrAtHistListDTO.tracelogId" />
	<html:hidden property="mgrAtHistListDTO.fieldId" />
	<html:hidden property="mgrAtHistListDTO.fileName" />
	<html:hidden property="mgrAtHistListDTO.objectId" />
	
	<div class="article_box">
		<div class="form_box">
			<!-- File Name -->
			<div class="field">
				<label><bean:message key="LABEL.fName"/></label>
				<div class="input_read">
					<html:text property="mgrAtDetailDTO.fileNameFieldDesc" tabindex="10" readonly="true"/>
				</div>
			</div>
			<!-- 항목명 -->
			<div class="field">
				<label><bean:message key="LABEL.prompt"/></label>
				<div class="input_read">
					<html:text property="mgrAtDetailDTO.fieldIdItemDesc" tabindex="20" readonly="true"/>
				</div>
			</div>
			<!-- 구분 -->
			<div class="field">
				<label><bean:message key="LABEL.dataCudType"/></label>
				<div class="input_read">
					<html:text property="mgrAtDetailDTO.dataCudTypeDesc" tabindex="30" readonly="true"/>
				</div>
			</div>
			<!-- 변경자 -->
			<div class="field">
				<label><bean:message key="LABEL.changeBy"/></label>
				<div class="input_read">
					<html:text property="mgrAtDetailDTO.updByDesc" tabindex="40" readonly="true"/>
				</div>
			</div>
			<!-- 변경일자 -->
			<div class="field">
				<label><bean:message key="LABEL.changeDate"/></label>
				<div class="input_read">
					<html:text property="mgrAtDetailDTO.updTime" tabindex="50" readonly="true"/>
				</div>
			</div>
			<!-- 변경 후 -->
			<div class="field">
				<label><bean:message key="LABEL.afterChange"/></label>
				<div class="input_read">
					<html:text property="mgrAtDetailDTO.afterValue" tabindex="60" readonly="true"/>
				</div>
			</div>
			<!-- Object Id -->
			<div class="field">
				<label><bean:message key="LABEL.objectId"/></label>
				<div class="input_read">
					<html:text property="mgrAtDetailDTO.objectId" tabindex="70" readonly="true"/>
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
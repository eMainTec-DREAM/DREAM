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
<%@ page import="dream.mgr.audtrail.action.MgrAudTrailDetailAction"%>
<%@ page import="common.bean.User"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html>
<head>
<!-- Audit Trail -->
<title><bean:message key='LABEL.docCountrNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">

function loadPage() 
{
	setTitle("mgrAudTrailDetailDTO.updTime","mgrAudTrailDetailDTO.dataCudTypeDesc");
	
	setForUpdate();
}

function goTabPage(pageId) 
{
    var form = document.mgrAudTrailDetailForm;
    
    form.elements['mgrAudTrailDtlListDTO.tracelogId'].value = form.elements['mgrAudTrailDetailDTO.tracelogId'].value; 
    
    goCommonTabPage(form, '' , pageId);
       
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/mgrAudTrailDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="mgrAudTrailCommonDTO.tracelogId" />
	<html:hidden property="mgrAudTrailCommonDTO.fileName" />
	<html:hidden property="mgrAudTrailCommonDTO.objectId" />
	<html:hidden property="mgrAudTrailDetailDTO.tracelogId" />
	<html:hidden property="mgrAudTrailDetailDTO.objectId" />
	<html:hidden property="mgrAudTrailDtlListDTO.tracelogId" />
	
	<div class="article_box">
		<div class="form_box">
			<!-- 변경일자 -->
			<div class="field">
				<label><bean:message key="LABEL.changeDate"/></label>
				<div class="input_read">
					<html:text property="mgrAudTrailDetailDTO.updTime" tabindex="10" readonly="true"/>
				</div>
			</div>
			<!-- 변경자 -->
			<div class="field">
				<label><bean:message key="LABEL.changeBy"/></label>
				<div class="input_read">
					<html:text property="mgrAudTrailDetailDTO.updByDesc" tabindex="20" readonly="true"/>
				</div>
			</div>
			<!-- 구분 -->
			<div class="field">
				<label><bean:message key="LABEL.separation"/></label>
				<div class="input_read">
					<html:text property="mgrAudTrailDetailDTO.dataCudTypeDesc" tabindex="30" readonly="true"/>
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
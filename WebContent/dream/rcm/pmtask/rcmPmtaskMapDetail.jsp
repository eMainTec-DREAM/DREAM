
<%--===========================================================================
상세
author  kim21017
version $Id: rcmPmtaskMapDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ page import="common.bean.User"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%@page import="dream.rcm.pmtask.action.RcmPmtaskMapDetailAction"%>
<html>
<head>
<!--답변사항 -->
<title><bean:message key="LABEL.questionPoint"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
function loadPage() 
{
	setTitle("rcmPmtaskMapDetailDTO.description");
	setForUpdate();
}


function goSave(){
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)) rcmPmtaskMapDetailForm.strutsAction.value = '<%=RcmPmtaskMapDetailAction.RCM_FMEA_DETAIL_INPUT%>';
	else rcmPmtaskMapDetailForm.strutsAction.value = '<%= RcmPmtaskMapDetailAction.RCM_FMEA_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/rcmPmtaskMapDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(rcmPmtaskMapDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(rcmPmtaskMapDetailForm.elements['rcmPmtaskMapDetailDTO.rcmpmtaskmapId'].value);

    getTopPage().afterSaveAll(currentPageId);
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = rcmPmtaskMapDetailForm.elements['rcmPmtaskMapDetailDTO.rcmpmtaskmapId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>

</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/rcmPmtaskMapDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="rcmPmtaskCommonDTO.rcmpmtaskId"/>
<html:hidden property="rcmPmtaskMapDetailDTO.rcmpmtaskmapId"/>
<html:hidden property="rcmPmtaskMapDetailDTO.rcmlistId"/>
<html:hidden property="rcmPmtaskMapDetailDTO.rcmfmeaId"/>

    <!-- searchbox 박스 Line -->
	<div class="article_box">
			<div class="form_box">

			<!-- 항목 -->
			<div class="field_long">
				<label><bean:message key="LABEL.prompt"/></label>
				<div class="input_read">
					<html:text property="rcmPmtaskMapDetailDTO.description" tabindex="20" readonly="true" />
				</div>
			</div>
			<!-- 값 -->
			<div class="field">
				<label><bean:message key="LABEL.rstVal"/></label>
				<div class="input_read">
					<html:text property="rcmPmtaskMapDetailDTO.value" tabindex="30" readonly="true" />
				</div>
			</div>
			<!-- 선택업무 -->
			<div class="field_long">
				<label><bean:message key="LABEL.rsltTask"/></label>
				<div class="input_read">
					<html:text property="rcmPmtaskMapDetailDTO.rsltTask" tabindex="40" readonly="true"/>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="rcmPmtaskMapDetailDTO.remark" styleClass="ta50" tabindex="50" />
				</div>
			</div>
			</div>
	</div><!--article_box end-->
</html:form> 
</body>
</html>
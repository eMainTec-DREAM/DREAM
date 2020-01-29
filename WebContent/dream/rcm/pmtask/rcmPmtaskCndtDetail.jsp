
<%--===========================================================================
상세
author  kim21017
version $Id: rcmPmtaskCndtDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
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
<%@page import="dream.rcm.pmtask.action.RcmPmtaskCndtDetailAction"%>
<html>
<head>
<!--대안작업 -->
<title><bean:message key="LABEL.questionPoint"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goUpdate();
		//goTabPage("maEqMstrSpecList");
	}
	
	setTitle("rcmPmtaskCndtDetailDTO.taskcndt");
	setForUpdate();
}

function goUpdate(){

}

function goInput()
{
	sequenceNextVal('SQARCMPMTASKCNDT_ID');
}

function setSequenceVal(sequenceVal)
{
	rcmPmtaskCndtDetailForm.elements['rcmPmtaskCndtDetailDTO.rcmpmtaskcndtId'].value = sequenceVal;
}

function goSave(){
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)) rcmPmtaskCndtDetailForm.strutsAction.value = '<%=RcmPmtaskCndtDetailAction.PMTASK_CNDT_DETAIL_INPUT%>';
	else rcmPmtaskCndtDetailForm.strutsAction.value = '<%= RcmPmtaskCndtDetailAction.PMTASK_CNDT_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/rcmPmtaskCndtDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(rcmPmtaskCndtDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(rcmPmtaskCndtDetailForm.elements['rcmPmtaskCndtDetailDTO.rcmpmtaskcndtId'].value);

    getTopPage().afterSaveAll(currentPageId);
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = rcmPmtaskCndtDetailForm.elements['rcmPmtaskCndtDetailDTO.rcmpmtaskcndtId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>

</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/rcmPmtaskCndtDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="rcmPmtaskCommonDTO.rcmpmtaskId"/>
<html:hidden property="rcmPmtaskCndtDetailDTO.rcmpmtaskcndtId"/>
<html:hidden property="rcmPmtaskCndtDetailDTO.rcmlistId"/>
<html:hidden property="rcmPmtaskCndtDetailDTO.rcmpmtaskId"/>

    <!-- searchbox 박스 Line -->
	<div class="article_box">
			<div class="form_box">

			<!-- 대안작업들 -->
			<div class="field_long">
				<label><bean:message key="LABEL.altTask"/></label>
				<div class="input_box">
					<html:text property="rcmPmtaskCndtDetailDTO.taskcndt" tabindex="20"/>
				</div>
			</div>
			<!-- 작업영향도 -->
			<div class="field_long">
				<label><bean:message key="LABEL.taskefinfo"/></label>
				<div class="input_box">
					<html:text property="rcmPmtaskCndtDetailDTO.taskefinfo" tabindex="30"/>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="rcmPmtaskCndtDetailDTO.remark" styleClass="ta50" tabindex="50" />
				</div>
			</div>
			</div>
	</div><!--article_box end-->
</html:form> 
</body>
</html>
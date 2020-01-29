
<%--===========================================================================
응답 상세
author  kim21017
version $Id: rcmFfailItemDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
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
<%@page import="dream.rcm.ffail.action.RcmFfailItemDetailAction"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
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
	if(ckCreate(currentPageId)) goInput();
	else
	{
		//수정시 readonly설정 
		rcmFfailItemDetailForm.elements['rcmFfailItemDetailDTO.rcmFfailNo'].readOnly="true";
		document.getElementById("failNoDiv").className = "input_read";
	}
	setTitle("rcmFfailItemDetailDTO.rcmFfailNo");
	setForUpdate();
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAANSWER_ID');

	
}

function setSequenceVal(sequenceVal)
{
	rcmFfailItemDetailForm.elements['rcmFfailItemDetailDTO.rcmFfailId'].value = sequenceVal;
}

function goSave(){
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)) rcmFfailItemDetailForm.strutsAction.value = '<%=RcmFfailItemDetailAction.QNA_ANS_DETAIL_INPUT%>';
	else rcmFfailItemDetailForm.strutsAction.value = '<%= RcmFfailItemDetailAction.QNA_ANS_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/rcmFfailItemDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(rcmFfailItemDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(rcmFfailItemDetailForm.elements['rcmFfailItemDetailDTO.rcmFfailId'].value);

	setTitle("rcmFfailItemDetailDTO.rcmFfailNo");
    getTopPage().afterSaveAll(currentPageId);
}

function goTabPage(pageId)
{
	var form = document.rcmFfailItemDetailForm;

	if(pageId == "maDocLibList" || pageId == "maEqDocLibList")
	{	
		rcmFfailItemDetailForm.elements['maDocLibCommonDTO.objectId'].value = rcmFfailItemDetailForm.elements['rcmFfailItemDetailDTO.answerId'].value;
		rcmFfailItemDetailForm.elements['maDocLibCommonDTO.objectType'].value = "ANS";
		rcmFfailItemDetailForm.elements['maDocLibCommonDTO.description'].value = rcmFfailItemDetailForm.elements['rcmFfailItemDetailDTO.answerDesc'].value;
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else
		goCommonTabPage(form, '' , pageId);
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = rcmFfailItemDetailForm.elements['rcmFfailItemDetailDTO.rcmFfailId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>

</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/rcmFfailItemDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="rcmFfailCommonDTO.rcmFuncId"/>
<html:hidden property="rcmFfailItemDetailDTO.rcmFfailId"/>
    <!-- searchbox 박스 Line -->
	<div class="article_box">
			<div class="form_box">
				<div class="field">
					<label><bean:message key="LABEL.rcmFfailNo"/></label>
					<div class="input_box" id="failNoDiv">
						<html:text property="rcmFfailItemDetailDTO.rcmFfailNo" tabindex="10"/>
					</div>
				</div>
				<div class="field_long">
					<label><bean:message key="LABEL.rcmFfailName"/></label>
					<div class="input_box">
						<html:text property="rcmFfailItemDetailDTO.description" tabindex="20" />
					</div>
				</div>

			<!-- 답변내용 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="rcmFfailItemDetailDTO.remark" styleClass="ta50" tabindex="50" />
				</div>
			</div>
			</div>
	</div><!--article_box end-->
</html:form> 
</body>
</html>
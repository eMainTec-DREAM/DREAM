
<%--===========================================================================
응답 상세
author  kim21017
version $Id: maQnaAnsDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
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
<%@page import="dream.req.qna.action.MaQnaAnsDetailAction"%>
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
	
	setTitle("maQnaAnsDetailDTO.answerNo");
	setForUpdate();
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAANSWER_ID');
	
	maQnaAnsDetailForm.elements['maQnaAnsDetailDTO.regDate'].value = getToday();

	maQnaAnsDetailForm.elements['maQnaAnsDetailDTO.deptId'].value    = "<%=user.getDeptId()%>";
	maQnaAnsDetailForm.elements['maQnaAnsDetailDTO.deptDesc'].value  = "<%=user.getDeptDesc()%>";
	maQnaAnsDetailForm.elements['maQnaAnsDetailDTO.userId'].value     = "<%=user.getUserId()%>";
	maQnaAnsDetailForm.elements['maQnaAnsDetailDTO.userDesc'].value   = "<%=user.getUserName()%>";
	
}

function setSequenceVal(sequenceVal)
{
	maQnaAnsDetailForm.elements['maQnaAnsDetailDTO.answerId'].value = sequenceVal;
	maQnaAnsDetailForm.elements['maQnaAnsDetailDTO.answerNo'].value = sequenceVal;
}

function goSave(){
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)) maQnaAnsDetailForm.strutsAction.value = '<%=MaQnaAnsDetailAction.QNA_ANS_DETAIL_INPUT%>';
	else maQnaAnsDetailForm.strutsAction.value = '<%= MaQnaAnsDetailAction.QNA_ANS_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maQnaAnsDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maQnaAnsDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maQnaAnsDetailForm.elements['maQnaAnsDetailDTO.answerId'].value);

	setTitle("maQnaAnsDetailDTO.answerNo");
    getTopPage().afterSaveAll(currentPageId);
}

function goTabPage(pageId)
{
	var form = document.maQnaAnsDetailForm;

	if(pageId == "maDocLibList" || pageId == "maQnaAnsDocLibList")
	{	
		maQnaAnsDetailForm.elements['maDocLibCommonDTO.objectId'].value = maQnaAnsDetailForm.elements['maQnaAnsDetailDTO.answerId'].value;
		maQnaAnsDetailForm.elements['maDocLibCommonDTO.objectType'].value = "ANS";
		maQnaAnsDetailForm.elements['maDocLibCommonDTO.description'].value = maQnaAnsDetailForm.elements['maQnaAnsDetailDTO.answerDesc'].value;
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else
		goCommonTabPage(form, '' , pageId);
}

function goAudtrailLink()
{
	var objectId = maQnaAnsDetailForm.elements['maQnaAnsDetailDTO.answerId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}


//-->
</script>

</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/maQnaAnsDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maQnaCommonDTO.questionId"/>
<html:hidden property="maQnaAnsDetailDTO.answerId"/>
<html:hidden property="maQnaAnsDetailDTO.userId"/>
<html:hidden property="maQnaAnsDetailDTO.deptId"/>
<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
<html:hidden property="maDocLibCommonDTO.objectType" />
<html:hidden property="maDocLibCommonDTO.description" />
    <!-- searchbox 박스 Line -->
	<div class="article_box">
			<div class="form_box">
				<div class="field">
					<label><bean:message key="LABEL.answerNo"/></label>
					<div class="input_read">
						<html:text property="maQnaAnsDetailDTO.answerNo" tabindex="10" readonly="true"/>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.updDate"/></label>
					<div class="input_read">
						<html:text property="maQnaAnsDetailDTO.regDate" tabindex="20"  readonly="true"/>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.regDept"/></label>
					<div class="input_read">
						<html:text property="maQnaAnsDetailDTO.deptDesc" tabindex="30"  readonly="true"/>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.updBy"/></label>
					<div class="input_read">
						<html:text property="maQnaAnsDetailDTO.userDesc" tabindex="40"  readonly="true"/>
					</div>
				</div>
			<!-- 답변내용 -->
			<div class="field_long">
				<label><bean:message key="LABEL.answerDesc"/></label>
				<div class="input_box">
					<html:textarea property="maQnaAnsDetailDTO.answerDesc" styleClass="ta50" tabindex="50" />
				</div>
			</div>
			</div>
	</div><!--article_box end-->
</html:form> 
</body>
</html>
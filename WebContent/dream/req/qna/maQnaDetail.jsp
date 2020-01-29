<%--===========================================================================
질의 - 상세
author  kim21017
version $Id: maQnaDetail.jsp,v 1.0 2015/12/04 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="common.bean.User"%>
<%@ page import="dream.req.qna.action.MaQnaDetailAction"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html>
<head>
<title><bean:message key="LABEL.qnaAns"/>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goTabPage("maQnaAnsList");
	}
	
	setTitle("maQnaDetailDTO.questionNo","maQnaDetailDTO.questionTitle");
	
	setForUpdate();
}

function goInput()
{
	sequenceNextVal('SQAQUESTION_ID');
	maQnaDetailForm.elements['maQnaDetailDTO.regDate'].value = getToday();

	maQnaDetailForm.elements['maQnaDetailDTO.deptId'].value    = "<%=user.getDeptId()%>";
	maQnaDetailForm.elements['maQnaDetailDTO.deptDesc'].value  = "<%=user.getDeptDesc()%>";
	maQnaDetailForm.elements['maQnaDetailDTO.userId'].value     = "<%=user.getUserId()%>";
	maQnaDetailForm.elements['maQnaDetailDTO.userDesc'].value   = "<%=user.getUserName()%>";
}

function setSequenceVal(sequenceVal)
{
	maQnaDetailForm.elements['maQnaDetailDTO.questionId'].value = sequenceVal;
	maQnaDetailForm.elements['maQnaCommonDTO.questionId'].value = sequenceVal;
	maQnaDetailForm.elements['maQnaDetailDTO.questionNo'].value = sequenceVal;
	
	goTabPage("maQnaAnsList");
}

function goOpen(pageId)
{
	goTabPage(pageId);
}

function goTabPage(pageId)
{
	var form = document.maQnaDetailForm;

	if(pageId == "maDocLibList" || pageId == "maQnaDocLibList")
	{	
		maQnaDetailForm.elements['maDocLibCommonDTO.objectId'].value = maQnaDetailForm.elements['maQnaCommonDTO.questionId'].value;
		maQnaDetailForm.elements['maDocLibCommonDTO.objectType'].value = "QNA";
		maQnaDetailForm.elements['maDocLibCommonDTO.description'].value = maQnaDetailForm.elements['maQnaDetailDTO.questionTitle'].value;
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else
		goCommonTabPage(form, '' , pageId);
    
}

/**
 * 저장
 */ 
function goSave()
{
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	//strutsAction 셋팅.
	if(ckCreate(currentPageId)) maQnaDetailForm.strutsAction.value = "<%=MaQnaDetailAction.QNA_DETAIL_INPUT%>";
	else maQnaDetailForm.strutsAction.value = '<%=MaQnaDetailAction.QNA_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/maQnaDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maQnaDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
 	//=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     maQnaDetailForm.elements['maQnaCommonDTO.questionId'].value = maQnaDetailForm.elements['maQnaDetailDTO.questionId'].value;
     if (parent.findGridRow)	parent.findGridRow(maQnaDetailForm.elements['maQnaCommonDTO.questionId'].value);

 	setTitle("maQnaDetailDTO.questionNo","maQnaDetailDTO.questionTitle");
     
     getTopPage().afterSaveAll(currentPageId);
 }
 /**
 *신청완료
 */
 function goConfirm(){
	alert('goConfirm');
 }
 
 function goAudtrailLink()
 {
 	var objectId = maQnaDetailForm.elements['maQnaDetailDTO.questionId'].value;
 	var fileName = currentPageId;

 	if(typeof objectId=="undefined") return;

 	goAudTrailList(objectId, fileName);
 }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maQnaDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maQnaCommonDTO.questionId" />
	<html:hidden property="maQnaDetailDTO.questionId" />
	<html:hidden property="maQnaDetailDTO.deptId" />
	<html:hidden property="maQnaDetailDTO.userId" />
 	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
 	<html:hidden property="maDocLibCommonDTO.objectType" />
 	<html:hidden property="maDocLibCommonDTO.description" />
	<div class="article_box">
		<div class="form_box">
				<div class="field">
					<label><bean:message key="LABEL.docCntrNo"/></label>
					<div class="input_read">
						<html:text property="maQnaDetailDTO.questionNo" tabindex="10" readonly="true"/>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.updDate"/></label>
					<div class="input_read">
						<html:text property="maQnaDetailDTO.regDate" tabindex="20"  readonly="true"/>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.regDept"/></label>
					<div class="input_read">
						<html:text property="maQnaDetailDTO.deptDesc" tabindex="30"  readonly="true"/>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.updBy"/></label>
					<div class="input_read">
						<html:text property="maQnaDetailDTO.userDesc" tabindex="40"  readonly="true"/>
					</div>
				</div>
				<div class="field_long">
					<label><bean:message key="LABEL.title"/></label>
					<div class="input_box">
						<html:text property="maQnaDetailDTO.questionTitle" tabindex="50" />
					</div>
				</div>
			<!-- 질의내용 -->
			<div class="field_long">
				<label><bean:message key="LABEL.questionDesc"/></label>
				<div class="input_box">
					<html:textarea property="maQnaDetailDTO.questionDesc" styleClass="ta50" tabindex="60" />
				</div>
			</div>
			</div> <!-- End of Form_box -->
		</div> <!-- End of Article_box -->
	</html:form>
</body>
</html:html>
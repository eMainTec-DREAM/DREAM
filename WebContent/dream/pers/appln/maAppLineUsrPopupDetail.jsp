
<%--===========================================================================
상세
author  kim21017
version $Id: maAppLineUsrDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
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
<%@ page import="dream.pers.appln.action.MaAppLineUsrDetailAction"%>
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
	
	setTitle("maAppLineUsrDetailDTO.apprByName");
	setForUpdate();
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAAPPRLINEUSR_ID');
	
	<%-- maAppLineUsrDetailForm.elements['maAppLineUsrDetailDTO.regDate'].value = getToday();

	maAppLineUsrDetailForm.elements['maAppLineUsrDetailDTO.deptId'].value    = "<%=user.getDeptId()%>";
	maAppLineUsrDetailForm.elements['maAppLineUsrDetailDTO.deptDesc'].value  = "<%=user.getDeptDesc()%>";
	maAppLineUsrDetailForm.elements['maAppLineUsrDetailDTO.userId'].value     = "<%=user.getUserId()%>";
	maAppLineUsrDetailForm.elements['maAppLineUsrDetailDTO.userDesc'].value   = "<%=user.getUserName()%>";
	 --%>
}

function setSequenceVal(sequenceVal)
{
	maAppLineUsrDetailForm.elements['maAppLineUsrDetailDTO.apprlineusrId'].value = sequenceVal;
	maAppLineUsrDetailForm.elements['maAppLineUsrListDTO.apprlineusrId'].value = sequenceVal;
}

function goSave(){
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)) maAppLineUsrDetailForm.strutsAction.value = '<%=MaAppLineUsrDetailAction.QNA_ANS_DETAIL_INPUT%>';
	else maAppLineUsrDetailForm.strutsAction.value = '<%= MaAppLineUsrDetailAction.QNA_ANS_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maAppLineUsrDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maAppLineUsrDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maAppLineUsrDetailForm.elements['maAppLineUsrDetailDTO.apprlineusrId'].value);

	setTitle("maAppLineUsrDetailDTO.apprByName");
    getTopPage().afterSaveAll(currentPageId);
}

function goTabPage(pageId)
{
	var form = document.maAppLineUsrDetailForm;

	goCommonTabPage(form, '' , pageId);
}

//-->
</script>

</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/maAppLineUsrDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maAppLineCommonDTO.apprlineId"/>
<html:hidden property="maAppLineUsrListDTO.apprlineusrId"/>
<html:hidden property="maAppLineUsrDetailDTO.apprlineusrId"/>
<html:hidden property="maAppLineUsrDetailDTO.apprBy"/>

    <!-- searchbox 박스 Line -->
	<div class="article_box">
			<div class="form_box">
				<!-- 결재순서 -->
	               <div class="field">
		               <label class="check"><bean:message key="LABEL.apprSeq"/></label>
	               	   <div class="input_box">
	               	   		<html:text property="maAppLineUsrDetailDTO.apprSeq" tabindex="10"/>
	               	   </div>
               	   </div>
				<!-- 결재자 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.apprByName"/></label>
					<div class="input_box">
						<html:text property="maAppLineUsrDetailDTO.apprByName" tabindex="50"
									onkeydown="validationKeyDown('maAppLineUsrDetailDTO.apprByName', 'maAppLineUsrDetailDTO.apprBy');"/>
						<p class="open_spop"><a href="javascript:lovTable('maAppLineUsrDetailDTO.apprBy', 'maAppLineUsrDetailDTO.apprByName','TAEMPNAME');"><span>조회</span></a></p>
					</div>
				</div>
				<!-- 직위 -->
				<div class="field">
					<label><bean:message key="LABEL.grade"/></label>
					<div class="input_read">
							<html:text property="maAppLineUsrDetailDTO.grade" tabindex="30" readonly="true"/>
					</div>
				</div>
				<!-- 부서 -->
				<div class="field">
	               <label><bean:message key="LABEL.dept"/></label>
               	   <div class="input_read">
               	   		<html:text property="maAppLineUsrDetailDTO.deptName" tabindex="40" readonly="true"/>
               	   </div>
				</div>
			</div>
	</div><!--article_box end-->
</html:form> 
</body>
</html>
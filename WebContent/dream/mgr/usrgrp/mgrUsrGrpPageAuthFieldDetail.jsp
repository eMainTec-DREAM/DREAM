 <%--===========================================================================
화면권한설정상세탭버튼권한상세
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.usrgrp.action.MgrUsrGrpPageAuthFieldAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 화면권한설정상세탭버튼권한상세 -->
<title><bean:message key='PAGE.mgrUsrGrpPageAuthFieldDetail' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

function loadPage() 
{
	goUpdate();
	
	setTitle("mgrUsrGrpPageAuthFieldDTO.fieldId", "mgrUsrGrpPageAuthFieldDTO.labelDesc");
    setForUpdate();

	acSysDesc("mgrUsrGrpPageAuthFieldDTO.authLimitTypeDesc","mgrUsrGrpPageAuthFieldDTO.authLimitTypeId","AUTH_LIMIT_TYPE", true);
    
    
}

/**
 * 수정
 */
function goUpdate()
{
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
    mgrUsrGrpPageAuthFieldForm.strutsAction.value = "<%=MgrUsrGrpPageAuthFieldAction.DETAIL_UPDATE%>";

	var actionUrl = contextPath + "/mgrUsrGrpPageAuthFieldDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(mgrUsrGrpPageAuthFieldForm), 'afterSave');
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc) {
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc))
		return;
	//=====================================
	if (parent.findGridRow)
		parent.findGridRow(mgrUsrGrpPageAuthFieldForm.elements['mgrUsrGrpPageAuthFieldDTO.pgfieldId'].value);

	getTopPage().afterSaveAll(currentPageId);
	
	alertMessage1('<bean:message key="MESSAGE.MSG1009"/>');
	
	setTitle("mgrUsrGrpPageAuthFieldDTO.fieldId", "mgrUsrGrpPageAuthFieldDTO.labelDesc");
}

function goTabPage(pageId)
{
    goCommonTabPage(mgrUsrGrpPageAuthFieldForm, '' , pageId);
}
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

<html:form action="/mgrUsrGrpPageAuthFieldDetail">
	<html:hidden property="strutsAction" />
	<html:hidden property="mgrUsrGrpPageAuthFieldDTO.pageId"/><!-- Key -->
	<html:hidden property="mgrUsrGrpPageAuthFieldDTO.pgfieldId"/><!-- Key -->
	<html:hidden property="mgrUsrGrpPageAuthFieldDTO.usrgrpId"/><!-- Key -->
	<html:hidden property="mgrUsrGrpPageAuthFieldDTO.ugpgfieldauId"/>
	<html:hidden property="mgrUsrGrpPageAuthFieldDTO.authLimitTypeId"/>
	<html:hidden property="mgrUsrGrpPageAuthFieldDTO.checkYn"/>
	<div class="article_box">
		<div class="form_box">
			<!-- FIELD ID -->
			<div class="field">
				<label><bean:message key="LABEL.fieldId" /></label>
				<div class="input_read">
					<html:text property="mgrUsrGrpPageAuthFieldDTO.fieldId" readonly="true" />
				</div>
			</div>
			<!-- Label명 -->
			<div class="field">
				<label><bean:message key="LABEL.labelName" /></label>
				<div class="input_read">
					<html:text property="mgrUsrGrpPageAuthFieldDTO.labelDesc" readonly="true" />
				</div>
			</div>
			<!-- 권한제한 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.authLimitType" /></label>
				<div class="input_box">
					<html:text property="mgrUsrGrpPageAuthFieldDTO.authLimitTypeDesc" tabindex="10" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
		</div>
		<!-- End of Form_box -->
	</div>
	<!-- End of Article_box -->
</html:form>
</body>
</html:html>

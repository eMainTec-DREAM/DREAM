 <%--===========================================================================
화면권한설정상세
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.usrgrp.action.MgrUsrGrpPageAuthAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 화면권한설정상세 -->
<title><bean:message key='PAGE.mgrUsrGrpPageAuthDetail' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

function loadPage() 
{
	goUpdate();
	
	setTitle("mgrUsrGrpPageAuthDTO.fileName", "mgrUsrGrpPageAuthDTO.pageDesc");
	
    //For Save
    setForUpdate();
    
    //권한여부 자동완성
    acSysDesc("mgrUsrGrpPageAuthDTO.isAuth","mgrUsrGrpPageAuthDTO.isAuth","IS_USE", true);
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
    mgrUsrGrpPageAuthForm.strutsAction.value = "<%=MgrUsrGrpPageAuthAction.DETAIL_UPDATE%>";

	var actionUrl = contextPath + "/mgrUsrGrpPageAuthDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(mgrUsrGrpPageAuthForm), 'afterSave');
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
		parent.findGridRow(mgrUsrGrpPageAuthForm.elements['mgrUsrGrpPageAuthDTO.pageId'].value, mgrUsrGrpPageAuthForm.elements['mgrUsrGrpPageAuthDTO.usrgrpId'].value);

	getTopPage().afterSaveAll(currentPageId);
	
	setTitle("mgrUsrGrpPageAuthDTO.fileName", "mgrUsrGrpPageAuthDTO.pageDesc");
}

function goTabPage(pageId)
{
    goCommonTabPage(mgrUsrGrpPageAuthForm, '' , pageId);
}
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

	<html:form action="/mgrUsrGrpPageAuthDetail">
		<html:hidden property="strutsAction" />
		<html:hidden property="mgrUsrGrpPageAuthDTO.pageId" />
		<html:hidden property="mgrUsrGrpPageAuthDTO.usrgrpId" />
		<html:hidden property="mgrUsrGrpPageAuthDTO.ugpgauId" />
		<html:hidden property="mgrUsrGrpPageAuthDTO.serviceType" />
		<div class="article_box">
			<div class="form_box">
				<!-- 권한코드 -->
				<div class="field">
					<label><bean:message key="LABEL.usrGrpNo" /></label>
					<div class="input_read">
						<html:text property="mgrUsrGrpPageAuthDTO.usrgrpNo" readonly="true" />
					</div>
				</div>
				<!-- 권한명 -->
				<div class="field">
					<label><bean:message key="LABEL.groupName" /></label>
					<div class="input_read">
						<html:text property="mgrUsrGrpPageAuthDTO.groupName" readonly="true" />
					</div>
				</div>
				<!-- 화면ID -->
				<div class="field">
					<label><bean:message key="LABEL.fileName" /></label>
					<div class="input_read">
						<html:text property="mgrUsrGrpPageAuthDTO.fileName" readonly="true" />
					</div>
				</div>
				<!-- 화면명 -->
				<div class="field">
					<label><bean:message key="LABEL.pageName" /></label>
					<div class="input_read">
						<html:text property="mgrUsrGrpPageAuthDTO.pageDesc" readonly="true" />
					</div>
				</div>
				<!-- 권한 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.auth" /></label>
					<div class="input_box">
						<html:text property="mgrUsrGrpPageAuthDTO.isAuth" tabindex="10" />
						<p class="open_spop"><a> <span>조회</span></a></p>
					</div>
				</div>
				<!-- 서비스구분 -->
				<div class="field">
					<label><bean:message key="LABEL.serviceType" /></label>
					<div class="input_read">
						<html:text property="mgrUsrGrpPageAuthDTO.serviceTypeDesc" readonly="true" />
					</div>
				</div>
			</div>
			<!-- End of Form_box -->
		</div>
		<!-- End of Article_box -->
	</html:form>
</body>
</html:html>

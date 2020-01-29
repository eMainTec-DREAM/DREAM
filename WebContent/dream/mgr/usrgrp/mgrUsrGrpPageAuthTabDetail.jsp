 <%--===========================================================================
화면권한설정상세탭탭권한상세
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.usrgrp.action.MgrUsrGrpPageAuthTabAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 화면권한설정상세탭버튼권한상세 -->
<title><bean:message key='PAGE.mgrUsrGrpPageAuthTabDetail' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

function loadPage() 
{
	goUpdate();
	
	setTitle("mgrUsrGrpPageAuthTabDTO.fileName", "mgrUsrGrpPageAuthTabDTO.tabName");
	
    //For Save
    setForUpdate();
    
    //권한여부 자동완성
    acSysDesc("mgrUsrGrpPageAuthTabDTO.isAuth","mgrUsrGrpPageAuthTabDTO.isAuth","IS_USE", true);
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
    mgrUsrGrpPageAuthTabForm.strutsAction.value = "<%=MgrUsrGrpPageAuthTabAction.DETAIL_UPDATE%>";

	var actionUrl = contextPath + "/mgrUsrGrpPageAuthTabDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(mgrUsrGrpPageAuthTabForm), 'afterSave');
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
		parent.findGridRow(mgrUsrGrpPageAuthTabForm.elements['mgrUsrGrpPageAuthTabDTO.pgpageId'].value);

	getTopPage().afterSaveAll(currentPageId);
	
	setTitle("mgrUsrGrpPageAuthTabDTO.fileName", "mgrUsrGrpPageAuthTabDTO.tabName");
}

function goTabPage(pageId)
{
    goCommonTabPage(mgrUsrGrpPageAuthTabForm, '' , pageId);
}

function goPageauthLink()
{
	goPageAuthList({
		"usrgrpId":mgrUsrGrpPageAuthTabForm.elements['mgrUsrGrpPageAuthTabDTO.usrgrpId'].value
		,"fileName":mgrUsrGrpPageAuthTabForm.elements['mgrUsrGrpPageAuthTabDTO.fileName'].value
	});
}
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

	<html:form action="/mgrUsrGrpPageAuthTabDetail">
		<html:hidden property="strutsAction" />
		<html:hidden property="mgrUsrGrpPageAuthTabDTO.pgpageId" />
		<html:hidden property="mgrUsrGrpPageAuthTabDTO.usrgrpId" />
		<html:hidden property="mgrUsrGrpPageAuthTabDTO.ugpgpgauId" />
		<div class="article_box">
			<div class="form_box">
				<!-- 탭페이지 ID -->
				<div class="field">
					<label><bean:message key="LABEL.tabPageId" /></label>
					<div class="input_read">
						<html:text property="mgrUsrGrpPageAuthTabDTO.fileName" readonly="true" />
					</div>
				</div>
				<!-- 탭페이지 명 -->
				<div class="field">
					<label><bean:message key="LABEL.tabPageDesc" /></label>
					<div class="input_read">
						<html:text property="mgrUsrGrpPageAuthTabDTO.tabName" readonly="true" />
					</div>
				</div>
				<!-- 권한 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.auth" /></label>
					<div class="input_box">
						<html:text property="mgrUsrGrpPageAuthTabDTO.isAuth" tabindex="10" />
						<p class="open_spop"><a> <span>조회</span></a></p>
					</div>
				</div>
				<!-- 페이지권한 -->
				<div class="field">
					<label><bean:message key="LABEL.pageAuth" /></label>
					<div class="input_read">
						<html:text property="mgrUsrGrpPageAuthTabDTO.pageAuth" readonly="true" />
					</div>
				</div>
			</div>
			<!-- End of Form_box -->
		</div>
		<!-- End of Article_box -->
	</html:form>
</body>
</html:html>

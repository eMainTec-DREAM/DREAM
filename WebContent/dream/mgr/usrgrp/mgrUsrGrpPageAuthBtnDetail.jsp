 <%--===========================================================================
화면권한설정상세탭버튼권한상세
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.usrgrp.action.MgrUsrGrpPageAuthBtnAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 화면권한설정상세탭버튼권한상세 -->
<title><bean:message key='PAGE.mgrUsrGrpPageAuthBtnDetail' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

function loadPage() 
{
	goUpdate();
	
	setTitle("mgrUsrGrpPageAuthBtnDTO.buttonNo", "mgrUsrGrpPageAuthBtnDTO.buttonName");
	
    //For Save
    setForUpdate();
    
    //권한여부 자동완성
    acSysDesc("mgrUsrGrpPageAuthBtnDTO.isAuth","mgrUsrGrpPageAuthBtnDTO.isAuth","IS_USE", true);
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
    mgrUsrGrpPageAuthBtnForm.strutsAction.value = "<%=MgrUsrGrpPageAuthBtnAction.DETAIL_UPDATE%>";

	var actionUrl = contextPath + "/mgrUsrGrpPageAuthBtnDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(mgrUsrGrpPageAuthBtnForm), 'afterSave');
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
		parent.findGridRow(mgrUsrGrpPageAuthBtnForm.elements['mgrUsrGrpPageAuthBtnDTO.pgbtnId'].value);

	getTopPage().afterSaveAll(currentPageId);
	
	setTitle("mgrUsrGrpPageAuthBtnDTO.buttonNo", "mgrUsrGrpPageAuthBtnDTO.buttonName");
}

function goTabPage(pageId)
{
    goCommonTabPage(mgrUsrGrpPageAuthBtnForm, '' , pageId);
}
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

	<html:form action="/mgrUsrGrpPageAuthBtnDetail">
		<html:hidden property="strutsAction" />
		<html:hidden property="mgrUsrGrpPageAuthBtnDTO.pgbtnId" />
		<html:hidden property="mgrUsrGrpPageAuthBtnDTO.usrgrpId" />
		<html:hidden property="mgrUsrGrpPageAuthBtnDTO.ugpgbtnauId" />
		<div class="article_box">
			<div class="form_box">
				<!-- 버튼ID -->
				<div class="field">
					<label><bean:message key="LABEL.buttonId" /></label>
					<div class="input_read">
						<html:text property="mgrUsrGrpPageAuthBtnDTO.buttonNo" readonly="true" />
					</div>
				</div>
				<!-- 버튼명 -->
				<div class="field">
					<label><bean:message key="LABEL.buttonName" /></label>
					<div class="input_read">
						<html:text property="mgrUsrGrpPageAuthBtnDTO.buttonName" readonly="true" />
					</div>
				</div>
				<!-- 권한 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.auth" /></label>
					<div class="input_box">
						<html:text property="mgrUsrGrpPageAuthBtnDTO.isAuth" tabindex="10" />
						<p class="open_spop"><a> <span>조회</span></a></p>
					</div>
				</div>
				<!-- 버튼위치 -->
				<div class="field">
					<label><bean:message key="LABEL.buttonLoc" /></label>
					<div class="input_read">
						<html:text property="mgrUsrGrpPageAuthBtnDTO.buttonLoc" readonly="true" />
					</div>
				</div>
			</div>
			<!-- End of Form_box -->
		</div>
		<!-- End of Article_box -->
	</html:form>
</body>
</html:html>

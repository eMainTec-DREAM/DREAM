 <%--===========================================================================
화면권한설정상세탭버튼권한상세
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.usrgrp.action.MgrUsrGrpPageAuthGridColAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 화면권한설정상세탭버튼권한상세 -->
<title><bean:message key='PAGE.mgrUsrGrpPageAuthGridColDetail' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

function loadPage() 
{
	goUpdate();
	
	setTitle("mgrUsrGrpPageAuthGridColDTO.columnId", "mgrUsrGrpPageAuthGridColDTO.labelDesc");
    setForUpdate();

	acSysDesc("mgrUsrGrpPageAuthGridColDTO.authLimitTypeDesc","mgrUsrGrpPageAuthGridColDTO.authLimitTypeId","AUTH_LIMIT_TYPE", true);
    
    
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
    mgrUsrGrpPageAuthGridColForm.strutsAction.value = "<%=MgrUsrGrpPageAuthGridColAction.DETAIL_UPDATE%>";

	var actionUrl = contextPath + "/mgrUsrGrpPageAuthGridColDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(mgrUsrGrpPageAuthGridColForm), 'afterSave');
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
		parent.findGridRow(mgrUsrGrpPageAuthGridColForm.elements['mgrUsrGrpPageAuthGridColDTO.pggridcolId'].value);

	getTopPage().afterSaveAll(currentPageId);
	
	setTitle("mgrUsrGrpPageAuthGridColDTO.columnId", "mgrUsrGrpPageAuthGridColDTO.labelDesc");
}

function goTabPage(pageId)
{
    goCommonTabPage(mgrUsrGrpPageAuthGridColForm, '' , pageId);
}
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

	<html:form action="/mgrUsrGrpPageAuthGridColDetail">
		<html:hidden property="strutsAction" />
		<html:hidden property="mgrUsrGrpPageAuthGridColDTO.pageId"/><!-- Key -->
		<html:hidden property="mgrUsrGrpPageAuthGridColDTO.pggridcolId"/><!-- Key -->
		<html:hidden property="mgrUsrGrpPageAuthGridColDTO.usrgrpId"/><!-- Key -->
		<html:hidden property="mgrUsrGrpPageAuthGridColDTO.authLimitTypeId"/>
		<html:hidden property="mgrUsrGrpPageAuthGridColDTO.ugpgridcolauId"/>
		<html:hidden property="mgrUsrGrpPageAuthGridColDTO.gridId"/>
		<div class="article_box">
			<div class="form_box">
				<!-- COLUMN ID -->
				<div class="field">
					<label><bean:message key="LABEL.fieldId" /></label>
					<div class="input_read">
						<html:text property="mgrUsrGrpPageAuthGridColDTO.columnId" readonly="true" />
					</div>
				</div>
				<!-- Label명 -->
				<div class="field">
					<label><bean:message key="LABEL.labelName" /></label>
					<div class="input_read">
						<html:text property="mgrUsrGrpPageAuthGridColDTO.labelDesc" readonly="true" />
					</div>
				</div>
				<!-- 권한제한 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.authLimitType" /></label>
					<div class="input_box">
						<html:text property="mgrUsrGrpPageAuthGridColDTO.authLimitTypeDesc" tabindex="10" />
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

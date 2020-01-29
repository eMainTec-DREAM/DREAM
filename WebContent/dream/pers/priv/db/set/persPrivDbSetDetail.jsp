 <%--===========================================================================
Program 대시보드(상세) Detail
author  nhkim8548
version $Id: persPrivDbSetDetail.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.pers.priv.db.set.action.PersPrivDbSetDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 대시보드 -->
<title><bean:message key='MENU.DASHBOARD' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */
var filterDbcTypeDescAc;

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
    setTitle("persPrivDbSetDetailDTO.usrDbId", "persPrivDbSetDetailDTO.usrDbDesc");
    //For Save
    setForUpdate(); // 저장시 값 체크
    // 매뉴표시명 자동완성
    acSysDesc("persPrivDbSetDetailDTO.usrDbMenuIsUseDesc","persPrivDbSetDetailDTO.usrDbMenuIsUseId","IS_USE", true);
}

function goInput()
{
    sequenceNextVal('SQAUSRDBMENU_ID');
    
    persPrivDbSetDetailForm.elements['persPrivDbSetDetailDTO.usrDbMenuIsUseId'].value = 'Y';
    persPrivDbSetDetailForm.elements['persPrivDbSetDetailDTO.usrDbMenuIsUseDesc'].value = 'Y';
    valSysDir('persPrivDbSetDetailDTO.usrDbMenuIsUseDesc', 'persPrivDbSetDetailDTO.usrDbMenuIsUseId', 'IS_USE', true);
}

 function setSequenceVal(sequenceVal)
{
	persPrivDbSetDetailForm.elements['persPrivDbSetDetailDTO.usrDbId'].value = sequenceVal;
	persPrivDbSetDetailForm.elements['persPrivDbSetCommonDTO.usrDbId'].value = sequenceVal;
}
 
function goOpen(pageId)
{
	goTabPage(pageId);
}

function goTabPage(pageId)
{
	var form = document.persPrivDbSetDetailForm;
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
    if(ckCreate(currentPageId)) persPrivDbSetDetailForm.strutsAction.value = "<%=PersPrivDbSetDetailAction.DETAIL_INPUT%>";
    else persPrivDbSetDetailForm.strutsAction.value = "<%=PersPrivDbSetDetailAction.DETAIL_UPDATE%>";

		var actionUrl = contextPath + "/persPrivDbSetDetail.do";
		XMLHttpPost(actionUrl, FormQueryString(persPrivDbSetDetailForm), 'afterSave');
	}

	/**
	 * 저장후 호출
	 */
	function afterSave(ajaxXmlDoc) {
		//=====================================
		if (!checkHttpXml(ajaxXmlDoc))
			return;
		//=====================================
		if (parent.findGridRow) parent.findGridRow(persPrivDbSetDetailForm.elements['persPrivDbSetDetailDTO.usrDbId'].value);

		persPrivDbSetDetailForm.elements['persPrivDbSetCommonDTO.usrDbId'].value = persPrivDbSetDetailForm.elements['persPrivDbSetDetailDTO.usrDbId'].value;
		getTopPage().afterSaveAll(currentPageId);
	}
	/**
	 *  Audit trail
	 */
	 function goAudtrailLink()
	 {
	    var objectId = persPrivDbSetDetailForm.elements['persPrivDbSetDetailDTO.usrDbId'].value;
	    var fileName = currentPageId;

	    if(typeof objectId=="undefined") return;

	    goAudTrailList(objectId, fileName);
	 }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/persPrivDbSetDetail">
	<html:hidden property="strutsAction" />
	<html:hidden property="currentPageId"/>
	<html:hidden property="persPrivDbSetCommonDTO.usrDbId"/>
	<html:hidden property="persPrivDbSetDetailDTO.usrDbMenuIsUseId"/>
		<div class="article_box">
			<div class="form_box">
				<!-- 대시보드 ID -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.dashboardId"/></label>
					<div class="input_read">
						<html:text property="persPrivDbSetDetailDTO.usrDbId" tabindex="10" readonly="true" />
					</div>
				</div>
				<!-- 대시보드 제목 -->
				<div class="field_long">
					<label class="check"><bean:message key="LABEL.dashboardTitle" /></label>
					<div class="input_box">
						<html:text property="persPrivDbSetDetailDTO.usrDbDesc" tabindex="20" />
					</div>
				</div>
				<!-- 매뉴 표시명 -->
				<div class="field">
					<label><bean:message key="LABEL.menuDisplayName" /></label>
					<div class="input_box">
						<html:text property="persPrivDbSetDetailDTO.usrDbMenuDesc" tabindex="30" />
					</div>
				</div>
				<!-- 사용 여부 -->
				<div class="field">
					<label><bean:message key="LABEL.isUse" /></label>
					<div class="input_box">
						<html:text property="persPrivDbSetDetailDTO.usrDbMenuIsUseDesc" tabindex="40" />
						<p class="open_spop">
							<a> <span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 비고 -->
				<div class="field_long">
					<label><bean:message key="LABEL.remark" /></label>
					<div class="input_box">
						<html:textarea property="persPrivDbSetDetailDTO.usrDbMenuRemark" styleClass="ta50" tabindex="50" />
					</div>
				</div>
			</div>
			<!-- End of Form_box -->
		</div>
		<!-- End of Article_box -->
	</html:form>
</body>
</html:html>

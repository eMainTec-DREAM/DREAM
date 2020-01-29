 <%--===========================================================================
Interface Detail
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.intf.action.MgrIntfDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 인터페이스 -->
<title><bean:message key='MENU.INTFLOG' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
    setTitle("mgrIntfDetailDTO.intfNo", "mgrIntfDetailDTO.intfDesc");
    //For Save
    setForUpdate();
    //사용여부 자동완성
    acSysDesc("mgrIntfDetailDTO.isUseDesc","mgrIntfDetailDTO.isUse","IS_USE", true);
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQAINTF_ID');
    
    mgrIntfDetailForm.elements['mgrIntfDetailDTO.isUse'].value = 'Y';
    mgrIntfDetailForm.elements['mgrIntfDetailDTO.isUseDesc'].value = 'Y';
    valSysDir('mgrIntfDetailDTO.isUse', 'mgrIntfDetailDTO.isUseDesc', 'IS_USE', true);
}
function setSequenceVal(sequenceVal)
{
    mgrIntfDetailForm.elements['mgrIntfDetailDTO.intfId'].value = sequenceVal;
    mgrIntfDetailForm.elements['mgrIntfCommonDTO.intfId'].value = sequenceVal;
}
/**
 * 수정
 */
function goUpdate()
{
	setDisable('#intfNoDiv');
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
    if(ckCreate(currentPageId)) mgrIntfDetailForm.strutsAction.value = "<%=MgrIntfDetailAction.DETAIL_INPUT%>";
    else mgrIntfDetailForm.strutsAction.value = "<%=MgrIntfDetailAction.DETAIL_UPDATE%>";

	var actionUrl = contextPath + "/mgrIntfDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(mgrIntfDetailForm), 'afterSave');

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
		parent.findGridRow(mgrIntfDetailForm.elements['mgrIntfDetailDTO.intfId'].value);

	mgrIntfDetailForm.elements['mgrIntfCommonDTO.intfId'].value = mgrIntfDetailForm.elements['mgrIntfDetailDTO.intfId'].value;
	getTopPage().afterSaveAll(currentPageId);
	setTitle("mgrIntfDetailDTO.intfNo", "mgrIntfDetailDTO.intfDesc");
	setDisable('#intfNoDiv');
}

function goTabPage(pageId) 
{
	goCommonTabPage(mgrIntfDetailForm, '' , pageId);
}

/**
 * 즉시수신
 */
function goIntfexe(){
	var param = "strutsAction=0"
				+"&mgrIntfCommonDTO.intfId="+mgrIntfDetailForm.elements['mgrIntfCommonDTO.intfId'].value
				+"&mgrIntfCommonDTO.intfNo="+mgrIntfDetailForm.elements['mgrIntfDetailDTO.intfNo'].value
				+"&mgrIntfCommonDTO.intfDesc="+mgrIntfDetailForm.elements['mgrIntfDetailDTO.intfDesc'].value;
	openLayerPopup("mgrIntfParams", param);
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = mgrIntfDetailForm.elements['mgrIntfDetailDTO.intfId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

	<html:form action="/mgrIntfDetail">
		<html:hidden property="strutsAction" />
		<html:hidden property="currentPageId"/>
		<html:hidden property="mgrIntfCommonDTO.intfId" />
		<!-- Key -->
		<html:hidden property="mgrIntfDetailDTO.intfId" />
		<!-- Key -->
		<html:hidden property="mgrIntfDetailDTO.isUse" />
		<div class="article_box">
			<div class="form_box">
				<!-- 인터페이스# -->
				<div class="field" id="intfNoDiv">
					<label class="check"><bean:message key="LABEL.intfNo" /></label>
					<div class="input_box">
						<html:text property="mgrIntfDetailDTO.intfNo" tabindex="10" />
					</div>
				</div>
				<!-- 인터페이스 명 -->
				<div class="field">
					<label><bean:message key="LABEL.intfName" /></label>
					<div class="input_box">
						<html:text property="mgrIntfDetailDTO.intfDesc" tabindex="20" />
					</div>
				</div>
				<!-- 인터페이스 분류 -->
				<div class="field">
					<label><bean:message key="LABEL.intfType" /></label>
					<div class="input_box">
						<html:text property="mgrIntfDetailDTO.intfType" tabindex="30" />
					</div>
				</div>
				<!-- 접속IP/URL -->
				<div class="field">
					<label><bean:message key="LABEL.accUrl" /></label>
					<div class="input_box">
						<html:text property="mgrIntfDetailDTO.accUrl" tabindex="40" />
					</div>
				</div>
				<!-- 접속Port -->
				<div class="field">
					<label><bean:message key="LABEL.accPort" /></label>
					<div class="input_box">
						<html:text property="mgrIntfDetailDTO.accPort" tabindex="50" />
					</div>
				</div>
				<!-- SID/DB -->
				<div class="field">
					<label><bean:message key="LABEL.accName" /></label>
					<div class="input_box">
						<html:text property="mgrIntfDetailDTO.accName" tabindex="60" />
					</div>
				</div>
				<!-- 접속ID -->
				<div class="field">
					<label><bean:message key="LABEL.accUser" /></label>
					<div class="input_box">
						<html:text property="mgrIntfDetailDTO.accUser" tabindex="70" />
					</div>
				</div>
				<!-- 접속비밀번호 -->
				<div class="field">
					<label><bean:message key="LABEL.accPassword" /></label>
					<div class="input_box">
						<html:text property="mgrIntfDetailDTO.accPassword" tabindex="80" />
					</div>
				</div>
				<!-- Client/Site -->
				<div class="field">
					<label><bean:message key="LABEL.accSite" /></label>
					<div class="input_box">
						<html:text property="mgrIntfDetailDTO.accSite" tabindex="90" />
					</div>
				</div>
				<!-- 사용여부 -->
				<div class="field">
					<label><bean:message key="LABEL.isUse" /></label>
					<div class="input_box">
						<html:text property="mgrIntfDetailDTO.isUseDesc" tabindex="100" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 비고 -->
				<div class="field_long">
					<label><bean:message key="LABEL.remark" /></label>
					<div class="input_box">
						<html:textarea styleClass="ta50" property="mgrIntfDetailDTO.remark" tabindex="110" />
					</div>
				</div>
			</div>
			<!-- End of Form_box -->
		</div>
		<!-- End of Article_box -->
	</html:form>
</body>
</html:html>

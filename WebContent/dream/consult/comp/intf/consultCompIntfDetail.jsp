 <%--===========================================================================
Interface Detail
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.comp.intf.action.ConsultCompIntfDetailAction"%>
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

var compAc;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
    setTitle("consultCompIntfDetailDTO.intfNo", "consultCompIntfDetailDTO.intfDesc");
    //For Save
    setForUpdate();
    //사용여부 자동완성
    acSysDesc("consultCompIntfDetailDTO.isUseDesc","consultCompIntfDetailDTO.isUse","IS_USE", true);
    
    //회사명
    compAc = new autoC({"consultCompIntfDetailDTO.compDesc":"description"});
    compAc.setTable("TACOMP");
    compAc.setKeyName("consultCompIntfDetailDTO.compNo");
    compAc.setAcResultMap({
        "consultCompIntfDetailDTO.compNo":"comp_no"
    });
    compAc.init();
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQAINTF_ID');
    
    consultCompIntfDetailForm.elements['consultCompIntfDetailDTO.isUse'].value = 'Y';
    consultCompIntfDetailForm.elements['consultCompIntfDetailDTO.isUseDesc'].value = 'Y';
    valSysDir('consultCompIntfDetailDTO.isUse', 'consultCompIntfDetailDTO.isUseDesc', 'IS_USE', true);
}
function setSequenceVal(sequenceVal)
{
    consultCompIntfDetailForm.elements['consultCompIntfDetailDTO.intfId'].value = sequenceVal;
    consultCompIntfDetailForm.elements['consultCompIntfCommonDTO.intfId'].value = sequenceVal;
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
    if(ckCreate(currentPageId)) consultCompIntfDetailForm.strutsAction.value = "<%=ConsultCompIntfDetailAction.DETAIL_INPUT%>";
    else consultCompIntfDetailForm.strutsAction.value = "<%=ConsultCompIntfDetailAction.DETAIL_UPDATE%>";

	var actionUrl = contextPath + "/consultCompIntfDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(consultCompIntfDetailForm), 'afterSave');

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
		parent.findGridRow(consultCompIntfDetailForm.elements['consultCompIntfDetailDTO.intfId'].value);

	consultCompIntfDetailForm.elements['consultCompIntfCommonDTO.intfId'].value = consultCompIntfDetailForm.elements['consultCompIntfDetailDTO.intfId'].value;
	getTopPage().afterSaveAll(currentPageId);
	setTitle("consultCompIntfDetailDTO.intfNo", "consultCompIntfDetailDTO.intfDesc");
	setDisable('#intfNoDiv');
}

function goTabPage(pageId) 
{
	goCommonTabPage(consultCompIntfDetailForm, '' , pageId);
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

	<html:form action="/consultCompIntfDetail">
		<html:hidden property="strutsAction" />
		<html:hidden property="currentPageId"/>
		<html:hidden property="consultCompIntfCommonDTO.intfId" />	<!-- Key -->
		<html:hidden property="consultCompIntfCommonDTO.compNo" />	
		<html:hidden property="consultCompIntfDetailDTO.compNo" />	
		<html:hidden property="consultCompIntfDetailDTO.intfId" />	<!-- Key -->
		<html:hidden property="consultCompIntfDetailDTO.isUse" />
		<div class="article_box">
			<div class="form_box">
				<!-- 회사명 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.compDesc" /></label>
					<div class="input_box">
						<html:text property="consultCompIntfDetailDTO.compDesc" tabindex="5" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<div class="field">
				</div>
				<!-- 인터페이스# -->
				<div class="field" id="intfNoDiv">
					<label class="check"><bean:message key="LABEL.intfNo" /></label>
					<div class="input_box">
						<html:text property="consultCompIntfDetailDTO.intfNo" tabindex="10" />
					</div>
				</div>
				<!-- 인터페이스 명 -->
				<div class="field">
					<label><bean:message key="LABEL.intfName" /></label>
					<div class="input_box">
						<html:text property="consultCompIntfDetailDTO.intfDesc" tabindex="20" />
					</div>
				</div>
				<!-- 인터페이스 분류 -->
				<div class="field">
					<label><bean:message key="LABEL.intfType" /></label>
					<div class="input_box">
						<html:text property="consultCompIntfDetailDTO.intfType" tabindex="30" />
					</div>
				</div>
				<!-- 접속IP/URL -->
				<div class="field">
					<label><bean:message key="LABEL.accUrl" /></label>
					<div class="input_box">
						<html:text property="consultCompIntfDetailDTO.accUrl" tabindex="40" />
					</div>
				</div>
				<!-- 접속Port -->
				<div class="field">
					<label><bean:message key="LABEL.accPort" /></label>
					<div class="input_box">
						<html:text property="consultCompIntfDetailDTO.accPort" tabindex="50" />
					</div>
				</div>
				<!-- SID/DB -->
				<div class="field">
					<label><bean:message key="LABEL.accName" /></label>
					<div class="input_box">
						<html:text property="consultCompIntfDetailDTO.accName" tabindex="60" />
					</div>
				</div>
				<!-- 접속ID -->
				<div class="field">
					<label><bean:message key="LABEL.accUser" /></label>
					<div class="input_box">
						<html:text property="consultCompIntfDetailDTO.accUser" tabindex="70" />
					</div>
				</div>
				<!-- 접속비밀번호 -->
				<div class="field">
					<label><bean:message key="LABEL.accPassword" /></label>
					<div class="input_box">
						<html:text property="consultCompIntfDetailDTO.accPassword" tabindex="80" />
					</div>
				</div>
				<!-- Client/Site -->
				<div class="field">
					<label><bean:message key="LABEL.accSite" /></label>
					<div class="input_box">
						<html:text property="consultCompIntfDetailDTO.accSite" tabindex="90" />
					</div>
				</div>
				<!-- 사용여부 -->
				<div class="field">
					<label><bean:message key="LABEL.isUse" /></label>
					<div class="input_box">
						<html:text property="consultCompIntfDetailDTO.isUseDesc" tabindex="100" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 비고 -->
				<div class="field_long">
					<label><bean:message key="LABEL.remark" /></label>
					<div class="input_box">
						<html:textarea styleClass="ta50" property="consultCompIntfDetailDTO.remark" tabindex="110" />
					</div>
				</div>
			</div>
			<!-- End of Form_box -->
		</div>
		<!-- End of Article_box -->
	</html:form>
</body>
</html:html>

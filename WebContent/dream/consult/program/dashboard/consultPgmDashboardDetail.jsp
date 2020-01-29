<%--===========================================================================
대시보드 Contents - 상세
author  kim21017
version $Id: consultPgmDashboardDetail.jsp,v 1.5 2014/07/02 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.consult.program.dashboard.action.ConsultPgmDashboardDetailAction"%>
<html:html>
<head>
<title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">

//자동완성 변수
var langAc;
var pageAc;


function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("consultPgmDashboardDetailDTO.dbContentsTypeDesc","consultPgmDashboardDetailDTO.dbContentsDesc");
	//For Save
	setForUpdate();
	
	acSysDesc("consultPgmDashboardDetailDTO.dbContentsTypeDesc","consultPgmDashboardDetailDTO.dbContentsTypeId","DBCONTENTS_TYPE", true);
	acSysDesc("consultPgmDashboardDetailDTO.dbContentsWidthDesc","consultPgmDashboardDetailDTO.dbContentsWidthId","DBCONTENTS_WIDTH", true);
	acSysDesc("consultPgmDashboardDetailDTO.isUseDesc","consultPgmDashboardDetailDTO.isUseId","IS_USE", true);
	
	pageAc = new autoC({"consultPgmDashboardDetailDTO.pageDesc":"description"});
	pageAc.setTable("TAPAGE");
	pageAc.setAcResultMap({
		"consultPgmDashboardDetailDTO.pageId":"page_id",
		"consultPgmDashboardDetailDTO.fileName":"file_name"
	});
	pageAc.setKeyName("consultPgmDashboardDetailDTO.pageId");
	pageAc.init();
	
	langAc = new autoC({"consultPgmDashboardDetailDTO.keyName":"key_name"});
	langAc.setTable("TALANG");
	langAc.setAcResultMap({
		"consultPgmDashboardDetailDTO.keyType":"key_type",
		"consultPgmDashboardDetailDTO.keyNo":"key_no"
	});
	langAc.setAcConditionMap({
		"key_type":"LABEL"
	});
	langAc.setKeyName("consultPgmDashboardDetailDTO.keyName");
	langAc.init();
	
}
function goInput()
{
	sequenceNextVal('SQADBCONTENTS_ID');
	
}
function setSequenceVal(sequenceVal)
{
	consultPgmDashboardDetailForm.elements['consultPgmDashboardDetailDTO.dbContentsId'].value = sequenceVal;
	consultPgmDashboardDetailForm.elements['consultPgmDashboardCommonDTO.dbContentsId'].value = sequenceVal;
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
	if(ckCreate(currentPageId)) consultPgmDashboardDetailForm.strutsAction.value = "<%=ConsultPgmDashboardDetailAction.DETAIL_INPUT%>";
	else consultPgmDashboardDetailForm.strutsAction.value = "<%=ConsultPgmDashboardDetailAction.DETAIL_UPDATE%>";
	
	
	var actionUrl = contextPath + "/consultPgmDashboardDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(consultPgmDashboardDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(consultPgmDashboardDetailForm.elements['consultPgmDashboardDetailDTO.dbContentsId'].value);
    
    consultPgmDashboardDetailForm.elements['consultPgmDashboardCommonDTO.dbContentsId'].value = consultPgmDashboardDetailForm.elements['consultPgmDashboardDetailDTO.dbContentsId'].value;
	
    getTopPage().afterSaveAll(currentPageId);
}
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/consultPgmDashboardDetail" >
		<html:hidden property="strutsAction"/>
		<html:hidden property="consultPgmDashboardCommonDTO.dbContentsId"/><!-- Key -->
		<html:hidden property="consultPgmDashboardDetailDTO.dbContentsTypeId" />
		<html:hidden property="consultPgmDashboardDetailDTO.keyType" />
		<html:hidden property="consultPgmDashboardDetailDTO.keyNo" />
		<html:hidden property="consultPgmDashboardDetailDTO.pageId" />
		<html:hidden property="consultPgmDashboardDetailDTO.dbContentsWidthId" />
		<html:hidden property="consultPgmDashboardDetailDTO.isUseId" />
		<div class="article_box" id="detailBox">
			<div class="form_box">
				<div class="field">
					<label><bean:message key="LABEL.contentsNo"/></label>
					<div class="input_read">
						<html:text property="consultPgmDashboardDetailDTO.dbContentsId" tabindex="10" readonly="true"/>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.contentsType"/></label>
					<div class="input_box">
						<html:text property="consultPgmDashboardDetailDTO.dbContentsTypeDesc" tabindex="20"/>
						<p class="open_spop">
							<a><span>조회</span></a>
						</p>
					</div>
				</div>
				<div class="field_long">
					<label class="check"><bean:message key="LABEL.contentsName"/></label>
					<div class="input_box">
						<html:text property="consultPgmDashboardDetailDTO.dbContentsDesc" tabindex="30"/>
					</div>
				</div>
				<div class="field">
					<label class="check"><bean:message key="LABEL.keyName"/></label>
					<div class="input_box">
						<html:text property="consultPgmDashboardDetailDTO.keyName" tabindex="40"/>
						<p class="open_spop">
							<a><span>조회</span></a>
						</p>
					</div>
				</div>
				<div class="field">
					<label class="check"><bean:message key="LABEL.jspFile"/></label>
					<div class="input_box">
						<html:text property="consultPgmDashboardDetailDTO.pageDesc" tabindex="50"/>
						<p class="open_spop">
							<a><span>조회</span></a>
						</p>
					</div>
				</div>
				<div class="field">
					<label class="check"><bean:message key="LABEL.width"/></label>
					<div class="input_box">
						<html:text property="consultPgmDashboardDetailDTO.dbContentsWidthDesc" tabindex="60"/>
						<p class="open_spop">
							<a><span>조회</span></a>
						</p>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.image"/></label>
					<div class="input_box">
						<html:text property="consultPgmDashboardDetailDTO.imageFile" tabindex="70"/>
					</div>
				</div>
				<div class="field">
					<label class="check"><bean:message key="LABEL.isUse"/></label>
					<div class="input_box">
						<html:text property="consultPgmDashboardDetailDTO.isUseDesc" tabindex="80"/>
						<p class="open_spop">
							<a><span>조회</span></a>
						</p>
					</div>
				</div>
				<div class="field_long">
					<label><bean:message key="LABEL.remark"/></label>
					<div class="input_box">
						<html:textarea property="consultPgmDashboardDetailDTO.remark" styleClass="ta50" tabindex="90" />
					</div>
				</div>
			</div> <!-- End of Article_box -->
		</div>
	</html:form>
</body>
</html:html>

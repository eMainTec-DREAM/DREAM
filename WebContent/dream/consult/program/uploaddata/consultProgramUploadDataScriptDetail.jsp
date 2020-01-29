<%--===========================================================================
엑셀 참조데이타
author  ghlee
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.program.uploaddata.action.ConsultProgramUploadDataScriptAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 컬럼 -->
<title><bean:message key='PAGE.consultProgramUploadDataScriptDetail'/></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();

    setTitle("consultProgramUploadDataScriptDTO.description");
    
    //For Save
    setForUpdate();
    
  	//사용여부 컬럼여부
    acSysDesc("consultProgramUploadDataScriptDTO.isUse","consultProgramUploadDataScriptDTO.isUse","IS_USE", true);
  	
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQAEXCELEXSCRIPT_ID');
    
    consultProgramUploadDataScriptForm.elements['consultProgramUploadDataScriptDTO.excelTabId'].value = consultProgramUploadDataScriptForm.elements['consultProgramUploadDataDTO.excelTabId'].value;
    
    consultProgramUploadDataScriptForm.elements['consultProgramUploadDataScriptDTO.isUse'].value = "Y";
}
function setSequenceVal(sequenceVal)
{
    consultProgramUploadDataScriptForm.elements['consultProgramUploadDataScriptDTO.excelExScriptId'].value = sequenceVal;
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
    if(ckCreate(currentPageId)) consultProgramUploadDataScriptForm.strutsAction.value = "<%=ConsultProgramUploadDataScriptAction.DETAIL_INPUT%>";
    else consultProgramUploadDataScriptForm.strutsAction.value = "<%=ConsultProgramUploadDataScriptAction.DETAIL_UPDATE%>";
    
	var actionUrl = contextPath + "/consultProgramUploadDataScriptDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(consultProgramUploadDataScriptForm), 'afterSave');
    
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(consultProgramUploadDataScriptForm.elements['consultProgramUploadDataScriptDTO.excelExScriptId'].value);
    getTopPage().afterSaveAll(currentPageId);
    setTitle("consultProgramUploadDataScriptDTO.description");
    
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

<html:form action="/consultProgramUploadDataScriptDetail.do" >
<html:hidden property="strutsAction"/>
<html:hidden property="consultProgramUploadDataDTO.excelTabId"/>
<html:hidden property="consultProgramUploadDataScriptDTO.excelTabId"/>
<html:hidden property="consultProgramUploadDataScriptDTO.excelExScriptId"/><!-- Key -->

	<div class="article_box">
		<div class="form_box">
			<!-- Sheet 순서 -->
			<div class="field">
				<label><bean:message key="LABEL.sheetSeq"/></label>
				<div class="input_box">
					<html:text property="consultProgramUploadDataScriptDTO.ordNo" tabindex="10"/>
				</div>
			</div>
			<!-- 제목 -->
			<div class="field_long">
				<label><bean:message key="LABEL.title"/></label>
				<div class="input_box">
					<html:text property="consultProgramUploadDataScriptDTO.description" tabindex="20"/>
				</div>
			</div>
			<!-- Sheet명 -->
			<div class="field">
				<label><bean:message key="LABEL.sheetName"/></label>
				<div class="input_box">
					<html:text property="consultProgramUploadDataScriptDTO.sheetName" tabindex="30"/>
				</div>
			</div>
			<!-- 사용여부 -->
			<div class="field">
				<label><bean:message key="LABEL.isUse"/></label>
				<div class="input_box">
					<html:text property="consultProgramUploadDataScriptDTO.isUse" tabindex="40"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- script -->
			<div class="field_long">
				<label><bean:message key="LABEL.script"/></label>
				<div class="input_box">
					<html:textarea property="consultProgramUploadDataScriptDTO.script" styleClass="ta50" tabindex="50" />
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="consultProgramUploadDataScriptDTO.remark" styleClass="ta50" tabindex="60" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>

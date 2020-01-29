<%--===========================================================================
컬럼
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.program.uploaddata.action.ConsultProgramUploadDataColAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 컬럼 -->
<title><bean:message key='LABEL.prompt'/></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();

    setTitle("consultProgramUploadDataColDTO.excelColComments");
    
    //For Save
    setForUpdate();
    
  	//컬럼타입
    acSysDesc("consultProgramUploadDataColDTO.tableColType","consultProgramUploadDataColDTO.tableColTypeId","DATA_TYPE", true);
    
  	//Editable 컬럼여부
    acSysDesc("consultProgramUploadDataColDTO.isEditable","consultProgramUploadDataColDTO.isEditableId","IS_USE", true);
  	//사용여부 컬럼여부
    acSysDesc("consultProgramUploadDataColDTO.isUse","consultProgramUploadDataColDTO.isUse","IS_USE", true);
  	
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQAEXCELCOLUMN_ID');
    
    consultProgramUploadDataColForm.elements['consultProgramUploadDataColDTO.excelTabId'].value = consultProgramUploadDataColForm.elements['consultProgramUploadDataDTO.excelTabId'].value;
    
    // 컬럼타입 STRING 세팅
    consultProgramUploadDataColForm.elements['consultProgramUploadDataColDTO.tableColTypeId'].value = "STRING";
    consultProgramUploadDataColForm.elements['consultProgramUploadDataColDTO.tableColType'].value = "STRING";
    valSysDir('consultProgramUploadDataColDTO.tableColTypeId', 'consultProgramUploadDataColDTO.tableColType', 'DATA_TYPE', true);
    
    //컬럼길이 100 세팅
    consultProgramUploadDataColForm.elements['consultProgramUploadDataColDTO.tableColSize'].value = "100";
    
 	// Editable 컬럼여부 "Y" 세팅
    consultProgramUploadDataColForm.elements['consultProgramUploadDataColDTO.isEditableId'].value = "Y";
    consultProgramUploadDataColForm.elements['consultProgramUploadDataColDTO.isEditable'].value = "Y";
    consultProgramUploadDataColForm.elements['consultProgramUploadDataColDTO.isUse'].value = "Y";
    valSysDir('consultProgramUploadDataColDTO.isEditableId', 'consultProgramUploadDataColDTO.isEditable', 'IS_USE', true);
    
}
function setSequenceVal(sequenceVal)
{
    consultProgramUploadDataColForm.elements['consultProgramUploadDataColDTO.excelColId'].value = sequenceVal;
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
    if(ckCreate(currentPageId)) consultProgramUploadDataColForm.strutsAction.value = "<%=ConsultProgramUploadDataColAction.DETAIL_INPUT%>";
    else consultProgramUploadDataColForm.strutsAction.value = "<%=ConsultProgramUploadDataColAction.DETAIL_UPDATE%>";
    
	var actionUrl = contextPath + "/consultProgramUploadDataColDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(consultProgramUploadDataColForm), 'afterSave');
    
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(consultProgramUploadDataColForm.elements['consultProgramUploadDataColDTO.excelColId'].value);
    getTopPage().afterSaveAll(currentPageId);
    setTitle("consultProgramUploadDataColDTO.excelColComments");
    
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

<html:form action="/consultProgramUploadDataColDetail.do" >
<html:hidden property="strutsAction"/>
<html:hidden property="consultProgramUploadDataDTO.excelTabId"/>
<html:hidden property="consultProgramUploadDataColDTO.excelTabId"/>
<html:hidden property="consultProgramUploadDataColDTO.excelColId"/><!-- Key -->
<html:hidden property="consultProgramUploadDataColDTO.tableColTypeId"/>
<html:hidden property="consultProgramUploadDataColDTO.isKeyId"/>
<html:hidden property="consultProgramUploadDataColDTO.isSystemId"/>
<html:hidden property="consultProgramUploadDataColDTO.isEditableId"/>
<html:hidden property="consultProgramUploadDataColDTO.isSystemDisplayId"/>

	<div class="article_box">
		<div class="form_box">
			<!-- Excel 컬럼명 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.excelColName"/></label>
				<div class="input_box">
					<html:text property="consultProgramUploadDataColDTO.excelColName" tabindex="10"/>
				</div>
			</div>
			<!-- 컬럼설명 -->
			<div class="field">
				<label><bean:message key="LABEL.colDesc"/></label>
				<div class="input_box">
					<html:text property="consultProgramUploadDataColDTO.excelColComments" tabindex="20"/>
				</div>
			</div>
			<!-- 컬럼명 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.columnName"/></label>
				<div class="input_box">
					<html:text property="consultProgramUploadDataColDTO.tableColName" tabindex="30"/>
				</div>
			</div>
			<!-- 순서 -->
			<div class="field">
				<label><bean:message key="LABEL.order"/></label>
				<div class="input_box">
					<html:text property="consultProgramUploadDataColDTO.ordNo" tabindex="70"/>
				</div>
			</div>
			<!-- 컬럼타입 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.colType"/></label>
				<div class="input_box">
					<html:text property="consultProgramUploadDataColDTO.tableColType" tabindex="40"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 컬럼길이 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.columnSize"/></label>
				<div class="input_box">
					<html:text property="consultProgramUploadDataColDTO.tableColSize" tabindex="50"/>
				</div>
			</div>
			<!-- Editable컬럼여부 -->
			<div class="field">
				<label><bean:message key="LABEL.isEditCol"/></label>
				<div class="input_box">
					<html:text property="consultProgramUploadDataColDTO.isEditable" tabindex="60"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 사용여부 -->
			<div class="field">
				<label><bean:message key="LABEL.isUse"/></label>
				<div class="input_box">
					<html:text property="consultProgramUploadDataColDTO.isUse" tabindex="70"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="consultProgramUploadDataColDTO.remark" styleClass="ta50" tabindex="100" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>

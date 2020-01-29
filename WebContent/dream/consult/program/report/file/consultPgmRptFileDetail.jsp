<%--===========================================================================
출력물 설정 파일 - 상세 
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="dream.consult.program.report.file.action.ConsultPgmRptFileAction" %>
<html>
<head>
<!-- Report List -->
<title><bean:message key="TAB.consultPgmRptFileDetail"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
	setTitle("consultPgmRptFileDTO.fileDesc");
	
	setForUpdate();
	
	// Report 종류
    acSysDesc("consultPgmRptFileDTO.reportFileType","consultPgmRptFileDTO.reportFileTypeId","RPTFILE_TYPE",true);
    // 사용여부
    acSysDesc("consultPgmRptFileDTO.isUse","consultPgmRptFileDTO.isUse","IS_USE",true);
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQARPTFILE_ID');
	consultPgmRptFileForm.elements['consultPgmRptFileDTO.isUse'].value = 'Y';
}

function setSequenceVal(sequenceVal)
{
	consultPgmRptFileForm.elements['consultPgmRptFileDTO.rptFileId'].value = sequenceVal;
}

function goUpdate()
{
	
}

function goSave()
{
	validFileDesc();
}

function validFileDesc()
{
	var actionUrl = contextPath + "/consultPgmRptFileDetail.do";
	var param =  "&strutsAction=" + '<%= ConsultPgmRptFileAction.DETAIL_CHECK %>'
	          +  "&" + FormQueryString(consultPgmRptFileForm);
	XMLHttpPostVal(actionUrl, param, 'afterValidFileDesc');	
}

var isValid;
function afterValidFileDesc(ajaxXmlDoc)
{
	isValid = '0';
	isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
	if(isValid == '0')
    {
		goSaveAfterValid();
    }
	else
	{
    	closeModal();
		alertMessage1("<bean:message key='MESSAGE.MSG0009'/>");
		consultPgmRptFileForm.elements['consultPgmRptFileDTO.fileDesc'].value = '';
	}
}

function goSaveAfterValid()
{
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)) consultPgmRptFileForm.strutsAction.value = '<%=ConsultPgmRptFileAction.DETAIL_INPUT%>';
	else consultPgmRptFileForm.strutsAction.value = '<%= ConsultPgmRptFileAction.DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/consultPgmRptFileDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(consultPgmRptFileForm), 'afterSave');	
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(consultPgmRptFileForm.elements['consultPgmRptFileDTO.rptFileId'].value);
    
    getTopPage().afterSaveAll(currentPageId);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/consultPgmRptFileDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="consultPgmRptDTO.rptListId"/>
<html:hidden property="consultPgmRptFileDTO.rptListId"/>
<html:hidden property="consultPgmRptFileDTO.rptFileId"/>
<html:hidden property="consultPgmRptFileDTO.reportFileTypeId"/>
<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- File명 -->
	        <div class="field">
		    	<label class="check">File명</label>
	            <div class="input_box">
	            	<html:text property="consultPgmRptFileDTO.fileDesc" tabindex="10"/>
	            </div>
            </div>
			<!-- Report 종류 -->
			<div class="field">
			    <label>Report 종류</label>
			    <div class="input_box">
					<html:text property="consultPgmRptFileDTO.reportFileType" tabindex="20"/>
					<p class="open_spop"><a><span>조회</span></a></p>
			    </div>
			</div>
			<!-- 서버주소 -->
	        <div class="field_long">
		    	<label>서버 주소</label>
	            <div class="input_box">
	            	<html:text property="consultPgmRptFileDTO.serverUrl" tabindex="30"/>
	            </div>
            </div>
           	<!-- Report File -->
            <div class="field">
            	<label class="check">Report File</label>
            	<div class="input_box">
            		<html:text property="consultPgmRptFileDTO.reportFileName" tabindex="40"/>
            	</div>
           	</div>
           	<!-- Query File -->
            <div class="field">
            	<label class="check">Query File</label>
            	<div class="input_box">
            		<html:text property="consultPgmRptFileDTO.queryFileName" tabindex="50"/>
            	</div>
           	</div>
			<!-- 작성일자 -->
			<div class="field">
			    <label>작성일자</label>
			    <div class="input_box">
					<html:text property="consultPgmRptFileDTO.repRegDate" tabindex="60"/>
					<p class="open_calendar"><a><span>조회</span></a></p>
			    </div>
			</div>
			<!-- 사용여부 -->
			<div class="field">
			    <label>사용여부</label>
			    <div class="input_box">
					<html:text property="consultPgmRptFileDTO.isUse" tabindex="70"/>
					<p class="open_spop"><a><span>조회</span></a></p>
			    </div>
			</div>
			<!-- File 작성자 -->
			<div class="field">
			    <label>File 작성자</label>
			    <div class="input_box">
					<html:text property="consultPgmRptFileDTO.writeBy" tabindex="80"/>
			    </div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
            	<label>비고</label>
                <div class="input_box">
                	<html:textarea property="consultPgmRptFileDTO.remark" styleClass="ta50" tabindex="90" />
                </div>
            </div>
		</div>
	</div><!--article_box end-->
</html:form> 
</body>
</html>
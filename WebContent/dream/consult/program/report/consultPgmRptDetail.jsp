<%--===========================================================================
Report List - 상세 
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.consult.program.report.action.ConsultPgmRptAction" %>
<%@ page import="common.bean.User"%>
<%
    User loginUser = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html>
<head>
<title><bean:message key='TAB.consultPgmRptDetail' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">

/** 자동완성 변수  */

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate(); 
	
	setTitle("consultPgmRptDTO.reportNo", "consultPgmRptDTO.reportName");
	
	setForUpdate();
	
	// 사용여부
	acSysDesc("consultPgmRptDTO.isUse","consultPgmRptDTO.isUse","IS_USE", true);
	
}
	

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQARPTLIST_ID');
    
    consultPgmRptForm.elements['consultPgmRptDTO.isUse'].value = 'Y';
    valSysDir('consultPgmRptDTO.isUse', 'consultPgmRptDTO.isUse', 'IS_USE', true);
}

function setSequenceVal(sequenceVal)
{
    consultPgmRptForm.elements['consultPgmRptDTO.rptListId'].value = sequenceVal;
}
/**
 * 수정
 */
function goUpdate()
{

}
 
/**
* Tab 이동시 호출
*/
function goTabPage(pageId)
{
	var form = document.consultPgmRptForm;

	goCommonTabPage(form, '' , pageId);
}

/**
 * 저장
 */ 
function goSave()
{
	validReportNo();
}

function validReportNo()
{
	var actionUrl = contextPath + "/consultPgmRptDetail.do";
	var param =  "&strutsAction=" + '<%= ConsultPgmRptAction.DETAIL_CHECK %>'
	          +  "&" + FormQueryString(consultPgmRptForm);
	XMLHttpPostVal(actionUrl, param, 'afterValidReportNo');	
}

var isValid;
function afterValidReportNo(ajaxXmlDoc)
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
		consultPgmRptForm.elements['consultPgmRptDTO.reportNo'].value = '';
	}
}

function goSaveAfterValid()
{
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
    
    //strutsAction 셋팅.
    if(ckCreate(currentPageId)) consultPgmRptForm.strutsAction.value = '<%=ConsultPgmRptAction.DETAIL_INPUT%>';
	else consultPgmRptForm.strutsAction.value = "<%=ConsultPgmRptAction.DETAIL_UPDATE%>";
    
	var actionUrl = contextPath + "/consultPgmRptDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(consultPgmRptForm), 'afterSave');
    
}


/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(consultPgmRptForm.elements['consultPgmRptDTO.rptListId'].value);

    getTopPage().afterSaveAll(currentPageId);
    
    setTitle("consultPgmRptDTO.reportNo", "consultPgmRptDTO.reportName");
}
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/consultPgmRptDetail" >
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="consultPgmRptDTO.rptListId" /><!-- key -->
<div class="article_box">
	<div class="form_box">
		<!-- 출력물#  -->
		<div class="field">
			<label class="check">출력물#</label>
			<div class="input_box">
				<html:text property="consultPgmRptDTO.reportNo" tabindex="10"/>
			</div>
		</div>
		<!-- 사용여부 -->
		<div class="field">
			<label>사용여부</label>
			<div class="input_box">
				<html:text property="consultPgmRptDTO.isUse" tabindex="20"/>
				<p class="open_spop"><a><span>조회</span></a></p>
			</div>
		</div>
		<!-- 출력물 명 -->
		<div class="field_long">
			<label class="check">출력물 명</label>
			<div class="input_box">
				<html:text property="consultPgmRptDTO.reportName" tabindex="30"/>
			</div>
		</div>
		<!-- 비고 -->
		<div class="field_long">
			<label>비고</label>
			<div class="input_box">
				<html:textarea property="consultPgmRptDTO.remark" styleClass="ta50" tabindex="110" />
			</div>
		</div>
	</div> <!-- End of Form_box -->
</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
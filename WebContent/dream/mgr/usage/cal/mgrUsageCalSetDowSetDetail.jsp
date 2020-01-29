<%--===========================================================================
가동시간설정 - 요일별설정 상세
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
<%@page import="dream.mgr.usage.cal.action.MgrUsageCalSetDowSetAction"%>
<html>
<head>
<!-- 요일-->
<title><bean:message key="LABEL.day2"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	if(ckCreate(currentPageId)){
		setEnable(document.getElementById("dayDiv"));
		goInput();
	}else{
		setDisable(document.getElementById("dayDiv"));
	}
	
	setTitle("mgrUsageCalSetDowSetDTO.dowDesc");
	setForUpdate();
	
	// 요일
    acSysDesc("mgrUsageCalSetDowSetDTO.dowDesc","mgrUsageCalSetDowSetDTO.dow","DOW", true);
	
	// 사용여부
    acSysDesc("mgrUsageCalSetDowSetDTO.isUse","mgrUsageCalSetDowSetDTO.isUse","IS_USE", true);
	
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAEQLOCDOWRUN_ID');
	mgrUsageCalSetDowSetForm.elements['mgrUsageCalSetDowSetDTO.lnWrkListId'].value = mgrUsageCalSetDowSetForm.elements['mgrUsageCalSetDTO.lnWrkListId'].value;
	mgrUsageCalSetDowSetForm.elements['mgrUsageCalSetDowSetDTO.isUse'].value = 'Y';
}

function setSequenceVal(sequenceVal)
{
	mgrUsageCalSetDowSetForm.elements['mgrUsageCalSetDowSetDTO.eqLocDowRunId'].value = sequenceVal;
}

function validDay(){
	var actionUrl = contextPath + "/mgrUsageCalSetDowSetDetail.do";
	var param =  "&strutsAction=" + '<%= MgrUsageCalSetDowSetAction.LINE_DOW_DAY_CHECK %>'
	          +  "&" + FormQueryString(mgrUsageCalSetDowSetForm);
	XMLHttpPostVal(actionUrl, param, 'afterValidDay');
}

var isValid;
function afterValidDay(ajaxXmlDoc)
{
	isValid = '0';
	isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
	if(isValid != '0')
    {
    	closeModal();
		alertMessage1("<bean:message key='MESSAGE.MSG0009'/>");
		mgrUsageCalSetDowSetForm.elements['mgrUsageCalSetDowSetDTO.dow'].value = '';
		mgrUsageCalSetDowSetForm.elements['mgrUsageCalSetDowSetDTO.dowDesc'].value = '';
    }
}

function goSave(){
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)){

		//중복 요일 체크
		validDay();
		if(isValid!='0') return;
		
		mgrUsageCalSetDowSetForm.strutsAction.value = '<%=MgrUsageCalSetDowSetAction.LINE_DOW_DETAIL_INPUT%>';
	}else{
		mgrUsageCalSetDowSetForm.strutsAction.value = '<%= MgrUsageCalSetDowSetAction.LINE_DOW_DETAIL_UPDATE %>';
	}
	
	var actionUrl = contextPath + "/mgrUsageCalSetDowSetDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(mgrUsageCalSetDowSetForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
//     parent.goSearch();
    if (parent.findGridRow)	parent.findGridRow(mgrUsageCalSetDowSetForm.elements['mgrUsageCalSetDowSetDTO.eqLocDowRunId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/mgrUsageCalSetDowSetDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="mgrUsageCalSetDTO.lnWrkListId"/><!-- Key -->
<%-- 	<html:hidden property="mgrUsageCalSetDTO.compNo"/><!-- Key --> --%>
	<html:hidden property="mgrUsageCalSetDowSetDTO.eqLocDowRunId"/>
	<html:hidden property="mgrUsageCalSetDowSetDTO.compNo"/>
	<html:hidden property="mgrUsageCalSetDowSetDTO.lnWrkListId"/>
	<html:hidden property="mgrUsageCalSetDowSetDTO.dow"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 요일 -->
			<div class="field" id="dayDiv">
				<label><bean:message key="LABEL.day2"/></label>
				<div class="input_box">
					<html:text property="mgrUsageCalSetDowSetDTO.dowDesc" tabindex="10"/>
					<p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
				</div>
			</div>
			<!-- 낮 가동시간 -->
			<div class="field">
				<label><bean:message key="LABEL.dayRunTime"/></label>
				<div class="input_box">
					<html:text property="mgrUsageCalSetDowSetDTO.dayRunTime" tabindex="20" styleClass="num"/>
				</div>
			</div>
			<!-- 저녁 가동시간 -->
			<div class="field">
				<label><bean:message key="LABEL.nightRunTime"/></label>
				<div class="input_box">
					<html:text property="mgrUsageCalSetDowSetDTO.nightRunTime" tabindex="30" styleClass="num"/>
				</div>
			</div>
			<!-- 새벽 가동시간 -->
			<div class="field">
				<label><bean:message key="LABEL.extraRunTime"/></label>
				<div class="input_box">
					<html:text property="mgrUsageCalSetDowSetDTO.extraRunTime" tabindex="40" styleClass="num"/>
				</div>
			</div>
			<!-- 사용횟수 -->
			<div class="field">
				<label><bean:message key="LABEL.ucnt"/></label>
				<div class="input_box">
					<html:text property="mgrUsageCalSetDowSetDTO.ucnt" tabindex="40" styleClass="num"/>
				</div>
			</div>
			<!-- 조회순서 -->
			<div class="field">
				<label><bean:message key="LABEL.ordNo"/></label>
				<div class="input_box">
					<html:text property="mgrUsageCalSetDowSetDTO.ordNo" tabindex="50"/>
				</div>
			</div>
			<div class="field">
				<label><bean:message key="LABEL.isUse"/></label>
				<div class="input_box">
					<html:text property="mgrUsageCalSetDowSetDTO.isUse" tabindex="60"/>
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>							
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="mgrUsageCalSetDowSetDTO.remark" styleClass="ta50" tabindex="900" />
				</div>
			</div>
		</div><!--article_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>
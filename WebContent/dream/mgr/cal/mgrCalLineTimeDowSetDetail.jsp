<%--===========================================================================
가동시간설정 - 요일별설정 상세
author  kim21017
version $Id: mgrCalLineTimeDowSetDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%> 
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.mgr.cal.action.MgrCalLineTimeDowSetAction"%>
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
	
	setTitle("mgrCalLineTimeDowSetDTO.dowDesc");
	setForUpdate();
	
	// 요일
    acSysDesc("mgrCalLineTimeDowSetDTO.dowDesc","mgrCalLineTimeDowSetDTO.dow","DOW", true);
	
	// 사용여부
    acSysDesc("mgrCalLineTimeDowSetDTO.isUse","mgrCalLineTimeDowSetDTO.isUse","IS_USE", true);
	
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAEQLOCDOWRUN_ID');
	mgrCalLineTimeDowSetForm.elements['mgrCalLineTimeDowSetDTO.lnWrkListId'].value = mgrCalLineTimeDowSetForm.elements['mgrCalLineTimeSetDTO.lnWrkListId'].value;
// 	mgrCalLineTimeDowSetForm.elements['mgrCalLineTimeDowSetDTO.compNo'].value = mgrCalLineTimeDowSetForm.elements['mgrCalLineTimeSetDTO.compNo'].value;
	mgrCalLineTimeDowSetForm.elements['mgrCalLineTimeDowSetDTO.isUse'].value = 'Y';
}

function setSequenceVal(sequenceVal)
{
	mgrCalLineTimeDowSetForm.elements['mgrCalLineTimeDowSetDTO.eqLocDowRunId'].value = sequenceVal;
}

function validDay(){
	var actionUrl = contextPath + "/mgrCalLineTimeDowSetDetail.do";
	var param =  "&strutsAction=" + '<%= MgrCalLineTimeDowSetAction.LINE_DOW_DAY_CHECK %>'
	          +  "&" + FormQueryString(mgrCalLineTimeDowSetForm);
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
		mgrCalLineTimeDowSetForm.elements['mgrCalLineTimeDowSetDTO.dow'].value = '';
		mgrCalLineTimeDowSetForm.elements['mgrCalLineTimeDowSetDTO.dowDesc'].value = '';
    }
}

function goSave(){
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	

// 	mgrCalLineTimeDowSetForm.elements['mgrCalLineTimeSetDTO.compNo'].value = 
// 		parent.parent.mgrCalLineTimeSetForm.elements['mgrCalLineTimeSetDTO.compNo'].value;
	
	if(ckCreate(currentPageId)){

		//중복 요일 체크
		validDay();
		if(isValid!='0') return;
		
		mgrCalLineTimeDowSetForm.strutsAction.value = '<%=MgrCalLineTimeDowSetAction.LINE_DOW_DETAIL_INPUT%>';
	}else{
		mgrCalLineTimeDowSetForm.strutsAction.value = '<%= MgrCalLineTimeDowSetAction.LINE_DOW_DETAIL_UPDATE %>';
	}
	
	var actionUrl = contextPath + "/mgrCalLineTimeDowSetDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(mgrCalLineTimeDowSetForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
//     parent.goSearch();
    if (parent.findGridRow)	parent.findGridRow(mgrCalLineTimeDowSetForm.elements['mgrCalLineTimeDowSetDTO.compNo'].value, mgrCalLineTimeDowSetForm.elements['mgrCalLineTimeDowSetDTO.eqLocDowRunId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/mgrCalLineTimeDowSetDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="mgrCalLineTimeSetDTO.lnWrkListId"/><!-- Key -->
<%-- 	<html:hidden property="mgrCalLineTimeSetDTO.compNo"/><!-- Key --> --%>
	<html:hidden property="mgrCalLineTimeDowSetDTO.eqLocDowRunId"/>
	<html:hidden property="mgrCalLineTimeDowSetDTO.compNo"/>
	<html:hidden property="mgrCalLineTimeDowSetDTO.lnWrkListId"/>
	<html:hidden property="mgrCalLineTimeDowSetDTO.dow"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 요일 -->
			<div class="field" id="dayDiv">
				<label><bean:message key="LABEL.day2"/></label>
				<div class="input_box">
					<html:text property="mgrCalLineTimeDowSetDTO.dowDesc" tabindex="10"/>
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
					<html:text property="mgrCalLineTimeDowSetDTO.dayRunTime" tabindex="20" styleClass="num"/>
				</div>
			</div>
			<!-- 저녁 가동시간 -->
			<div class="field">
				<label><bean:message key="LABEL.nightRunTime"/></label>
				<div class="input_box">
					<html:text property="mgrCalLineTimeDowSetDTO.nightRunTime" tabindex="30" styleClass="num"/>
				</div>
			</div>
			<!-- 새벽 가동시간 -->
			<div class="field">
				<label><bean:message key="LABEL.extraRunTime"/></label>
				<div class="input_box">
					<html:text property="mgrCalLineTimeDowSetDTO.extraRunTime" tabindex="40" styleClass="num"/>
				</div>
			</div>
			<!-- 사용횟수 -->
			<div class="field">
				<label><bean:message key="LABEL.ucnt"/></label>
				<div class="input_box">
					<html:text property="mgrCalLineTimeDowSetDTO.ucnt" tabindex="40" styleClass="num"/>
				</div>
			</div>
			<!-- 조회순서 -->
			<div class="field">
				<label><bean:message key="LABEL.ordNo"/></label>
				<div class="input_box">
					<html:text property="mgrCalLineTimeDowSetDTO.ordNo" tabindex="50"/>
				</div>
			</div>
			<div class="field">
				<label><bean:message key="LABEL.isUse"/></label>
				<div class="input_box">
					<html:text property="mgrCalLineTimeDowSetDTO.isUse" tabindex="60"/>
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
		</div><!--article_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>
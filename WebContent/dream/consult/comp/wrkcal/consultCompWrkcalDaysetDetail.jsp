<%--===========================================================================
휴무일 설정 - 상세
author  kim21017
version $Id: consultCompWrkcalDaysetDetail.jsp,v 1.5 2014/07/02 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.consult.comp.wrkcal.action.ConsultCompWrkcalDaysetDetailAction"%>
<html:html>
<head>
<!-- 회사설정 -->
<title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	//For Save
	setForUpdate();
	
	acSysDesc("consultCompWrkcalDaysetDetailDTO.isOff","consultCompWrkcalDaysetDetailDTO.isOff","IS_USE");
	acSysDesc("consultCompWrkcalDaysetDetailDTO.isRepeat","consultCompWrkcalDaysetDetailDTO.isRepeat","IS_USE");
}

function goInput()
{
	sequenceNextVal('SQAWRKCALDAYSET_ID');
}

function setSequenceVal(sequenceVal)
{
	//TAWRKCALLIST 테이블의 다음 pm_no 번호를 가져온다.
	consultCompWrkcalDaysetDetailForm.elements['consultCompWrkcalDaysetDetailDTO.wrkcalDaysetId'].value = sequenceVal;
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
	

	consultCompWrkcalDaysetDetailForm.elements['consultCompWrkcalCommonDTO.compNo'].value = 
		parent.parent.consultCompWrkcalDetailForm.elements['consultCompWrkcalDetailDTO.compNo'].value;
	
	
	//strutsAction 셋팅.
	if(ckCreate(currentPageId)) consultCompWrkcalDaysetDetailForm.strutsAction.value = "<%=ConsultCompWrkcalDaysetDetailAction.WRKCAL_DAYSET_DETAIL_INPUT%>";
	else consultCompWrkcalDaysetDetailForm.strutsAction.value = "<%=ConsultCompWrkcalDaysetDetailAction.WRKCAL_DAYSET_DETAIL_UPDATE%>";
	
	var actionUrl = contextPath + "/consultCompWrkcalDaysetDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(consultCompWrkcalDaysetDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    	
    if (parent.findGridRow)	parent.findGridRow(consultCompWrkcalDaysetDetailForm.elements['consultCompWrkcalDaysetDetailDTO.wrkcalDaysetId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
}
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/consultCompWrkcalDaysetDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="consultCompWrkcalCommonDTO.compNo"/><!-- Key -->
	<html:hidden property="consultCompWrkcalCommonDTO.wrkcalListId"/><!-- Key -->
	<html:hidden property="consultCompWrkcalDaysetDetailDTO.wrkcalDaysetId" />
         <div class="article_box" id="detailBox">
             <div class="form_box">
             	<!-- 일자 -->
                 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.day"/></label>
             	 	<div class="input_box">
             	 		<html:text property="consultCompWrkcalDaysetDetailDTO.calDate" tabindex="10"/>
             	 		<p class="open_calendar"><span>날짜</span></p>
             	 	</div>
             	 </div>
             	 <!-- 휴무여부 -->
             	 <div class="field">
					<label class="check"><bean:message key="LABEL.isOff"/></label>
					<div class="input_box">
	 					<html:text property="consultCompWrkcalDaysetDetailDTO.isOff" tabindex="60"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 반복여부 -->
             	 <div class="field">
					<label class="check"><bean:message key="LABEL.isRepeat"/></label>
					<div class="input_box">
	 					<html:text property="consultCompWrkcalDaysetDetailDTO.isRepeat" tabindex="60"/>
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
						<html:textarea property="consultCompWrkcalDaysetDetailDTO.remark" styleClass="ta50" tabindex="70"/>
					</div>
				</div>
				<div class="field_blank"></div>
				<div class="field_blank"></div>
				<div class="field_blank"></div>
				<div class="field_blank"></div>
				<div class="field_blank"></div>
         	</div> <!-- End of Article_box -->
         </div>
	</html:form>
</body>
</html:html>

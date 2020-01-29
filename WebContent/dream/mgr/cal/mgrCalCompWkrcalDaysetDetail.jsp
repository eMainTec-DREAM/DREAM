<%--===========================================================================
휴무일 설정 - 상세
author  kim21017
version $Id: mgrCalCompWkrcalDaysetDetail.jsp,v 1.5 2014/07/02 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.mgr.cal.action.MgrCalCompWkrcalDaysetDetailAction"%>
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
	
	acSysDesc("mgrCalCompWkrcalDaysetDetailDTO.isOff","mgrCalCompWkrcalDaysetDetailDTO.isOff","IS_USE");
	acSysDesc("mgrCalCompWkrcalDaysetDetailDTO.isRepeat","mgrCalCompWkrcalDaysetDetailDTO.isRepeat","IS_USE");
}

function goInput()
{
	sequenceNextVal('SQAWRKCALDAYSET_ID');
}

function setSequenceVal(sequenceVal)
{
	//TAWRKCALLIST 테이블의 다음 pm_no 번호를 가져온다.
	mgrCalCompWkrcalDaysetDetailForm.elements['mgrCalCompWkrcalDaysetDetailDTO.wrkcalDaysetId'].value = sequenceVal;
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
	if(ckCreate(currentPageId)) mgrCalCompWkrcalDaysetDetailForm.strutsAction.value = "<%=MgrCalCompWkrcalDaysetDetailAction.WRKCAL_DAYSET_DETAIL_INPUT%>";
	else mgrCalCompWkrcalDaysetDetailForm.strutsAction.value = "<%=MgrCalCompWkrcalDaysetDetailAction.WRKCAL_DAYSET_DETAIL_UPDATE%>";
	
	var actionUrl = contextPath + "/mgrCalCompWkrcalDaysetDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(mgrCalCompWkrcalDaysetDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    	
    if (parent.findGridRow)	parent.findGridRow(mgrCalCompWkrcalDaysetDetailForm.elements['mgrCalCompWkrcalDaysetDetailDTO.wrkcalDaysetId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
}
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/mgrCalCompWkrcalDaysetDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="mgrCalCompWkrcalCommonDTO.wrkcalListId"/><!-- Key -->
	<html:hidden property="mgrCalCompWkrcalDaysetDetailDTO.wrkcalDaysetId" />
         <div class="article_box" id="detailBox">
             <div class="form_box">
             	<!-- 일자 -->
                 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.day"/></label>
             	 	<div class="input_box">
             	 		<html:text property="mgrCalCompWkrcalDaysetDetailDTO.calDate" tabindex="10"/>
             	 		<p class="open_calendar"><span>날짜</span></p>
             	 	</div>
             	 </div>
             	 <!-- 휴무여부 -->
             	 <div class="field">
					<label class="check"><bean:message key="LABEL.isOff"/></label>
					<div class="input_box">
	 					<html:text property="mgrCalCompWkrcalDaysetDetailDTO.isOff" tabindex="60"/>
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
	 					<html:text property="mgrCalCompWkrcalDaysetDetailDTO.isRepeat" tabindex="60"/>
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
						<html:textarea property="mgrCalCompWkrcalDaysetDetailDTO.remark" styleClass="ta50" tabindex="70"/>
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

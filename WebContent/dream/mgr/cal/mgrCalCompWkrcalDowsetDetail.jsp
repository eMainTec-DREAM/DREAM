<%--===========================================================================
회사설정 - 상세
author  kim21017
version $Id: mgrCalCompWkrcalDowsetDetail.jsp,v 1.5 2014/07/02 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.mgr.cal.action.MgrCalCompWkrcalDowsetDetailAction"%>
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
	
	acSysDesc("mgrCalCompWkrcalDowsetDetailDTO.dowDesc","mgrCalCompWkrcalDowsetDetailDTO.dow","DOW");
	acSysDesc("mgrCalCompWkrcalDowsetDetailDTO.isOff","mgrCalCompWkrcalDowsetDetailDTO.isOff","IS_USE");
}

function goInput()
{
	sequenceNextVal('SQAWRKCALDOWSET_ID');
}

function setSequenceVal(sequenceVal)
{
	//TAWRKCALLIST 테이블의 다음 pm_no 번호를 가져온다.
	mgrCalCompWkrcalDowsetDetailForm.elements['mgrCalCompWkrcalDowsetDetailDTO.wrkcalDowsetId'].value = sequenceVal;
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
	mgrCalCompWkrcalDowsetDetailForm.elements['mgrCalCompWkrcalCommonDTO.compNo'].value = 
		parent.parent.mgrCalCompWkrcalDetailForm.elements['mgrCalCompWkrcalDetailDTO.compNo'].value;
	
	
	if(ckCreate(currentPageId)) mgrCalCompWkrcalDowsetDetailForm.strutsAction.value = "<%=MgrCalCompWkrcalDowsetDetailAction.WRKCAL_DOWSET_DETAIL_INPUT%>";
	else mgrCalCompWkrcalDowsetDetailForm.strutsAction.value = "<%=MgrCalCompWkrcalDowsetDetailAction.WRKCAL_DOWSET_DETAIL_UPDATE%>";
	
	var actionUrl = contextPath + "/mgrCalCompWkrcalDowsetDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(mgrCalCompWkrcalDowsetDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    	
    if (parent.findGridRow)	parent.findGridRow(mgrCalCompWkrcalDowsetDetailForm.elements['mgrCalCompWkrcalDowsetDetailDTO.wrkcalDowsetId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
}
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/mgrCalCompWkrcalDowsetDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="mgrCalCompWkrcalCommonDTO.compNo"/><!-- Key -->
	<html:hidden property="mgrCalCompWkrcalCommonDTO.wrkcalListId"/><!-- Key -->
	<html:hidden property="mgrCalCompWkrcalDowsetDetailDTO.wrkcalDowsetId" />
	<html:hidden property="mgrCalCompWkrcalDowsetDetailDTO.dow" />
         <div class="article_box" id="detailBox">
             <div class="form_box">
             	<!-- 요일 -->
                 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.day2"/></label>
             	 	<div class="input_box">
             	 		<html:text property="mgrCalCompWkrcalDowsetDetailDTO.dowDesc" tabindex="10"/>
             	 		<p class="open_spop">
             	 			<a>
             	 				<span>조회</span>
             	 			</a>
             	 		</p>
             	 	</div>
             	 </div>
             	 <!-- 휴무여부 -->
             	 <div class="field">
					<label class="check"><bean:message key="LABEL.isOff"/></label>
					<div class="input_box">
	 					<html:text property="mgrCalCompWkrcalDowsetDetailDTO.isOff" tabindex="60"/>
						<p class="open_spop">
							<a href="javascript:lovTable('mgrCalCompWkrcalDowsetDetailDTO.isOff', 'mgrCalCompWkrcalDowsetDetailDTO.isOff','YN');">
								<span>조회</span>
							</a>
						</p>
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

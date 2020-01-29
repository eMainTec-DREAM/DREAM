<%--===========================================================================
일자
author  kim21017
version $Id: workPmListSchdDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%> 
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.work.pm.list.action.WorkPmListSchdDetailAction"%>
<html>
<head>
<!-- 자재-->
<title><bean:message key="TAB.workPmListSchdList"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("maPmMstrCommonDTO.pmId","workPmListSchdDetailDTO.planDate");
	setForUpdate();
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAPMEVENTSCHED_ID');
}

function setSequenceVal(sequenceVal)
{
	workPmListSchdDetailForm.elements['workPmListSchdDetailDTO.pmEventSchedId'].value = sequenceVal;
}

function goSave(){
	if(checkValidation()) return;

	if(ckCreate(currentPageId)) workPmListSchdDetailForm.strutsAction.value = '<%=WorkPmListSchdDetailAction.WORK_PM_SCH_DETAIL_INPUT%>';
	else workPmListSchdDetailForm.strutsAction.value = '<%= WorkPmListSchdDetailAction.WORK_PM_SCH_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/workPmListSchdDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(workPmListSchdDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    //parent.goSearch(); 
	if (parent.findGridRow)	parent.findGridRow(workPmListSchdDetailForm.elements['workPmListSchdDetailDTO.pmEventSchedId'].value);
	setTitle("maPmMstrCommonDTO.pmId","workPmListSchdDetailDTO.planDate");
    getTopPage().afterSaveAll(currentPageId);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/workPmListSchdDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="maPmMstrCommonDTO.pmId"/>
	<html:hidden property="workPmListSchdDetailDTO.pmEventSchedId"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 일자 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.date"/></label>
				<div class="input_box">
					<html:text property="workPmListSchdDetailDTO.planDate" tabindex="10"/>
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			<div class="field_blank"></div>
			<div class="field_blank"></div>
			<div class="field_blank"></div>
			<div class="field_blank"></div>
			<div class="field_blank"></div>
			<div class="field_blank"></div>
			<div class="field_blank"></div>
			<div class="field_blank"></div>
			<div class="field_blank"></div>
			<div class="field_blank"></div>
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>
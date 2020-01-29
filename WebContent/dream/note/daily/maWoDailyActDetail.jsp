<%--===========================================================================
일일작업일지확인 - Main activities
author  kim21017
version $Id: maWoDailyActDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.note.daily.action.MaWoDailyActDetailAction"%>
<html>
<head>
<!-- Activity Contents-->
<title><bean:message key="TAB.maWoDailyActList"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
 
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("maWoDailyActDetailDTO.equipDesc","maWoDailyActDetailDTO.actContents"); 
	setForUpdate();
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAWODAYACT_ID');
}

function setSequenceVal(sequenceVal)
{
	maWoDailyActDetailForm.elements['maWoDailyActDetailDTO.woDayActId'].value = sequenceVal;
}

function goSave(){

	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	if(ckCreate(currentPageId)) maWoDailyActDetailForm.strutsAction.value = '<%=MaWoDailyActDetailAction.DETAIL_INPUT%>';
	else maWoDailyActDetailForm.strutsAction.value = '<%= MaWoDailyActDetailAction.DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maWoDailyActDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maWoDailyActDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maWoDailyActDetailForm.elements['maWoDailyActDetailDTO.woDayActId'].value);
    getTopPage().afterSaveAll(currentPageId);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maWoDailyActDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="maWoDailyCommonDTO.woDayListId"/><!-- Key -->
	<html:hidden property="maWoDailyActDetailDTO.woDayActId"/>
	<html:hidden property="maWoDailyActDetailDTO.empNo"/>
	<html:hidden property="maWoDailyActDetailDTO.empId"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 설비명 -->
			<div class="field_long">
				<label><bean:message key="LABEL.equipName"/></label>
				<div class="input_box">
					<html:text property="maWoDailyActDetailDTO.equipDesc" tabindex="10"/>
				</div>
			</div>
			<!-- activity contents -->
			<div class="field_long">
				<label><bean:message key="LABEL.actContents"/></label>
				<div class="input_box">
					<html:text property="maWoDailyActDetailDTO.actContents" tabindex="20" />
				</div>
			</div>
			<!-- activity contents -->
			<div class="field_long">
				<label><bean:message key="LABEL.action"/></label>
				<div class="input_box">
					<html:text property="maWoDailyActDetailDTO.action" tabindex="30" />
				</div>
			</div>
			<!-- 작업자 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.woCraft"/></label>
				<div class="input_box">
					<html:text property="maWoDailyActDetailDTO.empDesc" tabindex="40"
						onblur="valEmpName('maWoDailyActDetailDTO.empId', '', 'maWoDailyActDetailDTO.empDesc', true);"/>
					<p class="open_spop"><a href="javascript:lovEmp('maWoDailyActDetailDTO.empId', '', 'maWoDailyActDetailDTO.empDesc');"><span>조회</span></a></p>
				</div>
			</div>
			<!-- 정렬순서 -->
			<div class="field">
				<label><bean:message key="LABEL.ordNo"/></label>
				<div class="input_box">
					<html:text property="maWoDailyActDetailDTO.ordNo" tabindex="50" />
				</div>
			</div>
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>
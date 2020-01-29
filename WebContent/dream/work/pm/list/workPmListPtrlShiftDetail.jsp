<%--===========================================================================
교대조
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%> 
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.work.pm.list.action.WorkPmListShiftDetailAction"%>
<html>
<head>
<!-- 자재-->
<title><bean:message key="LABEL.ptrlOrd"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
	setTitle("workPmListShiftDetailDTO.shiftTypeDesc");
	setForUpdate();

	acSysDesc("workPmListShiftDetailDTO.shiftTypeDesc","workPmListShiftDetailDTO.shiftType","SHIFT_TYPE",true);
	acSysDesc("workPmListShiftDetailDTO.isActive","workPmListShiftDetailDTO.isActive","IS_USE",true);
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAPM_WRKSHIFT_ID');
	workPmListShiftDetailForm.elements['workPmListShiftDetailDTO.isActive'].value='Y';
}

function goUpdate(){

}

function setSequenceVal(sequenceVal)
{
	workPmListShiftDetailForm.elements['workPmListShiftDetailDTO.pmWrkShiftId'].value = sequenceVal;
}

function goOpen(pageId)
{
	goTabPage(pageId);
}

function goTabPage(pageId)
{
	var form = document.workPmListShiftDetailForm;
	goCommonTabPage(form, '' , pageId);
    
}

function goSave(){
	if(checkValidation()) return;

	if(ckCreate(currentPageId)) workPmListShiftDetailForm.strutsAction.value = '<%=WorkPmListShiftDetailAction.WORK_PM_SHIFT_DETAIL_INPUT%>';
	else workPmListShiftDetailForm.strutsAction.value = '<%= WorkPmListShiftDetailAction.WORK_PM_SHIFT_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/workPmListPtrlShiftDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(workPmListShiftDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    //parent.goSearch(); 
	if (parent.findGridRow)	parent.findGridRow(workPmListShiftDetailForm.elements['workPmListShiftDetailDTO.pmWrkShiftId'].value);
	setTitle("workPmListShiftDetailDTO.shiftTypeDesc");
    getTopPage().afterSaveAll(currentPageId);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/workPmListPtrlShiftDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="maPmMstrCommonDTO.pmId"/>
	<html:hidden property="workPmListShiftDetailDTO.pmWrkShiftId"/>
	<html:hidden property="workPmListShiftDetailDTO.shiftType"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 교대조 -->
			<div class="field">
				<label><bean:message key="LABEL.shiftType"/></label>
				<div class="input_box">
					<html:text property="workPmListShiftDetailDTO.shiftTypeDesc" tabindex="10"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 사용여부 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.isUse"/></label>
				<div class="input_box">
					<html:text property="workPmListShiftDetailDTO.isActive" tabindex="20"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="workPmListShiftDetailDTO.remark" styleClass="ta50" tabindex="30"/>
				</div>
			</div>
			
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>
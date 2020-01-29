<%--===========================================================================
에너지 값 측정항목 상세
author  sy.yang
version $Id: workListEnergyPointDetail.jsp,v 1.0 2015/12/04 07:26:18 sy.yang Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.work.list.energy.action.WorkListEnergyPointDetailAction"%>
<%@page import="dream.doc.file.action.MaDocLibListAction"%>
<html>
<head>
<!-- 측정항목 -->
<title><bean:message key="TAB.workListEnergyPointDetail"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	setTitle("workListEnergyPointDetailDTO.checkPoint");
	
	setForUpdate();
}

function goSave()
{
	workListEnergyPointDetailForm.strutsAction.value = '<%= WorkListEnergyPointDetailAction.WORK_PMU_POINT_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/workListEnergyPointDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(workListEnergyPointDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================

    if (parent.findGridRow)	parent.findGridRow(workListEnergyPointDetailForm.elements['workListEnergyPointDetailDTO.pmInsPointId'].value, workListEnergyPointDetailForm.elements['workListEnergyPointDetailDTO.pmPointId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
}

function goOpen(pageId)
{
	goTabPage(pageId);
}
function goTabPage(pageId)
{
	var form = document.workListEnergyPointDetailForm;

	if(pageId == "maDocLibList" || pageId == "workListEnergyPointDocLibList")
	{	
		form.elements['maDocLibCommonDTO.objectId'].value = workListEnergyPointDetailForm.elements['workListEnergyPointDetailDTO.pmInsPointId'].value;
		form.elements['maDocLibCommonDTO.objectType'].value = "PM_ROUTINE_POINT";
		form.elements['maDocLibCommonDTO.description'].value = workListEnergyPointDetailForm.elements['workListEnergyPointDetailDTO.checkPoint'].value;
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else
		goCommonTabPage(form, '' , pageId);
}

function checkCalValue()
{
	var isRunValue = workListEnergyPointDetailForm.elements['workListEnergyPointDetailDTO.isRunValue'].value;
	
	if(isRunValue == "Y")
	{
		// 측정값
		var resultVal = workListEnergyPointDetailForm.elements['workListEnergyPointDetailDTO.resultValue'].value.replace(",","");
		// 이전측정값
		var preResultValue = workListEnergyPointDetailForm.elements['workListEnergyPointDetailDTO.preResultValue'].value.replace(",","");

		// 사용값 계산 = 측정값 - 이전측정값
		var calValue = Number(resultVal) - Number(preResultValue); 
		workListEnergyPointDetailForm.elements['workListEnergyPointDetailDTO.calValue'].value = calValue;

		if(calValue <0)
		{
			alertMessage1("<bean:message key='MESSAGE.MSG1010' />");		
			workListEnergyPointDetailForm.elements['workListEnergyPointDetailDTO.resultValue'].value = "";
			workListEnergyPointDetailForm.elements['workListEnergyPointDetailDTO.calValue'].value = "";
		}else {
			workListEnergyPointDetailForm.elements['workListEnergyPointDetailDTO.calValue'].value = calValue;
		}
	}
	else{
		// 측정값
		var resultVal = workListEnergyPointDetailForm.elements['workListEnergyPointDetailDTO.resultValue'].value.replace(",","");
		// 이전측정값
		workListEnergyPointDetailForm.elements['workListEnergyPointDetailDTO.calValue'].value = resultVal;

	}
	
}


/* audit Trail */
function goAudtrailLink()
{
	var objectId = workListEnergyPointDetailForm.elements['workListEnergyPointDetailDTO.pmInsPointId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}


//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/workListEnergyPointDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="workListEnergyMstrListCommonDTO.pminslistId"/>
	<html:hidden property="workListEnergyMstrListCommonDTO.pmschedStatusId" />
	<html:hidden property="workListEnergyMstrListCommonDTO.pmId"/>
	<html:hidden property="workListEnergyPointDetailDTO.pmInsPointId"/>
	<html:hidden property="workListEnergyPointDetailDTO.pmPointId"/>
	<html:hidden property="workListEnergyPointDetailDTO.stdCheckPointId"/>
	
	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
	<html:hidden property="maDocLibCommonDTO.objectType" />
	<html:hidden property="maDocLibCommonDTO.description" />
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 측정순서 -->
			<div class="field">
				<label><bean:message key="LABEL.MSSTEPNUM"/></label>
				<div class="input_read">
					<html:text property="workListEnergyPointDetailDTO.stepNum" readonly="true" />
				</div>
			</div>
			<!-- 측정분류항목 -->
            <div class="field">
                <label><bean:message key="LABEL.MSCATEGPOINT"/></label>
                <div class="input_read">
                    <html:text property="workListEnergyPointDetailDTO.stdCheckPointDesc" readonly="true" />
                </div>
            </div>
			<!-- 측정항목 -->
			<div class="field">
				<label><bean:message key="LABEL.MSPOINT"/></label>
				<div class="input_read">
					<html:text property="workListEnergyPointDetailDTO.checkPoint" readonly="true" />
				</div>
			</div>
			<!-- 단위 -->
			<div class="field">
				<label><bean:message key="LABEL.uom"/></label>
				<div class="input_read">
					<html:text property="workListEnergyPointDetailDTO.uom" readonly="true" />
				</div>
			</div>
			<!-- 측정값 -->
			<div class="field">
				<label><bean:message key="LABEL.measure"/></label>
				<div class="input_box">
					<html:text property="workListEnergyPointDetailDTO.resultValue" tabindex="10" styleClass="ty_num" onblur="javascript:checkCalValue();" />
				</div>
			</div>
			<!-- 누적값여부 -->
			<div class="field">
                <label><bean:message key="LABEL.isRunValue"/></label>
                <div class="input_read">
                    <html:text property="workListEnergyPointDetailDTO.isRunValue" readonly="true" />
                </div>
            </div>
			<!-- 이전측정값 -->
			<div class="field">
				<label><bean:message key="LABEL.PREMEASURE"/></label>
				<div class="input_read">
					<html:text property="workListEnergyPointDetailDTO.preResultValue" styleClass="num" readonly="true" />
				</div>
			</div>
			<!-- 사용값 -->
			<div class="field">
				<label><bean:message key="LABEL.USEVALUE"/></label>
				<div class="input_read">
					<html:text property="workListEnergyPointDetailDTO.calValue" styleClass="num" readonly="true" />
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="workListEnergyPointDetailDTO.remark" styleClass="ta50" tabindex="20" />
				</div>
			</div>
			
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>
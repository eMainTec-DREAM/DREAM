<%--===========================================================================
검교정상세 측정값상세
author  kim21017
version $Id: maWoResultBmOilCraftDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.work.list.action.WorkListCavalDetailAction"%>
<html>
<head>
<!-- 측정값 -->
<title><bean:message key="TAB.workListCavalList"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

/** 자동완성 변수  */
var mainMngAc;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("workListCavalDetailDTO.setVal");
	setForUpdate();
	
	calDiff();
}

function calDiff(){
	$("input[name='workListCavalDetailDTO.befVal']").on({
		"keyup":function(e){
			var basisVal = workListCavalDetailForm.elements['workListCavalDetailDTO.basisVal'].value;
			var befVal = workListCavalDetailForm.elements['workListCavalDetailDTO.befVal'].value;
			
			workListCavalDetailForm.elements['workListCavalDetailDTO.befDiffVal'].value = parseFloat(intToData(basisVal) - intToData(befVal)).toFixed(3);
		}
	});
	$("input[name='workListCavalDetailDTO.aftVal']").on({
		"keyup":function(e){
			var basisVal = workListCavalDetailForm.elements['workListCavalDetailDTO.basisVal'].value;
			var aftVal = workListCavalDetailForm.elements['workListCavalDetailDTO.aftVal'].value;
			
			workListCavalDetailForm.elements['workListCavalDetailDTO.aftDiffVal'].value = parseFloat(intToData(basisVal) - intToData(aftVal)).toFixed(3);
		}
	});
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAWOCALIBETCVALUE_ID');
}

function setSequenceVal(sequenceVal)
{
	workListCavalDetailForm.elements['workListCavalDetailDTO.woCalibValueId'].value = sequenceVal;
}

function goSave(){

	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
// 	if(checkRequireValue('workListCavalDetailDTO.endDate','<bean:message key="LABEL.woDate"/>')) return;
	
	if(ckCreate(currentPageId)) workListCavalDetailForm.strutsAction.value = '<%=WorkListCavalDetailAction.WORK_LIST_CAVAL_DETAIL_INPUT%>';
	else workListCavalDetailForm.strutsAction.value = '<%= WorkListCavalDetailAction.WORK_LIST_CAVAL_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/workListCavalDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(workListCavalDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(workListCavalDetailForm.elements['workListCavalDetailDTO.woCalibValueId'].value);
    getTopPage().afterSaveAll(currentPageId);
    setTitle("workListCavalDetailDTO.setVal");
}


/* audit Trail */
function goAudtrailLink()
{
	var objectId = workListCavalDetailForm.elements['workListCavalDetailDTO.woCalibValueId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}


//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/workListCavalDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maWoResultMstrCommonDTO.wkOrId"/>
	<html:hidden property="workListCavalDetailDTO.wkOrId"/>
	<html:hidden property="workListCavalDetailDTO.woCalibValueId"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 설정치[A] -->
			<div class="field">
				<label><bean:message key="LABEL.setValue"/></label>
				<div class="input_box">
					<html:text property="workListCavalDetailDTO.setVal" tabindex="60" styleClass="num"/>
				</div>
			</div>
			<!-- 표준치[B] -->
			<div class="field">
				<label><bean:message key="LABEL.basisValue"/></label>
				<div class="input_box">
					<html:text property="workListCavalDetailDTO.basisVal" tabindex="60" styleClass="num"/>
				</div>
			</div>
			<!-- 조정전지시치[C] -->
			<div class="field">
				<label><bean:message key="LABEL.beforeValue"/></label>
				<div class="input_box">
					<html:text property="workListCavalDetailDTO.befVal" tabindex="60" styleClass="num"/>
				</div>
			</div>
			<!-- 조정전오차[B-C] -->
			<div class="field">
				<label><bean:message key="LABEL.beforeDiffValue"/></label>
				<div class="input_read">
					<html:text property="workListCavalDetailDTO.befDiffVal" tabindex="60" styleClass="num" readonly="true"/>
				</div>
			</div>
			<!-- 조정후지시치[D] -->
			<div class="field">
				<label><bean:message key="LABEL.afterValue"/></label>
				<div class="input_box">
					<html:text property="workListCavalDetailDTO.aftVal" tabindex="60" styleClass="num"/>
				</div>
			</div>
			<!-- 조정후오차[B-D] -->
			<div class="field">
				<label><bean:message key="LABEL.afterDiffValue"/></label>
				<div class="input_read">
					<html:text property="workListCavalDetailDTO.aftDiffVal" tabindex="60" styleClass="num" readonly="true"/>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="workListCavalDetailDTO.remark" styleClass="ta50" tabindex="70" />
				</div>
			</div>
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>
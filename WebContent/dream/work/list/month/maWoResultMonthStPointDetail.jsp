<%--===========================================================================
작업결과(월간작업일정) 작업필수검사항목
author  kim21017
version $Id: maWoResultMonthStPointDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.work.list.action.MaWoResultStPointDetailAction"%>
<html>
<head>
<!-- 점검항목-->
<title><bean:message key="LABEL.checkPoint"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	setTitle("maWoResultStPointDetailDTO.checkPoint");
	setForUpdate();
	
	//점검결과 AC
	acSysDesc("maWoResultStPointDetailDTO.stPointRsltStatusDesc","maWoResultStPointDetailDTO.stPointRsltStatus","ST_POINT_RSLT_STATUS",true);
}

function goSave(){
	if(!ckCreate(currentPageId)) maWoResultStPointDetailForm.strutsAction.value = '<%= MaWoResultStPointDetailAction.WO_RESULT_STPOINT_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maWoResultStPointDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maWoResultStPointDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maWoResultStPointDetailForm.elements['maWoResultStPointDetailDTO.woStPointId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maWoResultStPointDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="maWoResultMstrCommonDTO.wkOrId"/>
	<html:hidden property="maWoResultStPointDetailDTO.woStPointId"/>
	<html:hidden property="maWoResultStPointDetailDTO.stPointRsltStatus"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 점검순서 -->
			<div class="field">
				<label><bean:message key="LABEL.pmStepNum"/></label>
				<div class="input_read">
					<html:text property="maWoResultStPointDetailDTO.stepNum" readonly="true" />
				</div>
			</div>
			<!-- 점검부위 -->
			<div class="field">
				<label><bean:message key="LABEL.pmAsmb"/></label>
				<div class="input_read">
					<html:text property="maWoResultStPointDetailDTO.eqAsmbDesc" readonly="true" />
				</div>
			</div>
			<!-- 점검항목 -->
			<div class="field">
				<label><bean:message key="LABEL.checkPoint"/></label>
				<div class="input_read">
					<html:text property="maWoResultStPointDetailDTO.checkPoint" readonly="true" />
				</div>
			</div>
			<!-- 점검방법 -->
			<div class="field">
				<label><bean:message key="LABEL.checkMethod"/></label>
				<div class="input_read">
					<html:text property="maWoResultStPointDetailDTO.checkMethod" readonly="true" />
				</div>
			</div>
			<!-- 적정기준 -->
			<div class="field">
				<label><bean:message key="LABEL.fitBasis"/></label>
				<div class="input_read">
					<html:text property="maWoResultStPointDetailDTO.fitBasis" readonly="true" />
				</div>
			</div>
			<!-- 수치판정구분 -->
			<div class="field">
				<label><bean:message key="LABEL.checkType"/></label>
				<div class="input_read">
					<html:text property="maWoResultStPointDetailDTO.checkTypeDesc" readonly="true" />
				</div>
			</div>
			<!-- 설정값/단위 -->
			<div class="field_long">
				<label><bean:message key="LABEL.checkValUom"/></label>
				<div class="input_read">
					<html:text property="maWoResultStPointDetailDTO.checkValUom" readonly="true" />
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_read">
					<html:text property="maWoResultStPointDetailDTO.remark" readonly="true" />
				</div>
			</div>
			<!-- 점검결과  -->
			<div class="field">
				<label><bean:message key="LABEL.rsltStatusDesc"/></label>
				<div class="input_box">
					<html:text property="maWoResultStPointDetailDTO.stPointRsltStatusDesc" tabindex="10" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 점검값 -->
			<div class="field">
				<label><bean:message key="LABEL.resultVal"/></label>
				<div class="input_box">
					<html:text property="maWoResultStPointDetailDTO.resultValue" tabindex="20" styleClass="num" />
				</div>
			</div>
			<!-- 검사세부내용 -->
			<div class="field_long">
				<label><bean:message key="LABEL.checkDetailRemark"/></label>
				<div class="input_box">
					<html:textarea property="maWoResultStPointDetailDTO.resultRemark" styleClass="ta50" tabindex="30" />
				</div>
			</div>
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>
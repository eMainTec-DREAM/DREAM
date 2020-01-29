<%--===========================================================================
작업결과(예방작업 - 점검) 검사항목
author  kim21017
version $Id: maWoResultPmInsPointDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.work.list.action.MaWoResultPointDetailAction"%>
<%@page import="dream.doc.file.action.MaDocLibListAction"%>
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
	setTitle("maWoResultPointDetailDTO.checkPoint");
	setForUpdate();
	
	//점검결과  AC
    acSysDesc("maWoResultPointDetailDTO.pmPointRsltStatusDesc","maWoResultPointDetailDTO.pmPointRsltStatus","PM_POINT_RSLT_STATUS",true);
}

/* 
 * 부위보기
 * TAPGLINKEDFUNC에 등록하여 사용한다.
 */
function goAsmbLink(_pageId)
{
	var eqAsmbId = maWoResultPointDetailForm.elements['maWoResultPointDetailDTO.eqAsmbId'].value;
	var pmId = maWoResultPointDetailForm.elements['maWoResultPointDetailDTO.pmId'].value;
	
	goAsmbDetail(pmId, eqAsmbId);
}

function goSave(){
	if(ckCreate(currentPageId)) maWoResultPointDetailForm.strutsAction.value = '<%=MaWoResultPointDetailAction.WO_RESULT_POINT_DETAIL_INPUT%>';
	else maWoResultPointDetailForm.strutsAction.value = '<%= MaWoResultPointDetailAction.WO_RESULT_POINT_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maWoResultPointDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maWoResultPointDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maWoResultPointDetailForm.elements['maWoResultPointDetailDTO.woPointId'].value,maWoResultPointDetailForm.elements['maWoResultPointDetailDTO.pmPointId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
}

function checkRsltValue()
{
	var checkType = maWoResultPointDetailForm.elements['maWoResultPointDetailDTO.checkTypeId'].value;

	if(checkType == "VAL")
	{
		var resultVal = maWoResultPointDetailForm.elements['maWoResultPointDetailDTO.resultValue'].value;
		var checkVal = maWoResultPointDetailForm.elements['maWoResultPointDetailDTO.checkValUom'].value.split(" / ");
		var checkMin = checkVal[0];
		var checkMax = checkVal[2].split(" . ");
		checkMax = checkMax[0]

		if(Number(resultVal) != 0 && resultVal != null
				&& (Number(resultVal) < Number(checkMin) || Number(resultVal) > Number(checkMax)))
		{
			maWoResultPointDetailForm.elements['maWoResultPointDetailDTO.pmPointRsltStatus'].value = "BD";
			maWoResultPointDetailForm.elements['maWoResultPointDetailDTO.pmPointRsltStatusDesc'].value = "이상";

			valSysDir('maWoResultPointDetailDTO.pmPointRsltStatus', 'maWoResultPointDetailDTO.pmPointRsltStatusDesc', 'PM_POINT_RSLT_STATUS', true);
		}
		else
		{
			maWoResultPointDetailForm.elements['maWoResultPointDetailDTO.pmPointRsltStatus'].value = "GD";
			maWoResultPointDetailForm.elements['maWoResultPointDetailDTO.pmPointRsltStatusDesc'].value = "정상";

			valSysDir('maWoResultPointDetailDTO.pmPointRsltStatus', 'maWoResultPointDetailDTO.pmPointRsltStatusDesc', 'PM_POINT_RSLT_STATUS', true);
		}
	}
}

function goOpen(pageId)
{
	goTabPage(pageId);
}
function goTabPage(pageId)
{
	var form = document.maWoResultPointDetailForm;

	if(pageId == "maDocLibList" || pageId == "workPmiPointDocLibList")
	{	
		form.elements['maDocLibCommonDTO.objectId'].value = form.elements['maWoResultPointDetailDTO.woPointId'].value;
		form.elements['maDocLibCommonDTO.objectType'].value = "PM_POINT";
		form.elements['maDocLibCommonDTO.description'].value = form.elements['maWoResultPointDetailDTO.checkPoint'].value;
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else
		goCommonTabPage(form, '' , pageId);
}

/* audit Trail */
function goAudtrailLink()
{		
	var objectId = maWoResultPointDetailForm.elements['maWoResultPointDetailDTO.woPointId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}



//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maWoResultPointDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maWoResultMstrCommonDTO.wkOrId"/>
	<html:hidden property="maWoResultPointDetailDTO.woPointId"/>
	<html:hidden property="maWoResultPointDetailDTO.pmPointId"/>
	<html:hidden property="maWoResultPointDetailDTO.pmPointRsltStatus"/>
	<html:hidden property="maWoResultPointDetailDTO.eqAsmbId"/>
	<html:hidden property="maWoResultPointDetailDTO.pmId"/>
	<html:hidden property="maWoResultPointDetailDTO.checkTypeId"/>
	
	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
	<html:hidden property="maDocLibCommonDTO.objectType" />
	<html:hidden property="maDocLibCommonDTO.description" />
        
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 점검순서 -->
			<div class="field">
				<label><bean:message key="LABEL.pmStepNum"/></label>
				<div class="input_read">
					<html:text property="maWoResultPointDetailDTO.stepNum" readonly="true" />
				</div>
			</div>
			<!-- 점검부위 -->
			<div class="field">
				<label><bean:message key="LABEL.pmAsmb"/></label>
				<div class="input_read">
					<html:text property="maWoResultPointDetailDTO.eqAsmbDesc" readonly="true" />
				</div>
			</div>
			<!-- 점검항목 -->
			<div class="field">
				<label><bean:message key="LABEL.checkPoint"/></label>
				<div class="input_read">
					<html:text property="maWoResultPointDetailDTO.checkPoint" readonly="true" />
				</div>
			</div>
			<!-- 점검방법 -->
			<div class="field">
				<label><bean:message key="LABEL.checkMethod"/></label>
				<div class="input_read">
					<html:text property="maWoResultPointDetailDTO.checkMethod" readonly="true" />
				</div>
			</div>
			<!-- 적정기준 -->
			<div class="field">
				<label><bean:message key="LABEL.fitBasis"/></label>
				<div class="input_read">
					<html:text property="maWoResultPointDetailDTO.fitBasis" readonly="true" />
				</div>
			</div>
			<!-- 수치판정구분 -->
			<div class="field">
				<label><bean:message key="LABEL.checkType"/></label>
				<div class="input_read">
					<html:text property="maWoResultPointDetailDTO.checkTypeDesc" readonly="true" />
				</div>
			</div>
			<!-- 설정값/단위 -->
			<div class="field_long">
				<label><bean:message key="LABEL.checkValUom"/></label>
				<div class="input_read">
					<html:text property="maWoResultPointDetailDTO.checkValUom" readonly="true" />
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_read">
					<html:text property="maWoResultPointDetailDTO.remark" readonly="true" />
				</div>
			</div>
			<!-- 점검결과  -->
			<div class="field">
				<label><bean:message key="LABEL.rsltStatusDesc"/></label>
				<div class="input_box">
					<html:text property="maWoResultPointDetailDTO.pmPointRsltStatusDesc" tabindex="10" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 점검값 -->
			<div class="field">
				<label><bean:message key="LABEL.resultVal"/></label>
				<div class="input_box">
					<html:text property="maWoResultPointDetailDTO.resultValue" tabindex="20"
						onblur="javascript:checkRsltValue();" styleClass="num" />
				</div>
			</div>
			<!-- 검사세부내용 -->
			<div class="field_long">
				<label><bean:message key="LABEL.checkDetailRemark"/></label>
				<div class="input_box">
					<html:textarea property="maWoResultPointDetailDTO.resultRemark" styleClass="ta50" tabindex="30" />
				</div>
			</div>
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>
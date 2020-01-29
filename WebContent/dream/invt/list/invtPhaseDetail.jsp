
<%--===========================================================================
상세
author  kim21017
version $Id: invtPhaseDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ page import="common.bean.User"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%@page import="dream.invt.list.action.InvtPhaseDetailAction"%>
<html>
<head>
<!--답변사항 -->
<title><bean:message key="LABEL.questionPoint"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var mainMngAc, crityRow;
function loadPage() 
{	
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
	setTitle("invtPhaseDetailDTO.invtProcLtypeDesc","invtPhaseDetailDTO.invtProcStypeDesc");
	setForUpdate();
	
	acSysDesc("invtPhaseDetailDTO.invtphaseStatusDesc","invtPhaseDetailDTO.invtphaseStatus","INVTPHASE_STATUS", true);
}

function goUpdate()
{
	setReadOnly("invtPhaseDetailDTO.colName");
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAINVTPHASE_ID');
}

function setSequenceVal(sequenceVal)
{
	invtPhaseDetailForm.elements['invtCommonDTO.invtphaseId'].value = sequenceVal;
	invtPhaseDetailForm.elements['invtPhaseDetailDTO.invtphaseId'].value = sequenceVal;
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
	
	if(ckCreate(currentPageId)) invtPhaseDetailForm.strutsAction.value = '<%=InvtPhaseDetailAction.INVT_PHASE_DETAIL_INPUT%>';
	else invtPhaseDetailForm.strutsAction.value = '<%=InvtPhaseDetailAction.INVT_PHASE_DETAIL_UPDATE%>';
	
	
	var actionUrl = contextPath + "/invtPhaseDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(invtPhaseDetailForm), 'afterSave');
	
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================

    var invtphaseId = invtPhaseDetailForm.elements['invtCommonDTO.invtphaseId'].value;

  	if (parent.findGridRow) parent.findGridRow(invtphaseId);
	
	// 투자결과 상태 재조회 
	if(typeof searchPage("reqWoInvtRsltList").findGridList == "function"){
		searchPage("reqWoInvtRsltList").findGridList(invtPhaseDetailForm.elements['invtCommonDTO.invtlistId'].value);
	}
	
	if(typeof searchPage("invtDetail").checkStatus == "function"){
		searchPage("invtDetail").checkStatus(invtPhaseDetailForm.elements['invtCommonDTO.invtlistId'].value);
	}
	
 	// 작업요청접수 상태 변경 
	if(typeof searchPage("maWoReqDetail").checkStatus == "function"){
		searchPage("maWoReqDetail").checkStatus(invtPhaseDetailForm.elements['invtCommonDTO.woReqId'].value);	
	} 
 	
	
 	
    getTopPage().afterSaveAll(currentPageId);
}

function goTabPage(pageId)
{
	var form = document.invtPhaseDetailForm;

	if(pageId == "maDocLibList" || pageId == "invtPrcDocLibList")
	{	
		invtPhaseDetailForm.elements['maDocLibCommonDTO.objectId'].value = invtPhaseDetailForm.elements['invtCommonDTO.invtphaseId'].value;
		invtPhaseDetailForm.elements['maDocLibCommonDTO.objectType'].value = "INVTPHASE";
		invtPhaseDetailForm.elements['maDocLibCommonDTO.description'].value = invtPhaseDetailForm.elements['invtCommonDTO.description'].value;
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else
		goCommonTabPage(form, '' , pageId);
    
}

function goAudtrailLink()
{
	var objectId = invtPhaseDetailForm.elements['invtCommonDTO.invtphaseId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}


</script>

</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/invtPhaseDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="invtCommonDTO.invtlistId"/>
<html:hidden property="invtCommonDTO.invtphaseId"/>
<html:hidden property="invtCommonDTO.description"/>

<html:hidden property="invtCommonDTO.woReqId"/>
<html:hidden property="invtCommonDTO.woReqResId"/>

<html:hidden property="invtPhaseDetailDTO.invtlistId"/>
<html:hidden property="invtPhaseDetailDTO.invtphaseId"/>

<html:hidden property="invtPhaseDetailDTO.invtprcphId"/>
<html:hidden property="invtPhaseDetailDTO.invtProcLtype"/>
<html:hidden property="invtPhaseDetailDTO.invtProcStype"/>
<html:hidden property="invtPhaseDetailDTO.invtphaseStatus"/>

<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
<html:hidden property="maDocLibCommonDTO.objectType" />
<html:hidden property="maDocLibCommonDTO.description" />

    <!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">

			<!-- 순서 -->
			<div class="field">
				<label><bean:message key="LABEL.seqNo"/></label>
				<div class="input_read">
					<html:text property="invtPhaseDetailDTO.ordNo" tabindex="10" readonly="true"/>
				</div>
			</div>
			<!-- 진행상태 -->
            <div class="field">
                <label><bean:message key="LABEL.proStatus"/></label>
                <div class="input_read">
                    <html:text property="invtPhaseDetailDTO.invtphaseStatusDesc" tabindex="20" readonly="true"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
            
            
			<!-- 대분류 -->
			<div class="field">
				<label><bean:message key="LABEL.lType"/></label>
				<div class="input_box">
					<html:text property="invtPhaseDetailDTO.invtProcLtypeDesc" tabindex="30"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 소분류 -->
			<div class="field">
				<label><bean:message key="LABEL.sType"/></label>
				<div class="input_box">
					<html:text property="invtPhaseDetailDTO.invtProcStypeDesc" tabindex="40"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			
			
			<!-- 관련문서 -->
			<div class="field">
				<label><bean:message key="LABEL.refDoc"/></label>
				<div class="input_box">
					<html:text property="invtPhaseDetailDTO.refDoc" tabindex="50"/>
				</div>
			</div>
			<!-- 문서번호-->
			<div class="field">
				<label><bean:message key="LABEL.invtDocNo"/></label>
				<div class="input_box">
					<html:text property="invtPhaseDetailDTO.invtDocNo" tabindex="60"/>
				</div>
			</div>
			
			
			<!-- 시작일자 -->
			<div class="field">
				<label><bean:message key="LABEL.startDate"/></label>
				<div class="input_box input_carendar">
					<html:text property="invtPhaseDetailDTO.startDate" tabindex="70" />
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			<!-- 종료일자 -->
			<div class="field">
				<label><bean:message key="LABEL.endDate"/></label>
				<div class="input_box input_carendar">
					<html:text property="invtPhaseDetailDTO.endDate" tabindex="80" />
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			
			
			<!-- 유관부서-->
            <div class="field">
                <label><bean:message key="LABEL.refDept"/></label>
                <div class="input_box">
                    <html:text property="invtPhaseDetailDTO.refDepart" tabindex="90"/>
                </div>
            </div>
			<!-- 투자금액-->
			<div class="field">
				<label><bean:message key="LABEL.invtAmt"/></label>
				<div class="input_box">
					<html:text property="invtPhaseDetailDTO.actualAmt" tabindex="100" styleClass="num"/>
				</div>
			</div>
			
			
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="invtPhaseDetailDTO.remark" styleClass="ta50" tabindex="110" />
				</div>
			</div>
			
			<c:set var="filePath" value="enhance/${compName}/invt/list/invtPhaseDetail_${compNo}.jsp" />
			<c:if test="${udf:isExist(filePath)}">
				<c:import charEncoding="UTF-8" url="/enhance/${compName}/invt/list/invtPhaseDetail_${compNo}.jsp"></c:import>
			</c:if>
		</div>
	</div><!--article_box end-->
</html:form> 
</body>
</html>
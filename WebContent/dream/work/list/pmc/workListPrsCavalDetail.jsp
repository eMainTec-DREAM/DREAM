<%--===========================================================================
검교정상세 측정값상세
author  kim21017
version $Id: workListPrsCavalDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.work.list.action.WorkListGnlCavalDetailAction"%>
<html>
<head>
<!-- 측정값 -->
<title><bean:message key="TAB.workListGnlCavalList"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

/** 자동완성 변수  */
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("workListGnlCavalDetailDTO.calibPointTypeDesc","workListGnlCavalDetailDTO.calibPoint");
	setForUpdate();

	acSysDesc("workListGnlCavalDetailDTO.calibPointTypeDesc","workListGnlCavalDetailDTO.calibPointType","CALIB_POINT_TYPE", true);
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAWOCALIBGNLVALUE_ID');
}

function setSequenceVal(sequenceVal)
{
	workListGnlCavalDetailForm.elements['workListGnlCavalDetailDTO.wocalibgnlvalueId'].value = sequenceVal;
}

function goSave(){

	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;

	if(ckCreate(currentPageId)) workListGnlCavalDetailForm.strutsAction.value = '<%=WorkListGnlCavalDetailAction.WORK_LIST_GNL_CAVAL_DETAIL_INPUT%>';
	else workListGnlCavalDetailForm.strutsAction.value = '<%= WorkListGnlCavalDetailAction.WORK_LIST_GNL_CAVAL_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/workListGnlCavalDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(workListGnlCavalDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(workListGnlCavalDetailForm.elements['workListGnlCavalDetailDTO.wocalibgnlvalueId'].value);
    getTopPage().afterSaveAll(currentPageId);
	setTitle("workListGnlCavalDetailDTO.calibPointTypeDesc","workListGnlCavalDetailDTO.calibPoint");
}


/* audit Trail */
function goAudtrailLink()
{
	var objectId = workListGnlCavalDetailForm.elements['workListGnlCavalDetailDTO.wocalibgnlvalueId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}


//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/workListGnlCavalDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maWoResultMstrCommonDTO.wkOrId"/>
	<html:hidden property="workListGnlCavalDetailDTO.wkOrId"/>
	<html:hidden property="workListGnlCavalDetailDTO.wocalibgnlvalueId"/>
	<html:hidden property="workListGnlCavalDetailDTO.calibPointType"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 회차 -->
			<div class="field">
				<label><bean:message key="LABEL.calibPointType"/></label>
				<div class="input_box">
					<html:text property="workListGnlCavalDetailDTO.calibPointTypeDesc" tabindex="10"/>
					<p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
				</div>
			</div>
			<!-- 조회순서 -->
			<div class="field">
				<label><bean:message key="LABEL.ordNo"/></label>
				<div class="input_box">
					<html:text property="workListGnlCavalDetailDTO.ordNo" tabindex="20" styleClass="num"/>
				</div>
			</div>
			<!-- 교정Point -->
			<div class="field">
				<label><bean:message key="LABEL.calibPoint"/></label>
				<div class="input_box">
					<html:text property="workListGnlCavalDetailDTO.calibPoint" tabindex="30" styleClass="num"/>
				</div>
			</div>
			<!-- 허용오차 -->
			<div class="field">
				<label><bean:message key="LABEL.allowValue"/></label>
				<div class="input_box">
					<html:text property="workListGnlCavalDetailDTO.allowValue" tabindex="40" styleClass="num"/>
				</div>
			</div>
			<!-- As Found -->
			<div class="field ty3">
				<label><bean:message key="LABEL.asFound"/></label>
				<div class="multi_wrap">
					<div class="input_box">
						<div class="slabel"><bean:message key="LABEL.asfStdValue"/></div>
						<html:text property="workListGnlCavalDetailDTO.asfStdValue" tabindex="50" styleClass="num"/>
					</div>
					<div class="input_box">
						<div class="slabel"><bean:message key="LABEL.asfCalValue"/></div>
						<html:text property="workListGnlCavalDetailDTO.asfCalValue" tabindex="60" styleClass="num"/>
					</div>
					<div class="input_box">
						<div class="slabel"><bean:message key="LABEL.asfDiffValue"/></div>
						<html:text property="workListGnlCavalDetailDTO.asfDiffValue" tabindex="70" styleClass="num"/>
					</div>
				</div>
			</div>
			<!-- As Left -->
			<div class="field ty3">
				<label><bean:message key="LABEL.asLeft"/></label>
				<div class="multi_wrap">
					<div class="input_box">
						<div class="slabel"><bean:message key="LABEL.aslStdValue"/></div>
						<html:text property="workListGnlCavalDetailDTO.aslStdValue" tabindex="80" styleClass="num" />
					</div>
					<div class="input_box">
						<div class="slabel"><bean:message key="LABEL.aslCalValue"/></div>
						<html:text property="workListGnlCavalDetailDTO.aslCalValue" tabindex="90" styleClass="num" />
					</div>
					<div class="input_box">
						<div class="slabel"><bean:message key="LABEL.aslDiffValue"/></div>
						<html:text property="workListGnlCavalDetailDTO.aslDiffValue" tabindex="100" styleClass="num" />
					</div>
				</div>
			</div>
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>
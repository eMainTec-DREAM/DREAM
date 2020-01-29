<%--===========================================================================
제정
author  jung7126
version $Id: commRevHistDetail.jsp,v 1.0 2015/12/04 04:13:54 jung7126 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.comm.revision.action.CommRevHistDetailAction"%>
<%@ page import="common.bean.User"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html>
<head>
<!-- 예방작업기준 -->
<title><bean:message key='LABEL.objectNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">

function loadPage() 
{
	setTitle("commRevHistDetailDTO.objectNo","commRevHistDetailDTO.description");
	
	setForUpdate();
	setDisableAll();
	setLabel();
}

function setLabel()
{
	var revisionObjType =commRevHistDetailForm.elements['commRevHistDetailDTO.revisionObjType'].value; 
	
	if("PMSTD" == revisionObjType)
	{
		$('#objIdLabel').html('<bean:message key="LABEL.pmNo"/>');
		$('#descLabel').html('<bean:message key="LABEL.pmDesc"/>');
	}
	else if("ASSET" == revisionObjType)
	{
		$('#objIdLabel').html('<bean:message key="LABEL.equipNo"/>');
		$('#descLabel').html('<bean:message key="LABEL.equipName"/>');
	}
}

function setSequenceVal(sequenceVal)
{
	//TAPMLST 테이블의 다음 pm_no 번호를 가져온다.
	commRevHistDetailForm.elements['commRevHistDetailDTO.objectId'].value = sequenceVal;
	commRevHistDetailForm.elements['commRevHistDetailDTO.objectNo'].value = sequenceVal;
}

/**
 * 저장
 */ 
function goSave()
{
	if(checkValidation()){
		return;
	} else {
		commRevHistDetailForm.strutsAction.value = '<%=CommRevHistDetailAction.COMM_REV_HIST_UPDATE%>'

		var actionUrl = contextPath + "/commRevHistDetail.do";
	 	XMLHttpPost(actionUrl, FormQueryString(commRevHistDetailForm), 'afterSave');
	}
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================

	//생성 후 조회
	if(parent.findGridRow) parent.findGridRow(commRevHistDetailForm.elements['commRevHistDetailDTO.revisionhistId'].value);
	
	getTopPage().afterSaveAll(currentPageId);
 }
 
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/commRevHistDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="commRevHistCommonDTO.revisionhistId" />
	<html:hidden property="commRevHistDetailDTO.revisionhistId" />
	<html:hidden property="commRevHistDetailDTO.objectId" />
	<html:hidden property="commRevHistDetailDTO.revisionType" />
	<html:hidden property="commRevHistDetailDTO.revisionObjType" />
	<html:hidden property="commRevHistDetailDTO.revisionStatusId" />
	<html:hidden property="commRevHistDetailDTO.wrkDeptId" />
	<html:hidden property="commRevHistDetailDTO.wrkEmpId" />
	
	<div class="article_box">
		<div class="form_box">
			<!-- 구분 -->
			<div class="field">
				<label><bean:message key="LABEL.separation"/></label>
				<div class="input_read">
					<html:text property="commRevHistDetailDTO.revisionTypeDesc" tabindex="10" readonly="true"/>
				</div>
			</div>
			<!-- 업무유형 -->
			<div class="field">
				<label><bean:message key="LABEL.revObjType"/></label>
				<div class="input_read">
					<html:text property="commRevHistDetailDTO.revisionObjTypeDesc" tabindex="20" readonly="true"/>
				</div>
			</div>
			<!-- 예방작업번호	-->
			<div class="field">
				<label id="objIdLabel"></label>
				<div class="input_read">
					<html:text property="commRevHistDetailDTO.objectNo" tabindex="30" readonly="true"/>
				</div>
			</div>
			<!-- 예방작업명 -->
			<div class="field_long">
				<label id="descLabel"></label>
				<div class="input_read">
					<html:text property="commRevHistDetailDTO.description" tabindex="40" readonly="true"/>
				</div>
			</div>
			<!-- 문서번호 -->
			<div class="field">
				<label><bean:message key="LABEL.docNo"/></label>
				<div class="input_box">
					<html:text property="commRevHistDetailDTO.docNo" tabindex="50" />
				</div>
			</div>
			<!-- Revision 번호 -->
			<div class="field">
				<label><bean:message key="LABEL.revNo"/></label>
				<div class="input_box">
					<html:text property="commRevHistDetailDTO.revNo" tabindex="60" />
				</div>
			</div>
			<!-- 진행상태 -->
			<div class="field">
				<label><bean:message key="LABEL.revisionStatus"/></label>
				<div class="input_read">
					<html:text property="commRevHistDetailDTO.revisionStatusDesc" tabindex="70" readonly="true"/>
				</div>
			</div>
			<!-- 제정일자 -->
			<div class="field">
				<label><bean:message key="LABEL.wrkDate"/></label>
				<div class="input_read">
					<html:text property="commRevHistDetailDTO.wrkDate" tabindex="80" readonly="true"/>
				</div>
			</div>
			<!-- 제정담당부서	-->
			<div class="field">
				<label><bean:message key="LABEL.wrkDept"/></label>
				<div class="input_read">
					<html:text property="commRevHistDetailDTO.wrkDeptDesc" tabindex="90" readonly="true"/>
				</div>
			</div>
			<!-- 제정담당자-->
			<div class="field">
				<label><bean:message key="LABEL.wrkEmp"/></label>
				<div class="input_read">
					<html:text property="commRevHistDetailDTO.wrkEmpDesc" tabindex="100" readonly="true"/>
				</div>
			</div>
			<!-- 제정사항 -->
			<div class="field_long">
				<label><bean:message key="LABEL.revDesc"/></label>
				<div class="input_box">
					<html:textarea property="commRevHistDetailDTO.revDesc" styleClass="ta100" tabindex="110" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
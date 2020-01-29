<%--===========================================================================
제정
author  jung7126
version $Id: workPmListRevCreate.jsp,v 1.0 2015/12/04 04:13:54 jung7126 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.comm.revision.action.CommRevAction"%>
<%@ page import="common.bean.User"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html>
<head>
<!-- 예방작업기준 -->
<title><bean:message key='MENU.PMMSTR' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">

function loadPage() 
{
	goInput();
		
	setForUpdate("commRev");
}

function goInput()
{
	sequenceNextVal('SQAPM_ID');
	
	//구분 
	commRevForm.elements['commRevCommonDTO.revisionType'].value = "C";
	commRevForm.elements['commRevCommonDTO.revisionTypeDesc'].value = "C";
	valSysDir('commRevCommonDTO.revisionType', 'commRevCommonDTO.revisionTypeDesc', 'REVISION_TYPE', true);
	//업무유형 
	commRevForm.elements['commRevCommonDTO.revisionObjType'].value = "PMSTD";
	commRevForm.elements['commRevCommonDTO.revisionObjTypeDesc'].value = "PMSTD";
	valSysDir('commRevCommonDTO.revisionObjType', 'commRevCommonDTO.revisionObjTypeDesc', 'REVISION_OBJECT_TYPE', true);
	//진행상태 
	commRevForm.elements['commRevCommonDTO.revisionStatusId'].value = "W";
	commRevForm.elements['commRevCommonDTO.revisionStatusDesc'].value = "W";
	valSysDir('commRevCommonDTO.revisionStatusId', 'commRevCommonDTO.revisionStatusDesc', 'REVISION_STATUS', true);
	//부서정보 세팅
	commRevForm.elements['commRevCommonDTO.wrkDeptId'].value = "<%=user.getDeptId()%>";
	commRevForm.elements['commRevCommonDTO.wrkDeptDesc'].value = "<%=user.getDeptDesc()%>";
	//담당자 세팅
	commRevForm.elements['commRevCommonDTO.wrkEmpId'].value = "<%=user.getEmpId()%>";
	commRevForm.elements['commRevCommonDTO.wrkEmpDesc'].value = "<%=user.getEmpName()%>";
	//제정일자 세팅
	commRevForm.elements['commRevCommonDTO.wrkDate'].value = getToday();
	
	// Revision번호 초기값
	commRevForm.elements['commRevCommonDTO.revNo'].value = '1.0';
}

function setSequenceVal(sequenceVal)
{
	//TAPMLST 테이블의 다음 pm_no 번호를 가져온다.
	commRevForm.elements['commRevCommonDTO.objectId'].value = sequenceVal;
	commRevForm.elements['commRevCommonDTO.objectNo'].value = sequenceVal;
}

/**
 * 저장
 */ 
function goSave()
{
	if(checkValidation()){
		return;
	} else {
		commRevForm.strutsAction.value = '<%=CommRevAction.COMM_REV_REGISLATE_INPUT%>'

		var actionUrl = contextPath + "/workPmListRevCreate.do";
	 	XMLHttpPost(actionUrl, FormQueryString(commRevForm), 'afterSave');
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
	if(parent.afterCreate) parent.afterCreate(commRevForm.elements['commRevCommonDTO.objectId'].value, commRevForm.elements['commRevCommonDTO.param'].value);
	getTopPage().afterSaveAll("commRev");
	
	goCloseLayer();
 }
 
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/workPmListRevCreate" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="commRevCommonDTO.param" />
	<html:hidden property="commRevCommonDTO.objectId" />
	<html:hidden property="commRevCommonDTO.revisionType" />
	<html:hidden property="commRevCommonDTO.revisionObjType" />
	<html:hidden property="commRevCommonDTO.revisionStatusId" />
	<html:hidden property="commRevCommonDTO.wrkDeptId" />
	<html:hidden property="commRevCommonDTO.wrkEmpId" />
	<html:hidden property="commRevCommonDTO.selectedWoType" />
	<html:hidden property="commRevCommonDTO.selectedPmType" />
	
	 <div class="section_wrap">
      <div class="sheader_box">
         <div class="sheader_wrap"><a></a></div>
         <div class="stitle_wrap">
            <div class="stitle_icon"><a></a></div>
            <div class="stitle_tx"><bean:message key="LABEL.regislate"/></div>
         </div>
         <div class="function_box detail">
            <div class="fb_group3">
               <div class="sfb_wrap" style="display:none;">
               </div>
            </div>
            <div class="fb_group2">
            </div>
         </div>
    </div><!--sheader_box end-->
	
	<div class="article_box">
		<div class="form_box">
			<!-- 구분 -->
			<div class="field">
				<label><bean:message key="LABEL.separation"/></label>
				<div class="input_read">
					<html:text property="commRevCommonDTO.revisionTypeDesc" tabindex="10" readonly="true"/>
				</div>
			</div>
			<!-- 업무유형 -->
			<div class="field">
				<label><bean:message key="LABEL.revObjType"/></label>
				<div class="input_read">
					<html:text property="commRevCommonDTO.revisionObjTypeDesc" tabindex="20" readonly="true"/>
				</div>
			</div>
			<!-- 예방작업번호	-->
			<div class="field">
				<label class="check"><bean:message key="LABEL.pmNo"/></label>
				<div class="input_read">
					<html:text property="commRevCommonDTO.objectNo" tabindex="30" readonly="true"/>
				</div>
			</div>
			<!-- 예방작업명 -->
			<div class="field_long">
				<label class="check"><bean:message key="LABEL.pmDesc"/></label>
				<div class="input_box">
					<html:text property="commRevCommonDTO.description" tabindex="40"/>
				</div>
			</div>
			<!-- 문서번호 -->
			<div class="field">
				<label><bean:message key="LABEL.docNo"/></label>
				<div class="input_box">
					<html:text property="commRevCommonDTO.docNo" tabindex="50" />
				</div>
			</div>
			<!-- Revision 번호 -->
			<div class="field">
				<label><bean:message key="LABEL.revNo"/></label>
				<div class="input_box">
					<html:text property="commRevCommonDTO.revNo" tabindex="60" styleClass="num" />
				</div>
			</div>
			<!-- 진행상태 -->
			<div class="field">
				<label><bean:message key="LABEL.revisionStatus"/></label>
				<div class="input_read">
					<html:text property="commRevCommonDTO.revisionStatusDesc" tabindex="70" readonly="true"/>
				</div>
			</div>
			<!-- 제정일자 -->
			<div class="field">
				<label><bean:message key="LABEL.wrkDate"/></label>
				<div class="input_read">
					<html:text property="commRevCommonDTO.wrkDate" tabindex="80" readonly="true"/>
				</div>
			</div>
			<!-- 제정담당부서	-->
			<div class="field">
				<label><bean:message key="LABEL.wrkDept"/></label>
				<div class="input_read">
					<html:text property="commRevCommonDTO.wrkDeptDesc" tabindex="90" readonly="true"/>
				</div>
			</div>
			<!-- 제정담당자-->
			<div class="field">
				<label><bean:message key="LABEL.wrkEmp"/></label>
				<div class="input_read">
					<html:text property="commRevCommonDTO.wrkEmpDesc" tabindex="100" readonly="true"/>
				</div>
			</div>
			<!-- 제정사항 -->
			<div class="field_long">
				<label><bean:message key="LABEL.revDesc"/></label>
				<div class="input_box">
					<html:textarea property="commRevCommonDTO.revDesc" styleClass="ta100" tabindex="110" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
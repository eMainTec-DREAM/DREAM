<%--===========================================================================
Workflow Phase Detail
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page
	import="dream.mgr.workflow.action.MgrWorkflowPhaseDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 승인Flow 단계 -->
<title><bean:message key='TAB.mgrWorkflowPhaseList' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
    setTitle("mgrWorkflowPhaseDetailDTO.wfphaseId", "mgrWorkflowPhaseDetailDTO.description");
    //For Save
    setForUpdate();
    //사용여부 자동완성
    acSysDesc("mgrWorkflowPhaseDetailDTO.isUseDesc","mgrWorkflowPhaseDetailDTO.isUse","IS_USE", true);
    
    //결재구분 자동완성
    acSysDesc("mgrWorkflowPhaseDetailDTO.approvalTypeDesc","mgrWorkflowPhaseDetailDTO.approvalType","APPROVAL_TYPE", true);
    
    //결재자 자동완성
    acSysDesc("mgrWorkflowPhaseDetailDTO.gradeTypeDesc","mgrWorkflowPhaseDetailDTO.gradeType","GRADE_TYPE", true);
    
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQAWFPHASE_ID');
    
    mgrWorkflowPhaseDetailForm.elements['mgrWorkflowPhaseDetailDTO.wflistId'].value = mgrWorkflowPhaseDetailForm.elements['mgrWorkflowPhaseListDTO.wflistId'].value;
    		
    mgrWorkflowPhaseDetailForm.elements['mgrWorkflowPhaseDetailDTO.isUse'].value = 'Y';
    valSysDir('mgrWorkflowPhaseDetailDTO.isUse', 'mgrWorkflowPhaseDetailDTO.isUse', 'IS_USE', true);
}
function setSequenceVal(sequenceVal)
{
    mgrWorkflowPhaseDetailForm.elements['mgrWorkflowPhaseDetailDTO.wfphaseId'].value = sequenceVal;
    mgrWorkflowPhaseDetailForm.elements['mgrWorkflowPhaseListDTO.wfphaseId'].value = sequenceVal;
}
/**
 * 수정
 */
function goUpdate()
{
	
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
    
    //strutsAction 셋팅.
    if(ckCreate(currentPageId)) mgrWorkflowPhaseDetailForm.strutsAction.value = "<%=MgrWorkflowPhaseDetailAction.DETAIL_INPUT%>";
    else mgrWorkflowPhaseDetailForm.strutsAction.value = "<%=MgrWorkflowPhaseDetailAction.DETAIL_UPDATE%>";

		var actionUrl = contextPath + "/mgrWorkflowPhaseDetail.do";
		XMLHttpPost(actionUrl, FormQueryString(mgrWorkflowPhaseDetailForm),
				'afterSave');

	}

	/**
	 * 저장후 호출
	 */
	function afterSave(ajaxXmlDoc) {
		//=====================================
		if (!checkHttpXml(ajaxXmlDoc))
			return;
		//=====================================
		if (parent.findGridRow)
			parent.findGridRow(mgrWorkflowPhaseDetailForm.elements['mgrWorkflowPhaseDetailDTO.wfphaseId'].value);

		mgrWorkflowPhaseDetailForm.elements['mgrWorkflowPhaseListDTO.wfphaseId'].value = mgrWorkflowPhaseDetailForm.elements['mgrWorkflowPhaseDetailDTO.wfphaseId'].value;
		getTopPage().afterSaveAll(currentPageId);
		setTitle("mgrWorkflowPhaseDetailDTO.wfphaseId",
				"mgrWorkflowPhaseDetailDTO.description");

	}
	/**
	 *  Audit trail
	 */
	 function goAudtrailLink()
	 {
	    var objectId = mgrWorkflowPhaseDetailForm.elements['mgrWorkflowPhaseDetailDTO.wfphaseId'].value;
	    var fileName = currentPageId;

	    if(typeof objectId=="undefined") return;

	    goAudTrailList(objectId, fileName);
	 }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

	<html:form action="/mgrWorkflowPhaseDetail">
		<html:hidden property="strutsAction" />
		<html:hidden property="currentPageId"/>
		<html:hidden property="mgrWorkflowPhaseListDTO.compNo" /><!-- Key -->
		<html:hidden property="mgrWorkflowPhaseListDTO.wfphaseId" /><!-- Key -->
		<html:hidden property="mgrWorkflowPhaseListDTO.wflistId" />

		<html:hidden property="mgrWorkflowPhaseDetailDTO.compNo" />
		<html:hidden property="mgrWorkflowPhaseDetailDTO.wfphaseId" />
		<html:hidden property="mgrWorkflowPhaseDetailDTO.wflistId" />
		<html:hidden property="mgrWorkflowPhaseDetailDTO.approvalType" />
		<html:hidden property="mgrWorkflowPhaseDetailDTO.gradeType" />

		<div class="article_box">
			<div class="form_box">
				<!-- 진행순서 -->
				<div class="field">
					<label><bean:message key="LABEL.apprSeq" /></label>
					<div class="input_box">
						<html:text property="mgrWorkflowPhaseDetailDTO.stepNum"
							tabindex="10" />
					</div>
				</div>
				<!-- 사용여부 -->
				<div class="field">
					<label><bean:message key="LABEL.isUse" /></label>
					<div class="input_box">
						<html:text property="mgrWorkflowPhaseDetailDTO.isUse"
							tabindex="20" />
						<p class="open_spop">
							<a> <span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 결재명 -->
				<div class="field_long">
					<label><bean:message key="LABEL.apprTitle" /></label>
					<div class="input_box">
						<html:text property="mgrWorkflowPhaseDetailDTO.description"
							tabindex="30" />
					</div>
				</div>
				<!-- 결재구분 -->
				<div class="field">
					<label><bean:message key="LABEL.apprusrAction" /></label>
					<div class="input_box">
						<html:text property="mgrWorkflowPhaseDetailDTO.approvalTypeDesc"
							tabindex="40" />
						<p class="open_spop">
							<a> <span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 결재자 -->
				<div class="field">
					<label><bean:message key="LABEL.apprByName" /></label>
					<div class="input_box">
						<html:text property="mgrWorkflowPhaseDetailDTO.gradeTypeDesc"
							tabindex="50" />
						<p class="open_spop">
							<a> <span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 비고 -->
				<div class="field_long">
					<label><bean:message key="LABEL.remark" /></label>
					<div class="input_box">
						<html:textarea styleClass="ta50" property="mgrWorkflowPhaseDetailDTO.remark"
							tabindex="60" />
					</div>
				</div>
			</div>
			<!-- End of Form_box -->
		</div>
		<!-- End of Article_box -->
	</html:form>
</body>
</html:html>

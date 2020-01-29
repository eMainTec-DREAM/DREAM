<%--===========================================================================
Workflow Detail
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page
	import="dream.mgr.workflow.action.MgrWorkflowDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 승인Flow -->
<title><bean:message key='MENU.WORKFLOW' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */
var wfTypeAc;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else 
		{
			goUpdate();
			goTabPage("mgrWorkflowPhaseList");
		}
	
    setTitle("mgrWorkflowDetailDTO.wflistId", "mgrWorkflowDetailDTO.description");
    //For Save
    setForUpdate();
    //사용여부 자동완성
    acSysDesc("mgrWorkflowDetailDTO.isUse","mgrWorkflowDetailDTO.isUse","IS_USE", true);
    
    //업무구분 자동완성
    acSysDesc("mgrWorkflowDetailDTO.workflowTypeDesc","mgrWorkflowDetailDTO.workflowTypeId","WORKFLOW_TYPE", true);
    
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQAWFLIST_ID');
    
    mgrWorkflowDetailForm.elements['mgrWorkflowDetailDTO.isUse'].value = 'Y';
    valSysDir('mgrWorkflowDetailDTO.isUse', 'mgrWorkflowDetailDTO.isUse', 'IS_USE', true);
}
function setSequenceVal(sequenceVal)
{
    mgrWorkflowDetailForm.elements['mgrWorkflowDetailDTO.wflistId'].value = sequenceVal;
    mgrWorkflowDetailForm.elements['mgrWorkflowCommonDTO.wflistId'].value = sequenceVal;
}
/**
 * 수정
 */
function goUpdate()
{
	
}

/**
 * Tab 이동시 호출
 */
function goTabPage(pageId)
{
// 	var form = document.mgrWorkflowDetailForm;

// 	goCommonTabPage(form, '' , pageId);
	mgrWorkflowDetailForm.elements['mgrWorkflowCommonDTO.wflistId'].value = mgrWorkflowDetailForm.elements['mgrWorkflowDetailDTO.wflistId'].value;
	goCommonTabPage(mgrWorkflowDetailForm, '', pageId);
    
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
    if(ckCreate(currentPageId)) mgrWorkflowDetailForm.strutsAction.value = "<%=MgrWorkflowDetailAction.DETAIL_INPUT%>";
    else mgrWorkflowDetailForm.strutsAction.value = "<%=MgrWorkflowDetailAction.DETAIL_UPDATE%>";

		var actionUrl = contextPath + "/mgrWorkflowDetail.do";
		XMLHttpPost(actionUrl, FormQueryString(mgrWorkflowDetailForm),
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
			parent.findGridRow(mgrWorkflowDetailForm.elements['mgrWorkflowDetailDTO.wflistId'].value);

		mgrWorkflowDetailForm.elements['mgrWorkflowCommonDTO.wflistId'].value = mgrWorkflowDetailForm.elements['mgrWorkflowDetailDTO.wflistId'].value;
		getTopPage().afterSaveAll(currentPageId);
		setTitle("mgrWorkflowDetailDTO.wflistId",
				"mgrWorkflowDetailDTO.description");

	}
	/**
	 *  Audit trail
	 */
	 function goAudtrailLink()
	 {
	    var objectId = mgrWorkflowDetailForm.elements['mgrWorkflowDetailDTO.wflistId'].value;
	    var fileName = currentPageId;

	    if(typeof objectId=="undefined") return;

	    goAudTrailList(objectId, fileName);
	 }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

	<html:form action="/mgrWorkflowDetail">
		<html:hidden property="strutsAction" />
		<html:hidden property="currentPageId"/>
		<html:hidden property="mgrWorkflowCommonDTO.compNo" />
		<html:hidden property="mgrWorkflowCommonDTO.wflistId" />
		<!-- Key -->
		<html:hidden property="mgrWorkflowDetailDTO.compNo" />
		<html:hidden property="mgrWorkflowDetailDTO.wflistId" />
		
		<html:hidden property="mgrWorkflowDetailDTO.workflowTypeId" />

		<div class="article_box">
			<div class="form_box">
				<!-- 업무구분 -->
				<div class="field">
					<label><bean:message key="LABEL.wfType" /></label>
					<div class="input_box">
						<html:text property="mgrWorkflowDetailDTO.workflowTypeDesc"
							tabindex="10" />
						<p class="open_spop">
							<a> <span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 승인Flow 명 -->
				<div class="field_long">
					<label><bean:message key="LABEL.wfDesc" /></label>
					<div class="input_box">
						<html:text property="mgrWorkflowDetailDTO.description"
							tabindex="20" />
					</div>
				</div>
				<!-- 사용여부 -->
				<div class="field">
					<label><bean:message key="LABEL.isUse" /></label>
					<div class="input_box">
						<html:text property="mgrWorkflowDetailDTO.isUse"
							tabindex="30" />
						<p class="open_spop">
							<a> <span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 등록일 -->
				<div class="field">
					<label><bean:message key="LABEL.regDate" /></label>
					<div class="input_box">
						<html:text property="mgrWorkflowDetailDTO.regDate"
							tabindex="40" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
				</div>
				<!-- 비고 -->
				<div class="field_long">
					<label><bean:message key="LABEL.remark" /></label>
					<div class="input_box">
						<html:textarea styleClass="ta50" property="mgrWorkflowDetailDTO.remark"
							tabindex="50" />
					</div>
				</div>
			</div>
			<!-- End of Form_box -->
		</div>
		<!-- End of Article_box -->
	</html:form>
</body>
</html:html>

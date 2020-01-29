 <%--===========================================================================
서비스 마스터 디테일 페이지
author  cjscjs9
version $Id: workServiceDetail.jsp,v 1.1 2018/07/30 01:45:27 cjscjs9 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.service.action.WorkServiceDetailAction"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 프로그램 가이드 -->
<title><bean:message key='MENU.WORKSERVICE' /></title>
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
	
    setTitle("workServiceDetailDTO.serviceNo", "workServiceDetailDTO.serviceName");
    //For Save
    setForUpdate();
  //단위 자동완성
    acSysDesc("workServiceDetailDTO.serviceUomDesc","workServiceDetailDTO.serviceUomId","SERVICE_UOM", true);
    //사용여부 자동완성
    acSysDesc("workServiceDetailDTO.isUseDesc","workServiceDetailDTO.isUseId","IS_USE", true);

}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQASERVICE_ID');
    
    workServiceDetailForm.elements['workServiceDetailDTO.isUseId'].value = 'Y';
    workServiceDetailForm.elements['workServiceDetailDTO.isUseDesc'].value = 'Y';
    valSysDir('workServiceDetailDTO.isUseId', 'workServiceDetailDTO.isUseDesc', 'IS_USE', true);
}
function setSequenceVal(sequenceVal)
{
    workServiceDetailForm.elements['workServiceDetailDTO.serviceId'].value = sequenceVal;
    workServiceDetailForm.elements['workServiceDetailDTO.serviceNo'].value = sequenceVal;
    workServiceDetailForm.elements['workServiceDetailDTO.regDate'].value = getToday();
    workServiceDetailForm.elements['workServiceDetailDTO.deptId'].value = loginUser.deptId;
    workServiceDetailForm.elements['workServiceDetailDTO.deptDesc'].value = loginUser.deptDesc;
    workServiceDetailForm.elements['workServiceDetailDTO.empId'].value = loginUser.empId;
    workServiceDetailForm.elements['workServiceDetailDTO.empDesc'].value = loginUser.empName;
    workServiceDetailForm.elements['workServiceCommonDTO.serviceId'].value = sequenceVal;
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
    if(ckCreate(currentPageId)) workServiceDetailForm.strutsAction.value = "<%=WorkServiceDetailAction.DETAIL_INPUT%>";
    else workServiceDetailForm.strutsAction.value = "<%=WorkServiceDetailAction.DETAIL_UPDATE%>";

		var actionUrl = contextPath + "/workServiceDetail.do";
		XMLHttpPost(actionUrl, FormQueryString(workServiceDetailForm),
				'afterSave');

	}

	/**
	 * 저장후 호출
	 */
	function afterSave(ajaxXmlDoc) {
		//=====================================
		if (!checkHttpXml(ajaxXmlDoc)) return;
		//=====================================
		if (parent.findGridRow)
			parent.findGridRow(workServiceDetailForm.elements['workServiceDetailDTO.serviceId'].value);

		workServiceDetailForm.elements['workServiceCommonDTO.serviceId'].value = workServiceDetailForm.elements['workServiceDetailDTO.serviceId'].value;
	     getTopPage().afterSaveAll(currentPageId);
	     goUpdate();
		setTitle("workServiceDetailDTO.serviceNo",
				"workServiceDetailDTO.serviceName");

	}

	function goTabPage(pageId)
	{
		var form = document.workServiceDetailForm;

		if(pageId == "maDocLibList" || pageId == "maPtDocLibList")
		{	
			form.elements['maDocLibCommonDTO.objectId'].value = form.elements['workServiceDetailDTO.serviceId'].value;
			form.elements['maDocLibCommonDTO.objectType'].value = "CONTRACT_LIST";  //CONTRACT_LIST docDesc
			form.elements['maDocLibCommonDTO.description'].value = form.elements['workServiceDetailDTO.serviceName'].value;
			goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
		}
		else
			goCommonTabPage(form, '' , pageId);
	    
	}
	
	/**
	 *  Audit trail
	 */
	 function goAudtrailLink()
	 {
	    var objectId = workServiceDetailForm.elements['workServiceDetailDTO.serviceId'].value;
	    var fileName = currentPageId;

	    if(typeof objectId=="undefined") return;

	    goAudTrailList(objectId, fileName);
	 }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

	<html:form action="/workServiceDetail">
		<html:hidden property="strutsAction" />
		<html:hidden property="currentPageId"/>
		<html:hidden property="workServiceCommonDTO.serviceId" />
		<!-- Key -->
		<html:hidden property="workServiceDetailDTO.serviceId" />
		<!-- Key -->
		<html:hidden property="workServiceDetailDTO.deptId" />
		<html:hidden property="workServiceDetailDTO.empId" />
		<html:hidden property="workServiceDetailDTO.isUseId" />
		<html:hidden property="workServiceDetailDTO.serviceUomId" />
		
	 	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
	 	<html:hidden property="maDocLibCommonDTO.objectType" />
	 	<html:hidden property="maDocLibCommonDTO.description" />
		<div class="article_box">
			<div class="form_box">
				<!-- 서비스# -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.serviceNo" /></label>
					<div class="input_read">
						<html:text property="workServiceDetailDTO.serviceNo"
							readonly="true" />
					</div>
				</div>
				<!-- 서비스명 -->
				<div class="field_long">
					<label class="check"><bean:message key="LABEL.serviceName" /></label>
					<div class="input_box">
						<html:text property="workServiceDetailDTO.serviceName"
							tabindex="10" />
					</div>
				</div>
				<!-- 단위 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.uom" /></label>
					<div class="input_box">
						<html:text property="workServiceDetailDTO.serviceUomDesc"
							tabindex="20" />
						<p class="open_spop">
							<a> <span>조회</span>
							</a>
						</p>
					</div>
				</div>
  				<!-- 환산값 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.fitValue" /></label>
					<div class="input_box">
						<html:text property="workServiceDetailDTO.fitValue"
							tabindex="30" styleClass="num" />
					</div>
				</div>
				<!-- 사용여부 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.isUse" /></label>
					<div class="input_box">
						<html:text property="workServiceDetailDTO.isUseDesc"
							tabindex="40" />
						<p class="open_spop">
							<a> <span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 등록일자 -->
				<div class="field">
					<label><bean:message key="LABEL.regiDate" /></label>
					<div class="input_read">
						<html:text property="workServiceDetailDTO.regDate"
							  tabindex="50" readonly="true"/>
					</div>
				</div>
				<!-- 등록부서 -->
				<div class="field">
					<label><bean:message key="LABEL.deptId" /></label>
					<div class="input_read">
						<html:text property="workServiceDetailDTO.deptDesc"
							tabindex="60" readonly="true"/>
					</div>
				</div>
				<!-- 등록자 -->
				<div class="field">
					<label><bean:message key="LABEL.regId" /></label>
					<div class="input_read">
						<html:text property="workServiceDetailDTO.empDesc"
							tabindex="70" readonly="true"/>
					</div>
				</div>
				<!-- 비고 -->
				<div class="field_long">
					<label><bean:message key="LABEL.remark" /></label>
					<div class="input_box">
						<html:textarea property="workServiceDetailDTO.remark" styleClass="ta50" tabindex="120" />
					</div>
				</div>		
			</div>
			<!-- End of Form_box -->
		</div>
		<!-- End of Article_box -->
	</html:form>
</body>
</html:html>

 <%--===========================================================================
안전작업유형 점검항목 Detail page
author  euna0207
version $Id: workLetCategPointDetail.jsp,v 1.1 2015/12/03 01:45:27 euna0207 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.let.categ.action.WorkLetCategPointDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 안전작업유형 -->
<title><bean:message key='MENU.woLetCtg' /></title>
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
	
    setTitle("workLetCategPointDetailDTO.checkPoint");
    //For Save
    setForUpdate();
   
    //사용여부 자동완성
    acSysDesc("workLetCategPointDetailDTO.isUseDesc","workLetCategPointDetailDTO.isUseId","IS_USE", true);
    //수치판정구분 자동완성
    acSysDesc("workLetCategPointDetailDTO.checkTypeDesc","workLetCategPointDetailDTO.checkTypeId","CHECK_TYPE",true);
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQAWOLETCTGPOINT_ID');
    
    workLetCategPointDetailForm.elements['workLetCategPointDetailDTO.isUseId'].value = 'Y';
    workLetCategPointDetailForm.elements['workLetCategPointDetailDTO.isUseDesc'].value = 'Y';
    valSysDir('workLetCategPointDetailDTO.isUseId', 'workLetCategPointDetailDTO.isUseDesc', 'IS_USE', true);
}

function setSequenceVal(sequenceVal)
{
    workLetCategPointDetailForm.elements['workLetCategPointDetailDTO.woLetCtgPointId'].value = sequenceVal;
    workLetCategPointDetailForm.elements['workLetCategPointListDTO.woLetCtgPointId'].value = sequenceVal;
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
	goCommonTabPage(workLetCategPointDetailForm, '', pageId);
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
    if(ckCreate(currentPageId)) workLetCategPointDetailForm.strutsAction.value = "<%=WorkLetCategPointDetailAction.DETAIL_INPUT%>";
    else workLetCategPointDetailForm.strutsAction.value = "<%=WorkLetCategPointDetailAction.DETAIL_UPDATE%>";

		var actionUrl = contextPath + "/workLetCategPointDetail.do";
		XMLHttpPost(actionUrl, FormQueryString(workLetCategPointDetailForm),'afterSave');

	}

	/**
	 * 저장후 호출
	 */

	function afterSave(ajaxXmlDoc)
	
	{ 
		//=====================================
		if (!checkHttpXml(ajaxXmlDoc)) return;
		//=====================================
		if (parent.findGridRow)
		parent.findGridRow(workLetCategPointDetailForm.elements['workLetCategPointDetailDTO.woLetCtgPointId'].value);

		workLetCategPointDetailForm.elements['workLetCategPointDetailDTO.woLetCtgPointId'].value = workLetCategPointDetailForm.elements['workLetCategPointListDTO.woLetCtgPointId'].value;
		getTopPage().afterSaveAll(currentPageId);
		setTitle("workLetCategPointDetailDTO.stepNum", "workLetCategPointDetailDTO.checkPoint");
	}
	
	 /** 탭 안에 또 다른 탭 있는 경우, goOpen, goTabPage */
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = workLetCategPointDetailForm.elements['workLetCategPointDetailDTO.woLetCtgPointId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

	<html:form action="/workLetCategPointDetail">
		<html:hidden property="strutsAction" />
		<html:hidden property="currentPageId"/>
		<!-- key -->
		<html:hidden property="workLetCategCommonDTO.woLetCtgId" />
		<!-- Key -->
		<html:hidden property="workLetCategPointListDTO.woLetCtgPointId" />
		<!-- Key -->
		<html:hidden property="workLetCategPointDetailDTO.woLetCtgPointId" />
		<html:hidden property="workLetCategPointDetailDTO.woLetCtgId" />
		<html:hidden property="workLetCategPointDetailDTO.checkTypeId" />
		<html:hidden property="workLetCategPointDetailDTO.isUseId" />

		<div class="article_box">
			<div class="form_box">
				<!-- 점검순서 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.pmStepNum" /></label>
					<div class="input_read">
						<html:text property="workLetCategPointDetailDTO.stepNum"
							readonly="true" tabindex="10" styleClass="num"/>
					</div>
				</div>
				<div class="field">
				</div>	
				<!-- 점검항목 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.maPmMstrPointList" /></label>
					<div class="input_box">
						<html:text property="workLetCategPointDetailDTO.checkPoint"
							tabindex="15" />
					</div>
				</div>
				<!-- 점검부위 -->
				<div class="field">
					<label><bean:message key="LABEL.pmAsmb" /></label>
					<div class="input_box">
						<html:text property="workLetCategPointDetailDTO.checkPosition"
							tabindex="20" />
					</div>
				</div>
				<!-- 적정기준 -->
				<div class="field">
					<label><bean:message key="LABEL.fitBasis" /></label>
					<div class="input_box">
						<html:text property="workLetCategPointDetailDTO.fitBasis"
							tabindex="25" />
					</div>
				</div>
				<!-- 점검방법 -->
				<div class="field">
					<label><bean:message key="LABEL.checkMethod" /></label>
					<div class="input_box">
						<html:text property="workLetCategPointDetailDTO.checkMethod"
							tabindex="30" />
					</div>
				</div>
				<!-- 수치/판정구분 -->				
				<div class="field">
					<label class="check"><bean:message key="LABEL.checkType" /></label>
					<div class="input_box">
						<html:text property="workLetCategPointDetailDTO.checkTypeDesc"
							tabindex="35" />
						<p class="open_spop">
							<a> <span>조회</span>
							</a>
						</p>				
					</div>
				</div>
				<!-- 시행여부 -->
				<div class="field">
					<label><bean:message key="LABEL.isActive" /></label>
					<div class="input_box">
						<html:text property="workLetCategPointDetailDTO.isUseDesc"
							tabindex="40" />
						<p class="open_spop">
							<a> <span>조회</span>
							</a>
						</p>
					</div>
				</div>				
				<!-- 하한값 -->				
				<div class="field">
					<label><bean:message key="LABEL.checkMin" /></label>
					<div class="input_box">
						<html:text property="workLetCategPointDetailDTO.checkMin"
							tabindex="45" styleClass="num"/>
					</div>
				</div>
				<div class="field">
				</div>	
				<!-- 기준값 -->				
				<div class="field">
					<label><bean:message key="LABEL.checkBasisVal" /></label>
					<div class="input_box">
						<html:text property="workLetCategPointDetailDTO.checkBasisVal"
							tabindex="50" styleClass="num"/>
					</div>
				</div>				
				<div class="field">
				</div>	
				<!-- 상한값 -->				
				<div class="field">
					<label><bean:message key="LABEL.checkMax" /></label>
					<div class="input_box">
						<html:text property="workLetCategPointDetailDTO.checkMax"
							tabindex="55" styleClass="num"/>
					</div>
				</div>
				<!-- 단위 -->				
				<div class="field">
					<label><bean:message key="LABEL.uom" /></label>
					<div class="input_box">
						<html:text property="workLetCategPointDetailDTO.uom"
							tabindex="60"/>
					</div>
				</div>
				<!-- 비고 -->
				<div class="field_long">
					<label><bean:message key="LABEL.remark"/></label>
					<div class="input_box">
						<html:textarea property="workLetCategPointDetailDTO.remark" styleClass="ta50" 
							tabindex="65" />
					</div>
				</div>
			</div>
			<!-- End of Form_box -->
		</div>
		<!-- End of Article_box -->
	</html:form>
</body>
</html:html>

 <%--===========================================================================
안전작업유형 보완사항 Detail page
author  euna0207
version $Id: workLetCategEtcDetail.jsp,v 1.1 2015/12/03 01:45:27 euna0207 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.let.categ.action.WorkLetCategEtcDetailAction"%>
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
	
    setTitle("workLetCategEtcDetailDTO.ordNo", "workLetCategEtcDetailDTO.description");
    //For Save
    setForUpdate();
  //사용여부 자동완성
    acSysDesc("workLetCategEtcDetailDTO.isUseDesc","workLetCategEtcDetailDTO.isUseId","IS_USE", true);
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQAWOLETCTGETC_ID');
    
    workLetCategEtcDetailForm.elements['workLetCategEtcDetailDTO.isUseId'].value = 'Y';
    workLetCategEtcDetailForm.elements['workLetCategEtcDetailDTO.isUseDesc'].value = 'Y';
    valSysDir('workLetCategEtcDetailDTO.isUseId', 'workLetCategEtcDetailDTO.isUseDesc', 'IS_USE', true);
}
function setSequenceVal(sequenceVal)
{
    workLetCategEtcDetailForm.elements['workLetCategEtcDetailDTO.woLetCtgEtcId'].value = sequenceVal;
    workLetCategEtcDetailForm.elements['workLetCategEtcListDTO.woLetCtgEtcId'].value = sequenceVal;
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
	goCommonTabPage(workLetCategEtcDetailForm, '', pageId);
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
    if(ckCreate(currentPageId)) workLetCategEtcDetailForm.strutsAction.value = "<%=WorkLetCategEtcDetailAction.DETAIL_INPUT%>";
    else workLetCategEtcDetailForm.strutsAction.value = "<%=WorkLetCategEtcDetailAction.DETAIL_UPDATE%>";

		var actionUrl = contextPath + "/workLetCategEtcDetail.do";
		XMLHttpPost(actionUrl, FormQueryString(workLetCategEtcDetailForm), 'afterSave');

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
		parent.findGridRow(workLetCategEtcDetailForm.elements['workLetCategEtcDetailDTO.woLetCtgEtcId'].value);

		workLetCategEtcDetailForm.elements['workLetCategEtcDetailDTO.woLetCtgEtcId'].value = workLetCategEtcDetailForm.elements['workLetCategEtcListDTO.woLetCtgEtcId'].value;
		getTopPage().afterSaveAll(currentPageId);
		setTitle("workLetCategEtcDetailDTO.ordNo", "workLetCategEtcDetailDTO.description");

	}
	/**
	 *  Audit trail
	 */
	 function goAudtrailLink()
	 {
	    var objectId = workLetCategEtcDetailForm.elements['workLetCategEtcDetailDTO.woLetCtgEtcId'].value;
	    var fileName = currentPageId;

	    if(typeof objectId=="undefined") return;

	    goAudTrailList(objectId, fileName);
	 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

	<html:form action="/workLetCategEtcDetail">
		<html:hidden property="strutsAction" />
		<html:hidden property="currentPageId"/>
		<html:hidden property="workLetCategCommonDTO.woLetCtgId" /><!-- key -->
		<html:hidden property="workLetCategEtcListDTO.woLetCtgEtcId" /><!-- key -->
		<html:hidden property="workLetCategEtcDetailDTO.woLetCtgEtcId" /><!-- key -->
		<html:hidden property="workLetCategEtcDetailDTO.woLetCtgId" />
		<html:hidden property="workLetCategEtcDetailDTO.isUseId" />

		<div class="article_box">
			<div class="form_box">
				<!-- 추가사항 -->
				<div class="field_long">
					<label><bean:message key="LABEL.woLetAdd" /></label>
					<div class="input_box">
						<html:text property="workLetCategEtcDetailDTO.description"
							tabindex="10"  />
					</div>
				</div>
				<!-- 사용여부 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.isUse" /></label>
					<div class="input_box">
						<html:text property="workLetCategEtcDetailDTO.isUseDesc"
							tabindex="20" />
						<p class="open_spop">
							<a> <span>조회</span>
							</a>
						</p>	
					</div>
				</div>
				<!-- 조회순서 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.ordBy" /></label>
					<div class="input_box">
						<html:text property="workLetCategEtcDetailDTO.ordNo"
							tabindex="30"/>
					</div>
				</div>
				<!-- 비고 -->
				<div class="field_long">
					<label><bean:message key="LABEL.remark"/></label>
					<div class="input_box">
						<html:textarea property="workLetCategEtcDetailDTO.remark" styleClass="ta50" 
							tabindex="40" />
					</div>
				</div>
				</div>
			</div>
			<!-- End of Form_box -->
		</div>
		<!-- End of Article_box -->
	</html:form>
</body>
</html:html>

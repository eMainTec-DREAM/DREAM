<%--===========================================================================
Criticality Matrix Detail
author  kim21017
version $Id: rcmCrityDetail.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.rcm.crity.action.RcmCrityDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<html:html>
<head>
<!-- Criticality Matrix -->
<title><bean:message key='MENU.CRITICALMATRIX'/></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */
var isUseAc;

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goUpdate();
	} 
	
    setTitle("rcmCrityDetailDTO.crityListNo", "rcmCrityDetailDTO.crityListDesc");
    //For Save
    setForUpdate();
    //설비상태 자동완성
    acSysDesc("rcmCrityDetailDTO.isUseDesc","rcmCrityDetailDTO.isUseId","IS_USE", true);
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQACRITYLIST_ID');
    
    rcmCrityDetailForm.elements['rcmCrityDetailDTO.isUseId'].value = 'Y';
    rcmCrityDetailForm.elements['rcmCrityDetailDTO.isUseDesc'].value = 'Y';
    valSysDir('rcmCrityDetailDTO.isUseId', 'rcmCrityDetailDTO.isUseDesc', 'IS_USE', true);
    rcmCrityDetailForm.elements['rcmCrityDetailDTO.regDate'].value   = getToday();
}
function setSequenceVal(sequenceVal)
{
    rcmCrityDetailForm.elements['rcmCrityDetailDTO.crityListId'].value = sequenceVal;
    rcmCrityDetailForm.elements['rcmCrityDetailDTO.crityListNo'].value = sequenceVal;
    rcmCrityDetailForm.elements['rcmCrityCommonDTO.crityListId'].value = sequenceVal;
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
    if(ckCreate(currentPageId)) rcmCrityDetailForm.strutsAction.value = "<%=RcmCrityDetailAction.DETAIL_INPUT%>";
    else rcmCrityDetailForm.strutsAction.value = "<%=RcmCrityDetailAction.DETAIL_UPDATE%>";
    
	var actionUrl = contextPath + "/rcmCrityDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(rcmCrityDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(rcmCrityDetailForm.elements['rcmCrityDetailDTO.crityListId'].value);
    
    rcmCrityDetailForm.elements['rcmCrityCommonDTO.crityListId'].value = rcmCrityDetailForm.elements['rcmCrityDetailDTO.crityListId'].value;
    getTopPage().afterSaveAll(currentPageId);
    setTitle("rcmCrityDetailDTO.crityListNo", "rcmCrityDetailDTO.crityListDesc");
    
}

/**
 * Matrix 보기 
 */
 function goMatrixview(){
	 	var url   = contextPath + "/rcmCrityMatrix.do";
	 	var popWidth = 1280;
	 	var popHeight = 640;
	     // pop up이 중앙에 위치하게 한다.
	     var TopPosition  = (screen.height/2 - popHeight/2);
	     var LeftPosition = (screen.width/2 - popWidth/2);

	     var pos = "width=" + popWidth + ",height=" + popHeight + "" +
	               ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
	     pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
	 	
	 	var param = "isDecoratorName=popupPage"+
	 				"&strutsAction="+
	 				"&rcmCrityCommonDTO.crityListId="+rcmCrityDetailForm.elements['rcmCrityCommonDTO.crityListId'].value
	 				;

	 	//post 전송
	 	openWindowWithPost(url, "", param, pos);
}
 function goTabPage(pageId) 
 {
	 var form = document.rcmCrityDetailForm;
	 goCommonTabPage(form, '' , pageId);
 }
 /**
  *  Audit trail
  */
  function goAudtrailLink()
  {
     var objectId = rcmCrityDetailForm.elements['rcmCrityDetailDTO.crityListId'].value;
     var fileName = currentPageId;

     if(typeof objectId=="undefined") return;

     goAudTrailList(objectId, fileName);
  }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

<html:form action="/rcmCrityDetail" >
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="rcmCrityCommonDTO.crityListId"/><!-- Key -->
<html:hidden property="rcmCrityDetailDTO.crityListId"/><!-- Key -->
<html:hidden property="rcmCrityDetailDTO.isUseId"/>
	 
	<div class="article_box">
		<div class="form_box">
			<!-- Crity Matrix No -->
			<div class="field">
				<label><bean:message key="LABEL.crityListNo"/></label>
				<div class="input_read">
					<html:text property="rcmCrityDetailDTO.crityListNo" readonly="true"/>
				</div>
			</div>
			<!-- Crity Matrix 명 -->
			<div class="field_long">
				<label class="check"><bean:message key="LABEL.crityListName"/></label>
				<div class="input_box">
					<html:text property="rcmCrityDetailDTO.crityListDesc" tabindex="10"/>
				</div>
			</div>
			<!-- 사용여부 -->
			<div class="field">
				<label><bean:message key="LABEL.isUse"/></label>
				<div class="input_box">
					<html:text property="rcmCrityDetailDTO.isUseDesc" tabindex="20" />
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 등록일 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.regDate"/></label>
				<div class="input_box">
					<html:text property="rcmCrityDetailDTO.regDate" tabindex="30" />
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			<!-- remark -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="rcmCrityDetailDTO.remark" styleClass="ta50" tabindex="40" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>

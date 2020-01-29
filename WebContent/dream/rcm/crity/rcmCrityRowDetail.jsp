<%--===========================================================================
Criticality Matrix Row Detail
author  kim21017
version $Id: rcmCrityRowDetail.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.rcm.crity.action.RcmCrityRowDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 항목 -->
<title><bean:message key='LABEL.prompt'/></title>
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
	
    setTitle("rcmCrityRowDetailDTO.ordNo", "rcmCrityRowDetailDTO.crityRowDesc");
    //For Save
    setForUpdate();
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQACRITYROW_ID');
}
function setSequenceVal(sequenceVal)
{
    rcmCrityRowDetailForm.elements['rcmCrityRowDetailDTO.crityRowId'].value = sequenceVal;
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
    if(ckCreate(currentPageId)) rcmCrityRowDetailForm.strutsAction.value = "<%=RcmCrityRowDetailAction.DETAIL_INPUT%>";
    else rcmCrityRowDetailForm.strutsAction.value = "<%=RcmCrityRowDetailAction.DETAIL_UPDATE%>";
    
	var actionUrl = contextPath + "/rcmCrityRowDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(rcmCrityRowDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(rcmCrityRowDetailForm.elements['rcmCrityRowDetailDTO.crityRowId'].value);
    getTopPage().afterSaveAll(currentPageId);
    setTitle("rcmCrityRowDetailDTO.ordNo", "rcmCrityRowDetailDTO.crityRowDesc");
    
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = rcmCrityRowDetailForm.elements['rcmCrityRowDetailDTO.crityRowId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

<html:form action="/rcmCrityRowDetail.do" >
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="rcmCrityCommonDTO.crityListId"/><!-- Key -->
<html:hidden property="rcmCrityRowDetailDTO.crityRowId"/><!-- Key -->
	 
	<div class="article_box">
		<div class="form_box">
			<!-- level -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.level"/></label>
				<div class="input_box">
					<html:text property="rcmCrityRowDetailDTO.crityLevel" tabindex="1" />
				</div>
			</div>
			<!-- 항목 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.prompt"/></label>
				<div class="input_box">
					<html:text property="rcmCrityRowDetailDTO.crityRowDesc" tabindex="10" />
				</div>
			</div>
			<!-- 조회순서 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.ordNo"/></label>
				<div class="input_box">
					<html:text property="rcmCrityRowDetailDTO.ordNo" tabindex="20"/>
				</div>
			</div>
			<!-- remark -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="rcmCrityRowDetailDTO.remark" styleClass="ta50" tabindex="30" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>

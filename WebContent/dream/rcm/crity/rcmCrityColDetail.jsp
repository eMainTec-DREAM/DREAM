<%--===========================================================================
Criticality Matrix Col Detail
author  kim21017
version $Id: rcmCrityColDetail.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.rcm.crity.action.RcmCrityColDetailAction"%>
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
	
    setTitle("rcmCrityColDetailDTO.ordNo", "rcmCrityColDetailDTO.crityColDesc");
    //For Save
    setForUpdate();
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQACRITYCOL_ID');
}
function setSequenceVal(sequenceVal)
{
    rcmCrityColDetailForm.elements['rcmCrityColDetailDTO.crityColId'].value = sequenceVal;
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
    if(ckCreate(currentPageId)) rcmCrityColDetailForm.strutsAction.value = "<%=RcmCrityColDetailAction.DETAIL_INPUT%>";
    else rcmCrityColDetailForm.strutsAction.value = "<%=RcmCrityColDetailAction.DETAIL_UPDATE%>";
    
	var actionUrl = contextPath + "/rcmCrityColDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(rcmCrityColDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(rcmCrityColDetailForm.elements['rcmCrityColDetailDTO.crityColId'].value);
    getTopPage().afterSaveAll(currentPageId);
    setTitle("rcmCrityColDetailDTO.ordNo", "rcmCrityColDetailDTO.crityColDesc");
    
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = rcmCrityColDetailForm.elements['rcmCrityColDetailDTO.crityColId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/rcmCrityColDetail.do" >
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="rcmCrityCommonDTO.crityListId"/><!-- Key -->
<html:hidden property="rcmCrityColDetailDTO.crityColId"/><!-- Key -->
	 
	<div class="article_box">
		<div class="form_box">
			<!-- 항목 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.prompt"/></label>
				<div class="input_box">
					<html:text property="rcmCrityColDetailDTO.crityColDesc" tabindex="10" />
				</div>
			</div>
			<!-- 조회순서 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.ordNo"/></label>
				<div class="input_box">
					<html:text property="rcmCrityColDetailDTO.ordNo" tabindex="20"/>
				</div>
			</div>
			<!-- remark -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="rcmCrityColDetailDTO.remark" styleClass="ta50" tabindex="30" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>

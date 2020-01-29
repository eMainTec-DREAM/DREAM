<%--===========================================================================
기능정의
author  kim21017
version $Id: rcmSysFDefDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%> 
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.rcm.system.action.RcmSysFDefDetailAction"%>
<html>
<head>
<!-- 기능정의-->
<title><bean:message key="TAB.rcmSysFDefList"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
	setTitle("rcmSysFDefDetailDTO.rcmFuncNo","rcmSysFDefDetailDTO.description");
	setForUpdate();
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQARCMFUNC_ID');
	$(".accordion_wrap").hide();
}

function goUpdate()
{
	disableFormObject();
}

function disableFormObject()
{
	rcmSysFDefDetailForm.elements['rcmSysFDefDetailDTO.rcmFuncNo'].readOnly = true;
	document.getElementById("funcNoDiv").className = "input_read"; 
}

function setSequenceVal(sequenceVal)
{
	rcmSysFDefDetailForm.elements['rcmSysFDefDetailDTO.rcmFuncId'].value = sequenceVal;
}

function goSave(){
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)) rcmSysFDefDetailForm.strutsAction.value = '<%=RcmSysFDefDetailAction.RCM_SYS_FDEF_DETAIL_INPUT%>';
	else rcmSysFDefDetailForm.strutsAction.value = '<%= RcmSysFDefDetailAction.RCM_SYS_FDEF_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/rcmSysFDefDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(rcmSysFDefDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    //parent.goSearch(); 
	if (parent.findGridRow)	parent.findGridRow(rcmSysFDefDetailForm.elements['rcmSysFDefDetailDTO.rcmFuncId'].value);
	setTitle("rcmSysFDefDetailDTO.rcmFuncNo","rcmSysFDefDetailDTO.description");
    getTopPage().afterSaveAll(currentPageId);
    
    disableFormObject();
    $(".accordion_wrap").show();
}

function goTabPage(pageId)
{
	var form = document.rcmSysFDefDetailForm;

	goCommonTabPage(form, '' , pageId);
    
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = rcmSysFDefDetailForm.elements['rcmSysFDefDetailDTO.rcmFuncId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/rcmSysFDefDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="rcmSysCommonDTO.rcmListId"/>
	<html:hidden property="rcmSysFDefDetailDTO.rcmListId"/>
	<html:hidden property="rcmSysFDefDetailDTO.rcmFuncId"/>
	
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 기능코드 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.funcNo"/></label>
				<div class="input_box" id="funcNoDiv">
					<html:text property="rcmSysFDefDetailDTO.rcmFuncNo" tabindex="10"/>
				</div>
			</div>
			<div class="field_blank"></div>
			<!-- 기능명 -->
			<div class="field_long">
				<label class="check"><bean:message key="LABEL.funcName"/></label>
				<div class="input_box">
					<html:text property="rcmSysFDefDetailDTO.description" tabindex="20"/>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="rcmSysFDefDetailDTO.remark" styleClass="ta50" tabindex="30"/>
				</div>
			</div>
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>
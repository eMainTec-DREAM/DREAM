
<%--===========================================================================
설비부위 상세
author  kim21017
version $Id: rcmSysEqAsmbDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ page import="common.bean.User"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%@page import="dream.rcm.system.action.RcmSysEqAsmbDetailAction"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!--답변사항 -->
<title><bean:message key="LABEL.questionPoint"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var asmbDescAc;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
	setTitle("rcmSysEqAsmbDetailDTO.eqAsmbDesc");
	setForUpdate();
	
	asmbDescAc = new autoC({"rcmSysEqAsmbDetailDTO.eqAsmbDesc":"full_desc"});
	asmbDescAc.setAcConditionMap({
 	   "equip_id":rcmSysEqAsmbDetailForm.elements['rcmSysEqAsmbListDTO.equipId'].value 	   
 	   });
	asmbDescAc.setTable("TAEQASMB");
	asmbDescAc.setAcResultMap({
        "rcmSysEqAsmbDetailDTO.eqAsmbId":"eqasmb_id"
    });
	asmbDescAc.setKeyName("rcmSysEqAsmbDetailDTO.eqAsmbId");
	asmbDescAc.init();
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQARCMEQASMB_ID');
}

function goUpdate()
{
	disableFormObject();
}

function disableFormObject()
{
	rcmSysEqAsmbDetailForm.elements['rcmSysEqAsmbDetailDTO.eqAsmbDesc'].readOnly = true;
	document.getElementById("eqAsmbDiv").className = "input_read"; 
    document.getElementById("eqAsmbPopDiv").style.display = "none";
}

function setSequenceVal(sequenceVal)
{
	rcmSysEqAsmbDetailForm.elements['rcmSysEqAsmbDetailDTO.rcmEqAsmbId'].value = sequenceVal;
}

function goSave(){
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	rcmSysEqAsmbDetailForm.elements['rcmSysEqAsmbDetailDTO.rcmEqId'].value=rcmSysEqAsmbDetailForm.elements['rcmSysEqAsmbListDTO.rcmEqId'].value;
	
	if(ckCreate(currentPageId)) rcmSysEqAsmbDetailForm.strutsAction.value = '<%=RcmSysEqAsmbDetailAction.RCM_SYS_EQASMB_DETAIL_INPUT%>';
	else rcmSysEqAsmbDetailForm.strutsAction.value = '<%= RcmSysEqAsmbDetailAction.RCM_SYS_EQASMB_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/rcmSysEqAsmbDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(rcmSysEqAsmbDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(rcmSysEqAsmbDetailForm.elements['rcmSysEqAsmbDetailDTO.rcmEqAsmbId'].value);

	setTitle("rcmSysEqAsmbDetailDTO.eqAsmbDesc");
    getTopPage().afterSaveAll(currentPageId);
    
    disableFormObject();
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = rcmSysEqAsmbDetailForm.elements['rcmSysEqAsmbDetailDTO.rcmEqAsmbId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>

</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/rcmSysEqAsmbDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="rcmSysCommonDTO.rcmListId"/>
<html:hidden property="rcmSysEqAsmbListDTO.rcmEqId"/><!-- Key -->
<html:hidden property="rcmSysEqAsmbListDTO.equipId"/><!-- Key -->
<html:hidden property="rcmSysEqAsmbDetailDTO.rcmListId"/>
<html:hidden property="rcmSysEqAsmbDetailDTO.rcmEqAsmbId"/>
<html:hidden property="rcmSysEqAsmbDetailDTO.rcmEqId"/>
<html:hidden property="rcmSysEqAsmbDetailDTO.eqAsmbId"/>
    <!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 부위 -->
			<div class="field">
			<label class="check"><bean:message key="LABEL.asmb"/></label>
			<div class="input_box" id="eqAsmbDiv">
				<html:text property="rcmSysEqAsmbDetailDTO.eqAsmbDesc" tabindex="50" />
				<p class="open_spop" id="eqAsmbPopDiv">
					<a>
						<span>조회</span>
					</a>
				</p>
			</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="rcmSysEqAsmbDetailDTO.remark" styleClass="ta50" tabindex="60" />
				</div>
			</div>
		</div>
	</div><!--article_box end-->
</html:form> 
</body>
</html>
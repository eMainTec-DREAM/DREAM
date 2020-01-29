
<%--===========================================================================
응답 상세
author  kim21017
version $Id: rcmSysFEnvDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
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
<%@page import="dream.rcm.system.action.RcmSysFEnvDetailAction"%>
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
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("rcmSysFEnvDetailDTO.rcmFEnvTypeDesc","rcmSysFEnvDetailDTO.description");
	setForUpdate();
	
	acSysDesc("rcmSysFEnvDetailDTO.rcmFEnvTypeDesc","rcmSysFEnvDetailDTO.rcmFEnvType","RCMFENV_TYPE");
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQARCMFENV_ID');
}

function setSequenceVal(sequenceVal)
{
	rcmSysFEnvDetailForm.elements['rcmSysFEnvDetailDTO.rcmFEnvId'].value = sequenceVal;
}

function goSave(){
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	rcmSysFEnvDetailForm.elements['rcmSysFEnvDetailDTO.rcmFuncId'].value=rcmSysFEnvDetailForm.elements['rcmSysFEnvListDTO.rcmFuncId'].value;
	
	if(ckCreate(currentPageId)) rcmSysFEnvDetailForm.strutsAction.value = '<%=RcmSysFEnvDetailAction.RCM_SYS_FENV_DETAIL_INPUT%>';
	else rcmSysFEnvDetailForm.strutsAction.value = '<%= RcmSysFEnvDetailAction.RCM_SYS_FENV_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/rcmSysFEnvDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(rcmSysFEnvDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(rcmSysFEnvDetailForm.elements['rcmSysFEnvDetailDTO.rcmFEnvId'].value);

	setTitle("rcmSysFEnvDetailDTO.rcmFEnvTypeDesc","rcmSysFEnvDetailDTO.description");
    getTopPage().afterSaveAll(currentPageId);
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = rcmSysFEnvDetailForm.elements['rcmSysFEnvDetailDTO.rcmFEnvId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>

</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/rcmSysFEnvDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="rcmSysCommonDTO.rcmListId"/>
<html:hidden property="rcmSysFEnvListDTO.rcmFuncId"/><!-- Key -->
<html:hidden property="rcmSysFEnvDetailDTO.rcmListId"/>
<html:hidden property="rcmSysFEnvDetailDTO.rcmFEnvId"/>
<html:hidden property="rcmSysFEnvDetailDTO.rcmFuncId"/>
<html:hidden property="rcmSysFEnvDetailDTO.rcmFEnvType"/>
    <!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 항목 -->
			<div class="field">
			<label class="check"><bean:message key="LABEL.prompt"/></label>
			<div class="input_box">
				<html:text property="rcmSysFEnvDetailDTO.rcmFEnvTypeDesc" tabindex="50" />
				<p class="open_spop">
					<a>
						<span>조회</span>
					</a>
				</p>
			</div>
			</div>
			<!-- 값 -->
			<div class="field">
                <label class="check"><bean:message key="LABEL.value"/></label>
                <div class="input_box">
                    <html:text property="rcmSysFEnvDetailDTO.description" tabindex="30"/>
                </div>
             </div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="rcmSysFEnvDetailDTO.remark" styleClass="ta50" tabindex="60" />
				</div>
			</div>
		</div>
	</div><!--article_box end-->
</html:form> 
</body>
</html>
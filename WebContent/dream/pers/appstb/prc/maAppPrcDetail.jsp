<%--===========================================================================
칼럼 상세 
author  jung7126
version $Id: appPrcColDetail.jsp,v 1.0 2015/12/04 07:26:18 jung7126 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="dream.pers.appstb.prc.action.AppPrcDetailAction" %>
<html>
<head>
<!--결재자 -->
<title><bean:message key="LABEL.columnId"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!--//

function loadPage() 
{
	setTitle("appPrcDetailDTO.apprByName");
	
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goUpdate();
	}
	
	setForUpdate();
	setReadOnly();
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAAPPRUSR_ID');
	//appPrcDetailForm.elements['appPrcDetailDTO.hidden'].value = 'N';
}

function goUpdate()
{
	appPrcDetailForm.strutsAction.value = '<%=AppPrcDetailAction.APP_PRC_UPDATE%>';
}

function setSequenceVal(sequenceVal)
{
	appPrcDetailForm.elements['appPrcDetailDTO.apprusrId'].value = sequenceVal;
	appPrcDetailForm.elements['appReqCommonDTO.apprusrId'].value = sequenceVal;
	
	appPrcDetailForm.strutsAction.value = '<%=AppPrcDetailAction.APP_PRC_INPUT%>';
	//goSaveAction();
}

function goSaveAction()
{
	var actionUrl = contextPath + "/appPrcDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(appPrcDetailForm), 'afterSave');
}

function goSave()
{
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;

	appPrcDetailForm.elements['appPrcDetailDTO.apprlistId'].value = appPrcDetailForm.elements['appReqCommonDTO.apprlistId'].value;
	
	var actionUrl = contextPath + "/appPrcDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(appPrcDetailForm), 'afterSave');

}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    //parent.goSearch();
    
    goUpdate();

	if (parent.findGridRow)	parent.findGridRow(appPrcDetailForm.elements['appReqCommonDTO.apprusrId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
}


//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/appPrcDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="appReqCommonDTO.apprusrId"/>
<html:hidden property="appReqCommonDTO.apprlistId"/>

<html:hidden property="appPrcDetailDTO.apprBy"/>
<html:hidden property="appPrcDetailDTO.apprusrId"/>
<html:hidden property="appPrcDetailDTO.apprlistId"/>


    <!-- searchbox 박스 Line -->
	       <div class="article_box">
	           <div class="form_box">
				<!-- 결재순서 -->
	               <div class="field">
		               <label class="check"><bean:message key="LABEL.apprSeq"/></label>
	               	   <div class="input_box">
	               	   		<html:text property="appPrcDetailDTO.apprSeq" tabindex="10" readonly="true"/>
	               	   </div>
               	   </div>
				<!-- 결재자 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.apprByName"/></label>
					<div class="input_box">
						<html:text property="appPrcDetailDTO.apprByName" tabindex="50" readonly="true"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 직위 -->
				<div class="field">
					<label><bean:message key="LABEL.grade"/></label>
					<div class="input_read">
							<html:text property="appPrcDetailDTO.grade" tabindex="30" readonly="true"/>
					</div>
				</div>
				<!-- 부서 -->
				<div class="field">
	               <label><bean:message key="LABEL.dept"/></label>
               	   <div class="input_read">
               	   		<html:text property="appPrcDetailDTO.deptName" tabindex="40" readonly="true"/>
               	   </div>
				</div>
				<!-- 비고 -->
				<div class="field_long">
					<label><bean:message key="LABEL.appRemark"/></label>
					<div class="input_box">
						<html:textarea property="appPrcDetailDTO.remark" styleClass="ta50" tabindex="250" readonly="true" />
					</div>
				</div>
			</div>
		</div><!--article_box end-->
</html:form> 
</body>
</html>
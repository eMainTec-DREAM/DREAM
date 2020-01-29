
<%--===========================================================================
시스템 환경변수 - 상세
author  kim21017
version $Id: consultCompConfigDetail.jsp,v 1.5 2014/07/02 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.consult.comp.config.action.ConsultCompConfigDetailAction"%>
<html:html>
<head>
<!-- 시스템 환경변수 -->
<title><bean:message key='TAB.consultCompConfigDetail' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">// 저장 시 수행되는 action
var saveStrutsAction;

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("consultCompConfigDetailDTO.compconfigName","consultCompConfigDetailDTO.compconfigValue");
	//For Save
	setForUpdate();
	
	compAc = new autoC({"consultCompConfigDetailDTO.compDesc":"description"});
    compAc.setTable("TACOMP");
    compAc.setKeyName("consultCompConfigDetailDTO.compNo");
    compAc.setCustomLov("lovComp('consultCompConfigDetailDTO.compNo', 'consultCompConfigDetailDTO.compDesc')");
    compAc.setAcResultMap({
        "consultCompConfigDetailDTO.compNo":"COMP_NO"
    });
    compAc.init();
    
    
}

/**
 * 입력
 */
function goInput()
{
	sequenceNextVal('SQACONFIG_ID');
	consultCompConfigDetailForm.elements['consultCompConfigDetailDTO.isSystem'].value = 'Y';
}

function setSequenceVal(sequenceVal)
{
	consultCompConfigDetailForm.elements['consultCompConfigDetailDTO.compconfigId'].value = sequenceVal;
	consultCompConfigDetailForm.elements['consultCompConfigCommonDTO.compconfigId'].value = sequenceVal;
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
	if(ckCreate(currentPageId)) consultCompConfigDetailForm.strutsAction.value = "<%=ConsultCompConfigDetailAction.CONFIG_DETAIL_INPUT%>";
	else consultCompConfigDetailForm.strutsAction.value = "<%=ConsultCompConfigDetailAction.CONFIG_DETAIL_UPDATE%>";
	
	var actionUrl = contextPath + "/consultCompConfigDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(consultCompConfigDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
 	consultCompConfigDetailForm.elements['consultCompConfigCommonDTO.compconfigId'].value = consultCompConfigDetailForm.elements['consultCompConfigDetailDTO.compconfigId'].value;
 	consultCompConfigDetailForm.elements['consultCompConfigCommonDTO.compNo'].value = consultCompConfigDetailForm.elements['consultCompConfigDetailDTO.compNo'].value;
 	if (parent.findGridRow)	parent.findGridRow(consultCompConfigDetailForm.elements['consultCompConfigCommonDTO.compNo'].value, consultCompConfigDetailForm.elements['consultCompConfigCommonDTO.compconfigId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/consultCompConfigDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="consultCompConfigCommonDTO.compNo"/><!-- Key -->
	<html:hidden property="consultCompConfigDetailDTO.compNo"/><!-- Key -->
	<html:hidden property="consultCompConfigCommonDTO.compconfigId" />
	<html:hidden property="consultCompConfigDetailDTO.compconfigId" />
         <div class="article_box" id="detailBox">
             <div class="form_box">
                 <div class="field">
                    <label class="check"><bean:message key="LABEL.compDesc"/></label>
                    <div id="compNoDiv" class="input_box">
                        <html:text property="consultCompConfigDetailDTO.compDesc" tabindex="10"/>
                        <p class="open_spop">
                            <a><span>조회</span></a>
                        </p>
                    </div>
                 </div>
                 
             	 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.configName"/></label>
             	 	<div class="input_box">
             	 		<html:text property="consultCompConfigDetailDTO.compconfigName" tabindex="20" />
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.configValue"/></label>
             	 	<div class="input_box">
             	 		<html:text property="consultCompConfigDetailDTO.compconfigValue" tabindex="30"/>
             	 	</div>
             	 </div>
             	 
             	 <div class="field">
             	 	<label><bean:message key="LABEL.isSystem"/></label>
					<div class="input_box">
							<html:text property="consultCompConfigDetailDTO.isSystem" tabindex="40"
								onblur="valYn('consultCompConfigDetailDTO.isSystem', true);"/>
							<p class="open_spop">
								<a href="javascript:lovTable('consultCompConfigDetailDTO.isSystem', 'consultCompConfigDetailDTO.isSystem','YN');">
									<span>조회</span>
								</a>
							</p>
					</div>
				</div>
				
				<div class="field_long">
                    <label><bean:message key="LABEL.configDesc"/></label>
                    <div class="input_box">
                        <html:textarea property="consultCompConfigDetailDTO.compconfigDesc" styleClass="ta100" tabindex="50"/>
                    </div>
                 </div>
                 
                 
             </div> <!-- End of Form_box -->
         </div> <!-- End of Article_box -->
	</html:form>
</body>
</html:html>

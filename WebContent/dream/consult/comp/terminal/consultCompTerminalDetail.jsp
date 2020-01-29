<%--===========================================================================
접근터미널 - 상세
author  kim21017
version $Id: consultCompTerminalDetail.jsp,v 1.5 2014/07/02 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.consult.comp.terminal.action.ConsultCompTerminalDetailAction"%>
<html:html>
<head>
<title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">

var compAc;

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("consultCompTerminalDetailDTO.terminalNo","consultCompTerminalDetailDTO.description");
	//For Save
	setForUpdate();
	
	acSysDesc("consultCompTerminalDetailDTO.isUseDesc","consultCompTerminalDetailDTO.isUseId","IS_USE", true);
	acSysDesc("consultCompTerminalDetailDTO.serviceTypeDesc","consultCompTerminalDetailDTO.serviceTypeId","SERVICE_TYPE", true);
	
	compAc = new autoC({"consultCompTerminalDetailDTO.compNoDesc":"description"});
    compAc.setTable("TACOMP");
    compAc.setKeyName("consultCompTerminalDetailDTO.compNo");
    compAc.setAcResultMap({
        "consultCompTerminalDetailDTO.compNo":"comp_no",
    });
    compAc.init();
    
}
function goInput()
{
	sequenceNextVal('SQAACCESSMNL_ID');
	
}
function setSequenceVal(sequenceVal)
{
	consultCompTerminalDetailForm.elements['consultCompTerminalDetailDTO.accessMnlId'].value = sequenceVal;
	consultCompTerminalDetailForm.elements['consultCompTerminalCommonDTO.accessMnlId'].value = sequenceVal;
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
	if(ckCreate(currentPageId)) consultCompTerminalDetailForm.strutsAction.value = "<%=ConsultCompTerminalDetailAction.TERMINAL_DETAIL_INPUT%>";
	else consultCompTerminalDetailForm.strutsAction.value = "<%=ConsultCompTerminalDetailAction.TERMINAL_DETAIL_UPDATE%>";
	
	
	var actionUrl = contextPath + "/consultCompTerminalDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(consultCompTerminalDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(consultCompTerminalDetailForm.elements['consultCompTerminalDetailDTO.compNo'].value, consultCompTerminalDetailForm.elements['consultCompTerminalDetailDTO.accessMnlId'].value);
    
    consultCompTerminalDetailForm.elements['consultCompTerminalCommonDTO.compNo'].value = consultCompTerminalDetailForm.elements['consultCompTerminalDetailDTO.compNo'].value;
    consultCompTerminalDetailForm.elements['consultCompTerminalCommonDTO.accessMnlId'].value = consultCompTerminalDetailForm.elements['consultCompTerminalDetailDTO.accessMnlId'].value;
	
    getTopPage().afterSaveAll(currentPageId);
}
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/consultCompTerminalDetail" >
		<html:hidden property="strutsAction"/>
		<html:hidden property="consultCompTerminalCommonDTO.accessMnlId"/><!-- Key -->
        <html:hidden property="consultCompTerminalCommonDTO.compNo"/><!-- Key -->
		<html:hidden property="consultCompTerminalDetailDTO.serviceTypeId" />
		<html:hidden property="consultCompTerminalDetailDTO.isUseId" />
		<html:hidden property="consultCompTerminalDetailDTO.compNo" />
		<div class="article_box" id="detailBox">
			<div class="form_box">
			<div class="field">
                    <label class="check"><bean:message key="LABEL.compNo"/></label>
                        <div class="input_box">
                            <html:text property="consultCompTerminalDetailDTO.compNoDesc" tabindex="10"/>
                            <p class="open_spop">
                                <a><span>조회</span></a>
                            </p>
                        </div>
                </div>
				<div class="field">
					<label class="check"><bean:message key="LABEL.id"/></label>
					<div class="input_read">
						<html:text property="consultCompTerminalDetailDTO.accessMnlId" readonly="true" />
					</div>
				</div>
				
				<div class="field">
					<label><bean:message key="LABEL.serviceType"/></label>
					<div class="input_box">
	 					<html:text property="consultCompTerminalDetailDTO.serviceTypeDesc" tabindex="20"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<div class="field_long">
					<label class="check"><bean:message key="LABEL.terminalNo"/></label>
					<div class="input_box">
						<html:text property="consultCompTerminalDetailDTO.terminalNo" tabindex="30" />
					</div>
				</div>
				<div class="field_long">
					<label><bean:message key="LABEL.terminalDesc"/></label>
					<div class="input_box">
						<html:text property="consultCompTerminalDetailDTO.description" tabindex="40"  />
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.isAccess"/></label>
					<div class="input_box">
	 					<html:text property="consultCompTerminalDetailDTO.isUseDesc" tabindex="50"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<div class="field_long">
					<label><bean:message key="LABEL.detailDesc"/></label>
					<div class="input_box">
						<html:textarea property="consultCompTerminalDetailDTO.remark" styleClass="ta50" tabindex="60" />
					</div>
				</div>
			</div> <!-- End of Article_box -->
		</div>
	</html:form>
</body>
</html:html>

<%--===========================================================================
접근터미널 - 상세
author  kim21017
version $Id: consultCompTerminalExDetail.jsp,v 1.5 2014/07/02 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.consult.comp.terminal.action.ConsultCompTerminalExDetailAction"%>
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
	
	setTitle("consultCompTerminalExDetailDTO.terminalNo","consultCompTerminalExDetailDTO.description");
	//For Save
	setForUpdate();
	
	acSysDesc("consultCompTerminalExDetailDTO.isUseDesc","consultCompTerminalExDetailDTO.isUseId","IS_USE", true);
	acSysDesc("consultCompTerminalExDetailDTO.serviceTypeDesc","consultCompTerminalExDetailDTO.serviceTypeId","SERVICE_TYPE", true);
	
	compAc = new autoC({"consultCompTerminalExDetailDTO.compNoDesc":"description"});
    compAc.setTable("TACOMP");
    compAc.setKeyName("consultCompTerminalExDetailDTO.compNo");
    compAc.setAcResultMap({
        "consultCompTerminalExDetailDTO.compNo":"comp_no",
    });
    compAc.init();
    
}
function goInput()
{
	sequenceNextVal('SQAACCESSMNLEX_ID');
	
}
function setSequenceVal(sequenceVal)
{
	consultCompTerminalExDetailForm.elements['consultCompTerminalExDetailDTO.accessMnlExId'].value = sequenceVal;
	consultCompTerminalExDetailForm.elements['consultCompTerminalExCommonDTO.accessMnlExId'].value = sequenceVal;
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
	if(ckCreate(currentPageId)) consultCompTerminalExDetailForm.strutsAction.value = "<%=ConsultCompTerminalExDetailAction.TERMINAL_DETAIL_INPUT%>";
	else consultCompTerminalExDetailForm.strutsAction.value = "<%=ConsultCompTerminalExDetailAction.TERMINAL_DETAIL_UPDATE%>";
	
	
	var actionUrl = contextPath + "/consultCompTerminalExDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(consultCompTerminalExDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(consultCompTerminalExDetailForm.elements['consultCompTerminalExDetailDTO.compNo'].value, consultCompTerminalExDetailForm.elements['consultCompTerminalExDetailDTO.accessMnlExId'].value);
    
    consultCompTerminalExDetailForm.elements['consultCompTerminalExCommonDTO.compNo'].value = consultCompTerminalExDetailForm.elements['consultCompTerminalExDetailDTO.compNo'].value;
    consultCompTerminalExDetailForm.elements['consultCompTerminalExCommonDTO.accessMnlExId'].value = consultCompTerminalExDetailForm.elements['consultCompTerminalExDetailDTO.accessMnlExId'].value;
	
    getTopPage().afterSaveAll(currentPageId);
}
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/consultCompTerminalExDetail" >
		<html:hidden property="strutsAction"/>
		<html:hidden property="consultCompTerminalExCommonDTO.accessMnlExId"/><!-- Key -->
        <html:hidden property="consultCompTerminalExCommonDTO.compNo"/><!-- Key -->
		<html:hidden property="consultCompTerminalExDetailDTO.serviceTypeId" />
		<html:hidden property="consultCompTerminalExDetailDTO.isUseId" />
		<html:hidden property="consultCompTerminalExDetailDTO.compNo" />
		<div class="article_box" id="detailBox">
			<div class="form_box">
			<div class="field">
                    <label class="check"><bean:message key="LABEL.compNo"/></label>
                        <div class="input_box">
                            <html:text property="consultCompTerminalExDetailDTO.compNoDesc" tabindex="10"/>
                            <p class="open_spop">
                                <a><span>조회</span></a>
                            </p>
                        </div>
                </div>
				<div class="field">
					<label class="check"><bean:message key="LABEL.id"/></label>
					<div class="input_read">
						<html:text property="consultCompTerminalExDetailDTO.accessMnlExId" readonly="true" />
					</div>
				</div>
				
				<div class="field">
					<label><bean:message key="LABEL.serviceType"/></label>
					<div class="input_box">
	 					<html:text property="consultCompTerminalExDetailDTO.serviceTypeDesc" tabindex="20"/>
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
						<html:text property="consultCompTerminalExDetailDTO.terminalNo" tabindex="30" />
					</div>
				</div>
				<div class="field_long">
					<label><bean:message key="LABEL.terminalDesc"/></label>
					<div class="input_box">
						<html:text property="consultCompTerminalExDetailDTO.description" tabindex="40"  />
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.isAccess"/></label>
					<div class="input_box">
	 					<html:text property="consultCompTerminalExDetailDTO.isUseDesc" tabindex="50"/>
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
						<html:textarea property="consultCompTerminalExDetailDTO.remark" styleClass="ta50" tabindex="60" />
					</div>
				</div>
			</div> <!-- End of Article_box -->
		</div>
	</html:form>
</body>
</html:html>

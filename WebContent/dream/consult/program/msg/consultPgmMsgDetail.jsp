<%--===========================================================================
메시지 설정(메일,SMS) - 상세
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.consult.program.msg.action.ConsultPgmMsgDetailAction"%>
<html:html>
<head>
<title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">

function loadPage() 
{
	setTitle("consultPgmMsgDetailDTO.msgObjType", "consultPgmMsgDetailDTO.msgObjTypeDesc");
  	//For Save
    setForUpdate();
  	
  	//메시지 전송유형명
  	msgCategAc = new autoC({"consultPgmMsgDetailDTO.msgObjType":"cdsysd_no"});
    msgCategAc.setTable("TACDSYSD");
	msgCategAc.setAcConditionMap({
		"list_type":"MESSAGE_OBJECT_TYPE"
	});
    msgCategAc.setAcResultMap({
        "consultPgmMsgDetailDTO.msgObjTypeDesc":"description"
    });
    msgCategAc.init();	
	//사용여부
	acSysDesc("consultPgmMsgDetailDTO.isUseDesc","consultPgmMsgDetailDTO.isUse","IS_USE", true);
	//메일 사용여부
	acSysDesc("consultPgmMsgDetailDTO.isMailUseDesc","consultPgmMsgDetailDTO.isMailUse","IS_USE", true);
	//SMS 사용여부
	acSysDesc("consultPgmMsgDetailDTO.isSmsUseDesc","consultPgmMsgDetailDTO.isSmsUse","IS_USE", true);
	
	if(ckCreate(currentPageId)) goInput();
    else goUpdate();
}
function goInput()
{
	sequenceNextVal('SQAMESSAGECATEG_ID');
	
	consultPgmMsgDetailForm.elements['consultPgmMsgDetailDTO.isMailUse'].value = "Y";
    consultPgmMsgDetailForm.elements['consultPgmMsgDetailDTO.isMailUseDesc'].value = "Y";
    valSysDir('consultPgmMsgDetailDTO.isMailUse', 'consultPgmMsgDetailDTO.isMailUseDesc', 'IS_USE', true);
    
    consultPgmMsgDetailForm.elements['consultPgmMsgDetailDTO.isSmsUse'].value = "Y";
    consultPgmMsgDetailForm.elements['consultPgmMsgDetailDTO.isSmsUseDesc'].value = "Y";
    valSysDir('consultPgmMsgDetailDTO.isSmsUse', 'consultPgmMsgDetailDTO.isSmsUseDesc', 'IS_USE', true);
    
    consultPgmMsgDetailForm.elements['consultPgmMsgDetailDTO.isUse'].value = "Y";
    consultPgmMsgDetailForm.elements['consultPgmMsgDetailDTO.isUseDesc'].value = "Y";
    valSysDir('consultPgmMsgDetailDTO.isUse', 'consultPgmMsgDetailDTO.isUseDesc', 'IS_USE', true);
}
function setSequenceVal(sequenceVal)
{
	consultPgmMsgDetailForm.elements['consultPgmMsgDetailDTO.msgCategId'].value = sequenceVal;
	consultPgmMsgDetailForm.elements['consultPgmMsgCommonDTO.msgCategId'].value = sequenceVal;
}

function goUpdate()
{
	setDisable(document.getElementById("msgObjTypeDiv"));
}


function valMsgObjType()
{
	isValid = 0;
	
	var actionUrl = contextPath + "/consultPgmMsgDetail.do";
    var param =  "&strutsAction=" + '<%= ConsultPgmMsgDetailAction.DETAIL_CHECK %>'
              +  "&" + FormQueryString(consultPgmMsgDetailForm);
    XMLHttpPostVal(actionUrl, param, 'afterValMsgObjType');
}

var isValid = 0;
function afterValMsgObjType(ajaxXmlDoc)
{
	isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
	
	if(isValid != '0')
    {
		consultPgmMsgDetailForm.elements['consultPgmMsgDetailDTO.msgObjType'].value = ""; 
		consultPgmMsgDetailForm.elements['consultPgmMsgDetailDTO.msgObjTypeDesc'].value = ""; 
		consultPgmMsgDetailForm.elements['consultPgmMsgDetailDTO.msgObjTypeDesc'].focus(); 
		
		closeModal();
        alertMessage1("<bean:message key='MESSAGE.MSG0265' />");
    }
	else 
	{
		goSaveAfterValid();
	}
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

	if(ckCreate(currentPageId))
		valMsgObjType();
	else
    	goSaveAfterValid();
}

function goSaveAfterValid()
{
	//strutsAction 셋팅.
	if(ckCreate(currentPageId)) consultPgmMsgDetailForm.strutsAction.value = "<%=ConsultPgmMsgDetailAction.DETAIL_INPUT%>";
	else consultPgmMsgDetailForm.strutsAction.value = "<%=ConsultPgmMsgDetailAction.DETAIL_UPDATE%>";
	
	var actionUrl = contextPath + "/consultPgmMsgDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(consultPgmMsgDetailForm), 'afterSave');
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(consultPgmMsgDetailForm.elements['consultPgmMsgDetailDTO.msgCategId'].value);
    
    consultPgmMsgDetailForm.elements['consultPgmMsgCommonDTO.msgCategId'].value = consultPgmMsgDetailForm.elements['consultPgmMsgDetailDTO.msgCategId'].value;
	
    getTopPage().afterSaveAll(currentPageId);
}
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/consultPgmMsgDetail" >
		<html:hidden property="strutsAction"/>
		<html:hidden property="consultPgmMsgCommonDTO.msgCategId"/><!-- Key -->
		<html:hidden property="consultPgmMsgDetailDTO.msgCategId"/><!-- Key -->
	    <html:hidden property="consultPgmMsgDetailDTO.isMailUse"/>    
	    <html:hidden property="consultPgmMsgDetailDTO.isSmsUse"/>    
		<html:hidden property="consultPgmMsgDetailDTO.isUse" />
		<div class="article_box" id="detailBox">
			<div class="form_box">
                <!-- 메시지 전송유형 -->
                <div class="field" id = "msgObjTypeDiv">
                    <label class="check"><bean:message key="LABEL.msgObjType"/></label>
                    <div class="input_box">
                        <html:text property="consultPgmMsgDetailDTO.msgObjType" tabindex="10"/>
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                <!-- 메시지 전송유형명 -->
                <div class="field_long">
                    <label class="check"><bean:message key="LABEL.msgObjTypeDesc"/></label>
                    <div class="input_box">
                        <html:text property="consultPgmMsgDetailDTO.msgObjTypeDesc" tabindex="20"/>
                    </div>
                </div>
                <!-- 메일 사용여부 -->
                <div class="field">
                    <label><bean:message key="LABEL.isMailUse"/></label>
                    <div class="input_box">
                        <html:text property="consultPgmMsgDetailDTO.isMailUseDesc" tabindex="30"/>
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                <!-- SMS 사용여부 -->
                <div class="field">
                    <label><bean:message key="LABEL.isSmsUse"/></label>
                    <div class="input_box">
                        <html:text property="consultPgmMsgDetailDTO.isSmsUseDesc" tabindex="40"/>
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                <!-- 사용여부 -->
                <div class="field">
                    <label><bean:message key="LABEL.isUse"/></label>
                    <div class="input_box">
                        <html:text property="consultPgmMsgDetailDTO.isUseDesc" tabindex="50"/>
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                <!-- 비고 -->
                <div class="field_long">
                    <label><bean:message key="LABEL.remark"/></label>
                    <div class="input_box">
                        <html:textarea property="consultPgmMsgDetailDTO.remark" tabindex="60" styleClass="ta50"/>
                    </div>
                </div>
			</div> <!-- End of Article_box -->
		</div>
	</html:form>
</body>
</html:html>

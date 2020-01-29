<%--===========================================================================
상세 
author  youngjoo38
version $Id: persPrivInfoMsgEmpDetail.jsp,v 1.0 2015/12/04 07:26:18 youngjoo38 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.pers.priv.info.action.PersPrivInfoMsgEmpDetailAction"%>
<html>
<head>
<!--메뉴명 -->
<title><bean:message key="LABEL.menuName"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	setTitle("persPrivInfoMsgEmpDetailDTO.msgObjType", "persPrivInfoMsgEmpDetailDTO.msgObjTypeDesc");
	setForUpdate();
	
	//메시지전송유형
    msgCategAc = new autoC({"persPrivInfoMsgEmpDetailDTO.msgObjType":"msgObjType"});
    msgCategAc.setTable("TAMSGCOMPSET");
    msgCategAc.setAcConditionMap({
    	"is_use":"Y"
    });
    msgCategAc.setAcResultMap({
        "persPrivInfoMsgEmpDetailDTO.msgObjTypeDesc":"msgObjTypeDesc"
    });
    msgCategAc.init();	
    
	//메일 사용여부
	acSysDesc("persPrivInfoMsgEmpDetailDTO.isMailUseDesc","persPrivInfoMsgEmpDetailDTO.isMailUse","IS_USE");
	//SMS 사용여부
	acSysDesc("persPrivInfoMsgEmpDetailDTO.isSmsUseDesc","persPrivInfoMsgEmpDetailDTO.isSmsUse","IS_USE");
	
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAMSGEMP_ID');

    persPrivInfoMsgEmpDetailForm.elements['persPrivInfoMsgEmpDetailDTO.isMailUse'].value = "Y";
    persPrivInfoMsgEmpDetailForm.elements['persPrivInfoMsgEmpDetailDTO.isMailUseDesc'].value = "Y";
    valSysDir('persPrivInfoMsgEmpDetailDTO.isMailUse', 'persPrivInfoMsgEmpDetailDTO.isMailUseDesc', 'IS_USE', true);
    
    persPrivInfoMsgEmpDetailForm.elements['persPrivInfoMsgEmpDetailDTO.isSmsUse'].value = "Y";
    persPrivInfoMsgEmpDetailForm.elements['persPrivInfoMsgEmpDetailDTO.isSmsUseDesc'].value = "Y";
    valSysDir('persPrivInfoMsgEmpDetailDTO.isSmsUse', 'persPrivInfoMsgEmpDetailDTO.isSmsUseDesc', 'IS_USE', true);
    
    persPrivInfoMsgEmpDetailForm.elements['persPrivInfoMsgEmpDetailDTO.isUse'].value = "Y";
    persPrivInfoMsgEmpDetailForm.elements['persPrivInfoMsgEmpDetailDTO.isUseDesc'].value = "Y";
    valSysDir('persPrivInfoMsgEmpDetailDTO.isUse', 'persPrivInfoMsgEmpDetailDTO.isUseDesc', 'IS_USE', true);
    
    msgCategAc.openLov();
}

function setSequenceVal(sequenceVal)
{
	persPrivInfoMsgEmpDetailForm.elements['persPrivInfoMsgEmpDetailDTO.msgEmpSetId'].value = sequenceVal;
	persPrivInfoMsgEmpDetailForm.elements['maMyInfoCommonDTO.msgEmpSetId'].value = sequenceVal;
}

function goUpdate()
{
	setDisable(document.getElementsByName("msgObjTypeDiv"));
}

function valMsgObjType()
{
	isValid = 0;
	
	var actionUrl = contextPath + "/persPrivInfoMsgEmpDetail.do";
    var param =  "&strutsAction=" + '<%= PersPrivInfoMsgEmpDetailAction.DETAIL_CHECK %>'
              +  "&" + FormQueryString(persPrivInfoMsgEmpDetailForm);
    XMLHttpPostVal(actionUrl, param, 'afterValMsgObjType');
}

var isValid = 0;
function afterValMsgObjType(ajaxXmlDoc)
{
	isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
	
	if(isValid != '0')
    {
		persPrivInfoMsgEmpDetailForm.elements['persPrivInfoMsgEmpDetailDTO.msgObjType'].value = ""; 
		persPrivInfoMsgEmpDetailForm.elements['persPrivInfoMsgEmpDetailDTO.msgObjTypeDesc'].value = ""; 
		persPrivInfoMsgEmpDetailForm.elements['persPrivInfoMsgEmpDetailDTO.msgObjType'].focus(); 
		
		closeModal();
        alertMessage1("<bean:message key='MESSAGE.MSG0265' />");
    }
	else 
	{
		goSaveAfterValid();
	}
}

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
	if(ckCreate(currentPageId)) persPrivInfoMsgEmpDetailForm.strutsAction.value = '<%=PersPrivInfoMsgEmpDetailAction.DETAIL_INPUT%>';
	else persPrivInfoMsgEmpDetailForm.strutsAction.value = '<%= PersPrivInfoMsgEmpDetailAction.DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/persPrivInfoMsgEmpDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(persPrivInfoMsgEmpDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(persPrivInfoMsgEmpDetailForm.elements['persPrivInfoMsgEmpDetailDTO.msgEmpSetId'].value);
	
    setTitle("persPrivInfoMsgEmpDetailDTO.msgObjType", "persPrivInfoMsgEmpDetailDTO.msgObjTypeDesc");
    
    getTopPage().afterSaveAll(currentPageId);
    
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/persPrivInfoMsgEmpDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maMyInfoCommonDTO.msgEmpSetId"/>
<html:hidden property="persPrivInfoMsgEmpDetailDTO.msgEmpSetId"/>
<html:hidden property="persPrivInfoMsgEmpDetailDTO.isUse"/>
<html:hidden property="persPrivInfoMsgEmpDetailDTO.isUseDesc"/>
<html:hidden property="persPrivInfoMsgEmpDetailDTO.isSmsUse"/>
<html:hidden property="persPrivInfoMsgEmpDetailDTO.isMailUse"/>
    <!-- searchbox 박스 Line -->
	       <div class="article_box">
	           <div class="form_box">
                <!-- 메시지 전송유형 -->
                <div class="field" name = "msgObjTypeDiv">
                    <label class="check"><bean:message key="LABEL.msgObjType"/></label>
                    <div class="input_box">
                        <html:text property="persPrivInfoMsgEmpDetailDTO.msgObjType" tabindex="10"/>
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                <!-- 메시지 전송유형명 -->
                <div class="field_long" name = "msgObjTypeDiv">
                    <label class="check"><bean:message key="LABEL.msgObjTypeDesc"/></label>
                    <div class="input_box">
                        <html:text property="persPrivInfoMsgEmpDetailDTO.msgObjTypeDesc" tabindex="20"/>
                    </div>
                </div>
                <!-- 메일 사용여부 -->
                <div class="field">
                    <label><bean:message key="LABEL.isMailUse"/></label>
                    <div class="input_box">
                        <html:text property="persPrivInfoMsgEmpDetailDTO.isMailUseDesc" tabindex="30"/>
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                <!-- SMS 사용여부 -->
                <div class="field">
                    <label><bean:message key="LABEL.isSmsUse"/></label>
                    <div class="input_box">
                        <html:text property="persPrivInfoMsgEmpDetailDTO.isSmsUseDesc" tabindex="40"/>
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                <!-- 비고 -->
                <div class="field_long">
                    <label><bean:message key="LABEL.remark"/></label>
                    <div class="input_box">
                        <html:textarea property="persPrivInfoMsgEmpDetailDTO.remark" tabindex="60" styleClass="ta50"/>
                    </div>
                </div>
			</div>
		</div><!--article_box end-->
</html:form> 
</body>
</html>
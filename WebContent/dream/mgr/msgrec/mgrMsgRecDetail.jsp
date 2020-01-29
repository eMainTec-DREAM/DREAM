<%--===========================================================================
author  youngjoo38
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ page import="dream.mgr.msgrec.action.MgrMsgRecDetailAction" %>

<html:html>
<head>
<!-- 메시지 수신설정 디테일 -->
<title><bean:message key='LABEL.year' /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">

var msgCategAc;

function loadPage() 
{	
    setTitle("mgrMsgRecDetailDTO.msgObjType", "mgrMsgRecDetailDTO.msgObjTypeDesc");
    
  	//For Save
    setForUpdate();
  	
  	//메시지전송유형
    msgCategAc = new autoC({"mgrMsgRecDetailDTO.msgObjType":"msgObjType"});
    msgCategAc.setTable("TAMESSAGECATEG");
    msgCategAc.setAcConditionMap({
    	"is_use":"Y"
    });
    msgCategAc.setAcResultMap({
        "mgrMsgRecDetailDTO.msgObjTypeDesc":"msgObjTypeDesc"
    });
    msgCategAc.init();	
    
	//사용여부
	acSysDesc("mgrMsgRecDetailDTO.isUseDesc","mgrMsgRecDetailDTO.isUse","IS_USE");
	//메일 사용여부
	acSysDesc("mgrMsgRecDetailDTO.isMailUseDesc","mgrMsgRecDetailDTO.isMailUse","IS_USE");
	//SMS 사용여부
	acSysDesc("mgrMsgRecDetailDTO.isSmsUseDesc","mgrMsgRecDetailDTO.isSmsUse","IS_USE");
	
	if(ckCreate(currentPageId)) goInput();
    else goUpdate();
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQAMSGCOMPSET_ID');

    mgrMsgRecDetailForm.elements['mgrMsgRecDetailDTO.isMailUse'].value = "Y";
    mgrMsgRecDetailForm.elements['mgrMsgRecDetailDTO.isMailUseDesc'].value = "Y";
    valSysDir('mgrMsgRecDetailDTO.isMailUse', 'mgrMsgRecDetailDTO.isMailUseDesc', 'IS_USE', true);
    
    mgrMsgRecDetailForm.elements['mgrMsgRecDetailDTO.isSmsUse'].value = "Y";
    mgrMsgRecDetailForm.elements['mgrMsgRecDetailDTO.isSmsUseDesc'].value = "Y";
    valSysDir('mgrMsgRecDetailDTO.isSmsUse', 'mgrMsgRecDetailDTO.isSmsUseDesc', 'IS_USE', true);
    
    mgrMsgRecDetailForm.elements['mgrMsgRecDetailDTO.isUse'].value = "Y";
    mgrMsgRecDetailForm.elements['mgrMsgRecDetailDTO.isUseDesc'].value = "Y";
    valSysDir('mgrMsgRecDetailDTO.isUse', 'mgrMsgRecDetailDTO.isUseDesc', 'IS_USE', true);
    
    msgCategAc.openLov();
}

function setSequenceVal(sequenceVal)
{
    mgrMsgRecDetailForm.elements['mgrMsgRecDetailDTO.msgCompSetId'].value = sequenceVal;
    mgrMsgRecDetailForm.elements['mgrMsgRecCommonDTO.msgCompSetId'].value = sequenceVal;
}

function goUpdate()
{
	setDisable(document.getElementById("msgObjTypeDiv"));
}

function valMsgObjType()
{
	isValid = 0;
	
	var actionUrl = contextPath + "/mgrMsgRecDetail.do";
    var param =  "&strutsAction=" + '<%= MgrMsgRecDetailAction.DETAIL_CHECK %>'
              +  "&" + FormQueryString(mgrMsgRecDetailForm);
    XMLHttpPostVal(actionUrl, param, 'afterValMsgObjType');
}

var isValid = 0;
function afterValMsgObjType(ajaxXmlDoc)
{
	isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
	
	if(isValid != '0')
    {
		mgrMsgRecDetailForm.elements['mgrMsgRecDetailDTO.msgObjType'].value = ""; 
		mgrMsgRecDetailForm.elements['mgrMsgRecDetailDTO.msgObjTypeDesc'].value = ""; 
		mgrMsgRecDetailForm.elements['mgrMsgRecDetailDTO.msgObjType'].focus(); 
		
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
  	if(ckCreate(currentPageId)) mgrMsgRecDetailForm.strutsAction.value = "<%=MgrMsgRecDetailAction.DETAIL_INPUT%>";
	else mgrMsgRecDetailForm.strutsAction.value = "<%=MgrMsgRecDetailAction.DETAIL_UPDATE%>";
	
    var actionUrl = contextPath + "/mgrMsgRecDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(mgrMsgRecDetailForm),'afterSave');
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc) {
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc))
        return;
    //=====================================
    if (parent.findGridRow)
        parent.findGridRow(mgrMsgRecDetailForm.elements['mgrMsgRecDetailDTO.msgCompSetId'].value);

    mgrMsgRecDetailForm.elements['mgrMsgRecCommonDTO.msgCompSetId'].value = mgrMsgRecDetailForm.elements['mgrMsgRecDetailDTO.msgCompSetId'].value;
    getTopPage().afterSaveAll(currentPageId);
    setTitle("mgrMsgRecDetailDTO.msgObjType", "mgrMsgRecDetailDTO.msgObjTypeDesc");

}

</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

    <html:form action="/mgrMsgRecDetail">
    <html:hidden property="strutsAction" />
    <html:hidden property="mgrMsgRecCommonDTO.msgCompSetId"/><!-- Key -->
    <html:hidden property="mgrMsgRecDetailDTO.msgCompSetId"/><!-- Key -->
    <html:hidden property="mgrMsgRecDetailDTO.isMailUse"/>    
    <html:hidden property="mgrMsgRecDetailDTO.isSmsUse"/>    
    <html:hidden property="mgrMsgRecDetailDTO.isUse"/>    
    
        <div class="article_box">
            <div class="form_box">
                <!-- 메시지 전송유형 -->
                <div class="field" id = "msgObjTypeDiv">
                    <label class="check"><bean:message key="LABEL.msgObjType"/></label>
                    <div class="input_box">
                        <html:text property="mgrMsgRecDetailDTO.msgObjType" tabindex="10"/>
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                <!-- 메시지 전송유형명 -->
                <div class="field_long">
                    <label class="check"><bean:message key="LABEL.msgObjTypeDesc"/></label>
                    <div class="input_box">
                        <html:text property="mgrMsgRecDetailDTO.msgObjTypeDesc" tabindex="20"/>
                    </div>
                </div>
                <!-- 메일 사용여부 -->
                <div class="field">
                    <label><bean:message key="LABEL.isMailUse"/></label>
                    <div class="input_box">
                        <html:text property="mgrMsgRecDetailDTO.isMailUseDesc" tabindex="30"/>
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                <!-- SMS 사용여부 -->
                <div class="field">
                    <label><bean:message key="LABEL.isSmsUse"/></label>
                    <div class="input_box">
                        <html:text property="mgrMsgRecDetailDTO.isSmsUseDesc" tabindex="40"/>
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                <!-- 사용여부 -->
                <div class="field">
                    <label><bean:message key="LABEL.isUse"/></label>
                    <div class="input_box">
                        <html:text property="mgrMsgRecDetailDTO.isUseDesc" tabindex="50"/>
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                <!-- 비고 -->
                <div class="field_long">
                    <label><bean:message key="LABEL.remark"/></label>
                    <div class="input_box">
                        <html:textarea property="mgrMsgRecDetailDTO.remark" tabindex="60" styleClass="ta50"/>
                    </div>
                </div>
            </div>
            <!-- End of Form_box -->
        </div>
        <!-- End of Article_box -->
    </html:form>
</body>
</html:html>

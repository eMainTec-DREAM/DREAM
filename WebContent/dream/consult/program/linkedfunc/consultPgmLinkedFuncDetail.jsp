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

<%@ page import="dream.consult.program.linkedfunc.action.ConsultPgmLinkedFuncDetailAction" %>

<html:html>	
<head>
<!-- 연결메뉴 (Linked Function) 디테일 -->
<title><bean:message key='MENU.LINKEDFUNC' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //


function loadPage() 
{	
    if(ckCreate(currentPageId)) goInput();
    else goUpdate();
    
    setTitle("consultPgmLinkedFuncDetailDTO.linkedFuncNo", "consultPgmLinkedFuncDetailDTO.linkedFuncDesc");
    //For Save
    setForUpdate();
    
    //사용여부 자동완성
    acSysDesc("consultPgmLinkedFuncDetailDTO.isUseDesc","consultPgmLinkedFuncDetailDTO.isUseId","IS_USE", true);
}

/**
 * 입력
 */
function goInput()
{
    sequenceNextVal('SQALINKEDFUNC_ID');
    
    consultPgmLinkedFuncDetailForm.elements['consultPgmLinkedFuncDetailDTO.isUseId'].value = 'Y';
    consultPgmLinkedFuncDetailForm.elements['consultPgmLinkedFuncDetailDTO.isUseDesc'].value = 'Y';
    valSysDir('consultPgmLinkedFuncDetailDTO.isUseId', 'consultPgmLinkedFuncDetailDTO.isUseDesc', 'IS_USE', true);
}

function setSequenceVal(sequenceVal)
{
    consultPgmLinkedFuncDetailForm.elements['consultPgmLinkedFuncDetailDTO.linkedFuncId'].value = sequenceVal;
}
/**
 * 수정
 */
function goUpdate()
{

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
    if(ckCreate(currentPageId)) consultPgmLinkedFuncDetailForm.strutsAction.value = "<%=ConsultPgmLinkedFuncDetailAction.DETAIL_INPUT%>";
    else consultPgmLinkedFuncDetailForm.strutsAction.value = "<%=ConsultPgmLinkedFuncDetailAction.DETAIL_UPDATE%>";
    var actionUrl = contextPath + "/consultPgmLinkedFuncDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(consultPgmLinkedFuncDetailForm),'afterSave');

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
        parent.findGridRow(consultPgmLinkedFuncDetailForm.elements['consultPgmLinkedFuncDetailDTO.linkedFuncId'].value);

    consultPgmLinkedFuncDetailForm.elements['consultPgmLinkedFuncCommonDTO.linkedFuncId'].value = consultPgmLinkedFuncDetailForm.elements['consultPgmLinkedFuncDetailDTO.linkedFuncId'].value;
    getTopPage().afterSaveAll(currentPageId);
    setTitle("consultPgmLinkedFuncDetailDTO.linkedFuncNo", "consultPgmLinkedFuncDetailDTO.linkedFuncDesc");

}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

    <html:form action="/consultPgmLinkedFuncDetail">
        <html:hidden property="strutsAction" />
        <html:hidden property="consultPgmLinkedFuncCommonDTO.linkedFuncId" />          <!-- Key -->
        <html:hidden property="consultPgmLinkedFuncDetailDTO.linkedFuncId" />          <!-- Key -->
        <html:hidden property="consultPgmLinkedFuncDetailDTO.isUseId" />         <!-- 사용여부 ID -->
        
        <div class="article_box">
            <div class="form_box">
                <!-- No -->
                <div class="field">
                    <label><bean:message key="LABEL.docCountrNo"/></label>
                    <div class="input_box">
                        <html:text property="consultPgmLinkedFuncDetailDTO.linkedFuncNo" tabindex="10"/>
                    </div>
                </div>
                <!-- Method -->
                <div class="field">
                    <label class="check"><bean:message key="LABEL.method"/></label>
                    <div class="input_box">
                        <html:text property="consultPgmLinkedFuncDetailDTO.method" tabindex="20"/>
                    </div>
                </div>
                <!-- Linked Function 명 -->
                <div class="field">
                    <label class="check"><bean:message key="LABEL.linkedFuncDesc"/></label>
                    <div class="input_box">
                        <html:text property="consultPgmLinkedFuncDetailDTO.linkedFuncDesc" tabindex="30"/>
                    </div>
                </div>
                <!-- 사용여부 -->
                <div class="field">
                    <label><bean:message key="LABEL.isUse"/></label>
                    <div class="input_box">
                        <html:text property="consultPgmLinkedFuncDetailDTO.isUseDesc" tabindex="40" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 비고 -->
                <div class="field_long">
                    <label><bean:message key="LABEL.remark" /></label>
                    <div class="input_box">
                        <html:textarea property="consultPgmLinkedFuncDetailDTO.remark" styleClass="ta50" tabindex="50" />
                    </div>
                </div>
            </div>
            <!-- End of Form_box -->
        </div>
        <!-- End of Article_box -->
    </html:form>
</body>
</html:html>

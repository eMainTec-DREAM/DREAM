<%--===========================================================================
다국어 - 상세
author  kim21017
version $Id: maLangMngDetail.jsp,v 1.5 2014/07/02 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.consult.program.lang.action.MaLangMngDetailAction"%>
<html:html>
<head>
<!-- 다국어 -->
<title><bean:message key='TAB.maLangMngDetail' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">// 저장 시 수행되는 action
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("maLangMngDetailDTO.keyTypeDesc","maLangMngDetailDTO.keyNo");
	//For Save
	setForUpdate();
	
	acSysDesc("maLangMngDetailDTO.keyTypeDesc","maLangMngDetailDTO.keyType","KEY_TYPE", true);
	acSysDesc("maLangMngDetailDTO.langDesc","maLangMngDetailDTO.lang","LANG", true);
	
	acSysDesc("maLangMngDetailDTO.isCommJsUse","maLangMngDetailDTO.isCommJsUse","IS_USE",true);
    acSysDesc("maLangMngDetailDTO.useWeb","maLangMngDetailDTO.useWeb","IS_USE",true);; 
    acSysDesc("maLangMngDetailDTO.useAndroid","maLangMngDetailDTO.useAndroid","IS_USE",true);
}

/**
 * 입력
 */
function goInput()
{
 	sequenceNextVal('SQALANG_ID');
}


function setSequenceVal(sequenceVal)
{
	maLangMngDetailForm.elements['maLangMngCommonDTO.langId'].value = sequenceVal;
	maLangMngDetailForm.elements['maLangMngDetailDTO.langId'].value = sequenceVal;

}

/**
 * 중복 체크
 */
function valKeyCheck()
{
	isValid = 0;
	if(ckCreate(currentPageId))
	{
		var actionUrl = contextPath + "/maLangMngDetail.do";
		var param =  "&strutsAction=" + '<%= MaLangMngDetailAction.LANG_DETAIL_CHECK %>'
				  +  "&" + FormQueryString(maLangMngDetailForm);
		XMLHttpPostVal(actionUrl, param, 'setValKeyCheck');
	}else{
		runSave();
	}
}

/**
 * valKeyId()실행 후 호출
 */
 var isValid = 0;
function setValKeyCheck(ajaxXmlDoc)
{
	isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
	if(isValid != '0')
	{
		alertMessage1('<bean:message key="MESSAGE.MSG0009"/>');
		maLangMngDetailForm.elements['maLangMngDetailDTO.keyNo'].value = '';
		maLangMngDetailForm.elements['maLangMngDetailDTO.keyNo'].focus();
		closeModal();
		return;
	}
	runSave();
}

function runSave(){
	//strutsAction 셋팅.
	if(ckCreate(currentPageId)){
		maLangMngDetailForm.elements['maLangMngCommonDTO.langId'].value = maLangMngDetailForm.elements['maLangMngDetailDTO.langId'].value;
		maLangMngDetailForm.strutsAction.value="<%=MaLangMngDetailAction.LANG_DETAIL_INPUT%>";
	}else maLangMngDetailForm.strutsAction.value="<%=MaLangMngDetailAction.LANG_DETAIL_UPDATE%>";
	
	var actionUrl = contextPath + "/maLangMngDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maLangMngDetailForm), 'afterSave');
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
	
	//중복체크
	valKeyCheck();
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    maLangMngDetailForm.elements['maLangMngCommonDTO.langId'].value 	= maLangMngDetailForm.elements['maLangMngDetailDTO.langId'].value;
   //parent.goSearch();
   
    if (parent.findGridRow)	parent.findGridRow(maLangMngDetailForm.elements['maLangMngDetailDTO.langId'].value);
    
    getTopPage().afterSaveAll(currentPageId);
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maLangMngDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="maLangMngCommonDTO.langId" />
	<html:hidden property="maLangMngDetailDTO.langId" />
	<html:hidden property="maLangMngDetailDTO.lang" />
	<html:hidden property="maLangMngDetailDTO.keyType" />
         <div class="article_box" id="detailBox">
             <div class="form_box">
                 <div class="field">
                    <label class="check"><bean:message key="LABEL.language"/></label>
                    <div id = "keyTypeDiv" class="input_box">
                        <html:text property="maLangMngDetailDTO.langDesc" tabindex="10"/>
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                 </div>
             	 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.keyType"/></label>
					<div id = "keyTypeDiv" class="input_box">
						<html:text property="maLangMngDetailDTO.keyTypeDesc" tabindex="20"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
             	 </div>
             	 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.keyNo"/></label>
             	 	<div id = "keyNoDiv" class="input_box">
             	 		<html:text property="maLangMngDetailDTO.keyNo" tabindex="30" />
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.keyNameKo"/></label>
             	 	<div class="input_box">
             	 		<html:text property="maLangMngDetailDTO.keyName" tabindex="40" />
             	 	</div>
             	 </div>
             	 
             	 <div class="field">
                    <label><bean:message key="LABEL.isCommJsUse"/></label>
                    <div class="input_box">
                        <html:text property="maLangMngDetailDTO.isCommJsUse" tabindex="50"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
             	<div class="field">
                    <label><bean:message key="LABEL.useWeb"/></label>
                    <div class="input_box">
                        <html:text property="maLangMngDetailDTO.useWeb" tabindex="60"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
             	<div class="field">
                    <label><bean:message key="LABEL.useAndroid"/></label>
                    <div class="input_box">
                        <html:text property="maLangMngDetailDTO.useAndroid" tabindex="70"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <div class="field_long">
                    <label><bean:message key="LABEL.remark"/></label>
                    <div class="input_box">
                        <html:textarea property="maLangMngDetailDTO.remark" styleClass="ta50" tabindex="80" />
                    </div>
                 </div>
             	 
             </div> <!-- End of Form_box -->
         </div> <!-- End of Article_box -->
	</html:form>
</body>
</html:html>

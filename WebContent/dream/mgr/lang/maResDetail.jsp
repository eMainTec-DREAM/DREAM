<%--===========================================================================
언어 - 상세
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.lang.action.MaResDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<html:html>
<head>
<!-- 표시명 -->
<title><bean:message key='LABEL.keyName' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">// 저장 시 수행되는 action
<!-- //

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
    else 
    {
    	goUpdate();
    }
    
    //setTitle("maResDetailDTO.keyNo", "maResDetailDTO.keyName");
    setForUpdate();
}

/**
 * 수정
 */
function goUpdate()
{
}

function goInput() 
{
	sequenceNextVal('SQALANG_ID');
	setInitVal('maResDetailDTO.keyTypeDesc','USER');
	setInitVal('maResDetailDTO.lang', loginUser.langId);
	setInitVal('maResDetailDTO.langDesc',loginUser.langDesc);
	setInitVal('maResDetailDTO.isUseWeb','Y');
	setInitVal('maResDetailDTO.isUseAndroid','N');
	setInitVal('maResDetailDTO.isUseJs','N');
	
	valSysDir('maResDetailDTO.lang', 'maResDetailDTO.langDesc', 'LANG', true);
}


function setSequenceVal(sequenceVal)
{	
	maResDetailForm.elements['maResDetailDTO.keyNo'].value = sequenceVal;
	maResDetailForm.elements['maResDetailDTO.langId'].value = sequenceVal;
}

/**
 * 저장
 */ 
function goSave()
{
    if(ckCreate(currentPageId)) 
    {
    	maResDetailForm.elements['maResDetailDTO.creDate'].value = getNowDateTime(true);
    }
	maResDetailForm.elements['maResDetailDTO.updDate'].value = getNowDateTime(true);

	if(loginUser.langId == "ko") 
	{
		maResDetailForm.elements['maResDetailDTO.keyNameKo'].value = maResDetailForm.elements['maResDetailDTO.keyName'].value
	} else if (loginUser.langId == "en") 
	{
		maResDetailForm.elements['maResDetailDTO.keyNameEn'].value = maResDetailForm.elements['maResDetailDTO.keyName'].value
	}
    //================================
    // 필수 항목 체크한다.
    //================================
    if(checkValidation()) return;
    
    //strutsAction 셋팅.
    if(ckCreate(currentPageId)) maResDetailForm.strutsAction.value = "<%=MaResDetailAction.RES_DETAIL_INPUT%>";
    else maResDetailForm.strutsAction.value = '<%=MaResDetailAction.RES_DETAIL_UPDATE%>';
    
    var actionUrl = contextPath + "/maResDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(maResDetailForm), 'afterSave');
}


/**
 * Tab 이동시 호출
 */
function goTabPage(pageId)
{
	goCommonTabPage(maResDetailForm, '', pageId);
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
    //=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     maResDetailForm.elements['maResCommonDTO.langId'].value = maResDetailForm.elements['maResDetailDTO.langId'].value;

     if (parent.findGridRow)parent.findGridRow(maResDetailForm.elements['maResCommonDTO.langId'].value);
     
     //setTitle("maResDetailDTO.keyNo", "maResDetailDTO.keyName");
     goUpdate();
     
     getTopPage().afterSaveAll(currentPageId);
 }
 /**
  *  Audit trail
  */
  function goAudtrailLink()
  {
     var objectId = maResDetailForm.elements['maResDetailDTO.langId'].value;
     var fileName = currentPageId;

     if(typeof objectId=="undefined") return;

     goAudTrailList(objectId, fileName);
  }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maResDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maResCommonDTO.langId" />
	<html:hidden property="maResDetailDTO.langId" />
	<html:hidden property="maResDetailDTO.isUseWeb" />
	<html:hidden property="maResDetailDTO.isUseAndroid" />
	<html:hidden property="maResDetailDTO.isUseJs" />
	<html:hidden property="maResDetailDTO.creDate" />
	<html:hidden property="maResDetailDTO.updDate" />
	<html:hidden property="maResDetailDTO.lang" />
    <div class="article_box">
        <div class="form_box">
            <div class="field">
				<label><bean:message key="LABEL.separation"/></label>
				<div class="input_read">
					<html:text property="maResDetailDTO.keyTypeDesc" readonly = "true" tabindex="10" />
				</div>
			</div>
            <div class="field">
				<label><bean:message key="LABEL.language"/></label>
				<div class="input_read">
					<html:text property="maResDetailDTO.langDesc" readonly = "true" tabindex="20" />
				</div>
			</div>
			<!-- 등록ID -->
            <div class="field">
				<label><bean:message key="LABEL.key_no"/></label>
				<div class="input_read">
					<html:text property="maResDetailDTO.keyNo" readonly = "true" tabindex="30" />
				</div>
			</div>	
			<!-- Web 사용여부 -->
            <div class="field">
				<label><bean:message key="LABEL.use_web"/></label>
				<div class="input_read">
					<html:text property="maResDetailDTO.isUseWeb" readonly = "true" tabindex="40" />
				</div>
			</div>	
			<!-- 화면표시명 -->
            <div class="field_long">
				<label><bean:message key="LABEL.keyName"/></label>
				<div class="input_box">
					<html:text property="maResDetailDTO.keyName" tabindex="70" />
				</div>
			</div>
			<!-- Android 사용여부 -->
            <div class="field">
				<label><bean:message key="LABEL.use_android"/></label>
				<div class="input_read">
					<html:text property="maResDetailDTO.isUseAndroid" readonly = "true" tabindex="50" />
				</div>
			</div>		
			<!-- JS 사용여부 -->
            <div class="field">
				<label><bean:message key="LABEL.is_com_js_use"/></label>
				<div class="input_read">
					<html:text property="maResDetailDTO.isUseJs" readonly = "true" tabindex="60" />
				</div>
			</div>				
			<!-- 화면표시명(영어) -->			
            <div class="field_long">
				<label><bean:message key="LABEL.keyNameEn"/></label>
				<div class="input_read">
					<html:text property="maResDetailDTO.keyNameEn" readonly ="true" tabindex="80" />
				</div>
			</div>
			<!-- 화면표시명(한글) -->			
            <div class="field_long">
				<label><bean:message key="LABEL.keyNameKo"/></label>
				<div class="input_read">
					<html:text property="maResDetailDTO.keyNameKo" readonly ="true" tabindex="90" />
				</div>
			</div>
    </div> <!-- End of Form_box -->
</div> <!-- End of Article_box -->
</html:form>             
</body>
</html:html>

<%--===========================================================================
도움말 상세 
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="dream.app.onlinehelp.action.AppOnlinehelpDetailAction"%>
<html:html>
<head>
<!--도움말 -->
<title><bean:message key="LABEL.appOnlinehelpDetail"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 <style>
	 .field_long #editorObj { height: 200px; }
</style>
<script language="javascript">
<!--//
var myEditor;
function loadPage() 
{
	if(appOnlinehelpDetailForm.elements['appOnlinehelpDetailDTO.onlineHelpId'].value == "") 
		goInput();
	else
		goUpdate();
	
	setTitle("appOnlinehelpDetailDTO.fileName","appOnlinehelpDetailDTO.pageDesc");
// 	setReadOnly();
	setForUpdate();
	
	myEditor = new dhtmlXEditor({
		parent: "editorObj",
		toolbar: true, // force dhtmlxToolbar using
		iconsPath: '<c:url value="common/dhtmlxSuite/codebase/imgs/" />', // path for toolbar icons
		content: appOnlinehelpDetailForm.elements['appOnlinehelpDetailDTO.contents'].value
	});
	myEditor.attachEvent("onAccess", function(eventName, evObj){
	    if (eventName == "keyup") {
	        getTopPage().updateArray[currentPageId] = "dhtmlXEditor";
	    }
	});
	myEditor.attachEvent("onToolbarClick", function(name){
		getTopPage().updateArray[currentPageId] = "dhtmlXEditor";
	});
}

function goOpen(pageId)
{
	goTabPage(pageId);
}

function goTabPage(pageId)
{
	var form = document.appOnlinehelpDetailForm;
	
	if(pageId == "maDocLibList" || pageId == "appOnlinehelpDocLibList")
	{	
		form.elements['maDocLibCommonDTO.objectId'].value = form.elements['appOnlinehelpDetailDTO.onlineHelpId'].value;
		form.elements['maDocLibCommonDTO.objectType'].value = "ONLINEHELP";
		form.elements['maDocLibCommonDTO.description'].value = form.elements['appOnlinehelpDetailDTO.title'].value;
		goCommonTabPage(form, '<%=AppOnlinehelpDetailAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else
		goCommonTabPage(form, '' , pageId); 
}

function goInput()
{
	sequenceNextVal('SQAONLINEHELP_ID');
}

function goUpdate()
{
	
}

function setSequenceVal(sequenceVal)
{
	appOnlinehelpDetailForm.elements['appOnlinehelpDetailDTO.onlineHelpId'].value = sequenceVal;
	appOnlinehelpDetailForm.strutsAction.value = '<%=AppOnlinehelpDetailAction.ONLINE_HELP_DETAIL_INPUT%>';
	
}

function goSave()
{
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	appOnlinehelpDetailForm.elements['appOnlinehelpDetailDTO.contents'].value = myEditor.getContent();
	
	if(appOnlinehelpDetailForm.strutsAction.value == '<%=AppOnlinehelpDetailAction.ONLINE_HELP_DETAIL_INPUT%>')
	{ 
		var actionUrl = contextPath + "/appOnlinehelpDetail.do";
		XMLHttpPost(actionUrl, FormQueryString(appOnlinehelpDetailForm), 'afterSave');
	}
	else
	{
		appOnlinehelpDetailForm.strutsAction.value = '<%= AppOnlinehelpDetailAction.ONLINE_HELP_DETAIL_UPDATE %>';
	
		var actionUrl = contextPath + "/appOnlinehelpDetail.do";
		XMLHttpPost(actionUrl, FormQueryString(appOnlinehelpDetailForm), 'afterSave');
		
	}
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    
    getTopPage().afterSaveAll(currentPageId);
}

//-->
</script>

</head>
<body style="MARGIN: 0px; marginheight="0" marginwidth="0" > 
<html:form action="/appOnlinehelpDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="appOnlinehelpCommonDTO.pageId"/><!-- fileName -->

<html:hidden property="appOnlinehelpDetailDTO.onlineHelpId" />
<html:hidden property="appOnlinehelpDetailDTO.fileName" />
<html:hidden property="appOnlinehelpDetailDTO.pageDesc" />
<html:hidden property="appOnlinehelpDetailDTO.contents" />

<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
<html:hidden property="maDocLibCommonDTO.objectType" />
<html:hidden property="maDocLibCommonDTO.description" />
 	
	
		<div class="article_box">
			<div class="form_box">
			
				 <!-- 제목 -->
				 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.title"/></label>
             	 	<div class="input_box">
             	 		<html:text property="appOnlinehelpDetailDTO.title" tabindex="60" />
             	 	</div>
             	 </div>
             	 <!-- 내용 -->
             	 <div class="field_long">
             	 	<label><bean:message key="LABEL.contents"/></label>
             	 	<div class="input_box" id="editorObj">
             	 	</div>
             	 </div>
             	 
			</div>
		</div><!--article_box end-->
</html:form> 
</body>
</html:html>
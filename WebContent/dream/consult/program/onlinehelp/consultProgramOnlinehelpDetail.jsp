<%--===========================================================================
도움말 - 상세
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.consult.program.onlinehelp.action.ConsultProgramOnlinehelpDetailAction"%>
<html:html>
<head>
<!-- 도움말 -->
<title><bean:message key='LABEL.title' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<style>
	 .field_long #editorObj { height: 200px; }
</style>
<script language="javascript">// 저장 시 수행되는 action

var myEditor;
var fileNameAc;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goUpdate();
	}
	
	setTitle("consultProgramOnlinehelpDetailDTO.fileName","consultProgramOnlinehelpDetailDTO.pageDesc");
	
	setForUpdate();
	
	setDisable(document.getElementById("disableDiv"));
	
	myEditor = new dhtmlXEditor({
		parent: "editorObj",
		toolbar: true, // force dhtmlxToolbar using
		iconsPath: '<c:url value="common/dhtmlxSuite/codebase/imgs/" />', // path for toolbar icons
		content: consultProgramOnlinehelpDetailForm.elements['consultProgramOnlinehelpDetailDTO.contents'].value
	});
	myEditor.attachEvent("onAccess", function(eventName, evObj){
	    if (eventName == "keyup") {
	        getTopPage().updateArray[currentPageId] = "dhtmlXEditor";
	    }
	});
	myEditor.attachEvent("onToolbarClick", function(name){
		getTopPage().updateArray[currentPageId] = "dhtmlXEditor";
	});
	
	fileNameAc = new autoC({"consultProgramOnlinehelpDetailDTO.pageDesc":"description"});
	fileNameAc.setTable("TAPAGE");
	fileNameAc.setAcResultMap({
		"consultProgramOnlinehelpDetailDTO.pageId":"page_id"
    	,"consultProgramOnlinehelpDetailDTO.fileName":"file_name"
    });
	fileNameAc.setKeyName("consultProgramOnlinehelpDetailDTO.pageId");
	fileNameAc.init();  
}

function goInput()
{
	sequenceNextVal('SQAONLINEHELP_ID');
}

function goUpdate()
{
	setDisable(document.getElementById("fileNameDiv"));
	setDisable(document.getElementById("pageNameDiv"));
}

function setSequenceVal(sequenceVal)
{
	consultProgramOnlinehelpDetailForm.elements['consultProgramOnlinehelpDetailDTO.onlineHelpId'].value = sequenceVal;
	consultProgramOnlinehelpDetailForm.elements['consultProgramOnlinehelpCommonDTO.onlineHelpId'].value = sequenceVal;
}

function goOpen(pageId)
{
	goTabPage(pageId);
}

function goTabPage(pageId)
{
	var form = document.consultProgramOnlinehelpDetailForm;
	
	if(pageId == "maDocLibList" || pageId == "consultProgramOnlinehelpDocLibList")
	{	
		form.elements['maDocLibCommonDTO.objectId'].value = form.elements['consultProgramOnlinehelpCommonDTO.onlineHelpId'].value;
		form.elements['maDocLibCommonDTO.objectType'].value = "ONLINEHELP";
		form.elements['maDocLibCommonDTO.description'].value = form.elements['consultProgramOnlinehelpDetailDTO.title'].value;
		form.elements['maDocLibCommonDTO.compNo'].value = form.elements['consultProgramOnlinehelpDetailDTO.compNo'].value;
		goCommonTabPage(form, '<%=ConsultProgramOnlinehelpDetailAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else
		goCommonTabPage(form, '' , pageId); 
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
	if(ckCreate(currentPageId)) consultProgramOnlinehelpDetailForm.strutsAction.value = '<%=ConsultProgramOnlinehelpDetailAction.HELP_DETAIL_INPUT%>';
	else consultProgramOnlinehelpDetailForm.strutsAction.value = '<%=ConsultProgramOnlinehelpDetailAction.HELP_DETAIL_UPDATE%>';
	
	consultProgramOnlinehelpDetailForm.elements['consultProgramOnlinehelpDetailDTO.contents'].value = myEditor.getContent();
	consultProgramOnlinehelpDetailForm.elements['consultProgramOnlinehelpDetailDTO.updBy'].value = loginUser.userName;
	
	var actionUrl = contextPath + "/consultProgramOnlinehelpDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(consultProgramOnlinehelpDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	consultProgramOnlinehelpDetailForm.elements['consultProgramOnlinehelpDetailDTO.onlineHelpId'].value = consultProgramOnlinehelpDetailForm.elements['consultProgramOnlinehelpCommonDTO.onlineHelpId'].value;
	if (parent.findGridRow)	parent.findGridRow(consultProgramOnlinehelpDetailForm.elements['consultProgramOnlinehelpDetailDTO.onlineHelpId'].value);
	
	getTopPage().afterSaveAll(currentPageId);
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/consultProgramOnlinehelpDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="consultProgramOnlinehelpCommonDTO.onlineHelpId"/><!-- Key -->
	<html:hidden property="consultProgramOnlinehelpCommonDTO.compNo"/>
	<html:hidden property="consultProgramOnlinehelpDetailDTO.onlineHelpId" />
	<html:hidden property="consultProgramOnlinehelpDetailDTO.compNo"/>
	<html:hidden property="consultProgramOnlinehelpDetailDTO.pageId"/>
	<html:hidden property="consultProgramOnlinehelpDetailDTO.updDate"/>
	<html:hidden property="consultProgramOnlinehelpDetailDTO.updBy"/>
	<html:hidden property="consultProgramOnlinehelpDetailDTO.contents"/>
	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
 	<html:hidden property="maDocLibCommonDTO.objectType" />
 	<html:hidden property="maDocLibCommonDTO.description" />
 	<html:hidden property="maDocLibCommonDTO.compNo" />
 
         <div class="article_box" id="detailBox">
             <div class="form_box">
             	 <!-- 화면ID -->
             	 <div class="field" id="fileNameDiv">
             	 	<label><bean:message key="LABEL.fileName"/></label>
             	 	<div class="input_box">
             	 		<html:text property="consultProgramOnlinehelpDetailDTO.fileName" tabindex="1" />
             	 	</div>
             	 </div>
             	 <!-- 화면명 -->
             	 <div class="field" id="pageNameDiv">
             	 	<label class="check"><bean:message key="LABEL.pageName"/></label>
             	 	<div class="input_box">
             	 		<html:text property="consultProgramOnlinehelpDetailDTO.pageDesc" tabindex="10" />
             	 		<p class="open_spop"><a><span>조회</span></a></p>
             	 	</div>
             	 </div>
				<!-- 작성자 -->
				<div class="field" id="disableDiv">
					<label><bean:message key="LABEL.updBy"/></label>
					<div class="input_box">
						<html:text property="consultProgramOnlinehelpDetailDTO.updDateBy" tabindex="50" />
					</div>
				</div>
				<!-- 제목 -->
				<div class="field">
             	 	<label class="check"><bean:message key="LABEL.title"/></label>
             	 	<div class="input_box">
             	 		<html:text property="consultProgramOnlinehelpDetailDTO.title" tabindex="60" />
             	 	</div>
             	 </div>
             	 
             	 <!-- 내용 -->
             	 <div class="field_long">
             	 	<label><bean:message key="LABEL.contents"/></label>
             	 	<div class="input_box" id="editorObj">
             	 	</div>
             	 </div>
			
			
             </div> <!-- End of Form_box -->
         </div> <!-- End of Article_box -->
         
	</html:form>
</body>
</html:html>
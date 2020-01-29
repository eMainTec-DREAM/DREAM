<%--===========================================================================
사용자매뉴얼 - 상세
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
<%@ page import="dream.doc.manual.action.DocManualDetailAction"%>
<html:html>
<head>
<!-- 사용자매뉴얼 -->
<title><bean:message key='LABEL.title' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<style>
	 .field_long #editorObj { height: 200px; }
</style>
<script language="javascript">// 저장 시 수행되는 action

var myEditor;
var menuAc, fileNameAc;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goUpdate();
	}
	
	setTitle("docManualDetailDTO.fileName","docManualDetailDTO.pageDesc");
	
	setForUpdate();
	
	myEditor = new dhtmlXEditor({
		parent: "editorObj",
		toolbar: true, // force dhtmlxToolbar using
		iconsPath: '<c:url value="common/dhtmlxSuite/codebase/imgs/" />', // path for toolbar icons
		content: docManualDetailForm.elements['docManualDetailDTO.contents'].value
	});
	myEditor.attachEvent("onAccess", function(eventName, evObj){
	    if (eventName == "keyup") {
	        getTopPage().updateArray[currentPageId] = "dhtmlXEditor";
	    }
	});
	myEditor.attachEvent("onToolbarClick", function(name){
		getTopPage().updateArray[currentPageId] = "dhtmlXEditor";
	});
	
	//메뉴 (pop up)
    menuAc = new autoC({"docManualDetailDTO.menuDesc":"description"});
    menuAc.setTable("TACMENU");
    menuAc.setAcConditionMap({
		"is_use":"Y"
	});
    menuAc.setAcResultMap({
        "docManualDetailDTO.menuId":"MENU_ID"
    });
    menuAc.setKeyName("docManualDetailDTO.menuId");
    menuAc.init();
	
	fileNameAc = new autoC({"docManualDetailDTO.pageDesc":"description"});
	fileNameAc.setTable("TAPAGE");
	fileNameAc.setAcConditionMap({
		"is_use":"Y"
	});
	fileNameAc.setAcDConditionMap({
		"menu_id":"docManualDetailDTO.menuId"
	});
	fileNameAc.setAcResultMap({
		"docManualDetailDTO.pageId":"page_id"
    	,"docManualDetailDTO.fileName":"file_name"
    });
	fileNameAc.setKeyName("docManualDetailDTO.pageId");
	fileNameAc.init();  
}

function goInput()
{
	sequenceNextVal('SQAONLINEHELP_ID');
	
	docManualDetailForm.elements['docManualDetailDTO.updBy'].value = loginUser.empName;
	docManualDetailForm.elements['docManualDetailDTO.updDept'].value = loginUser.deptDesc;
}

function goUpdate()
{
	setDisable(document.getElementById("menuNameDiv"));
	setDisable(document.getElementById("pageNameDiv"));
}

function setSequenceVal(sequenceVal)
{
	docManualDetailForm.elements['docManualDetailDTO.onlineHelpId'].value = sequenceVal;
	docManualDetailForm.elements['docManualCommonDTO.onlineHelpId'].value = sequenceVal;
	docManualDetailForm.elements['docManualDetailDTO.onlineHelpNo'].value = sequenceVal;
}

function goOpen(pageId)
{
	goTabPage(pageId);
}

function goTabPage(pageId)
{
	var form = document.docManualDetailForm;
	
	if(pageId == "maDocLibList" || pageId == "docManualDocLibList")
	{	
		form.elements['maDocLibCommonDTO.objectId'].value = form.elements['docManualCommonDTO.onlineHelpId'].value;
		form.elements['maDocLibCommonDTO.objectType'].value = "ONLINEHELP";
		form.elements['maDocLibCommonDTO.description'].value = form.elements['docManualDetailDTO.title'].value;
		goCommonTabPage(form, '<%=DocManualDetailAction.BASE_QUICK_SEARCH%>' , pageId);
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
	if(ckCreate(currentPageId)) docManualDetailForm.strutsAction.value = '<%=DocManualDetailAction.HELP_DETAIL_INPUT%>';
	else docManualDetailForm.strutsAction.value = '<%=DocManualDetailAction.HELP_DETAIL_UPDATE%>';
	
	docManualDetailForm.elements['docManualDetailDTO.contents'].value = myEditor.getContent();
	
	var actionUrl = contextPath + "/docManualDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(docManualDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	docManualDetailForm.elements['docManualDetailDTO.onlineHelpId'].value = docManualDetailForm.elements['docManualCommonDTO.onlineHelpId'].value;
	if (parent.findGridRow)	parent.findGridRow(docManualDetailForm.elements['docManualDetailDTO.onlineHelpId'].value);
	
	getTopPage().afterSaveAll(currentPageId);
 }
 /**
  *  Audit trail
  */
  function goAudtrailLink()
  {
     var objectId = docManualDetailForm.elements['docManualDetailDTO.onlineHelpId'].value;
     var fileName = currentPageId;

     if(typeof objectId=="undefined") return;

     goAudTrailList(objectId, fileName);
  }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/docManualDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="docManualCommonDTO.onlineHelpId"/><!-- Key -->
	<html:hidden property="docManualDetailDTO.onlineHelpId" />
	<html:hidden property="docManualDetailDTO.pageId"/>
	<html:hidden property="docManualDetailDTO.menuId"/>
	<html:hidden property="docManualDetailDTO.fileName"/>
	<html:hidden property="docManualDetailDTO.contents"/>
	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
 	<html:hidden property="maDocLibCommonDTO.objectType" />
 	<html:hidden property="maDocLibCommonDTO.description" />
 	<html:hidden property="maDocLibCommonDTO.compNo" />
 
         <div class="article_box" id="detailBox">
             <div class="form_box">
				<!-- 제목 -->
				<div class="field_long">
             	 	<label class="check"><bean:message key="LABEL.title"/></label>
             	 	<div class="input_box">
             	 		<html:text property="docManualDetailDTO.title" tabindex="10" />
             	 	</div>
             	 </div>
             	 <!-- 매뉴명 -->
           	     <div class="field" id="menuNameDiv">
	                <label><bean:message key="LABEL.menuName"/></label>
                	<div class="input_box">
          	    		<html:text property="docManualDetailDTO.menuDesc" tabindex="20"/>
          	    		<p class="open_spop"><a></a></p>
               	    </div>
              	 </div>
             	 <!-- 화면명 -->
             	 <div class="field" id="pageNameDiv">
             	 	<label><bean:message key="LABEL.pageName"/></label>
             	 	<div class="input_box">
             	 		<html:text property="docManualDetailDTO.pageDesc" tabindex="30" />
             	 		<p class="open_spop"><a><span>조회</span></a></p>
             	 	</div>
             	 </div>
				 <!-- 등록일자 -->
				 <div class="field">
					<label><bean:message key="LABEL.regiDate"/></label>
					<div class="input_read">
						<html:text property="docManualDetailDTO.updDate" tabindex="40" readonly="true"/>
					</div>
				 </div>
             	 <!-- 등록부서 -->
				 <div class="field">
					<label><bean:message key="LABEL.deptId"/></label>
					<div class="input_read">
						<html:text property="docManualDetailDTO.updDept" tabindex="50" readonly="true"/>
					</div>
				 </div>
				 <!-- 등록자 -->
				 <div class="field">
					<label><bean:message key="LABEL.regId"/></label>
					<div class="input_read">
						<html:text property="docManualDetailDTO.updBy" tabindex="60" readonly="true"/>
					</div>
				 </div>
				 <!-- 등록번호 -->
				 <div class="field">
					<label><bean:message key="LABEL.regNo"/></label>
					<div class="input_read">
						<html:text property="docManualDetailDTO.onlineHelpNo" tabindex="70" readonly="true"/>
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
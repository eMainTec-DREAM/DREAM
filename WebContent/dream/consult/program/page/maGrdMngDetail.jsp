<%--===========================================================================
상세
author  jung7126
version $Id: maGrdMngDetail.jsp,v 1.0 2015/12/04 04:13:54 jung7126 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.consult.program.page.action.MaGrdMngDetailAction"%>
<html:html>
<head>
<!-- 화면컬럼정의 -->
<title><bean:message key='TAB.maGrdMngDetail' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">// 저장 시 수행되는 action
var saveStrutsAction;
var pageDescAc;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goTabPage("maGrdMngColList");
	}
	
	setTitle("maGrdMngDetailDTO.gridObjId","maGrdMngDetailDTO.pageDesc");
	
	setForUpdate();
	
	pageDescAc = new autoC({"maGrdMngDetailDTO.pageDesc":"description"});
    pageDescAc.setTable("TAPAGE");
    pageDescAc.setAcResultMap({
    	"maGrdMngDetailDTO.pageId":"page_id"
    });
    pageDescAc.setKeyName("maGrdMngDetailDTO.pageId");
    pageDescAc.init();  
}

function goInput()
{
	sequenceNextVal('SQAPGGRID_ID');
}

function setSequenceVal(sequenceVal)
{
	maGrdMngDetailForm.elements['maGrdMngDetailDTO.pgGridId'].value = sequenceVal;
	maGrdMngDetailForm.elements['maGrdMngCommonDTO.pgGridId'].value = sequenceVal;
	
	goTabPage("maGrdMngColList");
}

function goOpen(pageId)
{
	goTabPage(pageId);
}

function goTabPage(pageId)
{
	var form = document.maGrdMngDetailForm;

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
	if(ckCreate(currentPageId)) maGrdMngDetailForm.strutsAction.value = '<%=MaGrdMngDetailAction.GRD_DETAIL_INPUT%>';
	else maGrdMngDetailForm.strutsAction.value = '<%=MaGrdMngDetailAction.GRD_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/maGrdMngDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maGrdMngDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	if (parent.findGridRow)	parent.findGridRow(maGrdMngDetailForm.elements['maGrdMngDetailDTO.pgGridId'].value);
	
	getTopPage().afterSaveAll(currentPageId);
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maGrdMngDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="maGrdMngCommonDTO.pgGridId" />
	<html:hidden property="maGrdMngDetailDTO.pgGridId" />
 	<html:hidden property="maGrdMngDetailDTO.pageId" />
         <div class="article_box" id="detailBox">
             <div class="form_box">
             	 <div class="field">
             	 	<label class="check"><bean:message key="LABEL.pageDesc"/></label>
             	 	<div class="input_box">
             	 		<html:text property="maGrdMngDetailDTO.pageDesc" tabindex="5" />
						<p class="open_spop"><a><span>조회</span></a></p>
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.gridObjId"/></label>
             	 	<div class="input_box">
             	 		<html:text property="maGrdMngDetailDTO.gridObjId" tabindex="10" />
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.gridHeight"/></label>
             	 	<div class="input_box">
             	 		<html:text property="maGrdMngDetailDTO.height" tabindex="15"  styleClass="num"/>
             	 	</div>
             	 </div>
             	 <div class="field_long">
             	 	<label><bean:message key="LABEL.gridDesc"/></label>
             	 	<div class="input_box">
             	 		<html:text property="maGrdMngDetailDTO.description" tabindex="20" />
             	 	</div>
             	 </div>
             </div> <!-- End of Form_box -->
         </div> <!-- End of Article_box -->
         
	</html:form>
</body>
</html:html>
<%--===========================================================================
칼럼 상세 
author  jung7126
version $Id: maGrdUsrDetail.jsp,v 1.0 2015/12/04 07:26:18 jung7126 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.pers.priv.pgm.action.MaGrdUsrDetailAction"%>
<html>
<head>
<!--칼럼 -->
<title><bean:message key="LABEL.maGrdUsrDetail"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	setTitle("maGrdUsrDetailDTO.pageDesc");
	
	$('.stitle_box').append(" [ "+getIframeContent().currentPageId+" ]");
	
	setReadOnly();
	setForUpdate();
	goInput();
	//Get the javascript object name from body using grid id
	
	//set Height 
	
	
	//var test = "myGrid";
	//"getIframeContent()."+test+".clearAll();"
	//$.globalEval( "getIframeContent()."+test+".clearAll();" )


	//$('#detailTitle').html($('#detailTitle').html().replace(":",""));
}

function goTabPage(pageId)
{
	goCommonTabPage(maGrdUsrDetailForm, '', pageId);
}

function goInput()
{
	if(maGrdUsrDetailForm.elements['maGrdUsrDetailDTO.usrPgGridId'].value == "") 
		sequenceNextVal('SQAUSRPGGRID_ID');
	else
		goTabPage('maGrdUsrColList');
}

function setSequenceVal(sequenceVal)
{
	maGrdUsrDetailForm.elements['maGrdUsrDetailDTO.usrPgGridId'].value = sequenceVal;
	maGrdUsrDetailForm.strutsAction.value = '<%=MaGrdUsrDetailAction.GRD_USR_DETAIL_INPUT%>';
	
	goTabPage('maGrdUsrColList');
}

function goSaveA()
{
	var actionUrl = contextPath + "/maGrdUsrDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maGrdUsrDetailForm), 'afterSave');
}

function goSave()
{
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	if(maGrdUsrDetailForm.strutsAction.value == '<%=MaGrdUsrDetailAction.GRD_USR_DETAIL_INPUT%>')
	{ 
		goSaveA();
	}
	else
	{
		maGrdUsrDetailForm.strutsAction.value = '<%= MaGrdUsrDetailAction.GRD_USR_DETAIL_UPDATE %>';
	
		var actionUrl = contextPath + "/maGrdUsrDetail.do";
		XMLHttpPost(actionUrl, FormQueryString(maGrdUsrDetailForm), 'afterSave');
		
	}
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    //parent.goSearch();
    
    getTopPage().afterSaveAll(currentPageId);
    
    setGridHeight();
}

function setGridHeight()
{
	var gridObjId = $('[name="maGrdUsrCommonDTO.gridObjId"]').val();
	var gridHeight = $('[name="maGrdUsrDetailDTO.height"]').val();
	var parentObj = getIframeContent();

	if(parentObj.initGrid && gridHeight != "") 
	{
		var entire = parentObj.initGrid.toString();
		var body = entire.substring(entire.indexOf("{") + 1, entire.lastIndexOf("}"));
		var gridObjName = $.trim((body.split("dhtmlXGridObject('"+gridObjId)[0]).split("=")[0]);
		var heightInt = Math.round(parseInt(gridHeight)) * 30 + 30; //한줄에 30px. + footer 20px
        
		parentObj.$('#'+gridObjId).css("height",heightInt);
		$.globalEval( "getIframeContent()."+gridObjName+".setSizes();");

	};
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maGrdUsrDetail.do">
<html:hidden property="strutsAction" value="1"/>
<html:hidden property="maGrdUsrCommonDTO.pageId"/>
<html:hidden property="maGrdUsrCommonDTO.gridObjId"/>
<html:hidden property="maGrdUsrDetailDTO.pgGridId" />
<html:hidden property="maGrdUsrDetailDTO.usrPgGridId" /> <!-- User Page Grid ID -->
<html:hidden property="maGrdUsrDetailDTO.gridObjId" />
	<div class="section_wrap" id="filterSection">
		<div class="sheader_box">
			<div class="stitle_box"></div>
			<div class="function_box list">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>	
				<div class="fb_group2">
				</div>

				
			</div>
		</div><!--sheader_box end-->
		<div class="article_box">
			<div class="form_box">
				<div class="field">
             	 	<label><bean:message key="LABEL.pageDesc"/></label>
             	 	<div class="input_read">
             	 		<html:text property="maGrdUsrDetailDTO.pageDesc" tabindex="5" readonly="true"/>
             	 	</div>
             	 </div>
             	 <div class="field">
             	 	<label><bean:message key="LABEL.gridHeight"/></label>
             	 	<div class="input_box">
             	 		<html:text property="maGrdUsrDetailDTO.height" tabindex="15" styleClass="num"/>
             	 	</div>
             	 </div>
             	 <div class="field_long">
             	 	<label><bean:message key="LABEL.gridDesc"/></label>
             	 	<div class="input_read">
             	 		<html:text property="maGrdUsrDetailDTO.description" tabindex="20" readonly="true"/>
             	 	</div>
             	 </div>
			</div>
		</div><!--article_box end-->
	</div>
</html:form> 
</body>
</html>
<%--===========================================================================
다국어사용된사용된탭
author  euna0207
version $Id: mgrLangSyscodeList.jsp,v 1.1 2019/10/21 12:42:27 euna0207 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.lang.action.MgrLangSyscodeAction" %> 
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 다국어사용된사용된탭 -->
<title><bean:message key='TAB.mgrLangSyscodeList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	initGrid();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	return sortColumn("mgrLangSyscodeList", this, mgrLangSyscodeForm, "langId", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
	myGrid.init();
	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
    findGridList('Search');   
}

function findGridList(sheetAction)
{
	
	if (mgrLangSyscodeForm.elements['maResCommonDTO.langId'].value == '') return;
	
	var form = document.mgrLangSyscodeForm;	
	form.strutsAction.value = '<%=MgrLangSyscodeAction.LIST_FIND %>';
	
	var url = contextPath + "/mgrLangSyscodeList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mgrLangSyscodeForm), "langId", "Y");
}

 /**
  * 엑셀 다운.
  */
function goExcel()
{
	excelServerAction("mgrLangSyscodeList", mgrLangSyscodeForm );
} 
 
 
 /** 
  * 수정된 그리드 1건을 다시 조회한다.
  */
 function findGridRow(_langId)
 {
 	mgrLangSyscodeForm.elements['maResCommonDTO.langId'].value = _langId;
 	findGridList('ReloadRow');
 	mgrLangSyscodeForm.elements['maResCommonDTO.langId'].value = "";
 }

//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/mgrLangSyscodeList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maResCommonDTO.langId"/><!-- Key -->
    <!-- searchbox 박스 Line -->
    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>
</html:form> 
</body>
</html>
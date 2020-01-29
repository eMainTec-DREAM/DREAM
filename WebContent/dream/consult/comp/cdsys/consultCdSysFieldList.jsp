<%--===========================================================================
시스템코드 detail - 관련Field 목록
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="common.tree.action.TreeAction" %>
<%@ page import="dream.consult.comp.cdsys.action.ConsultCdSysFieldListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 관련Field -->
<title><bean:message key='PAGE.consultCdSysFieldList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="adminPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	//시스템코드 분류 Grid
	initGrid();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	findGridList('Search');
}

/**
 * gird find
 */
function findGridList(sheetAction)
{
	//listType 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (consultCdSysFieldListForm.elements['maCdSysCommonDTO.cdSysMId'].value == '') return;
	
	var form = document.consultCdSysFieldListForm;	
	form.strutsAction.value = '<%=ConsultCdSysFieldListAction.LIST_FIND%>';

	var url = contextPath + "/consultCdSysFieldList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(consultCdSysFieldListForm), "CDSYSDID");

}
 

/**
 * Excel Export
 */
function goExcel()
{
    excelAction(myGrid);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/consultCdSysFieldList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maCdSysCommonDTO.cdSysMId"/><!-- Key -->
<html:hidden property="consultCdSysFieldListDTO.cdSysDId"/><!-- Detail Key -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" >
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
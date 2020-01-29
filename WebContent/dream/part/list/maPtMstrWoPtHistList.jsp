<%--===========================================================================
자재마스터 사용이력
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.list.action.MaPtMstrWoPtHistListAction" %>
<%@ page import="dream.part.stk.action.MaPtStckDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 자재마스터 사용이력 -->
<title><bean:message key='TAB.maPtMstrWoPtHistList'/></title>
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
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	return sortColumn("maPtMstrWoPtHistList", this, maPtMstrWoPtHistListForm, "", ind, direction);
	});
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

function findGridList(sheetAction)
{
	//페이지id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (maPtMstrWoPtHistListForm.elements['maPtMstrCommonDTO.partId'].value == '') return;
	
	var form = document.maPtMstrWoPtHistListForm;	
	form.strutsAction.value = '<%=MaPtMstrWoPtHistListAction.LIST_FIND %>';
	
	var url = contextPath + "/maPtMstrWoPtHistList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPtMstrWoPtHistListForm), "", "Y");

}

/**
 * 엑셀 다운
 */
function goExcel()
{
	excelServerAction("maPtMstrWoPtHistList", maPtMstrWoPtHistListForm);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPtMstrWoPtHistList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPtMstrCommonDTO.partId"/><!-- Key -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
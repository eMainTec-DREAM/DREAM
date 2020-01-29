<%--===========================================================================
작업의뢰 시스템 요청 목록
author  nhkim8548
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.req.rpt.preworeqrate.action.ReqRptPreWoreqDetailListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 작업의뢰 사전 시스템 요청율 -->
<title><bean:message key='MENU.PREWOREQRATE'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

function loadPage() 
{
	setTitle("reqRptPreWoreqDetailListDTO.plantDesc", "reqRptPreWoreqDetailListDTO.month");
	initGrid();
}
/**
 * 그리드 초기화
 */
function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    myGrid.enableSmartRendering(true,500);
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	return sortColumn("reqRptPreWoreqDetailList", this, reqRptPreWoreqDetailListForm, "WONO", ind, direction);
	});
    myGrid.init();
    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/reqRptPreWoreqDetailList.do";
    reqRptPreWoreqDetailListForm.elements['strutsAction'].value = '<%=ReqRptPreWoreqDetailListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(reqRptPreWoreqDetailListForm), "PLANTID", "Y");
}

function goSearch()
{
    findGridList('Search');
}

/**
 * Excel Export
 */
function goExcel()
{
   excelServerAction("reqRptPreWoreqDetailList", reqRptPreWoreqDetailListForm);
}
//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/reqRptPreWoreqDetailList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="reqRptPreWoreqDetailListDTO.plantId"/>
<html:hidden property="reqRptPreWoreqDetailListDTO.plantDesc"/>
<html:hidden property="reqRptPreWoreqDetailListDTO.month"/>
    <div class="article_box" id="listBox">
    	<div class="grid_area">
        	<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
        </div>
	</div>
</html:form> 
</body>
</html>


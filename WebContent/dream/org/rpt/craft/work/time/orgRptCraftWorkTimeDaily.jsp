<%--===========================================================================
작업자 일별 작업시간
author  js.lee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.org.rpt.craft.work.time.action.OrgRptCraftWorkTimeDailyAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 일별 작업시간 -->
<title><bean:message key='PAGE.orgRptCraftWorkTimeDaily'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var selectedGridId;

function loadPage() 
{
	setTitle("orgRptCraftWorkTimeDailyDTO.empDesc");
    
	initGrid();
}

var myGrid;

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500)
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		selectedGridId = rowId;
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	return sortColumn("orgRptCraftWorkTimeDaily", this, orgRptCraftWorkTimeDailyForm, "EMPID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox","goSearch"); // grid, grid id, callBack
}

function goSearch()
{
	findGridList('Search');   
}

function findGridList(sheetAction)
{
	
	var form = document.orgRptCraftWorkTimeDailyForm;	
	form.strutsAction.value = '<%=OrgRptCraftWorkTimeDailyAction.LIST_FIND %>';

    var url = contextPath + "/orgRptCraftWorkTimeDaily.do";
    
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(orgRptCraftWorkTimeDailyForm));
}

/**
 * 엑셀 다운.
 */
function goExcel()
{
// 	excelAction(myGrid);
  	excelServerAction("orgRptCraftWorkTimeDaily", orgRptCraftWorkTimeDailyForm);
}
 
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/orgRptCraftWorkTimeDaily.do">
<html:hidden property="strutsAction"/>
<html:hidden property="orgRptCraftWorkTimeDailyDTO.startDate"/>
<html:hidden property="orgRptCraftWorkTimeDailyDTO.endDate"/>
<html:hidden property="orgRptCraftWorkTimeDailyDTO.empId"/>
<html:hidden property="orgRptCraftWorkTimeDailyDTO.empDesc"/>

	    <div class="article_box" id="listBox">
	        <div class="grid_area">
	            <div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
	        </div>
	    </div>


</html:form> 
</body>
</html>
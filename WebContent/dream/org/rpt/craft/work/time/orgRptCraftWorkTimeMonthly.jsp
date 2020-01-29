<%--===========================================================================
작업자 월별 작업시간
author  js.lee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.org.rpt.craft.work.time.action.OrgRptCraftWorkTimeMonthlyAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 월별 작업시간 -->
<title><bean:message key='PAGE.orgRptCraftWorkTimeMonthly'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var selectedGridId;

function loadPage() 
{
	setTitle("orgRptCraftWorkTimeMonthlyDTO.empDesc");
    
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
		goOpen();
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	return sortColumn("orgRptCraftWorkTimeMonthly", this, orgRptCraftWorkTimeMonthlyForm, "EMPID", ind, direction);
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
	
	var form = document.orgRptCraftWorkTimeMonthlyForm;	
	form.strutsAction.value = '<%=OrgRptCraftWorkTimeMonthlyAction.LIST_FIND %>';

    var url = contextPath + "/orgRptCraftWorkTimeMonthly.do";
    
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(orgRptCraftWorkTimeMonthlyForm));
}

/**
 * 엑셀 다운.
 */
function goExcel()
{
// 	excelAction(myGrid);
  	excelServerAction("orgRptCraftWorkTimeMonthly", orgRptCraftWorkTimeMonthlyForm);
}
 
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/orgRptCraftWorkTimeMonthly.do">
<html:hidden property="strutsAction"/>
<html:hidden property="orgRptCraftWorkTimeMonthlyDTO.startDate"/>
<html:hidden property="orgRptCraftWorkTimeMonthlyDTO.endDate"/>
<html:hidden property="orgRptCraftWorkTimeMonthlyDTO.empId"/>
<html:hidden property="orgRptCraftWorkTimeMonthlyDTO.empDesc"/>

	    <div class="article_box" id="listBox">
	        <div class="grid_area">
	            <div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
	        </div>
	    </div>


</html:form> 
</body>
</html>
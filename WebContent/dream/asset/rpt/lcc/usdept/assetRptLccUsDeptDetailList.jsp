<%--===========================================================================
고장TOP 상세(사용부서)
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.rpt.lcc.usdept.action.AssetRptLccUsDeptDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 고장TOP 상세(사용부서) -->
<title><bean:message key='MENU.LCCUSAGEDEPT'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var selectedRowId;

function loadPage() 
{
	
	setTitle("assetRptLccUsDeptDetailDTO.usageDeptDesc");
	
	initGrid();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		selectedRowId = rowId;
	});

	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox","goSearch"); // grid, grid id, callBack
}


function goSearch()
{
	findGridList('Search');   
}

function findGridList(gridAction)
{
	
	var form = document.assetRptLccUsDeptDetailForm;	
	form.strutsAction.value = '<%=AssetRptLccUsDeptDetailAction.LCC_EQUIP_DETAIL_FIND %>';

    var url = contextPath + "/assetRptLccUsDeptDetailList.do";
    
    doGridAction(gridAction, myGrid, "gridbox", url, FormQueryString(assetRptLccUsDeptDetailForm));
}

/**
 * 엑셀 다운.
 */
function goExcel()
{
	excelServerAction("assetRptLccUsDeptDetailList", assetRptLccUsDeptDetailForm );
}
 

/*
 * 고장이력 보기
 */
function goEqbmLink()
{
	if(typeof selectedRowId == "undefined" || "" == selectedRowId)
	{
		alertMessage1('<bean:message key="MESSAGE.MSG212"/>');
		return;
	}

	var fromDate = myGrid.cellById(selectedRowId,getIndexById(myGrid, "MONTH")).getValue().replace("-","")+"01";
	var toDate = myGrid.cellById(selectedRowId,getIndexById(myGrid, "MONTH")).getValue().replace("-","")+"31";
 	var usageDeptId   = assetRptLccUsDeptDetailForm.elements['assetRptLccUsDeptDetailDTO.usageDeptId'].value;	//getValueById(myGrid, selectedRowId,'USAGEDEPTID');
 	var usageDeptDesc = assetRptLccUsDeptDetailForm.elements['assetRptLccUsDeptDetailDTO.usageDeptDesc'].value;	//getValueById(myGrid, selectedRowId,'USAGEDEPTDESC');
 	var woStatus = "C";
	
	goEqbmList('', '', '', '', fromDate, toDate, woStatus, usageDeptId);
}
 
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assetRptLccUsDeptDetailList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="assetRptLccUsDeptDetailDTO.usageDeptId"/>
<html:hidden property="assetRptLccUsDeptDetailDTO.usageDeptDesc"/>
<html:hidden property="assetRptLccUsDeptDetailDTO.chartValue"/>
<html:hidden property="assetRptLccUsDeptDetailDTO.startDate"/>
<html:hidden property="assetRptLccUsDeptDetailDTO.endDate"/>

<html:hidden property="assetRptLccUsDeptCommonDTO.filterEqGrade"/>
<html:hidden property="assetRptLccUsDeptCommonDTO.filterEqGradeDesc"/>
<html:hidden property="assetRptLccUsDeptDetailDTO.plantId"/>
<html:hidden property="assetRptLccUsDeptDetailDTO.plantDesc"/>

		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
		
</html:form> 
</body>
</html>
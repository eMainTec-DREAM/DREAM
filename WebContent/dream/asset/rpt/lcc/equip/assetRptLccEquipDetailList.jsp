<%--===========================================================================
고장TOP 상세(설비)
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.rpt.lcc.equip.action.AssetRptLccEquipDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 고장TOP 상세(설비) -->
<title><bean:message key='MENU.LCCByEqp'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var selectedRowId;

function loadPage() 
{
	
	setTitle("assetRptLccEquipDetailDTO.itemDesc");
	
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
	
	var form = document.assetRptLccEquipDetailForm;	
	form.strutsAction.value = '<%=AssetRptLccEquipDetailAction.LCC_EQUIP_DETAIL_FIND %>';

    var url = contextPath + "/assetRptLccEquipDetailList.do";
    
    doGridAction(gridAction, myGrid, "gridbox", url, FormQueryString(assetRptLccEquipDetailForm));
}

/**
 * 엑셀 다운.
 */
function goExcel()
{
	 excelServerAction("assetRptLccEquipDetailList", assetRptLccEquipDetailForm );
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

	var equipId   = getValueById(myGrid, selectedRowId,'EQUIPID');
	var equipDesc = getValueById(myGrid, selectedRowId,'EQUIPDESC');
	var fromDate = myGrid.cellById(selectedRowId,getIndexById(myGrid, "MONTH")).getValue().replace("-","")+"01";
	var toDate = myGrid.cellById(selectedRowId,getIndexById(myGrid, "MONTH")).getValue().replace("-","")+"31";
	var woStatus = "C";
	
	goEqbmList('', '', equipId, equipDesc, fromDate, toDate, woStatus);
}
 
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assetRptLccEquipDetailList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="assetRptLccEquipDetailDTO.eqLocId"/>
<html:hidden property="assetRptLccEquipDetailDTO.eqLocDesc"/>
<html:hidden property="assetRptLccEquipDetailDTO.deptId"/>
<html:hidden property="assetRptLccEquipDetailDTO.deptDesc"/>
<html:hidden property="assetRptLccEquipDetailDTO.eqCtgId"/>
<html:hidden property="assetRptLccEquipDetailDTO.eqCtgDesc"/>
<html:hidden property="assetRptLccEquipDetailDTO.itemNo"/>
<html:hidden property="assetRptLccEquipDetailDTO.itemDesc"/>
<html:hidden property="assetRptLccEquipDetailDTO.startDate"/>
<html:hidden property="assetRptLccEquipDetailDTO.endDate"/>
<html:hidden property="assetRptLccEquipDetailDTO.plantId"/>
<html:hidden property="assetRptLccEquipDetailDTO.plantDesc"/>

		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
		
</html:form> 
</body>
</html>
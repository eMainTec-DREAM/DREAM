<%--===========================================================================
MTBF,MTTR 상세(설비)
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.rpt.mtbfmttr.equip.action.AssetRptMtbfmttrEquipDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- MTBF,MTTR 상세(설비) -->
<title><bean:message key='MENU.EQMTBFMTTR'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var selectedRowId;
function loadPage() 
{
	
	setTitle("assetRptMtbfmttrEquipDetailDTO.itemDesc");
	
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
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
   		return sortColumn("assetRptMtbfmttrEquipDetailList", this, assetRptMtbfmttrEquipDetailForm, "ITEMNO", ind, direction);
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
	
	var form = document.assetRptMtbfmttrEquipDetailForm;	
	form.strutsAction.value = '<%=AssetRptMtbfmttrEquipDetailAction.MTTRMTBF_EQUIP_DETAIL_FIND %>';

    var url = contextPath + "/assetRptMtbfmttrEquipDetailList.do";
    
    doGridAction(gridAction, myGrid, "gridbox", url, FormQueryString(assetRptMtbfmttrEquipDetailForm), "", "Y");
}

/**
 * 엑셀 다운.
 */
function goExcel()
{
	excelAction(myGrid);
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

	var eqlocId   = getValueById(myGrid, selectedRowId,'EQLOC_ID');
	var eqlocDesc = getValueById(myGrid, selectedRowId,'EQLOCDESC');
	var fromDate = myGrid.cellById(selectedRowId,getIndexById(myGrid, "MONTH")).getValue().replace("-","")+"01";
	var toDate = myGrid.cellById(selectedRowId,getIndexById(myGrid, "MONTH")).getValue().replace("-","")+"31";
	
	goEqbmList(eqlocId, eqlocDesc, '', '', fromDate, toDate);
}
 
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assetRptMtbfmttrEquipDetailList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="assetRptMtbfmttrEquipDetailDTO.equipId"/>
<html:hidden property="assetRptMtbfmttrEquipDetailDTO.itemDesc"/>
<html:hidden property="assetRptMtbfmttrEquipDetailDTO.startDate"/>
<html:hidden property="assetRptMtbfmttrEquipDetailDTO.endDate"/>

<html:hidden property="assetRptMtbfmttrEquipCommonDTO.filterEqGrade"/>
<html:hidden property="assetRptMtbfmttrEquipCommonDTO.filterEqGradeDesc"/>

		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
		
</html:form> 
</body>
</html>
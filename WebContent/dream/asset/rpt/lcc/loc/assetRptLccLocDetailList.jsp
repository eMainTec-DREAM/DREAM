<%--===========================================================================
고장TOP 상세(위치)
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.rpt.lcc.loc.action.AssetRptLccLocDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 고장TOP 상세(위치) -->
<title><bean:message key='MENU.LCCByLoc'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var selectedRowId;
function loadPage() 
{
	assetRptLccLocDetailForm.elements['assetRptLccLocDetailDTO.eqLocDesc'].value = assetRptLccLocDetailForm.elements['assetRptLccLocDetailDTO.eqLocDesc'].value.replace(/& gt;/gi,'>');
	
	setTitle("assetRptLccLocDetailDTO.eqLocDesc");
	
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
	
	var form = document.assetRptLccLocDetailForm;	
	form.strutsAction.value = '<%=AssetRptLccLocDetailAction.LCC_LOC_DETAIL_FIND %>';

    var url = contextPath + "/assetRptLccLocDetailList.do";
    
    doGridAction(gridAction, myGrid, "gridbox", url, FormQueryString(assetRptLccLocDetailForm));
}

/**
 * 엑셀 다운.
 */
function goExcel()
{
	excelServerAction("assetRptLccLocDetailList", assetRptLccLocDetailForm );
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

	var eqlocId	  = assetRptLccLocDetailForm.elements['assetRptLccLocDetailDTO.eqLocId'].value;		//getValueById(myGrid, selectedRowId,'EQLOCID');
 	var eqlocDesc = assetRptLccLocDetailForm.elements['assetRptLccLocDetailDTO.eqLocDesc'].value;	//getValueById(myGrid, selectedRowId,'EQLOCDESC');
	var fromDate = myGrid.cellById(selectedRowId,getIndexById(myGrid, "MONTH")).getValue().replace("-","")+"01";
	var toDate = myGrid.cellById(selectedRowId,getIndexById(myGrid, "MONTH")).getValue().replace("-","")+"31";
	var woStatus = "C";
	
	goEqbmList(eqlocId, eqlocDesc, '', '', fromDate, toDate, woStatus);
}
 
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assetRptLccLocDetailList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="assetRptLccLocDetailDTO.eqLocId"/>
<html:hidden property="assetRptLccLocDetailDTO.eqLocDesc"/>
<html:hidden property="assetRptLccLocDetailDTO.startDate"/>
<html:hidden property="assetRptLccLocDetailDTO.endDate"/>
<html:hidden property="assetRptLccLocDetailDTO.plantId"/>
<html:hidden property="assetRptLccLocDetailDTO.plantDesc"/>

		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
		
</html:form> 
</body>
</html>
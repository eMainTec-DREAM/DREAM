<%--===========================================================================
MTBF,MTTR 상세(위치)
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.rpt.mtbfmttr.loc.action.AssetRptMtbfmttrLocDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- MTBF,MTTR 상세(위치) -->
<title><bean:message key='MENU.LOCMTBFMTTR'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var selectedRowId;
function loadPage() 
{
	assetRptMtbfmttrLocDetailForm.elements['assetRptMtbfmttrLocDetailDTO.eqLocDesc'].value = assetRptMtbfmttrLocDetailForm.elements['assetRptMtbfmttrLocDetailDTO.eqLocDesc'].value.replace(/& gt;/gi,'>');
	
	setTitle("assetRptMtbfmttrLocDetailDTO.eqLocDesc");
	
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
	myGrid.attachEvent("onXLE",function(grdObj,count){ 
		setCounter(grdObj,"gridbox");
		
		for(var i = 0; grdObj.getColumnsNum() > i; i++)
  		{
  			if(grdObj.getColType(i) == "ron") grdObj.setNumberFormat("0,000.00",i,".",",");
  		}
	}); myGrid.init();

	setHeader(myGrid, "gridbox","goSearch"); // grid, grid id, callBack
}


function goSearch()
{
	findGridList('Search');   
}

function findGridList(gridAction)
{
	
	var form = document.assetRptMtbfmttrLocDetailForm;	
	form.strutsAction.value = '<%=AssetRptMtbfmttrLocDetailAction.MTTRMTBF_LOC_DETAIL_FIND %>';

    var url = contextPath + "/assetRptMtbfmttrLocDetailList.do";
    
    doGridAction(gridAction, myGrid, "gridbox", url, FormQueryString(assetRptMtbfmttrLocDetailForm));
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
<html:form action="/assetRptMtbfmttrLocDetailList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="assetRptMtbfmttrLocDetailDTO.eqLocId"/>
<html:hidden property="assetRptMtbfmttrLocDetailDTO.eqLocDesc"/>
<html:hidden property="assetRptMtbfmttrLocDetailDTO.startDate"/>
<html:hidden property="assetRptMtbfmttrLocDetailDTO.endDate"/>

		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
		
		
</html:form> 
</body>
</html>
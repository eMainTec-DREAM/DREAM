<%--===========================================================================
MTBF,MTTR 상세(사용부서)
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.rpt.mtbfmttr.usdept.action.AssetRptMtbfmttrUsDeptDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- MTBF,MTTR 상세(사용부서) -->
<title><bean:message key='MENU.MTBFMTTRUSAGEDEPT'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var selectedRowId;
function loadPage() 
{
	
	setTitle("assetRptMtbfmttrUsDeptDetailDTO.usageDeptDesc");
	
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
	var form = document.assetRptMtbfmttrUsDeptDetailForm;	
	form.strutsAction.value = '<%=AssetRptMtbfmttrUsDeptDetailAction.MTTRMTBF_EQUIP_DETAIL_FIND %>';

    var url = contextPath + "/assetRptMtbfmttrUsDeptDetailList.do";
    
    doGridAction(gridAction, myGrid, "gridbox", url, FormQueryString(assetRptMtbfmttrUsDeptDetailForm));
}

/**
 * 엑셀 다운.
 */
function goExcel()
{
	excelServerAction("assetRptMtbfmttrUsDeptDetailList", assetRptMtbfmttrUsDeptDetailForm );  
}

</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assetRptMtbfmttrUsDeptDetailList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="assetRptMtbfmttrUsDeptDetailDTO.plantId"/>
<html:hidden property="assetRptMtbfmttrUsDeptDetailDTO.plantDesc"/>
<html:hidden property="assetRptMtbfmttrUsDeptDetailDTO.usageDeptId"/>
<html:hidden property="assetRptMtbfmttrUsDeptDetailDTO.usageDeptDesc"/>
<html:hidden property="assetRptMtbfmttrUsDeptDetailDTO.startDate"/>
<html:hidden property="assetRptMtbfmttrUsDeptDetailDTO.endDate"/>

<html:hidden property="assetRptMtbfmttrUsDeptCommonDTO.filterSeparation"/>
<html:hidden property="assetRptMtbfmttrUsDeptCommonDTO.filterEqGrade"/>
<html:hidden property="assetRptMtbfmttrUsDeptCommonDTO.filterEqGradeDesc"/>

		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
		
</html:form> 
</body>
</html>
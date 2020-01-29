<%--===========================================================================
원단위분석 상세
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.basicunitanalysis.action.BasicUnitAnalysisDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 원단위분석 상세 -->
<title><bean:message key=''/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	setTitle("basicUnitAnalysisDetailDTO.plantDesc","basicUnitAnalysisDetailDTO.stdCheckPointDesc");
	
	initGrid();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox","goSearch"); // grid, grid id, callBack
}


function goSearch()
{
	findGridList('Search');   
}

function findGridList(gridAction)
{
	
	var form = document.basicUnitAnalysisDetailForm;	
	form.strutsAction.value = '<%=BasicUnitAnalysisDetailAction.BASIC_UNIT_ANALYSIS_DETAIL_FIND %>';

    var url = contextPath + "/basicUnitAnalysisDetailList.do";
    
    doGridAction(gridAction, myGrid, "gridbox", url, FormQueryString(basicUnitAnalysisDetailForm));
}

/**
 * 엑셀 다운.
 */
function goExcel()
{
	excelAction(myGrid);
}
 
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/basicUnitAnalysisDetailList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="basicUnitAnalysisDetailDTO.plantId"/>
<html:hidden property="basicUnitAnalysisDetailDTO.plantDesc"/>
<html:hidden property="basicUnitAnalysisDetailDTO.stdCheckPointDesc"/>
<html:hidden property="basicUnitAnalysisDetailDTO.startDate"/>
<html:hidden property="basicUnitAnalysisDetailDTO.endDate"/>
	
	
		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
		
</html:form> 
</body>
</html>
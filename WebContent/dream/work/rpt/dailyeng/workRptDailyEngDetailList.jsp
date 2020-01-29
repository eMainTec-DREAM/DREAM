<%--===========================================================================
에너지사용량 상세(일별)
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.dailyeng.action.WorkRptDailyEngDetailListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 에너지사용량 상세(일별) -->
<title><bean:message key='MENU.DAILYENG'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	var plantDesc = $("input[name='workRptDailyEngDetailListDTO.plantDesc']").val();
	var eqLocDesc = $("input[name='workRptDailyEngDetailListDTO.eqLocDesc']").val();
	var usageDeptDesc = $("input[name='workRptDailyEngDetailListDTO.usageDeptDesc']").val();
	var eqCtgDesc = $("input[name='workRptDailyEngDetailListDTO.eqCtgDesc']").val();
	var equipDesc = $("input[name='workRptDailyEngDetailListDTO.equipDesc']").val();
	if(equipDesc == "") {
		if(eqCtgDesc == "") {
			if(usageDeptDesc == "") {
				if(eqLocDesc ==""){
					setTitle("workRptDailyEngDetailListDTO.plantDesc");
				}
				else{
					setTitle("workRptDailyEngDetailListDTO.eqLocDesc");
				}
			}
			else {
				setTitle("workRptDailyEngDetailListDTO.usageDeptDesc");
			}
		}
		else {
			setTitle("workRptDailyEngDetailListDTO.eqCtgDesc");
		}
	}
	else {
		setTitle("workRptDailyEngDetailListDTO.equipDesc");
	}
	
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
	for(var i = 0; myGrid.getColumnsNum() > i; i++)
	{
		if(myGrid.getColType(i) == "ron" ) 
		{
			myGrid.setNumberFormat("0,000.00",i,".",",");
		}
	}
	
	findGridList('Search');   
}

function findGridList(gridAction)
{
	
	var form = document.workRptDailyEngDetailListForm;	
	form.strutsAction.value = '<%=WorkRptDailyEngDetailListAction.DETAIL_LIST_FIND %>';

    var url = contextPath + "/workRptDailyEngDetailList.do";
    
    doGridAction(gridAction, myGrid, "gridbox", url, FormQueryString(workRptDailyEngDetailListForm),"RESULTID");
}

/**
 * 엑셀 다운.
 */
function goExcel()
{
// 	excelAction(myGrid);
 	excelServerAction("workRptDailyEngDetailList", workRptDailyEngDetailListForm );  
}
 
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workRptDailyEngDetailList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="workRptDailyEngCommonDTO.filterStartDate"/>
<html:hidden property="workRptDailyEngCommonDTO.filterEndDate"/>

<html:hidden property="workRptDailyEngDetailListDTO.plantDesc"/>
<html:hidden property="workRptDailyEngDetailListDTO.usageDeptDesc"/>
<html:hidden property="workRptDailyEngDetailListDTO.eqCtgDesc"/>
<html:hidden property="workRptDailyEngDetailListDTO.eqLocDesc"/>
<html:hidden property="workRptDailyEngDetailListDTO.equipDesc"/>
<html:hidden property="workRptDailyEngDetailListDTO.plantId"/>
<html:hidden property="workRptDailyEngDetailListDTO.usageDeptId"/>
<html:hidden property="workRptDailyEngDetailListDTO.eqCtgId"/>
<html:hidden property="workRptDailyEngDetailListDTO.eqLocId"/>
<html:hidden property="workRptDailyEngDetailListDTO.equipId"/>

		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
		
</html:form> 
</body>
</html>
<%--===========================================================================
조직별 고장분석 상세
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.org.bd.action.WorkRptOrgBdDetailAction" %>
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

function loadPage() 
{
	
	setTitle("workRptOrgBdDetailDTO.deptDesc");
	
	initGrid();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
        return sortColumn("workRptOrgBdDetailList", this, workRptOrgBdDetailForm, "MONTH", ind, direction);
    });
	setHeader(myGrid, "gridbox","goSearch"); // grid, grid id, callBack
}


function goSearch()
{
	findGridList('Search');   
}

function findGridList(gridAction)
{
	
	var form = document.workRptOrgBdDetailForm;	
	form.strutsAction.value = '<%=WorkRptOrgBdDetailAction.LCC_EQUIP_DETAIL_FIND %>';

    var url = contextPath + "/workRptOrgBdDetailList.do";
    
    doGridAction(gridAction, myGrid, "gridbox", url, FormQueryString(workRptOrgBdDetailForm), "MONTH", "Y");
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
<html:form action="/workRptOrgBdDetailList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="workRptOrgBdDetailDTO.deptId"/>
<html:hidden property="workRptOrgBdDetailDTO.deptDesc"/>
<html:hidden property="workRptOrgBdDetailDTO.startDate"/>
<html:hidden property="workRptOrgBdDetailDTO.endDate"/>
<html:hidden property="workRptOrgBdDetailDTO.plantId"/>
<html:hidden property="workRptOrgBdDetailDTO.plantDesc"/>

		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
		
</html:form> 
</body>
</html>
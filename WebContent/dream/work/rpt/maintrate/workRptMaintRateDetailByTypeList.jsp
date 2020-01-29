<%--===========================================================================
author  youngjoo38
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.maintrate.action.WorkRptMaintRateDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 에너지 사용현황 -->
<title><bean:message key='LABEL.monthly'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

function loadPage() 
{
	setTitle("workRptMaintRateDetailDTO.plantDesc", "workRptMaintRateDetailDTO.deptDesc");
	
	initGrid();
	
}
/**
 * 그리드 초기화
 */
function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    myGrid.enableSmartRendering(true,500);
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
    myGrid.init();
    setHeader(myGrid, "gridbox", "goSearch"); // grid, grid id, callback
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/workRptMaintRateDetailByTypeList.do";
    workRptMaintRateListForm.elements['strutsAction'].value = '<%=WorkRptMaintRateDetailAction.TYPE_DETAIL_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workRptMaintRateListForm), "MONTH");
}

function goSearch()
{
    findGridList('Search');
}


/**
 * Excel Export
 */
function goExcel()
{
	excelAction(myGrid);
}

//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workRptMaintRateDetailByTypeList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="workRptMaintRateDetailDTO.deptId"/>
<html:hidden property="workRptMaintRateDetailDTO.deptDesc"/>
<html:hidden property="workRptMaintRateDetailDTO.plantId"/>
<html:hidden property="workRptMaintRateDetailDTO.plantDesc"/>
<html:hidden property="workRptMaintRateDetailDTO.startDate"/>
<html:hidden property="workRptMaintRateDetailDTO.endDate"/>

    	<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
		
</html:form> 
</body>
</html>


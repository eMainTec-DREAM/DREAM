<%--===========================================================================
계획보전율 상세(위치)
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.pm.ratio.action.WorkRptPmRatioDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 계획보전율 상세(위치) -->
<title><bean:message key='MENU.PMRATIO'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	workRptPmRatioDetailForm.elements['workRptPmRatioDetailDTO.eqLocDesc'].value = workRptPmRatioDetailForm.elements['workRptPmRatioDetailDTO.eqLocDesc'].value.replace(/& gt;/gi,'>');
	
	setTitle("workRptPmRatioDetailDTO.eqLocDesc");
	
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
	
	var form = document.workRptPmRatioDetailForm;	
	form.strutsAction.value = '<%=WorkRptPmRatioDetailAction.PM_RATIO_DETAIL_FIND %>';

    var url = contextPath + "/workRptPmRatioDetailList.do";
    
    doGridAction(gridAction, myGrid, "gridbox", url, FormQueryString(workRptPmRatioDetailForm));
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
<html:form action="/workRptPmRatioDetailList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="workRptPmRatioDetailDTO.eqLocId"/>
<html:hidden property="workRptPmRatioDetailDTO.eqLocDesc"/>
<html:hidden property="workRptPmRatioDetailDTO.startDate"/>
<html:hidden property="workRptPmRatioDetailDTO.endDate"/>

		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
		
</html:form> 
</body>
</html>
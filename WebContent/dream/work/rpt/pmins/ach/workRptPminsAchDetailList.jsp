<%--===========================================================================
예방점검 이행율(담당자) 상세
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.pmins.ach.action.WorkRptPminsAchDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 예방점검 이행율(담당자) 상세 -->
<title><bean:message key='MENU.PMINSACH'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	setTitle("workRptPminsAchDetailDTO.empDesc");
	
	initGrid();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
        return sortColumn("workRptPminsAchDetailList", this, workRptPminsAchDetailForm, "empId", ind, direction);
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
	
	var form = document.workRptPminsAchDetailForm;	
	form.strutsAction.value = '<%=WorkRptPminsAchDetailAction.PMINS_ACH_DETAIL_FIND %>';

    var url = contextPath + "/workRptPminsAchDetailList.do";
    
    doGridAction(gridAction, myGrid, "gridbox", url, FormQueryString(workRptPminsAchDetailForm));
}

/**
 * 엑셀 다운.
 */
function goExcel()
{
	excelServerAction("workRptPminsAchDetailList", workRptPminsAchDetailForm);
}
 
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workRptPminsAchDetailList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="workRptPminsAchDetailDTO.empId"/>
<html:hidden property="workRptPminsAchDetailDTO.empDesc"/>
<html:hidden property="workRptPminsAchDetailDTO.deptId"/>
<html:hidden property="workRptPminsAchDetailDTO.startDate"/>
<html:hidden property="workRptPminsAchDetailDTO.endDate"/>
	
	
		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
		
</html:form> 
</body>
</html>
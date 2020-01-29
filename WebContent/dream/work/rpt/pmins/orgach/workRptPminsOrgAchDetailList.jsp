<%--===========================================================================
예방점검 이행율(조직) 상세
author  sy.yang
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.pmins.orgach.action.WorkRptPminsOrgAchDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 예방점검 이행율(조직) 상세 -->
<title><bean:message key='MENU.PMINSORGACH'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	setTitle("workRptPminsOrgAchDetailDTO.deptDesc");

	initGrid();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
        return sortColumn("workRptPminsOrgAchDetailList", this, workRptPminsOrgAchDetailForm, "deptId", ind, direction);
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
	var form = document.workRptPminsOrgAchDetailForm;	
	form.strutsAction.value = '<%=WorkRptPminsOrgAchDetailAction.PMINS_ACH_DETAIL_FIND %>';

    var url = contextPath + "/workRptPminsOrgAchDetailList.do";
    
    doGridAction(gridAction, myGrid, "gridbox", url, FormQueryString(workRptPminsOrgAchDetailForm));
}

/**
 * 엑셀 다운.
 */
function goExcel()
{
	excelServerAction("workRptPminsOrgAchDetailList", workRptPminsOrgAchDetailForm);  
}
 
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workRptPminsOrgAchDetailList.do">
<html:hidden property="strutsAction"/>
	<html:hidden property="workRptPminsOrgAchDetailDTO.plantId"/>
	<html:hidden property="workRptPminsOrgAchDetailDTO.plantDesc"/>
	<html:hidden property="workRptPminsOrgAchDetailDTO.deptId"/>
	<html:hidden property="workRptPminsOrgAchDetailDTO.deptDesc"/>
	<html:hidden property="workRptPminsOrgAchDetailDTO.startDate"/>
	<html:hidden property="workRptPminsOrgAchDetailDTO.endDate"/>
		
	
		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
		
</html:form> 
</body>
</html>
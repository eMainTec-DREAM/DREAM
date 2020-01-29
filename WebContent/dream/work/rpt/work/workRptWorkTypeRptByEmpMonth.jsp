<%--===========================================================================
담당자별작업현황 - 담당자 월별작업현황 탭 
author  js.lee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.work.action.WorkRptWorkTypeRptByEmpMonthAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 담당자별작업현황 -->
<title><bean:message key='MENU.AP424'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var selectedRowId;
var myGrid;
function loadPage() 
{
	
	setTitle("workRptWorkTypeRptByEmpMonthDTO.title");
	
	initGrid();
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		workRptWorkTypeRptByEmpMonthForm.elements['workRptWorkTypeRptByEmpMonthDTO.deptId'].value = "";
        return sortColumn("workRptWorkTypeRptByEmpMonth", this, workRptWorkTypeRptByEmpMonthForm, "empId", ind, direction);
    });
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
	
	var form = document.workRptWorkTypeRptByEmpMonthForm;	
	form.strutsAction.value = '<%=WorkRptWorkTypeRptByEmpMonthAction.MONTH_LIST_FIND %>';

    var url = contextPath + "/workRptWorkTypeRptByEmpMonth.do";
    
    doGridAction(gridAction, myGrid, "gridbox", url, FormQueryString(workRptWorkTypeRptByEmpMonthForm));
}

/**
 * 엑셀 다운.
 */
function goExcel()
{
	excelServerAction("workRptWorkTypeRptByEmpMonth", workRptWorkTypeRptByEmpMonthForm );
}
 
/** 작업이력 */
function goWohistLink()
{
	var form = document.workRptWorkTypeRptByEmpMonthForm;
	var selectedId = myGrid.getSelectedRowId();
	if(selectedId == null) {
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	var startDate = getValueById(myGrid, selectedId,'yyyyMM');
	var endDate = getValueById(myGrid, selectedId,'yyyyMM');
	var plant = '';
	if (typeof form.elements['workRptWorkTypeRptByEmpMonthDTO.filterPlant'] != "undefined" && null != form.elements['workRptWorkTypeRptByEmpMonthDTO.filterPlant'].value && "" != form.elements['workRptWorkTypeRptByEmpMonthDTO.filterPlant'].value) 
		plant = form.elements['workRptWorkTypeRptByEmpMonthDTO.filterPlant'].value;
	var plantDesc = '';
	if (typeof form.elements['workRptWorkTypeRptByEmpMonthDTO.filterPlantDesc'] != "undefined" && null != form.elements['workRptWorkTypeRptByEmpMonthDTO.filterPlantDesc'].value && "" != form.elements['workRptWorkTypeRptByEmpMonthDTO.filterPlantDesc'].value) 
		plantDesc = form.elements['workRptWorkTypeRptByEmpMonthDTO.filterPlantDesc'].value;
	var woType = '';
	if (typeof form.elements['workRptWorkTypeRptByEmpMonthDTO.filterWoType'] != "undefined" && null != form.elements['workRptWorkTypeRptByEmpMonthDTO.filterWoType'].value && "" != form.elements['workRptWorkTypeRptByEmpMonthDTO.filterWoType'].value) 
		woType = form.elements['workRptWorkTypeRptByEmpMonthDTO.filterWoType'].value;
	var woTypeDesc = '';
	if (typeof form.elements['workRptWorkTypeRptByEmpMonthDTO.filterWoTypeDesc'] != "undefined" && null != form.elements['workRptWorkTypeRptByEmpMonthDTO.filterWoTypeDesc'].value && "" != form.elements['workRptWorkTypeRptByEmpMonthDTO.filterWoTypeDesc'].value) 
		woTypeDesc = form.elements['workRptWorkTypeRptByEmpMonthDTO.filterWoTypeDesc'].value;
	var empId = '';
	if (typeof form.elements['workRptWorkTypeRptByEmpMonthDTO.empId'] != "undefined" && null != form.elements['workRptWorkTypeRptByEmpMonthDTO.empId'].value && "" != form.elements['workRptWorkTypeRptByEmpMonthDTO.empId'].value) 
		empId = form.elements['workRptWorkTypeRptByEmpMonthDTO.empId'].value;
	var empDesc = '';
	if (typeof form.elements['workRptWorkTypeRptByEmpMonthDTO.empDesc'] != "undefined" && null != form.elements['workRptWorkTypeRptByEmpMonthDTO.empDesc'].value && "" != form.elements['workRptWorkTypeRptByEmpMonthDTO.empDesc'].value) 
		empDesc = form.elements['workRptWorkTypeRptByEmpMonthDTO.empDesc'].value;
	var deptId = '';
	if (typeof form.elements['workRptWorkTypeRptByEmpMonthDTO.deptId'] != "undefined" && null != form.elements['workRptWorkTypeRptByEmpMonthDTO.deptId'].value && "" != form.elements['workRptWorkTypeRptByEmpMonthDTO.deptId'].value) 
		deptId = form.elements['workRptWorkTypeRptByEmpMonthDTO.deptId'].value;
	var deptDesc = '';
	if (typeof form.elements['workRptWorkTypeRptByEmpMonthDTO.deptDesc'] != "undefined" && null != form.elements['workRptWorkTypeRptByEmpMonthDTO.deptDesc'].value && "" != form.elements['workRptWorkTypeRptByEmpMonthDTO.deptDesc'].value) 
		deptDesc = form.elements['workRptWorkTypeRptByEmpMonthDTO.deptDesc'].value;
	var wkctrId = '';
	if (typeof form.elements['workRptWorkTypeRptByEmpMonthDTO.wkctrId'] != "undefined" && null != form.elements['workRptWorkTypeRptByEmpMonthDTO.wkctrId'].value && "" != form.elements['workRptWorkTypeRptByEmpMonthDTO.wkctrId'].value) 
		wkctrId = form.elements['workRptWorkTypeRptByEmpMonthDTO.wkctrId'].value;
	var wkctrDesc = '';
	if (typeof form.elements['workRptWorkTypeRptByEmpMonthDTO.wkctrDesc'] != "undefined" && null != form.elements['workRptWorkTypeRptByEmpMonthDTO.wkctrDesc'].value && "" != form.elements['workRptWorkTypeRptByEmpMonthDTO.wkctrDesc'].value) 
		wkctrDesc = form.elements['workRptWorkTypeRptByEmpMonthDTO.wkctrDesc'].value;

	goWoList({
		"startDate":startDate+'01'
		,"endDate":endDate+getLastDayOfMonth(endDate.substr(0,4), endDate.substr(5,2))
		,"plant":plant
		,"plantDesc":plantDesc
		,"woType":woType
		,"woTypeDesc":woTypeDesc
		,"empId":empId
		,"empDesc":empDesc
		,"deptId":deptId
		,"deptDesc":deptDesc
		,"wkctrId":wkctrId
		,"wkctrDesc":wkctrDesc
	});
}

</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workRptWorkTypeRptByEmpMonth.do">
<html:hidden property="strutsAction"/>
<html:hidden property="workRptWorkTypeRptByEmpMonthDTO.title"/>
<html:hidden property="workRptWorkTypeRptByEmpMonthDTO.empId"/>
<html:hidden property="workRptWorkTypeRptByEmpMonthDTO.empDesc"/>
<html:hidden property="workRptWorkTypeRptByEmpMonthDTO.deptId"/>
<html:hidden property="workRptWorkTypeRptByEmpMonthDTO.deptDesc"/>
<html:hidden property="workRptWorkTypeRptByEmpMonthDTO.wkCtrId"/>
<html:hidden property="workRptWorkTypeRptByEmpMonthDTO.wkCtrDesc"/>
<html:hidden property="workRptWorkTypeRptByEmpMonthDTO.filterStartDate"/>
<html:hidden property="workRptWorkTypeRptByEmpMonthDTO.filterEndDate"/>
<html:hidden property="workRptWorkTypeRptByEmpMonthDTO.filterPlant"/>
<html:hidden property="workRptWorkTypeRptByEmpMonthDTO.filterPlantDesc"/>
<html:hidden property="workRptWorkTypeRptByEmpMonthDTO.filterWoType"/>
<html:hidden property="workRptWorkTypeRptByEmpMonthDTO.filterWoTypeDesc"/>

		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
		
</html:form> 
</body>
</html>
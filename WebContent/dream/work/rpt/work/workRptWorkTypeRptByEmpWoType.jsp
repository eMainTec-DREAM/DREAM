<%--===========================================================================
담당자별작업현황 - 담당자 작업종류별현황 탭 
author  js.lee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.work.action.WorkRptWorkTypeRptByEmpWoTypeAction" %>
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
	
	setTitle("workRptWorkTypeRptByEmpWoTypeDTO.title");
	
	initGrid();
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		workRptWorkTypeRptByEmpWoTypeForm.elements['workRptWorkTypeRptByEmpWoTypeDTO.deptId'].value = "";
        return sortColumn("workRptWorkTypeRptByEmpWoType", this, workRptWorkTypeRptByEmpWoTypeForm, "empId", ind, direction);
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
	
	var form = document.workRptWorkTypeRptByEmpWoTypeForm;	
	form.strutsAction.value = '<%=WorkRptWorkTypeRptByEmpWoTypeAction.WOTYPE_LIST_FIND %>';

    var url = contextPath + "/workRptWorkTypeRptByEmpWoType.do";
    
    doGridAction(gridAction, myGrid, "gridbox", url, FormQueryString(workRptWorkTypeRptByEmpWoTypeForm));
}

/**
 * 엑셀 다운.
 */
function goExcel()
{
	excelServerAction("workRptWorkTypeRptByEmpWoType", workRptWorkTypeRptByEmpWoTypeForm );
}
 
/** 작업이력 */
function goWohistLink()
{
	var form = document.workRptWorkTypeRptByEmpWoTypeForm;
	var selectedId = myGrid.getSelectedRowId();
	if(selectedId == null) {
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	var startDate = getValueById(myGrid, selectedId,'yyyyMM');
	var endDate = getValueById(myGrid, selectedId,'yyyyMM');
	var woType	= getValueById(myGrid, selectedId,'woType');
	var woTypeDesc	= getValueById(myGrid, selectedId,'woTypeDesc');
	
	var plant = '';
	if (typeof form.elements['workRptWorkTypeRptByEmpWoTypeDTO.filterPlant'] != "undefined" && null != form.elements['workRptWorkTypeRptByEmpWoTypeDTO.filterPlant'].value && "" != form.elements['workRptWorkTypeRptByEmpWoTypeDTO.filterPlant'].value) 
		plant = form.elements['workRptWorkTypeRptByEmpWoTypeDTO.filterPlant'].value;
	var plantDesc = '';
	if (typeof form.elements['workRptWorkTypeRptByEmpWoTypeDTO.filterPlantDesc'] != "undefined" && null != form.elements['workRptWorkTypeRptByEmpWoTypeDTO.filterPlantDesc'].value && "" != form.elements['workRptWorkTypeRptByEmpWoTypeDTO.filterPlantDesc'].value) 
		plantDesc = form.elements['workRptWorkTypeRptByEmpWoTypeDTO.filterPlantDesc'].value;
	
	var empId = '';
	if (typeof form.elements['workRptWorkTypeRptByEmpWoTypeDTO.empId'] != "undefined" && null != form.elements['workRptWorkTypeRptByEmpWoTypeDTO.empId'].value && "" != form.elements['workRptWorkTypeRptByEmpWoTypeDTO.empId'].value) 
		empId = form.elements['workRptWorkTypeRptByEmpWoTypeDTO.empId'].value;
	var empDesc = '';
	if (typeof form.elements['workRptWorkTypeRptByEmpWoTypeDTO.empDesc'] != "undefined" && null != form.elements['workRptWorkTypeRptByEmpWoTypeDTO.empDesc'].value && "" != form.elements['workRptWorkTypeRptByEmpWoTypeDTO.empDesc'].value) 
		empDesc = form.elements['workRptWorkTypeRptByEmpWoTypeDTO.empDesc'].value;
	var deptId = '';
	if (typeof form.elements['workRptWorkTypeRptByEmpWoTypeDTO.deptId'] != "undefined" && null != form.elements['workRptWorkTypeRptByEmpWoTypeDTO.deptId'].value && "" != form.elements['workRptWorkTypeRptByEmpWoTypeDTO.deptId'].value) 
		deptId = form.elements['workRptWorkTypeRptByEmpWoTypeDTO.deptId'].value;
	var deptDesc = '';
	if (typeof form.elements['workRptWorkTypeRptByEmpWoTypeDTO.deptDesc'] != "undefined" && null != form.elements['workRptWorkTypeRptByEmpWoTypeDTO.deptDesc'].value && "" != form.elements['workRptWorkTypeRptByEmpWoTypeDTO.deptDesc'].value) 
		deptDesc = form.elements['workRptWorkTypeRptByEmpWoTypeDTO.deptDesc'].value;
	var wkctrId = '';
	if (typeof form.elements['workRptWorkTypeRptByEmpWoTypeDTO.wkctrId'] != "undefined" && null != form.elements['workRptWorkTypeRptByEmpWoTypeDTO.wkctrId'].value && "" != form.elements['workRptWorkTypeRptByEmpWoTypeDTO.wkctrId'].value) 
		wkctrId = form.elements['workRptWorkTypeRptByEmpWoTypeDTO.wkctrId'].value;
	var wkctrDesc = '';
	if (typeof form.elements['workRptWorkTypeRptByEmpWoTypeDTO.wkctrDesc'] != "undefined" && null != form.elements['workRptWorkTypeRptByEmpWoTypeDTO.wkctrDesc'].value && "" != form.elements['workRptWorkTypeRptByEmpWoTypeDTO.wkctrDesc'].value) 
		wkctrDesc = form.elements['workRptWorkTypeRptByEmpWoTypeDTO.wkctrDesc'].value;
	
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
<html:form action="/workRptWorkTypeRptByEmpWoType.do">
<html:hidden property="strutsAction"/>
<html:hidden property="workRptWorkTypeRptByEmpWoTypeDTO.title"/>
<html:hidden property="workRptWorkTypeRptByEmpWoTypeDTO.empId"/>
<html:hidden property="workRptWorkTypeRptByEmpWoTypeDTO.empDesc"/>
<html:hidden property="workRptWorkTypeRptByEmpWoTypeDTO.deptId"/>
<html:hidden property="workRptWorkTypeRptByEmpWoTypeDTO.deptDesc"/>
<html:hidden property="workRptWorkTypeRptByEmpWoTypeDTO.wkCtrId"/>
<html:hidden property="workRptWorkTypeRptByEmpWoTypeDTO.wkCtrDesc"/>
<html:hidden property="workRptWorkTypeRptByEmpWoTypeDTO.filterStartDate"/>
<html:hidden property="workRptWorkTypeRptByEmpWoTypeDTO.filterEndDate"/>
<html:hidden property="workRptWorkTypeRptByEmpWoTypeDTO.filterPlant"/>
<html:hidden property="workRptWorkTypeRptByEmpWoTypeDTO.filterPlantDesc"/>
<html:hidden property="workRptWorkTypeRptByEmpWoTypeDTO.filterWoType"/>
<html:hidden property="workRptWorkTypeRptByEmpWoTypeDTO.filterWoTypeDesc"/>

		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
		
</html:form> 
</body>
</html>
<%--===========================================================================
작업오더 일마감 처리율 목록
author  js.lee
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.woendrate.action.WorkRptWoEndDetailListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 작업오더 일마감 처리율 -->
<title><bean:message key='MENU.WOENDRATE'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

function loadPage() 
{
	initGrid();
	setTitle("workRptWoEndDetailListDTO.plantDesc");
}

/**
 * 그리드 초기화
 */
function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		selectedRowId = rowId;
     });
    myGrid.enableSmartRendering(true,500);
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	workRptWoEndDetailListForm.elements['workRptWoEndDetailListDTO.plantId'].value = "";
        return sortColumn("workRptWoEndDetailList", this, workRptWoEndDetailListForm, "PLANTID", ind, direction);
    });
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
    myGrid.init();
    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/workRptWoEndDetailList.do";
    workRptWoEndDetailListForm.elements['strutsAction'].value = '<%=WorkRptWoEndDetailListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workRptWoEndDetailListForm), "", "Y");
}

function goSearch()
{
    if(checkValidation()) return;
    
    findGridList('Search');
    
}

/**
 * Excel Export
 */
function goExcel()
{
	if(checkValidation()) return;
	
   excelServerAction("workRptWoEndDetailList", workRptWoEndDetailListForm);
}

function goMachequipmentLink(_pageId)
{

	var equipId = getValueById(myGrid, selectedRowId,'EQUIPID');
	var eqctgType = getValueById(myGrid, selectedRowId,'EQCTGTYPE');

	if(typeof equipId == "undefined" || equipId == "") {
		alertMessage1('<bean:message key="MESSAGE.MSG212"/>');
		return;
	}else{
		goEquipDetail(equipId, eqctgType);
	}
}


function goWoLink()
{
	var wkorId = getValueById(myGrid, selectedRowId,'WKORID');
	var url = getValueById(myGrid, selectedRowId,'PARAM');
	
	if(typeof wkorId == "undefined" || typeof url == "undefined") {
		alertMessage1('<bean:message key="MESSAGE.MSG212"/>');
		return;
	} else {	
		goWo(wkorId, url);
	}
}
 //-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workRptWoEndDetailList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="workRptWoEndRateCommonDTO.filterPlantId"/>
<html:hidden property="workRptWoEndRateCommonDTO.filterDeptId"/>
<html:hidden property="workRptWoEndDetailListDTO.plantId"/>
<html:hidden property="workRptWoEndDetailListDTO.plantDesc"/>
<html:hidden property="workRptWoEndDetailListDTO.deptId"/>
<html:hidden property="workRptWoEndDetailListDTO.month"/>
        <div class="article_box" id="listBox">
            <div class="grid_area">
                <div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
            </div>
        </div>
</html:form> 
</body>
</html>


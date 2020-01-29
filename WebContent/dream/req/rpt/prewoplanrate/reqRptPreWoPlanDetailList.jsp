<%--===========================================================================
작업오더 사전 계획 수립률 목록
author  js.lee
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.req.rpt.prewoplanrate.action.ReqRptPreWoPlanDetailListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 작업오더 사전 계획 수립률 -->
<title><bean:message key='MENU.PREWOPLANRATE'/></title>
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
	setTitle("reqRptPreWoPlanDetailListDTO.plantDesc");
	
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
     myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
     myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
     	return sortColumn("reqRptPreWoPlanDetailList", this, reqRptPreWoPlanDetailListForm, "WONUM", ind, direction);
 	});
     myGrid.init();
     setHeader(myGrid, "gridbox"); // grid, grid id
 }
/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/reqRptPreWoPlanDetailList.do";
    reqRptPreWoPlanDetailListForm.elements['strutsAction'].value = '<%=ReqRptPreWoPlanDetailListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(reqRptPreWoPlanDetailListForm), "", "Y");
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
	
   excelServerAction("reqRptPreWoPlanDetailList", reqRptPreWoPlanDetailListForm);
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
	console.log(getValueById(myGrid, selectedRowId,'WKORID'));
	var wkorId = getValueById(myGrid, selectedRowId,'WKORID');
	var url = getValueById(myGrid, selectedRowId,'PARAM');
	
	if(typeof wkorId == "undefined" || typeof url == "undefined") {
		alertMessage1('<bean:message key="MESSAGE.MSG212"/>');
		return;
	} else {
		goWo(wkorId, url);
	}
	
} 

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/reqRptPreWoPlanDetailList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="reqRptPreWoPlanDetailListDTO.plantId"/>
<html:hidden property="reqRptPreWoPlanDetailListDTO.plantDesc"/>
<html:hidden property="reqRptPreWoPlanDetailListDTO.month"/>
<html:hidden property="reqRptPreWoPlanDetailListDTO.deptId"/>
        <div class="article_box" id="listBox">
            <div class="grid_area">
                <div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
            </div>
        </div>
</html:form> 
</body>
</html>


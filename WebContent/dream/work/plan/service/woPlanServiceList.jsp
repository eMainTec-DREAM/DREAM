<%--===========================================================================
서비스작업 - 목록 
author  nhkim8548
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.plan.service.action.WoPlanServiceAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<title><bean:message key='TAB.woPlanServiceList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<script language="javascript">
var myGrid;

function loadPage() 
{
	setForUpdate();
	
	initGrid();
}

function initGrid()
{
	setInitVal("woPlanServiceDTO.wkOrId", woPlanServiceForm.elements['woPlanCommonDTO.wkOrId'].value);
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	woPlanServiceForm.elements['woPlanServiceDTO.woPlanServiceId'].value = "";
        return sortColumn("woPlanServiceList", this, woPlanServiceForm, "woPlanServiceId", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
	myGrid.init();
	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	woPlanServiceForm.elements['woPlanServiceDTO.woPlanServiceId'].value = "";	// 검색시 Tab 이동Key Clear
    
	findGridList('Search');   
}

function findGridList(sheetAction) {
	var url = contextPath + "/woPlanServiceList.do";
	
	woPlanServiceForm.elements['strutsAction'].value = '<%=WoPlanServiceAction.LIST_FIND%>';
	
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(woPlanServiceForm), "woPlanServiceId", "Y");
}

/* 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_woPlanServiceId) {
	woPlanServiceForm.elements['woPlanServiceDTO.woPlanServiceId'].value = _woPlanServiceId;
	
	findGridList('ReloadRow');
	
	woPlanServiceForm.elements['woPlanServiceDTO.woPlanServiceId'].value = "";
}
/**
* Tab 이동시 호출
*/
function goTabPage(pageId) {
	tabValidationCheck(myGrid, pageId, "goTabPageAction");
}

function goTabPageAction(pageId, selectedId) {
	woPlanServiceForm.elements['woPlanServiceDTO.woPlanServiceId'].value = getValueById(myGrid, selectedId, 'woPlanServiceId');
	
	goCommonTabPage(woPlanServiceForm, <%= WoPlanServiceAction.DETAIL_INIT %>, pageId);
}

/*
 * 상세열기
 */
function goOpen() {
	goTabPage('woPlanServiceDetail');
}

function goOpenAction() {
	var selectedId = myGrid.getSelectedRowId();
    
    if(selectedId == null) return;
    
    woPlanServiceForm.elements['woPlanServiceDTO.woPlanServiceId'].value = getValueById(myGrid, selectedId, 'woPlanServiceId');
    woPlanServiceForm.elements['strutsAction'].value = '<%=WoPlanServiceAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(woPlanServiceForm), 'woPlanServiceDetail');
}
 
/*
 * 생성
 */
function goCreate() {
	createValidationCheck(myGrid, "woPlanServiceDetail" , "goCreateAction");
}

function goCreateAction(pageId) {
	woPlanServiceForm.elements['woPlanServiceDTO.woPlanServiceId'].value = "";
	goCommonTabPage(woPlanServiceForm, '', pageId);
}

/*
 * 삭제
 */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'isDelCheck', 'woPlanServiceId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
        alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
        return;
    }

    woPlanServiceForm.strutsAction.value = '<%=WoPlanServiceAction.LIST_DELETE%>';
    var url = contextPath + "/woPlanServiceList.do";
    
    $.post(url,FormQueryString(woPlanServiceForm)+delArray , function(_data){
        afterDelete();
    });
}
 
function afterDelete(){
	goClose('woPlanServiceDetail', this);

    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/*
 * Excel Export
 */
function goExcel()  {
	woPlanServiceForm.elements['woPlanServiceDTO.woPlanServiceId'].value = "";
	excelServerAction("woPlanServiceList", woPlanServiceForm);
}
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/woPlanServiceList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="woPlanCommonDTO.wkOrId"/><!-- Key -->
<html:hidden property="woPlanServiceDTO.wkOrId"/>
<html:hidden property="woPlanServiceDTO.woPlanServiceId"/><!-- Key -->
<!-- searchbox 박스 Line -->
<div class="article_box" id="listBox">
	<div class="grid_area">
    	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
    </div>
</div>
</html:form> 
</body>
</html>
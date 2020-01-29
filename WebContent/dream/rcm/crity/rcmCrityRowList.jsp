<%--===========================================================================
Criticality Matrix Row List
author  kim21017
version $Id: rcmCrityRowList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.rcm.crity.action.RcmCrityRowListAction" %>
<%@ page import="dream.rcm.crity.action.RcmCrityRowDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- Row 항목 정의 -->
<title><bean:message key='TAB.rcmCrityRowList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

/** 자동완성 변수 */

function loadPage() 
{
    initGrid();
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	rcmCrityRowListForm.elements['rcmCrityRowListDTO.crityRowId'].value = "";
    	return sortColumn("rcmCrityRowList", this, rcmCrityRowListForm, "CRITYROWID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (rcmCrityRowListForm.elements['rcmCrityCommonDTO.crityListId'].value == '') return;
    var url = contextPath + "/rcmCrityRowList.do";
    rcmCrityRowListForm.elements['strutsAction'].value = '<%=RcmCrityRowListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(rcmCrityRowListForm), "CRITYROWID", "Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_crityRowId)
{
	rcmCrityRowListForm.elements['rcmCrityRowListDTO.crityRowId'].value = _crityRowId;
	findGridList('ReloadRow');
	rcmCrityRowListForm.elements['rcmCrityRowListDTO.crityRowId'].value = "";
}

function goSearch()
{
	findGridList('Search');
}

/**
 * Tab 이동시 호출
 */
function goTabPage(pageId)
{
	tabValidationCheck(myGrid, pageId, "goTabPageAction");  
}

function goTabPageAction(pageId, selectedId)
{
	rcmCrityRowListForm.elements['rcmCrityRowListDTO.crityRowId'].value =  getValueById(myGrid, selectedId,'CRITYROWID');  
	goCommonTabPage(rcmCrityRowListForm, <%= RcmCrityRowDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('rcmCrityRowDetail');
}

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "rcmCrityRowDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	rcmCrityRowListForm.elements['rcmCrityRowListDTO.crityRowId'].value = "";
    goCommonTabPage(rcmCrityRowListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'CRITYROWID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    rcmCrityRowListForm.strutsAction.value = '<%=RcmCrityRowListAction.LIST_DELETE%>';
    var url = contextPath + "/rcmCrityRowList.do";
    
    $.post(url,FormQueryString(rcmCrityRowListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('rcmCrityRowDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
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
<html:form action="/rcmCrityRowList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="rcmCrityCommonDTO.crityListId"/><!-- Key -->
<html:hidden property="rcmCrityRowListDTO.crityRowId"/><!-- Key -->
    <!-- searchbox 박스 Line -->

	<div class="article_box" id="listBox">
		<div class="grid_area">
			<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
		</div>
	</div>

</html:form> 
</body>
</html>
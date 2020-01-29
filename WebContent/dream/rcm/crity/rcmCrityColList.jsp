<%--===========================================================================
Criticality Matrix Col List
author  kim21017
version $Id: rcmCrityColList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.rcm.crity.action.RcmCrityColListAction" %>
<%@ page import="dream.rcm.crity.action.RcmCrityColDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- Column 항목 정의 -->
<title><bean:message key='TAB.rcmCrityColList'/></title>
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
    	rcmCrityColListForm.elements['rcmCrityColListDTO.crityColId'].value = "";
    	return sortColumn("rcmCrityColList", this, rcmCrityColListForm, "CRITYCOLID", ind, direction);
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
	if (rcmCrityColListForm.elements['rcmCrityCommonDTO.crityListId'].value == '') return;
    var url = contextPath + "/rcmCrityColList.do";
    rcmCrityColListForm.elements['strutsAction'].value = '<%=RcmCrityColListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(rcmCrityColListForm), "CRITYCOLID", "Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_crityColId)
{
	rcmCrityColListForm.elements['rcmCrityColListDTO.crityColId'].value = _crityColId;
	findGridList('ReloadRow');
	rcmCrityColListForm.elements['rcmCrityColListDTO.crityColId'].value = "";
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
	rcmCrityColListForm.elements['rcmCrityColListDTO.crityColId'].value =  getValueById(myGrid, selectedId,'CRITYCOLID');  
	goCommonTabPage(rcmCrityColListForm, <%= RcmCrityColDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('rcmCrityColDetail');
}

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "rcmCrityColDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	rcmCrityColListForm.elements['rcmCrityColListDTO.crityColId'].value = "";
    goCommonTabPage(rcmCrityColListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'CRITYCOLID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    rcmCrityColListForm.strutsAction.value = '<%=RcmCrityColListAction.LIST_DELETE%>';
    var url = contextPath + "/rcmCrityColList.do";
    
    $.post(url,FormQueryString(rcmCrityColListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('rcmCrityColDetail');
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
<html:form action="/rcmCrityColList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="rcmCrityCommonDTO.crityListId"/><!-- Key -->
<html:hidden property="rcmCrityColListDTO.crityColId"/><!-- Key -->
    <!-- searchbox 박스 Line -->

	<div class="article_box" id="listBox">
		<div class="grid_area">
			<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
		</div>
	</div>

</html:form> 
</body>
</html>
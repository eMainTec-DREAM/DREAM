<%--===========================================================================
평가기준
author  kim21017
version $Id: assBasePointValList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.ass.base.action.AssBasePointValListAction" %>
<%@ page import="dream.ass.base.action.AssBasePointValDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 평가기준 -->
<title><bean:message key='TAB.assBasePointValList'/></title>
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
		assBasePointValListForm.elements['assBasePointValListDTO.assBasePointValId'].value = "";
    	return sortColumn("assBasePointValList", this, assBasePointValListForm, "ASSBASEPOINTVALID", ind, direction);
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
	if (assBasePointValListForm.elements['assBasePointListDTO.assBasePointId'].value == '') return;
    var url = contextPath + "/assBasePointValList.do";
    assBasePointValListForm.elements['strutsAction'].value = '<%=AssBasePointValListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(assBasePointValListForm), "ASSBASEPOINTVALID","Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_assBasePointValId)
{
	assBasePointValListForm.elements['assBasePointValListDTO.assBasePointValId'].value = _assBasePointValId;
	findGridList('ReloadRow');
	assBasePointValListForm.elements['assBasePointValListDTO.assBasePointValId'].value = "";
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
	assBasePointValListForm.elements['assBasePointValListDTO.assBasePointValId'].value =  getValueById(myGrid, selectedId,'ASSBASEPOINTVALID');  
	goCommonTabPage(assBasePointValListForm, <%= AssBasePointValDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('assBasePointValDetail');
}


function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    assBasePointValListForm.elements['assBasePointValListDTO.assBasePointValId'].value =  getValueById(myGrid, selectedId,'ASSBASEPOINTVALID');  
    assBasePointValListForm.elements['strutsAction'].value = '<%=AssBasePointValDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(assBasePointValListForm), 'assBasePointValDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "assBasePointValDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	assBasePointValListForm.elements['assBasePointValListDTO.assBasePointValId'].value = "";
    goCommonTabPage(assBasePointValListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'ASSBASEPOINTVALID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    assBasePointValListForm.strutsAction.value = '<%=AssBasePointValListAction.LIST_DELETE%>';
    var url = contextPath + "/assBasePointValList.do";
    
    $.post(url,FormQueryString(assBasePointValListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('assBasePointValDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	assBasePointValListForm.elements['assBasePointValListDTO.assBasePointValId'].value = "";
	excelServerAction("assBasePointValList", assBasePointValListForm );  
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assBasePointValList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="assBaseCommonDTO.assBaseListId"/><!-- Key -->
<html:hidden property="assBasePointListDTO.assBasePointId"/><!-- Key -->
<html:hidden property="assBasePointValListDTO.assBasePointValId"/><!-- Key -->
    <!-- searchbox 박스 Line -->

	<div class="article_box" id="listBox">
		<div class="grid_area">
			<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
		</div>
	</div>

</html:form> 
</body>
</html>
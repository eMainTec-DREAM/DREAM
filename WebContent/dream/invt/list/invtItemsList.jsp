<%--===========================================================================
구매항목 목록
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.invt.list.action.InvtItemsListAction" %>
<%@ page import="dream.invt.list.action.InvtDetailAction" %>
<%@ page import="dream.invt.list.action.InvtItemsDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<title><bean:message key='LABEL.questionPoint'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var myGrid;
var evId, proGrid, rId; //Event Id, Process Obj, Row Id
var isNew = false;
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
		//goTabPage("invtItemsDetail");
		goOpen();
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		invtItemsListForm.elements['invtCommonDTO.invtItemsId'].value = "";
        return sortColumn("invtItemsList", this, invtItemsListForm, "INVTITEMSID", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox", true)});
	
	myGrid.init();
	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	findGridList('Search');
}

/**
 * gird find
 */
function findGridList(sheetAction)
{
	if (invtItemsListForm.elements['invtCommonDTO.invtlistId'].value == '') return;
	
	var form = document.invtItemsListForm;	
	form.strutsAction.value = '<%=InvtItemsListAction.LIST_FIND%>'; 

	var url = contextPath + "/invtItemsList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(invtItemsListForm), "invtItemsId","Y");

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
	var form = document.invtItemsListForm;
	
	if(!isNew)
	{
		form.elements['invtCommonDTO.invtItemsId'].value = getValueById(myGrid, selectedId,'invtItemsId');
	}
	
	goCommonTabPage(form, <%= InvtItemsDetailAction.DETAIL_INIT %>, pageId);
	
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_invtItemsId)
{
	invtItemsListForm.elements['invtCommonDTO.invtItemsId'].value = _invtItemsId;
	findGridList('ReloadRow');
	invtItemsListForm.elements['invtCommonDTO.invtItemsId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('invtItemsDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    if(!isNew)
	{
    	invtItemsListForm.elements['invtCommonDTO.invtItemsId'].value =  getValueById(myGrid, selectedId,'invtItemsId');  
	}
    invtItemsListForm.elements['strutsAction'].value = '<%=InvtItemsDetailAction.DETAIL_INIT %>';
    openQuickTabPage(FormQueryString(invtItemsListForm), 'invtItemsDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "invtItemsDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	invtItemsListForm.elements['invtCommonDTO.invtItemsId'].value = "";
	goCommonTabPage(invtItemsListForm, '', pageId);
}
 

/**
 * Excel Export
 */
function goExcel()
{
    //excelAction(myGrid);
	invtItemsListForm.elements['invtCommonDTO.invtItemsId'].value = "";
	excelServerAction("invtItemsList", invtItemsListForm);
}

/**
 * 삭제
 */
 function goDelete()
 {
	var chkArray = getSelectRows(myGrid, 'ISDELCHECK','invtItemsStatus');
	if(chkArray.match('=RA') || chkArray.match('=OA') || chkArray.match('=C')) {
		alertMessage1('<bean:message key="MESSAGE.MSG0178"/>');
		return;
	}
	
	var delArray = getDeletRows(myGrid, 'ISDELCHECK','invtItemsId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	invtItemsListForm.strutsAction.value = '<%=InvtItemsListAction.LIST_DELETE%>';
	var url = contextPath + "/invtItemsList.do";
	
    $.post(url,FormQueryString(invtItemsListForm)+delArray , function(_data){
    	afterDelete();
    });
 }

function afterDelete(){
	goClose('invtItemsDetail', this);
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

function setKeyAftercopy(_newId)
{
	findGridRow(_newId);
	
	//상세 창 열기
	isNew = true;
	invtItemsListForm.elements['invtCommonDTO.invtItemsId'].value = _newId;
	goOpen();
	isNew = false;
}
   
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/invtItemsList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="invtCommonDTO.invtlistId"/><!-- Key -->
<html:hidden property="invtCommonDTO.invtItemsId"/>
<html:hidden property="invtCommonDTO.description"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
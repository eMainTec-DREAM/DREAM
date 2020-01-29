<%--===========================================================================
응답 목록
author  kim21017
version $Id: rcmFuncEqItemList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="common.tree.action.TreeAction" %>
<%@ page import="dream.rcm.funceq.action.RcmFuncEqItemListAction" %>
<%@ page import="dream.rcm.funceq.action.RcmFuncEqDetailAction" %>
<%@ page import="dream.rcm.funceq.action.RcmFuncEqItemDetailAction" %>
<%@ page import="dream.rcm.funceq.action.LovEquipCtgListAction" %>
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

function loadPage() 
{
	initGrid();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("rcmFuncEqItemDetail");
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	rcmFuncEqItemListForm.elements['rcmFuncEqItemListDTO.rcmFfEqId'].value = "";
    	return sortColumn("rcmFuncEqItemList", this, rcmFuncEqItemListForm, "RCMFFEQID", ind, direction);
	});
    
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

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
	if (rcmFuncEqItemListForm.elements['rcmFuncEqCommonDTO.rcmFfailId'].value == '') return;
	
	var form = document.rcmFuncEqItemListForm;	
	form.strutsAction.value = '<%=RcmFuncEqItemListAction.QNA_ANS_LIST_FIND%>'; 

	var url = contextPath + "/rcmFuncEqItemList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(rcmFuncEqItemListForm), "RCMFFEQID", "Y");

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
	var form = document.rcmFuncEqItemListForm;
	form.elements['rcmFuncEqItemListDTO.rcmFfEqId'].value = getValueById(myGrid, selectedId,'RCMFFEQID');
    
	goCommonTabPage(form, <%= RcmFuncEqItemDetailAction.QNA_ANS_DETAIL_INIT %>, "rcmFuncEqItemDetail");
	
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_rcmFfEqId)
{
	rcmFuncEqItemListForm.elements['rcmFuncEqItemListDTO.rcmFfEqId'].value = _rcmFfEqId;
	findGridList('ReloadRow');
	rcmFuncEqItemListForm.elements['rcmFuncEqItemListDTO.rcmFfEqId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('rcmFuncEqItemDetail');
}

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "rcmFuncEqItemDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	rcmFuncEqItemListForm.elements['rcmFuncEqItemListDTO.rcmFfEqId'].value = "";
	goCommonTabPage(rcmFuncEqItemListForm, '', pageId);
}
 

/**
 * Excel Export
 */
function goExcel()
{
    excelAction(myGrid);
}

/**
 * 삭제
 */
 function goDelete()
 {
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'RCMFFEQID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	rcmFuncEqItemListForm.strutsAction.value = '<%=RcmFuncEqItemListAction.QNA_ANS_LIST_DELETE%>';
	var url = contextPath + "/rcmFuncEqItemList.do";
	
    $.post(url,FormQueryString(rcmFuncEqItemListForm)+delArray , function(_data){
    	afterDelete();
    });
 }

function afterDelete(){
	goClose('rcmFuncEqItemDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

function goCtgselect()
{
	lovEquipCtg('rcmFuncEqItemListDTO.multiKey','rcmFuncEqItemListDTO.multiDesc');
}

/**
 * 팝업 값 선택 후 DB INSERT
 */
function afterSetValue(lovType,rtnValue)
{
	rcmFuncEqItemListForm.strutsAction.value = '<%=RcmFuncEqItemListAction.RCM_FUNC_LIST_INPUT%>';
	var url = contextPath + "/rcmFuncEqItemList.do";
	
    $.post(url,FormQueryString(rcmFuncEqItemListForm), function(_data){
    	goSearch()
    });
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/rcmFuncEqItemList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="rcmFuncEqCommonDTO.rcmFfailId"/><!-- Key -->
<html:hidden property="rcmFuncEqItemListDTO.rcmFfEqId"/><!-- Detail Key -->
<html:hidden property="rcmFuncEqItemListDTO.multiKey"/><!-- MultiSelect Key -->
<html:hidden property="rcmFuncEqItemListDTO.multiDesc"/><!-- MultiSelect Desc -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" >
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
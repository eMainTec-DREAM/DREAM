<%--===========================================================================
응답 목록
author  kim21017
version $Id: rcmFfailItemList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="common.tree.action.TreeAction" %>
<%@ page import="dream.rcm.ffail.action.RcmFfailItemListAction" %>
<%@ page import="dream.rcm.ffail.action.RcmFfailDetailAction" %>
<%@ page import="dream.rcm.ffail.action.RcmFfailItemDetailAction" %>
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
		goTabPage("rcmFfailItemDetail");
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		rcmFfailItemListForm.elements['rcmFfailItemListDTO.rcmFfailId'].value = "";
	   	return sortColumn("rcmFfailItemList", this, rcmFfailItemListForm, "RCMFFAILID", ind, direction);
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
	if (rcmFfailItemListForm.elements['rcmFfailCommonDTO.rcmFuncId'].value == '') return;
	
	var form = document.rcmFfailItemListForm;	
	form.strutsAction.value = '<%=RcmFfailItemListAction.QNA_ANS_LIST_FIND%>'; 

	var url = contextPath + "/rcmFfailItemList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(rcmFfailItemListForm), "RCMFFAILID", "Y");

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
	var form = document.rcmFfailItemListForm;
	form.elements['rcmFfailItemListDTO.rcmFfailId'].value = getValueById(myGrid, selectedId,'RCMFFAILID');
    
	goCommonTabPage(form, <%= RcmFfailItemDetailAction.QNA_ANS_DETAIL_INIT %>, "rcmFfailItemDetail");
	
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_rcmFfailId)
{
	rcmFfailItemListForm.elements['rcmFfailItemListDTO.rcmFfailId'].value = _rcmFfailId;
	findGridList('ReloadRow');
	rcmFfailItemListForm.elements['rcmFfailItemListDTO.rcmFfailId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('rcmFfailItemDetail');
}

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "rcmFfailItemDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	rcmFfailItemListForm.elements['rcmFfailItemListDTO.rcmFfailId'].value = "";
	goCommonTabPage(rcmFfailItemListForm, '', pageId);
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
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'RCMFFAILID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	rcmFfailItemListForm.strutsAction.value = '<%=RcmFfailItemListAction.QNA_ANS_LIST_DELETE%>';
	var url = contextPath + "/rcmFfailItemList.do";
	
    $.post(url,FormQueryString(rcmFfailItemListForm)+delArray , function(_data){
    	afterDelete();
    });
 }

function afterDelete(){
	goClose('rcmFfailItemDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/rcmFfailItemList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="rcmFfailCommonDTO.rcmFuncId"/><!-- Key -->
<html:hidden property="rcmFfailItemListDTO.rcmFfailId"/><!-- Detail Key -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" >
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
<%--===========================================================================
응답 목록
author  kim21017
version $Id: rcmTaskMapItemList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="common.tree.action.TreeAction" %>
<%@ page import="dream.rcm.taskmap.action.RcmTaskMapItemListAction" %>
<%@ page import="dream.rcm.taskmap.action.RcmTaskMapDetailAction" %>
<%@ page import="dream.rcm.taskmap.action.RcmTaskMapItemDetailAction" %>
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
		goTabPage("rcmTaskMapItemDetail");
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	rcmTaskMapItemListForm.elements['rcmTaskMapItemListDTO.pmTaskmapId'].value = "";
    	return sortColumn("rcmTaskMapItemList", this, rcmTaskMapItemListForm, "PMTASKMAPID", ind, direction);
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
	if (rcmTaskMapItemListForm.elements['rcmTaskMapCommonDTO.pmTaskMapListId'].value == '') return;
	
	var form = document.rcmTaskMapItemListForm;	
	form.strutsAction.value = '<%=RcmTaskMapItemListAction.QNA_ANS_LIST_FIND%>'; 

	var url = contextPath + "/rcmTaskMapItemList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(rcmTaskMapItemListForm), "PMTASKMAPID", "Y");

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
	var form = document.rcmTaskMapItemListForm;
	form.elements['rcmTaskMapItemListDTO.pmTaskmapId'].value = getValueById(myGrid, selectedId,'PMTASKMAPID');
    
	goCommonTabPage(form, <%= RcmTaskMapItemDetailAction.QNA_ANS_DETAIL_INIT %>, "rcmTaskMapItemDetail");
	
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pmTaskmapId)
{
	rcmTaskMapItemListForm.elements['rcmTaskMapItemListDTO.pmTaskmapId'].value = _pmTaskmapId;
	findGridList('ReloadRow');
	rcmTaskMapItemListForm.elements['rcmTaskMapItemListDTO.pmTaskmapId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('rcmTaskMapItemDetail');
}

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "rcmTaskMapItemDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	rcmTaskMapItemListForm.elements['rcmTaskMapItemListDTO.pmTaskmapId'].value = "";
	goCommonTabPage(rcmTaskMapItemListForm, '', pageId);
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
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'PMTASKMAPID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	rcmTaskMapItemListForm.strutsAction.value = '<%=RcmTaskMapItemListAction.QNA_ANS_LIST_DELETE%>';
	var url = contextPath + "/rcmTaskMapItemList.do";
	
    $.post(url,FormQueryString(rcmTaskMapItemListForm)+delArray , function(_data){
    	afterDelete();
    });
 }

function afterDelete(){
	goClose('rcmTaskMapItemDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/rcmTaskMapItemList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="rcmTaskMapCommonDTO.pmTaskMapListId"/><!-- Key -->
<html:hidden property="rcmTaskMapItemListDTO.pmTaskmapId"/><!-- Detail Key -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" >
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
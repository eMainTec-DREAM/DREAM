<%--===========================================================================
응답 목록
author  kim21017
version $Id: rcmPmtaskCndtList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="common.tree.action.TreeAction" %>
<%@ page import="dream.rcm.pmtask.action.RcmPmtaskCndtListAction" %>
<%@ page import="dream.rcm.pmtask.action.RcmPmtaskDetailAction" %>
<%@ page import="dream.rcm.pmtask.action.RcmPmtaskCndtDetailAction" %>
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
		goTabPage("rcmPmtaskCndtDetail");
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	rcmPmtaskCndtListForm.elements['rcmPmtaskCndtListDTO.rcmpmtaskcndtId'].value = "";
    	return sortColumn("rcmPmtaskCndtList", this, rcmPmtaskCndtListForm, "rcmpmtaskcndtId", ind, direction);
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
	var form = document.rcmPmtaskCndtListForm;	
	form.strutsAction.value = '<%=RcmPmtaskCndtListAction.PMTASK_CNDT_LIST_FIND%>'; 

	var url = contextPath + "/rcmPmtaskCndtList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(rcmPmtaskCndtListForm), "rcmpmtaskcndtId", "Y");

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
	var form = document.rcmPmtaskCndtListForm;
	form.elements['rcmPmtaskCndtListDTO.rcmpmtaskcndtId'].value = getValueById(myGrid, selectedId,'rcmpmtaskcndtId');
    
	goCommonTabPage(form, <%= RcmPmtaskCndtDetailAction.PMTASK_CNDT_DETAIL_INIT %>, pageId);
	
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_answerId)
{
	rcmPmtaskCndtListForm.elements['rcmPmtaskCndtListDTO.rcmpmtaskcndtId'].value = _answerId;
	findGridList('ReloadRow');
	rcmPmtaskCndtListForm.elements['rcmPmtaskCndtListDTO.rcmpmtaskcndtId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('rcmPmtaskCndtDetail');
}

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "rcmPmtaskCndtDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	rcmPmtaskCndtListForm.elements['rcmPmtaskCndtListDTO.rcmpmtaskcndtId'].value = "";
	goCommonTabPage(rcmPmtaskCndtListForm, '', pageId);
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
	var delArray = getDeletRows(myGrid, 'ISDELCHECK','rcmpmtaskcndtId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	rcmPmtaskCndtListForm.strutsAction.value = '<%=RcmPmtaskCndtListAction.PMTASK_CNDT_LIST_DELETE%>';
	var url = contextPath + "/rcmPmtaskCndtList.do";
	
    $.post(url,FormQueryString(rcmPmtaskCndtListForm)+delArray , function(_data){
    	afterDelete();
    });
 }

function afterDelete()
{
	goClose('rcmPmtaskCndtDetail', this);
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/rcmPmtaskCndtList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="rcmPmtaskCommonDTO.rcmpmtaskId"/><!-- Key -->
<html:hidden property="rcmPmtaskCommonDTO.rcmlistId"/>
<html:hidden property="rcmPmtaskCommonDTO.rcmfmeaId"/>
<html:hidden property="rcmPmtaskCommonDTO.pmtaskmapId"/>
<html:hidden property="rcmPmtaskCommonDTO.pmtaskmapVal"/>
<html:hidden property="rcmPmtaskCommonDTO.pmtaskmapRsltVal"/>

<html:hidden property="rcmPmtaskCndtListDTO.rcmpmtaskcndtId"/><!-- Detail Key -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" >
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
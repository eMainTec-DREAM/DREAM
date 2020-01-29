<%--===========================================================================
월별단가
author  youngjoo38
version $Id: workPmCheckMonthlyUnitPriceList.jsp,v 1.1 2017/08/28 10:17:27 youngjoo38 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.pm.check.action.WorkPmCheckMonthlyUnitPriceListAction" %>
<%@ page import="dream.work.pm.check.action.WorkPmCheckMonthlyUnitPriceDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 월별단가 -->
<title><bean:message key='LABEL.monthlyUnitPrice'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var myGrid;

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
	        workPmCheckMonthlyUnitPriceListForm.elements['workPmCheckMonthlyUnitPriceListDTO.stdChkPointPriceId'].value = "";
	        return sortColumn("workPmCheckMonthlyUnitPriceList", this, workPmCheckMonthlyUnitPriceListForm, "STDCHKPOINTPRICEID", ind, direction);
	    });
	    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	//페이지id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (workPmCheckMonthlyUnitPriceListForm.elements['workPmCheckCommonDTO.checkPointId'].value == '') return;
	
	var url = contextPath + "/workPmCheckMonthlyUnitPriceList.do";
	
	workPmCheckMonthlyUnitPriceListForm.elements['strutsAction'].value = '<%=WorkPmCheckMonthlyUnitPriceListAction.LIST_FIND%>';
	
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workPmCheckMonthlyUnitPriceListForm), "STDCHKPOINTPRICEID", "Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_stdChkPointPriceId)
{
    workPmCheckMonthlyUnitPriceListForm.elements['workPmCheckMonthlyUnitPriceListDTO.stdChkPointPriceId'].value = _stdChkPointPriceId;
    findGridList('ReloadRow');
    workPmCheckMonthlyUnitPriceListForm.elements['workPmCheckMonthlyUnitPriceListDTO.stdChkPointPriceId'].value = "";
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
	workPmCheckMonthlyUnitPriceListForm.elements['workPmCheckMonthlyUnitPriceListDTO.stdChkPointPriceId'].value = getValueById(myGrid, selectedId,'STDCHKPOINTPRICEID');
	
	goCommonTabPage(workPmCheckMonthlyUnitPriceListForm, <%= WorkPmCheckMonthlyUnitPriceDetailAction.DETAIL_INIT %>, "workPmCheckMonthlyUnitPriceDetail");
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('workPmCheckMonthlyUnitPriceDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    workPmCheckMonthlyUnitPriceListForm.elements['workPmCheckMonthlyUnitPriceListDTO.stdChkPointPriceId'].value = getValueById(myGrid, selectedId,'STDCHKPOINTPRICEID');
    workPmCheckMonthlyUnitPriceListForm.elements['strutsAction'].value = '<%=WorkPmCheckMonthlyUnitPriceDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(workPmCheckMonthlyUnitPriceListForm), 'workPmCheckMonthlyUnitPriceDetail'); 
} 

 /**
  * 생성
  */
function goCreate()
{
 	createValidationCheck(myGrid, "workPmCheckMonthlyUnitPriceDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
 	workPmCheckMonthlyUnitPriceListForm.elements['workPmCheckMonthlyUnitPriceListDTO.stdChkPointPriceId'].value = "";
 	goCommonTabPage(workPmCheckMonthlyUnitPriceListForm, '', pageId);
}
 
  /**
   * 삭제
   */
function goDelete()
{
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'STDCHKPOINTPRICEID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	workPmCheckMonthlyUnitPriceListForm.strutsAction.value = '<%=WorkPmCheckMonthlyUnitPriceListAction.LIST_DELETE%>';
	var url = contextPath + "/workPmCheckMonthlyUnitPriceList.do";
	
	$.post(url,FormQueryString(workPmCheckMonthlyUnitPriceListForm)+delArray , function(_data){
    	afterDelete();
    });
}
  
function afterDelete()
{
	goClose('workPmCheckMonthlyUnitPriceDetail');
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	workPmCheckMonthlyUnitPriceListForm.elements['workPmCheckMonthlyUnitPriceListDTO.stdChkPointPriceId'].value = "";
    excelServerAction("workPmCheckMonthlyUnitPriceList", workPmCheckMonthlyUnitPriceListForm );  
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = workPmCheckMonthlyUnitPriceListForm.elements['workPmCheckMonthlyUnitPriceListDTO.stdChkPointPriceId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workPmCheckMonthlyUnitPriceList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="workPmCheckCommonDTO.checkPointId"/><!-- Key -->
<html:hidden property="workPmCheckMonthlyUnitPriceListDTO.stdChkPointPriceId"/><!-- Key -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
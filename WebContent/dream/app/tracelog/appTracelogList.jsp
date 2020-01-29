<%--===========================================================================
TraceLog List
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.app.tracelog.action.AppTracelogListAction" %>
<%@ page import="dream.app.tracelog.action.AppTracelogDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- TraceLog -->
<title><bean:message key='MENU.SCREENTRACE'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
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
    	appTracelogListForm.elements['appTracelogCommonDTO.scrnTraceLogId'].value = "";
    	return sortColumn("appTracelogList", this, appTracelogListForm, "SCRNTRACELOGID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox", "goSearch"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/appTracelogList.do";
    appTracelogListForm.elements['strutsAction'].value = '<%=AppTracelogListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(appTracelogListForm), "SCRNTRACELOGID","Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_scrnTraceLogId)
{
	appTracelogListForm.elements['appTracelogCommonDTO.scrnTraceLogId'].value = _scrnTraceLogId;
	findGridList('ReloadRow');
	appTracelogListForm.elements['appTracelogCommonDTO.scrnTraceLogId'].value = "";
}

function goSearch()
{
	appTracelogListForm.elements['appTracelogCommonDTO.scrnTraceLogId'].value = "";	// 검색시 Tab 이동Key Clear
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
	appTracelogListForm.elements['appTracelogCommonDTO.scrnTraceLogId'].value =  getValueById(myGrid, selectedId,'SCRNTRACELOGID');  
	goCommonTabPage(appTracelogListForm, <%= AppTracelogDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('appTracelogDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    appTracelogListForm.elements['appTracelogCommonDTO.scrnTraceLogId'].value =  getValueById(myGrid, selectedId,'SCRNTRACELOGID'); 
    appTracelogListForm.elements['strutsAction'].value = '<%=AppTracelogDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(appTracelogListForm), 'appTracelogDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "appTracelogDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	appTracelogListForm.elements['appTracelogCommonDTO.scrnTraceLogId'].value = "";
    goCommonTabPage(appTracelogListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'SCRNTRACELOGID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    appTracelogListForm.strutsAction.value = '<%=AppTracelogListAction.LIST_DELETE%>';
    var url = contextPath + "/appTracelogList.do";
    
    $.post(url,FormQueryString(appTracelogListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('appTracelogDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	appTracelogListForm.elements['appTracelogCommonDTO.scrnTraceLogId'].value = "";
	excelServerAction("appTracelogList", appTracelogListForm );  
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/appTracelogList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="appTracelogCommonDTO.scrnTraceLogId"/><!-- Key -->
<html:hidden property="appTracelogCommonDTO.filterCompNo"/>
<html:hidden property="appTracelogCommonDTO.filterObjectId"/>
<html:hidden property="appTracelogCommonDTO.filterFileName"/>
    
	<div class="section_wrap">
		<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.List"/></div>
			</div>
			<div class="function_box list">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>	
				<div class="fb_group2">
				</div>
			</div>
		</div><!--sheader_box end-->
		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>
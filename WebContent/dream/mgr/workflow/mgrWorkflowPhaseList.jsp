<%--===========================================================================
Workflow Phase List
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.workflow.action.MgrWorkflowPhaseListAction" %>
<%@ page import="dream.mgr.workflow.action.MgrWorkflowPhaseDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 승인Flow 단계 -->
<title><bean:message key='TAB.mgrWorkflowPhaseList'/></title>
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
    	mgrWorkflowPhaseListForm.elements['mgrWorkflowPhaseListDTO.wfphaseId'].value = "";
    	return sortColumn("mgrWorkflowPhaseList", this, mgrWorkflowPhaseListForm, "wfphaseId", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	
	setHeader(myGrid, "gridbox"); // grid, grid id
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/mgrWorkflowPhaseList.do";
    mgrWorkflowPhaseListForm.elements['strutsAction'].value = '<%=MgrWorkflowPhaseListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mgrWorkflowPhaseListForm), "wfphaseId","Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_wfphaseId)
{
	mgrWorkflowPhaseListForm.elements['mgrWorkflowPhaseListDTO.wfphaseId'].value = _wfphaseId;
	findGridList('ReloadRow');
	mgrWorkflowPhaseListForm.elements['mgrWorkflowPhaseListDTO.wfphaseId'].value = "";
}

function goSearch()
{
	mgrWorkflowPhaseListForm.elements['mgrWorkflowPhaseListDTO.wfphaseId'].value = "";	// 검색시 Tab 이동Key Clear
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
	mgrWorkflowPhaseListForm.elements['mgrWorkflowPhaseListDTO.wfphaseId'].value =  getValueById(myGrid, selectedId,'WFPHASEID');
	goCommonTabPage(mgrWorkflowPhaseListForm, <%= MgrWorkflowPhaseDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('mgrWorkflowPhaseDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    mgrWorkflowPhaseListForm.elements['mgrWorkflowPhaseListDTO.wfphaseId'].value =  getValueById(myGrid, selectedId,'WFPHASEID');
    mgrWorkflowPhaseListForm.elements['strutsAction'].value = '<%=MgrWorkflowPhaseDetailAction.DETAIL_INIT%>';
    var pageId  = getValueById(myGrid, selectedId,'PARAM');
    openQuickTabPage(FormQueryString(mgrWorkflowPhaseListForm), 'mgrWorkflowPhaseDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "mgrWorkflowPhaseDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	mgrWorkflowPhaseListForm.elements['mgrWorkflowPhaseListDTO.wfphaseId'].value = "";
	mgrWorkflowPhaseListForm.elements['mgrWorkflowPhaseListDTO.wflistId'].value = mgrWorkflowPhaseListForm.elements['mgrWorkflowCommonDTO.wflistId'].value;
    goCommonTabPage(mgrWorkflowPhaseListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'wfphaseId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    mgrWorkflowPhaseListForm.strutsAction.value = '<%=MgrWorkflowPhaseListAction.LIST_DELETE%>';
    var url = contextPath + "/mgrWorkflowPhaseList.do";
    
    $.post(url,FormQueryString(mgrWorkflowPhaseListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('mgrWorkflowPhaseDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	mgrWorkflowPhaseListForm.elements['mgrWorkflowPhaseListDTO.wfphaseId'].value = "";
	excelServerAction("mgrWorkflowPhaseList", mgrWorkflowPhaseListForm );  
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/mgrWorkflowPhaseList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="mgrWorkflowCommonDTO.wflistId" />

<html:hidden property="mgrWorkflowPhaseListDTO.compNo"/><!-- Key -->
<html:hidden property="mgrWorkflowPhaseListDTO.wfphaseId"/><!-- Key -->
<html:hidden property="mgrWorkflowPhaseListDTO.wflistId"/>
    
		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
		
</html:form> 
</body>
</html>
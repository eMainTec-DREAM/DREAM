<%--===========================================================================
표준값
author  kim21017
version $Id: workPmStdCalibValList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.pm.std.action.WorkPmStdCalibValListAction" %>
<%@ page import="dream.work.pm.std.action.WorkPmStdCalibValDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 표준값 -->
<title><bean:message key='TAB.workPmStdCalibValList'/></title>
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
		workPmStdCalibValListForm.elements['workPmStdCalibValListDTO.pmCalibStdValueId'].value = "";
    	return sortColumn("workPmStdCalibValList", this, workPmStdCalibValListForm, "PMCALIBSTDVALUEID", ind, direction);
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
	if (workPmStdCalibValListForm.elements['workPmStdCalibCommonDTO.pmCalibStdTpId'].value == '') return;
    var url = contextPath + "/workPmStdCalibValList.do";
    workPmStdCalibValListForm.elements['strutsAction'].value = '<%=WorkPmStdCalibValListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workPmStdCalibValListForm), "PMCALIBSTDVALUEID","Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pmCalibStdValueId)
{
	workPmStdCalibValListForm.elements['workPmStdCalibValListDTO.pmCalibStdValueId'].value = _pmCalibStdValueId;
	findGridList('ReloadRow');
	workPmStdCalibValListForm.elements['workPmStdCalibValListDTO.pmCalibStdValueId'].value = "";
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
	workPmStdCalibValListForm.elements['workPmStdCalibValListDTO.pmCalibStdValueId'].value =  getValueById(myGrid, selectedId,'PMCALIBSTDVALUEID');  
	goCommonTabPage(workPmStdCalibValListForm, <%= WorkPmStdCalibValDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('workPmStdCalibValDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    workPmStdCalibValListForm.elements['workPmStdCalibValListDTO.pmCalibStdValueId'].value =  getValueById(myGrid, selectedId,'PMCALIBSTDVALUEID');  
    workPmStdCalibValListForm.elements['strutsAction'].value = '<%=WorkPmStdCalibValDetailAction.DETAIL_INIT %>';
    openQuickTabPage(FormQueryString(workPmStdCalibValListForm), 'workPmStdCalibValDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "workPmStdCalibValDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	workPmStdCalibValListForm.elements['workPmStdCalibValListDTO.pmCalibStdValueId'].value = "";
    goCommonTabPage(workPmStdCalibValListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'PMCALIBSTDVALUEID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    workPmStdCalibValListForm.strutsAction.value = '<%=WorkPmStdCalibValListAction.LIST_DELETE%>';
    var url = contextPath + "/workPmStdCalibValList.do";
    
    $.post(url,FormQueryString(workPmStdCalibValListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('workPmStdCalibValDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	workPmStdCalibValListForm.elements['workPmStdCalibValListDTO.pmCalibStdValueId'].value = "";
	excelServerAction("workPmStdCalibValList", workPmStdCalibValListForm );
// 	excelAction(myGrid);
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = workPmStdCalibValListForm.elements['workPmStdCalibValListDTO.pmCalibStdValueId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workPmStdCalibValList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="workPmStdCalibCommonDTO.pmCalibStdTpId"/><!-- Key -->
<html:hidden property="workPmStdCalibValListDTO.pmCalibStdValueId"/><!-- Key -->
    <!-- searchbox 박스 Line -->

	<div class="article_box" id="listBox">
		<div class="grid_area">
			<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
		</div>
	</div>

</html:form> 
</body>
</html>
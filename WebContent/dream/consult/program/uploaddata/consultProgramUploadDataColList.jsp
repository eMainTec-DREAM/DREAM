<%--===========================================================================
컬럼
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.program.uploaddata.action.ConsultProgramUploadDataColAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 컬럼 -->
<title><bean:message key='LABEL.column'/></title>
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
	        consultProgramUploadDataColForm.elements['consultProgramUploadDataColDTO.excelColId'].value = "";
	        return sortColumn("consultProgramUploadDataColList", this, consultProgramUploadDataColForm, "EXCELCOLID", ind, direction);
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
	if (consultProgramUploadDataColForm.elements['consultProgramUploadDataDTO.excelTabId'].value == '') return;
	
	var url = contextPath + "/consultProgramUploadDataColList.do";
	
	consultProgramUploadDataColForm.elements['strutsAction'].value = '<%=ConsultProgramUploadDataColAction.LIST_FIND%>';
	
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(consultProgramUploadDataColForm), "EXCELCOLID", "Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_excelColId)
{
    consultProgramUploadDataColForm.elements['consultProgramUploadDataColDTO.excelColId'].value = _excelColId;
    findGridList('ReloadRow');
    consultProgramUploadDataColForm.elements['consultProgramUploadDataColDTO.excelColId'].value = "";
}

function goSearch()
{
	consultProgramUploadDataColForm.elements['consultProgramUploadDataColDTO.excelColId'].value = "";
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
	consultProgramUploadDataColForm.elements['consultProgramUploadDataColDTO.excelColId'].value = getValueById(myGrid, selectedId,'EXCELCOLID');
	
	goCommonTabPage(consultProgramUploadDataColForm, <%= ConsultProgramUploadDataColAction.DETAIL_INIT %>, "consultProgramUploadDataColDetail");
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('consultProgramUploadDataColDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    consultProgramUploadDataColForm.elements['consultProgramUploadDataColDTO.excelColId'].value = getValueById(myGrid, selectedId,'EXCELCOLID');
    consultProgramUploadDataColForm.elements['strutsAction'].value = '<%=ConsultProgramUploadDataColAction.DETAIL_INIT%>';
    var pageId  = getValueById(myGrid, selectedId,'PARAM');
    openQuickTabPage(FormQueryString(consultProgramUploadDataColForm), 'consultProgramUploadDataColDetail'); 
} 

 /**
  * 생성
  */
function goCreate()
{
 	createValidationCheck(myGrid, "consultProgramUploadDataColDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
 	consultProgramUploadDataColForm.elements['consultProgramUploadDataColDTO.excelColId'].value = "";
 	goCommonTabPage(consultProgramUploadDataColForm, '', pageId);
}
 
  /**
   * 삭제
   */
function goDelete()
{
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'EXCELCOLID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	consultProgramUploadDataColForm.strutsAction.value = '<%=ConsultProgramUploadDataColAction.LIST_DELETE%>';
	var url = contextPath + "/consultProgramUploadDataColList.do";
	
	$.post(url,FormQueryString(consultProgramUploadDataColForm)+delArray , function(_data){
    	afterDelete();
    });
}
  
function afterDelete()
{
	goClose('consultProgramUploadDataColDetail');
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	consultProgramUploadDataColForm.elements['consultProgramUploadDataColDTO.excelColId'].value = "";
    excelServerAction("consultProgramUploadDataColList", consultProgramUploadDataColForm );  
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/consultProgramUploadDataColList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="consultProgramUploadDataDTO.excelTabId"/><!-- Key -->
<html:hidden property="consultProgramUploadDataColDTO.excelColId"/><!-- Key -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
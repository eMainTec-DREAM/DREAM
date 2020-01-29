<%--===========================================================================
엑셀 참조데이타
author  ghlee
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.program.uploaddata.action.ConsultProgramUploadDataScriptAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 컬럼 -->
<title><bean:message key='PAGE.consultProgramUploadDataScriptList'/></title>
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
	
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
        goOpen();
    });
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
        consultProgramUploadDataScriptForm.elements['consultProgramUploadDataScriptDTO.excelExScriptId'].value = "";
        return sortColumn("consultProgramUploadDataScriptList", this, consultProgramUploadDataScriptForm, "EXCELEXSCRIPTID", ind, direction);
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
	if (consultProgramUploadDataScriptForm.elements['consultProgramUploadDataDTO.excelTabId'].value == '') return;
	
	var url = contextPath + "/consultProgramUploadDataScriptList.do";
	
	consultProgramUploadDataScriptForm.elements['strutsAction'].value = '<%=ConsultProgramUploadDataScriptAction.LIST_FIND%>';
	
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(consultProgramUploadDataScriptForm), "EXCELEXSCRIPTID", "Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_excelExScriptId)
{
    consultProgramUploadDataScriptForm.elements['consultProgramUploadDataScriptDTO.excelExScriptId'].value = _excelExScriptId;
    findGridList('ReloadRow');
    consultProgramUploadDataScriptForm.elements['consultProgramUploadDataScriptDTO.excelExScriptId'].value = "";
}

function goSearch()
{
	consultProgramUploadDataScriptForm.elements['consultProgramUploadDataScriptDTO.excelExScriptId'].value = "";
	
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
	consultProgramUploadDataScriptForm.elements['consultProgramUploadDataScriptDTO.excelExScriptId'].value = getValueById(myGrid, selectedId,'EXCELEXSCRIPTID');
	
	goCommonTabPage(consultProgramUploadDataScriptForm, <%= ConsultProgramUploadDataScriptAction.DETAIL_INIT %>, "consultProgramUploadDataScriptDetail");
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('consultProgramUploadDataScriptDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    consultProgramUploadDataScriptForm.elements['consultProgramUploadDataScriptDTO.excelExScriptId'].value = getValueById(myGrid, selectedId,'EXCELEXSCRIPTID');
    consultProgramUploadDataScriptForm.elements['strutsAction'].value = '<%=ConsultProgramUploadDataScriptAction.DETAIL_INIT%>';
    var pageId  = getValueById(myGrid, selectedId,'PARAM');
    openQuickTabPage(FormQueryString(consultProgramUploadDataScriptForm), 'consultProgramUploadDataScriptDetail'); 
} 

 /**
  * 생성
  */
function goCreate()
{
 	createValidationCheck(myGrid, "consultProgramUploadDataScriptDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
 	consultProgramUploadDataScriptForm.elements['consultProgramUploadDataScriptDTO.excelExScriptId'].value = "";
 	goCommonTabPage(consultProgramUploadDataScriptForm, '', pageId);
}
 
  /**
   * 삭제
   */
function goDelete()
{
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'EXCELEXSCRIPTID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	consultProgramUploadDataScriptForm.strutsAction.value = '<%=ConsultProgramUploadDataScriptAction.LIST_DELETE%>';
	var url = contextPath + "/consultProgramUploadDataScriptList.do";
	
	$.post(url,FormQueryString(consultProgramUploadDataScriptForm)+delArray , function(_data){
    	afterDelete();
    });
}
  
function afterDelete()
{
	goClose('consultProgramUploadDataScriptDetail');
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	consultProgramUploadDataScriptForm.elements['consultProgramUploadDataScriptDTO.excelExScriptId'].value = "";
    excelServerAction("consultProgramUploadDataScriptList", consultProgramUploadDataScriptForm );  
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/consultProgramUploadDataScriptList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="consultProgramUploadDataDTO.excelTabId"/>
<html:hidden property="consultProgramUploadDataScriptDTO.excelExScriptId"/><!-- Key -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
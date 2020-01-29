<%--===========================================================================
설치 SW
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.list.action.AssetListITSWListAction" %>
<%@ page import="dream.asset.list.action.AssetListITSWDetailAction" %>
<%@ page import="dream.asset.list.action.MaEqMstrDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 설치 SW -->
<title><bean:message key='TAB.assetListITSWList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var myGrid;

function loadPage() 
{
	assetListITSWListForm.elements['assetListITSWListDTO.itEqId'].value 
		=  assetListITSWListForm.elements['maEqMstrDetailDTO.equipId'].value;
	
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
	        assetListITSWListForm.elements['assetListITSWListDTO.eqItInstSwId'].value = "";
	        return sortColumn("assetListITSWList", this, assetListITSWListForm, "EQITINSTSWID", ind, direction);
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
	if (assetListITSWListForm.elements['assetListITSWListDTO.itEqId'].value == '') return;
	
	var url = contextPath + "/assetListITSWList.do";
	
	assetListITSWListForm.elements['strutsAction'].value = '<%=AssetListITSWListAction.LIST_FIND%>';
	
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(assetListITSWListForm), "EQITINSTSWID", "Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_eqItInstSwId)
{
    assetListITSWListForm.elements['assetListITSWListDTO.eqItInstSwId'].value = _eqItInstSwId;
    findGridList('ReloadRow');
    assetListITSWListForm.elements['assetListITSWListDTO.eqItInstSwId'].value = "";
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
	assetListITSWListForm.elements['assetListITSWListDTO.eqItInstSwId'].value = getValueById(myGrid, selectedId,'EQITINSTSWID');
    assetListITSWListForm.elements['assetListITSWDetailDTO.eqItInstSwId'].value = getValueById(myGrid, selectedId,'EQITINSTSWID');

	goCommonTabPage(assetListITSWListForm, <%= AssetListITSWDetailAction.DETAIL_INIT %>, "assetListITSWDetail");
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('assetListITSWDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    assetListITSWListForm.elements['assetListITSWListDTO.eqItInstSwId'].value = getValueById(myGrid, selectedId,'EQITINSTSWID');
    assetListITSWListForm.elements['assetListITSWDetailDTO.eqItInstSwId'].value = getValueById(myGrid, selectedId,'EQITINSTSWID');
    assetListITSWListForm.elements['strutsAction'].value = '<%=AssetListITSWDetailAction.DETAIL_INIT%>';
    var pageId  = getValueById(myGrid, selectedId,'PARAM');
    openQuickTabPage(FormQueryString(assetListITSWListForm), 'assetListITSWDetail'); 
} 

 /**
  * 생성
  */
function goCreate()
{
 	createValidationCheck(myGrid, "assetListITSWDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
 	assetListITSWListForm.elements['assetListITSWListDTO.eqItInstSwId'].value = "";
 	goCommonTabPage(assetListITSWListForm, '', pageId);
}
 
  /**
   * 삭제
   */
function goDelete()
{
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'EQITINSTSWID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	assetListITSWListForm.strutsAction.value = '<%=AssetListITSWListAction.LIST_DELETE%>';
	var url = contextPath + "/assetListITSWList.do";
	
	$.post(url,FormQueryString(assetListITSWListForm)+delArray , function(_data){
    	afterDelete();
    });
}
  
function afterDelete()
{
	goClose('assetListITSWDetail');
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	assetListITSWListForm.elements['assetListITSWListDTO.eqItInstSwId'].value = "";
    excelServerAction("assetListITSWList", assetListITSWListForm );  
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assetListITSWList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maEqMstrDetailDTO.equipId"/><!-- Key -->
<html:hidden property="assetListITSWListDTO.itEqId"/><!-- Key -->
<html:hidden property="assetListITSWListDTO.eqItInstSwId"/><!-- Key -->
<html:hidden property="assetListITSWDetailDTO.eqItInstSwId"/><!-- Key -->
    <!-- searchbox 박스 Line -->
    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>
</html:form> 
</body>
</html>
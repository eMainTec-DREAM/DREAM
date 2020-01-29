<%--===========================================================================
설비운용기간사용예상수량목록
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.list.action.AssetListPartOpQtyAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 설비운용기간사용예상수량목록 -->
<title><bean:message key='PAGE.assetListPartOpQtyList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

function loadPage() 
{
    assetListPartOpQtyForm.elements['assetListPartOpQtyDTO.eqPartId'].value = searchPage("maEqMstrPartDetail").M$('maEqMstrPartDetailDTO.eqPartId').value;
    
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
    	assetListPartOpQtyForm.elements['assetListPartOpQtyDTO.eqPartOpQtyId'].value = "";
    	return sortColumn("assetListPartOpQtyList", this, assetListPartOpQtyForm, "EQPARTOPQTYID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/assetListPartOpQtyList.do";
    assetListPartOpQtyForm.elements['strutsAction'].value = '<%=AssetListPartOpQtyAction.FIND_LIST%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(assetListPartOpQtyForm), "EQPARTOPQTYID", "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_eqPartOpQtyId)
{
	assetListPartOpQtyForm.elements['assetListPartOpQtyDTO.eqPartOpQtyId'].value = _eqPartOpQtyId;
	findGridList('ReloadRow');
	assetListPartOpQtyForm.elements['assetListPartOpQtyDTO.eqPartOpQtyId'].value = "";
}

function goSearch()
{
	assetListPartOpQtyForm.elements['assetListPartOpQtyDTO.eqPartOpQtyId'].value = "";
	findGridList('Search');
}

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "assetListPartOpQtyDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	assetListPartOpQtyForm.elements['assetListPartOpQtyDTO.eqPartOpQtyId'].value = "";
	goCommonTabPage(assetListPartOpQtyForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'EQPARTOPQTYID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    assetListPartOpQtyForm.strutsAction.value = "<%=AssetListPartOpQtyAction.DELETE_LIST%>";
    var url = contextPath + "/assetListPartOpQtyList.do";
    
    $.post(url,FormQueryString(assetListPartOpQtyForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('assetListPartOpQtyDetail');
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
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
	assetListPartOpQtyForm.elements['assetListPartOpQtyDTO.eqPartOpQtyId'].value =  getValueById(myGrid, selectedId,'EQPARTOPQTYID');
	goCommonTabPage(assetListPartOpQtyForm, <%= AssetListPartOpQtyAction.INIT_DETAIL %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('assetListPartOpQtyDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    assetListPartOpQtyForm.elements['assetListPartOpQtyDTO.eqPartOpQtyId'].value =  getValueById(myGrid, selectedId,'EQPARTOPQTYID');
	assetListPartOpQtyForm.elements['strutsAction'].value = '<%=AssetListPartOpQtyAction.INIT_DETAIL%>';
    openQuickTabPage(FormQueryString(assetListPartOpQtyForm), 'assetListPartOpQtyDetail'); 
}

/**
 * Excel Export
 */
function goExcel()
{
	assetListPartOpQtyForm.elements['assetListPartOpQtyDTO.eqPartOpQtyId'].value = "";
	excelServerAction("assetListPartOpQtyList", assetListPartOpQtyForm );  
}

//-->
</script>


</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assetListPartOpQtyList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="assetListPartOpQtyDTO.eqPartId"/><!-- Key -->
<html:hidden property="assetListPartOpQtyDTO.eqPartOpQtyId"/>

    <div class="section_wrap">
        <div class="article_box" id="listBox">
            <div class="grid_area">
                <div id="gridbox" style="width:100%; height:500px; background-color:white;"></div>
            </div>
        </div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>
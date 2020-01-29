<%--===========================================================================
부품창고 보관위치 List
author  cjscjs9
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.list.action.PartListSafQtyListAction" %>
<%@ page import="dream.part.list.action.PartListSafQtyDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 담당자 -->
<title><bean:message key='LABEL.manager'/></title>
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
 		partListSafQtyListForm.elements['partListSafQtyListDTO.partId'].value = "";
        return sortColumn("partListSafQtyList", this, partListSafQtyListForm, "partId", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	
	setHeader(myGrid, "gridbox"); // grid, grid id
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/partListSafQtyList.do";
    partListSafQtyListForm.elements['strutsAction'].value = '<%=PartListSafQtyListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(partListSafQtyListForm), "partId","Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_partId)
{
	partListSafQtyListForm.elements['partListSafQtyListDTO.partId'].value = _partId;
	findGridList('ReloadRow');
	partListSafQtyListForm.elements['partListSafQtyListDTO.partId'].value = "";
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
    /* partListSafQtyListForm.elements['partListSafQtyListDTO.wcodeId'].value = getValueById(myGrid, selectedId, 'wcodeId'); */
    partListSafQtyListForm.elements['partListSafQtyListDTO.wcodeId'].value = partListSafQtyListForm.elements['maPtMstrCommonDTO.wcodeId'].value;
    partListSafQtyListForm.elements['partListSafQtyListDTO.partId'].value = partListSafQtyListForm.elements['maPtMstrCommonDTO.partId'].value;
	goCommonTabPage(partListSafQtyListForm, <%= PartListSafQtyDetailAction.DETAIL_INIT %>, pageId);
	
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('partListSafQtyDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    /* partListSafQtyListForm.elements['partListSafQtyListDTO.wcodeId'].value = getValueById(myGrid, selectedId, 'wcodeId'); */
    partListSafQtyListForm.elements['partListSafQtyListDTO.wcodeId'].value = partListSafQtyListForm.elements['maPtMstrCommonDTO.wcodeId'].value;
    partListSafQtyListForm.elements['partListSafQtyListDTO.partId'].value = partListSafQtyListForm.elements['maPtMstrCommonDTO.partId'].value;
    partListSafQtyListForm.elements['strutsAction'].value = '<%=PartListSafQtyDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(partListSafQtyListForm), 'partListSafQtyDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "partListSafQtyDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	partListSafQtyListForm.elements['partListSafQtyListDTO.partId'].value = "";
    goCommonTabPage(partListSafQtyListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'PARTID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    partListSafQtyListForm.strutsAction.value = '<%=PartListSafQtyListAction.LIST_DELETE%>';
    var url = contextPath + "/partListSafQtyList.do";
    
    $.post(url,FormQueryString(partListSafQtyListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('partListSafQtyDetail', this);
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	partListSafQtyListForm.elements['partListSafQtyListDTO.partId'].value = "";
	excelServerAction("partListSafQtyList", partListSafQtyListForm);

}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/partListSafQtyList.do">
		<html:hidden property="strutsAction"/>
 		<html:hidden property="maPtMstrCommonDTO.partId" />
 		<html:hidden property="maPtMstrCommonDTO.wcodeId" />
		
		<html:hidden property="partListSafQtyListDTO.wcodeId"/>
		<html:hidden property="partListSafQtyListDTO.partId"/>
		
		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
		
</html:form> 
</body>
</html>
<%--===========================================================================
부품창고 보관위치 List
author  cjscjs9
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.list.action.PartListBinListAction" %>
<%@ page import="dream.part.list.action.PartListBinDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 담당자 -->
<title><bean:message key='LABEL.binNoTxt'/></title>
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
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
 	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
 		partListBinListForm.elements['partListBinListDTO.ptwhBinNoId'].value = "";
        return sortColumn("partListBinList", this, partListBinListForm, "ptwhBinNoId", ind, direction);
    });
 
	setHeader(myGrid, "gridbox"); // grid, grid id
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/partListBinList.do";
    partListBinListForm.elements['strutsAction'].value = '<%=PartListBinListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(partListBinListForm), "ptwhBinNoId","Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_ptwhBinNoId)
{
	partListBinListForm.elements['partListBinListDTO.ptwhBinNoId'].value = _ptwhBinNoId;
	findGridList('ReloadRow');
	partListBinListForm.elements['partListBinListDTO.ptwhBinNoId'].value = "";
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
	partListBinListForm.elements['partListBinListDTO.partId'].value = partListBinListForm.elements['maPtMstrCommonDTO.partId'].value;
    partListBinListForm.elements['partListBinListDTO.ptwhBinNoId'].value =  getValueById(myGrid, selectedId,'ptwhBinNoId');
	goCommonTabPage(partListBinListForm, <%= PartListBinDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('partListBinDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    partListBinListForm.elements['partListBinListDTO.partId'].value = partListBinListForm.elements['maPtMstrCommonDTO.partId'].value;
    partListBinListForm.elements['partListBinListDTO.ptBinListId'].value = getValueById(myGrid, selectedId, 'ptBinListId');
    partListBinListForm.elements['strutsAction'].value = '<%=PartListBinDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(partListBinListForm), 'partListBinDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "partListBinDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	partListBinListForm.elements['partListBinListDTO.ptwhBinNoId'].value = "";
    goCommonTabPage(partListBinListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'ptwhBinNoId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    partListBinListForm.strutsAction.value = '<%=PartListBinListAction.LIST_DELETE%>';
    var url = contextPath + "/partListBinList.do";
    
    $.post(url,FormQueryString(partListBinListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('partListBinDetail', this);
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	partListBinListForm.elements['partListBinListDTO.ptwhBinNoId'].value = "";
	excelServerAction("partListBinList", partListBinListForm);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/partListBinList.do">
		<html:hidden property="strutsAction"/>
		 <html:hidden property="maPtMstrCommonDTO.partId" />
		
		<html:hidden property="partListBinListDTO.wcodeId"/>
		<html:hidden property="partListBinListDTO.partId"/>
		<html:hidden property="partListBinListDTO.ptwhBinNoId"/>
		<html:hidden property="partListBinListDTO.ptBinListId"/>
		
		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
		
</html:form> 
</body>
</html>
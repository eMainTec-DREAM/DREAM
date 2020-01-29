<%--===========================================================================
데이터 테이블 detail - code 목록
author  kim21017
version $Id: maTableColList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="common.tree.action.TreeAction" %>
<%@ page import="dream.consult.program.table.action.MaTableColListAction" %>
<%@ page import="dream.consult.program.table.action.MaTableDetailAction" %>
<%@ page import="dream.consult.program.table.action.MaTableColDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 화면 -->
<title><bean:message key='LABEL.code'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="adminPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	//데이터 테이블 분류 Grid
	initGrid();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("maTableColDetail");
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	maTableColListForm.elements['maTableColListDTO.tableDId'].value = "";
        return sortColumn("maTableColList", this, maTableColListForm, "TABLEDID", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	findGridList('Search');
}

/**
 * gird find
 */
function findGridList(sheetAction)
{
	//listType 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (maTableColListForm.elements['maTableCommonDTO.tableMId'].value == '') return;
	
	var form = document.maTableColListForm;	
	form.strutsAction.value = '<%=MaTableColListAction.LISTTYPE_CODE_LIST_FIND%>';

	var url = contextPath + "/maTableColList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maTableColListForm), "TABCOLID", "Y");

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
	var form = document.maTableColListForm;
	form.elements['maTableColListDTO.tableDId'].value = getValueById(myGrid, selectedId,'tabColId');
    
	goCommonTabPage(form, <%= MaTableColDetailAction.LISTTYPE_CODE_DETAIL_INIT %>, "maTableColDetail");
	
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_tableDId)
{
	maTableColListForm.elements['maTableColListDTO.tableDId'].value = _tableDId;
	findGridList('ReloadRow');
	maTableColListForm.elements['maTableColListDTO.tableDId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maTableColDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maTableColListForm.elements['maTableColListDTO.tableDId'].value = getValueById(myGrid, selectedId,'tabColId');  
    maTableColListForm.elements['strutsAction'].value = '<%=MaTableColDetailAction.LISTTYPE_CODE_DETAIL_INIT %>';
    openQuickTabPage(FormQueryString(maTableColListForm), 'maTableColDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maTableColDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maTableColListForm.elements['maTableColListDTO.tableDId'].value = "";
	goCommonTabPage(maTableColListForm, '', pageId);
}
 

/**
 * Excel Export
 */
function goExcel()
{
	maTableColListForm.elements['maTableColListDTO.tableDId'].value = "";
    excelServerAction("maTableColList",maTableColListForm);
}

/**
 * 삭제
 */
 function goDelete()
 {
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'TABLEID', 'TABCOLID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maTableColListForm.strutsAction.value = '<%=MaTableColListAction.LISTTYPE_CODE_LIST_DELETE%>';
	var url = contextPath + "/maTableColList.do";
	
    $.post(url,FormQueryString(maTableColListForm)+delArray , function(_data){
    	afterDelete();
    });
 }

function afterDelete(){
	goClose('maTableColDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maTableColList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maTableCommonDTO.tableMId"/><!-- Key -->
<html:hidden property="maTableColListDTO.tableDId"/><!-- Detail Key -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" >
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
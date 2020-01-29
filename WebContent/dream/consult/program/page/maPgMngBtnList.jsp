<%--===========================================================================
화변별 버튼 목록
author  kim21017
version $Id: maPgMngBtnList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.program.page.action.MaPgMngBtnListAction" %>
<%@ page import="dream.consult.program.page.action.MaPgMngBtnDetailAction" %>
<%@ page import="dream.consult.program.page.action.MaPgMngDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 화면 별 버튼 -->
<title><bean:message key='TAB.maPgMngBtnList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="adminPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	initGrid();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("maPgMngBtnDetail");
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox", "goSearch"); // grid, grid id, callBack
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
    findGridList('Search');   
}

function findGridList(sheetAction)
{
	//페이지id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (maPgMngBtnListForm.elements['maPgMngCommonDTO.pageId'].value == '') return;
	
	var form = document.maPgMngBtnListForm;	
	form.strutsAction.value = '<%=MaPgMngBtnListAction.PG_BTN_LIST_FIND %>';
	
	var url = contextPath + "/maPgMngBtnList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPgMngBtnListForm), "PGBTNID");

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
	maPgMngBtnListForm.elements['maPgMngBtnListDTO.pgBtnId'].value = getValueById(myGrid, selectedId,'PGBTNID');
	
	goCommonTabPage(maPgMngBtnListForm, <%= MaPgMngBtnDetailAction.PG_BTN_DETAIL_INIT %>, "maPgMngBtnDetail");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pgBtnId)
{
	maPgMngBtnListForm.elements['maPgMngBtnListDTO.pgBtnId'].value = _pgBtnId;
	findGridList('ReloadRow');
	maPgMngBtnListForm.elements['maPgMngBtnListDTO.pgBtnId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maPgMngBtnDetail');
}

 /**
  * 생성
  */
function goCreate()
{
 	createValidationCheck(myGrid, "maPgMngBtnDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
 	maPgMngBtnListForm.elements['maPgMngBtnListDTO.pgBtnId'].value = "";
 	goCommonTabPage(maPgMngBtnListForm, '', pageId);
}
 
/**
 * Excel Export
 */
function goExcel()
{
    excelAction(myGrid);
}

  /**
   * 삭제
   */
function goDelete()
{
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'PGBTNID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maPgMngBtnListForm.strutsAction.value = '<%=MaPgMngBtnListAction.PG_BTN_LIST_DELETE%>';
	var url = contextPath + "/maPgMngBtnList.do";
	
	$.post(url,FormQueryString(maPgMngBtnListForm)+delArray , function(_data){
    	afterDelete();
    });
}
  
function afterDelete()
{
	goClose('maPgMngBtnDetail');
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPgMngBtnList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPgMngCommonDTO.pageId"/><!-- Key -->
<html:hidden property="maPgMngBtnListDTO.pgBtnId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
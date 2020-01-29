<%--===========================================================================
화변별 연결기능 목록
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.program.page.action.ConsultPgmPgLinkedFuncListAction" %>
<%@ page import="dream.consult.program.page.action.ConsultPgmPgLinkedFuncDetailAction" %>
<%@ page import="dream.consult.program.page.action.MaPgMngDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 화면 별 연결기능 -->
<title><bean:message key='TAB.consultPgmPgLinkedFuncList'/></title>
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
		goTabPage("consultPgmPgLinkedFuncDetail");
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

function findGridList(sheetAction)
{
	//페이지id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (consultPgmPgLinkedFuncListForm.elements['maPgMngCommonDTO.pageId'].value == '') return;
	
	var form = document.consultPgmPgLinkedFuncListForm;	
	form.strutsAction.value = '<%=ConsultPgmPgLinkedFuncListAction.PG_FIELD_LIST_FIND %>';
	
	var url = contextPath + "/consultPgmPgLinkedFuncList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(consultPgmPgLinkedFuncListForm), "PGLINKEDFUNCID");

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
	consultPgmPgLinkedFuncListForm.elements['consultPgmPgLinkedFuncListDTO.pgLinkedFuncId'].value = getValueById(myGrid, selectedId,'PGLINKEDFUNCID');
	
	goCommonTabPage(consultPgmPgLinkedFuncListForm, <%= ConsultPgmPgLinkedFuncDetailAction.PG_FIELD_DETAIL_INIT %>, "consultPgmPgLinkedFuncDetail");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pgLinkedFuncId)
{
	consultPgmPgLinkedFuncListForm.elements['consultPgmPgLinkedFuncListDTO.pgLinkedFuncId'].value = _pgLinkedFuncId;
	findGridList('ReloadRow');
	consultPgmPgLinkedFuncListForm.elements['consultPgmPgLinkedFuncListDTO.pgLinkedFuncId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('consultPgmPgLinkedFuncDetail');
}

 /**
  * 생성
  */
function goCreate()
{
 	createValidationCheck(myGrid, "consultPgmPgLinkedFuncDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
 	consultPgmPgLinkedFuncListForm.elements['consultPgmPgLinkedFuncListDTO.pgLinkedFuncId'].value = "";
 	goCommonTabPage(consultPgmPgLinkedFuncListForm, '', pageId);
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
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'PGLINKEDFUNCID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	consultPgmPgLinkedFuncListForm.strutsAction.value = '<%=ConsultPgmPgLinkedFuncListAction.PG_FIELD_LIST_DELETE%>';
	var url = contextPath + "/consultPgmPgLinkedFuncList.do";
	
	$.post(url,FormQueryString(consultPgmPgLinkedFuncListForm)+delArray , function(_data){
    	afterDelete();
    });
}
  
function afterDelete()
{
	goClose('consultPgmPgLinkedFuncDetail');
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/consultPgmPgLinkedFuncList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPgMngCommonDTO.pageId"/><!-- Key -->
<html:hidden property="consultPgmPgLinkedFuncListDTO.pgLinkedFuncId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
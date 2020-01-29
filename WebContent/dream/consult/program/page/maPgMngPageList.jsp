<%--===========================================================================
화변별 탭페이지 목록
author  kim21017
version $Id: maPgMngPageList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.program.page.action.MaPgMngPageListAction" %>
<%@ page import="dream.consult.program.page.action.MaPgMngPageDetailAction" %>
<%@ page import="dream.consult.program.page.action.MaPgMngDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 화면 별 탭페이지 -->
<title><bean:message key='LABEL.page'/></title>
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
		goTabPage("maPgMngPageDetail");
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
	myGrid.init();

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
	if (maPgMngPageListForm.elements['maPgMngCommonDTO.pageId'].value == '') return;
	
	var form = document.maPgMngPageListForm;	
	form.strutsAction.value = '<%=MaPgMngPageListAction.PG_PAGE_LIST_FIND %>';
	
	var url = contextPath + "/maPgMngPageList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPgMngPageListForm), "PGPAGEID");

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
	maPgMngPageListForm.elements['maPgMngPageListDTO.pgPageId'].value = getValueById(myGrid, selectedId,'PGPAGEID');
	
	goCommonTabPage(maPgMngPageListForm, <%= MaPgMngPageDetailAction.PG_PAGE_DETAIL_INIT %>, "maPgMngPageDetail");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pgPageId)
{
	maPgMngPageListForm.elements['maPgMngPageListDTO.pgPageId'].value = _pgPageId;
	findGridList('ReloadRow');
	maPgMngPageListForm.elements['maPgMngPageListDTO.pgPageId'].value = "";
}

/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maPgMngPageDetail');
}

 /**
  * 생성
  */
function goCreate()
{
 	createValidationCheck(myGrid, "maPgMngPageDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
 	maPgMngPageListForm.elements['maPgMngPageListDTO.pgPageId'].value = "";
 	goCommonTabPage(maPgMngPageListForm, '', pageId);
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
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'PGPAGEID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maPgMngPageListForm.strutsAction.value = '<%=MaPgMngPageListAction.PG_PAGE_LIST_DELETE%>';
	var url = contextPath + "/maPgMngPageList.do";
	
	$.post(url,FormQueryString(maPgMngPageListForm)+delArray , function(_data){
    	afterDelete();
    });
}
  
function afterDelete()
{
	goClose('maPgMngPageDetail');
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPgMngPageList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPgMngCommonDTO.pageId"/><!-- Key -->
<html:hidden property="maPgMngPageListDTO.pgPageId"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box" id="listBox">
		<div class="grid_area">
			<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
		</div>
	</div>
</html:form> 
</body>
</html>
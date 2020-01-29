<%--===========================================================================
목록
author  kim21017
version $Id: maPgMngGrdList.jsp,v 1.1 2015/12/03 01:45:27 jung7126 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.program.page.action.MaGrdMngListAction" %>
<%@ page import="dream.consult.program.page.action.MaGrdMngDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 화면컬럼정의 -->
<title><bean:message key='MENU.PGGRD'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="consultPage">
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
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	if (maGrdMngListForm.elements['maPgMngCommonDTO.pageId'].value == '') return;
	
    var url = contextPath + "/maGrdMngList.do";

    maGrdMngListForm.elements['strutsAction'].value = '<%=MaGrdMngListAction.GRD_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maGrdMngListForm), "PGGRIDID");

}


/**
 * 검색 클릭시 호출
 */
function goSearch()
{
// 	maGrdMngListForm.elements['maGrdMngCommonDTO.pgGridId'].value = "";	// 검색시 Tab 이동Key Clear
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
    maGrdMngListForm.elements['maGrdMngCommonDTO.pgGridId'].value = getValueById(myGrid, selectedId, 'pgGridId');
    goCommonTabPage(maGrdMngListForm, <%= MaGrdMngDetailAction.GRD_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pgGridId)
{
	maGrdMngListForm.elements['maGrdMngCommonDTO.pgGridId'].value = _pgGridId;
	findGridList('ReloadRow');
	maGrdMngListForm.elements['maGrdMngCommonDTO.pgGridId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maPgMngGrdDetail');	
}

 /**
  * 생성
  */
function goCreate()
{
	createValidationCheck(myGrid, "maPgMngGrdDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maGrdMngListForm.elements['maGrdMngCommonDTO.pgGridId'].value = "";
	goCommonTabPage(maGrdMngListForm, '', pageId);
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
	var delArray = getDeletRows(myGrid, "isDelCheck", 'pgGridId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maGrdMngListForm.strutsAction.value = '<%=MaGrdMngListAction.GRD_LIST_DELETE%>';
	var url = contextPath + "/maGrdMngList.do";
	$.post(url,FormQueryString(maGrdMngListForm)+delArray , function(_data){
    	afterDelete();
    });
}
 
function afterDelete()
{
	goClose('maPgMngGrdDetail',this);
	// 	goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}
  
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPgMngGrdList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPgMngCommonDTO.pageId"/><!-- Key -->
<html:hidden property="maGrdMngCommonDTO.pgGridId"/><!-- Key -->
	<!-- searchbox 박스 Line -->
	<div class="article_box" id="listBox">
		<div class="grid_area">
			<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
		</div>
	</div>
</html:form> 
</body>
</html>
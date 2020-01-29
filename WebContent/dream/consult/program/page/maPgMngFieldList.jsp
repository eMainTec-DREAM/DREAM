<%--===========================================================================
화변별 필드 목록
author  kim21017
version $Id: maPgMngFieldList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.program.page.action.MaPgMngFieldListAction" %>
<%@ page import="dream.consult.program.page.action.MaPgMngFieldDetailAction" %>
<%@ page import="dream.consult.program.page.action.MaPgMngDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 화면 별 필드 -->
<title><bean:message key='TAB.maPgMngFieldList'/></title>
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
		goTabPage("maPgMngFieldDetail");
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
	if (maPgMngFieldListForm.elements['maPgMngCommonDTO.pageId'].value == '') return;
	
	var form = document.maPgMngFieldListForm;	
	form.strutsAction.value = '<%=MaPgMngFieldListAction.PG_FIELD_LIST_FIND %>';
	
	var url = contextPath + "/maPgMngFieldList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPgMngFieldListForm), "PGFIELDID");

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
	maPgMngFieldListForm.elements['maPgMngFieldListDTO.pgFieldId'].value = getValueById(myGrid, selectedId,'PGFIELDID');
	
	goCommonTabPage(maPgMngFieldListForm, <%= MaPgMngFieldDetailAction.PG_FIELD_DETAIL_INIT %>, "maPgMngFieldDetail");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pgFieldId)
{
	maPgMngFieldListForm.elements['maPgMngFieldListDTO.pgFieldId'].value = _pgFieldId;
	findGridList('ReloadRow');
	maPgMngFieldListForm.elements['maPgMngFieldListDTO.pgFieldId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maPgMngFieldDetail');
}

 /**
  * 생성
  */
function goCreate()
{
 	createValidationCheck(myGrid, "maPgMngFieldDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
 	maPgMngFieldListForm.elements['maPgMngFieldListDTO.pgFieldId'].value = "";
 	goCommonTabPage(maPgMngFieldListForm, '', pageId);
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
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'PGFIELDID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maPgMngFieldListForm.strutsAction.value = '<%=MaPgMngFieldListAction.PG_FIELD_LIST_DELETE%>';
	var url = contextPath + "/maPgMngFieldList.do";
	
	$.post(url,FormQueryString(maPgMngFieldListForm)+delArray , function(_data){
    	afterDelete();
    });
}
  
function afterDelete()
{
	goClose('maPgMngFieldDetail');
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPgMngFieldList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPgMngCommonDTO.pageId"/><!-- Key -->
<html:hidden property="maPgMngFieldListDTO.pgFieldId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
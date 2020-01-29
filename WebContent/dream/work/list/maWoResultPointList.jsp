<%--===========================================================================
작업결과 검사항목
author  kim21017
version $Id: maWoResultPointList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.list.action.MaWoResultPointListAction" %>
<%@ page import="dream.work.list.action.MaWoResultPointDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 검사항목 -->
<title><bean:message key='TAB.maWoResultPointList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
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
		goTabPage("maWoResultPointDetail");
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
    findGridList('Search', "WOPOINTID");   
}

function findGridList(sheetAction , keyCol)
{
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (maWoResultPointListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value == '') return;
	
	var form = document.maWoResultPointListForm;	
	form.strutsAction.value = '<%=MaWoResultPointListAction.WO_RESULT_POINT_LIST_FIND %>';
	
	var url = contextPath + "/maWoResultPointList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maWoResultPointListForm), keyCol);

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
	var form = document.maWoResultPointListForm;
	 
	form.elements['maWoResultPointListDTO.woPointId'].value = getValueById(myGrid, selectedId,'WOPOINTID');
	form.elements['maWoResultPointListDTO.pmPointId'].value = getValueById(myGrid, selectedId,'PMPOINTID');
	
	goCommonTabPage(form, <%= MaWoResultPointDetailAction.WO_RESULT_POINT_DETAIL_INIT %>, pageId);
}


/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('maWoResultPointDetail');
}

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
  	excelAction(myGrid);
  } 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "maWoResultPointDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	maWoResultPointListForm.elements['maWoResultPointListDTO.woPointId'].value = "";
	goCommonTabPage(maWoResultPointListForm, '', pageId);
 }

 /** 
  * 수정된 그리드 1건을 다시 조회한다.
  */
 function findGridRow(_woPointId, _pmPointId)
 {
	maWoResultPointListForm.elements['maWoResultPointListDTO.woPointId'].value = _woPointId;
	maWoResultPointListForm.elements['maWoResultPointListDTO.pmPointId'].value = _pmPointId;
 	findGridList('ReloadRow', "PMPOINTID");
 	maWoResultPointListForm.elements['maWoResultPointListDTO.woPointId'].value = "";
 	maWoResultPointListForm.elements['maWoResultPointListDTO.pmPointId'].value = "";
 	
 	goReloadTabPageAction("maWoResultPointDetail", _woPointId, _pmPointId);
 }
 
 function goReloadTabPageAction(pageId, _woPointId, _pmPointId)
 {
 	var form = document.maWoResultPointListForm;
 	
 	form.elements['workPmiPointListDTO.woPointId'].value = _woPointId;
 	form.elements['workPmiPointListDTO.pmPointId'].value = _pmPointId;
 	
 	goCommonTabPage(form, <%= MaWoResultPointDetailAction.WO_RESULT_POINT_DETAIL_INIT %>, pageId);
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'WOPOINTID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maWoResultPointListForm.strutsAction.value = '<%=MaWoResultPointListAction.WO_RESULT_POINT_LIST_DELETE%>';
	var url = contextPath + "/maWoResultPointList.do";
	
	$.post(url,FormQueryString(maWoResultPointListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('maWoResultPointDetail',this);
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maWoResultPointList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maWoResultMstrCommonDTO.wkOrId"/><!-- Key -->
<html:hidden property="maWoResultPointListDTO.woPointId"/>
<html:hidden property="maWoResultPointListDTO.pmPointId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
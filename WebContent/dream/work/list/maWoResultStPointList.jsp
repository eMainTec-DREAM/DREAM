<%--===========================================================================
작업결과 필수항목 검사항목
author  kim21017
version $Id: maWoResultStPointList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.list.action.MaWoResultStPointListAction" %>
<%@ page import="dream.work.list.action.MaWoResultStPointDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 작업필수 점검항목 -->
<title><bean:message key='TAB.maWoResultStPointList'/></title>
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
		goTabPage("maWoResultStPointDetail");
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	goClose('maWoResultStPointDetail',this);
    findGridList('Search');   
}

function findGridList(sheetAction)
{
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (maWoResultStPointListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value == '') return;
	
	var form = document.maWoResultStPointListForm;	
	form.strutsAction.value = '<%=MaWoResultStPointListAction.WO_RESULT_STPOINT_LIST_FIND %>';
	
	var url = contextPath + "/maWoResultStPointList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maWoResultStPointListForm), "WOSTPOINTID");

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
	var form = document.maWoResultStPointListForm;
	 
	form.elements['maWoResultStPointListDTO.woStPointId'].value = getValueById(myGrid, selectedId,'WOSTPOINTID');
	goCommonTabPage(form, <%= MaWoResultStPointDetailAction.WO_RESULT_STPOINT_DETAIL_INIT %>, pageId);
}


/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('maWoResultStPointDetail');
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
  	createValidationCheck(myGrid, "maWoResultStPointDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	maWoResultStPointListForm.elements['maWoResultStPointListDTO.woStPointId'].value = "";
	goCommonTabPage(maWoResultStPointListForm, '', pageId);
 }

 /** 
  * 수정된 그리드 1건을 다시 조회한다.
  */
 function findGridRow(_woStPointId)
 {
	maWoResultStPointListForm.elements['maWoResultStPointListDTO.woStPointId'].value = _woStPointId;
 	findGridList('ReloadRow');
 	maWoResultStPointListForm.elements['maWoResultStPointListDTO.woStPointId'].value = "";
 }
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'WOSTPOINTID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maWoResultStPointListForm.strutsAction.value = '<%=MaWoResultStPointListAction.WO_RESULT_STPOINT_LIST_DELETE%>';
	var url = contextPath + "/maWoResultStPointList.do";
	
	$.post(url,FormQueryString(maWoResultStPointListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('maWoResultStPointDetail',this);
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maWoResultStPointList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maWoResultMstrCommonDTO.wkOrId"/><!-- Key -->
<html:hidden property="maWoResultStPointListDTO.woStPointId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
<%--===========================================================================
일일작업일지확인 - W/O리스트
author  kim21017
version $Id: maWoDailyWoList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.note.daily.action.MaWoDailyWoListAction" %> 
<%@ page import="dream.work.list.action.MaWoResultMstrDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- W/O리스트 -->
<title><bean:message key='TAB.maWoDailyWoList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var beforePageId = '';

function loadPage() 
{
	initGrid();
}

var myGrid;
var selectedWoId;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		selectedWoId = rowId;
		goOpen(rowId);
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		maWoDailyWoListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value = "";
    	return sortColumn("maWoDailyWoList", this, maWoDailyWoListForm, "WKORID", ind, direction);
	});
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
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (maWoDailyWoListForm.elements['maWoDailyCommonDTO.woDayListId'].value == '') return;
	
	var form = document.maWoDailyWoListForm;	
	form.strutsAction.value = '<%=MaWoDailyWoListAction.LIST_FIND %>';
	
	var url = contextPath + "/maWoDailyWoList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maWoDailyWoListForm), "WKORID", "Y");

}

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
  	//excelAction(myGrid);
    maWoDailyWoListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value = "";
	excelServerAction("maWoDailyWoList", maWoDailyWoListForm ); 
  } 
 
  /**
   * 상세 열기
   */
  function goOpen(rowId)
  {
  	var woType = getValueById(myGrid, rowId,'WOTYPE');
  	var pmType = getValueById(myGrid, rowId,'PMTYPE');
  	var param  = getValueById(myGrid, rowId,'PARAM');
  	
  	maWoDailyWoListForm.elements['maWoResultMstrCommonDTO.selectedWoType'].value = woType;
  	maWoDailyWoListForm.elements['maWoResultMstrCommonDTO.selectedPmType'].value = pmType;

  	goTabPage(param);
  }

  function goOpenAction()
  {
  	var selectedId=myGrid.getSelectedRowId();
   	if(selectedId == null) return;
   	
   	var pageId  = getValueById(myGrid, selectedId,'PARAM');
   	maWoDailyWoListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value =  getValueById(myGrid, selectedId,'WKORID'); 
  	maWoDailyWoListForm.elements['strutsAction'].value = '<%=MaWoResultMstrDetailAction.WO_RESULT_DETAIL_INIT%>';
   	openQuickTabPage(FormQueryString(maWoDailyWoListForm), pageId); 
  }

 function goTabPage(pageId)
 {
	 tabValidationCheck(myGrid, pageId, "goTabPageAction");
 }

 function goTabPageAction(pageId, selectedId)
 {
 	maWoDailyWoListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value =  getValueById(myGrid, selectedId,'WKORID'); 
//  	maWoDailyWoListForm.elements['maWoMonthWoCommonDTO.wkOrId'].value =  getValueById(myGrid, selectedId,'WKORID'); 
 	goCommonTabPage(maWoDailyWoListForm, <%= MaWoResultMstrDetailAction.WO_RESULT_DETAIL_INIT %>, pageId,beforePageId);

 	beforePageId = pageId;
 }
 
 /** 
  * 수정된 그리드 1건을 다시 조회한다.
  */
 function findGridRow(_wkorId)
 {
 	maWoDailyWoListForm.elements['maWoDailyDetailDTO.wkorId'].value = _wkorId;
 	findGridList('ReloadRow');
 	maWoDailyWoListForm.elements['maWoDailyDetailDTO.wkorId'].value = "";
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maWoDailyWoList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maWoDailyCommonDTO.woDayListId"/><!-- Key -->
<html:hidden property="maWoDailyDetailDTO.woDayListId"/><!-- Key -->
<html:hidden property="maWoDailyDetailDTO.wkorId"/><!-- Key -->
<html:hidden property="maWoDailyDetailDTO.woDate"/>
<html:hidden property="maWoDailyDetailDTO.woDeptId"/>
<html:hidden property="maWoDailyDetailDTO.startFdate"/>
<html:hidden property="maWoDailyDetailDTO.startFtime"/>
<html:hidden property="maWoDailyDetailDTO.startEdate"/>
<html:hidden property="maWoDailyDetailDTO.startEtime"/>
<html:hidden property="maWoDailyDetailDTO.plant"/>
<html:hidden property="maWoDailyDetailDTO.wkCtrId"/>
<html:hidden property="maWoDailyDetailDTO.equipId"/>
<html:hidden property="maWoResultMstrCommonDTO.wkOrId"/><!-- Key -->
<html:hidden property="maWoResultMstrCommonDTO.selectedWoType"/>
<html:hidden property="maWoResultMstrCommonDTO.selectedPmType"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
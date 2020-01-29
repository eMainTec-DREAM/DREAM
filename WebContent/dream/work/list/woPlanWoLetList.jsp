<%--===========================================================================
작업계획목록 - 안전작업
author  syyang
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.list.action.WoPlanWoLetListAction" %>
<%@ page import="dream.work.list.action.WoPlanWoLetDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 안전작업 -->
<title><bean:message key='TAB.woPlanWoLetList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var woLetCtgAc;
function loadPage() 
{
	setForUpdate();
	
	initGrid();
	
	woLetCtgAc = new autoC({"woPlanWoLetDetailDTO.multiDesc":"description"});
	woLetCtgAc.setTable("TAWOLETCTG");
	woLetCtgAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
	woLetCtgAc.setAcResultMap({
        "woPlanWoLetDetailDTO.multiKey":"woLetCtgId"
    });
	woLetCtgAc.setMultiSelect(true);
	woLetCtgAc.init();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("woPlanWoLetDetail");
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		woPlanWoLetListForm.elements['woPlanWoLetListDTO.woWoLetListId'].value = "";
        return sortColumn("woPlanWoLetList", this, woPlanWoLetListForm, "WOWOLETLISTID", ind, direction);
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
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (woPlanWoLetListForm.elements['woPlanCommonDTO.wkOrId'].value == '') return;
	
	var form = document.woPlanWoLetListForm;	
	form.strutsAction.value = '<%=WoPlanWoLetListAction.WO_PLAN_WO_LET_LIST_FIND %>';
	
	var url = contextPath + "/woPlanWoLetList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(woPlanWoLetListForm), "WOWOLETLISTID", "Y");

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
	var form = document.woPlanWoLetListForm;
	 
	form.elements['woPlanWoLetListDTO.woWoLetListId'].value = getValueById(myGrid, selectedId,'WOWOLETLISTID');
	goCommonTabPage(form, <%= WoPlanWoLetDetailAction.WO_PLAN_WO_LET_DETAIL_INIT %>, pageId);
}

function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
 	if(selectedId == null) return;

 	woPlanWoLetListForm.elements['woPlanWoLetListDTO.woWoLetListId'].value = getValueById(myGrid, selectedId,'WOWOLETLISTID');
 	woPlanWoLetListForm.elements['strutsAction'].value = '<%=WoPlanWoLetDetailAction.WO_PLAN_WO_LET_DETAIL_INIT%>';
 	openQuickTabPage(FormQueryString(woPlanWoLetListForm), 'woPlanWoLetDetail'); 
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('woPlanWoLetDetail');
}
 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	  woPlanWoLetListForm.elements['woPlanWoLetListDTO.woWoLetListId'].value = "";
	  excelServerAction("woPlanWoLetList", woPlanWoLetListForm );
  } 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "woPlanWoLetDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	woPlanWoLetListForm.elements['woPlanWoLetListDTO.woWoLetListId'].value = "";
	goCommonTabPage(woPlanWoLetListForm, '', pageId);
 }
 
 /** 
  * 수정된 그리드 1건을 다시 조회한다.
  */
 function findGridRow(_woWoLetListId)
 {
	woPlanWoLetListForm.elements['woPlanWoLetListDTO.woWoLetListId'].value = _woWoLetListId;
 	findGridList('ReloadRow');
 	woPlanWoLetListForm.elements['woPlanWoLetListDTO.woWoLetListId'].value = "";
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'woWoLetListId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	woPlanWoLetListForm.strutsAction.value = '<%=WoPlanWoLetListAction.WO_PLAN_WO_LET_LIST_DELETE%>';
	var url = contextPath + "/woPlanWoLetList.do";
	
	$.post(url,FormQueryString(woPlanWoLetListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('woPlanWoLetDetail',this);
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

function goSave(){
	var url = contextPath + "/woPlanWoLetList.do";
	
    $.post(url,FormQueryString(woPlanWoLetListForm), function(_data){
    	afterSave(_data);
    });
}
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    getTopPage().afterSaveAll(currentPageId);
    
    goSearch();
}

/**
 * 일괄등록
 */
function goRegbatch()
{
	woLetCtgAc.openLov();
}

function setAcLovValue(rtnArr, code)
{
	woPlanWoLetListForm.strutsAction.value = '<%=WoPlanWoLetListAction.WO_PLAN_WO_LET_LIST_INPUT%>';
	
	goSaveAll();
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/woPlanWoLetList.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="woPlanCommonDTO.wkOrId"/><!-- Key -->
	<html:hidden property="woPlanWoLetListDTO.woWoLetListId"/>
	<html:hidden property="woPlanWoLetDetailDTO.multiKey"/><!-- MultiSelect Key -->
	<html:hidden property="woPlanWoLetDetailDTO.multiDesc"/><!-- MultiSelect Desc -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
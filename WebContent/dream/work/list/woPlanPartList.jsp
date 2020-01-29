<%--===========================================================================
작업계획목록 - 투입부품
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.list.action.WoPlanPartListAction" %>
<%@ page import="dream.work.list.action.WoPlanPartDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 투입부품 -->
<title><bean:message key='TAB.woPlanPartList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var ptEmgIssAc;
function loadPage() 
{
	setForUpdate();
	
	initGrid();
	
	ptEmgIssAc = new autoC({"woPlanPartListDTO.multiDesc":"partDesc"});
	ptEmgIssAc.setTable("TAPTEMGISSLIST");
	ptEmgIssAc.setAcResultMap({
        "woPlanPartListDTO.multiKey":"ptemgisslistId"
    });
	ptEmgIssAc.setMultiSelect(true);
	ptEmgIssAc.init();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("woPlanPartDetail");
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		woPlanPartListForm.elements['woPlanPartListDTO.woPartId'].value = "";
        return sortColumn("woPlanPartList", this, woPlanPartListForm, "WOPARTID", ind, direction);
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
	if (woPlanPartListForm.elements['woPlanCommonDTO.wkOrId'].value == '') return;
	
	var form = document.woPlanPartListForm;	
	form.strutsAction.value = '<%=WoPlanPartListAction.WO_RESULT_PART_LIST_FIND %>';
	
	var url = contextPath + "/woPlanPartList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(woPlanPartListForm), "WOPARTID","Y");
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
	var form = document.woPlanPartListForm;
	 
	form.elements['woPlanPartListDTO.woPartId'].value = getValueById(myGrid, selectedId,'WOPARTID');
	goCommonTabPage(form, <%= WoPlanPartDetailAction.WO_RESULT_PART_DETAIL_INIT %>, pageId);
	
	form.elements['woPlanPartListDTO.woPartId'].value = "";
}


/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('woPlanPartDetail');
}

function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
 	if(selectedId == null) return;

 	woPlanPartListForm.elements['woPlanPartListDTO.woPartId'].value = getValueById(myGrid, selectedId,'WOPARTID');
 	woPlanPartListForm.elements['strutsAction'].value = '<%=WoPlanPartDetailAction.WO_RESULT_PART_DETAIL_INIT%>';
 	openQuickTabPage(FormQueryString(woPlanPartListForm), 'woPlanPartDetail'); 
 	woPlanPartListForm.elements['woPlanPartListDTO.woPartId'].value = "";
}


 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	  woPlanPartListForm.elements['woPlanPartListDTO.woPartId'].value = "";
	  excelServerAction("woPlanPartList", woPlanPartListForm );
  } 
 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "woPlanPartDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	woPlanPartListForm.elements['woPlanPartListDTO.woPartId'].value = "";
	goCommonTabPage(woPlanPartListForm, '', pageId);
 }

 /** 
  * 수정된 그리드 1건을 다시 조회한다.
  */
 function findGridRow(_woPartId)
 {
	woPlanPartListForm.elements['woPlanPartListDTO.woPartId'].value = _woPartId;
 	findGridList('ReloadRow');
 	woPlanPartListForm.elements['woPlanPartListDTO.woPartId'].value = "";
 }
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'WOPARTID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	woPlanPartListForm.strutsAction.value = '<%=WoPlanPartListAction.WO_RESULT_PART_LIST_DELETE%>';
	var url = contextPath + "/woPlanPartList.do";
	
	$.post(url,FormQueryString(woPlanPartListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('woPlanPartDetail',this);
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

function goSave(){
	var url = contextPath + "/woPlanPartList.do";
	
    $.post(url,FormQueryString(woPlanPartListForm), function(_data){
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
 * 긴급출고자재 선택
 */
function goUseparts()
{
	ptEmgIssAc.openLov();
}

function setAcLovValue(rtnArr, code)
{
	woPlanPartListForm.strutsAction.value = '<%=WoPlanPartListAction.WO_RESULT_PART_LIST_INPUT%>';
	
	goSaveAll();
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/woPlanPartList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="woPlanCommonDTO.wkOrId"/><!-- Key -->
<html:hidden property="woPlanPartListDTO.woPartId"/>
<html:hidden property="woPlanPartListDTO.multiKey"/><!-- MultiSelect Key -->
<html:hidden property="woPlanPartListDTO.multiDesc"/><!-- MultiSelect Desc -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
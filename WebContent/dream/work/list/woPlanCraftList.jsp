<%--===========================================================================
작업계획목록 - 작업자
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.list.action.WoPlanCraftListAction" %>
<%@ page import="dream.work.list.action.WoPlanCraftDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 작업자 -->
<title><bean:message key='TAB.maWoResultCraftList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var empAc;
function loadPage() 
{
	setForUpdate();
	
	initGrid();
	
	empAc = new autoC({"woPlanCraftDetailDTO.multiDesc":"emp_name"});
	empAc.setTable("TAEMP");
	empAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
  	});
	empAc.setAcResultMap({
        "woPlanCraftDetailDTO.multiKey":"emp_id"
    });
	empAc.setMultiSelect(true);
	empAc.init();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("woPlanCraftDetail");
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		woPlanCraftListForm.elements['woPlanCraftListDTO.woCraftId'].value = "";
        return sortColumn("woPlanCraftList", this, woPlanCraftListForm, "WOCRAFTID", ind, direction);
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
	if (woPlanCraftListForm.elements['woPlanCommonDTO.wkOrId'].value == '') return;
	
	var form = document.woPlanCraftListForm;	
	form.strutsAction.value = '<%=WoPlanCraftListAction.WO_RESULT_CRAFT_LIST_FIND %>';
	
	var url = contextPath + "/woPlanCraftList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(woPlanCraftListForm), "WOCRAFTID", "Y");

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
	var form = document.woPlanCraftListForm;
	 
	form.elements['woPlanCraftListDTO.woCraftId'].value = getValueById(myGrid, selectedId,'WOCRAFTID');
	goCommonTabPage(form, <%= WoPlanCraftDetailAction.WO_RESULT_CRAFT_DETAIL_INIT %>, pageId);
}

function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
 	if(selectedId == null) return;

 	woPlanCraftListForm.elements['woPlanCraftListDTO.woCraftId'].value = getValueById(myGrid, selectedId,'WOCRAFTID');
 	woPlanCraftListForm.elements['strutsAction'].value = '<%=WoPlanCraftDetailAction.WO_RESULT_CRAFT_DETAIL_INIT%>';
 	openQuickTabPage(FormQueryString(woPlanCraftListForm), 'woPlanCraftDetail'); 
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('woPlanCraftDetail');
}
 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	  woPlanCraftListForm.elements['woPlanCraftListDTO.woCraftId'].value = "";
	  excelServerAction("woPlanCraftList", woPlanCraftListForm );
  } 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "woPlanCraftDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	woPlanCraftListForm.elements['woPlanCraftListDTO.woCraftId'].value = "";
	goCommonTabPage(woPlanCraftListForm, '', pageId);
 }
 
 /** 
  * 수정된 그리드 1건을 다시 조회한다.
  */
 function findGridRow(_woCraftId)
 {
	 woPlanCraftListForm.elements['woPlanCraftListDTO.woCraftId'].value = _woCraftId;
 	findGridList('ReloadRow');
 	woPlanCraftListForm.elements['woPlanCraftListDTO.woCraftId'].value = "";
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'WOCRAFTID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	woPlanCraftListForm.strutsAction.value = '<%=WoPlanCraftListAction.WO_RESULT_CRAFT_LIST_DELETE%>';
	var url = contextPath + "/woPlanCraftList.do";
	
	$.post(url,FormQueryString(woPlanCraftListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('woPlanCraftDetail',this);
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}
//작업자 상세페이지에 채울 일자와 시간을 작업상세에서 가져온다.
function getDateTime(){
	 var startDate = parent.M$('woPlanDetailDTO.startDate').value;
	 var startTime = parent.M$('woPlanDetailDTO.startTime').value;
	 var endDate   = parent.M$('woPlanDetailDTO.endDate').value;
	 var endTime   = parent.M$('woPlanDetailDTO.endTime').value;
	 return startDate+","+startTime+","+endDate+","+endTime;
}
function getWorkTime(){
	 var workTime = parent.M$('woPlanDetailDTO.workTime').value;
	 return workTime;
}

function goSave(){
	var url = contextPath + "/woPlanCraftList.do";
	
    $.post(url,FormQueryString(woPlanCraftListForm), function(_data){
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
	empAc.openLov();
}

function setAcLovValue(rtnArr, code)
{
	woPlanCraftListForm.strutsAction.value = '<%=WoPlanCraftListAction.WO_RESULT_CRAFT_LIST_INPUT%>';
	
	var dateTimes = getDateTime();
	dateTimes = dateTimes.split(",");
	var workTime = getWorkTime();
	if(woPlanCraftListForm.elements['woPlanCraftDetailDTO.startDate'].value=='') 
		woPlanCraftListForm.elements['woPlanCraftDetailDTO.startDate'].value = dateTimes[0];
	if(woPlanCraftListForm.elements['woPlanCraftDetailDTO.startTime'].value=='') 
		woPlanCraftListForm.elements['woPlanCraftDetailDTO.startTime'].value = dateTimes[1];
	if(woPlanCraftListForm.elements['woPlanCraftDetailDTO.endDate'].value=='') 
		woPlanCraftListForm.elements['woPlanCraftDetailDTO.endDate'].value = dateTimes[2];
	if(woPlanCraftListForm.elements['woPlanCraftDetailDTO.endTime'].value=='') 
		woPlanCraftListForm.elements['woPlanCraftDetailDTO.endTime'].value = dateTimes[3];
	if(woPlanCraftListForm.elements['woPlanCraftDetailDTO.workTime'].value=='') 
		woPlanCraftListForm.elements['woPlanCraftDetailDTO.workTime'].value = workTime;
	
	goSaveAll();
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/woPlanCraftList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="woPlanCommonDTO.wkOrId"/><!-- Key -->
<html:hidden property="woPlanCraftListDTO.woCraftId"/>
<html:hidden property="woPlanCraftDetailDTO.startDate"/>
<html:hidden property="woPlanCraftDetailDTO.startTime"/>
<html:hidden property="woPlanCraftDetailDTO.endDate"/>
<html:hidden property="woPlanCraftDetailDTO.endTime"/>
<html:hidden property="woPlanCraftDetailDTO.workTime"/>
<html:hidden property="woPlanCraftDetailDTO.multiKey"/><!-- MultiSelect Key -->
<html:hidden property="woPlanCraftDetailDTO.multiDesc"/><!-- MultiSelect Desc -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
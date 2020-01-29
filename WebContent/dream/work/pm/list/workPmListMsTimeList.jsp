<%--===========================================================================
작업시간 List
author  js.lee
version $Id: Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.pm.list.action.WorkPmListMsTimeListAction" %>
<%@ page import="dream.work.pm.list.action.WorkPmListMsTimeDetailAction" %>
<%@ page import="dream.work.pm.list.action.WorkPmListMsTimeLovAction"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 작업시간 -->
<title><bean:message key='TAB.measureTime'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;
var msTimeAc;

function loadPage() 
{
	initGrid();
	
	/** 작업시간 AC */
	msTimeAc = new autoC({"workPmListMsTimeDetailDTO.multiMsTimeKey":"measureTimeId"});
	msTimeAc.setAcConditionMap({
    	"is_use":"Y"
  	   });
    msTimeAc.setTable("TAMEASURETIME");
    msTimeAc.setAcResultMap({
        "workPmListMsTimeDetailDTO.multiMsTimeDesc":"measureTime"
    });
    msTimeAc.setMultiSelect(true);
    msTimeAc.init();
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("workPmListMsTimeDetail");
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	workPmListMsTimeListForm.elements['workPmListMsTimeListDTO.pmMsTimeId'].value = "";
        return sortColumn("workPmListMsTimeList", this, workPmListMsTimeListForm, "PMMSTIMEID", ind, direction);
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
	if (workPmListMsTimeListForm.elements['maPmMstrCommonDTO.pmId'].value == '') return;
	
	var form = document.workPmListMsTimeListForm;	
	form.strutsAction.value = '<%=WorkPmListMsTimeListAction.PM_MS_TIME_LIST_FIND %>';
	
	var url = contextPath + "/workPmListMsTimeList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workPmListMsTimeListForm), "PMMSTIMEID", "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pmMsTimeId)
{
	workPmListMsTimeListForm.elements['workPmListMsTimeListDTO.pmMsTimeId'].value = _pmMsTimeId;
	findGridList('ReloadRow');
	workPmListMsTimeListForm.elements['workPmListMsTimeListDTO.pmMsTimeId'].value = "";
}

function goSave(){
	var url = contextPath + "/workPmListMsTimeList.do";
	
    $.post(url,FormQueryString(workPmListMsTimeListForm), function(_data){
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
function goRegbatch(){

	msTimeAc.openLov();
}

function setAcLovValue(rtnArr, code)
{
	workPmListMsTimeListForm.strutsAction.value = '<%=WorkPmListMsTimeListAction.PM_MS_TIME_LIST_INPUT%>';
	
	var url = contextPath + "/workPmListMsTimeList.do";
	
    $.post(url,FormQueryString(workPmListMsTimeListForm), function(_data){
    	goSearch();
    });
	//goSaveAll();
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
	var form = document.workPmListMsTimeListForm;
	 
	form.elements['workPmListMsTimeListDTO.pmMsTimeId'].value = getValueById(myGrid, selectedId,'PMMSTIMEID');
	goCommonTabPage(form, <%= WorkPmListMsTimeDetailAction.PM_MS_TIME_DETAIL_INIT %>, pageId);
}


/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('workPmListMsTimeDetail');
}

 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "workPmListMsTimeDetail" , "goCreateAction");
 }
  
 function goCreateAction(pageId)
 {
	workPmListMsTimeListForm.elements['workPmListMsTimeListDTO.pmMsTimeId'].value = "";
	goCommonTabPage(workPmListMsTimeListForm, '', pageId);
 }

 function goOpenAction()
 {
     var selectedId=myGrid.getSelectedRowId();
     if(selectedId == null) return;
     
     workPmListMsTimeListForm.elements['workPmListMsTimeListDTO.pmMsTimeId'].value =  getValueById(myGrid, selectedId,'PMMSTIMEID');  
     workPmListMsTimeListForm.elements['strutsAction'].value = '<%=WorkPmListMsTimeDetailAction.PM_MS_TIME_DETAIL_INIT%>';
     openQuickTabPage(FormQueryString(workPmListMsTimeListForm), 'workPmListMsTimeDetail'); 
 } 
 

 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'isDelCheck', 'PMMSTIMEID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	workPmListMsTimeListForm.strutsAction.value = '<%=WorkPmListMsTimeListAction.PM_MS_TIME_LIST_DELETE%>';
	var url = contextPath + "/workPmListMsTimeList.do";
	
	$.post(url,FormQueryString(workPmListMsTimeListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('workPmListMsTimeDetail',this);
   	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
   
function goExcel()
{
	workPmListMsTimeListForm.elements['workPmListMsTimeListDTO.pmMsTimeId'].value = "";
	excelServerAction("workPmListMsTimeList", workPmListMsTimeListForm );
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workPmListMsTimeList">
<html:hidden property="strutsAction"/>
<html:hidden property="maPmMstrCommonDTO.pmId"/><!-- Key -->
<html:hidden property="workPmListMsTimeListDTO.pmMsTimeId"/>
<html:hidden property="workPmListMsTimeDetailDTO.multiMsTimeKey"/><!-- MultiSelect Key -->
<html:hidden property="workPmListMsTimeDetailDTO.multiMsTimeDesc"/><!-- MultiSelect Desc -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
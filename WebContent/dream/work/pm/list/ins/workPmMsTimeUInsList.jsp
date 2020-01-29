<%--===========================================================================
작업시간 List
author  js.lee
version $Id: Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.pm.list.action.WorkPmMsTimeUInsListAction" %>
<%@ page import="dream.work.pm.list.action.WorkPmMsTimeUInsDetailAction" %>
<%@ page import="dream.work.pm.list.action.WorkPmListMsTimeLovAction"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 작업시간 -->
<title><bean:message key='PAGE.workPmMsTimeUInsList'/></title>
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
	msTimeAc = new autoC({"workPmMsTimeUInsDetailDTO.multiMsTimeKey":"measureTimeId"});
	msTimeAc.setAcConditionMap({
    	"is_use":"Y"
  	   });
    msTimeAc.setTable("TAMEASURETIME");
    msTimeAc.setAcResultMap({
        "workPmMsTimeUInsDetailDTO.multiMsTimeDesc":"measureTime"
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
		goTabPage("workPmMsTimeUInsDetail");
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	workPmMsTimeUInsListForm.elements['workPmMsTimeUInsListDTO.pmMsTimeId'].value = "";
        return sortColumn("workPmMsTimeUInsList", this, workPmMsTimeUInsListForm, "PMMSTIMEID", ind, direction);
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
	if (workPmMsTimeUInsListForm.elements['maPmMstrCommonDTO.pmId'].value == '') return;
	
	var form = document.workPmMsTimeUInsListForm;	
	form.strutsAction.value = '<%=WorkPmMsTimeUInsListAction.PM_MS_TIME_LIST_FIND %>';
	
	var url = contextPath + "/workPmMsTimeUInsList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workPmMsTimeUInsListForm), "PMMSTIMEID", "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pmMsTimeId)
{
	workPmMsTimeUInsListForm.elements['workPmMsTimeUInsListDTO.pmMsTimeId'].value = _pmMsTimeId;
	findGridList('ReloadRow');
	workPmMsTimeUInsListForm.elements['workPmMsTimeUInsListDTO.pmMsTimeId'].value = "";
}

function goSave(){
	var url = contextPath + "/workPmMsTimeUInsList.do";
	
    $.post(url,FormQueryString(workPmMsTimeUInsListForm), function(_data){
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
	workPmMsTimeUInsListForm.strutsAction.value = '<%=WorkPmMsTimeUInsListAction.PM_MS_TIME_LIST_INPUT%>';
	
	var url = contextPath + "/workPmMsTimeUInsList.do";
	
    $.post(url,FormQueryString(workPmMsTimeUInsListForm), function(_data){
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
	var form = document.workPmMsTimeUInsListForm;
	 
	form.elements['workPmMsTimeUInsListDTO.pmMsTimeId'].value = getValueById(myGrid, selectedId,'PMMSTIMEID');
	goCommonTabPage(form, <%= WorkPmMsTimeUInsDetailAction.PM_MS_TIME_DETAIL_INIT %>, pageId);
}


/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('workPmMsTimeUInsDetail');
}

 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "workPmMsTimeUInsDetail" , "goCreateAction");
 }
  
 function goCreateAction(pageId)
 {
	workPmMsTimeUInsListForm.elements['workPmMsTimeUInsListDTO.pmMsTimeId'].value = "";
	goCommonTabPage(workPmMsTimeUInsListForm, '', pageId);
 }

 function goOpenAction()
 {
     var selectedId=myGrid.getSelectedRowId();
     if(selectedId == null) return;
     
     workPmMsTimeUInsListForm.elements['workPmMsTimeUInsListDTO.pmMsTimeId'].value =  getValueById(myGrid, selectedId,'PMMSTIMEID');  
     workPmMsTimeUInsListForm.elements['strutsAction'].value = '<%=WorkPmMsTimeUInsDetailAction.PM_MS_TIME_DETAIL_INIT%>';
     openQuickTabPage(FormQueryString(workPmMsTimeUInsListForm), 'workPmMsTimeUInsDetail'); 
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

	workPmMsTimeUInsListForm.strutsAction.value = '<%=WorkPmMsTimeUInsListAction.PM_MS_TIME_LIST_DELETE%>';
	var url = contextPath + "/workPmMsTimeUInsList.do";
	
	$.post(url,FormQueryString(workPmMsTimeUInsListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('workPmMsTimeUInsDetail',this);
   	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
   
function goExcel()
{
	workPmMsTimeUInsListForm.elements['workPmMsTimeUInsListDTO.pmMsTimeId'].value = "";
	excelServerAction("workPmMsTimeUInsList", workPmMsTimeUInsListForm );
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workPmMsTimeUInsList">
<html:hidden property="strutsAction"/>
<html:hidden property="maPmMstrCommonDTO.pmId"/><!-- Key -->
<html:hidden property="workPmMsTimeUInsListDTO.pmMsTimeId"/>
<html:hidden property="workPmMsTimeUInsDetailDTO.multiMsTimeKey"/><!-- MultiSelect Key -->
<html:hidden property="workPmMsTimeUInsDetailDTO.multiMsTimeDesc"/><!-- MultiSelect Desc -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
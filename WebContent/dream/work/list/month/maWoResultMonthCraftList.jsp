<%--===========================================================================
작업결과(월간작업일정) 작업자
author  kim21017
version $Id: maWoResultMonthCraftList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.list.action.MaWoResultCraftListAction" %>
<%@ page import="dream.work.list.action.MaWoResultCraftDetailAction" %>
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
	
	empAc = new autoC({"maWoResultCraftDetailDTO.multiDesc":"emp_name"});
	empAc.setTable("TAEMP");
	empAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
       	,"dept_id":parent.M$('maWoResultMstrDetailDTO.deptId').value
       	,"deptDesc":parent.M$('maWoResultMstrDetailDTO.deptDesc').value
  	});
	empAc.setAcResultMap({
        "maWoResultCraftDetailDTO.multiKey":"emp_id"
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
		goTabPage("maWoResultMonthCraftDetail");
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
	if (maWoResultCraftListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value == '') return;
	
	var form = document.maWoResultCraftListForm;	
	form.strutsAction.value = '<%=MaWoResultCraftListAction.WO_RESULT_CRAFT_LIST_FIND %>';
	
	var url = contextPath + "/maWoResultCraftList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maWoResultCraftListForm), "WOCRAFTID","Y");

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
	var form = document.maWoResultCraftListForm;
	 
	form.elements['maWoResultCraftListDTO.woCraftId'].value = getValueById(myGrid, selectedId,'WOCRAFTID');
	goCommonTabPage(form, <%= MaWoResultCraftDetailAction.WO_RESULT_CRAFT_DETAIL_INIT %>, pageId);
}


/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('maWoResultMonthCraftDetail');
}

 function goOpenAction()
 {
 	var selectedId=myGrid.getSelectedRowId();
  	if(selectedId == null) return;

  	maWoResultCraftListForm.elements['maWoResultCraftListDTO.woCraftId'].value = getValueById(myGrid, selectedId,'WOCRAFTID');
  	maWoResultCraftListForm.elements['strutsAction'].value = '<%=MaWoResultCraftDetailAction.WO_RESULT_CRAFT_DETAIL_INIT%>';
  	openQuickTabPage(FormQueryString(maWoResultCraftListForm), 'maWoResultMonthCraftDetail'); 
 }

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	maWoResultCraftListForm.elements['maWoResultCraftListDTO.woCraftId'].value = "";
  	excelServerAction("maWoResultCraftList",maWoResultCraftListForm);
  } 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "maWoResultMonthCraftDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	maWoResultCraftListForm.elements['maWoResultCraftListDTO.woCraftId'].value = "";
	goCommonTabPage(maWoResultCraftListForm, '', pageId);
 }
 
 /** 
  * 수정된 그리드 1건을 다시 조회한다.
  */
 function findGridRow(_woCraftId)
 {
	 maWoResultCraftListForm.elements['maWoResultCraftListDTO.woCraftId'].value = _woCraftId;
 	findGridList('ReloadRow');
 	maWoResultCraftListForm.elements['maWoResultCraftListDTO.woCraftId'].value = "";
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

	maWoResultCraftListForm.strutsAction.value = '<%=MaWoResultCraftListAction.WO_RESULT_CRAFT_LIST_DELETE%>';
	var url = contextPath + "/maWoResultCraftList.do";
	
	$.post(url,FormQueryString(maWoResultCraftListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('maWoResultMonthCraftDetail',this);
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}
//작업자 상세페이지에 채울 일자와 시간을 작업상세에서 가져온다.
function getDateTime(){
	 var startDate = parent.M$('maWoResultMstrDetailDTO.startDate').value;
	 var startTime = parent.M$('maWoResultMstrDetailDTO.startTime').value;
	 var endDate   = parent.M$('maWoResultMstrDetailDTO.endDate').value;
	 var endTime   = parent.M$('maWoResultMstrDetailDTO.endTime').value;
	 return startDate+","+startTime+","+endDate+","+endTime;
}
function getWorkTime(){
	 var workTime = parent.M$('maWoResultMstrDetailDTO.workTime').value;
	 return workTime;
}

function goSave(){
	var url = contextPath + "/maWoResultMonthCraftList.do";
	
    $.post(url,FormQueryString(maWoResultCraftListForm), function(_data){
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
	maWoResultCraftListForm.strutsAction.value = '<%=MaWoResultCraftListAction.WO_RESULT_CRAFT_LIST_INPUT%>';
	
	var dateTimes = getDateTime();
	dateTimes = dateTimes.split(",");
	var workTime = getWorkTime();
	if(maWoResultCraftListForm.elements['maWoResultCraftDetailDTO.startDate'].value=='') 
		maWoResultCraftListForm.elements['maWoResultCraftDetailDTO.startDate'].value = dateTimes[0];
	if(maWoResultCraftListForm.elements['maWoResultCraftDetailDTO.startTime'].value=='') 
		maWoResultCraftListForm.elements['maWoResultCraftDetailDTO.startTime'].value = dateTimes[1];
	if(maWoResultCraftListForm.elements['maWoResultCraftDetailDTO.endDate'].value=='') 
		maWoResultCraftListForm.elements['maWoResultCraftDetailDTO.endDate'].value = dateTimes[2];
	if(maWoResultCraftListForm.elements['maWoResultCraftDetailDTO.endTime'].value=='') 
		maWoResultCraftListForm.elements['maWoResultCraftDetailDTO.endTime'].value = dateTimes[3];
	if(maWoResultCraftListForm.elements['maWoResultCraftDetailDTO.workTime'].value=='') 
		maWoResultCraftListForm.elements['maWoResultCraftDetailDTO.workTime'].value = workTime;
	
	goSaveAll();
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maWoResultCraftList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maWoResultMstrCommonDTO.wkOrId"/><!-- Key -->
<html:hidden property="maWoResultCraftListDTO.woCraftId"/>
<html:hidden property="maWoResultCraftDetailDTO.startDate"/>
<html:hidden property="maWoResultCraftDetailDTO.startTime"/>
<html:hidden property="maWoResultCraftDetailDTO.endDate"/>
<html:hidden property="maWoResultCraftDetailDTO.endTime"/>
<html:hidden property="maWoResultCraftDetailDTO.workTime"/>
<html:hidden property="maWoResultCraftDetailDTO.multiKey"/><!-- MultiSelect Key -->
<html:hidden property="maWoResultCraftDetailDTO.multiDesc"/><!-- MultiSelect Desc -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
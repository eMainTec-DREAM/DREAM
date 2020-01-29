<%--===========================================================================
설비
author  kim21017
version $Id: workPmListSchdList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.pm.list.action.WorkPmListSchdListAction" %>
<%@ page import="dream.work.pm.list.action.WorkPmListSchdDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 청소설비 -->
<title><bean:message key='TAB.workPmListSchdList'/></title>
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
		goTabPage("workPmListSchdDetail");
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	workPmListSchdListForm.elements['maPmMstrCommonDTO.pmEventSchedId'].value = "";
        return sortColumn("workPmListSchdList", this, workPmListSchdListForm, "PMEVENTSCHEDID", ind, direction);
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
	if (workPmListSchdListForm.elements['maPmMstrCommonDTO.pmId'].value == '') return;
	
	var form = document.workPmListSchdListForm;	
	form.strutsAction.value = '<%=WorkPmListSchdListAction.WORK_PM_SCH_LIST_FIND %>';
	
	var url = contextPath + "/workPmListSchdList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workPmListSchdListForm), "PMEVENTSCHEDID", "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pmEventSchedId)
{
	workPmListSchdListForm.elements['maPmMstrCommonDTO.pmEventSchedId'].value = _pmEventSchedId;
	findGridList('ReloadRow');
	workPmListSchdListForm.elements['maPmMstrCommonDTO.pmEventSchedId'].value = "";
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
	var form = document.workPmListSchdListForm;
	 
	form.elements['maPmMstrCommonDTO.pmEventSchedId'].value = getValueById(myGrid, selectedId,'PMEVENTSCHEDID');
	goCommonTabPage(form, <%= WorkPmListSchdDetailAction.WORK_PM_SCH_DETAIL_INIT %>, pageId);
}


/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('workPmListSchdDetail');
}

 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "workPmListSchdDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	workPmListSchdListForm.elements['maPmMstrCommonDTO.pmEventSchedId'].value = "";
	goCommonTabPage(workPmListSchdListForm, '', pageId);
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'isDelCheck', 'PMEVENTSCHEDID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	workPmListSchdListForm.strutsAction.value = '<%=WorkPmListSchdListAction.WORK_PM_SCH_LIST_DELETE%>';
	var url = contextPath + "/workPmListSchdList.do";
	
	$.post(url,FormQueryString(workPmListSchdListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('workPmListSchdDetail',this);
   	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
   
function goExcel()
{
	workPmListSchdListForm.elements['maPmMstrCommonDTO.pmEventSchedId'].value = "";
	excelServerAction("workPmListSchdList",workPmListSchdListForm);
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workPmListSchdList">
<html:hidden property="strutsAction"/>
<html:hidden property="maPmMstrCommonDTO.pmId"/><!-- Key -->
<html:hidden property="maPmMstrCommonDTO.pmEventSchedId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
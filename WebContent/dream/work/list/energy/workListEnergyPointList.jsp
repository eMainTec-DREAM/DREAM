<%--===========================================================================
에너지 값 측정항목 목록
author  sy.yang
version $Id: workListEnergyPointList.jsp,v 1.1 2015/12/03 01:45:27 sy.yang Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.list.energy.action.WorkListEnergyPointListAction" %>
<%@ page import="dream.work.list.energy.action.WorkListEnergyPointDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 검사항목 -->
<title><bean:message key='TAB.workListEnergyPointList'/></title>
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
		goTabPage("workListEnergyPointDetail");
	});  
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	workListEnergyPointListForm.elements['workListEnergyPointListDTO.pmInsPointId'].value = "";
    	workListEnergyPointListForm.elements['workListEnergyPointListDTO.pmPointId'].value = "";
        return sortColumn("workListEnergyPointList", this, workListEnergyPointListForm, "PMINSPOINTID", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
	myGrid.init();
	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

// /**
//  * Set the Sequence receved to new row.
//  */
// function setSequenceVal(sequenceVal)
// {
// 	var pminslistId = workListEnergyPointListForm.elements['workListEnergyMstrListCommonDTO.pminslistId'].value;
// 	var pmpointId   = workListEnergyPointListForm.elements['workListEnergyPointListDTO.pmPointId'].value;
// }

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
    findGridList('Search', "PMINSPOINTID");   
}

function findGridList(sheetAction, keyCol)
{
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (workListEnergyPointListForm.elements['workListEnergyMstrListCommonDTO.pminslistId'].value == '') return;
	
	var form = document.workListEnergyPointListForm;	
	form.strutsAction.value = '<%=WorkListEnergyPointListAction.WORK_PMU_POINT_LIST_FIND %>';
	
	var url = contextPath + "/workListEnergyPointList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workListEnergyPointListForm), "PMINSPOINTID", "Y");

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
	var form = document.workListEnergyPointListForm;
	
	form.elements['workListEnergyPointListDTO.pmInsPointId'].value = getValueById(myGrid, selectedId,'PMINSPOINTID');
	form.elements['workListEnergyPointListDTO.pmPointId'].value = getValueById(myGrid, selectedId,'PMPOINTID');

	goCommonTabPage(form, <%= WorkListEnergyPointDetailAction.WORK_PMU_POINT_DETAIL_INIT %>, pageId); 
}

/**
 * 상세열기
 */
 function goOpen()
 {
	goTabPage('workListEnergyPointDetail');
}
 function goOpenAction()
 {
     var selectedId=myGrid.getSelectedRowId();
     if(selectedId == null) return;
     
     workListEnergyPointListForm.elements['workListEnergyPointListDTO.pmInsPointId'].value = getValueById(myGrid, selectedId,'PMINSPOINTID');
     workListEnergyPointListForm.elements['workListEnergyPointListDTO.pmPointId'].value = getValueById(myGrid, selectedId,'PMPOINTID');
     workListEnergyPointListForm.elements['strutsAction'].value = '<%= WorkListEnergyPointDetailAction.WORK_PMU_POINT_DETAIL_INIT %>';
     openQuickTabPage(FormQueryString(workListEnergyPointListForm), 'workListEnergyPointDetail'); 
 }

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
  	//excelAction(myGrid);
	workListEnergyPointListForm.elements['workListEnergyPointListDTO.pmInsPointId'].value = "";
    workListEnergyPointListForm.elements['workListEnergyPointListDTO.pmPointId'].value = "";
	excelServerAction("workListEnergyPointList", workListEnergyPointListForm);
  } 

 /** 
  * 수정된 그리드 1건을 다시 조회한다.
  */
 function findGridRow(_pmInsPointId, _pmPointId)
 {
	workListEnergyPointListForm.elements['workListEnergyPointListDTO.pmInsPointId'].value = _pmInsPointId;
	workListEnergyPointListForm.elements['workListEnergyPointListDTO.pmPointId'].value = _pmPointId;
 	findGridList('ReloadRow', 'PMINSPOINTID');
 	workListEnergyPointListForm.elements['workListEnergyPointListDTO.pmInsPointId'].value = "";	
 	workListEnergyPointListForm.elements['workListEnergyPointListDTO.pmPointId'].value = "";
 	
 	goReloadTabPageAction("workListEnergyPointDetail", _pmInsPointId, _pmPointId)
 }
 
 function goReloadTabPageAction(pageId, _pmInsPointId, _pmPointId)
 {
 	var form = document.workListEnergyPointListForm;
 	
 	form.elements['workListEnergyPointListDTO.pmInsPointId'].value = _pmInsPointId;
 	form.elements['workListEnergyPointListDTO.pmPointId'].value = _pmPointId;
 	
 	goCommonTabPage(form, <%= WorkListEnergyPointDetailAction.WORK_PMU_POINT_DETAIL_INIT %>, pageId);
 }
 
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = workListEnergyPointListForm.elements['workListEnergyPointListDTO.pmInsPointId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workListEnergyPointList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="workListEnergyMstrListCommonDTO.pminslistId"/><!-- Key -->
<html:hidden property="workListEnergyMstrListCommonDTO.pmId"/>
<html:hidden property="workListEnergyMstrListCommonDTO.pmschedStatusId"/>
<html:hidden property="workListEnergyPointListDTO.pmInsPointId"/>
<html:hidden property="workListEnergyPointListDTO.pmPointId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
<%--===========================================================================
일일작업일지확인 - 예방점검리스트
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.note.daily.action.MaWoDailyPmiListAction" %> 
<%@ page import="dream.work.pmi.list.action.WorkPmiDetailAction"%>
<%@ page import="dream.work.cal.pmrinsperiod.action.WorkCalPmRInsPeriodDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 예방점검리스트 -->
<title><bean:message key='TAB.maWoDailyPmiList'/></title>
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
		goOpen();
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		maWoDailyPmiListForm.elements['workCalPmRInsPeriodCommonDTO.pmInsSchedId'].value = "";
		maWoDailyPmiListForm.elements['workPmiCommonDTO.pminslistId'].value = "";
    	return sortColumn("maWoDailyPmiList", this, maWoDailyPmiListForm, "PMINSLISTID", ind, direction);
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
	if (maWoDailyPmiListForm.elements['maWoDailyCommonDTO.woDayListId'].value == '') return;
	
	var form = document.maWoDailyPmiListForm;	
	form.strutsAction.value = '<%=MaWoDailyPmiListAction.LIST_FIND %>';
	
	var url = contextPath + "/maWoDailyPmiList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maWoDailyPmiListForm), "PMINSLISTID", "Y");

}

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
  	//excelAction(myGrid);
  	maWoDailyPmiListForm.elements['workCalPmRInsPeriodCommonDTO.pmInsSchedId'].value = "";
	maWoDailyPmiListForm.elements['workPmiCommonDTO.pminslistId'].value = "";
	excelServerAction("maWoDailyPmiList", maWoDailyPmiListForm ); 
  } 
  /**
   * 작업결과 상세 열기
   */
  function goResult()
  { 
  	if(selectedWoId == "undefined" || selectedWoId == "") return;
  	
  	var pmInsListId = getValueById(myGrid, selectedWoId, "PMINSLISTID");
  	
  	if(pmInsListId == "undefined" || pmInsListId == "")
    {
    	alertMessage1('<bean:message key="MESSAGE.MSG0024"/>');
    	return;
    } 
  	
  	var woparam = "workPmiDetail";
	var url   = contextPath + "/"+woparam+".do";

	var popWidth = 1010;
	var popHeight = 640;

    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
    
    var param = "strutsAction=8001&workPmiCommonDTO.pminslistId="+ pmInsListId;
  
    openWindowWithPost(url, "WO_DETAIL", param, pos);
  }
  
  function goOpen()
  { 
  	if(selectedWoId == "undefined" || selectedWoId == "") return;
  	
  	var pmInsListId = getValueById(myGrid, selectedWoId, "PMINSLISTID");
  	
  	if(pmInsListId == "undefined" || pmInsListId == "")
    {
    	alertMessage1('<bean:message key="MESSAGE.MSG0024"/>');
    	return;
    } 
  	
  	var woparam = "workPmiDetail";
  	
  	goTabPage(woparam);
  	
  	/*
	var url   = contextPath + "/"+woparam+".do";

	var popWidth = 1010;
	var popHeight = 640;

    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
    
    var param = "strutsAction=8001&workPmiCommonDTO.pminslistId="+ pmInsListId;
  
    openWindowWithPost(url, "WO_DETAIL", param, pos);
    */
    
  }
  
  function goOpenAction()
  {
	  if(selectedWoId == "undefined" || selectedWoId == "") return;
	  	
	  	var pmInsListId = getValueById(myGrid, selectedWoId, "PMINSLISTID");
	  	
	  	if(pmInsListId == "undefined" || pmInsListId == "")
	    {
	    	alertMessage1('<bean:message key="MESSAGE.MSG0024"/>');
	    	return;
	    } 
	  	
	  var form = document.maWoDailyPmiListForm;
		
		var pmStatus = getValueById(myGrid, selectedWoId, "pmStatusCode");
		
		if(pmStatus == "P")
		{
			form.elements['workCalPmRInsPeriodCommonDTO.pmInsSchedId'].value = getValueById(myGrid, selectedWoId, 'pmInsSchedId');
			form.elements['strutsAction'] = '<%= WorkCalPmRInsPeriodDetailAction.MONTH_SCHED_DETAIL_INIT %>';
		}
		else
		{
			form.elements['workPmiCommonDTO.pminslistId'].value = getValueById(myGrid, selectedWoId, "pmInsListId");
			form.elements['strutsAction'] = '<%= WorkPmiDetailAction.WORK_PMI_DETAIL_INIT %>';
		}
	  
	  openQuickTabPage(FormQueryString(form), 'workPmiDetail'); 
  }

  function goTabPage(pageId)
  {
 	 tabValidationCheck(myGrid, pageId, "goTabPageAction");
  }

  function goTabPageAction(pageId, selectedWoId)
  {
	  var form = document.maWoDailyPmiListForm;
		
		var pmStatus = getValueById(myGrid, selectedWoId, "pmStatusCode");
		
		if(pmStatus == "P")
		{
			form.elements['workCalPmRInsPeriodCommonDTO.pmInsSchedId'].value = getValueById(myGrid, selectedWoId, 'pmInsSchedId');
			goCommonTabPage(form, <%= WorkCalPmRInsPeriodDetailAction.MONTH_SCHED_DETAIL_INIT %>, pageId, beforePageId);
		}
		else
		{
			form.elements['workPmiCommonDTO.pminslistId'].value = getValueById(myGrid, selectedWoId, "pmInsListId");
			goCommonTabPage(form, <%= WorkPmiDetailAction.WORK_PMI_DETAIL_INIT %>, pageId, beforePageId);
		}

		beforePageId = pageId; 
  }
  

  /** 
   * 수정된 그리드 1건을 다시 조회한다.
   */
  function findGridRow(_pminslistId)
  {
	maWoDailyPmiListForm.elements['maWoDailyDetailDTO.pminslistId'].value = _pminslistId;
  	findGridList('ReloadRow');
  	maWoDailyPmiListForm.elements['maWoDailyDetailDTO.pminslistId'].value = "";
  }
  
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maWoDailyPmiList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maWoDailyCommonDTO.woDayListId"/><!-- Key -->
<html:hidden property="maWoDailyDetailDTO.woDayListId"/><!-- Key -->
<html:hidden property="maWoDailyDetailDTO.woDate"/>
<html:hidden property="maWoDailyDetailDTO.woDeptId"/>
<html:hidden property="maWoDailyDetailDTO.startFdate"/>
<html:hidden property="maWoDailyDetailDTO.startFtime"/>
<html:hidden property="maWoDailyDetailDTO.startEdate"/>
<html:hidden property="maWoDailyDetailDTO.startEtime"/>
<html:hidden property="maWoDailyDetailDTO.plant"/>
<html:hidden property="maWoDailyDetailDTO.wkCtrId"/>
<html:hidden property="maWoDailyDetailDTO.equipId"/>
<html:hidden property="maWoDailyDetailDTO.pminslistId"/>
<html:hidden property="workCalPmRInsPeriodCommonDTO.pmInsSchedId"/>
<html:hidden property="workPmiCommonDTO.pminslistId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
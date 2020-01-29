<%--===========================================================================
작업계획승인-작업계획 
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.planappr.action.WorkPlanApprWorkListAction" %>
<%@ page import="dream.work.list.action.WoPlanDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html> 
<head>
<!-- 작업계획목록 -->
<title><bean:message key='LABEL.pmwork'/></title>
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
	myGrid.enableEditEvents(true,false,false);
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		workPlanApprWorkListForm.elements['workPlanApprCommonDTO.woPlanApprId'].value = "";
        return sortColumn("workPlanApprWorkList", this, workPlanApprWorkListForm, "WOPLANID", ind, direction);
    });
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		//goOpen();
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	myGrid.setDateFormat("%Y-%m-%d");

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	workPlanApprWorkListForm.elements['workPlanApprDetailDTO.startDate'].value
		= parent.workPlanApprDetailForm.elements['workPlanApprDetailDTO.startDate'].value;
	workPlanApprWorkListForm.elements['workPlanApprDetailDTO.endDate'].value
		= parent.workPlanApprDetailForm.elements['workPlanApprDetailDTO.endDate'].value;
	workPlanApprWorkListForm.elements['workPlanApprDetailDTO.deptId'].value
		= parent.workPlanApprDetailForm.elements['workPlanApprDetailDTO.deptId'].value;
	
    findGridList('Search');   
}

function findGridList(sheetAction)
{
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (workPlanApprWorkListForm.elements['workPlanApprCommonDTO.woPlanApprId'].value == '') return;

	var form = document.workPlanApprWorkListForm;	
	form.strutsAction.value = '<%=WorkPlanApprWorkListAction.LIST_FIND %>';
	
	var url = contextPath + "/workPlanApprWorkList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workPlanApprWorkListForm), "WOPLANID", "Y");

}
 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
  	//excelAction(myGrid);
	workPlanApprWorkListForm.elements['workPlanApprCommonDTO.woPlanApprId'].value = "";
    excelServerAction("workPlanApprWorkList", workPlanApprWorkListForm );  
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
	workPlanApprWorkListForm.elements['woPlanCommonDTO.wkOrId'].value = getValueById(myGrid, selectedId,'WKORID');
  	
  	goCommonTabPage(workPlanApprWorkListForm, <%= WoPlanDetailAction.WO_PLAN_DETAIL_INIT %>, "woPlanDetail");
  }
  /**
   * 상세열기
   */
  function goOpen()
  {
	goTabPage('woPlanDetail');
  }

  function goOpenAction()
  {
      var selectedId=myGrid.getSelectedRowId();
      if(selectedId == null) return;
      
      workPlanApprWorkListForm.elements['woPlanCommonDTO.wkOrId'].value = getValueById(myGrid, selectedId,'WKORID');
      workPlanApprWorkListForm.elements['strutsAction'].value = '<%=WoPlanDetailAction.WO_PLAN_DETAIL_INIT%>';
      var pageId  = getValueById(myGrid, selectedId,'PARAM');
      openQuickTabPage(FormQueryString(workPlanApprWorkListForm), 'woPlanDetail');
  } 


//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workPlanApprWorkList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="workPlanApprCommonDTO.woPlanApprId"/><!-- Key -->
<html:hidden property="workPlanApprWorkListDTO.woPlanId"/><!-- Key -->
<html:hidden property="workPlanApprDetailDTO.startDate"/>
<html:hidden property="workPlanApprDetailDTO.endDate"/>
<html:hidden property="workPlanApprDetailDTO.deptId" />
<html:hidden property="woPlanCommonDTO.wkOrId" />
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:200px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
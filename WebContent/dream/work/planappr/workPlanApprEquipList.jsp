<%--===========================================================================
작업계획승인-작업계획 
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.planappr.action.WorkPlanApprEquipListAction" %>
<%@ page import="dream.asset.list.action.MaEqMstrDetailAction"%>
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
		workPlanApprEquipListForm.elements['workPlanApprCommonDTO.woPlanApprId'].value = "";
        return sortColumn("workPlanApprEquipList", this, workPlanApprEquipListForm, "EQUIPID", ind, direction);
    });
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	workPlanApprEquipListForm.elements['workPlanApprDetailDTO.startDate'].value
		= parent.workPlanApprDetailForm.elements['workPlanApprDetailDTO.startDate'].value;
	workPlanApprEquipListForm.elements['workPlanApprDetailDTO.endDate'].value
		= parent.workPlanApprDetailForm.elements['workPlanApprDetailDTO.endDate'].value;
	workPlanApprEquipListForm.elements['workPlanApprDetailDTO.deptId'].value
		= parent.workPlanApprDetailForm.elements['workPlanApprDetailDTO.deptId'].value;
	
    findGridList('Search');   
}

function findGridList(sheetAction)
{
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (workPlanApprEquipListForm.elements['workPlanApprCommonDTO.woPlanApprId'].value == '') return;

	var form = document.workPlanApprEquipListForm;	
	form.strutsAction.value = '<%=WorkPlanApprEquipListAction.LIST_FIND %>';
	
	var url = contextPath + "/workPlanApprEquipList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workPlanApprEquipListForm), "EQUIPID", "Y");

}
 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
  	//excelAction(myGrid);
	workPlanApprEquipListForm.elements['workPlanApprCommonDTO.woPlanApprId'].value = "";
    excelServerAction("workPlanApprEquipList", workPlanApprEquipListForm );  
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
	workPlanApprEquipListForm.elements['maEqMstrCommonDTO.equipId'].value = getValueById(myGrid, selectedId,'EQUIPID');
	workPlanApprEquipListForm.elements['maEqMstrCommonDTO.eqCtgType'].value = "TL";
  	
  	goCommonTabPage(workPlanApprEquipListForm, <%= MaEqMstrDetailAction.EQ_MSTR_DETAIL_INIT %>, "maEqToolMstrDetail");
  }
  /**
   * 상세열기
   */
  function goOpen()
  {
	goTabPage('maEqToolMstrDetail');
  }

  function goOpenAction()
  {
      var selectedId=myGrid.getSelectedRowId();
      if(selectedId == null) return;
      
      workPlanApprEquipListForm.elements['maEqMstrCommonDTO.equipId'].value = getValueById(myGrid, selectedId,'EQUIPID');
      workPlanApprEquipListForm.elements['maEqMstrCommonDTO.eqCtgType'].value = "TL";
      
      workPlanApprEquipListForm.elements['strutsAction'].value = '<%=MaEqMstrDetailAction.EQ_MSTR_DETAIL_INIT%>';
      var pageId  = getValueById(myGrid, selectedId,'PARAM');
      openQuickTabPage(FormQueryString(workPlanApprEquipListForm), 'maEqToolMstrDetail');
  } 


//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workPlanApprEquipList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="workPlanApprCommonDTO.woPlanApprId"/><!-- Key -->
<html:hidden property="workPlanApprEquipListDTO.equipId"/><!-- Key -->
<html:hidden property="workPlanApprDetailDTO.startDate"/>
<html:hidden property="workPlanApprDetailDTO.endDate"/>
<html:hidden property="workPlanApprDetailDTO.deptId" />
<html:hidden property="maEqMstrCommonDTO.equipId" />
<html:hidden property="maEqMstrCommonDTO.eqCtgType" />
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:200px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
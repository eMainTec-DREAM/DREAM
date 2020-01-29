<%--===========================================================================
예방점검계획승인-점검결과 
author  kim21017
version $Id: workCalPmInsApprRsltList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.cal.pminsappr.action.WorkCalPmInsApprRsltListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html> 
<head>
<!-- 점검결과 -->
<title><bean:message key='TAB.workCalPmInsApprRsltList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
function loadPage() 
{
	initGrid();
}

var myGrid, proGrid;

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');

	myGrid.enableSmartRendering(true,500);
	myGrid.enableEditEvents(true,false,false);
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	return sortColumn("workCalPmInsApprRsltList", this, workCalPmInsApprRsltListForm, "WORKDATE", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	workCalPmInsApprRsltListForm.elements['workCalPmInsApprDetailDTO.startDate'].value
		= parent.workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.startDate'].value;
	workCalPmInsApprRsltListForm.elements['workCalPmInsApprDetailDTO.endDate'].value
		= parent.workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.endDate'].value;
	workCalPmInsApprRsltListForm.elements['workCalPmInsApprDetailDTO.deptId'].value
		= parent.workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.deptId'].value;
	
    findGridList('Search');   
}

function findGridList(sheetAction)
{
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (workCalPmInsApprRsltListForm.elements['workCalPmInsApprCommonDTO.pmInsSchedApprId'].value == '') return;

	var form = document.workCalPmInsApprRsltListForm;	
	form.strutsAction.value = '<%=WorkCalPmInsApprRsltListAction.LIST_FIND %>';
	
	var url = contextPath + "/workCalPmInsApprRsltList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workCalPmInsApprRsltListForm), "WORKDATE", "Y");

}
 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	excelServerAction("workCalPmInsApprRsltList", workCalPmInsApprRsltListForm );  
  } 
 
 /**
  * 저장
  */
  function goSave()
 {
	  proGrid.sendData();
 }
  
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workCalPmInsApprRsltList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="workCalPmInsApprCommonDTO.pmInsSchedApprId"/><!-- Key -->
<html:hidden property="workCalPmInsApprRsltListDTO.pmInsSchedId"/><!-- Key -->
<html:hidden property="workCalPmInsApprDetailDTO.pminsschedapprType" />
<html:hidden property="workCalPmInsApprDetailDTO.startDate"/>
<html:hidden property="workCalPmInsApprDetailDTO.endDate"/>
<html:hidden property="workCalPmInsApprDetailDTO.deptId" />
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:200px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
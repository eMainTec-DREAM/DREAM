<%--===========================================================================
예방점검계획승인-점검설비 
author  kim21017
version $Id: workCalPmInsApprWorkList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.cal.pminsappr.action.WorkCalPmInsApprWorkListAction" %>
<%@ page import="dream.work.cal.pminsappr.action.WorkCalPmInsApprDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html> 
<head>
<!-- 점검설비 -->
<title><bean:message key='TAB.workCalPmInsApprWorkList'/></title>
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
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	return sortColumn("workCalPmInsApprWorkList", this, workCalPmInsApprWorkListForm, "PMINSSCHEDID", ind, direction);
	});

	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	workCalPmInsApprWorkListForm.elements['workCalPmInsApprDetailDTO.startDate'].value
		= parent.workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.startDate'].value;
	workCalPmInsApprWorkListForm.elements['workCalPmInsApprDetailDTO.endDate'].value
		= parent.workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.endDate'].value;
	workCalPmInsApprWorkListForm.elements['workCalPmInsApprDetailDTO.deptId'].value
		= parent.workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.deptId'].value;
	
    findGridList('Search');   
}

function findGridList(sheetAction)
{
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (workCalPmInsApprWorkListForm.elements['workCalPmInsApprCommonDTO.pmInsSchedApprId'].value == '') return;

	var form = document.workCalPmInsApprWorkListForm;	
	form.strutsAction.value = '<%=WorkCalPmInsApprWorkListAction.LIST_FIND %>';
	
	var url = contextPath + "/workCalPmInsApprWorkList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workCalPmInsApprWorkListForm), "PMINSSCHEDID", "Y");

}
 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	excelServerAction("workCalPmInsApprWorkList", workCalPmInsApprWorkListForm );  
  } 
 
 /**
  * 저장
  */
  function goSave()
 {
	  proGrid.sendData();
 }

 /*
  * 기준서 보기
  */
  function goPmstd()
  {
	var selectedId=myGrid.getSelectedRowId();
  	if(selectedId == "undefined" || selectedId == "") return;

  	//strutsAction[0]=1001
  	//maPmMstrCommonDTO.pmId[0]=4522
  	var fileName = getValueById(myGrid, selectedId, "PARAM");
  	var pmId     = getValueById(myGrid, selectedId, "PMID");

  	var url   = contextPath + "/"+fileName+".do";

  	var popWidth = 1010;
  	var popHeight = 640;

      // pop up이 중앙에 위치하게 한다.
      var TopPosition  = (screen.height/2 - popHeight/2);
      var LeftPosition = (screen.width/2 - popWidth/2);

      var pos = "width=" + popWidth + ",height=" + popHeight + "" +
                ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
      pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";

      var param = "strutsAction="+ '<%=WorkCalPmInsApprDetailAction.DETAIL_INIT %>'
      			+ "&maPmMstrCommonDTO.pmId="+ pmId;

      openWindowWithPost(url, "PM_DETAIL", param, pos);
  }
  
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workCalPmInsApprWorkList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="workCalPmInsApprCommonDTO.pmInsSchedApprId"/><!-- Key -->
<html:hidden property="workCalPmInsApprWorkListDTO.pmInsSchedId"/><!-- Key -->
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
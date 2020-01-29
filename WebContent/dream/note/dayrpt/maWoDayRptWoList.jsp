<%--===========================================================================
업무일지 - W/O리스트
author  kim21017
version $Id: maWoDayRptWoList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.note.dayrpt.action.MaWoDayRptWoListAction" %> 
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- W/O리스트 -->
<title><bean:message key='TAB.maWoDayRptWoList'/></title>
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
var selectedWoId;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		selectedWoId = rowId;
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	return sortColumn("maWoDayRptWoList", this, maWoDayRptWoListForm, "WKORID", ind, direction);
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
	if (maWoDayRptWoListForm.elements['maWoDayRptDetailDTO.deptId'].value == '') return;
	if (maWoDayRptWoListForm.elements['maWoDayRptDetailDTO.workDate'].value == '') return;
	
	var form = document.maWoDayRptWoListForm;	
	form.strutsAction.value = '<%=MaWoDayRptWoListAction.LIST_FIND %>';
	
	var url = contextPath + "/maWoDayRptWoList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maWoDayRptWoListForm), "WKORID", "Y");

}

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	excelServerAction("maWoDayRptWoList", maWoDayRptWoListForm );  
  } 
  /**
   * w/o 상세 열기
   */
  function goWo()
  { 
  	if(selectedWoId == "undefined" || selectedWoId == "") return;
  	
  	var wkOrId = getValueById(myGrid, selectedWoId, "WKORID");
  	var param = getValueById(myGrid, selectedWoId, "PARAM");
  	
	var url   = contextPath + "/"+param+".do";

	var popWidth = 1010;
	var popHeight = 640;

    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
    
    var param = "strutsAction=8001&maWoResultMstrCommonDTO.wkOrId="+ wkOrId;
  
    openWindowWithPost(url, "WO_DETAIL", param, pos);
  }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maWoDayRptWoList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maWoDayRptDetailDTO.deptId"/><!-- Key -->
<html:hidden property="maWoDayRptDetailDTO.workDate"/><!-- Key -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
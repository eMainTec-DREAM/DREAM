<%--===========================================================================
작업요청서(투자요청)- 처리사항 (목록)
author  js.lee
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.req.work.action.ReqWorkResListAction" %>
<%@ page import="dream.req.work.action.ReqInvWorkResDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 처리사항 -->
<title><bean:message key='TAB.reqWorkResList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->

<script language="javascript">
<!--//
var beforePageId= '';
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
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")});
	myGrid.init();
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		var invtlistId = getValueById(myGrid, rowId,'INVTLISTID');
		reqWorkResListForm.elements['reqWorkResListDTO.invtlistId'].value = invtlistId;
		if(invtlistId != "") goTabPage("reqInvWorkResDetail");
		
	});
	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	reqWorkResListForm.elements['reqWorkResListDTO.woReqResId'].value = "";

    findGridList('Search');
}

function findGridList(sheetAction)
{
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (reqWorkResListForm.elements['reqWorkCommonDTO.woReqId'].value == '') return;

	var form = document.reqWorkResListForm;
	form.strutsAction.value = '<%=ReqWorkResListAction.LIST_FIND %>';

	var url = contextPath + "/reqInvWorkResList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(reqWorkResListForm), "WOREQRESID", "Y");

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
	var form = document.reqWorkResListForm;

	form.elements['reqWorkResListDTO.woReqResId'].value = getValueById(myGrid, selectedId,'WOREQRESID');
	goCommonTabPage(form, <%= ReqInvWorkResDetailAction.DETAIL_INIT %>, pageId, beforePageId);

	beforePageId = pageId;
}
 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
  	excelAction(myGrid);
  }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/reqInvWorkResList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="reqWorkCommonDTO.woReqId"/><!-- Key -->
<html:hidden property="reqWorkCommonDTO.wkorId"/>
<html:hidden property="reqWorkResListDTO.invtlistId"/>
<html:hidden property="reqWorkResListDTO.woReqResId"/><!-- Key -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:200px; background-color:white;"></div>
           </div>
 	</div>

</html:form>
</body>
</html>
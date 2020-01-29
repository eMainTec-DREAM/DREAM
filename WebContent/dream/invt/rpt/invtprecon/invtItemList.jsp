<%--===========================================================================
평가점수
author  youngjoo38
version $Id: invtItemList.jsp,v 1.1 2017/08/28 10:17:27 youngjoo38 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.invt.rpt.invtprecon.action.InvtRptInvtPreConListAction" %>
<%@ page import="dream.invt.rpt.invtprecon.action.InvtItemListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 투자항목 -->
<title><bean:message key='TAB.invtItem'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var myGrid;

function loadPage() 
{
    initGrid();
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
        //goOpen();
    });
/*     myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
        return sortColumn("invtItemList", this, invtItemListForm, "INVTLISTID", ind, direction);
    }); */
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	//페이지id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (invtItemListForm.elements['invtRptInvtPreConCommonDTO.deptId'].value == ''
			|| invtItemListForm.elements['invtRptInvtPreConCommonDTO.year'].value == '') return;
	
	var url = contextPath + "/invtItemList.do";
	
	invtItemListForm.elements['strutsAction'].value = '<%=InvtItemListAction.LIST_FIND%>';
	
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(invtItemListForm), "INVTLISTID", "Y");

}

function goSearch()
{
    findGridList('Search');
}


/**
 * Excel Export
 */
function goExcel()
{
    excelServerAction("invtItemList", invtItemListForm );  
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/invtItemList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="invtRptInvtPreConCommonDTO.deptId"/><!-- Key -->
<html:hidden property="invtRptInvtPreConCommonDTO.year"/><!-- Key -->
<html:hidden property="invtItemListDTO.invtListId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
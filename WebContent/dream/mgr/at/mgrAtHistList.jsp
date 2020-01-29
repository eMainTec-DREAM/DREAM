<%--===========================================================================
Audit Trail History
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.at.action.MgrAtHistListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- Audit Trail History -->
<title><bean:message key='LABEL.changedValue'/></title>
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
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
        mgrAtHistListForm.elements['mgrAtHistListDTO.tracelogDtlId'].value = "";
        return sortColumn("mgrAtHistList", this, mgrAtHistListForm, "TRACELOGID", ind, direction);
    });
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	//페이지id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	//if (mgrAtHistListForm.elements['mgrAtCommonDTO.tracelogId'].value == '') return;

	mgrAtHistListForm.elements['mgrAtHistListDTO.tracelogDtlId'].value = "";

	var url = contextPath + "/mgrAtHistList.do";
	
	mgrAtHistListForm.elements['strutsAction'].value = '<%=MgrAtHistListAction.LIST_FIND%>';
	
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mgrAtHistListForm), "TRACELOGDTLID", "Y");

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
	mgrAtHistListForm.elements['mgrAtHistListDTO.tracelogDtlId'].value = "";
    excelServerAction("mgrAtHistList", mgrAtHistListForm );  
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/mgrAtHistList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="mgrAtCommonDTO.tracelogId"/><!-- Key -->
<html:hidden property="mgrAtCommonDTO.fileName"/>
<html:hidden property="mgrAtHistListDTO.tracelogId"/><!-- Key -->
<html:hidden property="mgrAtHistListDTO.tracelogDtlId"/><!-- Key -->
<html:hidden property="mgrAtHistListDTO.fieldId" />
<html:hidden property="mgrAtHistListDTO.fileName" />
<html:hidden property="mgrAtHistListDTO.objectId" />
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
<%--===========================================================================
변경사항
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.audtrail.action.MgrAudTrailDtlListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 변경사항 -->
<title><bean:message key='LABEL.changedValue'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var myGrid, assEvalDescAc;

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
        mgrAudTrailDtlListForm.elements['mgrAudTrailDtlListDTO.tracelogDtlId'].value = "";
        return sortColumn("mgrAudTrailDtlList", this, mgrAudTrailDtlListForm, "TRACELOGID", ind, direction);
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
	if (mgrAudTrailDtlListForm.elements['mgrAudTrailCommonDTO.tracelogId'].value == '') return;

	mgrAudTrailDtlListForm.elements['mgrAudTrailDtlListDTO.tracelogDtlId'].value = "";

	var url = contextPath + "/mgrAudTrailDtlList.do";
	
	mgrAudTrailDtlListForm.elements['strutsAction'].value = '<%=MgrAudTrailDtlListAction.LIST_FIND%>';
	
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mgrAudTrailDtlListForm), "TRACELOGDTLID", "Y");

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
	mgrAudTrailDtlListForm.elements['mgrAudTrailDtlListDTO.tracelogDtlId'].value = "";
    excelServerAction("mgrAudTrailDtlList", mgrAudTrailDtlListForm );  
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/mgrAudTrailDtlList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="mgrAudTrailCommonDTO.tracelogId"/><!-- Key -->
<html:hidden property="mgrAudTrailCommonDTO.fileName"/>
<html:hidden property="mgrAudTrailCommonDTO.objectId"/>
<html:hidden property="mgrAudTrailDtlListDTO.tracelogId"/><!-- Key -->
<html:hidden property="mgrAudTrailDtlListDTO.tracelogDtlId"/><!-- Key -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
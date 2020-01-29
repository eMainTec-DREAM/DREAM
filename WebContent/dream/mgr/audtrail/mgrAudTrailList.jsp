<%--===========================================================================
Audit Trail
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.audtrail.action.MgrAudTrailListAction" %>
<%@ page import="dream.mgr.audtrail.action.MgrAudTrailDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page import="common.bean.User"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!-- Audit Trail -->
<title><bean:message key='MENU.AUDTRAIL'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;

var beforePageId = 'mgrAudTrailDetail';
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
		goOpen(rowId);
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	mgrAudTrailListForm.elements['mgrAudTrailCommonDTO.revisionhistId'].value = "";
        return sortColumn("mgrAudTrailList", this, mgrAudTrailListForm, "REVISIONHISTID", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox");}); 
	myGrid.init();

	setHeader(myGrid, "gridbox", "goSearch"); // grid, grid id
}

/**
* Tab 이동시 호출
*/
function goTabPage(pageId)
{
   //goClose(beforePageId);
   tabValidationCheck(myGrid, pageId, "goTabPageAction");
}

function goTabPageAction(pageId, selectedId)
{
	var form = document.mgrAudTrailListForm;

	form.elements['mgrAudTrailCommonDTO.tracelogId'].value = getValueById(myGrid, selectedId,'TRACELOGID');
	goCommonTabPage(mgrAudTrailListForm, '<%=MgrAudTrailDetailAction.DETAIL_FIND%>', pageId, beforePageId);
	
	beforePageId = pageId;
}

/**
 * 상세열기
 */
 function goOpen(rowId){
	goTabPage("mgrAudTrailDetail");	
}

/**
 * Open 버튼 클릭
 */
function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
 	if(selectedId == null) return;

 	var form = document.mgrAudTrailListForm;

	form.elements['mgrAudTrailCommonDTO.tracelogId'].value = getValueById(myGrid, selectedId,'TRACELOGID');
	form.elements['strutsAction'].value = '<%=MgrAudTrailDetailAction.DETAIL_FIND%>';
 	var pageId = "mgrAudTrailDetail";
 	
 	openQuickTabPage(FormQueryString(form), pageId); 
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/mgrAudTrailList.do";

    mgrAudTrailListForm.elements['strutsAction'].value = '<%=MgrAudTrailListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mgrAudTrailListForm), "REVISIONHISTID", "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_revisionhistId)
{
	mgrAudTrailListForm.elements['mgrAudTrailCommonDTO.revisionhistId'].value = _revisionhistId;
	findGridList('ReloadRow');
	mgrAudTrailListForm.elements['mgrAudTrailCommonDTO.revisionhistId'].value = "";
}

/**
 * 엑셀 다운.
 */
 function goExcel()
 {
     mgrAudTrailListForm.elements['mgrAudTrailCommonDTO.revisionhistId'].value = "";
	 excelServerAction("mgrAudTrailList", mgrAudTrailListForm );  
 }

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
    findGridList('Search');   
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/mgrAudTrailList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="mgrAudTrailCommonDTO.tracelogId"/>
<html:hidden property="mgrAudTrailCommonDTO.objectId"/>
<html:hidden property="mgrAudTrailCommonDTO.fileName"/>
	<div class="section_wrap">
		<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.List"/></div>
			</div>
			<div class="function_box list">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>
				<div class="fb_group2">
				</div>
			</div>
		</div><!--sheader_box end-->
		<div class="article_box">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:210px; background-color:white;"></div>
			</div>
		</div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>
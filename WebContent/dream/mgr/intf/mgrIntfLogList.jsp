<%--===========================================================================
Interface Log List
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.intf.action.MgrIntfLogListAction" %>
<%@ page import="dream.mgr.intf.action.MgrIntfLogDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 인터페이스 로그 -->
<title><bean:message key='MENU.INTFLOG'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
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
		goOpen();
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	mgrIntfLogListForm.elements['mgrIntfLogListDTO.intfLogId'].value = "";
    	return sortColumn("mgrIntfLogList", this, mgrIntfLogListForm, "INTFLOGID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/mgrIntfLogList.do";
    mgrIntfLogListForm.elements['strutsAction'].value = '<%=MgrIntfLogListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mgrIntfLogListForm), "INTFLOGID","Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_intfLogId)
{
	mgrIntfLogListForm.elements['mgrIntfLogListDTO.intfLogId'].value = _intfLogId;
	findGridList('ReloadRow');
	mgrIntfLogListForm.elements['mgrIntfLogListDTO.intfLogId'].value = "";
}

function goSearch()
{
	mgrIntfLogListForm.elements['mgrIntfLogListDTO.intfLogId'].value = "";	// 검색시 Tab 이동Key Clear
	findGridList('Search');
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
	mgrIntfLogListForm.elements['mgrIntfLogListDTO.intfLogId'].value =  getValueById(myGrid, selectedId,'INTFLOGID');  
	goCommonTabPage(mgrIntfLogListForm, <%= MgrIntfLogDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('mgrIntfLogDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    mgrIntfLogListForm.elements['mgrIntfLogListDTO.intfLogId'].value = getValueById(myGrid, selectedId, 'intfLogId');
    mgrIntfLogListForm.elements['strutsAction'].value = '<%=MgrIntfLogDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(mgrIntfLogListForm), 'mgrIntfLogDetail'); 
} 


/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'INTFLOGID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    mgrIntfLogListForm.strutsAction.value = '<%=MgrIntfLogListAction.LIST_DELETE%>';
    var url = contextPath + "/mgrIntfLogList.do";
    
    $.post(url,FormQueryString(mgrIntfLogListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('mgrIntfLogDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	mgrIntfLogListForm.elements['mgrIntfLogListDTO.intfLogId'].value = "";
	excelServerAction("mgrIntfLogList", mgrIntfLogListForm );  
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/mgrIntfLogList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="mgrIntfCommonDTO.intfId"/>
<html:hidden property="mgrIntfLogListDTO.intfId"/>
<html:hidden property="mgrIntfLogListDTO.intfLogId"/><!-- Key -->

		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
		
</html:form> 
</body>
</html>
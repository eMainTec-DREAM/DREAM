<%--===========================================================================
평가항목
author  kim21017
version $Id: assBasePointList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.ass.base.action.AssBasePointListAction" %>
<%@ page import="dream.ass.base.action.AssBasePointDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 평가항목 -->
<title><bean:message key='TAB.assBasePointList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

/** 자동완성 변수 */

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
		assBasePointListForm.elements['assBasePointListDTO.assBasePointId'].value = "";
    	return sortColumn("assBasePointList", this, assBasePointListForm, "ASSBASEPOINTID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (assBasePointListForm.elements['assBaseCommonDTO.assBaseListId'].value == '') return;
    var url = contextPath + "/assBasePointList.do";
    assBasePointListForm.elements['strutsAction'].value = '<%=AssBasePointListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(assBasePointListForm), "ASSBASEPOINTID","Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_assBasePointId)
{
	assBasePointListForm.elements['assBasePointListDTO.assBasePointId'].value = _assBasePointId;
	findGridList('ReloadRow');
	assBasePointListForm.elements['assBasePointListDTO.assBasePointId'].value = "";
}

function goSearch()
{
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
	assBasePointListForm.elements['assBasePointListDTO.assBasePointId'].value =  getValueById(myGrid, selectedId,'ASSBASEPOINTID');  
	goCommonTabPage(assBasePointListForm, <%= AssBasePointDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('assBasePointDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    assBasePointListForm.elements['assBasePointListDTO.assBasePointId'].value =  getValueById(myGrid, selectedId,'ASSBASEPOINTID');  
    assBasePointListForm.elements['strutsAction'].value = '<%=AssBasePointDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(assBasePointListForm), 'assBasePointDetail'); 
} 
/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "assBasePointDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	assBasePointListForm.elements['assBasePointListDTO.assBasePointId'].value = "";
    goCommonTabPage(assBasePointListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'ASSBASEPOINTID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    assBasePointListForm.strutsAction.value = '<%=AssBasePointListAction.LIST_DELETE%>';
    var url = contextPath + "/assBasePointList.do";
    
    $.post(url,FormQueryString(assBasePointListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('assBasePointDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	assBasePointListForm.elements['assBasePointListDTO.assBasePointId'].value = "";
	excelServerAction("assBasePointList", assBasePointListForm );  
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = assBasePointListForm.elements['assBasePointListDTO.assBasePointId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assBasePointList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="assBaseCommonDTO.assBaseListId"/><!-- Key -->
<html:hidden property="assBasePointListDTO.assBasePointId"/><!-- Key -->
    <!-- searchbox 박스 Line -->

	<div class="article_box" id="listBox">
		<div class="grid_area">
			<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
		</div>
	</div>

</html:form> 
</body>
</html>
<%--===========================================================================
사용량 항목  List
author  js.lee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.pm.list.action.WorkPmPointUInsListAction" %>
<%@ page import="dream.work.pm.list.action.WorkPmPointUInsDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 에너지측정 Point -->
<title><bean:message key='PAGE.workPmPointUInsList'/></title>
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
 		workPmPointUInsListForm.elements['maPmMstrCommonDTO.pmPointId'].value = "";
        return sortColumn("workPmPointUInsList", this, workPmPointUInsListForm, "PMPOINTID", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")});
	myGrid.init();
 
	setHeader(myGrid, "gridbox"); // grid, grid id
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (workPmPointUInsListForm.elements['maPmMstrCommonDTO.pmId'].value == '') return;
	
    var url = contextPath + "/workPmPointUInsList.do";
    workPmPointUInsListForm.elements['strutsAction'].value = '<%=WorkPmPointUInsListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workPmPointUInsListForm), "PMPOINTID","Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pmPointId)
{
	workPmPointUInsListForm.elements['maPmMstrCommonDTO.pmPointId'].value = _pmPointId;
	findGridList('ReloadRow');
	workPmPointUInsListForm.elements['maPmMstrCommonDTO.pmPointId'].value = "";
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
	var form = document.workPmPointUInsListForm;
	 
	form.elements['maPmMstrCommonDTO.pmPointId'].value = getValueById(myGrid, selectedId,'PMPOINTID');
	goCommonTabPage(form, <%= WorkPmPointUInsDetailAction.DETAIL_INIT %>, pageId);
	
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('workPmPointUInsDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    workPmPointUInsListForm.elements['maPmMstrCommonDTO.pmPointId'].value = getValueById(myGrid, selectedId,'PMPOINTID');
    workPmPointUInsListForm.elements['strutsAction'].value = '<%=WorkPmPointUInsDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(workPmPointUInsListForm), 'workPmPointUInsDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "workPmPointUInsDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	workPmPointUInsListForm.elements['maPmMstrCommonDTO.pmPointId'].value = "";
    goCommonTabPage(workPmPointUInsListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'pmPointId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    workPmPointUInsListForm.strutsAction.value = '<%=WorkPmPointUInsListAction.LIST_DELETE%>';
    var url = contextPath + "/workPmPointUInsList.do";
    
    $.post(url,FormQueryString(workPmPointUInsListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('workPmPointUInsDetail', this);
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	workPmPointUInsListForm.elements['maPmMstrCommonDTO.pmPointId'].value = "";
	excelServerAction("workPmPointUInsList", workPmPointUInsListForm);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/workPmPointUInsList.do">
		<html:hidden property="strutsAction"/>
		<html:hidden property="maPmMstrCommonDTO.pmId" />
		<html:hidden property="maPmMstrCommonDTO.pmPointId" />
		<html:hidden property="workPmPointUInsDetailDTO.pmPointId"/>
		
		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
		
</html:form> 
</body>
</html>
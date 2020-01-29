<%--===========================================================================
부품창고 담당자 List
author  sy.yang
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.ptwh.action.MgrPtWhEmpListAction" %>
<%@ page import="dream.mgr.ptwh.action.MgrPtWhEmpDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 담당자 -->
<title><bean:message key='LABEL.manager'/></title>
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
		mgrPtWhEmpListForm.elements['mgrPtWhEmpListDTO.whUserId'].value = "";
    	return sortColumn("mgrPtWhEmpList", this, mgrPtWhEmpListForm, "whUserId", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	
	setHeader(myGrid, "gridbox"); // grid, grid id
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/mgrPtWhEmpList.do";
    mgrPtWhEmpListForm.elements['strutsAction'].value = '<%=MgrPtWhEmpListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mgrPtWhEmpListForm), "whUserId","Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_whUserId)
{
	mgrPtWhEmpListForm.elements['mgrPtWhEmpListDTO.whUserId'].value = _whUserId;
	findGridList('ReloadRow');
	mgrPtWhEmpListForm.elements['mgrPtWhEmpListDTO.whUserId'].value = "";
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
	mgrPtWhEmpListForm.elements['mgrPtWhEmpListDTO.wcodeId'].value = mgrPtWhEmpListForm.elements['mgrPtWhCommonDTO.wcodeId'].value;
    mgrPtWhEmpListForm.elements['mgrPtWhEmpListDTO.whUserId'].value =  getValueById(myGrid, selectedId,'whUserId');
	goCommonTabPage(mgrPtWhEmpListForm, <%= MgrPtWhEmpDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('mgrPtWhEmpDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    mgrPtWhEmpListForm.elements['mgrPtWhEmpListDTO.wcodeId'].value = mgrPtWhEmpListForm.elements['mgrPtWhCommonDTO.wcodeId'].value;
    mgrPtWhEmpListForm.elements['mgrPtWhEmpListDTO.whUserId'].value = getValueById(myGrid, selectedId, 'whUserId');
    mgrPtWhEmpListForm.elements['strutsAction'].value = '<%=MgrPtWhEmpDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(mgrPtWhEmpListForm), 'mgrPtWhEmpDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "mgrPtWhEmpDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	mgrPtWhEmpListForm.elements['mgrPtWhEmpListDTO.whUserId'].value = "";
    goCommonTabPage(mgrPtWhEmpListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'whUserId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    mgrPtWhEmpListForm.strutsAction.value = '<%=MgrPtWhEmpListAction.LIST_DELETE%>';
    var url = contextPath + "/mgrPtWhEmpList.do";
    
    $.post(url,FormQueryString(mgrPtWhEmpListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('mgrPtWhEmpDetail', this);
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	//excelAction(myGrid);
	mgrPtWhEmpListForm.elements['mgrPtWhEmpListDTO.whUserId'].value = "";
 	excelServerAction("mgrPtWhEmpList", mgrPtWhEmpListForm );
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/mgrPtWhEmpList.do">
		<html:hidden property="strutsAction"/>
		<html:hidden property="mgrPtWhCommonDTO.wcodeId" /><!-- Key -->
		
		<html:hidden property="mgrPtWhEmpListDTO.wcodeId"/><!-- Key -->
		<html:hidden property="mgrPtWhEmpListDTO.whUserId"/><!-- Key -->
		
		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
		
</html:form> 
</body>
</html>
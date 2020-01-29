<%--===========================================================================
부품창고 보관위치 List
author  cjscjs9
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.ptwh.action.MgrPtWhBinListAction" %>
<%@ page import="dream.mgr.ptwh.action.MgrPtWhBinDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
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
		mgrPtWhBinListForm.elements['mgrPtWhBinListDTO.ptBinListId'].value = "";
    	return sortColumn("mgrPtWhBinList", this, mgrPtWhBinListForm, "ptBinListId", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	
	setHeader(myGrid, "gridbox"); // grid, grid id
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/mgrPtWhBinList.do";
    mgrPtWhBinListForm.elements['strutsAction'].value = '<%=MgrPtWhBinListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mgrPtWhBinListForm), "ptBinListId","Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_ptBinListId)
{
	mgrPtWhBinListForm.elements['mgrPtWhBinListDTO.ptBinListId'].value = _ptBinListId;
	findGridList('ReloadRow');
	mgrPtWhBinListForm.elements['mgrPtWhBinListDTO.ptBinListId'].value = "";
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
	mgrPtWhBinListForm.elements['mgrPtWhBinListDTO.wcodeId'].value = mgrPtWhBinListForm.elements['mgrPtWhCommonDTO.wcodeId'].value;
    mgrPtWhBinListForm.elements['mgrPtWhBinListDTO.ptBinListId'].value =  getValueById(myGrid, selectedId,'ptBinListId');
	goCommonTabPage(mgrPtWhBinListForm, <%= MgrPtWhBinDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('mgrPtWhBinDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    mgrPtWhBinListForm.elements['mgrPtWhBinListDTO.wcodeId'].value = mgrPtWhBinListForm.elements['mgrPtWhCommonDTO.wcodeId'].value;
    mgrPtWhBinListForm.elements['mgrPtWhBinListDTO.ptBinListId'].value = getValueById(myGrid, selectedId, 'ptBinListId');
    mgrPtWhBinListForm.elements['strutsAction'].value = '<%=MgrPtWhBinDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(mgrPtWhBinListForm), 'mgrPtWhBinDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "mgrPtWhBinDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	mgrPtWhBinListForm.elements['mgrPtWhBinListDTO.ptBinListId'].value = "";
    goCommonTabPage(mgrPtWhBinListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'ptBinListId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    mgrPtWhBinListForm.strutsAction.value = '<%=MgrPtWhBinListAction.LIST_DELETE%>';
    var url = contextPath + "/mgrPtWhBinList.do";
    
    $.post(url,FormQueryString(mgrPtWhBinListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('mgrPtWhBinDetail', this);
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	//excelAction(myGrid);  
	mgrPtWhBinListForm.elements['mgrPtWhBinListDTO.ptBinListId'].value = "";
 	excelServerAction("mgrPtWhBinList", mgrPtWhBinListForm );
}

/**
 * 레포트 출력
 */
function goPrint()
{
	var selArray = getSelectRows(myGrid, 'ISDELCHECK', 'ptBinListId'); //Grid, check box column seq, pk column seq
	
	if(typeof selArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	mgrPtWhBinListForm.strutsAction.value = '<%=MgrPtWhBinListAction.LIST_QR_INSERT%>';
    var url = contextPath + "/mgrPtWhBinList.do";
	$.post(url,FormQueryString(mgrPtWhBinListForm)+selArray+"&fileName=ptWhBinBarcode" , function(_data){
		startReportCall();
   });
}

function startReportCall ()
{
	 reportCall('ptWhBinBarcode','ptWhBinBarcode', "<%=user.getCompNo()%>", "<%=user.getUserId()%>", 'ptWhBinBarcode');
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/mgrPtWhBinList.do">
		<html:hidden property="strutsAction"/>
		<html:hidden property="mgrPtWhCommonDTO.wcodeId" /><!-- Key -->
		
		<html:hidden property="mgrPtWhBinListDTO.wcodeId"/><!-- Key -->
		<html:hidden property="mgrPtWhBinListDTO.ptBinListId"/><!-- Key -->
		
		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
		
</html:form> 
</body>
</html>
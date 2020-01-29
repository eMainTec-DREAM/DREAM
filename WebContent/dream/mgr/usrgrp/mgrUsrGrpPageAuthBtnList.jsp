<%--===========================================================================
화면권한설정상세탭버튼권한목록
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.usrgrp.action.MgrUsrGrpPageAuthBtnAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 화면권한설정상세탭버튼권한목록 -->
<title><bean:message key='PAGE.mgrUsrGrpPageAuthBtnList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

function loadPage() 
{
    mgrUsrGrpPageAuthBtnForm.elements['mgrUsrGrpPageAuthBtnDTO.pageId'].value = mgrUsrGrpPageAuthBtnForm.elements['mgrUsrGrpPageAuthDTO.pageId'].value;
    mgrUsrGrpPageAuthBtnForm.elements['mgrUsrGrpPageAuthBtnDTO.usrgrpId'].value = mgrUsrGrpPageAuthBtnForm.elements['mgrUsrGrpPageAuthDTO.usrgrpId'].value;
    
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
    	mgrUsrGrpPageAuthBtnForm.elements['mgrUsrGrpPageAuthBtnDTO.pgbtnId'].value = "";
    	return sortColumn("mgrUsrGrpPageAuthBtnList", this, mgrUsrGrpPageAuthBtnForm, ["PGBTNID", "USRGRPID"], ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/mgrUsrGrpPageAuthBtnList.do";
    mgrUsrGrpPageAuthBtnForm.elements['strutsAction'].value = '<%=MgrUsrGrpPageAuthBtnAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mgrUsrGrpPageAuthBtnForm), ["PGBTNID", "USRGRPID"], "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pgbtnId)
{
	mgrUsrGrpPageAuthBtnForm.elements['mgrUsrGrpPageAuthBtnDTO.pgbtnId'].value = _pgbtnId;
	findGridList('ReloadRow');
	mgrUsrGrpPageAuthBtnForm.elements['mgrUsrGrpPageAuthBtnDTO.pgbtnId'].value = "";
}

function goSearch()
{
	mgrUsrGrpPageAuthBtnForm.elements['mgrUsrGrpPageAuthBtnDTO.pgbtnId'].value = "";
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
	mgrUsrGrpPageAuthBtnForm.elements['mgrUsrGrpPageAuthBtnDTO.pgbtnId'].value =  getValueById(myGrid, selectedId,'PGBTNID');
	goCommonTabPage(mgrUsrGrpPageAuthBtnForm, <%= MgrUsrGrpPageAuthBtnAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('mgrUsrGrpPageAuthBtnDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    mgrUsrGrpPageAuthBtnForm.elements['mgrUsrGrpPageAuthBtnDTO.pgbtnId'].value =  getValueById(myGrid, selectedId,'PGBTNID');
	mgrUsrGrpPageAuthBtnForm.elements['strutsAction'].value = '<%=MgrUsrGrpPageAuthBtnAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(mgrUsrGrpPageAuthBtnForm), 'mgrUsrGrpPageAuthBtnDetail'); 
}

/**
 * 권한부여
 */
function goInputauth()
{
    var delArray = getSelectedRows(myGrid, 'isDelCheck', 'PGBTNID', 'USRGRPID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    mgrUsrGrpPageAuthBtnForm.strutsAction.value = '<%=MgrUsrGrpPageAuthBtnAction.LIST_INPUT_AUTH%>';
    var url = contextPath + "/mgrUsrGrpPageAuthBtnList.do";
    
    $.post(url,FormQueryString(mgrUsrGrpPageAuthBtnForm)+delArray , function(_data){
    	afterInputauth();
    });
}

function afterInputauth()
{
    goClose('mgrUsrGrpPageAuthBtnDetail');
    goSearch();
    alertMessage1('<bean:message key="MESSAGE.MGS0235"/>');
}

/**
 * 권한제거
 */
function goDeleteauth()
{
    var delArray = getSelectedRows(myGrid, 'isDelCheck', 'PGBTNID', 'USRGRPID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    mgrUsrGrpPageAuthBtnForm.strutsAction.value = '<%=MgrUsrGrpPageAuthBtnAction.LIST_DELETE_AUTH%>';
    var url = contextPath + "/mgrUsrGrpPageAuthBtnList.do";
    
    $.post(url,FormQueryString(mgrUsrGrpPageAuthBtnForm)+delArray , function(_data){
    	afterDeleteauth();
    });
}

function afterDeleteauth()
{
    goClose('mgrUsrGrpPageAuthBtnDetail');
    goSearch();
    alertMessage1('<bean:message key="MESSAGE.MGS0235"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	mgrUsrGrpPageAuthBtnForm.elements['mgrUsrGrpPageAuthBtnDTO.pgbtnId'].value = "";
	excelServerAction("mgrUsrGrpPageAuthBtnList", mgrUsrGrpPageAuthBtnForm );  
}

//-->
</script>


</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/mgrUsrGrpPageAuthBtnList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="mgrUsrGrpPageAuthDTO.pageId"/><!-- Key -->
<html:hidden property="mgrUsrGrpPageAuthDTO.usrgrpId"/><!-- Key -->

<html:hidden property="mgrUsrGrpPageAuthBtnDTO.pageId"/><!-- Key -->
<html:hidden property="mgrUsrGrpPageAuthBtnDTO.pgbtnId"/><!-- Key -->
<html:hidden property="mgrUsrGrpPageAuthBtnDTO.usrgrpId"/><!-- Key -->
<html:hidden property="mgrUsrGrpPageAuthBtnDTO.ugpgbtnauId"/>

    <div class="section_wrap">
        <div class="article_box" id="listBox">
            <div class="grid_area">
                <div id="gridbox" style="width:100%; height:500px; background-color:white;"></div>
            </div>
        </div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>
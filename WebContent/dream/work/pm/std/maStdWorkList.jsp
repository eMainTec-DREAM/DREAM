<%--===========================================================================
표준항목 - 목록
author  kim210017
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="common.tree.action.TreeAction" %>
<%@ page import="dream.work.pm.std.action.MaStdWorkListAction" %>
<%@ page import="dream.work.pm.std.action.MaStdWorkDetailAction" %>
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
<!-- 표준항목 -->
<title><bean:message key='MENU.STD'/></title>
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

function afterDisable()
{
	$('.b_open').show();
	$('.b_excel').show();
	$('.b_setting').show();
}

function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    myGrid.enableSmartRendering(true,500);
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
        goOpen();
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
	if (maStdWorkListForm.elements['maStdPointCommonDTO.stWrkId'].value == '') return;
	
    var url = contextPath + "/maStdWorkList.do";
    maStdWorkListForm.elements['strutsAction'].value = '<%=MaStdWorkListAction.STD_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maStdWorkListForm), "STWRKWORKPRCID");

}

/**
 * 검색버튼 클릭시 호출
 */
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
    maStdWorkListForm.elements['maStdWorkListDTO.stWrkWorkPrcId'].value = getValueById(myGrid, selectedId, 'STWRKWORKPRCID');

    goCommonTabPage(maStdWorkListForm, <%=MaStdWorkDetailAction.STD_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_stWrkWorkPrcId)
{
	maStdWorkListForm.elements['maStdWorkListDTO.stWrkWorkPrcId'].value = _stWrkWorkPrcId;
	findGridList('ReloadRow');
	maStdWorkListForm.elements['maStdWorkListDTO.stWrkWorkPrcId'].value = "";
}
/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('maStdWorkDetail');
}

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "maStdWorkDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
    maStdWorkListForm.elements['maStdWorkListDTO.stWrkWorkPrcId'].value = "";
    goCommonTabPage(maStdWorkListForm, '', pageId);
}

/**
 * Excel Export
 */
function goExcel()
{
    excelAction(myGrid);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'STWRKWORKPRCID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    maStdWorkListForm.strutsAction.value = '<%=MaStdWorkListAction.STD_LIST_DELETE%>';
    var url = contextPath + "/maStdWorkList.do";
    
    $.post(url,FormQueryString(maStdWorkListForm)+delArray , function(_data){
        afterDelete();
    }); 
}

function afterDelete()
{
    goClose('maStdWorkDetail',this);
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maStdWorkList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maStdPointCommonDTO.stWrkId"/><!-- Key -->
<html:hidden property="maStdWorkListDTO.stWrkWorkPrcId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>
</html:form> 
</body>
</html>
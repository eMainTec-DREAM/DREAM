<%--===========================================================================
안전작업유형 보완사항 list page
author  euna0207
version $Id: workLetCategEtcList.jsp,v 1.1 2015/12/03 01:45:27 euna0207 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.let.categ.action.WorkLetCategEtcListAction" %>
<%@ page import="dream.work.let.categ.action.WorkLetCategEtcDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 안전작업유형 -->
<title><bean:message key='MENU.woLetCtg'/></title>
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
		goTabPage("workLetCategEtcDetail");
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		workLetCategEtcListForm.elements['workLetCategEtcListDTO.woLetCtgEtcId'].value = "";
        return sortColumn("workLetCategEtcList", this, workLetCategEtcListForm, "woLetCtgEtcId", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}



/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/workLetCategEtcList.do";
    workLetCategEtcListForm.elements['strutsAction'].value = '<%=WorkLetCategEtcListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workLetCategEtcListForm), "woLetCtgEtcId","Y");

}




/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_woLetCtgEtcId)
{
	workLetCategEtcListForm.elements['workLetCategEtcListDTO.woLetCtgEtcId'].value = _woLetCtgEtcId;
	findGridList('ReloadRow');
	workLetCategEtcListForm.elements['workLetCategEtcListDTO.woLetCtgEtcId'].value = "";
}

function goSearch()
{
	workLetCategEtcListForm.elements['workLetCategEtcListDTO.woLetCtgEtcId'].value = "";	// 검색시 Tab 이동Key Clear
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
	workLetCategEtcListForm.elements['workLetCategEtcListDTO.woLetCtgEtcId'].value =  getValueById(myGrid, selectedId,'woLetCtgEtcId');  
	goCommonTabPage(workLetCategEtcListForm, <%= WorkLetCategEtcDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('workLetCategEtcDetail');
}

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "workLetCategEtcDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	workLetCategEtcListForm.elements['workLetCategEtcListDTO.woLetCtgEtcId'].value = "";
    goCommonTabPage(workLetCategEtcListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'woLetCtgEtcId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    workLetCategEtcListForm.strutsAction.value = '<%=WorkLetCategEtcListAction.LIST_DELETE%>';
    var url = contextPath + "/workLetCategEtcList.do";
    
    $.post(url,FormQueryString(workLetCategEtcListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('workLetCategEtcDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	workLetCategEtcListForm.elements['workLetCategEtcListDTO.woLetCtgEtcId'].value = "";
	excelServerAction("workLetCategEtcList", workLetCategEtcListForm );  
}

//-->
</script>



</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workLetCategEtcList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="workLetCategCommonDTO.woLetCtgId"/><!-- Key -->
<html:hidden property="workLetCategDetailDTO.woLetCtgId"/><!-- Key -->
<html:hidden property="workLetCategEtcListDTO.woLetCtgEtcId"/><!-- Key -->

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
		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>
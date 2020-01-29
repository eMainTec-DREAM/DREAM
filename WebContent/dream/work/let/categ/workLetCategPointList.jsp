<%--===========================================================================
안전작업유형 점검항목 list page
author  euna0207
version $Id: workLetCategPointList.jsp,v 1.1 2015/12/03 01:45:27 euna0207 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.let.categ.action.WorkLetCategPointListAction" %>
<%@ page import="dream.work.let.categ.action.WorkLetCategPointDetailAction" %>
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
		goTabPage("workLetCategPointDetail");
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		workLetCategPointListForm.elements['workLetCategPointListDTO.woLetCtgPointId'].value = "";
        return sortColumn("workLetCategPointList", this, workLetCategPointListForm, "woLetCtgPointId", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}



/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/workLetCategPointList.do";
    workLetCategPointListForm.elements['strutsAction'].value = '<%=WorkLetCategPointListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workLetCategPointListForm), "woLetCtgPointId","Y");

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
	workLetCategPointListForm.elements['workLetCategPointListDTO.woLetCtgPointId'].value =  getValueById(myGrid, selectedId,'woLetCtgPointId');  
	goCommonTabPage(workLetCategPointListForm, <%= WorkLetCategPointDetailAction.DETAIL_INIT %>, pageId);
}


/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_woLetCtgPointId)
{
	workLetCategPointListForm.elements['workLetCategPointListDTO.woLetCtgPointId'].value = _woLetCtgPointId;
	findGridList('ReloadRow');
	workLetCategPointListForm.elements['workLetCategPointListDTO.woLetCtgPointId'].value = "";
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	findGridList('Search');
}



/**
 * 상세 열기
 */
 function goOpen()
 {
     goTabPage('workLetCategPointDetail');
 }


/**
 * 생성
 */
 function goCreate()
 {
     createValidationCheck(myGrid, "workLetCategPointDetail", "goCreateAction");
 }

 function goCreateAction(pageId)
 {
 	workLetCategPointListForm.elements['workLetCategPointListDTO.woLetCtgPointId'].value = "";
     goCommonTabPage(workLetCategPointListForm, '', pageId);
 }
 
 
/**
 * Excel Export
 */
function goExcel()
{
	workLetCategPointListForm.elements['workLetCategPointListDTO.woLetCtgPointId'].value = "";
	excelServerAction("workLetCategPointList", workLetCategPointListForm );  
}


/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'woLetCtgPointId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    workLetCategPointListForm.strutsAction.value = '<%=WorkLetCategPointListAction.LIST_DELETE%>';
    var url = contextPath + "/workLetCategPointList.do";
    
    $.post(url,FormQueryString(workLetCategPointListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('workLetCategPointDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}


//-->
</script>


</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workLetCategPointList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="workLetCategCommonDTO.woLetCtgId"/><!-- Key -->
<html:hidden property="workLetCategPointListDTO.woLetCtgPointId"/><!-- Key -->

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
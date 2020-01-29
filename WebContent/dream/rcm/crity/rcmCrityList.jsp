<%--===========================================================================
Criticality Matrix List
author  kim21017
version $Id: rcmCrityList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.rcm.crity.action.RcmCrityListAction" %>
<%@ page import="dream.rcm.crity.action.RcmCrityDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- Criticality Matrix -->
<title><bean:message key='MENU.CRITICALMATRIX'/></title>
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
    	rcmCrityListForm.elements['rcmCrityCommonDTO.crityListId'].value = "";
    	return sortColumn("rcmCrityList", this, rcmCrityListForm, "CRITYLISTID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/rcmCrityList.do";
    rcmCrityListForm.elements['strutsAction'].value = '<%=RcmCrityListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(rcmCrityListForm), "CRITYLISTID", "Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_crityListId)
{
	rcmCrityListForm.elements['rcmCrityCommonDTO.crityListId'].value = _crityListId;
	findGridList('ReloadRow');
	rcmCrityListForm.elements['rcmCrityCommonDTO.crityListId'].value = "";
}

function goSearch()
{
	rcmCrityListForm.elements['rcmCrityCommonDTO.crityListId'].value = "";	// 검색시 Tab 이동Key Clear
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
	rcmCrityListForm.elements['rcmCrityCommonDTO.crityListId'].value =  getValueById(myGrid, selectedId,'CRITYLISTID');  
	goCommonTabPage(rcmCrityListForm, <%= RcmCrityDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('rcmCrityDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    rcmCrityListForm.elements['rcmCrityCommonDTO.crityListId'].value =  getValueById(myGrid, selectedId,'CRITYLISTID');  
    rcmCrityListForm.elements['strutsAction'].value = '<%=RcmCrityDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(rcmCrityListForm), 'rcmCrityDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "rcmCrityDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	rcmCrityListForm.elements['rcmCrityCommonDTO.crityListId'].value = "";
    goCommonTabPage(rcmCrityListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'CRITYLISTID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    rcmCrityListForm.strutsAction.value = '<%=RcmCrityListAction.LIST_DELETE%>';
    var url = contextPath + "/rcmCrityList.do";
    
    $.post(url,FormQueryString(rcmCrityListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('rcmCrityDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
    excelAction(myGrid);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/rcmCrityList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="rcmCrityCommonDTO.crityListId"/><!-- Key -->
    <!-- searchbox 박스 Line -->
    <div class="section_wrap">
    	<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.Filter"/></div>
			</div>
			<div class="function_box filter">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>	
				<div class="fb_group2">
				</div>
			</div>
		</div><!--sheader_box end-->
		<div class="article_box">
			<div class="form_box">
				<div class="field">
					<label><bean:message key="LABEL.crityListName"/></label>
					<div class="input_box">
						<html:text property="rcmCrityCommonDTO.filterCrityListDesc" tabindex="10"/>
					</div>
				</div>
			</div>
		</div><!--article_box end-->
	</div> <!--  end section_wrap -->
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
<%--===========================================================================
설비등급 평가기준
author  kim21017
version $Id: assBaseList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.ass.base.action.AssBaseListAction" %>
<%@ page import="dream.ass.base.action.AssBaseDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 설비등급 평가기준 -->
<title><bean:message key='MENU.ASSBASE'/></title>
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
		assBaseListForm.elements['assBaseCommonDTO.assBaseListId'].value = "";
    	return sortColumn("assBaseList", this, assBaseListForm, "ASSBASELISTID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
} 

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/assBaseList.do";
    assBaseListForm.elements['strutsAction'].value = '<%=AssBaseListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(assBaseListForm), "ASSBASELISTID","Y"); //callback 필요
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_assBaseListId)
{
	assBaseListForm.elements['assBaseCommonDTO.assBaseListId'].value = _assBaseListId;
	findGridList('ReloadRow');
	assBaseListForm.elements['assBaseCommonDTO.assBaseListId'].value = "";
}

function goSearch()
{
	assBaseListForm.elements['assBaseCommonDTO.assBaseListId'].value = "";	// 검색시 Tab 이동Key Clear
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
	assBaseListForm.elements['assBaseCommonDTO.assBaseListId'].value =  getValueById(myGrid, selectedId,'ASSBASELISTID');  
	goCommonTabPage(assBaseListForm, <%= AssBaseDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('assBaseDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    assBaseListForm.elements['assBaseCommonDTO.assBaseListId'].value =  getValueById(myGrid, selectedId,'ASSBASELISTID');  
    assBaseListForm.elements['strutsAction'].value = '<%=AssBaseDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(assBaseListForm), 'assBaseDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "assBaseDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	assBaseListForm.elements['assBaseCommonDTO.assBaseListId'].value = "";
    goCommonTabPage(assBaseListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'ASSBASELISTID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    assBaseListForm.strutsAction.value = '<%=AssBaseListAction.LIST_DELETE%>';
    var url = contextPath + "/assBaseList.do";
    
    $.post(url,FormQueryString(assBaseListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('assBaseDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	assBaseListForm.elements['assBaseCommonDTO.assBaseListId'].value = "";
	excelServerAction("assBaseList", assBaseListForm );  
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = assBaseListForm.elements['assBaseCommonDTO.assBaseListId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assBaseList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="assBaseCommonDTO.assBaseListId"/><!-- Key -->
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
					<label><bean:message key="LABEL.assBaseName"/></label>
					<div class="input_box">
						<html:text property="assBaseCommonDTO.filterAssBaseListDesc" tabindex="10"/>
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
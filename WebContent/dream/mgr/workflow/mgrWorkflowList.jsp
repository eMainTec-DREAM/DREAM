<%--===========================================================================
Workflow List
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.workflow.action.MgrWorkflowListAction" %>
<%@ page import="dream.mgr.workflow.action.MgrWorkflowDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 승인Flow -->
<title><bean:message key='MENU.WORKFLOW'/></title>
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
    
    //업무구분 자동완성
    acSysDesc("mgrWorkflowCommonDTO.filterWorkflowTypeDesc","mgrWorkflowCommonDTO.filterWorkflowTypeId","WORKFLOW_TYPE");
    
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
    	mgrWorkflowListForm.elements['mgrWorkflowCommonDTO.wflistId'].value = "";
    	return sortColumn("mgrWorkflowList", this, mgrWorkflowListForm, "WFLISTID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/mgrWorkflowList.do";
    mgrWorkflowListForm.elements['strutsAction'].value = '<%=MgrWorkflowListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mgrWorkflowListForm), "WFLISTID","Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_wflistId)
{
	mgrWorkflowListForm.elements['mgrWorkflowCommonDTO.wflistId'].value = _wflistId;
	findGridList('ReloadRow');
	mgrWorkflowListForm.elements['mgrWorkflowCommonDTO.wflistId'].value = "";
}

function goSearch()
{
	mgrWorkflowListForm.elements['mgrWorkflowCommonDTO.wflistId'].value = "";	// 검색시 Tab 이동Key Clear
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
	mgrWorkflowListForm.elements['mgrWorkflowCommonDTO.wflistId'].value =  getValueById(myGrid, selectedId,'WFLISTID');
	goCommonTabPage(mgrWorkflowListForm, <%= MgrWorkflowDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('mgrWorkflowDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    mgrWorkflowListForm.elements['mgrWorkflowCommonDTO.wflistId'].value =  getValueById(myGrid, selectedId,'WFLISTID');
    mgrWorkflowListForm.elements['strutsAction'].value = '<%=MgrWorkflowDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(mgrWorkflowListForm), 'mgrWorkflowDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "mgrWorkflowDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	mgrWorkflowListForm.elements['mgrWorkflowCommonDTO.wflistId'].value = "";
    goCommonTabPage(mgrWorkflowListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'WFLISTID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    mgrWorkflowListForm.strutsAction.value = '<%=MgrWorkflowListAction.LIST_DELETE%>';
    var url = contextPath + "/mgrWorkflowList.do";
    
    $.post(url,FormQueryString(mgrWorkflowListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('mgrWorkflowDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	mgrWorkflowListForm.elements['mgrWorkflowCommonDTO.wflistId'].value = "";
	excelServerAction("mgrWorkflowList", mgrWorkflowListForm );  
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/mgrWorkflowList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="mgrWorkflowCommonDTO.compNo"/><!-- Key -->
<html:hidden property="mgrWorkflowCommonDTO.wflistId"/><!-- Key -->
<html:hidden property="mgrWorkflowCommonDTO.filterWorkflowTypeId"/>
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
				<!-- 업무구분명 -->
				<div class="field">
					<label><bean:message key="LABEL.wfType"/></label>
					<div class="input_box">
						<html:text property="mgrWorkflowCommonDTO.filterWorkflowTypeDesc" tabindex="10"/>
						<p class="open_spop">
							<a>
							 <span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 승인명 -->
				<div class="field">
					<label><bean:message key="LABEL.wfDesc"/></label>
					<div class="input_box">
						<html:text property="mgrWorkflowCommonDTO.filterDescription" tabindex="20"/>
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
'<%--===========================================================================
사용달력일별횟수설정- 목록
author youngjoo38
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.usage.cal.action.MgrUsageCalSetDayAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>

<html>
<head>
<!-- 사용달력일별횟수설정 -->
<title><bean:message key="MENU.LNRUNPLAN"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">

var myGrid;
/** 자동완성 변수 */
var lnWrkAc;

function loadPage() 
{
	mgrUsageCalSetDayForm.elements['mgrUsageCalSetDayDTO.filterStartWrkDate'].value = getMinusDay(6);
	mgrUsageCalSetDayForm.elements['mgrUsageCalSetDayDTO.filterEndWrkDate'].value   = getToday();

	initGrid();
    
    // 가동달력 자동완성
    lnWrkAc = new autoC({"mgrUsageCalSetDayDTO.filterLnWrkListDesc":"description"});
    lnWrkAc.setTable("TAUSAGEWRKLIST");
    lnWrkAc.setAcConditionMap({
        "comp_no" : loginUser.compNo
    })
    lnWrkAc.setAcResultMap({
        "mgrUsageCalSetDayDTO.filterLnWrkListId":"lnWrkList_id"
    });
    lnWrkAc.init();
}

function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    //myGrid.enableSmartRendering(true,500);
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
        goOpen();
    });
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	mgrUsageCalSetDayForm.elements['mgrUsageCalSetDayDTO.lnWrkTimeId'].value = "";
       	return sortColumn("mgrUsageCalSetDayList", this, mgrUsageCalSetDayForm, "lnWrkTimeId", ind, direction);
    });
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox");}); myGrid.init();

    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/mgrUsageCalSetDayList.do";

    mgrUsageCalSetDayForm.elements['strutsAction'].value = '<%=MgrUsageCalSetDayAction.LINE_RUN_PLAN_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mgrUsageCalSetDayForm), "lnWrkTimeId", "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_lnWrkTimeId)
{
	mgrUsageCalSetDayForm.elements['mgrUsageCalSetDayDTO.lnWrkTimeId'].value = _lnWrkTimeId;
	findGridList('ReloadRow');
	mgrUsageCalSetDayForm.elements['mgrUsageCalSetDayDTO.lnWrkTimeId'].value = "";
}

/**
 * Filter에서 Sheet 검색을 하는 경우이다.
 */
function goSearch()
{
	mgrUsageCalSetDayForm.elements['mgrUsageCalSetDayDTO.lnWrkTimeId'].value = "";    // 검색시 Tab 이동Key Clear
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
    mgrUsageCalSetDayForm.elements['mgrUsageCalSetDayDTO.lnWrkTimeId'].value = getValueById(myGrid, selectedId, 'LNWRKTIMEID');

    goCommonTabPage(mgrUsageCalSetDayForm, <%=MgrUsageCalSetDayAction.LINE_RUN_PLAN_DETAIL_INIT%>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
	goTabPage('mgrUsageCalSetDayDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    mgrUsageCalSetDayForm.elements['mgrUsageCalSetDayDTO.lnWrkTimeId'].value = getValueById(myGrid, selectedId, 'LNWRKTIMEID');
    mgrUsageCalSetDayForm.elements['strutsAction'].value = '<%=MgrUsageCalSetDayAction.LINE_RUN_PLAN_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(mgrUsageCalSetDayForm), 'mgrUsageCalSetDayDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "mgrUsageCalSetDayDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	mgrUsageCalSetDayForm.elements['mgrUsageCalSetDayDTO.lnWrkTimeId'].value = "";
    goCommonTabPage(mgrUsageCalSetDayForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'LNWRKTIMEID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
    
    mgrUsageCalSetDayForm.strutsAction.value = '<%=MgrUsageCalSetDayAction.LINE_RUN_PLAN_LIST_DELETE%>';
    var url = contextPath + "/mgrUsageCalSetDayList.do";

    $.post(url,FormQueryString(mgrUsageCalSetDayForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('mgrUsageCalSetDayDetail', this);
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	mgrUsageCalSetDayForm.elements['mgrUsageCalSetDayDTO.lnWrkTimeId'].value = "";
	excelServerAction("mgrUsageCalSetDayList", mgrUsageCalSetDayForm);
}

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/mgrUsageCalSetDayList">
<html:hidden property="strutsAction"/>
<html:hidden property="mgrUsageCalSetDayDTO.lnWrkTimeId"/><!-- Key -->
<html:hidden property="mgrUsageCalSetDayDTO.filterPlantId"/>
<html:hidden property="mgrUsageCalSetDayDTO.filterLineId"/>
<html:hidden property="mgrUsageCalSetDayDTO.filterLnWrkListId"/>
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
			<!--일자 -->
			<div class="field">
				<label><bean:message key="LABEL.workDate"/></label>
				<div class="calendar_wrap">
					<div class="input_box input_carendar">
						<html:text property="mgrUsageCalSetDayDTO.filterStartWrkDate" tabindex="10" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box input_carendar">
						<html:text property="mgrUsageCalSetDayDTO.filterEndWrkDate" tabindex="20" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
				</div>
			</div>
			
			
			<!-- 가동달력 -->
			<div class="field">
				<label><bean:message key="LABEL.lnWrkListDesc"/></label>
				<div class="input_box">
					<html:text property="mgrUsageCalSetDayDTO.filterLnWrkListDesc" tabindex="50" />
					<p class="open_spop"><a><span>조회</span></a></p>
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
			<div class="fb_group2"></div>
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
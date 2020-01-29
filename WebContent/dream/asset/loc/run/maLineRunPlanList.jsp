<%--===========================================================================
라인가동계획- 목록
author kim21017
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.loc.run.action.MaLineRunPlanAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>

<html>
<head>
<!-- 라인가동계획 -->
<title><bean:message key="MENU.LNRUNPLAN"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//

var myGrid;

/** 자동완성 변수 */
var plantTypeAc;
var eqLocDescAc;
var lnWrkAc;

function loadPage() 
{
	//maLineRunPlanForm.elements['maLineRunPlanDTO.filterStartWrkDate'].value = getMinusDay(6);
	//maLineRunPlanForm.elements['maLineRunPlanDTO.filterEndWrkDate'].value   = getToday();

	setInitVal('maLineRunPlanDTO.filterStartWrkDate', getMinusDay(6));
	setInitVal('maLineRunPlanDTO.filterEndWrkDate', getToday());

	initGrid();
    
    // 가동달력 자동완성
    lnWrkAc = new autoC({"maLineRunPlanDTO.filterLnWrkListDesc":"description"});
    lnWrkAc.setTable("TALNWRKLIST");
    lnWrkAc.setAcConditionMap({
        "comp_no" : loginUser.compNo
        , "lnwrk_calendar_type" : "R"
    })
    lnWrkAc.setAcResultMap({
        "maLineRunPlanDTO.filterLnWrkListId":"lnWrkList_id"
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
    	maLineRunPlanForm.elements['maLineRunPlanDTO.lnWrkTimeId'].value = "";
       	return sortColumn("maLineRunPlanList", this, maLineRunPlanForm, "lnWrkTimeId", ind, direction);
    });
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox");}); myGrid.init();

    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maLineRunPlanList.do";

    maLineRunPlanForm.elements['strutsAction'].value = '<%=MaLineRunPlanAction.LINE_RUN_PLAN_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maLineRunPlanForm), "lnWrkTimeId", "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_failureId)
{
	maLineRunPlanForm.elements['maLineRunPlanDTO.lnWrkTimeId'].value = _failureId;
	findGridList('ReloadRow');
	maLineRunPlanForm.elements['maLineRunPlanDTO.lnWrkTimeId'].value = "";
}

/**
 * Filter에서 Sheet 검색을 하는 경우이다.
 */
function goSearch()
{
	maLineRunPlanForm.elements['maLineRunPlanDTO.lnWrkTimeId'].value = "";    // 검색시 Tab 이동Key Clear
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
    maLineRunPlanForm.elements['maLineRunPlanDTO.lnWrkTimeId'].value = getValueById(myGrid, selectedId, 'LNWRKTIMEID');

    goCommonTabPage(maLineRunPlanForm, <%=MaLineRunPlanAction.LINE_RUN_PLAN_DETAIL_INIT%>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
	goTabPage('maLineRunPlanDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maLineRunPlanForm.elements['maLineRunPlanDTO.lnWrkTimeId'].value = getValueById(myGrid, selectedId, 'LNWRKTIMEID');
    maLineRunPlanForm.elements['strutsAction'].value = '<%=MaLineRunPlanAction.LINE_RUN_PLAN_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maLineRunPlanForm), 'maLineRunPlanDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maLineRunPlanDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maLineRunPlanForm.elements['maLineRunPlanDTO.lnWrkTimeId'].value = "";
    goCommonTabPage(maLineRunPlanForm, '', pageId);
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
    
    maLineRunPlanForm.strutsAction.value = '<%=MaLineRunPlanAction.LINE_RUN_PLAN_LIST_DELETE%>';
    var url = contextPath + "/maLineRunPlanList.do";

    $.post(url,FormQueryString(maLineRunPlanForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('maLineRunPlanDetail', this);
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
//     excelAction(myGrid);
	maLineRunPlanForm.elements['maLineRunPlanDTO.lnWrkTimeId'].value = "";
	excelServerAction("maLineRunPlanList", maLineRunPlanForm);
}
//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/maLineRunPlanList">
<html:hidden property="strutsAction"/>
<html:hidden property="maLineRunPlanDTO.lnWrkTimeId"/><!-- Key -->
<html:hidden property="maLineRunPlanDTO.filterPlantId"/>
<html:hidden property="maLineRunPlanDTO.filterLineId"/>
<html:hidden property="maLineRunPlanDTO.filterLnWrkListId"/>
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
						<html:text property="maLineRunPlanDTO.filterStartWrkDate" tabindex="10" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box input_carendar">
						<html:text property="maLineRunPlanDTO.filterEndWrkDate" tabindex="20" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
				</div>
			</div>
			
			
			<!-- 가동달력 -->
			<div class="field">
				<label><bean:message key="LABEL.lnWrkListDesc"/></label>
				<div class="input_box">
					<html:text property="maLineRunPlanDTO.filterLnWrkListDesc" tabindex="50" />
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
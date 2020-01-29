<%--===========================================================================
Alarm List - 목록 
author  nhkim8548
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.alarm.action.WorkAlarmAction" %>
<%@ page import="common.util.CommonUtil"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%
    User loginUser = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!-- Alarm List -->
<title><bean:message key='PAGE.workAlarmList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
var myGrid;
/** 자동완성 변수 */
var eqTypeAc, plantAc, equipAc;

function loadPage() 
{
	/*
	if(window.name != "LINKED_POPUP")
	{
		workAlarmForm.elements['workAlarmDTO.filterFromDate'].value = getMinusDay(6); 
	    workAlarmForm.elements['workAlarmDTO.filterToDate'].value   = getToday();
	}
	*/
	
	setInitVal("workAlarmDTO.filterFromDate", getMinusDay(6));
	setInitVal("workAlarmDTO.filterToDate", getToday());

    // 설비 명
    equipAc = new autoC({"workAlarmDTO.filterEquipDesc":"description"});
	equipAc.setTable("TAEQUIPMENT");
	equipAc.setAcResultMap({
        "workAlarmDTO.filterEquipId":"EQUIP_ID"
    });
	equipAc.setAcDConditionMap({
    	//"plant" : "workAlarmDTO.filterPlantId"
    });
	equipAc.init();
	
	// Alarm 발생채널
	acSysDesc("workAlarmDTO.filterAlarmOcChDesc","workAlarmDTO.filterAlarmOcChId","ALARM_CHANNEL");
	
	// 수리요청서 발행여부
	acSysDesc("workAlarmDTO.filterIsRepairWork","workAlarmDTO.filterIsRepairWork","IS_USE");
	
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
    	workAlarmForm.elements['workAlarmDTO.alarmListId'].value = "";
        return sortColumn("workAlarmList", this, workAlarmForm, "alarmListId", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
	myGrid.init();
	setHeader(myGrid, "gridbox", "afterHeader"); // grid, grid id
}

function afterHeader() {
	myGrid.setDateFormat("%Y-%m-%d %H:%i:%s","%Y%m%d%H%i%s");
	goSearch();
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	var url = contextPath + "/workAlarmList.do";
	
	workAlarmForm.elements['strutsAction'].value = '<%=WorkAlarmAction.LIST_FIND%>';
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workAlarmForm), "alarmListId", "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_alarmListId)
{
	workAlarmForm.elements['workAlarmDTO.alarmListId'].value = _alarmListId;
	findGridList('ReloadRow');
	workAlarmForm.elements['workAlarmDTO.alarmListId'].value = "";
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	workAlarmForm.elements['workAlarmDTO.alarmListId'].value = "";	// 검색시 Tab 이동Key Clear
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
	workAlarmForm.elements['workAlarmDTO.alarmListId'].value = getValueById(myGrid, selectedId, 'alarmListId');
	goCommonTabPage(workAlarmForm, <%= WorkAlarmAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('workAlarmDetail');
}

function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
    
    if(selectedId == null) return;
    
    workAlarmForm.elements['workAlarmDTO.alarmListId'].value = getValueById(myGrid, selectedId, 'alarmListId');
    workAlarmForm.elements['strutsAction'].value = '<%=WorkAlarmAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(workAlarmForm), 'workAlarmDetail'); 
} 

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'alarmListId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
        alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
        return;
    }

    workAlarmForm.strutsAction.value = '<%=WorkAlarmAction.LIST_DELETE%>';
    var url = contextPath + "/workAlarmList.do";
    
    $.post(url,FormQueryString(workAlarmForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('workAlarmDetail');

    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}


/**
 * Excel Export
 */
function goExcel()
{
	workAlarmForm.elements['workAlarmDTO.alarmListId'].value = "";
	excelServerAction("workAlarmList", workAlarmForm);  
}
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workAlarmList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="workAlarmDTO.alarmListId"/><!-- Key -->
<html:hidden property="workAlarmDTO.filterEquipId"/>
<html:hidden property="workAlarmDTO.filterAlarmOcChId"/>
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
			<!-- 발생일자 -->
			<div class="field">
				<label>발생일자</label>
				<div class="calendar_wrap">
					<div class="input_box input_carendar">
						<html:text property="workAlarmDTO.filterFromDate" tabindex="10" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box input_carendar">
						<html:text property="workAlarmDTO.filterToDate" tabindex="20" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
				</div>
			</div>
			<!-- 설비 -->
			<div class="field">
				<label>설비</label>
				<div class="input_box">
					<html:text property="workAlarmDTO.filterEquipDesc" tabindex="30" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
            <!-- Alarm 설비코드  -->
            <div class="field">
                <label>Alarm 설비코드</label>
                <div class="input_box">
					<html:text property="workAlarmDTO.filterAlarmMcCode" tabindex="40" />
                </div>
            </div>
            <!-- Alarm 설비명 -->
			<div class="field">
				<label>Alarm 설비명</label>
				<div class="input_box">
					<html:text property="workAlarmDTO.filterAlarmMcDesc" tabindex="50" />
				</div>
			</div>
            <!-- Alarm 코드 -->
			<div class="field">
				<label>Alarm 코드</label>
				<div class="input_box">
					<html:text property="workAlarmDTO.filterAlarmCode" tabindex="60" />
				</div>
			</div>
            <!-- Alarm 명 -->
			<div class="field">
				<label>Alarm 명</label>
				<div class="input_box">
					<html:text property="workAlarmDTO.filterAlarmDesc" tabindex="70" />
				</div>
			</div>
            <!-- Alarm 발생채널 -->
			<div class="field">
				<label>Alarm 발생채널</label>
				<div class="input_box">
					<html:text property="workAlarmDTO.filterAlarmOcChDesc" tabindex="80" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
            <!-- 수리요청서 발행여부 -->
			<div class="field">
				<label>수리요청서 발행여부</label>
				<div class="input_box">
					<html:text property="workAlarmDTO.filterIsRepairWork" tabindex="90" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- Alarm 등급 -->
			<div class="field">
				<label>Alarm 등급</label>
				<div class="input_box">
					<html:text property="workAlarmDTO.filterAlarmGrade" tabindex="100" />
				</div>
			</div>
			<!-- Alarm 포인트 -->
			<div class="field">
				<label>Alarm 포인트</label>
				<div class="input_box">
					<html:text property="workAlarmDTO.filterAlarmPoint" tabindex="100" />
				</div>
			</div>
			<!-- Alarm 유형 -->
			<div class="field">
				<label>Alarm 유형</label>
				<div class="input_box">
					<html:text property="workAlarmDTO.filterAlarmType" tabindex="110" />
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
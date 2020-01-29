<%--===========================================================================
Notice 설정 - 목록
author  nhkim8548
version $Id: persPrivNoticeList.jsp, V 1.0 2019/06/12 16:23:00 nhkim8548 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.pers.priv.notice.action.PersPrivNoticeListAction" %>
<%@ page import="dream.pers.priv.notice.action.PersPrivNoticeDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!--메뉴명 -->
<title><bean:message key='TAB.persPrivNoticeList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
<script language="javascript">
function loadPage() 
{
	initGrid();
	
	hideBtn("SAVE");
	hideBtn("EDITCNCL");
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	myGrid.enableSmartRendering(true,500);
	
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		//goOpen();
		validAlarmEmpSet();
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	persPrivNoticeListForm.elements['maMyInfoCommonDTO.alarmEmpSetId'].value = "";
        return sortColumn("persPrivNoticeList", this, persPrivNoticeListForm, "ALARMEMPSETID", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}
 
/**
 * Make Editable Mode
 */
function goEdit()
{
	hideBtn("OPEN");
	hideBtn("EXCEL");
	hideBtn("SETTING");
	
	editRow(myGrid);	

    //Close Detail Page if it is open
    goClose("persPrivNoticeDetail", this);

    //set for update mark.
    setForUpdate();
    
    showBtn("SAVE");
    hideBtn("EDIT");
    showBtn("EDITCNCL");
    
    //Set Grid as updatable
	var url = contextPath + "/persPrivNoticeList.do";
	var stAct = "<%=PersPrivNoticeListAction.LIST_SAVE %>";
	//URL, StrutsAction, Grid Obj, Call Back Function, Validation Rule (not_empty)
	proGrid = setGridUpdate(url, stAct, myGrid, "afterSave",'{}');
	
	//change column type (acedp (Auto complete, LOV), aced(Auto complete only), ednum(Auto complete and number only))
	setColumnType(myGrid,"ISNOTICEDESC","acedp"); //AC,EDIT,POPUP
}

/**
 * AC Setting, This is called when the column is changed to edit mode.
 */
function setGridAc(_gridObj, _cellObj)
{
	// Notice 사용 여부 AC
	acSysDesc("ISNOTICEDESC","ISNOTICE","IS_USE",true,_gridObj,_cellObj);
}

function goSave()
{
	//Send All Data ONce
	proGrid.sendData();
}

/**
 * 수정모드 취소 
 */
function goEditcncl()
{
	editcnclRow(myGrid);
	
	afterSave();
}

/**
 * After Edit
 */
function afterSave()
{
	afterEditRow(myGrid);
	//Control Button
	hideBtn("SAVE");
	showBtn("EDIT");
	hideBtn("EDITCNCL");
	
	showBtn("OPEN");
	showBtn("EXCEL");
	showBtn("SETTING");
		
	//attach Event to open detail page
	//addRowSelEvent();

	//Clear Key Value
	persPrivNoticeListForm.elements['maMyInfoCommonDTO.alarmEmpSetId'].value = "";
	
	//Search
	goSearch();
	
	//Clear Update Mark for this page 
	clearUpdate(currentPageId);
	
	//Make Page as Normal
	setForNormal();
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
    findGridList('Search');   
}

function findGridList(sheetAction)
{
	document.persPrivNoticeListForm.strutsAction.value = '<%=PersPrivNoticeListAction.LIST_FIND %>';
	
	var url = contextPath + "/persPrivNoticeList.do";

	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(persPrivNoticeListForm), "ALARMEMPSETID", "Y");
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
	if(persPrivNoticeListForm.elements['maMyInfoCommonDTO.alarmEmpSetId'].value == "") {
		persPrivNoticeListForm.elements['maMyInfoCommonDTO.alarmEmpSetId'].value = getValueById(myGrid, selectedId,'alarmEmpSetId');	
	}
	goCommonTabPage(persPrivNoticeListForm, '<%=PersPrivNoticeDetailAction.DETAIL_INIT%>' , pageId, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_alarmEmpSetId)
{
	persPrivNoticeListForm.elements['maMyInfoCommonDTO.alarmEmpSetId'].value = _alarmEmpSetId;
	findGridList('ReloadRow');
	persPrivNoticeListForm.elements['maMyInfoCommonDTO.alarmEmpSetId'].value = "";
}

function validAlarmEmpSet() {
	var selectedId = myGrid.getSelectedRowId();
	if(selectedId == null) return;
	
	var alarmEmpSet = getValueById(myGrid, selectedId,'alarmEmpSetId');
	
	if(alarmEmpSet == ""){
		sequenceNextVal('SQAINITALARMEMPSET_ID');
		persPrivNoticeListForm.elements['persPrivNoticeDetailDTO.alarmCode'].value = getValueById(myGrid, selectedId,'alarmCode');
		persPrivNoticeListForm.elements['persPrivNoticeDetailDTO.isNotice'].value  = "N";
	} else {
		goOpen();
	}
}

function setSequenceVal(sequenceVal)
{
	persPrivNoticeListForm.elements['maMyInfoCommonDTO.alarmEmpSetId'].value = sequenceVal;
	persPrivNoticeListForm.elements['persPrivNoticeDetailDTO.alarmEmpSetId'].value = sequenceVal;
	
	setAlarmEmp();
}
// 사용자 Notice 설정에 데이터 없을 시 입력 먼저 실행
function setAlarmEmp() {
	persPrivNoticeListForm.strutsAction.value = '<%=PersPrivNoticeDetailAction.DETAIL_INPUT%>';
	var url = contextPath + "/persPrivNoticeDetail.do";
	
	$.post(url,FormQueryString(persPrivNoticeListForm) , function(_data){
		goOpen();
    });
}

/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('persPrivNoticeDetail');
}

function goOpenAction()
{
    var selectedId = myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    persPrivNoticeListForm.elements['maMyInfoCommonDTO.alarmEmpSetId'].value = getValueById(myGrid, selectedId,'alarmEmpSetId');
    persPrivNoticeListForm.elements['strutsAction'].value = '<%=PersPrivNoticeDetailAction.DETAIL_INIT%>';
    
    openQuickTabPage(FormQueryString(persPrivNoticeListForm), 'persPrivNoticeDetail'); 
} 

/**
 * Excel Export
 */
function goExcel()
{
	persPrivNoticeListForm.elements['maMyInfoCommonDTO.alarmEmpSetId'].value = "";
    excelServerAction("persPrivNoticeList", persPrivNoticeListForm);
}
</script>
</head>

<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/persPrivNoticeList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maMyInfoCommonDTO.alarmEmpSetId"/><!-- Key -->
<html:hidden property="persPrivNoticeDetailDTO.alarmEmpSetId"/>
<html:hidden property="persPrivNoticeDetailDTO.alarmCode"/>
<html:hidden property="persPrivNoticeDetailDTO.isNotice"/>
<!-- searchbox 박스 Line -->
	<div class="article_box" id="listBox">
    	<div class="grid_area">
        	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
        </div>
 	</div>
</html:form> 
</body>
</html>
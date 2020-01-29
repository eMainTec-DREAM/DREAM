<%--===========================================================================
월간작업일정 - 목록
author  kim21017
version $Id: workCalPmRInsMonthList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.cal.pmrinsmonth.action.WorkCalPmRInsMonthListAction" %>
<%@ page import="dream.work.cal.pmrinsperiod.action.WorkCalPmRInsPeriodDetailAction" %>
<%@ page import="dream.work.pmi.list.action.WorkPmiDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId());
%>
<html>
<head>
<!-- 예방작업일정 -->
<title><bean:message key='MENU.WORKCALPMRINSMONTH'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->

<script language="javascript">
<!--//
var beforePageId= '';
var isEdit = false;
//그리드명
var myGrid, proGrid;
/** 자동완성 변수 */
var deptAc;
var exeDeptAc;
var equipDescAc;
var eqLocDescAc;
var eqCtgTypeAc;
var mainMngAc;
var wkCtrDescAc;
var subMngAc;
var planEmpAc;
var empAc;
var plantAc;
var usageDeptAc;

function loadPage()
{
	if(window.name != "LINKED_POPUP")
	{
		//부서
		workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterDeptId'].value    = loginUser.filterDeptId;
		workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterDeptDesc'].value  = loginUser.filterDeptDesc;
		//년월
	    workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterYyyymm'].value = dateToData(getToday()).substr(0, 4)+"-"+dateToData(getToday()).substr(4, 2);
	
		if(loginUser.eqLocId!='null'){
			workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterEqLocId'].value = loginUser.eqLocId;
			workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterEqLocDesc'].value = loginUser.eqLocDesc;
		}
	/*     if(loginUser.wkctrId!='null'){
	    	workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterWkCtrId'].value = loginUser.wkctrId;
	    	workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterWkCtrDesc'].value = loginUser.wkctrDesc;
		} */
	    if(loginUser.filterWkCtrId!='null'){
	    	workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterWkCtrId'].value = loginUser.filterWkCtrId;
	    	workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterWkCtrDesc'].value = loginUser.filterWkCtrDesc;
		}
	    if(loginUser.eqCtgTypeId!='null'){
	    	workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterEqCtgTypeId'].value = loginUser.eqCtgTypeId;
	    	workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterEqCtgTypeDesc'].value = loginUser.eqCtgTypeDesc;
		}
		//공장명
	    if(loginUser.filterPlant!='null'){
	    	workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
	    	workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	    }
	}
    initGrid();

	hideBtn("SAVE");
	hideBtn("EDITCNCL");

    deptAc = new autoC({"workCalPmRInsMonthCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcDisplay("DESCRIPTION");
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "workCalPmRInsMonthCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "workCalPmRInsMonthCommonDTO.filterPlantId"
    	,"plantDesc" : "workCalPmRInsMonthCommonDTO.filterPlantDesc"
    });
    deptAc.init();
    
    exeDeptAc = new autoC({"workCalPmRInsMonthCommonDTO.filterExeDeptDesc":"description"});
    exeDeptAc.setAcDisplay("DESCRIPTION");
    exeDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    exeDeptAc.setTable("TADEPT");
    exeDeptAc.setAcResultMap({
        "workCalPmRInsMonthCommonDTO.filterExeDeptId":"dept_id"
    });
    exeDeptAc.setAcDConditionMap({
    	"plant" : "workCalPmRInsMonthCommonDTO.filterPlantId"
    	,"plantDesc" : "workCalPmRInsMonthCommonDTO.filterPlantDesc"
    });
    exeDeptAc.init();

    equipDescAc = new autoC({"workCalPmRInsMonthCommonDTO.filterEquipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcResultMap({
        "workCalPmRInsMonthCommonDTO.filterEquipId":"equip_id"
    });
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "workCalPmRInsMonthCommonDTO.filterEqLocId",
    	"eqctg_id" : "workCalPmRInsMonthCommonDTO.filterEqCtgId",
    	"dept_id" : "workCalPmRInsMonthCommonDTO.filterDeptId",
    	"plant" : "workCalPmRInsMonthCommonDTO.filterPlantId"
    });
    equipDescAc.init();

    //------------------------------------------------------------------------------------
    
    usageDeptAc = new autoC({"workCalPmRInsMonthCommonDTO.filterUsageDeptDesc":"description"});
    usageDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    usageDeptAc.setTable("TADEPT");
    usageDeptAc.setAcResultMap({
        "workCalPmRInsMonthCommonDTO.filterUsageDeptId":"dept_id"
    });
    usageDeptAc.setAcDConditionMap({
    	"plant" : "workCalPmRInsMonthCommonDTO.filterPlantId"
    	,"plantDesc" : "workCalPmRInsMonthCommonDTO.filterPlantDesc"
    });
    usageDeptAc.init();
    
    //-------------------------------------------------------------------------------------
    
    eqLocDescAc = new autoC({"workCalPmRInsMonthCommonDTO.filterEqLocDesc":"full_desc"});
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcResultMap({
        "workCalPmRInsMonthCommonDTO.filterEqLocId":"eqloc_id"
    });
    eqLocDescAc.setAcDConditionMap({
    	"plant" : "workCalPmRInsMonthCommonDTO.filterPlantId"
    });
    eqLocDescAc.init();

    eqCtgTypeAc = new autoC({"workCalPmRInsMonthCommonDTO.filterEqCtgDesc":"full_desc"});
    eqCtgTypeAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcResultMap({
        "workCalPmRInsMonthCommonDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();

	//법정설비여부  AC
    acSysDesc("workCalPmRInsMonthCommonDTO.filterIsLawEq","workCalPmRInsMonthCommonDTO.filterIsLawEq","IS_USE",false);

    mainMngAc = new autoC({"workCalPmRInsMonthCommonDTO.filterMainMngName":"emp_name"});
    mainMngAc.setAcDisplay("EMP_NAME");
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "workCalPmRInsMonthCommonDTO.filterMainMngId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "workCalPmRInsMonthCommonDTO.filterDeptId",
    	"plant" : "workCalPmRInsMonthCommonDTO.filterPlantId"
    });
    mainMngAc.init();

    wkCtrDescAc = new autoC({"workCalPmRInsMonthCommonDTO.filterWkCtrDesc":"description"});
    wkCtrDescAc.setAcDisplay("DESCRIPTION");
    wkCtrDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    wkCtrDescAc.setTable("TAWKCTR");
    wkCtrDescAc.setAcResultMap({
        "workCalPmRInsMonthCommonDTO.filterWkCtrId":"wkctr_id"
    });
    wkCtrDescAc.init();

	//설비유형  AC
    acSysDesc("workCalPmRInsMonthCommonDTO.filterEqCtgTypeDesc","workCalPmRInsMonthCommonDTO.filterEqCtgTypeId","EQCTG_TYPE");

    subMngAc = new autoC({"workCalPmRInsMonthCommonDTO.filterSubMngName":"emp_name"});
    subMngAc.setAcDisplay("EMP_NAME");
    subMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    subMngAc.setTable("TAEMP");
    subMngAc.setAcResultMap({
        "workCalPmRInsMonthCommonDTO.filterSubMngId":"emp_id"
    });
    subMngAc.setAcDConditionMap({
    	"dept_id" : "workCalPmRInsMonthCommonDTO.filterDeptId",
    	"plant" : "workCalPmRInsMonthCommonDTO.filterPlantId"
    });
    subMngAc.init();

	//주기구분  AC
    acSysDesc("workCalPmRInsMonthCommonDTO.filterPeriodTypeDesc","workCalPmRInsMonthCommonDTO.filterPeriodType","PERIOD_TYPE");
	
    // 계획담당자 자동완성
    planEmpAc = new autoC({"workCalPmRInsMonthCommonDTO.filterPlanEmpDesc":"emp_name"});
    planEmpAc.setTable("TAEMP");
    planEmpAc.setAcResultMap({
        "workCalPmRInsMonthCommonDTO.filterPlanEmpId":"emp_id"
      , "IS_USE":"Y"
    });
    planEmpAc.setAcDConditionMap({
    	"dept_id" : "workCalPmRInsMonthCommonDTO.filterDeptId",
    	"plant" : "workCalPmRInsMonthCommonDTO.filterPlantId"
    });
    planEmpAc.init();
    
    // 실행담당자 자동완성
    empAc = new autoC({"workCalPmRInsMonthCommonDTO.filterEmpDesc":"emp_name"});
    empAc.setTable("TAEMP");
    empAc.setAcResultMap({
        "workCalPmRInsMonthCommonDTO.filterEmpId":"emp_id"
      , "IS_USE":"Y"
    });
    empAc.setAcDConditionMap({
    	"dept_id" : "workCalPmRInsMonthCommonDTO.filterExeDeptId",
    	"plant" : "workCalPmRInsMonthCommonDTO.filterPlantId"
    });
    empAc.init();
    
    plantAc = new autoC({"workCalPmRInsMonthCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "workCalPmRInsMonthCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
}

/**
 * 그리드 초기화
 */
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');

	myGrid.enableSmartRendering(true,500);
	myGrid.enableEditEvents(true,false,false);
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
   	 workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.pmInsSchedId'].value = "";
        return sortColumn("workCalPmRInsMonthList", this, workCalPmRInsMonthListForm, "pmInsSchedId", ind, direction);
    });
	myGrid.attachEvent("onBeforeSelect",function(rowId, state){
		lv_selectedId = rowId;
		var status 		= getValueById(myGrid, rowId, "WOSTATUS");
		var pmStatus 	= getValueById(myGrid, rowId, "PMSTATUSCODE");
	    var index 		= getIndexById(myGrid, "SCHEDDATE");

	    if(isEdit)
		{
			if(pmStatus == "C" || pmStatus == "S") //완료되거나 스케줄 확정되면 스케줄 변화안됨.
			{
				 //disable
				 myGrid.cells(rowId,index).setDisabled(true);
				// return false;
			}
			else
			{
				 myGrid.cells(rowId,index).setDisabled(false);
				// return true;
			}
		}
		else
		{
			 //disable
			 myGrid.cells(rowId,index).setDisabled(true);			
		}
	    
	    if(typeof enhanceOnBeforeSelect == "function") enhanceOnBeforeSelect(myGrid,rowId,state);
	    
		return true;
	});
	//수정시 icon유지...
	 /* myGrid.attachEvent("onEditCell", function(stage,rId,cInd,nValue,oValue){

		if(typeof oValue == "object")
		{
			var year   = oValue.getFullYear();
			var month = oValue.getMonth() + 1;
			var date = oValue.getDate();

			if (month < 10)
			{
				month = "0" + month;
			}
			if (date < 10)
			{
				date = "0" + date;
			}
			oValue = year+"-"+month+"-"+date;
		}

		if (stage==2 && myGrid.getColType(cInd) == "dhxCalendar" && nValue.substring(0,10) != oValue.substring(0,10))
		{//if cell editor is closed
			myGrid.cells(rId, cInd).setValue(nValue + "<img src=\"common/images/ma/b_calendar.png\" style=\"width:13px;display:inline;float:right;position:relative;\">");
			return true;//mandatory
		}

		return true;//mandatory
	});  */
	//시간 숨기기
	myGrid.attachEvent("onCalendarShow", function(myCal,rId,colInd){
		myCal.hideTime();
		//setCalendar(myCal, rId, colInd);
	});
	myGrid.attachEvent("onEditCell", function(stage,rId,cInd,nValue,oValue){
		
		if(typeof checkDateInMon == "function") return checkDateInMon(getValueById(myGrid, rId, "HIDDENSDATE"), nValue);
		else return true;

	});
	
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){		
		selectedId = rowId;
		if(!isEdit)
		{		
			goOpen(rowId);
		}
	});

	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	//myGrid.setDateFormat("%Y-%m-%d");

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 일정조정 (상태가 작업계획(P))
 */
function goEdit()
{
	isEdit = true;
	/* 
	hideBtn("PMSTD");
	hideBtn("RESULT");
	 */
	hideBtn("FIX");
	hideBtn("DELETE");
	hideBtn("EXCEL");
	hideBtn("SETTING");

    //Close Detail Page if it is open
    goClose(beforePageId, this);
    
  	//set for update mark.
    setForUpdate();
    
    showBtn("SAVE");
    hideBtn("EDIT");
    showBtn("EDITCNCL");
	
    //Set Grid as updatable
	var url = contextPath + "/workCalPmRInsMonthList.do";
	var stAct = "<%=WorkCalPmRInsMonthListAction.MONTH_SCHED_CHANGE %>";

	//URL, StrutsAction, Grid Obj, Call Back Function, Validation Rule (not_empty)
	proGrid = setGridUpdate(url, stAct, myGrid, "afterSave");
	
	setColumnType(myGrid,"remark","ed"); //EDIT

}
/**
 * 수정모드 취소 
 */
function goEditcncl()
{
	editcnclRow(myGrid);
	
	afterSave();
}

function goSave()
{
	proGrid.sendData();
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
	/* 
	showBtn("PMSTD");
	showBtn("RESULT");
	 */
	showBtn("DELETE");
	showBtn("FIX");
	showBtn("EXCEL");
	showBtn("SETTING");
	
	workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.pmInsSchedId'].value = "";	// 검색시 Tab 이동Key Clear
	
	//Clear Update Mark for this page 
	clearUpdate(currentPageId);
	
	searchList();
	//달력 조회
	findSchedList();
	
	isEdit = false;
	
	//Make Page as Normal
	setForNormal();
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(yyyymmdd,type)
{
    var url = contextPath + "/workCalPmRInsMonthList.do";
    workCalPmRInsMonthListForm.elements['strutsAction'].value = '<%=WorkCalPmRInsMonthListAction.MONTH_SCHED_LIST_FIND%>';
    workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.yyyymmdd'].value     = yyyymmdd;
    workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.schedType'].value    = type;
    
    doGridAction("Search", myGrid, "gridbox", url, FormQueryString(workCalPmRInsMonthListForm),"","Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pmInsListId)
{
	goSearch();
	workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.pmInsListId'].value = _pmInsListId;
	findSchedRow("ReloadRow");
	workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.pmInsListId'].value = "";
}

function findSchedRow(sheetAction) {
	var url = contextPath + "/workCalPmRInsMonthList.do";
	workCalPmRInsMonthListForm.elements['strutsAction'].value = '<%=WorkCalPmRInsMonthListAction.MONTH_SCHED_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workCalPmRInsMonthListForm), "pmInsSchedId","Y");
}

/**
 * 저장후 리스트만 재조회
 */
 function searchList(){
	var url = contextPath + "/workCalPmRInsMonthList.do";
    workCalPmRInsMonthListForm.elements['strutsAction'].value = '<%=WorkCalPmRInsMonthListAction.MONTH_SCHED_LIST_FIND%>';

    doGridAction("Search", myGrid, "gridbox", url, FormQueryString(workCalPmRInsMonthListForm),"","Y");
//     myGrid.clearAll(); setLoading("gridbox");
//     $.post(url,FormQueryString(workCalPmRInsMonthListForm), function(_data){
//     	myGrid.parse(_data,"js");
//     });
}
/**
 * 월간작업일정 조회
 */
function findSchedList(){
	var url = contextPath + "/workCalPmRInsMonthList.do";
	workCalPmRInsMonthListForm.elements['strutsAction'].value = '<%=WorkCalPmRInsMonthListAction.MONTH_SCHED_FIND%>';
	setModal();
	$.post(url,FormQueryString(workCalPmRInsMonthListForm), function(_data){
		afterFindSched(JSON.parse(_data));
    });

}
/**
 * 월간일정 세팅
 */
function afterFindSched(_dataObj){
	closeModal();
	
	/**
	*달력초기화
	*/
	for(var i=1;i<=6;i++){
		for(var j=1;j<=7;j++){
			document.getElementById("TD"+i+"_"+j).innerHTML = "";
		}
	}
	document.getElementById("month_title").innerText = workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.yyyymmdd'].value;
	
	Object.keys(_dataObj).forEach(function(key){
		var _cntObject = _dataObj[key];
		
		var _yyyymmdd = key;
		var _week = _cntObject.WEEK;
		var _dow = _cntObject.DOW;
		var _dd;
		if(_yyyymmdd.substr(6, 1)=="0") _dd = _yyyymmdd.substr(7, 1);
		else _dd = _yyyymmdd.substr(6, 2);
		
		var _totCnt = _cntObject.TOTAL;
		var _sCnt = _cntObject.S + _cntObject.IRWRA + _cntObject.IRWOA + _cntObject.IRWDA;
		var _cCnt = _cntObject.C;
		
		var todayStyleStr = "date_box";
		if(_yyyymmdd==getToday().replace(/\-/gi, "")) todayStyleStr = "date_box date_today";
		var backWoStyleStr = "psc_box";
		if(Number(_totCnt)>Number(_cCnt) && Number(_yyyymmdd)<Number(getToday().replace(/\-/gi, ""))) backWoStyleStr = "psc_box psc_unc";
		
		var str = "<div class='"+todayStyleStr+"'>"+_dd+"</div>"+
					"<div class='"+backWoStyleStr+"'>"+
					"<span class='p'><a href=\"javascript:findGridList("+"'"+_yyyymmdd+"', '');\">"+_totCnt+"</a></span> /"+
					"<span class='s'><a href=\"javascript:findGridList("+"'"+_yyyymmdd+"', 'S+IRWRA+IRWOA+IRWDA');\">"+_sCnt+"</a></span> /"+
					"<span class='c'><a href=\"javascript:findGridList("+"'"+_yyyymmdd+"', 'C');\">"+_cCnt+"</a></span>"+
					"</div>";
		
		document.getElementById("TD"+_week+"_"+_dow).innerHTML = str;
	});
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.pmInsSchedId'].value = "";	// 검색시 Tab 이동Key Clear
	workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.pmInsListId'].value = "";
    workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.schedType'].value= "";
	
	workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.yyyymmdd'].value       = workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterYyyymm'].value;
    workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.deptId'].value         = workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterDeptId'].value;        
    workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.deptDesc'].value       = workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterDeptDesc'].value;      
    workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.exeDeptId'].value      = workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterExeDeptId'].value;        
    workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.exeDeptDesc'].value    = workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterExeDeptDesc'].value;      
    workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.eqLocId'].value        = workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterEqLocId'].value;       
    workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.eqLocDesc'].value      = workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterEqLocDesc'].value;     
    workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.eqCtgId'].value        = workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterEqCtgId'].value;       
    workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.eqCtgDesc'].value      = workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterEqCtgDesc'].value;     
    workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.eqCtgTypeId'].value    = workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterEqCtgTypeId'].value;   
    workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.eqCtgTypeDesc'].value  = workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterEqCtgTypeDesc'].value; 
    workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.mainMngId'].value      = workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterMainMngId'].value;     
    workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.mainMngName'].value    = workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterMainMngName'].value;   
    workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.subMngId'].value       = workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterSubMngId'].value;      
    workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.subMngName'].value     = workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterSubMngName'].value;    
    workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.plfTypeId'].value      = workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterPlfTypeId'].value;     
    workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.plfTypeDesc'].value    = workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterPlfTypeDesc'].value;   
    workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.wkCtrId'].value        = workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterWkCtrId'].value;       
    workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.wkCtrDesc'].value      = workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterWkCtrDesc'].value;     
    workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.equipId'].value        = workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterEquipId'].value;       
    workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.equipDesc'].value      = workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterEquipDesc'].value;     
    workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.isLawEq'].value        = workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterIsLawEq'].value;       
    workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.pmNo'].value           = workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterPmNo'].value;          
    workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.cycle'].value          = workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterCycle'].value;         
    workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.periodType'].value     = workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterPeriodType'].value;    
    workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.periodTypeDesc'].value = workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterPeriodTypeDesc'].value;
    workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.planEmpId'].value   	= workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterPlanEmpId'].value;         
    workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.planEmpDesc'].value	= workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterPlanEmpDesc'].value;       
    workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.empId'].value   		= workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterEmpId'].value;         
    workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.empDesc'].value		= workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterEmpDesc'].value;       
    workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.plantId'].value   		= workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterPlantId'].value;       
    workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.plantDesc'].value		= workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.filterPlantDesc'].value;     
    
	findSchedList();
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
	var form = document.workCalPmRInsMonthListForm;
	
	var pmStatus = getValueById(myGrid, selectedId, "pmStatusCode");
	var wkOrId = getValueById(myGrid, selectedId, "wkorId");
	
	if(pmStatus == "P")
	{
		form.elements['workCalPmRInsPeriodCommonDTO.pmInsSchedId'].value = getValueById(myGrid, selectedId, 'pmInsSchedId');
		goCommonTabPage(form, <%= WorkCalPmRInsPeriodDetailAction.MONTH_SCHED_DETAIL_INIT %>, pageId, beforePageId);
	}
	else
	{
		form.elements['workPmiCommonDTO.pminslistId'].value = getValueById(myGrid, selectedId, "pmInsListId");
		goCommonTabPage(form, <%= WorkPmiDetailAction.WORK_PMI_DETAIL_INIT %>, pageId, beforePageId);
	}

	beforePageId = pageId;
}

/**
 * 상세 열기
 */
function goOpen(rowId)
{
	var pmStatus = getValueById(myGrid, rowId, "pmStatusCode");
	
	if(pmStatus == "P")
	{
		goTabPage('workCalPmRInsPeriodDetail');
	}
	else
	{
		var pmInsListId = getValueById(myGrid, rowId, "pmInsListId");
	    var pmType = getValueById(myGrid, rowId, "PMTYPE");

	    if(pmInsListId == "undefined" || pmInsListId == "") 
	    {
	        alertMessage1('<bean:message key="MESSAGE.MSG0024"/>');
	        return; 
	    }
	    
	    var woparam = "workPmiDetail";
	    pmType = pmType.substring(0,1)+pmType.substring(1,pmType.length).toLowerCase();
	    
	    workCalPmRInsMonthListForm.elements['workPmiCommonDTO.pminslistId'].value = pmInsListId;
		
	    var param = "strutsAction=8001&workPmiCommonDTO.pminslistId="+ pmInsListId;
		
		goTabPage(woparam);
	}
	
}


/**
 * 엑셀 다운.
 */
 function goExcel()
 {
	 workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.pmInsSchedId'].value = "";
	excelServerAction("workCalPmRInsMonthList", workCalPmRInsMonthListForm );  
 }

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "workCalPmRInsMonthDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	workCalPmRInsMonthListForm.elements['workCalPmRInsMonthCommonDTO.pmInsSchedId'].value = "";
	goCommonTabPage(workCalPmRInsMonthListForm, '', pageId);
}

/**
 * 삭제
 */
 function goDelete(){
	//myGrid에 1(2번째)번에 체크가 되어있으면 2번 데이터 가져오기
	 var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'PMINSSCHEDID'); //Grid, check box column seq, pk column seq
	//체크된게 없으면 return
	 if(typeof delArray == "undefined"){
			alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
			return;
		}

  	workCalPmRInsMonthListForm.strutsAction.value = '<%=WorkCalPmRInsMonthListAction.MONTH_SCHED_LIST_DELETE%>';
  	var url = contextPath + "/workCalPmRInsMonthList.do";

  	$.post(url,FormQueryString(workCalPmRInsMonthListForm)+delArray , function(_data){
    	afterDelete();
    });
  }

function afterDelete(){
	goClose('workCalPmRInsMonthDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
  }

/**
 * 일별 확정
 */
function goFix(){
	//myGrid에 1(2번째)번에 체크가 되어있으면 2번 데이터 가져오기
	var fixArray = getSelectRows(myGrid, 'ISDELCHECK', 'PMINSSCHEDID'); //Grid, check box column seq, pk column seq
	
	if(typeof fixArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	dhtmlx.confirm('<bean:message key="MESSAGE.MSG045"/>', function(result){
		if(result){
			workCalPmRInsMonthListForm.strutsAction.value = '<%=WorkCalPmRInsMonthListAction.MONTH_SCHED_DAILY_SCHEDULED%>';
		 	var url = contextPath + "/workCalPmRInsMonthList.do";

		 	ajaxPost(url,FormQueryString(workCalPmRInsMonthListForm)+fixArray)
			.done(function(d){
				searchList();
				goSearch();
			});
		}
	});
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/workCalPmRInsMonthList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.pmInsSchedId"/><!-- Key -->
<html:hidden property="workCalPmRInsMonthCommonDTO.filterDeptId"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.filterExeDeptId"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.yyyymmdd"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.schedType"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.deptId"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.deptDesc"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.exeDeptId"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.exeDeptDesc"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.eqLocId"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.eqLocDesc"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.eqCtgId"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.eqCtgDesc"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.eqCtgTypeId"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.eqCtgTypeDesc"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.mainMngId"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.mainMngName"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.subMngId"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.subMngName"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.plfTypeId"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.plfTypeDesc"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.isLawEq"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.wkCtrId"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.wkCtrDesc"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.equipId"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.equipDesc"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.pmNo"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.cycle"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.periodType"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.periodTypeDesc"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.filterEqLocId"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.filterEqCtgId"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.filterEqCtgTypeId"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.filterPlfTypeId"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.filterPlfTypeDesc"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.filterWkCtrId"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.filterMainMngId"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.filterSubMngId"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.filterEquipId"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.filterPeriodType"/>

<html:hidden property="workCalPmRInsMonthCommonDTO.filterPlanEmpId"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.filterEmpId"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.planEmpId"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.planEmpDesc"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.empId"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.empDesc"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.filterPlantId"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.plantId"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.plantDesc"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.pmInsListId"/>
<html:hidden property="workCalPmRInsMonthCommonDTO.filterUsageDeptId"/>

<html:hidden property="workCalPmRInsPeriodCommonDTO.pmInsSchedId"/>
<html:hidden property="workPmiCommonDTO.pminslistId"/>
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
					<label><bean:message key="LABEL.yyyymm"/></label>
					<div class="input_read">
						<html:text property="workCalPmRInsMonthCommonDTO.filterYyyymm" readonly="true"/>
						<p class="open_mon_calendar"><span>날짜</span></p>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.pmNo"/></label>
					<div class="input_box">
						<html:text property="workCalPmRInsMonthCommonDTO.filterPmNo" tabindex="10"/>
					</div>
				</div>
				
				<!-- 계획부서 -->
				<div class="field">
					<label><bean:message key="LABEL.planDept"/></label>
					<div class="input_box">
						<html:text property="workCalPmRInsMonthCommonDTO.filterDeptDesc" tabindex="20" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 실행부서 -->
				<div class="field">
					<label><bean:message key="LABEL.exeDept"/></label>
					<div class="input_box">
						<html:text property="workCalPmRInsMonthCommonDTO.filterExeDeptDesc" tabindex="25" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 사용부서 -->
				<div class="field">
					<label><bean:message key="LABEL.usedDept"/></label>
					<div class="input_box">
						<html:text property="workCalPmRInsMonthCommonDTO.filterUsageDeptDesc" tabindex="20" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 계획담당자 -->
				<div class="field">
					<label><bean:message key="LABEL.planEmp"/></label>
					<div class="input_box">
						<html:text property="workCalPmRInsMonthCommonDTO.filterPlanEmpDesc" tabindex="21" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 실행담당자 -->
				<div class="field">
					<label><bean:message key="LABEL.exeEmp"/></label>
					<div class="input_box">
						<html:text property="workCalPmRInsMonthCommonDTO.filterEmpDesc" tabindex="22" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 설비 -->
				<div class="field">
					<label><bean:message key="LABEL.equipDesc"/></label>
					<div class="input_box">
						<html:text property="workCalPmRInsMonthCommonDTO.filterEquipDesc" tabindex="25" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 위치 -->
				<div class="field">
					<label><bean:message key="LABEL.location"/></label>
					<div class="input_box">
						<html:text property="workCalPmRInsMonthCommonDTO.filterEqLocDesc" tabindex="30" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 종류 -->
				<div class="field">
					<label><bean:message key="LABEL.type"/></label>
					<div class="input_box">
						<html:text property="workCalPmRInsMonthCommonDTO.filterEqCtgDesc" tabindex="35" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 내/외자  -->
				<!-- 법정설비여부 -->
				<div class="field">
					<label><bean:message key="LABEL.isLawEq"/></label>
					<div class="input_box">
						<html:text property="workCalPmRInsMonthCommonDTO.filterIsLawEq" tabindex="60" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 관리자(정) -->
				<div class="field">
					<label><bean:message key="LABEL.mainManager"/></label>
					<div class="input_box">
						<html:text property="workCalPmRInsMonthCommonDTO.filterMainMngName" tabindex="70" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 관리자(부) -->
				<div class="field">
					<label><bean:message key="LABEL.subManager"/></label>
					<div class="input_box">
						<html:text property="workCalPmRInsMonthCommonDTO.filterSubMngName" tabindex="90"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 작업그룹 -->
                <div class="field">
                     <label><bean:message key="LABEL.wkCtr"/></label>
                     <div class="input_box">
                        <html:text property="workCalPmRInsMonthCommonDTO.filterWkCtrDesc" tabindex="140" />
                         <p class="open_spop">
                             <a>
                                 <span>조회</span>
                             </a>
                         </p>
                   </div>
                </div>
				<!-- 주기 -->
				<div class="field">
					<label><bean:message key="LABEL.cycleDesc"/></label>
					<div class="input_box">
							<html:text property="workCalPmRInsMonthCommonDTO.filterCycle" tabindex="150" styleClass="num"/>
					</div>
				</div>
				<!-- 주기구불 -->
				<div class="field">
					<label><bean:message key="LABEL.periodTypeDesc"/></label>
					<div class="input_box">
							<html:text property="workCalPmRInsMonthCommonDTO.filterPeriodTypeDesc" tabindex="160" />
							<p class="open_spop">
								<a>
								<span>조회</span></a>
							</p>
					</div>
				</div>
				<!-- 설비유형  -->
				<div class="field">
					<label><bean:message key="LABEL.eqCtgType"/></label>
					<div class="input_box">
						<html:text property="workCalPmRInsMonthCommonDTO.filterEqCtgTypeDesc" tabindex="170" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
							<html:text property="workCalPmRInsMonthCommonDTO.filterPlantDesc" tabindex="180" />
							<p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
				
				<c:set var="filePath" value="enhance/${compName}/work/cal/pmrinsmonth/workCalPmRInsMonthList_${compNo}.jsp" />
				<c:if test="${udf:isExist(filePath)}">
					<c:import charEncoding="UTF-8" url="/enhance/${compName}//work/cal/pmrinsmonth/workCalPmRInsMonthList_${compNo}.jsp"></c:import>
				</c:if>
				
			</div>
		</div><!--article_box end-->
	</div> <!--  end section_wrap -->
	<!--월간일정 start-->
	<div class="section_wrap">
		<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx" id="month_title"></div>
			</div>
			<div class="function_box">
					<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>
				<%-- <div class="fb_group2">
				<a href="javascript:goFixMonthly();" class="b_fix"><span><bean:message key="BUTTON.CONFIRM"/></span></a>
				</div> --%>
			</div>
		</div>
		<div class="article_box">
						<div class="tb_month">
							<table width="100%" border="0" cellpadding="0" cellspacing="0;" >
								<tr>
									<th scope="col"><bean:message key="LABEL.sun"/></th>
									<th scope="col"><bean:message key="LABEL.mon"/></th>
									<th scope="col"><bean:message key="LABEL.tue"/></th>
									<th scope="col"><bean:message key="LABEL.wed"/></th>
									<th scope="col"><bean:message key="LABEL.thu"/></th>
									<th scope="col"><bean:message key="LABEL.fri"/></th>
									<th scope="col"><bean:message key="LABEL.sat"/></th>
								</tr>
								<tr>
									<td id="TD1_1"></td>
									<td id="TD1_2"></td>
									<td id="TD1_3"></td>
									<td id="TD1_4"></td>
									<td id="TD1_5"></td>
									<td id="TD1_6"></td>
									<td id="TD1_7"></td>
								</tr>
								<tr>
									<td id="TD2_1"></td>
									<td id="TD2_2"></td>
									<td id="TD2_3"></td>
									<td id="TD2_4"></td>
									<td id="TD2_5"></td>
									<td id="TD2_6"></td>
									<td id="TD2_7"></td>
								</tr>
								<tr>
									<td id="TD3_1"></td>
									<td id="TD3_2"></td>
									<td id="TD3_3"></td>
									<td id="TD3_4"></td>
									<td id="TD3_5"></td>
									<td id="TD3_6"></td>
									<td id="TD3_7"></td>
								</tr>
								<tr>
									<td id="TD4_1"></td>
									<td id="TD4_2"></td>
									<td id="TD4_3"></td>
									<td id="TD4_4"></td>
									<td id="TD4_5"></td>
									<td id="TD4_6"></td>
									<td id="TD4_7"></td>
								</tr>
								<tr>
									<td id="TD5_1"></td>
									<td id="TD5_2"></td>
									<td id="TD5_3"></td>
									<td id="TD5_4"></td>
									<td id="TD5_5"></td>
									<td id="TD5_6"></td>
									<td id="TD5_7"></td>
								</tr>
								<tr>
									<td id="TD6_1"></td>
									<td id="TD6_2"></td>
									<td id="TD6_3"></td>
									<td id="TD6_4"></td>
									<td id="TD6_5"></td>
									<td id="TD6_6"></td>
									<td id="TD6_7"></td>
								</tr>
							</table>
							<div class="legend_tx"><span class="p"><strong>P</strong></span> : Planed &nbsp;&nbsp;&nbsp;<span class="s"><strong>S</strong></span> : Scheduled &nbsp;&nbsp;&nbsp;<span class="c"><strong>C</strong></span> : Completed</div>
						</div>
					</div>
					<!--article_box-->
				</div>
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
		<div class="article_box">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
	</div> <!--  End of section_wrap -->
</html:form>
</body>
</html>
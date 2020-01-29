<%--===========================================================================
월간작업일정 - 목록
author  kim21017
version $Id: workCalPmDInsMonthList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.cal.pmdinsmonth.action.WorkCalPmDInsMonthListAction" %>
<%@ page import="dream.work.cal.pmdinsmonth.action.WorkCalPmDInsMonthDetailAction" %>
<%@ page import="dream.work.pm.list.action.WorkPmiDInsDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId());
%>
<html>
<head>
<!-- 예방작업일정 -->
<title><bean:message key='MENU.WORKCALPMDINSMONTH'/></title>
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
var equipDescAc;
var eqLocDescAc;
var mainMngAc;
var wkCtrDescAc;
var subMngAc;
var empAc;

function loadPage()
{
	if(window.name=="PMSCHED_LIST_POPUP"){
		workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.filterYyyymm'].value = dateToData(getToday()).substr(0, 4)+"-"+dateToData(getToday()).substr(4, 2);
		initGrid();
		goSearch();
	}else{
		//부서
		workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.filterDeptId'].value    = loginUser.filterDeptId;
		workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.filterDeptDesc'].value  = loginUser.filterDeptDesc;
		//년월
	    workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.filterYyyymm'].value = dateToData(getToday()).substr(0, 4)+"-"+dateToData(getToday()).substr(4, 2);

		if(loginUser.eqLocId!='null'){
			workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.filterEqLocId'].value = loginUser.eqLocId;
			workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.filterEqLocDesc'].value = loginUser.eqLocDesc;
		}
/* 	    if(loginUser.wkctrId!='null'){
	    	workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.filterWkCtrId'].value = loginUser.wkctrId;
	    	workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.filterWkCtrDesc'].value = loginUser.wkctrDesc;
		} */
	    if(loginUser.filterWkCtrId !='null'){
	    	workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.filterWkCtrId'].value = loginUser.filterWkCtrId;
	    	workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.filterWkCtrDesc'].value = loginUser.filterWkCtrDesc;
		}
	    if(loginUser.eqCtgTypeId!='null'){
	    	workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.filterEqCtgTypeId'].value = loginUser.eqCtgTypeId;
	    	workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.filterEqCtgTypeDesc'].value = loginUser.eqCtgTypeDesc;
		}
	    initGrid();

		hideBtn("SAVE");
		hideBtn("EDITCNCL");

	    deptAc = new autoC({"workCalPmDInsMonthCommonDTO.filterDeptDesc":"description"});
	    deptAc.setAcDisplay("DESCRIPTION");
	    deptAc.setAcConditionMap({
	    	"comp_no":loginUser.compNo
	  	   });
	    deptAc.setTable("TADEPT");
	    deptAc.setAcResultMap({
	        "workCalPmDInsMonthCommonDTO.filterDeptId":"dept_id"
	    });
	    deptAc.init();

	    equipDescAc = new autoC({"workCalPmDInsMonthCommonDTO.filterEquipDesc":"description"});
	    equipDescAc.setAcConditionMap({
	 	   "comp_no":loginUser.compNo
	 	   });
	    equipDescAc.setTable("TAEQUIPMENT");
	    equipDescAc.setAcDConditionMap({
	    	"eqloc_id" : "workCalPmDInsMonthCommonDTO.filterEqLocId",
	    	"eqctg_id" : "workCalPmDInsMonthCommonDTO.filterEqCtgId",
	    	"dept_id" : "workCalPmDInsMonthCommonDTO.filterDeptId"
	    });
	    equipDescAc.init();

	    eqLocDescAc = new autoC({"workCalPmDInsMonthCommonDTO.filterEqLocDesc":"full_desc"});
	    eqLocDescAc.setAcConditionMap({
	 	   "comp_no":loginUser.compNo
	 	   });
	    eqLocDescAc.setTable("TAEQLOC");
	    eqLocDescAc.setAcResultMap({
	        "workCalPmDInsMonthCommonDTO.filterEqLocId":"eqloc_id"
	    });
	    eqLocDescAc.init();

	    eqCtgTypeAc = new autoC({"workCalPmDInsMonthCommonDTO.filterEqCtgDesc":"full_desc"});
	    eqCtgTypeAc.setAcConditionMap({
	 	   "comp_no":loginUser.compNo
	 	   });
	    eqCtgTypeAc.setTable("TAEQCTG");
	    eqCtgTypeAc.setAcResultMap({
	        "workCalPmDInsMonthCommonDTO.filterEqCtgId":"eqctg_id"
	    });
	    eqCtgTypeAc.init();

		//법정설비여부 AC
	    acSysDesc("workCalPmDInsMonthCommonDTO.filterIsLawEq","workCalPmDInsMonthCommonDTO.filterIsLawEq","IS_USE",true);
		//설비유형 AC
	    acSysDesc("workCalPmDInsMonthCommonDTO.filterEqCtgTypeDesc","workCalPmDInsMonthCommonDTO.filterEqCtgTypeId","EQCTG_TYPE");

	    mainMngAc = new autoC({"workCalPmDInsMonthCommonDTO.filterMainMngName":"emp_name"});
	    mainMngAc.setAcDisplay("EMP_NAME");
	    mainMngAc.setAcConditionMap({
	    	"comp_no":loginUser.compNo
	  	   });
	    mainMngAc.setTable("TAEMP");
	    mainMngAc.setAcResultMap({
	        "workCalPmDInsMonthCommonDTO.filterMainMngId":"emp_id"
	    });
	    mainMngAc.setAcDConditionMap({
	    	"dept_id" : "workCalPmDInsMonthCommonDTO.filterDeptId"
	    });
	    mainMngAc.init();

	    wkCtrDescAc = new autoC({"workCalPmDInsMonthCommonDTO.filterWkCtrDesc":"description"});
	    wkCtrDescAc.setAcDisplay("DESCRIPTION");
	    wkCtrDescAc.setAcConditionMap({
	    	"comp_no":loginUser.compNo
	  	   });
	    wkCtrDescAc.setTable("TAWKCTR");
	    wkCtrDescAc.setAcResultMap({
	        "workCalPmDInsMonthCommonDTO.filterWkCtrId":"wkctr_id"
	    });
	    wkCtrDescAc.init();

	    subMngAc = new autoC({"workCalPmDInsMonthCommonDTO.filterSubMngName":"emp_name"});
	    subMngAc.setAcDisplay("EMP_NAME");
	    subMngAc.setAcConditionMap({
	    	"comp_no":loginUser.compNo
	  	   });
	    subMngAc.setTable("TAEMP");
	    subMngAc.setAcResultMap({
	        "workCalPmDInsMonthCommonDTO.filterSubMngId":"emp_id"
	    });
	    subMngAc.setAcDConditionMap({
	    	"dept_id" : "workCalPmDInsMonthCommonDTO.filterDeptId"
	    });
	    subMngAc.init();

	    // 담당자 자동완성
	    empAc = new autoC({"workCalPmDInsMonthCommonDTO.filterEmpDesc":"emp_name"});
	    empAc.setTable("TAEMP");
	    empAc.setAcResultMap({
	        "workCalPmDInsMonthCommonDTO.filterEmpId":"emp_id"
	      , "IS_USE":"Y"
	    });
	    empAc.setAcDConditionMap({
	    	"dept_id" : "workCalPmDInsMonthCommonDTO.filterDeptId"
	    });
	    empAc.init();
	    
	}

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
	myGrid.attachEvent("onBeforeSelect",function(rowId, state){

		lv_selectedId = rowId;
		var status 		= getValueById(myGrid, rowId, "WOSTATUS");
		var pmStatus 	= getValueById(myGrid, rowId, "PMSTATUSCODE");
	    var index 		= getIndexById(myGrid, "SCHEDDATE");

	    if(isEdit)
		{
			if(pmStatus == "C" || pmStatus == "S") //완료되거나 스케줄 확정되면 스케줄 변화안됨.
			{
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
		
		if(typeof checkDateInMon == "function") return checkDateInMon(getValueById(myGrid, rId, "PLANDATE"), nValue);
		else return true;

	});
	
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){		
		selectedId = rowId;
		if(!isEdit)
		{		
			goOpen(rowId);
		}
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.pmInsDSchedId'].value = "";
        return sortColumn("workCalPmDInsMonthList", this, workCalPmDInsMonthListForm, "pmInsDSchedId", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	myGrid.setDateFormat("%Y-%m-%d");

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 일정조정 (상태가 작업계획(P))
 */
function goEdit()
{
	isEdit = true;
	
	hideBtn("PMSTD");
	hideBtn("RESULT");
	
	hideBtn("FIX");
	hideBtn("DELETE");
	hideBtn("EXCEL");
	hideBtn("SETTING");

    //Close Detail Page if it is open
    goClose(beforePageId, this);
    
    showBtn("SAVE");
    hideBtn("EDIT");
    showBtn("EDITCNCL");
	
    //Set Grid as updatable
	var url = contextPath + "/workCalPmDInsMonthList.do";
	var stAct = "<%=WorkCalPmDInsMonthListAction.MONTH_SCHED_LIST_CHANGE %>";

	//URL, StrutsAction, Grid Obj, Call Back Function, Validation Rule (not_empty)
	proGrid = setGridUpdate(url, stAct, myGrid, "afterSave");

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

	myGrid.enableEditEvents(false,false,false);
	afterEditRow(myGrid);
	
	//Control Button
	hideBtn("SAVE");
	showBtn("EDIT");
	hideBtn("EDITCNCL");
	
	showBtn("PMSTD");
	showBtn("RESULT");
	
	showBtn("DELETE");
	showBtn("FIX");
	showBtn("EXCEL");
	showBtn("SETTING");
	
	workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.pmInsDSchedId'].value = "";	// 검색시 Tab 이동Key Clear
		
	findSchedList();
	
	//Clear Update Mark for this page 
	clearUpdate(currentPageId);
	
	searchList();
	
	isEdit = false;
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(yyyymmdd,type)
{
    var url = contextPath + "/workCalPmDInsMonthList.do";
    workCalPmDInsMonthListForm.elements['strutsAction'].value = '<%=WorkCalPmDInsMonthListAction.MONTH_SCHED_LIST_FIND%>';
    workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.yyyymmdd'].value     = yyyymmdd;
    workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.pmSchedStatus'].value    = type;
    
    doGridAction("Search", myGrid, "gridbox", url, FormQueryString(workCalPmDInsMonthListForm));
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pmInsListId)
{
	goSearch();
	workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.pmInsDListId'].value = _pmInsListId;
	findSchedRow("ReloadRow");
	workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.pmInsDListId'].value = "";
}

function findSchedRow(sheetAction) {
	var url = contextPath + "/workCalPmDInsMonthList.do";
	workCalPmDInsMonthListForm.elements['strutsAction'].value = '<%=WorkCalPmDInsMonthListAction.MONTH_SCHED_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workCalPmDInsMonthListForm), "pmInsDSchedId","Y");
}

/**
 * 저장후 리스트만 재조회
 */
 function searchList(){
	var url = contextPath + "/workCalPmDInsMonthList.do";
    workCalPmDInsMonthListForm.elements['strutsAction'].value = '<%=WorkCalPmDInsMonthListAction.MONTH_SCHED_LIST_FIND%>';

    myGrid.clearAll(); setLoading("gridbox");
    $.post(url,FormQueryString(workCalPmDInsMonthListForm), function(_data){
    	myGrid.parse(_data,"js");
    });
}
/**
 * 월간작업일정 조회
 */
function findSchedList(){
	var url = contextPath + "/workCalPmDInsMonthList.do";
	workCalPmDInsMonthListForm.elements['strutsAction'].value = '<%=WorkCalPmDInsMonthListAction.MONTH_SCHED_FIND%>';
	setModal();
	$.post(url,FormQueryString(workCalPmDInsMonthListForm), function(_data){
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
	document.getElementById("month_title").innerText = workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.yyyymmdd'].value;
	
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
	workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.pmInsDSchedId'].value = "";	// 검색시 Tab 이동Key Clear
	workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.pmInsDListId'].value = "";
    workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.pmSchedStatus'].value = "";
	
	workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.yyyymmdd'].value      = workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.filterYyyymm'].value;
    workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.deptId'].value        = workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.filterDeptId'].value;       
    workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.deptDesc'].value      = workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.filterDeptDesc'].value;     
    workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.eqLocId'].value       = workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.filterEqLocId'].value;      
    workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.eqLocDesc'].value     = workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.filterEqLocDesc'].value;    
    workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.eqCtgId'].value       = workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.filterEqCtgId'].value;      
    workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.eqCtgDesc'].value     = workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.filterEqCtgDesc'].value;    
    workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.eqCtgTypeId'].value   = workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.filterEqCtgTypeId'].value;  
    workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.eqCtgTypeDesc'].value = workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.filterEqCtgTypeDesc'].value;
    workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.mainMngId'].value     = workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.filterMainMngId'].value;    
    workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.mainMngName'].value   = workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.filterMainMngName'].value;  
    workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.subMngId'].value      = workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.filterSubMngId'].value;     
    workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.subMngName'].value    = workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.filterSubMngName'].value;   
    workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.plfTypeId'].value     = workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.filterPlfTypeId'].value;    
    workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.plfTypeDesc'].value   = workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.filterPlfTypeDesc'].value;  
    workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.wkCtrId'].value       = workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.filterWkCtrId'].value;      
    workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.wkCtrDesc'].value     = workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.filterWkCtrDesc'].value;    
    workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.equipId'].value       = workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.filterEquipId'].value;      
    workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.equipDesc'].value     = workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.filterEquipDesc'].value;    
    workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.isLawEq'].value       = workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.filterIsLawEq'].value;      
    workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.pmNo'].value          = workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.filterPmNo'].value;         
    workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.empId'].value         = workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.filterEmpId'].value;        
    workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.empDesc'].value       = workCalPmDInsMonthListForm.elements['workCalPmDInsMonthCommonDTO.filterEmpDesc'].value;      
	
	findSchedList();
}

/**
 * 삭제
 */
 function goDelete(){
    //myGrid에 1(2번째)번에 체크가 되어있으면 2번 데이터 가져오기
     var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'PMINSDSCHEDID'); //Grid, check box column seq, pk column seq
    //체크된게 없으면 return
     if(typeof delArray == "undefined"){
            alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
            return;
        }

     workCalPmDInsMonthListForm.strutsAction.value = '<%=WorkCalPmDInsMonthListAction.MONTH_SCHED_LIST_DELETE%>';
    var url = contextPath + "/workCalPmDInsMonthList.do";

    $.post(url,FormQueryString(workCalPmDInsMonthListForm)+delArray , function(_data){
        afterDelete();
    });
  }

function afterDelete(){
    //goClose('workCalPmDInsMonthDetail');
    goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
  }
  
  

/**
 * Tab 이동시 호출
 */
function goTabPage(pageId)
{
	tabValidationCheck(myGrid, pageId, "goTabPageAction");
}
function goTabPageAction(pageId, lv_selectedId)
{
	var form = document.workCalPmDInsMonthListForm;
	
	var pmStatus = getValueById(myGrid, selectedId, "PMSCHEDSTATUS");
	
	if(pmStatus == "P")
	{
		form.elements['workCalPmDInsMonthCommonDTO.pmInsDSchedId'].value =  getValueById(myGrid, lv_selectedId,'PMINSDSCHEDID');
		goCommonTabPage(form, <%= WorkCalPmDInsMonthDetailAction.MONTH_SCHED_DETAIL_INIT %>, pageId, beforePageId);
	}
	else
	{
		form.elements['workPmiDInsCommonDTO.pmInsDListId'].value = getValueById(myGrid, lv_selectedId, "PMINSDLISTID");
		goCommonTabPage(form, <%= WorkPmiDInsDetailAction.DETAIL_INIT %>, pageId, beforePageId);
	}

	beforePageId = pageId;
}

/**
 * 상세 열기
 */
function goOpen(rowId)
{
	var pmStatus = getValueById(myGrid, rowId, "PMSCHEDSTATUS");
    var woparam = "";
	
	if(pmStatus == "P")
	{
	    woparam = "workCalPmDInsMonthDetail";
		goTabPage(woparam);
	}
	else
	{
	    var pmType = getValueById(myGrid, lv_selectedId, "PMTYPE");
	    pmType = pmType.substring(0,1)+pmType.substring(1,pmType.length).toLowerCase();

	    woparam = "workPmiDInsDetail";
		goTabPage(woparam);
	}
	
	beforePageId = woparam;
	
}


/**
 * 엑셀 다운.
 */
function goExcel()
{
	excelServerAction("workCalPmDInsMonthList", workCalPmDInsMonthListForm);  
}

/**
 * 일별 확정
 */
function goFix(){
	//myGrid에 1(2번째)번에 체크가 되어있으면 2번 데이터 가져오기
	 var fixArray = getSelectRows(myGrid, 'ISDELCHECK', 'PMINSDSCHEDID'); //Grid, check box column seq, pk column seq

	//체크된게 없으면 return
	 if(typeof fixArray == "undefined"){
			alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
			return;
		}

	dhtmlx.confirm('<bean:message key="MESSAGE.MSG045"/>', function(result){
		if(result){
			workCalPmDInsMonthListForm.strutsAction.value = '<%=WorkCalPmDInsMonthListAction.MONTH_SCHED_DAILY_SCHEDULED%>';
		 	var url = contextPath + "/workCalPmDInsMonthList.do";

		 	ajaxPost(url,FormQueryString(workCalPmDInsMonthListForm)+fixArray)
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
<html:form action="/workCalPmDInsMonthList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="workCalPmDInsMonthCommonDTO.pmInsDSchedId"/><!-- Key -->
<html:hidden property="workCalPmDInsMonthCommonDTO.filterDeptId"/>
<html:hidden property="workCalPmDInsMonthCommonDTO.yyyymmdd"/>
<html:hidden property="workCalPmDInsMonthCommonDTO.pmSchedStatus"/>
<html:hidden property="workCalPmDInsMonthCommonDTO.deptId"/>
<html:hidden property="workCalPmDInsMonthCommonDTO.deptDesc"/>
<html:hidden property="workCalPmDInsMonthCommonDTO.eqLocId"/>
<html:hidden property="workCalPmDInsMonthCommonDTO.eqLocDesc"/>
<html:hidden property="workCalPmDInsMonthCommonDTO.eqCtgId"/>
<html:hidden property="workCalPmDInsMonthCommonDTO.eqCtgDesc"/>
<html:hidden property="workCalPmDInsMonthCommonDTO.eqCtgTypeId"/>
<html:hidden property="workCalPmDInsMonthCommonDTO.eqCtgTypeDesc"/>
<html:hidden property="workCalPmDInsMonthCommonDTO.mainMngId"/>
<html:hidden property="workCalPmDInsMonthCommonDTO.mainMngName"/>
<html:hidden property="workCalPmDInsMonthCommonDTO.subMngId"/>
<html:hidden property="workCalPmDInsMonthCommonDTO.subMngName"/>
<html:hidden property="workCalPmDInsMonthCommonDTO.plfTypeId"/>
<html:hidden property="workCalPmDInsMonthCommonDTO.plfTypeDesc"/>
<html:hidden property="workCalPmDInsMonthCommonDTO.isLawEq"/>
<html:hidden property="workCalPmDInsMonthCommonDTO.wkCtrId"/>
<html:hidden property="workCalPmDInsMonthCommonDTO.wkCtrDesc"/>
<html:hidden property="workCalPmDInsMonthCommonDTO.equipId"/>
<html:hidden property="workCalPmDInsMonthCommonDTO.equipDesc"/>
<html:hidden property="workCalPmDInsMonthCommonDTO.pmNo"/>
<html:hidden property="workCalPmDInsMonthCommonDTO.filterEqLocId"/>
<html:hidden property="workCalPmDInsMonthCommonDTO.filterEqCtgId"/>
<html:hidden property="workCalPmDInsMonthCommonDTO.filterEqCtgTypeId"/>
<html:hidden property="workCalPmDInsMonthCommonDTO.filterPlfTypeId"/>
<html:hidden property="workCalPmDInsMonthCommonDTO.filterPlfTypeDesc"/>
<html:hidden property="workCalPmDInsMonthCommonDTO.filterWkCtrId"/>
<html:hidden property="workCalPmDInsMonthCommonDTO.filterMainMngId"/>
<html:hidden property="workCalPmDInsMonthCommonDTO.filterSubMngId"/>
<html:hidden property="workCalPmDInsMonthCommonDTO.filterEquipId"/>

<html:hidden property="workCalPmDInsMonthCommonDTO.filterEmpId"/>
<html:hidden property="workCalPmDInsMonthCommonDTO.empId"/>
<html:hidden property="workCalPmDInsMonthCommonDTO.empDesc"/>
<html:hidden property="workCalPmDInsMonthCommonDTO.pmInsDListId"/>

<html:hidden property="workPmiDInsCommonDTO.pmInsDListId"/>
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
						<html:text property="workCalPmDInsMonthCommonDTO.filterYyyymm" readonly="true"/>
						<p class="open_mon_calendar"><span>날짜</span></p>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.pmNo"/></label>
					<div class="input_box">
						<html:text property="workCalPmDInsMonthCommonDTO.filterPmNo" tabindex="10"/>
					</div>
				</div>
				<!-- 관리부서 -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="workCalPmDInsMonthCommonDTO.filterDeptDesc" tabindex="20" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 담당자 -->
                <div class="field">
                    <label><bean:message key="LABEL.manager"/></label>
                    <div class="input_box">
                        <html:text property="workCalPmDInsMonthCommonDTO.filterEmpDesc" tabindex="22" />
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
				<!-- 설비 -->
				<div class="field">
					<label><bean:message key="LABEL.equipDesc"/></label>
					<div class="input_box">
						<html:text property="workCalPmDInsMonthCommonDTO.filterEquipDesc" tabindex="25" />
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
						<html:text property="workCalPmDInsMonthCommonDTO.filterEqLocDesc" tabindex="30" />
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
						<html:text property="workCalPmDInsMonthCommonDTO.filterEqCtgDesc" tabindex="35" />
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
						<html:text property="workCalPmDInsMonthCommonDTO.filterIsLawEq" tabindex="60" />
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
						<html:text property="workCalPmDInsMonthCommonDTO.filterMainMngName" tabindex="70" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 관리자(부) -->
				<div class="field">
					<label><bean:message key="LABEL.subManager"/></label>
					<div class="input_box">
						<html:text property="workCalPmDInsMonthCommonDTO.filterSubMngName" tabindex="90" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				
				<!-- 작업그룹 -->
                <div class="field">
                     <label><bean:message key="LABEL.wkCtr"/></label>
                     <div class="input_box">
                        <html:text property="workCalPmDInsMonthCommonDTO.filterWkCtrDesc" tabindex="140" />
                         <p class="open_spop">
                             <a>
                                 <span>조회</span>
                             </a>
                         </p>
                   </div>
                </div>
				<!-- 설비유형  -->
				<div class="field">
					<label><bean:message key="LABEL.eqCtgType"/></label>
					<div class="input_box">
						<html:text property="workCalPmDInsMonthCommonDTO.filterEqCtgTypeDesc" tabindex="170" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
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
				<div class="fb_group2">
				</div>
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
				<div class="b_line"></div>
				<div class="fb_group1">
					
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
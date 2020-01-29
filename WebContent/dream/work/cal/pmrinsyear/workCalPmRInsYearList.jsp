<%--===========================================================================
연간작업일정 - 목록
author  kim21017
version $Id: workCalPmRInsYearList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.cal.pmrinsyear.action.WorkCalPmRInsYearListAction" %>
<%@ page import="dream.work.cal.pmrinsperiod.action.WorkCalPmRInsPeriodDetailAction" %>
<%@ page import="dream.work.pmi.list.action.WorkPmiDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId());
%>
<html>
<head>
<!-- 예방작업일정 -->
<title><bean:message key='MENU.WORKCALPMRINSYEAR'/></title>
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
var empAc;
var exeEmpAc;
var usageDeptAc;

function loadPage()
{
	if(window.name != "LINKED_POPUP")
	{
		//부서
		workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterDeptId'].value    = loginUser.filterDeptId;
		workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterDeptDesc'].value  = loginUser.filterDeptDesc;
		//년도
	    workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterYear'].value = dateToData(getToday()).substr(0, 4);
		if(loginUser.eqLocId!='null'){
			workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterEqLocId'].value = loginUser.eqLocId;
			workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterEqLocDesc'].value = loginUser.eqLocDesc;
		}
	/*     if(loginUser.wkctrId!='null'){
	    	workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterWkCtrId'].value = loginUser.wkctrId;
	    	workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterWkCtrDesc'].value = loginUser.wkctrDesc;
		} */
	    if(loginUser.filterWkCtrId!='null'){
	    	workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterWkCtrId'].value = loginUser.filterWkCtrId;
	    	workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterWkCtrDesc'].value = loginUser.filterWkCtrDesc;
		}
	    if(loginUser.eqCtgTypeId!='null'){
	    	workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterEqCtgTypeId'].value = loginUser.eqCtgTypeId;
	    	workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterEqCtgTypeDesc'].value = loginUser.eqCtgTypeDesc;
		}
	  	//공장명
	    if(loginUser.filterPlant!='null'){
	       workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
	       workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	    }
	}
  
    initGrid();

	hideBtn("SAVE");
	hideBtn("EDITCNCL");

    deptAc = new autoC({"workCalPmRInsYearCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcDisplay("DESCRIPTION");
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "workCalPmRInsYearCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "workCalPmRInsYearCommonDTO.filterPlantId"
    	,"plantDesc" : "workCalPmRInsYearCommonDTO.filterPlantDesc"
    });
    deptAc.init();
    
    exeDeptAc = new autoC({"workCalPmRInsYearCommonDTO.filterExeDeptDesc":"description"});
    exeDeptAc.setAcDisplay("DESCRIPTION");
    exeDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    exeDeptAc.setTable("TADEPT");
    exeDeptAc.setAcResultMap({
        "workCalPmRInsYearCommonDTO.filterExeDeptId":"dept_id"
    });
    exeDeptAc.setAcDConditionMap({
    	"plant" : "workCalPmRInsYearCommonDTO.filterPlantId"
    	,"plantDesc" : "workCalPmRInsYearCommonDTO.filterPlantDesc"
    });
    exeDeptAc.init();
    
    //------------------------------------------------------------------------------------
    
    usageDeptAc = new autoC({"workCalPmRInsYearCommonDTO.filterUsageDeptDesc":"description"});
    usageDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    usageDeptAc.setTable("TADEPT");
    usageDeptAc.setAcResultMap({
        "workCalPmRInsYearCommonDTO.filterUsageDeptId":"dept_id"
    });
    usageDeptAc.setAcDConditionMap({
    	"plant" : "workCalPmRInsYearCommonDTO.filterPlantId"
    	,"plantDesc" : "workCalPmRInsYearCommonDTO.filterPlantDesc"
    });
    usageDeptAc.init();
    
    //-------------------------------------------------------------------------------------

    equipDescAc = new autoC({"workCalPmRInsYearCommonDTO.filterEquipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcResultMap({
        "workCalPmRInsYearCommonDTO.filterEquipId":"equip_id"
    });
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "workCalPmRInsYearCommonDTO.filterEqLocId",
    	"eqctg_id" : "workCalPmRInsYearCommonDTO.filterEqCtgId",
    	"dept_id" : "workCalPmRInsYearCommonDTO.filterDeptId",
    	"plant" : "workCalPmRInsYearCommonDTO.filterPlantId"
    });
    equipDescAc.init();

    eqLocDescAc = new autoC({"workCalPmRInsYearCommonDTO.filterEqLocDesc":"full_desc"});
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcResultMap({
        "workCalPmRInsYearCommonDTO.filterEqLocId":"eqloc_id"
    });
    eqLocDescAc.setAcDConditionMap({
    	"plant" : "workCalPmRInsYearCommonDTO.filterPlantId"
    });
    eqLocDescAc.init();

    eqCtgTypeAc = new autoC({"workCalPmRInsYearCommonDTO.filterEqCtgDesc":"full_desc"});
    eqCtgTypeAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcResultMap({
        "workCalPmRInsYearCommonDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();

	//법정설비여부  AC
    acSysDesc("workCalPmRInsYearCommonDTO.filterIsLawEq","workCalPmRInsYearCommonDTO.filterIsLawEq","IS_USE",false);

    mainMngAc = new autoC({"workCalPmRInsYearCommonDTO.filterMainMngName":"emp_name"});
    mainMngAc.setAcDisplay("EMP_NAME");
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "workCalPmRInsYearCommonDTO.filterMainMngId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "workCalPmRInsYearCommonDTO.filterDeptId",
    	"plant" : "workCalPmRInsYearCommonDTO.filterPlantId"
    });
    mainMngAc.init();

    wkCtrDescAc = new autoC({"workCalPmRInsYearCommonDTO.filterWkCtrDesc":"description"});
    wkCtrDescAc.setAcDisplay("DESCRIPTION");
    wkCtrDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    wkCtrDescAc.setTable("TAWKCTR");
    wkCtrDescAc.setAcResultMap({
        "workCalPmRInsYearCommonDTO.filterWkCtrId":"wkctr_id"
    });
    wkCtrDescAc.init();

	//설비유형  AC
    acSysDesc("workCalPmRInsYearCommonDTO.filterEqCtgTypeDesc","workCalPmRInsYearCommonDTO.filterEqCtgTypeId","EQCTG_TYPE");

    subMngAc = new autoC({"workCalPmRInsYearCommonDTO.filterSubMngName":"emp_name"});
    subMngAc.setAcDisplay("EMP_NAME");
    subMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    subMngAc.setTable("TAEMP");
    subMngAc.setAcResultMap({
        "workCalPmRInsYearCommonDTO.filterSubMngId":"emp_id"
    });
    subMngAc.setAcDConditionMap({
    	"dept_id" : "workCalPmRInsYearCommonDTO.filterDeptId",
    	"plant" : "workCalPmRInsYearCommonDTO.filterPlantId"
    });
    subMngAc.init();
    
    // 담당자 자동완성
    empAc = new autoC({"workCalPmRInsYearCommonDTO.filterEmpDesc":"emp_name"});
    empAc.setTable("TAEMP");
    empAc.setAcResultMap({
        "workCalPmRInsYearCommonDTO.filterEmpId":"emp_id"
      , "IS_USE":"Y"
    });
    empAc.setAcDConditionMap({
    	"dept_id" : "workCalPmRInsYearCommonDTO.filterDeptId",
    	"plant" : "workCalPmRInsYearCommonDTO.filterPlantId"
    });
    empAc.init();
    
    exeEmpAc = new autoC({"workCalPmRInsYearCommonDTO.filterExeEmpDesc":"emp_name"});
    exeEmpAc.setTable("TAEMP");
    exeEmpAc.setAcResultMap({
        "workCalPmRInsYearCommonDTO.filterExeEmpId":"emp_id"
      , "IS_USE":"Y"
    });
    exeEmpAc.setAcDConditionMap({
    	"dept_id" : "workCalPmRInsYearCommonDTO.filterExeDeptId",
    	"plant" : "workCalPmRInsYearCommonDTO.filterPlantId"
    });
    exeEmpAc.init();
    
    plantAc = new autoC({"workCalPmRInsYearCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "workCalPmRInsYearCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
}


/*작업종류 선택후 실행*/
function afterSetValue(lovType)
{
	
}


/**
 * 그리드 초기화
 */
function initGrid()
{
	var selDate;

	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	myGrid.enableSmartRendering(true,500);

/* 	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.pmInsSchedId'].value = "";
    	return sortColumn("workCalPmRInsYearList", this, workCalPmRInsYearListForm, "pmInsSchedId", ind, direction);
	}); */
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
     	workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.pmInsSchedId'].value = "";	
        return sortColumn("workCalPmRInsYearList", this, workCalPmRInsYearListForm, "PMINSSCHEDID", ind, direction);
    });
	
	myGrid.attachEvent("onBeforeSelect",function(rowId, state){
		lv_selectedId = rowId;
		var status 		= getValueById(myGrid, rowId, "WOSTATUS");
		var pmStatus 	= getValueById(myGrid, rowId, "PMSTATUSCODE");
	    var index 		= getIndexById(myGrid, "SCHEDDATE");

	    //console.log(getValueById(myGrid, rowId, "SCHEDDATE")+"    "+pmStatus);
	    
		if(isEdit)
		{
			if(pmStatus == "C"|| pmStatus == "S") //완료되거나 스케줄 확정되면 스케줄 변화안됨.
			{
				 //disable
				 myGrid.cells(rowId,index).setDisabled(true);
				 //return false;
			}
			else
			{
				myGrid.cells(rowId,index).setDisabled(false);
				//return true;
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
	
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	setHeader(myGrid, "gridbox"); // grid, grid id
}

<%-- function setUGrid()
{
	var url = contextPath + "/workCalPmRInsYearList.do";
	proGrid = setGridUpdate(url, '<%=WorkCalPmRInsYearListAction.YEAR_SCHED_CHANGE%>', myGrid);
	
	goSearch();
} --%>
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
	var url = contextPath + "/workCalPmRInsYearList.do";
	var stAct = "<%=WorkCalPmRInsYearListAction.YEAR_SCHED_CHANGE %>";

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
	
	workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.pmInsSchedId'].value = "";	// 검색시 Tab 이동Key Clear
	
	//Clear Update Mark for this page 
	clearUpdate(currentPageId);
	
	searchList();
	
	isEdit = false;
	
	//Make Page as Normal
	setForNormal();
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(yyyymm,status)
{
    var url = contextPath + "/workCalPmRInsYearList.do";
    workCalPmRInsYearListForm.elements['strutsAction'].value = '<%=WorkCalPmRInsYearListAction.YEAR_SCHED_LIST_FIND%>';
    workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.yyyymm'].value = yyyymm;
    workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.schedType'].value = status;

    doGridAction('Search', myGrid, "gridbox", url, FormQueryString(workCalPmRInsYearListForm), "pmInsSchedId", "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pmInsListId)
{
	goSearch();
	workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.pmInsListId'].value = _pmInsListId;
	findSchedRow("ReloadRow");
	workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.pmInsListId'].value = "";
}

function findSchedRow(sheetAction) {
	var url = contextPath + "/workCalPmRInsYearList.do";
	workCalPmRInsYearListForm.elements['strutsAction'].value = '<%=WorkCalPmRInsYearListAction.YEAR_SCHED_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workCalPmRInsYearListForm), "pmInsSchedId","Y");
}

/**
 * 저장후 리스트만 재조회
 */
 function searchList(){
	 lv_selectedId = "";
	var url = contextPath + "/workCalPmRInsYearList.do";
    workCalPmRInsYearListForm.elements['strutsAction'].value = '<%=WorkCalPmRInsYearListAction.YEAR_SCHED_LIST_FIND%>';

    myGrid.clearAll(); setLoading("gridbox");
    $.post(url,FormQueryString(workCalPmRInsYearListForm), function(_data){
    	myGrid.parse(_data,"js");
    });
}
/**
 * 연간작업일정 조회
 */
function findSchedList(){

	var url = contextPath + "/workCalPmRInsYearList.do";
	workCalPmRInsYearListForm.elements['strutsAction'].value = '<%=WorkCalPmRInsYearListAction.YEAR_SCHED_FIND%>';
	setModal();

	$.post(url,FormQueryString(workCalPmRInsYearListForm), function(_data){
		afterFindSched(JSON.parse(_data));
    });

}

function afterFindSched(_dataObj){
	closeModal();
	
	var _yyyy = workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.yyyymm'].value;
	
	document.getElementById("year_title").innerText = _yyyy+"<bean:message key='LABEL.year'/>";
	
	Object.keys(_dataObj).forEach(function(key){
		var _cntObject = _dataObj[key];
		
		var _yyyymm;
		if(key=='TOTAL') {
			_yyyymm = _yyyy;
		}
		else {
			_yyyymm = _yyyy+key;
		}
		var _totCnt = _cntObject.TOTAL;
		var _sCnt = _cntObject.S + _cntObject.IRWRA + _cntObject.IRWOA + _cntObject.IRWDA;
		var _cCnt = _cntObject.C;
		
		var str = "<span class='p'><a href=\"javascript:findGridList("+"'"+_yyyymm+"', '');\">"+_totCnt+"</a></span> /"+
					"<span class='s'><a href=\"javascript:findGridList("+"'"+_yyyymm+"', 'S+IRWRA+IRWOA+IRWDA');\">"+_sCnt+"</a></span> /"+
					"<span class='c'><a href=\"javascript:findGridList("+"'"+_yyyymm+"', 'C');\">"+_cCnt+"</a></span>";
		
		document.getElementById("td_"+key).innerHTML = str;
	});
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.pmInsSchedId'].value = "";	// 검색시 Tab 이동Key Clear
	workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.pmInsListId'].value = "";
    workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.schedType'].value = "";
	
	workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.yyyymm'].value 			= workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterYear'].value;
    workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.deptId'].value      		= workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterDeptId'].value;       
    workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.deptDesc'].value    		= workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterDeptDesc'].value;     
    workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.exeDeptId'].value        = workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterExeDeptId'].value;       
    workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.exeDeptDesc'].value    	= workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterExeDeptDesc'].value;     
    workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.eqLocId'].value     		= workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterEqLocId'].value;      
    workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.eqLocDesc'].value   		= workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterEqLocDesc'].value;    
    workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.eqCtgId'].value     		= workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterEqCtgId'].value;      
    workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.eqCtgDesc'].value   		= workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterEqCtgDesc'].value;    
    workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.eqCtgTypeId'].value     	= workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterEqCtgTypeId'].value;  
    workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.eqCtgTypeDesc'].value   	= workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterEqCtgTypeDesc'].value;
    workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.mainMngId'].value        = workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterMainMngId'].value;    
    workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.mainMngName'].value      = workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterMainMngName'].value;  
    workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.subMngId'].value         = workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterSubMngId'].value;     
    workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.subMngName'].value       = workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterSubMngName'].value;   
    workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.plfTypeId'].value        = workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterPlfTypeId'].value;    
    workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.plfTypeDesc'].value      = workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterPlfTypeDesc'].value;  
    workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.wkCtrId'].value          = workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterWkCtrId'].value;      
    workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.wkCtrDesc'].value        = workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterWkCtrDesc'].value;    
    workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.equipId'].value          = workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterEquipId'].value;      
    workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.equipDesc'].value        = workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterEquipDesc'].value;    
    workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.empId'].value            = workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterEmpId'].value;        
    workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.empDesc'].value          = workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterEmpDesc'].value;      
    workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.exeEmpId'].value         = workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterExeEmpId'].value;        
    workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.exeEmpDesc'].value       = workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterExeEmpDesc'].value;      
    workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.isLawEq'].value          = workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterIsLawEq'].value;      
    workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.pmNo'].value             = workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterPmNo'].value;         
    workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.plantId'].value          = workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterPlantId'].value;      
    workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.plantDesc'].value        = workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterPlantDesc'].value;    
	
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
	var form = document.workCalPmRInsYearListForm;
	
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
	//goClose(beforePageId, this);	
	var pmStatus = getValueById(myGrid, rowId, "pmStatusCode");
	
	if(pmStatus == "P")
	{
		goTabPage('workCalPmRInsPeriodDetail');
	}
	else
	{
		var pmInsListId = getValueById(myGrid, lv_selectedId, "pmInsListId");
	    var pmType = getValueById(myGrid, lv_selectedId, "PMTYPE");

	    if(pmInsListId == "undefined" || pmInsListId == "") 
	    {
	        alertMessage1('<bean:message key="MESSAGE.MSG0024"/>');
	        return; 
	    }
	    
	    var woparam = "workPmiDetail";
	    pmType = pmType.substring(0,1)+pmType.substring(1,pmType.length).toLowerCase();
	    
		workCalPmRInsYearListForm.elements['workPmiCommonDTO.pminslistId'].value = pmInsListId;
		
	    var param = "strutsAction=8001&workPmiCommonDTO.pminslistId="+ pmInsListId;
		
		goTabPage(woparam);
	}
	
}


/**
 * 엑셀 다운.
 */
 function goExcel()
 {
 	workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.pmInsSchedId'].value = "";	// 검색시 Tab 이동Key Clear
 	excelServerAction("workCalPmRInsYearList", workCalPmRInsYearListForm );
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

  	workCalPmRInsYearListForm.strutsAction.value = '<%=WorkCalPmRInsYearListAction.YEAR_SCHED_LIST_DELETE%>';
  	var url = contextPath + "/workCalPmRInsYearList.do";

  	$.post(url,FormQueryString(workCalPmRInsYearListForm)+delArray , function(_data){
    	afterDelete();
    });
  }

function afterDelete(){
	goClose('workCalPmRInsYearDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
  }
function goPrint()
{
	var deptId = workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterDeptId'].value==''?'0':workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterDeptId'].value;
	var eqLocId = workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterEqLocId'].value==''?'0':workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterEqLocId'].value;
	var plantId = workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterPlantId'].value;
	
	reportCall('workCalPmRInsYearList','workCalPmRInsYearList', "<%=user.getCompNo()%>", "<%=user.getUserId()%>", "<%=user.getLangId()%>",workCalPmRInsYearListForm.elements['workCalPmRInsYearCommonDTO.filterYear'].value,deptId,eqLocId,plantId); 
} 
  function goInssched(){
	  goPrint();
  }

/**
 * 일별 확정
 */
function goFix()
{
	
	//myGrid에 1(2번째)번에 체크가 되어있으면 2번 데이터 가져오기
	 var fixArray = getSelectRows(myGrid, 'ISDELCHECK', 'PMINSSCHEDID'); //Grid, check box column seq, pk column seq
	//체크된게 없으면 return
	if(typeof fixArray == "undefined")
	{
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		 return;
	}
	 
	dhtmlx.confirm('<bean:message key="MESSAGE.MSG045"/>', function(result){
		if(result)
		{
			workCalPmRInsYearListForm.strutsAction.value = '<%= WorkCalPmRInsYearListAction.YEAR_SCHED_DAILY_SCHEDULED%>';
		 	var url = contextPath + "/workCalPmRInsYearList.do";
		 	
		 	ajaxPost(url,FormQueryString(workCalPmRInsYearListForm)+fixArray)
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
<html:form action="/workCalPmRInsYearList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="workCalPmRInsYearCommonDTO.filterDeptId"/>
<html:hidden property="workCalPmRInsYearCommonDTO.filterExeDeptId"/>
<html:hidden property="workCalPmRInsYearCommonDTO.filterEqLocId"/>
<html:hidden property="workCalPmRInsYearCommonDTO.filterEqCtgId"/>
<html:hidden property="workCalPmRInsYearCommonDTO.filterEqCtgTypeId"/>
<html:hidden property="workCalPmRInsYearCommonDTO.filterPlfTypeId"/>
<html:hidden property="workCalPmRInsYearCommonDTO.filterPlfTypeDesc"/>
<html:hidden property="workCalPmRInsYearCommonDTO.filterWkCtrId"/>
<html:hidden property="workCalPmRInsYearCommonDTO.filterMainMngId"/>
<html:hidden property="workCalPmRInsYearCommonDTO.filterSubMngId"/>
<html:hidden property="workCalPmRInsYearCommonDTO.filterEquipId"/>

<html:hidden property="workCalPmRInsYearCommonDTO.pmInsSchedId"/><!-- Key -->
<html:hidden property="workCalPmRInsYearCommonDTO.yyyymm"/>
<html:hidden property="workCalPmRInsYearCommonDTO.schedType"/>
<html:hidden property="workCalPmRInsYearCommonDTO.deptId"/>
<html:hidden property="workCalPmRInsYearCommonDTO.deptDesc"/>
<html:hidden property="workCalPmRInsYearCommonDTO.exeDeptId"/>
<html:hidden property="workCalPmRInsYearCommonDTO.exeDeptDesc"/>
<html:hidden property="workCalPmRInsYearCommonDTO.eqLocId"/>
<html:hidden property="workCalPmRInsYearCommonDTO.eqLocDesc"/>
<html:hidden property="workCalPmRInsYearCommonDTO.eqCtgId"/>
<html:hidden property="workCalPmRInsYearCommonDTO.eqCtgDesc"/>
<html:hidden property="workCalPmRInsYearCommonDTO.eqCtgTypeId"/>
<html:hidden property="workCalPmRInsYearCommonDTO.eqCtgTypeDesc"/>
<html:hidden property="workCalPmRInsYearCommonDTO.mainMngId"/>
<html:hidden property="workCalPmRInsYearCommonDTO.mainMngName"/>
<html:hidden property="workCalPmRInsYearCommonDTO.subMngId"/>
<html:hidden property="workCalPmRInsYearCommonDTO.subMngName"/>
<html:hidden property="workCalPmRInsYearCommonDTO.plfTypeId"/>
<html:hidden property="workCalPmRInsYearCommonDTO.plfTypeDesc"/>
<html:hidden property="workCalPmRInsYearCommonDTO.wkCtrId"/>
<html:hidden property="workCalPmRInsYearCommonDTO.wkCtrDesc"/>
<html:hidden property="workCalPmRInsYearCommonDTO.isLawEq"/>
<html:hidden property="workCalPmRInsYearCommonDTO.equipId"/>
<html:hidden property="workCalPmRInsYearCommonDTO.equipDesc"/>
<html:hidden property="workCalPmRInsYearCommonDTO.pmNo"/>
<html:hidden property="workCalPmRInsYearCommonDTO.pmInsListId"/>

<html:hidden property="workCalPmRInsYearCommonDTO.filterEmpId"/>
<html:hidden property="workCalPmRInsYearCommonDTO.filterExeEmpId"/>
<html:hidden property="workCalPmRInsYearCommonDTO.empId"/>
<html:hidden property="workCalPmRInsYearCommonDTO.empDesc"/>
<html:hidden property="workCalPmRInsYearCommonDTO.exeEmpId"/>
<html:hidden property="workCalPmRInsYearCommonDTO.exeEmpDesc"/>
<html:hidden property="workCalPmRInsYearCommonDTO.plantId"/>
<html:hidden property="workCalPmRInsYearCommonDTO.plantDesc"/>
<html:hidden property="workCalPmRInsYearCommonDTO.filterPlantId"/>
<html:hidden property="workCalPmRInsYearCommonDTO.filterUsageDeptId"/>

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
				<!-- 년도 -->
				<div class="field">
					<label><bean:message key="LABEL.year"/></label>
					<div class="input_read">
						<html:text property="workCalPmRInsYearCommonDTO.filterYear" readonly="true"/>
						<p class="open_year_calendar"><span>날짜</span></p>
					</div>
				</div>
				<!-- 예방 작업 번호 -->
				<div class="field">
					<label><bean:message key="LABEL.pmNo"/></label>
					<div class="input_box">
						<html:text property="workCalPmRInsYearCommonDTO.filterPmNo" tabindex="10"/>
					</div>
				</div>
				<!-- 계획부서 -->
				<div class="field">
					<label><bean:message key="LABEL.planDept"/></label>
					<div class="input_box">
						<html:text property="workCalPmRInsYearCommonDTO.filterDeptDesc" tabindex="20" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 실행부서 -->
				<div class="field">
					<label><bean:message key="LABEL.exeDept"/></label>
					<div class="input_box">
						<html:text property="workCalPmRInsYearCommonDTO.filterExeDeptDesc" tabindex="21" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 사용부서 -->
				<div class="field">
					<label><bean:message key="LABEL.usedDept"/></label>
					<div class="input_box">
						<html:text property="workCalPmRInsYearCommonDTO.filterUsageDeptDesc" tabindex="22" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 계획담당자 -->
				<div class="field">
					<label><bean:message key="LABEL.planEmp"/></label>
					<div class="input_box">
						<html:text property="workCalPmRInsYearCommonDTO.filterEmpDesc" tabindex="23" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 실행담당자 -->
				<div class="field">
					<label><bean:message key="LABEL.exeEmp"/></label>
					<div class="input_box">
						<html:text property="workCalPmRInsYearCommonDTO.filterExeEmpDesc" tabindex="24" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 설비 -->
				<div class="field">
					<label><bean:message key="LABEL.equipDesc"/></label>
					<div class="input_box">
						<html:text property="workCalPmRInsYearCommonDTO.filterEquipDesc" tabindex="25" />
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
						<html:text property="workCalPmRInsYearCommonDTO.filterEqLocDesc" tabindex="30" />
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
						<html:text property="workCalPmRInsYearCommonDTO.filterEqCtgDesc" tabindex="35" />
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
						<html:text property="workCalPmRInsYearCommonDTO.filterIsLawEq" tabindex="60" />
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
						<html:text property="workCalPmRInsYearCommonDTO.filterMainMngName" tabindex="70" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 관리자(부) -->
				<div class="field">
					<label><bean:message key="LABEL.subManager"/></label>
					<div class="input_box">
						<html:text property="workCalPmRInsYearCommonDTO.filterSubMngName" tabindex="90" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				
				<!-- 작업그룹  -->
                <div class="field">
                     <label><bean:message key="LABEL.wkCtr"/></label>
                     <div class="input_box">
                        <html:text property="workCalPmRInsYearCommonDTO.filterWkCtrDesc" tabindex="140" />
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
						<html:text property="workCalPmRInsYearCommonDTO.filterEqCtgTypeDesc" tabindex="150" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
							<html:text property="workCalPmRInsYearCommonDTO.filterPlantDesc" tabindex="160" />
							<p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <c:set var="filePath" value="enhance/${compName}/work/cal/pmrinsyear/workCalPmRInsYearList_${compNo}.jsp" />
				<c:if test="${udf:isExist(filePath)}">
					<c:import charEncoding="UTF-8" url="/enhance/${compName}/work/cal/pmrinsyear/workCalPmRInsYearList_${compNo}.jsp"></c:import>
				</c:if>
			</div>
		</div><!--article_box end-->
	</div> <!--  end section_wrap -->
	<!--연간일정 start-->
	<div class="section_wrap">
		<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx" id="year_title"></div>
			</div>
			<div class="function_box middle">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>
				<div class="fb_group2">
				</div>
			</div>
		</div>
		<!--sheader_box end-->
		<div class="article_box">
			<div class="tb_year">
				<table width="100%" border="0" cellpadding="0" cellspacing="0;" >
					<tr>
						<th scope="col"><bean:message key="LABEL.month1"/></th>
						<th scope="col"><bean:message key="LABEL.month2"/></th>
						<th scope="col"><bean:message key="LABEL.month3"/></th>
						<th scope="col"><bean:message key="LABEL.month4"/></th>
						<th scope="col"><bean:message key="LABEL.month5"/></th>
						<th scope="col"><bean:message key="LABEL.month6"/></th>
						<th scope="col"><bean:message key="LABEL.month7"/></th>
						<th scope="col"><bean:message key="LABEL.month8"/></th>
						<th scope="col"><bean:message key="LABEL.month9"/></th>
						<th scope="col"><bean:message key="LABEL.month10"/></th>
						<th scope="col"><bean:message key="LABEL.month11"/></th>
						<th scope="col"><bean:message key="LABEL.month12"/></th>
						<th scope="col"><bean:message key="LABEL.total2"/></th>
					</tr>

					<tr>
						<td id="td_01"></td>
						<td id="td_02"></td>
						<td id="td_03"></td>
						<td id="td_04"></td>
						<td id="td_05"></td>
						<td id="td_06"></td>
						<td id="td_07"></td>
						<td id="td_08"></td>
						<td id="td_09"></td>
						<td id="td_10"></td>
						<td id="td_11"></td>
						<td id="td_12"></td>
						<td id="td_TOTAL"></td>
					</tr>
				</table>
				<div class="legend_tx"><span class="p"><strong>P</strong></span> : Planed &nbsp;&nbsp;&nbsp;<span class="s"><strong>S</strong></span> : Scheduled &nbsp;&nbsp;&nbsp;<span class="c"><strong>C</strong></span> : Completed</div>
			</div>
		</div>
	<!--article_box-->
	</div><!--연간일정 end-->
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
<%--===========================================================================
월간작업일정(통합) - 목록
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.cal.unitedmonth.action.WorkCalUnitedMonthListAction" %>
<%@ page import="dream.work.pmi.list.action.WorkPmiListAction" %>
<%@ page import="dream.work.pmi.list.action.WorkPmiDetailAction"%>
<%@ page import="dream.work.cal.womonth.action.MaWoMonthWoListAction" %>
<%@ page import="dream.work.list.action.MaWoResultMstrDetailAction" %>
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
<!-- 월간작업일정(통합) -->
<title><bean:message key='MENU.UNITEDMON'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myWoGrid;
var myPmiGrid;
//클릭한 날짜
var tempDay;
//클릭한 타입
var tempType;
var beforePageId = '';
/** 자동완성 변수 */
var equipDescAc;
var deptAc;
var eqLocDescAc;
var mainMngAc;
var subMngAc;
var wkCtrDescAc;
var pmTypeAc;
var empAc;
var plantAc;
var gridboxId = "";

function loadPage() 
{
	$('#work_grid_area').hide();
	$('#pmi_grid_area').hide();

	initGrid("PMI");
	initGrid("WO");
	
	//부서
	workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterDeptId'].value    = loginUser.filterDeptId;
	workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterDeptDesc'].value  = loginUser.filterDeptDesc;
	//년월
    workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterYyyymm'].value = dateToData(getToday()).substr(0, 4)+"-"+dateToData(getToday()).substr(4, 2);

	if(loginUser.eqLocId!='null'){
		workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterEqLocId'].value = loginUser.eqLocId;
		workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterEqLocDesc'].value = loginUser.eqLocDesc;
	}
    if(loginUser.filterWkCtrId!='null'){
    	workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterWkCtrId'].value = loginUser.filterWkCtrId;
    	workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterWkCtrDesc'].value = loginUser.filterWkCtrDesc;
	}
    if(loginUser.eqCtgTypeId!='null'){
    	workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterEqCtgTypeId'].value = loginUser.eqCtgTypeId;
    	workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterEqCtgTypeDesc'].value = loginUser.eqCtgTypeDesc;
	}
  	//공장명
    if(loginUser.filterPlant!='null'){
    	workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
  	
    equipDescAc = new autoC({"workCalUnitedMonthCommonDTO.filterEquipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipDescAc.setAcResultMap({
        "workCalUnitedMonthCommonDTO.filterEquipId":"equip_id"
    });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "workCalUnitedMonthCommonDTO.filterEqLocId",
    	"eqctg_id" : "workCalUnitedMonthCommonDTO.filterEqCtgId",
    	"dept_id" : "workCalUnitedMonthCommonDTO.filterDeptId",
    	"plant" : "workCalUnitedMonthCommonDTO.filterPlantId"
    });
    equipDescAc.init();
    
    deptAc = new autoC({"workCalUnitedMonthCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcDisplay("DESCRIPTION");
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "workCalUnitedMonthCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "workCalUnitedMonthCommonDTO.filterPlantId"
    	,"plantDesc" : "workCalUnitedMonthCommonDTO.filterPlantDesc"
    });
    deptAc.init();
    
    eqLocDescAc = new autoC({"workCalUnitedMonthCommonDTO.filterEqLocDesc":"full_desc"});
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcResultMap({
        "workCalUnitedMonthCommonDTO.filterEqLocId":"eqloc_id"
    });
    eqLocDescAc.setAcDConditionMap({
    	"plant" : "workCalUnitedMonthCommonDTO.filterPlantId"
    });
    eqLocDescAc.init();
    
    eqCtgTypeAc = new autoC({"workCalUnitedMonthCommonDTO.filterEqCtgDesc":"full_desc"});
    eqCtgTypeAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcResultMap({
        "workCalUnitedMonthCommonDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();

	//법정설비여부  AC
    acSysDesc("workCalUnitedMonthCommonDTO.filterIsLawEq","workCalUnitedMonthCommonDTO.filterIsLawEq","IS_USE",true);
    
	//교대조  AC
    acSysDesc("workCalUnitedMonthCommonDTO.filterShiftDesc","workCalUnitedMonthCommonDTO.filterShiftId","SHIFT_TYPE");
	
    mainMngAc = new autoC({"workCalUnitedMonthCommonDTO.filterMainMngName":"emp_name"});
    mainMngAc.setAcDisplay("EMP_NAME");
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "workCalUnitedMonthCommonDTO.filterMainMngId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "workCalUnitedMonthCommonDTO.filterDeptId",
    	"plant" : "workCalUnitedMonthCommonDTO.filterPlantId"
    });
    mainMngAc.init();
    
    subMngAc = new autoC({"workCalUnitedMonthCommonDTO.filterSubMngName":"emp_name"});
    subMngAc.setAcDisplay("EMP_NAME");
    subMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    subMngAc.setTable("TAEMP");
    subMngAc.setAcResultMap({
        "workCalUnitedMonthCommonDTO.filterSubMngId":"emp_id"
    });
    subMngAc.setAcDConditionMap({
    	"dept_id" : "workCalUnitedMonthCommonDTO.filterDeptId",
    	"plant" : "workCalUnitedMonthCommonDTO.filterPlantId"
    });
    subMngAc.init();
    empAc = new autoC({"workCalUnitedMonthCommonDTO.filterEmpDesc":"emp_name"});
    empAc.setAcDisplay("EMP_NAME");
    empAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    empAc.setTable("TAEMP");
    empAc.setAcResultMap({
        "workCalUnitedMonthCommonDTO.filterEmpId":"emp_id"
    });
    empAc.setAcDConditionMap({
    	"dept_id" : "workCalUnitedMonthCommonDTO.filterDeptId",
    	"plant" : "workCalUnitedMonthCommonDTO.filterPlantId"
    });
    empAc.init();

	//작업종류  AC
    acSysDesc("workCalUnitedMonthCommonDTO.filterWoTypeDesc","workCalUnitedMonthCommonDTO.filterWoTypeId","WO_TYPE");
    
    wkCtrDescAc = new autoC({"workCalUnitedMonthCommonDTO.filterWkCtrDesc":"description"});
    wkCtrDescAc.setAcDisplay("DESCRIPTION");
    wkCtrDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    wkCtrDescAc.setTable("TAWKCTR");
    wkCtrDescAc.setAcResultMap({
        "workCalUnitedMonthCommonDTO.filterWkCtrId":"wkctr_id"
    });
    wkCtrDescAc.init();
    
 	// 공장코드
	plantAc = new autoC({"workCalUnitedMonthCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "workCalUnitedMonthCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();

	//설비유형  AC
    acSysDesc("workCalUnitedMonthCommonDTO.filterEqCtgTypeDesc","workCalUnitedMonthCommonDTO.filterEqCtgTypeId","EQCTG_TYPE");

} // end of loadPage
	
/*작업종류 선택후 실행*/
function afterAutoCmpt(code)
{
		if(code=="workCalUnitedMonthCommonDTO.filterWoTypeDesc")
		{
		 	var listType = workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterWoTypeId'].value+"_TYPE";
			//작업형태  AC
		    acSysDesc("workCalUnitedMonthCommonDTO.filterPmTypeDesc","workCalUnitedMonthCommonDTO.filterPmTypeId",listType);
		}
} // end of afterAutoCmpt


/*작업종류 선택후 실행*/
function afterSetValue(lovType)
{
	if(lovType=="WO_TYPE")
	{
	 	var listType = workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterWoTypeId'].value+"_TYPE";
		//작업형태  AC
	    acSysDesc("workCalUnitedMonthCommonDTO.filterPmTypeDesc","workCalUnitedMonthCommonDTO.filterPmTypeId",listType);
	}
} // end of afterSetValue

/**
 * 그리드 초기화
 */

function initGrid(selectedType)
{
	if (typeof selectedType == "undefined") return;
	
	switch(selectedType)
	{
		case "WO" :
			gridboxId = "work_gridbox"
			myWoGrid = new dhtmlXGridObject(gridboxId);
			myWoGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
			myWoGrid.enableSmartRendering(true,500);
			myWoGrid.attachEvent("onRowSelect",function(rowId, columnId){
				goOpen(rowId);
			});
			myWoGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,gridboxId)}); 
			myWoGrid.init();
			setHeader(myWoGrid, gridboxId); // grid, grid id
			$('#work_grid_area').show();
			$('#pmi_grid_area').hide();
			break;
		case "PMI" :
			gridboxId = "pmi_gridbox"
			myPmiGrid = new dhtmlXGridObject(gridboxId);
			myPmiGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
			myPmiGrid.enableSmartRendering(true,500);
			myPmiGrid.attachEvent("onRowSelect",function(rowId, columnId){
				goOpen(rowId);
			});
			myPmiGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,gridboxId)}); 
			myPmiGrid.init();
			setHeader(myPmiGrid, gridboxId); // grid, grid id
			$('#pmi_grid_area').show();
			$('#work_grid_area').hide();
			break;
	}


} // end of initGrid

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction,selectedType,yyyymmdd,type)
{
	
	switch(selectedType)
	{
		case "WO" :
			$('#work_grid_area').show();
			$('#pmi_grid_area').hide();
			break;
		case "PMI" :
			$('#pmi_grid_area').show();
			$('#work_grid_area').hide();
			break;
	}

    var url = contextPath + "/workCalUnitedMonthList.do";
    tempDay = yyyymmdd;
    tempType = type;
    
	workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.yyyymmdd'].value     = yyyymmdd;
    workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.schedType'].value    = type;
    
	var keyColId = "";

	switch(selectedType)
	{
		case "WO" :
			gridboxId = "work_gridbox"
			keyColId = "WKORID"
		    workCalUnitedMonthListForm.elements['strutsAction'].value = '<%=WorkCalUnitedMonthListAction.MONTH_WO_LIST_FIND%>';
    		doGridAction(sheetAction, myWoGrid, gridboxId, url, FormQueryString(workCalUnitedMonthListForm), keyColId, "Y");
			break;
		case "PMI" :
			gridboxId = "pmi_gridbox"
			keyColId = "PMINSLISTID"
		    workCalUnitedMonthListForm.elements['strutsAction'].value = '<%=WorkCalUnitedMonthListAction.MONTH_PMI_LIST_FIND%>';
    		doGridAction(sheetAction, myPmiGrid, gridboxId, url, FormQueryString(workCalUnitedMonthListForm), keyColId, "Y");
			break;
	}
	

} // end of findGridList

/**
 * 월간작업일정 조회
 */
function findSchedList()
{
	var url = contextPath + "/workCalUnitedMonthList.do";
	workCalUnitedMonthListForm.elements['strutsAction'].value = '<%=WorkCalUnitedMonthListAction.MONTH_FIND%>';
	setModal();
	$.post(url,FormQueryString(workCalUnitedMonthListForm), function(_data){
		afterFindSched(JSON.parse(_data));
    });
}

/**
 * 월간일정 세팅
 */
function afterFindSched(_dataObj)
{
	closeModal();
	
	/**
	*달력초기화
	*/
	for(var i=1;i<=6;i++){
		for(var j=1;j<=7;j++){
			document.getElementById("TD"+i+"_"+j).innerHTML = "";
		}
	}
	document.getElementById("month_title").innerText = workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.yyyymmdd'].value;
	
	Object.keys(_dataObj).forEach(function(key){
		var _cntObject = _dataObj[key];
		
		var _yyyymmdd = key;
		var _week = _cntObject.WEEK;
		var _dow = _cntObject.DOW;
		var _dd;
		if(_yyyymmdd.substr(6, 1)=="0") _dd = _yyyymmdd.substr(7, 1);
		else _dd = _yyyymmdd.substr(6, 2);
		
		var todayStyleStr = "date_box";
		if(_yyyymmdd==getToday().replace(/\-/gi, "")) todayStyleStr = "date_box date_today";
		
		var _cntWoObject = _cntObject.WO;
		var _cntPmiObject = _cntObject.PMI;
		
		var w_totCnt = _cntWoObject.TOTAL;
		var w_sCnt = _cntWoObject.TOTAL - _cntWoObject.C;
		var w_cCnt = _cntWoObject.C;
		
		var i_totCnt = _cntPmiObject.TOTAL;
		var i_sCnt = _cntPmiObject.TOTAL - _cntPmiObject.C;
		var i_cCnt = _cntPmiObject.C;
		
		//작업 채워주기
		var str = "<div class='"+todayStyleStr+"'>"+_dd+"</div>"+
					"<div class='psc_w_box'>"+
					"<div style= 'float:left; margin-left:5px; color:black; font-weight:bold'>W</div>"+
					"<span class='p'><a href=\"javascript:findGridList('Search', 'WO', "+"'"+_yyyymmdd+"', '');\">"+w_totCnt+"</a></span> /"+
					"<span class='s'><a href=\"javascript:findGridList('Search', 'WO', "+"'"+_yyyymmdd+"', '-C');\">"+w_sCnt+"</a></span> /"+
					"<span class='c'><a href=\"javascript:findGridList('Search', 'WO', "+"'"+_yyyymmdd+"', 'C');\">"+w_cCnt+"</a></span>"+
					"</div>";
		//점검 채워주기
		str += "<div class='"+todayStyleStr+"'>"+_dd+"</div>"+
					"<div class='psc_i_box'>"+
					"<div style= 'float:left; margin-left:5px; color:black; font-weight:bold'>I</div>"+
					"<span class='p'><a href=\"javascript:findGridList('Search', 'PMI', "+"'"+_yyyymmdd+"', '');\">"+i_totCnt+"</a></span> /"+
					"<span class='s'><a href=\"javascript:findGridList('Search', 'PMI', "+"'"+_yyyymmdd+"', '-C');\">"+i_sCnt+"</a></span> /"+
					"<span class='c'><a href=\"javascript:findGridList('Search', 'PMI', "+"'"+_yyyymmdd+"', 'C');\">"+i_cCnt+"</a></span>"+
					"</div>";
		
		document.getElementById("TD"+_week+"_"+_dow).innerHTML = str;
	});
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.wkOrId'].value = "";	// 검색시 Tab 이동Key Clear
	workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.pminslistId'].value = "";	// 검색시 Tab 이동Key Clear
	workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.createdWkorId'].value = "";	// 검색시 Tab 이동Key Clear
	workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.createdPmInsId'].value = "";	// 검색시 Tab 이동Key Clear
    workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.schedType'].value = "";
	
	workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.yyyymmdd'].value      = workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterYyyymm'].value;
    workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.deptId'].value        = workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterDeptId'].value;       
    workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.deptDesc'].value      = workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterDeptDesc'].value;     
    workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.eqLocId'].value       = workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterEqLocId'].value;      
    workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.eqLocDesc'].value     = workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterEqLocDesc'].value;    
    workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.eqCtgId'].value       = workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterEqCtgId'].value;      
    workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.eqCtgDesc'].value     = workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterEqCtgDesc'].value;    
    workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.mainMngId'].value     = workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterMainMngId'].value;    
    workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.mainMngName'].value   = workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterMainMngName'].value;  
    workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.subMngId'].value      = workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterSubMngId'].value;     
    workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.subMngName'].value    = workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterSubMngName'].value;   
    workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.plfTypeId'].value     = workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterPlfTypeId'].value;    
    workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.plfTypeDesc'].value   = workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterPlfTypeDesc'].value;  
    workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.woTypeId'].value      = workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterWoTypeId'].value;     
    workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.woTypeDesc'].value    = workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterWoTypeDesc'].value;   
    workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.pmTypeId'].value      = workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterPmTypeId'].value;     
    workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.pmTypeDesc'].value    = workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterPmTypeDesc'].value;   
    workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.wkCtrId'].value       = workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterWkCtrId'].value;      
    workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.wkCtrDesc'].value     = workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterWkCtrDesc'].value;    
    workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.eqCtgTypeId'].value   = workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterEqCtgTypeId'].value;  
    workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.eqCtgTypeDesc'].value = workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterEqCtgTypeDesc'].value;
    workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.equipId'].value       = workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterEquipId'].value;      
    workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.equipDesc'].value     = workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterEquipDesc'].value;    
    workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.shiftId'].value       = workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterShiftId'].value;      
    workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.shiftDesc'].value     = workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterShiftDesc'].value;    
    workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.isLawEq'].value       = workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterIsLawEq'].value;      
    workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.empId'].value         = workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterEmpId'].value;        
    workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.empDesc'].value       = workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterEmpDesc'].value;      
    workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.plantId'].value       = workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterPlantId'].value;      
    workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.plantDesc'].value     = workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.filterPlantDesc'].value;    
	
	findSchedList();
} // end of goSearch

/**
 * Tab 이동시 호출
 */
function goTabPage(pageId)
{
	if ("work_gridbox" == gridboxId)
	{
		tabValidationCheck(myWoGrid, pageId, "goTabPageAction");  
	}
	else
	{
		tabValidationCheck(myPmiGrid, pageId, "goTabPageAction");  
	}
} // end of goTabPage

function goTabPageAction(pageId, selectedId)
{
	if ("work_gridbox" == gridboxId)
	{
		workCalUnitedMonthListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value =  getValueById(myWoGrid, selectedId,'WKORID'); 
		workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.wkOrId'].value =  getValueById(myWoGrid, selectedId,'WKORID'); 
		goCommonTabPage(workCalUnitedMonthListForm, <%= MaWoResultMstrDetailAction.WO_RESULT_DETAIL_INIT %>, pageId,beforePageId);
	}
	else
	{
		workCalUnitedMonthListForm.elements['workPmiCommonDTO.pminslistId'].value =  getValueById(myPmiGrid, selectedId,'PMINSLISTID'); 
		workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.pminslistId'].value =  getValueById(myPmiGrid, selectedId,'PMINSLISTID'); 
		goCommonTabPage(workCalUnitedMonthListForm, <%= WorkPmiDetailAction.WORK_PMI_DETAIL_INIT %>, pageId,beforePageId);
	}

	beforePageId = pageId;
} // end of goTabPageAction


/**
 * 상세 열기
 */
function goOpen(rowId)
{
	if ("work_gridbox" == gridboxId)
	{
		var woType = getValueById(myWoGrid, rowId,'WOTYPE');
		var pmType = getValueById(myWoGrid, rowId,'PMTYPE');
		var param  = getValueById(myWoGrid, rowId,'PARAM');
	}
	else
	{
		var woType = getValueById(myPmiGrid, rowId,'WOTYPE');
		var pmType = getValueById(myPmiGrid, rowId,'PMTYPE');
		var param  = getValueById(myPmiGrid, rowId,'PARAM');
	}

	workCalUnitedMonthListForm.elements['maWoResultMstrCommonDTO.selectedWoType'].value = woType;
	workCalUnitedMonthListForm.elements['maWoResultMstrCommonDTO.selectedPmType'].value = pmType;

	goTabPage(param);
} // end of goOpen

function goOpenAction()
{
	var selectedId = "";
 	var pageId  = "";
	
	if ("work_gridbox" == gridboxId)
	{
		selectedId = myWoGrid.getSelectedRowId();
		pageId  = getValueById(myWoGrid, selectedId,'PARAM');
	 	workCalUnitedMonthListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value =  getValueById(myWoGrid, selectedId,'WKORID'); 
		workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.wkOrId'].value =  getValueById(myWoGrid, selectedId,'WKORID'); 
		workCalUnitedMonthListForm.elements['strutsAction'].value = '<%=MaWoResultMstrDetailAction.WO_RESULT_DETAIL_INIT%>';
	}
	else
	{
		selectedId = myPmiGrid.getSelectedRowId();
		pageId  = getValueById(myPmiGrid, selectedId,'PARAM');
	 	workCalUnitedMonthListForm.elements['workPmiCommonDTO.pminslistId'].value =  getValueById(myPmiGrid, selectedId,'PMINSLISTID'); 
		workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.pminslistId'].value =  getValueById(myPmiGrid, selectedId,'PMINSLISTID'); 
		workCalUnitedMonthListForm.elements['strutsAction'].value = '<%=WorkPmiDetailAction.WORK_PMI_DETAIL_INIT%>';
	}
	
 	if(selectedId == null) return;
 	
 	openQuickTabPage(FormQueryString(workCalUnitedMonthListForm), pageId);
} // end of goOpenAction

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_keyId)
{
	goSearch();
	
	if ("work_gridbox" == gridboxId)
	{
		selectedType = "WO";
		workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.createdPmInsId'].value = "";
		workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.createdWkorId'].value = _keyId;
	}
	else
	{
		selectedType = "PMI";
		workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.createdWkorId'].value = "";
		workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.createdPmInsId'].value = _keyId;
	}
	findGridList('ReloadRow',selectedType,tempDay,tempType);
	workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.createdWkorId'].value = "";
	workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.createdPmInsId'].value = "";
} // end of findGridRow

/**
 * 삭제
 */
 function goDelete(){
	 var url = "";
	
	//myGrid에 1(2번째)번에 체크가 되어있으면 2번 데이터 가져오기
	if ("work_gridbox" == gridboxId)
	{
		var delArray = getDeletRows(myWoGrid, 'ISDELCHECK', 'WKORID'); //Grid, check box column seq, pk column seq
	  	workCalUnitedMonthListForm.strutsAction.value = '<%=MaWoMonthWoListAction.MONTH_WO_LIST_DELETE%>';
	  	url = contextPath + "/maWoMonthWoList.do";
	}
	else
	{
		var delArray = getDeletRows(myPmiGrid, 'ISDELCHECK', 'PMINSLISTID'); //Grid, check box column seq, pk column seq
	  	workCalUnitedMonthListForm.strutsAction.value = '<%=WorkPmiListAction.WORK_PMI_LIST_DELETE%>';
	  	url = contextPath + "/workPmiList.do";
	}
	//체크된게 없으면 return
	 if(typeof delArray == "undefined"){
			alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
			return;
		}

  	$.post(url,FormQueryString(workCalUnitedMonthListForm)+delArray , function(_data){
    	afterDelete();
    });
  }

function afterDelete(){
	goClose('workCalPmRInsMonthDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
  }


/**
 * Excel Export
 */
function goExcel()
{
	if ("work_gridbox" == gridboxId)
	{
		workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.createdWkorId'].value = "";
	}
	else
	{
		workCalUnitedMonthListForm.elements['workCalUnitedMonthCommonDTO.createdPmInsId'].value = "";
	}	
	
    excelServerAction("workCalUnitedMonthList", workCalUnitedMonthListForm );  
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workCalUnitedMonthList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="workCalUnitedMonthCommonDTO.wkOrId"/><!-- Key -->
<html:hidden property="workCalUnitedMonthCommonDTO.pminslistId"/><!-- Key -->
<html:hidden property="workPmiCommonDTO.pminslistId"/><!-- Key -->
<html:hidden property="maWoResultMstrCommonDTO.wkOrId"/><!-- Key -->
<html:hidden property="workCalUnitedMonthCommonDTO.createdWkorId"/><!--create Key -->
<html:hidden property="workCalUnitedMonthCommonDTO.createdPmInsId"/><!--create Key -->
<html:hidden property="workCalUnitedMonthCommonDTO.filterDeptId"/>
<html:hidden property="workCalUnitedMonthCommonDTO.yyyymmdd"/>
<html:hidden property="workCalUnitedMonthCommonDTO.schedType"/>
<html:hidden property="workCalUnitedMonthCommonDTO.deptId"/>
<html:hidden property="workCalUnitedMonthCommonDTO.deptDesc"/>
<html:hidden property="workCalUnitedMonthCommonDTO.eqLocId"/>
<html:hidden property="workCalUnitedMonthCommonDTO.eqLocDesc"/>
<html:hidden property="workCalUnitedMonthCommonDTO.eqCtgId"/>
<html:hidden property="workCalUnitedMonthCommonDTO.eqCtgDesc"/>
<html:hidden property="workCalUnitedMonthCommonDTO.mainMngId"/>
<html:hidden property="workCalUnitedMonthCommonDTO.mainMngName"/>
<html:hidden property="workCalUnitedMonthCommonDTO.subMngId"/>
<html:hidden property="workCalUnitedMonthCommonDTO.subMngName"/>
<html:hidden property="workCalUnitedMonthCommonDTO.plfTypeId"/>
<html:hidden property="workCalUnitedMonthCommonDTO.plfTypeDesc"/>
<html:hidden property="workCalUnitedMonthCommonDTO.isLawEq"/>
<html:hidden property="workCalUnitedMonthCommonDTO.woTypeId"/>
<html:hidden property="workCalUnitedMonthCommonDTO.woTypeDesc"/>
<html:hidden property="workCalUnitedMonthCommonDTO.pmTypeId"/>
<html:hidden property="workCalUnitedMonthCommonDTO.pmTypeDesc"/>
<html:hidden property="workCalUnitedMonthCommonDTO.wkCtrId"/>
<html:hidden property="workCalUnitedMonthCommonDTO.wkCtrDesc"/>
<html:hidden property="workCalUnitedMonthCommonDTO.eqCtgTypeId"/>
<html:hidden property="workCalUnitedMonthCommonDTO.eqCtgTypeDesc"/>
<html:hidden property="workCalUnitedMonthCommonDTO.equipId"/>
<html:hidden property="workCalUnitedMonthCommonDTO.equipDesc"/>
<html:hidden property="workCalUnitedMonthCommonDTO.filterEqLocId"/>
<html:hidden property="workCalUnitedMonthCommonDTO.filterEqCtgId"/>
<html:hidden property="workCalUnitedMonthCommonDTO.filterPlfTypeId"/>
<html:hidden property="workCalUnitedMonthCommonDTO.filterShiftId"/>
<html:hidden property="workCalUnitedMonthCommonDTO.filterPlfTypeDesc"/>
<html:hidden property="workCalUnitedMonthCommonDTO.filterWoTypeId"/>
<html:hidden property="workCalUnitedMonthCommonDTO.filterPmTypeId"/>
<html:hidden property="workCalUnitedMonthCommonDTO.filterMainMngId"/>
<html:hidden property="workCalUnitedMonthCommonDTO.filterSubMngId"/>
<html:hidden property="workCalUnitedMonthCommonDTO.filterEquipId"/>
<html:hidden property="workCalUnitedMonthCommonDTO.filterWkCtrId"/>
<html:hidden property="workCalUnitedMonthCommonDTO.filterEmpId"/>
<html:hidden property="workCalUnitedMonthCommonDTO.shiftId"/>
<html:hidden property="workCalUnitedMonthCommonDTO.shiftDesc"/>
<html:hidden property="workCalUnitedMonthCommonDTO.empId"/>
<html:hidden property="workCalUnitedMonthCommonDTO.empDesc"/>
<html:hidden property="workCalUnitedMonthCommonDTO.filterEqCtgTypeId"/>
<html:hidden property="workCalUnitedMonthCommonDTO.filterPlantId"/>
<html:hidden property="workCalUnitedMonthCommonDTO.plantId"/>
<html:hidden property="workCalUnitedMonthCommonDTO.plantDesc"/>
<html:hidden property="maWoResultMstrCommonDTO.selectedPmType"/>
<html:hidden property="maWoResultMstrCommonDTO.selectedWoType"/>
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
						<html:text property="workCalUnitedMonthCommonDTO.filterYyyymm" readonly="true"/>
						<p class="open_mon_calendar"><span>날짜</span></p>
					</div>
				</div>
				<!-- 관리부서 -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="workCalUnitedMonthCommonDTO.filterDeptDesc" tabindex="20" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 설비 -->
				<div class="field">
					<label><bean:message key="LABEL.equipDesc"/></label>
					<div class="input_box">
						<html:text property="workCalUnitedMonthCommonDTO.filterEquipDesc" tabindex="25" />
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
						<html:text property="workCalUnitedMonthCommonDTO.filterEqLocDesc" tabindex="30" />
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
						<html:text property="workCalUnitedMonthCommonDTO.filterEqCtgDesc" tabindex="35" />
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
						<html:text property="workCalUnitedMonthCommonDTO.filterIsLawEq" tabindex="60" />
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
						<html:text property="workCalUnitedMonthCommonDTO.filterMainMngName" tabindex="70" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 관리자(부) -->
				<div class="field">
					<label><bean:message key="LABEL.subManager"/></label>
					<div class="input_box">
						<html:text property="workCalUnitedMonthCommonDTO.filterSubMngName" tabindex="90" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 작업종류  -->
				<div class="field">
					<label><bean:message key="LABEL.woType"/></label>
					<div class="input_box">
						<html:text property="workCalUnitedMonthCommonDTO.filterWoTypeDesc" tabindex="120" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 작업형태  -->
				<div class="field">
					<label><bean:message key="LABEL.pmType"/></label>
					<div class="input_box">
						<html:text property="workCalUnitedMonthCommonDTO.filterPmTypeDesc" tabindex="130" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 작업그룹  -->
                <div class="field">
                     <label><bean:message key="LABEL.wkCtr"/></label>
                     <div class="input_box">
                        <html:text property="workCalUnitedMonthCommonDTO.filterWkCtrDesc" tabindex="140" />
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
						<html:text property="workCalUnitedMonthCommonDTO.filterEqCtgTypeDesc" tabindex="150" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- shift  -->
				<div class="field">
					<label><bean:message key="LABEL.shiftType"/></label>
					<div class="input_box">
						<html:text property="workCalUnitedMonthCommonDTO.filterShiftDesc" tabindex="160" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 담당자  -->
				<div class="field">
					<label><bean:message key="LABEL.manager"/></label>
					<div class="input_box">
						<html:text property="workCalUnitedMonthCommonDTO.filterEmpDesc" tabindex="170" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
							<html:text property="workCalUnitedMonthCommonDTO.filterPlantDesc"
								tabindex="180" />
							<p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <c:set var="filePath" value="enhance/${compName}/work/cal/unitedmonth/workCalUnitedMonthList_${compNo}.jsp" />
				<c:if test="${udf:isExist(filePath)}">
					<c:import charEncoding="UTF-8" url="/enhance/${compName}/work/cal/unitedmonth/workCalUnitedMonthList_${compNo}.jsp"></c:import>
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
				<div class="fb_group2">
				</div>
			</div>
		</div>
		<div class="article_box">
						<div class="tb_month_wi">
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
							<div class="legend_tx">
								<span class="wi"><strong>W</strong></span> : <bean:message key="LABEL.work"/> &nbsp;&nbsp;&nbsp;
								<span class="wi"><strong>I</strong></span> : <bean:message key="LABEL.pm"/> &nbsp;&nbsp;&nbsp;
								<span class="p"><strong>T</strong></span> : <bean:message key="LABEL.woTarget"/> &nbsp;&nbsp;&nbsp;<span class="s"><strong>P</strong></span> : <bean:message key="LABEL.woIncomplete"/>&nbsp;&nbsp;&nbsp;<span class="c"><strong>C</strong></span> : <bean:message key="LABEL.woCompleted"/></div>
						</div>
					</div>
					<!--article_box-->
				</div>
	<div class="section_wrap" name ="list"  id="work_grid_area" >
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
				<div id="work_gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
	</div> <!--  End of section_wrap -->
	<div class="section_wrap" name ="list"  id="pmi_grid_area" >
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
		<div class="article_box" >
			<div class="grid_area">
				<div id="pmi_gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>
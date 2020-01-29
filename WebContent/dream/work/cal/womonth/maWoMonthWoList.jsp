<%--===========================================================================
월간작업일정 - 목록
author  kim21017
version $Id: maWoMonthWoList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
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
<!-- 월간작업일정 -->
<title><bean:message key='MENU.MONTHWOSCHED'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;
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
function loadPage() 
{
	//부서
	maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterDeptId'].value    = loginUser.filterDeptId;
	maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterDeptDesc'].value  = loginUser.filterDeptDesc;
	//년월
    maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterYyyymm'].value = dateToData(getToday()).substr(0, 4)+"-"+dateToData(getToday()).substr(4, 2);

	if(loginUser.eqLocId!='null'){
		maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterEqLocId'].value = loginUser.eqLocId;
		maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterEqLocDesc'].value = loginUser.eqLocDesc;
	}
/*     if(loginUser.wkctrId!='null'){
    	maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterWkCtrId'].value = loginUser.wkctrId;
    	maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterWkCtrDesc'].value = loginUser.wkctrDesc;
	} */
    if(loginUser.filterWkCtrId!='null'){
    	maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterWkCtrId'].value = loginUser.filterWkCtrId;
    	maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterWkCtrDesc'].value = loginUser.filterWkCtrDesc;
	}
    if(loginUser.eqCtgTypeId!='null'){
    	maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterEqCtgTypeId'].value = loginUser.eqCtgTypeId;
    	maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterEqCtgTypeDesc'].value = loginUser.eqCtgTypeDesc;
	}
  	//공장명
    if(loginUser.filterPlant!='null'){
    	maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
    initGrid();
    
    equipDescAc = new autoC({"maWoMonthWoCommonDTO.filterEquipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipDescAc.setAcResultMap({
        "maWoMonthWoCommonDTO.filterEquipId":"equip_id"
    });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "maWoMonthWoCommonDTO.filterEqLocId",
    	"eqctg_id" : "maWoMonthWoCommonDTO.filterEqCtgId",
    	"dept_id" : "maWoMonthWoCommonDTO.filterDeptId",
    	"plant" : "maWoMonthWoCommonDTO.filterPlantId"
    });
    equipDescAc.init();
    
    deptAc = new autoC({"maWoMonthWoCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcDisplay("DESCRIPTION");
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maWoMonthWoCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "maWoMonthWoCommonDTO.filterPlantId"
    	,"plantDesc" : "maWoMonthWoCommonDTO.filterPlantDesc"
    });
    deptAc.init();
    
    eqLocDescAc = new autoC({"maWoMonthWoCommonDTO.filterEqLocDesc":"full_desc"});
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcResultMap({
        "maWoMonthWoCommonDTO.filterEqLocId":"eqloc_id"
    });
    eqLocDescAc.setAcDConditionMap({
    	"plant" : "maWoMonthWoCommonDTO.filterPlantId"
    });
    eqLocDescAc.init();
    
    eqCtgTypeAc = new autoC({"maWoMonthWoCommonDTO.filterEqCtgDesc":"full_desc"});
    eqCtgTypeAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcResultMap({
        "maWoMonthWoCommonDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();

	//법정설비여부  AC
    acSysDesc("maWoMonthWoCommonDTO.filterIsLawEq","maWoMonthWoCommonDTO.filterIsLawEq","IS_USE",true);
    
	//교대조  AC
    acSysDesc("maWoMonthWoCommonDTO.filterShiftDesc","maWoMonthWoCommonDTO.filterShiftId","SHIFT_TYPE");
	
    mainMngAc = new autoC({"maWoMonthWoCommonDTO.filterMainMngName":"emp_name"});
    mainMngAc.setAcDisplay("EMP_NAME");
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maWoMonthWoCommonDTO.filterMainMngId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maWoMonthWoCommonDTO.filterDeptId",
    	"plant" : "maWoMonthWoCommonDTO.filterPlantId"
    });
    mainMngAc.init();
    
    subMngAc = new autoC({"maWoMonthWoCommonDTO.filterSubMngName":"emp_name"});
    subMngAc.setAcDisplay("EMP_NAME");
    subMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    subMngAc.setTable("TAEMP");
    subMngAc.setAcResultMap({
        "maWoMonthWoCommonDTO.filterSubMngId":"emp_id"
    });
    subMngAc.setAcDConditionMap({
    	"dept_id" : "maWoMonthWoCommonDTO.filterDeptId",
    	"plant" : "maWoMonthWoCommonDTO.filterPlantId"
    });
    subMngAc.init();
    empAc = new autoC({"maWoMonthWoCommonDTO.filterEmpDesc":"emp_name"});
    empAc.setAcDisplay("EMP_NAME");
    empAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    empAc.setTable("TAEMP");
    empAc.setAcResultMap({
        "maWoMonthWoCommonDTO.filterEmpId":"emp_id"
    });
    empAc.setAcDConditionMap({
    	"dept_id" : "maWoMonthWoCommonDTO.filterDeptId",
    	"plant" : "maWoMonthWoCommonDTO.filterPlantId"
    });
    empAc.init();

	//작업종류  AC
    acSysDesc("maWoMonthWoCommonDTO.filterWoTypeDesc","maWoMonthWoCommonDTO.filterWoTypeId","WO_TYPE");
    
    wkCtrDescAc = new autoC({"maWoMonthWoCommonDTO.filterWkCtrDesc":"description"});
    wkCtrDescAc.setAcDisplay("DESCRIPTION");
    wkCtrDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    wkCtrDescAc.setTable("TAWKCTR");
    wkCtrDescAc.setAcResultMap({
        "maWoMonthWoCommonDTO.filterWkCtrId":"wkctr_id"
    });
    wkCtrDescAc.init();
    
 	// 공장코드
	plantAc = new autoC({"maWoMonthWoCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "maWoMonthWoCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();

	//설비유형  AC
    acSysDesc("maWoMonthWoCommonDTO.filterEqCtgTypeDesc","maWoMonthWoCommonDTO.filterEqCtgTypeId","EQCTG_TYPE");
	//내/외자
    acSysDesc("maWoMonthWoCommonDTO.filterPlfTypeDesc","maWoMonthWoCommonDTO.filterPlfTypeId","PLF_TYPE");
	}
	
/*작업종류 선택후 실행*/
function afterAutoCmpt(code)
{
		if(code=="maWoMonthWoCommonDTO.filterWoTypeDesc")
		{
			maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterPmTypeId'].value = "";
			maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterPmTypeDesc'].value = "";
			if(typeof pmTypeAc == 'object') pmTypeAc.destroy();
			
		 	var listType = maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterWoTypeId'].value+"_TYPE";
			//작업형태  AC
		    pmTypeAc = acSysDesc("maWoMonthWoCommonDTO.filterPmTypeDesc","maWoMonthWoCommonDTO.filterPmTypeId",listType);
		}
}


/*작업종류 선택후 실행*/
function afterSetValue(lovType)
{
	if(lovType=="WO_TYPE")
	{
	 	var listType = maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterWoTypeId'].value+"_TYPE";
		//작업형태  AC
	    acSysDesc("maWoMonthWoCommonDTO.filterPmTypeDesc","maWoMonthWoCommonDTO.filterPmTypeId",listType);
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
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen(rowId);
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.wkOrId'].value = "";
    	return sortColumn("maWoMonthWoList", this, maWoMonthWoListForm, "WKORID", ind, direction);
	});
// 	myGrid.attachEvent("onCheckbox",function (rowId,cellInd,state){
// 		if(getValueById(myGrid, rowId, 'WOSTATUS')=='C'){
// 			setValueById(myGrid, rowId, 'ISDELCHECK', '0');
// 			return true;
// 		}
// 		else return true;
// 	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction,yyyymmdd,type)
{
    var url = contextPath + "/maWoMonthWoList.do";
    tempDay = yyyymmdd;
    tempType = type;
    maWoMonthWoListForm.elements['strutsAction'].value = '<%=MaWoMonthWoListAction.MONTH_WO_LIST_FIND%>';
    maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.yyyymmdd'].value     = yyyymmdd;
    maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.schedType'].value    = type;
    
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maWoMonthWoListForm), "WKORID", "Y");
}

/**
 * 월간작업일정 조회
 */
function findSchedList(){
	var url = contextPath + "/maWoMonthWoList.do";
	maWoMonthWoListForm.elements['strutsAction'].value = '<%=MaWoMonthWoListAction.MONTH_WO_FIND%>';
	setModal();
	$.post(url,FormQueryString(maWoMonthWoListForm), function(_data){
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
	document.getElementById("month_title").innerText = maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.yyyymmdd'].value;
	
	Object.keys(_dataObj).forEach(function(key){
		var _cntObject = _dataObj[key];
		
		var _yyyymmdd = key;
		var _week = _cntObject.WEEK;
		var _dow = _cntObject.DOW;
		var _dd;
		if(_yyyymmdd.substr(6, 1)=="0") _dd = _yyyymmdd.substr(7, 1);
		else _dd = _yyyymmdd.substr(6, 2);
		
		var _totCnt = _cntObject.TOTAL;
		var _sCnt = _cntObject.TOTAL - _cntObject.C;
		var _cCnt = _cntObject.C;
		
		var todayStyleStr = "date_box";
		if(_yyyymmdd==getToday().replace(/\-/gi, "")) todayStyleStr = "date_box date_today";
		var backWoStyleStr = "psc_box";
		if(Number(_totCnt)>Number(_cCnt) && Number(_yyyymmdd)<Number(getToday().replace(/\-/gi, ""))) backWoStyleStr = "psc_box psc_unc";
		
		var str = "<div class='"+todayStyleStr+"'>"+_dd+"</div>"+
					"<div class='"+backWoStyleStr+"'>"+
					"<span class='p'><a href=\"javascript:findGridList('Search', "+"'"+_yyyymmdd+"', '');\">"+_totCnt+"</a></span> /"+
					"<span class='s'><a href=\"javascript:findGridList('Search', "+"'"+_yyyymmdd+"', '-C');\">"+_sCnt+"</a></span> /"+
					"<span class='c'><a href=\"javascript:findGridList('Search', "+"'"+_yyyymmdd+"', 'C');\">"+_cCnt+"</a></span>"+
					"</div>";
		
		document.getElementById("TD"+_week+"_"+_dow).innerHTML = str;
	});
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.wkOrId'].value = "";	// 검색시 Tab 이동Key Clear
    maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.schedType'].value = "";
	
	maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.yyyymmdd'].value       = maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterYyyymm'].value;
    maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.deptId'].value         = maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterDeptId'].value;       
    maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.deptDesc'].value       = maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterDeptDesc'].value;     
    maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.eqLocId'].value        = maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterEqLocId'].value;      
    maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.eqLocDesc'].value      = maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterEqLocDesc'].value;    
    maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.eqCtgId'].value        = maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterEqCtgId'].value;      
    maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.eqCtgDesc'].value      = maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterEqCtgDesc'].value;    
    maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.mainMngId'].value      = maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterMainMngId'].value;    
    maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.mainMngName'].value    = maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterMainMngName'].value;  
    maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.subMngId'].value       = maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterSubMngId'].value;     
    maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.subMngName'].value     = maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterSubMngName'].value;   
    maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.plfTypeId'].value      = maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterPlfTypeId'].value;    
    maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.plfTypeDesc'].value    = maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterPlfTypeDesc'].value;  
    maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.woTypeId'].value       = maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterWoTypeId'].value;     
    maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.woTypeDesc'].value     = maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterWoTypeDesc'].value;   
    maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.pmTypeId'].value       = maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterPmTypeId'].value;     
    maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.pmTypeDesc'].value     = maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterPmTypeDesc'].value;   
    maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.wkCtrId'].value        = maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterWkCtrId'].value;      
    maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.wkCtrDesc'].value      = maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterWkCtrDesc'].value;    
    maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.eqCtgTypeId'].value    = maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterEqCtgTypeId'].value;  
    maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.eqCtgTypeDesc'].value  = maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterEqCtgTypeDesc'].value;
    maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.equipId'].value        = maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterEquipId'].value;      
    maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.equipDesc'].value      = maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterEquipDesc'].value;    
    maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.shiftId'].value        = maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterShiftId'].value;      
    maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.shiftDesc'].value      = maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterShiftDesc'].value;    
    maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.isLawEq'].value        = maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterIsLawEq'].value;      
    maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.empId'].value          = maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterEmpId'].value;        
    maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.empDesc'].value        = maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterEmpDesc'].value;      
    maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.plantId'].value        = maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterPlantId'].value;      
    maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.plantDesc'].value      = maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterPlantDesc'].value;    
	
	findSchedList();
}

/**
 * Tab 이동시 호출
 */
function goTabPage(pageId)
{
// 	if(beforePageId!=pageId){
// 		goClose(beforePageId);
// 		beforePageId = pageId;
// 	}
	tabValidationCheck(myGrid, pageId, "goTabPageAction");  
}

function goTabPageAction(pageId, selectedId)
{
	maWoMonthWoListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value =  getValueById(myGrid, selectedId,'WKORID'); 
	maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.wkOrId'].value =  getValueById(myGrid, selectedId,'WKORID'); 
	goCommonTabPage(maWoMonthWoListForm, <%= MaWoResultMstrDetailAction.WO_RESULT_DETAIL_INIT %>, pageId,beforePageId);

	beforePageId = pageId;
}


/**
 * 상세 열기
 */
function goOpen(rowId)
{
	var woType = getValueById(myGrid, rowId,'WOTYPE');
	var pmType = getValueById(myGrid, rowId,'PMTYPE');
	var param  = getValueById(myGrid, rowId,'PARAM');
	
	maWoMonthWoListForm.elements['maWoResultMstrCommonDTO.selectedWoType'].value = woType;
	maWoMonthWoListForm.elements['maWoResultMstrCommonDTO.selectedPmType'].value = pmType;

	goTabPage(param);
}

function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
 	if(selectedId == null) return;
 	
 	var pageId  = getValueById(myGrid, selectedId,'PARAM');
 	maWoMonthWoListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value =  getValueById(myGrid, selectedId,'WKORID'); 
	maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.wkOrId'].value =  getValueById(myGrid, selectedId,'WKORID'); 
	maWoMonthWoListForm.elements['strutsAction'].value = '<%=MaWoResultMstrDetailAction.WO_RESULT_DETAIL_INIT%>';
 	openQuickTabPage(FormQueryString(maWoMonthWoListForm), pageId); 
}

/**
 * 엑셀 다운.
 */
 function goExcel()
 {
	maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.wkOrId'].value = "";
 	excelServerAction("maWoMonthWoList",maWoMonthWoListForm);
 } 
 /**
  * 생성
  */
function goCreate()
{
	goClose(beforePageId);
	openSelectType();
	maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.wkOrId'].value = "";
}

function goCreateAction(pageId)
{
	
}

function setAfterSelect(returnArray){
	var woType = returnArray[0];
	var pmType = returnArray[1];
	var param  = returnArray[2];
	maWoMonthWoListForm.elements['maWoResultMstrCommonDTO.selectedWoType'].value = woType;
	maWoMonthWoListForm.elements['maWoResultMstrCommonDTO.selectedPmType'].value = pmType;

// 	if(beforePageId!=param){
// 		goClose(beforePageId);
// 		beforePageId = param;
// 	}
	beforePageId = param;
// 	createValidationCheck(myGrid, param , "goCreateAction");
	goCommonTabPage(maWoMonthWoListForm, '', param);
}

/**
 * 작업종류& 작업형태 선택창 열기
 */
function openSelectType(){
	width  = 850;
	height = 540;
	sleft = (screen.width - width) / 2;
	stop = (screen.height - height) / 2;
	features = "left=" + sleft + ",top=" + stop;
	
	var param = "strutsAction=1001";
	var url =  contextPath + "/maWoResultSelect.do";
	
	openLayerPopup("maWoResultSelect", param);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_wkOrId)
{
	goSearch();
	
	maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.createdWkorId'].value = _wkOrId;
    findGridList('ReloadRow',tempDay,tempType);
	maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.createdWkorId'].value = "";
}

/**
 * 삭제
 */
 function goDelete(){
	
	 var chkedRowsId = getCheckedRows(myGrid, 'ISDELCHECK');
	 for(var i = 0 ;i < chkedRowsId.length; i++)
	{
		if(getValueById(myGrid, chkedRowsId[i], "WOSTATUS") == "C")
		{
			alertMessage1('<bean:message key="LABEL.delCompWork"/>'); //delCompWork
			return;
		}
		if(getValueById(myGrid, chkedRowsId[i], "WOGENTYPE") == "WOPOINT")
		{
			alertMessage1('<bean:message key="MESSAGE.MSG0099"/>');
			return;
		}
	}
		
		
	//myGrid에 1(2번째)번에 체크가 되어있으면 2번 데이터 가져오기
	 var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'WKORID'); //Grid, check box column seq, pk column seq
	//체크된게 없으면 return
	 if(typeof delArray == "undefined"){
			alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
			return;
		}
	
  	maWoMonthWoListForm.strutsAction.value = '<%=MaWoMonthWoListAction.MONTH_WO_LIST_DELETE%>';
  	var url = contextPath + "/maWoMonthWoList.do";
  	
  	$.post(url,FormQueryString(maWoMonthWoListForm)+delArray , function(_data){
    	afterDelete();
    });
  }

function afterDelete(){
	goClose(beforePageId);
	goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG0032"/>');
  }
  
/**
 * Print 버튼 클릭
 */
function goPrint()
{
	var selArray = getSelectRows(myGrid, 'ISDELCHECK', 'WKORID'); //Grid, check box column seq, pk column seq
	if(typeof selArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	var selectArray = selArray.substring(1,selArray.length).split("&");
	var selectStr;
	for(var i=0; i<selectArray.length;i++){
		selectStr +=","+selectArray[i].split("=")[1];
	}
	
	var url   = contextPath + "/maWoMonthWoList.do";
	openPrintView(url, "maWoMonthWoCommonDTO.selectedWkorId="+selectStr);
}

function goWopdf(){
	goPrint();
}
function goWomonthsched(){
	reportCall('maWoMonthWoList','maWoMonthWoList', "<%=user.getCompNo()%>", "<%=user.getUserId()%>", "<%=user.getLangId()%>",maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterDeptId'].value,maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.filterYyyymm'].value, "<%=user.getPlant()%>"); 
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maWoMonthWoListForm.elements['maWoMonthWoCommonDTO.wkOrId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maWoMonthWoList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maWoMonthWoCommonDTO.wkOrId"/><!-- Key -->
<html:hidden property="maWoResultMstrCommonDTO.wkOrId"/><!-- Key -->
<html:hidden property="maWoMonthWoCommonDTO.createdWkorId"/><!--create Key -->
<html:hidden property="maWoMonthWoCommonDTO.filterDeptId"/>
<html:hidden property="maWoMonthWoCommonDTO.yyyymmdd"/>
<html:hidden property="maWoMonthWoCommonDTO.schedType"/>
<html:hidden property="maWoMonthWoCommonDTO.deptId"/>
<html:hidden property="maWoMonthWoCommonDTO.deptDesc"/>
<html:hidden property="maWoMonthWoCommonDTO.eqLocId"/>
<html:hidden property="maWoMonthWoCommonDTO.eqLocDesc"/>
<html:hidden property="maWoMonthWoCommonDTO.eqCtgId"/>
<html:hidden property="maWoMonthWoCommonDTO.eqCtgDesc"/>
<html:hidden property="maWoMonthWoCommonDTO.mainMngId"/>
<html:hidden property="maWoMonthWoCommonDTO.mainMngName"/>
<html:hidden property="maWoMonthWoCommonDTO.subMngId"/>
<html:hidden property="maWoMonthWoCommonDTO.subMngName"/>
<html:hidden property="maWoMonthWoCommonDTO.plfTypeId"/>
<html:hidden property="maWoMonthWoCommonDTO.plfTypeDesc"/>
<html:hidden property="maWoMonthWoCommonDTO.isLawEq"/>
<html:hidden property="maWoMonthWoCommonDTO.woTypeId"/>
<html:hidden property="maWoMonthWoCommonDTO.woTypeDesc"/>
<html:hidden property="maWoMonthWoCommonDTO.pmTypeId"/>
<html:hidden property="maWoMonthWoCommonDTO.pmTypeDesc"/>
<html:hidden property="maWoMonthWoCommonDTO.wkCtrId"/>
<html:hidden property="maWoMonthWoCommonDTO.wkCtrDesc"/>
<html:hidden property="maWoMonthWoCommonDTO.eqCtgTypeId"/>
<html:hidden property="maWoMonthWoCommonDTO.eqCtgTypeDesc"/>
<html:hidden property="maWoMonthWoCommonDTO.equipId"/>
<html:hidden property="maWoMonthWoCommonDTO.equipDesc"/>
<html:hidden property="maWoMonthWoCommonDTO.filterEqLocId"/>
<html:hidden property="maWoMonthWoCommonDTO.filterEqCtgId"/>
<html:hidden property="maWoMonthWoCommonDTO.filterPlfTypeId"/>
<html:hidden property="maWoMonthWoCommonDTO.filterShiftId"/>
<%-- <html:hidden property="maWoMonthWoCommonDTO.filterPlfTypeDesc"/> --%>
<html:hidden property="maWoMonthWoCommonDTO.filterWoTypeId"/>
<html:hidden property="maWoMonthWoCommonDTO.filterPmTypeId"/>
<html:hidden property="maWoMonthWoCommonDTO.filterMainMngId"/>
<html:hidden property="maWoMonthWoCommonDTO.filterSubMngId"/>
<html:hidden property="maWoMonthWoCommonDTO.filterEquipId"/>
<html:hidden property="maWoMonthWoCommonDTO.filterWkCtrId"/>
<html:hidden property="maWoMonthWoCommonDTO.filterEmpId"/>
<html:hidden property="maWoMonthWoCommonDTO.shiftId"/>
<html:hidden property="maWoMonthWoCommonDTO.shiftDesc"/>
<html:hidden property="maWoMonthWoCommonDTO.empId"/>
<html:hidden property="maWoMonthWoCommonDTO.empDesc"/>
<html:hidden property="maWoMonthWoCommonDTO.filterEqCtgTypeId"/>
<html:hidden property="maWoMonthWoCommonDTO.filterPlantId"/>
<html:hidden property="maWoMonthWoCommonDTO.plantId"/>
<html:hidden property="maWoMonthWoCommonDTO.plantDesc"/>
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
						<html:text property="maWoMonthWoCommonDTO.filterYyyymm" readonly="true"/>
						<p class="open_mon_calendar"><span>날짜</span></p>
					</div>
				</div>
				<!-- 관리부서 -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="maWoMonthWoCommonDTO.filterDeptDesc" tabindex="20" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 설비 -->
				<div class="field">
					<label><bean:message key="LABEL.equipDesc"/></label>
					<div class="input_box">
						<html:text property="maWoMonthWoCommonDTO.filterEquipDesc" tabindex="25" />
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
						<html:text property="maWoMonthWoCommonDTO.filterEqLocDesc" tabindex="30" />
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
						<html:text property="maWoMonthWoCommonDTO.filterEqCtgDesc" tabindex="35" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 내/외자  -->
                <div class="field">
                    <label><bean:message key="LABEL.plfType"/></label>
                    <div class="input_box">
                         <html:text property="maWoMonthWoCommonDTO.filterPlfTypeDesc" tabindex="55"/>
                         <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
				<!-- 법정설비여부 -->
				<div class="field">
					<label><bean:message key="LABEL.isLawEq"/></label>
					<div class="input_box">
						<html:text property="maWoMonthWoCommonDTO.filterIsLawEq" tabindex="60" />
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
						<html:text property="maWoMonthWoCommonDTO.filterMainMngName" tabindex="70" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 관리자(부) -->
				<div class="field">
					<label><bean:message key="LABEL.subManager"/></label>
					<div class="input_box">
						<html:text property="maWoMonthWoCommonDTO.filterSubMngName" tabindex="90" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 작업종류  -->
				<div class="field">
					<label><bean:message key="LABEL.woType"/></label>
					<div class="input_box">
						<html:text property="maWoMonthWoCommonDTO.filterWoTypeDesc" tabindex="120" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 작업형태  -->
				<div class="field">
					<label><bean:message key="LABEL.pmType"/></label>
					<div class="input_box">
						<html:text property="maWoMonthWoCommonDTO.filterPmTypeDesc" tabindex="130" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 작업그룹  -->
                <div class="field">
                     <label><bean:message key="LABEL.wkCtr"/></label>
                     <div class="input_box">
                        <html:text property="maWoMonthWoCommonDTO.filterWkCtrDesc" tabindex="140" />
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
						<html:text property="maWoMonthWoCommonDTO.filterEqCtgTypeDesc" tabindex="150" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- shift  -->
				<div class="field">
					<label><bean:message key="LABEL.shiftType"/></label>
					<div class="input_box">
						<html:text property="maWoMonthWoCommonDTO.filterShiftDesc" tabindex="160" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 담당자  -->
				<div class="field">
					<label><bean:message key="LABEL.manager"/></label>
					<div class="input_box">
						<html:text property="maWoMonthWoCommonDTO.filterEmpDesc" tabindex="170" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
							<html:text property="maWoMonthWoCommonDTO.filterPlantDesc"
								tabindex="180" />
							<p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <c:set var="filePath" value="enhance/${compName}/work/cal/womonth/maWoMonthWoList_${compNo}.jsp" />
				<c:if test="${udf:isExist(filePath)}">
					<c:import charEncoding="UTF-8" url="/enhance/${compName}/work/cal/womonth/maWoMonthWoList_${compNo}.jsp"></c:import>
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
			<div class="function_box list2">
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
							<div class="legend_tx"><span class="p"><strong>T</strong></span> : <bean:message key="LABEL.woTarget"/> &nbsp;&nbsp;&nbsp;<span class="s"><strong>P</strong></span> : <bean:message key="LABEL.woIncomplete"/>&nbsp;&nbsp;&nbsp;<span class="c"><strong>C</strong></span> : <bean:message key="LABEL.woCompleted"/></div>
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
<%--===========================================================================
월간작업일정 - 목록
author  kim21017
version $Id: maWoMonthSchedList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.cal.pmmonth.action.MaWoMonthSchedListAction" %>
<%@ page import="dream.work.cal.pmmonth.action.MaWoMonthSchedDetailAction" %>
<%@ page import="dream.work.list.action.MaWoResultMstrDetailAction" %>
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
<!-- 교정작업일정 -->
<title><bean:message key='MENU.MONTHCALIBSCHED'/></title>
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
var woTypeAc;
var wkCtrDescAc;
var subMngAc;
var plantAc;
var usaDeptAc;
var pequipDescAc;
var pusaDeptAc;
function loadPage() 
{
	//작업종류
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterWoTypeId'].value = "PMC";
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterWoTypeDesc'].value = "PMC";
    valSysDir('maWoMonthSchedCommonDTO.filterWoTypeId', 'maWoMonthSchedCommonDTO.filterWoTypeDesc', 'WO_TYPE', true);

	//계측기
	maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterWoTypeId'].value = "PMC";
	maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterEqCtgTypeId'].value = "TL";
	//부서
	maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterDeptId'].value    = loginUser.filterDeptId;
	maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterDeptDesc'].value  = loginUser.filterDeptDesc;
	//년월
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterYyyymm'].value = dateToData(getToday()).substr(0, 4)+"-"+dateToData(getToday()).substr(4, 2);

	if(loginUser.eqLocId!='null'){
		maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterEqLocId'].value = loginUser.eqLocId;
		maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterEqLocDesc'].value = loginUser.eqLocDesc;
	}
/*     if(loginUser.wkctrId!='null'){
    	maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterWkCtrId'].value = loginUser.wkctrId;
    	maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterWkCtrDesc'].value = loginUser.wkctrDesc;
	} */
    if(loginUser.filterWkCtrId!='null'){
    	maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterWkCtrId'].value = loginUser.filterWkCtrId;
    	maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterWkCtrDesc'].value = loginUser.filterWkCtrDesc;
	}
    if(loginUser.eqCtgTypeId!='null'){
    	maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterEqCtgTypeId'].value = loginUser.eqCtgTypeId;
    	maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterEqCtgTypeDesc'].value = loginUser.eqCtgTypeDesc;
	}
	//공장명
    if(loginUser.filterPlant!='null'){
    	maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
    }
    initGrid();

	hideBtn("SAVE");
	hideBtn("EDITCNCL");
    
    deptAc = new autoC({"maWoMonthSchedCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcDisplay("DESCRIPTION");
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maWoMonthSchedCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "maWoMonthSchedCommonDTO.filterPlantId"
    	,"plantDesc" : "maWoMonthSchedCommonDTO.filterPlantDesc"
    });
    deptAc.init();
    
    exeDeptAc = new autoC({"maWoMonthSchedCommonDTO.filterExeDeptDesc":"description"});
    exeDeptAc.setAcDisplay("DESCRIPTION");
    exeDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    exeDeptAc.setTable("TADEPT");
    exeDeptAc.setAcResultMap({
        "maWoMonthSchedCommonDTO.filterExeDeptId":"dept_id"
    });
    exeDeptAc.setAcDConditionMap({
    	"plant" : "maWoMonthSchedCommonDTO.filterPlantId"
    	,"plantDesc" : "maWoMonthSchedCommonDTO.filterPlantDesc"
    });
    exeDeptAc.init();
    
    equipDescAc = new autoC({"maWoMonthSchedCommonDTO.filterEquipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   ,"eqctg_type":"TL"
 	   });
    equipDescAc.setAcResultMap({
 	        "maWoMonthSchedCommonDTO.filterEquipId":"equip_id"
 	    });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "maWoMonthSchedCommonDTO.filterEqLocId",
    	"eqctg_id" : "maWoMonthSchedCommonDTO.filterEqCtgId",
    	"dept_id" : "maWoMonthSchedCommonDTO.filterDeptId",
    	"plant" : "maWoMonthSchedCommonDTO.filterPlantId"
    });
    equipDescAc.init();
    
    eqLocDescAc = new autoC({"maWoMonthSchedCommonDTO.filterEqLocDesc":"full_desc"});
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcResultMap({
        "maWoMonthSchedCommonDTO.filterEqLocId":"eqloc_id"
    });
    eqLocDescAc.setAcDConditionMap({
    	"plant" : "maWoMonthSchedCommonDTO.filterPlantId"
    });
    eqLocDescAc.init();
    
    eqCtgTypeAc = new autoC({"maWoMonthSchedCommonDTO.filterEqCtgDesc":"full_desc"});
    eqCtgTypeAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcResultMap({
        "maWoMonthSchedCommonDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();

	//법정설비여부 AC
    acSysDesc("maWoMonthSchedCommonDTO.filterIsLawEq","maWoMonthSchedCommonDTO.filterIsLawEq","IS_USE",true);
    
    mainMngAc = new autoC({"maWoMonthSchedCommonDTO.filterMainMngName":"emp_name"});
    mainMngAc.setAcDisplay("EMP_NAME");
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maWoMonthSchedCommonDTO.filterMainMngId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maWoMonthSchedCommonDTO.filterDeptId",
    	"plant" : "maWoMonthSchedCommonDTO.filterPlantId"
    });
    mainMngAc.init();
    
    woTypeAc = new autoC({"maWoMonthSchedCommonDTO.filterWoTypeDesc":"description"});
    woTypeAc.setAcDisplay("DESCRIPTION");
    woTypeAc.setAcConditionMap({
        	"list_type":"WO_TYPE",
        	"param2":"PM",
        	"is_use":"Y",
  	   });
    woTypeAc.setTable("TACDSYSD");
    woTypeAc.setAcResultMap({
        "maWoMonthSchedCommonDTO.filterWoTypeId":"cdsysd_no"
    });
    woTypeAc.init();
    
    wkCtrDescAc = new autoC({"maWoMonthSchedCommonDTO.filterWkCtrDesc":"description"});
    wkCtrDescAc.setAcDisplay("DESCRIPTION");
    wkCtrDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    wkCtrDescAc.setTable("TAWKCTR");
    wkCtrDescAc.setAcResultMap({
        "maWoMonthSchedCommonDTO.filterWkCtrId":"wkctr_id"
    });
    wkCtrDescAc.init();

	//설비유형 AC
    acSysDesc("maWoMonthSchedCommonDTO.filterEqCtgTypeDesc","maWoMonthSchedCommonDTO.filterEqCtgTypeId","EQCTG_TYPE");
    
    subMngAc = new autoC({"maWoMonthSchedCommonDTO.filterSubMngName":"emp_name"});
    subMngAc.setAcDisplay("EMP_NAME");
    subMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    subMngAc.setTable("TAEMP");
    subMngAc.setAcResultMap({
        "maWoMonthSchedCommonDTO.filterSubMngId":"emp_id"
    });
    subMngAc.setAcDConditionMap({
    	"dept_id" : "maWoMonthSchedCommonDTO.filterDeptId",
    	"plant" : "maWoMonthSchedCommonDTO.filterPlantId"
    });
    subMngAc.init();

    plantAc = new autoC({"maWoMonthSchedCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "maWoMonthSchedCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
  	//사용부서
    usaDeptAc = new autoC({"maWoMonthSchedCommonDTO.filterEqUsaDeptDesc":"description"});
    usaDeptAc.setAcDisplay("DESCRIPTION");
    usaDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    usaDeptAc.setTable("TADEPT");
    usaDeptAc.setAcResultMap({
        "maWoMonthSchedCommonDTO.filterEqUsaDeptId":"dept_id"
    });
    usaDeptAc.setAcDConditionMap({
    	"plant" : "maWoMonthSchedCommonDTO.filterPlantId"
    	,"plantDesc" : "maWoMonthSchedCommonDTO.filterPlantDesc"
    });
    usaDeptAc.init();
    
    //상위설비명
    pequipDescAc = new autoC({"maWoMonthSchedCommonDTO.filterPequipDesc":"description"});
    pequipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   ,"eqctg_type":"MC"
 	   });
    pequipDescAc.setAcResultMap({
        "maWoMonthSchedCommonDTO.filterPequipId":"equip_id"
    });
    pequipDescAc.setTable("TAEQUIPMENT");
    pequipDescAc.setAcDConditionMap({
    	"eqloc_id" : "maWoMonthSchedCommonDTO.filterEqLocId",
    	"eqctg_id" : "maWoMonthSchedCommonDTO.filterEqCtgId",
    	"dept_id" : "maWoMonthSchedCommonDTO.filterDeptId",
    	"plant" : "maWoMonthSchedCommonDTO.filterPlantId"
    });
    pequipDescAc.init();
    
    //상위설비사용부서
    pusaDeptAc = new autoC({"maWoMonthSchedCommonDTO.filterPEqUsaDeptDesc":"description"});
    pusaDeptAc.setAcDisplay("DESCRIPTION");
    pusaDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    pusaDeptAc.setTable("TADEPT");
    pusaDeptAc.setAcResultMap({
        "maWoMonthSchedCommonDTO.filterPEqUsaDeptId":"dept_id"
    });
    pusaDeptAc.setAcDConditionMap({
    	"plant" : "maWoMonthSchedCommonDTO.filterPlantId"
    	,"plantDesc" : "maWoMonthSchedCommonDTO.filterPlantDesc"
    });
    pusaDeptAc.init();
    
	//주기구분 AC
    acSysDesc("maWoMonthSchedCommonDTO.periodTypeDesc","maWoMonthSchedCommonDTO.periodType","PERIOD_TYPE");
	
    acSysDesc("maWoMonthSchedCommonDTO.filterPmTypeDesc","maWoMonthSchedCommonDTO.filterPmTypeId","PMC_TYPE");
	// 작업상태 AC
    acSysDesc("maWoMonthSchedCommonDTO.filterWoStatusDesc","maWoMonthSchedCommonDTO.filterWoStatus","WO_STATUS");
	//교정사유 AC
    acSysDesc("maWoMonthSchedCommonDTO.filterCalTypeDesc","maWoMonthSchedCommonDTO.filterCalTypeId","CALIB_TYPE", true);

}

/*작업종류 선택후 실행*/
// function afterAutoCmpt(code)
// {
// 		if(code=="maWoMonthSchedCommonDTO.filterWoTypeDesc")
// 		{
// 		 	var listType = maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterWoTypeId'].value+"_TYPE";
// 			//작업형태  AC
// 		    acSysDesc("maWoMonthSchedCommonDTO.filterPmTypeDesc","maWoMonthSchedCommonDTO.filterPmTypeId",listType);
// 		}
// }


/*작업종류 선택후 실행*/
// function afterSetValue(lovType)
// {
// 	if(lovType=="WO_TYPE")
// 	{
// 	 	var listType = maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterWoTypeId'].value+"_TYPE";
// 		//작업형태  AC
// 	    acSysDesc("maWoMonthSchedCommonDTO.filterPmTypeDesc","maWoMonthSchedCommonDTO.filterPmTypeId",listType);
// 	}
// }
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
    	maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.pmSchedId'].value = "";
        return sortColumn("maWoMonthSchedList", this, maWoMonthSchedListForm, "PMSCHEDID", ind, direction);
    });
	myGrid.attachEvent("onBeforeSelect",function(rowId, state){

		lv_selectedId = rowId;
		var status 		= getValueById(myGrid, rowId, "WOSTATUS");
		var pmStatus 	= getValueById(myGrid, rowId, "PMSTATUSCODE");
	    var index 		= getIndexById(myGrid, "SCHEDDATE");
		
	    if(isEdit)
		{
			if(status == "C" || pmStatus == "S") //완료되거나 스케줄 확정되면 스케줄 변화안됨.
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
	    else{
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
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){		
		lv_selectedId = rowId;
		if(!isEdit)
		{
			goOpen(rowId);
		}
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
	var url = contextPath + "/maWoMonthSchedList.do";
	var stAct = "<%=MaWoMonthSchedListAction.MONTH_SCHED_CHANGE %>";

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
	//Control Button
	hideBtn("SAVE");
	showBtn("EDIT");
	hideBtn("EDITCNCL");
	
	showBtn("FIX");
	showBtn("DELETE");
	showBtn("EXCEL");
	showBtn("SETTING");
	
	maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.pmSchedId'].value = "";	// 검색시 Tab 이동Key Clear

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
    var url = contextPath + "/maWoMonthSchedList.do";
    maWoMonthSchedListForm.elements['strutsAction'].value = '<%=MaWoMonthSchedListAction.MONTH_SCHED_LIST_FIND%>';
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.pmSchedId'].value = "";	// 검색시 Tab 이동Key Clear
	maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.wkOrId'].value = "";
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.yyyymmdd'].value     	= yyyymmdd;
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.schedType'].value    	= type;
    
    doGridAction("Search", myGrid, "gridbox", url, FormQueryString(maWoMonthSchedListForm),"","Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_wkOrId)
{
	goSearch();
	maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.wkOrId'].value = _wkOrId;
	findSchedRow("ReloadRow");
	maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.wkOrId'].value = "";
}

function findSchedRow(sheetAction) {
	var url = contextPath + "/maWoMonthSchedList.do";
	maWoMonthSchedListForm.elements['strutsAction'].value = '<%=MaWoMonthSchedListAction.MONTH_SCHED_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maWoMonthSchedListForm), "pmSchedId","Y");
}

/**
 * 저장후 리스트만 재조회
 */
 function searchList(){
	var url = contextPath + "/maWoMonthSchedList.do";
    maWoMonthSchedListForm.elements['strutsAction'].value = '<%=MaWoMonthSchedListAction.MONTH_SCHED_LIST_FIND%>';
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.pmSchedId'].value = "";	// 검색시 Tab 이동Key Clear
	maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.wkOrId'].value = "";
    
    myGrid.clearAll(); setLoading("gridbox");
    $.post(url,FormQueryString(maWoMonthSchedListForm), function(_data){
    	myGrid.parse(_data,"js");
    });
}
/**
 * 월간작업일정 조회
 */
function findSchedList(){
	var url = contextPath + "/maWoMonthSchedList.do";
	maWoMonthSchedListForm.elements['strutsAction'].value = '<%=MaWoMonthSchedListAction.MONTH_SCHED_FIND%>';
	setModal();
	$.post(url,FormQueryString(maWoMonthSchedListForm), function(_data){
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
	document.getElementById("month_title").innerText = maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.yyyymmdd'].value;
	
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
	maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.pmSchedId'].value = "";	// 검색시 Tab 이동Key Clear
	maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.wkOrId'].value = "";
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.schedType'].value = "";
	
	maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.yyyymmdd'].value     	= maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterYyyymm'].value;
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.deptId'].value       	= maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterDeptId'].value;        
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.deptDesc'].value     	= maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterDeptDesc'].value;      
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.exeDeptId'].value      = maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterExeDeptId'].value;        
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.exeDeptDesc'].value    = maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterExeDeptDesc'].value;      
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.eqLocId'].value      	= maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterEqLocId'].value;       
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.eqLocDesc'].value    	= maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterEqLocDesc'].value;     
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.eqCtgId'].value      	= maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterEqCtgId'].value;       
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.eqCtgDesc'].value    	= maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterEqCtgDesc'].value;     
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.eqCtgTypeId'].value    = maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterEqCtgTypeId'].value;   
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.eqCtgTypeDesc'].value  = maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterEqCtgTypeDesc'].value; 
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.mainMngId'].value    	= maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterMainMngId'].value;     
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.mainMngName'].value  	= maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterMainMngName'].value;   
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.subMngId'].value     	= maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterSubMngId'].value;      
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.subMngName'].value   	= maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterSubMngName'].value;    
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.plfTypeId'].value    	= maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterPlfTypeId'].value;     
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.plfTypeDesc'].value  	= maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterPlfTypeDesc'].value;   
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.woTypeId'].value    	= maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterWoTypeId'].value;      
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.woTypeDesc'].value  	= maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterWoTypeDesc'].value;    
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.pmTypeId'].value    	= maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterPmTypeId'].value;      
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.pmTypeDesc'].value  	= maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterPmTypeDesc'].value;    
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.wkCtrId'].value     	= maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterWkCtrId'].value;       
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.wkCtrDesc'].value   	= maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterWkCtrDesc'].value;     
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.equipId'].value     	= maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterEquipId'].value;       
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.equipDesc'].value   	= maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterEquipDesc'].value;     
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.isLawEq'].value      	= maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterIsLawEq'].value;       
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.pmNo'].value         	= maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterPmNo'].value;          
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.plantId'].value        = maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterPlantId'].value;       
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.plantDesc'].value      = maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterPlantDesc'].value;     
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.equipNo'].value      	= maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterEquipNo'].value;       
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.eqUsaDeptId'].value    = maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterEqUsaDeptId'].value;   
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.eqUsaDeptDesc'].value  = maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterEqUsaDeptDesc'].value; 
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.pequipId'].value     	= maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterPequipId'].value;      
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.pequipDesc'].value   	= maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterPequipDesc'].value;    
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.peqUsaDeptId'].value   = maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterPEqUsaDeptId'].value;  
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.peqUsaDeptDesc'].value = maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterPEqUsaDeptDesc'].value;
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.roomNo'].value     	= maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterRoomNumber'].value;    
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.calTypeId'].value     	= maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterCalTypeId'].value;     
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.calTypeDesc'].value    = maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterCalTypeDesc'].value;   
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.woStatusId'].value 	= maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterWoStatusId'].value;    
    maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.woStatusDesc'].value 	= maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.filterWoStatusDesc'].value;  
	
	findSchedList();
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
	var pmStatus = getValueById(myGrid, lv_selectedId, "pmStatusCode");
	var wkOrId = getValueById(myGrid, lv_selectedId, "wkorId");
	
	if(pmStatus == "P")
	{
		maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.pmSchedId'].value =  getValueById(myGrid, lv_selectedId,'PMSCHEDID'); 
		goCommonTabPage(maWoMonthSchedListForm, <%= MaWoMonthSchedDetailAction.MONTH_SCHED_DETAIL_INIT %>, pageId, beforePageId);
	}
	else
	{
		maWoMonthSchedListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value = wkOrId;
		goCommonTabPage(maWoMonthSchedListForm, <%= MaWoResultMstrDetailAction.WO_RESULT_DETAIL_INIT %>, pageId, beforePageId);

	}

	beforePageId = pageId;
	
}

/**
 * 상세 열기
 */
function goOpen(rowId)
{
	/* if(""!=beforePageId)
		goClose(beforePageId, this); */
	var pmStatus = getValueById(myGrid, rowId, "pmStatusCode");
	
	if(pmStatus == "P")
	{
		goTabPage('maWoResultPmGnlMstrDetail');
	}
	else
	{
		var wkOrId = getValueById(myGrid, rowId, "wkorId");
		var pmType = getValueById(myGrid, rowId, "PMTYPE");
		var woparam = getValueById(myGrid, rowId, "WOPARAM");
		pmType = pmType.substring(0,1)+pmType.substring(1,pmType.length).toLowerCase();

		maWoMonthSchedListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value = wkOrId;
		maWoMonthSchedListForm.elements['maWoResultMstrCommonDTO.selectedWoType'].value = "PMC";
		
		goTabPage(woparam);
	}
}


/**
 * 상세 열기
 */
function goWo()
{ 
	if(lv_selectedId == "undefined" || lv_selectedId == "") return;
	
	var pmStatus = getValueById(myGrid, lv_selectedId, "pmStatusCode");
	var wkOrId = getValueById(myGrid, lv_selectedId, "wkorId");
	var pmType = getValueById(myGrid, lv_selectedId, "PMTYPE");
	var woparam = getValueById(myGrid, lv_selectedId, "WOPARAM");
	pmType = pmType.substring(0,1)+pmType.substring(1,pmType.length).toLowerCase();
	
	if(pmStatus != "P"){
		var url   = contextPath + "/"+woparam+".do";

		var popWidth = 1010;
		var popHeight = 640;

	    // pop up이 중앙에 위치하게 한다.
	    var TopPosition  = (screen.height/2 - popHeight/2);
	    var LeftPosition = (screen.width/2 - popWidth/2);

	    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
	              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
	    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
	    
	    var param = "strutsAction=8001&maWoResultMstrCommonDTO.wkOrId="+ wkOrId;
	  
	    openWindowWithPost(url, "WO_DETAIL", param, pos);
	}else{
		alertMessage1('<bean:message key="MESSAGE.MSG0024"/>');
	}
}

function goPmstd()
{
	if(lv_selectedId == "undefined" || lv_selectedId == "") return;
	
	//strutsAction[0]=1001
	//maWoMonthSchedCommonDTO.pmId[0]=4522
	var fileName = getValueById(myGrid, lv_selectedId, "PARAM");
	var pmId     = getValueById(myGrid, lv_selectedId, "PMID");

	var url   = contextPath + "/"+fileName+".do";

	var popWidth = 1010;
	var popHeight = 640;

    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
    
    //var param = "strutsAction=1001&maWoMonthSchedCommonDTO.pmId="+ pmId;
    var param = "strutsAction=8001&maPmMstrCommonDTO.pmId="+ pmId;
    
    openWindowWithPost(url, "PM_DETAIL", param, pos);
}

/**
 * 엑셀 다운.
 */
 function goExcel()
 {
	maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.pmSchedId'].value = "";	// 검색시 Tab 이동Key Clear
	maWoMonthSchedListForm.elements['maWoMonthSchedCommonDTO.wkOrId'].value = "";
	excelServerAction("maWoMonthSchedList", maWoMonthSchedListForm);
 } 
 
/**
 * 일별 확정
 */
function goFix(){
	//myGrid에 1(2번째)번에 체크가 되어있으면 2번 데이터 가져오기
	 var fixArray = getSelectRows(myGrid, 'ISDELCHECK', 'PMSCHEDID'); //Grid, check box column seq, pk column seq
	//체크된게 없으면 return
	 if(typeof fixArray == "undefined"){
			alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
			return;
		}
	 
	dhtmlx.confirm('<bean:message key="MESSAGE.MSG045"/>', function(result){
		if(result){
			maWoMonthSchedListForm.strutsAction.value = '<%=MaWoMonthSchedListAction.MONTH_SCHED_DAILY_SCHEDULED%>';
		 	var url = contextPath + "/maWoMonthSchedList.do";
		 	
		 	ajaxPost(url,FormQueryString(maWoMonthSchedListForm)+fixArray)
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
<html:form action="/maWoMonthSchedList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maWoMonthSchedCommonDTO.pmSchedId"/><!-- Key -->
<html:hidden property="maWoMonthSchedCommonDTO.filterDeptId"/>
<html:hidden property="maWoMonthSchedCommonDTO.filterExeDeptId"/>
<html:hidden property="maWoMonthSchedCommonDTO.yyyymmdd"/>
<html:hidden property="maWoMonthSchedCommonDTO.schedType"/>
<html:hidden property="maWoMonthSchedCommonDTO.deptId"/>
<html:hidden property="maWoMonthSchedCommonDTO.deptDesc"/>
<html:hidden property="maWoMonthSchedCommonDTO.exeDeptId"/>
<html:hidden property="maWoMonthSchedCommonDTO.exeDeptDesc"/>
<html:hidden property="maWoMonthSchedCommonDTO.eqLocId"/>
<html:hidden property="maWoMonthSchedCommonDTO.eqLocDesc"/>
<html:hidden property="maWoMonthSchedCommonDTO.eqCtgId"/>
<html:hidden property="maWoMonthSchedCommonDTO.eqCtgDesc"/>
<html:hidden property="maWoMonthSchedCommonDTO.eqCtgTypeId"/>
<html:hidden property="maWoMonthSchedCommonDTO.eqCtgTypeDesc"/>
<html:hidden property="maWoMonthSchedCommonDTO.mainMngId"/>
<html:hidden property="maWoMonthSchedCommonDTO.mainMngName"/>
<html:hidden property="maWoMonthSchedCommonDTO.subMngId"/>
<html:hidden property="maWoMonthSchedCommonDTO.subMngName"/>
<html:hidden property="maWoMonthSchedCommonDTO.plfTypeId"/>
<html:hidden property="maWoMonthSchedCommonDTO.plfTypeDesc"/>
<html:hidden property="maWoMonthSchedCommonDTO.isLawEq"/>
<html:hidden property="maWoMonthSchedCommonDTO.woTypeId"/>
<html:hidden property="maWoMonthSchedCommonDTO.woTypeDesc"/>
<html:hidden property="maWoMonthSchedCommonDTO.pmTypeId"/>
<html:hidden property="maWoMonthSchedCommonDTO.pmTypeDesc"/>
<html:hidden property="maWoMonthSchedCommonDTO.wkCtrId"/>
<html:hidden property="maWoMonthSchedCommonDTO.wkCtrDesc"/>
<html:hidden property="maWoMonthSchedCommonDTO.equipId"/>
<html:hidden property="maWoMonthSchedCommonDTO.equipDesc"/>
<html:hidden property="maWoMonthSchedCommonDTO.pmNo"/>
<html:hidden property="maWoMonthSchedCommonDTO.filterEqLocId"/>
<html:hidden property="maWoMonthSchedCommonDTO.filterEqCtgId"/>
<html:hidden property="maWoMonthSchedCommonDTO.filterEqCtgTypeId"/>
<html:hidden property="maWoMonthSchedCommonDTO.filterPlfTypeId"/>
<html:hidden property="maWoMonthSchedCommonDTO.filterPlfTypeDesc"/>
<html:hidden property="maWoMonthSchedCommonDTO.filterWoTypeId"/>
<html:hidden property="maWoMonthSchedCommonDTO.filterWoTypeDesc"/>
<html:hidden property="maWoMonthSchedCommonDTO.filterPmTypeId"/>
<html:hidden property="maWoMonthSchedCommonDTO.filterWkCtrId"/>
<html:hidden property="maWoMonthSchedCommonDTO.filterMainMngId"/>
<html:hidden property="maWoMonthSchedCommonDTO.filterSubMngId"/>
<html:hidden property="maWoMonthSchedCommonDTO.filterEquipId"/>
<html:hidden property="maWoMonthSchedCommonDTO.periodType"/>
<html:hidden property="maWoMonthSchedCommonDTO.filterPlantId"/>
<html:hidden property="maWoMonthSchedCommonDTO.plantId"/>
<html:hidden property="maWoMonthSchedCommonDTO.plantDesc"/>

<html:hidden property="maWoMonthSchedCommonDTO.filterPequipId"/>
<html:hidden property="maWoMonthSchedCommonDTO.filterEqUsaDeptId"/>
<html:hidden property="maWoMonthSchedCommonDTO.filterPEqUsaDeptId"/>
<html:hidden property="maWoMonthSchedCommonDTO.equipNo"/>
<html:hidden property="maWoMonthSchedCommonDTO.pequipId"/>
<html:hidden property="maWoMonthSchedCommonDTO.pequipDesc"/>
<html:hidden property="maWoMonthSchedCommonDTO.eqUsaDeptId"/>
<html:hidden property="maWoMonthSchedCommonDTO.eqUsaDeptDesc"/>
<html:hidden property="maWoMonthSchedCommonDTO.peqUsaDeptId"/>
<html:hidden property="maWoMonthSchedCommonDTO.peqUsaDeptDesc"/>
<html:hidden property="maWoMonthSchedCommonDTO.filterWoStatusId"/>
<html:hidden property="maWoMonthSchedCommonDTO.filterCalTypeId"/>
<html:hidden property="maWoMonthSchedCommonDTO.roomNo"/>
<html:hidden property="maWoMonthSchedCommonDTO.calTypeId"/>
<html:hidden property="maWoMonthSchedCommonDTO.calTypeDesc"/>
<html:hidden property="maWoMonthSchedCommonDTO.woStatusId"/>
<html:hidden property="maWoMonthSchedCommonDTO.woStatusDesc"/>
<html:hidden property="maWoMonthSchedCommonDTO.wkOrId"/>

<html:hidden property="maWoResultMstrCommonDTO.wkOrId"/>
<html:hidden property="maWoResultMstrCommonDTO.selectedWoType"/>
<html:hidden property="maWoResultMstrCommonDTO.selectedPmType"/>

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
						<html:text property="maWoMonthSchedCommonDTO.filterYyyymm" readonly="true"/>
						<p class="open_mon_calendar"><span>날짜</span></p>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.pmNo"/></label>
					<div class="input_box">
						<html:text property="maWoMonthSchedCommonDTO.filterPmNo" tabindex="10"/>
					</div>
				</div>
				<!-- 계획부서 -->
				<div class="field">
					<label><bean:message key="LABEL.planDept"/></label>
					<div class="input_box">
						<html:text property="maWoMonthSchedCommonDTO.filterDeptDesc" tabindex="15" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 실행부서 -->
				<div class="field">
					<label><bean:message key="LABEL.exeDept"/></label>
					<div class="input_box">
						<html:text property="maWoMonthSchedCommonDTO.filterExeDeptDesc" tabindex="20" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 계측기번호 -->
				<div class="field">
					<label><bean:message key="LABEL.toolNo"/></label>
					<div class="input_box">
						<html:text property="maWoMonthSchedCommonDTO.filterEquipNo" tabindex="23" />
					</div>
				</div>
				<!-- 계측기명 -->
				<div class="field">
					<label><bean:message key="LABEL.toolName"/></label>
					<div class="input_box">
						<html:text property="maWoMonthSchedCommonDTO.filterEquipDesc" tabindex="25" />
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
						<html:text property="maWoMonthSchedCommonDTO.filterEqCtgDesc" tabindex="35" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 내/외자  -->
				<!-- 법정설비여부 -->
				<div class="field hidden">
					<label><bean:message key="LABEL.isLawEq"/></label>
					<div class="input_box">
						<html:text property="maWoMonthSchedCommonDTO.filterIsLawEq" tabindex="60" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 관리자(정) -->
				<div class="field hidden">
					<label><bean:message key="LABEL.mainManager"/></label>
					<div class="input_box">
						<html:text property="maWoMonthSchedCommonDTO.filterMainMngName" tabindex="70" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 관리자(부) -->
				<div class="field hidden">
					<label><bean:message key="LABEL.subManager"/></label>
					<div class="input_box">
						<html:text property="maWoMonthSchedCommonDTO.filterSubMngName" tabindex="90" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 작업형태  -->
				<div class="field">
					<label><bean:message key="LABEL.pmType"/></label>
					<div class="input_box">
						<html:text property="maWoMonthSchedCommonDTO.filterPmTypeDesc" tabindex="130" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>  
				<!-- 작업그룹 -->   
                <div class="field">
                     <label><bean:message key="LABEL.wkCtr"/></label>
                     <div class="input_box">
                        <html:text property="maWoMonthSchedCommonDTO.filterWkCtrDesc" tabindex="140"/>
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
							<html:text property="maWoMonthSchedCommonDTO.cycle" tabindex="150" styleClass="num"/>
					</div>
				</div>
				<!-- 주기구분 -->
				<div class="field">
					<label><bean:message key="LABEL.periodTypeDesc"/></label>
					<div class="input_box">
							<html:text property="maWoMonthSchedCommonDTO.periodTypeDesc" tabindex="160" />
							<p class="open_spop">
								<a><span>조회</span></a>
							</p>
					</div>
				</div>
				<!-- 설비유형  -->
				<div class="field hidden">
					<label><bean:message key="LABEL.eqCtgType"/></label>
					<div class="input_box">
						<html:text property="maWoMonthSchedCommonDTO.filterEqCtgTypeDesc" tabindex="170" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
							<html:text property="maWoMonthSchedCommonDTO.filterPlantDesc" tabindex="180" />
							<p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
				<!-- 설비사용부서  -->
                <div class="field">
                    <label><bean:message key="LABEL.usedDept"/></label>
                    <div class="input_box">
							<html:text property="maWoMonthSchedCommonDTO.filterEqUsaDeptDesc" tabindex="190" />
							<p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
				<!-- 상위설비명  -->
                <div class="field">
                    <label><bean:message key="LABEL.parEquipDesc"/></label>
                    <div class="input_box">
							<html:text property="maWoMonthSchedCommonDTO.filterPequipDesc" tabindex="200" />
							<p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
				<!-- 상위설비사용부서  -->
                <div class="field">
                    <label><bean:message key="LABEL.pUsageDept"/></label>
                    <div class="input_box">
							<html:text property="maWoMonthSchedCommonDTO.filterPEqUsaDeptDesc" tabindex="210" />
							<p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
				<!-- Room No. -->
				<div class="field">
					<label><bean:message key="LABEL.roomNumber"/></label>
					<div class="input_box">
	                    <html:text property="maWoMonthSchedCommonDTO.filterRoomNumber" tabindex="100" />
	                </div>
				</div>
				<!-- 계측기 위치 -->
				<div class="field">
					<label><bean:message key="LABEL.toolLoc"/></label>
					<div class="input_box">
						<html:text property="maWoMonthSchedCommonDTO.filterEqLocDesc" tabindex="60"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>  
				<!-- w/o 상태  -->
				<div class="field">
					<label><bean:message key="LABEL.woStatus"/></label>
					<div class="input_box">
						<html:text property="maWoMonthSchedCommonDTO.filterWoStatusDesc" tabindex="140" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 교정사유 -->
				<div class="field">
					<label><bean:message key="LABEL.calType"/></label>
					<div class="input_box">
						<html:text property="maWoMonthSchedCommonDTO.filterCalTypeDesc" tabindex="240" />
						<p class="open_spop">
							<a><span>조회</span></a>
						</p>
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
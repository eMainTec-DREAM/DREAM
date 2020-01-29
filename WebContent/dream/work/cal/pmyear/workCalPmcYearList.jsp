<%--===========================================================================
연간작업일정 - 목록
author  kim21017
version $Id: maWoYearSchedList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.cal.pmyear.action.MaWoYearSchedListAction" %>
<%@ page import="dream.work.cal.pmyear.action.MaWoYearSchedDetailAction" %>
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
<title><bean:message key='MENU.YEARCALIBSCHED'/></title>
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
var pmTypeAc;
var plantAc;
var usaDeptAc;
var pequipDescAc;
var pusaDeptAc;
function loadPage() 
{
	//작업종류
	maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterWoTypeId'].value = "PMC";
	maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterWoTypeDesc'].value = "PMC";
    valSysDir('maWoYearSchedCommonDTO.filterWoTypeId', 'maWoYearSchedCommonDTO.filterWoTypeDesc', 'WO_TYPE', true);
	
	//계측기
	maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterWoTypeId'].value = "PMC";
	maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterEqCtgTypeId'].value = "TL";
	//부서
	maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterDeptId'].value    = loginUser.filterDeptId;
	maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterDeptDesc'].value  = loginUser.filterDeptDesc;
	//년도	
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterYear'].value = dateToData(getToday()).substr(0, 4);
	if(loginUser.eqLocId!='null'){
		//maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterEqLocId'].value = loginUser.eqLocId;
		//maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterEqLocDesc'].value = loginUser.eqLocDesc;
	}
/*     if(loginUser.wkctrId!='null'){
    	maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterWkCtrId'].value = loginUser.wkctrId;
    	maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterWkCtrDesc'].value = loginUser.wkctrDesc;
	} */
    if(loginUser.filterWkCtrId!='null'){
    	maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterWkCtrId'].value = loginUser.filterWkCtrId;
    	maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterWkCtrDesc'].value = loginUser.filterWkCtrDesc;
	}
    if(loginUser.eqCtgTypeId!='null'){
    	maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterEqCtgTypeId'].value = loginUser.eqCtgTypeId;
    	maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterEqCtgTypeDesc'].value = loginUser.eqCtgTypeDesc;
	}
  	//공장명
    if(loginUser.filterPlant!='null'){
    	maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
    }
  	
    initGrid();

	hideBtn("SAVE");
	hideBtn("EDITCNCL");
  
    deptAc = new autoC({"maWoYearSchedCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcDisplay("DESCRIPTION");
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maWoYearSchedCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "maWoYearSchedCommonDTO.filterPlantId"
    	,"plantDesc" : "maWoYearSchedCommonDTO.filterPlantDesc"
    });
    deptAc.init();
    
    exeDeptAc = new autoC({"maWoYearSchedCommonDTO.filterExeDeptDesc":"description"});
    exeDeptAc.setAcDisplay("DESCRIPTION");
    exeDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    exeDeptAc.setTable("TADEPT");
    exeDeptAc.setAcResultMap({
        "maWoYearSchedCommonDTO.filterExeDeptId":"dept_id"
    });
    exeDeptAc.setAcDConditionMap({
    	"plant" : "maWoYearSchedCommonDTO.filterPlantId"
    	,"plantDesc" : "maWoYearSchedCommonDTO.filterPlantDesc"
    });
    exeDeptAc.init();
    
    equipDescAc = new autoC({"maWoYearSchedCommonDTO.filterEquipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   ,"eqctg_type":"TL"
 	   });
    equipDescAc.setAcResultMap({
        "maWoYearSchedCommonDTO.filterEquipId":"equip_id"
    });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "maWoYearSchedCommonDTO.filterEqLocId",
    	"eqctg_id" : "maWoYearSchedCommonDTO.filterEqCtgId",
    	"dept_id" : "maWoYearSchedCommonDTO.filterDeptId",
    	"plant" : "maWoYearSchedCommonDTO.filterPlantId"
    });
    equipDescAc.init();
    
    eqLocDescAc = new autoC({"maWoYearSchedCommonDTO.filterEqLocDesc":"full_desc"});
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcResultMap({
        "maWoYearSchedCommonDTO.filterEqLocId":"eqloc_id"
    });
    eqLocDescAc.setAcDConditionMap({
    	"plant" : "maWoYearSchedCommonDTO.filterPlantId"
    });
    eqLocDescAc.init();
    
    eqCtgTypeAc = new autoC({"maWoYearSchedCommonDTO.filterEqCtgDesc":"full_desc"});
    eqCtgTypeAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcResultMap({
        "maWoYearSchedCommonDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();

	//법정설비유형  AC
    acSysDesc("maWoYearSchedCommonDTO.filterIsLawEq","maWoYearSchedCommonDTO.filterIsLawEq","IS_USE",true);
    
    mainMngAc = new autoC({"maWoYearSchedCommonDTO.filterMainMngName":"emp_name"});
    mainMngAc.setAcDisplay("EMP_NAME");
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maWoYearSchedCommonDTO.filterMainMngId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maWoYearSchedCommonDTO.filterDeptId",
    	"plant" : "maWoYearSchedCommonDTO.filterPlantId"
    });
    mainMngAc.init();
    
    woTypeAc = new autoC({"maWoYearSchedCommonDTO.filterWoTypeDesc":"description"});
    woTypeAc.setAcDisplay("DESCRIPTION");
    woTypeAc.setAcConditionMap({
        	"list_type":"WO_TYPE",
        	"param2":"PM",
        	"is_use":"Y"
  	   });
    woTypeAc.setTable("TACDSYSD");
    woTypeAc.setAcResultMap({
        "maWoYearSchedCommonDTO.filterWoTypeId":"cdsysd_no"
    });
    woTypeAc.init();
    
    wkCtrDescAc = new autoC({"maWoYearSchedCommonDTO.filterWkCtrDesc":"description"});
    wkCtrDescAc.setAcDisplay("DESCRIPTION");
    wkCtrDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    wkCtrDescAc.setTable("TAWKCTR");
    wkCtrDescAc.setAcResultMap({
        "maWoYearSchedCommonDTO.filterWkCtrId":"wkctr_id"
    });
    wkCtrDescAc.init();

	//설비유형  AC
    acSysDesc("maWoYearSchedCommonDTO.filterEqCtgTypeDesc","maWoYearSchedCommonDTO.filterEqCtgTypeId","EQCTG_TYPE");
    
    subMngAc = new autoC({"maWoYearSchedCommonDTO.filterSubMngName":"emp_name"});
    subMngAc.setAcDisplay("EMP_NAME");
    subMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    subMngAc.setTable("TAEMP");
    subMngAc.setAcResultMap({
        "maWoYearSchedCommonDTO.filterSubMngId":"emp_id"
    });
    subMngAc.setAcDConditionMap({
    	"dept_id" : "maWoYearSchedCommonDTO.filterDeptId",
    	"plant" : "maWoYearSchedCommonDTO.filterPlantId"
    });
    subMngAc.init();
    
    plantAc = new autoC({"maWoYearSchedCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "maWoYearSchedCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
    acSysDesc("maWoYearSchedCommonDTO.filterPmTypeDesc","maWoYearSchedCommonDTO.filterPmTypeId","PMC_TYPE");

    //사용부서
    usaDeptAc = new autoC({"maWoYearSchedCommonDTO.filterEqUsaDeptDesc":"description"});
    usaDeptAc.setAcDisplay("DESCRIPTION");
    usaDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    usaDeptAc.setTable("TADEPT");
    usaDeptAc.setAcResultMap({
        "maWoYearSchedCommonDTO.filterEqUsaDeptId":"dept_id"
    });
    usaDeptAc.setAcDConditionMap({
    	"plant" : "maWoYearSchedCommonDTO.filterPlantId"
    	,"plantDesc" : "maWoYearSchedCommonDTO.filterPlantDesc"
    });
    usaDeptAc.init();
    
    //상위설비명
    pequipDescAc = new autoC({"maWoYearSchedCommonDTO.filterPequipDesc":"description"});
    pequipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   ,"eqctg_type":"MC"
 	   });
    pequipDescAc.setAcResultMap({
        "maWoYearSchedCommonDTO.filterPequipId":"equip_id"
    });
    pequipDescAc.setTable("TAEQUIPMENT");
    pequipDescAc.setAcDConditionMap({
    	"eqloc_id" : "maWoYearSchedCommonDTO.filterEqLocId",
    	"eqctg_id" : "maWoYearSchedCommonDTO.filterEqCtgId",
    	"dept_id" : "maWoYearSchedCommonDTO.filterDeptId",
    	"plant" : "maWoYearSchedCommonDTO.filterPlantId"
    });
    pequipDescAc.init();
    
    //상위설비사용부서
    pusaDeptAc = new autoC({"maWoYearSchedCommonDTO.filterPEqUsaDeptDesc":"description"});
    pusaDeptAc.setAcDisplay("DESCRIPTION");
    pusaDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    pusaDeptAc.setTable("TADEPT");
    pusaDeptAc.setAcResultMap({
        "maWoYearSchedCommonDTO.filterPEqUsaDeptId":"dept_id"
    });
    pusaDeptAc.setAcDConditionMap({
    	"plant" : "maWoYearSchedCommonDTO.filterPlantId"
    	,"plantDesc" : "maWoYearSchedCommonDTO.filterPlantDesc"
    });
    pusaDeptAc.init();
    
  	// 교정사유 AC
    acSysDesc("maWoYearSchedCommonDTO.filterCalTypeDesc","maWoYearSchedCommonDTO.filterCalTypeId","CALIB_TYPE", true);
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
	myGrid.enableEditEvents(true,false,false);
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.pmSchedId'].value = "";
    	return sortColumn("maWoYearSchedList", this, maWoYearSchedListForm, "pmSchedId", ind, direction);
	});
	myGrid.attachEvent("onBeforeSelect",function(rowId, state){
		selectedId = rowId;
		var status 		= getValueById(myGrid, rowId, "WOSTATUS");
		var pmStatus 	= getValueById(myGrid, rowId, "PMSTATUSCODE");
	    var index 		= getIndexById(myGrid, "SCHEDDATE");

		if(isEdit)
		{
			if(status == "C"|| pmStatus == "S") //완료되거나 스케줄 확정되면 스케줄 변화안됨.
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
	
		return true;

	});
    
	/* myGrid.attachEvent("onCellChanged", function(rId,cInd,nValue){
	    //alert(myGrid.getColType(cInd));
	    if(myGrid.getColType(cInd) == "dhxCalendar")
	    {
	    	myGrid.cells(rId, cInd).setValue(nValue + "<img src=\"http://www.ddanzi.com/layouts/_common/images/icon-coin.png\" style=\"width:10px;display:inline;vertical-align:middle;\">");
	    }
	}); */
	
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
	
	myGrid.attachEvent("onCalendarShow", function(myCal,rId,colInd){
		myCal.hideTime();
		//setCalendar(myCal, rId, colInd);
		selDate = getValueById(myGrid, rId, "SCHEDDATE");
		//Validation
		myGrid.cells(myGrid.getSelectedId(),getIndexById(myGrid, "SCHEDDATE")).setAttribute("validate","ValidDate");
	});
	//myGrid.enableValidation(true); 
	myGrid.attachEvent("onValidationError",function(id,ind,value){
		alertMessage1('날짜 형식이 맞지 않습니다. '+value);
		setValueById(myGrid, id, "SCHEDDATE", selDate);
		//myGrid.setCellTextStyle(id,ind,"font-weight:bold;");
		return false;
	});
	
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){		
		selectedId = rowId;
		if(!isEdit)
		{
			goOpen(rowId);
		}
	});
	
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	myGrid.setDateFormat("%Y-%m-%d");

	setHeader(myGrid, "gridbox"); // grid, grid id
}
/* 
var calObj,eid;
function setCalendar(myCal, rId, colInd)
{
	myCal.detachEvent(eid);
	eid = myCal.attachEvent("onClick", function(date){
		var chedVal = myGrid.cells(rId, colInd).getValue();
		//alert(myCal.getFormatedDate("%Y-%m-%d", date));
		//src='"+contextPath+"/dream/imgSlide/"+slideImage[i]+"'
		myGrid.cells(rId, colInd).setValue(myCal.getFormatedDate("%Y-%m-%d", date)+ "<img src=\"http://www.ddanzi.com/layouts/_common/images/icon-coin.png\" style=\"width:10px;display:inline;vertical-align:middle;\">");
	});

} */

/**
 * 일정조정 (상태가 작업계획(P))
 */
function goEdit()
{
	isEdit = true;
	
	hideBtn("PMSTD");
	hideBtn("WO");
	
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
	var url = contextPath + "/maWoYearSchedList.do";
	var stAct = "<%=MaWoYearSchedListAction.YEAR_SCHED_CHANGE %>";

	//URL, StrutsAction, Grid Obj, Call Back Function, Validation Rule (not_empty)
	proGrid = setGridUpdate(url, stAct, myGrid, "afterSave");

}

function goSave()
{
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
	//Control Button
	hideBtn("SAVE");
	showBtn("EDIT");
	hideBtn("EDITCNCL");
	
	showBtn("PMSTD");
	showBtn("WO");
	
	showBtn("DELETE");
	showBtn("FIX");
	showBtn("EXCEL");
	showBtn("SETTING");
	
	maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.pmSchedId'].value = "";	// 검색시 Tab 이동Key Clear

	findSchedList();
	
	//Clear Update Mark for this page 
	clearUpdate(currentPageId);
	
	searchList();
	
	isEdit = false;
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(yyyymm,status)
{
    var url = contextPath + "/maWoYearSchedList.do";
    maWoYearSchedListForm.elements['strutsAction'].value = '<%=MaWoYearSchedListAction.YEAR_SCHED_LIST_FIND%>';
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.pmSchedId'].value = "";	// 검색시 Tab 이동Key Clear
	maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.wkOrId'].value = "";
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.yyyymm'].value = yyyymm;
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.schedType'].value = status;
    
    doGridAction('Search', myGrid, "gridbox", url, FormQueryString(maWoYearSchedListForm), "pmSchedId", "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_wkOrId)
{
	goSearch();
	maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.wkOrId'].value = _wkOrId;
	findSchedRow("ReloadRow");
   	maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.wkOrId'].value = "";
}

function findSchedRow(sheetAction) {
	var url = contextPath + "/maWoYearSchedList.do";
	maWoYearSchedListForm.elements['strutsAction'].value = '<%=MaWoYearSchedListAction.YEAR_SCHED_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maWoYearSchedListForm), "pmSchedId","Y");
}

/**
 * 저장후 리스트만 재조회
 */
 function searchList(){
	 selectedId = "";
	var url = contextPath + "/maWoYearSchedList.do";
    maWoYearSchedListForm.elements['strutsAction'].value = '<%=MaWoYearSchedListAction.YEAR_SCHED_LIST_FIND%>';
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.pmSchedId'].value = "";	// 검색시 Tab 이동Key Clear
	maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.wkOrId'].value = "";
    
    myGrid.clearAll(); setLoading("gridbox");
    $.post(url,FormQueryString(maWoYearSchedListForm), function(_data){
    	myGrid.parse(_data,"js");
    });
}
/**
 * 연간작업일정 조회
 */
function findSchedList(){

	var url = contextPath + "/maWoYearSchedList.do";
	maWoYearSchedListForm.elements['strutsAction'].value = '<%=MaWoYearSchedListAction.YEAR_SCHED_FIND%>';
	setModal();
	
	$.post(url,FormQueryString(maWoYearSchedListForm), function(_data){
		afterFindSched(JSON.parse(_data));
    });
	
}

function afterFindSched(_dataObj){
	closeModal();
	
	var _yyyy = maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.yyyymm'].value;
	
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
	maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.pmSchedId'].value = "";	// 검색시 Tab 이동Key Clear
	maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.wkOrId'].value = "";
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.schedType'].value = "";
    
	maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.yyyymm'].value 			= maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterYear'].value;
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.deptId'].value           = maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterDeptId'].value;        
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.deptDesc'].value         = maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterDeptDesc'].value;      
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.exeDeptId'].value        = maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterExeDeptId'].value;        
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.exeDeptDesc'].value      = maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterExeDeptDesc'].value;      
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.eqLocId'].value          = maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterEqLocId'].value;       
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.eqLocDesc'].value        = maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterEqLocDesc'].value;     
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.eqCtgId'].value          = maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterEqCtgId'].value;       
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.eqCtgDesc'].value        = maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterEqCtgDesc'].value;     
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.eqCtgTypeId'].value      = maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterEqCtgTypeId'].value;   
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.eqCtgTypeDesc'].value    = maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterEqCtgTypeDesc'].value; 
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.mainMngId'].value        = maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterMainMngId'].value;     
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.mainMngName'].value      = maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterMainMngName'].value;   
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.subMngId'].value         = maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterSubMngId'].value;      
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.subMngName'].value       = maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterSubMngName'].value;    
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.plfTypeId'].value        = maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterPlfTypeId'].value;     
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.plfTypeDesc'].value      = maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterPlfTypeDesc'].value;   
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.woTypeId'].value         = maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterWoTypeId'].value;      
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.woTypeDesc'].value       = maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterWoTypeDesc'].value;    
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.pmTypeId'].value         = maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterPmTypeId'].value;      
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.pmTypeDesc'].value       = maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterPmTypeDesc'].value;    
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.wkCtrId'].value          = maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterWkCtrId'].value;       
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.wkCtrDesc'].value        = maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterWkCtrDesc'].value;     
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.equipId'].value          = maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterEquipId'].value;       
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.equipDesc'].value        = maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterEquipDesc'].value;     
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.isLawEq'].value          = maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterIsLawEq'].value;       
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.pmNo'].value             = maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterPmNo'].value;          
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.plantId'].value          = maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterPlantId'].value;       
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.plantDesc'].value        = maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterPlantDesc'].value;     
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.equipNo'].value          = maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterEquipNo'].value;       
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.eqUsaDeptId'].value      = maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterEqUsaDeptId'].value;   
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.eqUsaDeptDesc'].value    = maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterEqUsaDeptDesc'].value; 
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.pequipId'].value     	= maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterPequipId'].value;      
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.pequipDesc'].value   	= maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterPequipDesc'].value;    
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.peqUsaDeptId'].value     = maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterPEqUsaDeptId'].value;  
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.peqUsaDeptDesc'].value   = maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterPEqUsaDeptDesc'].value;
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.roomNo'].value     		= maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterRoomNumber'].value;    
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.calTypeId'].value     	= maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterCalTypeId'].value;     
    maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.calTypeDesc'].value     	= maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.filterCalTypeDesc'].value;   
	
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
	var pmStatus = getValueById(myGrid, selectedId, "pmStatusCode");
	var wkOrId = getValueById(myGrid, selectedId, "wkorId");
	
	if(pmStatus == "P")
	{
		maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.pmSchedId'].value =  getValueById(myGrid, selectedId,'PMSCHEDID'); 
		goCommonTabPage(maWoYearSchedListForm, <%= MaWoYearSchedDetailAction.YEAR_SCHED_DETAIL_INIT %>, pageId, beforePageId);
	}
	else
	{
		maWoYearSchedListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value = wkOrId;
		goCommonTabPage(maWoYearSchedListForm, <%= MaWoResultMstrDetailAction.WO_RESULT_DETAIL_INIT %>, pageId, beforePageId);
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
		goTabPage('maWoYearSchedDetail');
	}
	else
	{
		var wkOrId = getValueById(myGrid, rowId, "wkorId");
		var pmType = getValueById(myGrid, rowId, "PMTYPE");
		var woparam = getValueById(myGrid, rowId, "WOPARAM");
		pmType = pmType.substring(0,1)+pmType.substring(1,pmType.length).toLowerCase();

		maWoYearSchedListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value = wkOrId;
		maWoYearSchedListForm.elements['maWoResultMstrCommonDTO.selectedWoType'].value = "PMC";

		console.log(woparam);
		goTabPage(woparam);
	}
}


/**
 * 엑셀 다운.
 */
 function goExcel()
 {
 	maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.pmSchedId'].value = "";	// 검색시 Tab 이동Key Clear
	maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.wkOrId'].value = "";
 	excelServerAction("maWoYearSchedList", maWoYearSchedListForm );  
 } 
 
/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maWoYearSchedDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maWoYearSchedListForm.elements['maWoYearSchedCommonDTO.pmSchedId'].value = "";
	goCommonTabPage(maWoYearSchedListForm, '', pageId);	
}

/**
 * 삭제
 */
 function goDelete(){
	//myGrid에 1(2번째)번에 체크가 되어있으면 2번 데이터 가져오기
	 var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'PMSCHEDID'); //Grid, check box column seq, pk column seq
	//체크된게 없으면 return
	 if(typeof delArray == "undefined"){
			alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
			return;
		}
	
  	maWoYearSchedListForm.strutsAction.value = '<%=MaWoYearSchedListAction.YEAR_SCHED_LIST_DELETE%>';
  	var url = contextPath + "/maWoYearSchedList.do";
  	
  	$.post(url,FormQueryString(maWoYearSchedListForm)+delArray , function(_data){
    	afterDelete();
    });
  }

function afterDelete(){
	goClose('maWoYearSchedDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
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
			maWoYearSchedListForm.strutsAction.value = '<%= MaWoYearSchedListAction.YEAR_SCHED_DAILY_SCHEDULED%>';
		 	var url = contextPath + "/maWoYearSchedList.do";
		 	
		 	ajaxPost(url,FormQueryString(maWoYearSchedListForm)+fixArray)
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
<html:form action="/maWoYearSchedList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maWoYearSchedCommonDTO.pmSchedId"/><!-- Key -->
<html:hidden property="maWoYearSchedCommonDTO.filterDeptId"/>
<html:hidden property="maWoYearSchedCommonDTO.filterExeDeptId"/>
<html:hidden property="maWoYearSchedCommonDTO.yyyymm"/>
<html:hidden property="maWoYearSchedCommonDTO.schedType"/>
<html:hidden property="maWoYearSchedCommonDTO.deptId"/>
<html:hidden property="maWoYearSchedCommonDTO.deptDesc"/>
<html:hidden property="maWoYearSchedCommonDTO.exeDeptId"/>
<html:hidden property="maWoYearSchedCommonDTO.exeDeptDesc"/>
<html:hidden property="maWoYearSchedCommonDTO.eqLocId"/>
<html:hidden property="maWoYearSchedCommonDTO.eqLocDesc"/>
<html:hidden property="maWoYearSchedCommonDTO.eqCtgId"/>
<html:hidden property="maWoYearSchedCommonDTO.eqCtgDesc"/>
<html:hidden property="maWoYearSchedCommonDTO.eqCtgTypeId"/>
<html:hidden property="maWoYearSchedCommonDTO.eqCtgTypeDesc"/>
<html:hidden property="maWoYearSchedCommonDTO.mainMngId"/>
<html:hidden property="maWoYearSchedCommonDTO.mainMngName"/>
<html:hidden property="maWoYearSchedCommonDTO.subMngId"/>
<html:hidden property="maWoYearSchedCommonDTO.subMngName"/>
<html:hidden property="maWoYearSchedCommonDTO.plfTypeId"/>
<html:hidden property="maWoYearSchedCommonDTO.plfTypeDesc"/>
<html:hidden property="maWoYearSchedCommonDTO.woTypeId"/>
<html:hidden property="maWoYearSchedCommonDTO.woTypeDesc"/>
<html:hidden property="maWoYearSchedCommonDTO.pmTypeId"/>
<html:hidden property="maWoYearSchedCommonDTO.pmTypeDesc"/>
<html:hidden property="maWoYearSchedCommonDTO.wkCtrId"/>
<html:hidden property="maWoYearSchedCommonDTO.wkCtrDesc"/>
<html:hidden property="maWoYearSchedCommonDTO.isLawEq"/>
<html:hidden property="maWoYearSchedCommonDTO.equipId"/>
<html:hidden property="maWoYearSchedCommonDTO.equipNo"/>
<html:hidden property="maWoYearSchedCommonDTO.equipDesc"/>
<html:hidden property="maWoYearSchedCommonDTO.plantId"/>
<html:hidden property="maWoYearSchedCommonDTO.plantDesc"/>
<html:hidden property="maWoYearSchedCommonDTO.pmNo"/>
<html:hidden property="maWoYearSchedCommonDTO.filterEqLocId"/>
<html:hidden property="maWoYearSchedCommonDTO.filterEqCtgId"/>
<html:hidden property="maWoYearSchedCommonDTO.filterEqCtgTypeId"/>
<html:hidden property="maWoYearSchedCommonDTO.filterPlfTypeId"/>
<html:hidden property="maWoYearSchedCommonDTO.filterPlfTypeDesc"/>
<html:hidden property="maWoYearSchedCommonDTO.filterWoTypeId"/>
<html:hidden property="maWoYearSchedCommonDTO.filterWoTypeDesc"/>
<html:hidden property="maWoYearSchedCommonDTO.filterPmTypeId"/>
<html:hidden property="maWoYearSchedCommonDTO.filterWkCtrId"/>
<html:hidden property="maWoYearSchedCommonDTO.filterMainMngId"/>
<html:hidden property="maWoYearSchedCommonDTO.filterSubMngId"/>
<html:hidden property="maWoYearSchedCommonDTO.filterEquipId"/>
<html:hidden property="maWoYearSchedCommonDTO.filterPlantId"/>
<html:hidden property="maWoYearSchedCommonDTO.filterCalTypeId"/>
<html:hidden property="maWoYearSchedCommonDTO.filterEqUsaDeptId"/>
<html:hidden property="maWoYearSchedCommonDTO.filterPEqUsaDeptId"/>
<html:hidden property="maWoYearSchedCommonDTO.filterPequipId"/>
<html:hidden property="maWoYearSchedCommonDTO.eqUsaDeptId"/>
<html:hidden property="maWoYearSchedCommonDTO.eqUsaDeptDesc"/>
<html:hidden property="maWoYearSchedCommonDTO.peqUsaDeptId"/>
<html:hidden property="maWoYearSchedCommonDTO.peqUsaDeptDesc"/>
<html:hidden property="maWoYearSchedCommonDTO.pequipId"/>
<html:hidden property="maWoYearSchedCommonDTO.pequipDesc"/>
<html:hidden property="maWoYearSchedCommonDTO.roomNo"/>
<html:hidden property="maWoYearSchedCommonDTO.calTypeId"/>
<html:hidden property="maWoYearSchedCommonDTO.calTypeDesc"/>
<html:hidden property="maWoYearSchedCommonDTO.wkOrId"/>

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
					<label><bean:message key="LABEL.year"/></label>
					<div class="input_read">
						<html:text property="maWoYearSchedCommonDTO.filterYear" readonly="true"/>
						<p class="open_year_calendar"><span>날짜</span></p>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.pmNo"/></label>
					<div class="input_box">
						<html:text property="maWoYearSchedCommonDTO.filterPmNo" tabindex="10"/>
					</div>
				</div>
				<!-- 계획부서 -->
				<div class="field">
					<label><bean:message key="LABEL.planDept"/></label>
					<div class="input_box">
						<html:text property="maWoYearSchedCommonDTO.filterDeptDesc" tabindex="20" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 실행부서 -->
				<div class="field">
					<label><bean:message key="LABEL.exeDept"/></label>
					<div class="input_box">
						<html:text property="maWoYearSchedCommonDTO.filterExeDeptDesc" tabindex="21" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 계측기번호 -->
				<div class="field">
					<label><bean:message key="LABEL.toolNo"/></label>
					<div class="input_box">
						<html:text property="maWoYearSchedCommonDTO.filterEquipNo" tabindex="23" />
					</div>
				</div>
				<!-- 계측기명 -->
				<div class="field">
					<label><bean:message key="LABEL.toolName"/></label>
					<div class="input_box">
						<html:text property="maWoYearSchedCommonDTO.filterEquipDesc" tabindex="25" />
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
						<html:text property="maWoYearSchedCommonDTO.filterEqLocDesc" tabindex="30" />
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
						<html:text property="maWoYearSchedCommonDTO.filterEqCtgDesc" tabindex="35" />
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
						<html:text property="maWoYearSchedCommonDTO.filterIsLawEq" tabindex="60" />
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
						<html:text property="maWoYearSchedCommonDTO.filterMainMngName" tabindex="70" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 관리자(부) -->
				<div class="field hidden">
					<label><bean:message key="LABEL.subManager"/></label>
					<div class="input_box">
						<html:text property="maWoYearSchedCommonDTO.filterSubMngName" tabindex="90" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 작업형태  -->
				<div class="field">
					<label><bean:message key="LABEL.pmType"/></label>
					<div class="input_box">
						<html:text property="maWoYearSchedCommonDTO.filterPmTypeDesc" tabindex="130" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>  
				<!-- 작업그룹  -->
                <div class="field">
                     <label><bean:message key="LABEL.wkCtr"/></label>
                     <div class="input_box">
                        <html:text property="maWoYearSchedCommonDTO.filterWkCtrDesc" tabindex="140" />
                         <p class="open_spop">
                             <a>
                                 <span>조회</span>
                             </a>
                         </p>
                   </div>
                </div>
				<!-- 설비유형  -->
				<div class="field hidden">
					<label><bean:message key="LABEL.eqCtgType"/></label>
					<div class="input_box">
						<html:text property="maWoYearSchedCommonDTO.filterEqCtgTypeDesc" tabindex="150" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
							<html:text property="maWoYearSchedCommonDTO.filterPlantDesc" tabindex="160" />
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
							<html:text property="maWoYearSchedCommonDTO.filterEqUsaDeptDesc" tabindex="170" />
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
							<html:text property="maWoYearSchedCommonDTO.filterPequipDesc" tabindex="180" />
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
							<html:text property="maWoYearSchedCommonDTO.filterPEqUsaDeptDesc" tabindex="190" />
							<p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 교정사유  -->
                <div class="field">
                    <label><bean:message key="LABEL.pUsageDept"/></label>
                    <div class="input_box">
							<html:text property="maWoYearSchedCommonDTO.filterCalTypeDesc" tabindex="190" />
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
						<html:text property="maWoYearSchedCommonDTO.filterRoomNumber" tabindex="23" />
					</div>
				</div>
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
			<div class="function_box">
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
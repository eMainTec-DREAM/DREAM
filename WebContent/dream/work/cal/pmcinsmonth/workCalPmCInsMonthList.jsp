<%--===========================================================================
월간작업일정 - 목록
author  kim21017
version $Id: workCalPmCInsMonthList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.cal.pmcinsmonth.action.WorkCalPmCInsMonthListAction" %>
<%@ page import="dream.work.pm.list.action.WorkPmiCInsDetailAction" %>
<%@ page import="dream.work.list.action.WorkListCinsPlanMstrListAction" %>
<%@ page import="dream.work.list.action.WorkListCinsPlanMstrDetailAction" %>
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
<!-- 파트체인지점검(월간) -->
<title><bean:message key='MENU.WORKCALPMCINSMONTH'/></title>
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
var deptAc, equipDescAc, eqLocDescAc, empAc;
function loadPage()
{
	//년월
    workCalPmCInsMonthListForm.elements['workCalPmCInsMonthCommonDTO.filterYyyymm'].value = dateToData(getToday()).substr(0, 4)+"-"+dateToData(getToday()).substr(4, 2);

	//부서
    deptAc = new autoC({"workCalPmCInsMonthCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcDisplay("DESCRIPTION");
    deptAc.setAcConditionMap({
        "comp_no":loginUser.compNo
       });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "workCalPmCInsMonthCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.init();

    //설비
    equipDescAc = new autoC({"workCalPmCInsMonthCommonDTO.filterEquipDesc":"description"});
    equipDescAc.setAcConditionMap({
       "comp_no":loginUser.compNo
       });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "workCalPmCInsMonthCommonDTO.filterEqLocId",
    	"dept_id" : "workCalPmCInsMonthCommonDTO.filterDeptId"
    });
    equipDescAc.init();

    //설비위치
    eqLocDescAc = new autoC({"workCalPmCInsMonthCommonDTO.filterEqLocDesc":"full_desc"});
    eqLocDescAc.setAcConditionMap({
       "comp_no":loginUser.compNo
       });
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcResultMap({
        "workCalPmCInsMonthCommonDTO.filterEqLocId":"eqloc_id"
    });
    eqLocDescAc.init();

    //담당자
    empAc = new autoC({"workCalPmCInsMonthCommonDTO.filterEmpDesc":"emp_name"});
    empAc.setTable("TAEMP");
    empAc.setAcResultMap({
        "workCalPmCInsMonthCommonDTO.filterEmpId":"emp_id"
      , "IS_USE":"Y"
    });
    empAc.setAcDConditionMap({
    	"dept_id" : "workCalPmCInsMonthCommonDTO.filterDeptId"
    });
    empAc.init();
	
	
    initGrid();

	hideBtn("SAVE");
	hideBtn("EDITCNCL");
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
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
    	lv_selectedId = rowId;
		if(!isEdit)
		{		
			goOpen(rowId);
		}
	}); 
	myGrid.attachEvent("onBeforeSelect",function(rowId, state){

		lv_selectedId = rowId;
		//var status 		= getValueById(myGrid, rowId, "WOSTATUS");
		var pmStatus 	= getValueById(myGrid, rowId, "PMSTATUSCODE");
		var isActive 	= getValueById(myGrid, rowId, "ISACTIVE");
	    var index 		= getIndexById(myGrid, "SCHEDDATE");
	    
	    if(isEdit)
		{
			if(pmStatus == "C" || pmStatus == "S") //완료되거나 스케줄 확정되면 스케줄 변화안됨.
			{
				if(isActive == "Y")
				{
					 //disable
					 myGrid.cells(rowId,index).setDisabled(true);
					// return false;
				}
				else
				{
					myGrid.cells(rowId,index).setDisabled(false);
				}
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
	//시간 숨기기
	myGrid.attachEvent("onCalendarShow", function(myCal,rId,colInd){
		myCal.hideTime();
		//setCalendar(myCal, rId, colInd);
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		workCalPmCInsMonthListForm.elements['workCalPmCInsMonthCommonDTO.pmInsDSchedId'].value = "";
        return sortColumn("workCalPmCInsMonthList", this, workCalPmCInsMonthListForm, "pmInsDSchedId", ind, direction);
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
	hideBtn("OPEN");
	hideBtn("CREATE");
	hideBtn("DELETE");
	hideBtn("EXCEL");
	hideBtn("SETTING");

    //Close Detail Page if it is open
    goClose(beforePageId, this);
    
    showBtn("SAVE");
    hideBtn("EDIT");
    showBtn("EDITCNCL");
	
    //Set Grid as updatable
	var url = contextPath + "/workCalPmCInsMonthList.do";
	var stAct = "<%=WorkCalPmCInsMonthListAction.MONTH_SCHED_CHANGE %>";

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
	
	showBtn("PMSTD");
	showBtn("RESULT");
	
	showBtn("FIX");
	showBtn("OPEN");
	showBtn("CREATE");
	showBtn("DELETE");
	showBtn("EXCEL");
	showBtn("SETTING");
	
	workCalPmCInsMonthListForm.elements['workCalPmCInsMonthCommonDTO.pmInsDSchedId'].value = "";	// 검색시 Tab 이동Key Clear
	
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
    var url = contextPath + "/workCalPmCInsMonthList.do";
    workCalPmCInsMonthListForm.elements['strutsAction'].value = '<%=WorkCalPmCInsMonthListAction.MONTH_SCHED_LIST_FIND%>';
    workCalPmCInsMonthListForm.elements['workCalPmCInsMonthCommonDTO.yyyymmdd'].value     = yyyymmdd;
    workCalPmCInsMonthListForm.elements['workCalPmCInsMonthCommonDTO.schedType'].value    = type;
    
    doGridAction("Search", myGrid, "gridbox", url, FormQueryString(workCalPmCInsMonthListForm));
}

/**
 * 저장후 리스트만 재조회
 */
 function searchList(){
	var url = contextPath + "/workCalPmCInsMonthList.do";
    workCalPmCInsMonthListForm.elements['strutsAction'].value = '<%=WorkCalPmCInsMonthListAction.MONTH_SCHED_LIST_FIND%>';

    myGrid.clearAll(); setLoading("gridbox");
    $.post(url,FormQueryString(workCalPmCInsMonthListForm), function(_data){
    	myGrid.parse(_data,"js");
    });
}
/**
 * 월간작업일정 조회
 */
function findSchedList(){
	var url = contextPath + "/workCalPmCInsMonthList.do";
	workCalPmCInsMonthListForm.elements['strutsAction'].value = '<%=WorkCalPmCInsMonthListAction.MONTH_SCHED_FIND%>';
	setModal();
	$.post(url,FormQueryString(workCalPmCInsMonthListForm), function(_data){
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
	document.getElementById("month_title").innerText = workCalPmCInsMonthListForm.elements['workCalPmCInsMonthCommonDTO.yyyymmdd'].value;
	
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
  * 수정된 그리드 1건을 다시 조회한다.
  */
function findGridRow(_pmInsDSchedId)
{
	workCalPmCInsMonthListForm.elements['workCalPmCInsMonthCommonDTO.pmInsDSchedId'].value = _pmInsDSchedId;
	
	if(parent.currentPageId=='workCalPmCInsMonthList')
	{
		searchList();
		goSearch();
	}
	else
	{
	 	findSchedRow('ReloadRow');
	}

    workCalPmCInsMonthListForm.elements['workCalPmCInsMonthCommonDTO.pmInsDSchedId'].value = "";
}
 
function findSchedRow(sheetAction) {
	var url = contextPath + "/workCalPmCInsMonthList.do";
    workCalPmCInsMonthListForm.elements['strutsAction'].value = '<%=WorkCalPmCInsMonthListAction.MONTH_SCHED_LIST_FIND%>';
    
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workCalPmCInsMonthListForm));
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	workCalPmCInsMonthListForm.elements['workCalPmCInsMonthCommonDTO.pmInsDSchedId'].value = "";	// 검색시 Tab 이동Key Clear
    workCalPmCInsMonthListForm.elements['workCalPmCInsMonthCommonDTO.schedType'].value = "";
	
    workCalPmCInsMonthListForm.elements['workCalPmCInsMonthCommonDTO.yyyymmdd'].value     = workCalPmCInsMonthListForm.elements['workCalPmCInsMonthCommonDTO.filterYyyymm'].value;
    workCalPmCInsMonthListForm.elements['workCalPmCInsMonthCommonDTO.deptId'].value       = workCalPmCInsMonthListForm.elements['workCalPmCInsMonthCommonDTO.filterDeptId'].value;   
    workCalPmCInsMonthListForm.elements['workCalPmCInsMonthCommonDTO.deptDesc'].value     = workCalPmCInsMonthListForm.elements['workCalPmCInsMonthCommonDTO.filterDeptDesc'].value; 
    workCalPmCInsMonthListForm.elements['workCalPmCInsMonthCommonDTO.eqLocId'].value      = workCalPmCInsMonthListForm.elements['workCalPmCInsMonthCommonDTO.filterEqLocId'].value;  
    workCalPmCInsMonthListForm.elements['workCalPmCInsMonthCommonDTO.eqLocDesc'].value    = workCalPmCInsMonthListForm.elements['workCalPmCInsMonthCommonDTO.filterEqLocDesc'].value;
    workCalPmCInsMonthListForm.elements['workCalPmCInsMonthCommonDTO.equipId'].value      = workCalPmCInsMonthListForm.elements['workCalPmCInsMonthCommonDTO.filterEquipId'].value;  
    workCalPmCInsMonthListForm.elements['workCalPmCInsMonthCommonDTO.equipDesc'].value    = workCalPmCInsMonthListForm.elements['workCalPmCInsMonthCommonDTO.filterEquipDesc'].value;
    workCalPmCInsMonthListForm.elements['workCalPmCInsMonthCommonDTO.pmNo'].value         = workCalPmCInsMonthListForm.elements['workCalPmCInsMonthCommonDTO.filterPmNo'].value;     
    workCalPmCInsMonthListForm.elements['workCalPmCInsMonthCommonDTO.empId'].value        = workCalPmCInsMonthListForm.elements['workCalPmCInsMonthCommonDTO.filterEmpId'].value;    
    workCalPmCInsMonthListForm.elements['workCalPmCInsMonthCommonDTO.empDesc'].value      = workCalPmCInsMonthListForm.elements['workCalPmCInsMonthCommonDTO.filterEmpDesc'].value;  
    
	findSchedList();
}

/**
 * 상세 열기
 */
function goOpen(rowId)
{
	var pmStatus = getValueById(myGrid, rowId, "PMSTATUSCODE");
	var pmInsDListId = getValueById(myGrid, rowId, "pmInsDListId");
    
	if(pmInsDListId == "undefined" || pmInsDListId == "" || pmStatus == "P")
	{
		workCalPmCInsMonthListForm.elements['workCalPmCInsMonthCommonDTO.pmInsDSchedId'].value =  getValueById(myGrid, rowId,'PMINSDSCHEDID');
		   
	    workCalPmCInsMonthListForm.elements['workListCinsPlanMstrCommonDTO.pmInsDSchedId'].value = workCalPmCInsMonthListForm.elements['workCalPmCInsMonthCommonDTO.pmInsDSchedId'].value;
	    
	    goTabPage('workListCinsPlanMstrDetail');
	}
	else if(pmStatus == "S" || pmStatus == "C")
	{
		var pmType = "CINS";
	    var woparam = "workPmiCInsDetail";
	    pmType = pmType.substring(0,1)+pmType.substring(1,pmType.length).toLowerCase();
	    
	    workCalPmCInsMonthListForm.elements['workPmiCInsCommonDTO.pmInsDListId'].value = pmInsDListId;

	    goTabPage(woparam);
	}

}
function goOpenAction()
{
    var lv_selectedId=myGrid.getSelectedRowId();
    if(lv_selectedId == null) return;
    
    var pmStatus = getValueById(myGrid, lv_selectedId, "PMSTATUSCODE");
	var pmInsDListId = getValueById(myGrid, lv_selectedId, "pmInsDListId");
    
	if(pmInsDListId == "undefined" || pmInsDListId == "" || pmStatus == "P")
	{
	    workCalPmCInsMonthListForm.elements['workCalPmCInsMonthCommonDTO.pmInsDSchedId'].value =  getValueById(myGrid, lv_selectedId,'PMINSDSCHEDID');
	    workCalPmCInsMonthListForm.elements['strutsAction'].value = '<%=WorkListCinsPlanMstrDetailAction.DETAIL_INIT%>';
	    openQuickTabPage(FormQueryString(workCalPmCInsMonthListForm), 'workListCinsPlanMstrDetail'); 
	}
	else if(pmStatus == "S" || pmStatus == "C")
	{
		workCalPmCInsMonthListForm.elements['workPmiCInsCommonDTO.pmInsDListId'].value =  pmInsDListId;
		workCalPmCInsMonthListForm.elements['strutsAction'].value = '<%= WorkPmiCInsDetailAction.DETAIL_INIT %>';
	    openQuickTabPage(FormQueryString(workCalPmCInsMonthListForm), 'workPmiCInsDetail'); 
	}
    
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
	var form = document.workCalPmCInsMonthListForm;
	
	var pmStatus = getValueById(myGrid, lv_selectedId, "PMSTATUSCODE");
	var pmInsDListId = getValueById(myGrid, lv_selectedId, "pmInsDListId");
    
	if(pmInsDListId == "undefined" || pmInsDListId == "" || pmStatus == "P")
	{
		workCalPmCInsMonthListForm.elements['workCalPmCInsMonthCommonDTO.pmInsDSchedId'].value =  getValueById(myGrid, lv_selectedId,'PMINSDSCHEDID');
	    goCommonTabPage(workCalPmCInsMonthListForm, <%= WorkListCinsPlanMstrDetailAction.DETAIL_INIT %>, pageId, beforePageId);
	}
	else if(pmStatus == "S" || pmStatus == "C")
	{
		workCalPmCInsMonthListForm.elements['workPmiCInsCommonDTO.pmInsDListId'].value =  pmInsDListId;
	    goCommonTabPage(workCalPmCInsMonthListForm, <%= WorkPmiCInsDetailAction.DETAIL_INIT %>, pageId, beforePageId);
	}

	beforePageId = pageId;
}


function goPmstd()
{
	if(lv_selectedId == "undefined" || lv_selectedId == "") return;

	//strutsAction[0]=1001
	//workCalPmCInsMonthCommonDTO.pmId[0]=4522
	var fileName = "workListCinsResultMstrDetail";
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

    //var param = "strutsAction=1001&workCalPmCInsMonthCommonDTO.pmId="+ pmId;
    var param = "strutsAction=8001&maPmMstrCommonDTO.pmId="+ pmId;

    openWindowWithPost(url, "PM_DETAIL", param, pos);
}

/**
 *   점검결과 열기
 */
function goResult(){
	
    if(lv_selectedId == "undefined" || lv_selectedId == "") return;
    var pmStatus = getValueById(myGrid, lv_selectedId, "pmStatusCode");
    var pmInsDListId = getValueById(myGrid, lv_selectedId, "pmInsDListId");
    
    if(pmInsDListId == "undefined" || pmInsDListId == "")
    {
    	alertMessage1('<bean:message key="MESSAGE.MSG0024"/>');
    	return;
    }
    
    //var pmType = getValueById(myGrid, lv_selectedId, "PMTYPE");
    var pmType = "CINS";
    //var woparam = getValueById(myGrid, lv_selectedId, "PARAM");
    var woparam = "workPmiCInsDetail";
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
        
        var param = "strutsAction=1001&workPmiCInsCommonDTO.pmInsDListId="+ pmInsDListId;
      
        openWindowWithPost(url, "WO_DETAIL", param, pos);
    }else{
        alertMessage1('<bean:message key="MESSAGE.MSG0024"/>');
    }
}


/**
 * 엑셀 다운.
 */
function goExcel()
{
	excelServerAction("workCalPmCInsMonthList", workCalPmCInsMonthListForm);  
}

/**
 * 생성
 */
function goCreate()
{
	//createValidationCheck(myGrid, "workCalPmCInsMonthDetail" , "goCreateAction");
	createValidationCheck(myGrid, "workListCinsPlanMstrDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	workCalPmCInsMonthListForm.elements['workCalPmCInsMonthCommonDTO.pmInsDSchedId'].value = "";
	goCommonTabPage(workCalPmCInsMonthListForm, '', pageId);
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

  	workCalPmCInsMonthListForm.strutsAction.value = '<%=WorkCalPmCInsMonthListAction.MONTH_SCHED_LIST_DELETE%>';
  	var url = contextPath + "/workCalPmCInsMonthList.do";

  	$.post(url,FormQueryString(workCalPmCInsMonthListForm)+delArray , function(_data){
    	afterDelete();
    });
  }

function afterDelete(){
	//goClose('workListCinsPlanMstrDetail');
	goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
  }
  
/**
 * 일별 확정
 */
function goFix(){
	//myGrid에 1(2번째)번에 체크가 되어있으면 2번 데이터 가져오기
	 var fixArray = getSelectRows(myGrid, 'ISDELCHECK', 'PMINSDSCHEDID'); //Grid, check box column seq, pk column seq

	//체크된게 없으면 return
	if(typeof fixArray == "undefined")
	{
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	dhtmlx.confirm('<bean:message key="MESSAGE.MSG045"/>', function(result){
		if(result){
			workCalPmCInsMonthListForm.strutsAction.value = '<%=WorkCalPmCInsMonthListAction.MONTH_SCHED_DAILY_SCHEDULED%>';
		 	var url = contextPath + "/workCalPmCInsMonthList.do";

		 	ajaxPost(url,FormQueryString(workCalPmCInsMonthListForm)+fixArray)
			.done(function(d){
				searchList();
				goSearch();
			});
		}
	});
}

 /**
  * 월별 확정
  */
function goFixMonthly(){

	dhtmlx.confirm('<bean:message key="MESSAGE.MSG045"/>', function(result){
		if(result){
			setModal('<bean:message key="MESSAGE.MSG0083"/>');
			workCalPmCInsMonthListForm.strutsAction.value = '<%=WorkCalPmCInsMonthListAction.MONTH_SCHED_MONTHLY_SCHEDULED%>';
		  	var url = contextPath + "/workCalPmCInsMonthList.do";

		  	ajaxPost(url,FormQueryString(workCalPmCInsMonthListForm))
			.done(function(d){
				goClose('workCalPmCInsMonthDetail');
				goSearch();
			});
		}
	});
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/workCalPmCInsMonthList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="workCalPmCInsMonthCommonDTO.pmInsDSchedId"/><!-- Key -->
<html:hidden property="workCalPmCInsMonthCommonDTO.yyyymmdd"/>
<html:hidden property="workCalPmCInsMonthCommonDTO.schedType"/>

<html:hidden property="workListCinsPlanMstrCommonDTO.pmInsDSchedId"/>

<html:hidden property="workCalPmCInsMonthCommonDTO.filterEmpId"/>
<html:hidden property="workCalPmCInsMonthCommonDTO.filterDeptId"/>
<html:hidden property="workCalPmCInsMonthCommonDTO.filterEquipId"/>
<html:hidden property="workCalPmCInsMonthCommonDTO.filterEqLocId"/>

<html:hidden property="workCalPmCInsMonthCommonDTO.empId"/>
<html:hidden property="workCalPmCInsMonthCommonDTO.empDesc"/>
<html:hidden property="workCalPmCInsMonthCommonDTO.deptId"/>
<html:hidden property="workCalPmCInsMonthCommonDTO.deptDesc"/>
<html:hidden property="workCalPmCInsMonthCommonDTO.equipId"/>
<html:hidden property="workCalPmCInsMonthCommonDTO.equipDesc"/>
<html:hidden property="workCalPmCInsMonthCommonDTO.eqLocId"/>
<html:hidden property="workCalPmCInsMonthCommonDTO.eqLocDesc"/>
<html:hidden property="workCalPmCInsMonthCommonDTO.pmNo"/>

<html:hidden property="workPmiCInsCommonDTO.pmInsDListId"/>

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
			    <!-- 년월 -->
				<div class="field">
					<label><bean:message key="LABEL.yyyymm"/></label>
					<div class="input_read">
						<html:text property="workCalPmCInsMonthCommonDTO.filterYyyymm" readonly="true"/>
						<p class="open_mon_calendar"><span>날짜</span></p>
					</div>
				</div>
				<!-- 예방작업번호 -->
				<div class="field">
                    <label><bean:message key="LABEL.pmNo"/></label>
                    <div class="input_box">
                        <html:text property="workCalPmCInsMonthCommonDTO.filterPmNo" tabindex="10"/>
                    </div>
                </div>
                <!-- 관리부서 -->
                <div class="field">
                    <label><bean:message key="LABEL.dept"/></label>
                    <div class="input_box">
                        <html:text property="workCalPmCInsMonthCommonDTO.filterDeptDesc" tabindex="20" />
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                <!-- 담당자 -->
                <div class="field">
                    <label><bean:message key="LABEL.manager"/></label>
                    <div class="input_box">
                        <html:text property="workCalPmCInsMonthCommonDTO.filterEmpDesc" tabindex="22" />
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                <!-- 설비 -->
                <div class="field">
                    <label><bean:message key="LABEL.equipDesc"/></label>
                    <div class="input_box">
                        <html:text property="workCalPmCInsMonthCommonDTO.filterEquipDesc" tabindex="25" />
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
                        <html:text property="workCalPmCInsMonthCommonDTO.filterEqLocDesc" tabindex="30" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
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
							<div class="legend_tx"><span class="p"><strong>Black</strong></span> : 생산계획 &nbsp;&nbsp;&nbsp;<span class="s"><strong>Red</strong></span> : 일정생성 &nbsp;&nbsp;&nbsp;<span class="c"><strong>Blue</strong></span> : 점검완료</div>
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
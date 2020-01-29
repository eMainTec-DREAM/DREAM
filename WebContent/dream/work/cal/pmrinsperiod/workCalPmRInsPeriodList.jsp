<%--===========================================================================
예방작업일정(기간) - 목록
author  kim21017
version $Id: workCalPmRInsPeriodList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.cal.pmrinsperiod.action.WorkCalPmRInsPeriodListAction" %>
<%@ page import="dream.work.cal.pmrinsperiod.action.WorkCalPmRInsPeriodDetailAction" %>
<%@ page import="dream.work.pmi.list.action.WorkPmiDetailAction"%>
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
<!-- 예방작업일정(기간) -->
<title><bean:message key='MENU.WORKCALPMRINSPERIOD'/></title>
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
var eqCtgTypeAc;
var mainMngAc;
var wkCtrDescAc;
var subMngAc;
var empAc;
var plantAc;
var usageDeptAc;

function loadPage()
{
	if(window.name=="PMSCHED_LIST_POPUP" || window.name == "LINKED_POPUP"){

	}else{
		//부서
		workCalPmRInsPeriodListForm.elements['workCalPmRInsPeriodCommonDTO.filterDeptId'].value    = loginUser.filterDeptId;
		workCalPmRInsPeriodListForm.elements['workCalPmRInsPeriodCommonDTO.filterDeptDesc'].value  = loginUser.filterDeptDesc;
		workCalPmRInsPeriodListForm.elements['workCalPmRInsPeriodCommonDTO.filterStartDate'].value   = getMinusDay(7);
		workCalPmRInsPeriodListForm.elements['workCalPmRInsPeriodCommonDTO.filterEndDate'].value   = getToday();
		//년월
	    //workCalPmRInsPeriodListForm.elements['workCalPmRInsPeriodCommonDTO.filterYyyymm'].value = dateToData(getToday()).substr(0, 4)+"-"+dateToData(getToday()).substr(4, 2);

		if(loginUser.eqLocId!='null'){
			workCalPmRInsPeriodListForm.elements['workCalPmRInsPeriodCommonDTO.filterEqLocId'].value = loginUser.eqLocId;
			workCalPmRInsPeriodListForm.elements['workCalPmRInsPeriodCommonDTO.filterEqLocDesc'].value = loginUser.eqLocDesc;
		}
/* 	    if(loginUser.wkctrId!='null'){
	    	workCalPmRInsPeriodListForm.elements['workCalPmRInsPeriodCommonDTO.filterWkCtrId'].value = loginUser.wkctrId;
	    	workCalPmRInsPeriodListForm.elements['workCalPmRInsPeriodCommonDTO.filterWkCtrDesc'].value = loginUser.wkctrDesc;
		} */
	    if(loginUser.filterWkCtrId!='null'){
	    	workCalPmRInsPeriodListForm.elements['workCalPmRInsPeriodCommonDTO.filterWkCtrId'].value = loginUser.filterWkCtrId;
	    	workCalPmRInsPeriodListForm.elements['workCalPmRInsPeriodCommonDTO.filterWkCtrDesc'].value = loginUser.filterWkCtrDesc;
		}
	    if(loginUser.eqCtgTypeId!='null'){
	    	workCalPmRInsPeriodListForm.elements['workCalPmRInsPeriodCommonDTO.filterEqCtgTypeId'].value = loginUser.eqCtgTypeId;
	    	workCalPmRInsPeriodListForm.elements['workCalPmRInsPeriodCommonDTO.filterEqCtgTypeDesc'].value = loginUser.eqCtgTypeDesc;
		}
	  	//공장명
	    if(loginUser.filterPlant!='null'){
	    	workCalPmRInsPeriodListForm.elements['workCalPmRInsPeriodCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
	    	workCalPmRInsPeriodListForm.elements['workCalPmRInsPeriodCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	    }
	}

    initGrid();

	hideBtn("SAVE");
	hideBtn("EDITCNCL");

    deptAc = new autoC({"workCalPmRInsPeriodCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcDisplay("DESCRIPTION");
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "workCalPmRInsPeriodCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "workCalPmRInsPeriodCommonDTO.filterPlantId"
    	,"plantDesc" : "workCalPmRInsPeriodCommonDTO.filterPlantDesc"
    });
    deptAc.init();

    equipDescAc = new autoC({"workCalPmRInsPeriodCommonDTO.filterEquipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcResultMap({
        "workCalPmRInsPeriodCommonDTO.filterEquipId":"equip_id"
    });
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "workCalPmRInsPeriodCommonDTO.filterEqLocId",
    	"eqctg_id" : "workCalPmRInsPeriodCommonDTO.filterEqCtgId",
    	"dept_id" : "workCalPmRInsPeriodCommonDTO.filterDeptId",
    	"plant" : "workCalPmRInsPeriodCommonDTO.filterPlantId"
    });
    equipDescAc.init();

    eqLocDescAc = new autoC({"workCalPmRInsPeriodCommonDTO.filterEqLocDesc":"full_desc"});
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcResultMap({
        "workCalPmRInsPeriodCommonDTO.filterEqLocId":"eqloc_id"
    });
    eqLocDescAc.setAcDConditionMap({
    	"plant" : "workCalPmRInsPeriodCommonDTO.filterPlantId"
    });
    eqLocDescAc.init();

    eqCtgTypeAc = new autoC({"workCalPmRInsPeriodCommonDTO.filterEqCtgDesc":"full_desc"});
    eqCtgTypeAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcResultMap({
        "workCalPmRInsPeriodCommonDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();

	//법정설비여부  AC
    acSysDesc("workCalPmRInsPeriodCommonDTO.filterIsLawEq","workCalPmRInsPeriodCommonDTO.filterIsLawEq","IS_USE",false);

    mainMngAc = new autoC({"workCalPmRInsPeriodCommonDTO.filterMainMngName":"emp_name"});
    mainMngAc.setAcDisplay("EMP_NAME");
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "workCalPmRInsPeriodCommonDTO.filterMainMngId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "workCalPmRInsPeriodCommonDTO.filterDeptId",
    	"plant" : "workCalPmRInsPeriodCommonDTO.filterPlantId"
    });
    mainMngAc.init();

    wkCtrDescAc = new autoC({"workCalPmRInsPeriodCommonDTO.filterWkCtrDesc":"description"});
    wkCtrDescAc.setAcDisplay("DESCRIPTION");
    wkCtrDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    wkCtrDescAc.setTable("TAWKCTR");
    wkCtrDescAc.setAcResultMap({
        "workCalPmRInsPeriodCommonDTO.filterWkCtrId":"wkctr_id"
    });
    wkCtrDescAc.init();

	//설비유형  AC
    acSysDesc("workCalPmRInsPeriodCommonDTO.filterEqCtgTypeDesc","workCalPmRInsPeriodCommonDTO.filterEqCtgTypeId","EQCTG_TYPE");

    subMngAc = new autoC({"workCalPmRInsPeriodCommonDTO.filterSubMngName":"emp_name"});
    subMngAc.setAcDisplay("EMP_NAME");
    subMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    subMngAc.setTable("TAEMP");
    subMngAc.setAcResultMap({
        "workCalPmRInsPeriodCommonDTO.filterSubMngId":"emp_id"
    });
    subMngAc.setAcDConditionMap({
    	"dept_id" : "workCalPmRInsPeriodCommonDTO.filterDeptId",
    	"plant" : "workCalPmRInsPeriodCommonDTO.filterPlantId"
    });
    subMngAc.init();
    
    // 담당자 자동완성
    empAc = new autoC({"workCalPmRInsPeriodCommonDTO.filterEmpDesc":"emp_name"});
    empAc.setTable("TAEMP");
    empAc.setAcResultMap({
        "workCalPmRInsPeriodCommonDTO.filterEmpId":"emp_id"
      , "IS_USE":"Y"
    });
    empAc.setAcDConditionMap({
    	"dept_id" : "workCalPmRInsPeriodCommonDTO.filterDeptId",
    	"plant" : "workCalPmRInsPeriodCommonDTO.filterPlantId"
    });
    empAc.init();
    
    plantAc = new autoC({"workCalPmRInsPeriodCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "workCalPmRInsPeriodCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
    /*사용부서 AC*/
	//------------------------------------------------------------------------------------
    usageDeptAc = new autoC({"workCalPmRInsPeriodCommonDTO.filterUsageDeptDesc":"description"});
    usageDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    usageDeptAc.setTable("TADEPT");
    usageDeptAc.setAcResultMap({
        "workCalPmRInsPeriodCommonDTO.filterUsageDeptId":"dept_id"
    });
    usageDeptAc.setAcDConditionMap({
    	"plant" : "workCalPmRInsPeriodCommonDTO.filterPlantId"
    	,"plantDesc" : "workCalPmRInsPeriodCommonDTO.filterPlantDesc"
    });
    usageDeptAc.init();
    //-------------------------------------------------------------------------------------

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
   	 workCalPmRInsPeriodListForm.elements['workCalPmRInsPeriodCommonDTO.pmInsSchedId'].value = "";
        return sortColumn("workCalPmRInsPeriodList", this, workCalPmRInsPeriodListForm, "pmInsListId", ind, direction);
    });
	myGrid.attachEvent("onBeforeSelect",function(rowId, state){
		lv_selectedId = rowId;
		var status 		= getValueById(myGrid, rowId, "WOSTATUS");
		var pmStatus 	= getValueById(myGrid, rowId, "PMSCHEDSTATUS");
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
		return true;
	});
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		lv_selectedId = rowId;
		if(!isEdit)
		{		
			goOpen(rowId);
		}
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 상세열기
 */
function goOpen(rowId)
{
	var pmStatus = getValueById(myGrid, rowId, "PMSCHEDSTATUS");
	
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
	    
	    workCalPmRInsPeriodListForm.elements['workPmiCommonDTO.pminslistId'].value = pmInsListId;
		
	    var param = "strutsAction=8001&workPmiCommonDTO.pminslistId="+ pmInsListId;
		
		goTabPage(woparam);
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
	var form = document.workCalPmRInsPeriodListForm;
	
	var pmStatus = getValueById(myGrid, lv_selectedId, "PMSCHEDSTATUS");
	var wkOrId = getValueById(myGrid, lv_selectedId, "wkorId");

	if(pmStatus == "P")
	{
		form.elements['workCalPmRInsPeriodCommonDTO.pmInsSchedId'].value = getValueById(myGrid, lv_selectedId, 'pmInsSchedId');
		goCommonTabPage(form, <%= WorkCalPmRInsPeriodDetailAction.MONTH_SCHED_DETAIL_INIT %>, pageId, beforePageId);
	}
	else
	{
		form.elements['workPmiCommonDTO.pminslistId'].value = getValueById(myGrid, lv_selectedId, "pmInsListId");
		goCommonTabPage(form, <%= WorkPmiDetailAction.WORK_PMI_DETAIL_INIT %>, pageId, beforePageId);
	}

	beforePageId = pageId;
	
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
	var url = contextPath + "/workCalPmRInsPeriodList.do";
	var stAct = "<%=WorkCalPmRInsPeriodListAction.MONTH_SCHED_CHANGE %>";

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
	
	workCalPmRInsPeriodListForm.elements['workCalPmRInsPeriodCommonDTO.pmInsSchedId'].value = "";	// 검색시 Tab 이동Key Clear
	
	//Clear Update Mark for this page 
	clearUpdate(currentPageId);
	
	searchList();
	
	isEdit = false;
	
	//Make Page as Normal
	setForNormal();
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pmInsListId)
{
	workCalPmRInsPeriodListForm.elements['workCalPmRInsPeriodCommonDTO.pmInsListId'].value = _pmInsListId;
	findGridList("ReloadRow");
	workCalPmRInsPeriodListForm.elements['workCalPmRInsPeriodCommonDTO.pmInsListId'].value = "";
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/workCalPmRInsPeriodList.do";
    workCalPmRInsPeriodListForm.elements['strutsAction'].value = '<%=WorkCalPmRInsPeriodListAction.MONTH_SCHED_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workCalPmRInsPeriodListForm), "pmInsSchedId", "Y");
}

/**
 * 저장후 리스트만 재조회
 */
 function searchList()
 {
	var url = contextPath + "/workCalPmRInsPeriodList.do";
    workCalPmRInsPeriodListForm.elements['strutsAction'].value = '<%=WorkCalPmRInsPeriodListAction.MONTH_SCHED_LIST_FIND%>';

    myGrid.clearAll(); setLoading("gridbox");
    $.post(url,FormQueryString(workCalPmRInsPeriodListForm), function(_data){
    	myGrid.parse(_data,"js");
    });
}




/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	workCalPmRInsPeriodListForm.elements['workCalPmRInsPeriodCommonDTO.pmInsSchedId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search', '<%=WorkCalPmRInsPeriodListAction.MONTH_SCHED_LIST_FIND%>');
}



/**
 * 엑셀 다운.
 */
 function goExcel()
 {
	 workCalPmRInsPeriodListForm.elements['workCalPmRInsPeriodCommonDTO.pmInsSchedId'].value = "";
	excelServerAction("workCalPmRInsPeriodList", workCalPmRInsPeriodListForm );  
 }

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "workCalPmRInsPeriodDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	workCalPmRInsPeriodListForm.elements['workCalPmRInsPeriodCommonDTO.pmInsSchedId'].value = "";
	goCommonTabPage(workCalPmRInsPeriodListForm, '', pageId);
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

  	workCalPmRInsPeriodListForm.strutsAction.value = '<%=WorkCalPmRInsPeriodListAction.MONTH_SCHED_LIST_DELETE%>';
  	var url = contextPath + "/workCalPmRInsPeriodList.do";

  	$.post(url,FormQueryString(workCalPmRInsPeriodListForm)+delArray , function(_data){
    	afterDelete();
    });
  }

function afterDelete(){
	goClose('workCalPmRInsPeriodDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
  }


//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/workCalPmRInsPeriodList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="workCalPmRInsPeriodCommonDTO.pmInsSchedId"/><!-- Key -->
<html:hidden property="workCalPmRInsPeriodCommonDTO.filterDeptId"/>
<html:hidden property="workCalPmRInsPeriodCommonDTO.yyyymmdd"/>
<html:hidden property="workCalPmRInsPeriodCommonDTO.schedType"/>
<html:hidden property="workCalPmRInsPeriodCommonDTO.deptId"/>
<html:hidden property="workCalPmRInsPeriodCommonDTO.deptDesc"/>
<html:hidden property="workCalPmRInsPeriodCommonDTO.eqLocId"/>
<html:hidden property="workCalPmRInsPeriodCommonDTO.eqLocDesc"/>
<html:hidden property="workCalPmRInsPeriodCommonDTO.eqCtgId"/>
<html:hidden property="workCalPmRInsPeriodCommonDTO.eqCtgDesc"/>
<html:hidden property="workCalPmRInsPeriodCommonDTO.mainMngId"/>
<html:hidden property="workCalPmRInsPeriodCommonDTO.mainMngName"/>
<html:hidden property="workCalPmRInsPeriodCommonDTO.subMngId"/>
<html:hidden property="workCalPmRInsPeriodCommonDTO.subMngName"/>
<html:hidden property="workCalPmRInsPeriodCommonDTO.plfTypeId"/>
<html:hidden property="workCalPmRInsPeriodCommonDTO.plfTypeDesc"/>
<html:hidden property="workCalPmRInsPeriodCommonDTO.isLawEq"/>
<html:hidden property="workCalPmRInsPeriodCommonDTO.equipId"/>
<html:hidden property="workCalPmRInsPeriodCommonDTO.equipDesc"/>
<html:hidden property="workCalPmRInsPeriodCommonDTO.pmNo"/>
<html:hidden property="workCalPmRInsPeriodCommonDTO.filterEqLocId"/>
<html:hidden property="workCalPmRInsPeriodCommonDTO.filterEqCtgId"/>
<html:hidden property="workCalPmRInsPeriodCommonDTO.filterEqCtgTypeId"/>
<html:hidden property="workCalPmRInsPeriodCommonDTO.filterPlfTypeId"/>
<html:hidden property="workCalPmRInsPeriodCommonDTO.filterPlfTypeDesc"/>
<html:hidden property="workCalPmRInsPeriodCommonDTO.filterMainMngId"/>
<html:hidden property="workCalPmRInsPeriodCommonDTO.filterSubMngId"/>
<html:hidden property="workCalPmRInsPeriodCommonDTO.filterEquipId"/>
<html:hidden property="workCalPmRInsPeriodCommonDTO.filterWkCtrId"/>
<html:hidden property="workCalPmRInsPeriodCommonDTO.filterUsageDeptId"/>
<html:hidden property="workCalPmRInsPeriodCommonDTO.filterEmpId"/>
<html:hidden property="workCalPmRInsPeriodCommonDTO.empId"/>
<html:hidden property="workCalPmRInsPeriodCommonDTO.empDesc"/>
<html:hidden property="workCalPmRInsPeriodCommonDTO.filterPlantId"/>
<html:hidden property="workCalPmRInsPeriodCommonDTO.pmInsListId"/>

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
				<div class="b_line"></div>
				<div class="fb_group1">
					
				</div>
			</div>
		</div><!--sheader_box end-->
		<div class="article_box">
			<div class="form_box">
				<!-- 일자 -->
				<div class="field">
					<label><bean:message key="LABEL.date"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="workCalPmRInsPeriodCommonDTO.filterStartDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="workCalPmRInsPeriodCommonDTO.filterEndDate" tabindex="30" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.pmNo"/></label>
					<div class="input_box">
						<html:text property="workCalPmRInsPeriodCommonDTO.filterPmNo" tabindex="10"/>
					</div>
				</div>
				<!-- 관리부서 -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="workCalPmRInsPeriodCommonDTO.filterDeptDesc" tabindex="20" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 담당자 -->
                <div class="field">
                    <label><bean:message key="LABEL.manager"/></label>
                    <div class="input_box">
                        <html:text property="workCalPmRInsPeriodCommonDTO.filterEmpDesc" tabindex="22" />
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
				<!-- 설비 -->
				<div class="field">
					<label><bean:message key="LABEL.equipDesc"/></label>
					<div class="input_box">
						<html:text property="workCalPmRInsPeriodCommonDTO.filterEquipDesc" tabindex="25" />
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
						<html:text property="workCalPmRInsPeriodCommonDTO.filterEqLocDesc" tabindex="30" />
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
						<html:text property="workCalPmRInsPeriodCommonDTO.filterEqCtgDesc" tabindex="35" />
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
						<html:text property="workCalPmRInsPeriodCommonDTO.filterIsLawEq" tabindex="60" />
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
						<html:text property="workCalPmRInsPeriodCommonDTO.filterMainMngName" tabindex="70" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 관리자(부) -->
				<div class="field">
					<label><bean:message key="LABEL.subManager"/></label>
					<div class="input_box">
						<html:text property="workCalPmRInsPeriodCommonDTO.filterSubMngName" tabindex="90" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
                <div class="field">
                     <label><bean:message key="LABEL.wkCtr"/></label>
                     <div class="input_box">
                        <html:text property="workCalPmRInsPeriodCommonDTO.filterWkCtrDesc" tabindex="140" />
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
						<html:text property="workCalPmRInsPeriodCommonDTO.filterEqCtgTypeDesc" tabindex="150" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
							<html:text property="workCalPmRInsPeriodCommonDTO.filterPlantDesc" tabindex="160" />
							<p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 사용부서 -->
				<div class="field">
					<label><bean:message key="LABEL.usedDept"/></label>
					<div class="input_box">
						<html:text property="workCalPmRInsPeriodCommonDTO.filterUsageDeptDesc" tabindex="20" />
						<p class="open_spop"><a><span>조회</span></a></p>
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
<%--===========================================================================
월간순회일정 - 목록
author  youngjoo38
version $Id: workCalPmPtrlMonthList.jsp,v 1.1 2017/09/24 01:45:27 youngjoo38 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.cal.pmptrlmonth.action.WorkCalPmPtrlMonthListAction"%>
<%@ page import="dream.work.list.action.WorkListPtrlResultMstrDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="common.bean.User"%>
<html>
<head>
<!-- 월간순회일정 -->
<title><bean:message key='MENU.MONPTRLCAL' /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->

<script language="javascript">
<!--//
//그리드명
var myGrid, proGrid;
//클릭한 
var tempDay;
/** 자동완성 변수 */
var deptAc, managerAc, workGrpDescAc;

function loadPage()
{
	
    //부서
    workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.filterDeptId'].value    = loginUser.filterDeptId;
    workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.filterDeptDesc'].value  = loginUser.filterDeptDesc;
    
    //년월
    workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.filterYyyymm'].value = dateToData(getToday()).substr(0, 4)+"-"+dateToData(getToday()).substr(4, 2);

    //작업그룹
    if(loginUser.filterWkCtrId!='null'){
    	workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.filterPtrlWorkGrpId'].value = loginUser.filterWkCtrId;
    	workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.filterPtrlWorkGrpDesc'].value = loginUser.filterWkCtrDesc;
	}
    
    initGrid();

    deptAc = new autoC({"workCalPmPtrlMonthCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcDisplay("DESCRIPTION");
    deptAc.setAcConditionMap({
        "comp_no":loginUser.compNo
       });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "workCalPmPtrlMonthCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.init();

    // 담당자
    managerAc = new autoC({"workCalPmPtrlMonthCommonDTO.filterManagerDesc":"emp_name"});
    managerAc.setAcConditionMap({
        "comp_no":loginUser.compNo
       });
    managerAc.setTable("TAEMP");
    managerAc.setAcResultMap({
        "workCalPmPtrlMonthCommonDTO.filterManagerId":"emp_id"
    });
    managerAc.setAcDConditionMap({
    	"dept_id" : "workCalPmPtrlMonthCommonDTO.filterDeptId"
    });
    managerAc.init();

    workGrpDescAc = new autoC({"workCalPmPtrlMonthCommonDTO.filterPtrlWorkGrpDesc":"description"});
    workGrpDescAc.setAcDisplay("DESCRIPTION");
    workGrpDescAc.setAcConditionMap({
        "comp_no":loginUser.compNo
       });
    workGrpDescAc.setTable("TAWKCTR");
    workGrpDescAc.setAcResultMap({
        "workCalPmPtrlMonthCommonDTO.filterPtrlWorkGrpId":"wkctr_id"
    });
    workGrpDescAc.init();

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
    	selectedId = rowId;
    	goOpen(rowId);
    });
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.pmPtrlRsltListId'].value = "";
        return sortColumn("workCalPmPtrlMonthList", this, workCalPmPtrlMonthListForm, "PMPTRLSCHEDID", ind, direction);
    });
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
    myGrid.setDateFormat("%Y-%m-%d");

    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction,yyyymmdd,type)
{
    var url = contextPath + "/workCalPmPtrlMonthList.do";
    
    tempDay = yyyymmdd;
    tempType = type;
    
    workCalPmPtrlMonthListForm.elements['strutsAction'].value = '<%=WorkCalPmPtrlMonthListAction.MONTH_SCHED_LIST_FIND%>';
    workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.yyyymmdd'].value               = yyyymmdd;
    workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.schedType'].value              = type;
    
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workCalPmPtrlMonthListForm), "PMPTRLSCHEDID", "Y");
}

/**
 * 월간작업일정 조회
 */
function findSchedList(){
    var url = contextPath + "/workCalPmPtrlMonthList.do";
    workCalPmPtrlMonthListForm.elements['strutsAction'].value = '<%=WorkCalPmPtrlMonthListAction.MONTH_SCHED_FIND%>';
    setModal();
    $.post(url,FormQueryString(workCalPmPtrlMonthListForm), function(_data){
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
	document.getElementById("month_title").innerText = workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.yyyymmdd'].value;
	
	Object.keys(_dataObj).forEach(function(key){
		var _cntObject = _dataObj[key];
		
		var _yyyymmdd = key;
		var _week = _cntObject.WEEK;
		var _dow = _cntObject.DOW;
		var _dd;
		if(_yyyymmdd.substr(6, 1)=="0") _dd = _yyyymmdd.substr(7, 1);
		else _dd = _yyyymmdd.substr(6, 2);
		
		var _totCnt = _cntObject.TOTAL;
		var _sCnt = _cntObject.S;
		var _cCnt = _cntObject.C;
		
		var todayStyleStr = "date_box";
		if(_yyyymmdd==getToday().replace(/\-/gi, "")) todayStyleStr = "date_box date_today";
		var backWoStyleStr = "psc_box";
		if(Number(_totCnt)>Number(_cCnt) && Number(_yyyymmdd)<Number(getToday().replace(/\-/gi, ""))) backWoStyleStr = "psc_box psc_unc";
		
		var str = "<div class='"+todayStyleStr+"'>"+_dd+"</div>"+
					"<div class='"+backWoStyleStr+"'>"+
					"<span class='p'><a href=\"javascript:findGridList('Search', "+"'"+_yyyymmdd+"', '');\">"+_totCnt+"</a></span> /"+
					"<span class='s'><a href=\"javascript:findGridList('Search', "+"'"+_yyyymmdd+"', 'S');\">"+_sCnt+"</a></span> /"+
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
    workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.pmPtrlSchedId'].value = ""; // 검색시 Tab 이동Key Clear
    workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.pmPtrlRsltListId'].value = "";
    workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.schedType'].value = "";
    
    workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.yyyymmdd'].value        = workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.filterYyyymm'].value;
    workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.deptId'].value          = workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.filterDeptId'].value;         
    workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.deptDesc'].value        = workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.filterDeptDesc'].value;       
    workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.managerId'].value       = workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.filterManagerId'].value;      
    workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.managerDesc'].value     = workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.filterManagerDesc'].value;    
    workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.ptrlWorkId'].value      = workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.filterPtrlWorkId'].value;     
    workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.ptrlWorkTitle'].value   = workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.filterPtrlWorkTitle'].value;  
    workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.ptrlWorkNo'].value      = workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.filterPtrlWorkNo'].value;     
    workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.ptrlWorkGrpId'].value   = workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.filterPtrlWorkGrpId'].value;  
    workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.ptrlWorkGrpDesc'].value = workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.filterPtrlWorkGrpDesc'].value;
    
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
    workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.pmPtrlRsltListId'].value = getValueById(myGrid, selectedId,'PMPTRLRSLTLISTID');
    workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.pmPtrlSchedId'].value = getValueById(myGrid, selectedId,'PMPTRLSCHEDID');
    
    goCommonTabPage(workCalPmPtrlMonthListForm, <%= WorkListPtrlResultMstrDetailAction.WORK_LIST_PTRL_RESULT_DETAIL_INIT %>, pageId);
}

/**
 * 상세열기
 */
function goOpen(rowId){
    
    var pmPtrlRsltListId = getValueById(myGrid, rowId,'PMPTRLRSLTLISTID');
    var pmPtrlSchedId = getValueById(myGrid, rowId,'PMPTRLSCHEDID');
    
    workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.pmPtrlRsltListId'].value = pmPtrlRsltListId;
    workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.pmPtrlSchedId'].value = pmPtrlSchedId;

    goTabPage('workListPtrlResultMstrDetail');
}

function goOpenAction()
 {
     var selectedId=myGrid.getSelectedRowId();
     if(selectedId == null) return;
     
     workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.pmPtrlRsltListId'].value = getValueById(myGrid, selectedId,'PMPTRLRSLTLISTID');
     workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.pmPtrlSchedId'].value = getValueById(myGrid, selectedId,'PMPTRLSCHEDID');
     workCalPmPtrlMonthListForm.elements['strutsAction'].value = '<%=WorkListPtrlResultMstrDetailAction.WORK_LIST_PTRL_RESULT_DETAIL_INIT%>';
     openQuickTabPage(FormQueryString(workCalPmPtrlMonthListForm), 'workListPtrlResultMstrDetail'); 
     
 } 

/* function goPmstd()
{
    if(selectedId == "undefined" || selectedId == "") return;
    //strutsAction[0]=1001
    //workCalPmDInsMonthCommonDTO.pmId[0]=4522
    var fileName = getValueById(myGrid, selectedId, "PARAM");
    //var fileName = "workListPtrlResultMstrDetail";
    var pmId     = getValueById(myGrid, selectedId, "ptrlWorkNo");

    var url   = contextPath + "/"+fileName+".do";

    var popWidth = 1010;
    var popHeight = 640;

    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";

    //var param = "strutsAction=1001&workCalPmDInsMonthCommonDTO.pmId="+ pmId;
    var param = "strutsAction=1001&maPmMstrCommonDTO.pmId="+ pmId;

    openWindowWithPost(url, "PM_DETAIL", param, pos);
}  */
/**
 *   W/O 열기
 */
/* function goWo(){
    if(selectedId == "undefined" || selectedId == "") return;
    
    var woparam = "workListPtrlResultMstrDetail";
    var pmType = getValueById(myGrid, selectedId, "PMTYPE");
    var pmStatusCode = getValueById(myGrid, selectedId, "pmStatusCode");
    var pmPtrlRsltListId = getValueById(myGrid, selectedId, "pmPtrlRsltListId");
    //var woparam = getValueById(myGrid, selectedId, "PARAM");
    pmType = pmType.substring(0,1)+pmType.substring(1,pmType.length).toLowerCase();
    
    if(pmStatusCode != "P"){
        var url   = contextPath + "/"+woparam+".do";

        var popWidth = 1010;
        var popHeight = 640;

        // pop up이 중앙에 위치하게 한다.
        var TopPosition  = (screen.height/2 - popHeight/2);
        var LeftPosition = (screen.width/2 - popWidth/2);

        var pos = "width=" + popWidth + ",height=" + popHeight + "" +
                  ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
        pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
        
        var param = "strutsAction=8001&WorkCalPmPtrlMonthCommonDTO.pmPtrlRsltListId="+ pmPtrlRsltListId;
      
        openWindowWithPost(url, "WO_DETAIL", param, pos);
    }else{
        alertMessage1('<bean:message key="MESSAGE.MSG0024"/>');
    }
} */
 
/**
 * 엑셀 다운.
 */
function goExcel()
{
	excelServerAction("workCalPmPtrlMonthList", workCalPmPtrlMonthListForm);  
}

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "workCalPmPtrlMonthDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
    workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.pmPtrlSchedId'].value = "";
    goCommonTabPage(workCalPmPtrlMonthListForm, '', pageId);
}

function setAfterSelect(returnArray){
    var pmPtrlRsltListId = returnArray[0];
    var pmPtrlSchedId = returnArray[1];
    var param  = returnArray[2];
    workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.pmPtrlRsltListId'].value = pmPtrlRsltListId;
    workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.pmPtrlSchedId'].value = pmPtrlSchedId;

    goCommonTabPage(workCalPmPtrlMonthListForm, '', param);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pmPtrlRsltListId)
{
	goSearch();
    workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.pmPtrlRsltListId'].value = _pmPtrlRsltListId;
    findGridList('ReloadRow',tempDay,tempType);
    workCalPmPtrlMonthListForm.elements['workCalPmPtrlMonthCommonDTO.pmPtrlRsltListId'].value = "";
}

/**
 * 삭제
 */
 function goDelete(){
	 var chkedRowsId = getCheckedRows(myGrid, 'ISDELCHECK');
     for(var i = 0 ;i < chkedRowsId.length; i++)
    {
        if(getValueById(myGrid, chkedRowsId[i], "PROSTATUSID") == "C")
        {
            alertMessage1('<bean:message key="LABEL.delCompWork"/>'); //delCompWork
            // 완료된 작업은 삭제할 수 없습니다.
            return;
        }
        /* else if(getValueById(myGrid, chkedRowsId[i], "WOGENTYPE") == "WOPOINT")
        {
            alertMessage1('<bean:message key="MESSAGE.MSG0099"/>');
            return;
        } */
    }
    // ↑ 완료상태의 순회점검과 이상상태의 순회점검은 삭제할 수 없다.
	
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'PMPTRLSCHEDID'); //Grid, check box column seq, pk column seq
    //체크된게 없으면 return
     if(typeof delArray == "undefined"){
            alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
            return;
        }

    workCalPmPtrlMonthListForm.strutsAction.value = '<%=WorkCalPmPtrlMonthListAction.MONTH_SCHED_LIST_DELETE%>';
    var url = contextPath + "/workCalPmPtrlMonthList.do";

    $.post(url,FormQueryString(workCalPmPtrlMonthListForm)+delArray , function(_data){
        afterDelete();
    });
  }

function afterDelete(){
    goClose('workListPtrlResultMstrDetail',this);
    goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG050"/>');
    // 완료상태의 순회일정을 제외 후 삭제하였습니다.
  }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/workCalPmPtrlMonthList.do">
		<html:hidden property="strutsAction" />
		<html:hidden property="workCalPmPtrlMonthCommonDTO.pmPtrlSchedId" />      <!-- Key -->
		<html:hidden property="workCalPmPtrlMonthCommonDTO.pmPtrlRsltListId" />   <!-- Key -->
		<html:hidden property="workCalPmPtrlMonthCommonDTO.filterDeptId" />
		<html:hidden property="workCalPmPtrlMonthCommonDTO.yyyymmdd" />
		<html:hidden property="workCalPmPtrlMonthCommonDTO.schedType" />
		<html:hidden property="workCalPmPtrlMonthCommonDTO.deptId" />
		<html:hidden property="workCalPmPtrlMonthCommonDTO.deptDesc" />
		<html:hidden property="workCalPmPtrlMonthCommonDTO.ptrlWorkGrpId" />
		<html:hidden property="workCalPmPtrlMonthCommonDTO.filterPtrlWorkGrpId" />
		<html:hidden property="workCalPmPtrlMonthCommonDTO.ptrlWorkGrpDesc" />

		<html:hidden property="workCalPmPtrlMonthCommonDTO.managerId" />
		<html:hidden property="workCalPmPtrlMonthCommonDTO.filterManagerId" />
		<html:hidden property="workCalPmPtrlMonthCommonDTO.managerDesc" />
		<html:hidden property="workCalPmPtrlMonthCommonDTO.ptrlWorkId" />
		<html:hidden property="workCalPmPtrlMonthCommonDTO.filterPtrlWorkId" />
		<html:hidden property="workCalPmPtrlMonthCommonDTO.ptrlWorkTitle" />
		<html:hidden property="workCalPmPtrlMonthCommonDTO.ptrlWorkNo" />
		<!-- searchbox 박스 Line -->
		<div class="section_wrap">
			<div class="sheader_box">
				<div class="sheader_wrap">
					<a></a>
				</div>
				<div class="stitle_wrap">
					<div class="stitle_icon">
						<a></a>
					</div>
					<div class="stitle_tx">
						<bean:message key="LABEL.Filter" />
					</div>
				</div>
				<div class="function_box filter">
					<div class="fb_group3">
						<div class="sfb_wrap" style="display: none;"></div>
					</div>
					<div class="fb_group2"></div>
				</div>
			</div>
			<!--sheader_box end-->
			<div class="article_box">
				<div class="form_box">
					<div class="field">
						<!-- 년월 -->
						<label><bean:message key="LABEL.yyyymm" /></label>
						<div class="input_read">
							<html:text property="workCalPmPtrlMonthCommonDTO.filterYyyymm"
								readonly="true" />
							<p class="open_mon_calendar">
								<span>날짜</span>
							</p>
						</div>
					</div>
					<!-- 순회업무명 -->
					<div class="field">
						<label><bean:message key="LABEL.ptrlWorkTitle" /></label>
						<div class="input_box">
							<html:text
								property="workCalPmPtrlMonthCommonDTO.filterPtrlWorkTitle"
								tabindex="20" />
						</div>
					</div>
					<!-- 부서 -->
					<div class="field">
						<label><bean:message key="MENU.DEPT" /></label>
						<div class="input_box">
							<html:text property="workCalPmPtrlMonthCommonDTO.filterDeptDesc"
								tabindex="30" />
							<p class="open_spop">
								<a><span>조회</span></a>
							</p>
						</div>
					</div>
					<!-- 작업그룹 -->
					<div class="field">
						<label><bean:message key="LABEL.wkCtr" /></label>
						<div class="input_box">
							<html:text
								property="workCalPmPtrlMonthCommonDTO.filterPtrlWorkGrpDesc"
								tabindex="40" />
							<p class="open_spop">
								<a> <span>조회</span>
								</a>
							</p>
						</div>
					</div>
					<!-- 담당자 -->
					<div class="field">
						<label><bean:message key="LABEL.manager" /></label>
						<div class="input_box">
							<html:text
								property="workCalPmPtrlMonthCommonDTO.filterManagerDesc"
								tabindex="50" />
							<p class="open_spop">
								<a> <span>조회</span>
								</a>
							</p>
						</div>
					</div>
                    <!-- 순회업무# -->
                    <div class="field">
                        <label><bean:message key="LABEL.ptrlWorkNo" /></label>
                        <div class="input_box">
                            <html:text property="workCalPmPtrlMonthCommonDTO.filterPtrlWorkNo"
                                tabindex="60" />
                        </div>
                    </div>
				</div>
			</div>
			<!--article_box end-->
		</div>
		<!--  end section_wrap -->
		<!--월간일정 start-->
		<div class="section_wrap">
			<div class="sheader_box">
				<div class="sheader_wrap">
					<a></a>
				</div>
				<div class="stitle_wrap">
					<div class="stitle_icon">
						<a></a>
					</div>
					<div class="stitle_tx" id="month_title"></div>
				</div>
				<div class="function_box">
					<div class="fb_group3">
						<div class="sfb_wrap" style="display: none;"></div>
					</div>
					<div class="fb_group2">
					</div>
				</div>
			</div>
			<div class="article_box">
				<div class="tb_month">
					<table width="100%" border="0" cellpadding="0" cellspacing="0;">
						<tr>
							<th scope="col"><bean:message key="LABEL.sun" /></th>
							<th scope="col"><bean:message key="LABEL.mon" /></th>
							<th scope="col"><bean:message key="LABEL.tue" /></th>
							<th scope="col"><bean:message key="LABEL.wed" /></th>
							<th scope="col"><bean:message key="LABEL.thu" /></th>
							<th scope="col"><bean:message key="LABEL.fri" /></th>
							<th scope="col"><bean:message key="LABEL.sat" /></th>
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
						<span class="p"><strong>P</strong></span> : Planed
						&nbsp;&nbsp;&nbsp;<span class="s"><strong>S</strong></span> :
						Scheduled &nbsp;&nbsp;&nbsp;<span class="c"><strong>C</strong></span>
						: Completed
					</div>
				</div>
			</div>
			<!--article_box-->
		</div>
		<div class="section_wrap">
			<div class="sheader_box">
				<div class="sheader_wrap">
					<a></a>
				</div>
				<div class="stitle_wrap">
					<div class="stitle_icon">
						<a></a>
					</div>
					<div class="stitle_tx">
						<bean:message key="LABEL.List" />
					</div>
				</div>
				<div class="function_box list">
					<div class="fb_group3">
						<div class="sfb_wrap" style="display: none;"></div>
					</div>
					<div class="fb_group2"></div>
				</div>
			</div>
			<!--sheader_box end-->
			<div class="article_box">
				<div class="grid_area">
					<div id="gridbox"
						style="width: 100%; height: 270px; background-color: white;"></div>
				</div>
			</div>
		</div>
		<!--  End of section_wrap -->
	</html:form>
</body>
</html>
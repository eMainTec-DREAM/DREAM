<%--===========================================================================
교정작업일정(기간) - 목록
author  kim21017
version $Id: maWoSchedList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.cal.pmperiod.action.MaWoSchedListAction" %>
<%@ page import="dream.work.cal.pmperiod.action.MaWoSchedDetailAction" %>
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
<!-- 교정작업일정(기간) -->
<title><bean:message key='MENU.CALIBSCHED'/></title>
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
var woTypeAc;
var wkCtrDescAc;
var subMngAc;
var pmTypeAc;
var plantAc;
var eqDeptAc;
var peqDeptAc;
function loadPage() 
{
	//작업종류
    maWoSchedListForm.elements['maWoSchedCommonDTO.filterWoTypeId'].value = "PMC";
    maWoSchedListForm.elements['maWoSchedCommonDTO.filterWoTypeDesc'].value = "PMC";
    valSysDir('maWoSchedCommonDTO.filterWoTypeId', 'maWoSchedCommonDTO.filterWoTypeDesc', 'WO_TYPE', true);

    //계측기
	maWoSchedListForm.elements['maWoSchedCommonDTO.filterWoTypeId'].value = "PMC";
	maWoSchedListForm.elements['maWoSchedCommonDTO.filterEqCtgTypeId'].value = "TL";
	if(window.name=="PMSCHED_LIST_POPUP"){
		
	}else{
		//부서
		maWoSchedListForm.elements['maWoSchedCommonDTO.filterDeptId'].value    = loginUser.filterDeptId;
		maWoSchedListForm.elements['maWoSchedCommonDTO.filterDeptDesc'].value  = loginUser.filterDeptDesc;
		maWoSchedListForm.elements['maWoSchedCommonDTO.filterStartDate'].value   = getMinusDay(7);
		maWoSchedListForm.elements['maWoSchedCommonDTO.filterEndDate'].value   = getToday();
		//년월
	    //maWoSchedListForm.elements['maWoSchedCommonDTO.filterYyyymm'].value = dateToData(getToday()).substr(0, 4)+"-"+dateToData(getToday()).substr(4, 2);

		if(loginUser.eqLocId!='null'){
			maWoSchedListForm.elements['maWoSchedCommonDTO.filterEqLocId'].value = loginUser.eqLocId;
			maWoSchedListForm.elements['maWoSchedCommonDTO.filterEqLocDesc'].value = loginUser.eqLocDesc;
		}
/* 	    if(loginUser.wkctrId!='null'){
	    	maWoSchedListForm.elements['maWoSchedCommonDTO.filterWkCtrId'].value = loginUser.wkctrId;
	    	maWoSchedListForm.elements['maWoSchedCommonDTO.filterWkCtrDesc'].value = loginUser.wkctrDesc;
		} */
	    if(loginUser.filterWkCtrId!='null'){
	    	maWoSchedListForm.elements['maWoSchedCommonDTO.filterWkCtrId'].value = loginUser.filterWkCtrId;
	    	maWoSchedListForm.elements['maWoSchedCommonDTO.filterWkCtrDesc'].value = loginUser.filterWkCtrDesc;
		}
	    if(loginUser.eqCtgTypeId!='null'){
	    	maWoSchedListForm.elements['maWoSchedCommonDTO.filterEqCtgTypeId'].value = loginUser.eqCtgTypeId;
	    	maWoSchedListForm.elements['maWoSchedCommonDTO.filterEqCtgTypeDesc'].value = loginUser.eqCtgTypeDesc;
		}
	  	//공장명
        if(loginUser.filterPlant!='null'){
        	maWoSchedListForm.elements['maWoSchedCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
        	maWoSchedListForm.elements['maWoSchedCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
        }
	}
	
    initGrid();

	hideBtn("SAVE");
	hideBtn("EDITCNCL");
    
    deptAc = new autoC({"maWoSchedCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maWoSchedCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "maWoSchedCommonDTO.filterPlantId"
    	,"plantDesc" : "maWoSchedCommonDTO.filterPlantDesc"
    });
    deptAc.init();
    
    equipDescAc = new autoC({"maWoSchedCommonDTO.filterEquipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   ,"eqctg_type":"TL"
 	   });
    equipDescAc.setAcResultMap({
        "maWoSchedCommonDTO.filterEquipId":"equip_id"
    });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "maWoSchedCommonDTO.filterEqLocId",
    	"eqctg_id" : "maWoSchedCommonDTO.filterEqCtgId",
    	"dept_id" : "maWoSchedCommonDTO.filterDeptId",
    	"plant" : "maWoSchedCommonDTO.filterPlantId"
    });
    equipDescAc.init();
    
    eqLocDescAc = new autoC({"maWoSchedCommonDTO.filterEqLocDesc":"full_desc"});
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcResultMap({
        "maWoSchedCommonDTO.filterEqLocId":"eqloc_id"
    });
    eqLocDescAc.setAcDConditionMap({
    	"plant" : "maWoSchedCommonDTO.filterPlantId"
    });
    eqLocDescAc.init();
    
    eqCtgTypeAc = new autoC({"maWoSchedCommonDTO.filterEqCtgDesc":"full_desc"});
    eqCtgTypeAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcResultMap({
        "maWoSchedCommonDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();
    
    mainMngAc = new autoC({"maWoSchedCommonDTO.filterMainMngName":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maWoSchedCommonDTO.filterMainMngId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maWoSchedCommonDTO.filterDeptId",
    	"plant" : "maWoSchedCommonDTO.filterPlantId"
    });
    mainMngAc.init();
    
    woTypeAc = new autoC({"maWoSchedCommonDTO.filterWoTypeDesc":"description"});
    woTypeAc.setAcConditionMap({
        	"list_type":"WO_TYPE",
        	"param2":"PM",
        	"is_use":"Y"
  	   });
    woTypeAc.setTable("TACDSYSD");
    woTypeAc.setAcResultMap({
        "maWoSchedCommonDTO.filterWoTypeId":"cdsysd_no"
    });
    woTypeAc.init();
    
    wkCtrDescAc = new autoC({"maWoSchedCommonDTO.filterWkCtrDesc":"description"});
    wkCtrDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    wkCtrDescAc.setTable("TAWKCTR");
    wkCtrDescAc.setAcResultMap({
        "maWoSchedCommonDTO.filterWkCtrId":"wkctr_id"
    });
    wkCtrDescAc.init();
    
    subMngAc = new autoC({"maWoSchedCommonDTO.filterSubMngName":"emp_name"});
    subMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    subMngAc.setTable("TAEMP");
    subMngAc.setAcResultMap({
        "maWoSchedCommonDTO.filterSubMngId":"emp_id"
    });
    subMngAc.setAcDConditionMap({
    	"dept_id" : "maWoSchedCommonDTO.filterDeptId",
    	"plant" : "maWoSchedCommonDTO.filterPlantId"
    });
    subMngAc.init();

    plantAc = new autoC({"maWoSchedCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "maWoSchedCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();

    // 사용부서
    eqDeptAc = new autoC({"maWoSchedCommonDTO.filterEqUsaDeptDesc":"description"});
    eqDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    eqDeptAc.setTable("TADEPT");
    eqDeptAc.setAcResultMap({
        "maWoSchedCommonDTO.filterEqUsaDeptId":"dept_id"
    });
    eqDeptAc.setAcDConditionMap({
    	"plant" : "maWoSchedCommonDTO.filterPlantId"
    	,"plantDesc" : "maWoSchedCommonDTO.filterPlantDesc"
    });
    eqDeptAc.init();

    //상위설비
    pequipDescAc = new autoC({"maWoSchedCommonDTO.filterPequipDesc":"description"});
    pequipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   ,"eqctg_type":"MC"
 	   });
    pequipDescAc.setAcResultMap({
        "maWoSchedCommonDTO.filterPequipId":"equip_id"
    });
    pequipDescAc.setTable("TAEQUIPMENT");
    pequipDescAc.init();
    
    // 상위설비사용부서
    peqDeptAc = new autoC({"maWoSchedCommonDTO.filterPEqUsaDeptDesc":"description"});
    peqDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    peqDeptAc.setTable("TADEPT");
    peqDeptAc.setAcResultMap({
        "maWoSchedCommonDTO.filterPEqUsaDeptId":"dept_id"
    });
    peqDeptAc.setAcDConditionMap({
    	"plant" : "maWoSchedCommonDTO.filterPlantId"
    	,"plantDesc" : "maWoSchedCommonDTO.filterPlantDesc"
    });
    peqDeptAc.init();
    
    acSysDesc("maWoSchedCommonDTO.filterIsLawEq","maWoSchedCommonDTO.filterIsLawEq","IS_USE",true);
    acSysDesc("maWoSchedCommonDTO.filterEqCtgTypeDesc","maWoSchedCommonDTO.filterEqCtgTypeId","EQCTG_TYPE");
    acSysDesc("maWoSchedCommonDTO.filterPmTypeDesc","maWoSchedCommonDTO.filterPmTypeId","PMC_TYPE");
 	// 교정사유 AC
    acSysDesc("maWoYearSchedCommonDTO.filterCalTypeDesc","maWoYearSchedCommonDTO.filterCalTypeId","CALIB_TYPE", true);
}


/*작업종류 선택후 실행*/
/* function afterAutoCmpt(code)
{
		if(code=="maWoSchedCommonDTO.filterWoTypeDesc")
		{
		 	var listType = maWoSchedListForm.elements['maWoSchedCommonDTO.filterWoTypeId'].value+"_TYPE";
			//작업형태  AC
		    acSysDesc("maWoSchedCommonDTO.filterPmTypeDesc","maWoSchedCommonDTO.filterPmTypeId",listType);
		}
}
 */

/*작업종류 선택후 실행*/
/* function afterSetValue(lovType)
{
	if(lovType=="WO_TYPE")
	{
	 	var listType = maWoSchedListForm.elements['maWoSchedCommonDTO.filterWoTypeId'].value+"_TYPE";
		//작업형태  AC
	    acSysDesc("maWoSchedCommonDTO.filterPmTypeDesc","maWoSchedCommonDTO.filterPmTypeId",listType);
	}
} */

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
		maWoSchedListForm.elements['maWoSchedCommonDTO.pmSchedId'].value = "";
        return sortColumn("maWoSchedList", this, maWoSchedListForm, "PMSCHEDID", ind, direction);
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
			}
			else
			{
				 myGrid.cells(rowId,index).setDisabled(false);
			}
		}
	    else{
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
 * 일정조정 (상태가 작업계획(P))
 */
function goEdit()
{
	isEdit = true;
	
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
	var url = contextPath + "/maWoSchedList.do";
	var stAct = "<%=MaWoSchedListAction.MONTH_SCHED_CHANGE %>";

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

	showBtn("CREATE");
	showBtn("DELETE");
	showBtn("EXCEL");
	showBtn("SETTING");
	
	maWoSchedListForm.elements['maWoSchedCommonDTO.pmSchedId'].value = "";	// 검색시 Tab 이동Key Clear

	searchList();
	
	//Clear Update Mark for this page 
	clearUpdate(currentPageId);
		
	isEdit = false;
}


/**
 * 상세열기
 */
function goOpen(rowId)
{
	/* if(""!=beforePageId)
		goClose(beforePageId, this); */

	var pmStatus = getValueById(myGrid, rowId, "pmStatusCode");
	
	if(pmStatus == "P")
	{
		goTabPage('workCalPmcPeriodDetail');	
	}
	else if(pmStatus == "S" || pmStatus == "C")
	{
		var wkOrId = getValueById(myGrid, rowId, "wkorId");
		var pmType = getValueById(myGrid, rowId, "PMTYPE");
		var woparam = getValueById(myGrid, rowId, "WOPARAM");
		pmType = pmType.substring(0,1)+pmType.substring(1,pmType.length).toLowerCase();

		maWoSchedListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value = wkOrId;
		maWoSchedListForm.elements['maWoResultMstrCommonDTO.selectedWoType'].value = "PMC";
		
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
	var form = document.maWoSchedListForm;
	 


	var pmStatus = getValueById(myGrid, lv_selectedId, "pmStatusCode");
	var wkOrId = getValueById(myGrid, lv_selectedId, "wkorId");
	
	if(pmStatus == "P")
	{
		form.elements['maWoSchedCommonDTO.pmSchedId'].value = getValueById(myGrid, lv_selectedId, 'pmSchedId');
		goCommonTabPage(form, <%= MaWoSchedDetailAction.MONTH_SCHED_DETAIL_INIT %>, pageId, beforePageId);
	}
	else if(pmStatus == "S" || pmStatus == "C")
	{
		form.elements['maWoResultMstrCommonDTO.wkOrId'].value = wkOrId;
		goCommonTabPage(form, <%= MaWoResultMstrDetailAction.WO_RESULT_DETAIL_INIT %>, pageId, beforePageId);

	}

	beforePageId = pageId;
	
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maWoSchedList.do";
    maWoSchedListForm.elements['strutsAction'].value = '<%=MaWoSchedListAction.MONTH_SCHED_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maWoSchedListForm), "ptdisuselistId", "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_wkOrId)
{
	maWoSchedListForm.elements['maWoSchedCommonDTO.wkOrId'].value = _wkOrId;
	findGridList("ReloadRow");
	maWoSchedListForm.elements['maWoSchedCommonDTO.wkOrId'].value = "";
}

/**
 * 저장후 리스트만 재조회
 */
 function searchList()
 {
	var url = contextPath + "/maWoSchedList.do";
    maWoSchedListForm.elements['strutsAction'].value = '<%=MaWoSchedListAction.MONTH_SCHED_LIST_FIND%>';
    
    myGrid.clearAll(); setLoading("gridbox");
    $.post(url,FormQueryString(maWoSchedListForm), function(_data){
    	myGrid.parse(_data,"js");
    });
}


/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	maWoSchedListForm.elements['maWoSchedCommonDTO.pmSchedId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search', '<%=MaWoSchedListAction.MONTH_SCHED_LIST_FIND%>'); 
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
	//maPmMstrCommonDTO.pmId[0]=4522
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
    
    var param = "strutsAction=8001&maPmMstrCommonDTO.pmId="+ pmId;
  
    openWindowWithPost(url, "PM_DETAIL", param, pos);
}

/**
 * 엑셀 다운.
 */
 function goExcel()
 {
	maWoSchedListForm.elements['maWoSchedCommonDTO.pmSchedId'].value = "";
	excelServerAction("maWoSchedList", maWoSchedListForm);
 } 
 
/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "workCalPmcPeriodDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maWoSchedListForm.elements['maWoSchedCommonDTO.pmSchedId'].value = "";
	goCommonTabPage(maWoSchedListForm, '', pageId);	
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
	
  	maWoSchedListForm.strutsAction.value = '<%=MaWoSchedListAction.MONTH_SCHED_LIST_DELETE%>';
  	var url = contextPath + "/maWoSchedList.do";
  	
  	$.post(url,FormQueryString(maWoSchedListForm)+delArray , function(_data){
    	afterDelete();
    });
  }

function afterDelete(){
	goClose('workCalPmcPeriodDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
  }


//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maWoSchedList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maWoSchedCommonDTO.pmSchedId"/><!-- Key -->
<html:hidden property="maWoSchedCommonDTO.filterDeptId"/>
<html:hidden property="maWoSchedCommonDTO.yyyymmdd"/>
<html:hidden property="maWoSchedCommonDTO.schedType"/>
<html:hidden property="maWoSchedCommonDTO.deptId"/>
<html:hidden property="maWoSchedCommonDTO.deptDesc"/>
<html:hidden property="maWoSchedCommonDTO.eqLocId"/>
<html:hidden property="maWoSchedCommonDTO.eqLocDesc"/>
<html:hidden property="maWoSchedCommonDTO.eqCtgId"/>
<html:hidden property="maWoSchedCommonDTO.eqCtgDesc"/>
<html:hidden property="maWoSchedCommonDTO.mainMngId"/>
<html:hidden property="maWoSchedCommonDTO.mainMngName"/>
<html:hidden property="maWoSchedCommonDTO.subMngId"/>
<html:hidden property="maWoSchedCommonDTO.subMngName"/>
<html:hidden property="maWoSchedCommonDTO.plfTypeId"/>
<html:hidden property="maWoSchedCommonDTO.plfTypeDesc"/>
<html:hidden property="maWoSchedCommonDTO.isLawEq"/>
<html:hidden property="maWoSchedCommonDTO.woTypeId"/>
<html:hidden property="maWoSchedCommonDTO.woTypeDesc"/>
<html:hidden property="maWoSchedCommonDTO.pmTypeId"/>
<html:hidden property="maWoSchedCommonDTO.pmTypeDesc"/>
<html:hidden property="maWoSchedCommonDTO.equipId"/>
<html:hidden property="maWoSchedCommonDTO.equipDesc"/>
<html:hidden property="maWoSchedCommonDTO.pmNo"/>
<html:hidden property="maWoSchedCommonDTO.wkOrId"/>
<html:hidden property="maWoSchedCommonDTO.filterEqLocId"/>
<html:hidden property="maWoSchedCommonDTO.filterEqCtgId"/>
<html:hidden property="maWoSchedCommonDTO.filterEqCtgTypeId"/>
<html:hidden property="maWoSchedCommonDTO.filterPlfTypeId"/>
<html:hidden property="maWoSchedCommonDTO.filterPlfTypeDesc"/>
<html:hidden property="maWoSchedCommonDTO.filterWoTypeId"/>
<html:hidden property="maWoSchedCommonDTO.filterWoTypeDesc"/>
<html:hidden property="maWoSchedCommonDTO.filterPmTypeId"/>
<html:hidden property="maWoSchedCommonDTO.filterMainMngId"/>
<html:hidden property="maWoSchedCommonDTO.filterSubMngId"/>
<html:hidden property="maWoSchedCommonDTO.filterEquipId"/>
<html:hidden property="maWoSchedCommonDTO.filterWkCtrId"/>
<html:hidden property="maWoSchedCommonDTO.filterPlantId"/>

<html:hidden property="maWoSchedCommonDTO.filterPequipId"/>
<html:hidden property="maWoSchedCommonDTO.filterEqUsaDeptId"/>
<html:hidden property="maWoSchedCommonDTO.filterPEqUsaDeptId"/>

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
				<!-- 일자 -->
				<div class="field">
					<label><bean:message key="LABEL.date"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="maWoSchedCommonDTO.filterStartDate" tabindex="10" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="maWoSchedCommonDTO.filterEndDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.pmNo"/></label>
					<div class="input_box">
						<html:text property="maWoSchedCommonDTO.filterPmNo" tabindex="30"/>
					</div>
				</div>
				<!-- 관리부서 -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="maWoSchedCommonDTO.filterDeptDesc" tabindex="40" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 계측기번호 -->
				<div class="field">
					<label><bean:message key="LABEL.toolNo"/></label>
					<div class="input_box">
						<html:text property="maWoSchedCommonDTO.filterEquipNo" tabindex="45" />
					</div>
				</div>
				<!-- 계측기명 -->
				<div class="field">
					<label><bean:message key="LABEL.toolName"/></label>
					<div class="input_box">
						<html:text property="maWoSchedCommonDTO.filterEquipDesc" tabindex="50" />
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
						<html:text property="maWoSchedCommonDTO.filterEqLocDesc" tabindex="60" />
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
						<html:text property="maWoSchedCommonDTO.filterEqCtgDesc" tabindex="70" />
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
						<html:text property="maWoSchedCommonDTO.filterIsLawEq" tabindex="80" />
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
						<html:text property="maWoSchedCommonDTO.filterMainMngName" tabindex="90" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 관리자(부) -->
				<div class="field hidden">
					<label><bean:message key="LABEL.subManager"/></label>
					<div class="input_box">
						<html:text property="maWoSchedCommonDTO.filterSubMngName" tabindex="100" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 작업형태  -->
				<div class="field">
					<label><bean:message key="LABEL.pmType"/></label>
					<div class="input_box">
						<html:text property="maWoSchedCommonDTO.filterPmTypeDesc" tabindex="120" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>     
                <div class="field">
                     <label><bean:message key="LABEL.wkCtr"/></label>
                     <div class="input_box">
                        <html:text property="maWoSchedCommonDTO.filterWkCtrDesc" tabindex="130" />
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
						<html:text property="maWoSchedCommonDTO.filterEqCtgTypeDesc" tabindex="140" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
							<html:text property="maWoSchedCommonDTO.filterPlantDesc" tabindex="150" />
							<p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
				<!-- 사용부서  -->
                <div class="field">
                    <label><bean:message key="LABEL.usedDept"/></label>
                    <div class="input_box">
							<html:text property="maWoSchedCommonDTO.filterEqUsaDeptDesc" tabindex="160" />
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
							<html:text property="maWoSchedCommonDTO.filterPequipDesc" tabindex="170" />
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
							<html:text property="maWoSchedCommonDTO.filterPEqUsaDeptDesc" tabindex="180" />
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
							<html:text property="maWoSchedCommonDTO.filterCalTypeDesc" tabindex="190" />
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
						<html:text property="maWoSchedCommonDTO.filterRoomNumber" tabindex="23" />
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
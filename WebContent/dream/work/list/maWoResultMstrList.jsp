<%--===========================================================================
작업결과 - 목록
author  kim21017
version $Id: maWoResultMstrList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.list.action.MaWoResultMstrListAction" %>
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
<!-- 작업결과 -->
<title><bean:message key='MENU.WORESULT'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;
var beforePageId = '';
/** 자동완성 변수 */
var equipDescAc;
var workDescAc;
var deptAc;
var empAc;
var mainMngAc;
var subMngAc;
var eqLocDescAc;
var eqCtgTypeAc;
var vendorDescAc;
var wkCtrDescAc;
var plantAc;
var usageDeptAc;
var pEquipDescAc;
var workerAc;
var woTypeAc;
var pmTypeAc;
function loadPage() 
{
	//PMC를 제외하고 조회 
	setInitVal('maWoResultMstrCommonDTO.notWoTypeId',"PMC");
	
	//설비작업현황 - 전체작업현황, 예방작업현황
	if(window.name=="CHART_WO_LIST_POPUP"){
		if(M$('maWoResultMstrCommonDTO.filterWoStatus').value!='')
			valSysDirCode('maWoResultMstrCommonDTO.filterWoStatus', 'maWoResultMstrCommonDTO.filterWoStatusDesc', 'WO_STATUS','', true);
		if(M$('maWoResultMstrCommonDTO.filterWoTypeId').value!='')
			valSysDirCode('maWoResultMstrCommonDTO.filterWoTypeId', 'maWoResultMstrCommonDTO.filterWoTypeDesc', 'WO_TYPE','', true);
		if(M$('maWoResultMstrCommonDTO.filterPmTypeId').value!=''){
			if(M$('maWoResultMstrCommonDTO.filterWoTypeId').value!=''){
				valSysDirCode('maWoResultMstrCommonDTO.filterPmTypeId', 'maWoResultMstrCommonDTO.filterPmTypeDesc', M$('maWoResultMstrCommonDTO.filterWoTypeId').value+'_TYPE','', true);
			}else{
				valSysDirCode('maWoResultMstrCommonDTO.filterPmTypeId', 'maWoResultMstrCommonDTO.filterPmTypeDesc', 'PM_TYPE','', true);
			}
		}
	} else if(window.name=="WO_PLAN_CMPT_RATE_LIST_POPUP" || window.name=="EM_WO_GEN_RATE_LIST_POPUP"){
		
	} else if(window.name=="DEPT_WORK_POPUP")	{	
		//부서별 짝업진행현황에서 열었을 때 
		if(M$('maWoResultMstrCommonDTO.filterWoTypeId').value!='')
			valSysDirCode('maWoResultMstrCommonDTO.filterWoTypeId', 'maWoResultMstrCommonDTO.filterWoTypeDesc', 'WO_TYPE','', true);
		if(M$('maWoResultMstrCommonDTO.filterWoStatus').value!='')
			if(M$('maWoResultMstrCommonDTO.filterWoStatus').value!='!C')
				valSysDirCode('maWoResultMstrCommonDTO.filterWoStatus', 'maWoResultMstrCommonDTO.filterWoStatusDesc', 'WO_STATUS','', true);
			else
				maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterWoStatusDesc'].value = "미완료";
				
	} else if(window.name=="WO_LIST_POPUP") {
		
	} else{
		//작업시작일자, 종료일자 넣기.
		if(maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterEndDate'].value == "")
		{
			maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterStartDate'].value   = getWorkDay(-7);	
			maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterEndDate'].value   = getWorkDay();
		}
		else if(maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterStartDate'].value == "-1")
		{
			maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterStartDate'].value   = "";
		}
		//담당자 
		maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterEmpId'].value = loginUser.filterEmpId;
		maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterEmpDesc'].value = loginUser.filterEmpDesc;
		//부서
		maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterDeptId'].value    = loginUser.filterDeptId;
		maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterDeptDesc'].value  = loginUser.filterDeptDesc;
		
        if(loginUser.eqLocId!='null' && loginUser.eqLocId!=''){
			maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterEqLocId'].value = loginUser.eqLocId;
			maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterEqLocDesc'].value = loginUser.eqLocDesc;
		}
/*         if(loginUser.wkctrId!='null'){
			maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterWkCtrId'].value = loginUser.wkctrId;
			maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterWkCtrDesc'].value = loginUser.wkctrDesc;
		} */
        if(loginUser.filterWkCtrId!='null' && loginUser.filterWkCtrId!=''){
			maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterWkCtrId'].value = loginUser.filterWkCtrId;
			maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterWkCtrDesc'].value = loginUser.filterWkCtrDesc;
		}
        if(loginUser.eqCtgTypeId!='null' && loginUser.eqCtgTypeId!=''){
			maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterEqCtgTypeId'].value = loginUser.eqCtgTypeId;
			maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterEqCtgTypeDesc'].value = loginUser.eqCtgTypeDesc;
		}
      	//공장명
        if(loginUser.filterPlant!='null' && loginUser.filterPlant!=''){
        	maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
        	maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
		}
      	
      	//작업종류
      	if("" != maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterWoTypeId'].value){
      		valSysDirCode('maWoResultMstrCommonDTO.filterWoTypeId', 'maWoResultMstrCommonDTO.filterWoTypeDesc', 'WO_TYPE','', true);
		}
      	//작업상태
      	if("" != maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterWoStatus'].value){
			valSysDirCode('maWoResultMstrCommonDTO.filterWoStatus', 'maWoResultMstrCommonDTO.filterWoStatusDesc', 'WO_STATUS','', true);
		}
	}
	
    initGrid();

    equipDescAc = new autoC({"maWoResultMstrCommonDTO.filterEquipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipDescAc.setAcResultMap({
        "maWoResultMstrCommonDTO.filterEquipId":"equip_id"
    });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "maWoResultMstrCommonDTO.filterEqLocId",
    	"eqctg_id" : "maWoResultMstrCommonDTO.filterEqCtgId",
    	"dept_id" : "maWoResultMstrCommonDTO.filterDeptId",
    	"plant" : "maWoResultMstrCommonDTO.filterPlantId"
    });
    equipDescAc.init();
    
    workDescAc = new autoC({"maWoResultMstrCommonDTO.filterWkOrDesc":"description"});
    workDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    workDescAc.setTable("TAWORKORDER");
    workDescAc.init();
    
    deptAc = new autoC({"maWoResultMstrCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcDisplay("DESCRIPTION");
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maWoResultMstrCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "maWoResultMstrCommonDTO.filterPlantId"
    });
    deptAc.init();
    
    mainMngAc = new autoC({"maWoResultMstrCommonDTO.filterMainMngName":"emp_name"});
    mainMngAc.setAcDisplay("EMP_NAME");
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maWoResultMstrCommonDTO.filterMainMngId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maWoResultMstrCommonDTO.filterDeptId",
    	"plant" : "maWoResultMstrCommonDTO.filterPlantId"
    });
    mainMngAc.init();
    
    subMngAc = new autoC({"maWoResultMstrCommonDTO.filterSubMngName":"emp_name"});
    subMngAc.setAcDisplay("EMP_NAME");
    subMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    subMngAc.setTable("TAEMP");
    subMngAc.setAcResultMap({
        "maWoResultMstrCommonDTO.filterSubMngId":"emp_id"
    });
    subMngAc.setAcDConditionMap({
    	"dept_id" : "maWoResultMstrCommonDTO.filterDeptId",
    	"plant" : "maWoResultMstrCommonDTO.filterPlantId"
    });
    subMngAc.init();
    
    empAc = new autoC({"maWoResultMstrCommonDTO.filterEmpDesc":"emp_name"});
    empAc.setAcDisplay("EMP_NAME");
    empAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    empAc.setTable("TAEMP");
    empAc.setAcResultMap({
        "maWoResultMstrCommonDTO.filterEmpId":"emp_id"
    });
    empAc.setAcDConditionMap({
    	"dept_id" : "maWoResultMstrCommonDTO.filterDeptId",
    	"plant" : "maWoResultMstrCommonDTO.filterPlantId"
    });
    empAc.init();
    
    eqLocDescAc = new autoC({"maWoResultMstrCommonDTO.filterEqLocDesc":"full_desc"});
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqLocDescAc.setAcResultMap({
        "maWoResultMstrCommonDTO.filterEqLocId":"eqloc_id"
    });
    eqLocDescAc.setAcDConditionMap({
    	"plant" : "maWoResultMstrCommonDTO.filterPlantId"
    });
    eqLocDescAc.init();
    
    eqCtgTypeAc = new autoC({"maWoResultMstrCommonDTO.filterEqCtgDesc":"full_desc"});
    eqCtgTypeAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcResultMap({
        "maWoResultMstrCommonDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();
    
    
    vendorDescAc = new autoC({"maWoResultMstrCommonDTO.vendorDesc":"description"});
    vendorDescAc.setAcDisplay("DESCRIPTION");
    vendorDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_use":"Y"
  	   });
    vendorDescAc.setTable("TAVENDOR");
    vendorDescAc.setAcResultMap({
        "maWoResultMstrCommonDTO.vendorId":"vendor_id"
    });
    vendorDescAc.init();
    
    wkCtrDescAc = new autoC({"maWoResultMstrCommonDTO.filterWkCtrDesc":"description"});
    wkCtrDescAc.setAcDisplay("DESCRIPTION");
    wkCtrDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    wkCtrDescAc.setTable("TAWKCTR");
    wkCtrDescAc.setAcResultMap({
        "maWoResultMstrCommonDTO.filterWkCtrId":"wkctr_id"
    });
    wkCtrDescAc.init();
    
 	// 공장코드
	plantAc = new autoC({"maWoResultMstrCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "maWoResultMstrCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
    // 설비사용부서
    usageDeptAc = new autoC({"maWoResultMstrCommonDTO.filterUsageDeptDesc":"description"});
    usageDeptAc.setAcDisplay("DESCRIPTION");
    usageDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    usageDeptAc.setTable("TADEPT");
    usageDeptAc.setAcResultMap({
        "maWoResultMstrCommonDTO.filterUsageDeptId":"dept_id"
    });
    usageDeptAc.setAcDConditionMap({
    	"plant" : "maWoResultMstrCommonDTO.filterPlantId"
    });
    usageDeptAc.init();

    // 상위설비명
    pEquipDescAc = new autoC({"maWoResultMstrCommonDTO.filterParEquipDesc":"description"});
    pEquipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo,
 	   "eq_status":"R+S",
 	   //"eqctg_type":"TL"
 	   });
    pEquipDescAc.setAcResultMap({
        "maWoResultMstrCommonDTO.filterParEquipId":"equip_id"
    });
    pEquipDescAc.setTable("TAEQUIPMENT");
    pEquipDescAc.setAcDConditionMap({
    	"dept_id" : "maWoResultMstrCommonDTO.filterDeptId",
    	"plant" : "maWoResultMstrCommonDTO.filterPlantId"
    });
    pEquipDescAc.init();
    
    // 부위
    eqAsmbAc = new autoC({"maWoResultMstrCommonDTO.filterEqAsmbDesc":"description"});
    eqAsmbAc.setTable("TAEQASMB");
    eqAsmbAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    });
    eqAsmbAc.setAcDConditionMap({
    	"equip_id":"maWoResultMstrCommonDTO.filterEquipId"
    });
    eqAsmbAc.setAcResultMap({
        "maWoResultMstrCommonDTO.filterEqAsmbId":"eqasmb_id"
        ,"maWoResultMstrCommonDTO.filterEqAsmbDesc":"description"
    });
    eqAsmbAc.init();
    
    // 작업자 AC
    workerAc = new autoC({"maWoResultMstrCommonDTO.filterWorkerDesc":"emp_name"});
    workerAc.setAcDisplay("EMP_NAME");
    workerAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    workerAc.setTable("TAEMP");
    workerAc.setAcResultMap({
        "maWoResultMstrCommonDTO.filterWorkerId":"emp_id"
    });
    workerAc.setAcDConditionMap({
    	"dept_id" : "maWoResultMstrCommonDTO.filterDeptId",
    	"plant" : "maWoResultMstrCommonDTO.filterPlantId"
    });
    workerAc.init();
    
    //작업종류 AC
    woTypeAc = new autoC({"maWoResultMstrCommonDTO.filterWoTypeDesc":"description"});
    woTypeAc.setTable("TACDSYSD");
    woTypeAc.setAcConditionMap({
    	"list_type":"WO_TYPE"
    	,"is_use":"Y"
  	});
    woTypeAc.setAcResultMap({
        "maWoResultMstrCommonDTO.filterWoTypeId":"cdsysd_no"
    });
    woTypeAc.init();
    
    //작업종류 AC
    pmTypeAc = new autoC({"maWoResultMstrCommonDTO.filterPmTypeDesc":"description"});
    pmTypeAc.setTable("TACDSYSD");
    pmTypeAc.setAcDConditionMap({
    	"p_cdsysd_no":"maWoResultMstrCommonDTO.filterWoTypeId"
  	});
    pmTypeAc.setAcConditionMap({
    	"p_list_type":"WO_TYPE"
    	,"is_use":"Y"
  	});
    pmTypeAc.setAcResultMap({
        "maWoResultMstrCommonDTO.filterPmTypeId":"cdsysd_no"
    });
    pmTypeAc.init();
    
	//법정설비여부 AC
    acSysDesc("maWoResultMstrCommonDTO.filterIsLawEq","maWoResultMstrCommonDTO.filterIsLawEq","IS_USE",true);
	// 작업상태 AC
    acSysDesc("maWoResultMstrCommonDTO.filterWoStatusDesc","maWoResultMstrCommonDTO.filterWoStatus","WO_STATUS");
	// 자가/외주 AC
    acSysDesc("maWoResultMstrCommonDTO.selfVendorTypeDesc","maWoResultMstrCommonDTO.selfVendorType","SELF_VENDOR_TYPE");
	// 설비유형 AC
    acSysDesc("maWoResultMstrCommonDTO.filterEqCtgTypeDesc","maWoResultMstrCommonDTO.filterEqCtgTypeId","EQCTG_TYPE");
	// 교대조 AC
    acSysDesc("maWoResultMstrCommonDTO.filterShiftDesc","maWoResultMstrCommonDTO.filterShiftId","SHIFT_TYPE");
	
    acSysDesc("maWoResultMstrCommonDTO.conOperDesc","maWoResultMstrCommonDTO.conOper","TAB_CON_OPERATOR");
    acSysDesc("maWoResultMstrCommonDTO.filterEqGradeDesc","maWoResultMstrCommonDTO.filterEqGrade","EQ_GRADE");
    acSysDesc("maWoResultMstrCommonDTO.filterReqPriorityDesc","maWoResultMstrCommonDTO.filterReqPriority","REQ_PRIORITY");

	//품의여부 AC	
    acSysDesc("maWoResultMstrCommonDTO.filterIsDraft","maWoResultMstrCommonDTO.filterIsDraft","IS_USE");

	//내/외자
    acSysDesc("maWoResultMstrCommonDTO.filterPlfTypeDesc","maWoResultMstrCommonDTO.filterPlfTypeId","PLF_TYPE");
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen(rowId);
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value = "";
    	return sortColumn("maWoResultMstrList", this, maWoResultMstrListForm, "WKORID", ind, direction);
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
function findGridList(sheetAction)
{
    var url = contextPath + "/maWoResultMstrList.do";

    maWoResultMstrListForm.elements['strutsAction'].value = '<%=MaWoResultMstrListAction.WO_RESULT_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maWoResultMstrListForm), "WKORID", "Y");
}

/**
 * 엑셀 다운.
 */
 function goExcel()
 {
	maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value = "";
 	excelServerAction("maWoResultMstrList", maWoResultMstrListForm );
	 //excelAction(myGrid);
	 
	//goWopdf();
 }

 
/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search');
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
	var form = document.maWoResultMstrListForm;
	 
	form.elements['maWoResultMstrCommonDTO.wkOrId'].value = getValueById(myGrid, selectedId,'WKORID');
	goCommonTabPage(form, <%= MaWoResultMstrDetailAction.WO_RESULT_DETAIL_INIT %>, pageId,beforePageId);

	beforePageId = pageId;
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_wkOrId)
{
	maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value = _wkOrId;
	findGridList('ReloadRow');
	maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value = "";
}

/**
 * 상세열기
 */
 function goOpen(rowId){

	var woType = getValueById(myGrid, rowId,'WOTYPE');
	var pmType = getValueById(myGrid, rowId,'PMTYPE');
	var param  = getValueById(myGrid, rowId,'PARAM');

	if(typeof woType != "undefined")
	{
		maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.selectedWoType'].value = woType;
		maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.selectedPmType'].value = pmType;
	
		goTabPage(param);

	}

}

/**
 * 상세열기 버튼
 */
function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
	if(selectedId == null) return;
	
	var pageId  = getValueById(myGrid, selectedId,'PARAM');
	maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value = getValueById(myGrid, selectedId,'WKORID');
	maWoResultMstrListForm.elements['strutsAction'].value = '<%=MaWoResultMstrDetailAction.WO_RESULT_DETAIL_INIT%>';
	openQuickTabPage(FormQueryString(maWoResultMstrListForm), pageId); 
} 
 
  /**
   * 생성
   */
 function goCreate()
 {
//  	createValidationCheck(myGrid, beforePageId , "goCreateAction");
	goClose(beforePageId);
	openSelectType();
	maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value = "";
 }

 function addRow()
 {
	 myGrid.addRow("","",0);
 }
 
 function goCreateAction(pageId)
 {
	
 }
 
 function setAfterSelect(returnArray){
		var woType = returnArray[0];
		var pmType = returnArray[1];
		var param  = returnArray[2];
		maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.selectedWoType'].value = woType;
		maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.selectedPmType'].value = pmType;

// 		if(beforePageId!=param){
// 			goClose(beforePageId);
// 			beforePageId = param;
// 		}
		beforePageId = param;
// 		createValidationCheck(myGrid, param , "goCreateAction");
		goCommonTabPage(maWoResultMstrListForm, '', param);
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
		

		var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'WKORID'); //Grid, check box column seq, pk column seq
		if(typeof delArray == "undefined"){
			alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
			return;
		}
		
		
		
		maWoResultMstrListForm.strutsAction.value = '<%=MaWoResultMstrListAction.WO_RESULT_LIST_DELETE%>';
		var url = contextPath + "/maWoResultMstrList.do";
		$.post(url,FormQueryString(maWoResultMstrListForm)+delArray , function(_data){
	    	afterDelete();
	    }); 
	}
 
	function afterDelete(){
		goClose(beforePageId);
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
		
		var url   = contextPath + "/maWoResultMstrList.do";
		openPrintView(url, "maWoResultMstrCommonDTO.selectedWkorId="+selectStr);
	}
	
	function goWopdf(){
		goPrint();
	}
	
// 선택목록출력
function goSelectedprint()
{
	var chkedRowsId = getCheckedRows(myGrid, 'ISDELCHECK');

	if(""== chkedRowsId || chkedRowsId == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	reportCall('maWoResultMstrList','maWoResultMstrList'
			, "<%=user.getCompNo()%>"
			, "<%=user.getLangId()%>"
			, maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterStartDate'].value
			, maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterEndDate'].value
			, chkedRowsId
			);
}
/**
 *  선택목록출력
 */
function goWorklistprintLink()
{
	var chkedRowsId = getCheckedRows(myGrid, 'ISDELCHECK');

	if(""== chkedRowsId || chkedRowsId == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	var reportName = "maWoResultMstrList"; 
	var qrdName = "maWoResultMstrList";
	var startDate = maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterStartDate'].value;
	var endDate = maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.filterEndDate'].value;
	
	goWorklistprint(reportName, qrdName, "<%=user.getCompNo()%>", "<%=user.getLangId()%>", startDate, endDate, chkedRowsId);
}
	
	/**
	 *  Audit trail
	 */
	 function goAudtrailLink()
	 {
	    var objectId = maWoResultMstrListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value;
	    var fileName = currentPageId;

	    if(typeof objectId=="undefined") return;

	    goAudTrailList(objectId, fileName);
	 }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maWoResultMstrList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maWoResultMstrCommonDTO.wkOrId"/><!-- Key -->
<html:hidden property="maWoResultMstrCommonDTO.filterDeptId"/>
<html:hidden property="maWoResultMstrCommonDTO.filterEquipId"/>
<html:hidden property="maWoResultMstrCommonDTO.filterEmpId"/>
<html:hidden property="maWoResultMstrCommonDTO.filterEqLocId"/>
<html:hidden property="maWoResultMstrCommonDTO.filterEqCtgId"/>
<html:hidden property="maWoResultMstrCommonDTO.filterMainMngId"/>
<html:hidden property="maWoResultMstrCommonDTO.filterSubMngId"/>
<html:hidden property="maWoResultMstrCommonDTO.filterShiftId"/>
<html:hidden property="maWoResultMstrCommonDTO.filterPlfTypeId"/>
<html:hidden property="maWoResultMstrCommonDTO.filterPmTypeId"/>
<html:hidden property="maWoResultMstrCommonDTO.filterWoTypeId"/>
<html:hidden property="maWoResultMstrCommonDTO.filterWoStatus"/>
<html:hidden property="maWoResultMstrCommonDTO.filterWkCtrId"/>
<html:hidden property="maWoResultMstrCommonDTO.filterEqCtgTypeId"/>
<html:hidden property="maWoResultMstrCommonDTO.selectedWkorId"/>
<html:hidden property="maWoResultMstrCommonDTO.selectedPmType"/>
<html:hidden property="maWoResultMstrCommonDTO.selectedWoType"/>
<html:hidden property="maWoResultMstrCommonDTO.caDesc"/>
<html:hidden property="maWoResultMstrCommonDTO.reDesc"/>
<html:hidden property="maWoResultMstrCommonDTO.notCaCd"/>
<html:hidden property="maWoResultMstrCommonDTO.notReCd"/>
<html:hidden property="maWoResultMstrCommonDTO.notWoTypeId"/>
<html:hidden property="maWoResultMstrCommonDTO.notPmTypeId"/>
<html:hidden property="maWoResultMstrCommonDTO.filterMainMngId"/>
<html:hidden property="maWoResultMstrCommonDTO.selfVendorType"/>
<html:hidden property="maWoResultMstrCommonDTO.vendorId"/>
<html:hidden property="maWoResultMstrCommonDTO.filterPlantId"/>
<html:hidden property="maWoResultMstrCommonDTO.filterEqAsmbId"/>
<html:hidden property="maWoResultMstrCommonDTO.filterUsageDeptId"/>
<html:hidden property="maWoResultMstrCommonDTO.filterParEquipId"/>

<html:hidden property="maWoResultMstrCommonDTO.filterReqPriority"/>
<html:hidden property="maWoResultMstrCommonDTO.filterEqGrade"/>
<html:hidden property="maWoResultMstrCommonDTO.conOper"/>
<html:hidden property="maWoResultMstrCommonDTO.filterResStartDate"/>
<html:hidden property="maWoResultMstrCommonDTO.filterResEndDate"/>
<html:hidden property="maWoResultMstrCommonDTO.filterPartId"/>
<html:hidden property="maWoResultMstrCommonDTO.linkedUsageDeptId"/>
<html:hidden property="maWoResultMstrCommonDTO.filterWorkerId"/>

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
				<!-- 작업번호 -->
				<div class="field">
					<label><bean:message key="LABEL.woNo"/></label>
					<div class="input_box">
						<html:text property="maWoResultMstrCommonDTO.filterWoNo" tabindex="1"/>
					</div>
				</div>
				<!-- 작업명 -->
				<div class="field">
					<label><bean:message key="LABEL.woName"/></label>
					<div class="input_box">
						<html:text property="maWoResultMstrCommonDTO.filterWkOrDesc" tabindex="10"/>
					</div>
				</div>
				<!-- 작업일자 -->
				<div class="field">
					<label><bean:message key="LABEL.woDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="maWoResultMstrCommonDTO.filterStartDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="maWoResultMstrCommonDTO.filterEndDate" tabindex="30" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.equipDesc"/></label>
					<div class="input_box">
						<html:text property="maWoResultMstrCommonDTO.filterEquipDesc" tabindex="35" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 관리부서 -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="maWoResultMstrCommonDTO.filterDeptDesc" tabindex="40"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 담당자 -->
				<div class="field">
					<label><bean:message key="LABEL.manager"/></label>
					<div class="input_box">
						<html:text property="maWoResultMstrCommonDTO.filterEmpDesc" tabindex="50" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 위치 -->
				<div class="field">
					<label><bean:message key="LABEL.location"/></label>
					<div class="input_box">
						<html:text property="maWoResultMstrCommonDTO.filterEqLocDesc" tabindex="60"/>
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
						<html:text property="maWoResultMstrCommonDTO.filterEqCtgDesc" tabindex="70" />
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
                         <html:text property="maWoResultMstrCommonDTO.filterPlfTypeDesc" tabindex="80"/>
                         <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
				<!-- 법정설비여부 -->
				<div class="field">
					<label><bean:message key="LABEL.isLawEq"/></label>
					<div class="input_box">
						<html:text property="maWoResultMstrCommonDTO.filterIsLawEq" tabindex="90" />
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
						<html:text property="maWoResultMstrCommonDTO.filterMainMngName" tabindex="100" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 관리자(부) -->
				<div class="field">
					<label><bean:message key="LABEL.subManager"/></label>
					<div class="input_box">
						<html:text property="maWoResultMstrCommonDTO.filterSubMngName" tabindex="110" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 작업종류  -->
				<div class="field">
					<label><bean:message key="LABEL.woType"/></label>
					<div class="input_box">
						<html:text property="maWoResultMstrCommonDTO.filterWoTypeDesc" tabindex="120" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 작업형태  -->
				<div class="field">
					<label><bean:message key="LABEL.pmType"/></label>
					<div class="input_box">
						<html:text property="maWoResultMstrCommonDTO.filterPmTypeDesc" tabindex="130" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 작업상태  -->
				<div class="field">
					<label><bean:message key="LABEL.woStatus"/></label>
					<div class="input_box">
						<html:text property="maWoResultMstrCommonDTO.filterWoStatusDesc" tabindex="140" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 자가/외주  -->
				<div class="field">
					<label><bean:message key="LABEL.selfVendorType"/></label>
					<div class="input_box">
						<html:text property="maWoResultMstrCommonDTO.selfVendorTypeDesc" tabindex="150"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 거래처 -->
				<div class="field">
					<label><bean:message key="LABEL.vendor"/></label>
					<div class="input_box">
	                    <html:text property="maWoResultMstrCommonDTO.vendorDesc" tabindex="160"/>
	                    <p class="open_spop">
	                        <a>
	                            <span>조회</span>
	                        </a>
	                    </p>
	                </div>
				</div>
				<!-- 거래처명 -->
				<div class="field">
					<label><bean:message key="LABEL.vendorName"/></label>
					<div class="input_box">
	                    <html:text property="maWoResultMstrCommonDTO.filterVendorName" tabindex="100" />
	                </div>
				</div>
				<!-- 예방작업번호 -->
				<div class="field">
					<label><bean:message key="LABEL.pmNo"/></label>
					<div class="input_box">
						<html:text property="maWoResultMstrCommonDTO.filterPmNo" tabindex="160"/>
					</div>
				</div>     
                <div class="field">
                     <label><bean:message key="LABEL.wkCtr"/></label>
                     <div class="input_box">
                        <html:text property="maWoResultMstrCommonDTO.filterWkCtrDesc" tabindex="170" />
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
						<html:text property="maWoResultMstrCommonDTO.filterEqCtgTypeDesc" tabindex="180" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- shift  -->
				<div class="field">
					<label><bean:message key="LABEL.shiftType"/></label>
					<div class="input_box">
						<html:text property="maWoResultMstrCommonDTO.filterShiftDesc" tabindex="190" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
							<html:text property="maWoResultMstrCommonDTO.filterPlantDesc"
								tabindex="200" />
							<p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
				<!-- 품의여부 -->
				<div class="field">
					<label><bean:message key="LABEL.isDraft"/></label>
					<div class="input_box">
						<html:text property="maWoResultMstrCommonDTO.filterIsDraft" tabindex="210" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
                <!-- 설비등급  -->
                <div class="field">
                    <label><bean:message key="LABEL.eqGrade"/></label>
                    <div class="input_box">
							<html:text property="maWoResultMstrCommonDTO.filterEqGradeDesc" tabindex="210" />
							<p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 요청우선순위  -->
                <div class="field">
                    <label><bean:message key="LABEL.reqPriority"/></label>
                    <div class="input_box">
							<html:text property="maWoResultMstrCommonDTO.filterReqPriorityDesc" tabindex="220" />
							<p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <div class="field">
                <label><bean:message key="LABEL.failTimeMin"/></label>
	                <div class="datetime_wrap">
	                    <div class="input_box">
	                        <html:text property="maWoResultMstrCommonDTO.filterEqDnWorkTime" tabindex="230" styleClass="num"/>
	                    </div>
	                    <div class="input_box">
	                        <html:text property="maWoResultMstrCommonDTO.conOperDesc" tabindex="240"/>
	                        <p class="open_spop">
	                            <a>
	                            <span>조회</span></a>
	                        </p>
	                    </div>
	                </div>
	            </div>
				<!-- 설비 사용부서  -->
                <div class="field">
                    <label><bean:message key="LABEL.pUsageDept"/></label>
                    <div class="input_box">
							<html:text property="maWoResultMstrCommonDTO.filterUsageDeptDesc" tabindex="260" />
							<p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
				<!-- 상위 설비명 -->
                <div class="field">
                    <label><bean:message key="LABEL.parEquipDesc"/></label>
                    <div class="input_box">
							<html:text property="maWoResultMstrCommonDTO.filterParEquipDesc" tabindex="270" />
							<p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
				<!-- 부위명 -->
                <div class="field">
                    <label><bean:message key="LABEL.asmb"/></label>
                    <div class="input_box">
							<html:text property="maWoResultMstrCommonDTO.filterEqAsmbDesc" tabindex="275" />
							<p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
				<!-- 수정일자 -->
				<div class="field">
					<label><bean:message key="LABEL.modifyDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="maWoResultMstrCommonDTO.filterUpdStartDate" tabindex="280" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="maWoResultMstrCommonDTO.filterUpdEndDate" tabindex="290" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 작업상세내용 -->
				<div class="field">
                    <label><bean:message key="LABEL.woPerform"/></label>
                    <div class="input_box">
						<html:text property="maWoResultMstrCommonDTO.filterWoPerform" tabindex="300" />
                    </div>
                </div>
				<!-- 작업자 -->
				<div class="field">
					<label><bean:message key="LABEL.woCraft"/></label>
					<div class="input_box">
						<html:text property="maWoResultMstrCommonDTO.filterWorkerDesc" tabindex="310" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
                
                <c:set var="filePath" value="enhance/${compName}/work/list/maWoResultMstrList_${compNo}.jsp" />
				<c:if test="${udf:isExist(filePath)}">
					<c:import charEncoding="UTF-8" url="/enhance/${compName}/work/list/maWoResultMstrList_${compNo}.jsp"></c:import>
				</c:if>
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
		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
	 </div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>
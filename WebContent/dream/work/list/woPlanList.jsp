<%--===========================================================================
작업계획목록 - 목록
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.list.action.WoPlanListAction" %>
<%@ page import="dream.work.list.action.WoPlanDetailAction" %>
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
<!-- 작업계획목록 -->
<title><bean:message key='MENU.WOPLAN'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;
/* var beforePageId = ''; */
/** 자동완성 변수 */
var equipDescAc;
var workDescAc;
var deptAc;
var empAc;
var subEmpAc;
var mainMngAc;
var subMngAc;
var eqLocDescAc;
var eqCtgTypeAc;
var vendorDescAc;
var wkCtrDescAc;
var woTypeAc;

function loadPage() 
{
	//설비작업현황 - 전체작업현황, 예방작업현황
	if(window.name=="CHART_WO_LIST_POPUP"){
		if(M$('woPlanCommonDTO.filterWoPlanStatus').value!='')
			valSysDirCode('woPlanCommonDTO.filterWoPlanStatus', 'woPlanCommonDTO.filterWoPlanStatusDesc', 'WOPLAN_STATUS','', true);
		if(M$('woPlanCommonDTO.filterWoTypeId').value!='')
			valSysDirCode('woPlanCommonDTO.filterWoTypeId', 'woPlanCommonDTO.filterWoTypeDesc', 'WO_TYPE','', true);
		if(M$('woPlanCommonDTO.filterPmTypeId').value!=''){
			if(M$('woPlanCommonDTO.filterWoTypeId').value!=''){
				valSysDirCode('woPlanCommonDTO.filterPmTypeId', 'woPlanCommonDTO.filterPmTypeDesc', M$('woPlanCommonDTO.filterWoTypeId').value+'_TYPE','', true);
			}else{
				valSysDirCode('woPlanCommonDTO.filterPmTypeId', 'woPlanCommonDTO.filterPmTypeDesc', 'PM_TYPE','', true);
			}
		}
	}else{
		//작업시작일자, 종료일자 넣기.
		if(woPlanListForm.elements['woPlanCommonDTO.filterStartDate'].value == "")
		{
			woPlanListForm.elements['woPlanCommonDTO.filterStartDate'].value   = getMinusDay(7);
			woPlanListForm.elements['woPlanCommonDTO.filterEndDate'].value   = getToday();
		}
		//담당자 
		woPlanListForm.elements['woPlanCommonDTO.filterEmpId'].value = loginUser.filterEmpId;
		woPlanListForm.elements['woPlanCommonDTO.filterEmpDesc'].value = loginUser.filterEmpDesc;
		//부서
		woPlanListForm.elements['woPlanCommonDTO.filterDeptId'].value    = loginUser.filterDeptId;
		woPlanListForm.elements['woPlanCommonDTO.filterDeptDesc'].value  = loginUser.filterDeptDesc;
		
		//공장명
        if(loginUser.filterPlant!='null'){
        	woPlanListForm.elements['woPlanCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
			woPlanListForm.elements['woPlanCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
		}
		
        if(loginUser.eqLocId!='null'){
			woPlanListForm.elements['woPlanCommonDTO.filterEqLocId'].value = loginUser.eqLocId;
			woPlanListForm.elements['woPlanCommonDTO.filterEqLocDesc'].value = loginUser.eqLocDesc;
		}
/*         if(loginUser.wkctrId!='null'){
			woPlanListForm.elements['woPlanCommonDTO.filterWkCtrId'].value = loginUser.wkctrId;
			woPlanListForm.elements['woPlanCommonDTO.filterWkCtrDesc'].value = loginUser.wkctrDesc;
		} */
        if(loginUser.filterWkCtrId!='null'){
			woPlanListForm.elements['woPlanCommonDTO.filterWkCtrId'].value = loginUser.filterWkCtrId;
			woPlanListForm.elements['woPlanCommonDTO.filterWkCtrDesc'].value = loginUser.filterWkCtrDesc;
		}
        if(loginUser.eqCtgTypeId!='null'){
			woPlanListForm.elements['woPlanCommonDTO.filterEqCtgTypeId'].value = loginUser.eqCtgTypeId;
			woPlanListForm.elements['woPlanCommonDTO.filterEqCtgTypeDesc'].value = loginUser.eqCtgTypeDesc;
		}
	}
	
    initGrid();

    equipDescAc = new autoC({"woPlanCommonDTO.filterEquipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipDescAc.setAcResultMap({
        "woPlanCommonDTO.filterEquipId":"equip_id"
    });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "woPlanCommonDTO.filterEqLocId",
    	"eqctg_id" : "woPlanCommonDTO.filterEqCtgId",
    	"dept_id" : "woPlanCommonDTO.filterDeptId",
    	"plant" : "woPlanCommonDTO.filterPlantId"
    });
    equipDescAc.init();
    
    workDescAc = new autoC({"woPlanCommonDTO.filterWkOrDesc":"woDesc"});
    workDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    workDescAc.setTable("TAWORKORDER");
    workDescAc.setAcDConditionMap({
    	"dept_id" : "woPlanCommonDTO.filterDeptId",
    	"plant" : "woPlanCommonDTO.filterPlantId"
    });
    workDescAc.init();
    
    deptAc = new autoC({"woPlanCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcDisplay("DESCRIPTION");
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "woPlanCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "woPlanCommonDTO.filterPlantId"
    });
    deptAc.init();
    
    mainMngAc = new autoC({"woPlanCommonDTO.filterMainMngName":"emp_name"});
    mainMngAc.setAcDisplay("EMP_NAME");
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "woPlanCommonDTO.filterMainMngId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "woPlanCommonDTO.filterDeptId",
    	"plant" : "woPlanCommonDTO.filterPlantId"
    });
    mainMngAc.init();
    
    subMngAc = new autoC({"woPlanCommonDTO.filterSubMngName":"emp_name"});
    subMngAc.setAcDisplay("EMP_NAME");
    subMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    subMngAc.setTable("TAEMP");
    subMngAc.setAcResultMap({
        "woPlanCommonDTO.filterSubMngId":"emp_id"
    });
    subMngAc.setAcDConditionMap({
    	"dept_id" : "woPlanCommonDTO.filterDeptId",
    	"plant" : "woPlanCommonDTO.filterPlantId"
    });
    subMngAc.init();
    
    empAc = new autoC({"woPlanCommonDTO.filterEmpDesc":"emp_name"});
    empAc.setAcDisplay("EMP_NAME");
    empAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    empAc.setTable("TAEMP");
    empAc.setAcResultMap({
        "woPlanCommonDTO.filterEmpId":"emp_id"
    });
    empAc.setAcDConditionMap({
    	"dept_id" : "woPlanCommonDTO.filterDeptId",
    	"plant" : "woPlanCommonDTO.filterPlantId"
    });
    empAc.init();
    
    subEmpAc = new autoC({"woPlanCommonDTO.filterSubEmpDesc":"emp_name"});
    subEmpAc.setAcDisplay("EMP_NAME");
    subEmpAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    subEmpAc.setTable("TAEMP");
    subEmpAc.setAcResultMap({
        "woPlanCommonDTO.filterSubEmpId":"emp_id"
    });
    subEmpAc.setAcDConditionMap({
    	"dept_id" : "woPlanCommonDTO.filterDeptId",
    	"plant" : "woPlanCommonDTO.filterPlantId"
    });
    subEmpAc.init();
    
    eqLocDescAc = new autoC({"woPlanCommonDTO.filterEqLocDesc":"full_desc"});
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqLocDescAc.setAcResultMap({
        "woPlanCommonDTO.filterEqLocId":"eqloc_id"
    });
    eqLocDescAc.setAcDConditionMap({
    	"plant" : "woPlanCommonDTO.filterPlantId"
    });
    eqLocDescAc.init();
    
    eqCtgTypeAc = new autoC({"woPlanCommonDTO.filterEqCtgDesc":"full_desc"});
    eqCtgTypeAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcResultMap({
        "woPlanCommonDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();
    
    
    vendorDescAc = new autoC({"woPlanCommonDTO.vendorDesc":"description"});
    vendorDescAc.setAcDisplay("DESCRIPTION");
    vendorDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_use":"Y"
  	   });
    vendorDescAc.setTable("TAVENDOR");
    vendorDescAc.setAcResultMap({
        "woPlanCommonDTO.vendorId":"vendor_id"
    });
    vendorDescAc.init();
    
    wkCtrDescAc = new autoC({"woPlanCommonDTO.filterWkCtrDesc":"description"});
    wkCtrDescAc.setAcDisplay("DESCRIPTION");
    wkCtrDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    wkCtrDescAc.setTable("TAWKCTR");
    wkCtrDescAc.setAcResultMap({
        "woPlanCommonDTO.filterWkCtrId":"wkctr_id"
    });
    wkCtrDescAc.init();
    

	//법정설비여부 AC
    acSysDesc("woPlanCommonDTO.filterIsLawEq","woPlanCommonDTO.filterIsLawEq","IS_USE",true);
	//작업종류 AC
//    acSysDesc("woPlanCommonDTO.filterWoTypeDesc","woPlanCommonDTO.filterWoTypeId","WO_TYPE");
	woTypeAc = new autoC({"woPlanCommonDTO.filterWoTypeDesc":"description"});
	woTypeAc.setAcDisplay("DESCRIPTION");
	woTypeAc.setAcConditionMap({
		"list_type":"WO_TYPE"
      , "param22":"param22"	// param2 != 'PM' 조건
  	   });
	woTypeAc.setTable("TACDSYSD");
	woTypeAc.setAcResultMap({
        "woPlanCommonDTO.filterWoTypeId":"cdsysd_no"
    });
	woTypeAc.init();
	
	// 공장코드
	plantAc = new autoC({"woPlanCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "woPlanCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
	
	// 작업상태 AC
    acSysDesc("woPlanCommonDTO.filterWoPlanStatusDesc","woPlanCommonDTO.filterWoPlanStatus","WOPLAN_STATUS");
	// 자가/외주 AC
    acSysDesc("woPlanCommonDTO.selfVendorTypeDesc","woPlanCommonDTO.selfVendorType","SELF_VENDOR_TYPE");
	// 설비유형 AC
    acSysDesc("woPlanCommonDTO.filterEqCtgTypeDesc","woPlanCommonDTO.filterEqCtgTypeId","EQCTG_TYPE");
	// 교대조 AC
    acSysDesc("woPlanCommonDTO.filterShiftDesc","woPlanCommonDTO.filterShiftId","SHIFT_TYPE");

}

/*작업종류 선택후 실행*/
function afterAutoCmpt(code)
{
		if(code=="woPlanCommonDTO.filterWoTypeDesc")
		{
		 	var listType = woPlanListForm.elements['woPlanCommonDTO.filterWoTypeId'].value+"_TYPE";
			//작업형태 AC
		    acSysDesc("woPlanCommonDTO.filterPmTypeDesc","woPlanCommonDTO.filterPmTypeId",listType);
		}
}

/*작업종류 선택후 실행*/
function afterSetValue(lovType)
{
	if(lovType=="WO_TYPE")
	{
	 	var listType = woPlanListForm.elements['woPlanCommonDTO.filterWoTypeId'].value+"_TYPE";
	 	//작업형태 AC
	    acSysDesc("woPlanCommonDTO.filterPmTypeDesc","woPlanCommonDTO.filterPmTypeId",listType);
	}
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	woPlanListForm.elements['woPlanCommonDTO.wkOrId'].value = "";
    	return sortColumn("woPlanList", this, woPlanListForm, "WKORID", ind, direction);
	});

// 	myGrid.attachEvent("onCheckbox",function (rowId,cellInd,state){
// 		if(getValueById(myGrid, rowId, 'WOSTATUS')=='C'){
// 			setValueById(myGrid, rowId, 'ISDELCHECK', '0');
// 			return true;
// 		}
// 		else return true;
// 	});
	
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
	myGrid.init();
	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/woPlanList.do";

    woPlanListForm.elements['strutsAction'].value = '<%=WoPlanListAction.WO_PLAN_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(woPlanListForm), "WKORID", "Y");
}

/**
 * 엑셀 다운.
 */
 function goExcel()
 {
	woPlanListForm.elements['woPlanCommonDTO.wkOrId'].value = "";
 	excelServerAction("woPlanList", woPlanListForm );  
 }

 
/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	if(checkValidation()) return;
	woPlanListForm.elements['woPlanCommonDTO.wkOrId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search');
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
	var form = document.woPlanListForm;
	
	form.elements['woPlanCommonDTO.wkOrId'].value = getValueById(myGrid, selectedId,'WKORID');
	goCommonTabPage(form, <%= WoPlanDetailAction.WO_PLAN_DETAIL_INIT %>, pageId);

	//beforePageId = pageId;
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_wkOrId)
{
	woPlanListForm.elements['woPlanCommonDTO.wkOrId'].value = _wkOrId;
	findGridList('ReloadRow');
	woPlanListForm.elements['woPlanCommonDTO.wkOrId'].value = "";
}

/**
 * 상세열기 버튼
 */
function goOpen()
{
    goTabPage('woPlanDetail');	
}
function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
	if(selectedId == null) return;
	
	woPlanListForm.elements['woPlanCommonDTO.wkOrId'].value = getValueById(myGrid, selectedId,'WKORID');
	woPlanListForm.elements['strutsAction'].value = '<%=WoPlanDetailAction.WO_PLAN_DETAIL_INIT%>';
	openQuickTabPage(FormQueryString(woPlanListForm), 'woPlanDetail'); 
} 
 
 /**
  * 생성
  */
function goCreate()
{
   createValidationCheck(myGrid, "woPlanDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	woPlanListForm.elements['woPlanCommonDTO.wkOrId'].value = "";
    goCommonTabPage(woPlanListForm, '', pageId);
}

 /**
   * 삭제
   */
	function goDelete(){
	 
		var chkedRowsId = getCheckedRows(myGrid, 'ISDELCHECK');
		for(var i = 0 ;i < chkedRowsId.length; i++)
		{
			if(getValueById(myGrid, chkedRowsId[i], "WOPLANSTATUS") == "C")
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
		
		
		
		woPlanListForm.strutsAction.value = '<%=WoPlanListAction.WO_PLAN_LIST_DELETE%>';
		var url = contextPath + "/woPlanList.do";
		$.post(url,FormQueryString(woPlanListForm)+delArray , function(_data){
	    	afterDelete();
	    }); 
	}
 
	function afterDelete(){
		//goClose(beforePageId);
    	alertMessage1('<bean:message key="MESSAGE.MSG0032"/>');
    }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/woPlanList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="woPlanCommonDTO.wkOrId"/><!-- Key -->
<html:hidden property="woPlanCommonDTO.filterDeptId"/>
<html:hidden property="woPlanCommonDTO.filterEquipId"/>
<html:hidden property="woPlanCommonDTO.filterEmpId"/>
<html:hidden property="woPlanCommonDTO.filterSubEmpId"/>
<html:hidden property="woPlanCommonDTO.filterEqLocId"/>
<html:hidden property="woPlanCommonDTO.filterEqCtgId"/>
<html:hidden property="woPlanCommonDTO.filterMainMngId"/>
<html:hidden property="woPlanCommonDTO.filterSubMngId"/>
<html:hidden property="woPlanCommonDTO.filterShiftId"/>
<html:hidden property="woPlanCommonDTO.filterPlfTypeId"/>
<html:hidden property="woPlanCommonDTO.filterPmTypeId"/>
<html:hidden property="woPlanCommonDTO.filterWoTypeId"/>
<html:hidden property="woPlanCommonDTO.filterWoPlanStatus"/>
<html:hidden property="woPlanCommonDTO.filterWkCtrId"/>
<html:hidden property="woPlanCommonDTO.filterEqCtgTypeId"/>
<html:hidden property="woPlanCommonDTO.selectedWkorId"/>
<html:hidden property="woPlanCommonDTO.selectedPmType"/>
<html:hidden property="woPlanCommonDTO.selectedWoType"/>
<html:hidden property="woPlanCommonDTO.selfVendorType"/>
<html:hidden property="woPlanCommonDTO.vendorId"/>
<html:hidden property="woPlanCommonDTO.filterPlantId"/>
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
						<html:text property="woPlanCommonDTO.filterWoNo" tabindex="1"/>
					</div>
				</div>
				<!-- 작업명 -->
				<div class="field">
					<label><bean:message key="LABEL.woName"/></label>
					<div class="input_box">
						<html:text property="woPlanCommonDTO.filterWkOrDesc" tabindex="10"/>
					</div>
				</div>
				<!-- 작업일자 -->
				<div class="field">
					<label><bean:message key="LABEL.woDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="woPlanCommonDTO.filterStartDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="woPlanCommonDTO.filterEndDate" tabindex="30" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.equipDesc"/></label>
					<div class="input_box">
						<html:text property="woPlanCommonDTO.filterEquipDesc" tabindex="35" />
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
						<html:text property="woPlanCommonDTO.filterDeptDesc" tabindex="40"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 담당자 -->
				<div class="field">
					<label><bean:message key="LABEL.manager"/></label>
					<div class="input_box">
						<html:text property="woPlanCommonDTO.filterEmpDesc" tabindex="50" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 부 담당자 -->
				<div class="field">
					<label><bean:message key="LABEL.managerSub"/></label>
					<div class="input_box">
						<html:text property="woPlanCommonDTO.filterSubEmpDesc" tabindex="50" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 위치 -->
				<div class="field">
					<label><bean:message key="LABEL.location"/></label>
					<div class="input_box">
						<html:text property="woPlanCommonDTO.filterEqLocDesc" tabindex="60"/>
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
						<html:text property="woPlanCommonDTO.filterEqCtgDesc" tabindex="70" />
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
						<html:text property="woPlanCommonDTO.filterIsLawEq" tabindex="90" />
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
						<html:text property="woPlanCommonDTO.filterMainMngName" tabindex="100" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 관리자(부) -->
				<div class="field">
					<label><bean:message key="LABEL.subManager"/></label>
					<div class="input_box">
						<html:text property="woPlanCommonDTO.filterSubMngName" tabindex="110" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 작업종류  -->
				<div class="field">
					<label><bean:message key="LABEL.woType"/></label>
					<div class="input_box">
						<html:text property="woPlanCommonDTO.filterWoTypeDesc" tabindex="120" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 작업형태  -->
				<div class="field">
					<label><bean:message key="LABEL.pmType"/></label>
					<div class="input_box">
						<html:text property="woPlanCommonDTO.filterPmTypeDesc" tabindex="130" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 작업상태  -->
				<div class="field">
					<label><bean:message key="LABEL.woStatus"/></label>
					<div class="input_box">
						<html:text property="woPlanCommonDTO.filterWoPlanStatusDesc" tabindex="140" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 자가/외주  -->
				<div class="field">
					<label><bean:message key="LABEL.selfVendorType"/></label>
					<div class="input_box">
						<html:text property="woPlanCommonDTO.selfVendorTypeDesc" tabindex="150"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 거래처 -->
				<div class="field">
					<label><bean:message key="LABEL.vendor"/></label>
					<div class="input_box">
	                    <html:text property="woPlanCommonDTO.vendorDesc" tabindex="160"/>
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
	                    <html:text property="woPlanCommonDTO.filterVendorName" tabindex="100" />
	                </div>
				</div>
				<!-- 예방작업번호 -->
				<div class="field">
					<label><bean:message key="LABEL.pmNo"/></label>
					<div class="input_box">
						<html:text property="woPlanCommonDTO.filterPmNo" tabindex="160"/>
					</div>
				</div>     
                <div class="field">
                     <label><bean:message key="LABEL.wkCtr"/></label>
                     <div class="input_box">
                        <html:text property="woPlanCommonDTO.filterWkCtrDesc" tabindex="170" />
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
						<html:text property="woPlanCommonDTO.filterEqCtgTypeDesc" tabindex="180" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- shift  -->
				<div class="field">
					<label><bean:message key="LABEL.shiftType"/></label>
					<div class="input_box">
						<html:text property="woPlanCommonDTO.filterShiftDesc" tabindex="190" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
							<html:text property="woPlanCommonDTO.filterPlantDesc"
								tabindex="200" />
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
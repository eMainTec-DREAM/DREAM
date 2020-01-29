<%--===========================================================================
주간작업일정 - 목록
author  kim21017
version $Id: maWoWeekWoList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.cal.woweek.action.MaWoWeekWoListAction" %>
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
<!-- 주간작업일정 -->
<title><bean:message key='MENU.WEEKWOSCHED'/></title>
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
var lang = loginUser.locale;
/** 자동완성 변수 */
var equipDescAc;
var deptAc;
var eqLocDescAc;
var eqCtgTypeAc;
var mainMngAc;
var subMngAc;
var wkCtrDescAc;
var pmTypeAc;
var plantAc;
function loadPage() 
{
	//부서
	maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterDeptId'].value    = loginUser.filterDeptId;
	maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterDeptDesc'].value  = loginUser.filterDeptDesc;
	//년월일
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterWkorDate'].value = monOfThisWeek(getToday());
//     maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.startWkorDate'].value = monOfThisWeek(getToday());
//     maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.endWkorDate'].value = getMinusDay2(monOfThisWeek(getToday()),'-6');

	if(loginUser.eqLocId!='null'){
		maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterEqLocId'].value = loginUser.eqLocId;
		maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterEqLocDesc'].value = loginUser.eqLocDesc;
	}
/*     if(loginUser.wkctrId!='null'){
    	maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterWkCtrId'].value = loginUser.wkctrId;
    	maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterWkCtrDesc'].value = loginUser.wkctrDesc;
	} */
    if(loginUser.filterWkCtrId!='null'){
    	maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterWkCtrId'].value = loginUser.filterWkCtrId;
    	maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterWkCtrDesc'].value = loginUser.filterWkCtrDesc;
	}
    if(loginUser.eqCtgTypeId!='null'){
    	maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterEqCtgTypeId'].value = loginUser.eqCtgTypeId;
    	maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterEqCtgTypeDesc'].value = loginUser.eqCtgTypeDesc;
	}
    
    //공장명
    if(loginUser.filterPlant!='null'){
    	maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
  
    initGrid();
    equipDescAc = new autoC({"maWoWeekWoCommonDTO.filterEquipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipDescAc.setAcResultMap({
        "maWoWeekWoCommonDTO.filterEquipId":"equip_id"
    });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "maWoWeekWoCommonDTO.filterEqLocId",
    	"eqctg_id" : "maWoWeekWoCommonDTO.filterEqCtgId",
    	"dept_id" : "maWoWeekWoCommonDTO.filterDeptId",
    	"plant" : "maWoWeekWoCommonDTO.filterPlantId"
    });
    equipDescAc.init();
    
    deptAc = new autoC({"maWoWeekWoCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcDisplay("DESCRIPTION");
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maWoWeekWoCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "maWoWeekWoCommonDTO.filterPlantId"
    	,"plantDesc" : "maWoWeekWoCommonDTO.filterPlantDesc"
    });

    deptAc.init();
    
    eqLocDescAc = new autoC({"maWoWeekWoCommonDTO.filterEqLocDesc":"full_desc"});
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcResultMap({
        "maWoWeekWoCommonDTO.filterEqLocId":"eqloc_id"
    });
    eqLocDescAc.setAcDConditionMap({
    	"plant" : "maWoWeekWoCommonDTO.filterPlantId"
    });
    eqLocDescAc.init();
    
    eqCtgTypeAc = new autoC({"maWoWeekWoCommonDTO.filterEqCtgDesc":"full_desc"});
    eqCtgTypeAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcResultMap({
        "maWoWeekWoCommonDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();

	//법정설비여부  AC
    acSysDesc("maWoWeekWoCommonDTO.filterIsLawEq","maWoWeekWoCommonDTO.filterIsLawEq","IS_USE",true);
	//교대조  AC
    acSysDesc("maWoWeekWoCommonDTO.filterShiftDesc","maWoWeekWoCommonDTO.filterShiftId","SHIFT_TYPE");
    
    mainMngAc = new autoC({"maWoWeekWoCommonDTO.filterMainMngName":"emp_name"});
    mainMngAc.setAcDisplay("EMP_NAME");
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maWoWeekWoCommonDTO.filterMainMngId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maWoWeekWoCommonDTO.filterDeptId",
    	"plant" : "maWoWeekWoCommonDTO.filterPlantId"
    });
    mainMngAc.init();
    
    subMngAc = new autoC({"maWoWeekWoCommonDTO.filterSubMngName":"emp_name"});
    subMngAc.setAcDisplay("EMP_NAME");
    subMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    subMngAc.setTable("TAEMP");
    subMngAc.setAcResultMap({
        "maWoWeekWoCommonDTO.filterSubMngId":"emp_id"
    });
    subMngAc.setAcDConditionMap({
    	"dept_id" : "maWoWeekWoCommonDTO.filterDeptId",
    	"plant" : "maWoWeekWoCommonDTO.filterPlantId"
    });
    subMngAc.init();

	//작업종류  AC
    acSysDesc("maWoWeekWoCommonDTO.filterWoTypeDesc","maWoWeekWoCommonDTO.filterWoTypeId","WO_TYPE");
    
    wkCtrDescAc = new autoC({"maWoWeekWoCommonDTO.filterWkCtrDesc":"description"});
    wkCtrDescAc.setAcDisplay("DESCRIPTION");
    wkCtrDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    wkCtrDescAc.setTable("TAWKCTR");
    wkCtrDescAc.setAcResultMap({
        "maWoWeekWoCommonDTO.filterWkCtrId":"wkctr_id"
    });
    wkCtrDescAc.init();
    
    // 공장코드
	plantAc = new autoC({"maWoWeekWoCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "maWoWeekWoCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();

	//설비유형  AC
    acSysDesc("maWoWeekWoCommonDTO.filterEqCtgTypeDesc","maWoWeekWoCommonDTO.filterEqCtgTypeId","EQCTG_TYPE");
	}
	
/*작업종류 선택후 실행*/
function afterAutoCmpt(code)
{
		if(code=="maWoWeekWoCommonDTO.filterWoTypeDesc")
		{
		 	var listType = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterWoTypeId'].value+"_TYPE";
			//작업형태  AC
		    acSysDesc("maWoWeekWoCommonDTO.filterPmTypeDesc","maWoWeekWoCommonDTO.filterPmTypeId",listType);
		}
}


/*작업종류 선택후 실행*/
function afterSetValue(lovType)
{
	if(lovType=="WO_TYPE")
	{
	 	var listType = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterWoTypeId'].value+"_TYPE";
		//작업형태  AC
	    acSysDesc("maWoWeekWoCommonDTO.filterPmTypeDesc","maWoWeekWoCommonDTO.filterPmTypeId",listType);
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
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction,yyyymmdd,deptId,deptDesc,eqLocId,eqLocDesc,eqCtgId,eqCtgDesc,mainMngId,mainMngName,subMngId,subMngName,plfTypeId,plfTypeDesc,woTypeId,woTypeDesc,pmTypeId,pmTypeDesc,equipId,equipDesc,isLawEq,type,clickedWoType,wkCtrId,wkCtrDesc,eqCtgTypeId, eqCtgTypeDesc,shiftId,shiftDesc,isBd,plantId,plantDesc)
{
	goClose(beforePageId);
    var url = contextPath + "/maWoWeekWoList.do";
    tempDay = yyyymmdd;
    tempType = type;
    maWoWeekWoListForm.elements['strutsAction'].value = '<%=MaWoWeekWoListAction.WEEK_WO_LIST_FIND%>';
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.yyyymmdd'].value     = yyyymmdd;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.schedType'].value    = type;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.deptId'].value       = deptId;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.deptDesc'].value     = deptDesc;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.eqLocId'].value      = eqLocId;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.eqLocDesc'].value    = eqLocDesc;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.eqCtgId'].value      = eqCtgId;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.eqCtgDesc'].value    = eqCtgDesc;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.mainMngId'].value    = mainMngId;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.mainMngName'].value  = mainMngName;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.subMngId'].value     = subMngId;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.subMngName'].value   = subMngName;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.plfTypeId'].value    = plfTypeId;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.plfTypeDesc'].value  = plfTypeDesc;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.woTypeId'].value     = woTypeId;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.woTypeDesc'].value   = woTypeDesc;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.pmTypeId'].value     = pmTypeId;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.pmTypeDesc'].value   = pmTypeDesc;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.wkCtrId'].value      = wkCtrId;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.wkCtrDesc'].value    = wkCtrDesc;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.eqCtgTypeId'].value  = eqCtgTypeId;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.eqCtgTypeDesc'].value= eqCtgTypeDesc;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.equipId'].value      = equipId;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.equipDesc'].value    = equipDesc;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.shiftId'].value      = shiftId;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.shiftDesc'].value    = shiftDesc;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.isLawEq'].value      = isLawEq;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.clickedWoType'].value= clickedWoType;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.isBd'].value         = isBd;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.plantId'].value    = plantId;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.plantDesc'].value  = plantDesc;

    myGrid.clearAll(); setLoading("gridbox");
    setModal();
    $.post(url,FormQueryString(maWoWeekWoListForm), function(_data){
    	myGrid.parse(_data,"js");
    }).done(function(e){
		closeModal();
	});
}

/**
 * 저장 후 기존 데이터로 재조회
 */
function findAfterGridList(sheetAction)
{
    var url = contextPath + "/maWoWeekWoList.do";
    maWoWeekWoListForm.elements['strutsAction'].value = '<%=MaWoWeekWoListAction.WEEK_WO_LIST_FIND%>';
    
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.yyyymmdd'].value     = tempDay;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.schedType'].value    = tempType;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.deptId'].value       = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterDeptId'].value;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.deptDesc'].value     = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterDeptDesc'].value;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.eqLocId'].value      = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterEqLocId'].value;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.eqLocDesc'].value    = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterEqLocDesc'].value;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.eqCtgId'].value      = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterEqCtgId'].value;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.eqCtgDesc'].value    = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterEqCtgDesc'].value;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.mainMngId'].value    = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterMainMngId'].value;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.mainMngName'].value  = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterMainMngName'].value;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.subMngId'].value     = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterSubMngId'].value;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.subMngName'].value   = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterMainMngName'].value;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.plfTypeId'].value    = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterPlfTypeId'].value;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.plfTypeDesc'].value  = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterPlfTypeDesc'].value;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.woTypeId'].value     = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterWoTypeId'].value;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.woTypeDesc'].value   = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterWoTypeDesc'].value;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.pmTypeId'].value     = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterPmTypeId'].value;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.pmTypeDesc'].value   = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterPmTypeDesc'].value;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.wkCtrId'].value      = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterWkCtrId'].value;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.wkCtrDesc'].value    = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterWkCtrDesc'].value;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.eqCtgTypeId'].value  = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterEqCtgTypeId'].value;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.eqCtgTypeDesc'].value= maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterEqCtgTypeDesc'].value;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.equipId'].value      = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterEquipId'].value;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.equipDesc'].value    = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterEquipDesc'].value;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.shiftId'].value      = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterShiftId'].value;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.shiftDesc'].value    = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterShiftDesc'].value;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.isLawEq'].value      = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterIsLawEq'].value;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.plantId'].value      = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterPlantId'].value;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.plantDesc'].value    = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterPlantDesc'].value;

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maWoWeekWoListForm), "WKORID","Y");

}

/**
 * woType 찾기
 */
 var woTypeArr;
 var woTypeNameArr;
 var isUseArr;

 function findWoTypeList(){
	 woTypeArr     = new Array();
	 woTypeNameArr = new Array();
	 isUseArr      = new Array();
	 
	var url = contextPath + "/maWoWeekWoList.do";
	maWoWeekWoListForm.elements['strutsAction'].value = '<%=MaWoWeekWoListAction.WO_TYPE_FIND%>'; 
	setModal();
	$.post(url,FormQueryString(maWoWeekWoListForm), function(_data){
		afterFindWoType(_data);
    }).done(function(e){
		//closeModal();
	});
	
}
 function afterFindWoType(data){
	 woTypeArr     = parseXmlDoc(data, 'ARR0');
	 woTypeNameArr = parseXmlDoc(data, 'ARR1');
	 isUseArr      = parseXmlDoc(data, 'ARR2');
	 findSchedList();
 }

function findSchedList(){
	var url = contextPath + "/maWoWeekWoList.do";
	maWoWeekWoListForm.elements['strutsAction'].value = '<%=MaWoWeekWoListAction.WEEK_WO_FIND%>';
	setModal();
	$.post(url,FormQueryString(maWoWeekWoListForm), function(_data){
		afterFindSched(_data);
    }).done(function(e){
		//closeModal();
	});
	
}
/**
 * 주간일정 세팅
 */
 function afterFindSched(data){
	var day      = parseXmlDoc(data, 'ARR0');
	var week     = parseXmlDoc(data, 'ARR1');
	var dow      = parseXmlDoc(data, 'ARR2');
	var totalt   = parseXmlDoc(data, 'ARR3');
	var totalc   = parseXmlDoc(data, 'ARR4');
	var totalp   = parseXmlDoc(data, 'ARR5');
	var bmt      = parseXmlDoc(data, 'ARR6');
	var bmc      = parseXmlDoc(data, 'ARR7');
	var bmp      = parseXmlDoc(data, 'ARR8');
	var pmt      = parseXmlDoc(data, 'ARR9');
	var pmc      = parseXmlDoc(data, 'ARR10');
	var pmp      = parseXmlDoc(data, 'ARR11');
	var cmt      = parseXmlDoc(data, 'ARR12');
	var cmc      = parseXmlDoc(data, 'ARR13');
	var cmp      = parseXmlDoc(data, 'ARR14');
	var ivt      = parseXmlDoc(data, 'ARR15');
	var ivc      = parseXmlDoc(data, 'ARR16');
	var ivp      = parseXmlDoc(data, 'ARR17');
	var trt      = parseXmlDoc(data, 'ARR18');
	var trc      = parseXmlDoc(data, 'ARR19');
	var trp      = parseXmlDoc(data, 'ARR20');
	var tit      = parseXmlDoc(data, 'ARR21');
	var tic      = parseXmlDoc(data, 'ARR22');
	var tip      = parseXmlDoc(data, 'ARR23');
	var pmct     = parseXmlDoc(data, 'ARR24');
	var pmcc     = parseXmlDoc(data, 'ARR25');
	var pmcp     = parseXmlDoc(data, 'ARR26');
	var pmwt     = parseXmlDoc(data, 'ARR27');
	var pmwc     = parseXmlDoc(data, 'ARR28');
	var pmwp     = parseXmlDoc(data, 'ARR29');
	var pmit     = parseXmlDoc(data, 'ARR30');
	var pmic     = parseXmlDoc(data, 'ARR31');
	var pmip     = parseXmlDoc(data, 'ARR32');
	var pmpt     = parseXmlDoc(data, 'ARR33');
	var pmpc     = parseXmlDoc(data, 'ARR34');
	var pmpp     = parseXmlDoc(data, 'ARR35');
	
	var deptId      = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterDeptId'].value;
	var deptDesc    = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterDeptDesc'].value;
	var eqLocId     = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterEqLocId'].value;
	var eqLocDesc   = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterEqLocDesc'].value;
	var eqCtgId     = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterEqCtgId'].value;
	var eqCtgDesc   = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterEqCtgDesc'].value;
	var mainMngId   = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterMainMngId'].value;
	var mainMngName = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterMainMngName'].value;
	var subMngId    = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterSubMngId'].value;
	var subMngName  = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterSubMngName'].value;
	var plfTypeId   = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterPlfTypeId'].value;
	var plfTypeDesc = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterPlfTypeDesc'].value;
	var woTypeId    = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterWoTypeId'].value;
	var woTypeDesc  = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterWoTypeDesc'].value;
	var pmTypeId    = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterPmTypeId'].value;
	var pmTypeDesc  = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterPmTypeDesc'].value;
	var wkCtrId     = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterWkCtrId'].value;
	var wkCtrDesc   = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterWkCtrDesc'].value;
	var eqCtgTypeId = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterEqCtgTypeId'].value;
	var eqCtgTypeDesc= maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterEqCtgTypeDesc'].value;
	var equipId     = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterEquipId'].value;
	var equipDesc   = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterEquipDesc'].value;
	var shiftId     = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterShiftId'].value;
	var shiftDesc   = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterShiftDesc'].value;
	var isLawEq     = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterIsLawEq'].value;
	var plantId     = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterPlantId'].value;
	var plantDesc   = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterPlantDesc'].value;
	
	document.getElementById("week_title").innerText = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterWkorDate'].value + '<bean:message key="LABEL.week"/>';
	/**
	*달력초기화
	*/
	for(var i=1;i<=7;i++){
		document.getElementById("TH_"+i).innerHTML = "";
	}
	for(var i=1;i<=9;i++){
		for(var j=1;j<=21;j++){
			document.getElementById("TD"+i+"_"+j).innerHTML = "";
		}
	}
	//값 세팅
	var str_array= [totalt,totalc,totalp,bmt,bmc,bmp,pmt,pmc,pmp,cmt,cmc,cmp,ivt,ivc,ivp,trt,trc,trp,tit,tic,tip,pmct,pmcc,pmcp,pmwt,pmwc,pmwp,pmit,pmic,pmip,pmpt,pmpc,pmpp];
	var str_type = ['T','C','P'];//상태
	var str_class = ['p','c','s'];//스타일 
	var str_woType = ['','BM','PM','CM','IV','TR','TI','PMC','PMW','PMI','PMP'];//작업종류
	for(var i=1;i<=7;i++){
		var todayClass = "";
		document.getElementById("TH_"+i).innerHTML = "<div "+todayClass+">"+day[i-1].substring(4, 6)+"/"+day[i-1].substring(6, 8)+" ("+setComDay('"'+day[i-1]+'"',""+lang+"")+")"+"</div>";
	}
	for(var i=1;i<=11;i++){//종류 갯수
		for(var j=1;j<=21;j++){//일수*상태
// 			if(str_woType[i-1]=='PMI'&&str_type[parseInt((j-1)%3)]=='C'){
// 				var value  = str_array[parseInt((j-1)%3)+((i-1)*3)][parseInt((j-1)/3)];
// 				var valArr = value.split(',');
				
// 				var str = "<div><span class="+'"'+str_class[parseInt((j-1)%3)]+'"'+"><a href='javascript:findGridList("+'"'+"Search"+'"'+", "+'"'+day[parseInt((j-1)/3)]+'"'+", "+'"'+deptId+'"'+", "+'"'+deptDesc+'"'+", "
// 						+'"'+eqLocId+'"'+", "+'"'+eqLocDesc+'"'+", "+'"'+eqCtgId+'"'+", "+'"'+eqCtgDesc+'"'+", "+'"'+mainMngId+'"'+", "
// 						+'"'+mainMngName+'"'+", "+'"'+subMngId+'"'+", "+'"'+subMngName+'"'+", "+'"'+plfTypeId+'"'+", "+'"'+plfTypeDesc+'"'+", "
// 						+'"'+woTypeId+'"'+", "+'"'+woTypeDesc+'"'+", "+'"'+pmTypeId+'"'+", "+'"'+pmTypeDesc+'"'+", "
// 						+'"'+equipId+'"'+", "+'"'+equipDesc+'"'+", "
// 						+'"'+isLawEq+'"'+", "+'"'+str_type[parseInt((j-1)%3)]+'"'+", "+'"'+str_woType[i-1]+'"'+", "
// 						+'"'+wkCtrId+'"'+", "+'"'+wkCtrDesc+'"'+", "
// 						+'"'+eqCtgTypeId+'"'+", "+'"'+eqCtgTypeDesc+'"'+", "
// 						+'"'+shiftId+'"'+", "+'"'+shiftDesc+'"'+", "
// 						+'"'+"0"+'"'
// 						+");'>"+valArr[0]+"</a>("
						
// 						+"<a href='javascript:findGridList("+'"'+"Search"+'"'+", "+'"'+day[parseInt((j-1)/3)]+'"'+", "+'"'+deptId+'"'+", "+'"'+deptDesc+'"'+", "
// 						+'"'+eqLocId+'"'+", "+'"'+eqLocDesc+'"'+", "+'"'+eqCtgId+'"'+", "+'"'+eqCtgDesc+'"'+", "+'"'+mainMngId+'"'+", "
// 						+'"'+mainMngName+'"'+", "+'"'+subMngId+'"'+", "+'"'+subMngName+'"'+", "+'"'+plfTypeId+'"'+", "+'"'+plfTypeDesc+'"'+", "
// 						+'"'+woTypeId+'"'+", "+'"'+woTypeDesc+'"'+", "+'"'+pmTypeId+'"'+", "+'"'+pmTypeDesc+'"'+", "
// 						+'"'+equipId+'"'+", "+'"'+equipDesc+'"'+", "
// 						+'"'+isLawEq+'"'+", "+'"'+str_type[parseInt((j-1)%3)]+'"'+", "+'"'+str_woType[i-1]+'"'+", "
// 						+'"'+wkCtrId+'"'+", "+'"'+wkCtrDesc+'"'+", "
// 						+'"'+eqCtgTypeId+'"'+", "+'"'+eqCtgTypeDesc+'"'+", "
// 						+'"'+shiftId+'"'+", "+'"'+shiftDesc+'"'+", "
// 						+'"'+"1"+'"'
// 						+");'>"+valArr[1]+"</a>)</span></div>";
// 				document.getElementById("TD"+i+"_"+j).innerHTML = str;
// 			}else{
				var str = "<div><span class="+'"'+str_class[parseInt((j-1)%3)]+'"'+"><a href='javascript:findGridList("+'"'+"Search"+'"'+", "+'"'+day[parseInt((j-1)/3)]+'"'+", "+'"'+deptId+'"'+", "+'"'+deptDesc+'"'+", "
						+'"'+eqLocId+'"'+", "+'"'+eqLocDesc+'"'+", "+'"'+eqCtgId+'"'+", "+'"'+eqCtgDesc+'"'+", "+'"'+mainMngId+'"'+", "
						+'"'+mainMngName+'"'+", "+'"'+subMngId+'"'+", "+'"'+subMngName+'"'+", "+'"'+plfTypeId+'"'+", "+'"'+plfTypeDesc+'"'+", "
						+'"'+woTypeId+'"'+", "+'"'+woTypeDesc+'"'+", "+'"'+pmTypeId+'"'+", "+'"'+pmTypeDesc+'"'+", "
						+'"'+equipId+'"'+", "+'"'+equipDesc+'"'+", "
						+'"'+isLawEq+'"'+", "+'"'+str_type[parseInt((j-1)%3)]+'"'+", "+'"'+str_woType[i-1]+'"'+", "
						+'"'+wkCtrId+'"'+", "+'"'+wkCtrDesc+'"'+", "
						+'"'+eqCtgTypeId+'"'+", "+'"'+eqCtgTypeDesc+'"'+", "
						+'"'+shiftId+'"'+", "+'"'+shiftDesc+'"'+", "
						+'"'+"0"+'"'+", "+'"'+plantId+'"'+", "+'"'+plantDesc+'"'
						+");'>"+str_array[parseInt((j-1)%3)+((i-1)*3)][parseInt((j-1)/3)]+"</a></span></div>";
				document.getElementById("TD"+i+"_"+j).innerHTML = str;
// 			}
			
		}
	}
	document.getElementById("ALL_TITLE").innerHTML ="<span class='p'><bean:message key='LABEL.all'/></span>";
	for (var i = 0; i < woTypeArr.length; i++) { 
		if(isUseArr[i]=='Y') document.getElementById(woTypeArr[i]+"_TITLE").innerHTML = "<span class='p'>"+woTypeNameArr[i]+"</span>";
	}
	for (var i = 0; i < woTypeArr.length; i++) {
		if(isUseArr[i]!='Y') document.getElementById(woTypeArr[i]+"_TR").style.display = "none";
	}
	
	closeModal();
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.wkOrId'].value = "";	// 검색시 Tab 이동Key Clear
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.startWkorDate'].value = maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterWkorDate'].value;
    maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.endWkorDate'].value = getMinusDay2(maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.filterWkorDate'].value,'-6');
    
    findWoTypeList();
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
	maWoWeekWoListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value =  getValueById(myGrid, selectedId,'WKORID'); 
	maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.wkOrId'].value =  getValueById(myGrid, selectedId,'WKORID'); 
	goCommonTabPage(maWoWeekWoListForm, <%= MaWoResultMstrDetailAction.WO_RESULT_DETAIL_INIT %>, pageId,beforePageId);

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
	
	maWoWeekWoListForm.elements['maWoResultMstrCommonDTO.selectedWoType'].value = woType;
	maWoWeekWoListForm.elements['maWoResultMstrCommonDTO.selectedPmType'].value = pmType;

	goTabPage(param);
}

function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
 	if(selectedId == null) return;
 	
 	var pageId  = getValueById(myGrid, selectedId,'PARAM');
 	maWoWeekWoListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value =  getValueById(myGrid, selectedId,'WKORID'); 
	maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.wkOrId'].value =  getValueById(myGrid, selectedId,'WKORID'); 
	maWoWeekWoListForm.elements['strutsAction'].value = '<%=MaWoResultMstrDetailAction.WO_RESULT_DETAIL_INIT%>';
 	openQuickTabPage(FormQueryString(maWoWeekWoListForm), pageId); 
}

/**
 * 엑셀 다운.
 */
 function goExcel()
 {
	maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.wkOrId'].value = "";
 	excelServerAction("maWoWeekWoList", maWoWeekWoListForm);
 } 
 /**
  * 생성
  */
function goCreate()
{
	goClose(beforePageId);
	openSelectType();
	maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.wkOrId'].value = "";
}

function goCreateAction(pageId)
{
	
}

function setAfterSelect(returnArray){
	var woType = returnArray[0];
	var pmType = returnArray[1];
	var param  = returnArray[2];
	maWoWeekWoListForm.elements['maWoResultMstrCommonDTO.selectedWoType'].value = woType;
	maWoWeekWoListForm.elements['maWoResultMstrCommonDTO.selectedPmType'].value = pmType;

// 	if(beforePageId!=param){
// 		goClose(beforePageId);
// 		beforePageId = param;
// 	}
	beforePageId = param;
// 	createValidationCheck(myGrid, param , "goCreateAction");
	goCommonTabPage(maWoWeekWoListForm, '', param);
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
// 	if(maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.yyyymmdd'].value==""){
		// 날짜를 바꾸면 리스트에서 없어지지않음. 없어지려면 위에 코드로 바꾸면 되지만,  그 날의 리스트를 재조회하기 때문에 저장 이전에 선택된 row를 찾을 수 없다.
		maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.createdWkorId'].value = _wkOrId;
		findAfterGridList('ReloadRow');
		maWoWeekWoListForm.elements['maWoWeekWoCommonDTO.createdWkorId'].value = "";
// 	}else{
// 		findAfterGridList('Search');
// 	}
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
	
  	maWoWeekWoListForm.strutsAction.value = '<%=MaWoWeekWoListAction.WEEK_WO_LIST_DELETE%>';
  	var url = contextPath + "/maWoWeekWoList.do";
  	
  	$.post(url,FormQueryString(maWoWeekWoListForm)+delArray , function(_data){
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
	
	var url   = contextPath + "/maWoWeekWoList.do";
	openPrintView(url, "maWoWeekWoCommonDTO.selectedWkorId="+selectStr);
}

function goWopdf(){
	goPrint();
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maWoWeekWoList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maWoWeekWoCommonDTO.wkOrId"/><!-- Key -->
<html:hidden property="maWoResultMstrCommonDTO.wkOrId"/><!-- Key -->
<html:hidden property="maWoWeekWoCommonDTO.createdWkorId"/><!--create Key -->
<html:hidden property="maWoWeekWoCommonDTO.filterDeptId"/>
<html:hidden property="maWoWeekWoCommonDTO.yyyymmdd"/>
<html:hidden property="maWoWeekWoCommonDTO.startWkorDate"/>
<html:hidden property="maWoWeekWoCommonDTO.endWkorDate"/>
<html:hidden property="maWoWeekWoCommonDTO.clickedWoType"/>
<html:hidden property="maWoWeekWoCommonDTO.schedType"/>
<html:hidden property="maWoWeekWoCommonDTO.deptId"/>
<html:hidden property="maWoWeekWoCommonDTO.deptDesc"/>
<html:hidden property="maWoWeekWoCommonDTO.eqLocId"/>
<html:hidden property="maWoWeekWoCommonDTO.eqLocDesc"/>
<html:hidden property="maWoWeekWoCommonDTO.eqCtgId"/>
<html:hidden property="maWoWeekWoCommonDTO.eqCtgDesc"/>
<html:hidden property="maWoWeekWoCommonDTO.mainMngId"/>
<html:hidden property="maWoWeekWoCommonDTO.mainMngName"/>
<html:hidden property="maWoWeekWoCommonDTO.subMngId"/>
<html:hidden property="maWoWeekWoCommonDTO.subMngName"/>
<html:hidden property="maWoWeekWoCommonDTO.plfTypeId"/>
<html:hidden property="maWoWeekWoCommonDTO.plfTypeDesc"/>
<html:hidden property="maWoWeekWoCommonDTO.isLawEq"/>
<html:hidden property="maWoWeekWoCommonDTO.woTypeId"/>
<html:hidden property="maWoWeekWoCommonDTO.woTypeDesc"/>
<html:hidden property="maWoWeekWoCommonDTO.pmTypeId"/>
<html:hidden property="maWoWeekWoCommonDTO.pmTypeDesc"/>
<html:hidden property="maWoWeekWoCommonDTO.wkCtrId"/>
<html:hidden property="maWoWeekWoCommonDTO.wkCtrDesc"/>
<html:hidden property="maWoWeekWoCommonDTO.eqCtgTypeId"/>
<html:hidden property="maWoWeekWoCommonDTO.eqCtgTypeDesc"/>
<html:hidden property="maWoWeekWoCommonDTO.shiftId"/>
<html:hidden property="maWoWeekWoCommonDTO.shiftDesc"/>
<html:hidden property="maWoWeekWoCommonDTO.equipId"/>
<html:hidden property="maWoWeekWoCommonDTO.equipDesc"/>
<html:hidden property="maWoWeekWoCommonDTO.isBd"/>
<html:hidden property="maWoWeekWoCommonDTO.filterEqLocId"/>
<html:hidden property="maWoWeekWoCommonDTO.filterEqCtgId"/>
<html:hidden property="maWoWeekWoCommonDTO.filterShiftId"/>
<html:hidden property="maWoWeekWoCommonDTO.filterPlfTypeId"/>
<html:hidden property="maWoWeekWoCommonDTO.filterPlfTypeDesc"/>
<html:hidden property="maWoWeekWoCommonDTO.filterWoTypeId"/>
<html:hidden property="maWoWeekWoCommonDTO.filterPmTypeId"/>
<html:hidden property="maWoWeekWoCommonDTO.filterWkCtrId"/>
<html:hidden property="maWoWeekWoCommonDTO.filterEqCtgTypeId"/>
<html:hidden property="maWoWeekWoCommonDTO.filterMainMngId"/>
<html:hidden property="maWoWeekWoCommonDTO.filterSubMngId"/>
<html:hidden property="maWoWeekWoCommonDTO.filterEquipId"/>
<html:hidden property="maWoWeekWoCommonDTO.filterPlantId"/>
<html:hidden property="maWoWeekWoCommonDTO.plantId"/>
<html:hidden property="maWoWeekWoCommonDTO.plantDesc"/>
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
					<label><bean:message key="LABEL.workDate"/></label>
					<div class="input_read">
						<html:text property="maWoWeekWoCommonDTO.filterWkorDate" readonly="true"/>
						<p class="open_calendar"><span>날짜</span></p>
					</div>
				</div>
				<!-- 관리부서 -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="maWoWeekWoCommonDTO.filterDeptDesc" tabindex="20"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 설비 -->
				<div class="field">
					<label><bean:message key="LABEL.equipDesc"/></label>
					<div class="input_box">
						<html:text property="maWoWeekWoCommonDTO.filterEquipDesc" tabindex="25" />
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
						<html:text property="maWoWeekWoCommonDTO.filterEqLocDesc" tabindex="30" />
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
						<html:text property="maWoWeekWoCommonDTO.filterEqCtgDesc" tabindex="35" />
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
						<html:text property="maWoWeekWoCommonDTO.filterIsLawEq" tabindex="60" />
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
						<html:text property="maWoWeekWoCommonDTO.filterMainMngName" tabindex="70" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 관리자(부) -->
				<div class="field">
					<label><bean:message key="LABEL.subManager"/></label>
					<div class="input_box">
						<html:text property="maWoWeekWoCommonDTO.filterSubMngName" tabindex="90" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 작업종류  -->
				<div class="field">
					<label><bean:message key="LABEL.woType"/></label>
					<div class="input_box">
						<html:text property="maWoWeekWoCommonDTO.filterWoTypeDesc" tabindex="120" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 작업형태  -->
				<div class="field">
					<label><bean:message key="LABEL.pmType"/></label>
					<div class="input_box">
						<html:text property="maWoWeekWoCommonDTO.filterPmTypeDesc" tabindex="130" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>    
				<!-- 작업형태  --> 
                <div class="field">
                     <label><bean:message key="LABEL.wkCtr"/></label>
                     <div class="input_box">
                        <html:text property="maWoWeekWoCommonDTO.filterWkCtrDesc" tabindex="140" />
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
						<html:text property="maWoWeekWoCommonDTO.filterEqCtgTypeDesc" tabindex="150" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- shift  -->
				<div class="field">
					<label><bean:message key="LABEL.shiftType"/></label>
					<div class="input_box">
						<html:text property="maWoWeekWoCommonDTO.filterShiftDesc" tabindex="160" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
							<html:text property="maWoWeekWoCommonDTO.filterPlantDesc"
								tabindex="180" />
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
	<!--주간일정 start-->
	<div class="section_wrap">
		<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx" id="week_title"></div>
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
						<div class="tb_week">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<col class="dt2">
								<col class="dt1">
								<col class="dt1">
								<col class="dt1">
								<col class="dt2">
								<col class="dt2">
								<col class="dt2">
								<col class="dt1">
								<col class="dt1">
								<col class="dt1">
								<col class="dt2">
								<col class="dt2">
								<col class="dt2">
								<col class="dt1">
								<col class="dt1">
								<col class="dt1">
								<col class="dt2">
								<col class="dt2">
								<col class="dt2">
								<col class="dt1">
								<col class="dt1">
								<col class="dt1">
								<tr>
									<th scope="col" rowspan="2"><bean:message key="LABEL.workDate"/></th>
									<th scope="col" id="TH_1" colspan="3" class="le"></th>
									<th scope="col" id="TH_2" colspan="3" class="le"></th>
									<th scope="col" id="TH_3" colspan="3" class="le"></th>
									<th scope="col" id="TH_4" colspan="3" class="le"></th>
									<th scope="col" id="TH_5" colspan="3" class="le"></th>
									<th scope="col" id="TH_6" colspan="3" class="le"></th>
									<th scope="col" id="TH_7" colspan="3" class="le"></th>
								</tr>
								<tr>
									<th scope="col" class="le"><span class='p'>T</span></th>
									<th scope="col"><span class='c'>C</span></th>
									<th scope="col"><span class='s'>P</span></th>
									<th scope="col" class="le"><span class='p'>T</span></th>
									<th scope="col"><span class='c'>C</span></th>
									<th scope="col"><span class='s'>P</span></th>
									<th scope="col" class="le"><span class='p'>T</span></th>
									<th scope="col"><span class='c'>C</span></th>
									<th scope="col"><span class='s'>P</span></th>
									<th scope="col" class="le"><span class='p'>T</span></th>
									<th scope="col"><span class='c'>C</span></th>
									<th scope="col"><span class='s'>P</span></th>
									<th scope="col" class="le"><span class='p'>T</span></th>
									<th scope="col"><span class='c'>C</span></th>
									<th scope="col"><span class='s'>P</span></th>
									<th scope="col" class="le"><span class='p'>T</span></th>
									<th scope="col"><span class='c'>C</span></th>
									<th scope="col"><span class='s'>P</span></th>
									<th scope="col" class="le"><span class='p'>T</span></th>
									<th scope="col"><span class='c'>C</span></th>
									<th scope="col"><span class='s'>P</span></th>
								</tr>
								<tr class="rowtotal">
									<td id="ALL_TITLE" style="text-align:left;"></td>
									<td id="TD1_1" class="le"></td>
									<td id="TD1_2"></td>
									<td id="TD1_3"></td>
									<td id="TD1_4" class="le"></td>
									<td id="TD1_5"></td>
									<td id="TD1_6"></td>
									<td id="TD1_7" class="le"></td>
									<td id="TD1_8"></td>
									<td id="TD1_9"></td>
									<td id="TD1_10" class="le"></td>
									<td id="TD1_11"></td>
									<td id="TD1_12"></td>
									<td id="TD1_13" class="le"></td>
									<td id="TD1_14"></td>
									<td id="TD1_15"></td>
									<td id="TD1_16" class="le"></td>
									<td id="TD1_17"></td>
									<td id="TD1_18"></td>
									<td id="TD1_19" class="le"></td>
									<td id="TD1_20"></td>
									<td id="TD1_21"></td>
								</tr>
								<tr id="BM_TR">
									<td id="BM_TITLE" style="text-align:left;"></td>
									<td id="TD2_1" class="le"></td>
									<td id="TD2_2"></td>
									<td id="TD2_3"></td>
									<td id="TD2_4" class="le"></td>
									<td id="TD2_5"></td>
									<td id="TD2_6"></td>
									<td id="TD2_7" class="le"></td>
									<td id="TD2_8"></td>
									<td id="TD2_9"></td>
									<td id="TD2_10" class="le"></td>
									<td id="TD2_11"></td>
									<td id="TD2_12"></td>
									<td id="TD2_13" class="le"></td>
									<td id="TD2_14"></td>
									<td id="TD2_15"></td>
									<td id="TD2_16" class="le"></td>
									<td id="TD2_17"></td>
									<td id="TD2_18"></td>
									<td id="TD2_19" class="le"></td>
									<td id="TD2_20"></td>
									<td id="TD2_21"></td>
								</tr>
								<tr id="PM_TR">
									<td id="PM_TITLE" style="text-align:left;"></td>
									<td id="TD3_1" class="le"></td>
									<td id="TD3_2"></td>
									<td id="TD3_3"></td>
									<td id="TD3_4" class="le"></td>
									<td id="TD3_5"></td>
									<td id="TD3_6"></td>
									<td id="TD3_7" class="le"></td>
									<td id="TD3_8"></td>
									<td id="TD3_9"></td>
									<td id="TD3_10" class="le"></td>
									<td id="TD3_11"></td>
									<td id="TD3_12"></td>
									<td id="TD3_13" class="le"></td>
									<td id="TD3_14"></td>
									<td id="TD3_15"></td>
									<td id="TD3_16" class="le"></td>
									<td id="TD3_17"></td>
									<td id="TD3_18"></td>
									<td id="TD3_19" class="le"></td>
									<td id="TD3_20"></td>
									<td id="TD3_21"></td>
								</tr>
								<tr id="CM_TR">
									<td id="CM_TITLE" style="text-align:left;"></td>
									<td id="TD4_1" class="le"></td>
									<td id="TD4_2"></td>
									<td id="TD4_3"></td>
									<td id="TD4_4" class="le"></td>
									<td id="TD4_5"></td>
									<td id="TD4_6"></td>
									<td id="TD4_7" class="le"></td>
									<td id="TD4_8"></td>
									<td id="TD4_9"></td>
									<td id="TD4_10" class="le"></td>
									<td id="TD4_11"></td>
									<td id="TD4_12"></td>
									<td id="TD4_13" class="le"></td>
									<td id="TD4_14"></td>
									<td id="TD4_15"></td>
									<td id="TD4_16" class="le"></td>
									<td id="TD4_17"></td>
									<td id="TD4_18"></td>
									<td id="TD4_19" class="le"></td>
									<td id="TD4_20"></td>
									<td id="TD4_21"></td>
								</tr>
								<tr id="IV_TR">
									<td id="IV_TITLE" style="text-align:left;"></td>
									<td id="TD5_1" class="le"></td>
									<td id="TD5_2"></td>
									<td id="TD5_3"></td>
									<td id="TD5_4" class="le"></td>
									<td id="TD5_5"></td>
									<td id="TD5_6"></td>
									<td id="TD5_7" class="le"></td>
									<td id="TD5_8"></td>
									<td id="TD5_9"></td>
									<td id="TD5_10" class="le"></td>
									<td id="TD5_11"></td>
									<td id="TD5_12"></td>
									<td id="TD5_13" class="le"></td>
									<td id="TD5_14"></td>
									<td id="TD5_15"></td>
									<td id="TD5_16" class="le"></td>
									<td id="TD5_17"></td>
									<td id="TD5_18"></td>
									<td id="TD5_19" class="le"></td>
									<td id="TD5_20"></td>
									<td id="TD5_21"></td>
								</tr>
								<tr id="TR_TR">
									<td id="TR_TITLE" style="text-align:left;"></td>
									<td id="TD6_1" class="le"></td>
									<td id="TD6_2"></td>
									<td id="TD6_3"></td>
									<td id="TD6_4" class="le"></td>
									<td id="TD6_5"></td>
									<td id="TD6_6"></td>
									<td id="TD6_7" class="le"></td>
									<td id="TD6_8"></td>
									<td id="TD6_9"></td>
									<td id="TD6_10" class="le"></td>
									<td id="TD6_11"></td>
									<td id="TD6_12"></td>
									<td id="TD6_13" class="le"></td>
									<td id="TD6_14"></td>
									<td id="TD6_15"></td>
									<td id="TD6_16" class="le"></td>
									<td id="TD6_17"></td>
									<td id="TD6_18"></td>
									<td id="TD6_19" class="le"></td>
									<td id="TD6_20"></td>
									<td id="TD6_21"></td>
								</tr>
								<tr id="TI_TR">
									<td id="TI_TITLE" style="text-align:left;"></td>
									<td id="TD7_1" class="le"></td>
									<td id="TD7_2"></td>
									<td id="TD7_3"></td>
									<td id="TD7_4" class="le"></td>
									<td id="TD7_5"></td>
									<td id="TD7_6"></td>
									<td id="TD7_7" class="le"></td>
									<td id="TD7_8"></td>
									<td id="TD7_9"></td>
									<td id="TD7_10" class="le"></td>
									<td id="TD7_11"></td>
									<td id="TD7_12"></td>
									<td id="TD7_13" class="le"></td>
									<td id="TD7_14"></td>
									<td id="TD7_15"></td>
									<td id="TD7_16" class="le"></td>
									<td id="TD7_17"></td>
									<td id="TD7_18"></td>
									<td id="TD7_19" class="le"></td>
									<td id="TD7_20"></td>
									<td id="TD7_21"></td>
								</tr>
								<tr id="PMC_TR">
									<td id="PMC_TITLE" style="text-align:left;"></td>
									<td id="TD8_1" class="le"></td>
									<td id="TD8_2"></td>
									<td id="TD8_3"></td>
									<td id="TD8_4" class="le"></td>
									<td id="TD8_5"></td>
									<td id="TD8_6"></td>
									<td id="TD8_7" class="le"></td>
									<td id="TD8_8"></td>
									<td id="TD8_9"></td>
									<td id="TD8_10" class="le"></td>
									<td id="TD8_11"></td>
									<td id="TD8_12"></td>
									<td id="TD8_13" class="le"></td>
									<td id="TD8_14"></td>
									<td id="TD8_15"></td>
									<td id="TD8_16" class="le"></td>
									<td id="TD8_17"></td>
									<td id="TD8_18"></td>
									<td id="TD8_19" class="le"></td>
									<td id="TD8_20"></td>
									<td id="TD8_21"></td>
								</tr>
								<tr id="PMW_TR">
									<td id="PMW_TITLE" style="text-align:left;"></td>
									<td id="TD9_1" class="le"></td>
									<td id="TD9_2"></td>
									<td id="TD9_3"></td>
									<td id="TD9_4" class="le"></td>
									<td id="TD9_5"></td>
									<td id="TD9_6"></td>
									<td id="TD9_7" class="le"></td>
									<td id="TD9_8"></td>
									<td id="TD9_9"></td>
									<td id="TD9_10" class="le"></td>
									<td id="TD9_11"></td>
									<td id="TD9_12"></td>
									<td id="TD9_13" class="le"></td>
									<td id="TD9_14"></td>
									<td id="TD9_15"></td>
									<td id="TD9_16" class="le"></td>
									<td id="TD9_17"></td>
									<td id="TD9_18"></td>
									<td id="TD9_19" class="le"></td>
									<td id="TD9_20"></td>
									<td id="TD9_21"></td>
								</tr>
								<tr id="PMI_TR">
									<td id="PMI_TITLE" style="text-align:left;"></td>
									<td id="TD10_1" class="le"></td>
									<td id="TD10_2"></td>
									<td id="TD10_3"></td>
									<td id="TD10_4" class="le"></td>
									<td id="TD10_5"></td>
									<td id="TD10_6"></td>
									<td id="TD10_7" class="le"></td>
									<td id="TD10_8"></td>
									<td id="TD10_9"></td>
									<td id="TD10_10" class="le"></td>
									<td id="TD10_11"></td>
									<td id="TD10_12"></td>
									<td id="TD10_13" class="le"></td>
									<td id="TD10_14"></td>
									<td id="TD10_15"></td>
									<td id="TD10_16" class="le"></td>
									<td id="TD10_17"></td>
									<td id="TD10_18"></td>
									<td id="TD10_19" class="le"></td>
									<td id="TD10_20"></td>
									<td id="TD10_21"></td>
								</tr>
								<tr id="PMP_TR">
									<td id="PMP_TITLE" style="text-align:left;"></td>
									<td id="TD11_1" class="le"></td>
									<td id="TD11_2"></td>
									<td id="TD11_3"></td>
									<td id="TD11_4" class="le"></td>
									<td id="TD11_5"></td>
									<td id="TD11_6"></td>
									<td id="TD11_7" class="le"></td>
									<td id="TD11_8"></td>
									<td id="TD11_9"></td>
									<td id="TD11_10" class="le"></td>
									<td id="TD11_11"></td>
									<td id="TD11_12"></td>
									<td id="TD11_13" class="le"></td>
									<td id="TD11_14"></td>
									<td id="TD11_15"></td>
									<td id="TD11_16" class="le"></td>
									<td id="TD11_17"></td>
									<td id="TD11_18"></td>
									<td id="TD11_19" class="le"></td>
									<td id="TD11_20"></td>
									<td id="TD11_21"></td>
								</tr>
							</table>
							<div class="legend_tx"><span class="p"><strong>T</strong></span> : <bean:message key="LABEL.woTarget"/> &nbsp;&nbsp;&nbsp;<span class="c"><strong>C</strong></span> : <bean:message key="LABEL.woCompleted"/>&nbsp;&nbsp;&nbsp;<span class="s"><strong>P</strong></span> : <bean:message key="LABEL.woIncomplete"/></div>
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
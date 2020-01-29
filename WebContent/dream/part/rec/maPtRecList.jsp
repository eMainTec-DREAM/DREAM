<%--===========================================================================
구매입고 - 목록
author  ssong
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.rec.action.MaPtRecListAction" %>
<%@ page import="dream.part.rec.action.MaPtRecDetailAction" %>
<%@ page import="common.util.CommonUtil"%>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%
    User loginUser = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!-- 구매입고 -->
<title><bean:message key='MENU.PTREC'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;

/** 자동완성 변수 */
var deptAc;
var inspUserAc;
var vendorDescAc;
var recStatusAc;
var partNameAc;
var multiPartAc;
var plantAc;
var multPoAc;

function loadPage() 
{

	//설비작업현황 - 부품입고 팝업 시
	if(window.name=="CHART_PT_LIST_POPUP"){
		if(M$('maPtRecCommonDTO.prRecStatus').value!='')
		valSysDirCode('maPtRecCommonDTO.prRecStatus', 'maPtRecCommonDTO.prRecStatusDesc', 'PRRECLIST_STATUS','', true);
	}else{
		maPtRecListForm.elements['maPtRecCommonDTO.filterDeptId'].value   = loginUser.filterDeptId;
		maPtRecListForm.elements['maPtRecCommonDTO.filterDeptDesc'].value = loginUser.filterDeptDesc;
		
		maPtRecListForm.elements['maPtRecCommonDTO.filterRecStartDate'].value = getMinusMonth2(new Date(), -2); 
	    maPtRecListForm.elements['maPtRecCommonDTO.filterRecEndDate'].value   = getToday();
	  	
	    //공장명
	    if(loginUser.filterPlant!='null'){
	    	maPtRecListForm.elements['maPtRecCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
	    	maPtRecListForm.elements['maPtRecCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
		}
	}
	
    initGrid();
    
	// Button hide
    hideBtn("SAVE");
    hideBtn("EDITCNCL");
    
    deptAc = new autoC({"maPtRecCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    deptAc.setAcDConditionMap({
    	"plant":"maPtRecCommonDTO.filterPlantId"
  	});
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maPtRecCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.init();
    
    inspUserAc = new autoC({"maPtRecCommonDTO.filterInspectorName":"emp_name"});
    inspUserAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    inspUserAc.setTable("TAEMP");
    inspUserAc.setAcResultMap({
        "maPtRecCommonDTO.filterInspector":"emp_id"
    });
    inspUserAc.setAcDConditionMap({
    	"dept_id" : "maPtRecCommonDTO.filterDeptId"
    	,"plant"  : "maPtRecCommonDTO.filterPlantId"
    });
    inspUserAc.init();
    
    vendorDescAc = new autoC({"maPtRecCommonDTO.filterVendorDesc":"description"});
    vendorDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_use":"Y"
  	   });
    vendorDescAc.setTable("TAVENDOR");
    vendorDescAc.setAcResultMap({
        "maPtRecCommonDTO.filterVendorId":"vendor_id"
    });
    vendorDescAc.init();
    
    acSysDesc("maPtRecCommonDTO.prRecStatusDesc","maPtRecCommonDTO.prRecStatus","PRRECLIST_STATUS");
    
    partNameAc = new autoC({"maPtRecCommonDTO.filterPartNameSize":"partNameSize"});
    partNameAc.setAcConditionMap({
	   "part_categ":"SPPT",
	   "comp_no":loginUser.compNo
	   });
    partNameAc.setTable("TAPARTS");
    partNameAc.init();
    
    multiPartAc = new autoC({"maPtRecDetailDTO.multiDesc":"full_desc"});
    multiPartAc.setTable("TAPARTS");
    multiPartAc.setAcConditionMap({
	   "comp_no":loginUser.compNo
	   ,"part_categ":"SPPT"
	   });
    multiPartAc.setAcResultMap({
	    "maPtRecDetailDTO.multiKey":"part_id"
	});
    multiPartAc.setMultiSelect(true);
    multiPartAc.init();
    
    // 공장
    plantAc = new autoC({"maPtRecCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "maPtRecCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
    //발주 
    multPoAc = new autoC({"maPtRecDetailDTO.poitemId":"POITEMID"});
    multPoAc.setTable("TAPOITEMLIST");
    multPoAc.setAcConditionMap({
	   "comp_no":loginUser.compNo
	});
    multPoAc.setAcResultMap({
    	"maPtRecDetailDTO.poitemId":"POITEMID"
    });
    multPoAc.setMultiSelect(true);
    multPoAc.init();
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
    	maPtRecListForm.elements['maPtRecCommonDTO.prRecListId'].value = "";
        return sortColumn("maPtRecList", this, maPtRecListForm, "PRRECLISTID", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maPtRecList.do";

    maPtRecListForm.elements['strutsAction'].value = '<%=MaPtRecListAction.PTREC_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPtRecListForm), "PRRECLISTID", "Y");
    
    for(var i = 0; myGrid.getColumnsNum() > i; i++)
	{
		//console.log(i+"    "+grdObj.getColType(i));
		if(myGrid.getColType(i) == "ron") myGrid.setNumberFormat("0,000.00",i,".",",");
	}
    if(typeof exFindGridList == "function") exFindGridList();
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_prRecListId)
{
	maPtRecListForm.elements['maPtRecCommonDTO.prRecListId'].value = _prRecListId;
	findGridList('ReloadRow');
	maPtRecListForm.elements['maPtRecCommonDTO.prRecListId'].value = "";
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	maPtRecListForm.elements['maPtRecCommonDTO.prRecListId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search', '<%=MaPtRecListAction.PTREC_LIST_FIND%>');   
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
	var form = document.maPtRecListForm;
	 
	form.elements['maPtRecCommonDTO.prRecListId'].value = getValueById(myGrid, selectedId, 'prRecListId');
	goCommonTabPage(form, <%= MaPtRecDetailAction.PTREC_DETAIL_INIT %>, pageId);
}

function reloadTabPageAction(keyId)
{
	var form = document.maPtRecListForm;
	 
	form.elements['maPtRecCommonDTO.prRecListId'].value = keyId;
	goCommonTabPage(form, <%= MaPtRecDetailAction.PTREC_DETAIL_INIT %>, 'maPtRecDetail');
}


/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maPtRecDetail');	
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maPtRecListForm.elements['maPtRecCommonDTO.prRecListId'].value = getValueById(myGrid, selectedId, 'prRecListId');
    maPtRecListForm.elements['strutsAction'].value = '<%=MaPtRecDetailAction.PTREC_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maPtRecListForm), 'maPtRecDetail'); 
} 
 
/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maPtRecDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maPtRecListForm.elements['maPtRecCommonDTO.prRecListId'].value = "";
	goCommonTabPage(maPtRecListForm, '', pageId);
}

/**
  * 삭제
  */
function goDelete()
{
	// 입고완료 여부 체크 
	var cnt = 0;
	var checkRows = myGrid.getCheckedRows(getIndexById(myGrid, 'isDelCheck')).split(",");
	for(var i=0; i < checkRows.length; i++)
	{
		 var prRecListStatus = getValueById(myGrid, checkRows[i], 'prRecListStatus');
		 if(prRecListStatus == "C")
         {
			cnt++;
			myGrid.cells(checkRows[i], getIndexById(myGrid, 'isDelCheck')).setValue(0);
         }
	}
	
	if(cnt > 0)
	{
		//alertMessage1("입고완료된 데이터는 삭제되지 않습니다.");
		alertMessage1('<bean:message key="MESSAGE.MSG0153"/>');
		return;
	}
	
	// 삭제 시작
	var delArray = getDeletRows(myGrid, 'isDelCheck', 'prRecListId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	maPtRecListForm.strutsAction.value = '<%=MaPtRecListAction.PTREC_LIST_DELETE%>';
	var url = contextPath + "/maPtRecList.do";
	$.post(url,FormQueryString(maPtRecListForm)+delArray , function(_data){
    	afterDelete();
    });
}

function afterDelete()
{
	goClose('maPtRecDetail');
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	maPtRecListForm.elements['maPtRecCommonDTO.prRecListId'].value = "";
    excelServerAction("maPtRecList", maPtRecListForm );  
}

function goSave(){
	if(maPtRecListForm.strutsAction.value == '<%=MaPtRecListAction.PTREC_LIST_INPUT%>')
	{
		var url = contextPath + "/maPtRecList.do";
		
	    $.post(url,FormQueryString(maPtRecListForm), function(_data){
	    	afterSave(_data);
	    });
	}
	else
	{
		//Send All Data ONce
		proGrid.sendData();
	}
}

function afterSave(ajaxXmlDoc)
{
	if(maPtRecListForm.strutsAction.value == '<%=MaPtRecListAction.PTREC_LIST_INPUT%>')
	{
		//=====================================
	    if (!checkHttpXml(ajaxXmlDoc)) return;
	    //=====================================
	    getTopPage().afterSaveAll(currentPageId);
	    
	    goSearch();
	}
	else
	{
		// After Edit
		afterEditRow(myGrid);
		
		//Control Button
		hideBtn("SAVE");
		showBtn("EDIT");
		hideBtn("EDITCNCL");
		
		showBtn("SELECTORDER");
		showBtn("REGBATCH");
		showBtn("RECCOMPLETE");
		showBtn("PRINT");
		showBtn("OPEN");
		showBtn("CREATE");
		showBtn("DELETE");
		showBtn("EXCEL");
		showBtn("SETTING");
		
		//Clear Key Value
		maPtRecListForm.elements['maPtRecCommonDTO.prRecListId'].value = "";
		
		//Search
		goSearch();
	}
}

/**
 * 일괄등록
 */
function goRegbatch()
{
	multiPartAc.openLov();

	setForUpdate();
}

function setAcLovValue(rtnArr, code)
{
	if(code == 'maPtRecDetailDTO.multiDesc')
	{
		maPtRecListForm.strutsAction.value = '<%=MaPtRecListAction.PTREC_LIST_INPUT%>';
		maPtRecListForm.elements['maPtRecDetailDTO.recDate'].value = getToday();
		maPtRecListForm.elements['maPtRecDetailDTO.deptId'].value = loginUser.deptId;
	    maPtRecListForm.elements['maPtRecDetailDTO.inspector'].value = loginUser.empId;
	    maPtRecListForm.elements['maPtRecDetailDTO.wcodeId'].value = loginUser.wcodeId;
	    maPtRecListForm.elements['maPtRecDetailDTO.plantId'].value = loginUser.plant;
		maPtRecListForm.elements['maPtRecDetailDTO.recQty'].value = "1";
		maPtRecListForm.elements['maPtRecDetailDTO.prRecListStatus'].value = "W";
		maPtRecListForm.elements['maPtRecDetailDTO.isMakePartNoId'].value = "N";
		maPtRecListForm.elements['maPtRecDetailDTO.poitemId'].value = "";
		maPtRecListForm.elements['maPtRecDetailDTO.multiDesc'].value = JSON.stringify(rtnArr);
		
		goSaveAll();
	}
	else if(code == 'maPtRecDetailDTO.poitemId')
	{
		maPtRecListForm.strutsAction.value = '<%=MaPtRecListAction.PTREC_LIST_INPUT%>';
		
		maPtRecListForm.elements['maPtRecDetailDTO.recDate'].value = getToday();
		maPtRecListForm.elements['maPtRecDetailDTO.deptId'].value = loginUser.deptId;
	    maPtRecListForm.elements['maPtRecDetailDTO.inspector'].value = loginUser.empId;
	    maPtRecListForm.elements['maPtRecDetailDTO.wcodeId'].value = loginUser.wcodeId;
	    maPtRecListForm.elements['maPtRecDetailDTO.plantId'].value = loginUser.plant;
		maPtRecListForm.elements['maPtRecDetailDTO.recQty'].value = "1";
		maPtRecListForm.elements['maPtRecDetailDTO.prRecListStatus'].value = "W";
		maPtRecListForm.elements['maPtRecDetailDTO.isMakePartNoId'].value = "N";
		maPtRecListForm.elements['maPtRecDetailDTO.multiDesc'].value = "";
		maPtRecListForm.elements['maPtRecDetailDTO.poitemId'].value = JSON.stringify(rtnArr);
		
		goSaveAll();
	}
}

function goReccomplete(){
	// 체크 리스트
	var selArray1 = getSelectRows(myGrid, 'isDelCheck', 'PRRECLISTID');
	
	//체크된 것이 하나도 없으면 리턴
	if(typeof selArray1 == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	dhtmlx.confirm('<bean:message key="MESSAGE.MSG045"/>', function(result){
		if(result){
			setModal('<bean:message key="MESSAGE.MSG0083"/>');
			maPtRecListForm.strutsAction.value = '<%=MaPtRecListAction.PTREC_LIST_REC_CONFIRM%>';
		 	var url = contextPath + "/maPtRecList.do";
		 	$.post(url,FormQueryString(maPtRecListForm)+selArray1 , function(_data){
		 		afterRecComplete();
		   });
		}
	});
}
function afterRecComplete(){
	closeModal();
	goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG044"/>');
}

/**
 * 레포트 출력
 */
function goPrint()
{
	var selArray = getSelectRows(myGrid, 'ISDELCHECK', 'PRRECLISTID'); //Grid, check box column seq, pk column seq
	
	if(typeof selArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	maPtRecListForm.strutsAction.value = '<%=MaPtRecListAction.PTREC_QR_INSERT%>';
	var url = contextPath + "/maPtRecList.do";
	$.post(url,FormQueryString(maPtRecListForm)+selArray+"&fileName=ptPrRecBarcode" , function(_data){
		startReportCall();
    });
}

function startReportCall ()
{
	reportCall('ptBarcode','ptPrRecBarcode', "<%=loginUser.getCompNo()%>", "<%=loginUser.getUserId()%>", "<%=loginUser.getLangId()%>",'ptPrRecBarcode');
}

/**
 * Make Editable Mode
 */
function goEdit()
{
	hideBtn("SELECTORDER");
	hideBtn("REGBATCH");
	hideBtn("RECCOMPLETE");
	hideBtn("PRINT");
	hideBtn("OPEN");
	hideBtn("CREATE");
	hideBtn("DELETE");
	hideBtn("EXCEL");
	hideBtn("SETTING");
	hideBtn("SELECTPO");
	
	editRow(myGrid);
	
    //Close Detail Page if it is open
    goClose("maPtRecDetail", this);

    //set for update mark.
    setForUpdate();
    
    showBtn("SAVE");
    hideBtn("EDIT");
    showBtn("EDITCNCL");
    
    //Set Grid as updatable
	var url = contextPath + "/maPtRecList.do";
	var stAct = "<%=MaPtRecListAction.EDIT_LIST_SAVE %>";
	//URL, StrutsAction, Grid Obj, Call Back Function, Validation Rule (not_empty)
	proGrid = setGridUpdate(url, stAct, myGrid, "afterSave");
	
	//change column type (acedp (Auto complete, LOV), aced(Auto complete only), ednum(Auto complete and number only))
	//거래처,수량,단가,재고등급
	
	// 거래처
	setColumnType(myGrid,"VENDORDESC","acedp"); //AC,EDIT,POPUP
	// 수량
	setColumnType(myGrid,"RECQTY","ednum"); //EDIT
	// 단가
	setColumnType(myGrid,"UNITPRICE","ednum"); //EDIT
	// 재고등급
	setColumnType(myGrid,"PARTGRADEDESC","acedp"); //AC,EDIT,POPUP
	// 품번생성
// 	setColumnType(myGrid,"MAKEPTMSTR","acedp"); //AC,EDIT,POPUP
	// 품번생성
	setColumnType(myGrid,"ISMAKEPARTNO","acedp"); //AC,EDIT,POPUP
	

	myGrid.attachEvent("onEditCell", function(stage,rId,cInd,nValue,oValue){
		
		// 입고수량, 입고단가 입력 시 입고 금액 계산
		if(stage == 2 && myGrid.getColumnId(cInd) == "RECQTY" )
	    {
	        var value = intToData(nValue) * intToData(getValueById(myGrid, rId, "UNITPRICE"));
	    	
	        setValueById(myGrid, rId, "TOTPRICE", value);
		}
	    else if(stage == 2 && myGrid.getColumnId(cInd) == "UNITPRICE" )
	    {
	        var value = intToData(nValue) * intToData(getValueById(myGrid, rId, "RECQTY"));
	    	
	        setValueById(myGrid, rId, "TOTPRICE", value);
		}

		// 부품이 이미 있다면 품번생성 컬럼 선택 안됨(품번 생성 안함)
		if(myGrid.getColumnId(cInd) == "ISMAKEPARTNO" )
		{
			if(getValueById(myGrid, rId, "PARTNO") != "" )	return false;
		}
		
	    return true;

	});
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
 * AC Setting, This is called when the column is changed to edit mode.
 */
function setGridAc(_gridObj, _cellObj)
{
	var assetAc = new autoC({"VENDORDESC":"description"});
	assetAc.setCol(_cellObj); //mandatory
	assetAc.setGrid(_gridObj); //mandatory
    assetAc.setTable("TAVENDOR");
    assetAc.setAcConditionMap({
        "comp_no":loginUser.compNo,
    	"is_use":"Y"
    });
    assetAc.setAcResultMap({
        "VENDORID":"vendor_id"
    });
    assetAc.setKeyName("VENDORID");
    assetAc.init();  

    acSysDesc("PARTGRADEDESC","PARTGRADE","PART_GRADE",true,_gridObj,_cellObj);
    
    acSysDesc("ISMAKEPARTNO","ISMAKEPARTNOID","IS_USE",true,_gridObj,_cellObj);
}

/*
 * 엑셀 업로드
 */
function goExluploadLink()
{
	var actionUrl = contextPath + "/maPtRecList.do";
    var param =  "&strutsAction=" + '<%= MaPtRecListAction.GET_DATA %>'
              +  "&" + FormQueryString(maPtRecListForm);
    XMLHttpPostVal(actionUrl, param, 'afterGoExlupload');
}

var dataArr;
function afterGoExlupload(ajaxXmlDoc)
{
	dataArr = '0';
	var data = parseXmlDoc(ajaxXmlDoc, 'DESC');
	var uploadTypeId = "";
	var uploadType = "";
	var tableName = "";
	
	data = data.toString();

	if(data != '0')
    {
		dataArr = data.split(',');
		
		uploadTypeId = dataArr[0];
		uploadType = dataArr[1];
		tableName = dataArr[2];
		
    }
		goExlupload(uploadTypeId, uploadType, tableName);
}

function goSelectpo() {
	multPoAc.openLov();
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maPtRecListForm.elements['maPtRecCommonDTO.prRecListId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPtRecList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maPtRecCommonDTO.prRecListId"/><!-- Key -->
<html:hidden property="maPtRecCommonDTO.filterDeptId"/>
<html:hidden property="maPtRecCommonDTO.filterInspector"/>
<html:hidden property="maPtRecCommonDTO.filterVendorId"/>
<html:hidden property="maPtRecCommonDTO.filterPlantId"/>
<html:hidden property="maPtRecCommonDTO.prRecStatus"/>
<html:hidden property="maPtRecDetailDTO.prRecListId"/>
<html:hidden property="maPtRecDetailDTO.prRecListNo"/>
<html:hidden property="maPtRecDetailDTO.recDate"/>
<html:hidden property="maPtRecDetailDTO.deptId"/>
<html:hidden property="maPtRecDetailDTO.inspector"/>
<html:hidden property="maPtRecDetailDTO.wcodeId"/>
<html:hidden property="maPtRecDetailDTO.recQty"/>
<html:hidden property="maPtRecDetailDTO.prRecListStatus"/>
<html:hidden property="maPtRecDetailDTO.isMakePartNoId"/>
<html:hidden property="maPtRecDetailDTO.plantId"/>
<html:hidden property="maPtRecDetailDTO.multiKey"/>
<html:hidden property="maPtRecDetailDTO.multiDesc"/>
<html:hidden property="maPtRecDetailDTO.poitemId"/>
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
                    <label><bean:message key="LABEL.manageDept"/></label>
                    <div class="input_box">
                        <html:text property="maPtRecCommonDTO.filterDeptDesc" tabindex="10"/>
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
				<div class="field">
					<label><bean:message key="LABEL.recDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
	                        <html:text property="maPtRecCommonDTO.filterRecStartDate" tabindex="20" />
	                        <p class="open_calendar"><span>날짜</span></p>
	                    </div>
	                    <div class="input_box input_carendar">
	                        <html:text property="maPtRecCommonDTO.filterRecEndDate" tabindex="30" />
	                        <p class="open_calendar"><span>날짜</span></p>
	                    </div>
	                </div>
				</div>
                <!-- 관리자(정) -->
                <div class="field">
                    <label><bean:message key="LABEL.inspector"/></label>
                    <div class="input_box">
                        <html:text property="maPtRecCommonDTO.filterInspectorName" tabindex="40"/>
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
				<div class="field">
					<label><bean:message key="LABEL.parts"/></label>
					<div class="input_box">
						<html:text property="maPtRecCommonDTO.filterPartNameSize" tabindex="50"/>
					</div>
				</div>
	            <div class="field">
	                <label><bean:message key="LABEL.recVendor"/></label>
	                <div class="input_box">
	                    <html:text property="maPtRecCommonDTO.filterVendorDesc" tabindex="60" />
	                    <p class="open_spop">
	                        <a>
	                            <span>조회</span>
	                        </a>
	                    </p>
	                </div>
	            </div>
				<!-- 입고상태  -->
				<div class="field">
					<label><bean:message key="LABEL.ptRecListStatus"/></label>
					<div class="input_box">
						<html:text property="maPtRecCommonDTO.prRecStatusDesc" tabindex="70"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 공장명  -->
				<div class="field">
				    <label><bean:message key="LABEL.plantDesc"/></label>
				    <div class="input_box">
				        <html:text property="maPtRecCommonDTO.filterPlantDesc" tabindex="80"/>
				        <p class="open_spop">
				            <a><span>조회</span></a>
				        </p>
				    </div>
				</div>
				<!-- 구매신청번호 -->
				<div class="field">
					<label><bean:message key="LABEL.buyReqNo"/></label>
					<div class="input_box">
						<html:text property="maPtRecCommonDTO.filterReqNo" tabindex="50"/>
					</div>
				</div>
				<!-- 발주번호 -->
				<div class="field">
					<label><bean:message key="LABEL.orderNo"/></label>
					<div class="input_box">
						<html:text property="maPtRecCommonDTO.filterPoNo" tabindex="50"/>
					</div>
				</div>
				<c:set var="filePath" value="enhance/${compName}/part/rec/maPtRecList_${compNo}.jsp" />
				<c:if test="${udf:isExist(filePath)}">
					<c:import charEncoding="UTF-8" url="/enhance/${compName}/part/rec/maPtRecList_${compNo}.jsp"></c:import>
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
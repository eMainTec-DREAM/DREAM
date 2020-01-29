<%--===========================================================================
부품수리 - 목록
author  ssong
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.rep.action.MaPtRepListAction" %>
<%@ page import="dream.part.rep.action.MaPtRepDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 부품수리 -->
<title><bean:message key='MENU.PTREP'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;

/** 자동완성 변수 */
var deptAc;
var mainMngAc;
var partNameAc;
var warehouseAc;
var equipAc;
var multiPartAc;
var plantAc;
function loadPage() 
{
	//설비작업현황 - 부품입고 팝업 시
	if(window.name=="CHART_PT_LIST_POPUP"){
		if(M$('maPtRepCommonDTO.ptRepStatus').value!='')
			valSysDirCode('maPtRepCommonDTO.ptRepStatus', 'maPtRepCommonDTO.ptRepStatusDesc', 'REPAIR_STATUS','', true);
	}else{
		maPtRepListForm.elements['maPtRepCommonDTO.filterDeptId'].value   = loginUser.filterDeptId;
		maPtRepListForm.elements['maPtRepCommonDTO.filterDeptDesc'].value = loginUser.filterDeptDesc;
		/*
		maPtRepListForm.elements['maPtRepCommonDTO.filterInspector'].value     = loginUser.filterEmpId;
		maPtRepListForm.elements['maPtRepCommonDTO.filterInspectorName'].value = loginUser.filterEmpDesc;
		*/   
		maPtRepListForm.elements['maPtRepCommonDTO.filterRegStartDate'].value = getMinusMonth2(new Date(), -1); 
		maPtRepListForm.elements['maPtRepCommonDTO.filterRegEndDate'].value   = getToday();
		
        //공장명
        maPtRepListForm.elements['maPtRepCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
        maPtRepListForm.elements['maPtRepCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
		
	}
	 
    initGrid();
    
    deptAc = new autoC({"maPtRepCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    deptAc.setAcDConditionMap({
    	"plant" : "maPtRepCommonDTO.filterPlantId"
    });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maPtRepCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.init();
    
    mainMngAc = new autoC({"maPtRepCommonDTO.filterInspectorName":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maPtRepCommonDTO.filterDeptId"
    	,"plant" : "maPtRepCommonDTO.filterPlantId"
    });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maWoResultMstrCommonDTO.filterInspector":"emp_id"
    });
    mainMngAc.init();
    
    acSysDesc("maPtRepCommonDTO.ptRepStatusDesc","maPtRepCommonDTO.ptRepStatus","REPAIR_STATUS");
    
    partNameAc = new autoC({"maPtRepCommonDTO.filterPartNameSize":"full_desc"});
    partNameAc.setAcConditionMap({
	   "part_categ":"SPPT",
	   "comp_no":loginUser.compNo
	   });
    partNameAc.setTable("TAPARTS");
    partNameAc.init();
    
    equipAc = new autoC({"maPtRepCommonDTO.filterEquipDesc":"description"});
	equipAc.setAcDisplay("DESCRIPTION");
	equipAc.setAcConditionMap({
		"comp_no":loginUser.compNo
	});
	equipAc.setAcDConditionMap({
    	"dept_id" : "maPtRepCommonDTO.filterDeptId"
    	,"plant" : "maPtRepCommonDTO.filterPlantId"
    });
	equipAc.setTable("TAEQUIPMENT");
	equipAc.setAcResultMap({
	    "maPtRepCommonDTO.filterEquipId":"equip_id"
	});
	equipAc.init();
	
	multiPartAc = new autoC({"maPtRepDetailDTO.multiDesc":"full_desc"});
    multiPartAc.setTable("TAPARTS");
    multiPartAc.setAcConditionMap({
	   "comp_no":loginUser.compNo
	   ,"part_categ":"SPPT"
	   });
    multiPartAc.setAcResultMap({
	    "maPtRepDetailDTO.multiKey":"part_id"
	});
    multiPartAc.setMultiSelect(true);
    multiPartAc.init();

    //공장
    plantAc = new autoC({"maPtRepCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    	, "is_use":"Y"
    });
    plantAc.setAcResultMap({
        "maPtRepCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
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
    	maPtRepListForm.elements['maPtRepCommonDTO.ptRepairListId'].value = "";
        return sortColumn("maPtRepList", this, maPtRepListForm, "PTREPAIRLISTID", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maPtRepList.do";

    maPtRepListForm.elements['strutsAction'].value = '<%=MaPtRepListAction.PTREP_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPtRepListForm), "PTREPAIRLISTID", "Y");
    
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_ptRepairListId)
{
	maPtRepListForm.elements['maPtRepCommonDTO.ptRepairListId'].value = _ptRepairListId;
	findGridList('ReloadRow');
	maPtRepListForm.elements['maPtRepCommonDTO.ptRepairListId'].value = "";
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	maPtRepListForm.elements['maPtRepCommonDTO.ptRepairListId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search', '<%=MaPtRepListAction.PTREP_LIST_FIND%>');   
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
	var form = document.maPtRepListForm;
	 
	form.elements['maPtRepCommonDTO.ptRepairListId'].value = getValueById(myGrid, selectedId, 'ptRepairListId');
	goCommonTabPage(form, <%= MaPtRepDetailAction.PTREP_DETAIL_INIT %>, pageId);
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('maPtRepDetail');	
}

 function goOpenAction()
 {
     var selectedId=myGrid.getSelectedRowId();
     if(selectedId == null) return;
     
     maPtRepListForm.elements['maPtRepCommonDTO.ptRepairListId'].value = getValueById(myGrid, selectedId, 'ptRepairListId');
     maPtRepListForm.elements['strutsAction'].value = '<%=MaPtRepDetailAction.PTREP_DETAIL_INIT%>';
     openQuickTabPage(FormQueryString(maPtRepListForm), 'maPtRepDetail'); 
 } 
 
/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maPtRepDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maPtRepListForm.elements['maPtRepCommonDTO.ptRepairListId'].value = "";
	goCommonTabPage(maPtRepListForm, '', pageId);
}

/**
  * 삭제
  */
function goDelete()
{
	var delArray = getDeletRows(myGrid, 'isDelCheck', 'ptRepairListId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	maPtRepListForm.strutsAction.value = '<%=MaPtRepListAction.PTREP_LIST_DELETE%>';
	var url = contextPath + "/maPtRepList.do";
	$.post(url,FormQueryString(maPtRepListForm)+delArray , function(_data){
    	afterDelete();
    });
}

function afterDelete()
{
	goClose('maPtRepDetail');
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	maPtRepListForm.elements['maPtRepCommonDTO.ptRepairListId'].value = "";
	excelServerAction("maPtRepList", maPtRepListForm );
}

function goSave(){
	var url = contextPath + "/maPtRepList.do";
	
    $.post(url,FormQueryString(maPtRepListForm), function(_data){
    	afterSave(_data);
    });
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    getTopPage().afterSaveAll(currentPageId);
    
    goSearch();
}

/**
 * 일괄등록
 */
function goRegbatch()
{
	multiPartAc.openLov();
}

function setAcLovValue(rtnArr, code)
{
	if(code == 'maPtRepDetailDTO.multiDesc')
	{
		maPtRepListForm.strutsAction.value = '<%=MaPtRepListAction.PTREP_LIST_INPUT%>';
		
		maPtRepListForm.elements['maPtRepDetailDTO.regDate'].value = getToday(); 
		maPtRepListForm.elements['maPtRepDetailDTO.repairDate'].value = getToday(); 
		maPtRepListForm.elements['maPtRepDetailDTO.deptId'].value = loginUser.deptId;
		maPtRepListForm.elements['maPtRepDetailDTO.inspector'].value = loginUser.empId;
		maPtRepListForm.elements['maPtRepDetailDTO.wcodeId'].value = loginUser.wcodeId;
		maPtRepListForm.elements['maPtRepDetailDTO.ptRepairListStatus'].value = "W"; 
		maPtRepListForm.elements['maPtRepDetailDTO.repairQty'].value = 1;
		
		maPtRepListForm.elements['maPtRepDetailDTO.multiDesc'].value = JSON.stringify(rtnArr);
		
		goSaveAll();
	}
}

//수리불가목록출력
function goRepairxpdfLink()
{
	var reportName = 'maPtRepList';
	var qrdName = 'maPtRepList';
 	var compNo = loginUser.compNo;
	var langId = loginUser.langId; 
	var filterPlantDesc = maPtRepListForm.elements['maPtRepCommonDTO.filterPlantDesc'].value
	var filterDeptDesc = maPtRepListForm.elements['maPtRepCommonDTO.filterDeptDesc'].value;
	var filterStartDate = maPtRepListForm.elements['maPtRepCommonDTO.filterStartDate'].value;
	var filterEndDate = maPtRepListForm.elements['maPtRepCommonDTO.filterEndDate'].value
	goRepairxpdf(reportName, qrdName, compNo, langId, filterPlantDesc, filterDeptDesc, dateToData(filterStartDate), dateToData(filterEndDate)); 
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maPtRepListForm.elements['maPtRepCommonDTO.ptRepairListId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPtRepList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maPtRepCommonDTO.ptRepairListId"/><!-- Key -->
<html:hidden property="maPtRepCommonDTO.filterDeptId"/>
<html:hidden property="maPtRepCommonDTO.filterInspector"/>
<html:hidden property="maPtRepCommonDTO.filterEquipId"/>
<html:hidden property="maPtRepCommonDTO.ptRepStatus"/>
<html:hidden property="maPtRepCommonDTO.filterPlantId"/>
<html:hidden property="maPtRepDetailDTO.regDate"/>
<html:hidden property="maPtRepDetailDTO.repairDate"/>
<html:hidden property="maPtRepDetailDTO.deptId"/>
<html:hidden property="maPtRepDetailDTO.inspector"/>
<html:hidden property="maPtRepDetailDTO.wcodeId"/>
<html:hidden property="maPtRepDetailDTO.ptRepairListStatus"/>
<html:hidden property="maPtRepDetailDTO.repairQty"/>
<html:hidden property="maPtRepDetailDTO.multiKey"/>
<html:hidden property="maPtRepDetailDTO.multiDesc"/>
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
                        <html:text property="maPtRepCommonDTO.filterDeptDesc" tabindex="10"/>
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
				<div class="field">
					<label><bean:message key="LABEL.regiDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
	                        <html:text property="maPtRepCommonDTO.filterRegStartDate" tabindex="20" />
	                        <p class="open_calendar"><span>날짜</span></p>
	                    </div>
	                    <div class="input_box input_carendar">
	                        <html:text property="maPtRepCommonDTO.filterRegEndDate" tabindex="30" />
	                        <p class="open_calendar"><span>날짜</span></p>
	                    </div>
	                </div>
				</div>
                <!-- 관리자(정) -->
                <div class="field">
                    <label><bean:message key="LABEL.inspector"/></label>
                    <div class="input_box">
                        <html:text property="maPtRepCommonDTO.filterInspectorName" tabindex="40"/>
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
				<div class="field">
					<label><bean:message key="LABEL.ptNameSize"/></label>
					<div class="input_box">
						<html:text property="maPtRepCommonDTO.filterPartNameSize" tabindex="50"/>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.repairRequestDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
	                        <html:text property="maPtRepCommonDTO.filterReqStartDate" tabindex="60" />
	                        <p class="open_calendar"><span>날짜</span></p>
	                    </div>
	                    <div class="input_box input_carendar">
	                        <html:text property="maPtRepCommonDTO.filterReqEndDate" tabindex="70" />
	                        <p class="open_calendar"><span>날짜</span></p>
	                    </div>
	                </div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.repairDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
	                        <html:text property="maPtRepCommonDTO.filterStartDate" tabindex="80" />
	                        <p class="open_calendar"><span>날짜</span></p>
	                    </div>
	                    <div class="input_box input_carendar">
	                        <html:text property="maPtRepCommonDTO.filterEndDate" tabindex="90" />
	                        <p class="open_calendar"><span>날짜</span></p>
	                    </div>
	                </div>
				</div>
				<!-- 수리상태  -->
				<div class="field">
					<label><bean:message key="LABEL.repairStatus"/></label>
					<div class="input_box">
						<html:text property="maPtRepCommonDTO.ptRepStatusDesc" tabindex="100" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 설비 -->
                <div class="field">
                     <label><bean:message key="LABEL.equipName"/></label>
                     <div class="input_box">
                        <html:text property="maPtRepCommonDTO.filterEquipDesc" tabindex="100"/>
                         <p class="open_spop"><a><span>조회</span></a></p>
                   </div>
                </div>
                <!-- 공장명  -->
				<div class="field">
				    <label><bean:message key="LABEL.plantDesc"/></label>
				    <div class="input_box">
				        <html:text property="maPtRepCommonDTO.filterPlantDesc" tabindex="110"/>
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
<%--===========================================================================
설비이력(과거) List
author  js.lee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.rpt.action.AssetRptWorkHistListAction" %>
<%@ page import="dream.asset.rpt.action.AssetRptWorkHistDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 설비이력(과거) -->
<title><bean:message key='MENU.EQHIST'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

/** 자동완성 변수 */
var equipDescAc;
var eqLocAc;
var eqCtgAc;
var deptAc;
var empAc;

function loadPage() 
{
    initGrid();
    
    //설비 자동완성
    equipDescAc = new autoC({"assetRptWorkHistCommonDTO.filterEquipDesc":"description"});
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipDescAc.setAcResultMap({
        "assetRptWorkHistCommonDTO.filterEquipId":"equip_id"
    });
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "assetRptWorkHistCommonDTO.filterEqLocId",
    	"eqctg_id" : "assetRptWorkHistCommonDTO.filterEqCtgId",
    	"dept_id" : "assetRptWorkHistCommonDTO.filterDeptId"
    });
    equipDescAc.init();
    
    //설비위치 자동완성
    eqLocAc = new autoC({"assetRptWorkHistCommonDTO.filterEqLocDesc":"full_desc"});
    eqLocAc.setTable("TAEQLOC");
    eqLocAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    eqLocAc.setAcResultMap({
        "assetRptWorkHistCommonDTO.filterEqLocId":"eqloc_id"
    });
    eqLocAc.init();
    
    //설비종류 자동완성
    eqCtgAc = new autoC({"assetRptWorkHistCommonDTO.filterEqCtgDesc":"full_desc"});
    eqCtgAc.setTable("TAEQCTG");
    eqCtgAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   });
    eqCtgAc.setAcResultMap({
        "assetRptWorkHistCommonDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgAc.init();
    
    //담당부서 자동완성
    deptAc = new autoC({"assetRptWorkHistCommonDTO.filterDeptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "assetRptWorkHistCommonDTO.filterDeptId":"dept_id",
    });
    deptAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    }); 
    deptAc.init();
    //담당자 자동완성
    empAc = new autoC({"assetRptWorkHistCommonDTO.filterEmpDesc":"emp_name"});
    empAc.setTable("TAEMP");
    empAc.setAcResultMap({
        "assetRptWorkHistCommonDTO.filterEmpId":"emp_id",
    });
    empAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    }); 
    empAc.init();
    
    acSysDesc("assetRptWorkHistCommonDTO.filterWoTypeDesc","assetRptWorkHistCommonDTO.filterWoTypeId","WO_TYPE",true);
    
    acSysDesc("assetRptWorkHistCommonDTO.filterEqHistGenTypeDesc","assetRptWorkHistCommonDTO.filterEqHistGenTypeId","EQHIST_GEN_TYPE",true);
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
    	assetRptWorkHistListForm.elements['assetRptWorkHistCommonDTO.eqHistoryId'].value = "";
    	return sortColumn("assetRptWorkHistList", this, assetRptWorkHistListForm, "eqHistoryId", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/assetRptWorkHistList.do";
    assetRptWorkHistListForm.elements['strutsAction'].value = '<%=AssetRptWorkHistListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(assetRptWorkHistListForm), "eqHistoryId","Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_eqHistoryId)
{

	assetRptWorkHistListForm.elements['assetRptWorkHistCommonDTO.eqHistoryId'].value = _eqHistoryId;
	findGridList('ReloadRow');
	assetRptWorkHistListForm.elements['assetRptWorkHistCommonDTO.eqHistoryId'].value = "";
}

function goSearch()
{
	assetRptWorkHistListForm.elements['assetRptWorkHistCommonDTO.eqHistoryId'].value = "";	// 검색시 Tab 이동Key Clear
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
	assetRptWorkHistListForm.elements['assetRptWorkHistCommonDTO.eqHistoryId'].value =  getValueById(myGrid, selectedId,'EQHISTORYID');  
	goCommonTabPage(assetRptWorkHistListForm, <%= AssetRptWorkHistDetailAction.DETAIL_INIT %>, pageId);
} 

/**
 * 상세 열기
 */
function goOpen()
{
	goTabPage('assetRptWorkHistDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    assetRptWorkHistListForm.elements['assetRptWorkHistCommonDTO.eqHistoryId'].value  = getValueById(myGrid, selectedId, 'EQHISTORYID');
    assetRptWorkHistListForm.elements['strutsAction'].value = '<%=AssetRptWorkHistDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(assetRptWorkHistListForm), 'assetRptWorkHistDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "assetRptWorkHistDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	assetRptWorkHistListForm.elements['assetRptWorkHistCommonDTO.eqHistoryId'].value = "";
    goCommonTabPage(assetRptWorkHistListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var chkedRowsId = getCheckedRows(myGrid, 'isDelCheck');

    for(var i = 0 ;i < chkedRowsId.length; i++)
	{
		if(getValueById(myGrid, chkedRowsId[i], "EQHISTGENTYPEID") == "WORKORDER")
		{
			alertMessage1('<bean:message key="MESSAGE.MSG1007"/>');
			return;
		} 
	}
	
	var delArray = getDeletRows(myGrid, 'isDelCheck', 'eqHistoryId'); //Grid, check box column seq, pk column seq
    
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
    
	assetRptWorkHistListForm.strutsAction.value = '<%=AssetRptWorkHistListAction.LIST_DELETE%>';
    var url = contextPath + "/assetRptWorkHistList.do";
    
    $.post(url,FormQueryString(assetRptWorkHistListForm)+delArray , function(_data){
        afterDelete();
    });	
}

function afterDelete()
{
    goClose('assetRptWorkHistDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
// 	assetRptWorkHistListForm.elements['assetRptWorkHistCommonDTO.eqHistoryId'].value = "";
// 	excelServerAction("assetRptWorkHistList", assetRptWorkHistListForm );  
	excelAction(myGrid);
}
function goPdf(){
	var chkedRowsId = getCheckedRows(myGrid, 'ISDELCHECK');
	
	if(""== chkedRowsId || chkedRowsId == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	var checkEqHistoryIds = "";
	for(var i = 0 ;i < chkedRowsId.length; i++)
	{
		if(i==0){
			checkEqHistoryIds += ""+getValueById(myGrid, chkedRowsId[i], "EQHISTORYID");
		}else{
			checkEqHistoryIds += ","+getValueById(myGrid, chkedRowsId[i], "EQHISTORYID");
		}
	}
	reportCall('assetRptWorkHistList','assetRptWorkHistList'
			,loginUser.compNo
			,loginUser.langId
			,checkEqHistoryIds);
}

function goPdfLink(){
	goPdf();
} 

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assetRptWorkHistList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="assetRptWorkHistCommonDTO.eqHistoryId"/>		<!-- Key -->
<html:hidden property="assetRptWorkHistCommonDTO.filterEquipId"/>
<html:hidden property="assetRptWorkHistCommonDTO.filterEqLocId"/>
<html:hidden property="assetRptWorkHistCommonDTO.filterEqCtgId"/>
<html:hidden property="assetRptWorkHistCommonDTO.filterDeptId"/>
<html:hidden property="assetRptWorkHistCommonDTO.filterEmpId"/>
<html:hidden property="assetRptWorkHistCommonDTO.filterWoTypeId"/>
<html:hidden property="assetRptWorkHistCommonDTO.filterEqHistGenTypeId"/>
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
				<!-- 작업일자 -->
				<div class="field">
					<label><bean:message key="LABEL.wkorDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="assetRptWorkHistCommonDTO.filterStartDate" tabindex="10" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="assetRptWorkHistCommonDTO.filterEndDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 설비 -->
				<div class="field">
					<label><bean:message key="LABEL.equipDesc"/></label>
					<div class="input_box">
						<html:text property="assetRptWorkHistCommonDTO.filterEquipDesc" tabindex="25"/>
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
						<html:text property="assetRptWorkHistCommonDTO.filterEqLocDesc" tabindex="30"/>
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
						<html:text property="assetRptWorkHistCommonDTO.filterEqCtgDesc" tabindex="40"/>
						<p class="open_spop">
							<a>
							 <span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 담당부서 -->
				<div class="field">
					<label><bean:message key="LABEL.manageDept"/></label>
					<div class="input_box">
						<html:text property="assetRptWorkHistCommonDTO.filterDeptDesc" tabindex="50"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 담당자 -->
				<div class="field">
					<label><bean:message key="LABEL.manager"/></label>
					<div class="input_box">
						<html:text property="assetRptWorkHistCommonDTO.filterEmpDesc" tabindex="60"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 고장원인 -->
				<div class="field">
					<label><bean:message key="LABEL.caCd"/></label>
					<div class="input_box">
						<html:text property="assetRptWorkHistCommonDTO.filterCaCdDesc" tabindex="70" />
					</div>
				</div>
				<!-- 고장조치 -->
				<div class="field">
					<label><bean:message key="LABEL.reCd"/></label>
					<div class="input_box">
						<html:text property="assetRptWorkHistCommonDTO.filterReCdDesc" tabindex="80" />
					</div>
				</div>
				<!-- 작업명 -->
				<div class="field">
					<label><bean:message key="LABEL.pmDesc"/></label>
					<div class="input_box">
						<html:text property="assetRptWorkHistCommonDTO.filterWkOrDesc" tabindex="90" />
					</div>
				</div>
				<!-- WO# -->
				<div class="field">
					<label><bean:message key="LABEL.woNo"/></label>
					<div class="input_box">
						<html:text property="assetRptWorkHistCommonDTO.filterWoNo" tabindex="100"/>
					</div>
				</div>
				<!-- 작업종류 -->
				<div class="field">
					<label><bean:message key="LABEL.woType"/></label>
					<div class="input_box">
						<html:text property="assetRptWorkHistCommonDTO.filterWoTypeDesc" tabindex="50"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 설비이력발생구분 -->
				<div class="field">
					<label><bean:message key="LABEL.woType"/></label>
					<div class="input_box">
						<html:text property="assetRptWorkHistCommonDTO.filterEqHistGenTypeDesc" tabindex="50"/>
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
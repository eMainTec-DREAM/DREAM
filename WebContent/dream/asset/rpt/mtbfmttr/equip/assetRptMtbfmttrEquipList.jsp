<%--===========================================================================
MTBF,MTTR(설비)
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.rpt.mtbfmttr.equip.action.AssetRptMtbfmttrEquipListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<style type="text/css">

.dhx_axis_item_x {
	padding-top:2px;
	-webkit-transform-origin: 0 0;
    -moz-transform-origin: 0 0;
         -o-transform-origin: 0 0;
            transform-origin: 0 0;
    -webkit-transform: rotate(-20deg);
       -moz-transform: rotate(-20deg);
         -o-transform: rotate(-20deg);
            transform: rotate(-20deg);
    margin-top:25px;  
}

</style>

<head>
<!-- MTBF,MTTR(설비) -->
<title><bean:message key='MENU.EQMTBFMTTR'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var eqLocAc;
var deptAc;
var eqCtgAc;
var plantAc;
var equipAc, uDeptAc;
var mainMngAc,subMngAc,eqGRade;
var selectedRowId;

function loadPage() 
{
	var startDate = assetRptMtbfmttrEquipListForm.elements['assetRptMtbfmttrEquipCommonDTO.filterStartDate'].value;
	var endDate = assetRptMtbfmttrEquipListForm.elements['assetRptMtbfmttrEquipCommonDTO.filterEndDate'].value;
	if((startDate=="" || startDate==null)&&(endDate=="" || endDate==null)) {
		assetRptMtbfmttrEquipListForm.elements['assetRptMtbfmttrEquipCommonDTO.filterStartDate'].value = getYear()+"-01";
		assetRptMtbfmttrEquipListForm.elements['assetRptMtbfmttrEquipCommonDTO.filterEndDate'].value   = getYear()+"-"+(getMonth());
	}
	
	//공장명
    if(loginUser.filterPlant!='null'){
    	assetRptMtbfmttrEquipListForm.elements['assetRptMtbfmttrEquipCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	assetRptMtbfmttrEquipListForm.elements['assetRptMtbfmttrEquipCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
	
    /* if(loginUser.deptId!='null'){
    	assetRptMtbfmttrEquipListForm.elements['assetRptMtbfmttrEquipCommonDTO.filterUsageDept'].value  = loginUser.deptId;
    	assetRptMtbfmttrEquipListForm.elements['assetRptMtbfmttrEquipCommonDTO.filterUsageDeptDesc'].value  = loginUser.deptDesc;
	} */

	
	assetRptMtbfmttrEquipListForm.elements['assetRptMtbfmttrEquipCommonDTO.filterEqLocDesc'].value = assetRptMtbfmttrEquipListForm.elements['assetRptMtbfmttrEquipCommonDTO.filterEqLocDesc'].value.replace(/& gt;/gi,'>');
	
	initGrid();
	initChart();
	
	eqLocAc = new autoC({"assetRptMtbfmttrEquipCommonDTO.filterEqLocDesc":"full_desc"});
    eqLocAc.setTable("TAEQLOC");
    eqLocAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   });
    eqLocAc.setAcResultMap({
        "assetRptMtbfmttrEquipCommonDTO.filterEqLocId":"eqloc_id"
    });
    eqLocAc.setAcDConditionMap({
    	"plant" : "assetRptMtbfmttrEquipCommonDTO.filterPlantId"
    });
    eqLocAc.init();
    
    deptAc = new autoC({"assetRptMtbfmttrEquipCommonDTO.filterDeptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   });
    deptAc.setAcResultMap({
        "assetRptMtbfmttrEquipCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "assetRptMtbfmttrEquipCommonDTO.filterPlantId"
    });
    deptAc.init();
    
    
    uDeptAc = new autoC({"assetRptMtbfmttrEquipCommonDTO.filterUsageDeptDesc":"description"});
    uDeptAc.setTable("TADEPT");
    uDeptAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   });
    uDeptAc.setAcResultMap({
        "assetRptMtbfmttrEquipCommonDTO.filterUsageDept":"dept_id"
    });
    uDeptAc.setAcDConditionMap({
    	"plant" : "assetRptMtbfmttrEquipCommonDTO.filterPlantId"
    });
    uDeptAc.init();
    
    
    eqCtgAc = new autoC({"assetRptMtbfmttrEquipCommonDTO.filterEqCtgDesc":"description"});
    eqCtgAc.setTable("TAEQCTG");
    eqCtgAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   });
    eqCtgAc.setAcResultMap({
    	"assetRptMtbfmttrEquipCommonDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgAc.init();
    
 	// 공장코드
	plantAc = new autoC({"assetRptMtbfmttrEquipCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "assetRptMtbfmttrEquipCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
 	// 설비
	equipAc = new autoC({"assetRptMtbfmttrEquipCommonDTO.filterEquipDesc":"description"});
	equipAc.setTable("TAEQUIPMENT");
	equipAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
	equipAc.setAcResultMap({
        "assetRptMtbfmttrEquipCommonDTO.filterEquipId":"equip_id"
        ,"assetRptMtbfmttrEquipCommonDTO.filterEquipNo":"item_no"
    });
	equipAc.setAcDConditionMap({
    	"eqloc_id" : "assetRptMtbfmttrEquipCommonDTO.filterEqLocId",
    	"eqctg_id" : "assetRptMtbfmttrEquipCommonDTO.filterEqCtgId",
    	"dept_id" : "assetRptMtbfmttrEquipCommonDTO.filterDeptId",
    	"plant" : "assetRptMtbfmttrEquipCommonDTO.filterPlantId"
    });
	equipAc.init();
	
	//담당자(쩡)
    mainMngAc = new autoC({"assetRptMtbfmttrEquipCommonDTO.filterMainMngName":"emp_name"});
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    mainMngAc.setAcDConditionMap({
    	"plant" : "assetRptMtbfmttrEquipCommonDTO.filterPlantId"
    });
    mainMngAc.setAcResultMap({
        "assetRptMtbfmttrEquipCommonDTO.filterMainMngId":"emp_id"
    });
    mainMngAc.init();
	
    //담당자(부)
    subMngAc = new autoC({"assetRptMtbfmttrEquipCommonDTO.filterSubMngName":"emp_name"});
    subMngAc.setTable("TAEMP");
    subMngAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    subMngAc.setAcDConditionMap({
    	"plant" : "assetRptMtbfmttrEquipCommonDTO.filterPlantId"
    });
    subMngAc.setAcResultMap({
        "assetRptMtbfmttrEquipCommonDTO.filterSubMngId":"emp_id"
    });
    subMngAc.init();
    
    //-----------------------------------------------설비등급------------------------------------------------
    eqGRade = new autoC({"assetRptMtbfmttrEquipCommonDTO.filterEqGradeDesc":"description"});
    eqGRade.setAcDisplay("DESCRIPTION");
    eqGRade.setAcConditionMap({
        	"list_type":"EQ_GRADE"
        	,"param2":"EQUIP"
  	   });
    eqGRade.setTable("TACDSYSD");
    eqGRade.setAcResultMap({
        "assetRptMtbfmttrEquipCommonDTO.filterEqGrade":"cdsysd_no"
    });
    eqGRade.init();
    
}

var myGrid;
var myMtbfChart;
var myMttrChart;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		selectedRowId = rowId;
		goOpen();
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
   		return sortColumn("assetRptMtbfmttrEquipList", this, assetRptMtbfmttrEquipListForm, "ITEMNO", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

function initChart(){
	myMtbfChart =  new dhtmlXChart({
		view:"bar",
		container:"mtbfchartbox",
		value:"#MTBF#",
		color: "#66ccff",
		gradient:"rising",
		label:function(val){
			return setNumberFormat(val.MTBF);
		},
		tooltip:{
			template:function(val){
				return val.ITEMDESC +" ("+ setNumberFormat(val.MTBF) +")";	
			}
		},
		xAxis:{
			template:"'#ITEMDESC#"
		},
		yAxis:{}
	});
	
	myMttrChart =  new dhtmlXChart({
		view:"bar",
		container:"mttrchartbox",
		value:"#MTTR#",
		color: "#66ccff",
		gradient:"rising",
		label:function(val){
			return setNumberFormat(val.MTTR);
		},
		tooltip:{
			template:function(val){
				return val.ITEMDESC +" ("+ setNumberFormat(val.MTTR) +")";	
			}
		},
		xAxis:{
			template:"'#ITEMDESC#"
		},
		yAxis:{}
	});

}

$(window).resize(function(){
	myMtbfChart.resize();
	myMttrChart.resize();
});

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	findChart();
	findGridList('Search');
    assetRptMtbfmttrEquipListForm.elements['assetRptMtbfmttrEquipDetailDTO.startDate'].value = assetRptMtbfmttrEquipListForm.elements['assetRptMtbfmttrEquipCommonDTO.filterStartDate'].value;
    assetRptMtbfmttrEquipListForm.elements['assetRptMtbfmttrEquipDetailDTO.endDate'].value = assetRptMtbfmttrEquipListForm.elements['assetRptMtbfmttrEquipCommonDTO.filterEndDate'].value;
}

function findChart()
{
	if(checkValidation()) return;
	
	var url = contextPath + "/assetRptMtbfmttrEquipList.do";
	var form = document.assetRptMtbfmttrEquipListForm;	
	form.strutsAction.value = '<%=AssetRptMtbfmttrEquipListAction.MTBF_EQUIP_CHART_FIND %>';
	
	$.post(url,FormQueryString(assetRptMtbfmttrEquipListForm), function(_data){
		myMtbfChart.clearAll();
		myMtbfChart.parse(_data,"json");
    });
	
	form.strutsAction.value = '<%=AssetRptMtbfmttrEquipListAction.MTTR_EQUIP_CHART_FIND %>';
	
	$.post(url,FormQueryString(assetRptMtbfmttrEquipListForm), function(_data){
		myMttrChart.clearAll();
		myMttrChart.parse(_data,"json");
    });
}

function findGridList(sheetAction)
{
	if(checkValidation()) return;
	
	var form = document.assetRptMtbfmttrEquipListForm;	
	form.strutsAction.value = '<%=AssetRptMtbfmttrEquipListAction.MTTRMTBF_EQUIP_LIST_FIND %>';
	
	var url = contextPath + "/assetRptMtbfmttrEquipList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(assetRptMtbfmttrEquipListForm), "ITEMNO", "Y");
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
	var form = document.assetRptMtbfmttrEquipListForm;
	
	form.elements['assetRptMtbfmttrEquipDetailDTO.equipId'].value = getValueById(myGrid, selectedId,'EQUIPID');
	form.elements['assetRptMtbfmttrEquipDetailDTO.itemDesc'].value = getValueById(myGrid, selectedId,'ITEMDESC');
	goCommonTabPage(form, '' , pageId);
}

/**
 * 상세열기
 */
function goOpen(){
	goTabPage('assetRptMtbfmttrEquipDetailList');
	
	goTabPage('assetRptMtbfmttrEquipDetailChart');
}

/**
 * 엑셀 다운.
 */
function goExcel()
{
	if(checkValidation()) return;
	excelServerAction("assetRptMtbfmttrEquipList", assetRptMtbfmttrEquipListForm);
}

/*
 * 고장이력 보기
 */
function goEqbmLink()
{
	if(typeof selectedRowId == "undefined" || "" == selectedRowId)
	{
		alertMessage1('<bean:message key="MESSAGE.MSG212"/>');
		return;
	}

	var equipId   = getValueById(myGrid, selectedRowId,'EQUIPID');
	var equipDesc = getValueById(myGrid, selectedRowId,'ITEMDESC');
	var fromDate = assetRptMtbfmttrEquipListForm.elements['assetRptMtbfmttrEquipCommonDTO.filterStartDate'].value.replace("-","")+"01";
	var toDate = assetRptMtbfmttrEquipListForm.elements['assetRptMtbfmttrEquipCommonDTO.filterEndDate'].value.replace("-","")+"31";
	
	goEqbmList('', '', equipId, equipDesc, fromDate, toDate);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assetRptMtbfmttrEquipList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="assetRptMtbfmttrEquipCommonDTO.filterEqLocId"/>
<html:hidden property="assetRptMtbfmttrEquipCommonDTO.filterDeptId"/>
<html:hidden property="assetRptMtbfmttrEquipCommonDTO.filterEqCtgId"/>
<html:hidden property="assetRptMtbfmttrEquipCommonDTO.filterPlantId"/>
<html:hidden property="assetRptMtbfmttrEquipCommonDTO.filterEquipId"/>
<html:hidden property="assetRptMtbfmttrEquipCommonDTO.filterEquipNo"/>
<html:hidden property="assetRptMtbfmttrEquipCommonDTO.filterUsageDept"/>
<html:hidden property="assetRptMtbfmttrEquipCommonDTO.filterEqGrade"/>


<html:hidden property="assetRptMtbfmttrEquipDetailDTO.equipId"/>
<html:hidden property="assetRptMtbfmttrEquipDetailDTO.itemDesc"/>
<html:hidden property="assetRptMtbfmttrEquipDetailDTO.startDate"/>
<html:hidden property="assetRptMtbfmttrEquipDetailDTO.endDate"/>

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
				<!-- 월 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.month"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="assetRptMtbfmttrEquipCommonDTO.filterStartDate" tabindex="10" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="assetRptMtbfmttrEquipCommonDTO.filterEndDate" tabindex="20" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 설비위치  -->
				<div class="field">
					<label><bean:message key="LABEL.location"/></label>
					<div class="input_box">
						<html:text property="assetRptMtbfmttrEquipCommonDTO.filterEqLocDesc" tabindex="30"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 부서  -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="assetRptMtbfmttrEquipCommonDTO.filterDeptDesc" tabindex="40"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 사용부서  -->
				<div class="field">
					<label><bean:message key="LABEL.usedDept"/></label>
					<div class="input_box">
						<html:text property="assetRptMtbfmttrEquipCommonDTO.filterUsageDeptDesc" tabindex="40"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 설비종류  -->
				<div class="field">
					<label><bean:message key="LABEL.eqCtg"/></label>
					<div class="input_box">
						<html:text property="assetRptMtbfmttrEquipCommonDTO.filterEqCtgDesc" tabindex="50"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
						<html:text property="assetRptMtbfmttrEquipCommonDTO.filterPlantDesc" tabindex="60" />
						<p class="open_spop"><a><span>조회</span></a>
                        </p>
                    </div>
                </div>
                <!-- 설비  -->
                <div class="field">
                    <label><bean:message key="LABEL.equipment"/></label>
                    <div class="input_box">
						<html:text property="assetRptMtbfmttrEquipCommonDTO.filterEquipDesc" tabindex="70" />
						<p class="open_spop"><a><span>조회</span></a>
                    </div>
                </div>
                <!-- 관리자(정)  -->
                <div class="field">
                    <label><bean:message key="LABEL.mainManager"/></label>
                    <div class="input_box">
						<html:text property="assetRptMtbfmttrEquipCommonDTO.filterMainMngName" tabindex="80" />
						<p class="open_spop"><a><span>조회</span></a>
                    </div>
                </div>
                <!-- 관리자(부)  -->
                <div class="field">
                    <label><bean:message key="LABEL.subManager"/></label>
                    <div class="input_box">
						<html:text property="assetRptMtbfmttrEquipCommonDTO.filterSubMngName" tabindex="90" />
						<p class="open_spop"><a><span>조회</span></a>
                    </div>
                </div>
                <!-- 관리자(부)  -->
                <div class="field">
                    <label><bean:message key="LABEL.eqGrade"/></label>
                    <div class="input_box">
						<html:text property="assetRptMtbfmttrEquipCommonDTO.filterEqGradeDesc" tabindex="90" />
						<p class="open_spop"><a><span>조회</span></a>
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
				<div class="stitle_tx"><bean:message key="LABEL.mtbfMttr"/></div>
			</div>
		</div><!--sheader_box end-->
		<div class="article_box">
			<div class="ch_title"><bean:message key='LABEL.mtbfHour'/> <bean:message key='LABEL.top10'/></div>
			<div class="grid_area">
				<div id="mtbfchartbox" style="width:100%;height:270px;background-color:white;"></div>
			</div>
		</div> 
		<div class="article_box">
			<div class="ch_title"><bean:message key='LABEL.mttrMin'/> <bean:message key='LABEL.top10'/></div>
			<div class="grid_area">
				<div id="mttrchartbox" style="width:100%;height:270px;background-color:white;"></div>
			</div>
		</div>
	 </div> <!--  End of section_wrap -->
	<div class="section_wrap">
		<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.mtbfMttr"/></div>
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
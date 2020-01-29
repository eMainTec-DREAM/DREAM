<%--===========================================================================
MTBF,MTTR(위치)
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.rpt.mtbfmttr.loc.action.AssetRptMtbfmttrLocListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- MTBF,MTTR(위치) -->
<title><bean:message key='MENU.LOCMTBFMTTR'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
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
<script language="javascript">
<!--//

var selectedEqLocId = "";
var selectedRowId;
var eqLocAc;

function loadPage() 
{
	assetRptMtbfmttrLocListForm.elements['assetRptMtbfmttrLocCommonDTO.filterStartDate'].value = getYear()+"-01";
	assetRptMtbfmttrLocListForm.elements['assetRptMtbfmttrLocCommonDTO.filterEndDate'].value   = getYear()+"-"+(getMonth());
	
	//공장명
    if(loginUser.filterPlant!='null'){
    	assetRptMtbfmttrLocListForm.elements['assetRptMtbfmttrLocCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	assetRptMtbfmttrLocListForm.elements['assetRptMtbfmttrLocCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
	
	initGrid();
	initChart();
    
	eqLocAc = new autoC({"assetRptMtbfmttrLocCommonDTO.filterEqLocDesc":"full_desc"});
    eqLocAc.setTable("TAEQLOC");
    eqLocAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   });
    eqLocAc.setAcResultMap({
        "assetRptMtbfmttrLocCommonDTO.filterEqLocId":"eqloc_id"
    });
    eqLocAc.setAcDConditionMap({
    	"plant" : "assetRptMtbfmttrLocCommonDTO.filterPlantId"
    });
    eqLocAc.init();
    
 	// 공장코드
	plantAc = new autoC({"assetRptMtbfmttrLocCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "assetRptMtbfmttrLocCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
    acSysDesc("assetRptMtbfmttrLocCommonDTO.filterEqLocLevelDesc","assetRptMtbfmttrLocCommonDTO.filterEqLocLevel","EQLOC_LVL_TYPE", true);
    
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
		selectedEqLocId = rowId;
		selectedRowId = rowId;
		goOpen();
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
   		return sortColumn("assetRptMtbfmttrLocList", this, assetRptMtbfmttrLocListForm, "EQLOCID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ 
		setCounter(grdObj,"gridbox");
		
		for(var i = 0; grdObj.getColumnsNum() > i; i++)
  		{
  			if(grdObj.getColType(i) == "ron") grdObj.setNumberFormat("0,000.00",i,".",",");
  		}
	}); myGrid.init();

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
			template:"#EQLOCLEAFDESC#"+" ("+"#MTBF#"+")"
		},
		xAxis:{
			template:"'#EQLOCLEAFDESC#"
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
			template:"#EQLOCLEAFDESC#"+" ("+"#MTTR#"+")"
		},
		xAxis:{
			template:"'#EQLOCLEAFDESC#"
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
    findGridList('Search');
    assetRptMtbfmttrLocListForm.elements['assetRptMtbfmttrLocDetailDTO.startDate'].value = assetRptMtbfmttrLocListForm.elements['assetRptMtbfmttrLocCommonDTO.filterStartDate'].value;
    assetRptMtbfmttrLocListForm.elements['assetRptMtbfmttrLocDetailDTO.endDate'].value = assetRptMtbfmttrLocListForm.elements['assetRptMtbfmttrLocCommonDTO.filterEndDate'].value;
}

function findGridList(sheetAction)
{
	if(checkValidation()) return;
	
	var form = document.assetRptMtbfmttrLocListForm;	
	form.strutsAction.value = '<%=AssetRptMtbfmttrLocListAction.MTTRMTBF_LOC_LIST_FIND %>';
	
	var url = contextPath + "/assetRptMtbfmttrLocList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(assetRptMtbfmttrLocListForm), "EQLOCID", "Y");
	
	$.post(url,FormQueryString(assetRptMtbfmttrLocListForm), function(_data){
		myMtbfChart.clearAll();
		myMttrChart.clearAll();
		myMtbfChart.parse(_data,"json");
		myMttrChart.parse(_data,"json");
    });
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
	var form = document.assetRptMtbfmttrLocListForm;
	
	form.elements['assetRptMtbfmttrLocDetailDTO.eqLocId'].value = getValueById(myGrid, selectedId,'EQLOCID');
	form.elements['assetRptMtbfmttrLocDetailDTO.eqLocDesc'].value = getValueById(myGrid, selectedId,'EQLOCDESC');
	
	goCommonTabPage(form, '' , pageId);
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('assetRptMtbfmttrLocDetailList');
	
	goTabPage('assetRptMtbfmttrLocDetailChart');
}

/**
 * 엑셀 다운.
 */
function goExcel()
{
	if(checkValidation()) return;
	excelServerAction("assetRptMtbfmttrLocList", assetRptMtbfmttrLocListForm);
}

/**
 * 설비별 보기
 */
function goEqlcc()
{
	var eqLocId = "";
	var eqLocDesc = "";
	var startDate = assetRptMtbfmttrLocListForm.elements['assetRptMtbfmttrLocDetailDTO.startDate'].value;
	var endDate = assetRptMtbfmttrLocListForm.elements['assetRptMtbfmttrLocDetailDTO.endDate'].value;
	
	if(!(selectedEqLocId == null||selectedEqLocId=='')){
		eqLocId = getValueById(myGrid, selectedEqLocId,'EQLOCID');
		eqLocDesc = getValueById(myGrid, selectedEqLocId,'EQLOCDESC');
	}
	
	var url   = contextPath + "/assetRptMtbfmttrEquipList.do";
	
	var popWidth = 1010;
	var popHeight = 640;
    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
	
	var param = "isDecoratorName=popupPage"+
				"&assetRptMtbfmttrEquipCommonDTO.strutsAction="+
				"&assetRptMtbfmttrEquipCommonDTO.filterStartDate="+startDate+
				"&assetRptMtbfmttrEquipCommonDTO.filterEndDate="+endDate+
				"&assetRptMtbfmttrEquipCommonDTO.filterEqLocId="+eqLocId+
				"&assetRptMtbfmttrEquipCommonDTO.filterEqLocDesc="+eqLocDesc; 
	//post 전송
	openWindowWithPost(url, "USE_PT_LIST_POPUP", param, pos);
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

	var eqlocId   = getValueById(myGrid, selectedRowId,'EQLOCID');
	var eqlocDesc = getValueById(myGrid, selectedRowId,'EQLOCDESC');
	
	var fromDate = assetRptMtbfmttrLocListForm.elements['assetRptMtbfmttrLocCommonDTO.filterStartDate'].value.replace("-","")+"01";
	var toDate = assetRptMtbfmttrLocListForm.elements['assetRptMtbfmttrLocCommonDTO.filterEndDate'].value.replace("-","")+"31";
	
	goEqbmList(eqlocId, eqlocDesc, '', '', fromDate, toDate);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assetRptMtbfmttrLocList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="assetRptMtbfmttrLocCommonDTO.filterEqLocId"/>
<html:hidden property="assetRptMtbfmttrLocCommonDTO.filterEqLocLevel"/>
<html:hidden property="assetRptMtbfmttrLocCommonDTO.filterPlantId"/>

<html:hidden property="assetRptMtbfmttrLocDetailDTO.eqLocId"/>
<html:hidden property="assetRptMtbfmttrLocDetailDTO.eqLocDesc"/>
<html:hidden property="assetRptMtbfmttrLocDetailDTO.startDate"/>
<html:hidden property="assetRptMtbfmttrLocDetailDTO.endDate"/>

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
							<html:text property="assetRptMtbfmttrLocCommonDTO.filterStartDate" tabindex="10" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="assetRptMtbfmttrLocCommonDTO.filterEndDate" tabindex="20" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 설비위치  -->
				<div class="field">
					<label><bean:message key="LABEL.location"/></label>
					<div class="input_box">
						<html:text property="assetRptMtbfmttrLocCommonDTO.filterEqLocDesc" tabindex="30"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 위치Level  -->
				<div class="field">
					<label><bean:message key="LABEL.locLevel"/></label>
					<div class="input_box">
						<html:text property="assetRptMtbfmttrLocCommonDTO.filterEqLocLevelDesc" tabindex="40"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
							<html:text property="assetRptMtbfmttrLocCommonDTO.filterPlantDesc"
								tabindex="50" />
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
				<div class="stitle_tx"><bean:message key="LABEL.mtbfMttr"/></div>
			</div>
		</div><!--sheader_box end-->
		<div class="article_box">
			<div class="ch_title"><bean:message key='LABEL.mtbfHour'/></div>
			<div class="grid_area">
				<div id="mtbfchartbox" style="width:100%;height:270px;background-color:white;"></div>
			</div>
		</div> 
		<div class="article_box">
			<div class="ch_title"><bean:message key='LABEL.mttrHour'/></div>
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
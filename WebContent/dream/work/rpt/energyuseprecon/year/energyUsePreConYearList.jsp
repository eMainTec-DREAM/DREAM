<%--===========================================================================
author  ghlee
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.energyuseprecon.year.action.EnergyUsePreConYearListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 에너지 사용현황 -->
<title><bean:message key='MENU.ENERGEUSAGEYEAR'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;
//chart
var myUsageChart;
var myPriceChart;

var plantAc;

function loadPage() 
{
	//년도 
	energyUsePreConYearListForm.elements['energyUsePreConYearCommonDTO.filterStartDate'].value = getYear();
    energyUsePreConYearListForm.elements['energyUsePreConYearCommonDTO.filterEndDate'].value = getYear();

    //공장 
	energyUsePreConYearListForm.elements['energyUsePreConYearCommonDTO.filterPlantId'].value = loginUser.filterPlant;
    energyUsePreConYearListForm.elements['energyUsePreConYearCommonDTO.filterPlantDesc'].value = loginUser.filterPlantDesc;
		
    //공장명 
    plantAc = new autoC({"energyUsePreConYearCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcResultMap({
        "energyUsePreConYearCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
    //항목 
    acSysDesc("energyUsePreConYearCommonDTO.filterCheckPointDesc","energyUsePreConYearCommonDTO.filterCheckPointType","CHECK_POINT_TYPE", true);
    
	initGrid();
	initChart();
	
}
/**
 * 그리드 초기화
 */
function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    myGrid.enableSmartRendering(true,500);
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	energyUsePreConYearListForm.elements['energyUsePreConYearCommonDTO.partId'].value = "";
        return sortColumn("energyUsePreConYearList", this, energyUsePreConYearListForm, "PLANTID", ind, direction);
    });
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
    myGrid.init();
    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 차트 초기화
 */
function initChart()
{
	myUsageChart =  new dhtmlXChart({
		view:"bar",
		container:"usagechartbox",
		value:"#TOTVALUE#",
		color: "#66ccff",
		gradient:"rising",
		tooltip:{
			template:"#TOTVALUE#"+"#UOM#"
		},
		xAxis:{
			template:"'#YEAR#"
		},
		yAxis:{}
	});
	
	myPriceChart =  new dhtmlXChart({
		view:"bar",
		container:"pricechartbox",
		value:"#TOTPRICE#",
		color: "#66ccff",
		gradient:"rising",
		tooltip:{
			template:"#TOTPRICE#"+"#PRICEUOM#"
		},
		xAxis:{
			template:"'#YEAR#"
		},
		yAxis:{}
	});
	
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/energyUsePreConYearList.do";
    energyUsePreConYearListForm.elements['strutsAction'].value = '<%=EnergyUsePreConYearListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(energyUsePreConYearListForm), "PLANTID", "Y");
    
    $.post(url,FormQueryString(energyUsePreConYearListForm), function(_data){
    	myUsageChart.clearAll();
    	myPriceChart.clearAll();
		myUsageChart.parse(_data,"json");
		myPriceChart.parse(_data,"json");
    });
}

function goSearch()
{
    if(checkValidation()) return;
    
    findGridList('Search');
    
}

/**
 * Excel Export
 */
function goExcel()
{
	if(checkValidation()) return;
	
   excelServerAction("energyUsePreConYearList", energyUsePreConYearListForm);
}

$(window).resize(function(){
	myUsageChart.resize();
	myPriceChart.resize();
});

//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/energyUsePreConYearList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="energyUsePreConYearCommonDTO.filterPlantId"/>
<html:hidden property="energyUsePreConYearCommonDTO.filterCheckPointType"/>

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
                <!-- 년 -->
                <div class="field">
                    <label class="check"><bean:message key="LABEL.year"/></label>
                    <div class="calendar_wrap">
                        <div class="input_box input_carendar">
                            <html:text property="energyUsePreConYearCommonDTO.filterStartDate" tabindex="10" />
                            <p class="open_year_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="energyUsePreConYearCommonDTO.filterEndDate" tabindex="20" />
                            <p class="open_year_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>
                <!-- 공장 -->
                <div class="field">
                    <label><bean:message key="LABEL.plant"/></label>
                    <div class="input_box">
                        <html:text property="energyUsePreConYearCommonDTO.filterPlantDesc" tabindex="30" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
	            <!-- 항목 -->
	            <div class="field">
	                <label class="check"><bean:message key="LABEL.prompt"/></label>
	                <div class="input_box">
	                    <html:text property="energyUsePreConYearCommonDTO.filterCheckPointDesc" tabindex="40" />
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
    <div class="section_wrap">
		<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.chart"/></div>
			</div>
		</div><!--sheader_box end-->
		<div class="article_box chbox">
			<div class="ch_title"><bean:message key='LABEL.totUsageYear'/></div>
			<div class="grid_area">
				<div id="usagechartbox" style="width:100%;height:270px;background-color:white;"></div>
			</div>
		</div> 
		<div class="article_box chbox">
			<div class="ch_title"><bean:message key='LABEL.totPriceYear'/></div>
			<div class="grid_area">
				<div id="pricechartbox" style="width:100%;height:270px;background-color:white;"></div>
			</div>
		</div>
	 </div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>


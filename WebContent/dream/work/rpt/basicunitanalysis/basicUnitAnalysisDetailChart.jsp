<%--===========================================================================
원단위분석 차트
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.basicunitanalysis.action.BasicUnitAnalysisDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 원단위분석 차트 -->
<title><bean:message key=''/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	initChart();
    setTitle("basicUnitAnalysisDetailDTO.plantDesc","basicUnitAnalysisDetailDTO.stdCheckPointDesc");
}

var rvLineChart;
var buLineChart;
/**
 * 차트
 */
function initChart(){
	rvLineChart =  new dhtmlXChart({
		view:"spline",
		container:"RV_chartbox",
		value:"#RESULTVALUE#",
		item:{
			borderColor: "#ffffff",
			color: "#639eb0"
		},
		line:{
			color:"#639eb0",
			width:2
		},
		tooltip:{
			template:"#WKORDATE#"
		},
		offset:0,
		xAxis:{
			template:"'#WKORDATE#"
		},
		yAxis:{
		},
		padding:{
			left:35,
			bottom: 50
		},
		origin:0
	});
	
	buLineChart =  new dhtmlXChart({
		view:"spline",
		container:"BU_chartbox",
		value:"#BASICUNIT#",
		item:{
			borderColor: "#ffffff",
			color: "#639eb0"
		},
		line:{
			color:"#639eb0",
			width:2
		},
		tooltip:{
			template:"#WKORDATE#"
		},
		offset:0,
		xAxis:{
			template:"'#WKORDATE#"
		},
		yAxis:{
		},
		padding:{
			left:35,
			bottom: 50
		},
		origin:0
	});
	
	findChart();

}

function findChart(idx,plant,line){
	var url = contextPath + "/basicUnitAnalysisDetailList.do";
	var form = document.basicUnitAnalysisDetailForm;	
	form.strutsAction.value = '<%=BasicUnitAnalysisDetailAction.BASIC_UNIT_ANALYSIS_DETAIL_FIND %>';
 	
    $.post(url,FormQueryString(basicUnitAnalysisDetailForm), function(_data){
    	rvLineChart.parse(_data,"json");
    	buLineChart.parse(_data,"json");
    });
}

$(window).resize(function(){
	rvLineChart.resize();
	buLineChart.resize();
});

</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/basicUnitAnalysisDetailList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="basicUnitAnalysisDetailDTO.plantId"/>
<html:hidden property="basicUnitAnalysisDetailDTO.plantDesc"/>
<html:hidden property="basicUnitAnalysisDetailDTO.stdCheckPointDesc"/>
<html:hidden property="basicUnitAnalysisDetailDTO.startDate"/>
<html:hidden property="basicUnitAnalysisDetailDTO.endDate"/>

    <!-- searchbox 박스 Line -->

<div class="article_box chbox">
        <div class="ch_title"><bean:message key='LABEL.resultVal'/></div>
        <div class="grid_area">
            <div id="RV_chartbox" style="width:100%;height:270px;background-color:white;"></div>
        </div>
    </div> 
    <div class="article_box chbox">
        <div class="ch_title"><bean:message key='LABEL.basicUnit'/></div>
        <div class="grid_area">
            <div id=BU_chartbox style="width:100%;height:270px;background-color:white;"></div>
        </div>
    </div>

</html:form> 
</body>
</html>
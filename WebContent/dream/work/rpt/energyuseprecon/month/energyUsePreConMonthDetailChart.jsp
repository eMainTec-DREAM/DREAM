<%--===========================================================================
author  ghlee
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.energyuseprecon.month.action.EnergyUsePreConMonthDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 에너지 사용현황 -->
<title><bean:message key='LABEL.monthly'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//chart
var myChart;

function loadPage() 
{
	setTitle("energyUsePreConMonthDetailDTO.plantDesc", "energyUsePreConMonthDetailDTO.checkPointDesc");
	
	initChart();
	
}
/**
 * 차트 초기화
 */
function initChart()
{
	myChart =  new dhtmlXChart({
		view:"line",
		container:"chartbox",
		value:"#TOTVALUE#",
		item:{
			borderColor: "#447900",
			color: "#69ba00"
		},
		line:{
			color:"#69ba00",
			width:2
		},
		tooltip:{
			template:"#MONTH#"
		},
		offset:0,
		xAxis:{
			template:"'#MONTH#"
		},
		yAxis:{
// 			start:0,
// 			step: 1000,
// 			end: 10000
		},
		padding:{
			left:35,
			bottom: 50
		},
		origin:0,
		legend:{
			values:[{text:"<bean:message key='LABEL.totUsage'/>"}],
			align:"right",
			valign:"middle",
			layout:"y",
			width: 100,
			margin: 8,
			marker:{
				type: "item"
			}
		}
	});
	
	findChart();
}

function findChart(){
	var url = contextPath + "/energyUsePreConMonthDetailChart.do";
	var form = document.energyUsePreConMonthListForm;	
	form.strutsAction.value = '<%=EnergyUsePreConMonthDetailAction.DETAIL_FIND %>';
 	
    $.post(url,FormQueryString(energyUsePreConMonthListForm), function(_data){
    	myChart.parse(_data,"json");
    });
}

$(window).resize(function(){
	myChart.resize();
});

//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/energyUsePreConMonthDetailChart.do">
<html:hidden property="strutsAction"/>
<html:hidden property="energyUsePreConMonthDetailDTO.plantId"/>
<html:hidden property="energyUsePreConMonthDetailDTO.checkPointType"/>
<html:hidden property="energyUsePreConMonthDetailDTO.plantDesc"/>
<html:hidden property="energyUsePreConMonthDetailDTO.checkPointDesc"/>
<html:hidden property="energyUsePreConMonthDetailDTO.startDate"/>
<html:hidden property="energyUsePreConMonthDetailDTO.endDate"/>

    <div class="article_box">
		<div class="grid_area">
			<div id="chartbox" style="width:100%;height:240px;background-color:white;"></div>
		</div>
	</div> 
	
</html:form> 
</body>
</html>


<%--===========================================================================
조직별 고장분석 고장 건 수 차트
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.org.bd.action.WorkRptOrgBdDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 조직별 고장분석 고장 건 수 차트 -->
<title><bean:message key='MENU.LCCByEqp'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	initChart();
	
	setTitle("workRptOrgBdDetailDTO.chartValue","workRptOrgBdDetailDTO.deptDesc");
}

var myLineChart;
/**
 * 차트
 */
function initChart(){
	var chartValue = workRptOrgBdDetailForm.elements['workRptOrgBdDetailDTO.chartValue'].value;
	var chartTitle = "";
	switch(chartValue) {
		case "BDCNT":
			chartTitle = "<bean:message key='LABEL.bdTimeFreq'/>";
			workRptOrgBdDetailForm.elements['workRptOrgBdDetailDTO.chartValue'].value = "<bean:message key='LABEL.bdTimeFreq'/>";
			break;
		case "WOTIMEHOUR":
			chartTitle = "<bean:message key='LABEL.woTimeHour'/>";
			workRptOrgBdDetailForm.elements['workRptOrgBdDetailDTO.chartValue'].value = "<bean:message key='LABEL.woTimeHour'/>";
			break;
		case "MAINTAMT":
			chartTitle = "<bean:message key='LABEL.maintAmt'/>";
			workRptOrgBdDetailForm.elements['workRptOrgBdDetailDTO.chartValue'].value = "<bean:message key='LABEL.maintAmt'/>";
			break;
		default:
			chartTitle = "";
			workRptOrgBdDetailForm.elements['workRptOrgBdDetailDTO.chartValue'].value = "";
			break;
	}
	myLineChart =  new dhtmlXChart({
		view:"line",
		container:"chartbox",
		value:"#"+chartValue+"#",
		item:{
			borderColor: "#447900",
			color: "#69ba00"
		},
		line:{
			color:"#69ba00",
			width:2
		},
		tooltip:{
			template:"#"+chartValue+"#"
		},
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
			values:[{text:chartTitle}],
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

function findChart(idx,plant,line){
	var url = contextPath + "/workRptOrgBdDetailChart.do";
	var form = document.workRptOrgBdDetailForm;	
	form.strutsAction.value = '<%=WorkRptOrgBdDetailAction.LCC_EQUIP_DETAIL_FIND %>';
 	
    $.post(url,FormQueryString(workRptOrgBdDetailForm), function(_data){
    	myLineChart.parse(_data,"json");
    });
}

$(window).resize(function(){
	myLineChart.resize();
});

</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workRptOrgBdDetailChart.do">
<html:hidden property="strutsAction"/>

<html:hidden property="workRptOrgBdDetailDTO.deptId"/>
<html:hidden property="workRptOrgBdDetailDTO.deptDesc"/>
<html:hidden property="workRptOrgBdDetailDTO.startDate"/>
<html:hidden property="workRptOrgBdDetailDTO.endDate"/>
<html:hidden property="workRptOrgBdDetailDTO.chartValue"/>
<html:hidden property="workRptOrgBdDetailDTO.plantId"/>
<html:hidden property="workRptOrgBdDetailDTO.plantDesc"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box">
		<div class="grid_area">
			<div id="chartbox" style="width:100%;height:240px;background-color:white;"></div>
		</div>
	</div> 

</html:form> 
</body>
</html>
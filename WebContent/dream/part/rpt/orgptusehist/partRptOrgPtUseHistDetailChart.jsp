<%--===========================================================================
author  youngjoo38
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.rpt.orgptusehist.action.PartRptOrgPtUseHistDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 조직별사용분석 -->
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
	initChart();
	setTitle("partRptOrgPtUseHistDetailDTO.chartValue", "partRptOrgPtUseHistDetailDTO.deptDesc");
}
/**
 * 차트 초기화
 */
function initChart()
{
	var form = document.partRptOrgPtUseHistListForm;	
	var chartValue = form.elements['partRptOrgPtUseHistDetailDTO.chartValue'].value;
	var chartTitle = "";
	
	switch(chartValue)
	{
		case "USEAMT" :
			chartTitle = "<bean:message key='LABEL.useAmt'/>";
			form.elements['partRptOrgPtUseHistDetailDTO.chartValue'].value	
			 		   = "<bean:message key='LABEL.useAmt'/>";
			break;
		case "USECNT" :
			chartTitle = "<bean:message key='LABEL.useQty'/>";
			form.elements['partRptOrgPtUseHistDetailDTO.chartValue'].value	
		 			   = "<bean:message key='LABEL.useQty'/>";
			break;
		default:
			chartTitle = "";
			form.elements['partRptOrgPtUseHistDetailDTO.chartValue'].value	
					   = "";
			break;
	}
	
	myChart =  new dhtmlXChart({
		view:"line",
		container:"chartbox",
		value:"#"+chartValue+"#",
		item:{
			borderColor: "#447900",
			color: "#4286f4",
			radius: 5
		},
		line:{
			color:"#4286f4",
			width:2
		},
		tooltip:{
			template:"#"+chartValue+"#"
		},
		offset:0,
		xAxis:{
			template:"'#MONTH#"
		},
		yAxis:{
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

function findChart(){
	var url = contextPath + "/partRptOrgPtUseHistDetailChart.do";
	var form = document.partRptOrgPtUseHistListForm;	
	form.strutsAction.value = '<%=PartRptOrgPtUseHistDetailAction.CHART_FIND %>';
 	
    $.post(url,FormQueryString(partRptOrgPtUseHistListForm), function(_data){
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
<html:form action="/partRptOrgPtUseHistDetailChart.do">
<html:hidden property="strutsAction"/>
<html:hidden property="partRptOrgPtUseHistDetailDTO.deptId"/>
<html:hidden property="partRptOrgPtUseHistDetailDTO.deptDesc"/>
<html:hidden property="partRptOrgPtUseHistDetailDTO.plantId"/>
<html:hidden property="partRptOrgPtUseHistDetailDTO.plantDesc"/>
<html:hidden property="partRptOrgPtUseHistDetailDTO.startYyyymm"/>
<html:hidden property="partRptOrgPtUseHistDetailDTO.endYyyymm"/>
<html:hidden property="partRptOrgPtUseHistDetailDTO.chartValue"/>

    <div class="article_box">
		<div class="grid_area">
			<div id="chartbox" style="width:100%;height:240px;background-color:white;"></div>
		</div>
	</div> 
	
</html:form> 
</body>
</html>


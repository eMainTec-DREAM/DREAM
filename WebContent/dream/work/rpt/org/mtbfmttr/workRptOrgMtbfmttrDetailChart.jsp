<%--===========================================================================
MTBF,MTTR 차트(설비)
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.org.mtbfmttr.action.WorkRptOrgMtbfmttrDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- MTBF,MTTR 차트(설비) -->
<title><bean:message key='MENU.EQMTBFMTTR'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	initChart();
	
	setTitle("workRptOrgMtbfmttrDetailDTO.deptDesc");
}

var myMtbfChart;
var myMttrChart;
/**
 * 차트
 */
function initChart(){
	myMtbfChart =  new dhtmlXChart({
		view:"spline",
		container:"mtbfchartbox",
		value:"#MTBFHOUR#",
		item:{
			borderColor: "#ffffff",
			color: "#000000"
		},
		line:{
			color:"#69ba00",
			width:3
		},
		tooltip:{
			template:"#MONTH#"+" ("+"#MTBFHOUR#"+")"
		},
		xAxis:{
			template:"'#MONTH#"
		},
		offset:0,
		yAxis:{
		}
	});
	
	myMttrChart =  new dhtmlXChart({
		view:"spline",
		container:"mttrchartbox",
		value:"#MTTRHOUR#",
		item:{
			borderColor: "#ffffff",
			color: "#000000"
		},
		line:{
			color:"#69ba00",
			width:3
		},
		tooltip:{
			template:"#MONTH#"+" ("+"#MTTRHOUR#"+")"
		},
		xAxis:{
			template:"'#MONTH#"
		},
		offset:0,
		yAxis:{
		}
	});
	
	findChart();

}

function findChart(idx,plant,line){
	var url = contextPath + "/workRptOrgMtbfmttrDetailChart.do";
	var form = document.workRptOrgMtbfmttrDetailForm;	
	form.strutsAction.value = '<%=WorkRptOrgMtbfmttrDetailAction.MTTRMTBF_EQUIP_DETAIL_FIND %>';
 	
    $.post(url,FormQueryString(workRptOrgMtbfmttrDetailForm), function(_data){
    	myMtbfChart.parse(_data,"json");
    	myMttrChart.parse(_data,"json");
    });
}

$(window).resize(function(){
	myMtbfChart.resize();
	myMttrChart.resize();
});

</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workRptOrgMtbfmttrDetailChart.do">
<html:hidden property="strutsAction"/>

<html:hidden property="workRptOrgMtbfmttrDetailDTO.deptId"/>
<html:hidden property="workRptOrgMtbfmttrDetailDTO.deptDesc"/>
<html:hidden property="workRptOrgMtbfmttrDetailDTO.startDate"/>
<html:hidden property="workRptOrgMtbfmttrDetailDTO.endDate"/>
<html:hidden property="workRptOrgMtbfmttrDetailDTO.plantId"/>
<html:hidden property="workRptOrgMtbfmttrDetailDTO.plantDesc"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box chbox">
		<div class="ch_title"><bean:message key='LABEL.mtbfHour'/></div>
		<div class="grid_area">
			<div id="mtbfchartbox" style="width:100%;height:270px;background-color:white;"></div>
		</div>
	</div> 
	<div class="article_box chbox">
		<div class="ch_title"><bean:message key='LABEL.mttrHour'/></div>
		<div class="grid_area">
			<div id="mttrchartbox" style="width:100%;height:270px;background-color:white;"></div>
		</div>
	</div>

</html:form> 
</body>
</html>
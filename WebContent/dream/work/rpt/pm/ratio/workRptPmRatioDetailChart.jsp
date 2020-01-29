<%--===========================================================================
계획보전율 차트(위치)
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.pm.ratio.action.WorkRptPmRatioDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 계획보전율 차트(위치) -->
<title><bean:message key='MENU.PMRATIO'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	workRptPmRatioDetailForm.elements['workRptPmRatioDetailDTO.eqLocDesc'].value = workRptPmRatioDetailForm.elements['workRptPmRatioDetailDTO.eqLocDesc'].value.replace(/& gt;/gi,'>');
	
	initChart();
	
	setTitle("workRptPmRatioDetailDTO.eqLocDesc");
}

var myPieChart;
/**
 * 차트
 */
function initChart(){
	var dataset = workRptPmRatioDetailForm.elements['workRptPmRatioDetailDTO.chartDataset'].value;
	myPieChart = new dhtmlXChart({
		view:"pie",
		container:"chartbox",
		value:"#value#",
		color:"#color#",
		label:"#key#",
		pieInnerText:"#value#",
		shadow:0
	});
	
	myPieChart.parse(dataset,"json");
	
}

$(window).resize(function(){
	myPieChart.resize();
});

</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workRptPmRatioDetailList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="workRptPmRatioDetailDTO.eqLocId"/>
<html:hidden property="workRptPmRatioDetailDTO.eqLocDesc"/>
<html:hidden property="workRptPmRatioDetailDTO.startDate"/>
<html:hidden property="workRptPmRatioDetailDTO.endDate"/>
<html:hidden property="workRptPmRatioDetailDTO.chartDataset"/>

    <!-- searchbox 박스 Line -->

    <div class="article_box chbox">
		<div class="grid_area">
			<div id="chartbox" style="width:100%;height:240px;background-color:white;"></div>
		</div>
	</div> 

</html:form> 
</body>
</html>
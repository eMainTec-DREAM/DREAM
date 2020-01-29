<%--===========================================================================
MTBF,MTTR 차트(위치)
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.rpt.mtbfmttr.loc.action.AssetRptMtbfmttrLocDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- MTBF,MTTR 차트(위치) -->
<title><bean:message key='MENU.LOCMTBFMTTR'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	assetRptMtbfmttrLocDetailForm.elements['assetRptMtbfmttrLocDetailDTO.eqLocDesc'].value = assetRptMtbfmttrLocDetailForm.elements['assetRptMtbfmttrLocDetailDTO.eqLocDesc'].value.replace(/& gt;/gi,'>');
	
	initChart();
	
	setTitle("assetRptMtbfmttrLocDetailDTO.eqLocDesc");
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
		value:"#MTBF#",
		item:{
			borderColor: "#ffffff",
			color: "#000000"
		},
		line:{
			color:"#ff9900",
			width:3
		},
		tooltip:{
			template:"#MONTH#"+" ("+"#MTBF#"+")"
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
		value:"#MTTR#",
		item:{
			borderColor: "#ffffff",
			color: "#000000"
		},
		line:{
			color:"#ff9900",
			width:3
		},
		tooltip:{
			template:"#MONTH#"+" ("+"#MTTR#"+")"
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
	var url = contextPath + "/assetRptMtbfmttrLocDetailChart.do";
	var form = document.assetRptMtbfmttrLocDetailForm;	
	form.strutsAction.value = '<%=AssetRptMtbfmttrLocDetailAction.MTTRMTBF_LOC_DETAIL_FIND %>';
 	
    $.post(url,FormQueryString(assetRptMtbfmttrLocDetailForm), function(_data){
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
<html:form action="/assetRptMtbfmttrLocDetailChart.do">
<html:hidden property="strutsAction"/>

<html:hidden property="assetRptMtbfmttrLocDetailDTO.eqLocId"/>
<html:hidden property="assetRptMtbfmttrLocDetailDTO.eqLocDesc"/>
<html:hidden property="assetRptMtbfmttrLocDetailDTO.startDate"/>
<html:hidden property="assetRptMtbfmttrLocDetailDTO.endDate"/>

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
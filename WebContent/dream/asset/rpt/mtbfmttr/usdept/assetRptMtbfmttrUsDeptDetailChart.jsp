<%--===========================================================================
MTBF,MTTR 차트(사용부서)
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.rpt.mtbfmttr.usdept.action.AssetRptMtbfmttrUsDeptDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- MTBF,MTTR 차트(사용부서) -->
<title><bean:message key='MENU.MTBFMTTRUSAGEDEPT'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	initChart();
	
	setTitle("assetRptMtbfmttrUsDeptDetailDTO.usageDeptDesc");
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
		label:function(val){
			return setNumberFormat(val.MTBF);
		},
		item:{
			borderColor: "#ffffff",
			color: "#000000"
		},
		line:{
			color:"#ff9900",
			width:3
		},
		tooltip:{
			template:function(val){
				return val.MONTH +" ("+ setNumberFormat(val.MTBF) +")";	
			}
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
		label:function(val){
			return setNumberFormat(val.MTTR);
		},
		item:{
			borderColor: "#ffffff",
			color: "#000000"
		},
		line:{
			color:"#ff9900",
			width:3
		},
		tooltip:{
			template:function(val){
				return val.MONTH +" ("+ setNumberFormat(val.MTTR) +")";	
			}
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
	var url = contextPath + "/assetRptMtbfmttrUsDeptDetailChart.do";
	var form = document.assetRptMtbfmttrUsDeptDetailForm;	
	form.strutsAction.value = '<%=AssetRptMtbfmttrUsDeptDetailAction.MTTRMTBF_EQUIP_DETAIL_FIND %>';
 	
    $.post(url,FormQueryString(assetRptMtbfmttrUsDeptDetailForm), function(_data){
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
<html:form action="/assetRptMtbfmttrUsDeptDetailChart.do">
<html:hidden property="strutsAction"/>

<html:hidden property="assetRptMtbfmttrUsDeptDetailDTO.plantId"/>
<html:hidden property="assetRptMtbfmttrUsDeptDetailDTO.plantDesc"/>
<html:hidden property="assetRptMtbfmttrUsDeptDetailDTO.usageDeptId"/>
<html:hidden property="assetRptMtbfmttrUsDeptDetailDTO.usageDeptDesc"/>
<html:hidden property="assetRptMtbfmttrUsDeptDetailDTO.startDate"/>
<html:hidden property="assetRptMtbfmttrUsDeptDetailDTO.endDate"/>

<html:hidden property="assetRptMtbfmttrUsDeptCommonDTO.filterEqGrade"/>
<html:hidden property="assetRptMtbfmttrUsDeptCommonDTO.filterEqGradeDesc"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box">
		<div class="ch_title"><bean:message key='LABEL.mtbfHour'/></div>
		<div class="grid_area">
			<div id="mtbfchartbox" style="width:100%;height:270px;background-color:white;"></div>
		</div>
	</div> 
	<div class="article_box">
		<div class="ch_title"><bean:message key='LABEL.mttrMin'/></div>
		<div class="grid_area">
			<div id="mttrchartbox" style="width:100%;height:270px;background-color:white;"></div>
		</div>
	</div>

</html:form> 
</body>
</html>
<%--===========================================================================
MTBF,MTTR 차트(설비)
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.rpt.mtbfmttr.equip.action.AssetRptMtbfmttrEquipDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<style type="text/css">

.dhx_axis_item_x {
	padding-top:2px;
	-webkit-transform-origin: 0 0;
    -moz-transform-origin: 0 0;
         -o-transform-origin: 0 0;
            transform-origin: 0 0;
    -webkit-transform: rotate(-30deg);
       -moz-transform: rotate(-30deg);
         -o-transform: rotate(-30deg);
            transform: rotate(-30deg);
    margin-top:25px;  
}

</style>
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
	
	setTitle("assetRptMtbfmttrEquipDetailDTO.itemDesc");
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
	var url = contextPath + "/assetRptMtbfmttrEquipDetailChart.do";
	var form = document.assetRptMtbfmttrEquipDetailForm;	
	form.strutsAction.value = '<%=AssetRptMtbfmttrEquipDetailAction.MTTRMTBF_EQUIP_DETAIL_FIND %>';
 	
    $.post(url,FormQueryString(assetRptMtbfmttrEquipDetailForm), function(_data){
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
<html:form action="/assetRptMtbfmttrEquipDetailChart.do">
<html:hidden property="strutsAction"/>

<html:hidden property="assetRptMtbfmttrEquipDetailDTO.equipId"/>
<html:hidden property="assetRptMtbfmttrEquipDetailDTO.itemDesc"/>
<html:hidden property="assetRptMtbfmttrEquipDetailDTO.startDate"/>
<html:hidden property="assetRptMtbfmttrEquipDetailDTO.endDate"/>

<html:hidden property="assetRptMtbfmttrEquipCommonDTO.filterEqGrade"/>
<html:hidden property="assetRptMtbfmttrEquipCommonDTO.filterEqGradeDesc"/>
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
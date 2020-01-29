<%--===========================================================================
고장TOP 차트(설비)
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.rpt.lcc.equip.action.AssetRptLccEquipDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 고장TOP 차트(설비) -->
<title><bean:message key='MENU.LCCByEqp'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	initChart();
	
	setTitle("assetRptLccEquipDetailDTO.chartValue","assetRptLccEquipDetailDTO.itemDesc");
}

var myLineChart;
/**
 * 차트
 */
function initChart(){
	var chartValue = "BDTIMEFREQ";
	var chartTitle = "";
	
	chartTitle = "<bean:message key='LABEL.bdTimeFreq'/>";
	assetRptLccEquipDetailForm.elements['assetRptLccEquipDetailDTO.chartValue'].value = "<bean:message key='LABEL.bdTimeFreq'/>";
	
	myLineChart =  new dhtmlXChart({
		view:"line",
		container:"chartbox",
		value:"#"+chartValue+"#",
		label:function(val){
			return setNumberFormat(val.BDTIMEFREQ);
		},
		item:{
			borderColor: "#447900",
			color: "#69ba00"
		},
		line:{
			color:"#69ba00",
			width:2
		},
		tooltip:{
			template:function(val){
				return setNumberFormat(val.BDTIMEFREQ);	
			}
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
	var url = contextPath + "/assetRptLccEquipDetailChart.do";
	var form = document.assetRptLccEquipDetailForm;	
	form.strutsAction.value = '<%=AssetRptLccEquipDetailAction.LCC_EQUIP_DETAIL_FIND %>';
 	
    $.post(url,FormQueryString(assetRptLccEquipDetailForm), function(_data){
    	myLineChart.parse(_data,"json");
    });
}

$(window).resize(function(){
	myLineChart.resize();
});

</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assetRptLccEquipDetailChart.do">
<html:hidden property="strutsAction"/>

<html:hidden property="assetRptLccEquipDetailDTO.eqLocId"/>
<html:hidden property="assetRptLccEquipDetailDTO.eqLocDesc"/>
<html:hidden property="assetRptLccEquipDetailDTO.deptId"/>
<html:hidden property="assetRptLccEquipDetailDTO.deptDesc"/>
<html:hidden property="assetRptLccEquipDetailDTO.eqCtgId"/>
<html:hidden property="assetRptLccEquipDetailDTO.eqCtgDesc"/>
<html:hidden property="assetRptLccEquipDetailDTO.itemNo"/>
<html:hidden property="assetRptLccEquipDetailDTO.itemDesc"/>
<html:hidden property="assetRptLccEquipDetailDTO.startDate"/>
<html:hidden property="assetRptLccEquipDetailDTO.endDate"/>
<html:hidden property="assetRptLccEquipDetailDTO.chartValue"/>
<html:hidden property="assetRptLccEquipDetailDTO.plantId"/>
<html:hidden property="assetRptLccEquipDetailDTO.plantDesc"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box">
		<div class="grid_area">
			<div id="chartbox" style="width:100%;height:240px;background-color:white;"></div>
		</div>
	</div> 

</html:form> 
</body>
</html>
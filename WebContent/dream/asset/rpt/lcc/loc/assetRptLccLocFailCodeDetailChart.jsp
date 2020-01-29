<%--===========================================================================
위치고장TOP고장분석그래프
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.rpt.lcc.loc.action.AssetRptLccLocDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 위치고장TOP고장분석그래프 -->
<title><bean:message key='PAGE.assetRptLccLocFailCodeDetailChart'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->

<!-- Fusion Charts --> 
<script type="text/javascript" src="common/js/fusioncharts.js"></script>
 
<script language="javascript">
<!--//

var moPieChart, caPieChart, rePieChart;
function loadPage() 
{
	findChart();
	
	setTitle("assetRptLccLocDetailDTO.eqLocDesc");
}

function findChart(){
	var url = contextPath + "/assetRptLccLocFailCodeDetailChart.do";
	var form = document.assetRptLccLocDetailForm;
	
	form.strutsAction.value = '<%=AssetRptLccLocDetailAction.LCC_LOC_MO_FIND %>';
    $.post(url,FormQueryString(form), function(_data){
    	drawMoChart(_data)
    });
    
	form.strutsAction.value = '<%=AssetRptLccLocDetailAction.LCC_LOC_CA_FIND %>';
    $.post(url,FormQueryString(form), function(_data){
    	drawCaChart(_data)
    });
    
	form.strutsAction.value = '<%=AssetRptLccLocDetailAction.LCC_LOC_RE_FIND %>';
    $.post(url,FormQueryString(form), function(_data){
    	drawReChart(_data)
    });
}

function drawMoChart(_data)
{
	var myObj = JSON.parse(_data);
	var contentsCnt = myObj.data.length;

	for(var i = contentsCnt-1; i>=0; i--)
	{
		myObj.data[i].label = myObj.data[i].MODESC;
		myObj.data[i].value = myObj.data[i].MOCNT;
	}
	
	moPieChart = new FusionCharts({
		type: 'pie2d', // The chart type
        renderAt: 'mochartbox', // Container of the chart
        width: '100%', // Width of the chart
        height: '100%', // Height of the chart
        dataFormat: 'json', // Data type
        dataSource: {
            // Chart Configuration
            "chart": {
               	"caption": "<bean:message key='LABEL.moCd'/>"
               	,"use3DLighting": "0"
                ,"decimals": "1"
                ,"useDataPlotColorForLabels": "1"
               	,"showLegend": "1"
                ,"legendPosition": "RIGHT"
                ,"theme": "fusion"
            },
            // Chart Data
            "data":  
                myObj.data
        }
	});
	
	moPieChart.render();
}
function drawCaChart(_data)
{
	var myObj = JSON.parse(_data);
	var contentsCnt = myObj.data.length;

	for(var i = contentsCnt-1; i>=0; i--)
	{
		myObj.data[i].label = myObj.data[i].CADESC;
		myObj.data[i].value = myObj.data[i].CACNT;
	}
	
	caPieChart = new FusionCharts({
		type: 'pie2d', // The chart type
        renderAt: 'cachartbox', // Container of the chart
        width: '100%', // Width of the chart
        height: '100%', // Height of the chart
        dataFormat: 'json', // Data type
        dataSource: {
            // Chart Configuration
            "chart": {
               	"caption": "<bean:message key='LABEL.caCd'/>"
               	,"use3DLighting": "0"
                ,"decimals": "1"
                ,"useDataPlotColorForLabels": "1"
                ,"showLegend": "1"
                ,"legendPosition": "RIGHT"
                ,"theme": "fusion"
            },
            // Chart Data
            "data":  
                myObj.data
        }
	});
	
	caPieChart.render();
}
function drawReChart(_data)
{
	var myObj = JSON.parse(_data);
	var contentsCnt = myObj.data.length;

	for(var i = contentsCnt-1; i>=0; i--)
	{
		myObj.data[i].label = myObj.data[i].REDESC;
		myObj.data[i].value = myObj.data[i].RECNT;
	}
	
	rePieChart = new FusionCharts({
		type: 'pie2d', // The chart type
        renderAt: 'rechartbox', // Container of the chart
        width: '100%', // Width of the chart
        height: '100%', // Height of the chart
        dataFormat: 'json', // Data type
        dataSource: {
            // Chart Configuration
            "chart": {
               	"caption": "<bean:message key='LABEL.reCd'/>"
               	,"use3DLighting": "0"
                ,"decimals": "1"
                ,"useDataPlotColorForLabels": "1"
                ,"showLegend": "1"
                ,"legendPosition": "RIGHT"
                ,"theme": "fusion"
            },
            // Chart Data
            "data":  
                myObj.data
        }
	});
	
	rePieChart.render();
}

</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assetRptLccLocFailCodeDetailChart.do">
<html:hidden property="strutsAction"/>

<html:hidden property="assetRptLccLocDetailDTO.eqLocId"/>
<html:hidden property="assetRptLccLocDetailDTO.eqLocDesc"/>
<html:hidden property="assetRptLccLocDetailDTO.startDate"/>
<html:hidden property="assetRptLccLocDetailDTO.endDate"/>
<html:hidden property="assetRptLccLocDetailDTO.chartValue"/>
<html:hidden property="assetRptLccLocDetailDTO.plantId"/>
<html:hidden property="assetRptLccLocDetailDTO.plantDesc"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box">
		<div class="grid_area">
			<div id="mochartbox" style="width:33%; height:240px; background-color:white; float:left;"></div>
			<div id="cachartbox" style="width:33%; height:240px; background-color:white; float:left;"></div>
			<div id="rechartbox" style="width:33%; height:240px; background-color:white; float:left;"></div>
		</div>
	</div> 

</html:form> 
</body>
</html>
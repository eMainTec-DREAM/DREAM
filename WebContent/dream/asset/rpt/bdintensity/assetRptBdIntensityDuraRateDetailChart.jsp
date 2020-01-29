<%--===========================================================================
에너지사용량 차트(일별)
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.rpt.bdintensity.action.AssetRptBdIntensityDetailListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 에너지사용량 차트(일별) -->
<title><bean:message key='MENU.DAILYENG'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->

<!-- Fusion Charts --> 
<script type="text/javascript" src="common/js/fusioncharts.js"></script>

<script language="javascript">
<!--//

function loadPage() 
{
	var plantDesc = $("input[name='assetRptBdIntensityDetailListDTO.plantDesc']").val();
	var eqLocDesc = $("input[name='assetRptBdIntensityDetailListDTO.eqLocDesc']").val();
	var usageDeptDesc = $("input[name='assetRptBdIntensityDetailListDTO.usageDeptDesc']").val();
	var eqCtgDesc = $("input[name='assetRptBdIntensityDetailListDTO.eqCtgDesc']").val();
	var equipDesc = $("input[name='assetRptBdIntensityDetailListDTO.equipDesc']").val();
	if(equipDesc == "") {
		if(eqCtgDesc == "") {
			if(usageDeptDesc == "") {
				if(eqLocDesc ==""){
					setTitle("assetRptBdIntensityDetailListDTO.plantDesc");
				}
				else{
					setTitle("assetRptBdIntensityDetailListDTO.eqLocDesc");
				}
			}
			else {
				setTitle("assetRptBdIntensityDetailListDTO.usageDeptDesc");
			}
		}
		else {
			setTitle("assetRptBdIntensityDetailListDTO.eqCtgDesc");
		}
	}
	else {
		setTitle("assetRptBdIntensityDetailListDTO.equipDesc");
	}
	
	findChart();
}

function initFusionChart(_data) {
	var myObj = JSON.parse(_data);
	var contentsCnt = myObj.data.length;

	for(var i = contentsCnt-1; i>=0; i--)
	{
		// 필요한 값을 제외하고 모두 제거
		delete myObj.data[i].ID;
		
		// 키값을 label(x축)과 value(y축)로 변경
		myObj.data[i].label = myObj.data[i].WKORDATE;
		delete myObj.data[i].WKORDATE;
		myObj.data[i].value = myObj.data[i].BDDURARATE;
		delete myObj.data[i].BDDURARATE;
	}
		
    var revenueChart = new FusionCharts({
        type: 'line', // The chart type
        renderAt: 'chart-container', // Container of the chart
        width: '100%', // Width of the chart
        height: '100%', // Height of the chart
        dataFormat: 'json', // Data type
        dataSource: {
            // Chart Configuration
            "chart": {
                  "xAxisName": "<bean:message key='LABEL.date'/>"
                , "yAxisName": "<bean:message key='LABEL.bdDuraRate'/>"
                , "baseFontSize" : "14"
                , "baseFont" : "Malgun Gothic"
                , "lineColor" : "ffbf1e"
                , "lineThickness": "3"
                , "showToolTip" : "1"
                , "theme": "fusion"
                //, "caption": "Countries With Most Oil Reserves [2017-18]"
                //, "subCaption": "In MMbbl = One Million barrels"
                //, "numberSuffix": "K"
                
            },
            // Chart Data
            "data":  
                myObj.data
              	/*{	"label": "x축에 표시되는 라벨",
	                "value": "y축에 표시되는 값"	  }*/
        }
    });
    
    revenueChart.render();
}

function findChart(idx,plant,line){
	var url = contextPath + "/assetRptBdIntensityDuraRateDetailChart.do";
	var form = document.assetRptBdIntensityDetailListForm;	
	form.strutsAction.value = '<%=AssetRptBdIntensityDetailListAction.DETAIL_DURA_FIND %>';
 	
    $.post(url,FormQueryString(assetRptBdIntensityDetailListForm), function(_data){
 	 	 initFusionChart(_data);
    });
}

</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assetRptBdIntensityDuraRateDetailChart.do">
<html:hidden property="strutsAction"/>

<html:hidden property="assetRptBdIntensityCommonDTO.filterStartDate"/>
<html:hidden property="assetRptBdIntensityCommonDTO.filterEndDate"/>

<html:hidden property="assetRptBdIntensityDetailListDTO.plantDesc"/>
<html:hidden property="assetRptBdIntensityDetailListDTO.eqLocDesc"/>
<html:hidden property="assetRptBdIntensityDetailListDTO.usageDeptDesc"/>
<html:hidden property="assetRptBdIntensityDetailListDTO.eqCtgDesc"/>
<html:hidden property="assetRptBdIntensityDetailListDTO.equipDesc"/>
<html:hidden property="assetRptBdIntensityDetailListDTO.plantId"/>
<html:hidden property="assetRptBdIntensityDetailListDTO.eqLocId"/>
<html:hidden property="assetRptBdIntensityDetailListDTO.usageDeptId"/>
<html:hidden property="assetRptBdIntensityDetailListDTO.eqCtgId"/>
<html:hidden property="assetRptBdIntensityDetailListDTO.equipId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box">
		<div class="grid_area">
			<div id="chart-container" style="width:100%;height:240px;background-color:white;"></div>
		</div>
	</div> 

</html:form> 
</body>
</html>
<%--===========================================================================
에너지사용량 차트(설비별)
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.eqeng.action.WorkRptEqEngDetailListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 에너지사용량 차트(설비별) -->
<title><bean:message key='MENU.EQENG'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->

<!-- Fusion Charts --> 
<script type="text/javascript" src="common/js/fusioncharts.js"></script>

<script language="javascript">
<!--//

function loadPage() 
{
	var plantDesc = $("input[name='workRptEqEngDetailListDTO.plantDesc']").val();
	var eqLocDesc = $("input[name='workRptEqEngDetailListDTO.eqLocDesc']").val();
	var usageDeptDesc = $("input[name='workRptEqEngDetailListDTO.usageDeptDesc']").val();
	var eqCtgDesc = $("input[name='workRptEqEngDetailListDTO.eqCtgDesc']").val();
	var equipDesc = $("input[name='workRptEqEngDetailListDTO.equipDesc']").val();
	if(equipDesc == "") {
		if(eqCtgDesc == "") {
			if(usageDeptDesc == "") {
				if(eqLocDesc ==""){
					setTitle("workRptEqEngDetailListDTO.plantDesc");
				}
				else{
					setTitle("workRptEqEngDetailListDTO.eqLocDesc");
				}
			}
			else {
				setTitle("workRptEqEngDetailListDTO.usageDeptDesc");
			}
		}
		else {
			setTitle("workRptEqEngDetailListDTO.eqCtgDesc");
		}
	}
	else {
		setTitle("workRptEqEngDetailListDTO.equipDesc");
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
		delete myObj.data[i].SEQNO;
		
		// 키값을 label(x축)과 value(y축)로 변경
		myObj.data[i].label = myObj.data[i].USAGEDATE;
		delete myObj.data[i].USAGEDATE;
		myObj.data[i].value = myObj.data[i].USAGE;
		delete myObj.data[i].USAGE;
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
                , "yAxisName": "<bean:message key='LABEL.usageAmt'/>"
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
	var url = contextPath + "/workRptEqEngDetailChart.do";
	var form = document.workRptEqEngDetailListForm;	
	form.strutsAction.value = '<%=WorkRptEqEngDetailListAction.DETAIL_LIST_FIND %>';
 	
    $.post(url,FormQueryString(workRptEqEngDetailListForm), function(_data){
 	 	 initFusionChart(_data);
    });
}

</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workRptEqEngDetailChart.do">
<html:hidden property="strutsAction"/>

<html:hidden property="workRptEqEngCommonDTO.filterYear"/>
<html:hidden property="workRptEqEngDetailListDTO.plantDesc"/>
<html:hidden property="workRptEqEngDetailListDTO.eqLocDesc"/>
<html:hidden property="workRptEqEngDetailListDTO.usageDeptDesc"/>
<html:hidden property="workRptEqEngDetailListDTO.eqCtgDesc"/>
<html:hidden property="workRptEqEngDetailListDTO.equipDesc"/>
<html:hidden property="workRptEqEngDetailListDTO.plantId"/>
<html:hidden property="workRptEqEngDetailListDTO.eqLocId"/>
<html:hidden property="workRptEqEngDetailListDTO.usageDeptId"/>
<html:hidden property="workRptEqEngDetailListDTO.eqCtgId"/>
<html:hidden property="workRptEqEngDetailListDTO.equipId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box">
		<div class="grid_area">
			<div id="chart-container" style="width:100%;height:240px;background-color:white;"></div>
		</div>
	</div> 

</html:form> 
</body>
</html>
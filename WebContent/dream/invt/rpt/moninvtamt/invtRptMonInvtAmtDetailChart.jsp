<%--===========================================================================
월별투자집행금액
author  cjscjs9
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.invt.rpt.moninvtamt.action.InvtRptMonInvtAmtDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 뭘별투자집행금액 -->
<title><bean:message key='MENU.MONINVTAMT'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->

<!-- Fusion Charts --> 
<script type="text/javascript" src="common/js/fusioncharts.js"></script>

<script language="javascript">
<!--//

function loadPage() 
{
 	var plantDesc = $("input[name='invtRptMonInvtAmtDetailDTO.plantDesc']").val();
	var deptDesc = $("input[name='invtRptMonInvtAmtDetailDTO.deptDesc']").val();
	var deptDesc = $("input[name='invtRptMonInvtAmtDetailDTO.YYYY']").val();
	
	setTitle("invtRptMonInvtAmtCommonDTO.filterYyyy");
	
	findChart();
}

function initFusionChart(_data) {
	var myObj = JSON.parse(_data);
	var contentsCnt = myObj.data.length;
console.log(_data);
	for(var i = contentsCnt-1; i>=0; i--)
	{
		// 필요한 값을 제외하고 모두 제거
		delete myObj.data[i].ID;
		delete myObj.data[i].SEQNO;
		
		// 키값을 label(x축)과 value(y축)로 변경
		myObj.data[i].label = myObj.data[i].RECDATE;
		delete myObj.data[i].USAGEDATE;
		myObj.data[i].value = myObj.data[i].TOTPRICE;
		delete myObj.data[i].USAGEAMT;
	}
		
    var revenueChart = new FusionCharts({
        type: 'column2d', // The chart type
        renderAt: 'chart-container', // Container of the chart
        width: '100%', // Width of the chart
        height: '100%', // Height of the chart
        dataFormat: 'json', // Data type
        dataSource: {
            // Chart Configuration
            "chart": {
                  "xAxisName": "<bean:message key='LABEL.month'/>"
                , "yAxisName": "<bean:message key='LABEL.invtAmt'/>"
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
	var url = contextPath + "/invtRptMonInvtAmtDetailChart.do";
	var form = document.invtRptMonInvtAmtDetailForm;	
	form.strutsAction.value = '<%=InvtRptMonInvtAmtDetailAction.DETAIL_LIST_FIND %>';
 	
    $.post(url,FormQueryString(invtRptMonInvtAmtDetailForm), function(_data){
 	 	 initFusionChart(_data);
    });
}

</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/invtRptMonInvtAmtDetailChart.do">
<html:hidden property="strutsAction"/>

<html:hidden property="invtRptMonInvtAmtCommonDTO.filterYyyy"/>
<html:hidden property="invtRptMonInvtAmtCommonDTO.filterInvtListId"/>
<html:hidden property="invtRptMonInvtAmtDetailDTO.plantDesc"/>
<html:hidden property="invtRptMonInvtAmtDetailDTO.deptDesc"/>
<html:hidden property="invtRptMonInvtAmtDetailDTO.plantId"/>
<html:hidden property="invtRptMonInvtAmtDetailDTO.deptId"/>
<html:hidden property="invtRptMonInvtAmtDetailDTO.invtListId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box">
		<div class="grid_area">
			<div id="chart-container" style="width:100%;height:240px;background-color:white;"></div>
		</div>
	</div> 

</html:form> 
</body>
</html>
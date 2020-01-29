<%--===========================================================================
에너지사용량 차트(집계)
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.toteng.action.WorkRptTotEngDetailListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 에너지사용량 차트(집계) -->
<title><bean:message key='MENU.TOTENG'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->

<!-- Fusion Charts --> 
<script type="text/javascript" src="common/js/fusioncharts.js"></script>

<script language="javascript">
<!--//

function loadPage() 
{
	workRptTotEngDetailListForm.elements['workRptTotEngDetailListDTO.tabDepth'].value  = "L3";
	
	setTitle("workRptTotEngDetailListDTO.usageDeptDesc");
	initGrid();
	goSearch();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	//myGrid.setImageSize(1,1);
	//myGrid.enableSmartRendering(true,500);
    
	myGrid.setHeader("<bean:message key='LABEL.seqNo'/>,,,,,,,"
					+",<bean:message key='LABEL.type'/>"
					+",<bean:message key='LABEL.month1'/>"+",#cspan"
					+",<bean:message key='LABEL.month2'/>"+",#cspan"
					+",<bean:message key='LABEL.month3'/>"+",#cspan"
					+",<bean:message key='LABEL.month4'/>"+",#cspan"
					+",<bean:message key='LABEL.month5'/>"+",#cspan"
					+",<bean:message key='LABEL.month6'/>"+",#cspan"
					+",<bean:message key='LABEL.month7'/>"+",#cspan"
					+",<bean:message key='LABEL.month8'/>"+",#cspan"
					+",<bean:message key='LABEL.month9'/>"+",#cspan"
					+",<bean:message key='LABEL.month10'/>"+",#cspan"
					+",<bean:message key='LABEL.month11'/>"+",#cspan"
					+",<bean:message key='LABEL.month12'/>"+",#cspan"
					+",<bean:message key='LABEL.totUsageYear'/>"
	);
	myGrid.attachHeader(["#rspan"
		                ,"#rspan"
		                ,"#rspan"
		                ,"#rspan"
		                ,"#rspan"
		                ,"#rspan"
		                ,"#rspan"
		                ,"#rspan"
		                ,"#rspan"
		                ,"<bean:message key='LABEL.amount'/>","<bean:message key='LABEL.usageAmt'/>"
	                    ,"<bean:message key='LABEL.amount'/>","<bean:message key='LABEL.usageAmt'/>"
						,"<bean:message key='LABEL.amount'/>","<bean:message key='LABEL.usageAmt'/>"
						,"<bean:message key='LABEL.amount'/>","<bean:message key='LABEL.usageAmt'/>"
						,"<bean:message key='LABEL.amount'/>","<bean:message key='LABEL.usageAmt'/>"
						,"<bean:message key='LABEL.amount'/>","<bean:message key='LABEL.usageAmt'/>"
						,"<bean:message key='LABEL.amount'/>","<bean:message key='LABEL.usageAmt'/>"
						,"<bean:message key='LABEL.amount'/>","<bean:message key='LABEL.usageAmt'/>"
						,"<bean:message key='LABEL.amount'/>","<bean:message key='LABEL.usageAmt'/>"
						,"<bean:message key='LABEL.amount'/>","<bean:message key='LABEL.usageAmt'/>"
						,"<bean:message key='LABEL.amount'/>","<bean:message key='LABEL.usageAmt'/>"
						,"<bean:message key='LABEL.amount'/>","<bean:message key='LABEL.usageAmt'/>"
						,"#rspan"
	]);
	myGrid.setColumnIds("SEQNO"
					  +",PLANTID"
					  +",PLANTDESC"
					  +",EQLOCID"
					  +",EQLOCDESC"
					  +",USAGEDEPTID"
					  +",USAGEDEPTDESC"
					  +",EQCTGID"
					  +",EQCTGDESC"
					  +",UNITPRICE01,USAGE01"
					  +",UNITPRICE02,USAGE02"
					  +",UNITPRICE03,USAGE03"
					  +",UNITPRICE04,USAGE04"
					  +",UNITPRICE05,USAGE05"
					  +",UNITPRICE06,USAGE06"
					  +",UNITPRICE07,USAGE07"
					  +",UNITPRICE08,USAGE08"
					  +",UNITPRICE09,USAGE09"
					  +",UNITPRICE10,USAGE10"
					  +",UNITPRICE11,USAGE11"
					  +",UNITPRICE12,USAGE12"
					  +",TOTUSAGEAMT"
	);
	myGrid.setInitWidths("60,60,100,60,100,60,100,60,100"+getWords(26,"100"));
	myGrid.setColAlign("center,center,left,center,left,center,left,center,left"+getWords(26,"center"));
	myGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro,ro"+getWords(26,"ron"));
	myGrid.setColumnHidden(1,true);
	myGrid.setColumnHidden(2,true);
	myGrid.setColumnHidden(3,true);
	myGrid.setColumnHidden(4,true);
	myGrid.setColumnHidden(5,true);
	myGrid.setColumnHidden(6,true);
	myGrid.setColumnHidden(7,true);
	
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
        return sortColumn("workRptTotEngCtgDetailList", this, workRptTotEngDetailListForm, "EQCTGID", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
	myGrid.init();
	//setHeader(myGrid, "gridbox", "goSearch"); // grid, grid id, callBack
}

function goSearch()
{
	for(var i = 0; myGrid.getColumnsNum() > i; i++)
	{
		if(myGrid.getColType(i) == "ron" ) 
		{
			myGrid.setNumberFormat("0,000.00",i,".",",");
		}
	}
	
	findGridList('Search');   
	findChart();
}

function findGridList(gridAction)
{
	var form = document.workRptTotEngDetailListForm;	
	form.strutsAction.value = '<%=WorkRptTotEngDetailListAction.DETAIL_LIST_FIND %>';

    var url = contextPath + "/workRptTotEngCtgDetailList.do";
    
    //doGridAction(gridAction, myGrid, "gridbox", url, FormQueryString(workRptTotEngDetailListForm));
    
    $.post(url,FormQueryString(workRptTotEngDetailListForm), function(_data){
    	myGrid.parse(_data,"js");
    });
}
 
/**
 * 엑셀 다운.
 */
function goExcel()
{
 	excelServerAction("workRptTotEngCtgDetailList", workRptTotEngDetailListForm );  
}

function initFusionChart(_data) {
	var myObj = JSON.parse(_data);
	var contentsCnt = myObj.data.length;
	
	for(var i = contentsCnt-1; i>=0; i--)
	{
		// 필요한 값을 제외하고 모두 제거
		delete myObj.data[i].ID;
		delete myObj.data[i].PLANTDESC;
		delete myObj.data[i].EQLOCDESC; 
		delete myObj.data[i].USAGEDEPTDESC; 
		
		// 키값을 label(x축)과 value(y축)로 변경
		myObj.data[i].label = myObj.data[i].EQCTGDESC;
		delete myObj.data[i].EQCTGDESC;
		myObj.data[i].value = myObj.data[i].TOTUSAGEAMT;
		delete myObj.data[i].TOTUSAGEAMT;
	}
		
    var revenueChart = new FusionCharts({
        type: 'pie3d', // The chart type
        renderAt: 'eqctg-chart-container', // Container of the chart
        width: '100%', // Width of the chart
        height: '100%', // Height of the chart
        dataFormat: 'json', // Data type
        dataSource: {
            // Chart Configuration
            "chart": {
                  "baseFontSize" : "11"
                , "baseFont" : "Malgun Gothic"
                , "showToolTip" : "1"
    			, "animationDuration" : "0.5"
   				, "showPercentValues" : "1"
                , "showLegend" : "1"
   				, "showLabels" : "1"
                , "showPercentValues": "1"
                , "decimals": "1"
                , "use3DLighting": "1"
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
	var url = contextPath + "/workRptTotEngCtgDetailList.do";
	var form = document.workRptTotEngDetailListForm;	
	form.strutsAction.value = '<%=WorkRptTotEngDetailListAction.DETAIL_CHART_FIND %>';
 	
    $.post(url,FormQueryString(workRptTotEngDetailListForm), function(_data){
 	 	 initFusionChart(_data);
    });
}

</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workRptTotEngCtgDetailList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="workRptTotEngCommonDTO.filterYear"/>
<html:hidden property="workRptTotEngDetailListDTO.tabDepth"/>
<html:hidden property="workRptTotEngDetailListDTO.plantDesc"/>
<html:hidden property="workRptTotEngDetailListDTO.plantId"/>
<html:hidden property="workRptTotEngDetailListDTO.eqLocDesc"/>
<html:hidden property="workRptTotEngDetailListDTO.eqLocId"/>
<html:hidden property="workRptTotEngDetailListDTO.usageDeptDesc"/>
<html:hidden property="workRptTotEngDetailListDTO.usageDeptId"/>
<html:hidden property="workRptTotEngDetailListDTO.eqCtgDesc"/>
    <!-- searchbox 박스 Line -->
    <div class="article_box">
		<div style = "width:48%; height:100%; float:left; ">
			<div>
				<div id="eqctg-chart-container" style="width:100%;height:270px;background-color:white;"></div>
			</div>
		</div>			
		<div style = "width:52%; height:100%; float:left; ">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
	</div>
</html:form> 
</body>
</html>
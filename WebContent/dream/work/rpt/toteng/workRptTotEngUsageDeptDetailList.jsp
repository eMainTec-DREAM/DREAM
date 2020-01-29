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

function loadPage() 
{
	workRptTotEngDetailListForm.elements['workRptTotEngDetailListDTO.tabDepth'].value  = "L2";
	
	setTitle("workRptTotEngDetailListDTO.eqLocDesc");
 	initGrid();
 	goSearch();
}

function afterLoad()
{
	parent.$('#workRptTotEngUsageDeptDetailList_tabList').find('.ac_menu').trigger("click");
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	//myGrid.setImageSize(1,1);
	//myGrid.enableSmartRendering(true,500);
	
	myGrid.setHeader("<bean:message key='LABEL.seqNo'/>,,,,,"
					+",<bean:message key='LABEL.usedDept'/>"
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
	myGrid.setInitWidths("60,60,100,60,100,60,100"+getWords(26,"100"));
	myGrid.setColAlign("center,center,left,center,left,center,left"+getWords(26,"center"));
	myGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro"+getWords(26,"ron"));
	myGrid.setColumnHidden(1,true);
	myGrid.setColumnHidden(2,true);
	myGrid.setColumnHidden(3,true);
	myGrid.setColumnHidden(4,true);
	myGrid.setColumnHidden(5,true);
	
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
        return sortColumn("workRptTotEngUsageDeptDetailList", this, workRptTotEngDetailListForm, "USAGEDEPTID", ind, direction);
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

    var url = contextPath + "/workRptTotEngUsageDeptDetailList.do";
    
    doGridAction(gridAction, myGrid, "gridbox", url, FormQueryString(workRptTotEngDetailListForm));
    
    $.post(url,FormQueryString(workRptTotEngDetailListForm), function(_data){
    	myGrid.parse(_data,"js");
    });
}
 
/**
 * 상세열기
 */
 function goOpen(){
 	goTabPage('workRptTotEngCtgDetailList');
}

 /**
 * Tab 이동시 호출
 */
 function goTabPage(pageId)
 {
    tabValidationCheck(myGrid, pageId, "goTabPageAction");
 }

 function goTabPageAction(pageId, selectedId)
 {
 	var form = document.workRptTotEngDetailListForm;
 	
 	form.elements['workRptTotEngDetailListDTO.plantId'].value = getValueById(myGrid, selectedId,'PLANTID');
 	form.elements['workRptTotEngDetailListDTO.plantDesc'].value = getValueById(myGrid, selectedId,'PLANTDESC');
 	form.elements['workRptTotEngDetailListDTO.eqLocId'].value = getValueById(myGrid, selectedId,'EQLOCID');
 	form.elements['workRptTotEngDetailListDTO.eqLocDesc'].value = getValueById(myGrid, selectedId,'EQLOCDESC');
 	form.elements['workRptTotEngDetailListDTO.usageDeptId'].value = getValueById(myGrid, selectedId,'USAGEDEPTID');
 	form.elements['workRptTotEngDetailListDTO.usageDeptDesc'].value = getValueById(myGrid, selectedId,'USAGEDEPTDESC');
 	
 	goCommonTabPage(form, '' , pageId, pageId);
 }

/**
 * 엑셀 다운.
 */
function goExcel()
{
	excelServerAction("workRptTotEngUsageDeptDetailList", workRptTotEngDetailListForm );
}

function initUsageDeptChart(_data) {
	var myUsageDeptObj = JSON.parse(_data);
	var usageDeptContentsCnt = myUsageDeptObj.data.length;
	
	for(var i = usageDeptContentsCnt-1; i>=0; i--)
	{
		// 필요한 값을 제외하고 모두 제거
		delete myUsageDeptObj.data[i].ID;
		delete myUsageDeptObj.data[i].SEQNO;
		delete myUsageDeptObj.data[i].PLANTDESC;
		delete myUsageDeptObj.data[i].EQLOCDESC; 
		
		// 키값을 label(x축)과 value(y축)로 변경
		myUsageDeptObj.data[i].label = myUsageDeptObj.data[i].USAGEDEPTDESC;
		delete myUsageDeptObj.data[i].USAGEDEPTDESC;
		myUsageDeptObj.data[i].value = myUsageDeptObj.data[i].TOTUSAGEAMT;
		delete myUsageDeptObj.data[i].TOTUSAGEAMT;
	}
	
	var revenueUsageDeptChart = new FusionCharts({
        type: 'pie3d', // The chart type
        renderAt: 'usagedept-chart-container', // Container of the chart
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
                myUsageDeptObj.data
              	/*{	"label": "x축에 표시되는 라벨",
	                "value": "y축에 표시되는 값"	  }*/
       		}
    });
    
    revenueUsageDeptChart.render();
}

function findChart(){
	var url = contextPath + "/workRptTotEngUsageDeptDetailList.do";
	var form = document.workRptTotEngDetailListForm;	
	form.strutsAction.value = '<%=WorkRptTotEngDetailListAction.DETAIL_CHART_FIND %>';
 	
    $.post(url,FormQueryString(workRptTotEngDetailListForm), function(_data){
 	 	 initUsageDeptChart(_data);
    });
}

</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workRptTotEngUsageDeptDetailList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="workRptTotEngCommonDTO.filterYear"/>
<html:hidden property="workRptTotEngDetailListDTO.tabDepth"/>
<html:hidden property="workRptTotEngDetailListDTO.plantDesc"/>
<html:hidden property="workRptTotEngDetailListDTO.plantId"/>
<html:hidden property="workRptTotEngDetailListDTO.eqLocDesc"/>
<html:hidden property="workRptTotEngDetailListDTO.eqLocId"/>
<html:hidden property="workRptTotEngDetailListDTO.usageDeptId"/>
<html:hidden property="workRptTotEngDetailListDTO.usageDeptDesc"/>
    <!-- searchbox 박스 Line -->
    <div class="article_box">
		<div style = "width:48%; height:100%; float:left; ">
			<div>
				<div id="usagedept-chart-container" style="width:100%;height:270px; background-color:white;"></div>
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
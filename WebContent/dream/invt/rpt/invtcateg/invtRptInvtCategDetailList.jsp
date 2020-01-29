<%--===========================================================================
투자구분분석
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.invt.rpt.invtcateg.action.InvtRptInvtCategDetailListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 에너지사용량 차트(집계) -->
<title><bean:message key='MENU.INVTCATEG'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->

<!-- Fusion Charts --> 
<script type="text/javascript" src="common/js/fusioncharts.js"></script>

<script language="javascript">
<!--//

function loadPage() 
{
	setTitle("invtRptInvtCategDetailListDTO.invtCategDesc");
	initGrid();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	myGrid.setImageSize(1,1);
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowDblClicked",function(rowId, columnId){
		goInvtList(rowId, columnId);
	});
//     myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
//         return sortColumn("invtRptInvtCategDetailList", this, invtRptInvtCategDetailListForm, "INVTTYPEDESC", ind, direction);
//     });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
	myGrid.init();
	setHeader(myGrid, "gridbox", "goSearch"); // grid, grid id, callBack
}

function goSearch()
{
	findGridList('Search');   
	findChart();
}

function findGridList(gridAction)
{
	var form = document.invtRptInvtCategDetailListForm;	
	form.strutsAction.value = '<%=InvtRptInvtCategDetailListAction.DETAIL_LIST_FIND %>';

    var url = contextPath + "/invtRptInvtCategDetailList.do";
    
    doGridAction(gridAction, myGrid, "gridbox", url, FormQueryString(invtRptInvtCategDetailListForm));
}

/**
 * 엑셀 다운.
 */
function goExcel()
{
 	excelServerAction("invtRptInvtCategDetailList", invtRptInvtCategDetailListForm );  
}

function initFusionChart(_data) {
	var myObj = JSON.parse(_data);
	var contentsCnt = myObj.data.length;
	
	for(var i = contentsCnt-1; i>=0; i--)
	{
		// 필요한 값을 제외하고 모두 제거
		delete myObj.data[i].ID;
		delete myObj.data[i].INVTCATEGID;
		delete myObj.data[i].INVTCATEGDESC;
		delete myObj.data[i].INVTTYPEID;
		
		// 키값을 label(x축)과 value(y축)로 변경
		myObj.data[i].label = myObj.data[i].INVTTYPEDESC;
		delete myObj.data[i].INVTTYPEDESC;
		myObj.data[i].value = myObj.data[i].INVTTYPECNT;
		delete myObj.data[i].INVTTYPECNT;
	}
		
    var revenueChart = new FusionCharts({
        type: 'pie3d', // The chart type
        renderAt: 'chart-container', // Container of the chart
        width: '100%', // Width of the chart
        height: '100%', // Height of the chart
        dataFormat: 'json', // Data type
        dataSource: {
            // Chart Configuration
            "chart": {
                  "xAxisName": "<bean:message key='LABEL.date'/>"
                , "yAxisName": "<bean:message key='LABEL.usageAmt'/>"
                , "baseFontSize" : "11"
                , "baseFont" : "Malgun Gothic"
                , "lineColor" : "ffbf1e"
                , "lineThickness": "3"
                , "showToolTip" : "1"
    			, "animationDuration" : "0.5"
   				, "showPercentValues" : "1"
   				, "showLabels" : "1"
                , "showPercentValues": "1"
                , "decimals": "1"
                , "use3DLighting": "1"
                , "showLegend" : "1"
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
	var url = contextPath + "/invtRptInvtCategDetailList.do";
	var form = document.invtRptInvtCategDetailListForm;	
	form.strutsAction.value = '<%=InvtRptInvtCategDetailListAction.DETAIL_LIST_FIND %>';
 	
    $.post(url,FormQueryString(invtRptInvtCategDetailListForm), function(_data){
 	 	 initFusionChart(_data);
    });
}


function goInvtList(rowId, columnId)
{
	 if(typeof rowId=="undefined"||typeof columnId=="undefined") return ;

		var headerDate = myGrid.getColumnLabel(myGrid.getSelectedCellIndex(),0);
				
		var invtCategId = getValueById(myGrid, rowId, "INVTCATEGID");
		var invtCategDesc = getValueById(myGrid, rowId, "INVTCATEGDESC");
		var invtTypeId = getValueById(myGrid, rowId, "INVTTYPEID");
		var invtTypeDesc = getValueById(myGrid, rowId, "INVTTYPEDESC");
		var fromDate = invtRptInvtCategDetailListForm.elements['invtRptInvtCategDetailListDTO.fromDate'].value;
		var toDate = invtRptInvtCategDetailListForm.elements['invtRptInvtCategDetailListDTO.toDate'].value;
		var usageDeptId = invtRptInvtCategDetailListForm.elements['invtRptInvtCategDetailListDTO.usageDeptId'].value;
		var usageDeptDesc = invtRptInvtCategDetailListForm.elements['invtRptInvtCategDetailListDTO.usageDeptDesc'].value;
		var plantId = invtRptInvtCategDetailListForm.elements['invtRptInvtCategDetailListDTO.plantId'].value;
		var plantDesc = invtRptInvtCategDetailListForm.elements['invtRptInvtCategDetailListDTO.plantDesc'].value;
		
		var url   = contextPath + "/invtList.do";
		var popWidth = 1010;
		var popHeight = 640;
	    // pop up이 중앙에 위치하게 한다.
	    var TopPosition  = (screen.height/2 - popHeight/2);
	    var LeftPosition = (screen.width/2 - popWidth/2);

	    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
	              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
	    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
	    
		var param = "isDecoratorName=popupPage"
					+"&invtCommonDTO.strutsAction="
					+"&invtCommonDTO.invtCateg="+invtCategId
					+"&invtCommonDTO.invtCategDesc="+invtCategDesc
					+"&invtCommonDTO.invtTypeId="+invtTypeId
					+"&invtCommonDTO.invtTypeDesc="+invtTypeDesc
					+"&invtCommonDTO.filterInvtComFromDate="+fromDate
					+"&invtCommonDTO.filterInvtComToDate="+toDate
					+"&invtCommonDTO.filterUsageDeptId="+usageDeptId
					+"&invtCommonDTO.filterUsageDeptDesc="+usageDeptDesc
					+"&invtCommonDTO.filterPlantId="+plantId
					+"&invtCommonDTO.filterPlantDesc="+plantDesc
					+"&ACTION_FUNCTION=goSearch";
		
		//post 전송
		openWindowWithPost(url, "INVT_CATEG_POPUP", param, pos);
}


</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/invtRptInvtCategDetailList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="invtRptInvtCategDetailListDTO.fromDate"/>
<html:hidden property="invtRptInvtCategDetailListDTO.toDate"/>
<html:hidden property="invtRptInvtCategDetailListDTO.plantId"/>
<html:hidden property="invtRptInvtCategDetailListDTO.plantDesc"/>
<html:hidden property="invtRptInvtCategDetailListDTO.usageDeptId"/>
<html:hidden property="invtRptInvtCategDetailListDTO.usageDeptDesc"/>
<html:hidden property="invtRptInvtCategDetailListDTO.invtCategId"/>
<html:hidden property="invtRptInvtCategDetailListDTO.invtCategDesc"/>
    <!-- searchbox 박스 Line -->
    <div class="article_box" >
		<div style = "width:48%; height:100%; float:left; ">
			<div>
				<div id="chart-container" style="width:100%;height:270px;background-color:white;"></div>
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
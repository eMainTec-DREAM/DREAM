<%--===========================================================================
에너지사용량(집계)
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.toteng.action.WorkRptTotEngListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 에너지사용량(집계) -->
<title><bean:message key='MENU.TOTENG'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->

<!-- Fusion Charts --> 
<script type="text/javascript" src="common/js/fusioncharts.js"></script>
 
<script language="javascript">
<!--//
function loadPage() 
{
	workRptTotEngListForm.elements['workRptTotEngCommonDTO.filterYear'].value = getYear(); 
	initGrid(workRptTotEngListForm.elements['workRptTotEngCommonDTO.filterYear'].value);
	goSearch();
}

var myGrid;
function initGrid(year)
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	/* myGrid.setImageSize(1,1);
	myGrid.enableSmartRendering(true,500); */
	myGrid.setHeader("<bean:message key='LABEL.seqNo'/>,"
					+",<bean:message key='LABEL.plantDesc'/>"
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
	myGrid.setInitWidths("60,60,100"+getWords(26,"100"));
	myGrid.setColAlign("center, center,left"+getWords(26,"center"));
	myGrid.setColTypes("ro,ro,ro"+getWords(26,"ron"));
	myGrid.setColumnHidden(1,true);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
        return sortColumn("workRptTotEngList", this, workRptTotEngListForm, "PLANTID", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
	myGrid.init();
	//setHeader(myGrid, "gridbox"); // grid, grid id, callBack
	
}

/**
 * 검색 클릭시 호출
 */
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

function findGridList(sheetAction)
{
	if(checkValidation()) return;
	
	var form = document.workRptTotEngListForm;	
	form.strutsAction.value = '<%=WorkRptTotEngListAction.LIST_FIND %>';
		
	var url = contextPath + "/workRptTotEngList.do";
	
	$.post(url,FormQueryString(workRptTotEngListForm), function(_data){
    	myGrid.parse(_data,"js");
    });
	//doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workRptTotEngListForm), "RESULTID");
}


function initFusionChart(_data) {
	var myObj = JSON.parse(_data);
	var contentsCnt = myObj.data.length;
	
	for(var i = contentsCnt-1; i>=0; i--)
	{
		// 필요한 값을 제외하고 모두 제거
		delete myObj.data[i].ID;
		delete myObj.data[i].SEQNO;
		delete myObj.data[i].PLANTID;
		
		// 키값을 label(x축)과 value(y축)로 변경
		myObj.data[i].label = myObj.data[i].PLANTDESC;
		delete myObj.data[i].PLANTDESC;
		myObj.data[i].value = myObj.data[i].TOTUSAGEAMT;
		delete myObj.data[i].TOTUSAGEAMT;
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
				  "showToolTip" : "1"
				, "animationDuration" : "0.5"
				, "showPercentValues" : "1"
				, "showLabels" : "1"
	            , "showPercentValues": "1"
	            , "decimals": "1"
	            , "use3DLighting": "0"
	            , "showLegend" : "1"
                , "baseFontSize" : "12"
                , "baseFont" : "Malgun Gothic"
                , "lineColor" : "ffbf1e"
                , "lineThickness": "3"
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
	var url = contextPath + "/workRptTotEngList.do";
	var form = document.workRptTotEngListForm;	
	form.strutsAction.value = '<%=WorkRptTotEngListAction.CHART_FIND %>';
 	
    $.post(url,FormQueryString(workRptTotEngListForm), function(_data){
 	 	 initFusionChart(_data);
    });
}

/**
 * 상세열기
 */
 function goOpen(){
 	goTabPage('workRptTotEngLocDetailList');
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
 	var form = document.workRptTotEngListForm;
 	
 	form.elements['workRptTotEngDetailListDTO.plantId'].value = getValueById(myGrid, selectedId,'PLANTID');
 	form.elements['workRptTotEngDetailListDTO.plantDesc'].value = getValueById(myGrid, selectedId,'PLANTDESC');
 	
 	goCommonTabPage(form, '' , pageId);
 }

/**
 * 엑셀 다운.
 */
 function goExcel()
 {
 	excelServerAction("workRptTotEngList", workRptTotEngListForm );  
 }
 
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workRptTotEngList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="workRptTotEngDetailListDTO.plantId"/>
<html:hidden property="workRptTotEngDetailListDTO.plantDesc"/>
<html:hidden property="workRptTotEngDetailListDTO.eqLocId"/>
<html:hidden property="workRptTotEngDetailListDTO.eqLocDesc"/>
<html:hidden property="workRptTotEngDetailListDTO.usageDeptId"/>
<html:hidden property="workRptTotEngDetailListDTO.usageDeptDesc"/>
<html:hidden property="workRptTotEngDetailListDTO.eqCtgId"/>
<html:hidden property="workRptTotEngDetailListDTO.eqCtgDesc"/>
<html:hidden property="workRptTotEngDetailListDTO.equipId"/>
<html:hidden property="workRptTotEngDetailListDTO.equipDesc"/>

	<!-- searchbox 박스 Line -->
	<div class="section_wrap">
		<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.Filter"/></div>
			</div>
			<div class="function_box filter">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>
				<div class="fb_group2">
				</div>
			</div>
		</div><!--sheader_box end-->
		<div class="article_box">
			<div class="form_box">
				<!-- 일자 -->
				<div class="field">
					<label><bean:message key="LABEL.date"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="workRptTotEngCommonDTO.filterYear" tabindex="10" />
							<p class="open_year_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
			</div>
		</div><!--article_box end-->
	</div> <!--  end section_wrap -->
	<div class="section_wrap">
		<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.List"/></div>
			</div>
			<div class="function_box list">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>
				<div class="fb_group2">
				</div>
			</div>
		</div><!--sheader_box end-->
	    <div class="article_box" >
			<div style = "width:48%; height:100%; float:left; ">
				<div>
					<div id="chart-container" style="width:100%;height:270;background-color:white;"></div>
				</div>
			</div>			
			<div style = "width:52%; height:100%; float:left; ">
				<div class="grid_area">
					<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
				</div>
			</div>
		</div>
	 </div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>
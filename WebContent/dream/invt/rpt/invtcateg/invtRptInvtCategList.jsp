<%--===========================================================================
투자구분분석
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.invt.rpt.invtcateg.action.InvtRptInvtCategListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 투자구분분석 -->
<title><bean:message key='MENU.INVTCATEG'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->

<!-- Fusion Charts --> 
<script type="text/javascript" src="common/js/fusioncharts.js"></script>
 
<script language="javascript">
<!--//
var plantAc, usageDeptAc;
function loadPage() 
{
	// 투자완료일자 세팅
	invtRptInvtCategListForm.elements['invtRptInvtCategCommonDTO.filterFromDate'].value = getMinusMonth2(new Date(), -1); 
	invtRptInvtCategListForm.elements['invtRptInvtCategCommonDTO.filterToDate'].value   = getToday();
	
	//공장
	invtRptInvtCategListForm.elements['invtRptInvtCategCommonDTO.filterPlantId'].value = loginUser.filterPlant;
	invtRptInvtCategListForm.elements['invtRptInvtCategCommonDTO.filterPlantDesc'].value = loginUser.filterPlantDesc;
    
	// 공장명
	plantAc = new autoC({"invtRptInvtCategCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "invtRptInvtCategCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
	
    // 사용부서
    usageDeptAc = new autoC({"invtRptInvtCategCommonDTO.filterUsageDeptDesc":"description"});
    usageDeptAc.setTable("TADEPT");
    usageDeptAc.setAcResultMap({
        "invtRptInvtCategCommonDTO.filterUsageDeptId":"dept_id",
    });
    usageDeptAc.setAcConditionMap({
		"comp_no":loginUser.compNo
	}); 
    usageDeptAc.setAcDConditionMap({
    	"plant" : "invtRptInvtCategCommonDTO.filterPlantId"
    });
    usageDeptAc.init();
    
	initGrid();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	myGrid.setImageSize(1,1);
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
	myGrid.attachEvent("onRowDblClicked",function(rowId, columnId){
// 		goInvtList(rowId, columnId);
	});
//     myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
//         return sortColumn("invtRptInvtCategList", this, invtRptInvtCategListForm, "INVTCATEGID", ind, direction);
//     });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
	myGrid.init();
	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
    findGridList('Search');   
    findChart();
}

function findGridList(sheetAction)
{
	if(checkValidation()) return;
	
	var form = document.invtRptInvtCategListForm;	
	form.strutsAction.value = '<%=InvtRptInvtCategListAction.LIST_FIND %>';
	
	var url = contextPath + "/invtRptInvtCategList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(invtRptInvtCategListForm), "RESULTID");
}


function initFusionChart(_data) {
	var myObj = JSON.parse(_data);
	var contentsCnt = myObj.data.length;
	
	for(var i = contentsCnt-1; i>=0; i--)
	{
		// 필요한 값을 제외하고 모두 제거
		delete myObj.data[i].ID;
		delete myObj.data[i].INVTCATEGID;
		
		// 키값을 label(x축)과 value(y축)로 변경
		myObj.data[i].label = myObj.data[i].INVTCATEGDESC;
		delete myObj.data[i].INVTCATEGDESC;
		myObj.data[i].value = myObj.data[i].INVTCATEGCNT;
		delete myObj.data[i].INVTCATEGCNT;
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
	var url = contextPath + "/invtRptInvtCategList.do";
	var form = document.invtRptInvtCategListForm;	
	form.strutsAction.value = '<%=InvtRptInvtCategListAction.LIST_FIND %>';
 	
    $.post(url,FormQueryString(invtRptInvtCategListForm), function(_data){
 	 	 initFusionChart(_data);
    });
}

/**
 * 상세열기
 */
 function goOpen(){
 	goTabPage('invtRptInvtCategDetailList');
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
 	var form = document.invtRptInvtCategListForm;
 	
 	form.elements['invtRptInvtCategDetailListDTO.invtCategId'].value = getValueById(myGrid, selectedId,'INVTCATEGID');
 	form.elements['invtRptInvtCategDetailListDTO.invtCategDesc'].value = getValueById(myGrid, selectedId,'INVTCATEGDESC');
 	form.elements['invtRptInvtCategDetailListDTO.fromDate'].value = form.elements['invtRptInvtCategCommonDTO.filterFromDate'].value;
 	form.elements['invtRptInvtCategDetailListDTO.toDate'].value = form.elements['invtRptInvtCategCommonDTO.filterToDate'].value
 	form.elements['invtRptInvtCategDetailListDTO.usageDeptId'].value = form.elements['invtRptInvtCategCommonDTO.filterUsageDeptId'].value;
 	form.elements['invtRptInvtCategDetailListDTO.usageDeptDesc'].value = form.elements['invtRptInvtCategCommonDTO.filterUsageDeptDesc'].value
 	form.elements['invtRptInvtCategDetailListDTO.plantId'].value = form.elements['invtRptInvtCategCommonDTO.filterPlantId'].value;
 	form.elements['invtRptInvtCategDetailListDTO.plantDesc'].value = form.elements['invtRptInvtCategCommonDTO.filterPlantDesc'].value
 	
 	goCommonTabPage(form, '' , pageId);
 }

/**
 * 엑셀 다운.
 */
 function goExcel()
 {
 	excelServerAction("invtRptInvtCategList", invtRptInvtCategListForm );  
 }
 
 function goInvtList(rowId, columnId)
 {
	 if(typeof rowId=="undefined"||typeof columnId=="undefined") return ;

		var headerDate = myGrid.getColumnLabel(myGrid.getSelectedCellIndex(),0);
				
		var invtCategId = getValueById(myGrid, rowId, "INVTCATEGID");
		var invtCategDesc = getValueById(myGrid, rowId, "INVTCATEGDESC");
		var fromDate = invtRptInvtCategListForm.elements['invtRptInvtCategCommonDTO.filterFromDate'].value;
		var toDate = invtRptInvtCategListForm.elements['invtRptInvtCategCommonDTO.filterToDate'].value;
		
		var url   = contextPath + "/invtList.do";
		var popWidth = 1010;
		var popHeight = 640;
	    // pop up이 중앙에 위치하게 한다.
	    var TopPosition  = (screen.height/2 - popHeight/2);
	    var LeftPosition = (screen.width/2 - popWidth/2);

	    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
	              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
	    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
	    
		var param = "isDecoratorName=popupPage"+
					"&invtCommonDTO.strutsAction="+
					"&invtCommonDTO.invtCateg="+invtCategId+
					"&invtCommonDTO.invtCategDesc="+invtCategDesc+
					"&invtCommonDTO.filterInvtComFromDate="+fromDate+
					"&invtCommonDTO.filterInvtComToDate="+toDate
					+"&ACTION_FUNCTION=goSearch";
		
		//post 전송
		openWindowWithPost(url, "LOCLIST_EQ_POPUP", param, pos);
 }
 
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/invtRptInvtCategList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="invtRptInvtCategCommonDTO.filterPlantId"/>
<html:hidden property="invtRptInvtCategCommonDTO.filterUsageDeptId"/>

<html:hidden property="invtRptInvtCategDetailListDTO.fromDate"/>
<html:hidden property="invtRptInvtCategDetailListDTO.toDate"/>
<html:hidden property="invtRptInvtCategDetailListDTO.plantId"/>
<html:hidden property="invtRptInvtCategDetailListDTO.plantDesc"/>
<html:hidden property="invtRptInvtCategDetailListDTO.usageDeptId"/>
<html:hidden property="invtRptInvtCategDetailListDTO.usageDeptDesc"/>
<html:hidden property="invtRptInvtCategDetailListDTO.invtCategId"/>
<html:hidden property="invtRptInvtCategDetailListDTO.invtCategDesc"/>

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
				<!-- 투자완료일자 -->
				<div class="field">
					<label><bean:message key="LABEL.invtComDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
                            <html:text property="invtRptInvtCategCommonDTO.filterFromDate" tabindex="10" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="invtRptInvtCategCommonDTO.filterToDate" tabindex="20" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
					</div>
				</div>
				<!-- 공장명 -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
                        <html:text property="invtRptInvtCategCommonDTO.filterPlantDesc" tabindex="30" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
				<!-- 사용부서 -->
                <div class="field">
                    <label><bean:message key="LABEL.usedDept"/></label>
                    <div class="input_box">
                        <html:text property="invtRptInvtCategCommonDTO.filterUsageDeptDesc" tabindex="40" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
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
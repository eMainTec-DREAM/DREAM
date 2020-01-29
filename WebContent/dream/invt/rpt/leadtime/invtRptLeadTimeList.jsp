<%--===========================================================================
author  cjscjs9
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.invt.rpt.leadtime.action.InvtRptLeadTimeListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 투자현황 -->
<title><bean:message key='MENU.INVTLEADTIME'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->

<!-- Fusion Charts --> 
<script type="text/javascript" src="common/js/fusioncharts.js"></script>
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

var deptAc;
var plantAc;

function loadPage() 
{
	// 날짜 자동완성 (1달전~오늘)
	invtRptLeadTimeListForm.elements['invtRptLeadTimeCommonDTO.filterStartYear'].value = getMinusMonth2(new Date(), -1); 
	invtRptLeadTimeListForm.elements['invtRptLeadTimeCommonDTO.filterEndYear'].value   = getToday();
	//공장
	invtRptLeadTimeListForm.elements['invtRptLeadTimeCommonDTO.filterPlantId'].value = loginUser.filterPlant;
	invtRptLeadTimeListForm.elements['invtRptLeadTimeCommonDTO.filterPlantDesc'].value = loginUser.filterPlantDesc;
	
    //부서 AC 
    deptAc = new autoC({"invtRptLeadTimeCommonDTO.filterDeptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "invtRptLeadTimeCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    deptAc.setAcDConditionMap({
    	"plant" : "invtRptLeadTimeCommonDTO.filterPlantId"
    });
    deptAc.init();
    
	acSysDesc("invtRptLeadTimeCommonDTO.filterInvtCategDesc","invtRptLeadTimeCommonDTO.filterInvtCateg","INVT_CATEG");
	acSysDesc("invtRptLeadTimeCommonDTO.filterInvtTypeDesc","invtRptLeadTimeCommonDTO.filterInvtType","INVT_TYPE");
	
	plantAc = new autoC({"invtRptLeadTimeCommonDTO.filterPlantDesc":"description"});
	plantAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
	plantAc.setTable("TAPLANT");
	plantAc.setAcResultMap({
        "invtRptLeadTimeCommonDTO.filterPlantId":"plant"
    });
	plantAc.init();
    
	initGrid();
	findChart();
	
}
/**
 * 그리드 초기화
 */
function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    myGrid.enableSmartRendering(true,500);
     myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	invtRptLeadTimeListForm.elements['invtRptLeadTimeCommonDTO.filterLTypeId'].value = "";
    	invtRptLeadTimeListForm.elements['invtRptLeadTimeCommonDTO.filterSTypeId'].value = "";
    	invtRptLeadTimeListForm.elements['strutsAction'].value = '<%=InvtRptLeadTimeListAction.LIST_FIND%>';
      return  sortColumn("invtRptLeadTimeList", this, invtRptLeadTimeListForm, "LTYPEID", ind, direction);
    });
 	myGrid.attachEvent("onRowDblClicked", function(rowId,columnId){
		goEqList(rowId,columnId);
	});
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
    myGrid.init();
    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/invtRptLeadTimeList.do";
    invtRptLeadTimeListForm.elements['strutsAction'].value = '<%=InvtRptLeadTimeListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(invtRptLeadTimeListForm), "LTYPEID", "Y");
}

function goSearch()
{
    if(checkValidation()) return;
    
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


/**
 * Excel Export
 */
function goExcel()
{
	if(checkValidation()) return;
   excelServerAction("invtRptLeadTimeList", invtRptLeadTimeListForm);
}

function goEqList(rowId, columnId){
	if(typeof rowId=="undefined"||typeof columnId=="undefined") return ;
			
	var invtCategId = invtRptLeadTimeListForm.elements['invtRptLeadTimeCommonDTO.filterInvtCateg'].value;
	var invtCategDesc = invtRptLeadTimeListForm.elements['invtRptLeadTimeCommonDTO.filterInvtCategDesc'].value;
	var invtTypeId = invtRptLeadTimeListForm.elements['invtRptLeadTimeCommonDTO.filterInvtType'].value;
	var invtTypeDesc = invtRptLeadTimeListForm.elements['invtRptLeadTimeCommonDTO.filterInvtTypeDesc'].value;
	var fromDate = invtRptLeadTimeListForm.elements['invtRptLeadTimeCommonDTO.filterStartYear'].value;
	var toDate = invtRptLeadTimeListForm.elements['invtRptLeadTimeCommonDTO.filterEndYear'].value;
	var usageDeptId = invtRptLeadTimeListForm.elements['invtRptLeadTimeCommonDTO.filterDeptId'].value;
	var usageDeptDesc = invtRptLeadTimeListForm.elements['invtRptLeadTimeCommonDTO.filterDeptDesc'].value;
	var plantId = invtRptLeadTimeListForm.elements['invtRptLeadTimeCommonDTO.filterPlantId'].value;
	var plantDesc = invtRptLeadTimeListForm.elements['invtRptLeadTimeCommonDTO.filterPlantDesc'].value;
	var lType = getValueById(myGrid, rowId, "LTYPEID");
	var lTypeDesc = getValueById(myGrid, rowId, "LTYPE");
	var sType = getValueById(myGrid, rowId, "STYPEID");
	var sTypeDesc = getValueById(myGrid, rowId, "STYPE");
	
	
	var url   = contextPath + "/invtPrcList.do";
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
		+"&invtCommonDTO.filterStartDate="+fromDate
		+"&invtCommonDTO.filterEndDate="+toDate
		+"&invtCommonDTO.filterUsageDeptId="+usageDeptId
		+"&invtCommonDTO.filterUsageDeptDesc="+usageDeptDesc
		+"&invtCommonDTO.filterPlantId="+plantId
		+"&invtCommonDTO.filterPlantDesc="+plantDesc
		+"&invtCommonDTO.filterLTypeId="+lType
		+"&invtCommonDTO.filterLType="+lTypeDesc
		+"&invtCommonDTO.filterSTypeId="+sType
		+"&invtCommonDTO.filterSType="+sTypeDesc
		+"&ACTION_FUNCTION=goSearch";
	
	//post 전송
	openWindowWithPost(url, "LOCLIST_EQ_POPUP", param, pos);
}

function initFusionChart(_data) {
	var myObj = JSON.parse(_data);
	var contentsCnt = myObj.data.length;

	for(var i = contentsCnt-1; i>=0; i--)
	{
		// 필요한 값을 제외하고 모두 제거
		delete myObj.data[i].ID;
		delete myObj.data[i].ISDELCHECK;
		delete myObj.data[i].SEQNO;
		/* delete myObj.data[i].STYPE; */
		delete myObj.data[i].STYPEID;
		delete myObj.data[i].LTYPEID;
		
		// 키값을 label(x축)과 value(y축)로 변경
		myObj.data[i].label = myObj.data[i].LTYPE+" "+myObj.data[i].STYPE;
		delete myObj.data[i].LTYPE;
		myObj.data[i].value = myObj.data[i].AVGLEADTIME;
		delete myObj.data[i].AVGLEADTIME;
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
//                   "xAxisName": "<bean:message key='LABEL.date'/>"
//                 , "yAxisName": "<bean:message key='LABEL.usageAmt'/>"
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
	var url = contextPath + "/invtRptLeadTimeList.do";
	var form = document.invtRptLeadTimeListForm;	
	form.strutsAction.value = '<%=InvtRptLeadTimeListAction.CHART_FIND %>';
 	
    $.post(url,FormQueryString(invtRptLeadTimeListForm), function(_data){
 	 	 initFusionChart(_data);
    });
}


//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/invtRptLeadTimeList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="invtRptLeadTimeCommonDTO.filterDeptId"/>
<html:hidden property="invtRptLeadTimeCommonDTO.filterInvtCateg"/>
<html:hidden property="invtRptLeadTimeCommonDTO.filterPlantId" />
<html:hidden property="invtRptLeadTimeCommonDTO.filterInvtType" />
<html:hidden property="invtRptLeadTimeCommonDTO.filterLTypeId" />
<html:hidden property="invtRptLeadTimeCommonDTO.filterSTypeId" />


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
                            <html:text property="invtRptLeadTimeCommonDTO.filterStartYear" tabindex="10" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="invtRptLeadTimeCommonDTO.filterEndYear" tabindex="20" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>
                <!-- 공장명 -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
                        <html:text property="invtRptLeadTimeCommonDTO.filterPlantDesc" tabindex="30"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 투자구분 -->
                <div class="field">
                    <label><bean:message key="LABEL.invtCategDesc"/></label>
                    <div class="input_box">
                        <html:text property="invtRptLeadTimeCommonDTO.filterInvtCategDesc" tabindex="40"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 분류 -->
				<div class="field">
					<label><bean:message key="LABEL.category"/></label>
					<div class="input_box">
						<html:text property="invtRptLeadTimeCommonDTO.filterInvtTypeDesc" tabindex="50"/>
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
						<html:text property="invtRptLeadTimeCommonDTO.filterDeptDesc" tabindex="60"/>
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
			<div style = "width:52%; height:100%; float:left; ">
				<div>
					<div id="chart-container" style="width:100%;height:270px;background-color:white;"></div>
				</div>
			</div>	
			<div style = "width:48%; height:100%; float:left; ">
				<div class="grid_area">
					<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
				</div>
			</div>		
		</div>
	 </div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>


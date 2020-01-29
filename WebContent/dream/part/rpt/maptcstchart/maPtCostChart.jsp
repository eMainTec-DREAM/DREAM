<%--===========================================================================
자재비용분석
author  ssong
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.rpt.maptcstchart.action.MaPtCostChartAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page import="common.bean.User"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!-- 자재비용분석 -->
<title><bean:message key='MENU.PTCOSTCHART'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//
//그리드명
var myGrid;			//자재비용분석 그리드
var myChart;		//자재비용분석 차트
var isChartLoading = false;
var selectedInd;


var deptAc;
var plantAc;

function loadPage() 
{
	//년도 세팅
	maPtCostChartForm.elements['maPtCostChartDTO.filterYyyy'].value = dateToData(getToday()).substr(0, 4);

	//부서정보 세팅
	//maPtCostChartForm.elements['maPtCostChartDTO.filterDeptId'].value    = "<%=user.getDeptId()%>";
	//maPtCostChartForm.elements['maPtCostChartDTO.filterDeptDesc'].value  = "<%=user.getDeptDesc()%>";

	//공장명
    if(loginUser.plant!='null'){
    	maPtCostChartForm.elements['maPtCostChartDTO.filterPlantId'].value  = loginUser.plant;
    	maPtCostChartForm.elements['maPtCostChartDTO.filterPlantDesc'].value  = loginUser.plantDesc;
	}
	
    //그리드 초기화
	initGrid();
	//차트 초기화
	initChart();
	goSearch();
	
	deptAc = new autoC({"maPtCostChartDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maPtCostChartDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "maPtCostChartDTO.filterPlantId"
    });
    deptAc.init();

 	// 공장코드
	plantAc = new autoC({"maPtCostChartDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "maPtCostChartDTO.filterPlantId":"plant"
    });
    plantAc.init();
}
/**
 * 그리드 초기화
 */
function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	myGrid.setHeader("DEPTID,<bean:message key='LABEL.deptOg'/>,<bean:message key='LABEL.month1'/>,#cspan,#cspan"
														+",<bean:message key='LABEL.month2'/>,#cspan,#cspan"
														+",<bean:message key='LABEL.month3'/>,#cspan,#cspan"
														+",<bean:message key='LABEL.month4'/>,#cspan,#cspan"
														+",<bean:message key='LABEL.month5'/>,#cspan,#cspan"
														+",<bean:message key='LABEL.month6'/>,#cspan,#cspan"
														+",<bean:message key='LABEL.month7'/>,#cspan,#cspan"
														+",<bean:message key='LABEL.month8'/>,#cspan,#cspan"
														+",<bean:message key='LABEL.month9'/>,#cspan,#cspan"
														+",<bean:message key='LABEL.month10'/>,#cspan,#cspan"
														+",<bean:message key='LABEL.month11'/>,#cspan,#cspan"
														+",<bean:message key='LABEL.month12'/>,#cspan,#cspan"
														+",<bean:message key='LABEL.total2'/>,#cspan,#cspan"
					);
	myGrid.attachHeader(["DEPTID","#rspan","<bean:message key='LABEL.recTotPrice'/>","<bean:message key='LABEL.repairTotPrice'/>","<bean:message key='LABEL.total'/>"
			                              ,"<bean:message key='LABEL.recTotPrice'/>","<bean:message key='LABEL.repairTotPrice'/>","<bean:message key='LABEL.total'/>"
			                              ,"<bean:message key='LABEL.recTotPrice'/>","<bean:message key='LABEL.repairTotPrice'/>","<bean:message key='LABEL.total'/>"
			                              ,"<bean:message key='LABEL.recTotPrice'/>","<bean:message key='LABEL.repairTotPrice'/>","<bean:message key='LABEL.total'/>"
			                              ,"<bean:message key='LABEL.recTotPrice'/>","<bean:message key='LABEL.repairTotPrice'/>","<bean:message key='LABEL.total'/>"
			                              ,"<bean:message key='LABEL.recTotPrice'/>","<bean:message key='LABEL.repairTotPrice'/>","<bean:message key='LABEL.total'/>"
			                              ,"<bean:message key='LABEL.recTotPrice'/>","<bean:message key='LABEL.repairTotPrice'/>","<bean:message key='LABEL.total'/>"
			                              ,"<bean:message key='LABEL.recTotPrice'/>","<bean:message key='LABEL.repairTotPrice'/>","<bean:message key='LABEL.total'/>"
			                              ,"<bean:message key='LABEL.recTotPrice'/>","<bean:message key='LABEL.repairTotPrice'/>","<bean:message key='LABEL.total'/>"
			                              ,"<bean:message key='LABEL.recTotPrice'/>","<bean:message key='LABEL.repairTotPrice'/>","<bean:message key='LABEL.total'/>"
			                              ,"<bean:message key='LABEL.recTotPrice'/>","<bean:message key='LABEL.repairTotPrice'/>","<bean:message key='LABEL.total'/>"
			                              ,"<bean:message key='LABEL.recTotPrice'/>","<bean:message key='LABEL.repairTotPrice'/>","<bean:message key='LABEL.total'/>"
			                              ,"<bean:message key='LABEL.recTotPrice'/>","<bean:message key='LABEL.repairTotPrice'/>","<bean:message key='LABEL.total'/>"
			            ]);
	myGrid.setColumnIds("DEPTID,DEPTDESC,REC01,REPAIR01,TOT01"
									  +",REC02,REPAIR02,TOT02"
									  +",REC03,REPAIR03,TOT03"
									  +",REC04,REPAIR04,TOT04"
									  +",REC05,REPAIR05,TOT05"
									  +",REC06,REPAIR06,TOT06"
									  +",REC07,REPAIR07,TOT07"
									  +",REC08,REPAIR08,TOT08"
									  +",REC09,REPAIR09,TOT09"
									  +",REC10,REPAIR10,TOT10"
									  +",REC11,REPAIR11,TOT11"
									  +",REC12,REPAIR12,TOT12"
									  +",RECTOT,REPAIRTOT,TOTAL"
						);
	myGrid.setInitWidths("100,110"+getWords(39, "90"));
	myGrid.setColAlign("left,left"+getWords(39, "right"));
	myGrid.setColTypes("ro,ro"+getWords(39, "ron"));
	for(i=0; i<39; i++)
	{
		myGrid.setNumberFormat("0,000", (i+2));
	}
	myGrid.setColumnHidden(0, true);
	myGrid.attachEvent("onXLE",function(grdObj,count){setCounter(grdObj,"gridbox")});

	myGrid.attachEvent("onRowSelect", function(id,ind){
		if(!isChartLoading){
			isChartLoading = true;
			selectedId = id;
			selectedInd = ind;
			drawChart(id,ind);
		}
	});
	myGrid.attachEvent("onRowDblClicked", function(id,ind){
		goPtList(id,ind);
	});
	myGrid.init();
}

function goStandard(){
	goPtList(selectedId, selectedInd);
}

function goPtList(id, ind){
	if(typeof id=="undefined"||typeof ind=="undefined") return ;
	
	if(ind>1)
	{
		//클릭한 컬럼의 일자.
		var yyyy = maPtCostChartForm.elements['maPtCostChartDTO.yyyy'].value;
		var mm = myGrid.getColumnId(ind).substring(myGrid.getColumnId(ind).length-2,myGrid.getColumnId(ind).length);
		var fromYyyyMmDd = yyyy+mm+"01";
		var toYyyyMmDd = yyyy+mm+"31";
		//클릭한 row의 부서정보
		var deptId = myGrid.cells(id, myGrid.getColIndexById("DEPTID")).getValue();
		var deptDesc = myGrid.cells(id, myGrid.getColIndexById("DEPTDESC")).getValue();
		//37이상의 컬럼은 합계 컬럼.
		if(ind>37){
			fromYyyyMmDd = yyyy+"0101";
			toYyyyMmDd = yyyy+"1231";
		}
		//팝업사이즈
		var popWidth = 1010;
		var popHeight = 640;
	    // pop up이 중앙에 위치하게 한다.
	    var TopPosition  = (screen.height/2 - popHeight/2);
	    var LeftPosition = (screen.width/2 - popWidth/2);

	    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
	              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
	    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
	    
		switch(ind%3)
		{
		case 2:
			var url   = contextPath + "/maPtRecStatList.do";
			var bottomForm = document.bottomForm;
			var param = "isDecoratorName=popupPage"+
						"&maPtRecStatCommonDTO.strutsAction="+
						"&maPtRecStatCommonDTO.filterRecStartDate="+fromYyyyMmDd+
						"&maPtRecStatCommonDTO.filterRecEndDate="+toYyyyMmDd+
						"&maPtRecStatCommonDTO.filterDeptId="+deptId+
						"&maPtRecStatCommonDTO.filterDeptDesc="+deptDesc;
			//post 전송
			openWindowWithPost(url, "CHART_REC_LIST_POPUP", param, pos);
			break;
		case 0:
			 var url   = contextPath + "/maPtRepStatList.do";
				var bottomForm = document.bottomForm;
				var param = "isDecoratorName=popupPage"+
							"&maPtRepStatCommonDTO.strutsAction="+
							"&maPtRepStatCommonDTO.filterStartDate="+fromYyyyMmDd+
							"&maPtRepStatCommonDTO.filterEndDate="+toYyyyMmDd+
							"&maPtRepStatCommonDTO.filterDeptId="+deptId+
							"&maPtRepStatCommonDTO.filterDeptDesc="+deptDesc;
				//post 전송
				openWindowWithPost(url, "CHART_REP_LIST_POPUP", param, pos);
			break;
		}
	}
}

function initChart()
{
	myChart =  new dhtmlXChart({
		view:"line",
		container:"chartbox",
		value:"#TOTPRICE#",
		tooltip:{
			template:"#TOTPRICE#"
		},
		item:{
			borderColor: "#FF7171",
			color: "#ffffff"
		},
		line:{
			color:"#FF7171",
			width:2
		},
		xAxis:{
			template:"'#MONTH#"
		},
		offset:0,
		yAxis:{
			/* start:0,
			step:1 */
		}
	});
	myChart.addSeries({
		value:"#LASTTOTPRICE#",
		tooltip:{
			template:"#LASTTOTPRICE#"
		},
		item:{
			borderColor: "#a7ee70",
			color: "#ffffff"
		},
		line:{
			color:"#a7ee70",
			width:2
		}
	});
	
}

/**
 * Grid Row 선택 시 호출
 */
function drawChart(id, ind)
{
	// Grid 검색 결과가 없을시 Return
	if(myGrid.getRowsNum() == 0) return;
	// 검색 결과가 있지만 Row id 정보가 없을 경우 첫번째 Row로 세팅한다. 
	if(typeof id == "undefined") id = 1;
	
	var colTitle = "<bean:message key='LABEL.recTotPrice'/>";
	var structsAction = "<%=MaPtCostChartAction.PTCOST_REC_CHART_FIND%>";
	if(ind>1)
	{
		switch(ind%3)
		{
		    case 2  : colTitle = "<bean:message key='LABEL.recTotPrice'/>"; 
		    structsAction = "<%=MaPtCostChartAction.PTCOST_REC_CHART_FIND%>";
		    break;
		    case 0  : colTitle = "<bean:message key='LABEL.repairTotPrice'/>"; 
		    structsAction = "<%=MaPtCostChartAction.PTCOST_REPAIR_CHART_FIND%>";
		    break;
		    case 1  : colTitle = "<bean:message key='LABEL.total'/>"; 
		    structsAction = "<%=MaPtCostChartAction.PTCOST_MONTH_TOTAL_CHART_FIND%>";
		    break;
		}
	}
		
	//차트 title설정
	setTitleChart(myGrid.cells(id, myGrid.getColIndexById("DEPTDESC")).getValue(), colTitle);
	
	//차트 검색할 deptId 설정
	maPtCostChartForm.elements['maPtCostChartDTO.deptId'].value = myGrid.cells(id, myGrid.getColIndexById("DEPTID")).getValue();
	
	findChart(structsAction);
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 자재비용분석 그리드에 셋팅한다.
 */
function findGridList()
{
    var url = contextPath + "/maPtCostChart.do";
    maPtCostChartForm.elements['strutsAction'].value = '<%=MaPtCostChartAction.PTCOST_LIST_FIND%>';

    myGrid.clearAll(); 
    setLoading("gridbox");
    
    $.post(url,FormQueryString(maPtCostChartForm), function(_data){
    	//myGrid.parse(_data,findChart,"js");
    	myGrid.parse(_data,drawChart,"js");
    });
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 자재비용분석 차트에 셋팅한다.
 */
function findChart(structsAction)
{
    var url = contextPath + "/maPtCostChart.do";
    maPtCostChartForm.elements['strutsAction'].value = structsAction;

    myChart.clearAll();

    // 차트 범례명 변경
	myChart.define("legend",{
		values:[
				{text:maPtCostChartForm.elements['maPtCostChartDTO.filterYyyy'].value+"년도"},
				{text:(maPtCostChartForm.elements['maPtCostChartDTO.filterYyyy'].value-1)+"년도"}
			],
		layout:"x",
	    width: 75,
	    align:"center",
	    valign:"bottom",
		margin: 10,
	    marker:{
	        type:"item"
	    }
	});
    
    $.post(url,FormQueryString(maPtCostChartForm), function(_data){
    	myChart.parse(_data,"json");
    	isChartLoading = false;
    });
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	//validation
	if(checkRequireValue("maPtCostChartDTO.filterYyyy","<bean:message key='LABEL.year'/>")) return;
	//if(checkRequireValue("maPtCostChartDTO.filterDeptDesc","<bean:message key='LABEL.dept'/>")) return;
	
	//차트 title 변경
	setTitleChart("<bean:message key='LABEL.total2'/>", "<bean:message key='LABEL.recTotPrice'/>");
	
	maPtCostChartForm.elements['maPtCostChartDTO.yyyy'].value = maPtCostChartForm.elements['maPtCostChartDTO.filterYyyy'].value;
	maPtCostChartForm.elements['maPtCostChartDTO.deptId'].value = maPtCostChartForm.elements['maPtCostChartDTO.filterDeptId'].value;
	findGridList();
}

/**
 * chart title 설정 
 */
function setTitleChart(plantDesc, totDesc)
{
	document.getElementById("chartDiv").innerText = plantDesc +" - " +totDesc;
    	
}

/**
 *  엑셀 다운
 */
 function goExcel()
 {
 	excelAction(myGrid);
 } 
 

 $(window).resize(function(){
	 myChart.resize();
 });

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPtCostChart.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPtCostChartDTO.filterDeptId"/>
<html:hidden property="maPtCostChartDTO.filterPlantId"/>
<html:hidden property="maPtCostChartDTO.yyyy"/>
<html:hidden property="maPtCostChartDTO.deptId"/>
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
				<!-- 년 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.year"/></label>
					<div class="input_read">
						<html:text property="maPtCostChartDTO.filterYyyy" tabindex="10" readonly="true"/>
						<p class="open_year_calendar"><span>날짜</span></p>
					</div>
				</div>
				<!-- 작업부서 -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="maPtCostChartDTO.filterDeptDesc" tabindex="20" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
						<html:text property="maPtCostChartDTO.filterPlantDesc" tabindex="90" />
						<p class="open_spop"><a><span>조회</span></a></p>
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
		<div class="article_box">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
	</div> <!--  End of section_wrap -->
	<div class="section_wrap">
		<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx" id="chartDiv"><bean:message key="LABEL.chart"/></div>
			</div>
			<div class="function_box">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>

				<div class="fb_group2">
				</div>
			</div>
		</div><!--sheader_box end-->
		<div class="article_box">
			<div class="grid_area">
				<div id="chartbox" style="width:100%;height:270px;background-color:white;"></div>
			</div>
		</div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>
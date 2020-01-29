<%--===========================================================================
작업유형별현황
author  kim21017
version $Id: maWoTypeChart.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.mawotype.action.MaWoTypeChartAction" %>
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
<!-- 작업유형별현황 -->
<title><bean:message key='MENU.WOTYPE'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//
//그리드명
var myGrid;
//작업횟수율 
var woCntChart;
//작업시간율 
var woTimeChart;
var isChartLoading = false;

var lang = loginUser.locale;


var plantTypeAc;
var eqLocDescAc;
var eqCtgTypeAc;

function loadPage() 
{
	//년월 세팅
	maWoTypeChartForm.elements['maWoTypeChartDTO.filterStartDate'].value =  getMinusDay(7);
	maWoTypeChartForm.elements['maWoTypeChartDTO.filterEndDate'].value = getToday();
	if(loginUser.eqLocId!='null'){
		maWoTypeChartForm.elements['maWoTypeChartDTO.filterEqLocId'].value = loginUser.eqLocId;
		maWoTypeChartForm.elements['maWoTypeChartDTO.filterEqLocDesc'].value = loginUser.eqLocDesc;
	}
	if(loginUser.filterPlant!='null'&&loginUser.filterPlant!=''){
		maWoTypeChartForm.elements['maWoTypeChartDTO.filterPlantId'].value = loginUser.filterPlant;
		maWoTypeChartForm.elements['maWoTypeChartDTO.filterPlantDesc'].value = loginUser.filterPlantDesc;
	}

	initGrid();
	initWoCntChart();
	initWoTimeChart();
	
	goSearch();
	
	plantTypeAc = new autoC({"maWoTypeChartDTO.filterPlantDesc":"description"});
	plantTypeAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
	plantTypeAc.setTable("TAPLANT");
	plantTypeAc.setAcResultMap({
        "maWoTypeChartDTO.filterPlantId":"plant"
    });
	plantTypeAc.init();
	
	eqLocDescAc = new autoC({"maWoTypeChartDTO.filterEqLocDesc":"full_desc"});
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcResultMap({
        "maWoTypeChartDTO.filterEqLocId":"eqloc_id"
    });
    eqLocDescAc.setAcDConditionMap({
    	"plant" : "maWoTypeChartDTO.filterPlantId"
    });
    eqLocDescAc.init();
    
    eqCtgTypeAc = new autoC({"maWoTypeChartDTO.filterEqCtgDesc":"full_desc"});
    eqCtgTypeAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcResultMap({
        "maWoTypeChartDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();
	
}


/**
 * 그리드 초기화
 */
function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    myGrid.setHeader("<bean:message key='LABEL.seqNo'/>"
					+",,<bean:message key='LABEL.woType2'/>"
					+",<bean:message key='LABEL.woCnt'/>"
					+",<bean:message key='LABEL.woCntRate'/>"
					+",<bean:message key='LABEL.woTimeMin'/>"
					+",<bean:message key='LABEL.woTimeRate'/>"
					+"");
	myGrid.setColumnIds("SEQNO,WOTYPE,WOTYPEDESC,WOCNT,WOCNTRATE,WOTIME,WOTIMERATE");
	myGrid.setInitWidths("100,100,100,100,100,100,100");
	myGrid.setColAlign("center,left,left,right,right,right,right");
	myGrid.setColTypes("cntr,ro,ro,ro,ro,ro,ro");
	myGrid.setColumnHidden(1,true);
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox"); });
	myGrid.attachEvent("onRowSelect", function(id,ind){
	});
	myGrid.attachEvent("onRowDblClicked", function(id,ind){
	});
	myGrid.init();
}
//빨주노초파남보회 
 var colArr = new Array('#FF4848','#FF7012','#FFFF24','#2FED28','#2524FF','#123478','#8A2457','#939393');
 var colorIndex1 = 0;//작업 횟수용 차트색상인덱스
 var colorIndex2 = 0;//작업 시간용 차트색상인덱스
 /**
  * 작업횟수율 차트 초기화
  */
 function initWoCntChart(){
	 woCntChart =  new dhtmlXChart({
 			view:"pie",
			container:"wocntchartbox",
			value:"#RATE#",
			color:function(obj){
				return colArr[colorIndex1++];
			},
			labelLines: true,
			label:"#WOTYPE#: #RATE#%",
		    tooltip:"#WOTYPE#: #RATE#%",
			shadow:1 
	 });
}
 /**
  * 작업시간율 차트 초기화
  */
  function initWoTimeChart(){
 	 woTimeChart =  new dhtmlXChart({
 			view:"pie",
 			container:"wotimechartbox",
 			value:"#RATE#",
			color:function(obj){
				return colArr[colorIndex2++];
			},
			labelLines: true,
			label:"#WOTYPE#: #RATE#%",
		    tooltip:"#WOTYPE#: #RATE#%",
			shadow:1 
 	 });
 }
/**
 * 현재 셋팅된 조건으로 값을 조회하여 라인별 그리드에 셋팅한다.
 */
function findGridList()
{
    var url = contextPath + "/maWoTypeChart.do";
    maWoTypeChartForm.elements['strutsAction'].value = '<%=MaWoTypeChartAction.LIST_FIND%>';

    myGrid.clearAll();
    setLoading("gridbox");

    $.post(url,FormQueryString(maWoTypeChartForm), function(_data){
    	myGrid.parse(_data,findWoCntChart,"js");
    });
}

function findWoCntChart(){
	var url = contextPath + "/maWoTypeChart.do";
	maWoTypeChartForm.elements['strutsAction'].value = '<%=MaWoTypeChartAction.WOCNT_CHART_FIND%>';
 
    woCntChart.clearAll();
    
    $.post(url,FormQueryString(maWoTypeChartForm), function(_data){
    	woCntChart.parse(_data,"json");
    	findWoTimeChart();
    });
}

function findWoTimeChart(){
	var url = contextPath + "/maWoTypeChart.do";
	maWoTypeChartForm.elements['strutsAction'].value = '<%=MaWoTypeChartAction.WOTIME_CHART_FIND%>';

    woTimeChart.clearAll();
    
    $.post(url,FormQueryString(maWoTypeChartForm), function(_data){
    	woTimeChart.parse(_data,"json");
    	isChartLoading = false;
		closeModal();
    	colorIndex1 = 0;
    	colorIndex2 = 0;
    });
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	//validation
	if(checkValidation()) return;
	setModal();
	if(!isChartLoading){
		isChartLoading = true;
		findGridList();
	}
    
}
/**
 *  엑셀 다운
 */
 function goExcel()
 {
 	excelAction(myGrid);
 } 

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maWoTypeChart.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maWoTypeChartDTO.filterPlantId"/>
<html:hidden property="maWoTypeChartDTO.filterEqLocId"/>
<html:hidden property="maWoTypeChartDTO.filterEqCtgId"/>
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
					<label class="check"><bean:message key="LABEL.woDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="maWoTypeChartDTO.filterStartDate" tabindex="10" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="maWoTypeChartDTO.filterEndDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 공장 -->
				<div class="field">
					<label><bean:message key="LABEL.plant"/></label>
					<div class="input_box">
						<html:text property="maWoTypeChartDTO.filterPlantDesc" tabindex="30" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 위치 -->
				<div class="field">
					<label><bean:message key="LABEL.location"/></label>
					<div class="input_box">
						<html:text property="maWoTypeChartDTO.filterEqLocDesc" tabindex="40"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 종류 -->
				<div class="field">
					<label><bean:message key="LABEL.type"/></label>
					<div class="input_box">
						<html:text property="maWoTypeChartDTO.filterEqCtgDesc" tabindex="50" />
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
			<div class="function_box list not_fold">
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
				<div id="gridbox" style="width:100%; height:283px; background-color:white;"></div>
			</div>
		</div>
	</div> <!--  End of section_wrap -->
	<div class="section_wrap">
		<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.chart"/></div>
			</div>
			<div class="function_box">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>

				<div class="fb_group2">
				</div>
				<div class="b_line"></div> 
				<div class="fb_group1">
					 
				</div>
			</div>
		</div>
		<div class="article_box chbox">
			<div class="ch_title"><bean:message key='LABEL.woCntRate'/></div>
			<div class="grid_area">
				<div id="wocntchartbox" style="width:100%;height:270px;background-color:white;"></div>
			</div>
		</div>
		<div class="article_box chbox">
			<div class="ch_title"><bean:message key='LABEL.woTimeRate'/></div>
			<div class="grid_area">
				<div id="wotimechartbox" style="width:100%;height:270px;background-color:white;"></div>
			</div>
		</div> 
	</div>
</html:form> 
</body>
</html>
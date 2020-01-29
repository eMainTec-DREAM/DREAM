<%--===========================================================================
월별접속현황
author  sy.yang
version $Id: connRptMonthLogList.jsp,v 1.1 2018/05/23 01:45:27 sy.yang Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.conn.rpt.month.log.action.ConnRptMonthLogListAction" %>
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
<!-- 접속현황 -->
<title><bean:message key="MENU.CONNMONTHLIST"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//
//그리드명
var connGrid;			//접속현황 그리드
var connChart;			//접속현황 차트
var usrGrid;			//접속자현황 그리드
var isChartLoading = false;
var lang = loginUser.locale;

/** 자동완성 변수 */
var deptAc;
var empAc;

function loadPage() 
{
	//월 세팅
	connRptMonthLogListForm.elements['connRptMonthLogListDTO.filterStartDate'].value = getMinusMonth(-2);
	connRptMonthLogListForm.elements['connRptMonthLogListDTO.filterEndDate'].value   = getMinusMonth(0);

	//월별접속현황 그리드 초기화
	initConnGrid(connRptMonthLogListForm.elements['connRptMonthLogListDTO.filterStartDate'].value,
			connRptMonthLogListForm.elements['connRptMonthLogListDTO.filterEndDate'].value);
	
	//접속자현황 그리드 초기화
	initUsrGrid(connRptMonthLogListForm.elements['connRptMonthLogListDTO.filterStartDate'].value,
			connRptMonthLogListForm.elements['connRptMonthLogListDTO.filterEndDate'].value);
	
	goSearch();
	//차트 초기화
	initChart();
	
	// 부서
	deptAc = new autoC({"connRptMonthLogListDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	,"is_monitoring":"Y"
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "connRptMonthLogListDTO.filterDeptId":"dept_id"
    });
    deptAc.setKeyName("connRptMonthLogListDTO.filterDeptId");
    deptAc.init();
    
    // 사원
    empAc = new autoC({"connRptMonthLogListDTO.filterEmpDesc":"emp_name"});
    empAc.setAcDisplay("EMP_NAME");
    empAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
  	   });
    empAc.setTable("TAEMP");
    empAc.setKeyName("connRptMonthLogListDTO.filterEmpId");
    empAc.setAcResultMap({
        "connRptMonthLogListDTO.filterEmpId":"emp_id"
    });
    empAc.init(); 
}

/**
 * 접속현황 그리드 초기화
 */
function initConnGrid(fromDate,toDate)
{
	var months = getMonthInterval(fromDate.replace(/\-/gi, ""),toDate.replace(/\-/gi, ""));

	connRptMonthLogListForm.elements['connRptMonthLogListDTO.dateArrStr'].value = getMonths1(fromDate,toDate,months);
    connGrid = new dhtmlXGridObject('conngridbox');
    connGrid.enableTreeGridLines();
    connGrid.setImageSize(1,1);
    connGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	connGrid.setHeader("<bean:message key='LABEL.dept'/>,,,,,"+getMonths2(fromDate,toDate,months));
	connGrid.setColumnIds("DEPTDESC,ID,LVL,MINLVL,DEPTID,PDEPTID"+getMonths2(fromDate,toDate,months));
	connGrid.setInitWidths("200,100,100,100,100,100"+getWords(months,"70"));
	connGrid.setColAlign("left,left,left,left,left,left"+getWords(months,"center"));
	connGrid.setColTypes("tree,ro,ro,ro,ro,ro"+getWords(months,"ro"));
	connGrid.setColumnHidden(1,true);
	connGrid.setColumnHidden(2,true);
	connGrid.setColumnHidden(3,true);
	connGrid.setColumnHidden(4,true);
	connGrid.setColumnHidden(5,true);
	connGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"conngridbox"); });
	 connGrid.attachEvent("onRowSelect", function(id,ind){
// 		//수치면서 실행률이 아닌 값을 클릭 했을 때.
		if(ind>5){
			if(!isChartLoading){
				isChartLoading = true;
				drawChart(id,ind);
			}
			
		}
	});
	connGrid.init();
}

/**
 * 접속사용자현황 그리드 초기화
 */
function initUsrGrid(fromDate,toDate)
{
	var months = getMonthInterval(fromDate.replace(/\-/gi, ""),toDate.replace(/\-/gi, ""));
	var statusArr = ["U","C"];
	
    connRptMonthLogListForm.elements['connRptMonthLogListDTO.dateArrStr'].value = getMonths1(fromDate,toDate,months);
    usrGrid = new dhtmlXGridObject('usrgridbox');
    usrGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	usrGrid.setHeader("<bean:message key='LABEL.dept'/>,<bean:message key='LABEL.empName'/>"+get2Months2(fromDate,toDate,months,true,lang)+",<bean:message key='LABEL.total'/>,#cspan");
	usrGrid.attachHeader("#rspan,#rspan"
							+get2Words(months,"<bean:message key='LABEL.isConn'/>","<bean:message key='LABEL.connCnt'/>")
							+",<bean:message key='LABEL.connDate'/>,<bean:message key='LABEL.connCnt'/>");
	usrGrid.setColumnIds("DEPTDESC,EMPNAME"+getMonths22(fromDate,toDate,months,statusArr)+",TOTAL,TOTALSUM");
	usrGrid.setInitWidths("200,100"+getWords(months,"70")+getWords(months,"70")+getWords(months,"70")+",70,70");
	usrGrid.setColAlign("left,left"+getWords(months,"center")+getWords(months,"center")+getWords(months,"center")+",center,center");
	usrGrid.setColTypes("ro,ro"+getWords(months,"ro")+getWords(months,"ro")+getWords(months,"ro")+",ro,ro");
	usrGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"usrgridbox"); });
	usrGrid.init();
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 접속현황 그리드에 셋팅한다.
 */
function findConnGridList()
{
    var url = contextPath + "/connRptMonthLogList.do";
    connRptMonthLogListForm.elements['strutsAction'].value = '<%=ConnRptMonthLogListAction.CONN_LIST_FIND%>';
    connGrid.clearAll();
    setLoading("conngridbox");
    //월별접속현황 그리드 초기화
    initConnGrid(connRptMonthLogListForm.elements['connRptMonthLogListDTO.filterStartDate'].value,connRptMonthLogListForm.elements['connRptMonthLogListDTO.filterEndDate'].value);

    setModal(); 
    
    $.post(url,FormQueryString(connRptMonthLogListForm), function(_data){
    	connGrid.parse(_data,"js");
    	connGrid.expandAll();
    	var strArr = getMonths1(connRptMonthLogListForm.elements['connRptMonthLogListDTO.filterStartDate'].value,
    			connRptMonthLogListForm.elements['connRptMonthLogListDTO.filterEndDate'].value,getMonthInterval(connRptMonthLogListForm.elements['connRptMonthLogListDTO.filterStartDate'].value.replace(/\-/gi, ""),
    			connRptMonthLogListForm.elements['connRptMonthLogListDTO.filterEndDate'].value.replace(/\-/gi, "")));
    	setCounter(connGrid,"conngridbox");
    	findConnChart();
    	findUsrGridList();
    	closeModal();
    });
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 접속자현황 그리드에 셋팅한다.
 */
function findUsrGridList()
{
    var url = contextPath + "/connRptMonthLogList.do";
    connRptMonthLogListForm.elements['strutsAction'].value = '<%=ConnRptMonthLogListAction.CONN_USR_LIST_FIND%>';
    usrGrid.clearAll();
    setLoading("usrgridbox");
    //접속자현황 그리드 초기화
    initUsrGrid(connRptMonthLogListForm.elements['connRptMonthLogListDTO.filterStartDate'].value,connRptMonthLogListForm.elements['connRptMonthLogListDTO.filterEndDate'].value);
    
    $.post(url,FormQueryString(connRptMonthLogListForm), function(_data){
    	usrGrid.parse(_data,"js");
    	var strArr = getMonths11(connRptMonthLogListForm.elements['connRptMonthLogListDTO.filterStartDate'].value,
    			connRptMonthLogListForm.elements['connRptMonthLogListDTO.filterEndDate'].value,getDayInterval(connRptMonthLogListForm.elements['connRptMonthLogListDTO.filterStartDate'].value.replace(/\-/gi, ""),
				connRptMonthLogListForm.elements['connRptMonthLogListDTO.filterEndDate'].value.replace(/\-/gi, "")));
    	for(var i=0; i<strArr.length&&strArr!='';i++){
    		usrGrid.setColumnHidden(1+Number(strArr[i]),true);
    	}
    	
    	
    });
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	//validation
	if(checkRequireValue("connRptMonthLogListDTO.filterStartDate","<bean:message key='LABEL.workDate'/>")) return;
	if(checkRequireValue("connRptMonthLogListDTO.filterEndDate","<bean:message key='LABEL.workDate'/>")) return;
	if(checkTwoDate(connRptMonthLogListForm.elements['connRptMonthLogListDTO.filterStartDate']+"-01",
			connRptMonthLogListForm.elements['connRptMonthLogListDTO.filterEndDate']+"-01")) return;
	
	//검색 기간 제한 (최대 2년)
	var months = getMonthInterval(connRptMonthLogListForm.elements['connRptMonthLogListDTO.filterStartDate'].value.replace(/\-/gi, ""),
			connRptMonthLogListForm.elements['connRptMonthLogListDTO.filterEndDate'].value.replace(/\-/gi, ""));
	if(months>24){
		alertMessage1("<bean:message key='MESSAGE.MSG0229'/>");
		return;
	}
	if(connChart!=null)
	connChart.clearAll(); 
	
	document.getElementById("chartDiv").innerText = "Chart";
		
    findConnGridList();
}

/**
 * 월별접속현황 엑셀 다운
 */
function goConnExcel()
{
	excelAction(connGrid);
} 
/**
 * 접속자현황 엑셀 다운
 */
 function goUsrExcel()
 {
 	excelAction(usrGrid);
 } 
/**
 * 차트 그리기
 **/
function drawChart(id, ind)
{
		//차트 title설정
		document.getElementById("chartDiv").innerText = connGrid.cells(id, connGrid.getColIndexById("DEPTDESC")).getValue();
		connRptMonthLogListForm.elements['connRptMonthLogListDTO.deptId'].value = connGrid.cells(id, connGrid.getColIndexById("DEPTID")).getValue();
		findConnChart();
		findUsrGridList();
}
 /**
  * 현재 셋팅된 조건으로 값을 조회하여 라인고장분석 차트에 셋팅한다.
  */
 function findConnChart()
 {
     var url = contextPath + "/connRptMonthLogList.do";
     connRptMonthLogListForm.elements['strutsAction'].value = '<%=ConnRptMonthLogListAction.CONN_CHART_FIND%>';
     connChart.clearAll(); 
     
     $.post(url,FormQueryString(connRptMonthLogListForm), function(_data){
     	connChart.parse(_data,"json");
     	isChartLoading = false;
     });
 }
 
 function initChart(){
		connChart =  new dhtmlXChart({
			view:"line",
			container:"chartbox",
			value:"#CNT#",
			label:"#CNT#",
			tooltip:{
				template:"#CNT#"
			},
			item:{
				borderColor: "#FF7171",
				color: "#ffffff"
			},
			line:{
				color:"#FF7171",
				width:1
			},
			xAxis:{
				template:"'#MONTHFORMAT#"
			},
			offset:0,
			yAxis:{
				start:0,
				step:1
			},
			origin:0,
			legend:{
				layout:"x",
				width: 75,
				align:"right",
				toggle:false,
				valign:"bottom",
				values:[
					{text:"<bean:message key='LABEL.connCnt'/>",color:"#FF7171"}
				],
				margin: 5
			}
		});
	}
 
/**
 *fromDate, toDate 사이 날짜를 2개씩 반환
 * 이페이지에서 만 사용...
 */
 function get2Months1(fromDate,toDate,months,statusArr)
 {
	var str = "";
	var arr1 = new Array();
	arr1 = toDate.split('-');

	toDate = arr1[0]+"-"+(arr1[1].substring(0,1)=="0"?arr1[1].substring(1,2):arr1[1])+"-1";
	arr1 = toDate.split('-');
	
	for(var i=months; i>=0; i--){
		var date1 = new Date(arr1[0], arr1[1]-1, arr1[2]);
		var date = getMinusMonth2(dat1,-(i));
		
		str +=","+getMinusMonth2(dat1,-(i));
		str +=","+getMinusMonth2(dat1,-(i));
	}
	return str;
}
 /**
  * fromDate와 toDate사이의 일자를 반환(format:,2/16(월),2/17(화))
  *  2개씩 반환
  * 이페이지에서만 사용..
  */
 function get2Months2(fromDate,toDate,months,isColspan,lang){
	var str = "";
	arr1 = new Array();
 	arr1 = toDate.split('-');

	toDate = arr1[0]+"-"+(arr1[1].substring(0,1)=="0"?arr1[1].substring(1,2):arr1[1])+"-1";
	arr1 = toDate.split('-');

 	for(var i=months; i>=0; i--){
	 	var date1 = new Date(arr1[0], arr1[1]-1, arr1[2]);
 		var dateArr = getMinusMonth2(date1,-(i)).split("-");
 		var dateStr = getMinusMonth2(date1,-(i)).replace(/\-/gi, "");
		var year   	= dateArr[0];
		var month   = dateArr[1];
		
 		str +=","+year+"-"+month;
 		if(isColspan){
 			str +=",#cspan"; 
 		}else{
 			str +=","+year+"-"+month; 
 		}
 	}
 	return str;
 }
 /**
  *fromDate, toDate 사이에 년-월을 조회 (2018-04, 2018-05)
  * 이페이지에서 만 사용...
  */
 function getMonths1(fromDate,toDate,months){
		var str = "";
		var arr1 = toDate.split('-');
	
		toDate = arr1[0]+"-"+(arr1[1].substring(0,1)=="0"?arr1[1].substring(1,2):arr1[1])+"-1";
		arr1 = toDate.split('-');
	
		for(var i=months; i>=0; i--){
			var dat1 = new Date(arr1[0], arr1[1]-1, arr1[2]);
			var date = getMinusMonth2(dat1,-(i));
			
			var dateArr = date.split("-");
			var dateStr = date.replace(/\-/gi, "");
			var year   	= dateArr[0];
			var month   = dateArr[1];
			
			if(i == months) str = year+"-"+month;
			else	str +=","+year+"-"+month; 
		}
		return str;
 }
 function getMonths11(fromDate,toDate,months){
		var str = "";
		var arr1 = toDate.split('-');
	
		toDate = arr1[0]+"-"+(arr1[1].substring(0,1)=="0"?arr1[1].substring(1,2):arr1[1])+"-1";
		arr1 = toDate.split('-');
	
		for(var i=months; i>=0; i--){
			var dat1 = new Date(arr1[0], arr1[1]-1, arr1[2]);
			var date = getMinusMonth2(dat1,-(i));
			
			var dateArr = date.split("-");
			var dateStr = date.replace(/\-/gi, "");
			var year   	= dateArr[0];
			var month   = dateArr[1];
			
			if(i == months) str = year+"-"+month;
			else	str +=","+year+"-"+month; 
		}
		return str;
 }
 /**
 *fromDate, toDate 사이에 년-월을 조회 (,2018-04, 2018-05)
 * 이페이지에서 만 사용...
 */
 function getMonths2(fromDate,toDate,months){
		var str = "";
		var arr1 = toDate.split('-');
	
		toDate = arr1[0]+"-"+(arr1[1].substring(0,1)=="0"?arr1[1].substring(1,2):arr1[1])+"-1";
		arr1 = toDate.split('-');
	
		for(var i=months; i>=0; i--){
			var dat1 = new Date(arr1[0], arr1[1]-1, arr1[2]);
			var date = getMinusMonth2(dat1,-(i));
			
			var dateArr = date.split("-");
			var dateStr = date.replace(/\-/gi, "");
			var year   	= dateArr[0];
			var month   = dateArr[1];
			
			str +=","+year+"-"+month; 
		}
		return str;
	}
 /**
 *fromDate, toDate 사이에 년-월을 조회 (,2018-04, 2018-05)
 * 이페이지에서 만 사용...
 */
 function getMonths22(fromDate,toDate,months,statusArr){
		var str = "";
		var arr1 = toDate.split('-');
	
		toDate = arr1[0]+"-"+(arr1[1].substring(0,1)=="0"?arr1[1].substring(1,2):arr1[1])+"-1";
		arr1 = toDate.split('-');
	
		for(var i=months; i>=0; i--){
			var dat1 = new Date(arr1[0], arr1[1]-1, arr1[2]);
			var date = getMinusMonth2(dat1,-(i));
			
			var dateArr = date.split("-");
			var dateStr = date.replace(/\-/gi, "");
			var year   	= dateArr[0];
			var month   = dateArr[1];
			
			str +=","+statusArr[0]+year+"-"+month; 
			str +=","+statusArr[1]+year+"-"+month; 
		}
		return str;
	}
 /**
  * val1,val2 문자열을 days수만큼 연속해서 반환
  */
 function get2Words(days,val1,val2){
 	var str = "";
 	for(var i=days; i>=0; i--){
 		str +=","+val1;
 		str +=","+val2;
 	}
 	return str;
 }

//윈도우 사이즈 재조정시 차트 크기 조정
$(window).resize(function(){
 connChart.resize();
});
 
//월별접속현황 빼고 접기.
 function foldList(){
 	var filterObj = $(".list:not('.not_fold')");
 	filterObj.find('.b_fold').removeClass('b_fold').addClass('b_unfold');

 	filterObj.parents('.section_wrap').find('.article_box, .accordion_wrap').hide("fast", function(){
 		if(typeof resizeTabFrame == "function") resizeTabFrame();
 	 });
 	
 	filterObj.find('a').each(function(index){
 		if(!$(this).is('.b_unfold,.b_fold,.b_close')) $(this).hide();
 	});
 	
 	filterObj.parents('.section_wrap').find('.stitle_icon').removeClass('stitle_icon').addClass('stitle_icon_up');
 }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/connRptMonthLogList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="connRptMonthLogListDTO.dateArrStr"/>
<html:hidden property="connRptMonthLogListDTO.deptId"/>
<html:hidden property="connRptMonthLogListDTO.filterDeptId"/>
<html:hidden property="connRptMonthLogListDTO.filterEmpId"/>
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
				<!-- 월 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.month"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="connRptMonthLogListDTO.filterStartDate" tabindex="10" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="connRptMonthLogListDTO.filterEndDate" tabindex="20" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 부서 -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="connRptMonthLogListDTO.filterDeptDesc" tabindex="30"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 사원 -->
				<div class="field">
					<label><bean:message key="LABEL.emp"/></label>
					<div class="input_box">
						<html:text property="connRptMonthLogListDTO.filterEmpDesc" tabindex="10"/>
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
			<div class="function_box list not_fold">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>

				<div class="fb_group2">
					<a href="javascript:goConnExcel();" class="b_excel"><span><bean:message key="BUTTON.EXCEL"/></span></a>
				</div>
			</div>
		</div><!--sheader_box end-->
		<div class="article_box">
			<div class="grid_area">
				<div id="conngridbox" style="width:100%; height:270px; background-color:white;"></div>
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
			<div class="function_box list not_fold">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>

				<div class="fb_group2">
				</div>
			</div>
		</div>
		<div class="article_box">
			<div class="grid_area">
				<div id="chartbox" style="width:100%;height:270px;background-color:white;"></div>
			</div>
		</div>
	</div> 
	<div class="section_wrap">
		<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx" ><bean:message key="LABEL.connUsrChart"/></div>
			</div>
			<div class="function_box list">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>

				<div class="fb_group2">
					<a href="javascript:goUsrExcel();" class="b_excel"><span><bean:message key="BUTTON.EXCEL"/></span></a>
				</div>
			</div>
		</div><!--sheader_box end-->
		<div class="article_box">
			<div class="grid_area">
				<div id="usrgridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>
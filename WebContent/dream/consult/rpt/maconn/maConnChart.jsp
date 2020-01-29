<%--===========================================================================
접속현황
author  kim21017
version $Id: maConnChart.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.rpt.maconn.action.MaConnChartAction" %>
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
<title><bean:message key="MENU.SYSCONNLIST"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//
//그리드명
var connGrid;			//접속현황 그리드
var usrGrid;			//접속자현황 그리드
var connChart;			//접속현황 차트
var isChartLoading = false;
var lang = loginUser.locale;

/** 자동완성 변수 */
var deptAc;
function loadPage() 
{
	//일자 세팅
	maConnChartForm.elements['maConnChartDTO.filterStartDate'].value = getMinusDay(5);
	maConnChartForm.elements['maConnChartDTO.filterEndDate'].value   = getMinusDay(1);

	//전체접속현황 그리드 초기화
	initConnGrid(maConnChartForm.elements['maConnChartDTO.filterStartDate'].value,
			maConnChartForm.elements['maConnChartDTO.filterEndDate'].value);
	//접속자현황 그리드 초기화
	initUsrGrid(maConnChartForm.elements['maConnChartDTO.filterStartDate'].value,
			maConnChartForm.elements['maConnChartDTO.filterEndDate'].value);
	goSearch();
	//차트 초기화
	initChart();
	
	deptAc = new autoC({"maConnChartDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	,"is_monitoring":"Y"
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maConnChartDTO.filterDeptId":"dept_id"
    });
    deptAc.setKeyName("maConnChartDTO.filterDeptId");
    deptAc.init();
}

/**
 * 접속현황 그리드 초기화
 */
function initConnGrid(fromDate,toDate)
{
	var days = getDayInterval(fromDate.replace(/\-/gi, ""),toDate.replace(/\-/gi, ""));
	var statusArr = ["USR_MCNT","USR_ACNT","USR_LCNT"];
    maConnChartForm.elements['maConnChartDTO.dateArrStr'].value = getDays(fromDate,toDate,days);
    connGrid = new dhtmlXGridObject('conngridbox');
    connGrid.enableTreeGridLines();
    connGrid.setImageSize(1,1);
    connGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	connGrid.setHeader("<bean:message key='LABEL.dept'/>,,,,,"+get3Days2(fromDate,toDate,days,true,lang));
	connGrid.attachHeader("#rspan,,,,,"+get3Words(days,"<bean:message key='LABEL.targetUsrCnt'/>"
																			  ,"<bean:message key='LABEL.connUsrCnt'/>"
																			  ,"<bean:message key='LABEL.connCnt'/>"));
	connGrid.setColumnIds("DEPTDESC,ID,LVL,MINLVL,DEPTID,PDEPTID"+get3Days(fromDate,toDate,days,statusArr));
	connGrid.setInitWidths("200,100,100,100,100,100"+getWords(days,"70")+getWords(days,"70")+getWords(days,"70"));
	connGrid.setColAlign("left,left,left,left,left,left"+getWords(days,"center")+getWords(days,"center")+getWords(days,"center"));
	connGrid.setColTypes("tree,ro,ro,ro,ro,ro"+getWords(days,"ro")+getWords(days,"ro")+getWords(days,"ro"));
	connGrid.setColumnColor(getWeekendColor("DEPTDESC,ID,LVL,MINLVL,DEPTID,PDEPTID"+get3Days1(fromDate,toDate,days)));
	connGrid.setColumnHidden(1,true);
	connGrid.setColumnHidden(2,true);
	connGrid.setColumnHidden(3,true);
	connGrid.setColumnHidden(4,true);
	connGrid.setColumnHidden(5,true);
	connGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"conngridbox"); });
	connGrid.attachEvent("onRowSelect", function(id,ind){
// 		//수치면서 실행률이 아닌 값을 클릭 했을 때.
		if(ind>5){
				//alert(isChartLoading);
			if(!isChartLoading){
				isChartLoading = true;
				drawChart(id,ind);
				
				//사용자 검색
				findUsrGridList(false);
			}
		}
	});
	connGrid.init();
	//connGrid.splitAt(1);
	
	var strArr = getWeekSeqNo(get3Days1(maConnChartForm.elements['maConnChartDTO.filterStartDate'].value,
			maConnChartForm.elements['maConnChartDTO.filterEndDate'].value,getDayInterval(maConnChartForm.elements['maConnChartDTO.filterStartDate'].value.replace(/\-/gi, ""),maConnChartForm.elements['maConnChartDTO.filterEndDate'].value.replace(/\-/gi, "")))).split(",");
	for(var i=0; i<strArr.length&&strArr!='';i++){
		connGrid.setColumnHidden(5+Number(strArr[i]),true);
	}
}

/**
 * 접속사용자현황 그리드 초기화
 */
function initUsrGrid(fromDate,toDate)
{
	var days = getDayInterval(fromDate.replace(/\-/gi, ""),toDate.replace(/\-/gi, ""));
	var statusArr = ["U","C"];
    maConnChartForm.elements['maConnChartDTO.dateArrStr'].value = getDays(fromDate,toDate,days);
    usrGrid = new dhtmlXGridObject('usrgridbox');
    usrGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	usrGrid.setHeader("<bean:message key='LABEL.dept'/>,<bean:message key='LABEL.userName'/>"+get2Days2(fromDate,toDate,days,true,lang)+",<bean:message key='LABEL.total'/>,#cspan");
	usrGrid.attachHeader("#rspan,#rspan"+get2Words(days,"<bean:message key='LABEL.isConn'/>"
													   ,"<bean:message key='LABEL.connCnt'/>")
													   +",<bean:message key='LABEL.connDate'/>,<bean:message key='LABEL.connCnt'/>");
	usrGrid.setColumnIds("DEPTDESC,USERNAME"+get2Days(fromDate,toDate,days,statusArr)+",TOTAL,TOTALSUM");
	usrGrid.setInitWidths("200,100"+getWords(days,"70")+getWords(days,"70")+getWords(days,"70")+",70,70");
	usrGrid.setColAlign("left,left"+getWords(days,"center")+getWords(days,"center")+getWords(days,"center")+",center,center");
	usrGrid.setColTypes("ro,ro"+getWords(days,"ro")+getWords(days,"ro")+getWords(days,"ro")+",ro,ro");
	usrGrid.setColumnColor(getWeekendColor("DEPTDESC,USERNAME"+get2Days1(fromDate,toDate,days)+",TOTAL,TOTALSUM"));
	usrGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"usrgridbox"); });
	usrGrid.init();
	
	var strArr = getWeekSeqNo(get2Days1(maConnChartForm.elements['maConnChartDTO.filterStartDate'].value,
			maConnChartForm.elements['maConnChartDTO.filterEndDate'].value,getDayInterval(maConnChartForm.elements['maConnChartDTO.filterStartDate'].value.replace(/\-/gi, ""),maConnChartForm.elements['maConnChartDTO.filterEndDate'].value.replace(/\-/gi, "")))).split(",");
	for(var i=0; i<strArr.length&&strArr!='';i++){
		usrGrid.setColumnHidden(1+Number(strArr[i]),true);
	}
	
	isHeaderLoaded[currentPageId+".usrgridbox"] = "Y";
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 접속현황 그리드에 셋팅한다.
 */
function findConnGridList(sheetAction)
{
    var url = contextPath + "/maConnChart.do";
    maConnChartForm.elements['strutsAction'].value = '<%=MaConnChartAction.CONN_LIST_FIND%>';
    connGrid.clearAll();
    setLoading("conngridbox");
    //전체접속현황 그리드 초기화
    initConnGrid(maConnChartForm.elements['maConnChartDTO.filterStartDate'].value,maConnChartForm.elements['maConnChartDTO.filterEndDate'].value);
    
    $.post(url,FormQueryString(maConnChartForm), function(_data){
    	connGrid.parse(_data,"js");
    	makeGridMap(connGrid, "conngridbox", '<%=MaConnChartAction.CONN_LIST_FIND%>');
    	connGrid.expandAll();
    	
    	setCounter(connGrid,"conngridbox");
    	findUsrGridList(true);
    });
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 접속자현황 그리드에 셋팅한다.
 */
function findUsrGridList(isInit)
{
    var url = contextPath + "/maConnChart.do";
    maConnChartForm.elements['strutsAction'].value = '<%=MaConnChartAction.CONN_USR_LIST_FIND%>';
    usrGrid.clearAll();
    setLoading("usrgridbox");
    //접속자현황 그리드 초기화
    if(isInit==true){
    	initUsrGrid(maConnChartForm.elements['maConnChartDTO.filterStartDate'].value,maConnChartForm.elements['maConnChartDTO.filterEndDate'].value);
    }
    
    doGridAction("Search", usrGrid, "usrgridbox", url, FormQueryString(maConnChartForm), "");
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	//validation
	if(checkRequireValue("maConnChartDTO.filterStartDate","<bean:message key='LABEL.workDate'/>")) return;
	if(checkRequireValue("maConnChartDTO.filterEndDate","<bean:message key='LABEL.workDate'/>")) return;
	if(checkTwoDate(maConnChartForm.elements['maConnChartDTO.filterStartDate'],
			maConnChartForm.elements['maConnChartDTO.filterEndDate'])) return;
	
	//검색 기간 제한 
	var days = getDayInterval(maConnChartForm.elements['maConnChartDTO.filterStartDate'].value.replace(/\-/gi, ""),
			maConnChartForm.elements['maConnChartDTO.filterEndDate'].value.replace(/\-/gi, ""));
	if(days>14){
		alertMessage1("<bean:message key='MESSAGE.MSG0224'/>");
		return;
	}
	if(connChart!=null)
	connChart.clearAll(); 
	
	document.getElementById("chartDiv").innerText = "Chart";
	maConnChartForm.elements['maConnChartDTO.deptId'].value = '';
    
	findConnGridList("SearchTree");
}
function getWeekSeqNo(str){
	var strArr = str.split(",");
	str = "";
	for(var i=0; i<strArr.length;i++){
		var day = strArr[i].replace(/\-/gi, "");
		//일요일
		if(new Date(day.substring(0, 4), day.substring(4, 6) - 1, day.substring(6)).getDay()==0){
			str +=","+i;
		}
		//토요일
		else if(new Date(day.substring(0, 4), day.substring(4, 6) - 1, day.substring(6)).getDay()==6){
			str +=","+i;
		}else{
			str +="";
		}
	}
	
	str = str.substring(1,str.length);
	return str;
}
/**
 * 주말 row 색 변경.
 */
function getWeekendColor(str){
	var strArr = str.split(",");
	str = "";
	for(var i=0; i<strArr.length;i++){
		var day = strArr[i].replace(/\-/gi, "");
		//일요일
		if(new Date(day.substring(0, 4), day.substring(4, 6) - 1, day.substring(6)).getDay()==0){
			str +=",#f8f8f8";
		}
		//토요일
		else if(new Date(day.substring(0, 4), day.substring(4, 6) - 1, day.substring(6)).getDay()==6){
			str +=",#f8f8f8";
		}else{
			str +=",";
		}
	}
	
	str = str.substring(1,str.length);
	return str;
}

function goExcel()
{
	excelServerAction("maConnChart", maConnChartForm);
}
/**
 * 차트 그리기
 **/
function drawChart(id, ind){
	//차트 title설정
	document.getElementById("chartDiv").innerText = 
		connGrid.cells(id, connGrid.getColIndexById("DEPTDESC")).getValue();
	maConnChartForm.elements['maConnChartDTO.deptId'].value = connGrid.cells(id, connGrid.getColIndexById("DEPTID")).getValue();
	findConnChart();
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 라인고장분석 차트에 셋팅한다.
 */
function findConnChart()
{
    var url = contextPath + "/maConnChart.do";
    maConnChartForm.elements['strutsAction'].value = '<%=MaConnChartAction.CONN_CHART_FIND%>';
    connChart.clearAll(); 
    
    $.post(url,FormQueryString(maConnChartForm), function(_data){
    	connChart.parse(_data,"json");
    	isChartLoading = false;
    });
}
function initChart(){
	connChart =  new dhtmlXChart({
		view:"line",
		container:"chartbox",
		value:"#CNT1#",
		label:"#CNT1#",
		tooltip:{
			template:"#CNT1#"
		},
		item:{
			borderColor: "#555555",
			color: "#ffffff"
		},
		line:{
			color:"#555555",
			width:1
		},
		xAxis:{
			template:"'#DAYFORMAT#"
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
				{text:"<bean:message key='LABEL.connUsrCnt'/>",color:"#555555"},
				{text:"<bean:message key='LABEL.connCnt'/>",color:"#FF7171"}
			],
			margin: 5
		}
	});
	connChart.addSeries({
		value:"#CNT2#",
		label:"#CNT2#",
		tooltip:{
			template:"#CNT2#"
		},item:{
			borderColor: "#FF7171",
			color: "#ffffff"
		},
		line:{
			color:"#FF7171",
			width:1
		}
	});
}
/**
 *fromDate, toDate 사이 날짜를 3개씩 반환
 * 이페이지에서 만 사용...
 */
function get3Days1(fromDate,toDate,days,statusArr){
	var str = "";
	var arr1 = new Array();
	arr1 = toDate.split('-');

	toDate = arr1[0]+"-"+(arr1[1].substring(0,1)=="0"?arr1[1].substring(1,2):arr1[1])+"-"+(arr1[2].substring(0,1)=="0"?arr1[2].substring(1,2):arr1[2]);
	arr1 = toDate.split('-');

	var date1 = new Date(arr1[0], arr1[1]-1, arr1[2]);
	for(var i=days; i>=0; i--){
		str +=","+getMinusDay2(date1,i);
		str +=","+getMinusDay2(date1,i);
		str +=","+getMinusDay2(date1,i);
	}
	return str;
}
/**
 *fromDate, toDate 사이 날짜를 2개씩 반환
 * 이페이지에서 만 사용...
 */
 function get2Days1(fromDate,toDate,days,statusArr){
		var str = "";
		var arr1 = new Array();
		arr1 = toDate.split('-');

		toDate = arr1[0]+"-"+(arr1[1].substring(0,1)=="0"?arr1[1].substring(1,2):arr1[1])+"-"+(arr1[2].substring(0,1)=="0"?arr1[2].substring(1,2):arr1[2]);
		arr1 = toDate.split('-');

		var date1 = new Date(arr1[0], arr1[1]-1, arr1[2]);
		for(var i=days; i>=0; i--){
			str +=","+getMinusDay2(date1,i);
			str +=","+getMinusDay2(date1,i);
		}
		return str;
	}
/**
 *fromDate, toDate 사이 날짜를 3개씩 반환
 * statusArr를 순서대로 날짜 앞에 붙혀준다...
 * 이페이지에서 만 사용...
 */
function get3Days(fromDate,toDate,days,statusArr){
		var str = "";
		var arr1 = new Array();
	arr1 = toDate.split('-');

	toDate = arr1[0]+"-"+(arr1[1].substring(0,1)=="0"?arr1[1].substring(1,2):arr1[1])+"-"+(arr1[2].substring(0,1)=="0"?arr1[2].substring(1,2):arr1[2]);
	arr1 = toDate.split('-');

	var date1 = new Date(arr1[0], arr1[1]-1, arr1[2]);
	for(var i=days; i>=0; i--){
		str +=","+statusArr[0]+getMinusDay2(date1,i);
		str +=","+statusArr[1]+getMinusDay2(date1,i);
		str +=","+statusArr[2]+getMinusDay2(date1,i);
	}
	return str;
}
/**
 *fromDate, toDate 사이 날짜를 3개씩 반환
 * statusArr를 순서대로 날짜 앞에 붙혀준다...
 * 이페이지에서 만 사용...
 */
 function get2Days(fromDate,toDate,days,statusArr){
		var str = "";
		var arr1 = new Array();
		arr1 = toDate.split('-');

		toDate = arr1[0]+"-"+(arr1[1].substring(0,1)=="0"?arr1[1].substring(1,2):arr1[1])+"-"+(arr1[2].substring(0,1)=="0"?arr1[2].substring(1,2):arr1[2]);
		arr1 = toDate.split('-');

		var date1 = new Date(arr1[0], arr1[1]-1, arr1[2]);
		for(var i=days; i>=0; i--){
			str +=","+statusArr[0]+getMinusDay2(date1,i);
			str +=","+statusArr[1]+getMinusDay2(date1,i);
		}
		return str;
	}
/**
 * fromDate와 toDate사이의 일자를 반환(format:,2/16(월),2/17(화))
 *  3개씩 반환
 * 이페이지에서만 사용..
 */
function get3Days2(fromDate,toDate,days,isColspan,lang){
var str = "";
arr1 = new Array();
	arr1 = toDate.split('-');

	toDate = arr1[0]+"-"+(arr1[1].substring(0,1)=="0"?arr1[1].substring(1,2):arr1[1])+"-"+(arr1[2].substring(0,1)=="0"?arr1[2].substring(1,2):arr1[2]);
	arr1 = toDate.split('-');

	var date1 = new Date(arr1[0], arr1[1]-1, arr1[2]);
	for(var i=days; i>=0; i--){
		var dateArr = getMinusDay2(date1,i).split("-");
		var dateStr = getMinusDay2(date1,i).replace(/\-/gi, "");
		var month   = dateArr[1].substring(0,1)=="0"?dateArr[1].substring(1,2):dateArr[1];
		var day     = dateArr[2].substring(0,1)=="0"?dateArr[2].substring(1,2):dateArr[2];
		
		str +=","+month+"/"+day+"("+setComDay('"'+dateStr+'"',""+lang+"")+")";
		if(isColspan){
			str +=",#cspan"; 
	 		str +=",#cspan"; 
		}else{
			str +=","+month+"/"+day+"("+setComDay('"'+dateStr+'"')+")"; 
	 		str +=","+month+"/"+day+"("+setComDay('"'+dateStr+'"')+")"; 
		}
	}
	return str;
}
/**
 * fromDate와 toDate사이의 일자를 반환(format:,2/16(월),2/17(화))
 *  2개씩 반환
 * 이페이지에서만 사용..
 */
function get2Days2(fromDate,toDate,days,isColspan,lang){
var str = "";
arr1 = new Array();
	arr1 = toDate.split('-');

	toDate = arr1[0]+"-"+(arr1[1].substring(0,1)=="0"?arr1[1].substring(1,2):arr1[1])+"-"+(arr1[2].substring(0,1)=="0"?arr1[2].substring(1,2):arr1[2]);
	arr1 = toDate.split('-');

	var date1 = new Date(arr1[0], arr1[1]-1, arr1[2]);
	for(var i=days; i>=0; i--){
		var dateArr = getMinusDay2(date1,i).split("-");
		var dateStr = getMinusDay2(date1,i).replace(/\-/gi, "");
		var month   = dateArr[1].substring(0,1)=="0"?dateArr[1].substring(1,2):dateArr[1];
		var day     = dateArr[2].substring(0,1)=="0"?dateArr[2].substring(1,2):dateArr[2];
		
		str +=","+month+"/"+day+"("+setComDay('"'+dateStr+'"',""+lang+"")+")";
		if(isColspan){
			str +=",#cspan"; 
		}else{
			str +=","+month+"/"+day+"("+setComDay('"'+dateStr+'"',""+lang+"")+")"; 
		}
	}
	return str;
}
/**
 * val1,val2,val3 문자열을 days수만큼 연속해서 반환
 */
function get3Words(days,val1,val2,val3){
	var str = "";
	for(var i=days; i>=0; i--){
		str +=","+val1;
		str +=","+val2;
		str +=","+val3;
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

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maConnChart.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maConnChartDTO.dateArrStr"/>
<html:hidden property="maConnChartDTO.type"/>
<html:hidden property="maConnChartDTO.deptId"/>
<html:hidden property="maConnChartDTO.filterDeptId"/>
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
					<label class="check"><bean:message key="LABEL.workDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="maConnChartDTO.filterStartDate" tabindex="10" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="maConnChartDTO.filterEndDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 부서 -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="maConnChartDTO.filterDeptDesc" tabindex="30"/>
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
			<div class="function_box chart not_fold">
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
				<div id="usrgridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>
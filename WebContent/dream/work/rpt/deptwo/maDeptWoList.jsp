<%--===========================================================================
부서별작업분석
author  kim21017
version $Id: maDeptWoList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.madeptwo.action.MaDeptWoListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<title><bean:message key="MENU.DEPTWO"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 <style>
		.label{
			background-color:#ffffff;
			-moz-border-radius:4px;
			-ms-border-radius:4px;
			-webkit-border-radius:4px;
			border-radius:4px;
			height:15px;
			line-height:15px;
			font-size: 9px;
			width:25px;
			text-align:center;
		}
	</style>
<script language="javascript">
<!--//

var myGrid;
var cntChart;		//건수 pie chart
var timeChart;		//시간 pie chart

// var testChart;		//테스트

var isChartLoading = false;

var cntData;
var timeData;
// var testData;//테스트

var deptAc;
var equipDescAc;
var eqLocDescAc;
var eqCtgTypeAc;
var mainMngAc;
var subMngAc;
var plantAc;

function loadPage() 
{
	//일자 세팅
	maDeptWoListForm.elements['maDeptWoListDTO.filterStartDate'].value = getMinusDay(7);
	maDeptWoListForm.elements['maDeptWoListDTO.filterEndDate'].value   = getToday();
	if(loginUser.eqLocId!='null'){
		maDeptWoListForm.elements['maDeptWoListDTO.filterEqLocId'].value = loginUser.eqLocId;
		maDeptWoListForm.elements['maDeptWoListDTO.filterEqLocDesc'].value = loginUser.eqLocDesc;
	}
	
	//공장명
    if(loginUser.filterPlant!='null'){
    	maDeptWoListForm.elements['maDeptWoListDTO.filterPlantId'].value  = loginUser.filterPlant;
    	maDeptWoListForm.elements['maDeptWoListDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
	
	//부서정보 세팅
    if(loginUser.filterDeptId!='null'){
    	maDeptWoListForm.elements['maDeptWoListDTO.filterDeptId'].value    = loginUser.filterDeptId;
		maDeptWoListForm.elements['maDeptWoListDTO.filterDeptDesc'].value  = loginUser.filterDeptDesc;
    }
	
    initGrid();

	//건수차트 초기화
	initCntChart();
	//시간차트 초기화
	initTimeChart();
	//테스트
// 	initTestChart();
	
    deptAc = new autoC({"maDeptWoListDTO.filterDeptDesc":"description"});
    deptAc.setAcDisplay("DESCRIPTION");
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maDeptWoListDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "maDeptWoListDTO.filterPlantId"
    });
    deptAc.init();
    
    equipDescAc = new autoC({"maDeptWoListDTO.filterEquipDesc":"description"});
    equipDescAc.setAcDisplay("DESCRIPTION");
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcResultMap({
        "maDeptWoListDTO.filterEquipId":"equip_id"
    });
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "maDeptWoListDTO.filterEqLocId",
    	"eqctg_id" : "maDeptWoListDTO.filterEqCtgId",
    	"dept_id" : "maDeptWoListDTO.filterDeptId",
    	"plant" : "maDeptWoListDTO.filterPlantId"
    });
    equipDescAc.init();
    
    eqLocDescAc = new autoC({"maDeptWoListDTO.filterEqLocDesc":"full_desc"});
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcResultMap({
        "maDeptWoListDTO.filterEqLocId":"eqloc_id"
    });
    eqLocDescAc.setAcDConditionMap({
    	"plant" : "maDeptWoListDTO.filterPlantId"
    });
    eqLocDescAc.init();
    
    eqCtgTypeAc = new autoC({"maDeptWoListDTO.filterEqCtgDesc":"description"});
    eqCtgTypeAc.setAcDisplay("DESCRIPTION");
    eqCtgTypeAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    eqCtgTypeAc.setTable("TAEQCTG");
    eqCtgTypeAc.setAcResultMap({
        "maDeptWoListDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgTypeAc.init();

	//법정설비유형  AC
    acSysDesc("maDeptWoListDTO.filterIsLawEq","maDeptWoListDTO.filterIsLawEq","IS_USE",true);
    
    mainMngAc = new autoC({"maDeptWoListDTO.filterMainMngName":"emp_name"});
    mainMngAc.setAcDisplay("EMP_NAME");
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maDeptWoListDTO.filterMainMngId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maDeptWoListDTO.filterDeptId",
    	"plant" : "maDeptWoListDTO.filterPlantId"
    });
    mainMngAc.init();
    
    subMngAc = new autoC({"maDeptWoListDTO.filterSubMngName":"emp_name"});
    subMngAc.setAcDisplay("EMP_NAME");
    subMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    subMngAc.setTable("TAEMP");
    subMngAc.setAcResultMap({
        "maDeptWoListDTO.filterSubMngId":"emp_id"
    });
    subMngAc.setAcDConditionMap({
    	"dept_id" : "maDeptWoListDTO.filterDeptId",
    	"plant" : "maDeptWoListDTO.filterPlantId"
    });
    subMngAc.init();
    
 	// 공장코드
	plantAc = new autoC({"maDeptWoListDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "maDeptWoListDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
	//내/외자
    acSysDesc("maDeptWoListDTO.filterPlfTypeDesc","maDeptWoListDTO.filterPlfTypeId","PLF_TYPE",true);
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	//헤어윗줄
	myGrid.setHeader("<bean:message key='LABEL.seqNo'/>,,,<bean:message key='LABEL.dept'/>,<bean:message key='LABEL.cnt2'/>,#cspan"
			+",#cspan,#cspan"
			+",<bean:message key='LABEL.timeMin'/>,#cspan"
			+",#cspan,#cspan,,"
	);
	//헤더 아랫줄
	myGrid.attachHeader(["#rspan","","","#rspan","<bean:message key='LABEL.total'/>","<bean:message key='LABEL.bmWo'/>"
                         ,"<bean:message key='LABEL.pmWo'/>","<bean:message key='CODESET.WO_TYPE.CM'/>"
                         ,"<bean:message key='LABEL.total'/>","<bean:message key='LABEL.bmWo'/>"
                         ,"<bean:message key='LABEL.pmWo'/>","<bean:message key='CODESET.WO_TYPE.CM'/>","",""
	]);
	//헤더 아이디
	myGrid.setColumnIds("SEQNO,DEPTID,PDEPTID,DEPTDESC,TOTALCNT,BMCNT"
			  +",PMCNT,CMCNT"
			  +",TOTALTIME,BMTIME"
			  +",PMTIME,CMTIME,LEVEL,MINLVL"
	);
	myGrid.setInitWidths("60,50,50,200,80,80,80,80,80,80,80,80,50,50");
	myGrid.setColAlign("center,left,left,left,right,right,right,right,right,right,right,right,left,left");
	myGrid.setColTypes("cntr,ro,ro,tree,ron[=sum],ron[=sum],ron[=sum],ron[=sum],ron[=sum],ron[=sum],ron[=sum],ron[=sum],ro,ro");
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		selectedId = rowId;
		isChartLoading = true;
		//건수 데이터 
		cntData = 	[{"TITLE":"<bean:message key='LABEL.break'/>","VALUE":""+getValueById(myGrid, selectedId, 'BMCNT')+"","COLOR":"#ff5959"},
					{"TITLE":"<bean:message key='LABEL.prevent'/>","VALUE":""+getValueById(myGrid, selectedId, 'PMCNT')+"","COLOR":"#c6ea1e"},
					{"TITLE":"<bean:message key='LABEL.improve'/>","VALUE":""+getValueById(myGrid, selectedId, 'CMCNT')+"","COLOR":"#ffcc33"}];
		//시간 데이터 
		timeData = [{"TITLE":"<bean:message key='LABEL.break'/>","VALUE":""+getValueById(myGrid, selectedId, 'BMTIME')+"","COLOR":"#ff5959"},
					{"TITLE":"<bean:message key='LABEL.prevent'/>","VALUE":""+getValueById(myGrid, selectedId, 'PMTIME')+"","COLOR":"#c6ea1e"},
					{"TITLE":"<bean:message key='LABEL.improve'/>","VALUE":""+getValueById(myGrid, selectedId, 'CMTIME')+"","COLOR":"#ffcc33"}];
		//테스트
// 		var myArray = [[1,5,10,7,11,2,4,9,24,33,21,8],[100,500,700,300,500,630,200,128,900,1100,388,322],[25,70,2,40,57,86,76,32,98,92,35,77]];
// 		var monthArray = ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'];
// 		var tempArr = new Array();
// 		var sumArr = new Array();
// 		for(var i=0; i<myArray.length;i++){
// 			tempArr.push(Math.max.apply(null, myArray[i]));
// 		}
// 		var maxVal = Math.max.apply(null, tempArr);
// 		for(var i=0; i<myArray.length;i++){
// 			sumArr[i] = new Array();
// 			for(var j=0; j<myArray[i].length;j++){
// 				var max = Math.max.apply(null, myArray[i]);
// 				var ell = maxVal/max;
// 				sumArr[i][j] = Math.round(myArray[i][j]*ell);
// 			}
// 		}
// 		var arrStr = "[";
// 		for(var i=0; i<sumArr[0].length;i++){
// 				arrStr+="{'TITLE':'"+monthArray[i]+"','VALUE1':'"+sumArr[0][i]+"','VALUE2':'"+sumArr[1][i]+"','VALUE3':'"+sumArr[2][i]+"'}"+(i==sumArr[0].length-1?'':',');
// 		}
// 		arrStr+="]";
		//테스트 데이터 
// 		testData = arrStr;
// 		testData = [{"TITLE":""+monthArray[0]+"","VALUE1":"10","VALUE2":"20","VALUE3":"30"},
// 					{"TITLE":""+monthArray[1]+"","VALUE1":"20","VALUE2":"20","VALUE3":"30"},
// 					{"TITLE":""+monthArray[2]+"","VALUE1":"30","VALUE2":"10","VALUE3":"10"}];
		
		//차트클리어
		if(cntChart!=null)
			cntChart.clearAll(); 
		if(timeChart!=null)
			timeChart.clearAll();
		//테스트
// 		if(testChart!=null)
// 			testChart.clearAll();
		//차트 적용
		drawCntChart(rowId);
	});
    myGrid.enableTreeGridLines();
	myGrid.setImageSize(1,1);
	//myGrid.enableAutoHeight(true,200);
	myGrid.setColumnHidden(1,true);
	myGrid.setColumnHidden(2,true);
	myGrid.setColumnHidden(12,true);
	myGrid.setColumnHidden(13,true);
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox") });
	isHeaderLoaded[currentPageId+".gridbox"] = "Y";
	myGrid.init();
	
    myGrid.setSizes();
	
	
    
	for(var i = 0; myGrid.getColumnsNum() > i; i++)
   {
      if(myGrid.getColType(i) == "math") myGrid.setNumberFormat("0,000",i,".",",");
   }
    goSearch();
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maDeptWoList.do";

    maDeptWoListForm.elements['strutsAction'].value = '<%=MaDeptWoListAction.WO_LIST_FIND%>';
    //검색버튼 누를 때 clear
//     myGrid.clearAll(); 
//     setLoading("gridbox");
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maDeptWoListForm), "DEPTID");
//     $.post(url,FormQueryString(maDeptWoListForm), function(_data){
//     	myGrid.parse(_data,"js");
//     	myGrid.expandAll(); //펼치기
//     	setCounter(myGrid,"gridbox");
//     });
}
/**
 * 엑셀 다운.
 */
 function goExcel()
 {
	 excelAction(myGrid);
	 
	 myGrid.setSizes();
 }

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	//validation
	if(checkRequireValue("maDeptWoListDTO.filterStartDate","<bean:message key='LABEL.date'/>")) return;
	if(checkRequireValue("maDeptWoListDTO.filterEndDate","<bean:message key='LABEL.date'/>")) return;
	if(checkTwoDate(maDeptWoListForm.elements['maDeptWoListDTO.filterStartDate'],
			maDeptWoListForm.elements['maDeptWoListDTO.filterEndDate'])) return;
	
	//검색 기간 제한 
	var days = getDayInterval(maDeptWoListForm.elements['maDeptWoListDTO.filterStartDate'].value.replace(/\-/gi, ""),
			maDeptWoListForm.elements['maDeptWoListDTO.filterEndDate'].value.replace(/\-/gi, ""));
	//if(days>30){
	//	alertMessage1("<bean:message key='MESSAGE.MSG0014'/>");
	//	return;
	//}
	//initGrid();
	//차트 clear
	if(cntChart!=null)
		cntChart.clearAll(); 
	if(timeChart!=null)
		timeChart.clearAll();
	//테스트
// 	if(testChart!=null)
// 		testChart.clearAll();
	
	document.getElementById("chartDiv").innerText = "<bean:message key='LABEL.woDate'/>"+"";
	
    findGridList("SearchTree");   
}

//테스트
// function drawTestChart(){
// 	testChart.parse(testData, "json");
// 	isChartLoading = false;
// }
/**
 * 건수차트 그리기
 **/
 function drawCntChart(id){
	//차트 title설정
	document.getElementById("chartDiv").innerText = 
		"<bean:message key='LABEL.woDate'/>"+" : "+
		maDeptWoListForm.elements['maDeptWoListDTO.filterStartDate'].value + " ~ "+
		maDeptWoListForm.elements['maDeptWoListDTO.filterEndDate'].value + " | "+
		myGrid.cells(id, myGrid.getColIndexById("DEPTDESC")).getValue();
	
	cntChart.parse(cntData,"json");
 	isChartLoading = false;
 	drawTimeChart();
}
/**
 * 시간차트 그리기
 **/
 function drawTimeChart(){
	 timeChart.parse(timeData,"json");
  	isChartLoading = false;
 	//테스트
//  	drawTestChart();
}
 function initCntChart(){
	 cntChart =  new dhtmlXChart({
			view:"pie",
			container:"cntchartbox",
			value:"#VALUE#",
			color:"#COLOR#",
			labelOffset:-5,
			shadow:0,
			legend:{
				width:75,
				align:"right",
				toggle:false,
				valign:"middle",
				template:"#TITLE#"
			},
			shadow:1,
			label:function(obj){
				var text = setNumberFormat(obj.VALUE);
				//상단에 style추가했습니다. 스타일명 : label
				return "<div class='label' style='border:1px solid "+obj.COLOR+"'>"+text+"</div>";
			}
		});
	}
function initTimeChart(){
	timeChart =  new dhtmlXChart({
		view:"pie",
		container:"timechartbox",
		value:"#VALUE#",
		color:"#COLOR#",
		labelOffset:-5,
		shadow:0,
		legend:{
			width:75,
			align:"right",
			toggle:false,
			valign:"middle",
			template:"#TITLE#"
		},
		shadow:1,
		label:function(obj){
			var text = setNumberFormat(obj.VALUE);
			//상단에 style추가했습니다. 스타일명 : label
			return "<div class='label' style='border:1px solid "+obj.COLOR+"'>"+text+"</div>";
		}
	});
}
//테스트
// function initTestChart(){
// 	testChart =  new dhtmlXChart({
// 		view:"line",
// 		container:"testchartbox",
// 		value:"#VALUE1#",
// 		tooltip:{
// 			template:"#VALUE1#"
// 		},
// 		item:{
// 			borderColor: "#FF6633",
// 			color: "#ffffff"
// 		},
// 		line:{
// 			color:"#FF6633",
// 			width:2
// 		},
// 		xAxis:{
// 			template:"#TITLE#"
// 		},
// 		offset:0,
// 		yAxis:{
// 		},
// 		origin:0,
// 		legend:{
// 			layout:"x",
// 			width: 55,
// 			align:"right",
// 			valign:"bottom",
// 			values:[
// 				{text:"<bean:message key='LABEL.cnt2'/>",color:"#FF7171"},
// 				{text:"<bean:message key='LABEL.timeMin'/>",color:"#3DB7CC"},
// 				{text:"<bean:message key='LABEL.pm'/>",color:"#ff48ff"}
// 			],
// 			margin: 5
// 		}
// 	});
// 	testChart.addSeries({
// 		value:"#VALUE2#",
// 		tooltip:{
// 			template:"#VALUE2#"
// 		},item:{
// 			borderColor: "#3DB7CC",
// 			color: "#ffffff"
// 		},
// 		line:{
// 			color:"#3DB7CC",
// 			width:2
// 		}
// 	});
// 	testChart.addSeries({
// 		value:"#VALUE3#",
// 		tooltip:{
// 			template:"#VALUE3#"
// 		},item:{
// 			borderColor: "#ff48ff",
// 			color: "#ffffff"
// 		},
// 		line:{
// 			color:"#ff48ff",
// 			width:2
// 		}
// 	});
// }
 /**
  * 설비고장검색하기
  */
  function goBreak(){
	if(typeof selectedId !="number") return;
	
	var url   = contextPath + "/maEqBmList.do";

	var popWidth = 1010;
	var popHeight = 640;

    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";

    var param = "isDecoratorName=popupPage&strutsAction=&maEqBmListDTO.filterDeptId="+ getValueById(myGrid, selectedId, "DEPTID")
								+"&maEqBmListDTO.filterDeptDesc="+ getValueById(myGrid, selectedId, "DEPTDESC")
								+"&maEqBmListDTO.filterStartDate="+ maDeptWoListForm.elements['maDeptWoListDTO.filterStartDate'].value.replace(/\-/gi, "")
								+"&maEqBmListDTO.filterEndDate="+ maDeptWoListForm.elements['maDeptWoListDTO.filterEndDate'].value.replace(/\-/gi, "");
    openWindowWithPost(url, "EQ_BM_LIST", param, pos);
 }
 /**
  * 부품교체검색하기
  */
  function goChpart(){
	if(typeof selectedId !="number") return;
	
	var url   = contextPath + "/maWoPtHistList.do";
	
	var popWidth = 1010;
	var popHeight = 640;
    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
	
	var bottomForm = document.bottomForm;
	var param = "isDecoratorName=popupPage"+
				"&maWoPtHistCommonDTO.strutsAction="+
				"&maWoPtHistCommonDTO.filterStartDate="+getMinusDay(365).replace(/\-/gi, "")+
				"&maWoPtHistCommonDTO.filterEndDate="+getToday().replace(/\-/gi, "")+
				"&maWoPtHistCommonDTO.filterDeptId="+getValueById(myGrid, selectedId, "DEPTID")+
				"&maWoPtHistCommonDTO.filterDeptDesc="+getValueById(myGrid, selectedId, "DEPTDESC");
	//post 전송
	openWindowWithPost(url, "USE_PT_LIST_POPUP", param, pos);
 }
 /**
  * 예방수리검색하기
  */
  function goRepair(){
	if(typeof selectedId !="number") return;
	var url   = contextPath + "/maPmRepList.do";

	var popWidth = 1010;
	var popHeight = 640;

    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";

    var param = "isDecoratorName=popupPage"+"&strutsAction=&maPmRepListDTO.filterDeptId="+ getValueById(myGrid, selectedId, "DEPTID")
								+"&maPmRepListDTO.filterDeptDesc="+ getValueById(myGrid, selectedId, "DEPTDESC")
								+"&maPmRepListDTO.filterStartDate="+ getMinusDay(365).replace(/\-/gi, "")
								+"&maPmRepListDTO.filterEndDate="+ getToday().replace(/\-/gi, "");
  
    openWindowWithPost(url, "PM_REP_LIST", param, pos);
 }
 /**
  * 예방점검검색
  */
  function goInspection(){
	if(typeof selectedId !="number") return;
	var url   = contextPath + "/maPmPointList.do";

	var popWidth = 1010;
	var popHeight = 640;

    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";

    var param = "isDecoratorName=popupPage"+"&strutsAction=&maPmPointListDTO.filterDeptId="+ getValueById(myGrid, selectedId, "DEPTID")
								+"&maPmPointListDTO.filterDeptDesc="+ getValueById(myGrid, selectedId, "DEPTDESC")
								+"&maPmPointListDTO.filterStartDate="+ getMinusDay(365).replace(/\-/gi, "")
								+"&maPmPointListDTO.filterEndDate="+ getToday().replace(/\-/gi, "");
  
    openWindowWithPost(url, "PM_POINT_LIST", param, pos);
 }
 /**
  * 예방기준확인
  */
  function goStandard(){
	if(typeof selectedId !="number") return;
	var url   = contextPath + "/maPmMstrList.do";
	var popWidth = 1010;
	var popHeight = 640;
    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
	
	var bottomForm = document.bottomForm;
	var param = "isDecoratorName=popupPage"+
				"&maPmMstrCommonDTO.strutsAction="+
				"&maPmMstrCommonDTO.deptId="+getValueById(myGrid, selectedId, "DEPTID")+
				"&maPmMstrCommonDTO.deptDesc="+getValueById(myGrid, selectedId, "DEPTDESC");
	//post 전송
	openWindowWithPost(url, "PM_LIST", param, pos);
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maDeptWoList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maDeptWoListDTO.filterDeptId"/>
<html:hidden property="maDeptWoListDTO.filterMainMngId"/>
<html:hidden property="maDeptWoListDTO.filterSubMngId"/>
<html:hidden property="maDeptWoListDTO.filterEqLocId"/>
<html:hidden property="maDeptWoListDTO.filterEqCtgId"/>
<html:hidden property="maDeptWoListDTO.filterEquipId"/>
<html:hidden property="maDeptWoListDTO.filterPlfTypeId"/>
<%-- <html:hidden property="maDeptWoListDTO.filterPlfTypeDesc"/> --%>
<html:hidden property="maDeptWoListDTO.filterPlantId"/>
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
				<!-- 작업일자 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.date"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="maDeptWoListDTO.filterStartDate" tabindex="10" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="maDeptWoListDTO.filterEndDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 부서 -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="maDeptWoListDTO.filterDeptDesc" tabindex="30" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 설비-->
				<div class="field">
					<label><bean:message key="LABEL.equipDesc"/></label>
					<div class="input_box">
						<html:text property="maDeptWoListDTO.filterEquipDesc" tabindex="40" />
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
						<html:text property="maDeptWoListDTO.filterEqLocDesc" tabindex="50"/>
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
						<html:text property="maDeptWoListDTO.filterEqCtgDesc" tabindex="60" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 내/외자 -->
                <div class="field">
                    <label><bean:message key="LABEL.plfType"/></label>
                    <div class="input_box">
                         <html:text property="maDeptWoListDTO.filterPlfTypeDesc" tabindex="55"/>
                         <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
				<!-- 법정설비여부 -->
				<div class="field">
					<label><bean:message key="LABEL.isLawEq"/></label>
					<div class="input_box">
						<html:text property="maDeptWoListDTO.filterIsLawEq" tabindex="80" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 관리자(정) -->
				<div class="field">
					<label><bean:message key="LABEL.mainManager"/></label>
					<div class="input_box">
						<html:text property="maDeptWoListDTO.filterMainMngName" tabindex="90" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 관리자(부) -->
				<div class="field">
					<label><bean:message key="LABEL.subManager"/></label>
					<div class="input_box">
						<html:text property="maDeptWoListDTO.filterSubMngName" tabindex="100" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
						<html:text property="maDeptWoListDTO.filterPlantDesc" tabindex="90" />
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
				<div class="b_line"></div> 
				<div class="fb_group1">
					 
				</div>
			</div>
		</div><!--sheader_box end-->
		<div class="article_box">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:410px; background-color:white;"></div>
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
		</div>
		<div class="article_box chbox">
			<div class="ch_title"><bean:message key='LABEL.cnt2'/></div>
			<div class="grid_area">
				<div id="cntchartbox" style="width:100%;height:270px;background-color:white;"></div>
			</div>
		</div>
		<div class="article_box chbox">
			<div class="ch_title"><bean:message key='LABEL.timeMin'/></div>
			<div class="grid_area">
				<div id="timechartbox" style="width:100%;height:270px;background-color:white;"></div>
			</div>
		</div> 
	</div>
</html:form> 
</body>
</html>
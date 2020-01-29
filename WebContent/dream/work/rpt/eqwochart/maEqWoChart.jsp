<%--===========================================================================
설비작업현황
author  kim21017
version $Id: maEqWoChart.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.maeqwochart.action.MaEqWoChartAction" %>
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
<!-- 설비작업현황 -->
<title><bean:message key='MENU.EQWOCHART'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//
//그리드명
var woGrid;			//전체작업현황
var pmGrid;			//예방작업현황
var ptGrid;			//부품입고
var usePtGrid;		//부품사용

var selectedWoId, selectedWoInd; //전체작업현황 클릭 id, ind
var selectedPmId, selectedPmInd; //예방작업현황 클릭 id, ind
var selectedPtId, selectedPtInd; //부품 id, ind
var selectedUseId, selectedUseInd; //자재사용이력 id, ind
var lang = loginUser.locale;

var deptAc;
var plantAc;

function loadPage() 
{
	//작업일자 세팅
	maEqWoChartForm.elements['maEqWoChartDTO.filterStartDate'].value = getMinusDay(4);
	maEqWoChartForm.elements['maEqWoChartDTO.filterEndDate'].value   = getToday();

	//부서정보 세팅
	maEqWoChartForm.elements['maEqWoChartDTO.filterDeptId'].value    = "<%=user.getDeptId()%>";
	maEqWoChartForm.elements['maEqWoChartDTO.filterDeptDesc'].value  = "<%=user.getDeptDesc()%>";
	
	//공장명
    if(loginUser.plant!='null'){
    	maEqWoChartForm.elements['maEqWoChartDTO.filterPlantId'].value  = loginUser.plant;
    	maEqWoChartForm.elements['maEqWoChartDTO.filterPlantDesc'].value  = loginUser.plantDesc;
	}
	
	
	//전체작업현황 그리드 초기화
	initWoGrid(maEqWoChartForm.elements['maEqWoChartDTO.filterStartDate'].value,
			maEqWoChartForm.elements['maEqWoChartDTO.filterEndDate'].value);
	//예방작업현황 그리드 초기화
	initPmGrid(maEqWoChartForm.elements['maEqWoChartDTO.filterStartDate'].value,
			maEqWoChartForm.elements['maEqWoChartDTO.filterEndDate'].value);
	//부품입고 그리드 초기화
	initPtGrid(maEqWoChartForm.elements['maEqWoChartDTO.filterStartDate'].value,
			maEqWoChartForm.elements['maEqWoChartDTO.filterEndDate'].value);
	//부품사용 그리드 초기화
	initUsePtGrid(maEqWoChartForm.elements['maEqWoChartDTO.filterStartDate'].value,
			maEqWoChartForm.elements['maEqWoChartDTO.filterEndDate'].value);
	
	goSearch();
	
	deptAc = new autoC({"maEqWoChartDTO.filterDeptDesc":"description"});
    deptAc.setAcDisplay("DESCRIPTION");
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maEqWoChartDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "maEqWoChartDTO.filterPlantId"
    });
    deptAc.init();
    
 	// 공장코드
	plantAc = new autoC({"maEqWoChartDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "maEqWoChartDTO.filterPlantId":"plant"
    });
    plantAc.init();
}

//전체작업현황 빼고 접기.
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
/**
 * 전체작업현황 그리드 초기화
 */
function initWoGrid(fromDate,toDate)
{
	var days = getDayInterval(fromDate.replace(/\-/gi, ""),toDate.replace(/\-/gi, ""));
    maEqWoChartForm.elements['maEqWoChartDTO.dateArrStr'].value = getDays(fromDate,toDate,days);
    woGrid = new dhtmlXGridObject('wogridbox');
    woGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	woGrid.setHeader(",,<bean:message key='LABEL.separation'/>,#cspan"+getDays2(fromDate,toDate,days,lang));
	woGrid.setColumnIds("PMTYPEDESC,PMSTATUS,PMTYPE,PMSCHEDSTATUS"+getDays(fromDate,toDate,days));
	woGrid.setInitWidths("100,100,100,100"+getWords(days,"100"));
	woGrid.setColAlign("left,left,left,left"+getWords(days,"center"));
	woGrid.setColTypes("ro,ro,ro,ro"+getWords(days,"ro"));
	woGrid.setColumnColor(getWeekendColor("PMTYPEDESC,PMSTATUS,PMTYPE,PMSCHEDSTATUS"+getDays(fromDate,toDate,days)));
	woGrid.setColumnHidden(0,true);
	woGrid.setColumnHidden(1,true);
	woGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"wogridbox"); setRowSpan(woGrid,'PMTYPE'); });
	woGrid.attachEvent("onRowSelect", function(id,ind){
		selectedWoId = id;
		selectedWoInd = ind;
	});
	woGrid.attachEvent("onRowDblClicked", function(id,ind){
		goAllWoList(id,ind);
	});
	woGrid.init();
}
function goWo(){
	goAllWoList(selectedWoId, selectedWoInd);
}
function goAllWoList(id,ind){
	if(typeof id=="undefined"||typeof ind=="undefined") return ;
	if(ind>3&&getValueById(woGrid, woGrid.getRowId(id-1), woGrid.getColumnId(1))!='completeRate'){
		var woDate = woGrid.getColumnId(ind).replace(/\-/gi, "");
		var woType = getValueById(woGrid, woGrid.getRowId(id-1), woGrid.getColumnId(0));
		//작업상태 코드 
		var status = "";
		if(getValueById(woGrid, woGrid.getRowId(id-1), woGrid.getColumnId(1))=="result")status="C";
		
		//값이 0 이상일 때
		if(getValueById(woGrid, woGrid.getRowId(id-1), woGrid.getColumnId(ind))>0){
			var url   = contextPath + "/maWoResultMstrList.do";
			var popWidth = 1010;
			var popHeight = 640;
		    // pop up이 중앙에 위치하게 한다.
		    var TopPosition  = (screen.height/2 - popHeight/2);
		    var LeftPosition = (screen.width/2 - popWidth/2);
	
		    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
		              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
		    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
		    
			var param = "isDecoratorName=popupPage"+
						"&maWoResultMstrCommonDTO.strutsAction="+
						"&maWoResultMstrCommonDTO.filterStartDate="+woDate+
						"&maWoResultMstrCommonDTO.filterEndDate="+woDate+
						"&maWoResultMstrCommonDTO.filterWoTypeId="+woType+
						"&maWoResultMstrCommonDTO.filterWoStatus="+status+
						"&maWoResultMstrCommonDTO.filterDeptId="+maEqWoChartForm.elements['maEqWoChartDTO.deptId'].value+
						"&maWoResultMstrCommonDTO.filterDeptDesc="+maEqWoChartForm.elements['maEqWoChartDTO.deptDesc'].value;
			//post 전송
			openWindowWithPost(url, "CHART_WO_LIST_POPUP", param, pos);
		}else{
			alertMessage1('<bean:message key="MESSAGE.MSG0024"/>');
		}
	}
}

/**
 * 예방작업현황 그리드 초기화
 */
function initPmGrid(fromDate,toDate)
{
	var days = getDayInterval(fromDate.replace(/\-/gi, ""),toDate.replace(/\-/gi, ""));
    maEqWoChartForm.elements['maEqWoChartDTO.dateArrStr'].value = getDays(fromDate,toDate,days);
    pmGrid = new dhtmlXGridObject('pmgridbox');
    pmGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	pmGrid.setHeader(",,<bean:message key='LABEL.separation'/>,#cspan"+getDays2(fromDate,toDate,days,lang));
	pmGrid.setColumnIds("PMTYPEDESC,PMSTATUS,PMTYPE,PMSCHEDSTATUS"+getDays(fromDate,toDate,days));
	pmGrid.setInitWidths("100,100,100,100"+getWords(days,"100"));
	pmGrid.setColAlign("left,left,left,left"+getWords(days,"center"));
	pmGrid.setColTypes("ro,ro,ro,ro"+getWords(days,"ro"));
	pmGrid.setColumnColor(getWeekendColor("PMTYPEDESC,PMSTATUS,PMTYPE,PMSCHEDSTATUS"+getDays(fromDate,toDate,days)));
	pmGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"pmgridbox");setRowSpan(pmGrid,'PMTYPE');});
	pmGrid.setColumnHidden(0,true);
	pmGrid.setColumnHidden(1,true);
	pmGrid.attachEvent("onRowSelect", function(id,ind){
		selectedPmId = id;
		selectedPmInd = ind;
	});
	pmGrid.attachEvent("onRowDblClicked", function(id,ind){
		goPmWoList(id,ind);
	});
	pmGrid.init();
}

function goPmWo(){
	goPmWoList(selectedPmId, selectedPmInd);
}

function goPmWoList(id, ind){
	if(typeof id=="undefined"||typeof ind=="undefined") return ;
	
	if(ind>3&&getValueById(pmGrid, pmGrid.getRowId(id-1), pmGrid.getColumnId(1))!='completeRate'){
		var woDate = pmGrid.getColumnId(ind).replace(/\-/gi, "");
		var pmType = getValueById(pmGrid, pmGrid.getRowId(id-1), pmGrid.getColumnId(0));
		var woType = "PMW";
		if(pmType == "INS") woType="PMI";
		//작업상태 코드 
		var status = "";
		if(getValueById(pmGrid, pmGrid.getRowId(id-1), pmGrid.getColumnId(1))=="result")status="C";
		
		//값이 0 이상일 때
		if(getValueById(pmGrid, pmGrid.getRowId(id-1), pmGrid.getColumnId(ind))>0){
			var url   = contextPath + "/maWoResultMstrList.do";
			var popWidth = 1010;
			var popHeight = 640;
		    // pop up이 중앙에 위치하게 한다.
		    var TopPosition  = (screen.height/2 - popHeight/2);
		    var LeftPosition = (screen.width/2 - popWidth/2);

		    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
		              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
		    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
			
			var param = "isDecoratorName=popupPage"+
						"&maWoResultMstrCommonDTO.strutsAction="+
						"&maWoResultMstrCommonDTO.filterStartDate="+woDate+
						"&maWoResultMstrCommonDTO.filterEndDate="+woDate+
						"&maWoResultMstrCommonDTO.filterPmTypeId="+pmType+
						"&maWoResultMstrCommonDTO.filterWoTypeId="+woType+
						"&maWoResultMstrCommonDTO.filterWoStatus="+status+
						"&maWoResultMstrCommonDTO.filterDeptId="+maEqWoChartForm.elements['maEqWoChartDTO.deptId'].value+
						"&maWoResultMstrCommonDTO.filterDeptDesc="+maEqWoChartForm.elements['maEqWoChartDTO.deptDesc'].value;
			//post 전송
			openWindowWithPost(url, "CHART_WO_LIST_POPUP", param, pos);
		}else{
			alertMessage1('<bean:message key="MESSAGE.MSG0024"/>');
		}
		
	}
}

/**
 * 부품입고 그리드 초기화
 */
function initPtGrid(fromDate,toDate)
{
	var days = getDayInterval(fromDate.replace(/\-/gi, ""),toDate.replace(/\-/gi, ""));
    maEqWoChartForm.elements['maEqWoChartDTO.dateArrStr'].value = getDays(fromDate,toDate,days);
    ptGrid = new dhtmlXGridObject('ptgridbox');
    ptGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	ptGrid.setHeader(",<bean:message key='LABEL.separation'/>,#cspan,#cspan"+getDays2(fromDate,toDate,days,lang));
	ptGrid.setColumnIds("TYPE,PTREC,PTTYPE,PTCNT"+getDays(fromDate,toDate,days));
	ptGrid.setInitWidths("50,70,65,65"+getWords(days,"100"));
	ptGrid.setColAlign("left,left,left,left"+getWords(days,"center"));
	ptGrid.setColTypes("ro,ro,ro,ro"+getWords(days,"ron"));
	ptGrid.setColumnHidden(0,true);
	for(var i=4; i<=days+4;i++){
		ptGrid.setNumberFormat("0,000",i); 
	}
	ptGrid.setColumnColor(getWeekendColor("TYPE,PTREC,PTTYPE,PTCNT"+getDays(fromDate,toDate,days)));
	ptGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"ptgridbox"); setRowSpan(ptGrid,'PTREC');setRowSpan(ptGrid,'PTTYPE');});
	ptGrid.attachEvent("onRowSelect", function(id,ind){
		selectedPtId = id;
		selectedPtInd = ind;
	});
	ptGrid.attachEvent("onRowDblClicked", function(id,ind){
		goPtList(id, ind);
	});
	ptGrid.init();
}

function goPt(){
	goPtList(selectedPtId, selectedPtInd);
}

function goPtList(id, ind){
	if(typeof id=="undefined"||typeof ind=="undefined") return ;
	
	if(ind>3&&getValueById(ptGrid, ptGrid.getRowId(id-1), ptGrid.getColumnId(0))!='total'){
		var ptDate = ptGrid.getColumnId(ind).replace(/\-/gi, "");
		//구매, 수리 구분 
		var type = getValueById(ptGrid, ptGrid.getRowId(id-1), ptGrid.getColumnId(0));
		
		//값이 0 이상일 때
		if(getValueById(ptGrid, ptGrid.getRowId(id-1), ptGrid.getColumnId(ind))>0){
			if(type=='buy'){
				var url   = contextPath + "/maPtRecList.do";
				
				var popWidth = 1010;
				var popHeight = 640;
			    // pop up이 중앙에 위치하게 한다.
			    var TopPosition  = (screen.height/2 - popHeight/2);
			    var LeftPosition = (screen.width/2 - popWidth/2);

			    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
			              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
			    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
				
				var param = "isDecoratorName=popupPage"+
							"&maPtRecCommonDTO.strutsAction="+
							"&maPtRecCommonDTO.filterRecStartDate="+ptDate+
							"&maPtRecCommonDTO.filterRecEndDate="+ptDate+
							"&maPtRecCommonDTO.prRecStatus="+"C"+
							"&maPtRecCommonDTO.filterDeptId="+maEqWoChartForm.elements['maEqWoChartDTO.deptId'].value+
							"&maPtRecCommonDTO.filterDeptDesc="+maEqWoChartForm.elements['maEqWoChartDTO.deptDesc'].value;
				//post 전송
				openWindowWithPost(url, "CHART_PT_LIST_POPUP", param, pos);
				
			}else if(type=='repair'){
				var url   = contextPath + "/maPtRepList.do";
				
				var popWidth = 1010;
				var popHeight = 640;
			    // pop up이 중앙에 위치하게 한다.
			    var TopPosition  = (screen.height/2 - popHeight/2);
			    var LeftPosition = (screen.width/2 - popWidth/2);

			    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
			              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
			    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";

				var param = "isDecoratorName=popupPage"+
							"&maPtRepCommonDTO.strutsAction="+
							"&maPtRepCommonDTO.filterStartDate="+ptDate+
							"&maPtRepCommonDTO.filterEndDate="+ptDate+
							"&maPtRepCommonDTO.ptRepStatus="+"V"+
							"&maPtRepCommonDTO.filterDeptId="+maEqWoChartForm.elements['maEqWoChartDTO.deptId'].value+
							"&maPtRepCommonDTO.filterDeptDesc="+maEqWoChartForm.elements['maEqWoChartDTO.deptDesc'].value;
				//post 전송
				openWindowWithPost(url, "CHART_PT_LIST_POPUP", param, pos);
//					bottomForm.elements['isDecoratorName'].value = "";
				
			}
		}else{
			if(type=='buy')
				alertMessage1('<bean:message key="MESSAGE.MSG0025"/>');
			else if(type=='repair')
				alertMessage1('<bean:message key="MESSAGE.MSG0026"/>');
		}
		
	}
}

/**
 * 부품사용 그리드 초기화
 */
function initUsePtGrid(fromDate,toDate)
{
	var days = getDayInterval(fromDate.replace(/\-/gi, ""),toDate.replace(/\-/gi, ""));
    maEqWoChartForm.elements['maEqWoChartDTO.dateArrStr'].value = getDays(fromDate,toDate,days);
    usePtGrid = new dhtmlXGridObject('useptgridbox');
    usePtGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	usePtGrid.setHeader("<bean:message key='LABEL.separation'/>,#cspan"+getDays2(fromDate,toDate,days,lang));
	usePtGrid.setColumnIds("USETYPE,USECNT"+getDays(fromDate,toDate,days));
	usePtGrid.setInitWidths("100,100"+getWords(days,"100"));
	usePtGrid.setColAlign("left,left"+getWords(days,"center"));
	usePtGrid.setColTypes("ro,ro"+getWords(days,"ro"));
	usePtGrid.setColumnColor(getWeekendColor("USETYPE,USECNT"+getDays(fromDate,toDate,days)));
	usePtGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"useptgridbox");});
	usePtGrid.attachEvent("onRowSelect",function(id, ind){
		selectedUseId = id;
		selectedUseInd = ind;
	});
	usePtGrid.attachEvent("onRowDblClicked", function(id,ind){
		goUseList(id, ind);
	});
	
	usePtGrid.init();
}

function goUse(){
	goUseList(selectedUseId,selectedUseInd);
}

function goUseList(id, ind){
	if(typeof id=="undefined"||typeof ind=="undefined") return ;
	
	if(ind>1){
		var useDate = usePtGrid.getColumnId(ind).replace(/\-/gi, "");
		//값이 0 이상일 때
		if(getValueById(usePtGrid, usePtGrid.getRowId(id-1), usePtGrid.getColumnId(ind))>0){

			var url   = contextPath + "/maWoPtHistList.do";
			
			var popWidth = 1010;
			var popHeight = 640;
		    // pop up이 중앙에 위치하게 한다.
		    var TopPosition  = (screen.height/2 - popHeight/2);
		    var LeftPosition = (screen.width/2 - popWidth/2);

		    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
		              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
		    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
			
			var param = "isDecoratorName=popupPage"+
						"&maWoPtHistCommonDTO.strutsAction="+
						"&maWoPtHistCommonDTO.filterStartDate="+useDate+
						"&maWoPtHistCommonDTO.filterEndDate="+useDate+
						"&maWoPtHistCommonDTO.filterDeptId="+maEqWoChartForm.elements['maEqWoChartDTO.deptId'].value+
						"&maWoPtHistCommonDTO.filterDeptDesc="+maEqWoChartForm.elements['maEqWoChartDTO.deptDesc'].value;
			//post 전송
			openWindowWithPost(url, "USE_PT_LIST_POPUP", param, pos);
		}else{
			alertMessage1('<bean:message key="MESSAGE.MSG0024"/>');
		}
		
	}
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 전체작업현황 그리드에 셋팅한다.
 */
function findWoGridList()
{
    var url = contextPath + "/maEqWoChart.do";
    maEqWoChartForm.elements['strutsAction'].value = '<%=MaEqWoChartAction.WO_LIST_FIND%>';

    woGrid.clearAll();
    setLoading("wogridbox");
    //전체작업현황 그리드 초기화
    initWoGrid(maEqWoChartForm.elements['maEqWoChartDTO.filterStartDate'].value,maEqWoChartForm.elements['maEqWoChartDTO.filterEndDate'].value);
    
    $.post(url,FormQueryString(maEqWoChartForm), function(_data){
    	//조회 후 findPmGridList 메서드 실행
    	woGrid.parse(_data,findPmGridList,"js");
    });
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 예방작업현황 그리드에 셋팅한다.
 */
function findPmGridList()
{
    var url = contextPath + "/maEqWoChart.do";
    maEqWoChartForm.elements['strutsAction'].value = '<%=MaEqWoChartAction.PM_LIST_FIND%>';

    pmGrid.clearAll();
    setLoading("pmgridbox");
    //예방작업현황 그리드 초기화
    initPmGrid(maEqWoChartForm.elements['maEqWoChartDTO.filterStartDate'].value,maEqWoChartForm.elements['maEqWoChartDTO.filterEndDate'].value);
    
    $.post(url,FormQueryString(maEqWoChartForm), function(_data){
    	//조회 후 findPtGridList 메서드 실행
    	pmGrid.parse(_data,findPtGridList,"js");
    });
}
/**
 * 현재 셋팅된 조건으로 값을 조회하여 부품입고 그리드에 셋팅한다.
 */
function findPtGridList()
{
    var url = contextPath + "/maEqWoChart.do";
    maEqWoChartForm.elements['strutsAction'].value = '<%=MaEqWoChartAction.PT_LIST_FIND%>';

    ptGrid.clearAll();
    setLoading("ptgridbox");
    //부품입고 그리드 초기화
    initPtGrid(maEqWoChartForm.elements['maEqWoChartDTO.filterStartDate'].value,maEqWoChartForm.elements['maEqWoChartDTO.filterEndDate'].value);
    
    $.post(url,FormQueryString(maEqWoChartForm), function(_data){
    	//조회 후 findUsePtGridList 메서드 실행
    	ptGrid.parse(_data,findUsePtGridList,"js");
    });
}
/**
 * 현재 셋팅된 조건으로 값을 조회하여 부품사용 그리드에 셋팅한다.
 */
function findUsePtGridList()
{
    var url = contextPath + "/maEqWoChart.do";
    maEqWoChartForm.elements['strutsAction'].value = '<%=MaEqWoChartAction.USEPT_LIST_FIND%>';

    usePtGrid.clearAll();
    setLoading("useptgridbox");
    //부품사용 그리드 초기화
    initUsePtGrid(maEqWoChartForm.elements['maEqWoChartDTO.filterStartDate'].value,maEqWoChartForm.elements['maEqWoChartDTO.filterEndDate'].value);
    
    $.post(url,FormQueryString(maEqWoChartForm), function(_data){
    	usePtGrid.parse(_data,"js");
    	//아래3개(예방 입고 부품) 폴더 접기
    	foldList();
    });
}


/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	//validation
	if(checkRequireValue("maEqWoChartDTO.filterStartDate","<bean:message key='LABEL.overhaulDate'/>")) return;
	if(checkRequireValue("maEqWoChartDTO.filterEndDate","<bean:message key='LABEL.overhaulDate'/>")) return;
	if(checkTwoDate(maEqWoChartForm.elements['maEqWoChartDTO.filterStartDate'],
			maEqWoChartForm.elements['maEqWoChartDTO.filterEndDate'])) return;
	if(checkRequireValue("maEqWoChartDTO.filterDeptDesc","<bean:message key='LABEL.manageDept'/>")) return;
	
	//검색 기간 제한 
	var days = getDayInterval(maEqWoChartForm.elements['maEqWoChartDTO.filterStartDate'].value.replace(/\-/gi, ""),
			maEqWoChartForm.elements['maEqWoChartDTO.filterEndDate'].value.replace(/\-/gi, ""));
	if(days>30){
		alertMessage1("<bean:message key='MESSAGE.MSG0011'/>");
		return;
	}

	maEqWoChartForm.elements['maEqWoChartDTO.deptId'].value = maEqWoChartForm.elements['maEqWoChartDTO.filterDeptId'].value;
	maEqWoChartForm.elements['maEqWoChartDTO.deptDesc'].value = maEqWoChartForm.elements['maEqWoChartDTO.filterDeptDesc'].value;
	
    findWoGridList();
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

/**
 * 전체작업리스트 엑셀 다운
 */
 function goWoExcel()
 {
 	excelAction(woGrid);
 } 
/**
 * 예방작업 엑셀 다운
 */
 function goPmExcel()
 {
 	excelAction(pmGrid);
 } 
/**
 * 부품입고 엑셀 다운
 */
 function goPtExcel()
 {
 	excelAction(ptGrid);
 } 
/**
 * 부품사용 엑셀 다운
 */
 function goUsePtExcel()
 {
 	excelAction(usePtGrid);
 } 

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maEqWoChart.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maEqWoChartDTO.filterDeptId"/>
<html:hidden property="maEqWoChartDTO.deptId"/>
<html:hidden property="maEqWoChartDTO.deptDesc"/>
<html:hidden property="maEqWoChartDTO.dateArrStr"/>
<html:hidden property="maEqWoChartDTO.filterPlantId"/>
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
				<!-- 담당부서 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.manageDept"/></label>
					<div class="input_box">
						<html:text property="maEqWoChartDTO.filterDeptDesc" tabindex="10" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 작업일자 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.wkorDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="maEqWoChartDTO.filterStartDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="maEqWoChartDTO.filterEndDate" tabindex="30" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
							<html:text property="maEqWoChartDTO.filterPlantDesc"
								tabindex="90" />
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
				<div class="stitle_tx"><bean:message key="LABEL.allWorkChart"/></div>
			</div>
			<div class="function_box list not_fold">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>

				<div class="fb_group2">
					<a href="javascript:goWo();" class="b_wo"><span><bean:message key="BUTTON.WORESULTVIEW"/></span></a>
					<a href="javascript:goWoExcel();" class="b_excel"><span><bean:message key="BUTTON.EXCEL"/></span></a>
				</div>
			</div>
		</div><!--sheader_box end-->
		<div class="article_box">
			<div class="grid_area">
				<div id="wogridbox" style="width:100%; height:483px; background-color:white;"></div>
			</div>
		</div>
	</div> <!--  End of section_wrap -->
	<div class="section_wrap">
		<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.pmWorkChart"/></div>
			</div>
			<div class="function_box list">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>

				<div class="fb_group2">
					<a href="javascript:goPmWo();" class="b_wo"><span><bean:message key="BUTTON.WORESULTVIEW"/></span></a>
					<a href="javascript:goPmExcel();" class="b_excel"><span><bean:message key="BUTTON.EXCEL"/></span></a>
					</div>
			</div>
		</div><!--sheader_box end-->
		<div class="article_box">
			<div class="grid_area">
				<div id="pmgridbox" style="width:100%; height:483px; background-color:white;"></div>
			</div>
		</div>
	</div> <!--  End of section_wrap -->
	<div class="section_wrap">
		<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.partRec"/></div>
			</div>
			<div class="function_box list">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>

				<div class="fb_group2">
					<a href="javascript:goPt();" class="b_standard"><span><bean:message key="BUTTON.REQREPVIEW"/></span></a>
					<a href="javascript:goPtExcel();" class="b_excel"><span><bean:message key="BUTTON.EXCEL"/></span></a>
				</div>
			</div>
		</div><!--sheader_box end-->
		<div class="article_box">
			<div class="grid_area">
				<div id="ptgridbox" style="width:100%; height:213px; background-color:white;"></div>
			</div>
		</div>
	</div> <!--  End of section_wrap -->
	<div class="section_wrap">
		<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.usePt"/></div>
			</div>
			<div class="function_box list">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>

				<div class="fb_group2">
					<a href="javascript:goUse();" class="b_standard"><span><bean:message key="BUTTON.STANDARD"/></span></a>
					<a href="javascript:goUsePtExcel();" class="b_excel"><span><bean:message key="BUTTON.EXCEL"/></span></a>
				</div>
			</div>
		</div><!--sheader_box end-->
		<div class="article_box">
			<div class="grid_area">
				<div id="useptgridbox" style="width:100%; height:113px; background-color:white;"></div>
			</div>
		</div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>
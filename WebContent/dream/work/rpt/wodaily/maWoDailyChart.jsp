<%--===========================================================================
일별작업실행율
author  kim21017
version $Id: maWoDailyChart.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.mawodaily.action.MaWoDailyChartAction" %>
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
<!-- 일별작업실행율 -->
<title><bean:message key='MENU.WODAILYRATE'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//
//그리드명
var woGrid;			//전체작업현황
var selectedInd;
var lang = loginUser.locale;

var plantAc;

function loadPage() 
{
    setInitVal('maWoDailyChartDTO.filterStartDate', getMinusDay(4));
    setInitVal('maWoDailyChartDTO.filterEndDate', getToday());

    setInitVal('maWoDailyChartDTO.filterDeptId', loginUser.filterDeptId);
    setInitVal('maWoDailyChartDTO.filterDeptDesc', loginUser.filterDeptDesc);
	
	//그리드 초기화
	initWoGrid(maWoDailyChartForm.elements['maWoDailyChartDTO.filterStartDate'].value,
			maWoDailyChartForm.elements['maWoDailyChartDTO.filterEndDate'].value);
	goSearch();
	
 	// 공장코드
	plantAc = new autoC({"maWoDailyChartDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "maWoDailyChartDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
	// 부서코드
    deptAc = new autoC({"maWoDailyChartDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setKeyName("maWoDailyChartDTO.filterDeptId");
    deptAc.setAcResultMap({
        "maWoDailyChartDTO.filterDeptId":"dept_id"
    });
    deptAc.init();
    
}

/**
 * 전체작업현황 그리드 초기화
 */
function initWoGrid(fromDate,toDate)
{
	var days = getDayInterval(fromDate.replace(/\-/gi, ""),toDate.replace(/\-/gi, ""));
	var statusArr = ["P","C","R"];
    maWoDailyChartForm.elements['maWoDailyChartDTO.dateArrStr'].value = getDays(fromDate,toDate,days);
    woGrid = new dhtmlXGridObject('wogridbox');
    woGrid.enableTreeGridLines();
    woGrid.setImageSize(1,1);
    woGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	woGrid.setHeader(",,,,,<bean:message key='LABEL.dept'/>"+get3Days2(fromDate,toDate,days,true));
	woGrid.attachHeader(",,,,,#rspan"+get3Words(days,"<bean:message key='LABEL.planCnt'/>"
																			  ,"<bean:message key='LABEL.completeCnt'/>"
																			  ,"<bean:message key='LABEL.runCnt'/>"));
	woGrid.setColumnIds("ID,LVL,MINLVL,DEPTID,PDEPTID,DEPTDESC"+get3Days(fromDate,toDate,days,statusArr));
	woGrid.setInitWidths("100,100,100,100,100,200"+getWords(days,"70")+getWords(days,"70")+getWords(days,"70"));
	woGrid.setColAlign("left,left,left,left,left,left"+getWords(days,"center")+getWords(days,"center")+getWords(days,"center"));
	woGrid.setColTypes("ro,ro,ro,ro,ro,tree"+getWords(days,"ro")+getWords(days,"ro")+getWords(days,"ro"));
	woGrid.setColumnColor(getWeekendColor("ID,LVL,MINLVL,DEPTID,PDEPTID,DEPTDESC"+get3Days1(fromDate,toDate,days)));
	woGrid.setColumnHidden(0,true);
	woGrid.setColumnHidden(1,true);
	woGrid.setColumnHidden(2,true);
	woGrid.setColumnHidden(3,true);
	woGrid.setColumnHidden(4,true);
	woGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"wogridbox"); });
	woGrid.attachEvent("onRowSelect", function(id,ind){
		selectedId = id;
		selectedInd = ind;
	});
	woGrid.attachEvent("onRowDblClicked", function(id,ind){
		goWoList(id,ind);
	});
	woGrid.init();
}

function goWo(){
	goWoList(selectedId, selectedInd);
}

function goWoList(id, ind){
	if(typeof id=="undefined"||typeof ind=="undefined") return ;
	//수치면서 실행률이 아닌 값을 더블클릭 했을 때.
	if(ind>5&&woGrid.getColumnId(ind).substring(0,1)!='R'){
		var woDate = woGrid.getColumnId(ind).replace(/\-/gi, "");
		woDate = woDate.substring(1,woDate.length);
		var status = woGrid.getColumnId(ind).replace(/\-/gi, "");
		status = status.substring(0,1);
		if(status=="P") status = "";
		//값이 0 이상일 때
		if(getValueById(woGrid,id,ind)>0){
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
						"&maWoResultMstrCommonDTO.filterWoStatus="+status+
						"&maWoResultMstrCommonDTO.filterDeptId="+getValueById(woGrid,id,3)+
						"&maWoResultMstrCommonDTO.filterDeptDesc="+getValueById(woGrid,id,5);
			
			//post 전송
			openWindowWithPost(url, "CHART_WO_LIST_POPUP", param, pos);
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
    var url = contextPath + "/maWoDailyChart.do";
    maWoDailyChartForm.elements['strutsAction'].value = '<%=MaWoDailyChartAction.WO_LIST_FIND%>';
    woGrid.clearAll();
    setLoading("wogridbox");
    //전체작업현황 그리드 초기화
    initWoGrid(maWoDailyChartForm.elements['maWoDailyChartDTO.filterStartDate'].value,maWoDailyChartForm.elements['maWoDailyChartDTO.filterEndDate'].value);
    
    $.post(url,FormQueryString(maWoDailyChartForm), function(_data){
    	woGrid.parse(_data,"js");
    	woGrid.expandAll();
    	setCounter(woGrid,"wogridbox");
    });
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	//validation
	if(checkRequireValue("maWoDailyChartDTO.filterStartDate","<bean:message key='LABEL.workDate'/>")) return;
	if(checkRequireValue("maWoDailyChartDTO.filterEndDate","<bean:message key='LABEL.workDate'/>")) return;
	if(checkTwoDate(maWoDailyChartForm.elements['maWoDailyChartDTO.filterStartDate'],
			maWoDailyChartForm.elements['maWoDailyChartDTO.filterEndDate'])) return;
	
	//검색 기간 제한 
	var days = getDayInterval(maWoDailyChartForm.elements['maWoDailyChartDTO.filterStartDate'].value.replace(/\-/gi, ""),
			maWoDailyChartForm.elements['maWoDailyChartDTO.filterEndDate'].value.replace(/\-/gi, ""));
	if(days>31){
		alertMessage1("<bean:message key='MESSAGE.MSG0221'/>");
		return;
	}
	
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
 * 엑셀 다운
 */
 function goExcel()
 {
 	excelAction(woGrid);
 } 

 /**
 *fromDate, toDate 사이 날짜를 3개씩 반환
 * 이페이지에서 만 사용...
 */
 function get3Days1(fromDate,toDate,days,statusArr){
		var str = "";
		var arr1 = toDate.split('-');

		toDate = arr1[0]+"-"+(arr1[1].substring(0,1)=="0"?arr1[1].substring(1,2):arr1[1])+"-"+(arr1[2].substring(0,1)=="0"?arr1[2].substring(1,2):arr1[2]);
		arr1 = toDate.split('-');

		var dat1 = new Date(arr1[0], arr1[1]-1, arr1[2]);
		for(var i=days; i>=0; i--){
			str +=","+getMinusDay2(dat1,i);
			str +=","+getMinusDay2(dat1,i);
			str +=","+getMinusDay2(dat1,i);
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
		var arr1 = toDate.split('-');

		toDate = arr1[0]+"-"+(arr1[1].substring(0,1)=="0"?arr1[1].substring(1,2):arr1[1])+"-"+(arr1[2].substring(0,1)=="0"?arr1[2].substring(1,2):arr1[2]);
		arr1 = toDate.split('-');

		var dat1 = new Date(arr1[0], arr1[1]-1, arr1[2]);
		for(var i=days; i>=0; i--){
			str +=","+statusArr[0]+getMinusDay2(dat1,i);
			str +=","+statusArr[1]+getMinusDay2(dat1,i);
			str +=","+statusArr[2]+getMinusDay2(dat1,i);
		}
		return str;
	}
 /**
  * fromDate와 toDate사이의 일자를 반환(format:,2/16(월),2/17(화))
  *  3개씩 반환
  * 이페이지에서만 사용..
  */
 function get3Days2(fromDate,toDate,days,isColspan){
 	var str = "";
 	var arr1 = toDate.split('-');

 	toDate = arr1[0]+"-"+(arr1[1].substring(0,1)=="0"?arr1[1].substring(1,2):arr1[1])+"-"+(arr1[2].substring(0,1)=="0"?arr1[2].substring(1,2):arr1[2]);
 	arr1 = toDate.split('-');

 	var dat1 = new Date(arr1[0], arr1[1]-1, arr1[2]);
 	for(var i=days; i>=0; i--){
 		var dateArr = getMinusDay2(dat1,i).split("-");
 		var dateStr = getMinusDay2(dat1,i).replace(/\-/gi, "");
 		var month   = dateArr[1].substring(0,1)=="0"?dateArr[1].substring(1,2):dateArr[1];
 		var day     = dateArr[2].substring(0,1)=="0"?dateArr[2].substring(1,2):dateArr[2];
 		
 		str +=","+month+"/"+day+"("+setComDay('"'+dateStr+'"',""+lang+"")+")"; 
 		if(isColspan){
 			str +=",#cspan"; 
 	 		str +=",#cspan"; 
 		}else{
 			str +=","+month+"/"+day+"("+setComDay('"'+dateStr+'"',""+lang+"")+")"; 
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
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maWoDailyChart.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maWoDailyChartDTO.dateArrStr"/>
<html:hidden property="maWoDailyChartDTO.filterDeptId"/>
<html:hidden property="maWoDailyChartDTO.filterPlantId"/>
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
				<!-- 수리일자 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.workDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="maWoDailyChartDTO.filterStartDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="maWoDailyChartDTO.filterEndDate" tabindex="30" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 부서명  -->
                <div class="field">
                    <label><bean:message key="LABEL.deptDesc"/></label>
                    <div class="input_box">
						<html:text property="maWoDailyChartDTO.filterDeptDesc" tabindex="35" />
						<p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>				
				<!-- 공장명  
				-->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
							<html:text property="maWoDailyChartDTO.filterPlantDesc"
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
				<div id="wogridbox" style="width:100%; height:483px; background-color:white;"></div>
			</div>
		</div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>
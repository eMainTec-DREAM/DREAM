<%--===========================================================================
월별점검실행율
author  sy.yang
version $Id: workRptPmMonthRateList.jsp,v 1.1 2015/12/03 01:45:27 sy.yang Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.pm.month.rate.action.WorkRptPmMonthRateListAction" %>
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
<!-- 월별점검실행율 -->
<title><bean:message key='MENU.PMMONTHRATE'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//
//그리드명
var woGrid;			//전체작업현황
var selectedInd;
var lang = loginUser.locale;

var deptAc;

function loadPage() 
{
	// 검색 월 세팅
	workRptPmMonthRateListForm.elements['workRptPmMonthRateListDTO.filterStartDate'].value = getMinusMonth(-2);
	workRptPmMonthRateListForm.elements['workRptPmMonthRateListDTO.filterEndDate'].value   = getMinusMonth(0);

	//그리드 초기화
	initWoGrid(workRptPmMonthRateListForm.elements['workRptPmMonthRateListDTO.filterStartDate'].value, workRptPmMonthRateListForm.elements['workRptPmMonthRateListDTO.filterEndDate'].value);
	
	goSearch();
	
	// 부서
	deptAc = new autoC({"workRptPmMonthRateListDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	,"is_monitoring":"Y"
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "workRptPmMonthRateListDTO.filterDeptId":"dept_id"
    });
    deptAc.setKeyName("workRptPmMonthRateListDTO.filterDeptId");
    deptAc.init();
	
}

/**
 * 전체작업현황 그리드 초기화
 */
function initWoGrid(fromDate,toDate)
{
	var months = getMonthInterval(fromDate.replace(/\-/gi, ""),toDate.replace(/\-/gi, ""));
	var statusArr = ["P","C","R"];
	workRptPmMonthRateListForm.elements['workRptPmMonthRateListDTO.dateArrStr'].value = getMonths1(fromDate,toDate,months);
    woGrid = new dhtmlXGridObject('wogridbox');
    woGrid.enableTreeGridLines();
    woGrid.setImageSize(1,1);
    woGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	woGrid.setHeader(",,,,,<bean:message key='LABEL.dept'/>"+get3Months2(fromDate,toDate,months,true));
	woGrid.attachHeader(",,,,,#rspan"+get3Words(months,"<bean:message key='LABEL.planCnt'/>"
																			  ,"<bean:message key='LABEL.completeCnt'/>"
																			  ,"<bean:message key='LABEL.runCnt'/>"));
	woGrid.setColumnIds("ID,LVL,MINLVL,DEPTID,PDEPTID,DEPTDESC"+get3Months(fromDate,toDate,months,statusArr));
	woGrid.setInitWidths("100,100,100,100,100,200"+getWords(months,"70")+getWords(months,"70")+getWords(months,"70"));
	woGrid.setColAlign("left,left,left,left,left,left"+getWords(months,"center")+getWords(months,"center")+getWords(months,"center"));
	woGrid.setColTypes("ro,ro,ro,ro,ro,tree"+getWords(months,"ro")+getWords(months,"ro")+getWords(months,"ro"));
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
						"&maWoResultMstrCommonDTO.filterStartDate="+woDate+"01"+
						"&maWoResultMstrCommonDTO.filterEndDate="+woDate+"31"+
						"&maWoResultMstrCommonDTO.filterWoStatus="+status+
						"&maWoResultMstrCommonDTO.filterWoTypeId="+"PMI"+
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
    var url = contextPath + "/workRptPmMonthRateList.do";
    workRptPmMonthRateListForm.elements['strutsAction'].value = '<%=WorkRptPmMonthRateListAction.WO_LIST_FIND%>';
    woGrid.clearAll();
    setLoading("wogridbox");
    //전체작업현황 그리드 초기화
    initWoGrid(workRptPmMonthRateListForm.elements['workRptPmMonthRateListDTO.filterStartDate'].value,workRptPmMonthRateListForm.elements['workRptPmMonthRateListDTO.filterEndDate'].value);
    
    setModal(); 
    
    $.post(url,FormQueryString(workRptPmMonthRateListForm), function(_data){
    	woGrid.parse(_data,"js");
    	woGrid.expandAll();
    	setCounter(woGrid,"wogridbox");
    	closeModal();
    });
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	//validation
	if(checkRequireValue("workRptPmMonthRateListDTO.filterStartDate","<bean:message key='LABEL.workDate'/>")) return;
	if(checkRequireValue("workRptPmMonthRateListDTO.filterEndDate","<bean:message key='LABEL.workDate'/>")) return;
	if(checkTwoDate(workRptPmMonthRateListForm.elements['workRptPmMonthRateListDTO.filterStartDate']+"-01",
			workRptPmMonthRateListForm.elements['workRptPmMonthRateListDTO.filterEndDate']+"-01")) return;
	
	//검색 기간 제한 (최대 2년)
	var months = getMonthInterval(workRptPmMonthRateListForm.elements['workRptPmMonthRateListDTO.filterStartDate'].value.replace(/\-/gi, ""),
			workRptPmMonthRateListForm.elements['workRptPmMonthRateListDTO.filterEndDate'].value.replace(/\-/gi, ""));
	if(months>24){
		alertMessage1("<bean:message key='MESSAGE.MSG0229'/>");
		return;
	}
    findWoGridList();
}

/**
 * 엑셀 다운
 */
 function goExcel()
 {
 	excelAction(woGrid);
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
			
			str +=","+year+"-"+month; 
		}
		return str;
 }
 /**
 *fromDate, toDate 사이 날짜를 3개씩 반환
 * statusArr를 순서대로 날짜 앞에 붙혀준다...
 * 이페이지에서 만 사용...
 */
 function get3Months(fromDate,toDate,months,statusArr){
		var str = "";
		var arr1 = toDate.split('-');

		toDate = arr1[0]+"-"+(arr1[1].substring(0,1)=="0"?arr1[1].substring(1,2):arr1[1])+"-01";
		arr1 = toDate.split('-');

		for(var i=months; i>=0; i--){
			var dat1 = new Date(arr1[0], arr1[1]-1, arr1[2]);
			var date = getMinusMonth2(dat1,-(i));
			
			str +=","+statusArr[0]+date.substring(0,7);
			str +=","+statusArr[1]+date.substring(0,7);
			str +=","+statusArr[2]+date.substring(0,7);
		}
		return str;
	}
 /**
  * fromDate와 toDate사이의 월을 반환(format:,2018-01,2018-02)
  *  3개씩 반환
  * 이페이지에서만 사용..
  */
 function get3Months2(fromDate,toDate,months,isColspan){
 	var str = "";
 	var arr1 = toDate.split('-');

 	toDate = arr1[0]+"-"+(arr1[1].substring(0,1)=="0"?arr1[1].substring(1,2):arr1[1])+"-01";
 	arr1 = toDate.split('-');

 	for(var i=months; i>=0; i--){
 		var dat1 = new Date(arr1[0], arr1[1]-1, arr1[2]);
		var date = getMinusMonth2(dat1,-(i));
		
 		var dateArr = date.split("-");
 		var dateStr = date.replace(/\-/gi, "");
 		var year 	= dateArr[0];
 		var month   = dateArr[1];
 		
 		str +=","+year+"-"+month; 
 		if(isColspan){
 			str +=",#cspan"; 
 	 		str +=",#cspan"; 
 		}else{
 			str +=","+year+"-"+month; 
 	 		str +=","+year+"-"+month; 
 		}
 	}
 	return str;
 }
 /**
  * val1,val2,val3 문자열을 days수만큼 연속해서 반환
  */
 function get3Words(months,val1,val2,val3){
 	var str = "";
 	for(var i=months; i>=0; i--){
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
<html:form action="/workRptPmMonthRateList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="workRptPmMonthRateListDTO.dateArrStr"/>
<html:hidden property="workRptPmMonthRateListDTO.filterDeptId"/>
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
							<html:text property="workRptPmMonthRateListDTO.filterStartDate" tabindex="210" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="workRptPmMonthRateListDTO.filterEndDate" tabindex="20" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 부서 -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="workRptPmMonthRateListDTO.filterDeptDesc" tabindex="30"/>
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
				<div id="wogridbox" style="width:100%; height:483px; background-color:white;"></div>
			</div>
		</div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>
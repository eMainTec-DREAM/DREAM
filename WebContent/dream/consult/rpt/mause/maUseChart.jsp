<%--===========================================================================
사용현황
author  kim21017
version $Id: maUseChart.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.rpt.mause.action.MaUseChartAction" %>
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
<!-- 사용현황 -->
<title><bean:message key="MENU.SYSUSELIST"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//
//그리드명
var useGrid;			//사용현황 그리드
var lang = loginUser.locale;

function loadPage() 
{
	//일자 세팅
	maUseChartForm.elements['maUseChartDTO.filterStartDate'].value = getMinusDay(5);
	maUseChartForm.elements['maUseChartDTO.filterEndDate'].value   = getMinusDay(1);

	//부서
<%-- 	maUseChartForm.elements['maUseChartDTO.filterDeptId'].value    = "<%=user.getDeptId()%>"; --%>
<%-- 	maUseChartForm.elements['maUseChartDTO.filterDeptDesc'].value  = "<%=user.getDeptDesc()%>"; --%>
	
	//전체사용현황 그리드 초기화
	initUseGrid(maUseChartForm.elements['maUseChartDTO.filterStartDate'].value,
			maUseChartForm.elements['maUseChartDTO.filterEndDate'].value);
	
	goSearch();
}

/**
 * 사용현황 그리드 초기화
 */
function initUseGrid(fromDate,toDate)
{
	var days = getDayInterval(fromDate.replace(/\-/gi, ""),toDate.replace(/\-/gi, ""));
    maUseChartForm.elements['maUseChartDTO.dateArrStr'].value = getDays(fromDate,toDate,days);
    useGrid = new dhtmlXGridObject('usegridbox');
    useGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	useGrid.setHeader(",<bean:message key='LABEL.dept'/>,<bean:message key='LABEL.plant'/>,<bean:message key='LABEL.title'/>"+getDays2(fromDate,toDate,days,lang));
	useGrid.setColumnIds("DEPTID,DEPTDESC,PLANTDESC,TITLE"+getDays(fromDate,toDate,days));
	useGrid.setInitWidths("100,200,100,200"+getWords(days,"70"));
	useGrid.setColAlign("left,left,left,left"+getWords(days,"center"));
	useGrid.setColTypes("ro,ro,ro,ro"+getWords(days,"ro"));
	useGrid.setColSorting("str,str,str,str"+getWords(days,"int"));
	useGrid.setColumnHidden(0,true);
	useGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	return sortColumn("maUseChart", this, maUseChartForm, "", ind, direction, "usegridbox");
	});
	useGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"usegridbox"); });
	useGrid.init();
	
	var strArr = getWeekSeqNo(getDays(maUseChartForm.elements['maUseChartDTO.filterStartDate'].value,
			maUseChartForm.elements['maUseChartDTO.filterEndDate'].value,getDayInterval(maUseChartForm.elements['maUseChartDTO.filterStartDate'].value.replace(/\-/gi, ""),maUseChartForm.elements['maUseChartDTO.filterEndDate'].value.replace(/\-/gi, "")))).split(",");
	for(var i=0; i<strArr.length;i++){
		if(strArr[i]!=0)
		useGrid.setColumnHidden(3+Number(strArr[i]),true);
	}
	
	isHeaderLoaded[currentPageId+".usegridbox"] = "Y";
}
/**
 * fromDate와 toDate사이의 일자를 반환(format:,2/16(월),2/17(화))
 *  2개씩 반환
 * 이페이지에서만 사용..
 */
function getDays2(fromDate,toDate,days,lang){
	var str = "";
	var arr1 = toDate.split('-');

	toDate = arr1[0]+"-"+(arr1[1].substring(0,1)=="0"?arr1[1].substring(1,2):arr1[1])+"-"+(arr1[2].substring(0,1)=="0"?arr1[2].substring(1,2):arr1[2]);
	arr1 = toDate.split('-');

	var date1 = new Date(arr1[0], arr1[1]-1, arr1[2]);
	for(var i=days; i>=0; i--){
		var dateArr = getMinusDay2(date1,i).split("-");
		var dateStr = getMinusDay2(date1,i).replace(/\-/gi, "");
		var month   = dateArr[1].substring(0,1)=="0"?dateArr[1].substring(1,2):dateArr[1];
		var day     = dateArr[2].substring(0,1)=="0"?dateArr[2].substring(1,2):dateArr[2];
		
		str +=","+month+"/"+day+"("+setComDay('"'+dateStr+'"',""+lang+"")+")";
	}
	return str;
}
/**
 * 현재 셋팅된 조건으로 값을 조회하여 사용현황 그리드에 셋팅한다.
 */
function findUseGridList(sheetAction)
{
    var url = contextPath + "/maUseChart.do";
    maUseChartForm.elements['strutsAction'].value = '<%=MaUseChartAction.USE_LIST_FIND%>';
    useGrid.clearAll();
    setLoading("usegridbox");
    //전체사용현황 그리드 초기화
    initUseGrid(maUseChartForm.elements['maUseChartDTO.filterStartDate'].value,maUseChartForm.elements['maUseChartDTO.filterEndDate'].value);
    
    doGridAction(sheetAction, useGrid, "usegridbox", url, FormQueryString(maUseChartForm), "", "Y");
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	//validation
	if(checkRequireValue("maUseChartDTO.filterStartDate","<bean:message key='LABEL.workDate'/>")) return;
	if(checkRequireValue("maUseChartDTO.filterEndDate","<bean:message key='LABEL.workDate'/>")) return;
	if(checkTwoDate(maUseChartForm.elements['maUseChartDTO.filterStartDate'],
			maUseChartForm.elements['maUseChartDTO.filterEndDate'])) return;
	
	//검색 기간 제한 
	var days = getDayInterval(maUseChartForm.elements['maUseChartDTO.filterStartDate'].value.replace(/\-/gi, ""),
			maUseChartForm.elements['maUseChartDTO.filterEndDate'].value.replace(/\-/gi, ""));
	
	if(days>14){
		alertMessage1("<bean:message key='MESSAGE.MSG0224'/>");
		return;
	}
    findUseGridList();
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
 * 전체사용현황 엑셀 다운
 */
function goExcel()
{
	excelServerAction("maUseChart", maUseChartForm);
}

function releaseRowSpan(_grid, columnId)
{
	var topValue = "";
	for(var i = 0; _grid.getRowsNum() > i; i++)
	{
		var cellValue = _grid.cellById(_grid.getRowId(i), getIndexById(_grid, columnId)).getValue();
		if(cellValue != "")
		{
			_grid.setRowspan(_grid.getRowId(i) ,getIndexById(_grid, columnId),1);
			topValue = cellValue;
		}
		else
		{
			_grid.cellById(_grid.getRowId(i), getIndexById(_grid, columnId)).setValue(topValue);
		}
	}
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maUseChart.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maUseChartDTO.dateArrStr"/>
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
							<html:text property="maUseChartDTO.filterStartDate" tabindex="10" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="maUseChartDTO.filterEndDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
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
				<div id="usegridbox" style="width:100%; height:500px; background-color:white;"></div>
			</div>
		</div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>
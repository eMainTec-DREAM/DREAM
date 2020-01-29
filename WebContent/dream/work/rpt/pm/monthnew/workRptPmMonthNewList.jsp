<%--===========================================================================
신규점검등록현황
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.pm.monthnew.action.WorkRptPmMonthNewListAction" %>
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
<!-- 신규점검등록현황 -->
<title><bean:message key="MENU.NEWPMI"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//
//그리드명
var myGrid;			//신규점검등록현황 그리드
var lang = loginUser.locale;

/** 자동완성 변수 */
var deptAc;
function loadPage() 
{
	//일자 세팅
	workRptPmMonthNewListForm.elements['workRptPmMonthNewListDTO.filterStartDate'].value = getMinusMonth(-2);
	workRptPmMonthNewListForm.elements['workRptPmMonthNewListDTO.filterEndDate'].value   = getMinusMonth(0);

	//전체신규점검등록현황 그리드 초기화
	initGrid(workRptPmMonthNewListForm.elements['workRptPmMonthNewListDTO.filterStartDate'].value,
			workRptPmMonthNewListForm.elements['workRptPmMonthNewListDTO.filterEndDate'].value);
	goSearch();
	
	deptAc = new autoC({"workRptPmMonthNewListDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	,"is_monitoring":"Y"
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "workRptPmMonthNewListDTO.filterDeptId":"dept_id"
    });
    deptAc.setKeyName("workRptPmMonthNewListDTO.filterDeptId");
    deptAc.init();
}

/**
 * 신규점검등록현황 그리드 초기화
 */
function initGrid(fromDate,toDate)
{
	var months = getMonthInterval(fromDate.replace(/\-/gi, ""),toDate.replace(/\-/gi, ""));
	
	workRptPmMonthNewListForm.elements['workRptPmMonthNewListDTO.months'].value = 
		months;

    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.enableTreeGridLines();
    myGrid.setImageSize(1,1);
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	myGrid.setHeader("<bean:message key='LABEL.dept'/>,,,,,"+getYearMonths(fromDate,toDate,months));
	myGrid.setColumnIds("DEPTDESC,ID,LVL,MINLVL,DEPTID,PDEPTID"+getYearMonthsId(fromDate,toDate,months));
	myGrid.setInitWidths("200,100,100,100,100,100"+getWords(months,"80"));
	myGrid.setColAlign("left,left,left,left,left,left"+getWords(months,"center"));
	myGrid.setColTypes("tree,ro,ro,ro,ro,ro"+getWords(months,"ro"));
	myGrid.setColumnHidden(1,true);
	myGrid.setColumnHidden(2,true);
	myGrid.setColumnHidden(3,true);
	myGrid.setColumnHidden(4,true);
	myGrid.setColumnHidden(5,true);

	$('#gridbox').height(500);
	myGrid.setSizes();
	
	myGrid.attachEvent("onRowDblClicked", function(id,ind){
		goPmList(id,ind);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox"); });
	myGrid.init();
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 신규점검등록현황 그리드에 셋팅한다.
 */
function findGridList()
{
	if(checkValidation()) return;

    var url = contextPath + "/workRptPmMonthNewList.do";
    workRptPmMonthNewListForm.elements['strutsAction'].value = '<%=WorkRptPmMonthNewListAction.LIST_FIND%>';
    myGrid.clearAll();
    setLoading("gridbox");
    //전체신규점검등록현황 그리드 초기화
    initGrid(workRptPmMonthNewListForm.elements['workRptPmMonthNewListDTO.filterStartDate'].value,workRptPmMonthNewListForm.elements['workRptPmMonthNewListDTO.filterEndDate'].value);

    setModal(); 
    
    $.post(url,FormQueryString(workRptPmMonthNewListForm), function(_data){
    	myGrid.parse(_data,"js");
    	myGrid.expandAll();
    	setCounter(myGrid,"gridbox");
    	closeModal();
    });
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	//validation
	
	if(checkRequireValue("workRptPmMonthNewListDTO.filterStartDate","<bean:message key='LABEL.workDate'/>")) return;
	if(checkRequireValue("workRptPmMonthNewListDTO.filterEndDate","<bean:message key='LABEL.workDate'/>")) return;
	if(checkTwoDate(workRptPmMonthNewListForm.elements['workRptPmMonthNewListDTO.filterStartDate']+"-01",
			workRptPmMonthNewListForm.elements['workRptPmMonthNewListDTO.filterEndDate']+"-01")) return;
	//검색 기간 제한 
	var months = getMonthInterval(workRptPmMonthNewListForm.elements['workRptPmMonthNewListDTO.filterStartDate'].value.replace(/\-/gi, ""),
			workRptPmMonthNewListForm.elements['workRptPmMonthNewListDTO.filterEndDate'].value.replace(/\-/gi, ""));
	
	if(months>24){
		alertMessage1("<bean:message key='MESSAGE.MSG0229'/>");
		return;
	}
		
    findGridList();
}
 
/**
 * 전체신규점검등록현황 엑셀 다운
 */
 function goExcel()
 {
 	excelAction(myGrid);
 } 

 /**
  * fromDate, toDate 사이 년월을 반환
  * 이 페이지에서 만 사용한다.
  */
 function getYearMonths(fromDate,toDate,months)
 {
 	var str = "";
 	var arr1 = fromDate.split('-');

 	fromDate = arr1[0]+"-"+(arr1[1].substring(0,1)=="0"?arr1[1].substring(1,2):arr1[1]);
 	arr1 = fromDate.split('-');

 	var fromDat = new Date(arr1[0], arr1[1]);

 	for(var i = 0; i<=months; i++)
 	{
 		if(fromDat.getMonth()==0)
		{
 			str +=","+(fromDat.getFullYear()-1)+"-12";
		}
 		else{
 			str +=","+fromDat.getFullYear()+"-"+(fromDat.getMonth() < 10? "0"+fromDat.getMonth(): fromDat.getMonth());
 			
 		}
 		fromDat.setMonth(fromDat.getMonth()+1);
 	}
 	return str;
 }

 /**
  * fromDate, toDate 사이 년월을 반환
  * 이 페이지에서 만 사용한다.
  */
 function getYearMonthsId(fromDate,toDate,months)
 {
 	var str = "";
 	var arr1 = fromDate.split('-');

 	fromDate = arr1[0]+"-"+(arr1[1].substring(0,1)=="0"?arr1[1].substring(1,2):arr1[1]);
 	arr1 = fromDate.split('-');
 	
 	var fromDat = new Date(arr1[0], arr1[1]);
 	
 	for(var i = 0; i<=months; i++)
 	{
 		if(fromDat.getMonth()==0)
		{
 			str +=",NEWPMICNT"+(fromDat.getFullYear()-1)+"12";
		}
 		else{
 			str +=",NEWPMICNT"+fromDat.getFullYear()+(fromDat.getMonth() < 10? "0"+fromDat.getMonth(): fromDat.getMonth());
 		}
 		fromDat.setMonth(fromDat.getMonth()+1);
 	}
 	return str;
 }
 
 function goPmList(id, ind)
 {
	 if(typeof id=="undefined"||typeof ind=="undefined") return ;

		var headerDate = myGrid.getColumnLabel(myGrid.getSelectedCellIndex(),0);
				
		var deptId = getValueById(myGrid, id, "DEPTID");
		var deptDesc = getValueById(myGrid, id, "DEPTDESC");
		var fromCreDate = headerDate.replace("-","")  +"01";
		var tempDate = new Date(fromCreDate.substr(0,4),fromCreDate.substr(4,2),fromCreDate.substr(6,2));
		var toCreDate = getMinusDay2(getMinusMonth2(tempDate,0),1).replace("-","").replace("-","");
		
		var url   = contextPath + "/maPmMstrList.do";
		var popWidth = 1010;
		var popHeight = 640;
	    // pop up이 중앙에 위치하게 한다.
	    var TopPosition  = (screen.height/2 - popHeight/2);
	    var LeftPosition = (screen.width/2 - popWidth/2);

	    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
	              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
	    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
	    
		var param = "isDecoratorName=popupPage"+
					"&maPmMstrCommonDTO.strutsAction="+
					"&maPmMstrCommonDTO.deptId="+deptId+
					"&maPmMstrCommonDTO.deptDesc="+deptDesc+
					"&maPmMstrCommonDTO.woType="+"PMI"+
					"&maPmMstrCommonDTO.woTypeDesc="+""+
					"&maPmMstrCommonDTO.isLastVersion="+
					"&maPmMstrCommonDTO.isLastVersionDesc="+
					"&maPmMstrCommonDTO.filterFromCreDate="+fromCreDate+
					"&maPmMstrCommonDTO.filterToCreDate="+toCreDate
					+"&ACTION_FUNCTION=goSearch";
		
		//post 전송
		openWindowWithPost(url, "PM_LIST", param, pos);
 }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workRptPmMonthNewList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="workRptPmMonthNewListDTO.months"/>
<html:hidden property="workRptPmMonthNewListDTO.deptId"/>
<html:hidden property="workRptPmMonthNewListDTO.filterDeptId"/>
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
					<label class="check"><bean:message key="LABEL.month"/></label>
					<div class="calendar_wrap">
						<div class="input_read input_carendar">
							<html:text property="workRptPmMonthNewListDTO.filterStartDate" tabindex="10" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
						<div class="input_read input_carendar">
							<html:text property="workRptPmMonthNewListDTO.filterEndDate" tabindex="20" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 부서 -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="workRptPmMonthNewListDTO.filterDeptDesc" tabindex="30"/>
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
</html:form> 
</body>
</html>
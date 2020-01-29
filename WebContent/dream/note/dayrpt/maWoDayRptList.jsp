<%--===========================================================================
 - 목록
author  kim21017
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.note.dayrpt.action.MaWoDayRptListAction" %>
<%@ page import="dream.note.dayrpt.action.MaWoDayRptDetailAction" %>
<%@ page import="common.util.CommonUtil"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%
    User loginUser = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!-- 업무일지 -->
<title><bean:message key='MENU.WRKDAYRPT'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;
var mainMngAc;
var deptAc;
function loadPage() 
{
	maWoDayRptListForm.elements['maWoDayRptCommonDTO.filterStartDate'].value = getMinusDay(7);
    maWoDayRptListForm.elements['maWoDayRptCommonDTO.filterEndDate'].value   = getToday();
    
    initGrid();
    
    
    /**작성자  */
    mainMngAc = new autoC({"maWoDayRptCommonDTO.filterEmpDesc":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maWoDayRptCommonDTO.filterEmpId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maWoDayRptCommonDTO.filterDeptId"
    });
    mainMngAc.init();
    
    /** 부서명  */
    deptAc = new autoC({"maWoDayRptCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maWoDayRptCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.init();
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		maWoDayRptListForm.elements['maWoDayRptCommonDTO.wrkDayRptId'].value = ""
    	return sortColumn("maWoDayRptList", this, maWoDayRptListForm, "wrkDayRptId", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maWoDayRptList.do";

    maWoDayRptListForm.elements['strutsAction'].value = '<%=MaWoDayRptListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maWoDayRptListForm), "wrkDayRptId", "Y");
    
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_wrkDayRptId)
{
	maWoDayRptListForm.elements['maWoDayRptCommonDTO.wrkDayRptId'].value = _wrkDayRptId;
	findGridList('ReloadRow');
	maWoDayRptListForm.elements['maWoDayRptCommonDTO.wrkDayRptId'].value = "";
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	maWoDayRptListForm.elements['maWoDayRptCommonDTO.wrkDayRptId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search');   
}

/**
* Tab 이동시 호출
*/
function goTabPage(pageId)
{
   tabValidationCheck(myGrid, pageId, "goTabPageAction");
}

function goTabPageAction(pageId, selectedId)
{
	var form = document.maWoDayRptListForm;
	 
	form.elements['maWoDayRptCommonDTO.wrkDayRptId'].value = getValueById(myGrid, selectedId, 'WRKDAYRPTID');
	goCommonTabPage(form, <%= MaWoDayRptDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maWoDayRptDetail');	
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maWoDayRptListForm.elements['maWoDayRptCommonDTO.wrkDayRptId'].value = getValueById(myGrid, selectedId, 'WRKDAYRPTID');
    maWoDayRptListForm.elements['strutsAction'].value = '<%=MaWoDayRptDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maWoDayRptListForm), 'maWoDayRptDetail'); 
} 
 
/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maWoDayRptDetail" , "goCreateAction");
}


function goCreateAction(pageId)
{
	maWoDayRptListForm.elements['maWoDayRptCommonDTO.wrkDayRptId'].value = "";
	goCommonTabPage(maWoDayRptListForm, '', pageId);
}

/**
 * Excel Export
 */
function goExcel()
{
    // excelAction(myGrid);
    maWoDayRptListForm.elements['maWoDayRptCommonDTO.wrkDayRptId'].value = "";
	excelServerAction("maWoDayRptList", maWoDayRptListForm );  
}

/**
 * 삭제
 */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'WRKDAYRPTID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	maWoDayRptListForm.strutsAction.value = '<%=MaWoDayRptListAction.LIST_DELETE%>';
	var url = contextPath + "/maWoDayRptList.do";
	$.post(url,FormQueryString(maWoDayRptListForm)+delArray , function(_data){
    	afterDelete();
    });
}

function afterDelete(){
	goClose('maWoDayRptDetail');
 	//goSearch();
 	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
 }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maWoDayRptList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maWoDayRptCommonDTO.wrkDayRptId"/><!-- Key -->
<html:hidden property="maWoDayRptCommonDTO.filterEmpId"/>
<html:hidden property="maWoDayRptCommonDTO.filterDeptId"/>
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
					<label><bean:message key="LABEL.date"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="maWoDayRptCommonDTO.filterStartDate" tabindex="10" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="maWoDayRptCommonDTO.filterEndDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 작성자 -->
				<div class="field">
					<label><bean:message key="LABEL.writeBy"/></label>
					<div class="input_box">
						<html:text property="maWoDayRptCommonDTO.filterEmpDesc" tabindex="30"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 부서 -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="maWoDayRptCommonDTO.filterDeptDesc" tabindex="40"/>
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
		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
	 </div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>
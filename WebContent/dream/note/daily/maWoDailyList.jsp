<%--===========================================================================
 - 목록
author  kim21017
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.note.daily.action.MaWoDailyListAction" %>
<%@ page import="dream.note.daily.action.MaWoDailyDetailAction" %>
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
<!--  -->
<title><bean:message key='MENU.WODAILY'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;

var deptAc;
var plantAc;
function loadPage() 
{
	/* maWoDailyListForm.elements['maWoDailyCommonDTO.filterDeptId'].value   = loginUser.filterDeptId;
	maWoDailyListForm.elements['maWoDailyCommonDTO.filterDeptDesc'].value = loginUser.filterDeptDesc;
	
	maWoDailyListForm.elements['maWoDailyCommonDTO.filterStartDate'].value = getMinusMonth2(new Date(), -1); 
    maWoDailyListForm.elements['maWoDailyCommonDTO.filterEndDate'].value   = getToday(); */
    
    initGrid();
    
    deptAc = new autoC({"maWoDailyCommonDTO.deptDesc":"description"});
    deptAc.setAcDisplay("DESCRIPTION");
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maWoDailyCommonDTO.deptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "maWoDailyCommonDTO.plantId"
    });
    deptAc.init();
    
    plantAc = new autoC({"maWoDailyCommonDTO.plantDesc":"description"});
    plantAc.setAcDisplay("DESCRIPTION");
    plantAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    plantAc.setTable("TAPLANT");
    plantAc.setAcResultMap({
        "maWoDailyCommonDTO.plantId":"plant"
    });
    plantAc.init();
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
		maWoDailyListForm.elements['maWoDailyCommonDTO.woDayListId'].value = "";
    	return sortColumn("maWoDailyList", this, maWoDailyListForm, "WODAYLISTID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maWoDailyList.do";

    maWoDailyListForm.elements['strutsAction'].value = '<%=MaWoDailyListAction.WO_DAILY_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maWoDailyListForm), "WODAYLISTID", "Y");
    
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_woDayListId)
{
	maWoDailyListForm.elements['maWoDailyCommonDTO.woDayListId'].value = _woDayListId;
	findGridList('ReloadRow');
	maWoDailyListForm.elements['maWoDailyCommonDTO.woDayListId'].value = "";
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	maWoDailyListForm.elements['maWoDailyCommonDTO.woDayListId'].value = "";	// 검색시 Tab 이동Key Clear
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
	var form = document.maWoDailyListForm;
	 
	form.elements['maWoDailyCommonDTO.woDayListId'].value = getValueById(myGrid, selectedId, 'WODAYLISTID');
	goCommonTabPage(form, <%= MaWoDailyDetailAction.WO_DAILY_DETAIL_INIT %>, pageId);
}

/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maWoDailyDetail');	
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    var form = document.maWoDailyListForm;
    form.elements['maWoDailyCommonDTO.woDayListId'].value = getValueById(myGrid, selectedId, 'WODAYLISTID');
    form.elements['strutsAction'].value = '<%=MaWoDailyDetailAction.WO_DAILY_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(form), 'maWoDailyDetail'); 
} 
 
/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maWoDailyDetail" , "goCreateAction");
}

function execValidation(deptId, workDate)
{
	var form = document.maWoDailyListForm;
	
	form.elements['maWoDailyCommonDTO.woDayListId'].value = "";
	goCommonTabPage(form, <%= MaWoDailyDetailAction.WO_DAILY_DETAIL_INIT %>, "maWoDailyDetail");
}

function goCreateAction(pageId)
{
	maWoDailyListForm.elements['maWoDailyCommonDTO.woDayListId'].value = "";
	goCommonTabPage(maWoDailyListForm, '', pageId);
}

/**
 * Excel Export
 */
function goExcel()
{
    //excelAction(myGrid);
    maWoDailyListForm.elements['maWoDailyCommonDTO.woDayListId'].value = "";
	excelServerAction("maWoDailyList", maWoDailyListForm );  
}

/**
 * 삭제
 */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'WODAYLISTID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	maWoDailyListForm.strutsAction.value = '<%=MaWoDailyListAction.WO_DAILY_LIST_DELETE%>';
	var url = contextPath + "/maWoDailyList.do";
	$.post(url,FormQueryString(maWoDailyListForm)+delArray , function(_data){
    	afterDelete();
    });
}

function afterDelete(){
	goClose('maWoDailyDetail');
 	//goSearch();
 	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
 }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maWoDailyList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maWoDailyCommonDTO.woDayListId"/><!-- Key -->
<html:hidden property="maWoDailyCommonDTO.deptId"/>
<html:hidden property="maWoDailyCommonDTO.plantId"/>

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
				<!-- 확인일자 -->
				<div class="field">
					<label><bean:message key="LABEL.apprDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="maWoDailyCommonDTO.startDate" tabindex="10" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="maWoDailyCommonDTO.endDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
                <!-- 부서 -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="maWoDailyCommonDTO.deptDesc" tabindex="30"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 제목 -->
				<div class="field">
					<label><bean:message key="LABEL.title"/></label>
					<div class="input_box">
						<html:text property="maWoDailyCommonDTO.title" tabindex="40"/>
					</div>
				</div>
				<!-- 공장 -->
				<div class="field">
					<label><bean:message key="LABEL.plant"/></label>
					<div class="input_box">
						<html:text property="maWoDailyCommonDTO.plantDesc" tabindex="50"/>
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
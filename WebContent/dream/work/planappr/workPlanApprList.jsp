<%--===========================================================================
작업계획 승인목록
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.planappr.action.WorkPlanApprListAction" %>
<%@ page import="dream.work.planappr.action.WorkPlanApprDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<html>
<head>
<!-- 작업계획승인 -->
<title><bean:message key='MENU.WOPLANAPPR'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->

<script language="javascript">
<!--//

var myGrid;

/** 자동완성 변수 */
var deptAc, plantAc;
function loadPage()
{

	//담당자 
	workPlanApprListForm.elements['workPlanApprCommonDTO.filterDeptId'].value = loginUser.filterDeptId;
	workPlanApprListForm.elements['workPlanApprCommonDTO.filterDeptDesc'].value = loginUser.filterDeptDesc;

    initGrid();

    /** 요청부서  */
    deptAc = new autoC({"workPlanApprCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	  , "plant":workPlanApprListForm.elements['workPlanApprCommonDTO.filterPlantId'].value
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "workPlanApprCommonDTO.filterDeptId":"dept_id"
       ,"workPlanApprCommonDTO.filterPlantId":"plant"
       ,"workPlanApprCommonDTO.filterPlantDesc":"plantDesc"
    });
    deptAc.init();
    
 	// 공장코드
	plantAc = new autoC({"workPlanApprCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "workPlanApprCommonDTO.filterPlantId":"plant"
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
		workPlanApprListForm.elements['workPlanApprCommonDTO.woPlanApprId'].value = "";
    	return sortColumn("workPlanApprList", this, workPlanApprListForm, "WOPLANAPPRID", ind, direction);
	}); 
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/workPlanApprList.do";

    workPlanApprListForm.elements['strutsAction'].value = '<%=WorkPlanApprListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workPlanApprListForm), "WOPLANAPPRID","Y");

}

/**
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_woPlanApprId)
{
	workPlanApprListForm.elements['workPlanApprCommonDTO.woPlanApprId'].value = _woPlanApprId;
	findGridList('ReloadRow');
	workPlanApprListForm.elements['workPlanApprCommonDTO.woPlanApprId'].value = "";
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	workPlanApprListForm.elements['workPlanApprCommonDTO.woPlanApprId'].value = "";	// 검색시 Tab 이동Key Clear
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
	var form = document.workPlanApprListForm;

	form.elements['workPlanApprCommonDTO.woPlanApprId'].value = getValueById(myGrid, selectedId, 'WOPLANAPPRID');
	goCommonTabPage(form, <%= WorkPlanApprDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('workPlanApprDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    workPlanApprListForm.elements['workPlanApprCommonDTO.woPlanApprId'].value = getValueById(myGrid, selectedId, 'WOPLANAPPRID');
    workPlanApprListForm.elements['strutsAction'].value = '<%=WorkPlanApprDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(workPlanApprListForm), 'workPlanApprDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "workPlanApprDetail" , "goCreateAction");
}


function goCreateAction(pageId)
{
	workPlanApprListForm.elements['workPlanApprCommonDTO.woPlanApprId'].value = "";
	goCommonTabPage(workPlanApprListForm, '', pageId);
}

/**
 * Excel Export
 */
function goExcel()
{
	workPlanApprListForm.elements['workPlanApprCommonDTO.woPlanApprId'].value = "";
 	excelServerAction("workPlanApprList", workPlanApprListForm );  
}

/**
 * 삭제
 */
function goDelete(){
	var chkedRowsId = getCheckedRows(myGrid, 'ISDELCHECK');
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'WOPLANAPPRID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	workPlanApprListForm.strutsAction.value = '<%=WorkPlanApprListAction.LIST_DELETE%>';
	var url = contextPath + "/workPlanApprList.do";
	$.post(url,FormQueryString(workPlanApprListForm)+delArray , function(_data){
    	afterDelete();
    });
}

function afterDelete(){
	goClose('workPlanApprDetail');
 	//goSearch();
 	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
 }
 
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = workPlanApprListForm.elements['workPlanApprCommonDTO.woPlanApprId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/workPlanApprList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="workPlanApprCommonDTO.woPlanApprId"/><!-- Key -->
<html:hidden property="workPlanApprCommonDTO.filterDeptId"/>
<html:hidden property="workPlanApprCommonDTO.filterPlantId"/>

<html:hidden property="workPlanApprCommonDTO.woplanapprType"/>
<html:hidden property="workPlanApprCommonDTO.woType"/>
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
				<!-- 작성일자 -->
				<div class="field">
					<label><bean:message key="LABEL.repRegDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="workPlanApprCommonDTO.filterStartDate" tabindex="10" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="workPlanApprCommonDTO.filterEndDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 요청부서 -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="workPlanApprCommonDTO.filterDeptDesc" tabindex="30"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 제목 -->
				<div class="field">
					<label><bean:message key="LABEL.title"/></label>
					<div class="input_box">
						<html:text property="workPlanApprCommonDTO.filterDesc" tabindex="40"/>
					</div>
				</div>
				<!-- 공장명 -->
				<div class="field">
					<label><bean:message key="LABEL.plantDesc"/></label>
					<div class="input_box">
						<html:text property="workPlanApprCommonDTO.filterPlantDesc" tabindex="50"/>
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
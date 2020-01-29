<%--===========================================================================
고장영향성 평가
author  youngjoo38
version $Id: workFmeaResList.jsp,v 1.1 2017/09/28 01:45:27 youngjoo38 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.fmea.list.action.WorkFmeaResListAction" %>
<%@ page import="dream.work.fmea.list.action.WorkFmeaResDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 고장영향성 평가접수 요청 -->
<title><bean:message key='MENU.WORKFMEARES'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

/** 자동완성 변수 */
var reqDeptAc;
var reqByAc;
var equipAc;
var reviewByAc;
function loadPage() 
{
	workFmeaResListForm.elements['workFmeaResCommonDTO.filterReqStartDate'].value   = getMinusDay(7);
	workFmeaResListForm.elements['workFmeaResCommonDTO.filterReqEndDate'].value   = getToday();
	
    initGrid();
	//의뢰부서 AC
    reqDeptAc = new autoC({"workFmeaResCommonDTO.filterReqDeptDesc":"description"});
    reqDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    reqDeptAc.setTable("TADEPT");
    reqDeptAc.setAcResultMap({
        "workFmeaResCommonDTO.filterReqDeptId":"dept_id"
    });
    reqDeptAc.init();
    //의뢰자 AC
    reqByAc = new autoC({"workFmeaResCommonDTO.filterReqByDesc":"emp_name"});
    reqByAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    reqByAc.setAcDConditionMap({
    	"dept_id":"workFmeaResCommonDTO.filterReqDeptId"
    });
    reqByAc.setTable("TAEMP");
    reqByAc.setAcResultMap({
        "workFmeaResCommonDTO.filterReqById":"emp_id"
    });
    reqByAc.init();
	//접수자 AC
    reviewByAc = new autoC({"workFmeaResCommonDTO.filterReviewByDesc":"emp_name"});
    reviewByAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    reviewByAc.setTable("TAEMP");
    reviewByAc.setAcResultMap({
        "workFmeaResCommonDTO.filterReviewById":"emp_id"
    });
    reviewByAc.init();
    //설비 AC
    equipAc = new autoC({"workFmeaResCommonDTO.filterEquipDesc":"description"});
    equipAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipAc.setAcDConditionMap({
    	"dept_id" : "workFmeaResCommonDTO.filterReqDeptId"
    });
    equipAc.setAcResultMap({
        "workFmeaResCommonDTO.filterEquipId":"equip_id"
    });
    equipAc.setTable("TAEQUIPMENT");
    equipAc.init();
    
    //상태 AC
    acSysDesc("workFmeaResCommonDTO.filterWoFmeaStatusDesc","workFmeaResCommonDTO.filterWoFmeaStatusId","WOFMEA_STATUS");
    //영향성평가 AC
    acSysDesc("workFmeaResCommonDTO.filterFmeaPriorityDesc","workFmeaResCommonDTO.filterFmeaPriorityId","FMEA_PRIORITY");
    //작업구분 AC
    acSysDesc("workFmeaResCommonDTO.filterFmeaWoTypeDesc","workFmeaResCommonDTO.filterFmeaWoTypeId","FMEA_WOTYPE");
    //Calibration 여부 AC
    acSysDesc("workFmeaResCommonDTO.filterIsCalibDesc","workFmeaResCommonDTO.filterIsCalibId","IS_USE",true);
    //Qualification 여부 AC
    acSysDesc("workFmeaResCommonDTO.filterIsQualDesc","workFmeaResCommonDTO.filterIsQualId","IS_USE",true);
	
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
		workFmeaResListForm.elements['workFmeaResCommonDTO.woFmeaId'].value = "";
    	return sortColumn("workFmeaResList", this, workFmeaResListForm, "WOFMEANO", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/workFmeaResList.do";
    workFmeaResListForm.elements['strutsAction'].value = '<%=WorkFmeaResListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workFmeaResListForm), "WOFMEAID","Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_woFmeaId)
{
	workFmeaResListForm.elements['workFmeaResCommonDTO.woFmeaId'].value = _woFmeaId;
	findGridList('ReloadRow');
	workFmeaResListForm.elements['workFmeaResCommonDTO.woFmeaId'].value = "";
}

function goSearch()
{
	workFmeaResListForm.elements['workFmeaResCommonDTO.woFmeaId'].value = "";	// 검색시 Tab 이동Key Clear
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
	workFmeaResListForm.elements['workFmeaResCommonDTO.woFmeaId'].value =  getValueById(myGrid, selectedId,'WOFMEAID');  
	goCommonTabPage(workFmeaResListForm, <%= WorkFmeaResDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('workFmeaResDetail');
}

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "workFmeaResDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	workFmeaResListForm.elements['workFmeaResCommonDTO.woFmeaId'].value = "";
    goCommonTabPage(workFmeaResListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'WOFMEAID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    workFmeaResListForm.strutsAction.value = '<%=WorkFmeaResListAction.LIST_DELETE%>';
    var url = contextPath + "/workFmeaResList.do";
    
    $.post(url,FormQueryString(workFmeaResListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('workFmeaResDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	workFmeaResListForm.elements['workFmeaResCommonDTO.woFmeaId'].value = "";
	excelServerAction("workFmeaResList", workFmeaResListForm );  
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workFmeaResList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="workFmeaResCommonDTO.woFmeaId"/><!-- Key -->
<html:hidden property="workFmeaResCommonDTO.filterWoFmeaStatusId"/>
<html:hidden property="workFmeaResCommonDTO.filterReqDeptId"/>
<html:hidden property="workFmeaResCommonDTO.filterReqById"/>
<html:hidden property="workFmeaResCommonDTO.filterEquipId"/>
<html:hidden property="workFmeaResCommonDTO.filterReviewById"/>
<html:hidden property="workFmeaResCommonDTO.filterFmeaPriorityId"/>
<html:hidden property="workFmeaResCommonDTO.filterFmeaWoTypeId"/>
<html:hidden property="workFmeaResCommonDTO.filterIsCalibId"/>
<html:hidden property="workFmeaResCommonDTO.filterIsQualId"/>
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
				<!-- 의뢰일자 -->
				<div class="field">
					<label><bean:message key="LABEL.requestDate1"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="workFmeaResCommonDTO.filterReqStartDate" tabindex="10" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="workFmeaResCommonDTO.filterReqEndDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 상태  -->
				<div class="field">
					<label><bean:message key="LABEL.status"/></label>
					<div class="input_box">
						<html:text property="workFmeaResCommonDTO.filterWoFmeaStatusDesc" tabindex="30" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 의뢰부서  -->
				<div class="field">
					<label><bean:message key="LABEL.requestDept"/></label>
					<div class="input_box">
						<html:text property="workFmeaResCommonDTO.filterReqDeptDesc" tabindex="40"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 의뢰자  -->
				<div class="field">
					<label><bean:message key="LABEL.requestBy"/></label>
					<div class="input_box">
						<html:text property="workFmeaResCommonDTO.filterReqByDesc" tabindex="50"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 설비  -->
				<div class="field">
					<label><bean:message key="LABEL.equipment"/></label>
					<div class="input_box">
						<html:text property="workFmeaResCommonDTO.filterEquipDesc" tabindex="60"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 접수번호  -->
				<div class="field">
					<label><bean:message key="LABEL.receiptNo"/></label>
					<div class="input_box">
						<html:text property="workFmeaResCommonDTO.filterWoFmeaNo" tabindex="70"/>
					</div>
				</div>
				<!-- 검토일자 -->
				<div class="field">
					<label><bean:message key="LABEL.viewDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="workFmeaResCommonDTO.filterReviewStartDate" tabindex="80" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="workFmeaResCommonDTO.filterReviewEndDate" tabindex="90" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 검토자  -->
				<div class="field">
					<label><bean:message key="LABEL.viewBy"/></label>
					<div class="input_box">
						<html:text property="workFmeaResCommonDTO.filterReviewByDesc" tabindex="100"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 영향성평가  -->
				<div class="field">
					<label><bean:message key="LABEL.fmeaPriority"/></label>
					<div class="input_box">
						<html:text property="workFmeaResCommonDTO.filterFmeaPriorityDesc" tabindex="110"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 작업구분  -->
				<div class="field">
					<label><bean:message key="LABEL.fmeaWoType"/></label>
					<div class="input_box">
						<html:text property="workFmeaResCommonDTO.filterFmeaWoTypeDesc" tabindex="120"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- Calibration 여부  -->
				<div class="field">
					<label><bean:message key="LABEL.calibration1"/></label>
					<div class="input_box">
						<html:text property="workFmeaResCommonDTO.filterIsCalibDesc" tabindex="130"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- Qualification 여부  -->
				<div class="field">
					<label><bean:message key="LABEL.qualification"/></label>
					<div class="input_box">
						<html:text property="workFmeaResCommonDTO.filterIsQualDesc" tabindex="140"/>
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
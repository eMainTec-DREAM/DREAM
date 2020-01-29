<%--===========================================================================
고장영향성 평가
author  kim21017
version $Id: workFmeaReqList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.fmea.list.action.WorkFmeaReqListAction" %>
<%@ page import="dream.work.fmea.list.action.WorkFmeaReqDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 고장영향성 평가 -->
<title><bean:message key='MENU.WORKFMEAREQ'/></title>
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
	workFmeaReqListForm.elements['workFmeaReqCommonDTO.filterReqStartDate'].value   = getMinusDay(7);
	workFmeaReqListForm.elements['workFmeaReqCommonDTO.filterReqEndDate'].value   = getToday();
	
    initGrid();
	//의뢰부서 AC
    reqDeptAc = new autoC({"workFmeaReqCommonDTO.filterReqDeptDesc":"description"});
    reqDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    reqDeptAc.setTable("TADEPT");
    reqDeptAc.setAcResultMap({
        "workFmeaReqCommonDTO.filterReqDeptId":"dept_id"
    });
    reqDeptAc.init();
    //의뢰자 AC
    reqByAc = new autoC({"workFmeaReqCommonDTO.filterReqByDesc":"emp_name"});
    reqByAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    reqByAc.setAcDConditionMap({
    	"dept_id":"workFmeaReqCommonDTO.filterReqDeptId"
    });
    reqByAc.setTable("TAEMP");
    reqByAc.setAcResultMap({
        "workFmeaReqCommonDTO.filterReqById":"emp_id"
    });
    reqByAc.init();
	//접수자 AC
    reviewByAc = new autoC({"workFmeaReqCommonDTO.filterReviewByDesc":"emp_name"});
    reviewByAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    reviewByAc.setTable("TAEMP");
    reviewByAc.setAcResultMap({
        "workFmeaReqCommonDTO.filterReviewById":"emp_id"
    });
    reviewByAc.init();
    //설비 AC
    equipAc = new autoC({"workFmeaReqCommonDTO.filterEquipDesc":"description"});
    equipAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipAc.setAcDConditionMap({
    	"dept_id" : "workFmeaReqCommonDTO.filterReqDeptId"
    });
    equipAc.setAcResultMap({
        "workFmeaReqCommonDTO.filterEquipId":"equip_id"
    });
    equipAc.setTable("TAEQUIPMENT");
    equipAc.init();
    
    //상태 AC
    acSysDesc("workFmeaReqCommonDTO.filterWoFmeaStatusDesc","workFmeaReqCommonDTO.filterWoFmeaStatusId","WOFMEA_STATUS");
    //영향성평가 AC
    acSysDesc("workFmeaReqCommonDTO.filterFmeaPriorityDesc","workFmeaReqCommonDTO.filterFmeaPriorityId","FMEA_PRIORITY");
    //작업구분 AC
    acSysDesc("workFmeaReqCommonDTO.filterFmeaWoTypeDesc","workFmeaReqCommonDTO.filterFmeaWoTypeId","FMEA_WOTYPE");
    //Calibration 여부 AC
    acSysDesc("workFmeaReqCommonDTO.filterIsCalibDesc","workFmeaReqCommonDTO.filterIsCalibId","IS_USE",true);
    //Qualification 여부 AC
    acSysDesc("workFmeaReqCommonDTO.filterIsQualDesc","workFmeaReqCommonDTO.filterIsQualId","IS_USE",true);
	
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
		workFmeaReqListForm.elements['workFmeaReqCommonDTO.woFmeaId'].value = "";
    	return sortColumn("workFmeaReqList", this, workFmeaReqListForm, "WOFMEANO", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/workFmeaReqList.do";
    workFmeaReqListForm.elements['strutsAction'].value = '<%=WorkFmeaReqListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workFmeaReqListForm), "WOFMEAID","Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_woFmeaId)
{
	workFmeaReqListForm.elements['workFmeaReqCommonDTO.woFmeaId'].value = _woFmeaId;
	findGridList('ReloadRow');
	workFmeaReqListForm.elements['workFmeaReqCommonDTO.woFmeaId'].value = "";
}

function goSearch()
{
	workFmeaReqListForm.elements['workFmeaReqCommonDTO.woFmeaId'].value = "";	// 검색시 Tab 이동Key Clear
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
	workFmeaReqListForm.elements['workFmeaReqCommonDTO.woFmeaId'].value = getValueById(myGrid, selectedId,'WOFMEAID');  
	goCommonTabPage(workFmeaReqListForm, <%= WorkFmeaReqDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('workFmeaReqDetail');
}

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "workFmeaReqDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	workFmeaReqListForm.elements['workFmeaReqCommonDTO.woFmeaId'].value = "";
    goCommonTabPage(workFmeaReqListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
	var chkedRowsId = getCheckedRows(myGrid, 'ISDELCHECK');

	for(var i = 0 ;i < chkedRowsId.length; i++)
	    {
	        if(getValueById(myGrid, chkedRowsId[i], "woFmeaStatusId") != "WRT")
	        {
	        	alertMessage1("<bean:message key='MESSAGE.MSG0153'/>");
	            return;	
	        }
	    }
	
	
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'WOFMEAID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    workFmeaReqListForm.strutsAction.value = '<%=WorkFmeaReqListAction.LIST_DELETE%>';
    var url = contextPath + "/workFmeaReqList.do";
    
    $.post(url,FormQueryString(workFmeaReqListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('workFmeaReqDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	workFmeaReqListForm.elements['workFmeaReqCommonDTO.woFmeaId'].value = "";
	excelServerAction("workFmeaReqList", workFmeaReqListForm );  
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workFmeaReqList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="workFmeaReqCommonDTO.woFmeaId"/><!-- Key -->
<html:hidden property="workFmeaReqCommonDTO.filterWoFmeaStatusId"/>
<html:hidden property="workFmeaReqCommonDTO.filterReqDeptId"/>
<html:hidden property="workFmeaReqCommonDTO.filterReqById"/>
<html:hidden property="workFmeaReqCommonDTO.filterEquipId"/>
<html:hidden property="workFmeaReqCommonDTO.filterReviewById"/>
<html:hidden property="workFmeaReqCommonDTO.filterFmeaPriorityId"/>
<html:hidden property="workFmeaReqCommonDTO.filterFmeaWoTypeId"/>
<html:hidden property="workFmeaReqCommonDTO.filterIsCalibId"/>
<html:hidden property="workFmeaReqCommonDTO.filterIsQualId"/>
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
							<html:text property="workFmeaReqCommonDTO.filterReqStartDate" tabindex="10" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="workFmeaReqCommonDTO.filterReqEndDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 상태  -->
				<div class="field">
					<label><bean:message key="LABEL.status"/></label>
					<div class="input_box">
						<html:text property="workFmeaReqCommonDTO.filterWoFmeaStatusDesc" tabindex="30" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 의뢰부서  -->
				<div class="field">
					<label><bean:message key="LABEL.requestDept"/></label>
					<div class="input_box">
						<html:text property="workFmeaReqCommonDTO.filterReqDeptDesc" tabindex="40"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 의뢰자  -->
				<div class="field">
					<label><bean:message key="LABEL.requestBy"/></label>
					<div class="input_box">
						<html:text property="workFmeaReqCommonDTO.filterReqByDesc" tabindex="50"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 설비  -->
				<div class="field">
					<label><bean:message key="LABEL.equipment"/></label>
					<div class="input_box">
						<html:text property="workFmeaReqCommonDTO.filterEquipDesc" tabindex="60"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 접수번호  -->
				<div class="field">
					<label><bean:message key="LABEL.receiptNo"/></label>
					<div class="input_box">
						<html:text property="workFmeaReqCommonDTO.filterWoFmeaNo" tabindex="70"/>
					</div>
				</div>
				<!-- 검토일자 -->
				<div class="field">
					<label><bean:message key="LABEL.viewDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="workFmeaReqCommonDTO.filterReviewStartDate" tabindex="80" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="workFmeaReqCommonDTO.filterReviewEndDate" tabindex="90" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 검토자  -->
				<div class="field">
					<label><bean:message key="LABEL.viewBy"/></label>
					<div class="input_box">
						<html:text property="workFmeaReqCommonDTO.filterReviewByDesc" tabindex="100"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 영향성평가  -->
				<div class="field">
					<label><bean:message key="LABEL.fmeaPriority"/></label>
					<div class="input_box">
						<html:text property="workFmeaReqCommonDTO.filterFmeaPriorityDesc" tabindex="110"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 작업구분  -->
				<div class="field">
					<label><bean:message key="LABEL.fmeaWoType"/></label>
					<div class="input_box">
						<html:text property="workFmeaReqCommonDTO.filterFmeaWoTypeDesc" tabindex="120"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- Calibration 여부  -->
				<div class="field">
					<label><bean:message key="LABEL.calibration1"/></label>
					<div class="input_box">
						<html:text property="workFmeaReqCommonDTO.filterIsCalibDesc" tabindex="130"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- Qualification 여부  -->
				<div class="field">
					<label><bean:message key="LABEL.qualification"/></label>
					<div class="input_box">
						<html:text property="workFmeaReqCommonDTO.filterIsQualDesc" tabindex="140"/>
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
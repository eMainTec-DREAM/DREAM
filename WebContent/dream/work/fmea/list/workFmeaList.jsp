<%--===========================================================================
고장영향성 평가
author  kim21017
version $Id: workFmeaList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.fmea.list.action.WorkFmeaListAction" %>
<%@ page import="dream.work.fmea.list.action.WorkFmeaDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 고장영향성 평가 -->
<title><bean:message key='MENU.WORKFMEA'/></title>
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
var plantAc;

function loadPage() 
{
	if(window.name =="WORK_POPUP"){
	} else {
		workFmeaListForm.elements['workFmeaCommonDTO.filterReqStartDate'].value   = getMinusDay(7);
		workFmeaListForm.elements['workFmeaCommonDTO.filterReqEndDate'].value   = getToday();
		
	}
	
	//공장명
    if(loginUser.filterPlant!='null'){
    	workFmeaListForm.elements['workFmeaCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	workFmeaListForm.elements['workFmeaCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
	
    initGrid();
	//의뢰부서 AC
    reqDeptAc = new autoC({"workFmeaCommonDTO.filterReqDeptDesc":"description"});
    reqDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    reqDeptAc.setAcDConditionMap({
    	"plant" : "workFmeaCommonDTO.filterPlantId"
    });
    reqDeptAc.setTable("TADEPT");
    reqDeptAc.setAcResultMap({
        "workFmeaCommonDTO.filterReqDeptId":"dept_id"
    });
    reqDeptAc.init();
    //의뢰자 AC
    reqByAc = new autoC({"workFmeaCommonDTO.filterReqByDesc":"emp_name"});
    reqByAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    reqByAc.setAcDConditionMap({
    	"plant" : "workFmeaCommonDTO.filterPlantId"
    });
    reqByAc.setTable("TAEMP");
    reqByAc.setAcResultMap({
        "workFmeaCommonDTO.filterReqById":"emp_id"
    });
    reqByAc.init();
	//접수자 AC
    reviewByAc = new autoC({"workFmeaCommonDTO.filterReviewByDesc":"emp_name"});
    reviewByAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    reviewByAc.setAcDConditionMap({
    	"plant" : "workFmeaCommonDTO.filterPlantId"
    });
    reviewByAc.setTable("TAEMP");
    reviewByAc.setAcResultMap({
        "workFmeaCommonDTO.filterReviewById":"emp_id"
    });
    reviewByAc.init();
    //설비 AC
    equipAc = new autoC({"workFmeaCommonDTO.filterEquipDesc":"description"});
    equipAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	});
    equipAc.setAcDConditionMap({
    	"plant" : "workFmeaCommonDTO.filterPlantId"
    });
    equipAc.setAcResultMap({
        "workFmeaCommonDTO.filterEquipId":"equip_id"
    });
    equipAc.setTable("TAEQUIPMENT");
    equipAc.init();

    // 공장명
    plantAc = new autoC({"workFmeaCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "workFmeaCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
    //상태 AC
    acSysDesc("workFmeaCommonDTO.filterWoFmeaStatusDesc","workFmeaCommonDTO.filterWoFmeaStatusId","WOFMEA_STATUS");
    //영향성평가 AC
    acSysDesc("workFmeaCommonDTO.filterFmeaPriorityDesc","workFmeaCommonDTO.filterFmeaPriorityId","FMEA_PRIORITY");
    //작업구분 AC
    acSysDesc("workFmeaCommonDTO.filterFmeaWoTypeDesc","workFmeaCommonDTO.filterFmeaWoTypeId","FMEA_WOTYPE");
    //Calibration 여부 AC
    acSysDesc("workFmeaCommonDTO.filterIsCalibDesc","workFmeaCommonDTO.filterIsCalibId","IS_USE",true);
    //Qualification 여부 AC
    acSysDesc("workFmeaCommonDTO.filterIsQualDesc","workFmeaCommonDTO.filterIsQualId","IS_USE",true);
	
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
/* 	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		workFmeaListForm.elements['workFmeaCommonDTO.woFmeaId'].value = "";
    	return sortColumn("workFmeaList", this, workFmeaListForm, "WOFMEANO", ind, direction);
	}); */
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/workFmeaList.do";
    workFmeaListForm.elements['strutsAction'].value = '<%=WorkFmeaListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workFmeaListForm), "WOFMEAID","Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_woFmeaId)
{
	workFmeaListForm.elements['workFmeaCommonDTO.woFmeaId'].value = _woFmeaId;
	findGridList('ReloadRow');
	workFmeaListForm.elements['workFmeaCommonDTO.woFmeaId'].value = "";
}

function goSearch()
{
	workFmeaListForm.elements['workFmeaCommonDTO.woFmeaId'].value = "";	// 검색시 Tab 이동Key Clear
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
	workFmeaListForm.elements['workFmeaCommonDTO.woFmeaId'].value =  getValueById(myGrid, selectedId,'WOFMEAID');  
	goCommonTabPage(workFmeaListForm, <%= WorkFmeaDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('workFmeaDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    workFmeaListForm.elements['workFmeaCommonDTO.woFmeaId'].value =  getValueById(myGrid, selectedId,'WOFMEAID');  
    workFmeaListForm.elements['strutsAction'].value = '<%=WorkFmeaDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(workFmeaListForm), 'workFmeaDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "workFmeaDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	workFmeaListForm.elements['workFmeaCommonDTO.woFmeaId'].value = "";
    goCommonTabPage(workFmeaListForm, '', pageId);
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

    workFmeaListForm.strutsAction.value = '<%=WorkFmeaListAction.LIST_DELETE%>';
    var url = contextPath + "/workFmeaList.do";
    
    $.post(url,FormQueryString(workFmeaListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('workFmeaDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	workFmeaListForm.elements['workFmeaCommonDTO.woFmeaId'].value = "";
	excelServerAction("workFmeaList", workFmeaListForm );  
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = workFmeaListForm.elements['workFmeaCommonDTO.woFmeaId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workFmeaList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="workFmeaCommonDTO.woFmeaId"/><!-- Key -->
<html:hidden property="workFmeaCommonDTO.filterWoFmeaStatusId"/>
<html:hidden property="workFmeaCommonDTO.filterReqDeptId"/>
<html:hidden property="workFmeaCommonDTO.filterReqById"/>
<html:hidden property="workFmeaCommonDTO.filterEquipId"/>
<html:hidden property="workFmeaCommonDTO.filterReviewById"/>
<html:hidden property="workFmeaCommonDTO.filterFmeaPriorityId"/>
<html:hidden property="workFmeaCommonDTO.filterFmeaWoTypeId"/>
<html:hidden property="workFmeaCommonDTO.filterIsCalibId"/>
<html:hidden property="workFmeaCommonDTO.filterIsQualId"/>
<html:hidden property="workFmeaCommonDTO.filterPlantId"/>
<html:hidden property="workFmeaCommonDTO.wkorId"/>
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
							<html:text property="workFmeaCommonDTO.filterReqStartDate" tabindex="10" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="workFmeaCommonDTO.filterReqEndDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 상태  -->
				<div class="field">
					<label><bean:message key="LABEL.status"/></label>
					<div class="input_box">
						<html:text property="workFmeaCommonDTO.filterWoFmeaStatusDesc" tabindex="30" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 의뢰부서  -->
				<div class="field">
					<label><bean:message key="LABEL.requestDept"/></label>
					<div class="input_box">
						<html:text property="workFmeaCommonDTO.filterReqDeptDesc" tabindex="40"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 의뢰자  -->
				<div class="field">
					<label><bean:message key="LABEL.requestBy"/></label>
					<div class="input_box">
						<html:text property="workFmeaCommonDTO.filterReqByDesc" tabindex="50"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 설비  -->
				<div class="field">
					<label><bean:message key="LABEL.equipment"/></label>
					<div class="input_box">
						<html:text property="workFmeaCommonDTO.filterEquipDesc" tabindex="60"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 접수번호  -->
				<div class="field">
					<label><bean:message key="LABEL.receiptNo"/></label>
					<div class="input_box">
						<html:text property="workFmeaCommonDTO.filterWoFmeaNo" tabindex="70"/>
					</div>
				</div>
				<!-- 검토일자 -->
				<div class="field">
					<label><bean:message key="LABEL.viewDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="workFmeaCommonDTO.filterReviewStartDate" tabindex="80" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="workFmeaCommonDTO.filterReviewEndDate" tabindex="90" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 검토자  -->
				<div class="field">
					<label><bean:message key="LABEL.viewBy"/></label>
					<div class="input_box">
						<html:text property="workFmeaCommonDTO.filterReviewByDesc" tabindex="100"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 영향성평가  -->
				<div class="field">
					<label><bean:message key="LABEL.fmeaPriority"/></label>
					<div class="input_box">
						<html:text property="workFmeaCommonDTO.filterFmeaPriorityDesc" tabindex="110"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 작업구분  -->
				<div class="field">
					<label><bean:message key="LABEL.fmeaWoType"/></label>
					<div class="input_box">
						<html:text property="workFmeaCommonDTO.filterFmeaWoTypeDesc" tabindex="120"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- Calibration 여부  -->
				<div class="field">
					<label><bean:message key="LABEL.calibration1"/></label>
					<div class="input_box">
						<html:text property="workFmeaCommonDTO.filterIsCalibDesc" tabindex="130"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- Qualification 여부  -->
				<div class="field">
					<label><bean:message key="LABEL.qualification"/></label>
					<div class="input_box">
						<html:text property="workFmeaCommonDTO.filterIsQualDesc" tabindex="140"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
							<html:text property="workFmeaCommonDTO.filterPlantDesc" tabindex="150" />
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
		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>
<%--===========================================================================
결재이력 List
author  sy.yang
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.pers.appr.action.PersApprHistListAction" %>
<%@ page import="dream.note.daily.action.MaWoDailyDetailAction" %>
<%@ page import="dream.invt.list.action.InvtPrcDetailAction" %>
<%@ page import="dream.invt.list.action.InvtDetailAction" %>
<%@ page import="dream.req.work.action.ReqWorkDetailAction"%>
<%@ page import="dream.part.pur.buy.action.MaPtBuyReqHdrDetailAction" %>
<%@ page import="dream.part.adj.stktake.action.PartAdjStkTakeDetailAction" %>
<%@ page import="dream.work.list.action.MaWoResultMstrDetailAction"%>
<%@ page import="dream.work.fmea.list.action.WorkFmeaDetailAction" %>
<%@ page import="dream.part.iss.emg.req.action.PartIssEmgReqDetailAction"%>
<%@ page import="dream.work.list.action.WoPlanDetailAction" %>
<%@ page import="dream.work.planappr.action.WorkPlanApprDetailAction" %>
<%@ page import="dream.work.cal.pminsappr.action.WorkCalPmInsApprDetailAction" %>
<%@ page import="dream.asset.list.action.MaEqMstrDetailAction"%>
<%@ page import="dream.work.pmi.list.action.WorkPmiDetailAction"%>
<%@ page import="dream.work.pm.list.action.MaPmMstrDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 프로그램 가이드 -->
<title><bean:message key='MENU.APPRHIST'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var detailPage, apprBy, reqBy;		// 상세페이지명

//그리드명
var myGrid;

function loadPage() 
{
	// 날짜 자동완성 (1달전~오늘)
	persApprHistListForm.elements['persApprHistCommonDTO.filterStartDate'].value = getMinusMonth2(new Date(), -1); 
	persApprHistListForm.elements['persApprHistCommonDTO.filterEndDate'].value   = getToday();
	
	// 결재구분
    acSysDesc("persApprHistCommonDTO.filterApprTypeDesc","persApprHistCommonDTO.filterApprTypeId","APPR_TYPE");
	
	// 진행상태
    acSysDesc("persApprHistCommonDTO.filterApprusrStatusDesc","persApprHistCommonDTO.filterApprusrStatusId","APPRUSR_STATUS");

    reqBy = new autoC({"persApprHistCommonDTO.reqByName":"emp_name"});
    reqBy.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    reqBy.setTable("TAEMP");
    reqBy.setAcResultMap({
        "ersApprHistCommonDTO.reqBy":"emp_id"
    });
    reqBy.init();
    
    //------------------------------------------------------------------------------------
    
    apprBy = new autoC({"persApprHistCommonDTO.apprByName":"emp_name"});
    apprBy.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    apprBy.setTable("TAEMP");
    apprBy.setAcResultMap({
        "persApprHistCommonDTO.apprBy":"emp_id"
    });
    apprBy.init();
    
    
    if(currentPageId == "persApprovedHistList") //결재한 이력
    {
    	persApprHistListForm.elements['persApprHistCommonDTO.apprBy'].value = loginUser.empId;
    	persApprHistListForm.elements['persApprHistCommonDTO.apprByName'].value = loginUser.empName;
    }
    else if(currentPageId == "persReqHistList") //결재요청이력
    {
    	persApprHistListForm.elements['persApprHistCommonDTO.reqBy'].value = loginUser.empId;
    	persApprHistListForm.elements['persApprHistCommonDTO.reqByName'].value = loginUser.empName;
    }

    
	initGrid();
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
    	persApprHistListForm.elements['persApprHistCommonDTO.apprListId'].value
    	return sortColumn("persApprHistList", this, persApprHistListForm, "apprListId", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
* 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
*/
function findGridList(sheetAction)
{
  var url = contextPath + "/persApprHistList.do";
  persApprHistListForm.elements['strutsAction'].value = '<%=PersApprHistListAction.LIST_FIND%>';
  doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(persApprHistListForm), "apprListId","Y");

}

/** 
* 수정된 그리드 1건을 다시 조회한다.
*/
function findGridRow(_apprListId)
{
	persApprHistListForm.elements['persApprHistCommonDTO.apprListId'].value = _apprListId;
	findGridList('ReloadRow');
	persApprHistListForm.elements['persApprHistCommonDTO.apprListId'].value = "";
}

function goSearch()
{
	
	if(checkValidation()) return;
	
	persApprHistListForm.elements['persApprHistCommonDTO.apprListId'].value = "";	// 검색시 Tab 이동Key Clear
	findGridList('Search');
}

/**
* Tab 이동시 호출
*/
<%-- function goTabPage(pageId)
{
	tabValidationCheck(myGrid, pageId, "goTabPageAction");  
}

function goTabPageAction(pageId, selectedId)
{
	persApprHistListForm.elements['persApprHistCommonDTO.apprListId'].value =  getValueById(myGrid, selectedId,'apprListId');  
	goCommonTabPage(persApprHistListForm, <%= PersApprHistDetailAction.DETAIL_INIT %>, pageId);
}
 --%>
/**
* 상세 열기
*/
function goOpen()
{
	var selectedId=myGrid.getSelectedRowId();
	if(typeof selectedId == "undefined" || selectedId == null) return
	
	var objectId = getValueById(myGrid, selectedId,'objectId');
	var apprType = getValueById(myGrid, selectedId,'apprType');
	
	console.log("Appr Type : "+apprType)

	goClose(detailPage);	// 상세목록페이지 닫기
	if(apprType == "WODAY") //일일작업일지확인
	{
		detailPage = "maWoDailyDetail";
		persApprHistListForm.elements['maWoDailyCommonDTO.woDayListId'].value = objectId;
		goCommonTabPage(persApprHistListForm, <%= MaWoDailyDetailAction.WO_DAILY_DETAIL_INIT %>, detailPage);
		// 일일작업일지확인 상세페이지 호출 
	}
	if(apprType == "INVTPRC") //투자진행확인
	{
		detailPage = "invtPrcDetail";
		persApprHistListForm.elements['invtCommonDTO.invtphaseId'].value = objectId;
		goCommonTabPage(persApprHistListForm, <%= InvtPrcDetailAction.INVT_PRC_DETAIL_INIT %>, detailPage);
	}
	if(apprType == "INVTLIST") //투자목록확인
	{
		detailPage = "invtDetail";
		persApprHistListForm.elements['invtCommonDTO.invtlistId'].value = objectId;
		goCommonTabPage(persApprHistListForm, <%= InvtDetailAction.INVT_DETAIL_INIT %>, detailPage);
	}	
	if(apprType == "REQWORK") //작업요청확인
	{
		detailPage = "reqWorkDetail";
		persApprHistListForm.elements['reqWorkCommonDTO.woReqId'].value = objectId;
		goCommonTabPage(persApprHistListForm, <%= ReqWorkDetailAction.DETAIL_INIT %>, detailPage);
	}
	if(apprType == "REQINVTWORK") //투자요청확인
	{
		detailPage = "reqInvWorkDetail";
		persApprHistListForm.elements['reqWorkCommonDTO.woReqId'].value = objectId;
		goCommonTabPage(persApprHistListForm, <%= ReqWorkDetailAction.DETAIL_INIT %>, detailPage);
	}
	if(apprType == "PTBUYREQ") //구매신청확인
	{
		detailPage = "maPtBuyReqHdrDetail";
		persApprHistListForm.elements['maPtBuyReqHdrCommonDTO.ptPrListId'].value = objectId;
		goCommonTabPage(persApprHistListForm, <%= MaPtBuyReqHdrDetailAction.BUY_DETAIL_INIT %>, detailPage);
	}
	if(apprType == "PTSTKTAKE") //부품실사확인
	{
		detailPage = "partAdjStkTakeDetail";
		persApprHistListForm.elements['partAdjStkTakeCommonDTO.ptStkTakeListId'].value = objectId;
		goCommonTabPage(persApprHistListForm, <%= PartAdjStkTakeDetailAction.BUY_DETAIL_INIT %>, detailPage);
	}
	if(apprType == "WORKORDER") //작업오더확인
	{
		findWkOrPage("goOpen");
	}
	if(apprType == "WORKFMEA") //고장영향성평가확인
	{
		detailPage = "workFmeaDetail";
		persApprHistListForm.elements['workFmeaCommonDTO.woFmeaId'].value = objectId;
		goCommonTabPage(persApprHistListForm, <%= WorkFmeaDetailAction.DETAIL_INIT %>, detailPage);
	}
	if(apprType == "PTISSREQ") //출고요청확인
	{
		detailPage = "partIssEmgReqDetail";
		persApprHistListForm.elements['partIssEmgReqCommonDTO.ptEmgIssReqId'].value = objectId;
		goCommonTabPage(persApprHistListForm, <%= PartIssEmgReqDetailAction.DETAIL_INIT %>, detailPage);
	}
	if(apprType == "WOPLAN") //작업계획확인
	{
		detailPage = "woPlanDetail";
		persApprHistListForm.elements['woPlanCommonDTO.wkOrId'].value = objectId;
		goCommonTabPage(persApprHistListForm, <%= WoPlanDetailAction.WO_PLAN_DETAIL_INIT %>, detailPage);
	}
	if(apprType == "PMINSSCHED") //예방점검계획승인
	{
		detailPage = "workCalPmInsApprDetail";
		persApprHistListForm.elements['workCalPmInsApprCommonDTO.pmInsSchedApprId'].value = objectId;
		goCommonTabPage(persApprHistListForm, <%= WorkCalPmInsApprDetailAction.DETAIL_INIT %>, detailPage);
	}
	if(apprType == "WOPLANAPPR") //작업계획승인
	{
		detailPage = "workPlanApprDetail";
		persApprHistListForm.elements['workPlanApprCommonDTO.woPlanApprId'].value = objectId;
		goCommonTabPage(persApprHistListForm, <%= WorkCalPmInsApprDetailAction.DETAIL_INIT %>, detailPage);
	}
	if(apprType == "EQUIPREV") //설비제개정 결재
	{
		detailPage = "maEqMstrDetail";
		persApprHistListForm.elements['maEqMstrCommonDTO.equipId'].value = objectId;
		goCommonTabPage(persApprHistListForm, <%= MaEqMstrDetailAction.EQ_MSTR_DETAIL_INIT %>, detailPage);
	}
	if(apprType == "EQTLREV") //계측기제개정 결재
	{
		detailPage = "maEqToolMstrDetail";
		persApprHistListForm.elements['maEqMstrCommonDTO.equipId'].value = objectId;
		goCommonTabPage(persApprHistListForm, <%= MaEqMstrDetailAction.EQ_MSTR_DETAIL_INIT %>, detailPage);
	}
	if(apprType == "PMINSRSLT") // 점검실시 결재
	{
		detailPage = "workPmiDetail";
		persApprHistListForm.elements['workPmiCommonDTO.pminslistId'].value = objectId;
		goCommonTabPage(persApprHistListForm, <%= WorkPmiDetailAction.WORK_PMI_DETAIL_INIT %>, detailPage);
	}
	if(apprType == "PMINSREV") //점검주기설정 결재
	{
		detailPage = "workPmListRInsDetail";
		persApprHistListForm.elements['maPmMstrCommonDTO.pmId'].value = objectId;
		goCommonTabPage(persApprHistListForm, <%= MaPmMstrDetailAction.PM_MSTR_DETAIL_INIT %>, detailPage);
	}
	
  //goTabPage('persApprHistDetail');
}

/**
* 상세열기 버튼
*/
function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
  	if(typeof selectedId == "undefined" || selectedId == null) return
  
	var objectId = getValueById(myGrid, selectedId,'objectId');
	var apprType = getValueById(myGrid, selectedId,'apprType');

	
	if(apprType == "WODAY") //일일작업일지확인
	{
		detailPage = "maWoDailyDetail";
		persApprHistListForm.elements['maWoDailyCommonDTO.woDayListId'].value = objectId;
		persApprHistListForm.elements['strutsAction'].value = '<%=MaWoDailyDetailAction.WO_DAILY_DETAIL_INIT%>';
		openQuickTabPage(FormQueryString(persApprHistListForm), detailPage); 
	}
	if(apprType == "INVTPRC") //투자진행확인
	{
		detailPage = "invtPrcDetail";
		persApprHistListForm.elements['invtCommonDTO.invtphaseId'].value = objectId;
		persApprHistListForm.elements['strutsAction'].value = '<%=InvtPrcDetailAction.INVT_PRC_DETAIL_INIT%>';
		openQuickTabPage(FormQueryString(persApprHistListForm), detailPage); 
	}
	if(apprType == "INVTLIST") //투자목록확인
	{
		detailPage = "invtDetail";
		persApprHistListForm.elements['invtCommonDTO.invtlistId'].value = objectId;
		persApprHistListForm.elements['strutsAction'].value = '<%=InvtDetailAction.INVT_DETAIL_INIT%>';
		openQuickTabPage(FormQueryString(persApprHistListForm), detailPage); 
	}	
	if(apprType == "REQWORK") //작업요청확인
	{
		detailPage = "reqWorkDetail";
		persApprHistListForm.elements['reqWorkCommonDTO.woReqId'].value = objectId;
		persApprHistListForm.elements['strutsAction'].value = '<%=ReqWorkDetailAction.DETAIL_INIT%>';
		openQuickTabPage(FormQueryString(persApprHistListForm), detailPage); 
	}
	if(apprType == "REQINVTWORK") //투자요청확인
	{
		detailPage = "reqInvWorkDetail";
		persApprHistListForm.elements['reqWorkCommonDTO.woReqId'].value = objectId;
		persApprHistListForm.elements['strutsAction'].value = '<%=ReqWorkDetailAction.DETAIL_INIT%>';
		openQuickTabPage(FormQueryString(persApprHistListForm), detailPage); 
	}
	if(apprType == "PTBUYREQ") //구매신청확인
	{
		detailPage = "maPtBuyReqHdrDetail";
		persApprHistListForm.elements['maPtBuyReqHdrCommonDTO.ptPrListId'].value = objectId;
		persApprHistListForm.elements['strutsAction'].value = '<%=MaPtBuyReqHdrDetailAction.BUY_DETAIL_INIT%>';
		openQuickTabPage(FormQueryString(persApprHistListForm), detailPage); 
	}
	if(apprType == "PTSTKTAKE") //부품실사확인
	{
		detailPage = "partAdjStkTakeDetail";
		persApprHistListForm.elements['partAdjStkTakeCommonDTO.ptStkTakeListId'].value = objectId;
		persApprHistListForm.elements['strutsAction'].value = '<%=PartAdjStkTakeDetailAction.BUY_DETAIL_INIT%>';
		openQuickTabPage(FormQueryString(persApprHistListForm), detailPage); 
	}
	if(apprType == "WORKORDER") //작업오더확인
	{
		findWkOrPage("goOpenAction");
	}
	if(apprType == "WORKFMEA") //고장영향성평가확인
	{
		detailPage = "workFmeaDetail";
		persApprHistListForm.elements['workFmeaCommonDTO.woFmeaId'].value = objectId;
		persApprHistListForm.elements['strutsAction'].value = '<%=WorkFmeaDetailAction.DETAIL_INIT%>';
		openQuickTabPage(FormQueryString(persApprHistListForm), detailPage); 
	}
	if(apprType == "PTISSREQ") //출고요청확인
	{
		detailPage = "partIssEmgReqDetail";
		persApprHistListForm.elements['partIssEmgReqCommonDTO.ptEmgIssReqId'].value = objectId;
		persApprHistListForm.elements['strutsAction'].value = '<%=PartIssEmgReqDetailAction.DETAIL_INIT%>';
		openQuickTabPage(FormQueryString(persApprHistListForm), detailPage); 
	}
	if(apprType == "WOPLAN") //작업계획확인
	{
		detailPage = "woPlanDetail";
		persApprHistListForm.elements['woPlanCommonDTO.wkOrId'].value = objectId;
		persApprHistListForm.elements['strutsAction'].value = '<%=WoPlanDetailAction.WO_PLAN_DETAIL_INIT%>';
		openQuickTabPage(FormQueryString(persApprHistListForm), detailPage); 
	}
	if(apprType == "PMINSSCHED") //예방점검계획승인
	{
		detailPage = "workCalPmInsApprDetail";
		persApprHistListForm.elements['workCalPmInsApprCommonDTO.pmInsSchedApprId'].value = objectId;
		persApprHistListForm.elements['strutsAction'].value = '<%=WorkCalPmInsApprDetailAction.DETAIL_INIT%>';
		openQuickTabPage(FormQueryString(persApprHistListForm), detailPage); 
	}
	if(apprType == "WOPLANAPPR") //작업계획승인
	{
		detailPage = "workPlanApprDetail";
		persApprHistListForm.elements['workPlanApprCommonDTO.woPlanApprId'].value = objectId;
		persApprHistListForm.elements['strutsAction'].value = '<%=WorkCalPmInsApprDetailAction.DETAIL_INIT%>';
		openQuickTabPage(FormQueryString(persApprHistListForm), detailPage); 
	}
	if(apprType == "EQUIPREV") //설비제개정 결재
	{
		detailPage = "maEqMstrDetail";
		persApprHistListForm.elements['maEqMstrCommonDTO.equipId'].value = objectId;
		persApprHistListForm.elements['strutsAction'].value = '<%=MaEqMstrDetailAction.EQ_MSTR_DETAIL_INIT%>';
		openQuickTabPage(FormQueryString(persApprHistListForm), detailPage); 
	}
	if(apprType == "EQTLREV") //계측기제개정 결재
	{
		detailPage = "maEqToolMstrDetail";
		persApprHistListForm.elements['maEqMstrCommonDTO.equipId'].value = objectId;
		persApprHistListForm.elements['strutsAction'].value = '<%=MaEqMstrDetailAction.EQ_MSTR_DETAIL_INIT%>';
		openQuickTabPage(FormQueryString(persApprHistListForm), detailPage); 
	}
	if(apprType == "PMINSRSLT") //점검실시 결재
	{
		detailPage = "workPmiDetail";
		persApprHistListForm.elements['workPmiCommonDTO.pminslistId'].value = objectId;
		persApprHistListForm.elements['strutsAction'].value = '<%=WorkPmiDetailAction.WORK_PMI_DETAIL_INIT%>';
		openQuickTabPage(FormQueryString(persApprHistListForm), detailPage); 
	}
	if(apprType == "PMINSREV") //점검주기설정 결재
	{
		detailPage = "workPmListRInsDetail";
		persApprHistListForm.elements['maPmMstrCommonDTO.pmId'].value = objectId;
		persApprHistListForm.elements['strutsAction'].value = '<%=MaPmMstrDetailAction.PM_MSTR_DETAIL_INIT%>';
		openQuickTabPage(FormQueryString(persApprHistListForm), detailPage); 
	}
}

/**
 * 작업오더 페이지 찾기
 */
function findWkOrPage(from){
	var url = contextPath + "/maWoResultMstrDetail.do";
	persApprHistListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value = getValueById(myGrid, myGrid.getSelectedRowId(),'objectId');
	
	var param = "&strutsAction=" + '<%=MaWoResultMstrDetailAction.WO_RESULT_FIND_PAGE%>'
	+  "&" + FormQueryString(persApprHistListForm);
	
	if(from=="goOpen") {
		XMLHttpPostVal(url, param, 'afterFindWkOrPage');
	}
	else if(from=="goOpenAction") {
		XMLHttpPostVal(url, param, 'afterFindWkOrPageB');
	}
}
var wkOrPage;
function afterFindWkOrPage(ajaxXmlDoc){
	wkOrPage = "";
	wkOrPage = parseXmlDoc(ajaxXmlDoc, 'DESC')[0];
	if(wkOrPage == ""){
		wkOrPage = "maWoResultMstrDetail"
	}
	goCommonTabPage(persApprHistListForm, <%= MaWoResultMstrDetailAction.WO_RESULT_DETAIL_INIT %>, wkOrPage);
	detailPage = wkOrPage;
}
function afterFindWkOrPageB(ajaxXmlDoc){
	wkOrPage = "";
	wkOrPage = parseXmlDoc(ajaxXmlDoc, 'DESC')[0];
	if(wkOrPage == ""){
		wkOrPage = "maWoResultMstrDetail"
	}
	openQuickTabPage(FormQueryString(persApprHistListForm), wkOrPage); 
	detailPage = wkOrPage;
}

/* /**
* 생성

function goCreate()
{
  createValidationCheck(myGrid, "persApprHistDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	persApprHistListForm.elements['persApprHistCommonDTO.apprListId'].value = "";
  goCommonTabPage(persApprHistListForm, '', pageId);
} */

<%-- 
/**
* 삭제
*/
function goDelete()
{
  var delArray = getDeletRows(myGrid, 'isDelCheck', 'apprListId'); //Grid, check box column seq, pk column seq
  if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

  persApprHistListForm.strutsAction.value = '<%=PersApprHistListAction.LIST_DELETE%>';
  var url = contextPath + "/persApprHistList.do";
  
  $.post(url,FormQueryString(persApprHistListForm)+delArray , function(_data){
      afterDelete();
  });
}	

function afterDelete()
{
  goClose('persApprHistDetail');
  //goSearch();
  alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}	 --%>

/**
* Excel Export
*/
function goExcel()
{
	excelAction(myGrid);
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/persApprHistList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maWoDailyCommonDTO.woDayListId"/> 	<!--  일일작업확인 -->
<html:hidden property="invtCommonDTO.invtphaseId"/> 		<!--  투자진행 -->
<html:hidden property="invtCommonDTO.invtlistId"/> 			<!--  투자목록 -->
<html:hidden property="reqWorkCommonDTO.woReqId"/> 			<!--  작업요청 -->
<html:hidden property="maPtBuyReqHdrCommonDTO.ptPrListId"/> 		<!--  구매신청 -->
<html:hidden property="partAdjStkTakeCommonDTO.ptStkTakeListId"/> 	<!--  부품실사 -->
<html:hidden property="maWoResultMstrCommonDTO.wkOrId" /> 	<!--  작업오더 -->
<html:hidden property="workFmeaCommonDTO.woFmeaId"/> 		<!--  고장영향성평가 -->
<html:hidden property="partIssEmgReqCommonDTO.ptEmgIssReqId"/> 		<!--  부품출고요청 -->
<html:hidden property="woPlanCommonDTO.wkOrId"/> 			<!--  작업계획 -->
<html:hidden property="workCalPmInsApprCommonDTO.pmInsSchedApprId"/>	<!--  예방점검계획승인 -->
<html:hidden property="workPlanApprCommonDTO.woPlanApprId"/>	<!--  작업계획승인 -->
<html:hidden property="maEqMstrCommonDTO.equipId" />	<!-- 설비제개정 결재 -->
<html:hidden property="workPmiCommonDTO.pminslistId" />	<!-- 점검실시 결재 -->
<html:hidden property="maPmMstrCommonDTO.pmId" />	<!-- 점검주기설정 결재 -->
<html:hidden property="persApprHistCommonDTO.apprListId"/><!-- Key -->
<html:hidden property="persApprHistCommonDTO.filterApprTypeId"/>
<html:hidden property="persApprHistCommonDTO.filterApprusrStatusId"/>
<html:hidden property="persApprHistCommonDTO.apprBy"/>
<html:hidden property="persApprHistCommonDTO.reqBy"/>
 
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
				<!-- 요청일자 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.appReqDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="persApprHistCommonDTO.filterStartDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="persApprHistCommonDTO.filterEndDate" tabindex="30" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 결재구분 -->
                <div class="field">
                    <label><bean:message key="LABEL.apprusrAction"/></label>
                    <div class="input_box">
                        <html:text property="persApprHistCommonDTO.filterApprTypeDesc" tabindex="40" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
				<!-- 진행상태 -->
                <div class="field">
                    <label><bean:message key="LABEL.proStatus"/></label>
                    <div class="input_box">
                        <html:text property="persApprHistCommonDTO.filterApprusrStatusDesc" tabindex="50" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 요청자 -->
                <div class="field">
                    <label><bean:message key="LABEL.appReqBy"/></label>
                    <div class="input_box">
                        <html:text property="persApprHistCommonDTO.reqByName" tabindex="40" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 결재자 -->
                <div class="field">
                    <label><bean:message key="LABEL.apprByName"/></label>
                    <div class="input_box">
                        <html:text property="persApprHistCommonDTO.apprByName" tabindex="40" />
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
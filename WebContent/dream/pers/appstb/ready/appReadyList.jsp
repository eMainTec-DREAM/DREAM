<%--===========================================================================
목록
author  jung7126
version $Id: appReadyList.jsp,v 1.1 2015/12/03 01:45:27 jung7126 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.pers.appstb.ready.action.AppReadyListAction" %>
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
<%@ page import="dream.work.cal.pminsappr.action.WorkCalPmInsApprDetailAction" %>
<%@ page import="dream.work.planappr.action.WorkPlanApprDetailAction" %>
<%@ page import="dream.asset.list.action.MaEqMstrDetailAction"%>
<%@ page import="dream.work.pmi.list.action.WorkPmiDetailAction"%>
<%@ page import="dream.work.pm.list.action.MaPmMstrDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 결재대기 -->
<title><bean:message key='MENU.APPREADY'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var detailPage;
function loadPage() 
{
	initGrid();
	
}

var myGrid;

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	appReadyListForm.elements['appReadyCommonDTO.apprusrId'].value = "";
    	return sortColumn("appReadyList", this, appReadyListForm, "apprusrId", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
	
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	findGridList('Search', '<%=AppReadyListAction.APP_PRC_FIND %>');   
}

function findGridList(gridAction, strutsAction)
{
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	//if (appReadyListForm.elements['appReqCommonDTO.apprlistId'].value == '') return;
	
	var form = document.appReadyListForm;	
	form.strutsAction.value = '<%=AppReadyListAction.APP_PRC_FIND %>';

    var url = contextPath + "/appReadyList.do";
    
    doGridAction(gridAction, myGrid, "gridbox", url, FormQueryString(appReadyListForm), "apprlistId", "Y");
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
	appReadyListForm.elements['appReqCommonDTO.apprusrId'].value = getValueById(myGrid, selectedId,'apprusrId'); //pggridcol

	goCommonTabPage(appReadyListForm, <%= AppReadyDetailAction.APP_PRC_INIT %>, "appReadyDetail");
}  --%>

/**
 * 상세열기
 */
function goOpen()
{
	var selectedId=myGrid.getSelectedRowId();
    if(typeof selectedId == "undefined" || selectedId == null) return
    
	var objectId = getValueById(myGrid, selectedId,'objectId');
	var apprType = getValueById(myGrid, selectedId,'apprType');

	goClose(detailPage);
	if(apprType == "WODAY") //일일작업일지확인
	{
		detailPage = "maWoDailyDetail";
		appReadyListForm.elements['maWoDailyCommonDTO.woDayListId'].value = objectId;
		goCommonTabPage(appReadyListForm, <%= MaWoDailyDetailAction.WO_DAILY_DETAIL_INIT %>, detailPage);
	}
	if(apprType == "INVTPRC") //투자진행확인
	{
		detailPage = "invtPrcDetail";
		appReadyListForm.elements['invtCommonDTO.invtphaseId'].value = objectId;
		goCommonTabPage(appReadyListForm, <%= InvtPrcDetailAction.INVT_PRC_DETAIL_INIT %>, detailPage);
	}
	if(apprType == "INVTLIST") //투자목록확인
	{
		detailPage = "invtDetail";
		appReadyListForm.elements['invtCommonDTO.invtlistId'].value = objectId;
		goCommonTabPage(appReadyListForm, <%= InvtDetailAction.INVT_DETAIL_INIT %>, detailPage);
	}
	if(apprType == "REQWORK") //작업요청확인
	{
		detailPage = "reqWorkDetail";
		appReadyListForm.elements['reqWorkCommonDTO.woReqId'].value = objectId;
		goCommonTabPage(appReadyListForm, <%= ReqWorkDetailAction.DETAIL_INIT %>, detailPage);
	}
	if(apprType == "REQINVTWORK") //투자요청확인
	{
		detailPage = "reqInvWorkDetail";
		appReadyListForm.elements['reqWorkCommonDTO.woReqId'].value = objectId;
		goCommonTabPage(appReadyListForm, <%= ReqWorkDetailAction.DETAIL_INIT %>, detailPage);
	}
	if(apprType == "PTBUYREQ") //구매신청확인
	{
		detailPage = "maPtBuyReqHdrDetail";
		appReadyListForm.elements['maPtBuyReqHdrCommonDTO.ptPrListId'].value = objectId;
		goCommonTabPage(appReadyListForm, <%= MaPtBuyReqHdrDetailAction.BUY_DETAIL_INIT %>, detailPage);
	}
	if(apprType == "PTSTKTAKE") //부품실사확인
	{
		detailPage = "partAdjStkTakeDetail";
		appReadyListForm.elements['partAdjStkTakeCommonDTO.ptStkTakeListId'].value = objectId;
		goCommonTabPage(appReadyListForm, <%= PartAdjStkTakeDetailAction.BUY_DETAIL_INIT %>, detailPage);
	}
	if(apprType == "WORKORDER") //작업오더확인
	{
		findWkOrPage("goOpen");
	}
	if(apprType == "WORKFMEA") //고장영향성평가확인
	{
		detailPage = "workFmeaDetail";
		appReadyListForm.elements['workFmeaCommonDTO.woFmeaId'].value = objectId;
		goCommonTabPage(appReadyListForm, <%= WorkFmeaDetailAction.DETAIL_INIT %>, detailPage);
	}
	if(apprType == "PTISSREQ") //출고요청확인
	{
		detailPage = "partIssEmgReqDetail";
		appReadyListForm.elements['partIssEmgReqCommonDTO.ptEmgIssReqId'].value = objectId;
		goCommonTabPage(appReadyListForm, <%= PartIssEmgReqDetailAction.DETAIL_INIT %>, detailPage);
	}
	if(apprType == "WOPLAN") //작업계획확인
	{
		detailPage = "woPlanDetail";
		appReadyListForm.elements['woPlanCommonDTO.wkOrId'].value = objectId;
		goCommonTabPage(appReadyListForm, <%= WoPlanDetailAction.WO_PLAN_DETAIL_INIT %>, detailPage);
	}
	if(apprType == "PMINSSCHED") //예방점검계획승인
	{
		detailPage = "workCalPmInsApprDetail";
		appReadyListForm.elements['workCalPmInsApprCommonDTO.pmInsSchedApprId'].value = objectId;
		goCommonTabPage(appReadyListForm, <%= WorkCalPmInsApprDetailAction.DETAIL_INIT %>, detailPage);
	}
	if(apprType == "WOPLANAPPR") //작업계획승인
	{
		detailPage = "workPlanApprDetail";
		appReadyListForm.elements['workPlanApprCommonDTO.woPlanApprId'].value = objectId;
		goCommonTabPage(appReadyListForm, <%= WorkPlanApprDetailAction.DETAIL_INIT %>, detailPage);
	}
	if(apprType == "EQUIPREV") //설비제개정 결재
	{
		detailPage = "maEqMstrDetail";
		appReadyListForm.elements['maEqMstrCommonDTO.equipId'].value = objectId;
		goCommonTabPage(appReadyListForm, <%= MaEqMstrDetailAction.EQ_MSTR_DETAIL_INIT %>, detailPage);
	}
	if(apprType == "EQTLREV") //계측기제개정 결재
	{
		detailPage = "maEqToolMstrDetail";
		appReadyListForm.elements['maEqMstrCommonDTO.equipId'].value = objectId;
		goCommonTabPage(appReadyListForm, <%= MaEqMstrDetailAction.EQ_MSTR_DETAIL_INIT %>, detailPage);
	}
	if(apprType == "PMINSRSLT") //점검실시 결재
	{
		detailPage = "workPmiDetail";
		appReadyListForm.elements['workPmiCommonDTO.pminslistId'].value = objectId;
		goCommonTabPage(appReadyListForm, <%= WorkPmiDetailAction.WORK_PMI_DETAIL_INIT %>, detailPage);
	}
	if(apprType == "PMINSREV") //점검주기설정 결재
	{
		detailPage = "workPmListRInsDetail";
		appReadyListForm.elements['maPmMstrCommonDTO.pmId'].value = objectId;
		goCommonTabPage(appReadyListForm, <%= MaPmMstrDetailAction.PM_MSTR_DETAIL_INIT %>, detailPage);
	}
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
		appReadyListForm.elements['maWoDailyCommonDTO.woDayListId'].value = objectId;
		appReadyListForm.elements['strutsAction'].value = '<%=MaWoDailyDetailAction.WO_DAILY_DETAIL_INIT%>';
		openQuickTabPage(FormQueryString(appReadyListForm), detailPage); 
	}
	if(apprType == "INVTPRC") //투자진행확인
	{
		detailPage = "invtPrcDetail";
		appReadyListForm.elements['invtCommonDTO.invtphaseId'].value = objectId;
		appReadyListForm.elements['strutsAction'].value = '<%=InvtPrcDetailAction.INVT_PRC_DETAIL_INIT%>';
		openQuickTabPage(FormQueryString(appReadyListForm), detailPage); 
	}
	if(apprType == "INVTLIST") //투자목록확인
	{
		detailPage = "invtDetail";
		appReadyListForm.elements['invtCommonDTO.invtlistId'].value = objectId;
		appReadyListForm.elements['strutsAction'].value = '<%=InvtDetailAction.INVT_DETAIL_INIT%>';
		openQuickTabPage(FormQueryString(appReadyListForm), detailPage); 
	}
	if(apprType == "REQWORK") //작업요청확인
	{
		detailPage = "reqWorkDetail";
		appReadyListForm.elements['reqWorkCommonDTO.woReqId'].value = objectId;
		appReadyListForm.elements['strutsAction'].value = '<%=ReqWorkDetailAction.DETAIL_INIT%>';
		openQuickTabPage(FormQueryString(appReadyListForm), detailPage); 
	}
	if(apprType == "REQINVTWORK") //투자요청확인
	{
		detailPage = "reqInvWorkDetail";
		appReadyListForm.elements['reqWorkCommonDTO.woReqId'].value = objectId;
		appReadyListForm.elements['strutsAction'].value = '<%=ReqWorkDetailAction.DETAIL_INIT%>';
		openQuickTabPage(FormQueryString(appReadyListForm), detailPage); 
	}
	if(apprType == "PTBUYREQ") //구매신청확인
	{
		detailPage = "maPtBuyReqHdrDetail";
		appReadyListForm.elements['maPtBuyReqHdrCommonDTO.ptPrListId'].value = objectId;
		appReadyListForm.elements['strutsAction'].value = '<%=MaPtBuyReqHdrDetailAction.BUY_DETAIL_INIT%>';
		openQuickTabPage(FormQueryString(appReadyListForm), detailPage); 
	}
	if(apprType == "PTSTKTAKE") //부품실사확인
	{
		detailPage = "partAdjStkTakeDetail";
		appReadyListForm.elements['partAdjStkTakeCommonDTO.ptStkTakeListId'].value = objectId;
		appReadyListForm.elements['strutsAction'].value = '<%=PartAdjStkTakeDetailAction.BUY_DETAIL_INIT%>';
		openQuickTabPage(FormQueryString(appReadyListForm), detailPage); 
	}
	if(apprType == "WORKORDER") //작업오더확인
	{
		findWkOrPage("goOpenAction");
	}
	if(apprType == "WORKFMEA") //고장영향성평가확인
	{
		detailPage = "workFmeaDetail";
		appReadyListForm.elements['workFmeaCommonDTO.woFmeaId'].value = objectId;
		appReadyListForm.elements['strutsAction'].value = '<%=WorkFmeaDetailAction.DETAIL_INIT%>';
		openQuickTabPage(FormQueryString(appReadyListForm), detailPage); 
	}
	if(apprType == "PTISSREQ") //출고요청확인
	{
		detailPage = "partIssEmgReqDetail";
		appReadyListForm.elements['partIssEmgReqCommonDTO.ptEmgIssReqId'].value = objectId;
		appReadyListForm.elements['strutsAction'].value = '<%=PartIssEmgReqDetailAction.DETAIL_INIT%>';
		openQuickTabPage(FormQueryString(appReadyListForm), detailPage); 
	}
	if(apprType == "WOPLAN") //작업계획확인
	{
		detailPage = "woPlanDetail";
		appReadyListForm.elements['woPlanCommonDTO.wkOrId'].value = objectId;
		appReadyListForm.elements['strutsAction'].value = '<%=WoPlanDetailAction.WO_PLAN_DETAIL_INIT%>';
		openQuickTabPage(FormQueryString(appReadyListForm), detailPage); 
	}
	if(apprType == "PMINSSCHED") //예방점검계획승인
	{
		detailPage = "workCalPmInsApprDetail";
		appReadyListForm.elements['workCalPmInsApprCommonDTO.pmInsSchedApprId'].value = objectId;
		appReadyListForm.elements['strutsAction'].value = '<%=WorkCalPmInsApprDetailAction.DETAIL_INIT%>';
		openQuickTabPage(FormQueryString(appReadyListForm), detailPage); 
	}
	if(apprType == "WOPLANAPPR") //작업계획승인
	{
		detailPage = "workPlanApprDetail";
		appReadyListForm.elements['workPlanApprCommonDTO.woPlanApprId'].value = objectId;
		appReadyListForm.elements['strutsAction'].value = '<%=WorkCalPmInsApprDetailAction.DETAIL_INIT%>';
		openQuickTabPage(FormQueryString(appReadyListForm), detailPage); 
	}
	if(apprType == "EQUIPREV") //설비제개정 결재
	{
		detailPage = "maEqMstrDetail";
		appReadyListForm.elements['maEqMstrCommonDTO.equipId'].value = objectId;
		appReadyListForm.elements['strutsAction'].value = '<%=MaEqMstrDetailAction.EQ_MSTR_DETAIL_INIT%>';
		openQuickTabPage(FormQueryString(appReadyListForm), detailPage); 
	}
	if(apprType == "EQTLREV") //계측기제개정 결재
	{
		detailPage = "maEqToolMstrDetail";
		appReadyListForm.elements['maEqMstrCommonDTO.equipId'].value = objectId;
		appReadyListForm.elements['strutsAction'].value = '<%=MaEqMstrDetailAction.EQ_MSTR_DETAIL_INIT%>';
		openQuickTabPage(FormQueryString(appReadyListForm), detailPage); 
	}
	if(apprType == "PMINSRSLT") //점검실시 결재
	{
		detailPage = "workPmiDetail";
		appReadyListForm.elements['workPmiCommonDTO.pminslistId'].value = objectId;
		appReadyListForm.elements['strutsAction'].value = '<%=WorkPmiDetailAction.WORK_PMI_DETAIL_INIT%>';
		openQuickTabPage(FormQueryString(appReadyListForm), detailPage); 
	}
	if(apprType == "PMINSREV") //점검주기설정 결재
	{
		detailPage = "workPmListRInsDetail";
		appReadyListForm.elements['maPmMstrCommonDTO.pmId'].value = objectId;
		appReadyListForm.elements['strutsAction'].value = '<%=MaPmMstrDetailAction.PM_MSTR_DETAIL_INIT%>';
		openQuickTabPage(FormQueryString(appReadyListForm), detailPage); 
	}
} 

/**
 * 작업오더 페이지 찾기
 */
function findWkOrPage(from){
	var url = contextPath + "/maWoResultMstrDetail.do";
	appReadyListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value = getValueById(myGrid, myGrid.getSelectedRowId(),'objectId');
	var param = "&strutsAction=" + '<%=MaWoResultMstrDetailAction.WO_RESULT_FIND_PAGE%>'
	+  "&" + FormQueryString(appReadyListForm);
	
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
	goCommonTabPage(appReadyListForm, <%= MaWoResultMstrDetailAction.WO_RESULT_DETAIL_INIT %>, wkOrPage);
	detailPage = wkOrPage;
}
function afterFindWkOrPageB(ajaxXmlDoc){
	wkOrPage = "";
	wkOrPage = parseXmlDoc(ajaxXmlDoc, 'DESC')[0];
	if(wkOrPage == ""){
		wkOrPage = "maWoResultMstrDetail"
	}
	openQuickTabPage(FormQueryString(appReadyListForm), wkOrPage); 
	detailPage = wkOrPage;
}
/* /**
 * 생성
 
function goCreate()
{
	createValidationCheck(myGrid, "appReadyDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	appReadyListForm.elements['appReqCommonDTO.apprusrId'].value = "";
	goCommonTabPage(appReadyListForm, '', pageId);
} */

/** 
 * 수정된 그리드 1건을 다시 조회한다.

function findGridRow(_keyId)
{
	// 검색 요청번호와 Tab이동시 요청번호가 다르면 상세에서 입력한것이므로 조회 하지 않는다.
	//if (maEqMstrListForm.elements['maEqMstrCommonDTO.equipId'].value != _equipId) return;
	appReadyListForm.elements['appReqCommonDTO.apprusrId'].value = _keyId;
	findGridList('ReloadRow');
	appReadyListForm.elements['appReqCommonDTO.apprusrId'].value = "";
} */

// function goApprline()
// {
// 	//결재선 선택후 삭제 및 재조회
// 	var param = "maAppLineCommonDTO.apprlistId="+appReadyListForm.elements['appReqCommonDTO.apprlistId'].value;
// 	openLayerPopup("maAppLinePopupList", param);  
// }

<%-- function goDelete()
{
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'apprusrId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined") return;
	
	appReadyListForm.strutsAction.value = '<%=AppReadyListAction.APP_PRC_DELETE%>';
	var url = contextPath + "/appReadyList.do";
	$.post(url,FormQueryString(appReadyListForm)+delArray , function(_data){
    	afterDelete();
    });
}

function afterDelete(){
	goClose('appReadyDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
} --%>

function goAppraction()
{

	var delArray = getSelectedRows(myGrid, 'ISDELCHECK', 'apprusrId', 'apprlistId');

	openLayerPopup("appReadyPopup");

}


//반려처리.
function rejectAction(remark)
{
	
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'apprusrId', 'apprlistId');
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	appReadyListForm.elements['appReadyCommonDTO.remark'].value = remark;
	
	appReadyListForm.strutsAction.value = '<%=AppReadyListAction.APP_READY_REJECT%>';
	var url = contextPath + "/appReadyList.do";
	$.post(url,FormQueryString(appReadyListForm)+delArray , function(_data){
    	afterAppraction("REJECT");
    }); 
}

//승인처리
function apprAction(remark)
{
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'apprusrId', 'apprlistId');
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	appReadyListForm.elements['appReadyCommonDTO.remark'].value = remark;
	
	appReadyListForm.strutsAction.value = '<%=AppReadyListAction.APP_READY_ACTION%>';
	var url = contextPath + "/appReadyList.do";
	$.post(url,FormQueryString(appReadyListForm)+delArray , function(_data){
    	afterAppraction("APPROVE");
    }); 
}


//참조확인
function referenceAction(remark)
{
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'apprusrId', 'apprlistId');
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	appReadyListForm.elements['appReadyCommonDTO.remark'].value = remark;
	
	appReadyListForm.strutsAction.value = '<%=AppReadyListAction.APP_READY_REF%>';
	var url = contextPath + "/appReadyList.do";
	$.post(url,FormQueryString(appReadyListForm)+delArray , function(_data){
    	afterAppraction("REFERENCE");
    }); 
}


function afterAppraction(_type)
{
	if(_type == "APPROVE")
	{
		alertMessage1('<bean:message key="MESSAGE.MSG0192"/>'); //결재건만 승인했습니다.
		goClose(detailPage);
	}
	else if(_type == "REJECT")
	{
		alertMessage1('<bean:message key="MESSAGE.MSG0193"/>'); //결재건만 반려했습니다.
		goClose(detailPage);
	}	
	else if(_type == "REFERENCE")
	{
		alertMessage1('<bean:message key="MESSAGE.MSG0194"/>'); //참조건만 확인했습니다.
		goClose(detailPage);
	}
	//재조회
	goSearch();
	
	
}

/**
 * 결재이력 팝업
 */
function goApprhist()
{
	var selectedId=myGrid.getSelectedRowId();
	if(typeof selectedId == "undefined" || selectedId == null) return
	    
	var objectId = getValueById(myGrid, selectedId,'objectId');
	var apprType = getValueById(myGrid, selectedId,'apprType');
		
    var param = "appReqCommonDTO.objectId="+objectId +
    			"&appReqCommonDTO.apprType="+apprType;
    openLayerPopup("maAppPrcList", param);
}

/**
 * 엑셀 다운.
 */
 function goExcel()
 {
 	excelAction(myGrid);
 }
 /**
  *  Audit trail
  */
  function goAudtrailLink()
  {
     var objectId = appReadyListForm.elements['appReadyCommonDTO.apprusrId'].value;
     var fileName = currentPageId;

     if(typeof objectId=="undefined") return;

     goAudTrailList(objectId, fileName);
  }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/appReadyList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maWoDailyCommonDTO.woDayListId"/> <!--  일일작업확인 -->
<html:hidden property="invtCommonDTO.invtphaseId"/> <!--  투자진행 -->
<html:hidden property="invtCommonDTO.invtlistId"/> <!--  투자목록 -->
<html:hidden property="reqWorkCommonDTO.woReqId"/> <!--  작업요청 -->
<html:hidden property="maPtBuyReqHdrCommonDTO.ptPrListId"/> <!--  구매신청 -->
<html:hidden property="partAdjStkTakeCommonDTO.ptStkTakeListId"/> <!--  부품실사 -->
<html:hidden property="maWoResultMstrCommonDTO.wkOrId" /> <!--  작업오더 -->
<html:hidden property="workFmeaCommonDTO.woFmeaId"/> <!--  고장영향성평가 -->
<html:hidden property="partIssEmgReqCommonDTO.ptEmgIssReqId"/> <!--  부품출고요청 -->
<html:hidden property="woPlanCommonDTO.wkOrId"/> <!--  작업계획 -->
<html:hidden property="workCalPmInsApprCommonDTO.pmInsSchedApprId"/> <!--  예방점검계획승인 -->
<html:hidden property="workPlanApprCommonDTO.woPlanApprId"/> <!--  작업계획승인 -->
<html:hidden property="maEqMstrCommonDTO.equipId" />	<!-- 설비제개정 결재 -->
<html:hidden property="workPmiCommonDTO.pminslistId" />	<!-- 점검실시 결재 -->
<html:hidden property="maPmMstrCommonDTO.pmId" />	<!-- 점검주기설정 결재 -->
<html:hidden property="appReadyCommonDTO.apprusrId"/>
<html:hidden property="appReadyCommonDTO.apprlistId"/>
<html:hidden property="appReadyCommonDTO.remark"/>

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
							<html:text property="appReadyCommonDTO.startDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="appReadyCommonDTO.endDate" tabindex="30" />
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
			<!--<div class="stitle_box"><bean:message key="LABEL.Filter"/></div>  -->
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
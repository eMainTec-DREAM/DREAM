<%--===========================================================================
예방작업기준 - 상세(정기점검)
author  jung7126
version $Id: workPmListRInsDetail.jsp,v 1.0 2015/12/04 04:13:54 jung7126 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.work.pm.list.action.MaPmMstrDetailAction"%>
<%@ page import="common.bean.User"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html>
<head>
<!-- 예방작업기준 -->
<title><bean:message key='LABEL.pmNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
var saveStrutsAction;

/** 자동완성 변수 */
var mainMngAc;
var deptAc;
var wkCtrDescAc;
var wrkCalAc;
var lnWrkListAc;
var plantAc;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else   
	{
		goUpdate();
	}
	
	setTitle("maPmMstrDetailDTO.pmNo","maPmMstrDetailDTO.description");
	
	setForUpdate();
	
	checkScheduleType($("input[name='maPmMstrDetailDTO.scheduleType']").val());
	
    mainMngAc = new autoC({"maPmMstrDetailDTO.empName":"emp_name"});
    mainMngAc.setAcDisplay("EMP_NAME");
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setKeyName("maPmMstrDetailDTO.empId");
    mainMngAc.setAcResultMap({
        "maPmMstrDetailDTO.empId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maPmMstrDetailDTO.deptId"
    });
    mainMngAc.init();
    
    deptAc = new autoC({"maPmMstrDetailDTO.deptDesc":"description"});
    deptAc.setAcDisplay("DESCRIPTION");
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setKeyName("maPmMstrDetailDTO.deptId");
    deptAc.setAcResultMap({
        "maPmMstrDetailDTO.deptId":"dept_id"
        ,"maPmMstrDetailDTO.plantId":"plant"
        ,"maPmMstrDetailDTO.plantDesc":"plantDesc"
       });
    deptAc.init();
    
	wkCtrDescAc = new autoC({"maPmMstrDetailDTO.wkCtrDesc":"description"});
	wkCtrDescAc.setAcDisplay("DESCRIPTION");
	wkCtrDescAc.setAcConditionMap({
		"comp_no":loginUser.compNo
	  });
	wkCtrDescAc.setTable("TAWKCTR");
	wkCtrDescAc.setKeyName("maPmMstrDetailDTO.wkCtrId");
	wkCtrDescAc.setAcResultMap({
	    "maPmMstrDetailDTO.wkCtrId":"wkctr_id"
	});
	wkCtrDescAc.init();

	wrkCalAc = new autoC({"maPmMstrDetailDTO.wrkcalListDesc":"description"});
	wrkCalAc.setAcConditionMap({
		"comp_no":loginUser.compNo
	  });
	wrkCalAc.setTable("TAWRKCALLIST");
	wrkCalAc.setKeyName("maPmMstrDetailDTO.wrkcalListId");
	wrkCalAc.setAcResultMap({
	    "maPmMstrDetailDTO.wrkcalListId":"wrkcallist_id"
	});
	wrkCalAc.init();
	
	lnWrkListAc = new autoC({"maPmMstrDetailDTO.lnWrkListDesc":"description"});
	lnWrkListAc.setAcConditionMap({
		"comp_no":loginUser.compNo
	  });
	lnWrkListAc.setTable("TALNWRKLIST");
	lnWrkListAc.setKeyName("maPmMstrDetailDTO.lnWrkListId");
	lnWrkListAc.setAcResultMap({
	    "maPmMstrDetailDTO.lnWrkListId":"lnWrkListId"
	});
	lnWrkListAc.init();

	//공장 
    plantAc = new autoC({"maPmMstrDetailDTO.plantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setKeyName("maPmMstrDetailDTO.plantId");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "maPmMstrDetailDTO.plantId":"plant"
    });
    plantAc.init();
    
    acSysDesc("maPmMstrDetailDTO.isActiveDesc","maPmMstrDetailDTO.isActive","IS_USE",true);
	acSysDesc("maPmMstrDetailDTO.pmCategDesc","maPmMstrDetailDTO.pmCateg","PM_CATEG",true);
	acSysDesc("maPmMstrDetailDTO.scheduleTypeDesc","maPmMstrDetailDTO.scheduleType","SCHEDULE_TYPE",true);
	acSysDesc("maPmMstrDetailDTO.periodTypeDesc","maPmMstrDetailDTO.periodType","PERIOD_TYPE",true);
	
	// 제/개정 화면제어
 	revDisplayCtrl(M$('maPmMstrDetailDTO.revisionStatusId').value,M$('maPmMstrDetailDTO.isLastVersion').value,"PM");
}

function afterDisable()
{
// 	$('.b_pmsched').show();
// 	$('.b_colsetting').show();
// 	$('.b_revisionhistory').show();

// 	if(maPmMstrDetailForm.elements['maPmMstrDetailDTO.isLastVersion'].value == "Y")
// 	{
// 		$('.b_revision').show();	
// 	}
}

function afterEnable()
{
// 	if(M$('maPmMstrDetailDTO.revisionStatusId').value == "W")
// 	{
// 		$('.b_revisionhistory').hide();
// 		$('.b_revision').hide();	
// 	} 
// 	else if(M$('maPmMstrDetailDTO.revisionStatusId').value == "P")
// 	{
// 		$('.b_revision').hide();	
// 	}
	
// 	if(M$('maPmMstrDetailDTO.isLastVersion').value == "Y")
// 	{
// 		$('.b_save').hide();
// 		$('.b_revcompleted').hide();
// 		$('.b_revision').hide();
// 	}
}

function checkScheduleType(scheduleType)
{
	var cycleObj 		= 'maPmMstrDetailDTO.cycle';
	var periodTypeObj	= 'maPmMstrDetailDTO.periodTypeDesc';
	var wrkcalListObj	= 'maPmMstrDetailDTO.wrkcalListDesc';
	var usageObj 		= 'maPmMstrDetailDTO.usage';
	var lnWrkListObj	= 'maPmMstrDetailDTO.lnWrkListDesc';
	
	if(scheduleType == 'T') {
		maPmMstrDetailForm.elements['maPmMstrDetailDTO.lnWrkListId'].value = '';
		maPmMstrDetailForm.elements['maPmMstrDetailDTO.usage'].value = '';
		hideField(usageObj);
		hideField(lnWrkListObj); //근무달력
		showField(cycleObj);
		showField(periodTypeObj);
		showField(wrkcalListObj);
	}
	else if(scheduleType == 'R' || scheduleType == 'U') {
		maPmMstrDetailForm.elements['maPmMstrDetailDTO.periodType'].value = '';
		maPmMstrDetailForm.elements['maPmMstrDetailDTO.cycle'].value = '';
		maPmMstrDetailForm.elements['maPmMstrDetailDTO.wrkcalListId'].value = '';
		showField(usageObj);
		showField(lnWrkListObj);
		hideField(cycleObj);
		hideField(periodTypeObj);
		hideField(wrkcalListObj);
	}
}

function goUpdate()
{
}

function goInput()
{
	sequenceNextVal('SQAPM_ID');
	//시행여부 세팅
	maPmMstrDetailForm.elements['maPmMstrDetailDTO.isActive'].value = 'Y';
	maPmMstrDetailForm.elements['maPmMstrDetailDTO.isActiveDesc'].value = 'Y';
	//공장셋팅
	maPmMstrDetailForm.elements['maPmMstrDetailDTO.plantId'].value = "<%=user.getPlant()%>";
	maPmMstrDetailForm.elements['maPmMstrDetailDTO.plantDesc'].value = "<%=user.getPlantDesc()%>";
	
	//며칠전 일정생성 7 세팅
	maPmMstrDetailForm.elements['maPmMstrDetailDTO.woResBef'].value = '7';
	//작업종류
	var selectedWoType = maPmMstrDetailForm.elements['maPmMstrCommonDTO.selectedWoType'].value;
	maPmMstrDetailForm.elements['maPmMstrDetailDTO.woTypeDesc'].value = selectedWoType;
	maPmMstrDetailForm.elements['maPmMstrDetailDTO.woType'].value = selectedWoType;
	valSysDir('maPmMstrDetailDTO.woType', 'maPmMstrDetailDTO.woTypeDesc', 'WO_TYPE', true);
	
	//작업형태
	var selectedPmType = maPmMstrDetailForm.elements['maPmMstrCommonDTO.selectedPmType'].value;
	maPmMstrDetailForm.elements['maPmMstrDetailDTO.pmTypeDesc'].value = selectedPmType;
	maPmMstrDetailForm.elements['maPmMstrDetailDTO.pmType'].value = selectedPmType;
	valSysDir('maPmMstrDetailDTO.pmType', 'maPmMstrDetailDTO.pmTypeDesc', selectedWoType+'_TYPE', true);
	//부서정보 세팅
	maPmMstrDetailForm.elements['maPmMstrDetailDTO.deptId'].value = "<%=user.getDeptId()%>";
	maPmMstrDetailForm.elements['maPmMstrDetailDTO.deptDesc'].value = "<%=user.getDeptDesc()%>";
		
	//가동량/시간 = T - 시간
	maPmMstrDetailForm.elements['maPmMstrDetailDTO.scheduleTypeDesc'].value = "T"; 
	maPmMstrDetailForm.elements['maPmMstrDetailDTO.scheduleType'].value = "T"; 
	valSysDir('maPmMstrDetailDTO.scheduleType', 'maPmMstrDetailDTO.scheduleTypeDesc', 'SCHEDULE_TYPE', true);
	//1일 작업횟수 = 1
	maPmMstrDetailForm.elements['maPmMstrDetailDTO.workNumber'].value = 1;
	//reversion여부
	maPmMstrDetailForm.elements['maPmMstrDetailDTO.isLastVersion'].value = 'Y';
}

function setSequenceVal(sequenceVal)
{
	//TAPMLST 테이블의 다음 pm_no 번호를 가져온다.
	maPmMstrDetailForm.elements['maPmMstrDetailDTO.pmId'].value = sequenceVal;
	maPmMstrDetailForm.elements['maPmMstrDetailDTO.pmNo'].value = sequenceVal;
	maPmMstrDetailForm.elements['maPmMstrCommonDTO.pmId'].value = sequenceVal;

}

function goOpen(pageId)
{
	goTabPage(pageId);
}

function goTabPage(pageId)
{
	var form = document.maPmMstrDetailForm;
	maPmMstrDetailForm.elements['maPmMstrCommonDTO.pmId'].value  = maPmMstrDetailForm.elements['maPmMstrDetailDTO.pmId'].value ;
	
	if(pageId == "maAppPrcList" || pageId == "appPrcList") {
		maPmMstrDetailForm.elements['appReqCommonDTO.objectId'].value = maPmMstrDetailForm.elements['maPmMstrDetailDTO.pmId'].value;
		maPmMstrDetailForm.elements['appReqCommonDTO.apprType'].value = "PMINSREV";
		goCommonTabPage(form, '' , pageId);
	}
	else
	goCommonTabPage(form, '' , pageId);
    
}

/**
 * 저장
 */ 
function goSave()
{
	if(checkValidation()) return;

	//strutsAction 셋팅.
	if(ckCreate(currentPageId)) {
		maPmMstrDetailForm.elements['maPmMstrDetailDTO.creDate'].value = getNowDateTime(true);
		maPmMstrDetailForm.elements['maPmMstrDetailDTO.updDate'].value = getNowDateTime(true);
	
		maPmMstrDetailForm.strutsAction.value = '<%=MaPmMstrDetailAction.PM_MSTR_DETAIL_INPUT%>';
	}
	else {
		maPmMstrDetailForm.elements['maPmMstrDetailDTO.updDate'].value = getNowDateTime(true);
		
		maPmMstrDetailForm.strutsAction.value = '<%=MaPmMstrDetailAction.PM_MSTR_DETAIL_UPDATE%>';
	}
	
	var actionUrl = contextPath + "/maPmMstrDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maPmMstrDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	maPmMstrDetailForm.elements['maPmMstrCommonDTO.pmId'].value = maPmMstrDetailForm.elements['maPmMstrDetailDTO.pmId'].value;

	//조회후 선택!
	if (parent.findGridRow)	parent.findGridRow(maPmMstrDetailForm.elements['maPmMstrCommonDTO.pmId'].value);
	
	getTopPage().afterSaveAll(currentPageId);
 }
 
 /**
 *작업일정 보기 
 */
 function goPmsched(){
	var pmNo = maPmMstrDetailForm.elements['maPmMstrDetailDTO.pmNo'].value;
		
	var url;
	var popWidth = 1010;
	var popHeight = 640;
    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
	
	var param;

	if(maPmMstrDetailForm.elements['maPmMstrDetailDTO.woType'].value == 'PMI' && (maPmMstrDetailForm.elements['maPmMstrDetailDTO.pmType'].value == 'RINS' || maPmMstrDetailForm.elements['maPmMstrDetailDTO.pmType'].value == 'EINS')) {
		param = "isDecoratorName=popupPage"+
					"&workCalPmRInsPeriodCommonDTO.filterPmNo="+pmNo+
					"&workCalPmRInsPeriodCommonDTO.strutsAction="+
					"&workCalPmRInsPeriodCommonDTO.filterStartDate="+getMinusMonth(-1)+"-01"+
					"&workCalPmRInsPeriodCommonDTO.filterEndDate="+getToday()
					+"&ACTION_FUNCTION=goSearch";
		url   = contextPath + "/workCalPmRInsPeriodList.do";
	}
	else {
		param = "isDecoratorName=popupPage"+
					"&maWoSchedCommonDTO.strutsAction="+
					"&maWoSchedCommonDTO.filterPmNo="+pmNo+
					"&maWoSchedCommonDTO.filterStartDate="+getMinusMonth(-1)+"-01"+
					"&maWoSchedCommonDTO.filterEndDate="+getToday()
					+"&ACTION_FUNCTION=goSearch";
		url   = contextPath + "/maWoSchedList.do";
	}
	 
	//post 전송 
	openWindowWithPost(url, "PMSCHED_LIST_POPUP", param, pos);
 }
 
 /**
  * 제개정 완료
  */ 
 function goRevcompleted()
 {
 	var revStatus = maPmMstrDetailForm.elements['maPmMstrDetailDTO.revisionStatusId'].value;
 	var objId = maPmMstrDetailForm.elements['maPmMstrDetailDTO.pmId'].value;
 	var objNo = maPmMstrDetailForm.elements['maPmMstrDetailDTO.pmNo'].value;
 	var revhistId = maPmMstrDetailForm.elements['maPmMstrDetailDTO.revisionhistId'].value;
 	 
 	revCompleted(revStatus, objId, objNo, revhistId, "PMSTD");
 }

 function afterRevcompleted(ajaxXmlDoc)
 {
 	//=====================================
 	if (!checkHttpXml(ajaxXmlDoc)) return;
 	//=====================================
 	alertMessage1("<bean:message key='MESSAGE.CMSG102'/>");
 	//조회후 선택!
 	if(parent.findGridRow) parent.findGridRow(maPmMstrDetailForm.elements['maPmMstrCommonDTO.pmId'].value);
 	if(parent.goTabPage) parent.goTabPage(currentPageId);
 }

 function goRevision()
 {
 	var revhistId = maPmMstrDetailForm.elements['maPmMstrDetailDTO.revisionhistId'].value;
 	var desc = maPmMstrDetailForm.elements['maPmMstrDetailDTO.description'].value;
 	
 	openRev("commRevRevision", revhistId, desc, currentPageId);
 }

 function goRevisionhistory()
 {
 	openRevHistory(maPmMstrDetailForm.elements['maPmMstrDetailDTO.pmId'].value, maPmMstrDetailForm.elements['maPmMstrDetailDTO.pmNo'].value);
 }

 /*
  * Audit Trail 보기
  */
 function goAudtrailLink()
 {
 	var objectId = maPmMstrDetailForm.elements['maPmMstrDetailDTO.pmId'].value;
 	var fileName = currentPageId;

 	if(typeof objectId=="undefined") return;

 	goAudTrailList(objectId, fileName);
 }

 /**
  * 승인요청
  */
 function goApproval()
 {
 	if(checkIsUpdate(document)){
 	 alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
 	}else{

	if(checkValidation()) return;
	
	var objectId = maPmMstrDetailForm.elements['maPmMstrCommonDTO.pmId'].value;
	var pmDesc = maPmMstrDetailForm.elements['maPmMstrDetailDTO.description'].value;
	 
	 //APPR_TYPE:결재구분상태[예:일일작업:WODAY] , OBJECT_ID, 
	appAction(objectId, "PMINSREV", pmDesc);
 	}
 }

 function afterApproval()
 {
 	if (parent.findGridRow)	parent.findGridRow(maPmMstrDetailForm.elements['maPmMstrCommonDTO.pmId'].value);
 	goClose('workPmListRInsDetail');
 }
 
function afterAutoCmpt(code, rtnJsonArry)
{
	if(code == "maPmMstrDetailDTO.scheduleTypeDesc") {
		checkScheduleType(rtnJsonArry[0].CDSYSD_NO);
	}
}

/*
 * 개정이력 보기
 */
function goRevisionhistoryLink()
{
	var objectId = maPmMstrDetailForm.elements['maPmMstrDetailDTO.pmId'].value;
	var objectNo = maPmMstrDetailForm.elements['maPmMstrDetailDTO.pmNo'].value;

	if(typeof objectId=="undefined") return;

	goRevisionhistoryList(objectId, objectNo, 'PMSTD');
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maPmMstrDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maPmMstrCommonDTO.pmId" />
	<html:hidden property="maPmMstrCommonDTO.selectedPmType" />
	<html:hidden property="maPmMstrDetailDTO.pmId" />
 	<html:hidden property="maPmMstrDetailDTO.pmType" />
 	<html:hidden property="maPmMstrDetailDTO.deptId" />
 	<html:hidden property="maPmMstrDetailDTO.scheduleType" />
 	<html:hidden property="maPmMstrDetailDTO.periodType" />
 	
 	<html:hidden property="maPmMstrCommonDTO.selectedWoType" />
 	<html:hidden property="maPmMstrDetailDTO.woType" />
 	<html:hidden property="maPmMstrDetailDTO.pmCateg" />
 	<html:hidden property="maPmMstrDetailDTO.empId" />
 	<html:hidden property="maPmMstrDetailDTO.wkCtrId" />
 	<html:hidden property="maPmMstrDetailDTO.eqLocId" />
 	<html:hidden property="maPmMstrDetailDTO.lastSchDate" />
 	<html:hidden property="maPmMstrDetailDTO.wrkcalListId" />
 	<html:hidden property="maPmMstrDetailDTO.lnWrkListId" />
 	<html:hidden property="maPmMstrDetailDTO.isActive" />
 	
 	<html:hidden property="maPmMstrDetailDTO.revisionhistId" />
 	<html:hidden property="maPmMstrDetailDTO.revisionStatusId" />
 	<html:hidden property="maPmMstrDetailDTO.oldCycle" />
    <html:hidden property="maPmMstrDetailDTO.oldPeriodType" />
    <html:hidden property="maPmMstrDetailDTO.oldScheduleType" />
    <html:hidden property="maPmMstrDetailDTO.initWrkDate" />
    <html:hidden property="maPmMstrDetailDTO.plantId" />
    
 	<html:hidden property="appReqCommonDTO.objectId"/>
	<html:hidden property="appReqCommonDTO.apprType"/>
	<div class="article_box">
		<div class="form_box">
		    <div class="field">
				<!-- 작업종류 -->
				<label><bean:message key="LABEL.woTypeDesc"/></label>
				<div class="input_read">
					<html:text property="maPmMstrDetailDTO.woTypeDesc" tabindex="10" readonly="true"/>
				</div>
			</div>
		    <div class="field">
				<!-- 작업형태-->
				<label><bean:message key="LABEL.pmTypeDesc"/></label>
				<div class="input_read">
					<html:text property="maPmMstrDetailDTO.pmTypeDesc" tabindex="20" readonly="true" />
				</div>
			</div>
			<div class="field">
				<!-- 카테고리 -->
				<label><bean:message key="LABEL.pmCategDesc"/></label>
				<div class="input_box">
					<html:text property="maPmMstrDetailDTO.pmCategDesc" tabindex="30" />
					<p class="open_spop">
						<a><span>조회</span></a>
					</p>
				</div>
			</div>
			<div class="field">
				<label><bean:message key="LABEL.pmNo"/></label>
				<div class="input_read">
					<html:text property="maPmMstrDetailDTO.pmNo" tabindex="40" />
				</div>
			</div>
			<div class="field">
				<!-- 사용여부 -->
				<label class="check"><bean:message key="LABEL.isActive"/></label>
				<div class="input_box">
					<html:text property="maPmMstrDetailDTO.isActiveDesc" tabindex="60"/>
                    <p class="open_spop">
                        <a><span>조회</span></a>
                    </p>
				</div>
			</div>
			<div class="field">
				<!-- 예상작업시간(분) -->
				<label><bean:message key="LABEL.predWoTimeMin"/></label>
				<div class="input_box">
					<html:text property="maPmMstrDetailDTO.predWoTimeMin" tabindex="65" styleClass="num"/>
				</div>
			</div>
			<div class="field_long">
				<label class="check"><bean:message key="LABEL.pmDesc"/></label>
				<div class="input_box">
					<html:text property="maPmMstrDetailDTO.description" tabindex="70" />
				</div>
			</div>
			<div class="field">
				<!-- 사용량/시간 구분-->
				<label class="check"><bean:message key="LABEL.scheduleTypeDesc"/></label>
				<div class="input_box">
					<html:text property="maPmMstrDetailDTO.scheduleTypeDesc" tabindex="80"/>
					<p class="open_spop">
						<a><span>조회</span></a>
					</p>
				</div>
			</div>
			
			<div class="field">
                <label><bean:message key="LABEL.cycleDesc"/></label>
                <div class="datetime_wrap">
                    <div class="input_box">
                        <html:text property="maPmMstrDetailDTO.cycle" tabindex="90" styleClass="num"/>
                    </div>
                    <div class="input_box">
                        <html:text property="maPmMstrDetailDTO.periodTypeDesc" tabindex="100" />
                        <p class="open_spop">
                            <a><span>조회</span></a>
                        </p>
                    </div>
                </div>
            </div>
			<div class="field">
				<!-- 사용량 -->
				<label><bean:message key="LABEL.usage"/></label>
				<div class="input_box">
					<html:text property="maPmMstrDetailDTO.usage" tabindex="110" styleClass="num"/>
				</div>
			</div>
			<div class="field">
				<!-- 몇일전 일정생성? -->
				<label><bean:message key="LABEL.woResBef"/></label>
				<div class="input_box">
					<html:text property="maPmMstrDetailDTO.woResBef" tabindex="130" styleClass="num"/>
				</div>
			</div>
			<!--가동달력 -->
			<div class="field">
				<label><bean:message key="LABEL.lnWrkListDesc"/></label>
				<div class="input_box">
					<html:text property="maPmMstrDetailDTO.lnWrkListDesc" tabindex="140" />
					<p class="open_spop">
						<a><span>조회</span></a>
					</p>
				</div>
			</div>
			<!--근무달력 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.workCal"/></label>
				<div class="input_box">
					<html:text property="maPmMstrDetailDTO.wrkcalListDesc" tabindex="140" />
					<p class="open_spop">
						<a><span>조회</span></a>
					</p>
				</div>
			</div>
			<div class="field">
				<!-- 부서 -->
				<label class="check"><bean:message key="LABEL.deptDesc"/></label>
				<div class="input_box">
					<html:text property="maPmMstrDetailDTO.deptDesc" tabindex="140" />
					<p class="open_spop">
						<a><span>조회</span></a>
					</p>
				</div>
			</div>
			<div class="field">
				<!-- 공장 -->
				<label class="check"><bean:message key="LABEL.plant"/></label>
				<div class="input_box">
					<html:text property="maPmMstrDetailDTO.plantDesc" tabindex="140" />
					<p class="open_spop">
						<a>
						<span>조회</span></a>
					</p>
				</div>
			</div>			
			<!-- 작업그룹 -->
			<div class="field">
				<label><bean:message key="LABEL.wkCtr"/></label>
				<div class="input_box">
					<html:text property="maPmMstrDetailDTO.wkCtrDesc" tabindex="145"/>
					<p class="open_spop">
						<a><span>조회</span></a>
					</p>
				</div>
			</div>
			<!-- 담당자 -->
			<div class="field">
				<label><bean:message key="LABEL.manager"/></label>
				<div class="input_box">
					<html:text property="maPmMstrDetailDTO.empName" tabindex="150"/>
					<p class="open_spop">
						<a><span>조회</span></a>
					</p>
				</div>
			</div>
			<!-- Revision상태 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.revisionStatus"/></label>
				<div class="input_read">
					<html:text property="maPmMstrDetailDTO.revisionStatusDesc" tabindex="160" readonly="true"/>
				</div>
			</div>
			<!-- 최신 Revision 여부 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.isLastVersion"/></label>
				<div class="input_read">
					<html:text property="maPmMstrDetailDTO.isLastVersion" tabindex="170"/>
				</div>
			</div>
			<!-- 생성시간 -->
			<div class="field">
				<label><bean:message key="LABEL.createDate"/></label>
				<div class="input_read" >
					<html:text property="maPmMstrDetailDTO.creDate" tabindex="178" readonly="true"/>
				</div>
			</div>
			<!-- 수정시간 -->
			<div class="field">
				<label><bean:message key="LABEL.updateDate"/></label>
				<div class="input_read" >
					<html:text property="maPmMstrDetailDTO.updDate" tabindex="179" readonly="true"/>
				</div>
			</div>
			<!-- 1일 작업횟수 -->
			<div class="field">
				<label><bean:message key="LABEL.dailyWoCnt"/></label>
				<div class="input_box">
					<html:text property="maPmMstrDetailDTO.workNumber" tabindex="180" styleClass="num"/>
				</div>
			</div>
			<!-- 지연허용(기간/시간/횟수)값 -->
			<div class="field">
				<label><bean:message key="LABEL.toleranceValue"/></label>
				<div class="input_box">
					<html:text property="maPmMstrDetailDTO.tolerance" tabindex="175" styleClass="num"/>
				</div>
			</div>
			<div class="field_long">
				<!-- 비고 -->
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="maPmMstrDetailDTO.remark" styleClass="ta50" tabindex="180" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
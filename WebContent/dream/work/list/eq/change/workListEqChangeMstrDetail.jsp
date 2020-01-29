<%--===========================================================================
작업결과 - 상세(설비변경작업)
author  nhkim8548
version $Id: workListEqChangeMstrDetail.jsp, V1.0 2019/12/16 16:17:54 nhkim8548 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="common.bean.User"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%@ page import="dream.work.list.action.MaWoResultMstrDetailAction"%>
<%@ page import="dream.work.list.action.MaWoResultWoImageListAction"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html>
<head>
<!-- WO # -->
<title><bean:message key='LABEL.woNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
var saveStrutsAction;

/** 자동완성 변수  */
var equipNoAc;
var mainMngAc;
var subEmpAc;
var eqAsmbAc;
var deptAc;
var vendorDescAc;
var wkCtrAc;
var plantAc;
var curEqLocAc;
var chgEqLocAc;
var chgEqLocDescAc;
var curEqStatusAc;
var chgEqStatusAc;

function loadPage() 
{		
	setForUpdate();
	setBtnAsStatus();
	
	setTitle("maWoResultMstrDetailDTO.woNo","maWoResultMstrDetailDTO.wkOrDesc");
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woStatusDesc'].readOnly="true";
	
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woReqResId'].value = maWoResultMstrDetailForm.elements['maWoResultMstrCommonDTO.woReqResId'].value;
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woReqId'].value = maWoResultMstrDetailForm.elements['maWoReqCommonDTO.woReqId'].value;

	$("input[name='maWoResultMstrDetailDTO.wkorDate']").blur( function(){
		if(typeof enhanceAfterChangeWkorDate == "function") enhanceAfterChangeWkorDate();
		setWorkTime();
    });
	$("input[name='maWoResultMstrDetailDTO.endDate']").blur( function(){
		setWorkTime();
	});
	$("input[name='maWoResultMstrDetailDTO.endTime']").blur( function(){
		setWorkTime();
	});
	
	// 설비 AC
	equipNoAc = new autoC({"maWoResultMstrDetailDTO.itemNo":"item_no"});
	equipNoAc.setAcConditionMap({
 		"comp_no":loginUser.compNo,
 	});
	equipNoAc.setTable("TAEQUIPMENT");
	equipNoAc.setAcResultMap({
    	"maWoResultMstrDetailDTO.equipId":"equip_id"
      , "maWoResultMstrDetailDTO.eqLocId":"eqloc_id"
      , "maWoResultMstrDetailDTO.eqLocDesc":"eqLocDesc"
      , "maWoResultMstrDetailDTO.equipDesc":"description"
      , "maWoResultMstrDetailDTO.eqctgType":"eqctg_type"
      , "maWoResultMstrDetailDTO.currentEqStatusNo":"eq_status"
      , "maWoResultMstrDetailDTO.currentEqStatusDesc":"eqStatusDesc"
      , "maWoResultMstrDetailDTO.currentEqLocNo":"eqloc_no"
      , "maWoResultMstrDetailDTO.currentEqLocDesc":"eqLocDesc"
    });
	equipNoAc.setAcDConditionMap({
    	"dept_id" : "maWoResultMstrDetailDTO.deptId"
      , "deptDesc" : "maWoResultMstrDetailDTO.deptDesc"
    });
	equipNoAc.setKeyName("maWoResultMstrDetailDTO.equipId"); 
	equipNoAc.init();
    
    // 담당자 AC
    mainMngAc = new autoC({"maWoResultMstrDetailDTO.empDesc":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
      , "is_join":"Y"
  	});
    mainMngAc.setTable("TAEMP");
    mainMngAc.setKeyName("maWoResultMstrDetailDTO.empId");
    mainMngAc.setAcResultMap({
        "maWoResultMstrDetailDTO.empId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maWoResultMstrDetailDTO.deptId"
    });
    mainMngAc.init();
    
    // 부 담당자 AC
    subEmpAc = new autoC({"maWoResultMstrDetailDTO.subEmpDesc":"emp_name"});
    subEmpAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
      , "is_join":"Y"
  	});
    subEmpAc.setTable("TAEMP");
    subEmpAc.setKeyName("maWoResultMstrDetailDTO.subEmpId");
    subEmpAc.setAcResultMap({
        "maWoResultMstrDetailDTO.subEmpId":"emp_id"
    });
    subEmpAc.init();
    
	// 설비부위 AC
    eqAsmbAc = new autoC({"maWoResultMstrDetailDTO.eqAsmbDesc":"full_desc"});
    eqAsmbAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    eqAsmbAc.setAcDConditionMap({
    	"equip_id":"maWoResultMstrDetailDTO.equipId"
  	});
    eqAsmbAc.setTable("TAEQASMB");
    eqAsmbAc.setKeyName("maWoResultMstrDetailDTO.eqAsmbId");
    eqAsmbAc.setAcResultMap({
        "maWoResultMstrDetailDTO.eqAsmbId":"eqasmb_id"
    });
    eqAsmbAc.init();
    
    // 담당부서 AC
    deptAc = new autoC({"maWoResultMstrDetailDTO.deptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    deptAc.setTable("TADEPT");
    deptAc.setKeyName("maWoResultMstrDetailDTO.deptId");
    deptAc.setAcResultMap({
        "maWoResultMstrDetailDTO.deptId":"dept_id"
    });
    deptAc.init();
	
    // 거래처 AC
    vendorDescAc = new autoC({"maWoResultMstrDetailDTO.vendorDesc":"description"});
    vendorDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
      , "is_use":"Y"
  	});
    vendorDescAc.setTable("TAVENDOR");
    vendorDescAc.setKeyName("maWoResultMstrDetailDTO.vendorId");
    vendorDescAc.setAcResultMap({
        "maWoResultMstrDetailDTO.vendorId":"vendor_id"
    });
    vendorDescAc.init();
	
    // 담당작업그룹 AC
    wkCtrAc = new autoC({"maWoResultMstrDetailDTO.wkCtrDesc":"description"});
    wkCtrAc.setAcDisplay("DESCRIPTION");
    wkCtrAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    wkCtrAc.setTable("TAWKCTR");
    wkCtrAc.setKeyName("maWoResultMstrDetailDTO.wkCtrId");
    wkCtrAc.setAcResultMap({
        "maWoResultMstrDetailDTO.wkCtrId":"wkctr_id"
    });
    wkCtrAc.init();

 	// 공장명 AC
    plantAc = new autoC({"maWoResultMstrDetailDTO.plantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "assAssetCommonDTO.plantId":"plant"
    });
    plantAc.init();
    
    // 현재위치 AC
    curEqLocAc = new autoC({"maWoResultMstrDetailDTO.currentEqLocNo":"eqLocNo"});
    curEqLocAc.setTable("TAEQLOC");
    curEqLocAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    });
    curEqLocAc.setAcDConditionMap({
    	"plant" : "maWoResultMstrDetailDTO.plant"
    });
    curEqLocAc.setAcResultMap({
      "maWoResultMstrDetailDTO.currentEqLocDesc":"full_desc"
    });
    curEqLocAc.init();
    
 	// 변경위치 AC
    chgEqLocAc = new autoC({"maWoResultMstrDetailDTO.changedEqLocNo":"eqLocNo"});
    chgEqLocAc.setTable("TAEQLOC");
    chgEqLocAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    });
    chgEqLocAc.setAcDConditionMap({
    	"plant" : "maWoResultMstrDetailDTO.plant"
    });
    chgEqLocAc.setAcResultMap({
       "maWoResultMstrDetailDTO.changedEqLocId":"eqLocId"
     , "maWoResultMstrDetailDTO.changedEqLocDesc":"full_desc"
    });
    chgEqLocAc.setKeyName("maWoResultMstrDetailDTO.changedEqLocId");
    chgEqLocAc.init();
    
 	// 변경위치 Desc AC
    chgEqLocDescAc = new autoC({"maWoResultMstrDetailDTO.changedEqLocDesc":"full_desc"});
    chgEqLocDescAc.setTable("TAEQLOC");
    chgEqLocDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    });
    chgEqLocDescAc.setAcDConditionMap({
    	"plant" : "maWoResultMstrDetailDTO.plant"
    });
    chgEqLocDescAc.setAcResultMap({
       "maWoResultMstrDetailDTO.changedEqLocNo":"eqLocNo"
    });
    chgEqLocDescAc.init();
    
 	// 현재상태 AC
    curEqStatusAc = new autoC({"maWoResultMstrDetailDTO.currentEqStatusNo":"cdsysd_no"});
    curEqStatusAc.setTable("TACDSYSD");
    curEqStatusAc.setAcConditionMap({
        "list_type":"EQ_STATUS"
    	,"is_use":"Y"
    });
    curEqStatusAc.setAcResultMap({
        "maWoResultMstrDetailDTO.currentEqStatusDesc":"description"
    });
    curEqStatusAc.init();
 	
    // 변경상태 AC
    chgEqStatusAc = new autoC({"maWoResultMstrDetailDTO.changedEqStatusNo":"cdsysd_no"});
    chgEqStatusAc.setTable("TACDSYSD");
    chgEqStatusAc.setAcConditionMap({
        "list_type":"EQ_STATUS"
    	,"is_use":"Y"
    });
    chgEqStatusAc.setAcResultMap({
    	"maWoResultMstrDetailDTO.changedEqStatusId":"cdsysd_no" // 자동완성 필드 체크를 위한 값
      , "maWoResultMstrDetailDTO.changedEqStatusDesc":"description"
    });
    chgEqStatusAc.setKeyName("maWoResultMstrDetailDTO.changedEqStatusId");
    chgEqStatusAc.init();

    // 자가/외주 구분  AC
    acSysDesc("maWoResultMstrDetailDTO.selfVendorTypeDesc","maWoResultMstrDetailDTO.selfVendorType","SELF_VENDOR_TYPE",true);
    
  	// 작업종류  AC
    acSysDesc("maWoResultMstrDetailDTO.woTypeDesc","maWoResultMstrDetailDTO.woTypeId","WO_TYPE",true);
	
  	// 청소여부  AC
    acSysDesc("maWoResultMstrDetailDTO.isCleaning","maWoResultMstrDetailDTO.isCleaningId","IS_USE",true);
	
  	// 품의여부  AC
    acSysDesc("maWoResultMstrDetailDTO.isDraft","maWoResultMstrDetailDTO.isDraft","IS_USE",true);

	if(ckCreate(currentPageId))
		goInput();
	else 
		goUpdate();
	
	if(typeof enhanceLoadPage == "function") enhanceLoadPage();
}

/**
 * Show/Hide Button
 */
function setBtnAsStatus()
{
	if(maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woStatusId'].value=="C"){
		setDisableAll();
		
		hideBtn("CONFIRM");
		hideBtn("APPROVAL");
		
		showBtn("REVERSE");

	}
	else if(maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woStatusId'].value=="PRWRA" || maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woStatusId'].value=="PRWOA"){
		setDisableAll();
		
		hideBtn("CONFIRM");
		hideBtn("REVERSE");
		hideBtn("APPROVAL");
	}
	else{
		setEnableAll();
		
		showBtn("CONFIRM");
		showBtn("APPROVAL");
		
		hideBtn("REVERSE");
	}
}


function goUpdate(){
	
}

function goInput()
{
	sequenceNextVal('SQAWKOR_ID');
	
	//완료버튼 감추기.
	hideBtn("CONFIRM");
	hideBtn("APPROVAL");
	
	//작업종류 
	setInitVal('maWoResultMstrDetailDTO.woTypeId', maWoResultMstrDetailForm.elements['maWoResultMstrCommonDTO.selectedWoType'].value);
	setInitVal('maWoResultMstrDetailDTO.woTypeDesc', getSysCodeDesc('WO_TYPE', maWoResultMstrDetailForm.elements['maWoResultMstrCommonDTO.selectedWoType'].value));
	
	//작업형태 
	setInitVal('maWoResultMstrDetailDTO.pmTypeId', maWoResultMstrDetailForm.elements['maWoResultMstrCommonDTO.selectedPmType'].value);
	setInitVal('maWoResultMstrDetailDTO.pmTypeDesc', getSysCodeDesc(maWoResultMstrDetailForm.elements['maWoResultMstrCommonDTO.selectedWoType'].value+ '_TYPE', maWoResultMstrDetailForm.elements['maWoResultMstrCommonDTO.selectedPmType'].value));

	//작업상태 = P - 작업대기
	setInitVal('maWoResultMstrDetailDTO.woStatusId', "P");
	setInitVal('maWoResultMstrDetailDTO.woStatusDesc', getSysCodeDesc("WO_STATUS", "P"));

	//자가/외주 설비종류가 투자인것은 외주로 셋팅, 나머지는 자가로 셋팅
	if(maWoResultMstrDetailForm.elements['maWoResultMstrCommonDTO.selectedWoType'].value=="IV" || maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woTypeId'].value=="IV"){
		setInitVal('maWoResultMstrDetailDTO.selfVendorType', "VENDOR");
		setInitVal('maWoResultMstrDetailDTO.selfVendorTypeDesc', getSysCodeDesc("SELF_VENDOR_TYPE", "VENDOR"));
	}else{
		setInitVal('maWoResultMstrDetailDTO.selfVendorType', "SELF");
		setInitVal('maWoResultMstrDetailDTO.selfVendorTypeDesc', getSysCodeDesc("SELF_VENDOR_TYPE", "SELF"));
	}
	//작업시작일자, 종료일자 넣기.
	setInitVal('maWoResultMstrDetailDTO.startDate', getToday());
	setInitVal('maWoResultMstrDetailDTO.endDate', getToday());
	
	//작업일자 넣기
	setInitVal('maWoResultMstrDetailDTO.wkorDate', getWorkDay());
	
	//작업시작시간(현재시간), 종료시간(현재시간) 넣기
	setInitVal('maWoResultMstrDetailDTO.startTime', getTime(false));
	setInitVal('maWoResultMstrDetailDTO.endTime', getTime(false));
	
	//작업시간(분) 넣기
	setWorkTime();
	
	//부서
	setInitVal('maWoResultMstrDetailDTO.deptId', "<%=user.getDeptId()%>");
	setInitVal('maWoResultMstrDetailDTO.deptDesc', "<%=user.getDeptDesc()%>");
	
	//사원
	setInitVal('maWoResultMstrDetailDTO.empId', "<%=user.getEmpId()%>");
	setInitVal('maWoResultMstrDetailDTO.empDesc', "<%=user.getEmpName()%>");
	
	// 처리작업그룹
	if(maWoResultMstrDetailForm.elements['maWoReqCommonDTO.woReqId'].value != '')
		setInitVal("maWoResultMstrDetailDTO.wkCtrId", maWoResultMstrDetailForm.elements['maWoReqDetailDTO.recWkCtrId'].value);
	else 
		setInitVal("maWoResultMstrDetailDTO.wkCtrId", <%=user.getWkctrId()%>);
	
	if(maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.equipId'].value == "")
	{
		equipNoAc.openLov();
	}
	
	if(typeof enhanceGoInput == "function") enhanceGoInput();
}

function setSequenceVal(sequenceVal)
{
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woNo'].value = sequenceVal;
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.wkOrId'].value = sequenceVal;
	maWoResultMstrDetailForm.elements['maWoResultMstrCommonDTO.wkOrId'].value = sequenceVal;
}

function goOpen(pageId)
{
	goTabPage(pageId);
}

function goTabPage(pageId)
{
	var form = document.maWoResultMstrDetailForm;
	
	if(pageId == "maDocLibList" || pageId == "maPtDocLibList")
	{	
		maWoResultMstrDetailForm.elements['maDocLibCommonDTO.objectId'].value = maWoResultMstrDetailForm.elements['maWoResultMstrCommonDTO.wkOrId'].value;
		maWoResultMstrDetailForm.elements['maDocLibCommonDTO.objectType'].value = "WORESULT";  //WORESULT
		maWoResultMstrDetailForm.elements['maDocLibCommonDTO.description'].value = 
		maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.wkOrDesc'].value;  //제목
	
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else if(pageId == "maAppPrcList" || pageId == "appPrcList") {
		maWoResultMstrDetailForm.elements['appReqCommonDTO.objectId'].value = maWoResultMstrDetailForm.elements['maWoResultMstrCommonDTO.wkOrId'].value;
		maWoResultMstrDetailForm.elements['appReqCommonDTO.apprType'].value = "WORKORDER";
		goCommonTabPage(form, '' , pageId);
	}
	else if(pageId == "maWoResultWoImageList") {
		goCommonTabPage(form, '<%=MaWoResultWoImageListAction.DATA_FIND%>' , pageId);
	}
	else
		goCommonTabPage(form, '' , pageId);
    
}
/**
 * 작업완료
 */
function goConfirm()
{
	if(checkIsUpdate(document)){
		alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
	} else {
		getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG045'/>", function(result){
			if(result){
				//================================
				// 필수 항목 체크한다.
				//================================
				if(checkValidation()) return;
				//================================
				// 필수 확정항목 체크한다.
				//================================
				if(checkConfirmValidation()) return;
				
				//작업시간이 미래라면 완료 처리 안됨
				var todayStr = getToday().split("-").join("");
				var startDate = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.startDate'].value;
				startDate = startDate.split("-").join("");
				var wkorDate = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.wkorDate'].value;
				wkorDate = wkorDate.split("-").join("");
				
				if(todayStr < startDate||todayStr < wkorDate)
				{
					alertMessage1("<bean:message key='MESSAGE.MSG0034'/>");
					return;
				}
				//작업상태 = C - 작업완료
				maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woStatusId'].value = "C"
				maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woStatusDesc'].value = getSysCodeDesc("WO_STATUS", "C");
				
				maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.updDate'].value = getNowDateTime(true); 
				maWoResultMstrDetailForm.strutsAction.value = '<%=MaWoResultMstrDetailAction.WO_RESULT_DETAIL_COMPLETE%>';
				
				var actionUrl = contextPath + "/maWoResultMstrDetail.do";
				
				XMLHttpPost(actionUrl, FormQueryString(maWoResultMstrDetailForm), 'afterConfirm');
			}
		}
	)}
}

/**
 * 완료후 호출
 */
function afterConfirm(ajaxXmlDoc)
{
	alertMessage1("<bean:message key='MESSAGE.MSG0015'/>");
	
	if (parent.findGridRow)	parent.findGridRow(maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.wkOrId'].value);

	// 이상점검처리대상 - 작업결과 (목록) 에서 확정한 경우 확정 후 이상점검처리대상 상세 재조회
	if(typeof searchPage("maBdPointWoRsltList").findGridRow == "function" && parent.currentPageId == "maBdPointWoRsltList"){
		searchPage("maBdPointWoRsltList").findGridRow(maWoResultMstrDetailForm.elements['maWoResultMstrCommonDTO.woNgPointResId'].value, '');
			
		if(typeof searchPage("maBdPointDetail").checkStatus == "function" && parent.currentPageId == "maBdPointWoRsltList"){
			searchPage("maBdPointDetail").checkStatus();	
		}
	}
	 
	if(typeof enhanceAfterConfirm == "function") enhanceAfterConfirm();
	 
	// 작업 확정 시 요청 상태 변경
	if(typeof searchPage("maWoReqDetail").checkStatus == "function"){
		searchPage("maWoReqDetail").checkStatus(maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woReqResId'].value);	
	}
	
	//요청-처리사항(목록)에서 팝업창을 열었다면 저장시 요청-처리사항(목록) 조회 호출 
	if(opener)
	{	
		if(opener.currentPageId == "maWoReqResList") opener.goSearch();
	}

	setBtnAsStatus();
 }
 
/**
 * 작업완료 취소
 */
function goReverse()
{
	getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0231'/>", function(result){
		 if(result){
		    var url = contextPath + "/maWoResultMstrDetail.do";
		    maWoResultMstrDetailForm.strutsAction.value = '<%=MaWoResultMstrDetailAction.WO_RESULT_DETAIL_COMPLETE_CANCEL%>';
		    XMLHttpPost(url, FormQueryString(maWoResultMstrDetailForm), 'afterReverse');
		 }
	});
}

/**
 * 완료 취소후 호출
 */
function afterReverse(ajaxXmlDoc) {
 	alertMessage1("<bean:message key='MESSAGE.CMSG034'/>");
	
 	//작업상태 = P - 작업대기
 	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woStatusId'].value = "P"
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woStatusDesc'].value = getSysCodeDesc("WO_STATUS", "P");
	
	if (parent.findGridRow)	parent.findGridRow(maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.wkOrId'].value);

	// 작업 확정 취소 시 요청접수 상태 변경
	if(typeof searchPage("maWoReqDetail").checkStatus == "function"){
		searchPage("maWoReqDetail").checkStatus(maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woReqResId'].value);	
	}
	
	//요청-처리사항(목록)에서 팝업창을 열었다면 저장시 요청-처리사항(목록) 조회 호출 
	if(opener)
	{	
		if(opener.currentPageId == "maWoReqResList") opener.goSearch();
	}
	
	setBtnAsStatus();
}

/**
 * 저장
 */ 
function goSave()
{
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	//strutsAction 셋팅.
	if(ckCreate(currentPageId)) {
		maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.creDate'].value = getNowDateTime(true); 
		maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.updDate'].value = getNowDateTime(true); 

		maWoResultMstrDetailForm.strutsAction.value = '<%=MaWoResultMstrDetailAction.WO_RESULT_DETAIL_INPUT%>';
	}
	else {
		maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.updDate'].value = getNowDateTime(true); 

		maWoResultMstrDetailForm.strutsAction.value = '<%=MaWoResultMstrDetailAction.WO_RESULT_DETAIL_UPDATE%>';
	}
	
	if(M$('maWoResultMstrDetailDTO.workTime').value == '') setWorkTime();
		
	var actionUrl = contextPath + "/maWoResultMstrDetail.do";
	ajaxPost(actionUrl, FormQueryString(maWoResultMstrDetailForm))
	.done(function(d){
		maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.wkOrId'].value = maWoResultMstrDetailForm.elements['maWoResultMstrCommonDTO.wkOrId'].value;
		
		// 요청 - 작업결과 (목록)에서 저장한 경우 
		if(typeof searchPage("maWoReqDetail").checkStatus == "function"){
			searchPage("maWoReqDetail").checkStatus(maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woReqResId'].value);	
		}
	 	
		// 이상점검처리대상 - 작업계획 (목록) 에서 저장한 경우
	    if(parent.currentPageId == "maBdPointWoRsltList" && maWoResultMstrDetailForm.strutsAction.value == '<%=MaWoResultMstrDetailAction.WO_RESULT_DETAIL_INPUT%>')
	    {
	   	 	parent.afterCreate(maWoResultMstrDetailForm.elements['maWoResultMstrCommonDTO.wkOrId'].value);
	    }
		
		if (parent.findGridRow)	parent.findGridRow(maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.wkOrId'].value);
		
		getTopPage().afterSaveAll(currentPageId);
		
		setBtnAsStatus();
		
		if(opener)
		{	//요처-처리사항(목록)에서 팝업창을 열었다면 저장시 요청-처리사항(목록) 조회 호출 
			if(opener.currentPageId == "maWoReqResList") opener.goSearch();
		}
	});
}

/**
 * Time 세팅되고 호출되는 Callback
 */
function afterSetTime(_name)
{
	setWorkTime();
	if(_name.indexOf("lnDnStartTime")>-1||_name.indexOf("lnDnEndTime")>-1){
		setBmWorkTime('lnDn');
	}
}

/**
 * Date 세팅되고 호출되는 Callback
 */
function afterSetDate(_name)
{
	if(_name.indexOf("wkorDate")>-1){
		if(typeof enhanceAfterChangeWkorDate == "function") enhanceAfterChangeWkorDate();
	}
	
	setWorkTime();
}

/**
  * 승인요청
  */
function goApproval()
{
	if(checkIsUpdate(document)){
		alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
	} else {
		var wkOrId = maWoResultMstrDetailForm.elements['maWoResultMstrCommonDTO.wkOrId'].value;
		var description = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.wkOrDesc'].value;
		 
		//APPR_TYPE:결재구분상태[예:일일작업:WODAY] , OBJECT_ID, 
		appAction(wkOrId, "WORKORDER", description);
	}
}
 
function appAction(objectId, apprType, desc)
{
	var param = "strutsAction="+strutsActionApproval + 
	            "&appReqCommonDTO.objectId="+objectId +
		        "&appReqCommonDTO.apprType=" + apprType +
	 			"&appReqCommonDTO.title=" + desc;
	  
	 openLayerPopup("appReqDetail",param);
}
 
function afterApproval()
{
	if (parent.findGridRow)	parent.findGridRow(maWoResultMstrDetailForm.elements['maWoResultMstrCommonDTO.wkOrId'].value);
	goClose('workListEqChangeMstrDetail');
}

function setWorkTime(){
	var startDate = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.startDate'].value;
 	var startTime = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.startTime'].value;
 	var endDate = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.endDate'].value;
 	var endTime = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.endTime'].value;
 	
 	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.workTime'].value = setNumberFormat(getMinInterval(maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.startDate'], maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.startTime'], maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.endDate'],maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.endTime']));
}
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/maWoResultMstrDetail" >
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maWoResultMstrCommonDTO.wkOrId" />
<html:hidden property="maWoResultMstrCommonDTO.selectedWoType" />
<html:hidden property="maWoResultMstrCommonDTO.selectedPmType" />
<html:hidden property="maWoResultMstrCommonDTO.woReqResId" />
<html:hidden property="maWoResultMstrCommonDTO.woReqId" />
<html:hidden property="maWoResultMstrDetailDTO.wkOrId" />
<html:hidden property="maWoResultMstrDetailDTO.equipId" />
<html:hidden property="maWoResultMstrDetailDTO.woTypeId" />
<html:hidden property="maWoResultMstrDetailDTO.woStatusId" />
<html:hidden property="maWoResultMstrDetailDTO.pmTypeId" />
<html:hidden property="maWoResultMstrDetailDTO.deptId" />
<html:hidden property="maWoResultMstrDetailDTO.empId" />
<html:hidden property="maWoResultMstrDetailDTO.vendorId" />
<html:hidden property="maWoResultMstrDetailDTO.selfVendorType" />
<html:hidden property="maWoResultMstrDetailDTO.pWkOrId" />  <!-- 상위Work Order ID -->
<html:hidden property="maWoResultMstrDetailDTO.woGenType" />
<html:hidden property="maWoResultMstrDetailDTO.eqLocId" />
<html:hidden property="maWoResultMstrDetailDTO.wkCtrId" />
<html:hidden property="maWoResultMstrDetailDTO.eqAsmbId"/>
<html:hidden property="maWoResultMstrDetailDTO.isCleaningId"/>
<html:hidden property="maWoResultMstrDetailDTO.pmCalibStdTpId" />
<html:hidden property="maWoResultMstrDetailDTO.subEmpId" />
<html:hidden property="maWoResultMstrDetailDTO.woReqId" />
<html:hidden property="maWoResultMstrDetailDTO.eqctgType" />
<html:hidden property="maWoResultMstrDetailDTO.plant" />
<html:hidden property="maWoResultMstrDetailDTO.woReqResId" />
<html:hidden property="maWoResultMstrDetailDTO.changedEqLocId" />
<html:hidden property="maWoResultMstrDetailDTO.changedEqStatusId" />
<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
<html:hidden property="maDocLibCommonDTO.objectType" />
<html:hidden property="maDocLibCommonDTO.securGrade" />
<html:hidden property="maDocLibCommonDTO.docCateg" />
<html:hidden property="maDocLibCommonDTO.description" />
<html:hidden property="appReqCommonDTO.objectId"/>
<html:hidden property="appReqCommonDTO.apprType"/>
<html:hidden property="maWoReqCommonDTO.woReqId"/>
<html:hidden property="maWoReqDetailDTO.recWkCtrId"/>
<div class="article_box">
	<div class="form_box">
		<!-- WO # -->
		<div class="field">
			<label><bean:message key="LABEL.woNo"/></label>
			<div class="input_read" id="woNoDiv">
				<html:text property="maWoResultMstrDetailDTO.woNo" tabindex="110" readonly="true"/>
			</div>
		</div>
		<!-- WO상태 -->
		<div class="field">
			<label><bean:message key="LABEL.woStatus"/></label>
			<div class="input_read" id="woStatusDescDiv">
				<html:text property="maWoResultMstrDetailDTO.woStatusDesc" tabindex="120" />
			</div>
		</div>
		<!-- 작업종류  -->
		<div class="field">
			<label class="check"><bean:message key="LABEL.woType"/></label>
			<div class="input_read">
				<html:text property="maWoResultMstrDetailDTO.woTypeDesc" tabindex="130" readonly="true" />
				<p class="open_spop">
					<a><span>조회</span></a>
				</p>
			</div>
		</div>
		<!-- 작업형태  -->
		<div class="field">
			<label class="check"><bean:message key="LABEL.pmType"/></label>
			<div class="input_read">
				<html:text property="maWoResultMstrDetailDTO.pmTypeDesc" tabindex="140" readonly="true" />
				<p class="open_spop">
					<a><span>조회</span></a>
				</p>
			</div>
		</div>
		<!-- 설비위치 -->
		<div class="field_long">
			<label><bean:message key="LABEL.location"/></label>
			<div class="input_read" id="eqLocDescDiv">
				<html:text property="maWoResultMstrDetailDTO.eqLocDesc" tabindex="150" readonly="true"/>
			</div>
		</div>
		<!-- 작업명 -->
		<div class="field_long">
			<label class="check"><bean:message key="LABEL.woName"/></label>
			<div class="input_box">
				<html:text property="maWoResultMstrDetailDTO.wkOrDesc" tabindex="160" />
			</div>
		</div>
		<!-- 설비 -->
		<div class="field ty1-2">
			<label class="check"><bean:message key="LABEL.equipment"/></label>
			<div class="multi_wrap">
				<div class="input_box">
					<html:text property="maWoResultMstrDetailDTO.itemNo" tabindex="170"/>
					<p class="open_spop" id="equipPopDiv">
						<a><span>조회</span></a>
					</p>
				</div>
				<div class="input_read">
					<html:text property="maWoResultMstrDetailDTO.equipDesc" tabindex="171" readonly="true"/>
				</div>
			</div>
		</div>
		<!-- 설비부위 -->
		<div class="field">
			<label><bean:message key="LABEL.asmb"/></label>
			<div class="input_box">
				<html:text property="maWoResultMstrDetailDTO.eqAsmbDesc" tabindex="180"/>
				<p class="open_spop">
					<a><span>조회</span></a>
				</p>
			</div>
		</div>
		<!-- 작업일자 -->
		<div class="field">
			<label class="check"><bean:message key="LABEL.wkorDate"/></label>
			<div class="input_box">
				<html:text property="maWoResultMstrDetailDTO.wkorDate" tabindex="190" />
				<p class="open_calendar"><span>날짜</span></p>
			</div>
		</div>
		<div class="field">
			<label><bean:message key="LABEL.plant"/></label>
			<div class="input_box">
				<html:text property="maWoResultMstrDetailDTO.plantDesc" tabindex="200"/>
				<p class="open_spop">
					<a><span>조회</span></a>
				</p>
			</div>
		</div>
		<!-- 담당부서 -->
		<div class="field">
			<label class="check"><bean:message key="LABEL.manageDept"/></label>
			<div class="input_box">
				<html:text property="maWoResultMstrDetailDTO.deptDesc" tabindex="210"/>
				<p class="open_spop">
					<a><span>조회</span></a>
				</p>
			</div>
		</div>
		<!-- 담당작업그룹 -->
		<div class="field">
			<label><bean:message key="LABEL.wkCtr"/></label>
			<div class="input_box">
				<html:text property="maWoResultMstrDetailDTO.wkCtrDesc" tabindex="220"/>
				<p class="open_spop">
					<a><span>조회</span></a>
				</p>
			</div>
		</div>
		<!-- 담당자 -->
		<div class="field">
			<label class="lastcheck"><bean:message key="LABEL.manager"/></label>
			<div class="input_box">
				<html:text property="maWoResultMstrDetailDTO.empDesc" tabindex="230"/>
				<p class="open_spop">
					<a><span>조회</span></a>
				</p>
			</div>
		</div>
		<!-- 부 담당자 -->
		<div class="field">
			<label><bean:message key="LABEL.managerSub"/></label>
			<div class="input_box">
				<html:text property="maWoResultMstrDetailDTO.subEmpDesc" tabindex="240"/>
				<p class="open_spop">
					<a><span>조회</span></a>
				</p>
			</div>
		</div>
		<!-- 자가/외주  -->
		<div class="field">
			<label><bean:message key="LABEL.selfVendorType"/></label>
			<div class="input_box">
				<html:text property="maWoResultMstrDetailDTO.selfVendorTypeDesc" tabindex="250" />
				<p class="open_spop">
					<a><span>조회</span></a>
				</p>
			</div>
		</div>
		<!-- 거래처 -->
		<div class="field">
			<label><bean:message key="LABEL.vendor"/></label>
			<div class="input_box">
                   <html:text property="maWoResultMstrDetailDTO.vendorDesc" tabindex="260" />
                   <p class="open_spop">
                       <a><span>조회</span></a>
                   </p>
               </div>
		</div>
		<!-- 청소여부 -->
		<div class="field">
			<label><bean:message key="LABEL.isCleaning"/></label>
			<div class="input_read" >
				<html:text property="maWoResultMstrDetailDTO.isCleaning" tabindex="270"/>
				<p class="open_spop">
					<a><span>조회</span></a>
				</p>
			</div>
		</div>
		<!-- 품의여부 -->
		<div class="field">
			<label><bean:message key="LABEL.isDraft"/></label>
			<div class="input_box">
				<html:text property="maWoResultMstrDetailDTO.isDraft" tabindex="280"/>
				<p class="open_spop">
					<a><span>조회</span></a>
				</p>
			</div>
		</div>
		<!-- 작업시작시간 -->
		<div class="field">
			<label class="lastcheck"><bean:message key="LABEL.woFromTime"/></label>
			<div class="datetime_wrap">
				<div class="input_box input_carendar">
					<html:text property="maWoResultMstrDetailDTO.startDate" tabindex="290" />
					<p class="open_calendar"><span>날짜</span></p>
				</div>
				<div class="input_box">
					<html:text property="maWoResultMstrDetailDTO.startTime"   tabindex="291"/>
					<p class="open_time"><span>시간</span></p>
				</div>
			</div>
		</div>
		<!-- 금액 -->
		<div class="field" id="amtDiv">
			<label><bean:message key="LABEL.amt"/></label>
			<div class="input_box">
				<html:text property="maWoResultMstrDetailDTO.laborAmt" tabindex="300" styleClass="num"/>
			</div>
		</div>
		<!-- 작업종료시간-->
		<div class="field">
			<label class="lastcheck"><bean:message key="LABEL.woToTime"/></label>
			<div class="datetime_wrap">
				<div class="input_box input_carendar">
					<html:text property="maWoResultMstrDetailDTO.endDate" tabindex="310" />
					<p class="open_calendar"><span>날짜</span></p>
				</div>
				<div class="input_box">
					<html:text property="maWoResultMstrDetailDTO.endTime" tabindex="311" />
					<p class="open_time"><span>시간</span></p>
				</div>
			</div>
		</div>
		<!-- 작업시간(분) -->
		<div class="field">
			<label class="lastcheck"><bean:message key="LABEL.woTimeMin"/></label>
			<div class="input_box">
				<html:text property="maWoResultMstrDetailDTO.workTime" tabindex="320" styleClass="num"/>
			</div>
		</div>
		<!-- 현재상태 -->
		<div class="field ty1-2">
			<label class="check"><bean:message key="LABEL.currentEqStatus"/></label>
			<div class="multi_wrap">
				<div class="input_box">
					<html:text property="maWoResultMstrDetailDTO.currentEqStatusNo" tabindex="330"/>
					<p class="open_spop">
						<a><span>조회</span></a>
					</p>
				</div>
				<div class="input_read">
					<html:text property="maWoResultMstrDetailDTO.currentEqStatusDesc" tabindex="331" readonly="true"/>
				</div>
			</div>
		</div>
		<!-- 변경상태 -->
		<div class="field ty1-2">
			<label class="check"><bean:message key="LABEL.changedEqStatus"/></label>
			<div class="multi_wrap">
				<div class="input_box">
					<html:text property="maWoResultMstrDetailDTO.changedEqStatusNo" tabindex="340"/>
					<p class="open_spop">
						<a><span>조회</span></a>
					</p>
				</div>
				<div class="input_read">
					<html:text property="maWoResultMstrDetailDTO.changedEqStatusDesc" tabindex="341" readonly="true"/>
				</div>
			</div>
		</div>
		<!-- 현재위치 -->
		<div class="field ty1-2">
			<label class="check"><bean:message key="LABEL.currentEqLoc"/></label>
			<div class="multi_wrap">
				<div class="input_box">
					<html:text property="maWoResultMstrDetailDTO.currentEqLocNo" tabindex="350"/>
					<p class="open_spop">
						<a><span>조회</span></a>
					</p>
				</div>
				<div class="input_read">
					<html:text property="maWoResultMstrDetailDTO.currentEqLocDesc" tabindex="351" readonly="true"/>
				</div>
			</div>
		</div>
		<!-- 변경위치 -->
		<div class="field ty1-2">
			<label class="check"><bean:message key="LABEL.changedEqLoc"/></label>
			<div class="multi_wrap">
				<div class="input_box">
					<html:text property="maWoResultMstrDetailDTO.changedEqLocNo" tabindex="360"/>
					<p class="open_spop">
						<a><span>조회</span></a>
					</p>
				</div>
				<div class="input_box">
					<html:text property="maWoResultMstrDetailDTO.changedEqLocDesc" tabindex="361"/>
				</div>
			</div>
		</div>
		<!-- 생성시간 -->
		<div class="field">
			<label><bean:message key="LABEL.createDate"/></label>
			<div class="input_read" >
				<html:text property="maWoResultMstrDetailDTO.creDate" tabindex="370" readonly="true" styleClass="datetime"/>
			</div>
		</div>
		<!-- 수정시간 -->
		<div class="field">
			<label><bean:message key="LABEL.updateDate"/></label>
			<div class="input_read" >
				<html:text property="maWoResultMstrDetailDTO.updDate" tabindex="380" readonly="true" styleClass="datetime"/>
			</div>
		</div>
		<!-- 작업상세내용 -->
		<div class="field_long">
			<label><bean:message key="LABEL.woPerform"/></label>
			<div class="input_box">
				<html:textarea property="maWoResultMstrDetailDTO.perform" styleClass="ta50" tabindex="390" />
			</div>
		</div>
		<c:set var="filePath" value="enhance/${compName}/work/list/bm/maWoResultBmRplMstrDetail_${compNo}.jsp" />
		<c:if test="${udf:isExist(filePath)}">
			<c:import charEncoding="UTF-8" url="/enhance/${compName}/work/list/bm/maWoResultBmRplMstrDetail_${compNo}.jsp"></c:import>
		</c:if>
	</div> <!-- End of Form_box -->
</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
<%--===========================================================================
작업결과 - 상세(예방작업-점검)
author  kim21017
version $Id: maWoResultPmInsMstrDetail.jsp,v 1.0 2015/12/04 04:13:54 kim21017 Exp $
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
var equipDescAc;
var mainMngAc;
var deptAc;
var vendorDescAc;
var wkCtrAc;
var woAc;
var subEmpAc;

function loadPage() 
{	
// 	if(maWoResultMstrDetailForm.elements['maWoResultMstrCommonDTO.selectedWoType'].value=="IV"||
// 			maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woTypeId'].value=="IV"){
// 		document.getElementById("amtDiv").style.display="";
// 	}else{
// 		document.getElementById("amtDiv").style.display="none";
// 	}
	//결재보기에서 열었을때.
	//if(parent.currentPageId == "appReadyList") setDisableAll();
	
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
	
	equipDescAc = new autoC({"maWoResultMstrDetailDTO.equipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo,
 	   });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcResultMap({
        "maWoResultMstrDetailDTO.equipId":"equip_id"
        ,"maWoResultMstrDetailDTO.eqLocId":"eqloc_id"
        ,"maWoResultMstrDetailDTO.eqLocDesc":"eqLocDesc"
       	,"maWoResultMstrDetailDTO.equipNo":"item_no"
    });
    equipDescAc.setAcDConditionMap({
    	"dept_id":"maWoResultMstrDetailDTO.deptId"
    	,"deptDesc":"maWoResultMstrDetailDTO.deptDesc"
    });
    equipDescAc.setKeyName("maWoResultMstrDetailDTO.equipId"); 
    equipDescAc.init();
    
    mainMngAc = new autoC({"maWoResultMstrDetailDTO.empDesc":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
  	   });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maWoResultMstrDetailDTO.deptId"
    });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setKeyName("maWoResultMstrDetailDTO.empId");
    mainMngAc.setAcResultMap({
        "maWoResultMstrDetailDTO.empId":"emp_id"
    });
    mainMngAc.init();
    
    subEmpAc = new autoC({"maWoResultMstrDetailDTO.subEmpDesc":"emp_name"});
    subEmpAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
  	   });
    subEmpAc.setAcDConditionMap({
    	"dept_id" : "maWoResultMstrDetailDTO.deptId"
    });
    subEmpAc.setTable("TAEMP");
    subEmpAc.setKeyName("maWoResultMstrDetailDTO.subEmpId");
    subEmpAc.setAcResultMap({
        "maWoResultMstrDetailDTO.subEmpId":"emp_id"
    });
    subEmpAc.init();
    
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

	//교대조  AC
    acSysDesc("maWoResultMstrDetailDTO.shiftTypeDesc","maWoResultMstrDetailDTO.shiftTypeId","SHIFT_TYPE",true);
	//자가/외주 구분  AC
    acSysDesc("maWoResultMstrDetailDTO.selfVendorTypeDesc","maWoResultMstrDetailDTO.selfVendorType","SELF_VENDOR_TYPE",true);
    
    vendorDescAc = new autoC({"maWoResultMstrDetailDTO.vendorDesc":"description"});
    vendorDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_use":"Y"
  	   });
    vendorDescAc.setTable("TAVENDOR");
    vendorDescAc.setKeyName("maWoResultMstrDetailDTO.vendorId");
    vendorDescAc.setAcResultMap({
        "maWoResultMstrDetailDTO.vendorId":"vendor_id"
    });
    vendorDescAc.init();

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

	woAc = new autoC({"maWoResultMstrDetailDTO.parentWoDesc":"woDesc"});
	woAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo,
 	   "wo_status":"C"
 	   });
	woAc.setTable("TAWORKORDER");
	woAc.setAcResultMap({
        "maWoResultMstrDetailDTO.parentWoId":"wkorId"
    });
	woAc.setKeyName("maWoResultMstrDetailDTO.parentWoId"); 
	woAc.init();
	
	//청소여부  AC
    acSysDesc("maWoResultMstrDetailDTO.isCleaning","maWoResultMstrDetailDTO.isCleaningId","IS_USE",true);

	if(ckCreate(currentPageId)){
		goInput();
	}
	else 
	{
		goUpdate();
		goTabPage("maWoResultPmInsPointList");
		goTabPage("maWoResultPmInsCraftList");
	}
}

/**
 * Show/Hide Button
 */
function setBtnAsStatus()
{
	if(maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woStatusId'].value=="C"){
		setDisableAll();
		hideBtn("PDF");
		hideBtn("WOPLAN");
		hideBtn("CONFIRM");
		hideBtn("APPROVAL");
		
		showBtn("WOPDF");
		showBtn("REVERSE");

	}
	else if(maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woStatusId'].value=="PRWRA"
			|| maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woStatusId'].value=="PRWOA"){
		setEnableAll();
		showBtn("WOPDF");
		showBtn("PDF");
		showBtn("WOPLAN");
		showBtn("CONFIRM");
		
		hideBtn("REVERSE");
		hideBtn("APPROVAL");
	}
	else{
		setEnableAll();
		showBtn("WOPDF");
		showBtn("PDF");
		showBtn("WOPLAN");
		showBtn("CONFIRM");
		showBtn("APPROVAL");
		
		hideBtn("REVERSE");
	}
	
	//요청서가 없는 경우 요청서보기 버튼 비활성화
	var _id = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woReqId'].value;
	
	if(_id=="" || _id =="undefined")
	{
		hideBtn("REQDETAIL");
	}
}

/* 
 * 부위보기
 * TAPGLINKEDFUNC에 등록하여 사용한다.
 */
function goAsmbLink(_pageId)
{
	var pmId = '';
	var eqAsmbId = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.eqAsmbId'].value;
	var equipId = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.equipId'].value;

 	goAsmbDetail(pmId, eqAsmbId, equipId);
}
/*
 * 요청접수보기
 */
 function goWoreqLink(_pageId)
 {
 	var woNo = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woNo'].value;
 	goWoreqList(woNo);
 }
 
/* 
 * 전체설비보기
 * TAPGLINKEDFUNC에 등록하여 사용한다.
 */
function goEquipmentLink(_pageId)
{
	var equipId = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.equipId'].value;
	var eqctgType = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.eqctgType'].value;

	goEquipDetail(equipId, eqctgType);
}

/* 
 * 설비보기
 */
function goMachequipmentLink(_pageId)
{
	var equipId = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.equipId'].value;
	var eqctgType = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.eqctgType'].value;

	if(typeof equipId == "undefined" || equipId == "") {
		goMachEquipList();
		return;
	}else{
		goEquipDetail(equipId, eqctgType);
	}
}

/**
 * 추가작업서 발행하기
 */
function goWoex()
{
	var wkorId = maWoResultMstrDetailForm.elements['maWoResultMstrCommonDTO.wkOrId'].value;
	var equipId = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.equipId'].value;
	var equipDesc = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.equipDesc'].value;
	var wkOrDesc = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.wkOrDesc'].value;
	var eqLocDesc = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.eqLocDesc'].value;
	clearAll();
	goInput();
	
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.pWkOrId'].value = wkorId;
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.equipId'].value = equipId;
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.equipDesc'].value = equipDesc;
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.wkOrDesc'].value = wkOrDesc;
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.eqLocDesc'].value = eqLocDesc;

	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.startTime'].value   = "";
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.endTime'].value   = "";
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.workTime'].value   = "";
	
	goCloseAll(currentPageId);
}

function goUpdate(){
	//예방점검기준서에 의해 생성된 작업결과는 설비를 변경하지못함.
	if(maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woGenType'].value =="PMPLAN"){
		maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.equipDesc'].readOnly = true;
	    document.getElementById("equipDiv").className = "input_read"; 
	    document.getElementById("equipPopDiv").style.display = "none";
	}
}

function goInput()
{
	sequenceNextVal('SQAWKOR_ID');
	
	//작업계획보기 버튼 숨기기
	hideBtn("WOPLAN");
	//완료버튼 감추기.
	hideBtn("CONFIRM");
	hideBtn("PDF");
    
	if("<%=user.getShiftType()%>"!='null'){
		//교대조 세팅
		maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.shiftTypeId'].value = "<%=user.getShiftType()%>";
		maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.shiftTypeDesc'].value = "<%=user.getShiftTypeDesc()%>";
	}

	var selectedWoType = maWoResultMstrDetailForm.elements['maWoResultMstrCommonDTO.selectedWoType'].value;
	var selectedPmType = maWoResultMstrDetailForm.elements['maWoResultMstrCommonDTO.selectedPmType'].value;
	//작업종류 
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woTypeDesc'].value = selectedWoType;
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woTypeId'].value = selectedWoType;
	valSysDir('maWoResultMstrDetailDTO.woTypeId', 'maWoResultMstrDetailDTO.woTypeDesc', 'WO_TYPE', true);
	
	//작업형태 
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.pmTypeDesc'].value = selectedPmType;
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.pmTypeId'].value = selectedPmType;
	valSysDir('maWoResultMstrDetailDTO.pmTypeId', 'maWoResultMstrDetailDTO.pmTypeDesc', selectedWoType+'_TYPE', true);
	
	
	//작업상태 = P - 작업대기
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woStatusDesc'].value = "P";
	valSysDir('maWoResultMstrDetailDTO.woStatusId', 'maWoResultMstrDetailDTO.woStatusDesc', 'WO_STATUS', true);

	//자가/외주 설비종류가 투자인것은 외주로 셋팅, 나머지는 자가로 셋팅
	if(maWoResultMstrDetailForm.elements['maWoResultMstrCommonDTO.selectedWoType'].value=="IV"||
			maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woTypeId'].value=="IV"){
		maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.selfVendorType'].value = "VENDOR";
		maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.selfVendorTypeDesc'].value = "VENDOR";
		valSysDir('maWoResultMstrDetailDTO.selfVendorType', 'maWoResultMstrDetailDTO.selfVendorTypeDesc', 'SELF_VENDOR_TYPE', true);
	}else{
		maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.selfVendorType'].value = "SELF";
		maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.selfVendorTypeDesc'].value = "SELF";
		valSysDir('maWoResultMstrDetailDTO.selfVendorType', 'maWoResultMstrDetailDTO.selfVendorTypeDesc', 'SELF_VENDOR_TYPE', true);
	}
	
	//작업시작일자, 종료일자 넣기.
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.startDate'].value   = getToday();
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.endDate'].value   = getToday();
	//작업일자 넣기
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.wkorDate'].value   = getWorkDay();
	//작업시작시간(1시간전), 종료시간(현재시간) 넣기.
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.startTime'].value   = getMinusTime(false,1);
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.endTime'].value   = getTime(false);
	//부서
	if(maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.deptId'].value==""){
		maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.deptId'].value    = "<%=user.getDeptId()%>";
		maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.deptDesc'].value  = "<%=user.getDeptDesc()%>";
	}
	
	//사원
	if(maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.empId'].value==""){
		maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.empId'].value     = "<%=user.getEmpId()%>";
		maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.empDesc'].value   = "<%=user.getEmpName()%>";
	}
	setWorkTime();
	
// 	//청소여부
// 	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.isCleaningId'].value     = "N";
// 	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.isCleaning'].value     = "N";
// 	valSysDir('maWoResultMstrDetailDTO.isCleaningId', 'maWoResultMstrDetailDTO.isCleaning', 'IS_USE', true);

	if(maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.equipId'].value == "")
	{
		equipDescAc.openLov();
	}
	
}

/**
 * 작업필수점검항목 데이터 생성
 */
function createPointData(){
	maWoResultMstrDetailForm.strutsAction.value = '<%=MaWoResultMstrDetailAction.CREATE_POINT_DATA%>'; 
	 var actionUrl = contextPath + "/maWoResultMstrDetail.do";
		XMLHttpPost(actionUrl, FormQueryString(maWoResultMstrDetailForm), 'afterCreatePoint');
}

function afterCreatePoint(){
	maWoResultMstrDetailForm.strutsAction.value ='0';
		
	if(maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woTypeId'].value=="PMI"){
	}else if(maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woTypeId'].value=="PMW"){
	}else{
	}
	
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
	
	if(pageId == "maDocLibList" || pageId == "maWoResultPmInsDocLibList")
	{	
		maWoResultMstrDetailForm.elements['maDocLibCommonDTO.objectId'].value = maWoResultMstrDetailForm.elements['maWoResultMstrCommonDTO.wkOrId'].value;
		maWoResultMstrDetailForm.elements['maDocLibCommonDTO.objectType'].value = "WORESULT";  //WORESULT
		//maWoResultMstrDetailForm.elements['maDocLibCommonDTO.securGrade'].value = "3";  //보안등급-일반
		//maWoResultMstrDetailForm.elements['maDocLibCommonDTO.docCateg'].value = "DOC";  //문서타입-일반
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
 * 점검항목의 모든 결과값이 들어있는지 확인.
 */
var isValid = 0;

function checkPoint()
{
	isValid=0;
	
	var actionUrl = contextPath + "/maWoResultMstrDetail.do";
    var param =  "&strutsAction=" + '<%= MaWoResultMstrDetailAction.WO_RESULT_DETAIL_CHECKPOINT %>'
              +  "&" + FormQueryString(maWoResultMstrDetailForm);
    XMLHttpPostVal(actionUrl, param, 'setValidCheckPoint');
    
}
function setValidCheckPoint(ajaxXmlDoc)
{
	isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
	if(isValid == '0')
    {
		maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woNgPointId'].value = maWoResultMstrDetailForm.elements['maWoResultMstrCommonDTO.woNgPointId'].value;
		maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.updDate'].value = getNowDateTime(true); 
		 maWoResultMstrDetailForm.strutsAction.value = '<%=MaWoResultMstrDetailAction.WO_RESULT_DETAIL_COMPLETE%>';
		 var actionUrl = contextPath + "/maWoResultMstrDetail.do";
			XMLHttpPost(actionUrl, FormQueryString(maWoResultMstrDetailForm), 'afterConfirm');
    }else{
    	alertMessage1("<bean:message key='MESSAGE.MSG0112' />");
    	return;
    }
}

/**
 * 작업완료
 */
 function goConfirm(){
	 if(checkIsUpdate(document)){
		 alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
	 }else{
		 getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0017'/>", function(result){
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

				checkPoint();
			 }
			});
	 }
}
 /**
  * 완료후 호출
  */
 function afterConfirm(ajaxXmlDoc)
 {
	 alertMessage1("<bean:message key='MESSAGE.MSG0015'/>");
	//작업상태 = C - 작업완료
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woStatusId'].value = "C";
	valSysDirCode('maWoResultMstrDetailDTO.woStatusId', 'maWoResultMstrDetailDTO.woStatusDesc', 'WO_STATUS', true);
	
	//확정자명 = 로그인 유저
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.closeBy'].value = "<%=user.getEmpName()%>";
	if (parent.findGridRow)	parent.findGridRow(maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.wkOrId'].value);

	// 이상점검처리대상 - 작업결과 (목록) 에서 확정한 경우 확정 후 이상점검처리대상 상세 재조회
	if(typeof searchPage("maBdPointWoRsltList").findGridRow == "function" && parent.currentPageId == "maBdPointWoRsltList"){
		searchPage("maBdPointWoRsltList").findGridRow(maWoResultMstrDetailForm.elements['maWoResultMstrCommonDTO.woNgPointResId'].value, '');
			
		if(typeof searchPage("maBdPointDetail").checkStatus == "function" && parent.currentPageId == "maBdPointWoRsltList"){
			searchPage("maBdPointDetail").checkStatus();	
		}
	}
	 
	// 작업 확정 시 요청 상태 변경
	if(typeof searchPage("maWoReqDetail").checkStatus == "function"){
		searchPage("maWoReqDetail").checkStatus(maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woReqResId'].value);	
	}
	
	//요청-처리사항(목록)에서 팝업창을 열었다면 저장시 요청-처리사항(목록) 조회 호출 
	if(opener)
	{	
		if(opener.currentPageId == "maWoReqResList") opener.goSearch();
	} 	
	
	// 작업결과(목록) 상태 변경 
	if (parent.findGridRow)	
		parent.findGridRow(maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.wkOrId'].value);
	
	 setBtnAsStatus();
 }

/**
 * 작업완료 취소
 */
function goReverse(){
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
function afterReverse(ajaxXmlDoc)
{
 	alertMessage1("<bean:message key='MESSAGE.CMSG034'/>");
	//작업상태 = P - 작업대기
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woStatusId'].value = "P";
	valSysDirCode('maWoResultMstrDetailDTO.woStatusId', 'maWoResultMstrDetailDTO.woStatusDesc', 'WO_STATUS', true);
	
	if (parent.findGridRow)	parent.findGridRow(maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.wkOrId'].value);

	// 이상점검처리대상 - 작업결과 (목록) 에서 확정한 경우 확정취소 후 이상점검처리대상 상세 재조회
	if(typeof searchPage("maBdPointWoRsltList").findGridRow == "function"){
		searchPage("maBdPointWoRsltList").findGridRow(maWoResultMstrDetailForm.elements['maWoResultMstrCommonDTO.woNgPointResId'].value, '');
		
		if(typeof searchPage("maBdPointDetail").checkStatus == "function"){
			searchPage("maBdPointDetail").checkStatus();	
		}
	}
	 
	// 작업 확정 취소 시 요청접수 상태 변경
	if(typeof searchPage("maWoReqDetail").checkStatus == "function"){
		searchPage("maWoReqDetail").checkStatus(maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woReqResId'].value);	
	}
	
	//요청-처리사항(목록)에서 팝업창을 열었다면 저장시 요청-처리사항(목록) 조회 호출 
	if(opener)
	{	
		if(opener.currentPageId == "maWoReqResList") opener.goSearch();
	}
	
	// 작업결과(목록) 상태 변경 
	if (parent.findGridRow)	
		parent.findGridRow(maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.wkOrId'].value);
	
	setBtnAsStatus();
}

/**
 * 저장
 */ 
function goSave()
{
	if(maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.empDesc'].value == "") {
		maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.empId'].value = loginUser.empId;
		maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.empDesc'].value = loginUser.empName;
	}
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	if(maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.selfVendorType'].value == "VENDOR") //외주라면..
	{
		//거래처 필수 
//		if(checkRequireValue('maWoResultMstrDetailDTO.vendorDesc','<bean:message key="LABEL.recVendor"/>')) return;
	}
	
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
	 	
		// 이상점검처리대상 - 작업결과 (목록) 에서 저장한 경우
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
			hideBtn("CONFIRM");
		}
	});
}

/**
 * Time 세팅되고 호출되는 Callback
 */
function afterSetTime(_name)
{
	setWorkTime();
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
 
 function setWorkTime(){
	var startDate = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.startDate'].value;
 	var startTime = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.startTime'].value;
 	var endDate = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.endDate'].value;
 	var endTime = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.endTime'].value;

	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.workTime'].value = setNumberFormat(getMinInterval(maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.startDate'], maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.startTime'], maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.endDate'],maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.endTime']));
	
 }
 /**
  * Print 버튼 클릭
  */
  function goPrint()
  {
 	 reportCall('maWoResultMstrInsDetail','maWoResultMstrInsDetail', "<%=user.getCompNo()%>", "<%=user.getUserId()%>", "<%=user.getLangId()%>",maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.wkOrId'].value); 
  }
  
  function goWopdf(){
 	 goPrint();
  }
  
  /**
   * 승인요청
   */
   function goApproval()
   {
  	 if(checkIsUpdate(document)){
  		 alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
  	 }else{
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
 	goClose('maWoResultPmInsMstrDetail');
  }
  
  /**
   * 작업계획보기
   */
  function goWoplan()
  {
      var id = "";
      var isExist = '0';
      var actionUrl = contextPath + "/maWoResultMstrDetail.do";
      var param =  "&strutsAction=" + '<%= MaWoResultMstrDetailAction.WO_PLAN_CHECK %>'
                 +  "&" + FormQueryString(maWoResultMstrDetailForm);
      XMLHttpPostVal(actionUrl, param, 'afterGoWoplan');
  }
  
  function afterGoWoplan(ajaxXmlDoc)
  {
      
       isExist = parseXmlDoc(ajaxXmlDoc, 'DESC');

       if(isExist == '0')
       {
           alertMessage1('<bean:message key="MESSAGE.CSMG013"/>');
           return;
       }else{
           id = maWoResultMstrDetailForm.elements['maWoResultMstrCommonDTO.wkOrId'].value;
           var wkorId = id;
           var woparam = "woPlanDetail";
           
           var url   = contextPath + "/"+woparam+".do";
        
           var popWidth = 1010;
           var popHeight = 640;
        
           // pop up이 중앙에 위치하게 한다.
           var TopPosition  = (screen.height/2 - popHeight/2);
           var LeftPosition = (screen.width/2 - popWidth/2);
        
           var pos = "width=" + popWidth + ",height=" + popHeight + "" +
                     ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
           pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
           
           var param = "strutsAction=1001&woPlanCommonDTO.wkOrId="+ wkorId;
           
           openWindowWithPost(url, "WOPLAN_DETAIL", param, pos);
       }
  }  
  
//요청서 보기
function goReqdetail()
{
	id = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woReqId'].value;
	
	if(id=="" || id =="undefined")
	{
       alertMessage1('<bean:message key="MESSAGE.CSMG013"/>');
       return;
	}
	
   var woReqId = id;
   var woparam = "reqWorkDetail";
   
   var url   = contextPath + "/"+woparam+".do";

   var popWidth = 1010;
   var popHeight = 640;

   // pop up이 중앙에 위치하게 한다.
   var TopPosition  = (screen.height/2 - popHeight/2);
   var LeftPosition = (screen.width/2 - popWidth/2);

   var pos = "width=" + popWidth + ",height=" + popHeight + "" +
             ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
   pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
   
   var param = "strutsAction=8001&reqWorkCommonDTO.woReqId="+ woReqId;
   
   openWindowWithPost(url, "WOREQ_DETAIL", param, pos);
}


/* audit Trail */
function goAudtrailLink()
{	
	var objectId = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.wkOrId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}

/*
 * 고장영향성평가 보기
 */
function goFmeaLink()
{
	var equipId = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.equipId'].value;
	var equipDesc = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.equipDesc'].value;
	var wkorId = maWoResultMstrDetailForm.elements['maWoResultMstrCommonDTO.wkOrId'].value;

	if(typeof equipId=="undefined") return;

	goFmeaList(equipId, equipDesc, wkorId);
}

/*
 * 기준서 보기 WOPMPRESULT
 */
function goPmstdLink()
{
	var fileName = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.pmParam'].value;
	var pmId     = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.pmId'].value;

	goPmstdList(pmId, fileName);
}

/*
 * 작업결과서 출력
 */
function goWopdfLink()
{
	goPrint();
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
	<html:hidden property="maWoResultMstrCommonDTO.woNgPointId" />
	<html:hidden property="maWoResultMstrCommonDTO.woNgPointResId" />
	<html:hidden property="maWoResultMstrDetailDTO.wkOrId" />
	<html:hidden property="maWoResultMstrDetailDTO.equipId" />
	<html:hidden property="maWoResultMstrDetailDTO.woTypeId" />
	<html:hidden property="maWoResultMstrDetailDTO.woStatusId" />
	<html:hidden property="maWoResultMstrDetailDTO.pmTypeId" />
	<html:hidden property="maWoResultMstrDetailDTO.deptId" />
	<html:hidden property="maWoResultMstrDetailDTO.empId" />
 	<html:hidden property="maWoResultMstrDetailDTO.shiftTypeId" />
	<html:hidden property="maWoResultMstrDetailDTO.vendorId" />
	<html:hidden property="maWoResultMstrDetailDTO.selfVendorType" />
	<html:hidden property="maWoResultMstrDetailDTO.pWkOrId" />  <!-- 상위Work Order ID -->
 	<html:hidden property="maWoResultMstrDetailDTO.woGenType" />
 	<html:hidden property="maWoResultMstrDetailDTO.eqLocId" />
 	<html:hidden property="maWoResultMstrDetailDTO.wkCtrId" />
 	<html:hidden property="maWoResultMstrDetailDTO.parentWoId" />
 	<html:hidden property="maWoResultMstrDetailDTO.subEmpId" />
 	<html:hidden property="maWoResultMstrCommonDTO.woReqResId" />
 	<html:hidden property="maWoResultMstrDetailDTO.woReqResId" />
 	<html:hidden property="maWoResultMstrDetailDTO.isCleaningId" />
 	<html:hidden property="maWoResultMstrDetailDTO.eqAsmbId" />
 	<html:hidden property="maWoResultMstrDetailDTO.woNgPointId" />
	<html:hidden property="maWoResultMstrDetailDTO.woNgPointResId" />
 	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
 	<html:hidden property="maDocLibCommonDTO.objectType" />
 	<html:hidden property="maDocLibCommonDTO.securGrade" />
 	<html:hidden property="maDocLibCommonDTO.docCateg" />
 	<html:hidden property="maDocLibCommonDTO.description" />
 	<html:hidden property="maWoResultMstrCommonDTO.woReqId" />
 	<html:hidden property="maWoResultMstrCommonDTO.invtlistId" />
 	<html:hidden property="maWoResultMstrDetailDTO.woReqId" />
 	<html:hidden property="maWoResultMstrDetailDTO.eqctgType" />
 	<html:hidden property="maWoResultMstrDetailDTO.pmId" />
 	<html:hidden property="maWoResultMstrDetailDTO.pmParam" />
 	
 	<html:hidden property="appReqCommonDTO.objectId"/>
	<html:hidden property="appReqCommonDTO.apprType"/>
	
 	<html:hidden property="maWoReqCommonDTO.woReqId"/>
	
	<div class="article_box">
		<div class="form_box">
			<!-- 작업No -->
			<div class="field">
				<label><bean:message key="LABEL.woNo"/></label>
				<div class="input_read" id="woNoDiv">
					<html:text property="maWoResultMstrDetailDTO.woNo" tabindex="10" readonly="true"/>
				</div>
			</div>
			<!-- WO상태 -->
			<div class="field">
				<label><bean:message key="LABEL.woStatus"/></label>
				<div class="input_read" id="woStatusDescDiv">
					<html:text property="maWoResultMstrDetailDTO.woStatusDesc" tabindex="20" />
				</div>
			</div>
			<!-- 작업종류  -->
			<div class="field">
				<label><bean:message key="LABEL.woType"/></label>
				<div class="input_read">
					<html:text property="maWoResultMstrDetailDTO.woTypeDesc" tabindex="30" readonly="true" />
				</div>
			</div>
			<!-- 작업형태  -->
			<div class="field">
				<label><bean:message key="LABEL.pmType"/></label>
				<div class="input_read">
					<html:text property="maWoResultMstrDetailDTO.pmTypeDesc" tabindex="40" readonly="true" />
				</div>
			</div>
			<!-- 위치 -->
			<div class="field_long">
				<label><bean:message key="LABEL.location"/></label>
				<div class="input_read" id="eqLocDescDiv">
					<html:text property="maWoResultMstrDetailDTO.eqLocDesc" tabindex="50" readonly="true"/>
				</div>
			</div>
			<div class="field_divide"></div>
			<!-- 작업명 -->
			<div class="field_long">
				<label class="check"><bean:message key="LABEL.woName"/></label>
				<div class="input_box">
					<html:text property="maWoResultMstrDetailDTO.wkOrDesc" tabindex="60" />
				</div>
			</div>
			<!-- 설비코드 -->
			<div class="field">
				<label><bean:message key="LABEL.equipNo"/></label>
				<div class="input_read">
					<html:text property="maWoResultMstrDetailDTO.equipNo" tabindex="65" readonly="true"/>
				</div>
			</div>
			<!-- 설비 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.equipment"/></label>
				<div class="input_box" id="equipDiv">
					<html:text property="maWoResultMstrDetailDTO.equipDesc" tabindex="70" />
					<p class="open_spop" id="equipPopDiv">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<div class="field_blank"></div>
			<!-- 작업일자 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.wkorDate"/></label>
				<div class="input_box input_carendar">
					<html:text property="maWoResultMstrDetailDTO.wkorDate" tabindex="80" />
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			<!-- 담당부서 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.manageDept"/></label>
				<div class="input_box">
					<html:text property="maWoResultMstrDetailDTO.deptDesc" tabindex="90"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<div class="field">
				<label><bean:message key="LABEL.wkCtr"/></label>
				<div class="input_box">
					<html:text property="maWoResultMstrDetailDTO.wkCtrDesc" tabindex="95"/>
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 교대조. -->
			<div class="field">
				<label><bean:message key="LABEL.shiftType"/></label>
				<div class="input_box">
					<html:text property="maWoResultMstrDetailDTO.shiftTypeDesc" tabindex="100" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 담당자 -->
			<div class="field">
				<label class="lastcheck"><bean:message key="LABEL.manager"/></label>
				<div class="input_box">
					<html:text property="maWoResultMstrDetailDTO.empDesc" tabindex="110"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 부 담당자 -->
			<div class="field">
				<label><bean:message key="LABEL.managerSub"/></label>
				<div class="input_box">
					<html:text property="maWoResultMstrDetailDTO.subEmpDesc" tabindex="115"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 자가/외주  -->
			<div class="field">
				<label><bean:message key="LABEL.selfVendorType"/></label>
				<div class="input_box">
					<html:text property="maWoResultMstrDetailDTO.selfVendorTypeDesc" tabindex="120" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 거래처 -->
			<div class="field">
				<label><bean:message key="LABEL.vendor"/></label>
				<div class="input_box">
                    <html:text property="maWoResultMstrDetailDTO.vendorDesc" tabindex="130" />
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
			</div>
			<!-- 거래처명 -->
			<div class="field">
				<label><bean:message key="LABEL.vendorName"/></label>
				<div class="input_box">
                    <html:text property="maWoResultMstrDetailDTO.vendorName" tabindex="100" />
                </div>
			</div>
			<div class="field_long_blank"></div>
			
			<!-- 작업시작시간 -->
			<div class="field">
				<label class="lastcheck"><bean:message key="LABEL.woFromTime"/></label>
				<div class="datetime_wrap">
					<div class="input_box input_carendar">
						<html:text property="maWoResultMstrDetailDTO.startDate"  tabindex="140" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box">
						<html:text property="maWoResultMstrDetailDTO.startTime"   tabindex="145"/>
						<p class="open_time"><span>시간</span></p>
					</div>
				</div>
			</div>
        	 <!-- 원인 W/O # -->
        	 <div class="field">
        	 	<label><bean:message key="LABEL.parentWoNo"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maWoResultMstrDetailDTO.parentWoDesc" tabindex="148" />
                    <p id="wnameSchBtn" class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
        	 	</div>
        	 </div>
			<!-- 작업종료시간-->
			<div class="field">
				<label class="lastcheck"><bean:message key="LABEL.woToTime"/></label>
				<div class="datetime_wrap">
					<div class="input_box input_carendar">
						<html:text property="maWoResultMstrDetailDTO.endDate" tabindex="150" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box">
						<html:text property="maWoResultMstrDetailDTO.endTime" tabindex="155" />
						<p class="open_time"><span>시간</span></p>
					</div>
				</div>
			</div>
			<!-- 소요시간 -->
			<div class="field">
				<label><bean:message key="LABEL.woTimeMin"/></label>
				<div class="input_box">
					<html:text property="maWoResultMstrDetailDTO.workTime" tabindex="160" styleClass="num"/>
				</div>
			</div>
			
			<!-- 금액 -->
			<div class="field" id="amtDiv">
				<label><bean:message key="LABEL.amt"/></label>
				<div class="input_box">
					<html:text property="maWoResultMstrDetailDTO.laborAmt" tabindex="165" styleClass="num"/>
				</div>
			</div>
			<!-- 확정자 -->
			<div class="field">
				<label><bean:message key="LABEL.closeBy"/></label>
				<div class="input_read" >
					<html:text property="maWoResultMstrDetailDTO.closeBy" tabindex="170" readonly="true" />
				</div>
			</div>
			<!-- 생성시간 -->
			<div class="field">
				<label><bean:message key="LABEL.createDate"/></label>
				<div class="input_read" >
					<html:text property="maWoResultMstrDetailDTO.creDate" tabindex="175" readonly="true" styleClass="datetime"/>
				</div>
			</div>
			<!-- 수정시간 -->
			<div class="field">
				<label><bean:message key="LABEL.updateDate"/></label>
				<div class="input_read" >
					<html:text property="maWoResultMstrDetailDTO.updDate" tabindex="180" readonly="true" styleClass="datetime"/>
				</div>
			</div>
			<!-- 청소여부 -->
			<div class="field">
				<label><bean:message key="LABEL.isCleaning"/></label>
				<div class="input_box">
					<html:text property="maWoResultMstrDetailDTO.isCleaning" tabindex="185"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 작업상세내용 -->
			<div class="field_long">
				<label><bean:message key="LABEL.woPerform"/></label>
				<div class="input_box">
					<html:textarea property="maWoResultMstrDetailDTO.perform" styleClass="ta50" tabindex="190" />
				</div>
			</div>
			<c:set var="filePath" value="enhance/${compName}/work/list/pmi/maWoResultPmInsMstrDetail_${compNo}.jsp" />
			<c:if test="${udf:isExist(filePath)}">
				<c:import charEncoding="UTF-8" url="/enhance/${compName}/work/list/pmi/maWoResultPmInsMstrDetail_${compNo}.jsp"></c:import>
			</c:if>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
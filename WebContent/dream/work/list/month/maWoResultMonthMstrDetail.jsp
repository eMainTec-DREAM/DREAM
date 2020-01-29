<%--===========================================================================
작업결과(월간작업계획) - 상세
author  kim21017
version $Id: maWoResultMonthMstrDetail.jsp,v 1.0 2015/12/04 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
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
//자동완성
var empAc;
var deptAc;
var equipAc;
var vendorDescAc;
var subEmpAc;

function loadPage() 
{	
	//결재보기에서 열었을때.
	//if(parent.currentPageId == "appReadyList") setDisableAll();
	
	setForUpdate();
	setBtnAsStatus();
	
	if(ckCreate(currentPageId)){
		goInput();
	}
	else 
	{
		goUpdate();
		//예방작업일 때
		if(maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woTypeId'].value=="PMI"){
			goTabPage("maWoResultMonthPointList");
			goTabPage("maWoResultMonthCraftList");
		}else if(maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woTypeId'].value=="PMW"){
			goTabPage("maWoResultMonthPartList");
			goTabPage("maWoResultMonthCraftList");
		}else{
			goTabPage("maWoResultMonthFailDetail");
			goTabPage("maWoResultMonthCraftList");
		}
	}

	setDisable(document.getElementById("woTypeDiv"));
	setDisable(document.getElementById("pmTypeDiv"));
	
	setTitle("maWoResultMstrDetailDTO.woNo","maWoResultMstrDetailDTO.wkOrDesc");
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woStatusDesc'].readOnly="true";

	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woReqResId'].value = maWoResultMstrDetailForm.elements['maWoResultMstrCommonDTO.woReqResId'].value;

	$("input[name='maWoResultMstrDetailDTO.endDate']").blur( function(){
		setWorkTime(); 
    });
	$("input[name='maWoResultMstrDetailDTO.endTime']").blur( function(){
		setWorkTime(); 
    });
    
    empAc = new autoC({"maWoResultMstrDetailDTO.empDesc":"emp_name"});
    empAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
  	   });
    empAc.setTable("TAEMP");
    empAc.setKeyName("maWoResultMstrDetailDTO.empId");
    empAc.setAcResultMap({
        "maWoResultMstrDetailDTO.empId":"emp_id"
    });
    empAc.init();
    
    subEmpAc = new autoC({"maWoResultMstrDetailDTO.subEmpDesc":"emp_name"});
    subEmpAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
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
    
	equipAc = new autoC({"maWoResultMstrDetailDTO.equipDesc":"description"});
    equipAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo,
 	   "EQ_STATUS":"R+S"
 	   });
    equipAc.setTable("TAEQUIPMENT");
    equipAc.setAcResultMap({
        "maWoResultMstrDetailDTO.equipId":"equip_id"
        ,"maWoResultMstrDetailDTO.eqLocId":"eqloc_id"
        ,"maWoResultMstrDetailDTO.eqLocDesc":"eqLocDesc"
       	,"maWoResultMstrDetailDTO.equipNo":"item_no"
    });
    equipAc.setKeyName("maWoResultMstrDetailDTO.equipId"); 
    equipAc.init();

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
    
	//자가/외주  AC
    acSysDesc("maWoResultMstrDetailDTO.selfVendorTypeDesc","maWoResultMstrDetailDTO.selfVendorType","SELF_VENDOR_TYPE",true);
	//작업종류 AC
    acSysDesc("maWoResultMstrDetailDTO.woTypeDesc","maWoResultMstrDetailDTO.woTypeId","WO_TYPE",true);
	//작업유형 AC
    acSysDesc("maWoResultMstrDetailDTO.pmTypeDesc","maWoResultMstrDetailDTO.pmTypeId","PM_TYPE",true);
	
}

/**
 * Show/Hide Button
 */
function setBtnAsStatus()
{
	if(maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woStatusId'].value=="C"){
		setDisableAll();
		hideBtn("PDF");
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
		showBtn("CONFIRM");
		
		hideBtn("REVERSE");
		hideBtn("APPROVAL");
	}
	else{
		setEnableAll();
		showBtn("WOPDF");
		showBtn("PDF");
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

function goUpdate(){
}

function goInput()
{
	sequenceNextVal('SQAWKOR_ID');
	
	//완료버튼 감추기.
	hideBtn("CONFIRM");
	hideBtn("PDF");
	
	openSelectType();
	//작업상태 = P - 작업대기
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woStatusDesc'].value = "P";
	valSysDir('maWoResultMstrDetailDTO.woStatusId', 'maWoResultMstrDetailDTO.woStatusDesc', 'WO_STATUS', true);

	//자가/외주 = SELF - 자가
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.selfVendorType'].value = "SELF";
	valSysDir('maWoResultMstrDetailDTO.selfVendorType', 'maWoResultMstrDetailDTO.selfVendorTypeDesc', 'SELF_VENDOR_TYPE', true);
	
	//작업시작일자, 종료일자 넣기.
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.startDate'].value   = getToday();
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.endDate'].value   = getToday();
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
	
}

function setAfterSelect(returnArray){
	var woType = returnArray[0];
	var pmType = returnArray[1];
	
	//작업종류 
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woTypeDesc'].value = woType;
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woTypeId'].value = woType;
	valSysDir('maWoResultMstrDetailDTO.woTypeId', 'maWoResultMstrDetailDTO.woTypeDesc', 'WO_TYPE', true);
	
	//작업형태 
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.pmTypeDesc'].value = pmType;
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.pmTypeId'].value = pmType;
	valSysDir('maWoResultMstrDetailDTO.pmTypeId', 'maWoResultMstrDetailDTO.pmTypeDesc', 'PM_TYPE', true);
	
	createPointData();
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
		
	//예방작업일 때
	if(maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woTypeId'].value=="PMI"){
		goTabPage("maWoResultMonthPointList");
		goTabPage("maWoResultMonthCraftList");
	}else if(maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woTypeId'].value=="PMW"){
		goTabPage("maWoResultMonthPartList");
		goTabPage("maWoResultMonthCraftList");
	}else{
		goTabPage("maWoResultMonthFailDetail");
		goTabPage("maWoResultMonthCraftList");
	}
	
}

function setSequenceVal(sequenceVal)
{
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woNo'].value = sequenceVal;
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.wkOrId'].value = sequenceVal;
	maWoResultMstrDetailForm.elements['maWoResultMstrCommonDTO.wkOrId'].value = sequenceVal;

}
/**
 * 작업종류& 작업형태 선택창 열기
 */
function openSelectType(){
	width  = 850;
	height = 540;
	sleft = (screen.width - width) / 2;
	stop = (screen.height - height) / 2;
	features = "left=" + sleft + ",top=" + stop;
	
	var param = "strutsAction=1001";
	var url =  contextPath + "/maWoResultSelect.do";
	
	openLayerPopup("maWoResultSelect", param);
	
}

function goOpen(pageId)
{
	goTabPage(pageId);
}

function goTabPage(pageId)
{
	var form = document.maWoResultMstrDetailForm;
	
	if(pageId == "maDocLibList" || pageId == "maWoMonthDocLibList")
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
				
				 maWoResultMstrDetailForm.strutsAction.value = '<%=MaWoResultMstrDetailAction.WO_RESULT_DETAIL_COMPLETE%>';
				 var actionUrl = contextPath + "/maWoResultMstrDetail.do";
					XMLHttpPost(actionUrl, FormQueryString(maWoResultMstrDetailForm), 'afterConfirm');
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
	 if (parent.findGridRow)	parent.findGridRow(maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.wkOrId'].value);
	 
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
		if(checkRequireValue('maWoResultMstrDetailDTO.vendorDesc','<bean:message key="LABEL.recVendor"/>')) return;
	}
	
	//strutsAction 셋팅.
	if(ckCreate(currentPageId)) maWoResultMstrDetailForm.strutsAction.value = '<%=MaWoResultMstrDetailAction.WO_RESULT_DETAIL_INPUT%>';
	else maWoResultMstrDetailForm.strutsAction.value = '<%=MaWoResultMstrDetailAction.WO_RESULT_DETAIL_UPDATE%>';
	
	if(M$('maWoResultMstrDetailDTO.workTime').value == '') setWorkTime();
	
	var actionUrl = contextPath + "/maWoResultMstrDetail.do";

	ajaxPost(actionUrl, FormQueryString(maWoResultMstrDetailForm))
	.done(function(d){
		maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.wkOrId'].value = maWoResultMstrDetailForm.elements['maWoResultMstrCommonDTO.wkOrId'].value;
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
	setWorkTime();
}
 
 function setWorkTime(){
	var startDate = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.startDate'].value;
 	var startTime = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.startTime'].value;
 	var endDate = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.endDate'].value;
 	var endTime = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.endTime'].value;

 	if(startDate != "" && startTime != "" && endDate != "" && endTime != "")
 	{
		var sDate = new Date();
		sDate.setFullYear(startDate.substring(0,4));
		sDate.setMonth(Number(startDate.substring(5,7)) -1);
		sDate.setDate(startDate.substring(8,10));
		sDate.setHours(startTime.substring(0,2));
		sDate.setMinutes(startTime.substring(3,5));
		var eDate = new Date();
		eDate.setFullYear(endDate.substring(0,4));
		eDate.setMonth(Number(endDate.substring(5,7)) -1);
		eDate.setDate(endDate.substring(8,10));
		eDate.setHours(endTime.substring(0,2));
		eDate.setMinutes(Number(endTime.substring(3,5))+1);
		
		
		var tempTime = Math.floor((eDate.getTime() - sDate.getTime())/60000);
		
		maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.workTime'].value = tempTime;
 	}
 }
 /**
  * Print 버튼 클릭
  */
  function goPrint()
  {
 	 reportCall('maWoResultMstrDetail','maWoResultMstrDetail', "<%=user.getCompNo()%>", "<%=user.getUserId()%>", "<%=user.getLangId()%>",maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.wkOrId'].value); 
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
 	goClose('maWoResultMonthMstrDetail');
  }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maWoResultMstrDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="maWoResultMstrCommonDTO.wkOrId" />
	<html:hidden property="maWoResultMstrDetailDTO.wkOrId" />
	<html:hidden property="maWoResultMstrDetailDTO.equipId" />
	<html:hidden property="maWoResultMstrDetailDTO.eqAsmbId" />
	<html:hidden property="maWoResultMstrDetailDTO.woTypeId" />
	<html:hidden property="maWoResultMstrDetailDTO.woStatusId" />
	<html:hidden property="maWoResultMstrDetailDTO.pmTypeId" />
	<html:hidden property="maWoResultMstrDetailDTO.deptId" />
	<html:hidden property="maWoResultMstrDetailDTO.empId" />
	<html:hidden property="maWoResultMstrDetailDTO.vendorId" />
	<html:hidden property="maWoResultMstrDetailDTO.selfVendorType" />
	<html:hidden property="maWoResultMstrDetailDTO.pWkOrId" />  <!-- 상위Work Order ID -->
 	<html:hidden property="maWoResultMstrDetailDTO.subEmpId" />
 	<html:hidden property="maWoResultMstrCommonDTO.woReqResId" />
 	<html:hidden property="maWoResultMstrDetailDTO.woReqResId" />
 	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
 	<html:hidden property="maDocLibCommonDTO.objectType" />
 	<html:hidden property="maDocLibCommonDTO.securGrade" />
 	<html:hidden property="maDocLibCommonDTO.docCateg" />
 	<html:hidden property="maDocLibCommonDTO.description" />
 	<html:hidden property="maWoResultMstrCommonDTO.woReqId" />
 	
 	<html:hidden property="appReqCommonDTO.objectId"/>
	<html:hidden property="appReqCommonDTO.apprType"/>
 
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
			<div class="field" id="woTypeDiv">
				<label class="check"><bean:message key="LABEL.woType"/></label>
				<div class="input_read">
					<html:text property="maWoResultMstrDetailDTO.woTypeDesc" tabindex="60" readonly="true" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 작업형태  -->
			<div class="field" id="pmTypeDiv">
				<label class="check"><bean:message key="LABEL.pmType"/></label>
				<div class="input_read">
					<html:text property="maWoResultMstrDetailDTO.pmTypeDesc" tabindex="80" readonly="true" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 설비 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.equipment"/></label>
				<div class="input_box">
					<html:text property="maWoResultMstrDetailDTO.equipDesc" tabindex="30" />
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 위치 -->
			<div class="field">
				<label><bean:message key="LABEL.location"/></label>
				<div class="input_read" id="eqLocDescDiv">
					<html:text property="maWoResultMstrDetailDTO.eqLocDesc" tabindex="40" readonly="true"/>
				</div>
			</div>
			<!-- 작업명 -->
			<div class="field_long">
				<label class="check"><bean:message key="LABEL.woName"/></label>
				<div class="input_box">
					<html:text property="maWoResultMstrDetailDTO.wkOrDesc" tabindex="50" />
				</div>
			</div>
			<!-- 설비코드 -->
			<div class="field">
				<label><bean:message key="LABEL.equipNo"/></label>
				<div class="input_read">
					<html:text property="maWoResultMstrDetailDTO.equipNo" tabindex="65" readonly="true"/>
				</div>
			</div>
			<!-- 담당부서 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.manageDept"/></label>
				<div class="input_box">
					<html:text property="maWoResultMstrDetailDTO.deptDesc" tabindex="70"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 담당자 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.manager"/></label>
				<div class="input_box">
					<html:text property="maWoResultMstrDetailDTO.empDesc" tabindex="90" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 부 담당자 -->
			<div class="field">
				<label><bean:message key="LABEL.managerSub"/></label>
				<div class="input_box">
					<html:text property="maWoResultMstrDetailDTO.subEmpDesc" tabindex="95"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 작업시작시간 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.woFromTime"/></label>
				<div class="datetime_wrap">
					<div class="input_box input_carendar">
						<html:text property="maWoResultMstrDetailDTO.startDate"  tabindex="100" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box">
						<html:text property="maWoResultMstrDetailDTO.startTime"   tabindex="120"/>
						<p class="open_time"><span>시간</span></p>
					</div>
				</div>
			</div>
			<!-- 작업종료시간-->
			<div class="field">
				<label class="check"><bean:message key="LABEL.woToTime"/></label>
				<div class="datetime_wrap">
					<div class="input_box input_carendar">
						<html:text property="maWoResultMstrDetailDTO.endDate" tabindex="110" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box">
						<html:text property="maWoResultMstrDetailDTO.endTime"  tabindex="130" />
						<p class="open_time"><span>시간</span></p>
					</div>
				</div>
			</div>
			<!-- 소요시간 -->
			<div class="field">
				<label><bean:message key="LABEL.woTimeMin"/></label>
				<div class="input_box">
					<html:text property="maWoResultMstrDetailDTO.workTime" tabindex="140" styleClass="num"/>
				</div>
			</div>
			<!-- 자가/외주  -->
			<div class="field">
				<label><bean:message key="LABEL.selfVendorType"/></label>
				<div class="input_box">
					<html:text property="maWoResultMstrDetailDTO.selfVendorTypeDesc" tabindex="60" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 거래처 -->
			<div class="field">
				<label><bean:message key="LABEL.vendor"/></label>
				<div class="input_box">
                    <html:text property="maWoResultMstrDetailDTO.vendorDesc" tabindex="30" 
                            onblur="valVendorDesc('maWoResultMstrDetailDTO.vendorId','','maWoResultMstrDetailDTO.vendorDesc', '', '', '', '', '', true);"/>
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
			<!-- 작업상세내용 -->
			<div class="field_long">
				<label><bean:message key="LABEL.woPerform"/></label>
				<div class="input_box">
					<html:textarea property="maWoResultMstrDetailDTO.perform" styleClass="ta50" tabindex="150" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
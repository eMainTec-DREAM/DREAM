<%--===========================================================================
작업결과 - 상세(자재,작업자,첨부파일) (작업종류추가 페이지에서 추가되는 코드에 페이지로 들어감)
author  kim21017
version $Id: maWoResultBmBaseMstrDetail.jsp,v 1.0 2015/12/04 04:13:54 kim21017 Exp $
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

/** 자동완성 변수  */
var equipDescAc;
var mainMngAc;
var eqAsmbAc;
var deptAc;
var shiftTypeAc;
var selfVendorAc;
var vendorDescAc;
var fsDescAc;
var moDescAc;
var caCdDesc;
var reCdDesc;

function loadPage() 
{	
// 	if(maWoResultMstrDetailForm.elements['maWoResultMstrCommonDTO.selectedWoType'].value=="IV"||
// 			maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woTypeId'].value=="IV"){
// 		document.getElementById("amtDiv").style.display="";
// 	}else{
// 		document.getElementById("amtDiv").style.display="none";
// 	}
	
	setForUpdate();
	setBtnAsStatus();
	
	//결재보기에서 열었을때.
	if(parent.currentPageId == "appReadyList") setDisableAll();
	
	if(ckCreate(currentPageId)){
		goInput();
	}
	else 
	{
		goUpdate();
		goTabPage("maWoResultBmRplCraftList");
		goTabPage("maWoResultBmRplPartList");
	}
	
	setTitle("maWoResultMstrDetailDTO.woNo","maWoResultMstrDetailDTO.wkOrDesc");
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woStatusDesc'].readOnly="true";

	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woReqResId'].value = maWoResultMstrDetailForm.elements['maWoResultMstrCommonDTO.woReqResId'].value;
	
	equipDescAc = new autoC({"maWoResultMstrDetailDTO.equipDesc":"a.description"});
    equipDescAc.setAcConditionMap({
 	   "a.comp_no":loginUser.compNo,
 	   "CUSTOM":"EQ_STATUS IN('R','S')"
 	   });
    equipDescAc.setTable("TAEQUIPMENT a");
    equipDescAc.setAcResultMap({
        "maWoResultMstrDetailDTO.equipId":"a.equip_id"
        ,"maWoResultMstrDetailDTO.eqLocId":"a.eqloc_id"
        ,"maWoResultMstrDetailDTO.eqLocDesc":"(SELECT b.full_desc FROM TAEQLOC b WHERE a.comp_no = b.comp_no AND a.eqloc_id = b.eqloc_id)"
       	,"maWoResultMstrDetailDTO.equipNo":"item_no"
    });
    equipDescAc.setKeyName("maWoResultMstrDetailDTO.equipId"); 
    equipDescAc.init();
    
    mainMngAc = new autoC({"maWoResultMstrDetailDTO.empDesc":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setKeyName("maWoResultMstrDetailDTO.empId");
    mainMngAc.setAcResultMap({
        "maWoResultMstrDetailDTO.empId":"emp_id"
    });
    mainMngAc.init();
    
    eqAsmbAc = new autoC({"maWoResultMstrDetailDTO.eqAsmbDesc":"full_desc"});
    eqAsmbAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"equip_id":maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.equipId'].value,
  	   });
    eqAsmbAc.setTable("TAEQASMB");
    eqAsmbAc.setKeyName("maWoResultMstrDetailDTO.eqAsmbId");
    eqAsmbAc.setAcResultMap({
        "maWoResultMstrDetailDTO.eqAsmbId":"eqasmb_id"
    });
    eqAsmbAc.init();
    
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
    
    shiftTypeAc = new autoC({"maWoResultMstrDetailDTO.shiftTypeDesc":"description"});
    shiftTypeAc.setAcConditionMap({
        	"list_type":"SHIFT_TYPE",
        	"is_use":"Y"
  	   });
    shiftTypeAc.setTable("TACDSYSD");
    shiftTypeAc.setKeyName("maWoResultMstrDetailDTO.shiftTypeId");
    shiftTypeAc.setAcResultMap({
        "maWoResultMstrDetailDTO.shiftTypeId":"cdsysd_no"
    });
    shiftTypeAc.init();
    
    selfVendorAc = new autoC({"maWoResultMstrDetailDTO.selfVendorTypeDesc":"description"});
    selfVendorAc.setAcConditionMap({
        	"list_type":"SELF_VENDOR_TYPE",
        	"is_use":"Y"
  	   });
    selfVendorAc.setTable("TACDSYSD");
    selfVendorAc.setKeyName("maWoResultMstrDetailDTO.selfVendorType");
    selfVendorAc.setAcResultMap({
        "maWoResultMstrDetailDTO.selfVendorType":"cdsysd_no"
    });
    selfVendorAc.init();
    
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
    
    fsDescAc = new autoC({"maWoResultFailDetailDTO.fsCdDesc":"description"});
    fsDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"fail_type":"FS",
  	   "is_use":"Y"
  	   });
    fsDescAc.setTable("TAFAILURE");
    fsDescAc.setKeyName("maWoResultMstrDetailDTO.fsCd");
    fsDescAc.setAcResultMap({
        "maWoResultFailDetailDTO.fsCd":"failure_id"
    });
    fsDescAc.init();
    
    moDescAc = new autoC({"maWoResultFailDetailDTO.moCdDesc":"description"});
    moDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"fail_type":"MO",
  	   "is_use":"Y"
  	   });
    moDescAc.setTable("TAFAILURE");
    moDescAc.setKeyName("maWoResultMstrDetailDTO.moCd");
    moDescAc.setAcResultMap({
        "maWoResultFailDetailDTO.moCd":"failure_id"
    });
    moDescAc.init();
	
    caCdDesc = new autoC({"maWoResultFailDetailDTO.caCdDesc":"description"});
    caCdDesc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"fail_type":"CA",
  	   "is_use":"Y"
  	   });
    caCdDesc.setTable("TAFAILURE");
    caCdDesc.setKeyName("maWoResultMstrDetailDTO.caCd");
    caCdDesc.setAcResultMap({
        "maWoResultFailDetailDTO.caCd":"failure_id"
    });
    caCdDesc.init();
    
    reCdDesc = new autoC({"maWoResultFailDetailDTO.reCdDesc":"description"});
    reCdDesc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"fail_type":"RE",
  	   "is_use":"Y"
  	   });
    reCdDesc.setTable("TAFAILURE");
    reCdDesc.setKeyName("maWoResultMstrDetailDTO.reCd");
    reCdDesc.setAcResultMap({
        "maWoResultFailDetailDTO.reCd":"failure_id"
    });
    reCdDesc.init();
	
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
	if("<%=user.getShiftType()%>"!='null'){
		//교대조 세팅
		maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.shiftTypeId'].value = "<%=user.getShiftType()%>";
		maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.shiftTypeDesc'].value = "<%=user.getShiftTypeDesc()%>";
	}
	
	//완료버튼 감추기.
	hideBtn("CONFIRM");
	hideBtn("PDF");
	
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
	//고장 작업시작일자, 종료일자 넣기.
	/* maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.eqDnStartDate'].value   = getToday();
	maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.eqDnEndDate'].value   = getToday(); */
	//고장 작업시작시간(1시간전), 종료시간(현재시간) 넣기.
	/* maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.eqDnStartTime'].value   = getMinusTime(false,1);
	maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.eqDnEndTime'].value   = getTime(false); */
	//부서
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.deptId'].value    = "<%=user.getDeptId()%>";
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.deptDesc'].value  = "<%=user.getDeptDesc()%>";
	//사원
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.empId'].value     = "<%=user.getEmpId()%>";
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.empDesc'].value   = "<%=user.getEmpName()%>";
	if(<%=user.getWkctrId()%>!=null)
	{
		//작업그룹
<%-- 		maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.wkCtrId'].value     = "<%=user.getWkctrId()%>";
		maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.wkCtrDesc'].value   = "<%=user.getWkctrDesc()%>"; --%>
		maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.wkCtrId'].value     = "<%=user.getFilterWkCtrId()%>";
		maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.wkCtrDesc'].value   = "<%=user.getFilterWkCtrDesc()%>";
	}
	
	setWorkTime();
	setBmWorkTime('eqDn');
	sequenceNextVal('SQAWKOR_ID');
	maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.eqDnStartDate'].value   = "";
	maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.eqDnEndDate'].value   = "";
	maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.eqDnStartTime'].value   = "";
	maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.eqDnEndTime'].value = "";
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
	
	if(pageId == "maDocLibList" || pageId == "maWoDocLibList")
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
				//================================
				// 필수 확정항목 체크한다.
				//================================
				if(checkConfirmValidation()) return;
				
				//작업시간이 미래라면 완료 처리 안됨
				var todayStr = getToday().split("-").join("");
				var startDate = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.startDate'].value;
				startDate = startDate.split("-").join("");
				
				if(todayStr < startDate)
				{
					alertMessage1("<bean:message key='MESSAGE.MSG0034'/>");
					return;
				}
				//RPL,RLR의 경우 설비 고장시간, 라인 고장시간 체크
				if(maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.pmTypeId'].value=="RPL"||maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.pmTypeId'].value=="RPR"){
					if(checkRequireValue('maWoResultFailDetailDTO.eqDnStartDate','<bean:message key="LABEL.eqFailStartTime"/>')) return;
					if(checkRequireValue('maWoResultFailDetailDTO.eqDnStartTime','<bean:message key="LABEL.eqFailStartTime"/>')) return;
					if(checkRequireValue('maWoResultFailDetailDTO.eqDnEndDate','<bean:message key="LABEL.eqFailEndTime"/>')) return;
					if(checkRequireValue('maWoResultFailDetailDTO.eqDnEndTime','<bean:message key="LABEL.eqFailEndTime"/>')) return;
				
					if(checkRequireValue('maWoResultFailDetailDTO.lnDnStartDate','<bean:message key="LABEL.lnDownStartTime"/>')) return;
					if(checkRequireValue('maWoResultFailDetailDTO.lnDnStartTime','<bean:message key="LABEL.lnDownStartTime"/>')) return;
					if(checkRequireValue('maWoResultFailDetailDTO.lnDnEndDate','<bean:message key="LABEL.lnDownEndTime"/>')) return;
					if(checkRequireValue('maWoResultFailDetailDTO.lnDnEndTime','<bean:message key="LABEL.lnDownEndTime"/>')) return;
				}else if(maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.pmTypeId'].value=="COR"){
					if(checkRequireValue('maWoResultFailDetailDTO.eqDnStartDate','<bean:message key="LABEL.eqFailStartTime"/>')) return;
					if(checkRequireValue('maWoResultFailDetailDTO.eqDnStartTime','<bean:message key="LABEL.eqFailStartTime"/>')) return;
					if(checkRequireValue('maWoResultFailDetailDTO.eqDnEndDate','<bean:message key="LABEL.eqFailEndTime"/>')) return;
					if(checkRequireValue('maWoResultFailDetailDTO.eqDnEndTime','<bean:message key="LABEL.eqFailEndTime"/>')) return;
				}
				
				maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.woNgPointId'].value = maWoResultMstrDetailForm.elements['maWoResultMstrCommonDTO.woNgPointId'].value;
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
	if(M$('maWoResultFailDetailDTO.eqDnWorkTime').value == '') setBmWorkTime('eqDn');
	if(M$('maWoResultFailDetailDTO.lnDnWorkTime').value == '')  setBmWorkTime('lnDn');
	
	var actionUrl = contextPath + "/maWoResultMstrDetail.do"; 

	ajaxPost(actionUrl, FormQueryString(maWoResultMstrDetailForm))
	.done(function(d){
		maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.wkOrId'].value = maWoResultMstrDetailForm.elements['maWoResultMstrCommonDTO.wkOrId'].value;

		// 요청 - 작업결과 (목록)에서 저장한 경우 
	<%--     if (parent.currentPageId == "reqWoRsltList" && maWoResultMstrDetailForm.strutsAction.value == '<%=MaWoResultMstrDetailAction.WO_RESULT_DETAIL_INPUT%>')  --%>
//	   	{
//	    	 	parent.afterCreate(maWoResultMstrDetailForm.elements['maWoResultMstrCommonDTO.wkOrId'].value);
//	   	}
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
	setWorkTime();
	if(_name.indexOf("lnDnStartDate")>-1||_name.indexOf("lnDnEndDate")>-1){
		setBmWorkTime('lnDn');
	}
}
 
 function setWorkTime(){
	var startDate = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.startDate'].value;
 	var startTime = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.startTime'].value;
 	var endDate = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.endDate'].value;
 	var endTime = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.endTime'].value;
 	//작업시간 입력시 설비고장시간 데이터 없을 때 같게 넣어주기. 
 	var eqDnStartDate = maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.eqDnStartDate'].value;
 	var eqDnStartTime = maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.eqDnStartTime'].value;
 	var eqDnEndDate = maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.eqDnEndDate'].value;
 	var eqDnEndTime = maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.eqDnEndTime'].value;
 	

    if(maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.eqDnStartDate'].value=='')maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.eqDnStartDate'].value = startDate;        
    if(maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.eqDnStartTime'].value=='')maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.eqDnStartTime'].value = startTime;    	    	
    if(maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.eqDnEndDate'].value=='')maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.eqDnEndDate'].value = endDate;        	
    if(maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.eqDnEndTime'].value=='')maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.eqDnEndTime'].value = endTime;
 	
	maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.workTime'].value = setNumberFormat(getMinInterval(maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.startDate'], maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.startTime'], maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.endDate'],maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.endTime']));
 	
 	setBmWorkTime('eqDn');
	
 }
 
 function setBmWorkTime(str){
		var startDate = maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.'+str+'StartDate'].value;
	 	var startTime = maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.'+str+'StartTime'].value;
	 	var endDate = maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.'+str+'EndDate'].value;
	 	var endTime = maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.'+str+'EndTime'].value;
	 	
	 	maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.'+str+'WorkTime'].value = 
			setNumberFormat(getMinInterval(maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.'+str+'StartDate'], maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.'+str+'StartTime'], maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.'+str+'EndDate'],maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.'+str+'EndTime']));

	 	
	 	var eqLnDnTime = maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.'+str+'WorkTime'].value;
		var wrkTime = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.workTime'].value;

		if(eqLnDnTime != "" && wrkTime !="" && eqLnDnTime < wrkTime)
		{
			alertMessage1("작업시간이 설비고장시간보다 크게 입력되었습니다.");
		}
	 }
 
 /**
  * Print 버튼 클릭
  */
 function goPrint()
 {
 	
 	var url   = contextPath + "/maWoResultMstrDetail.do";
 	var param = "maWoResultMstrDetailDTO.wkOrId="+ maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.wkOrId'].value;
 	
 	openPrintView(url, param);
 }
  
  function goWopdf(){
 	 goPrint();
  }
  
  	function afterSetValue(type)
	{
		if(type =='TAFAILUREFS')
		{
			maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.fsDesc'].value=maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.fsCdDesc'].value;
		}
		else if(type =='TAFAILUREMO')
		{
			maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.moDesc'].value=maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.moCdDesc'].value;
		}
		else if(type =='TAFAILURECA')
		{
			maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.caDesc'].value=maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.caCdDesc'].value;
		}
		else if(type =='TAFAILURERE')
		{
			maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.reDesc'].value=maWoResultMstrDetailForm.elements['maWoResultFailDetailDTO.reCdDesc'].value;
		}
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
	goClose('maWoResultBmBaseMstrDetail');
 }
 
 

 /*
  * 요청접수보기
  */
 function goWoreqLink(_pageId)
 {
 	var equipId = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.equipId'].value;
 	var equipDesc = maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.equipDesc'].value;
 	goWoreqList(equipId, equipDesc);
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
	<html:hidden property="maWoResultMstrDetailDTO.shiftTypeId" />
	<html:hidden property="maWoResultMstrDetailDTO.empId" />
	<html:hidden property="maWoResultMstrDetailDTO.vendorId" />
	<html:hidden property="maWoResultMstrDetailDTO.selfVendorType" />
	<html:hidden property="maWoResultMstrDetailDTO.pWkOrId" />  <!-- 상위Work Order ID -->
	<html:hidden property="maWoResultMstrDetailDTO.woGenType" />
	<html:hidden property="maWoResultMstrDetailDTO.eqLocId" />
	<html:hidden property="maWoResultMstrDetailDTO.wkCtrId" />
 	<html:hidden property="maWoResultMstrCommonDTO.woReqResId" />
 	<html:hidden property="maWoResultMstrDetailDTO.woReqResId" />
	<html:hidden property="maWoResultMstrDetailDTO.eqAsmbId" />
 	<html:hidden property="maWoResultFailDetailDTO.eqAsmbId"/>
 	<html:hidden property="maWoResultFailDetailDTO.fsCd"/>
	<html:hidden property="maWoResultFailDetailDTO.moCd"/>
	<html:hidden property="maWoResultFailDetailDTO.caCd"/>
	<html:hidden property="maWoResultFailDetailDTO.reCd"/>
	<html:hidden property="maWoResultFailDetailDTO.remark"/>
 	<html:hidden property="maWoResultMstrDetailDTO.parentWoId" />
 	<html:hidden property="maWoResultMstrDetailDTO.parentWoDesc" />
 	<html:hidden property="maWoResultMstrDetailDTO.woNgPointId" />
	<html:hidden property="maWoResultMstrDetailDTO.woNgPointResId" />
 	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
 	<html:hidden property="maDocLibCommonDTO.objectType" />
 	<html:hidden property="maDocLibCommonDTO.securGrade" />
 	<html:hidden property="maDocLibCommonDTO.docCateg" />
 	<html:hidden property="maDocLibCommonDTO.description" />
 	<html:hidden property="maWoResultMstrCommonDTO.woReqId" />
 	<html:hidden property="maWoResultMstrCommonDTO.invtlistId" />
 	<html:hidden property="maWoResultMstrDetailDTO.eqctgType" />
 	
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
			<div class="field">
				<label><bean:message key="LABEL.woType"/></label>
				<div class="input_read">
					<html:text property="maWoResultMstrDetailDTO.woTypeDesc" tabindex="30" readonly="true" />
				</div>
			</div>
			<!-- 작업형태  -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.pmType"/></label>
				<div class="input_box">
					<html:text property="maWoResultMstrDetailDTO.pmTypeDesc" tabindex="40" />
					<p class="open_spop"><a href="javascript:lovSysDir('maWoResultMstrDetailDTO.pmTypeId', 'maWoResultMstrDetailDTO.pmTypeDesc','BM_TYPE');"><span>조회</span></a></p>
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
						<a href="javascript:lovEquip('maWoResultMstrDetailDTO.equipId','maWoResultMstrDetailDTO.equipDesc','maWoResultMstrDetailDTO.eqLocDesc','','maWoResultMstrDetailDTO.eqLocId');">
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 부위 -->
			<div class="field">
				<label><bean:message key="LABEL.asmb"/></label>
				<div class="input_box">
					<html:text property="maWoResultFailDetailDTO.eqAsmbDesc" tabindex="80"/>
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 작업일자 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.wkorDate"/></label>
				<div class="input_box">
					<html:text property="maWoResultMstrDetailDTO.wkorDate" tabindex="81" />
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			<!-- 담당부서 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.manageDept"/></label>
				<div class="input_box">
					<html:text property="maWoResultMstrDetailDTO.deptDesc" tabindex="82"/>
					<p class="open_spop"><a href="javascript:lovDept('maWoResultMstrDetailDTO.deptId', '', 'maWoResultMstrDetailDTO.deptDesc');"><span>조회</span></a></p>
				</div>
			</div>
			<div class="field">
				<label><bean:message key="LABEL.wkCtr"/></label>
				<div class="input_box">
					<html:text property="maWoResultMstrDetailDTO.wkCtrDesc" tabindex="83"/>
					<p class="open_spop">
						<a href="javascript:lovWkCtr('maWoResultMstrDetailDTO.wkCtrId', 'maWoResultMstrDetailDTO.wkCtrDesc');">
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 교대조. -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.shiftType"/></label>
				<div class="input_box">
					<html:text property="maWoResultMstrDetailDTO.shiftTypeDesc" tabindex="84" />
					<p class="open_spop"><a href="javascript:lovSysDir('maWoResultMstrDetailDTO.shiftTypeId', 'maWoResultMstrDetailDTO.shiftTypeDesc','SHIFT_TYPE');"><span>조회</span></a></p>
				</div>
			</div>
			<!-- 담당자 -->
			<div class="field">
				<label class="lastcheck"><bean:message key="LABEL.manager"/></label>
				<div class="input_box">
					<html:text property="maWoResultMstrDetailDTO.empDesc" tabindex="84"/>
					<p class="open_spop"><a href="javascript:lovEmp('maWoResultMstrDetailDTO.empId', '', 'maWoResultMstrDetailDTO.empDesc');"><span>조회</span></a></p>
				</div>
			</div>
			<!-- 자가/외주  -->
			<div class="field">
				<label><bean:message key="LABEL.selfVendorType"/></label>
				<div class="input_box">
					<html:text property="maWoResultMstrDetailDTO.selfVendorTypeDesc" tabindex="90" />
					<p class="open_spop"><a href="javascript:lovSysDir('maWoResultMstrDetailDTO.selfVendorType', 'maWoResultMstrDetailDTO.selfVendorTypeDesc','SELF_VENDOR_TYPE');"><span>조회</span></a></p>
				</div>
			</div>
			
			<!-- 거래처 -->
			<div class="field">
				<label><bean:message key="LABEL.vendor"/></label>
				<div class="input_box">
                    <html:text property="maWoResultMstrDetailDTO.vendorDesc" tabindex="100" />
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
			<!-- 금액 -->
			<div class="field" id="amtDiv">
				<label><bean:message key="LABEL.amt"/></label>
				<div class="input_box">
					<html:text property="maWoResultMstrDetailDTO.totAmt" tabindex="105" styleClass="num"/>
				</div>
			</div>
			
			<!-- 작업상세내용 -->
			<div class="field_long">
				<label><bean:message key="LABEL.woPerform"/></label>
				<div class="input_box">
					<html:textarea property="maWoResultMstrDetailDTO.perform" styleClass="ta50" tabindex="110" />
				</div>
			</div>
			<!-- 향후개선대책 -->
			<div class="field_long">
				<label><bean:message key="LABEL.pmAction"/></label>
				<div class="input_box">
					<html:textarea property="maWoResultMstrDetailDTO.pmAction" styleClass="ta50" tabindex="115" />
				</div>
			</div>
			<div class="field_divide"></div>
			<!-- 고장계통 -->
			<div class="field">
				<label><bean:message key="LABEL.failFs"/></label>
				<div class="input_box">
					<html:text property="maWoResultFailDetailDTO.fsCdDesc" tabindex="120"/>
					<p class="open_spop">
						<a href="javascript:lovTable('maWoResultFailDetailDTO.fsCd', 'maWoResultFailDetailDTO.fsCdDesc','TAFAILUREFS','FS');">
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 계통설명 -->
			<div class="field">
				<label><bean:message key="LABEL.failFsRemark"/></label>
				<div class="input_box">
					<html:text property="maWoResultFailDetailDTO.fsDesc" tabindex="125" />
				</div>
			</div>
			<!-- 고장현상 -->
			<div class="field">
				<label><bean:message key="LABEL.moCd"/></label>
				<div class="input_box">
					<html:text property="maWoResultFailDetailDTO.moCdDesc" tabindex="120"/>
					<p class="open_spop">
						<a href="javascript:lovTable('maWoResultFailDetailDTO.moCd', 'maWoResultFailDetailDTO.moCdDesc','TAFAILUREMO','MO');">
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 현상설명 -->
			<div class="field">
				<label><bean:message key="LABEL.moCdRemark"/></label>
				<div class="input_box">
					<html:text property="maWoResultFailDetailDTO.moDesc" tabindex="125" />
				</div>
			</div>
			
			<!-- 고장원인 -->
			<div class="field">
				<label><bean:message key="LABEL.caCd"/></label>
				<div class="input_box">
					<html:text property="maWoResultFailDetailDTO.caCdDesc" tabindex="130"/>
					<p class="open_spop">
						<a href="javascript:lovTable('maWoResultFailDetailDTO.caCd', 'maWoResultFailDetailDTO.caCdDesc','TAFAILURECA','CA');">
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 원인설명 -->
			<div class="field">
				<label><bean:message key="LABEL.caCdRemark"/></label>
				<div class="input_box">
					<html:text property="maWoResultFailDetailDTO.caDesc" tabindex="135" />
				</div>
			</div>
			<!-- 고장조치 -->
			<div class="field">
				<label><bean:message key="LABEL.reCd"/></label>
				<div class="input_box">
					<html:text property="maWoResultFailDetailDTO.reCdDesc" tabindex="140"/>
					<p class="open_spop">
						<a href="javascript:lovTable('maWoResultFailDetailDTO.reCd', 'maWoResultFailDetailDTO.reCdDesc','TAFAILURERE','RE');">
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 조치설명 -->
			<div class="field">
				<label><bean:message key="LABEL.reCdRemark"/></label>
				<div class="input_box">
					<html:text property="maWoResultFailDetailDTO.reDesc" tabindex="145" />
				</div>
			</div>
			
			
			
			<div class="field_divide"></div>
			
			<!-- 작업시작시간 -->
			<div class="field">
				<label class="lastcheck"><bean:message key="LABEL.woFromTime"/></label>
				<div class="datetime_wrap">
					<div class="input_box input_carendar">
						<html:text property="maWoResultMstrDetailDTO.startDate" tabindex="150" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box">
						<html:text property="maWoResultMstrDetailDTO.startTime" tabindex="152"/>
						<p class="open_time"><span>시간</span></p>
					</div>
				</div>
			</div>
			<!-- 설비고장시작 -->
			<div class="field">
				<label><bean:message key="LABEL.eqFailStartTime"/></label>
				<div class="datetime_wrap">
					<div class="input_box input_carendar">
						<html:text property="maWoResultFailDetailDTO.eqDnStartDate" onblur="javascript:setBmWorkTime('eqDn');" tabindex="160" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box">
						<html:text property="maWoResultFailDetailDTO.eqDnStartTime" onblur="javascript:setBmWorkTime('eqDn');"  tabindex="162"/>
						<p class="open_time"><span>시간</span></p>
					</div>
				</div>
			</div>
			
			<!-- 작업종료시간-->
			<div class="field">
				<label class="lastcheck"><bean:message key="LABEL.woToTime"/></label>
				<div class="datetime_wrap">
					<div class="input_box input_carendar">
						<html:text property="maWoResultMstrDetailDTO.endDate" onblur="javascript:setWorkTime();" tabindex="165" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box">
						<html:text property="maWoResultMstrDetailDTO.endTime" onblur="javascript:setWorkTime();"  tabindex="166" />
						<p class="open_time"><span>시간</span></p>
					</div>
				</div>
			</div>
			<!-- 설비종료시간 -->
			<div class="field">
				<label><bean:message key="LABEL.eqFailEndTime"/></label>
				<div class="datetime_wrap">
					<div class="input_box input_carendar">
						<html:text property="maWoResultFailDetailDTO.eqDnEndDate" onblur="javascript:setBmWorkTime('eqDn');" tabindex="175" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box">
						<html:text property="maWoResultFailDetailDTO.eqDnEndTime" onblur="javascript:setBmWorkTime('eqDn');"  tabindex="176" />
						<p class="open_time"><span>시간</span></p>
					</div>
				</div>
			</div>
			<!-- 작업시간(분) -->
			<div class="field">
				<label class="lastcheck"><bean:message key="LABEL.woTimeMin"/></label>
				<div class="input_box">
					<html:text property="maWoResultMstrDetailDTO.workTime" tabindex="177" styleClass="num"/>
				</div>
			</div>
			
			<!-- 설비고장시간(분) -->
			<div class="field">
				<label><bean:message key="LABEL.failTimeMin"/></label>
				<div class="input_box">
					<html:text property="maWoResultFailDetailDTO.eqDnWorkTime" tabindex="180" styleClass="num"/>
				</div>
			</div>
			
			<div class="field_divide"></div>
			
			<!-- 라인정지시작시간-->
			<div class="field">
				<label><bean:message key="LABEL.lnDownStartTime"/></label>
				<div class="datetime_wrap">
					<div class="input_box input_carendar">
						<html:text property="maWoResultFailDetailDTO.lnDnStartDate" onblur="javascript:setBmWorkTime('lnDn');" tabindex="190" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box">
						<html:text property="maWoResultFailDetailDTO.lnDnStartTime" onblur="javascript:setBmWorkTime('lnDn');"  tabindex="192"/>
						<p class="open_time"><span>시간</span></p>
					</div>
				</div>
			</div>
			<div class="field"> </div>
			<!-- 라인정지종료시간-->
			<div class="field">
				<label><bean:message key="LABEL.lnDownEndTime"/></label>
				<div class="datetime_wrap">
					<div class="input_box input_carendar">
						<html:text property="maWoResultFailDetailDTO.lnDnEndDate" onblur="javascript:setBmWorkTime('lnDn');" tabindex="195" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box">
						<html:text property="maWoResultFailDetailDTO.lnDnEndTime" onblur="javascript:setBmWorkTime('lnDn');"  tabindex="196" />
						<p class="open_time"><span>시간</span></p>
					</div>
				</div>
			</div>
			<div class="field"> </div>
			<!-- 라인정지(분) -->
			<div class="field">
				<label><bean:message key="LABEL.dnTimeMin"/></label>
				<div class="input_box">
					<html:text property="maWoResultFailDetailDTO.lnDnWorkTime" tabindex="197" styleClass="num"/>
				</div>
			</div>
			<!-- 확정자 -->
			<div class="field">
				<label><bean:message key="LABEL.closeBy"/></label>
				<div class="input_read" >
					<html:text property="maWoResultMstrDetailDTO.closeBy" tabindex="20" readonly="true"/>
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
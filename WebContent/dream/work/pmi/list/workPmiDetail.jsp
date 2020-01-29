<%--===========================================================================
점검작업 - 상세
author  kim21017
version $Id: workPmiDetail.jsp,v 1.0 2015/12/04 04:13:54 kim21017 Exp $
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
<%@ page import="dream.work.pmi.list.action.WorkPmiDetailAction"%>
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
var wkCtrDescAc;

function loadPage() 
{	
	//결재보기에서 열었을때.
// 	if(parent.currentPageId == "appReadyList") setDisableAll();
	
	setTitle("workPmiDetailDTO.pminslistId","workPmiDetailDTO.pmiDesc");
	
	setForUpdate();
	goUpdate();
	
	$("input[name='workPmiDetailDTO.endDate']").blur( function(){
		setWorkTime();
    });
	$("input[name='workPmiDetailDTO.endTime']").blur( function(){
		setWorkTime();
    });
	
	equipDescAc = new autoC({"workPmiDetailDTO.equipDesc":"a.description"});
    equipDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   ,"EQ_STATUS":"R+S"
 	   });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcResultMap({
    	"maWoResultMstrDetailDTO.equipId":"equip_id"
       ,"maWoResultMstrDetailDTO.eqLocId":"eqloc_id"
       ,"maWoResultMstrDetailDTO.eqLocDesc":"eqLocDesc"
    });
    equipDescAc.setKeyName("workPmiDetailDTO.equipId"); 
    equipDescAc.init();
    
    mainMngAc = new autoC({"workPmiDetailDTO.empDesc":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
  	   });
    /* mainMngAc.setAcDConditionMap({
    	"plant" : "workPmiDetailDTO.plant"
    }); */
    mainMngAc.setTable("TAEMP");
    mainMngAc.setKeyName("workPmiDetailDTO.empId");
    mainMngAc.setAcResultMap({
        "workPmiDetailDTO.empId":"emp_id"
    });
    mainMngAc.init();
    
    deptAc = new autoC({"workPmiDetailDTO.deptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    /* deptAc.setAcDConditionMap({
    	"plant" : "workPmiDetailDTO.plant"
    }); */
    deptAc.setTable("TADEPT");
    deptAc.setKeyName("workPmiDetailDTO.deptId");
    deptAc.setAcResultMap({
        "workPmiDetailDTO.deptId":"dept_id"
    });
    deptAc.init();
    
    wkCtrDescAc = new autoC({"workPmiDetailDTO.wkCtrDesc":"description"});
    wkCtrDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    wkCtrDescAc.setTable("TAWKCTR");
    wkCtrDescAc.setAcResultMap({
        "workPmiDetailDTO.wkCtrId":"wkctr_id"
    });
    wkCtrDescAc.init();
    
    acSysDesc("workPmiDetailDTO.shiftTypeDesc","workPmiDetailDTO.shiftTypeId","SHIFT_TYPE");
    //alert(workPmiDetailForm.elements['workPmiDetailDTO.pmschedStatusId'].value);
    
    setBtnAsStatus();
    
}

function setBtnAsStatus()
{
	if(workPmiDetailForm.elements['workPmiDetailDTO.pmschedStatusId'].value=="C")
	{
		setDisableAll();
		showBtn("REVERSE");
		hideBtn('CONFIRM');
		hideBtn("APPROVAL");
	}
	else if(workPmiDetailForm.elements['workPmiDetailDTO.pmschedStatusId'].value=="IRWRA"
			|| workPmiDetailForm.elements['workPmiDetailDTO.pmschedStatusId'].value=="IRWOA"){
		setDisableAll();
		showBtn("CONFIRM");
		hideBtn('REVERSE');
		hideBtn("APPROVAL");
	}
	else{
		setEnableAll();
		hideBtn('REVERSE');
		showBtn("CONFIRM");
		showBtn("APPROVAL");
	}
	   
	   	
}

function goUpdate(){	
	workPmiDetailForm.elements['workPmiDetailDTO.pmschedStatusDesc'].readOnly="true";
	workPmiDetailForm.elements['workPmiDetailDTO.workTime'].readOnly="true";
	
	//예방점검기준서에 의해 생성된 작업결과는 설비를 변경하지못함.
	workPmiDetailForm.elements['workPmiDetailDTO.equipDesc'].readOnly = true;
    document.getElementsByName("equipDiv").className = "input_read"; 
    document.getElementById("equipPopDiv").style.display = "none";
    
    if(workPmiDetailForm.elements['workPmiDetailDTO.pmschedStatusId'].value=="C")
	{
		$('.b_confirm').hide();	
	}
}

function goOpen(pageId)
{
	goTabPage(pageId);
}

function goTabPage(pageId)
{
	var form = document.workPmiDetailForm;
	
	if(pageId == "maDocLibList" || pageId == "workPmiDocLibList")
	{	
		workPmiDetailForm.elements['maDocLibCommonDTO.objectId'].value = workPmiDetailForm.elements['workPmiCommonDTO.pminslistId'].value;
		workPmiDetailForm.elements['maDocLibCommonDTO.objectType'].value = "PMILIST";  //WORESULT
		workPmiDetailForm.elements['maDocLibCommonDTO.description'].value = workPmiDetailForm.elements['workPmiDetailDTO.pmiDesc'].value;  //제목
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}	
	else if(pageId == "maAppPrcList" || pageId == "appPrcList") {
		workPmiDetailForm.elements['appReqCommonDTO.objectId'].value = workPmiDetailForm.elements['workPmiCommonDTO.pminslistId'].value;
		workPmiDetailForm.elements['appReqCommonDTO.apprType'].value = "PMINSRSLT";
		goCommonTabPage(form, '' , pageId);
	}
	else
	{
		workPmiDetailForm.elements['workPmiCommonDTO.pmschedStatusId'].value = workPmiDetailForm.elements['workPmiDetailDTO.pmschedStatusId'].value;
		goCommonTabPage(form, '' , pageId);
	}
}

/**
 * 저장
 */ 
function goSave()
{
	if(workPmiDetailForm.elements['workPmiDetailDTO.empDesc'].value == "") {
		workPmiDetailForm.elements['workPmiDetailDTO.empId'].value = loginUser.empId;
		workPmiDetailForm.elements['workPmiDetailDTO.empDesc'].value = loginUser.empName;
	}
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;

	//strutsAction 셋팅.
	workPmiDetailForm.elements['workPmiDetailDTO.updDate'].value = getNowDateTime(true); 	
	workPmiDetailForm.elements['workPmiDetailDTO.updById'].value = loginUser.empId; 	
	workPmiDetailForm.elements['workPmiDetailDTO.updBy'].value = loginUser.empName; 
		
	workPmiDetailForm.strutsAction.value = '<%=WorkPmiDetailAction.WORK_PMI_DETAIL_UPDATE%>';
	if(M$('workPmiDetailDTO.workTime').value == '') setWorkTime();
	
	var actionUrl = contextPath + "/workPmiDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(workPmiDetailForm), 'afterSave');
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
	
	if(typeof afterSetDateAct == "function") afterSetDateAct(_name);
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	workPmiDetailForm.elements['workPmiDetailDTO.pminslistId'].value = workPmiDetailForm.elements['workPmiCommonDTO.pminslistId'].value;
	
	if (parent.findGridRow)	parent.findGridRow(workPmiDetailForm.elements['workPmiDetailDTO.pminslistId'].value);
	
	getTopPage().afterSaveAll(currentPageId);
	//저장후 완료버튼 보이기
	$(".b_confirm").css("display","");
 }
 
 function setWorkTime(){
	var startDate = workPmiDetailForm.elements['workPmiDetailDTO.startDate'].value;
 	var startTime = workPmiDetailForm.elements['workPmiDetailDTO.startTime'].value;
 	var endDate = workPmiDetailForm.elements['workPmiDetailDTO.endDate'].value;
 	var endTime = workPmiDetailForm.elements['workPmiDetailDTO.endTime'].value;

	workPmiDetailForm.elements['workPmiDetailDTO.workTime'].value = setNumberFormat(getMinInterval(workPmiDetailForm.elements['workPmiDetailDTO.startDate'], workPmiDetailForm.elements['workPmiDetailDTO.startTime'], workPmiDetailForm.elements['workPmiDetailDTO.endDate'],workPmiDetailForm.elements['workPmiDetailDTO.endTime']));
	
 }
 
 /**
  * 점검항목의 모든 결과값이 들어있는지 확인.
  */
 var isValid = 0;

 function checkPoint(_callback)
 {
 	isValid=0;
 	
 	var actionUrl = contextPath + "/workPmiDetail.do";
     var param =  "&strutsAction=" + '<%= WorkPmiDetailAction.WORK_PMI_DETAIL_CHECKPOINT %>'
               +  "&" + FormQueryString(workPmiDetailForm);
     XMLHttpPostVal(actionUrl, param, _callback);
     
 }
 function setValidCheckPoint(ajaxXmlDoc)
 {
 	isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
 	if(isValid == '0')
     {
 		workPmiDetailForm.elements['workPmiDetailDTO.updDate'].value = getNowDateTime(true); 
 		workPmiDetailForm.strutsAction.value = '<%=WorkPmiDetailAction.WORK_PMI_DETAIL_COMPLETE%>';
 		 var actionUrl = contextPath + "/workPmiDetail.do";
 			XMLHttpPost(actionUrl, FormQueryString(workPmiDetailForm), 'afterConfirm');
     }else{
     	alertMessage1("<bean:message key='MESSAGE.MSG0112' />");
     	return;
     }
 	
 }
 

 function setValidCheckPointAppr(ajaxXmlDoc)
 {
 	isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
 	if(isValid == '0')
     {
 		goApprovalAct();
 		
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
 				if(checkRequireValue('workPmiDetailDTO.endDate','<bean:message key="LABEL.woDate"/>')) return;
 				
 				//작업시간이 미래라면 완료 처리 안됨
 				var todayStr = getToday().split("-").join("");
 				var startDate = workPmiDetailForm.elements['workPmiDetailDTO.startDate'].value;
 				startDate = startDate.split("-").join("");
 				
 				if(todayStr < startDate)
 				{
 					alertMessage1("<bean:message key='MESSAGE.MSG0034'/>");
 					return;
 				}
 				checkPoint('setValidCheckPoint');
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
	workPmiDetailForm.elements['workPmiDetailDTO.pmschedStatusId'].value = "C";
	valSysDirCode('workPmiDetailDTO.pmschedStatusId', 'workPmiDetailDTO.pmschedStatusDesc', 'PMSCHED_STATUS', true);
	//확정자명 = 로그인 유저
 	if (parent.findGridRow)	parent.findGridRow(workPmiDetailForm.elements['workPmiDetailDTO.pminslistId'].value);
	
//  	if(workPmiDetailForm.elements['workPmiDetailDTO.pmschedStatusId'].value=="C")
//  	{
//  		$('.b_confirm').hide();	
//  	}
	
	// 예방점검(목록) 상태 변경 
	if (parent.findGridRow)	
		parent.findGridRow(workPmiDetailForm.elements['workPmiDetailDTO.pminslistId'].value);
	
 	setBtnAsStatus();
}

/**
 * 작업완료 취소
 */
function goReverse(){
	getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0231'/>", function(result){
		 if(result){
		    var url = contextPath + "/workPmiDetail.do";
		    workPmiDetailForm.strutsAction.value = '<%=WorkPmiDetailAction.WORK_PMI_DETAIL_COMPLETE_CANCEL%>';
		    XMLHttpPost(url, FormQueryString(workPmiDetailForm), 'afterReverse');
		 }
	});
}

/**
 * 완료 취소후 호출
 */
function afterReverse(ajaxXmlDoc)
{
 	alertMessage1("<bean:message key='MESSAGE.CMSG034'/>");
	//작업상태 = P - 작업완료
	workPmiDetailForm.elements['workPmiDetailDTO.pmschedStatusId'].value = "S";
	valSysDirCode('workPmiDetailDTO.pmschedStatusId', 'workPmiDetailDTO.pmschedStatusDesc', 'PMSCHED_STATUS', true);
	
	// 예방점검(목록) 상태 변경 
	if (parent.findGridRow)	
		parent.findGridRow(workPmiDetailForm.elements['workPmiDetailDTO.pminslistId'].value);
	
	setBtnAsStatus();
}  
  
/**
 * 예방점검표 출력
 */
 function goPmchecklist()
 {
	 reportCall('workPmiDetail','workPmiDetail', "<%=user.getCompNo()%>", "<%=user.getUserId()%>", "<%=user.getLangId()%>",workPmiDetailForm.elements['workPmiDetailDTO.pminslistId'].value); 

 }
 
 /* audit Trail */
 function goAudtrailLink()
 {		
 	var objectId = workPmiDetailForm.elements['workPmiDetailDTO.pminslistId'].value;
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
        alertMessage1("<bean:message key='MESSAGE.MSG0036'/>");
    }else{
    	
    	if(checkConfirmValidation()) return;
    	if(checkRequireValue('workPmiDetailDTO.endDate','<bean:message key="LABEL.woDate"/>')) return;
			
		//작업시간이 미래라면 완료 처리 안됨
		var todayStr = getToday().split("-").join("");
		var startDate = workPmiDetailForm.elements['workPmiDetailDTO.startDate'].value;
		startDate = startDate.split("-").join("");
		
		if(todayStr < startDate)
		{
			alertMessage1("<bean:message key='MESSAGE.MSG0034'/>");
			return;
		}
		
		checkPoint('setValidCheckPointAppr');
    }
  }
  
 function goApprovalAct()
 {
     var pminslistId = workPmiDetailForm.elements['workPmiDetailDTO.pminslistId'].value;
     var description = workPmiDetailForm.elements['workPmiDetailDTO.pmiDesc'].value;
     
     //APPR_TYPE:결재구분상태[예:일일작업:WODAY] , OBJECT_ID, 
     appAction(pminslistId, "PMINSRSLT", description);
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
    if (parent.findGridRow) parent.findGridRow(workPmiDetailForm.elements['workPmiDetailDTO.pminslistId'].value);
    
    setBtnAsStatus();
    
    goClose('workPmiDetail');
  }

/*
 * 기준서 보기 WOPMPRESULT
 */
function goPmstdLink()
{
	var fileName = workPmiDetailForm.elements['workPmiDetailDTO.param'].value;
	var pmId     = workPmiDetailForm.elements['workPmiDetailDTO.pmId'].value;

	goPmstdList(pmId, fileName);
}
  

/**
 * Print 버튼 클릭
 */
 function goPrint()
 {
	 reportCall('workPmiInsPdfDetail','workPmiInsPdfDetail', "<%=user.getCompNo()%>", "<%=user.getUserId()%>", "<%=user.getLangId()%>",workPmiDetailForm.elements['workPmiDetailDTO.pminslistId'].value); 
 }


 function goInspdfLink()
 {
	 goPrint();
 }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/workPmiDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="workPmiCommonDTO.pminslistId" />
	<html:hidden property="workPmiCommonDTO.pmschedStatusId" />
	<html:hidden property="workPmiDetailDTO.equipId" />
	<html:hidden property="workPmiDetailDTO.woTypeId" />
	<html:hidden property="workPmiDetailDTO.pmschedStatusId" />
	<html:hidden property="workPmiDetailDTO.pmTypeId" />
	<html:hidden property="workPmiDetailDTO.deptId" />
	<html:hidden property="workPmiDetailDTO.empId" />
	<html:hidden property="workPmiDetailDTO.shiftTypeId" />
 	<html:hidden property="workPmiDetailDTO.eqLocId" />
 	<html:hidden property="workPmiDetailDTO.wkCtrId" />
 	<html:hidden property="workPmiDetailDTO.pmId" />
 	<html:hidden property="workPmiDetailDTO.pmInscchedId" />
 	<html:hidden property="workPmiDetailDTO.param" />
 	<html:hidden property="workPmiDetailDTO.updById" />
 	<html:hidden property="workPmiDetailDTO.creById" />
 	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
 	<html:hidden property="maDocLibCommonDTO.objectType" />
 	<html:hidden property="maDocLibCommonDTO.securGrade" />
 	<html:hidden property="maDocLibCommonDTO.docCateg" />
 	<html:hidden property="maDocLibCommonDTO.description" />
 	<html:hidden property="workPmiCommonDTO.woReqId" />
 	<html:hidden property="appReqCommonDTO.objectId" />
 	<html:hidden property="appReqCommonDTO.apprType" />

	<div class="article_box">
		<div class="form_box">
			<!-- 점검작업No -->
			<div class="field">
				<label><bean:message key="LABEL.pmiNo"/></label>
				<div class="input_read">
					<html:text property="workPmiDetailDTO.pminslistId" tabindex="10" readonly="true"/>
				</div>
			</div>
			<!-- 점검작업상태 -->
			<div class="field">
				<label><bean:message key="LABEL.pmiSchedStatus"/></label>
				<div class="input_read">
					<html:text property="workPmiDetailDTO.pmschedStatusDesc" tabindex="20" />
				</div>
			</div>
			<!-- 작업종류  -->
			<div class="field">
				<label><bean:message key="LABEL.woType"/></label>
				<div class="input_read">
					<html:text property="workPmiDetailDTO.woTypeDesc" tabindex="30" readonly="true" />
				</div>
			</div>
			<!-- 작업형태  -->
			<div class="field">
				<label><bean:message key="LABEL.pmType"/></label>
				<div class="input_read">
					<html:text property="workPmiDetailDTO.pmTypeDesc" tabindex="40" readonly="true" />
				</div>
			</div>
			<!-- 설비번호 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.equipNo"/></label>
				<div class="input_box" name ="equipDiv">
					<html:text property="workPmiDetailDTO.itemNo" tabindex="45" />
				</div>
			</div>
			<!-- 설비 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.equipment"/></label>
				<div class="input_box" name ="equipDiv">
					<html:text property="workPmiDetailDTO.equipDesc" tabindex="50" />
					<p class="open_spop" id="equipPopDiv">
						<a><span>조회</span></a>
					</p>
				</div>
			</div>
			<!-- 설비위치 -->
			<div class="field">
				<label><bean:message key="LABEL.location"/></label>
				<div class="input_read">
					<html:text property="workPmiDetailDTO.eqLocDesc" tabindex="60" readonly="true"/>
				</div>
			</div>
			<!-- 점검명 -->
			<div class="field_long">
				<label class="check"><bean:message key="LABEL.pmiDesc"/></label>
				<div class="input_box">
					<html:text property="workPmiDetailDTO.pmiDesc" tabindex="70" />
				</div>
			</div>
			<!-- 점검일자 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.inspectDate"/></label>
				<div class="input_box">
					<html:text property="workPmiDetailDTO.wkorDate" tabindex="80" />
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			<!-- 담당부서 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.manageDept"/></label>
				<div class="input_box">
					<html:text property="workPmiDetailDTO.deptDesc" tabindex="90"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 작업그룹 -->
			<div class="field">
				<label><bean:message key="LABEL.wkCtr"/></label>
				<div class="input_box">
					<html:text property="workPmiDetailDTO.wkCtrDesc" tabindex="95"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 교대조 -->
			<div class="field">
				<label><bean:message key="LABEL.shiftType"/></label>
				<div class="input_box">
					<html:text property="workPmiDetailDTO.shiftTypeDesc" tabindex="100" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 점검시작시간 -->
			<div class="field">
				<label class="lastcheck"><bean:message key="LABEL.pmiFromTime"/></label>
				<div class="datetime_wrap">
					<div class="input_box input_carendar">
						<html:text property="workPmiDetailDTO.startDate" tabindex="110" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box">
						<html:text property="workPmiDetailDTO.startTime" tabindex="120"/>
						<p class="open_time"><span>시간</span></p>
					</div>
				</div>
			</div>
			<!-- 담당자 -->
			<div class="field">
				<label class="lastcheck"><bean:message key="LABEL.manager"/></label>
				<div class="input_box">
					<html:text property="workPmiDetailDTO.empDesc" tabindex="150"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 점검종료시간-->
			<div class="field">
				<label class="lastcheck"><bean:message key="LABEL.pmiToTime"/></label>
				<div class="datetime_wrap">
					<div class="input_box input_carendar">
						<html:text property="workPmiDetailDTO.endDate" tabindex="130" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box">
						<html:text property="workPmiDetailDTO.endTime" tabindex="140" />
						<p class="open_time"><span>시간</span></p>
					</div>
				</div>
			</div>
			<!-- 점검시간(분) -->
			<div class="field">
				<label><bean:message key="LABEL.pmiWorkTime"/></label>
				<div class="input_box">
					<html:text property="workPmiDetailDTO.workTime" tabindex="160" styleClass="num"/>
				</div>
			</div>
			<!-- 측정시간 -->
			<div class="field">
             	<label><bean:message key="LABEL.measureTime"/></label>
             	<div class="input_read">
             		<html:text property="workPmiDetailDTO.measureTime" readonly="true" />
             	</div>
             </div>
			<!-- 생성시간 -->
			<div class="field">
				<label><bean:message key="LABEL.createDate"/></label>
				<div class="input_read" >
					<html:text property="workPmiDetailDTO.creDate" tabindex="165" readonly="true"/>
				</div>
			</div>		
			<!-- 생성자 -->
			<div class="field">
				<label><bean:message key="LABEL.creBy"/></label>
				<div class="input_read" >
					<html:text property="workPmiDetailDTO.creBy" tabindex="165" readonly="true"/>
				</div>
			</div>				
			<!-- 수정시간 -->
			<div class="field">
				<label><bean:message key="LABEL.updateDate"/></label>
				<div class="input_read" >
					<html:text property="workPmiDetailDTO.updDate" tabindex="168" readonly="true"/>
				</div>
			</div>             
			<!-- 수정자 -->
			<div class="field">
				<label><bean:message key="LABEL.modifyBy"/></label>
				<div class="input_read" >
					<html:text property="workPmiDetailDTO.updBy" tabindex="165" readonly="true"/>
				</div>
			</div>				
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="workPmiDetailDTO.remark" styleClass="ta50" tabindex="170" />
				</div>
			</div>
			<c:set var="filePath" value="enhance/${compName}/work/pmi/list/workPmiDetail_${compNo}.jsp" />
			<c:if test="${udf:isExist(filePath)}">
				<c:import charEncoding="UTF-8" url="/enhance/${compName}/work/pmi/list/workPmiDetail_${compNo}.jsp"></c:import>
			</c:if>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
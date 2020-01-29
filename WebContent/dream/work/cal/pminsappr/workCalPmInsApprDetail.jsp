<%--===========================================================================
 - 상세
author  kim21017
version $Id:$
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
<%@ page import="dream.work.cal.pminsappr.action.WorkCalPmInsApprDetailAction" %>
<%@ page import="common.bean.User"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html>
<head>
<title><bean:message key='LABEL.title' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<script language="javascript">

var deptAc, plantAc;
function loadPage()
{
	
	if(ckCreate(currentPageId)) goInput();
	else
	{
		goUpdate();
	}
	setTitle("workCalPmInsApprDetailDTO.description");
	setForUpdate();

    /** 부서  */
    deptAc = new autoC({"workCalPmInsApprDetailDTO.deptDesc":"description"});
    deptAc.setAcConditionMap({
        "comp_no":loginUser.compNo
       });
    deptAc.setTable("TADEPT");
    deptAc.setKeyName("workCalPmInsApprDetailDTO.deptId");
    deptAc.setAcResultMap({
        "workCalPmInsApprDetailDTO.deptId":"dept_id"
      /* , "workCalPmInsApprDetailDTO.plantId":"plant" */
    });
    deptAc.init();
    
    plantAc = new autoC({"workCalPmInsApprDetailDTO.plantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "workCalPmInsApprDetailDTO.plantId":"plant"
    });
    plantAc.setKeyName("workCalPmInsApprDetailDTO.plantId");
    plantAc.init();
    

    // 점검계획승인 구분
    acSysDesc("workCalPmInsApprDetailDTO.pminsschedapprTypeDesc","workCalPmInsApprDetailDTO.pminsschedapprType","PMINSSCHEDAPPR_TYPE");
}

function afterBtnLoad()
{
	if(workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.pmInsSchedAppStatusId'].value=="W"||
			workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.pmInsSchedAppStatusId'].value=="DA"){
		$(".b_fix").show();
		$(".b_approval").show();
		$(".b_save").show();
	}else{
		$(".b_fix").hide();
		$(".b_approval").hide();
		$(".b_save").hide();
	}
	if(workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.updBy'].value!=loginUser.empId){
		$(".b_approval").hide();
	}
	
}
function goTabPage(pageId)
{
	var form = document.workCalPmInsApprDetailForm;
	
	if(pageId == "maAppPrcList" || pageId == "appPrcList") {
		workCalPmInsApprDetailForm.elements['appReqCommonDTO.objectId'].value = workCalPmInsApprDetailForm.elements['workCalPmInsApprCommonDTO.pmInsSchedApprId'].value;
		workCalPmInsApprDetailForm.elements['appReqCommonDTO.apprType'].value = "PMINSSCHED";
		goCommonTabPage(form, '' , pageId);
	}
	else
	goCommonTabPage(form, '' , pageId);  
}

function goInput()
{
	setEnable(document.getElementsByName("disableDiv"));
	
	// 수정여부 true
	getTopPage().updateArray[currentPageId] = "AUTH";
	
	sequenceNextVal('SQAPMINSSCHEDAPPR_ID');
	
	workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.pmInsSchedAppStatusId'].value   = 'W';
	workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.pmInsSchedAppStatusDesc'].value = 'W';
	valSysDir('workCalPmInsApprDetailDTO.pmInsSchedAppStatusId', 'workCalPmInsApprDetailDTO.pmInsSchedAppStatusDesc', 'PMINSSCHEDAPP_STATUS', true);

	workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.updDate'].value   = getToday();
	workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.updBy'].value   = loginUser.empId;
	workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.updDesc'].value   = loginUser.empName;
	
	// 점검일자, 부서, 공장코드, 제목 넣어주기
// 	workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.startDate'].value   = getToday();
// 	workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.endDate'].value   = getToday();
	workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.deptId'].value   = loginUser.deptId;
	workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.deptDesc'].value   = loginUser.deptDesc;
	
	workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.plantId'].value   = loginUser.plant;
	workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.plantDesc'].value   = loginUser.plantDesc;
// 	workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.description'].value   = '['+getToday()+'] '+loginUser.deptDesc + ' ' + "<bean:message key='MENU.INSAPPR' />";

	// 계획/완료구분
	workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.pminsschedapprType'].value = workCalPmInsApprDetailForm.elements['workCalPmInsApprCommonDTO.pminsschedapprType'].value;
	workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.pminsschedapprTypeDesc'].value = workCalPmInsApprDetailForm.elements['workCalPmInsApprCommonDTO.pminsschedapprType'].value;
	valSysDir('workCalPmInsApprDetailDTO.pminsschedapprType', 'workCalPmInsApprDetailDTO.pminsschedapprTypeDesc', 'PMINSSCHEDAPPR_TYPE', true);

	//공장
	workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.plantId'].value   = loginUser.plant;
	workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.plantDesc'].value   = loginUser.plantDesc;
	$(".b_save").show();
	
}

function setSequenceVal(sequenceVal)
{
	workCalPmInsApprDetailForm.elements['workCalPmInsApprCommonDTO.pmInsSchedApprId'].value = sequenceVal;
	workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.pmInsSchedApprId'].value = sequenceVal;
	workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.pmInsSchedApprNo'].value = sequenceVal;
}

function goUpdate()
{
	setDisable(document.getElementsByName("disableDiv"));
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
	if(ckCreate(currentPageId)) workCalPmInsApprDetailForm.strutsAction.value = '<%=WorkCalPmInsApprDetailAction.DETAIL_INPUT%>';
	else workCalPmInsApprDetailForm.strutsAction.value = '<%=WorkCalPmInsApprDetailAction.DETAIL_UPDATE%>';

	//validSched();
	//if(isValid!='0') return;
	
	var actionUrl = contextPath + "/workCalPmInsApprDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(workCalPmInsApprDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
function afterSave(ajaxXmlDoc)
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================

	if (parent.findGridRow)	parent.findGridRow(workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.pmInsSchedApprId'].value);

	getTopPage().afterSaveAll(currentPageId);
	afterBtnLoad();
	
	if(typeof getParentIframe("workCalPmInsApprSchedList").goSearch == "function") getParentIframe("workCalPmInsApprSchedList").goSearch();
}

 /**
  * 승인요청
  */
 function goApproval()
 {
	 if(checkIsUpdate(document)){
		 alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
	 }else{
		 var woReqId = workCalPmInsApprDetailForm.elements['workCalPmInsApprCommonDTO.pmInsSchedApprId'].value;
		 var description = workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.description'].value;
		 appAction(woReqId, "PMINSSCHED", description);
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
	if (parent.findGridRow)	parent.findGridRow(workCalPmInsApprDetailForm.elements['workCalPmInsApprCommonDTO.pmInsSchedApprId'].value);
	goClose('workCalPmInsApprDetail');
 }
 
 /**
  * 확정
  */
function goFix()
{
	if(checkIsUpdate(document)){
        alertMessage1("<bean:message key='MESSAGE.MSG0036'/>");
    }
	else {
		getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG045'/>", function(result){
			 if(result){
				//================================
				// 필수 항목 체크한다.
				//================================
				if(checkValidation()) return;

				workCalPmInsApprDetailForm.strutsAction.value = '<%=WorkCalPmInsApprDetailAction.DETAIL_STATUS_UPDATE%>'; 

				var actionUrl = contextPath + "/workCalPmInsApprDetail.do";
				XMLHttpPost(actionUrl, FormQueryString(workCalPmInsApprDetailForm), 'afterFix');
			 }
			});
	}
}
 
/**
 * 확정 후 호출
 */
function afterFix(ajaxXmlDoc)
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	if (parent.findGridRow)	parent.findGridRow(workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.pmInsSchedApprId'].value);
	
	workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.pmInsSchedAppStatusId'].value = "C";
	workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.pmInsSchedAppStatusDesc'].value = "C";
	valSysDirCode('workCalPmInsApprDetailDTO.pmInsSchedAppStatusId', 'workCalPmInsApprDetailDTO.pmInsSchedAppStatusDesc', 'PMINSSCHEDAPP_STATUS', true);

	afterBtnLoad();
	
	getTopPage().afterSaveAll(currentPageId);
}

function validSched(){
	var actionUrl = contextPath + "/workCalPmInsApprDetail.do";
	var param =  "&strutsAction=" + '<%= WorkCalPmInsApprDetailAction.DETAIL_DUPLICATE_CHECK %>'
	          +  "&" + FormQueryString(workCalPmInsApprDetailForm);
	XMLHttpPostVal(actionUrl, param, 'afterValidSched');
}
var isValid;
function afterValidSched(ajaxXmlDoc)
{
	isValid = '0';
	isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
	if(isValid != '0')
    {
    	closeModal();
		alertMessage1("<bean:message key='MESSAGE.MSG0230'/>");
		workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.startDate'].value = '';
		workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.endDate'].value = '';
		workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.deptId'].value = '';
		workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.deptDesc'].value = '';
    }
}

/**
 * Date 세팅되고 호출되는 Callback
 */
function afterSetDate(_name)
{
	if(_name=="workCalPmInsApprDetailDTO.startDate"||_name=="workCalPmInsApprDetailDTO.endDate"){
		var startDate = workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.startDate'].value;
		var endDate = workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.endDate'].value;
		if(startDate != "" && endDate != "")
	 	{
			var sDate = new Date();
			sDate.setFullYear(startDate.substring(0,4));
			sDate.setMonth(Number(startDate.substring(5,7)) -1);
			sDate.setDate(startDate.substring(8,10));
			
			var eDate = new Date();
			eDate.setFullYear(endDate.substring(0,4));
			eDate.setMonth(Number(endDate.substring(5,7)) -1);
			eDate.setDate(endDate.substring(8,10));
			
			var caleDate = new Date();
			caleDate.setFullYear(endDate.substring(0,4));
			caleDate.setMonth(Number(endDate.substring(5,7)) -1);
			caleDate.setDate(endDate.substring(8,10));
			
			if(sDate>caleDate)
			{
				workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.endDate'].value = "";
	    		alertMessage1("<bean:message key='MESSAGE.MSG0110' />");
	    		workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.endDate'].focus();
	    		return;
			}else{
				workSearch();
			}
			
			var sDate = startDate.split("-");
			$('[name="workCalPmInsApprDetailDTO.yyyy"]').val(sDate[0]);
			$('[name="workCalPmInsApprDetailDTO.yyyymm"]').val(sDate[0]+"-"+sDate[1]);
	 	}
	}
}
function afterAutoCmpt(code)
{
	if(code=="workCalPmInsApprDetailDTO.deptDesc")
	{
		workSearch();
	}
}

function workSearch(){
	/* if(""!=workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.startDate'].value&&
		""!=workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.endDate'].value&&
		""!=workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.deptId'].value){
		getParentIframe("workCalPmInsApprWorkList", document).goSearch();
	} */
}

/* audit Trail */
function goAudtrailLink()
{	
	var objectId = workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.pmInsSchedApprId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}

function goInsschedLink()
{
	var deptId = workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.deptId'].value==''?'0':workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.deptId'].value;
	var eqLocId = "0"; 
	var yyyy = workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.startDate'].value.substring(0,4);
	
	reportCall('workCalPmRInsYearList','workCalPmRInsYearList', "<%=user.getCompNo()%>", "<%=user.getUserId()%>", "<%=user.getLangId()%>",yyyy,deptId,eqLocId); 
}

function afterSetYear(_name)
{
	var year = $('[name="workCalPmInsApprDetailDTO.yyyy"]').val();
	$('[name="workCalPmInsApprDetailDTO.yyyymm"]').val(year+"-01");
	$('[name="workCalPmInsApprDetailDTO.startDate"]').val(year+"-01-01");
	$('[name="workCalPmInsApprDetailDTO.endDate"]').val(year+"-12-"+getLastDayOfMonth(year, "12"));
}

function afterSetMonth(_name)
{
	var month = $('[name="workCalPmInsApprDetailDTO.yyyymm"]').val();
	$('[name="workCalPmInsApprDetailDTO.yyyy"]').val(month.substr(0,4));
	$('[name="workCalPmInsApprDetailDTO.startDate"]').val(month+"-01");
	$('[name="workCalPmInsApprDetailDTO.endDate"]').val(month+"-"+getLastDayOfMonth(month.substr(0,4), month.substr(5,2) ) );
}

/**
 * Print 버튼 클릭
 */
function goPrintLink()
{
	var pminsschedapprType = workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.pminsschedapprType'].value;

	var reportName = 'workCalPmInsApprDetail';
	var qrdName = 'workCalPmInsApprDetail';
 	var compNo = "<%=user.getCompNo()%>";
	var langId = "<%=user.getLangId()%>"; 
	
	var yyyy = dateToData(workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.yyyy'].value);
	var startDate = dateToData(workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.startDate'].value);
	var endDate = dateToData(workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.endDate'].value);
	var pmInsSchedAppr = "";

	if(pminsschedapprType == "PLN")
	{
		reportName = "workCalPmInsApprDetail";
		qrdName = "workCalPmInsApprDetail";
	}
	else if(pminsschedapprType == "ACT")
	{
		reportName = "workCalPmInsApprCompDetail";
		qrdName = "workCalPmInsApprCompDetail";

		pmInsSchedAppr = "C";
	}
	else if(pminsschedapprType == "NOT")
	{
		reportName = "workCalPmInsApprNOTDetail";
		qrdName = "workCalPmInsApprNOTDetail";

		pmInsSchedAppr = "C";
	}
	
	reportCall(reportName, qrdName, compNo, langId, yyyy, startDate, endDate, pmInsSchedAppr); 
}

/**
 * 표지출력 버튼 클릭(완료승인)
 */
function goCoverprintLink()
{
	var pminsschedapprType = workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.pminsschedapprType'].value;

	var reportName = 'workCalPmInsApprCompDetailCover';
	var qrdName = 'workCalPmInsApprCompDetailCover';
 	var compNo = "<%=user.getCompNo()%>";
	var langId = "<%=user.getLangId()%>"; 
	
	var yyyy = dateToData(workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.yyyy'].value);
	var startDate = dateToData(workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.startDate'].value);
	var endDate = dateToData(workCalPmInsApprDetailForm.elements['workCalPmInsApprDetailDTO.endDate'].value);
	var pmInsSchedAppr = "";

	if(pminsschedapprType == "ACT")
	{
		reportName = "workCalPmInsApprCompDetailCover";
		qrdName = "workCalPmInsApprCompDetailCover";

		pmInsSchedAppr = "C";
		
		reportCall(reportName, qrdName, compNo, langId, yyyy, startDate, endDate, pmInsSchedAppr); 
	}
	
}


/**
 * 필드 세팅후 년. 년월 달력버튼 활성화 
 */
// function afterFieldSet()
// {
// 	var yearObj = $('[name="workCalPmInsApprDetailDTO.yyyy"]');
	
// 	yearObj.prop("readonly",true);
// 	yearObj.parent(".input_box").removeClass("input_box").addClass("input_read");
	
// 	var monObj = $('[name="workCalPmInsApprDetailDTO.yyyymm"]');
	
// 	monObj.prop("readonly",true);
// 	monObj.parent(".input_box").removeClass("input_box").addClass("input_read");
// }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/workCalPmInsApprDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="workCalPmInsApprCommonDTO.pmInsSchedApprId" />
	<html:hidden property="workCalPmInsApprCommonDTO.pminsschedapprType" />
	<html:hidden property="workCalPmInsApprDetailDTO.pmInsSchedApprId" />
	<html:hidden property="workCalPmInsApprDetailDTO.pmInsSchedAppStatusId" />
	<html:hidden property="workCalPmInsApprDetailDTO.deptId" />
	<html:hidden property="workCalPmInsApprDetailDTO.updBy" />
	<html:hidden property="workCalPmInsApprDetailDTO.plantId" />
	<html:hidden property="workCalPmInsApprDetailDTO.pminsschedapprType" />
	
 	<html:hidden property="appReqCommonDTO.objectId"/>
	<html:hidden property="appReqCommonDTO.apprType"/>
	

	<div class="article_box">
		<div class="form_box">
			<!-- 승인번호 -->
			<div class="field" name="disableDiv">
				<label class="check"><bean:message key="LABEL.pmInsSchedApprNo"/></label>
				<div class="input_box">
					<html:text property="workCalPmInsApprDetailDTO.pmInsSchedApprNo" tabindex="10"/>
				</div>
			</div>	
			<!-- 상태 -->
			<div class="field">
                <label><bean:message key="LABEL.status"/></label>
                <div class="input_read">
                    <html:text property="workCalPmInsApprDetailDTO.pmInsSchedAppStatusDesc" tabindex="20" readonly="true"/>
                </div>
            </div>	
			<!-- 제목 -->
			<div class="field_long">
                <label><bean:message key="LABEL.title"/></label>
                <div class="input_box">
                    <html:text property="workCalPmInsApprDetailDTO.description" tabindex="30" />
                </div>
            </div>
			<!-- 년 -->
        	<div class="field" name="disableDiv">
				<label><bean:message key="LABEL.year"/></label>
				<div class="input_box">
					<html:text property="workCalPmInsApprDetailDTO.yyyy" tabindex="40" />
					<p class="open_year_calendar"><span>날짜</span></p>
				</div>
			</div>
			<!-- 년월 -->
			<div class="field" name="disableDiv">
        	 	<label><bean:message key="LABEL.yyyymm"/></label>
        	 	<div class="input_box">
        	 		<html:text property="workCalPmInsApprDetailDTO.yyyymm" tabindex="50" />
				    <p class="open_mon_calendar"><span>날짜</span></p>
        	 	</div>
        	</div>			
			<!-- 점검일자 -->
			<div class="field" name="disableDiv">
				<label class="check"><bean:message key="LABEL.inspectDate"/></label>
				<div class="calendar_wrap">
					<div class="input_box input_carendar">
						<html:text property="workCalPmInsApprDetailDTO.startDate" tabindex="60" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box input_carendar">
						<html:text property="workCalPmInsApprDetailDTO.endDate" tabindex="70" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
				</div>
			</div>
			<!-- 계획/완료 구분 -->
			<div class="field">
                <label class="check"><bean:message key="LABEL.pmInsSchedApprType"/></label>
                <div class="input_box">
                    <html:text property="workCalPmInsApprDetailDTO.pminsschedapprTypeDesc" tabindex="80" />
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
            <!-- 공장 -->
            <div class="field">
                <label><bean:message key="LABEL.plantDesc"/></label>
                <div class="input_box">
                    <html:text property="workCalPmInsApprDetailDTO.plantDesc" tabindex="90"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>			
			<!-- 부서 -->
			<div class="field">
                <label class="check"><bean:message key="LABEL.dept"/></label>
                <div class="input_box">
                    <html:text property="workCalPmInsApprDetailDTO.deptDesc" tabindex="100" />
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
			<!-- 작성일자 -->
			<div class="field">
                <label><bean:message key="LABEL.repRegDate"/></label>
                <div class="input_read">
                    <html:text property="workCalPmInsApprDetailDTO.updDate" tabindex="110" readonly="true" />
                </div>
            </div>
            <!-- 작성자 -->
            <div class="field">
                <label><bean:message key="LABEL.writeBy"/></label>
                <div class="input_read">
                    <html:text property="workCalPmInsApprDetailDTO.updDesc" tabindex="120" readonly="true"/>
                </div>
            </div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="workCalPmInsApprDetailDTO.remark" styleClass="ta150" tabindex="130" />
				</div>
			</div>
			<c:set var="filePath" value="enhance/${compName}/work/cal/pminsappr/workCalPmInsApprDetail_${compNo}.jsp" />
			<c:if test="${udf:isExist(filePath)}">
				<c:import charEncoding="UTF-8" url="/enhance/${compName}/work/cal/pminsappr/workCalPmInsApprDetail_${compNo}.jsp"></c:import>
			</c:if>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
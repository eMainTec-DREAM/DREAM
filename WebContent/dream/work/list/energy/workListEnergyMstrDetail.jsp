<%--===========================================================================
점검작업 - 상세
author  kim21017
version $Id: workListEnergyMstrDetail.jsp,v 1.0 2015/12/04 04:13:54 kim21017 Exp $
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
<%@ page import="dream.work.list.energy.action.WorkListEnergyMstrDetailAction"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html>
<head>
<!-- WO # -->
<title><bean:message key='TAB.workListEnergyMstrDetail' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
var saveStrutsAction;

/** 자동완성 변수  */
var equipDescAc;
var empAc;
var deptAc;
var wkCtrDescAc;

function loadPage() 
{	
	setTitle("workListEnergyMstrDetailDTO.pminslistId","workListEnergyMstrDetailDTO.pmiDesc");
	
	setForUpdate();
    setBtnAsStatus();
	
    // 작업부서
    deptAc = new autoC({"workListEnergyMstrDetailDTO.deptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    deptAc.setAcDConditionMap({
    	"plant" : "workListEnergyMstrDetailDTO.plant"
    });
    deptAc.setTable("TADEPT");
    deptAc.setKeyName("workListEnergyMstrDetailDTO.deptId");
    deptAc.setAcResultMap({
        "workListEnergyMstrDetailDTO.deptId":"dept_id"
    });
    deptAc.init();
    
    // 작업그룹
    wkCtrDescAc = new autoC({"workListEnergyMstrDetailDTO.wkCtrDesc":"description"});
    wkCtrDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    wkCtrDescAc.setTable("TAWKCTR");
    wkCtrDescAc.setAcResultMap({
        "workListEnergyMstrDetailDTO.wkCtrId":"wkctr_id"
    });
    wkCtrDescAc.init();
    
    // 담당자
    empAc = new autoC({"workListEnergyMstrDetailDTO.empDesc":"emp_name"});
    empAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
  	   });
    empAc.setAcDConditionMap({
    	"plant" : "workListEnergyMstrDetailDTO.plant"
    });
    empAc.setTable("TAEMP");
    empAc.setKeyName("workListEnergyMstrDetailDTO.empId");
    empAc.setAcResultMap({
        "workListEnergyMstrDetailDTO.empId":"emp_id"
    });
    empAc.init();
 
    // 측정시간
    acSysDesc("workListEnergyMstrDetailDTO.measureTime","workListEnergyMstrDetailDTO.measureTime","MEASURE_TIME",true);
 
	goUpdate();
    
}

function setBtnAsStatus()
{
	if(workListEnergyMstrDetailForm.elements['workListEnergyMstrDetailDTO.pmschedStatusId'].value=="C")
	{
		setDisableAll();
		hideBtn('CONFIRM');
		showBtn("CANCEL");
	}
	else if(workListEnergyMstrDetailForm.elements['workListEnergyMstrDetailDTO.pmschedStatusId'].value=="IRWRA"
			|| workListEnergyMstrDetailForm.elements['workListEnergyMstrDetailDTO.pmschedStatusId'].value=="IRWOA")
	{
		setDisableAll();
		showBtn("CONFIRM");
		hideBtn('CANCEL');
	}
	else{
		setEnableAll();
		showBtn("CONFIRM");
		hideBtn('CANCEL');
	}
	   	
}

function goUpdate()
{	
	workListEnergyMstrDetailForm.elements['workListEnergyMstrDetailDTO.pmschedStatusDesc'].readOnly="true";
	
	//예방점검기준서에 의해 생성된 작업결과는 설비를 변경하지못함.
	workListEnergyMstrDetailForm.elements['workListEnergyMstrDetailDTO.equipDesc'].readOnly = true;
    document.getElementById("equipDiv").className = "input_read"; 
    document.getElementById("equipPopDiv").style.display = "none";
    
    if(workListEnergyMstrDetailForm.elements['workListEnergyMstrDetailDTO.pmschedStatusId'].value=="C")
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
	var form = document.workListEnergyMstrDetailForm;
	
	workListEnergyMstrDetailForm.elements['workListEnergyMstrListCommonDTO.pmschedStatusId'].value = workListEnergyMstrDetailForm.elements['workListEnergyMstrDetailDTO.pmschedStatusId'].value;
	goCommonTabPage(form, '' , pageId);
	
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
	
	workListEnergyMstrDetailForm.strutsAction.value = '<%=WorkListEnergyMstrDetailAction.WORK_PMU_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/workListEnergyMstrDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(workListEnergyMstrDetailForm), 'afterSave');
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	workListEnergyMstrDetailForm.elements['workListEnergyMstrDetailDTO.pminslistId'].value = workListEnergyMstrDetailForm.elements['workListEnergyMstrListCommonDTO.pminslistId'].value;
	
	if (parent.findGridRow)	parent.findGridRow(workListEnergyMstrDetailForm.elements['workListEnergyMstrDetailDTO.pminslistId'].value);
	
	getTopPage().afterSaveAll(currentPageId);
	//저장후 완료버튼 보이기
	$(".b_confirm").css("display","");
}
 
/**
 * 점검항목의 모든 결과값이 들어있는지 확인.
 */
var isValid = 0;
function checkPoint(_callback)
{
	isValid=0;
	
	var actionUrl = contextPath + "/workListEnergyMstrDetail.do";
    var param =  "&strutsAction=" + '<%= WorkListEnergyMstrDetailAction.WORK_PMU_DETAIL_CHECKPOINT %>'
              +  "&" + FormQueryString(workListEnergyMstrDetailForm);
    XMLHttpPostVal(actionUrl, param, _callback);
    
}
function setValidCheckPoint(ajaxXmlDoc)
{
	isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
 	if(isValid == '0'){
 		// 확정하기전에 미래에 확정된 작업 있는지 확인
 		workListEnergyMstrDetailForm.strutsAction.value = '<%=WorkListEnergyMstrDetailAction.WORK_PMU_DETAIL_CHECK_CONFIRM%>';
 		var actionUrl = contextPath + "/workListEnergyMstrDetail.do";
 			XMLHttpPost(actionUrl, FormQueryString(workListEnergyMstrDetailForm), 'afterConfirmCheck');
	}else{
    	alertMessage1("<bean:message key='MESSAGE.MSG0112' />");
     	return;
    }
}

function afterConfirmCheck(ajaxXmlDoc)
{
	isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
 	if(isValid == '0'){
 		workListEnergyMstrDetailForm.strutsAction.value = '<%=WorkListEnergyMstrDetailAction.WORK_PMU_DETAIL_COMPLETE%>';
 		var actionUrl = contextPath + "/workListEnergyMstrDetail.do";
 			XMLHttpPost(actionUrl, FormQueryString(workListEnergyMstrDetailForm), 'afterConfirm');
	}else{
    	alertMessage1("<bean:message key='MESSAGE.MSG1008' />");
     	return;
    }
}


/**
 * 선택한 데이타가 최종 등록 값인지 확인.
 */
function isLastPoint(_callback)
{
	isValid=0;
	
	var actionUrl = contextPath + "/workListEnergyMstrDetail.do";
    var param =  "&strutsAction=" + '<%= WorkListEnergyMstrDetailAction.WORK_PMU_DETAIL_CHECK_CONFIRM %>'
              +  "&" + FormQueryString(workListEnergyMstrDetailForm);
    XMLHttpPostVal(actionUrl, param, _callback);
    
}
function setValidIsLastPoint(ajaxXmlDoc)
{
	isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
 	if(isValid == '0'){
 		workListEnergyMstrDetailForm.strutsAction.value = '<%=WorkListEnergyMstrDetailAction.WORK_PMU_DETAIL_COMPLETE_CANCEL%>';
 		var actionUrl = contextPath + "/workListEnergyMstrDetail.do";
 			XMLHttpPost(actionUrl, FormQueryString(workListEnergyMstrDetailForm), 'afterCancel');
	}else{
    	alertMessage1("<bean:message key='MESSAGE.MSG0272' />");
     	return;
    }
 	
}

/**
 * 작업완료
 */
function goConfirm()
{
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
	workListEnergyMstrDetailForm.elements['workListEnergyMstrDetailDTO.pmschedStatusId'].value = "C";
	valSysDirCode('workListEnergyMstrDetailDTO.pmschedStatusId', 'workListEnergyMstrDetailDTO.pmschedStatusDesc', 'PMSCHED_STATUS', true);
	
 	if (parent.findGridRow)	parent.findGridRow(workListEnergyMstrDetailForm.elements['workListEnergyMstrDetailDTO.pminslistId'].value);
	
 	if(workListEnergyMstrDetailForm.elements['workListEnergyMstrDetailDTO.pmschedStatusId'].value=="C")
 	{
 		$('.b_confirm').hide();	
 	}
 	setBtnAsStatus();
}

/**
 * 작업완료 취소
 */
function goCancel()
{
	getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0231'/>", function(result){
		if(result){
			isLastPoint('setValidIsLastPoint');
		 }
	});
	
}
/**
 * 완료 취소후 호출
 */
function afterCancel(ajaxXmlDoc)
{	
 	alertMessage1("<bean:message key='MESSAGE.CMSG034'/>");
	//작업상태 = P - 작업대기
	workListEnergyMstrDetailForm.elements['workListEnergyMstrDetailDTO.pmschedStatusId'].value = "P";
	valSysDirCode('workListEnergyMstrDetailDTO.pmschedStatusId', 'workListEnergyMstrDetailDTO.pmschedStatusDesc', 'PMSCHED_STATUS', true);
	
 	if (parent.findGridRow)	parent.findGridRow(workListEnergyMstrDetailForm.elements['workListEnergyMstrDetailDTO.pminslistId'].value);

	setBtnAsStatus();
}

  
/* audit Trail */
function goAudtrailLink()
{
	var objectId = workListEnergyMstrDetailForm.elements['workListEnergyMstrDetailDTO.pminslistId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}

/*
 * 기준서 보기 WOPMPRESULT
 */
function goPmstdLink()
{
	var fileName = workListEnergyMstrDetailForm.elements['workListEnergyMstrDetailDTO.param'].value;
	var pmId     = workListEnergyMstrDetailForm.elements['workListEnergyMstrDetailDTO.pmId'].value;

	goPmstdList(pmId, fileName);
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/workListEnergyMstrDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="workListEnergyMstrListCommonDTO.pminslistId" />
	<html:hidden property="workListEnergyMstrListCommonDTO.pmschedStatusId" />
	<html:hidden property="workListEnergyMstrListCommonDTO.pmId"/>
	<html:hidden property="workListEnergyMstrDetailDTO.pmschedStatusId" />
	<html:hidden property="workListEnergyMstrDetailDTO.deptId" />
	<html:hidden property="workListEnergyMstrDetailDTO.empId" />
 	<html:hidden property="workListEnergyMstrDetailDTO.wkCtrId" />
 	<html:hidden property="workListEnergyMstrDetailDTO.pmInscchedId" />
 	<html:hidden property="workListEnergyMstrDetailDTO.param" />
 	<html:hidden property="workListEnergyMstrDetailDTO.pmId" />
 
	<div class="article_box">
		<div class="form_box">
			<!-- 작업No -->
			<div class="field">
				<label><bean:message key="LABEL.workNo"/></label>
				<div class="input_read">
					<html:text property="workListEnergyMstrDetailDTO.pminslistId" tabindex="10" readonly="true" />
				</div>
			</div>
			<!-- 작업상태 -->
			<div class="field">
				<label><bean:message key="LABEL.woStatus"/></label>
				<div class="input_read">
					<html:text property="workListEnergyMstrDetailDTO.pmschedStatusDesc" tabindex="20" readonly="true" />
				</div>
			</div>
			<!-- 설비 -->
			<div class="field">
				<label><bean:message key="LABEL.equipment"/></label>
				<div class="input_box" id="equipDiv">
					<html:text property="workListEnergyMstrDetailDTO.equipDesc" tabindex="30" readonly="true"/>
					<p class="open_spop" id="equipPopDiv">
						<a><span>조회</span></a>
					</p>
				</div>
			</div>
			<!-- 작업형태  -->
			<div class="field">
				<label><bean:message key="LABEL.pmType"/></label>
				<div class="input_read">
					<html:text property="workListEnergyMstrDetailDTO.pmTypeDesc" tabindex="40" readonly="true" />
				</div>
			</div>
			<!-- 기준#  -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.periodNo"/></label>
				<div class="input_box">
					<html:text property="workListEnergyMstrDetailDTO.pmNo" tabindex="50" readonly="true" />
				</div>
			</div>
			<!-- 작업명 -->
			<div class="field_long">
				<label class="check"><bean:message key="LABEL.woName"/></label>
				<div class="input_box">
					<html:text property="workListEnergyMstrDetailDTO.pmiDesc" tabindex="60"/>
				</div>
			</div>
			<!-- 일자 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.workDate"/></label>
				<div class="input_box">
					<html:text property="workListEnergyMstrDetailDTO.wkorDate" tabindex="70" />
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			<!-- 시간대 -->
			<div class="field">
				<label><bean:message key="LABEL.timeslot"/></label>
				<div class="input_box">
					<html:text property="workListEnergyMstrDetailDTO.measureTime" tabindex="80"/>
					<p class="open_time"><a><span>시간</span></a></p>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>				
			</div>			
			<!-- 부서 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.dept"/></label>
				<div class="input_box">
					<html:text property="workListEnergyMstrDetailDTO.deptDesc" tabindex="90"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>            
			<!-- 작업그룹 -->
			<div class="field">
				<label><bean:message key="LABEL.wkCtr"/></label>
				<div class="input_box">
					<html:text property="workListEnergyMstrDetailDTO.wkCtrDesc" tabindex="100"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 담당자 -->
			<div class="field">
				<label><bean:message key="LABEL.manager"/></label>
				<div class="input_box">
					<html:text property="workListEnergyMstrDetailDTO.empDesc" tabindex="120"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="workListEnergyMstrDetailDTO.remark" styleClass="ta50" tabindex="130" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
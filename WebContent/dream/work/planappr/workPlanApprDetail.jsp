<%--===========================================================================
작업계획승인 - 상세
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.work.planappr.action.WorkPlanApprDetailAction" %>
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
	//결재보기에서 열었을때.
// 	if(parent.currentPageId == "appReadyList") setDisableAll();
	
	if(ckCreate(currentPageId)) goInput();
	else
	{
		goUpdate();
	}
	setTitle("workPlanApprDetailDTO.description");
	setForUpdate();

    /** 부서  */
    deptAc = new autoC({"workPlanApprDetailDTO.deptDesc":"description"});
    deptAc.setAcConditionMap({
        "comp_no":loginUser.compNo
       });
    deptAc.setTable("TADEPT");
    deptAc.setKeyName("workPlanApprDetailDTO.deptId");
    deptAc.setAcResultMap({
        "workPlanApprDetailDTO.deptId":"dept_id"
    });
    deptAc.init();
    
    plantAc = new autoC({"workPlanApprDetailDTO.plantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setKeyName("workPlanApprDetailDTO.plantId");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "workPlanApprDetailDTO.plantId":"plant"
    });
    plantAc.init();
}

/**
 * 
 */
function afterSetYear(_name)
{
	var year = $('[name="workPlanApprDetailDTO.yyyy"]').val();
	$('[name="workPlanApprDetailDTO.yyyymm"]').val(year+"-01");
	$('[name="workPlanApprDetailDTO.startDate"]').val(year+"-01-01");
	$('[name="workPlanApprDetailDTO.endDate"]').val(year+"-12-"+getLastDayOfMonth(year, "12"));
}

function afterSetMonth(_name)
{
	var month = $('[name="workPlanApprDetailDTO.yyyymm"]').val();
	$('[name="workPlanApprDetailDTO.yyyy"]').val(month.substr(0,4));
	$('[name="workPlanApprDetailDTO.startDate"]').val(month+"-01");
	$('[name="workPlanApprDetailDTO.endDate"]').val(month+"-"+getLastDayOfMonth(month.substr(0,4), month.substr(5,2) ) );
}

function afterBtnLoad()
{
	if(!ckCreate(currentPageId)) 
	{
		if(workPlanApprDetailForm.elements['workPlanApprDetailDTO.woPlanApprStatusId'].value=="W"||
			workPlanApprDetailForm.elements['workPlanApprDetailDTO.woPlanApprStatusId'].value=="DA"){
			$(".b_confirm").show();
			//$(".b_apprequest").show();
			showBtn("APPREQUEST");
			$(".b_save").show();
	
			setEnableAll();
		}
		else{
			$(".b_confirm").hide();
			$(".b_apprequest").hide();
			$(".b_save").hide();
			
			setDisableAll();
		}
	}
	
	if(workPlanApprDetailForm.elements['workPlanApprDetailDTO.updBy'].value!=loginUser.empId){
		//$(".b_apprequest").hide();
	}
	
}
function goTabPage(pageId)
{
	var form = document.workPlanApprDetailForm;
	
	if(pageId == "maAppPrcList" || pageId == "appPrcList") {
		workPlanApprDetailForm.elements['appReqCommonDTO.objectId'].value = workPlanApprDetailForm.elements['workPlanApprCommonDTO.woPlanApprId'].value;
		workPlanApprDetailForm.elements['appReqCommonDTO.apprType'].value = "WOPLANAPPR";
		goCommonTabPage(form, '' , pageId);
	}
	else
		goCommonTabPage(form, '' , pageId);  
}

function goInput()
{
	setEnable(document.getElementsByName("disableDiv"));

	sequenceNextVal('SQAWOPLANAPPR_ID');
	
	workPlanApprDetailForm.elements['workPlanApprDetailDTO.woPlanApprStatusId'].value   = 'W';
	workPlanApprDetailForm.elements['workPlanApprDetailDTO.woPlanApprStatusDesc'].value = 'W';
	valSysDir('workPlanApprDetailDTO.woPlanApprStatusId', 'workPlanApprDetailDTO.woPlanApprStatusDesc', 'WOPLANAPPR_STATUS', true);

	workPlanApprDetailForm.elements['workPlanApprDetailDTO.updDate'].value   = getToday();
	workPlanApprDetailForm.elements['workPlanApprDetailDTO.updBy'].value   = loginUser.empId;
	workPlanApprDetailForm.elements['workPlanApprDetailDTO.updDesc'].value   = loginUser.empName;
	
	afterBtnLoad();
}

function setSequenceVal(sequenceVal)
{
	workPlanApprDetailForm.elements['workPlanApprCommonDTO.woPlanApprId'].value = sequenceVal;
	workPlanApprDetailForm.elements['workPlanApprDetailDTO.woPlanApprId'].value = sequenceVal;
}

function goUpdate()
{
	setDisable(document.getElementsByName("disableDiv"));

	goTabPage("workPlanApprWorkList");
	goTabPage("workPlanApprEquipList");
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
	if(ckCreate(currentPageId)) workPlanApprDetailForm.strutsAction.value = '<%=WorkPlanApprDetailAction.DETAIL_INPUT%>';
	else workPlanApprDetailForm.strutsAction.value = '<%=WorkPlanApprDetailAction.DETAIL_UPDATE%>';

	//validSched();
	//if(isValid!='0') return;
	
	var actionUrl = contextPath + "/workPlanApprDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(workPlanApprDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
function afterSave(ajaxXmlDoc)
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================

	if (parent.findGridRow)	parent.findGridRow(workPlanApprDetailForm.elements['workPlanApprDetailDTO.woPlanApprId'].value);

	getTopPage().afterSaveAll(currentPageId);
	
	getParentIframe("workPlanApprWorkList").goSearch();
	getParentIframe("workPlanApprEquipList").goSearch();
}

 /**
  * 승인요청
  */
 function goApprequest()
 {
	 if(checkIsUpdate(document)){
		 alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
	 }else{
		 var woPlanApprId = workPlanApprDetailForm.elements['workPlanApprCommonDTO.woPlanApprId'].value;
		 var description = workPlanApprDetailForm.elements['workPlanApprDetailDTO.description'].value;
		 appAction(woPlanApprId, "WOPLANAPPR", description);
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
	if (parent.findGridRow)	parent.findGridRow(workPlanApprDetailForm.elements['workPlanApprCommonDTO.woPlanApprId'].value);
	goClose('workPlanApprDetail');
 }
 
 /**
  * 확정
  */
function goConfirm()
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

				workPlanApprDetailForm.strutsAction.value = '<%=WorkPlanApprDetailAction.DETAIL_STATUS_UPDATE%>'; 

				var actionUrl = contextPath + "/workPlanApprDetail.do";
				XMLHttpPost(actionUrl, FormQueryString(workPlanApprDetailForm), 'afterConfirm');
			 }
			});
	}
}
 
/**
 * 확정 후 호출
 */
function afterConfirm(ajaxXmlDoc)
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	if (parent.findGridRow)	parent.findGridRow(workPlanApprDetailForm.elements['workPlanApprDetailDTO.woPlanApprId'].value);
	
	workPlanApprDetailForm.elements['workPlanApprDetailDTO.woPlanApprStatusId'].value = "C";
	workPlanApprDetailForm.elements['workPlanApprDetailDTO.woPlanApprStatusDesc'].value = "C";
	valSysDirCode('workPlanApprDetailDTO.woPlanApprStatusId', 'workPlanApprDetailDTO.woPlanApprStatusDesc', 'WOPLANAPPR_STATUS', true);

	afterBtnLoad();
	
	getTopPage().afterSaveAll(currentPageId);
}

function validSched(){
	var actionUrl = contextPath + "/workPlanApprDetail.do";
	var param =  "&strutsAction=" + '<%= WorkPlanApprDetailAction.DETAIL_DUPLICATE_CHECK %>'
	          +  "&" + FormQueryString(workPlanApprDetailForm);
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
		workPlanApprDetailForm.elements['workPlanApprDetailDTO.startDate'].value = '';
		workPlanApprDetailForm.elements['workPlanApprDetailDTO.endDate'].value = '';
		workPlanApprDetailForm.elements['workPlanApprDetailDTO.deptId'].value = '';
		workPlanApprDetailForm.elements['workPlanApprDetailDTO.deptDesc'].value = '';
    }
}

/**
 * Date 세팅되고 호출되는 Callback
 */
function afterSetDate(_name)
{
	if(_name=="workPlanApprDetailDTO.startDate"||_name=="workPlanApprDetailDTO.endDate"){
		var startDate = workPlanApprDetailForm.elements['workPlanApprDetailDTO.startDate'].value;
		var endDate = workPlanApprDetailForm.elements['workPlanApprDetailDTO.endDate'].value;
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
				workPlanApprDetailForm.elements['workPlanApprDetailDTO.endDate'].value = "";
	    		alertMessage1("<bean:message key='MESSAGE.MSG0110' />");
	    		workPlanApprDetailForm.elements['workPlanApprDetailDTO.endDate'].focus();
	    		return;
			}else{
				workSearch();
			}
			
			var sDate = startDate.split("-");
			$('[name="workPlanApprDetailDTO.yyyy"]').val(sDate[0]);
			$('[name="workPlanApprDetailDTO.yyyymm"]').val(sDate[0]+"-"+sDate[1]);
	 	}
		
		
	}
}

function afterFieldSet()
{
	var yearObj = $('[name="workPlanApprDetailDTO.yyyy"]');
	
	yearObj.prop("readonly",true);
	//yearObj.parent(".input_box").removeClass("input_box").addClass("input_read");
	
	var monObj = $('[name="workPlanApprDetailDTO.yyyymm"]');
	
	monObj.prop("readonly",true);
	//monObj.parent(".input_box").removeClass("input_box").addClass("input_read");
}

function afterAutoCmpt(code)
{
	if(code=="workPlanApprDetailDTO.deptDesc")
	{
		workSearch();
	}
}

function workSearch(){
	/* if(""!=workPlanApprDetailForm.elements['workPlanApprDetailDTO.startDate'].value&&
		""!=workPlanApprDetailForm.elements['workPlanApprDetailDTO.endDate'].value&&
		""!=workPlanApprDetailForm.elements['workPlanApprDetailDTO.deptId'].value){
		getParentIframe("workPlanApprWorkList", document).goSearch();
	} */
}

/* audit Trail */
function goAudtrailLink()
{		
	var objectId = workPlanApprDetailForm.elements['workPlanApprDetailDTO.woPlanApprId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}


//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/workPlanApprDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="workPlanApprCommonDTO.woPlanApprId" />
	<html:hidden property="workPlanApprDetailDTO.woPlanApprId" />
	<html:hidden property="workPlanApprDetailDTO.woPlanApprStatusId" />
	<html:hidden property="workPlanApprDetailDTO.deptId" />
	<html:hidden property="workPlanApprDetailDTO.updBy" />
	<html:hidden property="workPlanApprDetailDTO.plantId" />
 	<html:hidden property="appReqCommonDTO.objectId"/>
	<html:hidden property="appReqCommonDTO.apprType"/>
	
	<html:hidden property="workPlanApprCommonDTO.woplanapprType" />
	<html:hidden property="workPlanApprCommonDTO.woType"/>

	<div class="article_box">
		<div class="form_box">
			<!-- 작업일자 -->
			<div class="field">
        	 	<label class="check"><bean:message key="LABEL.yyyymm"/></label>
        	 	<div class="input_read">
        	 		<html:text property="workPlanApprDetailDTO.yyyymm" readonly="true" tabindex="10"/>
				    <p class="open_mon_calendar"><span>날짜</span></p>
        	 	</div>
        	 </div>
			<div class="field" name="disableDiv">
				<label class="check"><bean:message key="LABEL.wkorDate"/></label>
				<div class="calendar_wrap">
					<div class="input_box input_carendar">
						<html:text property="workPlanApprDetailDTO.startDate" tabindex="10" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box input_carendar">
						<html:text property="workPlanApprDetailDTO.endDate" tabindex="20" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
				</div>
			</div>
			<div class="field">
				<label class="check"><bean:message key="LABEL.year"/></label>
				<div class="input_read">
					<html:text property="workPlanApprDetailDTO.yyyy" readonly="true"/>
					<p class="open_year_calendar"><span>날짜</span></p>
				</div>
			</div>
			<!-- 상태 -->
			<div class="field">
                <label><bean:message key="LABEL.status"/></label>
                <div class="input_read">
                    <html:text property="workPlanApprDetailDTO.woPlanApprStatusDesc" tabindex="30" readonly="true"/>
                </div>
            </div>
			<!-- 부서 -->
			<div class="field" name="disableDiv">
                <label class="check"><bean:message key="LABEL.dept"/></label>
                <div class="input_box">
                    <html:text property="workPlanApprDetailDTO.deptDesc" tabindex="40" />
                    <p class="open_spop">
                        <a><span>조회</span></a>
                    </p>
                </div>
            </div>
			<!-- 제목 -->
			<div class="field_long">
                <label class="check"><bean:message key="LABEL.title"/></label>
                <div class="input_box">
                    <html:text property="workPlanApprDetailDTO.description" tabindex="50" />
                </div>
            </div>
			<!-- 작성일자 -->
			<div class="field">
                <label><bean:message key="LABEL.repRegDate"/></label>
                <div class="input_read">
                    <html:text property="workPlanApprDetailDTO.updDate" tabindex="60" readonly="true" />
                </div>
            </div>
            
            <!-- 작성자 -->
            <div class="field">
                <label><bean:message key="LABEL.writeBy"/></label>
                <div class="input_read">
                    <html:text property="workPlanApprDetailDTO.updDesc" tabindex="70" readonly="true"/>
                </div>
            </div>
            <!-- 공장 -->
            <div class="field">
                <label class="check"><bean:message key="LABEL.plantDesc"/></label>
                <div class="input_box">
                    <html:text property="workPlanApprDetailDTO.plantDesc" tabindex="70"/>
                    <p class="open_spop">
                        <a><span>조회</span></a>
                    </p>
                </div>
            </div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="workPlanApprDetailDTO.remark" styleClass="ta150" tabindex="80" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
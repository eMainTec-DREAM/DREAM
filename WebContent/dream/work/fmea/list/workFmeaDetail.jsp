<%--===========================================================================
고장영향성 평가
author  kim21017
version $Id: workFmeaDetail.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.fmea.list.action.WorkFmeaDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<html:html>
<head>
<!-- No. -->
<title><bean:message key='LABEL.number'/></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */
var equipAc;
var reqByAc;
var reviewByAc;

function loadPage() 
{	
    setTitle("workFmeaDetailDTO.woFmeaNo", "workFmeaDetailDTO.equipDesc");
    //For Save
    setForUpdate();
    
  //설비 AC
    equipAc = new autoC({"workFmeaDetailDTO.equipDesc":"description"});
    equipAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipAc.setAcResultMap({
        "workFmeaDetailDTO.equipId":"equip_id"
       ,"workFmeaDetailDTO.eqLocId":"eqloc_id"
       ,"workFmeaDetailDTO.eqLocDesc":"eqLocDesc"
    });
    equipAc.setTable("TAEQUIPMENT");
    equipAc.setKeyName("workFmeaDetailDTO.equipId");
    equipAc.init();

    //의뢰자 AC
    reqByAc = new autoC({"workFmeaDetailDTO.reqByDesc":"emp_name"});
    reqByAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    reqByAc.setTable("TAEMP");
    reqByAc.setAcResultMap({
        "workFmeaDetailDTO.reqById":"emp_id",
        "workFmeaDetailDTO.reqDeptId":"dept_id"
    });
    reqByAc.setKeyName("workFmeaDetailDTO.reqById");
    reqByAc.init();
    
    //검토자 AC
    reviewByAc = new autoC({"workFmeaDetailDTO.reviewByDesc":"emp_name"});
    reviewByAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    reviewByAc.setTable("TAEMP");
    reviewByAc.setAcResultMap({
        "workFmeaDetailDTO.reviewById":"emp_id",
        "workFmeaDetailDTO.reviewDeptId":"dept_id"
    });
    reviewByAc.setKeyName("workFmeaDetailDTO.reviewById");
    reviewByAc.init();
    
    //영향성평가 AC
    acSysDesc("workFmeaDetailDTO.fmeaPriorityDesc","workFmeaDetailDTO.fmeaPriorityId","FMEA_PRIORITY",true);
    //작업구분 AC
    acSysDesc("workFmeaDetailDTO.fmeaWoTypeDesc","workFmeaDetailDTO.fmeaWoTypeId","FMEA_WOTYPE",true);
    //Calibration 여부 AC
    acSysDesc("workFmeaDetailDTO.isCalibDesc","workFmeaDetailDTO.isCalibId","IS_USE",true);
    //Qualification 여부 AC
    acSysDesc("workFmeaDetailDTO.isQualDesc","workFmeaDetailDTO.isQualId","IS_USE",true);
    
    if(ckCreate(currentPageId)) goInput();
	else 
	{
		goUpdate();
	} 
	
	afterBtnLoad();
	
}

function afterBtnLoad()
{
	if(workFmeaDetailForm.elements['workFmeaDetailDTO.woFmeaStatusId'].value!="WRT" 
			&& workFmeaDetailForm.elements['workFmeaDetailDTO.woFmeaStatusId'].value!="WRTDA" 
			&& workFmeaDetailForm.elements['workFmeaDetailDTO.woFmeaStatusId'].value!="RCA" 
			&& workFmeaDetailForm.elements['workFmeaDetailDTO.woFmeaStatusId'].value!="RCADA")
	{
		$('.b_confirm').hide();	
		$(".b_approval").hide();
		setDisableAll();
	}
	else
	{
		setEnableAll();
	}
}

/**
 * 입력
 */
function goInput()
{
	//상태 작성중
	workFmeaDetailForm.elements['workFmeaDetailDTO.woFmeaStatusId'].value = "WRT";
	workFmeaDetailForm.elements['workFmeaDetailDTO.woFmeaStatusDesc'].value = "WRT";
	valSysDir('workFmeaDetailDTO.woFmeaStatusId', 'workFmeaDetailDTO.woFmeaStatusDesc', 'WOFMEA_STATUS', true);
	
	workFmeaDetailForm.elements['workFmeaDetailDTO.reqDate'].value = getToday();
	
	workFmeaDetailForm.elements['workFmeaDetailDTO.reqById'].value = loginUser.empId;
	workFmeaDetailForm.elements['workFmeaDetailDTO.reqByDesc'].value = loginUser.empName;
	workFmeaDetailForm.elements['workFmeaDetailDTO.reqDeptId'].value = loginUser.deptId;
	workFmeaDetailForm.elements['workFmeaDetailDTO.reqDeptDesc'].value = loginUser.deptDesc;
	
    sequenceNextVal('SQAWOFMEA_ID');
    
    equipAc.openLov();
}
function setSequenceVal(sequenceVal)
{
    workFmeaDetailForm.elements['workFmeaDetailDTO.woFmeaId'].value = sequenceVal;
    workFmeaDetailForm.elements['workFmeaDetailDTO.woFmeaNo'].value = sequenceVal;
    workFmeaDetailForm.elements['workFmeaCommonDTO.woFmeaId'].value = sequenceVal;
}
/**
 * 수정
 */
function goUpdate()
{
	
}
/**
 * 결재요청
 */
function goApprequest(){
	alert('Coming soon.');
}
/**
 * 확정
 */
 function goConfirm(){
	 if(checkIsUpdate(document)){
		 alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
	 }else{
		 getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0018'/>", function(result){
			 if(result){
				//================================
				// 필수 항목 체크한다.
				//================================
				if(checkValidation()) return;
				//================================
				// 필수 확정항목 체크한다.
				//================================
				if(checkConfirmValidation()) return;
				
				workFmeaDetailForm.strutsAction.value = '<%=WorkFmeaDetailAction.DETAIL_CONFIRM%>';
				var actionUrl = contextPath + "/workFmeaDetail.do";
				XMLHttpPost(actionUrl, FormQueryString(workFmeaDetailForm), 'afterConfirm');
				
			}
		});
	 }
}
 /**
  * 완료후 호출
  */
 function afterConfirm(ajaxXmlDoc)
 {
	alertMessage1("<bean:message key='MESSAGE.MSG034'/>");
	//상태-완료
	workFmeaDetailForm.elements['workFmeaDetailDTO.woFmeaStatusId'].value = "CCA";
	workFmeaDetailForm.elements['workFmeaDetailDTO.woFmeaStatusDesc'].value = "CCA";
	valSysDir('workFmeaDetailDTO.woFmeaStatusId', 'workFmeaDetailDTO.woFmeaStatusDesc', 'WOFMEA_STATUS', true);
	
	 if (parent.findGridRow)	parent.findGridRow(workFmeaDetailForm.elements['workFmeaDetailDTO.woFmeaId'].value);
	 
	 afterBtnLoad();
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
    if(ckCreate(currentPageId)) workFmeaDetailForm.strutsAction.value = "<%=WorkFmeaDetailAction.DETAIL_INPUT%>";
    else workFmeaDetailForm.strutsAction.value = "<%=WorkFmeaDetailAction.DETAIL_UPDATE%>";
    
	var actionUrl = contextPath + "/workFmeaDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(workFmeaDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(workFmeaDetailForm.elements['workFmeaDetailDTO.woFmeaId'].value);
    
    workFmeaDetailForm.elements['workFmeaCommonDTO.woFmeaId'].value = workFmeaDetailForm.elements['workFmeaDetailDTO.woFmeaId'].value;
    getTopPage().afterSaveAll(currentPageId);
    setTitle("workFmeaDetailDTO.woFmeaNo", "workFmeaDetailDTO.equipDesc");
    
    //결재요청 보이기
    $(".b_approval").show();
}

 function goTabPage(pageId) 
 {
	 var form = document.workFmeaDetailForm;
	 
	 if(pageId == "maAppPrcList" || pageId == "appPrcList") {
		 	workFmeaDetailForm.elements['appReqCommonDTO.objectId'].value = workFmeaDetailForm.elements['workFmeaCommonDTO.woFmeaId'].value;
		 	workFmeaDetailForm.elements['appReqCommonDTO.apprType'].value = "WORKFMEA";
			goCommonTabPage(form, '' , pageId);
		}
	 else
	 	goCommonTabPage(form, '' , pageId);
 }

 /**
  * 승인요청
  */
 function goApproval()
 {
	 if(checkIsUpdate(document)){
		 alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
	 }else{
		 var woFmeaId = workFmeaDetailForm.elements['workFmeaCommonDTO.woFmeaId'].value;
		 var description = workFmeaDetailForm.elements['workFmeaDetailDTO.wkorDesc'].value;
		 
		 //APPR_TYPE:결재구분상태[예:일일작업:WODAY] , OBJECT_ID, 
		 appAction(woFmeaId, "WORKFMEA", description);
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
	if (parent.findGridRow)	parent.findGridRow(workFmeaDetailForm.elements['workFmeaCommonDTO.woFmeaId'].value);
	goClose('workFmeaDetail');
 }
 
 /* audit Trail */
 function goAudtrailLink()
 {
 	var objectId = workFmeaDetailForm.elements['workFmeaDetailDTO.woFmeaId'].value;
 	var fileName = currentPageId;

 	if(typeof objectId=="undefined") return;

 	goAudTrailList(objectId, fileName);
 }

 
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

<html:form action="/workFmeaDetail" >
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="workFmeaCommonDTO.woFmeaId"/><!-- Key -->
<html:hidden property="workFmeaDetailDTO.woFmeaId"/><!-- Key -->
<html:hidden property="workFmeaDetailDTO.wkorId"/>
<html:hidden property="workFmeaDetailDTO.wkorDesc"/>
<html:hidden property="workFmeaDetailDTO.woFmeaStatusId"/>
<html:hidden property="workFmeaDetailDTO.equipId"/>
<html:hidden property="workFmeaDetailDTO.eqLocId"/>
<html:hidden property="workFmeaDetailDTO.reqById"/>
<html:hidden property="workFmeaDetailDTO.reqDeptId"/>
<html:hidden property="workFmeaDetailDTO.reqDeptDesc"/>
<html:hidden property="workFmeaDetailDTO.fmeaPriorityId"/>
<html:hidden property="workFmeaDetailDTO.fmeaWoTypeId"/>
<html:hidden property="workFmeaDetailDTO.isCalibId"/>
<html:hidden property="workFmeaDetailDTO.isQualId"/>
<html:hidden property="workFmeaDetailDTO.reviewById"/>
<html:hidden property="workFmeaDetailDTO.reviewDeptId"/>
<html:hidden property="workFmeaDetailDTO.reviewDeptDesc"/>

<html:hidden property="appReqCommonDTO.objectId"/>
<html:hidden property="appReqCommonDTO.apprType"/>
	 
	<div class="article_box">
		<div class="form_box">
		
			<!-- 접수번호  -->
			<div class="field">
				<label><bean:message key="LABEL.receiptNo"/></label>
				<div class="input_read">
					<html:text property="workFmeaDetailDTO.woFmeaNo" readonly="true"/>
				</div>
			</div>
			<!-- 상태  -->
			<div class="field">
				<label><bean:message key="LABEL.status"/></label>
				<div class="input_read">
					<html:text property="workFmeaDetailDTO.woFmeaStatusDesc" readonly="true"/>
				</div>
			</div>
			<!-- 설치장소  -->
			<div class="field_long">
				<label><bean:message key="LABEL.setupLocation"/></label>
				<div class="input_read">
					<html:text property="workFmeaDetailDTO.eqLocDesc" readonly="true"/>
				</div>
			</div>
			<!-- 설비  -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.equipment"/></label>
				<div class="input_box">
					<html:text property="workFmeaDetailDTO.equipDesc" tabindex="10"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 의뢰자  -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.requestBy"/></label>
				<div class="input_box">
					<html:text property="workFmeaDetailDTO.reqByDesc" tabindex="20"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 의뢰일자 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.requestDate1"/></label>
				<div class="input_box">
					<html:text property="workFmeaDetailDTO.reqDate" tabindex="30" />
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			<!-- 발생현황 -->
			<div class="field_long">
				<label><bean:message key="LABEL.occurrence"/></label>
				<div class="input_box">
					<html:textarea property="workFmeaDetailDTO.situation" styleClass="ta50" tabindex="40" />
				</div>
			</div>
			<!-- 수리내용 -->
			<div class="field_long">
				<label><bean:message key="LABEL.repairDescription"/></label>
				<div class="input_box">
					<html:textarea property="workFmeaDetailDTO.repair" styleClass="ta50" tabindex="50" />
				</div>
			</div>
			<div class="field_divide"></div>
			<!-- 영향성평가  -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.fmeaPriority"/></label>
				<div class="input_box">
					<html:text property="workFmeaDetailDTO.fmeaPriorityDesc" tabindex="60"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 작업구분  -->
			<div class="field">
				<label><bean:message key="LABEL.fmeaWoType"/></label>
				<div class="input_box">
					<html:text property="workFmeaDetailDTO.fmeaWoTypeDesc" tabindex="70"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- Calibration  -->
			<div class="field">
				<label><bean:message key="LABEL.calibration1"/></label>
				<div class="input_box">
					<html:text property="workFmeaDetailDTO.isCalibDesc" tabindex="80"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- Qualification  -->
			<div class="field">
				<label><bean:message key="LABEL.qualification"/></label>
				<div class="input_box">
					<html:text property="workFmeaDetailDTO.isQualDesc" tabindex="90"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 검토일자 -->
			<div class="field">
				<label class="lastcheck"><bean:message key="LABEL.viewDate"/></label>
				<div class="input_box">
					<html:text property="workFmeaDetailDTO.reviewDate" tabindex="100" />
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			<!-- 검토자  -->
			<div class="field">
				<label class="lastcheck"><bean:message key="LABEL.viewBy"/></label>
				<div class="input_box">
					<html:text property="workFmeaDetailDTO.reviewByDesc" tabindex="110"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 검토의견 -->
			<div class="field_long">
				<label><bean:message key="LABEL.reviewComments"/></label>
				<div class="input_box">
					<html:textarea property="workFmeaDetailDTO.reviewComments" styleClass="ta50" tabindex="120" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>

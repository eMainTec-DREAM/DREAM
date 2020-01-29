<%--===========================================================================
고장영향성 평가
author  kim21017
version $Id: workFmeaReqDetail.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.fmea.list.action.WorkFmeaReqDetailAction"%>
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
	
	setDisable(document.getElementsByName("disableDiv"));
    
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goUpdate();
	} 
	
    setTitle("workFmeaReqDetailDTO.woFmeaNo", "workFmeaReqDetailDTO.equipDesc");
    //For Save
    setForUpdate();
    
  //설비 AC
    equipAc = new autoC({"workFmeaReqDetailDTO.equipDesc":"description"});
    equipAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipAc.setAcResultMap({
        "workFmeaReqDetailDTO.equipId":"equip_id"
       ,"workFmeaReqDetailDTO.eqLocId":"eqloc_id"
       ,"workFmeaReqDetailDTO.eqLocDesc":"eqLocDesc"
    });
    equipAc.setTable("TAEQUIPMENT");
    equipAc.setKeyName("workFmeaReqDetailDTO.equipId");
    equipAc.init();

    //의뢰자 AC
    reqByAc = new autoC({"workFmeaReqDetailDTO.reqByDesc":"emp_name"});
    reqByAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    reqByAc.setTable("TAEMP");
    reqByAc.setAcResultMap({
        "workFmeaReqDetailDTO.reqDeptId":"dept_id"
      , "workFmeaReqDetailDTO.reqById":"emp_id"
    });
    reqByAc.setKeyName("workFmeaReqDetailDTO.reqById");
    reqByAc.init();
    
    //검토자 AC
    reviewByAc = new autoC({"workFmeaReqDetailDTO.reviewByDesc":"emp_name"});
    reviewByAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    reviewByAc.setTable("TAEMP");
    reviewByAc.setAcResultMap({
        "workFmeaReqDetailDTO.reviewById":"emp_id",
        "workFmeaReqDetailDTO.reviewDeptId":"dept_id"
    });
    reviewByAc.setKeyName("workFmeaReqDetailDTO.reviewById");
    reviewByAc.init();
    
    //영향성평가 AC
    acSysDesc("workFmeaReqDetailDTO.fmeaPriorityDesc","workFmeaReqDetailDTO.fmeaPriorityId","FMEA_PRIORITY",true);
    //작업구분 AC
    acSysDesc("workFmeaReqDetailDTO.fmeaWoTypeDesc","workFmeaReqDetailDTO.fmeaWoTypeId","FMEA_WOTYPE",true);
    //Calibration 여부 AC
    acSysDesc("workFmeaReqDetailDTO.isCalibDesc","workFmeaReqDetailDTO.isCalibId","IS_USE",true);
    //Qualification 여부 AC
    acSysDesc("workFmeaReqDetailDTO.isQualDesc","workFmeaReqDetailDTO.isQualId","IS_USE",true);
	
}

/**
 * 입력
 */
function goInput()
{
	
	setEnableAll();
	
	//상태 작성중
	workFmeaReqDetailForm.elements['workFmeaReqDetailDTO.woFmeaStatusId'].value = "WRT";
	workFmeaReqDetailForm.elements['workFmeaReqDetailDTO.woFmeaStatusDesc'].value = "WRT";
	valSysDir('workFmeaReqDetailDTO.woFmeaStatusId', 'workFmeaReqDetailDTO.woFmeaStatusDesc', 'WOFMEA_STATUS', true);
	
	workFmeaReqDetailForm.elements['workFmeaReqDetailDTO.reqDate'].value = getToday();
	
	workFmeaReqDetailForm.elements['workFmeaReqDetailDTO.reqById'].value = loginUser.empId;
	workFmeaReqDetailForm.elements['workFmeaReqDetailDTO.reqByDesc'].value = loginUser.empName;
	workFmeaReqDetailForm.elements['workFmeaReqDetailDTO.reqDeptId'].value = loginUser.deptId;
	workFmeaReqDetailForm.elements['workFmeaReqDetailDTO.reqDeptDesc'].value = loginUser.deptDesc;
	
    sequenceNextVal('SQAWOFMEA_ID');
}
function setSequenceVal(sequenceVal)
{
    workFmeaReqDetailForm.elements['workFmeaReqDetailDTO.woFmeaId'].value = sequenceVal;
    workFmeaReqDetailForm.elements['workFmeaReqDetailDTO.woFmeaNo'].value = sequenceVal;
    workFmeaReqDetailForm.elements['workFmeaReqCommonDTO.woFmeaId'].value = sequenceVal;
}
/**
 * 수정
 */
function goUpdate()
{
    var woFmeaStatus = workFmeaReqDetailForm.elements['workFmeaReqDetailDTO.woFmeaStatusId'].value;
    
    if(woFmeaStatus == "RCA" || woFmeaStatus == "CCA"){
        setDisableAll();
    }else{
        setEnableAll();
    }
    setForUpdate();
}


/**
 * 요청
 */
function goRequest()
{
   if(checkIsUpdate(document)){
       alertMessage1("<bean:message key='MESSAGE.MSG0036'/>");
   }
   else {
	   
       getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG046'/>", function(result){
           if(result){
                  //================================
                  // 필수 항목 체크한다.
                  //================================
                  if(checkValidation()) return;
                  
                  
			   workFmeaReqDetailForm.strutsAction.value = '<%=WorkFmeaReqDetailAction.REQ_STATUS_UPDATE%>';
		
		       var actionUrl = contextPath + "/workFmeaReqDetail.do";
		       XMLHttpPost(actionUrl, FormQueryString(workFmeaReqDetailForm), 'afterRequest');
		
		           }
		   });
   }
}

/**
  * 완료후 호출
  */
 function afterRequest(ajaxXmlDoc)
 {
	alertMessage1("<bean:message key='MESSAGE.MSG034'/>");
	//상태-완료
	workFmeaReqDetailForm.elements['workFmeaReqDetailDTO.woFmeaStatusId'].value = "RCA";   // 검토중
	workFmeaReqDetailForm.elements['workFmeaReqDetailDTO.woFmeaStatusDesc'].value = "RCA";
	valSysDir('workFmeaReqDetailDTO.woFmeaStatusId', 'workFmeaReqDetailDTO.woFmeaStatusDesc', 'WOFMEA_STATUS', true);
	setDisableAll();
	
	 if (parent.findGridRow)	parent.findGridRow(workFmeaReqDetailForm.elements['workFmeaReqDetailDTO.woFmeaId'].value);
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
    if(ckCreate(currentPageId)) workFmeaReqDetailForm.strutsAction.value = "<%=WorkFmeaReqDetailAction.DETAIL_INPUT%>";
    else workFmeaReqDetailForm.strutsAction.value = "<%=WorkFmeaReqDetailAction.DETAIL_UPDATE%>";
    
	var actionUrl = contextPath + "/workFmeaReqDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(workFmeaReqDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(workFmeaReqDetailForm.elements['workFmeaReqDetailDTO.woFmeaId'].value);
    
    workFmeaReqDetailForm.elements['workFmeaReqCommonDTO.woFmeaId'].value = workFmeaReqDetailForm.elements['workFmeaReqDetailDTO.woFmeaId'].value;
    getTopPage().afterSaveAll(currentPageId);
    setTitle("workFmeaReqDetailDTO.woFmeaNo", "workFmeaReqDetailDTO.equipDesc");
    
}

 function goTabPage(pageId) 
 {
	 var form = document.workFmeaReqDetailForm;
	 goCommonTabPage(form, '' , pageId);
 }


 /* audit Trail */
 function goAudtrailLink()
 {
 	var objectId = workFmeaReqDetailForm.elements['workFmeaReqDetailDTO.woFmeaId'].value;
 	var fileName = currentPageId;

 	if(typeof objectId=="undefined") return;

 	goAudTrailList(objectId, fileName);
 }

 
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

<html:form action="/workFmeaReqDetail" >
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="workFmeaReqCommonDTO.woFmeaId"/><!-- Key -->
<html:hidden property="workFmeaReqDetailDTO.woFmeaId"/><!-- Key -->
<html:hidden property="workFmeaReqDetailDTO.wkorId"/>
<html:hidden property="workFmeaReqDetailDTO.woFmeaStatusId"/>
<html:hidden property="workFmeaReqDetailDTO.equipId"/>
<html:hidden property="workFmeaReqDetailDTO.eqLocId"/>
<html:hidden property="workFmeaReqDetailDTO.reqById"/>
<html:hidden property="workFmeaReqDetailDTO.reqDeptId"/>
<html:hidden property="workFmeaReqDetailDTO.reqDeptDesc"/>
<html:hidden property="workFmeaReqDetailDTO.fmeaPriorityId"/>
<html:hidden property="workFmeaReqDetailDTO.fmeaWoTypeId"/>
<html:hidden property="workFmeaReqDetailDTO.isCalibId"/>
<html:hidden property="workFmeaReqDetailDTO.isQualId"/>
<html:hidden property="workFmeaReqDetailDTO.reviewById"/>
<html:hidden property="workFmeaReqDetailDTO.reviewDeptId"/>
<html:hidden property="workFmeaReqDetailDTO.reviewDeptDesc"/>
	 
	<div class="article_box">
		<div class="form_box">
		
			<!-- 접수번호  -->
			<div class="field">
				<label><bean:message key="LABEL.receiptNo"/></label>
				<div class="input_read">
					<html:text property="workFmeaReqDetailDTO.woFmeaNo" readonly="true"/>
				</div>
			</div>
			<!-- 상태  -->
			<div class="field">
				<label><bean:message key="LABEL.status"/></label>
				<div class="input_read">
					<html:text property="workFmeaReqDetailDTO.woFmeaStatusDesc" readonly="true"/>
				</div>
			</div>
			<!-- 설치장소  -->
			<div class="field_long">
				<label><bean:message key="LABEL.setupLocation"/></label>
				<div class="input_read">
					<html:text property="workFmeaReqDetailDTO.eqLocDesc" readonly="true"/>
				</div>
			</div>
			<!-- 설비  -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.equipment"/></label>
				<div class="input_box">
					<html:text property="workFmeaReqDetailDTO.equipDesc" tabindex="10"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 의뢰자  -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.requestBy"/></label>
				<div class="input_box">
					<html:text property="workFmeaReqDetailDTO.reqByDesc" tabindex="20"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 의뢰일자 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.requestDate1"/></label>
				<div class="input_box">
					<html:text property="workFmeaReqDetailDTO.reqDate" tabindex="30" />
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			<!-- 발생현황 -->
			<div class="field_long">
				<label class="check"><bean:message key="LABEL.occurrence"/></label>
				<div class="input_box">
					<html:textarea property="workFmeaReqDetailDTO.situation" styleClass="ta50" tabindex="40" />
				</div>
			</div>
			<!-- 수리내용 -->
			<div class="field_long" name="disableDiv">
				<label><bean:message key="LABEL.repairDescription"/></label>
				<div class="input_box">
					<html:textarea property="workFmeaReqDetailDTO.repair" styleClass="ta50" tabindex="50" />
				</div>
			</div>
			<div class="field_divide"></div>
			<!-- 영향성평가  -->
			<div class="field" name="disableDiv">
				<label><bean:message key="LABEL.fmeaPriority"/></label>
				<div class="input_box">
					<html:text property="workFmeaReqDetailDTO.fmeaPriorityDesc" tabindex="60"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 작업구분  -->
			<div class="field" name="disableDiv">
				<label><bean:message key="LABEL.fmeaWoType"/></label>
				<div class="input_box">
					<html:text property="workFmeaReqDetailDTO.fmeaWoTypeDesc" tabindex="70"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- Calibration  -->
			<div class="field" name="disableDiv">
				<label><bean:message key="LABEL.calibration1"/></label>
				<div class="input_box">
					<html:text property="workFmeaReqDetailDTO.isCalibDesc" tabindex="80"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- Qualification  -->
			<div class="field" name="disableDiv">
				<label><bean:message key="LABEL.qualification"/></label>
				<div class="input_box">
					<html:text property="workFmeaReqDetailDTO.isQualDesc" tabindex="90"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 검토일자 -->
			<div class="field" name="disableDiv">
				<label class="lastcheck"><bean:message key="LABEL.viewDate"/></label>
				<div class="input_box">
					<html:text property="workFmeaReqDetailDTO.reviewDate" tabindex="100" />
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			<!-- 검토자  -->
			<div class="field" name="disableDiv">
				<label class="lastcheck"><bean:message key="LABEL.viewBy"/></label>
				<div class="input_box">
					<html:text property="workFmeaReqDetailDTO.reviewByDesc" tabindex="110"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 검토의견 -->
			<div class="field_long" name="disableDiv">
				<label><bean:message key="LABEL.reviewComments"/></label>
				<div class="input_box">
					<html:textarea property="workFmeaReqDetailDTO.reviewComments" styleClass="ta50" tabindex="120" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>

<%--===========================================================================
고장영향성 평가
author  kim21017
version $Id: workFmeaResDetail.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.fmea.list.action.WorkFmeaResDetailAction"%>
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
	
    setTitle("workFmeaResDetailDTO.woFmeaNo", "workFmeaResDetailDTO.equipDesc");
    //For Save
    setForUpdate();
    
  //설비 AC
    equipAc = new autoC({"workFmeaResDetailDTO.equipDesc":"description"});
    equipAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipAc.setAcResultMap({
        "workFmeaResDetailDTO.equipId":"equip_id"
       ,"workFmeaResDetailDTO.eqLocId":"eqloc_id"
       ,"workFmeaResDetailDTO.eqLocDesc":"eqLocDesc"
    });
    equipAc.setTable("TAEQUIPMENT");
    equipAc.setKeyName("workFmeaResDetailDTO.equipId");
    equipAc.init();

    //의뢰자 AC
    reqByAc = new autoC({"workFmeaResDetailDTO.reqByDesc":"emp_name"});
    reqByAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    reqByAc.setTable("TAEMP");
    reqByAc.setAcResultMap({
        "workFmeaResDetailDTO.reqById":"emp_id",
        "workFmeaResDetailDTO.reqDeptId":"dept_id"
    });
    reqByAc.setKeyName("workFmeaResDetailDTO.reqById");
    reqByAc.init();
    
    //검토자 AC
    reviewByAc = new autoC({"workFmeaResDetailDTO.reviewByDesc":"emp_name"});
    reviewByAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    reviewByAc.setTable("TAEMP");
    reviewByAc.setAcResultMap({
        "workFmeaResDetailDTO.reviewById":"emp_id",
        "workFmeaResDetailDTO.reviewDeptId":"dept_id"
    });
    reviewByAc.setKeyName("workFmeaResDetailDTO.reviewById");
    reviewByAc.init();
    
    //영향성평가 AC
    acSysDesc("workFmeaResDetailDTO.fmeaPriorityDesc","workFmeaResDetailDTO.fmeaPriorityId","FMEA_PRIORITY",true);
    //작업구분 AC
    acSysDesc("workFmeaResDetailDTO.fmeaWoTypeDesc","workFmeaResDetailDTO.fmeaWoTypeId","FMEA_WOTYPE",true);
    //Calibration 여부 AC
    acSysDesc("workFmeaResDetailDTO.isCalibDesc","workFmeaResDetailDTO.isCalibId","IS_USE",true);
    //Qualification 여부 AC
    acSysDesc("workFmeaResDetailDTO.isQualDesc","workFmeaResDetailDTO.isQualId","IS_USE",true);
	
}

/**
 * 입력
 */
function goInput()
{
	//상태 작성중
	workFmeaResDetailForm.elements['workFmeaResDetailDTO.woFmeaStatusId'].value = "WRT";
	workFmeaResDetailForm.elements['workFmeaResDetailDTO.woFmeaStatusDesc'].value = "WRT";
	valSysDir('workFmeaResDetailDTO.woFmeaStatusId', 'workFmeaResDetailDTO.woFmeaStatusDesc', 'WOFMEA_STATUS', true);
	
	workFmeaResDetailForm.elements['workFmeaResDetailDTO.reqDate'].value = getToday();
	
	workFmeaResDetailForm.elements['workFmeaResDetailDTO.reqById'].value = loginUser.empId;
	workFmeaResDetailForm.elements['workFmeaResDetailDTO.reqByDesc'].value = loginUser.empName;
	workFmeaResDetailForm.elements['workFmeaResDetailDTO.reqDeptId'].value = loginUser.deptId;
	workFmeaResDetailForm.elements['workFmeaResDetailDTO.reqDeptDesc'].value = loginUser.deptDesc;
	
    sequenceNextVal('SQAWOFMEA_ID');
}
function setSequenceVal(sequenceVal)
{
    workFmeaResDetailForm.elements['workFmeaResDetailDTO.woFmeaId'].value = sequenceVal;
    workFmeaResDetailForm.elements['workFmeaResDetailDTO.woFmeaNo'].value = sequenceVal;
    workFmeaResDetailForm.elements['workFmeaResCommonDTO.woFmeaId'].value = sequenceVal;
}
/**
 * 수정
 */
function goUpdate()
{
	  var woFmeaStatus = workFmeaResDetailForm.elements['workFmeaResDetailDTO.woFmeaStatusId'].value;
	    
	    if(woFmeaStatus == "CCA"){
	        setDisableAll();
	    }else{
	        setEnableAll();
	    }
	    setForUpdate();
}


//평가완료
function goCompleted(){
	
	if(checkConfirmValidation()) return;
	
    if(checkIsUpdate(document)){
        alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
    }else{
        getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0018'/>", function(result){
            if(result){
                   //================================
                   // 필수 항목 체크한다.
                   //================================
                   if(checkValidation()) return;

                   workFmeaResDetailForm.strutsAction.value = '<%=WorkFmeaResDetailAction.DETAIL_COMPLETED%>';
                   
                   var actionUrl = contextPath + "/workFmeaResDetail.do";
                   XMLHttpPost(actionUrl, FormQueryString(workFmeaResDetailForm), 'afterCompleted');
                   
                   }
           });
    }
}
    
/**
  * 평가 완료 후 호출
  */
 function afterCompleted(ajaxXmlDoc)
 {
    //=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     workFmeaResDetailForm.elements['workFmeaResCommonDTO.woFmeaId'].value = workFmeaResDetailForm.elements['workFmeaResDetailDTO.woFmeaId'].value;
     if (parent.findGridRow)    parent.findGridRow(workFmeaResDetailForm.elements['workFmeaResCommonDTO.woFmeaId'].value);
     
     //상태 = 검토완료
     workFmeaResDetailForm.elements['workFmeaResDetailDTO.woFmeaStatusId'].value = "CCA";
     workFmeaResDetailForm.elements['workFmeaResDetailDTO.woFmeaStatusDesc'].value = "검토완료";
     valSysDirCode('workFmeaResDetailDTO.woFmeaStatusId', 'workFmeaResDetailDTO.woFmeaStatusDesc', 'WOFMEA_STATUS', true);
     setDisableAll();
     getTopPage().afterSaveAll(currentPageId);
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
    if(ckCreate(currentPageId)) workFmeaResDetailForm.strutsAction.value = "<%=WorkFmeaResDetailAction.DETAIL_INPUT%>";
    else workFmeaResDetailForm.strutsAction.value = "<%=WorkFmeaResDetailAction.DETAIL_UPDATE%>";
    
	var actionUrl = contextPath + "/workFmeaResDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(workFmeaResDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(workFmeaResDetailForm.elements['workFmeaResDetailDTO.woFmeaId'].value);
    
    workFmeaResDetailForm.elements['workFmeaResCommonDTO.woFmeaId'].value = workFmeaResDetailForm.elements['workFmeaResDetailDTO.woFmeaId'].value;
    getTopPage().afterSaveAll(currentPageId);
    setTitle("workFmeaResDetailDTO.woFmeaNo", "workFmeaResDetailDTO.equipDesc");
    
}

 function goTabPage(pageId) 
 {
	 var form = document.workFmeaResDetailForm;
	 goCommonTabPage(form, '' , pageId);
 }

 
 /* audit Trail */
 function goAudtrailLink()
 {
 	var objectId = workFmeaResDetailForm.elements['workFmeaResDetailDTO.woFmeaId'].value;
 	var fileName = currentPageId;

 	if(typeof objectId=="undefined") return;

 	goAudTrailList(objectId, fileName);
 }

 
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

<html:form action="/workFmeaResDetail" >
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="workFmeaResCommonDTO.woFmeaId"/><!-- Key -->
<html:hidden property="workFmeaResDetailDTO.woFmeaId"/><!-- Key -->
<html:hidden property="workFmeaResDetailDTO.wkorId"/>
<html:hidden property="workFmeaResDetailDTO.woFmeaStatusId"/>
<html:hidden property="workFmeaResDetailDTO.equipId"/>
<html:hidden property="workFmeaResDetailDTO.eqLocId"/>
<html:hidden property="workFmeaResDetailDTO.reqById"/>
<html:hidden property="workFmeaResDetailDTO.reqDeptId"/>
<html:hidden property="workFmeaResDetailDTO.reqDeptDesc"/>
<html:hidden property="workFmeaResDetailDTO.fmeaPriorityId"/>
<html:hidden property="workFmeaResDetailDTO.fmeaWoTypeId"/>
<html:hidden property="workFmeaResDetailDTO.isCalibId"/>
<html:hidden property="workFmeaResDetailDTO.isQualId"/>
<html:hidden property="workFmeaResDetailDTO.reviewById"/>
<html:hidden property="workFmeaResDetailDTO.reviewDeptId"/>
<html:hidden property="workFmeaResDetailDTO.reviewDeptDesc"/>
	 
	<div class="article_box">
		<div class="form_box">
		
			<!-- 접수번호  -->
			<div class="field" name="disableDiv">
				<label><bean:message key="LABEL.receiptNo"/></label>
				<div class="input_read">
					<html:text property="workFmeaResDetailDTO.woFmeaNo" readonly="true"/>
				</div>
			</div>
			<!-- 상태  -->
			<div class="field" name="disableDiv">
				<label><bean:message key="LABEL.status"/></label>
				<div class="input_read">
					<html:text property="workFmeaResDetailDTO.woFmeaStatusDesc" readonly="true"/>
				</div>
			</div>
			<!-- 설치장소  -->
			<div class="field_long" name="disableDiv">
				<label><bean:message key="LABEL.setupLocation"/></label>
				<div class="input_read">
					<html:text property="workFmeaResDetailDTO.eqLocDesc" readonly="true"/>
				</div>
			</div>
			<!-- 설비  -->
			<div class="field" name="disableDiv">
				<label class="check"><bean:message key="LABEL.equipment"/></label>
				<div class="input_box">
					<html:text property="workFmeaResDetailDTO.equipDesc" tabindex="10"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 의뢰자  -->
			<div class="field" name="disableDiv">
				<label class="check"><bean:message key="LABEL.requestBy"/></label>
				<div class="input_box">
					<html:text property="workFmeaResDetailDTO.reqByDesc" tabindex="20"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 의뢰일자 -->
			<div class="field" name="disableDiv">
				<label class="check"><bean:message key="LABEL.requestDate1"/></label>
				<div class="input_box">
					<html:text property="workFmeaResDetailDTO.reqDate" tabindex="30" />
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			<!-- 발생현황 -->
			<div class="field_long" name="disableDiv">
				<label><bean:message key="LABEL.occurrence"/></label>
				<div class="input_box">
					<html:textarea property="workFmeaResDetailDTO.situation" styleClass="ta50" tabindex="40" />
				</div>
			</div>
			<!-- 수리내용 -->
			<div class="field_long">
				<label class="check"><bean:message key="LABEL.repairDescription"/></label>
				<div class="input_box">
					<html:textarea property="workFmeaResDetailDTO.repair" styleClass="ta50" tabindex="50" />
				</div>
			</div>
			<div class="field_divide"></div>
			<!-- 영향성평가  -->
			<div class="field">
				<label><bean:message key="LABEL.fmeaPriority"/></label>
				<div class="input_box">
					<html:text property="workFmeaResDetailDTO.fmeaPriorityDesc" tabindex="60"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 작업구분  -->
			<div class="field">
				<label><bean:message key="LABEL.fmeaWoType"/></label>
				<div class="input_box">
					<html:text property="workFmeaResDetailDTO.fmeaWoTypeDesc" tabindex="70"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- Calibration  -->
			<div class="field">
				<label><bean:message key="LABEL.calibration1"/></label>
				<div class="input_box">
					<html:text property="workFmeaResDetailDTO.isCalibDesc" tabindex="80"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- Qualification  -->
			<div class="field">
				<label><bean:message key="LABEL.qualification"/></label>
				<div class="input_box">
					<html:text property="workFmeaResDetailDTO.isQualDesc" tabindex="90"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 검토일자 -->
			<div class="field">
				<label class="lastcheck"><bean:message key="LABEL.viewDate"/></label>
				<div class="input_box">
					<html:text property="workFmeaResDetailDTO.reviewDate" tabindex="100" />
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
			<!-- 검토자  -->
			<div class="field">
				<label class="lastcheck"><bean:message key="LABEL.viewBy"/></label>
				<div class="input_box">
					<html:text property="workFmeaResDetailDTO.reviewByDesc" tabindex="110"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 검토의견 -->
			<div class="field_long">
				<label><bean:message key="LABEL.reviewComments"/></label>
				<div class="input_box">
					<html:textarea property="workFmeaResDetailDTO.reviewComments" styleClass="ta50" tabindex="120" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>

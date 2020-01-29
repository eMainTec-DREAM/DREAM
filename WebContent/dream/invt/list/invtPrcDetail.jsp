<%--===========================================================================
질의 - 상세
author  kim21017
version $Id: invtPrcDetail.jsp,v 1.0 2015/12/04 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.invt.list.action.InvtPrcDetailAction" %>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%@ page import="common.bean.User"%>
<%
    User loginUser = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html>
<head>
<title><bean:message key="MENU.EQUIPINVTPRC"/>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">

var invtListAc,eqLocDescAc,eqCtgAc, deptAc, empAc, invtPrc;
function loadPage() 
{
	//결재보기에서 열었을때.
	if(parent.currentPageId == "appReadyList") setDisableAll();
	
	setTitle("invtPrcDetailDTO.invtProcStypeDesc");
	
	setForUpdate();
<%-- 	if("<%=loginUser.getCompNo()%>" == "140"&&loginUser.plant=='SLP'){ --%>
		
<%-- 	}else if("<%=loginUser.getCompNo()%>" == "150"&&loginUser.plant=='GA'){ --%>
		
// 	}else{
// 		goTabPage("maAppPrcList");
// 	}
	
	setFunction();
	
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goUpdate();
	}

}  

function setFunction()
{
	//-------------------------------------------------------------------//
	invtListAc = new autoC({"invtPrcDetailDTO.description":"description"});
	invtListAc.setAcConditionMap({
 	   	"comp_no":loginUser.compNo
 	   	,"NOT_IN_STATUS":"'RA','OA','C'"
    });
	invtListAc.setAcDConditionMap({
	 	"invtprctp_id":"invtPrcDetailDTO.invtprctpId"
	});
	invtListAc.setTable("TAINVTLIST");
	invtListAc.setAcResultMap({
        "invtCommonDTO.invtlistId":"invtlistId"
        ,"invtPrcDetailDTO.invtlistId":"invtlistId"
        ,"invtPrcDetailDTO.invtprctpId":"invtprctpId"
    });
    invtListAc.init();
    
    //-------------------------------------------------------------------//
    invtPrc = new autoC({"invtPrcDetailDTO.invtProcStypeDesc":"invtProcStypeDesc"});
    invtPrc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	,"is_use":"Y"
  	   });
    invtPrc.setAcDConditionMap({
    	"invtprctp_id":"invtPrcDetailDTO.invtprctpId"
  	   });
    invtPrc.setTable("TAINVTPRCPH");
    invtPrc.setAcResultMap({
        "invtPrcDetailDTO.invtprctpId":"invtprctpId"
        ,"invtPrcDetailDTO.invtprcphId":"invtprcphId"
        ,"invtPrcDetailDTO.refDepart":"refDepart"
        ,"invtPrcDetailDTO.refDoc":"refDoc"
        ,"invtPrcDetailDTO.invtDocNo":"docPrefix"
    });
    invtPrc.init();
}

function goUpdate()
{
	invtPhaseStatus = invtPrcDetailForm.elements['invtPrcDetailDTO.invtphaseStatus'].value;

	$('.b_approval').show();	
	$('.b_completed').show();
	
	if("W" == invtPhaseStatus || "DA"==invtPhaseStatus){
		setEnableAll();
	}else{
		setDisableAll();
	}
}

function goInput()
{
	sequenceNextVal('SQAINVTPHASE_ID');
	invtPrcDetailForm.elements['invtPrcDetailDTO.invtphaseStatus'].value= "W";
	var sysCode = acSysCode("invtPrcDetailDTO.invtphaseStatus","invtPrcDetailDTO.invtphaseStatusDesc","INVTPHASE_STATUS");
	sysCode.exec(); 

	$('.b_approval').hide();
	$('.b_completed').hide();
	
	invtListAc.openLov();
	
}

function setSequenceVal(sequenceVal)
{
	invtPrcDetailForm.elements['invtCommonDTO.invtphaseId'].value= sequenceVal;
	invtPrcDetailForm.elements['invtPrcDetailDTO.invtphaseId'].value= sequenceVal;
}

function goOpen(pageId)
{
	goTabPage(pageId);
}

function goTabPage(pageId)
{
	var form = document.invtPrcDetailForm;

	if(pageId == "maDocLibList" || pageId == "invtPrcDocLibList")
	{	
		invtPrcDetailForm.elements['maDocLibCommonDTO.objectId'].value = invtPrcDetailForm.elements['invtCommonDTO.invtphaseId'].value;
		invtPrcDetailForm.elements['maDocLibCommonDTO.objectType'].value = "INVTPHASE";
		invtPrcDetailForm.elements['maDocLibCommonDTO.description'].value = invtPrcDetailForm.elements['invtPrcDetailDTO.description'].value;
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else if(pageId == "maAppPrcList" || pageId == "appPrcList") {
		invtPrcDetailForm.elements['appReqCommonDTO.objectId'].value = invtPrcDetailForm.elements['invtCommonDTO.invtphaseId'].value;
		invtPrcDetailForm.elements['appReqCommonDTO.apprType'].value = "INVTPRC";
		goCommonTabPage(form, '' , pageId);
	}
	else
		goCommonTabPage(form, '' , pageId);
    
}
var isPrcValid;
/**
 * 절차 중복 체크
 */
 function validPrc(){
	 var actionUrl = contextPath + "/invtPrcDetail.do";
		var param =  "&strutsAction=" + '<%= InvtPrcDetailAction.INVT_PRC_DETAIL_CHECK %>'
		          +  "&" + FormQueryString(invtPrcDetailForm);
		XMLHttpPostVal(actionUrl, param, 'afterValidPrc');
}

function afterValidPrc(ajaxXmlDoc){
	isPrcValid = '0';
	isPrcValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
	if(isPrcValid != '0'){
		closeModal();
		alertMessage1("<bean:message key='MESSAGE.MSG0174'/>");
		invtPrcDetailForm.elements['invtPrcDetailDTO.invtProcStypeDesc'].value = '';
		invtPrcDetailForm.elements['invtPrcDetailDTO.invtprcphId'].value = '';
		invtPrcDetailForm.elements['invtPrcDetailDTO.refDepart'].value = '';
		invtPrcDetailForm.elements['invtPrcDetailDTO.refDoc'].value = '';
		invtPrcDetailForm.elements['invtPrcDetailDTO.invtDocNo'].value = '';
	}
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
	
	validPrc();
	if(isPrcValid!='0') return;
	
	if(ckCreate(currentPageId)) invtPrcDetailForm.strutsAction.value = '<%=InvtPrcDetailAction.INVT_PRC_DETAIL_INPUT%>';
	else invtPrcDetailForm.strutsAction.value = '<%=InvtPrcDetailAction.INVT_PRC_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/invtPrcDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(invtPrcDetailForm), 'afterSave');
	
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
 	//=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================

     var invtphaseId = invtPrcDetailForm.elements['invtCommonDTO.invtphaseId'].value;
	
   	 if (parent.findGridRow) parent.findGridRow(invtphaseId);

     getTopPage().afterSaveAll(currentPageId);

 	$('.b_approval').show();
 	$('.b_completed').show();
     //getParentIframe("invtPhaseList", document).goSearch();
     //$(getPIframe("invtPhaseList", document)).eq(0).goSearch();
 }
 
 function goCompleted()
 {
	 if(checkIsUpdate(document)){
		 alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
	 }else{
		 getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0018'/>", function(result){
	            if(result){
	                   //================================
	                   // 필수 항목 체크한다.
	                   //================================
	                   if(checkValidation()) return;

	                   invtPrcDetailForm.strutsAction.value = '<%=InvtPrcDetailAction.INVT_PRC_DETAIL_COMPLETED%>';
	                   
	                   var actionUrl = contextPath + "/invtPrcDetail.do";
	                   XMLHttpPost(actionUrl, FormQueryString(invtPrcDetailForm), 'afterCompleted');
	                   
                }
         });
	 }
 }
 
 function afterCompleted(ajaxXmlDoc)
 {
    //=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     var invtphaseId = invtPrcDetailForm.elements['invtCommonDTO.invtphaseId'].value;
	
   	 if (parent.findGridRow) parent.findGridRow(invtphaseId);
     
   	 
   	 invtPrcDetailForm.elements['invtPrcDetailDTO.invtphaseStatus'].value = "C";
   	 invtPrcDetailForm.elements['invtPrcDetailDTO.invtphaseStatusDesc'].value = "C";
   	 valSysDir('invtPrcDetailDTO.invtphaseStatus', 'invtPrcDetailDTO.invtphaseStatusDesc', 'INVTPHASE_STATUS', true);
     setDisableAll();
     getTopPage().afterSaveAll(currentPageId);
  }

 function goApproval()
 {
	 if(checkIsUpdate(document)){
		 alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
	 }else{
		 var invtphaseId = invtPrcDetailForm.elements['invtCommonDTO.invtphaseId'].value;
		 var description = invtPrcDetailForm.elements['invtPrcDetailDTO.description'].value;
		 
		 //APPR_TYPE:결재구분상태[예:일일작업:WODAY] , OBJECT_ID, 
		 appAction(invtphaseId, "INVTPRC", description);
	 }
 }
 
 function afterApproval()
 {
	if (parent.findGridRow)	parent.findGridRow(invtPrcDetailForm.elements['invtCommonDTO.invtphaseId'].value);
	goClose('invtPrcDetail');
 }
 
 
 function goAudtrailLink()
 {
 	var objectId = invtPrcDetailForm.elements['invtPrcDetailDTO.invtprctpId'].value;
 	var fileName = currentPageId;

 	if(typeof objectId=="undefined") return;

 	goAudTrailList(objectId, fileName);
 }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/invtPrcDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	
	<html:hidden property="invtCommonDTO.invtlistId"/>
	<html:hidden property="invtCommonDTO.invtphaseId"/>
	
	<html:hidden property="invtPrcDetailDTO.invtlistId"/>
	<html:hidden property="invtPrcDetailDTO.invtphaseId"/>
	<html:hidden property="invtPrcDetailDTO.invtprctpId"/> 
	<html:hidden property="invtPrcDetailDTO.invtphaseStatus"/>
	<html:hidden property="invtPrcDetailDTO.invtprcphId"/>
	
	<html:hidden property="appReqCommonDTO.objectId"/>
	<html:hidden property="appReqCommonDTO.apprType"/>
	
 	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
 	<html:hidden property="maDocLibCommonDTO.objectType" />
 	<html:hidden property="maDocLibCommonDTO.description" />
	<div class="article_box">
		<div class="form_box">
				<!-- 투자명 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.eqInvtDesc"/></label>
					<div class="input_box">
						<html:text property="invtPrcDetailDTO.description" tabindex="10"/>
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 내용(소분류) -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.contents"/></label>
					<div class="input_box">
						<html:text property="invtPrcDetailDTO.invtProcStypeDesc" tabindex="20" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 관련문서 -->
				<div class="field">
					<label><bean:message key="LABEL.refDoc"/></label>
					<div class="input_read">
						<html:text property="invtPrcDetailDTO.refDoc" tabindex="30" readonly="true"/>
					</div>
				</div>
				<!-- 문서번호 -->
				<div class="field">
					<label><bean:message key="LABEL.invtDocNo"/></label>
					<div class="input_box">
						<html:text property="invtPrcDetailDTO.invtDocNo" tabindex="35"/>
					</div>
				</div>
				<!-- 유관부서 -->
				<div class="field">
					<label><bean:message key="LABEL.refDept"/></label>
					<div class="input_read">
						<html:text property="invtPrcDetailDTO.refDepart" tabindex="40" readonly="true"/>
					</div>
				</div>
				<!-- 시작일자 -->
				<div class="field">
					<label><bean:message key="LABEL.invtSDate"/></label>
					
						<div class="input_box input_carendar">
							<html:text property="invtPrcDetailDTO.startDate" tabindex="50" />
							<p class="open_calendar">
								<a>
									<span>조회</span>
								</a>
							</p>
						</div>
					
				</div>
				<!-- 종료일자 -->
				<div class="field">
					<label><bean:message key="LABEL.invtEDate"/></label>
					
						<div class="input_box input_carendar">
							<html:text property="invtPrcDetailDTO.endDate" tabindex="60" />
							<p class="open_calendar">
								<a>
									<span>조회</span>
								</a>
							</p>
						</div>
					
				</div>
				<!-- 진행단계 -->
				<div class="field">
					<label><bean:message key="LABEL.invtStatus"/></label>
					<div class="input_read">
						<html:text property="invtPrcDetailDTO.invtphaseStatusDesc" tabindex="70" readonly="true"/>
					</div>
				</div>
				<!-- 투자금액 -->
				<div class="field">
					<label><bean:message key="LABEL.invtAmt"/></label>
					<div class="input_box">
						<html:text property="invtPrcDetailDTO.invtAmt" tabindex="80" styleClass="num"/>
					</div>
				</div>
				<!-- 비고 -->
				<div class="field_long">
					<label><bean:message key="LABEL.remark"/></label>
					<div class="input_box">
						<html:textarea property="invtPrcDetailDTO.remark" styleClass="ta50" tabindex="250" />
					</div>
				</div>
			</div> <!-- End of Form_box -->
		</div> <!-- End of Article_box -->
	</html:form>
</body>
</html:html>
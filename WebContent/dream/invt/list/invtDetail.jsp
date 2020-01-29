<%--===========================================================================
질의 - 상세
author  kim21017
version $Id: invtDetail.jsp,v 1.0 2015/12/04 04:13:54 kim21017 Exp $
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
<%@ page import="dream.invt.list.action.InvtDetailAction" %>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<html:html>
<head>
<title><bean:message key="LABEL.qnaAns"/>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
var isNew = false;
var equipDescAc,eqLocDescAc,eqCtgAc, deptAc, empAc, invtPrc, plantAc, vendorDescAc,udeptAc;
function loadPage() 
{
	//결재보기에서 열었을때.
	if(parent.currentPageId == "appReadyList") setDisableAll();
	
	setTitle("invtDetailDTO.invtlistNo");
	
	setForUpdate();
	
	setFunction();

	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goUpdate();
	}
	
}

function setFunction()
{
	
	acSysDesc("invtDetailDTO.invtCategDesc","invtDetailDTO.invtCateg","INVT_CATEG");
	//acSysDesc("invtDetailDTO.invtTypeDesc","invtDetailDTO.invtTypeId","INVT_TYPE");
	acSysDesc("invtDetailDTO.invtKindDesc","invtDetailDTO.invtKindId","INVT_KIND", true);

	var woTypeAc = new autoC({"invtDetailDTO.invtTypeDesc":"description"});
    woTypeAc.setAcConditionMap({
        	"list_type":"INVT_TYPE",
        	"is_use":"Y"
  	   });
    woTypeAc.setAcDConditionMap({
    	"param1":"invtDetailDTO.invtCateg"
	   });
    woTypeAc.setTable("TACDSYSD");
    woTypeAc.setAcResultMap({
        "invtDetailDTO.invtTypeId":"cdsysd_no"
    });
    woTypeAc.init();
	
	equipDescAc = new autoC({"invtDetailDTO.equipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo,
 	   "EQ_STATUS":"R+S"
 	   });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcResultMap({
        "invtDetailDTO.equipId":"equip_id"
    });
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "invtDetailDTO.eqlocId",
    	"eqctg_id" : "invtDetailDTO.eqctgId"
    });
    equipDescAc.setKeyName("invtDetailDTO.equipId");
    equipDescAc.init();
    
    eqLocDescAc = new autoC({"invtDetailDTO.eqLocDesc":"full_desc"});
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setKeyName("invtDetailDTO.eqlocId");
    eqLocDescAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   });
    eqLocDescAc.setAcResultMap({
        "invtDetailDTO.eqlocId":"eqloc_id"
    });
    eqLocDescAc.init();
   //-------------------------------------------------------------------//
	eqCtgAc = new autoC({"invtDetailDTO.eqctgDesc":"full_desc"});
	eqCtgAc.setTable("TAEQCTG");
	eqCtgAc.setAcConditionMap({
	 	   "comp_no":loginUser.compNo
	 	   });
	eqCtgAc.setAcResultMap({
        "invtDetailDTO.eqctgId":"eqctg_id"
    });
    eqCtgAc.setKeyName("invtDetailDTO.eqctgId");
	eqCtgAc.init();
	//-------------------------------------------------------------------//
	deptAc = new autoC({"invtDetailDTO.deptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "invtDetailDTO.deptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "invtDetailDTO.plantId"
    });
    deptAc.setKeyName("invtDetailDTO.deptId");
    deptAc.init();
    
    
    udeptAc = new autoC({"invtDetailDTO.usageDeptDesc":"description"});
    udeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    udeptAc.setTable("TADEPT");
    udeptAc.setAcResultMap({
        "invtDetailDTO.usageDept":"dept_id"
    });
    udeptAc.setAcDConditionMap({
    	"plant" : "invtDetailDTO.plantId"
    });
    udeptAc.setKeyName("invtDetailDTO.usageDept");
    udeptAc.init();
    
	//-------------------------------------------------------------------//
    empAc = new autoC({"invtDetailDTO.empDesc":"emp_name"});
    empAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
  	   });
    empAc.setTable("TAEMP");
    empAc.setKeyName("invtDetailDTO.empId");
    empAc.setAcResultMap({
        "invtDetailDTO.empId":"emp_id"
        ,"invtDetailDTO.deptId":"deptId"
        ,"invtDetailDTO.deptDesc":"deptDesc"
    });
    empAc.setAcDConditionMap({
    	"plant" : "invtDetailDTO.plantId"
    }); 
    empAc.init();
    
    //-------------------------------------------------------------------//
    invtPrc = new autoC({"invtDetailDTO.invtDesc":"description"});
    invtPrc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_use":"Y"
  	   });
    invtPrc.setTable("TAINVTPRCTP");
    invtPrc.setAcResultMap({
        "invtDetailDTO.invtprctpId":"invtprctp_id"
    });
    invtPrc.setKeyName("invtDetailDTO.invtprctpId");
    invtPrc.init();
    
    //------------------------------------------------------------------//
    plantTypeAc = new autoC({"invtDetailDTO.plantDesc":"description"});
	plantTypeAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
	plantTypeAc.setTable("TAPLANT");
	plantTypeAc.setKeyName("invtDetailDTO.plantId");
	plantTypeAc.setAcResultMap({
        "invtDetailDTO.plantId":"plant"
    });
	plantTypeAc.init();
    
    vendorDescAc = new autoC({"invtDetailDTO.vendorDesc":"description"});
    vendorDescAc.setAcDisplay("DESCRIPTION");
    vendorDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_use":"Y"
  	});
    vendorDescAc.setTable("TAVENDOR");
    vendorDescAc.setAcResultMap({
        "invtDetailDTO.vendorId":"vendor_id"
    });
    vendorDescAc.init();
}

function goUpdate()
{

	$("#equipCtgDiv").css("display","");
	$("#equipLocDiv").css("display","");
	$("#equipDiv").css("display","");
	
	invtlistStatus = invtDetailForm.elements['invtDetailDTO.invtlistStatus'].value;

	
	if("WA" == invtlistStatus || "DA"==invtlistStatus || "W" == invtlistStatus) {
		$('.b_approval').show();	
		showBtn('CONFIRM');
	}
	else {
		//$('.b_approval').hide();	
		hideBtn("APPROVAL");
		hideBtn('CONFIRM');
	}
	
	if("RA" == invtlistStatus || "OA"==invtlistStatus || "C"==invtlistStatus || "P"==invtlistStatus){
		setDisableAll();
	}else{
		setEnableAll();
	}
	
	if("C" == invtlistStatus) {
		// 취소버튼 보이기
		$('.b_cancelinvt').show();	
	}else {
		$('.b_cancelinvt').hide();	
	}
	
	
}

function goInput()
{
	$('.b_cancelinvt').hide();	
	
	sequenceNextVal('sqainvtlist_id');
	
	if(invtDetailForm.elements['invtDetailDTO.plantId'].value==""){
	invtDetailForm.elements['invtDetailDTO.plantId'].value = loginUser.plant;
	invtDetailForm.elements['invtDetailDTO.plantDesc'].value = loginUser.plantDesc;
	}
	//부서
	if(invtDetailForm.elements['invtDetailDTO.deptId'].value==""){
		invtDetailForm.elements['invtDetailDTO.deptId'].value    = loginUser.deptId;
		invtDetailForm.elements['invtDetailDTO.deptDesc'].value  = loginUser.deptDesc;
	}
	
	//사원
	if(invtDetailForm.elements['invtDetailDTO.empId'].value==""){
		invtDetailForm.elements['invtDetailDTO.empId'].value     = loginUser.empId;
		invtDetailForm.elements['invtDetailDTO.empDesc'].value   = loginUser.empName;
	}
	
	invtDetailForm.elements['invtDetailDTO.invtlistStatus'].value = "W";
	invtDetailForm.elements['invtDetailDTO.invtlistStatusDesc'].value = "W";
 	valSysDir('invtDetailDTO.invtlistStatus', 'invtDetailDTO.invtlistStatusDesc', 'INVTLIST_STATUS', true);
 
/* 	var sysCode = acSysCode("invtDetailDTO.invtlistStatus","invtDetailDTO.invtlistStatusDesc","INVTLIST_STATUS");
	sysCode.exec();  */

	//$('.b_approval').hide();	
	
	invtPrc.openLov();
	
	if(typeof goEnhanceInput == "function") goEnhanceInput();
}

function setSequenceVal(sequenceVal)
{
	if(isNew) invtDetailForm.elements['invtDetailDTO.oldInvtlistId'].value = invtDetailForm.elements['invtCommonDTO.invtlistId'].value;

	invtDetailForm.elements['invtDetailDTO.invtlistId'].value = sequenceVal;
	invtDetailForm.elements['invtCommonDTO.invtlistId'].value = sequenceVal;
	invtDetailForm.elements['invtDetailDTO.invtlistNo'].value = sequenceVal;
	
	if(isNew) afterSetSeqVal();
}

function goOpen(pageId)
{
	goTabPage(pageId);
}

function goTabPage(pageId)
{
	var form = document.invtDetailForm;
	invtDetailForm.elements['invtCommonDTO.description'].value = invtDetailForm.elements['invtDetailDTO.description'].value;
	invtDetailForm.elements['invtCommonDTO.invtprctpId'].value = invtDetailForm.elements['invtDetailDTO.invtprctpId'].value;
	invtDetailForm.elements['invtCommonDTO.woReqId'].value = invtDetailForm.elements['maWoReqCommonDTO.woReqId'].value;
	invtDetailForm.elements['invtCommonDTO.woReqResId'].value = invtDetailForm.elements['maWoReqCommonDTO.woReqResId'].value;
	
	if(pageId == "maDocLibList" || pageId == "invtDocLibList")
	{	
		invtDetailForm.elements['maDocLibCommonDTO.objectId'].value = invtDetailForm.elements['invtCommonDTO.invtlistId'].value;
		invtDetailForm.elements['maDocLibCommonDTO.objectType'].value = "INVTLIST";
		invtDetailForm.elements['maDocLibCommonDTO.description'].value = invtDetailForm.elements['invtDetailDTO.description'].value;
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else if(pageId == "maAppPrcList" || pageId == "appPrcList") {
		invtDetailForm.elements['appReqCommonDTO.objectId'].value = invtDetailForm.elements['invtCommonDTO.invtlistId'].value;
		invtDetailForm.elements['appReqCommonDTO.apprType'].value = "INVTLIST";
		goCommonTabPage(form, '' , pageId);
	}
	else
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
	
	if(ckCreate(currentPageId)) invtDetailForm.strutsAction.value = '<%=InvtDetailAction.INVT_DETAIL_INPUT%>';
	else invtDetailForm.strutsAction.value = '<%=InvtDetailAction.INVT_DETAIL_UPDATE%>';
	
	
	var actionUrl = contextPath + "/invtDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(invtDetailForm), 'afterSave');
	
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
 	//=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================

     var invtlistId = invtDetailForm.elements['invtCommonDTO.invtlistId'].value;
   	
     if (parent.findGridRow) parent.findGridRow(invtlistId);
	
    // 요청 - 투자결과 (목록)에서 저장한 경우 
    if(typeof searchPage("maWoReqDetail").checkStatus == "function"){
    	searchPage("maWoReqDetail").checkStatus(invtDetailForm.elements['maWoReqCommonDTO.woReqId'].value, invtDetailForm.elements['maWoReqCommonDTO.woReqResId'].value);	
 	}
<%--    
	 if (parent.currentPageId == "reqWoInvtRsltList" && invtDetailForm.strutsAction.value == '<%=InvtDetailAction.INVT_DETAIL_INPUT%>') 
   	 {
    	 parent.afterCreate(invtlistId);
   	 }
--%>     

     getTopPage().afterSaveAll(currentPageId);
     
     //요청-처리사항(목록)에서 팝업창을 열었다면 저장시 요청-처리사항(목록) 조회 호출 
     if(opener)
	{	
		if(opener.currentPageId == "reqInvRecWorkResList") opener.goSearch();
	}

     //getParentIframe("invtPhaseList", document).goSearch();
     //$(getPIframe("invtPhaseList", document)).eq(0).goSearch();
     
 }

 /**
  * 승인요청
  */
 function goApproval()
 {
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
		
	 if(checkIsUpdate(document)){
		 alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
	 }else{
		 var invtlistId = invtDetailForm.elements['invtCommonDTO.invtlistId'].value;
		 var description = invtDetailForm.elements['invtDetailDTO.description'].value;
		 
		 //APPR_TYPE:결재구분상태[예:일일작업:WODAY] , OBJECT_ID, 
		 appAction(invtlistId, "INVTLIST", description);
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
	if (parent.findGridRow)	parent.findGridRow(invtDetailForm.elements['invtCommonDTO.invtlistId'].value);
	
	if(typeof searchPage("maWoReqDetail").checkStatus == "function"){
 		searchPage("maWoReqDetail").checkStatus(invtDetailForm.elements['maWoReqCommonDTO.woReqId'].value, invtDetailForm.elements['maWoReqCommonDTO.woReqResId'].value);	
 	}
	
	goClose('invtDetail');
 }
 
function changStatus(_invtStatus){
	invtDetailForm.elements['invtDetailDTO.invtlistStatus'].value = _invtStatus;
	invtDetailForm.elements['invtDetailDTO.invtlistStatusDesc'].value = _invtStatus;
	valSysDir('invtDetailDTO.invtlistStatus', 'invtDetailDTO.invtlistStatusDesc', 'INVTLIST_STATUS', true);
}
 
/*
 * 투자결과, 작업결과 상태체크
 */
function checkStatus(_invtlistId) {
	invtDetailForm.elements['invtCommonDTO.invtlistId'].value = _invtlistId;

	invtDetailForm.strutsAction.value = '<%=InvtDetailAction.CHK_INVT_STATUS%>';
	var actionUrl = contextPath + "/invtDetail.do";
	XMLHttpPostVal(actionUrl, FormQueryString(invtDetailForm), "afterCheckStatus");
}

function afterCheckStatus(ajaxXmlDoc){
	var isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
    // 변경된 상태 수정
    changStatus(isValid);
}

 /*
  * 생성
  */
 function goCreate()
 {
 	parent.goCreate();
 }
  
 /*
  * 복사
  */
 function goCopycreate()
 {
 	isNew = true;
 	
 	if(checkIsUpdate(document)){
         alertMessage1("<bean:message key='MESSAGE.MSG0036'/>");
         								// 저장후 사용해주세요.
     }else{
 		dhtmlx.confirm('<bean:message key="MESSAGE.MSG0100"/>', function(result){
 			if(result){					// 복사하시겠습니까?
		 		sequenceNextVal('SQAINVTLIST_ID');
 			}
 		});
     }
 }
 
 function afterSetSeqVal()
 {
 	var form = invtDetailForm;
 	var url = contextPath + "/invtDetail.do"; 
	var param = "&strutsAction=" + '<%=InvtDetailAction.DETAIL_COPY%>'
	  + "&" + FormQueryString(form);
	XMLHttpPostVal(url, param, 'afterCopycreate');
 }
 
 function afterCopycreate()
 {
 	isNew = false;
 	var newKeyId = invtDetailForm.elements['invtDetailDTO.invtlistId'].value;

//  	goClose('invtDetail');
 	
	if(parent.setKeyAftercopy) parent.setKeyAftercopy(newKeyId);
 }

 function goAudtrailLink()
 {
 	var objectId = invtDetailForm.elements['invtDetailDTO.invtlistId'].value;
		var fileName = currentPageId;

		if (typeof objectId == "undefined")
			return;

		goAudTrailList(objectId, fileName);
	}
 
 /**
  * 완료 후 취소
  */
 function goCancelinvt(){
	 
	 // 상태변경 완료 -> 진행
	 invtDetailForm.elements['invtDetailDTO.invtlistStatus'].value = 'P';
 	 var sysCode = acSysCode('invtDetailDTO.invtlistStatus','invtDetailDTO.invtlistStatusDesc','INVTLIST_STATUS');
	 sysCode.exec(); 
	 
	 invtDetailForm.strutsAction.value = '<%=InvtDetailAction.INVT_STATUS_CANCEL%>';
	
	 var actionUrl = contextPath + "/invtDetail.do";
	 XMLHttpPost(actionUrl, FormQueryString(invtDetailForm), 'afterCancelinvt');
	 
 }

 function afterCancelinvt(ajaxXmlDoc)
 {
	// 요청 - 투자결과 완료 후 취소 시 상태 변경
	if(typeof searchPage("maWoReqDetail").checkStatus == "function"){
		searchPage("maWoReqDetail").checkStatus(invtDetailForm.elements['maWoReqCommonDTO.woReqId'].value, invtDetailForm.elements['maWoReqCommonDTO.woReqResId'].value);	
	}
	
	parent.findGridList(invtDetailForm.elements['invtCommonDTO.invtlistId'].value);
	 
	   window.location.reload();
 }

 /*
  * 요청접수보기 (투자)
  */
 function goWoreqLink()
 {
 	var invtListId = invtDetailForm.elements['invtCommonDTO.invtlistId'].value;
 	
 	goInvtWoreqList(invtListId);
 }
 
 function goCopyLink()
 {
	 goCopycreate(); 
 }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/invtDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="invtCommonDTO.invtlistId"/>
	<html:hidden property="invtCommonDTO.description"/>
	<html:hidden property="invtCommonDTO.invtprctpId"/>
	<html:hidden property="invtCommonDTO.woReqId"/>
	<html:hidden property="invtCommonDTO.woReqResId"/>
	<html:hidden property="invtDetailDTO.invtlistId"/>
	<html:hidden property="invtDetailDTO.oldInvtlistId"/>
	<html:hidden property="invtDetailDTO.invtlistStatus"/>
	<html:hidden property="invtDetailDTO.equipId"/>
	<html:hidden property="invtDetailDTO.eqlocId"/>
	<html:hidden property="invtDetailDTO.eqctgId"/>
	<html:hidden property="invtDetailDTO.deptId"/>
	<html:hidden property="invtDetailDTO.empId"/>
	<html:hidden property="invtDetailDTO.invtCateg"/>
	<html:hidden property="invtDetailDTO.invtprctpId"/>
	<html:hidden property="invtDetailDTO.invtTypeId"/>
	<html:hidden property="invtDetailDTO.plantId" />
	<html:hidden property="invtDetailDTO.woReqId" />
	<html:hidden property="invtDetailDTO.woReqResId" />
	<html:hidden property="invtDetailDTO.invtKindId" />
	<html:hidden property="invtDetailDTO.vendorId" />
	<html:hidden property="invtDetailDTO.usageDept" />
	
	<html:hidden property="maWoReqCommonDTO.woReqResId"/>
	<html:hidden property="maWoReqCommonDTO.woReqId"/>
	
 	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
 	<html:hidden property="maDocLibCommonDTO.objectType" />
 	<html:hidden property="maDocLibCommonDTO.description" />
 	
 	<html:hidden property="appReqCommonDTO.objectId"/>
	<html:hidden property="appReqCommonDTO.apprType"/>
 	
	<div class="article_box">
		<div class="form_box">
				<!-- 투자계획# -->
				<div class="field">
					<label><bean:message key="LABEL.invtlistNo"/></label>
					<div class="input_read">
						<html:text property="invtDetailDTO.invtlistNo" tabindex="10" readonly="true"/>
					</div>
				</div>
				<!-- 상태 -->
				<div class="field">
					<label><bean:message key="LABEL.status"/></label>
					<div class="input_read">
						<html:text property="invtDetailDTO.invtlistStatusDesc" tabindex="20"  readonly="true"/>
					</div>
				</div>
				<!-- 투자명 -->
				<div class="field_long">
					<label><bean:message key="LABEL.eqInvtDesc"/></label>
					<div class="input_box">
						<html:text property="invtDetailDTO.description" tabindex="30"/>
					</div>
				</div>
				
				<!-- 투자구분 -->
                <div class="field">
                    <label class="check"><bean:message key="LABEL.invtCategDesc"/></label>
                    <div class="input_box">
                        <html:text property="invtDetailDTO.invtCategDesc" tabindex="40"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
				<!-- 분류 -->
                <div class="field">
                    <label class="check"><bean:message key="LABEL.category"/></label>
                    <div class="input_box">
                        <html:text property="invtDetailDTO.invtTypeDesc" tabindex="45"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                
				<!-- 투자종류 -->
                <div class="field">
                    <label class="check"><bean:message key="LABEL.invtKind"/></label>
                    <div class="input_box">
                        <html:text property="invtDetailDTO.invtKindDesc" tabindex="47"/>
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                
				<!-- 구매절차 -->
                <div class="field">
                    <label class="check"><bean:message key="LABEL.invtDesc"/></label>
                    <div class="input_box">
                        <html:text property="invtDetailDTO.invtDesc" tabindex="50" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                
				
				<!-- 종류 -->
				<div id ='equipCtgDiv' class="field">
					<label><bean:message key="LABEL.eqCtg"/></label>
					<div class="input_box">
						<html:text property="invtDetailDTO.eqctgDesc" tabindex="50" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 위치 -->
				<div id ='equipLocDiv' class="field">
					<label><bean:message key="LABEL.eqLocDesc"/></label>
					<div class="input_box">
						<html:text property="invtDetailDTO.eqLocDesc" tabindex="50" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 설비 -->
				<div id ='equipDiv' class="field">
					<label><bean:message key="LABEL.equipDesc"/></label>
					<div class="input_box">
						<html:text property="invtDetailDTO.equipDesc" tabindex="50" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 투자부서 -->
				<div class="field">
					<label><bean:message key="LABEL.invtDept"/></label>
					<div class="input_box">
						<html:text property="invtDetailDTO.deptDesc" tabindex="50" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 담당자 -->
				<div class="field">
					<label><bean:message key="LABEL.manager"/></label>
					<div class="input_box">
						<html:text property="invtDetailDTO.empDesc" tabindex="50" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 예상비용 -->
				<div class="field">
					<label><bean:message key="LABEL.planAmt"/></label>
					<div class="input_box">
						<html:text property="invtDetailDTO.planAmt" tabindex="50" styleClass="num"/>
					</div>
				</div>
				<!-- 계약금액 -->
				<div class="field">
					<label><bean:message key="LABEL.contAmt"/></label>
					<div class="input_read">
						<html:text property="invtDetailDTO.contAmt" tabindex="45" styleClass="num" readonly="true"/>
					</div>
				</div>
				<!-- 투자금액 -->
				<div class="field">
					<label><bean:message key="LABEL.invtAmt"/></label>
					<div class="input_read">
						<html:text property="invtDetailDTO.invtAmt" tabindex="50" styleClass="num" readonly="true"/>
					</div>
				</div>
				<!-- 투자시기 -->
				<div class="field">
					<label><bean:message key="LABEL.invtSDate"/></label>
					
						<div class="input_box input_carendar">
							<html:text property="invtDetailDTO.planSdate" tabindex="50" />
							<p class="open_calendar">
								<a>
									<span>조회</span>
								</a>
							</p>
						</div>
					
				</div>
				<!-- 완료시기 -->
				<div class="field">
					<label><bean:message key="LABEL.invtEDate"/></label>
					
						<div class="input_box input_carendar">
							<html:text property="invtDetailDTO.planEdate" tabindex="50" />
							<p class="open_calendar">
								<a>
									<span>조회</span>
								</a>
							</p>
						</div>
					
				</div>
				<!-- 진행단계 -->
				<div class="field hidden">
					<label><bean:message key="LABEL.invtStatus"/></label>
					<div class="input_read">
						<html:text property="invtDetailDTO.invtphaseStatusDesc" tabindex="50" readonly="true"/>
					</div>
				</div>
				<!-- 완료일자 -->
				<div class="field">
					<label><bean:message key="LABEL.completeDate"/></label>
					
						<div class="input_box input_carendar">
							<html:text property="invtDetailDTO.endDate" tabindex="50" />
							<p class="open_calendar">
								<a>
									<span>조회</span>
								</a>
							</p>
						</div>
					
				</div>
				
				<!-- 공장(Plant) -->
				<div class="field">
					<label><bean:message key="LABEL.plant"/></label>
					<div class="input_box">
						<html:text property="invtDetailDTO.plantDesc" tabindex="110"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				
				<!-- 업체명 -->
				<div class="field">
					<label><bean:message key="LABEL.vendorDesc"/></label>
					<div class="input_box">
						<html:text property="invtDetailDTO.vendorDesc" tabindex="110"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 사용부서 -->
				<div class="field">
					<label><bean:message key="LABEL.usedDept"/></label>
					<div class="input_box">
						<html:text property="invtDetailDTO.usageDeptDesc" tabindex="50" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 비고 -->
				<div class="field_long">
					<label><bean:message key="LABEL.remark"/></label>
					<div class="input_box">
						<html:textarea property="invtDetailDTO.remark" styleClass="ta50" tabindex="250" />
					</div>
				</div>
				
				<c:set var="filePath" value="enhance/${compName}/invt/list/invtDetail_${compNo}.jsp" />
				<c:if test="${udf:isExist(filePath)}">
					<c:import charEncoding="UTF-8" url="/enhance/${compName}/invt/list/invtDetail_${compNo}.jsp"></c:import>
				</c:if>
			</div> <!-- End of Form_box -->
		</div> <!-- End of Article_box -->
	</html:form>
</body>
</html:html>
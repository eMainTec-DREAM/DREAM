<%--===========================================================================
부품수리 - 상세
author  ssong
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
<%@ page import="dream.part.pur.req.action.MaPtPurReqDetailAction"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<html:html>
<head>
<!-- 부품수리 -->
<title><bean:message key='LABEL.ptNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
//자동완성 변수 선언
var partNoAc;
var entDeptAc;
var reqDeptAc;
var reqByAc;
var enterByAc;
var usedEquipAc;
var plantAc;
var uomAc;
function loadPage() 
{
	setTitle("maPtPurReqDetailDTO.partNo", "maPtPurReqDetailDTO.detailTitle");
	
	setForUpdate();
	
	partNoAc = new autoC({"maPtPurReqDetailDTO.partNo":"full_desc"});
    partNoAc.setAcDisplay("full_desc");
    partNoAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   ,"part_categ":"SPPT"
 	   });
    partNoAc.setTable("TAPARTS");
    partNoAc.setAcResultMap({
    	"maPtPurReqDetailDTO.partNo":"part_no"
        ,"maPtPurReqDetailDTO.partId":"part_id"
        ,"maPtPurReqDetailDTO.partDesc":"description"
        ,"maPtPurReqDetailDTO.ptSize":"pt_size"
        ,"maPtPurReqDetailDTO.ptModel":"model"
        ,"maPtPurReqDetailDTO.uom":"uom"
        ,"maPtPurReqDetailDTO.uomDesc":"uom_desc"
        ,"maPtPurReqDetailDTO.erpPartNo":"erp_part_no"
    });
    partNoAc.setKeyName("maPtPurReqDetailDTO.partId"); 
    partNoAc.init();
    
    // 접수부서 자동완성
    entDeptAc = new autoC({"maPtPurReqDetailDTO.entDeptDesc":"description"});
    entDeptAc.setTable("TADEPT");
    entDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    });
    entDeptAc.setKeyName("maPtPurReqDetailDTO.recDept");
    entDeptAc.setAcResultMap({
        "maPtPurReqDetailDTO.entDept":"dept_id"
    });
    entDeptAc.init();
    
    // 접수부서 자동완성
    reqDeptAc = new autoC({"maPtPurReqDetailDTO.recDeptDesc":"description"});
    reqDeptAc.setTable("TADEPT");
    reqDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    });
    reqDeptAc.setKeyName("maPtPurReqDetailDTO.recDept");
    reqDeptAc.setAcResultMap({
        "maPtPurReqDetailDTO.recDept":"dept_id"
    });
    reqDeptAc.init();
 	
    // 접수자 자동완성
    reqByAc = new autoC({"maPtPurReqDetailDTO.recByName":"emp_name"});
    reqByAc.setTable("TAEMP");
    reqByAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    });
    reqByAc.setKeyName("maPtPurReqDetailDTO.recBy");
    reqByAc.setAcResultMap({
        "maPtPurReqDetailDTO.recBy":"emp_id"
    });
    reqByAc.init();
    
    // 작성자 자동완성
    enterByAc = new autoC({"maPtPurReqDetailDTO.enterByName":"emp_name"});
    enterByAc.setTable("TAEMP");
    enterByAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    });
    enterByAc.setKeyName("maPtPurReqDetailDTO.enterById");
    enterByAc.setAcResultMap({
        "maPtPurReqDetailDTO.enterById":"emp_id"
    });
    enterByAc.init();
    
 	// 사용설비 자동완성
    usedEquipAc = new autoC({"maPtPurReqDetailDTO.usedEquipDesc":"description"});
    usedEquipAc.setTable("TAEQUIPMENT");
    usedEquipAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    });
    usedEquipAc.setKeyName("maPtPurReqDetailDTO.usedEquip");
    usedEquipAc.setAcResultMap({
        "maPtPurReqDetailDTO.usedEquip":"equip_id"
    });
    usedEquipAc.init();
    
    // 공장 자동완성
    plantAc = new autoC({"maPtPurReqDetailDTO.plantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    });
    plantAc.setKeyName("maPtPurReqDetailDTO.plant");
    plantAc.setAcResultMap({
        "maPtPurReqDetailDTO.plant":"plant"
    });
    plantAc.init();
    
    uomAc = new autoC({"maPtPurReqDetailDTO.uomDesc":"description"});
	uomAc.setAcConditionMap({
		"dir_type":"UOM",
		"is_use":"Y",
		"comp_no":loginUser.compNo
	});
	uomAc.setTable("TACDUSRD");
	uomAc.setKeyName("maPtPurReqDetailDTO.uom");
	uomAc.setAcResultMap({
	    "maPtPurReqDetailDTO.uom":"cdusrd_no"
	});
	uomAc.init();
    
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goUpdate();
	}
}
	
function goUpdate()
{
	
   // 버튼 활성화 
    var ptPnListStatus = maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.inputStatus'].value;

   if (ptPnListStatus == "W" && maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.enterById'].value == loginUser.empId) {
    	setEnableAll();
    	showBtn("SAVE");
    	showBtn("SAVENEW");
    	showBtn("CONFIRM");
    	showBtn("RECCONFIRM");
    	showBtn("REQUEST");
    	hideBtn("REQCANCEL");
    } else if (ptPnListStatus == "C" && maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.enterById'].value == loginUser.empId) {
    	setDisableAll();
    	hideBtn("SAVE");
    	hideBtn("SAVENEW");
    	hideBtn("CONFIRM");
    	hideBtn("RECCONFIRM");
    	hideBtn("REQUEST");
    	showBtn("REQCANCEL");
    } else {
    	setDisableAll();
    }
    setForUpdate();
}

function goInput()
{
	sequenceNextVal('SQPTPNLITS_ID');
		    
 	// 입고상태 : W=작성중
	maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.inputStatus'].value = "W"; 
	valSysDirCode('maPtPurReqDetailDTO.inputStatus', 'maPtPurReqDetailDTO.inputStatusDesc', 'PTPNLIST_STATUS', true); 
	maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.reDate'].value = getToday(); 
	maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.entDept'].value = loginUser.deptId;
	maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.entDeptDesc'].value = loginUser.deptDesc;
	maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.enterById'].value = loginUser.empId;
	maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.enterByName'].value = loginUser.empName;
	maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.plant'].value = loginUser.plant;
	maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.plantDesc'].value = loginUser.plantDesc;
	
	setEnableAll();
	hideBtn("REQCANCEL");
	
	partNoAc.openLov();
}

function setSequenceVal(sequenceVal)
{
	var seq = sequenceVal;
	maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.reqNo'].value = seq;
	maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.reqId'].value = seq;
	maPtPurReqDetailForm.elements['maPtReqCommonDTO.reqId'].value = seq;
   
	//goTabPage("maPtRepAppList");
}

function goOpen(pageId)
{
    goTabPage(pageId);
}
    
function goTabPage(pageId)
{
    var form = document.maPtPurReqDetailForm;

    if(pageId == "maDocLibList" || pageId == "maPtPurReqDocLibList")
    {   
    	maPtPurReqDetailForm.elements['maDocLibCommonDTO.objectId'].value = maPtPurReqDetailForm.elements['maPtReqCommonDTO.reqId'].value;
    	maPtPurReqDetailForm.elements['maDocLibCommonDTO.objectType'].value = "PTPURREQ";  //PTPURREQ
    	maPtPurReqDetailForm.elements['maDocLibCommonDTO.securGrade'].value = "3";  // 3등급 기본세팅 
    	maPtPurReqDetailForm.elements['maDocLibCommonDTO.description'].value = maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.partDesc'].value;  
        goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
    }
    else
    {
        maPtPurReqDetailForm.elements['maPtReqCommonDTO.reqId'].value = maPtPurReqDetailForm.elements['maPtReqCommonDTO.reqId'].value;

        goCommonTabPage(form, '<%=MaPtPurReqDetailAction.PTREQ_DETAIL_INIT%>' , pageId);
    }
 


}
function chkPtDesc() {
	maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.partNo'].value = "";
	maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.partId'].value = "";
	maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.ptSize'].value = "";
	maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.ptModel'].value = "";
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
    if("" == maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.qty'].value || 0 >= parseInt(maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.qty'].value))
    {
    	alertMessage1("<bean:message key='LABEL.qty'/>"+"<bean:message key='MESSAGE.MSG0013' />");
    	closeModal();
    	return;
    } 
    //strutsAction 셋팅.
   	if(ckCreate(currentPageId)) maPtPurReqDetailForm.strutsAction.value = '<%=MaPtPurReqDetailAction.PTREQ_DETAIL_INPUT%>';
   	else maPtPurReqDetailForm.strutsAction.value = '<%=MaPtPurReqDetailAction.PTREQ_DETAIL_UPDATE%>';
   	
   	var actionUrl = contextPath + "/maPtPurReqDetail.do";
   	XMLHttpPost(actionUrl, FormQueryString(maPtPurReqDetailForm), 'afterSave');	
    
}

 /**
  * 저장후 호출
  */
function afterSave(ajaxXmlDoc)
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.reqId'].value = maPtPurReqDetailForm.elements['maPtReqCommonDTO.reqId'].value;
	if (parent.findGridRow)	parent.findGridRow(maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.reqId'].value);
	getTopPage().afterSaveAll(currentPageId);
	
	setTitle("maPtPurReqDetailDTO.partNo", "maPtPurReqDetailDTO.detailTitle");
}


/**
 * PtSize의 데이터를 입력한다.
 */
function setPtSize()
{	

	if(maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.partDesc'].value=='')
	{
		maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.partDesc'].value='';
		maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.ptSize'].value=''; 
	}
	else
	{
		var splPartDesc = maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.partDesc'].value.split(',');
				
		maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.partDesc'].value=splPartDesc[0];
		maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.ptSize'].value=splPartDesc[1]; 
	}
	
}

/**
 * 요청 처리 
 */
function goRequest()
{
	if(checkIsUpdate(document)){
		 alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
	 }else{
		 if(maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.qty'].value=="" || Number(maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.qty'].value)<=0) {
			 alertMessage1("<bean:message key='MESSAGE.MSG0055'/>");
			 return;
		 }
		 getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG046'/>", function(result){
		 	if(result) {
			    maPtPurReqDetailForm.strutsAction.value = '<%=MaPtPurReqDetailAction.PTREQ_DETAIL_STATUS_UPDATE%>';
			    maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.reDate'].value = getToday();
			    var actionUrl = contextPath + "/maPtPurReqDetail.do";
			    XMLHttpPost(actionUrl, FormQueryString(maPtPurReqDetailForm), 'afterSaveStatus');
			     
		    }
		 });
	 }
}


function afterSaveStatus()
{
	alertMessage1("<bean:message key='MESSAGE.MSG0030'/>");
	 
	if (parent.findGridRow)	parent.findGridRow(maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.reqId'].value);

	if(maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.inputStatus'].value == "W"){
		maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.inputStatus'].value = "C";
		valSysDirCode('maPtPurReqDetailDTO.inputStatus', 'maPtPurReqDetailDTO.inputStatusDesc', 'PTPNLIST_STATUS', true);
		//setDisableAll();
		goUpdate();
 	}
}

/** 접수확인 버튼 */
function goRecconfirm(){
	if(checkIsUpdate(document)){
		 alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
	 }else{
		 if(maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.qty'].value==""
				 ||Number(maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.qty'].value)<=0){
			 alertMessage1("<bean:message key='MESSAGE.MSG0055'/>");
			 return;
		 }
		 getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0018'/>", function(result){
		        if(result)
		        {
				    maPtPurReqDetailForm.strutsAction.value = '<%=MaPtPurReqDetailAction.PTREQ_DETAIL_STATUS_REC%>';
				    maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.recDate'].value = getToday();
				    maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.recBy'].value = loginUser.empId;
				    maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.recByName'].value = loginUser.empName;
				    var actionUrl = contextPath + "/maPtPurReqDetail.do";
				    XMLHttpPost(actionUrl, FormQueryString(maPtPurReqDetailForm), 'afterSaveStatusRec');
		        }
		    });
	 }
}
/**
 * 접수완료 처리 후 호출 
 */
function afterSaveStatusRec()
{
	alertMessage1("<bean:message key='MESSAGE.MSG0030'/>");
	maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.inputStatus'].value = "P"; // 확정완료
	valSysDirCode('maPtPurReqDetailDTO.inputStatus', 'maPtPurReqDetailDTO.inputStatusDesc', 'PTPNLIST_STATUS', true);
	if (parent.findGridRow)	parent.findGridRow(maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.reqId'].value);
	//setDisableAll();
	goUpdate();
}

/**
 * 요청취소
 */
function goReqcancel() {
	//작성상태가 구매신청요청(C)에서 작성중(W)으로 변경
    maPtPurReqDetailForm.strutsAction.value = '<%=MaPtPurReqDetailAction.PTREQ_REQ_CANCEL%>';
    
    maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.inputStatus'].value = "W";
    maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.inputStatusDesc'].value = "W";
    var actionUrl = contextPath + "/maPtPurReqDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(maPtPurReqDetailForm), 'afterCancelStatus');
}


function afterCancelStatus()
{
    valSysDirCode('maPtPurReqDetailDTO.inputStatus', 'maPtPurReqDetailDTO.inputStatusDesc', 'PTPNLIST_STATUS', true);

    maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.reqId'].value = maPtPurReqDetailForm.elements['maPtReqCommonDTO.reqId'].value;
	if (parent.findGridRow)	parent.findGridRow(maPtPurReqDetailForm.elements['maPtReqCommonDTO.reqId'].value);
    
	goUpdate();
}

/* audit Trail */
function goAudtrailLink()
{
	var objectId = maPtPurReqDetailForm.elements['maPtPurReqDetailDTO.reqId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maPtPurReqDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maPtReqCommonDTO.reqId" />
	<html:hidden property="maPtPurReqDetailDTO.partId" />
	<html:hidden property="maPtPurReqDetailDTO.reqId" />
	<html:hidden property="maPtPurReqDetailDTO.entDept" />
	<html:hidden property="maPtPurReqDetailDTO.inputStatus" />
	<html:hidden property="maPtPurReqDetailDTO.recBy" />
	<html:hidden property="maPtPurReqDetailDTO.enterById" />
	<html:hidden property="maPtPurReqDetailDTO.detailTitle" />
	<html:hidden property="maPtPurReqDetailDTO.recDept" />
	<html:hidden property="maPtPurReqDetailDTO.recDate" />
	<html:hidden property="maPtPurReqDetailDTO.usedEquip" />
	<html:hidden property="maPtPurReqDetailDTO.plant" />
	<html:hidden property="maPtPurReqDetailDTO.uom" />
	<html:hidden property="maPtPurReqDetailDTO.prOnQty" />
	<html:hidden property="maPtPurReqDetailDTO.prQty" />
	<html:hidden property="maPtPurReqDetailDTO.poOnQty" />
	<html:hidden property="maPtPurReqDetailDTO.poQty" />
	<html:hidden property="maPtPurReqDetailDTO.grOnQty" />
	<html:hidden property="maPtPurReqDetailDTO.grQty" />
	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
    <html:hidden property="maDocLibCommonDTO.objectType" />
    <html:hidden property="maDocLibCommonDTO.securGrade" />
    <html:hidden property="maDocLibCommonDTO.description" />
	<div class="article_box">
		<div class="form_box">
			<!-- 요청번호 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.reqNo"/></label>
				<div id="ptRepairListNoDiv" class="input_read">
					<html:text property="maPtPurReqDetailDTO.reqNo" tabindex="10" readonly="true"/>
				</div>
			</div>
			<!-- 작성상태 -->
			<div class="field">
				<label><bean:message key="LABEL.stWrkStatus"/></label>
				<div class="input_read">
					<html:text property="maPtPurReqDetailDTO.inputStatusDesc" tabindex="20" readonly="true"/>
				</div>
			</div>
			<!-- 부품번호 -->
			<div class="field">
				<label><bean:message key="LABEL.partNo"/></label>
				<div class="input_box">
					<html:text property="maPtPurReqDetailDTO.partNo" tabindex="30" />
					<p class="open_spop">
						<a href="javascript:lovReqParts('maPtPurReqDetailDTO.partId','maPtPurReqDetailDTO.partNo','maPtPurReqDetailDTO.partDesc','','','','','','maPtPurReqDetailDTO.ptSize');">
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
            <!-- erp품번 -->
            <div class="field">
                <label><bean:message key="LABEL.erpPartNo"/></label>
                <div class="input_read">
                    <html:text property="maPtPurReqDetailDTO.erpPartNo" tabindex="35" readonly="true"/>
                </div>
            </div>
            <!-- 품명 -->
            <div class="field">
                <label class="check"><bean:message key="LABEL.ptDesc"/></label>
                <div class="input_box">
                    <html:text property="maPtPurReqDetailDTO.partDesc" tabindex="40" onkeyup="chkPtDesc();"/>
                </div>
            </div>
            <!-- 모델 -->
            <div class="field">
                <label><bean:message key="LABEL.model"/></label>
                <div class="input_box">
                    <html:text property="maPtPurReqDetailDTO.ptModel" tabindex="45"/>
                </div>
            </div>
             <!-- 수량 -->
            <div class="field">
               <label class="check"><bean:message key="LABEL.qty"/></label>
               <div class="input_box">
                   <html:text property="maPtPurReqDetailDTO.qty" tabindex="50"  styleClass="num"/>
               </div>
            </div>
            <!-- 단위 -->
            <div class="field">
               <label><bean:message key="LABEL.uom"/></label>
               <div class="input_box">
                   <html:text property="maPtPurReqDetailDTO.uomDesc" tabindex="55"/>
                   <p class="open_spop"><a><span>조회</span></a></p>
               </div>
            </div>
            <!-- 규격 -->
            <div class="field">
                <label><bean:message key="LABEL.ptSize"/></label>
                <div class="input_box">
                    <html:text property="maPtPurReqDetailDTO.ptSize" tabindex="60"/>
                </div>
            </div>  
            <!-- 신청부서 -->   
            <div class="field">
                <label class="check"><bean:message key="LABEL.entDept"/></label>
                <div class="input_box">
                    <html:text property="maPtPurReqDetailDTO.entDeptDesc" tabindex="70"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
            <!-- 작성자 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.updBy"/></label>
				<div class="input_box">
					<html:text property="maPtPurReqDetailDTO.enterByName" tabindex="80"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 신청일자 -->
            <div class="field">
                <label><bean:message key="LABEL.reDate"/></label>
                <div class="input_read">
                    <html:text property="maPtPurReqDetailDTO.reDate" tabindex="90" readonly="true"/>
                </div>
            </div>    
			<!-- 접수부서 -->
			<div class="field">
				<label><bean:message key="LABEL.receptDept" /></label>
				<div class="input_box">
					<html:text property="maPtPurReqDetailDTO.recDeptDesc" tabindex="100"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 접수일자 -->
			<div class="field">
				<label><bean:message key="LABEL.recpDate"/></label>
				<div class="input_read">
					<html:text property="maPtPurReqDetailDTO.recDate" readonly="true" tabindex="110" />
				</div>
			</div>
			<!-- 접수자-->
			<div class="field">
				<label><bean:message key="LABEL.recName"/></label>
				<div class="input_box">
					<html:text property="maPtPurReqDetailDTO.recByName" tabindex="120" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 사용설비 -->
			<div class="field">
				<label><bean:message key="LABEL.usedEquip" /></label>
				<div class="input_box">
					<html:text property="maPtPurReqDetailDTO.usedEquipDesc" tabindex="130" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 사용용도 -->
			<div class="field">
				<label><bean:message key="LABEL.usePur"/></label>
				<div class="input_box">
					<html:text property="maPtPurReqDetailDTO.usage" tabindex="140"/>
				</div>
			</div>
			<!-- 공장 -->
			<div class="field">
				<label><bean:message key="LABEL.plant" /></label>
				<div class="input_box">
					<html:text property="maPtPurReqDetailDTO.plantDesc" tabindex="150" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 구매신청일자 -->
			<div class="field">
				<label><bean:message key="LABEL.prDate"/></label>
				<div class="input_read">
					<html:text property="maPtPurReqDetailDTO.prDate" readonly="true" tabindex="160" />
				</div>
			</div>
            <!-- 비고 -->
            <div class="field_long">
                <label><bean:message key="LABEL.remark"/></label>
                <div class="input_box">
                    <html:textarea property="maPtPurReqDetailDTO.remark" styleClass="ta50" tabindex="170" />
                </div>
            </div>		
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
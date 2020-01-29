<%--===========================================================================
구매신청 - 상세
author  kim21017
version $Id: maPtBuyReqHdrDetail.jsp,v 1.0 2015/12/04 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="common.bean.User"%>
<%@ page import="dream.part.pur.buy.action.MaPtBuyReqHdrDetailAction"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html>
<head>
<title><bean:message key="LABEL.buyReq"/>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">

var lang = loginUser.locale;

var mainMngAc;
var deptAc;
var vendorDescAc;
var plantAc;
var recByAc;
var recPlantAc;
function loadPage() 
{
	//결재보기에서 열었을때.
	if(parent.currentPageId == "appReadyList") setDisableAll();
	
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goTabPage("maPtBuyReqList");
	}
	
	setTitle("maPtBuyReqHdrDetailDTO.ptPrListNo","maPtBuyReqHdrDetailDTO.ptPrListDesc");
	if(maPtBuyReqHdrDetailForm.elements['maPtBuyReqHdrDetailDTO.ptPrListStatusId'].value != "W" && maPtBuyReqHdrDetailForm.elements['maPtBuyReqHdrDetailDTO.ptPrListStatusId'].value != "WDA"){
		setDisableAll();
	}else{
		setEnableAll();
	}
	setForUpdate();
	
	mainMngAc = new autoC({"maPtBuyReqHdrDetailDTO.userDesc":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
  	});
    mainMngAc.setAcDConditionMap({
    	"plant":"maPtBuyReqHdrDetailDTO.plantId"
  	});
    mainMngAc.setTable("TAEMP");
    mainMngAc.setKeyName("maPtBuyReqHdrDetailDTO.userId");
    mainMngAc.setAcResultMap({
    	"maPtBuyReqHdrDetailDTO.userId":"emp_id",
        "maPtBuyReqHdrDetailDTO.deptId":"dept_id",
        "maPtBuyReqHdrDetailDTO.deptDesc":"deptDesc"
    });
    
    mainMngAc.init();
    
    deptAc = new autoC({"maPtBuyReqHdrDetailDTO.deptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    deptAc.setAcDConditionMap({
    	"plant":"maPtBuyReqHdrDetailDTO.plantId"
  	});
    deptAc.setTable("TADEPT");
    deptAc.setKeyName("maPtBuyReqHdrDetailDTO.deptId");
    deptAc.setAcResultMap({
        "maPtBuyReqHdrDetailDTO.deptId":"dept_id",
        "maPtBuyReqHdrDetailDTO.wcodeId":"wcode_id"
    });
    deptAc.init();
    
    vendorDescAc = new autoC({"maPtBuyReqHdrDetailDTO.vendorDesc":"description"});
    vendorDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_use":"Y"
  	   });
    vendorDescAc.setTable("TAVENDOR");
    vendorDescAc.setKeyName("maPtBuyReqHdrDetailDTO.vendorId");
    vendorDescAc.setAcResultMap({
        "maPtBuyReqHdrDetailDTO.vendorId":"vendor_id"
    });
    vendorDescAc.init();
    
    /** 공장 */
    plantAc = new autoC({"maPtBuyReqHdrDetailDTO.plantDesc":"description"});
    plantAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	, "is_use":"Y"
  	   });
    plantAc.setTable("TAPLANT");
    plantAc.setKeyName("maPtBuyReqHdrDetailDTO.plantId");
    plantAc.setAcResultMap({
        "maPtBuyReqHdrDetailDTO.plantId":"plant"
    });
    plantAc.init();
    
    /** 접수자 AC */
    recByAc = new autoC({"maPtBuyReqHdrDetailDTO.recByName":"emp_name"});
    recByAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
  	});
    recByAc.setTable("TAEMP");
    recByAc.setKeyName("maPtBuyReqHdrDetailDTO.recById");
    recByAc.setAcResultMap({
        "maPtBuyReqHdrDetailDTO.recById":"emp_id"
      , "maPtBuyReqHdrDetailDTO.recDeptId":"dept_id"
      , "maPtBuyReqHdrDetailDTO.recDeptDesc":"deptDesc"
    });
    recByAc.init();
    
    /** 접수공장 AC */
    recPlantAc = new autoC({"maPtBuyReqHdrDetailDTO.recPlantDesc":"description"});
    plantAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
      , "is_use":"Y"
  	});
    recPlantAc.setTable("TAPLANT");
    recPlantAc.setKeyName("maPtBuyReqHdrDetailDTO.recPlant");
    recPlantAc.setAcResultMap({
        "maPtBuyReqHdrDetailDTO.recPlant":"plant"
    });
    recPlantAc.init();
}

/**
 * setDisableAll() Call Back 
 */
function afterDisable()
{
	$('.b_ptreqpdf').show();
	$('.b_mtlprprint').show();
}

function goInput()
{
	sequenceNextVal('SQAPTPRLIST_ID');
	//작성상태 - W 작성중
	maPtBuyReqHdrDetailForm.elements['maPtBuyReqHdrDetailDTO.ptPrListStatusId'].value = "W";
	valSysDirCode('maPtBuyReqHdrDetailDTO.ptPrListStatusId', 'maPtBuyReqHdrDetailDTO.ptPrListStatusDesc', 'PTPRLIST_STATUS', true); 
	
	maPtBuyReqHdrDetailForm.elements['maPtBuyReqHdrDetailDTO.reqDate'].value = getToday();

	maPtBuyReqHdrDetailForm.elements['maPtBuyReqHdrDetailDTO.deptId'].value    = loginUser.deptId;
	maPtBuyReqHdrDetailForm.elements['maPtBuyReqHdrDetailDTO.deptDesc'].value  = loginUser.deptDesc;
	maPtBuyReqHdrDetailForm.elements['maPtBuyReqHdrDetailDTO.wcodeId'].value  = loginUser.wcodeId;
	maPtBuyReqHdrDetailForm.elements['maPtBuyReqHdrDetailDTO.userId'].value     =loginUser.empId;
	maPtBuyReqHdrDetailForm.elements['maPtBuyReqHdrDetailDTO.userDesc'].value   =loginUser.empName;
	//공장명
    if(loginUser.plant!='null'){
    	maPtBuyReqHdrDetailForm.elements['maPtBuyReqHdrDetailDTO.plantId'].value = loginUser.plant;
    	maPtBuyReqHdrDetailForm.elements['maPtBuyReqHdrDetailDTO.plantDesc'].value = loginUser.plantDesc;
    }
}

function setSequenceVal(sequenceVal)
{
	maPtBuyReqHdrDetailForm.elements['maPtBuyReqHdrDetailDTO.ptPrListId'].value = sequenceVal;
	maPtBuyReqHdrDetailForm.elements['maPtBuyReqHdrCommonDTO.ptPrListId'].value = sequenceVal;
	maPtBuyReqHdrDetailForm.elements['maPtBuyReqHdrDetailDTO.ptPrListNo'].value = sequenceVal;
	
	goTabPage("maPtBuyReqList");
}

function goOpen(pageId)
{
	goTabPage(pageId);
}

function goTabPage(pageId)
{
	var form = document.maPtBuyReqHdrDetailForm;
	
	if(pageId == "maDocLibList" || pageId == "maPtBuyReqHdrDocLibList")
	{	
		maPtBuyReqHdrDetailForm.elements['maDocLibCommonDTO.objectId'].value = maPtBuyReqHdrDetailForm.elements['maPtBuyReqHdrCommonDTO.ptPrListId'].value;
		maPtBuyReqHdrDetailForm.elements['maDocLibCommonDTO.objectType'].value = "PR";  // docDesc
		maPtBuyReqHdrDetailForm.elements['maDocLibCommonDTO.description'].value = maPtBuyReqHdrDetailForm.elements['maPtBuyReqHdrDetailDTO.ptPrListDesc'].value;
		maPtBuyReqHdrDetailForm.elements['appReqCommonDTO.apprType'].value = "PTBUYREQ";
		goCommonTabPage(form, '<%=MaPtBuyReqHdrDetailAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else if(pageId == "maAppPrcList" || pageId == "appPrcList") {
		maPtBuyReqHdrDetailForm.elements['appReqCommonDTO.objectId'].value = maPtBuyReqHdrDetailForm.elements['maPtBuyReqHdrCommonDTO.ptPrListId'].value;
		maPtBuyReqHdrDetailForm.elements['appReqCommonDTO.apprType'].value = "PTBUYREQ";
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
	//strutsAction 셋팅.
	if(ckCreate(currentPageId)) maPtBuyReqHdrDetailForm.strutsAction.value = '<%=MaPtBuyReqHdrDetailAction.BUY_DETAIL_INPUT%>';
	else maPtBuyReqHdrDetailForm.strutsAction.value = '<%=MaPtBuyReqHdrDetailAction.BUY_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/maPtBuyReqHdrDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maPtBuyReqHdrDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
 	//=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
     maPtBuyReqHdrDetailForm.elements['maPtBuyReqHdrCommonDTO.ptPrListId'].value = maPtBuyReqHdrDetailForm.elements['maPtBuyReqHdrDetailDTO.ptPrListId'].value;
     if (parent.findGridRow)	parent.findGridRow(maPtBuyReqHdrDetailForm.elements['maPtBuyReqHdrCommonDTO.ptPrListId'].value);

 	setTitle("maPtBuyReqHdrDetailDTO.ptPrListNo","maPtBuyReqHdrDetailDTO.ptPrListDesc");
     
     getTopPage().afterSaveAll(currentPageId);
 }
 
 /**
  * 승인요청
  */
 function goApproval()
 {
	 if(checkIsUpdate(document)){
		 alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
	 }else{
		 if(checkRequireValue('maPtBuyReqHdrDetailDTO.ptPrListDesc','<bean:message key="LABEL.title"/>')) return;
		//================================
		// 필수 항목 체크한다.
		//================================
		if(checkValidation()) return;
		valQty('setAppAction');
		
		 
	 }
 }

  function setAppAction(ajaxXmlDoc)
  {
 	 isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
 		if(isValid != '0')
 		{
 			alertMessage1('<bean:message key="MESSAGE.MSG0055"/>');
 			return;
 		}else{
			var ptPrListId = maPtBuyReqHdrDetailForm.elements['maPtBuyReqHdrCommonDTO.ptPrListId'].value;
			var description = maPtBuyReqHdrDetailForm.elements['maPtBuyReqHdrDetailDTO.ptPrListDesc'].value;
			 
			 //APPR_TYPE:결재구분상태[예:일일작업:WODAY] , OBJECT_ID, 
			 appAction(ptPrListId, "PTBUYREQ", description);
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
	if (parent.findGridRow)	parent.findGridRow(maPtBuyReqHdrDetailForm.elements['maPtBuyReqHdrCommonDTO.ptPrListId'].value);
	goClose('maPtBuyReqHdrDetail');
 }
 
 //신청완료
 function goConfirm(){
	 if(checkIsUpdate(document)){
		 alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
	 }else{
		 if(checkRequireValue('maPtBuyReqHdrDetailDTO.ptPrListDesc','<bean:message key="LABEL.title"/>')) return;
		//================================
		// 필수 항목 체크한다.
		//================================
		if(checkValidation()) return;
		valQty('setValQty');
	 }
 }
 /**
 * 확정 전 품목의 수량중 0인 것이 있는 지 확인.
 */
 function valQty(callPath){
	var actionUrl = contextPath + "/maPtBuyReqHdrDetail.do";
	var param =  "&strutsAction=" + '<%= MaPtBuyReqHdrDetailAction.BUY_DETAIL_CHECK %>'
			  +  "&" + FormQueryString(maPtBuyReqHdrDetailForm); 
	XMLHttpPostVal(actionUrl, param, callPath);
 }
 /**
  * valKeyId()실행 후 호출
  */
  var isValid = 0;
 function setValQty(ajaxXmlDoc)
 {
	 isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
		if(isValid != '0')
		{
			alertMessage1('<bean:message key="MESSAGE.MSG0055"/>');
			return;
		}else{
			getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0042'/>", function(result){
				 if(result){
					//기다려주세요
		        	setModal('<bean:message key="MESSAGE.MSG0083"/>');
					
					maPtBuyReqHdrDetailForm.elements['maPtBuyReqHdrDetailDTO.reqDate'].value = getToday();
					maPtBuyReqHdrDetailForm.strutsAction.value = '<%=MaPtBuyReqHdrDetailAction.BUY_DETAIL_CONFIRM%>';
					 var actionUrl = contextPath + "/maPtBuyReqHdrDetail.do";
					 XMLHttpPostVal(actionUrl, FormQueryString(maPtBuyReqHdrDetailForm), 'afterConfirm');
				 }
				});
		}
 }
 
 function afterConfirm(ajaxXmlDoc){
	var rtnValue = parseXmlDoc(ajaxXmlDoc, 'DESC');
	
	closeModal();
	
	if(rtnValue[0]=="E"){
		alertMessage1(rtnValue[1]);
		return;
	}
	
	alertMessage1("<bean:message key='MESSAGE.MSG0030'/>");
	//작성상태 - C 구매신청완료
	maPtBuyReqHdrDetailForm.elements['maPtBuyReqHdrDetailDTO.ptPrListStatusDesc'].value = "C";
	valSysDir('maPtBuyReqHdrDetailDTO.ptPrListStatusId', 'maPtBuyReqHdrDetailDTO.ptPrListStatusDesc', 'PTPRLIST_STATUS', true);
	if (parent.findGridRow)	parent.findGridRow(maPtBuyReqHdrDetailForm.elements['maPtBuyReqHdrDetailDTO.ptPrListId'].value);
	 setDisableAll();
 }
 /**
  * Print 버튼 클릭
  */
 function goPrint()
 {
 	
 	var url   = contextPath + "/maPtBuyReqHdrDetail.do";
 	var param = "maPtBuyReqHdrDetailDTO.ptPrListId="+ maPtBuyReqHdrDetailForm.elements['maPtBuyReqHdrDetailDTO.ptPrListId'].value;
 	
 	openPrintView(url, param);
 }
 function goPtreqpdf()
 {
	reportCall('maPtBuyReqHdrDetail','maPtBuyReqHdrDetail', "<%=user.getCompNo()%>", "<%=user.getUserId()%>",lang,maPtBuyReqHdrDetailForm.elements['maPtBuyReqHdrDetailDTO.ptPrListId'].value); 
 }
 

 /* audit Trail */
 function goAudtrailLink()
 {
 	var objectId = maPtBuyReqHdrDetailForm.elements['maPtBuyReqHdrDetailDTO.ptPrListId'].value;
 	var fileName = currentPageId;

 	if(typeof objectId=="undefined") return;

 	goAudTrailList(objectId, fileName);
 }


/* 
 * 거래처보기
 */ 
function goVendorLink(_pageId)
{
	var vendorId = maPtBuyReqHdrDetailForm.elements['maPtBuyReqHdrDetailDTO.vendorId'].value;
	var compNo = "<%=user.getCompNo()%>";

	goVendorDetail(vendorId, compNo);
}

 
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maPtBuyReqHdrDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maPtBuyReqHdrCommonDTO.ptPrListId" />
	<html:hidden property="maPtBuyReqHdrDetailDTO.ptPrListId" />
	<html:hidden property="maPtBuyReqHdrDetailDTO.deptId" />
	<html:hidden property="maPtBuyReqHdrDetailDTO.userId" />
	<html:hidden property="maPtBuyReqHdrDetailDTO.vendorId" />
	<html:hidden property="maPtBuyReqHdrDetailDTO.ptPrListStatusId" />
	<html:hidden property="maPtBuyReqHdrDetailDTO.plantId" />
	<html:hidden property="maPtBuyReqHdrDetailDTO.recById" />
	<html:hidden property="maPtBuyReqHdrDetailDTO.recPlant" />
	<html:hidden property="maPtBuyReqHdrDetailDTO.recDeptId" />
	<html:hidden property="maPtBuyReqHdrDetailDTO.wcodeId" />
	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
 	<html:hidden property="maDocLibCommonDTO.objectType" />
 	<html:hidden property="maDocLibCommonDTO.description" />
 	<html:hidden property="appReqCommonDTO.objectId"/>
	<html:hidden property="appReqCommonDTO.apprType"/>
 	
	<div class="article_box">
		<div class="form_box">
				<div class="field">
					<label><bean:message key="LABEL.reqNo"/></label>
					<div class="input_read">
						<html:text property="maPtBuyReqHdrDetailDTO.ptPrListNo" tabindex="10" readonly="true"/>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.stWrkStatus"/></label>
					<div class="input_read">
						<html:text property="maPtBuyReqHdrDetailDTO.ptPrListStatusDesc" tabindex="20"  readonly="true"/>
					</div>
				</div>
				<div class="field_long">
					<label><bean:message key="LABEL.title"/></label>
					<div class="input_box">
						<html:text property="maPtBuyReqHdrDetailDTO.ptPrListDesc" tabindex="30" />
					</div>
				</div>
				<!-- 거래처 -->
				<div class="field">
					<label><bean:message key="LABEL.vendor"/></label>
					<div class="input_box">
						<html:text property="maPtBuyReqHdrDetailDTO.vendorDesc"  tabindex="40" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 신청일자 -->
				<div class="field">
					<label><bean:message key="LABEL.requestDate"/></label>
					<div class="input_read">
						<html:text property="maPtBuyReqHdrDetailDTO.reqDate" readonly="true" />
					</div>
				</div>
				<!-- 신청부서 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.reqDept"/></label>
					<div class="input_box">
						<html:text property="maPtBuyReqHdrDetailDTO.deptDesc" tabindex="50"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 신청자-->
				<div class="field">
					<label class="check"><bean:message key="LABEL.reqBy"/></label>
					<div class="input_box">
						<html:text property="maPtBuyReqHdrDetailDTO.userDesc" tabindex="60"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 접수자 -->
				<div class="field">
					<label><bean:message key="LABEL.recName"/></label>
					<div class="input_box">
						<html:text property="maPtBuyReqHdrDetailDTO.recByName" tabindex="70"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 공장(Plant) -->
				<div class="field">
					<label><bean:message key="LABEL.plant"/></label>
					<div class="input_box">
						<html:text property="maPtBuyReqHdrDetailDTO.plantDesc" tabindex="110"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>								
				<!-- ERP PR번호 -->
				<div class="field">
					<label><bean:message key="LABEL.erpPrNo"/></label>
					<div class="input_read">
						<html:text property="maPtBuyReqHdrDetailDTO.erpPrNo" tabindex="140" readonly="true"/>
					</div>
				</div>
				<!-- 접수공장 -->
				<div class="field">
					<label><bean:message key="LABEL.recPlantDesc"/></label>
					<div class="input_box">
						<html:text property="maPtBuyReqHdrDetailDTO.recPlantDesc" tabindex="110"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 접수부서 -->
				<div class="field">
					<label><bean:message key="LABEL.receptDept"/></label>
					<div class="input_read">
						<html:text property="maPtBuyReqHdrDetailDTO.recDeptDesc" readonly="true" />
					</div>
				</div>								
				<!-- 비고 -->
				<div class="field_long">
					<label><bean:message key="LABEL.remark"/></label>
					<div class="input_box">
						<html:textarea property="maPtBuyReqHdrDetailDTO.remark" styleClass="ta30" tabindex="70" />
					</div>
				</div>
				<c:set var="filePath" value="enhance/${compName}/part/pur/buy/maPtBuyReqHdrDetail_${compNo}.jsp" />
				<c:if test="${udf:isExist(filePath)}">
					<c:import charEncoding="UTF-8" url="/enhance/${compName}/part/pur/buy/maPtBuyReqHdrDetail_${compNo}.jsp"></c:import>
				</c:if>
			</div> <!-- End of Form_box -->
		</div> <!-- End of Article_box -->
	</html:form>
</body>
</html:html>
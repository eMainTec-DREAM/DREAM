<%--===========================================================================
부품실사 - 상세
author  kim21017
version $Id: partAdjStkTakeDetail.jsp,v 1.0 2015/12/04 04:13:54 kim21017 Exp $
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
<%@ page import="dream.part.adj.stktake.action.PartAdjStkTakeDetailAction"%>
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
var empAc;
var warehouseAc;
var plantAc;
var isInput = false;
function loadPage() 
{
	//결재보기에서 열었을때.
	if(parent.currentPageId == "appReadyList") setDisableAll();
	
	setTitle("partAdjStkTakeDetailDTO.ptStkTakeListId","partAdjStkTakeDetailDTO.description");
	if(partAdjStkTakeDetailForm.elements['partAdjStkTakeDetailDTO.ptStkTakeStatus'].value != "" 
			&& partAdjStkTakeDetailForm.elements['partAdjStkTakeDetailDTO.ptStkTakeStatus'].value != "W" 
			&& partAdjStkTakeDetailForm.elements['partAdjStkTakeDetailDTO.ptStkTakeStatus'].value != "WDA"){
		setDisableAll();
	}else{
		setEnableAll();
	}
	setForUpdate();

	/* mainMngAc = new autoC({"partAdjStkTakeDetailDTO.userDesc":"user_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setTable("TAUSER");
    mainMngAc.setKeyName("partAdjStkTakeDetailDTO.userId");
    mainMngAc.setAcResultMap({
        "partAdjStkTakeDetailDTO.userId":"user_id"
    });
    mainMngAc.init(); */
    
    deptAc = new autoC({"partAdjStkTakeDetailDTO.deptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    deptAc.setAcDConditionMap({
    	"plant" : "partAdjStkTakeDetailDTO.plantId"
    });
    deptAc.setTable("TADEPT");
    deptAc.setKeyName("partAdjStkTakeDetailDTO.deptId");
    deptAc.setAcResultMap({
        "partAdjStkTakeDetailDTO.deptId":"dept_id"
    });
    deptAc.init();
    
    warehouseAc = new autoC({"partAdjStkTakeDetailDTO.wname":"wname"});
    warehouseAc.setAcConditionMap({
        "comp_no":loginUser.compNo
        ,"wh_categ":"PART"
    });
    warehouseAc.setAcDConditionMap({
    	"plant" : "partAdjStkTakeDetailDTO.plantId"
    	,"plantDesc" : "partAdjStkTakeDetailDTO.plantDesc"
    });
    warehouseAc.setTable("TAWAREHOUSE");
    warehouseAc.setAcResultMap({
    	"partAdjStkTakeDetailDTO.wcodeId":"wcode_id"
    	,"partAdjStkTakeDetailDTO.plantId":"plantId"
    	,"partAdjStkTakeDetailDTO.plantDesc":"plantDesc"
    }); 
    warehouseAc.setKeyName("partAdjStkTakeDetailDTO.wcodeId");
    //creByAc.setCustomLov("lovUser('partAdjStkTakeCommonDTO.filterUserId', '', 'maUserRptCommonDTO.filterUserDesc')");
    warehouseAc.init(); 

    // 작성자
    empAc = new autoC({"partAdjStkTakeDetailDTO.userDesc":"emp_name"});
    empAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    empAc.setAcDConditionMap({
    	"dept_id" : "partAdjStkTakeDetailDTO.deptId",
    	"plant" : "partAdjStkTakeDetailDTO.plantId"
    });
    empAc.setTable("TAEMP");
    empAc.setKeyName("partAdjStkTakeDetailDTO.userId");
    empAc.setAcResultMap({
        "partAdjStkTakeDetailDTO.userId":"emp_id"
    });
    empAc.init();

    /** 공장 */
    plantAc = new autoC({"partAdjStkTakeDetailDTO.plantDesc":"description"});
    plantAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	, "is_use":"Y"
  	   });
    plantAc.setTable("TAPLANT");
    plantAc.setKeyName("partAdjStkTakeDetailDTO.plantId");
    plantAc.setAcResultMap({
        "partAdjStkTakeDetailDTO.plantId":"plant"
    });
    plantAc.init();
    
	if(ckCreate(currentPageId)) goInput();
	else	goUpdate();

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
	isInput = true;
	sequenceNextVal('SQAPTSTKTAKELIST_ID');
	//작성상태 - C 작성중
	partAdjStkTakeDetailForm.elements['partAdjStkTakeDetailDTO.ptStkTakeStatus'].value = "W";
	partAdjStkTakeDetailForm.elements['partAdjStkTakeDetailDTO.ptStkTakeStatusDesc'].value = "W";
	valSysDir('partAdjStkTakeDetailDTO.ptStkTakeStatus', 'partAdjStkTakeDetailDTO.ptStkTakeStatusDesc', 'PTSTKTAKE_STATUS', true);

	
	partAdjStkTakeDetailForm.elements['partAdjStkTakeDetailDTO.regDate'].value = getToday();

	partAdjStkTakeDetailForm.elements['partAdjStkTakeDetailDTO.deptId'].value    =  loginUser.deptId; 
	partAdjStkTakeDetailForm.elements['partAdjStkTakeDetailDTO.deptDesc'].value  =  loginUser.deptDesc;
	partAdjStkTakeDetailForm.elements['partAdjStkTakeDetailDTO.userId'].value     = loginUser.empId;
	partAdjStkTakeDetailForm.elements['partAdjStkTakeDetailDTO.userDesc'].value   = loginUser.empName;
	//공장명
    if(loginUser.plant!='null'){
    	partAdjStkTakeDetailForm.elements['partAdjStkTakeDetailDTO.plantId'].value = loginUser.plant;
    	partAdjStkTakeDetailForm.elements['partAdjStkTakeDetailDTO.plantDesc'].value = loginUser.plantDesc;
    }
	// 창고명 생성일 때만 활성화
    setEnable(document.getElementsByName("disableDiv"));
	
	warehouseAc.openLov();
}

function setSequenceVal(sequenceVal)
{	
	partAdjStkTakeDetailForm.elements['partAdjStkTakeDetailDTO.ptStkTakeListId'].value = sequenceVal;
	partAdjStkTakeDetailForm.elements['partAdjStkTakeCommonDTO.ptStkTakeListId'].value = sequenceVal;
	partAdjStkTakeDetailForm.elements['partAdjStkTakeDetailDTO.ptStkTakeListNo'].value = sequenceVal;
	
	goTabPage("partAdjStkTakeItemList");
}

/**
 * 수정
 */
function goUpdate()
{
	isInput = false;
	//결재보기에서 열었을때.
	if(parent.currentPageId == "appReadyList") setDisableAll();
	
	if(partAdjStkTakeDetailForm.elements['partAdjStkTakeDetailDTO.ptStkTakeStatus'].value != "" 
		&& partAdjStkTakeDetailForm.elements['partAdjStkTakeDetailDTO.ptStkTakeStatus'].value != "W" 
		&& partAdjStkTakeDetailForm.elements['partAdjStkTakeDetailDTO.ptStkTakeStatus'].value != "WDA")
	{
		setDisableAll();
	}
	else{
		setEnableAll();
		//생성이 아닐 때 창고명 readonly설정 
		setDisable(document.getElementsByName("disableDiv"));
	}

	goTabPage("partAdjStkTakeItemList");
}

function goOpen(pageId)
{
	goTabPage(pageId);
}

function goTabPage(pageId)
{	
	var form = document.partAdjStkTakeDetailForm;

	if(pageId == "maDocLibList" || pageId == "partAdjStkTakeDocLibList")
	{	
		partAdjStkTakeDetailForm.elements['maDocLibCommonDTO.objectId'].value = partAdjStkTakeDetailForm.elements['partAdjStkTakeCommonDTO.ptStkTakeListId'].value;
		partAdjStkTakeDetailForm.elements['maDocLibCommonDTO.objectType'].value = "PTSTK";  // docDesc
		partAdjStkTakeDetailForm.elements['maDocLibCommonDTO.description'].value = partAdjStkTakeDetailForm.elements['partAdjStkTakeDetailDTO.description'].value;
		goCommonTabPage(form, '<%=PartAdjStkTakeDetailAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else if(pageId == "maAppPrcList" || pageId == "appPrcList") {
		partAdjStkTakeDetailForm.elements['appReqCommonDTO.objectId'].value = partAdjStkTakeDetailForm.elements['partAdjStkTakeCommonDTO.ptStkTakeListId'].value;
		partAdjStkTakeDetailForm.elements['appReqCommonDTO.apprType'].value = "PTSTKTAKE";
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
	if(ckCreate(currentPageId)) partAdjStkTakeDetailForm.strutsAction.value = "<%=PartAdjStkTakeDetailAction.BUY_DETAIL_INPUT%>";
	else partAdjStkTakeDetailForm.strutsAction.value = '<%=PartAdjStkTakeDetailAction.BUY_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/partAdjStkTakeDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(partAdjStkTakeDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
 function afterSave(ajaxXmlDoc)
 {
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    partAdjStkTakeDetailForm.elements['partAdjStkTakeCommonDTO.ptStkTakeListId'].value = partAdjStkTakeDetailForm.elements['partAdjStkTakeDetailDTO.ptStkTakeListId'].value;
    if (parent.findGridRow)	parent.findGridRow(partAdjStkTakeDetailForm.elements['partAdjStkTakeCommonDTO.ptStkTakeListId'].value);

	setTitle("partAdjStkTakeDetailDTO.ptStkTakeListId","partAdjStkTakeDetailDTO.description");
     
    getTopPage().afterSaveAll(currentPageId);

	if (parent.setKeyAfterCreate)	parent.setKeyAfterCreate(partAdjStkTakeDetailForm.elements['partAdjStkTakeCommonDTO.ptStkTakeListId'].value,currentPageId);
//     if (isInput) document.getElementById("tabFrameTAB.partAdjStkTakeItemList").contentDocument.location.reload(true);
// 	isInput = false;	
 }
 
 /**
  * 승인요청
  */
 function goApproval()
 {
	 if(checkIsUpdate(document)){
		 alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
	 }else{
		//================================
	    // 필수 항목 체크한다.
	    //================================
		 if(checkValidation()) return;
		 
		 var ptStkTakeListId = partAdjStkTakeDetailForm.elements['partAdjStkTakeCommonDTO.ptStkTakeListId'].value;
		 var description = partAdjStkTakeDetailForm.elements['partAdjStkTakeDetailDTO.description'].value;
		 
		 //APPR_TYPE:결재구분상태[예:일일작업:WODAY] , OBJECT_ID, 
		 appAction(ptStkTakeListId, "PTSTKTAKE", description);
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
	if (parent.findGridRow)	parent.findGridRow(partAdjStkTakeDetailForm.elements['partAdjStkTakeCommonDTO.ptStkTakeListId'].value);
	goClose('partAdjStkTakeDetail');
 }
 
 //실사완료
 function goConfirmtake(){
	 
	 if(checkIsUpdate(document)){
		 alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
	 }else{
		 getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0145'/>", function(result){
			 if(result){
					//================================
				    // 필수 항목 체크한다.
				    //================================
				    if(checkValidation()) return;
					
				  	//기다려주세요
		        	setModal('<bean:message key="MESSAGE.MSG0083"/>');
				    
				    partAdjStkTakeDetailForm.strutsAction.value = '<%=PartAdjStkTakeDetailAction.BUY_DETAIL_CONFIRM%>';
				    
				    var actionUrl = contextPath + "/partAdjStkTakeDetail.do";
				    XMLHttpPost(actionUrl, FormQueryString(partAdjStkTakeDetailForm), 'afterConfirm');
				    }

			});
	 }
 }
 /**
  * 저장후 호출
  */
 function afterConfirm(ajaxXmlDoc)
 {
	 closeModal();
    //=====================================
     if (!checkHttpXml(ajaxXmlDoc)) return;
     //=====================================
	partAdjStkTakeDetailForm.elements['partAdjStkTakeCommonDTO.ptStkTakeListId'].value = partAdjStkTakeDetailForm.elements['partAdjStkTakeDetailDTO.ptStkTakeListId'].value;
     if (parent.findGridRow)	parent.findGridRow(partAdjStkTakeDetailForm.elements['partAdjStkTakeCommonDTO.ptStkTakeListId'].value);
     
 	 //상태 = C - 실사완료
     partAdjStkTakeDetailForm.elements['partAdjStkTakeDetailDTO.ptStkTakeStatus'].value = "C";
     valSysDirCode('partAdjStkTakeDetailDTO.ptStkTakeStatus', 'partAdjStkTakeDetailDTO.ptStkTakeStatusDesc', 'PTSTKTAKE_STATUS', true);
     setDisableAll();
     getTopPage().afterSaveAll(currentPageId);
     
     //goUpdate();
     //setPtStckTitle();
     
     //저장후 자동완료 기능 제거
     //if(partNoAc)partNoAc.destroy();
     //if(whAc)whAc.destroy();
     
     
 }
 
 /* audit Trail */
 function goAudtrailLink()
 {
 	var objectId = partAdjStkTakeDetailForm.elements['partAdjStkTakeDetailDTO.ptStkTakeListId'].value;
 	var fileName = currentPageId;

 	if(typeof objectId=="undefined") return;

 	goAudTrailList(objectId, fileName);
 }

 
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/partAdjStkTakeDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="partAdjStkTakeCommonDTO.ptStkTakeListId" />
	<html:hidden property="partAdjStkTakeDetailDTO.ptStkTakeListId" />
	<html:hidden property="partAdjStkTakeDetailDTO.deptId" />
	<html:hidden property="partAdjStkTakeDetailDTO.userId" />
	<html:hidden property="partAdjStkTakeDetailDTO.wcodeId" />
	<html:hidden property="partAdjStkTakeDetailDTO.whType" />
	<html:hidden property="partAdjStkTakeDetailDTO.ptStkTakeStatus" />
	<html:hidden property="partAdjStkTakeDetailDTO.plantId" />
	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
 	<html:hidden property="maDocLibCommonDTO.objectType" />
 	<html:hidden property="maDocLibCommonDTO.description" />
 	<html:hidden property="partAdjStkTakeCommonDTO.ptStkTakeStatus" />
 	
 	<html:hidden property="appReqCommonDTO.objectId"/>
	<html:hidden property="appReqCommonDTO.apprType"/>
 	
	<div class="article_box">
		<div class="form_box">
				<div class="field">
					<label><bean:message key="LABEL.takeNo"/></label>
					<div class="input_read">
						<html:text property="partAdjStkTakeDetailDTO.ptStkTakeListNo" tabindex="10" readonly="true"/>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.stWrkStatus"/></label>
					<div class="input_read">
						<html:text property="partAdjStkTakeDetailDTO.ptStkTakeStatusDesc" tabindex="20"  readonly="true"/>
					</div>
				</div>				
				<div class="field_long">
					<label><bean:message key="LABEL.title"/></label>
					<div class="input_box">
						<html:text property="partAdjStkTakeDetailDTO.description" tabindex="30" />
					</div>
				</div>
				<div class="field" name="disableDiv">
        	 	<label class="check"><bean:message key="LABEL.wname"/></label>
        	 	<div id="wnameDiv" class="input_box">
        	 		<html:text property="partAdjStkTakeDetailDTO.wname" tabindex="20"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
        	 	</div>
        	 </div>
				<!-- 계획일자 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.pmDate"/></label>
				<div class="input_box">
					<html:text property="partAdjStkTakeDetailDTO.pmDate" tabindex="80" />
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
				<!-- 상세설명 -->
				<div class="field_long">
					<label><bean:message key="LABEL.detailDesc"/></label>
					<div class="input_box">
						<html:textarea property="partAdjStkTakeDetailDTO.remark" styleClass="ta30" tabindex="70" />
					</div>
				</div>
				<!-- 실행일자 -->
				<div class="field">
					<label><bean:message key="LABEL.CRE_DATE"/></label>
					<div class="input_read">
						<html:text property="partAdjStkTakeDetailDTO.actDate" readonly="true" />
					</div>
				</div>
				<!-- 작성부서 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.regDept"/></label>
					<div class="input_box">
						<html:text property="partAdjStkTakeDetailDTO.deptDesc" tabindex="50"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 작성자-->
				<div class="field">
					<label class="check"><bean:message key="LABEL.writeBy"/></label>
					<div class="input_box">
						<html:text property="partAdjStkTakeDetailDTO.userDesc" tabindex="60"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>	
				<!-- 작성일자 -->
				<div class="field">
					<label><bean:message key="LABEL.updDate"/></label>
					<div class="input_read">
						<html:text property="partAdjStkTakeDetailDTO.regDate" readonly="true" />
					</div>
				</div>
				<!-- 공장(Plant) -->
				<div class="field">
					<label><bean:message key="LABEL.plant"/></label>
					<div class="input_box">
						<html:text property="partAdjStkTakeDetailDTO.plantDesc" tabindex="110"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- G/W승인번호 -->
				<div class="field">
					<label><bean:message key="LABEL.lappNo"/></label>
					<div class="input_box">
						<html:text property="partAdjStkTakeDetailDTO.lappNo" tabindex="110"/>
					</div>
				</div>			
				<c:set var="filePath" value="enhance/${compName}/part/adj/stktake/partAdjStkTakeDetail_${compNo}.jsp" />
				<c:if test="${udf:isExist(filePath)}">
					<c:import charEncoding="UTF-8" url="/enhance/${compName}/part/adj/stktake/partAdjStkTakeDetail_${compNo}.jsp"></c:import>
				</c:if>
			</div> <!-- End of Form_box -->
		</div> <!-- End of Article_box -->
	</html:form>
</body>
</html:html>
<%--===========================================================================
 - 상세
author  kim21017
version $Id:$
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
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%@ page import="dream.req.work.action.ReqWorkDetailAction"%>
<%@ page import="common.bean.User"%>
<html:html>
<head>
<title><bean:message key='LABEL.woReqNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<script language="javascript">

var eqLocDescAc;
var equipDescAc;
var recDeptAc;
var recWkCtrDescAc;
var recEmpAc;
var reqRequestDefaultStr;
var moDescAc;
var plantAc;
var reqPlantAc;
var eqAsmbAc;
function loadPage()
{
	//요청내용 insert 시 기본 내용. focus 시 사라짐.
	reqRequestDefaultStr = "<bean:message key='MESSAGE.MSG0187'/>";
	//결재보기에서 열었을때.
	if(parent.currentPageId == "appReadyList") setDisableAll();
	
	setTitle("reqWorkDetailDTO.woReqNo", "reqWorkDetailDTO.reqDesc");
	setForUpdate();

	/*설비위치  */
	eqLocDescAc = new autoC({"reqWorkDetailDTO.reqEqLocDesc":"full_desc"});
    eqLocDescAc.setKeyName("reqWorkDetailDTO.reqEqLocId");
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	});
    eqLocDescAc.setAcDConditionMap({
    	"plant" : "reqWorkDetailDTO.reqPlantId"
        ,"plantDesc":"reqWorkDetailDTO.reqPlantDesc"
    });
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcResultMap({
        "reqWorkDetailDTO.reqEqLocId":"eqloc_id"
    });
    eqLocDescAc.init();

    /**설비  */
    equipDescAc = new autoC({"reqWorkDetailDTO.reqEquipDesc":"description"});
    equipDescAc.setKeyName("reqWorkDetailDTO.reqEquipId");
    equipDescAc.setAcConditionMap({
 	   "a.comp_no":loginUser.compNo,
 	   "CUSTOM":"EQ_STATUS IN('R','S')"
 	});
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcResultMap({
        "reqWorkDetailDTO.reqEquipId":"equip_id"
//         ,"reqWorkDetailDTO.reqEquipNo":"item_no"
        ,"reqWorkDetailDTO.reqEqLocId":"eqloc_id"
        ,"reqWorkDetailDTO.reqEqLocDesc":"eqLocDesc"
        ,"reqWorkDetailDTO.reqEqAsmbId":""
        ,"reqWorkDetailDTO.reqEqAsmbDesc":""
    });
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "reqWorkDetailDTO.reqEqLocId"
        ,"plant" : "reqWorkDetailDTO.reqPlantId"
        ,"plantDesc":"reqWorkDetailDTO.reqPlantDesc"
       	,"dept_id":"reqWorkDetailDTO.recDeptId"
        ,"deptDesc":"reqWorkDetailDTO.recDeptDesc"
    });
    equipDescAc.init();
    
    /** 처리부서 */
    reqDeptAc = new autoC({"reqWorkDetailDTO.recDeptDesc":"description"});
    reqDeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
       	,"isMaint":"Y"
        ,"isMaintDesc":"Y"
  	   });
    reqDeptAc.setAcDConditionMap({
        "plant":"reqWorkDetailDTO.plantId"
        ,"plantDesc":"reqWorkDetailDTO.plantDesc"
    });
    reqDeptAc.setTable("TADEPT");
    reqDeptAc.setAcResultMap({
        "reqWorkDetailDTO.recDeptId":"dept_id"
        ,"reqWorkDetailDTO.plantId":"plant"
        ,"reqWorkDetailDTO.plantDesc":"plantDesc"
    });
    reqDeptAc.init();
    
    /** 처리작업 그룹  */
    recWkCtrDescAc = new autoC({"reqWorkDetailDTO.recWkCtrDesc":"description"});
    recWkCtrDescAc.setKeyName("reqWorkDetailDTO.recWkCtrId");
    recWkCtrDescAc.setAcConditionMap({
        "comp_no":loginUser.compNo
       });
    recWkCtrDescAc.setTable("TAWKCTR");
    recWkCtrDescAc.setAcResultMap({
        "reqWorkDetailDTO.recWkCtrId":"wkctr_id"
    });
    recWkCtrDescAc.init();
    
    /**처리담당자  */
    recEmpAc = new autoC({"reqWorkDetailDTO.recEmpName":"emp_name"});
    recEmpAc.setTable("TAEMP");
    recEmpAc.setAcConditionMap({
        "comp_no":loginUser.compNo,
        "is_join":"Y"
    });
    recEmpAc.setAcDConditionMap({
    	"plant":"reqWorkDetailDTO.plantId"
        ,"plantDesc":"reqWorkDetailDTO.plantDesc"
        ,"dept_id":"reqWorkDetailDTO.recDeptId"
        ,"deptDesc":"reqWorkDetailDTO.recDeptDesc"
    });
    recEmpAc.setKeyName("reqWorkDetailDTO.recEmpId");
    recEmpAc.setAcResultMap({
        "reqWorkDetailDTO.recEmpId":"emp_id",
        "reqWorkDetailDTO.recDeptDesc":"deptDesc",
        "reqWorkDetailDTO.recDeptId":"dept_Id"
    });
    recEmpAc.init();
    
    /** 현상 */
    moDescAc = new autoC({"reqWorkDetailDTO.moCdDesc":"failureDesc"});
    moDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"fail_type":"MO",
 		"is_use":"Y"
  	   });
    moDescAc.setTable("TAFAILURE");
    moDescAc.setKeyName("reqWorkDetailDTO.moCd");
    moDescAc.setAcResultMap({
        "reqWorkDetailDTO.moCd":"failure_id"
            ,"reqWorkDetailDTO.moDesc":"failureDesc"
    });
    moDescAc.init();
    
    /** 접수공장 */
    plantAc = new autoC({"reqWorkDetailDTO.plantDesc":"description"});
    plantAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	, "is_use":"Y"
  	   });
    plantAc.setTable("TAPLANT");
    plantAc.setKeyName("reqWorkDetailDTO.plantId");
    plantAc.setAcResultMap({
        "reqWorkDetailDTO.plantId":"plant"
    });
    plantAc.init();
    
    /** 부위 AC */
    eqAsmbAc = new autoC({"reqWorkDetailDTO.reqEqAsmbDesc":"full_desc"});
    eqAsmbAc.setTable("TAEQASMB");
    eqAsmbAc.setAcConditionMap({
       "comp_no": loginUser.compNo
     , "is_use" : "Y"
    });
    eqAsmbAc.setAcDConditionMap({
       "equip_id": "reqWorkDetailDTO.reqEquipId"
    });
    eqAsmbAc.setKeyName("reqWorkDetailDTO.reqEqAsmbId");
    eqAsmbAc.setAcResultMap({
        "reqWorkDetailDTO.reqEqAsmbId":"eqasmb_id"
    });
    eqAsmbAc.init();
    
	//우선순위
    acSysDesc("reqWorkDetailDTO.reqPriorityDesc","reqWorkDetailDTO.reqPriorityId","REQ_PRIORITY",true);
	//요청타입
    acSysDesc("reqWorkDetailDTO.eqClassDesc","reqWorkDetailDTO.eqClassId","EQ_CLASS",true);
    
    $("textarea[name='reqWorkDetailDTO.reqRequest']").focus( function(){
    	if (reqWorkDetailForm.elements['reqWorkDetailDTO.reqRequest'].value == reqRequestDefaultStr && ckCreate(currentPageId)) 
        {
    		reqWorkDetailForm.elements['reqWorkDetailDTO.reqRequest'].value = '';
    		reqWorkDetailForm.elements['reqWorkDetailDTO.reqRequest'].style.color = "#000000";
        }
    });
    $("textarea[name='reqWorkDetailDTO.reqRequest']").blur( function(){
    	if (reqWorkDetailForm.elements['reqWorkDetailDTO.reqRequest'].value == '' && ckCreate(currentPageId))
        {
    		reqWorkDetailForm.elements['reqWorkDetailDTO.reqRequest'].value = reqRequestDefaultStr;  
    		reqWorkDetailForm.elements['reqWorkDetailDTO.reqRequest'].style.color = "#bdbdbd";
        }
    });
    
	if(ckCreate(currentPageId)) goInput();
	else
	{
		goUpdate();
	}
	
}

function setState()
{
	if(reqWorkDetailForm.elements['reqWorkDetailDTO.reqEmpId'].value!=loginUser.empId){
		setEnableAll();
		hideBtn("request");
		hideBtn("approval");
	}
	else {
		setEnableAll();
	}
	
	if("WRT"==reqWorkDetailForm.elements['reqWorkDetailDTO.woReqStatusId'].value
			|| "WRTDA"==reqWorkDetailForm.elements['reqWorkDetailDTO.woReqStatusId'].value){
		setEnableAll();
	}
	else if("WRTRA"==reqWorkDetailForm.elements['reqWorkDetailDTO.woReqStatusId'].value
			|| "WRTOA"==reqWorkDetailForm.elements['reqWorkDetailDTO.woReqStatusId'].value
			|| "REQ"==reqWorkDetailForm.elements['reqWorkDetailDTO.woReqStatusId'].value) {
		setDisableAll();
		hideBtn("request");
		hideBtn("approval");
	}
	else {
		setDisableAll();
	}
	//검토내용
	setDisable(document.getElementById("disableDiv"));
}

function goTabPage(pageId)
{
	var form = document.reqWorkDetailForm;

	if(pageId == "maDocLibList" || pageId == "maWoDocLibList"|| pageId == "reqWorkDocLibList")
	{
		reqWorkDetailForm.elements['maDocLibCommonDTO.objectId'].value = reqWorkDetailForm.elements['reqWorkCommonDTO.woReqId'].value;
		reqWorkDetailForm.elements['maDocLibCommonDTO.objectType'].value = "WOREQ";
		//reqWorkDetailForm.elements['maDocLibCommonDTO.securGrade'].value = "3";  //보안등급-일반
		//reqWorkDetailForm.elements['maDocLibCommonDTO.docCateg'].value = "DOC";  //문서타입-일반
		reqWorkDetailForm.elements['maDocLibCommonDTO.description'].value =
			reqWorkDetailForm.elements['reqWorkDetailDTO.reqDesc'].value;  //제목
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else if(pageId == "maAppPrcList" || pageId == "appPrcList") {
		reqWorkDetailForm.elements['appReqCommonDTO.objectId'].value = reqWorkDetailForm.elements['reqWorkCommonDTO.woReqId'].value;
		reqWorkDetailForm.elements['appReqCommonDTO.apprType'].value = "REQWORK";
		goCommonTabPage(form, '' , pageId);
	}
	else
	goCommonTabPage(form, '' , pageId);
}

function goInput()
{
	setEnableAll();
	//검토내용
	setDisable(document.getElementById("disableDiv"));
	
	sequenceNextVal('SQAWOREQ_ID');
	//reqWorkDetailForm.elements['reqWorkDetailDTO.reqRequest'].value = reqRequestDefaultStr;
	
	$('[name="reqWorkDetailDTO.reqRequest"]').attr("placeholder", reqRequestDefaultStr);
	
	/* if (reqWorkDetailForm.elements['reqWorkDetailDTO.reqRequest'].value == reqRequestDefaultStr) 
    {
		reqWorkDetailForm.elements['reqWorkDetailDTO.reqRequest'].style.color = "#bdbdbd";
    } */
	
	reqWorkDetailForm.elements['reqWorkDetailDTO.woReqStatusId'].value   = 'WRT';
	reqWorkDetailForm.elements['reqWorkDetailDTO.woReqStatusDesc'].value = 'WRT';
	valSysDir('reqWorkDetailDTO.woReqStatusId', 'reqWorkDetailDTO.woReqStatusDesc', 'WOREQ_STATUS', true);

	reqWorkDetailForm.elements['reqWorkDetailDTO.reqDate'].value   = getToday();
	reqWorkDetailForm.elements['reqWorkDetailDTO.reqEmpId'].value   = loginUser.empId;
	reqWorkDetailForm.elements['reqWorkDetailDTO.reqDeptId'].value   = loginUser.deptId;
	reqWorkDetailForm.elements['reqWorkDetailDTO.reqPhone'].value   = loginUser.mPhone;
	reqWorkDetailForm.elements['reqWorkDetailDTO.reqEmail'].value   = loginUser.eMail;
	reqWorkDetailForm.elements['reqWorkDetailDTO.reqEmpDesc'].value   = loginUser.empName+"/"+loginUser.deptDesc;

	var woReqType = reqWorkDetailForm.elements['reqWorkCommonDTO.selectedWoReqTypeId'].value;
	reqWorkDetailForm.elements['reqWorkDetailDTO.woReqType'].value = woReqType;
	
	//공장명
    if(loginUser.plant!='null'){
		reqWorkDetailForm.elements['reqWorkDetailDTO.reqPlantId'].value = loginUser.plant;
		reqWorkDetailForm.elements['reqWorkDetailDTO.reqPlantDesc'].value = loginUser.plantDesc;
    }
	
	// 접수공장명
    if(loginUser.plant!='null'){
		reqWorkDetailForm.elements['reqWorkDetailDTO.plantId'].value = loginUser.plant;
		reqWorkDetailForm.elements['reqWorkDetailDTO.plantDesc'].value = loginUser.plantDesc;
    }
	
	// 설비 (이상점검에서 설비 넘겨주지 않으면)
    if(reqWorkDetailForm.elements['reqWorkDetailDTO.reqEquipId'].value == "")
    {
		equipDescAc.openLov();
    }
	
	// 작업발생요청구분
    reqWorkDetailForm.elements['reqWorkDetailDTO.reqChannelId'].value = "WEB";
	
    if(typeof exGoInput == "function") exGoInput();
}

function setSequenceVal(sequenceVal)
{
	reqWorkDetailForm.elements['reqWorkCommonDTO.woReqId'].value = sequenceVal;
	reqWorkDetailForm.elements['reqWorkDetailDTO.woReqId'].value = sequenceVal;
	reqWorkDetailForm.elements['reqWorkDetailDTO.woReqNo'].value = sequenceVal;
}

function goUpdate()
{
	setState();
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

	
	//위치면 위치필수 , 없으면 체크 안함.
    if(reqWorkDetailForm.elements['reqWorkDetailDTO.eqClassId'].value == "L") 
	{
    	if(checkRequireValue('reqWorkDetailDTO.reqEqLocDesc','<bean:message key="LABEL.location"/>')) return;
	}
    else if(reqWorkDetailForm.elements['reqWorkDetailDTO.eqClassId'].value == "C")  //요청타입 교정?
    {
    	
    }
    else if(reqWorkDetailForm.elements['reqWorkDetailDTO.eqClassId'].value != "") 
	{
    	if(checkRequireValue('reqWorkDetailDTO.reqEquipDesc','<bean:message key="LABEL.equipment"/>')) return;
	} 

	//strutsAction 셋팅.
	if(ckCreate(currentPageId)) reqWorkDetailForm.strutsAction.value = '<%=ReqWorkDetailAction.DETAIL_INSERT%>';
	else reqWorkDetailForm.strutsAction.value = '<%=ReqWorkDetailAction.DETAIL_UPDATE%>';

	var actionUrl = contextPath + "/reqWorkDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(reqWorkDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
function afterSave(ajaxXmlDoc)
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	//reqWorkDetailForm.elements['reqWorkDetailDTO.woDayListId'].value = reqWorkDetailForm.elements['reqWorkCommonDTO.woDayListId'].value;
	// 이상점검처리대상 - 작업계획 (목록) 에서 요청한 경우
    if(parent.currentPageId == "maBdPointWoReqList" && reqWorkDetailForm.strutsAction.value == '<%=ReqWorkDetailAction.DETAIL_INSERT%>')
    {
   	 	parent.afterCreate(reqWorkDetailForm.elements['reqWorkCommonDTO.woReqId'].value);
    }
		
	if (parent.findGridRow)	parent.findGridRow(reqWorkDetailForm.elements['reqWorkDetailDTO.woReqId'].value);
	
	setState();

	getTopPage().afterSaveAll(currentPageId);
}

 function changStatus(_resStatus){
		reqWorkDetailForm.elements['reqWorkDetailDTO.woReqStatusId'].value   = _resStatus;
		reqWorkDetailForm.elements['reqWorkDetailDTO.woReqStatusDesc'].value = _resStatus;
		valSysDir('reqWorkDetailDTO.woReqStatusId', 'reqWorkDetailDTO.woReqStatusDesc', 'WOREQ_STATUS', true);

		if (parent.findGridRow)	parent.findGridRow(reqWorkDetailForm.elements['reqWorkDetailDTO.woReqId'].value);
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
		 var woReqId = reqWorkDetailForm.elements['reqWorkCommonDTO.woReqId'].value;
		 var description = reqWorkDetailForm.elements['reqWorkDetailDTO.reqDesc'].value;
		 
		 //APPR_TYPE:결재구분상태[예:일일작업:WODAY] , OBJECT_ID, 
		 appAction(woReqId, "REQWORK", description);
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
	if (parent.findGridRow)	parent.findGridRow(reqWorkDetailForm.elements['reqWorkCommonDTO.woReqId'].value);
	goClose('reqWorkDetail');
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
		reqWorkDetailForm.strutsAction.value = '<%=ReqWorkDetailAction.REQ_STATUS_UPDATE%>';

		var actionUrl = contextPath + "/reqWorkDetail.do";
		XMLHttpPost(actionUrl, FormQueryString(reqWorkDetailForm), 'afterRequest');
	}
}
 
/**
 * 요청후 호출
 */
function afterRequest(ajaxXmlDoc)
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
		
	if (parent.findGridRow)	parent.findGridRow(reqWorkDetailForm.elements['reqWorkDetailDTO.woReqId'].value);
	
	reqWorkDetailForm.elements['reqWorkDetailDTO.woReqStatusId'].value = "REQ";
	valSysDirCode('reqWorkDetailDTO.woReqStatusId', 'reqWorkDetailDTO.woReqStatusDesc', 'WOREQ_STATUS', true);
	getTopPage().afterSaveAll(currentPageId);
	
	setState();
}

function afterAutoCmpt(codeName, rtnJsonArry)
{
	if(typeof exAfterAutoCmpt == "function") exAfterAutoCmpt(codeName, rtnJsonArry);
}

function goAudtrailLink()
{	
	var objectId = reqWorkDetailForm.elements['reqWorkDetailDTO.woReqId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}

/*
 * 의뢰서 출력
 */
function goReqpdfLink()
{
	var woReqId = reqWorkDetailForm.elements['reqWorkDetailDTO.woReqId'].value;
	reportCall('maWoReqDetail','maWoReqDetail', loginUser.compNo, loginUser.userId, loginUser.langId ,woReqId);
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/reqWorkDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="reqWorkCommonDTO.woReqId" />
	<html:hidden property="reqWorkCommonDTO.selectedWoReqTypeId" />
	<html:hidden property="reqWorkDetailDTO.woReqId" />
	<html:hidden property="reqWorkDetailDTO.woReqStatusId" />
	<html:hidden property="reqWorkDetailDTO.reqDeptId" />
	<html:hidden property="reqWorkDetailDTO.reqEmpId" />
	<html:hidden property="reqWorkDetailDTO.reqEqLocId" />
	<html:hidden property="reqWorkDetailDTO.reqEquipId" />
	<html:hidden property="reqWorkDetailDTO.recDeptId" />
	<html:hidden property="reqWorkDetailDTO.recWkCtrId" />
	<html:hidden property="reqWorkDetailDTO.recEmpId" />
	<html:hidden property="reqWorkDetailDTO.reqPriorityId" />
	<html:hidden property="reqWorkDetailDTO.eqClassId" />
	<html:hidden property="reqWorkDetailDTO.moCd"/>
	<html:hidden property="reqWorkDetailDTO.woReqType"/>
	<html:hidden property="reqWorkDetailDTO.plantId"/>
	<html:hidden property="reqWorkDetailDTO.reqChannelId"/>
	<html:hidden property="reqWorkDetailDTO.reqPlantId"/>
	<html:hidden property="reqWorkDetailDTO.reqEqAsmbId"/>
 	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
 	<html:hidden property="maDocLibCommonDTO.objectType" />
 	<html:hidden property="maDocLibCommonDTO.securGrade" />
 	<html:hidden property="maDocLibCommonDTO.docCateg" />
 	<html:hidden property="maDocLibCommonDTO.description" />
 	
 	<html:hidden property="appReqCommonDTO.objectId"/>
	<html:hidden property="appReqCommonDTO.apprType"/>
	
	
	<div class="article_box">
		<div class="form_box">
			<!-- 요청No -->
			<div class="field">
				<label><bean:message key="LABEL.woReqNo"/></label>
				<div class="input_read">
					<html:text property="reqWorkDetailDTO.woReqNo" readonly="true"/>
				</div>
			</div>
			<!-- 진행상태 -->
			<div class="field">
				<label><bean:message key="LABEL.proStatus"/></label>
				<div class="input_read">
					<html:text property="reqWorkDetailDTO.woReqStatusDesc" readonly="true"/>
				</div>
			</div>
			<!-- 요청일자 -->
			<div class="field">
				<label><bean:message key="LABEL.appReqDate"/></label>
				<div class="input_read">
					<html:text property="reqWorkDetailDTO.reqDate" readonly="true"/>
				</div>
			</div>
			<!-- 요청자 -->
			<div class="field">
				<label><bean:message key="LABEL.appReqBy"/></label>
				<div class="input_read">
					<html:text property="reqWorkDetailDTO.reqEmpDesc" readonly="true"/>
				</div>
			</div>
			<!-- 요청자 전화번호-->
			<div class="field">
				<label><bean:message key="LABEL.woReqPhone"/></label>
				<div class="input_box">
					<html:text property="reqWorkDetailDTO.reqPhone" tabindex="10"/>
				</div>
			</div>
			<!-- 요청자 이메일-->
			<div class="field">
				<label><bean:message key="LABEL.woReqEmail"/></label>
				<div class="input_box">
					<html:text property="reqWorkDetailDTO.reqEmail" tabindex="20"/>
				</div>
			</div>
            <!-- 공장(Plant) -->
			<div class="field">
				<label><bean:message key="LABEL.plant"/></label>
				<div class="input_read">
					<html:text property="reqWorkDetailDTO.reqPlantDesc" tabindex="23"/>
				</div>
			</div>
			<!-- 요청타입 -->
			<div class="field">
				<label><bean:message key="LABEL.reqType"/></label>
				<div class="input_box">
					<html:text property="reqWorkDetailDTO.eqClassDesc"  tabindex="25"/>
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 설비 -->
			<div class="field">
				<label><bean:message key="LABEL.equipment"/></label>
				<div class="input_box">
					<html:text property="reqWorkDetailDTO.reqEquipDesc" tabindex="30"/>
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 설비코드 -->
<!-- 			<div class="field"> -->
<%-- 				<label><bean:message key="LABEL.equipNo"/></label> --%>
<!-- 				<div class="input_read"> -->
<%-- 					<html:text property="reqWorkDetailDTO.reqEquipNo" tabindex="35" readonly="true"/> --%>
<!-- 				</div> -->
<!-- 			</div> -->
			<!-- 위치 -->
			<div class="field">
				<label><bean:message key="LABEL.location"/></label>
				<div class="input_box">
					<html:text property="reqWorkDetailDTO.reqEqLocDesc" tabindex="40"/>
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 작업명 -->
			<div class="field_long">
				<label class="check"><bean:message key="LABEL.title"/></label>
				<div class="input_box">
					<html:text property="reqWorkDetailDTO.reqDesc" tabindex="50" />
				</div>
			</div>
			
			<!-- 처리부서 -->
			<div class="field">
                <label><bean:message key="LABEL.recReqDept"/></label>
                <div class="input_box">
                    <html:text property="reqWorkDetailDTO.recDeptDesc" tabindex="60" />
                    <p class="open_spop">
                        <a><span>조회</span></a>
                    </p>
                </div>
            </div>
            
            <!-- 작업그룹 -->
            <div class="field">
                <label><bean:message key="LABEL.recReqWkCtr"/></label>
                <div class="input_box">
                    <html:text property="reqWorkDetailDTO.recWkCtrDesc" tabindex="70"/>
                    <p class="open_spop">
                        <a><span>조회</span></a>
                    </p>
                </div>
            </div>
            
			<!-- 처리담당자 -->
            <div class="field">
                <label><bean:message key="LABEL.recReqEmp"/></label>
                <div class="input_box">
                    <html:text property="reqWorkDetailDTO.recEmpName" tabindex="80"/>
                    <p class="open_spop">
                        <a><span>조회</span></a>
                    </p>
                </div>
            </div>
            <!-- 고장현상 -->
			<div class="field">
				<label><bean:message key="LABEL.moCd"/></label>
				<div class="input_box">
					<html:text property="reqWorkDetailDTO.moCdDesc" tabindex="120"/>
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 현상설명 -->
			<div class="field">
				<label><bean:message key="LABEL.moCdRemark"/></label>
				<div class="input_box">
					<html:text property="reqWorkDetailDTO.moDesc" tabindex="125" />
				</div>
			</div>
			
			<!-- 처리요청일 -->
			<div class="field">
				<label><bean:message key="LABEL.reqComDate"/></label>
				<div class="input_box">
					<html:text property="reqWorkDetailDTO.reqComDate" tabindex="90" />
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
            <!-- 우선순위 -->
            <div class="field">
                <label><bean:message key="LABEL.reqPriority"/></label>
                <div class="input_box">
                    <html:text property="reqWorkDetailDTO.reqPriorityDesc" tabindex="100"/>
                    <p class="open_spop">
                        <a><span>조회</span></a>
                    </p>
                </div>
            </div>
            <!-- 접수공장(Plant) -->
			<div class="field">
				<label><bean:message key="LABEL.recPlantDesc"/></label>
				<div class="input_box">
					<html:text property="reqWorkDetailDTO.plantDesc" tabindex="110"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 요청내용 -->
			<div class="field_long">
				<label><bean:message key="LABEL.request"/></label>
				<div class="input_box">
					<html:textarea property="reqWorkDetailDTO.reqRequest" styleClass="ta150" tabindex="110" />
				</div>
			</div>
			<!-- 검토내용 -->
			<div class="field_long" id="disableDiv">
				<label><bean:message key="LABEL.reviewDesc"/></label>
				<div class="input_read">
					<html:textarea property="reqWorkDetailDTO.review" styleClass="ta150" tabindex="120" readonly="true"/>
				</div>
			</div>
			<!-- 고장발생일자 -->
			<div class="field">
				<label><bean:message key="LABEL.EQDNDATE"/></label>
				<div class="datetime_wrap">
					<div class="input_box input_carendar">
						<html:text property="reqWorkDetailDTO.eqDnDate" tabindex="120" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box">
						<html:text property="reqWorkDetailDTO.eqDnTime" tabindex="130" />
						<p class="open_time_second"><span>날짜</span></p>
					</div>
				</div>
			</div>
			<!-- 고장발생시간 -->
<!-- 			<div class="field"> -->
<%-- 				<label><bean:message key="LABEL.EQDNTIME"/></label> --%>
<!-- 			</div> -->
			<!-- 부위 -->
			<div class="field">
				<label><bean:message key="LABEL.asmb"/></label>
				<div class="input_box">
					<html:text property="reqWorkDetailDTO.reqEqAsmbDesc" tabindex="140"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<c:set var="filePath" value="enhance/${compName}/req/work/reqWorkDetail_${compNo}.jsp" />
			<c:if test="${udf:isExist(filePath)}">
				<c:import charEncoding="UTF-8" url="/enhance/${compName}/req/work/reqWorkDetail_${compNo}.jsp"></c:import>
			</c:if>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
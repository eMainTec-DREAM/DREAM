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
<%@ page import="dream.part.rep.action.MaPtRepDetailAction"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%@ page import="common.bean.MwareConfig"%>
<html:html>
<head>
<!-- 부품수리 -->
<title><bean:message key='LABEL.ptNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">

var partNoAc;
var mainMngAc;
var reqByAc;
var vendorDescAc, warehouseAc;
var equipAc;
var plantAc;
var eqAsmbAc;
var mngDeptAC;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goUpdate();
		//goTabPage("maPtRepAppList");
		//goTabPage("maDocLibList");
	}
	
	setTitle("maPtRepDetailDTO.partNo", "maPtRepDetailDTO.partNameSize");
	
	setForUpdate();
	
    // 설비 자동완성 
    equipAc = new autoC({"maPtRepDetailDTO.equipName":"description"});
    equipAc.setTable("TAEQUIPMENT");
    equipAc.setKeyName("maPtRepDetailDTO.equipId");
    equipAc.setAcResultMap({
        "maPtRepDetailDTO.equipId":"equip_id"
        ,"maPtRepDetailDTO.itemNo":"item_no"
    });
    equipAc.init();
	

    warehouseAc = new autoC({"maPtRepDetailDTO.wname":"wname"});
    warehouseAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	});
    warehouseAc.setAcDConditionMap({
     	"plant" : "maPtRepDetailDTO.plantId"
     });
    warehouseAc.setTable("TAWAREHOUSE");
    warehouseAc.setAcResultMap({
    	"maPtRepDetailDTO.wcodeId":"wcode_id"
    }); 
    warehouseAc.setKeyName("maPtRepDetailDTO.wcodeId"); 
    warehouseAc.init();  
    
	partNoAc = new autoC({"maPtRepDetailDTO.partNo":"part_no"});
	partNoAc.setAcConditionMap({
	   "comp_no":loginUser.compNo
	   ,"part_categ":"SPPT"
	   });
	partNoAc.setAcDConditionMap({
    	"wcode_id" : "maPtRepDetailDTO.wcodeId"
    });
	partNoAc.setTable("TAPARTS");
	partNoAc.setAcResultMap({
	    "maPtRepDetailDTO.partId":"part_id"
	    ,"maPtRepDetailDTO.partNameSize":"partNameSize"   
	    ,"maPtRepDetailDTO.isSerial":"is_serial_part"
	});
	partNoAc.setKeyName("maPtRepDetailDTO.partId"); 
	partNoAc.init();
	
	mainMngAc = new autoC({"maPtRepDetailDTO.inspectorName":"emp_name"});
	mainMngAc.setAcConditionMap({
		"comp_no":loginUser.compNo,
    	"is_join":"Y"
	   });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maPtRepDetailDTO.deptId",
    	"plant" : "maPtRepDetailDTO.plantId"
    });
	mainMngAc.setTable("TAEMP");
	mainMngAc.setKeyName("maPtRepDetailDTO.inspector");
	mainMngAc.setAcResultMap({
	    "maPtRepDetailDTO.inspector":"emp_id"
	});
	mainMngAc.init();
	    
	reqByAc = new autoC({"maPtRepDetailDTO.requestName":"emp_name"});
	reqByAc.setAcDisplay("EMP_NAME");
	reqByAc.setAcConditionMap({
		"comp_no":loginUser.compNo,
    	"is_join":"Y"
	});
	reqByAc.setAcDConditionMap({
    	"plant" : "maPtRepDetailDTO.plantId"
    });
	reqByAc.setTable("TAEMP");
	reqByAc.setKeyName("maPtRepDetailDTO.requestBy");
	reqByAc.setAcResultMap({
	    "maPtRepDetailDTO.requestBy":"emp_id"
	});
	reqByAc.init();
	
	vendorDescAc = new autoC({"maPtRepDetailDTO.vendorDesc":"description"});
    vendorDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_use":"Y"
  	   });
    vendorDescAc.setTable("TAVENDOR");
    vendorDescAc.setKeyName("maPtRepDetailDTO.vendorId");
    vendorDescAc.setAcResultMap({
        "maPtRepDetailDTO.vendorId":"vendor_id"
    });
    vendorDescAc.init();
	/* $("input[name='maPtRepDetailDTO.partNo']").on({
		"keyup":function(e){
			valPartNo('maPtRepDetailDTO.partId','maPtRepDetailDTO.partNo','maPtRepDetailDTO.partNameSize', true);
		}
	}); */
	
	partSerialAc = new autoC({"maPtRepDetailDTO.serialNo":"serial_no"});
	partSerialAc.setAcConditionMap({
	   "comp_no":loginUser.compNo,
	   //"part_id":$('[name="maPtRepDetailDTO.partId"]').val(),
		"eq_status":"S"
	   });
	partSerialAc.setAcDConditionMap({
        "part_id" : "maPtRepDetailDTO.partId"
    });
	partSerialAc.setTable("TAPARTEQUIP");
	partSerialAc.setAcResultMap({
	    "maPtRepDetailDTO.serialNo":"serialNo"
	    ,"maPtRepDetailDTO.equipId":"equipId"   
	});
	partSerialAc.init();
	
    /** 공장 */
    plantAc = new autoC({"maPtRepDetailDTO.plantDesc":"description"});
    plantAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	, "is_use":"Y"
  	   });
    plantAc.setTable("TAPLANT");
    plantAc.setKeyName("maPtRepDetailDTO.plantId");
    plantAc.setAcResultMap({
        "maPtRepDetailDTO.plantId":"plant"
    });
    plantAc.init();
    
    /** 부위 */
    eqAsmbAc = new autoC({"maPtRepDetailDTO.eqAsmbDesc":"full_desc"});
    eqAsmbAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    eqAsmbAc.setAcDConditionMap({
    	"equip_id":"maPtRepDetailDTO.equipId"
  	   });
    eqAsmbAc.setTable("TAEQASMB");
    eqAsmbAc.setKeyName("maPtRepDetailDTO.eqAsmbId");
    eqAsmbAc.setAcResultMap({
        "maPtRepDetailDTO.eqAsmbId":"eqasmb_id"
    });
    eqAsmbAc.init();
	
    /** 담당부서 */
    mngDeptAC = new autoC({"maPtRepDetailDTO.deptDesc":"description"});
    mngDeptAC.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	,"is_use":"Y"
    });
    mngDeptAC.setAcDConditionMap({
    	"plant":"maPtRepDetailDTO.plantId"
  	});
    mngDeptAC.setTable("TADEPT");
    mngDeptAC.setKeyName("maPtRepDetailDTO.deptId");
    mngDeptAC.setAcResultMap({
    	"maPtRepDetailDTO.deptId":"dept_id"
    });
    mngDeptAC.init();
    
    acSysDesc("maPtRepDetailDTO.partGradeDesc","maPtRepDetailDTO.partGrade","PART_GRADE", true);
}
	
function goUpdate()
{
    //수정시 readonly설정 
	
    var ptRepairListStatus = maPtRepDetailForm.elements['maPtRepDetailDTO.ptRepairListStatus'].value;
    // 수리불가, 사내수리, 외주수리 완료시 취소 버튼만 활성화.
    if(ptRepairListStatus == "X" || ptRepairListStatus == "L" || ptRepairListStatus == "V" )
    {
        $(document).find('.fb_group2').children().each(function(){
            if($(this).is('a'))
                $(this).hide();
            else
                return false;           
        });
        $(document).find('.b_cancel').show();
        
        // 입력 Form disable
    	setDisable($(".form_box"));
     
    }
    else if(ptRepairListStatus == "S"){
    	$(document).find('.fb_group2').children().each(function(){
            if($(this).is('a'))
                $(this).hide();
            else
                return false;           
        });
        $(document).find('.b_repair_v').show();
        $(document).find('.b_repair_x').show();
        $(document).find('.b_save').show();
    }else
    {
        //button Show
	    $(document).find('.fb_group2').children().each(function(){
	        if($(this).is('a'))
	            $(this).show();
	        else
	            return false;           
	    });
        $(document).find('.b_repair_v').hide();
	    $(document).find('.b_cancel').hide();

	    // 입력 Form disable
        setEnable($(".form_box"));
    }
    
    $(document).find('.b_colsetting').show();
}

function goInput()
{
	sequenceNextVal('SQAPTREPAIRLIST_ID');
	
	maPtRepDetailForm.elements['maPtRepDetailDTO.regDate'].value = getToday(); 
	maPtRepDetailForm.elements['maPtRepDetailDTO.deptId'].value = loginUser.deptId;
	maPtRepDetailForm.elements['maPtRepDetailDTO.deptDesc'].value = loginUser.deptDesc;
	maPtRepDetailForm.elements['maPtRepDetailDTO.inspector'].value = loginUser.empId;
	maPtRepDetailForm.elements['maPtRepDetailDTO.inspectorName'].value = loginUser.empName;
	maPtRepDetailForm.elements['maPtRepDetailDTO.wcodeId'].value = loginUser.wcodeId;
	maPtRepDetailForm.elements['maPtRepDetailDTO.wname'].value = loginUser.wname;
	maPtRepDetailForm.elements['maPtRepDetailDTO.plantId'].value = loginUser.plant;
	maPtRepDetailForm.elements['maPtRepDetailDTO.plantDesc'].value = loginUser.plantDesc;
	
	// 입고상태 : W=작성중
	maPtRepDetailForm.elements['maPtRepDetailDTO.ptRepairListStatus'].value = "W"; 
	valSysDirCode('maPtRepDetailDTO.ptRepairListStatus', 'maPtRepDetailDTO.ptRepairListStatusDesc', 'REPAIR_STATUS', true);
	
	if('N'=='<%=MwareConfig.getIsUsePartGrade()%>') {
		maPtRepDetailForm.elements['maPtRepDetailDTO.partGrade'].value = '<%=MwareConfig.getPartGrade()%>'; 
		valSysDirCode('maPtRepDetailDTO.partGrade', 'maPtRepDetailDTO.partGradeDesc', 'PART_GRADE', true);
	}
	else {
		// 재고등급 B
		maPtRepDetailForm.elements['maPtRepDetailDTO.partGrade'].value = "B"; 
		valSysDirCode('maPtRepDetailDTO.partGrade', 'maPtRepDetailDTO.partGradeDesc', 'PART_GRADE', true);
	}
	
    // 버튼 설정 
	$(document).find('.fb_group2').children().each(function(){
        if($(this).is('a'))
            $(this).hide();
        else
            return false;
    });
    $(document).find('.b_save').show();
    $(document).find('.b_colsetting').show();
}

function setSequenceVal(sequenceVal)
{
	maPtRepDetailForm.elements['maPtRepDetailDTO.ptRepairListId'].value = sequenceVal;
	maPtRepDetailForm.elements['maPtRepDetailDTO.ptRepairListNo'].value = sequenceVal;
	maPtRepDetailForm.elements['maPtRepCommonDTO.ptRepairListId'].value = sequenceVal;
   
	//goTabPage("maPtRepAppList");
}

function goOpen(pageId)
{
    goTabPage(pageId);
}
    
function goTabPage(pageId)
{
    var form = document.maPtRepDetailForm;

    if(pageId == "maPtRepDocLibList")
    {   
        maPtRepDetailForm.elements['maDocLibCommonDTO.objectId'].value = maPtRepDetailForm.elements['maPtRepCommonDTO.ptRepairListId'].value;
        maPtRepDetailForm.elements['maDocLibCommonDTO.objectType'].value = "PTREPAIR";  //PTREPAIR - 148
        maPtRepDetailForm.elements['maDocLibCommonDTO.securGrade'].value = "3";  // 3등급 기본세팅 
        maPtRepDetailForm.elements['maDocLibCommonDTO.description'].value = maPtRepDetailForm.elements['maPtRepDetailDTO.partNo'].value;  
        goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
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
	
    var repairQty = maPtRepDetailForm.elements['maPtRepDetailDTO.repairQty'].value;
    repairQty = intToData(repairQty);
    if(repairQty<=0){
    	alertMessage1("<bean:message key='MESSAGE.MSG0055'/>");
    	closeModal();
    	return;
    }
	
	//strutsAction 셋팅.
	if(ckCreate(currentPageId)) maPtRepDetailForm.strutsAction.value = '<%=MaPtRepDetailAction.PTREP_DETAIL_INPUT%>';
	else maPtRepDetailForm.strutsAction.value = '<%=MaPtRepDetailAction.PTREP_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/maPtRepDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maPtRepDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
function afterSave(ajaxXmlDoc)
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	maPtRepDetailForm.elements['maPtRepDetailDTO.ptRepairListId'].value = maPtRepDetailForm.elements['maPtRepCommonDTO.ptRepairListId'].value;
	if (parent.findGridRow)	parent.findGridRow(maPtRepDetailForm.elements['maPtRepDetailDTO.ptRepairListId'].value);
	getTopPage().afterSaveAll(currentPageId);
	
	goUpdate();
	setTitle("maPtRepDetailDTO.partNo", "maPtRepDetailDTO.partNameSize");
}
 
function sumTotPrice()
{
	 var unitPrice = maPtRepDetailForm.elements['maPtRepDetailDTO.unitPrice'].value;
	 var repairQty = maPtRepDetailForm.elements['maPtRepDetailDTO.repairQty'].value;
	
	 var result = intToData(repairQty) * intToData(unitPrice);
	 maPtRepDetailForm.elements['maPtRepDetailDTO.totPrice'].value = result;
	 setMoneyFormat(maPtRepDetailForm.elements['maPtRepDetailDTO.totPrice'], "3");
}

/**
 * 수리불가 처리 
 */
function goRepair_x()
{
    getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0020'/>", function(result){
        if(result)
        {
		    maPtRepDetailForm.strutsAction.value = '<%=MaPtRepDetailAction.PTREP_DETAIL_STATUS_UPDATE%>';
		    
		    maPtRepDetailForm.elements['maPtRepDetailDTO.ptRepairListStatus'].value = "X";
            maPtRepDetailForm.elements['maPtRepDetailDTO.ptRecMode'].value = ""; 
		    
		    var actionUrl = contextPath + "/maPtRepDetail.do";
		    XMLHttpPost(actionUrl, FormQueryString(maPtRepDetailForm), 'afterSaveStatus');
        }
    });
}

/**
 * 사내입고 처리 
 */
function goRepair_l()
{
	// 필수 체크 : 수리수량
    if(checkRequireValue('maPtRepDetailDTO.repairQty', '<bean:message key="LABEL.repairQty"/>')) return;
    // 필수 체크 : 수리완료일자
    if(checkRequireValue('maPtRepDetailDTO.repairDate', '<bean:message key="LABEL.repairDate"/>')) return;
    // 필수 체크 : 창고명
    if(checkRequireValue('maPtRepDetailDTO.wname', '<bean:message key="LABEL.wname"/>')) return;
    if(checkIsUpdate(document)){
		 alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
	 }else{
		 getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0021'/>", function(result){
		        if(result)
		        {
				    maPtRepDetailForm.strutsAction.value = '<%=MaPtRepDetailAction.PTREP_DETAIL_STATUS_UPDATE%>';
				    
				    maPtRepDetailForm.elements['maPtRepDetailDTO.ptRepairListStatus'].value = "L";
		            maPtRepDetailForm.elements['maPtRepDetailDTO.ptRecMode'].value = "C"; // 입고 
				    
				    var actionUrl = contextPath + "/maPtRepDetail.do";
				    XMLHttpPost(actionUrl, FormQueryString(maPtRepDetailForm), 'afterSaveStatus');
		        }
		    });
	 }
}

/**
 * 외주수리입고 처리 
 */
function goRepair_v()
{
	if(maPtRepDetailForm.elements['maPtRepDetailDTO.ptRepairListStatus'].value!="S"){
		alertMessage1("<bean:message key='MESSAGE.MSG0058'/>");
		return;
	}
	// 필수 체크 : 수리거래처, 수리수량
	if(checkRequireValue('maPtRepDetailDTO.vendorId', '<bean:message key="LABEL.repairVendor"/>')) return;
	if(checkRequireValue('maPtRepDetailDTO.repairQty', '<bean:message key="LABEL.repairQty"/>')) return;
    // 필수 체크 : 수리완료일자
    if(checkRequireValue('maPtRepDetailDTO.repairDate', '<bean:message key="LABEL.repairDate"/>')) return;
    // 필수 체크 : 창고명
    if(checkRequireValue('maPtRepDetailDTO.wname', '<bean:message key="LABEL.wname"/>')) return;
    // 필수 체크 : 검수자
    if(checkRequireValue('maPtRepDetailDTO.inspectorName', '<bean:message key="LABEL.inspector"/>')) return;
    if(checkIsUpdate(document)){
		 alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
	 }else{
		 getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0022'/>", function(result){
		        if(result)
		        {
				    maPtRepDetailForm.strutsAction.value = '<%=MaPtRepDetailAction.PTREP_DETAIL_STATUS_UPDATE%>';
				    
				    maPtRepDetailForm.elements['maPtRepDetailDTO.ptRepairListStatus'].value = "V";
		            maPtRepDetailForm.elements['maPtRepDetailDTO.ptRecMode'].value = "C"; // 입고 
				    
				    var actionUrl = contextPath + "/maPtRepDetail.do";
				    XMLHttpPost(actionUrl, FormQueryString(maPtRepDetailForm), 'afterSaveStatus');
		        }
		    });
	 }
}

/**
 * 외주수리의뢰 버튼
 */
 function goReqrepven(){
	// 필수 체크 : 수리거래처, 수리수량
		if(checkRequireValue('maPtRepDetailDTO.vendorId', '<bean:message key="LABEL.repairVendor"/>')) return;
	    // 필수 체크 : 수리의뢰일자
	    if(checkRequireValue('maPtRepDetailDTO.requestDate', '<bean:message key="LABEL.repairRequestDate"/>')) return;
	    if(checkIsUpdate(document)){
			 alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
		 }else{
			 getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0059'/>", function(result){
			        if(result)
			        {
					    maPtRepDetailForm.strutsAction.value = '<%=MaPtRepDetailAction.PTREP_DETAIL_STATUS_UPDATE%>';
					    
					    maPtRepDetailForm.elements['maPtRepDetailDTO.ptRepairListStatus'].value = "S";
					    
					    var actionUrl = contextPath + "/maPtRepDetail.do";
					    XMLHttpPost(actionUrl, FormQueryString(maPtRepDetailForm), 'afterSaveStatus');
			        }
			    });
		 }
}

/**
 * 취소 처리 
 */
function goCancel()
{
    getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0023'/>", function(result){
        if(result)
        {
		    maPtRepDetailForm.strutsAction.value = '<%=MaPtRepDetailAction.PTREP_DETAIL_STATUS_CANCEL%>';
		    
		    maPtRepDetailForm.elements['maPtRepDetailDTO.ptRepairListStatus'].value = "W"; // 대기상태
		    maPtRepDetailForm.elements['maPtRepDetailDTO.ptRecMode'].value = "R"; // 입고이력 취소 
		    
		    var actionUrl = contextPath + "/maPtRepDetail.do";
		    XMLHttpPost(actionUrl, FormQueryString(maPtRepDetailForm), 'afterSaveStatus');
        }
    });
}

/**
 * 상태값 처리 후 호출 
 */
function afterSaveStatus()
{
	valSysDirCode('maPtRepDetailDTO.ptRepairListStatus', 'maPtRepDetailDTO.ptRepairListStatusDesc', 'REPAIR_STATUS', true);
	maPtRepDetailForm.elements['maPtRepDetailDTO.ptRecMode'].value = ""; // 입고이력 모드 초기화
	
	maPtRepDetailForm.elements['maPtRepDetailDTO.ptRepairListId'].value = maPtRepDetailForm.elements['maPtRepCommonDTO.ptRepairListId'].value;
	if (parent.findGridRow)	parent.findGridRow(maPtRepDetailForm.elements['maPtRepDetailDTO.ptRepairListId'].value);
	
	goUpdate();
}


/* audit Trail */
function goAudtrailLink()
{
	var objectId = maPtRepDetailForm.elements['maPtRepDetailDTO.ptRepairListId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maPtRepDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="maPtRepCommonDTO.ptRepairListId" />
	<html:hidden property="currentPageId"/>
	<html:hidden property="maPtRepDetailDTO.ptRepairListId" />
	<html:hidden property="maPtRepDetailDTO.ptRepairListStatus" />
	<html:hidden property="maPtRepDetailDTO.partId" />
	<html:hidden property="maPtRepDetailDTO.vendorId" />
	<html:hidden property="maPtRepDetailDTO.deptId" />
	<html:hidden property="maPtRepDetailDTO.wcodeId" />
	<html:hidden property="maPtRepDetailDTO.inspector" />
	<html:hidden property="maPtRepDetailDTO.ptRecMode" />
	<html:hidden property="maPtRepDetailDTO.requestBy" />
	<html:hidden property="maPtRepDetailDTO.isSerial" />
	<html:hidden property="maPtRepDetailDTO.equipId" />
	<html:hidden property="maPtRepDetailDTO.plantId" />
 	<html:hidden property="maPtRepDetailDTO.eqAsmbId"/>
 	<html:hidden property="maPtRepDetailDTO.partGrade"/>
    <html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
    <html:hidden property="maDocLibCommonDTO.objectType" />
    <html:hidden property="maDocLibCommonDTO.securGrade" />
    <html:hidden property="maDocLibCommonDTO.description" />
	<div class="article_box">
		<div class="form_box">
			<div class="field">
				<label><bean:message key="LABEL.ptRepairListNo"/></label>
				<div id="ptRepairListNoDiv" class="input_read">
					<html:text property="maPtRepDetailDTO.ptRepairListNo" tabindex="10" readonly="true" />
				</div>
			</div>
			<div class="field">
				<label><bean:message key="LABEL.repairStatus"/></label>
				<div class="input_read">
					<html:text property="maPtRepDetailDTO.ptRepairListStatusDesc" tabindex="20" readonly="true" />
				</div>
			</div>
			<div class="field">
                <label class="check"><bean:message key="LABEL.ptNo"/></label>
                <div id="partNoDiv" class="input_box">
                    <html:text property="maPtRepDetailDTO.partNo" tabindex="30"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
            </div>
			<div class="field">
                <label class="check"><bean:message key="LABEL.partGrade"/></label>
                <div class="input_box">
                    <html:text property="maPtRepDetailDTO.partGradeDesc" tabindex="35"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
            </div>

            <div class="field">
                <label><bean:message key="LABEL.repRegDate"/></label>
                <div class="input_read">
                    <html:text property="maPtRepDetailDTO.regDate" tabindex="40" readonly="true"/>
                    <p class="open_calendar"><span>날짜</span></p>
                </div>
            </div>  
            <div class="field">
                <label><bean:message key="LABEL.serialNo"/></label>
                <div class="input_box">
                    <html:text property="maPtRepDetailDTO.serialNo" tabindex="30"/>
                </div>
            </div>
            <div class="field">
                <label><bean:message key="LABEL.ptNameSize"/></label>
                <div class="input_read">
                    <html:text property="maPtRepDetailDTO.partNameSize" tabindex="50" readonly="true"/>
                </div>
            </div>
            <div class="field">
                <label><bean:message key="LABEL.equipName"/></label>
                <div class="input_read">
                    <html:text property="maPtRepDetailDTO.equipName" tabindex="60" />
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
 			<!-- 부위 -->
			<div class="field">
				<label><bean:message key="LABEL.asmb"/></label>
				<div class="input_box">
					<html:text property="maPtRepDetailDTO.eqAsmbDesc" tabindex="70"/>
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
            <!-- 설비번호 -->
			<div class="field">
				<label><bean:message key="LABEL.equipNo"/></label>
				<div class="input_read">
					<html:text property="maPtRepDetailDTO.itemNo" tabindex="10" readonly="true"/>
				</div>
			</div>
            <div class="field">
               <label><bean:message key="LABEL.repairQty"/></label>
               <div class="input_box">
                   <html:text property="maPtRepDetailDTO.repairQty" tabindex="70"  
                       onblur="javascript:sumTotPrice();" styleClass="num"/>
               </div>
            </div>   
            <div class="field">
                <label><bean:message key="LABEL.manageDept"/></label>
                <div class="input_box">
                    <html:text property="maPtRepDetailDTO.deptDesc" tabindex="80"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>  
            <!-- 비고 -->
            <div class="field_long">
                <label><bean:message key="LABEL.remark"/></label>
                <div class="input_box">
                    <html:textarea property="maPtRepDetailDTO.remark" styleClass="ta50" tabindex="90" />
                </div>
            </div>       
            <div class="field_long_blank"></div> 
            <div class="field">
                <label><bean:message key="LABEL.repairRequestDate"/></label>
                <div class="input_box">
                    <html:text property="maPtRepDetailDTO.requestDate" tabindex="100" />
                    <p class="open_calendar"><span>날짜</span></p>
                </div>
            </div>	
            <div class="field_blank"></div>
            <div class="field">
				<label><bean:message key="LABEL.repairVendor"/></label>
				<div class="input_box">
                    <html:text property="maPtRepDetailDTO.vendorDesc" tabindex="110" />
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
			</div>
			<div class="field">
                <label><bean:message key="LABEL.repairRequestBy"/></label>
                <div class="input_box">
                    <html:text property="maPtRepDetailDTO.requestName" tabindex="120"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>  
			<div class="field">
                <label><bean:message key="LABEL.repairUnitPrice"/></label>
                <div class="input_box">
                    <html:text property="maPtRepDetailDTO.unitPrice" tabindex="130" 
                        onblur="javascript:sumTotPrice();" styleClass="num"/>
                </div>
            </div>			
            <div class="field">
                <label><bean:message key="LABEL.repairTotPrice"/></label>
                <div class="input_read">
                    <html:text property="maPtRepDetailDTO.totPrice" tabindex="140" readonly="true" styleClass="num"/>
                </div>
            </div>  
            
            <div class="field_long_blank"></div>
            <div class="field">
                <label><bean:message key="LABEL.repairDate"/></label>
                <div class="input_box">
                    <html:text property="maPtRepDetailDTO.repairDate" tabindex="150" />
                    <p class="open_calendar"><span>날짜</span></p>
                </div>
            </div>
            <div class="field_blank"></div>
            <div class="field">
                <label><bean:message key="LABEL.inspector"/></label>
                <div class="input_box">
                    <html:text property="maPtRepDetailDTO.inspectorName" tabindex="160"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>    
            
            <div class="field">
               <label><bean:message key="LABEL.wname"/></label>
               <div id="wnameDiv" class="input_box">
                   <html:text property="maPtRepDetailDTO.wname" tabindex="170"/>
                   <p id="wnameSchBtn" class="open_spop">
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
					<html:text property="maPtRepDetailDTO.plantDesc" tabindex="80"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>		        
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>

<%--===========================================================================
작업계획목록 - 상세
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="common.bean.User"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%@ page import="dream.work.list.action.WoPlanDetailAction"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html>
<head>
<!-- WO # -->
<title><bean:message key='LABEL.woNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
var saveStrutsAction;

/** 자동완성 변수  */
var equipDescAc;
var mainMngAc;
var deptAc;
var vendorDescAc;
var wkCtrAc;
var subEmpAc;
var pmTypeAc;
var woTypeAc;
var eqAsmbAc;
var plantAc;

var isPmTypeLoad = false;   // true면 distroy 후 재로딩
var isVendorLoad = false;   // true면 distroy 후 재로딩

function loadPage() 
{	
	setInitVal("woPlanDetailDTO.woReqResId", woPlanDetailForm.elements['woPlanCommonDTO.woReqResId'].value);
	
	setTitle("woPlanDetailDTO.woNo","woPlanDetailDTO.wkOrDesc");
	
	setForUpdate();
	
	$("input[name='woPlanDetailDTO.endDate']").blur( function(){
		setWorkTime();
    });
	$("input[name='woPlanDetailDTO.endTime']").blur( function(){
		setWorkTime();
    });
	
	//설비
	equipDescAc = new autoC({"woPlanDetailDTO.equipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo,
 	   "EQ_STATUS":"R+S"
 	   });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcResultMap({
        "woPlanDetailDTO.equipId":"equip_id"
        ,"woPlanDetailDTO.eqLocId":"eqloc_id"
        ,"woPlanDetailDTO.eqLocDesc":"eqLocDesc"
    });
    equipDescAc.setKeyName("woPlanDetailDTO.equipId"); 
    equipDescAc.init();
    
    //담당자(정)
    mainMngAc = new autoC({"woPlanDetailDTO.empDesc":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
  	   });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "woPlanDetailDTO.deptId"
    });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setKeyName("woPlanDetailDTO.empId");
    mainMngAc.setAcResultMap({
        "woPlanDetailDTO.empId":"emp_id"
    });
    mainMngAc.init();
    
    //담당자(부)
    subEmpAc = new autoC({"woPlanDetailDTO.subEmpDesc":"emp_name"});
    subEmpAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
  	   });
    subEmpAc.setAcDConditionMap({
    	"dept_id" : "woPlanDetailDTO.deptId"
    });
    subEmpAc.setTable("TAEMP");
    subEmpAc.setKeyName("woPlanDetailDTO.subEmpId");
    subEmpAc.setAcResultMap({
        "woPlanDetailDTO.subEmpId":"emp_id"
    });
    subEmpAc.init();
    
    //부서
    deptAc = new autoC({"woPlanDetailDTO.deptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setKeyName("woPlanDetailDTO.deptId");
    deptAc.setAcResultMap({
        "woPlanDetailDTO.deptId":"dept_id"
    });
    deptAc.init();

	//교대조  AC
    acSysDesc("woPlanDetailDTO.shiftTypeDesc","woPlanDetailDTO.shiftTypeId","SHIFT_TYPE",true);
	//자가/외주 구분  AC
    acSysDesc("woPlanDetailDTO.selfVendorTypeDesc","woPlanDetailDTO.selfVendorType","SELF_VENDOR_TYPE",true);
    
	//작업그룹
    wkCtrAc = new autoC({"woPlanDetailDTO.wkCtrDesc":"description"});
    wkCtrAc.setAcDisplay("DESCRIPTION");
    wkCtrAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    wkCtrAc.setTable("TAWKCTR");
    wkCtrAc.setKeyName("woPlanDetailDTO.wkCtrId");
    wkCtrAc.setAcResultMap({
        "woPlanDetailDTO.wkCtrId":"wkctr_id"
    });
    wkCtrAc.init();
	
    // 작업종류
    woTypeAc = new autoC({"woPlanDetailDTO.woTypeDesc":"description"});
	woTypeAc.setAcDisplay("DESCRIPTION");
	woTypeAc.setAcConditionMap({
		"list_type":"WO_TYPE"
      , "param22":"param22"
      , "is_use":"Y"
  	   });
	woTypeAc.setTable("TACDSYSD");
	woTypeAc.setAcResultMap({
        "woPlanDetailDTO.woTypeId":"cdsysd_no"
    });
	woTypeAc.init();
    
    //거래처 AC
    vendorDescAc = new autoC({"woPlanDetailDTO.vendorDesc":"description"});
    vendorDescAc.setAcConditionMap({
        "comp_no":loginUser.compNo,
        "is_use":"Y"
    });
    vendorDescAc.setTable("TAVENDOR");
    vendorDescAc.setKeyName("woPlanDetailDTO.vendorId");
    vendorDescAc.setAcResultMap({
        "woPlanDetailDTO.vendorId":"vendor_id"
    });
    vendorDescAc.init();	
    
  	//공장 
    plantAc = new autoC({"woPlanDetailDTO.plantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setKeyName("woPlanDetailDTO.plant");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "woPlanDetailDTO.plant":"plant"
    });
    plantAc.init();
    
    //부위
    eqAsmbAc = new autoC({"woPlanDetailDTO.eqAsmbDesc":"full_desc"});
    eqAsmbAc.setTable("TAEQASMB");
    eqAsmbAc.setAcConditionMap({
		"comp_no":loginUser.compNo
      , "is_use" : "Y"	
  	});
    eqAsmbAc.setAcDConditionMap({
    	"equip_id":"woPlanDetailDTO.equipId"
  	});
    eqAsmbAc.setKeyName("woPlanDetailDTO.eqAsmbId");
    eqAsmbAc.setAcResultMap({
        "woPlanDetailDTO.eqAsmbId":"eqasmb_id"
    });
    eqAsmbAc.init();
    
	if(ckCreate(currentPageId))
		goInput();
	else 
		goUpdate();
}


/*작업종류 선택후 실행*/
function afterAutoCmpt(code)
{
    if(code=="woPlanDetailDTO.woTypeDesc")
    {
        var listType = woPlanDetailForm.elements['woPlanDetailDTO.woTypeId'].value+"_TYPE";
        
        setPmTypeAc(listType);
        isPmTypeLoad = true;
        
        //자가/외주 설비종류가 투자인것은 외주로 셋팅, 나머지는 자가로 셋팅
        if(woPlanDetailForm.elements['woPlanCommonDTO.selectedWoType'].value=="IV"||
                woPlanDetailForm.elements['woPlanDetailDTO.woTypeId'].value=="IV"){
            woPlanDetailForm.elements['woPlanDetailDTO.selfVendorType'].value = "VENDOR";
            woPlanDetailForm.elements['woPlanDetailDTO.selfVendorTypeDesc'].value = "VENDOR";
            valSysDir('woPlanDetailDTO.selfVendorType', 'woPlanDetailDTO.selfVendorTypeDesc', 'SELF_VENDOR_TYPE', true);
            setVendorDescAc();
            isVendorLoad = true;
        }else{
            woPlanDetailForm.elements['woPlanDetailDTO.selfVendorType'].value = "SELF";
            woPlanDetailForm.elements['woPlanDetailDTO.selfVendorTypeDesc'].value = "SELF";
            woPlanDetailForm.elements['woPlanDetailDTO.vendorId'].value = "";
            woPlanDetailForm.elements['woPlanDetailDTO.vendorDesc'].value = "";
            valSysDir('woPlanDetailDTO.selfVendorType', 'woPlanDetailDTO.selfVendorTypeDesc', 'SELF_VENDOR_TYPE', true);
            if("undefined"!=vendorDescAc&& isVendorLoad) 
            	vendorDescAc.destroy();
        }
    }
    
     if(code=="woPlanDetailDTO.selfVendorTypeDesc" && woPlanDetailForm.elements['woPlanDetailDTO.selfVendorType'].value == 'VENDOR')
    {
    	isVendorLoad = true;
    	setVendorDescAc();
    }
}

// 작업형태 AC
function setPmTypeAc(listType)
{
	if(isPmTypeLoad)
    {
        // 작업종류를 재선택한 경우 distroy 후 로드한다.
        pmTypeAc.destroy();
        
        // 선택했던 작업형태 초기화
        woPlanDetailForm.elements['woPlanDetailDTO.pmTypeDesc'].value = "";
        woPlanDetailForm.elements['woPlanDetailDTO.pmTypeId'].value = "";
        
        isPmTypeLoad = false;
    }
	
	// 작업형태
    pmTypeAc = new autoC({"woPlanDetailDTO.pmTypeDesc":"description"});
    pmTypeAc.setAcConditionMap({
            "list_type":listType,
            "is_use":"Y"
       });
    pmTypeAc.setTable("TACDSYSD");
    pmTypeAc.setKeyName("woPlanDetailDTO.pmTypeId");
    pmTypeAc.setAcResultMap({
        "woPlanDetailDTO.pmTypeId":"cdsysd_no"
    });
    pmTypeAc.init();
}

// 거래처 AC
function setVendorDescAc()
{
	if(isVendorLoad)
	{
		// 작업종류를 재선택한 경우 distroy 후 로드한다.
		vendorDescAc.destroy();
		isVendorLoad = false;
	}
	//거래처 AC
    vendorDescAc = new autoC({"woPlanDetailDTO.vendorDesc":"description"});
    vendorDescAc.setAcConditionMap({
        "comp_no":loginUser.compNo,
        "is_use":"Y"
    });
    vendorDescAc.setTable("TAVENDOR");
    vendorDescAc.setKeyName("woPlanDetailDTO.vendorId");
    vendorDescAc.setAcResultMap({
        "woPlanDetailDTO.vendorId":"vendor_id"
    });
    vendorDescAc.init();	
}

function goUpdate(){
	hideBtn("REVERSE");
	//예방점검기준서에 의해 생성된 작업결과는 설비를 변경하지못함.
	if(woPlanDetailForm.elements['woPlanDetailDTO.woGenType'].value =="PMPLAN"){
		woPlanDetailForm.elements['woPlanDetailDTO.equipDesc'].readOnly = true;
	    document.getElementById("equipDiv").className = "input_read"; 
	    document.getElementById("equipPopDiv").style.display = "none";
	}

	checkWoPlanStatus();
}

function goInput()
{
	//작업상태 = PPW - 작업계획대기
	setInitVal('woPlanDetailDTO.woPlanStatusId', 'PPW');
	setInitVal('woPlanDetailDTO.woPlanStatusDesc', getSysCodeDesc('WOPLAN_STATUS', 'PPW'));
	
	//작업시작일자, 종료일자 넣기.
	setInitVal('woPlanDetailDTO.startDate', getToday());
	setInitVal('woPlanDetailDTO.endDate', getToday());

	//작업일자 넣기
	setInitVal('woPlanDetailDTO.wkorDate', getToday);
	
	//작업시작시간(1시간전), 종료시간(현재시간) 넣기.
	setInitVal('woPlanDetailDTO.startTime', getMinusTime(false, '1'));
	setInitVal('woPlanDetailDTO.endTime', getTime(false));
	
	//부서
	setInitVal('woPlanDetailDTO.deptId', '<%=user.getDeptId()%>');
	setInitVal('woPlanDetailDTO.deptDesc', '<%=user.getDeptDesc()%>');
	
	//사원
	setInitVal('woPlanDetailDTO.empId', '<%=user.getEmpId()%>');
	setInitVal('woPlanDetailDTO.empDesc', '<%=user.getEmpName()%>');

	//교대조 세팅
	setInitVal('woPlanDetailDTO.shiftTypeId', '<%=user.getShiftType()%>');
	setInitVal('woPlanDetailDTO.shiftTypeDesc', '<%=user.getShiftTypeDesc()%>');
	
	setWorkTime();
	
	if(woPlanDetailForm.elements['woPlanDetailDTO.equipId'].value == ""){
		equipDescAc.openLov();
	}
	
	checkWoPlanStatus();
	
	sequenceNextVal('SQAWKOR_ID');
}

function setSequenceVal(sequenceVal)
{
	setInitVal('woPlanDetailDTO.woNo', sequenceVal);
	setInitVal('woPlanDetailDTO.wkOrId', sequenceVal);
	setInitVal('woPlanCommonDTO.wkOrId', sequenceVal);
}

function goOpen(pageId)
{
	goTabPage(pageId);
}

function goTabPage(pageId)
{
	var form = document.woPlanDetailForm;
	
	if(pageId == "maDocLibList" || pageId == "maWoDocLibList")
	{	
		woPlanDetailForm.elements['maDocLibCommonDTO.objectId'].value = woPlanDetailForm.elements['woPlanCommonDTO.wkOrId'].value;
		woPlanDetailForm.elements['maDocLibCommonDTO.objectType'].value = "WOPLAN";  //WOPLAN
		woPlanDetailForm.elements['maDocLibCommonDTO.description'].value = 
			woPlanDetailForm.elements['woPlanDetailDTO.wkOrDesc'].value;  //제목
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else if(pageId == "maAppPrcList" || pageId == "appPrcList") {
        woPlanDetailForm.elements['appReqCommonDTO.objectId'].value = woPlanDetailForm.elements['woPlanCommonDTO.wkOrId'].value;
        woPlanDetailForm.elements['appReqCommonDTO.apprType'].value = "WOPLAN";
        goCommonTabPage(form, '' , pageId);
	}
    else
		goCommonTabPage(form, '' , pageId);
}
/**
 * 점검항목의 모든 결과값이 들어있는지 확인.
 */
var isValid = 0;

function checkPoint()
{
	isValid = 0;
	
	var actionUrl = contextPath + "/woPlanDetail.do";
    var param =  "&strutsAction=" + '<%= WoPlanDetailAction.WO_PLAN_DETAIL_CHECKPOINT %>'
              +  "&" + FormQueryString(woPlanDetailForm);
    XMLHttpPostVal(actionUrl, param, 'setValidCheckPoint');
    
}
function setValidCheckPoint(ajaxXmlDoc)
{
	isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
	if(isValid == '0')
    {
		 woPlanDetailForm.strutsAction.value = '<%=WoPlanDetailAction.WO_PLAN_DETAIL_COMPLETE%>';
		 var actionUrl = contextPath + "/woPlanDetail.do";
			XMLHttpPost(actionUrl, FormQueryString(woPlanDetailForm), 'afterConfirm');
    }else{
    	alertMessage1("<bean:message key='MESSAGE.MSG0112' />");
    	return;
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
 	
	//strutsAction 셋팅.
	if(ckCreate(currentPageId)) woPlanDetailForm.strutsAction.value = '<%=WoPlanDetailAction.WO_PLAN_DETAIL_INPUT%>';
	else woPlanDetailForm.strutsAction.value = '<%=WoPlanDetailAction.WO_PLAN_DETAIL_UPDATE%>';
	
	if(M$('woPlanDetailDTO.workTime').value == '') setWorkTime();
	
	var actionUrl = contextPath + "/woPlanDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(woPlanDetailForm), 'afterSave');
}

/**
 * Time 세팅되고 호출되는 Callback
 */
function afterSetTime(_name)
{
	setWorkTime();
}

/**
 * Date 세팅되고 호출되는 Callback
 */
function afterSetDate(_name)
{
	setWorkTime();
}

 /**
  * 저장후 호출
  */
function afterSave(ajaxXmlDoc)
 {
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	woPlanDetailForm.elements['woPlanDetailDTO.wkOrId'].value = woPlanDetailForm.elements['woPlanCommonDTO.wkOrId'].value;

    // 요청 - 투자결과 (목록)에서 저장한 경우 
    if (parent.currentPageId == "reqWoPlanRsltList" && woPlanDetailForm.strutsAction.value == '<%=WoPlanDetailAction.WO_PLAN_DETAIL_INPUT%>') 
  	{
   	 	parent.afterCreate(woPlanDetailForm.elements['woPlanCommonDTO.wkOrId'].value);
  	}
 	// 이상점검처리대상 - 작업계획 (목록) 에서 저장한 경우
    else if(parent.currentPageId == "maBdPointWoPlanList" && woPlanDetailForm.strutsAction.value == '<%=WoPlanDetailAction.WO_PLAN_DETAIL_INPUT%>')
    {
   	 	parent.afterCreate(woPlanDetailForm.elements['woPlanCommonDTO.wkOrId'].value);
    }
    
	if (parent.findGridRow)	parent.findGridRow(woPlanDetailForm.elements['woPlanDetailDTO.wkOrId'].value);
	
	getTopPage().afterSaveAll(currentPageId);
	
	checkWoPlanStatus();
	
	woPlanDetailForm.elements['woPlanDetailDTO.woPlanStatusId'].value = 'PPP';
	woPlanDetailForm.elements['woPlanDetailDTO.woPlanStatusDesc'].value = getSysCodeDesc('WOPLAN_STATUS', 'PPP');

	if(opener)
	{	//요처-처리사항(목록)에서 팝업창을 열었다면 저장시 요청-처리사항(목록) 조회 호출 
		if(opener.currentPageId == "maWoReqResList") opener.goSearch();
		$(".b_confirmwoplan").css("display","none");
	}
 }
 
function setWorkTime(){
	var startDate = woPlanDetailForm.elements['woPlanDetailDTO.startDate'].value;
 	var startTime = woPlanDetailForm.elements['woPlanDetailDTO.startTime'].value;
 	var endDate = woPlanDetailForm.elements['woPlanDetailDTO.endDate'].value;
 	var endTime = woPlanDetailForm.elements['woPlanDetailDTO.endTime'].value;

	woPlanDetailForm.elements['woPlanDetailDTO.workTime'].value = setNumberFormat(getMinInterval(woPlanDetailForm.elements['woPlanDetailDTO.startDate'], woPlanDetailForm.elements['woPlanDetailDTO.startTime'], woPlanDetailForm.elements['woPlanDetailDTO.endDate'],woPlanDetailForm.elements['woPlanDetailDTO.endTime']));
}

/**
 * 승인요청
 */
function goApproval()
{
	if(checkIsUpdate(document)){
	    alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
	}else{
	    var wkOrId = woPlanDetailForm.elements['woPlanCommonDTO.wkOrId'].value;
	    var description = woPlanDetailForm.elements['woPlanDetailDTO.wkOrDesc'].value;
	    
	    //APPR_TYPE:결재구분상태[예:일일작업:WODAY] , OBJECT_ID, 
	    appAction(wkOrId, "WOPLAN", description);
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
	if (parent.findGridRow) parent.findGridRow(woPlanDetailForm.elements['woPlanCommonDTO.wkOrId'].value);
    
	checkWoPlanStatus();
	
	goClose('woPlanDetail');
}

/*
    작업계획확정하기
*/
function goConfirmwoplan()
{
	if(checkIsUpdate(document)){
		alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
	}else{
		getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG045'/>", function(result){
			if(result){
				//================================
				// 필수 항목 체크한다.
				//================================
				if(checkValidation()) return;
				//================================
				// 필수 확정항목 체크한다.
				//================================
				if(checkConfirmValidation()) return;
				
				if(checkRequireValue('woPlanDetailDTO.endDate','<bean:message key="LABEL.woDate"/>')) return;
		
				checkPoint();
			}
		});
	}
}


/**
 * 완료후 호출
 */
function afterConfirm(ajaxXmlDoc)
{
	alertMessage1("<bean:message key='MESSAGE.MSG0015'/>");
	   
	//수리작업계획상태 = PPC : 작업계획완료
	woPlanDetailForm.elements['woPlanDetailDTO.woPlanStatusId'].value = "PPC";
	woPlanDetailForm.elements['woPlanDetailDTO.woPlanStatusDesc'].value = getSysCodeDesc('WOPLAN_STATUS', 'PPC');
	 
	//확정자명 = 로그인 유저
	woPlanDetailForm.elements['woPlanDetailDTO.closeBy'].value = "<%=user.getEmpName()%>";
	
	// 수리작업계획(목록) 상태 변경 
	if (parent.findGridRow)	
		parent.findGridRow(woPlanDetailForm.elements['woPlanDetailDTO.wkOrId'].value);
	
	checkWoPlanStatus();
}

/**
 * 작업완료 취소
 */
function goReverse(){
	getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0231'/>", function(result){
		 if(result){
		    var url = contextPath + "/woPlanDetail.do";
		    woPlanDetailForm.strutsAction.value = '<%=WoPlanDetailAction.WO_PLAN_DETAIL_COMPLETE_CANCEL%>';
		    XMLHttpPost(url, FormQueryString(woPlanDetailForm), 'afterReverse');
		 }
	});
}

/**
 * 완료 취소후 호출
 */
function afterReverse(ajaxXmlDoc)
{
	isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
	if(isValid != '0') {
		alertMessage1("<bean:message key='MESSAGE.MSG0178'/>");
		return ;
    } else {
    	alertMessage1("<bean:message key='MESSAGE.CMSG034'/>");
    	// 수리작업계획상태 = PPP : 작업계획중
    	woPlanDetailForm.elements['woPlanDetailDTO.woPlanStatusId'].value = "PPP";
    	woPlanDetailForm.elements['woPlanDetailDTO.woPlanStatusDesc'].value = getSysCodeDesc('WOPLAN_STATUS', 'PPP');
    	
    	// 수리작업계획(목록) 상태 변경 
    	if (parent.findGridRow)	
    		parent.findGridRow(woPlanDetailForm.elements['woPlanDetailDTO.wkOrId'].value);
    }
	
	checkWoPlanStatus();
}

/* audit Trail */
function goAudtrailLink()
{
	var objectId = woPlanDetailForm.elements['woPlanDetailDTO.wkOrId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}
/**
 * PPW - 작업계획중 / PPWDA - 작업계획결재반려
 */
function checkWoPlanStatus(){
	
	if(woPlanDetailForm.elements['woPlanDetailDTO.woPlanStatusId'].value != 'PPW' 
		&& woPlanDetailForm.elements['woPlanDetailDTO.woPlanStatusId'].value != 'PPWDA' 
		&& woPlanDetailForm.elements['woPlanDetailDTO.woPlanStatusId'].value != 'PPP'
		&& woPlanDetailForm.elements['woPlanDetailDTO.woPlanStatusId'].value != 'PPWRA') {
		
		showBtn("REVERSE");
		hideBtn("CONFIRMWOPLAN");
		hideBtn("APPROVAL");
		
		setDisableAll();
	} else if(woPlanDetailForm.elements['woPlanDetailDTO.woPlanStatusId'].value == 'PPWRA') {
		setDisableAll();
	} else {
		hideBtn("REVERSE");
		showBtn("CONFIRMWOPLAN");
		showBtn("APPROVAL");
		
		setEnableAll();
	}
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/woPlanDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="woPlanCommonDTO.wkOrId" />
	<html:hidden property="woPlanDetailDTO.wkOrId" />
	<html:hidden property="woPlanCommonDTO.woReqResId" />
	<html:hidden property="woPlanDetailDTO.woReqResId" />
	<html:hidden property="woPlanDetailDTO.eqAsmbId" />
	<html:hidden property="woPlanDetailDTO.equipId" />
	<html:hidden property="woPlanDetailDTO.woTypeId" />
	<html:hidden property="woPlanDetailDTO.woPlanStatusId" />
	<html:hidden property="woPlanDetailDTO.pmTypeId" />
	<html:hidden property="woPlanDetailDTO.deptId" />
	<html:hidden property="woPlanDetailDTO.empId" />
	<html:hidden property="woPlanDetailDTO.vendorId" />
	<html:hidden property="woPlanDetailDTO.selfVendorType" />
	<html:hidden property="woPlanDetailDTO.pWkOrId" />  <!-- 상위Work Order ID -->
	<html:hidden property="woPlanDetailDTO.shiftTypeId" />
 	<html:hidden property="woPlanDetailDTO.woGenType" />
 	<html:hidden property="woPlanDetailDTO.eqLocId" />
 	<html:hidden property="woPlanDetailDTO.parentWoId" />
 	<html:hidden property="woPlanDetailDTO.parentWoDesc" />
 	<html:hidden property="woPlanDetailDTO.eqLocId" />
 	<html:hidden property="woPlanDetailDTO.wkCtrId" />
 	<html:hidden property="woPlanDetailDTO.pmCalibStdTpId" />
 	<html:hidden property="woPlanDetailDTO.subEmpId" />
 	<html:hidden property="woPlanDetailDTO.plant" />
 	
 	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
 	<html:hidden property="maDocLibCommonDTO.objectType" />
 	<html:hidden property="maDocLibCommonDTO.securGrade" />
 	<html:hidden property="maDocLibCommonDTO.docCateg" />
 	<html:hidden property="maDocLibCommonDTO.description" />
 	
 	<html:hidden property="woPlanCommonDTO.woReqId" />
 	<html:hidden property="woPlanCommonDTO.selectedWoType" />
 	<html:hidden property="woPlanCommonDTO.selectedPmType" />
 	
 	<html:hidden property="appReqCommonDTO.objectId"/>
    <html:hidden property="appReqCommonDTO.apprType"/>
 
	<div class="article_box">
		<div class="form_box">
			<!-- W/O# -->
			<div class="field">
				<label><bean:message key="LABEL.woNo"/></label>
				<div class="input_read" id="woNoDiv">
					<html:text property="woPlanDetailDTO.woNo" tabindex="10" readonly="true"/>
				</div>
			</div>
			<!-- W/O상태 -->
			<div class="field">
				<label><bean:message key="LABEL.woStatus"/></label>
				<div class="input_read" id="woPlanStatusDescDiv">
					<html:text property="woPlanDetailDTO.woPlanStatusDesc" tabindex="20" />
				</div>
			</div>
			<!-- 작업종류  -->
			<div class="field">
				<label><bean:message key="LABEL.woType"/></label>
				<div class="input_box">
					<html:text property="woPlanDetailDTO.woTypeDesc" tabindex="30" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 작업형태  -->
			<div class="field">
				<label><bean:message key="LABEL.pmType"/></label>
				<div class="input_box">
					<html:text property="woPlanDetailDTO.pmTypeDesc" tabindex="40" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 설비위치 -->
			<div class="field_long">
				<label><bean:message key="LABEL.location"/></label>
				<div class="input_read" id="eqLocDescDiv">
					<html:text property="woPlanDetailDTO.eqLocDesc" tabindex="50" readonly="true"/>
				</div>
			</div>
			<!-- 작업명 -->
			<div class="field_long">
				<label class="check"><bean:message key="LABEL.woName"/></label>
				<div class="input_box">
					<html:text property="woPlanDetailDTO.wkOrDesc" tabindex="60" />
				</div>
			</div>
			<!-- 설비 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.equipment"/></label>
				<div class="input_box" id="equipDiv">
					<html:text property="woPlanDetailDTO.equipDesc" tabindex="70" />
					<p class="open_spop" id="equipPopDiv">
						<a><span>조회</span></a>
					</p>
				</div>
			</div>
			<!-- 부위 -->
			<div class="field">
				<label><bean:message key="LABEL.asmb"/></label>
				<div class="input_box">
					<html:text property="woPlanDetailDTO.eqAsmbDesc" tabindex="80"/>
					<p class="open_spop">
						<a><span>조회</span></a>
					</p>
				</div>
			</div>
			<!-- 작업일자 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.wkorDate"/></label>
				<div class="input_box">
					<html:text property="woPlanDetailDTO.wkorDate" tabindex="90" />
					<p class="open_calendar">
						<span>날짜</span>
					</p>
				</div>
			</div>
			<!-- 공장명 -->
			<div class="field">
				<label><bean:message key="LABEL.plant"/></label>
				<div class="input_box">
                    <html:text property="woPlanDetailDTO.plantDesc" tabindex="100" />
                    <p class="open_spop">
                        <a><span>조회</span></a>
                    </p>
                </div>
			</div>
			<!-- 담당부서 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.manageDept"/></label>
				<div class="input_box">
					<html:text property="woPlanDetailDTO.deptDesc" tabindex="110"/>
					<p class="open_spop">
						<a><span>조회</span></a>
					</p>
				</div>
			</div>
			<!-- 작업그룹 -->
			<div class="field">
				<label><bean:message key="LABEL.wkCtr"/></label>
				<div class="input_box">
					<html:text property="woPlanDetailDTO.wkCtrDesc" tabindex="120"/>
					<p class="open_spop">
						<a><span>조회</span></a>
					</p>
				</div>
			</div>
			<!-- 담당자 -->
			<div class="field">
				<label class="lastcheck"><bean:message key="LABEL.manager"/></label>
				<div class="input_box">
					<html:text property="woPlanDetailDTO.empDesc" tabindex="130"/>
					<p class="open_spop">
						<a><span>조회</span></a>
					</p>
				</div>
			</div>
			<!-- 교대조 -->
			<div class="field">
				<label><bean:message key="LABEL.shiftType"/></label>
				<div class="input_box">
					<html:text property="woPlanDetailDTO.shiftTypeDesc" tabindex="140" />
					<p class="open_spop">
						<a><span>조회</span></a>
					</p>
				</div>
			</div>
			<!-- 부 담당자 -->
			<div class="field">
				<label><bean:message key="LABEL.managerSub"/></label>
				<div class="input_box">
					<html:text property="woPlanDetailDTO.subEmpDesc" tabindex="150"/>
					<p class="open_spop">
						<a><span>조회</span></a>
					</p>
				</div>
			</div>
			<!-- 자가/외주  -->
			<div class="field">
				<label><bean:message key="LABEL.selfVendorType"/></label>
				<div class="input_box">
					<html:text property="woPlanDetailDTO.selfVendorTypeDesc" tabindex="160" />
					<p class="open_spop">
						<a><span>조회</span></a>
					</p>
				</div>
			</div>
			<!-- 작업시작시간 -->
			<div class="field">
				<label class="lastcheck"><bean:message key="LABEL.woFromTime"/></label>
				<div class="datetime_wrap">
					<div class="input_box input_carendar">
						<html:text property="woPlanDetailDTO.startDate"  tabindex="170" />
						<p class="open_calendar">
							<span>날짜</span>
						</p>
					</div>
					<div class="input_box">
						<html:text property="woPlanDetailDTO.startTime"   tabindex="180"/>
						<p class="open_time">
							<span>시간</span>
						</p>
					</div>
				</div>
			</div>
			<!-- 작업종료시간-->
			<div class="field">
				<label class="lastcheck"><bean:message key="LABEL.woToTime"/></label>
				<div class="datetime_wrap">
					<div class="input_box input_carendar">
						<html:text property="woPlanDetailDTO.endDate" tabindex="190" />
						<p class="open_calendar">
							<span>날짜</span>
						</p>
					</div>
					<div class="input_box">
						<html:text property="woPlanDetailDTO.endTime" tabindex="200" />
						<p class="open_time">
							<span>시간</span>
						</p>
					</div>
				</div>
			</div>
			<!-- 소요시간 -->
			<div class="field">
				<label><bean:message key="LABEL.woTimeMin"/></label>
				<div class="input_box">
					<html:text property="woPlanDetailDTO.workTime" tabindex="210" styleClass="num"/>
				</div>
			</div>
			<!-- 거래처 -->
			<div class="field">
				<label><bean:message key="LABEL.vendor"/></label>
				<div class="input_box">
                    <html:text property="woPlanDetailDTO.vendorDesc" tabindex="220" />
                    <p class="open_spop">
                        <a><span>조회</span></a>
                    </p>
                </div>
			</div>
			<!-- 금액 -->
			<div class="field" id="amtDiv">
				<label><bean:message key="LABEL.amt"/></label>
				<div class="input_box">
					<html:text property="woPlanDetailDTO.totAmt" tabindex="230" styleClass="num"/>
				</div>
			</div>
			<!-- 확정자 -->
			<div class="field">
				<label><bean:message key="LABEL.closeBy"/></label>
				<div class="input_read" >
					<html:text property="woPlanDetailDTO.closeBy" tabindex="240" readonly="true" />
				</div>
			</div>
			<!-- 작업상세내용 -->
			<div class="field_long">
				<label><bean:message key="LABEL.woPerform"/></label>
				<div class="input_box">
					<html:textarea property="woPlanDetailDTO.perform" styleClass="ta50" tabindex="250" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
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
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%@ page import="dream.req.work.action.MaWoReqDetailAction"%>
<%@ page import="common.bean.User"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
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
var moDescAc;
var plantAc;
var woReqStatusAc;
var reqEmpAc;
var reqDeptAc;
var eqAsmbAc;

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goUpdate(); 
	}
	
	setTitle("maWoReqDetailDTO.woReqNo", "maWoReqDetailDTO.reqDesc");
	setForUpdate();
	
	//우선순위
    acSysDesc("maWoReqDetailDTO.reqPriorityDesc","maWoReqDetailDTO.reqPriorityId","REQ_PRIORITY",true);
	
	/** 설비위치  */
	eqLocDescAc = new autoC({"maWoReqDetailDTO.reqEqLocDesc":"full_desc"});
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	});
    eqLocDescAc.setAcDConditionMap({
    	"plant" : "maWoReqDetailDTO.plantId"
    });
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setKeyName("maWoReqDetailDTO.reqEqLocId");
    eqLocDescAc.setAcResultMap({
        "maWoReqDetailDTO.reqEqLocId":"eqloc_id"
    });
    eqLocDescAc.init();
    
	equipDescAc = new autoC({"maWoReqDetailDTO.reqEquipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo,
 	   "EQ_STATUS":"R+S"
 	   });
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "maWoReqDetailDTO.reqEqLocId",
    	"plant" : "maWoReqDetailDTO.plantId"
    });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcResultMap({
        "maWoReqDetailDTO.reqEquipId":"equip_id"
        ,"maWoReqDetailDTO.reqEquipNo":"item_no"
        ,"maWoReqDetailDTO.reqEqLocId":"eqloc_id"
        ,"maWoReqDetailDTO.reqEqLocDesc":"eqlocDesc"
        ,"maWoReqDetailDTO.reqEqAsmbId":""
        ,"maWoReqDetailDTO.reqEqAsmbDesc":""
    });
    equipDescAc.setKeyName("maWoReqDetailDTO.reqEquipId"); 
    equipDescAc.init();
    
    /** 처리부서 */
    recDeptAc = new autoC({"maWoReqDetailDTO.recDeptDesc":"description"});
    recDeptAc.setAcConditionMap({
        "comp_no":loginUser.compNo,
        'IS_LOWEST_LVL':"Y"
    });
    recDeptAc.setAcDConditionMap({
    	"plant" : "maWoReqDetailDTO.plantId"
    });
    recDeptAc.setTable("TADEPT");
    recDeptAc.setKeyName("maWoReqDetailDTO.recDeptId");
    recDeptAc.setAcResultMap({
        "maWoReqDetailDTO.recDeptId":"dept_id"
        ,"maWoReqDetailDTO.plantId":"plant_id"
        ,"maWoReqDetailDTO.plantDesc":"plantDesc"
    });
    recDeptAc.init();
    
    /**처리작업그룹  */
    recWkCtrDescAc = new autoC({"maWoReqDetailDTO.recWkCtrDesc":"description"});
    recWkCtrDescAc.setAcConditionMap({
        "comp_no":loginUser.compNo
       });
    recWkCtrDescAc.setTable("TAWKCTR");
    recDeptAc.setKeyName("maWoReqDetailDTO.recWkCtrId");
    recWkCtrDescAc.setAcResultMap({
        "maWoReqDetailDTO.recWkCtrId":"wkctr_id"
    });
    recWkCtrDescAc.init();
    
    /** 요청자 */
    reqEmpAc = new autoC({"maWoReqDetailDTO.reqEmpDesc":"emp_name"});
    reqEmpAc.setAcConditionMap({
        "comp_no":loginUser.compNo,
        "is_join":"Y"
       });
    reqEmpAc.setAcDConditionMap({
    	"plant" : "maWoReqDetailDTO.plantId"
      , "plantDesc":"maWoReqDetailDTO.plantDesc"
      , "dept_id":"maWoReqDetailDTO.reqDeptId"
      , "deptDesc":"maWoReqDetailDTO.reqDeptDesc"
    });
    reqEmpAc.setTable("TAEMP");
    reqEmpAc.setKeyName("maWoReqDetailDTO.reqEmpId");
    reqEmpAc.setAcResultMap({
        "maWoReqDetailDTO.reqEmpId":"emp_id",
        "maWoReqDetailDTO.reqDeptId":"dept_id",
        "maWoReqDetailDTO.reqDeptDesc":"deptDesc"
    });
    reqEmpAc.init();
    
    /** 요청부서 */
    reqDeptAc = new autoC({"maWoReqDetailDTO.reqDeptDesc":"description"});
    reqDeptAc.setAcConditionMap({
        "comp_no":loginUser.compNo
      , "IS_LOWEST_LVL":"Y"
    });
    reqDeptAc.setAcDConditionMap({
    	"plant" : "maWoReqDetailDTO.plantId"
    });
    reqDeptAc.setTable("TADEPT");
    reqDeptAc.setKeyName("maWoReqDetailDTO.reqDeptId");
    reqDeptAc.setAcResultMap({
        "maWoReqDetailDTO.reqDeptId":"dept_id"
      , "maWoReqDetailDTO.plantId":"plant_id"
      , "maWoReqDetailDTO.plantDesc":"plantDesc"
    });
    reqDeptAc.init();
    
    /** 처리담당자 */
    recEmpAc = new autoC({"maWoReqDetailDTO.recEmpName":"emp_name"});
    recEmpAc.setAcConditionMap({
        "comp_no":loginUser.compNo,
        "is_join":"Y"
       });
    recEmpAc.setAcDConditionMap({
    	"plant" : "maWoReqDetailDTO.plantId"
        ,"plantDesc":"maWoReqDetailDTO.plantDesc"
        ,"dept_id":"maWoReqDetailDTO.recDeptId"
        ,"deptDesc":"maWoReqDetailDTO.recDeptDesc"
    });
    recEmpAc.setTable("TAEMP");
    recEmpAc.setKeyName("maWoReqDetailDTO.recEmpId");
    recEmpAc.setAcResultMap({
        "maWoReqDetailDTO.recEmpId":"emp_id",
        "maWoReqDetailDTO.recDeptId":"dept_id",
        "maWoReqDetailDTO.recDeptDesc":"deptDesc"
    });
    recEmpAc.init();
    
    /** 고장현상 */
    moDescAc = new autoC({"maWoReqDetailDTO.moCdDesc":"failureDesc"});
    moDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"fail_type":"MO",
 		"is_use":"Y"
  	   });
    moDescAc.setTable("TAFAILURE");
    moDescAc.setKeyName("maWoReqDetailDTO.moCd");
    moDescAc.setAcResultMap({
        "maWoReqDetailDTO.moCd":"failure_id"
       ,"maWoReqDetailDTO.moDesc":"failureDesc"
    });
    moDescAc.init();

    /** 공장 */
    plantAc = new autoC({"maWoReqDetailDTO.plantDesc":"description"});
    plantAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	, "is_use":"Y"
  	   });
    plantAc.setTable("TAPLANT");
    plantAc.setKeyName("maWoReqDetailDTO.plantId");
    plantAc.setAcResultMap({
        "maWoReqDetailDTO.plantId":"plant"
    });
    plantAc.init();
    
    /** 진행상태  */
    woReqStatusAc = new autoC({"maWoReqDetailDTO.woReqStatusDesc":"description"});
    woReqStatusAc.setTable("TACDSYSD");
    woReqStatusAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	, "list_type":"WOREQ_STATUS"
    	, "is_use":"Y"
    	, "param1":"C"
  	});
    woReqStatusAc.setKeyName("maWoReqDetailDTO.woReqStatusId");
    woReqStatusAc.setAcResultMap({
        "maWoReqDetailDTO.woReqStatusId":"cdsysd_no"
    });
    woReqStatusAc.init();
    
    /** 부위 AC */
    eqAsmbAc = new autoC({"maWoReqDetailDTO.reqEqAsmbDesc":"full_desc"});
    eqAsmbAc.setTable("TAEQASMB");
    eqAsmbAc.setAcConditionMap({
       "comp_no": loginUser.compNo
     , "is_use" : "Y"
    });
    eqAsmbAc.setAcDConditionMap({
       "equip_id": "maWoReqDetailDTO.reqEquipId"
    });
    eqAsmbAc.setKeyName("maWoReqDetailDTO.reqEqAsmbId");
    eqAsmbAc.setAcResultMap({
        "maWoReqDetailDTO.reqEqAsmbId":"eqasmb_id"
    });
    eqAsmbAc.init();
    
    //acSysDesc("maWoReqCommonDTO.filterWoReqStatusDesc","maWoReqCommonDTO.filterWoReqStatusId","WOREQ_STATUS");
    
    /** 작업요청발생구분 */
    acSysDesc("maWoReqDetailDTO.reqChannelDesc", "maWoReqDetailDTO.reqChannelId", "WOREQ_CHANNEL", true);
}
	
function goTabPage(pageId)
{
	var form = document.maWoReqDetailForm;

	if(pageId == "maDocLibList" || pageId == "maWoDocLibList"|| pageId == "maWoReqDocLibList")
	{	
		maWoReqDetailForm.elements['maDocLibCommonDTO.objectId'].value = maWoReqDetailForm.elements['maWoReqCommonDTO.woReqId'].value;
		maWoReqDetailForm.elements['maDocLibCommonDTO.objectType'].value = "WOREQ"; 
		//maWoReqDetailForm.elements['maDocLibCommonDTO.securGrade'].value = "3";  //보안등급-일반
		//maWoReqDetailForm.elements['maDocLibCommonDTO.docCateg'].value = "DOC";  //문서타입-일반
		maWoReqDetailForm.elements['maDocLibCommonDTO.description'].value = 
			maWoReqDetailForm.elements['maWoReqDetailDTO.reqDesc'].value;  //제목
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else if(pageId == "maAppPrcList" || pageId == "appPrcList") {
		maWoReqDetailForm.elements['appReqCommonDTO.objectId'].value = maWoReqDetailForm.elements['maWoReqCommonDTO.woReqId'].value;
		maWoReqDetailForm.elements['appReqCommonDTO.apprType'].value = "REQWORK";
		goCommonTabPage(form, '' , pageId);
	}
	else
	goCommonTabPage(form, '' , pageId);  
}

function goInput()
{
	sequenceNextVal('SQAWOREQ_ID');
	
	setState();
	
	maWoReqDetailForm.elements['maWoReqDetailDTO.woReqStatusId'].value   = 'REQ';
	maWoReqDetailForm.elements['maWoReqDetailDTO.woReqStatusDesc'].value = 'REQ';
	valSysDir('maWoReqDetailDTO.woReqStatusId', 'maWoReqDetailDTO.woReqStatusDesc', 'WOREQ_STATUS', true);

	maWoReqDetailForm.elements['maWoReqDetailDTO.reqDate'].value   = getToday();
	maWoReqDetailForm.elements['maWoReqDetailDTO.reqEmpId'].value   = loginUser.empId;
	maWoReqDetailForm.elements['maWoReqDetailDTO.reqEmpDesc'].value   = loginUser.empName+"/"+loginUser.deptDesc;

	maWoReqDetailForm.elements['maWoReqDetailDTO.recEmpId'].value   = loginUser.empId;
	maWoReqDetailForm.elements['maWoReqDetailDTO.recEmpName'].value   = loginUser.empName;
	
	// 요청자 전화번호
	maWoReqDetailForm.elements['maWoReqDetailDTO.reqPhone'].value   = loginUser.mPhone;
	// 요청자 이메일
	maWoReqDetailForm.elements['maWoReqDetailDTO.reqEmail'].value   = loginUser.eMail;

	if(loginUser.filterDeptId!='null'){
		maWoReqDetailForm.elements['maWoReqDetailDTO.recDeptId'].value   = loginUser.filterDeptId;
		maWoReqDetailForm.elements['maWoReqDetailDTO.recDeptDesc'].value   = loginUser.filterDeptDesc;
    } 
    
	//공장명
    if(loginUser.plant!='null'){
    	maWoReqDetailForm.elements['maWoReqDetailDTO.plantId'].value = loginUser.plant;
    	maWoReqDetailForm.elements['maWoReqDetailDTO.plantDesc'].value = loginUser.plantDesc;
    }
    // 작업발생요청구분
    maWoReqDetailForm.elements['maWoReqDetailDTO.reqChannelId'].value = "WEB"
    maWoReqDetailForm.elements['maWoReqDetailDTO.reqChannelDesc'].value = "WEB"
}

function setSequenceVal(sequenceVal)
{
	maWoReqDetailForm.elements['maWoReqCommonDTO.woReqId'].value = sequenceVal;
	maWoReqDetailForm.elements['maWoReqDetailDTO.woReqId'].value = sequenceVal;
	maWoReqDetailForm.elements['maWoReqDetailDTO.woReqNo'].value = sequenceVal;
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
    
	//strutsAction 셋팅.
	if(ckCreate(currentPageId)) maWoReqDetailForm.strutsAction.value = '<%=MaWoReqDetailAction.DETAIL_INSERT%>';
	else maWoReqDetailForm.strutsAction.value = '<%=MaWoReqDetailAction.DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/maWoReqDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maWoReqDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
function afterSave(ajaxXmlDoc)
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	if (parent.findGridRow)	parent.findGridRow(maWoReqDetailForm.elements['maWoReqDetailDTO.woReqId'].value);
	
	if(typeof searchPage("workAlarmReqList").afterCreate == "function" && parent.currentPageId == 'workAlarmReqList' 
			&& maWoReqDetailForm.strutsAction.value == "<%=MaWoReqDetailAction.DETAIL_INSERT%>"){
		searchPage("workAlarmReqList").afterCreate(maWoReqDetailForm.elements['maWoReqDetailDTO.woReqId'].value);	
	}
	
	getTopPage().afterSaveAll(currentPageId);
	
	setState();
}

function changStatus(_reqStatus){
	maWoReqDetailForm.elements['maWoReqDetailDTO.woReqStatusId'].value   = _reqStatus;
	maWoReqDetailForm.elements['maWoReqDetailDTO.woReqStatusDesc'].value = _reqStatus;
	valSysDir('maWoReqDetailDTO.woReqStatusId', 'maWoReqDetailDTO.woReqStatusDesc', 'WOREQ_STATUS', true);
	
	if (parent.findGridRow)	parent.findGridRow(maWoReqDetailForm.elements['maWoReqDetailDTO.woReqId'].value);
}

/*
 * 투자결과, 작업결과 상태체크
 */
function checkStatus(_woReqResId) {
	maWoReqDetailForm.elements['maWoReqCommonDTO.woReqResId'].value = _woReqResId;
	maWoReqDetailForm.strutsAction.value = '<%=MaWoReqDetailAction.CHK_WO_STATUS%>';
	var actionUrl = contextPath + "/maWoReqDetail.do";
	
	XMLHttpPostVal(actionUrl, FormQueryString(maWoReqDetailForm), "afterCheckStatus");
}

function afterCheckStatus(ajaxXmlDoc){
	var isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
	// 변경된 상태 수정
    changStatus(isValid);
}

/**
 * 처리부서여부 확인
 */
function checkValidRecDept(_callback) {
	//처리담당자 로그인 사용자로 자동입력
	if("" == maWoReqDetailForm.elements['maWoReqDetailDTO.recEmpId'].value)
	{
		maWoReqDetailForm.elements['maWoReqDetailDTO.recEmpId'].value   = loginUser.empId;
		maWoReqDetailForm.elements['maWoReqDetailDTO.recEmpName'].value = loginUser.empName;
		
		//처리부서 로그인 사용자 부서로 자동입력
		maWoReqDetailForm.elements['maWoReqDetailDTO.recDeptId'].value   = loginUser.deptId;
		maWoReqDetailForm.elements['maWoReqDetailDTO.recDeptDesc'].value = loginUser.deptDesc;
	}
	
	maWoReqDetailForm.strutsAction.value = '<%=MaWoReqDetailAction.REC_CHECK_VALID_DEPT%>';
	var actionUrl = contextPath + "/maWoReqDetail.do";
	XMLHttpPostVal(actionUrl, FormQueryString(maWoReqDetailForm), _callback);
}

/**
 * 작업불가
 */
function goCantwork(){
	if(checkIsUpdate(document)){
	    alertMessage1("<bean:message key='MESSAGE.MSG0036'/>");
	}
	else {
		getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0196'/>", function(result){
			if(result){
				checkValidRecDept('setCantwork');
			}
		});
	}
}
/**
 * 작업불가 처리
 */
function setCantwork(ajaxXmlDoc){
	var isValid = 'N';
    isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
    
    if(isValid == 'N')
    {
    	closeModal();
        
        // 처리부서가 아닙니다.
        alertMessage1("<bean:message key='MESSAGE.MSG0232' /> ");
    }
    else
    {
		maWoReqDetailForm.strutsAction.value = '<%=MaWoReqDetailAction.REC_INC_STATUS_UPDATE%>';
		var actionUrl = contextPath + "/maWoReqDetail.do";
		XMLHttpPost(actionUrl, FormQueryString(maWoReqDetailForm), 'afterCantwork');
    }
}
/**
 * 작업불가 후 호출
 */
function afterCantwork(ajaxXmlDoc)
{
 	//=====================================
 	if (!checkHttpXml(ajaxXmlDoc)) return;
 	//=====================================
 	if (parent.findGridRow)	parent.findGridRow(maWoReqDetailForm.elements['maWoReqDetailDTO.woReqId'].value);
	
	if(typeof searchPage("workAlarmReqList").afterCreate == "function" && parent.currentPageId == 'workAlarmReqList'){
		searchPage("workAlarmReqList").afterCreate(maWoReqDetailForm.elements['maWoReqDetailDTO.woReqId'].value);	
	}
	
 	maWoReqDetailForm.elements['maWoReqDetailDTO.woReqStatusId'].value = "INC";
 	valSysDirCode('maWoReqDetailDTO.woReqStatusId', 'maWoReqDetailDTO.woReqStatusDesc', 'WOREQ_STATUS', true);
 	getTopPage().afterSaveAll(currentPageId);
 	//작업불가처리 되었습니다.
	alertMessage1("<bean:message key='MESSAGE.MSG0244'/>");
 	setState();
}

/**
 * 접수
 */
function goReceive()
{
	if(checkIsUpdate(document)){
        alertMessage1("<bean:message key='MESSAGE.MSG0036'/>");
    }
	else {
		checkValidRecDept('setReceive');
	}
}
/**
 * 접수 처리
 */
function setReceive(ajaxXmlDoc){
	var isValid = 'N';
    isValid = parseXmlDoc(ajaxXmlDoc, 'DESC');
    
    if(isValid == 'N')
    {
    	closeModal();
        
        // 처리부서가 아닙니다.
        alertMessage1("<bean:message key='MESSAGE.MSG0232' /> ");
    }
    else
    {
    	maWoReqDetailForm.strutsAction.value = '<%=MaWoReqDetailAction.REC_STATUS_UPDATE%>';

		var actionUrl = contextPath + "/maWoReqDetail.do";
		XMLHttpPost(actionUrl, FormQueryString(maWoReqDetailForm), 'afterReceive');
    }
}
/**
 * 접수후 호출
 */
function afterReceive(ajaxXmlDoc)
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	if (parent.findGridRow)	parent.findGridRow(maWoReqDetailForm.elements['maWoReqDetailDTO.woReqId'].value);

	if(typeof searchPage("workAlarmReqList").afterCreate == "function" && parent.currentPageId == 'workAlarmReqList'){
		searchPage("workAlarmReqList").afterCreate(maWoReqDetailForm.elements['maWoReqDetailDTO.woReqId'].value);	
	}

	maWoReqDetailForm.elements['maWoReqDetailDTO.woReqStatusId'].value = "REC";
	valSysDirCode('maWoReqDetailDTO.woReqStatusId', 'maWoReqDetailDTO.woReqStatusDesc', 'WOREQ_STATUS', true);
	getTopPage().afterSaveAll(currentPageId);
	//접수되었습니다.
	alertMessage1("<bean:message key='MESSAGE.MSG0243'/>");
	setState();
}

/*
 * 접수불가 취소
 */
function goCantworkcancel() {
	if(checkIsUpdate(document)){
	    alertMessage1("<bean:message key='MESSAGE.MSG0036'/>");
	} else {
		getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG1003'/>", function(result){
			if(result) {
				maWoReqDetailForm.strutsAction.value = '<%=MaWoReqDetailAction.REC_REQ_STATUS_UPDATE%>';
				var actionUrl = contextPath + "/maWoReqDetail.do";
				XMLHttpPostVal(actionUrl, FormQueryString(maWoReqDetailForm), "afterCantWorkCancel");
			}
		});
	}
}

/**
 * 접수불가취소 후 호출
 */
function afterCantWorkCancel(ajaxXmlDoc)
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	if (parent.findGridRow)	parent.findGridRow(maWoReqDetailForm.elements['maWoReqDetailDTO.woReqId'].value);

	if(typeof searchPage("workAlarmReqList").afterCreate == "function" && parent.currentPageId == 'workAlarmReqList'){
		searchPage("workAlarmReqList").afterCreate(maWoReqDetailForm.elements['maWoReqDetailDTO.woReqId'].value);	
	}

	maWoReqDetailForm.elements['maWoReqDetailDTO.woReqStatusId'].value = "REQ";
	valSysDirCode('maWoReqDetailDTO.woReqStatusId', 'maWoReqDetailDTO.woReqStatusDesc', 'WOREQ_STATUS', true);
	getTopPage().afterSaveAll(currentPageId);
	
	//접수되었습니다.
	alertMessage1("<bean:message key='MESSAGE.MSG1004'/>");
	setState();
}

/**
 * 상태 확인 후 버튼 숨김 및 column disable
 */
function setState(){
	setEnableAll();
	
	if("REV"==maWoReqDetailForm.elements['maWoReqDetailDTO.woReqStatusId'].value){
		$(".b_receive").show();
		$(".b_cantwork").show();
		hideBtn("CANTWORKCANCEL");
	}
	else if("REQ"== maWoReqDetailForm.elements['maWoReqDetailDTO.woReqStatusId'].value)
	{
		showBtn("RECEIVE");
		showBtn("CANTWORK");
		hideBtn("CANTWORKCANCEL");
	} else if("INC" == maWoReqDetailForm.elements['maWoReqDetailDTO.woReqStatusId'].value) {
		setDisableAll();
		hideBtn("RECEIVE");
		hideBtn("CANTWORK");
		showBtn("CANTWORKCANCEL");
	} else {
		hideBtn("RECEIVE");
		hideBtn("CANTWORK");
		hideBtn("CANTWORKCANCEL");
	}
}

function goAudtrailLink()
{
	var objectId = maWoReqDetailForm.elements['maWoReqDetailDTO.woReqId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}


/*
 * 의뢰서 출력
 */
function goReqpdfLink()
{
	var woReqId = maWoReqDetailForm.elements['maWoReqDetailDTO.woReqId'].value;
	
	reportCall('maWoReqDetail','maWoReqDetail', "<%=user.getCompNo()%>", "<%=user.getUserId()%>", "<%=user.getLangId()%>",woReqId);
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maWoReqDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maWoReqCommonDTO.woReqId" />
	<html:hidden property="maWoReqCommonDTO.woReqResId" />
	<html:hidden property="maWoReqDetailDTO.woReqId" />
	<html:hidden property="maWoReqDetailDTO.woReqStatusId" />
	<html:hidden property="maWoReqDetailDTO.reqDeptId" />
	<html:hidden property="maWoReqDetailDTO.reqEmpId" />
	<html:hidden property="maWoReqDetailDTO.reqEqLocId" />
	<html:hidden property="maWoReqDetailDTO.reqEquipId" />
 	<html:hidden property="maWoReqDetailDTO.recDeptId" />
 	<html:hidden property="maWoReqDetailDTO.recWkCtrId" />
 	<html:hidden property="maWoReqDetailDTO.recEmpId" />
 	<html:hidden property="maWoReqDetailDTO.reqPriorityId" />
 	<html:hidden property="maWoReqDetailDTO.eqClassId" />
	<html:hidden property="maWoReqDetailDTO.moCd"/>
	<html:hidden property="maWoReqDetailDTO.plantId"/>
	<html:hidden property="maWoReqDetailDTO.alarmListId"/>
	<html:hidden property="maWoReqDetailDTO.reqChannelId"/>
	<html:hidden property="maWoReqDetailDTO.reqEqAsmbId"/>
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
					<html:text property="maWoReqDetailDTO.woReqNo"/>
				</div>
			</div>
			<!-- 진행상태 -->
			<div class="field">
				<label><bean:message key="LABEL.proStatus"/></label>
				<div class="input_read">
					<html:text property="maWoReqDetailDTO.woReqStatusDesc"/>
					<p class="open_spop"><a><span>조회</span>	</a></p>
				</div>
			</div>
			<!-- 요청일자 -->
			<div class="field">
				<label><bean:message key="LABEL.appReqDate"/></label>
				<div class="input_read">
					<html:text property="maWoReqDetailDTO.reqDate"/>
					<p class="open_calendar">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
				</div>
			</div>
			<!-- 요청부서 -->
            <div class="field">
                <label>요청부서</label>
                <div class="input_box">
                    <html:text property="maWoReqDetailDTO.reqDeptDesc" tabindex="60" />
                    <p class="open_spop">
                        <a><span>조회</span></a>
                    </p>
                </div>
            </div>
			<!-- 요청자 -->
			<div class="field">
				<label>요청자</label>
				<div class="input_box">
					<html:text property="maWoReqDetailDTO.reqEmpDesc" tabindex="60"/>
					<p class="open_spop">
                        <a><span>조회</span></a>
                    </p>
				</div>
			</div>
			<!-- 요청자 전화번호-->
			<div class="field">
				<label><bean:message key="LABEL.woReqPhone"/></label>
				<div class="input_read">
					<html:text property="maWoReqDetailDTO.reqPhone" tabindex="10" readonly="true"/>
				</div>
			</div>
			<!-- 요청자 이메일-->
			<div class="field">
				<label><bean:message key="LABEL.woReqEmail"/></label>
				<div class="input_read">
					<html:text property="maWoReqDetailDTO.reqEmail" tabindex="20" readonly="true"/>
				</div>
			</div>
			<!-- 요청타입 -->
			<div class="field">
				<label><bean:message key="LABEL.reqType"/></label>
				<div class="input_read">
					<html:text property="maWoReqDetailDTO.eqClassDesc" tabindex="25" readonly="true"/>
				</div>
			</div>
			<!-- 위치 -->
			<div class="field">
				<label><bean:message key="LABEL.location"/></label>
				<div class="input_box">
					<html:text property="maWoReqDetailDTO.reqEqLocDesc" tabindex="30"/>
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
					<html:text property="maWoReqDetailDTO.reqEquipDesc" tabindex="40"/>
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 설비코드 -->
			<div class="field">
				<label><bean:message key="LABEL.equipNo"/></label>
				<div class="input_read">
					<html:text property="maWoReqDetailDTO.reqEquipNo" tabindex="45" readonly="true"/>
				</div>
			</div>
			<!-- 작업명 -->
			<div class="field_long">
				<label class="check"><bean:message key="LABEL.title"/></label>
				<div class="input_box">
					<html:text property="maWoReqDetailDTO.reqDesc" tabindex="50"/>
				</div>
			</div>
			
			
			<!-- 처리부서 -->
            <div class="field">
                <label><bean:message key="LABEL.recReqDept"/></label>
                <div class="input_box">
                    <html:text property="maWoReqDetailDTO.recDeptDesc" tabindex="60" />
                    <p class="open_spop">
                        <a><span>조회</span></a>
                    </p>
                </div>
            </div>
            
            <!-- 작업그룹 -->
            <div class="field">
                <label><bean:message key="LABEL.recReqWkCtr"/></label>
                <div class="input_box">
                    <html:text property="maWoReqDetailDTO.recWkCtrDesc" tabindex="70"/>
                    <p class="open_spop">
                        <a><span>조회</span></a>
                    </p>
                </div>
            </div>
            
            <!-- 처리담당자 -->
            <div class="field">
                <label><bean:message key="LABEL.recReqEmp"/></label>
                <div class="input_box">
                    <html:text property="maWoReqDetailDTO.recEmpName" tabindex="80"/>
                    <p class="open_spop">
                        <a><span>조회</span></a>
                    </p>
                </div>
            </div>
            <!-- 고장현상 -->
			<div class="field">
				<label><bean:message key="LABEL.moCd"/></label>
				<div class="input_box">
					<html:text property="maWoReqDetailDTO.moCdDesc" tabindex="120"/>
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
					<html:text property="maWoReqDetailDTO.moDesc" tabindex="125" />
				</div>
			</div>
			<!-- 처리요청일 -->
			<div class="field">
				<label><bean:message key="LABEL.reqComDate"/></label>
				<div class="input_read">
					<html:text property="maWoReqDetailDTO.reqComDate" tabindex="90"/>
					<p class="open_calendar"><span>날짜</span></p>
				</div>
			</div>
            <!-- 우선순위 -->
            <div class="field">
                <label><bean:message key="LABEL.reqPriority"/></label>
                <div class="input_read">
                    <html:text property="maWoReqDetailDTO.reqPriorityDesc" tabindex="100"/>
					<p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
            <!-- 공장(Plant) -->
			<div class="field">
				<label><bean:message key="LABEL.plant"/></label>
				<div class="input_box">
					<html:text property="maWoReqDetailDTO.plantDesc" tabindex="110"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
            
			<!-- 요청내용 -->
			<div class="field_long">
				<label><bean:message key="LABEL.request"/></label>
				<div class="input_read">
					<html:textarea property="maWoReqDetailDTO.reqRequest" styleClass="ta150" tabindex="90"/>
				</div>
			</div>
			<!-- 검토내용 -->
			<div class="field_long">
				<label><bean:message key="LABEL.reviewDesc"/></label>
				<div class="input_box">
					<html:textarea property="maWoReqDetailDTO.review" styleClass="ta150" tabindex="999"/>
				</div>
			</div>
			<!-- 고장발생일자 -->
			<div class="field">
				<label><bean:message key="LABEL.EQDNDATE"/></label>
				<div class="datetime_wrap">
					<div class="input_box input_carendar">
						<html:text property="maWoReqDetailDTO.eqDnDate" tabindex="120" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box">
						<html:text property="maWoReqDetailDTO.eqDnTime" tabindex="130" />
						<p class="open_time_second"><span>시간</span></p>
					</div>
				</div>
			</div>
			<!-- 작업요청발생구분 -->
			<div class="field">
				<label>작업요청발생구분</label>
				<div class="input_box">
					<html:text property="maWoReqDetailDTO.reqChannelDesc" tabindex="330"/>
				</div>
			</div>
			<!-- 부위 -->
			<div class="field">
				<label><bean:message key="LABEL.asmb"/></label>
				<div class="input_box">
					<html:text property="maWoReqDetailDTO.reqEqAsmbDesc" tabindex="340"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<c:set var="filePath" value="enhance/${compName}/req/work/maWoReqDetail_${compNo}.jsp" />
			<c:if test="${udf:isExist(filePath)}">
				<c:import charEncoding="UTF-8" url="/enhance/${compName}/req/work/maWoReqDetail_${compNo}.jsp"></c:import>
			</c:if>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
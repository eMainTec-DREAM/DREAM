<%--===========================================================================
에너지측정주기 - 상세
author  js.lee
version $Id: workPmListUInsDetail.jsp,v 1.0 2015/12/04 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.work.pm.list.action.MaPmMstrDetailAction"%>
<%@ page import="common.bean.MwareConfig"%>
<%
	String isUsePmRevision = MwareConfig.getIsUsePmRevision();
%>
<html:html>
<head>
<!-- 에너지측정주기설정 -->
<title><bean:message key='PAGE.workPmListUInsDetail' />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">

/** 자동완성 변수  */
var plantAc;
var equipDescAc;
var wrkCalAc;
var deptAc;
var wkCtrDescAc;
var mainMngAc;

var chkPmNo = '0';

function loadPage() 
{	
	
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goUpdate();
	}
	
	setTitle("maPmMstrDetailDTO.pmNo","maPmMstrDetailDTO.description");
	
	setForUpdate();
	
    /** 공장 */
    plantAc = new autoC({"maPmMstrDetailDTO.plantDesc":"description"});
    plantAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	, "is_use":"Y"
  	   });
    plantAc.setTable("TAPLANT");
    plantAc.setKeyName("maPmMstrDetailDTO.plantId");
    plantAc.setAcResultMap({
        "maPmMstrDetailDTO.plantId":"plant"
    });
    plantAc.init();
    
    /** 설비  */
    equipDescAc = new autoC({"maPmMstrDetailDTO.equipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "a.comp_no":loginUser.compNo,
 	   "CUSTOM":"EQ_STATUS IN('R','S')"
 	   });
    equipDescAc.setKeyName("maPmMstrDetailDTO.equipId");
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcResultMap({
        "maPmMstrDetailDTO.equipId":"equip_id"
    });
    equipDescAc.setAcDConditionMap({
    	"plant" : "maPmMstrDetailDTO.plantId"
    });
    equipDescAc.init();
    
    /** 근무달력  */
	wrkCalAc = new autoC({"maPmMstrDetailDTO.wrkcalListDesc":"description"});
	wrkCalAc.setAcConditionMap({
		"comp_no":loginUser.compNo
	  });
	wrkCalAc.setTable("TAWRKCALLIST");
	wrkCalAc.setKeyName("maPmMstrDetailDTO.wrkcalListId");
	wrkCalAc.setAcResultMap({
	    "maPmMstrDetailDTO.wrkcalListId":"wrkcallist_id"
	});
	wrkCalAc.init();
	
    /** 작업부서 */
    deptAc = new autoC({"maPmMstrDetailDTO.deptDesc":"description"});
    deptAc.setAcDisplay("DESCRIPTION");
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setKeyName("maPmMstrDetailDTO.deptId");
    deptAc.setAcResultMap({
        "maPmMstrDetailDTO.deptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "maPmMstrDetailDTO.plantId"
    });
    deptAc.init();
    
    /** 작업부서 */
	wkCtrDescAc = new autoC({"maPmMstrDetailDTO.wkCtrDesc":"description"});
	wkCtrDescAc.setAcDisplay("DESCRIPTION");
	wkCtrDescAc.setAcConditionMap({
		"comp_no":loginUser.compNo
	  });
	wkCtrDescAc.setTable("TAWKCTR");
	wkCtrDescAc.setKeyName("maPmMstrDetailDTO.wkCtrId");
	wkCtrDescAc.setAcResultMap({
	    "maPmMstrDetailDTO.wkCtrId":"wkctr_id"
	});
	wkCtrDescAc.init();
	
    /** 담당자 */
    mainMngAc = new autoC({"maPmMstrDetailDTO.empName":"emp_name"});
    mainMngAc.setAcDisplay("EMP_NAME");
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
  	   });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setKeyName("maPmMstrDetailDTO.empId");
    mainMngAc.setAcResultMap({
        "maPmMstrDetailDTO.empId":"emp_id"
    });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maPmMstrDetailDTO.deptId"
    });
    mainMngAc.init();
    
    acSysDesc("maPmMstrDetailDTO.isActiveDesc","maPmMstrDetailDTO.isActive","IS_USE",true);
	acSysDesc("maPmMstrDetailDTO.pmCategDesc","maPmMstrDetailDTO.pmCateg","PM_CATEG",true);
	acSysDesc("maPmMstrDetailDTO.scheduleTypeDesc","maPmMstrDetailDTO.scheduleType","SCHEDULE_TYPE",true);
	acSysDesc("maPmMstrDetailDTO.periodTypeDesc","maPmMstrDetailDTO.periodType","PERIOD_TYPE",true);	
	
	// 제/개정 화면제어
 	revDisplayCtrl(M$('maPmMstrDetailDTO.revisionStatusId').value,M$('maPmMstrDetailDTO.isLastVersion').value,"PM");
}

function goUpdate()
{
	// 설비 등록은 가능하지만 수정은 불가능
/*     setDisable(document.getElementById("equipDescDiv"));   */  
	if (maPmMstrDetailForm.elements['maPmMstrDetailDTO.revisionStatusId'].value != "W")  setReadOnly("maPmMstrDetailDTO.equipDesc");

}

function goInput()
{
	sequenceNextVal('SQAPM_ID');
	
	// 사용량 세팅 (PMU는 T가 default)
	maPmMstrDetailForm.elements['maPmMstrDetailDTO.scheduleType'].value = "T"; 
	
	//시행여부 세팅
	maPmMstrDetailForm.elements['maPmMstrDetailDTO.isActive'].value = 'Y';
	maPmMstrDetailForm.elements['maPmMstrDetailDTO.isActiveDesc'].value = 'Y';
	
	// 며칠전 일정생성 7 세팅
	maPmMstrDetailForm.elements['maPmMstrDetailDTO.woResBef'].value = '7';
	
	// 1일 작업횟수
	maPmMstrDetailForm.elements['maPmMstrDetailDTO.workNumber'].value = '1';
	
	//작업종류
	var selectedWoType = maPmMstrDetailForm.elements['maPmMstrCommonDTO.selectedWoType'].value;
	maPmMstrDetailForm.elements['maPmMstrDetailDTO.woTypeDesc'].value = selectedWoType;
	maPmMstrDetailForm.elements['maPmMstrDetailDTO.woType'].value = selectedWoType;
	valSysDir('maPmMstrDetailDTO.woType', 'maPmMstrDetailDTO.woTypeDesc', 'WO_TYPE', true);
	
	//작업형태
	var selectedPmType = maPmMstrDetailForm.elements['maPmMstrCommonDTO.selectedPmType'].value;
	maPmMstrDetailForm.elements['maPmMstrDetailDTO.pmTypeDesc'].value = selectedPmType;
	maPmMstrDetailForm.elements['maPmMstrDetailDTO.pmType'].value = selectedPmType;
	valSysDir('maPmMstrDetailDTO.pmType', 'maPmMstrDetailDTO.pmTypeDesc', selectedWoType+'_TYPE', true);
	
	// 공장
	if(loginUser.plant != '' && null != loginUser.plant){
		maPmMstrDetailForm.elements['maPmMstrDetailDTO.plantId'].value    = loginUser.plant;
		maPmMstrDetailForm.elements['maPmMstrDetailDTO.plantDesc'].value  = loginUser.plantDesc;
	}
	// 부서
	if(loginUser.deptId != '' && null != loginUser.deptId){
		maPmMstrDetailForm.elements['maPmMstrDetailDTO.deptId'].value    = loginUser.deptId;
		maPmMstrDetailForm.elements['maPmMstrDetailDTO.deptDesc'].value  = loginUser.deptDesc;
	}
	// 작업그룹
	if(loginUser.wkctrId != '' && null != loginUser.wkctrId){
		maPmMstrDetailForm.elements['maPmMstrDetailDTO.wkctrId'].value    = loginUser.wkctrId;
		maPmMstrDetailForm.elements['maPmMstrDetailDTO.wkctrDesc'].value  = loginUser.wkctrDesc;
	}
	
	maPmMstrDetailForm.elements['maPmMstrDetailDTO.isLastVersion'].value = "Y";
	valSysDir('maPmMstrDetailDTO.isLastVersion', 'maPmMstrDetailDTO.isLastVersion', 'IS_USE', true);
	
	//제개정 사용여부
	if("<%=isUsePmRevision%>"=="N"){
		maPmMstrDetailForm.elements['maPmMstrDetailDTO.revisionStatusId'].value = "C";
		maPmMstrDetailForm.elements['maPmMstrDetailDTO.revisionStatusDesc'].value = "C";
		valSysDir('maPmMstrDetailDTO.revisionStatusId', 'maPmMstrDetailDTO.revisionStatusDesc', 'REVISION_STATUS', true);
	}
	
	
    
}

function setSequenceVal(sequenceVal)
{
	maPmMstrDetailForm.elements['maPmMstrDetailDTO.pmNo'].value = sequenceVal;
	maPmMstrDetailForm.elements['maPmMstrDetailDTO.pmId'].value = sequenceVal;
	maPmMstrDetailForm.elements['maPmMstrDetailDTO.oldPmNo'].value = sequenceVal;
	maPmMstrDetailForm.elements['maPmMstrCommonDTO.pmId'].value = sequenceVal;

}

function goOpen(pageId)
{
	goTabPage(pageId);
}

function goTabPage(pageId)
{
	var form = document.maPmMstrDetailForm;
	
	if(pageId == "maAppPrcList" || pageId == "appPrcList") {
		maPmMstrDetailForm.elements['appReqCommonDTO.objectId'].value = maPmMstrDetailForm.elements['maPmMstrDetailDTO.pmId'].value;
		maPmMstrDetailForm.elements['appReqCommonDTO.apprType'].value = "PMINSREV";
		goCommonTabPage(form, '' , pageId);
	}else{
		goCommonTabPage(form, '' , pageId);
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
	
	var pmNo = maPmMstrDetailForm.elements['maPmMstrDetailDTO.pmNo'].value;
	var oldPmNo = maPmMstrDetailForm.elements['maPmMstrDetailDTO.oldPmNo'].value;
	
	// 주기#를 수정했을시에 중복체크
	if (pmNo != oldPmNo && pmNo != '') {
		checkPmNo();
		if (chkPmNo != '0') {
			chkPmNo = '0';
			closeModal();
			return;
		}
	}
	
	//strutsAction 셋팅.
	if(ckCreate(currentPageId)) {
		maPmMstrDetailForm.strutsAction.value = '<%=MaPmMstrDetailAction.PM_MSTR_DETAIL_INPUT%>';
	}
	else {
		maPmMstrDetailForm.strutsAction.value = '<%=MaPmMstrDetailAction.PM_MSTR_DETAIL_UPDATE%>';
	}
	
	var actionUrl = contextPath + "/workPmListUInsDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maPmMstrDetailForm), 'afterSave');
}

/**
 * 주기# 중복 check
 */
function checkPmNo(){
	var actionUrl = contextPath + "/workPmListUInsDetail.do";
	var param =  "&strutsAction=" + '<%= MaPmMstrDetailAction.PM_MSTR_DETAIL_PM_NO_CHECK %>'
    		+  "&" + FormQueryString(maPmMstrDetailForm);
	XMLHttpPostVal(actionUrl, param, 'afterCheckPmNo');
}

function afterCheckPmNo(ajaxXmlDoc){
	chkPmNo = parseXmlDoc(ajaxXmlDoc, 'DESC');
	if(chkPmNo != '0')
	{
		alertMessage1("<bean:message key='MESSAGE.MSG0269'/>");
		maPmMstrDetailForm.elements['maPmMstrDetailDTO.pmNo'].value = maPmMstrDetailForm.elements['maPmMstrDetailDTO.oldPmNo'].value;
    }
}


/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	maPmMstrDetailForm.elements['maPmMstrDetailDTO.pmId'].value = maPmMstrDetailForm.elements['maPmMstrCommonDTO.pmId'].value;
	maPmMstrDetailForm.elements['maPmMstrDetailDTO.oldPmNo'].value = maPmMstrDetailForm.elements['maPmMstrDetailDTO.pmNo'].value;
	
	if (parent.findGridRow)	parent.findGridRow(maPmMstrDetailForm.elements['maPmMstrDetailDTO.pmId'].value);
	
	getTopPage().afterSaveAll(currentPageId);
	
	if (maPmMstrDetailForm.elements['maPmMstrDetailDTO.revisionStatusId'].value != "W")  setReadOnly("maPmMstrDetailDTO.equipDesc");
	
}

/**
 * 승인요청
 */
function goApproval()
{
	if(checkIsUpdate(document)){
	 alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
	}else{

	if(checkValidation()) return;
	
	var objectId = maPmMstrDetailForm.elements['maPmMstrCommonDTO.pmId'].value;
	var pmDesc = maPmMstrDetailForm.elements['maPmMstrDetailDTO.description'].value;
	 
	 //APPR_TYPE:결재구분상태[예:일일작업:WODAY] , OBJECT_ID, 
	appAction(objectId, "PMINSREV", pmDesc);
	}
}
 
function afterApproval()
{
	if (parent.findGridRow)	parent.findGridRow(maPmMstrDetailForm.elements['maPmMstrCommonDTO.pmId'].value);
	goClose('workPmListUInsDetail');
}
  
/**
 * 제개정 완료
 */ 
function goRevcompleted()
{
	var revStatus = maPmMstrDetailForm.elements['maPmMstrDetailDTO.revisionStatusId'].value;
	var objId = maPmMstrDetailForm.elements['maPmMstrDetailDTO.pmId'].value;
	var objNo = maPmMstrDetailForm.elements['maPmMstrDetailDTO.pmNo'].value;
	var revhistId = maPmMstrDetailForm.elements['maPmMstrDetailDTO.revisionhistId'].value;
	 
	revCompleted(revStatus, objId, objNo, revhistId, "PMSTD");
}

function afterRevcompleted(ajaxXmlDoc)
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	alertMessage1("<bean:message key='MESSAGE.CMSG102'/>");
	//조회후 선택!
	if(parent.findGridRow) parent.findGridRow(maPmMstrDetailForm.elements['maPmMstrCommonDTO.pmId'].value);
	if(parent.goTabPage) parent.goTabPage(currentPageId);
	
	setState();
}

function goRevision()
{
	var revhistId = maPmMstrDetailForm.elements['maPmMstrDetailDTO.revisionhistId'].value;
	var desc = maPmMstrDetailForm.elements['maPmMstrDetailDTO.description'].value;
	
	openRev("commRevRevision", revhistId, desc, currentPageId);
}

function setState()
{
	
	if("W"==maPmMstrDetailForm.elements['maPmMstrDetailDTO.revisionStatusId'].value
			|| "P"==maPmMstrDetailForm.elements['maPmMstrDetailDTO.revisionStatusId'].value
			|| "DA"==maPmMstrDetailForm.elements['maPmMstrDetailDTO.revisionStatusId'].value){
		setEnableAll();
		hideBtn("REVISION");
	}
	else if("C"==maPmMstrDetailForm.elements['maPmMstrDetailDTO.revisionStatusId'].value
			|| "RA"==maPmMstrDetailForm.elements['maPmMstrDetailDTO.revisionStatusId'].value
			|| "PA"==maPmMstrDetailForm.elements['maPmMstrDetailDTO.revisionStatusId'].value
			|| "OA"==maPmMstrDetailForm.elements['maPmMstrDetailDTO.revisionStatusId'].value) {
		setDisableAll();
		hideBtn("APPROVAL");
		hideBtn("REVCOMPLETED");
		showBtn("REVISION");
	}
	else {
		setDisableAll();
	}
}



//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maPmMstrDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maPmMstrCommonDTO.pmId" />
	<html:hidden property="maPmMstrCommonDTO.selectedPmType" />
	<html:hidden property="maPmMstrDetailDTO.pmId" />
 	<html:hidden property="maPmMstrDetailDTO.pmType" />
 	<html:hidden property="maPmMstrDetailDTO.deptId" />
 	<html:hidden property="maPmMstrDetailDTO.scheduleType" />
 	<html:hidden property="maPmMstrDetailDTO.periodType" />
 	
 	<html:hidden property="maPmMstrCommonDTO.selectedWoType" />
 	<html:hidden property="maPmMstrDetailDTO.woType" />
 	<html:hidden property="maPmMstrDetailDTO.pmCateg" />
 	<html:hidden property="maPmMstrDetailDTO.empId" />
 	<html:hidden property="maPmMstrDetailDTO.wkCtrId" />
 	<html:hidden property="maPmMstrDetailDTO.eqLocId" />
 	<html:hidden property="maPmMstrDetailDTO.lastSchDate" />
 	<html:hidden property="maPmMstrDetailDTO.wrkcalListId" />
 	<html:hidden property="maPmMstrDetailDTO.lnWrkListId" />
 	<html:hidden property="maPmMstrDetailDTO.isActive" />
 	
 	<html:hidden property="maPmMstrDetailDTO.revisionhistId" />
 	<html:hidden property="maPmMstrDetailDTO.revisionStatusId" />
 	<html:hidden property="maPmMstrDetailDTO.oldCycle" />
    <html:hidden property="maPmMstrDetailDTO.oldPeriodType" />
    <html:hidden property="maPmMstrDetailDTO.oldScheduleType" />
    <html:hidden property="maPmMstrDetailDTO.oldInitWrkDate" />
    <html:hidden property="maPmMstrDetailDTO.oldEquipId" />
    <html:hidden property="maPmMstrDetailDTO.plantId" />
	<html:hidden property="maPmMstrDetailDTO.equipId" />
	<html:hidden property="maPmMstrDetailDTO.pmEquipId" />
	<html:hidden property="maPmMstrDetailDTO.oldPmNo" />
	
 	<html:hidden property="appReqCommonDTO.objectId"/>
	<html:hidden property="appReqCommonDTO.apprType"/>
 	
	<div class="article_box">
		<div class="form_box">
			<!-- 작업종류 -->
			<div class="field">
				<label class="check">작업종류</label>
				<div class="input_read" id="woNoDiv">
					<html:text property="maPmMstrDetailDTO.woTypeDesc" tabindex="10" readonly="true"/>
				</div>
			</div>
			<!-- 작업형태 -->
			<div class="field">
				<label class="check">작업형태</label>
				<div class="input_read" id="woStatusDescDiv">
					<html:text property="maPmMstrDetailDTO.pmTypeDesc" tabindex="20" readonly="true" />
				</div>
			</div>
			<!-- 주기# -->
			<div class="field">
				<label class="check">주기#</label>
				<div class="input_box">
					<html:text property="maPmMstrDetailDTO.pmNo" tabindex="30" />
				</div>
			</div>
			<!-- 공장 -->
			<div class="field">
				<label class="check">공장</label>
				<div class="input_box">
                    <html:text property="maPmMstrDetailDTO.plantDesc" tabindex="40" />
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
			</div>
			<!-- 작업명 -->
			<div class="field_long">
				<label class="check">작업명</label>
				<div class="input_box">
                    <html:text property="maPmMstrDetailDTO.description" tabindex="50" />
                </div>
			</div>
			<!-- 설비 -->
			<div class="field">
				<label class="check">설비</label>
				<div class="input_box">
                    <html:text property="maPmMstrDetailDTO.equipDesc" tabindex="60" />
					<p class="open_spop"><a><span>조회</span></a></p>
                </div>
			</div>
			<!-- 최초작업시작일 -->
			<div class="field">
				<label class="check">최초작업시작일</label>
				<div class="input_box">
                    <html:text property="maPmMstrDetailDTO.initWrkDate" tabindex="70" />
                    <p class="open_calendar"><a><span>조회</span></a></p>
                </div>
			</div>
			<!-- 주기 -->
			<div class="field">
                <label class="check">주기</label>
                <div class="datetime_wrap">
                    <div class="input_box">
                        <html:text property="maPmMstrDetailDTO.cycle" tabindex="80" styleClass="num"/>
                    </div>
                    <div class="input_box">
                        <html:text property="maPmMstrDetailDTO.periodTypeDesc" tabindex="90" />
                        <p class="open_spop">
                            <a><span>조회</span></a>
                        </p>
                    </div>
                </div>
            </div>
			<!-- 몇일전 일정생성 -->
			<div class="field">
				<label>몇일전 일정생성?</label>
				<div class="input_box">
					<html:text property="maPmMstrDetailDTO.woResBef" tabindex="100" styleClass="num"/>
				</div>
			</div>
			<!-- 근무달력 -->
			<div class="field">
				<label class="check">근무달력</label>
				<div class="input_box">
					<html:text property="maPmMstrDetailDTO.wrkcalListDesc" tabindex="110"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 시행여부 -->
			<div class="field">
				<label class="check">시행여부</label>
				<div class="input_box">
					<html:text property="maPmMstrDetailDTO.isActiveDesc" tabindex="120"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 작업부서 -->
			<div class="field">
				<label class="check">작업부서</label>
				<div class="input_box">
					<html:text property="maPmMstrDetailDTO.deptDesc" tabindex="130"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 작업그룹 -->
			<div class="field">
				<label>작업그룹</label>
				<div class="input_box">
					<html:text property="maPmMstrDetailDTO.wkCtrDesc" tabindex="140"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 담당자 -->
			<div class="field">
				<label>담당자</label>
				<div class="input_box">
					<html:text property="maPmMstrDetailDTO.empName" tabindex="150"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 1일 작업횟수 -->
			<div class="field">
				<label>1일 작업횟수</label>
				<div class="input_box">
					<html:text property="maPmMstrDetailDTO.workNumber" tabindex="160"/>
				</div>
			</div>
			<!-- 최신 Version 여부 -->
			<div class="field">
				<label>최신 Version 여부</label>
				<div class="input_read">
					<html:text property="maPmMstrDetailDTO.isLastVersion" tabindex="170" readonly="true" />
				</div>
			</div>
			
			<!-- Revision 진행상태 -->
			<div class="field">
				<label>Revision 진행상태</label>
				<div class="input_read" >
					<html:text property="maPmMstrDetailDTO.revisionStatusDesc" tabindex="180" readonly="true" />
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
			    <label>비고</label>
			    <div class="input_box">
			        <html:textarea property="maPmMstrDetailDTO.remark" styleClass="ta50" tabindex="190" />
			    </div>
			</div>
			<c:set var="filePath" value="enhance/${compName}/work/pm/list/ins/workPmListUInsDetail_${compNo}.jsp" />
			<c:if test="${udf:isExist(filePath)}">
				<c:import charEncoding="UTF-8" url="/enhance/${compName}/work/pm/list/ins/workPmListUInsDetail_${compNo}.jsp"></c:import>
			</c:if>
			
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
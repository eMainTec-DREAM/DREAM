<%--===========================================================================
작업요청 - 투자요청(상세)
author  js.lee
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

var recDeptAc;
var recWkCtrDescAc;
var recEmpAc;
var reqRequestDefaultStr;
var moDescAc;
var equipDescAc;
var eqLocDescAc,plantAc;

function loadPage()
{
	//요청내용 insert 시 기본 내용. focus 시 사라짐.
	reqRequestDefaultStr = "<bean:message key='MESSAGE.MSG215'/>";
	//결재보기에서 열었을때.
	if(parent.currentPageId == "appReadyList") setDisableAll();
	
	setTitle("reqWorkDetailDTO.woReqNo", "reqWorkDetailDTO.reqDesc");
	setForUpdate();
	
	/** 투자구분 */
	acSysDesc("reqWorkDetailDTO.invtCategDesc","reqWorkDetailDTO.invtCateg","INVT_CATEG");
	/** 분류 */
	var woTypeAc = new autoC({"reqWorkDetailDTO.invtTypeDesc":"description"});
    woTypeAc.setAcConditionMap({
        	"list_type":"INVT_TYPE",
        	"is_use":"Y"
  	   });
    woTypeAc.setAcDConditionMap({
       	"param1":"reqWorkDetailDTO.invtCateg"
 	   });
    woTypeAc.setTable("TACDSYSD");
    woTypeAc.setAcResultMap({
        "reqWorkDetailDTO.invtType":"cdsysd_no"
    });
    woTypeAc.init();
			
    /*설비위치  */
	eqLocDescAc = new autoC({"reqWorkDetailDTO.reqEqLocDesc":"full_desc"});
    eqLocDescAc.setKeyName("reqWorkDetailDTO.reqEqLocId");
    eqLocDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	});
    eqLocDescAc.setAcDConditionMap({
    	"plant" : "reqWorkDetailDTO.plantId"
    });
    eqLocDescAc.setTable("TAEQLOC");
    eqLocDescAc.setAcResultMap({
        "reqWorkDetailDTO.reqEqLocId":"eqloc_id"
    });
    eqLocDescAc.init();
    
    /** 공장 */
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
    
	//acSysDesc("reqWorkDetailDTO.invtTypeDesc","reqWorkDetailDTO.invtType","INVT_TYPE");

    /**처리부서  */
    recDeptAc = new autoC({"reqWorkDetailDTO.recDeptDesc":"description"});
    recDeptAc.setAcConditionMap({
        "comp_no":loginUser.compNo
        ,"is_maint":"Y"
    });
    recDeptAc.setAcDConditionMap({
    	"plant" : "reqWorkDetailDTO.plantId"
    });
    recDeptAc.setTable("TADEPT");
    recDeptAc.setKeyName("reqWorkDetailDTO.recDeptId");
    recDeptAc.setAcResultMap({
        "reqWorkDetailDTO.recDeptId":"dept_id"
        ,"reqWorkDetailDTO.recEmpId":"responseBy"
        ,"reqWorkDetailDTO.recEmpName":"responseDesc"
    });
    recDeptAc.init();
    
    /**설비  */
    equipDescAc = new autoC({"reqWorkDetailDTO.reqEquipDesc":"description"});
    equipDescAc.setKeyName("reqWorkDetailDTO.reqEquipId");
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcConditionMap({
  	   "a.comp_no":loginUser.compNo,
  	   "CUSTOM":"EQ_STATUS IN('R','S')"
  	});
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "reqWorkDetailDTO.reqEqLocId",
    	"plant" : "reqWorkDetailDTO.plantId"
    });
    equipDescAc.setAcResultMap({
        "reqWorkDetailDTO.reqEquipId":"equip_id"
        ,"reqWorkDetailDTO.reqEqLocId":"eqloc_id"
        ,"reqWorkDetailDTO.reqEqLocDesc":"eqLocDesc"
    });
    equipDescAc.init();
    
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
    recEmpAc.setAcConditionMap({
        "comp_no":loginUser.compNo,
        "is_join":"Y"
       });
    recEmpAc.setTable("TAEMP");
    recEmpAc.setAcDConditionMap({
    	"plant" : "reqWorkDetailDTO.plantId"
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
    
	//우선순위
    acSysDesc("reqWorkDetailDTO.reqPriorityDesc","reqWorkDetailDTO.reqPriorityId","REQ_PRIORITY",true);
    
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
		hideBtn("save");
	}
	else {
		setDisableAll();
	}
}

function btnControl()
{
	//승인요청btn
	if(reqWorkDetailForm.elements['reqWorkDetailDTO.reqEmpId'].value!=loginUser.empId){
		hideBtn("approval");
	}
	else if("WRT"==reqWorkDetailForm.elements['reqWorkDetailDTO.woReqStatusId'].value || "WRTDA"==reqWorkDetailForm.elements['reqWorkDetailDTO.woReqStatusId'].value){
		showBtn("approval");
	}
}

function goTabPage(pageId)
{
	var form = document.reqWorkDetailForm;

	if(pageId == "maDocLibList" || pageId == "maWoDocLibList"|| pageId == "reqInvWorkDocLibList")
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
		reqWorkDetailForm.elements['appReqCommonDTO.apprType'].value = "REQINVTWORK";
		goCommonTabPage(form, '' , pageId);
	}
	else
	goCommonTabPage(form, '' , pageId);
}

function goInput()
{
	setEnableAll();
	sequenceNextVal('SQAWOREQ_ID');
	
	$('[name="reqWorkDetailDTO.reqRequest"]').attr("placeholder", reqRequestDefaultStr);
	
	/* reqWorkDetailForm.elements['reqWorkDetailDTO.reqRequest'].value = reqRequestDefaultStr;
	
	if (reqWorkDetailForm.elements['reqWorkDetailDTO.reqRequest'].value == reqRequestDefaultStr) 
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
		reqWorkDetailForm.elements['reqWorkDetailDTO.plantId'].value = loginUser.plant;
		reqWorkDetailForm.elements['reqWorkDetailDTO.plantDesc'].value = loginUser.plantDesc;
    }
	
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
	
	//strutsAction 셋팅.
	if(ckCreate(currentPageId)) reqWorkDetailForm.strutsAction.value = '<%=ReqWorkDetailAction.DETAIL_INSERT%>';
	else reqWorkDetailForm.strutsAction.value = '<%=ReqWorkDetailAction.DETAIL_UPDATE%>';

	var actionUrl = contextPath + "/reqInvWorkDetail.do";
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
	if (parent.findGridRow)	parent.findGridRow(reqWorkDetailForm.elements['reqWorkDetailDTO.woReqId'].value);

	// 이상점검처리대상 - 작업계획 (목록) 에서 요청한 경우
    if(parent.currentPageId=="maBdPointWoReqList" && reqWorkDetailForm.strutsAction.value=='<%=ReqWorkDetailAction.DETAIL_INSERT%>')
    {
   	 	parent.afterCreate(reqWorkDetailForm.elements['reqWorkCommonDTO.woReqId'].value);
    }
	
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
		 appAction(woReqId, "REQINVTWORK", description);
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
	goClose('reqInvWorkDetail');
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

		var actionUrl = contextPath + "/reqInvWorkDetail.do";
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


/* audit Trail */
function goAudtrailLink()
{
	var objectId = reqWorkDetailForm.elements['reqWorkDetailDTO.woReqId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}


//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/reqInvWorkDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="reqWorkDetailDTO.woReqId" />
	<html:hidden property="currentPageId"/>
	<html:hidden property="reqWorkCommonDTO.woReqId" />
	<html:hidden property="reqWorkCommonDTO.selectedWoReqTypeId" />
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
	<html:hidden property="reqWorkDetailDTO.invtCateg"/>
	<html:hidden property="reqWorkDetailDTO.invtType"/>
	<html:hidden property="reqWorkDetailDTO.woReqType"/>
	<html:hidden property="reqWorkDetailDTO.plantId"/>
	
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
			<!-- 요청상태 -->
			<div class="field">
				<label><bean:message key="LABEL.apprStatus"/></label>
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
			<!-- 투자구분 -->
            <div class="field">
                <label><bean:message key="LABEL.invtCategDesc"/></label>
                <div class="input_box">
                    <html:text property="reqWorkDetailDTO.invtCategDesc" tabindex="30"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
            </div>
			<!-- 분류 -->
            <div class="field">
                <label><bean:message key="LABEL.category"/></label>
                <div class="input_box">
                    <html:text property="reqWorkDetailDTO.invtTypeDesc" tabindex="40"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
            </div>
			<!-- 제목 -->
			<div class="field_long">
				<label class="check"><bean:message key="LABEL.title"/></label>
				<div class="input_box">
					<html:text property="reqWorkDetailDTO.reqDesc" tabindex="50" />
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
            
            <!-- 처리작업그룹 -->
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
			<!-- 완료희망일 -->
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
            <!-- 공장(Plant) -->
			<div class="field">
				<label><bean:message key="LABEL.plant"/></label>
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
			<div class="field_long">
				<label><bean:message key="LABEL.reviewDesc"/></label>
				<div class="input_read">
					<html:textarea property="reqWorkDetailDTO.review" styleClass="ta150" tabindex="120" readonly="true"/>
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
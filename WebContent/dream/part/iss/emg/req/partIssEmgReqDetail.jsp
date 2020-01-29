<%--===========================================================================
출고요청 Detail
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.iss.emg.req.action.PartIssEmgReqDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ page import="common.bean.User"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html>
<head>
<!-- 출고요청 -->
<title><bean:message key='LABEL.issNo' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

/** 자동완성 변수 */
var whAc;
var toWhAc;
var ctctrAc;
var recByAc;
var equipDescAc;
var plantAc;

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
    setTitle("partIssEmgReqDetailDTO.ptEmgIssReqNo");
    //For Save
    setForUpdate();
    
    setState();
    
    //창고 자동완성
    whAc = new autoC({"partIssEmgReqDetailDTO.wcodeName":"wname"});
    whAc.setTable("TAWAREHOUSE");
    whAc.setKeyName("partIssEmgReqDetailDTO.wcodeId");
    whAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	,"wh_categ":"PART"
    	,"is_use":"Y"
  	});
    whAc.setAcDConditionMap({
    	"plant" : "partIssEmgReqDetailDTO.plantId"
   		,"plantDesc" : "partIssEmgReqDetailDTO.plantDesc"
    });
    whAc.setAcResultMap({
        "partIssEmgReqDetailDTO.wcodeId":"wcode_id"
    });
    whAc.init();
    
    //저장창고 자동완성
    toWhAc = new autoC({"partIssEmgReqDetailDTO.toWcodeName":"wname"});
    toWhAc.setTable("TAWAREHOUSE");
    toWhAc.setKeyName("partIssEmgReqDetailDTO.toWcodeId");
    toWhAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	,"wh_categ":"PART"
    	,"is_use":"Y"
  	});
    toWhAc.setAcDConditionMap({
    	"plant" : "partIssEmgReqDetailDTO.plantId"
   		,"plantDesc" : "partIssEmgReqDetailDTO.plantDesc"
    });
    toWhAc.setAcResultMap({
        "partIssEmgReqDetailDTO.toWcodeId":"wcode_id"
    });
    toWhAc.init();
    
 	// 코스트센터
	ctctrAc = new autoC({"partIssEmgReqDetailDTO.ctctrDesc":"description"});
	ctctrAc.setTable("TACTCTR");
	ctctrAc.setKeyName("partIssEmgReqDetailDTO.ctctrId");
	ctctrAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
	ctctrAc.setAcDConditionMap({
    	"plant" : "partIssEmgReqDetailDTO.plantId"
   		,"plantDesc" : "partIssEmgReqDetailDTO.plantDesc"
    });
	ctctrAc.setAcResultMap({
        "partIssEmgReqDetailDTO.ctctrId":"ctctr_id"
        ,"partIssEmgReqDetailDTO.toWcodeId":"inWcodeId"
        ,"partIssEmgReqDetailDTO.toWcodeName":"inWcodeDesc"
    });
	ctctrAc.init();
	
	//수령자 자동완성
    recByAc = new autoC({"partIssEmgReqDetailDTO.recByDesc":"emp_name"});
    recByAc.setTable("TAEMP");
    recByAc.setKeyName("partIssEmgReqDetailDTO.recBy");
    recByAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
	});
    recByAc.setAcDConditionMap({
    	"plant" : "partIssEmgReqDetailDTO.plantId"
    });
    recByAc.setAcResultMap({
        "partIssEmgReqDetailDTO.recBy":"emp_id"
    });
    recByAc.init();
    
 	// 설비
	equipDescAc = new autoC({"partIssEmgReqDetailDTO.equipDesc":"description"});
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setKeyName("partIssEmgReqDetailDTO.equipId");
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	});
    equipDescAc.setAcDConditionMap({
    	"plant" : "partIssEmgReqDetailDTO.plantId"
    });
    equipDescAc.setAcResultMap({
        "partIssEmgReqDetailDTO.equipId":"equip_id"
    });
    equipDescAc.init();
    
 	// 공장코드
	plantAc = new autoC({"partIssEmgReqDetailDTO.plantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setKeyName("partIssEmgReqDetailDTO.plantId");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "partIssEmgReqDetailDTO.plantId":"plant"
    });
    plantAc.init();
}

function setState()
{
	var ptEmgIssReqStatus = partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.ptEmgIssReqStatus'].value;
	
	if(ptEmgIssReqStatus == "CI"){
		setDisableAll();
		showBtn("CANCELISS");
	}
	else {
		setEnableAll();
		hideBtn("CANCELISS");
	}
}

/**
 * 입력
 */
function goInput()
{
	partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.plantId'].value = loginUser.plant;
	partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.plantDesc'].value = loginUser.plantDesc;
	partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.wcodeId'].value = loginUser.fromWcodeId;
	partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.wcodeName'].value = loginUser.fromWname;
	partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.toWcodeId'].value = loginUser.toWcodeId;
	partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.toWcodeName'].value = loginUser.toWname;
	partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.reqBy'].value = loginUser.empId;
	partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.reqDept'].value = loginUser.deptId;
	partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.ptEmgIssReqStatus'].value = "WT";
	partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.ptEmgIssReqStatusDesc'].value = "WT";
	valSysDir('partIssEmgReqDetailDTO.ptEmgIssReqStatus', 'partIssEmgReqDetailDTO.ptEmgIssReqStatusDesc', 'PTEMGISSREQ_STATUS', true);
	partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.reqDate'].value = getToday();
	
    sequenceNextVal('SQAPTEMGISSREQ_ID');
}
function setSequenceVal(sequenceVal)
{
    partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.ptEmgIssReqId'].value = sequenceVal;
    partIssEmgReqDetailForm.elements['partIssEmgReqCommonDTO.ptEmgIssReqId'].value = sequenceVal;
    partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.ptEmgIssReqNo'].value = sequenceVal;
}
/**
 * 수정
 */
function goUpdate()
{
	
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
    if(ckCreate(currentPageId)) partIssEmgReqDetailForm.strutsAction.value = "<%=PartIssEmgReqDetailAction.DETAIL_INPUT%>";
    else partIssEmgReqDetailForm.strutsAction.value = "<%=PartIssEmgReqDetailAction.DETAIL_UPDATE%>";

	var actionUrl = contextPath + "/partIssEmgReqDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(partIssEmgReqDetailForm),'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc) {
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc))
		return;
	//=====================================
	if (parent.findGridRow)parent.findGridRow(partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.ptEmgIssReqId'].value);

	partIssEmgReqDetailForm.elements['partIssEmgReqCommonDTO.ptEmgIssReqId'].value = partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.ptEmgIssReqId'].value;
	getTopPage().afterSaveAll(currentPageId);
	setTitle("partIssEmgReqDetailDTO.ptEmgIssReqNo");

	setState();
}

function goTabPage(pageId)
{
	var form = document.partIssEmgReqDetailForm;
	
	if(pageId == "maAppPrcList" || pageId == "appPrcList") {
		partIssEmgReqDetailForm.elements['appReqCommonDTO.objectId'].value = partIssEmgReqDetailForm.elements['partIssEmgReqCommonDTO.ptEmgIssReqId'].value;
		partIssEmgReqDetailForm.elements['appReqCommonDTO.apprType'].value = "PTISSREQ";
		goCommonTabPage(form, '' , pageId);
	}
	else
		goCommonTabPage(form, '' , pageId);
    
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
		partIssEmgReqDetailForm.strutsAction.value = '<%=PartIssEmgReqDetailAction.REQ_STATUS_UPDATE%>';

		var actionUrl = contextPath + "/partIssEmgReqDetail.do";
		XMLHttpPost(actionUrl, FormQueryString(partIssEmgReqDetailForm), 'afterRequest');
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
	if (parent.findGridRow)	parent.findGridRow(partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.ptEmgIssReqId'].value);

	setDisableAll();
	$('.b_grprint').show();	
	partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.ptEmgIssReqStatus'].value = "SI";
	partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.ptEmgIssReqStatusDesc'].value = "SI";
	valSysDir('partIssEmgReqDetailDTO.ptEmgIssReqStatus', 'partIssEmgReqDetailDTO.ptEmgIssReqStatusDesc', 'PTEMGISSREQ_STATUS', true);
	getTopPage().afterSaveAll(currentPageId);
	
	setState();
}

/**
 * 승인요청
 */
function goApproval()
{
	 if(checkIsUpdate(document)){
		 alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
	 }else{
		 var ptEmgIssReqId = partIssEmgReqDetailForm.elements['partIssEmgReqCommonDTO.ptEmgIssReqId'].value;
		 var description = partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.title'].value;
		 
		 //APPR_TYPE:결재구분상태[예:일일작업:WODAY] , OBJECT_ID, 
		 appAction(ptEmgIssReqId, "PTISSREQ", description);
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
	if (parent.findGridRow)	parent.findGridRow(partIssEmgReqDetailForm.elements['partIssEmgReqCommonDTO.ptEmgIssReqId'].value);
	goClose('partIssEmgReqDetail');
}

/** 
 * 출고 요청서 출력 
 */
 function goGrprint(){
	 reportCall('partIssEmgReqDetail','partIssEmgReqDetail', "<%=user.getCompNo()%>", "<%=user.getUserId()%>", "<%=user.getLangId()%>",partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.ptEmgIssReqId'].value); 
}

/**
 * 출고처리
 */
function goConfirmiss()
{
	//저장을 누르지 않고 출고처리시 저장 프로세스 후에 출고처리를 실행합니다.
	
	//================================
    // 필수 항목 체크한다.
    //================================
    if(checkValidation()) return;

	if(checkConfirmValidation()) return;
	
	getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0053'/>", function(result){
		if(result){
			if(ckCreate(currentPageId)) partIssEmgReqDetailForm.strutsAction.value = "<%=PartIssEmgReqDetailAction.DETAIL_INPUT%>";
			else partIssEmgReqDetailForm.strutsAction.value = "<%=PartIssEmgReqDetailAction.DETAIL_UPDATE%>";

			var actionUrl = contextPath + "/partIssEmgReqDetail.do";
			XMLHttpPost(actionUrl, FormQueryString(partIssEmgReqDetailForm),'beforeConfirm');
		}
	});
}
function beforeConfirm(ajaxXmlDoc)
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc))
		return;
	//=====================================
	if (parent.findGridRow)parent.findGridRow(partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.ptEmgIssReqId'].value);

	partIssEmgReqDetailForm.elements['partIssEmgReqCommonDTO.ptEmgIssReqId'].value = partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.ptEmgIssReqId'].value;
	getTopPage().afterSaveAll(currentPageId);
	setTitle("partIssEmgReqDetailDTO.ptEmgIssReqNo");

	setState();
	
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;

	if(checkConfirmValidation()) return;

	partIssEmgReqDetailForm.strutsAction.value = '<%=PartIssEmgReqDetailAction.PTISSEMG_ISSUE%>';
	var actionUrl = contextPath + "/partIssEmgReqDetail.do";
	XMLHttpPostVal(actionUrl, FormQueryString(partIssEmgReqDetailForm), 'afterConfirm');
}
function afterConfirm(ajaxXmlDoc)
{

	var rtnValue = parseXmlDoc(ajaxXmlDoc, 'DESC');
	
	if(rtnValue[0]=="E"){
		alert(rtnValue[1]);
	}
	//출고 처리했습니다.
	alertMessage1("<bean:message key='MESSAGE.MSG0066'/>");
		
	//완료!
	partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.ptEmgIssReqStatus'].value = "CI";
	valSysDirCode('partIssEmgReqDetailDTO.ptEmgIssReqStatus', 'partIssEmgReqDetailDTO.ptEmgIssReqStatusDesc', 'PTEMGISSREQ_STATUS', true);
	setState();
	
	if (parent.findGridRow)parent.findGridRow(partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.ptEmgIssReqId'].value);
	
    if(typeof searchPage("partIssEmgReqPartsList").loadPage == "function"){
    	searchPage("partIssEmgReqPartsList").loadPage();	
 	}
}

function goCanceliss()
{
	if(ckCreate(currentPageId)) return;
		
	if(checkIsUpdate(document)){
		 alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
	}else{
		getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0054'/>", function(result){
			if(result){
				//================================
				// 필수 항목 체크한다.
				//================================
				if(checkValidation()) return;
				
				partIssEmgReqDetailForm.strutsAction.value = '<%=PartIssEmgReqDetailAction.PTISSEMG_ISSUE_CANCEL%>';
				var actionUrl = contextPath + "/partIssEmgReqDetail.do";
				XMLHttpPostVal(actionUrl, FormQueryString(partIssEmgReqDetailForm), 'afterCanceliss');
			}
		});
	 }
}
function afterCanceliss(ajaxXmlDoc)
{
	var rtnValue = parseXmlDoc(ajaxXmlDoc, 'DESC');
	
	if(rtnValue[0]=="E"){
		alertMessage1(rtnValue[1]);
	}
	alertMessage1("<bean:message key='MESSAGE.MSG0064'/>");
	
	//취소!
	partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.ptEmgIssReqStatus'].value = "X";
	valSysDirCode('partIssEmgReqDetailDTO.ptEmgIssReqStatus', 'partIssEmgReqDetailDTO.ptEmgIssReqStatusDesc', 'PTEMGISSREQ_STATUS', true);
	setState();
	
	if (parent.findGridRow)parent.findGridRow(partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.ptEmgIssReqId'].value);
}


/* audit Trail */
function goAudtrailLink()
{
	var objectId = partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.ptEmgIssReqId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}


//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

	<html:form action="/partIssEmgReqDetail">
		<html:hidden property="strutsAction" />
		<html:hidden property="currentPageId"/>
		<html:hidden property="partIssEmgReqCommonDTO.ptEmgIssReqId" />
		<!-- Key -->
		<html:hidden property="partIssEmgReqDetailDTO.ptEmgIssReqId" />
		<!-- Key -->
		<html:hidden property="partIssEmgReqDetailDTO.ptEmgIssReqStatus" />
		<html:hidden property="partIssEmgReqDetailDTO.wcodeId" />
		<html:hidden property="partIssEmgReqDetailDTO.ctctrId" />
		<html:hidden property="partIssEmgReqDetailDTO.toWcodeId" />
		<html:hidden property="partIssEmgReqDetailDTO.recBy" />
		<html:hidden property="partIssEmgReqDetailDTO.equipId" />
		<html:hidden property="partIssEmgReqDetailDTO.plantId" />
		<html:hidden property="partIssEmgReqDetailDTO.reqBy" />
		<html:hidden property="partIssEmgReqDetailDTO.reqDept" />
		
		<html:hidden property="appReqCommonDTO.objectId"/>
		<html:hidden property="appReqCommonDTO.apprType"/>

		<div class="article_box">
			<div class="form_box">
				<!-- 출고No -->
				<div class="field" id="issNoField">
					<label class="check"><bean:message key="LABEL.issNo" /></label>
					<div class="input_read">
						<html:text property="partIssEmgReqDetailDTO.ptEmgIssReqNo" tabindex="10" readonly="true"/>
					</div>
				</div>
				<!-- 상태 -->
				<div class="field">
					<label><bean:message key="LABEL.status" /></label>
					<div class="input_read">
						<html:text property="partIssEmgReqDetailDTO.ptEmgIssReqStatusDesc" tabindex="20" readonly="true" />
					</div>
				</div>
				<!-- 출고일자 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.issDate"/></label>
					<div class="input_box input_carendar">
						<html:text property="partIssEmgReqDetailDTO.reqDate" tabindex="30" />
						<p class="open_calendar"><span>날짜</span></p>
					</div>
				</div>
				<!-- 출고창고 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.fromWname" /></label>
					<div class="input_box">
						<html:text property="partIssEmgReqDetailDTO.wcodeName" tabindex="40" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 코스트센터 -->
				<div class="field">
					<label><bean:message key="LABEL.ctctrName" /></label>
					<div class="input_box">
						<html:text property="partIssEmgReqDetailDTO.ctctrDesc" tabindex="50" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 보관창고 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.toWname" /></label>
					<div class="input_box">
						<html:text property="partIssEmgReqDetailDTO.toWcodeName" tabindex="60" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 수령자 -->
				<div class="field">
					<label><bean:message key="LABEL.recBy" /></label>
					<div class="input_box">
						<html:text property="partIssEmgReqDetailDTO.recByDesc" tabindex="70" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 설비 -->
				<div class="field">
					<label><bean:message key="LABEL.equipDesc" /></label>
					<div class="input_box">
						<html:text property="partIssEmgReqDetailDTO.equipDesc" tabindex="80" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 공장 -->
				<div class="field">
					<label><bean:message key="LABEL.plantDesc" /></label>
					<div class="input_box">
						<html:text property="partIssEmgReqDetailDTO.plantDesc" tabindex="90" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 비고 -->
				<div class="field_long">
					<label><bean:message key="LABEL.remark" /></label>
					<div class="input_box">
						<html:textarea property="partIssEmgReqDetailDTO.remark" tabindex="100" />
					</div>
				</div>
				<c:set var="filePath" value="enhance/${compName}/part/iss/emg/req/partIssEmgReqDetail_${compNo}.jsp" />
				<c:if test="${udf:isExist(filePath)}">
					<c:import charEncoding="UTF-8" url="/enhance/${compName}/part/iss/emg/req/partIssEmgReqDetail_${compNo}.jsp"></c:import>
				</c:if>
			</div>
			<!-- End of Form_box -->
		</div>
		<!-- End of Article_box -->
	</html:form>
</body>
</html:html>

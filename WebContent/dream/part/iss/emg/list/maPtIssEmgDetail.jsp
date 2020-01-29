<%--===========================================================================
긴급출고 - 상세
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.iss.emg.list.action.MaPtIssEmgDetailAction"%> 
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="common.bean.MwareConfig"%>
<%
	String partGrade = MwareConfig.getPartGrade();
%>
<html:html> 
<head>
<!-- 긴급출고 -->
<title><bean:message key='LABEL.partNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!--//

var partNoAc;
var mainMngAc;
var wareHouseAc;
var partGradeAc;
var ctCtrAc;

function loadPage() 
{
	chkStatus();
    
    setForUpdate();
    
    ctCtrAc = new autoC({"maPtIssEmgDetailDTO.ctCtrDesc":"description"});
    ctCtrAc.setAcConditionMap({
	   "comp_no":loginUser.compNo
	   });
    ctCtrAc.setTable("TACTCTR");
    ctCtrAc.setAcResultMap({
	    "maPtIssEmgDetailDTO.ctCtrId":"ctctr_id" 
		,"maPtIssEmgDetailDTO.toWcodeId":"inWcodeId"
		,"maPtIssEmgDetailDTO.toWname":"inWcodeDesc" 
	});
    ctCtrAc.setKeyName("maPtIssEmgDetailDTO.ctCtrId"); 
    ctCtrAc.init();
    
	partNoAc = new autoC({"maPtIssEmgDetailDTO.partNo":"part_no"});
	partNoAc.setAcConditionMap({
	   "comp_no":loginUser.compNo
	   ,"part_categ":"SPPT"
	   });
	partNoAc.setAcDConditionMap({
    	"wcode_id" : "maPtIssEmgDetailDTO.wcodeId"
    });
	partNoAc.setTable("TAPARTS");
	partNoAc.setAcResultMap({
	    "maPtIssEmgDetailDTO.partId":"part_id"
	    ,"maPtIssEmgDetailDTO.partDesc":"full_desc"    
	});
	partNoAc.setKeyName("maPtIssEmgDetailDTO.partId"); 
	partNoAc.init();
    
	mainMngAc = new autoC({"maPtIssEmgDetailDTO.recByName":"emp_name"});
	mainMngAc.setAcConditionMap({
		"comp_no":loginUser.compNo,
    	"is_join":"Y"
		   });
	mainMngAc.setTable("TAEMP");
	mainMngAc.setKeyName("maPtIssEmgDetailDTO.recBy");
	mainMngAc.setAcResultMap({
	    "maPtIssEmgDetailDTO.recBy":"emp_id"
	});
	mainMngAc.init();
	
	wareHouseAc = new autoC({"maPtIssEmgDetailDTO.wname":"wname"});
    wareHouseAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"wh_categ":"PART"
  	   });
    wareHouseAc.setTable("TAWAREHOUSE");
    wareHouseAc.setKeyName("maPtIssEmgDetailDTO.wcodeId");
    wareHouseAc.setAcResultMap({
        "maPtIssEmgDetailDTO.wcodeId":"wcode_id"
    });
    wareHouseAc.init();
    
    
    toWhAc = new autoC({"maPtIssEmgDetailDTO.toWname":"wname"});
    toWhAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"wh_categ":"PART"
  	   });
    toWhAc.setTable("TAWAREHOUSE");
    toWhAc.setKeyName("maPtIssEmgDetailDTO.toWcodeId");
    toWhAc.setAcResultMap({
        "maPtIssEmgDetailDTO.toWcodeId":"wcode_id"
    });
    toWhAc.init();
    
    acSysDesc("maPtIssEmgDetailDTO.partGradeDesc","maPtIssEmgDetailDTO.partGrade","PART_GRADE",true);

	/* $("input[name='maPtIssEmgDetailDTO.partNo']").on({
		"keyup":function(e){
			valPartNo('maPtIssEmgDetailDTO.partId','maPtIssEmgDetailDTO.partNo','maPtIssEmgDetailDTO.partDesc', true);
		}
	}); */
	
    if(ckCreate(currentPageId)) goInput();
    else 
    {
    	setPtIssEmgTitle();
    	goUpdate();
    }
}

/**
 * Check Status 
 */
function chkStatus()
{
	var ptemgissStatus = maPtIssEmgDetailForm.elements['maPtIssEmgDetailDTO.ptemgissStatus'].value;
	 //완료!
	if(ptemgissStatus=="C")
	{
		setDisableAll();
		//출고취소버튼 살리기
		showBtn("CANCELISS");
	}
	else{
		setEnableAll();
		//출고취소버튼 죽임
		hideBtn("CANCELISS");
	}
}

function setPtIssEmgTitle()
{
    setTitle("maPtIssEmgDetailDTO.ptemgisslistId", "maPtIssEmgDetailDTO.partNo");
}

/**
 * 입력
 */
function goInput()
{
	sequenceNextVal('SQAPTEMGISSLIST_ID');

    maPtIssEmgDetailForm.elements['maPtIssEmgDetailDTO.ptemgissStatus'].value = "W";
    valSysDirCode('maPtIssEmgDetailDTO.ptemgissStatus', 'maPtIssEmgDetailDTO.ptemgissStatusDesc', 'PTEMGISS_STATUS', true);
    
    maPtIssEmgDetailForm.elements['maPtIssEmgDetailDTO.ptissType'].value = "WOISS";
   	maPtIssEmgDetailForm.elements['maPtIssEmgDetailDTO.ptemgTaskStatus'].value = "W";
    
    maPtIssEmgDetailForm.elements['maPtIssEmgDetailDTO.partGrade'].value = '<%=partGrade%>';
    maPtIssEmgDetailForm.elements['maPtIssEmgDetailDTO.partGradeDesc'].value = '<%=partGrade%>';
	valSysDirCode('maPtIssEmgDetailDTO.partGrade', 'maPtIssEmgDetailDTO.partGradeDesc', 'PART_GRADE', true);
	
    maPtIssEmgDetailForm.elements['maPtIssEmgDetailDTO.issueDept'].value = loginUser.deptId;
    maPtIssEmgDetailForm.elements['maPtIssEmgDetailDTO.issueDeptDesc'].value = loginUser.deptDesc;
    maPtIssEmgDetailForm.elements['maPtIssEmgDetailDTO.issueBy'].value = loginUser.empId;
    maPtIssEmgDetailForm.elements['maPtIssEmgDetailDTO.issueByName'].value = loginUser.empName;
    
    maPtIssEmgDetailForm.elements['maPtIssEmgDetailDTO.wcodeId'].value = loginUser.fromWcodeId;
	maPtIssEmgDetailForm.elements['maPtIssEmgDetailDTO.wname'].value = loginUser.fromWname;
	
	maPtIssEmgDetailForm.elements['maPtIssEmgDetailDTO.toWcodeId'].value = loginUser.toWcodeId;
	maPtIssEmgDetailForm.elements['maPtIssEmgDetailDTO.toWname'].value = loginUser.toWname;
	
	maPtIssEmgDetailForm.elements['maPtIssEmgDetailDTO.issueQty'].value = 1;
	
	maPtIssEmgDetailForm.elements['maPtIssEmgDetailDTO.issueDate'].value = getToday();
	
	partNoAc.openLov();
}


function setSequenceVal(sequenceVal)
{
	maPtIssEmgDetailForm.elements['maPtIssEmgDetailDTO.ptemgisslistId'].value = sequenceVal;
	maPtIssEmgDetailForm.elements['maPtIssEmgCommonDTO.ptemgisslistId'].value = sequenceVal;
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
    
    var issueQty = maPtIssEmgDetailForm.elements['maPtIssEmgDetailDTO.issueQty'].value;
    if(issueQty == "" || parseFloat(issueQty) == 0 )
    {
    	alertMessage1("<bean:message key='LABEL.useQty'/>"+"<bean:message key='MESSAGE.MSG0013' />");
    	return;
    }
        
    //strutsAction 셋팅.
    if(ckCreate(currentPageId)) maPtIssEmgDetailForm.strutsAction.value = "<%=MaPtIssEmgDetailAction.PTISSEMG_DETAIL_INPUT%>";
    else maPtIssEmgDetailForm.strutsAction.value = '<%=MaPtIssEmgDetailAction.PTISSEMG_DETAIL_UPDATE%>';
    
    var actionUrl = contextPath + "/maPtIssEmgDetail.do";
    XMLHttpPost(actionUrl, FormQueryString(maPtIssEmgDetailForm), 'afterSave');
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
   //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================

    if (parent.findGridRow)	parent.findGridRow(maPtIssEmgDetailForm.elements['maPtIssEmgCommonDTO.ptemgisslistId'].value);
    getTopPage().afterSaveAll(currentPageId);
    
    goUpdate();
    setPtIssEmgTitle();
    
    chkStatus();
}

/**
 * 저장후생성후 호출
 */
function afterSavenew() {
	sequenceNextVal('SQAPTEMGISSLIST_ID');
	M$('maPtIssEmgDetailDTO.partNo').value = '';
	M$('maPtIssEmgDetailDTO.partId').value = '';
	M$('maPtIssEmgDetailDTO.partDesc').value = '';
}

/**
 * Popup에서 데이터 선택후 호출됨. 
 */
function afterSetValue()
{

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
	
    var issueQty = maPtIssEmgDetailForm.elements['maPtIssEmgDetailDTO.issueQty'].value;
    if(issueQty == "" || parseFloat(issueQty) == 0 )
    {
    	alertMessage1("<bean:message key='LABEL.useQty'/>"+"<bean:message key='MESSAGE.MSG0013' />");
    	return;
    }
    
	 getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0053'/>", function(result){
		 if(result){
			 
			//strutsAction 셋팅.
			    if(ckCreate(currentPageId)) maPtIssEmgDetailForm.strutsAction.value = "<%=MaPtIssEmgDetailAction.PTISSEMG_DETAIL_INPUT%>";
			    else maPtIssEmgDetailForm.strutsAction.value = '<%=MaPtIssEmgDetailAction.PTISSEMG_DETAIL_UPDATE%>';
			    
			    var actionUrl = contextPath + "/maPtIssEmgDetail.do";
			    XMLHttpPost(actionUrl, FormQueryString(maPtIssEmgDetailForm), 'beforeConfirm');
			 
		 }
		});
	 
}
function beforeConfirm(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================

    if (parent.findGridRow)	parent.findGridRow(maPtIssEmgDetailForm.elements['maPtIssEmgCommonDTO.ptemgisslistId'].value);
    getTopPage().afterSaveAll(currentPageId);
    
    goUpdate();
    setPtIssEmgTitle();
    
    chkStatus();
    
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;

	if(checkConfirmValidation()) return;

	 maPtIssEmgDetailForm.strutsAction.value = '<%=MaPtIssEmgDetailAction.PTISSEMG_ISSUE%>';
	 var actionUrl = contextPath + "/maPtIssEmgDetail.do";
	XMLHttpPostVal(actionUrl, FormQueryString(maPtIssEmgDetailForm), 'afterConfirm');
}
function afterConfirm(ajaxXmlDoc)
{

	var rtnValue = parseXmlDoc(ajaxXmlDoc, 'DESC');
	
	if(rtnValue[0]=="E"){
		alert(rtnValue[1]);
	}else if(rtnValue[0]=="S"){
		//출고 처리했습니다.
		alertMessage1("<bean:message key='MESSAGE.MSG0066'/>");
			
		//완료!
		maPtIssEmgDetailForm.elements['maPtIssEmgDetailDTO.ptemgissStatus'].value = "C";
		valSysDirCode('maPtIssEmgDetailDTO.ptemgissStatus', 'maPtIssEmgDetailDTO.ptemgissStatusDesc', 'PTEMGISS_STATUS', true);
		chkStatus();
		
		 if (parent.findGridRow)	parent.findGridRow(maPtIssEmgDetailForm.elements['maPtIssEmgCommonDTO.ptemgisslistId'].value);
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

				 maPtIssEmgDetailForm.strutsAction.value = '<%=MaPtIssEmgDetailAction.PTISSEMG_ISSUE_CANCEL%>';
				 var actionUrl = contextPath + "/maPtIssEmgDetail.do";
				 XMLHttpPostVal(actionUrl, FormQueryString(maPtIssEmgDetailForm), 'afterCanceliss');
			 }
			});
	 }
}
function afterCanceliss(ajaxXmlDoc)
{
	var rtnValue = parseXmlDoc(ajaxXmlDoc, 'DESC');
	
	if(rtnValue[0]=="E"){
		alertMessage1(rtnValue[1]);
	}else if(rtnValue[0]=="S"){
		alertMessage1("<bean:message key='MESSAGE.MSG0064'/>");
		
		//완료!
		maPtIssEmgDetailForm.elements['maPtIssEmgDetailDTO.ptemgissStatus'].value = "W";
		valSysDirCode('maPtIssEmgDetailDTO.ptemgissStatus', 'maPtIssEmgDetailDTO.ptemgissStatusDesc', 'PTEMGISS_STATUS', true);
		chkStatus();
		
		 if (parent.findGridRow)	parent.findGridRow(maPtIssEmgDetailForm.elements['maPtIssEmgCommonDTO.ptemgisslistId'].value);
	}
}


/* audit Trail */
function goAudtrailLink()
{
	var objectId = maPtIssEmgDetailForm.elements['maPtIssEmgCommonDTO.ptemgisslistId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}

function afterAutoCmpt(code)
{
	if(code=='maPtIssEmgDetailDTO.wname' || code=='maPtIssEmgDetailDTO.partNo' || code=='maPtIssEmgDetailDTO.partGradeDesc')
    { 
		if(maPtIssEmgDetailForm.elements['maPtIssEmgDetailDTO.wcodeId'].value != "" &&
				maPtIssEmgDetailForm.elements['maPtIssEmgDetailDTO.partId'].value != "" &&
				maPtIssEmgDetailForm.elements['maPtIssEmgDetailDTO.partGrade'].value != "") 
		{
			findStockQty();
		}
	}
}

function findStockQty()
{
	var actionUrl = contextPath + "/maPtIssEmgDetail.do";
	
	params = 'strutsAction='+'<%=MaPtIssEmgDetailAction.PTISSEMG_FIND_STOCK_QTY%>'
			+'&maPtIssEmgDetailDTO.wcodeId='+maPtIssEmgDetailForm.elements['maPtIssEmgDetailDTO.wcodeId'].value
			+'&maPtIssEmgDetailDTO.partId='+maPtIssEmgDetailForm.elements['maPtIssEmgDetailDTO.partId'].value
			+'&maPtIssEmgDetailDTO.partGrade='+maPtIssEmgDetailForm.elements['maPtIssEmgDetailDTO.partGrade'].value;
	
	XMLHttpPost(actionUrl, params, 'afterFindStockQty');
}

function afterFindStockQty(ajaxXmlDoc)
{
	var rtnValue = parseXmlDoc(ajaxXmlDoc, 'DESC');
	
	maPtIssEmgDetailForm.elements['maPtIssEmgDetailDTO.stockQty'].value = rtnValue[0];
}


//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

	<html:form action="/maPtIssEmgDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maPtIssEmgCommonDTO.compNo" />
	<html:hidden property="maPtIssEmgCommonDTO.ptemgisslistId" />
	
    <html:hidden property="maPtIssEmgDetailDTO.wcodeId" />
    <html:hidden property="maPtIssEmgDetailDTO.partId" />
    <html:hidden property="maPtIssEmgDetailDTO.partGrade" />
    <html:hidden property="maPtIssEmgDetailDTO.wkorId" />
    <html:hidden property="maPtIssEmgDetailDTO.wopartId" />
    <html:hidden property="maPtIssEmgDetailDTO.ptemgissStatus" />
    <html:hidden property="maPtIssEmgDetailDTO.ptemgTaskStatus" />
    <html:hidden property="maPtIssEmgDetailDTO.ptissType" />
    <html:hidden property="maPtIssEmgDetailDTO.issueBy" />
<%--     <html:hidden property="maPtIssEmgDetailDTO.wcodeId" /> --%>
    <html:hidden property="maPtIssEmgDetailDTO.toWcodeId" />
    <html:hidden property="maPtIssEmgDetailDTO.issueDept" />
    <html:hidden property="maPtIssEmgDetailDTO.recBy" />
    <html:hidden property="maPtIssEmgDetailDTO.ctCtrId" />
    <html:hidden property="maPtIssEmgDetailDTO.ptEmgIssReqStatus" />
    <html:hidden property="maPtIssEmgDetailDTO.erpYyyy" />
    <html:hidden property="maPtIssEmgDetailDTO.erpIssNo" />
    <html:hidden property="maPtIssEmgDetailDTO.erpBudat" />
    
    <div class="article_box" id="detailBox">
        <div class="form_box">
        	 <!-- 출고번호 -->
        	 <div class="field">
        	 	<label><bean:message key="LABEL.ptIssListId"/></label>
        	 	<div class="input_read">
        	 		<html:text property="maPtIssEmgDetailDTO.ptemgisslistId" tabindex="10" readonly="true"/>
        	 	</div>
        	 </div>
        	 <!-- 출고상태 -->
        	 <div class="field">
        	 	<label><bean:message key="LABEL.issStatus"/></label>
        	 	<div class="input_read">
        	 		<html:text property="maPtIssEmgDetailDTO.ptemgissStatusDesc" tabindex="20" readonly="true"/>
        	 	</div>
        	 </div>
        	 <!-- 출고일자 -->
        	 <div class="field">
        	 	<label class="check"><bean:message key="LABEL.issDate"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPtIssEmgDetailDTO.issueDate" tabindex="30"/>
        	 		<p class="open_calendar"><span>날짜</span></p>
        	 	</div>
        	 </div>
        	 <!-- 출고창고 -->
        	 <div class="field">
        	 	<label class="check"><bean:message key="LABEL.fromWname"/></label>
        	 	<div id="wnameDiv" class="input_box">
        	 		<html:text property="maPtIssEmgDetailDTO.wname" tabindex="40"/>
                    <p id="wnameSchBtn" class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>    
                    </p>
        	 	</div>
        	 </div>
        	 <!-- 부품번호 -->
        	 <div class="field">
        	 	<label class="check"><bean:message key="LABEL.partNo"/></label>
                <div id="partNoDiv" class="input_box">
                    <html:text property="maPtIssEmgDetailDTO.partNo" tabindex="50"/>
                    <p id="partNoSchBtn" class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
        	 </div>
        	 <!-- 보관창고 -->
        	 <div class="field">
        	 	<label class="check"><bean:message key="LABEL.toWname"/></label>
        	 	<div id="wnameDiv" class="input_box">
        	 		<html:text property="maPtIssEmgDetailDTO.toWname" tabindex="40"/>
                    <p id="wnameSchBtn" class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>    
                    </p>
        	 	</div>
        	 </div>
        	 
        	 <!-- 부품명 -->
			 <div class="field">
				<label><bean:message key="LABEL.partNameSize"/></label>
        	 	<div class="input_read">
        	 		<html:text property="maPtIssEmgDetailDTO.partDesc" readonly="true" tabindex="70"/>
        	 	</div>
			 </div>
			 
        	 <!-- 자재등급 -->
			 <div class="field">
				<label class="check"><bean:message key="LABEL.partGrade"/></label>
				<div class="input_box">
					<html:text property="maPtIssEmgDetailDTO.partGradeDesc" tabindex="60" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			 </div>
        	 
			 <!-- 사용수량 -->
        	 <div class="field">
        	 	<label class="check"><bean:message key="LABEL.useQty"/></label>
        	 	<div class="input_box">
        	 		<html:text property="maPtIssEmgDetailDTO.issueQty" tabindex="80" styleClass="num"/>
        	 	</div>
        	 </div>
			 <!-- 현재고 -->
        	 <div class="field">
        	 	<label><bean:message key="LABEL.stockQty"/></label>
        	 	<div class="input_read">
        	 		<html:text property="maPtIssEmgDetailDTO.stockQty" tabindex="85" readonly="true" styleClass="num"/>
        	 	</div>
        	 </div>
        	 <!-- 출고부서 -->
        	 <div class="field">
        	 	<label><bean:message key="LABEL.issDept"/></label>
        	 	<div class="input_read">
        	 		<html:text property="maPtIssEmgDetailDTO.issueDeptDesc" readonly="true" tabindex="90"/>
        	 	</div>
        	 </div>
			 <!-- 수령자 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.recBy"/></label>
				<div class="input_box">
					<html:text property="maPtIssEmgDetailDTO.recByName" tabindex="100"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 출고담당자 -->
			<div class="field">
				<label><bean:message key="LABEL.issIncharge"/></label>
				<div class="input_read">
					<html:text property="maPtIssEmgDetailDTO.issueByName" tabindex="110" />
				</div>
			</div>
			
			<!-- Cost Center명 -->
			 <div class="field">
				<label class="check"><bean:message key="LABEL.ctctrName"/></label>
				<div class="input_box">
					<html:text property="maPtIssEmgDetailDTO.ctCtrDesc" tabindex="115"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			 </div>

        	 <!-- 작업명 -->
			 <div class="field_long">
				<label><bean:message key="LABEL.woName"/></label>
				<div class="input_read">
					<html:text property="maPtIssEmgDetailDTO.woDesc" tabindex="120" readonly="true" />
				</div>
			 </div>
			 <!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="maPtIssEmgDetailDTO.remark" styleClass="ta50" tabindex="130" />
				</div>
			</div>
			<c:set var="filePath" value="enhance/${compName}/part/iss/emg/list/maPtIssEmgDetail_${compNo}.jsp" />
			<c:if test="${udf:isExist(filePath)}">
				<c:import charEncoding="UTF-8" url="/enhance/${compName}/part/iss/emg/list/maPtIssEmgDetail_${compNo}.jsp"></c:import>
			</c:if>
        </div> <!-- End of Form_box -->
    </div> <!-- End of Article_box -->
</html:form>
            
</body>
</html:html>
<%--===========================================================================
구매출고 - 상세
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
<%@ page import="dream.tool.iss.rent.action.MaPttIssDetailAction"%>
<%@ page import="common.bean.User"%>
<%
    User loginUser = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html:html>
<head>
<!-- 구매출고 : 품번 -->
<title><bean:message key='LABEL.ptNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
var partNameAc,wareHouseAc,deptAc,mainMngAc,accdeptAc;
function loadPage() 
{
	chkStatus();
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goUpdate(); 
	}
	
	setTitle("maPttIssDetailDTO.partNo", "maPttIssDetailDTO.partDesc");
	
	setForUpdate();
	
	setFunction();
}

function setFunction()
{
	partNameAc = new autoC({"maPttIssDetailDTO.partNo":"part_no"});
    partNameAc.setAcConditionMap({
	   "part_categ":"TOOL",
	   "comp_no":loginUser.compNo
	   });
    partNameAc.setAcDConditionMap({
    	"wcode_id" : "maPttIssDetailDTO.wcodeId"
    });
    partNameAc.setTable("TAPARTS");
    partNameAc.setAcResultMap({
        "maPttIssDetailDTO.partDesc":"partNameSize"
        ,"maPttIssDetailDTO.partId":"part_id"
    })
    partNameAc.init();
    
    wareHouseAc = new autoC({"maPttIssDetailDTO.wname":"wname"});
    wareHouseAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"wh_categ":"TOOL"
  	   });
    wareHouseAc.setTable("TAWAREHOUSE");
    wareHouseAc.setAcResultMap({
        "maPttIssDetailDTO.wcodeId":"wcode_id"
    });
    wareHouseAc.init();
    
	deptAc = new autoC({"maPttIssDetailDTO.issueDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maPttIssDetailDTO.issueDept":"dept_id"
    });
    deptAc.init();
    
    mainMngAc = new autoC({"maPttIssDetailDTO.recByName":"emp_name"});
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    mainMngAc.setAcDConditionMap({
    	"dept_id" : "maPttIssDetailDTO.recDept"
    });
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcResultMap({
        "maPttIssDetailDTO.recBy":"emp_id"
    });
    mainMngAc.init();

    accdeptAc = new autoC({"maPttIssDetailDTO.recDeptDesc":"description"});
    accdeptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    accdeptAc.setTable("TADEPT");
    accdeptAc.setAcResultMap({
        "maPttIssDetailDTO.recDept":"dept_id"
    });
    accdeptAc.init();
    
}

/**
 * Check Status 
 */
function chkStatus()
{
	var ptissStatus = maPttIssDetailForm.elements['maPttIssDetailDTO.ptissStatus'].value;
	 //완료!
	if(ptissStatus=="C")
	{
		setDisableAll();

		//출고취소버튼 살리기
		setTimeout("$('.b_canceliss').show();",200);
	}
/* 	else if(ptissStatus == "X")
	{
		setDisableAll();
	} */
	else{
		setEnableAll();
		
		//출고취소버튼 죽임
		$('.b_canceliss').hide();
	}
}
	
function goUpdate()
{
    //수정시 readonly설정 
    //maPttIssDetailForm.elements['maPttIssDetailDTO.prRecListNo'].readOnly = true;
    //document.getElementById("ptRecListNoDiv").className = "input_read";  
    // 버튼 활성화 
    var ptissStatus = maPttIssDetailForm.elements['maPttIssDetailDTO.ptissStatus'].value;

    if(ptissStatus == "W") // 작성중 
    {
	    $(document).find('.b_rec_complete').show();
	    $(document).find('.b_rec_cancel').hide();
	    $(document).find('.b_save').show();

	    // 입력 Form disable
        setEnable($(".form_box"));
    }
    else if(ptissStatus == "C") // 출고완료 
    {
        $(document).find('.b_rec_complete').hide();
        $(document).find('.b_rec_cancel').show();
        $(document).find('.b_save').hide();

        // 입력 Form disable
        setDisable($(".form_box"));
    }
    else
   	{
    	$(document).find('.b_save').show();
   	}
}

function goInput()
{ 
	sequenceNextVal('SQAPTISSLIST_ID');
	
	maPttIssDetailForm.elements['maPttIssDetailDTO.issueDate'].value = getToday(); 
	maPttIssDetailForm.elements['maPttIssDetailDTO.issueDept'].value = loginUser.deptId;
    maPttIssDetailForm.elements['maPttIssDetailDTO.issueDeptDesc'].value = loginUser.deptDesc;

    //maPttIssDetailForm.elements['maPttIssDetailDTO.wcodeId'].value = loginUser.twcodeId;
    //maPttIssDetailForm.elements['maPttIssDetailDTO.wname'].value = loginUser.twname;
	//maPttIssDetailForm.elements['maPttIssDetailDTO.recQty'].value = "0"; 
	//maPttIssDetailForm.elements['maPttIssDetailDTO.unitPrice'].value = "0"; 
	//maPttIssDetailForm.elements['maPttIssDetailDTO.totPrice'].value = "0"; 

	// 출고상태 : W=작성중
	maPttIssDetailForm.elements['maPttIssDetailDTO.ptissStatus'].value = "W"; 
	valSysDirCode('maPttIssDetailDTO.ptissStatus', 'maPttIssDetailDTO.ptissStatusDesc', 'PTISS_STATUS', true);

	// 버튼 비활성화 
/* 	$(document).find('.b_rec_complete').hide(); */
	$(document).find('.b_confirmiss').hide();
	$(document).find('.b_canceliss').hide();
}

function setSequenceVal(sequenceVal)
{
	maPttIssDetailForm.elements['maPttIssDetailDTO.ptIssListId'].value = sequenceVal;
}

/**
 * 저장
 */ 
function goSave()
{
	// 출고완료된 정보일 경우 "저장" 불가.
	if(!ckCreate(currentPageId) 
			&& maPttIssDetailForm.elements['maPttIssDetailDTO.ptissStatus'].value == 'C') 
	{
		alertMessage1("<bean:message key='MESSAGE.MSG0012' />");
		return;
	}
		
	//================================
    // 필수 항목 체크한다.
    //================================
    if(checkValidation()) return;
    
	//strutsAction 셋팅.
	if(ckCreate(currentPageId)) maPttIssDetailForm.strutsAction.value = '<%=MaPttIssDetailAction.PTISS_DETAIL_INPUT%>';
	else maPttIssDetailForm.strutsAction.value = '<%=MaPttIssDetailAction.PTISS_DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/maPttIssDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maPttIssDetailForm), 'afterSave');
}

 /**
  * 저장후 호출
  */
function afterSave(ajaxXmlDoc)
{
	//=====================================
	if (!checkHttpXml(ajaxXmlDoc)) return;
	//=====================================
	maPttIssDetailForm.elements['maPttIssCommonDTO.ptIssListId'].value = maPttIssDetailForm.elements['maPttIssDetailDTO.ptIssListId'].value;
	if (parent.findGridRow)	parent.findGridRow(maPttIssDetailForm.elements['maPttIssCommonDTO.ptIssListId'].value);
    
	getTopPage().afterSaveAll(currentPageId);
	
	goUpdate();
	setTitle("maPttIssDetailDTO.partNo", "maPttIssDetailDTO.partDesc");
	
	chkStatus();
	$(document).find('.b_confirmiss').show();
	$(document).find('.b_canceliss').show();
}
 


/**
 * 출고완료 처리 
 */
function goConfirmiss()
{
	if(checkIsUpdate(document)){
		 alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
	 }else{
		 getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0053'/>", function(result){
			 if(result){
				//================================
				// 필수 항목 체크한다.
				//================================
				if(checkValidation()) return;

				 maPttIssDetailForm.strutsAction.value = '<%=MaPttIssDetailAction.PTISS_ISSUE%>';
				 var actionUrl = contextPath + "/maPttIssDetail.do";
					XMLHttpPost(actionUrl, FormQueryString(maPttIssDetailForm), 'afterConfirm');
			 }
			});
	 }
}

function afterConfirm(ajaxXmlDoc)
{
	var rtnValue = parseXmlDoc(ajaxXmlDoc, 'DESC');
	//0:성공여부 (S :성공), 1: ERP 문서번호 , 2:출고년도, 3:전기일 
	if("S" == rtnValue[0])
	{

		//출고 처리했습니다.
		alertMessage1("<bean:message key='MESSAGE.MSG0066'/>");
		
		//완료!
		maPttIssDetailForm.elements['maPttIssDetailDTO.ptissStatus'].value = "C";
		valSysDirCode('maPttIssDetailDTO.ptissStatus', 'maPttIssDetailDTO.ptissStatusDesc', 'PTISS_STATUS', true);
		chkStatus();

		
		if (parent.findGridRow)	parent.findGridRow(maPttIssDetailForm.elements['maPttIssCommonDTO.ptIssListId'].value);
	}
	else
	{
		//출고에 문제가 있습니다. 사이트 관리자에게 문의하세요.
		alertMessage1("<bean:message key='MESSAGE.MSG0065'/>");
	}
}

/**
 * 출고취소
 */
function goCanceliss()
{
	if(checkIsUpdate(document)){
		 alertMessage1("<bean:message key='MESSAGE.MSG0033'/>");
	 }else{
		 getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0054'/>", function(result){
			 if(result){
				//================================
				// 필수 항목 체크한다.
				//================================
				if(checkValidation()) return;

				 maPttIssDetailForm.strutsAction.value = '<%=MaPttIssDetailAction.PTISS_CANCEL_ISSUE%>';
				 var actionUrl = contextPath + "/maPttIssDetail.do";
					XMLHttpPost(actionUrl, FormQueryString(maPttIssDetailForm), 'afterCancel');
			 }
			});
	 }
}

function afterCancel(ajaxXmlDoc)
{
	var rtnValue = parseXmlDoc(ajaxXmlDoc, 'DESC');
	//0:성공여부 (S :성공), 1: ERP 문서번호 , 2:출고년도
	if("S" == rtnValue[0])
	{
		//출고 취소했습니다.
		alertMessage1("<bean:message key='MESSAGE.MSG0064'/>");

		//취소!
		maPttIssDetailForm.elements['maPttIssDetailDTO.ptissStatus'].value = "X";
		valSysDirCode('maPttIssDetailDTO.ptissStatus', 'maPttIssDetailDTO.ptissStatusDesc', 'PTISS_STATUS', true);
		chkStatus();

		
		if (parent.findGridRow)	parent.findGridRow(maPttIssDetailForm.elements['maPttIssCommonDTO.ptIssListId'].value);
	}
	else
	{
		//출고에 문제가 있습니다. 사이트 관리자에게 문의하세요.
		alertMessage1("<bean:message key='MESSAGE.MSG0065'/>");
	}
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maPttIssDetailForm.elements['maPttIssDetailDTO.ptIssListId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maPttIssDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maPttIssCommonDTO.ptIssListId" />
	<html:hidden property="maPttIssDetailDTO.ptissStatus" />
	<html:hidden property="maPttIssDetailDTO.partId" />
	<html:hidden property="maPttIssDetailDTO.issueDept" />
	<html:hidden property="maPttIssDetailDTO.recDept" />
	<html:hidden property="maPttIssDetailDTO.wcodeId" />
	<html:hidden property="maPttIssDetailDTO.recBy" />

	<div class="article_box">
		<div class="form_box">
			<div class="field">
				<label class="check"><bean:message key="LABEL.ptIssListId"/></label>
				<div id="ptRecListNoDiv" class="input_read">
					<html:text property="maPttIssDetailDTO.ptIssListId" tabindex="10" readonly="true"/>
				</div>
			</div>
			<div class="field">
				<label><bean:message key="LABEL.issStatus"/></label>
				<div class="input_read">
					<html:text property="maPttIssDetailDTO.ptissStatusDesc" tabindex="20" readonly="true" />
				</div>
			</div>
            <div class="field">
                <label class="check"><bean:message key="LABEL.issDate"/></label>
                <div class="input_box">
                    <html:text property="maPttIssDetailDTO.issueDate" tabindex="100" />
                    <p class="open_calendar"><span>날짜</span></p>
                </div>
            </div>			
            <div class="field">
               <label class="check"><bean:message key="LABEL.wname"/></label>
               <div id="wnameDiv" class="input_box">
                   <html:text property="maPttIssDetailDTO.wname" tabindex="65"/>
                   <p id="wnameSchBtn" class="open_spop">
                       <a>
                           <span>조회</span>
                       </a>    
                   </p>
               </div> 
             </div>   
			<div class="field">
                <label class="check"><bean:message key="LABEL.ptNo"/></label>
                <div id="partNoDiv" class="input_box">
                    <html:text property="maPttIssDetailDTO.partNo" tabindex="40"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
             </div>
            <div class="field">
                <label><bean:message key="LABEL.issDept"/></label>
                <div class="input_box">
                    <html:text property="maPttIssDetailDTO.issueDeptDesc" tabindex="50"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>			
            <div class="field_long">
                <label><bean:message key="LABEL.ptNameSize"/></label>
                <div class="input_read">
                    <html:text property="maPttIssDetailDTO.partDesc" tabindex="60" readonly="true"/>
                </div>
            </div>
                       <div class="field">
                <label class="check"><bean:message key="LABEL.issQty"/></label>
                <div class="input_box">
                    <html:text property="maPttIssDetailDTO.issueQty" tabindex="70"  
                         styleClass="num"/>
                </div>
             </div>  
            <div class="field">
                <label class="check"><bean:message key="LABEL.recBy"/></label>
                <div class="input_box">
                    <html:text property="maPttIssDetailDTO.recByName" tabindex="68"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>     
            <div class="field">
                <label class="check"><bean:message key="LABEL.recDept"/></label>
                <div class="input_box">
                    <html:text property="maPttIssDetailDTO.recDeptDesc" tabindex="50"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
            </div>  
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="maPttIssDetailDTO.remark" styleClass="ta50" tabindex="190" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
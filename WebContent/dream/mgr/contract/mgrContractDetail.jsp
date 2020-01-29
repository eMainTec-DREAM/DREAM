<%--===========================================================================
사용달력설정- 상세
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
<%@ page import="dream.mgr.contract.action.MgrContractAction"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<html:html>
<head>
<!--설비위치 -->
<title><bean:message key='LABEL.contractNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">

var vendorDescAc;

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else 
	{
		goUpdate();
	}
	
	setTitle("mgrContractDTO.contractNo","mgrContractDTO.contractDesc");
	//For Save
	setForUpdate();
	
	// 거래처
    vendorDescAc = new autoC({"mgrContractDTO.vendorNo":"vendor_no"});
    vendorDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_use":"Y"
    });
    vendorDescAc.setTable("TAVENDOR");
    vendorDescAc.setKeyName("mgrContractDTO.vendorId");
    vendorDescAc.setAcResultMap({
        "mgrContractDTO.vendorId":"vendor_id"
        ,"mgrContractDTO.vendorDesc":"description"
    });
    vendorDescAc.init();
    
	// 사용여부
    acSysDesc("mgrContractDTO.isUse","mgrContractDTO.isUse","IS_USE", true);
	
}

function goInput()
{
	sequenceNextVal('SQACONTRACT_ID');
	
	mgrContractForm.elements['mgrContractDTO.isUse'].value = 'Y';
}

function setSequenceVal(sequenceVal)
{
	mgrContractForm.elements['mgrContractDTO.contractId'].value = sequenceVal;
	mgrContractForm.elements['mgrContractDTO.contractNo'].value = sequenceVal;
}

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
	if(ckCreate(currentPageId)) mgrContractForm.strutsAction.value = "<%=MgrContractAction.DETAIL_INPUT%>";
	else mgrContractForm.strutsAction.value = "<%=MgrContractAction.DETAIL_UPDATE%>";
	
	var actionUrl = contextPath + "/mgrContractDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(mgrContractForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    
    if (parent.findGridRow)	parent.findGridRow(mgrContractForm.elements['mgrContractDTO.contractId'].value);
    getTopPage().afterSaveAll(currentPageId);
}

function goTabPage(pageId)
{
	var form = document.mgrContractForm;

	if(pageId == "maDocLibList" || pageId == "maPtDocLibList")
	{	
		form.elements['maDocLibCommonDTO.objectId'].value = form.elements['mgrContractDTO.contractId'].value;
		form.elements['maDocLibCommonDTO.objectType'].value = "CONTRACT_LIST";  //CONTRACT_LIST docDesc
		form.elements['maDocLibCommonDTO.description'].value = form.elements['mgrContractDTO.contractDesc'].value;
		goCommonTabPage(form, '<%=MaDocLibListAction.BASE_QUICK_SEARCH%>' , pageId);
	}
	else
		goCommonTabPage(form, '' , pageId);
    
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/mgrContractDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="mgrContractDTO.contractId" />
	<html:hidden property="mgrContractDTO.vendorId" />
 	<html:hidden property="maDocLibCommonDTO.objectId" /> <!-- 첨부문서 Key -->
 	<html:hidden property="maDocLibCommonDTO.objectType" />
 	<html:hidden property="maDocLibCommonDTO.description" />
	<div class="article_box" id="detailBox">
		<div class="form_box">
			<!-- 계약# -->
			<div class="field">
				<label class="check">계약#</label>
				<div class="input_read">
					<html:text property="mgrContractDTO.contractNo" readonly="true"/>
				</div>
			</div>
			<!-- 사용여부 -->
			<div class="field">
				<label class="check">사용여부</label>
				<div class="input_box">
					<html:text property="mgrContractDTO.isUse" tabindex="20"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 계약명 -->
			<div class="field_long">
				<label class="check">계약명</label>
				<div class="input_box">
					<html:text property="mgrContractDTO.contractDesc"  tabindex="30"/>
				</div>
			</div>
			<!-- 업체#  -->
            <div class="field">
                <label>업체#</label>
                <div class="input_box">
					<html:text property="mgrContractDTO.vendorNo" tabindex="40" />
					<p class="open_spop"><a><span>조회</span></a></p>
                </div>
            </div>
			<!-- 업체명 -->
			<div class="field">
				<label>업체명</label>
				<div class="input_read">
					<html:text property="mgrContractDTO.vendorDesc" readonly="true"/>
				</div>
			</div>
			<!-- 계약일자  -->
            <div class="field">
                <label>계약일자</label>
                <div class="input_box">
                    <html:text property="mgrContractDTO.contractDate" tabindex="50"/>
                    <p class="open_calendar"><a><span>조회</span></a></p>
                </div>
            </div>
			<!-- 계약금액  -->
            <div class="field">
                <label>계약금액</label>
                <div class="input_box">
                    <html:text property="mgrContractDTO.contractAmount" tabindex="60" styleClass="num"/>
                </div>
            </div>
            <!-- 계약기간 -->
            <div class="field">
                <label>계약기간</label>
                <div class="calendar_wrap">
                    <div class="input_box input_carendar">
                        <html:text property="mgrContractDTO.contractStartDate" tabindex="70" />
                        <p class="open_calendar"><span>날짜</span></p>
                    </div>
                    <div class="input_box input_carendar">
                        <html:text property="mgrContractDTO.contractEndDate" tabindex="80" />
                        <p class="open_calendar"><span>날짜</span></p>
                    </div>
                </div>
            </div>
			<!-- 비고 -->
			<div class="field_long">
				<label>비고</label>
				<div class="input_box">
					<html:textarea property="mgrContractDTO.remark" styleClass="ta50" tabindex="90" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>

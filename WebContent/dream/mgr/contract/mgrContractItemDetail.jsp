<%--===========================================================================
가동시간설정 - 단가계약설정 상세
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%> 
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.mgr.contract.action.MgrContractItemAction"%>
<html>
<head>
<!-- 계약 Item-->
<title><bean:message key="PAGE.mgrContractItemDetail"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var serviceAc;

function loadPage() 
{
	if(ckCreate(currentPageId))
		goInput();
	
	setTitle("mgrContractItemDTO.serviceNo","mgrContractItemDTO.contractItemDesc");
	
	setForUpdate();
	
	//서비스명
    serviceAc = new autoC({"mgrContractItemDTO.serviceNo":"serviceNo"});
    serviceAc.setTable("TASERVICE");
    serviceAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    	,"is_use":"Y"
    });
    serviceAc.setKeyName("mgrContractItemDTO.serviceId");
    serviceAc.setAcResultMap({
        "mgrContractItemDTO.serviceId":"serviceId"
    });
    serviceAc.init();    
    
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQACONTRACTITEM_ID');
	mgrContractItemForm.elements['mgrContractItemDTO.contractId'].value = mgrContractItemForm.elements['mgrContractDTO.contractId'].value;
	
	mgrContractItemForm.elements['mgrContractItemDTO.contractAmount'].value = $(searchPage("mgrContractDetail").document).find("[name='mgrContractDTO.contractAmount']").val();
}

function setSequenceVal(sequenceVal)
{
	mgrContractItemForm.elements['mgrContractItemDTO.contractItemId'].value = sequenceVal;
}

function goSave(){
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)) mgrContractItemForm.strutsAction.value = '<%=MgrContractItemAction.DETAIL_INPUT%>';
	else mgrContractItemForm.strutsAction.value = '<%= MgrContractItemAction.DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/mgrContractItemDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(mgrContractItemForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    	
    if (parent.findGridRow)	parent.findGridRow(mgrContractItemForm.elements['mgrContractItemDTO.contractItemId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
    
    var contractAmt = parseInt(intToData(mgrContractItemForm.elements['mgrContractItemDTO.contractAmount'].value));
    var amt = parseInt(intToData(mgrContractItemForm.elements['mgrContractItemDTO.amount'].value));
    var rslt = (contractAmt + amt);
    
    $(searchPage("mgrContractDetail").document).find("[name='mgrContractDTO.contractAmount']").val(rslt);
    $(searchPage("mgrContractDetail").document).find("[name='mgrContractDTO.contractAmount']").focus();
    
    getTopPage().updateArray[parent.parent.currentPageId] = "AUTH";
    
    setTitle("mgrContractItemDTO.serviceNo","mgrContractItemDTO.contractItemDesc");
}

function sumTotPrice()
{
	 var unitPrice = mgrContractItemForm.elements['mgrContractItemDTO.unitPrice'].value;
	 var qty = mgrContractItemForm.elements['mgrContractItemDTO.qty'].value;

	 var result = intToData(qty) * intToData(unitPrice);
	 mgrContractItemForm.elements['mgrContractItemDTO.amount'].value = result;
	 setMoneyFormat(mgrContractItemForm.elements['mgrContractItemDTO.amount'], "3");
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/mgrContractItemDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="mgrContractDTO.contractId"/><!-- Key -->
	<html:hidden property="mgrContractItemDTO.contractItemId"/>
	<html:hidden property="mgrContractItemDTO.contractId"/>
	<html:hidden property="mgrContractItemDTO.serviceId"/>
	<html:hidden property="mgrContractItemDTO.contractAmount"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 서비스 # -->
			<div class="field">
				<label>서비스 #</label>
				<div class="input_box">
					<html:text property="mgrContractItemDTO.serviceNo" tabindex="10"/>
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 조회순서 -->
			<div class="field">
				<label>조회순서</label>
				<div class="input_box">
					<html:text property="mgrContractItemDTO.ordNo" tabindex="20"/>
				</div>
			</div>
			<!-- Item 명 -->
			<div class="field_long">
				<label>Item 명</label>
				<div class="input_box">
					<html:text property="mgrContractItemDTO.contractItemDesc" tabindex="30"/>
				</div>
			</div>
			<!-- 수량 -->
			<div class="field">
				<label>수량</label>
				<div class="input_box">
					<html:text property="mgrContractItemDTO.qty" tabindex="40"
						onblur="javascript:sumTotPrice();" styleClass="num"/>
				</div>
			</div>
			<!-- 단가 -->
			<div class="field">
				<label>단가</label>
				<div class="input_box">
					<html:text property="mgrContractItemDTO.unitPrice" tabindex="50" 
						onblur="javascript:sumTotPrice();" styleClass="num"/>
				</div>
			</div>
			<!-- 금액 -->
			<div class="field">
				<label>금액</label>
				<div class="input_box">
					<html:text property="mgrContractItemDTO.amount" tabindex="60" styleClass="num"/>
				</div>
			</div>
			<!-- 단위 -->
			<div class="field">
				<label>단위</label>
				<div class="input_box">
					<html:text property="mgrContractItemDTO.uom" tabindex="70"/>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label>비고</label>
				<div class="input_box">
					<html:textarea property="mgrContractItemDTO.remark" styleClass="ta50" tabindex="900" />
				</div>
			</div>
		</div><!--article_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>
<%--===========================================================================
서비스작업 - 상세
author  nhkim8548
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
<%@page import="dream.work.plan.service.action.WoPlanServiceAction"%>
<html>
<head>
<title><bean:message key="PAGE.woPlanServiceDetail"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
<script language="javascript">
var serviceAc;
var contractAc;
var vendorAc;

function loadPage() 
{
	if(ckCreate(currentPageId))
		goInput();
	
	setTitle("woPlanServiceDTO.serviceNo","woPlanServiceDTO.serviceDesc");
	
	setForUpdate();
	
	// 서비스 AC
    serviceAc = new autoC({"woPlanServiceDTO.serviceNo":"serviceNo"});
    serviceAc.setTable("TASERVICE");
    serviceAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    	,"is_use":"Y"
    });
    serviceAc.setKeyName("woPlanServiceDTO.serviceId");
    serviceAc.setAcResultMap({
        "woPlanServiceDTO.serviceId":"serviceId"
      	,"woPlanServiceDTO.serviceDesc":"serviceName"
    });
    serviceAc.init();  
    
  	// 계약 AC
    contractAc = new autoC({"woPlanServiceDTO.contractNo":"contractNo"});
    contractAc.setTable("TACONTRACT");
    contractAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    	,"is_use":"Y"
    });
    contractAc.setKeyName("woPlanServiceDTO.contractId");
    contractAc.setAcResultMap({
        "woPlanServiceDTO.contractId":"contractId"
      	,"woPlanServiceDTO.contractDesc":"contractDesc"
    });
    contractAc.init();  
    
 	// 거래처 AC
    vendorAc = new autoC({"woPlanServiceDTO.vendorNo":"vendor_no"});
    vendorAc.setTable("TAVENDOR");
    vendorAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    	,"is_use":"Y"
    });
    vendorAc.setKeyName("woPlanServiceDTO.vendorId");
    vendorAc.setAcResultMap({
        "woPlanServiceDTO.vendorId":"vendor_id"
      	,"woPlanServiceDTO.vendorDesc":"description"
    });
    vendorAc.init(); 
    
 // 단위
    acSysDesc("woPlanServiceDTO.uomDesc","woPlanServiceDTO.uomId","SERVICE_UOM", true);
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAWOPLANSERVICE_ID');

	setInitVal("woPlanServiceDTO.woPlanServiceId", woPlanServiceForm.elements['woPlanCommonDTO.wkOrId'].value);
}

function setSequenceVal(sequenceVal)
{
	woPlanServiceForm.elements['woPlanServiceDTO.woPlanServiceId'].value = sequenceVal;
}

function goSave(){
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId))
		woPlanServiceForm.strutsAction.value = '<%=WoPlanServiceAction.DETAIL_INSERT%>';
	else
		woPlanServiceForm.strutsAction.value = '<%= WoPlanServiceAction.DETAIL_UPDATE%>';
	
	var actionUrl = contextPath + "/woPlanServiceDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(woPlanServiceForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(woPlanServiceForm.elements['woPlanServiceDTO.woPlanServiceId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
    
    setTitle("woPlanServiceDTO.serviceNo", "woPlanServiceDTO.serviceDesc");
}

function sumTotPrice()
{
	var unitPrice = woPlanServiceForm.elements['woPlanServiceDTO.unitPrice'].value;
	var qty = woPlanServiceForm.elements['woPlanServiceDTO.planWorkTime'].value;

	var result = intToData(qty) * intToData(unitPrice);
	woPlanServiceForm.elements['woPlanServiceDTO.amt'].value = result;
	
	setMoneyFormat(woPlanServiceForm.elements['woPlanServiceDTO.amt'], "3");
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/woPlanServiceDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="woPlanCommonDTO.wkOrId" /><!-- key -->
	<html:hidden property="woPlanServiceDTO.woPlanServiceId"/><!-- Key -->
	<html:hidden property="woPlanServiceDTO.wkOrId"/>
	<html:hidden property="woPlanServiceDTO.serviceId"/>
	<html:hidden property="woPlanServiceDTO.contractId"/>
	<html:hidden property="woPlanServiceDTO.vendorId"/>
	<html:hidden property="woPlanServiceDTO.uomId"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 순서 -->
			<div class="field">
				<label>순서</label>
				<div class="input_box">
					<html:text property="woPlanServiceDTO.ordNo" tabindex="10"/>
				</div>
			</div>
			<!-- 작업명 -->
			<div class="field_long">
				<label>작업명</label>
				<div class="input_box">
					<html:text property="woPlanServiceDTO.woDesc" tabindex="20"/>
				</div>
			</div>
			<!-- 서비스 # -->
			<div class="field">
				<label>서비스 #</label>
				<div class="input_box">
					<html:text property="woPlanServiceDTO.serviceNo" tabindex="30"/>
					<p class="open_spop">
						<a><span>조회</span></a>
					</p>
				</div>
			</div>
			<!-- 서비스 명 -->
			<div class="field">
				<label>서비스 명</label>
				<div class="input_box">
					<html:text property="woPlanServiceDTO.serviceDesc" tabindex="40"/>
				</div>
			</div>
			<!-- 계약 # -->
			<div class="field">
				<label>계약 #</label>
				<div class="input_box">
					<html:text property="woPlanServiceDTO.contractNo" tabindex="50"/>
					<p class="open_spop">
						<a><span>조회</span></a>
					</p>
				</div>
			</div>
			<!-- 계약 명 -->
			<div class="field">
				<label>계약 명</label>
				<div class="input_box">
					<html:text property="woPlanServiceDTO.contractDesc" tabindex="60"/>
				</div>
			</div>
			<!-- 거래처 # -->
			<div class="field">
				<label>거래처 #</label>
				<div class="input_box">
					<html:text property="woPlanServiceDTO.vendorNo" tabindex="70"/>
					<p class="open_spop">
						<a><span>조회</span></a>
					</p>
				</div>
			</div>
			<!-- 거래처 명 -->
			<div class="field">
				<label>거래처 명</label>
				<div class="input_box">
					<html:text property="woPlanServiceDTO.vendorDesc" tabindex="80"/>
				</div>
			</div>
			<!-- 작업시간 -->
			<div class="field">
				<label>작업시간</label>
				<div class="input_box">
					<html:text property="woPlanServiceDTO.planWorkTime" tabindex="90" onblur="javascript:sumTotPrice();" styleClass="num"/>
				</div>
			</div>
			<!-- 단위 -->
			<div class="field">
				<label>단위</label>
				<div class="input_box">
					<html:text property="woPlanServiceDTO.uomDesc" tabindex="100"/>
					<p class="open_spop">
						<a><span>조회</span></a>
					</p>
				</div>
			</div>
			<!-- 단가 -->
			<div class="field">
				<label>단가</label>
				<div class="input_box">
					<html:text property="woPlanServiceDTO.unitPrice" tabindex="110" onblur="javascript:sumTotPrice();" styleClass="num"/>
				</div>
			</div>
			<!-- 금액 -->
			<div class="field">
				<label>금액</label>
				<div class="input_box">
					<html:text property="woPlanServiceDTO.amt" tabindex="120" styleClass="num"/>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label>비고</label>
				<div class="input_box">
					<html:textarea property="woPlanServiceDTO.remark" styleClass="ta50" tabindex="900" />
				</div>
			</div>
		</div><!--article_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>
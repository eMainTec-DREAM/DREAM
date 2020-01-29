<%--===========================================================================
서비스 - 단가 상세
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
<%@page import="dream.work.service.action.WorkServicePriceAction"%>
<html>
<head>
<!-- 단가-->
<title><bean:message key="PAGE.workServicePriceDetail"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	if(ckCreate(currentPageId))
		goInput();
	
	setTitle("workServicePriceDTO.fromDate","workServicePriceDTO.toDate");
	
	setForUpdate();
	
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQASERVICEPRICE_ID');
	workServicePriceForm.elements['workServicePriceDTO.serviceId'].value = workServicePriceForm.elements['workServiceCommonDTO.serviceId'].value;
}

function setSequenceVal(sequenceVal)
{
	workServicePriceForm.elements['workServicePriceDTO.servicePriceId'].value = sequenceVal;
}

function goSave(){
	
	checkDate();
}

function afterCheckDate()
{
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)){
		workServicePriceForm.strutsAction.value = '<%=WorkServicePriceAction.DETAIL_INPUT%>';
	}else{
		workServicePriceForm.strutsAction.value = '<%= WorkServicePriceAction.DETAIL_UPDATE %>';
	}
	
	var actionUrl = contextPath + "/workServicePriceDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(workServicePriceForm), 'afterSave');	
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    	
    if (parent.findGridRow)	parent.findGridRow(workServicePriceForm.elements['workServicePriceDTO.servicePriceId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
    
    setTitle("workServicePriceDTO.fromDate","workServicePriceDTO.toDate");
}

function checkDate()
{
	var fromDate = workServicePriceForm.elements['workServicePriceDTO.fromDate'].value;
	var toDate = workServicePriceForm.elements['workServicePriceDTO.toDate'].value;
	
	if(""!=fromDate&&""!=toDate)
	{
		if( (onlyNum(toDate)-onlyNum(fromDate)) <0)
		{
			alertMessage1("<bean:message key='MESSAGE.CMSG012' />");
			closeModal();
		}
		else
		{
			afterCheckDate();
		}
	}
	else
	{
		afterCheckDate();
	}
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/workServicePriceDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="workServiceCommonDTO.serviceId"/><!-- Key -->
	<html:hidden property="workServicePriceDTO.servicePriceId"/>
	<html:hidden property="workServicePriceDTO.serviceId"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 적용기간 -->
			<div class="field">
				<label>적용기간</label>
                <div class="calendar_wrap">
                	<div class="input_box input_carendar">
						<html:text property="workServicePriceDTO.fromDate" readonly="true"/>
						<p class="open_mon_calendar"><span>날짜</span></p>
					</div>
					<div class="input_box input_carendar">
						<html:text property="workServicePriceDTO.toDate" readonly="true"/>
						<p class="open_mon_calendar"><span>날짜</span></p>
					</div>
				</div>
			</div>
			<!-- 단가 -->
			<div class="field">
				<label>단가</label>
				<div class="input_box">
					<html:text property="workServicePriceDTO.unitPrice" tabindex="50" styleClass="num"/>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label>비고</label>
				<div class="input_box">
					<html:textarea property="workServicePriceDTO.remark" styleClass="ta50" tabindex="900" />
				</div>
			</div>
			<div class="field_long_blank"></div>
			<div class="field_long_blank"></div>
			<div class="field_long_blank"></div>
			<div class="field_long_blank"></div>
			<div class="field_long_blank"></div>
			<div class="field_long_blank"></div>
			<div class="field_long_blank"></div>
			<div class="field_long_blank"></div>
			<div class="field_long_blank"></div>
			<div class="field_long_blank"></div>
		</div><!--article_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>
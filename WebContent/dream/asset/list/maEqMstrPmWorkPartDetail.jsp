<%--===========================================================================
설비 정기작업-부품 상세
author  kim21017
version $Id: maEqMstrPmWorkPartDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.asset.list.action.MaEqMstrPmWorkPartDetailAction"%>
<html>
<head>
<!--점검항목-->
<title><bean:message key="TAB.maEqMstrPmWorkPartDetail"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var partNoAc;

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("maEqMstrPmWorkPartDetailDTO.partNo","maEqMstrPmWorkPartDetailDTO.partDesc");
	setForUpdate();

	partNoAc = new autoC({"maEqMstrPmWorkPartDetailDTO.partNo":"full_desc"});
	partNoAc.setTable("TAPARTS");
	partNoAc.setAcConditionMap({
	   "comp_no":loginUser.compNo
	   ,"part_categ":"SPPT"
	   });
	partNoAc.setAcResultMap({
		"maEqMstrPmWorkPartDetailDTO.partNo":"part_no",
	    "maEqMstrPmWorkPartDetailDTO.partId":"part_id",
	    "maEqMstrPmWorkPartDetailDTO.partDesc":"description",
	    "maEqMstrPmWorkPartDetailDTO.partSize":"pt_size"
	});
	partNoAc.setKeyName("maEqMstrPmWorkPartDetailDTO.partId"); 
	partNoAc.init();
	
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAPM_PART_ID');
}

function setSequenceVal(sequenceVal)
{
	maEqMstrPmWorkPartDetailForm.elements['maEqMstrPmWorkPartDetailDTO.pmPartId'].value = sequenceVal;
}

function goSave(){
	if(checkValidation()) return;
	
	pmSaveAction();
}

function pmSaveAction(){
	if(ckCreate(currentPageId)) maEqMstrPmWorkPartDetailForm.strutsAction.value = '<%=MaEqMstrPmWorkPartDetailAction.EQ_MSTR_PMWORK_PART_DETAIL_INPUT%>';
	else maEqMstrPmWorkPartDetailForm.strutsAction.value = '<%= MaEqMstrPmWorkPartDetailAction.EQ_MSTR_PMWORK_PART_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maEqMstrPmWorkPartDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maEqMstrPmWorkPartDetailForm), 'afterSave');
	
}
function afterSave(data)
{
    if (!checkHttpXml(data)) return;
    
    if (parent.findGridRow)	parent.findGridRow(
    		maEqMstrPmWorkPartDetailForm.elements['maEqMstrPmWorkPartDetailDTO.pmId'].value
    		,maEqMstrPmWorkPartDetailForm.elements['maEqMstrPmWorkPartDetailDTO.pmPartId'].value);
    getTopPage().afterSaveAll(currentPageId);
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maEqMstrPmWorkPartDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="maEqMstrCommonDTO.equipId"/>
	<html:hidden property="maEqMstrPmWorkDetailDTO.pmId"/>
	<html:hidden property="maEqMstrPmWorkPartListDTO.pmId"/>
	<html:hidden property="maEqMstrPmWorkPartListDTO.pmPartId"/>
	<html:hidden property="maEqMstrPmWorkPartDetailDTO.pmId"/>
	<html:hidden property="maEqMstrPmWorkPartDetailDTO.pmPartId"/>
	<html:hidden property="maEqMstrPmWorkPartDetailDTO.partId"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 부품번호 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.ptNo"/></label>
				<div class="input_box">
					<html:text property="maEqMstrPmWorkPartDetailDTO.partNo" tabindex="10" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 부품명 -->
			<div class="field">
				<label><bean:message key="LABEL.ptDesc"/></label>
				<div class="input_read">
					<html:text property="maEqMstrPmWorkPartDetailDTO.partDesc" tabindex="20" readonly ="true" />
				</div>
			</div>
			<!-- 규격 -->
			<div class="field">
				<label><bean:message key="LABEL.ptSize"/></label>
				<div class="input_read">
					<html:text property="maEqMstrPmWorkPartDetailDTO.partSize" tabindex="30" readonly ="true" />
				</div>
			</div>
			<!-- 사용수량 -->
			<div class="field">
				<label><bean:message key="LABEL.useQty"/></label>
				<div class="input_box">
					<html:text property="maEqMstrPmWorkPartDetailDTO.useQty" tabindex="40" styleClass="num"/>
				</div>
			</div>		
		</div><!--article_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>
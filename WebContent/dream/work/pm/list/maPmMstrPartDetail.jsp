<%--===========================================================================
작업결과 투입자재
author  jung7126
version $Id: maPmMstrPartDetail.jsp,v 1.0 2015/12/04 07:26:18 jung7126 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.work.pm.list.action.MaPmMstrPartDetailAction"%>
<html>
<head>
<!-- 자재-->
<title><bean:message key="LABEL.parts"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

/** 자동완성 변성 */
var partNoAc;

function loadPage() 
{
	setTitle("maPmMstrPartDetailDTO.partNo","maPmMstrPartDetailDTO.partDesc");
	setForUpdate();
	
	partNoAc = new autoC({"maPmMstrPartDetailDTO.partNo":"part_no"});
    partNoAc.setTable("TAPARTS");
    partNoAc.setAcResultMap({
    	"maPmMstrPartDetailDTO.partDesc":"description",
    	"maPmMstrPartDetailDTO.ptSize":"pt_size",
    	"maPmMstrPartDetailDTO.model":"model",
        "maPmMstrPartDetailDTO.partId":"part_id"
    });
    partNoAc.setAcConditionMap({
   	   "comp_no":loginUser.compNo
   	   });
    partNoAc.setKeyName("maPmMstrPartDetailDTO.partId"); 
    partNoAc.init();
    
	if(ckCreate(currentPageId)) goInput();
    
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAPM_PART_ID');
	
	partNoAc.openLov();
}

function setSequenceVal(sequenceVal)
{
	maPmMstrPartDetailForm.elements['maPmMstrPartDetailDTO.pmPartId'].value = sequenceVal;
}

function goSave(){
	if(checkValidation()) return;

	if(ckCreate(currentPageId)) maPmMstrPartDetailForm.strutsAction.value = '<%=MaPmMstrPartDetailAction.PM_MSTR_PART_DETAIL_INPUT%>';
	else maPmMstrPartDetailForm.strutsAction.value = '<%= MaPmMstrPartDetailAction.PM_MSTR_PART_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maPmMstrPartDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maPmMstrPartDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    //parent.goSearch(); 
	if (parent.findGridRow)	parent.findGridRow(maPmMstrPartDetailForm.elements['maPmMstrPartDetailDTO.pmPartId'].value);
	
    getTopPage().afterSaveAll(currentPageId);
}


/* audit Trail */
function goAudtrailLink()
{
	var objectId = maPmMstrPartDetailForm.elements['maPmMstrPartDetailDTO.pmPartId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}


//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maPmMstrPartDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maPmMstrCommonDTO.pmId"/>
	<html:hidden property="maPmMstrPartDetailDTO.pmPartId"/>
	<html:hidden property="maPmMstrPartDetailDTO.partId"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 부품번호 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.partNo"/></label>
				<div class="input_box">
					<html:text property="maPmMstrPartDetailDTO.partNo" tabindex="10"/>
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 부품명 -->
			<div class="field">
				<label><bean:message key="LABEL.partDesc"/></label>
				<div class="input_read">
					<html:text property="maPmMstrPartDetailDTO.partDesc" readonly="true" />
				</div>
			</div>
			<!-- 규격 -->
			<div class="field">
				<label><bean:message key="LABEL.ptSize"/></label>
				<div class="input_read">
					<html:text property="maPmMstrPartDetailDTO.ptSize" readonly="true" />
				</div>
			</div>
			<!-- 모델 -->
			<div class="field">
				<label><bean:message key="LABEL.model"/></label>
				<div class="input_read">
					<html:text property="maPmMstrPartDetailDTO.model" readonly="true" />
				</div>
			</div>
			<!-- 사용수량 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.useQty"/></label>
				<div class="input_box">
					<html:text property="maPmMstrPartDetailDTO.useQty" tabindex="20" styleClass="num"/>
				</div>
			</div>
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>
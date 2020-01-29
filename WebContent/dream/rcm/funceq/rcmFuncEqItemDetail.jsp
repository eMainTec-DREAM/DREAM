
<%--===========================================================================
응답 상세
author  kim21017
version $Id: rcmFuncEqItemDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ page import="common.bean.User"%>
<%@ page import="dream.doc.file.action.MaDocLibListAction"%>
<%@page import="dream.rcm.funceq.action.RcmFuncEqItemDetailAction"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!--답변사항 -->
<title><bean:message key="LABEL.questionPoint"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var equipDescAc ,asmbDescAc;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("rcmFuncEqItemDetailDTO.asmbDesc");
	setForUpdate();
	
	
	equipDescAc = new autoC({"rcmFuncEqItemDetailDTO.equipDesc":"description"});
	equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
	equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcResultMap({
        "rcmFuncEqItemDetailDTO.equipId":"equip_id"
    });
    equipDescAc.setKeyName("rcmFuncEqItemDetailDTO.equipId");
    equipDescAc.init();
    
    
	asmbDescAc = new autoC({"rcmFuncEqItemDetailDTO.asmbDesc":"full_desc"});
	asmbDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo, 	   
 	   });
	asmbDescAc.setAcDConditionMap({
	 	   "equip_id":"rcmFuncEqItemDetailDTO.equipId"
	 	   });
	asmbDescAc.setTable("TAEQASMB");
	//asmbDescAc.setMultiSelect(true);
	asmbDescAc.setAcResultMap({
        "rcmFuncEqItemDetailDTO.asmbId":"eqasmb_id"
    });
	asmbDescAc.setKeyName("rcmFuncEqItemDetailDTO.asmbId");
	asmbDescAc.init();
    
    
	acSysDesc("rcmFuncEqItemDetailDTO.isPossible","rcmFuncEqItemDetailDTO.isPossible","IS_USE");
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAANSWER_ID');

	
}

function setSequenceVal(sequenceVal)
{
	rcmFuncEqItemDetailForm.elements['rcmFuncEqItemDetailDTO.rcmFfEqId'].value = sequenceVal;
}

function goSave(){
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)) rcmFuncEqItemDetailForm.strutsAction.value = '<%=RcmFuncEqItemDetailAction.QNA_ANS_DETAIL_INPUT%>';
	else rcmFuncEqItemDetailForm.strutsAction.value = '<%= RcmFuncEqItemDetailAction.QNA_ANS_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/rcmFuncEqItemDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(rcmFuncEqItemDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(rcmFuncEqItemDetailForm.elements['rcmFuncEqItemDetailDTO.rcmFfEqId'].value);

	setTitle("rcmFuncEqItemDetailDTO.rcmFuncEqNo");
    getTopPage().afterSaveAll(currentPageId);
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = rcmFuncEqItemDetailForm.elements['rcmFuncEqItemDetailDTO.rcmFfEqId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>

</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/rcmFuncEqItemDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="rcmFuncEqCommonDTO.rcmFfailId"/>
<html:hidden property="rcmFuncEqItemDetailDTO.rcmFfEqId"/>
<html:hidden property="rcmFuncEqItemDetailDTO.asmbId"/>
<html:hidden property="rcmFuncEqItemDetailDTO.equipId"/>
<html:hidden property="rcmFuncEqItemDetailDTO.taEquipId"/>
    <!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
				<div class="field">
				<label class="check"><bean:message key="LABEL.equipment"/></label>
				<div class="input_box">
					<html:text property="rcmFuncEqItemDetailDTO.equipDesc" tabindex="50" />
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<div class="field">
                <label><bean:message key="LABEL.asmb"/></label>
                <div class="input_box">
                    <html:text property="rcmFuncEqItemDetailDTO.asmbDesc" tabindex="30"/>
                    <p class="open_spop">
                        <a>
                            <span>조회</span>
                        </a>
                    </p>
                </div>
             </div>
             <!-- 고장가능성여부 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.isPossible"/></label>
				<div class="input_box">
					<html:text property="rcmFuncEqItemDetailDTO.isPossible" tabindex="160"/>
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="rcmFuncEqItemDetailDTO.remark" styleClass="ta50" tabindex="60" />
				</div>
			</div>
		</div>
	</div><!--article_box end-->
</html:form> 
</body>
</html>
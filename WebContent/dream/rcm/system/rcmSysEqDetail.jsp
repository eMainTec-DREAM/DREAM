<%--===========================================================================
설비설정 - 상세
author  kim21017
version $Id: rcmSysEqDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%> 
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.rcm.system.action.RcmSysEqDetailAction"%>
<html>
<head>
<!-- 설비설정-->
<title><bean:message key="TAB.rcmSysEqList"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var equipDescAc;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
	setTitle("rcmSysEqDetailDTO.itemNo","rcmSysEqDetailDTO.description");
	setForUpdate();
	
	equipDescAc = new autoC({"rcmSysEqDetailDTO.description":"description"});
	equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
	equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcResultMap({
        "rcmSysEqDetailDTO.equipId":"equip_id"
        ,"rcmSysEqDetailDTO.itemNo":"item_no"
        ,"rcmSysEqDetailDTO.eqLocId":"eqloc_id"
        ,"rcmSysEqDetailDTO.eqCtgId":"eqctg_id"
        ,"rcmSysEqDetailDTO.eqStatusId":"eq_status"
        ,"rcmSysEqDetailDTO.eqLocDesc":"eqLocDesc"
        ,"rcmSysEqDetailDTO.eqCtgDesc":"eqCtgDesc"
        ,"rcmSysEqDetailDTO.eqStatusDesc":"eqStatusDesc"
    });
    equipDescAc.setKeyName("rcmSysEqDetailDTO.equipId");
    equipDescAc.init();
}

function goInput()
{
	// 시퀀스를 조회한다.
	sequenceNextVal('SQARCMEQ_ID');
	
	$(".accordion_wrap").hide();
}

function goUpdate()
{
	disableFormObject();
}

function disableFormObject()
{
	rcmSysEqDetailForm.elements['rcmSysEqDetailDTO.rcmEqId'].readOnly = true;
	document.getElementById("equipDiv").className = "input_read"; 
    document.getElementById("equipPopDiv").style.display = "none";
}

function setSequenceVal(sequenceVal)
{
	rcmSysEqDetailForm.elements['rcmSysEqDetailDTO.rcmEqId'].value = sequenceVal;
}

function goSave(){
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)) rcmSysEqDetailForm.strutsAction.value = '<%=RcmSysEqDetailAction.RCM_SYS_EQ_DETAIL_INPUT%>';
	else rcmSysEqDetailForm.strutsAction.value = '<%= RcmSysEqDetailAction.RCM_SYS_EQ_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/rcmSysEqDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(rcmSysEqDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    //parent.goSearch(); 
	if (parent.findGridRow)	parent.findGridRow(rcmSysEqDetailForm.elements['rcmSysEqDetailDTO.rcmEqId'].value);
	setTitle("rcmSysEqDetailDTO.itemNo","rcmSysEqDetailDTO.description");
    getTopPage().afterSaveAll(currentPageId);
    
    disableFormObject();
    $(".accordion_wrap").show();
}

function goTabPage(pageId)
{
	var form = document.rcmSysEqDetailForm;

	goCommonTabPage(form, '' , pageId);
    
}
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = rcmSysEqDetailForm.elements['rcmSysEqDetailDTO.rcmEqId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/rcmSysEqDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="rcmSysCommonDTO.rcmListId"/>
	<html:hidden property="rcmSysEqDetailDTO.rcmListId"/>
	<html:hidden property="rcmSysEqDetailDTO.rcmEqId"/>
	<html:hidden property="rcmSysEqDetailDTO.equipId"/>
	<html:hidden property="rcmSysEqDetailDTO.itemNo"/>
	<html:hidden property="rcmSysEqDetailDTO.eqCtgId"/>
	<html:hidden property="rcmSysEqDetailDTO.eqLocId"/>
	<html:hidden property="rcmSysEqDetailDTO.eqStatusId"/>
	
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 설비 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.equipment"/></label>
				<div class="input_box" id="equipDiv">
					<html:text property="rcmSysEqDetailDTO.description" tabindex="10"/>
						<p class="open_spop" id="equipPopDiv">
							<a><span>조회</span></a>
						</p>
				</div>
			</div>
			<!-- 설비상태 -->
			<div class="field">
				<label><bean:message key="LABEL.equipStatus"/></label>
				<div class="input_read">
					<html:text property="rcmSysEqDetailDTO.eqStatusDesc" tabindex="20" readonly="true"/>
				</div>
			</div>
			<!-- 설비종류 -->
			<div class="field">
				<label><bean:message key="LABEL.type"/></label>
				<div class="input_read">
					<html:text property="rcmSysEqDetailDTO.eqCtgDesc" tabindex="30" readonly="true"/>
				</div>
			</div>
			<!-- 위치 -->
			<div class="field">
				<label><bean:message key="LABEL.location"/></label>
				<div class="input_read">
					<html:text property="rcmSysEqDetailDTO.eqLocDesc" tabindex="40" readonly="true"/>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="rcmSysEqDetailDTO.remark" styleClass="ta50" tabindex="50"/>
				</div>
			</div>
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>
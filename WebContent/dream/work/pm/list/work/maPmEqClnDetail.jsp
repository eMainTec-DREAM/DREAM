<%--===========================================================================
청소설비
author  kim21017
version $Id: maPmEqClnDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%> 
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.work.pm.list.action.MaPmEqClnDetailAction"%>
<html>
<head>
<!-- 자재-->
<title><bean:message key="TAB.maPmEqClnList"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var equipDescAc;
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
	setTitle("maPmEqClnDetailDTO.equipDesc","maPmEqClnDetailDTO.eqLocDesc");
	setForUpdate();
	
	equipDescAc = new autoC({"maPmEqClnDetailDTO.equipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo,
 	   "eq_status":"R+S"
 	   });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcResultMap({
        "maPmEqClnDetailDTO.equipId":"equip_id"
        ,"maPmEqClnDetailDTO.eqLocId":"eqloc_id"
        ,"maPmEqClnDetailDTO.eqLocDesc":"eqLocDesc"
    });
    equipDescAc.setKeyName("maPmEqClnDetailDTO.equipId"); 
    equipDescAc.init();
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAPMEQUIP_ID');
}

function setSequenceVal(sequenceVal)
{
	maPmEqClnDetailForm.elements['maPmEqClnDetailDTO.pmEquipId'].value = sequenceVal;
}

function goSave(){
	if(checkValidation()) return;
	
	maPmEqClnDetailForm.elements['maPmEqClnDetailDTO.initWrkDate'].value =
		parent.parent.maPmMstrDetailForm.elements['maPmMstrDetailDTO.initWrkDate'].value;

	if(ckCreate(currentPageId)) maPmEqClnDetailForm.strutsAction.value = '<%=MaPmEqClnDetailAction.PM_EQ_CLN_DETAIL_INPUT%>';
	else maPmEqClnDetailForm.strutsAction.value = '<%= MaPmEqClnDetailAction.PM_EQ_CLN_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maPmEqClnDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maPmEqClnDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    //parent.goSearch(); 
	if (parent.findGridRow)	parent.findGridRow(maPmEqClnDetailForm.elements['maPmEqClnDetailDTO.pmEquipId'].value);
	setTitle("maPmEqClnDetailDTO.equipDesc","maPmEqClnDetailDTO.eqLocDesc");
    getTopPage().afterSaveAll(currentPageId);
}

/* audit Trail */
function goAudtrailLink()
{
	var objectId = maPmEqClnDetailForm.elements['maPmEqClnDetailDTO.pmEquipId'].value;
	var fileName = currentPageId;

	if(typeof objectId=="undefined") return;

	goAudTrailList(objectId, fileName);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maPmEqClnDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maPmMstrCommonDTO.pmId"/>
	<html:hidden property="maPmEqClnDetailDTO.pmEquipId"/>
	<html:hidden property="maPmEqClnDetailDTO.equipId"/>
	<html:hidden property="maPmEqClnDetailDTO.initWrkDate"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 설비 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.equipment"/></label>
				<div class="input_box">
					<html:text property="maPmEqClnDetailDTO.equipDesc" tabindex="10"/>
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 청소부위 -->
			<div class="field">
				<label><bean:message key="LABEL.clnAsmb"/></label>
				<div class="input_box">
					<html:text property="maPmEqClnDetailDTO.workPart" tabindex="20"/>
				</div>
			</div>
			<!-- 위치 -->
			<div class="field_long">
				<label><bean:message key="LABEL.location"/></label>
				<div class="input_read">
					<html:text property="maPmEqClnDetailDTO.eqLocDesc" tabindex="30" readonly="true"/>
				</div>
			</div>
			
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>
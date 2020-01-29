<%--===========================================================================
작업결과 작업설비(윤활) 상세
author  kim21017
version $Id: maWoResultOilDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.work.list.action.MaWoResultClnDetailAction"%>
<html>
<head>
<!-- 윤활설비-->
<title><bean:message key="TAB.maWoResultOilList"/></title>
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
	
	setTitle("maWoResultClnDetailDTO.equipDesc","maWoResultClnDetailDTO.eqLocDesc");
	setForUpdate();
	
	equipDescAc = new autoC({"maWoResultClnDetailDTO.equipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "a.comp_no":loginUser.compNo,
 	   "EQ_STATUS":"R+S"
 	   });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcResultMap({
        "maWoResultClnDetailDTO.equipId":"equip_id"
        ,"maWoResultClnDetailDTO.eqLocId":"eqloc_id"
        ,"maWoResultClnDetailDTO.eqLocDesc":"eqLocDesc"
    });
    equipDescAc.setKeyName("maWoResultClnDetailDTO.equipId"); 
    equipDescAc.init();
}

function goUpdate(){
	if(maWoResultClnDetailForm.elements['maWoResultClnDetailDTO.woGenType'].value=="PMPLAN"){
		maWoResultClnDetailForm.elements['maWoResultClnDetailDTO.equipDesc'].readOnly = true;
	    document.getElementById("equipDiv").className = "input_read"; 
	    document.getElementById("equipPopDiv").style.display = "none";
	}
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQAWOEQUIP_ID');
}

function setSequenceVal(sequenceVal)
{
	maWoResultClnDetailForm.elements['maWoResultClnDetailDTO.woEquipId'].value = sequenceVal;
}

function goSave(){

	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;

	if(ckCreate(currentPageId)) maWoResultClnDetailForm.strutsAction.value = '<%=MaWoResultClnDetailAction.WO_RESULT_CLN_DETAIL_INPUT%>';
	else maWoResultClnDetailForm.strutsAction.value = '<%= MaWoResultClnDetailAction.WO_RESULT_CLN_DETAIL_UPDATE %>';
	
	parent.parent.maWoResultMstrDetailForm.elements['maWoResultMstrDetailDTO.equipId'].value
	 = maWoResultClnDetailForm.elements['maWoResultClnDetailDTO.equipId'].value;
	
	var actionUrl = contextPath + "/maWoResultClnDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maWoResultClnDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maWoResultClnDetailForm.elements['maWoResultClnDetailDTO.woEquipId'].value);
    getTopPage().afterSaveAll(currentPageId);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/maWoResultClnDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="maWoResultMstrCommonDTO.wkOrId"/>
	<html:hidden property="maWoResultClnDetailDTO.woEquipId"/>
	<html:hidden property="maWoResultClnDetailDTO.equipId"/>
	<html:hidden property="maWoResultClnDetailDTO.woGenType"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
		     
			<!-- 설비 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.equipment"/></label>
				<div class="input_box" id="equipDiv">
					<html:text property="maWoResultClnDetailDTO.equipDesc" tabindex="10"/>
					<p class="open_spop" id="equipPopDiv">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<!-- 위치 -->
			<div class="field">
				<label><bean:message key="LABEL.location"/></label>
				<div class="input_read">
					<html:text property="maWoResultClnDetailDTO.eqLocDesc" tabindex="20" readonly="true"/>
				</div>
			</div>
			<!-- 청소부위 -->
			<div class="field">
				<label><bean:message key="LABEL.asmb"/></label>
				<div class="input_box">
					<html:text property="maWoResultClnDetailDTO.workPart" tabindex="30"/>
				</div>
			</div>
			<!-- 작업시간(분) -->
			<div class="field">
				<label><bean:message key="LABEL.woTimeMin"/></label>
				<div class="input_box">
					<html:text property="maWoResultClnDetailDTO.workTime" styleClass="num" tabindex="40"/>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="maWoResultClnDetailDTO.remark" styleClass="ta50" tabindex="50"/>
				</div>
			</div>
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>
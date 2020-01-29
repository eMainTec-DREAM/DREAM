<%--===========================================================================
무정지대표라인 - 상세
author  kim21017
version $Id: maNstGrpDetail.jsp,v 1.5 2014/07/02 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.mgr.manst.action.MaNstGrpDetailAction"%>
<html:html>
<head>
<title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
	setTitle("maNstGrpDetailDTO.popLineNo","maNstGrpDetailDTO.mainLineNo");
	//For Save
	setForUpdate();
}

function goUpdate(){
}

function goInput()
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
	if(ckCreate(currentPageId)) maNstGrpDetailForm.strutsAction.value = "<%=MaNstGrpDetailAction.NST_GRP_DETAIL_INPUT%>";
	else maNstGrpDetailForm.strutsAction.value = "<%=MaNstGrpDetailAction.NST_GRP_DETAIL_UPDATE%>";
	
	var actionUrl = contextPath + "/maNstGrpDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maNstGrpDetailForm), 'afterSave');

}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    /* maNstGrpDetailForm.elements['maNstGrpDetailDTO.popPlantNo'].value = maNstGrpDetailForm.elements['maNstGrpCommonDTO.popPlantNo'].value;
    maNstGrpDetailForm.elements['maNstGrpDetailDTO.popDeptNo'].value = maNstGrpDetailForm.elements['maNstGrpCommonDTO.popDeptNo'].value;
    maNstGrpDetailForm.elements['maNstGrpDetailDTO.popLineNo'].value = maNstGrpDetailForm.elements['maNstGrpCommonDTO.popLineNo'].value;
    maNstGrpDetailForm.elements['maNstGrpDetailDTO.mainLineNo'].value = maNstGrpDetailForm.elements['maNstGrpCommonDTO.mainLineNo'].value; */
    //parent.goSearch();
     if (parent.findGridRow)	parent.findGridRow(maNstGrpDetailForm.elements['maNstGrpDetailDTO.popPlantNo'].value
     											,maNstGrpDetailForm.elements['maNstGrpDetailDTO.popDeptNo'].value
     											,maNstGrpDetailForm.elements['maNstGrpDetailDTO.popLineNo'].value);
    getTopPage().afterSaveAll(currentPageId);
}
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maNstGrpDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="maNstGrpCommonDTO.popPlantNo" />
	<html:hidden property="maNstGrpCommonDTO.popDeptNo" />
	<html:hidden property="maNstGrpCommonDTO.popLineNo" />
	<html:hidden property="maNstGrpCommonDTO.mainLineNo" />
	<html:hidden property="maNstGrpDetailDTO.popPlantNo" />
	<html:hidden property="maNstGrpDetailDTO.popDeptNo" />
	<div class="article_box">
		<div class="form_box">
			<!-- 공장 -->
		    <div class="field">
				<label><bean:message key="LABEL.plant"/></label>
				<div class="input_read">
					<html:text property="maNstGrpDetailDTO.popPlantDesc" tabindex="10" readonly="true"/>
				</div>
			</div>
			<!-- 과 -->
		    <div class="field">
				<label><bean:message key="LABEL.deptOg"/></label>
				<div class="input_read">
					<html:text property="maNstGrpDetailDTO.popDeptDesc" tabindex="20" readonly="true"/>
				</div>
			</div>
			<!-- 라인 -->
			<div class="field">
				<label><bean:message key="LABEL.line"/></label>
				<div class="input_read">
					<html:text property="maNstGrpDetailDTO.popLineNo" tabindex="30" readonly="true"/>
				</div>
			</div>
			<!-- 대표라인 -->
			<div class="field">
				<label><bean:message key="LABEL.mainLine"/></label>
				<div class="input_box">
					<html:text property="maNstGrpDetailDTO.mainLineNo" tabindex="40"/>
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>

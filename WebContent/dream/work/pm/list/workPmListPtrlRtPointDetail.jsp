<%--===========================================================================
검사항목
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.work.pm.list.action.MaPmMstrPointDetailAction"%>
<html>
<head>
<!-- 점검항목-->
<title><bean:message key="LABEL.checkPoint"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
 	if(ckCreate(currentPageId)) goInput();
	setTitle("maPmMstrPointDetailDTO.stepNum","maPmMstrPointDetailDTO.checkPoint");
	setForUpdate();

	//사용여부  AC
    acSysDesc("maPmMstrPointDetailDTO.isActive","maPmMstrPointDetailDTO.isActive","IS_USE",true);
}

function goInput(){
 	// 시퀀스를 조회한다.
 	sequenceNextVal('SQAPM_POINT_ID');
 	maPmMstrPointDetailForm.elements['maPmMstrPointDetailDTO.isActive'].value='Y';
 	maPmMstrPointDetailForm.elements['maPmMstrPointDetailDTO.checkType'].value='SEN';
}

function setSequenceVal(sequenceVal)
{
 	maPmMstrPointDetailForm.elements['maPmMstrPointDetailDTO.pmPointId'].value = sequenceVal;
}

function goSave()
{
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)) maPmMstrPointDetailForm.strutsAction.value = '<%=MaPmMstrPointDetailAction.PM_MSTR_POINT_DETAIL_INPUT%>';
	else maPmMstrPointDetailForm.strutsAction.value = '<%= MaPmMstrPointDetailAction.PM_MSTR_POINT_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/workPmListPtrlRtPointDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maPmMstrPointDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maPmMstrPointDetailForm.elements['maPmMstrPointDetailDTO.pmPointId'].value);
    setTitle("maPmMstrPointDetailDTO.stepNum","maPmMstrPointDetailDTO.checkPoint");
    getTopPage().afterSaveAll(currentPageId);
}

/**
 * 저장후생성후 호출
 */
function afterSavenew()
{
	sequenceNextVal('SQAPM_POINT_ID');

	//점검순서, 점검항목
    maPmMstrPointDetailForm.elements['maPmMstrPointDetailDTO.stepNum'].value = "";
    maPmMstrPointDetailForm.elements['maPmMstrPointDetailDTO.checkPoint'].value = "";
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/workPmListPtrlRtPointDetail.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="maPmMstrCommonDTO.pmId"/>
	<html:hidden property="maPmMstrCommonDTO.pmPointId"/>
	<html:hidden property="maPmMstrCommonDTO.pmEquipId"/>
	<html:hidden property="maPmMstrPointDetailDTO.pmPointId"/>
	<html:hidden property="maPmMstrPointDetailDTO.checkType"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 점검순서 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.pmStepNum"/></label>
				<div class="input_box">
					<html:text property="maPmMstrPointDetailDTO.stepNum" tabindex="10" styleClass="num"/>
				</div>
			</div>
			<!-- 사용여부 -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.isUse"/></label>
				<div class="input_box">
					<html:text property="maPmMstrPointDetailDTO.isActive" tabindex="20"/>
                    <p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<!-- 점검내용 -->
			<div class="field_long">
				<label class="check"><bean:message key="LABEL.pmContents"/></label>
				<div class="input_box">
					<html:text property="maPmMstrPointDetailDTO.checkPoint" tabindex="30"/>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="maPmMstrPointDetailDTO.remark" styleClass="ta50" tabindex="40"/>
				</div>
			</div>
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>
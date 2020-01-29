
<%--===========================================================================
시스템코드 - 분류 
author  kim21017
version $Id: maCdSysCodeDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
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
<%@page import="dream.consult.comp.cdsys.action.MaCdSysCodeDetailAction"%>
<html>
<head>
<!--유형별 세부코드 -->
<title><bean:message key="LABEL.typeCode"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	
    //사용여부
    acSysDesc("maCdSysCodeDetailDTO.isUse","maCdSysCodeDetailDTO.isUse","IS_USE", true);
	
	setTitle("maCdSysCodeDetailDTO.code","maCdSysCodeDetailDTO.codeDesc");
	setForUpdate();
}

function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQACDSYSD_ID');
	maCdSysCodeDetailForm.elements['maCdSysCodeDetailDTO.isUse'].value = 'Y';
}

function setSequenceVal(sequenceVal)
{
	maCdSysCodeDetailForm.elements['maCdSysCodeDetailDTO.cdSysDId'].value = sequenceVal;
}

function goSave(){
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)) maCdSysCodeDetailForm.strutsAction.value = '<%=MaCdSysCodeDetailAction.LISTTYPE_CODE_DETAIL_INPUT%>';
	else maCdSysCodeDetailForm.strutsAction.value = '<%= MaCdSysCodeDetailAction.LISTTYPE_CODE_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maCdSysCodeDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maCdSysCodeDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maCdSysCodeDetailForm.elements['maCdSysCodeDetailDTO.cdSysDId'].value);
    
    getTopPage().afterSaveAll(currentPageId);
}

//-->
</script>

</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/maCdSysCodeDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maCdSysCommonDTO.cdSysMId"/>
<html:hidden property="maCdSysCodeDetailDTO.listType"/>
<html:hidden property="maCdSysCodeDetailDTO.cdSysDId"/>
<html:hidden property="maCdSysCodeDetailDTO.keyType"/><!-- 다국어 key_type -->
<html:hidden property="maCdSysCodeDetailDTO.keyNo"/><!-- 다국어 keyNo -->
<html:hidden property="maCdSysCodeDetailDTO.keyTypeStr"/>
    <!-- searchbox 박스 Line -->
	<div class="article_box">
			<div class="form_box">
			<div class="field">
				<!-- 분류코드 -->
				<label class="check"><bean:message key="LABEL.typeCode"/></label>
				<div class="input_box">
					<html:text property="maCdSysCodeDetailDTO.code" tabindex="10"/>
				</div>
			</div>
			<div class="field">
				<!-- 분류명 -->
				<label><bean:message key="LABEL.typeCodeDesc"/></label>
				<div class="input_box">
					<html:text property="maCdSysCodeDetailDTO.codeDesc" tabindex="20"/>
				</div>
			</div>
			
				
			<div class="field">
				<!-- 분류설명 -->
				<label><bean:message key="LABEL.codeRemark"/></label>
				<div class="input_box">
					<html:text property="maCdSysCodeDetailDTO.remark" tabindex="30"/>
				</div>
			</div>
			<div class="field">
				<!-- 화면순서 -->
				<label><bean:message key="LABEL.ordNo"/></label>
				<div class="input_box">
					<html:text property="maCdSysCodeDetailDTO.ordNo" tabindex="50" />
				</div>
			</div>
			<div class="field">
				<!-- param1 -->
				<label><bean:message key="LABEL.param1"/></label>
				<div class="input_box">
					<html:text property="maCdSysCodeDetailDTO.param1" tabindex="60" />
				</div>
			</div>
			<div class="field">
				<!-- param2 -->
				<label><bean:message key="LABEL.param2"/></label>
				<div class="input_box">
					<html:text property="maCdSysCodeDetailDTO.param2" tabindex="70" />
				</div>
			</div>
			<div class="field">
				<!-- param3 -->
				<label>param3</label>
				<div class="input_box">
					<html:text property="maCdSysCodeDetailDTO.param3" tabindex="75" />
				</div>
			</div>
			<div class="field">
				<label><bean:message key="LABEL.isUse"/></label>
				<div class="input_box">
					<html:text property="maCdSysCodeDetailDTO.isUse" tabindex="80"/>
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
		</div>
	</div><!--article_box end-->
</html:form> 
</body>
</html>
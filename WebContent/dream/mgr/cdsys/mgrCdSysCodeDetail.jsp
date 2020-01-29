
<%--===========================================================================
시스템코드 - 분류 
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
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@page import="dream.mgr.cdsys.action.MgrCdSysCodeDetailAction"%>
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
    //사용여부
    acSysDesc("mgrCdSysCodeDetailDTO.isUse","mgrCdSysCodeDetailDTO.isUse","IS_USE", true);
	
	setTitle("mgrCdSysCodeDetailDTO.code","mgrCdSysCodeDetailDTO.codeDesc");
	
	setForUpdate();
}

function goSave(){
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	mgrCdSysCodeDetailForm.strutsAction.value = '<%= MgrCdSysCodeDetailAction.LISTTYPE_CODE_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/mgrCdSysCodeDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(mgrCdSysCodeDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(mgrCdSysCodeDetailForm.elements['mgrCdSysCodeDetailDTO.cdSysDId'].value);
    
    getTopPage().afterSaveAll(currentPageId);
}

//-->
</script>

</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/mgrCdSysCodeDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="mgrCdSysCommonDTO.cdSysMId"/>
<html:hidden property="mgrCdSysCodeDetailDTO.listType"/>
<html:hidden property="mgrCdSysCodeDetailDTO.cdSysDId"/>
<html:hidden property="mgrCdSysCodeDetailDTO.keyType"/><!-- 다국어 key_type -->
<html:hidden property="mgrCdSysCodeDetailDTO.keyNo"/><!-- 다국어 keyNo -->
<html:hidden property="mgrCdSysCodeDetailDTO.keyTypeStr"/>
    <!-- searchbox 박스 Line -->
	<div class="article_box">
			<div class="form_box">
			<div class="field">
				<!-- 분류코드 -->
				<label class="check">분류코드</label>
				<div class="input_read">
					<html:text property="mgrCdSysCodeDetailDTO.code" tabindex="10" readonly="true"/>
				</div>
			</div>
			<div class="field">
				<!-- 분류명 -->
				<label><bean:message key="LABEL.typeCodeDesc"/></label>
				<div class="input_box">
					<html:text property="mgrCdSysCodeDetailDTO.codeDesc" tabindex="20"/>
				</div>
			</div>
			
			<!-- 정렬값 -->
			<div class="field">
				<label><bean:message key="LABEL.ordNo"/></label>
				<div class="input_box">
					<html:text property="mgrCdSysCodeDetailDTO.ordNo" tabindex="50" />
				</div>
			</div>
			<div class="field">
				<label><bean:message key="LABEL.isUse"/></label>
				<div class="input_box">
					<html:text property="mgrCdSysCodeDetailDTO.isUse" tabindex="80"/>
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
			<div class="field">
				<!-- 분류설명 -->
				<label><bean:message key="LABEL.codeRemark"/></label>
				<div class="input_box">
					<html:text property="mgrCdSysCodeDetailDTO.remark" tabindex="90"/>
				</div>
			</div>
		</div>
	</div><!--article_box end-->
</html:form> 
</body>
</html>
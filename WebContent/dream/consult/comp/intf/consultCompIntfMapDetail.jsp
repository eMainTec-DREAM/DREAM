 <%--===========================================================================
Interface Map Detail
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.comp.intf.action.ConsultCompIntfMapDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 인터페이스 MAP -->
<title><bean:message key='MENU.INTFLOG' /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

function loadPage() 
{
	// Param Type
	acSysDesc("consultCompIntfMapDetailDTO.paramTypeDesc","consultCompIntfMapDetailDTO.paramType","INTF_PARAM_TYPE", true);
	// Source PK 여부
	acSysDesc("consultCompIntfMapDetailDTO.sisPkDesc","consultCompIntfMapDetailDTO.sisPk","IS_USE", true);
	// Source Not Null 여부
	acSysDesc("consultCompIntfMapDetailDTO.sisNotNullDesc","consultCompIntfMapDetailDTO.sisNotNull","IS_USE", true);
	// Target PK 여부
	acSysDesc("consultCompIntfMapDetailDTO.tisPkDesc","consultCompIntfMapDetailDTO.tisPk","IS_USE", true);
	// Target Not Null 여부
	acSysDesc("consultCompIntfMapDetailDTO.tisNotNullDesc","consultCompIntfMapDetailDTO.tisNotNull","IS_USE", true);
	
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
    setTitle("consultCompIntfMapDetailDTO.paramSeq");
    
    //For Save
    setForUpdate();
}

/**
 * 입력
 */
function goInput()
{
	sequenceNextVal('SQAINTFMAP_ID');
}

function setSequenceVal(sequenceVal)
{
	consultCompIntfMapDetailForm.elements['consultCompIntfMapDetailDTO.intfMapId'].value = sequenceVal;
}

/**
 * 수정
 */
function goUpdate()
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
    if(ckCreate(currentPageId)) consultCompIntfMapDetailForm.strutsAction.value = "<%=ConsultCompIntfMapDetailAction.DETAIL_INPUT%>";
    else consultCompIntfMapDetailForm.strutsAction.value = "<%=ConsultCompIntfMapDetailAction.DETAIL_UPDATE%>";
	var actionUrl = contextPath + "/consultCompIntfMapDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(consultCompIntfMapDetailForm), 'afterSave');
    
}

/**
 * 저장후 호출
 */
function afterSave(ajaxXmlDoc)
{
    //=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(consultCompIntfMapDetailForm.elements['consultCompIntfMapDetailDTO.intfMapId'].value);
    getTopPage().afterSaveAll(currentPageId);
 
    setTitle("consultCompIntfMapDetailDTO.paramSeq");

}

</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

	<html:form action="/consultCompIntfMapDetail">
		<html:hidden property="strutsAction" />
		<html:hidden property="consultCompIntfCommonDTO.intfId" /><!-- Key -->
		<html:hidden property="consultCompIntfCommonDTO.compNo" />
		<html:hidden property="consultCompIntfMapListDTO.intfMapId" /><!-- Key -->
		<html:hidden property="consultCompIntfMapListDTO.compNo" />
		<html:hidden property="consultCompIntfMapDetailDTO.intfMapId" /><!-- Key -->
		<html:hidden property="consultCompIntfMapDetailDTO.intfId" />
		
		<html:hidden property="consultCompIntfMapDetailDTO.paramType" />
		<html:hidden property="consultCompIntfMapDetailDTO.sisPk" />
		<html:hidden property="consultCompIntfMapDetailDTO.sisNotNull" />
		<html:hidden property="consultCompIntfMapDetailDTO.tisPk" />
		<html:hidden property="consultCompIntfMapDetailDTO.tisNotNull" />
		<div class="article_box">
			<div class="form_box">
				<!-- Parameter 순서 -->
				<div class="field">
					<label><bean:message key="LABEL.paramSeq" /></label>
					<div class="input_box">
						<html:text property="consultCompIntfMapDetailDTO.paramSeq" tabindex="10"/>
					</div>
				</div>
				<!-- Param Type -->
				<div class="field">
					<label><bean:message key="LABEL.paramTypeDesc" /></label>
					<div class="input_box">
						<html:text property="consultCompIntfMapDetailDTO.paramTypeDesc" tabindex="20"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- Source 테이블명 -->
				<div class="field">
					<label><bean:message key="LABEL.stabName" /></label>
					<div class="input_box">
						<html:text property="consultCompIntfMapDetailDTO.stabName" tabindex="30"/>
					</div>
				</div>
				<!-- Source 컬럼명 -->
				<div class="field">
					<label><bean:message key="LABEL.sfieldName" /></label>
					<div class="input_box">
						<html:text property="consultCompIntfMapDetailDTO.sfieldName" tabindex="40"/>
					</div>
				</div>
				<!-- Source PK 여부 -->
				<div class="field">
					<label><bean:message key="LABEL.sisPkDesc" /></label>
					<div class="input_box">
						<html:text property="consultCompIntfMapDetailDTO.sisPkDesc" tabindex="50"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- Source 컬럼타입 -->
				<div class="field">
					<label><bean:message key="LABEL.sfieldTypeDesc" /></label>
					<div class="input_box">
						<html:text property="consultCompIntfMapDetailDTO.sfieldTypeDesc" tabindex="60"/>
					</div>
				</div>
				<!-- Source 컬럼 Size -->
				<div class="field">
					<label><bean:message key="LABEL.sfieldSizeDesc" /></label>
					<div class="input_box">
						<html:text property="consultCompIntfMapDetailDTO.sfieldSizeDesc" tabindex="70"/>
					</div>
				</div>
				<!-- Source Not Null 여부 -->
				<div class="field">
					<label><bean:message key="LABEL.sisNotNullDesc" /></label>
					<div class="input_box">
						<html:text property="consultCompIntfMapDetailDTO.sisNotNullDesc" tabindex="80"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- Source Default Value -->
				<div class="field">
					<label><bean:message key="LABEL.sdefaultVal" /></label>
					<div class="input_box">
						<html:text property="consultCompIntfMapDetailDTO.sdefaultVal" tabindex="90"/>
					</div>
				</div>
				<div class="field">
				</div>
				<!-- Source 컬럼 설명 -->
				<div class="field_long">
					<label><bean:message key="LABEL.sremark" /></label>
					<div class="input_box">
						<html:textarea styleClass="ta50" property="consultCompIntfMapDetailDTO.sremark" tabindex="100"/>
					</div>
				</div>
				<!-- Target 테이블명 -->
				<div class="field">
					<label><bean:message key="LABEL.ttabName" /></label>
					<div class="input_box">
						<html:text property="consultCompIntfMapDetailDTO.ttabName" tabindex="110"/>
					</div>
				</div>
				<!-- Target 컬럼명 -->
				<div class="field">
					<label><bean:message key="LABEL.tfieldName" /></label>
					<div class="input_box">
						<html:text property="consultCompIntfMapDetailDTO.tfieldName" tabindex="120"/>
					</div>
				</div>
				<!-- Target PK 여부 -->
				<div class="field">
					<label><bean:message key="LABEL.tisPkDesc" /></label>
					<div class="input_box">
						<html:text property="consultCompIntfMapDetailDTO.tisPkDesc" tabindex="130"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- Target 컬럼타입 -->
				<div class="field">
					<label><bean:message key="LABEL.tfieldTypeDesc" /></label>
					<div class="input_box">
						<html:text property="consultCompIntfMapDetailDTO.tfieldTypeDesc" tabindex="140"/>
					</div>
				</div>
				<!-- Target 컬럼 Size -->
				<div class="field">
					<label><bean:message key="LABEL.tfieldSizeDesc" /></label>
					<div class="input_box">
						<html:text property="consultCompIntfMapDetailDTO.tfieldSizeDesc" tabindex="150"/>
					</div>
				</div>
				<!-- Target Not Null 여부 -->
				<div class="field">
					<label><bean:message key="LABEL.tisNotNullDesc" /></label>
					<div class="input_box">
						<html:text property="consultCompIntfMapDetailDTO.tisNotNullDesc" tabindex="160"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- Target Default Value -->
				<div class="field">
					<label><bean:message key="LABEL.tdefaultVal" /></label>
					<div class="input_box">
						<html:text property="consultCompIntfMapDetailDTO.tdefaultVal" tabindex="170"/>
					</div>
				</div>
				<div class="field">
				</div>
				<!-- Target 컬럼 설명 -->
				<div class="field_long">
					<label><bean:message key="LABEL.tremark" /></label>
					<div class="input_box">
						<html:textarea styleClass="ta50" property="consultCompIntfMapDetailDTO.tremark" tabindex="180"/>
					</div>
				</div>
			</div>
			<!-- End of Form_box -->
		</div>
		<!-- End of Article_box -->
	</html:form>
</body>
</html:html>


<%--===========================================================================
데이터 테이블 - 분류 
author  kim21017
version $Id: maTableColDetail.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
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
<%@page import="dream.consult.program.table.action.MaTableColDetailAction"%>
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
	
	setTitle("maTableColDetailDTO.columnName","maTableColDetailDTO.description");
	setForUpdate();
	setFunction();
}

function setFunction()
{
	acSysDesc("maTableColDetailDTO.dataTypeDesc","maTableColDetailDTO.dataTypeId","DATA_TYPE",true);
}
function goInput(){
	// 시퀀스를 조회한다.
	sequenceNextVal('SQATABCOL_ID');
	
	maTableColDetailForm.elements['maTableColDetailDTO.creDate'].value   = getToday();
	maTableColDetailForm.elements['maTableColDetailDTO.creBy'].value     = loginUser.userId;
	maTableColDetailForm.elements['maTableColDetailDTO.creByDesc'].value = loginUser.userName;
}

function setSequenceVal(sequenceVal)
{
	maTableColDetailForm.elements['maTableColDetailDTO.tabColId'].value = sequenceVal;
}

function goSave(){
	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;
	
	if(ckCreate(currentPageId)) maTableColDetailForm.strutsAction.value = '<%=MaTableColDetailAction.LISTTYPE_CODE_DETAIL_INPUT%>';
	else maTableColDetailForm.strutsAction.value = '<%= MaTableColDetailAction.LISTTYPE_CODE_DETAIL_UPDATE %>';
	
	var actionUrl = contextPath + "/maTableColDetail.do";
	XMLHttpPost(actionUrl, FormQueryString(maTableColDetailForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    if (parent.findGridRow)	parent.findGridRow(maTableColDetailForm.elements['maTableColDetailDTO.tabColId'].value);
    
    getTopPage().afterSaveAll(currentPageId);
}

function goRefColumn()
{
	if(maTableColDetailForm.elements['maTableColDetailDTO.refColumn'].value=='')
	{
		maTableColDetailForm.elements['maTableColDetailDTO.refTable'].value='';
		maTableColDetailForm.elements['maTableColDetailDTO.refTabColId'].value='';
	}
}
//-->
</script>

</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/maTableColDetail.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maTableCommonDTO.tableMId"/>
<html:hidden property="maTableCommonDTO.tableName"/>
<html:hidden property="maTableColDetailDTO.tabColId"/>
<html:hidden property="maTableColDetailDTO.keyType"/><!-- 다국어 key_type -->
<html:hidden property="maTableColDetailDTO.keyNo"/><!-- 다국어 keyNo -->
<html:hidden property="maTableColDetailDTO.creBy" />
<html:hidden property="maTableColDetailDTO.dataTypeId"/>
<html:hidden property="maTableColDetailDTO.refTabColId"/>
    <!-- searchbox 박스 Line -->
	<div class="article_box">
			<div class="form_box">
			<div class="field">
				<!-- 컬럼 -->
				<label class="check"><bean:message key="LABEL.columnName"/></label>
				<div class="input_box">
					<html:text property="maTableColDetailDTO.columnName" tabindex="10"/>
				</div>
			</div>
			<div class="field">
				<!-- 컬럼설명 -->
				<label class="check"><bean:message key="LABEL.columnDetail"/></label>
				<div class="input_box">
					<html:text property="maTableColDetailDTO.description" tabindex="10"/>
				</div>
			</div>
			<!-- 타입. -->
			<div class="field">
				<label class="check"><bean:message key="LABEL.typeDesc"/></label>
				<div class="input_box">
					<html:text property="maTableColDetailDTO.dataTypeDesc" tabindex="100" />
					<p class="open_spop"><a><span>조회</span></a></p>
				</div>
			</div>
			<div class="field">
        	 	<label class="check"><bean:message key="LABEL.length"/></label>
        	 	<div class="input_box" >
        	 		<html:text property="maTableColDetailDTO.dataLength" tabindex="60" styleClass="num"/>
        	 	</div>
        	 </div>	
			 <!-- 생성자 -->
			 <div class="field">
			 <label><bean:message key="LABEL.creBy"/></label>
			 <div class="input_read">
			 	<html:text property="maTableColDetailDTO.creByDesc" readonly="true"/>
			 </div>
			 </div>
			 <!-- 생성일자 -->
			<div class="field">
		    <label><bean:message key="LABEL.creDate"/></label>
		    <div class="input_read">
		    	<html:text property="maTableColDetailDTO.creDate" readonly="true"/>
		    </div>
		    </div>
		    <!-- Ref테이블 -->
			 <div class="field">
			 <label><bean:message key="LABEL.refTable"/></label>
			 <div class="input_read">
			 	<html:text property="maTableColDetailDTO.refTable" readonly="true"/>
			 </div>
			 </div>
			 <!-- Ref컬럼 -->
			 <div class="field">
			 <label><bean:message key="LABEL.refColumn"/></label>
			 <div class="input_box">
					<html:text property="maTableColDetailDTO.refColumn" tabindex="100" onchange="goRefColumn();" />
					<p class="open_spop"><a href="javascript:lovRefColumn('maTableColDetailDTO.refTable', 'maTableColDetailDTO.refColumn','maTableCommonDTO.tableMId','maTableColDetailDTO.refTabColId');"><span>조회</span></a></p>
				</div>
			 </div>
			 <div class="field">
        	 	<label class="check"><bean:message key="LABEL.seqNo"/></label>
        	 	<div class="input_box" >
        	 		<html:text property="maTableColDetailDTO.seqNo" tabindex="60" styleClass="num"/>
        	 	</div>
        	 </div>	
		    <div class="field_long">
				<!-- 상세설명 -->
				<label><bean:message key="LABEL.detailDesc"/></label>
				<div class="input_box">
					<html:textarea property="maTableColDetailDTO.remark" styleClass="ta50" tabindex="110" />
				</div>
			</div>
		    </div>
	</div><!--article_box end-->
</html:form> 
</body>
</html>
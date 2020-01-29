<%--===========================================================================
설비변경이력 - 상세
author  kim21017
version $Id: maEqMstrHistDetail.jsp,v 1.0 2015/12/04 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.asset.list.action.MaEqMstrHistDetailAction"%>
<html:html>
<head>
<!-- 설비 -->
<title><bean:message key='LABEL.equipment' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
var saveStrutsAction;
var slideImage = new Array();

function loadPage() 
{
	setTitle("maEqMstrHistDetailDTO.itemNo","maEqMstrHistDetailDTO.equipDesc");
}

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maEqMstrHistDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="maEqMstrHistListDTO.equipId" />
	<html:hidden property="maEqMstrHistDetailDTO.capacity" />
	<html:hidden property="maEqMstrHistDetailDTO.utilCapa" />
	<html:hidden property="maEqMstrHistDetailDTO.buyAmt" />
	<html:hidden property="maEqMstrHistDetailDTO.eqalthistId" />
	<div class="article_box">
		<div class="form_box">
			<!-- 설비번호 -->
			<div class="field">
				<label><bean:message key="LABEL.equipNo"/></label>
				<div class="input_read">
					<html:text property="maEqMstrHistDetailDTO.itemNo" readonly="true"/>
				</div>
			</div>
			<!-- 설비상태 -->
			<div class="field">
				<label><bean:message key="LABEL.equipStatus"/></label>
				<div class="input_read">
					<html:text property="maEqMstrHistDetailDTO.eqStatusDesc" readonly="true" />
				</div>
			</div>
			<!-- 설비명 -->
			<div class="field">
				<label><bean:message key="LABEL.equipName"/></label>
				<div class="input_read">
					<html:text property="maEqMstrHistDetailDTO.equipDesc" readonly="true" />
				</div>
			</div>
			<!-- 설치일자 -->
			<div class="field">
				<label><bean:message key="LABEL.setupDate"/></label>
				<div class="input_read">
					<html:text property="maEqMstrHistDetailDTO.setupDate" readonly="true" />
				</div>
			</div>
			<!-- 위치 -->
			<div class="field">
				<label><bean:message key="LABEL.location"/></label>
				<div class="input_read">
					<html:text property="maEqMstrHistDetailDTO.eqLocDesc" readonly="true" />
				</div>
			</div>
			<!-- 사용Utility -->
			<!-- 종류 -->
			<div class="field">
				<label><bean:message key="LABEL.type"/></label>
				<div class="input_read">
					<html:text property="maEqMstrHistDetailDTO.eqCtgDesc" readonly="true" />
				</div>
			</div>
			<!-- 구매금액 -->
			<!-- 관리자(정) -->
			<div class="field">
				<label><bean:message key="LABEL.mainManager"/></label>
				<div class="input_read">
					<html:text property="maEqMstrHistDetailDTO.mainMngName" readonly="true" />
				</div>
			</div>
			<!-- 관리자(부) -->
			<div class="field">
				<label><bean:message key="LABEL.subManager"/></label>
				<div class="input_read">
					<html:text property="maEqMstrHistDetailDTO.subMngName" readonly="true" />
				</div>
			</div>
			<!-- 관리부서 -->
			<div class="field">
				<label><bean:message key="LABEL.dept"/></label>
				<div class="input_read">
					<html:text property="maEqMstrHistDetailDTO.deptDesc" readonly="true" />
				</div>
			</div>
			<!-- 법정설비여부 -->
			<div class="field">
				<label><bean:message key="LABEL.isLawEq"/></label>
				<div class="input_read">
					<html:text property="maEqMstrHistDetailDTO.isLawEq" readonly="true" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
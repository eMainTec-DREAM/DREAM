<%--===========================================================================
검교정상세 측정값상세
author  kim21017
version $Id: workListSclCavalList.jsp,v 1.0 2015/12/04 07:26:18 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@page import="dream.work.list.action.WorkListSclCavalListAction"%>
<html>
<head>
<!-- 측정값 -->
<title><bean:message key="TAB.workListGnlCavalList"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	setForUpdate();
}

function goSave(){

	//================================
	// 필수 항목 체크한다.
	//================================
	if(checkValidation()) return;

	workListSclCavalListForm.strutsAction.value = '<%= WorkListSclCavalListAction.WORK_LIST_SCL_CAVAL_LIST_UPDATE %>';
	
	var actionUrl = contextPath + "/workListSclCavalList.do";
	XMLHttpPost(actionUrl, FormQueryString(workListSclCavalListForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    getTopPage().afterSaveAll(currentPageId);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px; width: 900px;" marginheight="0" marginwidth="0" > 
<html:form action="/workListSclCavalList">
	<html:hidden property="strutsAction"/>
	<html:hidden property="maWoResultMstrCommonDTO.wkOrId"/>
	<html:hidden property="workListSclCavalListDTO.wkOrId"/>
	<html:hidden property="workListSclCavalListDTO.woCalibSclValueId"/>
	<!-- searchbox 박스 Line -->
	<div class="article_box">
		<div class="form_box">
			<!-- 직선성(증가) -->
			<div class="field_long ty5">
				<label><bean:message key="LABEL.linearityIncrease"/></label>
				<div class="multi_wrap">
					<div class="input_box">
						<div class="slabel">0%</div>
						<html:text property="workListSclCavalListDTO.liValue0" tabindex="10"/>
					</div>
					<div class="input_box">
						<div class="slabel">25%</div>
						<html:text property="workListSclCavalListDTO.liValue25" tabindex="20"/>
					</div>
					<div class="input_box">
						<div class="slabel">50%</div>
						<html:text property="workListSclCavalListDTO.liValue50" tabindex="30"/>
					</div>
					<div class="input_box">
						<div class="slabel">75%</div>
						<html:text property="workListSclCavalListDTO.liValue75" tabindex="40"/>
					</div>
					<div class="input_box">
						<div class="slabel">100%</div>
						<html:text property="workListSclCavalListDTO.liValue100" tabindex="50"/>
					</div>
				</div>
			</div>
			<!-- 직선성(감소) -->
			<div class="field_long ty4">
				<label><bean:message key="LABEL.linearityDown"/></label>
				<div class="multi_wrap">
					<div class="input_box">
						<div class="slabel">75%</div>
						<html:text property="workListSclCavalListDTO.ldValue75" tabindex="50"/>
					</div>
					<div class="input_box">
						<div class="slabel">50%</div>
						<html:text property="workListSclCavalListDTO.ldValue50" tabindex="60"/>
					</div>
					<div class="input_box">
						<div class="slabel">25%</div>
						<html:text property="workListSclCavalListDTO.ldValue25" tabindex="70"/>
					</div>
					<div class="input_box">
						<div class="slabel">0%</div>
						<html:text property="workListSclCavalListDTO.ldValue0" tabindex="80"/>
					</div>
				</div>
			</div>
			<!-- 편심오차 -->
			<div class="field_long ty5">
				<label><bean:message key="LABEL.eccentricityError"/></label>
				<div class="multi_wrap">
					<div class="input_box">
						<div class="slabel"><bean:message key="LABEL.center"/></div>
						<html:text property="workListSclCavalListDTO.ecntrValue" tabindex="90"/>
					</div>
					<div class="input_box">
						<div class="slabel"><bean:message key="LABEL.before"/></div>
						<html:text property="workListSclCavalListDTO.ebefValue" tabindex="100"/>
					</div>
					<div class="input_box">
						<div class="slabel"><bean:message key="LABEL.after"/></div>
						<html:text property="workListSclCavalListDTO.eaftValue" tabindex="110"/>
					</div>
					<div class="input_box">
						<div class="slabel"><bean:message key="LABEL.left"/></div>
						<html:text property="workListSclCavalListDTO.elftValue" tabindex="120"/>
					</div>
					<div class="input_box">
						<div class="slabel"><bean:message key="LABEL.right"/></div>
						<html:text property="workListSclCavalListDTO.erigValue" tabindex="130"/>
					</div>
				</div>
			</div>
			<!-- 정밀도 -->
			<div class="field_long ty3">
				<label><bean:message key="LABEL.precision"/></label>
				<div class="multi_wrap">
					<div class="input_box">
						<div class="slabel"></div>
						<html:text property="workListSclCavalListDTO.degree1" tabindex="140"/>
					</div>
					<div class="input_box">
						<div class="slabel"></div>
						<html:text property="workListSclCavalListDTO.degree2" tabindex="150"/>
					</div>
					<div class="input_box">
						<div class="slabel"></div>
						<html:text property="workListSclCavalListDTO.degree3" tabindex="160"/>
					</div>
				</div>
			</div>
			<!-- 비고 -->
			<div class="field_long">
				<label><bean:message key="LABEL.remark"/></label>
				<div class="input_box">
					<html:textarea property="workListSclCavalListDTO.remark" styleClass="ta50" tabindex="170" />
				</div>
			</div>
		</div><!--form_box end-->
	</div> <!-- End of Article_box -->
</html:form> 
</body>
</html>
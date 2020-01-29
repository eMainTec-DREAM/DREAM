<%--===========================================================================
 - 상세
author  kim21017
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.req.work.action.ReqWorkReswoDetailAction"%>
<%@ page import="common.bean.User"%>
<html:html>
<head>
<title><bean:message key='LABEL.woReqNo' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<script language="javascript">


function loadPage() 
{
	//setTitle("reqWorkReswoDetailDTO.woNo", "reqWorkReswoDetailDTO.wkOrDesc");
}
	
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/reqWorkReswoDetail" >
	<html:hidden property="strutsAction"/>
	<html:hidden property="maWoReqCommonDTO.woReqId" />

	<div class="article_box">
		<div class="form_box">
			<!-- 작업No -->
			<div class="field">
				<label><bean:message key="LABEL.woNo"/></label>
				<div class="input_read" id="woNoDiv">
					<html:text property="reqWorkReswoDetailDTO.woNo" tabindex="10" readonly="true"/>
				</div>
			</div>
			<!-- WO상태 -->
			<div class="field">
				<label><bean:message key="LABEL.woStatus"/></label>
				<div class="input_read" id="woStatusDescDiv">
					<html:text property="reqWorkReswoDetailDTO.woStatusDesc" tabindex="20" />
				</div>
			</div>
			<!-- 작업명 -->
			<div class="field_long">
				<label ><bean:message key="LABEL.woName"/></label>
				<div class="input_read">
					<html:text property="reqWorkReswoDetailDTO.wkOrDesc" tabindex="70" />
				</div>
			</div>
			<!-- 작업일자 -->
			<div class="field">
				<label ><bean:message key="LABEL.wkorDate"/></label>
				<div class="input_read">
					<html:text property="reqWorkReswoDetailDTO.wkorDate" tabindex="80" />
				</div>
			</div>
			<!-- 담당부서 -->
			<div class="field">
				<label ><bean:message key="LABEL.manageDept"/></label>
				<div class="input_read">
					<html:text property="reqWorkReswoDetailDTO.deptDesc" tabindex="90"/>
				</div>
			</div>
			<!-- 담당자 -->
			<div class="field">
				<label class="lastcheck"><bean:message key="LABEL.manager"/></label>
				<div class="input_read">
					<html:text property="reqWorkReswoDetailDTO.empDesc" tabindex="110"/>
				</div>
			</div>
			<div class="field">
				<label><bean:message key="LABEL.wkCtr"/></label>
				<div class="input_read">
					<html:text property="reqWorkReswoDetailDTO.wkCtrDesc" tabindex="95"/>
				</div>
			</div>
			<!-- 작업상세내용 -->
			<div class="field_long">
				<label><bean:message key="LABEL.woPerform"/></label>
				<div class="input_read">
					<html:textarea property="reqWorkReswoDetailDTO.perform" styleClass="ta50" tabindex="190" />
				</div>
			</div>
		</div> <!-- End of Form_box -->
	</div> <!-- End of Article_box -->
</html:form>
</body>
</html:html>
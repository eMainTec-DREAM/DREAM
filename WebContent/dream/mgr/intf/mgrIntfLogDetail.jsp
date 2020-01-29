 <%--===========================================================================
Interface Log Detail
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page
	import="dream.mgr.intf.action.MgrIntfLogDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 인터페이스 로그 -->
<title><bean:message key='MENU.INTFLOG' /></title>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!-- //

function loadPage() 
{
	if(ckCreate(currentPageId)) goInput();
	else goUpdate();
	
    setTitle("mgrIntfLogDetailDTO.exeTime");
    //For Save
    setForUpdate();
}

/**
 * 입력
 */
function goInput()
{
	
}
/**
 * 수정
 */
function goUpdate()
{
	
}

</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">

	<html:form action="/mgrIntfLogDetail">
		<html:hidden property="strutsAction" />
		<html:hidden property="mgrIntfLogListDTO.intfLogId" /><!-- Key -->
		<html:hidden property="mgrIntfLogDetailDTO.intfLogId" /><!-- Key -->
		<html:hidden property="mgrIntfLogDetailDTO.intfId" />
		<html:hidden property="mgrIntfLogDetailDTO.exeBy" />
		<div class="article_box">
			<div class="form_box">
				<!-- 실행시간 -->
				<div class="field">
					<label><bean:message key="LABEL.exeTime" /></label>
					<div class="input_read">
						<html:text property="mgrIntfLogDetailDTO.exeTime" tabindex="10" readonly="true" />
					</div>
				</div>
				<!-- 성공여부 -->
				<div class="field">
					<label><bean:message key="LABEL.isSuccess" /></label>
					<div class="input_read">
						<html:text property="mgrIntfLogDetailDTO.rtnYn" tabindex="20" readonly="true" />
					</div>
				</div>
				<!-- 비고 -->
				<div class="field_long">
					<label><bean:message key="LABEL.remark" /></label>
					<div class="input_read">
						<html:textarea styleClass="ta50" property="mgrIntfLogDetailDTO.rtnMsg" tabindex="30" readonly="true" />
					</div>
				</div>
				<!-- Log -->
				<div class="field_long">
					<label><bean:message key="LABEL.log" /></label>
					<div class="input_read">
						<html:textarea styleClass="ta100" property="mgrIntfLogDetailDTO.exeLog" tabindex="40" readonly="true" />
					</div>
				</div>
			</div>
			<!-- End of Form_box -->
		</div>
		<!-- End of Article_box -->
	</html:form>
</body>
</html:html>

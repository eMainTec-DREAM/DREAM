<%--===========================================================================
버튼 - 상세
author  kim21017
version $Id: maSesMngDetail.jsp,v 1.5 2014/07/02 04:13:54 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ page import="dream.consult.rpt.mases.action.MaSesMngDetailAction"%>
<html:html>
<head>
<!-- 실시간 접속자 -->
<title><bean:message key='TAB.maSesMngDetail' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">// 저장 시 수행되는 action
var saveStrutsAction;

function loadPage() 
{
	setTitle("maSesMngCommonDTO.sessionId","maSesMngCommonDTO.userNo");
}
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/maSesMngDetail" >
	<html:hidden property="strutsAction"/>
		<div class="article_box" id="detailBox">
			<div class="form_box">
				<div class="field">
					<label><bean:message key="LABEL.sessionId"/></label>
					<div class="input_read">
						<html:text property="maSesMngCommonDTO.sessionId" tabindex="1" readonly="true"/>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.loginTime"/></label>
					<div class="input_read">
						<html:text property="maSesMngCommonDTO.loginTime" tabindex="2" readonly="true"/>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.user"/></label>
					<div class="input_read">
						<html:text property="maSesMngCommonDTO.userNo" tabindex="3" readonly="true"/>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.userName"/></label>
					<div class="input_read">
						<html:text property="maSesMngCommonDTO.userName" tabindex="4" readonly="true"/>
					</div>
				</div>
			</div> <!-- End of Form_box -->
		</div> <!-- End of Article_box -->
	</html:form>
</body>
</html:html>

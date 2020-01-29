<%--===========================================================================
권한없음 페이지
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
<html:html>
<head>
<title><bean:message key='MENU.NOAUTHPAGELIST' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<script language="javascript">

function loadPage() 
{	
	$('#pg_title > h1').text(document.title);
	
	$('#noAuthPageId').html('<%= request.getParameter("noAuthPageId") %>');
}
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/noAuthPageList" >
	<html:hidden property="strutsAction" />
	<c:import charEncoding="UTF-8" url="/dream/auth/noAuthPageHtml.jsp"></c:import>
	</html:form>
</body>
</html:html>
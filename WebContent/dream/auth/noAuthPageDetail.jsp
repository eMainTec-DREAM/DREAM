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
<script src="<c:url value="/common/js/common.js" />" ></script>
<script src="<c:url value="/common/jquery/jquery-1.11.1.js" />" ></script>
<link rel="stylesheet" href="<c:url value="/common/css/ma/style.css" />" type="text/css" />

<script language="javascript">

window.onload=function(){
	loadPage() ;
}

function loadPage() 
{	
	$('#noAuthPageId').html('<%= request.getParameter("noAuthPageId") %>');
	
	if(typeof parent.resizeTabFrame == "function")
	{
		parent.resizeTabFrame();
	}
}

</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/noAuthPageDetail">
		<html:hidden property="strutsAction" />
		<c:import charEncoding="UTF-8" url="/dream/auth/noAuthPageHtml.jsp"></c:import>
	</html:form>
</body>
</html:html>
<%--===========================================================================
session 말료시 이용
author  javaworker
version $Id: logoutPage.jsp,v 1.1 2013/08/30 09:09:08 javaworker Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%
	//=====================================================================================
	String commonErrorMss = (String)request.getSession().getAttribute("gridException");
	String isTreeAction = (String)request.getSession().getAttribute("isTreeAction");
	//=====================================================================================
%>
<html>
<head>
<!-- SESSEION MANAGER -->
<title><bean:message key="LABEL.logout"/>
</title>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<script src="<c:url value="/common/js/common.js" />" ></script>
<script type="text/javascript">
var contextPath = '<%=request.getContextPath()%>';
//======================================
// COMMON MESSAGE - START
//======================================
// 시스템 사용대기 시간이 종료되었습니다. 다시 로그인 하셔야만 합니다.\n로그인 페이지로 이동합니다.
var COMMON_CMSG020 = "<bean:message key='COMMON.CMSG020'/>";
// 강제 종료 되었습니다. 다시 로그인 하셔야만 합니다.\n로그인 페이지로 이동합니다.
var COMMON_CMSG021 = "<bean:message key='COMMON.CMSG021'/>";

function goLoginPage(tempErrMsg)
{
    if (tempErrMsg == "-9999")
    {
        // 세션이 종료되었습니다. 다시 로긴 하셔야만 합니다.\n로긴페이지로 이동합니다.
        alert(COMMON_CMSG020);
    }
    
    if (tempErrMsg == "-8888")
    {
        // 강제 종료 되었습니다. 다시 로긴 하셔야만 합니다.\n로긴페이지로 이동합니다.
        alert(COMMON_CMSG021);
    }
    
    var bottomForm = document.bottomForm;
    bottomForm.target = "";
    bottomForm.action = contextPath + "/mobile.do";
    bottomForm.submit();
}

//======================================
// Sesson , log out check
//======================================
function loadPage()
{
	<%
	System.out.println(commonErrorMss);
	if (commonErrorMss != null &&
	    !"".equals(commonErrorMss))
	{
	%>
		if ("true" == "<%=isTreeAction%>")
		{
			try 
			{
				// master tree에서 열은 경우이다.
				parent.parent.logOutCheck("<%=commonErrorMss%>");
			}
			catch(ex)
			{
				// 일반 tree 에서 열은 경우이다.
				parent.opener.logOutCheck("<%=commonErrorMss%>");
			}
		}
		else
		{
			try 
			{
				getTopPage().logOutCheck("<%=commonErrorMss%>");

                if(opener)self.close();
			}
			catch(ex)
			{
                try
                {
				    parent.logOutCheck("<%=commonErrorMss%>");
			    }
			    catch(ex2)
			    {
			        logOutCheck("<%=commonErrorMss%>");
			    }
			}
		}
	<%    
	}
	%>
}

//======================================
// COMMON MESSAGE - END
//======================================

</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0" onload="loadPage();" >
<form name="bottomForm" method="post" style="border:0;" >
</form>
</body>
</html>
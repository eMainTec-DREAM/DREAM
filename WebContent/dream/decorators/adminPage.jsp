<%--===========================================================================
관리자 공통 decorator
author  pochul2423
version $Id: adminPage.jsp,v 1.6 2014/04/17 05:58:14 jung7126 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@page import="common.bean.User"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%
    User menuLoginUser = (User)request.getSession().getAttribute(request.getSession().getId()); 
    String menuLoginUserName = menuLoginUser.getUserName();
    
    // 현재 페이지의 Menu 경로를 표시한다.
    String [] menuPath = (String [])request.getAttribute("menuPath");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<link rel="shortcut icon" href="<c:url value="/common/images/${ctPath}/favicon_dream.ico" />" type="image/x-icon" />
<link rel="icon" href="<c:url value="/common/images/${ctPath}/favicon_dream.ico" />" type="image/x-icon" />
<title><decorator:title/></title>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">


<!-- 공통 페이지 -->
<c:import charEncoding="UTF-8" url="/common/jsp/commonInclude.jsp"></c:import>
<decorator:head />
<script language="JavaScript" type="text/JavaScript">

function loadDefault()
{
	$(".form_box").keydown(function(e){
    	if(e.keyCode == 13 && typeof goSearch == "function") goSearch();	
    });
	//prevent trigger submit with one input in the form when enter key perssed.
	$('input,select').keypress(function(event) { return event.keyCode != 13; }); 
	
	//필터 접기
	//foldFilter();
}
jQuery(function($){
	$("#content").scroll(function(){
		if ($(this).scrollTop() > 0) {
			$("#pg_title").addClass("fixed");
		} else {
			$("#pg_title").removeClass("fixed");
		};
	});
});	

//-->
</script>
</head>
<body class="top_body" onload="loadDefault();"  style="margin-top: 0px; margin-left: 0px; margin-right: 0px; margin-bottom: 0px; background-color: #eeeeee;">
    <div id="wrap">
        <div id="header">
			<c:import charEncoding="UTF-8" url="/common/jsp/adminTopInclude.jsp"></c:import> 
		</div><!-- end header -->
        <div id="container">
        	<div id="nav_left">
        	<div class="b_leftfold"><a><span class="blind">메뉴접기버튼</span></a></div>
        		<c:import charEncoding="UTF-8" url="/common/menu/adminMenuInclude.jsp"></c:import>
       		</div><!--nav_left end-->
       		<div id="pg_title" style="margin-left: 246px;"><h1>페이지 타이틀</h1></div><!-- 타이틀 -->
        	<div id="content"><!--nav_left를 접었을때 41px, 펼쳤을대 246px-->
	        	<div class="content_wrap">
					<div id='bodyDiv' style="margin-top: 3px; display: block; margin: 0px; padding: 0px; width: auto;">
	           			<decorator:body />
	           		</div>
	           		<c:import charEncoding="UTF-8" url="/common/jsp/tabPageInclude.jsp"></c:import>
	           	</div> <!-- End Content_wrap -->
        	</div> <!--  End content -->
        </div> <!-- End Container -->
   	</div>
    <!-- //CONTAINER -->
<!-- ########## page 하단 공통 페이지 ########## -->
<c:import charEncoding="UTF-8" url="/common/jsp/bottomInclude.jsp"></c:import>
<script>
// body 의 onload 에 넣었다가 IE 8 에서 body 가 모두 로딩되기 전에 호출이 되어서 제일 뒤에 loadpage를 호출하게 수정하였음
loadPage();
</script>
<c:import charEncoding="UTF-8" url="/common/jsp/buttonInclude.jsp" ></c:import>
</body>

</html>
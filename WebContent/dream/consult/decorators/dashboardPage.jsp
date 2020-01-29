<%--===========================================================================
기본 decorator
author  pochul2423
version $Id: defaultPage.jsp,v 1.14 2015/01/09 00:16:42 pochul2423 Exp $
since   1.0
===========================================================================--%>
<%@page import="common.bean.MwareConfig"%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@page import="common.finder.valid.action.ValidationAction"%>
<%@page import="common.message.DataBaseMessageResources"%>
<%@page import="org.apache.struts.Globals"%>
<%@page import="common.struts.BaseAction"%>
<%@page import="common.bean.User"%>
<%

    User menuLoginUser = (User)request.getSession().getAttribute(request.getSession().getId());
	String noticeDp = (String)session.getAttribute("noticeDiplayed");
    String menuLoginUserName = menuLoginUser.getUserName();
    String usrGrpId = menuLoginUser.getUsrgrpId();
    String compNo =menuLoginUser.getCompNo();
    String userId = menuLoginUser.getUserId();
    String noticeYn = menuLoginUser.getAlarmViewYn();
    String menuDp = menuLoginUser.getMenuDisplay();
    menuDp = "EXPAND";
    
    System.out.println();
    // 현재 페이지의 Menu 경로를 표시한다.
    String [] menuPath = (String [])request.getAttribute("menuPath");
 	
 	String pMenuId = "";
 	String menuId = "";
 	String menuDescr = "";
 	String menuDesc = "";
 	 DataBaseMessageResources dataBaseMessageResources =
             (DataBaseMessageResources)request.getAttribute(Globals.MESSAGES_KEY);

 	
 	if(menuPath != null)
 	{
 	    pMenuId = menuPath[0];
 	    menuId = menuPath[1];
 	    menuDescr = menuPath[3];
 	    menuDesc = "MENU."+menuPath[4];
	 	menuDesc = dataBaseMessageResources.getMessage(menuLoginUser.getLocale(), "MENU." + menuPath[4]);
 	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="shortcut icon" href="<c:url value="/common/images/${ctPath}/favicon_dream.ico" />" type="image/x-icon" />
<link rel="icon" href="<c:url value="/common/images/${ctPath}/favicon_dream.ico" />" type="image/x-icon" />
<title><decorator:title/></title>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<c:import charEncoding="UTF-8" url="/common/jsp/commonInclude.jsp"></c:import>


<decorator:head />
<script language="JavaScript" type="text/JavaScript">
</script>
</head>
<body class="top_body" onload="loadDefault();"  style="margin-top: 0px; margin-left: 0px; margin-right: 0px; margin-bottom: 0px;  background-color: #eeeeee;">
    <div id="wrap">
        <div id="header">
        	<c:import charEncoding="UTF-8" url="/common/jsp/consultTopInclude.jsp"></c:import> 
        </div><!-- end header -->
        <c:set var="menuDp" value="<%=menuDp%>" />
        <c:set var="expand" value="EXPAND" />
    	<c:choose>
   			<c:when test="${expand == menuDp}">
   			
		<div id="container">
        	<div id="nav_left" style="width:245px;">
        	<div class="b_leftfold"><a><span class="blind">메뉴접기버튼</span></a></div>
        		<c:import charEncoding="UTF-8" url="/common/menu/menuInclude.jsp"></c:import>
			</div><!--nav_left end-->
        	<div id="content" style="margin-left: 246px; top:0; background-color:#fafafa; "><!--nav_left를 접었을때 41px, 펼쳤을대 246px-->
	        	<div class="content_wrap">
	        		<div id='bodyDiv' style="margin-top: 3px; display: block; margin: 0px; padding: 0px; width: auto;">
	           			<decorator:body />
	           		</div>
	        		<c:import charEncoding="UTF-8" url="/common/jsp/tabPageInclude.jsp"></c:import>
				</div> <!-- End Content_wrap -->
        	</div> <!--  End content -->
        </div> <!-- End Container -->
        
    		</c:when>
    		<c:otherwise>
     
     <div id="container">
        	<div id="nav_left" style="width:40px;">
        	<div class="b_leftunfold"><a><span class="blind">메뉴접기버튼</span></a></div>
        		<c:import charEncoding="UTF-8" url="/common/menu/menuInclude_fold.jsp"></c:import>
			</div><!--nav_left end-->
        	<div id="content" style="margin-left: 41px; top:0; background-color:#fafafa; "><!--nav_left를 접었을때 41px, 펼쳤을대 246px-->
	        	<div class="content_wrap">
	        		<div id='bodyDiv' style="margin-top: 3px; display: block; margin: 0px; padding: 0px; width: auto;">
	           			<decorator:body />
	           		</div>
	        		<c:import charEncoding="UTF-8" url="/common/jsp/tabPageInclude.jsp"></c:import>
				</div> <!-- End Content_wrap -->
        	</div> <!--  End content -->
        </div> <!-- End Container -->
        
 			</c:otherwise>
    	</c:choose>
        
   	</div>
    <!-- //CONTAINER -->
<!-- page 하단 공통 페이지 -->
<c:import charEncoding="UTF-8" url="/common/jsp/bottomInclude.jsp"></c:import>

<script language="javascript">
// 비활성화 되어 있다면 활성화 시킨다.
window.focus();
// body 의 onload 에 넣었다가 IE 8 에서 body 가 모두 로딩되기 전에 호출이 되어서 제일 뒤에 loadpage를 호출하게 수정하였음
loadPage();

jQuery(function($){

	/* var rstField = $('.field').map(function(index){
		return $(this).get(0);
	});
	
	$('.form_box').children().remove();
	$('.form_box').append(rstField);
	
	setCalendar(); */
});		
</script>
<c:import charEncoding="UTF-8" url="/common/jsp/buttonInclude.jsp" ></c:import>
</body>
</html>
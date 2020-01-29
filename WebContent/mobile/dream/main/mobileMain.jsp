<%--===========================================================================
Main
author  wondo
version $Id: main.jsp,v 1.51 2015/01/09 00:16:42 pochul2423 Exp $
since   1.0
===========================================================================--%>
<%@page import="dream.main.action.MainAction"%>
<%@ page import="common.struts.BaseAction"%>
<%@ page import="common.util.StringUtil"%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/c-rt.tld" prefix="c-rt"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Hashtable"%>
<%@ page import="common.bean.User"%>
<%@ page import="common.util.CommonUtil"%>
<%@ page import="common.bean.MwareConfig"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<jsp:useBean id="mobileMainForm" class="mobile.dream.main.form.MobileMainForm" scope="request" />
<%
	//로그인 유저
	User menuLoginUser = (User)request.getSession().getAttribute(request.getSession().getId()); 
	String menuLoginUserName = menuLoginUser.getUserName();

	String mainListStr = (String)session.getAttribute("MAINLIST");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<!--  -->
<title>Dream</title>
<meta name="decorator" content="mobilePage">
<script language="JavaScript" type="text/JavaScript">
//<![CDATA[
           

//]]>
</script>
</head>
<body>
<html:form action="/mobileMain.do">
<c:import charEncoding="UTF-8" url="/mobile/dream/jsp/baseDTOInclude.jsp"></c:import>
	<div class="mlist_wrap">
		<div class="user_box"><span>홍길동</span>님 , 환영합니다.</div>
		<ul>
			<div id='data_container' style='width:100%;height:100%;'></div>
			
			
		</ul>
	</div>
</html:form>
<!-- ########## page 하단 공통 : 모든 페이지 적용 ########## --> 
<c:import charEncoding="UTF-8"  url="/common/jsp/bottomInclude.jsp"></c:import>

<script type="text/javascript">
var myList;
function loadPage()
{
	initList();
	
	//Top Bar as Main 
	$('.btn_back').hide();
	$('.page_title').css("font-weight","bold");
}

function initList()
{
	myList = new dhtmlXList({
		container:"data_container",
		type:{
			template:"<li class='list_box'><a>"+
   		 			 "<div class='mtitle'>#TITLE#</div>"+
   		 			 "<div class='mnum'>#COUNT#</div></a></li>"
		,height:"auto"
   		 			 
		}
	});
	
	
	myList.attachEvent("onItemClick", function (id, ev, html){
		var data = myList.get(id);
		
		var _menuId;
		for(var i in menuJArray)
	    {
	         if(menuJArray[i].url.split("?")[0] == data.URL) _menuId =  menuJArray[i].id;
	    }
		
		submitPost(data.URL, FormQueryString(mobileMainForm)+"&menuId="+_menuId);

		//goCommonMobilePage(mobileMainForm, "", data.URL);

	    return true;
	});
	
	myList.attachEvent("onAfterRender", function(){
	    //  your code here
		$('.dhx_list_default_item').css("padding","0px");
	    $('.user_box').css("border-bottom","1px solid #ffcccc");
		$('.list_box').css("border-top","0px");
		
		$('.mtitle').css({
			"font-size":"2em"
			,"top":"20px"
		});
		
		$('.mnum').css({
			"top":"20px"
		});
	});
	
	myList.parse(<%=mainListStr%>,"json");
}

</script>
</body>
</html>
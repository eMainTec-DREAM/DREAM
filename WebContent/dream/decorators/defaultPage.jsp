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
    // 현재 페이지의 Menu 경로를 표시한다.
    String [] menuPath = (String [])request.getAttribute("menuPath");

 // login 후 main에서 셋팅된 admin menu를 session에서 가지고 온다.
    String [][] adminMenu1 = (String [][])session.getAttribute("ADMINMENU");
    int adminMenuCount = 0;
    adminMenuCount = adminMenu1.length;
    String adminPage = ""; String adminMenuId = "";
    if(adminMenuCount!=0)
    {
        adminPage = adminMenu1[0][1];
        adminMenuId  = adminMenu1[0][2];
    }
    
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
<c:import charEncoding="UTF-8" url="/common/jsp/licensePage.jsp"></c:import>
<decorator:head />
<script language="JavaScript" type="text/JavaScript">
<!--
function loadDefault()
{
   //현재 선택된 subMenu 의 style을 변경하기 위해 사용한다.   
   //document.getElementById("TD_SUB_MENU_"+'${currentPageId}').className = "allmenu_list_selected";
   $(".form_box").keydown(function(e){
       if(e.keyCode == 13 && typeof goSearch == "function"){
          if(!(myPop && myPop.isVisible())) goSearch();   
       }
    });
   $('input,select').keypress(function(event) { return event.keyCode != 13; }); 
   
   //필터 접기
   //foldFilter();
   
   showCopyRight();

   //if(getCookie('dashboard') != "HIDE" && loginUser.alarmViewYn == "Y") goDashboard();
   if(loginUser.alarmViewYn == "Y" && '<%=noticeDp%>' != "Y") goDashboard();
   
   //menuDisplay(loginUser.menuDisplay);

   //$('title').text($("li[id^='TD_SUB_MENU_']>a.selected").text());
}

function goMenu()
{
   //Admin 페이지를 갈경우 기존에 mgMenuList 가 고정이었는데 mgMenu 에 권한이 없을경우 admin 페이지 이동이 안되었음.
   //권한이 있는 페이지중에서 첫번째 페이지로 이동 하도록 수정
   goMenuPage("<%=adminPage%>"+"?&menuId="+"<%=adminMenuId%>");
}

jQuery(function($){

   var orgTop;
   $("#content").scroll(function(){
      if ($(this).scrollTop() > 0) {
         $("#pg_title").addClass("fixed");
      } else {
         $("#pg_title").removeClass("fixed");
      };
      
      if(myPop && myPop.isVisible())myPop.hide();
      
      genTabTitle();
   });
   
   $('#pg_title').on("click",function(e){
      $("#content").animate({scrollTop:0},100);
   });
   
   
});   

function goMyMenu()
{
    var param = "maMyMenuDTO.usrGrpId="+<%=usrGrpId%>+"&maMyMenuDTO.compNo="+<%=compNo%>+"&maMyMenuDTO.userId="+<%=userId%>;

    openLayerPopup("maMyMenu", param);
}

function goMyLink()
{
    var param = "maMyLinkDTO.usrGrpId="+<%=usrGrpId%>+"&maMyLinkDTO.compNo="+<%=compNo%>+"&maMyLinkDTO.userId="+<%=userId%>+"&maMyLinkDTO.menuId="+<%=menuId%>
    +"&maMyLinkDTO.menuDesc="+'<%=menuDesc%>';

    openLayerPopup("maMyLink", param);
}

/**
 * Open Dashboard 
 */
function goDashboard()
{
    var param = "popupWidth=380&popupHeight=650";

    openLayerPopup("maDbList", param);
}
   
function goReload()
{
   //alert(window.location.href);
   //window.location.href = window.location.href;
   
}

//-->
</script>
</head>
<body class="top_body" onload="loadDefault();"  style="margin-top: 0px; margin-left: 0px; margin-right: 0px; margin-bottom: 0px;  background-color: #eeeeee;">
    <div id="wrap">
        <div id="header">
           <c:import charEncoding="UTF-8" url="/common/jsp/topInclude.jsp"></c:import> 
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
         <div id="pg_title" style="margin-left: 246px;">
            <h1></h1>
            <div class="mset_box"><a href="javascript:goMyMenu();" class="mbookmark"><span>bookmark</span></a><a href="javascript:goMyLink();" class="mlinked"><span>linkedmenu</span></a></div>
         </div><!-- 타이틀 -->
           <div id="content" style="margin-left: 246px;"><!--nav_left를 접었을때 41px, 펼쳤을대 246px-->
              <div class="content_wrap">
                 <div id='bodyDiv' style="margin-top: 3px; display: none; margin: 0px; padding: 0px; width: auto;">
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
         <div id="pg_title" style="margin-left: 41px;">
            <h1></h1>
            <div class="mset_box"><a href="javascript:goMyMenu();" class="mbookmark"><span>bookmark</span></a><a href="javascript:goMyLink();" class="mlinked"><span>linkedmenu</span></a></div>
         </div><!-- 타이틀 -->
           <div id="content" style="margin-left: 41px;"><!--nav_left를 접었을때 41px, 펼쳤을대 246px-->
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
//loadPage();

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
<%--===========================================================================
Menu 를 구성하기 위한 JSP 이다.
author  ssong
version $Id: menuInclude.jsp,v 1.4 2014/03/06 04:34:43 javaworker Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="common.util.CommonUtil"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/c-rt.tld" prefix="c-rt"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%
String [] menuPath = (String [])request.getAttribute("menuPath");
String pMenuId = "";
String menuId = "";
if(menuPath != null)
{
    pMenuId = menuPath[0];
    menuId = menuPath[1];
}

String scrollTopValue = request.getParameter("menuScrollTop");
%>

<style type="text/css">
 <!--
.scroll-wrapper {
    overflow: hidden !important;
    padding: 0 !important;
    position: relative;
    height: calc(100% - 23px) !important; 
    /*height: 100% !important; */
}
 
.scroll-wrapper > .scroll-content {
    border: none !important;
    box-sizing: content-box !important;
    height: auto;
    left: 0;
    margin: 0;
    max-height: none;
    max-width: none !important;
    overflow: scroll !important;
    padding: 0;
    position: relative !important;
    top: 0;
    width: auto !important;
}
 
.scroll-wrapper > .scroll-content::-webkit-scrollbar {
    height: 0;
    width: 0;
}
 
.scroll-element {
    display: none;
}
.scroll-element, .scroll-element div {
    box-sizing: content-box;
}
 
.scroll-element.scroll-x.scroll-scrollx_visible,
.scroll-element.scroll-y.scroll-scrolly_visible {
    display: block;
}
 
.scroll-element .scroll-bar,
.scroll-element .scroll-arrow {
    cursor: default;
}
 
.scroll-textarea {
    border: 1px solid #cccccc;
    border-top-color: #999999;
}
.scroll-textarea > .scroll-content {
    overflow: hidden !important;
}
.scroll-textarea > .scroll-content > textarea {
    border: none !important;
    box-sizing: border-box;
    height: 100% !important;
    margin: 0;
    max-height: none !important;
    max-width: none !important;
    overflow: scroll !important;
    outline: none;
    padding: 2px;
    position: relative !important;
    top: 0;
    width: 100% !important;
}
.scroll-textarea > .scroll-content > textarea::-webkit-scrollbar {
    height: 0;
    width: 0;
}
 
 
 
 
/*************** SIMPLE INNER SCROLLBAR ***************/
 
.scrollbar-inner > .scroll-element,
.scrollbar-inner > .scroll-element div
{
    border: none;
    margin: 0;
    padding: 0;
    position: absolute;
    z-index: 10;
}
 
.scrollbar-inner > .scroll-element div {
    display: block;
    height: 100%;
    left: 0;
    top: 0;
    width: 100%;
}
 
.scrollbar-inner > .scroll-element.scroll-x {
    bottom: 2px;
    height: 8px;
    left: 0;
    width: 100%;
}
 
.scrollbar-inner > .scroll-element.scroll-y {
    height: 100%;
    right: 1px;
    top: 0;
    width: 4px;
}
 
.scrollbar-inner > .scroll-element .scroll-element_outer {
    overflow: hidden;
}
 
.scrollbar-inner > .scroll-element .scroll-element_outer,
.scrollbar-inner > .scroll-element .scroll-element_track,
.scrollbar-inner > .scroll-element .scroll-bar {
    -webkit-border-radius: 0px;
    -moz-border-radius: 0px;
    border-radius: 0px;
}
 
.scrollbar-inner > .scroll-element .scroll-element_track,
.scrollbar-inner > .scroll-element .scroll-bar {
    -ms-filter:"progid:DXImageTransform.Microsoft.Alpha(Opacity=20)";
    filter: alpha(opacity=20);
    opacity: 0.2;
}
 
.scrollbar-inner > .scroll-element .scroll-element_track { background-color: #e0e0e0; }
.scrollbar-inner > .scroll-element .scroll-bar { background-color: #c2c2c2; }
.scrollbar-inner > .scroll-element:hover .scroll-bar { background-color: #919191; }
.scrollbar-inner > .scroll-element.scroll-draggable .scroll-bar { background-color: #919191; }
 
 
/* update scrollbar offset if both scrolls are visible */
 
.scrollbar-inner > .scroll-element.scroll-x.scroll-scrolly_visible .scroll-element_track { left: -12px; }
.scrollbar-inner > .scroll-element.scroll-y.scroll-scrollx_visible .scroll-element_track { top: -12px; }
 
 
.scrollbar-inner > .scroll-element.scroll-x.scroll-scrolly_visible .scroll-element_size { left: -12px; }
.scrollbar-inner > .scroll-element.scroll-y.scroll-scrollx_visible .scroll-element_size { top: -12px; }
 //-->
 </style>

<script>

function bindMouseOpen()
{
	$('.b_menuopen').bind("click",function(){

		var fileName = $(this).prev().attr("alt");
		var menuId = $(this).prev().attr("id"); 
		
		/* var fileName = $('.selected').attr("alt");
	    var menuId = $('.selected').attr("id"); */

	    //$.globalEval("goMenuPage('"+fileName+"&menuId="+menuId+"');");
	    $.globalEval("openQuickPage('', '"+fileName+"');");
	}).css("cursor","pointer");
}

/**
 * 메뉴구분(main,user,linked) 펼치기 닫기
 */
function openMenu(id){
	 var obj = document.getElementById(id);
	if (obj.style.display == "none")
    {
        obj.style.display = "";
    }
    else
    {
        obj.style.display = "none";
    }
}
</script>
<%
    String jsonMenuString = String.valueOf(session.getAttribute("JSONMENU"));
%>


<div class="nav_box scrollbar-inner" align="left" style="height:100%">
	<ul>
		<li>
			<div class="mbox m_current">
				<a class="mb"><bean:message key="LABEL.Menu"/></a>
			</div>
			<div id="mainMenu" class="smb" style="height:auto; min-height:350px;">
				
			</div>
		</li>
		<li>
			<div class="mbox">
				<a class="mb"><bean:message key="LABEL.UserMenu"/></a><p class="b_open" style="display:none"><a><span>열기</span></a></p>
			</div>
			<div id="userMenuDiv" class="smb" style="height:auto; display:none;">
				<div class="umenu_box usermenu">
					<ul>
<%
    Object userMenuObj = session.getAttribute("USERMENU");
    // 저장되는 ArrayList이다.
    ArrayList<Map> userMenuList = null;

    if (userMenuObj != null)
    {           
        userMenuList = (ArrayList<Map>)userMenuObj;        
    }
%>
<%
						int userMenuCount = 0;
						if (userMenuList != null && userMenuList.size() > 0)
						{
						    // Main Menu의 갯수
						    userMenuCount = userMenuList.size();
						    // Main Menu를 구성한다.
						    for (int i=0; i < userMenuCount; i++)
						    {
						        Map map = (Map)userMenuList.get(i);
						        String menuName = String.valueOf(map.get("MENUDESC"));
						        String fileName = String.valueOf(map.get("FILENAME"));
						        String curMenuId = String.valueOf(map.get("MENUID"));
						        String param     = String.valueOf(map.get("PARAM"));
						        String keyNo     = String.valueOf(map.get("KEYNO"));
						        if(param =="null") param = "";
						        %>
								<c:set var="keyNo" value="<%=keyNo%>" />
						        <li ><a class="m_child" alt="<%=fileName%>?<%=param%>" id="<%=curMenuId%>"><bean:message key="MENU.${keyNo}"/></a></li>
						        <%
						    }
						}
%>
					</ul>
				</div>
			</div><!--User Menu - smb end-->
		</li>
		<li>
			<div class="mbox m_current">
				<a class="mb"><bean:message key="LABEL.LinkedMenu"/></a><p class="b_open" style="display:none"><a href="#"><span>열기</span></a></p>
			</div>
			<div id="linkedMenuDiv" class="smb" style="height:auto; border-bottom:1px solid #ccc; display:block;">
				<div class="umenu_box linkedMenu">
					<ul>
<%
	String [][] linkedMenu = (String [][])session.getAttribute("LINKEDMENU"); //linkedMenu[0]:menuId, [1]:linkedMenuDesc, [2]:fileName, [3]:ord_no, [4]:keyNo

    if (linkedMenu != null)
    {
        for (int i=0, length=linkedMenu.length; i<length; i++)
        {
%>
	<c:set var="selectedMenuId" value="<%=menuId%>" />
    <c:set var="menuId" value="<%=linkedMenu[i][0]%>" />
    <c:set var="menuKeyNo" value="<%=linkedMenu[i][4]%>" />
    <c:choose>
   		<c:when test="${selectedMenuId == menuId}">
    		<li><a class="m_child"  alt="<%=linkedMenu[i][2]%>"><bean:message key="MENU.${menuKeyNo}"/></a></li>
    	</c:when>
    </c:choose>
<%
        }   // end for
    }   // end if
%>
					</ul>
				</div>
			</div><!--Linked Menu - smb end-->
		</li>
	</ul>

</div>
<script>

var myTree, myPop, isPopupCk = false;
myTree = new dhtmlXTreeObject("mainMenu","100%","100%",0);
myTree.setImagesPath('<c:url value="common/dhtmlxSuite/skins/skyblue/imgs/dhxtree_skyblue/" />');

myTree.enableSmartXMLParsing(true);
myTree.attachEvent("onClick",function(id){

	if(isPopupCk)
	{
		isPopupCk = false; 
		return false;
	}
	
	//alert(myTree._globalIdStorageFind(id).htmlNode);
	
	var pageId = myTree.getUserData(id,"PAGEID");
	var menuId = myTree.getUserData(id,"MENUID");
	var _url = myTree.getUserData(id,"URL");
	
	if(typeof pageId == "undefined") myTree.openItem(id);
	else $.globalEval("goMenuPage('"+_url+"&menuId="+menuId+"');");
		
    return true;
});

//myPop = new dhtmlXPopup();
//myPop.attachHTML("<p class='b_menuopen'><a><span>열기</span></a></p>");
myTree.attachEvent("onMouseIn", function(id){
		$('.b_menuopen').remove();
		
        var node = myTree._globalIdStorageFind(id).htmlNode;
        var _pName = myTree.getUserData(id,"PAGEID");
        if(typeof _pName != "undefined")
        {
        	$(node).find('span').append("<p class='b_menuopen' id='"+id+"'><a><span>열기</span></a></p>");
            $('.b_menuopen').bind("click",function(){
            	isPopupCk = true;
	            var id = $(this).attr("id");
		       	var pageId = myTree.getUserData(id,"PAGEID");
        		var menuId = myTree.getUserData(id,"MENUID");
        		var _url = myTree.getUserData(id,"URL");

        		$.globalEval("openQuickPage('', '"+_url+"');");

        	}).css("cursor","pointer");
        }
       // console.log($(node).offset());
        /* var x = $(node).offset().left;
        var y = $(node).offset().top;
        var w = node.offsetWidth;
        var h = node.offsetHeight;
        myPop.show(x + w/2,y,w,h); */
    });
myTree.attachEvent("onMouseOut", function(id){
   // myPop.hide();
});

myTree.attachEvent("onXLE", function(grid_obj,count){
	/* $('td.standartTreeRow > span').on({
		"mouseenter":function(){
			$('.b_menuopen').remove();
			$(this).parent().append($("<p class='b_menuopen'><a><span>열기</span></a></p>"));
		},
		"mouseleave":function(){
			
		}
	}); */

	$('#mainMenu').css("display","block");
	
	myTree.selectItem(<%=menuId%>,false); //select without onClick event
	myTree.focusItem(<%=menuId%>); //auto-scroll
	
});

myTree.parse(<%=jsonMenuString%>,"json");

//Inner Scroll
jQuery('.scrollbar-inner').scrollbar();
jQuery('.scroll-wrapper').css('height', jQuery('.scroll-wrapper').css('height').replace("px","") - 23);


jQuery(document).ready(function(){
	
});

$(window).load(function(){

	//유저메뉴 바인드
	$('.usermenu > ul > li > a').on({
		click:function(){
			$('a').removeClass("selected");
			$('.b_menuopen').remove();
			$(this).addClass("selected");
		    $(this).parent().append($("<p class='b_menuopen'><a><span>열기</span></a></p>"));
		    
		    var fileName = $('.selected').attr("alt");
		    var menuId = $('.selected').attr("id");
		    selectedMenuName = $('.selected').text();
			$.globalEval("goMenuPage('"+fileName+"&menuId="+menuId+"');");
		}/* ,
		dblclick:function(){
			var menuId = $(this).attr("alt");
			$.globalEval("goMenuPage('"+menuId+"');");
		} */
	});
	
	$('.linkedMenu > ul > li > a').on({
		click:function(){
			$('a').removeClass("selected");
			$('.b_menuopen').remove();
			$(this).addClass("selected");
		    $(this).parent().append($("<p class='b_menuopen'><a><span>열기</span></a></p>").on("click",function(e){
				var menuId = $('.selected').attr("alt");
				$.globalEval("openQuickPage('', '"+menuId+"');");
		    }));
		    
		    var menuId = $('.selected').attr("alt");
			$.globalEval("openQuickPage('', '"+menuId+"');");
		}/* ,
		dblclick:function(){
			var menuId = $(this).attr("alt");
			$.globalEval("openQuickPage('', '"+menuId+"');");
		} */
	});
});
</script>
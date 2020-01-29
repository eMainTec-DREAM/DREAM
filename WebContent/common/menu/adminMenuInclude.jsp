<%--===========================================================================
author  ssong
version $Id: adminMenuInclude.jsp,v 1.2 2014/02/06 08:28:00 javaworker Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/c-rt.tld" prefix="c-rt"%>
<script>
/**
 * 메뉴구분(main,user,linked 펼치기 닫기
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
<div class="nav_box" align="left">
	<ul>
		<li>
			<div class="mbox m_current">
				<a class="mb"><bean:message key="LABEL.Menu"/></a>
			</div>
			<div id="mainMenuDiv" class="smb" style="height:350px;">
				<div class="tree_box">
					<ul>
<%

	String [] menuPath = (String [])request.getAttribute("menuPath");
	String menuId = "";
	
	if (menuPath != null)
	{
	    menuId = menuPath[1];
	}
	// login 후 main에서 셋팅된 admin menu를 session에서 가지고 온다.
	String [][] adminMenu = (String [][])session.getAttribute("ADMINMENU");
	int adminMenuCount = 0;
	adminMenuCount = adminMenu.length;
    if (adminMenu != null)
    {
        for (int i=0, length=adminMenu.length; i<length; i++)
        {
    %>
    <c:set var="selectedMenuId" value="<%=menuId%>" />
    <c:set var="menuId" value="<%=adminMenu[i][2]%>" />
    <c:set var="keyNo" value="<%=adminMenu[i][5]%>" />
    <c:choose>
   		<c:when test="${selectedMenuId == menuId}">
    		<li id="ADMIN_MENU_<%=adminMenu[i][2]%>"><a class="m_child selected" id="<%=adminMenu[i][2]%>" title="<%=adminMenu[i][1]%>?"><bean:message key="MENU.${keyNo}"/></a></li>
    	</c:when>
    	<c:otherwise>
    		<li id="ADMIN_MENU_<%=adminMenu[i][2]%>"><a class="m_child" id="<%=adminMenu[i][2]%>" title="<%=adminMenu[i][1]%>?"><bean:message key="MENU.${keyNo}"/></a></li>
    	</c:otherwise>
    </c:choose>
    <%
        }   // end for
    }   // end if
    %>
    				</ul>
    			</div>
    		</div>
    	</li>
    	<li>
			<div class="mbox">
				<a class="mb"><bean:message key="LABEL.UserMenu"/></a><p class="b_open" style="display:none"><a href="#"><span>열기</span></a></p>
			</div>
			<div id="userMenuDiv" class="smb" style="height:100px; display:none;">
				<div class="umenu_box">
					<ul>
					    <!-- 
						<li ><a href="#" class="m_child">예방정비관리</a></li>
						<li><a href="#" class="m_child">년차보수관리</a></li>
						<li><a href="#" class="m_child">표준자료관리</a></li>
						<li><a href="#" class="m_child">보전예산관리</a></li>
						<li><a href="#" class="m_child">공사품의관리</a></li>
						<li><a href="#" class="m_child">자재자료관리</a></li>
						<li><a href="#" class="m_child">표준자료관리</a></li>
						<li><a href="#" class="m_child">보전예산관리</a></li>
						<li><a href="#" class="m_child">공사품의관리</a></li>
						<li><a href="#" class="m_child">자재자료관리</a></li>
						 -->
					</ul>
				</div>
			</div><!--User Menu - smb end-->
		</li>
		<li>
			<div class="mbox">
				<a class="mb"><bean:message key="LABEL.LinkedMenu"/></a><p class="b_open" style="display:none"><a href="#"><span>열기</span></a></p>
			</div>
			<div id="linkedMenuDiv" class="smb" style="height:100px; border-bottom:1px solid #ccc; display:none;">
				<div class="umenu_box">
					<ul>
					    <!-- 
						<li><a href="#" class="m_child">예방정비관리</a></li>
						<li><a href="#" class="m_child">년차보수관리</a></li>
						<li><a href="#" class="m_child">표준자료관리</a></li>
						<li><a href="#" class="m_child">보전예산관리</a></li>
						<li><a href="#" class="m_child">공사품의관리</a></li>
						<li><a href="#" class="m_child">자재자료관리</a></li>
						<li><a href="#" class="m_child">표준자료관리</a></li>
						<li><a href="#" class="m_child">보전예산관리</a></li>
						<li><a href="#" class="m_child">공사품의관리</a></li>
						<li><a href="#" class="m_child">자재자료관리</a></li>
						 -->
					</ul>
				</div>
			</div><!--Linked Menu - smb end-->
		</li>
    </ul>
</div>
<script>
if(document.getElementById('ADMIN_MENU_'+menuId))
	document.getElementById('ADMIN_MENU_'+menuId).scrollIntoView();

function bindMouseOpen()
{
	$('.b_menuopen').bind("click",function(){

		var fileName = $(this).prev().attr("title");
		
		/* var fileName = $('.selected').attr("alt");
	    var menuId = $('.selected').attr("id"); */

	    //$.globalEval("goMenuPage('"+fileName+"&menuId="+menuId+"');");
	    $.globalEval("openQuickPage('', '"+fileName+"');");
	}).css("cursor","pointer");
}

$(window).load(function(){
	
	$('.selected').parent().append($("<p class='b_menuopen'><a><span>열기</span></a></p>").on("click",function(e){
		var menuId = $('.selected').prop("title");
		$.globalEval("goMenuPage('"+menuId+"');");
    }));
	
	$("li[id^='ADMIN_MENU_'] > a").bind({
		click:function(){
			$('a').removeClass("selected");
			//$('.b_menuopen').remove();
			$(this).addClass("selected");
		    $(this).parent().append($("<p class='b_menuopen'><a><span>열기</span></a></p>"));
		    
	    	var fileName = $('.selected').attr("title");
		    var menuId = $('.selected').attr("id");
		    $.globalEval("goMenuPage('"+fileName+"&menuId="+menuId+"');");
		},
		mouseover:function() {
			$('.b_menuopen').remove();
			$(this).parent().append($("<p class='b_menuopen'><a><span>열기</span></a></p>"));
			bindMouseOpen();	
		}/* ,
		dblclick:function(){
			var menuId = $(this).prop("title");
			$.globalEval("goMenuPage('"+menuId+"');");
		} */
	}).css("cursor","pointer");
	
	$('.b_menuopen').bind("click",function(){
		var fileName = $('.selected').attr("title");
	    var menuId = $('.selected').attr("id");
	    $.globalEval("goMenuPage('"+fileName+"&menuId="+menuId+"');");
	}).css("cursor","pointer");
});
</script>
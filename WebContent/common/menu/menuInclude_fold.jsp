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
String _menuId = (String)request.getAttribute("menuId");
String pMenuId = "";
String menuId = "";
if(menuPath != null)
{
    pMenuId = menuPath[0];
    menuId = menuPath[1];
}

if(menuId == "" || menuId == null) menuId = _menuId;

Object object = session.getAttribute("MENU");

// 저장되는 ArrayList이다.
ArrayList<Map> menuList = null;

if (object != null)
{           
    menuList = (ArrayList<Map>)object;        
}

%>

<style type="text/css">
 <!--
.scroll-wrapper {
    overflow: hidden !important;
    padding: 0 !important;
    position: relative;
    /*height: calc(100% - 23px) !important;*/ 
    height: 97% !important;
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

<div class="nav_box scrollbar-inner" align="left" style="min-height: 350px;">
	<ul>
		<li>
			<div class="mboxfold temp">
				<a class="mb"><bean:message key="LABEL.Menu"/></a>
			</div>
			<div id="mainMenu" class="smb" style="height:auto;display: none;">
				<div class="tree_box">
					<ul>
					<%
						// 넘겨진 Menu가 0 보다 큰경우
						if (menuList != null && menuList.size() > 0)
						{
						    List<Map> tempSubMenu = null;

							int i = 0;
						    // Main Menu를 구성한다.
						    for(Map menuObj : menuList)
							{
						    	if(!"0".equals(String.valueOf(menuObj.get("PMENUID")))) continue;
						    	
						    	String keyNo = String.valueOf(menuObj.get("KEYNO"));
						    	String keyType = String.valueOf(menuObj.get("KEYTYPE"));
						    	String mainMenuId = String.valueOf(menuObj.get("MENUID"))=="null"?"":String.valueOf(menuObj.get("MENUID"));

						        tempSubMenu = CommonUtil.getSubMenu(menuList, mainMenuId);
						        String isParent = "m_child";
						        if(tempSubMenu.size() > 0) isParent = "m_parent";
					%>
						<c-rt:set value='<%=keyNo%>' var='mainMenuKeyNo'></c-rt:set>
						<c-rt:set value='<%=keyType%>' var='mainMenuKeyType'></c-rt:set>
						<li id="TD_ANS_<%=i%>"><a class="<%=isParent%>" id="<%=mainMenuId%>"><bean:message key="${mainMenuKeyType}.${mainMenuKeyNo}"/></a>
					<% if(tempSubMenu.size() > 0) { %>
							<ul style="display: none;">
					<%		// Sub Menu를 구성한다.
								for (Map subMenu : tempSubMenu)
								{
									String subMenuId = String.valueOf(subMenu.get("MENUID"));
									String subkeyNo = String.valueOf(subMenu.get("KEYNO"));
									String subkeyType = String.valueOf(subMenu.get("KEYTYPE"));
									String selected = "";
									isParent = "m_child";
									
									List subMenuList = CommonUtil.getSubMenu(menuList, subMenuId);
									if(subMenuId.equals(menuId)) selected = "selected";
									if(subMenuList.size() > 0) isParent = "m_parent";
					%>
									<c:set value='<%=subkeyNo%>' var='subMenuKeyNo'/>
									<c:set value='<%=subkeyType%>' var='subMenuKeyType'/>
									<li id="TD_SUB_MENU_<%=subMenuId%>"><a class="<%=isParent%> <%=selected%>" id="<%=subMenuId%>"><bean:message key="MENU.${subMenuKeyNo}"/></a>
									
									<%
									if(subMenuList.size() > 0)	
									{
										%>
										<ul style="display: none;">
										<%		// Sub Menu를 구성한다.
													for (Object subMenuObj : subMenuList)
													{
														Map subMenuMap = (Map)subMenuObj;
														String subMenuId2 = String.valueOf(subMenuMap.get("MENUID"));
														String subkeyNo2 = String.valueOf(subMenuMap.get("KEYNO"));
														String selected2 = "";
			
														if(subMenuId2.equals(menuId)) selected2 = "selected";
					
										%>
											<c:set value='<%=subkeyNo2%>' var='subMenuKeyNo2'/>
											<li id="TD_SUB_MENU_<%=subMenuId2%>">
												<a class="m_child <%=selected2%>" id="<%=subMenuId2%>"><bean:message key="MENU.${subMenuKeyNo2}"/></a>
											</li>
									
												<% 
													} //End of For
									%> </ul> <%
									} //ENd of if subMenuList
						%> 
						
						</li>
						
					   
					<%
								} // Sub Menu 구성 for
					%>
							</ul>
					<% }//End if %>
						</li>
					<%
			        		i++;
							}
					%>  
					</ul>
				</div>
			</div>
		</li>
		<li>
			<div class="mboxfold">
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
						    for (int k=0; k < userMenuCount; k++)
						    {
						        Map map = (Map)userMenuList.get(k);
						        String curMenuId = String.valueOf(map.get("MENUID"));
						        String keyNoUser     = String.valueOf(map.get("KEYNO"));

						        %>
								<c:set var="keyNo" value="<%=keyNoUser%>" />
						        <li ><a class="m_child" id="<%=curMenuId%>"><bean:message key="MENU.${keyNo}"/></a></li>
						        <%
						    }
						}
%>
					</ul>
				</div>
			</div><!--User Menu - smb end-->
		</li>
		<li>
			<div class="mboxfold temp">
				<a class="mb"><bean:message key="LABEL.LinkedMenu"/></a><p class="b_open" style="display:none"><a href="#"><span>열기</span></a></p>
			</div>
			<div id="linkedMenuDiv" class="smb" style="height:auto; border-bottom:1px solid #ccc; display:none;">
				<div class="umenu_box linkedMenu">
					<ul>
<%
	String [][] linkedMenu = (String [][])session.getAttribute("LINKEDMENU"); //linkedMenu[0]:menuId, [1]:linkedMenuDesc, [2]:fileName, [3]:ord_no, [4]:keyNo

    if (linkedMenu != null)
    {
        for (int j=0, length=linkedMenu.length; j<length; j++)
        {
%>
	<c:set var="selectedMenuId" value='<%=menuId%>' />
    <c:set var="menuId" value="<%=linkedMenu[j][6]%>" />
    <c:set var="menuKeyNo" value="<%=linkedMenu[j][4]%>" />
    <c:choose>
   		<c:when test="${selectedMenuId == menuId}">
    		<li><a class="m_child"  id="<%=linkedMenu[j][0]%>"><bean:message key="MENU.${menuKeyNo}"/></a></li>
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

<%
}
%>     
<script>

jQuery(document).ready(function(){

    //Open Sub Menu
    $("li > a.m_parent").on({
    	"click":function(){
    		var subMenuObj = $(this).parent().find('ul');
    		if(subMenuObj.size() > 0) subMenuObj.eq("0").css("display",subMenuObj.eq("0").css("display")=="none"?"block":"none");
    		else 
    		{
    			var menuId = $(this).prop("id");
    		    var fileName = findMenuUrl(menuId);
    		    selectedMenuName = $(this).text();

    		    if(fileName.isExLink == "Y")
    		    {
    		    	openELink(fileName.exLink, fileName.pValue, fileName.isGetLink);
    		    }
     		    else
     		    { 	 		    	
	    			$.globalEval("goMenuPage('"+fileName.url+"&menuId="+menuId+"');");
     		    }
    		}
    		
    		if(subMenuObj.size() > 0 && "block" == subMenuObj.eq("0").css("display")) $(this).addClass("open");
    		else $(this).removeClass("open");
    	},
    	mouseover:function() {
    		var subMenuObj = $(this).parent().find('ul');
    		if(subMenuObj.size() == 0)
    		{
    			var menuId = $(this).prop("id");
    			var fileName = findMenuUrl(menuId);

    			if(fileName.url.indexOf("?") > 0 )
    			{
    				$('.b_menuopen').remove();
    				$(this).parent().append($("<p class='b_menuopen'><a><span>열기</span></a></p>"));
    				bindMouseOpen();	
    			}
    		}

		}
    });
    
	$("li > a.m_child").on({
		click:function(){
			$('a').removeClass("selected");
			$(this).addClass("selected");

		    var menuId = $(this).prop("id");
		    var fileName = findMenuUrl(menuId);
		    selectedMenuName = $(this).text();

 		    if(fileName.isExLink == "Y")
		    {
 		    	openELink(fileName.exLink,fileName.pValue, fileName.isGetLink);
		    }
 		    else
 		    { 		    	
				if($(this).parents('.linkedMenu').size() > 0)
					$.globalEval("openQuickPage('', '"+fileName.url+"&menuId="+menuId+"');");
				else 
					$.globalEval("goMenuPage('"+fileName.url+"&menuId="+menuId+"');");
 		    }
		},
		mouseover:function() {
			var menuId = $(this).prop("id");
			var fileName = findMenuUrl(menuId);

			if(fileName.url.indexOf("?") > 0 )
			{
				$('.b_menuopen').remove();
				$(this).parent().append($("<p class='b_menuopen'><a><span>열기</span></a></p>"));
				bindMouseOpen();	
			}
		}
	}).css("cursor","pointer");
	
	//Menu Inner Scroll
    jQuery('.scrollbar-inner').scrollbar();
    
    //Open All Nodes to MenuId

    var menuObj = findMenuJObj(<%=menuId%>);
    
    openToMenu(<%=menuId%>);
    
    <%-- if(menuObj && menuObj.url.split("?")[0] == currentPageId) openToMenu(<%=menuId%>);
    else
    {
    	for(var i in menuJArray)
        {
            if(menuJArray[i].url.split("?")[0] == currentPageId) openToMenu(menuJArray[i].id);
        }
    } --%>
    
});

function openELink(url, param, isGetLink)
{
	if(isGetLink == "Y"){
		window.open(url, "_blank");
	}else{
		var popWidth = 1010;
		var popHeight = 640;

		// pop up이 중앙에 위치하게 한다.
		var TopPosition  = (screen.height/2 - popHeight/2);
		var LeftPosition = (screen.width/2 - popWidth/2);
		
		var pos = "width=" + popWidth + ",height=" + popHeight + "" +
			      ",top=" + TopPosition + "px,left=" + LeftPosition + "px" +
			      ",toolbar=no,scrollbars=yes,resizable=yes";
		
		openWindowWithPost(url, "EXTLINK", param, pos);
	}
}

</script>
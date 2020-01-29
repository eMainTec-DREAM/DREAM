<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="common.util.CommonUtil"%>
<%@ taglib uri="/WEB-INF/tld/c-rt.tld" prefix="c-rt"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>

<%
String [] menuPath = (String [])request.getAttribute("menuPath");
String pMenuId = "";
String menuId = "";
if(menuPath != null)
{
    pMenuId = menuPath[0];
    menuId = menuPath[1];
}

Object object = session.getAttribute("MENU");

// 저장되는 ArrayList이다.
ArrayList<Map> menuList = null;

if (object != null)
{           
    menuList = (ArrayList<Map>)object;        
}

%>

<div class="nav_header">
 	<div class="b_home"><a href="#"><span>Home</span></a></div>
     <div class="b_mmenu current"><a href="#"><span>Menu</span></a></div>
     <div class="b_setting"><a href="#"><span>Setting</span></a></div>
     <div class="b_close"><a href="#"><span>Close</span></a></div>
    </div>
 <div class="menu_box">

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
			    	if(!"0".equals(String.valueOf(menuObj.get("pMenuId")))) continue;
			    	
			    	String keyNo = String.valueOf(menuObj.get("keyNo"));
			    	String keyType = String.valueOf(menuObj.get("keyType"));
			    	String mainMenuId = String.valueOf(menuObj.get("menuId"))=="null"?"":String.valueOf(menuObj.get("menuId"));

			        tempSubMenu = CommonUtil.getSubMenu(menuList, mainMenuId);
			        String isParent = "";
			        if(tempSubMenu.size() > 0) isParent = "ch1";
		%>
		<c-rt:set value='<%=keyNo%>' var='mainMenuKeyNo'></c-rt:set>
		<c-rt:set value='<%=keyType%>' var='mainMenuKeyType'></c-rt:set>
		<li><a class="<%=isParent%>" id="<%=mainMenuId%>"><bean:message key="${mainMenuKeyType}.${mainMenuKeyNo}"/></a>
				<% if(tempSubMenu.size() > 0) { %>
							<ul style="display: none;">
					<%		// Sub Menu를 구성한다.
								for (Map subMenu : tempSubMenu)
								{
									String subMenuId = String.valueOf(subMenu.get("menuId"));
									String subkeyNo = String.valueOf(subMenu.get("keyNo"));
									String subkeyType = String.valueOf(subMenu.get("keyType"));
									
									List subMenuList = CommonUtil.getSubMenu(menuList, subMenuId);
					%>
									<c:set value='<%=subkeyNo%>' var='subMenuKeyNo'/>
									<c:set value='<%=subkeyType%>' var='subMenuKeyType'/>
									<li id="TD_SUB_MENU_<%=subMenuId%>">
										<a id="<%=subMenuId%>"><bean:message key="${subMenuKeyType}.${subMenuKeyNo}"/></a>
									<%
									if(subMenuList.size() > 0)	
									{
										%>
										<ul style="display: none;">
										<%		// Sub Menu를 구성한다.
													for (Object subMenuObj : subMenuList)
													{
														Map subMenuMap = (Map)subMenuObj;
														String subMenuId2 = String.valueOf(subMenuMap.get("menuId"));
														String subkeyNo2 = String.valueOf(subMenuMap.get("keyNo"));
														String subkeyType2 = String.valueOf(subMenu.get("keyType"));
					
										%>
											<c:set value='<%=subkeyNo2%>' var='subMenuKeyNo2'/>
											<c:set value='<%=subkeyType2%>' var='subMenuKeyType2'/>
											<li id="TD_SUB_MENU_<%=subMenuId2%>">
												<a id="<%=subMenuId2%>"><bean:message key="${subMenuKeyType2}.${subMenuKeyNo2}"/></a>
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
					  } //233 End For
						}
					%>  
    	</ul>
    <div class="b_logout"><a href="#">로그아웃</a></div>
</div>

<script>

jQuery(function($){
	
	//메뉴 닫기 
	$('.main_wrap,.b_close').on('click',function(e){
		var menuWidth = $('.nav_wrap').width() + 10;
		
		$('.nav_wrap').animate({
			right : "-"+menuWidth+"px"
		});
	});
	
	//메뉴 열기 
	$('.btn_mopen').on('click',function(e){
		var menuWidth = $('.nav_wrap').width() + 10;
		$('.nav_wrap').css("right","-"+menuWidth+"px");
		
		$('.nav_wrap').animate({
			right : "0px"
		});
	});
	
	$('.menu_box').find('.ch1,.ch2').on('click',function(e){

		var subMenuObj = $(this).next('ul').eq(0);
		if(subMenuObj)
		{
			if(subMenuObj.is(':hidden')) 
			{
				subMenuObj.show();
				$(this).removeClass('ch1').addClass('ch2');
			}
			else 
			{
				subMenuObj.hide();		
				$(this).removeClass('ch2').addClass('ch1');
			}
		}
	});
	
	$('.menu_box').find('a').not('.ch1,.ch2').on('click',function(e){
		var menuId = $(this).prop("id");
		
		var fileName = findMenuJObj(menuId).url.split("?")[0];
		var param = findMenuJObj(menuId).url.split("?")[1];
		
		if(param == "") param = "menuId="+menuId;
		else param = param + "&menuId="+menuId;
		
		submitPost(fileName, param);
		
	});

	
	openToMenu(<%=menuId%>);
	
});

function openToMenu(_menuId)
{
	var flag = false;
	$('.menu_box').find('li > a').each(function(e){
    	if(_menuId == $(this).prop("id"))
    	{ 
    		if($(this).is('.ch1')) $(this).removeClass('ch1').addClass('ch2');
    		//상위 메뉴 Open
    		$(this).parents("ul").css('display','block');
    		
    		flag = true;
    	}
    	
    });
	
	/* var _menuId;
	for(var i in menuJArray)
    {
         if(menuJArray[i].url.split("?")[0] == currentPageId) _menuId =  menuJArray[i].id;
    } */
}

</script>
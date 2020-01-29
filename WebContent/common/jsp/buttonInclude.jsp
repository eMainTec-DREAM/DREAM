<%--===========================================================================
하단 버튼
author  javaworker
version $Id: buttonInclude.jsp,v 1.2 2013/12/23 06:35:27 pochul2423 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/c-rt.tld" prefix="c-rt"%>
<%@ page import="common.util.CommonUtil"%>
<%@page import="java.util.Hashtable"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="common.bean.MwareConfig"%>

<%
String currentPageId = (String)request.getAttribute("currentPageId");
ArrayList buttonList = (ArrayList)request.getAttribute("pageButton");

ArrayList linkList = (ArrayList)request.getAttribute("pageLinks");
%>
<script>
$(window).ready(function(){
<%  

	if(!CommonUtil.isNullCheck(buttonList))
	{
		int btnLength = buttonList.size();
		for(int i = 0; btnLength > i; i++)
		{
		    Map button = (Map)buttonList.get(i);
		    
		    //String padeId = String.valueOf(button.get("FILENAME"));
		    String buttonId 	= String.valueOf(button.get("BUTTONID")).toLowerCase();
		    String buttonType 	= String.valueOf(button.get("BUTTONLOC")).toLowerCase();
		    String buttonDesc 	= String.valueOf(button.get("REMARK"));  //keyNo
		    String buttonImg 	= String.valueOf(button.get("BUTTONIMG"));
		    String isBasic   	= String.valueOf(button.get("ISBASIC"));
		    String isSetGroup 	= String.valueOf(button.get("ISSETGROUP"));
		 %>
        	makeButtonHtml("<%=currentPageId%>","<%=buttonId%>", "<%=buttonType%>", "<bean:message key='<%=buttonDesc%>'/>", "<%=buttonImg%>","<%=isBasic%>","<%=isSetGroup%>");
        <%

		}
	}


		if(!CommonUtil.isNullCheck(linkList))
		{
			int linkLength = linkList.size();
			for(int j = 0; linkLength > j; j++)
			{
			    Map link = (Map)linkList.get(j);
			    
			    String funcMethod 	= String.valueOf(link.get("LINKEDFUNCMETHOD")).toLowerCase();
			    String funcLabel 	= String.valueOf(link.get("LINKEDLABEL")); 
 			    String funcLoc 	    = String.valueOf(link.get("LIKEDFUNCLOC"));
		
			 %>
		    	makeLinkHtml("<%=currentPageId%>", "<%=funcLoc%>", "<%=funcMethod%>", "<bean:message key='<%=funcLabel%>'/>");
		    <%
		
			}
		}

	%>
	
	//$('.fb_group3').insertAfter('.fb_group2');
   	if(typeof afterBtnLoad == "function") afterBtnLoad(); 
   	
   	if(typeof loadPage == "function") loadPage(); 
   	
	if(typeof afterBtnLoadForSys == "function") afterBtnLoadForSys(); 


});

/**
 *Make Page Link function 
 */
function makeLinkHtml(pageId, funcLoc, funcName, funcLabel)
{
	var wrap = "sfb_wrap";
	//var linkObj = $("<a></a>").addClass("l_"+funcName+" "+pageId+"_link").bind("click",function(){
	var linkObj = $("<a></a>").addClass("link_"+funcName+" "+pageId+"_link b_linked").bind("click",function(){

		var funcId = funcName.substr(0,1).toUpperCase() + funcName.substr(1,funcName.length);

		$.globalEval("go"+funcId+"Link('"+pageId+"');");
		
	}).css("margin","2px");

	if(typeof COMMON_LINKED == "undefined") COMMON_LINKED = "Linked";
	//var showLinkObj = linkObj.clone().append("<span>"+funcLabel+"</span><div class='fb_view'><span>더보기</span></div>");
	var showLinkObj = $("<a></a>").addClass("b_linked").append("<span>"+COMMON_LINKED+"</span>");
	linkObj.append("<span>"+funcLabel+"</span>");

	if($('.fb_group3 > a').length > 0) $('.fb_group3 > a').remove();

	showLinkObj.bind('click',function(){
		var selObj = $(this).parents('.fb_group3').find('.sfb_wrap');

		if(DECORATOR_NAME != "innerTabPage")
		{
			var clickObj = $(this).parent();

			var offset = clickObj.offset();
			var height = clickObj.outerHeight(true);
	
			//위치 설정 
			selObj.css({
				"left":offset.left,
				"top" :offset.top + height+3
			}); 
		}
		else //innerTabPage에서는 다른 CSS 적용
		{
			//위치 설정 
			selObj.css({
				"position":"absolute",
				"top" : "auto",
				"float" : "right",
				"left" : "auto"
			}); 

		}
		
		if(selObj.is(':hidden')) selObj.show();
		else selObj.hide();
		
	});

	var pObj;
	if(!isNull(funcLoc))
	{
		pObj = $('.function_box.'+funcLoc+":not('.manual')").find('.fb_group3');
		
// 		showLinkObj.append(pObj.find('.'+wrap));
		pObj.prepend(showLinkObj);	
		pObj.find('.'+wrap).prepend(linkObj);
	}
	else
	{		
		if($(".function_box.filter:not('.manual')").length > 0) pObj = $(".function_box.filter:not('.manual')").find('.fb_group3');
		else pObj = $(".function_box:not('.manual')").find('.fb_group3');
		
		pObj.prepend(showLinkObj);	
		pObj.find('.'+wrap).prepend(linkObj);
	}

	//if(!$('.fb_group3').next().is(".b_line")) $('.fb_group3').after("<div class='b_line'></div>");
	
	if(pObj.next().is(".b_line"))pObj.next().hide();
	else if(pObj.next().is(".fb_group2"))  pObj.next().after(pObj);
}

function makeButtonHtml(pageId, buttonId, btnType, buttonDesc, buttonImg, isBasic, isSetGroup)
{
	//SAVE 버튼이 존재할 경우만 저장이 되게 한다.
	if(buttonId == "save") disabled = false;
	
	if(DECORATOR_NAME == "innerTabPage" || DECORATOR_NAME == "defaultPage") disabled = false;

	var btnId = buttonId.substr(0,1).toUpperCase() + buttonId.substr(1,buttonId.length);

	var btnObj = $("<a></a>").addClass("b_"+buttonId+" "+pageId+"_btn").bind("click",function(){
		//tabPageId = pageId.replace("TAB.","");
		//save인 경우 전역 함수 goSaveAll을 호출하게 함.
		if(btnId == "Save"){
			$.globalEval("goSaveAll();");
		}
		else if(btnId == "Delete"){
			$.globalEval("goDeleteAll();");
		}
		else if(btnId == "Open"){
			document.getElementsByName("strutsAction")[0].value = 1001;
			//alert("openQuickTabPage(FormQueryString("+pageId+"Form),'"+pageId.replace("List","Detail")+"');");
			if(typeof goOpenAction == "function") $.globalEval("goOpenAction('"+pageId+"');");
			else $.globalEval("openQuickTabPage(FormQueryString("+pageId+"Form),'"+pageId.replace("List","Detail")+"');");
		}
		else if(btnId == "Setting")
		{
			var gridId = $(this).parents('.section_wrap').find('.grid_area>div').prop("id");
			if(typeof gridId == "undefined") gridId = "gridbox";  //추후 수정 필요
			$.globalEval("go"+btnId+"('"+pageId+"','"+gridId+"');");
		}
		else if(btnId == "Excel")
		{
			var gridId = $(this).parents('.section_wrap').find('.gridbox').prop("id");
			if(typeof gridId == "undefined") gridId = "gridbox";  //추후 수정 필요
			_excelGridId = gridId;
			$.globalEval("go"+btnId+"('"+pageId+"');");
		}
		else if(btnId == "Approval")
		{
			$.globalEval("goBeforeApproval('"+pageId+"');");
		}
		else $.globalEval("go"+btnId+"('"+pageId+"');");
	}).css("margin","2px");//.prop("title",buttonDesc=="null"?"":buttonDesc);
	
	//Excel과 Setting은 Disable에서도 보여야 한다.
	if(btnId == "Setting" || btnId == "Excel" || btnId == "Colsetting")
		btnObj.addClass("fShow");

	var wrap = "";
	/* if(isBasic == "Y")
	{ */
		wrap = "fb_group2";
		
		if(buttonId == "close")
		{
			wrap = "fb_group1";
		}
		else
		{
			btnObj.append("<span>"+buttonDesc+"</span>");
		}
	/* } */
	/**else //드롭 다운 메뉴
	{
		wrap = "sfb_wrap";
		var showBtnObj = btnObj.clone().append("<span>"+buttonDesc+"</span><div class='fb_view'><span>더보기</span></div>");
		btnObj.append("<span>"+buttonDesc+"</span>");

		if($('.fb_group3 > a').length > 0) $('.fb_group3 > a').remove();

		showBtnObj.bind('click',function(){
			var selObj = $(this).parents('.fb_group3').find('.sfb_wrap');
			
			var clickObj = $(this).parent();
			var offset = clickObj.offset();
			var height = clickObj.outerHeight(true);
			//위치 설정 
			selObj.css({
				"left":offset.left,
				"top" :offset.top + height+3
			});

			if(selObj.is(':hidden')) selObj.show();
			else selObj.hide();

		});
		
		//$('.fb_group3').prepend(showBtnObj);
		
		if(btnType !="null")
			$('.function_box.'+btnType+":not('.manual')").find('.fb_group3').prepend(showBtnObj);	
		else
			$(".function_box:not('.manual')").find('.fb_group3').prepend(showBtnObj);	

		
		if(!$('.fb_group3').next().is(".b_line")) $('.fb_group3').after("<div class='b_line'></div>");
	}**/

	if(!isNull(btnType))
	{
		if(buttonId == "close")
			$('.function_box.'+btnType+":not('.manual')").find('.'+wrap).append(btnObj);	
		else
			$('.function_box.'+btnType+":not('.manual')").find('.'+wrap).prepend(btnObj);	
	}
	else
	{		
		if(buttonId == "close")
			$(".function_box:not('.manual')").find('.'+wrap).append(btnObj);	
		else
			$(".function_box:not('.manual')").find('.'+wrap).prepend(btnObj);
	}
	
	//그룹 세팅(라인설정)
	if(isSetGroup == "Y" && isBasic != "Y") $(".b_"+buttonId).before( "<hr color='#eeeeee'>");

	//이미지 임의 세팅
	if(!isNull(buttonImg))
	{
		var imgUrl = $(".b_"+buttonId).css("background-image");	
		if(imgUrl == "none") imgUrl = 'url("http://'+serverName+':'+serverPort+contextPath+'/common/images/ma/usrbtn/b_save.png")';
		
		var imgUrlArr = imgUrl.split("/");
		$(".b_"+buttonId).css({
			"background-image":imgUrl.replace(imgUrlArr[imgUrlArr.length - 1], buttonImg+'")'),
			"background-size" : "21px",
			"opacity" : "0.77"
			}).hover(function(){
				$(this).css("background-position","21");
				$(this).animate({opacity:1});
			},function(){
				$(this).css("background-position","21");
				$(this).animate({opacity:0.77});
			});
		
	}

	if(typeof hideButton == "function") hideButton(buttonId);
}
</script>
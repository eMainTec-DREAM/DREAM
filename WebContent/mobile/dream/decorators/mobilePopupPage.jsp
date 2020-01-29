<%--===========================================================================
popup decorator
author  ssong
version $Id: popupPage.jsp,v 1.7 2014/02/21 05:23:30 javaworker Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%
	String popupWidth = (String)request.getParameter("popupWidth");
	String _menuId = String.valueOf(request.getParameter("menuId"))=="null"?"":String.valueOf(request.getParameter("menuId"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><decorator:title /></title>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<c:import charEncoding="UTF-8" url="/mobile/dream/jsp/mobileCommonInclude.jsp"></c:import>
<link rel="stylesheet" href="<c:url value="/mobile/dream/css/style.css" />" type="text/css" />
<decorator:head />
</head>
<body class="top_body">
<div class="layer_pop large">
	<div class="pop_header">
    	<div class="pop_title"><decorator:title/></div>
        <div class="b_pclose"><a href="javascript:goCloseLayer();"><span>창닫기</span></a></div>
    </div>
    <div class="pop_content_wrap" style="overflow: hidden;">
         <decorator:body />
      	 <c:import charEncoding="UTF-8" url="/mobile/dream/jsp/mobileTabPageInclude.jsp"></c:import>
		 <c:import charEncoding="UTF-8" url="/mobile/dream/jsp/mobileButtonInclude.jsp" ></c:import>
    </div> <!-- end pwin_container -->
</div>
<!-- page 하단 공통 페이지 -->
<c:import charEncoding="UTF-8" url="/common/jsp/bottomInclude.jsp"></c:import>
<script language="javascript">

/**
 * Popup 페이지 열때 페이지를 중앙으로 위치시킨다.
 */
function popupLoadPage()
{
   	setTimeout("setPopupMinWidth();", 500);
}

function setPopupMinWidth()
{
    try
    {
		document.getElementById('pageTop').style.minWidth = (document.body.offsetWidth-32)+"px";
	    document.getElementById('tabFrame').style.minWidth = (document.body.offsetWidth-26)+"px";
	    // 비활성화 되어 있다면 활성화 시킨다.
	    window.focus();
	}
	catch(ex){}
}

function resizeTabFrame()
{
	heightSet();
}

function heightSet()
{
	var _curDiaId = popupDivId;
	var bodyHeight = 70;
 	var topPage = getTopPage();
 	
 	bodyHeight = $(parent.document).find('body').css('height').replace("px","")/2;

	/* if(topPage.$("#popupIframe"+_curDiaId).contents().find('.section_wrap').length > 0)
		topPage.$("#popupIframe"+_curDiaId).contents().find('.section_wrap').each(function(){
			if(!$(this).is(':hidden'))bodyHeight = bodyHeight + $(this).outerHeight(true);
		});
	else 
		topPage.$("#popupIframe"+_curDiaId).contents().find('.article_box').each(function(){
			if(!$(this).is(':hidden'))bodyHeight = bodyHeight + $(this).outerHeight(true);
		});
	
	$("iframe[name^='tabFrame']").each(function(){
		if(!$(this).is(':hidden')) bodyHeight = bodyHeight + $(this).outerHeight(true);
	}); */

	parent.$("#popupIframe"+_curDiaId).css({
			"height" : (bodyHeight-1) +5
		});
	
	var popupDivObj = parent.$("#popupDiv"+_curDiaId);
	popupDivObj.css({
		 "height" : bodyHeight + 5
	});
	
	topPage.listState = false; //리사이즈 끝나면 리스트 선택 풀어준다.
}

function positionSet()
{
	var popupWidth = '<%=popupWidth%>';
	var _curDiaId = popupDivId;
	var bodyHeight = 50;
	var bodyWidth = parent.$("#popupIframe"+_curDiaId).contents().width();

	bodyWidth= $(parent.document).find('body').css('width').replace("px","")/1.5;
	bodyHeight = $(parent.document).find('body').css('height').replace("px","")/2;

	parent.$("#popupIframe"+_curDiaId).css({
			"height" : (bodyHeight-1) +5,
			"width"  : bodyWidth
		});

	var top = $(parent.document).scrollTop() + ((parent.document.documentElement.clientHeight-document.body.offsetHeight)/4);
	//Title Bar가 숨지 않도록.
	if(top < 0) top = 50;
	
	var popupDivObj = parent.$("#popupDiv"+_curDiaId);
	popupDivObj.css({
		"top" 	: top+"px",
		"left"  : $(parent.document).scrollLeft() + ((parent.document.documentElement.clientWidth-document.body.offsetWidth)/2)+"px",
		"height" : bodyHeight + 5,
		"width" : bodyWidth 
	});

}
// body 의 onload 에 넣었다가 IE 8 에서 body 가 모두 로딩되기 전에 호출이 되어서 제일 뒤에 loadpage를 호출하게 수정하였음
loadPage();

$(window).load(function() {

	//setTimeout("loadAction();", 100);
	loadAction();
});

function loadAction()
{
	positionSet();
	
    $("input").bind('keydown',function(e){
    	if(e.keyCode == 13 && typeof goSearch == "function"){
    		if(!(myPop && myPop.isVisible())) goSearch();	
    	}
    });
    
     //필터 접기
	//foldFilter();

	//prevent trigger submit with one input in the form when enter key perssed.
	$('input,select').keypress(function(event) { return event.keyCode != 13; }); 
}

function goCloseLayer()
{
	//작업유형&작업형태 창에서 닫기 버튼을 클릭했을 때
	if(typeof afterSelectClose == "function"){
		afterSelectClose();
	}
	closeLayerPopup(this);
}

jQuery(function($){

	var orgTop;
	$(".pwin_content").scroll(function(){
		
		if(myPop && myPop.isVisible())
		{
			myPop.hide();
			/* var scrollTop = $(this).scrollTop();
			if(!orgTop) orgTop = scrollTop;

			$('.dhx_popup_dhx_skyblue').stop(false, false).animate({
	            top: myPopTop - scrollTop
	        }, 200);
			
			orgTop = scrollTop; */
		}
		
		genTabTitle();
	});
});	
</script> 
<c:import charEncoding="UTF-8" url="/common/jsp/buttonInclude.jsp" ></c:import>
</body>
<style type="text/css">
	
.top_button_div{text-align:right; margin-top:0px; margin-bottom:5px; margin-right:7px; width: 100%;}

</style>

</html>
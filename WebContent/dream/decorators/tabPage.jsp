<%--===========================================================================
Tab decorator
author  pochul2423
version $Id: tabPage.jsp,v 1.8 2014/02/21 05:23:30 javaworker Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@page import="common.bean.User"%>
<%@page import="java.util.Hashtable"%>
<%@page import="common.bean.MwareConfig"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><decorator:title/></title>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<c:import charEncoding="UTF-8" url="/common/jsp/commonInclude.jsp"></c:import>
<decorator:head />
</head>
<%
String currentPageId = (String)request.getAttribute("currentPageId");
%>
<body style="margin:0px;" bgcolor="#eeeeee">
<!-- CONTENT -->

    <div class="section_wrap" id="<%=currentPageId%>">
    	<div class="sheader_box">
			<div class="sheader_wrap detail"><a></a></div>
			<div class="stitle_wrap">
				<div class="view_icon"></div>
				<div class="stitle_tx"></div>
			</div>
			<!--<div class="stitle_box"><bean:message key="LABEL.Filter"/></div>  -->
			<div class="function_box detail">
				<div class="fb_group3">

					<div class="sfb_wrap" style="display:none;">
						
					</div>
				</div>
				<div class="fb_group2">
					
				</div>
				<div class="b_line"></div> 
				<div class="fb_group1">
					
				</div>
			</div>
		</div><!--sheader_box end-->

		<div id='bodyDiv' style="margin:0px; display: none; border:0px; ">
			<decorator:body />
		</div>
		<c:import charEncoding="UTF-8" url="/common/jsp/tabPageInclude.jsp"></c:import>
	</div>
<!-- page 하단 공통 페이지 -->
<c:import charEncoding="UTF-8" url="/common/jsp/bottomInclude.jsp"></c:import>
<script type="text/javascript" charset="UTF-8">

function resizeTabFrame()
{
	var topPage = getTopPage();
	topPage.listState = true; //리사이즈 중엔 재 선택 못하게 함
	
	var heightChrome = $('.section_wrap').outerHeight(true);

	parent.$("#tabFrameTAB\\."+currentPageId).css("height",heightChrome);

	if(typeof parent.resizeTabFrame == "function")
	{
		if(!opener) //innerTab detail
		{
			parent.resizeTabFrame(parent.getDecoName() == "innerTabPage"?20:0);
		} 
	}
	parent.$('.gridbox.gridbox_dhx_skyblue').css("width","100%");
}

/**
 * Tab Page Title Setting
 */
function setTabTitle()
{
	var tabTitle = parent.$('#TAB\\.'+currentPageId+"_title").text();
	if(tabTitle.trim() != '') $('.stitle_tx').prepend(tabTitle+" : ");
}

function afterBtnLoadForSys()
{
	setTabTitle();
}

$(window).load(function(){
	//if(typeof loadPage == "function") loadPage();	
	
	$('input,select').keypress(function(event) {
		return event.keyCode != 13; 
	}); 
	
	if(parent.getDecoName() == "innerTabPage")
	{
		$('body').addClass('inner');
	}	
	
	//Tab Detail Page가 Popup창에 로드되면..제목 고정
	if (window.opener) {
		if($('.sheader_box').eq(0).find('.detail').size() > 0)
		{
			$('.sheader_box').eq(0).addClass("fixhd");
			$('#bodyDiv').css("margin-top","40px");
			
			$('.sheader_box').on("click",function(e){
				$("html,body").animate({scrollTop:0},100);
			});
		}
	}

	if(parent.disableAll)
	{
		setDisable();
		disableAll = true;
	}
	
	if(typeof loadTabPage == "function") loadTabPage();
	
	parent.$('#tabDivTAB\\.'+currentPageId).show();

	//resize iframe
	resizeTabFrame();

	//setTimeout("closeModal()", 100);
	closeModal();
	
	var topPage = getTopPage();
	var pObj = self;
	var topH = 0;
	var tHeight = 90;
	
	if(pObj != top)
		do{
			var cPage = pObj.currentPageId;		
			var ifm = getIframe(cPage);
			
			if(ifm)
			{
				topH = topH + ifm.parent().offset().top - 10;
			}
				
			pObj = pObj.parent;
			
			
		}while(pObj != top); 

	if(topPage.DECORATOR_NAME == "popupPage") tHeight = 30;	
	
	var cH = topPage.$('#content, .pwin_content').scrollTop();
	
	if(parent.DECORATOR_NAME == "defaultPage"  || parent.DECORATOR_NAME == "popupPage")
	{
		//if(topPage.$('#content:animated, .pwin_content:animated').length == 0)
			topPage.$('#content, .pwin_content').animate( { scrollTop : topH + cH - tHeight }, 300 );
	}

	if(opener)
	{
		popupAutoResize();
	}
});

function popupAutoResize() {//팝업리사이징
    var w = $('.section_wrap').eq(0).outerWidth();
    var h = $('.section_wrap').eq(0).outerHeight() +90;

    window.resizeTo(w, h);
}

/* function getIframe(pIfmName, _contents)
{
	 if(typeof _contents == "undefined") _contents = getTopPage().document;
		 
	 $(_contents).find('iframe').each(function(index){
		var _nm = $(this).attr("name");
		if(typeof _nm == "undefined"){
			return true;
		}

		if(_nm.indexOf("tabFrameTAB.") >= 0)
		{
			var iframeName = $(this).attr("name").replace("tabFrameTAB.","");

			if(iframeName != pIfmName)
			{
				getIframe(pIfmName, $(this).contents());
			}
			else
			{
				targetIfmCnt = $(this);
				return false;
			}	
		}
		else
		{
			getIframe(pIfmName, $(this).contents());
		}
	 });
	 
	 return targetIfmCnt;
} */


    
//Tab Page가 최상위일때는 popupDivId를 Blank 처리 
if(self == top) popupDivId = "";
else popupDivId = "tabFrame_"+currentPageId;


</script>
<c:import charEncoding="UTF-8" url="/common/jsp/buttonInclude.jsp" ></c:import>
</body>
</html>
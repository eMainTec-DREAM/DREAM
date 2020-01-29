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
<div class="inner_section">
    <div class="section_wrap" id="<%=currentPageId%>" >
		<div class="sheader_box">
	        <div class="stitle_box"><span></span></div>
	        <div class="function_box list">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>
				<!-- <div class="b_line"></div>  -->
				<div class="fb_group2">
				</div>
				<!-- <div class="b_line"></div>  -->
				<div class="fb_group1">
				</div>
	        </div>
	    </div><!--sheader_box end-->
	    
		<div id='bodyDiv' style="margin:0px; display: none; border:0px; ">
			<decorator:body />
		</div>
	</div>
		<c:import charEncoding="UTF-8" url="/common/jsp/innerTabPageInclude.jsp"></c:import>

</div> <!-- end of Inner_section -->
<!-- page 하단 공통 페이지 -->
<c:import charEncoding="UTF-8" url="/common/jsp/bottomInclude.jsp"></c:import>
<script>
function resizeTabFrame(height)
{
	var topPage = getTopPage();
	topPage.listState = true; //리사이즈 중엔 재 선택 못하게 함
	
	if(typeof height  == "undefined")
	{
		height = 0;	
	}
	
	var heightChrome = $('.inner_section').outerHeight(true) - height;

	parent.$("#tabFrameTAB\\."+currentPageId).css("height",heightChrome);
	
	if(typeof parent.resizeTabFrame == "function")
	{
		parent.resizeTabFrame();
	}
}

/**
 * Tab Page Title Setting
 */
function setTabTitle()
{
	var tabTitle = parent.$('#TAB\\.'+currentPageId+"_title").text();
 	$('.stitle_box').prepend(tabTitle); 
}

function afterBtnLoadForSys()
{
	if(getTopPage().disableAll) setDisable();
	
	if(parent.$('div.tab_header.'+currentPageId).length > 0)
	{
		parent.$('div.tab_header.'+currentPageId).children().remove();

		$('.function_box.list').eq(0).children().hide();
		parent.$('div.tab_header.'+currentPageId).append($('.function_box.list').eq(0).children());
	 	$('.sheader_box').css("display","none"); 

	 	/* $('.section_wrap').each(function(){
	 		if($(this).find('.grid_area').size() == 0) $(this).hide();
	 	}); */
	}
	

}

$(window).load(function(){

	//if(typeof loadPage == "function") loadPage();	
	
	if(parent.disableAll)
	{
		setDisable();
		disableAll = true;
	}

	//새로 만든 문서일 경우 ...
	if(parent.$("[name='strutsAction']").val() == "0" || parent.DECORATOR_NAME == "popupPage")
	{
		parent.$('#tabDivTAB\\.'+currentPageId).show();
		parent.$('div.tab_header.'+currentPageId).children().show();
	}
	else
	{
		var divObj = parent.$('#'+currentPageId+'_tabList');
		divObj.find('.ac_menu').removeClass("current");
		divObj.find("div:not('.sfb_wrap')").hide();
		
		if(typeof afterLoad =="function") afterLoad();
	}

	resizeTabFrame();

	setTitle();
	setTabTitle();

	setTimeout(closeModal, 10);
});


//Tab Page가 최상위일때는 popupDivId를 Blank 처리 
if(self == top) popupDivId = "";
else popupDivId = "tabFrame_"+currentPageId;

</script>
<c:import charEncoding="UTF-8" url="/common/jsp/buttonInclude.jsp" ></c:import>
</body>
</html>
<%--===========================================================================
REPORT
author  jung7126
version $Id: mcDataSelectPopup.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.usrdata.action.McDataSelectPopupAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- REPORT -->
<title><bean:message key='MENU.mcDataSelectList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var myGrid,loadCnt = 0;
var _resize_timer;

$(function(){
	$(window).resize(function(){
	window.clearTimeout(_resize_timer);
	_resize_timer=window.setTimeout(function(){
	setGridHeight();
	}, 100);
	});
});

function setGridHeight() {
	h = $(window).height();
	h -= Math.round($('#gridbox').position().top);
	h -= 150;
	$('#gridbox').height(h);
	myGrid.setSizes();
	}

	
function loadPage() 
{
	initGrid();

	$('.pwintitle_box > h1').text(mcDataSelectPopupForm.elements['mcDataSelectCommonDTO.title'].value);
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	//myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	//myGrid.enableAutoHeight(true);
 	myGrid.attachEvent("OnContentLoaded", function(win) {
       var height = win.getView()._frame.contentDocument.height;
       var width = win.getView()._frame.contentDocument.width;

       win.setDimension(width, height);
    });

 	isHeaderLoaded[currentPageId+".gridbox"] = "Y";
 	
	goSearch();
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
    findGridList('Search');   
	loadCnt++;
}

function findGridList(sheetAction)
{
	var form = document.mcDataSelectPopupForm;	
	form.strutsAction.value = '<%=McDataSelectPopupAction.DATA_LIST_FIND %>';
	
	var url = contextPath + "/mcDataSelectPopup.do";

	doGridAction(sheetAction, myGrid, "gridbox", url, decodeURI(FormQueryString(mcDataSelectPopupForm)),'','Y');
	
}

function afterSearch()
{
	//if(loadCnt==1) goSearch();
	
	//setHeader(myGrid, "gridbox", "goSearch"); // grid, grid id, callBack
	if(typeof resizeTabFrame == "function") resizeTabFrame();
	
}

/**
 * Excel Export
 */
function goExcel()
{
    //excelAction(myGrid);
    excelServerAction("mcDataSelectPopup", mcDataSelectPopupForm );  
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/mcDataSelectPopup.do">
<html:hidden property="strutsAction"/>
<html:hidden property="mcDataSelectCommonDTO.usrrptId"/><!-- Key -->
<html:hidden property="mcDataSelectCommonDTO.title"/><!-- Key -->
<html:hidden property="mcDataSelectCommonDTO.script"/><!-- Key -->
    <!-- searchbox 박스 Line -->
<div class="section_wrap">
	<div class="sheader_box">
		<div class="sheader_wrap"><a></a></div>
		<div class="stitle_wrap">
			<div class="stitle_icon"><a></a></div>
			<div class="stitle_tx"><bean:message key="LABEL.List"/></div>
		</div>
		<!--<div class="stitle_box"><bean:message key="LABEL.Filter"/></div>  -->
		<div class="function_box list">
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
		
    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:250px; background-color:white;"></div>
           </div>
 	</div>
</div>
</html:form> 
</body>
</html>
<%--===========================================================================
REPORT
author  jung7126
version $Id: maUserRptPopup.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.usrrpt.action.MaUserRptPopupAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- REPORT -->
<title><bean:message key='MENU.maUserRptList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	//initGrid();

	//$('.pwintitle_box > h1').text(decodeURI(maUserRptPopupForm.elements['maUserRptCommonDTO.title'].value));
	
	setTitle("maUserRptCommonDTO.usrrptlistId","maUserRptCommonDTO.title");
	$('.stitle_tx').prepend('<bean:message key="LABEL.id"/>');
}

var myGrid,loadCnt = 0;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	//myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

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
	var form = document.maUserRptPopupForm;	
	form.strutsAction.value = '<%=MaUserRptPopupAction.USER_RPT_LIST_FIND %>';
	
	var url = contextPath + "/maUserRptPopup.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, decodeURI(FormQueryString(maUserRptPopupForm)));
	
}

function afterSearch()
{
	//if(loadCnt==1) goSearch();
	
	//setHeader(myGrid, "gridbox", "goSearch"); // grid, grid id, callBack
	if(typeof resizeTabFrame == "function") resizeTabFrame();
	
}

function goTabPage(pageId)
{
	var form = document.maUserRptPopupForm;

	goCommonTabPage(form, '' , pageId);
    
}

/**
 * Excel Export
 */
function goExcel()
{
    excelAction(myGrid);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maUserRptPopup.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maUserRptCommonDTO.usrrptlistId"/><!-- Key -->
    <!-- searchbox 박스 Line -->
     <div class="article_box" id="detailBox">
             <div class="form_box">
                <div class="field_long">
             	 	<label><bean:message key="LABEL.title"/></label>
             	 	<div class="input_box">
             	 		<html:text property="maUserRptCommonDTO.title" tabindex="10" />
             	 	</div>
             	 </div>
             </div>
     </div>
    
    
    
    
<%-- <div class="section_wrap">
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
</div> --%>
</html:form> 
</body>
</html>
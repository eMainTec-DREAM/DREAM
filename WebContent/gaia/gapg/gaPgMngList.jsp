<%--===========================================================================
화면 - 목록
author  kim21017
version $Id: gaPgMngList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="gaia.gapg.action.GaPgMngListAction" %>
<%@ page import="gaia.gapg.action.GaPgMngDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 화면 -->
<title><bean:message key='GAIA.MENU.gaPgMngList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="gaiaPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;
function loadPage() 
{
    initGrid();
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/gaPgMngList.do";

    gaPgMngListForm.elements['strutsAction'].value = '<%=GaPgMngListAction.PG_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(gaPgMngListForm), "PAGEID");

}


/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	gaPgMngListForm.elements['gaPgMngCommonDTO.pageId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search');   
}

/**
 * Tab 이동시 호출
 */
function goTabPage(pageId)
{
    tabValidationCheck(myGrid, pageId, "goTabPageAction");
}

function goTabPageAction(pageId, selectedId)
{
    gaPgMngListForm.elements['gaPgMngCommonDTO.pageId'].value = getValueById(myGrid, selectedId,'PAGEID');
    goCommonTabPage(gaPgMngListForm, <%= GaPgMngDetailAction.PG_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pageId)
{
	gaPgMngListForm.elements['gaPgMngCommonDTO.pageId'].value = _pageId;
	findGridList('ReloadRow');
	gaPgMngListForm.elements['gaPgMngCommonDTO.pageId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('gaPgMngDetail');	
}

 /**
  * 생성
  */
function goCreate()
{
	createValidationCheck(myGrid, "gaPgMngDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	gaPgMngListForm.elements['gaPgMngCommonDTO.pageId'].value = "";
	goCommonTabPage(gaPgMngListForm, '', pageId);
}

/**
 * Excel Export
 */
function goExcel()
{
    excelAction(myGrid);
}

 /**
   * 삭제
   */
function goDelete()
{
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'PAGEID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	gaPgMngListForm.strutsAction.value = '<%=GaPgMngListAction.PG_LIST_DELETE%>';
	var url = contextPath + "/gaPgMngList.do";
	$.post(url,FormQueryString(gaPgMngListForm)+delArray , function(_data){
    	afterDelete();
    });
}
 
function afterDelete()
{
	goClose('gaPgMngDetail');
	// 	goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}
  
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/gaPgMngList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="gaPgMngCommonDTO.pageId"/><!-- Key -->
    <!-- searchbox 박스 Line -->
    <div class="section_wrap">
    	<div class="sheader_box">
	       	<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.Filter"/></div>
			</div>
			<div class="function_box filter">
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
	       <div class="article_box">
	           <div class="form_box">
	               <div class="field">
		               <label><bean:message key="GAIA.LABEL.fileName"/></label>
	               	   <div class="input_box">
	               	   		<html:text property="gaPgMngCommonDTO.filterFileName" tabindex="10"/>
	               	   </div>
               	   </div>
	               <div class="field">
		               <label><bean:message key="GAIA.LABEL.pageDesc"/></label>
		               <div class="input_box">
		               		<html:text property="gaPgMngCommonDTO.filterPageDesc" tabindex="20"/>
		               </div>
	               </div>
	           	</div>
   			</div><!--article_box end-->
    </div> <!--  end section_wrap -->
		
	 <div class="section_wrap">
	    <div class="sheader_box">
	        <div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.List"/></div>
			</div>
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
            	<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
            </div>
	 	</div>
	 </div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>
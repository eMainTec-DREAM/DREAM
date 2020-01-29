<%--===========================================================================
화면 - 목록
author  kim21017
version $Id: maPgMngList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.program.page.action.MaPgMngListAction" %>
<%@ page import="dream.consult.program.page.action.MaPgMngDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 화면 -->
<title><bean:message key='MENU.PAGE'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="consultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid,menuAc;
function loadPage() 
{
	
	//메뉴 (pop up)
    menuAc = new autoC({"maPgMngCommonDTO.filterMenuDesc":"description"});
    menuAc.setTable("TACMENU");
    menuAc.setAcResultMap({
        "maPgMngCommonDTO.filterMenuId":"MENU_ID"
    });
    menuAc.init();
	
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
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		maPgMngListForm.elements['maPgMngCommonDTO.pageId'].value = "";
    	return sortColumn("maPgMngList", this, maPgMngListForm, "PAGE_TYPE", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
	
	acSysDesc("maPgMngCommonDTO.filterPageType","maPgMngCommonDTO.filterPageTypeId","PAGE_TYPE");
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maPgMngList.do";

    maPgMngListForm.elements['strutsAction'].value = '<%=MaPgMngListAction.PG_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPgMngListForm), "PAGEID","Y");

}


/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	maPgMngListForm.elements['maPgMngCommonDTO.pageId'].value = "";	// 검색시 Tab 이동Key Clear
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
    maPgMngListForm.elements['maPgMngCommonDTO.pageId'].value = getValueById(myGrid, selectedId,'PAGEID');
    goCommonTabPage(maPgMngListForm, <%= MaPgMngDetailAction.PG_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pageId)
{
	maPgMngListForm.elements['maPgMngCommonDTO.pageId'].value = _pageId;
	findGridList('ReloadRow');
	maPgMngListForm.elements['maPgMngCommonDTO.pageId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maPgMngDetail');	
}

 /**
  * 생성
  */
function goCreate()
{
	createValidationCheck(myGrid, "maPgMngDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maPgMngListForm.elements['maPgMngCommonDTO.pageId'].value = "";
	goCommonTabPage(maPgMngListForm, '', pageId);
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
	
	maPgMngListForm.strutsAction.value = '<%=MaPgMngListAction.PG_LIST_DELETE%>';
	var url = contextPath + "/maPgMngList.do";
	$.post(url,FormQueryString(maPgMngListForm)+delArray , function(_data){
    	afterDelete();
    });
}
 
function afterDelete()
{
	goClose('maPgMngDetail');
	// 	goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}
  
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPgMngList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPgMngCommonDTO.pageId"/><!-- Key -->
<html:hidden property="maPgMngCommonDTO.filterPageTypeId"/><!-- Key -->
<html:hidden property="maPgMngCommonDTO.filterMenuId"/>
<html:hidden property="maPgMngCommonDTO.paramPageType"/>
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
			</div>
		</div><!--sheader_box end-->
	       <div class="article_box">
	           <div class="form_box">
	               <!-- 화면 ID -->
	               <div class="field">
		               <label><bean:message key="LABEL.fileName"/></label>
	               	   <div class="input_box">
	               	   		<html:text property="maPgMngCommonDTO.filterFileName" tabindex="10"/>
	               	   </div>
               	   </div>
	               <!-- 화면설명 -->
	               <div class="field">
		               <label><bean:message key="LABEL.pageDesc"/></label>
		               <div class="input_box">
		               		<html:text property="maPgMngCommonDTO.filterPageDesc" tabindex="20"/>
		               </div>
	               </div>
               	   <!-- 페이지유형 -->
               	   <div class="field">
						<label><bean:message key="LABEL.pageType"/></label>
						<div class="input_box">
							<html:text property="maPgMngCommonDTO.filterPageType" tabindex="30"
								onkeydown="validationKeyDown('maPgMngCommonDTO.filterPageType', 'maPgMngCommonDTO.filterPageTypeId');"/>
							<p class="open_spop"><a href="javascript:lovSysDir('maPgMngCommonDTO.filterPageTypeId', 'maPgMngCommonDTO.filterPageType','PAGE_TYPE');"><span>조회</span></a></p>
						</div>
				    </div>
				
				   <!-- 페이지분류 -->
               	   <div class="field">
		               <label><bean:message key="LABEL.pageCateg"/></label>
	               	   <div class="input_box">
	               	   		<html:text property="maPgMngCommonDTO.filterPageCateg" tabindex="40"/>
	               	   </div>
               	   </div>
               	   
               	   <!-- 매뉴 -->
               	   <div class="field">
		               <label><bean:message key="LABEL.menuName"/></label>
	               	   <div class="input_box">
	               	   		<html:text property="maPgMngCommonDTO.filterMenuDesc" tabindex="50"/>
	               	   		<p class="open_spop"><a></a></p>
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
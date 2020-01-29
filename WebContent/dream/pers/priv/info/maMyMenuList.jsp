<%--===========================================================================
목록
author  jung7126
version $Id: maMyMenuList.jsp,v 1.1 2015/12/03 01:45:27 jung7126 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.pers.priv.info.action.MaMyMenuListAction" %>
<%@ page import="dream.pers.priv.info.action.MaMyMenuDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 사용자메뉴 -->
<title><bean:message key='LABEL.maMyMenuList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="innerTabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	initGrid();
	
/* 	parent.$("#tabFrameTAB\\."+currentPageId).contents().find('.inner_section').animate({
		//margin : "0"
	},  function(){
		resizeTabFrame();
	}); */
	
	parent.$("#tabFrameTAB\\."+currentPageId).contents().find('.inner_section').css("margin","7px 0px 30px 0px");
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("maMyMenuDetail");
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	//$('#gridbox').css("height","250px");
	//myGrid.setSizes();
	
    findGridList('Search');   
}

function findGridList(sheetAction)
{
	//페이지id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (maMyMenuListForm.elements['maMyInfoCommonDTO.userId'].value == '') return;
	
	var form = document.maMyMenuListForm;	
	form.strutsAction.value = '<%=MaMyMenuListAction.MY_MENU_LIST_FIND %>';
	
	var url = contextPath + "/maMyMenuList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maMyMenuListForm), "USRMENUID");

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
	maMyMenuListForm.elements['maMyInfoCommonDTO.usrMenuId'].value = getValueById(myGrid, selectedId,'usrMenuId');
	goCommonTabPage(maMyMenuListForm, '<%=MaMyMenuDetailAction.MY_MENU_DETAIL_INIT%>' , pageId);
	
}

/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maMyMenuDetail');
}

 /**
  * 생성
  */
function goCreate()
{
 	createValidationCheck(myGrid, "maMyMenuDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
 	maMyMenuListForm.elements['maMyInfoCommonDTO.usrMenuId'].value = "";
 	goCommonTabPage(maMyMenuListForm, '', pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_usrMenuId)
{
	maMyMenuListForm.elements['maMyInfoCommonDTO.usrMenuId'].value = _usrMenuId;
	findGridList('ReloadRow');
	maMyMenuListForm.elements['maMyInfoCommonDTO.usrMenuId'].value = "";
}
  /**
   * 삭제
   */
function goDelete()
{
	var delArray = getDeletRows(myGrid, 'isDelCheck', 'usrMenuId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maMyMenuListForm.strutsAction.value = '<%=MaMyMenuListAction.MY_MENU_LIST_DELETE%>';
	var url = contextPath + "/maMyMenuList.do";
	
	$.post(url,FormQueryString(maMyMenuListForm)+delArray , function(_data){
    	afterDelete();
    });
}
  
function afterDelete()
{
	goClose('maMyMenuDetail');
	//	goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
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
<html:form action="/maMyMenuList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maMyInfoCommonDTO.userId"/><!-- Key -->
<html:hidden property="maMyInfoCommonDTO.compNo"/><!-- Key -->
<html:hidden property="maMyInfoCommonDTO.usrMenuId"/><!-- Key -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
<%--===========================================================================
목록
author  jung7126
version $Id: maGrdMngColList.jsp,v 1.1 2015/12/03 01:45:27 jung7126 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.program.page.action.MaGrdMngColListAction" %>
<%@ page import="dream.consult.program.page.action.MaGrdMngColDetailAction" %>
<%@ page import="dream.consult.program.page.action.MaGrdMngDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 칼럼 목록 -->
<title><bean:message key='LABEL.maGrdMngColList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="adminPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	initGrid();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("maGrdMngColDetail");
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
    findGridList('Search');   
}

function findGridList(sheetAction)
{
	//페이지id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (maGrdMngColListForm.elements['maGrdMngCommonDTO.pgGridId'].value == '') return;
	
	var form = document.maGrdMngColListForm;	
	form.strutsAction.value = '<%=MaGrdMngColListAction.GRD_COL_LIST_FIND %>';
	
	var url = contextPath + "/maGrdMngColList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maGrdMngColListForm), "PGGRIDCOLID");

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
	maGrdMngColListForm.elements['maGrdMngCommonDTO.pgGridColId'].value = getValueById(myGrid, selectedId,'pgGridColId');
	
	goCommonTabPage(maGrdMngColListForm, <%= MaGrdMngColDetailAction.GRD_COL_DETAIL_INIT %>, "maGrdMngColDetail");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pgGridColId)
{
	maGrdMngColListForm.elements['maGrdMngCommonDTO.pgGridColId'].value = _pgGridColId;
	findGridList('ReloadRow');
	maGrdMngColListForm.elements['maGrdMngCommonDTO.pgGridColId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maGrdMngColDetail');
}

 /**
  * 생성
  */
function goCreate()
{
 	createValidationCheck(myGrid, "maGrdMngColDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
 	maGrdMngColListForm.elements['maGrdMngCommonDTO.pgGridColId'].value = "";
 	goCommonTabPage(maGrdMngColListForm, '', pageId);
}
 
  /**
   * 삭제
   */
function goDelete()
{
	var delArray = getDeletRows(myGrid, 'isDelCheck', 'pgGridColId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maGrdMngColListForm.strutsAction.value = '<%=MaGrdMngColListAction.GRD_COL_LIST_DELETE%>';
	var url = contextPath + "/maGrdMngColList.do";
	
	$.post(url,FormQueryString(maGrdMngColListForm)+delArray , function(_data){
    	afterDelete();
    });
}
  
function afterDelete()
{
	goClose('maGrdMngColDetail');
	//	goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maGrdMngColList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maGrdMngCommonDTO.pgGridId"/><!-- Key -->
<html:hidden property="maGrdMngCommonDTO.pgGridColId"/><!-- Key -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
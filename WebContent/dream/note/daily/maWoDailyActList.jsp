<%--===========================================================================
일일작업일지확인 - Main activities
author  kim21017
version $Id: maWoDailyActList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.note.daily.action.MaWoDailyActListAction" %> 
<%@ page import="dream.note.daily.action.MaWoDailyActDetailAction" %> 
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html> 
<head>
<!-- Main Activities -->
<title><bean:message key='TAB.maWoDailyActList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
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
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
	myGrid.init();
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("maWoDailyActDetail");
	});
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
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (maWoDailyActListForm.elements['maWoDailyCommonDTO.woDayListId'].value == '') return;
	
	var form = document.maWoDailyActListForm;	
	form.strutsAction.value = '<%=MaWoDailyActListAction.LIST_FIND %>';
	
	var url = contextPath + "/maWoDailyActList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maWoDailyActListForm), "WODAYACTID");

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
	var form = document.maWoDailyActListForm;
	 
	form.elements['maWoDailyActListDTO.woDayActId'].value = getValueById(myGrid, selectedId,'WODAYACTID');
	goCommonTabPage(form, <%= MaWoDailyActDetailAction.DETAIL_INIT %>, pageId);
}
function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
 	if(selectedId == null) return;

 	maWoDailyActListForm.elements['maWoDailyActListDTO.woDayActId'].value = getValueById(myGrid, selectedId,'WODAYACTID');
 	maWoDailyActListForm.elements['strutsAction'].value = '<%=MaWoDailyActDetailAction.DETAIL_INIT%>';
 	openQuickTabPage(FormQueryString(maWoDailyActListForm), 'maWoDailyActDetail'); 
}
/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('maWoDailyActDetail');
}
 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
  	excelAction(myGrid);
  } 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "maWoDailyActDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	 maWoDailyActListForm.elements['maWoDailyActListDTO.woDayActId'].value = "";
	goCommonTabPage(maWoDailyActListForm, '', pageId);
 }
 
 /** 
  * 수정된 그리드 1건을 다시 조회한다.
  */
 function findGridRow(_woDayActId)
 {
	maWoDailyActListForm.elements['maWoDailyActListDTO.woDayActId'].value = _woDayActId;
 	findGridList('ReloadRow');
 	maWoDailyActListForm.elements['maWoDailyActListDTO.woDayActId'].value = "";
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'WODAYACTID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maWoDailyActListForm.strutsAction.value = '<%=MaWoDailyActListAction.LIST_DELETE%>';
	var url = contextPath + "/maWoDailyActList.do";
	
	$.post(url,FormQueryString(maWoDailyActListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('maWoDailyActDetail',this);
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maWoDailyActList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maWoDailyCommonDTO.woDayListId"/><!-- Key -->
<html:hidden property="maWoDailyActListDTO.woDayActId"/><!-- Key -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
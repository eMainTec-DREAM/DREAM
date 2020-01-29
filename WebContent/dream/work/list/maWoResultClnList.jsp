<%--===========================================================================
작업결과 작업설비
author  kim21017
version $Id: maWoResultClnList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.list.action.MaWoResultClnListAction" %>
<%@ page import="dream.work.list.action.MaWoResultClnDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 청소설비 -->
<title><bean:message key='TAB.maWoResultClnList'/></title>
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
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("maWoResultClnDetail");
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	maWoResultClnListForm.elements['maWoResultClnListDTO.woEquipId'].value = "";
        return sortColumn("maWoResultClnList", this, maWoResultClnListForm, "WOEQUIPID", ind, direction);
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
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (maWoResultClnListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value == '') return;
	
	var form = document.maWoResultClnListForm;	
	form.strutsAction.value = '<%=MaWoResultClnListAction.WO_RESULT_CLN_LIST_FIND %>';
	
	var url = contextPath + "/maWoResultClnList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maWoResultClnListForm), "WOEQUIPID", "Y");

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
	var form = document.maWoResultClnListForm;
	 
	form.elements['maWoResultClnListDTO.woEquipId'].value = getValueById(myGrid, selectedId,'WOEQUIPID');
	goCommonTabPage(form, <%= MaWoResultClnDetailAction.WO_RESULT_CLN_DETAIL_INIT %>, pageId);
}


/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('maWoResultClnDetail');
}
 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	maWoResultClnListForm.elements['maWoResultClnListDTO.woEquipId'].value = "";
  	excelServerAction("maWoResultClnList",maWoResultClnListForm);
  } 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "maWoResultClnDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	maWoResultClnListForm.elements['maWoResultClnListDTO.woEquipId'].value = "";
	goCommonTabPage(maWoResultClnListForm, '', pageId);
 }
 
 /** 
  * 수정된 그리드 1건을 다시 조회한다.
  */
 function findGridRow(_woEquipId)
 {
	 maWoResultClnListForm.elements['maWoResultClnListDTO.woEquipId'].value = _woEquipId;
 	findGridList('ReloadRow');
 	maWoResultClnListForm.elements['maWoResultClnListDTO.woEquipId'].value = "";
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'WOEQUIPID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maWoResultClnListForm.strutsAction.value = '<%=MaWoResultClnListAction.WO_RESULT_CLN_LIST_DELETE%>';
	var url = contextPath + "/maWoResultClnList.do";
	
	$.post(url,FormQueryString(maWoResultClnListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('maWoResultClnDetail',this);
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maWoResultClnList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maWoResultMstrCommonDTO.wkOrId"/><!-- Key -->
<html:hidden property="maWoResultClnListDTO.woEquipId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
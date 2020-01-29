<%--===========================================================================
기능정의
author  kim21017
version $Id: rcmSysFDefList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.rcm.system.action.RcmSysFDefListAction" %>
<%@ page import="dream.rcm.system.action.RcmSysFDefDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 기능정의 -->
<title><bean:message key='TAB.rcmSysFDefList'/></title>
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
		goTabPage("rcmSysFDefDetail");
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		rcmSysFDefListForm.elements['rcmSysFDefListDTO.rcmFuncId'].value = "";
    	return sortColumn("rcmSysFDefList", this, rcmSysFDefListForm, "RCMFUNCID", ind, direction);
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
	if (rcmSysFDefListForm.elements['rcmSysCommonDTO.rcmListId'].value == '') return;
	
	var form = document.rcmSysFDefListForm;	
	form.strutsAction.value = '<%=RcmSysFDefListAction.RCM_SYS_FDEF_LIST_FIND %>';
	
	var url = contextPath + "/rcmSysFDefList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(rcmSysFDefListForm), "RCMFUNCID", "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_rcmFuncId)
{
	rcmSysFDefListForm.elements['rcmSysFDefListDTO.rcmFuncId'].value = _rcmFuncId;
	findGridList('ReloadRow');
	rcmSysFDefListForm.elements['rcmSysFDefListDTO.rcmFuncId'].value = "";
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
	var form = document.rcmSysFDefListForm;
	 
	form.elements['rcmSysFDefListDTO.rcmFuncId'].value = getValueById(myGrid, selectedId,'RCMFUNCID');
	goCommonTabPage(form, <%= RcmSysFDefDetailAction.RCM_SYS_FDEF_DETAIL_INIT %>, pageId);
}


/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('rcmSysFDefDetail');
}

 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "rcmSysFDefDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	rcmSysFDefListForm.elements['rcmSysFDefListDTO.rcmFuncId'].value = "";
	goCommonTabPage(rcmSysFDefListForm, '', pageId);
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'isDelCheck', 'RCMFUNCID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	rcmSysFDefListForm.strutsAction.value = '<%=RcmSysFDefListAction.RCM_SYS_FDEF_LIST_DELETE%>';
	var url = contextPath + "/rcmSysFDefList.do";
	
	$.post(url,FormQueryString(rcmSysFDefListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('rcmSysFDefDetail',this);
   	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
   
function goExcel()
{
	excelAction(myGrid);
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/rcmSysFDefList">
<html:hidden property="strutsAction"/>
<html:hidden property="rcmSysCommonDTO.rcmListId"/><!-- Key -->
<html:hidden property="rcmSysFDefListDTO.rcmFuncId"/><!-- Key -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
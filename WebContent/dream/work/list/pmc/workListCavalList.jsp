<%--===========================================================================
검교정상세 측정값목록
author  kim21017
version $Id: workListCavalList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.list.action.WorkListCavalListAction" %>
<%@ page import="dream.work.list.action.WorkListCavalDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 측정값 -->
<title><bean:message key='TAB.workListCavalList'/></title>
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
		goTabPage("workListCavalDetail");
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	workListCavalListForm.elements['workListCavalListDTO.woCalibValueId'].value = "";
        return sortColumn("workListCavalList", this, workListCavalListForm, "woCalibValueId", ind, direction);
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
	if (workListCavalListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value == '') return;
	
	var form = document.workListCavalListForm;	
	form.strutsAction.value = '<%=WorkListCavalListAction.WORK_LIST_CAVAL_LIST_FIND %>';
	
	var url = contextPath + "/workListCavalList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workListCavalListForm), "WOCALIBVALUEID", "Y");

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
	var form = document.workListCavalListForm;
	 
	form.elements['workListCavalListDTO.woCalibValueId'].value = getValueById(myGrid, selectedId,'WOCALIBVALUEID');
	goCommonTabPage(form, <%= WorkListCavalDetailAction.WORK_LIST_CAVAL_DETAIL_INIT %>, pageId);
}


/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('workListCavalDetail');
}
 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	workListCavalListForm.elements['workListCavalListDTO.woCalibValueId'].value = "";
  	excelServerAction("workListCavalList",workListCavalListForm);
  } 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "workListCavalDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	workListCavalListForm.elements['workListCavalListDTO.woCalibValueId'].value = "";
	goCommonTabPage(workListCavalListForm, '', pageId);
 }
 
 /** 
  * 수정된 그리드 1건을 다시 조회한다.
  */
 function findGridRow(_woCalibValueId)
 {
	workListCavalListForm.elements['workListCavalListDTO.woCalibValueId'].value = _woCalibValueId;
 	findGridList('ReloadRow');
 	workListCavalListForm.elements['workListCavalListDTO.woCalibValueId'].value = "";
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'WOCALIBVALUEID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	workListCavalListForm.strutsAction.value = '<%=WorkListCavalListAction.WORK_LIST_CAVAL_LIST_DELETE%>';
	var url = contextPath + "/workListCavalList.do";
	
	$.post(url,FormQueryString(workListCavalListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('workListCavalDetail',this);
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workListCavalList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maWoResultMstrCommonDTO.wkOrId"/><!-- Key -->
<html:hidden property="workListCavalListDTO.woCalibValueId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
<%--===========================================================================
가동시간설정 - 요일별 설정 목록
author  kim21017
version $Id: maLineTimeDowList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.comp.time.action.MaLineTimeDowListAction" %>
<%@ page import="dream.consult.comp.time.action.MaLineTimeDowDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%> 
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 요일별설정 -->
<title><bean:message key='TAB.maLineTimeDowList'/></title>
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
		goTabPage("maLineTimeDowDetail");
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	maLineTimeDowListForm.elements['maLineTimeDowListDTO.eqLocDowRunId'].value = "";
    	maLineTimeDowListForm.elements['maLineTimeDowListDTO.compNo'].value = "";
        return sortColumn("maLineTimeDowList", this, maLineTimeDowListForm, "eqLocDowRunId", ind, direction);
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
	if (maLineTimeDowListForm.elements['maLineTimeCommonDTO.lnWrkListId'].value == '' 
			&& maLineTimeDowListForm.elements['maLineTimeCommonDTO.compNo'].value == '') return;
	var form = document.maLineTimeDowListForm;	
	form.strutsAction.value = '<%=MaLineTimeDowListAction.LINE_DOW_LIST_FIND %>';
	
	var url = contextPath + "/maLineTimeDowList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maLineTimeDowListForm), "EQLOCDOWRUNID", "Y");
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
	var form = document.maLineTimeDowListForm;
	 
	form.elements['maLineTimeDowListDTO.eqLocDowRunId'].value = getValueById(myGrid, selectedId,'EQLOCDOWRUNID');
	form.elements['maLineTimeDowListDTO.compNo'].value = getValueById(myGrid, selectedId,'COMPNO');
	goCommonTabPage(form, <%= MaLineTimeDowDetailAction.LINE_DOW_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_compNo, _eqLocDowRunId)
{
	maLineTimeDowListForm.elements['maLineTimeDowListDTO.eqLocDowRunId'].value = _eqLocDowRunId;
	maLineTimeDowListForm.elements['maLineTimeDowListDTO.compNo'].value = _compNo;
	findGridList('ReloadRow');
	maLineTimeDowListForm.elements['maLineTimeDowListDTO.eqLocDowRunId'].value = "";
	maLineTimeDowListForm.elements['maLineTimeDowListDTO.compNo'].value = "";
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('maLineTimeDowDetail');
}

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	  maLineTimeDowListForm.elements['maLineTimeDowListDTO.eqLocDowRunId'].value = "";
	maLineTimeDowListForm.elements['maLineTimeDowListDTO.compNo'].value = "";
  	excelServerAction("maLineTimeDowList",maLineTimeDowListForm);
  }
 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "maLineTimeDowDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	maLineTimeDowListForm.elements['maLineTimeDowListDTO.eqLocDowRunId'].value = "";
	maLineTimeDowListForm.elements['maLineTimeDowListDTO.compNo'].value = "";
	goCommonTabPage(maLineTimeDowListForm, '', pageId);
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'EQLOCDOWRUNID', 'COMPNO'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maLineTimeDowListForm.strutsAction.value = '<%=MaLineTimeDowListAction.LINE_DOW_LIST_DELETE%>';
	var url = contextPath + "/maLineTimeDowList.do";
	
	$.post(url,FormQueryString(maLineTimeDowListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('maLineTimeDowDetail',this);
   	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maLineTimeDowList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maLineTimeCommonDTO.lnWrkListId"/><!-- Key -->
<html:hidden property="maLineTimeCommonDTO.compNo"/><!-- Key -->
<html:hidden property="maLineTimeDowListDTO.eqLocDowRunId"/>
<html:hidden property="maLineTimeDowListDTO.compNo"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
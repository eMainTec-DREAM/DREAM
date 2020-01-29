<%--===========================================================================
가동시간설정 - 요일별 설정 목록
author  kim21017
version $Id: mgrCalLineTimeDowSetList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.cal.action.MgrCalLineTimeDowSetAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%> 
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 요일별설정 -->
<title><bean:message key='TAB.mgrCalLineTimeDowSetList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	mgrCalLineTimeDowSetForm.elements['mgrCalLineTimeDowSetDTO.lnWrkListId'].value
	= mgrCalLineTimeDowSetForm.elements['mgrCalLineTimeSetDTO.lnWrkListId'].value;
	
	initGrid();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("mgrCalLineTimeDowSetDetail");
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	mgrCalLineTimeDowSetForm.elements['mgrCalLineTimeSetDTO.lnWrkListId'].value = "";
        return sortColumn("mgrCalLineTimeDowSetList", this, mgrCalLineTimeDowSetForm, "lnWrkListId", ind, direction);
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
	if (mgrCalLineTimeDowSetForm.elements['mgrCalLineTimeSetDTO.lnWrkListId'].value == '') return;
	var form = document.mgrCalLineTimeDowSetForm;	
	form.strutsAction.value = '<%=MgrCalLineTimeDowSetAction.LINE_DOW_LIST_FIND %>';
	
	var url = contextPath + "/mgrCalLineTimeDowSetList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mgrCalLineTimeDowSetForm), "EQLOCDOWRUNID", "Y");
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
	var form = document.mgrCalLineTimeDowSetForm;
	 
	form.elements['mgrCalLineTimeDowSetDTO.eqLocDowRunId'].value = getValueById(myGrid, selectedId,'EQLOCDOWRUNID');
	goCommonTabPage(form, <%= MgrCalLineTimeDowSetAction.LINE_DOW_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_eqLocDowRunId)
{
	mgrCalLineTimeDowSetForm.elements['mgrCalLineTimeDowSetDTO.eqLocDowRunId'].value = _eqLocDowRunId;
	findGridList('ReloadRow');
	mgrCalLineTimeDowSetForm.elements['mgrCalLineTimeDowSetDTO.eqLocDowRunId'].value = "";
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('mgrCalLineTimeDowSetDetail');
}

function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
    
    if(selectedId == null) return;
    
    mgrCalLineTimeDowSetForm.elements['mgrCalLineTimeDowSetDTO.eqLocDowRunId'].value = getValueById(myGrid, selectedId,'EQLOCDOWRUNID');
    mgrCalLineTimeDowSetForm.elements['strutsAction'].value = '<%=MgrCalLineTimeDowSetAction.LINE_DOW_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(mgrCalLineTimeDowSetForm), 'mgrCalLineTimeDowSetDetail');
}

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	mgrCalLineTimeDowSetForm.elements['mgrCalLineTimeDowSetDTO.eqLocDowRunId'].value = "";
  	excelServerAction("mgrCalLineTimeDowSetList",mgrCalLineTimeDowSetForm);
  }
 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "mgrCalLineTimeDowSetDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	mgrCalLineTimeDowSetForm.elements['mgrCalLineTimeDowSetDTO.eqLocDowRunId'].value = "";
	goCommonTabPage(mgrCalLineTimeDowSetForm, '', pageId);
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

	mgrCalLineTimeDowSetForm.strutsAction.value = '<%=MgrCalLineTimeDowSetAction.LINE_DOW_LIST_DELETE%>';
	var url = contextPath + "/mgrCalLineTimeDowSetList.do";
	
	$.post(url,FormQueryString(mgrCalLineTimeDowSetForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('mgrCalLineTimeDowSetDetail',this);
   	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/mgrCalLineTimeDowSetList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="mgrCalLineTimeSetDTO.lnWrkListId"/><!-- Key -->
<html:hidden property="mgrCalLineTimeDowSetDTO.lnWrkListId"/>
<%-- <html:hidden property="mgrCalLineTimeSetDTO.compNo"/><!-- Key --> --%>
<html:hidden property="mgrCalLineTimeDowSetDTO.eqLocDowRunId"/>
<html:hidden property="mgrCalLineTimeDowSetDTO.compNo"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
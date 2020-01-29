<%--===========================================================================
가동시간설정 - 요일별 설정 목록
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.usage.cal.action.MgrUsageCalSetDowSetAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%> 
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 요일별설정 -->
<title><bean:message key='TAB.mgrUsageCalSetDowSetList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	mgrUsageCalSetDowSetForm.elements['mgrUsageCalSetDowSetDTO.lnWrkListId'].value
	= mgrUsageCalSetDowSetForm.elements['mgrUsageCalSetDTO.lnWrkListId'].value;
	
	initGrid();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	mgrUsageCalSetDowSetForm.elements['mgrUsageCalSetDTO.lnWrkListId'].value = "";
        return sortColumn("mgrUsageCalSetDowSetList", this, mgrUsageCalSetDowSetForm, "lnWrkListId", ind, direction);
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
	if (mgrUsageCalSetDowSetForm.elements['mgrUsageCalSetDTO.lnWrkListId'].value == '') return;
	var form = document.mgrUsageCalSetDowSetForm;	
	form.strutsAction.value = '<%=MgrUsageCalSetDowSetAction.LINE_DOW_LIST_FIND %>';
	
	var url = contextPath + "/mgrUsageCalSetDowSetList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mgrUsageCalSetDowSetForm), "EQLOCDOWRUNID", "Y");
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
	var form = document.mgrUsageCalSetDowSetForm;
	 
	form.elements['mgrUsageCalSetDowSetDTO.eqLocDowRunId'].value = getValueById(myGrid, selectedId,'EQLOCDOWRUNID');
	goCommonTabPage(form, <%= MgrUsageCalSetDowSetAction.LINE_DOW_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_eqLocDowRunId)
{
	mgrUsageCalSetDowSetForm.elements['mgrUsageCalSetDowSetDTO.eqLocDowRunId'].value = _eqLocDowRunId;
	findGridList('ReloadRow');
	mgrUsageCalSetDowSetForm.elements['mgrUsageCalSetDowSetDTO.eqLocDowRunId'].value = "";
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('mgrUsageCalSetDowSetDetail');
}

function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
    
    if(selectedId == null) return;

    mgrUsageCalSetDowSetForm.elements['mgrUsageCalSetDowSetDTO.eqLocDowRunId'].value = getValueById(myGrid, selectedId,'EQLOCDOWRUNID');
    mgrUsageCalSetDowSetForm.elements['strutsAction'].value = '<%= MgrUsageCalSetDowSetAction.LINE_DOW_DETAIL_INIT %>';
    	
    openQuickTabPage(FormQueryString(mgrUsageCalSetDowSetForm), 'mgrUsageCalSetDowSetDetail'); 

	
}

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	mgrUsageCalSetDowSetForm.elements['mgrUsageCalSetDowSetDTO.eqLocDowRunId'].value = "";
  	excelServerAction("mgrUsageCalSetDowSetList",mgrUsageCalSetDowSetForm);
  }
 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "mgrUsageCalSetDowSetDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	mgrUsageCalSetDowSetForm.elements['mgrUsageCalSetDowSetDTO.eqLocDowRunId'].value = "";
	goCommonTabPage(mgrUsageCalSetDowSetForm, '', pageId);
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

	mgrUsageCalSetDowSetForm.strutsAction.value = '<%=MgrUsageCalSetDowSetAction.LINE_DOW_LIST_DELETE%>';
	var url = contextPath + "/mgrUsageCalSetDowSetList.do";
	
	$.post(url,FormQueryString(mgrUsageCalSetDowSetForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('mgrUsageCalSetDowSetDetail',this);
   	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/mgrUsageCalSetDowSetList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="mgrUsageCalSetDTO.lnWrkListId"/><!-- Key -->
<html:hidden property="mgrUsageCalSetDowSetDTO.lnWrkListId"/>
<%-- <html:hidden property="mgrUsageCalSetDTO.compNo"/><!-- Key --> --%>
<html:hidden property="mgrUsageCalSetDowSetDTO.eqLocDowRunId"/>
<html:hidden property="mgrUsageCalSetDowSetDTO.compNo"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
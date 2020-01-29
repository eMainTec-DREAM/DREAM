<%--===========================================================================
공지부서
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.doc.notice.action.DocNoticeDeptListAction" %>
<%@ page import="dream.doc.notice.action.DocNoticeDeptDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 공지부서 -->
<title><bean:message key='TAB.docNoticeDeptList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var myGrid;

function loadPage() 
{
	docNoticeDeptListForm.elements['docNoticeDeptListDTO.noticeId'].value = 
		docNoticeDeptListForm.elements['docNoticeCommonDTO.noticeId'].value;
	
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
	    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	//페이지id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (docNoticeDeptListForm.elements['docNoticeCommonDTO.noticeId'].value == '') return;
	
	var url = contextPath + "/docNoticeDeptList.do";
	
	docNoticeDeptListForm.elements['strutsAction'].value = '<%=DocNoticeDeptListAction.LIST_FIND%>';
	
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(docNoticeDeptListForm), "NOTIDEPTID", "Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_notiDeptId)
{
    docNoticeDeptListForm.elements['docNoticeDeptListDTO.notiDeptId'].value = _notiDeptId;
    findGridList('ReloadRow');
    docNoticeDeptListForm.elements['docNoticeDeptListDTO.notiDeptId'].value = "";
}

function goSearch()
{
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
	docNoticeDeptListForm.elements['docNoticeDeptListDTO.notiDeptId'].value = getValueById(myGrid, selectedId,'NOTIDEPTID');
	
	goCommonTabPage(docNoticeDeptListForm, <%= DocNoticeDeptDetailAction.DETAIL_INIT %>, "docNoticeDeptDetail");
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('docNoticeDeptDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    docNoticeDeptListForm.elements['docNoticeDeptListDTO.notiDeptId'].value = getValueById(myGrid, selectedId,'NOTIDEPTID');
    docNoticeDeptListForm.elements['strutsAction'].value = '<%=DocNoticeDeptDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(docNoticeDeptListForm), 'docNoticeDeptDetail'); 
} 

 /**
  * 생성
  */
function goCreate()
{
 	createValidationCheck(myGrid, "docNoticeDeptDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
 	docNoticeDeptListForm.elements['docNoticeDeptListDTO.notiDeptId'].value = "";
 	goCommonTabPage(docNoticeDeptListForm, '', pageId);
}
 
  /**
   * 삭제
   */
function goDelete()
{
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'NOTIDEPTID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	docNoticeDeptListForm.strutsAction.value = '<%=DocNoticeDeptListAction.LIST_DELETE%>';
	var url = contextPath + "/docNoticeDeptList.do";
	
	$.post(url,FormQueryString(docNoticeDeptListForm)+delArray , function(_data){
    	afterDelete();
    });
}
  
function afterDelete()
{
	goClose('docNoticeDeptDetail', this);
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
<html:form action="/docNoticeDeptList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="docNoticeCommonDTO.noticeId"/><!-- Key -->
<html:hidden property="docNoticeDeptListDTO.notiDeptId"/><!-- Key -->
<html:hidden property="docNoticeDeptListDTO.noticeId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
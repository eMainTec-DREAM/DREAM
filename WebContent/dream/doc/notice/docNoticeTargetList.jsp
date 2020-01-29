<%--===========================================================================
공지대상
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.doc.notice.action.DocNoticeTargetListAction" %>
<%@ page import="dream.doc.notice.action.DocNoticeTargetDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 공지대상 -->
<title><bean:message key='TAB.docNoticeTargetList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var myGrid;

function loadPage() 
{
	docNoticeTargetListForm.elements['docNoticeTargetListDTO.noticeId'].value = 
		docNoticeTargetListForm.elements['docNoticeCommonDTO.noticeId'].value;
	
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
	if (docNoticeTargetListForm.elements['docNoticeCommonDTO.noticeId'].value == '') return;
	
	var url = contextPath + "/docNoticeTargetList.do";
	
	docNoticeTargetListForm.elements['strutsAction'].value = '<%=DocNoticeTargetListAction.LIST_FIND%>';
	
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(docNoticeTargetListForm), "NOTIUSRID", "Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_notiUsrId)
{
    docNoticeTargetListForm.elements['docNoticeTargetListDTO.notiUsrId'].value = _notiUsrId;
    findGridList('ReloadRow');
    docNoticeTargetListForm.elements['docNoticeTargetListDTO.notiUsrId'].value = "";
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
	docNoticeTargetListForm.elements['docNoticeTargetListDTO.notiUsrId'].value = getValueById(myGrid, selectedId,'NOTIUSRID');
	
	goCommonTabPage(docNoticeTargetListForm, <%= DocNoticeTargetDetailAction.DETAIL_INIT %>, "docNoticeTargetDetail");
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('docNoticeTargetDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    docNoticeTargetListForm.elements['docNoticeTargetListDTO.notiUsrId'].value = getValueById(myGrid, selectedId,'NOTIUSRID');
    docNoticeTargetListForm.elements['strutsAction'].value = '<%=DocNoticeTargetDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(docNoticeTargetListForm), 'docNoticeTargetDetail'); 
} 

 /**
  * 생성
  */
function goCreate()
{
 	createValidationCheck(myGrid, "docNoticeTargetDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
 	docNoticeTargetListForm.elements['docNoticeTargetListDTO.notiUsrId'].value = "";
 	goCommonTabPage(docNoticeTargetListForm, '', pageId);
}
 
  /**
   * 삭제
   */
function goDelete()
{
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'NOTIUSRID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	docNoticeTargetListForm.strutsAction.value = '<%=DocNoticeTargetListAction.LIST_DELETE%>';
	var url = contextPath + "/docNoticeTargetList.do";
	
	$.post(url,FormQueryString(docNoticeTargetListForm)+delArray , function(_data){
    	afterDelete();
    });
}
  
function afterDelete()
{
	goClose('docNoticeTargetDetail');
	//goSearch();
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
<html:form action="/docNoticeTargetList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="docNoticeCommonDTO.noticeId"/><!-- Key -->
<html:hidden property="docNoticeTargetListDTO.notiUsrId"/><!-- Key -->
<html:hidden property="docNoticeTargetListDTO.noticeId"/>
<html:hidden property="docNoticeTargetListDTO.targetId"/>
<html:hidden property="docNoticeTargetListDTO.targetNo"/>
<html:hidden property="docNoticeTargetListDTO.targetDesc"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
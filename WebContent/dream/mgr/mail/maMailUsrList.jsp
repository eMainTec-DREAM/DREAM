<%--===========================================================================
메일수신자설정 - 수신자 목록
author  kim21017
version $Id: maMailUsrList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="common.tree.action.TreeAction" %>
<%@ page import="dream.mgr.mail.action.MaMailUsrListAction" %>
<%@ page import="dream.mgr.mail.action.MaMailUsrDetailAction" %>
<%@ page import="dream.mgr.mail.action.MaMailDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 메일담당자 -->
<title><bean:message key='LABEL.mailManager'/></title>
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
		goTabPage("maMailUsrDetail");
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

/**
 * gird find
 */
function findGridList(sheetAction)
{
	if (maMailUsrListForm.elements['maMailCommonDTO.mailListId'].value == '') return;
	
	var form = document.maMailUsrListForm;	
	form.strutsAction.value = '<%=MaMailUsrListAction.MAIL_USR_LIST_FIND%>';

	var url = contextPath + "/maMailUsrList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maMailUsrListForm), "MAILUSRID", "Y");

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
	var form = document.maMailUsrListForm;
	form.elements['maMailUsrListDTO.mailUsrId'].value = getValueById(myGrid, selectedId,'MAILUSRID');
    
	goCommonTabPage(form, <%= MaMailUsrDetailAction.MAIL_USR_DETAIL_INIT %>, "maMailUsrDetail");
	
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_mailUsrId)
{
	maMailUsrListForm.elements['maMailUsrListDTO.mailUsrId'].value = _mailUsrId;
	findGridList('ReloadRow');
	maMailUsrListForm.elements['maMailUsrListDTO.mailUsrId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maMailUsrDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maMailUsrListForm.elements['maMailUsrListDTO.mailUsrId'].value =  getValueById(myGrid, selectedId,'MAILUSRID');  
    maMailUsrListForm.elements['strutsAction'].value = '<%=MaMailUsrDetailAction.MAIL_USR_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maMailUsrListForm), 'maMailUsrDetail'); 
} 


/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maMailUsrDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maMailUsrListForm.elements['maMailUsrListDTO.mailUsrId'].value = "";
	goCommonTabPage(maMailUsrListForm, '', pageId);
}
 

/**
 * Excel Export
 */
function goExcel()
{
    excelAction(myGrid);
}

/**
 * 삭제
 */
 function goDelete()
 {
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'MAILLISTID', 'MAILUSRID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maMailUsrListForm.strutsAction.value = '<%=MaMailUsrListAction.MAIL_USR_LIST_DELETE%>';
	var url = contextPath + "/maMailUsrList.do";
	
    $.post(url,FormQueryString(maMailUsrListForm)+delArray , function(_data){
    	afterDelete();
    });
 }

function afterDelete(){
	goClose('maMailUsrDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maMailUsrList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maMailCommonDTO.mailListId"/><!-- Key -->
<html:hidden property="maMailUsrListDTO.mailUsrId"/><!-- Detail Key -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" >
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
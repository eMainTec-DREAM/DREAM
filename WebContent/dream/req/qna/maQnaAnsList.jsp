<%--===========================================================================
응답 목록
author  kim21017
version $Id: maQnaAnsList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="common.tree.action.TreeAction" %>
<%@ page import="dream.req.qna.action.MaQnaAnsListAction" %>
<%@ page import="dream.req.qna.action.MaQnaDetailAction" %>
<%@ page import="dream.req.qna.action.MaQnaAnsDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<title><bean:message key='LABEL.questionPoint'/></title>
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
		goTabPage("maQnaAnsDetail");
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
	if (maQnaAnsListForm.elements['maQnaCommonDTO.questionId'].value == '') return;
	
	var form = document.maQnaAnsListForm;	
	form.strutsAction.value = '<%=MaQnaAnsListAction.QNA_ANS_LIST_FIND%>'; 

	var url = contextPath + "/maQnaAnsList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maQnaAnsListForm), "ANSWERID");

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
	var form = document.maQnaAnsListForm;
	form.elements['maQnaAnsListDTO.answerId'].value = getValueById(myGrid, selectedId,'ANSWERID');
    
	goCommonTabPage(form, <%= MaQnaAnsDetailAction.QNA_ANS_DETAIL_INIT %>, "maQnaAnsDetail");
	
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_answerId)
{
	maQnaAnsListForm.elements['maQnaAnsListDTO.answerId'].value = _answerId;
	findGridList('ReloadRow');
	maQnaAnsListForm.elements['maQnaAnsListDTO.answerId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maQnaAnsDetail');
}

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maQnaAnsDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maQnaAnsListForm.elements['maQnaAnsListDTO.answerId'].value = "";
	goCommonTabPage(maQnaAnsListForm, '', pageId);
}
 

/**
 * Excel Export
 */
function goExcel()
{
	maQnaAnsListForm.elements['maQnaAnsListDTO.answerId'].value = "";
	excelServerAction("maQnaAnsList", maQnaAnsListForm);
}

/**
 * 삭제
 */
 function goDelete()
 {
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'QUESTIONID', 'ANSWERID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maQnaAnsListForm.strutsAction.value = '<%=MaQnaAnsListAction.QNA_ANS_LIST_DELETE%>';
	var url = contextPath + "/maQnaAnsList.do";
	
    $.post(url,FormQueryString(maQnaAnsListForm)+delArray , function(_data){
    	afterDelete();
    });
 }

function afterDelete(){
	goClose('maQnaAnsDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maQnaAnsListForm.elements['maQnaAnsListDTO.answerId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maQnaAnsList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maQnaCommonDTO.questionId"/><!-- Key -->
<html:hidden property="maQnaAnsListDTO.answerId"/><!-- Detail Key -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" >
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
<%--===========================================================================
구매입고 item  목록
author  kim21017
version $Id: maPtRecSerialList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="common.tree.action.TreeAction" %>
<%@ page import="dream.part.rec.action.MaPtRecSerialListAction" %>
<%@ page import="dream.part.rec.action.MaPtRecSerialDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<title><bean:message key='LABEL.buyReq'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
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
		goTabPage("maPtRecSerialDetail");
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
	if (maPtRecSerialListForm.elements['maPtRecCommonDTO.prRecListId'].value == '') return;
	
	var form = document.maPtRecSerialListForm;	
	form.strutsAction.value = '<%=MaPtRecSerialListAction.BUY_ITEM_LIST_FIND%>'; 

	var url = contextPath + "/maPtRecSerialList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPtRecSerialListForm), "PRRECLISTSERIALID");

	
	
	

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
	var form = document.maPtRecSerialListForm;
	form.elements['maPtRecSerialListDTO.prreclistSerialId'].value = getValueById(myGrid, selectedId,'PRRECLISTSERIALID');
   
    goCommonTabPage(form, <%= MaPtRecSerialDetailAction.BUY_ITEM_DETAIL_INIT %>, "maPtRecSerialDetail"); 

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_prreclistSerialId)
{
	maPtRecSerialListForm.elements['maPtRecSerialListDTO.prreclistSerialId'].value = _prreclistSerialId;
	findGridList('ReloadRow');
	maPtRecSerialListForm.elements['maPtRecSerialListDTO.prreclistSerialId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maPtRecSerialDetail');
}

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maPtRecSerialDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maPtRecSerialListForm.elements['maPtRecSerialListDTO.prreclistSerialId'].value = "";
	goCommonTabPage(maPtRecSerialListForm, '', pageId);
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
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'PRRECLISTSERIALID','PRRECLISTID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maPtRecSerialListForm.strutsAction.value = '<%=MaPtRecSerialListAction.BUY_ITEM_LIST_DELETE%>';
	var url = contextPath + "/maPtRecSerialList.do";
	
    $.post(url,FormQueryString(maPtRecSerialListForm)+delArray , function(_data){
    	afterDelete();
    });
 }

function afterDelete(){
	goClose('maPtRecSerialDetail', this);
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPtRecSerialList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPtRecCommonDTO.prRecListId"/><!-- Key -->
<html:hidden property="maPtRecCommonDTO.prRecStatus"/><!-- Key -->
<html:hidden property="maPtRecCommonDTO.partId"/><!-- Key -->
<html:hidden property="maPtRecSerialListDTO.prreclistSerialId"/><!-- Detail Key -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" >
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>

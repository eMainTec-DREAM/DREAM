<%--===========================================================================
목록
author  kim21017
version $Id: maPttDisPartsList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="common.tree.action.TreeAction" %>
<%@ page import="dream.tool.adj.action.MaPttDisPartsListAction" %>
<%@ page import="dream.tool.adj.action.MaPttDisPartsDetailAction" %>
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
	//chkStatus();
	
}

function hideButton(id)
{
	var ptDisStatus =maPttDisPartsListForm.elements['maPttDisDetailDTO.ptDisStatus'].value;

	if(ptDisStatus=="W")
	{
		$(document).find('.b_delete').show();
	}
	else
	{	
		$('.b_delete').hide();
		$('.b_create').hide();
	}
}

/* function chkStatus()
{	
	var ptDisStatus =maPttDisPartsListForm.elements['maPttDisDetailDTO.ptDisStatus'].value;

	if(ptDisStatus=="W")
	{
		$(document).find('.b_delete').show();
	}
	else
	{	
		$(document).find('.b_delete').hide();
		$('.b_create').hide();
	}
} */

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("maPttDisPartsDetail");
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	maPttDisPartsListForm.elements['maPttDisPartsListDTO.ptdisuseitemId'].value = "";
        return sortColumn("maPttDisPartsList", this, maPttDisPartsListForm, "ptdisuseitemId", ind, direction);
    });
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
	if (maPttDisPartsListForm.elements['maPttDisCommonDTO.ptdisuselistId'].value == '') return;
	
	var form = document.maPttDisPartsListForm;	
	form.strutsAction.value = '<%=MaPttDisPartsListAction.QNA_ANS_LIST_FIND%>'; 

	var url = contextPath + "/maPttDisPartsList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPttDisPartsListForm), "ptdisuseitemId", "Y");

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
	var form = document.maPttDisPartsListForm;
	form.elements['maPttDisPartsListDTO.ptdisuseitemId'].value = getValueById(myGrid, selectedId,'ptdisuseitemId');
    
	goCommonTabPage(form, <%=MaPttDisPartsDetailAction.QNA_ANS_DETAIL_INIT %>, "maPttDisPartsDetail");
	
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_answerId)
{
	maPttDisPartsListForm.elements['maPttDisPartsListDTO.ptdisuseitemId'].value = _answerId;
	findGridList('ReloadRow');
	maPttDisPartsListForm.elements['maPttDisPartsListDTO.ptdisuseitemId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maPttDisPartsDetail');
}

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maPttDisPartsDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maPttDisPartsListForm.elements['maPttDisPartsListDTO.ptdisuseitemId'].value = "";
	goCommonTabPage(maPttDisPartsListForm, '', pageId);
}
 

/**
 * Excel Export
 */
function goExcel()
{
	maPttDisPartsListForm.elements['maPttDisPartsListDTO.ptdisuseitemId'].value = "";
    excelServerAction("maPttDisPartsList", maPttDisPartsListForm ); 
}

/**
 * 삭제
 */
 function goDelete()
 {
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'ptdisuseitemId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maPttDisPartsListForm.strutsAction.value = '<%=MaPttDisPartsListAction.QNA_ANS_LIST_DELETE%>';
	var url = contextPath + "/maPttDisPartsList.do";
	
    $.post(url,FormQueryString(maPttDisPartsListForm)+delArray , function(_data){
    	afterDelete();
    });
 }

function afterDelete(){
	goClose('maPttDisPartsDetail', this);
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}



//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPttDisPartsList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPttDisCommonDTO.ptdisuselistId"/><!-- Key -->
<html:hidden property="maPttDisDetailDTO.ptDisStatus" />
<html:hidden property="maPttDisPartsListDTO.ptdisuseitemId"/><!-- Detail Key -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" >
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
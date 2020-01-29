<%--===========================================================================
구매절차 Item 목록
author  hyosung
version $Id: invtPrcTpItemList.jsp,v 1.1 2015/12/03 01:45:27 hyosung Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="common.tree.action.TreeAction" %>
<%@ page import="dream.invt.prc.action.InvtPrcTpItemListAction" %>
<%@ page import="dream.invt.prc.action.InvtPrcTpDetailAction" %>
<%@ page import="dream.invt.prc.action.InvtPrcTpItemDetailAction" %>
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
		goOpen();
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		invtPrcTpItemListForm.elements['invtPrcTpItemListDTO.invtPrcPhId'].value = "";
        return sortColumn("invtPrcTpItemList", this, invtPrcTpItemListForm, "INVTPRCPHID", ind, direction);
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
	if (invtPrcTpItemListForm.elements['invtPrcTpCommonDTO.invtPrcTpId'].value == '') return;
	
	var url = contextPath + "/invtPrcTpItemList.do";
    invtPrcTpItemListForm.elements['strutsAction'].value = '<%=InvtPrcTpItemListAction.INVT_LIST_FIND%>';
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(invtPrcTpItemListForm), "INVTPRCPHID", "Y");
	
}


/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_invtPrcPhId)
{
    invtPrcTpItemListForm.elements['invtPrcTpItemListDTO.invtPrcPhId'].value = _invtPrcPhId;
    findGridList('ReloadRow');
    invtPrcTpItemListForm.elements['invtPrcTpItemListDTO.invtPrcPhId'].value = "";
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
	/** List에서 레코드 하나 눌렀을 때 key값으로 정하고 보내준다*/
	invtPrcTpItemListForm.elements['invtPrcTpItemListDTO.invtPrcPhId'].value =getValueById(myGrid, selectedId,'INVTPRCPHID');
	
	goCommonTabPage(invtPrcTpItemListForm, <%= InvtPrcTpItemDetailAction.INVT_DETAIL_INIT %>, pageId);
	
}

/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('invtPrcTpItemDetail');
}
function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;

	invtPrcTpItemListForm.elements['invtPrcTpItemListDTO.invtPrcPhId'].value =getValueById(myGrid, selectedId,'INVTPRCPHID');
	invtPrcTpItemListForm.elements['strutsAction'].value = '<%= InvtPrcTpItemDetailAction.INVT_DETAIL_INIT %>';
    openQuickTabPage(FormQueryString(invtPrcTpItemListForm), 'invtPrcTpItemDetail'); 
}

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "invtPrcTpItemDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	goCommonTabPage(invtPrcTpItemListForm, '', pageId);
}
 

/**
 * Excel Export
 */
function goExcel()
{
//     excelAction(myGrid);
	invtPrcTpItemListForm.elements['invtPrcTpItemListDTO.invtPrcPhId'].value = "";
    excelServerAction("invtPrcTpItemList", invtPrcTpItemListForm);
}

/**
 * 삭제
 */
 function goDelete()
 {
	/** 마찬가지로 삭제할 때 어떤 key값으로 삭제를 할지 */
	var delArray = getDeletRows(myGrid, 'ISDELCHECK','INVTPRCPHID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	invtPrcTpItemListForm.strutsAction.value = '<%=InvtPrcTpItemListAction.INVT_LIST_DELETE%>';
	var url = contextPath + "/invtPrcTpItemList.do";
	
    $.post(url,FormQueryString(invtPrcTpItemListForm)+delArray , function(_data){
    	afterDelete();
    });
 }

function afterDelete(){
	goClose('invtPrcTpItemDetail');
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = invtPrcTpItemListForm.elements['invtPrcTpItemListDTO.invtPrcPhId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/invtPrcTpItemList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="invtPrcTpCommonDTO.invtPrcTpId"/><!-- Key -->
<html:hidden property="invtPrcTpItemListDTO.invtPrcPhId"/><!-- List Detail Key -->
    <!-- searchbox 박스 Line -->
    
    <div class="article_box" >
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
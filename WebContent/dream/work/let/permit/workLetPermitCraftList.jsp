<%--===========================================================================
안전작업허가서유형 - 작업자 목록
author  syyang
version $Id: workLetPermitCraftList.jsp,v 1.1 2015/12/03 01:45:27 syyang Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.let.permit.action.WorkLetPermitCraftListAction" %>
<%@ page import="dream.work.let.permit.action.WorkLetPermitCraftDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 작업자 -->
<title><bean:message key='TAB.maWoResultCraftList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var empAc;
function loadPage() 
{
	setForUpdate();
	
	initGrid();
	
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("workLetPermitCraftDetail");
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		workLetPermitCraftListForm.elements['workLetPermitCraftListDTO.woLetListCraftId'].value = "";
        return sortColumn("workLetPermitCraftList", this, workLetPermitCraftListForm, "WOLETLISTCRAFTID", ind, direction);
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
	if (workLetPermitCraftListForm.elements['workLetPermitDetailDTO.woLetListId'].value == '') return;
	
	var form = document.workLetPermitCraftListForm;	
	form.strutsAction.value = '<%=WorkLetPermitCraftListAction.WO_LET_PERMIT_CRAFT_LIST_FIND %>';
	
	var url = contextPath + "/workLetPermitCraftList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workLetPermitCraftListForm), "WOLETLISTCRAFTID", "Y");
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
	var form = document.workLetPermitCraftListForm;
	 
	form.elements['workLetPermitCraftListDTO.woLetListCraftId'].value = getValueById(myGrid, selectedId,'WOLETLISTCRAFTID');
	goCommonTabPage(form, <%= WorkLetPermitCraftDetailAction.WO_LET_PERMIT_CRAFT_DETAIL_INIT %>, pageId);
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('workLetPermitCraftDetail');
}

function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
 	if(selectedId == null) return;

 	workLetPermitCraftListForm.elements['workLetPermitCraftListDTO.woLetListCraftId'].value = getValueById(myGrid, selectedId,'WOLETLISTCRAFTID');
 	workLetPermitCraftListForm.elements['strutsAction'].value = '<%=WorkLetPermitCraftDetailAction.WO_LET_PERMIT_CRAFT_DETAIL_INIT%>';
 	openQuickTabPage(FormQueryString(workLetPermitCraftListForm), 'workLetPermitCraftDetail'); 
}

/**
 * 엑셀 다운.
 */
function goExcel()
{
	workLetPermitCraftListForm.elements['workLetPermitCraftListDTO.woLetListCraftId'].value = "";
	excelServerAction("workLetPermitCraftList", workLetPermitCraftListForm );
} 
/**
 * 생성
 */
function goCreate()
{
 	createValidationCheck(myGrid, "workLetPermitCraftDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	workLetPermitCraftListForm.elements['workLetPermitCraftListDTO.woLetListCraftId'].value = "";
	goCommonTabPage(workLetPermitCraftListForm, '', pageId);
}
 
/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_woLetListCraftId)
{
	workLetPermitCraftListForm.elements['workLetPermitCraftListDTO.woLetListCraftId'].value = _woLetListCraftId;
	findGridList('ReloadRow');
	workLetPermitCraftListForm.elements['workLetPermitCraftListDTO.woLetListCraftId'].value = "";
}
 
/**
 * 삭제
 */
function goDelete()
{
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'WOLETLISTCRAFTID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	workLetPermitCraftListForm.strutsAction.value = '<%=WorkLetPermitCraftListAction.WO_LET_PERMIT_CRAFT_LIST_DELETE%>';
	var url = contextPath + "/workLetPermitCraftList.do";
	
	$.post(url,FormQueryString(workLetPermitCraftListForm)+delArray , function(_data){
		afterDelete();
	});
}
function afterDelete(){
	goClose('workLetPermitCraftDetail',this);
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

//작업자 상세페이지에 채울 일자와 시간을 작업상세에서 가져온다.
function getDateTime()
{
	 var startDate = parent.M$('workLetPermitDetailDTO.startDate').value;
	 var startTime = parent.M$('workLetPermitDetailDTO.startTime').value;
	 var endDate   = parent.M$('workLetPermitDetailDTO.endDate').value;
	 var endTime   = parent.M$('workLetPermitDetailDTO.endTime').value;
	 return startDate+","+startTime+","+endDate+","+endTime;
}
function getWorkTime()
{
	 var workTime = parent.M$('workLetPermitDetailDTO.workTime').value;
	 return workTime;
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workLetPermitCraftList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="workLetPermitDetailDTO.woLetListId"/>
<html:hidden property="workLetPermitCraftListDTO.woLetListCraftId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
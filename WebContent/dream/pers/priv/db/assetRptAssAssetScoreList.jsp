<%--===========================================================================
구매신청 item  목록
author  nhkim8548
version $Id: orgEmpCertList.jsp,v 1.1 2018/08/03 08:04:00 nhkim8548 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.pers.priv.db.set.action.PersPrivDbSetContDetailAction" %>
<%@ page import="dream.pers.priv.db.set.action.PersPrivDbSetContListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<title><bean:message key='MENU.DASHBOARD'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var cntAc;
function loadPage() 
{

	initGrid();
	
	cntAc = new autoC({"persPrivDbSetCommonDTO.multiDesc":"usrCntDesc"});
    cntAc.setTable("TADBCONTENTS");
    cntAc.setAcResultMap({
        "persPrivDbSetCommonDTO.multiKey":"usrDbCntNo"
    });
    cntAc.setMultiSelect(true);
    cntAc.init();
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
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

// 검색 클릭시 호출
function goSearch()
{
	findGridList('Search');
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/persPrivDbSetContList.do";
    persPrivDbSetContListForm.elements['strutsAction'].value = '<%=PersPrivDbSetContListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(persPrivDbSetContListForm), "usrDbMenuCntId","Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_usrDbMenuCntId)
{
	persPrivDbSetContListForm.elements['persPrivDbSetContListDTO.usrDbMenuCntId'].value = _usrDbMenuCntId;
   	findGridList('ReloadRow');
   	persPrivDbSetContListForm.elements['persPrivDbSetContListDTO.usrDbMenuCntId'].value = "";
}

// 상세 열기
function goOpen()
{
	goTabPage('persPrivDbSetContDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    persPrivDbSetContListForm.elements['persPrivDbSetContListDTO.usrDbMenuCntId'].value =  getValueById(myGrid, selectedId,'usrDbMenuCntId');   
    persPrivDbSetContListForm.elements['strutsAction'].value = '<%=PersPrivDbSetContDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(persPrivDbSetContListForm), 'persPrivDbSetContDetail'); 
}

// Tab 이동시 호출
function goTabPage(pageId)
{
	tabValidationCheck(myGrid, pageId, "goTabPageAction");
}

function goTabPageAction(pageId, selectedId)
{
	persPrivDbSetContListForm.elements['persPrivDbSetContListDTO.usrDbMenuCntId'].value =  getValueById(myGrid, selectedId,'usrDbMenuCntId'); 
	goCommonTabPage(persPrivDbSetContListForm, <%= PersPrivDbSetContDetailAction.DETAIL_INIT %>, "persPrivDbSetContDetail");
}

// 생성
function goCreate()
{
	createValidationCheck(myGrid, "persPrivDbSetContDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	persPrivDbSetContListForm.elements['persPrivDbSetContListDTO.usrDbMenuCntId'].value = "";
	goCommonTabPage(persPrivDbSetContListForm, '', pageId);
}
 


// Excel Export
function goExcel()
{
	persPrivDbSetContListForm.elements['persPrivDbSetContListDTO.usrDbMenuCntId'].value = "";
	excelServerAction("persPrivDbSetContList", persPrivDbSetContListForm );
}

// 삭제
function goDelete()
{
	var delArray = getDeletRows(myGrid, 'ISDELCHECK','usrDbMenuCntId','COMPNO'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	persPrivDbSetContListForm.strutsAction.value = '<%=PersPrivDbSetContListAction.LIST_DELETE%>';
	var url = contextPath + "/persPrivDbSetContList.do";
	
    $.post(url,FormQueryString(persPrivDbSetContListForm)+delArray , function(_data){
    	afterDelete();
    });
}

function afterDelete(){
	goClose('persPrivDbSetContDetail');
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

function goContentsselect() {
	cntAc.openLov();
}

function setAcLovValue(rtnValueArr, acInputName)
{
	afterSetValue();
}
/**
 * 팝업 값 선택 후 DB INSERT
 */
function afterSetValue(lovType,rtnValue)
{
	persPrivDbSetContListForm.strutsAction.value = '<%=PersPrivDbSetContListAction.CNTS_LOV_LIST_INPUT%>';
	var url = contextPath + "/persPrivDbSetContList.do";
	
    $.post(url,FormQueryString(persPrivDbSetContListForm), function(_data){
    	goSearch();
    });
}
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/persPrivDbSetContList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="persPrivDbSetCommonDTO.usrDbId"/>
<html:hidden property="persPrivDbSetContListDTO.usrDbCntId"/>
<html:hidden property="persPrivDbSetContListDTO.usrDbMenuCntId"/>
<html:hidden property="persPrivDbSetCommonDTO.multiKey"/><!-- MultiSelect Key -->
<html:hidden property="persPrivDbSetCommonDTO.multiDesc"/><!-- MultiSelect Desc -->
<!-- searchbox 박스 Line -->
    <div class="article_box" >
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>
</html:form> 
</body>
</html>
<%--===========================================================================
시스템코드 detail - code 목록
author  kim21017
version $Id: maCdSysCodeList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="common.tree.action.TreeAction" %>
<%@ page import="dream.consult.comp.cdsys.action.MaCdSysCodeListAction" %>
<%@ page import="dream.consult.comp.cdsys.action.MaCdSysDetailAction" %>
<%@ page import="dream.consult.comp.cdsys.action.MaCdSysCodeDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 화면 -->
<title><bean:message key='LABEL.code'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="adminPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	//시스템코드 분류 Grid
	initGrid();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("maCdSysCodeDetail");
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
	//listType 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (maCdSysCodeListForm.elements['maCdSysCommonDTO.cdSysMId'].value == '') return;
	
	var form = document.maCdSysCodeListForm;	
	form.strutsAction.value = '<%=MaCdSysCodeListAction.LISTTYPE_CODE_LIST_FIND%>';

	var url = contextPath + "/maCdSysCodeList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maCdSysCodeListForm), "CDSYSDID");

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
	var form = document.maCdSysCodeListForm;
	form.elements['maCdSysCodeListDTO.cdSysDId'].value = getValueById(myGrid, selectedId,'CDSYSDID');
    
	goCommonTabPage(form, <%= MaCdSysCodeDetailAction.LISTTYPE_CODE_DETAIL_INIT %>, "maCdSysCodeDetail");
	
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_cdSysDId)
{
	maCdSysCodeListForm.elements['maCdSysCodeListDTO.cdSysDId'].value = _cdSysDId;
	findGridList('ReloadRow');
	maCdSysCodeListForm.elements['maCdSysCodeListDTO.cdSysDId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maCdSysCodeDetail');
}

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maCdSysCodeDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maCdSysCodeListForm.elements['maCdSysCodeListDTO.cdSysDId'].value = "";
	goCommonTabPage(maCdSysCodeListForm, '', pageId);
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
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'CDSYSMID', 'CDSYSDID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maCdSysCodeListForm.strutsAction.value = '<%=MaCdSysCodeListAction.LISTTYPE_CODE_LIST_DELETE%>';
	var url = contextPath + "/maCdSysCodeList.do";
	
    $.post(url,FormQueryString(maCdSysCodeListForm)+delArray , function(_data){
    	afterDelete();
    });
 }

function afterDelete(){
	goClose('maCdSysCodeDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maCdSysCodeList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maCdSysCommonDTO.cdSysMId"/><!-- Key -->
<html:hidden property="maCdSysCodeListDTO.cdSysDId"/><!-- Detail Key -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" >
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
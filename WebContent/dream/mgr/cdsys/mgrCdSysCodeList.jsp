<%--===========================================================================
시스템코드 detail - code 목록
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="common.tree.action.TreeAction" %>
<%@ page import="dream.mgr.cdsys.action.MgrCdSysCodeListAction" %>
<%@ page import="dream.mgr.cdsys.action.MgrCdSysDetailAction" %>
<%@ page import="dream.mgr.cdsys.action.MgrCdSysCodeDetailAction" %>
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
		goOpen();
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		mgrCdSysCodeListForm.elements['mgrCdSysCodeListDTO.cdSysDId'].value = "";
    	return sortColumn("mgrCdSysCodeList", this, mgrCdSysCodeListForm, "CDSYSDID", ind, direction);
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
	if (mgrCdSysCodeListForm.elements['mgrCdSysCommonDTO.cdSysMId'].value == '') return;
	
	var form = document.mgrCdSysCodeListForm;	
	form.strutsAction.value = '<%=MgrCdSysCodeListAction.LISTTYPE_CODE_LIST_FIND%>';

	var url = contextPath + "/mgrCdSysCodeList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mgrCdSysCodeListForm), "CDSYSDID" ,"Y");

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
	var form = document.mgrCdSysCodeListForm;
	form.elements['mgrCdSysCodeListDTO.cdSysDId'].value = getValueById(myGrid, selectedId,'CDSYSDID');
    
	goCommonTabPage(form, <%= MgrCdSysCodeDetailAction.LISTTYPE_CODE_DETAIL_INIT %>, "mgrCdSysCodeDetail");
	
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_cdSysDId)
{
	mgrCdSysCodeListForm.elements['mgrCdSysCodeListDTO.cdSysDId'].value = _cdSysDId;
	findGridList('ReloadRow');
	mgrCdSysCodeListForm.elements['mgrCdSysCodeListDTO.cdSysDId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('mgrCdSysCodeDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    mgrCdSysCodeListForm.elements['mgrCdSysCodeListDTO.cdSysDId'].value = getValueById(myGrid, selectedId,'CDSYSDID');
    mgrCdSysCodeListForm.elements['strutsAction'].value = '<%=MgrCdSysCodeDetailAction.LISTTYPE_CODE_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(mgrCdSysCodeListForm), 'mgrCdSysCodeDetail'); 
} 
/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "mgrCdSysCodeDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	mgrCdSysCodeListForm.elements['mgrCdSysCodeListDTO.cdSysDId'].value = "";
	goCommonTabPage(mgrCdSysCodeListForm, '', pageId);
}
 

/**
 * Excel Export
 */
function goExcel()
{
	mgrCdSysCodeListForm.elements['mgrCdSysCodeListDTO.cdSysDId'].value = "";
    excelServerAction("mgrCdSysCodeList", mgrCdSysCodeListForm);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/mgrCdSysCodeList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="mgrCdSysCommonDTO.cdSysMId"/><!-- Key -->
<html:hidden property="mgrCdSysCodeListDTO.cdSysDId"/><!-- Detail Key -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" >
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
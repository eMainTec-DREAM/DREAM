<%--===========================================================================
응답 목록
author  kim21017
version $Id: rcmSysFEnvList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="common.tree.action.TreeAction" %>
<%@ page import="dream.rcm.system.action.RcmSysFEnvListAction" %>
<%@ page import="dream.rcm.system.action.RcmSysFEnvDetailAction" %>
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
		goTabPage("rcmSysFEnvDetail");
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
	if (rcmSysFEnvListForm.elements['rcmSysFDefDetailDTO.rcmFuncId'].value == '') return;
	else rcmSysFEnvListForm.elements['rcmSysFEnvListDTO.rcmFuncId'].value = rcmSysFEnvListForm.elements['rcmSysFDefDetailDTO.rcmFuncId'].value; 
	
	var form = document.rcmSysFEnvListForm;
	form.strutsAction.value = '<%=RcmSysFEnvListAction.RCM_SYS_FENV_LIST_FIND%>'; 

	var url = contextPath + "/rcmSysFEnvList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(rcmSysFEnvListForm), "RCMFENVID");

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
	var form = document.rcmSysFEnvListForm;
	form.elements['rcmSysFEnvListDTO.rcmFEnvId'].value = getValueById(myGrid, selectedId,'RCMFENVID');
    
	goCommonTabPage(form, <%= RcmSysFEnvDetailAction.RCM_SYS_FENV_DETAIL_INIT %>, "rcmSysFEnvDetail");
	
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_rcmFEnvId)
{
	rcmSysFEnvListForm.elements['rcmSysFEnvListDTO.rcmFEnvId'].value = _rcmFEnvId;
	findGridList('ReloadRow');
	rcmSysFEnvListForm.elements['rcmSysFEnvListDTO.rcmFEnvId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('rcmSysFEnvDetail');
}

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "rcmSysFEnvDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	rcmSysFEnvListForm.elements['rcmSysFEnvListDTO.rcmFEnvId'].value = "";
	goCommonTabPage(rcmSysFEnvListForm, '', pageId);
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
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'RCMFENVID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	rcmSysFEnvListForm.strutsAction.value = '<%=RcmSysFEnvListAction.RCM_SYS_FENV_LIST_DELETE%>';
	var url = contextPath + "/rcmSysFEnvList.do";
	
    $.post(url,FormQueryString(rcmSysFEnvListForm)+delArray , function(_data){
    	afterDelete();
    });
 }

function afterDelete(){
	goClose('rcmSysFEnvDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * 운전환경 추가 버튼 클릭 시
 */
function goAddfenv()
{
	lovSysDir('rcmSysFEnvListDTO.multiKey','rcmSysFEnvListDTO.multiDesc','RCMFENV_TYPE',{multiSelect:"Y"});
}

/**
 * 팝업 값 선택 후 DB INSERT
 */
function afterSetValue(lovType,rtnValue)
{
	rcmSysFEnvListForm.strutsAction.value = '<%=RcmSysFEnvListAction.RCM_SYS_FENV_LIST_INPUT%>';
	var url = contextPath + "/rcmSysFEnvList.do";
	
    $.post(url,FormQueryString(rcmSysFEnvListForm), function(_data){
    	goSearch()
    });
}


//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/rcmSysFEnvList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="rcmSysCommonDTO.rcmListId"/><!-- Key -->
<html:hidden property="rcmSysFDefDetailDTO.rcmFuncId"/><!-- Key -->
<html:hidden property="rcmSysFEnvListDTO.rcmFuncId"/><!-- Key -->
<html:hidden property="rcmSysFEnvListDTO.rcmFEnvId"/><!-- Detail Key -->
<html:hidden property="rcmSysFEnvListDTO.multiKey"/><!-- MultiSelect Key -->
<html:hidden property="rcmSysFEnvListDTO.multiDesc"/><!-- MultiSelect Desc -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" >
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
<%--===========================================================================
응답 목록
author  kim21017
version $Id: rcmFmeaCrityList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="common.tree.action.TreeAction" %>
<%@ page import="dream.rcm.fmea.action.RcmFmeaCrityListAction" %>
<%@ page import="dream.rcm.fmea.action.RcmFmeaDetailAction" %>
<%@ page import="dream.rcm.fmea.action.RcmFmeaCrityDetailAction" %>
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
		goTabPage("rcmFmeaCrityDetail");
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	rcmFmeaCrityListForm.elements['rcmFmeaCrityListDTO.rcmcrityId'].value = "";
    	return sortColumn("rcmFmeaCrityList", this, rcmFmeaCrityListForm, "rcmcrityId", ind, direction);
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
	if (rcmFmeaCrityListForm.elements['rcmFmeaCommonDTO.rcmfmeaId'].value == '') return;
	
	var form = document.rcmFmeaCrityListForm;	
	form.strutsAction.value = '<%=RcmFmeaCrityListAction.FMEA_CRITY_LIST_FIND%>'; 

	var url = contextPath + "/rcmFmeaCrityList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(rcmFmeaCrityListForm), "rcmcrityId", "Y");

}
var idAry;
function afterSearch()
{
	
	var idArray = new Array();
	for(var i = 0 ; myGrid.getRowsNum() > i; i++)
	{
		var rowId= myGrid.getRowId(i);
		myGrid.setCellTextStyle(rowId,getCoumnIdx(myGrid, "colorDisplay"),"background-color:"+getValueById(myGrid, rowId, "critycolor"));
		idArray.push(getValueById(myGrid, rowId,'crityvalueId'));
	}
	
	idAry = idArray;
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
	var form = document.rcmFmeaCrityListForm;
	form.elements['rcmFmeaCrityListDTO.rcmcrityId'].value = getValueById(myGrid, selectedId,'rcmcrityId');
    
	goCommonTabPage(form, <%= RcmFmeaCrityDetailAction.RCM_FMEA_DETAIL_INIT %>, pageId);
	
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_answerId)
{
	rcmFmeaCrityListForm.elements['rcmFmeaCrityListDTO.rcmcrityId'].value = _answerId;
	findGridList('ReloadRow');
	rcmFmeaCrityListForm.elements['rcmFmeaCrityListDTO.rcmcrityId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('rcmFmeaCrityDetail');
}

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "rcmFmeaCrityDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	rcmFmeaCrityListForm.elements['rcmFmeaCrityListDTO.rcmcrityId'].value = "";
	goCommonTabPage(rcmFmeaCrityListForm, '', pageId);
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
	var delArray = getDeletRows(myGrid, 'ISDELCHECK','rcmcrityId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	rcmFmeaCrityListForm.strutsAction.value = '<%=RcmFmeaCrityListAction.FMEA_CRITY_LIST_DELETE%>';
	var url = contextPath + "/rcmFmeaCrityList.do";
	
    $.post(url,FormQueryString(rcmFmeaCrityListForm)+delArray , function(_data){
    	afterDelete();
    });
 }

function afterDelete(){
	goClose('rcmFmeaCrityDetail', this);
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Matrix 보기 
 */
 function goMatrixview(){
	 	var url   = contextPath + "/rcmCrityMatrix.do";
	 	var popWidth = 1280;
	 	var popHeight = 640;
	     // pop up이 중앙에 위치하게 한다.
	     var TopPosition  = (screen.height/2 - popHeight/2);
	     var LeftPosition = (screen.width/2 - popWidth/2);

	     var pos = "width=" + popWidth + ",height=" + popHeight + "" +
	               ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
	     pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
	 	
	 	var param = "isDecoratorName=popupPage"+
	 				"&strutsAction="+
	 				"&rcmCrityCommonDTO.crityListId="+rcmFmeaCrityListForm.elements['rcmFmeaCommonDTO.critylistId'].value
	 				;

	 	//post 전송
	 	openWindowWithPost(url, "", param, pos);
}

/**
 * 
 */
function saveMartix(_valueId, _saveType)
{
	var ifm = getIframeContent();
	ifm.goSave();
	
	if(_saveType == "DELETE")
		rcmFmeaCrityListForm.strutsAction.value = '<%=RcmFmeaCrityListAction.FMEA_CRITY_DELETE%>';
	else
		rcmFmeaCrityListForm.strutsAction.value = '<%=RcmFmeaCrityListAction.FMEA_CRITY_SAVE%>';
		
	rcmFmeaCrityListForm.elements['rcmFmeaCrityListDTO.crityvalueId'].value = _valueId;
	
	var url = contextPath + "/rcmFmeaCrityList.do";
	
    $.post(url,FormQueryString(rcmFmeaCrityListForm) , function(_data){
    	rcmFmeaCrityListForm.elements['rcmFmeaCrityListDTO.rcmcrityId'].value = "";
    	
    	goSearch();
    });
}

</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/rcmFmeaCrityList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="rcmFmeaCommonDTO.rcmfmeaId"/><!-- Key -->
<html:hidden property="rcmFmeaCommonDTO.rcmlistId"/>
<html:hidden property="rcmFmeaCommonDTO.critylistId"/>

<html:hidden property="rcmFmeaCrityListDTO.crityvalueId"/><!-- Detail Key -->
<html:hidden property="rcmFmeaCrityListDTO.rcmcrityId"/><!-- Detail Key -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" >
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
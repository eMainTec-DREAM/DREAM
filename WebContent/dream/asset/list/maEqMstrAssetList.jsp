<%--===========================================================================
관련자산
author  kim21017
version $Id: maEqMstrAssetList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.list.action.MaEqMstrAssetListAction" %>
<%@ page import="dream.asset.list.action.MaEqMstrAssetDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 관련자산 -->
<title><bean:message key='TAB.maEqMstrAssetList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var isNew = false;
function loadPage() 
{
	initGrid();
}

function afterDisable()
{
	$('.b_open').show();
	$('.b_excel').show();
	$('.b_setting').show();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("maEqMstrAssetDetail");
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		maEqMstrAssetListForm.elements['maEqMstrCommonDTO.equipId'].value == '';
   		return sortColumn("maEqMstrAssetList", this, maEqMstrAssetListForm, "EQASSETID", ind, direction);
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
	if (maEqMstrAssetListForm.elements['maEqMstrCommonDTO.equipId'].value == '') return;
	
	var form = document.maEqMstrAssetListForm;	
	form.strutsAction.value = '<%=MaEqMstrAssetListAction.EQ_MSTR_ASSET_LIST_FIND %>';
	
	var url = contextPath + "/maEqMstrAssetList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maEqMstrAssetListForm), "EQASSETID","Y");

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
	var form = document.maEqMstrAssetListForm;
	 
	if(!isNew)
	{
		form.elements['maEqMstrAssetListDTO.eqAssetId'].value = getValueById(myGrid, selectedId,'EQASSETID');
	}
	goCommonTabPage(form, <%= MaEqMstrAssetDetailAction.EQ_MSTR_ASSET_DETAIL_INIT %>, pageId);
}


/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('maEqMstrAssetDetail');
}

 function goOpenAction()
 {
     var selectedId=myGrid.getSelectedRowId();
     if(selectedId == null) return;
     
     var form = document.maEqMstrAssetListForm;
     
     form.elements['maEqMstrAssetListDTO.eqAssetId'].value =  getValueById(myGrid, selectedId,'EQASSETID');  
     form.elements['strutsAction'].value = '<%=MaEqMstrAssetDetailAction.EQ_MSTR_ASSET_DETAIL_INIT%>';
     openQuickTabPage(FormQueryString(form), 'maEqMstrAssetDetail'); 
 } 


 /**
  * 엑셀 다운.
  */
function goExcel()
{
//   	excelAction(myGrid);
	maEqMstrAssetListForm.elements['maEqMstrAssetListDTO.eqAssetId'].value = "";
	excelServerAction("maEqMstrAssetList", maEqMstrAssetListForm);
}
 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "maEqMstrAssetDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	maEqMstrAssetListForm.elements['maEqMstrAssetListDTO.eqAssetId'].value = "";
	goCommonTabPage(maEqMstrAssetListForm, '', pageId);
 }
 

 /** 
  * 수정된 그리드 1건을 다시 조회한다.
  */
 function findGridRow(_eqAssetId)
 {
	maEqMstrAssetListForm.elements['maEqMstrAssetListDTO.eqAssetId'].value = _eqAssetId;
 	findGridList('ReloadRow');
 	maEqMstrAssetListForm.elements['maEqMstrAssetListDTO.eqAssetId'].value = "";
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'EQASSETID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maEqMstrAssetListForm.strutsAction.value = '<%=MaEqMstrAssetListAction.EQ_MSTR_ASSET_LIST_DELETE%>';
	var url = contextPath + "/maEqMstrAssetList.do";
	
	$.post(url,FormQueryString(maEqMstrAssetListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('maEqMstrAssetDetail',this);
   	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }

function setKeyAftercopy(_newId)
{
	findGridRow(_newId);
	
	//상세 창 열기
	isNew = true;
	maEqMstrAssetListForm.elements['maEqMstrAssetListDTO.eqAssetId'].value = _newId;
	goTabPage("maEqMstrAssetDetail");
	isNew = false;
}
 
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maEqMstrAssetList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maEqMstrCommonDTO.equipId"/><!-- Key -->
<html:hidden property="maEqMstrAssetListDTO.eqAssetId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
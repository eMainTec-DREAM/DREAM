<%--===========================================================================
설비제원(스펙)
author  kim21017
version $Id: assetListTcycleList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.list.action.AssetListTcycleListAction" %>
<%@ page import="dream.asset.list.action.AssetListTcycleDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 설비제원 -->
<title><bean:message key='TAB.assetListTcycleList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

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
		goTabPage("assetListTcycleDetail");
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
        return sortColumn("assetListTcycleList", this, assetListTcycleListForm, "EQPMCYCLEID", ind, direction);
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
	if (assetListTcycleListForm.elements['maEqMstrCommonDTO.equipId'].value == '') return;
	var form = document.assetListTcycleListForm;	
	form.strutsAction.value = '<%=AssetListTcycleListAction.ASSET_LIST_TCYCLE_LIST_FIND %>';
	
	var url = contextPath + "/assetListTcycleList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(assetListTcycleListForm), "EQPMCYCLEID","Y");
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
	var form = document.assetListTcycleListForm;
	 
	form.elements['assetListTcycleListDTO.eqPmCycleId'].value = getValueById(myGrid, selectedId,'EQPMCYCLEID');
	goCommonTabPage(form, <%= AssetListTcycleDetailAction.ASSET_LIST_TCYCLE_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_eqPmCycleId)
{
	assetListTcycleListForm.elements['assetListTcycleListDTO.eqPmCycleId'].value = _eqPmCycleId;
	findGridList('ReloadRow');
	assetListTcycleListForm.elements['assetListTcycleListDTO.eqPmCycleId'].value = "";
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('assetListTcycleDetail');
}
 
 function goOpenAction()
 {
     var selectedId=myGrid.getSelectedRowId();
     if(selectedId == null) return;
     
     assetListTcycleListForm.elements['assetListTcycleListDTO.eqPmCycleId'].value =  getValueById(myGrid, selectedId,'EQPMCYCLEID');  
     assetListTcycleListForm.elements['strutsAction'].value = '<%=AssetListTcycleDetailAction.ASSET_LIST_TCYCLE_DETAIL_INIT%>';
     openQuickTabPage(FormQueryString(assetListTcycleListForm), 'assetListTcycleDetail'); 
 } 


 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
//   	excelAction(myGrid);
		assetListTcycleListForm.elements['assetListTcycleListDTO.eqPmCycleId'].value = "";
		excelServerAction("assetListTcycleList", assetListTcycleListForm);
  }
 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "assetListTcycleDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	assetListTcycleListForm.elements['assetListTcycleListDTO.eqPmCycleId'].value = "";
	goCommonTabPage(assetListTcycleListForm, '', pageId);
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'EQPMCYCLEID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined") return;if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	assetListTcycleListForm.strutsAction.value = '<%=AssetListTcycleListAction.ASSET_LIST_TCYCLE_LIST_DELETE%>';
	var url = contextPath + "/assetListTcycleList.do";
	
	$.post(url,FormQueryString(assetListTcycleListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('assetListTcycleDetail',this);
   	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assetListTcycleList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maEqMstrCommonDTO.equipId"/><!-- Key -->
<html:hidden property="maEqMstrCommonDTO.equipDesc"/>
<html:hidden property="maEqMstrCommonDTO.deptId" />
<html:hidden property="assetListTcycleListDTO.eqPmCycleId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
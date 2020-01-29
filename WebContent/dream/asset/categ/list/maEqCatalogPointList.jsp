<%--===========================================================================
점검항목 리스트
author  euna0207
version $Id: maEqCatalogPointList.jsp,v 1.1 2015/12/03 01:45:27 euna0207 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.categ.list.action.MaEqCatalogPointListAction" %>
<%@ page import="dream.asset.categ.list.action.MaEqCatalogPointDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 점검항목 -->
<title><bean:message key='TAB.maEqMstrPmInsPointList'/></title>
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
		maEqCatalogPointListForm.elements['maEqCatalogPointListDTO.eqCtgPmPointId'].value = "";
    	return sortColumn("maEqCatalogPointList", this, maEqCatalogListForm, "eqCtgPmPointId", ind, direction);
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
	if (maEqCatalogPointListForm.elements['maEqCatalogCommonDTO.eqCtgId'].value == '') return;
	var form = document.maEqCatalogPointListForm;	

	form.strutsAction.value = '<%=MaEqCatalogPointListAction.EQ_MSTR_PMWORK_LIST_FIND %>';
	
	var url = contextPath + "/maEqCatalogPointList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maEqCatalogPointListForm), "eqCtgPmPointId", "Y");
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
	var form = document.maEqCatalogPointListForm;
	
	form.elements['maEqCatalogPointListDTO.eqCtgPmPointId'].value = getValueById(myGrid, selectedId,'eqCtgPmPointId');
	goCommonTabPage(form, <%= MaEqCatalogPointDetailAction.EQ_MSTR_PMWORK_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_eqCtgPmPointId)
{
	maEqCatalogPointListForm.elements['maEqCatalogPointListDTO.eqCtgPmPointId'].value = _eqCtgPmPointId;
	findGridList('ReloadRow');
	maEqCatalogPointListForm.elements['maEqCatalogPointListDTO.eqCtgPmPointId'].value = "";
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('maEqCatalogPointDetail');
}

 function goOpenAction()
 {
     var selectedId=myGrid.getSelectedRowId();
     if(selectedId == null) return;
     
     maEqCatalogPointListForm.elements['maEqCatalogPointListDTO.eqCtgPmPointId'].value =  getValueById(myGrid, selectedId,'eqCtgPmPointId');
     maEqCatalogPointListForm.elements['strutsAction'].value = '<%=MaEqCatalogPointDetailAction.EQ_MSTR_PMWORK_DETAIL_INIT %>';
     openQuickTabPage(FormQueryString(maEqCatalogPointListForm), 'maEqCatalogPointDetail'); 
 } 

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	 maEqCatalogPointListForm.elements['maEqCatalogPointListDTO.eqCtgPmPointId'].value = "";
	 excelServerAction("maEqCatalogPointList", maEqCatalogPointListForm);
//   	excelAction(myGrid);
  }
 
  /**
   * 생성
   */
 function goCreate()
 {
     createValidationCheck(myGrid, "maEqCatalogPointDetail", "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	maEqCatalogPointListForm.elements['maEqCatalogPointListDTO.eqCtgPmPointId'].value = "";
	goCommonTabPage(maEqCatalogPointListForm, '', pageId);
 }
 

 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'eqCtgPmPointId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined") return;if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maEqCatalogPointListForm.strutsAction.value = '<%=MaEqCatalogPointListAction.EQ_MSTR_PMWORK_LIST_DELETE%>';
	var url = contextPath + "/maEqCatalogPointList.do";
	
	$.post(url,FormQueryString(maEqCatalogPointListForm)+delArray , function(_data){
		afterDelete();
	}); 
  }
 
function afterDelete(){
	goClose('maEqCatalogPointDetail',this);
   	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
//-->
</script>

</head> 
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maEqCatalogPointList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maEqCatalogCommonDTO.eqCtgId"/>
<html:hidden property="maEqCatalogCommonDTO.eqTypeId"/>
<html:hidden property="maEqCatalogPointListDTO.eqCtgPmPointId"/>
<html:hidden property="maEqCatalogPointListDTO.eqCtgId"/>
<html:hidden property="maEqCatalogPointListDTO.eqasmbId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
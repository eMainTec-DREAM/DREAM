<%--===========================================================================
설비 정기작업 부품 목록
author  kim21017
version $Id: maEqMstrPmWorkPartList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.list.action.MaEqMstrPmWorkPartListAction" %>
<%@ page import="dream.asset.list.action.MaEqMstrPmWorkPartDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 부품 -->
<title><bean:message key='TAB.maEqMstrPmWorkPartList'/></title>
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
		goTabPage("maEqMstrPmWorkPartDetail");
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		maEqMstrPmWorkPartListForm.elements['maEqMstrPmWorkPartListDTO.pmId'].value = "";
		maEqMstrPmWorkPartListForm.elements['maEqMstrPmWorkPartListDTO.pmPartId'].value = "";
    	return sortColumn("maEqMstrPmWorkPartList", this, maEqMstrListForm, "PMPARTID", ind, direction);
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
	if (maEqMstrPmWorkPartListForm.elements['maEqMstrPmWorkDetailDTO.pmId'].value == '') return;
	var form = document.maEqMstrPmWorkPartListForm;	
	form.strutsAction.value = '<%=MaEqMstrPmWorkPartListAction.EQ_MSTR_PMWORK_PART_LIST_FIND %>';
	
	var url = contextPath + "/maEqMstrPmWorkPartList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maEqMstrPmWorkPartListForm), "PMPARTID", "Y");
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
	var form = document.maEqMstrPmWorkPartListForm;
	
	form.elements['maEqMstrPmWorkPartListDTO.pmId'].value = getValueById(myGrid, selectedId,'PMID');
	form.elements['maEqMstrPmWorkPartListDTO.pmPartId'].value = getValueById(myGrid, selectedId,'PMPARTID');
	goCommonTabPage(form, <%= MaEqMstrPmWorkPartDetailAction.EQ_MSTR_PMWORK_PART_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pmId,_pmPartId)
{
	maEqMstrPmWorkPartListForm.elements['maEqMstrPmWorkPartListDTO.pmId'].value = _pmId;
	maEqMstrPmWorkPartListForm.elements['maEqMstrPmWorkPartListDTO.pmPartId'].value = _pmPartId;
	
	findGridList('ReloadRow');
	
	maEqMstrPmWorkPartListForm.elements['maEqMstrPmWorkPartListDTO.pmId'].value = "";
	maEqMstrPmWorkPartListForm.elements['maEqMstrPmWorkPartListDTO.pmPartId'].value = "";
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('maEqMstrPmWorkPartDetail');
}

 function goOpenAction()
 {
     var selectedId=myGrid.getSelectedRowId();
     if(selectedId == null) return;
     
     maEqMstrPmWorkPartListForm.elements['maEqMstrPmWorkPartListDTO.pmId'].value =  getValueById(myGrid, selectedId,'PMID');
     maEqMstrPmWorkPartListForm.elements['maEqMstrPmWorkPartListDTO.pmPartId'].value =  getValueById(myGrid, selectedId,'PMPARTID');  
     maEqMstrPmWorkPartListForm.elements['strutsAction'].value = '<%=MaEqMstrPmWorkPartDetailAction.EQ_MSTR_PMWORK_PART_DETAIL_INIT %>';
     openQuickTabPage(FormQueryString(maEqMstrPmWorkPartListForm), 'maEqMstrPmWorkPartDetail'); 
 } 

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
//   	excelAction(myGrid);
	maEqMstrPmWorkPartListForm.elements['maEqMstrPmWorkPartListDTO.pmId'].value = "";
	maEqMstrPmWorkPartListForm.elements['maEqMstrPmWorkPartListDTO.pmPartId'].value = "";
	excelServerAction("maEqMstrPmWorkPartList", maEqMstrPmWorkPartListForm);
  }
 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "maEqMstrPmWorkPartDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	maEqMstrPmWorkPartListForm.elements['maEqMstrPmWorkPartListDTO.pmId'].value = "";
	maEqMstrPmWorkPartListForm.elements['maEqMstrPmWorkPartListDTO.pmPartId'].value = "";
	goCommonTabPage(maEqMstrPmWorkPartListForm, '', pageId);
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'PMID','PMPARTID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined") return;if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maEqMstrPmWorkPartListForm.strutsAction.value = '<%=MaEqMstrPmWorkPartListAction.EQ_MSTR_PMWORK_PART_LIST_DELETE%>';
	var url = contextPath + "/maEqMstrPmWorkPartList.do";
	
	$.post(url,FormQueryString(maEqMstrPmWorkPartListForm)+delArray , function(_data){
		afterDelete();
	}); 
  }
 
function afterDelete(){
	goClose('maEqMstrPmWorkPartDetail',this);
   	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maEqMstrPmWorkPartList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maEqMstrCommonDTO.equipId"/>
<html:hidden property="maEqMstrPmWorkDetailDTO.pmId"/>
<html:hidden property="maEqMstrPmWorkPartListDTO.pmId"/>
<html:hidden property="maEqMstrPmWorkPartListDTO.pmPartId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
<%--===========================================================================
구성자재
author  kim21017
version $Id: maEqMstrMoldModHistList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.list.action.MaEqMstrMoldModHistListAction"%>
<%@ page import="dream.asset.list.action.MaEqMstrPartDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 구성자재 -->
<title><bean:message key='TAB.maEqMstrMoldModHistList'/></title>
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
		goTabPage("maEqMstrMoldModHistDetail");
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox","goSearch"); // grid, grid id, callBack
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
	if (maEqMstrMoldModHistListForm.elements['maEqMstrCommonDTO.equipId'].value == '') return;
	
	var form = document.maEqMstrMoldModHistListForm;	
	form.strutsAction.value = '<%=MaEqMstrMoldModHistListAction.EQ_MSTR_MOLDPROD_LIST_FIND %>';
	
	var url = contextPath + "/maEqMstrMoldModHistList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maEqMstrMoldModHistListForm), "EQMOLDMODHISTID","Y");

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
	var form = document.maEqMstrMoldModHistListForm;
	 
	//form.elements['maEqMstrCommonDTO.equipId'].value = getValueById(myGrid, selectedId,'EQMOLDMODHISTID');
	maEqMstrMoldModHistListForm.elements['maEqMstrMoldModHistListDTO.eqMoldModHistId'].value = getValueById(myGrid, selectedId,'EQMOLDMODHISTID');
	goCommonTabPage(form, <%= MaEqMstrPartDetailAction.EQ_MSTR_PART_DETAIL_INIT %>, pageId);
}


/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('maEqMstrMoldModHistDetail');
}

 function goOpenAction()
 {
     var selectedId=myGrid.getSelectedRowId();
     if(selectedId == null) return;
     
     maEqMstrMoldModHistListForm.elements['assAssetCommonDTO.eqasslistId'].value =  getValueById(myGrid, selectedId,'EQMOLDMODHISTID');  
     maEqMstrMoldModHistListForm.elements['strutsAction'].value = '<%=MaEqMstrPartDetailAction.EQ_MSTR_PART_DETAIL_INIT%>';
     openQuickTabPage(FormQueryString(maEqMstrMoldModHistListForm), 'maEqMstrMoldModHistDetail'); 
 } 

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
//   	excelAction(myGrid);
	maEqMstrMoldModHistListForm.elements['maEqMstrMoldModHistListDTO.eqMoldModHistId'].value = "";
	excelServerAction("maEqMstrMoldModHistList", maEqMstrMoldModHistListForm);
  }
 
  /**
   * 생성
   */
 function goCreate()
 {
	  createValidationCheck(myGrid, "maEqMstrMoldModHistDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	maEqMstrMoldModHistListForm.elements['maEqMstrMoldModHistListDTO.eqMoldModHistId'].value = "";
	goCommonTabPage(maEqMstrMoldModHistListForm, '', pageId);
 }
 
 /** 
  * 수정된 그리드 1건을 다시 조회한다.
  */
 function findGridRow(_eqPartId)
 {
	maEqMstrMoldModHistListForm.elements['maEqMstrMoldModHistListDTO.eqMoldModHistId'].value = _eqPartId;
 	findGridList('ReloadRow');
 	maEqMstrMoldModHistListForm.elements['maEqMstrMoldModHistListDTO.eqMoldModHistId'].value = "";
 }
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'EQMOLDMODHISTID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined") return;

	maEqMstrMoldModHistListForm.strutsAction.value = '<%=MaEqMstrMoldModHistListAction.EQ_MSTR_MOLDPROD_LIST_DELETE%>';
	var url = contextPath + "/maEqMstrMoldModHistList.do";
	
	$.post(url,FormQueryString(maEqMstrMoldModHistListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('maEqMstrMoldModHistDetail',this);
   	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
   //detail EquipId 가져오기
   function getEquipId(){
	   return parent.M$('maEqMstrDetailDTO.equipId').value;
   }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maEqMstrMoldModHistList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maEqMstrCommonDTO.equipId"/><!-- Key -->
<html:hidden property="maEqMstrMoldModHistListDTO.eqMoldModHistId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
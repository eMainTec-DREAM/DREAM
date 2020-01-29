<%--===========================================================================
생산품목
author  euna0207
version $Id: maEqMoldMstrProdList.jsp,v 1.1 2019/08/05 01:45:27 euna0207 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.list.action.MaEqMstrMoldProdListAction"%>
<%@ page import="dream.asset.list.action.MaEqMstrPartDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!--  생산품목 -->
<title><bean:message key='TAB.maEqMoldMstrProdList'/></title>
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
		goTabPage("maEqMoldMstrProdDetail");
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
        return sortColumn("maEqMoldMstrProdList", this, maEqMstrMoldProdListForm, "EQMOLDPRODUCTID", ind, direction);
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
	if (maEqMstrMoldProdListForm.elements['maEqMstrCommonDTO.equipId'].value == '') return;
	
	var form = document.maEqMstrMoldProdListForm;	
	form.strutsAction.value = '<%=MaEqMstrMoldProdListAction.EQ_MSTR_MOLDPROD_LIST_FIND %>';
	
	var url = contextPath + "/maEqMoldMstrProdList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maEqMstrMoldProdListForm), "EQMOLDPRODUCTID","Y");

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
	var form = document.maEqMstrMoldProdListForm;
	 
	form.elements['maEqMstrMoldProdListDTO.eqMoldProductId'].value = getValueById(myGrid, selectedId,'EQMOLDPRODUCTID');
	goCommonTabPage(form, <%= MaEqMstrPartDetailAction.EQ_MSTR_PART_DETAIL_INIT %>, pageId);
}


/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('maEqMoldMstrProdDetail');
}
 
  function goOpenAction()
  {
      var selectedId=myGrid.getSelectedRowId();
      if(selectedId == null) return;
      
      maEqMstrMoldProdListForm.elements['maEqMstrMoldProdListDTO.eqMoldProductId'].value =  getValueById(myGrid, selectedId,'EQMOLDPRODUCTID');  
      maEqMstrMoldProdListForm.elements['strutsAction'].value = '<%=MaEqMstrPartDetailAction.EQ_MSTR_PART_DETAIL_INIT%>';
      openQuickTabPage(FormQueryString(maEqMstrMoldProdListForm), 'maEqMoldMstrProdDetail'); 
  } 
 

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
//   	excelAction(myGrid);
	maEqMstrMoldProdListForm.elements['maEqMstrMoldProdListDTO.eqMoldProductId'].value = "";
	excelServerAction("maEqMoldMstrProdList", maEqMstrMoldProdListForm);
  }
  /**
   * 생성
   */
 function goCreate()
 {
	  createValidationCheck(myGrid, "maEqMoldMstrProdDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	maEqMstrMoldProdListForm.elements['maEqMstrMoldProdListDTO.eqMoldProductId'].value = "";
	goCommonTabPage(maEqMstrMoldProdListForm, '', pageId);
 }
 
 /** 
  * 수정된 그리드 1건을 다시 조회한다.
  */
 function findGridRow(_eqMoldProductId)
 {
	maEqMstrMoldProdListForm.elements['maEqMstrMoldProdListDTO.eqMoldProductId'].value = _eqMoldProductId;
 	findGridList('ReloadRow');
 	maEqMstrMoldProdListForm.elements['maEqMstrMoldProdListDTO.eqMoldProductId'].value = "";
 }
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'EQMOLDPRODUCTID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined") return;

	maEqMstrMoldProdListForm.strutsAction.value = '<%=MaEqMstrMoldProdListAction.EQ_MSTR_MOLDPROD_LIST_DELETE%>';
	var url = contextPath + "/maEqMoldMstrProdList.do";
	
	$.post(url,FormQueryString(maEqMstrMoldProdListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('maEqMoldMstrProdDetail',this);
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
<html:form action="/maEqMoldMstrProdList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maEqMstrCommonDTO.equipId"/><!-- Key -->
<html:hidden property="maEqMstrMoldProdListDTO.eqMoldProductId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
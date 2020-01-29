<%--===========================================================================
설비 정기점검 목록
author  kim21017
version $Id: maEqMstrPmInsList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.list.action.MaEqMstrPmInsListAction" %>
<%@ page import="dream.asset.list.action.MaEqMstrPmInsDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 정기점검 -->
<title><bean:message key='TAB.maEqMstrPmInsList'/></title>
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
		goTabPage("maEqMstrPmInsDetail");
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		maEqMstrPmInsListForm.elements['maEqMstrPmInsListDTO.pmId'].value = "";
		maEqMstrPmInsListForm.elements['maEqMstrPmInsListDTO.pmEquipId'].value = "";
    	return sortColumn("maEqMstrPmInsList", this, maEqMstrPmInsListForm, "PMID", ind, direction);
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
	if (maEqMstrPmInsListForm.elements['maEqMstrCommonDTO.equipId'].value == '') return;
	var form = document.maEqMstrPmInsListForm;	
	form.strutsAction.value = '<%=MaEqMstrPmInsListAction.EQ_MSTR_PMINS_LIST_FIND %>';
	
	var url = contextPath + "/maEqMstrPmInsList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maEqMstrPmInsListForm), "PMID", "Y");
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
	var form = document.maEqMstrPmInsListForm;
	
	form.elements['maEqMstrPmInsListDTO.pmId'].value = getValueById(myGrid, selectedId,'PMID');
	form.elements['maEqMstrPmInsListDTO.pmEquipId'].value = getValueById(myGrid, selectedId,'PMEQUIPID');
	goCommonTabPage(form, <%= MaEqMstrPmInsDetailAction.EQ_MSTR_PMINS_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pmId,_pmEquipId)
{
	maEqMstrPmInsListForm.elements['maEqMstrPmInsListDTO.pmId'].value = _pmId;
	maEqMstrPmInsListForm.elements['maEqMstrPmInsListDTO.pmEquipId'].value = _pmEquipId;
	
	findGridList('ReloadRow');
	
	maEqMstrPmInsListForm.elements['maEqMstrPmInsListDTO.pmId'].value = "";
	maEqMstrPmInsListForm.elements['maEqMstrPmInsListDTO.pmEquipId'].value = "";
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('maEqMstrPmInsDetail');
}

 function goOpenAction()
 {
     var selectedId=myGrid.getSelectedRowId();
     if(selectedId == null) return;
     
     maEqMstrPmInsListForm.elements['maEqMstrPmInsListDTO.pmId'].value =  getValueById(myGrid, selectedId,'PMID');
     maEqMstrPmInsListForm.elements['maEqMstrPmInsListDTO.pmEquipId'].value =  getValueById(myGrid, selectedId,'PMEQUIPID');  
     maEqMstrPmInsListForm.elements['strutsAction'].value = '<%=MaEqMstrPmInsDetailAction.EQ_MSTR_PMINS_DETAIL_INIT %>';
     openQuickTabPage(FormQueryString(maEqMstrPmInsListForm), 'maEqMstrPmInsDetail'); 
 } 

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
//   	excelAction(myGrid);
	maEqMstrPmInsListForm.elements['maEqMstrPmInsListDTO.pmId'].value = "";
	maEqMstrPmInsListForm.elements['maEqMstrPmInsListDTO.pmEquipId'].value = "";
	excelServerAction("maEqMstrPmInsList", maEqMstrPmInsListForm);
  }
 
  /**
   * 생성
   */
 function goCreate()
 {
	openSelectType();
 }

 function goCreateAction(pageId)
 {
	maEqMstrPmInsListForm.elements['maEqMstrPmInsListDTO.pmId'].value = "";
	maEqMstrPmInsListForm.elements['maEqMstrPmInsListDTO.pmEquipId'].value = "";
	goCommonTabPage(maEqMstrPmInsListForm, '', pageId);
 }
 

 /**
  * 작업형태 선택창 열기
  */
 function openSelectType(){
 	var param = "strutsAction=1001";
 	param += "&"+"maPmMstrSelectDTO.selectedPmType=PMI_TYPE";
 	
 	var url =  contextPath + "/workPmListSelectPm.do";
 	
 	openLayerPopup("workPmListSelectPm", param);
 }
 
 function setAfterSelect(returnArray){
	var pmType = returnArray[0];
	maEqMstrPmInsListForm.elements['maEqMstrPmInsListDTO.selectedPmType'].value = pmType;
	createValidationCheck(myGrid, "maEqMstrPmInsDetail" , "goCreateAction");
 }
 
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'PMID','PMEQUIPID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined") return;if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maEqMstrPmInsListForm.strutsAction.value = '<%=MaEqMstrPmInsListAction.EQ_MSTR_PMINS_LIST_DELETE%>';
	var url = contextPath + "/maEqMstrPmInsList.do";
	
	$.post(url,FormQueryString(maEqMstrPmInsListForm)+delArray , function(_data){
		afterDelete();
	}); 
  }
 
function afterDelete(){
	goClose('maEqMstrPmInsDetail',this);
   	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maEqMstrPmInsList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maEqMstrCommonDTO.equipId"/>
<html:hidden property="maEqMstrPmInsListDTO.selectedPmType"/>
<html:hidden property="maEqMstrPmInsListDTO.pmId"/>
<html:hidden property="maEqMstrPmInsListDTO.pmEquipId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
<%--===========================================================================
설비 정기작업 목록
author  kim21017
version $Id: maEqMstrPmWorkList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.list.action.MaEqMstrPmWorkListAction" %>
<%@ page import="dream.asset.list.action.MaEqMstrPmWorkDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 정기작업 -->
<title><bean:message key='TAB.maEqMstrPmWorkList'/></title>
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
		goTabPage("maEqMstrPmWorkDetail");
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		maEqMstrPmWorkListForm.elements['maEqMstrPmWorkListDTO.pmId'].value = "";
    	return sortColumn("maEqMstrPmWorkList", this, maEqMstrPmWorkListForm, "PMID", ind, direction);
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
	if (maEqMstrPmWorkListForm.elements['maEqMstrCommonDTO.equipId'].value == '') return;
	var form = document.maEqMstrPmWorkListForm;	
	form.strutsAction.value = '<%=MaEqMstrPmWorkListAction.EQ_MSTR_PMWORK_LIST_FIND %>';
	
	var url = contextPath + "/maEqMstrPmWorkList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maEqMstrPmWorkListForm), "PMID", "Y");
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
	var form = document.maEqMstrPmWorkListForm;
	
	form.elements['maEqMstrPmWorkListDTO.pmId'].value = getValueById(myGrid, selectedId,'PMID');
	form.elements['maEqMstrPmWorkListDTO.pmEquipId'].value = getValueById(myGrid, selectedId,'PMEQUIPID');
	goCommonTabPage(form, <%= MaEqMstrPmWorkDetailAction.EQ_MSTR_PMWORK_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pmId,_pmEquipId)
{
	maEqMstrPmWorkListForm.elements['maEqMstrPmWorkListDTO.pmId'].value = _pmId;
	maEqMstrPmWorkListForm.elements['maEqMstrPmWorkListDTO.pmEquipId'].value = _pmEquipId;
	
	findGridList('ReloadRow');
	
	maEqMstrPmWorkListForm.elements['maEqMstrPmWorkListDTO.pmId'].value = "";
	maEqMstrPmWorkListForm.elements['maEqMstrPmWorkListDTO.pmEquipId'].value = "";
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('maEqMstrPmWorkDetail');
}

 function goOpenAction()
 {
     var selectedId=myGrid.getSelectedRowId();
     if(selectedId == null) return;
     
     maEqMstrPmWorkListForm.elements['maEqMstrPmWorkListDTO.pmId'].value =  getValueById(myGrid, selectedId,'PMID');
     maEqMstrPmWorkListForm.elements['maEqMstrPmWorkListDTO.pmEquipId'].value =  getValueById(myGrid, selectedId,'PMEQUIPID');  
     maEqMstrPmWorkListForm.elements['strutsAction'].value = '<%=MaEqMstrPmWorkDetailAction.EQ_MSTR_PMWORK_DETAIL_INIT %>';
     openQuickTabPage(FormQueryString(maEqMstrPmWorkListForm), 'maEqMstrPmWorkDetail'); 
 } 

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
//   	excelAction(myGrid);
	maEqMstrPmWorkListForm.elements['maEqMstrPmWorkListDTO.pmId'].value = "";
	maEqMstrPmWorkListForm.elements['maEqMstrPmWorkListDTO.pmEquipId'].value = "";
	excelServerAction("maEqMstrPmWorkList", maEqMstrPmWorkListForm);
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
	maEqMstrPmWorkListForm.elements['maEqMstrPmWorkListDTO.pmId'].value = "";
	maEqMstrPmWorkListForm.elements['maEqMstrPmWorkListDTO.pmEquipId'].value = "";
	goCommonTabPage(maEqMstrPmWorkListForm, '', pageId);
 }
 

 /**
  * 작업형태 선택창 열기
  */
 function openSelectType(){
 	var param = "strutsAction=1001";
 	param += "&"+"maPmMstrSelectDTO.selectedPmType=PMW_TYPE";
 	
 	var url =  contextPath + "/workPmListSelectPm.do";
 	
 	openLayerPopup("workPmListSelectPm", param);
 }
 
 function setAfterSelect(returnArray){
	var pmType = returnArray[0];
	maEqMstrPmWorkListForm.elements['maEqMstrPmWorkListDTO.selectedPmType'].value = pmType;
	createValidationCheck(myGrid, "maEqMstrPmWorkDetail" , "goCreateAction");
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

	maEqMstrPmWorkListForm.strutsAction.value = '<%=MaEqMstrPmWorkListAction.EQ_MSTR_PMWORK_LIST_DELETE%>';
	var url = contextPath + "/maEqMstrPmWorkList.do";
	
	$.post(url,FormQueryString(maEqMstrPmWorkListForm)+delArray , function(_data){
		afterDelete();
	}); 
  }
 
function afterDelete(){
	goClose('maEqMstrPmWorkDetail',this);
   	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
//-->
</script>

</head> 
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maEqMstrPmWorkList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maEqMstrCommonDTO.equipId"/>
<html:hidden property="maEqMstrPmWorkListDTO.selectedPmType"/>
<html:hidden property="maEqMstrPmWorkListDTO.pmId"/>
<html:hidden property="maEqMstrPmWorkListDTO.pmEquipId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
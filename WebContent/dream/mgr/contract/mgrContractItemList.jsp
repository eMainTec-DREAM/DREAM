<%--===========================================================================
가동시간설정 - 단가계약 설정 목록
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.contract.action.MgrContractItemAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%> 
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 단가계약설정 -->
<title><bean:message key='PAGE.mgrContractItemList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	mgrContractItemForm.elements['mgrContractItemDTO.contractId'].value
	= mgrContractItemForm.elements['mgrContractDTO.contractId'].value;
	
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
    	mgrContractItemForm.elements['mgrContractDTO.contractId'].value = "";
        return sortColumn("mgrContractItemList", this, mgrContractItemForm, "contractId", ind, direction);
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
	if (mgrContractItemForm.elements['mgrContractDTO.contractId'].value == '') return;
	var form = document.mgrContractItemForm;	
	form.strutsAction.value = '<%=MgrContractItemAction.LIST_FIND %>';
	
	var url = contextPath + "/mgrContractItemList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mgrContractItemForm), "CONTRACTITEMID", "Y");
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
	var form = document.mgrContractItemForm;
	 
	form.elements['mgrContractItemDTO.contractItemId'].value = getValueById(myGrid, selectedId,'CONTRACTITEMID');
	goCommonTabPage(form, <%= MgrContractItemAction.DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_contractItemId)
{
	mgrContractItemForm.elements['mgrContractItemDTO.contractItemId'].value = _contractItemId;
	findGridList('ReloadRow');
	mgrContractItemForm.elements['mgrContractItemDTO.contractItemId'].value = "";
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('mgrContractItemDetail');
}

function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
    
    if(selectedId == null) return;

    mgrContractItemForm.elements['mgrContractItemDTO.contractItemId'].value = getValueById(myGrid, selectedId,'CONTRACTITEMID');
    mgrContractItemForm.elements['strutsAction'].value = '<%= MgrContractItemAction.DETAIL_INIT %>';
    	
    openQuickTabPage(FormQueryString(mgrContractItemForm), 'mgrContractItemDetail'); 

	
}

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	mgrContractItemForm.elements['mgrContractItemDTO.contractItemId'].value = "";
  	excelServerAction("mgrContractItemList",mgrContractItemForm);
  }
 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "mgrContractItemDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	mgrContractItemForm.elements['mgrContractItemDTO.contractItemId'].value = "";
	goCommonTabPage(mgrContractItemForm, '', pageId);
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'CONTRACTITEMID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	mgrContractItemForm.strutsAction.value = '<%=MgrContractItemAction.LIST_DELETE%>';
	var url = contextPath + "/mgrContractItemList.do";
	
	$.post(url,FormQueryString(mgrContractItemForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('mgrContractItemDetail',this);
   	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/mgrContractItemList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="mgrContractDTO.contractId"/><!-- Key -->
<html:hidden property="mgrContractItemDTO.contractId"/>
<html:hidden property="mgrContractItemDTO.contractItemId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
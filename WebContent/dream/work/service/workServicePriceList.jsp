<%--===========================================================================
서비스 - 단가 목록
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.service.action.WorkServicePriceAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%> 
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 단가 -->
<title><bean:message key='PAGE.workServicePriceList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	workServicePriceForm.elements['workServicePriceDTO.serviceId'].value
	= workServicePriceForm.elements['workServiceCommonDTO.serviceId'].value;
	
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
    	workServicePriceForm.elements['workServiceCommonDTO.serviceId'].value = "";
        return sortColumn("workServicePriceList", this, workServicePriceForm, "serviceId", ind, direction);
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
	if (workServicePriceForm.elements['workServiceCommonDTO.serviceId'].value == '') return;
	var form = document.workServicePriceForm;	
	form.strutsAction.value = '<%=WorkServicePriceAction.LIST_FIND %>';
	
	var url = contextPath + "/workServicePriceList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workServicePriceForm), "servicePriceId", "Y");
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
	var form = document.workServicePriceForm;
	 
	form.elements['workServicePriceDTO.servicePriceId'].value = getValueById(myGrid, selectedId,'servicePriceId');
	goCommonTabPage(form, <%= WorkServicePriceAction.DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_servicePriceId)
{
	workServicePriceForm.elements['workServicePriceDTO.servicePriceId'].value = _servicePriceId;
	findGridList('ReloadRow');
	workServicePriceForm.elements['workServicePriceDTO.servicePriceId'].value = "";
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('workServicePriceDetail');
}

function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
    
    if(selectedId == null) return;

    workServicePriceForm.elements['workServicePriceDTO.servicePriceId'].value = getValueById(myGrid, selectedId,'servicePriceId');
    workServicePriceForm.elements['strutsAction'].value = '<%= WorkServicePriceAction.DETAIL_INIT %>';
    	
    openQuickTabPage(FormQueryString(workServicePriceForm), 'workServicePriceDetail'); 

	
}

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	workServicePriceForm.elements['workServicePriceDTO.servicePriceId'].value = "";
  	excelServerAction("workServicePriceList",workServicePriceForm);
  }
 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "workServicePriceDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	workServicePriceForm.elements['workServicePriceDTO.servicePriceId'].value = "";
	goCommonTabPage(workServicePriceForm, '', pageId);
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'servicePriceId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	workServicePriceForm.strutsAction.value = '<%=WorkServicePriceAction.LIST_DELETE%>';
	var url = contextPath + "/workServicePriceList.do";
	
	$.post(url,FormQueryString(workServicePriceForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('workServicePriceDetail',this);
   	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workServicePriceList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="workServiceCommonDTO.serviceId"/><!-- Key -->
<html:hidden property="workServicePriceDTO.serviceId"/>
<html:hidden property="workServicePriceDTO.servicePriceId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
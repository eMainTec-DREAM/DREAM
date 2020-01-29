<%--===========================================================================
요청접수 (투자요청) - 처리사항 
author  js.lee
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.req.work.action.MaWoReqResListAction" %> 
<%@ page import="dream.req.work.action.MaWoReqResDetailAction" %> 
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html> 
<head>
<!-- 처리사항 -->
<title><bean:message key='TAB.maWoReqResList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var beforePageId= '';
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
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
	myGrid.init();
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		var invtlistId = getValueById(myGrid, rowId,'INVTLISTID');
		
		maWoReqResListForm.elements['invtCommonDTO.invtlistId'].value = invtlistId;

		if(invtlistId != "") goTabPage("invtDetail");
	});
	
	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}


/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	maWoReqResListForm.elements['maWoReqResListDTO.woReqResId'].value = "";
	
    findGridList('Search');   
}

function findGridList(sheetAction)
{
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (maWoReqResListForm.elements['maWoReqCommonDTO.woReqId'].value == '') return;
	
	var form = document.maWoReqResListForm;	
	form.strutsAction.value = '<%=MaWoReqResListAction.LIST_FIND %>';
	
	var url = contextPath + "/reqInvRecWorkResList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maWoReqResListForm), "WOREQRESID", "Y");

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
	var form = document.maWoReqResListForm;
	 
	form.elements['maWoReqResListDTO.woReqResId'].value = getValueById(myGrid, selectedId,'WOREQRESID');
	goCommonTabPage(form, <%= MaWoReqResDetailAction.DETAIL_INIT %>, pageId, beforePageId);

	beforePageId = pageId;
}
function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
 	if(selectedId == null) return;

 	maWoReqResListForm.elements['maWoReqResListDTO.woReqResId'].value = getValueById(myGrid, selectedId,'WOREQRESID');
 	maWoReqResListForm.elements['strutsAction'].value = '<%=MaWoReqResDetailAction.DETAIL_INIT%>';
 	openQuickTabPage(FormQueryString(maWoReqResListForm), 'reqInvRecWorkResDetail'); 
}
 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
  	excelAction(myGrid);
  } 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "invtDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	 maWoReqResListForm.elements['maWoReqResListDTO.woReqResId'].value = "";
	goCommonTabPage(maWoReqResListForm, '', pageId);
 }
 
 /** 
  * 수정된 그리드 1건을 다시 조회한다.
  */
 function findGridRow(_woReqResId,_resStatus)
 {
	maWoReqResListForm.elements['maWoReqResListDTO.woReqResId'].value = _woReqResId;
 	findGridList('ReloadRow');
 	maWoReqResListForm.elements['maWoReqResListDTO.woReqResId'].value = "";
 	//헤더 상태변경
 	if (parent.changStatus) parent.changStatus(_resStatus);
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'WOREQRESID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maWoReqResListForm.strutsAction.value = '<%=MaWoReqResListAction.LIST_DELETE%>';
	var url = contextPath + "/reqInvRecWorkResList.do";
	
	$.post(url,FormQueryString(maWoReqResListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('reqInvRecWorkResDetail',this);
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
   

/**
 * 투자목록생성버튼
 */
function goInvtcreate(){
	var ifm = getIframeContent();
	
	var _param = "strutsAction=0"+
				 "&invtCommonDTO.compNo="+loginUser.compNo+
				 "&invtDetailDTO.woReqId="+maWoReqResListForm.elements['maWoReqCommonDTO.woReqId'].value;
	openQuickTabPage(_param, "invtDetail"); 
}

/**
 * 기존투자연결버튼
 */
function goInvtselect(){
	var param = "strutsAction=1001";
	var url =  contextPath + "/lovInvtList.do";
	openLayerPopup("lovInvtList", param);
}


function setLovValue(_arrayData, _type)
{
	var invtlistId = _arrayData[0];

	maWoReqResListForm.strutsAction.value = '<%=MaWoReqResListAction.LIST_INVT_LINK %>';
	maWoReqResListForm.elements['maWoReqCommonDTO.invtlistId'].value = invtlistId;
	
	var url = contextPath + "/maWoReqResList.do";
	
	$.post(url,FormQueryString(maWoReqResListForm), function(_data){
    	goSearch();
	
	});
	
   	if (parent.parent.findGridRow)
        parent.parent.findGridRow(maWoReqResListForm.elements['maWoReqCommonDTO.woReqId'].value);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/reqInvRecWorkResList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maWoReqCommonDTO.woReqId"/><!-- Key -->
<html:hidden property="maWoReqCommonDTO.wkorId"/>
<html:hidden property="maWoReqCommonDTO.invtlistId"/>
<html:hidden property="invtCommonDTO.invtlistId"/>
<html:hidden property="maWoReqResListDTO.woReqResId"/><!-- Key -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:200px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
<%--===========================================================================
작업요청서- 처리사항
author  kim21017
version $Id: reqWorkResList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.req.work.action.ReqWorkResListAction" %>
<%@ page import="dream.req.work.action.ReqWorkResDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 처리사항 -->
<title><bean:message key='TAB.reqWorkResList'/></title>
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
		var wkorId = getValueById(myGrid, rowId,'WKORID');
		var pageId = "reqWorkResDetail";

		if(wkorId != "") pageId = "reqWorkReswoDetail";

		goTabPage(pageId);
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	reqWorkResListForm.elements['reqWorkResListDTO.woReqResId'].value = "";
    	return sortColumn("reqWorkResList", this, reqWorkResListForm, "WOREQRESID", ind, direction);
	});
	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	reqWorkResListForm.elements['reqWorkResListDTO.woReqResId'].value = "";

    findGridList('Search');
}

function findGridList(sheetAction)
{
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (reqWorkResListForm.elements['reqWorkCommonDTO.woReqId'].value == '') return;

	var form = document.reqWorkResListForm;
	form.strutsAction.value = '<%=ReqWorkResListAction.LIST_FIND %>';

	var url = contextPath + "/reqWorkResList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(reqWorkResListForm), "WOREQRESID", "Y");

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
	var form = document.reqWorkResListForm;

	form.elements['reqWorkResListDTO.woReqResId'].value = getValueById(myGrid, selectedId,'WOREQRESID');
	goCommonTabPage(form, <%= ReqWorkResDetailAction.DETAIL_INIT %>, pageId, beforePageId);

	beforePageId = pageId;
}
function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
 	if(selectedId == null) return;

 	reqWorkResListForm.elements['reqWorkResListDTO.woReqResId'].value = getValueById(myGrid, selectedId,'WOREQRESID');
 	reqWorkResListForm.elements['strutsAction'].value = '<%=ReqWorkResDetailAction.DETAIL_INIT%>';
 	openQuickTabPage(FormQueryString(reqWorkResListForm), 'reqWorkResDetail');
}
/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('reqWorkResDetail');
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
  	createValidationCheck(myGrid, "reqWorkResDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	 reqWorkResListForm.elements['reqWorkResListDTO.woReqResId'].value = "";
	goCommonTabPage(reqWorkResListForm, '', pageId);
 }

 /**
  * 수정된 그리드 1건을 다시 조회한다.
  */
 function findGridRow(_woReqResId,_resStatus)
 {
	reqWorkResListForm.elements['reqWorkResListDTO.woReqResId'].value = _woReqResId;
 	findGridList('ReloadRow');
 	reqWorkResListForm.elements['reqWorkResListDTO.woReqResId'].value = "";
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

	reqWorkResListForm.strutsAction.value = '<%=ReqWorkResListAction.LIST_DELETE%>';
	var url = contextPath + "/reqWorkResList.do";

	$.post(url,FormQueryString(reqWorkResListForm)+delArray , function(_data){
		afterDelete();
	});
  }

function afterDelete(){
	goClose('reqWorkResDetail',this);
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }


function goSelect()
{
	lovWo('', '','', '','','','','','');
	//(wkorId, woNo, woDesc, woStatus, woEquip, woEquipLoc, woType, pmType, pWoStatus)
}

function goFix()
{
	var param = "strutsAction=1001";
	var url =  contextPath + "/maWoResultSelect.do";

	openLayerPopup("maWoResultSelect", param);
}

function setAfterSelect(returnArray){

	var woType = returnArray[0];
	var pmType = returnArray[1];
	var _pageId  = returnArray[2];

	var ifm = getIframeContent();

	var _param = "strutsAction=0&maWoResultMstrCommonDTO.selectedWoType="+woType+
				 "&maWoResultMstrCommonDTO.selectedPmType="+pmType+
				 "&maWoResultMstrCommonDTO.compNo="+loginUser.compNo+
				 "&maWoResultMstrCommonDTO.woReqId="+reqWorkResListForm.elements['reqWorkCommonDTO.woReqId'].value +
				 "&maWoResultMstrDetailDTO.equipDesc="+$(ifm.document).find('[name="reqWorkDetailDTO.reqEquipDesc"]').val()+
				 "&maWoResultMstrDetailDTO.equipId="+$(ifm.document).find('[name="reqWorkDetailDTO.reqEquipId"]').val();

	openQuickTabPage(_param, _pageId);

}

/**
 *
 */
function setLovValue(_arrayData, _type)
{
	var wkorId = _arrayData[0];

	reqWorkResListForm.strutsAction.value = '<%=ReqWorkResListAction.LIST_WO_LINK %>';
	reqWorkResListForm.elements['reqWorkCommonDTO.wkorId'].value = wkorId;

	var url = contextPath + "/reqWorkResList.do";

	$.post(url,FormQueryString(reqWorkResListForm), function(_data){
    	goSearch();
    });
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/reqWorkResList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="reqWorkCommonDTO.woReqId"/><!-- Key -->
<html:hidden property="reqWorkCommonDTO.wkorId"/>
<html:hidden property="reqWorkResListDTO.woReqResId"/><!-- Key -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:200px; background-color:white;"></div>
           </div>
 	</div>

</html:form>
</body>
</html>
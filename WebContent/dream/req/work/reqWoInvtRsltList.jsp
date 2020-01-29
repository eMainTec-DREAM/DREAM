<%--===========================================================================
요청접수 (투자요청) - 투자결과 
author  youngjoo38
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.req.work.action.ReqWoInvtRsltListAction" %> 
<%@ page import="dream.invt.list.action.InvtDetailAction" %> 
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html> 
<head>
<!-- 투자결과 -->
<title><bean:message key='TAB.REQINVT'/></title>
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
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		reqWoInvtRsltListForm.elements['maWoReqCommonDTO.invtlistId'].value = "";
        return sortColumn("reqWoInvtRsltList", this, reqWoInvtRsltListForm, "WOREQRESID", ind, direction);
    });
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		var invtlistId = getValueById(myGrid, rowId,'INVTLISTID');
		
		reqWoInvtRsltListForm.elements['invtCommonDTO.invtlistId'].value = invtlistId;

		if(invtlistId != "") goTabPage("invtDetail");
	});
	
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
	myGrid.init();
	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}


/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	reqWoInvtRsltListForm.elements['reqWoInvtRsltListDTO.woReqResId'].value = "";
	reqWoInvtRsltListForm.elements['maWoReqCommonDTO.invtlistId'].value = "";
	
    findGridList('Search');   
}

function findGridList(sheetAction)
{
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (reqWoInvtRsltListForm.elements['maWoReqCommonDTO.woReqId'].value == '') return;
	
	var form = document.reqWoInvtRsltListForm;	
	form.strutsAction.value = '<%=ReqWoInvtRsltListAction.LIST_FIND %>';
	
	var url = contextPath + "/reqWoInvtRsltList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(reqWoInvtRsltListForm), "WOREQRESID", "Y");
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
	var form = document.reqWoInvtRsltListForm;
	form.elements['reqWoInvtRsltListDTO.woReqResId'].value = getValueById(myGrid, selectedId,'WOREQRESID');
	form.elements['maWoReqCommonDTO.woReqResId'].value = getValueById(myGrid, selectedId,'WOREQRESID');
	form.elements['invtDetailDTO.woReqId'].value = form.elements['maWoReqCommonDTO.woReqId'].value;
	
	goCommonTabPage(form, <%= InvtDetailAction.INVT_DETAIL_INIT %>, pageId, beforePageId);

	beforePageId = pageId;
}
function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
 	if(selectedId == null) return;

 	reqWoInvtRsltListForm.elements['reqWoInvtRsltListDTO.woReqResId'].value = getValueById(myGrid, selectedId,'WOREQRESID');
 	reqWoInvtRsltListForm.elements['strutsAction'].value = '<%=InvtDetailAction.INVT_DETAIL_INIT%>';
 	openQuickTabPage(FormQueryString(reqWoInvtRsltListForm), 'reqInvRecWorkResDetail'); 
}
 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	 reqWoInvtRsltListForm.elements['reqWoInvtRsltListDTO.woReqResId'].value = "";
	 reqWoInvtRsltListForm.elements['maWoReqCommonDTO.invtlistId'].value = "";
	 excelServerAction("reqWoInvtRsltList", reqWoInvtRsltListForm );
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
	reqWoInvtRsltListForm.elements['reqWoInvtRsltListDTO.woReqResId'].value = "";
	reqWoInvtRsltListForm.elements['maWoReqCommonDTO.invtlistId'].value = "";
	
	reqWoInvtRsltListForm.elements['invtDetailDTO.woReqId'].value = reqWoInvtRsltListForm.elements['maWoReqCommonDTO.woReqId'].value
	
	goCommonTabPage(reqWoInvtRsltListForm, '', pageId);
 }
 
 /** 
  * 수정된 그리드 1건을 다시 조회한다.
  */
 function findGridRow(_woReqResId,_type)
 {
	reqWoInvtRsltListForm.elements['maWoReqCommonDTO.invtlistId'].value = _woReqResId;
 	findGridList('ReloadRow');
 	reqWoInvtRsltListForm.elements['maWoReqCommonDTO.invtlistId'].value = "";
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

	reqWoInvtRsltListForm.strutsAction.value = '<%=ReqWoInvtRsltListAction.LIST_DELETE%>';
	var url = contextPath + "/reqWoInvtRsltList.do";
	
	XMLHttpPostVal(url, FormQueryString(reqWoInvtRsltListForm)+delArray, 'afterDelete');
	
	/* $.post(url,FormQueryString(reqWoInvtRsltListForm)+delArray , function(_data){
		afterDelete();
	}); */
  }
 
function afterDelete(ajaxXmlDoc){
	goClose(beforePageId, this);
	//goSearch();
	
	// 요청 - 투자결과 (목록)에서 저장한 경우 
    if(typeof searchPage("maWoReqDetail").checkStatus == "function"){
    	searchPage("maWoReqDetail").checkStatus(reqWoInvtRsltListForm.elements['maWoReqCommonDTO.woReqResId'].value);	
 	}
	
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}
   

/**
 * 투자목록 생성버튼
 */
function goInvtcreate()
{
	goCreate();
}

function afterCreate(keyId)
{
	// 생성한 투자를 TAWOREQRES에 넣어준다. 
	// 기존투자연결과 같은 프로세스.
	var _arrayData = [];
	_arrayData.push(keyId);
	
	setLovValue(_arrayData, '');
}

/**
 * 기존투자 연결버튼
 */
function goInvtselect(){
	goClose(beforePageId, this);
	
	var param = "strutsAction=1001";
	var url =  contextPath + "/lovInvtList.do";
	openLayerPopup("lovInvtList", param);
}


function setLovValue(_arrayData, _type)
{
	var invtlistId = _arrayData[0];

	reqWoInvtRsltListForm.strutsAction.value = '<%=ReqWoInvtRsltListAction.LIST_INVT_LINK %>';
	reqWoInvtRsltListForm.elements['maWoReqCommonDTO.invtlistId'].value = invtlistId;
	
	var url = contextPath + "/reqWoInvtRsltList.do";
	
	XMLHttpPostVal(url, FormQueryString(reqWoInvtRsltListForm), 'afterSetLovValue');
	
	findGridRow(invtlistId);
	/* $.post(url,FormQueryString(reqWoInvtRsltListForm), function(_data){
		//goSearch();	
		findGridRow(invtlistId);
	}); */
}

function afterSetLovValue(ajaxXmlDoc){
	if (parent.changStatus)
		parent.changStatus(parseXmlDoc(ajaxXmlDoc, 'DESC'));

	if (parent.parent.findGridRow)
        parent.parent.findGridRow(reqWoInvtRsltListForm.elements['maWoReqCommonDTO.woReqId'].value);
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = reqWoInvtRsltListForm.elements['maWoReqCommonDTO.woReqId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/reqWoInvtRsltList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maWoReqCommonDTO.woReqId"/><!-- Key -->
<html:hidden property="maWoReqCommonDTO.woReqResId"/>
<html:hidden property="maWoReqCommonDTO.wkorId"/>
<html:hidden property="maWoReqCommonDTO.invtlistId"/>
<html:hidden property="invtCommonDTO.invtlistId"/>
<html:hidden property="invtDetailDTO.woReqId"/>
<html:hidden property="reqWoInvtRsltListDTO.woReqResId"/><!-- Key -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:200px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
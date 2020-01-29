<%--===========================================================================
요청접수 (투자요청) - 작업계획 
author  youngjoo38
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.req.work.action.ReqWoPlanRsltListAction" %> 
<%@ page import="dream.req.work.action.MaWoReqResListAction" %> 
<%@ page import="dream.work.list.action.WoPlanDetailAction" %> 
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html> 
<head>
<!-- 작업계획 -->
<title><bean:message key='TAB.REQWOPLAN'/></title>
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
		reqWoPlanRsltListForm.elements['reqWoPlanRsltListDTO.woReqResId'].value = "";
        return sortColumn("reqWoPlanRsltList", this, reqWoPlanRsltListForm, "woReqResId", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
	myGrid.init();
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen(rowId);
	});
	
	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}


/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	reqWoPlanRsltListForm.elements['reqWoPlanRsltListDTO.woReqResId'].value = "";
	reqWoPlanRsltListForm.elements['maWoReqCommonDTO.wkorId'].value = "";
	
    findGridList('Search');   
}

function findGridList(sheetAction)
{
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (reqWoPlanRsltListForm.elements['maWoReqCommonDTO.woReqId'].value == '') return;
	
	var form = document.reqWoPlanRsltListForm;	
	form.strutsAction.value = '<%=ReqWoPlanRsltListAction.LIST_FIND %>';
	
	var url = contextPath + "/reqWoPlanRsltList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(reqWoPlanRsltListForm), "WOREQRESID", "Y");

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
	var form = document.reqWoPlanRsltListForm;
	var woGenType = "WOREQ";
	
	form.elements['woPlanCommonDTO.wkOrId'].value = getValueById(myGrid, selectedId,'WKORID');
	form.elements['woPlanDetailDTO.woGenType'].value = woGenType;
	
	goCommonTabPage(form, <%= WoPlanDetailAction.WO_PLAN_DETAIL_INIT %>, pageId, beforePageId);

	beforePageId = pageId;
}

function goOpen(rowId)
{
	var wkorId = getValueById(myGrid, rowId,'WKORID');
	var woReqResId = getValueById(myGrid, rowId,'WOREQRESID');
	
	reqWoPlanRsltListForm.elements['woPlanCommonDTO.woReqResId'].value = woReqResId;
	
	goTabPage("woPlanDetail");
}

function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
 	if(selectedId == null) return;

 	reqWoPlanRsltListForm.elements['reqWoPlanRsltListDTO.woReqResId'].value = getValueById(myGrid, selectedId,'WOREQRESID');
}
 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	  reqWoPlanRsltListForm.elements['reqWoPlanRsltListDTO.woReqResId'].value = "";
	  reqWoPlanRsltListForm.elements['maWoReqCommonDTO.wkorId'].value = "";
	  excelServerAction("reqWoPlanRsltList", reqWoPlanRsltListForm );
  } 
  /**
   * 생성
   */
 function goCreate()
 {
	reqWoPlanRsltListForm.elements['woPlanDetailDTO.equipId'].value = reqWoPlanRsltListForm.elements['maWoReqDetailDTO.reqEquipId'].value;
	reqWoPlanRsltListForm.elements['woPlanDetailDTO.equipDesc'].value = reqWoPlanRsltListForm.elements['maWoReqDetailDTO.reqEquipDesc'].value;
	reqWoPlanRsltListForm.elements['woPlanDetailDTO.eqAsmbId'].value = reqWoPlanRsltListForm.elements['maWoReqDetailDTO.reqEqAsmbId'].value;
	reqWoPlanRsltListForm.elements['woPlanDetailDTO.eqAsmbDesc'].value = reqWoPlanRsltListForm.elements['maWoReqDetailDTO.reqEqAsmbDesc'].value;
	
	createValidationCheck(myGrid, "woPlanDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	reqWoPlanRsltListForm.elements['reqWoPlanRsltListDTO.woReqResId'].value = "";
	reqWoPlanRsltListForm.elements['maWoReqCommonDTO.wkorId'].value = "";
	goCommonTabPage(reqWoPlanRsltListForm, '', pageId);
 }
 
 /** 
  * 수정된 그리드 1건을 다시 조회한다.
  */
 function findGridRow(_wkorId,_type)
 {
	reqWoPlanRsltListForm.elements['maWoReqCommonDTO.wkorId'].value = _wkorId;
 	findGridList('ReloadRow');
 	reqWoPlanRsltListForm.elements['maWoReqCommonDTO.wkorId'].value = "";
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

	reqWoPlanRsltListForm.strutsAction.value = '<%=ReqWoPlanRsltListAction.LIST_DELETE%>';
	var url = contextPath + "/reqWoPlanRsltList.do";
	
	$.post(url,FormQueryString(reqWoPlanRsltListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('reqInvRecWorkResDetail',this);
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
   

/**
 * 신규계획 생성버튼
 */
function goWoplancreate()
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
 * 기존계획 연결버튼
 */
function goWoplanselect(){
	if(""!=beforePageId)
		goClose(beforePageId, this);
	
	var param = "strutsAction=1001";
	var url =  contextPath + "/lovWoPlanAcList.do";
	openLayerPopup("lovWoPlanAcList", param);
}


function setLovValue(_arrayData, _type)
{
	var wkorId = _arrayData[0];
	reqWoPlanRsltListForm.strutsAction.value = '<%=MaWoReqResListAction.LIST_WOPLAN_LINK %>';
	reqWoPlanRsltListForm.elements['maWoReqCommonDTO.wkorId'].value = wkorId;
	
	var url = contextPath + "/maWoReqResList.do";
	
	$.post(url,FormQueryString(reqWoPlanRsltListForm), function(_data){
		//goSearch();	
		findGridRow(wkorId);
		afterSetLovValue();
	});
}


function afterSetLovValue(){
   	if (parent.parent.findGridRow)
        parent.parent.findGridRow(reqWoPlanRsltListForm.elements['maWoReqCommonDTO.woReqId'].value);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/reqWoPlanRsltList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maWoReqCommonDTO.woReqId"/><!-- Key -->
<html:hidden property="maWoReqCommonDTO.wkorId"/>
<html:hidden property="woPlanCommonDTO.wkOrId"/>
<html:hidden property="woPlanCommonDTO.woReqResId"/>
<html:hidden property="woPlanDetailDTO.woGenType"/>
<html:hidden property="reqWoPlanRsltListDTO.woReqResId"/><!-- Key -->

<html:hidden property="maWoReqDetailDTO.reqEquipId" />
<html:hidden property="maWoReqDetailDTO.reqEquipDesc" />
<html:hidden property="woPlanDetailDTO.equipId"/>
<html:hidden property="woPlanDetailDTO.equipDesc"/>
<html:hidden property="maWoReqDetailDTO.reqEqAsmbId" />
<html:hidden property="maWoReqDetailDTO.reqEqAsmbDesc" />
<html:hidden property="woPlanDetailDTO.eqAsmbId"/>
<html:hidden property="woPlanDetailDTO.eqAsmbDesc"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:200px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
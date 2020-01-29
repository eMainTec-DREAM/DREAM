<%--===========================================================================
요청접수 (투자요청) - 작업결과 
author  youngjoo38
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.req.work.action.ReqWoRsltListAction" %> 
<%@ page import="dream.work.list.action.MaWoResultMstrDetailAction" %> 
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html> 
<head>
<!-- 작업결과 -->
<title><bean:message key='TAB.REQWO'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var beforePageId= '';
var woAc;
function loadPage() 
{
	setForUpdate();
	
	initGrid();
	
	woAc = new autoC({"maWoReqCommonDTO.wkorDesc":"description"});
	woAc.setTable("TAWORKORDER");
	woAc.setAcResultMap({
        "maWoReqCommonDTO.wkorId":"wkorId"
    });
	woAc.init();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	reqWoRsltListForm.elements['reqWoRsltListDTO.woReqResId'].value = "";
    	return sortColumn("reqWoRsltList", this, reqWoRsltListForm, "WOREQRESID", ind, direction);
	});
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
	reqWoRsltListForm.elements['reqWoRsltListDTO.woReqResId'].value = "";
	reqWoRsltListForm.elements['maWoReqCommonDTO.wkorId'].value = "";
	
    findGridList('Search');   
}

function findGridList(sheetAction)
{
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (reqWoRsltListForm.elements['maWoReqCommonDTO.woReqId'].value == '') return;
	
	var form = document.reqWoRsltListForm;	
	form.strutsAction.value = '<%=ReqWoRsltListAction.LIST_FIND %>';
	
	var url = contextPath + "/reqWoRsltList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(reqWoRsltListForm), "WOREQRESID", "Y");

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
	var form = document.reqWoRsltListForm;
	
	form.elements['maWoResultMstrCommonDTO.wkOrId'].value = getValueById(myGrid, selectedId,'WKORID');
	goCommonTabPage(form, <%= MaWoResultMstrDetailAction.WO_RESULT_DETAIL_INIT %>, pageId, beforePageId);

	beforePageId = pageId;
}

function goOpen(rowId)
{
	var wkorId = getValueById(myGrid, rowId,'WKORID');
	var woType = getValueById(myGrid, rowId,'WOTYPE');
	var pmType = getValueById(myGrid, rowId,'PMTYPE');
	var param = getValueById(myGrid, rowId,'PARAM');
	var woReqResId = getValueById(myGrid, rowId,'WOREQRESID');
	
	if(typeof woType != "undefined" && wkorId != "")
	{
		reqWoRsltListForm.elements['maWoResultMstrCommonDTO.selectedWoType'].value = woType;
		reqWoRsltListForm.elements['maWoResultMstrCommonDTO.selectedPmType'].value = pmType;
		reqWoRsltListForm.elements['maWoResultMstrCommonDTO.woReqResId'].value = woReqResId;
		goTabPage(param);
	}
}

function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
 	if(selectedId == null) return;

 	reqWoRsltListForm.elements['reqWoRsltListDTO.woReqResId'].value = getValueById(myGrid, selectedId,'WOREQRESID');
}
 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	  reqWoRsltListForm.elements['maWoReqCommonDTO.wkorId'].value = "";
	  excelServerAction("reqWoRsltList", reqWoRsltListForm );  
  } 
  /**
   * 생성
   */
 function goCreate()
 {
  	if(""!=beforePageId)
		goClose(beforePageId, this);
  	
  	openSelectType();
	reqWoRsltListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value = "";
	reqWoRsltListForm.elements['maWoResultMstrDetailDTO.wkOrDesc'].value = reqWoRsltListForm.elements['maWoReqDetailDTO.reqDesc'].value;
	reqWoRsltListForm.elements['maWoResultMstrDetailDTO.equipId'].value = reqWoRsltListForm.elements['maWoReqDetailDTO.reqEquipId'].value;
	reqWoRsltListForm.elements['maWoResultMstrDetailDTO.equipDesc'].value = reqWoRsltListForm.elements['maWoReqDetailDTO.reqEquipDesc'].value;
	reqWoRsltListForm.elements['maWoResultMstrDetailDTO.equipNo'].value = reqWoRsltListForm.elements['maWoReqDetailDTO.reqEquipNo'].value;
	reqWoRsltListForm.elements['maWoResultMstrDetailDTO.eqLocId'].value = reqWoRsltListForm.elements['maWoReqDetailDTO.reqEqLocId'].value;
	reqWoRsltListForm.elements['maWoResultMstrDetailDTO.eqLocDesc'].value = reqWoRsltListForm.elements['maWoReqDetailDTO.reqEqLocDesc'].value;
	reqWoRsltListForm.elements['maWoResultMstrDetailDTO.wkCtrId'].value = reqWoRsltListForm.elements['maWoReqDetailDTO.recWkCtrId'].value;
	reqWoRsltListForm.elements['maWoResultMstrDetailDTO.wkCtrDesc'].value = reqWoRsltListForm.elements['maWoReqDetailDTO.recWkCtrDesc'].value;
	reqWoRsltListForm.elements['maWoResultMstrDetailDTO.eqAsmbId'].value = reqWoRsltListForm.elements['maWoReqDetailDTO.reqEqAsmbId'].value;
	reqWoRsltListForm.elements['maWoResultMstrDetailDTO.eqAsmbDesc'].value = reqWoRsltListForm.elements['maWoReqDetailDTO.reqEqAsmbDesc'].value;
 }

//  function goCreateAction(pageId)
//  {
// 	reqWoRsltListForm.elements['reqWoRsltListDTO.woReqResId'].value = "";
// 	reqWoRsltListForm.elements['maWoReqCommonDTO.wkorId'].value = "";
// 	goCommonTabPage(reqWoRsltListForm, '', pageId);
//  }
 

 function setAfterSelect(returnArray)
 {
	var woType = returnArray[0];
	var pmType = returnArray[1];
	var _pageId  = returnArray[2];
	var woGenType = 'WOREQ';
	
	reqWoRsltListForm.elements['maWoResultMstrCommonDTO.compNo'].value = loginUser.compNo;
	reqWoRsltListForm.elements['maWoResultMstrCommonDTO.selectedWoType'].value = woType;
	reqWoRsltListForm.elements['maWoResultMstrCommonDTO.selectedPmType'].value = pmType;
	reqWoRsltListForm.elements['maWoResultMstrDetailDTO.woGenType'].value = woGenType;
	reqWoRsltListForm.elements['maWoResultMstrCommonDTO.woReqId'].value = reqWoRsltListForm.elements['maWoReqCommonDTO.woReqId'].value;
	
	// 고장작업 생성 시 요청접수(상세) 고장발생일자(일자,시간)를 설비고장시작(일자,시간)에 셋팅
	if("BM" == reqWoRsltListForm.elements['maWoResultMstrCommonDTO.selectedWoType'].value && "" != reqWoRsltListForm.elements['maWoReqDetailDTO.eqDnDate'].value)
	{
		reqWoRsltListForm.elements['maWoResultFailDetailDTO.eqDnStartDate'].value = reqWoRsltListForm.elements['maWoReqDetailDTO.eqDnDate'].value;
		reqWoRsltListForm.elements['maWoResultFailDetailDTO.eqDnStartTime'].value = reqWoRsltListForm.elements['maWoReqDetailDTO.eqDnTime'].value;
	}
	
	beforePageId = _pageId;
	goCommonTabPage(reqWoRsltListForm, '', _pageId);
}
 
 /**
  * 작업종류& 작업형태 선택창 열기
  */
 function openSelectType(){
 	width  = 850;
 	height = 540;
 	sleft = (screen.width - width) / 2;
 	stop = (screen.height - height) / 2;
 	features = "left=" + sleft + ",top=" + stop;
 	
 	var param = "strutsAction=1001";
 	var url =  contextPath + "/maWoResultSelect.do";
 	
 	openLayerPopup("maWoResultSelect", param);
 }

 
 /** 
  * 수정된 그리드 1건을 다시 조회한다.
  */
 function findGridRow(_wkorId,_type,_resStatus)
 {
 	reqWoRsltListForm.elements['maWoReqCommonDTO.wkorId'].value = _wkorId;
 	findGridList('ReloadRow');
 	reqWoRsltListForm.elements['maWoReqCommonDTO.wkorId'].value = "";
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

	reqWoRsltListForm.strutsAction.value = '<%=ReqWoRsltListAction.LIST_DELETE%>';
	var url = contextPath + "/reqWoRsltList.do";
	
	XMLHttpPostVal(url, FormQueryString(reqWoRsltListForm)+delArray, 'afterDelete');
  }
 
function afterDelete(ajaxXmlDoc){
	goClose('reqInvRecWorkResDetail',this);
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   	
   	if(typeof searchPage("maWoReqDetail").checkStatus == "function"){
   		searchPage("maWoReqDetail").checkStatus(reqWoRsltListForm.elements['reqWoRsltListDTO.woReqResId'].value);	
	}
}
   

/**
 * 신규작업 생성버튼
 */
function goWocreate()
{
	goCreate();
}

/**
 * 기존작업 연결버튼
 */
function goWoselect(){
	woAc.openLov();
}

function setAcLovValue(rtnArr, code)
{
	if(code == 'maWoReqCommonDTO.wkorDesc')
	{
		reqWoRsltListForm.strutsAction.value = '<%=ReqWoRsltListAction.LIST_WO_LINK %>';
		
		reqWoRsltListForm.elements['maWoReqCommonDTO.wkorDesc'].value = JSON.stringify(rtnArr);
		
		goSaveAll();
	}
}

function goSave(){
	var url = contextPath + "/reqWoRsltList.do";
	
	$.post(url,FormQueryString(reqWoRsltListForm), function(_data){
		afterSave(_data);
	});
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    getTopPage().afterSaveAll(currentPageId);
    
    if (parent.changStatus)
		parent.changStatus(parseXmlDoc(ajaxXmlDoc, 'DESC'));
    
    if (parent.parent.findGridRow)
        parent.parent.findGridRow(reqWoRsltListForm.elements['maWoReqCommonDTO.woReqId'].value);
    
    findGridRow(reqWoRsltListForm.elements['maWoReqCommonDTO.wkorId'].value);
}

function afterSetLovValue(ajaxXmlDoc){
	if (parent.changStatus)
		parent.changStatus(parseXmlDoc(ajaxXmlDoc, 'DESC'));

	if (parent.parent.findGridRow)
        parent.parent.findGridRow(reqWoRsltListForm.elements['maWoReqCommonDTO.woReqId'].value);
}


/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = reqWoRsltListForm.elements['maWoReqCommonDTO.woReqId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/reqWoRsltList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maWoReqCommonDTO.woReqId"/><!-- Key -->
<html:hidden property="maWoReqCommonDTO.wkorId"/>
<html:hidden property="maWoReqCommonDTO.wkorDesc"/>
<html:hidden property="maWoResultMstrCommonDTO.compNo"/>
<html:hidden property="maWoResultMstrCommonDTO.wkOrId"/>
<html:hidden property="maWoResultMstrCommonDTO.woReqId"/>
<html:hidden property="maWoResultMstrCommonDTO.selectedWoType"/>
<html:hidden property="maWoResultMstrCommonDTO.selectedPmType"/>
<html:hidden property="maWoResultMstrCommonDTO.woReqResId"/>
<html:hidden property="maWoResultMstrDetailDTO.woGenType"/>
<html:hidden property="reqWoRsltListDTO.woReqResId"/><!-- Key -->

<html:hidden property="maWoReqDetailDTO.reqEquipId" />
<html:hidden property="maWoReqDetailDTO.reqEquipNo" />
<html:hidden property="maWoReqDetailDTO.reqEquipDesc" />
<html:hidden property="maWoReqDetailDTO.reqEqLocId" />
<html:hidden property="maWoReqDetailDTO.reqEqLocDesc" />
<html:hidden property="maWoReqDetailDTO.recWkCtrId" />
<html:hidden property="maWoReqDetailDTO.recWkCtrDesc" />
<html:hidden property="maWoReqDetailDTO.reqDesc"/>
<html:hidden property="maWoReqDetailDTO.reqEqAsmbId" />
<html:hidden property="maWoReqDetailDTO.reqEqAsmbDesc" />
<html:hidden property="maWoResultMstrDetailDTO.equipId" />
<html:hidden property="maWoResultMstrDetailDTO.equipNo" />
<html:hidden property="maWoResultMstrDetailDTO.equipDesc" />
<html:hidden property="maWoResultMstrDetailDTO.eqLocId" />
<html:hidden property="maWoResultMstrDetailDTO.eqLocDesc" />
<html:hidden property="maWoResultMstrDetailDTO.wkCtrId" />
<html:hidden property="maWoResultMstrDetailDTO.wkCtrDesc" />
<html:hidden property="maWoResultMstrDetailDTO.wkOrDesc" />
<html:hidden property="maWoResultMstrDetailDTO.eqAsmbId" />
<html:hidden property="maWoResultMstrDetailDTO.eqAsmbDesc" />
<html:hidden property="maWoReqResDetailDTO.resStatusId" />

<html:hidden property="maWoReqDetailDTO.eqDnDate" />
<html:hidden property="maWoReqDetailDTO.eqDnTime" />
<html:hidden property="maWoResultFailDetailDTO.eqDnStartDate" />
<html:hidden property="maWoResultFailDetailDTO.eqDnStartTime" />
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:200px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
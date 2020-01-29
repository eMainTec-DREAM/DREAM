<%--===========================================================================
 이상점검조치 - 작업계획 목록
author  syyang
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.mabdpoint.action.MaBdPointWoPlanListAction" %> 
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
var myGrid;
var woplanAc;

function loadPage() 
{
	setForUpdate();
	
	// 기존작업계획
	woplanAc = new autoC({"maBdPointWoPlanListDTO.multiDesc":"woDesc"});
	woplanAc.setTable("TAWOPLAN");
	woplanAc.setAcResultMap({
        "maBdPointWoPlanListDTO.multiKey":"wkorId"
    });
	woplanAc.setAcConditionMap({
    	"comp_no" : loginUser.compNo
    });
	woplanAc.setMultiSelect(true);
	woplanAc.init();
	
	initGrid();
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
	myGrid.init();
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		maBdPointWoPlanListForm.elements['maBdPointWoPlanListDTO.woNgPointResId'].value = "";
        return sortColumn("maBdPointWoPlanList", this, maBdPointWoPlanListForm, "woNgPointResId", ind, direction);
    });
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
	maBdPointWoPlanListForm.elements['maBdPointWoPlanListDTO.woNgPointResId'].value = "";
	
    findGridList('Search');   
}

function findGridList(sheetAction)
{
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (maBdPointWoPlanListForm.elements['maBdPointCommonDTO.woNgPointId'].value == '') return;
	
	var form = document.maBdPointWoPlanListForm;	
	form.strutsAction.value = '<%=MaBdPointWoPlanListAction.LIST_FIND %>';
	
	var url = contextPath + "/maBdPointWoPlanList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maBdPointWoPlanListForm), "WONGPOINTRESID", "Y");

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
	var form = document.maBdPointWoPlanListForm;
	 
	form.elements['woPlanCommonDTO.wkOrId'].value = getValueById(myGrid, selectedId,'WKORID');
	goCommonTabPage(form, <%= WoPlanDetailAction.WO_PLAN_DETAIL_INIT %>, pageId, beforePageId);

	beforePageId = pageId;
}

function goOpen(rowId)
{
	var wkorId = getValueById(myGrid, rowId,'WKORID');
	
	goTabPage("woPlanDetail");
}
function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
 	if(selectedId == null) return;

 	maBdPointWoPlanListForm.elements['woPlanCommonDTO.wkOrId'].value = getValueById(myGrid, selectedId, 'WKORID');
    
}

 
 /** 
  * 수정된 그리드 1건을 다시 조회한다.
  */
 function findGridRow(_woNgPointResId,_type)
 {
	maBdPointWoPlanListForm.elements['maBdPointWoPlanListDTO.woNgPointResId'].value = _woNgPointResId;
 	findGridList('ReloadRow');
 	maBdPointWoPlanListForm.elements['maBdPointWoPlanListDTO.woNgPointResId'].value = "";
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'WONGPOINTRESID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maBdPointWoPlanListForm.strutsAction.value = '<%=MaBdPointWoPlanListAction.LIST_DELETE%>';
	var url = contextPath + "/maBdPointWoPlanList.do";
	
	$.post(url,FormQueryString(maBdPointWoPlanListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete()
{
	goClose(beforePageId, this);
	//goClose('maBdPointWoPlanList',this);
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}
   
  /**
   * 생성
   */
 function goCreate()
 {
	 maBdPointWoPlanListForm.elements['woPlanDetailDTO.equipId'].value = maBdPointWoPlanListForm.elements['maBdPointDetailDTO.equipId'].value;
	 maBdPointWoPlanListForm.elements['woPlanDetailDTO.equipDesc'].value = maBdPointWoPlanListForm.elements['maBdPointDetailDTO.equipDesc'].value;
	 
 	createValidationCheck(myGrid, "woPlanDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	 goCommonTabPage(maBdPointWoPlanListForm, '', pageId);
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
	// 생성한 작업계획을 TAWONGPOINTRES에 넣어준다. 
	// 기존계획선택과 같은 프로세스.
	var _arrayData = [];
	_arrayData.push(keyId);
	
	setLovValue(_arrayData, '');
}

function goSave(){
	var url = contextPath + "/maBdPointWoPlanList.do";
	
    $.post(url,FormQueryString(maBdPointWoPlanListForm), function(_data){
    	afterSave(_data);
    });
}
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    getTopPage().afterSaveAll(currentPageId);
    
    maBdPointWoPlanListForm.elements['maBdPointWoPlanListDTO.woNgPointResId'].value = "";
    goSearch();
}

// function setLovValue(_arrayData, _type)
// {
// 	var wkorId = _arrayData;

<%-- 	maBdPointWoPlanListForm.strutsAction.value = '<%=MaBdPointWoPlanListAction.LIST_WOPLAN_INPUT %>'; --%>
// 	maBdPointWoPlanListForm.elements['maBdPointWoPlanListDTO.wkorId'].value = wkorId;
	
// 	var url = contextPath + "/maBdPointWoPlanList.do";
	
// 	$.post(url,FormQueryString(maBdPointWoPlanListForm), function(_data){
// 		goSearch();	
// 	});
	
// }

/**
 * 기존계획 연결버튼
 */
function goWoplanselect(){
	woplanAc.openLov();
}

function setAcLovValue(rtnArr, code)
{
	maBdPointWoPlanListForm.strutsAction.value = '<%=MaBdPointWoPlanListAction.LIST_WOPLAN_LINK %>';

	goSaveAll();
}

function goSave(){
	var url = contextPath + "/maBdPointWoPlanList.do";
	
    $.post(url,FormQueryString(maBdPointWoPlanListForm), function(_data){
    	afterSave(_data);
    });
}
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    getTopPage().afterSaveAll(currentPageId);
    
    maBdPointWoPlanListForm.elements['maBdPointWoPlanListDTO.woNgPointResId'].value = "";
	
    if(parent.parent.findGridRow){
    	parent.parent.findGridRow(maBdPointWoPlanListForm.elements['maBdPointCommonDTO.woNgPointId'].value);
    }
    if(parent.parent.goTabPage){
    	parent.parent.goTabPage(parent.currentPageId);
    }
}


/**
 * 엑셀 다운.
 */
 function goExcel()
 {
 	//excelAction(myGrid);
	maBdPointWoPlanListForm.elements['maBdPointWoPlanListDTO.woNgPointResId'].value = "";
	excelServerAction(currentPageId, maBdPointWoPlanListForm);
 } 


//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
	<html:form action="/maBdPointWoPlanList.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="currentPageId"/>
	<html:hidden property="maBdPointCommonDTO.woNgPointId" />	<!-- Key -->
	<html:hidden property="maBdPointDetailDTO.equipId" />
	<html:hidden property="maBdPointDetailDTO.equipDesc" />
	<html:hidden property="maBdPointWoPlanListDTO.woNgPointResId"/>
	<html:hidden property="maBdPointWoPlanListDTO.woReqId"/>
	<html:hidden property="maBdPointWoPlanListDTO.wkorId"/>
	<html:hidden property="maBdPointWoPlanListDTO.woDesc"/>
	<html:hidden property="maBdPointWoPlanListDTO.multiKey"/><!-- MultiSelect Key -->
	<html:hidden property="maBdPointWoPlanListDTO.multiDesc"/><!-- MultiSelect Desc -->

	<html:hidden property="woPlanCommonDTO.wkOrId"/>
	<html:hidden property="woPlanDetailDTO.equipId"/>
	<html:hidden property="woPlanDetailDTO.equipDesc"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:200px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
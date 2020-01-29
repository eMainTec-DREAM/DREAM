<%--===========================================================================
부품실사 item  목록
author  kim21017
version $Id: partAdjStkTakeItemList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="common.tree.action.TreeAction" %>
<%@ page import="dream.part.adj.stktake.action.PartAdjStkTakeItemListAction" %>
<%@ page import="dream.part.adj.stktake.action.PartAdjStkTakeItemDetailAction" %>
<%@ page import="dream.part.adj.stktake.action.PartAdjStkTakeDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.MwareConfig"%>
<%
	String partGrade = MwareConfig.getPartGrade();
%>
<html>
<head>
<title><bean:message key='LABEL.buyReq'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var multiPartAc;
function loadPage() 
{
/* 	if(partAdjStkTakeItemListForm.elements['partAdjStkTakeCommonDTO.ptStkTakeStatus'].value == "C"){
		setDisableAll();
	}else{
		setEnableAll();
	} */
	
	setForUpdate();
	
	initGrid();
	
	multiPartAc = new autoC({"partAdjStkTakeItemDetailDTO.multiDesc":"full_desc"});
    multiPartAc.setTable("TAPARTS");
    multiPartAc.setAcConditionMap({
	   "comp_no":loginUser.compNo
	   ,"is_stock_control":"Y"
	   });
    multiPartAc.setAcResultMap({
	    "partAdjStkTakeItemDetailDTO.multiKey":"part_id"
	});
    multiPartAc.setMultiSelect(true);
    multiPartAc.init();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("partAdjStkTakeItemDetail");
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	partAdjStkTakeItemListForm.elements['partAdjStkTakeListDTO.ptStkTakeItemId'].value = "";
    	return sortColumn("partAdjStkTakeItemList", this, partAdjStkTakeItemListForm, "PTSTKTAKEITEMID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();


	
	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	partAdjStkTakeItemListForm.elements['partAdjStkTakeListDTO.ptStkTakeItemId'].value = "";     // 검색시 Tab 이동Key Clear
	
	findGridList('Search');
}

/**
 * gird find
 */
function findGridList(sheetAction)
{
	if (partAdjStkTakeItemListForm.elements['partAdjStkTakeCommonDTO.ptStkTakeListId'].value == '') return;
	
	var form = document.partAdjStkTakeItemListForm;	
	form.strutsAction.value = '<%=PartAdjStkTakeItemListAction.BUY_ITEM_LIST_FIND%>'; 

	var url = contextPath + "/partAdjStkTakeItemList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(partAdjStkTakeItemListForm), "PTSTKTAKEITEMID", "Y");
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
	var form = document.partAdjStkTakeItemListForm;
	form.elements['partAdjStkTakeListDTO.ptStkTakeItemId'].value = getValueById(myGrid, selectedId,'PTSTKTAKEITEMID');
    
	goCommonTabPage(form, <%= PartAdjStkTakeItemDetailAction.BUY_ITEM_DETAIL_INIT %>, "partAdjStkTakeItemDetail");
	
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_ptPrItemId)
{
	partAdjStkTakeItemListForm.elements['partAdjStkTakeListDTO.ptStkTakeItemId'].value = _ptPrItemId;
	findGridList('ReloadRow');
	partAdjStkTakeItemListForm.elements['partAdjStkTakeListDTO.ptStkTakeItemId'].value = "";
}

/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('partAdjStkTakeItemDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    partAdjStkTakeItemListForm.elements['partAdjStkTakeListDTO.ptStkTakeItemId'].value =  getValueById(myGrid, selectedId,'PTSTKTAKEITEMID');  
    partAdjStkTakeItemListForm.elements['strutsAction'].value = '<%=PartAdjStkTakeItemDetailAction.BUY_ITEM_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(partAdjStkTakeItemListForm), 'partAdjStkTakeItemDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "partAdjStkTakeItemDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	partAdjStkTakeItemListForm.elements['partAdjStkTakeListDTO.ptStkTakeItemId'].value = "";
	goCommonTabPage(partAdjStkTakeItemListForm, '', pageId);
}

/*
 * 엑셀 업로드
 */
function goExluploadLink()
{
	var actionUrl = contextPath + "/partAdjStkTakeItemList.do";
    var param =  "&strutsAction=" + '<%= PartAdjStkTakeItemListAction.GET_DATA %>'
              +  "&" + FormQueryString(partAdjStkTakeItemListForm);
    XMLHttpPostVal(actionUrl, param, 'afterGoExlupload');
}

var dataArr;
function afterGoExlupload(ajaxXmlDoc)
{
	dataArr = '0';
	var data = parseXmlDoc(ajaxXmlDoc, 'DESC');
	var uploadTypeId = "";
	var uploadType = "";
	var tableName = "";
	
	data = data.toString();

	if(data != '0')
    {
		dataArr = data.split(',');
		
		uploadTypeId = dataArr[0];
		uploadType = dataArr[1];
		tableName = dataArr[2];
		
    }
		goExlupload(uploadTypeId, uploadType, tableName);
}

/**
 * Excel Export
 */
function goExcel()
{
    partAdjStkTakeItemListForm.elements['partAdjStkTakeListDTO.ptStkTakeItemId'].value = "";
	excelServerAction("partAdjStkTakeItemList", partAdjStkTakeItemListForm );
}

/**
 * 삭제
 */
 function goDelete()
 {
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'PTSTKTAKEITEMID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	partAdjStkTakeItemListForm.strutsAction.value = '<%=PartAdjStkTakeItemListAction.BUY_ITEM_LIST_DELETE%>';
	var url = contextPath + "/partAdjStkTakeItemList.do";
	
    $.post(url,FormQueryString(partAdjStkTakeItemListForm)+delArray , function(_data){
    	afterDelete();
    });
 }

function afterDelete(){
	goClose('partAdjStkTakeItemDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

function goSave(){
	var url = contextPath + "/partAdjStkTakeItemList.do";
	
    XMLHttpPost(url, FormQueryString(partAdjStkTakeItemListForm), 'afterSave');
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    getTopPage().afterSaveAll(currentPageId);
    
    goSearch();
}

/**
 * 일괄등록
 */
function goRegbatch()
{
	multiPartAc.openLov();
}

function setAcLovValue(rtnArr, code)
{
	if(code == 'partAdjStkTakeItemDetailDTO.multiDesc')
	{
		partAdjStkTakeItemListForm.strutsAction.value = '<%=PartAdjStkTakeItemListAction.BUY_ITEM_LIST_INPUT%>';
		
		partAdjStkTakeItemListForm.elements['partAdjStkTakeItemDetailDTO.partGrade'].value = '<%=partGrade%>';
		partAdjStkTakeItemListForm.elements['partAdjStkTakeItemDetailDTO.realQty'].value = 1;
		
		partAdjStkTakeItemListForm.elements['partAdjStkTakeItemDetailDTO.multiDesc'].value = JSON.stringify(rtnArr);
		
		goSaveAll();
	}
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = partAdjStkTakeItemListForm.elements['partAdjStkTakeListDTO.ptStkTakeItemId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/partAdjStkTakeItemList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="partAdjStkTakeCommonDTO.ptStkTakeListId"/><!-- Key -->
<html:hidden property="partAdjStkTakeListDTO.ptStkTakeItemId"/><!-- Detail Key -->
<html:hidden property="partAdjStkTakeCommonDTO.ptStkTakeStatus"/>
<html:hidden property="partAdjStkTakeItemDetailDTO.partGrade"/>
<html:hidden property="partAdjStkTakeItemDetailDTO.realQty"/>
<html:hidden property="partAdjStkTakeItemDetailDTO.multiKey"/>
<html:hidden property="partAdjStkTakeItemDetailDTO.multiDesc"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" >
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
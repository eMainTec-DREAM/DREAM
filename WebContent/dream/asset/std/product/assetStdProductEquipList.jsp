<%--===========================================================================
생산설비
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.std.product.action.AssetStdProductEquipListAction" %>
<%@ page import="dream.asset.std.product.action.AssetStdProductEquipDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 생산설비 -->
<title><bean:message key='TAB.assetStdProductEquipList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

/** 자동완성 변수 */
var equipAc;

function loadPage() 
{
	setForUpdate();
	
    initGrid();
    
    // 설비
    equipAc = new autoC({"assetStdProductEquipListDTO.equipDesc":"description"});
    equipAc.setTable("TAEQUIPMENT");
    equipAc.setKeyName("assetStdProductEquipListDTO.equipId");
    equipAc.setAcResultMap({
        "assetStdProductEquipListDTO.equipId":"equip_id"
    });
    equipAc.setMultiSelect(true);
    equipAc.init();
    
    
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		assetStdProductEquipListForm.elements['assetStdProductEquipListDTO.assetStdProductEquipId'].value = "";
    	return sortColumn("assetStdProductEquipList", this, assetStdProductEquipListForm, "PRDEQUIPID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (assetStdProductEquipListForm.elements['assetStdProductCommonDTO.productId'].value == '') return;
    var url = contextPath + "/assetStdProductEquipList.do";
    assetStdProductEquipListForm.elements['strutsAction'].value = '<%=AssetStdProductEquipListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(assetStdProductEquipListForm), "PRDEQUIPID","Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_assetStdProductEquipId)
{
	assetStdProductEquipListForm.elements['assetStdProductEquipListDTO.assetStdProductEquipId'].value = _assetStdProductEquipId;
	findGridList('ReloadRow');
	assetStdProductEquipListForm.elements['assetStdProductEquipListDTO.assetStdProductEquipId'].value = "";
}

function goSearch()
{
	findGridList('Search');
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
	assetStdProductEquipListForm.elements['assetStdProductEquipListDTO.assetStdProductEquipId'].value =  getValueById(myGrid, selectedId,'PRDEQUIPID');  
	goCommonTabPage(assetStdProductEquipListForm, <%= AssetStdProductEquipDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('assetStdProductEquipDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    assetStdProductEquipListForm.elements['assetStdProductEquipListDTO.assetStdProductEquipId'].value =  getValueById(myGrid, selectedId,'PRDEQUIPID');  
    assetStdProductEquipListForm.elements['strutsAction'].value = '<%=AssetStdProductEquipDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(assetStdProductEquipListForm), 'assetStdProductEquipDetail'); 
} 
/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "assetStdProductEquipDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	assetStdProductEquipListForm.elements['assetStdProductEquipListDTO.assetStdProductEquipId'].value = "";
    goCommonTabPage(assetStdProductEquipListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'PRDEQUIPID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    assetStdProductEquipListForm.strutsAction.value = '<%=AssetStdProductEquipListAction.LIST_DELETE%>';
    var url = contextPath + "/assetStdProductEquipList.do";
    
    $.post(url,FormQueryString(assetStdProductEquipListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('assetStdProductEquipDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	assetStdProductEquipListForm.elements['assetStdProductEquipListDTO.assetStdProductEquipId'].value = "";
	excelServerAction("assetStdProductEquipList", assetStdProductEquipListForm );  
}

function goSave(){
	var url = contextPath + "/assetStdProductEquipList.do";
	
    $.post(url,FormQueryString(assetStdProductEquipListForm), function(_data){
    	afterSave(_data);
    });
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    getTopPage().afterSaveAll(currentPageId);
    
    assetStdProductEquipListForm.elements['assetStdProductEquipListDTO.equipId'].value = "";
	assetStdProductEquipListForm.elements['assetStdProductEquipListDTO.equipDesc'].value = "";
    goSearch();
}

/**
 * 일괄등록
 */
function goRegbatch()
{
	goClose('assetStdProductEquipDetail', this);
	equipAc.openLov();
}

function setAcLovValue(rtnArr, code)
{
	assetStdProductEquipListForm.strutsAction.value = '<%=AssetStdProductEquipListAction.EQ_LIST_INPUT%>';
	
	goSaveAll();
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assetStdProductEquipList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="assetStdProductCommonDTO.productId"/><!-- Key -->
<html:hidden property="assetStdProductEquipListDTO.assetStdProductEquipId"/><!-- Key -->
<html:hidden property="assetStdProductEquipListDTO.equipId"/>
<html:hidden property="assetStdProductEquipListDTO.equipDesc"/>
    <!-- searchbox 박스 Line -->

	<div class="article_box" id="listBox">
		<div class="grid_area">
			<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
		</div>
	</div>

</html:form> 
</body>
</html>
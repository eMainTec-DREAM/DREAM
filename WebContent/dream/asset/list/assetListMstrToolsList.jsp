<%--===========================================================================
구성자재
author  kim21017
version $Id: assetListMstrToolsList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.list.action.AssetListMstrToolsListAction" %>
<%@ page import="dream.asset.list.action.MaEqMstrDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.MwareConfig"%>
<%
String isUseAssetRevision = MwareConfig.getIsUseAssetRevision();
%>
<html>
<head>
<!-- 구성자재 -->
<title><bean:message key='TAB.assetListMstrToolsList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->

<script language="javascript">
<!--//
var selectedRowId;
function loadPage()
{
	initGrid();
	
	// 생성버튼 Control
 	createBtnCtrl();
 	
}

function createBtnCtrl()
{
	if("Y"=="<%=isUseAssetRevision%>")
	{
		hideBtn("CREATE");
	}
	
}

function afterDisable()
{
	$('.b_open').show();
	$('.b_excel').show();
	$('.b_setting').show();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');

	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		selectedRowId = rowId;
		goOpen(rowId);
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		assetListMstrToolsListForm.elements['assetListMstrToolsListDTO.equipId'].value = "";
    	return sortColumn("assetListMstrToolsList", this, assetListMstrToolsListForm, "EQUIPID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_equipId)
{
	if(""!=assetListMstrToolsListForm.elements['maEqMstrDetailDTO.equipId'].value)
		assetListMstrToolsListForm.elements['maEqMstrCommonDTO.equipId'].value = assetListMstrToolsListForm.elements['maEqMstrDetailDTO.equipId'].value;
	
    assetListMstrToolsListForm.elements['assetListMstrToolsListDTO.equipId'].value = _equipId;
    findGridList('ReloadRow');
    assetListMstrToolsListForm.elements['assetListMstrToolsListDTO.equipId'].value = "";
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
	if (assetListMstrToolsListForm.elements['maEqMstrCommonDTO.equipId'].value == '') return;

	var form = document.assetListMstrToolsListForm;
	form.strutsAction.value = '<%=AssetListMstrToolsListAction.EQ_MSTR_PART_LIST_FIND %>';

	var url = contextPath + "/assetListMstrToolsList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(assetListMstrToolsListForm), "TOOLNO", "Y");

}

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "maEqToolMstrDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
    assetListMstrToolsListForm.elements['maEqMstrDetailDTO.equipId'].value = "";
    assetListMstrToolsListForm.elements['maEqMstrCommonDTO.selectedEqCtgTypeId'].value = "TL";
    assetListMstrToolsListForm.elements['maEqMstrDetailDTO.parentEquipId'].value = assetListMstrToolsListForm.elements['maEqMstrCommonDTO.equipId'].value;
    assetListMstrToolsListForm.elements['maEqMstrDetailDTO.parentEquipDesc'].value = assetListMstrToolsListForm.elements['maEqMstrCommonDTO.equipDesc'].value;
    goCommonTabPage(assetListMstrToolsListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
	var delArray = getDeletRows(myGrid, 'isDelCheck', 'EQUIPID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
        alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
        return;
    }

    assetListMstrToolsListForm.strutsAction.value = '<%=AssetListMstrToolsListAction.EQ_MSTR_PEQUIP_DELETE%>';
    var url = contextPath + "/assetListMstrToolsList.do";
    
    $.post(url,FormQueryString(assetListMstrToolsListForm)+delArray , function(_data){
    	afterDelete();
    });
}

function afterDelete()
{
    goClose('maEqToolMstrDetail');
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}


/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('maEqToolMstrDetail');
}

/**
*
* 상세열기 버튼
*/
function goOpenAction()
{
   var selectedRowId=myGrid.getSelectedRowId();
   if(selectedRowId == null) return;
   
   assetListMstrToolsListForm.elements['maEqMstrCommonDTO.equipId'].value =getValueById(myGrid, selectedRowId,'EQUIPID');
   assetListMstrToolsListForm.elements['maEqMstrCommonDTO.selectedEqCtgTypeId'].value='TL'
	assetListMstrToolsListForm.elements['strutsAction'].value = '<%=MaEqMstrDetailAction.EQ_MSTR_DETAIL_INIT%>';
   openQuickTabPage(FormQueryString(assetListMstrToolsListForm), 'maEqToolMstrDetail'); 
} 
 
 /**
 * Tab 이동시 호출 
 */
 function goTabPage(pageId)
 {
    tabValidationCheck(myGrid, pageId, "goTabPageAction");
 }

 function goTabPageAction(pageId,selectedRowId)
 {
     assetListMstrToolsListForm.elements['maEqMstrCommonDTO.equipId'].value =getValueById(myGrid, selectedRowId,'EQUIPID');
     assetListMstrToolsListForm.elements['maEqMstrCommonDTO.selectedEqCtgTypeId'].value='TL'
     goCommonTabPage(assetListMstrToolsListForm, <%= MaEqMstrDetailAction.EQ_MSTR_DETAIL_INIT %>, pageId);
 }
 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
  	//excelAction(myGrid);
    assetListMstrToolsListForm.elements['maEqMstrCommonDTO.equipId'].value = getValueById(myGrid, selectedRowId,'PEQUIPID');
	excelServerAction("assetListMstrToolsList", assetListMstrToolsListForm );  
  }

 
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/assetListMstrToolsList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maEqMstrCommonDTO.equipId"/><!-- Key -->
<html:hidden property="maEqMstrCommonDTO.equipDesc"/>
<html:hidden property="maEqMstrCommonDTO.selectedEqCtgTypeId"/>
<html:hidden property="assetListMstrToolsListDTO.equipId"/>
<html:hidden property="maEqMstrDetailDTO.equipId"/>
<html:hidden property="maEqMstrDetailDTO.parentEquipId"/>
<html:hidden property="maEqMstrDetailDTO.parentEquipDesc"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form>
</body>
</html>
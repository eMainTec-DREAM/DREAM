<%--===========================================================================
author  youngjoo38
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.list.action.AssetListAssListAction" %>
<%@ page import="dream.ass.asset.action.AssAssetListAction" %>
<%@ page import="dream.ass.asset.action.AssAssetDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 계측기 위험도 평가 -->
<title><bean:message key='MENU.ASSASSETTL'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

function loadPage() 
{
	initGrid();
}
/**
 * 그리드 초기화
 */
function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    myGrid.enableSmartRendering(true,500);
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
        goOpen();
    });
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
    myGrid.init();
    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/assetListAssTlList.do";
    assetListAssListForm.elements['strutsAction'].value = '<%=AssetListAssListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(assetListAssListForm), "EQASSLISTID" , "Y");

}
/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_eqasslistId)
{
    assetListAssListForm.elements['assetListAssListDTO.eqasslistId'].value = _eqasslistId;
    findGridList('ReloadRow');
    assetListAssListForm.elements['assetListAssListDTO.eqasslistId'].value = "";
}

function goSearch()
{
    assetListAssListForm.elements['assetListAssListDTO.eqasslistId'].value = "";  // 검색시 Tab 이동Key Clear
    assetListAssListForm.elements['assetListAssListDTO.equipId'].value = assetListAssListForm.elements['maEqMstrCommonDTO.equipId'].value;
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
    assetListAssListForm.elements['assAssetCommonDTO.eqasslistId'].value =  getValueById(myGrid, selectedId,'EQASSLISTID');  
    
    goCommonTabPage(assetListAssListForm, <%= AssAssetDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('assetListAssTlDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    assetListAssListForm.elements['assAssetCommonDTO.eqasslistId'].value =  getValueById(myGrid, selectedId,'EQASSLISTID');  
    assetListAssListForm.elements['strutsAction'].value = '<%=AssAssetDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(assetListAssListForm), 'assetListAssDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "assetListAssTlDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
    assetListAssListForm.elements['assetListAssListDTO.eqasslistId'].value = "";
    assetListAssListForm.elements['assAssetDetailDTO.equipId'].value = assetListAssListForm.elements['maEqMstrCommonDTO.equipId'].value;
    assetListAssListForm.elements['assAssetDetailDTO.equipDesc'].value = assetListAssListForm.elements['maEqMstrCommonDTO.equipDesc'].value;
    goCommonTabPage(assetListAssListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
	var chkArray = getSelectRows(myGrid, 'ISDELCHECK','EQASSLISTSTATUSID');
 	if(chkArray.match('C')) {
		alertMessage1('<bean:message key="MESSAGE.MSG0245"/>');
		return;
	}
 	
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'EQASSLISTID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
        alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
        return;
    }

    assetListAssListForm.strutsAction.value = '<%=AssetListAssListAction.LIST_DELETE%>';
    var url = contextPath + "/assetListAssTlList.do";
    
    $.post(url,FormQueryString(assetListAssListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('assetListAssTlDetail');
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
    assetListAssListForm.elements['assetListAssListDTO.eqasslistId'].value = "";
    excelServerAction("assetListAssTlList", assetListAssListForm);
}


function goCreplaninvt()
{
    getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0236'/>", function(result){
		 if(result){
			 
			 var selArray = getSelectRows(myGrid, 'ISDELCHECK', 'EQUIPID'); //Grid, check box column seq, pk column seq
			 
			 if(typeof selArray == "undefined"){
					alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
					return;
				}
			 	assetListAssListForm.strutsAction.value = '<%=AssAssetListAction.CREATE_INVT_LIST%>';
				var url = contextPath + "/assAssetTlList.do";
			    $.post(url,FormQueryString(assetListAssListForm)+selArray , function(_data){
			        afterCreplaninvt();
			    });
		 }
	 });
}

function afterCreplaninvt()
{
    goClose('assetListAssTlDetail', this);
    alertMessage1('<bean:message key="MESSAGE.MSG0237"/>');
}


//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assetListAssTlList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="assAssetCommonDTO.eqasslistId"/><!-- Key -->
<html:hidden property="assAssetDetailDTO.equipId"/>
<html:hidden property="assAssetDetailDTO.equipDesc"/>
<html:hidden property="assetListAssListDTO.eqasslistId"/><!-- Key -->
<html:hidden property="assetListAssListDTO.equipId"/><!-- 설비ID -->

<html:hidden property="maEqMstrCommonDTO.equipId" /><!-- 설비ID -->
<html:hidden property="maEqMstrCommonDTO.equipDesc" />

    <!-- searchbox 박스 Line -->
    
    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>
</html:form> 
</body>
</html>


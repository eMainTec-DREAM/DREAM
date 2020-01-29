<%--===========================================================================
부품Image Link
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.list.action.PartListImgLinkUrlAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<title><bean:message key='PAGE.partListImgLinkUrlList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

/** 자동완성 변수 */

function loadPage() 
{
	partListImgLinkUrlForm.elements['partListImgLinkUrlDTO.partId'].value = partListImgLinkUrlForm.elements['maPtMstrCommonDTO.partId'].value;
	
    initGrid();
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
 	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
 		partListImgLinkUrlForm.elements['partListImgLinkUrlDTO.ptImgUrlId'].value = "";
        return sortColumn("partListImgLinkUrlList", this, partListImgLinkUrlForm, "PTIMGURLID", ind, direction);
    });
	
	setHeader(myGrid, "gridbox"); // grid, grid id
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/partListImgLinkUrlList.do";
    partListImgLinkUrlForm.elements['strutsAction'].value = '<%=PartListImgLinkUrlAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(partListImgLinkUrlForm), "PTIMGURLID","Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_ptImgUrlId)
{
	partListImgLinkUrlForm.elements['partListImgLinkUrlDTO.ptImgUrlId'].value = _ptImgUrlId;
	findGridList('ReloadRow');
	partListImgLinkUrlForm.elements['partListImgLinkUrlDTO.ptImgUrlId'].value = "";
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
    partListImgLinkUrlForm.elements['partListImgLinkUrlDTO.ptImgUrlId'].value =  getValueById(myGrid, selectedId,'PTIMGURLID');
	goCommonTabPage(partListImgLinkUrlForm, <%= PartListImgLinkUrlAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('partListImgLinkUrlDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    partListImgLinkUrlForm.elements['partListImgLinkUrlDTO.ptImgUrlId'].value = getValueById(myGrid, selectedId, 'PTIMGURLID');
    partListImgLinkUrlForm.elements['strutsAction'].value = '<%=PartListImgLinkUrlAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(partListImgLinkUrlForm), 'partListImgLinkUrlDetail');
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "partListImgLinkUrlDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	partListImgLinkUrlForm.elements['partListImgLinkUrlDTO.ptImgUrlId'].value = "";
    goCommonTabPage(partListImgLinkUrlForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'ptImgUrlId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    partListImgLinkUrlForm.strutsAction.value = '<%=PartListImgLinkUrlAction.LIST_DELETE%>';
    var url = contextPath + "/partListImgLinkUrlList.do";
    
    $.post(url,FormQueryString(partListImgLinkUrlForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('partListImgLinkUrlDetail', this);
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	partListImgLinkUrlForm.elements['partListImgLinkUrlDTO.ptImgUrlId'].value = "";
	excelServerAction("partListImgLinkUrlList", partListImgLinkUrlForm);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/partListImgLinkUrlList.do">
		<html:hidden property="strutsAction"/>
		<html:hidden property="maPtMstrCommonDTO.partId" />
		<html:hidden property="partListImgLinkUrlDTO.partId"/>
		<html:hidden property="partListImgLinkUrlDTO.ptImgUrlId"/>
		
		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
		
</html:form> 
</body>
</html>
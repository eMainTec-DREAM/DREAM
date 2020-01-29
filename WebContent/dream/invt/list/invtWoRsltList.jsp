<%--===========================================================================
설비투자작업
author  ghlee
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.invt.list.action.InvtWoRsltAction" %>
<%@ page import="dream.work.list.action.MaWoResultMstrDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%@ page import="common.bean.MwareConfig"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<title><bean:message key='PAGE.invtWoRsltList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var myGrid;
var woAc;
var beforePageId;

function loadPage() 
{
	setForUpdate();
	
	invtWoRsltForm.elements['invtWoRsltDTO.invtlistId'].value = invtWoRsltForm.elements['invtCommonDTO.invtlistId'].value;
	invtWoRsltForm.elements['invtWoRsltDTO.invtworkMethod'].value = 'RSLT';
	
	woAc = new autoC({"invtWoRsltDTO.wkorDesc":"description"});
	woAc.setTable("TAWORKORDER");
	woAc.setAcResultMap({
        "invtWoRsltDTO.wkorId":"wkorId"
    });
	woAc.init();
	
	initGrid();
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen(rowId);
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		invtWoRsltForm.elements['invtWoRsltDTO.wkorId'].value = "";
        return sortColumn("invtWoRsltList", this, invtWoRsltForm, "INVTWORKID", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox", true)});
	
	myGrid.init();
	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	invtWoRsltForm.elements['invtWoRsltDTO.wkorId'].value = "";
	findGridList('Search');
}

/**
 * gird find
 */
function findGridList(sheetAction)
{
	if (invtWoRsltForm.elements['invtWoRsltDTO.invtlistId'].value == '') return;
	
	var form = document.invtWoRsltForm;	
	form.strutsAction.value = '<%=InvtWoRsltAction.LIST_FIND%>'; 

	var url = contextPath + "/invtWoRsltList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(form), "INVTWORKID","Y");

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
	invtWoRsltForm.elements['maWoResultMstrCommonDTO.wkOrId'].value = getValueById(myGrid, selectedId,'WKORID');
	
	goCommonTabPage(invtWoRsltForm, <%=MaWoResultMstrDetailAction.WO_RESULT_DETAIL_INIT%>, pageId, beforePageId);
	beforePageId = pageId;
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_wkorId)
{
	invtWoRsltForm.elements['invtWoRsltDTO.wkorId'].value = _wkorId;
	findGridList('ReloadRow');
	invtWoRsltForm.elements['invtWoRsltDTO.wkorId'].value = "";
}
/**
 * 상세열기
 */
function goOpen(rowId)
{
	var param  = getValueById(myGrid, rowId,'PARAM');
	goTabPage(param);
}

function goOpenAction()
{
    var selectedId = myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
	var pageId  = getValueById(myGrid, selectedId,'PARAM');
	invtWoRsltForm.elements['maWoResultMstrCommonDTO.wkOrId'].value = getValueById(myGrid, selectedId,'WKORID');
	invtWoRsltForm.elements['strutsAction'].value = '<%=MaWoResultMstrDetailAction.WO_RESULT_DETAIL_INIT%>';
	openQuickTabPage(FormQueryString(invtWoRsltForm), pageId);
}

/**
 * Excel Export
 */
function goExcel()
{
	invtWoRsltForm.elements['invtWoRsltDTO.wkorId'].value = "";
	excelServerAction("invtWoRsltList", invtWoRsltForm);
}

/**
 * 삭제
 */
function goDelete()
{
	var delArray = getDeletRows(myGrid, 'ISDELCHECK','INVTWORKID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	invtWoRsltForm.strutsAction.value = '<%=InvtWoRsltAction.LIST_DELETE%>';
	var url = contextPath + "/invtWoRsltList.do";
	
    $.post(url,FormQueryString(invtWoRsltForm)+delArray , function(_data){
    	afterDelete();
    });
}

function afterDelete(){
	goClose();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * 생성
 */
function goCreate()
{
// 	if(""!=beforePageId)
// 		goClose(beforePageId, this);
	
	openSelectType();
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

function setAfterSelect(returnArray)
{
	var woType = returnArray[0];
	var pmType = returnArray[1];
	var _pageId  = returnArray[2];
	
	invtWoRsltForm.elements['maWoResultMstrCommonDTO.wkOrId'].value = '';
	invtWoRsltForm.elements['maWoResultMstrCommonDTO.selectedWoType'].value = woType;
	invtWoRsltForm.elements['maWoResultMstrCommonDTO.selectedPmType'].value = pmType;
	invtWoRsltForm.elements['maWoResultMstrCommonDTO.invtlistId'].value = invtWoRsltForm.elements['invtWoRsltDTO.invtlistId'].value;
	
	goCommonTabPage(invtWoRsltForm, '', _pageId, beforePageId);
	beforePageId = _pageId;
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
	if(code == 'invtWoRsltDTO.wkorDesc')
	{
		invtWoRsltForm.strutsAction.value = '<%=InvtWoRsltAction.LIST_LINK %>';
		
		invtWoRsltForm.elements['invtWoRsltDTO.wkorDesc'].value = JSON.stringify(rtnArr);
		
		goSaveAll();
	}
}

function goSave(){
	var url = contextPath + "/invtWoRsltList.do";
	
    $.post(url,FormQueryString(invtWoRsltForm), function(_data){
    	afterSave(_data);
    });
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    getTopPage().afterSaveAll(currentPageId);
    
    goSearch();
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/invtWoRsltList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="invtCommonDTO.invtlistId"/>
<html:hidden property="invtWoRsltDTO.invtlistId"/>
<html:hidden property="invtWoRsltDTO.invtworkId"/>
<html:hidden property="invtWoRsltDTO.wkorId"/>
<html:hidden property="invtWoRsltDTO.wkorDesc"/>
<html:hidden property="invtWoRsltDTO.invtworkMethod"/>
<html:hidden property="maWoResultMstrCommonDTO.wkOrId"/>
<html:hidden property="maWoResultMstrCommonDTO.selectedWoType"/>
<html:hidden property="maWoResultMstrCommonDTO.selectedPmType"/>
<html:hidden property="maWoResultMstrCommonDTO.invtlistId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
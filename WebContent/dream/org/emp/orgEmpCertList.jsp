<%--===========================================================================
구매신청 item  목록
author  kim21017
version $Id: orgEmpCertList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="common.tree.action.TreeAction" %>
<%@ page import="dream.org.emp.action.OrgEmpCertListAction" %>
<%@ page import="dream.org.emp.action.OrgEmpCertDetailAction" %>
<%@ page import="dream.org.emp.action.MaEmpDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<title><bean:message key='LABEL.buyReq'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

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
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("orgEmpCertDetail");
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	findGridList('Search');
}

/**
 * gird find
 */
function findGridList(sheetAction)
{
	if (orgEmpCertListForm.elements['maEmpCommonDTO.empId'].value == '') return;
	
	var form = document.orgEmpCertListForm;	
	form.strutsAction.value = '<%=OrgEmpCertListAction.BUY_ITEM_LIST_FIND%>'; 

	var url = contextPath + "/orgEmpCertList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(orgEmpCertListForm), "EMPCERTLISTID", "Y");
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
	var form = document.orgEmpCertListForm;
	form.elements['orgEmpCertListDTO.empCertListId'].value = getValueById(myGrid, selectedId,'EMPCERTLISTID');
	goCommonTabPage(form, <%= OrgEmpCertDetailAction.BUY_ITEM_DETAIL_INIT %>, "orgEmpCertDetail");
	
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_empCertListId)
{
	orgEmpCertListForm.elements['orgEmpCertListDTO.empCertListId'].value = _empCertListId;
	findGridList('ReloadRow');
	orgEmpCertListForm.elements['orgEmpCertListDTO.empCertListId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('orgEmpCertDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    orgEmpCertListForm.elements['orgEmpCertListDTO.empCertListId'].value = getValueById(myGrid, selectedId,'EMPCERTLISTID');
    orgEmpCertListForm.elements['strutsAction'].value = '<%= OrgEmpCertDetailAction.BUY_ITEM_DETAIL_INIT %>';
    openQuickTabPage(FormQueryString(orgEmpCertListForm), 'orgEmpCertDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "orgEmpCertDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	orgEmpCertListForm.elements['orgEmpCertListDTO.empCertListId'].value = "";
	goCommonTabPage(orgEmpCertListForm, '', pageId);
}
 

/**
 * Excel Export
 */
function goExcel()
{
	orgEmpCertListForm.elements['orgEmpCertListDTO.empCertListId'].value = "";
	excelServerAction("orgEmpCertList", orgEmpCertListForm );  
}

/**
 * 삭제
 */
 function goDelete()
 {
	var delArray = getDeletRows(myGrid, 'ISDELCHECK','EMPCERTLISTID','COMPNO'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	orgEmpCertListForm.strutsAction.value = '<%=OrgEmpCertListAction.BUY_ITEM_LIST_DELETE%>';
	var url = contextPath + "/orgEmpCertList.do";
	
    $.post(url,FormQueryString(orgEmpCertListForm)+delArray , function(_data){
    	afterDelete();
    });
 }

function afterDelete(){
	goClose('orgEmpCertDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = orgEmpCertListForm.elements['orgEmpCertListDTO.empCertListId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/orgEmpCertList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maEmpCommonDTO.empId"/><!-- Key -->
<html:hidden property="orgEmpCertListDTO.empCertListId"/><!-- Detail Key -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" >
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
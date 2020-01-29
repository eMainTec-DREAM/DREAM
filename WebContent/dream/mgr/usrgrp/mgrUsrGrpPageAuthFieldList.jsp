<%--===========================================================================
화면권한설정상세탭버튼권한목록
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.usrgrp.action.MgrUsrGrpPageAuthFieldAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 화면권한설정상세필드권한목록 -->
<title><bean:message key='PAGE.mgrUsrGrpPageAuthFieldList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

function loadPage() 
{
    mgrUsrGrpPageAuthFieldForm.elements['mgrUsrGrpPageAuthFieldDTO.pageId'].value = mgrUsrGrpPageAuthFieldForm.elements['mgrUsrGrpPageAuthDTO.pageId'].value;
    mgrUsrGrpPageAuthFieldForm.elements['mgrUsrGrpPageAuthFieldDTO.usrgrpId'].value = mgrUsrGrpPageAuthFieldForm.elements['mgrUsrGrpPageAuthDTO.usrgrpId'].value;
    
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
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	mgrUsrGrpPageAuthFieldForm.elements['mgrUsrGrpPageAuthFieldDTO.pgfieldId'].value = "";
    	return sortColumn("mgrUsrGrpPageAuthFieldList", this, mgrUsrGrpPageAuthFieldForm, ["PGFIELDID", "USRGRPID"], ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/mgrUsrGrpPageAuthFieldList.do";
    mgrUsrGrpPageAuthFieldForm.elements['strutsAction'].value = '<%=MgrUsrGrpPageAuthFieldAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mgrUsrGrpPageAuthFieldForm), ["PGFIELDID", "USRGRPID"], "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pgfieldId)
{
	mgrUsrGrpPageAuthFieldForm.elements['mgrUsrGrpPageAuthFieldDTO.pgfieldId'].value = _pgfieldId;
	findGridList('ReloadRow');
	mgrUsrGrpPageAuthFieldForm.elements['mgrUsrGrpPageAuthFieldDTO.pgfieldId'].value = "";
}

function goSearch()
{
	mgrUsrGrpPageAuthFieldForm.elements['mgrUsrGrpPageAuthFieldDTO.pgfieldId'].value = "";
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
	mgrUsrGrpPageAuthFieldForm.elements['mgrUsrGrpPageAuthFieldDTO.pgfieldId'].value =  getValueById(myGrid, selectedId,'PGFIELDID');
	goCommonTabPage(mgrUsrGrpPageAuthFieldForm, <%= MgrUsrGrpPageAuthFieldAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('mgrUsrGrpPageAuthFieldDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    mgrUsrGrpPageAuthFieldForm.elements['mgrUsrGrpPageAuthFieldDTO.pgfieldId'].value =  getValueById(myGrid, selectedId,'PGFIELDID');
	mgrUsrGrpPageAuthFieldForm.elements['strutsAction'].value = '<%=MgrUsrGrpPageAuthFieldAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(mgrUsrGrpPageAuthFieldForm), 'mgrUsrGrpPageAuthFieldDetail'); 
}

/**
 * Excel Export
 */
function goExcel()
{
	mgrUsrGrpPageAuthFieldForm.elements['mgrUsrGrpPageAuthFieldDTO.pgfieldId'].value = "";
	excelServerAction("mgrUsrGrpPageAuthFieldList", mgrUsrGrpPageAuthFieldForm );  
}


/**
 * 조회불가
 */
 
function goNoview() 
{
	var delArray = getSelectedRows(myGrid, 'isDelCheck', 'PGFIELDID', 'UGPGFIELDAUID', 'USRGRPID'); 
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
    mgrUsrGrpPageAuthFieldForm.elements['mgrUsrGrpPageAuthFieldDTO.authLimitTypeId'].value ="NV";
    
	mgrUsrGrpPageAuthFieldForm.strutsAction.value = '<%=MgrUsrGrpPageAuthFieldAction.LIST_NO_VIEW%>';
    var url = contextPath + "/mgrUsrGrpPageAuthFieldList.do";
    
    $.post(url,FormQueryString(mgrUsrGrpPageAuthFieldForm)+delArray , function(_data){
    	afterNoview();
    });
}


function afterNoview()
{
    goClose('mgrUsrGrpPageAuthFieldDetail');
    goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG1009"/>');
}


/**
 * 수정불가
 */
 
function goNoedit() 
{
	var delArray = getSelectedRows(myGrid, 'isDelCheck', 'PGFIELDID', 'UGPGFIELDAUID', 'USRGRPID'); 

	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    mgrUsrGrpPageAuthFieldForm.elements['mgrUsrGrpPageAuthFieldDTO.authLimitTypeId'].value = "NE"; 
    
	mgrUsrGrpPageAuthFieldForm.strutsAction.value = '<%=MgrUsrGrpPageAuthFieldAction.LIST_NO_EDIT%>';
    var url = contextPath + "/mgrUsrGrpPageAuthFieldList.do";
    
    $.post(url,FormQueryString(mgrUsrGrpPageAuthFieldForm)+delArray , function(_data){
    	afterNoedit();
    });
}


function afterNoedit()
{
    goClose('mgrUsrGrpPageAuthFieldDetail');
    goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG1009"/>');
}

/**
 * 제한없음
 */
function goNolimit()
{
	var delArray = getSelectedRows(myGrid, 'isDelCheck', 'PGFIELDID', 'UGPGFIELDAUID', 'USRGRPID'); 

    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    mgrUsrGrpPageAuthFieldForm.elements['mgrUsrGrpPageAuthFieldDTO.authLimitTypeId'].value ="NL"; 
    
	mgrUsrGrpPageAuthFieldForm.strutsAction.value = '<%=MgrUsrGrpPageAuthFieldAction.LIST_NO_LIMIT%>';
    var url = contextPath + "/mgrUsrGrpPageAuthFieldList.do";
    
    $.post(url,FormQueryString(mgrUsrGrpPageAuthFieldForm)+delArray , function(_data){
    	afterNolimit();
    });
}

function afterNolimit()
{
    goClose('mgrUsrGrpPageAuthFieldDetail');
    goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG1009"/>');
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/mgrUsrGrpPageAuthFieldList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="mgrUsrGrpPageAuthDTO.pageId"/>
<html:hidden property="mgrUsrGrpPageAuthDTO.usrgrpId"/>
<html:hidden property="mgrUsrGrpPageAuthFieldDTO.pageId"/>
<html:hidden property="mgrUsrGrpPageAuthFieldDTO.pgfieldId"/><!-- Key -->
<html:hidden property="mgrUsrGrpPageAuthFieldDTO.ugpgfieldauId"/><!-- Key -->
<html:hidden property="mgrUsrGrpPageAuthFieldDTO.usrgrpId"/><!-- Key -->
<html:hidden property="mgrUsrGrpPageAuthFieldDTO.authLimitTypeId"/>
    <div class="section_wrap">
        <div class="article_box" id="listBox">
            <div class="grid_area">
                <div id="gridbox" style="width:100%; height:500px; background-color:white;"></div>
            </div>
        </div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>
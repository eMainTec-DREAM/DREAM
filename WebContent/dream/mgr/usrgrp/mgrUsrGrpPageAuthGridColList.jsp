<%--===========================================================================
화면권한설정상세탭버튼권한목록
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.usrgrp.action.MgrUsrGrpPageAuthGridColAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 화면권한설정상세탭버튼권한목록 -->
<title><bean:message key='PAGE.mgrUsrGrpPageAuthGridColList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

function loadPage() 
{
    mgrUsrGrpPageAuthGridColForm.elements['mgrUsrGrpPageAuthGridColDTO.pageId'].value = mgrUsrGrpPageAuthGridColForm.elements['mgrUsrGrpPageAuthDTO.pageId'].value;
    mgrUsrGrpPageAuthGridColForm.elements['mgrUsrGrpPageAuthGridColDTO.usrgrpId'].value = mgrUsrGrpPageAuthGridColForm.elements['mgrUsrGrpPageAuthDTO.usrgrpId'].value;
    
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
    	mgrUsrGrpPageAuthGridColForm.elements['mgrUsrGrpPageAuthGridColDTO.pggridcolId'].value = "";
    	return sortColumn("mgrUsrGrpPageAuthGridColList", this, mgrUsrGrpPageAuthGridColForm, ["PGGRIDCOLID", "USRGRPID"], ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/mgrUsrGrpPageAuthGridColList.do";
    mgrUsrGrpPageAuthGridColForm.elements['strutsAction'].value = '<%=MgrUsrGrpPageAuthGridColAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mgrUsrGrpPageAuthGridColForm), ["PGGRIDCOLID", "USRGRPID"], "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pggridcolId)
{
	mgrUsrGrpPageAuthGridColForm.elements['mgrUsrGrpPageAuthGridColDTO.pggridcolId'].value = _pggridcolId;
	findGridList('ReloadRow');
	mgrUsrGrpPageAuthGridColForm.elements['mgrUsrGrpPageAuthGridColDTO.pggridcolId'].value = "";
}

function goSearch()
{
	mgrUsrGrpPageAuthGridColForm.elements['mgrUsrGrpPageAuthGridColDTO.pggridcolId'].value = "";
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
	mgrUsrGrpPageAuthGridColForm.elements['mgrUsrGrpPageAuthGridColDTO.pggridcolId'].value =  getValueById(myGrid, selectedId,'PGGRIDCOLID');
	goCommonTabPage(mgrUsrGrpPageAuthGridColForm, <%= MgrUsrGrpPageAuthGridColAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('mgrUsrGrpPageAuthGridColDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    mgrUsrGrpPageAuthGridColForm.elements['mgrUsrGrpPageAuthGridColDTO.pggridcolId'].value =  getValueById(myGrid, selectedId,'PGGRIDCOLID');
	mgrUsrGrpPageAuthGridColForm.elements['strutsAction'].value = '<%=MgrUsrGrpPageAuthGridColAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(mgrUsrGrpPageAuthGridColForm), 'mgrUsrGrpPageAuthGridColDetail'); 
}



/**
 * 조회불가
 */
 
function goNoview() 
{
	var delArray = getSelectedRows(myGrid, 'isDelCheck', 'PGGRIDCOLID', 'UGPGRIDCOLAUID', 'USRGRPID'); 

    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
    mgrUsrGrpPageAuthGridColForm.elements['mgrUsrGrpPageAuthGridColDTO.authLimitTypeId'].value ="NV";
    
    mgrUsrGrpPageAuthGridColForm.strutsAction.value = '<%=MgrUsrGrpPageAuthGridColAction.LIST_NO_VIEW%>';
    var url = contextPath + "/mgrUsrGrpPageAuthGridColList.do";
    
    $.post(url,FormQueryString(mgrUsrGrpPageAuthGridColForm)+delArray , function(_data){
    	afterNoview();
    });
}


function afterNoview()
{
    goClose('mgrUsrGrpPageAuthGridColDetail');
    goSearch();
    alertMessage1('<bean:message key="MESSAGE.MGS0235"/>');
}


/**
 * 수정불가
 */
 
function goNoedit() 
{
	var delArray = getSelectedRows(myGrid, 'isDelCheck', 'PGGRIDCOLID', 'UGPGRIDCOLAUID', 'USRGRPID'); 

    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    mgrUsrGrpPageAuthGridColForm.elements['mgrUsrGrpPageAuthGridColDTO.authLimitTypeId'].value ="NE";
    
    mgrUsrGrpPageAuthGridColForm.strutsAction.value = '<%=MgrUsrGrpPageAuthGridColAction.LIST_NO_EDIT%>';
    var url = contextPath + "/mgrUsrGrpPageAuthGridColList.do";
    
    $.post(url,FormQueryString(mgrUsrGrpPageAuthGridColForm)+delArray , function(_data){
    	afterNoedit();
    });
}


function afterNoedit()
{
    goClose('mgrUsrGrpPageAuthGridColDetail');
    goSearch();
    alertMessage1('<bean:message key="MESSAGE.MGS0235"/>');
}

/**
 * 제한없음
 */
function goNolimit()
{
	var delArray = getSelectedRows(myGrid, 'isDelCheck', 'PGGRIDCOLID', 'UGPGRIDCOLAUID', 'USRGRPID'); 

    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
    mgrUsrGrpPageAuthGridColForm.elements['mgrUsrGrpPageAuthGridColDTO.authLimitTypeId'].value ="NL";
    
    mgrUsrGrpPageAuthGridColForm.strutsAction.value = '<%=MgrUsrGrpPageAuthGridColAction.LIST_NO_LIMIT%>';
    var url = contextPath + "/mgrUsrGrpPageAuthGridColList.do";
    
    $.post(url,FormQueryString(mgrUsrGrpPageAuthGridColForm)+delArray , function(_data){
    	afterNolimit();
    });
}

function afterNolimit()
{
    goClose('mgrUsrGrpPageAuthGridColDetail');
    goSearch();
    alertMessage1('<bean:message key="MESSAGE.MGS0235"/>');
}


/**
 * Excel Export
 */
function goExcel()
{
	mgrUsrGrpPageAuthGridColForm.elements['mgrUsrGrpPageAuthGridColDTO.pggridcolId'].value = "";
	excelServerAction("mgrUsrGrpPageAuthGridColList", mgrUsrGrpPageAuthGridColForm );  
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/mgrUsrGrpPageAuthGridColList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="mgrUsrGrpPageAuthDTO.pageId"/><!-- Key -->
<html:hidden property="mgrUsrGrpPageAuthDTO.usrgrpId"/><!-- Key -->
<html:hidden property="mgrUsrGrpPageAuthGridColDTO.pageId"/><!-- Key -->
<html:hidden property="mgrUsrGrpPageAuthGridColDTO.pggridcolId"/><!-- Key -->
<html:hidden property="mgrUsrGrpPageAuthGridColDTO.usrgrpId"/><!-- Key -->
<html:hidden property="mgrUsrGrpPageAuthGridColDTO.gridId"/>
<html:hidden property="mgrUsrGrpPageAuthGridColDTO.authLimitTypeId"/>
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
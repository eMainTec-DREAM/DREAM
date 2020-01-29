<%--===========================================================================
Interface List
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.intf.action.MgrIntfListAction" %>
<%@ page import="dream.mgr.intf.action.MgrIntfDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 인터페이스 -->
<title><bean:message key='MENU.INTFLOG'/></title>
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
    
    // 사용여부 - 기본 선택.
    mgrIntfListForm.elements['mgrIntfCommonDTO.filterIsUse'].value = "Y";
    mgrIntfListForm.elements['mgrIntfCommonDTO.filterIsUseDesc'].value = "Y";
    //사용여부 자동완성
    acSysDesc("mgrIntfCommonDTO.filterIsUseDesc","mgrIntfCommonDTO.filterIsUse","IS_USE");
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
    	mgrIntfListForm.elements['mgrIntfCommonDTO.intfId'].value = "";
    	return sortColumn("mgrIntfList", this, mgrIntfListForm, "INTFID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

function setAcLovValue(rtnValueArr, acInputName)
{
	for(var i in rtnValueArr)
	{
	     console.log("setAcLovValue:"+JSON.stringify(rtnValueArr[i]));
	}
}

function afterAutoCmpt(acInputName, rtnJArray)
{
	for(var i in rtnJArray)
	{
	     console.log("afterAutoCmpt:"+JSON.stringify(rtnJArray[i]));
	}
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/mgrIntfList.do";
    mgrIntfListForm.elements['strutsAction'].value = '<%=MgrIntfListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mgrIntfListForm), "INTFID","Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_intfId)
{
	mgrIntfListForm.elements['mgrIntfCommonDTO.intfId'].value = _intfId;
	findGridList('ReloadRow');
	mgrIntfListForm.elements['mgrIntfCommonDTO.intfId'].value = "";
}

function goSearch()
{
	mgrIntfListForm.elements['mgrIntfCommonDTO.intfId'].value = "";	// 검색시 Tab 이동Key Clear
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
	mgrIntfListForm.elements['mgrIntfCommonDTO.intfId'].value =  getValueById(myGrid, selectedId,'INTFID');  
	goCommonTabPage(mgrIntfListForm, <%= MgrIntfDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('mgrIntfDetail');
}


function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    mgrIntfListForm.elements['mgrIntfCommonDTO.intfId'].value = getValueById(myGrid, selectedId, 'intfId');
    mgrIntfListForm.elements['strutsAction'].value = '<%=MgrIntfDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(mgrIntfListForm), 'mgrIntfDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "mgrIntfDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	mgrIntfListForm.elements['mgrIntfCommonDTO.intfId'].value = "";
    goCommonTabPage(mgrIntfListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'INTFID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    mgrIntfListForm.strutsAction.value = '<%=MgrIntfListAction.LIST_DELETE%>';
    var url = contextPath + "/mgrIntfList.do";
    
    $.post(url,FormQueryString(mgrIntfListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('mgrIntfDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	mgrIntfListForm.elements['mgrIntfCommonDTO.intfId'].value = "";
	excelServerAction("mgrIntfList", mgrIntfListForm );  
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/mgrIntfList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="mgrIntfCommonDTO.intfId"/><!-- Key -->
<html:hidden property="mgrIntfCommonDTO.filterIsUse"/>

    <!-- searchbox 박스 Line -->
    <div class="section_wrap">
    	<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.Filter"/></div>
			</div>
			<div class="function_box filter">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>	
				<div class="fb_group2">
				</div>
			</div>
		</div><!--sheader_box end-->
		<div class="article_box">
			<div class="form_box">
				<!-- 프로그램 가이드명 -->
				<div class="field">
					<label><bean:message key="LABEL.intfName"/></label>
					<div class="input_box">
						<html:text property="mgrIntfCommonDTO.filterIntfDesc" tabindex="10"/>
					</div>
				</div>
				<!-- 사용여부 -->
				<div class="field">
					<label><bean:message key="LABEL.isUse"/></label>
					<div class="input_box">
						<html:text property="mgrIntfCommonDTO.filterIsUseDesc" tabindex="20" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
			</div>
		</div><!--article_box end-->
	</div> <!--  end section_wrap -->
	<div class="section_wrap">
		<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.List"/></div>
			</div>
			<div class="function_box list">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>	
				<div class="fb_group2">
				</div>
			</div>
		</div><!--sheader_box end-->
		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>
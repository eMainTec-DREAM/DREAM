<%--===========================================================================
Program Guide List
author  kim21017
version $Id: persPrivDbSetList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.pers.priv.db.set.action.PersPrivDbSetListAction" %>
<%@ page import="dream.pers.priv.db.set.action.PersPrivDbSetDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 대시보드 -->
<title><bean:message key='MENU.DASHBOARD'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

/** 자동완성 변수 */
var filterDbContTypeDescAc;

function loadPage() 
{
    initGrid();
    // Contents 구분 자동완성
    acSysDesc("persPrivDbSetCommonDTO.usrCntTypeDesc","persPrivDbSetCommonDTO.usrCntTypeId","DBCONTENTS_TYPE");
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
    	persPrivDbSetListForm.elements['persPrivDbSetCommonDTO.usrCntTypeId'].value = "";
    	return sortColumn("persPrivDbSetList", this, persPrivDbSetListForm, "usrDbId", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/persPrivDbSetList.do";
    persPrivDbSetListForm.elements['strutsAction'].value = '<%=PersPrivDbSetListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(persPrivDbSetListForm), "usrDbId","Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_usrDbId)
{
	persPrivDbSetListForm.elements['persPrivDbSetCommonDTO.usrDbId'].value = _usrDbId;
	findGridList('ReloadRow');
	persPrivDbSetListForm.elements['persPrivDbSetCommonDTO.usrDbId'].value = "";
}

function goSearch()
{
	persPrivDbSetListForm.elements['persPrivDbSetCommonDTO.usrDbId'].value = "";	// 검색시 Tab 이동Key Clear
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
	persPrivDbSetListForm.elements['persPrivDbSetCommonDTO.usrDbId'].value =  getValueById(myGrid, selectedId,'usrDbId');  
	goCommonTabPage(persPrivDbSetListForm, <%= PersPrivDbSetDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('persPrivDbSetDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    persPrivDbSetListForm.elements['persPrivDbSetCommonDTO.usrDbId'].value =  getValueById(myGrid, selectedId,'usrDbId');   
    persPrivDbSetListForm.elements['strutsAction'].value = '<%=PersPrivDbSetDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(persPrivDbSetListForm), 'persPrivDbSetDetail'); 
}

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "persPrivDbSetDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	persPrivDbSetListForm.elements['persPrivDbSetCommonDTO.usrDbId'].value = "";
    goCommonTabPage(persPrivDbSetListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'usrDbId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    persPrivDbSetListForm.strutsAction.value = '<%=PersPrivDbSetListAction.LIST_DELETE%>';
    var url = contextPath + "/persPrivDbSetList.do";
    
    $.post(url,FormQueryString(persPrivDbSetListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('persPrivDbSetDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	persPrivDbSetListForm.elements['persPrivDbSetCommonDTO.usrDbId'].value = "";
	excelServerAction("persPrivDbSetList", persPrivDbSetListForm );  
}

//-->
</script>

<!-- ########## tab 관련 ########## -->
<LINK href="common/css/ma/bootstrap.css" rel="stylesheet">
<!--  
<SCRIPT src="common/js/jquery.js"></SCRIPT>
-->
<SCRIPT src="common/js/bootstrap-dropdown.js"></SCRIPT>
<SCRIPT src="common/js/bootstrap-tab.js"></SCRIPT>
<SCRIPT src="common/js/bootstrap-tabdrop.js"></SCRIPT>
<SCRIPT>
	if (top.location != location) {
    top.location.href = document.location.href ;
  }
		$(function(){
			window.prettyPrint && prettyPrint();
      $('.nav-tabs:first').tabdrop();
      $('.nav-tabs:last').tabdrop({text: 'More options'});
      $('.nav-pills').tabdrop({text: 'With pills'});
		});
</SCRIPT>
<!-- ########## tab 관련 end ########## -->
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/persPrivDbSetList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="persPrivDbSetCommonDTO.usrDbId"/>  	  <!-- 대시보드 ID -->
<html:hidden property="persPrivDbSetCommonDTO.usrCntTypeId"/> <!-- 컨텐츠 구분 ID -->
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
	</div>
	<!--sheader_box end-->
	<div class="article_box">
		<div class="form_box">
			<!-- 제목 -->
			<div class="field">
				<label><bean:message key="LABEL.title"/></label>
				<div class="input_box">
					<html:text property="persPrivDbSetCommonDTO.usrDbDesc" tabindex="10"/>
				</div>
			</div>
			<!-- Contents 구분 -->
			<div class="field">
				<label><bean:message key="LABEL.contentsType"/></label>
				<div class="input_box">
					<html:text property="persPrivDbSetCommonDTO.usrCntTypeDesc" tabindex="110" />
					<p class="open_spop">
						<a>
							<span>조회</span>
						</a>
					</p>
				</div>
			</div>
		</div>
	</div>
	<!--article_box end-->
</div> 
<!--  end section_wrap -->
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
</div> 
<!--  End of section_wrap -->
</html:form> 
</body>
</html>
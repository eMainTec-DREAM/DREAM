<%--===========================================================================
Report List - 목록 
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.program.report.action.ConsultPgmRptAction" %>
<%@ page import="common.util.CommonUtil"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%
    User loginUser = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!-- Report List -->
<title><bean:message key='PAGE.consultPgmRptList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="consultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
var myGrid;

function loadPage() 
{
	// Report 종류
	acSysDesc("consultPgmRptDTO.filterReportFileTypeDesc","consultPgmRptDTO.filterReportFileType","RPTFILE_TYPE");
	
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
    	consultPgmRptForm.elements['consultPgmRptDTO.rptListId'].value = "";
        return sortColumn("consultPgmRptList", this, consultPgmRptForm, "rptListId", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
	myGrid.init();
	setHeader(myGrid, "gridbox", "afterHeader"); // grid, grid id
}

function afterHeader() {
	myGrid.setDateFormat("%Y-%m-%d %H:%i:%s","%Y%m%d%H%i%s");
	goSearch();
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	var url = contextPath + "/consultPgmRptList.do";
	
	consultPgmRptForm.elements['strutsAction'].value = '<%=ConsultPgmRptAction.LIST_FIND%>';
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(consultPgmRptForm), "rptListId", "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_rptListId)
{
	consultPgmRptForm.elements['consultPgmRptDTO.rptListId'].value = _rptListId;
	findGridList('ReloadRow');
	consultPgmRptForm.elements['consultPgmRptDTO.rptListId'].value = "";
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	consultPgmRptForm.elements['consultPgmRptDTO.rptListId'].value = "";	// 검색시 Tab 이동Key Clear
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
	consultPgmRptForm.elements['consultPgmRptDTO.rptListId'].value = getValueById(myGrid, selectedId, 'rptListId');
	goCommonTabPage(consultPgmRptForm, <%= ConsultPgmRptAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('consultPgmRptDetail');
}

function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
    
    if(selectedId == null) return;
    
    consultPgmRptForm.elements['consultPgmRptDTO.rptListId'].value = getValueById(myGrid, selectedId, 'rptListId');
    consultPgmRptForm.elements['strutsAction'].value = '<%=ConsultPgmRptAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(consultPgmRptForm), 'consultPgmRptDetail'); 
} 


/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "consultPgmRptDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	consultPgmRptForm.elements['consultPgmRptDTO.rptListId'].value = "";
    goCommonTabPage(consultPgmRptForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'rptListId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
        alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
        return;
    }

    consultPgmRptForm.strutsAction.value = '<%=ConsultPgmRptAction.LIST_DELETE%>';
    var url = contextPath + "/consultPgmRptList.do";
    
    $.post(url,FormQueryString(consultPgmRptForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('consultPgmRptDetail');

    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}


/**
 * Excel Export
 */
function goExcel()
{
	consultPgmRptForm.elements['consultPgmRptDTO.rptListId'].value = "";
	excelServerAction("consultPgmRptList", consultPgmRptForm);  
}
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/consultPgmRptList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="consultPgmRptDTO.rptListId"/><!-- Key -->
<html:hidden property="consultPgmRptDTO.filterReportFileType"/>
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
            <!-- 출력물No -->
			<div class="field">
				<label>출력물No</label>
				<div class="input_box">
					<html:text property="consultPgmRptDTO.filterReportNo" tabindex="10" />
				</div>
			</div>
            <!-- 출력물 명 -->
			<div class="field">
				<label>출력물 명</label>
				<div class="input_box">
					<html:text property="consultPgmRptDTO.filterReportName" tabindex="20" />
				</div>
			</div>
            <!-- Report 종류 -->
			<div class="field">
				<label>Report 종류</label>
				<div class="input_box">
					<html:text property="consultPgmRptDTO.filterReportFileTypeDesc" tabindex="30" />
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
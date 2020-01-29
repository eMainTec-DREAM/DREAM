<%--===========================================================================
출력물 설정 파일 - 목록 
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.program.report.file.action.ConsultPgmRptFileAction" %>
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
<title><bean:message key='TAB.consultPgmRptFileList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="adminPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
var myGrid;
var beforePageId = '';

function loadPage() 
{
	setForUpdate();

	consultPgmRptFileForm.elements['consultPgmRptFileDTO.rptListId'].value = consultPgmRptFileForm.elements['consultPgmRptDTO.rptListId'].value;
	
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
    	consultPgmRptFileForm.elements['consultPgmRptFileDTO.rptFileId'].value = "";
        return sortColumn("consultPgmRptFileList", this, consultPgmRptFileForm, "rptFileId", ind, direction);
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
	var url = contextPath + "/consultPgmRptFileList.do";
	
	consultPgmRptFileForm.elements['strutsAction'].value = '<%=ConsultPgmRptFileAction.LIST_FIND%>';
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(consultPgmRptFileForm), "rptFileId", "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_rptFileId)
{
	consultPgmRptFileForm.elements['consultPgmRptFileDTO.rptFileId'].value = _rptFileId;
	findGridList('ReloadRow');
	consultPgmRptFileForm.elements['consultPgmRptFileDTO.rptFileId'].value = "";
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	consultPgmRptFileForm.elements['consultPgmRptFileDTO.rptFileId'].value = "";	// 검색시 Tab 이동Key Clear
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
	consultPgmRptFileForm.elements['consultPgmRptFileDTO.rptFileId'].value = getValueById(myGrid, selectedId, 'RPTFILEID');
	
	goCommonTabPage(consultPgmRptFileForm, <%= ConsultPgmRptFileAction.DETAIL_INIT %>, pageId, beforePageId);
	
	beforePageId = pageId;
}

/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('consultPgmRptFileDetail');
}

function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
    
    if(selectedId == null) return;
    
    consultPgmRptFileForm.elements['consultPgmRptFileDTO.rptFileId'].value = getValueById(myGrid, selectedId, 'rptFileId');
    consultPgmRptFileForm.elements['strutsAction'].value = '<%=ConsultPgmRptFileAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(consultPgmRptFileForm), 'consultPgmRptFileDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "consultPgmRptFileDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	consultPgmRptFileForm.elements['consultPgmRptFileDTO.rptFileId'].value = "";
    goCommonTabPage(consultPgmRptFileForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'rptFileId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
        alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
        return;
    }

    consultPgmRptFileForm.strutsAction.value = '<%=ConsultPgmRptFileAction.LIST_DELETE%>';
    var url = contextPath + "/consultPgmRptFileList.do";
    
    $.post(url,FormQueryString(consultPgmRptFileForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('consultPgmRptFileDetail');
	
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}


/**
 * Excel Export
 */
function goExcel()
{
	consultPgmRptFileForm.elements['consultPgmRptFileDTO.rptFileId'].value = "";
	excelServerAction(currentPageId, consultPgmRptFileForm );  
}
 
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/consultPgmRptFileList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="consultPgmRptDTO.rptListId"/><!-- Key -->
<html:hidden property="consultPgmRptFileDTO.rptListId"/><!-- Key -->
<html:hidden property="consultPgmRptFileDTO.rptFileId"/><!-- Key -->
	<!-- searchbox 박스 Line -->
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
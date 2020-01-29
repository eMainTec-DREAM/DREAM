<%--===========================================================================
교정표준값 타입
author  kim21017
version $Id: workPmStdCalibList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.pm.std.action.WorkPmStdCalibListAction" %>
<%@ page import="dream.work.pm.std.action.WorkPmStdCalibDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 교정표준값 -->
<title><bean:message key='MENU.STDCALIB'/></title>
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
    initGrid();

	//교정작업 타입 AC
    acSysDesc("workPmStdCalibCommonDTO.filterPmcTypeDesc","workPmStdCalibCommonDTO.filterPmcTypeId","PMC_TYPE");
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
		workPmStdCalibListForm.elements['workPmStdCalibCommonDTO.pmCalibStdTpId'].value = "";
    	return sortColumn("workPmStdCalibList", this, workPmStdCalibListForm, "PMCALIBSTDTPID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/workPmStdCalibList.do";
    workPmStdCalibListForm.elements['strutsAction'].value = '<%=WorkPmStdCalibListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workPmStdCalibListForm), "PMCALIBSTDTPID","Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pmCalibStdTpId)
{
	workPmStdCalibListForm.elements['workPmStdCalibCommonDTO.pmCalibStdTpId'].value = _pmCalibStdTpId;
	findGridList('ReloadRow');
	workPmStdCalibListForm.elements['workPmStdCalibCommonDTO.pmCalibStdTpId'].value = "";
}

function goSearch()
{
	workPmStdCalibListForm.elements['workPmStdCalibCommonDTO.pmCalibStdTpId'].value = "";	// 검색시 Tab 이동Key Clear
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
	workPmStdCalibListForm.elements['workPmStdCalibCommonDTO.pmCalibStdTpId'].value =  getValueById(myGrid, selectedId,'PMCALIBSTDTPID');  
	goCommonTabPage(workPmStdCalibListForm, <%= WorkPmStdCalibDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('workPmStdCalibDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    workPmStdCalibListForm.elements['workPmStdCalibCommonDTO.pmCalibStdTpId'].value =  getValueById(myGrid, selectedId,'PMCALIBSTDTPID');  
    workPmStdCalibListForm.elements['strutsAction'].value = '<%=WorkPmStdCalibDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(workPmStdCalibListForm), 'workPmStdCalibDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "workPmStdCalibDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	workPmStdCalibListForm.elements['workPmStdCalibCommonDTO.pmCalibStdTpId'].value = "";
    goCommonTabPage(workPmStdCalibListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'PMCALIBSTDTPID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    workPmStdCalibListForm.strutsAction.value = '<%=WorkPmStdCalibListAction.LIST_DELETE%>';
    var url = contextPath + "/workPmStdCalibList.do";
    
    $.post(url,FormQueryString(workPmStdCalibListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('workPmStdCalibDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	workPmStdCalibListForm.elements['workPmStdCalibCommonDTO.pmCalibStdTpId'].value = "";
	excelServerAction("workPmStdCalibList", workPmStdCalibListForm );  
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = workPmStdCalibListForm.elements['workPmStdCalibCommonDTO.pmCalibStdTpId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workPmStdCalibList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="workPmStdCalibCommonDTO.pmCalibStdTpId"/><!-- Key -->
<html:hidden property="workPmStdCalibCommonDTO.filterPmcTypeId"/>
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
				<!-- 교정작업 타입  -->
				<div class="field">
					<label><bean:message key="LABEL.pmcType"/></label>
					<div class="input_box">
						<html:text property="workPmStdCalibCommonDTO.filterPmcTypeDesc" tabindex="120" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 표준교정값 타입No  -->
				<div class="field">
					<label><bean:message key="LABEL.pmStdCalibNo"/></label>
					<div class="input_box">
						<html:text property="workPmStdCalibCommonDTO.filterPmCalibStdTpNo" tabindex="10"/>
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
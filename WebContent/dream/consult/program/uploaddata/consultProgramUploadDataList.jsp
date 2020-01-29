<%--===========================================================================
업로드데이타 - 목록
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.program.uploaddata.action.ConsultProgramUploadDataAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 업로드데이타 -->
<title><bean:message key='MENU.UPLOADDATA'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="consultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;
function loadPage() 
{
	// Config Setting 사용
    acSysDesc("consultProgramUploadDataDTO.filterIsUseConfig","consultProgramUploadDataDTO.filterIsUseConfig","IS_USE");
    // 사용여부
    acSysDesc("consultProgramUploadDataDTO.filterIsUse","consultProgramUploadDataDTO.filterIsUse","IS_USE");
    
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
		consultProgramUploadDataForm.elements['consultProgramUploadDataDTO.excelTabId'].value = "";
    	return sortColumn("consultProgramUploadDataList", this, consultProgramUploadDataForm, "EXCELTABID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
	
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/consultProgramUploadDataList.do";

    consultProgramUploadDataForm.elements['strutsAction'].value = '<%=ConsultProgramUploadDataAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(consultProgramUploadDataForm), "EXCELTABID", "Y");

}


/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	consultProgramUploadDataForm.elements['consultProgramUploadDataDTO.excelTabId'].value = "";	// 검색시 Tab 이동Key Clear
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
    consultProgramUploadDataForm.elements['consultProgramUploadDataDTO.excelTabId'].value = getValueById(myGrid, selectedId,'EXCELTABID');
    goCommonTabPage(consultProgramUploadDataForm, <%= ConsultProgramUploadDataAction.DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_excelTabId)
{
	consultProgramUploadDataForm.elements['consultProgramUploadDataDTO.excelTabId'].value = _excelTabId;
	findGridList('ReloadRow');
	consultProgramUploadDataForm.elements['consultProgramUploadDataDTO.excelTabId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('consultProgramUploadDataDetail');	
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    consultProgramUploadDataForm.elements['consultProgramUploadDataDTO.excelTabId'].value = getValueById(myGrid, selectedId,'EXCELTABID');
    consultProgramUploadDataForm.elements['strutsAction'].value = '<%=ConsultProgramUploadDataAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(consultProgramUploadDataForm), 'consultProgramUploadDataDetail'); 
} 

 /**
  * 생성
  */
function goCreate()
{
	createValidationCheck(myGrid, "consultProgramUploadDataDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	consultProgramUploadDataForm.elements['consultProgramUploadDataDTO.excelTabId'].value = "";
	goCommonTabPage(consultProgramUploadDataForm, '', pageId);
}

/**
 * Excel Export
 */
function goExcel()
{
	consultProgramUploadDataForm.elements['consultProgramUploadDataDTO.excelTabId'].value = "";
    excelServerAction("consultProgramUploadDataList",consultProgramUploadDataForm);
}

 /**
   * 삭제
   */
function goDelete()
{
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'EXCELTABID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	consultProgramUploadDataForm.strutsAction.value = '<%=ConsultProgramUploadDataAction.LIST_DELETE%>';
	var url = contextPath + "/consultProgramUploadDataList.do";
	$.post(url,FormQueryString(consultProgramUploadDataForm)+delArray , function(_data){
    	afterDelete();
    });
}
 
function afterDelete()
{
	goClose('consultProgramUploadDataDetail');
	// 	goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}
  
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/consultProgramUploadDataList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="consultProgramUploadDataDTO.excelTabId"/><!-- Key -->
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
                <!-- 엑셀유형 No -->
                <div class="field">
	                <label><bean:message key="LABEL.excelTypeNo"/></label>
               	    <div class="input_box">
               	    	<html:text property="consultProgramUploadDataDTO.filterExcelTabNo" tabindex="10"/>
               	    </div>
              	</div>
             	<!-- 엑셀유형명 -->
             	<div class="field">
					<label><bean:message key="LABEL.excelType"/></label>
					<div class="input_box">
						<html:text property="consultProgramUploadDataDTO.filterExcelTabName" tabindex="30"/>
					</div>
			    </div>
			    <!-- 테이블명 -->
           	    <div class="field">
            	    <label><bean:message key="LABEL.tableDesc"/></label>
            	    <div class="input_box">
            	    	<html:text property="consultProgramUploadDataDTO.filterTableName" tabindex="40"/>
            	    </div>
           	    </div>
           	    <!-- 프로그램명 -->
           	    <div class="field">
	                <label><bean:message key="LABEL.programName"/></label>
               	    <div class="input_box">
          	    		<html:text property="consultProgramUploadDataDTO.filterUploadPgmName" tabindex="50"/>
               	    </div>
              	</div>
           	    <!-- Config Setting 사용 -->
           	    <div class="field">
	                <label><bean:message key="LABEL.isUseConfig"/></label>
               	    <div class="input_box">
          	    		<html:text property="consultProgramUploadDataDTO.filterIsUseConfig" tabindex="60"/>
          	    		<p class="open_spop"><a><span>조회</span></a></p>
               	    </div>
              	</div>
           	    <!-- 사용여부 -->
           	    <div class="field">
	                <label><bean:message key="LABEL.isUse"/></label>
               	    <div class="input_box">
          	    		<html:text property="consultProgramUploadDataDTO.filterIsUse" tabindex="70"/>
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
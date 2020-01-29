<%--===========================================================================
Program Error List
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.program.error.action.ConsultPgmErrorListAction" %>
<%@ page import="dream.consult.program.error.action.ConsultPgmErrorDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 프로그램 가이드 -->
<title><bean:message key='MENU.PGMGUIDE'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="consultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

/** 자동완성 변수 */
function loadPage() 
{
    initGrid();
    
    // Error 일자
	consultPgmErrorListForm.elements['consultPgmErrorCommonDTO.filterStartDate'].value = getMinusMonth2(new Date(), -1);;
    consultPgmErrorListForm.elements['consultPgmErrorCommonDTO.filterEndDate'].value = getToday();

    
    // 확인여부
    acSysDesc("consultPgmErrorCommonDTO.filterIsCheckDesc","consultPgmErrorCommonDTO.filterIsCheckId","IS_USE");
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
    	consultPgmErrorListForm.elements['consultPgmErrorCommonDTO.errorLogId'].value = "";
    	return sortColumn("consultPgmErrorList", this, consultPgmErrorListForm, "ERRORLOGID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/consultPgmErrorList.do";
    consultPgmErrorListForm.elements['strutsAction'].value = '<%=ConsultPgmErrorListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(consultPgmErrorListForm), "ERRORLOGID","Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_errorLogId)
{
	consultPgmErrorListForm.elements['consultPgmErrorCommonDTO.errorLogId'].value = _errorLogId;
	findGridList('ReloadRow');
	consultPgmErrorListForm.elements['consultPgmErrorCommonDTO.errorLogId'].value = "";
}

function goSearch()
{
	consultPgmErrorListForm.elements['consultPgmErrorCommonDTO.errorLogId'].value = "";	// 검색시 Tab 이동Key Clear
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
	consultPgmErrorListForm.elements['consultPgmErrorCommonDTO.errorLogId'].value =  getValueById(myGrid, selectedId,'ERRORLOGID');  
	goCommonTabPage(consultPgmErrorListForm, <%= ConsultPgmErrorDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('consultPgmErrorDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    consultPgmErrorListForm.elements['consultPgmErrorCommonDTO.errorLogId'].value =  getValueById(myGrid, selectedId,'ERRORLOGID');  
    consultPgmErrorListForm.elements['strutsAction'].value = '<%=ConsultPgmErrorDetailAction.DETAIL_INIT %>';
    openQuickTabPage(FormQueryString(consultPgmErrorListForm), 'consultPgmErrorDetail'); 
} 

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'ERRORLOGID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    consultPgmErrorListForm.strutsAction.value = '<%=ConsultPgmErrorListAction.LIST_DELETE%>';
    var url = contextPath + "/consultPgmErrorList.do";
    
    $.post(url,FormQueryString(consultPgmErrorListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('consultPgmErrorDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	consultPgmErrorListForm.elements['consultPgmErrorCommonDTO.errorLogId'].value = "";
	excelServerAction("consultPgmErrorList", consultPgmErrorListForm );  
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/consultPgmErrorList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="consultPgmErrorCommonDTO.errorLogId"/><!-- Key -->
<html:hidden property="consultPgmErrorCommonDTO.filterIsCheckId"/>
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
				<div class="field">
                    <label><bean:message key="LABEL.date"/></label>
                    <div class="calendar_wrap">
                        <div class="input_box input_carendar">
                            <html:text property="consultPgmErrorCommonDTO.filterStartDate" tabindex="10" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="consultPgmErrorCommonDTO.filterEndDate" tabindex="20" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>
                <!-- 확인여부 -->
				<div class="field">
					<label><bean:message key="LABEL.check"/></label>
					<div class="input_box">
						<html:text property="consultPgmErrorCommonDTO.filterIsCheckDesc" tabindex="110" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
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
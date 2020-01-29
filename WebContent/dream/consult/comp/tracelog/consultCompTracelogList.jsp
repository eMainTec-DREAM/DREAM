<%--===========================================================================
Screen Trace List
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.comp.tracelog.action.ConsultCompTracelogListAction" %>
<%@ page import="dream.consult.comp.tracelog.action.ConsultCompTracelogDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- Screen Trace -->
<title><bean:message key='MENU.SCREENTRACE'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="consultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

var compAc, pageAc, menuAc;

function loadPage() 
{
    initGrid();
    
    compAc = new autoC({"consultCompTracelogCommonDTO.filterCompDesc":"description"});
    compAc.setTable("TACOMP");
    compAc.setAcResultMap({
        "consultCompTracelogCommonDTO.filterCompNo":"comp_no"
    });
    compAc.init();
    
    pageAc = new autoC({"consultCompTracelogCommonDTO.filterPageDesc":"description"});
    pageAc.setTable("TAPAGE");
    pageAc.setAcResultMap({
    	"consultCompTracelogCommonDTO.filterPageId":"page_id",
    	"consultCompTracelogCommonDTO.filterFileName":"file_name"
    });
    pageAc.init();  
    
    menuAc = new autoC({"consultCompTracelogCommonDTO.filterMenuDesc":"description"});
    menuAc.setTable("TACMENU");
    menuAc.setAcResultMap({
        "consultCompTracelogCommonDTO.filterMenuId":"MENU_ID"
    });
    menuAc.init();
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
    	consultCompTracelogListForm.elements['consultCompTracelogCommonDTO.scrnTraceLogId'].value = "";
    	return sortColumn("consultCompTracelogList", this, consultCompTracelogListForm, "SCRNTRACELOGID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/consultCompTracelogList.do";
    consultCompTracelogListForm.elements['strutsAction'].value = '<%=ConsultCompTracelogListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(consultCompTracelogListForm), "SCRNTRACELOGID","Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_scrnTraceLogId)
{
	consultCompTracelogListForm.elements['consultCompTracelogCommonDTO.scrnTraceLogId'].value = _scrnTraceLogId;
	findGridList('ReloadRow');
	consultCompTracelogListForm.elements['consultCompTracelogCommonDTO.scrnTraceLogId'].value = "";
}

function goSearch()
{
	consultCompTracelogListForm.elements['consultCompTracelogCommonDTO.scrnTraceLogId'].value = "";	// 검색시 Tab 이동Key Clear
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
	consultCompTracelogListForm.elements['consultCompTracelogCommonDTO.scrnTraceLogId'].value =  getValueById(myGrid, selectedId,'SCRNTRACELOGID');  
	goCommonTabPage(consultCompTracelogListForm, <%= ConsultCompTracelogDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('consultCompTracelogDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    consultCompTracelogListForm.elements['consultCompTracelogCommonDTO.scrnTraceLogId'].value =  getValueById(myGrid, selectedId,'SCRNTRACELOGID'); 
    consultCompTracelogListForm.elements['strutsAction'].value = '<%=ConsultCompTracelogDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(consultCompTracelogListForm), 'consultCompTracelogDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "consultCompTracelogDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	consultCompTracelogListForm.elements['consultCompTracelogCommonDTO.scrnTraceLogId'].value = "";
    goCommonTabPage(consultCompTracelogListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'SCRNTRACELOGID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    consultCompTracelogListForm.strutsAction.value = '<%=ConsultCompTracelogListAction.LIST_DELETE%>';
    var url = contextPath + "/consultCompTracelogList.do";
    
    $.post(url,FormQueryString(consultCompTracelogListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('consultCompTracelogDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	consultCompTracelogListForm.elements['consultCompTracelogCommonDTO.scrnTraceLogId'].value = "";
	excelServerAction("consultCompTracelogList", consultCompTracelogListForm );  
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/consultCompTracelogList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="consultCompTracelogCommonDTO.scrnTraceLogId"/><!-- Key -->
<html:hidden property="consultCompTracelogCommonDTO.filterCompNo"/>
<html:hidden property="consultCompTracelogCommonDTO.filterPageId"/>
<html:hidden property="consultCompTracelogCommonDTO.filterFileName"/>
<html:hidden property="consultCompTracelogCommonDTO.filterMenuId"/>
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
				<!-- 회사 -->
				<div class="field">
                    <label><bean:message key="LABEL.compDesc"/></label>
                    <div class="input_box">
                         <html:text property="consultCompTracelogCommonDTO.filterCompDesc" tabindex="10"/>
                         <p class="open_spop">
                             <a><span>조회</span></a>
                         </p>
                    </div>
                </div>
				<!-- 화면명 -->
				<div class="field">
		              <label><bean:message key="LABEL.pageDesc"/></label>
		              <div class="input_box">
		              		<html:text property="consultCompTracelogCommonDTO.filterPageDesc" tabindex="20"/>
		              		<p class="open_spop">
		                       <a>
		                           <span>조회</span>
		                       </a>
		                    </p>
		              </div>
	             </div>
				<!-- Object Id -->
				<div class="field">
					<label><bean:message key="LABEL.objectId"/></label>
					<div class="input_box">
						<html:text property="consultCompTracelogCommonDTO.filterObjectId" tabindex="40"/>
					</div>
				</div>
				<!-- 매뉴 -->
           	    <div class="field">
	               <label><bean:message key="LABEL.menuName"/></label>
               	   <div class="input_box">
               	   		<html:text property="consultCompTracelogCommonDTO.filterMenuDesc" tabindex="50"/>
               	   		<p class="open_spop"><a></a></p>
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
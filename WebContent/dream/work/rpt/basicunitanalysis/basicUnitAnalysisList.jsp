<%--===========================================================================
원단위분석
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.basicunitanalysis.action.BasicUnitAnalysisListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 원단위분석 -->
<title><bean:message key='MENU.basicUnitAnalysis'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var plantAc;

function loadPage() 
{
	basicUnitAnalysisListForm.elements['basicUnitAnalysisCommonDTO.filterStartDate'].value = getYear()+"-01";
	basicUnitAnalysisListForm.elements['basicUnitAnalysisCommonDTO.filterEndDate'].value   = getYear()+"-"+(getMonth());
	
	initGrid();
	
	//공장명 
    plantAc = new autoC({"basicUnitAnalysisCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcResultMap({
        "basicUnitAnalysisCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}


/**
 * 검색 클릭시 호출
 */
function goSearch()
{
    findGridList('Search');
    basicUnitAnalysisListForm.elements['basicUnitAnalysisDetailDTO.startDate'].value = basicUnitAnalysisListForm.elements['basicUnitAnalysisCommonDTO.filterStartDate'].value;
    basicUnitAnalysisListForm.elements['basicUnitAnalysisDetailDTO.endDate'].value = basicUnitAnalysisListForm.elements['basicUnitAnalysisCommonDTO.filterEndDate'].value;
}

function findGridList(sheetAction)
{
	if(checkValidation()) return;
	
	var form = document.basicUnitAnalysisListForm;	
	form.strutsAction.value = '<%=BasicUnitAnalysisListAction.BASIC_UNIT_ANALYSIS_LIST_FIND%>';
	
	var url = contextPath + "/basicUnitAnalysisList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(basicUnitAnalysisListForm), "PLANTID", "Y");
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
	var form = document.basicUnitAnalysisListForm;
	
	form.elements['basicUnitAnalysisDetailDTO.plantId'].value = getValueById(myGrid, selectedId,'PLANTID');
	form.elements['basicUnitAnalysisDetailDTO.plantDesc'].value = getValueById(myGrid, selectedId,'PLANTDESC');
	
	form.elements['basicUnitAnalysisDetailDTO.stdCheckPointDesc'].value = getValueById(myGrid, selectedId,'STDCHECKPOINTDESC');
	
	goCommonTabPage(form, '' , pageId);
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('basicUnitAnalysisDetailList');
	goTabPage('basicUnitAnalysisDetailChart');
}

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	  if(checkValidation()) return;
  	excelServerAction("basicUnitAnalysisList", basicUnitAnalysisListForm);
  }
 

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/basicUnitAnalysisList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="basicUnitAnalysisCommonDTO.filterPlantId"/>

<html:hidden property="basicUnitAnalysisDetailDTO.plantId"/>
<html:hidden property="basicUnitAnalysisDetailDTO.plantDesc"/>
<html:hidden property="basicUnitAnalysisDetailDTO.stdCheckPointDesc"/>
<html:hidden property="basicUnitAnalysisDetailDTO.startDate"/>
<html:hidden property="basicUnitAnalysisDetailDTO.endDate"/>

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
				<!-- 월 -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.month"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="basicUnitAnalysisCommonDTO.filterStartDate" tabindex="10" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="basicUnitAnalysisCommonDTO.filterEndDate" tabindex="20" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 공장  -->
				<div class="field">
					<label><bean:message key="LABEL.plant"/></label>
					<div class="input_box">
						<html:text property="basicUnitAnalysisCommonDTO.filterPlantDesc" tabindex="30"/>
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
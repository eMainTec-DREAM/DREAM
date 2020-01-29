<%--===========================================================================
고장TOP(사용부서)
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.rpt.lcc.usdept.action.AssetRptLccUsDeptListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 고장TOP(설비) -->
<title><bean:message key='MENU.LCCUSAGEDEPT'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var plantAc,uDeptAc;

var selectedCid, selectedRowId;

function loadPage() 
{
	if(window.name != "LINKED_POPUP")
	{
		var startDate = assetRptLccUsDeptListForm.elements['assetRptLccUsDeptCommonDTO.filterStartDate'].value;
		var endDate = assetRptLccUsDeptListForm.elements['assetRptLccUsDeptCommonDTO.filterEndDate'].value;
		if((startDate=="" || startDate==null)&&(endDate=="" || endDate==null)) {
			assetRptLccUsDeptListForm.elements['assetRptLccUsDeptCommonDTO.filterStartDate'].value = getYear()+"-01";
			assetRptLccUsDeptListForm.elements['assetRptLccUsDeptCommonDTO.filterEndDate'].value   = getYear()+"-"+(getMonth());
		}
		
		//공장명
	    if(loginUser.filterPlant!='null'){
	    	assetRptLccUsDeptListForm.elements['assetRptLccUsDeptCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
	    	assetRptLccUsDeptListForm.elements['assetRptLccUsDeptCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
		}
	}
	
    if(typeof afterloadPage == "function")
		afterloadPage();
    
	initGrid();
	
    uDeptAc = new autoC({"assetRptLccUsDeptCommonDTO.filterUsageDeptDesc":"description"});
    uDeptAc.setTable("TADEPT");
    uDeptAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   });
    uDeptAc.setAcResultMap({
        "assetRptLccUsDeptCommonDTO.filterUsageDept":"dept_id"
    });
    uDeptAc.setAcDConditionMap({
    	"plant" : "assetRptLccUsDeptCommonDTO.filterPlantId"
    });
    uDeptAc.init();

 	// 공장코드
	plantAc = new autoC({"assetRptLccUsDeptCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "assetRptLccUsDeptCommonDTO.filterPlantId":"plant"
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
		selectedRowId = rowId;
		selectedCid = columnId;
		goOpen();
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
   		return sortColumn("assetRptLccUsDeptList", this, assetRptLccUsDeptListForm, "USAGEDEPTID", ind, direction);
	});

	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
    assetRptLccUsDeptListForm.elements['assetRptLccUsDeptDetailDTO.startDate'].value = assetRptLccUsDeptListForm.elements['assetRptLccUsDeptCommonDTO.filterStartDate'].value;
    assetRptLccUsDeptListForm.elements['assetRptLccUsDeptDetailDTO.endDate'].value = assetRptLccUsDeptListForm.elements['assetRptLccUsDeptCommonDTO.filterEndDate'].value;
    assetRptLccUsDeptListForm.elements['assetRptLccUsDeptDetailDTO.plantId'].value = assetRptLccUsDeptListForm.elements['assetRptLccUsDeptCommonDTO.filterPlantId'].value;
    assetRptLccUsDeptListForm.elements['assetRptLccUsDeptDetailDTO.plantDesc'].value = assetRptLccUsDeptListForm.elements['assetRptLccUsDeptCommonDTO.filterPlantDesc'].value;
    findGridList('Search');   
}

function findGridList(sheetAction)
{
	if(checkValidation()) return;
	
	var form = document.assetRptLccUsDeptListForm;	
	form.strutsAction.value = '<%=AssetRptLccUsDeptListAction.LCC_EQUIP_LIST_FIND %>';
	
	var url = contextPath + "/assetRptLccUsDeptList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(assetRptLccUsDeptListForm), "USAGEDEPTID", "Y");
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
	var form = document.assetRptLccUsDeptListForm;
	
	form.elements['assetRptLccUsDeptDetailDTO.usageDeptId'].value = getValueById(myGrid, selectedId,'USAGEDEPTID');
	form.elements['assetRptLccUsDeptDetailDTO.usageDeptDesc'].value = getValueById(myGrid, selectedId,'USAGEDEPTDESC');
	goCommonTabPage(form, '' , pageId);
}

/**
 * 상세열기
 */
 function goOpen()
 {
	goTabPage('assetRptLccUsDeptDetailList');
	goTabPage('assetRptLccUsDeptDetailChart');
	goTabPage('assetRptLccUsDeptWorkTimeDetailChart');
	//보전비용 차트
	goTabPage('assetRptLccUsDeptMaintAmtDetailChart');
}

/**
 * 엑셀 다운.
*/
function goExcel()
{
	excelServerAction("assetRptLccUsDeptList", assetRptLccUsDeptListForm );
}

/*
 * 고장이력 보기
 */
function goEqbmLink()
{
	if(typeof selectedRowId == "undefined" || "" == selectedRowId)
 	{
 		alertMessage1('<bean:message key="MESSAGE.MSG212"/>');
 		return;
 	}

 	var fromDate = assetRptLccUsDeptListForm.elements['assetRptLccUsDeptCommonDTO.filterStartDate'].value.replace("-","")+"01";
 	var toDate = assetRptLccUsDeptListForm.elements['assetRptLccUsDeptCommonDTO.filterEndDate'].value.replace("-","")+"31";
 	var usageDeptId   = getValueById(myGrid, selectedRowId,'USAGEDEPTID');
 	var usageDeptDesc = getValueById(myGrid, selectedRowId,'USAGEDEPTDESC');
 	var woStatus = "C";
 	
 	goEqbmList('', '', '', '', fromDate, toDate, woStatus, usageDeptId);
}
   
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assetRptLccUsDeptList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="assetRptLccUsDeptCommonDTO.filterPlantId"/>
<html:hidden property="assetRptLccUsDeptCommonDTO.filterUsageDept"/>
<html:hidden property="assetRptLccUsDeptDetailDTO.startDate"/>
<html:hidden property="assetRptLccUsDeptDetailDTO.endDate"/>
<html:hidden property="assetRptLccUsDeptDetailDTO.chartValue"/>
<html:hidden property="assetRptLccUsDeptDetailDTO.usageDeptId"/>
<html:hidden property="assetRptLccUsDeptDetailDTO.usageDeptDesc"/>
<html:hidden property="assetRptLccUsDeptDetailDTO.plantId"/>
<html:hidden property="assetRptLccUsDeptDetailDTO.plantDesc"/>

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
							<html:text property="assetRptLccUsDeptCommonDTO.filterStartDate" tabindex="10" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="assetRptLccUsDeptCommonDTO.filterEndDate" tabindex="20" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 사용부서  -->
				<div class="field">
					<label><bean:message key="LABEL.usedDept"/></label>
					<div class="input_box">
						<html:text property="assetRptLccUsDeptCommonDTO.filterUsageDeptDesc" tabindex="30"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 공장명  -->
				<div class="field">
					<label><bean:message key="LABEL.plantDesc"/></label>
					<div class="input_box">
						<html:text property="assetRptLccUsDeptCommonDTO.filterPlantDesc" tabindex="40"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<c:set var="filePath" value="enhance/${compName}/asset/rpt/lcc/usdept/assetRptLccUsDeptList_${compNo}.jsp" />
				<c:if test="${udf:isExist(filePath)}">
					<c:import charEncoding="UTF-8" url="/enhance/${compName}/asset/rpt/lcc/usdept/assetRptLccUsDeptList_${compNo}.jsp"></c:import>
				</c:if>
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
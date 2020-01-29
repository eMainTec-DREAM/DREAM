<%--===========================================================================
에너지사용량(일별)
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.dailyeng.action.WorkRptDailyEngListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 에너지사용량(일별) -->
<title><bean:message key='MENU.DAILYENG'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var plantAc;
function loadPage() 
{
	workRptDailyEngListForm.elements['workRptDailyEngCommonDTO.filterStartDate'].value = getMinusMonth2(new Date(), -1); 
	workRptDailyEngListForm.elements['workRptDailyEngCommonDTO.filterEndDate'].value   = getToday();
	//공장
	workRptDailyEngListForm.elements['workRptDailyEngCommonDTO.filterPlant'].value = loginUser.filterPlant;
	workRptDailyEngListForm.elements['workRptDailyEngCommonDTO.filterPlantDesc'].value = loginUser.filterPlantDesc;
	
	workRptDailyEngListForm.elements['workRptDailyEngCommonDTO.filterSeparation'].value   = "L5";
	workRptDailyEngListForm.elements['workRptDailyEngCommonDTO.filterSeparationDesc'].value   = "<bean:message key='CODESET.DW_EMS_LTYPE.L5'/>";
	valSysDir('workRptDailyEngCommonDTO.filterSeparation', 'workRptDailyEngCommonDTO.filterSeparationDesc', 'DW_EMS_LTYPE', true);
	
	// 공장코드
	plantAc = new autoC({"workRptDailyEngCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "workRptDailyEngCommonDTO.filterPlant":"plant"
    });
    plantAc.init();
	initGrid();
	
	acSysDesc("workRptDailyEngCommonDTO.filterSeparationDesc","workRptDailyEngCommonDTO.filterSeparation","DW_EMS_LTYPE",true);
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
	for(var i = 0; myGrid.getColumnsNum() > i; i++)
	{
		if(myGrid.getColType(i) == "ron" ) 
		{
			myGrid.setNumberFormat("0,000.00",i,".",",");
		}
	}
	
    findGridList('Search');   
}

function findGridList(sheetAction)
{
	if(checkValidation()) return;
	
	var form = document.workRptDailyEngListForm;	
	form.strutsAction.value = '<%=WorkRptDailyEngListAction.LIST_FIND %>';
	
	var url = contextPath + "/workRptDailyEngList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workRptDailyEngListForm), "RESULTID");
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
	var form = document.workRptDailyEngListForm;
	
	form.elements['workRptDailyEngDetailListDTO.plantId'].value = getValueById(myGrid, selectedId,'PLANTID');
	form.elements['workRptDailyEngDetailListDTO.plantDesc'].value = getValueById(myGrid, selectedId,'PLANTDESC');
	form.elements['workRptDailyEngDetailListDTO.eqLocId'].value = getValueById(myGrid, selectedId,'EQLOCID');
	form.elements['workRptDailyEngDetailListDTO.eqLocDesc'].value = getValueById(myGrid, selectedId,'EQLOCDESC');
	form.elements['workRptDailyEngDetailListDTO.usageDeptId'].value = getValueById(myGrid, selectedId,'USAGEDEPTID');
	form.elements['workRptDailyEngDetailListDTO.usageDeptDesc'].value = getValueById(myGrid, selectedId,'USAGEDEPTDESC');
	form.elements['workRptDailyEngDetailListDTO.eqCtgId'].value = getValueById(myGrid, selectedId,'EQCTGID');
	form.elements['workRptDailyEngDetailListDTO.eqCtgDesc'].value = getValueById(myGrid, selectedId,'EQCTGDESC');
	form.elements['workRptDailyEngDetailListDTO.equipId'].value = getValueById(myGrid, selectedId,'EQUIPID');
	form.elements['workRptDailyEngDetailListDTO.equipDesc'].value = getValueById(myGrid, selectedId,'EQUIPDESC');
	
	goCommonTabPage(form, '' , pageId);
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('workRptDailyEngDetailList');
 	goTabPage('workRptDailyEngDetailChart');
}

/**
 * 엑셀 다운.
 */
 function goExcel()
 {
//  	excelAction(myGrid);
 	excelServerAction("workRptDailyEngList", workRptDailyEngListForm );  
 }
 
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workRptDailyEngList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="workRptDailyEngCommonDTO.filterSeparation"/>
<html:hidden property="workRptDailyEngCommonDTO.filterPlant"/>
<html:hidden property="workRptDailyEngDetailListDTO.plantId"/>
<html:hidden property="workRptDailyEngDetailListDTO.eqLocId"/>
<html:hidden property="workRptDailyEngDetailListDTO.eqLocDesc"/>
<html:hidden property="workRptDailyEngDetailListDTO.usageDeptId"/>
<html:hidden property="workRptDailyEngDetailListDTO.usageDeptDesc"/>
<html:hidden property="workRptDailyEngDetailListDTO.eqCtgId"/>
<html:hidden property="workRptDailyEngDetailListDTO.eqCtgDesc"/>
<html:hidden property="workRptDailyEngDetailListDTO.equipId"/>
<html:hidden property="workRptDailyEngDetailListDTO.equipDesc"/>
<html:hidden property="workRptDailyEngDetailListDTO.plantDesc"/>

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
				<!-- 일자 -->
				<div class="field">
					<label><bean:message key="LABEL.date"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="workRptDailyEngCommonDTO.filterStartDate" tabindex="10" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="workRptDailyEngCommonDTO.filterEndDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 조회구분  -->
				<div class="field">
					<label><bean:message key="LABEL.separation"/></label>
					<div class="input_box">
						<html:text property="workRptDailyEngCommonDTO.filterSeparationDesc" tabindex="30"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
			<!-- 공장 -->
			<div class="field">
				<label><bean:message key="LABEL.plant"/></label>
				<div class="input_box">
					<html:text property="workRptDailyEngCommonDTO.filterPlantDesc" tabindex="40"/>
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
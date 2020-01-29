<%--===========================================================================
계획보전율(위치)
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.pm.ratio.action.WorkRptPmRatioListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 계획보전율(위치) -->
<title><bean:message key='MENU.PMRATIO'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var eqLocAc;

function loadPage() 
{
	workRptPmRatioListForm.elements['workRptPmRatioCommonDTO.filterStartDate'].value = getYear()+"-01";
	workRptPmRatioListForm.elements['workRptPmRatioCommonDTO.filterEndDate'].value   = getYear()+"-"+(getMonth());
	initGrid();
	
	eqLocAc = new autoC({"workRptPmRatioCommonDTO.filterEqLocDesc":"full_desc"});
    eqLocAc.setTable("TAEQLOC");
    eqLocAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   });
    eqLocAc.setAcResultMap({
        "workRptPmRatioCommonDTO.filterEqLocId":"eqloc_id"
    });
    eqLocAc.init();
    
    acSysDesc("workRptPmRatioCommonDTO.filterEqLocLevelDesc","workRptPmRatioCommonDTO.filterEqLocLevel","EQLOC_LVL_TYPE", true);
    
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
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	return sortColumn("workRptPmRatioList", this, workRptPmRatioListForm, "EQLOCID", ind, direction);
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
    workRptPmRatioListForm.elements['workRptPmRatioDetailDTO.startDate'].value = workRptPmRatioListForm.elements['workRptPmRatioCommonDTO.filterStartDate'].value;
    workRptPmRatioListForm.elements['workRptPmRatioDetailDTO.endDate'].value = workRptPmRatioListForm.elements['workRptPmRatioCommonDTO.filterEndDate'].value;
}

function findGridList(sheetAction)
{
	if(checkValidation()) return;
	
	var form = document.workRptPmRatioListForm;	
	form.strutsAction.value = '<%=WorkRptPmRatioListAction.PM_RATIO_LIST_FIND %>';
	
	var url = contextPath + "/workRptPmRatioList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workRptPmRatioListForm), "EQLOCID", "Y");
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
	var form = document.workRptPmRatioListForm;
	
	form.elements['workRptPmRatioDetailDTO.eqLocId'].value = getValueById(myGrid, selectedId,'EQLOCID');
	form.elements['workRptPmRatioDetailDTO.eqLocDesc'].value = getValueById(myGrid, selectedId,'EQLOCDESC');
	
	var chartDataset = [{"key":"<bean:message key='LABEL.schedMaintCnt'/>","value":getValueById(myGrid, selectedId,'schedMaintCnt'),"color":"#1DDB16"},
	                    		{"key":"<bean:message key='LABEL.prevMaintCnt'/>","value":getValueById(myGrid, selectedId,'prevMaintCnt'),"color":"#0054FF"},
	                    		{"key":"<bean:message key='LABEL.brkMaintCnt'/>","value":getValueById(myGrid, selectedId,'brkMaintCnt'),"color":"#FF0000"}];
	
	form.elements['workRptPmRatioDetailDTO.chartDataset'].value = JSON.stringify(chartDataset);
	
	goCommonTabPage(form, '' , pageId);
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('workRptPmRatioDetailList');
	goTabPage('workRptPmRatioDetailChart');
}

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	  if(checkValidation()) return;
  	excelServerAction("workRptPmRatioList", workRptPmRatioListForm);
  }
 
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workRptPmRatioList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="workRptPmRatioCommonDTO.filterEqLocId"/>
<html:hidden property="workRptPmRatioCommonDTO.filterEqLocLevel"/>

<html:hidden property="workRptPmRatioDetailDTO.eqLocId"/>
<html:hidden property="workRptPmRatioDetailDTO.eqLocDesc"/>
<html:hidden property="workRptPmRatioDetailDTO.startDate"/>
<html:hidden property="workRptPmRatioDetailDTO.endDate"/>
<html:hidden property="workRptPmRatioDetailDTO.chartDataset"/>

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
							<html:text property="workRptPmRatioCommonDTO.filterStartDate" tabindex="10" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="workRptPmRatioCommonDTO.filterEndDate" tabindex="20" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 설비위치  -->
				<div class="field">
					<label><bean:message key="LABEL.location"/></label>
					<div class="input_box">
						<html:text property="workRptPmRatioCommonDTO.filterEqLocDesc" tabindex="30"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 위치Level  -->
				<div class="field">
					<label><bean:message key="LABEL.locLevel"/></label>
					<div class="input_box">
						<html:text property="workRptPmRatioCommonDTO.filterEqLocLevelDesc" tabindex="40"/>
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
				<div class="stitle_tx"><bean:message key="TAB.pmRatio"/></div>
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
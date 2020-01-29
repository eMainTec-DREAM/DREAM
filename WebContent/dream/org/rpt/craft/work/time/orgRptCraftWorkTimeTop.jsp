<%--===========================================================================
작업자 작업시간 Top
author  js.lee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.org.rpt.craft.work.time.action.OrgRptCraftWorkTimeTopAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 작업자 작업시간 Top -->
<title><bean:message key='PAGE.orgRptCraftWorkTimeTop'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var deptAc;
var plantAc;
var wkCtrAc;
var selectedGridId;

function loadPage() 
{
	setInitVal('orgRptCraftWorkTimeTopCommonDTO.filterPlantId', loginUser.filterPlant);
	setInitVal('orgRptCraftWorkTimeTopCommonDTO.filterPlantDesc', loginUser.filterPlantDesc);
	setInitVal('orgRptCraftWorkTimeTopCommonDTO.filterStartDate', getYear()+"-01");
	setInitVal('orgRptCraftWorkTimeTopCommonDTO.filterEndDate', getYear()+"-"+(getMonth()));
	setInitVal('orgRptCraftWorkTimeTopCommonDTO.filterDeptId', loginUser.filterDeptId);
	setInitVal('orgRptCraftWorkTimeTopCommonDTO.filterDeptDesc', loginUser.filterDeptDesc);
	setInitVal('orgRptCraftWorkTimeTopCommonDTO.filterWkCtrId', loginUser.filterWkCtrId);
	setInitVal('orgRptCraftWorkTimeTopCommonDTO.filterWkCtrDesc', loginUser.filterWkCtrDesc);
	
	initGrid();
	initChart();
	
	// 부서 AC
	deptAc = new autoC({"orgRptCraftWorkTimeTopCommonDTO.filterDeptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   });
    deptAc.setAcResultMap({
        "orgRptCraftWorkTimeTopCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "orgRptCraftWorkTimeTopCommonDTO.filterPlantId"
    });
    deptAc.init();
    
 	// 공장 AC
	plantAc = new autoC({"orgRptCraftWorkTimeTopCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "orgRptCraftWorkTimeTopCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
    // 작업그룹 AC
    wkCtrAc = new autoC({"orgRptCraftWorkTimeTopCommonDTO.filterWkCtrDesc":"description"});
    wkCtrAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    wkCtrAc.setTable("TAWKCTR");
    wkCtrAc.setAcResultMap({
        "orgRptCraftWorkTimeTopCommonDTO.filterWkCtrId":"wkctr_id"
    });
    wkCtrAc.init();
    
}

var myGrid;
var myChart;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		selectedGridId = rowId;
		goOpen();
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	return sortColumn("orgRptCraftWorkTimeTop", this, orgRptCraftWorkTimeTopForm, "EMPID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

function initChart(){
	myChart =  new dhtmlXChart({
		view:"stackedBar",
		container:"chartbox",
		value:"#TOTWORKTIME#",
		color: "#ffbf1e",
		gradient:"falling",
		tooltip:{
			template:"#TOTWORKTIME#"
		},
		xAxis:{
			template:"'#EMPDESC#",
			step:1
		},
		yAxis:{}
	});
}

$(window).resize(function(){
	myChart.resize();
});

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	findChart();
    findGridList('Search');
    orgRptCraftWorkTimeTopForm.elements['orgRptCraftWorkTimeMonthlyDTO.startDate'].value = dateToData(orgRptCraftWorkTimeTopForm.elements['orgRptCraftWorkTimeTopCommonDTO.filterStartDate'].value);
    orgRptCraftWorkTimeTopForm.elements['orgRptCraftWorkTimeMonthlyDTO.endDate'].value = dateToData(orgRptCraftWorkTimeTopForm.elements['orgRptCraftWorkTimeTopCommonDTO.filterEndDate'].value);
    orgRptCraftWorkTimeTopForm.elements['orgRptCraftWorkTimeDailyDTO.startDate'].value = dateToData(orgRptCraftWorkTimeTopForm.elements['orgRptCraftWorkTimeTopCommonDTO.filterStartDate'].value);
    orgRptCraftWorkTimeTopForm.elements['orgRptCraftWorkTimeDailyDTO.endDate'].value = dateToData(orgRptCraftWorkTimeTopForm.elements['orgRptCraftWorkTimeTopCommonDTO.filterEndDate'].value);
    

    goClose('orgRptCraftWorkTimeMonthly');
    goClose('orgRptCraftWorkTimeDaily');
    
}

function findChart()
{
	if(checkValidation()) return;
	
	var url = contextPath + "/orgRptCraftWorkTimeTop.do";
	var form = document.orgRptCraftWorkTimeTopForm;	
	form.strutsAction.value = '<%=OrgRptCraftWorkTimeTopAction.CHART_FIND %>';
	
	$.post(url,FormQueryString(orgRptCraftWorkTimeTopForm), function(_data){
		myChart.clearAll();
    	myChart.parse(_data,"json");
    });
	
}

function findGridList(sheetAction)
{
	if(checkValidation()) return;
	
	var form = document.orgRptCraftWorkTimeTopForm;	
	form.strutsAction.value = '<%=OrgRptCraftWorkTimeTopAction.LIST_FIND %>';
	
	var url = contextPath + "/orgRptCraftWorkTimeTop.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(orgRptCraftWorkTimeTopForm), "DEPTID", "Y");
	
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
	var form = document.orgRptCraftWorkTimeTopForm;
	
	form.elements['orgRptCraftWorkTimeMonthlyDTO.empId'].value = getValueById(myGrid, selectedId,'EMPID');
	form.elements['orgRptCraftWorkTimeMonthlyDTO.empDesc'].value = getValueById(myGrid, selectedId,'EMPDESC');
	form.elements['orgRptCraftWorkTimeDailyDTO.empId'].value = getValueById(myGrid, selectedId,'EMPID');
	form.elements['orgRptCraftWorkTimeDailyDTO.empDesc'].value = getValueById(myGrid, selectedId,'EMPDESC');
	goCommonTabPage(form, '' , pageId);
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('orgRptCraftWorkTimeMonthly');
	goTabPage('orgRptCraftWorkTimeDaily');
}

/**
  * 엑셀 다운.
  */
  function goExcel()
  {
	  if(checkValidation()) return;
  	excelServerAction("orgRptCraftWorkTimeTop", orgRptCraftWorkTimeTopForm);
  }
 
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/orgRptCraftWorkTimeTop.do">
<html:hidden property="strutsAction"/>
<html:hidden property="orgRptCraftWorkTimeTopCommonDTO.filterDeptId"/>
<html:hidden property="orgRptCraftWorkTimeTopCommonDTO.filterPlantId"/>
<html:hidden property="orgRptCraftWorkTimeTopCommonDTO.filterWkCtrId"/>
<html:hidden property="orgRptCraftWorkTimeMonthlyDTO.empId"/>
<html:hidden property="orgRptCraftWorkTimeMonthlyDTO.empDesc"/>
<html:hidden property="orgRptCraftWorkTimeMonthlyDTO.startDate"/>
<html:hidden property="orgRptCraftWorkTimeMonthlyDTO.endDate"/>
<html:hidden property="orgRptCraftWorkTimeDailyDTO.empId"/>
<html:hidden property="orgRptCraftWorkTimeDailyDTO.empDesc"/>
<html:hidden property="orgRptCraftWorkTimeDailyDTO.startDate"/>
<html:hidden property="orgRptCraftWorkTimeDailyDTO.endDate"/>

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
							<html:text property="orgRptCraftWorkTimeTopCommonDTO.filterStartDate" tabindex="10" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="orgRptCraftWorkTimeTopCommonDTO.filterEndDate" tabindex="20" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 공장  -->
                <div class="field">
                    <label><bean:message key="LABEL.plant"/></label>
                    <div class="input_box">
						<html:text property="orgRptCraftWorkTimeTopCommonDTO.filterPlantDesc" tabindex="50" />
						<p class="open_spop">
                           <a>
                               <span>조회</span>
                           </a>
                        </p>
                    </div>
                </div>
				<!-- 부서  -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="orgRptCraftWorkTimeTopCommonDTO.filterDeptDesc" tabindex="40"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 작업그룹  -->
                <div class="field">
                    <label><bean:message key="LABEL.wkCtr"/></label>
                    <div class="input_box">
						<html:text property="orgRptCraftWorkTimeTopCommonDTO.filterWkCtrDesc" tabindex="30" />
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
                <div class="stitle_tx"><bean:message key="LABEL.workTimeTop"/></div>
            </div>
            <div class="function_box">
                <div class="fb_group3">
                    <div class="sfb_wrap" style="display:none;">
                    </div>
                </div>

                <div class="fb_group2">
                </div>
            </div>
        </div>
        <div class="article_box">
            <div class="grid_area">
                <div id="chartbox" style="width:100%;height:270px;background-color:white;"></div>
            </div>
        </div>
    </div> <!--  end section_wrap -->	
	<div class="section_wrap">
		<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key='LABEL.workTime'/></div>
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
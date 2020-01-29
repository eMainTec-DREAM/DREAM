<%--===========================================================================
예방점검 이행율(담당자)
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.pmins.ach.action.WorkRptPminsAchListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 예방점검 이행율(담당자) -->
<title><bean:message key='MENU.PMINSACH'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 <style type="text/css">
.dhx_axis_item_x {
	padding-top:2px; 
	-webkit-transform-origin: 0 0;
    -moz-transform-origin: 0 0;
         -o-transform-origin: 0 0;
            transform-origin: 0 0; 
    -webkit-transform: rotate(-20deg);
       -moz-transform: rotate(-20deg);
         -o-transform: rotate(-20deg);
            transform: rotate(-20deg);
    margin-top:25px;  
}  
</style> 
<script language="javascript">
<!--//

var deptAc;
var empAc;
var plantAc;

function loadPage() 
{
	workRptPminsAchListForm.elements['workRptPminsAchCommonDTO.filterStartDate'].value = getYear()+"-01";
	workRptPminsAchListForm.elements['workRptPminsAchCommonDTO.filterEndDate'].value   = getYear()+"-"+(getMonth());
	
	//공장명
    if(loginUser.filterPlant!='null'){
    	workRptPminsAchListForm.elements['workRptPminsAchCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	workRptPminsAchListForm.elements['workRptPminsAchCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
	
	initGrid();
	initChart();
	
	deptAc = new autoC({"workRptPminsAchCommonDTO.filterDeptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   });
    deptAc.setAcResultMap({
        "workRptPminsAchCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "workRptPminsAchCommonDTO.filterPlantId"
    });
    deptAc.init();
    
    empAc = new autoC({"workRptPminsAchCommonDTO.filterEmpDesc":"emp_name"});
    empAc.setAcDisplay("EMP_NAME");
    empAc.setAcConditionMap({
        "comp_no":loginUser.compNo
       });
    empAc.setTable("TAEMP");
    empAc.setAcResultMap({
        "workRptPminsAchCommonDTO.filterEmpId":"emp_id"
    });
    empAc.setAcDConditionMap({
    	"dept_id" : "workRptPminsAchCommonDTO.filterDeptId",
    	"plant" : "workRptPminsAchCommonDTO.filterPlantId"
    });
    empAc.init();
    
 	// 공장코드
	plantAc = new autoC({"workRptPminsAchCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "workRptPminsAchCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
}

var myGrid;
var myChart;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		workRptPminsAchListForm.elements['workRptPminsAchDetailDTO.deptId'].value = "";
    	return sortColumn("workRptPminsAchList", this, workRptPminsAchListForm, "EMPID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

function initChart(){
	myChart =  new dhtmlXChart({
		view:"stackedBar",
		container:"chartbox",
		value:"#COMPLETED#",
		label:"#COMPLETED#",
		color: "#a7ee70",
		gradient:"falling",
		tooltip:{
			template:"#ACHIEVEMENT#"+"%"
		},
		xAxis:{
			template:"'#EMPDESC#"
		},
		yAxis:{},
		legend:{
			values:[{text:"<bean:message key='LABEL.planCnt'/>",color:"#dddddd"},{text:"<bean:message key='LABEL.completeCnt'/>",color:"#a7ee70"}],
			valign:"middle",
			align:"right",
			width:90,
			layout:"y"
		}
	});
	myChart.addSeries({
		value:"#INCOMPLETED#",
		label:"#INCOMPLETED#",
		color:"#dddddd"
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
    findGridList('Search');
    workRptPminsAchListForm.elements['workRptPminsAchDetailDTO.startDate'].value = workRptPminsAchListForm.elements['workRptPminsAchCommonDTO.filterStartDate'].value;
    workRptPminsAchListForm.elements['workRptPminsAchDetailDTO.endDate'].value = workRptPminsAchListForm.elements['workRptPminsAchCommonDTO.filterEndDate'].value;
}

function findGridList(sheetAction)
{
	if(checkValidation()) return;
	
	var form = document.workRptPminsAchListForm;	
	form.strutsAction.value = '<%=WorkRptPminsAchListAction.PMINS_ACH_LIST_FIND %>';
	
	var url = contextPath + "/workRptPminsAchList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workRptPminsAchListForm), "EMPID", "Y");
	
	$.post(url,FormQueryString(workRptPminsAchListForm), function(_data){
		myChart.clearAll();
    	myChart.parse(_data,"json");
    });
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
	var form = document.workRptPminsAchListForm;
	
	form.elements['workRptPminsAchDetailDTO.empId'].value = getValueById(myGrid, selectedId,'EMPID');
	form.elements['workRptPminsAchDetailDTO.empDesc'].value = getValueById(myGrid, selectedId,'EMPDESC');
	form.elements['workRptPminsAchDetailDTO.deptId'].value = getValueById(myGrid, selectedId,'DEPTID');
	
	goCommonTabPage(form, '' , pageId);
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('workRptPminsAchDetailList');
	goTabPage('workRptPminsAchDetailChart');
}

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
  	excelServerAction("workRptPminsAchList", workRptPminsAchListForm);
  }
 

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workRptPminsAchList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="workRptPminsAchCommonDTO.filterDeptId"/>
<html:hidden property="workRptPminsAchCommonDTO.filterPlantId"/>

<html:hidden property="workRptPminsAchDetailDTO.empId"/>
<html:hidden property="workRptPminsAchDetailDTO.empDesc"/>
<html:hidden property="workRptPminsAchDetailDTO.deptId"/>
<html:hidden property="workRptPminsAchDetailDTO.startDate"/>
<html:hidden property="workRptPminsAchDetailDTO.endDate"/>

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
							<html:text property="workRptPminsAchCommonDTO.filterStartDate" tabindex="10" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="workRptPminsAchCommonDTO.filterEndDate" tabindex="20" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 부서  -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="workRptPminsAchCommonDTO.filterDeptDesc" tabindex="30"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 담당자 -->
                <div class="field">
                    <label><bean:message key="LABEL.manager"/></label>
                    <div class="input_box">
                        <html:text property="workRptPminsAchCommonDTO.filterEmpDesc" tabindex="50" />
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                <!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
							<html:text property="workRptPminsAchCommonDTO.filterPlantDesc"
								tabindex="60" />
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
				<div class="stitle_tx"><bean:message key="PAGE.workRptPminsAchList"/></div>
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
				<div class="stitle_tx"><bean:message key="PAGE.workRptPminsAchList"/></div>
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
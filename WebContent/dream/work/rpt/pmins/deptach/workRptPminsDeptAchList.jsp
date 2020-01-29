<%--===========================================================================
예방점검 이행율(부서)
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.pmins.deptach.action.WorkRptPminsDeptAchListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 예방점검 이행율(부서) -->
<title><bean:message key='MENU.PMINSDEPTACH'/></title>
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
var plantAc;

function loadPage() 
{
	workRptPminsDeptAchListForm.elements['workRptPminsDeptAchCommonDTO.filterStartDate'].value = getYear()+"-01";
	workRptPminsDeptAchListForm.elements['workRptPminsDeptAchCommonDTO.filterEndDate'].value   = getYear()+"-"+(getMonth());
	
	//공장명
    if(loginUser.filterPlant!='null'){
    	workRptPminsDeptAchListForm.elements['workRptPminsDeptAchCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	workRptPminsDeptAchListForm.elements['workRptPminsDeptAchCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
	
	initGrid();
	initChart();
	
	deptAc = new autoC({"workRptPminsDeptAchCommonDTO.filterDeptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   });
    deptAc.setAcResultMap({
        "workRptPminsDeptAchCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "workRptPminsDeptAchCommonDTO.filterPlantId"
    });
    deptAc.init();
    
 	// 공장코드
	plantAc = new autoC({"workRptPminsDeptAchCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "workRptPminsDeptAchCommonDTO.filterPlantId":"plant"
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
		workRptPminsDeptAchListForm.elements['workRptPminsDeptAchDetailDTO.deptId'].value = "";
    	return sortColumn("workRptPminsDeptAchList", this, workRptPminsDeptAchListForm, "DEPTID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

function initChart(){
	myChart =  new dhtmlXChart({
		view:"stackedBar",
		container:"chartbox",
		value:"#COMPLETED#",
		color: "#a7ee70",
		gradient:"falling",
		tooltip:{
			template:"#ACHIEVEMENT#"+"%"
		},
		xAxis:{
			template:"'#DEPTDESC#"
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
    workRptPminsDeptAchListForm.elements['workRptPminsDeptAchDetailDTO.startDate'].value = workRptPminsDeptAchListForm.elements['workRptPminsDeptAchCommonDTO.filterStartDate'].value;
    workRptPminsDeptAchListForm.elements['workRptPminsDeptAchDetailDTO.endDate'].value = workRptPminsDeptAchListForm.elements['workRptPminsDeptAchCommonDTO.filterEndDate'].value;
}

function findGridList(sheetAction)
{
	if(checkValidation()) return;
	
	var form = document.workRptPminsDeptAchListForm;	
	form.strutsAction.value = '<%=WorkRptPminsDeptAchListAction.PMINS_ACH_LIST_FIND %>';
	
	var url = contextPath + "/workRptPminsDeptAchList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workRptPminsDeptAchListForm), "DEPTID", "Y");
	
	$.post(url,FormQueryString(workRptPminsDeptAchListForm), function(_data){
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
	var form = document.workRptPminsDeptAchListForm;
	
	form.elements['workRptPminsDeptAchDetailDTO.deptId'].value = getValueById(myGrid, selectedId,'DEPTID');
	form.elements['workRptPminsDeptAchDetailDTO.deptDesc'].value = getValueById(myGrid, selectedId,'DEPTDESC');
	
	goCommonTabPage(form, '' , pageId);
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('workRptPminsDeptAchDetailList');
	goTabPage('workRptPminsDeptAchDetailChart');
}

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
  	excelServerAction("workRptPminsDeptAchList", workRptPminsDeptAchListForm);
  }
 

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workRptPminsDeptAchList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="workRptPminsDeptAchCommonDTO.filterDeptId"/>
<html:hidden property="workRptPminsDeptAchCommonDTO.filterPlantId"/>

<html:hidden property="workRptPminsDeptAchDetailDTO.deptId"/>
<html:hidden property="workRptPminsDeptAchDetailDTO.deptDesc"/>
<html:hidden property="workRptPminsDeptAchDetailDTO.startDate"/>
<html:hidden property="workRptPminsDeptAchDetailDTO.endDate"/>

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
							<html:text property="workRptPminsDeptAchCommonDTO.filterStartDate" tabindex="10" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="workRptPminsDeptAchCommonDTO.filterEndDate" tabindex="20" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 부서  -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="workRptPminsDeptAchCommonDTO.filterDeptDesc" tabindex="30"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
                <!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
							<html:text property="workRptPminsDeptAchCommonDTO.filterPlantDesc"
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
				<div class="stitle_tx"><bean:message key="MENU.PMINSDEPTACH"/></div>
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
				<div class="stitle_tx"><bean:message key="MENU.PMINSDEPTACH"/></div>
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
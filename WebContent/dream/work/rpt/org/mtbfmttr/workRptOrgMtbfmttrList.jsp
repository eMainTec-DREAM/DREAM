<%--===========================================================================
조직별MTBF,MTTR
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.org.mtbfmttr.action.WorkRptOrgMtbfmttrListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 조직별MTBF,MTTR -->
<title><bean:message key='MENU.ORGMTBFMTTR'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var deptAc;
var plantAc;

function loadPage() 
{
	var startDate = workRptOrgMtbfmttrListForm.elements['workRptOrgMtbfmttrCommonDTO.filterStartDate'].value;
	var endDate = workRptOrgMtbfmttrListForm.elements['workRptOrgMtbfmttrCommonDTO.filterEndDate'].value;
	
	if((startDate=="" || startDate==null)&&(endDate=="" || endDate==null)) {
		workRptOrgMtbfmttrListForm.elements['workRptOrgMtbfmttrCommonDTO.filterStartDate'].value = getYear()+"-01";
		workRptOrgMtbfmttrListForm.elements['workRptOrgMtbfmttrCommonDTO.filterEndDate'].value   = getYear()+"-"+(getMonth());
	}
	
	//공장명
    if(loginUser.filterPlant!='null'){
    	workRptOrgMtbfmttrListForm.elements['workRptOrgMtbfmttrCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	workRptOrgMtbfmttrListForm.elements['workRptOrgMtbfmttrCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
	
	initGrid();
	initChart();
	
	// 부서
    deptAc = new autoC({"workRptOrgMtbfmttrCommonDTO.filterDeptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   });
    deptAc.setAcResultMap({
        "workRptOrgMtbfmttrCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "workRptOrgMtbfmttrCommonDTO.filterPlantId"
      , "dept_categ" : "workRptOrgMtbfmttrCommonDTO.filterDeptCategId"
    });
    deptAc.init();
    
 	// 공장코드
	plantAc = new autoC({"workRptOrgMtbfmttrCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "workRptOrgMtbfmttrCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
    // 조직구분
    acSysDesc("workRptOrgMtbfmttrCommonDTO.filterDeptCategDesc","workRptOrgMtbfmttrCommonDTO.filterDeptCategId","DEPT_CATEG");

}

var myGrid;
var myMtbfChart;
var myMttrChart;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		workRptOrgMtbfmttrListForm.elements['workRptOrgMtbfmttrDetailDTO.deptId'].value = "";
        return sortColumn("workRptOrgMtbfmttrList", this, workRptOrgMtbfmttrListForm, "deptId", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

function initChart(){
	myMtbfChart =  new dhtmlXChart({
		view:"bar",
		container:"mtbfchartbox",
		value:"#MTBFHOUR#",
		color: "#69ba00",
		gradient:"rising",
		tooltip:{
			template:"#ORGNAME#"+" ("+"#MTBFHOUR#"+")"
		},
		xAxis:{
			template:"'#ORGNAME#"
		},
		yAxis:{},
		origin:0,
		radius:0,
	});
	
	myMttrChart =  new dhtmlXChart({
		view:"bar",
		container:"mttrchartbox",
		value:"#MTTRHOUR#",
		color: "#69ba00",
		gradient:"rising",
		tooltip:{
			template:"#ORGNAME#"+" ("+"#MTTRHOUR#"+")"
		},
		xAxis:{
			template:"'#ORGNAME#"
		},
		yAxis:{},
		origin:0,
		radius:0,
	});

}

$(window).resize(function(){
	myMtbfChart.resize();
	myMttrChart.resize();
});

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
    findGridList('Search');   
    workRptOrgMtbfmttrListForm.elements['workRptOrgMtbfmttrDetailDTO.startDate'].value = workRptOrgMtbfmttrListForm.elements['workRptOrgMtbfmttrCommonDTO.filterStartDate'].value;
    workRptOrgMtbfmttrListForm.elements['workRptOrgMtbfmttrDetailDTO.endDate'].value = workRptOrgMtbfmttrListForm.elements['workRptOrgMtbfmttrCommonDTO.filterEndDate'].value;
}

function findGridList(sheetAction)
{
	if(checkValidation()) return;
	
	var form = document.workRptOrgMtbfmttrListForm;	
	form.strutsAction.value = '<%=WorkRptOrgMtbfmttrListAction.MTTRMTBF_EQUIP_LIST_FIND %>';
	
	var url = contextPath + "/workRptOrgMtbfmttrList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workRptOrgMtbfmttrListForm), "ITEMNO", "Y");
	
	$.post(url,FormQueryString(workRptOrgMtbfmttrListForm), function(_data){
		myMtbfChart.clearAll();
		myMttrChart.clearAll();
		myMtbfChart.parse(_data,"json");
		myMttrChart.parse(_data,"json");
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
	var form = document.workRptOrgMtbfmttrListForm;
	
	form.elements['workRptOrgMtbfmttrDetailDTO.deptId'].value = getValueById(myGrid, selectedId,'DEPTID');
	form.elements['workRptOrgMtbfmttrDetailDTO.deptDesc'].value = getValueById(myGrid, selectedId,'ORGNAME');
	
	form.elements['workRptOrgMtbfmttrDetailDTO.plantId'].value = form.elements['workRptOrgMtbfmttrCommonDTO.filterPlantId'].value
	form.elements['workRptOrgMtbfmttrDetailDTO.plantDesc'].value = form.elements['workRptOrgMtbfmttrCommonDTO.filterPlantDesc'].value
	
	goCommonTabPage(form, '' , pageId);
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('workRptOrgMtbfmttrDetailList');
	goTabPage('workRptOrgMtbfmttrDetailChart');
}

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	workRptOrgMtbfmttrListForm.elements['workRptOrgMtbfmttrDetailDTO.deptId'].value = "";
	excelServerAction("workRptOrgMtbfmttrList", workRptOrgMtbfmttrListForm );  
  }
 
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workRptOrgMtbfmttrList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="workRptOrgMtbfmttrCommonDTO.filterDeptId"/>
<html:hidden property="workRptOrgMtbfmttrCommonDTO.filterPlantId"/>
<html:hidden property="workRptOrgMtbfmttrCommonDTO.filterDeptCategId"/>

<html:hidden property="workRptOrgMtbfmttrDetailDTO.deptId"/>
<html:hidden property="workRptOrgMtbfmttrDetailDTO.deptDesc"/>
<html:hidden property="workRptOrgMtbfmttrDetailDTO.startDate"/>
<html:hidden property="workRptOrgMtbfmttrDetailDTO.endDate"/>
<html:hidden property="workRptOrgMtbfmttrDetailDTO.plantId"/>
<html:hidden property="workRptOrgMtbfmttrDetailDTO.plantDesc"/>

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
							<html:text property="workRptOrgMtbfmttrCommonDTO.filterStartDate" tabindex="10" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="workRptOrgMtbfmttrCommonDTO.filterEndDate" tabindex="20" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 조직구분  -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.deptCategDesc"/></label>
					<div class="input_box">
						<html:text property="workRptOrgMtbfmttrCommonDTO.filterDeptCategDesc" tabindex="30"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
						<html:text property="workRptOrgMtbfmttrCommonDTO.filterPlantDesc" tabindex="40" />
						<p class="open_spop"><a><span>조회</span></a>
                        </p>
                    </div>
                </div>
				<!-- 부서  -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="workRptOrgMtbfmttrCommonDTO.filterDeptDesc" tabindex="50"/>
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
				<div class="stitle_tx"><bean:message key="LABEL.mtbfMttr"/></div>
			</div>
		</div><!--sheader_box end-->
		<div class="article_box chbox">
			<div class="ch_title"><bean:message key='LABEL.mtbfHour'/></div>
			<div class="grid_area">
				<div id="mtbfchartbox" style="width:100%;height:270px;background-color:white;"></div>
			</div>
		</div> 
		<div class="article_box chbox">
			<div class="ch_title"><bean:message key='LABEL.mttrHour'/></div>
			<div class="grid_area">
				<div id="mttrchartbox" style="width:100%;height:270px;background-color:white;"></div>
			</div>
		</div>
	 </div> <!--  End of section_wrap -->
	<div class="section_wrap">
		<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.mtbfMttr"/></div>
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
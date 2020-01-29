<%--===========================================================================
MTTR(담당자)
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.org.rpt.empmttr.action.OrgRptEmpMttrListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- MTTR(담당자) -->
<title><bean:message key='MENU.EmpMTTR'/></title>
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

var deptAc,uDeptAc, plantAc, eqGRade;

function loadPage() 
{
	orgRptEmpMttrListForm.elements['orgRptEmpMttrCommonDTO.filterStartDate'].value = getYear()+"-01";
	orgRptEmpMttrListForm.elements['orgRptEmpMttrCommonDTO.filterEndDate'].value   = getYear()+"-"+(getMonth());
	
	//공장명
    if(loginUser.filterPlant!='null'){
    	orgRptEmpMttrListForm.elements['orgRptEmpMttrCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	orgRptEmpMttrListForm.elements['orgRptEmpMttrCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
	
	initGrid();
	initChart();
	
	uDeptAc = new autoC({"orgRptEmpMttrCommonDTO.filterUsageDeptDesc":"description"});
    uDeptAc.setTable("TADEPT");
    uDeptAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   });
    uDeptAc.setAcResultMap({
        "orgRptEmpMttrCommonDTO.filterUsageDept":"dept_id"
    });
    uDeptAc.init();
    
	deptAc = new autoC({"orgRptEmpMttrCommonDTO.filterDeptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   });
    deptAc.setAcDConditionMap({
    	"plant" : "orgRptEmpMttrCommonDTO.filterPlantId"
   	   });
    deptAc.setAcResultMap({
        "orgRptEmpMttrCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.init();
    
    //공장명
    plantAc = new autoC({"orgRptEmpMttrCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "orgRptEmpMttrCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
    //-----------------------------------------------설비등급------------------------------------------------
    eqGRade = new autoC({"orgRptEmpMttrCommonDTO.filterEqGradeDesc":"description"});
    eqGRade.setAcConditionMap({
        	"list_type":"EQ_GRADE"
        	,"param2":"EQUIP"
  	   });
    eqGRade.setTable("TACDSYSD");
    eqGRade.setAcResultMap({
        "orgRptEmpMttrCommonDTO.filterEqGrade":"cdsysd_no"
    });
    eqGRade.init();
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
		orgRptEmpMttrListForm.elements['orgRptEmpMttrDetailDTO.empId'].value = "";
		orgRptEmpMttrListForm.elements['orgRptEmpMttrDetailDTO.empDesc'].value = "";
		orgRptEmpMttrListForm.elements['orgRptEmpMttrDetailDTO.deptId'].value = "";
    	return sortColumn("orgRptEmpMttrList", this, orgRptEmpMttrListForm, "EMPID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

function initChart(){
	myChart =  new dhtmlXChart({
		//view:"stackedBar",
		view:"bar",
		container:"chartbox",
		value:"#MTTR#",
		color: "#639eb0",
		gradient:"falling",
		label:"#MTTR#",
		tooltip:{
			template:"#MTTR#"
		},
		xAxis:{
			template:"'#EMPNAME#"
		},
		yAxis:{},
		legend:{
			values:[{text:"<bean:message key='LABEL.mttrMin'/>",color:"#639eb0"}],
			valign:"middle",
			align:"right",
			width:90,
			layout:"y"
		}
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
    orgRptEmpMttrListForm.elements['orgRptEmpMttrDetailDTO.startDate'].value = orgRptEmpMttrListForm.elements['orgRptEmpMttrCommonDTO.filterStartDate'].value;
    orgRptEmpMttrListForm.elements['orgRptEmpMttrDetailDTO.endDate'].value = orgRptEmpMttrListForm.elements['orgRptEmpMttrCommonDTO.filterEndDate'].value;
}

function findGridList(sheetAction)
{
	if(checkValidation()) return;
	
	var form = document.orgRptEmpMttrListForm;	
	form.strutsAction.value = '<%=OrgRptEmpMttrListAction.EMP_MTTR_LIST_FIND %>';
	
	var url = contextPath + "/orgRptEmpMttrList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(orgRptEmpMttrListForm), "EMPID", "Y");
	
	$.post(url,FormQueryString(orgRptEmpMttrListForm), function(_data){
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
	var form = document.orgRptEmpMttrListForm;
	
	form.elements['orgRptEmpMttrDetailDTO.empId'].value = getValueById(myGrid, selectedId,'EMPID');
	form.elements['orgRptEmpMttrDetailDTO.empDesc'].value = getValueById(myGrid, selectedId,'EMPNAME');
	form.elements['orgRptEmpMttrDetailDTO.deptId'].value = getValueById(myGrid, selectedId,'DEPTID');
	
	form.elements['orgRptEmpMttrDetailDTO.plantId'].value = form.elements['orgRptEmpMttrCommonDTO.filterPlantId'].value;
	
	goCommonTabPage(form, '' , pageId);
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('orgRptEmpMttrDetailList');
	goTabPage('orgRptEmpMttrDetailChart');
}

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	excelAction(myGrid);
  }
 

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/orgRptEmpMttrList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="orgRptEmpMttrCommonDTO.filterDeptId"/>
<html:hidden property="orgRptEmpMttrCommonDTO.filterUsageDept"/>

<html:hidden property="orgRptEmpMttrCommonDTO.filterEqGrade"/>

<html:hidden property="orgRptEmpMttrDetailDTO.empId"/>
<html:hidden property="orgRptEmpMttrDetailDTO.empDesc"/>
<html:hidden property="orgRptEmpMttrDetailDTO.deptId"/>
<html:hidden property="orgRptEmpMttrDetailDTO.startDate"/>
<html:hidden property="orgRptEmpMttrDetailDTO.endDate"/>
<html:hidden property="orgRptEmpMttrDetailDTO.plantId"/>


<html:hidden property="orgRptEmpMttrCommonDTO.filterPlantId"/>

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
							<html:text property="orgRptEmpMttrCommonDTO.filterStartDate" tabindex="10" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="orgRptEmpMttrCommonDTO.filterEndDate" tabindex="20" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 부서  -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="orgRptEmpMttrCommonDTO.filterDeptDesc" tabindex="30"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 공장  -->
				<div class="field">
					<label><bean:message key="LABEL.plant"/></label>
					<div class="input_box">
						<html:text property="orgRptEmpMttrCommonDTO.filterPlantDesc" tabindex="30"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 사용부서  -->
				<div class="field">
					<label><bean:message key="LABEL.usedDept"/></label>
					<div class="input_box">
						<html:text property="orgRptEmpMttrCommonDTO.filterUsageDeptDesc" tabindex="45"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 사용부서  -->
				<div class="field">
					<label><bean:message key="LABEL.eqGrade"/></label>
					<div class="input_box">
						<html:text property="orgRptEmpMttrCommonDTO.filterEqGradeDesc" tabindex="45"/>
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
                <div class="stitle_tx"><bean:message key="LABEL.MTTR"/></div>
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
				<div class="stitle_tx"><bean:message key="LABEL.MTTR"/></div>
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
<%--===========================================================================
담당자별작업현황 
author  js.lee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.work.action.WorkRptWorkTypeRptByEmpListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 담당자별작업현황 -->
<title><bean:message key='MENU.AP424'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;
var plantAc;
var wkctrAc;
var wrkDeptAc;
var empAc;

function loadPage() 
{
	// 월 셋팅
	workRptWorkTypeRptByEmpListForm.elements['workRptWorkTypeRptByEmpCommonDTO.filterFromYyyyMm'].value = getYear()+"-"+(getMonth());
	workRptWorkTypeRptByEmpListForm.elements['workRptWorkTypeRptByEmpCommonDTO.filterToYyyyMm'].value   = getYear()+"-"+(getMonth());
		
	initGrid();
	
    // 공장
    plantAc = new autoC({"workRptWorkTypeRptByEmpCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "workRptWorkTypeRptByEmpCommonDTO.filterPlant":"plant"
    });
    plantAc.init();
    
	// 작업부서
    wrkDeptAc = new autoC({"workRptWorkTypeRptByEmpCommonDTO.filterWrkDeptDesc":"description"});
    wrkDeptAc.setTable("TADEPT");
    wrkDeptAc.setAcResultMap({
        "workRptWorkTypeRptByEmpCommonDTO.filterWrkDeptId":"dept_id",
    });
    wrkDeptAc.setAcConditionMap({
		"comp_no":loginUser.compNo
	}); 
    wrkDeptAc.setAcDConditionMap({
    	"plant" : "workRptWorkTypeRptByEmpCommonDTO.filterPlant"
    });
    wrkDeptAc.init();
    
	// 작업그룹
	wkctrAc = new autoC({"workRptWorkTypeRptByEmpCommonDTO.filterWkCtrDesc":"description"});
	wkctrAc.setTable("TAWKCTR");
	wkctrAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
	wkctrAc.setAcResultMap({
        "workRptWorkTypeRptByEmpCommonDTO.filterWkCtrId":"wkctr_id"
    });
	wkctrAc.init();
	
	// 작업종류 
    acSysDesc("workRptWorkTypeRptByEmpCommonDTO.filterWoTypeDesc","workRptWorkTypeRptByEmpCommonDTO.filterWoType","WO_TYPE");
    
	// 담당자
    empAc = new autoC({"workRptWorkTypeRptByEmpCommonDTO.filterEmpDesc":"emp_name"});
    empAc.setTable("TAEMP");
    empAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    empAc.setAcResultMap({
        "workRptWorkTypeRptByEmpCommonDTO.filterEmpId":"emp_id"
    });
    empAc.setAcDConditionMap({
    	"plant" : "workRptWorkTypeRptByEmpCommonDTO.filterPlant"
    });
    empAc.init();
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		workRptWorkTypeRptByEmpListForm.elements['workRptWorkTypeRptByEmpMonthDTO.empId'].value = "";
        return sortColumn("workRptWorkTypeRptByEmpList", this, workRptWorkTypeRptByEmpListForm, "empId", ind, direction);
    });
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		selectedRowId = rowId;
		selectedEqLocId = rowId;
		selectedCid = columnId;
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
}

function findGridList(sheetAction)
{
	if(checkValidation()) return;
	
	var form = document.workRptWorkTypeRptByEmpListForm;	
	form.strutsAction.value = '<%=WorkRptWorkTypeRptByEmpListAction.LIST_FIND %>';
	
	var url = contextPath + "/workRptWorkTypeRptByEmpList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workRptWorkTypeRptByEmpListForm), "SEQNO", "Y");
	
	goClose('workRptWorkTypeRptByEmpMonth');
	goClose('workRptWorkTypeRptByEmpWoType');
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
	var form = document.workRptWorkTypeRptByEmpListForm;
	
	form.elements['workRptWorkTypeRptByEmpMonthDTO.title'].value = getValueById(myGrid, selectedId,'empDesc') + " : " + monthToMonth(getValueById(myGrid, selectedId,'filterStartDate')) + " ~ " + monthToMonth(getValueById(myGrid, selectedId,'filterEndDate'));
	form.elements['workRptWorkTypeRptByEmpMonthDTO.empId'].value = getValueById(myGrid, selectedId,'empId');
	form.elements['workRptWorkTypeRptByEmpMonthDTO.empDesc'].value = getValueById(myGrid, selectedId,'empDesc');
	form.elements['workRptWorkTypeRptByEmpMonthDTO.deptId'].value = getValueById(myGrid, selectedId,'deptId');
	form.elements['workRptWorkTypeRptByEmpMonthDTO.deptDesc'].value = getValueById(myGrid, selectedId,'deptDesc');
	form.elements['workRptWorkTypeRptByEmpMonthDTO.wkCtrId'].value = getValueById(myGrid, selectedId,'wkctrId');
	form.elements['workRptWorkTypeRptByEmpMonthDTO.wkCtrDesc'].value = getValueById(myGrid, selectedId,'wkCtrDesc');
	form.elements['workRptWorkTypeRptByEmpMonthDTO.filterStartDate'].value = getValueById(myGrid, selectedId,'filterStartDate');
	form.elements['workRptWorkTypeRptByEmpMonthDTO.filterEndDate'].value = getValueById(myGrid, selectedId,'filterEndDate');
	form.elements['workRptWorkTypeRptByEmpMonthDTO.filterPlant'].value = getValueById(myGrid, selectedId,'filterPlant');
	form.elements['workRptWorkTypeRptByEmpMonthDTO.filterPlantDesc'].value = getValueById(myGrid, selectedId,'filterPlantDesc');
	form.elements['workRptWorkTypeRptByEmpMonthDTO.filterWoType'].value = getValueById(myGrid, selectedId,'filterWoType');
	form.elements['workRptWorkTypeRptByEmpMonthDTO.filterWoTypeDesc'].value = getValueById(myGrid, selectedId,'filterWoTypeDesc');
	form.elements['workRptWorkTypeRptByEmpWoTypeDTO.title'].value = getValueById(myGrid, selectedId,'empDesc') + " : " + monthToMonth(getValueById(myGrid, selectedId,'filterStartDate')) + " ~ " + monthToMonth(getValueById(myGrid, selectedId,'filterEndDate'));
	form.elements['workRptWorkTypeRptByEmpWoTypeDTO.empId'].value = getValueById(myGrid, selectedId,'empId');
	form.elements['workRptWorkTypeRptByEmpWoTypeDTO.empDesc'].value = getValueById(myGrid, selectedId,'empDesc');
	form.elements['workRptWorkTypeRptByEmpWoTypeDTO.deptId'].value = getValueById(myGrid, selectedId,'deptId');
	form.elements['workRptWorkTypeRptByEmpWoTypeDTO.deptDesc'].value = getValueById(myGrid, selectedId,'deptDesc');
	form.elements['workRptWorkTypeRptByEmpWoTypeDTO.wkCtrId'].value = getValueById(myGrid, selectedId,'wkctrId');
	form.elements['workRptWorkTypeRptByEmpWoTypeDTO.wkCtrDesc'].value = getValueById(myGrid, selectedId,'wkCtrDesc');
	form.elements['workRptWorkTypeRptByEmpWoTypeDTO.filterStartDate'].value = getValueById(myGrid, selectedId,'filterStartDate');
	form.elements['workRptWorkTypeRptByEmpWoTypeDTO.filterEndDate'].value = getValueById(myGrid, selectedId,'filterEndDate');
	form.elements['workRptWorkTypeRptByEmpWoTypeDTO.filterPlant'].value = getValueById(myGrid, selectedId,'filterPlant');
	form.elements['workRptWorkTypeRptByEmpWoTypeDTO.filterPlantDesc'].value = getValueById(myGrid, selectedId,'filterPlantDesc');
	form.elements['workRptWorkTypeRptByEmpWoTypeDTO.filterWoType'].value = getValueById(myGrid, selectedId,'filterWoType');
	form.elements['workRptWorkTypeRptByEmpWoTypeDTO.filterWoTypeDesc'].value = getValueById(myGrid, selectedId,'filterWoTypeDesc');
	
	goCommonTabPage(form, '' , pageId);
}

/**
 * 상세열기
 */
function goOpen(){
	goTabPage('workRptWorkTypeRptByEmpMonth');
	goTabPage('workRptWorkTypeRptByEmpWoType');
}

/**
 * 엑셀 다운.
 */
function goExcel()
{
 excelServerAction("workRptWorkTypeRptByEmpList", workRptWorkTypeRptByEmpListForm );
}
 
 /** 작업이력 */
function goWohistLink()
{
	var selectedId = myGrid.getSelectedRowId();
	if(selectedId == null) {
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	var startDate = getValueById(myGrid, selectedId,'filterStartDate');
	var endDate = getValueById(myGrid, selectedId,'filterEndDate');
	var plant = getValueById(myGrid, selectedId,'filterPlant');
	var plantDesc = getValueById(myGrid, selectedId,'filterPlantDesc');
	var woType = getValueById(myGrid, selectedId,'filterWoType');
	var woTypeDesc = getValueById(myGrid, selectedId,'filterWoTypeDesc');
	var empId = getValueById(myGrid, selectedId,'empId');
	var empDesc = getValueById(myGrid, selectedId,'empDesc');
	var deptId = getValueById(myGrid, selectedId,'deptId');
	var deptDesc = getValueById(myGrid, selectedId,'deptDesc');
	var wkctrId = getValueById(myGrid, selectedId,'wkctrId');
	var wkctrDesc = getValueById(myGrid, selectedId,'wkctrDesc');
	
	goWoList({
		"startDate":startDate+'01'
		,"endDate":endDate+getLastDayOfMonth(endDate.substr(0,4), endDate.substr(5,2))
		,"plant":plant
		,"plantDesc":plantDesc
		,"woType":woType
		,"woTypeDesc":woTypeDesc
		,"empId":empId
		,"empDesc":empDesc
		,"deptId":deptId
		,"deptDesc":deptDesc
		,"wkctrId":wkctrId
		,"wkctrDesc":wkctrDesc
	});
}

 
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workRptWorkTypeRptByEmpList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="workRptWorkTypeRptByEmpCommonDTO.filterPlant"/>
<html:hidden property="workRptWorkTypeRptByEmpCommonDTO.filterWrkDeptId"/>
<html:hidden property="workRptWorkTypeRptByEmpCommonDTO.filterWkCtrId"/>
<html:hidden property="workRptWorkTypeRptByEmpCommonDTO.filterWoType"/>
<html:hidden property="workRptWorkTypeRptByEmpCommonDTO.filterEmpId"/>
<html:hidden property="workRptWorkTypeRptByEmpMonthDTO.title"/>
<html:hidden property="workRptWorkTypeRptByEmpMonthDTO.empId"/>
<html:hidden property="workRptWorkTypeRptByEmpMonthDTO.empDesc"/>
<html:hidden property="workRptWorkTypeRptByEmpMonthDTO.deptId"/>
<html:hidden property="workRptWorkTypeRptByEmpMonthDTO.deptDesc"/>
<html:hidden property="workRptWorkTypeRptByEmpMonthDTO.wkCtrId"/>
<html:hidden property="workRptWorkTypeRptByEmpMonthDTO.wkCtrDesc"/>
<html:hidden property="workRptWorkTypeRptByEmpMonthDTO.filterStartDate"/>
<html:hidden property="workRptWorkTypeRptByEmpMonthDTO.filterEndDate"/>
<html:hidden property="workRptWorkTypeRptByEmpMonthDTO.filterPlant"/>
<html:hidden property="workRptWorkTypeRptByEmpMonthDTO.filterPlantDesc"/>
<html:hidden property="workRptWorkTypeRptByEmpMonthDTO.filterWoType"/>
<html:hidden property="workRptWorkTypeRptByEmpMonthDTO.filterWoTypeDesc"/>
<html:hidden property="workRptWorkTypeRptByEmpWoTypeDTO.title"/>
<html:hidden property="workRptWorkTypeRptByEmpWoTypeDTO.empId"/>
<html:hidden property="workRptWorkTypeRptByEmpWoTypeDTO.empDesc"/>
<html:hidden property="workRptWorkTypeRptByEmpWoTypeDTO.deptId"/>
<html:hidden property="workRptWorkTypeRptByEmpWoTypeDTO.deptDesc"/>
<html:hidden property="workRptWorkTypeRptByEmpWoTypeDTO.wkCtrId"/>
<html:hidden property="workRptWorkTypeRptByEmpWoTypeDTO.wkCtrDesc"/>
<html:hidden property="workRptWorkTypeRptByEmpWoTypeDTO.filterStartDate"/>
<html:hidden property="workRptWorkTypeRptByEmpWoTypeDTO.filterEndDate"/>
<html:hidden property="workRptWorkTypeRptByEmpWoTypeDTO.filterPlant"/>
<html:hidden property="workRptWorkTypeRptByEmpWoTypeDTO.filterPlantDesc"/>
<html:hidden property="workRptWorkTypeRptByEmpWoTypeDTO.filterWoType"/>
<html:hidden property="workRptWorkTypeRptByEmpWoTypeDTO.filterWoTypeDesc"/>

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
					<label class="check"><bean:message key="LABEL.week2"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="workRptWorkTypeRptByEmpCommonDTO.filterFromYyyyMm" tabindex="10" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="workRptWorkTypeRptByEmpCommonDTO.filterToYyyyMm" tabindex="20" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 공장  -->
				<div class="field">
					<label><bean:message key="LABEL.plant"/></label>
					<div class="input_box">
						<html:text property="workRptWorkTypeRptByEmpCommonDTO.filterPlantDesc" tabindex="30"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 작업부서  -->
				<div class="field">
					<label><bean:message key="LABEL.workDept"/></label>
					<div class="input_box">
						<html:text property="workRptWorkTypeRptByEmpCommonDTO.filterWrkDeptDesc" tabindex="40"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 작업그룹  -->
				<div class="field">
					<label><bean:message key="LABEL.wkCtr"/></label>
					<div class="input_box">
						<html:text property="workRptWorkTypeRptByEmpCommonDTO.filterWkCtrDesc" tabindex="50"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 작업종류  -->
                <div class="field">
                    <label><bean:message key="LABEL.woType"/></label>
                    <div class="input_box">
							<html:text property="workRptWorkTypeRptByEmpCommonDTO.filterWoTypeDesc"	tabindex="60" />
							<p class="open_spop"><a><span>조회</span></a></p>
                        </p>
                    </div>
                </div>
                <!-- 담당자  -->
				<div class="field">
					<label><bean:message key="LABEL.manager"/></label>
					<div class="input_box">
						<html:text property="workRptWorkTypeRptByEmpCommonDTO.filterEmpDesc" tabindex="70"/>
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
				<div class="fb_group2">
				</div>
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
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
<%--===========================================================================
조직별 고장분석
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.org.bd.action.WorkRptOrgBdListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 조직별 고장분석 -->
<title><bean:message key='MENU.LCCByEqp'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var deptAc;
var plantAc;

var selectedCid;

function loadPage() 
{
	// 월
	var startDate = workRptOrgBdListForm.elements['workRptOrgBdCommonDTO.filterStartDate'].value;
	var endDate = workRptOrgBdListForm.elements['workRptOrgBdCommonDTO.filterEndDate'].value;
	
	if((startDate=="" || startDate==null)&&(endDate=="" || endDate==null)) {
		workRptOrgBdListForm.elements['workRptOrgBdCommonDTO.filterStartDate'].value = getYear()+"-01";
		workRptOrgBdListForm.elements['workRptOrgBdCommonDTO.filterEndDate'].value   = getYear()+"-"+(getMonth());
	}
	//공장명
    if(loginUser.filterPlant!='null'){
    	workRptOrgBdListForm.elements['workRptOrgBdCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	workRptOrgBdListForm.elements['workRptOrgBdCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
	
	initGrid();

	// 부서
    deptAc = new autoC({"workRptOrgBdCommonDTO.filterDeptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   });
    deptAc.setAcResultMap({
        "workRptOrgBdCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "workRptOrgBdCommonDTO.filterPlantId"
      , "dept_categ" : "workRptOrgBdCommonDTO.filterDeptCategId"
    });
    deptAc.init();
    
    // 조직구분
    acSysDesc("workRptOrgBdCommonDTO.filterDeptCategDesc","workRptOrgBdCommonDTO.filterDeptCategId","DEPT_CATEG");
    
 	// 공장코드
	plantAc = new autoC({"workRptOrgBdCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "workRptOrgBdCommonDTO.filterPlantId":"plant"
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
		selectedCid = columnId;
		goOpen();
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		workRptOrgBdListForm.elements['workRptOrgBdDetailDTO.deptId'].value = "";
        return sortColumn("workRptOrgBdList", this, workRptOrgBdListForm, "deptId", ind, direction);
    });

	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
    workRptOrgBdListForm.elements['workRptOrgBdDetailDTO.startDate'].value = workRptOrgBdListForm.elements['workRptOrgBdCommonDTO.filterStartDate'].value;
    workRptOrgBdListForm.elements['workRptOrgBdDetailDTO.endDate'].value = workRptOrgBdListForm.elements['workRptOrgBdCommonDTO.filterEndDate'].value;
    findGridList('Search');   
}

function findGridList(sheetAction)
{
	if(checkValidation()) return;
	
	var form = document.workRptOrgBdListForm;	
	form.strutsAction.value = '<%=WorkRptOrgBdListAction.LCC_EQUIP_LIST_FIND %>';
	
	var url = contextPath + "/workRptOrgBdList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workRptOrgBdListForm), "ITEMNO", "Y");
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
	var form = document.workRptOrgBdListForm;
	
	form.elements['workRptOrgBdDetailDTO.deptId'].value = getValueById(myGrid, selectedId,'DEPTID');
	form.elements['workRptOrgBdDetailDTO.deptDesc'].value = getValueById(myGrid, selectedId,'ORGNAME');
	form.elements['workRptOrgBdDetailDTO.plantId'].value = form.elements['workRptOrgBdCommonDTO.filterPlantId'].value;
	form.elements['workRptOrgBdDetailDTO.plantDesc'].value = form.elements['workRptOrgBdCommonDTO.filterPlantDesc'].value;

	goCommonTabPage(form, '' , pageId);
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('workRptOrgBdDetailList');
	
	var selectedColumnId = myGrid.getColumnId(selectedCid);
	
	if(selectedColumnId == "WOTIMEHOUR" || selectedColumnId == "MAINTAMT") {
		workRptOrgBdListForm.elements['workRptOrgBdDetailDTO.chartValue'].value = selectedColumnId;
	}
	else {
		workRptOrgBdListForm.elements['workRptOrgBdDetailDTO.chartValue'].value = "BDCNT";
	}

	goTabPage('workRptOrgBdDetailChart');
	workRptOrgBdListForm.elements['workRptOrgBdDetailDTO.chartValue'].value="WOTIMEHOUR";
	goTabPage('workRptOrgBdWorkTimeDetailChart');
}

 /**
  * 엑셀 다운.
  */
 function goExcel()
 {
 	excelServerAction("workRptOrgBdList", workRptOrgBdListForm);  
 }
 
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workRptOrgBdList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="workRptOrgBdCommonDTO.filterDeptId"/>
<html:hidden property="workRptOrgBdCommonDTO.filterPlantId"/>
<html:hidden property="workRptOrgBdCommonDTO.filterDeptCategId"/>
<html:hidden property="workRptOrgBdDetailDTO.plantId"/>
<html:hidden property="workRptOrgBdDetailDTO.plantDesc"/>
<html:hidden property="workRptOrgBdDetailDTO.deptId"/>
<html:hidden property="workRptOrgBdDetailDTO.deptDesc"/>
<html:hidden property="workRptOrgBdDetailDTO.startDate"/>
<html:hidden property="workRptOrgBdDetailDTO.endDate"/>
<html:hidden property="workRptOrgBdDetailDTO.chartValue"/>

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
							<html:text property="workRptOrgBdCommonDTO.filterStartDate" tabindex="10" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="workRptOrgBdCommonDTO.filterEndDate" tabindex="20" />
							<p class="open_mon_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 조직구분  -->
				<div class="field">
					<label class="check"><bean:message key="LABEL.deptCategDesc"/></label>
					<div class="input_box">
						<html:text property="workRptOrgBdCommonDTO.filterDeptCategDesc" tabindex="30"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 공장명  -->
                <div class="field">
                    <label><bean:message key="LABEL.plantDesc"/></label>
                    <div class="input_box">
							<html:text property="workRptOrgBdCommonDTO.filterPlantDesc" tabindex="40" />
							<p class="open_spop"><a><span>조회</span></a>
                        </p>
                    </div>
                </div>
				<!-- 부서  -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="workRptOrgBdCommonDTO.filterDeptDesc" tabindex="50"/>
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
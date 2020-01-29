<%--===========================================================================
정기점검항목결과
author  nhkim8548
version $Id: workRptPmiPointValueList.jsp, v1.0 2019/07/10 10:09:27 nhkim8548 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.pmi.point.value.action.WorkRptPmiPointValueListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 정기점검항목결과 -->
<title><bean:message key='PAGE.workRptPmiPointValueList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">

<!-- 공통메뉴 -->
<script language="javascript">

var myGrid, plantAc, deptAc, wkctrAc, eqCtgAc, eqLocAc;

function loadPage() 
{
	// 점검일자(시작일)
    workRptPmiPointValueListForm.elements['workRptPmiPointValueCommonDTO.filterInsStartDate'].value = getMinusMonth2(new Date(), -36).substr(0,4)+"-"+dateToData(getToday()).substr(4, 2)+"-"+dateToData(getToday()).substr(6, 2);
    // 점검일자(종료)
    workRptPmiPointValueListForm.elements['workRptPmiPointValueCommonDTO.filterInsEndDate'].value = dateToData(getToday()).substr(0, 4)+"-"+dateToData(getToday()).substr(4, 2)+"-"+dateToData(getToday()).substr(6, 2);
	
    initGrid();
    
	// 공장 AC
	plantAc = new autoC({"workRptPmiPointValueCommonDTO.filterPlantDesc":"description"});
	plantAc.setTable("TAPLANT");
	plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    }); 
	plantAc.setAcResultMap({
        "workRptPmiPointValueCommonDTO.filterPalntId":"plant"
    });
	plantAc.init();
	
	// 작업부서 AC
	deptAc = new autoC({"workRptPmiPointValueCommonDTO.filterWorkDeptDesc":"description"});
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "workRptPmiPointValueCommonDTO.filterWorkDeptId":"dept_id"
    });
    deptAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    }); 
    deptAc.setAcDConditionMap({
    	"plant" : "workRptPmiPointValueCommonDTO.filterPlantId"
    });
    deptAc.init();
	
    // 작업그룹 AC
    wkctrAc = new autoC({"workRptPmiPointValueCommonDTO.filterWkCtrDesc":"description"});
	wkctrAc.setTable("TAWKCTR");
	wkctrAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
	wkctrAc.setAcResultMap({
        "workRptPmiPointValueCommonDTO.filterWkCtrId":"wkctr_id"
    });
	wkctrAc.init();

	// 작업담당자 AC
	woMngAc = new autoC({"workRptPmiPointValueCommonDTO.filterWoManagerDesc":"emp_name"});
	woMngAc.setTable("TAEMP");
	woMngAc.setAcResultMap({
        "workRptPmiPointValueCommonDTO.filterWoManagerId":"emp_id"
    });
	woMngAc.setAcDConditionMap({
    	"dept_id" : "workRptPmiPointValueCommonDTO.filterDeptId"
      , "plant" : "workRptPmiPointValueCommonDTO.filterPlantId"
    });
	woMngAc.init();

	// 설비 AC
	equipAc = new autoC({"workRptPmiPointValueCommonDTO.filterEquipName":"description"});
	equipAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	});
	equipAc.setTable("TAEQUIPMENT");
	equipAc.setAcResultMap({
        "workRptPmiPointValueCommonDTO.filterEquipId":"euqip_id"
    });
	equipAc.setAcDConditionMap({
    	"eqloc_id" : "workRptPmiPointValueCommonDTO.filterEqLocId"
      , "eqctg_id" : "workRptPmiPointValueCommonDTO.filterEqCtgId"
      , "plant"	: "workRptPmiPointValueCommonDTO.filterPlantId"
    });
	equipAc.init();
	
  	// 설비위치 AC
	eqLocAc = new autoC({"workRptPmiPointValueCommonDTO.filterEqLocDesc":"full_desc"});
	eqLocAc.setTable("TAEQLOC");
	eqLocAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	});
	eqLocAc.setAcResultMap({
        "workRptPmiPointValueCommonDTO.filterEqLocId":"eqloc_id"
    });
	eqLocAc.setAcDConditionMap({
    	"plant"	: "workRptPmiPointValueCommonDTO.filterPlantId"
    });
	eqLocAc.init();
	
	// 설비종류 AC
	eqCtgAc = new autoC({"workRptPmiPointValueCommonDTO.filterEqCtgDesc":"description"});
	eqCtgAc.setTable("TAEQCTG");
	eqCtgAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    }); 
	eqCtgAc.setAcResultMap({
        "workRptPmiPointValueCommonDTO.filterEqCtgId":"eqctg_id"
    });
	eqCtgAc.init();
	
	acSysDesc("workRptPmiPointValueCommonDTO.filterRsltStatusDesc","workRptPmiPointValueCommonDTO.filterRsltStatusId","PM_POINT_RSLT_STATUS");
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
	});  
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
        return sortColumn("workRptPmiPointValueList", this, workRptPmiPointValueListForm, "", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
	myGrid.init();
	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

// 검색 클릭 시 호출
function goSearch()
{
    findGridList('Search', "");   
}

function findGridList(sheetAction, keyCol)
{
	document.workRptPmiPointValueListForm.strutsAction.value = '<%=WorkRptPmiPointValueListAction.WORK_PMI_POINT_VALUE_LIST_FIND %>';
	
	var url = contextPath + "/workRptPmiPointValueList.do";
	
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workRptPmiPointValueListForm), "", "Y");
}

// Excel 출력
function goExcel()
{
	excelServerAction("workRptPmiPointValueList", workRptPmiPointValueListForm);
} 
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workRptPmiPointValueList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="workRptPmiPointValueCommonDTO.filterPlantId"/>
<html:hidden property="workRptPmiPointValueCommonDTO.filterWorkDeptId"/>
<html:hidden property="workRptPmiPointValueCommonDTO.filterWkCtrId"/>
<html:hidden property="workRptPmiPointValueCommonDTO.filterWoManagerId"/>
<html:hidden property="workRptPmiPointValueCommonDTO.filterEquipId"/>
<html:hidden property="workRptPmiPointValueCommonDTO.filterEqLocId"/>
<html:hidden property="workRptPmiPointValueCommonDTO.filterEqCtgId"/>
<html:hidden property="workRptPmiPointValueCommonDTO.filterRsltStatusId"/>
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
		<div class="article_box" >
			<div class="form_box">
				<!-- 점검일자 -->
				<div class="field">
					<label><bean:message key="LABEL.inspectDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="workRptPmiPointValueCommonDTO.filterInsStartDate" tabindex="10"/>
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="workRptPmiPointValueCommonDTO.filterInsEndDate" tabindex="20"/>
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 공장 -->
				<div class="field">
					<label><bean:message key="LABEL.plant"/></label>
					<div class="input_box">
						<html:text property="workRptPmiPointValueCommonDTO.filterPlantDesc" tabindex="30"/>
						<p class="open_spop">
							<a><span>조회</span></a>
						</p>
					</div>
				</div>
				<!-- 작업부서 -->
				<div class="field">
					<label><bean:message key="LABEL.workDept"/></label>
					<div class="input_box">
						<html:text property="workRptPmiPointValueCommonDTO.filterWorkDeptDesc" tabindex="40"/>
						<p class="open_spop">
							<a><span>조회</span></a>
						</p>
					</div>
				</div>
				<!-- 작업그룹 -->
				<div class="field">
					<label><bean:message key="LABEL.wkCtr"/></label>
					<div class="input_box">
						<html:text property="workRptPmiPointValueCommonDTO.filterWkCtrDesc" tabindex="50"/>
						<p class="open_spop">
							<a><span>조회</span></a>
						</p>
					</div>
				</div>
				<!-- 작업담당자 -->
				<div class="field">
					<label><bean:message key="LABEL.WOMANAGER"/></label>
					<div class="input_box">
						<html:text property="workRptPmiPointValueCommonDTO.filterWoManagerDesc" tabindex="60"/>
						<p class="open_spop">
							<a><span>조회</span></a>
						</p>
					</div>
				</div>
				<!-- 설비 -->
				<div class="field">
					<label><bean:message key="LABEL.equipment"/></label>
					<div class="input_box">
						<html:text property="workRptPmiPointValueCommonDTO.filterEquipName" tabindex="70"/>
						<p class="open_spop">
							<a><span>조회</span></a>
						</p>
					</div>
				</div>
				<!-- 설비위치 -->
				<div class="field">
					<label><bean:message key="LABEL.eqLocDesc"/></label>
					<div class="input_box">
						<html:text property="workRptPmiPointValueCommonDTO.filterEqLocDesc" tabindex="80"/>
						<p class="open_spop">
							<a><span>조회</span></a>
						</p>
					</div>
				</div>
				<!-- 설비종류 -->
				<div class="field">
					<label><bean:message key="LABEL.eqCtg"/></label>
					<div class="input_box">
						<html:text property="workRptPmiPointValueCommonDTO.filterEqCtgDesc" tabindex="90"/>
						<p class="open_spop">
							<a><span>조회</span></a>
						</p>
					</div>
				</div>
				<!-- 예방점검# -->
				<div class="field">
					<label><bean:message key="LABEL.pmNo"/></label>
					<div class="input_box">
						<html:text property="workRptPmiPointValueCommonDTO.filterPmInsListNo" tabindex="100"/>
					</div>
				</div>
				<!-- 점검결과 -->
				<div class="field">
					<label><bean:message key="LABEL.rsltStatusDesc"/></label>
					<div class="input_box">
						<html:text property="workRptPmiPointValueCommonDTO.filterRsltStatusDesc" tabindex="110"/>
						<p class="open_spop">
							<a><span>조회</span></a>
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
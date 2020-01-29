<%--===========================================================================
에너지관리 - 에너지값 등록
author  sy.yang
version $Id: workListEnergyMstrList.jsp,v 1.1 2015/12/03 01:45:27 sy.yang Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.list.energy.action.WorkListEnergyMstrListAction" %>
<%@ page import="dream.work.list.energy.action.WorkListEnergyMstrDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 

%>
<html>
<head>
<!-- 에너지값 등록 -->
<title><bean:message key='PAGE.workListEnergyMstrList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;
var beforePageId = '';
/** 자동완성 변수 */
var equipDescAc;
var deptAc;
var pmTypeAc;
var wkCtrDescAc;
var empAc;
var plantAc;

var multiPmAc;

var woType = "PMU";

function loadPage() 
{
	workListEnergyMstrListForm.elements['workListEnergyMstrListCommonDTO.filterWoTypeId'].value = woType;
    acSysDesc("workListEnergyMstrListCommonDTO.filterWoTypeDesc","workListEnergyMstrListCommonDTO.filterWoTypeId","WO_TYPE");
	
	//작업시작일자, 종료일자 넣기.
	if(workListEnergyMstrListForm.elements['workListEnergyMstrListCommonDTO.filterStartDate'].value == "")
	{
		workListEnergyMstrListForm.elements['workListEnergyMstrListCommonDTO.filterStartDate'].value   = getMinusDay(7);
		workListEnergyMstrListForm.elements['workListEnergyMstrListCommonDTO.filterEndDate'].value   = getToday();
	}
	//부서
	workListEnergyMstrListForm.elements['workListEnergyMstrListCommonDTO.filterDeptId'].value    = loginUser.filterDeptId;
	workListEnergyMstrListForm.elements['workListEnergyMstrListCommonDTO.filterDeptDesc'].value  = loginUser.filterDeptDesc;
    if(loginUser.filterWkCtrId!='null'){
       	workListEnergyMstrListForm.elements['workListEnergyMstrListCommonDTO.filterWkCtrId'].value = loginUser.filterWkCtrId;
       	workListEnergyMstrListForm.elements['workListEnergyMstrListCommonDTO.filterWkCtrDesc'].value = loginUser.filterWkCtrDesc;
	}
    //공장명
       if(loginUser.filterPlant!='null'){
      	workListEnergyMstrListForm.elements['workListEnergyMstrListCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
      	workListEnergyMstrListForm.elements['workListEnergyMstrListCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
    }
    initGrid();

    // 설비
    equipDescAc = new autoC({"workListEnergyMstrListCommonDTO.filterEquipDesc":"description"});
    equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
    equipDescAc.setAcResultMap({
        "workListEnergyMstrListCommonDTO.filterEquipId":"equip_id"
    });
    equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcDConditionMap({
    	"eqloc_id" : "workListEnergyMstrListCommonDTO.filterEqLocId",
    	"dept_id" : "workListEnergyMstrListCommonDTO.filterDeptId",
    	"plant" : "workListEnergyMstrListCommonDTO.filterPlantId"
    });
    equipDescAc.init();
    
    // 부서
    deptAc = new autoC({"workListEnergyMstrListCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "workListEnergyMstrListCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.setAcDConditionMap({
    	"plant" : "workListEnergyMstrListCommonDTO.filterPlantId"
    });
    deptAc.init();
    
    // 작업그룹
    wkCtrDescAc = new autoC({"workListEnergyMstrListCommonDTO.filterWkCtrDesc":"description"});
    wkCtrDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    wkCtrDescAc.setTable("TAWKCTR");
    wkCtrDescAc.setAcResultMap({
        "workListEnergyMstrListCommonDTO.filterWkCtrId":"wkctr_id"
    });
    wkCtrDescAc.init();
    
    // 담당자
    empAc = new autoC({"workListEnergyMstrListCommonDTO.filterEmpDesc":"emp_name"});
    empAc.setAcDisplay("EMP_NAME");
    empAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    empAc.setTable("TAEMP");
    empAc.setAcResultMap({
        "workListEnergyMstrListCommonDTO.filterEmpId":"emp_id"
    });
    empAc.setAcDConditionMap({
    	"dept_id" : "workListEnergyMstrListCommonDTO.filterDeptId",
    	"plant" : "workListEnergyMstrListCommonDTO.filterPlantId"
    });
    empAc.init();
    
    // 공장
    plantAc = new autoC({"workListEnergyMstrListCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "workListEnergyMstrListCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
    // 작업명
    pmuPMAc = new autoC({"workListEnergyMstrListCommonDTO.filterPmiDesc":"description"});
    pmuPMAc.setTable("TAPMUPMLST");
    pmuPMAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
	    ,"is_active":"Y"
		,"wo_type":"PMU"
    });
    pmuPMAc.setAcDConditionMap({
    	"plant" : "workListEnergyMstrListCommonDTO.filterPlantId"
    });
    pmuPMAc.setAcResultMap({
        "workListEnergyMstrListCommonDTO.filterPmiDesc":"description"
    });
    pmuPMAc.init();
    

    multiPmAc = new autoC({"workListEnergyMstrDetailDTO.multiDesc":"description"});
    multiPmAc.setTable("TAPMUPMLST");
    multiPmAc.setAcConditionMap({
	   "comp_no":loginUser.compNo
	   ,"is_active":"Y"
	   ,"wo_type":"PMU"
	   });
    multiPmAc.setAcResultMap({
	    "workListEnergyMstrDetailDTO.multiKey":"pmId"
	});
    multiPmAc.setMultiSelect(true);
    multiPmAc.init();
    
    
    // 점검작업상태
    acSysDesc("workListEnergyMstrListCommonDTO.filterPmSchedStatusDesc","workListEnergyMstrListCommonDTO.filterPmSchedStatusId","PMSCHED_STATUS");
 	// 작업형태 AC
    acSysDesc("workListEnergyMstrListCommonDTO.filterPmTypeDesc","workListEnergyMstrListCommonDTO.filterPmTypeId", woType+"_TYPE");

}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
     myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	workListEnergyMstrListForm.elements['workListEnergyMstrListCommonDTO.pminslistId'].value = "";
    	return sortColumn("workListEnergyMstrList", this, workListEnergyMstrListForm, "PMINSLISTID", ind, direction);
	}); 

	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/workListEnergyMstrList.do";

    workListEnergyMstrListForm.elements['strutsAction'].value = '<%=WorkListEnergyMstrListAction.WORK_PMU_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workListEnergyMstrListForm), "PMINSLISTID", "Y");
}

/**
 * 엑셀 다운.
 */
 function goExcel()
 {
	workListEnergyMstrListForm.elements['workListEnergyMstrListCommonDTO.pminslistId'].value = "";
 	excelServerAction("workListEnergyMstrList", workListEnergyMstrListForm );  
 }
 
/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	workListEnergyMstrListForm.elements['workListEnergyMstrListCommonDTO.pminslistId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search');
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
	var form = document.workListEnergyMstrListForm;
	
	form.elements['workListEnergyMstrListCommonDTO.pminslistId'].value = getValueById(myGrid, selectedId,'PMINSLISTID');
	form.elements['workListEnergyMstrListCommonDTO.pmId'].value = getValueById(myGrid, selectedId,'PMID');
	
	goCommonTabPage(form, <%= WorkListEnergyMstrDetailAction.WORK_PMU_DETAIL_INIT %>, pageId,beforePageId);

	beforePageId = pageId;
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pminslistId)
{
	workListEnergyMstrListForm.elements['workListEnergyMstrListCommonDTO.pminslistId'].value = _pminslistId;
	findGridList('ReloadRow');
	workListEnergyMstrListForm.elements['workListEnergyMstrListCommonDTO.pminslistId'].value = "";
}

/**
 * 상세열기
 */
 function goOpen()
 {
	goTabPage("workListEnergyMstrDetail");
}
 
 function goOpenAction()
 {
     var selectedId=myGrid.getSelectedRowId();
     if(selectedId == null) return;
     
     workListEnergyMstrListForm.elements['workListEnergyMstrListCommonDTO.pminslistId'].value = getValueById(myGrid, selectedId,'PMINSLISTID');
     workListEnergyMstrListForm.elements['workListEnergyMstrListCommonDTO.pmId'].value = getValueById(myGrid, selectedId,'PMID');
     workListEnergyMstrListForm.elements['strutsAction'].value = '<%=WorkListEnergyMstrDetailAction.WORK_PMU_DETAIL_INIT%>';
     openQuickTabPage(FormQueryString(workListEnergyMstrListForm), 'workListEnergyMstrDetail'); 
 }

/**
 * 생성
 */
function goCreate()
{
	goClose(beforePageId);
	
	multiPmAc.openLov();
}

function goCreateAction(pageId)
{

}
function setAcLovValue(rtnArr, code)
{
	workListEnergyMstrListForm.strutsAction.value = '<%=WorkListEnergyMstrListAction.WORK_PMU_LIST_INPUT%>';
	
	//작업상태 = P - 작업대기
	workListEnergyMstrListForm.elements['workListEnergyMstrDetailDTO.pmschedStatusId'].value = "P";
	//작업종류 
	workListEnergyMstrListForm.elements['workListEnergyMstrDetailDTO.woTypeId'].value = "PMU";
	
	var url = contextPath + "/workListEnergyMstrList.do";

    $.post(url,FormQueryString(workListEnergyMstrListForm), function(_data){
    	goSearch();	
    });
    
}


/**
  * 삭제
  */
function goDelete()
{
	var chkedRowsId = getCheckedRows(myGrid, 'ISDELCHECK');

	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'PMINSLISTID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	workListEnergyMstrListForm.strutsAction.value = '<%=WorkListEnergyMstrListAction.WORK_PMU_LIST_DELETE%>';
	var url = contextPath + "/workListEnergyMstrList.do";
	$.post(url,FormQueryString(workListEnergyMstrListForm)+delArray , function(_data){
    	afterDelete();
    }); 
}

function afterDelete()
{
	goClose(beforePageId);
  	alertMessage1('<bean:message key="MESSAGE.MSG0273"/>');
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = workListEnergyMstrListForm.elements['workListEnergyMstrListCommonDTO.pminslistId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }


//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workListEnergyMstrList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="workListEnergyMstrListCommonDTO.pminslistId"/><!-- Key -->
<html:hidden property="workListEnergyMstrListCommonDTO.pmId"/>
<html:hidden property="workListEnergyMstrListCommonDTO.filterDeptId"/>
<html:hidden property="workListEnergyMstrListCommonDTO.filterEquipId"/>
<html:hidden property="workListEnergyMstrListCommonDTO.filterPmTypeId"/>
<html:hidden property="workListEnergyMstrListCommonDTO.filterWoTypeId"/>
<html:hidden property="workListEnergyMstrListCommonDTO.filterWkCtrId"/>
<html:hidden property="workListEnergyMstrListCommonDTO.filterEmpId"/>
<html:hidden property="workListEnergyMstrListCommonDTO.selectedPmType"/>
<html:hidden property="workListEnergyMstrListCommonDTO.selectedWoType"/>
<html:hidden property="workListEnergyMstrListCommonDTO.filterPlantId"/>
<html:hidden property="workListEnergyMstrListCommonDTO.filterPmSchedStatusId"/>

<html:hidden property="workListEnergyMstrDetailDTO.multiKey"/><!-- MultiSelect Key -->
<html:hidden property="workListEnergyMstrDetailDTO.multiDesc"/><!-- MultiSelect Desc -->
<html:hidden property="workListEnergyMstrDetailDTO.pmschedStatusId"/>
<html:hidden property="workListEnergyMstrDetailDTO.woTypeId"/>
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
					<label><bean:message key="LABEL.workDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="workListEnergyMstrListCommonDTO.filterStartDate" tabindex="10" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="workListEnergyMstrListCommonDTO.filterEndDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 공장  -->
                <div class="field">
                    <label><bean:message key="LABEL.plant"/></label>
                    <div class="input_box">
						<html:text property="workListEnergyMstrListCommonDTO.filterPlantDesc" tabindex="140" />
						<p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
                <!-- 설비 -->
				<div class="field">
					<label><bean:message key="LABEL.equipDesc"/></label>
					<div class="input_box">
						<html:text property="workListEnergyMstrListCommonDTO.filterEquipDesc" tabindex="50" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 작업형태  -->
				<div class="field">
					<label><bean:message key="LABEL.pmType"/></label>
					<div class="input_box">
						<html:text property="workListEnergyMstrListCommonDTO.filterPmTypeDesc" tabindex="120" />
						<p class="open_spop">
							<a><span>조회</span></a>
						</p>
					</div>
				</div>
				<!-- 부서 -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="workListEnergyMstrListCommonDTO.filterDeptDesc" tabindex="40"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>				
				<!-- 작업그룹  -->
				<div class="field">
                     <label><bean:message key="LABEL.wkCtr"/></label>
                     <div class="input_box">
                        <html:text property="workListEnergyMstrListCommonDTO.filterWkCtrDesc" tabindex="170" />
                         <p class="open_spop">
                             <a><span>조회</span></a>
                         </p>
                   </div>
                </div>
				<!-- 담당자 -->
				<div class="field">
					<label><bean:message key="LABEL.manager"/></label>
					<div class="input_box">
						<html:text property="workListEnergyMstrListCommonDTO.filterEmpDesc" tabindex="45"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 상태  -->
                <div class="field">
                    <label><bean:message key="LABEL.status"/></label>
                    <div class="input_box">
						<html:text property="workListEnergyMstrListCommonDTO.filterPmSchedStatusDesc" tabindex="150" />
						<p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
				<!-- 주기# -->
				<div class="field">
					<label><bean:message key="LABEL.PERIODNO"/></label>
					<div class="input_box">
						<html:text property="workListEnergyMstrListCommonDTO.filterPmNo" tabindex="30"/>
					</div>
				</div>    
				<!-- 작업명 -->
				<div class="field">
					<label><bean:message key="LABEL.woName"/></label>
					<div class="input_box">
						<html:text property="workListEnergyMstrListCommonDTO.filterPmiDesc" tabindex="10"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 작업번호 -->
				<div class="field">
					<label><bean:message key="LABEL.woNo"/></label>
					<div class="input_box">
						<html:text property="workListEnergyMstrListCommonDTO.filterWoNo" tabindex="1"/>
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
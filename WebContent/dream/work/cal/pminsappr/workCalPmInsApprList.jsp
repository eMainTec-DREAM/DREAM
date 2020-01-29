<%--===========================================================================
정기점검 승인목록
author  kim21017
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.cal.pminsappr.action.WorkCalPmInsApprListAction" %>
<%@ page import="dream.work.cal.pminsappr.action.WorkCalPmInsApprDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<html>
<head>
<!-- 예방점검계획승인 -->
<title><bean:message key='MENU.INSAPPR'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->

<script language="javascript">
<!--//

var myGrid;

/** 자동완성 변수 */
var deptAc, plantAc;
function loadPage()
{
	//공장 
	workCalPmInsApprListForm.elements['workCalPmInsApprCommonDTO.filterPlantId'].value = loginUser.filterPlant;
	workCalPmInsApprListForm.elements['workCalPmInsApprCommonDTO.filterPlantDesc'].value = loginUser.filterPlantDesc;
	
	//담당자 
	workCalPmInsApprListForm.elements['workCalPmInsApprCommonDTO.filterDeptId'].value = loginUser.filterDeptId;
	workCalPmInsApprListForm.elements['workCalPmInsApprCommonDTO.filterDeptDesc'].value = loginUser.filterDeptDesc;

	//작성일자
	workCalPmInsApprListForm.elements['workCalPmInsApprCommonDTO.filterStartDate'].value = getMinusDay(7);
	workCalPmInsApprListForm.elements['workCalPmInsApprCommonDTO.filterEndDate'].value = getToday();
	
	//계획완료 구분
	if(workCalPmInsApprListForm.elements['workCalPmInsApprCommonDTO.pminsschedapprType'].value=='')
	{
		if(currentPageId == "workCalPmInsApprList")
		{
			workCalPmInsApprListForm.elements['workCalPmInsApprCommonDTO.pminsschedapprType'].value = "PLN"
		}else if(currentPageId == "workCalPmInsApprCompList")
		{
			workCalPmInsApprListForm.elements['workCalPmInsApprCommonDTO.pminsschedapprType'].value = "ACT"
		}else if(currentPageId == "workCalPmInsApprNotList")
		{
			workCalPmInsApprListForm.elements['workCalPmInsApprCommonDTO.pminsschedapprType'].value = "NOT"
		}else
		{
			workCalPmInsApprListForm.elements['workCalPmInsApprCommonDTO.pminsschedapprType'].value = "PLN";
		}
	}
	workCalPmInsApprListForm.elements['workCalPmInsApprCommonDTO.filterPminsschedapprType'].value = workCalPmInsApprListForm.elements['workCalPmInsApprCommonDTO.pminsschedapprType'].value;
	workCalPmInsApprListForm.elements['workCalPmInsApprCommonDTO.filterPminsschedapprTypeDesc'].value = workCalPmInsApprListForm.elements['workCalPmInsApprCommonDTO.pminsschedapprType'].value;
	valSysDir('workCalPmInsApprCommonDTO.filterPminsschedapprType', 'workCalPmInsApprCommonDTO.filterPminsschedapprTypeDesc', 'PMINSSCHEDAPPR_TYPE', true);

    initGrid();

    /** 요청부서  */
    deptAc = new autoC({"workCalPmInsApprCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
      , "plant":workCalPmInsApprListForm.elements['workCalPmInsApprCommonDTO.filterPlantId'].value
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "workCalPmInsApprCommonDTO.filterDeptId":"dept_id"
       ,"workCalPmInsApprCommonDTO.filterPlantId":"plant"
       ,"workCalPmInsApprCommonDTO.filterPlantDesc":"plantDesc"
    });
    deptAc.init();
    
 	// 공장코드
	plantAc = new autoC({"workCalPmInsApprCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "workCalPmInsApprCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
    // 점검계획승인 구분
    acSysDesc("workCalPmInsApprCommonDTO.filterPminsschedapprTypeDesc","workCalPmInsApprCommonDTO.filterPminsschedapprType","PMINSSCHEDAPPR_TYPE");
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
		workCalPmInsApprListForm.elements['workCalPmInsApprCommonDTO.pmInsSchedApprId'].value = "";
    	return sortColumn("workCalPmInsApprList", this, workCalPmInsApprListForm, "PMINSSCHEDAPPRID", ind, direction);
	}); 
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/workCalPmInsApprList.do";

    workCalPmInsApprListForm.elements['strutsAction'].value = '<%=WorkCalPmInsApprListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workCalPmInsApprListForm), "PMINSSCHEDAPPRID","Y");

}

/**
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pmInsSchedApprId)
{
	workCalPmInsApprListForm.elements['workCalPmInsApprCommonDTO.pmInsSchedApprId'].value = _pmInsSchedApprId;
	findGridList('ReloadRow');
	workCalPmInsApprListForm.elements['workCalPmInsApprCommonDTO.pmInsSchedApprId'].value = "";
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	workCalPmInsApprListForm.elements['workCalPmInsApprCommonDTO.pmInsSchedApprId'].value = "";	// 검색시 Tab 이동Key Clear
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
	var form = document.workCalPmInsApprListForm;

	form.elements['workCalPmInsApprCommonDTO.pmInsSchedApprId'].value = getValueById(myGrid, selectedId, 'PMINSSCHEDAPPRID');
	goCommonTabPage(form, <%= WorkCalPmInsApprDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세열기
 */
function goOpen()
{
	var detailPage = "";
	
	if(workCalPmInsApprListForm.elements['workCalPmInsApprCommonDTO.pminsschedapprType'].value == "PLN")
	{
		detailPage = "workCalPmInsApprDetail";
	}
	else if(workCalPmInsApprListForm.elements['workCalPmInsApprCommonDTO.pminsschedapprType'].value == "ACT")
	{
		detailPage = "workCalPmInsApprCompDetail";
	}
	else if(workCalPmInsApprListForm.elements['workCalPmInsApprCommonDTO.pminsschedapprType'].value == "NOT")
	{
		detailPage = "workCalPmInsApprNOTDetail";
	}
	else
	{
		detailPage = "workCalPmInsApprDetail";
	}
	
	goTabPage(detailPage);
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
	var detailPage = "";
	
	if(workCalPmInsApprListForm.elements['workCalPmInsApprCommonDTO.pminsschedapprType'].value == "PLN")
	{
		detailPage = "workCalPmInsApprDetail";
	}
	else if(workCalPmInsApprListForm.elements['workCalPmInsApprCommonDTO.pminsschedapprType'].value == "ACT")
	{
		detailPage = "workCalPmInsApprCompDetail";
	}
	else if(workCalPmInsApprListForm.elements['workCalPmInsApprCommonDTO.pminsschedapprType'].value == "NOT")
	{
		detailPage = "workCalPmInsApprNOTDetail";
	}
	else
	{
		detailPage = "workCalPmInsApprDetail";
	}
    
    workCalPmInsApprListForm.elements['workCalPmInsApprCommonDTO.pmInsSchedApprId'].value = getValueById(myGrid, selectedId, 'PMINSSCHEDAPPRID');
    workCalPmInsApprListForm.elements['strutsAction'].value = '<%=WorkCalPmInsApprDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(workCalPmInsApprListForm), detailPage); 
} 

/**
 * 생성
 */
function goCreate()
{
	var detailPage = "";
	
	if(workCalPmInsApprListForm.elements['workCalPmInsApprCommonDTO.pminsschedapprType'].value == "PLN")
	{
		detailPage = "workCalPmInsApprDetail";
	}
	else if(workCalPmInsApprListForm.elements['workCalPmInsApprCommonDTO.pminsschedapprType'].value == "ACT")
	{
		detailPage = "workCalPmInsApprCompDetail";
	}
	else if(workCalPmInsApprListForm.elements['workCalPmInsApprCommonDTO.pminsschedapprType'].value == "NOT")
	{
		detailPage = "workCalPmInsApprNOTDetail";
	}
	else
	{
		detailPage = "workCalPmInsApprDetail";
	}
		
	createValidationCheck(myGrid, detailPage, "goCreateAction");
}


function goCreateAction(pageId)
{
	workCalPmInsApprListForm.elements['workCalPmInsApprCommonDTO.pmInsSchedApprId'].value = "";
	goCommonTabPage(workCalPmInsApprListForm, '', pageId);
}

/**
 * Excel Export
 */
function goExcel()
{
	workCalPmInsApprListForm.elements['workCalPmInsApprCommonDTO.pmInsSchedApprId'].value = "";
 	excelServerAction("workCalPmInsApprList", workCalPmInsApprListForm );  
}

/**
 * 삭제
 */
function goDelete(){
	var chkedRowsId = getCheckedRows(myGrid, 'ISDELCHECK');
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'PMINSSCHEDAPPRID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	workCalPmInsApprListForm.strutsAction.value = '<%=WorkCalPmInsApprListAction.LIST_DELETE%>';
	var url = contextPath + "/workCalPmInsApprList.do";
	$.post(url,FormQueryString(workCalPmInsApprListForm)+delArray , function(_data){
    	afterDelete();
    });
}

function afterDelete()
{
	var detailPage = "";	
	if(workCalPmInsApprListForm.elements['workCalPmInsApprCommonDTO.pminsschedapprType'].value == "PLN")
	{
		detailPage = "workCalPmInsApprDetail";
	}
	else if(workCalPmInsApprListForm.elements['workCalPmInsApprCommonDTO.pminsschedapprType'].value == "ACT")
	{
		detailPage = "workCalPmInsApprCompDetail";
	}
	else if(workCalPmInsApprListForm.elements['workCalPmInsApprCommonDTO.pminsschedapprType'].value == "NOT")
	{
		detailPage = "workCalPmInsApprNOTDetail";
	}
	else
	{
		detailPage = "workCalPmInsApprDetail";
	}
    
	goClose(detailPage);
 	//goSearch();
 	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
 }
 
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = workCalPmInsApprListForm.elements['workCalPmInsApprCommonDTO.pmInsSchedApprId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/workCalPmInsApprList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="workCalPmInsApprCommonDTO.pmInsSchedApprId"/><!-- Key -->
<html:hidden property="workCalPmInsApprCommonDTO.filterDeptId"/>
<html:hidden property="workCalPmInsApprCommonDTO.filterPlantId"/>
<html:hidden property="workCalPmInsApprCommonDTO.filterPminsschedapprType"/>
<html:hidden property="workCalPmInsApprCommonDTO.pminsschedapprType" />

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
				<!-- 작성일자 -->
				<div class="field">
					<label><bean:message key="LABEL.repRegDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="workCalPmInsApprCommonDTO.filterStartDate" tabindex="10" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="workCalPmInsApprCommonDTO.filterEndDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 년 -->
	        	<div class="field">
					<label><bean:message key="LABEL.year"/></label>
					<div class="input_box">
						<html:text property="workCalPmInsApprCommonDTO.filterYyyy" tabindex="30" />
						<p class="open_year_calendar"><span>날짜</span></p>
					</div>
				</div>
				<!-- 년월 -->
				<div class="field">
	        	 	<label><bean:message key="LABEL.yyyymm"/></label>
	        	 	<div class="input_box">
	        	 		<html:text property="workCalPmInsApprCommonDTO.filterYyyymm" tabindex="40"/>
					    <p class="open_mon_calendar"><span>날짜</span></p>
	        	 	</div>
	        	</div>
				<!-- 공장명 -->
				<div class="field">
					<label><bean:message key="LABEL.plantDesc"/></label>
					<div class="input_box">
						<html:text property="workCalPmInsApprCommonDTO.filterPlantDesc" tabindex="50"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 요청부서 -->
				<div class="field">
					<label><bean:message key="LABEL.dept"/></label>
					<div class="input_box">
						<html:text property="workCalPmInsApprCommonDTO.filterDeptDesc" tabindex="60"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 계획/완료 구분 -->
				<div class="field">
	                <label><bean:message key="LABEL.pmInsSchedApprType"/></label>
	                <div class="input_box">
	                    <html:text property="workCalPmInsApprCommonDTO.filterPminsschedapprTypeDesc" tabindex="70" />
	                    <p class="open_spop"><a><span>조회</span></a></p>
	                </div>
	            </div>
				<!-- 제목 -->
				<div class="field">
					<label><bean:message key="LABEL.title"/></label>
					<div class="input_box">
						<html:text property="workCalPmInsApprCommonDTO.filterDesc" tabindex="80"/>
					</div>
				</div>
				<!-- 승인번호 -->
				<div class="field">
					<label><bean:message key="LABEL.pmInsSchedApprNo"/></label>
					<div class="input_box">
						<html:text property="workCalPmInsApprCommonDTO.filterPmInsSchedApprNo" tabindex="90"/>
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
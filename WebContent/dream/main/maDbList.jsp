<%--===========================================================================
Dashboard - 목록
author  kim21017
version $Id: maDbList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.main.action.MaDbListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%
	session.setAttribute("noticeDiplayed","Y");
%>
<html>
<head>
<!-- Notice -->
<title><bean:message key='LABEL.notice'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

function loadPage() 
{
    initGrid();
}
/**
 * 그리드 초기화
 */
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');

	myGrid.attachEvent("onXLE",function(grdObj,count){setCounter(grdObj,"gridbox")});
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		//goOpen();
	});
	myGrid.attachEvent("onRowDblClicked",function(rowId, columnId){
		goOpen();
	});
	
	myGrid.init();
	setHeader(myGrid, "gridbox", "goSearch"); // grid, grid id

}

function goOpenAction()
{
	goOpen();
}

function goOpen()
{
	var selectedId=myGrid.getSelectedRowId();
 	if(selectedId == null) return;
 	
 	var typeCode = getValueById(myGrid, selectedId, "TYPE");
 	var td = new Date();
 	
 	if(typeCode == "NWD") //미완료 작업 오늘
 	{
 		var param = "maWoResultMstrCommonDTO.filterStartDate="+td.format('yyyyMMdd')
 		            +"&maWoResultMstrCommonDTO.filterEndDate="+td.format('yyyyMMdd')
 		            +"&maWoResultMstrCommonDTO.filterEmpId="+loginUser.filterEmpId
 		            +"&maWoResultMstrCommonDTO.filterEmpDesc="+loginUser.filterEmpDesc
         			+"&maWoResultMstrCommonDTO.filterDeptId="+loginUser.filterDeptId
         			+"&maWoResultMstrCommonDTO.filterDeptDesc="+loginUser.filterDeptDesc
         			+"&maWoResultMstrCommonDTO.filterEqLocId="+loginUser.eqLocId
         			+"&maWoResultMstrCommonDTO.filterEqLocDesc="+loginUser.eqLocDesc
         			+"&maWoResultMstrCommonDTO.filterWkCtrId="+loginUser.filterWkCtrId
         			+"&maWoResultMstrCommonDTO.filterWkCtrDesc="+loginUser.filterWkCtrDesc
         			+"&maWoResultMstrCommonDTO.filterEqCtgTypeId="+loginUser.eqCtgTypeId
         			+"&maWoResultMstrCommonDTO.filterEqCtgTypeDesc="+loginUser.eqCtgTypeDesc
         			+"&maWoResultMstrCommonDTO.filterPlantId="+loginUser.plant
         			+"&maWoResultMstrCommonDTO.filterPlantDesc="+loginUser.plantDesc
         			+"&maWoResultMstrCommonDTO.notWoTypeId=PMI"
 		            +"&maWoResultMstrCommonDTO.filterWoStatus=P"
 		            +"&IS_FROM_NOTICE=Y";
 		getIframeContent().goMenuPage("maWoResultMstrList?"+param);
 	}
 	else if(typeCode == "NCD") //미완료 분해 점검 오늘
 	{
 		var param = "maWoResultMstrCommonDTO.filterStartDate="+td.format('yyyyMMdd')
         			+"&maWoResultMstrCommonDTO.filterEndDate="+td.format('yyyyMMdd')
         			+"&maWoResultMstrCommonDTO.filterEmpId="+loginUser.filterEmpId
         			+"&maWoResultMstrCommonDTO.filterEmpDesc="+loginUser.filterEmpDesc
         			+"&maWoResultMstrCommonDTO.filterDeptId="+loginUser.filterDeptId
         			+"&maWoResultMstrCommonDTO.filterDeptDesc="+loginUser.filterDeptDesc
         			+"&maWoResultMstrCommonDTO.filterEqLocId="+loginUser.eqLocId
         			+"&maWoResultMstrCommonDTO.filterEqLocDesc="+loginUser.eqLocDesc
         			+"&maWoResultMstrCommonDTO.filterWkCtrId="+loginUser.filterWkCtrId
         			+"&maWoResultMstrCommonDTO.filterWkCtrDesc="+loginUser.filterWkCtrDesc
         			+"&maWoResultMstrCommonDTO.filterEqCtgTypeId="+loginUser.eqCtgTypeId
         			+"&maWoResultMstrCommonDTO.filterEqCtgTypeDesc="+loginUser.eqCtgTypeDesc
         			+"&maWoResultMstrCommonDTO.filterPlantId="+loginUser.plant
         			+"&maWoResultMstrCommonDTO.filterPlantDesc="+loginUser.plantDesc
         			+"&maWoResultMstrCommonDTO.filterWoTypeId=PMI"
         			+"&maWoResultMstrCommonDTO.filterWoStatus=P"
         			+"&IS_FROM_NOTICE=Y";
		getIframeContent().goMenuPage("workListPmiList?"+param);
 	}
 	else if(typeCode == "NRINSD") //미완료 정기 점검 오늘
 	{
 		var param = "workPmiCommonDTO.filterStartDate="+td.format('yyyyMMdd')
         			+"&workPmiCommonDTO.filterEndDate="+td.format('yyyyMMdd')
         			+"&workPmiCommonDTO.filterManagerId="+loginUser.filterEmpId
         			+"&workPmiCommonDTO.filterManagerDesc="+loginUser.filterEmpDesc
         			+"&workPmiCommonDTO.filterDeptId="+loginUser.filterDeptId
         			+"&workPmiCommonDTO.filterDeptDesc="+loginUser.filterDeptDesc
         			+"&workPmiCommonDTO.filterEqLocId="+loginUser.eqLocId
         			+"&workPmiCommonDTO.filterEqLocDesc="+loginUser.eqLocDesc
         			+"&workPmiCommonDTO.filterWkCtrId="+loginUser.filterWkCtrId
         			+"&workPmiCommonDTO.filterWkCtrDesc="+loginUser.filterWkCtrDesc
         			+"&workPmiCommonDTO.filterEqCtgTypeId="+loginUser.eqCtgTypeId
         			+"&workPmiCommonDTO.filterEqCtgTypeDesc="+loginUser.eqCtgTypeDesc
         			+"&workPmiCommonDTO.filterPlantId="+loginUser.plant
         			+"&workPmiCommonDTO.filterPlantDesc="+loginUser.plantDesc
         			+"&workPmiCommonDTO.notPmschedStatusId=C"
         			+"&IS_FROM_NOTICE=Y";
		getIframeContent().goMenuPage("workPmiList?"+param);
 	}
 	else if(typeCode == "NDINSD") //미완료 일상 점검 오늘
 	{
 		var param = "workPmiDInsCommonDTO.filterStartDate="+td.format('yyyyMMdd')
         			+"&workPmiDInsCommonDTO.filterEndDate="+td.format('yyyyMMdd')
         			+"&workPmiDInsCommonDTO.filterEmpId="+loginUser.filterEmpId
         			+"&workPmiDInsCommonDTO.filterEmpDesc="+loginUser.filterEmpDesc
         			+"&workPmiDInsCommonDTO.filterDeptId="+loginUser.filterDeptId
         			+"&workPmiDInsCommonDTO.filterDeptDesc="+loginUser.filterDeptDesc
         			+"&workPmiDInsCommonDTO.filterEqLocId="+loginUser.eqLocId
         			+"&workPmiDInsCommonDTO.filterEqLocDesc="+loginUser.eqLocDesc
         			+"&workPmiDInsCommonDTO.filterWkCtrId="+loginUser.filterWkCtrId
         			+"&workPmiDInsCommonDTO.filterWkCtrDesc="+loginUser.filterWkCtrDesc
         			+"&workPmiDInsCommonDTO.filterEqCtgTypeId="+loginUser.eqCtgTypeId
         			+"&workPmiDInsCommonDTO.filterEqCtgTypeDesc="+loginUser.eqCtgTypeDesc
         			+"&workPmiDInsCommonDTO.notPmschedStatusId=C"
         			+"&IS_FROM_NOTICE=Y";
		getIframeContent().goMenuPage("workPmiDInsList?"+param);
 	}
 	else if(typeCode == "NPMCD") //미완료 교정작업 오늘
 	{
 		var param = "maWoResultMstrCommonDTO.filterStartDate="+td.format('yyyyMMdd')
         			+"&maWoResultMstrCommonDTO.filterEndDate="+td.format('yyyyMMdd')
         			+"&maWoResultMstrCommonDTO.filterEmpId="+loginUser.filterEmpId
         			+"&maWoResultMstrCommonDTO.filterEmpDesc="+loginUser.filterEmpDesc
         			+"&maWoResultMstrCommonDTO.filterDeptId="+loginUser.filterDeptId
         			+"&maWoResultMstrCommonDTO.filterDeptDesc="+loginUser.filterDeptDesc
         			+"&maWoResultMstrCommonDTO.filterEqLocId="+loginUser.eqLocId
         			+"&maWoResultMstrCommonDTO.filterEqLocDesc="+loginUser.eqLocDesc
         			+"&maWoResultMstrCommonDTO.filterWkCtrId="+loginUser.filterWkCtrId
         			+"&maWoResultMstrCommonDTO.filterWkCtrDesc="+loginUser.filterWkCtrDesc
         			+"&maWoResultMstrCommonDTO.filterEqCtgTypeId="+loginUser.eqCtgTypeId
         			+"&maWoResultMstrCommonDTO.filterEqCtgTypeDesc="+loginUser.eqCtgTypeDesc
//        			+"&maWoResultMstrCommonDTO.notPmschedStatusId=C"
         			+"&IS_FROM_NOTICE=Y";
		getIframeContent().goMenuPage("maWoPmcResultMstrList?"+param);
 	}
 	else if(typeCode == "NWW") //미완료 작업 금주
 	{
 		var param = "maWoResultMstrCommonDTO.filterEndDate="+td.format('yyyyMMdd') 
 					+"&maWoResultMstrCommonDTO.filterStartDate="+td.getMonday().format('yyyyMMdd')
 					+"&maWoResultMstrCommonDTO.filterEmpId="+loginUser.filterEmpId
 		            +"&maWoResultMstrCommonDTO.filterEmpDesc="+loginUser.filterEmpDesc
         			+"&maWoResultMstrCommonDTO.filterDeptId="+loginUser.filterDeptId
         			+"&maWoResultMstrCommonDTO.filterDeptDesc="+loginUser.filterDeptDesc
         			+"&maWoResultMstrCommonDTO.filterEqLocId="+loginUser.eqLocId
         			+"&maWoResultMstrCommonDTO.filterEqLocDesc="+loginUser.eqLocDesc
         			+"&maWoResultMstrCommonDTO.filterWkCtrId="+loginUser.filterWkCtrId
         			+"&maWoResultMstrCommonDTO.filterWkCtrDesc="+loginUser.filterWkCtrDesc
         			+"&maWoResultMstrCommonDTO.filterEqCtgTypeId="+loginUser.eqCtgTypeId
         			+"&maWoResultMstrCommonDTO.filterEqCtgTypeDesc="+loginUser.eqCtgTypeDesc
         			+"&maWoResultMstrCommonDTO.filterPlantId="+loginUser.plant
         			+"&maWoResultMstrCommonDTO.filterPlantDesc="+loginUser.plantDesc
         			+"&maWoResultMstrCommonDTO.notWoTypeId=PMI"
         			+"&maWoResultMstrCommonDTO.filterWoStatus=P"
         			+"&IS_FROM_NOTICE=Y";
		getIframeContent().goMenuPage("maWoResultMstrList?"+param);
 	}
 	else if(typeCode == "NCW") //미완료 분해 점검 금주
 	{
 		var param = "maWoResultMstrCommonDTO.filterEndDate="+td.format('yyyyMMdd')
 					+"&maWoResultMstrCommonDTO.filterStartDate="+td.getMonday().format('yyyyMMdd')
 					+"&maWoResultMstrCommonDTO.filterEmpId="+loginUser.filterEmpId
 		            +"&maWoResultMstrCommonDTO.filterEmpDesc="+loginUser.filterEmpDesc
         			+"&maWoResultMstrCommonDTO.filterDeptId="+loginUser.filterDeptId
         			+"&maWoResultMstrCommonDTO.filterDeptDesc="+loginUser.filterDeptDesc
         			+"&maWoResultMstrCommonDTO.filterEqLocId="+loginUser.eqLocId
         			+"&maWoResultMstrCommonDTO.filterEqLocDesc="+loginUser.eqLocDesc
         			+"&maWoResultMstrCommonDTO.filterWkCtrId="+loginUser.filterWkCtrId
         			+"&maWoResultMstrCommonDTO.filterWkCtrDesc="+loginUser.filterWkCtrDesc
         			+"&maWoResultMstrCommonDTO.filterEqCtgTypeId="+loginUser.eqCtgTypeId
         			+"&maWoResultMstrCommonDTO.filterEqCtgTypeDesc="+loginUser.eqCtgTypeDesc
         			+"&maWoResultMstrCommonDTO.filterPlantId="+loginUser.plant
         			+"&maWoResultMstrCommonDTO.filterPlantDesc="+loginUser.plantDesc
 					+"&maWoResultMstrCommonDTO.filterWoTypeId=PMI"
         			+"&maWoResultMstrCommonDTO.filterWoStatus=P"
         			+"&IS_FROM_NOTICE=Y";
		getIframeContent().goMenuPage("workListPmiList?"+param);
 	}
 	else if(typeCode == "NRINSW") //미완료 정기 점검 금주
 	{
 		var param = "workPmiCommonDTO.filterEndDate="+td.format('yyyyMMdd')
 					+"&workPmiCommonDTO.filterStartDate="+td.getMonday().format('yyyyMMdd')
 					+"&workPmiCommonDTO.filterManagerId="+loginUser.filterEmpId
         			+"&workPmiCommonDTO.filterManagerDesc="+loginUser.filterEmpDesc
         			+"&workPmiCommonDTO.filterDeptId="+loginUser.filterDeptId
         			+"&workPmiCommonDTO.filterDeptDesc="+loginUser.filterDeptDesc
         			+"&workPmiCommonDTO.filterEqLocId="+loginUser.eqLocId
         			+"&workPmiCommonDTO.filterEqLocDesc="+loginUser.eqLocDesc
         			+"&workPmiCommonDTO.filterWkCtrId="+loginUser.filterWkCtrId
         			+"&workPmiCommonDTO.filterWkCtrDesc="+loginUser.filterWkCtrDesc
         			+"&workPmiCommonDTO.filterEqCtgTypeId="+loginUser.eqCtgTypeId
         			+"&workPmiCommonDTO.filterEqCtgTypeDesc="+loginUser.eqCtgTypeDesc
         			+"&workPmiCommonDTO.filterPlantId="+loginUser.plant
         			+"&workPmiCommonDTO.filterPlantDesc="+loginUser.plantDesc
         			+"&workPmiCommonDTO.notPmschedStatusId=C"
         			+"&IS_FROM_NOTICE=Y";
		getIframeContent().goMenuPage("workPmiList?"+param);
 	}
 	else if(typeCode == "NPMCW") //미완료 교정작업 금주
 	{
 		var param = "maWoResultMstrCommonDTO.filterEndDate="+td.format('yyyyMMdd')
 					+"&maWoResultMstrCommonDTO.filterStartDate="+td.getMonday().format('yyyyMMdd')
 					+"&maWoResultMstrCommonDTO.filterManagerId="+loginUser.filterEmpId
         			+"&maWoResultMstrCommonDTO.filterManagerDesc="+loginUser.filterEmpDesc
         			+"&maWoResultMstrCommonDTO.filterDeptId="+loginUser.filterDeptId
         			+"&maWoResultMstrCommonDTO.filterDeptDesc="+loginUser.filterDeptDesc
         			+"&maWoResultMstrCommonDTO.filterEqLocId="+loginUser.eqLocId
         			+"&maWoResultMstrCommonDTO.filterEqLocDesc="+loginUser.eqLocDesc
         			+"&maWoResultMstrCommonDTO.filterWkCtrId="+loginUser.filterWkCtrId
         			+"&maWoResultMstrCommonDTO.filterWkCtrDesc="+loginUser.filterWkCtrDesc
         			+"&maWoResultMstrCommonDTO.filterEqCtgTypeId="+loginUser.eqCtgTypeId
         			+"&maWoResultMstrCommonDTO.filterEqCtgTypeDesc="+loginUser.eqCtgTypeDesc
         			+"&maWoResultMstrCommonDTO.filterPlantId="+loginUser.plant
         			+"&maWoResultMstrCommonDTO.filterPlantDesc="+loginUser.plantDesc
//          		+"&maWoResultMstrCommonDTO.notPmschedStatusId=C"
         			+"&IS_FROM_NOTICE=Y";
		getIframeContent().goMenuPage("maWoPmcResultMstrList?"+param);
 	}
 	else if(typeCode == "NWREQ") //미완료 작업요청
 	{
 		var param = "&reqWorkCommonDTO.filterReqEmpId="+loginUser.filterEmpId
         			+"&reqWorkCommonDTO.filterReqEmpDesc="+loginUser.filterEmpDesc
         			+"&reqWorkCommonDTO.filterReqDeptId="+loginUser.filterDeptId
         			+"&reqWorkCommonDTO.filterReqDeptDesc="+loginUser.filterDeptDesc
         			+"&reqWorkCommonDTO.filterEqLocId="+loginUser.eqLocId
         			+"&reqWorkCommonDTO.filterEqLocDesc="+loginUser.eqLocDesc
         			+"&reqWorkCommonDTO.filterPlantId="+loginUser.plant
         			+"&reqWorkCommonDTO.filterPlantDesc="+loginUser.plantDesc
         			+"&reqWorkCommonDTO.notWoReqStatusId=COM+INC"
         			+"&IS_FROM_NOTICE=Y";
		getIframeContent().goMenuPage("reqWorkList?"+param);
 	}
 	else if(typeCode == "USQ") //안전재고미만건수
 	{
 		var param = "maPtStckCommonDTO.filterSaftyYN=Y"
 					+"&maPtStckCommonDTO.filterPlantId="+loginUser.plant
 					+"&maPtStckCommonDTO.filterPlantDesc="+loginUser.plantDesc
 					+"&maPtStckCommonDTO.filterWcodeId="+loginUser.filterWcodeId
 					+"&maPtStckCommonDTO.filterWname="+loginUser.filterWcodeDesc
 					+"&IS_FROM_NOTICE=Y";
		getIframeContent().goMenuPage("maPtStckList?"+param);
 	}
 	else if(typeCode == "SPMD") //New S/Parts in SAP(Today)
 	{
 		var param = "maPtMstrCommonDTO.sapParts=DAY"
 					+"&IS_FROM_NOTICE=Y";
		getIframeContent().goMenuPage("maPtMstrList?"+param);
 	}
 	else if(typeCode == "SPMW") //New S/Parts in SAP(This Week)
 	{
 		var param = "maPtMstrCommonDTO.sapParts=WEEK"
 					+"&IS_FROM_NOTICE=Y";
		getIframeContent().goMenuPage("maPtMstrList?"+param);
 	}
 	else if(typeCode == "SPRD") //GR today
 	{
 		var param = "maIfPtRecCommonDTO.budatFrom="+td.format('yyyyMMdd')
 					+ "&maIfPtRecCommonDTO.budatTo="+td.format('yyyyMMdd')
 					+"&IS_FROM_NOTICE=Y";
		getIframeContent().goMenuPage("maIfPtRecList?"+param);
 	}
 	else if(typeCode == "SPRW") //GR (This Week)
 	{
 		var param = "maIfPtRecCommonDTO.budatFrom="+td.getMonday().format('yyyy-MM-dd')
					+ "&maIfPtRecCommonDTO.budatTo="+td.getSunday().format('yyyy-MM-dd')
					+"&IS_FROM_NOTICE=Y";
		getIframeContent().goMenuPage("maIfPtRecList?"+param);
 	}
 	else if(typeCode == "APPR") //결재대기
 	{
 		var param = "&IS_FROM_NOTICE=Y";
		getIframeContent().goMenuPage("appReadyList?"+param);
 	}
 	else if(typeCode == "NOTICE") //공지사항
 	{
 		var param = "docNoticeCommonDTO.filterNoticePeriodFromDate="+td.format('yyyyMMdd')
					+ "&docNoticeCommonDTO.filterReadYn=N"
					+ "&docNoticeCommonDTO.filterEmpId="+loginUser.empId
					+"&IS_FROM_NOTICE=Y";
		getIframeContent().goMenuPage("docNoticeCheckList?"+param);
 	}
 	else if(typeCode == "EQREVW") // 변경설비(금주)
 	{
 		var param =  "maEqMstrCommonDTO.filterStartDate="+td.getMonday().format('yyyy-MM-dd')
				   + "&maEqMstrCommonDTO.filterEndDate="+td.getSunday().format('yyyy-MM-dd')
				   + "&maEqMstrCommonDTO.filterIsLastVersionId=Y"
				   + "&maEqMstrCommonDTO.filterPlantId="+ loginUser.plant
				   + "&IS_FROM_NOTICE=Y";
		getIframeContent().goMenuPage("maEqMachMstrList?"+param);
 	}
 	else if(typeCode == "NWP") //미완료 작업 과거
 	{
 		var param = "maWoResultMstrCommonDTO.filterStartDate="+"-1"
 		            +"&maWoResultMstrCommonDTO.filterEndDate="+td.format('yyyyMMdd')
 		            +"&maWoResultMstrCommonDTO.filterEmpId="+loginUser.filterEmpId
 		            +"&maWoResultMstrCommonDTO.filterEmpDesc="+loginUser.filterEmpDesc
         			+"&maWoResultMstrCommonDTO.filterDeptId="+loginUser.filterDeptId
         			+"&maWoResultMstrCommonDTO.filterDeptDesc="+loginUser.filterDeptDesc
         			+"&maWoResultMstrCommonDTO.filterEqLocId="+loginUser.eqLocId
         			+"&maWoResultMstrCommonDTO.filterEqLocDesc="+loginUser.eqLocDesc
         			+"&maWoResultMstrCommonDTO.filterWkCtrId="+loginUser.filterWkCtrId
         			+"&maWoResultMstrCommonDTO.filterWkCtrDesc="+loginUser.filterWkCtrDesc
         			+"&maWoResultMstrCommonDTO.filterEqCtgTypeId="+loginUser.eqCtgTypeId
         			+"&maWoResultMstrCommonDTO.filterEqCtgTypeDesc="+loginUser.eqCtgTypeDesc
         			+"&maWoResultMstrCommonDTO.filterPlantId="+loginUser.plant
         			+"&maWoResultMstrCommonDTO.filterPlantDesc="+loginUser.plantDesc
         			+"&maWoResultMstrCommonDTO.notWoTypeId=PMI"
 		            +"&maWoResultMstrCommonDTO.filterWoStatus=P"
 		            +"&IS_FROM_NOTICE=Y";
 		getIframeContent().goMenuPage("maWoResultMstrList?"+param);
 	}
 	else if(typeCode == "NREC") // 작업요청접수대기
 	{
 		var param =  "maWoReqCommonDTO.filterWoReqStatusId=REQ"
 				   +"&maWoReqCommonDTO.filterWoReqStatusDesc=요청"
 		           +"&maWoReqCommonDTO.filterPlantId="+loginUser.filterPlant
 		           +"&maWoReqCommonDTO.filterPlantDesc="+loginUser.filterPlantDesc
 		           +"&maWoReqCommonDTO.filterRecDeptId="+loginUser.filterDeptId
 		           +"&maWoReqCommonDTO.filterRecDeptDesc="+loginUser.filterDeptDesc
 		           +"&maWoReqCommonDTO.filterRecEmpId="+loginUser.filterEmpId 
 		           +"&maWoReqCommonDTO.filterRecEmpDesc="+loginUser.filterEmpDesc
 		           +"&maWoReqCommonDTO.filterReqStartDate="+getMinusMonth2(new Date(), -12)
 		           +"&maWoReqCommonDTO.filterReqEndDate="+td.format('yyyy-MM-dd')
 		           +"&IS_FROM_NOTICE=Y"; 
 		getIframeContent().goMenuPage("maWoReqList?"+param);
 	}
}

function goApprove()
{
	//1일동안 숨김
	//setCookie("dashboard", "HIDE", 0);

	goCloseLayer();
	
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maDbList.do";
    maDbListForm.elements['strutsAction'].value = '<%=MaDbListAction.DASHBOARD_LIST_FIND%>';

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maDbListForm), "eqLocId");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_eqLocId)
{
	maDbListForm.elements['maDbListDTO.eqLocId'].value = _eqLocId;
	findGridList('ReloadTreeRow');
	maDbListForm.elements['maDbListDTO.eqLocId'].value = "";
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	//maDbListForm.elements['maDbListDTO.eqLocId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search');   
}

function afterSelectClose()
{
	//var topPage = getTopPage();
	//topPage.noticeYn = true;
	//setCookie("dashboard", "HIDE", 0);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maDbList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maDbListDTO.compNo"/><!-- Key -->

    <div class="section_wrap">
            <div class="sheader_box">
            <div class="stitle_wrap" >
            	<div class="view_icon"></div>
            	<div class="stitle_tx"><bean:message key="LABEL.notice"/></div>
            </div>
            <div class="function_box list">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>
				<div class="fb_group2">
				</div>
				<div class="b_line"></div> 
				<div class="fb_group1">
					 
				</div>
			</div>
<!-- 			
            <div class="function_box list">
            	<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>
				<div class="fb_group2">
					
				</div>
				<div class="b_line" style="display:none"></div>
            </div> -->
        </div><!--sheader_box end-->
        <div class="article_box" id="listBox">
            <div class="grid_area">
                <div id="gridbox" style="width:100%; height:280px; background-color:white;"></div>
            </div>
        </div>
	 </div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>
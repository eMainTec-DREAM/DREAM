<%--===========================================================================
안전작업허가서유형 - 점검항목 목록
author  syyang
version $Id: workLetPermitPointList.jsp,v 1.1 2015/12/03 01:45:27 syyang Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.let.permit.action.WorkLetPermitPointListAction" %>
<%@ page import="dream.work.let.permit.action.WorkLetPermitPointDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 점검항목 -->
<title><bean:message key='TAB.maEqMstrPmInsPointList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

var stdPointAc;

function loadPage() 
{
    initGrid();

	// 표준항목선택
	stdPointAc = new autoC({"workLetPermitPointDetailDTO.multiDesc":"checkPoint"});
	stdPointAc.setTable("TAWOLETCTGPOINT");
	stdPointAc.setAcConditionMap({
        "comp_no" : loginUser.compNo
    })
    stdPointAc.setAcDConditionMap({
    	"woletctg_type":"workLetPermitDetailDTO.woLetCtgType"
    })
	stdPointAc.setAcResultMap({
        "workLetPermitPointDetailDTO.multiKey":"woLetCtgPointId"
    });
	stdPointAc.setMultiSelect(true);
	stdPointAc.init();
	
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("workLetPermitPointDetail");
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		workLetPermitPointListForm.elements['workLetPermitPointListDTO.woLetListPointId'].value = "";
    	return sortColumn("workLetPermitPointList", this, workLetPermitPointListForm, "WOLETLISTPOINTID", ind, direction);
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

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
  	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (workLetPermitPointListForm.elements['workLetPermitDetailDTO.woLetListId'].value == '') return;
  	var form = document.workLetPermitPointListForm;	
	form.strutsAction.value = '<%=WorkLetPermitPointListAction.LIST_FIND%>';
	
	var url = contextPath + "/workLetPermitPointList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workLetPermitPointListForm), "WOLETLISTPOINTID", "Y");
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
	workLetPermitPointListForm.elements['workLetPermitPointListDTO.woLetListPointId'].value =  getValueById(myGrid, selectedId,'woLetListPointId');  
	goCommonTabPage(workLetPermitPointListForm, <%= WorkLetPermitPointDetailAction.DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_woLetListPointId)
{
	workLetPermitPointListForm.elements['workLetPermitPointListDTO.woLetListPointId'].value = _woLetListPointId;
	findGridList('ReloadRow');
	workLetPermitPointListForm.elements['workLetPermitPointListDTO.woLetListPointId'].value = "";
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('workLetPermitPointDetail');
}

function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
 	if(selectedId == null) return;

 	workLetPermitPointListForm.elements['workLetPermitPointListDTO.woLetListPointId'].value = getValueById(myGrid, selectedId,'woLetListPointId');
 	workLetPermitPointListForm.elements['strutsAction'].value = '<%=WorkLetPermitPointDetailAction.DETAIL_INIT%>';
 	openQuickTabPage(FormQueryString(workLetPermitPointListForm), 'workLetPermitPointDetail'); 
 	
}

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "workLetPermitPointDetail", "goCreateAction");
}

function goCreateAction(pageId)
{
	workLetPermitPointListForm.elements['workLetPermitPointListDTO.woLetListPointId'].value = "";
    goCommonTabPage(workLetPermitPointListForm, '', pageId);
}
 
/**
 * Excel Export
 */
function goExcel()
{
	workLetPermitPointListForm.elements['workLetPermitPointListDTO.woLetListPointId'].value = "";
	excelServerAction("workLetPermitPointList", workLetPermitPointListForm );  
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'woLetListPointId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    workLetPermitPointListForm.strutsAction.value = '<%=WorkLetPermitPointListAction.LIST_DELETE%>';
    var url = contextPath + "/workLetPermitPointList.do";
    
    $.post(url,FormQueryString(workLetPermitPointListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('workLetPermitPointDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * 표준점검항목선택
 */
function goCopy()
{
 	stdPointAc.openLov();
}   

function setAcLovValue(rtnArr, code)
{
	afterSetValue();
}

/**
 * 팝업 값 선택 후 DB INSERT
 */
function afterSetValue(lovType,rtnValue)
{
	workLetPermitPointListForm.strutsAction.value = '<%=WorkLetPermitPointListAction.WO_LET_STDPOINT_LIST_INPUT%>';
	
	var url = contextPath + "/workLetPermitPointList.do";
	
    $.post(url,FormQueryString(workLetPermitPointListForm), function(_data){
    	goSearch();
    });
}

//-->
</script>


</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workLetPermitPointList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="workLetPermitDetailDTO.woLetListId"/>
<html:hidden property="workLetPermitDetailDTO.woLetCtgType"/>
<html:hidden property="workLetPermitPointListDTO.woLetListPointId"/><!-- Key -->
<html:hidden property="workLetPermitPointDetailDTO.multiKey"/>
<html:hidden property="workLetPermitPointDetailDTO.multiDesc"/>
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
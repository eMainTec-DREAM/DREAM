<%--===========================================================================
예방점검 실행 상세 목록
author  sy.yang
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.pmiequipcmptrate.action.WorkRptPmiEquipCmptDetailListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 예방점검설비 실행 비율 -->
<title><bean:message key='LABEL.plant'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;
var selectedRowId;

function loadPage() 
{
	setTitle("workRptPmiEquipCmptDetailListDTO.plantDesc", "workRptPmiEquipCmptDetailListDTO.yyyymm");
    
	initGrid();
	
}
/**
 * 그리드 초기화
 */
function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    myGrid.enableSmartRendering(true,500);
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		selectedRowId = rowId;    	
    });
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
        return sortColumn("workRptPmiEquipCmptDetailList", this, workRptPmiEquipCmptDetailListForm, "PMINSLISTNO", ind, direction);
    });
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
    myGrid.init();
    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	var url = contextPath + "/workRptPmiEquipCmptDetailList.do";
	workRptPmiEquipCmptDetailListForm.elements['strutsAction'].value = '<%=WorkRptPmiEquipCmptDetailListAction.DETAIL_LIST_FIND%>';
	
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workRptPmiEquipCmptDetailListForm), "PMINSLISTNO", "Y");
}

function goSearch()
{
    findGridList('Search');
}

/**
 * Excel Export
 */
function goExcel()
{	
   excelServerAction("workRptPmiEquipCmptDetailList", workRptPmiEquipCmptDetailListForm);
}

/* 
 * 설비보기
 */
function goMachequipmentLink(_pageId)
{
	var equipId = getValueById(myGrid, selectedRowId,'EQUIPID');
	var eqctgType = getValueById(myGrid, selectedRowId,'EQCTGTYPE');

	if(typeof equipId == "undefined" || equipId == "") {
		goMachEquipList();
		return;
	}else{
		goEquipDetail(equipId, eqctgType);
	}
}
 
/*
 * 점검 작업 상세 보기
 */
function goWopmiLink()
{
	var pmInsListId = getValueById(myGrid, selectedRowId, "PMINSLISTNO");
	var url = "workPmiDetail";
	
	if(typeof pmInsListId == "undefined" || typeof url == "undefined") return;
	
	goWopmi(pmInsListId, url);
}


//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workRptPmiEquipCmptDetailList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="workRptPmiEquipCmptDetailListDTO.yyyymm"/>
<html:hidden property="workRptPmiEquipCmptDetailListDTO.plantId"/>
<html:hidden property="workRptPmiEquipCmptDetailListDTO.plantDesc"/>

		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
		
</html:form> 
</body>
</html>


<%--===========================================================================
예방정비 계획대비 실행 비율 상세
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.pmiequipplanrate.action.WorkRptPmiEquipPlanDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 고장예방정비 계획대비 실행 비율 상세 -->
<title><bean:message key='LABEL.plant'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var selectedRowId;
function loadPage() 
{
	setTitle("workRptPmiEquipPlanDetailDTO.plantDesc", "workRptPmiEquipPlanDetailDTO.yyyymm");
	
	initGrid();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		selectedRowId = rowId;
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
        return sortColumn("workRptPmiEquipPlanDetailList", this, workRptPmiEquipPlanDetailForm, "PMINSLISTID", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox","goSearch"); // grid, grid id, callBack
}


function goSearch()
{
	findGridList('Search');   
}

function findGridList(gridAction)
{
	
	var form = document.workRptPmiEquipPlanDetailForm;	
	form.strutsAction.value = '<%=WorkRptPmiEquipPlanDetailAction.DETAIL_FIND %>';

    var url = contextPath + "/workRptPmiEquipPlanDetailList.do";
    
    doGridAction(gridAction, myGrid, "gridbox", url, FormQueryString(workRptPmiEquipPlanDetailForm),"PMINSLISTID","Y");
}

/**
 * 엑셀 다운.
 */
function goExcel()
{
	excelServerAction("workRptPmiEquipPlanDetailList", workRptPmiEquipPlanDetailForm );
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
	var pmInsListId = getValueById(myGrid, selectedRowId, "pmInsListId");
	var url = "workPmiDetail";
	
	if(typeof pmInsListId == "undefined" || typeof url == "undefined") return;
	
	goWopmi(pmInsListId, url);
}

/*
 * 기준서 보기 WOPMPRESULT
 */
function goPmstdLink()
{
	var fileName = getValueById(myGrid, selectedRowId,'PARAM');
	var pmId     = getValueById(myGrid, selectedRowId,'PMID');  

	goPmstdList(pmId, fileName);
}
 
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workRptPmiEquipPlanDetailList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="workRptPmiEquipPlanDetailDTO.plantId"/>
<html:hidden property="workRptPmiEquipPlanDetailDTO.plantDesc"/>
<html:hidden property="workRptPmiEquipPlanDetailDTO.yyyymm"/>

		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
		
</html:form> 
</body>
</html>
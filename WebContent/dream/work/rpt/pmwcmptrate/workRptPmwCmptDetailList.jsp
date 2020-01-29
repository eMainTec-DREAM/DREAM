<%--===========================================================================
주기정비 계획대비 실행 비율 상세
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.pmwcmptrate.action.WorkRptPmwCmptDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 주기정비 계획대비 실행 비율 -->
<title><bean:message key='LABEL.plant'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var selectedRowId;
function loadPage() 
{
	setTitle("workRptPmwCmptDetailDTO.plantDesc", "workRptPmwCmptDetailDTO.yyyymm");
	
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
        return sortColumn("workRptPmwCmptDetailList", this, workRptPmwCmptDetailForm, "", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}


function goSearch()
{
	findGridList('Search');   
}

function findGridList(gridAction)
{
	
	var form = document.workRptPmwCmptDetailForm;	
	form.strutsAction.value = '<%=WorkRptPmwCmptDetailAction.DETAIL_FIND %>';

    var url = contextPath + "/workRptPmwCmptDetailList.do";
    
    doGridAction(gridAction, myGrid, "gridbox", url, FormQueryString(workRptPmwCmptDetailForm), "", "Y");
}

/**
 * 엑셀 다운.
 */
function goExcel()
{
	excelServerAction("workRptPmwCmptDetailList", workRptPmwCmptDetailForm );
}
 

/* 
 * 설비보기
 */
function goMachequipmentLink(_pageId)
{
	var selectedId = myGrid.getSelectedRowId();
	if(selectedId == null) {
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	var equipId = getValueById(myGrid, selectedId,'EQUIP_ID');
	var eqctgType = getValueById(myGrid, selectedId,'EQCTG_TYPE');
	
	if(equipId == "" || eqctgType == "") {
		alertMessage1('<bean:message key="MESSAGE.CSMG013"/>');
		return;
	}
	
	goEquipDetail(equipId, eqctgType);
}

function goWoLink()
{
	var selectedId = myGrid.getSelectedRowId();
	if(selectedId == null) {
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	var wkorId = getValueById(myGrid, selectedId,'WKOR_ID');
	var url = getValueById(myGrid, selectedId,'PARAM');
	
	if(wkorId == "" || url == "") {
		alertMessage1('<bean:message key="MESSAGE.CSMG013"/>');
		return;
	}
	
	goWo(wkorId, url);
}
 
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workRptPmwCmptDetailList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="workRptPmwCmptDetailDTO.plantId"/>
<html:hidden property="workRptPmwCmptDetailDTO.plantDesc"/>
<html:hidden property="workRptPmwCmptDetailDTO.yyyymm"/>

		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
		
</html:form> 
</body>
</html>
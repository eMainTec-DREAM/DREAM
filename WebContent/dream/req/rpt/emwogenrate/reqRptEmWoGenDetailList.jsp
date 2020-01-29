<%--===========================================================================
작업의뢰 작업발행율 상세(설비)
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.req.rpt.emwogenrate.action.ReqRptEmWoGenDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 작업의뢰 작업발행율 상세(설비) -->
<title><bean:message key='MENU.EMWOGENRATE'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var selectedRowId;

function loadPage() 
{
	
	setTitle("reqRptEmWoGenRateCommonDTO.plantDesc");
	
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
    	return sortColumn("reqRptEmWoGenDetailList", this, reqRptEmWoGenDetailForm, "WONO", ind, direction);
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
	
	var form = document.reqRptEmWoGenDetailForm;	
	form.strutsAction.value = '<%=ReqRptEmWoGenDetailAction.DETAIL_FIND %>';

    var url = contextPath + "/reqRptEmWoGenDetailList.do";
    
    doGridAction(gridAction, myGrid, "gridbox", url, FormQueryString(reqRptEmWoGenDetailForm));
}

/**
 * 엑셀 다운.
 */
function goExcel()
{
	 excelServerAction("reqRptEmWoGenDetailList", reqRptEmWoGenDetailForm );
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
 * 작업 상세 보기
 */
function goWoLink()
{
	var wkorId = getValueById(myGrid, selectedRowId,'WKORID');
	var url = getValueById(myGrid, selectedRowId,'PARAM');
	
	if(typeof wkorId == "undefined" || typeof url == "undefined") return;
	
	goWo(wkorId, url);
}
 
/*
 * 요청접수 상세 보기
 */
function goWoreqdetailLink()
{
	var woReqId = getValueById(myGrid, selectedRowId,'WOREQID');
	
	if(typeof woReqId == "undefined" ) return;
	
	goWoreqdetail(woReqId);
}
 
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/reqRptEmWoGenDetailList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="reqRptEmWoGenRateCommonDTO.plantId"/>
<html:hidden property="reqRptEmWoGenRateCommonDTO.plantDesc"/>
<html:hidden property="reqRptEmWoGenRateCommonDTO.month"/>

		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
		
</html:form> 
</body>
</html>
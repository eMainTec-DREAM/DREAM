 <%--===========================================================================
부품사용분석(부품) Detail
author  sy.yang
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.rpt.ptusehist.action.PartRptPtUseHistDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<html:html>
<head>
<!-- 부품사용분석 -->
<title><bean:message key='MENU.PTUSEHIST' /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">

<script language="javascript">
<!--//
//그리드명
var myGrid;

function loadPage() 
{			  
	setTitle("partRptPtUseHistDetailDTO.partNo","partRptPtUseHistDetailDTO.partDesc");
	
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
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	partRptPtUseHistListForm.elements['partRptPtUseHistDetailDTO.partId'].value = "";
        return sortColumn("partRptPtUseHistDetailList", this, partRptPtUseHistListForm, "PARTID", ind, direction);
    });
    myGrid.init();
    setHeader(myGrid, "gridbox", "goSearch"); // grid, grid id, callback
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/partRptPtUseHistDetailList.do";
    partRptPtUseHistListForm.elements['strutsAction'].value = '<%=PartRptPtUseHistDetailAction.PART_DETAIL_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(partRptPtUseHistListForm), "PARTID", "Y");
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
	excelAction(myGrid);
}
//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<html:form action="/partRptPtUseHistList.do">
		<html:hidden property="strutsAction"/>
		<html:hidden property="partRptPtUseHistDetailDTO.partId"/>		
		<html:hidden property="partRptPtUseHistDetailDTO.partNo"/>
		<html:hidden property="partRptPtUseHistDetailDTO.partDesc"/>
		<html:hidden property="partRptPtUseHistDetailDTO.startDate" />
		<html:hidden property="partRptPtUseHistDetailDTO.endDate" />
		<html:hidden property="partRptPtUseHistDetailDTO.deptId" />
		<html:hidden property="partRptPtUseHistDetailDTO.plantId" />
    	<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
		
	</html:form>
</body>
</html:html>

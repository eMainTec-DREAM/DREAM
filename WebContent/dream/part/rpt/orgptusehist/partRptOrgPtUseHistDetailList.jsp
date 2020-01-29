<%--===========================================================================
author  youngjoo38
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.rpt.orgptusehist.action.PartRptOrgPtUseHistDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 조직별사용분석 -->
<title><bean:message key='LABEL.monthly'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

function loadPage() 
{
	setTitle("partRptOrgPtUseHistDetailDTO.deptDesc");
	
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
    myGrid.init();
    setHeader(myGrid, "gridbox", "goSearch"); // grid, grid id, callback
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/partRptOrgPtUseHistDetailList.do";
    partRptOrgPtUseHistListForm.elements['strutsAction'].value = '<%=PartRptOrgPtUseHistDetailAction.DETAIL_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(partRptOrgPtUseHistListForm), "MONTH");
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
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/partRptOrgPtUseHistDetailList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="partRptOrgPtUseHistDetailDTO.deptId"/>
<html:hidden property="partRptOrgPtUseHistDetailDTO.deptDesc"/>
<html:hidden property="partRptOrgPtUseHistDetailDTO.plantId"/>
<html:hidden property="partRptOrgPtUseHistDetailDTO.plantDesc"/>
<html:hidden property="partRptOrgPtUseHistDetailDTO.startYyyymm"/>
<html:hidden property="partRptOrgPtUseHistDetailDTO.endYyyymm"/>

    	<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
		
</html:form> 
</body>
</html>


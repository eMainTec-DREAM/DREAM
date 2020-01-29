<%--===========================================================================
조직별MTBF,MTTR 상세
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.org.mtbfmttr.action.WorkRptOrgMtbfmttrDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 조직별MTBF,MTTR 상세 -->
<title><bean:message key='MENU.EQMTBFMTTR'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	
	setTitle("workRptOrgMtbfmttrDetailDTO.deptDesc");
	
	initGrid();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		workRptOrgMtbfmttrDetailForm.elements['workRptOrgMtbfmttrDetailDTO.deptId'].value = "";
        return sortColumn("workRptOrgMtbfmttrDetailList", this, workRptOrgMtbfmttrDetailForm, "deptId", ind, direction);
    });
	setHeader(myGrid, "gridbox","goSearch"); // grid, grid id, callBack
}


function goSearch()
{
	findGridList('Search');   
}

function findGridList(gridAction)
{
	
	var form = document.workRptOrgMtbfmttrDetailForm;	
	form.strutsAction.value = '<%=WorkRptOrgMtbfmttrDetailAction.MTTRMTBF_EQUIP_DETAIL_FIND %>';

    var url = contextPath + "/workRptOrgMtbfmttrDetailList.do";
    
    doGridAction(gridAction, myGrid, "gridbox", url, FormQueryString(workRptOrgMtbfmttrDetailForm));
}

/**
 * 엑셀 다운.
 */
function goExcel()
{
	excelServerAction("workRptOrgMtbfmttrDetailList", workRptOrgMtbfmttrDetailForm );  
}
 
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workRptOrgMtbfmttrDetailList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="workRptOrgMtbfmttrDetailDTO.deptId"/>
<html:hidden property="workRptOrgMtbfmttrDetailDTO.deptDesc"/>
<html:hidden property="workRptOrgMtbfmttrDetailDTO.startDate"/>
<html:hidden property="workRptOrgMtbfmttrDetailDTO.endDate"/>
<html:hidden property="workRptOrgMtbfmttrDetailDTO.plantId"/>
<html:hidden property="workRptOrgMtbfmttrDetailDTO.plantDesc"/>

		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
		
</html:form> 
</body>
</html>
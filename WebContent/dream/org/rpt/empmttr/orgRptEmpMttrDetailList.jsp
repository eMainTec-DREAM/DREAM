<%--===========================================================================
MTTR(담당자) 상세
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.org.rpt.empmttr.action.OrgRptEmpMttrDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- MTTR(담당자) 상세 -->
<title><bean:message key='MENU.EmpMTTR'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	setTitle("orgRptEmpMttrDetailDTO.empDesc");
	
	initGrid();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	return sortColumn("orgRptEmpMttrDetailList", this, orgRptEmpMttrDetailForm, "", ind, direction);
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
	
	var form = document.orgRptEmpMttrDetailForm;	
	form.strutsAction.value = '<%=OrgRptEmpMttrDetailAction.EMP_MTTR_DETAIL_FIND %>';

    var url = contextPath + "/orgRptEmpMttrDetailList.do";
    
    doGridAction(gridAction, myGrid, "gridbox", url, FormQueryString(orgRptEmpMttrDetailForm),"","Y");
}

/**
 * 엑셀 다운.
 */
function goExcel()
{
	excelAction(myGrid);
}
 
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/orgRptEmpMttrDetailList.do">
<html:hidden property="strutsAction"/>

<html:hidden property="orgRptEmpMttrDetailDTO.empId"/>
<html:hidden property="orgRptEmpMttrDetailDTO.empDesc"/>
<html:hidden property="orgRptEmpMttrDetailDTO.deptId"/>
<html:hidden property="orgRptEmpMttrDetailDTO.startDate"/>
<html:hidden property="orgRptEmpMttrDetailDTO.endDate"/>

<html:hidden property="orgRptEmpMttrCommonDTO.filterEqGrade"/>
<html:hidden property="orgRptEmpMttrCommonDTO.filterEqGradeDesc"/>
	
	
		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
		
</html:form> 
</body>
</html>
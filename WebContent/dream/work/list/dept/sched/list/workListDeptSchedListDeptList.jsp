<%--===========================================================================
업체별 작업스케줄탭부서별 작업
author  js.lee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.list.dept.sched.list.action.WorkListDeptSchedListDeptListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 업체별 작업스케줄탭부서별 작업 -->
<title><bean:message key='PAGE.workListDeptSchedListDeptList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	// 타이틀 조합 	부서(시작일 ~ 종료일)
	var title = workListDeptSchedListDeptListForm.elements['workListDeptSchedCommonDTO.deptDesc'].value
				+ '('
				+ workListDeptSchedListDeptListForm.elements['workListDeptSchedCommonDTO.startDate'].value
				+ ' ~ '
				+ workListDeptSchedListDeptListForm.elements['workListDeptSchedCommonDTO.endDate'].value
				+ ')';
	workListDeptSchedListDeptListForm.elements['workListDeptSchedListDeptListDTO.title'].value = title;
	
	setTitle("workListDeptSchedListDeptListDTO.title");
	
	initGrid();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox","goSearch"); // grid, grid id, callBack
}


function goSearch()
{
	findGridList('Search');   
}

function findGridList(gridAction)
{
	
	var form = document.workListDeptSchedListDeptListForm;	
	form.strutsAction.value = '<%=WorkListDeptSchedListDeptListAction.LIST_FIND %>';

    var url = contextPath + "/workListDeptSchedListDeptList.do";
    
    doGridAction(gridAction, myGrid, "gridbox", url, FormQueryString(workListDeptSchedListDeptListForm),"","Y");
}

/**
 * 엑셀 다운.
 */
function goExcel()
{
	excelServerAction("workListDeptSchedListDeptList", workListDeptSchedListDeptListForm );
}
 
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workListDeptSchedListDeptList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="workListDeptSchedCommonDTO.deptId"/>
<html:hidden property="workListDeptSchedCommonDTO.deptDesc"/>
<html:hidden property="workListDeptSchedCommonDTO.startDate"/>
<html:hidden property="workListDeptSchedCommonDTO.endDate"/>
<html:hidden property="workListDeptSchedCommonDTO.eqCtgId"/>
<html:hidden property="workListDeptSchedCommonDTO.eqCtgDesc"/>
<html:hidden property="workListDeptSchedCommonDTO.plantId"/>
<html:hidden property="workListDeptSchedCommonDTO.plantDesc"/>
<html:hidden property="workListDeptSchedListDeptListDTO.title"/>

		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
		
</html:form> 
</body>
</html>
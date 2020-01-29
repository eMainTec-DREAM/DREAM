<%--===========================================================================
부서별 예산금액
author  ssong
version $Id: maPtDeptBgList.jsp,v 1.1 2015/12/03 01:45:27 ssong Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.budget.plan.list.action.MaPtDeptBgListAction" %>
<%@ page import="dream.budget.plan.list.action.MaPtDeptBgDetailAction" %>
<%@ page import="dream.budget.plan.list.action.MaPtBudgetDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 부서별 예산금액 -->
<title><bean:message key='TAB.maPtDeptBgList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	initGrid();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	//myGrid.enableSmartRendering(true,500);
	myGrid.enableTreeGridLines();
	myGrid.setImageSize(1,1);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("maPtDeptBgDetail");
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ }); myGrid.init();
	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

function afterSearch()
{
	//myGrid.collapseAll();
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
    findGridList('SearchTree');   
}

function findGridList(sheetAction)
{
	//페이지id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	//if (maPtDeptBgListForm.elements['maPtBudgetCommonDTO.bgtDeptPlanId'].value == '') return;
	
	var form = document.maPtDeptBgListForm;	
	form.strutsAction.value = '<%=MaPtDeptBgListAction.PTDEPT_BUDGET_LIST_FIND %>';
	
	var url = contextPath + "/maPtDeptBgList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPtDeptBgListForm), "deptId");
	//myGrid.setNumberFormat("0,000.00",2,".",",");
	myGrid.setNumberFormat("0,000",getIndexById(myGrid, "planAmt"),".",",");
	myGrid.setNumberFormat("0,000",getIndexById(myGrid, "sumPlanAmt"),".",",");

}

function afterRowSearch(_gridId)
{
	var planAmtSum = 0;
	for(var i = 0; myGrid.getRowsNum() > i; i++)
	{
		var rowId = myGrid.getRowId(i);
		var planAmt = getValueById(myGrid, rowId, "planAmt");
		if(planAmt != "") planAmtSum = parseInt(planAmtSum) + parseInt(getValueById(myGrid, rowId, "planAmt"));

	}
	
	if(typeof parent.setTotalAmt == "function") parent.setTotalAmt(planAmtSum);
}


function afterSearch()
{
	myGrid.expandAll();
}
/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(bgtDeptPlanId,deptId)
{
	maPtDeptBgListForm.elements['maPtBudgetCommonDTO.bgtDeptPlanId'].value = bgtDeptPlanId;
	maPtDeptBgListForm.elements['maPtBudgetCommonDTO.deptId'].value = deptId;
	
	findGridList('ReloadTreeRow');
	maPtDeptBgListForm.elements['maPtBudgetCommonDTO.bgtDeptPlanId'].value = "";
	maPtDeptBgListForm.elements['maPtBudgetCommonDTO.deptId'].value = "";
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
	var form = document.maPtDeptBgListForm;
	form.elements['maPtBudgetCommonDTO.bgtDeptPlanId'].value = getValueById(myGrid, selectedId, 'bgtDeptPlanId');
	form.elements['maPtBudgetCommonDTO.deptId'].value = getValueById(myGrid, selectedId, 'deptId');
	
	goCommonTabPage(form, <%= MaPtDeptBgDetailAction.PTDEPT_BUDGET_DETAIL_INIT %>, pageId);
}


/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maPtDeptBgDetail');
}

/**
 * 엑셀 다운.
 */
 function goExcel()
 {
 	excelAction(myGrid);
 }
 
  /**
   * 생성
   */
function goCreate()
{
 	createValidationCheck(myGrid, "maPtDeptBgDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maPtDeptBgListForm.elements['maPtBudgetCommonDTO.ptVendorId'].value = "";
	goCommonTabPage(maPtDeptBgListForm, '', pageId);
}
 
 /**
  * 삭제
  */
function goDelete()
 {
	var delArray = getDeletRows(myGrid, 'isDelCheck', 'ptVendorId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maPtDeptBgListForm.strutsAction.value = '<%=MaPtDeptBgListAction.PTDEPT_BUDGET_LIST_DELETE%>';
	var url = contextPath + "/maPtDeptBgList.do";
	
	$.post(url,FormQueryString(maPtDeptBgListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete()
{
	goClose('maPtDeptBgDetail',this);
   	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPtDeptBgList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPtBudgetCommonDTO.bgtPlanId"/><!-- Key -->
<html:hidden property="maPtBudgetCommonDTO.bgtDeptPlanId"/><!-- Key -->
<html:hidden property="maPtBudgetCommonDTO.compNo"/>
<html:hidden property="maPtBudgetCommonDTO.deptId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
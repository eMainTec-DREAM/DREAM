<%--===========================================================================
교대조
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.pm.list.action.WorkPmListShiftListAction" %>
<%@ page import="dream.work.pm.list.action.WorkPmListShiftDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 교대조 -->
<title><bean:message key='LABEL.shiftType'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	initGrid();
}

function afterDisable()
{
	$('.b_open').show();
	$('.b_excel').show();
	$('.b_setting').show();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("workPmListPtrlShiftDetail");
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	workPmListShiftListForm.elements['maPmMstrCommonDTO.pmWrkShiftId'].value = "";
        return sortColumn("workPmListPtrlShiftList", this, workPmListShiftListForm, "PMWRKSHIFTID", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{	
    findGridList('Search');   
}

function findGridList(sheetAction)
{
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (workPmListShiftListForm.elements['maPmMstrCommonDTO.pmId'].value == '') return;
	
	var form = document.workPmListShiftListForm;	
	form.strutsAction.value = '<%=WorkPmListShiftListAction.WORK_PM_SHIFT_LIST_FIND %>';
	
	var url = contextPath + "/workPmListPtrlShiftList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workPmListShiftListForm), "PMWRKSHIFTID", "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pmWrkShiftId)
{
	workPmListShiftListForm.elements['maPmMstrCommonDTO.pmWrkShiftId'].value = _pmWrkShiftId;
	findGridList('ReloadRow');
	workPmListShiftListForm.elements['maPmMstrCommonDTO.pmWrkShiftId'].value = "";
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
	var form = document.workPmListShiftListForm;
	 
	form.elements['maPmMstrCommonDTO.pmWrkShiftId'].value = getValueById(myGrid, selectedId,'PMWRKSHIFTID');
	goCommonTabPage(form, <%= WorkPmListShiftDetailAction.WORK_PM_SHIFT_DETAIL_INIT %>, pageId);
}


/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('workPmListPtrlShiftDetail');
}

 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "workPmListPtrlShiftDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	workPmListShiftListForm.elements['maPmMstrCommonDTO.pmWrkShiftId'].value = "";
	goCommonTabPage(workPmListShiftListForm, '', pageId);
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'isDelCheck', 'PMWRKSHIFTID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	workPmListShiftListForm.strutsAction.value = '<%=WorkPmListShiftListAction.WORK_PM_SHIFT_LIST_DELETE%>';
	var url = contextPath + "/workPmListPtrlShiftList.do";
	
	$.post(url,FormQueryString(workPmListShiftListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('workPmListPtrlRtDetail',this);
   	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
   
function goExcel()
{
	workPmListShiftListForm.elements['maPmMstrCommonDTO.pmWrkShiftId'].value = "";
	excelServerAction("workPmListPtrlShiftList",workPmListShiftListForm);
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workPmListPtrlShiftList">
<html:hidden property="strutsAction"/>
<html:hidden property="maPmMstrCommonDTO.pmId"/><!-- Key -->
<html:hidden property="maPmMstrCommonDTO.pmWrkShiftId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
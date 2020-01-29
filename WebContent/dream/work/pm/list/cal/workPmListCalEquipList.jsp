<%--===========================================================================
설비
author  kim21017
version $Id: workPmListCalEquipList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.pm.list.action.WorkPmListEquipListAction" %>
<%@ page import="dream.work.pm.list.action.WorkPmListEquipDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 청소설비 -->
<title><bean:message key='TAB.workPmListCalEquipList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	workPmListEquipListForm.elements['maPmMstrCommonDTO.woType'].value = workPmListEquipListForm.elements['maPmMstrDetailDTO.woType'].value;
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
		goTabPage("workPmListCalEquipDetail");
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	workPmListEquipListForm.elements['maPmMstrCommonDTO.pmEquipId'].value = "";
        return sortColumn("workPmListCalEquipList", this, workPmListEquipListForm, "PMEQUIPID", ind, direction);
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
	if (workPmListEquipListForm.elements['maPmMstrCommonDTO.pmId'].value == '') return;
	
	var form = document.workPmListEquipListForm;	
	form.strutsAction.value = '<%=WorkPmListEquipListAction.WORK_PM_EQ_LIST_FIND %>';
	
	var url = contextPath + "/workPmListCalEquipList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workPmListEquipListForm), "PMEQUIPID","Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pmEquipId)
{
	workPmListEquipListForm.elements['maPmMstrCommonDTO.pmEquipId'].value = _pmEquipId;
	findGridList('ReloadRow');
	workPmListEquipListForm.elements['maPmMstrCommonDTO.pmEquipId'].value = "";
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
	var form = document.workPmListEquipListForm;
	 
	form.elements['maPmMstrCommonDTO.pmEquipId'].value = getValueById(myGrid, selectedId,'PMEQUIPID');
	goCommonTabPage(form, <%= WorkPmListEquipDetailAction.WORK_PM_EQ_DETAIL_INIT %>, pageId);
}


/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('workPmListCalEquipDetail');
}

 function goOpenAction()
 {
     var selectedId=myGrid.getSelectedRowId();
     if(selectedId == null) return;
     
     workPmListEquipListForm.elements['maPmMstrCommonDTO.pmEquipId'].value = getValueById(myGrid, selectedId, 'pmEquipId');
     workPmListEquipListForm.elements['strutsAction'].value = '<%=WorkPmListEquipDetailAction.WORK_PM_EQ_DETAIL_INIT%>';
     openQuickTabPage(FormQueryString(workPmListEquipListForm), 'workPmListCalEquipDetail'); 
 } 

 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "workPmListCalEquipDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	workPmListEquipListForm.elements['maPmMstrCommonDTO.pmEquipId'].value = "";
	goCommonTabPage(workPmListEquipListForm, '', pageId);
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'isDelCheck', 'PMEQUIPID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	workPmListEquipListForm.strutsAction.value = '<%=WorkPmListEquipListAction.WORK_PM_EQ_LIST_DELETE%>';
	var url = contextPath + "/workPmListCalEquipList.do";
	
	$.post(url,FormQueryString(workPmListEquipListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('workPmListCalEquipDetail',this);
   	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
   
function goExcel()
{
	workPmListEquipListForm.elements['maPmMstrCommonDTO.pmEquipId'].value = "";
	excelServerAction("workPmListCalEquipList", workPmListEquipListForm);
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workPmListCalEquipList">
<html:hidden property="strutsAction"/>
<html:hidden property="maPmMstrCommonDTO.pmId"/><!-- Key -->
<html:hidden property="maPmMstrCommonDTO.pmEquipId"/>
<html:hidden property="maPmMstrCommonDTO.woType"/>
<html:hidden property="maPmMstrDetailDTO.woType"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
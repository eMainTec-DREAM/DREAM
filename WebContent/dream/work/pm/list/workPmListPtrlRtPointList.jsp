<%--===========================================================================
검사항목
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.pm.list.action.MaPmMstrPointListAction" %>
<%@ page import="dream.work.pm.list.action.MaPmMstrPointDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 검사항목 -->
<title><bean:message key='LABEL.maPmMstrPointList'/></title>
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
		goTabPage("workPmListPtrlRtPointDetail");
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
	if (maPmMstrPointListForm.elements['maPmMstrCommonDTO.pmId'].value == '') return;
	
	var form = document.maPmMstrPointListForm;	
	form.strutsAction.value = '<%=MaPmMstrPointListAction.PM_MSTR_POINT_LIST_FIND %>';
	
	var url = contextPath + "/workPmListPtrlRtPointList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPmMstrPointListForm), "PMPOINTID");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pmPointId)
{
	maPmMstrPointListForm.elements['maPmMstrCommonDTO.pmPointId'].value = _pmPointId;
	findGridList('ReloadRow');
	maPmMstrPointListForm.elements['maPmMstrCommonDTO.pmPointId'].value = "";
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
	var form = document.maPmMstrPointListForm;
	 
	form.elements['maPmMstrCommonDTO.pmPointId'].value = getValueById(myGrid, selectedId,'pmPointId');
	goCommonTabPage(form, <%= MaPmMstrPointDetailAction.PM_MSTR_POINT_DETAIL_INIT %>, pageId);
}


/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('workPmListPtrlRtPointDetail');
}
 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "workPmListPtrlRtPointDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	maPmMstrPointListForm.elements['maPmMstrCommonDTO.pmPointId'].value = "";
	goCommonTabPage(maPmMstrPointListForm, '', pageId);
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'isDelCheck', 'pmPointId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maPmMstrPointListForm.strutsAction.value = '<%=MaPmMstrPointListAction.PM_MSTR_POINT_LIST_DELETE%>';
	var url = contextPath + "/workPmListPtrlRtPointList.do";
	
	$.post(url,FormQueryString(maPmMstrPointListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('workPmListPtrlRtPointDetail',this);
   	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * 엑셀 다운.
 */
 function goExcel()
 {
 	excelAction(myGrid);
 } 
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workPmListPtrlRtPointList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPmMstrCommonDTO.pmId"/><!-- Key -->
<html:hidden property="maPmMstrCommonDTO.pmPointId"/>
<html:hidden property="maPmMstrCommonDTO.pmEquipId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
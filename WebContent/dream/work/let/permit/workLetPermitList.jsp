<%--===========================================================================
안전작업 - 안전작업허가서 목록
author  syyang
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.let.permit.action.WorkLetPermitListAction" %>
<%@ page import="dream.work.let.permit.action.WorkLetPermitDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 안전작업 -->
<title><bean:message key='LABEL.woType2'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var woLetCtgAc;
function loadPage() 
{
	setForUpdate();
	
	initGrid();
	
	woLetCtgAc = new autoC({"workLetPermitDetailDTO.multiDesc":"woLetCtgTypeDesc"});
	woLetCtgAc.setTable("TAWOLETCTG");
	woLetCtgAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
	woLetCtgAc.setAcResultMap({
        "workLetPermitDetailDTO.multiKey":"woLetCtgType"
    });
	woLetCtgAc.setMultiSelect(true);
	woLetCtgAc.init();
	
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("workLetPermitDetail");
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		workLetPermitListForm.elements['workLetCommonDTO.woLetId'].value = "";
        return sortColumn("workLetPermitList", this, workLetPermitListForm, "WOLETLISTID", ind, direction);
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
	if (workLetPermitListForm.elements['workLetCommonDTO.woLetId'].value == '') return;
	
	var form = document.workLetPermitListForm;	
	form.strutsAction.value = '<%=WorkLetPermitListAction.WO_LET_PERMIT_LIST_FIND %>';
	
	var url = contextPath + "/workLetPermitList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workLetPermitListForm), "WOLETLISTID", "Y");

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
	var form = document.workLetPermitListForm;
	 
	form.elements['workLetPermitListDTO.woLetListId'].value = getValueById(myGrid, selectedId,'WOLETLISTID');
	goCommonTabPage(form, <%= WorkLetPermitDetailAction.WO_LET_PERMIT_DETAIL_INIT %>, pageId);
}

function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
 	if(selectedId == null) return;

 	workLetPermitListForm.elements['workLetPermitListDTO.woLetListId'].value = getValueById(myGrid, selectedId,'WOLETLISTID');
 	workLetPermitListForm.elements['strutsAction'].value = '<%=WorkLetPermitDetailAction.WO_LET_PERMIT_DETAIL_INIT%>';
 	openQuickTabPage(FormQueryString(workLetPermitListForm), 'workLetPermitDetail'); 
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('workLetPermitDetail');
}
 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	  workLetPermitListForm.elements['workLetPermitListDTO.woLetListId'].value = "";
	  excelServerAction("workLetPermitList", workLetPermitListForm );
  } 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "workLetPermitDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	workLetPermitListForm.elements['workLetPermitListDTO.woLetListId'].value = "";
	goCommonTabPage(workLetPermitListForm, '', pageId);
 }
 
 /** 
  * 수정된 그리드 1건을 다시 조회한다.
  */
 function findGridRow(_woLetListId)
 {
	workLetPermitListForm.elements['workLetPermitListDTO.woLetListId'].value = _woLetListId;
 	findGridList('ReloadRow');
 	workLetPermitListForm.elements['workLetPermitListDTO.woLetListId'].value = "";
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'woLetListId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	workLetPermitListForm.strutsAction.value = '<%=WorkLetPermitListAction.WO_LET_PERMIT_LIST_DELETE%>';
	var url = contextPath + "/workLetPermitList.do";
	
	$.post(url,FormQueryString(workLetPermitListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('workLetPermitDetail',this);
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}


//작업자 상세페이지에 채울 일자와 시간을 작업상세에서 가져온다.
function getDateTime(){
	 var startDate = parent.M$('workLetDetailDTO.startDate').value;
	 var startTime = parent.M$('workLetDetailDTO.startTime').value;
	 var endDate   = parent.M$('workLetDetailDTO.endDate').value;
	 var endTime   = parent.M$('workLetDetailDTO.endTime').value;
	 return startDate+","+startTime+","+endDate+","+endTime;
}

function goSave(){
	var url = contextPath + "/workLetPermitList.do";
	
    $.post(url,FormQueryString(workLetPermitListForm), function(_data){
    	afterSave(_data);
    });
}
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    getTopPage().afterSaveAll(currentPageId);
    
    goSearch();
    
    //시간설정 초기화
    if(workLetPermitListForm.elements['workLetPermitDetailDTO.startDate'].value!='') 
		workLetPermitListForm.elements['workLetPermitDetailDTO.startDate'].value = '';
	if(workLetPermitListForm.elements['workLetPermitDetailDTO.startTime'].value!='') 
		workLetPermitListForm.elements['workLetPermitDetailDTO.startTime'].value = '';
	if(workLetPermitListForm.elements['workLetPermitDetailDTO.endDate'].value!='') 
		workLetPermitListForm.elements['workLetPermitDetailDTO.endDate'].value = '';
	if(workLetPermitListForm.elements['workLetPermitDetailDTO.endTime'].value!='') 
		workLetPermitListForm.elements['workLetPermitDetailDTO.endTime'].value = '';
	if(workLetPermitListForm.elements['workLetPermitDetailDTO.woLetDate'].value!='') 
		workLetPermitListForm.elements['workLetPermitDetailDTO.woLetDate'].value = '';
}

/**
 * 일괄등록
 */
function goRegbatch()
{
	woLetCtgAc.openLov();
}

function setAcLovValue(rtnArr, code)
{
	workLetPermitListForm.strutsAction.value = '<%=WorkLetPermitListAction.WO_LET_PERMIT_LIST_INPUT%>';
	
	//작업상태 = DNG - 진행중
	workLetPermitListForm.elements['workLetPermitDetailDTO.woLetListStatusDesc'].value = "DNG";
	valSysDir('workLetPermitDetailDTO.woLetListStatus', 'workLetPermitDetailDTO.woLetListStatusDesc', 'WOLETLIST_STATUS', true);
	
	//날짜,시간값이 비어있으면 작업상세의 값을 넣는다.
	var dateTimes = getDateTime();
	dateTimes = dateTimes.split(",");
	if(workLetPermitListForm.elements['workLetPermitDetailDTO.startDate'].value=='') 
		workLetPermitListForm.elements['workLetPermitDetailDTO.startDate'].value = dateTimes[0];
	if(workLetPermitListForm.elements['workLetPermitDetailDTO.startTime'].value=='') 
		workLetPermitListForm.elements['workLetPermitDetailDTO.startTime'].value = dateTimes[1];
	if(workLetPermitListForm.elements['workLetPermitDetailDTO.endDate'].value=='') 
		workLetPermitListForm.elements['workLetPermitDetailDTO.endDate'].value = dateTimes[2];
	if(workLetPermitListForm.elements['workLetPermitDetailDTO.endTime'].value=='') 
		workLetPermitListForm.elements['workLetPermitDetailDTO.endTime'].value = dateTimes[3];
	
	workLetPermitListForm.elements['workLetPermitDetailDTO.creTime'].value = getNowDateTime(true); 
	workLetPermitListForm.elements['workLetPermitDetailDTO.updTime'].value = getNowDateTime(true); 
	workLetPermitListForm.elements['workLetPermitDetailDTO.woLetDate'].value = getToday(); 
	
	goSaveAll();
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workLetPermitList.do">
	<html:hidden property="strutsAction"/>
	<html:hidden property="workLetCommonDTO.woLetId"/><!-- Key -->
	<html:hidden property="workLetDetailDTO.woLetId"/><!-- Key -->
	<html:hidden property="workLetDetailDTO.woLetDesc"/>
	<html:hidden property="workLetPermitListDTO.woLetListId"/>
	<html:hidden property="workLetPermitDetailDTO.multiKey"/><!-- MultiSelect Key -->
	<html:hidden property="workLetPermitDetailDTO.multiDesc"/><!-- MultiSelect Desc -->
	<html:hidden property="workLetPermitDetailDTO.woLetListStatus"/>
	<html:hidden property="workLetPermitDetailDTO.woLetListStatusDesc"/>
	<html:hidden property="workLetPermitDetailDTO.startDate"/>
	<html:hidden property="workLetPermitDetailDTO.startTime"/>
	<html:hidden property="workLetPermitDetailDTO.endDate"/>
	<html:hidden property="workLetPermitDetailDTO.endTime"/>
	<html:hidden property="workLetPermitDetailDTO.creTime"/>
	<html:hidden property="workLetPermitDetailDTO.updTime"/>
	<html:hidden property="workLetPermitDetailDTO.woLetDate"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
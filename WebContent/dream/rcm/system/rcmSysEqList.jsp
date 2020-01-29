<%--===========================================================================
설비설정
author  kim21017
version $Id: rcmSysEqList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.rcm.system.action.RcmSysEqListAction" %>
<%@ page import="dream.rcm.system.action.RcmSysEqDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 기능정의 -->
<title><bean:message key='TAB.rcmSysEqList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var equipDescAc;
function loadPage() 
{
	setForUpdate();
	
	initGrid();
	
	equipDescAc = new autoC({"rcmSysEqListDTO.multiDesc":"description"});
	equipDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo
 	   });
	equipDescAc.setTable("TAEQUIPMENT");
    equipDescAc.setAcResultMap({
        "rcmSysEqListDTO.multiKey":"equip_id"
    });
    equipDescAc.setMultiSelect(true);   // <<------Enable Multi Select Function (Must not enable in Detail Page)
    equipDescAc.init();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("rcmSysEqDetail");
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	rcmSysEqListForm.elements['rcmSysEqListDTO.rcmEqId'].value = "";
    	return sortColumn("rcmSysEqList", this, rcmSysEqListForm, "RCMEQID", ind, direction);
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
	if (rcmSysEqListForm.elements['rcmSysCommonDTO.rcmListId'].value == '') return;
	
	var form = document.rcmSysEqListForm;	
	form.strutsAction.value = '<%=RcmSysEqListAction.RCM_SYS_EQ_LIST_FIND %>';
	
	var url = contextPath + "/rcmSysEqList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(rcmSysEqListForm), "RCMEQID", "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_rcmFuncId)
{
	rcmSysEqListForm.elements['rcmSysEqListDTO.rcmEqId'].value = _rcmFuncId;
	findGridList('ReloadRow');
	rcmSysEqListForm.elements['rcmSysEqListDTO.rcmEqId'].value = "";
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
	var form = document.rcmSysEqListForm;
	 
	form.elements['rcmSysEqListDTO.rcmEqId'].value = getValueById(myGrid, selectedId,'RCMEQID');
	goCommonTabPage(form, <%= RcmSysEqDetailAction.RCM_SYS_EQ_DETAIL_INIT %>, pageId);
}


/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('rcmSysEqDetail');
}

 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "rcmSysEqDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	rcmSysEqListForm.elements['rcmSysEqListDTO.rcmEqId'].value = "";
	goCommonTabPage(rcmSysEqListForm, '', pageId);
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'isDelCheck', 'RCMEQID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	rcmSysEqListForm.strutsAction.value = '<%=RcmSysEqListAction.RCM_SYS_EQ_LIST_DELETE%>';
	var url = contextPath + "/rcmSysEqList.do";
	
	$.post(url,FormQueryString(rcmSysEqListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('rcmSysEqDetail',this);
   	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
   
function goExcel()
{
	excelAction(myGrid);
}

function goSave(){
	var url = contextPath + "/rcmSysEqList.do";
	
    $.post(url,FormQueryString(rcmSysEqListForm), function(_data){
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
}

/**
 * 설비설정 추가 버튼 클릭 시
 */
function goAddeq()
{
	//Open LOV for AC
    equipDescAc.openLov();
}

/**
 * Return Selected multi Keys as rtnValueArr[0]
 */
function setAcLovValue(rtnArr, code)
{
	rcmSysEqListForm.strutsAction.value = '<%=RcmSysEqListAction.RCM_SYS_EQ_LIST_INPUT%>';
	
	goSaveAll();
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/rcmSysEqList">
<html:hidden property="strutsAction"/>
<html:hidden property="rcmSysCommonDTO.rcmListId"/><!-- Key -->
<html:hidden property="rcmSysEqListDTO.rcmEqId"/><!-- Key -->
<html:hidden property="rcmSysEqListDTO.multiDesc"/><!-- MultiSelect Desc -->
<html:hidden property="rcmSysEqListDTO.multiKey"/><!-- MultiSelect Key -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
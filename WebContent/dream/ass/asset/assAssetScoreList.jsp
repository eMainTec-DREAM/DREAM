<%--===========================================================================
평가점수
author  youngjoo38
version $Id: assAssetScoreList.jsp,v 1.1 2017/08/28 10:17:27 youngjoo38 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.ass.asset.action.AssAssetScoreListAction" %>
<%@ page import="dream.ass.asset.action.AssAssetScoreDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 평가점수 -->
<title><bean:message key='TAB.assAssetScoreList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var myGrid;
var assEvalDescAc;
var eqAssAc;

function loadPage() 
{
    initGrid();
    
    hideBtn("SAVE");
	//hideBtn("ADD");
	hideBtn("DEL");
	hideBtn("EDITCNCL");
	//hideBtn("EDIT");
	
	/** 평가결과복사 LOV AC */
	eqAssAc = new autoC({"assAssetScoreListDTO.eqasslistIdLov":"eqasslistId"});
	eqAssAc.setAcConditionMap({
// 		"eqasslist_id" : "assAssetCommonDTO.eqasslistId"
  	   });
	eqAssAc.setAcDConditionMap({
		"eqasslist_id" : "assAssetCommonDTO.eqasslistId"
  	   });
	eqAssAc.setTable("TAEQASSLIST");
	eqAssAc.setAcResultMap({
    });
	eqAssAc.init();
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
         goOpen();
     });
    myGrid.attachEvent("onCellMarked", function(rowId,columnId){
  	   selectedId = rowId;
  	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
       assAssetScoreListForm.elements['assAssetScoreListDTO.eqAssPValId'].value = "";
       return sortColumn("assAssetScoreList", this, assAssetScoreListForm, "EQASSPVALID", ind, direction);
    });
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
   
    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * Make Editable Mode
 */
function goEdit()
{
	hideBtn("EXCEL");
	hideBtn("OPEN");
	hideBtn("SETTING");
	
	editRow(myGrid);	

    //Close Detail Page if it is open
    goClose("assAssetScoreDetail", this);

    //set for update mark.
    setForUpdate();
    
    showBtn("SAVE");
    //showBtn("ADD");
    //showBtn("DEL");
    hideBtn("EDIT");
    showBtn("EDITCNCL");
    
    //Set Grid as updatable
	var url = contextPath + "/assAssetScoreList.do";
	var stAct = "<%=AssAssetScoreListAction.LIST_SAVE %>";
	//URL, StrutsAction, Grid Obj, Call Back Function, Validation Rule (not_empty)
	proGrid = setGridUpdate(url, stAct, myGrid, "afterSave",'{}');
	
	//change column type (acedp (Auto complete, LOV), aced(Auto complete only), ednum(Auto complete and number only))
	//setColumnType(myGrid,"EQASMBDESC","acedp");
	setColumnType(myGrid,"ASSEVALDESC","acedp"); //AC,EDIT,POPUP
	//setColumnType(myGrid,"RESULTVALUE","ednum"); //EDIT,NUMBER
	setColumnType(myGrid,"REMARK","ed"); //EDIT
}

function goSave()
{
	//Send All Data ONce
	proGrid.sendData();
}

/**
 * 수정모드 취소 
 */
function goEditcncl()
{
	editcnclRow(myGrid);
	
	afterSave();
}

/**
 * After Edit
 */
function afterSave()
{
	parent.gradeUpdate();
	parent.scoreUpdate();
	parent.parent.findGridRow(assAssetScoreListForm.elements['assAssetCommonDTO.eqasslistId'].value);
	
	afterEditRow(myGrid);
	
	//Control Button
	hideBtn("SAVE");
//	hideBtn("ADD");
	hideBtn("DEL");
	showBtn("EDIT");
	hideBtn("EDITCNCL");
	
	showBtn("EXCEL");
	showBtn("OPEN");
	showBtn("SETTING");
	
	//attach Event to open detail page
	//addRowSelEvent();

	//Clear Key Value
//	assAssetScoreListForm.elements['assAssetCommonDTO.eqasslistId'].value = "";
	assAssetScoreListForm.elements['assAssetScoreListDTO.eqAssPValId'].value = "";
	//Search
	goSearch();
	
	//Clear Update Mark for this page 
	clearUpdate(currentPageId);
	
	//Make Page as Normal
	setForNormal();
}

/**
 * AC Setting, This is called when the column is changed to edit mode.
 */
function setGridAc(_gridObj, _cellObj)
{
    var assBasePointId = myGrid.cellById(selectedId,getIndexById(myGrid, "ASSBASEPOINTID")).getValue();

    assEvalDescAc = new autoC({"ASSEVALDESC":"assEval"});
    assEvalDescAc.setCol(_cellObj); //mandatory
	assEvalDescAc.setGrid(_gridObj); //mandatory
    assEvalDescAc.setTable("TAASSBASEPVAL");
    assEvalDescAc.setAcConditionMap({
        "comp_no" : loginUser.compNo
      , "is_use" : "Y"
      , "assbasepoint_id" : assBasePointId
    });
    assEvalDescAc.setAcResultMap({
        "ASSEVALDESC":"assEval" /* 선택항목 */
      , "EQASSPVALDESC":"evalValue" /* 점수 */
      , "ASSBASEPVALID":"assBasePvalId" /* assBasePvalId */
    });
    assEvalDescAc.setKeyName("ASSBASEPVALID");
    assEvalDescAc.init();
}

/**
 * Put the delete Mark
 */
function goDel()
{
	//del Row (return Row ID)
	delRow(myGrid);
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	//페이지id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (assAssetScoreListForm.elements['assAssetCommonDTO.eqasslistId'].value == '') return;
	
	assAssetScoreListForm.elements['assAssetScoreListDTO.assBaseListId'].value = parent.$('input[name="assAssetDetailDTO.assBaseListId"]').val();
	//assAssetScoreListForm.elements['assAssetScoreListDTO.eqAssPValId'].value = "";

	var url = contextPath + "/assAssetScoreList.do";
	
	assAssetScoreListForm.elements['strutsAction'].value = '<%=AssAssetScoreListAction.LIST_FIND%>';
	
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(assAssetScoreListForm), "EQASSPVALID", "Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_eqAssPValId)
{
    assAssetScoreListForm.elements['assAssetScoreListDTO.eqAssPValId'].value = _eqAssPValId;
    findGridList('ReloadRow');
    assAssetScoreListForm.elements['assAssetScoreListDTO.eqAssPValId'].value = "";
}

function goSearch()
{
    findGridList('Search');
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
	assAssetScoreListForm.elements['assAssetScoreListDTO.eqAssPValId'].value = getValueById(myGrid, selectedId,'EQASSPVALID');
	assAssetScoreListForm.elements['assAssetCommonDTO.eqasslistId'].value = getValueById(myGrid, selectedId,'EQASSLISTID');
    
	goCommonTabPage(assAssetScoreListForm, <%= AssAssetScoreDetailAction.DETAIL_INIT %>, "assAssetScoreDetail");
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('assAssetScoreDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    assAssetScoreListForm.elements['assAssetScoreListDTO.eqAssPValId'].value = getValueById(myGrid, selectedId,'EQASSPVALID');
    assAssetScoreListForm.elements['assAssetCommonDTO.eqasslistId'].value = getValueById(myGrid, selectedId,'EQASSLISTID');
    assAssetScoreListForm.elements['strutsAction'].value = '<%=AssAssetScoreDetailAction.DETAIL_INIT%>';
    var pageId  = getValueById(myGrid, selectedId,'PARAM');
    openQuickTabPage(FormQueryString(assAssetScoreListForm), 'assAssetScoreDetail'); 
} 

 /**
  * 생성
  */
function goCreate()
{
 	createValidationCheck(myGrid, "assAssetScoreDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
 	assAssetScoreListForm.elements['assAssetScoreListDTO.eqAssPValId'].value = "";
 	goCommonTabPage(assAssetScoreListForm, '', pageId);
}
 
  /**
   * 삭제
   */
function goDelete()
{
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'EQASSPVALID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	assAssetScoreListForm.strutsAction.value = '<%=AssAssetScoreListAction.LIST_DELETE%>';
	var url = contextPath + "/assAssetScoreList.do";
	
	$.post(url,FormQueryString(assAssetScoreListForm)+delArray , function(_data){
    	afterDelete();
    });
}
  
function afterDelete()
{
	goClose('assAssetScoreDetail');
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	assAssetScoreListForm.elements['assAssetScoreListDTO.eqAssPValId'].value = "";
    excelServerAction("assAssetScoreList", assAssetScoreListForm );  
}

/**
 * 평가결과복사
 */
function goScorecopy(){
	
	eqAssAc.openLov();
}

function setAcLovValue(rtnArr, code)
{
	if(code == 'assAssetScoreListDTO.eqasslistIdLov') 
	{
		assAssetScoreListForm.strutsAction.value = '<%=AssAssetScoreListAction.LIST_UPDATE%>';
	
		var url = contextPath + "/assAssetScoreList.do";
		
	    $.post(url,FormQueryString(assAssetScoreListForm), function(_data){
	    	goSearch();
	        if (parent.parent.findGridRow)    parent.parent.findGridRow(assAssetScoreListForm.elements['assAssetCommonDTO.eqasslistId'].value);
	    });
	}
	
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assAssetScoreList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="assAssetCommonDTO.eqasslistId"/><!-- Key -->
<html:hidden property="assAssetScoreListDTO.eqAssPValId"/><!-- Key -->
<html:hidden property="assAssetScoreListDTO.filterAssBaseListId"/>
<html:hidden property="assAssetScoreListDTO.assBaseListId"/>
<html:hidden property="assAssetScoreListDTO.assBasePointId"/>
<html:hidden property="assAssetScoreListDTO.eqasslistId"/>
<html:hidden property="assAssetScoreListDTO.eqasslistIdLov"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>
	<c:set var="filePath" value="enhance/${compName}/ass/asset/assAssetScoreList_${compNo}.jsp" />
	<c:if test="${udf:isExist(filePath)}">
		<c:import charEncoding="UTF-8" url="/enhance/${compName}/ass/asset/assAssetScoreList_${compNo}.jsp"></c:import>
	</c:if>
</html:form> 
</body>
</html>
<%--===========================================================================
등급기준
author  kim21017
version $Id: assBaseGradeList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.ass.base.action.AssBaseGradeListAction" %>
<%@ page import="dream.ass.base.action.AssBaseGradeDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 등급기준 -->
<title><bean:message key='TAB.assBaseGradeList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

var isEditableGrid = false;

/** 자동완성 변수 */

function loadPage() 
{
    initGrid();

	hideBtn("SAVE");
	hideBtn("ADD");
	hideBtn("DEL");
	hideBtn("EDITCNCL");
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		if(!isEditableGrid) {
			goOpen();
		}
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		assBaseGradeListForm.elements['assBaseGradeListDTO.assBaseGradeId'].value = "";
    	return sortColumn("assBaseGradeList", this, assBaseGradeListForm, "ASSBASEGRADEID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

function goEdit() {
	hideBtn("CREATE");
	hideBtn("OPEN");
	hideBtn("DELETE");
	hideBtn("EXCEL");
	hideBtn("SETTING");
	
	editRow(myGrid);	

    //Close Detail Page if it is open
    goClose("assBaseGradeDetail", this);

    //set for update mark.
    setForUpdate();
    
    showBtn("SAVE");
    //showBtn("ADD");
    showBtn("DEL");
    hideBtn("EDIT");
    showBtn("EDITCNCL");
    
    //Set Grid as updatable
	var url = contextPath + "/assBaseGradeList.do";
	var stAct = "<%=AssBaseGradeListAction.LIST_SAVE %>";
	//URL, StrutsAction, Grid Obj, Call Back Function, Validation Rule (not_empty)
	proGrid = setGridUpdate(url, stAct, myGrid, "afterSave",'{"EQGRADEID":"not_empty","EQGRADEDESC":"not_empty","GRADEFROM":"not_empty","GRADETO":"not_empty"}');
	
	//change column type (acedp (Auto complete, LOV), aced(Auto complete only), ednum(Auto complete and number only))
// 	setColumnType(myGrid,"EQGRADEDESC","acedp"); //AC,EDIT,POPUP
	setColumnType(myGrid,"GRADEFROM","edtynum"); //EDIT,NUMBER
	setColumnType(myGrid,"GRADETO","edtynum"); //EDIT,NUMBER
	setColumnType(myGrid,"ORDNO","ed"); //EDIT
	setColumnType(myGrid,"REMARK","ed"); //EDIT
}

function goSave()
{
	//Send All Data ONce
	proGrid.sendData();
}

/**
 * After Edit
 */
function afterSave()
{
	afterEditRow(myGrid);
	
	//Control Button
	hideBtn("SAVE");
	hideBtn("ADD");
	hideBtn("DEL");
	showBtn("EDIT");
	hideBtn("EDITCNCL");
	
	showBtn("CREATE");
	showBtn("OPEN");
	showBtn("DELETE");
	showBtn("EXCEL");
	showBtn("SETTING");
	
	//attach Event to open detail page
	//addRowSelEvent();

	//Clear Key Value
	assBaseGradeListForm.elements['assBaseGradeListDTO.assBaseGradeId'].value = "";
	
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
	//Column Id별로 event 할당
//     acSysDesc("EQGRADEDESC","EQGRADEID","EQ_GRADE",true,_gridObj,_cellObj);
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
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (assBaseGradeListForm.elements['assBaseCommonDTO.assBaseListId'].value == '') return;
    var url = contextPath + "/assBaseGradeList.do";
    assBaseGradeListForm.elements['strutsAction'].value = '<%=AssBaseGradeListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(assBaseGradeListForm), "ASSBASEGRADEID","Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_assBaseGradeId)
{
	assBaseGradeListForm.elements['assBaseGradeListDTO.assBaseGradeId'].value = _assBaseGradeId;
	findGridList('ReloadRow');
	assBaseGradeListForm.elements['assBaseGradeListDTO.assBaseGradeId'].value = "";
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
	assBaseGradeListForm.elements['assBaseGradeListDTO.assBaseGradeId'].value =  getValueById(myGrid, selectedId,'ASSBASEGRADEID');  
	goCommonTabPage(assBaseGradeListForm, <%= AssBaseGradeDetailAction.DETAIL_INIT %>, pageId);
	assBaseGradeListForm.elements['assBaseGradeListDTO.assBaseGradeId'].value = "";
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('assBaseGradeDetail');
}
function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;

	assBaseGradeListForm.elements['assBaseGradeListDTO.assBaseGradeId'].value =  getValueById(myGrid, selectedId,'ASSBASEGRADEID');  
	assBaseGradeListForm.elements['strutsAction'].value = '<%= AssBaseGradeDetailAction.DETAIL_INIT %>';
    openQuickTabPage(FormQueryString(assBaseGradeListForm), 'assBaseGradeDetail'); 
}

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "assBaseGradeDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	assBaseGradeListForm.elements['assBaseGradeListDTO.assBaseGradeId'].value = "";
    goCommonTabPage(assBaseGradeListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'ASSBASEGRADEID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    assBaseGradeListForm.strutsAction.value = '<%=AssBaseGradeListAction.LIST_DELETE%>';
    var url = contextPath + "/assBaseGradeList.do";
    
    $.post(url,FormQueryString(assBaseGradeListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('assBaseGradeDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	assBaseGradeListForm.elements['assBaseGradeListDTO.assBaseGradeId'].value = "";
	excelServerAction("assBaseGradeList", assBaseGradeListForm );  
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = assBaseGradeListForm.elements['assBaseGradeListDTO.assBaseGradeId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assBaseGradeList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="assBaseCommonDTO.assBaseListId"/><!-- Key -->
<html:hidden property="assBaseGradeListDTO.assBaseGradeId"/><!-- Key -->
    <!-- searchbox 박스 Line -->

	<div class="article_box" id="listBox">
		<div class="grid_area">
			<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
		</div>
	</div>

</html:form> 
</body>
</html>
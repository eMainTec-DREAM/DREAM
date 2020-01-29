<%--===========================================================================
목록
author  youngjoo38
version $Id$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.pers.priv.info.action.PersPrivInfoMsgEmpListAction" %>
<%@ page import="dream.pers.priv.info.action.PersPrivInfoMsgEmpDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 메시지 수신설정 -->
<title><bean:message key='TAB.msgEmp'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="tabPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	initGrid();
	
	hideBtn("SAVE");
	hideBtn("ADD");
	hideBtn("DEL");
	hideBtn("EDITCNCL");
	
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	persPrivInfoMsgEmpListForm.elements['maMyInfoCommonDTO.msgEmpSetId'].value = "";
        return sortColumn("persPrivInfoMsgEmpList", this, persPrivInfoMsgEmpListForm, "MSGEMPSETID", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}


/**
 * Make Editable Mode
 */
function goEdit()
{
	hideBtn("CREATE");
	hideBtn("OPEN");
	hideBtn("DELETE");
	hideBtn("EXCEL");
	hideBtn("SETTING");
	
	editRow(myGrid);	

    //Close Detail Page if it is open
    goClose("persPrivInfoMsgEmpDetail", this);

    //set for update mark.
    setForUpdate();
    
    showBtn("SAVE");
//     showBtn("ADD");
//     showBtn("DEL");
    hideBtn("EDIT");
    showBtn("EDITCNCL");
    
    //Set Grid as updatable
	var url = contextPath + "/persPrivInfoMsgEmpList.do";
	var stAct = "<%=PersPrivInfoMsgEmpListAction.LIST_SAVE %>";
	//URL, StrutsAction, Grid Obj, Call Back Function, Validation Rule (not_empty)
	proGrid = setGridUpdate(url, stAct, myGrid, "afterSave",'{}');
	
	//change column type (acedp (Auto complete, LOV), aced(Auto complete only), ednum(Auto complete and number only))
	//setColumnType(myGrid,"EQASMBDESC","acedp");
	setColumnType(myGrid,"ISMAILUSEDESC","acedp"); //AC,EDIT,POPUP
	setColumnType(myGrid,"ISSMSUSEDESC","acedp"); //AC,EDIT,POPUP
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
	persPrivInfoMsgEmpListForm.elements['maMyInfoCommonDTO.msgEmpSetId'].value = "";
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
	acSysDesc("ISMAILUSEDESC","ISMAILUSE","IS_USE",true,_gridObj,_cellObj);
	acSysDesc("ISSMSUSEDESC","ISSMSUSE","IS_USE",true,_gridObj,_cellObj);
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
 * 검색 클릭시 호출
 */
function goSearch()
{
    findGridList('Search');   
}

function findGridList(sheetAction)
{
	var form = document.persPrivInfoMsgEmpListForm;	
	form.strutsAction.value = '<%=PersPrivInfoMsgEmpListAction.LIST_FIND %>';
	
	var url = contextPath + "/persPrivInfoMsgEmpList.do";

	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(persPrivInfoMsgEmpListForm), "MSGEMPSETID", "Y");

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
	persPrivInfoMsgEmpListForm.elements['maMyInfoCommonDTO.msgEmpSetId'].value = getValueById(myGrid, selectedId,'msgEmpSetId');
	goCommonTabPage(persPrivInfoMsgEmpListForm, '<%=PersPrivInfoMsgEmpDetailAction.DETAIL_INIT%>' , pageId, pageId);
	
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_msgEmpSetId)
{
	persPrivInfoMsgEmpListForm.elements['maMyInfoCommonDTO.msgEmpSetId'].value = _msgEmpSetId;
	findGridList('ReloadRow');
	persPrivInfoMsgEmpListForm.elements['maMyInfoCommonDTO.msgEmpSetId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('persPrivInfoMsgEmpDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    persPrivInfoMsgEmpListForm.elements['maMyInfoCommonDTO.msgEmpSetId'].value = getValueById(myGrid, selectedId,'msgEmpSetId');
    persPrivInfoMsgEmpListForm.elements['strutsAction'].value = '<%=PersPrivInfoMsgEmpDetailAction.DETAIL_INIT%>';
    
    openQuickTabPage(FormQueryString(persPrivInfoMsgEmpListForm), 'persPrivInfoMsgEmpDetail'); 
} 

 /**
  * 생성
  */
function goCreate()
{
 	createValidationCheck(myGrid, "persPrivInfoMsgEmpDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
 	persPrivInfoMsgEmpListForm.elements['maMyInfoCommonDTO.msgEmpSetId'].value = "";
 	goCommonTabPage(persPrivInfoMsgEmpListForm, '', pageId, pageId);
}
 
  /**
   * 삭제
   */
function goDelete()
{
	var delArray = getDeletRows(myGrid, 'isDelCheck', 'msgEmpSetId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	persPrivInfoMsgEmpListForm.strutsAction.value = '<%=PersPrivInfoMsgEmpListAction.LIST_DELETE%>';
	var url = contextPath + "/persPrivInfoMsgEmpList.do";
	
	$.post(url,FormQueryString(persPrivInfoMsgEmpListForm)+delArray , function(_data){
    	afterDelete();
    });
}
  
function afterDelete()
{
	goClose('persPrivInfoMsgEmpDetail', this);
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	persPrivInfoMsgEmpListForm.elements['maMyInfoCommonDTO.msgEmpSetId'].value = "";
    excelServerAction("persPrivInfoMsgEmpList", persPrivInfoMsgEmpListForm);
}


//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/persPrivInfoMsgEmpList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maMyInfoCommonDTO.msgEmpSetId"/><!-- Key -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
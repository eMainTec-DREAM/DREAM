<%--===========================================================================
author  youngjoo38
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.list.action.WorkListPtrlResultListAction" %>
<%@ page import="dream.work.list.action.WorkListPtrlResultMstrDetailAction"%>
<%@ page import="dream.work.list.action.WorkListPointListAction" %>
<%@ page import="dream.work.list.action.WorkListPointDetailAction" %>

<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 점검내용 -->
<title><bean:message key='LABEL.pmComtents'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

/** 자동완성 변수 */
var eqLocAc, empAc, equipAc;


function loadPage() 
{
	workListPointListForm.elements['workListPointListDTO.filterPmPtrlRsltListId'].value = workListPointListForm.elements['workListPtrlResultMstrDetailDTO.pmPtrlRsltListId'].value;
	
	initGrid();
	
	hideBtn("SAVE");
	hideBtn("EDITCNCL");
	
}
/**
 * 그리드 초기화
 */
function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    myGrid.enableSmartRendering(true,500);
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
        goOpen();
    });
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	workListPointListForm.elements['workListPointListDTO.pmPtrlRsltPointId'].value = "";
        return sortColumn("workListPointList", this, workListPointListForm, "PMPTRLRSLTPOINTID", ind, direction);
    });
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
    myGrid.init();
    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/workListPointList.do";
    workListPointListForm.elements['strutsAction'].value = '<%=WorkListPointListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workListPointListForm), "PMPTRLRSLTPOINTID" , "Y");

}
/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pmPtrlRsltPointId)
{
    workListPointListForm.elements['workListPointListDTO.pmPtrlRsltPointId'].value = _pmPtrlRsltPointId;
    findGridList('ReloadRow');
    workListPointListForm.elements['workListPointListDTO.pmPtrlRsltPointId'].value = "";
}

function goSearch()
{
    if(checkValidation()) return;
    workListPointListForm.elements['workListPointListDTO.pmPtrlRsltPointId'].value = "";  // 검색시 Tab 이동Key Clear
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
    workListPointListForm.elements['workListPointListDTO.pmPtrlRsltPointId'].value =  getValueById(myGrid, selectedId,'PMPTRLRSLTPOINTID');  
    
    goCommonTabPage(workListPointListForm, <%= WorkListPointDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('workListPointDetail');
}

function goOpenAction()
{
    //openQuickTabPage(FormQueryString(workListPointListForm),'workListPointDetail');
	var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    workListPointListForm.elements['workListPointListDTO.pmPtrlRsltPointId'].value =  getValueById(myGrid, selectedId,'PMPTRLRSLTPOINTID');  
    workListPointListForm.elements['strutsAction'].value = '<%=WorkListPointDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(workListPointListForm), 'workListPointDetail'); 
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'PMPTRLRSLTPOINTID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
        alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
        return;
    }

    workListPointListForm.strutsAction.value = '<%=WorkListPointListAction.LIST_DELETE%>';
    var url = contextPath + "/workListPointList.do";
    
    $.post(url,FormQueryString(workListPointListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('workListPointDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	workListPointListForm.elements['workListPointListDTO.pmPtrlRsltPointId'].value = "";
    excelServerAction("workListPointList", workListPointListForm );  
}

/**
 * Make Editable Mode
 */
function goEdit()
{
	hideBtn("OPEN");
	hideBtn("DELETE");
	hideBtn("EXCEL");
	hideBtn("SETTING");
	
	editRow(myGrid);	

    //Close Detail Page if it is open
    goClose("workListPointDetail", this);

    //set for update mark.
    setForUpdate();
    
    showBtn("SAVE");
    hideBtn("EDIT");
    showBtn("EDITCNCL");
    
    //Set Grid as updatable
	var url = contextPath + "/workListPointList.do";
	var stAct = "<%=WorkListPointListAction.LIST_SAVE %>";
	//URL, StrutsAction, Grid Obj, Call Back Function, Validation Rule (not_empty)
	proGrid = setGridUpdate(url, stAct, myGrid, "afterSave",'{"PMPOINTRSLTSTATUSDESC":"not_empty","PTRLINSPECTORDESC":"not_empty"}');
	
	//change column type (acedp (Auto complete, LOV), aced(Auto complete only), ednum(Auto complete and number only))
	//setColumnType(myGrid,"EQASMBDESC","acedp");
	setColumnType(myGrid,"PMPOINTRSLTSTATUSDESC","acedp"); //AC,EDIT,POPUP
	setColumnType(myGrid,"PTRLINSPECTORDESC","acedp"); //AC,EDIT,POPUP
	setColumnType(myGrid,"REMARK","ed"); //EDIT
}
/**
 * 수정모드 취소 
 */
function goEditcncl()
{
	editcnclRow(myGrid);
	
	afterSave();
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
	showBtn("EDIT");
	hideBtn("EDITCNCL");
	
	showBtn("OPEN");
	showBtn("DELETE");
	showBtn("EXCEL");
	showBtn("SETTING");
	
	//attach Event to open detail page
	//addRowSelEvent();

	//Clear Key Value
    workListPointListForm.elements['workListPointListDTO.pmPtrlRsltPointId'].value = "";
	
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
 	var assetAc = new autoC({"PTRLINSPECTORDESC":"emp_name"});
	assetAc.setCol(_cellObj); //mandatory
	assetAc.setGrid(_gridObj); //mandatory
    assetAc.setTable("TAEMP");
    assetAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    assetAc.setAcResultMap({
        "PTRLINSPECTORID":"emp_id"
    });
    assetAc.setKeyName("PTRLINSPECTORID");
    assetAc.init(); 

    acSysDesc("PMPOINTRSLTSTATUSDESC","pmPointRsltStatusId","PM_POINT_RSLT_STATUS",true,_gridObj,_cellObj);
}
 
//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workListPointList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="workListPtrlResultCommonDTO.pmPtrlRsltListId"/>
<html:hidden property="workListPtrlResultMstrDetailDTO.pmPtrlRsltListId"/>

<html:hidden property="workListPointListDTO.pmPtrlRsltPointId"/><!-- Key -->

<html:hidden property="workListPointListDTO.filterPmPtrlRsltListId"/>
<html:hidden property="workListPointListDTO.filterPtrlAreaId"/>
<html:hidden property="workListPointListDTO.filterPointType"/>
<html:hidden property="workListPointListDTO.filterPmPointRsltStatusId"/>
<html:hidden property="workListPointListDTO.filterPtrlInspectorId"/>

    <!-- searchbox 박스 Line -->
    <div class="article_box" id="listBox">
        <div class="grid_area">
            <div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
        </div>
    </div>
</html:form> 
</body>
</html>


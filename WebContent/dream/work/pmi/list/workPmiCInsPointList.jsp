<%--===========================================================================
author  youngjoo38
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.pm.list.action.MaPmMstrListAction" %>
<%@ page import="dream.work.pm.list.action.WorkPmiCInsDetailAction" %>
<%@ page import="dream.work.pm.list.action.WorkPmiCInsPointListAction" %>
<%@ page import="dream.work.pm.list.action.WorkPmiCInsPointDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 점검 -->
<title><bean:message key='LABEL.pm'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

function loadPage() 
{ 
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
    	workPmiCInsPointListForm.elements['workPmiCInsCommonDTO.pmInsDPointId'].value = "";
        return sortColumn("workPmiCInsPointList", this, workPmiCInsPointListForm, "PMINSDPOINTID", ind, direction);
    });
	myGrid.attachEvent("onEditCell", function(stage,rId,cInd,nValue,oValue){

		if(stage == 2 && getValueById(myGrid, rId, "CHECKTYPEID") == "VAL")
		{
			var checkMin = getValueById(myGrid, rId, "checkMin");
			var checkMax = getValueById(myGrid, rId, "checkMax");

			if(myGrid.getColumnId(cInd) == "RESULTVALUE" || myGrid.getColumnId(cInd) == "RESULTVALUE2" || myGrid.getColumnId(cInd) == "RESULTVALUE3")
			{
				var resultVal = getValueById(myGrid, rId, "resultValue");
				var resultVal2 = getValueById(myGrid, rId, "resultValue2");
				var resultVal3 = getValueById(myGrid, rId, "resultValue3");

				var pmPointRsltStatus = "GD";
				
				if(Number(resultVal) != 0 && resultVal != null
						&& (Number(resultVal) < Number(checkMin) || Number(resultVal) > Number(checkMax)))
				{
					pmPointRsltStatus = "BD";
					
					setValueById(myGrid, rId, "pmPointRsltStatusId", "BD");
					setValueById(myGrid, rId, "pmPointRsltStatus", "이상");
				}
				if(Number(resultVal2) != 0 && resultVal2 != null
						&& (Number(resultVal2) < Number(checkMin) || Number(resultVal2) > Number(checkMax)))
				{
					pmPointRsltStatus = "BD";
					
					setValueById(myGrid, rId, "pmPointRsltStatusId", "BD");
					setValueById(myGrid, rId, "pmPointRsltStatus", "이상");
				}
				if(Number(resultVal3) != 0 && resultVal3 != null
						&& (Number(resultVal3) < Number(checkMin) || Number(resultVal3) > Number(checkMax)))
				{
					pmPointRsltStatus = "BD";
					
					setValueById(myGrid, rId, "pmPointRsltStatusId", "BD");
					setValueById(myGrid, rId, "pmPointRsltStatus", "이상");
				}
				
				if(pmPointRsltStatus != "BD")
				{
					setValueById(myGrid, rId, "pmPointRsltStatusId", "GD");
					setValueById(myGrid, rId, "pmPointRsltStatus", "정상");
				}
			}
		}

	    return true;
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
    var url = contextPath + "/workPmiCInsPointList.do";
    workPmiCInsPointListForm.elements['strutsAction'].value = '<%=WorkPmiCInsPointListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workPmiCInsPointListForm), "PMINSDPOINTID", "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pmInsDPointId)
{
    workPmiCInsPointListForm.elements['workPmiCInsCommonDTO.pmInsDPointId'].value = _pmInsDPointId;
    findGridList('ReloadRow');
    workPmiCInsPointListForm.elements['workPmiCInsCommonDTO.pmInsDPointId'].value = "";
}

function goSearch()
{
    if(checkValidation()) return;
    
    workPmiCInsPointListForm.elements['workPmiCInsCommonDTO.pmInsDPointId'].value = "";  // 검색시 Tab 이동Key Clear
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
    workPmiCInsPointListForm.elements['workPmiCInsCommonDTO.pmInsDPointId'].value =  getValueById(myGrid, selectedId,'PMINSDPOINTID');  
    
    goCommonTabPage(workPmiCInsPointListForm, <%= WorkPmiCInsPointDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('workPmiCInsPointDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    workPmiCInsPointListForm.elements['workPmiCInsCommonDTO.pmInsDPointId'].value =  getValueById(myGrid, selectedId,'PMINSDPOINTID');  
    workPmiCInsPointListForm.elements['strutsAction'].value = '<%=WorkPmiCInsPointDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(workPmiCInsPointListForm), 'workPmiCInsPointDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "workPmiCInsPointDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
    workPmiCInsPointListForm.elements['workPmiCInsCommonDTO.pmInsDPointId'].value = "";
    goCommonTabPage(workPmiCInsPointListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'PMINSDPOINTID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
        alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
        return;
    }

    workPmiCInsPointListForm.strutsAction.value = '<%=WorkPmiCInsPointListAction.LIST_DELETE%>';
    var url = contextPath + "/workPmiCInsPointList.do";
    
    $.post(url,FormQueryString(workPmiCInsPointListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('workPmiCInsPointDetail');
    
	var wpplPage = searchPage("workPmiCInsPointList");
	if(typeof wpplPage.goSearch  == "function") wpplPage.goSearch();
	
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	if(checkValidation()) return;
    workPmiCInsPointListForm.elements['workPmiCInsCommonDTO.pmInsDPointId'].value = "";
   excelServerAction("workPmiCInsPointList", workPmiCInsPointListForm);
}

/**
 * Make Editable Mode
 */
function goEdit()
{
	hideBtn("OPEN");
	hideBtn("EXCEL");
	hideBtn("SETTING");
	
	editRow(myGrid);	

    //Close Detail Page if it is open
    goClose("workPmiCInsPointDetail", this);

    //set for update mark.
    setForUpdate();
    
    showBtn("SAVE");
    hideBtn("EDIT");
    showBtn("EDITCNCL");
    
    //Set Grid as updatable
	var url = contextPath + "/workPmiCInsPointList.do";
	var stAct = "<%=WorkPmiCInsPointListAction.EDIT_LIST_SAVE %>";
	//URL, StrutsAction, Grid Obj, Call Back Function, Validation Rule (not_empty)
	proGrid = setGridUpdate(url, stAct, myGrid, "afterSave",'{}');
	
	//change column type (acedp (Auto complete, LOV), aced(Auto complete only), ednum(Auto complete and number only))
	//setColumnType(myGrid,"EQASMBDESC","acedp");
	setColumnType(myGrid,"PMPOINTRSLTSTATUS","acedp"); //AC,EDIT,POPUP
	setColumnType(myGrid,"RESULTVALUE","edtynum"); //EDIT,NUMBER
	setColumnType(myGrid,"RESULTVALUE2","edtynum"); //EDIT,NUMBER
	setColumnType(myGrid,"RESULTVALUE3","edtynum"); //EDIT,NUMBER
	setColumnType(myGrid,"insDetail","ed"); //EDIT
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
	showBtn("EXCEL");
	showBtn("SETTING");
	
	//attach Event to open detail page
	//addRowSelEvent();

	//Clear Key Value
    workPmiCInsPointListForm.elements['workPmiCInsCommonDTO.pmInsDPointId'].value = "";
	
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
    acSysDesc("PMPOINTRSLTSTATUS","PMPOINTRSLTSTATUSID","PM_POINT_RSLT_STATUS",true,_gridObj,_cellObj);
}
 
//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workPmiCInsPointList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="workPmiCInsCommonDTO.pmInsDPointId"/><!-- Key -->
<html:hidden property="workPmiCInsCommonDTO.pmInsDListId"/>

    <!-- searchbox 박스 Line -->
    <div class="article_box" id="listBox">
        <div class="grid_area">
            <div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
        </div>
    </div>
    
</html:form> 
</body>
</html>


<%--===========================================================================
점검작업 점검
author  kim21017
version $Id: workPmiPointList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.pmi.list.action.WorkPmiPointListAction" %>
<%@ page import="dream.work.pmi.list.action.WorkPmiPointDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 검사항목 -->
<title><bean:message key='TAB.workPmiPointList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
var beforePageId = '';
<!--//
function loadPage() 
{
	initGrid();
	
	hideBtn("SAVE");
	hideBtn("ADD");
	hideBtn("DEL");
	hideBtn("EDITCNCL");
	
	//hideBtn("EDIT");
}

var myGrid, evId, proGrid, rId; //GridObj, Event Id, Process Obj, Row Id
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen(rowId);
	});  
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	workPmiPointListForm.elements['workPmiPointListDTO.pmInsPointId'].value = "";
    	workPmiPointListForm.elements['workPmiPointListDTO.pmPointId'].value = "";
        return sortColumn("workPmiPointList", this, workPmiPointListForm, "PMPOINTID", ind, direction);
    });
	myGrid.attachEvent("onEditCell", function(stage,rId,cInd,nValue,oValue){
		
		if(stage == 2 && myGrid.getColumnId(cInd) == "RESULTVALUE" && getValueById(myGrid, rId, "CHECKTYPEID") == "VAL")
		{
			var resultVal = getValueById(myGrid, rId, "resultValue");
			
			var checkMin = getValueById(myGrid, rId, "checkMin");
			var checkMax = getValueById(myGrid, rId, "checkMax");

			if (Number(resultVal) != 0 && resultVal != null && Number(checkMin) != 0 && checkMin != null
					&& Number(resultVal) < Number(checkMin) )
			{
				setValueById(myGrid, rId, "pmPointRsltStatus", "BD");
				setValueById(myGrid, rId, "RSLTSTATUSDESC", '<bean:message key="CODESET.PM_POINT_RSLT_STATUS.BD"/>');
			}
			else if (Number(resultVal) != 0 && resultVal != null && Number(checkMax) != 0 && checkMax != null
					&& Number(resultVal) > Number(checkMax) )
			{
				setValueById(myGrid, rId, "pmPointRsltStatus", "BD");
				setValueById(myGrid, rId, "RSLTSTATUSDESC", '<bean:message key="CODESET.PM_POINT_RSLT_STATUS.BD"/>');
			}
			else
			{
				setValueById(myGrid, rId, "pmPointRsltStatus", "GD");
				setValueById(myGrid, rId, "RSLTSTATUSDESC", '<bean:message key="CODESET.PM_POINT_RSLT_STATUS.GD"/>');
			}
		}

	    return true;
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
	myGrid.init();
	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
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
	
// 	showBtn("COPY");
	showBtn("OPEN");
	showBtn("DELETE");
	showBtn("EXCEL");
	showBtn("SETTING");
	
	//attach Event to open detail page
	//addRowSelEvent();

	//Clear Key Value
	workPmiPointListForm.elements['workPmiPointListDTO.pmInsPointId'].value = "";
	workPmiPointListForm.elements['workPmiPointListDTO.pmPointId'].value = "";
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
	/* var assetAc = new autoC({"EQASMBDESC":"description"});
	assetAc.setCol(_cellObj); //mandatory
	assetAc.setGrid(_gridObj); //mandatory
    assetAc.setTable("TAASSET");
    assetAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    assetAc.setAcResultMap({
        "STEPNUM":"asset_id"
    });
    assetAc.setKeyName("STEPNUM");
    assetAc.init();   */
    
    acSysDesc("RSLTSTATUSDESC","PMPOINTRSLTSTATUS","PM_POINT_RSLT_STATUS",true,_gridObj,_cellObj);
}

/**
 * Add new row
 */
function goAdd()
{
	//Add Row (return Row ID)
    rId = addRow(myGrid);
    
    sequenceNextVal('SQAEQUIP_ID');
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
 * Set the Sequence receved to new row.
 */
function setSequenceVal(sequenceVal)
{
	var pminslistId = workPmiPointListForm.elements['workPmiCommonDTO.pminslistId'].value;
	var pmpointId   = workPmiPointListForm.elements['workPmiPointListDTO.pmPointId'].value;
	setValueById(myGrid, rId, "PMINSPOINTID", sequenceVal);
	setValueById(myGrid, rId, "PMPOINTID", pmpointId);
	setValueById(myGrid, rId, "PMINSLISTID", pminslistId);
	setValueById(myGrid, rId, "COMPNO", loginUser.compNo);
}

/**
 * Make Editable Mode
 */
function goEdit()
{
	hideBtn("COPY");
	hideBtn("OPEN");
	hideBtn("DELETE");
	hideBtn("EXCEL");
	hideBtn("SETTING");
	
	editRow(myGrid);	

    //Close Detail Page if it is open
    goClose("workPmiPointDetail", this);

    //set for update mark.
    setForUpdate();
    
    showBtn("SAVE");
    //showBtn("ADD");
    showBtn("DEL");
    hideBtn("EDIT");
    showBtn("EDITCNCL");
    
    //Set Grid as updatable
	var url = contextPath + "/workPmiPointList.do";
	var stAct = "<%=WorkPmiPointListAction.WORK_PMI_POINT_LIST_SAVE %>";
	//URL, StrutsAction, Grid Obj, Call Back Function, Validation Rule (not_empty)
	proGrid = setGridUpdate(url, stAct, myGrid, "afterSave",'{"RSLTSTATUSDESC":"not_empty","RESULTVALUE":"not_empty"}');
	
	//change column type (acedp (Auto complete, LOV), aced(Auto complete only), ednum(Auto complete and number only))
	//setColumnType(myGrid,"EQASMBDESC","acedp");
	setColumnType(myGrid,"RSLTSTATUSDESC","acedp"); //AC,EDIT,POPUP
	setColumnType(myGrid,"RESULTVALUE","edtynum"); //EDIT,NUMBER
	setColumnType(myGrid,"RESULTREMARK","ed"); //EDIT
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
 * 검색 클릭시 호출
 */
function goSearch()
{
	workPmiPointListForm.elements['workPmiPointListDTO.pmInsPointId'].value = "";
	workPmiPointListForm.elements['workPmiPointListDTO.pmPointId'].value = "";

	myGrid.setNumberFormat("0,000.0",myGrid.getColIndexById("RESULTVALUE"),".",",");
    findGridList('Search', "PMINSPOINTID");   
}

function findGridList(sheetAction, keyCol)
{
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (workPmiPointListForm.elements['workPmiCommonDTO.pminslistId'].value == '') return;
	
	var form = document.workPmiPointListForm;	
	form.strutsAction.value = '<%=WorkPmiPointListAction.WORK_PMI_POINT_LIST_FIND %>';
	<%--form.elements['strutsAction'].value = '<%=WorkPmiPointListAction.WORK_PMI_POINT_LIST_FIND %>';--%>
	
	var url = contextPath + "/workPmiPointList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workPmiPointListForm), "PMPOINTID", "Y");

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
	var form = document.workPmiPointListForm;
	
	form.elements['workPmiPointListDTO.pmInsPointId'].value = getValueById(myGrid, selectedId,'PMINSPOINTID');
	form.elements['workPmiPointListDTO.pmPointId'].value = getValueById(myGrid, selectedId,'PMPOINTID');
	
	goCommonTabPage(form, <%= WorkPmiPointDetailAction.WORK_PMI_POINT_DETAIL_INIT %>, pageId, beforePageId); 
	
	beforePageId = pageId;
}

/**
 * 상세열기
 */
 function goOpen(rowId){
	var detailPage  = getValueById(myGrid, rowId,'DETAILPAGE');
	
	workPmiPointListForm.elements['workPmiPointListDTO.pointDetailPageId'].value = detailPage;
	
	goTabPage(detailPage);
}
 function goOpenAction()
 {
     var selectedId=myGrid.getSelectedRowId();
     if(selectedId == null) return;
     
     var detailPage  = getValueById(myGrid, selectedId,'DETAILPAGE');
     
     workPmiPointListForm.elements['workPmiPointListDTO.pmInsPointId'].value = getValueById(myGrid, selectedId,'PMINSPOINTID');
     workPmiPointListForm.elements['workPmiPointListDTO.pmPointId'].value = getValueById(myGrid, selectedId,'PMPOINTID');
     workPmiPointListForm.elements['strutsAction'].value = '<%= WorkPmiPointDetailAction.WORK_PMI_POINT_DETAIL_INIT %>';
     openQuickTabPage(FormQueryString(workPmiPointListForm), detailPage); 
 }

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
  	//excelAction(myGrid);
	workPmiPointListForm.elements['workPmiPointListDTO.pmInsPointId'].value = "";
    workPmiPointListForm.elements['workPmiPointListDTO.pmPointId'].value = "";
	excelServerAction("workPmiPointList", workPmiPointListForm);
  } 

 /** 
  * 수정된 그리드 1건을 다시 조회한다.
  */
 function findGridRow(_pmInsPointId, _pmPointId)
 {
	workPmiPointListForm.elements['workPmiPointListDTO.pmInsPointId'].value = _pmInsPointId;
	workPmiPointListForm.elements['workPmiPointListDTO.pmPointId'].value = _pmPointId;
 	findGridList('ReloadRow', 'PMINSPOINTID');
 	workPmiPointListForm.elements['workPmiPointListDTO.pmInsPointId'].value = "";	
 	workPmiPointListForm.elements['workPmiPointListDTO.pmPointId'].value = "";
 	
 	goReloadTabPageAction(beforePageId, _pmInsPointId, _pmPointId)
 }
 
 function goReloadTabPageAction(pageId, _pmInsPointId, _pmPointId)
 {
 	var form = document.workPmiPointListForm;
 	
 	form.elements['workPmiPointListDTO.pmInsPointId'].value = _pmInsPointId;
 	form.elements['workPmiPointListDTO.pmPointId'].value = _pmPointId;
 	
 	goCommonTabPage(form, <%= WorkPmiPointDetailAction.WORK_PMI_POINT_DETAIL_INIT %>, pageId);
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'PMINSPOINTID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	workPmiPointListForm.strutsAction.value = '<%=WorkPmiPointListAction.WORK_PMI_POINT_LIST_DELETE%>';
	var url = contextPath + "/workPmiPointList.do";
	
	$.post(url,FormQueryString(workPmiPointListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose(beforePageId);
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
   
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = workPmiPointListForm.elements['workPmiPointListDTO.pmInsPointId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workPmiPointList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="workPmiCommonDTO.pminslistId"/><!-- Key -->
<html:hidden property="workPmiCommonDTO.pmschedStatusId"/>
<html:hidden property="workPmiPointListDTO.pmInsPointId"/>
<html:hidden property="workPmiPointListDTO.pmPointId"/>
<html:hidden property="workPmiPointListDTO.pointDetailPageId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
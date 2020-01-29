<%--===========================================================================
작업결과(예방작업 - 점검) 검사항목
author  kim21017
version $Id: maWoResultPmInsPointList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.list.action.MaWoResultPointListAction" %>
<%@ page import="dream.work.list.action.MaWoResultPointDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 검사항목 -->
<title><bean:message key='TAB.maWoResultPointList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

function loadPage() 
{
	initGrid();
	
	hideBtn("SAVE");
	hideBtn("EDITCNCL");
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("maWoResultPmInsPointDetail");
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	maWoResultPointListForm.elements['maWoResultPointListDTO.woPointId'].value = "";
        return sortColumn("maWoResultPointList", this, maWoResultPointListForm, "WOPOINTID", ind, direction);
     });
	myGrid.attachEvent("onEditCell", function(stage,rId,cInd,nValue,oValue){
		
		if(stage == 2 && myGrid.getColumnId(cInd) == "RESULTVALUE" && getValueById(myGrid, rId, "CHECKTYPEID") == "VAL")
		{
			var resultVal = getValueById(myGrid, rId, "resultValue");
			
			var checkMin = getValueById(myGrid, rId, "checkMin");
			var checkMax = getValueById(myGrid, rId, "checkMax");

			if(Number(resultVal) != 0 && resultVal != null
					&& Number(resultVal) < Number(checkMin) || Number(resultVal) > Number(checkMax))
			{
				setValueById(myGrid, rId, "pmPointRsltStatus", "BD");
				setValueById(myGrid, rId, "RSLTSTATUSDESC", "이상");
			}
			else
			{
				setValueById(myGrid, rId, "pmPointRsltStatus", "GD");
				setValueById(myGrid, rId, "RSLTSTATUSDESC", "정상");
			}
		}

	    return true;
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
    findGridList('Search', "WOPOINTID");   
}

function findGridList(sheetAction, keyCol)
{
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (maWoResultPointListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value == '') return;
	
	var form = document.maWoResultPointListForm;	
	form.strutsAction.value = '<%=MaWoResultPointListAction.WO_RESULT_POINT_LIST_FIND %>';
	
	var url = contextPath + "/maWoResultPointList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maWoResultPointListForm), keyCol, "Y");

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
	var form = document.maWoResultPointListForm;
	 
	form.elements['maWoResultPointListDTO.woPointId'].value = getValueById(myGrid, selectedId,'WOPOINTID');
	form.elements['maWoResultPointListDTO.pmPointId'].value = getValueById(myGrid, selectedId,'PMPOINTID');
	
	goCommonTabPage(form, <%= MaWoResultPointDetailAction.WO_RESULT_POINT_DETAIL_INIT %>, pageId);
}

/**
 * Open 버튼 클릭
 */
function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
 	if(selectedId == null) return;

 	maWoResultPointListForm.elements['maWoResultPointListDTO.woPointId'].value = getValueById(myGrid, selectedId,'WOPOINTID');
 	maWoResultPointListForm.elements['maWoResultPointListDTO.pmPointId'].value = getValueById(myGrid, selectedId,'PMPOINTID');
 	maWoResultPointListForm.elements['strutsAction'].value = '<%=MaWoResultPointDetailAction.WO_RESULT_POINT_DETAIL_INIT%>';
 	openQuickTabPage(FormQueryString(maWoResultPointListForm), 'maWoResultPmInsPointDetail'); 
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('maWoResultPmInsPointDetail');
}

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	maWoResultPointListForm.elements['maWoResultPointListDTO.woPointId'].value = "";
  	excelAction("maWoResultPointList",maWoResultPointListForm);
  } 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "maWoResultPmInsPointDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	maWoResultPointListForm.elements['maWoResultPointListDTO.woPointId'].value = "";
	goCommonTabPage(maWoResultPointListForm, '', pageId);
 }

 /** 
  * 수정된 그리드 1건을 다시 조회한다.
  */
 function findGridRow(_woPointId, _pmPointId)
 {
	maWoResultPointListForm.elements['maWoResultPointListDTO.woPointId'].value = _woPointId;
	maWoResultPointListForm.elements['maWoResultPointListDTO.pmPointId'].value = _pmPointId;
 	findGridList('ReloadRow', "PMPOINTID");
 	maWoResultPointListForm.elements['maWoResultPointListDTO.woPointId'].value = "";
 	maWoResultPointListForm.elements['maWoResultPointListDTO.pmPointId'].value = "";
 	
 	goReloadTabPageAction("maWoResultPmInsPointDetail", _woPointId, _pmPointId);
 }
 
 function goReloadTabPageAction(pageId, _woPointId, _pmPointId)
 {
 	var form = document.maWoResultPointListForm;
 	
 	form.elements['maWoResultPointListDTO.woPointId'].value = _woPointId;
 	form.elements['maWoResultPointListDTO.pmPointId'].value = _pmPointId;
 	
 	goCommonTabPage(form, <%= MaWoResultPointDetailAction.WO_RESULT_POINT_DETAIL_INIT %>, pageId);
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'WOPOINTID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maWoResultPointListForm.strutsAction.value = '<%=MaWoResultPointListAction.WO_RESULT_POINT_LIST_DELETE%>';
	var url = contextPath + "/maWoResultPointList.do";
	
	$.post(url,FormQueryString(maWoResultPointListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('maWoResultPmInsPointDetail',this);
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
   

/**
 * Make Editable Mode
 */
function goEdit()
{
	hideBtn("OPEN");
	hideBtn("CREATE");
	hideBtn("DELETE");
	hideBtn("EXCEL");
	hideBtn("SETTING");
	
	editRow(myGrid);	

    //Close Detail Page if it is open
    goClose("maWoResultPmInsPointDetail", this);

    //set for update mark.
    setForUpdate();
    
    showBtn("SAVE");
    hideBtn("EDIT");
    showBtn("EDITCNCL");
    
    //Set Grid as updatable
	var url = contextPath + "/maWoResultPointList.do";
	var stAct = "<%=MaWoResultPointListAction.WO_RESULT_POINT_LIST_SAVE %>";
	//URL, StrutsAction, Grid Obj, Call Back Function, Validation Rule (not_empty)
	proGrid = setGridUpdate(url, stAct, myGrid, "afterSave",'{"RSLTSTATUSDESC":"not_empty","RESULTVALUE":"not_empty"}');
	
	//change column type (acedp (Auto complete, LOV), aced(Auto complete only), ednum(Auto complete and number only))
	//setColumnType(myGrid,"EQASMBDESC","acedp");
	setColumnType(myGrid,"RSLTSTATUSDESC","acedp"); //AC,EDIT,POPUP
	setColumnType(myGrid,"RESULTVALUE","ednum"); //EDIT,NUMBER
	setColumnType(myGrid,"RESULTREMARK","ed"); //EDIT
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
	showBtn("CREATE");
	showBtn("DELETE");
	showBtn("EXCEL");
	showBtn("SETTING");
	
	//attach Event to open detail page
	//addRowSelEvent();

	//Clear Key Value
	maWoResultPointListForm.elements['maWoResultPointListDTO.woPointId'].value = "";
	maWoResultPointListForm.elements['maWoResultPointListDTO.pmPointId'].value = "";
	
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
    
    acSysDesc("RSLTSTATUSDESC","PMPOINTRSLTSTATUS","PM_POINT_RSLT_STATUS",true,_gridObj,_cellObj);
}
   
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maWoResultPointList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maWoResultMstrCommonDTO.wkOrId"/><!-- Key -->
<html:hidden property="maWoResultPointListDTO.woPointId"/>
<html:hidden property="maWoResultPointListDTO.pmPointId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
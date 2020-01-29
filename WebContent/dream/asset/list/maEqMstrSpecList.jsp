<%--===========================================================================
설비제원(스펙)
author  kim21017
version $Id: maEqMstrSpecList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.list.action.MaEqMstrSpecListAction" %>
<%@ page import="dream.asset.list.action.MaEqMstrSpecDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 설비제원 -->
<title><bean:message key='TAB.maEqMstrSpecList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var isNew = false;
function loadPage() 
{
	initGrid();
	
	hideBtn("SAVE");
	//hideBtn("ADD");
	hideBtn("DEL");
	hideBtn("EDITCNCL");

	//표준부품선택
	stdSpecAc = new autoC({"maEqMstrSpecDetailDTO.multiDesc":"EQCTGASMBDESC"});
	stdSpecAc.setTable("TAEQCTGSPEC");
	stdSpecAc.setAcConditionMap({
        "comp_no" : loginUser.compNo
        , "is_use" : "Y"
    })
    stdSpecAc.setAcDConditionMap({
        "eqctg_id" : "maEqMstrDetailDTO.eqCtgId"
    })
	stdSpecAc.setAcResultMap({
        "maEqMstrSpecDetailDTO.multiKey":"eqCtgSpecId"
    });
	stdSpecAc.setMultiSelect(true);
	stdSpecAc.init();
	
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
		goOpen();
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		maEqMstrSpecListForm.elements['maEqMstrSpecListDTO.eqSpecId'].value = "";
    	return sortColumn("maEqMstrSpecList", this, maEqMstrSpecListForm, "EQSPECID", ind, direction);
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
	if (maEqMstrSpecListForm.elements['maEqMstrCommonDTO.equipId'].value == '') return;
	var form = document.maEqMstrSpecListForm;	
	form.strutsAction.value = '<%=MaEqMstrSpecListAction.EQ_MSTR_SPEC_LIST_FIND %>';
	
	var url = contextPath + "/maEqMstrSpecList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maEqMstrSpecListForm), "EQSPECID", "Y");
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
	var form = document.maEqMstrSpecListForm;
	
	if(!isNew)
	{
		form.elements['maEqMstrSpecListDTO.eqSpecId'].value = getValueById(myGrid, selectedId,'EQSPECID');
	}
	
	goCommonTabPage(form, <%= MaEqMstrSpecDetailAction.EQ_MSTR_SPEC_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_eqSpecId)
{
	maEqMstrSpecListForm.elements['maEqMstrSpecListDTO.eqSpecId'].value = _eqSpecId;
	findGridList('ReloadRow');
	maEqMstrSpecListForm.elements['maEqMstrSpecListDTO.eqSpecId'].value = "";
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('maEqMstrSpecDetail');
}

 function goOpenAction()
 {
     var selectedId=myGrid.getSelectedRowId();
     if(selectedId == null) return;
     
     if(!isNew)
 	{
     	maEqMstrSpecListForm.elements['maEqMstrSpecListDTO.eqSpecId'].value =  getValueById(myGrid, selectedId,'EQSPECID');  
 	}
     maEqMstrSpecListForm.elements['strutsAction'].value = '<%=MaEqMstrSpecDetailAction.EQ_MSTR_SPEC_DETAIL_INIT %>';
     openQuickTabPage(FormQueryString(maEqMstrSpecListForm), 'maEqMstrSpecDetail'); 
 } 

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
  	//excelAction(myGrid);
    maEqMstrSpecListForm.elements['maEqMstrSpecListDTO.eqSpecId'].value =  "";
    excelServerAction("maEqMstrSpecList", maEqMstrSpecListForm );  
  }
 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "maEqMstrSpecDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	maEqMstrSpecListForm.elements['maEqMstrSpecListDTO.eqSpecId'].value = "";
	goCommonTabPage(maEqMstrSpecListForm, '', pageId);
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'EQSPECID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined") return;if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maEqMstrSpecListForm.strutsAction.value = '<%=MaEqMstrSpecListAction.EQ_MSTR_SPEC_LIST_DELETE%>';
	var url = contextPath + "/maEqMstrSpecList.do";
	
	$.post(url,FormQueryString(maEqMstrSpecListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('maEqMstrSpecDetail',this);
   	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }

/**
 * Make Editable Mode
 */
function goEdit()
{
	hideBtn("CREATE");
	hideBtn("DELETE");
	hideBtn("EXCEL");
	hideBtn("OPEN");
	hideBtn("SETTING");
	
	editRow(myGrid);	

    //Close Detail Page if it is open
    goClose("maEqMstrSpecDetail", this);

    //set for update mark.
    setForUpdate();
    
    showBtn("SAVE");
    //showBtn("ADD");
    //showBtn("DEL");
    hideBtn("EDIT");
    showBtn("EDITCNCL");
    
    //Set Grid as updatable
	var url = contextPath + "/maEqMstrSpecList.do";
	var stAct = "<%=MaEqMstrSpecListAction.LIST_SAVE %>";
	//URL, StrutsAction, Grid Obj, Call Back Function, Validation Rule (not_empty)
	proGrid = setGridUpdate(url, stAct, myGrid, "afterSave",'{}');
	
	//change column type (acedp (Auto complete, LOV), aced(Auto complete only), ednum(Auto complete and number only))
	setColumnType(myGrid,"RESPONSE","ed"); //EDIT
	setColumnType(myGrid,"UOM","ed"); //EDIT
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
	hideBtn("ADD");
	hideBtn("DEL");
	showBtn("EDIT");
	hideBtn("EDITCNCL");
	
	showBtn("CREATE");
	showBtn("DELETE");
	showBtn("EXCEL");
	showBtn("OPEN");
	showBtn("SETTING");
	
	//attach Event to open detail page
	//addRowSelEvent();

	//Clear Key Value
	maEqMstrSpecListForm.elements['maEqMstrSpecListDTO.eqSpecId'].value = "";
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

}

/**
 * Add new row
 */
function goAdd()
{
	//Add Row (return Row ID)
    //rId = addRow(myGrid);
    
    //sequenceNextVal('SQAEQUIP_ID');
}

/**
 * Put the delete Mark
 */
function goDel()
{
	//del Row (return Row ID)
	//delRow(myGrid);
}

function setKeyAftercopy(_newId)
{
	findGridRow(_newId);
	
	//상세 창 열기
	isNew = true;
	maEqMstrSpecListForm.elements['maEqMstrSpecListDTO.eqSpecId'].value = _newId;
	goOpen();
	isNew = false;
}
   
   

function goStdsp()
{
	var eqCtgId = maEqMstrSpecListForm.elements['maEqMstrDetailDTO.eqCtgId'].value;

	// 상세 페이지에서 설비 종류를 선택 안한경우 return
	if(""==eqCtgId || "undefined" == typeof eqCtgId)
	{
        alertMessage1('<bean:message key="MESSAGE.MSG0234"/>'); // 설비종류가 선택되지 않았습니다.
		return;
	}
	
 	stdSpecAc.openLov();
}   

function setAcLovValue(rtnValueArr, acInputName) //JSONArray
{
	if(acInputName == 'maEqMstrSpecDetailDTO.multiDesc')
	{
		afterSetValue(acInputName);	
	}
}


/**
 * 팝업 값 선택 후 DB INSERT
 */
function afterSetValue(lovType,rtnValue)
{
	if(lovType == 'maEqMstrSpecDetailDTO.multiDesc') {
		maEqMstrSpecListForm.strutsAction.value = '<%=MaEqMstrSpecListAction.EQ_MSTR_SPEC_LIST_INPUT%>';
	}
	
	var url = contextPath + "/maEqMstrSpecList.do";
	
    $.post(url,FormQueryString(maEqMstrSpecListForm), function(_data){
    	goSearch();
    });
}


//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maEqMstrSpecList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maEqMstrCommonDTO.equipId"/><!-- Key -->
<html:hidden property="maEqMstrSpecListDTO.eqSpecId"/>
<html:hidden property="maEqMstrDetailDTO.eqCtgId"/>
<html:hidden property="maEqMstrSpecDetailDTO.multiKey"/><!-- MultiSelect Key -->
<html:hidden property="maEqMstrSpecDetailDTO.multiDesc"/><!-- MultiSelect Desc -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
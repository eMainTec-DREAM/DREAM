<%--===========================================================================
구성자재
author  kim21017
version $Id: maEqMstrPartList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.list.action.MaEqMstrPartListAction" %>
<%@ page import="dream.asset.list.action.MaEqMstrPartDetailAction" %>
<%@ page import="dream.asset.list.action.MaEqMstrDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 구성자재 -->
<title><bean:message key='TAB.maEqMstrPartList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var isNew = false;
var partAc, stdPartAc;

function loadPage() 
{
	initGrid();
	
	hideBtn("SAVE");
	//hideBtn("ADD");
	hideBtn("DEL");
	hideBtn("EDITCNCL");
	
	//일괄등록
	partAc = new autoC({"maEqMstrPartDetailDTO.multiDesc":"full_desc"});
	partAc.setTable("TAPARTS");
	partAc.setAcResultMap({
        "maEqMstrPartDetailDTO.multiKey":"part_id"
    });
	partAc.setAcConditionMap({
		"comp_no":loginUser.compNo
	});
	partAc.setMultiSelect(true);
	partAc.init();

	// 표준부품선택
	stdPartAc = new autoC({"maEqMstrPartListDTO.eqCtgPartDescs":"partNameSize"});
	stdPartAc.setTable("TAEQCTGPART");
	stdPartAc.setAcConditionMap({
        "comp_no" : loginUser.compNo
        , "is_use" : "Y"
    });
    stdPartAc.setAcDConditionMap({
        "eqctg_id" : "maEqMstrDetailDTO.eqCtgId"
    });
	stdPartAc.setAcResultMap({
        "maEqMstrPartListDTO.eqCtgPartIds":"eqCtgPartId"
    });
	stdPartAc.setMultiSelect(true);
	stdPartAc.init();
	
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
	 
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	maEqMstrPartListForm.elements['maEqMstrPartListDTO.eqPartId'].value = "";
       return sortColumn("maEqMstrPartList", this, maEqMstrPartListForm, "EQPARTID", ind, direction);
   });
     
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
	    goOpen();
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ 
		setCounter(grdObj,"gridbox")
	}); 
	myGrid.init();

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
	if (maEqMstrPartListForm.elements['maEqMstrCommonDTO.equipId'].value == '') return;
	
	var form = document.maEqMstrPartListForm;	
	form.strutsAction.value = '<%=MaEqMstrPartListAction.EQ_MSTR_PART_LIST_FIND %>';
	
	var url = contextPath + "/maEqMstrPartList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maEqMstrPartListForm), "EQPARTID","Y");
	//doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maEqMstrPartListForm), "EQPARTID");
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
	var form = document.maEqMstrPartListForm;
	 
	if(!isNew)
	{
		form.elements['maEqMstrPartListDTO.eqPartId'].value = getValueById(myGrid, selectedId,'EQPARTID');
	}
	goCommonTabPage(form, <%= MaEqMstrPartDetailAction.EQ_MSTR_PART_DETAIL_INIT %>, pageId);
}


/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('maEqMstrPartDetail');
}

 function goOpenAction()
 {
     var selectedId=myGrid.getSelectedRowId();
     if(selectedId == null) return;
     if(getValueById(myGrid, selectedId,'PARTTYPE') == "ASMB") return;
     
     var form = document.maEqMstrPartListForm;
	 
 	form.elements['maEqMstrPartListDTO.eqPartId'].value = getValueById(myGrid, selectedId,'EQPARTID');
     form.elements['strutsAction'].value = '<%=MaEqMstrPartDetailAction.EQ_MSTR_PART_DETAIL_INIT%>';
     openQuickTabPage(FormQueryString(form), 'maEqMstrPartDetail'); 
 } 

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	  //excelAction(myGrid);
	  maEqMstrPartListForm.elements['maEqMstrPartListDTO.eqPartId'].value = "";
	  excelServerAction("maEqMstrPartList", maEqMstrPartListForm );  
  }
 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "maEqMstrPartDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	maEqMstrPartListForm.elements['maEqMstrPartListDTO.eqPartId'].value = "";
	goCommonTabPage(maEqMstrPartListForm, '', pageId);
 }
 /** 
  * 수정된 그리드 1건을 다시 조회한다.
  */
 function findGridRow(_eqPartId)
 {
	maEqMstrPartListForm.elements['maEqMstrPartListDTO.eqPartId'].value = _eqPartId;
 	findGridList('ReloadRow');
 	maEqMstrPartListForm.elements['maEqMstrPartListDTO.eqPartId'].value = "";
 }
 /**
  * 삭제
  */
function goDelete(){
	 
	// 공통부위여부가 Y인경우 삭제 불가
	var chkedRowsId = getCheckedRows(myGrid, 'ISDELCHECK');
	
    for(var i = 0 ;i < chkedRowsId.length; i++)
    {
        if(getValueById(myGrid, chkedRowsId[i], "isEqTypePart") == "Y")
        {
            alertMessage1('<bean:message key="MESSAGE.MSG0177"/>'); // 공통부위여부가 Y인 부품은 삭제할 수 없습니다.
            return;
        }
    }
	
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'EQPARTID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maEqMstrPartListForm.strutsAction.value = '<%=MaEqMstrPartListAction.EQ_MSTR_PART_LIST_DELETE%>';
	var url = contextPath + "/maEqMstrPartList.do";
	
	$.post(url,FormQueryString(maEqMstrPartListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('maEqMstrPartDetail',this);
   	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}
//detail EquipId 가져오기
function getEquipId(){
 	return parent.M$('maEqMstrDetailDTO.equipId').value;
}

/**
 * 일괄등록
 */
function goRegbatch()
{
	partAc.openLov();
}

function setAcLovValue(rtnValueArr, acInputName) //JSONArray
{
	if(acInputName == 'maEqMstrPartDetailDTO.multiDesc')
	{
		// detail EquipId 가져오기
		maEqMstrPartListForm.elements['maEqMstrPartDetailDTO.equipId'].value = getEquipId();
		// 공통부위여부 N으로 셋팅
		maEqMstrPartListForm.elements['maEqMstrPartDetailDTO.isEqTypePart'].value = "N";
		// 사용여부 Y로 셋팅
		maEqMstrPartListForm.elements['maEqMstrPartDetailDTO.isUse'].value = "Y";
		// 구성수량 1로 셋팅
		maEqMstrPartListForm.elements['maEqMstrPartDetailDTO.consistQty'].value = "1";
		
		afterSetValue(acInputName);
	} 
	if(acInputName == 'maEqMstrPartListDTO.eqCtgPartDescs')
	{
		afterSetValue(acInputName);
	}
}
/**
 * 팝업 값 선택 후 DB INSERT
 */
function afterSetValue(lovType,rtnValue)
{
	if(lovType == 'maEqMstrPartDetailDTO.multiDesc') {
		maEqMstrPartListForm.strutsAction.value = '<%=MaEqMstrPartListAction.EQ_MSTR_PART_LIST_INPUT%>';
	}
	if(lovType == 'maEqMstrPartListDTO.eqCtgPartDescs') {
		maEqMstrPartListForm.strutsAction.value = '<%=MaEqMstrPartListAction.EQ_MSTR_CTG_PART_LIST_INPUT%>';
	}
	
	var url = contextPath + "/maEqMstrPartList.do";
	
    $.post(url,FormQueryString(maEqMstrPartListForm), function(_data){
    	goSearch();
    });
}

/*
 * 복사 후 key 값 초기화
 */
function setKeyAftercopy(_newId)
{
	findGridRow(_newId);
	
	//상세 창 열기
	isNew = true;
	maEqMstrPartListForm.elements['maEqMstrPartListDTO.eqPartId'].value = _newId;
	goTabPage("maEqMstrPartDetail");
	isNew = false;
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
    goClose("maEqMstrPartDetail", this);

    //set for update mark.
    setForUpdate();
    
    showBtn("SAVE");
    //showBtn("ADD");
    //showBtn("DEL");
    hideBtn("EDIT");
    showBtn("EDITCNCL");
    
    //Set Grid as updatable
	var url = contextPath + "/maEqMstrPartList.do";
	var stAct = "<%=MaEqMstrPartListAction.LIST_SAVE %>";
	//URL, StrutsAction, Grid Obj, Call Back Function, Validation Rule (not_empty)
	proGrid = setGridUpdate(url, stAct, myGrid, "afterSave",'{}');
	
	//change column type (acedp (Auto complete, LOV), aced(Auto complete only), ednum(Auto complete and number only))
	setColumnType(myGrid,"CONSISTQTY","ednum"); //EDIT
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
	maEqMstrPartListForm.elements['maEqMstrPartListDTO.eqPartId'].value = "";
	
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
   
function goStdpt()
{
	var eqCtgId = maEqMstrPartListForm.elements['maEqMstrDetailDTO.eqCtgId'].value;
   	
	// 상세 페이지에서 설비 종류를 선택 안한경우 return
	if(""==eqCtgId || "undefined" == typeof eqCtgId)
	{
		alertMessage1('<bean:message key="MESSAGE.MSG0234"/>'); // 설비종류가 선택되지 않았습니다.
		return;
	}
	
 	stdPartAc.openLov();
}   

function goPtstock()
{
	var parts = '';
	
	var chkedRowsId = getCheckedRows(myGrid, 'ISDELCHECK');
	
	if(chkedRowsId.length == 0)
	{
		alertMessage1('<bean:message key="MESSAGE.MSG213"/>');
		return;
	}
	
    for(var i = 0 ;i < chkedRowsId.length; i++)
    {
        if(i==0) parts += getValueById(myGrid, chkedRowsId[i], "partNo");
        else parts += '+' + getValueById(myGrid, chkedRowsId[i], "partNo");
    }
	
	var _param = "strutsAction=0"+
				 "&maPtStckCommonDTO.filterPartNo="+parts;
	openQuickPage(_param, "maPtStckList"); 
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maEqMstrPartList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maEqMstrCommonDTO.equipId"/><!-- Key -->
<html:hidden property="maEqMstrPartListDTO.eqPartId"/>
<html:hidden property="maEqMstrPartListDTO.eqCtgPartIds"/>
<html:hidden property="maEqMstrPartListDTO.eqCtgPartDescs"/>
<html:hidden property="maEqMstrPartDetailDTO.multiKey"/><!-- MultiSelect Key -->
<html:hidden property="maEqMstrPartDetailDTO.multiDesc"/><!-- MultiSelect Desc -->
<html:hidden property="maEqMstrPartDetailDTO.equipId"/>
<html:hidden property="maEqMstrPartDetailDTO.isEqTypePart"/>
<html:hidden property="maEqMstrPartDetailDTO.isUse"/>
<html:hidden property="maEqMstrPartDetailDTO.consistQty"/>
<html:hidden property="maEqMstrDetailDTO.eqCtgId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
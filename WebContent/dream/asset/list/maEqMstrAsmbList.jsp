<%--===========================================================================
설비부위
author  kim21017
version $Id: maEqMstrAsmbList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.list.action.MaEqMstrAsmbListAction" %>
<%@ page import="dream.asset.list.action.MaEqMstrAsmbDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 설비부위 -->
<title><bean:message key='TAB.maEqMstrAsmbList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var isNew = false;
var stdAsmbAc;

function loadPage() 
{
	initGrid();
	
	hideBtn("SAVE");
	//hideBtn("ADD");
	hideBtn("DEL");
	hideBtn("EDITCNCL");
	
	//표준부위선택
	stdAsmbAc = new autoC({"maEqMstrAsmbDetailDTO.multiDesc":"description"});
	stdAsmbAc.setTable("TAEQCTGASMB");
	stdAsmbAc.setAcConditionMap({
        "comp_no": loginUser.compNo
      , "is_use" : "Y"
     });
	stdAsmbAc.setAcDConditionMap({
        "eqctg_id": "maEqMstrDetailDTO.eqCtgId"
     });
	stdAsmbAc.setAcResultMap({
        "maEqMstrAsmbDetailDTO.multiKey":"eq_ctg_asmb_id"
    });
	stdAsmbAc.setMultiSelect(true);
	stdAsmbAc.init();
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
    myGrid.enableTreeGridLines();
    myGrid.setImageSize(1,1);
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")});
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		//goTabPage("maEqMstrAsmbDetail");
		selectedId = rowId;
        selectedInd = columnId;
        goOpen();
	});
	myGrid.init();

	setHeader(myGrid, "gridbox", "setCal"); // grid, grid id, callBack
}

function setCal()
{
	myGrid.setDateFormat("%Y-%m-%d %H:%i:%s","%Y%m%d%H%i%s");
	myGrid.setDateFormat("%Y-%m-%d","%Y%m%d");
	goSearch();
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
    //findGridList('Search');   
    maEqMstrAsmbListForm.elements['maEqMstrAsmbListDTO.eqAsmbId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('SearchTree');
}

function findGridList(sheetAction)
{
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	// equiID / pmId(부위링크메뉴 사용) 둘 중 하나라도 값이 있으면 retrun 하지 않는다
	// if (maEqMstrAsmbListForm.elements['maEqMstrCommonDTO.equipId'].value == '' )	return;
	if (!(maEqMstrAsmbListForm.elements['maEqMstrCommonDTO.equipId'].value != '' || maEqMstrAsmbListForm.elements['maEqMstrAsmbListDTO.pmId'].value != ''))	return;
	
	var form = document.maEqMstrAsmbListForm;	
	form.strutsAction.value = '<%=MaEqMstrAsmbListAction.EQ_MSTR_ASMB_LIST_FIND %>';
	
	var url = contextPath + "/maEqMstrAsmbList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maEqMstrAsmbListForm), "EQASMBID", "", "PEQASMBID");
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
	var form = document.maEqMstrAsmbListForm;
	 
	if(!isNew)
	{
		form.elements['maEqMstrAsmbListDTO.eqAsmbId'].value = getValueById(myGrid, selectedId,'EQASMBID');
		form.elements['maEqMstrAsmbDetailDTO.peqAsmbId'].value = getValueById(myGrid, selectedId,'EQASMBID');
		form.elements['maEqMstrAsmbDetailDTO.peqAsmbDesc'].value = getValueById(myGrid, selectedId,'EQASMBDESC');
		
	}
	
	goCommonTabPage(form, <%= MaEqMstrAsmbDetailAction.EQ_MSTR_ASMB_DETAIL_INIT %>, pageId);
}
/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_eqAsmbId)
{
	maEqMstrAsmbListForm.elements['maEqMstrAsmbListDTO.eqAsmbId'].value = _eqAsmbId;
	//findGridList('ReloadRow');
    findGridList('ReloadTreeRow');
    
	maEqMstrAsmbListForm.elements['maEqMstrAsmbListDTO.eqAsmbId'].value = "";
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('maEqMstrAsmbDetail');
}

 function goOpenAction()
 {
     var selectedId=myGrid.getSelectedRowId();
     if(selectedId == null) return;
     
     var form = document.maEqMstrAsmbListForm;
	 
     if(!isNew)
 	{
 		form.elements['maEqMstrAsmbListDTO.eqAsmbId'].value = getValueById(myGrid, selectedId,'EQASMBID');
 	}
 	form.elements['strutsAction'].value = '<%=MaEqMstrAsmbDetailAction.EQ_MSTR_ASMB_DETAIL_INIT%>';
     openQuickTabPage(FormQueryString(form), 'maEqMstrAsmbDetail'); 
 } 
 
 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	  maEqMstrAsmbListForm.elements['maEqMstrAsmbListDTO.eqAsmbId'].value = "";
	  excelServerAction("maEqMstrAsmbList", maEqMstrAsmbListForm );
  }
 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "maEqMstrAsmbDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	maEqMstrAsmbListForm.elements['maEqMstrAsmbListDTO.eqAsmbId'].value = "";
	goCommonTabPage(maEqMstrAsmbListForm, '', pageId);
 }
 
 /**
  * 삭제
  */
function goDelete(){
	 var delArray = getSelectRows(myGrid, 'isDelCheck', 'EQASMBID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maEqMstrAsmbListForm.strutsAction.value = '<%=MaEqMstrAsmbListAction.EQ_MSTR_ASMB_LIST_DELETE%>';
	var url = contextPath + "/maEqMstrAsmbList.do";
	
	$.post(url,FormQueryString(maEqMstrAsmbListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete()
{
	goClose('maEqMstrAsmbDetail',this);
	
  	goSearch();
  	
  	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
  	
  	//parent.frames["tabFrameTAB.maEqMstrAsmbList"].goSearch();
  }

/**
 * Make Editable Mode
 */
function goEdit()
{
	hideBtn("STDASMB");
	hideBtn("OPEN");
	hideBtn("CREATE");
	hideBtn("DELETE");
	hideBtn("EXCEL");
	hideBtn("SETTING");
	
	editRow(myGrid);	

    //Close Detail Page if it is open
    goClose("maEqMstrAsmbDetail", this);

    //set for update mark.
    setForUpdate();
    
    showBtn("SAVE");
    //showBtn("ADD");
    //showBtn("DEL");
    hideBtn("EDIT");
    showBtn("EDITCNCL");
    
    //Set Grid as updatable
	var url = contextPath + "/maEqMstrAsmbList.do";
	var stAct = "<%=MaEqMstrAsmbListAction.LIST_SAVE %>";
	//URL, StrutsAction, Grid Obj, Call Back Function, Validation Rule (not_empty)
	proGrid = setGridUpdate(url, stAct, myGrid, "afterSave",'{}');
	
	//change column type (acedp (Auto complete, LOV), aced(Auto complete only), ednum(Auto complete and number only))
	//제작사, 모델, 구입금액,설치일자, 중요스펙, 부위설명, 사용여부, 정렬값
	setColumnType(myGrid,"maker","ed"); //EDIT
	setColumnType(myGrid,"modelNo","ed"); //EDIT
	setColumnType(myGrid,"buyAmt","ednum"); //EDIT,NUMBER
	setColumnType(myGrid,"setupDate","dhxCalendarA"); //EDIT Calendar
	setColumnType(myGrid,"spec","ed"); //EDIT
	setColumnType(myGrid,"REMARK","ed"); //EDIT
	setColumnType(myGrid,"ISUSE","acedp"); //AC,EDIT,POPUP
	setColumnType(myGrid,"ORDNO","ed"); //EDIT
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
//	hideBtn("ADD");
//	hideBtn("DEL");
	showBtn("EDIT");
	hideBtn("EDITCNCL");

	showBtn("STDASMB");
	showBtn("OPEN");
	showBtn("CREATE");
	showBtn("DELETE");
	showBtn("EXCEL");
	showBtn("SETTING");
	
	//attach Event to open detail page
	//addRowSelEvent();

	//Clear Key Value
	maEqMstrAsmbListForm.elements['maEqMstrAsmbListDTO.eqAsmbId'].value = "";
	
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
    acSysDesc("ISUSE","isUse","IS_USE",true,_gridObj,_cellObj);

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


/**
 * 표준부위선택
 */
function goStdasmb()
{
	var eqCtgId = maEqMstrAsmbListForm.elements['maEqMstrDetailDTO.eqCtgId'].value;
	// 상세 페이지에서 설비 종류를 선택 안한경우 return
	if(""==eqCtgId || "undefined" == typeof eqCtgId)
	{
        alertMessage1('<bean:message key="MESSAGE.MSG0234"/>'); // 설비종류가 선택되지 않았습니다.
		return;
	}
	
	stdAsmbAc.openLov();
}

function setAcLovValue(rtnValueArr, acInputName) //JSONArray
{
	if(acInputName == 'maEqMstrAsmbDetailDTO.multiDesc')
	{
		afterSetValue(acInputName);
	} 
}
/**
 * 팝업 값 선택 후 DB INSERT
 */
function afterSetValue(lovType,rtnValue)
{
	if(lovType == 'maEqMstrAsmbDetailDTO.multiDesc') {
		maEqMstrAsmbListForm.strutsAction.value = '<%=MaEqMstrAsmbListAction.EQ_MSTR_ASMB_LIST_INPUT%>';
	}
	
	var url = contextPath + "/maEqMstrAsmbList.do";
	
    $.post(url,FormQueryString(maEqMstrAsmbListForm), function(_data){
    	goSearch();
    });
}

/*
 * 복사 후  재조회
 */
function setKeyAftercopy(_newId,_pageId)
{
	//parent.frames["tabFrameTAB.maEqMstrAsmbList"].goSearch();
	
	findGridRow(_newId);
	
	//상세 창 열기
	isNew = true;
	maEqMstrAsmbListForm.elements['maEqMstrAsmbListDTO.eqAsmbId'].value = _newId; 
	//goOpen();
	goTabPage(_pageId);
	isNew = false;
} 
   
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maEqMstrAsmbList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maEqMstrCommonDTO.equipId"/><!-- Key -->
<html:hidden property="maEqMstrDetailDTO.eqCtgId"/>
<html:hidden property="maEqMstrAsmbListDTO.eqAsmbId"/>
<html:hidden property="maEqMstrAsmbDetailDTO.multiKey"/><!-- MultiSelect Key -->
<html:hidden property="maEqMstrAsmbDetailDTO.multiDesc"/><!-- MultiSelect Desc -->
<html:hidden property="maEqMstrAsmbDetailDTO.isEqTypeAsmb"/>
<html:hidden property="maEqMstrAsmbDetailDTO.isUse"/>
<html:hidden property="maEqMstrAsmbDetailDTO.equipId"/>
<html:hidden property="maEqMstrAsmbDetailDTO.peqAsmbId"/>
<html:hidden property="maEqMstrAsmbDetailDTO.peqAsmbDesc"/>
<html:hidden property="maEqMstrAsmbListDTO.pmId"/>

    <!-- searchbox 박스 Line -->
    
	<div class="section_wrap">
		<div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.List"/></div>
			</div>
			<div class="function_box list">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>
				<div class="fb_group2">
				</div>
			</div>
		</div><!--sheader_box end-->
	    <div class="article_box" id="listBox">
	           <div class="grid_area">
	           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
	           </div>
	 	</div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>
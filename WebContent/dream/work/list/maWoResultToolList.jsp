<%--===========================================================================
투입공기구
author  kim21017
version $Id: maWoResultToolList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.list.action.MaWoResultToolListAction" %>
<%@ page import="dream.work.list.action.MaWoResultToolDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.MwareConfig"%>
<%
	String partGrade = MwareConfig.getPartGrade();
%>
<html>
<head>
<!-- 투입공기구 -->
<title><bean:message key='TAB.maWoResultToolList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var partAc;
function loadPage() 
{
	setForUpdate();
	initGrid();

	hideBtn("SAVE");
	hideBtn("EDITCNCL");
	
	partAc = new autoC({"maWoResultToolDetailDTO.multiPartDesc":"description"});
	partAc.setTable("TAPARTSTOOLS");
	partAc.setAcConditionMap({
	  	"comp_no":loginUser.compNo
  	});
	partAc.setAcResultMap({
        "maWoResultToolDetailDTO.multiPartKey":"part_id"
    });
	partAc.setMultiSelect(true);
	partAc.init();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("maWoResultToolDetail");
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
	if (maWoResultToolListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value == '') return;
	
	var form = document.maWoResultToolListForm;	
	form.strutsAction.value = '<%=MaWoResultToolListAction.WO_RESULT_TOOL_LIST_FIND %>';
	
	var url = contextPath + "/maWoResultToolList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maWoResultToolListForm), "WOTOOLID","Y");
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
	var form = document.maWoResultToolListForm;
	 
	form.elements['maWoResultToolListDTO.woToolId'].value = getValueById(myGrid, selectedId,'WOTOOLID');
	goCommonTabPage(form, <%= MaWoResultToolDetailAction.WO_RESULT_TOOL_DETAIL_INIT %>, pageId);
	
	form.elements['maWoResultToolListDTO.woToolId'].value = "";
}


/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('maWoResultToolDetail');
}

function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
 	if(selectedId == null) return;

 	maWoResultToolListForm.elements['maWoResultToolListDTO.woToolId'].value = getValueById(myGrid, selectedId,'WOTOOLID');
 	maWoResultToolListForm.elements['strutsAction'].value = '<%=MaWoResultToolDetailAction.WO_RESULT_TOOL_DETAIL_INIT%>';
 	openQuickTabPage(FormQueryString(maWoResultToolListForm), 'maWoResultToolDetail'); 
 	maWoResultToolListForm.elements['maWoResultToolListDTO.woToolId'].value = "";
}


 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
  	excelAction(myGrid);
  } 
 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "maWoResultToolDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	maWoResultToolListForm.elements['maWoResultToolListDTO.woToolId'].value = "";
	goCommonTabPage(maWoResultToolListForm, '', pageId);
 }

 /** 
  * 수정된 그리드 1건을 다시 조회한다.
  */
 function findGridRow(_woToolId)
 {
	maWoResultToolListForm.elements['maWoResultToolListDTO.woToolId'].value = _woToolId;
 	findGridList('ReloadRow');
 	maWoResultToolListForm.elements['maWoResultToolListDTO.woToolId'].value = "";
 }
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'WOTOOLID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maWoResultToolListForm.strutsAction.value = '<%=MaWoResultToolListAction.WO_RESULT_TOOL_LIST_DELETE%>';
	var url = contextPath + "/maWoResultToolList.do";
	
	$.post(url,FormQueryString(maWoResultToolListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('maWoResultToolDetail',this);
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

function goSave(){
	
	if(maWoResultToolListForm.strutsAction.value=='<%=MaWoResultToolListAction.WO_RESULT_TOOL_LIST_INPUT%>')
	{
		var url = contextPath + "/maWoResultToolList.do";
	    
	    $.post(url,FormQueryString(maWoResultToolListForm), function(_data){
	    	afterSave(_data);
	    });
	}
	else
	{
		//Send All Data ONce
		proGrid.sendData();
	}
}

function afterSave(ajaxXmlDoc)
{
	if(maWoResultToolListForm.strutsAction.value=='<%=MaWoResultToolListAction.WO_RESULT_TOOL_LIST_INPUT%>')
	{
		//=====================================
	    if (!checkHttpXml(ajaxXmlDoc)) return;
	    //=====================================
	    getTopPage().afterSaveAll(currentPageId);
	    goSearch();
	}
	else
	{
		// After Edit
		afterEditRow(myGrid);
		
		//Control Button
		hideBtn("SAVE");
		showBtn("EDIT");
		hideBtn("EDITCNCL");

		showBtn("USEPARTS");
		showBtn("REGBATCH");
		showBtn("OPEN");
		showBtn("CREATE");
		showBtn("DELETE");
		showBtn("EXCEL");
		showBtn("SETTING");

		//Clear Key Value
		maWoResultToolListForm.elements['maWoResultToolListDTO.woToolId'].value = "";
		
		//Search
		goSearch();
		
		//Clear Update Mark for this page 
		clearUpdate(currentPageId);
		
		//Make Page as Normal
		setForNormal();
	}
}

/**
 * 일괄등록
 */
function goRegbatch()
{
	partAc.openLov();
}


function setAcLovValue(rtnArr, code)
{
	if(code == 'maWoResultToolDetailDTO.multiPartDesc') {
		maWoResultToolListForm.strutsAction.value = '<%=MaWoResultToolListAction.WO_RESULT_TOOL_LIST_INPUT%>';
		//부품등급-A
		maWoResultToolListForm.elements['maWoResultToolDetailDTO.partGradeId'].value = '<%=partGrade%>';
		//부서창고로 기본 세팅
	    maWoResultToolListForm.elements['maWoResultToolDetailDTO.wcodeId'].value = loginUser.twcodeId;
	  	//사용 개수
	    maWoResultToolListForm.elements['maWoResultToolDetailDTO.useQty'].value = '1';
	  	//회수 개수 
	    maWoResultToolListForm.elements['maWoResultToolDetailDTO.disUseRtnQty'].value = '1';
	  	goSaveAll();
	}
}
/**
 * Make Editable Mode
 */
function goEdit()
{
	hideBtn("USEPARTS");
	hideBtn("REGBATCH");
	hideBtn("OPEN");
	hideBtn("CREATE");
	hideBtn("DELETE");
	hideBtn("EXCEL");
	hideBtn("SETTING");
	
	editRow(myGrid);	

    //Close Detail Page if it is open
    goClose("maWoResultToolDetail", this);

    //set for update mark.
    setForUpdate();
    
    showBtn("SAVE");
    hideBtn("EDIT");
    showBtn("EDITCNCL");
    
    //Set Grid as updatable
	var url = contextPath + "/maWoResultToolList.do";
	var stAct = "<%=MaWoResultToolListAction.EDIT_LIST_SAVE %>";
	//URL, StrutsAction, Grid Obj, Call Back Function, Validation Rule (not_empty)
	proGrid = setGridUpdate(url, stAct, myGrid, "afterSave",'{}');
	
	//change column type (acedp (Auto complete, LOV), aced(Auto complete only), ednum(Auto complete and number only))
	setColumnType(myGrid,"USEQTY","ednum"); //EDIT
	setColumnType(myGrid,"DISUSERTNQTY","ednum"); //EDIT
}

/**
 * 수정모드 취소 
 */
function goEditcncl()
{
	editcnclRow(myGrid);
	
	afterSave();
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maWoResultToolList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maWoResultMstrCommonDTO.wkOrId"/><!-- Key -->
<html:hidden property="maWoResultToolListDTO.woToolId"/>
<html:hidden property="maWoResultToolDetailDTO.partGradeId"/>
<html:hidden property="maWoResultToolDetailDTO.wcodeId"/>
<html:hidden property="maWoResultToolDetailDTO.useQty"/>
<html:hidden property="maWoResultToolDetailDTO.disUseRtnQty"/>
<html:hidden property="maWoResultToolDetailDTO.multiPartKey"/><!-- MultiSelect Tool Key -->
<html:hidden property="maWoResultToolDetailDTO.multiPartDesc"/><!-- MultiSelect Tool Desc -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
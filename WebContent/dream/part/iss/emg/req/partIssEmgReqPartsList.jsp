<%--===========================================================================
출고요청부품
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.iss.emg.req.action.PartIssEmgReqPartsListAction" %> 
<%@ page import="dream.part.iss.emg.req.action.PartIssEmgReqPartsDetailAction" %>
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
<!-- 출고요청부품 -->
<title><bean:message key='LABEL.parts'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

var multiPartAc;
function loadPage() 
{
	partIssEmgReqPartsListForm.elements['partIssEmgReqPartsDetailDTO.ptEmgIssReqId'].value = partIssEmgReqPartsListForm.elements['partIssEmgReqCommonDTO.ptEmgIssReqId'].value;
	
    initGrid();
    
    multiPartAc = new autoC({"partIssEmgReqPartsDetailDTO.multiDesc":"full_desc"});
    multiPartAc.setTable("TAPARTS");
    multiPartAc.setAcConditionMap({
	   "comp_no":loginUser.compNo
	   ,"part_categ":"SPPT"
	   });
    multiPartAc.setAcResultMap({
	    "partIssEmgReqPartsDetailDTO.multiKey":"part_id"
	});
    multiPartAc.setMultiSelect(true);
    multiPartAc.init();
    
    hideBtn("SAVE");
	hideBtn("EDITCNCL");
}

function initGrid()
{ 
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	partIssEmgReqPartsListForm.elements['partIssEmgReqCommonDTO.ptEmgIssListId'].value = "";
        return sortColumn("partIssEmgReqPartsList", this, partIssEmgReqPartsListForm, "ptEmgIssListId", ind, direction);
    });
	myGrid.attachEvent("onCellMarked", function(rowId,columnId){
		selectedId = rowId;
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * Make Editable Mode
 */
function goEdit()
{
	hideBtn("REGBATCH");
	hideBtn("OPEN");
	hideBtn("CREATE");
	hideBtn("DELETE");
	hideBtn("EXCEL");
	hideBtn("SETTING");
	
	editRow(myGrid);	
	
    //Close Detail Page if it is open
    goClose("partIssEmgReqPartsDetail", this);
	
    //set for update mark.
    setForUpdate();
    
    showBtn("SAVE");
    hideBtn("EDIT");
    showBtn("EDITCNCL");
    
    //Set Grid as updatable
	var url = contextPath + "/partIssEmgReqPartsList.do";
	var stAct = "<%=PartIssEmgReqPartsListAction.EDIT_LIST_SAVE %>";
	//URL, StrutsAction, Grid Obj, Call Back Function, Validation Rule (not_empty)
	proGrid = setGridUpdate(url, stAct, myGrid, "afterSave",'{}');
	
	setColumnType(myGrid,"issueQty","ed"); //EDIT
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
    var url = contextPath + "/partIssEmgReqPartsList.do";

    partIssEmgReqPartsListForm.elements['strutsAction'].value = '<%=PartIssEmgReqPartsListAction.PTISSEMG_LIST_FIND%>';

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(partIssEmgReqPartsListForm), "ptEmgIssListId");
    
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(ptEmgIssListId)
{
	partIssEmgReqPartsListForm.elements['partIssEmgReqCommonDTO.ptEmgIssListId'].value 	= ptEmgIssListId;
	findGridList('ReloadRow');
}

function goSearch()
{
	partIssEmgReqPartsListForm.elements['partIssEmgReqCommonDTO.ptEmgIssListId'].value = "";     // 검색시 Tab 이동Key Clear
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
	var form = document.partIssEmgReqPartsListForm;
	partIssEmgReqPartsListForm.elements['partIssEmgReqCommonDTO.ptEmgIssListId'].value = getValueById(myGrid, selectedId, 'ptEmgIssListId');

    goCommonTabPage(form, <%=PartIssEmgReqPartsDetailAction.PTISSEMG_DETAIL_INIT%>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('partIssEmgReqPartsDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    partIssEmgReqPartsListForm.elements['partIssEmgReqCommonDTO.ptEmgIssListId'].value = getValueById(myGrid, selectedId, 'ptEmgIssListId');
    partIssEmgReqPartsListForm.elements['strutsAction'].value = '<%=PartIssEmgReqPartsDetailAction.PTISSEMG_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(partIssEmgReqPartsListForm), 'partIssEmgReqPartsDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "partIssEmgReqPartsDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	partIssEmgReqPartsListForm.elements['partIssEmgReqCommonDTO.ptEmgIssListId'].value = "";
    goCommonTabPage(partIssEmgReqPartsListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
	var delArray = getDeletRows(myGrid, 'isDelCheck', 'ptEmgIssListId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    partIssEmgReqPartsListForm.strutsAction.value = '<%=PartIssEmgReqPartsListAction.PTISSEMG_LIST_DELETE%>';
    var url = contextPath + "/partIssEmgReqPartsList.do";
    
    $.post(url,FormQueryString(partIssEmgReqPartsListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
	goClose('partIssEmgReqPartsDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	partIssEmgReqPartsListForm.elements['partIssEmgReqCommonDTO.ptEmgIssListId'].value = "";
	excelServerAction("partIssEmgReqPartsList", partIssEmgReqPartsListForm );  
}

function goSave(){
	if(partIssEmgReqPartsListForm.strutsAction.value == '<%=PartIssEmgReqPartsListAction.PTISSEMG_LIST_INPUT%>'){
		var url = contextPath + "/partIssEmgReqPartsList.do";
		
	    $.post(url,FormQueryString(partIssEmgReqPartsListForm), function(_data){
	    	afterSave(_data);
	    });
	}
	else {
		//Send All Data ONce
		proGrid.sendData();
	}
}

function afterSave(ajaxXmlDoc)
{
	if(partIssEmgReqPartsListForm.strutsAction.value == '<%=PartIssEmgReqPartsListAction.PTISSEMG_LIST_INPUT%>'){
		//=====================================
	    if (!checkHttpXml(ajaxXmlDoc)) return;
	    //=====================================
	    getTopPage().afterSaveAll(currentPageId);
	}
	else {
		afterEditRow(myGrid);
		
		//Control Button
		hideBtn("SAVE");
		showBtn("EDIT");
		hideBtn("EDITCNCL");
		
		showBtn("REGBATCH");
		showBtn("OPEN");
		showBtn("CREATE");
		showBtn("DELETE");
		showBtn("EXCEL");
		showBtn("SETTING");
		
		//attach Event to open detail page
		//addRowSelEvent();

		//Clear Key Value
		partIssEmgReqPartsListForm.elements['partIssEmgReqCommonDTO.ptEmgIssListId'].value = "";
		
		//Clear Update Mark for this page 
		clearUpdate(currentPageId);
	}
	//Search
	goSearch();
	
	//Make Page as Normal
	setForNormal();
}

/**
 * 일괄등록
 */
function goRegbatch()
{
	multiPartAc.openLov();
}

function beforeSetAcValue(code)
{
	if(code == "PART_ID")
	{
		setForUpdate();
	}
}

function setAcLovValue(rtnArr, code)
{
	if(code == 'partIssEmgReqPartsDetailDTO.multiDesc')
	{
		partIssEmgReqPartsListForm.strutsAction.value = '<%=PartIssEmgReqPartsListAction.PTISSEMG_LIST_INPUT%>';
		
		partIssEmgReqPartsListForm.elements['partIssEmgReqPartsDetailDTO.partGrade'].value = '<%=partGrade%>';
		partIssEmgReqPartsListForm.elements['partIssEmgReqPartsDetailDTO.issueQty'].value = 1;
		partIssEmgReqPartsListForm.elements['partIssEmgReqPartsDetailDTO.reqDate'].value = parent.partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.reqDate'].value;
		partIssEmgReqPartsListForm.elements['partIssEmgReqPartsDetailDTO.wcodeId'].value = parent.partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.wcodeId'].value;
		partIssEmgReqPartsListForm.elements['partIssEmgReqPartsDetailDTO.ctctrId'].value = parent.partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.ctctrId'].value;
		partIssEmgReqPartsListForm.elements['partIssEmgReqPartsDetailDTO.toWcodeId'].value = parent.partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.toWcodeId'].value;
		partIssEmgReqPartsListForm.elements['partIssEmgReqPartsDetailDTO.recBy'].value = parent.partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.recBy'].value;
		partIssEmgReqPartsListForm.elements['partIssEmgReqPartsDetailDTO.equipId'].value = parent.partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.equipId'].value;
		partIssEmgReqPartsListForm.elements['partIssEmgReqPartsDetailDTO.plantId'].value = parent.partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.plantId'].value;
		partIssEmgReqPartsListForm.elements['partIssEmgReqPartsDetailDTO.reqBy'].value = parent.partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.reqBy'].value;
		partIssEmgReqPartsListForm.elements['partIssEmgReqPartsDetailDTO.reqDept'].value = parent.partIssEmgReqDetailForm.elements['partIssEmgReqDetailDTO.reqDept'].value;
		partIssEmgReqPartsListForm.elements['partIssEmgReqPartsDetailDTO.multiDesc'].value = JSON.stringify(rtnArr);
		
		goSaveAll();
	}
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/partIssEmgReqPartsList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="partIssEmgReqCommonDTO.ptEmgIssReqId"/><!-- Key -->
<html:hidden property="partIssEmgReqCommonDTO.ptEmgIssListId"/>
<html:hidden property="partIssEmgReqPartsDetailDTO.ptEmgIssReqId"/>
<html:hidden property="partIssEmgReqPartsDetailDTO.partGrade"/>
<html:hidden property="partIssEmgReqPartsDetailDTO.issueQty"/>
<html:hidden property="partIssEmgReqPartsDetailDTO.reqDate"/>
<html:hidden property="partIssEmgReqPartsDetailDTO.wcodeId"/>
<html:hidden property="partIssEmgReqPartsDetailDTO.ctctrId"/>
<html:hidden property="partIssEmgReqPartsDetailDTO.toWcodeId"/>
<html:hidden property="partIssEmgReqPartsDetailDTO.recBy"/>
<html:hidden property="partIssEmgReqPartsDetailDTO.equipId"/>
<html:hidden property="partIssEmgReqPartsDetailDTO.plantId"/>
<html:hidden property="partIssEmgReqPartsDetailDTO.reqBy"/>
<html:hidden property="partIssEmgReqPartsDetailDTO.reqDept"/>   
<html:hidden property="partIssEmgReqPartsDetailDTO.ptemgissStatus"  value="W" />
<html:hidden property="partIssEmgReqPartsDetailDTO.ptissType"  value="COST" />
<html:hidden property="partIssEmgReqPartsDetailDTO.ptemgTaskStatus" value="W" />
<html:hidden property="partIssEmgReqPartsDetailDTO.multiKey"/>
<html:hidden property="partIssEmgReqPartsDetailDTO.multiDesc"/>
	
		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
		
</html:form> 
</body>
</html>
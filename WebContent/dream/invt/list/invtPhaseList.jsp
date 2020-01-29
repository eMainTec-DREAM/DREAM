<%--===========================================================================
응답 목록
author  kim21017
version $Id: invtPhaseList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.invt.list.action.InvtPhaseListAction" %>
<%@ page import="dream.invt.list.action.InvtDetailAction" %>
<%@ page import="dream.invt.list.action.InvtPhaseDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<title><bean:message key='LABEL.questionPoint'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var myGrid;
var evId, proGrid, rId; //Event Id, Process Obj, Row Id
var phaseAc;
function loadPage() 
{
    phaseAc = new autoC({"invtCommonDTO.invtprcphId":"invtprcphId"});
    phaseAc.setTable("TAINVTPRCPH");
    phaseAc.setAcConditionMap({
        "comp_no" : loginUser.compNo
      , "is_use" : "Y"
    })
    phaseAc.setAcDConditionMap({
        "invtprctp_id" : "invtCommonDTO.invtprctpId" 
    })
    phaseAc.setAcResultMap({
        "invtCommonDTO.invtprcphId":"invtprcphId"
    });
    phaseAc.setMultiSelect(true);
    phaseAc.init();
	
	initGrid();
	
	hideBtn("SAVE");
	hideBtn("ADD");
	hideBtn("DEL");
	hideBtn("EDITCNCL");
	//hideBtn("EDIT");
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("invtPhaseDetail");
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		invtPhaseListForm.elements['invtCommonDTO.invtphaseId'].value = "";
        return sortColumn("invtPhaseList", this, invtPhaseListForm, "INVTPHASEID", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox", true)});
	
	myGrid.init();
	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

/* Edit 기능 추가 */
/**
 * Make Editable Mode
 */
function goEdit()
{
	hideBtn("INVTPHASE");
	hideBtn("ADDPHASE");
	hideBtn("OPEN");
	hideBtn("DELETE");
	hideBtn("EXCEL");
	hideBtn("SETTING");
	
	editRow(myGrid);	

    //Close Detail Page if it is open
    goClose("invtPhaseDetail", this);

    //set for update mark.
    setForUpdate();
    
    showBtn("SAVE");
    //showBtn("ADD");
    //showBtn("DEL");
    hideBtn("EDIT");
    showBtn("EDITCNCL");
    
    //Set Grid as updatable
	var url = contextPath + "/invtPhaseList.do";
	var stAct = "<%=InvtPhaseListAction.INVT_PHASE_LIST_SAVE %>";

	//URL, StrutsAction, Grid Obj, Call Back Function, Validation Rule (not_empty)
	proGrid = setGridUpdate(url, stAct, myGrid, "afterSave");

	//change column type (acedp (Auto complete, LOV), aced(Auto complete only), ednum(Auto complete and number only))
	setColumnType(myGrid,"INVTPHASESTATUSDESC","acedp"); //AC,EDIT,POPUP
	setColumnType(myGrid,"startDate","dhxCalendarA"); //AC,EDIT,POPUP
	setColumnType(myGrid,"endDate","dhxCalendarA"); //AC,EDIT,POPUP
	setColumnType(myGrid,"ACTUALAMT","ednum"); //EDIT,NUMBER
	setColumnType(myGrid,"remark","ed"); //EDIT
	
/* 	myGrid.attachEvent("onEditCell",function(stage,rId,cInd,nValue,oValue){
		
		if(stage == 0)
		{
			var ips = getValueById(myGrid, rId, "invtphaseStatus");
			
			console.log(ips);
			
			if(ips == "C") return true;
			else return false;
		}
		else return true;
    }); */

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
	//findGridRow(invtPhaseListForm.elements['invtCommonDTO.invtphaseId'].value);
	
	afterEditRow(myGrid);
	
	if(typeof searchPage("invtDetail").checkStatus == "function"){
		searchPage("invtDetail").checkStatus(invtPhaseListForm.elements['invtCommonDTO.invtlistId'].value);
	}
	
	if(typeof searchPage("reqWoInvtRsltList").findGridRow == "function"){
		searchPage("reqWoInvtRsltList").findGridRow(invtPhaseListForm.elements['invtCommonDTO.invtlistId'].value);
	}

	if(typeof searchPage("maWoReqDetail").checkStatus == "function"){
		searchPage("maWoReqDetail").checkStatus(invtPhaseListForm.elements['invtCommonDTO.woReqResId'].value);	
	}
	
	//Control Button
	hideBtn("SAVE");
	//hideBtn("ADD");
	//hideBtn("DEL");
	showBtn("EDIT");
	hideBtn("EDITCNCL");
	
	showBtn("INVTPHASE");
	showBtn("ADDPHASE");
	showBtn("OPEN");
	showBtn("DELETE");
	showBtn("EXCEL");
	showBtn("SETTING");
	
	//attach Event to open detail page
	//addRowSelEvent();

	//Clear Key Value
	invtPhaseListForm.elements['invtCommonDTO.invtphaseId'].value = "";
	
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
	acSysDesc("INVTPHASESTATUSDESC","INVTPHASESTATUS","INVTPHASE_STATUS", true,_gridObj,_cellObj);
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

/**
 * gird find
 */
function findGridList(sheetAction)
{
	if (invtPhaseListForm.elements['invtCommonDTO.invtlistId'].value == '') return;
	
	var form = document.invtPhaseListForm;	
	form.strutsAction.value = '<%=InvtPhaseListAction.INVT_PHASE_LIST_FIND%>'; 

	var url = contextPath + "/invtPhaseList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(invtPhaseListForm), "invtphaseId","Y");

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
	var form = document.invtPhaseListForm;
	form.elements['invtCommonDTO.invtphaseId'].value = getValueById(myGrid, selectedId,'invtphaseId');
    
	goCommonTabPage(form, <%= InvtPhaseDetailAction.INVT_PHASE_DETAIL_INIT %>, pageId);
	
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_answerId)
{
	invtPhaseListForm.elements['invtCommonDTO.invtphaseId'].value = _answerId;
	findGridList('ReloadRow');
	invtPhaseListForm.elements['invtCommonDTO.invtphaseId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('invtPhaseDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    invtPhaseListForm.elements['invtCommonDTO.invtphaseId'].value =  getValueById(myGrid, selectedId,'invtphaseId');  
    invtPhaseListForm.elements['strutsAction'].value = '<%=InvtPhaseDetailAction.INVT_PHASE_DETAIL_INIT %>';
    openQuickTabPage(FormQueryString(invtPhaseListForm), 'invtPhaseDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "invtPhaseDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	invtPhaseListForm.elements['invtCommonDTO.invtphaseId'].value = "";
	goCommonTabPage(invtPhaseListForm, '', pageId);
}
 

/**
 * Excel Export
 */
function goExcel()
{
    //excelAction(myGrid);
	invtPhaseListForm.elements['invtCommonDTO.invtphaseId'].value = "";
	excelServerAction("invtPhaseList", invtPhaseListForm);
}

/**
 * 삭제
 */
 function goDelete()
 {
	var chkArray = getSelectRows(myGrid, 'ISDELCHECK','invtphaseStatus');
	if(chkArray.match('=RA') || chkArray.match('=OA') || chkArray.match('=C')) {
		alertMessage1('<bean:message key="MESSAGE.MSG0178"/>');
		return;
	}
	
	var delArray = getDeletRows(myGrid, 'ISDELCHECK','invtphaseId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	invtPhaseListForm.strutsAction.value = '<%=InvtPhaseListAction.INVT_PHASE_LIST_DELETE%>';
	var url = contextPath + "/invtPhaseList.do";
	
    $.post(url,FormQueryString(invtPhaseListForm)+delArray , function(_data){
    	afterDelete();
    });
 }

function afterDelete(){
	goClose('invtPhaseDetail', this);
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');

	if(typeof searchPage("invtDetail").checkStatus == "function"){
		searchPage("invtDetail").checkStatus(invtPhaseListForm.elements['invtCommonDTO.invtlistId'].value);
	}
	
	if(typeof searchPage("reqWoInvtRsltList").findGridRow == "function"){
		searchPage("reqWoInvtRsltList").findGridRow(invtPhaseListForm.elements['invtCommonDTO.invtlistId'].value);
	}

	if(typeof searchPage("maWoReqDetail").checkStatus == "function"){
		searchPage("maWoReqDetail").checkStatus(invtPhaseListForm.elements['invtCommonDTO.woReqResId'].value);	
	}
}

/*
 * 투자진행하기
 */
function goInvtphase()
{
	var selectedId=myGrid.getSelectedRowId();
	if(selectedId == "undefined" || selectedId == "" || !selectedId)
	{
		alertMessage1('<bean:message key="MESSAGE.MSG037"/>');
		return;
	}

	var invtphaseId = getValueById(myGrid, selectedId, "invtphaseId");
	var param = "invtPrcDetail";
    
    var url   = contextPath + "/"+param+".do";

    var popWidth = 1010;
    var popHeight = 640;

    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
    
    var param = "strutsAction=1001&invtCommonDTO.invtphaseId="+ invtphaseId;
  
    openWindowWithPost(url, "INVTPHASE_DETAIL", param, pos);
}

/*
 * 절차 추가하기
 */
function goAddphase()
{
	phaseAc.openLov();
}

function setAcLovValue(rtnValueArr, acInputName)
{
	if(acInputName != "INVTPHASESTATUSDESC")
	{
		afterSetValue();
	}
}

/**
 * 팝업 값 선택 후 DB INSERT
 */
function afterSetValue(lovType,rtnValue)
{
	invtPhaseListForm.strutsAction.value = '<%=InvtPhaseListAction.PHASE_INPUT%>';
	var url = contextPath + "/invtPhaseList.do";
	
    $.post(url,FormQueryString(invtPhaseListForm), function(_data){
    	invtPhaseListForm.elements['invtCommonDTO.invtphaseId'].value = "";
    	goSearch();
    });

	if(typeof searchPage("invtDetail").checkStatus == "function"){
		searchPage("invtDetail").checkStatus(invtPhaseListForm.elements['invtCommonDTO.invtlistId'].value);
	}
	
	if(typeof searchPage("reqWoInvtRsltList").findGridList == "function"){
		searchPage("reqWoInvtRsltList").findGridList(invtPhaseListForm.elements['invtCommonDTO.invtlistId'].value);
	}
    
	if(typeof searchPage("maWoReqDetail").checkStatus == "function"){
		searchPage("maWoReqDetail").checkStatus(invtPhaseListForm.elements['invtCommonDTO.woReqResId'].value);	
	}
}

function afterDisable()
{
	setEnableAll();
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = invtPhaseListForm.elements['invtCommonDTO.invtphaseId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/invtPhaseList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="invtCommonDTO.invtlistId"/><!-- Key -->
<html:hidden property="invtCommonDTO.invtphaseId"/>
<html:hidden property="invtCommonDTO.description"/>
<html:hidden property="invtCommonDTO.invtprctpId"/>
<html:hidden property="invtCommonDTO.invtprcphId"/>
<html:hidden property="invtCommonDTO.woReqId"/>
<html:hidden property="invtCommonDTO.woReqResId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>
	<c:set var="filePath" value="enhance/${compName}/invt/list/invtPhaseList_${compNo}.jsp" />
	<c:if test="${udf:isExist(filePath)}">
		<c:import charEncoding="UTF-8" url="/enhance/${compName}/invt/list/invtPhaseList_${compNo}.jsp"></c:import>
	</c:if>
</html:form> 
</body>
</html>
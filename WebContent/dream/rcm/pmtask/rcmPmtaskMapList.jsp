<%--===========================================================================
응답 목록
author  kim21017
version $Id: rcmPmtaskMapList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="common.tree.action.TreeAction" %>
<%@ page import="dream.rcm.pmtask.action.RcmPmtaskMapListAction" %>
<%@ page import="dream.rcm.pmtask.action.RcmPmtaskDetailAction" %>
<%@ page import="dream.rcm.pmtask.action.RcmPmtaskMapDetailAction" %>
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

function loadPage() 
{
	initGrid();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("rcmPmtaskMapDetail");
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	rcmPmtaskMapListForm.elements['rcmPmtaskMapListDTO.rcmpmtaskmapId'].value = "";
    	return sortColumn("rcmPmtaskMapList", this, rcmPmtaskMapListForm, "rcmpmtaskmapId", ind, direction);
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

/**
 * gird find
 */
function findGridList(sheetAction)
{	
	var form = document.rcmPmtaskMapListForm;	
	form.strutsAction.value = '<%=RcmPmtaskMapListAction.FMEA_CRITY_LIST_FIND%>'; 

	rcmPmtaskMapListForm.elements['rcmPmtaskMapListDTO.rcmpmtaskmapId'].value = "";
	
	var url = contextPath + "/rcmPmtaskMapList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(rcmPmtaskMapListForm), "rcmpmtaskmapId", "Y");

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
	var form = document.rcmPmtaskMapListForm;
	form.elements['rcmPmtaskMapListDTO.rcmpmtaskmapId'].value = getValueById(myGrid, selectedId,'rcmpmtaskmapId');
    
	goCommonTabPage(form, <%= RcmPmtaskMapDetailAction.RCM_FMEA_DETAIL_INIT %>, pageId);
	
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_answerId)
{
	rcmPmtaskMapListForm.elements['rcmPmtaskMapListDTO.rcmpmtaskmapId'].value = _answerId;
	findGridList('ReloadRow');
	rcmPmtaskMapListForm.elements['rcmPmtaskMapListDTO.rcmpmtaskmapId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('rcmPmtaskMapDetail');
}

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "rcmPmtaskMapDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	rcmPmtaskMapListForm.elements['rcmPmtaskMapListDTO.rcmpmtaskmapId'].value = "";
	goCommonTabPage(rcmPmtaskMapListForm, '', pageId);
}
 

/**
 * Excel Export
 */
function goExcel()
{
    excelAction(myGrid);
}

function goPmsched()
{
	var pIfm = getIframeContent();

	if(pIfm.ckCreate("rcmPmtaskDetail"))//rcmPmtaskDetail
	{
		pIfm.goSave();
		/* $(pIfm.document).find('[name="rcmPmtaskDetailDTO.pmDesc"]').focus();
		alertMessage1('<bean:message key="MESSAGE.writePmFirst"/>');
		return; */
	}
	
	
	
	var topPage = getTopPage();
	var rowsNum =  myGrid.getRowsNum();

	if(rowsNum > 0)
		topPage.dhtmlx.confirm({
			title: "시작 ",
			type :"myCss",
			ok:"Yes", cancel:"No",
			width : "500px",
			text:'<bean:message key="MESSAGE.pmtaskMapQ"/>',
			callback:function(e){
	
				if(e){
					//Ajax Save with questionObj to TARCMPMTASKMAP Table
					rcmPmtaskMapListForm.strutsAction.value = '<%=RcmPmtaskMapListAction.FMEA_CRITY_LIST_DELETE%>';
					$.post(contextPath + "/rcmPmtaskMapList.do",FormQueryString(rcmPmtaskMapListForm) , function(_data){
				    	goSearch();
						goPmschedAction();
				    }); 
				}
	
			}
		});
	else goPmschedAction();
}

/*
 * 업무선정질문
 */
function goPmschedAction()
{
	rcmPmtaskMapListForm.strutsAction.value = '<%=RcmPmtaskMapListAction.PMTASK_QUESTION_FIND%>';
	var url = contextPath + "/rcmPmtaskMapList.do";
	//질문 조회 (JSON)
	$.post(url,FormQueryString(rcmPmtaskMapListForm) , function(_data){
    	//JSON OBJ
    	console.log(_data);
    	var jsonObj = JSON.parse(_data);  
    	goQuestion(jsonObj);
    });
}

function goQuestion(_result, _mapId, _colName)
{
	if(typeof _mapId == "undefined") _seq = "1";
	if(typeof _colName == "undefinded") _colName = "PMTASKMAPNO";
	
	var questionObj;
	var topPage = getTopPage();
	
	for(var seq in _result)
	{
		if(_result[seq][_colName] == _mapId)
		{
			questionObj = _result[seq];
			break;
		}
	}

	if(typeof questionObj != "undefined")
		topPage.dhtmlx.confirm({
			title : "Question",
			type :"myCss",
			ok:"Yes", cancel:"No",
			width : "500px",
			text:questionObj.DESCRIPTION,
			callback:function(e){

				rcmPmtaskMapListForm.strutsAction.value = '<%=RcmPmtaskMapListAction.PMTASK_QUESTION_INPUT%>';
				rcmPmtaskMapListForm.elements['rcmPmtaskCommonDTO.pmtaskmapId'].value = questionObj.PMTASKMAPID;
				rcmPmtaskMapListForm.elements['rcmPmtaskCommonDTO.pmtaskmapVal'].value = e;
				rcmPmtaskMapListForm.elements['rcmPmtaskCommonDTO.pmtaskmapRsltVal'].value = JSON.stringify(questionObj);

				//Ajax Save with questionObj to TARCMPMTASKMAP Table
				$.post(contextPath + "/rcmPmtaskMapList.do",FormQueryString(rcmPmtaskMapListForm) , function(_data){
			    	goSearch();
			    });
				
				if(e){
					//YES
					console.log(questionObj.TASKMAPRSLTYTYPE);
					if(questionObj.TASKMAPRSLTYTYPE == "Q")
					{
						goQuestion(_result, questionObj.PMTASKMAPYID, "PMTASKMAPID");
					}
				}
				else
				{
					//NO
					if(questionObj.TASKMAPRSLTNTYPE == "Q")
					{
						goQuestion(_result, questionObj.PMTASKMAPNID, "PMTASKMAPID");
					}
					//No 다음질문 번호가 있으면 goQuestion(_result, 다음번호);
				}
	
			}
		});
	
}


/**
 * 삭제
 */
 function goDelete()
 {
	var delArray = getDeletRows(myGrid, 'ISDELCHECK','rcmpmtaskmapId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	rcmPmtaskMapListForm.strutsAction.value = '<%=RcmPmtaskMapListAction.FMEA_CRITY_LIST_DELETE%>';
	var url = contextPath + "/rcmPmtaskMapList.do";
	
    $.post(url,FormQueryString(rcmPmtaskMapListForm)+delArray , function(_data){
    	afterDelete();
    });
 }

function afterDelete(){
	goClose('rcmPmtaskMapDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/rcmPmtaskMapList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="rcmPmtaskCommonDTO.rcmpmtaskId"/><!-- Key -->
<html:hidden property="rcmPmtaskCommonDTO.rcmlistId"/>
<html:hidden property="rcmPmtaskCommonDTO.rcmfmeaId"/>
<html:hidden property="rcmPmtaskCommonDTO.pmtaskmapId"/>
<html:hidden property="rcmPmtaskCommonDTO.pmtaskmapVal"/>
<html:hidden property="rcmPmtaskCommonDTO.pmtaskmapRsltVal"/>

<html:hidden property="rcmPmtaskMapListDTO.rcmpmtaskmapId"/><!-- Detail Key -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" >
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
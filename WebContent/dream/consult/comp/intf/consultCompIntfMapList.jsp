<%--===========================================================================
Interface Map List
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.comp.intf.action.ConsultCompIntfMapListAction" %>
<%@ page import="dream.consult.comp.intf.action.ConsultCompIntfMapDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 인터페이스 로그 -->
<title><bean:message key='MENU.INTFLOG'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

function loadPage() 
{
// 	goSearch();
    initGrid();
}

function initGrid()
{ 
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	myGrid.enableSmartRendering(true,500);
	/*
	//헤어윗줄
	myGrid.setHeader("<bean:message key='LABEL.ISDELCHECK'/>,<bean:message key='LABEL.seqNo'/>" // 선택, 순번
			// param Type, param 순서
 			+",<bean:message key='LABEL.paramTypeDesc'/>,<bean:message key='LABEL.paramSeq'/>"  
 			// Source
			+",<bean:message key='LABEL.source'/>,#cspan,#cspan,#cspan,#cspan,#cspan,#cspan,#cspan"
 			// Target
			+",<bean:message key='LABEL.target'/>,#cspan,#cspan,#cspan,#cspan,#cspan,#cspan,#cspan"
			//INTFMAPID, INTFID
			+"",""
	);
	//헤더 아랫줄
	myGrid.attachHeader(["#rspan","#rspan","#rspan","#rspan"
	                     ,"<bean:message key='LABEL.tableDesc'/>","<bean:message key='LABEL.columnName'/>"
	                     ,"<bean:message key='LABEL.isPkDesc'/>","<bean:message key='LABEL.colType'/>"
	                     ,"<bean:message key='LABEL.columnSize'/>","<bean:message key='LABEL.isNotNull'/>","<bean:message key='LABEL.remark'/>"
	                     ,"<bean:message key='LABEL.tableDesc'/>","<bean:message key='LABEL.columnName'/>"
	                     ,"<bean:message key='LABEL.isPkDesc'/>","<bean:message key='LABEL.colType'/>"
	                     ,"<bean:message key='LABEL.columnSize'/>","<bean:message key='LABEL.isNotNull'/>","<bean:message key='LABEL.remark'/>"
	                     ,"",""
	]);
	//헤더 아이디
	myGrid.setColumnIds("ISDELCHECK,SEQNO,PARAMTYPEDESC,PARAMSEQ" // 3
			  +",STABNAME,SFIELDNAME,SISPKDESC,SFIELDTYPEDESC,SFIELDSIZEDESC,SISNOTNULLDESC,SREMARK"	// 7
			  +",TTABNAME,TFIELDNAME,TISPKDESC,TFIELDTYPEDESC,TFIELDSIZEDESC,TISNOTNULLDESC,TREMARK"	// 7
			  +",INTFMAPID,INTFID"
	);
	myGrid.setInitWidths("60,50,100,100"
					   + ",100,100,100,100,100,100,100"
					   + ",100,100,100,100,100,100,100"
					   + ",50,50"
    );
	myGrid.setColAlign("center,center,left,left"
					   + ",left,left,left,left,left,left,left"
					   + ",left,left,left,left,left,left,left"
					   + ",left,left"
    );
	myGrid.setColTypes("ch,cntr,ro,ro"
					   + ",ro,ro,ro,ro,ro,ro,ro"
					   + ",ro,ro,ro,ro,ro,ro,ro"
					   + ",ro,ro"
    );
	myGrid.setColumnHidden(18,true);
	myGrid.setColumnHidden(19,true);
	myGrid.enableAutoHeight(true,200);
	*/
	
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	consultCompIntfMapListForm.elements['consultCompIntfMapListDTO.intfMapId'].value = "";
    	return sortColumn("consultCompIntfMapList", this, consultCompIntfMapListForm, "INTFMAPID", ind, direction);
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
    var url = contextPath + "/consultCompIntfMapList.do";
    consultCompIntfMapListForm.elements['strutsAction'].value = '<%=ConsultCompIntfMapListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(consultCompIntfMapListForm), "INTFMAPID","Y");
//     setLoading("gridbox");
//     $.post(url,FormQueryString(consultCompIntfMapListForm), function(_data){
//     	myGrid.parse(_data,"js");
//     	setCounter(myGrid,"gridbox");
//     });
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_intfMapId)
{
	consultCompIntfMapListForm.elements['consultCompIntfMapListDTO.intfMapId'].value = _intfMapId;
	findGridList('ReloadRow');
	consultCompIntfMapListForm.elements['consultCompIntfMapListDTO.intfMapId'].value = "";
}

function goSearch()
{
	consultCompIntfMapListForm.elements['consultCompIntfMapListDTO.intfMapId'].value = "";	// 검색시 Tab 이동Key Clear
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
	consultCompIntfMapListForm.elements['consultCompIntfMapListDTO.intfMapId'].value =  getValueById(myGrid, selectedId,'INTFMAPID');  
	goCommonTabPage(consultCompIntfMapListForm, <%= ConsultCompIntfMapDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('consultCompIntfMapDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    consultCompIntfMapListForm.elements['consultCompIntfMapListDTO.intfMapId'].value = getValueById(myGrid, selectedId, 'intfMapId');
    consultCompIntfMapListForm.elements['strutsAction'].value = '<%=ConsultCompIntfMapDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(consultCompIntfMapListForm), 'consultCompIntfMapDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "consultCompIntfMapDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	consultCompIntfMapListForm.elements['consultCompIntfMapListDTO.intfMapId'].value = "";
	goCommonTabPage(consultCompIntfMapListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'INTFMAPID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    consultCompIntfMapListForm.strutsAction.value = '<%=ConsultCompIntfMapListAction.LIST_DELETE%>';
    var url = contextPath + "/consultCompIntfMapList.do";
    
    $.post(url,FormQueryString(consultCompIntfMapListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('consultCompIntfMapDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	consultCompIntfMapListForm.elements['consultCompIntfMapListDTO.intfMapId'].value = "";
	excelServerAction("consultCompIntfMapList", consultCompIntfMapListForm );  
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/consultCompIntfMapList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="consultCompIntfCommonDTO.intfId"/>
<html:hidden property="consultCompIntfCommonDTO.compNo"/>
<html:hidden property="consultCompIntfMapListDTO.intfId"/>
<html:hidden property="consultCompIntfMapListDTO.intfMapId"/><!-- Key -->

		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
		
</html:form> 
</body>
</html>
<%--===========================================================================
설비부위 목록
author  kim21017
version $Id: rcmSysEqAsmbList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="common.tree.action.TreeAction" %>
<%@ page import="dream.rcm.system.action.RcmSysEqAsmbListAction" %>
<%@ page import="dream.rcm.system.action.RcmSysEqAsmbDetailAction" %>
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

var asmbDescAc;
function loadPage() 
{
	initGrid();
	
	asmbDescAc = new autoC({"rcmSysEqAsmbListDTO.multiDesc":"full_desc"});
	asmbDescAc.setTable("TAEQASMB");
	asmbDescAc.setAcConditionMap({
 	   "comp_no":loginUser.compNo, 	   
 	   });
	asmbDescAc.setAcResultMap({
        "rcmSysEqAsmbListDTO.multiKey":"eqasmb_id"
    });
	asmbDescAc.setMultiSelect(true);
	asmbDescAc.init();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("rcmSysEqAsmbDetail");
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
	if (rcmSysEqAsmbListForm.elements['rcmSysEqDetailDTO.rcmEqId'].value == '') 
	{
		return;
	} 
	else 
	{
		rcmSysEqAsmbListForm.elements['rcmSysEqAsmbListDTO.rcmEqId'].value = rcmSysEqAsmbListForm.elements['rcmSysEqDetailDTO.rcmEqId'].value;
		rcmSysEqAsmbListForm.elements['rcmSysEqAsmbListDTO.equipId'].value = rcmSysEqAsmbListForm.elements['rcmSysEqDetailDTO.equipId'].value;
	}
	
	var form = document.rcmSysEqAsmbListForm;
	form.strutsAction.value = '<%=RcmSysEqAsmbListAction.RCM_SYS_EQASMB_LIST_FIND%>'; 

	var url = contextPath + "/rcmSysEqAsmbList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(rcmSysEqAsmbListForm), "RCMEQASMBID");

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
	var form = document.rcmSysEqAsmbListForm;
	form.elements['rcmSysEqAsmbListDTO.rcmEqAsmbId'].value = getValueById(myGrid, selectedId,'RCMEQASMBID');
    
	goCommonTabPage(form, <%= RcmSysEqAsmbDetailAction.RCM_SYS_EQASMB_DETAIL_INIT %>, "rcmSysEqAsmbDetail");
	
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_rcmEqAsmbId)
{
	rcmSysEqAsmbListForm.elements['rcmSysEqAsmbListDTO.rcmEqAsmbId'].value = _rcmEqAsmbId;
	findGridList('ReloadRow');
	rcmSysEqAsmbListForm.elements['rcmSysEqAsmbListDTO.rcmEqAsmbId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('rcmSysEqAsmbDetail');
}

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "rcmSysEqAsmbDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	rcmSysEqAsmbListForm.elements['rcmSysEqAsmbListDTO.rcmEqAsmbId'].value = "";
	goCommonTabPage(rcmSysEqAsmbListForm, '', pageId);
}
 

/**
 * Excel Export
 */
function goExcel()
{
    excelAction(myGrid);
}

/**
 * 삭제
 */
 function goDelete()
 {
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'RCMEQASMBID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	rcmSysEqAsmbListForm.strutsAction.value = '<%=RcmSysEqAsmbListAction.RCM_SYS_EQASMB_LIST_DELETE%>';
	var url = contextPath + "/rcmSysEqAsmbList.do";
	
    $.post(url,FormQueryString(rcmSysEqAsmbListForm)+delArray , function(_data){
    	afterDelete();
    });
 }

function afterDelete(){
	goClose('rcmSysEqAsmbDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * 설비설정 추가 버튼 클릭 시
 */
function goAddasmb()
{
	asmbDescAc.openLov();
}


function setLovValue(returnArray, dirType)
{
	afterSetValue();
}


/**
 * 팝업 값 선택 후 DB INSERT
 */
function afterSetValue(lovType,rtnValue)
{
	rcmSysEqAsmbListForm.strutsAction.value = '<%=RcmSysEqAsmbListAction.RCM_SYS_EQASMB_LIST_INPUT%>';
	var url = contextPath + "/rcmSysEqAsmbList.do";
	
    $.post(url,FormQueryString(rcmSysEqAsmbListForm), function(_data){
    	goSearch()
    });
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/rcmSysEqAsmbList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="rcmSysCommonDTO.rcmListId"/><!-- Key -->
<html:hidden property="rcmSysEqDetailDTO.rcmEqId"/><!-- Key -->
<html:hidden property="rcmSysEqDetailDTO.equipId"/><!-- Key -->
<html:hidden property="rcmSysEqAsmbListDTO.rcmEqId"/><!-- Key -->
<html:hidden property="rcmSysEqAsmbListDTO.equipId"/><!-- Key -->
<html:hidden property="rcmSysEqAsmbListDTO.rcmEqAsmbId"/><!-- Detail Key -->
<html:hidden property="rcmSysEqAsmbListDTO.multiKey"/><!-- MultiSelect Key -->
<html:hidden property="rcmSysEqAsmbListDTO.multiDesc"/><!-- MultiSelect Desc -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" >
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
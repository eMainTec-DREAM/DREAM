<%--===========================================================================
분석자
author  kim21017
version $Id: rcmSysEmpList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.rcm.system.action.RcmSysEmpListAction" %>
<%@ page import="dream.rcm.system.action.RcmSysEmpDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 분석자 -->
<title><bean:message key='TAB.rcmSysEmpList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var mainMngAc;
function loadPage() 
{
	setForUpdate();
	
	initGrid();
	
	mainMngAc = new autoC({"rcmSysEmpListDTO.multiDesc":"emp_name"});
    mainMngAc.setTable("TAEMP");
    mainMngAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_join":"Y"
  	});
    mainMngAc.setAcResultMap({
        "rcmSysEmpListDTO.multiKey":"emp_id"
    });
    mainMngAc.setMultiSelect(true);
    mainMngAc.init();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("rcmSysEmpDetail");
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	rcmSysEmpListForm.elements['rcmSysEmpListDTO.rcmEmpId'].value = "";
    	return sortColumn("rcmSysEmpList", this, rcmSysEmpListForm, "RCMEMPID", ind, direction);
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
	if (rcmSysEmpListForm.elements['rcmSysCommonDTO.rcmListId'].value == '') return;
	
	var form = document.rcmSysEmpListForm;	
	form.strutsAction.value = '<%=RcmSysEmpListAction.RCM_SYS_EMP_LIST_FIND %>';
	
	var url = contextPath + "/rcmSysEmpList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(rcmSysEmpListForm), "RCMEMPID", "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_rcmFuncId)
{
	rcmSysEmpListForm.elements['rcmSysEmpListDTO.rcmEmpId'].value = _rcmFuncId;
	findGridList('ReloadRow');
	rcmSysEmpListForm.elements['rcmSysEmpListDTO.rcmEmpId'].value = "";
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
	var form = document.rcmSysEmpListForm;
	 
	form.elements['rcmSysEmpListDTO.rcmEmpId'].value = getValueById(myGrid, selectedId,'RCMEMPID');
	goCommonTabPage(form, <%= RcmSysEmpDetailAction.RCM_SYS_EMP_DETAIL_INIT %>, pageId);
}


/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('rcmSysEmpDetail');
}

 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "rcmSysEmpDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	rcmSysEmpListForm.elements['rcmSysEmpListDTO.rcmEmpId'].value = "";
	goCommonTabPage(rcmSysEmpListForm, '', pageId);
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'isDelCheck', 'RCMEMPID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	rcmSysEmpListForm.strutsAction.value = '<%=RcmSysEmpListAction.RCM_SYS_EMP_LIST_DELETE%>';
	var url = contextPath + "/rcmSysEmpList.do";
	
	$.post(url,FormQueryString(rcmSysEmpListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('rcmSysEmpDetail',this);
   	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
   
function goExcel()
{
	excelAction(myGrid);
}

function goSave(){
	var url = contextPath + "/rcmSysEmpList.do";
	
    $.post(url,FormQueryString(rcmSysEmpListForm), function(_data){
    	afterSave(_data);
    });
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    getTopPage().afterSaveAll(currentPageId);
    
    goSearch();
}

/**
 * 분석자 추가 버튼 클릭 시
 */
function goAddemp()
{
	mainMngAc.openLov();
}

function setAcLovValue(rtnArr, code)
{
	rcmSysEmpListForm.strutsAction.value = '<%=RcmSysEmpListAction.RCM_SYS_EMP_LIST_INPUT%>';
	
	goSaveAll();
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/rcmSysEmpList">
<html:hidden property="strutsAction"/>
<html:hidden property="rcmSysCommonDTO.rcmListId"/><!-- Key -->
<html:hidden property="rcmSysEmpListDTO.rcmEmpId"/><!-- Key -->
<html:hidden property="rcmSysEmpListDTO.multiKey"/><!-- MultiSelect Key -->
<html:hidden property="rcmSysEmpListDTO.multiDesc"/><!-- MultiSelect Desc -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
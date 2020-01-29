<%--===========================================================================
작업결과(월간작업일정) 투입자재
author  kim21017
version $Id: maWoResultMonthPartList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.list.action.MaWoResultPartListAction" %>
<%@ page import="dream.work.list.action.MaWoResultPartDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.MwareConfig"%>
<%
	String partGrade = MwareConfig.getPartGrade();
%>
<html>
<head>
<!-- 투입자재 -->
<title><bean:message key='TAB.maWoResultPartList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var ptEmgIssAc;
var partAc;
var ptIssAc;
function loadPage() 
{
	setForUpdate();
	
	initGrid();
	
	setFunction();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("maWoResultMonthPartDetail");
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

function setFunction()
{
	ptEmgIssAc = new autoC({"maWoResultPartDetailDTO.multiDesc":"partDesc"});
	ptEmgIssAc.setTable("TAPTEMGISSLIST");
	ptEmgIssAc.setAcResultMap({
        "maWoResultPartDetailDTO.multiKey":"ptemgisslistId"
    });
	ptEmgIssAc.setMultiSelect(true);
	ptEmgIssAc.init();
	
	partAc = new autoC({"maWoResultPartDetailDTO.multiPartDesc":"full_desc"});
	partAc.setTable("TAPARTS");
	partAc.setAcConditionMap({
    	"wcode_id": loginUser.wcodeId,
    	"wcode_desc" : loginUser.wname,
	  	"comp_no":loginUser.compNo
  	});
	partAc.setAcResultMap({
        "maWoResultPartDetailDTO.multiPartKey":"part_id"
    });
	partAc.setMultiSelect(true);
	partAc.init();
	
	ptIssAc = new autoC({"maWoResultPartDetailDTO.multiIssDesc":"partName"});
	ptIssAc.setTable("TAPTISSLIST");
	ptIssAc.setAcResultMap({
        "maWoResultPartDetailDTO.multiIssKey":"ptisslistId"
    });
	ptIssAc.setMultiSelect(true);
	ptIssAc.init();
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
	if (maWoResultPartListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value == '') return;
	
	var form = document.maWoResultPartListForm;	
	form.strutsAction.value = '<%=MaWoResultPartListAction.WO_RESULT_PART_LIST_FIND %>';
	
	var url = contextPath + "/maWoResultPartList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maWoResultPartListForm), "WOPARTID","Y");
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
	var form = document.maWoResultPartListForm;
	 
	form.elements['maWoResultPartListDTO.woPartId'].value = getValueById(myGrid, selectedId,'WOPARTID');
	goCommonTabPage(form, <%= MaWoResultPartDetailAction.WO_RESULT_PART_DETAIL_INIT %>, pageId);
	
	form.elements['maWoResultPartListDTO.woPartId'].value = "";
}


/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('maWoResultMonthPartDetail');
}


 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	maWoResultPartListForm.elements['maWoResultPartListDTO.woPartId'].value = "";
  	excelServerAction("maWoResultPartList",maWoResultPartListForm);
  } 
 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "maWoResultMonthPartDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	maWoResultPartListForm.elements['maWoResultPartListDTO.woPartId'].value = "";
	goCommonTabPage(maWoResultPartListForm, '', pageId);
 }

 /** 
  * 수정된 그리드 1건을 다시 조회한다.
  */
 function findGridRow(_woPartId)
 {
	maWoResultPartListForm.elements['maWoResultPartListDTO.woPartId'].value = _woPartId;
 	findGridList('ReloadRow');
 	maWoResultPartListForm.elements['maWoResultPartListDTO.woPartId'].value = "";
 }
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'WOPARTID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maWoResultPartListForm.strutsAction.value = '<%=MaWoResultPartListAction.WO_RESULT_PART_LIST_DELETE%>';
	var url = contextPath + "/maWoResultPartList.do";
	
	$.post(url,FormQueryString(maWoResultPartListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('maWoResultMonthPartDetail',this);
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

function goSave(){
	var url = contextPath + "/maWoResultBmRplPartList.do";
	
    $.post(url,FormQueryString(maWoResultPartListForm), function(_data){
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
 * 긴급출고자재 선택
 */
function goUseparts()
{
	ptEmgIssAc.openLov();
}

/**
 * 일괄등록
 */
function goRegbatch()
{
	partAc.openLov();
}

/**
 * 출고자재 선택
 */
function goIssselect()
{
	ptIssAc.openLov();
}

function setAcLovValue(rtnArr, code)
{
	if(code == 'maWoResultPartDetailDTO.multiDesc') {
		maWoResultPartListForm.strutsAction.value = '<%=MaWoResultPartListAction.WO_RESULT_ISS_PART_LIST_INPUT%>';
		
		goSaveAll();
	}
	else if(code == 'maWoResultPartDetailDTO.multiPartDesc') {
		maWoResultPartListForm.strutsAction.value = '<%=MaWoResultPartListAction.WO_RESULT_PART_LIST_INPUT%>';
		
		//부품등급-A
		maWoResultPartListForm.elements['maWoResultPartDetailDTO.partGrade'].value = '<%=partGrade%>';
		
		//부서창고로 기본 세팅
	    maWoResultPartListForm.elements['maWoResultPartDetailDTO.wcodeId'].value = loginUser.wcodeId;
		
	  	//사용 개수
	    maWoResultPartListForm.elements['maWoResultPartDetailDTO.useQty'].value = '1';
	  	
	  	goSaveAll();
	}
	else if(code == 'maWoResultPartDetailDTO.multiIssDesc') {
		maWoResultPartListForm.strutsAction.value = '<%=MaWoResultPartListAction.WO_RESULT_ISS_LIST_INPUT%>';
		
		goSaveAll();
	}
}
//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maWoResultPartList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maWoResultMstrCommonDTO.wkOrId"/><!-- Key -->
<html:hidden property="maWoResultPartListDTO.woPartId"/>
<html:hidden property="maWoResultPartDetailDTO.partGrade"/>
<html:hidden property="maWoResultPartDetailDTO.wcodeId"/>
<html:hidden property="maWoResultPartDetailDTO.useQty"/>
<html:hidden property="maWoResultPartDetailDTO.eqAsmbId"/>
<html:hidden property="maWoResultPartDetailDTO.multiKey"/><!-- MultiSelect Key -->
<html:hidden property="maWoResultPartDetailDTO.multiDesc"/><!-- MultiSelect Desc -->
<html:hidden property="maWoResultPartDetailDTO.multiPartKey"/><!-- MultiSelect Part Key -->
<html:hidden property="maWoResultPartDetailDTO.multiPartDesc"/><!-- MultiSelect Part Desc -->
<html:hidden property="maWoResultPartDetailDTO.multiIssKey"/><!-- MultiSelect Iss Key -->
<html:hidden property="maWoResultPartDetailDTO.multiIssDesc"/><!-- MultiSelect Iss Desc -->
    <!-- searchbox 박스 Line -->
    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>
 	
 	<c:set var="filePath" value="enhance/${compName}/work/list/month/maWoResultMonthPartList_${compNo}.jsp" />
	<c:if test="${udf:isExist(filePath)}">
		<c:import charEncoding="UTF-8" url="/enhance/${compName}/work/list/month/maWoResultMonthPartList_${compNo}.jsp"></c:import>
	</c:if>

</html:form> 
</body>
</html>
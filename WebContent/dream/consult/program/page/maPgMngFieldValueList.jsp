<%--===========================================================================
화변별 필드 기본값 목록
author  kim21017
version $Id: maPgMngFieldValueList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.program.page.action.MaPgMngFieldValueListAction" %>
<%@ page import="dream.consult.program.page.action.MaPgMngFieldValueDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 화면별 필드 기본값 -->
<title><bean:message key='TAB.maPgMngFieldValueList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="adminPage">
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
		goTabPage("maPgMngFieldValueDetail");
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
	if (maPgMngFieldValueListForm.elements['maPgMngFieldListDTO.pgFieldId'].value == '') return;

	var form = document.maPgMngFieldValueListForm;	
	form.strutsAction.value = '<%=MaPgMngFieldValueListAction.FIND_LIST %>';
	
	var url = contextPath + "/maPgMngFieldValueList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPgMngFieldValueListForm), "PGFIELDVALUEID");

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
	maPgMngFieldValueListForm.elements['maPgMngFieldValueListDTO.pgFieldValueId'].value 
		= getValueById(myGrid, selectedId,'PGFIELDVALUEID');
	
	goCommonTabPage(maPgMngFieldValueListForm, <%= MaPgMngFieldValueDetailAction.INIT_DETAIL %>, "maPgMngFieldValueDetail");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pgFieldValueId)
{
	maPgMngFieldValueListForm.elements['maPgMngFieldValueListDTO.pgFieldValueId'].value = _pgFieldValueId;
	findGridList('ReloadRow');
	maPgMngFieldValueListForm.elements['maPgMngFieldValueListDTO.pgFieldValueId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maPgMngFieldValueDetail');
}

 /**
  * 생성
  */
function goCreate()
{
 	createValidationCheck(myGrid, "maPgMngFieldValueDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
 	maPgMngFieldValueListForm.elements['maPgMngFieldValueListDTO.pgFieldValueId'].value = "";
 	goCommonTabPage(maPgMngFieldValueListForm, '', pageId);
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
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'PGFIELDVALUEID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maPgMngFieldValueListForm.strutsAction.value = '<%=MaPgMngFieldValueListAction.DELETE_LIST%>';
	var url = contextPath + "/maPgMngFieldValueList.do";
	
	$.post(url,FormQueryString(maPgMngFieldValueListForm)+delArray , function(_data){
    	afterDelete();
    });
}
  
function afterDelete()
{
	goClose('maPgMngFieldValueDetail', this);
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPgMngFieldValueList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPgMngCommonDTO.pageId"/><!-- Key -->
<html:hidden property="maPgMngFieldListDTO.pgFieldId"/>
<html:hidden property="maPgMngFieldDetailDTO.fieldId"/>
<html:hidden property="maPgMngFieldDetailDTO.pgFieldId"/>
<html:hidden property="maPgMngFieldDetailDTO.formInputTypeId"/>
<html:hidden property="maPgMngFieldDetailDTO.formInputDetailTypeId"/>
<html:hidden property="maPgMngFieldDetailDTO.codeListTypeId"/>
<html:hidden property="maPgMngFieldValueListDTO.pgFieldValueId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
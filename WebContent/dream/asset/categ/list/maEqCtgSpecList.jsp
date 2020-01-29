<%--===========================================================================
설비종류별 표준제원
author  syyang
version $Id: maEqCtgSpecList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.categ.list.action.MaEqCtgSpecListAction" %>
<%@ page import="dream.asset.categ.list.action.MaEqCtgSpecDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 설비종류별 표준제원 -->
<title><bean:message key='TAB.maEqCtgSpecList'/></title>
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

	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	maEqCtgSpecListForm.elements['maEqCtgSpecListDTO.eqCtgSpecId'].value = "";
        return sortColumn("maEqCtgSpecList", this, maEqCtgSpecListForm, "EQCTGID", ind, direction);
    });

	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("maEqCtgSpecDetail");
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
	if (maEqCtgSpecListForm.elements['maEqCatalogCommonDTO.eqCtgId'].value == '') return;
	
	var form = document.maEqCtgSpecListForm;	
	form.strutsAction.value = '<%=MaEqCtgSpecListAction.EQ_CTG_SPEC_LIST_FIND %>';
	
	var url = contextPath + "/maEqCtgSpecList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maEqCtgSpecListForm), "EQCTGID","Y");
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
	var form = document.maEqCtgSpecListForm;
	 
	form.elements['maEqCtgSpecListDTO.eqCtgSpecId'].value = getValueById(myGrid, selectedId,'EQCTGSPECID');
	goCommonTabPage(form, <%= MaEqCtgSpecDetailAction.EQ_CTG_SPEC_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_eqCtgId)
{
	maEqCtgSpecListForm.elements['maEqCatalogCommonDTO.eqCtgId'].value = _eqCtgId;
	findGridList('ReloadRow');
    maEqCtgSpecListForm.elements['maEqCatalogCommonDTO.eqCtgId'].value = "";
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('maEqCtgSpecDetail');
}

 function goOpenAction()
 {
     var selectedId=myGrid.getSelectedRowId();
     if(selectedId == null) return;
     
     maEqCtgSpecListForm.elements['maEqCtgSpecListDTO.eqCtgSpecId'].value = getValueById(myGrid, selectedId,'EQCTGSPECID');
     maEqCtgSpecListForm.elements['strutsAction'].value = '<%=MaEqCtgSpecDetailAction.EQ_CTG_SPEC_DETAIL_INIT%>';
     openQuickTabPage(FormQueryString(maEqCtgSpecListForm), 'maEqCtgSpecDetail'); 
 } 

 
 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	 maEqCtgSpecListForm.elements['maEqCtgSpecListDTO.eqCtgSpecId'].value = "";
	 excelServerAction("maEqCtgSpecList", maEqCtgSpecListForm);
  }
 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "maEqCtgSpecDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	maEqCtgSpecListForm.elements['maEqCtgSpecListDTO.eqCtgSpecId'].value = "";
	goCommonTabPage(maEqCtgSpecListForm, '', pageId);
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'EQCTGSPECID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maEqCtgSpecListForm.strutsAction.value = '<%=MaEqCtgSpecListAction.EQ_CTG_SPEC_LIST_DELETE%>';
	var url = contextPath + "/maEqCtgSpecList.do";
	
	$.post(url,FormQueryString(maEqCtgSpecListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('maEqCtgSpecDetail',this);
   	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maEqCtgSpecList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maEqCatalogCommonDTO.eqCtgId"/><!-- Key -->
<html:hidden property="maEqCtgSpecListDTO.eqCtgSpecId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
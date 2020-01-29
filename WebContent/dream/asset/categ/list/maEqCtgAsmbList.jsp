<%--===========================================================================
설비종류별 부위
author  kim21017
version $Id: maEqCtgAsmbList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.categ.list.action.MaEqCtgAsmbListAction" %>
<%@ page import="dream.asset.categ.list.action.MaEqCtgAsmbDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 설비종류별 부위 -->
<title><bean:message key='TAB.maEqCtgAsmbList'/></title>
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
var selectedInd;

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    myGrid.setImageSize(1,1);
	myGrid.enableTreeGridLines();
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
	
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	maEqCtgAsmbListForm.elements['maEqCtgAsmbListDTO.eqCtgAsmbId'].value = "";
        return sortColumn("maEqCtgAsmbList", this, maEqCtgAsmbListForm, "EQCTGASMBID", ind, direction);
    });
	
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		//goTabPage("maEqCtgAsmbDetail");
		selectedId = rowId;
		selectedInd = columnId;
		goOpen();
	});
	
    
	//myGrid.enableSmartRendering(true,500);
	myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
    //findGridList('Search');   
	findGridList('SearchTree');
}

function findGridList(sheetAction)
{
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (maEqCtgAsmbListForm.elements['maEqCatalogCommonDTO.eqCtgId'].value == '') return;
	var form = document.maEqCtgAsmbListForm;	
	form.strutsAction.value = '<%=MaEqCtgAsmbListAction.EQ_CTG_ASMB_LIST_FIND %>';
	
	var url = contextPath + "/maEqCtgAsmbList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maEqCtgAsmbListForm), "EQCTGASMBID","" , "PEQCTGASMBID");

// 	myGrid.clearAll(); setLoading("gridbox");
//     $.post(url,FormQueryString(form), function(_data){
//     	myGrid.parse(_data,"js");
//     });
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
	var form = document.maEqCtgAsmbListForm;
	 
	form.elements['maEqCtgAsmbListDTO.eqCtgAsmbId'].value = getValueById(myGrid, selectedId,'EQCTGASMBID');
	form.elements['maEqCtgAsmbListDTO.detailPasmbId'].value = getValueById(myGrid, selectedId,'EQCTGASMBID');
	form.elements['maEqCtgAsmbListDTO.detailPasmbDesc'].value = getValueById(myGrid, selectedId,'EQCTGASMBDESC');
	goCommonTabPage(form, <%= MaEqCtgAsmbDetailAction.EQ_CTG_ASMB_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_eqCtgAsmbId)
{
	
	maEqCtgAsmbListForm.elements['maEqCtgAsmbListDTO.eqCtgAsmbId'].value = _eqCtgAsmbId;
	
	//findGridList('ReloadRow');
    findGridList('ReloadTreeRow');

    maEqCtgAsmbListForm.elements['maEqCtgAsmbListDTO.eqCtgAsmbId'].value = "";
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('maEqCtgAsmbDetail');
}

 function goOpenAction()
 {
     var selectedId=myGrid.getSelectedRowId();
     if(selectedId == null) return;
     
     maEqCtgAsmbListForm.elements['maEqCtgAsmbListDTO.eqCtgAsmbId'].value = getValueById(myGrid, selectedId,'EQCTGASMBID');
     maEqCtgAsmbListForm.elements['strutsAction'].value = '<%=MaEqCtgAsmbDetailAction.EQ_CTG_ASMB_DETAIL_INIT%>';
     openQuickTabPage(FormQueryString(maEqCtgAsmbListForm), 'maEqCtgAsmbDetail'); 
 } 

 
 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
  	excelServerAction("maEqCtgAsmbList", maEqCtgAsmbListForm);
  }
 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "maEqCtgAsmbDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	maEqCtgAsmbListForm.elements['maEqCtgAsmbListDTO.eqCtgAsmbId'].value = "";
	goCommonTabPage(maEqCtgAsmbListForm, '', pageId);
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'EQCTGASMBID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maEqCtgAsmbListForm.strutsAction.value = '<%=MaEqCtgAsmbListAction.EQ_CTG_ASMB_LIST_DELETE%>';
	var url = contextPath + "/maEqCtgAsmbList.do";
	
	$.post(url,FormQueryString(maEqCtgAsmbListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('maEqCtgAsmbDetail',this);
   	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maEqCtgAsmbList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maEqCatalogCommonDTO.eqCtgId"/><!-- Key -->
<html:hidden property="maEqCtgAsmbListDTO.eqCtgAsmbId"/>
<html:hidden property="maEqCtgAsmbListDTO.detailPasmbId"/>
<html:hidden property="maEqCtgAsmbListDTO.detailPasmbDesc"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
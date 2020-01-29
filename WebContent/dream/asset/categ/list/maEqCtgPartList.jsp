<%--===========================================================================
설비종류별 부품
author  kim21017
version $Id: maEqCtgPartList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.categ.list.action.MaEqCtgPartListAction" %>
<%@ page import="dream.asset.categ.list.action.MaEqCtgPartDetailAction" %>
<%@ page import="dream.asset.categ.list.action.MaEqCatalogDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 설비종류별 부품 -->
<title><bean:message key='TAB.maEqCtgPartList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var isNew = false;
var partAc;
function loadPage() 
{
    setForUpdate();
	
	initGrid();
	
	partAc = new autoC({"maEqCtgPartDetailDTO.multiDesc":"full_desc"});
	partAc.setTable("TAPARTS");
	partAc.setAcResultMap({
        "maEqCtgPartDetailDTO.multiKey":"part_id"
    });
	partAc.setAcConditionMap({
		"comp_no":loginUser.compNo
      , "is_use" : "Y"
	});
	partAc.setMultiSelect(true);
	partAc.init();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	maEqCtgPartListForm.elements['maEqCtgPartListDTO.eqCtgPartId'].value = "";
        return sortColumn("maEqCtgPartList", this, maEqCtgPartListForm, "EQCTGPARTID", ind, direction);
    });

	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("maEqCtgPartDetail");
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_eqCtgPartId)
{
	maEqCtgPartListForm.elements['maEqCtgPartListDTO.eqCtgPartId'].value = _eqCtgPartId
    findGridList('ReloadRow');
    maEqCtgPartListForm.elements['maEqCtgPartListDTO.eqCtgPartId'].value = "";
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
    <%-- findGridList('Search', '<%=MaEqCtgPartListAction.EQ_CTG_PART_LIST_FIND %>'); --%>   
	//findGridList('SearchTree');
	findGridList('Search');
}

function findGridList(sheetAction)
{
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (maEqCtgPartListForm.elements['maEqCatalogCommonDTO.eqCtgId'].value == '') return;
	
	var form = document.maEqCtgPartListForm;	
	form.strutsAction.value = '<%=MaEqCtgPartListAction.EQ_CTG_PART_LIST_FIND %>';
	
	var url = contextPath + "/maEqCtgPartList.do";
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maEqCtgPartListForm), "EQCTGPARTID","Y");

	/* myGrid.clearAll(); setLoading("gridbox");
    $.post(url,FormQueryString(form), function(_data){
    	myGrid.parse(_data,"js");
    }); */
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
	var form = document.maEqCtgPartListForm;
	 
	if(!isNew)
	{
		form.elements['maEqCtgPartListDTO.eqCtgPartId'].value = getValueById(myGrid, selectedId,'EQCTGPARTID');
	}
	
	goCommonTabPage(form, <%=MaEqCtgPartDetailAction.EQ_CTG_PART_DETAIL_INIT %>, pageId);
}


/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('maEqCtgPartDetail');
}

 function goOpenAction()
 {
     var selectedId=myGrid.getSelectedRowId();
     if(selectedId == null) return;
     
     var form = document.maEqCtgPartListForm;
	 
     if(!isNew)
 	{
 		form.elements['maEqCtgPartListDTO.eqCtgPartId'].value = getValueById(myGrid, selectedId,'EQCTGPARTID');
 	}
 	form.elements['strutsAction'].value = '<%=MaEqCtgPartDetailAction.EQ_CTG_PART_DETAIL_INIT%>';
     openQuickTabPage(FormQueryString(form), 'maEqCtgPartDetail'); 
 } 

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	 maEqCtgPartListForm.elements['maEqCtgPartListDTO.eqCtgPartId'].value = "";
	 excelServerAction("maEqCtgPartList", maEqCtgPartListForm);
  }
 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "maEqCtgPartDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	maEqCtgPartListForm.elements['maEqCtgPartListDTO.eqCtgPartId'].value = "";
	goCommonTabPage(maEqCtgPartListForm, '', pageId);
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'EQCTGPARTID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maEqCtgPartListForm.strutsAction.value = '<%=MaEqCtgPartListAction.EQ_CTG_PART_LIST_DELETE%>';
	var url = contextPath + "/maEqCtgPartList.do";
	
	$.post(url,FormQueryString(maEqCtgPartListForm)+delArray , function(_data){
		afterDelete();
	});
}
 
function afterDelete(){
	goClose('maEqCtgPartDetail',this);
   	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

function goSave(){
	var url = contextPath + "/maEqCtgPartList.do";
	
    $.post(url,FormQueryString(maEqCtgPartListForm), function(_data){
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
 * 일괄등록
 */
function goRegbatch()
{
	partAc.openLov();
}

function setAcLovValue(rtnArr, code)
{
	if(code == 'maEqCtgPartDetailDTO.multiDesc')
	{
		maEqCtgPartListForm.strutsAction.value = '<%=MaEqCtgPartListAction.EQ_CTG_PART_LIST_INPUT%>';
		
		maEqCtgPartListForm.elements['maEqCtgPartDetailDTO.useQty'].value = '1'
		maEqCtgPartListForm.elements['maEqCtgPartDetailDTO.isUse'].value = 'Y';
		maEqCtgPartListForm.elements['maEqCtgPartDetailDTO.isEqTypePart'].value = 'N';
		
		goSaveAll();
	}
}

function setKeyAftercopy(_newId)
{
	findGridRow(_newId);
	
	//상세 창 열기
	isNew = true;
	maEqCtgPartListForm.elements['maEqCtgPartListDTO.eqCtgPartId'].value = _newId;
	//goTabPage("maEqCtgPartDetail");
	goOpen();
	isNew = false;
}


//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maEqCtgPartList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maEqCatalogCommonDTO.eqCtgId"/><!-- Key -->
<html:hidden property="maEqCtgPartListDTO.eqCtgPartId"/>
<html:hidden property="maEqCtgPartDetailDTO.multiKey"/><!-- MultiSelect Key -->
<html:hidden property="maEqCtgPartDetailDTO.multiDesc"/><!-- MultiSelect Desc -->
<html:hidden property="maEqCtgPartDetailDTO.useQty"/>
<html:hidden property="maEqCtgPartDetailDTO.isUse"/>
<html:hidden property="maEqCtgPartDetailDTO.isEqTypePart"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
<%--===========================================================================
설비 정기점검 목록
author  kim21017
version $Id: maEqMstrPmInsPointList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.list.action.MaEqMstrPmInsPointListAction" %>
<%@ page import="dream.work.pm.list.action.MaPmMstrPointDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 점검항목 -->
<title><bean:message key='TAB.maEqMstrPmInsPointList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var beforePageId = '';
var detailPageAc;

function loadPage() 
{
	initGrid();

	detailPageAc = new autoC({"maPmMstrCommonDTO.pointDetailPageId":"param1"});
	detailPageAc.setTable("TACDSYSD");
	detailPageAc.setAcConditionMap({
        "list_type" : "CHECK_TYPE"
       ,"is_use" : "Y"
    });
	detailPageAc.setAcResultMap({
        "maPmMstrCommonDTO.pointDetailCheckTypeId":"cdsysd_no"
    });
	detailPageAc.init();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen(rowId);
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		maEqMstrPmInsPointListForm.elements['maEqMstrPmInsPointListDTO.pmId'].value = "";
		maEqMstrPmInsPointListForm.elements['maEqMstrPmInsPointListDTO.pmPointId'].value = "";
    	return sortColumn("maEqMstrPmInsPointList", this, maEqMstrListForm, "PMPOINTID", ind, direction);
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
	if (maEqMstrPmInsPointListForm.elements['maEqMstrPmInsDetailDTO.pmId'].value == '') return;
	var form = document.maEqMstrPmInsPointListForm;	
	form.strutsAction.value = '<%=MaEqMstrPmInsPointListAction.EQ_MSTR_PMINS_POINT_LIST_FIND %>';
	
	var url = contextPath + "/maEqMstrPmInsPointList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maEqMstrPmInsPointListForm), "PMPOINTID", "Y");
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
	var form = document.maEqMstrPmInsPointListForm;
	
	form.elements['maEqMstrPmInsPointListDTO.pmId'].value = getValueById(myGrid, selectedId,'PMID');
	form.elements['maEqMstrPmInsPointListDTO.pmPointId'].value = getValueById(myGrid, selectedId,'PMPOINTID');
	form.elements['maPmMstrCommonDTO.pmId'].value = getValueById(myGrid, selectedId,'PMID');
	form.elements['maPmMstrCommonDTO.pmPointId'].value = getValueById(myGrid, selectedId,'PMPOINTID');
	goCommonTabPage(form, <%= MaPmMstrPointDetailAction.PM_MSTR_POINT_DETAIL_INIT%>, pageId, beforePageId);
	
	beforePageId = pageId;
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pmPointId)
{
	maEqMstrPmInsPointListForm.elements['maEqMstrPmInsPointListDTO.pmPointId'].value = _pmPointId;
	
	findGridList('ReloadRow');
	
	maEqMstrPmInsPointListForm.elements['maEqMstrPmInsPointListDTO.pmPointId'].value = "";
}

/**
 * 상세열기
 */
 function goOpen(rowId){
	var detailPage  = getValueById(myGrid, rowId,'DETAILPAGE');
	var detailCheckTypeId = getValueById(myGrid, rowId,'CHECKTYPEID');

	maEqMstrPmInsPointListForm.elements['maPmMstrCommonDTO.pointDetailPageId'].value = detailPage;
	maEqMstrPmInsPointListForm.elements['maPmMstrCommonDTO.pointDetailCheckTypeId'].value = detailCheckTypeId;
	
	goTabPage(detailPage);
}

 function goOpenAction()
 {
     var selectedId=myGrid.getSelectedRowId();
     if(selectedId == null) return;

     var detailPage  = getValueById(myGrid, selectedId,'DETAILPAGE');
     
     maEqMstrPmInsPointListForm.elements['maPmMstrCommonDTO.pmId'].value =  getValueById(myGrid, selectedId,'PMID');
 	 maEqMstrPmInsPointListForm.elements['maPmMstrCommonDTO.pmPointId'].value = getValueById(myGrid, selectedId,'PMPOINTID');

     maEqMstrPmInsPointListForm.elements['strutsAction'].value = '<%=MaPmMstrPointDetailAction.PM_MSTR_POINT_DETAIL_INIT %>';
     openQuickTabPage(FormQueryString(maEqMstrPmInsPointListForm), detailPage); 
 } 

 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
//   	excelAction(myGrid);
	maEqMstrPmInsPointListForm.elements['maEqMstrPmInsPointListDTO.pmId'].value = "";
	maEqMstrPmInsPointListForm.elements['maEqMstrPmInsPointListDTO.pmPointId'].value = "";
	excelServerAction("maEqMstrPmInsPointList", maEqMstrPmInsPointListForm);
  }
 
  /**
   * 생성
   */
 function goCreate()
 {
	 goClose(beforePageId,this);
	//점검타입 LOV 
	detailPageAc.openLov();
	maEqMstrPmInsPointListForm.elements['maEqMstrPmInsPointListDTO.pmPointId'].value = "";
 }

 function afterAutoCmpt(code)
 {
 	if(code=="maPmMstrCommonDTO.pointDetailPageId")
 	{
 		setAfterSelect(maEqMstrPmInsPointListForm.elements['maPmMstrCommonDTO.pointDetailPageId'].value);
 	}
 }

 function setAfterSelect(detailPageId)
 {
 	 var detailPage = detailPageId;
 	 
 	 beforePageId = detailPage;
 	 goCommonTabPage(maEqMstrPmInsPointListForm, '', detailPage);
}

 function goCreateAction(pageId)
 {
// 	maEqMstrPmInsPointListForm.elements['maEqMstrPmInsPointListDTO.pmId'].value = "";
// 	maEqMstrPmInsPointListForm.elements['maEqMstrPmInsPointListDTO.pmPointId'].value = "";
// 	goCommonTabPage(maEqMstrPmInsPointListForm, '', pageId);
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'PMID','PMPOINTID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined") return;if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maEqMstrPmInsPointListForm.strutsAction.value = '<%=MaEqMstrPmInsPointListAction.EQ_MSTR_PMINS_POINT_LIST_DELETE%>';
	var url = contextPath + "/maEqMstrPmInsPointList.do";
	
	$.post(url,FormQueryString(maEqMstrPmInsPointListForm)+delArray , function(_data){
		afterDelete();
	}); 
  }
 
function afterDelete(){
	goClose(beforePageId,this);
   	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maEqMstrPmInsPointList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maEqMstrCommonDTO.equipId"/>
<html:hidden property="maEqMstrPmInsDetailDTO.pmId"/>
<html:hidden property="maEqMstrPmInsPointListDTO.pmId"/>
<html:hidden property="maEqMstrPmInsPointListDTO.pmPointId"/>

<html:hidden property="maPmMstrCommonDTO.pointDetailPageId"/>
<html:hidden property="maPmMstrCommonDTO.pointDetailCheckTypeId"/>
<html:hidden property="maPmMstrCommonDTO.pmId"/>
<html:hidden property="maPmMstrCommonDTO.pmPointId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
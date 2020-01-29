<%--===========================================================================
작업결과(고장작업-교체(부품있음)) Serial
author  kim21017
version $Id: workListBmRplPartSerialList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.list.action.WorkListBmRplPartSerialListAction" %>
<%@ page import="dream.work.list.action.WorkListBmRplPartSerialDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- Serial# -->
<title><bean:message key='TAB.workListBmRplPartSerialList'/></title>
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
		goTabPage("workListBmRplPartSerialDetail");
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	workListBmRplPartSerialListForm.elements['workListBmRplPartSerialListDTO.wopartSerialId'].value = "";
        return sortColumn("workListBmRplPartSerialList", this, workListBmRplPartSerialListForm, "WOPARTSSERIALID", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	workListBmRplPartSerialListForm.elements['maWoResultPartDetailDTO.partId'].value = $(parent.document).find('[name="maWoResultPartDetailDTO.partId"]').val();
	
    findGridList('Search'); 

 	goClose('workListBmRplPartSerialDetail',this);
}

function findGridList(sheetAction)
{
	//id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
	if (workListBmRplPartSerialListForm.elements['maWoResultPartDetailDTO.partId'].value == '') return;
	
	var form = document.workListBmRplPartSerialListForm;	
	form.strutsAction.value = '<%=WorkListBmRplPartSerialListAction.WO_RESULT_PTSERIAL_LIST_FIND %>';
	
	var url = contextPath + "/workListBmRplPartSerialList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workListBmRplPartSerialListForm), "wopartsSerialId", "Y");
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
	var form = document.workListBmRplPartSerialListForm;
	 
	form.elements['workListBmRplPartSerialListDTO.wopartSerialId'].value = getValueById(myGrid, selectedId,'wopartsSerialId');
	goCommonTabPage(form, <%= WorkListBmRplPartSerialDetailAction.WO_RESULT_PTSERIAL_DETAIL_INIT %>, pageId);
	
	form.elements['workListBmRplPartSerialListDTO.wopartSerialId'].value = "";
}


/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('workListBmRplPartSerialDetail');
}
 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	workListBmRplPartSerialListForm.elements['workListBmRplPartSerialListDTO.wopartSerialId'].value = "";
  	excelServerAction("workListBmRplPartSerialList", workListBmRplPartSerialListForm);
  } 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "workListBmRplPartSerialDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	workListBmRplPartSerialListForm.elements['workListBmRplPartSerialListDTO.wopartSerialId'].value = "";
	goCommonTabPage(workListBmRplPartSerialListForm, '', pageId);
 }
 
 /** 
  * 수정된 그리드 1건을 다시 조회한다.
  */
 function findGridRow(_wopartSerialId)
 {
	workListBmRplPartSerialListForm.elements['workListBmRplPartSerialListDTO.wopartSerialId'].value = _wopartSerialId;
 	findGridList('ReloadRow');
 	workListBmRplPartSerialListForm.elements['workListBmRplPartSerialListDTO.wopartSerialId'].value = "";
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'wopartsSerialId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	workListBmRplPartSerialListForm.strutsAction.value = '<%=WorkListBmRplPartSerialListAction.WO_RESULT_PTSERIAL_LIST_DELETE%>';
	var url = contextPath + "/workListBmRplPartSerialList.do";
	
	$.post(url,FormQueryString(workListBmRplPartSerialListForm)+delArray , function(_data){
		afterDelete();
	});
}
 
function afterDelete(){
	goClose('workListBmRplPartSerialDetail',this);
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workListBmRplPartSerialList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maWoResultMstrCommonDTO.wkOrId"/><!-- Key -->
<html:hidden property="maWoResultPartDetailDTO.partId"/><!-- Key -->
<html:hidden property="maWoResultPartDetailDTO.woPartId"/>

<html:hidden property="workListBmRplPartSerialListDTO.wopartSerialId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
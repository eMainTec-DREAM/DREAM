<%--===========================================================================
표준기목록
author  kim21017
version $Id: workListGnlCaEqList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.list.action.WorkListGnlCaEqListAction" %>
<%@ page import="dream.work.list.action.WorkListGnlCaEqDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 표준기 -->
<title><bean:message key='LABEL.calibEq'/></title>
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
		goTabPage("workListGnlCaEqDetail");
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	workListGnlCaEqListForm.elements['maWoResultMstrCommonDTO.wocalibstdeqId'].value = "";
        return sortColumn("workListGnlCaEqList", this, workListGnlCaEqListForm, "wocalibstdeqId", ind, direction);
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
	if (workListGnlCaEqListForm.elements['maWoResultMstrCommonDTO.wkOrId'].value == '') return;
	
	var form = document.workListGnlCaEqListForm;	
	form.strutsAction.value = '<%=WorkListGnlCaEqListAction.WORK_LIST_GNLCAEQ_LIST_FIND %>';
	
	var url = contextPath + "/workListGnlCaEqList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workListGnlCaEqListForm), "wocalibstdeqId", "Y");

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
	var form = document.workListGnlCaEqListForm;
	 
	form.elements['maWoResultMstrCommonDTO.wocalibstdeqId'].value = getValueById(myGrid, selectedId,'wocalibstdeqId');
	goCommonTabPage(form, <%= WorkListGnlCaEqDetailAction.WORK_LIST_GNLCAEQ_DETAIL_INIT %>, pageId);
}


/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('workListGnlCaEqDetail');
}
 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	 workListGnlCaEqListForm.elements['maWoResultMstrCommonDTO.wocalibstdeqId'].value = "";
	 excelServerAction("workListGnlCaEqList", workListGnlCaEqListForm);
  } 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "workListGnlCaEqDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	workListGnlCaEqListForm.elements['maWoResultMstrCommonDTO.wocalibstdeqId'].value = "";
	goCommonTabPage(workListGnlCaEqListForm, '', pageId);
 }
 
 /** 
  * 수정된 그리드 1건을 다시 조회한다.
  */
 function findGridRow(_woCalibValueId)
 {
	workListGnlCaEqListForm.elements['maWoResultMstrCommonDTO.wocalibstdeqId'].value = _woCalibValueId;
 	findGridList('ReloadRow');
 	workListGnlCaEqListForm.elements['maWoResultMstrCommonDTO.wocalibstdeqId'].value = "";
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'wocalibstdeqId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	workListGnlCaEqListForm.strutsAction.value = '<%=WorkListGnlCaEqListAction.WORK_LIST_GNLCAEQ_LIST_DELETE%>';
	var url = contextPath + "/workListGnlCaEqList.do";
	
	$.post(url,FormQueryString(workListGnlCaEqListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('workListGnlCaEqDetail',this);
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = workListGnlCaEqListForm.elements['maWoResultMstrCommonDTO.wocalibstdeqId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workListGnlCaEqList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maWoResultMstrCommonDTO.wkOrId"/><!-- Key -->
<html:hidden property="maWoResultMstrCommonDTO.wocalibstdeqId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
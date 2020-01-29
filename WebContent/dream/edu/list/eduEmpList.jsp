<%--===========================================================================
자격증 사원
author  kim21017
version $Id: eduEmpList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.edu.list.action.EduEmpListAction" %>
<%@ page import="dream.edu.list.action.EduEmpDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 사원 -->
<title><bean:message key='TAB.eduEmpList'/></title>
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
		goTabPage("eduEmpDetail");
	});

    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
     	eduEmpListForm.elements['eduEmpListDTO.empTrainListId'].value = "";
    	return sortColumn("eduEmpList", this, eduEmpListForm, "EMPTRAINLISTID", ind, direction);
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
	if (eduEmpListForm.elements['eduCommonDTO.courseListId'].value == '') return;
	
	var form = document.eduEmpListForm;	
	form.strutsAction.value = '<%=EduEmpListAction.EDU_EMP_LIST_FIND %>';
	
	var url = contextPath + "/eduEmpList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(eduEmpListForm), "EMPTRAINLISTID", "Y");

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
	var form = document.eduEmpListForm;
	 
	form.elements['eduEmpListDTO.empTrainListId'].value = getValueById(myGrid, selectedId,'EMPTRAINLISTID');
	
	goCommonTabPage(form, <%= EduEmpDetailAction.EDU_EMP_DETAIL_INIT %>, pageId);
}

function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
 	if(selectedId == null) return;

 	var form = document.eduEmpListForm;
	 
	form.elements['eduEmpListDTO.empTrainListId'].value = getValueById(myGrid, selectedId,'EMPTRAINLISTID');
 	form.elements['strutsAction'].value = '<%=EduEmpDetailAction.EDU_EMP_DETAIL_INIT%>';
 	openQuickTabPage(FormQueryString(form), 'eduEmpDetail'); 
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('eduEmpDetail');
}
 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
	//excelAction(myGrid);
	eduEmpListForm.elements['eduEmpListDTO.empTrainListId'].value = "";
	excelServerAction("eduEmpList", eduEmpListForm ); 
  } 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "eduEmpDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	eduEmpListForm.elements['eduEmpListDTO.empTrainListId'].value = "";
	goCommonTabPage(eduEmpListForm, '', pageId);
 }
 
 /** 
  * 수정된 그리드 1건을 다시 조회한다.
  */
 function findGridRow(_empTrainListId)
 {
	eduEmpListForm.elements['eduEmpListDTO.empTrainListId'].value = _empTrainListId;
 	findGridList('ReloadRow');
 	eduEmpListForm.elements['eduEmpListDTO.empTrainListId'].value = "";
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'EMPTRAINLISTID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	eduEmpListForm.strutsAction.value = '<%=EduEmpListAction.EDU_EMP_LIST_DELETE%>';
	var url = contextPath + "/eduEmpList.do";
	
	$.post(url,FormQueryString(eduEmpListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('eduEmpDetail',this);
	//goSearch();
  	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
  }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/eduEmpList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="eduCommonDTO.courseListId"/><!-- Key -->
<html:hidden property="eduEmpListDTO.empTrainListId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
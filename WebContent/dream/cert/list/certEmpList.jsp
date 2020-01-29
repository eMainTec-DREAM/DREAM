<%--===========================================================================
자격증 사원
author  kim21017
version $Id: certEmpList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.cert.list.action.CertEmpListAction" %>
<%@ page import="dream.cert.list.action.CertEmpDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 사원 -->
<title><bean:message key='TAB.certEmpList'/></title>
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
		goTabPage("certEmpDetail");
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		certEmpListForm.elements['certEmpListDTO.empCertListId'].value = "";
    	return sortColumn("certEmpList", this, certEmpListForm, "EMPCERTLISTID", ind, direction);
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
	if (certEmpListForm.elements['certCommonDTO.certListId'].value == '') return;
	
	var form = document.certEmpListForm;	
	form.strutsAction.value = '<%=CertEmpListAction.CERT_EMP_LIST_FIND %>';
	
	var url = contextPath + "/certEmpList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(certEmpListForm), "EMPCERTLISTID", "Y");

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
	var form = document.certEmpListForm;
	 
	form.elements['certEmpListDTO.empCertListId'].value = getValueById(myGrid, selectedId,'EMPCERTLISTID');
	
	goCommonTabPage(form, <%= CertEmpDetailAction.CERT_EMP_DETAIL_INIT %>, pageId);
}

function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
 	if(selectedId == null) return;

 	var form = document.certEmpListForm;
	 
	form.elements['certEmpListDTO.empCertListId'].value = getValueById(myGrid, selectedId,'EMPCERTLISTID');
	form.elements['strutsAction'].value = '<%=CertEmpDetailAction.CERT_EMP_DETAIL_INIT%>';
 	openQuickTabPage(FormQueryString(certEmpListForm), 'certEmpDetail'); 
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('certEmpDetail');
}
 /**
  * 엑셀 다운.
  */
  function goExcel()
  {
  	excelAction(myGrid);
  } 
  /**
   * 생성
   */
 function goCreate()
 {
  	createValidationCheck(myGrid, "certEmpDetail" , "goCreateAction");
 }

 function goCreateAction(pageId)
 {
	certEmpListForm.elements['certEmpListDTO.empCertListId'].value = "";
	goCommonTabPage(certEmpListForm, '', pageId);
 }
 
 /** 
  * 수정된 그리드 1건을 다시 조회한다.
  */
 function findGridRow(_woCraftId)
 {
	certEmpListForm.elements['certEmpListDTO.empCertListId'].value = _woCraftId;
 	findGridList('ReloadRow');
 	certEmpListForm.elements['certEmpListDTO.empCertListId'].value = "";
 }
 
 /**
  * 삭제
  */
function goDelete(){
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'EMPCERTLISTID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	certEmpListForm.strutsAction.value = '<%=CertEmpListAction.CERT_EMP_LIST_DELETE%>';
	var url = contextPath + "/certEmpList.do";
	
	$.post(url,FormQueryString(certEmpListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete(){
	goClose('certEmpDetail',this);
	//goSearch();
  	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
  }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/certEmpList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="certCommonDTO.certListId"/><!-- Key -->
<html:hidden property="certEmpListDTO.empCertListId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
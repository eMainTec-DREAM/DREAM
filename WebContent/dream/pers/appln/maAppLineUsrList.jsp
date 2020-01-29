<%--===========================================================================
목록
author  kim21017
version $Id: maAppLineUsrList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="common.tree.action.TreeAction" %>
<%@ page import="dream.pers.appln.action.MaAppLineUsrListAction" %>
<%@ page import="dream.pers.appln.action.MaAppLineUsrDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<title><bean:message key='LABEL.questionPoint'/></title>
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
		goTabPage("maAppLineUsrDetail");
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	maAppLineUsrListForm.elements['maAppLineUsrListDTO.apprlineusrId'].value = "";
    	return sortColumn("maAppLineUsrList", this, maAppLineUsrListForm, "apprlineusrId", ind, direction);
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

/**
 * gird find
 */
function findGridList(sheetAction)
{
	if (maAppLineUsrListForm.elements['maAppLineCommonDTO.apprlineId'].value == '') return;
	
	var form = document.maAppLineUsrListForm;	
	form.strutsAction.value = '<%=MaAppLineUsrListAction.QNA_ANS_LIST_FIND%>'; 

	var url = contextPath + "/maAppLineUsrList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maAppLineUsrListForm), "apprlineusrId", "Y");

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
	var form = document.maAppLineUsrListForm;
	form.elements['maAppLineUsrListDTO.apprlineusrId'].value = getValueById(myGrid, selectedId,'apprlineusrId');
    
	goCommonTabPage(form, <%=MaAppLineUsrDetailAction.QNA_ANS_DETAIL_INIT %>, "maAppLineUsrDetail");
	
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_answerId)
{
	maAppLineUsrListForm.elements['maAppLineUsrListDTO.apprlineusrId'].value = _answerId;
	findGridList('ReloadRow');
	maAppLineUsrListForm.elements['maAppLineUsrListDTO.apprlineusrId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maAppLineUsrDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maAppLineUsrListForm.elements['maAppLineUsrListDTO.apprlineusrId'].value =  getValueById(myGrid, selectedId,'apprlineusrId');  
    maAppLineUsrListForm.elements['strutsAction'].value = '<%=MaAppLineUsrDetailAction.QNA_ANS_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maAppLineUsrListForm), 'maAppLineUsrDetail'); 
} 


/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maAppLineUsrDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maAppLineUsrListForm.elements['maAppLineUsrListDTO.apprlineusrId'].value = "";
	goCommonTabPage(maAppLineUsrListForm, '', pageId);
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
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'apprlineusrId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maAppLineUsrListForm.strutsAction.value = '<%=MaAppLineUsrListAction.QNA_ANS_LIST_DELETE%>';
	var url = contextPath + "/maAppLineUsrList.do";
	
    $.post(url,FormQueryString(maAppLineUsrListForm)+delArray , function(_data){
    	afterDelete();
    });
 }

function afterDelete(){
	goClose('maAppLineUsrDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maAppLineUsrListForm.elements['maAppLineUsrListDTO.apprlineusrId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maAppLineUsrList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maAppLineCommonDTO.apprlineId"/><!-- Key -->
<html:hidden property="maAppLineUsrListDTO.apprlineusrId"/><!-- Detail Key -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" >
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
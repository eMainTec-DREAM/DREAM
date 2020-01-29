<%--===========================================================================
수리기안
author  ssong
version $Id:  $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.rep.action.MaPtRepAppListAction" %>
<%@ page import="dream.part.rep.action.MaPtRepAppDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 수리기안 -->
<title><bean:message key='TAB.maPtRepAppList'/></title>
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
        goTabPage("maPtRepAppDetail");
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
    if (maPtRepAppListForm.elements['maPtRepCommonDTO.ptRepairListId'].value == '') return;
    
    var form = document.maPtRepAppListForm; 
    form.strutsAction.value = '<%=MaPtRepAppListAction.PTREPAPP_LIST_FIND %>';
    
    var url = contextPath + "/maPtRepAppList.do";
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPtRepAppListForm), "PTRPRAPPLISTID");

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
    var form = document.maPtRepAppListForm;
     
    form.elements['maPtRepAppListDTO.ptRprAppListId'].value = getValueById(myGrid, selectedId, 'ptRprAppListId');
    goCommonTabPage(form, <%=MaPtRepAppDetailAction.PTREPAPP_DETAIL_INIT %>, pageId);
}

/**
 * 상세열기
 */
function goOpen()
{
    goTabPage('maPtRepAppDetail');
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
    createValidationCheck(myGrid, "maPtRepAppDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
    maPtRepAppListForm.elements['maPtRepAppListDTO.ptRprAppListId'].value = "";
    goCommonTabPage(maPtRepAppListForm, '', pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_ptRprAppListId)
{
	maPtRepAppListForm.elements['maPtRepAppListDTO.ptRprAppListId'].value = _ptRprAppListId;
	findGridList('ReloadRow');
	maPtRepAppListForm.elements['maPtRepAppListDTO.ptRprAppListId'].value = "";
}

 /**
  * 삭제
  */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'PTRPRAPPLISTID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    maPtRepAppListForm.strutsAction.value = '<%=MaPtRepAppListAction.PTREPAPP_LIST_DELETE%>';
    var url = contextPath + "/maPtRepAppList.do";
    
    $.post(url,FormQueryString(maPtRepAppListForm)+delArray , function(_data){
        afterDelete();
    });
 }
 
function afterDelete()
{
    goClose('maPtRepAppDetail',this);
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * I/F 적용 버튼 클릭 시 
 */
function goInterface()
{
	// todo 
	lovPtApp();
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPtRepAppList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPtRepCommonDTO.ptRepairListId"/><!-- Key -->
<html:hidden property="maPtRepAppListDTO.ptRprAppListId"/>
    <!-- searchbox 박스 Line --> 

    <div class="article_box" id="listBox">
        <div class="grid_area">
            <div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
        </div>
    </div>

</html:form> 
</body>
</html>
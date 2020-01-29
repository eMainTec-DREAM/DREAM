<%--===========================================================================

author  ssong
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.usrrpt.action.MaUserRptTableListAction" %>
<%@ page import="dream.mgr.usrrpt.action.MaUserRptTableDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!--  -->
<title><bean:message key='TAB.maUserRptTableList'/></title>
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
        goTabPage("maUserRptTableDetail");
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
    var form = document.maUserRptTableListForm; 
    form.strutsAction.value = '<%=MaUserRptTableListAction.USER_TABLE_LIST_FIND %>';
    
    var url = contextPath + "/maUserRptTableList.do";

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maUserRptTableListForm), "usrrpttabId");

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
    var form = document.maUserRptTableListForm;
    form.elements['maUserRptCommonDTO.stepNum'].value = myGrid.getRowsNum();
    form.elements['maUserRptCommonDTO.usrrpttabId'].value = getValueById(myGrid, selectedId, 'usrrpttabId');
    
    goCommonTabPage(form, <%= MaUserRptTableDetailAction.USER_TABLE_DETAIL_INIT %>, pageId);
}
/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_usrrptlistId)
{
	maUserRptTableListForm.elements['maUserRptCommonDTO.usrrpttabId'].value = _usrrptlistId;
	findGridList('ReloadRow');
	maUserRptTableListForm.elements['maUserRptCommonDTO.usrrpttabId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
    goTabPage('maUserRptTableDetail');
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
    createValidationCheck(myGrid, "maUserRptTableDetail" , "goCreateAction");
    maUserRptTableListForm.elements['maUserRptCommonDTO.stepNum'].value = myGrid.getRowsNum();
}

function goCreateAction(pageId)
{
    goCommonTabPage(maUserRptTableListForm, '', pageId);
}
 
/**
 * 삭제
 */
function goDelete()
{
	
	var deleteRows = myGrid.getCheckedRows(getIndexById(myGrid, 'isDelCheck')).split(",");
    if(deleteRows == "" && !myGrid.isItemExists('')) return;

    var isMain = false;
	for(var i = 0; deleteRows.length > i; i++)
	{
		if("MAIN" == getValueById(myGrid, deleteRows[i], 'mainSubType')) isMain = true;
	}
	
	if(isMain && myGrid.getRowsNum() >1)
	{
		alertMessage2("Sub Table이 존재하면 Main Table을 지울수 없습니다.");
		return;
	}
	
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'usrrpttabId'); //Grid, check box column seq, pk column seq

    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
    
    maUserRptTableListForm.strutsAction.value = '<%=MaUserRptTableListAction.USER_TABLE_LIST_DELETE%>';
    var url = contextPath + "/maUserRptTableList.do";
    
    $.post(url,FormQueryString(maUserRptTableListForm)+delArray , function(_data){
        afterDelete();
    });
}
 
function afterDelete()
{
    goClose('maUserRptTableDetail',this);
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
    
    getParentIframe("maUserRptColList").goSearch();
	getParentIframe("maUserRptParamList").goSearch();
	getParentIframe("maUserRptOrdList").goSearch();
	getParentIframe("maUserRptJoinList").goSearch();
}


function reloadSubList()
{
	getParentIframe("maUserRptColList").goSearch();
	getParentIframe("maUserRptParamList").goSearch();
	getParentIframe("maUserRptOrdList").goSearch();
	getParentIframe("maUserRptJoinList").goSearch();
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maUserRptTableList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maUserRptCommonDTO.usrrptlistId"/><!-- Key -->
<html:hidden property="maUserRptCommonDTO.usrrpttabId"/>
<html:hidden property="maUserRptCommonDTO.stepNum"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
            <div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
    </div>

</html:form> 
</body>
</html>
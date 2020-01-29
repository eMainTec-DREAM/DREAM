<%--===========================================================================

author  ssong
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.usrrpt.action.MaUserRptColListAction" %>
<%@ page import="dream.mgr.usrrpt.action.MaUserRptColDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!--  -->
<title><bean:message key='TAB.maUserRptColList'/></title>
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
        goTabPage("maUserRptColDetail");
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
    var form = document.maUserRptColListForm; 
    form.strutsAction.value = '<%=MaUserRptColListAction.USER_COL_LIST_FIND %>';
    
    var url = contextPath + "/maUserRptColList.do";

    //doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maUserRptColListForm), ["tabcolId","usrrptcolId"]);
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maUserRptColListForm), "tabcolId");

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
    var form = document.maUserRptColListForm;
   
    form.elements['maUserRptCommonDTO.tabcolId'].value = getValueById(myGrid, selectedId, "tabcolId");
    form.elements['maUserRptCommonDTO.usrrptcolId'].value = getValueById(myGrid, selectedId, "usrrptcolId");
    form.elements['maUserRptCommonDTO.usrrpttabId'].value = getValueById(myGrid, selectedId, "usrrpttabId");
    
    goCommonTabPage(form, <%= MaUserRptColDetailAction.USER_COL_DETAIL_INIT %>, pageId);
}
/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_tabcolId, _usrrptcolId)
{
	maUserRptColListForm.elements['maUserRptCommonDTO.usrrptcolId'].value = _usrrptcolId;
	maUserRptColListForm.elements['maUserRptCommonDTO.tabcolId'].value = _tabcolId;
	findGridList('ReloadRow');
	maUserRptColListForm.elements['maUserRptCommonDTO.usrrptcolId'].value = "";
	maUserRptColListForm.elements['maUserRptCommonDTO.tabcolId'].value ="";
	
	//goSearch();
}
/**
 * 상세열기
 */
function goOpen()
{
    goTabPage('maUserRptColDetail');
}

/**
 * 엑셀 다운.
 */
function goExcel()
{
   excelAction(myGrid);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maUserRptColList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maUserRptCommonDTO.usrrptlistId"/><!-- Key -->
<html:hidden property="maUserRptCommonDTO.usrrpttabId"/>
<html:hidden property="maUserRptCommonDTO.usrrptcolId"/>
<html:hidden property="maUserRptCommonDTO.tabcolId"/>
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
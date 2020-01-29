<%--===========================================================================

author  ssong
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.usrrpt.action.MaUserRptJoinListAction" %>
<%@ page import="dream.mgr.usrrpt.action.MaUserRptJoinDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!--  -->
<title><bean:message key='TAB.maUserRptJoinList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="innerTabPage">
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
        goTabPage("maUserRptJoinDetail");
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
    var form = document.maUserRptJoinListForm; 
    form.strutsAction.value = '<%=MaUserRptJoinListAction.USER_JOIN_LIST_FIND %>';
    
    var url = contextPath + "/maUserRptJoinList.do";

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maUserRptJoinListForm), "usrrptjoinId");

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
    var form = document.maUserRptJoinListForm;
    form.elements['maUserRptCommonDTO.usrrptjoinId'].value = getValueById(myGrid, selectedId, 'usrrptjoinId');
    goCommonTabPage(form, <%= MaUserRptJoinDetailAction.USER_JOIN_DETAIL_INIT %>, pageId);
}
/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_usrrptlistId)
{
	maUserRptJoinListForm.elements['maUserRptCommonDTO.usrrptjoinId'].value = _usrrptlistId;
	findGridList('ReloadRow');
	maUserRptJoinListForm.elements['maUserRptCommonDTO.usrrptjoinId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
    goTabPage('maUserRptJoinDetail');
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
    createValidationCheck(myGrid, "maUserRptJoinDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
    goCommonTabPage(maUserRptJoinListForm, '', pageId);
}
 
/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'usrrpttabId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
    
    maUserRptJoinListForm.strutsAction.value = '<%=MaUserRptJoinListAction.USER_JOIN_LIST_DELETE%>';
    var url = contextPath + "/maUserRptJoinList.do";
    
    $.post(url,FormQueryString(maUserRptJoinListForm)+delArray , function(_data){
        afterDelete();
    });
}
 
function afterDelete()
{
    goClose('maUserRptJoinDetail',this);
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maUserRptJoinList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maUserRptCommonDTO.usrrptlistId"/><!-- Key -->
<html:hidden property="maUserRptCommonDTO.usrrpttabId"/>
<html:hidden property="maUserRptCommonDTO.usrrptjoinId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
            <div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
    </div>

</html:form> 
</body>
</html>
<%--===========================================================================
부품사용부서
author  ssong
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.list.action.MaPtMstrUsedDeptListAction" %>
<%@ page import="dream.part.list.action.MaPtMstrUsedDeptDetailAction" %>
<%@ page import="dream.part.list.action.MaPtMstrDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 부품사용부서 -->
<title><bean:message key='TAB.maPtMstrUsedDept'/></title>
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
        goTabPage("maPtMstrUsedDeptDetail");
    });
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	maPtMstrEqPartListForm.elements['maPtMstrCommonDTO.ptUsedDeptId'].value = "";
        return sortColumn("maPtMstrEqPartList", this, maPtMstrEqPartListForm, "PTUSEDDEPTID", ind, direction);
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
    //페이지id에 값이 없는경우 Grid 조회를 하지않기 위해 return 한다.
    if (maPtMstrUsedDeptListForm.elements['maPtMstrCommonDTO.partId'].value == '') return;
    
    var form = document.maPtMstrUsedDeptListForm; 
    form.strutsAction.value = '<%=MaPtMstrUsedDeptListAction.PTMSTR_USEDDEPT_LIST_FIND %>';
    
    var url = contextPath + "/maPtMstrUsedDeptList.do";

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPtMstrUsedDeptListForm), "PTUSEDDEPTID", "Y");

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
    var form = document.maPtMstrUsedDeptListForm;
    form.elements['maPtMstrCommonDTO.ptUsedDeptId'].value = getValueById(myGrid, selectedId, 'ptUsedDeptId');
    goCommonTabPage(form, <%= MaPtMstrUsedDeptDetailAction.PTMSTR_USEDDEPT_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_ptUsedDeptId)
{
	maPtMstrUsedDeptListForm.elements['maPtMstrCommonDTO.ptUsedDeptId'].value = _ptUsedDeptId;
	findGridList('ReloadRow');
	maPtMstrUsedDeptListForm.elements['maPtMstrCommonDTO.ptUsedDeptId'].value = "";
}

/**
 * 상세열기
 */
function goOpen()
{
    goTabPage('maPtMstrUsedDeptDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maPtMstrUsedDeptListForm.elements['maPtMstrCommonDTO.ptUsedDeptId'].value = getValueById(myGrid, selectedId, 'ptUsedDeptId');
    maPtMstrUsedDeptListForm.elements['strutsAction'].value = '<%=MaPtMstrUsedDeptDetailAction.PTMSTR_USEDDEPT_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maPtMstrUsedDeptListForm), 'maPtMstrUsedDeptDetail'); 
} 

/**
 * 엑셀 다운.
 */
function goExcel()
{
	maPtMstrUsedDeptListForm.elements['maPtMstrCommonDTO.ptUsedDeptId'].value = "";
   	excelServerAction("maPtMstrUsedDeptList",maPtMstrUsedDeptListForm);
}
 
  /**
   * 생성
   */
function goCreate()
{
    createValidationCheck(myGrid, "maPtMstrUsedDeptDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
    maPtMstrUsedDeptListForm.elements['maPtMstrCommonDTO.ptUsedDeptId'].value = "";
    goCommonTabPage(maPtMstrUsedDeptListForm, '', pageId);
}
 
/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'ptUsedDeptId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
    
    maPtMstrUsedDeptListForm.strutsAction.value = '<%=MaPtMstrUsedDeptListAction.PTMSTR_USEDDEPT_LIST_DELETE%>';
    var url = contextPath + "/maPtMstrUsedDeptList.do";
    
    $.post(url,FormQueryString(maPtMstrUsedDeptListForm)+delArray , function(_data){
        afterDelete();
    });
}
 
function afterDelete()
{
    goClose('maPtMstrUsedDeptDetail',this);
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPtMstrUsedDeptList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPtMstrCommonDTO.partId"/><!-- Key -->
<html:hidden property="maPtMstrCommonDTO.ptUsedDeptId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
            <div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
    </div>

</html:form> 
</body>
</html>
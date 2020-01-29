<%--===========================================================================
자재마스터 거래처
author  ssong
version $Id: maPtMstrVendorList.jsp,v 1.1 2015/12/03 01:45:27 ssong Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.list.action.MaPtMstrVendorListAction" %>
<%@ page import="dream.part.list.action.MaPtMstrVendorDetailAction" %>
<%@ page import="dream.part.list.action.MaPtMstrDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 자재마스터 거래처 -->
<title><bean:message key='TAB.maPtMstrVendorList'/></title>
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
 	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
 		maPtMstrVendorListForm.elements['maPtMstrCommonDTO.ptVendorId'].value = "";
        return sortColumn("maPtMstrVendorList", this, maPtMstrVendorListForm, "ptVendorId", ind, direction);
    });
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goTabPage("maPtMstrVendorDetail");
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
	if (maPtMstrVendorListForm.elements['maPtMstrCommonDTO.partId'].value == '') return;
	
	var form = document.maPtMstrVendorListForm;	
	form.strutsAction.value = '<%=MaPtMstrVendorListAction.PTMSTR_VENDOR_LIST_FIND %>';
	
	var url = contextPath + "/maPtMstrVendorList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPtMstrVendorListForm), "PTVENDORID", "Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_ptVendorId)
{
	maPtMstrVendorListForm.elements['maPtMstrCommonDTO.ptVendorId'].value = _ptVendorId;
	findGridList('ReloadRow');
	maPtMstrVendorListForm.elements['maPtMstrCommonDTO.ptVendorId'].value = "";
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
	var form = document.maPtMstrVendorListForm;
	form.elements['maPtMstrCommonDTO.ptVendorId'].value = getValueById(myGrid, selectedId, 'ptVendorId');
	goCommonTabPage(form, <%= MaPtMstrVendorDetailAction.PTMSTR_VENDOR_DETAIL_INIT %>, pageId);
}


/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maPtMstrVendorDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maPtMstrVendorListForm.elements['maPtMstrCommonDTO.ptVendorId'].value = getValueById(myGrid, selectedId, 'ptVendorId');
    maPtMstrVendorListForm.elements['strutsAction'].value = '<%=MaPtMstrVendorDetailAction.PTMSTR_VENDOR_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maPtMstrVendorListForm), 'maPtMstrVendorDetail'); 
} 

/**
 * 엑셀 다운.
 */
 function goExcel()
 {
	 maPtMstrVendorListForm.elements['maPtMstrCommonDTO.ptVendorId'].value = "";
 	 excelServerAction("maPtMstrVendorList", maPtMstrVendorListForm);
 }
 
  /**
   * 생성
   */
function goCreate()
{
 	createValidationCheck(myGrid, "maPtMstrVendorDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maPtMstrVendorListForm.elements['maPtMstrCommonDTO.ptVendorId'].value = "";
	goCommonTabPage(maPtMstrVendorListForm, '', pageId);
}
 
 /**
  * 삭제
  */
function goDelete()
 {
	var delArray = getDeletRows(myGrid, 'isDelCheck', 'ptVendorId'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maPtMstrVendorListForm.strutsAction.value = '<%=MaPtMstrVendorListAction.PTMSTR_VENDOR_LIST_DELETE%>';
	var url = contextPath + "/maPtMstrVendorList.do";
	
	$.post(url,FormQueryString(maPtMstrVendorListForm)+delArray , function(_data){
		afterDelete();
	});
  }
 
function afterDelete()
{
	goClose('maPtMstrVendorDetail',this);
   	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
   }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPtMstrVendorList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPtMstrCommonDTO.partId"/><!-- Key -->
<html:hidden property="maPtMstrCommonDTO.ptVendorId"/>
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
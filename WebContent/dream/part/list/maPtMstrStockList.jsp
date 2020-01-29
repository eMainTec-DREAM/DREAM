<%--===========================================================================
자재마스터 현재고
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.list.action.MaPtMstrStockListAction" %>
<%@ page import="dream.part.stk.action.MaPtStckListAction" %>
<%@ page import="dream.part.stk.action.MaPtStckDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!-- 자재마스터 현재고 -->
<title><bean:message key='TAB.maPtMstrStockList'/></title>
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
		goOpen();
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		maPtMstrStockListForm.elements['maPtMstrStockListDTO.wcodeId'].value 	= "";
    	return sortColumn("maPtMstrStockList", this, maPtMstrStockListForm, "WCODEID", ind, direction);
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
	if (maPtMstrStockListForm.elements['maPtMstrCommonDTO.partId'].value == '') return;
	
	maPtMstrStockListForm.elements['maPtMstrStockListDTO.partId'].value = maPtMstrStockListForm.elements['maPtMstrCommonDTO.partId'].value;
	
	var form = document.maPtMstrStockListForm;	
	form.strutsAction.value = '<%=MaPtMstrStockListAction.LIST_FIND %>';
	
	var url = contextPath + "/maPtMstrStockList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPtMstrStockListForm), "WCODEID", "Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
 function findGridRow(wcodeId, partId)
 {
	maPtMstrStockListForm.elements['maPtMstrStockListDTO.wcodeId'].value 	= wcodeId;
  	findGridList('ReloadRow');
  	
  	maPtMstrStockListForm.elements['maPtMstrStockListDTO.wcodeId'].value 	= "";
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
	maPtMstrStockListForm.elements['maPtStckCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'compNo');
	maPtMstrStockListForm.elements['maPtStckCommonDTO.wcodeId'].value = getValueById(myGrid, selectedId, 'wcodeId');
	maPtMstrStockListForm.elements['maPtStckCommonDTO.partId'].value = maPtMstrStockListForm.elements['maPtMstrCommonDTO.partId'].value;
	
    goCommonTabPage(maPtMstrStockListForm, <%=MaPtStckDetailAction.PTSTCK_DETAIL_INIT%>, pageId);
}


/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maPtStckDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maPtMstrStockListForm.elements['maPtStckCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'compNo');
	maPtMstrStockListForm.elements['maPtStckCommonDTO.wcodeId'].value = getValueById(myGrid, selectedId, 'wcodeId');
	maPtMstrStockListForm.elements['maPtStckCommonDTO.partId'].value = maPtMstrStockListForm.elements['maPtMstrCommonDTO.partId'].value;
	maPtMstrStockListForm.elements['strutsAction'].value = '<%=MaPtStckDetailAction.PTSTCK_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maPtMstrStockListForm), 'maPtStckDetail'); 
} 

/**
 * 엑셀 다운
 */
function goExcel()
{
	maPtMstrStockListForm.elements['maPtMstrStockListDTO.wcodeId'].value 	= "";
	excelServerAction("maPtMstrStockList", maPtMstrStockListForm);
}

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maPtStckDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maPtMstrStockListForm.elements['maPtStckCommonDTO.wcodeId'].value = "";
	maPtMstrStockListForm.elements['maPtStckCommonDTO.partId'].value
		= maPtMstrStockListForm.elements['maPtMstrStockListDTO.partId'].value;
	
	maPtMstrStockListForm.elements['maPtStckCommonDTO.wcodeId'].value
		= maPtMstrStockListForm.elements['maPtMstrStockListDTO.wcodeId'].value;
	
    goCommonTabPage(maPtMstrStockListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'wcodeId', 'partId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
    
    maPtMstrStockListForm.strutsAction.value = '<%=MaPtStckListAction.PTSTCK_LIST_DELETE%>';
    var url = contextPath + "/maPtStckList.do";

    $.post(url,FormQueryString(maPtMstrStockListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('maPtStckDetail', this);
//     goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * 구매신청
 */
 var selectArray;
function goBuyreq()
{
	var selArray = getSelectedRows(myGrid, 'ISDELCHECK', 'PARTID', 'UNDERSAFTYQTY');
	
	if(typeof selArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0052'/>", function(result){
		if(result){
	 		selectArray = selArray;
			sequenceNextVal('SQAPTPRLIST_ID');
		}	
	});
}

function setSequenceVal(sequenceVal)
{
	maPtMstrStockListForm.elements['maPtStckCommonDTO.reqIdx'].value = sequenceVal;
	maPtMstrStockListForm.strutsAction.value = '<%=MaPtStckListAction.PTSTCK_LIST_BUYREQ%>';
	var url = contextPath + "/maPtStckList.do"; 
	$.post(url,FormQueryString(maPtMstrStockListForm)+selectArray , function(_data){
		afterBuyreq();
	});
}
/**
 * after구매신청
 */
 function afterBuyreq()
 {
 	//구매신청 상세 팝업 띄우기
	var url   = contextPath + "/maPtBuyReqHdrDetail.do";
	var popWidth = 1010;
	var popHeight = 640;

    // pop up이 중앙에 위치하게 한다.
    var TopPosition  = (screen.height/2 - popHeight/2);
    var LeftPosition = (screen.width/2 - popWidth/2);

    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
    
    var param = "strutsAction="+'<%=MaPtStckDetailAction.PTSTCK_DETAIL_INIT%>'
    		  +"&maPtBuyReqHdrCommonDTO.ptPrListId="+ maPtMstrStockListForm.elements['maPtStckCommonDTO.reqIdx'].value;
  
    openWindowWithPost(url, "BUYREQHDR", param, pos);
 }
 
 /**
  * 레포트 출력
  */
function goPrint()
 {
	var selArray = "&deleteRows="+maPtMstrStockListForm.elements['maPtMstrDetailDTO.partId'].value
				 + "&deleteRowsExt=" + loginUser.wcodeId;

	if(typeof selArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	maPtMstrStockListForm.strutsAction.value = '<%=MaPtStckListAction.PTSTCK_QR_INSERT%>';
	var url = contextPath + "/maPtStckList.do";
	$.post(url,FormQueryString(maPtMstrStockListForm)+selArray , function(_data){
		startReportCall();
    });
 } 
function goListbarcode()
 {
	
	maPtMstrStockListForm.strutsAction.value = '<%=MaPtStckListAction.PTSTCK_QR_LIST_INSERT%>'; 
	var url = contextPath + "/maPtStckList.do";
	$.post(url,FormQueryString(maPtMstrStockListForm) , function(_data){
		startReportCall();
    });
 } 

 function startReportCall ()
 {
	 if("<%=user.getCompNo()%>" == "140"&&loginUser.plant=='SLP'){
		 reportCall('ptBarcode_SLP','ptBarcode', "<%=user.getCompNo()%>", "<%=user.getUserId()%>", "<%=user.getLangId()%>");
	 }
	 else if("<%=user.getCompNo()%>" == "130"||loginUser.plant=='MTL'){
		 reportCall('ptBarcode_MTL','ptBarcode_MTL', "<%=user.getCompNo()%>", "<%=user.getUserId()%>");
	 }
	 else if("<%=user.getCompNo()%>" == "150"||loginUser.plant=='GA'){
		 reportCall('ptBarcode_GA','ptBarcode_GA', "<%=user.getCompNo()%>", "<%=user.getUserId()%>");
	 }else{
		 reportCall('ptBarcode','ptBarcode', "<%=user.getCompNo()%>", "<%=user.getUserId()%>", "<%=user.getLangId()%>");
	 }
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPtMstrStockList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPtMstrCommonDTO.partId"/><!-- Key -->
<html:hidden property="maPtMstrStockListDTO.partId"/><!-- Key -->

<html:hidden property="maPtMstrDetailDTO.partId"/>
<html:hidden property="maPtMstrDetailDTO.partNo"/>
<html:hidden property="maPtMstrDetailDTO.description"/>
<html:hidden property="maPtMstrDetailDTO.ptSize"/>
<html:hidden property="maPtMstrDetailDTO.model"/>

<html:hidden property="maPtMstrStockListDTO.wcodeId"/><!-- Key -->
<html:hidden property="maPtStckCommonDTO.compNo"/><!-- Key -->
<html:hidden property="maPtStckCommonDTO.wcodeId"/><!-- Key -->
<html:hidden property="maPtStckCommonDTO.partId"/><!-- Key -->
<html:hidden property="maPtStckCommonDTO.reqIdx"/><!-- Key -->
    <!-- searchbox 박스 Line -->

    <div class="article_box" id="listBox">
           <div class="grid_area">
           	<div id="gridbox" style="width:100%; height:170px; background-color:white;"></div>
           </div>
 	</div>

</html:form> 
</body>
</html>
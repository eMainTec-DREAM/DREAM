<%--===========================================================================
사용자코드관리 - 상세
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.usrcd.action.MaCdUsrCdListAction"%>
<%@ page import="dream.mgr.usrcd.action.MaCdUsrCdDetailAction"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<html:html> 
<head>
<!-- 사용자코드 -->
<title><bean:message key='TAB.maCdUsrCdList' />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="adminPage">

<script language="javascript">// 저장 시 수행되는 action
<!--//
//그리드명
var myGrid;
var selectedUsrCdId, selectedUsrCdInd;
function loadPage() 
{
    initGrid();
}

function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    myGrid.enableTreeGridLines();
	myGrid.setImageSize(1,1); 
	myGrid.enableSmartRendering(true,500);
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")});
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
        selectedUsrCdId = rowId;
		selectedUsrCdInd = columnId;
		goOpen();
    });
     
    myGrid.init();
    setHeader(myGrid, "gridbox", "goSearch", "maCdUsrCdList"); // grid, grid id, callBack
}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	maCdUsrCdListForm.elements['maCdUsrCommonDTO.cdUsrdId'].value = "";	// 검색시 Tab 이동Key Clear
	findGridList('SearchTree');
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
 function findGridList(sheetAction)
 {
  	var url = contextPath + "/maCdUsrCdList.do"; 	
  	maCdUsrCdListForm.strutsAction.value = '<%=MaCdUsrCdListAction.CDUSR_CD_FINDSHEET%>';
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maCdUsrCdListForm), "CDUSRDID", "", "PCDUSRDID");
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
	var form = document.maCdUsrCdListForm;
	 
	form.elements['maCdUsrCommonDTO.cdUsrdId'].value = getValueById(myGrid, selectedId, 'cdUsrdId');
	goCommonTabPage(form, <%= MaCdUsrCdDetailAction.CDUSR_CD_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_cdUsrdId)
{
	maCdUsrCdListForm.elements['maCdUsrCommonDTO.cdUsrdId'].value = _cdUsrdId;
	findGridList('ReloadTreeRow');
	maCdUsrCdListForm.elements['maCdUsrCommonDTO.cdUsrdId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
    goTabPage('maCdUsrCdDetail');
}

function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
	if(selectedId == null) return;
	    
	maCdUsrCdListForm.elements['maCdUsrCommonDTO.cdUsrdId'].value = getValueById(myGrid, selectedId, 'cdUsrdId');
    maCdUsrCdListForm.elements['strutsAction'].value = '<%= MaCdUsrCdDetailAction.CDUSR_CD_DETAIL_INIT %>';
    openQuickTabPage(FormQueryString(maCdUsrCdListForm),'maCdUsrCdDetail');
}

/**
 * Popup 생성
 */
function goCreate()
{
    //수정중 확인
//    if(checkIsUpdate(document)) return;

    //이미 신규생성 페이지가 열려 있다면... confirm
/*     if(!ckCreate('maCdUsrCdDetail', this))
    {
        myGrid.addRow("","",0);
    }
    
    maCdUsrCdListForm.elements['maCdUsrCommonDTO.cdUsrdId'].value = "";
    goCommonTabPage(maCdUsrCdListForm, '', "maCdUsrCdDetail"); */
    
 	createValidationCheck(myGrid, "maCdUsrCdDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maCdUsrCdListForm.elements['maCdUsrCommonDTO.cdUsrdId'].value = "";
 	goCommonTabPage(maCdUsrCdListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
	var delArray = getDeletRows(myGrid, 'isDelCheck', 'cdUsrdId'); //Grid, check box column seq, pk column seq
	
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
    
    maCdUsrCdListForm.strutsAction.value = '<%=MaCdUsrCdListAction.CDUSR_CD_GRID_DELETE%>';
    var url = contextPath + "/maCdUsrCdList.do";
    
    $.post(url,FormQueryString(maCdUsrCdListForm)+delArray , function(_data){
        afterDelete();
    });
 }

function afterDelete()
{
    goClose('maCdUsrCdDetail', this);
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
   	maCdUsrCdListForm.elements['maCdUsrCommonDTO.cdUsrdId'].value = "";
    excelServerAction("maCdUsrCdList", maCdUsrCdListForm );  
}

/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maCdUsrCdListForm.elements['maCdUsrCommonDTO.cdUsrdId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>
</head>
<body style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/maCdUsrCdList.do" >
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maCdUsrCommonDTO.compNo" />
<html:hidden property="maCdUsrCommonDTO.cdUsrmId" />
<html:hidden property="maCdUsrCommonDTO.cdUsrdId" />

    <div class="section_wrap">
	    <div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.List"/></div>
			</div>
			<div class="function_box list">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>

				<div class="fb_group2">
				</div>
			</div>
	    </div><!--sheader_box end-->
	    <div class="article_box">
            <div class="grid_area">
            	<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
            </div>
	 	</div>
	 </div> <!--  End of section_wrap -->
</html:form>
</body>
</html:html>
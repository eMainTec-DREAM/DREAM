<%--===========================================================================
사용자코드관리
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.usrcd.action.MaCdUsrListAction" %>
<%@ page import="dream.mgr.usrcd.action.MaCdUsrDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>

<html>
<head>
<!-- 사용자코드관리 -->
<title><bean:message key="MENU.CDUSR"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//

var myGrid;
function loadPage() 
{
	initGrid();
}

function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    myGrid.enableSmartRendering(true,500);
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
        goOpen();
    });
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	maCdUsrListForm.elements['maCdUsrCommonDTO.cdUsrmId'].value = "";
    	return sortColumn("maCdUsrList", this, maCdUsrListForm, "CDUSRMID", ind, direction);
	});
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maCdUsrList.do";

    maCdUsrListForm.elements['strutsAction'].value = '<%=MaCdUsrListAction.CDUSR_FINDSHEET%>';

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maCdUsrListForm), "CDUSRMID", "Y");

}

/**
 * Filter에서 Sheet 검색을 하는 경우이다.
 */
function goSearch()
{
	maCdUsrListForm.elements['maCdUsrCommonDTO.cdUsrmId'].value = "";    // 검색시 Tab 이동Key Clear
	findGridList('Search');
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_cdUsrmId)
{
	maCdUsrListForm.elements['maCdUsrCommonDTO.cdUsrmId'].value = _cdUsrmId;
	findGridList('ReloadRow');
	maCdUsrListForm.elements['maCdUsrCommonDTO.cdUsrmId'].value = "";
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
	maCdUsrListForm.elements['maCdUsrCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'compNo');
    maCdUsrListForm.elements['maCdUsrCommonDTO.cdUsrmId'].value = getValueById(myGrid, selectedId, 'cdUsrmId');

    goCommonTabPage(maCdUsrListForm, <%=MaCdUsrDetailAction.CDUSR_DETAIL_INIT%>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
	goTabPage('maCdUsrDetail');
}

function goOpenAction()
{
	var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maCdUsrListForm.elements['maCdUsrCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'compNo');
    maCdUsrListForm.elements['maCdUsrCommonDTO.cdUsrmId'].value = getValueById(myGrid, selectedId, 'cdUsrmId');
	maCdUsrListForm.elements['strutsAction'].value = '<%=MaCdUsrDetailAction.CDUSR_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maCdUsrListForm),'maCdUsrDetail');
}

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maCdUsrDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maCdUsrListForm.elements['maCdUsrCommonDTO.cdUsrmId'].value = "";
    goCommonTabPage(maCdUsrListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'cdUsrmId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
    
    maCdUsrListForm.strutsAction.value = '<%=MaCdUsrListAction.CDUSR_LIST_DELETE%>';
    var url = contextPath + "/maCdUsrList.do";

    $.post(url,FormQueryString(maCdUsrListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('maCdUsrDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
    //excelAction(myGrid);
    maCdUsrListForm.elements['maCdUsrCommonDTO.cdUsrmId'].value = "";
	excelServerAction("maCdUsrList", maCdUsrListForm );  
}
//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/maCdUsrList">
<html:hidden property="strutsAction"/>
<html:hidden property="maCdUsrCommonDTO.compNo"/><!-- Key -->
<html:hidden property="maCdUsrCommonDTO.cdUsrmId"/><!-- Key -->
    <!-- searchbox 박스 Line -->
    <div class="section_wrap">
        <div class="sheader_box">
			<div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.Filter"/></div>
			</div>
			<div class="function_box filter">
				<div class="fb_group3">
					<div class="sfb_wrap" style="display:none;">
					</div>
				</div>	
				<div class="fb_group2">
				</div>
			</div>
		</div><!--sheader_box end-->
           <div class="article_box">
               <div class="form_box">
                   <div class="field">
                       <label><bean:message key="LABEL.dirTypeDesc"/></label>
                       <div class="input_box">
                            <html:text property="maCdUsrCommonDTO.filterDescription" tabindex="10"/>
                       </div>
                   </div>
                   <div class="field">
                       <label><bean:message key="LABEL.dirType"/></label>
                       <div class="input_box">
                            <html:text property="maCdUsrCommonDTO.filterDirType" tabindex="11"/>
                       </div>
                   </div>
                </div>
            </div><!--article_box end-->
    </div> <!--  end section_wrap -->
        
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
        <div class="article_box" id="listBox">
            <div class="grid_area">
                <div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
            </div>
        </div>
     </div> <!--  End of section_wrap -->

</html:form> 
</body>
</html>
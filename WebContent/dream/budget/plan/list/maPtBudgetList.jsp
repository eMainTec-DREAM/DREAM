<%--===========================================================================
예산관리
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.budget.plan.list.action.MaPtBudgetListAction" %>
<%@ page import="dream.budget.plan.list.action.MaPtBudgetDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>

<html>
<head>
<!-- 예산관리 -->
<title><bean:message key="LABEL.PTBUDGET"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//

var myGrid;
function loadPage() 
{
	initGrid();
	
	maPtBudgetListForm.elements['maPtBudgetCommonDTO.yyyymm'].value = dateToData(getToday()).substr(0, 4)+"-"+dateToData(getToday()).substr(4, 2);

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
    	maPtBudgetListForm.elements['maPtBudgetCommonDTO.bgtPlanId'].value = "";
        return sortColumn("maPtBudgetList", this, maPtBudgetListForm, "BGTPLANID", ind, direction);
    });
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maPtBudgetList.do";

    maPtBudgetListForm.elements['strutsAction'].value = '<%=MaPtBudgetListAction.PTBUDGET_LIST_FIND%>';

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPtBudgetListForm), "bgtPlanId", "Y");
    
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(bgtPlanId)
{
	maPtBudgetListForm.elements['maPtBudgetCommonDTO.bgtPlanId'].value = bgtPlanId;
	findGridList('ReloadRow');
	maPtBudgetListForm.elements['maPtBudgetCommonDTO.bgtPlanId'].value = "";
}

/**
 * Filter에서 Sheet 검색을 하는 경우이다.
 */
function goSearch()
{
	maPtBudgetListForm.elements['maPtBudgetCommonDTO.bgtPlanId'].value = "";    // 검색시 Tab 이동Key Clear
	findGridList('Search');
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
	maPtBudgetListForm.elements['maPtBudgetCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'compNo');
    maPtBudgetListForm.elements['maPtBudgetCommonDTO.bgtPlanId'].value = getValueById(myGrid, selectedId, 'bgtPlanId');

    goCommonTabPage(maPtBudgetListForm, <%=MaPtBudgetDetailAction.PTBUDGET_DETAIL_INIT%>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
	goTabPage('maPtBudgetDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maPtBudgetListForm.elements['maPtBudgetCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'compNo');
    maPtBudgetListForm.elements['maPtBudgetCommonDTO.bgtPlanId'].value = getValueById(myGrid, selectedId, 'bgtPlanId');
    maPtBudgetListForm.elements['strutsAction'].value = '<%=MaPtBudgetDetailAction.PTBUDGET_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maPtBudgetListForm), 'maPtBudgetDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maPtBudgetDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maPtBudgetListForm.elements['maPtBudgetCommonDTO.bgtPlanId'].value = "";
    goCommonTabPage(maPtBudgetListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'bgtPlanId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
    
    maPtBudgetListForm.strutsAction.value = '<%=MaPtBudgetListAction.PTBUDGET_LIST_DELETE%>';
    var url = contextPath + "/maPtBudgetList.do";

    $.post(url,FormQueryString(maPtBudgetListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('maPtBudgetDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	excelAction(myGrid);
}
//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/maPtBudgetList">
<html:hidden property="strutsAction"/>
<html:hidden property="maPtBudgetCommonDTO.compNo"/><!-- Key -->
<html:hidden property="maPtBudgetCommonDTO.bgtPlanId"/><!-- Key -->
<html:hidden property="maPtBudgetCommonDTO.accountNo"/>
    <!-- searchbox 박스 Line -->
    <div class="section_wrap">
        <div class="sheader_box">
            <div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.Filter"/></div>
			</div>
			<div class="function_box filter not_fold">
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
                       <label><bean:message key="LABEL.yyyymm"/></label>
                       <div class="input_read">
						   <html:text property="maPtBudgetCommonDTO.yyyymm" readonly="true"/>
						   <p class="open_mon_calendar"><span>날짜</span></p>
                       </div>
                   </div>
                   <div class="field">
                       <label><bean:message key="LABEL.accountNo"/></label>
                       <div class="input_box">
                           <html:text property="maPtBudgetCommonDTO.accountDesc" tabindex="50"
                                onkeydown="validationKeyDown('maPtBudgetCommonDTO.accountDesc', 'maPtBudgetCommonDTO.accountNo');"/>
                            <p class="open_spop">
                                <a href="javascript:lovSysDir('maPtBudgetCommonDTO.accountNo', 'maPtBudgetCommonDTO.accountDesc', 'ACCNT_NO');">
                                    <span>조회</span>
                                </a>
                            </p>
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
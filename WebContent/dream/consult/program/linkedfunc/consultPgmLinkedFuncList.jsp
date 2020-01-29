<%--===========================================================================
author  youngjoo38
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.program.linkedfunc.action.ConsultPgmLinkedFuncListAction" %>
<%@ page import="dream.consult.program.linkedfunc.action.ConsultPgmLinkedFuncDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 연결기능 (linked Function) -->
<title><bean:message key='MENU.LINKEDFUNC'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="consultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

function loadPage() 
{
	initGrid();
}
/**
 * 그리드 초기화
 */
function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    myGrid.enableSmartRendering(true,500);
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
        goOpen();
    });
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	consultPgmLinkedFuncListForm.elements['consultPgmLinkedFuncCommonDTO.linkedFuncId'].value = "";
        return sortColumn("consultPgmLinkedFuncList", this, consultPgmLinkedFuncListForm, "LINKEDFUNCID", ind, direction);
    });
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); 
    myGrid.init();
    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/consultPgmLinkedFuncList.do";
    consultPgmLinkedFuncListForm.elements['strutsAction'].value = '<%=ConsultPgmLinkedFuncListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(consultPgmLinkedFuncListForm), "LINKEDFUNCID" , "Y");

}
/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_linkedFuncId)
{
    consultPgmLinkedFuncListForm.elements['consultPgmLinkedFuncCommonDTO.linkedFuncId'].value = _linkedFuncId;
    findGridList('ReloadRow');
    consultPgmLinkedFuncListForm.elements['consultPgmLinkedFuncCommonDTO.linkedFuncId'].value = "";
}

function goSearch()
{
    if(checkValidation()) return;
    consultPgmLinkedFuncListForm.elements['consultPgmLinkedFuncCommonDTO.linkedFuncId'].value = "";  // 검색시 Tab 이동Key Clear
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
    consultPgmLinkedFuncListForm.elements['consultPgmLinkedFuncCommonDTO.linkedFuncId'].value =  getValueById(myGrid, selectedId,'LINKEDFUNCID');  
    
    goCommonTabPage(consultPgmLinkedFuncListForm, <%= ConsultPgmLinkedFuncDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('consultPgmLinkedFuncDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    consultPgmLinkedFuncListForm.elements['consultPgmLinkedFuncCommonDTO.linkedFuncId'].value =  getValueById(myGrid, selectedId,'LINKEDFUNCID');  
    consultPgmLinkedFuncListForm.elements['strutsAction'].value = '<%=ConsultPgmLinkedFuncDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(consultPgmLinkedFuncListForm), 'consultPgmLinkedFuncDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "consultPgmLinkedFuncDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
    consultPgmLinkedFuncListForm.elements['consultPgmLinkedFuncCommonDTO.linkedFuncId'].value = "";
    goCommonTabPage(consultPgmLinkedFuncListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'LINKEDFUNCID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
        alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
        return;
    }

    consultPgmLinkedFuncListForm.strutsAction.value = '<%=ConsultPgmLinkedFuncListAction.LIST_DELETE%>';
    var url = contextPath + "/consultPgmLinkedFuncList.do";
    
    $.post(url,FormQueryString(consultPgmLinkedFuncListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('consultPgmLinkedFuncDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	consultPgmLinkedFuncListForm.elements['consultPgmLinkedFuncCommonDTO.linkedFuncId'].value = "";
    excelServerAction("consultPgmLinkedFuncList", consultPgmLinkedFuncListForm );  
}

//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/consultPgmLinkedFuncList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="consultPgmLinkedFuncCommonDTO.linkedFuncId"/><!-- Key -->
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
                <!-- Linked Func 명 -->
                <div class="field">
                    <label><bean:message key="LABEL.linkedFuncDesc"/></label>
                    <div class="input_box">
                        <html:text property="consultPgmLinkedFuncCommonDTO.filterLinkedFuncDesc" tabindex="10"/>
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


<%--===========================================================================
author  youngjoo38
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.close.check.action.MgrWorkCloseCheckListAction" %>
<%@ page import="dream.work.close.check.action.MgrWorkCloseCheckDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 작업완료 점검항목 설정 -->
<title><bean:message key='MENU.AR121'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

/** 자동완성 변수 */
var plantAc;

function loadPage() 
{
	//공장명
    if(loginUser.filterPlant!='null'){
    	mgrWorkCloseCheckListForm.elements['mgrWorkCloseCheckCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	mgrWorkCloseCheckListForm.elements['mgrWorkCloseCheckCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
	
    // 공장명
    plantAc = new autoC({"mgrWorkCloseCheckCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
    plantAc.setAcResultMap({
        "mgrWorkCloseCheckCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
	
    // 작업종류
    acSysDesc("mgrWorkCloseCheckCommonDTO.filterWoTypeDesc","mgrWorkCloseCheckCommonDTO.filterWoTypeId","WO_TYPE");
    
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
    	mgrWorkCloseCheckListForm.elements['mgrWorkCloseCheckCommonDTO.stwrkId'].value = "";
        return sortColumn("mgrWorkCloseCheckList", this, mgrWorkCloseCheckListForm, "stwrkId", ind, direction);
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
    var url = contextPath + "/mgrWorkCloseCheckList.do";
    mgrWorkCloseCheckListForm.elements['strutsAction'].value = '<%=MgrWorkCloseCheckListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mgrWorkCloseCheckListForm), "stwrkId" , "Y");

}
/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_stwrkId)
{
    mgrWorkCloseCheckListForm.elements['mgrWorkCloseCheckCommonDTO.stwrkId'].value = _stwrkId;
    findGridList('ReloadRow');
    mgrWorkCloseCheckListForm.elements['mgrWorkCloseCheckCommonDTO.stwrkId'].value = "";
}

function goSearch()
{
    if(checkValidation()) return;
    mgrWorkCloseCheckListForm.elements['mgrWorkCloseCheckCommonDTO.stwrkId'].value = "";  // 검색시 Tab 이동Key Clear
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
    mgrWorkCloseCheckListForm.elements['mgrWorkCloseCheckCommonDTO.stwrkId'].value =  getValueById(myGrid, selectedId,'stwrkId');  
    
    goCommonTabPage(mgrWorkCloseCheckListForm, <%= MgrWorkCloseCheckDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('mgrWorkCloseCheckDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    mgrWorkCloseCheckListForm.elements['mgrWorkCloseCheckCommonDTO.stwrkId'].value =  getValueById(myGrid, selectedId,'stwrkId');  
    mgrWorkCloseCheckListForm.elements['strutsAction'].value = '<%=MgrWorkCloseCheckDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(mgrWorkCloseCheckListForm), 'mgrWorkCloseCheckDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "mgrWorkCloseCheckDetail" , "goCreateAction");
    
    // 생성시 설비, 평가등급 기준표 수정 불가
}

function goCreateAction(pageId)
{
    mgrWorkCloseCheckListForm.elements['mgrWorkCloseCheckCommonDTO.stwrkId'].value = "";
    goCommonTabPage(mgrWorkCloseCheckListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{

    var delArray = getDeletRows(myGrid, 'isDelCheck', 'stwrkId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
        alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
        return;
    }

    mgrWorkCloseCheckListForm.strutsAction.value = '<%=MgrWorkCloseCheckListAction.LIST_DELETE%>';
    var url = contextPath + "/mgrWorkCloseCheckList.do";
    
    $.post(url,FormQueryString(mgrWorkCloseCheckListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('mgrWorkCloseCheckDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	mgrWorkCloseCheckListForm.elements['mgrWorkCloseCheckCommonDTO.stwrkId'].value = "";
    excelServerAction("mgrWorkCloseCheckList", mgrWorkCloseCheckListForm );  
}

//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/mgrWorkCloseCheckList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="mgrWorkCloseCheckCommonDTO.stwrkId"/><!-- Key -->
<html:hidden property="mgrWorkCloseCheckCommonDTO.filterWoTypeId"/><!-- 작업종류ID -->
<html:hidden property="mgrWorkCloseCheckCommonDTO.filterPlantId"/><!-- 공장ID -->

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
                <!-- 점검# -->
                <div class="field">
                    <label>점검#</label>
                    <div class="input_box">
                        <html:text property="mgrWorkCloseCheckCommonDTO.filterStwrkNo" tabindex="10"/>
                    </div>
                </div>
				<!-- 제목  -->
                <div class="field">
                    <label>제목</label>
                    <div class="input_box">
						<html:text property="mgrWorkCloseCheckCommonDTO.filterStwrkDesc" tabindex="20" />
                    </div>
                </div>
                <!-- 작업종류 -->
                <div class="field">
                    <label>작업종류</label>
                    <div class="input_box">
                        <html:text property="mgrWorkCloseCheckCommonDTO.filterWoTypeDesc" tabindex="30"/>
						<p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
				<!-- 공장  -->
                <div class="field">
                    <label>공장</label>
                    <div class="input_box">
						<html:text property="mgrWorkCloseCheckCommonDTO.filterPlantDesc" tabindex="40" />
						<p class="open_spop"><a><span>조회</span></a></p>
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
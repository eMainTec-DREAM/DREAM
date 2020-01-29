<%--===========================================================================
작업오더 일마감 처리율 목록
author  js.lee
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.woendrate.action.WorkRptWoEndRateListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 작업오더 일마감 처리율 -->
<title><bean:message key='MENU.WOENDRATE'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;
var plantAc;

function loadPage() 
{
	//공장명
    if(loginUser.filterPlant!='null'){
    	workRptWoEndRateListForm.elements['workRptWoEndRateCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	workRptWoEndRateListForm.elements['workRptWoEndRateCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
	//월
	workRptWoEndRateListForm.elements['workRptWoEndRateCommonDTO.filterStartDate'].value = getMinusMonth(-6);
	workRptWoEndRateListForm.elements['workRptWoEndRateCommonDTO.filterEndDate'].value = getMinusMonth(0);
	
    //공장 
    plantAc = new autoC({"workRptWoEndRateCommonDTO.filterPlantDesc":"description"});
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    })
    plantAc.setTable("TAPLANT");
    plantAc.setAcResultMap({
        "workRptWoEndRateCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();

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
    	workRptWoEndRateListForm.elements['workRptWoEndDetailListDTO.plantId'].value = "";
        return sortColumn("workRptWoEndRateList", this, workRptWoEndRateListForm, "PLANTID", ind, direction);
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
    var url = contextPath + "/workRptWoEndRateList.do";
    workRptWoEndRateListForm.elements['strutsAction'].value = '<%=WorkRptWoEndRateListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workRptWoEndRateListForm), "PLANTID", "Y");
}

function goSearch()
{
	if(checkRequireValue("workRptWoEndRateCommonDTO.filterStartDate","<bean:message key='LABEL.workDate'/>")) return;
    if(checkRequireValue("workRptWoEndRateCommonDTO.filterEndDate","<bean:message key='LABEL.workDate'/>")) return;
	
    if(checkValidation()) return;
    
    findGridList('Search');
    
}



function goTabPage(pageId)
{
   tabValidationCheck(myGrid, pageId, "goTabPageAction");
}

function goTabPageAction(pageId, selectedId)
{
	var form = document.workRptWoEndRateListForm;
	
	form.elements['workRptWoEndRateCommonDTO.filterPlantId'].value = getValueById(myGrid, selectedId,'PLANTID');
	form.elements['workRptWoEndDetailListDTO.plantId'].value = getValueById(myGrid, selectedId,'PLANTID');
	form.elements['workRptWoEndDetailListDTO.plantDesc'].value = getValueById(myGrid, selectedId,'PLANTDESC');
	
	form.elements['workRptWoEndDetailListDTO.plantId'].value = form.elements['workRptWoEndRateCommonDTO.filterPlantId'].value;
	form.elements['workRptWoEndDetailListDTO.month'].value = getValueById(myGrid, selectedId,'MONTH');
	
	goCommonTabPage(form, '', pageId);
}


 function goOpen(){
	goTabPage('workRptWoEndDetailList');
}

 
 
/**
 * Excel Export
 */
function goExcel()
{
	if(checkValidation()) return;
	
   excelServerAction("workRptWoEndRateList", workRptWoEndRateListForm);
}


//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workRptWoEndRateList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="workRptWoEndRateCommonDTO.filterPlantId"/>
<html:hidden property="workRptWoEndRateCommonDTO.filterDeptId"/>
<html:hidden property="workRptWoEndDetailListDTO.plantId"/>
<html:hidden property="workRptWoEndDetailListDTO.plantDesc"/>
<html:hidden property="workRptWoEndDetailListDTO.month"/>

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
                <!-- 월 -->
                <div class="field">
                    <label class="check"><bean:message key="LABEL.month"/></label>
                    <div class="calendar_wrap">
                        <div class="input_box input_carendar">
                            <html:text property="workRptWoEndRateCommonDTO.filterStartDate" tabindex="10" />
                            <p class="open_mon_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="workRptWoEndRateCommonDTO.filterEndDate" tabindex="20" />
                            <p class="open_mon_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>
                <!-- 공장 -->
                <div class="field">
                    <label><bean:message key="LABEL.plant"/></label>
                    <div class="input_box">
                        <html:text property="workRptWoEndRateCommonDTO.filterPlantDesc" tabindex="30" />
                        <p class="open_spop">
                            <a>
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


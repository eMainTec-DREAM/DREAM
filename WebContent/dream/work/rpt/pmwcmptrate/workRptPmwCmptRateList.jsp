<%--===========================================================================
주기정비 계획대비 실행 비율 목록
author  js.lee
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.pmwcmptrate.action.WorkRptPmwCmptRateListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 주기정비 계획대비 실행 비율 -->
<title><bean:message key='MENU.PMWCMPTRATE'/></title>
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
    	workRptPmwCmptRateListForm.elements['workRptPmwCmptRateCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	workRptPmwCmptRateListForm.elements['workRptPmwCmptRateCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
	//월
	workRptPmwCmptRateListForm.elements['workRptPmwCmptRateCommonDTO.filterStartDate'].value = getMinusMonth(-6);
	workRptPmwCmptRateListForm.elements['workRptPmwCmptRateCommonDTO.filterEndDate'].value = getMinusMonth(0);
	
    //공장 
    plantAc = new autoC({"workRptPmwCmptRateCommonDTO.filterPlantDesc":"description"});
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    })
    plantAc.setTable("TAPLANT");
    plantAc.setAcResultMap({
        "workRptPmwCmptRateCommonDTO.filterPlantId":"plant"
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
    
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
    	goOpen(rowId);
    });
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
        return sortColumn("workRptPmwCmptRateList", this, workRptPmwCmptRateListForm, "", ind, direction);
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
    var url = contextPath + "/workRptPmwCmptRateList.do";
    workRptPmwCmptRateListForm.elements['strutsAction'].value = '<%=WorkRptPmwCmptRateListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workRptPmwCmptRateListForm), "", "Y");
}

function goSearch()
{
    if(checkValidation()) return;
    
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
	var form = document.workRptPmwCmptRateListForm;
	 
	form.elements['workRptPmwCmptDetailDTO.yyyymm'].value = getValueById(myGrid, selectedId,'YYYYMM');
	form.elements['workRptPmwCmptDetailDTO.plantId'].value = getValueById(myGrid, selectedId,'PLANT');
	form.elements['workRptPmwCmptDetailDTO.plantDesc'].value = getValueById(myGrid, selectedId,'PLANTDESC');
	goCommonTabPage(form, '', pageId);
}

function goOpen(rowId)
{
	goTabPage("workRptPmwCmptDetailList");
}

/**
 * Excel Export
 */
function goExcel()
{
	if(checkValidation()) return;
	
   excelServerAction("workRptPmwCmptRateList", workRptPmwCmptRateListForm);
}


//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workRptPmwCmptRateList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="workRptPmwCmptRateCommonDTO.filterPlantId"/>
<html:hidden property="workRptPmwCmptDetailDTO.yyyymm"/>
<html:hidden property="workRptPmwCmptDetailDTO.plantId"/>
<html:hidden property="workRptPmwCmptDetailDTO.plantDesc"/>

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
                            <html:text property="workRptPmwCmptRateCommonDTO.filterStartDate" tabindex="10" />
                            <p class="open_mon_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="workRptPmwCmptRateCommonDTO.filterEndDate" tabindex="20" />
                            <p class="open_mon_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>
                <!-- 공장 -->
                <div class="field">
                    <label><bean:message key="LABEL.plant"/></label>
                    <div class="input_box">
                        <html:text property="workRptPmwCmptRateCommonDTO.filterPlantDesc" tabindex="30" />
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


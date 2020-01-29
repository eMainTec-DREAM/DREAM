<%--===========================================================================
author  ghlee
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.energyuseprecon.month.action.EnergyUsePreConMonthListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 에너지 사용현황 -->
<title><bean:message key='MENU.ENERGEUSAGEMON'/></title>
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
	//년도 
	energyUsePreConMonthListForm.elements['energyUsePreConMonthCommonDTO.filterStartDate'].value = getYear()+"-01";
    energyUsePreConMonthListForm.elements['energyUsePreConMonthCommonDTO.filterEndDate'].value = getYear()+"-"+(getMonth());
	//공장 
	energyUsePreConMonthListForm.elements['energyUsePreConMonthCommonDTO.filterPlantId'].value = loginUser.filterPlant;
    energyUsePreConMonthListForm.elements['energyUsePreConMonthCommonDTO.filterPlantDesc'].value = loginUser.filterPlantDesc;
	
    //위치명 
    plantAc = new autoC({"energyUsePreConMonthCommonDTO.filterPlantDesc":"full_desc"});
    plantAc.setTable("TAEQLOC");
    plantAc.setAcResultMap({
        "energyUsePreConMonthCommonDTO.filterPlantId":"eqloc_id"
    });
    plantAc.init();
    
    //항목 
    acSysDesc("energyUsePreConMonthCommonDTO.filterCheckPointDesc","energyUsePreConMonthCommonDTO.filterCheckPointType","CHECK_POINT_TYPE");
    
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
    	energyUsePreConMonthListForm.elements['energyUsePreConMonthCommonDTO.partId'].value = "";
        return sortColumn("energyUsePreConMonthList", this, energyUsePreConMonthListForm, "PLANTID", ind, direction);
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
    var url = contextPath + "/energyUsePreConMonthList.do";
    energyUsePreConMonthListForm.elements['strutsAction'].value = '<%=EnergyUsePreConMonthListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(energyUsePreConMonthListForm), "PLANTID", "Y");
}

function goSearch()
{
    if(checkValidation()) return;
    
    energyUsePreConMonthListForm.elements['energyUsePreConMonthDetailDTO.startDate'].value = energyUsePreConMonthListForm.elements['energyUsePreConMonthCommonDTO.filterStartDate'].value;
    energyUsePreConMonthListForm.elements['energyUsePreConMonthDetailDTO.endDate'].value = energyUsePreConMonthListForm.elements['energyUsePreConMonthCommonDTO.filterEndDate'].value;
	
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
	var form = document.energyUsePreConMonthListForm;
	
	form.elements['energyUsePreConMonthDetailDTO.plantId'].value = getValueById(myGrid, selectedId,'PLANTID');
	form.elements['energyUsePreConMonthDetailDTO.checkPointType'].value = getValueById(myGrid, selectedId,'CHECKPOINTTYPE');
	form.elements['energyUsePreConMonthDetailDTO.plantDesc'].value = getValueById(myGrid, selectedId,'PLANTDESC');
	form.elements['energyUsePreConMonthDetailDTO.checkPointDesc'].value = getValueById(myGrid, selectedId,'CHECKPOINTDESC');
	goCommonTabPage(form, '' , pageId);
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('energyUsePreConMonthDetailList');
	goTabPage('energyUsePreConMonthDetailChart');
}

/**
 * Excel Export
 */
function goExcel()
{
	if(checkValidation()) return;
	
   excelServerAction("energyUsePreConMonthList", energyUsePreConMonthListForm);
}

//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/energyUsePreConMonthList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="energyUsePreConMonthCommonDTO.filterPlantId"/>
<html:hidden property="energyUsePreConMonthCommonDTO.filterCheckPointType"/>

<html:hidden property="energyUsePreConMonthDetailDTO.plantId"/>
<html:hidden property="energyUsePreConMonthDetailDTO.checkPointType"/>
<html:hidden property="energyUsePreConMonthDetailDTO.plantDesc"/>
<html:hidden property="energyUsePreConMonthDetailDTO.checkPointDesc"/>
<html:hidden property="energyUsePreConMonthDetailDTO.startDate"/>
<html:hidden property="energyUsePreConMonthDetailDTO.endDate"/>

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
                            <html:text property="energyUsePreConMonthCommonDTO.filterStartDate" tabindex="10" />
                            <p class="open_mon_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="energyUsePreConMonthCommonDTO.filterEndDate" tabindex="20" />
                            <p class="open_mon_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>
                <!-- 위치 -->
                <div class="field">
                    <label><bean:message key="LABEL.plant"/></label>
                    <div class="input_box">
                        <html:text property="energyUsePreConMonthCommonDTO.filterPlantDesc" tabindex="30" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
	            <!-- 항목 -->
	            <div class="field">
	                <label><bean:message key="LABEL.prompt"/></label>
	                <div class="input_box">
	                    <html:text property="energyUsePreConMonthCommonDTO.filterCheckPointDesc" tabindex="40" />
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


<%--===========================================================================
author  youngjoo38
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.rpt.maintrate.action.WorkRptMaintRateListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 정비지표 -->
<title><bean:message key='MENU.MAINTRATE'/></title>
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
    	workRptMaintRateListForm.elements['workRptMaintRateCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	workRptMaintRateListForm.elements['workRptMaintRateCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
	//년도 
	workRptMaintRateListForm.elements['workRptMaintRateCommonDTO.filterStartDate'].value = getMinusMonth2(new Date(), -1); 
    workRptMaintRateListForm.elements['workRptMaintRateCommonDTO.filterEndDate'].value = getToday();
    
    //위치명 
    plantAc = new autoC({"workRptMaintRateCommonDTO.filterPlantDesc":"description"});
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    })
    plantAc.setTable("TAPLANT");
    plantAc.setAcResultMap({
        "workRptMaintRateCommonDTO.filterPlantId":"plant"
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
    	workRptMaintRateListForm.elements['workRptMaintRateDetailDTO.deptId'].value = "";
        return sortColumn("workRptMaintRateList", this, workRptMaintRateListForm, "deptId", ind, direction);
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
    var url = contextPath + "/workRptMaintRateList.do";
    workRptMaintRateListForm.elements['strutsAction'].value = '<%=WorkRptMaintRateListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workRptMaintRateListForm), "PLANTID", "Y");
}

function goSearch()
{
	if(checkRequireValue("workRptMaintRateCommonDTO.filterStartDate","<bean:message key='LABEL.workDate'/>")) return;
    if(checkRequireValue("workRptMaintRateCommonDTO.filterEndDate","<bean:message key='LABEL.workDate'/>")) return;
    if(checkTwoDate(workRptMaintRateListForm.elements['workRptMaintRateDetailDTO.startDate'],
    		workRptMaintRateListForm.elements['workRptMaintRateDetailDTO.endDate'])) return;
	
    //검색 기간 제한 
    var days = getDayInterval(workRptMaintRateListForm.elements['workRptMaintRateCommonDTO.filterStartDate'].value.replace(/\-/gi, ""),
    		workRptMaintRateListForm.elements['workRptMaintRateCommonDTO.filterEndDate'].value.replace(/\-/gi, ""));
    
    if(days>62){
        alertMessage1("<bean:message key='MESSAGE.MSG0209'/>");
        return;
    }
    
    if(checkValidation()) return;
    
    workRptMaintRateListForm.elements['workRptMaintRateDetailDTO.startDate'].value = workRptMaintRateListForm.elements['workRptMaintRateCommonDTO.filterStartDate'].value;
    workRptMaintRateListForm.elements['workRptMaintRateDetailDTO.endDate'].value = workRptMaintRateListForm.elements['workRptMaintRateCommonDTO.filterEndDate'].value;
    workRptMaintRateListForm.elements['workRptMaintRateDetailDTO.deptId'].value = workRptMaintRateListForm.elements['workRptMaintRateCommonDTO.deptId'].value;
    workRptMaintRateListForm.elements['workRptMaintRateDetailDTO.plantId'].value = workRptMaintRateListForm.elements['workRptMaintRateCommonDTO.filterPlantId'].value;
	
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
	var form = document.workRptMaintRateListForm;
	
	form.elements['workRptMaintRateDetailDTO.deptId'].value = getValueById(myGrid, selectedId,'DEPTID');
	form.elements['workRptMaintRateDetailDTO.plantId'].value = getValueById(myGrid, selectedId,'PLANTID');
	form.elements['workRptMaintRateDetailDTO.deptDesc'].value = getValueById(myGrid, selectedId,'TEAMDESC');
	form.elements['workRptMaintRateDetailDTO.plantDesc'].value = getValueById(myGrid, selectedId,'PLANTDESC');
	goCommonTabPage(form, '' , pageId);
}

/**
 * 상세열기
 */
 function goOpen(){
	goTabPage('workRptMaintRateDetailByPartList');
	goTabPage('workRptMaintRateDetailByTypeList');
	goTabPage('workRptMaintRateDetailDayChart');
}

/**
 * Excel Export
 */
function goExcel()
{
	if(checkValidation()) return;
	
   excelServerAction("workRptMaintRateList", workRptMaintRateListForm);
}

//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workRptMaintRateList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="workRptMaintRateCommonDTO.filterPlantId"/>
<html:hidden property="workRptMaintRateCommonDTO.deptId"/>

<html:hidden property="workRptMaintRateDetailDTO.deptId"/>
<html:hidden property="workRptMaintRateDetailDTO.deptDesc"/>
<html:hidden property="workRptMaintRateDetailDTO.plantId"/>
<html:hidden property="workRptMaintRateDetailDTO.plantDesc"/>
<html:hidden property="workRptMaintRateDetailDTO.startDate"/>
<html:hidden property="workRptMaintRateDetailDTO.endDate"/>

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
                <!-- 일자 -->
                <div class="field">
                    <label class="check"><bean:message key="LABEL.date"/></label>
                    <div class="calendar_wrap">
                        <div class="input_box input_carendar">
                            <html:text property="workRptMaintRateCommonDTO.filterStartDate" tabindex="10" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="workRptMaintRateCommonDTO.filterEndDate" tabindex="20" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>
                <!-- 위치 -->
                <div class="field">
                    <label class="check"><bean:message key="LABEL.plant"/></label>
                    <div class="input_box">
                        <html:text property="workRptMaintRateCommonDTO.filterPlantDesc" tabindex="30" />
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


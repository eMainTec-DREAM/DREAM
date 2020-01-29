<%--===========================================================================
투자비 집행현황 목록
author  cjscjs9
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.invt.rpt.priceexerate.action.InvtRptPriceExeRateListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 작업의뢰 계획대비 실행 비율 -->
<title><bean:message key='MENU.PRICEEXERATE'/></title>
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
    if(loginUser.plant!='null'){
    	invtRptPriceExeRateListForm.elements['invtRptPriceExeRateCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	invtRptPriceExeRateListForm.elements['invtRptPriceExeRateCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
	//월
	invtRptPriceExeRateListForm.elements['invtRptPriceExeRateCommonDTO.filterStartDate'].value = getMinusMonth(-6);
	invtRptPriceExeRateListForm.elements['invtRptPriceExeRateCommonDTO.filterEndDate'].value = getMinusMonth(0);
	
    //공장 
    plantAc = new autoC({"invtRptPriceExeRateCommonDTO.filterPlantDesc":"description"});
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    })
    plantAc.setTable("TAPLANT");
    plantAc.setAcResultMap({
        "invtRptPriceExeRateCommonDTO.filterPlantId":"plant"
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
    	
    });
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
        return sortColumn("invtRptPriceExeRateList", this, invtRptPriceExeRateListForm, "PLANTID", ind, direction);
    });
	myGrid.attachEvent("onRowDblClicked", function(rowId,columnId){
		goWoResultList(rowId,columnId);
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
    var url = contextPath + "/invtRptPriceExeRateList.do";
    invtRptPriceExeRateListForm.elements['strutsAction'].value = '<%=InvtRptPriceExeRateListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(invtRptPriceExeRateListForm), "PLANTID", "Y");
}

function goSearch()
{
	if(checkRequireValue("invtRptPriceExeRateCommonDTO.filterStartDate","<bean:message key='LABEL.workDate'/>")) return;
    if(checkRequireValue("invtRptPriceExeRateCommonDTO.filterEndDate","<bean:message key='LABEL.workDate'/>")) return;
	
    if(checkValidation()) return;
    
    findGridList('Search');
    
}

/**
 * Excel Export
 */
function goExcel()
{
	if(checkValidation()) return;
	
   excelServerAction("invtRptPriceExeRateList", invtRptPriceExeRateListForm);
}

function goWoResultList(rowId,columnId){
	
	if(columnId == 3)
	{
		var yyyyMM = getValueById(myGrid, rowId,'month');
		var startDate = yyyyMM + '01';
		var endDate = plusLastDayOfMonth(yyyyMM);
		var plantId = getValueById(myGrid, rowId,'plantId');
		var plantDesc = getValueById(myGrid, rowId,'plantDesc');
		
		//팝업사이즈
		var popWidth = 1010;
		var popHeight = 640;
	    // pop up이 중앙에 위치하게 한다.
	    var TopPosition  = (screen.height/2 - popHeight/2);
	    var LeftPosition = (screen.width/2 - popWidth/2);
	
	    var pos = "width=" + popWidth + ",height=" + popHeight + "" +
	              ",top=" + TopPosition + "px,left=" + LeftPosition + "px";
	    pos = pos + ",toolbar=no, scrollbars=yes, status=no, resizable=yes";
	    
		var url   = contextPath + "/maWoResultMstrList.do";
		var param = "isDecoratorName=popupPage"+
					"&maWoResultMstrCommonDTO.strutsAction="+
					"&maWoResultMstrCommonDTO.filterResStartDate="+startDate+
					"&maWoResultMstrCommonDTO.filterResEndDate="+endDate+
					"&maWoResultMstrCommonDTO.filterPlantId="+plantId+
					"&maWoResultMstrCommonDTO.filterPlantDesc="+plantDesc;
		//post 전송
		openWindowWithPost(url, "EM_WO_GEN_RATE_LIST_POPUP", param, pos);
	}
}

//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/invtRptPriceExeRateList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="invtRptPriceExeRateCommonDTO.filterPlantId"/>

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
                            <html:text property="invtRptPriceExeRateCommonDTO.filterStartDate" tabindex="10" />
                            <p class="open_mon_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="invtRptPriceExeRateCommonDTO.filterEndDate" tabindex="20" />
                            <p class="open_mon_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>
                <!-- 공장 -->
                <div class="field">
                    <label><bean:message key="LABEL.plant"/></label>
                    <div class="input_box">
                        <html:text property="invtRptPriceExeRateCommonDTO.filterPlantDesc" tabindex="30" />
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


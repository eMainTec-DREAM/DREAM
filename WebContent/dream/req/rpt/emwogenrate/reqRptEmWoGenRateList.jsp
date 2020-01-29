<%--===========================================================================
사후 작업오더 발생률 목록
author  js.lee
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.req.rpt.emwogenrate.action.ReqRptEmWoGenRateListAction" %>
<%@ page import="dream.req.rpt.emwogenrate.action.ReqRptEmWoGenDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 작업의뢰 계획대비 실행 비율 -->
<title><bean:message key='MENU.EMWOGENRATE'/></title>
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
    	reqRptEmWoGenRateListForm.elements['reqRptEmWoGenRateCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	reqRptEmWoGenRateListForm.elements['reqRptEmWoGenRateCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
	//월
	reqRptEmWoGenRateListForm.elements['reqRptEmWoGenRateCommonDTO.filterStartDate'].value = getMinusMonth(-6);
	reqRptEmWoGenRateListForm.elements['reqRptEmWoGenRateCommonDTO.filterEndDate'].value = getMinusMonth(0);
	
    //공장 
    plantAc = new autoC({"reqRptEmWoGenRateCommonDTO.filterPlantDesc":"description"});
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    })
    plantAc.setTable("TAPLANT");
    plantAc.setAcResultMap({
        "reqRptEmWoGenRateCommonDTO.filterPlantId":"plant"
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
        return sortColumn("reqRptEmWoGenRateList", this, reqRptEmWoGenRateListForm, "PLANTID", ind, direction);
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
    var url = contextPath + "/reqRptEmWoGenRateList.do";
    reqRptEmWoGenRateListForm.elements['strutsAction'].value = '<%=ReqRptEmWoGenRateListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(reqRptEmWoGenRateListForm), "PLANTID", "Y");
}

function goSearch()
{
	if(checkRequireValue("reqRptEmWoGenRateCommonDTO.filterStartDate","<bean:message key='LABEL.workDate'/>")) return;
    if(checkRequireValue("reqRptEmWoGenRateCommonDTO.filterEndDate","<bean:message key='LABEL.workDate'/>")) return;
	
    if(checkValidation()) return;
    
    findGridList('Search');
    
}

/**
 * Excel Export
 */
function goExcel()
{
	if(checkValidation()) return;
	
   excelServerAction("reqRptEmWoGenRateList", reqRptEmWoGenRateListForm);
}

function goOpen()
{
	goTabPage('reqRptEmWoGenDetailList');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    reqRptEmWoGenRateListForm.elements['reqRptEmWoGenRateCommonDTO.plantId'].value =  getValueById(myGrid, selectedId,'PLANTID');
    reqRptEmWoGenRateListForm.elements['reqRptEmWoGenRateCommonDTO.plantDesc'].value =  getValueById(myGrid, selectedId,'PLANTDESC');  
    reqRptEmWoGenRateListForm.elements['reqRptEmWoGenRateCommonDTO.month'].value = getValueById(myGrid, selectedId,'MONTH');
    
    reqRptEmWoGenRateListForm.elements['strutsAction'].value = '<%=ReqRptEmWoGenDetailAction.DETAIL_FIND%>';
    openQuickTabPage(FormQueryString(reqRptEmWoGenRateListForm), 'reqRptEmWoGenDetailList'); 
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
	var form = document.reqRptEmWoGenRateListForm;

    form.elements['reqRptEmWoGenRateCommonDTO.plantId'].value =  getValueById(myGrid, selectedId,'PLANTID');  
    form.elements['reqRptEmWoGenRateCommonDTO.plantDesc'].value =  getValueById(myGrid, selectedId,'PLANTDESC');  
    form.elements['reqRptEmWoGenRateCommonDTO.month'].value = getValueById(myGrid, selectedId,'MONTH');
    
	goCommonTabPage(form, '' , pageId);

}

//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/reqRptEmWoGenRateList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="reqRptEmWoGenRateCommonDTO.filterPlantId"/>
<%-- <html:hidden property="reqRptEmWoGenRateCommonDTO.filterPlantId"/> --%>

<html:hidden property="reqRptEmWoGenRateCommonDTO.plantId"/>
<html:hidden property="reqRptEmWoGenRateCommonDTO.plantDesc"/>
<html:hidden property="reqRptEmWoGenRateCommonDTO.month"/>
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
                            <html:text property="reqRptEmWoGenRateCommonDTO.filterStartDate" tabindex="10" />
                            <p class="open_mon_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="reqRptEmWoGenRateCommonDTO.filterEndDate" tabindex="20" />
                            <p class="open_mon_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>
                <!-- 공장 -->
                <div class="field">
                    <label><bean:message key="LABEL.plant"/></label>
                    <div class="input_box">
                        <html:text property="reqRptEmWoGenRateCommonDTO.filterPlantDesc" tabindex="30" />
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

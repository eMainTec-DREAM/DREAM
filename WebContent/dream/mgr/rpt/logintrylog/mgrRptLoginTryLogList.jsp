<%--===========================================================================
Login Log
author  youngjoo38 
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.rpt.logintrylog.action.MgrRptLoginTryLogListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- Login Log -->
<title><bean:message key='MENU.LoginTryLog'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

/** 자동완성 변수 */
var termType;
var empUser;

function loadPage() 
{
	//일자
	mgrRptLoginTryLogListForm.elements['mgrRptLoginTryLogCommonDTO.filterLoginFromDate'].value = getYear()+"-"+getMonth()+"-"+"01";
	mgrRptLoginTryLogListForm.elements['mgrRptLoginTryLogCommonDTO.filterLoginToDate'].value = getToday();	
	
	//Terminal Type 자동완성
	acSysDesc("mgrRptLoginTryLogCommonDTO.filterTerminalTypeDesc","mgrRptLoginTryLogCommonDTO.filterTerminalTypeId","SERVICE_TYPE");
	//사용여부
	acSysDesc("mgrRptLoginTryLogCommonDTO.filterIsSuccessDesc","mgrRptLoginTryLogCommonDTO.filterIsSuccess","IS_USE");

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
     	mgrRptLoginTryLogListForm.elements['mgrRptLoginTryLogCommonDTO.loginTryLogId'].value = "";
         return sortColumn("mgrRptLoginTryLogList", this, mgrRptLoginTryLogListForm, "loginTryLogId", ind, direction);
     });
 	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox");}); 
 	myGrid.init();

 	setHeader(myGrid, "gridbox", "goSearch"); // grid, grid id
 }

function goSearch()
{
    if(checkValidation()) return;
    mgrRptLoginTryLogListForm.elements['mgrRptLoginTryLogCommonDTO.loginTryLogId'].value = "";  // 검색시 Tab 이동Key Clear
    findGridList('Search');
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/mgrRptLoginTryLogList.do";

    mgrRptLoginTryLogListForm.elements['strutsAction'].value = '<%=MgrRptLoginTryLogListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mgrRptLoginTryLogListForm), "loginTryLogId", "Y");
}


/**
 * Excel Export
 */
function goExcel()
{
	mgrRptLoginTryLogListForm.elements['mgrRptLoginTryLogCommonDTO.loginTryLogId'].value = "";	
	excelServerAction("mgrRptLoginTryLogList", mgrRptLoginTryLogListForm );
}

//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/mgrRptLoginTryLogList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="mgrRptLoginTryLogCommonDTO.loginTryLogId"/><!-- Key -->
<html:hidden property="mgrRptLoginTryLogCommonDTO.filterTerminalTypeId"/>
<html:hidden property="mgrRptLoginTryLogCommonDTO.filterIsSuccess"/>

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
                    <label><bean:message key="LABEL.date"/></label>
                    <div class="calendar_wrap">
                        <div class="input_box input_carendar">
                            <html:text property="mgrRptLoginTryLogCommonDTO.filterLoginFromDate" tabindex="10" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="mgrRptLoginTryLogCommonDTO.filterLoginToDate" tabindex="20" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>
                <!-- 사용자 ID -->
                <div class="field">
                    <label><bean:message key="LABEL.UserLoginId"/></label>
                    <div class="input_box">
                        <html:text property="mgrRptLoginTryLogCommonDTO.filterUserId" tabindex="30" />
                    </div>
                </div>
                <!-- Terminal Type -->
                <div class="field">
                    <label><bean:message key="LABEL.TerminalType"/></label>
                    <div class="input_box">
                        <html:text property="mgrRptLoginTryLogCommonDTO.filterTerminalTypeDesc" tabindex="40" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- Terminal -->
                <div class="field">
                    <label><bean:message key="LABEL.terminalNo"/></label>
                    <div class="input_box">
                        <html:text property="mgrRptLoginTryLogCommonDTO.filterTerminalNo" tabindex="40" />
                    </div>
                </div>
                <!-- Terminal Ver-->
                <div class="field">
                    <label><bean:message key="LABEL.TerminalVer"/></label>
                    <div class="input_box">
                        <html:text property="mgrRptLoginTryLogCommonDTO.filterTerminalVer" tabindex="50" />
                    </div>
                </div>                
                <!-- 성공여부 -->
                <div class="field">
                    <label><bean:message key="LABEL.isSuccess"/></label>
                    <div class="input_box">
                        <html:text property="mgrRptLoginTryLogCommonDTO.filterIsSuccessDesc" tabindex="60" />
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


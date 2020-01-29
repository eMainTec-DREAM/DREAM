<%--===========================================================================
화면접속 Log
author  euna0207 
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.rpt.action.MgrRptScrnLogListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 화면접속 Log -->
<title><bean:message key='MENU.AccessLog'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

/** 자동완성 변수 */
var scrnType;
var empUser;
var menuType;

function loadPage() 
{
    //사용자 자동완성
    empUser = new autoC({"mgrRptScrnLogCommonDTO.filterUserDesc":"emp_name"});
	empUser.setTable("TAUSER");
	empUser.setAcResultMap({
        "mgrRptScrnLogCommonDTO.filterUserId":"user_id",
    });
	empUser.init();
	
    //메뉴 자동완성
    menuType = new autoC({"mgrRptScrnLogCommonDTO.filterMenuDesc":"description"});
    menuType.setTable("TAPMENU");
    menuType.setAcResultMap({
        "mgrRptScrnLogCommonDTO.filterMenuId":"menu_id"
    });
    menuType.setAcConditionMap({
 		"service_type":"WEB"
   	   });
    menuType.init();

	//화면 자동완성
    scrnType = new autoC({"mgrRptScrnLogCommonDTO.filterScrnDesc":"file_name"});
    scrnType.setTable("TAPAGE");
    scrnType.setAcResultMap({
        "mgrRptScrnLogCommonDTO.filterScrnId":"page_id"
    });
    scrnType.init();
    
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
 		goOpen(rowId);
 	});
     myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
     	mgrRptScrnLogListForm.elements['mgrRptScrnLogCommonDTO.loginccLogId'].value = "";
         return sortColumn("mgrRptScrnLogList", this, mgrRptScrnLogListForm, "loginccLogId", ind, direction);
     });
 	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox");}); 
 	myGrid.init();

 	setHeader(myGrid, "gridbox", "goSearch"); // grid, grid id
 }

function goSearch()
{
    if(checkValidation()) return;
    mgrRptScrnLogListForm.elements['mgrRptScrnLogCommonDTO.loginccLogId'].value = "";  // 검색시 Tab 이동Key Clear
    findGridList('Search');
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/mgrRptScrnLogList.do";

    mgrRptScrnLogListForm.elements['strutsAction'].value = '<%=MgrRptScrnLogListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mgrRptScrnLogListForm), "loginccLogId", "Y");
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
<html:form action="/mgrRptScrnLogList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="mgrRptScrnLogCommonDTO.loginccLogId"/><!-- Key -->
<html:hidden property="mgrRptScrnLogCommonDTO.filterUserId"/>
<html:hidden property="mgrRptScrnLogCommonDTO.filterMenuId"/>
<html:hidden property="mgrRptScrnLogCommonDTO.filterScrnId"/>

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
                    <label><bean:message key="LABEL.workDate"/></label>
                    <div class="calendar_wrap">
                        <div class="input_box input_carendar">
                            <html:text property="mgrRptScrnLogCommonDTO.filterScrnFromDate" tabindex="10" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="mgrRptScrnLogCommonDTO.filterScrnToDate" tabindex="20" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>
                <!-- 사용자 -->
                <div class="field">
                    <label><bean:message key="LABEL.user"/></label>
                    <div class="input_box">
                        <html:text property="mgrRptScrnLogCommonDTO.filterUserDesc" tabindex="30" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 메뉴 -->
                <div class="field">
                    <label><bean:message key="LABEL.Menu"/></label>
                    <div class="input_box">
                        <html:text property="mgrRptScrnLogCommonDTO.filterMenuDesc" tabindex="40" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 화면 -->
                <div class="field">
                    <label><bean:message key="LABEL.page"/></label>
                    <div class="input_box">
                        <html:text property="mgrRptScrnLogCommonDTO.filterScrnDesc" tabindex="40" />
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


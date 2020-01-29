<%--===========================================================================
Login Log
author  euna0207 
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.rpt.action.MgrRptLoginLogListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- Login Log -->
<title><bean:message key='MENU.LoginLog'/></title>
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
	//Terminal Type 자동완성
	acSysDesc("mgrRptLoginLogCommonDTO.filterTerminalTypeDesc","mgrRptLoginLogCommonDTO.filterTerminalTypeId","SERVICE_TYPE");

    //사용자 자동완성
    empUser = new autoC({"mgrRptLoginLogCommonDTO.filterUserDesc":"emp_name"});
	empUser.setTable("TAUSER");
	empUser.setAcResultMap({
        "mgrRptLoginLogCommonDTO.filterUserId":"user_id",
    });
	empUser.init();
	
	//부서 자동완성
	deptAc = new autoC({"mgrRptLoginLogCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "mgrRptLoginLogCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.init();
	
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
     	mgrRptLoginLogListForm.elements['mgrRptLoginLogCommonDTO.loginccLogId'].value = "";
         return sortColumn("mgrRptLoginLogList", this, mgrRptLoginLogListForm, "loginccLogId", ind, direction);
     });
 	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox");}); 
 	myGrid.init();

 	setHeader(myGrid, "gridbox", "goSearch"); // grid, grid id
 }

function goSearch()
{
    if(checkValidation()) return;
    mgrRptLoginLogListForm.elements['mgrRptLoginLogCommonDTO.loginccLogId'].value = "";  // 검색시 Tab 이동Key Clear
    findGridList('Search');
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/mgrRptLoginLogList.do";

    mgrRptLoginLogListForm.elements['strutsAction'].value = '<%=MgrRptLoginLogListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mgrRptLoginLogListForm), "loginccLogId", "Y");
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
<html:form action="/mgrRptLoginLogList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="mgrRptLoginLogCommonDTO.loginccLogId"/><!-- Key -->
<html:hidden property="mgrRptLoginLogCommonDTO.filterUserId"/>
<html:hidden property="mgrRptLoginLogCommonDTO.filterTerminalTypeId"/>
<html:hidden property="mgrRptLoginLogCommonDTO.filterDeptId"/>

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
                            <html:text property="mgrRptLoginLogCommonDTO.filterLoginFromDate" tabindex="10" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="mgrRptLoginLogCommonDTO.filterLoginToDate" tabindex="20" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>
                <!-- 사용자 -->
                <div class="field">
                    <label><bean:message key="LABEL.user"/></label>
                    <div class="input_box">
                        <html:text property="mgrRptLoginLogCommonDTO.filterUserDesc" tabindex="30" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- Terminal Type -->
                <div class="field">
                    <label><bean:message key="LABEL.TerminalType"/></label>
                    <div class="input_box">
                        <html:text property="mgrRptLoginLogCommonDTO.filterTerminalTypeDesc" tabindex="40" />
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
                        <html:text property="mgrRptLoginLogCommonDTO.filterTerminalNo" tabindex="40" />
                    </div>
                </div>
                <!-- Terminal Ver-->
                <div class="field">
                    <label><bean:message key="LABEL.TerminalVer"/></label>
                    <div class="input_box">
                        <html:text property="mgrRptLoginLogCommonDTO.filterTerminalVer" tabindex="40" />
                    </div>
                </div>  
				<!-- 부서 -->
				<div class="field">
					<label><bean:message key="LABEL.deptName"/></label>
					<div class="input_box">
						<html:text property="mgrRptLoginLogCommonDTO.filterDeptDesc" tabindex="30"/>
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


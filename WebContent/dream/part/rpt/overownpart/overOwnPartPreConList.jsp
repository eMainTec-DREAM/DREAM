<%--===========================================================================
author  youngjoo38
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.rpt.overownpart.action.OverOwnPartPreConListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 투자현황 -->
<title><bean:message key='MENU.OverOwnPartPreCon'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

var deptAc;

function loadPage() 
{
	if(window.name != "LINKED_POPUP")
	{
		//년도 
	    overOwnPartPreConListForm.elements['overOwnPartPreConCommonDTO.filterStandardDate'].value = getToday();
	}
	
    //창고명 
    deptAc = new autoC({"overOwnPartPreConCommonDTO.filterWDesc":"wname"});
    deptAc.setTable("TAWAREHOUSE");
    deptAc.setAcResultMap({
        "overOwnPartPreConCommonDTO.filterWCode":"wcode_id"
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
    	overOwnPartPreConListForm.elements['overOwnPartPreConCommonDTO.partId'].value = "";
        return sortColumn("overOwnPartPreConList", this, overOwnPartPreConListForm, "PARTID", ind, direction);
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
    var url = contextPath + "/overOwnPartPreConList.do";
    overOwnPartPreConListForm.elements['strutsAction'].value = '<%=OverOwnPartPreConListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(overOwnPartPreConListForm), "PARTID", "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_partId)
{
    overOwnPartPreConListForm.elements['overOwnPartPreConCommonDTO.partId'].value = _partId;
    findGridList('ReloadRow');
    overOwnPartPreConListForm.elements['overOwnPartPreConCommonDTO.partId'].value = "";
}

function goSearch()
{
    if(checkValidation()) return;
    overOwnPartPreConListForm.elements['overOwnPartPreConCommonDTO.partId'].value = "";  // 검색시 Tab 이동Key Clear
    findGridList('Search');
}


/**
 * Excel Export
 */
function goExcel()
{
	if(checkValidation()) return;
    overOwnPartPreConListForm.elements['overOwnPartPreConCommonDTO.partId'].value = "";
   excelServerAction("overOwnPartPreConList", overOwnPartPreConListForm);
}

//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/overOwnPartPreConList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="overOwnPartPreConCommonDTO.partId"/><!-- Key -->
<html:hidden property="overOwnPartPreConCommonDTO.filterWCode"/>

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
                <!-- 기준일자 -->
                 <div class="field">
                    <label><bean:message key="LABEL.standardDate"/></label>
                    <div class="calendar">
                        <div class="input_box input_carendar">
                            <html:text property="overOwnPartPreConCommonDTO.filterStandardDate" tabindex="10" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>
                <!-- 창고명 -->
                <div class="field">
                    <label><bean:message key="LABEL.wname"/></label>
                    <div class="input_box">
                        <html:text property="overOwnPartPreConCommonDTO.filterWDesc" tabindex="30" />
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


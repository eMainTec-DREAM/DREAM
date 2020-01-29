<%--===========================================================================
author  youngjoo38
version $Id:$ 
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.doc.notice.action.DocNoticeListAction" %>
<%@ page import="dream.doc.notice.action.DocNoticeDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 공지사항 -->
<title><bean:message key='MENU.NOTICE'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

/** 자동완성 변수 */
var eqLocAc, empAc, equipAc;


function loadPage() 
{	
	if(_isFromNotice != "Y")
	{
		// 날짜 자동완성 (1달전~오늘)
		//docNoticeListForm.elements['docNoticeCommonDTO.filterNoticeFromDate'].value = getMinusMonth2(new Date(), -1); 
		//docNoticeListForm.elements['docNoticeCommonDTO.filterNoticeToDate'].value   = getToday();
	}
    
    // 작성자 자동완성
    empAc = new autoC({"docNoticeCommonDTO.filterWriteByDesc":"emp_name"});
    empAc.setTable("TAEMP");
    empAc.setAcResultMap({
        "docNoticeCommonDTO.filterWriteById":"emp_id"
    });
    empAc.init();
	
	
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
    	docNoticeListForm.elements['docNoticeCommonDTO.noticeId'].value = "";
        return sortColumn("docNoticeCheckList", this, docNoticeListForm, "NOTICEID", ind, direction);
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
    var url = contextPath + "/docNoticeCheckList.do";
    docNoticeListForm.elements['strutsAction'].value = '<%=DocNoticeListAction.LIST_CHECK_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(docNoticeListForm), "NOTICEID" , "Y");

}

function goSearch()
{
    if(checkValidation()) return;
    docNoticeListForm.elements['docNoticeCommonDTO.noticeId'].value = "";  // 검색시 Tab 이동Key Clear
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
    docNoticeListForm.elements['docNoticeCommonDTO.noticeId'].value =  getValueById(myGrid, selectedId,'NOTICEID');  
    
    goCommonTabPage(docNoticeListForm, <%= DocNoticeDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('docNoticeCheckDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    docNoticeListForm.elements['docNoticeCommonDTO.noticeId'].value =  getValueById(myGrid, selectedId,'NOTICEID');  
    docNoticeListForm.elements['strutsAction'].value = '<%=DocNoticeDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(docNoticeListForm), 'docNoticeCheckDetail'); 
} 

/**
 * Excel Export
 */
function goExcel()
{
	docNoticeListForm.elements['docNoticeCommonDTO.noticeId'].value = "";
   	excelServerAction("docNoticeCheckList",docNoticeListForm);
}

//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/docNoticeCheckList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="docNoticeCommonDTO.noticeId"/><!-- Key -->
<html:hidden property="docNoticeCommonDTO.filterWriteById"/>
<html:hidden property="docNoticeCommonDTO.filterReadYn"/>
<html:hidden property="docNoticeCommonDTO.filterEmpId"/>

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
                <!-- 평가일자 -->
                <div class="field">
                    <label><bean:message key="LABEL.noticeDate"/></label>
                    <div class="calendar_wrap">
                        <div class="input_box input_carendar">
                            <html:text property="docNoticeCommonDTO.filterNoticeFromDate" tabindex="10" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="docNoticeCommonDTO.filterNoticeToDate" tabindex="20" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>
                <!-- 공지기한 -->
                <div class="field">
                    <label><bean:message key="LABEL.noticePeriod"/></label>
                    <div class="calendar_wrap">
                        <div class="input_box input_carendar">
                            <html:text property="docNoticeCommonDTO.filterNoticePeriodFromDate" tabindex="30" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="docNoticeCommonDTO.filterNoticePeriodToDate" tabindex="40" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>
                <!-- 작성자 -->
                <div class="field">
                    <label><bean:message key="LABEL.writeBy"/></label>
                    <div class="input_box">
                        <html:text property="docNoticeCommonDTO.filterWriteByDesc" tabindex="50" />
                        <p class="open_spop">
                            <a>
                                <span>조회</span>
                            </a>
                        </p>
                    </div>
                </div>
                <!-- 제목 -->
                <div class="field">
                    <label><bean:message key="LABEL.title"/></label>
                    <div class="input_box">
                        <html:text property="docNoticeCommonDTO.filterTitle" tabindex="60" />
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

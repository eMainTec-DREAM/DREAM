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
	// 날짜 자동완성 (1달전~오늘)
	docNoticeListForm.elements['docNoticeCommonDTO.filterNoticeFromDate'].value = getMinusMonth2(new Date(), -1); 
	docNoticeListForm.elements['docNoticeCommonDTO.filterNoticeToDate'].value   = getToday();
    
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
        return sortColumn("docNoticeList", this, docNoticeListForm, "NOTICEID", ind, direction);
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
    var url = contextPath + "/docNoticeList.do";
    docNoticeListForm.elements['strutsAction'].value = '<%=DocNoticeListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(docNoticeListForm), "NOTICEID" , "Y");

}
/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_noticeId)
{
    docNoticeListForm.elements['docNoticeCommonDTO.noticeId'].value = _noticeId;
    findGridList('ReloadRow');
    docNoticeListForm.elements['docNoticeCommonDTO.noticeId'].value = "";
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
    goTabPage('docNoticeDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    docNoticeListForm.elements['docNoticeCommonDTO.noticeId'].value =  getValueById(myGrid, selectedId,'NOTICEID');  
    docNoticeListForm.elements['strutsAction'].value = '<%=DocNoticeDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(docNoticeListForm), 'docNoticeDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "docNoticeDetail" , "goCreateAction");
    
    // 생성시 설비, 평가등급 기준표 수정 불가
}

function goCreateAction(pageId)
{
    docNoticeListForm.elements['docNoticeCommonDTO.noticeId'].value = "";
    goCommonTabPage(docNoticeListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
	//notice_status 가 C이면 삭제 안됨
	var checkedRows = getCheckedRows(myGrid, 'isDelCheck');
	for(var i = 0; checkedRows.length > i; i++)
	{
		if(getValueById(myGrid, checkedRows[i],'NOTICESTATUS') == "C")
		{
			alertMessage1('( '+getValueById(myGrid, checkedRows[i],'TITLE')+' ) <bean:message key="MESSAGE.MSG0142"/>');
			return;
		}
	}
	
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'NOTICEID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
        alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
        return;
    }

    docNoticeListForm.strutsAction.value = '<%=DocNoticeListAction.LIST_DELETE%>';
    var url = contextPath + "/docNoticeList.do";
    
    $.post(url,FormQueryString(docNoticeListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('docNoticeDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
//    	excelAction(myGrid);
	docNoticeListForm.elements['docNoticeCommonDTO.noticeId'].value = "";
	excelServerAction('docNoticeList', docNoticeListForm);
}

//-->

</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/docNoticeList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="docNoticeCommonDTO.noticeId"/><!-- Key -->
<html:hidden property="docNoticeCommonDTO.filterWriteById"/>

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
                <!-- 작성자 -->
                <div class="field">
                    <label><bean:message key="LABEL.writeBy"/></label>
                    <div class="input_box">
                        <html:text property="docNoticeCommonDTO.filterWriteByDesc" tabindex="30" />
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
                        <html:text property="docNoticeCommonDTO.filterTitle" tabindex="40" />
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


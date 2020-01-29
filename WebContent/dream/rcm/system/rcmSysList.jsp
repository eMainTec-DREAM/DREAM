<%--===========================================================================
System분석 - 목록
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.rcm.system.action.RcmSysListAction" %>
<%@ page import="dream.rcm.system.action.RcmSysDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- System분석 -->
<title><bean:message key='MENU.RCMSYSTEM'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;

function loadPage() 
{
    initGrid();
}

function initGrid()
{
    myGrid = new dhtmlXGridObject('gridbox');
    myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
    
    myGrid.enableSmartRendering(true,500);
    myGrid.attachEvent("onRowSelect",function(rowId, columnId){
        goOpen();
    });
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	rcmSysListForm.elements['rcmSysCommonDTO.rcmListId'].value = "";
    	return sortColumn("rcmSysList", this, rcmSysListForm, "RCMLISTID", ind, direction);
	});
    myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/rcmSysList.do";
    rcmSysListForm.elements['strutsAction'].value = '<%=RcmSysListAction.RCM_LIST_FIND%>';

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(rcmSysListForm), "RCMLISTID", "Y");
}

function findGridRow(_rcmListId)
{
	// 검색 요청번호와 Tab이동시 요청번호가 다르면 상세에서 입력한것이므로 조회 하지 않는다.
	rcmSysListForm.elements['rcmSysCommonDTO.rcmListId'].value = _rcmListId;
	findGridList('ReloadRow');
	rcmSysListForm.elements['rcmSysCommonDTO.rcmListId'].value = "";
}


/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	rcmSysListForm.elements['rcmSysCommonDTO.rcmListId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search', '<%=RcmSysListAction.RCM_LIST_FIND%>');   
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
   	rcmSysListForm.elements['rcmSysCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'compNo');
   	rcmSysListForm.elements['rcmSysCommonDTO.rcmListId'].value  = getValueById(myGrid, selectedId, 'RCMLISTID');

	goCommonTabPage(rcmSysListForm, <%= RcmSysDetailAction.RCM_DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('rcmSysDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    rcmSysListForm.elements['rcmSysCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'compNo');
   	rcmSysListForm.elements['rcmSysCommonDTO.rcmListId'].value  = getValueById(myGrid, selectedId, 'RCMLISTID');
    rcmSysListForm.elements['strutsAction'].value = '<%=RcmSysDetailAction.RCM_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(rcmSysListForm), 'rcmSysDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "rcmSysDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	rcmSysListForm.elements['rcmSysCommonDTO.rcmListId'].value = "";
    goCommonTabPage(rcmSysListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'RCMLISTID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    rcmSysListForm.strutsAction.value = '<%=RcmSysListAction.RCM_LIST_DELETE%>';
    var url = contextPath + "/rcmSysList.do";
    
    $.post(url,FormQueryString(rcmSysListForm)+delArray , function(_data){
        afterDelete();
    }); 
}

function afterDelete()
{
    goClose('rcmSysDetail', this);
    // goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
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
<html:form action="/rcmSysList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="rcmSysCommonDTO.compNo"/><!-- Key -->
<html:hidden property="rcmSysCommonDTO.rcmListId"/><!-- Key -->
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
        <div class="article_box" >
            <div class="form_box">
            	<!-- System 분석명 -->
				<div class="field">
	            	<label><bean:message key="LABEL.rcmDesc"/></label>
               	   	<div class="input_box">
               	   		<html:text property="rcmSysCommonDTO.filterRcmDesc" tabindex="10"/>
               	   	</div>
              	</div>
              	<!-- 등록일 -->
				<div class="field">
					<label><bean:message key="LABEL.regDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="rcmSysCommonDTO.filterStartDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="rcmSysCommonDTO.filterEndDate" tabindex="30" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
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
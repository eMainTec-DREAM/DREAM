<%--===========================================================================
Message Transfer List
author  syyang
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.message.action.MgrMessageTransListAction" %>
<%@ page import="dream.mgr.message.action.MgrMessageTransDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- message전송 현황 -->
<title><bean:message key='MENU.MESSAGETRANS'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

function loadPage() 
{
    initGrid();

    // 전송구분
    acSysDesc("mgrMessageTransCommonDTO.filterMethodTypeDesc","mgrMessageTransCommonDTO.filterMethodTypeID","METHOD_TYPE");
    // 상태
    acSysDesc("mgrMessageTransCommonDTO.filterMsgStatusDesc","mgrMessageTransCommonDTO.filterMsgStatusID","MESSAGE_STATUS");
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
    	mgrMessageTransListForm.elements['mgrMessageTransCommonDTO.messageId'].value = "";
    	return sortColumn("mgrMessageTransList", this, mgrMessageTransListForm, "MESSAGEID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/mgrMessageTransList.do";
    mgrMessageTransListForm.elements['strutsAction'].value = '<%=MgrMessageTransListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mgrMessageTransListForm), "MESSAGEID","Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_messageId)
{
	mgrMessageTransListForm.elements['mgrMessageTransCommonDTO.messageId'].value = _messageId;
	findGridList('ReloadRow');
	mgrMessageTransListForm.elements['mgrMessageTransCommonDTO.messageId'].value = "";
}

function goSearch()
{
	mgrMessageTransListForm.elements['mgrMessageTransCommonDTO.messageId'].value = "";	// 검색시 Tab 이동Key Clear
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
	mgrMessageTransListForm.elements['mgrMessageTransCommonDTO.messageId'].value =  getValueById(myGrid, selectedId,'MESSAGEID');  
	goCommonTabPage(mgrMessageTransListForm, <%= MgrMessageTransDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('mgrMessageTransDetail');
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'MESSAGEID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    mgrMessageTransListForm.strutsAction.value = '<%=MgrMessageTransListAction.LIST_DELETE%>';
    var url = contextPath + "/mgrMessageTransList.do";
    
    $.post(url,FormQueryString(mgrMessageTransListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('mgrMessageTransDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	mgrMessageTransListForm.elements['mgrMessageTransCommonDTO.messageId'].value = "";
	excelServerAction("mgrMessageTransList", mgrMessageTransListForm );  
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/mgrMessageTransList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="mgrMessageTransCommonDTO.messageId"/>			<!-- Key -->
<html:hidden property="mgrMessageTransCommonDTO.filterMethodTypeID"/>
<html:hidden property="mgrMessageTransCommonDTO.filterMsgStatusID"/>

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
				<!-- 전송구분 -->
				<div class="field">
					<label><bean:message key="LABEL.transType"/></label>
					<div class="input_box">
						<html:text property="mgrMessageTransCommonDTO.filterMethodTypeDesc" tabindex="10"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 수신자 -->
				<div class="field">
					<label><bean:message key="LABEL.Recipient"/></label>
					<div class="input_box">
						<html:text property="mgrMessageTransCommonDTO.filterReceiversDesc" tabindex="20"/>
					</div>
				</div>
				<!-- 전송일자 -->
				<div class="field">
					<label><bean:message key="LABEL.transDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="mgrMessageTransCommonDTO.filterStartDate" tabindex="30" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="mgrMessageTransCommonDTO.filterEndDate" tabindex="40" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 상태 -->
				<div class="field">
					<label><bean:message key="LABEL.status"/></label>
					<div class="input_box">
						<html:text property="mgrMessageTransCommonDTO.filterMsgStatusDesc" tabindex="50" />
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 생성일자 -->
				<div class="field">
					<label><bean:message key="LABEL.creDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="mgrMessageTransCommonDTO.filterCreStartDate" tabindex="60" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="mgrMessageTransCommonDTO.filterCreEndDate" tabindex="70" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 제목 -->
				<div class="field">
					<label><bean:message key="LABEL.title"/></label>
					<div class="input_box">
						<html:text property="mgrMessageTransCommonDTO.filterDescription" tabindex="80"/>
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
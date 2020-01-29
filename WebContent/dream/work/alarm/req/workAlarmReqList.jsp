<%--===========================================================================
수리요청 접수 - 목록 
author  nhkim8548
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.alarm.req.action.WorkAlarmReqAction" %>
<%@ page import="dream.req.work.action.MaWoReqDetailAction" %>
<%@ page import="common.util.CommonUtil"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%
    User loginUser = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!-- Alarm List -->
<title><bean:message key='TAB.workAlarmReqList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
var myGrid;
var beforePageId = '';
var woReqAc;

function loadPage() 
{
	setForUpdate();

	workAlarmReqForm.elements['workAlarmReqDTO.alarmListId'].value = workAlarmReqForm.elements['workAlarmDTO.alarmListId'].value;
	
	// 기존작업요청
	woReqAc = new autoC({"workAlarmReqDTO.multiDesc":"woReqDesc"});
	woReqAc.setTable("TAWOREQ");
	woReqAc.setAcResultMap({
        "workAlarmReqDTO.multiKey":"woreqid"
    });
	woReqAc.setAcConditionMap({
    	"comp_no" : loginUser.compNo
    });
	woReqAc.setMultiSelect(true);
	woReqAc.init();
	
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
    	workAlarmReqForm.elements['workAlarmReqDTO.alarmReqId'].value = "";
        return sortColumn("workAlarmReqList", this, workAlarmReqForm, "alarmReqId", ind, direction);
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
	var url = contextPath + "/workAlarmReqList.do";
	
	workAlarmReqForm.elements['strutsAction'].value = '<%=WorkAlarmReqAction.LIST_FIND%>';
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workAlarmReqForm), "alarmReqId", "Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_alarmReqId)
{
	workAlarmReqForm.elements['workAlarmReqDTO.alarmReqId'].value = _alarmReqId;
	findGridList('ReloadRow');
	workAlarmReqForm.elements['workAlarmReqDTO.alarmReqId'].value = "";
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	workAlarmReqForm.elements['workAlarmReqDTO.alarmReqId'].value = "";	// 검색시 Tab 이동Key Clear
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
	workAlarmReqForm.elements['maWoReqCommonDTO.woReqId'].value = getValueById(myGrid, selectedId, 'WOREQID');
	
	goCommonTabPage(workAlarmReqForm, <%= MaWoReqDetailAction.DETAIL_INIT %>, pageId, beforePageId);
	
	beforePageId = pageId;
}

/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maWoReqDetail');
}

/**
 * 생성
 */
function goWoreqcreate()
{
	goCreate();
}

/**
 * 생성
 */
function goCreate()
{
	goClose(beforePageId, this);
	// 요청명
	workAlarmReqForm.elements['maWoReqDetailDTO.reqDesc'].value = workAlarmReqForm.elements['workAlarmDTO.alarmMcDesc'].value 
																+ ' ' + workAlarmReqForm.elements['workAlarmDTO.alarmPoint'].value 
																+ ' ' + workAlarmReqForm.elements['workAlarmDTO.alarmType'].value ;
	
	// 요청내용
	workAlarmReqForm.elements['maWoReqDetailDTO.reqRequest'].value	= workAlarmReqForm.elements['workAlarmDTO.alarmName'].value;
	
	// 설비
	workAlarmReqForm.elements['maWoReqDetailDTO.reqEquipId'].value = workAlarmReqForm.elements['workAlarmDTO.equipId'].value;
	workAlarmReqForm.elements['maWoReqDetailDTO.reqEquipDesc'].value = workAlarmReqForm.elements['workAlarmDTO.equipDesc'].value;
	
	// 설비위치
	workAlarmReqForm.elements['maWoReqDetailDTO.reqEqLocId'].value = workAlarmReqForm.elements['workAlarmDTO.eqLocId'].value;
	workAlarmReqForm.elements['maWoReqDetailDTO.reqEqLocDesc'].value = workAlarmReqForm.elements['workAlarmDTO.eqLocDesc'].value;
	
	// 고장발생일자
	var alarmStartTime = workAlarmReqForm.elements['workAlarmDTO.alarmStartTime'].value;
	workAlarmReqForm.elements['maWoReqDetailDTO.eqDnDate'].value = alarmStartTime.substring(0, 8);
	
	// 고장발생시간
	workAlarmReqForm.elements['maWoReqDetailDTO.eqDnTime'].value = alarmStartTime.substring(8, 12);
	
	// 진행상태
	workAlarmReqForm.elements['maWoReqDetailDTO.woReqStatusId'].value = 'REQ';
	
	// Alarm ID
	workAlarmReqForm.elements['maWoReqDetailDTO.alarmListId'].value = workAlarmReqForm.elements['workAlarmDTO.alarmListId'].value;
	
	createValidationCheck(myGrid, "maWoReqDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	workAlarmReqForm.elements['workAlarmReqDTO.woReqId'].value = "";
    goCommonTabPage(workAlarmReqForm, '', pageId);
}

function afterCreate(keyId)
{
	var _arrayData = [];
	_arrayData.push(keyId);
	
	setLovValue(_arrayData, '');
}

function setLovValue(_arrayData, _type)
{
	var woReqId = _arrayData;

	workAlarmReqForm.strutsAction.value = '<%=WorkAlarmReqAction.LIST_ALARM_REQ_INPUT %>';
	workAlarmReqForm.elements['workAlarmReqDTO.woReqId'].value = woReqId;
	
	var url = contextPath + "/workAlarmReqList.do";
	
	$.post(url,FormQueryString(workAlarmReqForm), function(_data){
		goSearch();	
	});
}

function goWoreqselect()
{
	if(""!=beforePageId)
		goClose(beforePageId, this);
	
	woReqAc.openLov();
}

function setAcLovValue(rtnArr, code)
{
	workAlarmReqForm.strutsAction.value = '<%=WorkAlarmReqAction.LIST_WOREQ_LINK%>';
	
	goSaveAll();
}

function goSave(){
	var url = contextPath + "/workAlarmReqList.do";
	
    $.post(url,FormQueryString(workAlarmReqForm), function(_data){
    	afterSave(_data);
    });
}
function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    getTopPage().afterSaveAll(currentPageId);
    
    workAlarmReqForm.elements['workAlarmReqDTO.alarmListId'].value = "";
  
    if(parent.parent.findGridRow){
    	parent.parent.findGridRow(workAlarmReqForm.elements['workAlarmReqDTO.alarmListId'].value);
    }
    if(parent.parent.goTabPage){
    	parent.parent.goTabPage(parent.currentPageId);
    }
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'alarmReqId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
        alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
        return;
    }

    workAlarmReqForm.strutsAction.value = '<%=WorkAlarmReqAction.LIST_DELETE%>';
    var url = contextPath + "/workAlarmReqList.do";
    
    $.post(url,FormQueryString(workAlarmReqForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('maWoReqDetail');
	
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}


/**
 * Excel Export
 */
function goExcel()
{
	workAlarmReqForm.elements['workAlarmReqDTO.alarmReqId'].value = "";
	excelServerAction(currentPageId, workAlarmReqForm );  
}
 
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workAlarmReqList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="workAlarmDTO.alarmListId"/><!-- Key -->
<html:hidden property="workAlarmReqDTO.alarmListId"/><!-- Key -->
<html:hidden property="workAlarmReqDTO.alarmReqId"/><!-- Key -->
<html:hidden property="workAlarmReqDTO.woReqId"/>
<html:hidden property="workAlarmReqDTO.woReqDesc"/>
<html:hidden property="workAlarmReqDTO.multiKey"/><!-- MultiSelect Key -->
<html:hidden property="workAlarmReqDTO.multiDesc"/><!-- MultiSelect Desc -->

<html:hidden property="maWoReqCommonDTO.woReqId"/>

<html:hidden property="maWoReqDetailDTO.reqDesc"/>
<html:hidden property="maWoReqDetailDTO.reqRequest"/>
<html:hidden property="maWoReqDetailDTO.reqEquipId"/>
<html:hidden property="maWoReqDetailDTO.reqEquipDesc"/>
<html:hidden property="maWoReqDetailDTO.reqEqLocId"/>
<html:hidden property="maWoReqDetailDTO.reqEqLocDesc"/>
<html:hidden property="maWoReqDetailDTO.eqDnDate"/>
<html:hidden property="maWoReqDetailDTO.eqDnTime"/>
<html:hidden property="maWoReqDetailDTO.woReqStatusId"/>
<html:hidden property="maWoReqDetailDTO.alarmListId"/>

<html:hidden property="workAlarmDTO.alarmMcDesc"/>
<html:hidden property="workAlarmDTO.alarmPoint"/>
<html:hidden property="workAlarmDTO.alarmType"/>
<html:hidden property="workAlarmDTO.alarmName"/>
<html:hidden property="workAlarmDTO.equipId"/>
<html:hidden property="workAlarmDTO.equipDesc"/>
<html:hidden property="workAlarmDTO.eqLocId"/>
<html:hidden property="workAlarmDTO.eqLocDesc"/>
<html:hidden property="workAlarmDTO.alarmStartTime"/>
	<!-- searchbox 박스 Line -->
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
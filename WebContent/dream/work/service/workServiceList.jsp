<%--===========================================================================
서비스 마스터 목록 페이지
author  cjscjs9
version $Id: workServiceList.jsp,v 1.1 2018/07/30 01:45:27 cjscjs9 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.work.service.action.WorkServiceListAction" %>
<%@ page import="dream.work.service.action.WorkServiceDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 서비스 마스터 -->
<title><bean:message key='MENU.WORKSERVICE'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;
/** 자동완성 변수 */
var empAc;

function loadPage() 
{
    initGrid();

    //사원 자동완성
    empAc = new autoC({"workServiceCommonDTO.filterEmpDesc":"emp_name"});
    empAc.setTable("TAEMP");
    empAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    });
    empAc.setAcResultMap({
        "workServiceCommonDTO.filterEmpId":"emp_id"
    });
    empAc.init();
    
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
    	workServiceListForm.elements['workServiceCommonDTO.serviceId'].value = "";
    	return sortColumn("workServiceList", this, workServiceListForm, "SERVICEID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/workServiceList.do";
    workServiceListForm.elements['strutsAction'].value = '<%=WorkServiceListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(workServiceListForm), "SERVICEID","Y");

}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_serviceId)
{
	workServiceListForm.elements['workServiceCommonDTO.serviceId'].value = _serviceId;
	findGridList('ReloadRow');
	workServiceListForm.elements['workServiceCommonDTO.serviceId'].value = "";
}

function goSearch()
{
	workServiceListForm.elements['workServiceCommonDTO.serviceId'].value = "";	// 검색시 Tab 이동Key Clear
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
	workServiceListForm.elements['workServiceCommonDTO.serviceId'].value =  getValueById(myGrid, selectedId,'SERVICEID');  
	goCommonTabPage(workServiceListForm, <%= WorkServiceDetailAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('workServiceDetail');
}

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "workServiceDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	workServiceListForm.elements['workServiceCommonDTO.serviceId'].value = "";
    goCommonTabPage(workServiceListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'SERVICEID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    workServiceListForm.strutsAction.value = '<%=WorkServiceListAction.LIST_DELETE%>';
    var url = contextPath + "/workServiceList.do";
    
    $.post(url,FormQueryString(workServiceListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('workServiceDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	workServiceListForm.elements['workServiceCommonDTO.serviceId'].value = "";
	excelServerAction("workServiceList", workServiceListForm );  
}

//-->
</script>





</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/workServiceList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="workServiceCommonDTO.serviceId"/><!-- Key -->
<html:hidden property="workServiceCommonDTO.filterEmpId"/>



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
				<!-- 서비스# -->
				<div class="field">
					<label><bean:message key="LABEL.serviceNo"/></label>
					<div class="input_box">
						<html:text property="workServiceCommonDTO.filterServiceNo" tabindex="10"/>
					</div>
				</div>
				<!-- 서비스명 -->
				<div class="field">
					<label><bean:message key="LABEL.serviceName"/></label>
					<div class="input_box">
						<html:text property="workServiceCommonDTO.filterDescription" tabindex="20"/>
					</div>
				</div>
				<!-- 등록일자 -->
				<div class="field">
					<label><bean:message key="LABEL.regiDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="workServiceCommonDTO.filterFromRegDate" tabindex="30" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="workServiceCommonDTO.filterToRegDate" tabindex="40" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 담당자 -->
                <div class="field">
                    <label><bean:message key="LABEL.regId"/></label>
                    <div class="input_box">
                        <html:text property="workServiceCommonDTO.filterEmpDesc" tabindex="50" />
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
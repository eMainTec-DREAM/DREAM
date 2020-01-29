<%--===========================================================================
화면권한설정목록
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.usrgrp.action.MgrUsrGrpPageAuthAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 화면권한설정목록 -->
<title><bean:message key='PAGE.mgrUsrGrpPageAuthList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

/** 자동완성 변수 */
var usrGrpAc;

function loadPage() 
{
    initGrid();
    
    //권한그룹 자동완성
    usrGrpAc = new autoC({"mgrUsrGrpPageAuthDTO.filterGroupName":"group_name"});
    usrGrpAc.setTable("TAUSRGRP");
    usrGrpAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    usrGrpAc.setAcResultMap({
        "mgrUsrGrpPageAuthDTO.filterUsrgrpId":"usrgrp_id"
    });
    usrGrpAc.init();
    
    //서비스구분 자동완성
    acSysDesc("mgrUsrGrpPageAuthDTO.filterServiceTypeDesc","mgrUsrGrpPageAuthDTO.filterServiceType","SERVICE_TYPE");
    
    //권한여부 자동완성
    acSysDesc("mgrUsrGrpPageAuthDTO.filterIsAuth","mgrUsrGrpPageAuthDTO.filterIsAuth","IS_USE");
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
    	mgrUsrGrpPageAuthForm.elements['mgrUsrGrpPageAuthDTO.pageId'].value = "";
    	mgrUsrGrpPageAuthForm.elements['mgrUsrGrpPageAuthDTO.usrgrpId'].value = "";
    	return sortColumn("mgrUsrGrpPageAuthList", this, mgrUsrGrpPageAuthForm, ["PAGEID", "USRGRPID"], ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/mgrUsrGrpPageAuthList.do";
    mgrUsrGrpPageAuthForm.elements['strutsAction'].value = '<%=MgrUsrGrpPageAuthAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mgrUsrGrpPageAuthForm), ["PAGEID", "USRGRPID"],"Y");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_pageId, _usrgrpId)
{
	mgrUsrGrpPageAuthForm.elements['mgrUsrGrpPageAuthDTO.pageId'].value = _pageId;
	mgrUsrGrpPageAuthForm.elements['mgrUsrGrpPageAuthDTO.usrgrpId'].value = _usrgrpId;
	findGridList('ReloadRow');
	mgrUsrGrpPageAuthForm.elements['mgrUsrGrpPageAuthDTO.pageId'].value = "";
	mgrUsrGrpPageAuthForm.elements['mgrUsrGrpPageAuthDTO.usrgrpId'].value = "";
}

function goSearch()
{
	mgrUsrGrpPageAuthForm.elements['mgrUsrGrpPageAuthDTO.pageId'].value = "";
	mgrUsrGrpPageAuthForm.elements['mgrUsrGrpPageAuthDTO.usrgrpId'].value = "";
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
	mgrUsrGrpPageAuthForm.elements['mgrUsrGrpPageAuthDTO.pageId'].value =  getValueById(myGrid, selectedId,'PAGEID');
	mgrUsrGrpPageAuthForm.elements['mgrUsrGrpPageAuthDTO.usrgrpId'].value =  getValueById(myGrid, selectedId,'USRGRPID');
	goCommonTabPage(mgrUsrGrpPageAuthForm, <%= MgrUsrGrpPageAuthAction.DETAIL_INIT %>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('mgrUsrGrpPageAuthDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    mgrUsrGrpPageAuthForm.elements['mgrUsrGrpPageAuthDTO.pageId'].value =  getValueById(myGrid, selectedId,'PAGEID');
	mgrUsrGrpPageAuthForm.elements['mgrUsrGrpPageAuthDTO.usrgrpId'].value =  getValueById(myGrid, selectedId,'USRGRPID');
	mgrUsrGrpPageAuthForm.elements['strutsAction'].value = '<%=MgrUsrGrpPageAuthAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(mgrUsrGrpPageAuthForm), 'mgrUsrGrpPageAuthDetail'); 
}

/**
 * 권한부여
 */
function goInputauth()
{
    var delArray = getSelectedRows(myGrid, 'isDelCheck', 'PAGEID', 'USRGRPID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    mgrUsrGrpPageAuthForm.strutsAction.value = '<%=MgrUsrGrpPageAuthAction.LIST_INPUT_AUTH%>';
    var url = contextPath + "/mgrUsrGrpPageAuthList.do";
    
    $.post(url,FormQueryString(mgrUsrGrpPageAuthForm)+delArray , function(_data){
    	afterInputauth();
    });
}

function afterInputauth()
{
    goClose('mgrUsrGrpPageAuthDetail');
    goSearch();
    alertMessage1('<bean:message key="MESSAGE.MGS0235"/>');
}

/**
 * 권한제거
 */
function goDeleteauth()
{
    var delArray = getSelectedRows(myGrid, 'isDelCheck', 'PAGEID', 'USRGRPID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    mgrUsrGrpPageAuthForm.strutsAction.value = '<%=MgrUsrGrpPageAuthAction.LIST_DELETE_AUTH%>';
    var url = contextPath + "/mgrUsrGrpPageAuthList.do";
    
    $.post(url,FormQueryString(mgrUsrGrpPageAuthForm)+delArray , function(_data){
    	afterDeleteauth();
    });
}

function afterDeleteauth()
{
    goClose('mgrUsrGrpPageAuthDetail');
    goSearch();
    alertMessage1('<bean:message key="MESSAGE.MGS0235"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	mgrUsrGrpPageAuthForm.elements['mgrUsrGrpPageAuthDTO.pageId'].value = "";
	mgrUsrGrpPageAuthForm.elements['mgrUsrGrpPageAuthDTO.usrgrpId'].value = "";
	excelServerAction("mgrUsrGrpPageAuthList", mgrUsrGrpPageAuthForm );  
}

//-->
</script>


</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/mgrUsrGrpPageAuthList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="mgrUsrGrpPageAuthDTO.pageId"/><!-- Key -->
<html:hidden property="mgrUsrGrpPageAuthDTO.usrgrpId"/><!-- Key -->
<html:hidden property="mgrUsrGrpPageAuthDTO.filterUsrgrpId"/>
<html:hidden property="mgrUsrGrpPageAuthDTO.filterServiceType"/>

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
				<!-- 권한명 -->
				<div class="field">
					<label><bean:message key="LABEL.groupName"/></label>
					<div class="input_box">
						<html:text property="mgrUsrGrpPageAuthDTO.filterGroupName" tabindex="10"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 서비스구분 -->
				<div class="field">
					<label><bean:message key="LABEL.serviceType"/></label>
					<div class="input_box">
						<html:text property="mgrUsrGrpPageAuthDTO.filterServiceTypeDesc" tabindex="20"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 화면ID -->
				<div class="field">
					<label><bean:message key="LABEL.fileName"/></label>
					<div class="input_box">
						<html:text property="mgrUsrGrpPageAuthDTO.filterFileName" tabindex="30"/>
					</div>
				</div>
				<!-- 권한 -->
				<div class="field">
					<label><bean:message key="LABEL.auth"/></label>
					<div class="input_box">
						<html:text property="mgrUsrGrpPageAuthDTO.filterIsAuth" tabindex="40"/>
						<p class="open_spop"><a><span>조회</span></a></p>
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
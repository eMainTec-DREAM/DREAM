<%--===========================================================================
표준항목 - 목록
author  kim210017
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="common.tree.action.TreeAction" %>
<%@ page import="dream.work.pm.std.action.MaStdPointHdrListAction" %>
<%@ page import="dream.work.pm.std.action.MaStdPointHdrDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%@ page import="common.bean.MwareConfig"%>
<%
	User user = (User)request.getSession().getAttribute(request.getSession().getId()); 
	String isUsePmRevision = MwareConfig.getIsUsePmRevision();
%>
<html>
<head>
<!-- 표준항목 -->
<title><bean:message key="MENU.PMSTDPOINT"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;
var eqCtgAc;
function loadPage() 
{
	initGrid();
	
	maStdPointHdrListForm.elements['maStdPointCommonDTO.isLastVersion'].value = "Y";
	valSysDir('maStdPointCommonDTO.isLastVersion', 'maStdPointCommonDTO.isLastVersion', 'IS_USE', true);
	
    //설비종류 자동완성
    eqCtgAc = new autoC({"maStdPointCommonDTO.filterEqCtgDesc":"full_desc"});
    eqCtgAc.setTable("TAEQCTG");
    eqCtgAc.setAcConditionMap({
  	   "comp_no":loginUser.compNo
  	   });
    eqCtgAc.setAcResultMap({
        "maStdPointCommonDTO.filterEqCtgId":"eqctg_id"
    });
    eqCtgAc.init();
    
	acSysDesc("maStdPointCommonDTO.isLastVersion","maStdPointCommonDTO.isLastVersion","IS_USE",true);
    acSysDesc("maStdPointCommonDTO.revisionStatusDesc","maStdPointCommonDTO.revisionStatus","REVISION_STATUS",true);
}

/*작업종류 선택후 실행*/
function afterAutoCmpt(code)
{
		if(code=="maStdPointCommonDTO.filterWoTypeDesc")
		{
		 	var listType = maStdPointHdrListForm.elements['maStdPointCommonDTO.filterWoTypeId'].value+"_TYPE";
		    //작업종류 AC
		    acSysDesc("maStdPointCommonDTO.filterPmTypeDesc","maStdPointCommonDTO.filterPmTypeId",listType);
		}
}


/*작업종류 선택후 실행*/
function afterSetValue(lovType)
{
	if(lovType=="WO_TYPE")
	{
	 	var listType = maStdPointHdrListForm.elements['maStdPointCommonDTO.filterWoTypeId'].value+"_TYPE";
	    //작업종류 AC
	    acSysDesc("maStdPointCommonDTO.filterPmTypeDesc","maStdPointCommonDTO.filterPmTypeId",listType);
	}
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
    	maStdPointHdrListForm.elements['maStdPointCommonDTO.stWrkId'].value = "";
        return sortColumn("maStdPointHdrList", this, maStdPointHdrListForm, "STWRKID", ind, direction);
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
    var url = contextPath + "/maStdPointHdrList.do";
    maStdPointHdrListForm.elements['strutsAction'].value = '<%=MaStdPointHdrListAction.STD_HDR_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maStdPointHdrListForm), "STWRKID", "Y");

}

/**
 * 검색버튼 클릭시 호출
 */
function goSearch()
{
	maStdPointHdrListForm.elements['maStdPointCommonDTO.stWrkId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search', '<%=MaStdPointHdrListAction.STD_HDR_LIST_FIND%>');   
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
    maStdPointHdrListForm.elements['maStdPointCommonDTO.stWrkId'].value = getValueById(myGrid, selectedId, 'STWRKID');

    goCommonTabPage(maStdPointHdrListForm, <%=MaStdPointHdrDetailAction.STD_HDR_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_stWrkId)
{
	maStdPointHdrListForm.elements['maStdPointCommonDTO.stWrkId'].value = _stWrkId;
	findGridList('ReloadRow');
	maStdPointHdrListForm.elements['maStdPointCommonDTO.stWrkId'].value = "";
}
/**
 * 상세 열기
 */
function goOpen()
{
    goTabPage('maStdPointHdrDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maStdPointHdrListForm.elements['maStdPointCommonDTO.stWrkId'].value = getValueById(myGrid, selectedId, 'STWRKID');
    maStdPointHdrListForm.elements['strutsAction'].value = '<%=MaStdPointHdrDetailAction.STD_HDR_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maStdPointHdrListForm), 'maStdPointHdrDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
    createValidationCheck(myGrid, "maStdPointHdrDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
    maStdPointHdrListForm.elements['maStdPointCommonDTO.stWrkId'].value = "";
    
    //제개정 사용여부
	if("<%=isUsePmRevision%>"=="N"){
		goCommonTabPage(maStdPointHdrListForm, '', pageId);
	}else{
		var param = "strutsAction=1001";
		param += "&" + "popupWidth=800";
		param += "&" + "commRevCommonDTO.revisionObjType="+"STWRK";
		param += "&" + "commRevCommonDTO.param="+pageId;
			
		openLayerPopup("commRevRegislate", param);
	}
}

function afterCreate(_stWrkId, pageId)
{
	findGridRow(_stWrkId);
	 
	var form = document.maStdPointHdrListForm;

	form.elements['maStdPointCommonDTO.stWrkId'].value = _stWrkId;
	goCommonTabPage(form, '<%=MaStdPointHdrDetailAction.STD_HDR_DETAIL_INIT%>', pageId);
}

/**
 * Excel Export
 */
function goExcel()
{
	maStdPointHdrListForm.elements['maStdPointCommonDTO.stWrkId'].value = "";
    excelServerAction("maStdPointHdrList",maStdPointHdrListForm);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'STWRKID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

    maStdPointHdrListForm.strutsAction.value = '<%=MaStdPointHdrListAction.STD_HDR_LIST_DELETE%>';
    var url = contextPath + "/maStdPointHdrList.do";
    
    $.post(url,FormQueryString(maStdPointHdrListForm)+delArray , function(_data){
        afterDelete();
    }); 
}

function afterDelete()
{
    goClose('maStdPointHdrDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maStdPointHdrList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maStdPointCommonDTO.stWrkId"/><!-- Key -->
<html:hidden property="maStdPointCommonDTO.filterEqCtgId"/>
<html:hidden property="maStdPointCommonDTO.revisionStatus"/>
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
				<div class="b_line"></div> 
				<div class="fb_group1">
					 
				</div>
			</div>
        </div><!--sheader_box end-->
        <div class="article_box">
            <div class="form_box">
				<div class="field">
	               <label><bean:message key="LABEL.stWrkDesc"/></label>
               	   <div class="input_box">
               	   		<html:text property="maStdPointCommonDTO.filterStWrkDesc" tabindex="30"/>
               	   </div>
				</div>
				<div class="field">
	               <label><bean:message key="LABEL.stdWrkNo"/></label>
               	   <div class="input_box">
               	   		<html:text property="maStdPointCommonDTO.filterStWrkNo" tabindex="30"/>
               	   </div>
				</div>
				<!-- 종류 -->
				<div class="field">
					<label><bean:message key="LABEL.type"/></label>
					<div class="input_box">
						<html:text property="maStdPointCommonDTO.filterEqCtgDesc" tabindex="70" />
						<p class="open_spop">
							<a>
								<span>조회</span>
							</a>
						</p>
					</div>
				</div>
				<!-- 최신 Version 여부  -->
				<div class="field">
					<label><bean:message key="LABEL.isLastVersion"/></label>
					<div class="input_box">
						<html:text property="maStdPointCommonDTO.isLastVersion" tabindex="150"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!--  Revision 상태  -->
				<div class="field">
					<label><bean:message key="LABEL.revisionStatus"/></label>
					<div class="input_box">
						<html:text property="maStdPointCommonDTO.revisionStatusDesc" tabindex="150"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- revision 번호  -->
				<div class="field">
					<label><bean:message key="LABEL.revNo"/></label>
					<div class="input_box">
						<html:text property="maStdPointCommonDTO.revNo" tabindex="150"/>
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
				<div class="b_line"></div> 
				<div class="fb_group1">
					 
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
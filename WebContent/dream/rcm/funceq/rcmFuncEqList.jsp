<%--===========================================================================
질의 - 목록
author  kim21017
version $Id: rcmFuncEqList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.rcm.funceq.action.RcmFuncEqListAction" %>
<%@ page import="dream.rcm.funceq.action.RcmFuncEqDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 제목-->
<title><bean:message key="MENU.RCMFUNCEQ"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//

var myGrid;

var rcmNameAc;
function loadPage() 
{
    initGrid();
    
    // System 분석명
	rcmNameAc = new autoC({"rcmFuncEqCommonDTO.filterRcmDesc":"description"});
	rcmNameAc.setAcDisplay("DESCRIPTION");
	rcmNameAc.setTable("TARCMLIST");
	rcmNameAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
	rcmNameAc.setAcResultMap({
        "rcmFuncEqCommonDTO.filterRcmListId":"rcmlist_id"
    });
	rcmNameAc.init();
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/"/>');
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		rcmFuncEqListForm.elements['rcmFuncEqCommonDTO.rcmFfailId'].value = "";
	   	return sortColumn("rcmFuncEqList", this, rcmFuncEqListForm, "RCMFFAILID", ind, direction);
	});
	myGrid.enableSmartRendering(true,20);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	
	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/rcmFuncEqList.do";
    rcmFuncEqListForm.elements['strutsAction'].value = '<%=RcmFuncEqListAction.QNA_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(rcmFuncEqListForm), "RCMFFAILID", "Y");

}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	rcmFuncEqListForm.elements['rcmFuncEqCommonDTO.rcmFfailId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search', '<%=RcmFuncEqListAction.QNA_LIST_FIND%>');   
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
	rcmFuncEqListForm.elements['rcmFuncEqCommonDTO.rcmFfailId'].value = getValueById(myGrid, selectedId,'RCMFFAILID');
	
	goCommonTabPage(rcmFuncEqListForm, <%= RcmFuncEqDetailAction.QNA_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_rcmFfailId)
{
	rcmFuncEqListForm.elements['rcmFuncEqCommonDTO.rcmFfailId'].value = _rcmFfailId;
	findGridList('ReloadRow');
	rcmFuncEqListForm.elements['rcmFuncEqCommonDTO.rcmFfailId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('rcmFuncEqDetail');	
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    rcmFuncEqListForm.elements['rcmFuncEqCommonDTO.rcmFfailId'].value = getValueById(myGrid, selectedId,'RCMFFAILID');
    rcmFuncEqListForm.elements['strutsAction'].value = '<%=RcmFuncEqDetailAction.QNA_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(rcmFuncEqListForm), 'rcmFuncEqDetail'); 
} 

 /**
  * 생성
  */
function goCreate()
{
	createValidationCheck(myGrid, "rcmFuncEqDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	rcmFuncEqListForm.elements['rcmFuncEqCommonDTO.rcmFfailId'].value = "";
	goCommonTabPage(rcmFuncEqListForm, '', pageId);
}


/**
 * Excel Export
 */
function goExcel()
{
    excelAction(myGrid);
}

/**
 * 삭제
 */
function goDelete(){
	
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'RCMFFAILID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	rcmFuncEqListForm.strutsAction.value = '<%=RcmFuncEqListAction.QNA_LIST_DELETE%>';
	var url = contextPath + "/rcmFuncEqList.do";
	
    $.post(url,FormQueryString(rcmFuncEqListForm)+delArray , function(_data){
    	afterDelete();
    });
}

  
function afterDelete(){
	goClose('rcmFuncEqDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
 }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/rcmFuncEqList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="rcmFuncEqCommonDTO.rcmFfailId"/>
<html:hidden property="rcmFuncEqCommonDTO.filterRcmListId"/>
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
						<html:text property="rcmFuncEqCommonDTO.filterRcmDesc" tabindex="60"/>
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
		<div class="article_box" >
			<div class="grid_area">
				<div id="gridbox" style="width:100%;height:270px; background-color:white;"></div>		
			</div>			
		</div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>


<%--===========================================================================
시스템 코드 - 목록
author  kim21017
version $Id: maCdSysList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.comp.cdsys.action.MaCdSysListAction" %>
<%@ page import="dream.consult.comp.cdsys.action.MaCdSysDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 시스템코드-->
<title><bean:message key="MENU.CDSYS"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="consultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//

var myGrid;
function loadPage() 
{
    initGrid();

    //시스템코드여부
    acSysDesc("maCdSysCommonDTO.filterSyscode","maCdSysCommonDTO.filterSyscode","IS_USE");
    //사용여부
    acSysDesc("maCdSysCommonDTO.filterIsuse","maCdSysCommonDTO.filterIsuse","IS_USE");
    
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
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
    var url = contextPath + "/maCdSysList.do";
    maCdSysListForm.elements['strutsAction'].value = '<%=MaCdSysListAction.LISTTYPE_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maCdSysListForm), "CDSYSM_ID");

}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	maCdSysListForm.elements['maCdSysCommonDTO.cdSysMId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search', '<%=MaCdSysListAction.LISTTYPE_LIST_FIND%>');   
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
	maCdSysListForm.elements['maCdSysCommonDTO.cdSysMId'].value = getValueById(myGrid, selectedId,'CDSYSM_ID');
	
	goCommonTabPage(maCdSysListForm, <%= MaCdSysDetailAction.LISTTYPE_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_cdSysMId)
{
	maCdSysListForm.elements['maCdSysCommonDTO.cdSysMId'].value = _cdSysMId;
	findGridList('ReloadRow');
	maCdSysListForm.elements['maCdSysCommonDTO.cdSysMId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maCdSysDetail');	
}

 /**
  * 생성
  */
function goCreate()
{
	createValidationCheck(myGrid, "maCdSysDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maCdSysListForm.elements['maCdSysCommonDTO.cdSysMId'].value = "";
	goCommonTabPage(maCdSysListForm, '', pageId);
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
	
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'CDSYSM_ID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maCdSysListForm.strutsAction.value = '<%=MaCdSysListAction.LISTTYPE_LIST_DELETE%>';
	var url = contextPath + "/maCdSysList.do";
	
    $.post(url,FormQueryString(maCdSysListForm)+delArray , function(_data){
    	afterDelete();
    });
}

  
function afterDelete(){
	goClose('maCdSysDetail');
	//goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
 }
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maCdSysList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maCdSysCommonDTO.cdSysMId"/><!-- Key -->
<html:hidden property="maCdSysCommonDTO.paramListTypeCateg"/>
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
				<div class="field">
					<label><bean:message key="LABEL.codeType"/></label>
					<div class="input_box">
						<html:text property="maCdSysCommonDTO.filterListType" tabindex="10"/>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.codeDesc"/></label>
					<div class="input_box">
						<html:text property="maCdSysCommonDTO.filterListTypeDesc" tabindex="20"/>
					</div>
				</div>
				
				<div class="field">
					<label><bean:message key="LABEL.codeCateg"/></label>
					<div class="input_box">
						<html:text property="maCdSysCommonDTO.filterListTypeCateg" tabindex="20"/>
					</div>
				</div>
				
				<div class="field">
					<label>시스템코드</label>
					<div class="input_box">
						<html:text property="maCdSysCommonDTO.filterSyscode" tabindex="30"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				
				<div class="field">
					<label>사용여부</label>
					<div class="input_box">
						<html:text property="maCdSysCommonDTO.filterIsuse" tabindex="40"/>
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
				<div id="gridbox" style="height:270px; background-color:white;"></div>		
			</div>			
		</div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>


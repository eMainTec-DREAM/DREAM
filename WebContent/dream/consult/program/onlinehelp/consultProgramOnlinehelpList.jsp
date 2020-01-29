<%--===========================================================================
도움말 - 목록
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.consult.program.onlinehelp.action.ConsultProgramOnlinehelpListAction" %>
<%@ page import="dream.consult.program.onlinehelp.action.ConsultProgramOnlinehelpDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 도움말 -->
<title><bean:message key='MENU.ONLINEHELP'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="consultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid,menuAc;
function loadPage() 
{
	
	//메뉴 (pop up)
    menuAc = new autoC({"consultProgramOnlinehelpCommonDTO.filterMenuDesc":"description"});
    menuAc.setTable("TACMENU");
    menuAc.setAcResultMap({
        "consultProgramOnlinehelpCommonDTO.filterMenuId":"MENU_ID"
    });
    menuAc.init();
	
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
		consultProgramOnlinehelpListForm.elements['consultProgramOnlinehelpCommonDTO.onlineHelpId'].value = "";
    	return sortColumn("consultProgramOnlinehelpList", this, consultProgramOnlinehelpListForm, "ONLINEHELPID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
	
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/consultProgramOnlinehelpList.do";

    consultProgramOnlinehelpListForm.elements['strutsAction'].value = '<%=ConsultProgramOnlinehelpListAction.HELP_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(consultProgramOnlinehelpListForm), "ONLINEHELPID", "Y");

}


/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	consultProgramOnlinehelpListForm.elements['consultProgramOnlinehelpCommonDTO.onlineHelpId'].value = "";	// 검색시 Tab 이동Key Clear
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
    consultProgramOnlinehelpListForm.elements['consultProgramOnlinehelpCommonDTO.onlineHelpId'].value = getValueById(myGrid, selectedId,'ONLINEHELPID');
    goCommonTabPage(consultProgramOnlinehelpListForm, <%= ConsultProgramOnlinehelpDetailAction.HELP_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_onlineHelpId)
{
	consultProgramOnlinehelpListForm.elements['consultProgramOnlinehelpCommonDTO.onlineHelpId'].value = _onlineHelpId;
	findGridList('ReloadRow');
	consultProgramOnlinehelpListForm.elements['consultProgramOnlinehelpCommonDTO.onlineHelpId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('consultProgramOnlinehelpDetail');	
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    consultProgramOnlinehelpListForm.elements['consultProgramOnlinehelpCommonDTO.onlineHelpId'].value = getValueById(myGrid, selectedId,'ONLINEHELPID');
    consultProgramOnlinehelpListForm.elements['strutsAction'].value = '<%=ConsultProgramOnlinehelpDetailAction.HELP_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(consultProgramOnlinehelpListForm), 'consultProgramOnlinehelpDetail'); 
} 

 /**
  * 생성
  */
function goCreate()
{
	createValidationCheck(myGrid, "consultProgramOnlinehelpDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	consultProgramOnlinehelpListForm.elements['consultProgramOnlinehelpCommonDTO.onlineHelpId'].value = "";
	goCommonTabPage(consultProgramOnlinehelpListForm, '', pageId);
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
function goDelete()
{
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'ONLINEHELPID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	consultProgramOnlinehelpListForm.strutsAction.value = '<%=ConsultProgramOnlinehelpListAction.HELP_LIST_DELETE%>';
	var url = contextPath + "/consultProgramOnlinehelpList.do";
	$.post(url,FormQueryString(consultProgramOnlinehelpListForm)+delArray , function(_data){
    	afterDelete();
    });
}
 
function afterDelete()
{
	goClose('consultProgramOnlinehelpDetail');
	// 	goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}
  
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/consultProgramOnlinehelpList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="consultProgramOnlinehelpCommonDTO.onlineHelpId"/><!-- Key -->
<html:hidden property="consultProgramOnlinehelpCommonDTO.compNo"/>
<html:hidden property="consultProgramOnlinehelpCommonDTO.filterMenuId"/>
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
                <!-- 화면 ID -->
                <div class="field">
	                <label><bean:message key="LABEL.fileName"/></label>
               	    <div class="input_box">
               	    	<html:text property="consultProgramOnlinehelpCommonDTO.filterFileName" tabindex="10"/>
               	    </div>
              	</div>
             	<!-- Title -->
             	<div class="field">
					<label><bean:message key="LABEL.title"/></label>
					<div class="input_box">
						<html:text property="consultProgramOnlinehelpCommonDTO.filterTitle" tabindex="30"/>
					</div>
			    </div>
			    <!-- Contents -->
           	    <div class="field">
            	    <label><bean:message key="LABEL.contents"/></label>
            	    <div class="input_box">
            	    	<html:text property="consultProgramOnlinehelpCommonDTO.filterContents" tabindex="40"/>
            	    </div>
           	    </div>
           	    <!-- 매뉴 -->
           	    <div class="field">
	                <label><bean:message key="LABEL.menuName"/></label>
               	    <div class="input_box">
          	    		<html:text property="consultProgramOnlinehelpCommonDTO.filterMenuDesc" tabindex="50"/>
          	    		<p class="open_spop"><a></a></p>
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
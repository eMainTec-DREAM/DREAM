<%--===========================================================================
To Be Process List
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.mgr.tobeprocess.action.MgrToBeProcessListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- TO-BE 프로세스 -->
<title><bean:message key='MENU.TOBEPROC'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
//그리드명
var myGrid;

/** 자동완성 변수 */


function loadPage() 
{
    initGrid();
}

function initGrid()
{ 
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	mgrToBeProcessListForm.elements['mgrToBeProcessCommonDTO.toBeProcessId'].value = "";
    	return sortColumn("mgrToBeProcessList", this, mgrToBeProcessListForm, "WFLISTID", ind, direction);
	});
	myGrid.attachEvent("onRowDblClicked", function(id,ind){
		goOpenProcess();
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/mgrToBeProcessList.do";
    mgrToBeProcessListForm.elements['strutsAction'].value = '<%=MgrToBeProcessListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(mgrToBeProcessListForm), "WFLISTID","Y");

}

function goSearch()
{
	mgrToBeProcessListForm.elements['mgrToBeProcessCommonDTO.toBeProcessId'].value = "";	// 검색시 Tab 이동Key Clear
	findGridList('Search');
}

/**
 * Excel Export
 */
function goExcel()
{
	mgrToBeProcessListForm.elements['mgrToBeProcessCommonDTO.toBeProcessId'].value = "";
	excelServerAction("mgrToBeProcessList", mgrToBeProcessListForm );  
}

// To Be Process Flow 열기
function goOpenProcess()
{
	var selectedId = myGrid.getSelectedRowId();
	var fileName = getValueById(myGrid, selectedId,'FILENAME');
	var url = contextPath + "/" + fileName ;
	
	window.open(url, "processPop", "width=800, height=850, resizable=yes"); 
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/mgrToBeProcessList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="mgrToBeProcessCommonDTO.toBeProcessId"/><!-- Key -->
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
				<!-- Process 명 -->
				<div class="field">
					<label><bean:message key="LABEL.processName"/></label>
					<div class="input_box">
						<html:text property="mgrToBeProcessCommonDTO.filterToBeProcessDesc" tabindex="10"/>
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
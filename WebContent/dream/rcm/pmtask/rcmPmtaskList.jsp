<%--===========================================================================
목록
author  kim21017
version $Id: rcmPmtaskList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.rcm.pmtask.action.RcmPmtaskListAction" %>
<%@ page import="dream.rcm.pmtask.action.RcmPmtaskDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 제목-->
<title><bean:message key="MENU.RCMPMTASK"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//

var myGrid, rcmNameAc;

function loadPage() 
{
    initGrid();
    
    rcmNameAc = new autoC({"rcmPmtaskCommonDTO.filterRcmlistDesc":"description"});
	rcmNameAc.setTable("TARCMLIST");
	rcmNameAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
	rcmNameAc.setAcResultMap({
          "rcmPmtaskCommonDTO.filterRcmlistId":"rcmlist_id"
    });
	rcmNameAc.init();
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,20);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	clearKey();
    	return sortColumn("rcmPmtaskList", this, rcmPmtaskListForm, ["rcmlistId", "rcmfuncId","rcmffailId", "rcmeqId","rcmeqasmbId","rcmfmeaId" ], ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	
	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/rcmPmtaskList.do";
    rcmPmtaskListForm.elements['strutsAction'].value = '<%=RcmPmtaskListAction.PMTASK_LIST_FIND%>';
     doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(rcmPmtaskListForm), ["rcmlistId", "rcmfuncId","rcmffailId", "rcmeqId","rcmeqasmbId","rcmfmeaId" ], "Y");

}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	clearKey();
	
    findGridList('Search', '<%=RcmPmtaskListAction.PMTASK_LIST_FIND%>');   
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
	rcmPmtaskListForm.elements['rcmPmtaskCommonDTO.rcmlistId'].value = getValueById(myGrid, selectedId,'rcmlistId');
	rcmPmtaskListForm.elements['rcmPmtaskCommonDTO.rcmfuncId'].value = getValueById(myGrid, selectedId,'rcmfuncId');
	rcmPmtaskListForm.elements['rcmPmtaskCommonDTO.rcmffailId'].value = getValueById(myGrid, selectedId,'rcmffailId');
	rcmPmtaskListForm.elements['rcmPmtaskCommonDTO.rcmeqId'].value = getValueById(myGrid, selectedId,'rcmeqId');
	rcmPmtaskListForm.elements['rcmPmtaskCommonDTO.rcmeqasmbId'].value = getValueById(myGrid, selectedId,'rcmeqasmbId');
	rcmPmtaskListForm.elements['rcmPmtaskCommonDTO.rcmfmeaId'].value = getValueById(myGrid, selectedId,'rcmfmeaId');
	rcmPmtaskListForm.elements['rcmPmtaskCommonDTO.rcmpmtaskId'].value = getValueById(myGrid, selectedId,'rcmpmtaskId');
	
	goCommonTabPage(rcmPmtaskListForm, <%= RcmPmtaskDetailAction.PMTASK_DETAIL_INIT %>, pageId);
}

function clearKey()
{
	rcmPmtaskListForm.elements['rcmPmtaskCommonDTO.rcmlistId'].value = "";
	rcmPmtaskListForm.elements['rcmPmtaskCommonDTO.rcmfuncId'].value = "";
	rcmPmtaskListForm.elements['rcmPmtaskCommonDTO.rcmffailId'].value = "";
	rcmPmtaskListForm.elements['rcmPmtaskCommonDTO.rcmeqId'].value = "";
	rcmPmtaskListForm.elements['rcmPmtaskCommonDTO.rcmeqasmbId'].value = "";
	rcmPmtaskListForm.elements['rcmPmtaskCommonDTO.rcmfmeaId'].value = "";
	rcmPmtaskListForm.elements['rcmPmtaskCommonDTO.rcmpmtaskId'].value = "";
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_arrayVal)
{
	clearKey();
	
	rcmPmtaskListForm.elements['rcmPmtaskCommonDTO.rcmlistId'].value = _arrayVal[0];
	rcmPmtaskListForm.elements['rcmPmtaskCommonDTO.rcmfuncId'].value = _arrayVal[1];
	rcmPmtaskListForm.elements['rcmPmtaskCommonDTO.rcmffailId'].value = _arrayVal[2];
	rcmPmtaskListForm.elements['rcmPmtaskCommonDTO.rcmeqId'].value = _arrayVal[3];
	rcmPmtaskListForm.elements['rcmPmtaskCommonDTO.rcmeqasmbId'].value = _arrayVal[4];
	rcmPmtaskListForm.elements['rcmPmtaskCommonDTO.rcmfmeaId'].value = _arrayVal[5];
	
	findGridList('ReloadRow');

}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('rcmPmtaskDetail');	
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    rcmPmtaskListForm.elements['rcmPmtaskCommonDTO.rcmlistId'].value = getValueById(myGrid, selectedId,'rcmlistId');
	rcmPmtaskListForm.elements['rcmPmtaskCommonDTO.rcmfuncId'].value = getValueById(myGrid, selectedId,'rcmfuncId');
	rcmPmtaskListForm.elements['rcmPmtaskCommonDTO.rcmffailId'].value = getValueById(myGrid, selectedId,'rcmffailId');
	rcmPmtaskListForm.elements['rcmPmtaskCommonDTO.rcmeqId'].value = getValueById(myGrid, selectedId,'rcmeqId');
	rcmPmtaskListForm.elements['rcmPmtaskCommonDTO.rcmeqasmbId'].value = getValueById(myGrid, selectedId,'rcmeqasmbId');
	rcmPmtaskListForm.elements['rcmPmtaskCommonDTO.rcmfmeaId'].value = getValueById(myGrid, selectedId,'rcmfmeaId');
	rcmPmtaskListForm.elements['rcmPmtaskCommonDTO.rcmpmtaskId'].value = getValueById(myGrid, selectedId,'rcmpmtaskId');
    rcmPmtaskListForm.elements['strutsAction'].value = '<%=RcmPmtaskDetailAction.PMTASK_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(rcmPmtaskListForm), 'rcmPmtaskDetail'); 
} 

/**
 * Excel Export
 */
function goExcel()
{
	clearKey();
	excelServerAction("rcmPmtaskList", rcmPmtaskListForm );
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/rcmPmtaskList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="rcmPmtaskCommonDTO.filterRcmlistId"/>

<html:hidden property="rcmPmtaskCommonDTO.rcmlistId"/>
<html:hidden property="rcmPmtaskCommonDTO.rcmfuncId"/>
<html:hidden property="rcmPmtaskCommonDTO.rcmffailId"/>
<html:hidden property="rcmPmtaskCommonDTO.rcmeqId"/>
<html:hidden property="rcmPmtaskCommonDTO.rcmeqasmbId"/>
<html:hidden property="rcmPmtaskCommonDTO.rcmfmeaId"/>
<html:hidden property="rcmPmtaskCommonDTO.rcmpmtaskId"/>

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
				<!--  -->
				<div class="field">
					<label><bean:message key="LABEL.rcmSystemDesc"/></label>
					<div class="input_box">
						<html:text property="rcmPmtaskCommonDTO.filterRcmlistDesc" tabindex="60"/>
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
		<div class="article_box" >
			<div class="grid_area">
				<div id="gridbox" style="height:270px; background-color:white;"></div>		
			</div>			
		</div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>


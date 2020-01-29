<%--===========================================================================
목록
author  kim21017
version $Id: rcmFmeaList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.rcm.fmea.action.RcmFmeaListAction" %>
<%@ page import="dream.rcm.fmea.action.RcmFmeaDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 제목-->
<title><bean:message key="MENU.RCMFMEA"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//

var myGrid, rcmNameAc;

function loadPage() 
{
    initGrid();
    
    rcmNameAc = new autoC({"rcmFmeaCommonDTO.filterRcmlistDesc":"description"});
	rcmNameAc.setTable("TARCMLIST");
	rcmNameAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    });
	rcmNameAc.setAcResultMap({
        "rcmFmeaCommonDTO.filterRcmlistId":"rcmlist_id"
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
    	rcmFmeaListForm.elements['rcmFmeaCommonDTO.rcmlistId'].value = "";
    	rcmFmeaListForm.elements['rcmFmeaCommonDTO.rcmfuncId'].value = "";
    	rcmFmeaListForm.elements['rcmFmeaCommonDTO.rcmffailId'].value = "";
    	rcmFmeaListForm.elements['rcmFmeaCommonDTO.rcmeqId'].value = "";
    	rcmFmeaListForm.elements['rcmFmeaCommonDTO.rcmeqasmbId'].value = "";
    	rcmFmeaListForm.elements['rcmFmeaCommonDTO.rcmfmeaId'].value = "";
    	rcmFmeaListForm.elements['rcmFmeaCommonDTO.critylistId'].value = "";
    	return sortColumn("rcmFmeaList", this, rcmFmeaListForm, ["rcmlistId", "rcmfuncId","rcmffailId", "rcmeqId","rcmeqasmbId"], ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	
	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/rcmFmeaList.do";
    rcmFmeaListForm.elements['strutsAction'].value = '<%=RcmFmeaListAction.FMEA_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(rcmFmeaListForm), ["rcmlistId", "rcmfuncId","rcmffailId", "rcmeqId","rcmeqasmbId" ], "Y");

}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	clearKey();
	
    findGridList('Search', '<%=RcmFmeaListAction.FMEA_LIST_FIND%>');   
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
	rcmFmeaListForm.elements['rcmFmeaCommonDTO.rcmlistId'].value = getValueById(myGrid, selectedId,'rcmlistId');
	rcmFmeaListForm.elements['rcmFmeaCommonDTO.rcmfuncId'].value = getValueById(myGrid, selectedId,'rcmfuncId');
	rcmFmeaListForm.elements['rcmFmeaCommonDTO.rcmffailId'].value = getValueById(myGrid, selectedId,'rcmffailId');
	rcmFmeaListForm.elements['rcmFmeaCommonDTO.rcmeqId'].value = getValueById(myGrid, selectedId,'rcmeqId');
	rcmFmeaListForm.elements['rcmFmeaCommonDTO.rcmeqasmbId'].value = getValueById(myGrid, selectedId,'rcmeqasmbId');
	rcmFmeaListForm.elements['rcmFmeaCommonDTO.rcmfmeaId'].value = getValueById(myGrid, selectedId,'rcmfmeaId');
	rcmFmeaListForm.elements['rcmFmeaCommonDTO.critylistId'].value = getValueById(myGrid, selectedId,'critylistId');
	
	goCommonTabPage(rcmFmeaListForm, <%= RcmFmeaDetailAction.FMEA_DETAIL_INIT %>, pageId);
}

function clearKey()
{
	rcmFmeaListForm.elements['rcmFmeaCommonDTO.rcmlistId'].value = "";
	rcmFmeaListForm.elements['rcmFmeaCommonDTO.rcmfuncId'].value = "";
	rcmFmeaListForm.elements['rcmFmeaCommonDTO.rcmffailId'].value = "";
	rcmFmeaListForm.elements['rcmFmeaCommonDTO.rcmeqId'].value = "";
	rcmFmeaListForm.elements['rcmFmeaCommonDTO.rcmeqasmbId'].value = "";
	rcmFmeaListForm.elements['rcmFmeaCommonDTO.rcmfmeaId'].value = "";
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_arrayVal)
{
	clearKey();
	
	rcmFmeaListForm.elements['rcmFmeaCommonDTO.rcmlistId'].value = _arrayVal[0];
	rcmFmeaListForm.elements['rcmFmeaCommonDTO.rcmfuncId'].value = _arrayVal[1];
	rcmFmeaListForm.elements['rcmFmeaCommonDTO.rcmffailId'].value = _arrayVal[2];
	rcmFmeaListForm.elements['rcmFmeaCommonDTO.rcmeqId'].value = _arrayVal[3];
	rcmFmeaListForm.elements['rcmFmeaCommonDTO.rcmeqasmbId'].value = _arrayVal[4];

	findGridList('ReloadRow');

}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('rcmFmeaDetail');	
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    rcmFmeaListForm.elements['rcmFmeaCommonDTO.rcmlistId'].value = getValueById(myGrid, selectedId,'rcmlistId');
	rcmFmeaListForm.elements['rcmFmeaCommonDTO.rcmfuncId'].value = getValueById(myGrid, selectedId,'rcmfuncId');
	rcmFmeaListForm.elements['rcmFmeaCommonDTO.rcmffailId'].value = getValueById(myGrid, selectedId,'rcmffailId');
	rcmFmeaListForm.elements['rcmFmeaCommonDTO.rcmeqId'].value = getValueById(myGrid, selectedId,'rcmeqId');
	rcmFmeaListForm.elements['rcmFmeaCommonDTO.rcmeqasmbId'].value = getValueById(myGrid, selectedId,'rcmeqasmbId');
	rcmFmeaListForm.elements['rcmFmeaCommonDTO.rcmfmeaId'].value = getValueById(myGrid, selectedId,'rcmfmeaId');
	rcmFmeaListForm.elements['rcmFmeaCommonDTO.critylistId'].value = getValueById(myGrid, selectedId,'critylistId');
    rcmFmeaListForm.elements['strutsAction'].value = '<%=RcmFmeaDetailAction.FMEA_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(rcmFmeaListForm), 'rcmFmeaDetail'); 
} 

/**
 * Excel Export
 */
function goExcel()
{
    excelAction(myGrid);
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/rcmFmeaList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="rcmFmeaCommonDTO.filterRcmlistId"/>

<html:hidden property="rcmFmeaCommonDTO.rcmlistId"/>
<html:hidden property="rcmFmeaCommonDTO.rcmfuncId"/>
<html:hidden property="rcmFmeaCommonDTO.rcmffailId"/>
<html:hidden property="rcmFmeaCommonDTO.rcmeqId"/>
<html:hidden property="rcmFmeaCommonDTO.rcmeqasmbId"/>
<html:hidden property="rcmFmeaCommonDTO.rcmfmeaId"/>
<html:hidden property="rcmFmeaCommonDTO.critylistId"/>

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
						<html:text property="rcmFmeaCommonDTO.filterRcmlistDesc" tabindex="60"/>
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
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>		
			</div>			
		</div>
	</div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>


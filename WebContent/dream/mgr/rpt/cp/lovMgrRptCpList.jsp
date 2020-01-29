<%--===========================================================================
출력물 선택 Popup
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ page import="dream.mgr.rpt.cp.action.LovMgrRptCpAction"%>
<html>
<head>
<!-- 출력물 선택 -->
<title><bean:message key="PAGE.lovMgrRptCpList"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- ########## page 상단 공통 : 모든 페이지 적용 ########## --> 

<script language="javascript">
<!--//

//그리드명
var myGrid;
var selectedId;

function loadPage() 
{
	initGrid();
}

/**
 * 그리드 초기화
 */
function initGrid()
{	
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowDblClicked",function(rowId, columnId){
		goConfirm();
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
	lovMgrRptCpForm.elements['strutsAction'].value = '<%=LovMgrRptCpAction.FIND%>';
	var url = contextPath + "/lovMgrRptCpList.do";

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(lovMgrRptCpForm), "RPTCPFILEID","Y");
}

function goSelect(){
	goConfirm();
}

/**
 * 확인
 */
function goConfirm()
{
	selectedId = myGrid.getSelectedRowId();
	if(selectedId==null) return;
	
	printReport({
		rptCpFileId: getValueById(myGrid, selectedId, 'RPTCPFILEID')
		,rptListName: getValueById(myGrid, selectedId, 'RPTLISTNAME')
		,svrAddr: getValueById(myGrid, selectedId, 'SVRADDR')
		,designFile: getValueById(myGrid, selectedId, 'DESIGNFILE')
		,queryFile: getValueById(myGrid, selectedId, 'QUERYFILE')
		,rptFileType: getValueById(myGrid, selectedId, 'RPTFILETYPE')
		,param: lovMgrRptCpForm.elements['lovMgrRptCpDTO.param'].value
	})
	.done(function(d){
		goClose();
	})
	.fail(function(d){
		alertMessage1(d);
	});
}

/**
 * 검색
 */
function goSearch()
{
	findGridList('Search');
}

function goClose()
{
	closeLayerPopup(this);
}
//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<!-- searchbox 박스 Line -->
	<html:form action="/lovMgrRptCpList" >
		<html:hidden property="strutsAction" />
		<html:hidden property="lovMgrRptCpDTO.rptCpFileId" />
		<html:hidden property="lovMgrRptCpDTO.rptListName" />
		<html:hidden property="lovMgrRptCpDTO.svrAddr" />
		<html:hidden property="lovMgrRptCpDTO.designFile" />
		<html:hidden property="lovMgrRptCpDTO.queryFile" />
		<html:hidden property="lovMgrRptCpDTO.rptFileType" />
		<html:hidden property="lovMgrRptCpDTO.isUse" />
		<html:hidden property="lovMgrRptCpDTO.param" />
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
					<!-- 출력물# -->
					<div class="field">
						<label><bean:message key="LABEL.reportNo"/></label>
						<div class="input_read">
							<html:text property="lovMgrRptCpDTO.rptListNo" tabindex="10" readonly="true" />
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
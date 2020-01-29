<%--===========================================================================
단가계약  AC LOV
author  youngjoo38
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
<%@ page import="dream.mgr.contract.action.LovMgrContractListAction"%>
<html>
<head>
<!-- 단가계약 -->
<title><bean:message key="PAGE.lovMgrContractList"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- ########## page 상단 공통 : 모든 페이지 적용 ########## --> 

<script language="javascript">
<!--//

//그리드명
var myGrid;

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
	
	myGrid.enableMultiselect(chkFilter());
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowDblClicked",function(rowId, columnId){
		goConfirm();
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
		lovMgrContractListForm.elements['lovMgrContractListDTO.contractId'].value = "";
    	return sortColumn("lovMgrContractList", this, workPmStdCalibListForm, "CONTRACTID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	setHeader(myGrid, "gridbox", "goSearch", "lovMgrContractList"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	lovMgrContractListForm.elements['strutsAction'].value = '<%=LovMgrContractListAction.LIST_FIND%>';
	var url = contextPath + "/lovMgrContractList.do";

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(lovMgrContractListForm), "CONTRACTID","Y");
}

function goSelect(){
	goConfirm();
}

/**
 * 확인
 */
function goConfirm()
{	
	setAcValue(myGrid, "CONTRACTID");
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
	<html:form action="/lovMgrContractList" >
		<html:hidden property="currentPageId"/>
		<html:hidden property="keyCode" /><html:hidden property="chName" />
		<html:hidden property="resultCol" /><html:hidden property="multiSelect"/>
		<html:hidden property="codeType" />
		<html:hidden property="param" />
		<html:hidden property="label" />
		<html:hidden property="title" />
		<html:hidden property="lovMgrContractListDTO.contractId" />
		<html:hidden property="lovMgrContractListDTO.filterVendorId"/>
		<html:hidden property="lovMgrContractListDTO.filterServiceId"/>
		<html:hidden property="strutsAction" />
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
				<!-- 계약 # -->
				<div class="field">
					<label>계약 #</label>
					<div class="input_box">
						<html:text property="lovMgrContractListDTO.filterContractNo" tabindex="10"/>
					</div>
				</div>
				<!-- 계약명 -->
				<div class="field">
					<label>계약명</label>
					<div class="input_box">
						<html:text property="lovMgrContractListDTO.filterContractDesc" tabindex="20"/>
					</div>
				</div>
				<!-- 업체명 -->
				<div class="field">
					<label>업체명</label>
					<div class="input_box">
						<html:text property="lovMgrContractListDTO.filterVendorDesc" tabindex="30"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 서비스명 -->
				<div class="field">
					<label>서비스명</label>
					<div class="input_box">
						<html:text property="lovMgrContractListDTO.filterServiceDesc" tabindex="40"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
                <!-- 계약일자 -->
                <div class="field">
                    <label>계약일자</label>
                    <div class="calendar_wrap">
                        <div class="input_box input_carendar">
                            <html:text property="lovMgrContractListDTO.filterContractStartDate" tabindex="50" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="lovMgrContractListDTO.filterContractEndDate" tabindex="60" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>
                <!-- 계약기간시작일자 -->
                <div class="field">
                    <label>계약기간시작일자</label>
                    <div class="calendar_wrap">
                        <div class="input_box input_carendar">
                            <html:text property="lovMgrContractListDTO.filterContractFromStartDate" tabindex="70" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="lovMgrContractListDTO.filterContractFromEndDate" tabindex="80" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>
                <!-- 계약기간종료일자 -->
                <div class="field">
                    <label>계약기간종료일자</label>
                    <div class="calendar_wrap">
                        <div class="input_box input_carendar">
                            <html:text property="lovMgrContractListDTO.filterContractToStartDate" tabindex="90" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                        <div class="input_box input_carendar">
                            <html:text property="lovMgrContractListDTO.filterContractToEndDate" tabindex="100" />
                            <p class="open_calendar"><span>날짜</span></p>
                        </div>
                    </div>
                </div>
				<!-- 사용여부 -->
				<div class="field">
					<label>사용여부</label>
					<div class="input_box">
						<html:text property="lovMgrContractListDTO.filterIsUse" tabindex="110"/>
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
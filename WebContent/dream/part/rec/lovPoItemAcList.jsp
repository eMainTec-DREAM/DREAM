<%--===========================================================================
발주선택 jsp
author  nhkim8548
version $Id: lovPoItemAcList.jsp,v 1.1 2018/09/13 14:40:01 nhkim8548 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ page import="dream.part.rec.action.LovPoItemAcListAction"%>
<%@ page import="common.bean.User"%>
<%
    User user = (User) request.getSession().getAttribute(request.getSession().getId());
%>
<html>
<head>
<!-- 컨텐츠 -->
<title><bean:message key="LABEL.selectPo" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="popupPage">
<!-- ########## page 상단 공통 : 모든 페이지 적용 ########## -->

<script language="javascript">
//그리드명
var myGrid;

/** 자동완성 변수 */
var plantAc;

function loadPage() 
{
	
	initGrid();
	// 공장 자동완성
    plantAc = new autoC({"lovPoItemAcListDTO.plantDesc":"description"});
    plantAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
      , "is_use":"Y"
    });
    plantAc.setAcDConditionMap({
    });
    plantAc.setTable("TAPLANT");
    plantAc.setAcResultMap({
        "lovPoItemAcListDTO.plantId":"plant"
    });
    plantAc.init();
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
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	setHeader(myGrid, "gridbox", "goSearch"); // grid, grid id
}


/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
	var url = contextPath + "/lovPoItemAcList.do";
	lovPoItemAcListForm.elements['strutsAction'].value = '<%=LovPoItemAcListAction.LOV_PT_REC_FIND%>';
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(lovPoItemAcListForm), "POITEMID");
}
function goSelect() {
	goConfirm();
}
/**
 * 확인
 */
function goConfirm() {
	setAcValue(myGrid, "POITEMID");
}
/**
 * 검색
 */
function goSearch() {
	findGridList("Search");
}
// Excel Export
function goExcel()
{
	lovPoItemAcListForm.elements['lovPoItemAcListDTO.poItemId'].value = "";
	excelServerAction("lovPoItemAcList", lovPoItemAcListForm );  
}
function goClose() {
	closeLayerPopup(this);
}
//-->
</script>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
	<!-- searchbox 박스 Line -->
	<html:form action="/lovPoItemAcList.do">
	<html:hidden property="multiSelect"/>
	<html:hidden property="keyCode" /><html:hidden property="chName" />
	<html:hidden property="resultCol" /><html:hidden property="multiSelect"/>
	<html:hidden property="codeType" />
	<html:hidden property="strutsAction" />
	<html:hidden property="currentPageId"/>
	<!-- keyCode, resultCol, codeType, chName / multiSelect <- 멀티 선택 시 필요 -->
		<div class="section_wrap">
			<div class="sheader_box">
				<div class="sheader_wrap">
					<a></a>
				</div>
				<div class="stitle_wrap">
					<div class="stitle_icon">
						<a></a>
					</div>
					<div class="stitle_tx">
						<bean:message key="LABEL.Filter" />
					</div>
				</div>
				<div class="function_box filter">
					<div class="fb_group3">
						<div class="sfb_wrap" style="display: none;"></div>
					</div>
					<div class="fb_group2"></div>
					<div class="b_line"></div>
					<div class="fb_group1"></div>
				</div>
			</div>
			<!--sheader_box end-->
			<div class="article_box">
				<div class="form_box">
					<div class="field">
						<!-- 발주번호 -->
						<label><bean:message key="LABEL.orderNo" /></label>
						<div class="input_box">
							<html:text property="lovPoItemAcListDTO.orderNo" tabindex="10" />
						</div>
					</div>
					<div class="field">
						<!-- 부품번호 -->
						<label><bean:message key="LABEL.partNo" /></label>
						<div class="input_box">
							<html:text property="lovPoItemAcListDTO.partNo" tabindex="20" />
						</div>
					</div>
					<div class="field">
						<!-- 부품명 -->
						<label><bean:message key="LABEL.partDesc" /></label>
						<div class="input_box">
							<html:text property="lovPoItemAcListDTO.partNameSize" tabindex="30" />
						</div>
					</div>
					<div class="field">
						<!-- 공장 -->
						<label><bean:message key="LABEL.plant" /></label>
						<div class="input_box">
							<html:text property="lovPoItemAcListDTO.plantDesc" tabindex="40" />
							<p class="open_spop">
								<a><span>조회</span></a>
							</p>
						</div>
					</div>
				</div>
			</div>
			<!--article_box end-->
		</div>
		<!--  end section_wrap -->
		<div class="section_wrap">
			<div class="sheader_box">
				<div class="sheader_wrap">
					<a></a>
				</div>
				<div class="stitle_wrap">
					<div class="stitle_icon">
						<a></a>
					</div>
					<div class="stitle_tx">
						<bean:message key="LABEL.List" />
					</div>
				</div>
				<div class="function_box list">
					<div class="fb_group3">
						<div class="sfb_wrap" style="display: none;"></div>
					</div>
					<div class="fb_group2"></div>
					<div class="b_line"></div>
					<div class="fb_group1"></div>
				</div>
			</div>
			<!--sheader_box end-->
			<div class="article_box" id="listBox">
				<div class="grid_area">
					<div id="gridbox" style="width: 100%; height: 270px; background-color: white;"></div>
				</div>
			</div>
		</div>
		<!--  End of section_wrap -->
	</html:form>
</body>
</html>
<%--===========================================================================
N Year Spare Part
author  youngjoo38
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.asset.rpt.nyearpo.action.AssetRptNYearPOListAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/functions.tld" prefix="udf"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- N Year Spare Part -->
<title><bean:message key='PAGE.assetRptNYearPOList'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//
var vendorDescAc;
var selectedRowId;

function loadPage() 
{
	initGrid();
	
    vendorDescAc = new autoC({"assetRptNYearPOCommonDTO.filterVendorDesc":"description"});
    vendorDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_use":"Y"
  	   });
    vendorDescAc.setTable("TAVENDOR");
    vendorDescAc.setAcResultMap({
        "assetRptNYearPOCommonDTO.filterVendorId":"vendor_id"
    });
    vendorDescAc.init();
}

var myGrid;
function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		selectedRowId = rowId;
		goOpen();
	});
	myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
   		return sortColumn("assetRptNYearPOList", this, assetRptNYearPOListForm, "VENPONO", ind, direction);
	});

	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();

	setHeader(myGrid, "gridbox"); // grid, grid id, callBack
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
    findGridList('Search');   
}

function findGridList(sheetAction)
{
	if(checkValidation()) return;
	
	var form = document.assetRptNYearPOListForm;	
	form.strutsAction.value = '<%=AssetRptNYearPOListAction.LCC_EQUIP_LIST_FIND %>';
	
	var url = contextPath + "/assetRptNYearPOList.do";
	doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(assetRptNYearPOListForm), "VENPONO", "Y");
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
	var form = document.assetRptNYearPOListForm;
	form.elements['assetRptNYearPODetailDTO.venPoNo'].value = getValueById(myGrid, selectedId,'VENPONO');
	goCommonTabPage(form, '' , pageId);
}

/**
 * 상세열기
 */
 function goOpen()
 {
	goTabPage('assetRptNYearPartsList');
}

/**
 * 엑셀 다운.
*/
function goExcel()
{
	excelServerAction("assetRptNYearPOList", assetRptNYearPOListForm );
}
   
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/assetRptNYearPOList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="assetRptNYearPOCommonDTO.filterVendorId"/>
<html:hidden property="assetRptNYearPODetailDTO.venPoNo"/>

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
				<!-- 거래처  -->
				<div class="field">
					<label>거래처</label>
					<div class="input_box">
						<html:text property="assetRptNYearPOCommonDTO.filterVendorDesc" tabindex="10"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- PO#  -->
				<div class="field">
					<label>PO#</label>
					<div class="input_box">
						<html:text property="assetRptNYearPOCommonDTO.filterPoNumber" tabindex="20"/>
					</div>
				</div>
				<!-- 운영기간  -->
				<div class="field">
					<label>운영기간</label>
					<div class="input_box">
						<html:text property="assetRptNYearPOCommonDTO.filterOpPeriod" tabindex="30"/>
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
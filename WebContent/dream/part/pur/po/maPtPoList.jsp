<%--===========================================================================
발주이력 - 목록
author  kim21017
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.pur.po.action.MaPtPoListAction" %>
<%@ page import="dream.part.pur.po.action.MaPtPoDetailAction" %>
<%@ page import="common.util.CommonUtil"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<%@ page import="common.bean.User"%>
<%
    User loginUser = (User)request.getSession().getAttribute(request.getSession().getId()); 
%>
<html>
<head>
<!-- 발주이력 -->
<title><bean:message key='MENU.PTPO'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;

var deptAc;
var partNameAc;
var vendorDescAc;
var poStatusAc;
var isUseAc;
function loadPage() 
{
	maPtPoListForm.elements['maPtPoCommonDTO.filterDeptId'].value   = loginUser.filterDeptId;
	maPtPoListForm.elements['maPtPoCommonDTO.filterDeptDesc'].value = loginUser.filterDeptDesc;
	
	maPtPoListForm.elements['maPtPoCommonDTO.filterStartPoDate'].value = getMinusMonth2(new Date(), -1);
    maPtPoListForm.elements['maPtPoCommonDTO.filterEndPoDate'].value   = getToday();
    
    initGrid();
    
    deptAc = new autoC({"maPtPoCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcDisplay("DESCRIPTION");
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maPtPoCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.init();
    
    partNameAc = new autoC({"maPtPoCommonDTO.filterPtNameSize":"full_desc"});
    partNameAc.setAcConditionMap({
	   "part_categ":"SPPT",
	   "comp_no":loginUser.compNo
	   });
    partNameAc.setTable("TAPARTS");
    partNameAc.init();
    
    vendorDescAc = new autoC({"maPtPoCommonDTO.filterVendorDesc":"description"});
    vendorDescAc.setAcDisplay("DESCRIPTION");
    vendorDescAc.setAcConditionMap({
    	"comp_no":loginUser.compNo,
    	"is_use":"Y"
  	   });
    vendorDescAc.setTable("TAVENDOR");
    vendorDescAc.setAcResultMap({
        "maPtPoCommonDTO.filterVendorId":"vendor_id"
    });
    vendorDescAc.init();
    
    poStatusAc = new autoC({"maPtPoCommonDTO.filterPoStatusDesc":"description"});
    poStatusAc.setAcDisplay("DESCRIPTION");
    poStatusAc.setAcConditionMap({
        	"list_type":"PO_STATUS",
        	"is_use":"Y"
  	   });
    poStatusAc.setTable("TACDSYSD");
    poStatusAc.setAcResultMap({
        "maPtPoCommonDTO.filterPoStatusId":"cdsysd_no"
    });
    poStatusAc.init();
    

    acSysDesc("maPtPoCommonDTO.filterIsTransfer","maPtPoCommonDTO.filterIsTransfer","IS_USE");
}

function initGrid()
{
	myGrid = new dhtmlXGridObject('gridbox');
	myGrid.setImagesPath('<c:url value="common/dhtmlxSuite/codebase/imgs/" />');
	
	myGrid.enableSmartRendering(true,500);
	myGrid.attachEvent("onRowSelect",function(rowId, columnId){
		goOpen();
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
    myGrid.attachEvent("onBeforeSorting",function(ind,type,direction){
    	maPtPoListForm.elements['maPtPoCommonDTO.poListId'].value = "";
        return sortColumn("maPtPoList", this, maPtPoListForm, "POLISTID", ind, direction);
    });
	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maPtPoList.do";

    maPtPoListForm.elements['strutsAction'].value = '<%=MaPtPoListAction.PTPO_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPtPoListForm), "POLISTID", "Y");
    
	myGrid.setNumberFormat("0,000",getCoumnIdx(myGrid,"REQQTY"),".",",");
	myGrid.setNumberFormat("0,000",getCoumnIdx(myGrid,"POQTY"),".",",");
	myGrid.setNumberFormat("0,000",getCoumnIdx(myGrid,"RECQTY"),".",",");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_poListId)
{
	maPtPoListForm.elements['maPtPoCommonDTO.poListId'].value = _poListId;
	findGridList('ReloadRow');
	maPtPoListForm.elements['maPtPoCommonDTO.poListId'].value = "";
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	maPtPoListForm.elements['maPtPoCommonDTO.poListId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search', '<%=MaPtPoListAction.PTPO_LIST_FIND%>');   
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
	var form = document.maPtPoListForm;
	 
	form.elements['maPtPoCommonDTO.poListId'].value = getValueById(myGrid, selectedId, 'POLISTID');
	goCommonTabPage(form, <%= MaPtPoDetailAction.PTPO_DETAIL_INIT %>, pageId);
}

/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maPtPoDetail');	
}
 
/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maPtPoDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maPtPoListForm.elements['maPtPoCommonDTO.poListId'].value = "";
	goCommonTabPage(maPtPoListForm, '', pageId);
}

/**
  * 삭제
  */
function goDelete()
{
	// 삭제 시작
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'POLISTID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	maPtPoListForm.strutsAction.value = '<%=MaPtPoListAction.PTPO_LIST_DELETE%>';
	var url = contextPath + "/maPtPoList.do";
	$.post(url,FormQueryString(maPtPoListForm)+delArray , function(_data){
    	afterDelete();
    });
}

function afterDelete()
{
	goClose('maPtPoDetail');
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	maPtPoListForm.elements['maPtPoCommonDTO.poListId'].value = "";
    excelServerAction("maPtPoList", maPtPoListForm );  
}

var selectArray;
function goTransferbstock(){
	var checkY = getSelectRows(myGrid, 'ISDELCHECK', 'ISFCRECYN');
	if(checkY.indexOf("Y")>-1){
		alertMessage1('<bean:message key="MESSAGE.MSG0095"/>');
		return;
	}
	var checkStatusId = getSelectRows(myGrid, 'ISDELCHECK', 'POLISTSTATUSID');
	if(checkStatusId.indexOf("P")>-1||checkStatusId.indexOf("W")>-1){
		alertMessage1('<bean:message key="MESSAGE.MSG0096"/>');
		return;
	}
	var selArray = getSelectRows(myGrid, 'ISDELCHECK', 'POLISTID');
	if(typeof selArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	getTopPage().dhtmlx.confirm("<bean:message key='MESSAGE.MSG0018'/>", function(result){
		 if(result){
			selectArray = selArray;
			sequenceNextVal('SQAFCRECLIST_ID');
			maPtPoListForm.strutsAction.value = '<%=MaPtPoListAction.PTPO_LIST_REC%>'; 
			var url = contextPath + "/maPtPoList.do";
			$.post(url,FormQueryString(maPtPoListForm)+selectArray , function(_data){
		    	afterRec();
		    });
		 }
		});
}

function afterRec(){
	alertMessage1('<bean:message key="MESSAGE.MSG0056"/>');
	goSearch();
}
//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPtPoList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPtPoCommonDTO.poListId"/><!-- Key -->
<html:hidden property="maPtPoCommonDTO.filterDeptId"/>
<html:hidden property="maPtPoCommonDTO.filterVendorId"/>
<html:hidden property="maPtPoCommonDTO.filterPoStatusId"/>
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
				<div class="b_line"></div> 
				<div class="fb_group1">
					 
				</div>
			</div>
		</div><!--sheader_box end-->
		<div class="article_box">
			<div class="form_box">
                <div class="field">
                    <label><bean:message key="LABEL.manageDept"/></label>
                    <div class="input_box">
                        <html:text property="maPtPoCommonDTO.filterDeptDesc" tabindex="10"
                                    onkeydown="validationKeyDown('maPtPoCommonDTO.filterDeptDesc', 'maPtPoCommonDTO.filterDeptId');"/>
                        <p class="open_spop"><a href="javascript:lovDept('maPtPoCommonDTO.filterDeptId', '', 'maPtPoCommonDTO.filterDeptDesc');"><span>조회</span></a></p>
                    </div>
                </div>
				<div class="field">
					<label><bean:message key="LABEL.poDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
	                        <html:text property="maPtPoCommonDTO.filterStartPoDate" tabindex="20" />
	                        <p class="open_calendar"><span>날짜</span></p>
	                    </div>
	                    <div class="input_box input_carendar">
	                        <html:text property="maPtPoCommonDTO.filterEndPoDate" tabindex="30" />
	                        <p class="open_calendar"><span>날짜</span></p>
	                    </div>
	                </div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.reqDate1"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
	                        <html:text property="maPtPoCommonDTO.filterStartRequestDate" tabindex="40" />
	                        <p class="open_calendar"><span>날짜</span></p>
	                    </div>
	                    <div class="input_box input_carendar">
	                        <html:text property="maPtPoCommonDTO.filterEndRequestDate" tabindex="50" />
	                        <p class="open_calendar"><span>날짜</span></p>
	                    </div>
	                </div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.ptNameSize"/></label>
					<div class="input_box">
						<html:text property="maPtPoCommonDTO.filterPtNameSize" tabindex="60"/>
					</div>
				</div>
				<!-- 발주거래처 -->
	            <div class="field">
	                <label><bean:message key="LABEL.poVendor"/></label>
	                <div class="input_box">
	                    <html:text property="maPtPoCommonDTO.filterVendorDesc" tabindex="70" 
                                    onkeydown="validationKeyDown('maPtPoCommonDTO.filterVendorDesc', 'maPtPoCommonDTO.filterVendorId');"/>
	                    <p class="open_spop">
	                        <a href="javascript:lovVendor('maPtPoCommonDTO.filterVendorId'
	                                                    , ''
	                                                    , 'maPtPoCommonDTO.filterVendorDesc'
	                                                    , '', '', '', '', ''
	                                                    , '','');">
	                            <span>조회</span>
	                        </a>
	                    </p>
	                </div>
	            </div>
				<!-- 발주상태  -->
				<div class="field">
					<label><bean:message key="LABEL.poStatus"/></label>
					<div class="input_box">
						<html:text property="maPtPoCommonDTO.filterPoStatusDesc" tabindex="80" 
									onkeydown="validationKeyDown('maPtPoCommonDTO.filterPoStatusDesc', 'maPtPoCommonDTO.filterPoStatusId');"/>
						<p class="open_spop"><a href="javascript:lovSysDir('maPtPoCommonDTO.filterPoStatusId', 'maPtPoCommonDTO.filterPoStatusDesc','PO_STATUS');"><span>조회</span></a></p>
					</div>
				</div>
				<!-- 청구번호  -->
                <div class="field">
                    <label><bean:message key="LABEL.reqNo1"/></label>
                    <div class="input_box">
                        <html:text property="maPtPoCommonDTO.filterRequestNo" tabindex="90" />
                    </div>
                </div>
				<!-- 발주번호  -->
                <div class="field">
                    <label><bean:message key="LABEL.poNo"/></label>
                    <div class="input_box">
                        <html:text property="maPtPoCommonDTO.filterPoNo" tabindex="100" />
                    </div>
                </div>
				<!-- 무상입고반영여부 -->
				<div class="field">
					<label><bean:message key="LABEL.isTransferBStock"/></label>
					<div class="input_box">
						<html:text property="maPtPoCommonDTO.filterIsTransfer" tabindex="110" />
						<p class="open_spop">
							<a href="javascript:lovTable('maPtPoCommonDTO.filterIsTransfer', 'maPtPoCommonDTO.filterIsTransfer','YN');">
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
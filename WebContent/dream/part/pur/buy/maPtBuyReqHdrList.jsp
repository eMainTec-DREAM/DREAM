
<%--===========================================================================
구매신청 - 목록
author  kim21017
version $Id: maPtBuyReqHdrList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.pur.buy.action.MaPtBuyReqHdrListAction" %>
<%@ page import="dream.part.pur.buy.action.MaPtBuyReqHdrDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 제목-->
<title><bean:message key="LABEL.buyReq"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//

var myGrid;
/** 자동완성 변수 */
var deptAc;
var partAc;
var prStatusAc;
var creUserAc;
var partNameAc;
var plantAc;
var recByAc;
var recPlantAc;
function loadPage() 
{
    initGrid();
    	
    maPtBuyReqHdrListForm.elements['maPtBuyReqHdrCommonDTO.filterStartReqDate'].value   = getMinusDay(30);
    maPtBuyReqHdrListForm.elements['maPtBuyReqHdrCommonDTO.filterEndReqDate'].value   = getToday();
    maPtBuyReqHdrListForm.elements['maPtBuyReqHdrCommonDTO.filterDeptId'].value   = loginUser.filterDeptId;
    maPtBuyReqHdrListForm.elements['maPtBuyReqHdrCommonDTO.filterDeptDesc'].value   = loginUser.filterDeptDesc;
  	//공장명
    if(loginUser.filterPlant!='null'){
    	maPtBuyReqHdrListForm.elements['maPtBuyReqHdrCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	maPtBuyReqHdrListForm.elements['maPtBuyReqHdrCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
    
    deptAc = new autoC({"maPtBuyReqHdrCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maPtBuyReqHdrCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.init();
    
    partAc = new autoC({"maPtBuyReqHdrCommonDTO.filterPartDesc":"part_no"});
    partAc.setAcConditionMap({
 	   "part_categ":"SPPT",
 	   "comp_no":loginUser.compNo
 	   });
    partAc.setTable("TAPARTS");
    partAc.setAcResultMap({
        "maPtBuyReqHdrCommonDTO.filterPartId":"part_id"
    });
    partAc.init(); //maPtBuyReqHdrCommonDTO.filterPartId
    
    acSysDesc("maPtBuyReqHdrCommonDTO.filterPtPrListStatusDesc","maPtBuyReqHdrCommonDTO.filterPtPrListStatusId","PTPRLIST_STATUS");

    creUserAc = new autoC({"maPtBuyReqHdrCommonDTO.filterUserDesc":"emp_name"});
    creUserAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    creUserAc.setTable("TAUSER");
    creUserAc.setAcResultMap({
        "maPtBuyReqHdrCommonDTO.filterUserId":"user_id"
    });
    creUserAc.setAcDConditionMap({
    	"dept_id" : "maPtBuyReqHdrCommonDTO.filterDeptId"
    });
    creUserAc.init();
    
    
    partNameAc = new autoC({"maPtBuyReqHdrCommonDTO.filterPtDescSize":"full_desc"});
    partNameAc.setAcConditionMap({
	   "part_categ":"SPPT",
	   "comp_no":loginUser.compNo
	   });
    partNameAc.setTable("TAPARTS");
    partNameAc.init();
    
    //공장
    plantAc = new autoC({"maPtBuyReqHdrCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    	, "is_use":"Y"
    });
    plantAc.setAcResultMap({
        "maPtBuyReqHdrCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
    
 	// 접수자 AC
    recByAc = new autoC({"maPtBuyReqHdrCommonDTO.filterRecByDesc":"emp_name"});
    recByAc.setTable("TAEMP");
    recByAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    recByAc.setAcResultMap({
        "maPtBuyReqHdrCommonDTO.filterRecById":"emp_id"
    });
    recByAc.init();
    
  	// 접수공장 AC
    recPlantAc = new autoC({"maPtBuyReqHdrCommonDTO.filterRecPlantDesc":"description"});
    recPlantAc.setTable("TAPLANT");
    recPlantAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
      , "is_use":"Y"
    });
    recPlantAc.setAcResultMap({
        "maPtBuyReqHdrCommonDTO.filterRecPlantId":"plant"
    });
    recPlantAc.init();
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
    	maPtBuyReqHdrListForm.elements['maPtBuyReqHdrCommonDTO.ptPrListId'].value = "";	
    	return sortColumn("maPtBuyReqHdrList", this, maPtBuyReqHdrListForm, "PTPRLISTID", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	
	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maPtBuyReqHdrList.do";
    maPtBuyReqHdrListForm.elements['strutsAction'].value = '<%=MaPtBuyReqHdrListAction.BUY_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPtBuyReqHdrListForm), "PTPRLISTID", "Y");

}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	maPtBuyReqHdrListForm.elements['maPtBuyReqHdrCommonDTO.ptPrListId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search', '<%=MaPtBuyReqHdrListAction.BUY_LIST_FIND%>');   
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
	maPtBuyReqHdrListForm.elements['maPtBuyReqHdrCommonDTO.ptPrListId'].value = getValueById(myGrid, selectedId,'PTPRLISTID');
	
	goCommonTabPage(maPtBuyReqHdrListForm, <%= MaPtBuyReqHdrDetailAction.BUY_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_ptPrListId)
{
	maPtBuyReqHdrListForm.elements['maPtBuyReqHdrCommonDTO.ptPrListId'].value = _ptPrListId;
	findGridList('ReloadRow');
	maPtBuyReqHdrListForm.elements['maPtBuyReqHdrCommonDTO.ptPrListId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maPtBuyReqHdrDetail');	
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maPtBuyReqHdrListForm.elements['maPtBuyReqHdrCommonDTO.ptPrListId'].value = getValueById(myGrid, selectedId,'PTPRLISTID');
    maPtBuyReqHdrListForm.elements['strutsAction'].value = '<%=MaPtBuyReqHdrDetailAction.BUY_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maPtBuyReqHdrListForm), 'maPtBuyReqHdrDetail'); 
} 

 /**
  * 생성
  */
function goCreate()
{
	createValidationCheck(myGrid, "maPtBuyReqHdrDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maPtBuyReqHdrListForm.elements['maPtBuyReqHdrCommonDTO.ptPrListId'].value = "";
	goCommonTabPage(maPtBuyReqHdrListForm, '', pageId);
}


/**
 * Excel Export
 */
function goExcel()
{
	maPtBuyReqHdrListForm.elements['maPtBuyReqHdrCommonDTO.ptPrListId'].value = "";	
    excelServerAction("maPtBuyReqHdrList", maPtBuyReqHdrListForm );  

}

/**
 * 삭제
 */
function goDelete(){
	
	var chkedRowsId = getCheckedRows(myGrid, 'ISDELCHECK');
	for(var i = 0 ;i < chkedRowsId.length; i++)
	{
		if(getValueById(myGrid, chkedRowsId[i], "PTPRLISTSTATUSID") != "W")
		{
			alertMessage1('<bean:message key="MESSAGE.MSG0153"/>');
			return;
		}
	}
	
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'PTPRLISTID'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	maPtBuyReqHdrListForm.strutsAction.value = '<%=MaPtBuyReqHdrListAction.BUY_LIST_DELETE%>';
	var url = contextPath + "/maPtBuyReqHdrList.do";
	
    $.post(url,FormQueryString(maPtBuyReqHdrListForm)+delArray , function(_data){
    	afterDelete();
    });
}

  
function afterDelete(){
	goClose('maPtBuyReqHdrDetail');
	goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG240"/>');
 }
 
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = maPtBuyReqHdrListForm.elements['maPtBuyReqHdrCommonDTO.ptPrListId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPtBuyReqHdrList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="maPtBuyReqHdrCommonDTO.ptPrListId"/>
<html:hidden property="maPtBuyReqHdrCommonDTO.filterUserId"/>
<html:hidden property="maPtBuyReqHdrCommonDTO.filterPartId"/>
<html:hidden property="maPtBuyReqHdrCommonDTO.filterPartNo"/>
<html:hidden property="maPtBuyReqHdrCommonDTO.filterDeptId"/>
<html:hidden property="maPtBuyReqHdrCommonDTO.filterPtPrListStatusId"/>
<html:hidden property="maPtBuyReqHdrCommonDTO.filterPlantId"/>
<html:hidden property="maPtBuyReqHdrCommonDTO.filterRecById"/>
<html:hidden property="maPtBuyReqHdrCommonDTO.filterRecPlantId"/>
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
				<!-- 신청부서 -->
				<div class="field">
					<label><bean:message key="LABEL.reqDept"/></label>
					<div class="input_box">
						<html:text property="maPtBuyReqHdrCommonDTO.filterDeptDesc" tabindex="10"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 신청일자 -->
				<div class="field">
					<label><bean:message key="LABEL.requestDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="maPtBuyReqHdrCommonDTO.filterStartReqDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="maPtBuyReqHdrCommonDTO.filterEndReqDate" tabindex="30" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 제목 -->
				<div class="field">
					<label><bean:message key="LABEL.title"/></label>
					<div class="input_box">
						<html:text property="maPtBuyReqHdrCommonDTO.filterPtPrListDesc" tabindex="40"/>
					</div>
				</div>
				<!-- 품번 -->
				<div class="field">
				    <label><bean:message key="LABEL.ptNo"/></label>
				    <div class="input_box">
				        <html:text property="maPtBuyReqHdrCommonDTO.filterPartDesc" tabindex="50"/>
				        <p class="open_spop">
				            <a>
				                <span>조회</span>
				            </a>
				        </p>
				    </div>
				</div> 
				<!-- 작성상태  -->
				<div class="field">
					<label><bean:message key="LABEL.stWrkStatus"/></label>
					<div class="input_box">
						<html:text property="maPtBuyReqHdrCommonDTO.filterPtPrListStatusDesc" tabindex="60"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 품명/규격 -->
				<div class="field">
					<label><bean:message key="LABEL.parts"/></label>
					<div class="input_box">
						<html:text property="maPtBuyReqHdrCommonDTO.filterPtDescSize" tabindex="70"/>
					</div>
				</div>
				<!-- 요청번호 -->
				<div class="field">
					<label><bean:message key="LABEL.reqNo"/></label>
					<div class="input_box">
						<html:text property="maPtBuyReqHdrCommonDTO.filterPtPrListNo" tabindex="80"/>
					</div>
				</div>
				<!-- 작성자 -->
				<div class="field">
					<label><bean:message key="LABEL.updBy"/></label>
					<div class="input_box">
						<html:text property="maPtBuyReqHdrCommonDTO.filterUserDesc" tabindex="90"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 공장명  -->
				<div class="field">
				    <label><bean:message key="LABEL.plantDesc"/></label>
				    <div class="input_box">
				        <html:text property="maPtBuyReqHdrCommonDTO.filterPlantDesc" tabindex="110"/>
				        <p class="open_spop"><a><span>조회</span></a></p>
				    </div>
				</div>
				<!-- 현장요청# -->
				<div class="field">
					<label><bean:message key="LABEL.pnNo"/></label>
					<div class="input_box">
						<html:text property="maPtBuyReqHdrCommonDTO.filterPtPnListNo" tabindex="120"/>
					</div>
				</div>
				<!-- ERP PR번호 -->
				<div class="field">
					<label><bean:message key="LABEL.erpPrNo"/></label>
					<div class="input_box">
						<html:text property="maPtBuyReqHdrCommonDTO.filterErpPrNo" tabindex="130"/>
					</div>
				</div>
				<!-- 접수자 명-->
				<div class="field">
					<label><bean:message key="LABEL.recName"/></label>
					<div class="input_box">
						<html:text property="maPtBuyReqHdrCommonDTO.filterRecByDesc" tabindex="140"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 접수공장 명 -->
				<div class="field">
				    <label><bean:message key="LABEL.recPlantDesc"/></label>
				    <div class="input_box">
				        <html:text property="maPtBuyReqHdrCommonDTO.filterRecPlantDesc" tabindex="150"/>
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


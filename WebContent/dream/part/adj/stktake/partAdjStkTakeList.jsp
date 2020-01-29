<%--===========================================================================
부품실사 - 목록
author  kim21017
version $Id: partAdjStkTakeList.jsp,v 1.1 2015/12/03 01:45:27 kim21017 Exp $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.adj.stktake.action.PartAdjStkTakeListAction"%>
<%@ page import="dream.part.adj.stktake.action.PartAdjStkTakeDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 제목-->
<title><bean:message key="MENU.PTADJ"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//

var myGrid;
/** 자동완성 변수 */

var creUserAc;
var warehouseAc;
var plantAc;
function loadPage() 
{
    initGrid();

  	//공장명
    if(loginUser.filterPlant!='null'){
    	partAdjStkTakeListForm.elements['partAdjStkTakeCommonDTO.filterPlantId'].value  = loginUser.filterPlant;
    	partAdjStkTakeListForm.elements['partAdjStkTakeCommonDTO.filterPlantDesc'].value  = loginUser.filterPlantDesc;
	}
    
    partAdjStkTakeListForm.elements['partAdjStkTakeCommonDTO.filterStartPlanDate'].value   = getMinusDay(30);
    partAdjStkTakeListForm.elements['partAdjStkTakeCommonDTO.filterEndPlanDate'].value   = getToday();

    creByAc = new autoC({"partAdjStkTakeCommonDTO.filterUserDesc":"emp_name"});
    creByAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    creByAc.setAcDConditionMap({
    	"plant" : "partAdjStkTakeCommonDTO.filterPlantId"
    });
    creByAc.setTable("TAEMP");
    creByAc.setAcResultMap({
    	"partAdjStkTakeCommonDTO.filterUserId":"emp_id"
    }); 
    creByAc.init();  
    
    warehouseAc = new autoC({"partAdjStkTakeCommonDTO.filterWname":"wname"});
    warehouseAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    warehouseAc.setAcDConditionMap({
    	"plant" : "partAdjStkTakeCommonDTO.filterPlantId"
    });
    warehouseAc.setTable("TAWAREHOUSE");
    warehouseAc.setAcResultMap({
    	"partAdjStkTakeCommonDTO.filterWcodeId":"wcode_id"
    }); 
    //creByAc.setCustomLov("lovUser('partAdjStkTakeCommonDTO.filterUserId', '', 'maUserRptCommonDTO.filterUserDesc')");
    warehouseAc.init();  

 	//공장
    plantAc = new autoC({"partAdjStkTakeCommonDTO.filterPlantDesc":"description"});
    plantAc.setTable("TAPLANT");
    plantAc.setAcConditionMap({
        "comp_no":loginUser.compNo
    	, "is_use":"Y"
    });
    plantAc.setAcResultMap({
        "partAdjStkTakeCommonDTO.filterPlantId":"plant"
    });
    plantAc.init();
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
    	partAdjStkTakeListForm.elements['partAdjStkTakeCommonDTO.ptStkTakeListId'].value = "";
    	return sortColumn("partAdjStkTakeList", this, partAdjStkTakeListForm, "PTSTKTAKELISTID", ind, direction);
	});
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	
	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/partAdjStkTakeList.do";
    partAdjStkTakeListForm.elements['strutsAction'].value = '<%=PartAdjStkTakeListAction.BUY_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(partAdjStkTakeListForm), "PTSTKTAKELISTID", "Y");

}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	partAdjStkTakeListForm.elements['partAdjStkTakeCommonDTO.ptStkTakeListId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search', '<%=PartAdjStkTakeListAction.BUY_LIST_FIND%>');   
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
	partAdjStkTakeListForm.elements['partAdjStkTakeCommonDTO.ptStkTakeListId'].value = getValueById(myGrid, selectedId,'PTSTKTAKELISTID');
	partAdjStkTakeListForm.elements['partAdjStkTakeCommonDTO.ptStkTakeStatus'].value = getValueById(myGrid, selectedId,'PTSTKTAKESTATUS');

	goCommonTabPage(partAdjStkTakeListForm, <%= PartAdjStkTakeDetailAction.BUY_DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_ptStkTakeListId)
{
	partAdjStkTakeListForm.elements['partAdjStkTakeCommonDTO.ptStkTakeListId'].value = _ptStkTakeListId;
	findGridList('ReloadRow');
	partAdjStkTakeListForm.elements['partAdjStkTakeCommonDTO.ptStkTakeListId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('partAdjStkTakeDetail');	
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    partAdjStkTakeListForm.elements['partAdjStkTakeCommonDTO.ptStkTakeListId'].value = getValueById(myGrid, selectedId,'PTSTKTAKELISTID');
	partAdjStkTakeListForm.elements['partAdjStkTakeCommonDTO.ptStkTakeStatus'].value = getValueById(myGrid, selectedId,'PTSTKTAKESTATUS');
    partAdjStkTakeListForm.elements['strutsAction'].value = '<%=PartAdjStkTakeDetailAction.BUY_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(partAdjStkTakeListForm), 'partAdjStkTakeDetail'); 
} 

 /**
  * 생성
  */
function goCreate()
{
	createValidationCheck(myGrid, "partAdjStkTakeDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	partAdjStkTakeListForm.elements['partAdjStkTakeCommonDTO.ptStkTakeListId'].value = "";
	goCommonTabPage(partAdjStkTakeListForm, '', pageId);
}

/**
 * Excel Export
 */
function goExcel()
{
    partAdjStkTakeListForm.elements['partAdjStkTakeCommonDTO.ptStkTakeListId'].value = "";
	excelServerAction("partAdjStkTakeList", partAdjStkTakeListForm );  
}

/**
 * 삭제
 */
function goDelete(){
	
	 var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'PTSTKTAKELISTID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	partAdjStkTakeListForm.strutsAction.value = '<%=PartAdjStkTakeListAction.BUY_LIST_DELETE%>';
	var url = contextPath + "/partAdjStkTakeList.do";
	
    $.post(url,FormQueryString(partAdjStkTakeListForm)+delArray , function(_data){
    	afterDelete();
    }); 
}

  
function afterDelete(){
	goClose('partAdjStkTakeDetail');
	goSearch();
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
 }
 

/*
 * 생성 후  재조회
 */
function setKeyAfterCreate(_newId,_pageId)
{
	findGridRow(_newId);
	
	partAdjStkTakeListForm.elements['partAdjStkTakeCommonDTO.ptStkTakeListId'].value = _newId;
	partAdjStkTakeListForm.elements['partAdjStkTakeCommonDTO.ptStkTakeStatus'].value = "W";

	goCommonTabPage(partAdjStkTakeListForm, <%= PartAdjStkTakeDetailAction.BUY_DETAIL_INIT %>, _pageId);
} 
 
/**
 *  Audit trail
 */
 function goAudtrailLink()
 {
    var objectId = partAdjStkTakeListForm.elements['partAdjStkTakeCommonDTO.ptStkTakeListId'].value;
    var fileName = currentPageId;

    if(typeof objectId=="undefined") return;

    goAudTrailList(objectId, fileName);
 }

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/partAdjStkTakeList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="currentPageId"/>
<html:hidden property="partAdjStkTakeCommonDTO.ptStkTakeListId"/>
<html:hidden property="partAdjStkTakeCommonDTO.filterUserId"/>
<html:hidden property="partAdjStkTakeCommonDTO.filterPartId"/>
<html:hidden property="partAdjStkTakeCommonDTO.filterPartNo"/>
<html:hidden property="partAdjStkTakeCommonDTO.filterDeptId"/>
<html:hidden property="partAdjStkTakeCommonDTO.filterWcodeId"/>
<html:hidden property="partAdjStkTakeCommonDTO.filterPtPrListStatusId"/>
<html:hidden property="partAdjStkTakeCommonDTO.filterPlantId"/>
<html:hidden property="partAdjStkTakeCommonDTO.ptStkTakeStatus"/>
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
				<!-- 제목 -->
				<div class="field">
					<label><bean:message key="LABEL.title"/></label>
					<div class="input_box">
						<html:text property="partAdjStkTakeCommonDTO.filterPtPrListDesc" tabindex="40"/>
					</div>
				</div>				
				<!-- 작성자 -->
				<div class="field">
					<label><bean:message key="LABEL.updBy"/></label>
					<div class="input_box">
						<html:text property="partAdjStkTakeCommonDTO.filterUserDesc" tabindex="90"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- 계획일자 -->
				<div class="field">
					<label><bean:message key="LABEL.pmDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="partAdjStkTakeCommonDTO.filterStartPlanDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="partAdjStkTakeCommonDTO.filterEndPlanDate" tabindex="30" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.wname"/></label>
                    <div class="input_box">
                        <html:text property="partAdjStkTakeCommonDTO.filterWname" tabindex="10"/>
	                   	<p class="open_spop">
	                       <a>
	                           <span>조회</span>
	                       </a>
	                   </p>
                    </div>
                </div>
            	<!-- 공장명  -->
				<div class="field">
				    <label><bean:message key="LABEL.plantDesc"/></label>
				    <div class="input_box">
				        <html:text property="partAdjStkTakeCommonDTO.filterPlantDesc" tabindex="100"/>
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


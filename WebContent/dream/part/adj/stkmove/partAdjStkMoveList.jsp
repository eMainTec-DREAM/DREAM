<%--===========================================================================
재고이동 - 목록
author  ghlee
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.adj.stkmove.action.PartAdjStkMoveListAction"%>
<%@ page import="dream.part.adj.stkmove.action.PartAdjStkMoveDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>
<html>
<head>
<!-- 재고이동-->
<title><bean:message key="MENU.PTSTKMOVE"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//

var myGrid;
/** 자동완성 변수 */
var fromWhAc;
var toWhAc;
var partAc;
var regByAc;
function loadPage() 
{
    initGrid();
    
    acSysDesc("partAdjStkMoveCommonDTO.filterPtStkMoveStatusDesc","partAdjStkMoveCommonDTO.filterPtStkMoveStatus","PTSTKMOVE_STATUS");
    
    fromWhAc = new autoC({"partAdjStkMoveCommonDTO.filterFromWname":"wname"});
    fromWhAc.setTable("TAWAREHOUSE");
    fromWhAc.setAcResultMap({
    	"partAdjStkMoveCommonDTO.filterFromWcodeId":"wcode_id"
    });
    fromWhAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
    	,"is_use":"Y"
  	});
    fromWhAc.init();
    
    toWhAc = new autoC({"partAdjStkMoveCommonDTO.filterToWname":"wname"});
    toWhAc.setTable("TAWAREHOUSE");
    toWhAc.setAcResultMap({
    	"partAdjStkMoveCommonDTO.filterToWcodeId":"wcode_id"
    });
    toWhAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
       	,"is_use":"Y"
  	});
    toWhAc.init();
    
    partAc = new autoC({"partAdjStkMoveCommonDTO.filterPartDesc":"full_desc"});
    partAc.setTable("TAPARTS");
    partAc.setAcResultMap({
    	"partAdjStkMoveCommonDTO.filterPartId":"part_id"
    });
    partAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
 	    ,"part_categ":"SPPT"
 	    ,"wcode_id":loginUser.wcodeId
  	});
    partAc.init();
    
    regByAc = new autoC({"partAdjStkMoveCommonDTO.filterRegDesc":"emp_name"});
    regByAc.setTable("TAEMP");
    regByAc.setAcResultMap({
    	"partAdjStkMoveCommonDTO.filterRegId":"emp_id"
    });
    regByAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	});
    regByAc.init();
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
    	partAdjStkMoveListForm.elements['partAdjStkMoveCommonDTO.ptStkMoveId'].value = "";
        return sortColumn("partAdjStkMoveList", this, partAdjStkMoveListForm, "PTSTKMOVEID", ind, direction);
    });
	myGrid.attachEvent("onXLE",function(grdObj,count){ setCounter(grdObj,"gridbox")}); myGrid.init();
	
	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/partAdjStkMoveList.do";
    partAdjStkMoveListForm.elements['strutsAction'].value = '<%=PartAdjStkMoveListAction.LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(partAdjStkMoveListForm), "PTSTKMOVEID", "Y");

}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	partAdjStkMoveListForm.elements['partAdjStkMoveCommonDTO.ptStkMoveId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search', '<%=PartAdjStkMoveListAction.LIST_FIND%>');   
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
	partAdjStkMoveListForm.elements['partAdjStkMoveCommonDTO.ptStkMoveId'].value = getValueById(myGrid, selectedId,'PTSTKMOVEID');
	
	goCommonTabPage(partAdjStkMoveListForm, <%= PartAdjStkMoveDetailAction.DETAIL_INIT %>, pageId);
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_ptStkMoveId)
{
	partAdjStkMoveListForm.elements['partAdjStkMoveCommonDTO.ptStkMoveId'].value = _ptStkMoveId;
	findGridList('ReloadRow');
	partAdjStkMoveListForm.elements['partAdjStkMoveCommonDTO.ptStkMoveId'].value = "";
}
/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('partAdjStkMoveDetail');	
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    partAdjStkMoveListForm.elements['partAdjStkMoveCommonDTO.ptStkMoveId'].value = getValueById(myGrid, selectedId,'PTSTKMOVEID');
    partAdjStkMoveListForm.elements['strutsAction'].value = '<%=PartAdjStkMoveDetailAction.DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(partAdjStkMoveListForm), 'partAdjStkMoveDetail'); 
} 

 /**
  * 생성
  */
function goCreate()
{
	createValidationCheck(myGrid, "partAdjStkMoveDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	partAdjStkMoveListForm.elements['partAdjStkMoveCommonDTO.ptStkMoveId'].value = "";
	goCommonTabPage(partAdjStkMoveListForm, '', pageId);
}

/**
 * Excel Export
 */
function goExcel()
{
	partAdjStkMoveListForm.elements['partAdjStkMoveCommonDTO.ptStkMoveId'].value = "";
	excelServerAction("partAdjStkMoveList", partAdjStkMoveListForm );  
}

/**
 * 삭제
 */
function goDelete(){
	var selArray = getSelectedRows(myGrid, 'ISDELCHECK', 'PTSTKMOVESTATUS').split('&');
	for(var ind in selArray) {
		if(typeof selArray[ind].split('=')[1] != "undefined" && selArray[ind].split('=')[1] != 'W'){
			alertMessage1('<bean:message key="MESSAGE.MSG0153"/>');
			return;
		}
	}
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'PTSTKMOVEID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}

	partAdjStkMoveListForm.strutsAction.value = '<%=PartAdjStkMoveListAction.LIST_DELETE%>';
	var url = contextPath + "/partAdjStkMoveList.do";
	
    $.post(url,FormQueryString(partAdjStkMoveListForm)+delArray , function(_data){
    	afterDelete();
    }); 
}

  
function afterDelete(){
	goClose('partAdjStkMoveDetail');
	
	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

//-->
</script>

</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/partAdjStkMoveList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="partAdjStkMoveCommonDTO.ptStkMoveId"/>
<html:hidden property="partAdjStkMoveCommonDTO.filterPtStkMoveStatus"/>
<html:hidden property="partAdjStkMoveCommonDTO.filterFromWcodeId"/>
<html:hidden property="partAdjStkMoveCommonDTO.filterToWcodeId"/>
<html:hidden property="partAdjStkMoveCommonDTO.filterPartId"/>
<html:hidden property="partAdjStkMoveCommonDTO.filterRegId"/>
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
				<!-- 처리일자 -->
				<div class="field">
					<label><bean:message key="LABEL.moveDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
							<html:text property="partAdjStkMoveCommonDTO.filterStartMoveDate" tabindex="10" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
						<div class="input_box input_carendar">
							<html:text property="partAdjStkMoveCommonDTO.filterEndMoveDate" tabindex="20" />
							<p class="open_calendar"><span>날짜</span></p>
						</div>
					</div>
				</div>
				<!-- 상태 -->
				<div class="field">
					<label><bean:message key="LABEL.status"/></label>
					<div class="input_box">
						<html:text property="partAdjStkMoveCommonDTO.filterPtStkMoveStatusDesc" tabindex="30"/>
						<p class="open_spop"><a><span>조회</span></a></p>
					</div>
				</div>
				<!-- From 창고 -->
				<div class="field">
					<label><bean:message key="LABEL.fromWcodeDesc"/></label>
                    <div class="input_box">
                        <html:text property="partAdjStkMoveCommonDTO.filterFromWname" tabindex="40"/>
	                   	<p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
				<!-- To 창고 -->
				<div class="field">
					<label><bean:message key="LABEL.toWcodeDesc"/></label>
                    <div class="input_box">
                        <html:text property="partAdjStkMoveCommonDTO.filterToWname" tabindex="50"/>
	                   	<p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
            	<!-- 부품  -->
				<div class="field">
				    <label><bean:message key="LABEL.parts"/></label>
				    <div class="input_box">
				        <html:text property="partAdjStkMoveCommonDTO.filterPartDesc" tabindex="60"/>
				        <p class="open_spop"><a><span>조회</span></a></p>
				    </div>
				</div>
            	<!-- 등록자  -->
				<div class="field">
				    <label><bean:message key="LABEL.regId"/></label>
				    <div class="input_box">
				        <html:text property="partAdjStkMoveCommonDTO.filterRegDesc" tabindex="70"/>
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


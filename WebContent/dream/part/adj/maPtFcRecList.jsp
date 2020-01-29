<%--===========================================================================
무상입고 - 목록
author  ssong
version $Id:$
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.part.adj.action.MaPtFcRecListAction" %>
<%@ page import="dream.part.adj.action.MaPtFcRecDetailAction" %>
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
<!-- 무상입고 -->
<title><bean:message key='MENU.PTFCREC'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
 
<script language="javascript">
<!--//

var myGrid;

/** 자동완성 변수 */
var deptAc;
var recStatusAc;
var partNameAc;
var partNoAc;
var multiPartAc;
function loadPage() 
{
	maPtFcRecListForm.elements['maPtFcRecCommonDTO.filterDeptId'].value   = loginUser.filterDeptId;
	maPtFcRecListForm.elements['maPtFcRecCommonDTO.filterDeptDesc'].value = loginUser.filterDeptDesc;
	
	maPtFcRecListForm.elements['maPtFcRecCommonDTO.filterRecStartDate'].value = getMinusMonth2(new Date(), -2); 
    maPtFcRecListForm.elements['maPtFcRecCommonDTO.filterRecEndDate'].value   = getToday();
    
    initGrid();
    
    deptAc = new autoC({"maPtFcRecCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maPtFcRecCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.init();
    
    acSysDesc("maPtFcRecCommonDTO.fcRecStatusDesc","maPtFcRecCommonDTO.fcRecStatus","FCRECLIST_STATUS");
    
    partNameAc = new autoC({"maPtFcRecCommonDTO.filterPartNameSize":"full_desc"});
    partNameAc.setAcConditionMap({
	   "part_categ":"SPPT",
	   "comp_no":loginUser.compNo
	   });
    partNameAc.setTable("TAPARTS");
    partNameAc.init();
    
    partNoAc = new autoC({"maPtFcRecCommonDTO.filterPartDesc":"part_no"});
    partNoAc.setAcConditionMap({
	   "part_categ":"SPPT",
	   "comp_no":loginUser.compNo
	   });
    partNoAc.setTable("TAPARTS");
    partNoAc.setAcResultMap({
        "maPtFcRecCommonDTO.filterPartId":"part_id"
    });
    partNoAc.init();
    
    multiPartAc = new autoC({"maPtFcRecDetailDTO.multiDesc":"full_desc"});
    multiPartAc.setTable("TAPARTS");
    multiPartAc.setAcConditionMap({
	   "comp_no":loginUser.compNo
	   });
    multiPartAc.setAcResultMap({
	    "maPtFcRecDetailDTO.multiKey":"part_id"
	});
    multiPartAc.setMultiSelect(true);
    multiPartAc.init();
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
    	maPtFcRecListForm.elements['maPtFcRecCommonDTO.fcRecListId'].value = "";
        return sortColumn("maPtFcRecList", this, maPtFcRecListForm, "FCRECLISTID", ind, direction);
    });
	setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maPtFcRecList.do";
	
    maPtFcRecListForm.elements['strutsAction'].value = '<%=MaPtFcRecListAction.PTFCREC_LIST_FIND%>';
    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPtFcRecListForm), "FCRECLISTID", "Y");
    
	/* myGrid.setNumberFormat("0,000",getCoumnIdx(myGrid,"RECQTY"),".",","); */
	myGrid.setNumberFormat("0,000.000",getCoumnIdx(myGrid,"UNITPRICE"),".",",");
	myGrid.setNumberFormat("0,000.000",getCoumnIdx(myGrid,"TOTPRICE"),".",",");
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_fcRecListId)
{
	maPtFcRecListForm.elements['maPtFcRecCommonDTO.fcRecListId'].value = _fcRecListId;
	findGridList('ReloadRow');
	maPtFcRecListForm.elements['maPtFcRecCommonDTO.fcRecListId'].value = "";
}

/**
 * 검색 클릭시 호출
 */
function goSearch()
{
	maPtFcRecListForm.elements['maPtFcRecCommonDTO.fcRecListId'].value = "";	// 검색시 Tab 이동Key Clear
    findGridList('Search');
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
	document.maPtFcRecListForm.elements['maPtFcRecCommonDTO.fcRecListId'].value = getValueById(myGrid, selectedId, 'FCRECLISTID');
	goCommonTabPage(maPtFcRecListForm, <%= MaPtFcRecDetailAction.PTFCREC_DETAIL_INIT %>, pageId);
}

/**
 * 상세열기
 */
function goOpen()
{
	goTabPage('maPtFcRecDetail');	
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maPtFcRecListForm.elements['maPtFcRecCommonDTO.fcRecListId'].value = getValueById(myGrid, selectedId, 'FCRECLISTID');
    maPtFcRecListForm.elements['strutsAction'].value = '<%=MaPtFcRecDetailAction.PTFCREC_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maPtFcRecListForm), 'maPtFcRecDetail'); 
} 
 
/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maPtFcRecDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maPtFcRecListForm.elements['maPtFcRecCommonDTO.fcRecListId'].value = "";
	goCommonTabPage(maPtFcRecListForm, '', pageId);
}

/**
  * 삭제
  */
function goDelete()
{
	// 입고완료 여부 체크 
	var cnt = 0;
	var checkRows = myGrid.getCheckedRows(getIndexById(myGrid, 'isDelCheck')).split(",");
	for(var i=0; i < checkRows.length; i++)
	{
		 var fcRecListStatus = getValueById(myGrid, checkRows[i], 'FCRECLISTSTATUS');
		 if(fcRecListStatus == "C")
         {
			 cnt++;
			 myGrid.cells(checkRows[i], getIndexById(myGrid, 'ISDELCHECK')).setValue(0);
         }
	}
	if(cnt > 0)
	{
		//alertMessage1("입고완료된 데이터는 삭제되지 않습니다.");
		alertMessage1('<bean:message key="MESSAGE.MSG0153"/>');
		return;
	}
	
	// 삭제 시작
	var delArray = getDeletRows(myGrid, 'ISDELCHECK', 'FCRECLISTID'); //Grid, check box column seq, pk column seq
	if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
	
	maPtFcRecListForm.strutsAction.value = '<%=MaPtFcRecListAction.PTFCREC_LIST_DELETE%>';
	var url = contextPath + "/maPtFcRecList.do";
	$.post(url,FormQueryString(maPtFcRecListForm)+delArray , function(_data){
    	afterDelete();
    });
}

function afterDelete()
{
	goClose('maPtFcRecDetail');
	//goSearch();
   	alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	maPtFcRecListForm.elements['maPtFcRecCommonDTO.fcRecListId'].value = "";
	excelServerAction("maPtFcRecList", maPtFcRecListForm ); 
    //excelAction(myGrid);
    
    
}

function goSave(){
	var url = contextPath + "/maPtFcRecList.do";
	
    $.post(url,FormQueryString(maPtFcRecListForm), function(_data){
    	afterSave(_data);
    });
}

function afterSave(ajaxXmlDoc)
{
	//=====================================
    if (!checkHttpXml(ajaxXmlDoc)) return;
    //=====================================
    getTopPage().afterSaveAll(currentPageId);
    
    goSearch();
    
	setForNomal();
}

/**
 * 일괄등록
 */
function goRegbatch()
{
	multiPartAc.openLov();
	
	setForUpdate();
}

function setAcLovValue(rtnArr, code)
{
	
	if(code == 'maPtFcRecDetailDTO.multiDesc')
	{
		maPtFcRecListForm.strutsAction.value = '<%=MaPtFcRecListAction.PTFCREC_LIST_INPUT%>';
		
		maPtFcRecListForm.elements['maPtFcRecDetailDTO.recDate'].value = getToday();
		maPtFcRecListForm.elements['maPtFcRecDetailDTO.deptId'].value = loginUser.deptId;
	    maPtFcRecListForm.elements['maPtFcRecDetailDTO.wcodeId'].value = loginUser.wcodeId;
		maPtFcRecListForm.elements['maPtFcRecDetailDTO.recQty'].value = "0";
		maPtFcRecListForm.elements['maPtFcRecDetailDTO.unitPrice'].value = "0";
		maPtFcRecListForm.elements['maPtFcRecDetailDTO.totPrice'].value = "0";
		maPtFcRecListForm.elements['maPtFcRecDetailDTO.fcRecListStatus'].value = "W";
		
		maPtFcRecListForm.elements['maPtFcRecDetailDTO.multiDesc'].value = JSON.stringify(rtnArr);
		
		goSaveAll();
	}
}
//-->
</script>
<SCRIPT src="common/js/bootstrap-dropdown.js"></SCRIPT>
<SCRIPT src="common/js/bootstrap-tab.js"></SCRIPT>
<SCRIPT src="common/js/bootstrap-tabdrop.js"></SCRIPT>
<SCRIPT>
	if (top.location != location) {
    top.location.href = document.location.href ;
  }
		$(function(){
			window.prettyPrint && prettyPrint();
      $('.nav-tabs:first').tabdrop();
      $('.nav-tabs:last').tabdrop({text: 'More options'});
      $('.nav-pills').tabdrop({text: 'With pills'});
		});
</SCRIPT>
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0"> 
<html:form action="/maPtFcRecList.do">
<html:hidden property="strutsAction"/>
<html:hidden property="maPtFcRecCommonDTO.fcRecListId"/><!-- Key -->
<html:hidden property="maPtFcRecCommonDTO.filterDeptId"/>
<html:hidden property="maPtFcRecCommonDTO.filterPartId"/>
<html:hidden property="maPtFcRecCommonDTO.fcRecStatus"/>
<html:hidden property="maPtFcRecDetailDTO.recDate"/>
<html:hidden property="maPtFcRecDetailDTO.deptId"/>
<html:hidden property="maPtFcRecDetailDTO.wcodeId"/>
<html:hidden property="maPtFcRecDetailDTO.recQty"/>
<html:hidden property="maPtFcRecDetailDTO.unitPrice"/>
<html:hidden property="maPtFcRecDetailDTO.totPrice"/>
<html:hidden property="maPtFcRecDetailDTO.fcRecListStatus"/>
<html:hidden property="maPtFcRecDetailDTO.multiKey"/>
<html:hidden property="maPtFcRecDetailDTO.multiDesc"/>
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
                <div class="field">
                    <label><bean:message key="LABEL.manageDept"/></label>
                    <div class="input_box">
                        <html:text property="maPtFcRecCommonDTO.filterDeptDesc" tabindex="10"/>
                        <p class="open_spop"><a><span>조회</span></a></p>
                    </div>
                </div>
				<div class="field">
					<label><bean:message key="LABEL.recDate"/></label>
					<div class="calendar_wrap">
						<div class="input_box input_carendar">
	                        <html:text property="maPtFcRecCommonDTO.filterRecStartDate" tabindex="20" />
	                        <p class="open_calendar"><span>날짜</span></p>
	                    </div>
	                    <div class="input_box input_carendar">
	                        <html:text property="maPtFcRecCommonDTO.filterRecEndDate" tabindex="30" />
	                        <p class="open_calendar"><span>날짜</span></p>
	                    </div>
	                </div>
				</div>
				<div class="field">
					<label><bean:message key="LABEL.ptNameSize"/></label>
					<div class="input_box">
						<html:text property="maPtFcRecCommonDTO.filterPartNameSize" tabindex="50"/>
					</div>
				</div>
				    <!-- 품번 -->
					<div class="field">
					    <label><bean:message key="LABEL.ptNo"/></label>
					    <div class="input_box">
					        <html:text property="maPtFcRecCommonDTO.filterPartDesc" tabindex="60"/>
					        <p class="open_spop">
					            <a>
					                <span>조회</span>
					            </a>
					        </p>
					    </div>
					</div> 
				<!-- 입고상태  -->
				<div class="field">
					<label><bean:message key="LABEL.ptRecListStatus"/></label>
					<div class="input_box">
						<html:text property="maPtFcRecCommonDTO.fcRecStatusDesc" tabindex="70" />
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
		<div class="article_box" id="listBox">
			<div class="grid_area">
				<div id="gridbox" style="width:100%; height:270px; background-color:white;"></div>
			</div>
		</div>
	 </div> <!--  End of section_wrap -->
</html:form> 
</body>
</html>
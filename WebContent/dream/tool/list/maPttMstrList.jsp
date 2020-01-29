<%--===========================================================================
자재마스터
author  
version $Id: $
since   1.0
===========================================================================--%>
<%@ page language="java" buffer="8kb"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" autoFlush="true" isErrorPage="false"%>
<%@ page import="dream.tool.list.action.MaPttMstrListAction" %>
<%@ page import="dream.tool.list.action.MaPttMstrDetailAction" %>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mware" %>

<html>
<head>
<!-- 자재마스터 -->
<title><bean:message key="MENU.PTTMSTR"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="decorator" content="defaultPage">
<!-- 공통메뉴 -->
<script language="javascript">
<!--//

var myGrid;

/** 자동완성 변수 */
var partNameAc;
var isUseAc;
var deptAc;
function loadPage() 
{
	initGrid();
	
    maPttMstrListForm.elements['maPttMstrCommonDTO.filterIsUse'].value = 'Y';
    maPttMstrListForm.elements['maPttMstrCommonDTO.filterIsUseDesc'].value = 'Y';
   // maPttMstrListForm.elements['maPttMstrCommonDTO.filterDeptId'].value   = loginUser.filterDeptId;
   // maPttMstrListForm.elements['maPttMstrCommonDTO.filterDeptDesc'].value = loginUser.filterDeptDesc;
   
    partNameAc = new autoC({"maPttMstrCommonDTO.filterPartNameSize":"full_desc"});
    partNameAc.setAcConditionMap({
 	   "part_categ":"TOOL",
 	   "comp_no":loginUser.compNo
 	   });
    partNameAc.setTable("TAPARTS");
    partNameAc.init();


    
    acSysDesc("maPttMstrCommonDTO.filterIsUseDesc","maPttMstrCommonDTO.filterIsUseDesc","IS_USE");
    
    deptAc = new autoC({"maPttMstrCommonDTO.filterDeptDesc":"description"});
    deptAc.setAcConditionMap({
    	"comp_no":loginUser.compNo
  	   });
    deptAc.setTable("TADEPT");
    deptAc.setAcResultMap({
        "maPttMstrCommonDTO.filterDeptId":"dept_id"
    });
    deptAc.init();
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
    	maPttMstrListForm.elements['maPttMstrCommonDTO.partId'].value = "";
        return sortColumn("maPttMstrList", this, maPttMstrListForm, "PARTID", ind, direction);
    });
    setHeader(myGrid, "gridbox"); // grid, grid id
}

/**
 * 현재 셋팅된 조건으로 값을 조회하여 sheet에 셋팅한다.
 */
function findGridList(sheetAction)
{
    var url = contextPath + "/maPttMstrList.do";

    maPttMstrListForm.elements['strutsAction'].value = '<%=MaPttMstrListAction.PTMSTR_LIST_FIND%>';

    doGridAction(sheetAction, myGrid, "gridbox", url, FormQueryString(maPttMstrListForm), "PARTID", "Y");
    
}

/** 
 * 수정된 그리드 1건을 다시 조회한다.
 */
function findGridRow(_partId)
{
	maPttMstrListForm.elements['maPttMstrCommonDTO.partId'].value = _partId;
	findGridList('ReloadRow');
	maPttMstrListForm.elements['maPttMstrCommonDTO.partId'].value = "";
}

/**
 * Filter에서 Sheet 검색을 하는 경우이다.
 */
function goSearch()
{
	maPttMstrListForm.elements['maPttMstrCommonDTO.partId'].value = "";    // 검색시 Tab 이동Key Clear
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
	maPttMstrListForm.elements['maPttMstrCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'compNo');
    maPttMstrListForm.elements['maPttMstrCommonDTO.partId'].value = getValueById(myGrid, selectedId, 'partId');
   
    goCommonTabPage(maPttMstrListForm, <%=MaPttMstrDetailAction.PTMSTR_DETAIL_INIT%>, pageId);
}

/**
 * 상세 열기
 */
function goOpen()
{
	goTabPage('maPttMstrDetail');
}

function goOpenAction()
{
    var selectedId=myGrid.getSelectedRowId();
    if(selectedId == null) return;
    
    maPttMstrListForm.elements['maPttMstrCommonDTO.compNo'].value = getValueById(myGrid, selectedId, 'compNo');
    maPttMstrListForm.elements['maPttMstrCommonDTO.partId'].value = getValueById(myGrid, selectedId, 'partId');
    maPttMstrListForm.elements['strutsAction'].value = '<%=MaPttMstrDetailAction.PTMSTR_DETAIL_INIT%>';
    openQuickTabPage(FormQueryString(maPttMstrListForm), 'maPttMstrDetail'); 
} 

/**
 * 생성
 */
function goCreate()
{
	createValidationCheck(myGrid, "maPttMstrDetail" , "goCreateAction");
}

function goCreateAction(pageId)
{
	maPttMstrListForm.elements['maPttMstrCommonDTO.partId'].value = "";
    goCommonTabPage(maPttMstrListForm, '', pageId);
}

/**
 * 삭제
 */
function goDelete()
{
    var delArray = getDeletRows(myGrid, 'isDelCheck', 'partId'); //Grid, check box column seq, pk column seq
    if(typeof delArray == "undefined"){
		alertMessage1('<bean:message key="MESSAGE.MSG0074"/>');
		return;
	}
    
    maPttMstrListForm.strutsAction.value = '<%=MaPttMstrListAction.PTMSTR_LIST_DELETE%>';
    var url = contextPath + "/maPttMstrList.do";

    $.post(url,FormQueryString(maPttMstrListForm)+delArray , function(_data){
        afterDelete();
    });
}

function afterDelete()
{
    goClose('maPtMstrDetail');
    //goSearch();
    alertMessage1('<bean:message key="MESSAGE.MSG021"/>');
}

/**
 * Excel Export
 */
function goExcel()
{
	maPttMstrListForm.elements['maPttMstrCommonDTO.partId'].value = "";
    excelServerAction("maPttMstrList", maPttMstrListForm );  

}

//-->
</script>89
</head>
<BODY style="MARGIN: 0px" marginheight="0" marginwidth="0">
<html:form action="/maPttMstrList">
<html:hidden property="strutsAction"/>
<html:hidden property="maPttMstrCommonDTO.compNo"/><!-- Key -->
<html:hidden property="maPttMstrCommonDTO.partId"/><!-- Key -->
<html:hidden property="maPttMstrCommonDTO.filterPlfTypeDesc"/>
<html:hidden property="maPttMstrCommonDTO.filterPlfType"/>
<html:hidden property="maPttMstrCommonDTO.filterMroType"/>
<html:hidden property="maPttMstrCommonDTO.filterIsUse"/>
<html:hidden property="maPttMstrCommonDTO.filterDeptId"/>
<html:hidden property="maPttMstrCommonDTO.filterIsInPart"/>
    <!-- searchbox 박스 Line -->
    <div class="section_wrap">
        <div class="sheader_box">
            <div class="sheader_wrap"><a></a></div>
			<div class="stitle_wrap">
				<div class="stitle_icon"><a></a></div>
				<div class="stitle_tx"><bean:message key="LABEL.Filter"/></div>
			</div>
			<div class="function_box filter not_fold">
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
                       <label><bean:message key="LABEL.partNameSize"/></label>
                       <div class="input_box">
                            <html:text property="maPttMstrCommonDTO.filterPartNameSize" tabindex="10"/>
                       </div>
                   </div>
                   <div class="field">
                       <label><bean:message key="LABEL.partNo"/></label>
                       <div class="input_box">
                            <html:text property="maPttMstrCommonDTO.filterPartNo" tabindex="15"/>
                       </div>
                   </div>
                   <div class="field">
                       <label><bean:message key="LABEL.model"/></label>
                       <div class="input_box">
                            <html:text property="maPttMstrCommonDTO.filterModel" tabindex="20"/>
                       </div>
                   </div>
                   <div class="field">
                       <label><bean:message key="LABEL.maker"/></label>
                       <div class="input_box">
                            <html:text property="maPttMstrCommonDTO.filterMaker" tabindex="30"/>
                       </div>
                   </div>
                   <div class="field">
                       <label><bean:message key="LABEL.ptUsage"/></label>
                       <div class="input_box">
                            <html:text property="maPttMstrCommonDTO.filterUsage" tabindex="40"/>
                       </div>
                   </div>
                   <div class="field">
                        <label><bean:message key="LABEL.isUse"/></label>
                        <div class="input_box">
                           <html:text property="maPttMstrCommonDTO.filterIsUseDesc" tabindex="60"/>
                            <p class="open_spop">
                                <a>
                                    <span>조회</span>
                                </a>
                            </p>
                      </div>
                   </div> 
                   <!-- 관리부서 -->
				   <div class="field">
					    <label><bean:message key="LABEL.usedDept"/></label>
					    <div class="input_box">
					        <html:text property="maPttMstrCommonDTO.filterDeptDesc" tabindex="70"/>
					        <p class="open_spop"><a><span>조회</span></a></p>
					    </div>
                   </div> 
                   <div class="field">
                       <label><bean:message key="LABEL.vendorPtCode"/></label>
                       <div class="input_box">
                            <html:text property="maPttMstrCommonDTO.filterVendorPtCode" tabindex="90"/>
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
			<!--<div class="stitle_box"><bean:message key="LABEL.Filter"/></div>  -->
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